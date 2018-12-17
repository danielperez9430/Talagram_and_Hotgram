package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff$Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.a;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.base.R$color;
import com.google.android.gms.base.R$drawable;
import com.google.android.gms.base.R$string;
import com.google.android.gms.common.util.DeviceProperties;

public final class SignInButtonImpl extends Button {
    public SignInButtonImpl(Context arg2) {
        this(arg2, null);
    }

    public SignInButtonImpl(Context arg2, AttributeSet arg3) {
        super(arg2, arg3, 16842824);
    }

    public final void configure(Resources arg5, int arg6, int arg7) {
        this.setTypeface(Typeface.DEFAULT_BOLD);
        this.setTextSize(14f);
        int v0 = ((int)(arg5.getDisplayMetrics().density * 48f + 0.5f));
        this.setMinHeight(v0);
        this.setMinWidth(v0);
        v0 = SignInButtonImpl.zza(arg7, drawable.common_google_signin_btn_icon_dark, drawable.common_google_signin_btn_icon_light, drawable.common_google_signin_btn_icon_light);
        int v1 = SignInButtonImpl.zza(arg7, drawable.common_google_signin_btn_text_dark, drawable.common_google_signin_btn_text_light, drawable.common_google_signin_btn_text_light);
        int v2 = 32;
        switch(arg6) {
            case 0: 
            case 1: {
                goto label_32;
            }
            case 2: {
                goto label_33;
            }
        }

        StringBuilder v7 = new StringBuilder(v2);
        v7.append("Unknown button size: ");
        v7.append(arg6);
        throw new IllegalStateException(v7.toString());
    label_32:
        v0 = v1;
    label_33:
        Drawable v0_1 = a.g(arg5.getDrawable(v0));
        a.a(v0_1, arg5.getColorStateList(color.common_google_signin_btn_tint));
        a.a(v0_1, PorterDuff$Mode.SRC_ATOP);
        this.setBackgroundDrawable(v0_1);
        this.setTextColor(Preconditions.checkNotNull(arg5.getColorStateList(SignInButtonImpl.zza(arg7, color.common_google_signin_btn_text_dark, color.common_google_signin_btn_text_light, color.common_google_signin_btn_text_light))));
        CharSequence v7_1 = null;
        switch(arg6) {
            case 0: {
                goto label_63;
            }
            case 1: {
                goto label_61;
            }
            case 2: {
                goto label_59;
            }
        }

        v7 = new StringBuilder(v2);
        v7.append("Unknown button size: ");
        v7.append(arg6);
        throw new IllegalStateException(v7.toString());
    label_59:
        this.setText(v7_1);
        goto label_66;
    label_61:
        arg6 = string.common_signin_button_text_long;
        goto label_64;
    label_63:
        arg6 = string.common_signin_button_text;
    label_64:
        this.setText(arg5.getString(arg6));
    label_66:
        this.setTransformationMethod(((TransformationMethod)v7_1));
        if(DeviceProperties.isWearable(this.getContext())) {
            this.setGravity(19);
        }
    }

    public final void configure(Resources arg2, SignInButtonConfig arg3) {
        this.configure(arg2, arg3.getButtonSize(), arg3.getColorScheme());
    }

    private static int zza(int arg0, int arg1, int arg2, int arg3) {
        switch(arg0) {
            case 0: {
                return arg1;
            }
            case 1: {
                return arg2;
            }
            case 2: {
                return arg3;
            }
        }

        StringBuilder v3 = new StringBuilder(33);
        v3.append("Unknown color scheme: ");
        v3.append(arg0);
        throw new IllegalStateException(v3.toString());
        return arg3;
    }
}

