package com.xinzhen.xznongshang.common;

import lombok.Getter;

/**
 * Author: TinhoXu
 *
 * Date: 2017/2/8 10:14
 * <p/>
 * Description: 借款预约接收类型
 */
@Getter
public class BorrowBespeakRec {
    /**  */
    private String account;
    /** 省市区数据 */
    private String areaJson;
    /** 借款期限 */
    private String bespeakTimeList;
    /** token防重复 */
    private String borrowBespeakAddToken;
    /** 可借款的最大值 */
    private String borrowMaxAccount;
    /** 可借款的最小值 */
    private String borrowMinAccount;
    private String __sid;

}
