package com.xinzhen.xznongshang.module.user.dataModel;

/**
 * Created by liufang on 2017/11/16.
 */

public class AuthenticationSub {
    private String realname;
    private String identityCard;
    private String communicationAddress;
    private String expireDate;
    private String certificateBackUrl;
    private String certificateFrontUrl;

    public AuthenticationSub(String realname, String identityCard, String communicationAddress, String expireDate, String certificateBackUrl, String certificateFrontUrl) {
        this.realname = realname;
        this.identityCard = identityCard;
        this.communicationAddress = communicationAddress;
        this.expireDate = expireDate;
        this.certificateBackUrl = certificateBackUrl;
        this.certificateFrontUrl = certificateFrontUrl;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getCommunicationAddress() {
        return communicationAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCertificateBackUrl() {
        return certificateBackUrl;
    }

    public void setCertificateBackUrl(String certificateBackUrl) {
        this.certificateBackUrl = certificateBackUrl;
    }

    public String getCertificateFrontUrl() {
        return certificateFrontUrl;
    }

    public void setCertificateFrontUrl(String certificateFrontUrl) {
        this.certificateFrontUrl = certificateFrontUrl;
    }
}
