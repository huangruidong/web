package com.xinzhen.xznongshang.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.utils.XZPermissionUtil;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.AndroidUtil;

import java.io.File;

/**
 * Author: tudehua
 * Date: 17/6/26 下午3:48
 * <p/>
 * Description: 二维码弹出窗口
 */
public class QrCodePopupWindow extends PopupWindow {
    private Context context;
    private ImageView qrImage;
    /**
     * 根路径
     */
    public static final String ROOT_PATH = getSDPath() + "/Stanley";
    /**
     * 照片文件文件保存路径
     */
    public static final String PHOTO_PATH = ROOT_PATH + "/photo";

    public QrCodePopupWindow(Context context) {
        super(context);
        this.context = context;

        View qrView = View.inflate(context, R.layout.qrcode_popup_window, null);
        final View qrContent = qrView.findViewById(R.id.qrcode_content);
        qrImage = (ImageView) qrView.findViewById(R.id.qrcode_iv);
        qrView.findViewById(R.id.qrcode_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        // QrCodePopupWindow
        this.setContentView(qrView);
        // QrCodePopupWindow
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // QrCodePopupWindow
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // QrCodePopupWindow
        this.setFocusable(true);
        // QrCodePopupWindow
//        this.setAnimationStyle(R.style.PopupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SharePopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // shareView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        qrView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = qrContent.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        XZPermissionUtil.requestPermissions(AndroidUtil.getActivity(qrView), Constant.PERMISSIONS);
    }

    public void setQrImage(String shareLink) {
        File filePhoto = new File(PHOTO_PATH);
        if (!filePhoto.exists()) {
            if (!filePhoto.mkdirs()) {
                Logger.d("QrCode", "文件创建失败");
            }
        }
        String path = PHOTO_PATH + File.separator + "/qr_code.jpg";
        File fileImage = new File(path);
        if (!fileImage.exists()) {
            try {
                if (!fileImage.createNewFile()) {
                    Logger.d("QrCode", "文件创建失败");
                }
            } catch (Exception e) {
                Logger.d("logger", "error", e);
            }
        }
        boolean qr = CodeUtil.createQRImage(shareLink, context.getResources().getDimensionPixelSize(R.dimen.x240), context.getResources().getDimensionPixelSize(R.dimen.x240), null, path);
        if (qr) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            qrImage.setImageBitmap(bitmap);
        }
    }

    /**
     * 获取SD卡的根目录
     */
    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        if (sdDir == null) {
            return "";
        } else {
            return sdDir.toString();
        }
    }
}