package com.example.alex.parsejson;

import java.util.UUID;

public class Offer  {

    private UUID mUId;
    private String mId;
        private String mTitle;
        private String mDescription;
        private String mAdvancedDescription;
        private String mOfferLink;

    public Offer() {
        mUId = UUID.randomUUID();
    }

    public UUID getUId() {
        return mUId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getAdvancedDescription() {
        return mAdvancedDescription;
    }

    public void setAdvancedDescription(String advancedDescription) {
        mAdvancedDescription = advancedDescription;
    }

    public String getOfferLink() {
        return mOfferLink;
    }

    public void setOfferLink(String offerLink) {
        mOfferLink = offerLink;
    }

}
