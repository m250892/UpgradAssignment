package com.manoj.upgradassignment.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {

    private static final float LOW_MEMORY_THRESHOLD_PERCENTAGE = 5;
    private static ImageLoader instance;
    private Context context;

    private ImageLoader(Context context) {
        this.context = context;
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Image loader init not called");
        }
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new ImageLoader(context);
        }
    }

    public void loadImage(String url, ImageView imageView) {
        loadImage(url, imageView, null);
    }

    public void loadImage(String url, ImageView imageView, Drawable backupDrawable) {
        loadImage(url, imageView, backupDrawable, backupDrawable);
    }

    public void loadImage(String url, ImageView imageView, Drawable drawableWhileLoading,
                          Drawable drawableOnFailure) {
        if (isAdequateMemoryAvailable()) {
            if (imageView != null && !TextUtils.isEmpty(url)) {
                Picasso.with(context)
                        .load(url)
                        .error(drawableOnFailure)
                        .placeholder(drawableWhileLoading)
                        .into(imageView);
            } else {
                setDrawable(imageView, drawableOnFailure);
            }
        } else {
            setDrawable(imageView, drawableOnFailure);
        }
    }

    void setDrawable(ImageView imageView, Drawable drawable) {
        if (imageView != null && drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    private static boolean isAdequateMemoryAvailable() {
        boolean isAdequateMemoryAvailable = true;
        // Get app memory info
        long total = Runtime.getRuntime().maxMemory();
        long used = Runtime.getRuntime().totalMemory();
        float percentAvailable = 100f * (1f - ((float) used / total));
        if (percentAvailable <= LOW_MEMORY_THRESHOLD_PERCENTAGE) {
            isAdequateMemoryAvailable = false;
            handleLowMemory();
        }
        return isAdequateMemoryAvailable;
    }

    private static void handleLowMemory() {
        System.gc();
    }
}

