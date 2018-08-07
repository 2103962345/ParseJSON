package com.example.alex.parsejson;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OfferList {
    private static OfferList sOfferList;
    private List<Offer> mOffers;

    public static OfferList get(Context context) {
        if (sOfferList == null) {
            sOfferList = new OfferList(context);
        }

        return sOfferList;
    }

    private OfferList(Context context) {
        mOffers = new ArrayList<>();
   

    }

    public void setOffers(List<Offer> offers) {
        mOffers=offers;
    }
    public List<Offer> getOffers() {
        return mOffers;
    }

    public Offer getOffer(UUID id) {
        for (Offer offer : mOffers) {
            if (offer.getUId().equals(id)) {
                return offer;
            }
        }

        return null;
    }
}
