package com.xinzhen.xznongshang.module.user.logic;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.xinzhen.xznongshang.MainAct;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BundleKeys;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.common.DialogUtils;
import com.xinzhen.xznongshang.module.user.dataModel.receive.OauthTokenMo;
import com.xz.wireless.tools.gesture.logic.GestureLogic;
import com.xz.wireless.tools.gesture.logic.SharedInfo;
import com.xz.wireless.tools.utils.ContextHolder;

import cn.pedant.SweetAlert.OnSweetClickListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/16 17:55
 * <p/>
 * Description:
 */
public class UserLogic {
    /**
     * 是否已经登录
     */
    public static boolean isLand() {
        boolean isLand = (boolean) SharedInfo.getInstance().getValue(Constant.IS_LAND, false);
        OauthTokenMo tokenRec = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
        // 是否已经登录
        return isLand && null != tokenRec && !TextUtils.isEmpty(tokenRec.getUserId());
    }

    /**
     * 用户登录
     */
    public static void login(Activity activity, OauthTokenMo oauthTokenMo, String phone) {
        // 登录逻辑处理
        SharedInfo.getInstance().saveValue(Constant.IS_LAND, true);
        SharedInfo.getInstance().saveValue(Constant.USER_PHONE, phone);
        SharedInfo.getInstance().saveEntity(oauthTokenMo);

        Intent intent = new Intent();
        intent.setAction(Constant.IS_LAND);
        ContextHolder.getContext().sendBroadcast(intent);
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    /**
     * 登出必须执行的操作
     */
    public static void signOut() {
        // OauthTokenMo tokenMo = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
        // if (null != tokenMo) {
        //     // 删除保存的手势密码信息
        //     GestureLogic.getInstance().removeEntity(tokenMo.getUserId());
        // }
        // 标记未登录
        SharedInfo.getInstance().remove(Constant.IS_LAND);
        // 删除保存的OauthToken信息
        SharedInfo.getInstance().remove(OauthTokenMo.class);

        SharedInfo.getInstance().remove(Constant.IS_BINDING_CARD);//删除是否绑卡标志
        SharedInfo.getInstance().remove(Constant.IS_REAL_NAME);//删除是否实名标志
        SharedInfo.getInstance().remove(Constant.IS_SET_TRADE_PWD);//删除是否设置交易密码标志

        // 清楚缓存的手势密码信息
        GestureLogic.getInstance().clean();
        Intent intent = new Intent();
        intent.setAction(Constant.IS_LAND);
        ContextHolder.getContext().sendBroadcast(intent);
        Constant.cookieStore.clear();
    }

    /**
     * 用户被动登出(到主界面)
     */
    public static void signOutForcibly(Activity activity) {
        signOut();
        //ARouter.getInstance().build(RouterUrl.XZHOMEPAGE).withInt(BundleKeys.TYPE, Constant.NUMBER_0).navigation();
        if (!(activity instanceof MainAct)) {
            activity.finish();
        }
    }

    /**
     * 用户主动登出(到主界面)
     */
    public static void signOutToMain(final Activity activity) {
        DialogUtils.showDialog(activity, R.string.user_login_out, R.string.dialog_confirm, true, new OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog dialog) {
                signOutForcibly(activity);
                dialog.dismiss();
            }
        });
    }

    /**
     * 用户主动登出(到登录界面)
     */
    public static void signOutSelf(final Activity activity) {
        signOut();
        //ARouter.getInstance().build(RouterUrl.XZHOMEPAGE).withInt(BundleKeys.TYPE, Constant.NUMBER_0).navigation();
        //ARouter.getInstance().build(RouterUrl.USER_LOGIN).navigation();
        if (!(activity instanceof MainAct)) {
            activity.finish();
        }
    }

    /**
     * 用户主动登出(到登录界面)
     */
    public static void signOutToLogin(final Activity activity) {
        DialogUtils.showDialog(activity, R.string.user_login_out, R.string.dialog_confirm, true, new OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog dialog) {
                signOutSelf(activity);
                dialog.dismiss();
            }
        });
                /*
        OauthTokenMo oauthTokenMo = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
        if (!TextUtil.isEmpty(oauthTokenMo.getUserId())) {
            XZApiManager.getInstance().loginOut(Base64.encode(oauthTokenMo.getUserId().getBytes()), new XZApiManagerCallBack() {
                @Override
                public void onSuccess(Response response) {
                }

                @Override
                public void onFailed(Response response) {
                    super.onFailed(response);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    super.onFailure(call, t);
                }
            });
        }
        */
    }
}
