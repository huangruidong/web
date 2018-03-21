package com.xinzhen.xznongshang.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 10:30
 * <p/>
 * Description:
 */
public class InputCheck {
    /**
     * 验证密码规则，是否由8-16为数字+字母组成
     *
     * @param pwd 待检测密码
     * @return true - 符合密码规则;
     * false - 不符合密码规则
     */
    public static boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }
        Pattern regex = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$).{8,16}$");
        Matcher matcher = regex.matcher(pwd);
        return matcher.matches();
    }

    /**
     * 检验验证码是否符合规则
     *
     * @param code 待检测验证码
     * @return true - 符合验证码规则；
     * false - 不符合验证码规则
     */
    public static boolean checkCode(String code) {
        if (TextUtils.isEmpty(code)) {
            return false;
        }
        Pattern regex = Pattern.compile("^\\d{4,6}$");
        Matcher matcher = regex.matcher(code);
        return matcher.matches();
    }

    /**
     * 校验银行卡号是否符合规则
     *
     * @param bankcard 待检测银行卡号
     * @return true - 符合验证码规则；
     * false - 不符合验证码规则
     */
    public static boolean checkBankcard(String bankcard) {
        return !TextUtils.isEmpty(bankcard) && bankcard.length() >= 15 && bankcard.length() <= 19;
    }

    /*
    *匹配短信中间的6个数字(验证码等)
     */
    public static String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        Pattern pattern = Pattern.compile("(?<!\\d)\\d{6}(?!\\d)");
        Matcher matcher = pattern.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
