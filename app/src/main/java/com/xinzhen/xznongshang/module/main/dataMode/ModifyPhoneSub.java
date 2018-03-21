package com.xinzhen.xznongshang.module.main.dataMode;

import com.google.gson.annotations.SerializedName;
import com.xinzhen.xznongshang.network.RequestParams;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/12/9 16:20
 * <p/>
 * Description: 修改绑定手机提交数据
 */
public class ModifyPhoneSub {
    /**
     * 手机号
     */
    @SerializedName(RequestParams.MOBILE)
    private String phone;
    /**
     * 验证码
     */
    private String code;
    /**
     * 操作类型：0-绑定，1-修改;
     */
    private String modifyType;
    /**
     * 绑定手机标识
     */
    private String mobileBindToken;
    /**
     * 绑定手机标识
     */
    private String modifyPhoneSign;

    public ModifyPhoneSub(String modifyType) {
        this.modifyType = modifyType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }

    public String getMobileBindToken() {
        return mobileBindToken;
    }

    public void setMobileBindToken(String mobileBindToken) {
        this.mobileBindToken = mobileBindToken;
    }

    public String getModifyPhoneSign() {
        return modifyPhoneSign;
    }

    public void setModifyPhoneSign(String modifyPhoneSign) {
        this.modifyPhoneSign = modifyPhoneSign;
    }
}
