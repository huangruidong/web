package com.xinzhen.xznongshang.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.xinzhen.xznongshang.MainAct;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.DialogUtils;
import com.xinzhen.xznongshang.module.user.logic.UserLogic;
import com.xinzhen.xznongshang.module.user.ui.activity.LoginAct;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.ActivityManage;
import com.xz.wireless.tools.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import cn.pedant.SweetAlert.OnSweetClickListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/5/30 11:53
 * <p/>
 * Description: 异常处理
 */
@SuppressWarnings("unchecked")
final class ExceptionHandling {
    static void operate(final HttpResult result) {
        switch (result.getCode()) {
            case AppResultCode.ERROR_PASSWORD:
            case AppResultCode.ERROR_ACCOUNT_EXIT:
            case AppResultCode.ERROR_ACCOUNT_NOT_EXIT:
                return;

            case AppResultCode.TOKEN_TIMEOUT: {
                Logger.d("Main", "TOKEN_TIMEOUT");
                UserLogic.signOutSelf(ActivityManage.peek());
                return;
            }

            case AppResultCode.TOKEN_REFRESH_TIMEOUT:
            case AppResultCode.TOKEN_NOT_EXIT: {
                Logger.d("Main", "TOKEN_REFRESH_TIMEOUT");
                if (!(ActivityManage.peek() instanceof MainAct)) {
                    UserLogic.signOutSelf(ActivityManage.peek());
                }
                return;
            }

            case AppResultCode.USER_FREEZE_RECHARGE:
            case AppResultCode.USER_FREEZE_CASH:
            case AppResultCode.USER_FREEZE_INVEST:
            case AppResultCode.USER_FREEZE_REALIZE:
            case AppResultCode.USER_FREEZE_BOND:
            case AppResultCode.USER_FREEZE_LOAN: {
                final Activity activity = ActivityManage.peek();
                DialogUtils.showDialog(activity, result.getMsg(), activity.getString(R.string.dialog_cancel),
                        activity.getString(R.string.step_customer_service), false,
                        new OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                activity.finish();
                                sweetAlertDialog.dismiss();
                            }
                        },
                        new OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                // 拨打客服电话
                                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + activity.getString(R.string.app_hotline))));
                                activity.finish();
                                sweetAlertDialog.dismiss();
                            }
                        });
                return;
            }

            case AppResultCode.USER_LOCK: {
                UserLogic.signOut();
                final Activity activity = ActivityManage.peek();
                DialogUtils.showDialog(activity, result.getMsg(), activity.getString(R.string.step_know), activity.getString(R.string.step_customer_service),
                        false,
                        new OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                if (!(activity instanceof LoginAct)) {
                                    // 当前页非登录页，则跳转到主页后再跳转到登录页
                                    //ARouter.getInstance().build(RouterUrl.XZHOMEPAGE).withInt(BundleKeys.TYPE, Constant.NUMBER_0).navigation();
                                    //ARouter.getInstance().build(RouterUrl.USER_LOGIN).navigation();
                                    activity.finish();
                                }
                                sweetAlertDialog.dismiss();
                            }
                        },
                        new OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                if (!(activity instanceof LoginAct)) {
                                    // 当前页非登录页，则跳转到主页后再跳转到登录页
                                    //ARouter.getInstance().build(RouterUrl.XZHOMEPAGE).withInt(BundleKeys.TYPE, Constant.NUMBER_0).navigation();
                                    //ARouter.getInstance().build(RouterUrl.USER_LOGIN).navigation();
                                }
                                // 拨打客服电话
                                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + activity.getString(R.string.app_hotline))));
                                if (!(activity instanceof LoginAct)) {
                                    activity.finish();
                                }
                                sweetAlertDialog.dismiss();
                            }
                        });
                return;
            }

            case AppResultCode.TOKEN_NOT_UNIQUE: {
                UserLogic.signOut();
                final Context context = ActivityManage.peek();
                Logger.d("Main", "activity:" + ActivityManage.peek());
                DialogUtils.showDialog(context,
                        context.getString(R.string.other_login_prompt), context.getString(R.string.dialog_cancel), context.getString(R.string.re_login), false,
                        new OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                // 跳转到主页
                                sweetAlertDialog.dismiss();
                                if (ActivityManage.peek() instanceof MainAct) {
                                } else {
                                    //ARouter.getInstance().build(RouterUrl.XZHOMEPAGE).withInt(BundleKeys.TYPE, Constant.NUMBER_0).navigation();
                                }
                            }
                        },
                        new OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                // 跳转到主页后再跳转到登录页
                                sweetAlertDialog.dismiss();
                                if (ActivityManage.peek() instanceof MainAct) {
                                } else {
                                    //ARouter.getInstance().build(RouterUrl.XZHOMEPAGE).withInt(BundleKeys.TYPE, Constant.NUMBER_3).navigation();
                                }
                                //ARouter.getInstance().build(RouterUrl.USER_LOGIN).navigation();
                            }
                        });
                return;
            }
            default:
                break;
        }
        ToastUtil.toast(result.getMsg());
    }
}
