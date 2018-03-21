package com.xinzhen.xznongshang.module.user.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.databinding.ActUserDealPasswordBinding;
import com.xinzhen.xznongshang.module.user.viewControl.ModifyDealPasswordCtrl;
import com.xinzhen.xznongshang.utils.InputCheck;
import com.xz.wireless.tools.utils.ToastUtil;

/**
 * Description: 修改交易密码
 */
public class ModifyDealPasswordAct extends BaseAct {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private static final String KEY = "pdus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActUserDealPasswordBinding binding = DataBindingUtil.setContentView(ModifyDealPasswordAct.this, R.layout.act_user_deal_password);
        binding.setDealPasswordCtrl(new ModifyDealPasswordCtrl(this, binding.timeButton, binding));
    }

    /*
    *从短信中截取验证码
     */
    private void getCodeFromMessage() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        intentFilter.setPriority(Integer.MAX_VALUE);
        BroadcastReceiver smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Object[] objs = (Object[]) intent.getExtras().get(KEY);
                for (Object obj : objs != null ? objs : new Object[0]) {
                    byte[] pdu = (byte[]) obj;
                    SmsMessage sms = SmsMessage.createFromPdu(pdu);
                    //短信的内容
                    String message = sms.getMessageBody();
                    //短信的手机号  +86开头
                    String fromPhone = sms.getOriginatingAddress();
                    if (!TextUtils.isEmpty(fromPhone)) {
                        String authenticationCode = InputCheck.patternCode(message);
                        if (!TextUtils.isEmpty(authenticationCode)) {
                            ToastUtil.toast(authenticationCode);
                        }
                    }
                }

            }
        };
        registerReceiver(smsReceiver, intentFilter);
    }
}
