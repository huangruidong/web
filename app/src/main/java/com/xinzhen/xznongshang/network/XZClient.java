package com.xinzhen.xznongshang.network;

import com.xinzhen.xznongshang.common.BaseParams;
import com.xinzhen.xznongshang.common.Constant;
import com.xz.wireless.tools.gesture.logic.SharedInfo;
import com.xz.wireless.network.converter.RDConverterFactory;
import com.xz.wireless.network.interceptor.HttpLoggingInterceptor;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.TextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/4/5 10:30
 * <p/>
 * Description: 网络请求client
 */
public class XZClient {
    // 网络请求超时时间值(s)
    private static final int DEFAULT_TIMEOUT = 30;
    // 请求地址
    private static final String BASE_URL = BaseParams.URI;
    // retrofit实例
    private Retrofit retrofit;

    /**
     * 私有化构造方法
     */
    private XZClient() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                // 设置网络请求超时时间
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cookieJar(cookie())
                // 添加签名参数
                .addInterceptor(new BasicParamsInject().getInterceptor())
                // 打印参数
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                // 失败后尝试重新请求
                .retryOnConnectionFailure(true);

        String inputUrl = (String) SharedInfo.getInstance().getValue("input_url", "");

        if (!TextUtil.isEmpty(inputUrl)) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + inputUrl + "/app/")
                    .client(builder.build())
                    .addConverterFactory(RDConverterFactory.create())
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(builder.build())
                    .addConverterFactory(RDConverterFactory.create())
                    .build();
        }
    }

    private CookieJar cookie() {
        return new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                Constant.cookieStore.put(httpUrl.host(), list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = Constant.cookieStore.get(httpUrl.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
    }

    /**
     * 调用单例对象
     */
    private static XZClient getInstance() {
        return RDClientInstance.instance;
    }

    /**
     * 创建单例对象
     */
    private static class RDClientInstance {
        static XZClient instance = new XZClient();
    }

    ///////////////////////////////////////////////////////////////////////////
    // service
    ///////////////////////////////////////////////////////////////////////////
    private static TreeMap<String, Object> serviceMap;

    private static TreeMap<String, Object> getServiceMap() {
        if (serviceMap == null)
            serviceMap = new TreeMap<>();
        return serviceMap;
    }

    /**
     * @return 指定service实例
     */
    public static <T> T getService(Class<T> clazz) {
        if (getServiceMap().containsKey(clazz.getSimpleName())) {
            return (T) getServiceMap().get(clazz.getSimpleName());
        }

        Logger.w("RDClient", "need to create a new " + clazz.getSimpleName());
        T service = XZClient.getInstance().retrofit.create(clazz);
        getServiceMap().put(clazz.getSimpleName(), service);
        return service;
    }
}
