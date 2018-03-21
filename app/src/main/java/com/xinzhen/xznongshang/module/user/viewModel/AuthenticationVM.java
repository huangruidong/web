package com.xinzhen.xznongshang.module.user.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;


/**
 * Created by liufang on 2017/11/6.
 */

public class AuthenticationVM extends BaseObservable {
    private String realName;//真实姓名
    private String idNumber;//身份证号
    private boolean enable;//提交按钮是否可用

    @Bindable
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
        checkInput();
        notifyPropertyChanged(BR.realName);
    }

    @Bindable
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        checkInput();
        notifyPropertyChanged(BR.idNumber);
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
        if (!TextUtils.isEmpty(realName) && !TextUtils.isEmpty(idNumber)) {
            setEnable(true);
        } else {
            setEnable(false);
        }
    }
}
