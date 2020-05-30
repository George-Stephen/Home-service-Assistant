package com.moringa.homeservice.Objects;

public class GResults {
    private String title ;
    private String Url ;

    public GResults(String title, String url) {
        this.title = title;
        Url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
