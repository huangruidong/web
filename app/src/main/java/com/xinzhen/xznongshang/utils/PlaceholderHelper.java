package com.xinzhen.xznongshang.utils;

import android.content.Context;

import com.xinzhen.xznongshang.R;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.views.PlaceholderLayout;

/**
 * Author: TinhoXu
 *
 * Date: 2017/1/6 14:16
 * <p/>
 * Description: placeholder 工具类
 */
public class PlaceholderHelper {
    /** 空页 - 暂无加息券 */
    public final static int EMPTY_COUPON      = 0x101;
    /** 空页 - 暂无红包 */
    public final static int EMPTY_LUCKY_MONEY = 0x102;
    /** 空页 - 暂无消息 */
    public final static int EMPTY_MESSAGE     = 0x103;
    /** 空页 - 暂无公告 */
    public final static int EMPTY_NOTICE      = 0x104;
    /** 空页 - 暂无产品 */
    public final static int EMPTY_PRODUCT     = 0x105;
    /** 空页 - 暂无记录 */
    public final static int EMPTY_RECORD      = 0x106;
    /** 空页 - 暂无银行卡 */
    public final static int EMPTY_CARD        = 0x107;
    /** 上下文 */
    private Context context;

    private PlaceholderHelper() {
        context = ContextHolder.getContext();
    }

    public static PlaceholderHelper getInstance() {
        return PlaceholderHelperInstance.instance;
    }

    private static class PlaceholderHelperInstance {
        static PlaceholderHelper instance = new PlaceholderHelper();
    }

    public void setStatus(PlaceholderLayout layout, int status) {
        switch (status) {
            case PlaceholderLayout.SUCCESS:
            case PlaceholderLayout.ERROR:
            case PlaceholderLayout.NO_NETWORK:
            case PlaceholderLayout.LOADING:
                layout.setStatus(status);
                return;

            case EMPTY_COUPON:
                layout.setEmptyText(context.getString(R.string.placeholder_empty_coupon));
                layout.setEmptyImage(R.drawable.default_icon_norate);
                break;

            case EMPTY_LUCKY_MONEY:
                layout.setEmptyText(context.getString(R.string.placeholder_empty_lucky_money));
                layout.setEmptyImage(R.drawable.default_icon_nored);
                break;

            case EMPTY_MESSAGE:
                layout.setEmptyText(context.getString(R.string.placeholder_empty_message));
                layout.setEmptyImage(R.drawable.default_icon_nomessage);
                break;

            case EMPTY_NOTICE:
                layout.setEmptyText(context.getString(R.string.placeholder_empty_notice));
                layout.setEmptyImage(R.drawable.default_icon_nonotice);
                layout.setStatus(PlaceholderLayout.EMPTY);
                break;

            case EMPTY_PRODUCT:
                layout.setEmptyText(context.getString(R.string.placeholder_empty_product));
                layout.setEmptyImage(R.drawable.nodata_icon_trilateral);
                break;

            case EMPTY_RECORD:
                layout.setEmptyText(context.getString(R.string.placeholder_empty_record));
                layout.setEmptyImage(R.drawable.default_icon_norecord);
                break;

            case EMPTY_CARD:
                layout.setEmptyText(context.getString(R.string.placeholder_empty_card));
                layout.setEmptyImage(R.drawable.placeholder_empty_card);
                break;

            case PlaceholderLayout.EMPTY:
            default:
                layout.setEmptyText(context.getString(R.string.placeholder_empty));
                layout.setEmptyImage(R.drawable.placeholder_empty);
                break;
        }
        layout.setStatus(PlaceholderLayout.EMPTY);
    }
}
