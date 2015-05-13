package com.example.lp_eng_ggn_068.testapp;

import android.graphics.Bitmap;

public class Singleton {
    private static Singleton mInstance = null;

    private Bitmap click;
    public static Singleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public Bitmap getClick(){
        return this.click;
    }

    public void setClick(Bitmap bitmap){
        click = bitmap;
    }
}