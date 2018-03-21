package com.xinzhen.xznongshang.module.main.viewMode;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.module.main.viewControl.ModifyPasswordCtrl;


/**
 * Author: TinhoXu
 *
 * Date: 2016/11/29 17:26
 * <p/>
 * Description: 修改登录密码模型({@link ModifyPasswordCtrl})
 */
public class ModifyPasswordVM extends BaseObservable {
    /** 原密码 */
    private String  pwdOld;
    /** 新密码 */
    private String  pwdNew;
    /** 确认密码 */
    private String  pwdConfirm;
    /** 按钮是否可用 */
    private boolean enable;

    @Bindable
    public String getPwdOld() {
        return pwdOld;
    }

    public void setPwdOld(String pwdOld) {
        this.pwdOld = pwdOld;
        checkInput();
        notifyPropertyChanged(BR.pwdOld);
    }

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

    /**
     * 输入校验
     */
    private void checkInput() {
        if (!TextUtils.isEmpty(pwdOld) && !TextUtils.isEmpty(pwdNew) && !TextUtils.isEmpty(pwdConfirm)) {
            setEnable(true);
        } else {
            setEnable(false);
        }
    }
}
