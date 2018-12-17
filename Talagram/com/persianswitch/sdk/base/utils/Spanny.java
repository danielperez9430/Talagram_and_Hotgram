package com.persianswitch.sdk.base.utils;

import android.text.Editable;
import android.text.SpannableStringBuilder;

public class Spanny extends SpannableStringBuilder {
    public interface GetSpan {
    }

    private int a;

    public Spanny() {
        super("");
        this.a = 33;
    }

    private void a(Object arg2, int arg3, int arg4) {
        this.setSpan(arg2, arg3, arg4, this.a);
    }

    public Spanny a(CharSequence arg1) {
        super.append(arg1);
        return this;
    }

    public Spanny a(CharSequence arg2, Object arg3) {
        this.a(arg2);
        this.a(arg3, this.length() - arg2.length(), this.length());
        return this;
    }

    public Spanny a(CharSequence arg6, Object[] arg7) {
        this.a(arg6);
        int v0 = arg7.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.a(arg7[v1], this.length() - arg6.length(), this.length());
        }

        return this;
    }

    public Editable append(CharSequence arg1) {
        return this.a(arg1);
    }

    public SpannableStringBuilder append(CharSequence arg1) {
        return this.a(arg1);
    }

    public Appendable append(CharSequence arg1) {
        return this.a(arg1);
    }
}

