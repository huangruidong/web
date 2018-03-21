package com.xinzhen.xznongshang.network;

/**
 * Author: TinhoXu
 *
 * Date: 2016/10/31 13:53
 * <p/>
 * Description:
 */
public class AppResultCode {
    /** 常规错误 - Toast提示 */
    public static final int ERROR                  = 0x0000;
    /** 密码错误，提示重新输入或者找回密码 */
    public static final int ERROR_PASSWORD         = 0x0001;
    /** 手机号已经注册，提示重新输入或者立即登录 */
    public static final int ERROR_ACCOUNT_EXIT     = 0x0002;
    /** 登录时使用 -- 账户不存在，请重新输入或注册新账号 */
    public static final int ERROR_ACCOUNT_NOT_EXIT = 0x0003;
    /** 初始化错误号 */
    public static final int BACK_ERROR             = 0x0004;
    /** 账户被锁定 需要退出 */
    public static final int USER_LOCK              = 0x5001;
    /** 充值功能被冻结 */
    public static final int USER_FREEZE_RECHARGE   = 0x5002;
    /** 提现功能被冻结 */
    public static final int USER_FREEZE_CASH       = 0x5003;
    /** 投资功能被冻结 */
    public static final int USER_FREEZE_INVEST     = 0x5004;
    /** 变现能被冻结 */
    public static final int USER_FREEZE_REALIZE    = 0x5005;
    /** 债权功能被冻结 */
    public static final int USER_FREEZE_BOND       = 0x5006;
    /** 借款功能被冻结 */
    public static final int USER_FREEZE_LOAN       = 0x5007;
    /** 开通托管账户 */
    public static final int USER_PAYMENT           = 0x6001;
    /** 风险评测 */
    public static final int USER_RISK              = 0x6002;
    /** 绑定邮箱 */
    public static final int USER_EMAIL             = 0x6003;
    /** 提示更新 */
    public static final int UPDATE_NORMAL          = 0x7001;
    /** 强制更新 */
    public static final int UPDATE_FORCED          = 0x7002;
    /** Token过期 - APP调用RefreshToken接口 */
    public static final int TOKEN_TIMEOUT          = 0x8000;
    /** RefreshToken过期 - APP提示需要登录，跳转到登录页面 */
    public static final int TOKEN_REFRESH_TIMEOUT  = 0x8001;
    /** Token不唯一 - APP提示被顶号，跳转到登录页面 */
    public static final int TOKEN_NOT_UNIQUE       = 0x8002;
    /** Token不存在 - APP提示需要登录，跳转到登录页面 */
    public static final int TOKEN_NOT_EXIT         = 0x8003;
    /** 成功 */
    public static final int SUCCESS                = 0x9999;
}
