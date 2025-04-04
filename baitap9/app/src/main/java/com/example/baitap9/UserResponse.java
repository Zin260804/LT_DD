package com.example.baitap9;

import java.util.List;

public class UserResponse {
    private boolean success;
    private String message;
    private User result;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getResult() {
        return result;
    }
}
