package com.example.admin.androidtvn;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleBackgroundManager {
 
    private static final String TAG = SimpleBackgroundManager.class.getSimpleName();
 
    private final int DEFAULT_BACKGROUND_RES_ID = R.drawable.default_background;
    private static final int BACKGROUND_UPDATE_DELAY = 300;
    private static Drawable mDefaultBackground;
    private final Handler mHandler = new Handler();
    private Activity mActivity;
    private DisplayMetrics mMetrics;
    private BackgroundManager mBackgroundManager;
    private Timer mBackgroundTimer;
    private String mBackgroundURI;
 
    public SimpleBackgroundManager(Activity activity) {
        mActivity = activity;
        prepareBackgroundManager();
    }

    private void prepareBackgroundManager() {

        mBackgroundManager = BackgroundManager.getInstance(mActivity);
        mBackgroundManager.attach(mActivity.getWindow());

        mDefaultBackground = ContextCompat.getDrawable(mActivity, R.drawable.default_background);
        mMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }
 
    public void clearBackground() {
        mBackgroundManager.setDrawable(mDefaultBackground);
    }

    private void updateBackground(String uri) {
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
        Glide.with(mActivity)
                .load(uri)
                .centerCrop()
                .error(mDefaultBackground)
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                            GlideAnimation<? super GlideDrawable>
                                    glideAnimation) {
                        mBackgroundManager.setDrawable(resource);
                    }
                });
        mBackgroundTimer.cancel();
    }

    public void startBackgroundTimer(String backgroundURI) {
        mBackgroundURI = backgroundURI;
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
    }

    private class UpdateBackgroundTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    updateBackground(mBackgroundURI);
                }
            });
        }
    }
 
}