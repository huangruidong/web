package com.xinzhen.xznongshang.module.user.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActUserResetPwdBinding;
import com.xinzhen.xznongshang.module.user.viewControl.ResetPwdCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 18:48
 * <p/>
 * Description: 重置密码
 */
@Route(path = RouterUrl.USER_RESET_PASSWORD, extras = RouterExtras.EXTRA_COMMON)
public class ResetPwdAct extends BaseAct {
    String id;
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActUserResetPwdBinding binding = DataBindingUtil.setContentView(this, R.layout.act_user_reset_pwd);
        binding.setViewCtrl(new ResetPwdCtrl(id, sid));
    }
}
