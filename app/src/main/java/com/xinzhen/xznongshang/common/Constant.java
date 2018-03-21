package com.xinzhen.xznongshang.common;

import android.Manifest;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/2/18 16:23
 * <p/>
 * Description: 常量类
 */
public class Constant {
    /**
     * number
     */
    public static final int NUMBER_0 = 0;
    public static final int NUMBER_1 = 1;
    public static final int NUMBER_2 = 2;
    public static final int NUMBER_3 = 3;
    public static final int NUMBER_4 = 4;
    public static final int NUMBER_5 = 5;
    public static final int NUMBER_6 = 6;
    public static final int NUMBER_7 = 7;
    /**
     * status
     */
    public static final String STATUS__1 = "-1";
    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";
    public static final String STATUS_2 = "2";
    public static final String STATUS_3 = "3";
    public static final String STATUS_4 = "4";
    public static final String STATUS_5 = "5";
    public static final String STATUS_6 = "6";
    public static final String STATUS_7 = "7";
    public static final String STATUS_8 = "8";
    public static final String STATUS_9 = "9";
    public static final String STATUS_10 = "10";
    public static final String STATUS_11 = "11";
    public static final String STATUS_12 = "12";
    public static final String STATUS_13 = "13";
    public static final String STATUS_14 = "14";
    public static final String STATUS_15 = "15";
    //验证交易密码回调
    public static final int REQUEST_TRANSACTIONPASSWORD = 10001;
    //设置交易密码回调
    public static final int REQUEST_SETPASSWORD = 10002;

    /**
     * 符号
     */
    public static final String SYMBOL_PLUS = "+";
    public static final String SYMBOL_MINUS = "-";
    /**
     * network params
     */
    // 公共参数
    public static final String APP_KEY = "appkey";
    public static final String SIGNA = "signa";
    public static final String TS = "ts";
    public static final String MOBILE_TYPE = "mobileType";
    public static final String VERSION_NUMBER = "versionNumber";
    // SP 字段
    public static final String SP_NAME = "hujin_android";
    public static final String USER_PHONE = "userPhone";
    public static final String IS_LAND = "isLand";
    public static final String IS_FIRST_IN = "isFirstIn";
    public static final String FROM_INVEST = "fromInvest";
    // SP 产品名称和金额
    public static final String PRODUCT_NAME = "productName";
    public static final String PRODUCT_AMOUNT = "productAmount";
    // 登录参数
    public static final String TOKEN = "__sid";
    public static final String USER_ID = "userId";
    ///////////////////////////////////////////////////////////////////////////
    // 刷新
    ///////////////////////////////////////////////////////////////////////////
    // 主页到登录页面
    public static final String MAIN_TO_LOGIN = "mainToLogin";
    // 登录页面到主页
    public static final String LOGIN_TO_MAIN = "loginToMain";
    // 可变现页面刷新
    public static final String CASH_REALIZABLE = "cashRealizable";
    // 变现中页面刷新
    public static final String CASH_REALIZATION = "cashRealization";
    // 可转让页面刷新
    public static final String CAN_TRANSFER = "canTransfer";
    // 转让中页面刷新
    public static final String TRANSFER_IN = "transferIn";
    // 已转让页面刷新
    public static final String TRANSFERRED = "transferred";
    // 已受让中页面刷新
    public static final String TRANSFEREE = "transferee";
    // 已受让中页面 - 去支付
    public static final String TRANSFEREE_PAY = "transferee_pay";
    // 待还页面刷新
    public static final String P2P_REPAYING = "repaying";
    // 已还页面刷新
    public static final String P2P_REPAYED = "repayed";
    // 投资申请页面刷新
    public static final String P2P_APPLY_PAY = "p2pApplyPay";
    // 后台新增的用户，第一次登录，提示修改密码
    public static final String MODIFY_LOGIN_PWD = "modifyLoginPwd";
    //是否绑定银行卡
    public static final String IS_BINDING_CARD = "isBindingCard";
    public static final String IS_REAL_NAME = "isRealname";
    public static final String IS_SET_TRADE_PWD = "isSetTradePwd";
    //支付
    /***充值****/
    public static final int REQUEST_CODE_RECHARGE = 0x103;
    /****提现****/
    public static final int REQUEST_CODE_WITHDRAW = 0x104;
    /**
     * 债转投标
     */
    public static final int REQUEST_CODE_BOND = 0x106;
    /**
     * 普通投标
     */
    public static final int REQUEST_CODE_INVEST = 0x105;
    /**
     * 变现投标
     */
    public static final int REQUEST_CODE_REALIZE = 0x107;
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
    };
    //信真宝项目ID
    public static String goldFundProjectId;

    public static final String[] TEXT_ARRAY_GOLD = {"信真宝", "定期投资", "债权转让", "变现", "我的借款", "百宝箱", "自动投标"};
    public static boolean isGold = false;
    public static String authenticationUrl;
    public static String cardFrontAddress;//身份证正面本地路径
    public static String cardBackAddress;//身份证反面本地路径
    public static String cardFrontPath;//身份证正面服务器路径
    public static String cardBackPath;//身份证反面服务器路径
    public static final String CARD_FRONT_FILE_PATH = "/sdcard/cardFront.jpg";
    public static final String CARD_BACK_FILE_PATH = "/sdcard/cardBack.jpg";
    public static final String AUTHENTICATION_BASE_URL = "/static/uploading/image" + "?type=card";

    public static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();


}
