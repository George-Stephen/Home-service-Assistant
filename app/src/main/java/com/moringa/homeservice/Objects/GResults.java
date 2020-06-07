package com.moringa.homeservice.Objects;

import org.parceler.Parcel;


public class GResults {
    private String title ;
    private String link ;

    public GResults() {
    }

    public GResults(String title, String link) {
        this.title = title;
        link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setUrl(String link) {
        link = link;
    }
}
