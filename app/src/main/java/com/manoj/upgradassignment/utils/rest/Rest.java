package com.manoj.upgradassignment.utils.rest;

import android.content.Context;

import com.android.volley.Request;

public class Rest {

    static Rest singleton = null;

    private Context context;

    public Rest(Context context) {
        this.context = context;
    }

    public synchronized static Rest init(Context context) {
        if (singleton == null) {
            singleton = new Rest(context);
            NetworkHelper.init(context);
        }
        return singleton;
    }

    public static Context getContext() {
        if (singleton == null) {
            throw new IllegalArgumentException("Rest not init till now");
        }
        return singleton.context;
    }

    //Creating new get request
    public static NetworkManager.INetworkManagerBuilder GET() {
        return new NetworkManager.Builder().setMethod(Request.Method.GET);
    }

    public static void cancelPendingRequests() {

    }

}
