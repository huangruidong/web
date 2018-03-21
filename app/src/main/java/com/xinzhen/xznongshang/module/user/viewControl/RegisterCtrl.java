package com.xinzhen.xznongshang.module.user.viewControl;

import android.app.Activity;
import android.content.Intent;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;


import com.alibaba.android.arouter.launcher.ARouter;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.DialogUtils;
import com.xinzhen.xznongshang.module.user.dataModel.receive.OauthTokenMo;
import com.xinzhen.xznongshang.module.user.dataModel.receive.RegisterProtocolRec;
import com.xinzhen.xznongshang.module.user.dataModel.receive.SessionRec;
import com.xinzhen.xznongshang.module.user.dataModel.submit.RegisterSub;
import com.xinzhen.xznongshang.module.user.logic.UserLogic;
import com.xinzhen.xznongshang.module.user.ui.activity.RegisterAct;
import com.xinzhen.xznongshang.module.user.ui.activity.RegisterSucceedAct;
import com.xinzhen.xznongshang.module.user.viewModel.RegisterVM;
import com.xinzhen.xznongshang.network.AppResultCode;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.InputCheck;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.network.entity.ListData;
import com.xz.wireless.network.exception.ApiException;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.tools.utils.RegularUtil;
import com.xz.wireless.tools.utils.ToastUtil;
import com.xz.wireless.tools.utils.TransformTools;
import com.xz.wireless.views.TimeButton;

import java.util.List;
import java.util.TreeMap;

import cn.pedant.SweetAlert.OnSweetClickListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/17 14:02
 * <p/>
 * Description: 注册页面控制器({@link RegisterAct})
 */
public class RegisterCtrl {
    private RegisterVM registerVM;
    /**
     * 图形验证码校验时需要的sid
     */
    private String sid;
    private TimeButton timeButton;
    private TextView protocolView;

    public RegisterCtrl(TimeButton timeButton, TextView protocolView) {
        registerVM = new RegisterVM();
        this.timeButton = timeButton;
        this.protocolView = protocolView;
        this.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCodeClick(view);
            }
        });
        this.timeButton.setResetCallback(new TimeButton.ResetCallback() {
            @Override
            public void reset() {
                registerVM.setPhone(registerVM.getPhone());
            }
        });
        registerProtocol();
    }

    /**
     * 获取注册协议信息
     */
    private void registerProtocol() {
        XZApiManager.getInstance().registerProtocol(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                Response<HttpResult<ListData<RegisterProtocolRec>>> responseRegister = (Response<HttpResult<ListData<RegisterProtocolRec>>>) response;
                protocolInit(responseRegister.body().getData().getList());
            }
        });
    }

    /**
     * 协议初始化
     */
    private void protocolInit(List<RegisterProtocolRec> listData) {
        if (listData == null || listData.isEmpty()) {
            return;
        }
        String protocol = ContextHolder.getContext().getString(R.string.register_agree);
        int start;
        int end;
        int index = 0;
        int position = 0;
        for (RegisterProtocolRec rec : listData) {
            protocol += "《" + rec.getName() + "》 ";
        }

        protocol = protocol.substring(0, protocol.length() - 1);

        SpannableString string = new SpannableString(protocol);
        String temp = protocol;
        while (temp.contains("》")) {
            start = position + temp.indexOf("《");
            end = position + temp.indexOf("》");
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("protocolId", listData.get(index++).getId());
            //      string.setSpan(new ProtocolClickableSpan(protocol.substring(start + 1, end), treeMap, protocolView.getId()),
            //              start, end + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            temp = protocol.substring(end + 1);
            position = end + 1;
        }
        registerVM.setProtocol(string);
    }

    /**
     * 获取验证码
     */
    private void getCodeClick(final View view) {
        if (!RegularUtil.isPhone(registerVM.getPhone())) {
            ToastUtil.toast(R.string.validate_phone);
        } else {
            ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
            XZApiManager.getInstance().getRegisterCode(registerVM.getPhone(), new XZApiManagerCallBack() {
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
                    final Activity activity = AndroidUtil.getActivity(view);
                    if (t instanceof ApiException && ((ApiException) t).getResult().getCode() == AppResultCode.ERROR_ACCOUNT_EXIT) {
                        HttpResult result = ((ApiException) t).getResult();
                        DialogUtils.showDialog(activity, result.getMsg(),
                                activity.getString(R.string.login_reenter), activity.getString(R.string.login_immediately),
                                false,
                                new OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        registerVM.setPhone("");
                                    }
                                },
                                new OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        activity.finish();
                                    }
                                });
                    }
                }
            });
        }
    }

    /**
     * 注册协议点击
     */
    public void protocolClick(View view) {
        ToastUtil.toast("注册协议");
    }

    /**
     * 注册_基本信息提交
     */
    public void submitClick(final View view) {
        if (!RegularUtil.isPhone(registerVM.getPhone())) {
            ToastUtil.toast(R.string.validate_phone);
        } else if (!InputCheck.checkCode(registerVM.getCode())) {
            ToastUtil.toast(R.string.validate_code);
        } else if (!InputCheck.checkPwd(registerVM.getPwd())) {
            ToastUtil.toast(R.string.validate_pwd);
        } else if (!registerVM.isAgree()) {
            ToastUtil.toast(R.string.validate_agree_register);
        } else {
            XZApiManager.getInstance().submitRegisterInfo(new RegisterSub(registerVM.getPhone(), registerVM.getPwd(), registerVM.getInvite()), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                    doRegister(view, ((Response<HttpResult<SessionRec>>) response).body().getData().get__sid());
                }


                @Override
                public void onFailure(Call call, Throwable t) {
                    super.onFailure(call, t);
                    final Activity activity = AndroidUtil.getActivity(view);
                    if (t instanceof ApiException && ((ApiException) t).getResult().getCode() == AppResultCode.ERROR_ACCOUNT_EXIT) {
                        HttpResult result = ((ApiException) t).getResult();
                        DialogUtils.showDialog(activity, result.getMsg(),
                                activity.getString(R.string.login_reenter), activity.getString(R.string.login_immediately),
                                false,
                                new OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        registerVM.setPhone("");
                                    }
                                },
                                new OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        activity.finish();
                                    }
                                });
                    }
                }
            });
        }
    }

    /**
     * 注册_确认注册
     */
    private void doRegister(final View view, String __sid) {
        XZApiManager.getInstance().doRegister(__sid, registerVM.getCode(), new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                Activity activity = AndroidUtil.getActivity(view);
                UserLogic.login(activity, ((Response<HttpResult<OauthTokenMo>>) response).body().getData(), registerVM.getPhone());
                //ARouter.getInstance().build(RouterUrl.USER_REGISTER_SUCCEED).navigation();
                activity.startActivity(new Intent(activity, RegisterSucceedAct.class));
                activity.finish();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                t.printStackTrace();
                final Activity activity = AndroidUtil.getActivity(view);
                if (t instanceof ApiException && ((ApiException) t).getResult().getCode() == AppResultCode.ERROR_ACCOUNT_EXIT) {
                    HttpResult result = ((ApiException) t).getResult();
                    DialogUtils.showDialog(activity, result.getMsg(),
                            activity.getString(R.string.login_reenter), activity.getString(R.string.login_immediately),
                            false,
                            new OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    registerVM.setPhone("");
                                }
                            },
                            new OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    activity.finish();
                                }
                            });
                }
            }
        });
    }

    public RegisterVM getRegisterVM() {
        return registerVM;
    }

    /**
     * 获取图形验证码
     */
    public void getImgCodeClick(View view) {
        XZApiManager.getInstance().validimg(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                sid = getSid(response.headers().get("Set-Cookie"));
                registerVM.setDrawable(TransformTools.getInstance().InputStream2Drawable(((Response<ResponseBody>) response).body().byteStream()));
            }
        });
    }

    /**
     * 字符串中获取sid值
     */
    private String getSid(String cookie) {
        String sid = "";
        if (cookie.contains("sid=")) {
            cookie = cookie.substring(cookie.indexOf("sid="));
            sid = cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";"));
        }
        return sid;
    }

    /**
     * 校验图形验证码
     */
    public void checkImgCode(View view) {
        XZApiManager.getInstance().valicode("1234", sid, new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                ToastUtil.toast(((Response<HttpResult>) response).body().getMsg());
            }
        });
    }
}
