package com.xinzhen.xznongshang.module.main.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;

import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.common.FunctionConfig;
import com.xinzhen.xznongshang.databinding.ActMineAccountSecurityBinding;
import com.xinzhen.xznongshang.module.main.dataMode.ServerRec;
import com.xinzhen.xznongshang.module.main.logic.DynamicInfoLogic;
import com.xinzhen.xznongshang.module.main.viewControl.AccountSecurityCtrl;
import com.xinzhen.xznongshang.router.RouterExtras;
import com.xinzhen.xznongshang.router.RouterUrl;
import com.xz.wireless.tools.gesture.logic.PhotographLogic;
import com.xz.wireless.tools.gesture.logic.SharedInfo;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.tools.utils.FileUtil;

import java.io.File;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/22 15:55
 * <p/>
 * Description: 账户与安全
 */
@Route(path = RouterUrl.MINE_ACCOUNT_SECURITY, extras = RouterExtras.EXTRA_LOGIN)
public class AccountSecurityAct extends BaseAct {
    private AccountSecurityCtrl securityCtrl;
    private File avatarFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActMineAccountSecurityBinding binding = DataBindingUtil.setContentView(this, R.layout.act_mine_account_security);
        if (FunctionConfig.getInstance().isFunctionModifyPhoneNumberEnable()) {
            binding.securityPhone.setArrowRightRes(R.drawable.arrow_right);
            binding.securitySex.setArrowRightRes(R.drawable.arrow_right);
            binding.securityCity.setArrowRightRes(R.drawable.arrow_right);
            binding.securityNickName.setArrowRightRes(R.drawable.arrow_right);
            binding.securityModifyPassword.setArrowRightRes(R.drawable.arrow_right);
        }
        securityCtrl = new AccountSecurityCtrl(this, binding);
        binding.setViewCtrl(securityCtrl);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotographLogic.getInstance().onActivityResult(this, requestCode, resultCode, data, new PhotographLogic.PhotographCallback() {
            @Override
            public void obtain(final File file) {
                avatarFile = file;
                if (null != file) {
                    ServerRec rec = SharedInfo.getInstance().getEntity(ServerRec.class);
                    if (null != rec) {
                        refreshAvatar(rec.getImageServer(), file);
                    } else {
                        DynamicInfoLogic.getInstance().reqServerInfo(new DynamicInfoLogic.IServerInfo() {
                            @Override
                            public void callback(ServerRec rec) {
                                refreshAvatar(rec.getImageServer(), file);
                            }
                        });
                    }
                }
            }
        });
        if (requestCode == PhotographLogic.REQUEST_CODE_PICK ||
                requestCode == PhotographLogic.REQUEST_CODE_TAKE ||
                requestCode == PhotographLogic.REQUEST_CODE_CUTTING) {
            return;
        }

        if (resultCode == RESULT_OK) {
            securityCtrl.reqData();
        }
    }

    /**
     * 上传头像
     */
    private void refreshAvatar(String imageServer, File file) {
        Glide.get(ContextHolder.getContext()).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(ContextHolder.getContext()).clearDiskCache();
            }
        }).start();
        securityCtrl.getSecurityVM().setAvatarPath(file.getPath());
        securityCtrl.toImage(imageServer, file);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != avatarFile) {
            FileUtil.deleteFile(avatarFile);
            avatarFile = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        securityCtrl.reqData();
    }
}
