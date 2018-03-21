package com.xinzhen.xznongshang.network;

import com.xinzhen.xznongshang.common.AppConfig;
import com.xinzhen.xznongshang.common.BaseParams;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.utils.statistics.DeviceInfoUtils;
import com.xz.wireless.network.interceptor.BasicParamsInterceptor;
import com.xz.wireless.network.interceptor.IBasicDynamic;

import com.xz.wireless.tools.utils.ContextHolder;

import okhttp3.Interceptor;

/**
 * Author: TinhoXu
 *
 * Date: 2016/4/5 17:59
 * <p/>
 * Description: 拦截器 - 用于添加签名参数
 */
class BasicParamsInject {
    private BasicParamsInterceptor interceptor;

    BasicParamsInject() {
        // 设置静态参数
        interceptor = new BasicParamsInterceptor.Builder(false)
                .addHeaderParam("User-Agent", "android")
                .addBodyParam(Constant.APP_KEY, AppConfig.APP_KEY)
                .addBodyParam(Constant.MOBILE_TYPE, BaseParams.MOBILE_TYPE)
                .addBodyParam(Constant.VERSION_NUMBER, DeviceInfoUtils.getVersionName(ContextHolder.getContext()))
                .build();
        // 设置动态参数
        interceptor.setIBasicDynamic(new IBasicDynamic() {
            @Override
            public String signParams(String postBodyString) {
                return UrlUtils.getInstance().signParams(postBodyString);
            }
        });
    }

    Interceptor getInterceptor() {
        return interceptor;
    }
}
