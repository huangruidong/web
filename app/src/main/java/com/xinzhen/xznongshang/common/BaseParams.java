package com.xinzhen.xznongshang.common;

import android.os.Environment;


import com.xinzhen.xznongshang.BuildConfig;

import java.io.File;

/**
 * Author: TinhoXu
 *
 * Date: 2016/2/23 15:22
 * <p>
 * Description:
 */
public class BaseParams {
    /** 是否是debug模式 */
    public static final  boolean IS_DEBUG = true;
    /** 测试服务器地址 */
    public static final  String  URI_AUTHORITY_BETA = BuildConfig.BASE_URL;
    /** 正式服务器地址 */
    public static final  String  URI_AUTHORITY_RELEASE = BuildConfig.BASE_URL;
    /** 模块名称("接口地址"会拼接在 URL 中最后的"/"之后，故URL必须以"/"结尾) */
    private static final String  URI_PATH              = "/app/";
    /** 请求地址 */
    public static final  String  URI                   = AppConfig.URI_AUTHORITY + (AppConfig.IS_RAP ? "18/app/" : URI_PATH);
    /** ios传“1”，安卓传“2” */
    public static final  String  MOBILE_TYPE           = "2";
    /** 转让专区uuid */
    public static final  String  TRANSFER_ZONE         = "a31bd335e12ac0dced8849a16fd4a894";
    /** 变现uuid */
    public static final  String  REALIZATION           = "090d5d939784fe33aceff143ba1c198c";
    /** 网络是否可用*/
    public static Boolean  isNetwork            = null;
    /**
     * 加密是需要使用的密钥
     * DES加解密时KEY必须是16进制字符串,不可小于8位
     * E.G.    6C4E60E55552386C
     * <p>
     * 3DES加解密时KEY必须是6进制字符串,不可小于24位
     * E.G.    6C4E60E55552386C759569836DC0F83869836DC0F838C0F7
     */
    public static final  String  SECRET_KEY            = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7";
    /** 根路径 */
    public static final  String  ROOT_PATH             = getSDPath() + "/Stanley";
    /** SP文件名 */
    public static final  String  SP_NAME               = "basic_params";
    /** 数据库名称 */
    public static final  String  DATABASE_NAME         = "stanley_db";

    /**
     * 获取SD卡的根目录
     */
    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        if (sdDir == null) {
            return "";
        } else {
            return sdDir.toString();
        }
    }
}
