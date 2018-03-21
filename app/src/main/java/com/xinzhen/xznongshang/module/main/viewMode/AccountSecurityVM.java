package com.xinzhen.xznongshang.module.main.viewMode;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.common.FunctionConfig;
import com.xinzhen.xznongshang.module.main.viewControl.AccountSecurityCtrl;
import com.xz.wireless.tools.utils.StringFormat;
import com.xz.wireless.tools.utils.TextUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/22 15:56
 * <p/>
 * Description: 账户与安全模型({@link AccountSecurityCtrl})
 */
public class AccountSecurityVM extends BaseObservable {
    /**
     * 我的头像路径
     */
    private String avatarPath;
    /**
     * 绑定手机
     */
    private String phone;

    @Bindable
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        notifyPropertyChanged(BR.dealCodeClick);
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }

    @Bindable
    public String getModifyPassword() {
        return modifyPassword;
    }

    public void setModifyPassword(String modifyPassword) {
        this.modifyPassword = modifyPassword;
        notifyPropertyChanged(BR.modifyPassword);
    }

    private String nickName;
    private String sex;
    private String city;
    private String modifyPassword;

    private boolean dealCodeClick;


    @Bindable
    public boolean isDealCodeClick() {
        return dealCodeClick;
    }

    public void setDealCodeClick(boolean dealCodeClick) {
        this.dealCodeClick = dealCodeClick;
        notifyPropertyChanged(BR.dealCodeClick);
    }


    @Bindable
    public String getAvatarPath() {
        if (!TextUtil.isEmpty(avatarPath)) {
            try {
                return URLDecoder.decode(avatarPath, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        notifyPropertyChanged(BR.avatarPath);
    }


    @Bindable
    public String getPhone() {
        return StringFormat.phoneHideFormat(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    /**
     * 业务授权是否打开
     */
    @Bindable
    public boolean isAuthEnable() {
        return FunctionConfig.getInstance().isFunctionBusinessAuthorizeEnable();
    }
}
