package com.xinzhen.xznongshang.module.main.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActNoNetworkBinding;
import com.xinzhen.xznongshang.module.main.viewControl.XZNoNetwordCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;

/**
 * Author: huangruidong
 * <p>
 * <p/>
 * Description: 断网页面
 */
@Route(path = RouterUrl.XZ_NO_NETWORK, extras = RouterExtras.EXTRA_COMMON)
public class XZNoNetworkAct extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActNoNetworkBinding binding = DataBindingUtil.setContentView(this, R.layout.act_no_network);
        binding.setViewCtrl(new XZNoNetwordCtrl());
    }
}