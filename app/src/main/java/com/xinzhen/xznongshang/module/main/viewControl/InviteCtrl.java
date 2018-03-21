package com.xinzhen.xznongshang.module.main.viewControl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.xinzhen.share.Constant;
import com.xinzhen.share.ShareUtils;
import com.xinzhen.share.WechatManager;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.AppConfig;
import com.xinzhen.xznongshang.common.QrCodePopupWindow;
import com.xinzhen.xznongshang.databinding.ActMineInviteBinding;
import com.xinzhen.xznongshang.module.main.adapter.GridAdapter;
import com.xinzhen.xznongshang.module.main.dataMode.InviteGridItem;
import com.xinzhen.xznongshang.module.main.dataMode.InviteRec;
import com.xinzhen.xznongshang.module.main.ui.InviteAct;
import com.xinzhen.xznongshang.module.main.viewMode.InviteVM;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.Utils;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/18 10:07
 * <p/>
 * Description: 邀请好友控制器({@link InviteAct})
 */
public class InviteCtrl implements GridAdapter.OnItemClickListener {
    private InviteVM inviteVM;
    private final String TAG = "InviteCtrl";

    private QrCodePopupWindow qrCodePopupWindow;
    private InviteRec rec;
    private ActMineInviteBinding binding;
    private RecyclerView mRecycle;
    private Activity mActivity;

    public InviteCtrl(ActMineInviteBinding binding, Activity activity) {
        this.mActivity = activity;
        this.binding = binding;
        inviteVM = new InviteVM();
        qrCodePopupWindow = new QrCodePopupWindow(activity);
        mRecycle = binding.inviteRecycle;
        init();
        reqData();
    }

    private void init() {
        ShareUtils.init(Utils.getCtx());
        initRecycle();
    }

    private void initRecycle() {
        mRecycle.setLayoutManager(new GridLayoutManager(mActivity, 3));
        GridAdapter adapter = new GridAdapter(initGridData());
        adapter.setItemClickListener(this);
        mRecycle.setAdapter(adapter);
    }

    private List<InviteGridItem> initGridData() {
        List<InviteGridItem> list = new ArrayList<>();
        list.add(new InviteGridItem(ContextCompat.getDrawable(mActivity, R.drawable.invite_icon_wechat), Utils.getCtx().getString(R.string.invite_wechat)));
        list.add(new InviteGridItem(ContextCompat.getDrawable(mActivity, R.drawable.invite_icon_qq), Utils.getCtx().getString(R.string.invite_qq)));
        list.add(new InviteGridItem(ContextCompat.getDrawable(mActivity, R.drawable.invite_icon_qr_code), Utils.getCtx().getString(R.string.invite_qr_code)));
        list.add(new InviteGridItem(ContextCompat.getDrawable(mActivity, R.drawable.invite_icon_circle), Utils.getCtx().getString(R.string.invite_circle)));
        list.add(new InviteGridItem(ContextCompat.getDrawable(mActivity, R.drawable.invite_icon_qzone), Utils.getCtx().getString(R.string.invite_qzone)));
        list.add(new InviteGridItem(ContextCompat.getDrawable(mActivity, R.drawable.invite_icon_link), Utils.getCtx().getString(R.string.invite_more_share)));
        return list;
    }

    @Override
    public void onItemClick(View view, int index) {
        Bundle param = new Bundle();
        if (rec == null) {
            ToastUtil.toast("获取分享数据失败");
            return;
        }
        switch (index) {
            /*微信分享*/
            case 0:
                ShareUtils.getWeChatManager(AppConfig.WX_APP_ID).shareText(rec.getInviteUrl(), WechatManager.Type.WX_FRIEND);
                break;
            /*QQ好友分享*/
            case 1:
                param.putString(Constant.QQ_SHARE_TITLE, rec.getShareTitle());
                param.putString(Constant.QQ_SHARE_TARGETURL, rec.getInviteUrl());
                param.putString(Constant.QQ_SHARE_SUMMARY, rec.getShareContent());
                ShareUtils.getQQShareManager(AppConfig.QQ_APP_ID).friendShare(mActivity, param);
                break;
            /*二维码*/
            case 2:
                qrCodePopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            /*朋友圈分享*/
            case 3:
                ShareUtils.getWeChatManager(AppConfig.WX_APP_ID).shareText(rec.getInviteUrl(), WechatManager.Type.WX_CIRCLE);
                break;
            /*空间分享*/
            case 4:
                param.putString(Constant.QQ_SHARE_TITLE, rec.getShareTitle());
                param.putString(Constant.QQ_SHARE_TARGETURL, rec.getInviteUrl());
                param.putString(Constant.QQ_SHARE_SUMMARY, rec.getShareContent());
                ShareUtils.getQQShareManager(AppConfig.QQ_APP_ID).qZoneShare(mActivity, param);

                break;
            /*更多*/
            case 5:
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, rec.getInviteUrl());
                mActivity.startActivity(Intent.createChooser(textIntent, "分享"));
                break;
        }
    }

    /**
     * 网络请求
     */
    private void reqData() {
        XZApiManager.getInstance().userInvite(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                rec = ((Response<HttpResult<InviteRec>>) response).body().getData();
                convert();
                binding.awardPrompt.setText(rec.getAwardPrompt());
                qrCodePopupWindow.setQrImage(rec.getInviteUrl());
                Log.i("getInviteUrl:", rec.getInviteUrl());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
            }

            @Override
            public void onFailed(Response response) {
                super.onFailed(response);
                Log.i("fail:", "fail");
            }
        });
    }

    /**
     * 数据转换
     */
    private void convert() {
        inviteVM.setRateCount(rec.getRateCount());
        inviteVM.setRedAmount(rec.getRedAmount());
        inviteVM.setRedCount(rec.getRedCount());
    }

    /**
     * 奖励规则点击
     */
    public void ruleClick(View view) {
        if (rec == null) {
            Logger.d(TAG, "失败读取数据");
            return;
        }
        //    ARouter.getInstance().build(RouterUrl.MINE_INVITE_RULE).withString(BundleKeys.CONTENT, rec.getAwardRule()).navigation();
    }

    /**
     * 我的邀请点击
     */
    public void myInvitedClick(View view) {
        //    ARouter.getInstance().build(RouterUrl.MINE_INVITED).navigation();
    }

    public InviteVM getInviteVM() {
        return inviteVM;
    }
}
