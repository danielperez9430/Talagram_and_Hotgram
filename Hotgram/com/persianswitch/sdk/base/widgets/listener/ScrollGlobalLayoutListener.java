package com.persianswitch.sdk.base.widgets.listener;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ScrollView;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScrollGlobalLayoutListener implements ViewTreeObserver$OnGlobalLayoutListener {
    private final AtomicBoolean a;
    private final ScrollView b;

    public ScrollGlobalLayoutListener(ScrollView arg3) {
        super();
        this.a = new AtomicBoolean(false);
        this.b = arg3;
    }

    public static void a(ScrollView arg2) {
        if(arg2 == null) {
            return;
        }

        arg2.getViewTreeObserver().addOnGlobalLayoutListener(new ScrollGlobalLayoutListener(arg2));
    }

    public void onGlobalLayout() {
        // Method was not decompiled
    }
}

