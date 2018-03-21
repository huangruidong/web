package com.xinzhen.xznongshang.module.user.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;


/**
 * Created by liufang on 2017/11/7.
 */

public class ModifyDealPasswordVM extends BaseObservable {
    private String dealPassword;//新的交易密码
    private String messageCode;//短信验证码
    /*** 获取验证码按钮是否可用*/
    private boolean codeEnable;
    /*** 按钮是否可用*/
    private boolean enable;

    @Bindable
    public String getDealPassword() {
        return dealPassword;
    }

    public void setDealPassword(String dealPassword) {
        this.dealPassword = dealPassword;
        checkInput();
        notifyPropertyChanged(BR.dealPassword);
    }

    @Bindable
    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
        checkInput();
        notifyPropertyChanged(BR.messageCode);
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

    /*
    *
     */

    /*
    *输入检验
     */
    private void checkInput() {
        if (!TextUtils.isEmpty(dealPassword) && !TextUtils.isEmpty(messageCode)) {
            setEnable(true);
        } else {
            setEnable(false);
        }

    }

}
