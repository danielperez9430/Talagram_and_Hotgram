package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build$VERSION;
import android.support.v7.a.a$j;
import android.text.Layout$Alignment;
import android.text.StaticLayout$Builder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class y {
    private static final RectF a;
    private static ConcurrentHashMap b;
    private int c;
    private boolean d;
    private float e;
    private float f;
    private float g;
    private int[] h;
    private boolean i;
    private TextPaint j;
    private final TextView k;
    private final Context l;

    static {
        y.a = new RectF();
        y.b = new ConcurrentHashMap();
    }

    y(TextView arg3) {
        super();
        this.c = 0;
        this.d = false;
        this.e = -1f;
        this.f = -1f;
        this.g = -1f;
        this.h = new int[0];
        this.i = false;
        this.k = arg3;
        this.l = this.k.getContext();
    }

    void a(int arg2, float arg3) {
        Resources v0 = this.l == null ? Resources.getSystem() : this.l.getResources();
        this.a(TypedValue.applyDimension(arg2, arg3, v0.getDisplayMetrics()));
    }

    void a(int arg4) {
        if(this.k()) {
            switch(arg4) {
                case 0: {
                    goto label_26;
                }
                case 1: {
                    goto label_12;
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Unknown auto-size text type: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        label_26:
            this.j();
            return;
        label_12:
            DisplayMetrics v4 = this.l.getResources().getDisplayMetrics();
            this.a(TypedValue.applyDimension(2, 12f, v4), TypedValue.applyDimension(2, 112f, v4), 1f);
            if(this.i()) {
                this.f();
            }
        }
    }

    void a(int arg2, int arg3, int arg4, int arg5) {
        if(this.k()) {
            DisplayMetrics v0 = this.l.getResources().getDisplayMetrics();
            this.a(TypedValue.applyDimension(arg5, ((float)arg2), v0), TypedValue.applyDimension(arg5, ((float)arg3), v0), TypedValue.applyDimension(arg5, ((float)arg4), v0));
            if(this.i()) {
                this.f();
            }
        }
    }

    void a(AttributeSet arg7, int arg8) {
        int v4;
        TypedArray v7 = this.l.obtainStyledAttributes(arg7, j.AppCompatTextView, arg8, 0);
        if(v7.hasValue(j.AppCompatTextView_autoSizeTextType)) {
            this.c = v7.getInt(j.AppCompatTextView_autoSizeTextType, 0);
        }

        float v0 = -1f;
        float v8 = v7.hasValue(j.AppCompatTextView_autoSizeStepGranularity) ? v7.getDimension(j.AppCompatTextView_autoSizeStepGranularity, v0) : -1f;
        float v1 = v7.hasValue(j.AppCompatTextView_autoSizeMinTextSize) ? v7.getDimension(j.AppCompatTextView_autoSizeMinTextSize, v0) : -1f;
        float v3 = v7.hasValue(j.AppCompatTextView_autoSizeMaxTextSize) ? v7.getDimension(j.AppCompatTextView_autoSizeMaxTextSize, v0) : -1f;
        if(v7.hasValue(j.AppCompatTextView_autoSizePresetSizes)) {
            v4 = v7.getResourceId(j.AppCompatTextView_autoSizePresetSizes, 0);
            if(v4 > 0) {
                TypedArray v4_1 = v7.getResources().obtainTypedArray(v4);
                this.a(v4_1);
                v4_1.recycle();
            }
        }

        v7.recycle();
        if(!this.k()) {
            this.c = 0;
        }
        else if(this.c == 1) {
            if(!this.i) {
                DisplayMetrics v7_1 = this.l.getResources().getDisplayMetrics();
                v4 = 2;
                if(Float.compare(v1, v0) == 0) {
                    v1 = TypedValue.applyDimension(v4, 12f, v7_1);
                }

                if(v3 == v0) {
                    v3 = TypedValue.applyDimension(v4, 112f, v7_1);
                }

                if(v8 == v0) {
                    v8 = 1f;
                }

                this.a(v1, v3, v8);
            }

            this.i();
        }
    }

    int a() {
        return this.c;
    }

    void a(int[] arg6, int arg7) {
        if(this.k()) {
            int v0 = arg6.length;
            int v1 = 0;
            if(v0 > 0) {
                int[] v2 = new int[v0];
                if(arg7 == 0) {
                    v2 = Arrays.copyOf(arg6, v0);
                }
                else {
                    DisplayMetrics v3 = this.l.getResources().getDisplayMetrics();
                    while(v1 < v0) {
                        v2[v1] = Math.round(TypedValue.applyDimension(arg7, ((float)arg6[v1]), v3));
                        ++v1;
                    }
                }

                this.h = this.a(v2);
                if(this.h()) {
                    goto label_36;
                }

                StringBuilder v0_1 = new StringBuilder();
                v0_1.append("None of the preset sizes is valid: ");
                v0_1.append(Arrays.toString(arg6));
                throw new IllegalArgumentException(v0_1.toString());
            }
            else {
                this.i = false;
            }

        label_36:
            if(!this.i()) {
                return;
            }

            this.f();
        }
    }

    private int a(RectF arg6) {
        int v0 = this.h.length;
        if(v0 != 0) {
            --v0;
            int v1 = 1;
            int v2 = 0;
            while(v1 <= v0) {
                v2 = (v1 + v0) / 2;
                if(this.a(this.h[v2], arg6)) {
                    int v4 = v2 + 1;
                    v2 = v1;
                    v1 = v4;
                    continue;
                }

                --v2;
                v0 = v2;
            }

            return this.h[v2];
        }

        throw new IllegalStateException("No available text sizes to choose from.");
    }

    private boolean a(int arg6, RectF arg7) {
        CharSequence v0 = this.k.getText();
        TransformationMethod v1 = this.k.getTransformationMethod();
        if(v1 != null) {
            CharSequence v1_1 = v1.getTransformation(v0, this.k);
            if(v1_1 != null) {
                v0 = v1_1;
            }
        }

        int v3 = -1;
        int v1_2 = Build$VERSION.SDK_INT >= 16 ? this.k.getMaxLines() : -1;
        if(this.j == null) {
            this.j = new TextPaint();
        }
        else {
            this.j.reset();
        }

        this.j.set(this.k.getPaint());
        this.j.setTextSize(((float)arg6));
        Object v6 = this.a(this.k, "getLayoutAlignment", Layout$Alignment.ALIGN_NORMAL);
        StaticLayout v6_1 = Build$VERSION.SDK_INT >= 23 ? this.a(v0, ((Layout$Alignment)v6), Math.round(arg7.right), v1_2) : this.a(v0, ((Layout$Alignment)v6), Math.round(arg7.right));
        if(v1_2 != v3 && (v6_1.getLineCount() > v1_2 || v6_1.getLineEnd(v6_1.getLineCount() - 1) != v0.length())) {
            return 0;
        }

        if((((float)v6_1.getHeight())) > arg7.bottom) {
            return 0;
        }

        return 1;
    }

    private StaticLayout a(CharSequence arg12, Layout$Alignment arg13, int arg14) {
        boolean v2;
        float v1;
        float v0;
        if(Build$VERSION.SDK_INT >= 16) {
            v0 = this.k.getLineSpacingMultiplier();
            v1 = this.k.getLineSpacingExtra();
            v2 = this.k.getIncludeFontPadding();
        }
        else {
            v0 = this.a(this.k, "getLineSpacingMultiplier", Float.valueOf(1f)).floatValue();
            v1 = this.a(this.k, "getLineSpacingExtra", Float.valueOf(0f)).floatValue();
            v2 = this.a(this.k, "getIncludeFontPadding", Boolean.valueOf(true)).booleanValue();
        }

        float v8 = v0;
        float v9 = v1;
        boolean v10 = v2;
        return new StaticLayout(arg12, this.j, arg14, arg13, v8, v9, v10);
    }

    private Object a(Object arg5, String arg6, Object arg7) {
        try {
            arg5 = this.a(arg6).invoke(arg5);
        }
        catch(Throwable v5) {
        }
        catch(Exception v5_1) {
            try {
                Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + arg6 + "() method", ((Throwable)v5_1));
                arg5 = arg7;
            }
            catch(Throwable v5) {
                throw v5;
            }
        }

        return arg5;
    }

    private StaticLayout a(CharSequence arg5, Layout$Alignment arg6, int arg7, int arg8) {
        Object v0 = this.a(this.k, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        StaticLayout$Builder v5 = StaticLayout$Builder.obtain(arg5, 0, arg5.length(), this.j, arg7).setAlignment(arg6).setLineSpacing(this.k.getLineSpacingExtra(), this.k.getLineSpacingMultiplier()).setIncludePad(this.k.getIncludeFontPadding()).setBreakStrategy(this.k.getBreakStrategy()).setHyphenationFrequency(this.k.getHyphenationFrequency());
        if(arg8 == -1) {
            arg8 = 2147483647;
        }

        return v5.setMaxLines(arg8).setTextDirection(((TextDirectionHeuristic)v0)).build();
    }

    private Method a(String arg5) {
        Method v0_2;
        try {
            Object v0_1 = y.b.get(arg5);
            if(v0_1 == null) {
                v0_2 = TextView.class.getDeclaredMethod(arg5);
                if(v0_2 != null) {
                    v0_2.setAccessible(true);
                    y.b.put(arg5, v0_2);
                }
            }
        }
        catch(Exception v0) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + arg5 + "() method", ((Throwable)v0));
            return null;
        }

        return v0_2;
    }

    private void a(float arg4) {
        if(arg4 != this.k.getPaint().getTextSize()) {
            this.k.getPaint().setTextSize(arg4);
            boolean v4 = Build$VERSION.SDK_INT >= 18 ? this.k.isInLayout() : false;
            if(this.k.getLayout() == null) {
                return;
            }

            this.d = false;
            try {
                Method v0_1 = this.a("nullLayouts");
                if(v0_1 == null) {
                    goto label_30;
                }

                v0_1.invoke(this.k);
            }
            catch(Exception v0) {
                Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", ((Throwable)v0));
            }

        label_30:
            if(!v4) {
                this.k.requestLayout();
            }
            else {
                this.k.forceLayout();
            }

            this.k.invalidate();
        }
    }

    private void a(float arg3, float arg4, float arg5) {
        if(arg3 > 0f) {
            if(arg4 > arg3) {
                if(arg5 > 0f) {
                    this.c = 1;
                    this.f = arg3;
                    this.g = arg4;
                    this.e = arg5;
                    this.i = false;
                    return;
                }

                StringBuilder v4 = new StringBuilder();
                v4.append("The auto-size step granularity (");
                v4.append(arg5);
                v4.append("px) is less or equal to (0px)");
                throw new IllegalArgumentException(v4.toString());
            }

            StringBuilder v0 = new StringBuilder();
            v0.append("Maximum auto-size text size (");
            v0.append(arg4);
            v0.append("px) is less or equal to minimum auto-size ");
            v0.append("text size (");
            v0.append(arg3);
            v0.append("px)");
            throw new IllegalArgumentException(v0.toString());
        }

        StringBuilder v5 = new StringBuilder();
        v5.append("Minimum auto-size text size (");
        v5.append(arg3);
        v5.append("px) is less or equal to (0px)");
        throw new IllegalArgumentException(v5.toString());
    }

    private void a(TypedArray arg5) {
        int v0 = arg5.length();
        int[] v1 = new int[v0];
        if(v0 > 0) {
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                v1[v2] = arg5.getDimensionPixelSize(v2, -1);
            }

            this.h = this.a(v1);
            this.h();
        }
    }

    private int[] a(int[] arg7) {
        int v0 = arg7.length;
        if(v0 == 0) {
            return arg7;
        }

        Arrays.sort(arg7);
        ArrayList v1 = new ArrayList();
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            int v4 = arg7[v3];
            if(v4 > 0 && Collections.binarySearch(((List)v1), Integer.valueOf(v4)) < 0) {
                ((List)v1).add(Integer.valueOf(v4));
            }
        }

        if(v0 == ((List)v1).size()) {
            return arg7;
        }

        int v7 = ((List)v1).size();
        int[] v0_1 = new int[v7];
        while(v2 < v7) {
            v0_1[v2] = ((List)v1).get(v2).intValue();
            ++v2;
        }

        return v0_1;
    }

    int b() {
        return Math.round(this.e);
    }

    int c() {
        return Math.round(this.f);
    }

    int d() {
        return Math.round(this.g);
    }

    int[] e() {
        return this.h;
    }

    void f() {
        if(!this.g()) {
            return;
        }

        if(this.d) {
            if(this.k.getMeasuredHeight() > 0) {
                if(this.k.getMeasuredWidth() <= 0) {
                }
                else {
                    int v0 = this.a(this.k, "getHorizontallyScrolling", Boolean.valueOf(false)).booleanValue() ? 1048576 : this.k.getMeasuredWidth() - this.k.getTotalPaddingLeft() - this.k.getTotalPaddingRight();
                    int v1 = this.k.getHeight() - this.k.getCompoundPaddingBottom() - this.k.getCompoundPaddingTop();
                    if(v0 <= 0) {
                        return;
                    }

                    if(v1 <= 0) {
                        return;
                    }

                    RectF v3 = y.a;
                    __monitor_enter(v3);
                    try {
                        y.a.setEmpty();
                        y.a.right = ((float)v0);
                        y.a.bottom = ((float)v1);
                        float v0_2 = ((float)this.a(y.a));
                        if(v0_2 != this.k.getTextSize()) {
                            this.a(0, v0_2);
                        }

                        __monitor_exit(v3);
                        goto label_63;
                    label_60:
                        __monitor_exit(v3);
                    }
                    catch(Throwable v0_1) {
                        goto label_60;
                    }

                    throw v0_1;
                }
            }

            return;
        }

    label_63:
        this.d = true;
    }

    boolean g() {
        boolean v0 = !this.k() || this.c == 0 ? false : true;
        return v0;
    }

    private boolean h() {
        int v0 = this.h.length;
        boolean v3 = v0 > 0 ? true : false;
        this.i = v3;
        if(this.i) {
            this.c = 1;
            this.f = ((float)this.h[0]);
            this.g = ((float)this.h[v0 - 1]);
            this.e = -1f;
        }

        return this.i;
    }

    private boolean i() {
        int v1 = 0;
        if(!this.k() || this.c != 1) {
            this.d = false;
        }
        else {
            if(!this.i || this.h.length == 0) {
                float v0 = ((float)Math.round(this.f));
                int v3 = 1;
                while(Math.round(this.e + v0) <= Math.round(this.g)) {
                    ++v3;
                    v0 += this.e;
                }

                int[] v0_1 = new int[v3];
                float v4 = this.f;
                while(v1 < v3) {
                    v0_1[v1] = Math.round(v4);
                    v4 += this.e;
                    ++v1;
                }

                this.h = this.a(v0_1);
            }

            this.d = true;
        }

        return this.d;
    }

    private void j() {
        this.c = 0;
        this.f = -1f;
        this.g = -1f;
        this.e = -1f;
        this.h = new int[0];
        this.d = false;
    }

    private boolean k() {
        boolean v0 = !(this.k instanceof l) ? true : false;
        return v0;
    }
}

