package com.xinzhen.xznongshang.common;

import com.xinzhen.xznongshang.BuildConfig;

/**
 * Author: Hubert
 * <p>
 * Date: 2017/5/15 下午5:14
 * <p/>
 * Description:
 */
public class AppConfig {
    /**
     * 是否使用RAP MOCK服务
     */
    public static final boolean IS_RAP = false;
    /**
     * RAP服务器地址
     */
    private static final String URI_AUTHORITY_RAP = BuildConfig.BASE_URL;
    /**
     * 服务器地址
     */
    public static final String URI_AUTHORITY = BaseParams.IS_DEBUG ? (IS_RAP ? URI_AUTHORITY_RAP : BaseParams.URI_AUTHORITY_BETA) : BaseParams
            .URI_AUTHORITY_RELEASE;
    /**
     * app_key
     */
    public static final String APP_KEY = BuildConfig.APP_KEY;
    /**
     * app_secret
     */
    public static final String APP_SECRET = BuildConfig.APP_SECRET;
    /**
     * 微信分享Key
     **/
    public final static String WX_APP_ID = BuildConfig.WECHAT_KEY;

    /**
     * QQ分享Key
     */
    public static final String QQ_APP_ID = BuildConfig.QQ_KEY;

    /**
     * 账户中心样式
     * 1：九宫格  2：列表
     */
    public static final int ACCOUNT_TYPE = 1;
    public static String productName;
}
