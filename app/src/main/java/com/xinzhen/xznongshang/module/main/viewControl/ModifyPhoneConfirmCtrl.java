package com.xinzhen.xznongshang.module.main.viewControl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.module.main.dataMode.ModifyPhoneSub;
import com.xinzhen.xznongshang.module.main.frag.ModifyPhoneConfirmFrag;
import com.xinzhen.xznongshang.module.main.viewMode.ModifyPhoneConfirmVM;
import com.xinzhen.xznongshang.module.user.dataModel.receive.SessionRec;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.InputCheck;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.gesture.logic.SharedInfo;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.RegularUtil;
import com.xz.wireless.tools.utils.ToastUtil;
import com.xz.wireless.views.TimeButton;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 *
 * Date: 2016/11/24 14:52
 * <p/>
 * Description: 修改绑定手机号 - 确定 页面控制器({@link ModifyPhoneConfirmFrag})
 */
public class ModifyPhoneConfirmCtrl {
    private ModifyPhoneConfirmVM confirmVM;
    /** 绑定手机提交数据 */
    private ModifyPhoneSub sub;
    private TimeButton           timeButton;

    public ModifyPhoneConfirmCtrl(String modifyPhoneSign, TimeButton timeButton) {
        confirmVM = new ModifyPhoneConfirmVM();
        sub = new ModifyPhoneSub(Constant.STATUS_1);
        sub.setModifyPhoneSign(modifyPhoneSign);
        this.timeButton = timeButton;
        this.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCodeClick(view);
            }
        });
        this.timeButton.setResetCallback(new TimeButton.ResetCallback() {
            @Override
            public void reset() {
                confirmVM.setPhone(confirmVM.getPhone());
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getCodeClick(View view) {
        if (!RegularUtil.isPhone(confirmVM.getPhone())) {
            ToastUtil.toast(R.string.validate_phone);
        } else {
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().bindPhoneCode(confirmVM.getPhone(), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    ProgressUtil.getInstance().dismiss();
                    ToastUtil.toast(((Response<HttpResult<SessionRec>>)response).body().getMsg());
                    sub.setMobileBindToken(((Response<HttpResult<SessionRec>>)response).body().getData().getMobileBindToken());
                }

                @Override
                public void onFailed(Response response) {
                    ProgressUtil.getInstance().dismiss();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    super.onFailure(call, t);
                    ProgressUtil.getInstance().dismiss();
                }
            });
        }
    }

    /**
     * 确定按钮点击
     */
    public void submitClick(final View view) {
        if (!RegularUtil.isPhone(confirmVM.getPhone())) {
            ToastUtil.toast(R.string.validate_phone);
        } else if (TextUtils.isEmpty(sub.getMobileBindToken())) {
            ToastUtil.toast(R.string.validate_get_code);
        } else if (!InputCheck.checkCode(confirmVM.getCode())) {
            ToastUtil.toast(R.string.validate_code);
        } else {
            sub.setPhone(confirmVM.getPhone());
            sub.setCode(confirmVM.getCode());
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().doBindPhone(sub,new XZApiManagerCallBack(){
                @Override
                public void onSuccess(Response response) {
                    ProgressUtil.getInstance().dismiss();
                    ToastUtil.toast(((Response<HttpResult>)response).body().getMsg());
                    Activity activity = AndroidUtil.getActivity(view);
                    SharedInfo.getInstance().saveValue(Constant.USER_PHONE, confirmVM.getPhone());
                    activity.setResult(Activity.RESULT_OK);
                    activity.finish();
                }

                @Override
                public void onFailed(Response response) {
                    ProgressUtil.getInstance().dismiss();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    super.onFailure(call, t);
                    ProgressUtil.getInstance().dismiss();
                }
            });
        }
    }

    public ModifyPhoneConfirmVM getConfirmVM() {
        return confirmVM;
    }
}
