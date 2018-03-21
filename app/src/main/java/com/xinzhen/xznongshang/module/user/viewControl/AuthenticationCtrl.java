package com.xinzhen.xznongshang.module.user.viewControl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinzhen.xznongshang.R;
import com.xinzhen.xznongshang.common.BorrowBespeakRec;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.common.RegionBean;
import com.xinzhen.xznongshang.databinding.ActUserAuthenticationBinding;
import com.xinzhen.xznongshang.module.user.dataModel.AuthenticationSub;
import com.xinzhen.xznongshang.module.user.viewModel.AuthenticationVM;
import com.xinzhen.xznongshang.network.AppResultCode;
import com.xinzhen.xznongshang.network.XZApiManager;
import com.xinzhen.xznongshang.network.XZApiManagerCallBack;
import com.xinzhen.xznongshang.utils.ImaZipUtil;
import com.xinzhen.xznongshang.utils.ProgressUtil;
import com.xinzhen.xznongshang.utils.XZPermissionUtil;
import com.xz.wireless.network.entity.HttpResult;
import com.xz.wireless.tools.utils.AndroidUtil;
import com.xz.wireless.tools.utils.ContextHolder;
import com.xz.wireless.tools.utils.IDCardValidator;
import com.xz.wireless.tools.utils.TextUtil;
import com.xz.wireless.tools.utils.ToastUtil;
import com.xz.wireless.views.appbar.TitleBar;

import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.ImageCropBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.xinzhen.xznongshang.common.Constant.PERMISSIONS;


/**
 * Created by liufang on 2017/11/6.
 */

public class AuthenticationCtrl {
    private AuthenticationVM authenticationVM;
    private boolean isRealnameProcess;
    private ActUserAuthenticationBinding binding;
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
    private String codeAddress;
    Activity activity;
    private String dateStr;

    public AuthenticationCtrl(ActUserAuthenticationBinding binding, boolean isRealnameProcess, Activity activity) {
        this.binding = binding;
        this.activity = activity;
        authenticationVM = new AuthenticationVM();
        this.isRealnameProcess = isRealnameProcess;
        binding.headView1.setVisibility(isRealnameProcess ? View.VISIBLE : View.GONE);
        binding.headView2.setVisibility(isRealnameProcess ? View.VISIBLE : View.GONE);
        if (this.isRealnameProcess) {
            // 取消左侧点击按钮事件及去除图标
            binding.toolBar.setLeftListener(null);
            binding.toolBar.addAction(new TitleBar.TextAction("跳过") {
                @Override
                public void performAction(View view) {
                    Activity activity = AndroidUtil.getActivity(view);
                    activity.setResult(1);
                    activity.finish();
                }
            });
        }
        reqData();
    }

    public void reqData() {
        XZApiManager.getInstance().bespeak(new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                BorrowBespeakRec rec = ((Response<HttpResult<BorrowBespeakRec>>) response).body().getData();
                regionInit(AndroidUtil.getActivity(binding.toolBar), rec.getAreaJson());
            }

            @Override
            public void onFailed(Response response) {
                super.onFailed(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    /*
    *提交实名认证信息
     */
    public void submitAuthentication(View view) {
        if (TextUtils.isEmpty(authenticationVM.getRealName())) {
            ToastUtil.toast(R.string.authentication_real_name_hint);
        } else if (!new IDCardValidator().isValidatedAllIdcard(authenticationVM.getIdNumber())) {
            ToastUtil.toast(R.string.validate_id_card);
        } else if (!isAdult(authenticationVM.getIdNumber())) {
            ToastUtil.toast(R.string.validate_id_card_underage);
        } else if (TextUtil.isEmpty(codeAddress)) {
            ToastUtil.toast("请选择通讯地址");
        } else if (TextUtil.isEmpty(dateStr)) {
            ToastUtil.toast("请选择证件有效期");
        } else {
            if (TextUtil.isEmpty(Constant.cardFrontAddress)) {
                ToastUtil.toast("请选择身份证正面");
            } else if (TextUtil.isEmpty(Constant.cardBackAddress)) {
                ToastUtil.toast("请选择身份证反面");
            } else {
                ProgressUtil.getInstance().show(AndroidUtil.getActivity(view));
                XZApiManager.getInstance().getCardUrl(new XZApiManagerCallBack() {
                    @Override
                    public void onSuccess(Response response) {
                        Response<ResponseBody> cardResponse = (Response<ResponseBody>) response;
                        try {
                            String json = cardResponse.body().string();
                            JSONObject object = new JSONObject(json);
                            int resCode = object.getInt("resCode");
                            if (resCode == AppResultCode.SUCCESS) {
                                JSONObject jsonObject = object.getJSONObject("resData");
                                Constant.authenticationUrl = jsonObject.getString("uploadServerUrl");
                                //发送消息
                                //第一个是要传递的数据，第二个参数是标记
                                EventBus.getDefault().post(Constant.NUMBER_1, "realname");//去上传身份证
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailed(Response response) {
                        super.onFailed(response);
                        ProgressUtil.getInstance().dismiss();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        super.onFailure(call, t);
                        ProgressUtil.getInstance().dismiss();
                    }
                });
            }
        }
    }

    public void realName() {
        XZApiManager.getInstance().authentication(new AuthenticationSub(authenticationVM.getRealName(), authenticationVM.getIdNumber(),
                codeAddress, binding.xzAuthenticationTime.getText().toString(), Constant.cardBackPath, Constant.cardFrontPath), new XZApiManagerCallBack() {
            @Override
            public void onSuccess(Response response) {
                ProgressUtil.getInstance().dismiss();
                try {
                    String json = ((Response<ResponseBody>) response).body().string();
                    JSONObject object = new JSONObject(json);
                    int resCode = object.getInt("resCode");
                    if (resCode == AppResultCode.SUCCESS) {
                        ToastUtil.toast("实名认证成功");
                        if (isRealnameProcess) {

                        } else {
                        }
                    } else {
                        ToastUtil.toast(object.getString("resMsg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(Response response) {
                ProgressUtil.getInstance().dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                ToastUtil.toast(R.string.authentication_fail);
                ProgressUtil.getInstance().dismiss();
            }
        });
    }


    public boolean isAdult(String IdNumber) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(IdNumber.substring(6, 14)) + 180000 <= Integer.parseInt(formatter.format(new Date(System.currentTimeMillis()))) ? true : false;
    }

    public void chooseCity(View view) {
        if (regionPickerView != null && !regionPickerView.isShowing()) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }
            regionPickerView.show();
        }
    }

    public void chooseDate(View view) {
        //选择日期
        initTimePicker();
    }

    public void chooseFrontCard(View view) {
        //选择身份证的正面
        XZPermissionUtil.requestPermissions(AndroidUtil.getActivity(view), PERMISSIONS);
        showImage(Constant.NUMBER_0);
    }

    public void chooseBackCard(View view) {
        //选择身份证的反面
        XZPermissionUtil.requestPermissions(AndroidUtil.getActivity(view), PERMISSIONS);
        showImage(Constant.NUMBER_1);
    }

    /**
     * 从相册中选择图片
     *
     * @param type type:0 身份证正面  type:1 身份证反面
     */
    public void showImage(final int type) {
        RxGalleryFinal.with(AndroidUtil.getActivity(binding.toolBar)).image().radio()
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        //图片选择结果
                        if (imageRadioResultEvent != null) {
                            ImageCropBean result = imageRadioResultEvent.getResult();
                            if (result != null) {
                                String path;
                                if (result.getThumbnailBigPath() != null) {
                                    path = result.getThumbnailBigPath();
                                } else {
                                    path = result.getOriginalPath();
                                }
                                if (0 == type) {
                                    Glide.with(AndroidUtil.getActivity(binding.toolBar))
                                            .load(path)
                                            .into(binding.xzAuthenticationFrontcard);
                                    compressCardImage(0, path);
                                } else if (1 == type) {
                                    Glide.with(AndroidUtil.getActivity(binding.toolBar))
                                            .load(path)
                                            .into(binding.xzAuthenticationBackcard);
                                    compressCardImage(1, path);
                                }

                            }


                        }
                    }
                })
                .openGallery();
    }

    public void compressCardImage(int type, String imagePath) {
        String filePath;
        if (type == Constant.NUMBER_0) {
            //身份证正面
            filePath = Constant.CARD_FRONT_FILE_PATH;
        } else {
            //身份证反面
            filePath = Constant.CARD_BACK_FILE_PATH;
        }
        try {
            FileInputStream fis = new FileInputStream(imagePath);
            Bitmap bitmapOld = BitmapFactory.decodeStream(fis);
            Bitmap bitmapNew = ImaZipUtil.zipPic(bitmapOld, 800, 480);
            ImaZipUtil.savaBitmap(bitmapNew, filePath);
            if (type == Constant.NUMBER_0) {
                //身份证正面
                Constant.cardFrontAddress = filePath;
            } else {
                //身份证反面
                Constant.cardBackAddress = filePath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTimePicker() {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(AndroidUtil.getActivity(binding.toolBar), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                dateStr = getTime(date);
                binding.xzAuthenticationTime.setText(dateStr);
                binding.xzAuthenticationTime.setTextColor(ContextHolder.getContext().getResources().getColor(R.color.text_black));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setTitleText("选择日期")
                .build();
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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

                if (null != province && null != city) {
                    name = province.getLabel() + "," + city.getLabel();
                    codeAddress = province.getValue() + "," + city.getValue();
                }

                if (null != area && !TextUtils.isEmpty(area.getLabel())) {
                    name += "," + area.getLabel();
                    codeAddress += "," + area.getValue();
                }

                binding.xzAuthenticationAddress.setTextColor(ContextHolder.getContext().getResources().getColor(R.color.text_color));
                binding.xzAuthenticationAddress.setText(name);
            }
        })
                .build();
        regionPickerView.setPicker(provinceList, cityList, areaList);
    }


    public AuthenticationVM getAuthenticationVM() {
        return authenticationVM;
    }
}
