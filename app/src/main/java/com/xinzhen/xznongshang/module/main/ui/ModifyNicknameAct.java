package com.xinzhen.xznongshang.module.main.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActMineModifyNicknameBinding;
import com.xinzhen.xznongshang.module.main.viewControl.ModifyNicknameCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;

/**
 * Created by sense on 2018/3/20.
 */
@Route(path = RouterUrl.MINE_MODIFY_NICKNAME, extras = RouterExtras.EXTRA_LOGIN)
public class ModifyNicknameAct extends BaseAct {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActMineModifyNicknameBinding binding = DataBindingUtil.setContentView(this, R.layout.act_mine_modify_nickname);
        binding.setViewCtrl(new ModifyNicknameCtrl());
    }
}
