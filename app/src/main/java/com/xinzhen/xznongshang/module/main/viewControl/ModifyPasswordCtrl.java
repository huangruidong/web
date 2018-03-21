package com.xinzhen.xznongshang.module.main.viewControl;

import android.view.View;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.module.main.dataMode.ModifyPwdSub;
import com.xinzhen.xznongshang.module.main.ui.ModifyPasswordAct;
import com.xinzhen.xznongshang.module.main.viewMode.ModifyPasswordVM;
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
 * Date: 2016/11/29 17:26
 * <p/>
 * Description: 修改登录密码页面控制器({@link ModifyPasswordAct})
 */
public class ModifyPasswordCtrl {
    private ModifyPasswordVM passwordVM;

    public ModifyPasswordCtrl() {
        passwordVM = new ModifyPasswordVM();
    }

    /**
     * 确认修改点击
     */
    public void submitClick(final View view) {
        if (!InputCheck.checkPwd(passwordVM.getPwdNew())) {
            ToastUtil.toast(R.string.validate_pwd);
        } else if (!passwordVM.getPwdNew().equals(passwordVM.getPwdConfirm())) {
            ToastUtil.toast(R.string.validate_pwd_different);
        } else {
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().doModifyPwd(new ModifyPwdSub(passwordVM.getPwdOld(), passwordVM.getPwdNew(), passwordVM.getPwdConfirm()), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    ProgressUtil.getInstance().dismiss();
                    ToastUtil.toast(((Response<HttpResult>)response).body().getMsg());
                    (AndroidUtil.getActivity(view)).finish();
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

    public ModifyPasswordVM getPasswordVM() {
        return passwordVM;
    }
}
