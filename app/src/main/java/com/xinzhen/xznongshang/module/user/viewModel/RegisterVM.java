package com.xinzhen.xznongshang.module.user.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.module.user.viewControl.RegisterCtrl;

/**
 * Author: TinhoXu
 *
 * Date: 2016/11/17 14:03
 * <p/>
 * Description:注册页面模型({@link RegisterCtrl})
 */
public class RegisterVM extends BaseObservable {
    /** 手机号 */
    private String    phone;
    /** 验证码 */
    private String    code;
    /** 登录密码 */
    private String    pwd;
    /** 邀请人 */
    private String    invite;
    /** 协议勾选 */
    private boolean   agree;
    /** 注册协议 */
    private Spannable protocol;
    /** 图形验证码 */
    private Drawable  drawable;
    /** 获取验证码按钮是否可用 */
    private boolean   codeEnable;
    /** 按钮是否可用 */
    private boolean   enable;

    public RegisterVM() {
        agree = true;
    }

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
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        checkInput();
        notifyPropertyChanged(BR.pwd);
    }

    @Bindable
    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
        notifyPropertyChanged(BR.invite);
    }

    @Bindable
    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
        notifyPropertyChanged(BR.agree);
    }

    @Bindable
    public Spannable getProtocol() {
        return protocol;
    }

    public void setProtocol(Spannable protocol) {
        this.protocol = protocol;
        notifyPropertyChanged(BR.protocol);
    }

    @Bindable
    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        notifyPropertyChanged(BR.drawable);
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
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(code)) {
            setEnable(true);
        } else {
            setEnable(false);
        }
    }
}
