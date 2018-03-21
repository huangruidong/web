package com.xinzhen.xznongshang.module.main.frag;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseLazyLoadFragment;
import com.xinzhen.xznongshang.databinding.FragMineModifyPhoneConfirmBinding;
import com.xinzhen.xznongshang.module.main.viewControl.ModifyPhoneConfirmCtrl;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/24 14:15
 * <p/>
 * Description:修改绑定手机号 - 确定({@link ModifyPhoneNextCtrl#nextClick(View)})
 */
public class ModifyPhoneConfirmFrag extends BaseLazyLoadFragment {
    public static ModifyPhoneConfirmFrag newInstance(String param) {
        ModifyPhoneConfirmFrag fragment = new ModifyPhoneConfirmFrag();
        Bundle args = new Bundle();
        args.putString("args", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragMineModifyPhoneConfirmBinding binding = DataBindingUtil.inflate(inflater, R.layout.frag_mine_modify_phone_confirm, container, false);
        final ModifyPhoneConfirmCtrl nextCtrl = new ModifyPhoneConfirmCtrl(getArguments().getString("args"), binding.timeButton);
        binding.setViewCtrl(nextCtrl);
        return binding.getRoot();
    }

    @Override
    public void fetchData() {
    }
}
