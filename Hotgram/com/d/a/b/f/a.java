package com.d.a.b.f;

import android.graphics.Bitmap;
import android.view.View;
import com.d.a.b.a.b;

public interface a {
    void onLoadingCancelled(String arg1, View arg2);

    void onLoadingComplete(String arg1, View arg2, Bitmap arg3);

    void onLoadingFailed(String arg1, View arg2, b arg3);

    void onLoadingStarted(String arg1, View arg2);
}

