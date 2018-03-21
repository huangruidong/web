package com.xinzhen.xznongshang.module.main.dataMode;

import lombok.Getter;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/23 14:52
 * <p/>
 * Description: 用户基础信息
 */
@Getter
public class BasicInfoRec {
    /**
     * 业务授权
     */
    private String authorizated;
    /**
     * 头像路径
     */
    private String avatarPhoto;
    /**
     * 银行卡张数
     */
    private String bankNum;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱认证 0:未认证,1:已认证
     */
    private String emailStatus;
    /**
     * 是否设置交易密码0否,1是
     */
    private String hasSetPayPwd;
    /**
     * 显示用的名称
     */
    private String hideUserName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 手机认证 -1:未通过,0:未认证,1:已认证
     */
    private String mobilePhoneStatus;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 实名认证 -1:未通过,0:未认证,1:已认证
     */
    private String realnameStatus;
    /**
     * 风险评估等级(数字等级)
     */
    private String riskLevel;
    /**
     * 风险评估等级(等级说明)
     */
    private String riskLevelStr;
    /**
     * 用户资金托管平台账户号
     */
    private String tppUserAccId;
    /**
     * VIP等级
     */
    private String vipLevel;
    /**
     * 密保问题是否已设置1 已设置密保，0 未设置
     */
    private String securityFlag;
}
