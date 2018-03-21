package com.xinzhen.xznongshang.module.user.viewControl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.module.user.dataModel.submit.ResetPwdSub;
import com.xinzhen.xznongshang.module.user.ui.activity.LoginAct;
import com.xinzhen.xznongshang.module.user.ui.activity.ResetPwdAct;
import com.xinzhen.xznongshang.module.user.viewModel.ResetPwdVM;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.InputCheck;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 18:50
 * <p/>
 * Description: 重置密码页面控制器({@link ResetPwdAct})
 */
public class ResetPwdCtrl {
    private ResetPwdVM resetPwdVM;
    /**
     * 重置密码需要提交的数据
     */
    private ResetPwdSub sub;

    public ResetPwdCtrl(String phone, String sid) {
        resetPwdVM = new ResetPwdVM();
        sub = new ResetPwdSub(phone, sid);
    }

    /**
     * 完成点击
     */
    public void submitClick(final View view) {
        if (!InputCheck.checkPwd(resetPwdVM.getPwdNew())) {
            ToastUtil.toast(R.string.validate_pwd);
        } else if (!resetPwdVM.getPwdNew().equals(resetPwdVM.getPwdConfirm())) {
            ToastUtil.toast(R.string.validate_pwd_different);
        } else {
            sub.setPwd(resetPwdVM.getPwdNew());
            sub.setConfirmPwd(resetPwdVM.getPwdConfirm());
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().resetPwd(sub, new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    ToastUtil.toast(((Response<HttpResult>) response).body().getMsg());
                    Activity activity = AndroidUtil.getActivity(view);
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

    public ResetPwdVM getResetPwdVM() {
        return resetPwdVM;
    }
}
