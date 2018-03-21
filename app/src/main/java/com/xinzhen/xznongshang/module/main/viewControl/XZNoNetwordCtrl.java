package com.xinzhen.xznongshang.module.main.viewControl;

import android.view.View;

import com.xinzhen.xznongshang.common.BaseParams;
import com.xz.wireless.tools.utils.AndroidUtil;

public class XZNoNetwordCtrl {

    public XZNoNetwordCtrl(){
    }
    /*
     *刷新重试
     */
    public void submitAuthentication(View view) {
        if (BaseParams.isNetwork){
            AndroidUtil.getActivity(view).finish();
        }
    }
}
