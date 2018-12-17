package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.base.R$styleable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.SignInButtonCreator;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements View$OnClickListener {
    @Retention(value=RetentionPolicy.SOURCE) @public interface ButtonSize {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface ColorScheme {
    }

    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zzbw;
    private View$OnClickListener zzbx;

    public SignInButton(Context arg2) {
        this(arg2, null);
    }

    public SignInButton(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public SignInButton(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.zzbx = null;
        TypedArray v2 = arg2.getTheme().obtainStyledAttributes(arg3, styleable.SignInButton, 0, 0);
        try {
            this.mSize = v2.getInt(styleable.SignInButton_buttonSize, 0);
            this.mColor = v2.getInt(styleable.SignInButton_colorScheme, 2);
        }
        catch(Throwable v3) {
            v2.recycle();
            throw v3;
        }

        v2.recycle();
        this.setStyle(this.mSize, this.mColor);
    }

    public final void onClick(View arg2) {
        if(this.zzbx != null && arg2 == this.zzbw) {
            this.zzbx.onClick(((View)this));
        }
    }

    public final void setColorScheme(int arg2) {
        this.setStyle(this.mSize, arg2);
    }

    public final void setEnabled(boolean arg2) {
        super.setEnabled(arg2);
        this.zzbw.setEnabled(arg2);
    }

    public final void setOnClickListener(View$OnClickListener arg1) {
        this.zzbx = arg1;
        if(this.zzbw != null) {
            this.zzbw.setOnClickListener(((View$OnClickListener)this));
        }
    }

    @Deprecated public final void setScopes(Scope[] arg2) {
        this.setStyle(this.mSize, this.mColor);
    }

    public final void setSize(int arg2) {
        this.setStyle(arg2, this.mColor);
    }

    public final void setStyle(int arg3, int arg4) {
        this.mSize = arg3;
        this.mColor = arg4;
        Context v3 = this.getContext();
        if(this.zzbw != null) {
            this.removeView(this.zzbw);
        }

        try {
            this.zzbw = SignInButtonCreator.createView(v3, this.mSize, this.mColor);
        }
        catch(RemoteCreatorException ) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            arg4 = this.mSize;
            int v0 = this.mColor;
            SignInButtonImpl v1 = new SignInButtonImpl(v3);
            v1.configure(v3.getResources(), arg4, v0);
            this.zzbw = ((View)v1);
        }

        this.addView(this.zzbw);
        this.zzbw.setEnabled(this.isEnabled());
        this.zzbw.setOnClickListener(((View$OnClickListener)this));
    }

    @Deprecated public final void setStyle(int arg1, int arg2, Scope[] arg3) {
        this.setStyle(arg1, arg2);
    }
}

