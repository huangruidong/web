package com.xinzhen.xznongshang.module.user.dataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liufang on 2017/11/16.
 * 申请绑卡
 */
@Setter
@Getter
public class BankCardApplySub {
    private String accountName;//银行卡上的用户名
    private String accountNo;//银行卡号
    private String tel;//电话号码
    private String bankCode;//银行code

    public BankCardApplySub(String accountName, String accountNo, String tel, String bankCode) {
        this.accountName = accountName;
        this.accountNo = accountNo;
        this.tel = tel;
        this.bankCode = bankCode;
    }
}
