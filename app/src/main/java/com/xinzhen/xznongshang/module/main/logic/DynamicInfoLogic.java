package com.xinzhen.xznongshang.module.main.logic;

import android.view.View;

import com.xinzhen.xznongshang.module.main.dataMode.BasicInfoRec;
import com.xinzhen.xznongshang.module.main.dataMode.ServerRec;
import com.xinzhen.xznongshang.module.user.dataModel.receive.OauthTokenMo;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.gesture.logic.SharedInfo;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 *
 * Date: 2016/12/12 15:36
 * <p/>
 * Description: 动态信息获取接口
 */
public class DynamicInfoLogic {
    private DynamicInfoLogic() {
    }

    public static DynamicInfoLogic getInstance() {
        return DynamicInfoLogicInstance.instance;
    }

    private static class DynamicInfoLogicInstance {
        static DynamicInfoLogic instance = new DynamicInfoLogic();
    }

    /**
     * 获取用户基础信息
     */
    public void reqBasicInfo(final View view, final IBasicInfo info) {
        viewClickable(view, false);
        XZApiManager.getInstance().basicInfo(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                viewClickable(view, true);
                OauthTokenMo tokenMo = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
                if (null != tokenMo) {
                    tokenMo.setAvatarPhoto(((Response<HttpResult<BasicInfoRec>> )response).body().getData().getAvatarPhoto());
                    tokenMo.setHideUserName(((Response<HttpResult<BasicInfoRec>> )response).body().getData().getHideUserName());

                    SharedInfo.getInstance().saveEntity(tokenMo);
                    SharedInfo.getInstance().saveEntity(((Response<HttpResult<BasicInfoRec>> )response).body().getData());
                    if (null != info) {
                        info.callback(((Response<HttpResult<BasicInfoRec>> )response).body().getData());
                    }
                }
            }

            @Override
            public void onFailed(Response response) {
                viewClickable(view, true);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                viewClickable(view, true);
            }
        });
    }

    private void viewClickable(View view, boolean clickable) {
        if (null != view) {
            view.setClickable(clickable);
        }
    }

    /**
     * 获取服务器地址
     */
    public void reqServerInfo(final IServerInfo info) {
        XZApiManager.getInstance().doGetServers(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                Response<HttpResult<ServerRec>> responseData = response;
                SharedInfo.getInstance().saveEntity(responseData.body().getData());
                if (null != info) {
                    info.callback(responseData.body().getData());
                }
            }
        });
    }

    /**
     * 用户基础信息转换为三方状态
     */
//    public ToPaymentMo BasicInfoConvertToPaymentMo(BasicInfoRec rec) {
//        ToPaymentMo paymentMo = new ToPaymentMo();
//        paymentMo.setAuthorize(ConverterUtil.getBoolean(rec.getAuthorizated()));
//        paymentMo.setAuthorizeType("");
//        paymentMo.setBankCardCount(rec.getBankNum());
//        paymentMo.setPayPwdStatus(ConverterUtil.getBoolean(rec.getHasSetPayPwd()));
//        paymentMo.setRealNameStatus(ConverterUtil.getBoolean(rec.getRealnameStatus()));
//        paymentMo.setRiskStatus(!TextUtil.isEmpty(rec.getRiskLevel()));
//        return paymentMo;
//    }

    public interface IBasicInfo {
        /** 用户基础信息回调 */
        void callback(BasicInfoRec rec);
    }

    public interface IServerInfo {
        /** 服务器地址回调 */
        void callback(ServerRec rec);
    }
}
