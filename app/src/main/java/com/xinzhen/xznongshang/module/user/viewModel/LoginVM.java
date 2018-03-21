package com.xinzhen.xznongshang.module.user.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.module.user.viewControl.LoginCtrl;


/**
 * Author: TinhoXu
 *
 * Date: 2016/11/16 16:17
 * <p/>
 * Description: 登录页面模型({@link LoginCtrl})
 */
public class LoginVM extends BaseObservable {
    /** 手机号 */
    private String  phone;
    /** 密码 */
    private String  pwd;
    /** 按钮是否可用 */
    private boolean enable;

    public LoginVM() {
        // phone = "15200040084";
        // phone = "15988126416";
        // phone = "15100070003";
        // pwd = "asdf1234";
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        checkInput();
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        checkInput();
        notifyPropertyChanged(BR.pwd);
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
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
            setEnable(false);
        } else {
            setEnable(true);
        }
    }
}
