package com.example.alex.parsejson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewFragment extends Fragment {
    private String offerURL;
    private static final String ARG_OFFER_URL = "offer_url";

    public static WebViewFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_OFFER_URL, url);

        WebViewFragment fragment = new WebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offerURL = (String) getArguments().getSerializable(ARG_OFFER_URL);
    }
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
                View v=inflater.inflate(R.layout.webview,container,false);
                if(offerURL!=null){
                    WebView wv = (WebView) v.findViewById(R.id.webview);
                    wv.getSettings().setJavaScriptEnabled(true);

                    wv.setWebViewClient(new WebViewClient());
                    wv.loadUrl(offerURL);
                }
                return v;
}
}
