package com.persianswitch.sdk.base.widgets.edittext;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.widget.TextView;

public class AutoResizeTextView extends TextView {
    interface SizeTester {
        int a(int arg1, RectF arg2);
    }

    private final RectF a;
    private final SparseIntArray b;
    private final SizeTester c;
    private float d;
    private float e;
    private float f;
    private float g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private TextPaint l;

    public AutoResizeTextView(Context arg3) {
        this(arg3, null, 0);
    }

    public AutoResizeTextView(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.a = new RectF();
        this.b = new SparseIntArray();
        this.e = 1f;
        this.f = 0f;
        this.j = true;
        this.k = false;
        this.g = TypedValue.applyDimension(2, 12f, this.getResources().getDisplayMetrics());
        this.d = this.getTextSize();
        if(this.i == 0) {
            this.i = -1;
        }

        this.c = new SizeTester() {
            final RectF a;

            public int a(int arg12, RectF arg13) {
                float v0;
                RectF v12;
                AutoResizeTextView.a(this.b).setTextSize(((float)arg12));
                String v1 = this.b.getText().toString();
                int v8 = 0;
                arg12 = AutoResizeTextView.b(this.b) == 1 ? 1 : 0;
                int v10 = -1;
                if(arg12 != 0) {
                    this.a.bottom = AutoResizeTextView.a(this.b).getFontSpacing();
                    v12 = this.a;
                    v0 = AutoResizeTextView.a(this.b).measureText(v1);
                }
                else {
                    StaticLayout v12_1 = new StaticLayout(((CharSequence)v1), AutoResizeTextView.a(this.b), AutoResizeTextView.c(this.b), Layout$Alignment.ALIGN_NORMAL, AutoResizeTextView.d(this.b), AutoResizeTextView.e(this.b), true);
                    if(AutoResizeTextView.b(this.b) != v10 && v12_1.getLineCount() > AutoResizeTextView.b(this.b)) {
                        return 1;
                    }

                    this.a.bottom = ((float)v12_1.getHeight());
                    int v0_1 = -1;
                    while(v8 < v12_1.getLineCount()) {
                        if((((float)v0_1)) < v12_1.getLineRight(v8) - v12_1.getLineLeft(v8)) {
                            v0_1 = (((int)v12_1.getLineRight(v8))) - (((int)v12_1.getLineLeft(v8)));
                        }

                        ++v8;
                    }

                    v12 = this.a;
                    v0 = ((float)v0_1);
                }

                v12.right = v0;
                this.a.offsetTo(0f, 0f);
                if(arg13.contains(this.a)) {
                    return v10;
                }

                return 1;
            }
        };
        this.k = true;
    }

    public AutoResizeTextView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    static TextPaint a(AutoResizeTextView arg0) {
        return arg0.l;
    }

    private int a(int arg3, int arg4, SizeTester arg5, RectF arg6) {
        if(!this.j) {
            return this.b(arg3, arg4, arg5, arg6);
        }

        String v0 = this.getText().toString();
        int v0_1 = v0 == null ? 0 : v0.length();
        int v1 = this.b.get(v0_1);
        if(v1 != 0) {
            return v1;
        }

        arg3 = this.b(arg3, arg4, arg5, arg6);
        this.b.put(v0_1, arg3);
        return arg3;
    }

    private void a() {
        this.b();
    }

    private void a(int arg4) {
        super.setTextSize(0, ((float)this.a(arg4, ((int)this.d), this.c, this.a)));
    }

    static int b(AutoResizeTextView arg0) {
        return arg0.getSupportedMaxLines();
    }

    private int b(int arg4, int arg5, SizeTester arg6, RectF arg7) {
        --arg5;
        int v0 = arg4;
        while(arg4 <= arg5) {
            v0 = arg4 + arg5 >>> 1;
            int v1 = arg6.a(v0, arg7);
            if(v1 < 0) {
                int v2 = v0 + 1;
                v0 = arg4;
                arg4 = v2;
                continue;
            }
            else if(v1 > 0) {
                --v0;
                arg5 = v0;
                continue;
            }

            return v0;
        }

        return v0;
    }

    private void b() {
        if(!this.k) {
            return;
        }

        int v0 = ((int)this.g);
        int v1 = this.getMeasuredHeight() - this.getCompoundPaddingBottom() - this.getCompoundPaddingTop();
        this.h = this.getMeasuredWidth() - this.getCompoundPaddingLeft() - this.getCompoundPaddingRight();
        if(this.h <= 0) {
            return;
        }

        this.a.right = ((float)this.h);
        this.a.bottom = ((float)v1);
        this.a(v0);
    }

    static int c(AutoResizeTextView arg0) {
        return arg0.h;
    }

    static float d(AutoResizeTextView arg0) {
        return arg0.e;
    }

    static float e(AutoResizeTextView arg0) {
        return arg0.f;
    }

    private int getSupportedMaxLines() {
        return this.i;
    }

    protected void onSizeChanged(int arg2, int arg3, int arg4, int arg5) {
        this.b.clear();
        super.onSizeChanged(arg2, arg3, arg4, arg5);
        if(arg2 != arg4 || arg3 != arg5) {
            this.a();
        }
    }

    protected void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        super.onTextChanged(arg1, arg2, arg3, arg4);
        this.a();
    }

    public void setEnableSizeCache(boolean arg1) {
        this.j = arg1;
        this.b.clear();
        this.b();
    }

    public void setLineSpacing(float arg1, float arg2) {
        super.setLineSpacing(arg1, arg2);
        this.e = arg2;
        this.f = arg1;
    }

    public void setLines(int arg1) {
        super.setLines(arg1);
        this.i = arg1;
        this.a();
    }

    public void setMaxLines(int arg1) {
        super.setMaxLines(arg1);
        this.i = arg1;
        this.a();
    }

    public void setMinTextSize(float arg1) {
        this.g = arg1;
        this.a();
    }

    public void setSingleLine() {
        super.setSingleLine();
        this.i = 1;
        this.a();
    }

    public void setSingleLine(boolean arg1) {
        super.setSingleLine(arg1);
        int v1 = arg1 ? 1 : -1;
        this.i = v1;
        this.a();
    }

    public void setTextSize(float arg1) {
        this.d = arg1;
        this.b.clear();
        this.b();
    }

    public void setTextSize(int arg2, float arg3) {
        Context v0 = this.getContext();
        Resources v0_1 = v0 == null ? Resources.getSystem() : v0.getResources();
        this.d = TypedValue.applyDimension(arg2, arg3, v0_1.getDisplayMetrics());
        this.b.clear();
        this.b();
    }

    public void setTypeface(Typeface arg3) {
        if(this.l == null) {
            this.l = new TextPaint(this.getPaint());
        }

        this.l.setTypeface(arg3);
        this.b();
        super.setTypeface(arg3);
    }
}

