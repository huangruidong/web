package com.xinzhen.xznongshang.module.main.dataMode;

/**
 * Author: TinhoXu
 *
 * Date: 2017/1/25 11:47
 * <p/>
 * Description: 预约借款提交数据
 */
public class BorrowBespeakSub {
    /** 联系人 */
    private String contactName;
    /** 借款期限(天) */
    private String limitTime;
    /** 联系电话 */
    private String mobile;
    /** 借款金额 */
    private String money;
    /** 性别:M 男性，F女性 */
    private String sex;
    /** 地区字符拼接 逗号隔开.如: 110000, 110100 */
    private String zone;
    /** 借款预约Token */
    private String borrowBespeakAddToken;
    private String __sid;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getBorrowBespeakAddToken() {
        return borrowBespeakAddToken;
    }

    public void setBorrowBespeakAddToken(String borrowBespeakAddToken) {
        this.borrowBespeakAddToken = borrowBespeakAddToken;
    }

    public String get__sid() {
        return __sid;
    }

    public void set__sid(String __sid) {
        this.__sid = __sid;
    }
}
