package com.example.administrator.application1116;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/11/16.
 */

public class image {
    private String name;
    private String size;
    private Bitmap data;

    public image() {
    }

    public image(String name, String size, Bitmap data) {
        this.name = name;
        this.size = size;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Bitmap getData() {
        return data;
    }

    public void setData(Bitmap data) {
        this.data = data;
    }
}
