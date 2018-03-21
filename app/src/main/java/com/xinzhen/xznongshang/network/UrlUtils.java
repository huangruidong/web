package com.xinzhen.xznongshang.network;

import android.text.TextUtils;

import com.xinzhen.xznongshang.common.AppConfig;
import com.xinzhen.xznongshang.common.BaseParams;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.module.user.dataModel.receive.OauthTokenMo;
import com.xinzhen.xznongshang.utils.statistics.DeviceInfoUtils;
import com.xz.wireless.tools.gesture.logic.SharedInfo;

import com.xz.wireless.tools.encryption.MDUtil;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.ContextHolder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Author: TinhoXu
 *
 * Date: 2016/12/20 15:04
 * <p/>
 * Description:
 */
public class UrlUtils {
    /** 通用参数 */
    private TreeMap<String, String> commonParamsTreeMap;

    private UrlUtils() {
        commonParamsTreeMap = new TreeMap<>();
        commonParamsTreeMap.put(Constant.APP_KEY, AppConfig.APP_KEY);
        commonParamsTreeMap.put(Constant.MOBILE_TYPE, BaseParams.MOBILE_TYPE);
        commonParamsTreeMap.put(Constant.VERSION_NUMBER, DeviceInfoUtils.getVersionName(ContextHolder.getContext()));
    }

    public static UrlUtils getInstance() {
        return NetworkUtilsInstance.instance;
    }

    private static class NetworkUtilsInstance {
        static UrlUtils instance = new UrlUtils();
    }

    /**
     * 对字符串进行签名
     */
    public String signParams(String postBodyString) {
        TreeMap<String, String> treeMap = splitPostString(postBodyString);
        treeMap = dynamicParams(treeMap);
        String sign = getSign(treeMap);
        treeMap.put(Constant.SIGNA, sign);
        return getPostParamsStr(treeMap);
    }

    /**
     * 对Map数据进行签名
     */
    public String signParams(TreeMap<String, String> treeMap) {
        treeMap.putAll(commonParamsTreeMap);
        treeMap = dynamicParams(treeMap);
        String sign = getSign(treeMap);
        treeMap.put(Constant.SIGNA, sign);
        return getPostParamsStr(treeMap);
    }

    /**
     * 分割请求参数，放入treeMap中,拼接动态参数
     *
     * @param postBodyString
     *         请求参数
     */
    private TreeMap<String, String> splitPostString(String postBodyString) {
        TreeMap<String, String> map = new TreeMap<>();
        for (String s : postBodyString.split("&")) {
            String[] keyValue = s.split("=");
            map.put(keyValue[0], keyValue.length > 1 ? keyValue[1] : "");
        }
        return map;
    }

    /**
     * 动态拼接请求参数
     */
    private TreeMap<String, String> dynamicParams(TreeMap<String, String> map) {
        String ts = String.valueOf(System.currentTimeMillis() / 1000);
        map.put(Constant.TS, ts);
        String token  = getToken();
        String userId = getUserId();
        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(userId)) {
            map.put(Constant.TOKEN, token);
            map.put(Constant.USER_ID, userId);
        }
        map.put("__cookie", "true");


        return map;
    }

    /**
     * 一般接口调用-signa签名生成规则
     *
     * @param map
     *         有序请求参数map
     */
    private String getSign(TreeMap map) {
        String signa = "";
        try {
            Iterator      it = map.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (null != entry.getValue()) {
                    sb.append(entry.getKey()).append("=").append(URLDecoder.decode(entry.getValue().toString(), "utf-8"));
                }
            }
            // 所有请求参数排序后的字符串后进行MD5（32）
            signa = MDUtil.encode(MDUtil.TYPE.MD5, sb.toString());
            // 得到的MD5串拼接appsecret再次MD5，所得结果转大写
            signa = MDUtil.encode(MDUtil.TYPE.MD5, signa + AppConfig.APP_SECRET).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            Logger.d("logger","error",e);
        }
        return signa;
    }

    /**
     * 将map拼装成请求字符串
     *
     * @return 返回请求参数
     */
    private String getPostParamsStr(TreeMap map) {
        Iterator      it = map.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        try {
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (null != entry.getValue()) {
                    sb.append(entry.getKey()).append("=").append(URLDecoder.decode(entry.getValue().toString(), "utf-8")).append("&");
                }
            }
        } catch (UnsupportedEncodingException e) {
            Logger.d("logger","error",e);
        }
        if (sb.toString().length() > 1) {
            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }

    /**
     * 获取oauthToken
     */
    private String getToken() {
        OauthTokenMo mo = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
        if (mo != null) {
            return mo.get__sid();
        }
        return "";
    }

    /**
     * 获取userId
     */
    private String getUserId() {
        OauthTokenMo mo = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
        if (mo != null) {
            return mo.getUserId();
        }
        return "";
    }
}
