package com.xinzhen.xznongshang.module.user.dataModel.submit;

import com.xinzhen.xznongshang.network.RequestParams;
import com.xz.wireless.network.annotation.SerializedEncryption;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 19:09
 * <p/>
 * Description: 重置密码需要提交的数据
 */
@Setter
@Getter
public class ResetPwdSub {
    /**
     * 手机号
     */
    @SerializedName(RequestParams.PATH_WAY)
    private String phone;
    /**
     * 新密码
     */
    @SerializedEncryption
    @SerializedName(RequestParams.PASSWORD)
    private String pwd;
    /**
     * 确认密码
     */
    @SerializedEncryption
    @SerializedName("confirmPassword")
    private String confirmPwd;
    /**
     * 用户标识
     */
    private String __sid;

    public ResetPwdSub(String phone, String sid) {
        this.phone = phone;
        this.__sid = sid;
    }

}
