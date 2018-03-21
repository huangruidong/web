package com.xinzhen.xznongshang.network.api;


import com.xinzhen.xznongshang.module.user.dataModel.AuthenticationSub;
import com.xinzhen.xznongshang.module.user.dataModel.BankCardApplySub;
import com.xinzhen.xznongshang.module.user.dataModel.receive.ForgotRec;
import com.xinzhen.xznongshang.module.user.dataModel.receive.OauthTokenMo;
import com.xinzhen.xznongshang.module.user.dataModel.receive.RegisterProtocolRec;
import com.xinzhen.xznongshang.module.user.dataModel.receive.SessionRec;
import com.xinzhen.xznongshang.module.user.dataModel.submit.LoginSub;
import com.xinzhen.xznongshang.module.user.dataModel.submit.RegisterSub;
import com.xinzhen.xznongshang.module.user.dataModel.submit.ResetPwdSub;
import com.xinzhen.xznongshang.network.RequestParams;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.network.entity.ListData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 15:44
 * <p/>
 * Description: 用户接口
 * (@Url: 不要以 / 开头)
 */
public interface UserService {
    /**
     * 登录
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("open/user/doLogin.html")
    Call<HttpResult<OauthTokenMo>> doLogin(@Body LoginSub sub);

    /**
     * 刷新令牌
     */
    @FormUrlEncoded
    @POST("open/user/refreshToken.html")
    Call<HttpResult<OauthTokenMo>> refreshToken(@Field(RequestParams.REFRESH_TOKEN) String refreshToken);

    /**
     * 注册_获取验证码
     */
    @FormUrlEncoded
    @POST("open/user/registerPhoneCode.html")
    Call<HttpResult> getRegisterCode(@Field(RequestParams.MOBILE) String phone);

    /**
     * 注册_获取协议名称
     */
    @POST("open/user/registerProtocol.html")
    Call<HttpResult<ListData<RegisterProtocolRec>>> registerProtocol();

    /**
     * 注册_基本信息提交
     */
    @POST("open/user/registerFirst.html")
    Call<HttpResult<SessionRec>> submitRegisterInfo(@Body RegisterSub sub);

    /**
     * 注册_确认注册
     */
    @FormUrlEncoded
    @POST("open/user/doRegister.html")
    Call<HttpResult<OauthTokenMo>> doRegister(@Field(RequestParams.__SID) String __sid, @Field(RequestParams.CODE) String code);

    /**
     * 忘记密码_获取验证码
     */
    @FormUrlEncoded
    @POST("open/user/security/sendValidCode.html")
    Call<HttpResult> getForgotCode(@Field(RequestParams.PATH_WAY) String phone);

    /**
     * 忘记密码_下一步,万能码9999
     */
    @FormUrlEncoded
    @POST("open/user/retrievepwd/validation.html")
    Call<HttpResult<ForgotRec>> checkCode(@Field(RequestParams.PATH_WAY) String phone, @Field(RequestParams.DYNAMIC_VALID_CODE) String code);

    /**
     * 忘记密码_确认提交
     */
    @POST("open/user/retrievepwd/confirm.html")
    Call<HttpResult> resetPwd(@Body ResetPwdSub sub);

    /**
     * 获取图形验证码
     */
    @POST("open/validimg.html")
    Call<ResponseBody> validimg();

    /**
     * 校验图形验证码
     */
    @FormUrlEncoded
    @POST("open/valicode.html")
    Call<HttpResult> valicode(@Field(RequestParams.VALID_CODE) String validCode, @Field(RequestParams.__SID) String sid);

    /**
     * 实名认证
     */
    @POST("user/individual/realname.html")
    Call<ResponseBody> authentication(@Body AuthenticationSub sub);

    /**
     * 根据银行卡号获取银行卡信息
     */
    @FormUrlEncoded
    @POST("user/individual/card/type.html")
    Call<ResponseBody> bankCardType(@Field(RequestParams.BNAK_CARDNO) String cardNo);

    /**
     * 申请绑卡
     */
    @POST("user/individual/card/binding/apply.html")
    Call<ResponseBody> applyBindingBankCard(@Body BankCardApplySub sub);

    /**
     * 确认绑卡
     */
    @FormUrlEncoded
    @POST("user/individual/card/binding/confirm.html")
    Call<ResponseBody> confirmBindingBankCard(@Field(RequestParams.VERIFICATION_CODE) String varifyCode);

    /**
     * 获取用户信息
     */
    @POST("user/info.html")
    Call<ResponseBody> getUserStatus();
    /**
     * 登出
     */
    @FormUrlEncoded
    @POST("open/user/doLogout.html")
    Call<ResponseBody> loginOut(@Field(RequestParams.USERID) String userId);
    /**
     * 获取现金贷url
     */
    @POST("open/user/turnPaydayLoan.html")
    Call<ResponseBody> doLoan();
}