package com.xinzhen.xznongshang.module.user.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActUserLoginBinding;
import com.xinzhen.xznongshang.module.user.viewControl.LoginCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;
import com.xz.wireless.tools.log.Logger;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/16 16:16
 * <p/>
 * Description: 登录页面
 */
@Route(path = RouterUrl.USER_LOGIN, extras = RouterExtras.EXTRA_COMMON)
public class LoginAct extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("Main", "LoginAct");
        ActUserLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.act_user_login);
        binding.setViewCtrl(new LoginCtrl(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    /**
     * 注册按钮
     */
    public void registerClick(View view) {
        startActivity(new Intent(this, RegisterAct.class));
    }
}
