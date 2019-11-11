package com.dev.ext.asansor;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FontApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Pacifico.ttc")  // default font
                .setFontAttrId(R.attr.fontPath)
                .build()

        );

    }
}

