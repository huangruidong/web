package com.xinzhen.xznongshang.module.user.dataModel.receive;


import lombok.Getter;
import lombok.Setter;

/**
 * Author: chenming
 * <p>
 * Date: 16/3/18 下午3:15
 * <p/>
 * Description: 登录信息
 */
@Setter
@Getter
public class OauthTokenMo {
    /**
     * 头像路径
     */
    private String avatarPhoto;
    /**
     * 用户标识
     */
    private String bindingId;
    /**
     * 有效时长
     */
    private String expiresIn;
    /**
     * 保密用户名
     */
    private String hideUserName;
    /**
     * 刷新token值
     */
    private String refreshToken;
    /**
     * token
     */
    private String __sid;
    /**
     * token类型
     */
    private String tokenType;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 是否修改密码0-否,1-是
     */
    private String pwdResetFlag;
    /**
     * 城市
     */
    private String city;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private String sex;
}
