package com.xinzhen.xznongshang.module.main.dataMode;

import lombok.Getter;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/18 14:16
 * <p/>
 * Description: 邀请好友数据
 */
@Getter
public class InviteRec {
    /**
     * 邀请链接
     */
    private String inviteUrl;
    /**
     * 加息券个数
     */
    private String rateCount;
    /**
     * 红包总额
     */
    private String redAmount;
    /**
     * 红包个数
     */
    private String redCount;
    /**
     * 分享标题
     */
    private String shareTitle;
    /**
     * 分享内容
     */
    private String shareContent;
    /**
     * 奖励规则说明
     */
    private String awardRule;
    /**
     * 奖励提示
     */
    private String awardPrompt;
}
