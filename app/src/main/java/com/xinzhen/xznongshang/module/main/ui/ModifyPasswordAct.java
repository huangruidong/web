package com.xinzhen.xznongshang.module.main.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActMineModifyPasswordBinding;
import com.xinzhen.xznongshang.module.main.viewControl.ModifyPasswordCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/29 15:08
 * <p/>
 * Description: 修改登录密码
 */
@Route(path = RouterUrl.USER_MODIFY_PASSWORD, extras = RouterExtras.EXTRA_LOGIN)
public class ModifyPasswordAct extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActMineModifyPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.act_mine_modify_password);
        binding.setViewCtrl(new ModifyPasswordCtrl());
    }
}
