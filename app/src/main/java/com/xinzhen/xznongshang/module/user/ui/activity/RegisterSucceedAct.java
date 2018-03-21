package com.xinzhen.xznongshang.module.user.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActUserRegisterSucceedBinding;
import com.xinzhen.xznongshang.module.user.viewControl.RegisterSucceedCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 17:34
 * <p/>
 * Description: 注册成功
 */
@Route(path = RouterUrl.USER_REGISTER_SUCCEED, extras = RouterExtras.EXTRA_LOGIN)
public class RegisterSucceedAct extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActUserRegisterSucceedBinding binding = DataBindingUtil.setContentView(this, R.layout.act_user_register_succeed);
        binding.setViewCtrl(new RegisterSucceedCtrl(binding));
    }

    @Override
    public void onBackPressed() {
    }
}
