package com.xinzhen.xznongshang.module.user.dataModel.submit;

import com.xz.wireless.network.annotation.SerializedEncryption;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 16:18
 * <p/>
 * Description: 注册需要提交的数据
 */
@Setter
@Getter
public class RegisterSub {
    /**
     * 手机号
     */
    @SerializedName("mobile")
    private String phone;
    /**
     * 密码
     */
    @SerializedEncryption
    private String pwd;
    /**
     * 邀请人手机号
     */
    private String inviter;
    /**
     * 是否同意协议
     */
    private int agree = 1;

    public RegisterSub(String phone, String pwd, String inviter) {
        this.phone = phone;
        this.pwd = pwd;
        this.inviter = inviter;
    }

}
