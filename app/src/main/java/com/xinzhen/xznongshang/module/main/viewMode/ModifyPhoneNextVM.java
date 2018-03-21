package com.xinzhen.xznongshang.module.main.viewMode;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.module.main.viewControl.ModifyPhoneNextCtrl;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.tools.utils.StringFormat;

/**
 * Author: TinhoXu
 *
 * Date: 2016/11/24 11:51
 * <p/>
 * Description:修改绑定手机号 - 下一步 模型({@link ModifyPhoneNextCtrl})
 */
public class ModifyPhoneNextVM extends BaseObservable {
    /** 已绑定手机号 */
    private String  phone;
    /** 验证码 */
    private String  code;
    /** 按钮是否可用 */
    private boolean enable;

    @Bindable
    public String getPhone() {
        if (TextUtils.isEmpty(phone)) {
            phone = "";
        } else {
            phone = ContextHolder.getContext().getString(R.string.bind_phone_bound, StringFormat.phoneHideFormat(phone));
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        checkInput();
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        notifyPropertyChanged(BR.enable);
    }

    /**
     * 输入校验
     */
    private void checkInput() {
        if (!TextUtils.isEmpty(code)) {
            setEnable(true);
        } else {
            setEnable(false);
        }
    }
}
