package com.xinzhen.xznongshang.module.user.dataModel.submit;

import com.xz.wireless.network.annotation.SerializedEncryption;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 18:43
 * <p/>
 * Description: 忘记密码  重置密码需要提交的数据
 */
@Getter
@Setter
public class ForgotSub {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    @SerializedEncryption
    private String pwd;
    /**
     * 验证码
     */
    private String code;

    public ForgotSub(String phone, String pwd, String code) {
        this.phone = phone;
        this.pwd = pwd;
        this.code = code;
    }
}
