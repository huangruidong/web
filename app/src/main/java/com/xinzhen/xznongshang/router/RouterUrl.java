package com.xinzhen.xznongshang.router;


import com.xinzhen.xznongshang.BuildConfig;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2017/6/1 18:00
 * <p/>
 * Description:
 */
public class RouterUrl {
    public static final String XZHOMEPAGE = BuildConfig.HOMEPAGE;
    private static final String SCHEME = "/thor";
    /**
     * 自定义全局降级策略
     */
    public static final String SERVICE_DEGRADE = SCHEME + "/service/degrade";
    /**
     * 重写跳转URL
     */
    public static final String SERVICE_PATH_REPLACE = SCHEME + "/service/pathReplace";
    /**
     * 传递自定义对象
     */
    public static final String SERVICE_JSON = SCHEME + "/service/json";
    /**
     * 主页
     */
    public static final String MAIN = SCHEME + "/main";
    public static final String MAINDEMO = SCHEME + "/mainDemo";
    public static final String DEMO_HOME_PAGE = SCHEME + "/mainDemo2";

    ///////////////////////////////////////////////////////////////////////////
    // COMMON
    ///////////////////////////////////////////////////////////////////////////
    /**
     * 启动页
     */
    public static final String COMMON_SPLASH = SCHEME + "/common/splash";
    /**
     * 引导页
     */
    public static final String COMMON_GUIDE = SCHEME + "/common/guide";
    /**
     * H5页面
     */
    public static final String COMMON_WEB_VIEW = SCHEME + "/common/webView";
    /**
     * 第三方WebView 页面
     */
    public static final String COMMON_THIRD_WEB_VIEW = SCHEME + "/common/thirdWebView";
    /**
     * 三方操作结果页面
     */
    public static final String COMMON_THIRD_PAYMENT_RESULT = SCHEME + "/common/thirdPaymentResult";
    /**
     * 设置手势密码
     */
    public static final String COMMON_GESTURE_LOCK = SCHEME + "/common/gestureLock";
    /**
     * 解锁手势密码 - 声明
     */
    public static final String COMMON_GESTURE_UNLOCK = SCHEME + "/common/gestureUnlock";
    ///////////////////////////////////////////////////////////////////////////
    // USER
    ///////////////////////////////////////////////////////////////////////////
    /**
     * 登录
     */
    public static final String USER_LOGIN = SCHEME + "/user/login";
    /**
     * 注册
     */
    public static final String USER_REGISTER = SCHEME + "/user/register";
    /**
     * 注册成功
     */
    public static final String USER_REGISTER_SUCCEED = SCHEME + "/user/registerSucceed";
    /**
     * 忘记密码
     */
    public static final String USER_FORGOT_PASSWORD = SCHEME + "/user/forgotPassword";
    /**
     * 重置密码
     */
    public static final String USER_RESET_PASSWORD = SCHEME + "/user/resetPassword";
    /**
     * 修改密码
     */
    public static final String USER_MODIFY_PASSWORD = SCHEME + "/user/modifyPassword";
    /**
     * 邀请好友
     */
    public static final String MINE_INVITE = SCHEME + "/mine/invite";
    /**
     * 我的邀请(人脉)
     */
    public static final String MINE_INVITED = SCHEME + "/mine/invited";
    /**
     * 邀请奖励规则
     */
    public static final String MINE_INVITE_RULE = SCHEME + "/mine/inviteRule";
    /**
     * 账户与安全
     */
    public static final String MINE_ACCOUNT_SECURITY = SCHEME + "/mine/accountSecurity";
    /**
     * 修改绑定手机号
     */
    public static final String MINE_MODIFY_PHONE = SCHEME + "/mine/modifyPhone";
    /**
     * 密码管理
     */
    public static final String MINE_PWD_MANAGE = SCHEME + "/mine/pwdManage";
    /**
     * 修改昵称
     */
    public static final String MINE_MODIFY_NICKNAME = SCHEME + "/mine/pwdManage";
    /**
     * 断网页面
     */
    public static final String XZ_NO_NETWORK = SCHEME + "/user/XZNoNetwork";
}
