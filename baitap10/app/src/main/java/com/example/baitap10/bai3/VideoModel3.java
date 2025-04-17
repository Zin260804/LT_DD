package com.example.baitap10.bai3;

import java.io.Serializable;

public class VideoModel3 implements Serializable {
    private String title, description, videoUrl, userEmail, userId;
    private int likes, dislikes;

    public VideoModel3() {}  // Required for Firebase

    public VideoModel3(String title, String description, String videoUrl,String userEmail
                       , String userId, int likes, int dislikes) {
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
        this.userEmail = userEmail;
        this.userId = userId;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    // Getters and setters...

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
