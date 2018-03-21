package com.xinzhen.xznongshang.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.xz.wireless.tools.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class XZPermissionUtil {

    private static int mRequestCode = 0;

    public static void requestPermissions(Activity activity, String[] permission) {
        request(activity, permission);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static void request(Object object, String[] permissions) {
        if (isOverMarshmallow()) {
            List<String> deniedPermissions = getDeniedPermissions(getContext(object), permissions);
            if (deniedPermissions.size() > 0) {
                if (object instanceof Activity) {
                    ((Activity) object).requestPermissions(deniedPermissions
                            .toArray(new String[deniedPermissions.size()]), mRequestCode);
                } else if (object instanceof android.app.Fragment) {
                    ((android.app.Fragment) object).requestPermissions(deniedPermissions
                            .toArray(new String[deniedPermissions.size()]), mRequestCode);
                } else if (object instanceof android.support.v4.app.Fragment) {
                    ((android.support.v4.app.Fragment) object).requestPermissions(deniedPermissions
                            .toArray(new String[deniedPermissions.size()]), mRequestCode);
                } else {
                    mRequestCode = -1;
                }
            }
        }
    }

    /**
     * 获取权限列表中所有需要授权的权限
     *
     * @param context     上下文
     * @param permissions 权限列表
     * @return
     */
    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        try {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                    deniedPermissions.add(permission);
                }
            }
        } catch (RuntimeException e) {
            Logger.d("logger","error",e);
        }
        return deniedPermissions;
    }

    /**
     * 获取上下文
     */
    private static Context getContext(Object object) {
        Context context;
        if (object instanceof android.app.Fragment) {
            context = ((android.app.Fragment) object).getActivity();
        } else if (object instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) object).getActivity();
        } else {
            context = (Activity) object;
        }
        return context;
    }


    /**
     * 检查所有的权限是否已经被授权
     *
     * @param permissions 权限列表
     * @return
     */
    public static boolean checkAllPermissions(Context context, String... permissions) {
        if (isOverMarshmallow()) {
            try {
                for (String permission : permissions) {
                    if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                        return false;
                    }
                }
            } catch (RuntimeException e) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断当前手机API版本是否 >= 6.0
     */
    private static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
