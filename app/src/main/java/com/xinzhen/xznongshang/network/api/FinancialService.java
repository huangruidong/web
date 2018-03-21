package com.xinzhen.xznongshang.network.api;

import com.xinzhen.xznongshang.common.BorrowBespeakRec;
import com.xinzhen.xznongshang.network.RequestParams;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.network.entity.PageMo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: TaoHao
 * <p>
 * Date: 2016/10/27$ 13:25$
 * <p/>
 * Description: 理财产品请求接口
 * (@Url: 不要以 / 开头)
 */
public interface FinancialService {
    /**
     * 预约提醒
     */
    @FormUrlEncoded
    @POST("member/index/investBespeak.html")
    Call<HttpResult<String>> getInvestBespeak(@Field(RequestParams.PROJECT_ID) String projectId);

    /**
     * 预约借款 初始化接口
     */
    @POST("open/borrow/bespeak.html")
    Call<HttpResult<BorrowBespeakRec>> bespeak();

    /**
     * 校验定向密码
     */
    @FormUrlEncoded
    @POST("open/project/checkPwd.html")
    Call<HttpResult<String>> getCheckPwd(@Field(RequestParams.PROJECT_ID) String projectId, @Field(RequestParams.PWD) String pwd);

    /**
     * 普通投资
     */
    @FormUrlEncoded
    @POST("member/invest/doInvest.html")
    Call<HttpResult> doInvest(@Field(RequestParams.PROJECT_ID) String projectId, @Field(RequestParams.AMOUNT) String amount,
                              @Field(RequestParams.PAYPWD) String payPwd, @Field(RequestParams.REDENVELOPEIDS) String redEnvelopeIds,
                              @Field(RequestParams.RATECOUPONID) String rateCouponId);

}
