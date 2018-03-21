package com.xinzhen.xznongshang.network.api;


import com.xinzhen.xznongshang.module.main.dataMode.BasicInfoRec;
import com.xinzhen.xznongshang.module.main.dataMode.InviteRec;
import com.xinzhen.xznongshang.module.main.dataMode.ModifyPhoneSub;
import com.xinzhen.xznongshang.module.main.dataMode.ModifyPwdSub;
import com.xinzhen.xznongshang.module.user.dataModel.receive.SessionRec;
import com.xinzhen.xznongshang.network.RequestParams;
import com.xz.wireless.network.entity.HttpResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/15 16:56
 * <p/>
 * Description: 我的接口
 * (@Url: 不要以 / 开头)
 */
public interface MineService {

    /**
     * 账户与安全
     */
    @POST("member/account/basicInfo.html")
    Call<HttpResult<BasicInfoRec>> basicInfo();

    /**
     * 好友邀请
     */
    @POST("member/invite/userInvite.html")
    Call<HttpResult<InviteRec>> userInvite();

    /**
     * 修改绑定手机号 获取验证码
     */
    @POST("member/security/modifyPhoneCode.html")
    Call<HttpResult> modifyPhoneCode();

    /**
     * 修改绑定手机号 校验验证码
     */
    @FormUrlEncoded
    @POST("member/security/doModifyPhone.html")
    Call<HttpResult<SessionRec>> doModifyPhone(@Field(RequestParams.CODE) String code);

    /**
     * 绑定手机号 获取验证码
     */
    @FormUrlEncoded
    @POST("member/security/bindPhoneCode.html")
    Call<HttpResult<SessionRec>> bindPhoneCode(@Field(RequestParams.MOBILE) String phone);

    /**
     * 绑定手机 确认提交
     */
    @POST("member/security/doBindPhone.html")
    Call<HttpResult> doBindPhone(@Body ModifyPhoneSub sub);

    /**
     * 修改登录密码
     */
    @POST("member/security/doModifyPwd.html")
    Call<HttpResult> doModifyPwd(@Body ModifyPwdSub sub);
}
