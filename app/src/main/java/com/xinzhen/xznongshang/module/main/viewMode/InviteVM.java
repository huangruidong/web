package com.xinzhen.xznongshang.module.main.viewMode;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.xinzhen.xznongshang.BR;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.module.main.viewControl.InviteCtrl;
import com.xinzhen.xznongshang.utils.DividerGridItemDecoration;
import com.xinzhen.xznongshang.utils.Utils;
import com.xz.wireless.tools.utils.ContextHolder;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/18 10:08
 * <p/>
 * Description: 邀请好友模型({@link InviteCtrl})
 */
public class InviteVM<T> extends BaseObservable {
    /**
     * 加息券个数
     */
    private String rateCount = "--";
    /**
     * 红包总额
     */
    private String redAmount = "--";
    /**
     * 红包个数
     */
    private String redCount;

    public InviteVM() {
    }

    @Bindable
    public String getRateCount() {
        return ContextHolder.getContext().getString(R.string.invite_amount, rateCount);
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
        notifyPropertyChanged(BR.rateCount);


    }

    @Bindable
    public String getRedAmount() {
        return ContextHolder.getContext().getString(R.string.invite_total, redAmount);
    }

    public void setRedAmount(String redAmount) {
        this.redAmount = redAmount;
        notifyPropertyChanged(BR.redAmount);
    }

    @Bindable
    public String getRedCount() {
        return redCount;
    }

    public void setRedCount(String redCount) {
        this.redCount = redCount;
        notifyPropertyChanged(BR.redCount);
    }


    @Bindable
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerGridItemDecoration(Utils.getCtx());
    }
}
