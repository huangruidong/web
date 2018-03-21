package com.xinzhen.xznongshang.module.main.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.common.BundleKeys;
import com.xinzhen.xznongshang.module.main.frag.ModifyPhoneNextFrag;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/24 11:37
 * <p/>
 * Description: 修改绑定手机号
 */
@Route(path = RouterUrl.MINE_MODIFY_PHONE, extras = RouterExtras.EXTRA_LOGIN)
public class ModifyPhoneAct extends BaseAct {
    @Autowired(name = BundleKeys.ID)
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.act_mine_binding_phone);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, ModifyPhoneNextFrag.newInstance(id)).commit();
    }
}
