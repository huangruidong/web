package com.xinzhen.xznongshang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.xinzhen.xznongshang.common.BaseAct;
import com.xinzhen.xznongshang.common.BaseParams;
import com.xinzhen.xznongshang.common.Constant;
import com.xinzhen.xznongshang.module.main.ui.InviteAct;
import com.xinzhen.xznongshang.router.RouterUrl;
import com.xinzhen.xznongshang.utils.MyGestureListener;
import com.xz.wireless.tools.log.Logger;
import com.xz.wireless.tools.utils.ToastUtil;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;

/**
 * Created by sense on 2018/3/7.
 * App入口
 */

public class MainAct extends BaseAct {
    private static final String TAG = "MainAct";
    private BridgeWebView mWebView;
    private ProgressBar mProgressbar;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "MainAct is onCreate");
        setContentView(R.layout.act_main);
        mWebView = findViewById(R.id.main_wv_webview);
        mProgressbar = findViewById(R.id.main_pb_percent);

        initWebView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                    BaseParams.isNetwork = false;
                    ARouter.getInstance().build(RouterUrl.XZ_NO_NETWORK).navigation();
                }else {
                    BaseParams.isNetwork = true;
                }
            }
        }, intentFilter);
    }

    String mUrl = "file:///android_asset/test.html";

    private void initWebView() {
        mWebView.setDefaultHandler(new DefaultHandler());
        WebSettings wbSet = mWebView.getSettings();
        wbSet.setSupportZoom(false);

        // 适应分辨率
        //wbSet.setUseWideViewPort(true);
        wbSet.setLoadWithOverviewMode(true);
        wbSet.setJavaScriptEnabled(true);
        wbSet.setUserAgentString("Android xzhujin");
        CookieManager.getInstance().setAcceptCookie(true);
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //mWebView.loadUrl(url);
                switch (mathceStr(url)) {
                    case "login":
                        ARouter.getInstance().build(RouterUrl.USER_LOGIN).navigation();
                        //    startActivity(new Intent(MainAct.this, LoginAct.class));
                        break;
                    case "exit":
                        Constant.cookieStore.clear();
                        ToastUtil.toast("登出成功");
                        mWebView.loadUrl(mUrl);
                        break;
                    case "mine":
                        ARouter.getInstance().build(RouterUrl.MINE_ACCOUNT_SECURITY).navigation();
                        //   startActivity(new Intent(MainAct.this, AccountSecurityAct.class));
                        break;
                    case "share":
                        ARouter.getInstance().build(RouterUrl.MINE_INVITE).navigation();

                        startActivity(new Intent(MainAct.this, InviteAct.class));
                        break;
                    case "map":
                        double LATITUDE_A = 28.1903;  //起点纬度
                        double LONGTITUDE_A = 113.031738;  //起点经度
                        setUpBaiduAPPByName();
                        break;
                }
                return super.shouldOverrideUrlLoading(view, url) || !mathceStr(url).equals("-1");
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                syncCookie();
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                ToastUtil.toast("网络错误，请重试！");
                super.onReceivedError(view, request, error);
            }
        });


        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 100) {
                    mProgressbar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    mProgressbar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    Logger.d("Main", "newProgress:" + newProgress);
                    mProgressbar.setProgress(newProgress);//设置进度值
                }
                super.onProgressChanged(view, newProgress);
            }
        });

    }

    private void setUpBaiduAPPByName() {
        try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=万家丽国际Mall&destination=东郡华城广场|A座&mode=driving&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            if (isInstallByread("com.baidu.BaiduMap")) {
                startActivity(intent);
                Log.e(TAG, "百度地图客户端已经安装");
            } else {
                Log.e(TAG, "没有安装百度地图客户端");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    private String mFilter[] = {"login", "exit", "mine", "share", "map"};

    //url拦截
    private String mathceStr(String url) {
        for (int i = 0; i < mFilter.length; i++) {
            if (url.contains(mFilter[i])) {
                Logger.d("Main", "mFileter:" + mFilter[i]);
                return mFilter[i];
            }
        }
        return "-1";
    }

    //设置cookie
    private void syncCookie() {
        HashMap<String, List<Cookie>> cookies = Constant.cookieStore;
        if (cookies == null) return;

        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();// 移除旧的[可以省略]

        for (Map.Entry<String, List<Cookie>> entry : cookies.entrySet()) {
            String url = entry.getKey();
            for (int i = 0; i < entry.getValue().size(); i++) {
                String cookie = entry.getValue().get(i).toString();
                cookieManager.setCookie(url, cookie);
            }
        }
        CookieSyncManager.getInstance().sync();
    }

    private static final int FINGER_TAP = 2;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.d("qqqqqqq", "按下");
                break;
            case MotionEvent.ACTION_UP:
                Logger.d("qqqqqqq", "释放");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Logger.d("qqqqqqq", "多指按下");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Logger.d("qqqqqqq", "多指释放");
                break;
            //if (event.getPointerCount()==FINGER_TAP)
        }
        return true;
    }

    private final long DELAY_TIME = 2000;
    private long mFirstTime = 0;

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        long secondTime = System.currentTimeMillis();
        if (secondTime - mFirstTime < DELAY_TIME) {
            System.exit(0);
        } else {
            ToastUtil.toast(getResources().getString(R.string.app_exit));
            mFirstTime = System.currentTimeMillis();
        }
    }
}

