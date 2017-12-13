package com.example.a2106088.amaru;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by estudiante on 10/10/17.
 */

public class Post implements Serializable {

    private byte[] bites;
    private String message;

    public Post(byte[] bites, String message) {
        this.bites = bites;
        this.message = message;
    }

    public byte[] getBites() {
        return bites;
    }

    public void setBites(byte[] bites) {
        this.bites = bites;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
