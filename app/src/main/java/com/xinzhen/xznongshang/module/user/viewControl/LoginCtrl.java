package com.xinzhen.xznongshang.module.user.viewControl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.xinzhen.xznongshang.MainAct;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.common.DialogUtils;
import com.xinzhen.xznongshang.module.user.dataModel.receive.OauthTokenMo;
import com.xinzhen.xznongshang.module.user.dataModel.submit.LoginSub;
import com.xinzhen.xznongshang.module.user.logic.UserLogic;
import com.xinzhen.xznongshang.module.user.ui.activity.ForgotAct;
import com.xinzhen.xznongshang.module.user.ui.activity.LoginAct;
import com.xinzhen.xznongshang.module.user.ui.activity.RegisterAct;
import com.xinzhen.xznongshang.module.user.viewModel.LoginVM;
import com.xinzhen.xznongshang.network.AppResultCode;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.network.exception.ApiException;
import com.xz.wireless.tools.gesture.logic.SharedInfo;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.ToastUtil;

import cn.pedant.SweetAlert.OnSweetClickListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Response;

import static com.xinzhen.xznongshang.utils.ProgressUtil.getInstance;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/16 16:17
 * <p/>
 * Description: 登录页面控制器({@link LoginAct})
 */
public class LoginCtrl {
    private LoginVM loginVM;
    private Activity mAct;

    public LoginCtrl(Activity act) {
        mAct = act;
        loginVM = new LoginVM();
        String account = (String) SharedInfo.getInstance().getValue(Constant.USER_PHONE, "");
        if (!TextUtils.isEmpty(account)) {
            loginVM.setPhone(account);
        }
    }

    /**
     * 登录按钮
     */
    public void submitClick(final View view) {
        ProgressUtil.getInstance().show(mAct);
        XZApiManager.getInstance().doLogin(new LoginSub(loginVM.getPhone(), loginVM.getPwd()), new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                //登录成功
                ToastUtil.toast(R.string.login_success);
                ProgressUtil.getInstance().dismiss();
                UserLogic.login(mAct, ((Response<HttpResult<OauthTokenMo>>) response).body().getData(), loginVM.getPhone());
                mAct.startActivity(new Intent(mAct, MainAct.class));
                mAct.finish();
            }


            @Override
            public void onFailed(Response response) {
                getInstance().dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                Logger.d("LoginCtrl", "onFailure");
                //登录失败
                getInstance().dismiss();
                super.onFailure(call, t);
                final Context context = view.getContext();
                if (t instanceof ApiException && ((ApiException) t).getResult().getCode() == AppResultCode.ERROR_PASSWORD) {
                    HttpResult result = ((ApiException) t).getResult();
                    ToastUtil.toast(result.getMsg());
                } else if (t instanceof ApiException && ((ApiException) t).getResult().getCode() == AppResultCode.ERROR_ACCOUNT_NOT_EXIT) {
                    HttpResult result = ((ApiException) t).getResult();
                    DialogUtils.showDialog(context, result.getMsg(),
                            context.getString(R.string.login_reenter), context.getString(R.string
                                    .login_register_now), false,
                            new OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    loginVM.setPhone("");
                                    loginVM.setPwd("");
                                }
                            },
                            new OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    //       ARouter.getInstance().build(RouterUrl.USER_REGISTER).navigation();
                                }
                            });
                }

            }
        });
    }

    /**
     * 忘记密码按钮
     */
    public void forgotClick(View view) {
        mAct.startActivity(new Intent(mAct, ForgotAct.class));
    }

    /**
     * 注册按钮
     */
    public void registerClick(View view) {
        mAct.startActivity(new Intent(mAct, RegisterAct.class));
    }

    public LoginVM getLoginVM() {
        return loginVM;
    }
}
