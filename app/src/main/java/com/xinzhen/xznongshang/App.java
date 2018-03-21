package com.xinzhen.xznongshang;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xinzhen.xznongshang.common.BaseParams;
import com.xinzhen.xznongshang.common.Constant;
import com.xz.wireless.tools.Config;
import com.xz.wireless.tools.gesture.logic.SharedInfo;

/**
 * Created by sense on 2018/3/7.
 */

public class App extends MultiDexApplication {

    private static Context mCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mCtx = getApplicationContext();
        init();
    }

    private void init() {
        SharedInfo.init(Constant.SP_NAME);
        // 打印日志
        ARouter.openLog();
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openDebug();
        ARouter.init(this);
        Config.DEBUG.set(BaseParams.IS_DEBUG);
        Config.ROOT_PATH.set(BaseParams.ROOT_PATH);
    }

    public static Context getCtx() {
        return mCtx;
    }

}
