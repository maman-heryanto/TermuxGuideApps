package com.t.termuxgapps;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class MainDiscussion extends Fragment {

    public MainDiscussion(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_discussion, container, false);
        getActivity().setTitle("Discussion");

        //fab hide
        FloatingActionButton floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }

        WebView chatAngo = (WebView) view.findViewById(R.id.webView);

        chatAngo.getSettings().setLoadsImagesAutomatically(true);
        chatAngo.getSettings().setJavaScriptEnabled(true);
        chatAngo.getSettings().setAllowContentAccess(true);
        chatAngo.getSettings().setUseWideViewPort(true);

        chatAngo.loadUrl("file:///android_asset/chatango.html");

        chatAngo.getSettings().setUseWideViewPort(true);
        chatAngo.getSettings().setLoadWithOverviewMode(true);
        chatAngo.getSettings().setDomStorageEnabled(true);
        chatAngo.clearView();
        chatAngo.setHorizontalScrollBarEnabled(false);
        chatAngo.getSettings().setAppCacheEnabled(true);
        chatAngo.getSettings().setDatabaseEnabled(true);

        chatAngo.setVerticalScrollBarEnabled(false);
        chatAngo.getSettings().setBuiltInZoomControls(true);
        chatAngo.getSettings().setDisplayZoomControls(false);
        chatAngo.getSettings().setAllowFileAccess(true);
        chatAngo.getSettings().setPluginState(WebSettings.PluginState.OFF);
        chatAngo.setScrollbarFadingEnabled(false);
        chatAngo.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        chatAngo.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        chatAngo.setWebViewClient(new WebViewClient());
        chatAngo.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        chatAngo.setInitialScale(1);

        chatAngo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return false;
            }
        });


        return view;
    }

}