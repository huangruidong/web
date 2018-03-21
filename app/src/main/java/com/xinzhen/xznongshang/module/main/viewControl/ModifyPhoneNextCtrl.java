package com.xinzhen.xznongshang.module.main.viewControl;

import android.support.v4.app.FragmentManager;
import android.view.View;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.module.main.frag.ModifyPhoneConfirmFrag;
import com.xinzhen.xznongshang.module.main.frag.ModifyPhoneNextFrag;
import com.xinzhen.xznongshang.module.main.viewMode.ModifyPhoneNextVM;
import com.xinzhen.xznongshang.module.user.dataModel.receive.SessionRec;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.InputCheck;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.ToastUtil;
import com.xz.wireless.views.TimeButton;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 *
 * Date: 2016/11/24 11:51
 * <p/>
 * Description: 修改绑定手机号 - 下一步 页面控制器({@link ModifyPhoneNextFrag})
 */
public class ModifyPhoneNextCtrl {
    private ModifyPhoneNextVM nextVM;
    private FragmentManager   manager;
    private TimeButton        timeButton;

    public ModifyPhoneNextCtrl(String phone, FragmentManager manager, TimeButton timeButton) {
        nextVM = new ModifyPhoneNextVM();
        nextVM.setPhone(phone);
        this.manager = manager;
        this.timeButton = timeButton;
        this.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCodeClick(view);
            }
        });
    }

    /**
     * 获取验证码点击
     */
    private void getCodeClick(View view) {
        ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
        XZApiManager.getInstance().modifyPhoneCode(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                ProgressUtil.getInstance().dismiss();
                ToastUtil.toast((( Response<HttpResult>)response).body().getMsg());
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

    /**
     * 下一步点击
     */
    public void nextClick(View view) {
        if (!InputCheck.checkCode(nextVM.getCode())) {
            ToastUtil.toast(R.string.validate_code);
        } else {
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().doModifyPhone(nextVM.getCode(), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    ProgressUtil.getInstance().dismiss();
                    manager.beginTransaction().replace(R.id.content,
                            ModifyPhoneConfirmFrag.newInstance(((Response<HttpResult<SessionRec>> )response).body().getData().getModifyPhoneSign())).addToBackStack(null).commit();
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

    public ModifyPhoneNextVM getNextVM() {
        return nextVM;
    }
}
