package com.xinzhen.xznongshang.module.main.dataMode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lombok.Data;
import lombok.Getter;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/12/12 15:14
 * <p/>
 * Description:
 */
public class ServerRec {
    /**
     * 静态资源服务器地址
     */
    private String imageServer;
    /**
     * 移动端服务器地址
     */
    private String mobileServer;
    private List<RepayStyle> repayStyles;

    public String getImageServer() {
        return imageServer;
    }

    public String getMobileServer() {
        return mobileServer;
    }

    public List<RepayStyle> getRepayStyles() {
        return repayStyles;
    }

    public TreeMap<Integer, String> getStyles() {
        TreeMap<Integer, String> map = new TreeMap<>();
        for (RepayStyle style : repayStyles) {
            map.put(style.getSort(), style.getItemName());
        }
        return map;
    }

    public String[] getTypes() {
        TreeMap<Integer, String> map = getStyles();
        String[] types = new String[map.size()];
        Iterator it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            types[i++] = entry.getValue().toString();
        }
        return types;
    }

    @Getter
    private class RepayStyle {
        /**
         * 收益名称
         */
        private String itemName;
        /**
         * 收益序号
         */
        private int sort;
    }
}
