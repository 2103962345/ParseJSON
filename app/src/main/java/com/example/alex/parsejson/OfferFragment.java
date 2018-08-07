package com.example.alex.parsejson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

public class OfferFragment extends Fragment {
    private static final String ARG_OFFER_ID = "offer_id";

    private Offer mOffer;
    private TextView mTitleField;
    private TextView mDescriptionField;
    private Button mNextButton;
    private Button mAdvancedDescriptionButton;

    public static OfferFragment newInstance(UUID offerId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_OFFER_ID, offerId);

        OfferFragment fragment = new OfferFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID offerId = (UUID) getArguments().getSerializable(ARG_OFFER_ID);
        mOffer = OfferList.get(getActivity()).getOffer(offerId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offer, container, false);
        mTitleField = (TextView) v.findViewById(R.id.title);
        mTitleField.setText(mOffer.getTitle());
        mDescriptionField = (TextView) v.findViewById(R.id.description);
        mDescriptionField.setText(mOffer.getDescription());
        mAdvancedDescriptionButton = (Button) v.findViewById(R.id.advanced_description);
        mAdvancedDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mDescriptionField.setText(mOffer.getDescription()+"\n"+mOffer.getAdvancedDescription());
            }
        });

        mNextButton = (Button) v.findViewById(R.id.next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = WebViewActivity.newIntent(getActivity(), mOffer.getOfferLink());
                startActivity(i);

            }
        });

        return v;
    }
}
