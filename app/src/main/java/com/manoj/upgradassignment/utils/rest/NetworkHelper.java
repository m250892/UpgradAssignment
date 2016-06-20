package com.manoj.upgradassignment.utils.rest;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.concurrent.TimeUnit;

public class NetworkHelper {

    private static final String TAG = NetworkHelper.class.getCanonicalName();
    private static NetworkHelper instance;
    private RequestQueue mRequestQueue;
    private final Context context;

    public static void init(Context context) {
        if (instance == null) {
            synchronized (NetworkHelper.class) {
                if (instance == null) {
                    instance = new NetworkHelper(context);
                }
            }
        }
    }

    public static NetworkHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Network helper not initialze");
        }
        return instance;
    }


    public NetworkHelper(Context context) {
        this.context = context;
    }

    public DefaultRetryPolicy retryPolicy() {
        return new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(20),
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        req.setRetryPolicy(retryPolicy());
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void cancelAll() {
        cancelPendingRequests(TAG);
    }
}
