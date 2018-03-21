package com.xinzhen.xznongshang.module.main.frag;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseLazyLoadFragment;
import com.xinzhen.xznongshang.common.BundleKeys;
import com.xinzhen.xznongshang.databinding.FragMineModifyPhoneNextBinding;
import com.xinzhen.xznongshang.module.main.viewControl.ModifyPhoneNextCtrl;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/24 14:15
 * <p/>
 * Description: 修改绑定手机号 - 下一步
 */
public class ModifyPhoneNextFrag extends BaseLazyLoadFragment {
    public static ModifyPhoneNextFrag newInstance(String param) {
        ModifyPhoneNextFrag fragment = new ModifyPhoneNextFrag();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeys.ID, param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragMineModifyPhoneNextBinding binding = DataBindingUtil.inflate(inflater, R.layout.frag_mine_modify_phone_next, container, false);
        final ModifyPhoneNextCtrl nextCtrl = new ModifyPhoneNextCtrl(getArguments().getString(BundleKeys.ID), getFragmentManager(), binding.timeButton);
        binding.setViewCtrl(nextCtrl);
        return binding.getRoot();
    }

    @Override
    public void fetchData() {
    }
}
