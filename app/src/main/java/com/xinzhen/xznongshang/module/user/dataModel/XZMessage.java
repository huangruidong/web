package com.xinzhen.xznongshang.module.user.dataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liufang on 2017/11/17.
 *
 */
@Setter
@Getter
public class XZMessage {

    private int resCode;
    private String resMsg;
    private XZUserStatus resData;


}
