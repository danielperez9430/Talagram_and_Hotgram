package com.persianswitch.sdk.base.manager;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class FontManager {
    private static FontManager a;
    private final Context b;
    private Typeface c;
    private Typeface d;

    private FontManager(Context arg2) {
        super();
        this.b = arg2;
        this.c = Typeface.createFromAsset(this.b.getAssets(), "nyekan.ttf");
        this.d = null;
    }

    private Typeface a(String arg2) {
        if("fa".equals(arg2)) {
            return this.c;
        }

        return this.d;
    }

    public static FontManager a(Context arg1) {
        if(FontManager.a == null) {
            FontManager.a = new FontManager(arg1);
        }

        return FontManager.a;
    }

    public static void a(View arg1) {
        if(arg1 != null) {
            FontManager.a(arg1.getContext()).b(arg1);
        }
    }

    public void a(View arg4, Typeface arg5) {
        if((arg4 instanceof TextView)) {
            ((TextView)arg4).setTypeface(arg5);
        }
        else if((arg4 instanceof ViewGroup)) {
            int v0 = 0;
            while(true) {
                View v1 = arg4;
                if(v0 < ((ViewGroup)v1).getChildCount()) {
                    this.a(((ViewGroup)v1).getChildAt(v0), arg5);
                    ++v0;
                    continue;
                }

                return;
            }
        }
    }

    public void b(View arg2) {
        this.a(arg2, this.a(LanguageManager.a(this.b).c()));
    }
}

