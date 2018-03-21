package com.xinzhen.xznongshang.module.user.dataModel.receive;

import lombok.Getter;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/12/9 14:12
 * <p/>
 * Description:
 */
@Getter
public class SessionRec {
    /**
     * 用户标识
     */
    private String __sid;
    /**
     * 修改邮箱标识
     */
    private String modifyEmailSign;
    /**
     * 绑定邮箱标识
     */
    private String emailBindToken;
    /**
     * 修改手机标识
     */
    private String modifyPhoneSign;
    /**
     * 绑定手机标识
     */
    private String mobileBindToken;

}
