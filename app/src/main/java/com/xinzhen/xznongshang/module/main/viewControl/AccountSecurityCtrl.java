package com.xinzhen.xznongshang.module.main.viewControl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinzhen.xznongshang.common.BorrowBespeakRec;
import com.xinzhen.xznongshang.common.RegionBean;
import com.xinzhen.xznongshang.databinding.ActMineAccountSecurityBinding;
import com.xinzhen.xznongshang.module.main.dataMode.BasicInfoRec;
import com.xinzhen.xznongshang.module.main.dataMode.BorrowBespeakSub;
import com.xinzhen.xznongshang.module.main.logic.DynamicInfoLogic;
import com.xinzhen.xznongshang.module.main.ui.AccountSecurityAct;
import com.xinzhen.xznongshang.module.main.viewMode.AccountSecurityVM;
import com.xinzhen.xznongshang.module.user.dataModel.receive.OauthTokenMo;
import com.xinzhen.xznongshang.module.user.logic.UserLogic;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.router.RouterUrl;
import com.xinzhen.xznongshang.utils.Utils;
import com.xinzhen.xznongshang.utils.XZPermissionUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.gesture.logic.PhotographLogic;
import com.xz.wireless.tools.gesture.logic.SharedInfo;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.ConverterUtil;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Author: TinhoXu
 * <p>
 * Date: 2016/11/22 15:55
 * <p/>
 * Description:账户与安全页面控制器({@link AccountSecurityAct})
 */
public class AccountSecurityCtrl {
    public AccountSecurityVM securityVM;
    public BasicInfoRec rec;
    private ActMineAccountSecurityBinding binding;
    private final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private BorrowBespeakSub bespeakSub;

    /**
     * 所在城市选择框
     */
    private OptionsPickerView regionPickerView;
    /**
     * 所在城市 - 省数据源
     */
    private ArrayList<RegionBean> provinceList;
    /**
     * 所在城市 - 市数据源
     */
    private ArrayList<ArrayList<RegionBean>> cityList;
    /**
     * 所在城市 - 区数据源
     */
    private ArrayList<ArrayList<ArrayList<RegionBean>>> areaList;

    /**
     * 借款期限选择框
     */
    private OptionsPickerView limitTimePickerView;
    /**
     * 借款期限数据源
     */
    private ArrayList<String> limitTimeList;

    /**
     * startActivity requestCode
     */
    private final int requestCode = 0x22;
    private Activity mAct;

    public AccountSecurityCtrl(Activity act, ActMineAccountSecurityBinding binding) {
        this.mAct = act;
        this.binding = binding;
        securityVM = new AccountSecurityVM();
        bespeakSub = new BorrowBespeakSub();
        reqData();
        sexInit(mAct);
    }

    /**
     * 获取用户基础信息
     */
    public void reqData() {
        DynamicInfoLogic.getInstance().reqBasicInfo(null, new DynamicInfoLogic.IBasicInfo() {
            @Override
            public void callback(BasicInfoRec rec) {
                AccountSecurityCtrl.this.rec = rec;
                convert();
            }
        });

        /**
         * 所在城市
         */
        XZApiManager.getInstance().bespeak(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                BorrowBespeakRec rec = ((Response<HttpResult<BorrowBespeakRec>>) response).body().getData();
                regionInit(mAct, rec.getAreaJson());
                bespeakSub.setBorrowBespeakAddToken(rec.getBorrowBespeakAddToken());
                // 如果未登录，则需要提交__sid，避免表单重复提交提示
                if (null == SharedInfo.getInstance().getEntity(OauthTokenMo.class)) {
                    bespeakSub.set__sid(rec.get__sid());
                }
            }
        });
    }

    /**
     * 数据转换为viewModel
     */
    private void convert() {
        securityVM.setAvatarPath(rec.getAvatarPhoto());
        securityVM.setPhone(rec.getMobile());
//        securityVM.setCity(rec.get);
    }

    /**
     * 我的头像点击
     */
    public void avatarClick(View view) {
        XZPermissionUtil.requestPermissions(AndroidUtil.getActivity(view), PERMISSIONS);
        PhotographLogic.getInstance().obtain(view, System.currentTimeMillis() + "_avatar.jpg");
    }

    /**
     * 我的等级点击
     */
    public void vipClick(View view) {
        /*Context context = view.getContext();
        ARouter.getInstance().build(RouterUrl.COMMON_WEB_VIEW)
                .withString(BundleKeys.TITLE, context.getString(R.string.vip_title))
                .withString(BundleKeys.URL, BaseParams.URI + H5Service.API_VIP)
                .withString(BundleKeys.POST_DATA, UrlUtils.getInstance().signParams(new TreeMap<String, String>()))
                .withInt(BundleKeys.RES_INT, -1)
                .withString(BundleKeys.ID, "")
                .navigation();*/
    }

    /**
     * 绑定手机点击
     */
    public void phoneClick(View view) {
        /*if (!FunctionConfig.getInstance().isFunctionModifyPhoneNumberEnable())
            return;

        if (null != rec) {
            ARouter.getInstance().build(RouterUrl.MINE_MODIFY_PHONE)
                    .withString(BundleKeys.ID, rec.getMobile())
                    .navigation(AndroidUtil.getActivity(view), requestCode);
        }*/
    }

    /**
     * 修改昵称
     */
    public void nickNameClick(View view) {
        ARouter.getInstance().build(RouterUrl.MINE_MODIFY_NICKNAME).navigation();
    }

    /**
     * 修改性别
     */
    public void sexClick(View view) {
        if (limitTimePickerView != null && !limitTimePickerView.isShowing()) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }
            limitTimePickerView.show();
        }
    }

    /**
     * 所在城市
     */
    public void cityClick(View view) {
        if (regionPickerView != null && !regionPickerView.isShowing()) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }
            regionPickerView.show();
        }
    }


    /**
     * 修改密码
     */
    public void modifyPasswordClick(View view) {
        ARouter.getInstance().build(RouterUrl.USER_MODIFY_PASSWORD).navigation();
    }

    /**
     * 退出登录点击
     */
    public void signOutClick(View view) {
        UserLogic.signOutToLogin(AndroidUtil.getActivity(view));
    }

    /**
     * 所在城市 - 弹出框初始化
     */

    private void regionInit(Context context, String str) {

        ArrayList<RegionBean> regionBeanArrayList;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<RegionBean>>() {
        }.getType();
        regionBeanArrayList = gson.fromJson(str, type);

        provinceList = new ArrayList<>();
        cityList = new ArrayList<>();
        areaList = new ArrayList<>();

        if (regionBeanArrayList == null || regionBeanArrayList.size() == 0) {
            return;
        }

        // 省
        for (RegionBean provinceBean : regionBeanArrayList) {
            provinceList.add(provinceBean);
            // 市
            if (provinceBean.getChildren() != null) {
                ArrayList<RegionBean> cityTemp = new ArrayList<>();
                ArrayList<ArrayList<RegionBean>> areaTemp = new ArrayList<>();
                ArrayList<RegionBean> temp = new ArrayList<>();
                temp.add(new RegionBean("", ""));
                for (RegionBean cityBean : provinceBean.getChildren()) {
                    cityTemp.add(cityBean);
                    // 区
                    if (cityBean.getChildren() != null) {
                        areaTemp.add(cityBean.getChildren());
                    } else {
                        areaTemp.add(temp);
                    }
                }
                cityList.add(cityTemp);
                areaList.add(areaTemp);
            }
        }

        regionPickerView = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                RegionBean province = null;
                RegionBean city = null;
                RegionBean area = null;

                // 省
                if (null != provinceList && !provinceList.isEmpty()) {
                    province = provinceList.get(options1);
                }
                // 市
                if (null != cityList && !cityList.isEmpty()) {
                    city = cityList.get(options1).get(options2);
                }
                // 区
                if (null != areaList && !areaList.isEmpty()) {
                    area = areaList.get(options1).get(options2).get(options3);
                }

                String name = "";
                String code = "";

                if (null != province && null != city) {
                    name = province.getLabel() + "," + city.getLabel();
                    code = province.getValue() + "," + city.getValue();
                }

                if (null != area && !TextUtils.isEmpty(area.getLabel())) {
                    name += "," + area.getLabel();
                    code += "," + area.getValue();
                }

                securityVM.setCity(name);
                bespeakSub.setZone(code);
            }
        }).build();
        regionPickerView.setPicker(provinceList, cityList, areaList);
    }


    /**
     * 性别 - 弹出框初始化
     */
    private void sexInit(Context context) {
        limitTimeList = new ArrayList<>();
        limitTimeList.add("男");
        limitTimeList.add("女");
        limitTimePickerView = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                securityVM.setSex(limitTimeList.get(options1));
            }
        }).build();

        limitTimePickerView.setPicker(limitTimeList);
    }

    public void toImage(String imageServer, final File file) {
        /*String url = imageServer + H5Service.API_UPLOAD_AVATAR;
        Map<String, File> map = new HashMap<>();
        map.put("upload", file);
        XZApiManager.getInstance().doToImage(url, FileUploadUtil.getRequestMap(map), new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                Response<HttpResult<AvatarRec>> responseData = response;
                uploadAvatar(responseData.body().getData().getImgUrl(), file);
            }
        });
    }

    private void uploadAvatar(final String path, final File imageFile) {
        XZApiManager.getInstance().uploadAvatar(path, new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                ToastUtil.toast(((Response<HttpResult>) response).body().getMsg());
                binding.imageview.setImageDrawable(Drawable.createFromPath(imageFile.toString()));
            }
        });*/
    }

    public AccountSecurityVM getSecurityVM() {
        return securityVM;
    }
}
