package com.example.myapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public class BackgroundManager {

    private static final String TAG = BackgroundManager.class.getSimpleName();

    private final int DEFAULT_BACKGROUND_RES_ID = R.drawable.default_background;
    private static Drawable mDefaultBackground;

    private Activity mActivity;
    private android.support.v17.leanback.app.BackgroundManager mBackgroundManager;

    public BackgroundManager(Activity activity) {
        mActivity = activity;
        mDefaultBackground = activity.getDrawable(DEFAULT_BACKGROUND_RES_ID);
        mBackgroundManager = android.support.v17.leanback.app.BackgroundManager.getInstance(activity);
        mBackgroundManager.attach(activity.getWindow());
        activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
    }

    public void updateBackground(Drawable drawable) {

        mBackgroundManager.setDrawable(drawable);
    }

    public void clearBackground() {
        mBackgroundManager.setDrawable(mDefaultBackground);
    }
}
