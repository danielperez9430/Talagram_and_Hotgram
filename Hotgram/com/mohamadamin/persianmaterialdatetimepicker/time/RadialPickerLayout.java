package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import java.util.Calendar;

public class RadialPickerLayout extends FrameLayout implements View$OnTouchListener {
    public interface a {
        void a(int arg1, int arg2, boolean arg3);
    }

    private AccessibilityManager A;
    private AnimatorSet B;
    private Handler C;
    private final int a;
    private final int b;
    private int c;
    private com.mohamadamin.persianmaterialdatetimepicker.a d;
    private a e;
    private boolean f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private int k;
    private b l;
    private com.mohamadamin.persianmaterialdatetimepicker.time.a m;
    private d n;
    private d o;
    private c p;
    private c q;
    private View r;
    private int[] s;
    private boolean t;
    private int u;
    private boolean v;
    private boolean w;
    private int x;
    private float y;
    private float z;

    public RadialPickerLayout(Context arg4, AttributeSet arg5) {
        super(arg4, arg5);
        this.u = -1;
        this.C = new Handler();
        this.setOnTouchListener(((View$OnTouchListener)this));
        this.a = ViewConfiguration.get(arg4).getScaledTouchSlop();
        this.b = ViewConfiguration.getTapTimeout();
        this.v = false;
        this.l = new b(arg4);
        this.addView(this.l);
        this.m = new com.mohamadamin.persianmaterialdatetimepicker.time.a(arg4);
        this.addView(this.m);
        this.p = new c(arg4);
        this.addView(this.p);
        this.q = new c(arg4);
        this.addView(this.q);
        this.n = new d(arg4);
        this.addView(this.n);
        this.o = new d(arg4);
        this.addView(this.o);
        this.a();
        this.c = -1;
        this.t = true;
        this.r = new View(arg4);
        this.r.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
        this.r.setBackgroundColor(this.getResources().getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_transparent_black));
        this.r.setVisibility(4);
        this.addView(this.r);
        this.A = arg4.getSystemService("accessibility");
        this.f = false;
    }

    private void a() {
        int v0 = 361;
        this.s = new int[v0];
        int v1 = 0;
        int v3 = 0;
        int v4 = 1;
        int v5 = 8;
        while(v1 < v0) {
            this.s[v1] = v3;
            if(v4 == v5) {
                v3 += 6;
                if(v3 == 360) {
                    v4 = 7;
                }
                else if(v3 % 30 == 0) {
                    v4 = 14;
                }
                else {
                    v4 = 4;
                }

                v5 = v4;
                v4 = 1;
            }
            else {
                ++v4;
            }

            ++v1;
        }
    }

    private int a(float arg3, float arg4, boolean arg5, Boolean[] arg6) {
        c v0_1;
        int v0 = this.getCurrentItemShowing();
        if(v0 == 0) {
            v0_1 = this.p;
        }
        else if(v0 == 1) {
            v0_1 = this.q;
        }
        else {
            return -1;
        }

        return v0_1.a(arg3, arg4, arg5, arg6);
    }

    private int a(int arg5, boolean arg6, boolean arg7, boolean arg8) {
        d v6;
        int v3;
        c v7_1;
        int v0 = -1;
        if(arg5 == v0) {
            return v0;
        }

        v0 = this.getCurrentItemShowing();
        int v7 = (arg7) || v0 != 1 ? 0 : 1;
        arg5 = v7 != 0 ? this.b(arg5) : RadialPickerLayout.d(arg5, 0);
        if(v0 == 0) {
            v7_1 = this.p;
            v3 = 30;
        }
        else {
            v7_1 = this.q;
            v3 = 6;
        }

        v7_1.a(arg5, arg6, arg8);
        v7_1.invalidate();
        v7 = 360;
        if(v0 != 0) {
            if(arg5 == v7 && v0 == 1) {
            label_37:
                v7 = 0;
                goto label_40;
            }

        label_39:
            v7 = arg5;
        }
        else if(this.i) {
            if(arg5 == 0 && (arg6)) {
                goto label_40;
            }

            if(arg5 != v7) {
                goto label_39;
            }

            if(arg6) {
                goto label_39;
            }

            goto label_37;
        }
        else if(arg5 != 0) {
            goto label_39;
        }

    label_40:
        arg5 = v7 / v3;
        if(v0 == 0 && (this.i) && !arg6 && v7 != 0) {
            arg5 += 12;
        }

        if(this.getCurrentItemShowing() == 0) {
            this.n.setSelection(arg5);
            v6 = this.n;
            goto label_52;
        }
        else if(this.getCurrentItemShowing() == 1) {
            this.o.setSelection(arg5);
            v6 = this.o;
        label_52:
            v6.invalidate();
        }

        return arg5;
    }

    static int a(RadialPickerLayout arg0) {
        return arg0.u;
    }

    static int a(RadialPickerLayout arg0, int arg1) {
        arg0.c = arg1;
        return arg1;
    }

    static int a(RadialPickerLayout arg0, int arg1, boolean arg2, boolean arg3, boolean arg4) {
        return arg0.a(arg1, arg2, arg3, arg4);
    }

    private boolean a(int arg2) {
        boolean v2 = !this.i || arg2 > 12 || arg2 == 0 ? false : true;
        return v2;
    }

    static boolean a(RadialPickerLayout arg0, boolean arg1) {
        arg0.v = arg1;
        return arg1;
    }

    public void a(int arg2, int arg3) {
        this.b(0, arg2);
        this.b(1, arg3);
    }

    public void a(int arg5, boolean arg6) {
        if(arg5 != 0 && arg5 != 1) {
            Log.e("RadialPickerLayout", "TimePicker does not support view at index " + arg5);
            return;
        }

        int v1 = this.getCurrentItemShowing();
        this.k = arg5;
        if(!arg6 || arg5 == v1) {
            int v6_1 = 255;
            v1 = arg5 == 0 ? 255 : 0;
            if(arg5 == 1) {
            }
            else {
                v6_1 = 0;
            }

            float v0_1 = ((float)v1);
            this.n.setAlpha(v0_1);
            this.p.setAlpha(v0_1);
            float v6_2 = ((float)v6_1);
            this.o.setAlpha(v6_2);
            this.q.setAlpha(v6_2);
        }
        else {
            ObjectAnimator[] v6 = new ObjectAnimator[4];
            v1 = 3;
            int v3 = 2;
            if(arg5 == 1) {
                v6[0] = this.n.getDisappearAnimator();
                v6[1] = this.p.getDisappearAnimator();
                v6[v3] = this.o.getReappearAnimator();
                v6[v1] = this.q.getReappearAnimator();
            }
            else if(arg5 == 0) {
                v6[0] = this.n.getReappearAnimator();
                v6[1] = this.p.getReappearAnimator();
                v6[v3] = this.o.getDisappearAnimator();
                v6[v1] = this.q.getDisappearAnimator();
            }

            if(this.B != null && (this.B.isRunning())) {
                this.B.end();
            }

            this.B = new AnimatorSet();
            this.B.playTogether(((Animator[])v6));
            this.B.start();
        }
    }

    public void a(Context arg19, com.mohamadamin.persianmaterialdatetimepicker.a arg20, int arg21, int arg22, boolean arg23) {
        String v3_1;
        int v17;
        int v6;
        RadialPickerLayout v0 = this;
        Context v8 = arg19;
        int v1 = arg21;
        int v9 = arg22;
        boolean v4 = arg23;
        if(v0.f) {
            Log.e("RadialPickerLayout", "Time has already been initialized.");
            return;
        }

        v0.d = arg20;
        v0.i = v4;
        boolean v2 = (v0.A.isTouchExplorationEnabled()) || (v0.i) ? true : false;
        v0.j = v2;
        v0.l.a(v8, v0.j);
        v0.l.invalidate();
        int v5 = 12;
        if(!v0.j) {
            com.mohamadamin.persianmaterialdatetimepicker.time.a v2_1 = v0.m;
            v6 = v1 < v5 ? 0 : 1;
            v2_1.a(v8, v6);
            v0.m.invalidate();
        }

        Resources v2_2 = arg19.getResources();
        int[] v6_1 = new int[]{12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] v7 = new int[]{0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        int[] v11 = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        String[] v13 = new String[v5];
        String[] v12 = new String[v5];
        String[] v15 = new String[v5];
        int v14 = 0;
        while(v14 < v5) {
            if(v4) {
                Object[] v3 = new Object[1];
                v17 = 0;
                v3[0] = Integer.valueOf(v7[v14]);
                v3_1 = String.format("%02d", v3);
            }
            else {
                v17 = 0;
                v3_1 = String.format("%d", Integer.valueOf(v6_1[v14]));
            }

            v13[v14] = com.mohamadamin.persianmaterialdatetimepicker.a.a.a(v3_1);
            Object[] v5_1 = new Object[1];
            v5_1[v17] = Integer.valueOf(v6_1[v14]);
            v12[v14] = com.mohamadamin.persianmaterialdatetimepicker.a.a.a(String.format("%d", v5_1));
            v5_1 = new Object[1];
            v5_1[v17] = Integer.valueOf(v11[v14]);
            v15[v14] = com.mohamadamin.persianmaterialdatetimepicker.a.a.a(String.format("%02d", v5_1));
            ++v14;
            v5 = 12;
        }

        d v11_1 = v0.n;
        String[] v14_1 = v4 ? v12 : null;
        String[] v5_2 = v15;
        v11_1.a(v2_2, v13, v14_1, v0.j, true);
        d v3_2 = v0.n;
        v6 = v4 ? v1 : v1 % 12;
        v3_2.setSelection(v6);
        v0.n.invalidate();
        v0.o.a(v2_2, v5_2, null, v0.j, false);
        v0.o.setSelection(v9);
        v0.o.invalidate();
        v0.c(0, v1);
        v0.c(1, v9);
        v0.p.a(arg19, v0.j, arg23, true, v1 % 12 * 30, v0.a(v1));
        v0.q.a(arg19, v0.j, false, false, v9 * 6, false);
        v0.f = true;
    }

    void a(Context arg2, boolean arg3) {
        this.l.b(arg2, arg3);
        this.m.a(arg2, arg3);
        this.n.a(arg2, arg3);
        this.o.a(arg2, arg3);
        this.p.a(arg2, arg3);
        this.q.a(arg2, arg3);
    }

    public boolean a(boolean arg3) {
        int v1 = 0;
        if((this.w) && !arg3) {
            return 0;
        }

        this.t = arg3;
        View v0 = this.r;
        if(arg3) {
            v1 = 4;
        }

        v0.setVisibility(v1);
        return 1;
    }

    private int b(int arg2) {
        if(this.s == null) {
            return -1;
        }

        return this.s[arg2];
    }

    static com.mohamadamin.persianmaterialdatetimepicker.time.a b(RadialPickerLayout arg0) {
        return arg0.m;
    }

    private void b(int arg4, int arg5) {
        d v4;
        if(arg4 == 0) {
            this.c(0, arg5);
            this.p.a(arg5 % 12 * 30, this.a(arg5), false);
            this.p.invalidate();
            v4 = this.n;
            goto label_11;
        }
        else if(arg4 == 1) {
            this.c(1, arg5);
            this.q.a(arg5 * 6, false, false);
            this.q.invalidate();
            v4 = this.o;
        label_11:
            v4.setSelection(arg5);
            this.n.invalidate();
        }
    }

    private void c(int arg3, int arg4) {
        if(arg3 == 0) {
            this.g = arg4;
        }
        else if(arg3 == 1) {
            this.h = arg4;
        }
        else if(arg3 == 2) {
            if(arg4 == 0) {
                arg3 = this.g % 12;
            }
            else if(arg4 == 1) {
                arg3 = this.g % 12 + 12;
            }
            else {
                return;
            }

            this.g = arg3;
        }
    }

    static int c(RadialPickerLayout arg0) {
        return arg0.x;
    }

    private static int d(int arg3, int arg4) {
        int v0 = arg3 / 30 * 30;
        int v1 = v0 + 30;
        if(arg4 == 1) {
            goto label_15;
        }
        else if(arg4 == -1) {
            if(arg3 == v0) {
                v0 += -30;
            }
        }
        else if(arg3 - v0 < v1 - arg3) {
        }
        else {
        label_15:
            v0 = v1;
        }

        return v0;
    }

    static a d(RadialPickerLayout arg0) {
        return arg0.e;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg6) {
        if(arg6.getEventType() == 32) {
            arg6.getText().clear();
            Calendar v0 = Calendar.getInstance();
            v0.set(10, this.getHours());
            v0.set(12, this.getMinutes());
            long v0_1 = v0.getTimeInMillis();
            int v2 = this.i ? 129 : 1;
            arg6.getText().add(com.mohamadamin.persianmaterialdatetimepicker.a.a.a(DateUtils.formatDateTime(this.getContext(), v0_1, v2)));
            return 1;
        }

        return super.dispatchPopulateAccessibilityEvent(arg6);
    }

    public int getCurrentItemShowing() {
        if(this.k != 0 && this.k != 1) {
            Log.e("RadialPickerLayout", "Current item showing was unfortunately set to " + this.k);
            return -1;
        }

        return this.k;
    }

    private int getCurrentlyShowingValue() {
        int v0 = this.getCurrentItemShowing();
        if(v0 == 0) {
            return this.g;
        }

        if(v0 == 1) {
            return this.h;
        }

        return -1;
    }

    public int getHours() {
        return this.g;
    }

    public int getIsCurrentlyAmOrPm() {
        if(this.g < 12) {
            return 0;
        }

        if(this.g < 24) {
            return 1;
        }

        return -1;
    }

    public int getMinutes() {
        return this.h;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg3) {
        super.onInitializeAccessibilityNodeInfo(arg3);
        if(Build$VERSION.SDK_INT >= 21) {
            arg3.addAction(AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_BACKWARD);
            arg3.addAction(AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_FORWARD);
        }
        else {
            arg3.addAction(4096);
            arg3.addAction(8192);
        }
    }

    public boolean onTouch(View arg9, MotionEvent arg10) {
        Handler v9_2;
        int v9_1;
        float v9 = arg10.getX();
        float v0 = arg10.getY();
        Boolean[] v2 = new Boolean[]{Boolean.valueOf(false)};
        Object v4 = null;
        int v5 = -1;
        switch(arg10.getAction()) {
            case 0: {
                if(!this.t) {
                    return 1;
                }

                this.y = v9;
                this.z = v0;
                this.c = v5;
                this.v = false;
                this.w = true;
                this.u = !this.j ? this.m.a(v9, v0) : v5;
                if(this.u == 0 || this.u == 1) {
                    this.d.c();
                    this.x = v5;
                    v9_2 = this.C;
                    com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout$1 v10_2 = new Runnable() {
                        public void run() {
                            RadialPickerLayout.b(this.a).setAmOrPmPressed(RadialPickerLayout.a(this.a));
                            RadialPickerLayout.b(this.a).invalidate();
                        }
                    };
                label_173:
                    v9_2.postDelayed(((Runnable)v10_2), ((long)this.b));
                }
                else {
                    this.x = this.a(v9, v0, this.A.isTouchExplorationEnabled(), v2);
                    if(this.x != v5) {
                        this.d.c();
                        v9_2 = this.C;
                        com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout$2 v10_1 = new Runnable(v2) {
                            public void run() {
                                RadialPickerLayout.a(this.b, true);
                                int v0 = RadialPickerLayout.a(this.b, RadialPickerLayout.c(this.b), this.a[0].booleanValue(), false, true);
                                RadialPickerLayout.a(this.b, v0);
                                RadialPickerLayout.d(this.b).a(this.b.getCurrentItemShowing(), v0, false);
                            }
                        };
                        goto label_173;
                    }
                }

                return 1;
            }
            case 1: {
                if(!this.t) {
                    Log.d("RadialPickerLayout", "Input was disabled, but received ACTION_UP.");
                    this.e.a(3, 1, false);
                    return 1;
                }

                this.C.removeCallbacksAndMessages(v4);
                this.w = false;
                if(this.u != 0) {
                    if(this.u == 1) {
                    }
                    else {
                        if(this.x != v5) {
                            v9_1 = this.a(v9, v0, this.v, v2);
                            if(v9_1 != v5) {
                                v9_1 = this.a(v9_1, v2[0].booleanValue(), this.v ^ 1, false);
                                if(this.getCurrentItemShowing() == 0 && !this.i) {
                                    int v10 = this.getIsCurrentlyAmOrPm();
                                    int v0_1 = 12;
                                    if(v10 == 0 && v9_1 == v0_1) {
                                        v9_1 = 0;
                                        goto label_109;
                                    }

                                    if(v10 != 1) {
                                        goto label_109;
                                    }

                                    if(v9_1 == v0_1) {
                                        goto label_109;
                                    }

                                    v9_1 += 12;
                                }

                            label_109:
                                this.c(this.getCurrentItemShowing(), v9_1);
                                this.e.a(this.getCurrentItemShowing(), v9_1, true);
                            }
                        }

                        this.v = false;
                        return 1;
                    }
                }

                v9_1 = this.m.a(v9, v0);
                this.m.setAmOrPmPressed(v5);
                this.m.invalidate();
                if(v9_1 != this.u) {
                    goto label_133;
                }

                this.m.setAmOrPm(v9_1);
                if(this.getIsCurrentlyAmOrPm() == v9_1) {
                    goto label_133;
                }

                this.e.a(2, this.u, false);
                this.c(2, v9_1);
                goto label_133;
            }
            case 2: {
                if(!this.t) {
                    Log.e("RadialPickerLayout", "Input was disabled, but received ACTION_MOVE.");
                    return 1;
                }

                float v10_3 = Math.abs(v0 - this.z);
                float v6 = Math.abs(v9 - this.y);
                if(!this.v && v6 <= (((float)this.a)) && v10_3 <= (((float)this.a))) {
                    return 0;
                }

                if(this.u != 0) {
                    if(this.u == 1) {
                    }
                    else if(this.x == v5) {
                        return 0;
                    }
                    else {
                        this.v = true;
                        this.C.removeCallbacksAndMessages(v4);
                        v9_1 = this.a(v9, v0, true, v2);
                        if(v9_1 != v5) {
                            v9_1 = this.a(v9_1, v2[0].booleanValue(), false, true);
                            if(v9_1 != this.c) {
                                this.d.c();
                                this.c = v9_1;
                                this.e.a(this.getCurrentItemShowing(), v9_1, false);
                            }
                        }

                        return 1;
                    }
                }

                this.C.removeCallbacksAndMessages(v4);
                if(this.m.a(v9, v0) == this.u) {
                    return 0;
                }

                this.m.setAmOrPmPressed(v5);
                this.m.invalidate();
            label_133:
                this.u = v5;
                break;
            }
            default: {
                break;
            }
        }

        return 0;
    }

    @SuppressLint(value={"NewApi"}) public boolean performAccessibilityAction(int arg5, Bundle arg6) {
        int v3;
        if(super.performAccessibilityAction(arg5, arg6)) {
            return 1;
        }

        if(arg5 == 4096) {
            arg5 = 1;
        }
        else if(arg5 == 8192) {
            arg5 = -1;
        }
        else {
            arg5 = 0;
        }

        if(arg5 != 0) {
            int v6 = this.getCurrentlyShowingValue();
            int v2 = this.getCurrentItemShowing();
            if(v2 == 0) {
                v3 = 30;
                v6 %= 12;
            }
            else if(v2 == 1) {
                v3 = 6;
            }
            else {
                v3 = 0;
            }

            arg5 = RadialPickerLayout.d(v6 * v3, arg5) / v3;
            if(v2 != 0) {
                v6 = 55;
            label_37:
                v3 = 0;
            }
            else if(this.i) {
                v6 = 23;
                goto label_37;
            }
            else {
                v6 = 12;
                v3 = 1;
            }

            if(arg5 > v6) {
                arg5 = v3;
            }
            else if(arg5 < v3) {
                arg5 = v6;
            }

            this.b(v2, arg5);
            this.e.a(v2, arg5, false);
            return 1;
        }

        return 0;
    }

    public void setAmOrPm(int arg2) {
        this.m.setAmOrPm(arg2);
        this.m.invalidate();
        this.c(2, arg2);
    }

    public void setOnValueSelectedListener(a arg1) {
        this.e = arg1;
    }
}

