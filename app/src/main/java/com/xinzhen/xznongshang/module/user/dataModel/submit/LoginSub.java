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
 * Description: 登录需要提交的数据
 */
@Setter
@Getter
public class LoginSub {
    /**
     * 手机号
     */
    @SerializedName("loginName")
    private String id;
    /**
     * 登录密码
     */
    @SerializedName("loginPwd")
    @SerializedEncryption()
    private String pwd;

    public LoginSub(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }


}
