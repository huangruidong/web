package com.xinzhen.xznongshang.network.api;

/**
 * Author: TinhoXu
 *
 * Date: 2016/12/22 15:33
 * <p/>
 * Description: H5页接口
 */
public class H5Service {
    /** 头像上传接口 */
    public static final String API_UPLOAD_AVATAR            = "/upload/avatarMobile.html";
    /** 汇付天下 */
    public static final String API_LOGIN                    = "member/security/apiLogin.html";
    /** 风险承受能力评估 - 进入风险评测 */
    public static final String API_SECURITY_TO_RISK         = "member/risk/userappRiskPapers.html";
    /** app普通投资 产品简介 */
    public static final String API_PROJECT_APP_CONTENT      = "member/project/appContent.html";
    /** app交易所 产品简介 */
    public static final String API_PROJECT_APP_EXCHANGE     = "member/exchange/knowledge.html";
    /** app变现投资 产品简介 */
    public static final String API_REALIZE_APP_CONTENT      = "member/realize/appContent.html";
    /** VIP等级 */
    public static final String API_VIP                      = "member/account/appvip.html";
    /** 关于我们 */
    public static final String API_ABOUT_US                 = "open/column/aboutUs.html";
    /** 帮助中心 */
    public static final String API_HELP                     = "open/column/helpCenter.html";
    /** 注册协议详情 */
    public static final String API_REGISTER_PROTOCOL_DETAIL = "open/user/registerProtocolDetail.html";
    /** 资产--变现协议 */
    public static final String API_REALIZE_PROTOCOL         = "member/myRealize/realizeProtocol.html";
    /** 债权投资协议 */
    public static final String API_BOND_PROTOCOL_CONTENT    = "member/myBond/getBondProtocolContent.html";
    /** 普通投资和变现借款协议 */
    public static final String API_PROTOCOL_SEARCH          = "member/myInvest/protocolSearch.html";
    /** 汇付天下 */
    public static final String API_PNR                      = "open/user/registerProtocolDetail.html";
    /** 平台数据 */
    public static final String API_SITE_DATA                = "open/column/siteData.html";
    /** 安全保障 */
    public static final String API_INSURANCE                = "open/column/insurance.html";
    /** 绑卡协议 */
    public static final String API_TIED_CARD_AGREEMENT      = "user/card/banding/protocol.html";
    /**信真宝协议*/
    public static final String API_GOLD = "open/fund/queryProtocol.html";
}
