package com.xinzhen.xznongshang.module.main.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActMineInviteBinding;
import com.xinzhen.xznongshang.module.main.viewControl.InviteCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/18 10:06
 * <p/>
 * Description: 邀请好友
 */
@Route(path = RouterUrl.MINE_INVITE, extras = RouterExtras.EXTRA_LOGIN)
public class InviteAct extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActMineInviteBinding binding = DataBindingUtil.setContentView(this, R.layout.act_mine_invite);
        binding.setViewCtrl(new InviteCtrl(binding, this));
    }
}
