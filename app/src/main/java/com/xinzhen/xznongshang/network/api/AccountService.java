package com.xinzhen.xznongshang.network.api;


import com.xinzhen.xznongshang.module.user.dataModel.receive.UploadCardResult;
import com.xinzhen.xznongshang.network.RequestParams;
import com.xz.wireless.network.entity.HttpResult;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Author: Chenming
 * <p>
 * Date: 16/10/27 上午11:02
 * <p/>
 * Description:账户中心请求接口
 * (@Url: 不要以 / 开头)
 */
public interface AccountService {


    /**
     * 投资人 - 下载协议
     */
    @Streaming
    @FormUrlEncoded
    @POST("member/myInvest/downloadInvestProtocol.html")
    Call<ResponseBody> downloadInvestProtocol(@Field(RequestParams.INVEST_ID) String investId);

    /**
     * 借款人 - 下载协议
     */
    @Streaming
    @FormUrlEncoded
    @POST("member/myInvest/downloadProjectProtocol.html")
    Call<ResponseBody> downloadProjectProtocol(@Field(RequestParams.PROJECT_ID) String projectId);

    /**
     * 已受让 - 下载协议
     */
    @FormUrlEncoded
    @POST("member/myBond/downloadBondInvestProtocol.html")
    Call<ResponseBody> downloadBondInvestProtocol(@Field(RequestParams.BOND_INVEST_ID) String bondInvestId);

    /**
     * 已转让 - 下载协议
     */
    @FormUrlEncoded
    @POST("member/bond/protocol/transfer.html")
    Call<ResponseBody> downloadAllBondProtocol(@Field(RequestParams.BOND_ID) String bondId);


    /**
     * 关闭用户自动投标
     */
    @FormUrlEncoded
    @POST("member/closeAutoInvest.html")
    Call<HttpResult> closeAutoInvest(@Field(RequestParams.AUTO_INVEST_PWD) String payPwd);


    /**
     * 设置交易密码
     */
    @FormUrlEncoded
    @POST("member/security/doSetPayPwd.html")
    Call<ResponseBody> setPayPwd(@Field(RequestParams.PAY_PWD) String payPwd);


    /**
     * 修改交易密码
     */
    @FormUrlEncoded
    @POST("member/security/doModifyPayPwd.html")
    Call<ResponseBody> doModifyPayPwd(@Field(RequestParams.PAY_PWD) String payPwd,
                                      @Field(RequestParams.CODE) String code);

    /**
     * 修改支付密码时获取手机验证码
     */
    @POST("member/security/sendPayValidCode.html")
    Call<ResponseBody> sendPayValidCode();


    /**
     * 转入查询(预计收益)
     */
    @FormUrlEncoded
    @POST("open/fund/interestMoney.html")
    Call<ResponseBody> getInterestMoney(@Field(RequestParams.TRANSFER_AMOUNT) String transferAmount, @Field(RequestParams.FUND_PROJECT_ID) String fundProjectId);



    /**
     * 货基信息
     */
    @POST("open/fund/fundProject.html")
    Call<ResponseBody> getFundProject();


    /**
     * 请求上传身份证图片的url
     */
    @POST("user/individual/getImageServerUrl.html")
    Call<ResponseBody> getCardUrl();

    /**
     * 上传身份证图片
     */
    @Multipart
    @POST
    Call<HttpResult<UploadCardResult>> uploadCardImage(@Url String url, @PartMap Map<String, RequestBody> params);

}
