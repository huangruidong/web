package com.xinzhen.xznongshang.module.user.dataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liufang on 2017/11/17.
 */
@Setter
@Getter
public class XZUserStatus {
    private boolean isBindingCard;//是否绑卡
    private boolean isRealname;//是否实名
    private boolean isSetTradePwd;//是否设置交易密码

}
