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
   /*         Offer offer = new Offer();
            offer.setTitle("Crime #");
            offer.setDescription("  121");
            offer.setAdvancedDescription("  121");
            mOffers.add(offer);
        Offer offer2 = new Offer();
        offer.setTitle("Crime #");
        offer.setDescription("  121");
        offer.setAdvancedDescription("  121");
        mOffers.add(offer2);
        Offer offer3 = new Offer();
        offer.setTitle("Crime #");
        offer.setDescription("  121");
        offer.setAdvancedDescription("  121");
        mOffers.add(offer3);*/

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
