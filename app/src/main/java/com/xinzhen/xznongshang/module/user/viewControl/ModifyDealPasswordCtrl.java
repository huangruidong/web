package com.xinzhen.xznongshang.module.user.viewControl;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.databinding.ActUserDealPasswordBinding;
import com.xinzhen.xznongshang.module.user.ui.activity.ModifyDealPasswordAct;
import com.xinzhen.xznongshang.module.user.viewModel.ModifyDealPasswordVM;
import com.xinzhen.xznongshang.network.AppResultCode;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.tools.utils.ToastUtil;
import com.xz.wireless.views.TimeButton;
import com.xz.wireless.views.TradePasswordBox;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by liufang on 2017/11/7.
 * 修改密码
 */

public class ModifyDealPasswordCtrl {
    private final String TAG = "ModifyDealPasswordCtrl";
    private ModifyDealPasswordVM modifyDealPasswordVM;
    private TimeButton timeButton;
    private ModifyDealPasswordAct act;
    private InputMethodManager methodManager;

    public ModifyDealPasswordCtrl(ModifyDealPasswordAct act, TimeButton timeButton, final ActUserDealPasswordBinding binding) {
        modifyDealPasswordVM = new ModifyDealPasswordVM();
        this.act = act;
        this.timeButton = timeButton;
        methodManager = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCodeClick();
            }
        });
        this.timeButton.setResetCallback(new TimeButton.ResetCallback() {
            @Override
            public void reset() {
                modifyDealPasswordVM.setDealPassword(modifyDealPasswordVM.getDealPassword());
            }
        });
        initData(binding);
    }

    private void initData(ActUserDealPasswordBinding binding) {
        TradePasswordBox tBox = (TradePasswordBox) binding.getRoot().findViewById(R.id.trade_password_box);
        tBox.addUpdateTextListener(new TradePasswordBox.OnUpdateTextListener() {
            @Override
            public void onUpdateText(String password) {
                modifyDealPasswordVM.setDealPassword(password);
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getCodeClick() {
        //请求后台服务
        XZApiManager.getInstance().sendPayValidCode(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                try {
                    String json = ((Response<ResponseBody>) response).body().string();
                    JSONObject object = new JSONObject(json);
                    int resCode = object.getInt("resCode");
                    if (resCode == AppResultCode.SUCCESS) {
                        ToastUtil.toast(R.string.verify_pwd_success);
                    } else {
                        ToastUtil.toast(object.getString("resMsg"));
                    }
                } catch (Exception e) {
                    Logger.d(TAG, "error", e);
                }
            }

        });
    }

    /**
     * 提交
     *
     * @return
     */
    public void submitDealPassword(final View view) {
        //收回键盘
        methodManager.hideSoftInputFromWindow(act.getWindow().getDecorView().getWindowToken(),
                0);
        if (modifyDealPasswordVM.getDealPassword().length() != 6) {
            ToastUtil.toast("交易密码为" + ContextHolder.getContext().getString(R.string.deal_code_tip_one));
        } else {
            //交易密码提交到后台
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().doModifyPayPwd(modifyDealPasswordVM.getDealPassword(), modifyDealPasswordVM.getMessageCode(), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    ProgressUtil.getInstance().dismiss();
                    try {
                        String json = ((Response<ResponseBody>) response).body().string();
                        JSONObject object = new JSONObject(json);
                        int resCode = object.getInt("resCode");
                        if (resCode == AppResultCode.SUCCESS) {
                            ToastUtil.toast(R.string.pwd_change_success);
                            AndroidUtil.getActivity(view).finish();
                        } else {
                            ToastUtil.toast(object.getString("resMsg"));
                        }
                    } catch (Exception e) {
                        Logger.d(TAG, "error", e);
                    }
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

    public ModifyDealPasswordVM getModifyDealPasswordVM() {
        return modifyDealPasswordVM;
    }
}
