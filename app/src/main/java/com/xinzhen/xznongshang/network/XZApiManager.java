package com.xinzhen.xznongshang.network;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import android.databinding.ObservableInt;


import com.xinzhen.xznongshang.module.main.dataMode.ModifyPhoneSub;
import com.xinzhen.xznongshang.module.main.dataMode.ModifyPwdSub;
import com.xinzhen.xznongshang.module.user.dataModel.AuthenticationSub;
import com.xinzhen.xznongshang.module.user.dataModel.BankCardApplySub;
import com.xinzhen.xznongshang.module.user.dataModel.submit.LoginSub;
import com.xinzhen.xznongshang.module.user.dataModel.submit.RegisterSub;
import com.xinzhen.xznongshang.module.user.dataModel.submit.ResetPwdSub;
import com.xinzhen.xznongshang.network.api.AccountService;
import com.xinzhen.xznongshang.network.api.FinancialService;
import com.xinzhen.xznongshang.network.api.HomeServer;
import com.xinzhen.xznongshang.network.api.MineService;
import com.xinzhen.xznongshang.network.api.UserService;
import com.xz.wireless.network.entity.PageMo;

import java.math.BigDecimal;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by mac on 2017/11/27.
 * 网络请求管理类
 */

public class XZApiManager {
    private volatile static XZApiManager apiManager = null;

    private XZApiManager() {
    }

    public static XZApiManager getInstance() {
        if (apiManager == null) {
            synchronized (XZApiManager.class) {
                if (apiManager == null) {
                    apiManager = new XZApiManager();
                }
            }
        }
        return apiManager;
    }

    public void requestCallBack(Call call, final XZApiManagerCallBack callBack, SwipeToLoadLayout swipeLayout, ObservableInt placeholderState) {
        call.enqueue(new RequestCallBack(swipeLayout, placeholderState) {
            @Override
            public void onSuccess(Call call, Response response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onFailed(Call call, Response response) {
                super.onFailed(call, response);
                callBack.onFailed(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                callBack.onFailure(call, t);
            }
        });
    }

    /*实名认证*/
    public void authentication(AuthenticationSub authenticationSub, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).authentication(authenticationSub), xzApiManagerCallBack, null, null);
    }

    /**
     * 校验定向密码
     */
    public void doGetCheckPwd(String productId, String pwd, XZApiManagerCallBack callBack) {
        requestCallBack(XZClient.getService(FinancialService.class).getCheckPwd(productId, pwd), callBack, null, null);
    }

    /*修改支付密码时获取手机验证码 */
    public void sendPayValidCode(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(AccountService.class).sendPayValidCode(), xzApiManagerCallBack, null, null);
    }

    /*上传身份证图片*/
    public void uploadCardImage(String url, Map<String, RequestBody> map, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(AccountService.class).uploadCardImage(url, map), xzApiManagerCallBack, null, null);
    }

    /* 修改交易密码  */
    public void doModifyPayPwd(String payPwd, String code, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(AccountService.class).doModifyPayPwd(payPwd, code), xzApiManagerCallBack, null, null);
    }

    /* 预约借款 初始化接口 */
    public void bespeak(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(FinancialService.class).bespeak(), xzApiManagerCallBack, null, null);
    }

    /*请求上传身份证图片的url*/
    public void getCardUrl(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(AccountService.class).getCardUrl(), xzApiManagerCallBack, null, null);
    }

    /*注册_基本信息提交*/
    public void submitRegisterInfo(RegisterSub sub, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).submitRegisterInfo(sub), xzApiManagerCallBack, null, null);
    }

    /*注册_确认注册*/
    public void doRegister(String __sid, String code, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).doRegister(__sid, code), xzApiManagerCallBack, null, null);
    }

    /*获取图形验证码*/
    public void validimg(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).validimg(), xzApiManagerCallBack, null, null);
    }

    /* 注册_获取验证码*/
    public void getRegisterCode(String phone, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).getRegisterCode(phone), xzApiManagerCallBack, null, null);
    }

    /*忘记密码_确认提交*/
    public void resetPwd(ResetPwdSub sub, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).resetPwd(sub), xzApiManagerCallBack, null, null);
    }

    /*忘记密码_获取验证码*/
    public void getForgotCode(String phone, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).getForgotCode(phone), xzApiManagerCallBack, null, null);
    }

    /*忘记密码_下一步,万能码9999*/
    public void checkCode(String phone, String code, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).checkCode(phone, code), xzApiManagerCallBack, null, null);
    }

    /* 校验图形验证码 */
    public void valicode(String validCode, String sid, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).valicode(validCode, sid), xzApiManagerCallBack, null, null);
    }

    /* 注册_获取协议名称 */
    public void registerProtocol(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).registerProtocol(), xzApiManagerCallBack, null, null);
    }

    /* 登录*/
    public void doLogin(LoginSub sub, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(UserService.class).doLogin(sub), xzApiManagerCallBack, null, null);
    }

    /*账户与安全*/
    public void basicInfo(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(MineService.class).basicInfo(), xzApiManagerCallBack, null, null);
    }

    /**
     * 获取服务器地址
     */
    public void doGetServers(XZApiManagerCallBack callBack) {
        requestCallBack(XZClient.getService(HomeServer.class).servers(), callBack, null, null);
    }

    /*好友邀请 */
    public void userInvite(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(MineService.class).userInvite(), xzApiManagerCallBack, null, null);
    }

    /*绑定手机号 获取验证码*/
    public void bindPhoneCode(String phone, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(MineService.class).bindPhoneCode(phone), xzApiManagerCallBack, null, null);
    }

    /*修改绑定手机号 校验验证码*/
    public void doModifyPhone(String code, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(MineService.class).doModifyPhone(code), xzApiManagerCallBack, null, null);
    }

    /* 修改绑定手机号 获取验证码*/
    public void modifyPhoneCode(XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(MineService.class).modifyPhoneCode(), xzApiManagerCallBack, null, null);
    }

    /*绑定手机 确认提交*/
    public void doBindPhone(ModifyPhoneSub sub, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(MineService.class).doBindPhone(sub), xzApiManagerCallBack, null, null);
    }

    /*修改登录密码*/
    public void doModifyPwd(ModifyPwdSub sub, XZApiManagerCallBack xzApiManagerCallBack) {
        requestCallBack(XZClient.getService(MineService.class).doModifyPwd(sub), xzApiManagerCallBack, null, null);
    }


}