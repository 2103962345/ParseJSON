package com.example.alex.parsejson;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class OfferActivity extends SingleFragmentActivity {

    private static final String EXTRA_OFFER_ID =
            "com.example.alex.parsejson.offer_id";

    public static Intent newIntent(Context packageContext, UUID offerId) {
        Intent intent = new Intent(packageContext, OfferActivity.class);
        intent.putExtra(EXTRA_OFFER_ID, offerId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID offerId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_OFFER_ID);
        return OfferFragment.newInstance(offerId);
    }
}
