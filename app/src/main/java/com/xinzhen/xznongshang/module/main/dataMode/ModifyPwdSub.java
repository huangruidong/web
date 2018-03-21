package com.xinzhen.xznongshang.module.main.dataMode;

import com.xz.wireless.network.annotation.SerializedEncryption;
import com.google.gson.annotations.SerializedName;

/**
 * Author: TinhoXu
 *
 * Date: 2016/11/29 17:58
 * <p/>
 * Description: 修改登录密码提交数据
 */
public class ModifyPwdSub {
    /** 老密码 */
    @SerializedEncryption
    @SerializedName("loginPwd")
    private String oldPwd;
    /** 新密码 */
    @SerializedEncryption
    @SerializedName("newLoginPwd")
    private String newPwd;
    /** 确认密码 */
    @SerializedEncryption
    @SerializedName("confirmPwd")
    private String confirmPwd;

    public ModifyPwdSub(String oldPwd, String newPwd, String confirmPwd) {
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.confirmPwd = confirmPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
}
