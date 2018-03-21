package com.xinzhen.xznongshang.module.main.dataMode;

import android.graphics.drawable.Drawable;

import lombok.Getter;

/**
 * Created by sense on 2018/3/19.
 */
@Getter
public class InviteGridItem {

    private String text;
    private Drawable drawable;

    public InviteGridItem() {
    }


    public InviteGridItem(Drawable drawable, String text) {
        this.text = text;
        this.drawable = drawable;
    }
}
