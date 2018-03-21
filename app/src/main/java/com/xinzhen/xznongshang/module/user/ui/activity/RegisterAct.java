package com.xinzhen.xznongshang.module.user.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActUserRegisterBinding;
import com.xinzhen.xznongshang.module.user.viewControl.RegisterCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.views.appbar.TitleBar;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/16 16:16
 * <p/>
 * Description: 注册页面
 */
@Route(path = RouterUrl.USER_REGISTER, extras = RouterExtras.EXTRA_COMMON)
public class RegisterAct extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("Main", "registerAct");
        ActUserRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.act_user_register);
        final RegisterCtrl registerCtrl = new RegisterCtrl(binding.timeButton, binding.userRegisterProtocol);
        binding.setViewCtrl(registerCtrl);
        binding.toolBar.addAction(new TitleBar.TextAction(ContextHolder.getContext().getString(R.string.register_login_button)) {
            @Override
            public void performAction(View view) {
                (AndroidUtil.getActivity(view)).onBackPressed();
            }
        });
    }
}
