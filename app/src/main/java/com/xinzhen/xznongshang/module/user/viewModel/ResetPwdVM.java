package com.xinzhen.xznongshang.module.user.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.module.user.viewControl.ResetPwdCtrl;

/**
 * Author: TinhoXu
 *
 * Date: 2016/11/17 18:50
 * <p/>
 * Description: 重置密码页面模型({@link ResetPwdCtrl})
 */
public class ResetPwdVM extends BaseObservable {
    /** 新密码 */
    private String  pwdNew;
    /** 确认密码 */
    private String  pwdConfirm;
    /** 按钮是否可用 */
    private boolean enable;

    @Bindable
    public String getPwdNew() {
        return pwdNew;
    }

    public void setPwdNew(String pwdNew) {
        this.pwdNew = pwdNew;
        checkInput();
        notifyPropertyChanged(BR.pwdNew);
    }

    @Bindable
    public String getPwdConfirm() {
        return pwdConfirm;
    }

    public void setPwdConfirm(String pwdConfirm) {
        this.pwdConfirm = pwdConfirm;
        checkInput();
        notifyPropertyChanged(BR.pwdConfirm);
    }

    @Bindable
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        notifyPropertyChanged(BR.enable);
    }

    private void checkInput() {
        if (!TextUtils.isEmpty(pwdNew) && !TextUtils.isEmpty(pwdConfirm)) {
            setEnable(true);
        } else {
            setEnable(false);
        }
    }
}
