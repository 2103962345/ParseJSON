package com.example.alex.parsejson;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ParserJSONFragment extends Fragment {
    private RecyclerView mOfferRecyclerView;
   // List<Offer> offers;
    //private List<Offer> mItems = new ArrayList<>();
    private OfferAdapter mAdapter;
    public static ParserJSONFragment newInstance() {
        return new ParserJSONFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     setRetainInstance(true);
        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_offer, container, false);

        mOfferRecyclerView = (RecyclerView) v.findViewById(R.id.offer_recycler_view);
        mOfferRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    private void updateUI() {
        OfferList offerList = OfferList.get(getActivity());
        List<Offer>  offers= offerList.getOffers();

        if (mAdapter == null) {
            mAdapter = new OfferAdapter(offers);
            mOfferRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class OfferHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Offer mOffer;
        private TextView mTitleTextView;
        private TextView mDescriptionTextView;

        public OfferHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_offer,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.description);
        }

        public void bind(Offer offer) {
            mOffer = offer;
            mTitleTextView.setText(offer.getTitle());
            mDescriptionTextView.setText(offer.getDescription());
        }
        @Override
        public void onClick(View v) {
            //Intent i = new Intent(getActivity(),OfferActivity.class);
            Intent i = OfferActivity.newIntent(getActivity(), mOffer.getUId());
            //i.putExtra("offer_id",mOffer.getId());
            startActivity(i);
        }
    }

    private class OfferAdapter extends RecyclerView.Adapter<OfferHolder> {

        private List<Offer> mOffers;

        public OfferAdapter(List<Offer> offers) {
            mOffers = offers;
        }

        @Override
        public OfferHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new OfferHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(OfferHolder offerHolder, int position) {
            Offer offer = mOffers.get(position);
            offerHolder.bind(offer);
        }

        @Override
        public int getItemCount() {
            return mOffers.size();
        }

    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<Offer>> {
        @Override
        protected List<Offer> doInBackground(Void... params) {
            return new JSONFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<Offer> items) {
            OfferList offerList = OfferList.get(getActivity());
           offerList.setOffers(items);
           if(isAdded()) {
               mAdapter = new OfferAdapter(items);
               mOfferRecyclerView.setAdapter(mAdapter);
               mAdapter.notifyDataSetChanged();
           }
        }
    }


}