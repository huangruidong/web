package com.xinzhen.xznongshang.common;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2017/3/3 14:21
 * <p/>
 * Description: 功能配置
 */
public class FunctionConfig {
    /**
     * 债权开关
     */
    private boolean functionBondEnable;
    /**
     * 红包开关
     */
    private boolean functionRedPacketEnable;
    /**
     * 加息券开关
     */
    private boolean functionUpRateEnable;
    /**
     * 新手引导页开关
     */
    private boolean functionGuideEnable;
    /**
     * 自动投标开关
     */
    private boolean functionAutoTenderEnable;
    /**
     * 变现开关
     */
    private boolean functionRealizeEnable;
    /**
     * 借款开关
     */
    private boolean functionLoanEnable;
    /**
     * 业务授权
     */
    private boolean functionBusinessAuthorizeEnable;
    /**
     * 积分商城
     */
    private boolean functionCreditMallEnable;
    /**
     * 积分抽奖
     */
    private boolean functionCreditPriseEnable;
    /**
     * 修改手机号
     */
    private boolean functionModifyPhoneNumberEnable;
    /**
     * 信真宝
     */
    private boolean functionDemandGoldEnable;
    /**
     * 浔银宝
     */
    private boolean functionDemandSilverEnable;

    private FunctionConfig() {
        this.functionGuideEnable = true;
        this.functionBondEnable = true;
        this.functionRealizeEnable = true;
        this.functionLoanEnable = true;
        this.functionAutoTenderEnable = true;
        this.functionRedPacketEnable = true;
        this.functionUpRateEnable = true;
        this.functionBusinessAuthorizeEnable = true;
        this.functionCreditMallEnable = false;
        this.functionCreditPriseEnable = false;
        this.functionModifyPhoneNumberEnable = true;
        this.functionDemandGoldEnable = true;
        this.functionDemandSilverEnable = true;
    }

    public static FunctionConfig getInstance() {
        return FunctionConfigInstance.instance;
    }

    private static class FunctionConfigInstance {
        static FunctionConfig instance = new FunctionConfig();
    }

    public boolean isFunctionGuideEnable() {
        return functionGuideEnable;
    }

    public boolean isFunctionBondEnable() {
        return functionBondEnable;
    }

    public boolean isFunctionRealizeEnable() {
        return functionRealizeEnable;
    }

    public boolean isFunctionLoanEnable() {
        return functionLoanEnable;
    }

    public boolean isFunctionRedPacketEnable() {
        return functionRedPacketEnable;
    }

    public boolean isFunctionUpRateEnable() {
        return functionUpRateEnable;
    }

    public boolean isFunctionBusinessAuthorizeEnable() {
        return functionBusinessAuthorizeEnable;
    }

    public boolean isFunctionModifyPhoneNumberEnable() {
        return functionModifyPhoneNumberEnable;
    }
}
