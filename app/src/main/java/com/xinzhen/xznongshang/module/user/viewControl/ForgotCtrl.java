package com.xinzhen.xznongshang.module.user.viewControl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BundleKeys;
import com.xinzhen.xznongshang.module.user.dataModel.receive.ForgotRec;
import com.xinzhen.xznongshang.module.user.ui.activity.ForgotAct;
import com.xinzhen.xznongshang.module.user.ui.activity.ResetPwdAct;
import com.xinzhen.xznongshang.module.user.viewModel.ForgotVM;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.InputCheck;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.RegularUtil;
import com.xz.wireless.tools.utils.ToastUtil;
import com.xz.wireless.views.TimeButton;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 17:25
 * <p/>
 * Description:忘记密码页面控制器({@link ForgotAct})
 */
public class ForgotCtrl {
    private ForgotVM forgotVM;
    private TimeButton timeButton;

    public ForgotCtrl(TimeButton timeButton) {
        forgotVM = new ForgotVM();
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
                forgotVM.setPhone(forgotVM.getPhone());
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getCodeClick(View view) {
        if (!RegularUtil.isPhone(forgotVM.getPhone())) {
            ToastUtil.toast(R.string.validate_phone);
        } else {
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().getForgotCode(forgotVM.getPhone(), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    ProgressUtil.getInstance().dismiss();
                    ToastUtil.toast(((Response<HttpResult>) response).body().getMsg());
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
     * 下一步点击
     */
    public void nextClick(final View view) {
        if (!RegularUtil.isPhone(forgotVM.getPhone())) {
            ToastUtil.toast(R.string.validate_phone);
        } else if (!InputCheck.checkCode(forgotVM.getCode())) {
            ToastUtil.toast(R.string.validate_code);
        } else {
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().checkCode(forgotVM.getPhone(), forgotVM.getCode(), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    ProgressUtil.getInstance().dismiss();
                    ForgotRec forgotRec = ((Response<HttpResult<ForgotRec>>) response).body().getData();
                    /*ARouter.getInstance().build(RouterUrl.USER_RESET_PASSWORD)
                            .withString(BundleKeys.ID, forgotRec.getPathWay())
                            .withString(BundleKeys.SID, forgotRec.get__sid())
                            .navigation(AndroidUtil.getActivity(view), 0x99);*/
                    Activity act = AndroidUtil.getActivity(view);
                    Intent intent = new Intent(act, ResetPwdAct.class);
                    intent.putExtra(BundleKeys.ID, forgotRec.getPathWay());
                    intent.putExtra(BundleKeys.ID, forgotRec.get__sid());
                    act.startActivityForResult(intent, 0 * 99);

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

    public ForgotVM getForgotVM() {
        return forgotVM;
    }
}
