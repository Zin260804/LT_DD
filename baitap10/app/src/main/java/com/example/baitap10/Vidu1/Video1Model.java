package com.example.baitap10.Vidu1;

import java.io.Serializable;

public class Video1Model implements Serializable {
    private String title;
    private String desc;
    private String url;

    // 💥 Constructor mặc định (bắt buộc với Firebase)


    public Video1Model() {
    }

    public Video1Model(String title, String desc, String url) {
        this.title = title;
        this.desc = desc;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }
}
