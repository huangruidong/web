package com.xinzhen.xznongshang.utils;

import android.content.Context;

import com.bigkoo.svprogresshud.SVProgressHUD;


/**
 * Created by liufang on 2017/11/30.
 *
 */

public class ProgressUtil {
    private static SVProgressHUD svProgressHUD;

    private volatile static ProgressUtil instance = null;

    private ProgressUtil() {
    }

    public static ProgressUtil getInstance() {
        if (instance == null) {
            synchronized (ProgressUtil.class) {
                if (instance == null) {
                    instance = new ProgressUtil();
                }
            }
        }
        return instance;
    }

    /*显示*/
    public void show(Context context) {
        dismiss();
        svProgressHUD = new SVProgressHUD(context);
        svProgressHUD.show();
    }

    /* 消失*/
    public void dismiss() {
        if (svProgressHUD != null && svProgressHUD.isShowing()) {
            svProgressHUD.dismiss();
        }
    }
}
