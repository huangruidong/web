package com.xinzhen.xznongshang.module.user.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.module.user.viewControl.ForgotCtrl;


/**
 * Author: TinhoXu
 *
 * Date: 2016/11/17 17:25
 * <p/>
 * Description: 忘记密码页面模型({@link ForgotCtrl})
 */
public class ForgotVM extends BaseObservable {
    /** 手机号 */
    private String  phone;
    /** 验证码 */
    private String  code;
    /** 获取验证码按钮是否可用 */
    private boolean codeEnable;
    /** 按钮是否可用 */
    private boolean enable;

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        checkInput();
        codeEnableCheck();
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        checkInput();
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public boolean isCodeEnable() {
        return codeEnable;
    }

    public void setCodeEnable(boolean codeEnable) {
        this.codeEnable = codeEnable;
        notifyPropertyChanged(BR.codeEnable);
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
     * TimeButton是否可用
     */
    private void codeEnableCheck() {
        if (!TextUtils.isEmpty(phone)) {
            setCodeEnable(true);
        } else {
            setCodeEnable(false);
        }
    }

    /**
     * 输入校验
     */
    private void checkInput() {
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
            setEnable(true);
        } else {
            setEnable(false);
        }
    }
}
