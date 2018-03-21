package com.xinzhen.xznongshang.network;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by mac on 2017/11/27.
 * 网络回调方法
 */

public abstract class XZApiManagerCallBack<T> {
    public abstract void onSuccess(Response<T> response);

    public void onFailed(Response<T> response) {
    }

    public void onFailure(Call call, Throwable t) {
    }
}
