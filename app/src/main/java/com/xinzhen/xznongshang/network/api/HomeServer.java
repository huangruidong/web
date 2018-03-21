package com.xinzhen.xznongshang.network.api;


import com.xinzhen.xznongshang.module.main.dataMode.ServerRec;
import com.xz.wireless.network.entity.HttpResult;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Author: TinhoXu
 *
 * Date: 2016/12/12 15:12
 * <p/>
 * Description: 首页公共接口
 * (@Url: 不要以 / 开头)
 */
public interface HomeServer {
    /** 获取服务器地址 */
    @POST("open/index/servers.html")
    Call<HttpResult<ServerRec>> servers();
}
