package com.xinzhen.xznongshang.module.user.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActUserForgotBinding;
import com.xinzhen.xznongshang.module.user.viewControl.ForgotCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 13:57
 * <p/>
 * Description: 忘记密码
 */
@Route(path = RouterUrl.USER_FORGOT_PASSWORD, extras = RouterExtras.EXTRA_COMMON)
public class ForgotAct extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActUserForgotBinding binding = DataBindingUtil.setContentView(this, R.layout.act_user_forgot);
        binding.setViewCtrl(new ForgotCtrl(binding.timeButton));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            this.finish();
        }
    }
}
