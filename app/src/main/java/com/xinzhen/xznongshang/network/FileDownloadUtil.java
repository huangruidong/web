package com.xinzhen.xznongshang.network;

import android.content.Context;

import com.xinzhen.xznongshang.common.BaseParams;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.tools.utils.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/7/6 09:53
 * <p/>
 * Description:
 */
public class FileDownloadUtil {
    private static final String TAG = "DownloadFile";

    /**
     * 保存文件
     *
     * @param response 文件流 flag:1 投资协议，flag:2 借款协议 ，flag:3 转让协议 ，flag:4 受让协议
     */
    public static String writeResponseBodyToDisk(Response<ResponseBody> response, String folder, char flag) {
        ResponseBody body = response.body();
        String filename1 = response.headers().get("Content-Disposition");
        if (filename1 == null) {
            ToastUtil.toast("下载失败,文件不存在。");
            return null;
        }
        filename1 = filename1.substring("attachment;filename=".length());

        try {
            filename1 = URLDecoder.decode(filename1, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filename;
        switch (flag) {
            case '1':
                //投资协议
                filename = folder + "协议.pdf";
                break;
            case '2':
                //借款协议
                filename = folder + "协议.zip";
                break;
            case '3':
                //转让协议
                filename = folder + "协议.pdf";
                break;
            case '4':
                //受让协议
                filename = folder + "协议.pdf";
                break;
            default:
                filename = "协议.pdf";
                break;
        }


        Logger.i(TAG, "下载完成，准备写入文件.");
        Context context = ContextHolder.getContext();
        // 如果没有读写SD卡的权限，则不写入文件
//        if (null != context && !PermissionCheck.getInstance().checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Logger.i(TAG, "请求读写SD卡权限");
//            return null;
//        }
        // 目录不存在  则创建
        File dir = new File(BaseParams.ROOT_PATH + File.separator + folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            File futureStudioIconFile = new File(BaseParams.ROOT_PATH + File.separator + filename);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Logger.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                ToastUtil.toast("下载成功");
                return filename;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 文件是否存在
     */
    public static boolean isExists(String filename) {
        try {
            File file = new File(BaseParams.ROOT_PATH + File.separator + filename);
            if (!file.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 解压缩一个文件
     *
     * @param file   要解压的压缩文件
     * @param folder 解压缩的目标目录
     * @throws IOException 当解压缩过程出错时抛出
     */
    public static int upZipFile(String file, String folder) {
        int count = 0;
        String folderPath = BaseParams.ROOT_PATH + File.separator + folder;
        String filePath = BaseParams.ROOT_PATH + File.separator + file;

        File zipFile = new File(filePath);
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        try {
            ZipFile zf = new ZipFile(zipFile);
            for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                InputStream in = zf.getInputStream(entry);
//                String str = folderPath + File.separator + entry.getName();
                String str = folderPath + File.separator + folder + "协议" + count + ".pdf";
                File desFile = new File(str);
                if (!desFile.exists()) {
                    File fileParentDir = desFile.getParentFile();
                    if (!fileParentDir.exists()) {
                        fileParentDir.mkdirs();
                    }
                    desFile.createNewFile();
                }
                OutputStream out = new FileOutputStream(desFile);
                byte buffer[] = new byte[1024];
                int realLength;
                while ((realLength = in.read(buffer)) > 0) {
                    out.write(buffer, 0, realLength);
                }
                in.close();
                out.close();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static List<String> searchFiles(String folderName) {
        String folderPath = BaseParams.ROOT_PATH + File.separator + folderName;
        List<String> list = new ArrayList();
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            Logger.d(TAG, "no file");
            return list;
        }
        File[] files = new File(folderPath).listFiles();
        for (File file : files) {
            list.add(file.getName());
        }
        return list;
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int byteSum = 0;
            int byteRead = 0;
            File oldFile = new File(oldPath);
            if (oldFile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; //字节数 文件大小
                    System.out.println(byteSum);
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
                oldFile.delete();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }
}
