package com.xinzhen.xznongshang.common;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.xinzhen.xznongshang.utils.PermissionCheck;


/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/4/25 15:25
 * <p/>
 * Description: Fragment基类
 */
public class BaseFrag extends Fragment {
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheck.getInstance().onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
