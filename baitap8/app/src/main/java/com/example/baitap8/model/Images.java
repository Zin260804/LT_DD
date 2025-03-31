package com.example.baitap8.model;

public class Images {
    private int imagesId;
    private String imageUrl;   // dùng cho ảnh online (từ API)

    public Images(int imagesId) {
        this.imagesId = imagesId;
    }

    public int getImagesId() {
        return imagesId;
    }

    public void setImagesId(int imagesId) {
        this.imagesId = imagesId;
    }

    public Images(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
