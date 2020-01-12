package com.example.android.udacity_baking_app.utils;

import retrofit2.HttpException;

public class NetworkUtils {

    public final static String SERVER_ERROR = "Unable to resolve host";

    public static boolean isHttpError404(Throwable t) {
        return t instanceof HttpException && ((HttpException) t).code() == 404;
    }
}
