package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.d;
import android.support.v4.view.t;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class ap extends ViewGroup {
    public class a extends ViewGroup$MarginLayoutParams {
        public float g;
        public int h;

        public a(Context arg3, AttributeSet arg4) {
            super(arg3, arg4);
            this.h = -1;
            TypedArray v3 = arg3.obtainStyledAttributes(arg4, j.LinearLayoutCompat_Layout);
            this.g = v3.getFloat(j.LinearLayoutCompat_Layout_android_layout_weight, 0f);
            this.h = v3.getInt(j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            v3.recycle();
        }

        public a(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.h = -1;
        }

        public a(int arg1, int arg2) {
            super(arg1, arg2);
            this.h = -1;
            this.g = 0f;
        }
    }

    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private int[] i;
    private int[] j;
    private Drawable k;
    private int l;
    private int m;
    private int n;
    private int o;

    public ap(Context arg2) {
        this(arg2, null);
    }

    public ap(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ap(Context arg5, AttributeSet arg6, int arg7) {
        super(arg5, arg6, arg7);
        this.a = true;
        int v1 = -1;
        this.b = v1;
        this.c = 0;
        this.e = 8388659;
        bk v5 = bk.a(arg5, arg6, j.LinearLayoutCompat, arg7, 0);
        int v6 = v5.a(j.LinearLayoutCompat_android_orientation, v1);
        if(v6 >= 0) {
            this.setOrientation(v6);
        }

        v6 = v5.a(j.LinearLayoutCompat_android_gravity, v1);
        if(v6 >= 0) {
            this.setGravity(v6);
        }

        boolean v6_1 = v5.a(j.LinearLayoutCompat_android_baselineAligned, true);
        if(!v6_1) {
            this.setBaselineAligned(v6_1);
        }

        this.g = v5.a(j.LinearLayoutCompat_android_weightSum, -1f);
        this.b = v5.a(j.LinearLayoutCompat_android_baselineAlignedChildIndex, v1);
        this.h = v5.a(j.LinearLayoutCompat_measureWithLargestChild, false);
        this.setDividerDrawable(v5.a(j.LinearLayoutCompat_divider));
        this.n = v5.a(j.LinearLayoutCompat_showDividers, 0);
        this.o = v5.e(j.LinearLayoutCompat_dividerPadding, 0);
        v5.a();
    }

    private void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
        arg1.layout(arg2, arg3, arg4 + arg2, arg5 + arg3);
    }

    int a(View arg1) {
        return 0;
    }

    int a(View arg1, int arg2) {
        return 0;
    }

    void a(int arg41, int arg42) {
        int v15_1;
        float v10_2;
        int v38;
        int v39;
        int v29;
        int v8;
        int v33;
        View v3_1;
        int v0_1;
        int v9;
        int v31;
        int v32;
        int v22;
        int v10;
        ap v7 = this;
        v7.f = 0;
        int v11 = this.getVirtualChildCount();
        int v12 = View$MeasureSpec.getMode(arg41);
        int v13 = View$MeasureSpec.getMode(arg42);
        int v14 = v7.b;
        boolean v15 = v7.h;
        float v0 = 0f;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v18 = 0;
        int v19 = 1;
        int v20 = 0;
        while(true) {
            v10 = 8;
            v22 = v4;
            if(v6 >= v11) {
                break;
            }

            View v4_1 = v7.b(v6);
            if(v4_1 == null) {
                v7.f += v7.d(v6);
                v32 = v11;
                v31 = v13;
                v4 = v22;
            }
            else {
                int v24 = v1;
                if(v4_1.getVisibility() == v10) {
                    v6 += v7.a(v4_1, v6);
                    v32 = v11;
                    v31 = v13;
                    v4 = v22;
                    v1 = v24;
                }
                else {
                    if(v7.c(v6)) {
                        v7.f += v7.m;
                    }

                    ViewGroup$LayoutParams v10_1 = v4_1.getLayoutParams();
                    float v25 = v0 + ((a)v10_1).g;
                    if(v13 != 1073741824 || ((a)v10_1).height != 0 || ((a)v10_1).g <= 0f) {
                        int v26 = v2;
                        if(((a)v10_1).height != 0 || ((a)v10_1).g <= 0f) {
                            v2 = -2147483648;
                        }
                        else {
                            ((a)v10_1).height = -2;
                            v2 = 0;
                        }

                        int v27 = v25 == 0f ? v7.f : 0;
                        v8 = v24;
                        v29 = v26;
                        v9 = v3;
                        View v30 = v4_1;
                        v32 = v11;
                        v31 = v13;
                        v13 = v22;
                        v33 = v5;
                        v11 = v6;
                        this.a(v4_1, v6, arg41, 0, arg42, v27);
                        v0_1 = v2;
                        if(v0_1 != -2147483648) {
                            ((a)v10_1).height = v0_1;
                        }

                        v0_1 = v30.getMeasuredHeight();
                        v3_1 = v30;
                        v7.f = Math.max(v7.f, v7.f + v0_1 + ((a)v10_1).topMargin + ((a)v10_1).bottomMargin + v7.b(v3_1));
                        if(v15) {
                            v0_1 = Math.max(v0_1, v9);
                            goto label_136;
                        }

                        v0_1 = v9;
                    }
                    else {
                        v7.f = Math.max(v7.f, ((a)v10_1).topMargin + v7.f + ((a)v10_1).bottomMargin);
                        v0_1 = v3;
                        v3_1 = v4_1;
                        v33 = v5;
                        v32 = v11;
                        v31 = v13;
                        v13 = v22;
                        v8 = v24;
                        v29 = v2;
                        v18 = 1;
                        v11 = v6;
                    }

                label_136:
                    if(v14 >= 0 && v14 == v11 + 1) {
                        v7.c = v7.f;
                    }

                    if(v11 < v14) {
                        if(((a)v10_1).g <= 0f) {
                        }
                        else {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won\'t work.  Either remove the weight, or don\'t set mBaselineAlignedChildIndex.");
                        }
                    }

                    if(v12 == 1073741824 || ((a)v10_1).width != -1) {
                        v1 = 0;
                    }
                    else {
                        v1 = 1;
                        v20 = 1;
                    }

                    v2 = ((a)v10_1).leftMargin + ((a)v10_1).rightMargin;
                    v4 = v3_1.getMeasuredWidth() + v2;
                    v5 = Math.max(v29, v4);
                    v6 = View.combineMeasuredStates(v8, v3_1.getMeasuredState());
                    v8 = v19 == 0 || ((a)v10_1).width != -1 ? 0 : 1;
                    if(((a)v10_1).g > 0f) {
                        if(v1 != 0) {
                        }
                        else {
                            v2 = v4;
                        }

                        v13 = Math.max(v13, v2);
                        v1 = v33;
                    }
                    else {
                        if(v1 == 0) {
                            v2 = v4;
                        }

                        v1 = v33;
                        v1 = Math.max(v1, v2);
                    }

                    v2 = v7.a(v3_1, v11) + v11;
                    v3 = v0_1;
                    v19 = v8;
                    v4 = v13;
                    v0 = v25;
                    v39 = v5;
                    v5 = v1;
                    v1 = v6;
                    v6 = v2;
                    v2 = v39;
                }
            }

            ++v6;
            v13 = v31;
            v11 = v32;
        }

        v8 = v1;
        v9 = v3;
        v1 = v5;
        v32 = v11;
        v31 = v13;
        v13 = v22;
        v5 = v2;
        if(v7.f > 0) {
            v2 = v32;
            if(v7.c(v2)) {
                v7.f += v7.m;
            }
        }
        else {
            v2 = v32;
        }

        if(v15) {
            v3 = v31;
            if(v3 != -2147483648 && v3 != 0) {
                goto label_260;
            }

            v7.f = 0;
            v4 = 0;
            goto label_232;
        }
        else {
            v3 = v31;
            goto label_260;
        label_232:
            while(v4 < v2) {
                View v6_1 = v7.b(v4);
                if(v6_1 == null) {
                    v6 = v7.f + v7.d(v4);
                    goto label_238;
                }
                else if(v6_1.getVisibility() == v10) {
                    v4 += v7.a(v6_1, v4);
                }
                else {
                    ViewGroup$LayoutParams v11_1 = v6_1.getLayoutParams();
                    v6 = Math.max(v7.f, v7.f + v9 + ((a)v11_1).topMargin + ((a)v11_1).bottomMargin + v7.b(v6_1));
                label_238:
                    v7.f = v6;
                }

                ++v4;
                v10 = 8;
            }
        }

    label_260:
        v7.f += this.getPaddingTop() + this.getPaddingBottom();
        v10 = v9;
        v6 = arg42;
        v4 = View.resolveSizeAndState(Math.max(v7.f, this.getSuggestedMinimumHeight()), v6, 0);
        v9 = (16777215 & v4) - v7.f;
        if(v18 == 0) {
            if(v9 != 0 && v0 > 0f) {
                goto label_306;
            }

            v0_1 = Math.max(v1, v13);
            if((v15) && v3 != 1073741824) {
                for(v1 = 0; v1 < v2; ++v1) {
                    v3_1 = v7.b(v1);
                    if(v3_1 != null) {
                        if(v3_1.getVisibility() == 8) {
                        }
                        else if(v3_1.getLayoutParams().g > 0f) {
                            v3_1.measure(View$MeasureSpec.makeMeasureSpec(v3_1.getMeasuredWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(v10, 1073741824));
                        }
                    }
                }
            }

            v1 = v8;
            v11 = arg41;
        }
        else {
        label_306:
            if(v7.g > 0f) {
                v0 = v7.g;
            }

            v7.f = 0;
            float v11_2 = v0;
            v0_1 = 0;
            v39 = v8;
            v8 = v1;
            v1 = v39;
            while(v0_1 < v2) {
                View v13_1 = v7.b(v0_1);
                if(v13_1.getVisibility() == 8) {
                    v38 = v3;
                    v10_2 = v11_2;
                }
                else {
                    ViewGroup$LayoutParams v14_1 = v13_1.getLayoutParams();
                    v10_2 = ((a)v14_1).g;
                    if(v10_2 > 0f) {
                        v15_1 = ((int)((((float)v9)) * v10_2 / v11_2));
                        int v34 = v9 - v15_1;
                        float v35 = v11_2 - v10_2;
                        v9 = ap.getChildMeasureSpec(arg41, this.getPaddingLeft() + this.getPaddingRight() + ((a)v14_1).leftMargin + ((a)v14_1).rightMargin, ((a)v14_1).width);
                        if(((a)v14_1).height == 0) {
                            v10 = 1073741824;
                            if(v3 != v10) {
                                goto label_354;
                            }
                            else if(v15_1 > 0) {
                            }
                            else {
                                goto label_357;
                            }
                        }
                        else {
                            v10 = 1073741824;
                        label_354:
                            v15_1 = v13_1.getMeasuredHeight() + v15_1;
                            if(v15_1 < 0) {
                            label_357:
                                v15_1 = 0;
                            }
                        }

                        v13_1.measure(v9, View$MeasureSpec.makeMeasureSpec(v15_1, v10));
                        v1 = View.combineMeasuredStates(v1, v13_1.getMeasuredState() & -256);
                        v9 = v34;
                        v10_2 = v35;
                    }
                    else {
                        v10_2 = v11_2;
                    }

                    int v36 = v1;
                    v15_1 = ((a)v14_1).leftMargin + ((a)v14_1).rightMargin;
                    v1 = v13_1.getMeasuredWidth() + v15_1;
                    v5 = Math.max(v5, v1);
                    int v37 = v1;
                    if(v12 != 1073741824) {
                        v38 = v3;
                        v3 = -1;
                        if(((a)v14_1).width == v3) {
                            v1 = 1;
                        }
                        else {
                            goto label_386;
                        }
                    }
                    else {
                        v38 = v3;
                        v3 = -1;
                    label_386:
                        v1 = 0;
                    }

                    if(v1 != 0) {
                    }
                    else {
                        v15_1 = v37;
                    }

                    v1 = Math.max(v8, v15_1);
                    v8 = v19 == 0 || ((a)v14_1).width != v3 ? 0 : 1;
                    v7.f = Math.max(v7.f, v7.f + v13_1.getMeasuredHeight() + ((a)v14_1).topMargin + ((a)v14_1).bottomMargin + v7.b(v13_1));
                    v19 = v8;
                    v8 = v1;
                    v1 = v36;
                }

                ++v0_1;
                v11_2 = v10_2;
                v3 = v38;
            }

            v11 = arg41;
            v7.f += this.getPaddingTop() + this.getPaddingBottom();
            v0_1 = v8;
        }

        if(v19 != 0 || v12 == 1073741824) {
            v0_1 = v5;
        }
        else {
        }

        v7.setMeasuredDimension(View.resolveSizeAndState(Math.max(v0_1 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), v11, v1), v4);
        if(v20 != 0) {
            v7.c(v2, v6);
        }
    }

    void a(View arg7, int arg8, int arg9, int arg10, int arg11, int arg12) {
        this.measureChildWithMargins(arg7, arg9, arg10, arg11, arg12);
    }

    void a(int arg18, int arg19, int arg20, int arg21) {
        int v0;
        ap v6 = this;
        int v7 = this.getPaddingLeft();
        int v2 = arg20 - arg18;
        int v8 = v2 - this.getPaddingRight();
        int v9 = v2 - v7 - this.getPaddingRight();
        int v10 = this.getVirtualChildCount();
        v2 = v6.e & 112;
        int v11 = v6.e & 8388615;
        if(v2 == 16) {
            v0 = (arg21 - arg19 - v6.f) / 2 + this.getPaddingTop();
        }
        else if(v2 != 80) {
            v0 = this.getPaddingTop();
        }
        else {
            v0 = this.getPaddingTop() + arg21 - arg19 - v6.f;
        }

        int v12;
        for(v12 = 0; v12 < v10; ++v12) {
            View v13 = v6.b(v12);
            if(v13 == null) {
                v0 += v6.d(v12);
            }
            else if(v13.getVisibility() != 8) {
                int v4 = v13.getMeasuredWidth();
                int v15 = v13.getMeasuredHeight();
                ViewGroup$LayoutParams v5 = v13.getLayoutParams();
                int v1 = ((a)v5).h;
                if(v1 < 0) {
                    v1 = v11;
                }

                v1 = d.a(v1, t.f(((View)this))) & 7;
                if(v1 == 1) {
                    v1 = (v9 - v4) / 2 + v7 + ((a)v5).leftMargin;
                label_69:
                    v1 -= ((a)v5).rightMargin;
                }
                else if(v1 != 5) {
                    v1 = ((a)v5).leftMargin + v7;
                }
                else {
                    v1 = v8 - v4;
                    goto label_69;
                }

                v2 = v1;
                if(v6.c(v12)) {
                    v0 += v6.m;
                }

                int v16 = v0 + ((a)v5).topMargin;
                this.a(v13, v2, v16 + v6.a(v13), v4, v15);
                v16 += v15 + v5.bottomMargin + v6.b(v13);
                v12 += v6.a(v13, v12);
                v0 = v16;
            }
        }
    }

    void a(Canvas arg6) {
        int v0 = this.getVirtualChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.b(v1);
            if(v2 != null && v2.getVisibility() != 8 && (this.c(v1))) {
                this.a(arg6, v2.getTop() - v2.getLayoutParams().topMargin - this.m);
            }
        }

        if(this.c(v0)) {
            View v0_1 = this.b(v0 - 1);
            v0 = v0_1 == null ? this.getHeight() - this.getPaddingBottom() - this.m : v0_1.getBottom() + v0_1.getLayoutParams().bottomMargin;
            this.a(arg6, v0);
        }
    }

    void a(Canvas arg5, int arg6) {
        this.k.setBounds(this.getPaddingLeft() + this.o, arg6, this.getWidth() - this.getPaddingRight() - this.o, this.m + arg6);
        this.k.draw(arg5);
    }

    View b(int arg1) {
        return this.getChildAt(arg1);
    }

    int b(View arg1) {
        return 0;
    }

    public a b(AttributeSet arg3) {
        return new a(this.getContext(), arg3);
    }

    protected a b(ViewGroup$LayoutParams arg2) {
        return new a(arg2);
    }

    void b(int arg45, int arg46) {
        float v43;
        int v42;
        int v39;
        ViewGroup$LayoutParams v5_1;
        int v3_2;
        int v38;
        int v10_1;
        int v9;
        int v6_2;
        int v4_1;
        View v3_1;
        int v34;
        int v0_1;
        boolean v36;
        boolean v32;
        int v5;
        int[] v29;
        ap v7 = this;
        int v8 = arg45;
        v7.f = 0;
        int v11 = this.getVirtualChildCount();
        int v12 = View$MeasureSpec.getMode(arg45);
        int v13 = View$MeasureSpec.getMode(arg46);
        int v14 = 4;
        if(v7.i == null || v7.j == null) {
            v7.i = new int[v14];
            v7.j = new int[v14];
        }

        int[] v15 = v7.i;
        int[] v6 = v7.j;
        int v16 = 3;
        v15[v16] = -1;
        int v17 = 2;
        v15[v17] = -1;
        v15[1] = -1;
        v15[0] = -1;
        v6[v16] = -1;
        v6[v17] = -1;
        v6[1] = -1;
        v6[0] = -1;
        boolean v4 = v7.a;
        boolean v3 = v7.h;
        int v2 = 1073741824;
        int v19 = v12 == v2 ? 1 : 0;
        float v0 = 0f;
        int v1 = 0;
        v14 = 0;
        int v21 = 0;
        int v22 = 0;
        int v23 = 0;
        int v24 = 0;
        int v26 = 0;
        int v27 = 1;
        int v28 = 0;
        while(true) {
            v29 = v6;
            v5 = 8;
            if(v1 >= v11) {
                break;
            }

            View v6_1 = v7.b(v1);
            if(v6_1 == null) {
                v7.f += v7.d(v1);
                goto label_58;
            }
            else if(v6_1.getVisibility() == v5) {
                v1 += v7.a(v6_1, v1);
            label_58:
                v32 = v3;
                v36 = v4;
            }
            else {
                if(v7.c(v1)) {
                    v7.f += v7.l;
                }

                ViewGroup$LayoutParams v10 = v6_1.getLayoutParams();
                float v31 = v0 + ((a)v10).g;
                if(v12 != v2 || ((a)v10).width != 0 || ((a)v10).g <= 0f) {
                    if(((a)v10).width != 0 || ((a)v10).g <= 0f) {
                        v2 = -2147483648;
                    }
                    else {
                        ((a)v10).width = -2;
                        v2 = 0;
                    }

                    int v30 = v31 == 0f ? v7.f : 0;
                    v34 = v1;
                    v32 = v3;
                    v36 = v4;
                    View v37 = v6_1;
                    this.a(v6_1, v34, arg45, v30, arg46, 0);
                    v0_1 = v2;
                    if(v0_1 != -2147483648) {
                        ((a)v10).width = v0_1;
                    }

                    v0_1 = v37.getMeasuredWidth();
                    if(v19 != 0) {
                        v3_1 = v37;
                        v1 = v7.f + (((a)v10).leftMargin + v0_1 + ((a)v10).rightMargin + v7.b(v3_1));
                    }
                    else {
                        v3_1 = v37;
                        v1 = Math.max(v7.f, v7.f + v0_1 + ((a)v10).leftMargin + ((a)v10).rightMargin + v7.b(v3_1));
                    }

                    v7.f = v1;
                    if(v32) {
                        v14 = Math.max(v0_1, v14);
                    }

                label_175:
                    v1 = 1073741824;
                }
                else {
                    v0_1 = v19 != 0 ? v7.f + (((a)v10).leftMargin + ((a)v10).rightMargin) : Math.max(v7.f, ((a)v10).leftMargin + v7.f + ((a)v10).rightMargin);
                    v7.f = v0_1;
                    if(v4) {
                        v2 = View$MeasureSpec.makeMeasureSpec(0, 0);
                        v6_1.measure(v2, v2);
                        v34 = v1;
                        v32 = v3;
                        v36 = v4;
                        v3_1 = v6_1;
                        goto label_175;
                    }

                    v34 = v1;
                    v32 = v3;
                    v36 = v4;
                    v3_1 = v6_1;
                    v1 = 1073741824;
                    v22 = 1;
                }

                if(v13 == v1 || ((a)v10).height != -1) {
                    v0_1 = 0;
                }
                else {
                    v0_1 = 1;
                    v28 = 1;
                }

                v2 = ((a)v10).topMargin + ((a)v10).bottomMargin;
                v4_1 = v3_1.getMeasuredHeight() + v2;
                v5 = View.combineMeasuredStates(v26, v3_1.getMeasuredState());
                if(v36) {
                    v6_2 = v3_1.getBaseline();
                    if(v6_2 != -1) {
                        v9 = ((a)v10).h < 0 ? v7.e : ((a)v10).h;
                        v9 = ((v9 & 112) >> 4 & -2) >> 1;
                        v15[v9] = Math.max(v15[v9], v6_2);
                        v29[v9] = Math.max(v29[v9], v4_1 - v6_2);
                    }
                }

                v1 = Math.max(v21, v4_1);
                v6_2 = v27 == 0 || ((a)v10).height != -1 ? 0 : 1;
                if(((a)v10).g > 0f) {
                    if(v0_1 == 0) {
                        v2 = v4_1;
                    }

                    v10_1 = v24;
                    v24 = Math.max(v10_1, v2);
                }
                else {
                    v10_1 = v24;
                    if(v0_1 != 0) {
                        v4_1 = v2;
                    }

                    v23 = Math.max(v23, v4_1);
                    v24 = v10_1;
                }

                v10_1 = v34;
                v21 = v1;
                v26 = v5;
                v27 = v6_2;
                v1 = v7.a(v3_1, v10_1) + v10_1;
                v0 = v31;
            }

            ++v1;
            v6 = v29;
            v3 = v32;
            v4 = v36;
            v2 = 1073741824;
        }

        v32 = v3;
        v36 = v4;
        v1 = v21;
        v2 = v23;
        v10_1 = v24;
        v6_2 = v26;
        if(v7.f > 0 && (v7.c(v11))) {
            v7.f += v7.l;
        }

        v4_1 = -1;
        if(v15[1] != v4_1 || v15[0] != v4_1 || v15[v17] != v4_1 || v15[v16] != v4_1) {
            v38 = v6_2;
            v1 = Math.max(v1, Math.max(v15[v16], Math.max(v15[0], Math.max(v15[1], v15[v17]))) + Math.max(v29[v16], Math.max(v29[0], Math.max(v29[1], v29[v17]))));
        }
        else {
            v38 = v6_2;
        }

        if((v32) && (v12 == -2147483648 || v12 == 0)) {
            v7.f = 0;
            v3_2 = 0;
            while(v3_2 < v11) {
                View v4_2 = v7.b(v3_2);
                if(v4_2 == null) {
                    v7.f += v7.d(v3_2);
                    goto label_324;
                }
                else if(v4_2.getVisibility() == 8) {
                    v3_2 += v7.a(v4_2, v3_2);
                    goto label_324;
                }
                else {
                    v5_1 = v4_2.getLayoutParams();
                    if(v19 != 0) {
                        v7.f += ((a)v5_1).leftMargin + v14 + ((a)v5_1).rightMargin + v7.b(v4_2);
                    label_324:
                        v39 = v1;
                    }
                    else {
                        v39 = v1;
                        v7.f = Math.max(v7.f, v7.f + v14 + ((a)v5_1).leftMargin + ((a)v5_1).rightMargin + v7.b(v4_2));
                    }
                }

                ++v3_2;
                v1 = v39;
            }
        }

        v39 = v1;
        v7.f += this.getPaddingLeft() + this.getPaddingRight();
        v1 = View.resolveSizeAndState(Math.max(v7.f, this.getSuggestedMinimumWidth()), v8, 0);
        v3_2 = (16777215 & v1) - v7.f;
        if(v22 == 0) {
            if(v3_2 != 0 && v0 > 0f) {
                goto label_397;
            }

            v0_1 = Math.max(v2, v10_1);
            if((v32) && v12 != 1073741824) {
                for(v2 = 0; v2 < v11; ++v2) {
                    v3_1 = v7.b(v2);
                    if(v3_1 != null) {
                        if(v3_1.getVisibility() == 8) {
                        }
                        else if(v3_1.getLayoutParams().g > 0f) {
                            v3_1.measure(View$MeasureSpec.makeMeasureSpec(v14, 1073741824), View$MeasureSpec.makeMeasureSpec(v3_1.getMeasuredHeight(), 1073741824));
                        }
                    }
                }
            }

            v42 = v11;
            v3_2 = arg46;
        }
        else {
        label_397:
            if(v7.g > 0f) {
                v0 = v7.g;
            }

            v15[v16] = -1;
            v15[v17] = -1;
            v15[1] = -1;
            v15[0] = -1;
            v29[v16] = -1;
            v29[v17] = -1;
            v29[1] = -1;
            v29[0] = -1;
            v7.f = 0;
            v10_1 = v2;
            v9 = v38;
            v6_2 = -1;
            float v2_1 = v0;
            v0_1 = 0;
            while(v0_1 < v11) {
                View v14_1 = v7.b(v0_1);
                if(v14_1 == null || v14_1.getVisibility() == 8) {
                    v4_1 = v3_2;
                    v42 = v11;
                }
                else {
                    v5_1 = v14_1.getLayoutParams();
                    float v4_3 = ((a)v5_1).g;
                    if(v4_3 > 0f) {
                        v8 = ((int)((((float)v3_2)) * v4_3 / v2_1));
                        float v40 = v2_1 - v4_3;
                        int v41 = v3_2 - v8;
                        v42 = v11;
                        v2 = ap.getChildMeasureSpec(arg46, this.getPaddingTop() + this.getPaddingBottom() + ((a)v5_1).topMargin + ((a)v5_1).bottomMargin, ((a)v5_1).height);
                        if(((a)v5_1).width == 0) {
                            v4_1 = 1073741824;
                            if(v12 != v4_1) {
                                goto label_454;
                            }
                            else if(v8 > 0) {
                            }
                            else {
                                goto label_457;
                            }
                        }
                        else {
                            v4_1 = 1073741824;
                        label_454:
                            v8 = v14_1.getMeasuredWidth() + v8;
                            if(v8 < 0) {
                            label_457:
                                v8 = 0;
                            }
                        }

                        v14_1.measure(View$MeasureSpec.makeMeasureSpec(v8, v4_1), v2);
                        v9 = View.combineMeasuredStates(v9, v14_1.getMeasuredState() & -16777216);
                        v2_1 = v40;
                        v4_1 = v41;
                    }
                    else {
                        v4_1 = v3_2;
                        v42 = v11;
                    }

                    if(v19 != 0) {
                        v7.f += v14_1.getMeasuredWidth() + ((a)v5_1).leftMargin + ((a)v5_1).rightMargin + v7.b(v14_1);
                        v43 = v2_1;
                    }
                    else {
                        v43 = v2_1;
                        v7.f = Math.max(v7.f, v14_1.getMeasuredWidth() + v7.f + ((a)v5_1).leftMargin + ((a)v5_1).rightMargin + v7.b(v14_1));
                    }

                    v2 = 1073741824;
                    v2 = v13 == v2 || ((a)v5_1).height != -1 ? 0 : 1;
                    v8 = ((a)v5_1).topMargin + ((a)v5_1).bottomMargin;
                    v11 = v14_1.getMeasuredHeight() + v8;
                    v6_2 = Math.max(v6_2, v11);
                    if(v2 != 0) {
                    }
                    else {
                        v8 = v11;
                    }

                    v2 = Math.max(v10_1, v8);
                    if(v27 != 0) {
                        v10_1 = -1;
                        if(((a)v5_1).height == v10_1) {
                            v8 = 1;
                        }
                        else {
                            goto label_522;
                        }
                    }
                    else {
                        v10_1 = -1;
                    label_522:
                        v8 = 0;
                    }

                    if(v36) {
                        v14 = v14_1.getBaseline();
                        if(v14 != v10_1) {
                            v5 = ((a)v5_1).h < 0 ? v7.e : ((a)v5_1).h;
                            v5 = ((v5 & 112) >> 4 & -2) >> 1;
                            v15[v5] = Math.max(v15[v5], v14);
                            v29[v5] = Math.max(v29[v5], v11 - v14);
                        }
                    }

                    v10_1 = v2;
                    v27 = v8;
                    v2_1 = v43;
                }

                ++v0_1;
                v3_2 = v4_1;
                v11 = v42;
            }

            v42 = v11;
            v3_2 = arg46;
            v7.f += this.getPaddingLeft() + this.getPaddingRight();
            v2 = -1;
            v0_1 = v15[1] != v2 || v15[0] != v2 || v15[v17] != v2 || v15[v16] != v2 ? Math.max(v6_2, Math.max(v15[v16], Math.max(v15[0], Math.max(v15[1], v15[v17]))) + Math.max(v29[v16], Math.max(v29[0], Math.max(v29[1], v29[v17])))) : v6_2;
            v39 = v0_1;
            v38 = v9;
            v0_1 = v10_1;
        }

        if(v27 == 0 && v13 != 1073741824) {
            v39 = v0_1;
        }

        v7.setMeasuredDimension(v1 | v38 & -16777216, View.resolveSizeAndState(Math.max(v39 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), v3_2, v38 << 16));
        if(v28 != 0) {
            v7.d(v42, arg45);
        }
    }

    void b(int arg28, int arg29, int arg30, int arg31) {
        int v26;
        int v24;
        int v23;
        int v22;
        int v17;
        int v16;
        int v0;
        ap v6 = this;
        boolean v2 = bs.a(((View)this));
        int v7 = this.getPaddingTop();
        int v3 = arg31 - arg29;
        int v8 = v3 - this.getPaddingBottom();
        int v9 = v3 - v7 - this.getPaddingBottom();
        int v10 = this.getVirtualChildCount();
        v3 = v6.e & 8388615;
        int v11 = v6.e & 112;
        boolean v12 = v6.a;
        int[] v13 = v6.i;
        int[] v14 = v6.j;
        v3 = d.a(v3, t.f(((View)this)));
        int v15 = 2;
        if(v3 == 1) {
            v0 = (arg30 - arg28 - v6.f) / v15 + this.getPaddingLeft();
        }
        else if(v3 != 5) {
            v0 = this.getPaddingLeft();
        }
        else {
            v0 = this.getPaddingLeft() + arg30 - arg28 - v6.f;
        }

        if(v2) {
            v16 = v10 - 1;
            v17 = -1;
        }
        else {
            v16 = 0;
            v17 = 1;
        }

        v3 = 0;
        while(v3 < v10) {
            int v2_1 = v16 + v17 * v3;
            View v1 = v6.b(v2_1);
            if(v1 == null) {
                v0 += v6.d(v2_1);
                goto label_55;
            }
            else if(v1.getVisibility() != 8) {
                v15 = v1.getMeasuredWidth();
                int v5 = v1.getMeasuredHeight();
                ViewGroup$LayoutParams v4 = v1.getLayoutParams();
                if(v12) {
                    v22 = v3;
                    v23 = v10;
                    if(((a)v4).height != -1) {
                        v3 = v1.getBaseline();
                    }
                    else {
                        goto label_78;
                    }
                }
                else {
                    v22 = v3;
                    v23 = v10;
                label_78:
                    v3 = -1;
                }

                v10 = ((a)v4).h;
                if(v10 < 0) {
                    v10 = v11;
                }

                v10 &= 112;
                v24 = v11;
                if(v10 == 16) {
                    v3 = (v9 - v5) / 2 + v7 + ((a)v4).topMargin - ((a)v4).bottomMargin;
                }
                else if(v10 == 48) {
                    v10 = ((a)v4).topMargin + v7;
                    if(v3 != -1) {
                        v10 += v13[1] - v3;
                    }

                    v3 = v10;
                }
                else if(v10 != 80) {
                    v3 = v7;
                }
                else {
                    v10 = v8 - v5 - ((a)v4).bottomMargin;
                    if(v3 != -1) {
                        v10 -= v14[2] - (v1.getMeasuredHeight() - v3);
                    }

                    v3 = v10;
                }

                if(v6.c(v2_1)) {
                    v0 += v6.l;
                }

                v10 = ((a)v4).leftMargin + v0;
                v26 = v7;
                this.a(v1, v10 + v6.a(v1), v3, v15, v5);
                v10 += v15 + v4.rightMargin + v6.b(v1);
                v3 = v22 + v6.a(v1, v2_1);
                v0 = v10;
            }
            else {
            label_55:
                v26 = v7;
                v23 = v10;
                v24 = v11;
            }

            ++v3;
            v10 = v23;
            v11 = v24;
            v7 = v26;
        }
    }

    void b(Canvas arg7) {
        int v1_1;
        int v0 = this.getVirtualChildCount();
        boolean v1 = bs.a(((View)this));
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.b(v2);
            if(v3 != null && v3.getVisibility() != 8 && (this.c(v2))) {
                ViewGroup$LayoutParams v4 = v3.getLayoutParams();
                int v3_1 = v1 ? v3.getRight() + ((a)v4).rightMargin : v3.getLeft() - ((a)v4).leftMargin - this.l;
                this.b(arg7, v3_1);
            }
        }

        if(this.c(v0)) {
            View v0_1 = this.b(v0 - 1);
            if(v0_1 != null) {
                ViewGroup$LayoutParams v2_1 = v0_1.getLayoutParams();
                if(v1) {
                    v0 = v0_1.getLeft();
                    v1_1 = ((a)v2_1).leftMargin;
                label_35:
                    v0 = v0 - v1_1 - this.l;
                }
                else {
                    v0 = v0_1.getRight() + ((a)v2_1).rightMargin;
                }
            }
            else if(v1) {
                v0 = this.getPaddingLeft();
            }
            else {
                v0 = this.getWidth();
                v1_1 = this.getPaddingRight();
                goto label_35;
            }

            this.b(arg7, v0);
        }
    }

    void b(Canvas arg6, int arg7) {
        this.k.setBounds(arg7, this.getPaddingTop() + this.o, this.l + arg7, this.getHeight() - this.getPaddingBottom() - this.o);
        this.k.draw(arg6);
    }

    private void c(int arg11, int arg12) {
        int v0 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        int v1;
        for(v1 = 0; v1 < arg11; ++v1) {
            View v3 = this.b(v1);
            if(v3.getVisibility() != 8) {
                ViewGroup$LayoutParams v8 = v3.getLayoutParams();
                if(((a)v8).width == -1) {
                    int v9 = ((a)v8).height;
                    ((a)v8).height = v3.getMeasuredHeight();
                    this.measureChildWithMargins(v3, v0, 0, arg12, 0);
                    ((a)v8).height = v9;
                }
            }
        }
    }

    protected boolean c(int arg5) {
        boolean v0 = false;
        if(arg5 == 0) {
            if((this.n & 1) != 0) {
                v0 = true;
            }

            return v0;
        }

        if(arg5 == this.getChildCount()) {
            if((this.n & 4) != 0) {
                v0 = true;
            }

            return v0;
        }

        if((this.n & 2) != 0) {
            --arg5;
            while(arg5 >= 0) {
                if(this.getChildAt(arg5).getVisibility() != 8) {
                    v0 = true;
                }
                else {
                    --arg5;
                    continue;
                }

                return v0;
            }
        }

        return v0;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        return arg1 instanceof a;
    }

    private void d(int arg11, int arg12) {
        int v0 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824);
        int v1;
        for(v1 = 0; v1 < arg11; ++v1) {
            View v3 = this.b(v1);
            if(v3.getVisibility() != 8) {
                ViewGroup$LayoutParams v8 = v3.getLayoutParams();
                if(((a)v8).height == -1) {
                    int v9 = ((a)v8).width;
                    ((a)v8).width = v3.getMeasuredWidth();
                    this.measureChildWithMargins(v3, arg12, 0, v0, 0);
                    ((a)v8).width = v9;
                }
            }
        }
    }

    int d(int arg1) {
        return 0;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.j();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.b(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.b(arg1);
    }

    public int getBaseline() {
        if(this.b < 0) {
            return super.getBaseline();
        }

        if(this.getChildCount() > this.b) {
            View v0 = this.getChildAt(this.b);
            int v1 = v0.getBaseline();
            int v2 = -1;
            if(v1 == v2) {
                if(this.b == 0) {
                    return v2;
                }

                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn\'t know how to get its baseline.");
            }

            v2 = this.c;
            if(this.d == 1) {
                int v3 = this.e & 112;
                if(v3 != 48) {
                    if(v3 == 16) {
                        v2 += (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.f) / 2;
                    }
                    else if(v3 != 80) {
                    }
                    else {
                        v2 = this.getBottom() - this.getTop() - this.getPaddingBottom() - this.f;
                    }
                }
            }

            return v2 + v0.getLayoutParams().topMargin + v1;
        }

        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.b;
    }

    public Drawable getDividerDrawable() {
        return this.k;
    }

    public int getDividerPadding() {
        return this.o;
    }

    public int getDividerWidth() {
        return this.l;
    }

    public int getGravity() {
        return this.e;
    }

    public int getOrientation() {
        return this.d;
    }

    public int getShowDividers() {
        return this.n;
    }

    int getVirtualChildCount() {
        return this.getChildCount();
    }

    public float getWeightSum() {
        return this.g;
    }

    protected a j() {
        int v1 = -2;
        if(this.d == 0) {
            return new a(v1, v1);
        }

        if(this.d == 1) {
            return new a(-1, v1);
        }

        return null;
    }

    protected void onDraw(Canvas arg3) {
        if(this.k == null) {
            return;
        }

        if(this.d == 1) {
            this.a(arg3);
        }
        else {
            this.b(arg3);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent arg2) {
        super.onInitializeAccessibilityEvent(arg2);
        arg2.setClassName(ap.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg2) {
        super.onInitializeAccessibilityNodeInfo(arg2);
        arg2.setClassName(ap.class.getName());
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        if(this.d == 1) {
            this.a(arg3, arg4, arg5, arg6);
        }
        else {
            this.b(arg3, arg4, arg5, arg6);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        if(this.d == 1) {
            this.a(arg3, arg4);
        }
        else {
            this.b(arg3, arg4);
        }
    }

    public void setBaselineAligned(boolean arg1) {
        this.a = arg1;
    }

    public void setBaselineAlignedChildIndex(int arg3) {
        if(arg3 >= 0 && arg3 < this.getChildCount()) {
            this.b = arg3;
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("base aligned child index out of range (0, ");
        v0.append(this.getChildCount());
        v0.append(")");
        throw new IllegalArgumentException(v0.toString());
    }

    public void setDividerDrawable(Drawable arg3) {
        if(arg3 == this.k) {
            return;
        }

        this.k = arg3;
        boolean v0 = false;
        if(arg3 != null) {
            this.l = arg3.getIntrinsicWidth();
            this.m = arg3.getIntrinsicHeight();
        }
        else {
            this.l = 0;
            this.m = 0;
        }

        if(arg3 == null) {
            v0 = true;
        }

        this.setWillNotDraw(v0);
        this.requestLayout();
    }

    public void setDividerPadding(int arg1) {
        this.o = arg1;
    }

    public void setGravity(int arg2) {
        if(this.e != arg2) {
            if((8388615 & arg2) == 0) {
                arg2 |= 8388611;
            }

            if((arg2 & 112) == 0) {
                arg2 |= 48;
            }

            this.e = arg2;
            this.requestLayout();
        }
    }

    public void setHorizontalGravity(int arg3) {
        arg3 &= 8388615;
        if((8388615 & this.e) != arg3) {
            this.e = arg3 | this.e & -8388616;
            this.requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean arg1) {
        this.h = arg1;
    }

    public void setOrientation(int arg2) {
        if(this.d != arg2) {
            this.d = arg2;
            this.requestLayout();
        }
    }

    public void setShowDividers(int arg2) {
        if(arg2 != this.n) {
            this.requestLayout();
        }

        this.n = arg2;
    }

    public void setVerticalGravity(int arg2) {
        arg2 &= 112;
        if((this.e & 112) != arg2) {
            this.e = arg2 | this.e & -113;
            this.requestLayout();
        }
    }

    public void setWeightSum(float arg2) {
        this.g = Math.max(0f, arg2);
    }

    public boolean shouldDelayChildPressedState() {
        return 0;
    }
}

