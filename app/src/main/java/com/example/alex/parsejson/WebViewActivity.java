package com.example.alex.parsejson;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class WebViewActivity extends SingleFragmentActivity {

    private static final String EXTRA_OFFER_URL =
            "com.example.alex.parsejson.offer_url";

    public static Intent newIntent(Context packageContext, String offerURL) {
        Intent intent = new Intent(packageContext, WebViewActivity.class);
        intent.putExtra(EXTRA_OFFER_URL, offerURL);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String offerURL = (String) getIntent()
                .getSerializableExtra(EXTRA_OFFER_URL);
        return WebViewFragment.newInstance(offerURL);
    }
}