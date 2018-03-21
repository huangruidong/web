package com.xinzhen.xznongshang.module.user.viewControl;

import android.app.Activity;
import android.view.View;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.databinding.ActUserRegisterSucceedBinding;
import com.xinzhen.xznongshang.module.user.ui.activity.RegisterSucceedAct;
import com.xz.wireless.tools.utils.AndroidUtil;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 17:35
 * <p/>
 * Description: 注册成功页面控制器({@link RegisterSucceedAct})
 */
public class RegisterSucceedCtrl {
    public RegisterSucceedCtrl(ActUserRegisterSucceedBinding binding) {
        binding.toolBar.setLeftListener(null);
    }

    /**
     * 立即开通点击
     */
    public void openClick(View view) {
        Activity activity = AndroidUtil.getActivity(view);
        /*ARouter.getInstance().build(RouterUrl.USER_AUTHENTICATION)
                .withBoolean(BundleKeys.REALNAMEPROCESS, true)
                .navigation();*/
        activity.finish();

    }

    /**
     * 先去逛逛点击
     */
    public void visitClick(View view) {
        /*Activity activity = AndroidUtil.getActivity(view);
        ARouter.getInstance().build(RouterUrl.XZHOMEPAGE).withInt(BundleKeys.TYPE, Constant.NUMBER_0).navigation();
        activity.finish();*/
    }
}
