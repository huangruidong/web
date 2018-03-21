package com.xinzhen.xznongshang.utils;

/**
 * Created by liufang on 2018/1/10.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImaZipUtil {
    /**
     * 压缩图片到指定宽高，并进行质量压缩，最终大小保持在100K以下
     *
     * @param sourceBm
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public static Bitmap zipPic(Bitmap sourceBm, float targetWidth, float targetHeight) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        // 可删除
        newOpts.inPurgeable = true;
        // 可共享
        newOpts.inInputShareable = true;
        // 转成数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        sourceBm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] temp = baos.toByteArray();
        // 此时返回bm为空
        Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = targetHeight;
        float ww = targetWidth;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        // 如果宽度大的话根据宽度固定大小缩放
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            // 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) {
            be = 1;
        }
        // 设置缩放比例
        newOpts.inSampleSize = be;
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length, newOpts);
        // 压缩好比例大小后再进行质量压缩
        return compressImage(bitmap);
    }

    /**
     * @param image
     * @return
     * @Description 质量压缩方法
     * @author XiongJie
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 100) {
            // 重置baos即清空baos
            baos.reset();
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            // 每次都减少10
            options -= 10;
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 只进行分辨率压缩，不进行图片的质量压缩
     *
     * @param sourceBm
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public static Bitmap zipPicWithoutCompress(Bitmap sourceBm, float targetWidth, float targetHeight) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        // 可删除
        newOpts.inPurgeable = true;
        // 可共享
        newOpts.inInputShareable = true;
        // 转成数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        sourceBm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] temp = baos.toByteArray();
        // 此时返回bm为空
        Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = targetHeight;
        float ww = targetWidth;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        // be=1表示不缩放
        int be = 1;
        if (w > h && w > ww) {
            // 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            // 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) {
            be = 1;
        }
        // 设置缩放比例
        newOpts.inSampleSize = be;
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length, newOpts);
        // 压缩好比例大小后再进行质量压缩
        return bitmap;
    }

    //获取sd卡的路径
    public static void savaBitmap(Bitmap bitmap,String filePath) {
        File f = new File(filePath);
        FileOutputStream fOut = null;
        try {
            f.createNewFile();
            fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);//把Bitmap对象解析成流
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}