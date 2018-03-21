package com.xinzhen.xznongshang.module.main.viewMode;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.xinzhen.xznongshang.BR;

/**
 * Created by sense on 2018/3/20.
 */

public class ModifyNicknameVM extends BaseObservable {
    private String nickname;

    @Bindable
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        notifyPropertyChanged(BR.pwdNew);
    }
}
