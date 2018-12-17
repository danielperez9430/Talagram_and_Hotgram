package com.persianswitch.sdk.base;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public class ProgressDialogBuilder {
    private String a;
    private Typeface b;
    private Drawable c;

    public ProgressDialogBuilder() {
        super();
    }

    public ProgressDialog a() {
        ProgressDialog v0 = new ProgressDialog();
        v0.a(this.a);
        v0.a(this.b);
        v0.a(this.c);
        return v0;
    }
}

