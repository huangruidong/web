package com.xinzhen.xznongshang.module.user.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.databinding.ActUserAuthenticationBinding;
import com.xinzhen.xznongshang.module.user.dataModel.receive.UploadCardResult;
import com.xinzhen.xznongshang.module.user.viewControl.AuthenticationCtrl;
import com.xinzhen.xznongshang.network.AppResultCode;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.network.utils.FileUploadUtil;
import com.xz.wireless.tools.utils.ToastUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 实名认证
 */
public class AuthenticationAct extends BaseAct {
    boolean isRealnameProcess;
    private AuthenticationCtrl ctrl;
    private ActUserAuthenticationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(AuthenticationAct.this, R.layout.act_user_authentication);
        ctrl = new AuthenticationCtrl(binding, isRealnameProcess, this);
        binding.setAuthenticationCtrl(ctrl);
        // 启用EventBus3.0加速功能
        EventBus.getDefault().register(this);//注册事件
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//注销事件
    }

    //标记和发送消息的标记一样的，包括类型和值都必须一样 .type: 1 上传身份证正面      2 上传身份证反面   3  实名认证
    @Subscriber(tag = "realname")
    public void onReceive(int type) {
        if (type == Constant.NUMBER_1) {
            uploadCardImage(Constant.NUMBER_1);
        } else if (type == Constant.NUMBER_2) {
            uploadCardImage(Constant.NUMBER_2);
        } else if (type == Constant.NUMBER_3) {
            ctrl.realName();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK || resultCode == 1) {
            setResult(RESULT_OK);
            finish();
        }
    }

    public void uploadCardImage(final int type) {
        File file;
        if (type == Constant.NUMBER_1) {
            //上传身份证正面图片
            file = new File(Constant.cardFrontAddress);
        } else {
            //上传身份证反面图片
            file = new File(Constant.cardBackAddress);
        }
        String url = Constant.authenticationUrl + Constant.AUTHENTICATION_BASE_URL;
        Map<String, File> map = new HashMap<>();
        map.put("image", file);
        XZApiManager.getInstance().uploadCardImage(url, FileUploadUtil.getRequestMap(map), new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                Response<HttpResult<UploadCardResult>> picResponse = (Response<HttpResult<UploadCardResult>>) response;
                int resCode = picResponse.body().getCode();
                if (resCode == AppResultCode.SUCCESS) {
                    //发送消息
                    //第一个是要传递的数据，第二个参数是标记
                    if (type == Constant.NUMBER_1) {
                        Constant.cardFrontPath = picResponse.body().getData().getPath();
                        EventBus.getDefault().post(Constant.NUMBER_2, "realname");
                    } else {
                        Constant.cardBackPath = picResponse.body().getData().getPath();
                        EventBus.getDefault().post(Constant.NUMBER_3, "realname");
                    }
                } else {
                    ToastUtil.toast(picResponse.body().getMsg());
                }
            }

            @Override
            public void onFailed(Response response) {
                super.onFailed(response);
                ProgressUtil.getInstance().dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                super.onFailure(call, t);
                ProgressUtil.getInstance().dismiss();
            }
        });
    }
}
