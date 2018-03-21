package com.xinzhen.xznongshang.module.main.viewControl;

import android.view.View;

import com.xinzhen.xznongshang.module.main.viewMode.ModifyNicknameVM;
import com.xz.wireless.tools.utils.ToastUtil;

/**
 * Created by sense on 2018/3/20.
 */

public class ModifyNicknameCtrl {
    private ModifyNicknameVM nicknameVM;

    public ModifyNicknameCtrl() {
        init();
    }

    private void init() {
        nicknameVM = new ModifyNicknameVM();
    }

    public void modifyNicknameClick(View view) {
        ToastUtil.toast("保存");
    }

    public ModifyNicknameVM getNicknameVM() {
        return nicknameVM;
    }
}
