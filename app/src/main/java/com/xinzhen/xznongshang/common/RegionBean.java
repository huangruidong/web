package com.xinzhen.xznongshang.common;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.ArrayList;

/**
 * Author: Chenming
 *
 * Date: 2016/11/22 下午2:56
 * <p/>
 * Description:
 */
public class RegionBean implements IPickerViewData {
    /** 省市区-code */
    private String                value;
    /** 省市区-显示名称 */
    private String                label;
    /**  */
    private ArrayList<RegionBean> children;

    public RegionBean() {
    }

    public RegionBean(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<RegionBean> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<RegionBean> children) {
        this.children = children;
    }

    public String getPickerViewText() {
        return label.replaceAll("(?:自治区|自治州)", "");
    }
}
