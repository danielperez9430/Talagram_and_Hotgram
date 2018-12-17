package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.a.a$d;
import android.support.v7.a.a$f;
import android.support.v7.a.a$g;
import android.support.v7.a.a$i;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.WindowManager$LayoutParams;
import android.widget.TextView;

class bo {
    private final Context a;
    private final View b;
    private final TextView c;
    private final WindowManager$LayoutParams d;
    private final Rect e;
    private final int[] f;
    private final int[] g;

    bo(Context arg3) {
        super();
        this.d = new WindowManager$LayoutParams();
        this.e = new Rect();
        this.f = new int[2];
        this.g = new int[2];
        this.a = arg3;
        this.b = LayoutInflater.from(this.a).inflate(g.abc_tooltip, null);
        this.c = this.b.findViewById(f.message);
        this.d.setTitle(this.getClass().getSimpleName());
        this.d.packageName = this.a.getPackageName();
        this.d.type = 1002;
        this.d.width = -2;
        this.d.height = -2;
        this.d.format = -3;
        this.d.windowAnimations = i.Animation_AppCompat_Tooltip;
        this.d.flags = 24;
    }

    void a() {
        if(!this.b()) {
            return;
        }

        this.a.getSystemService("window").removeView(this.b);
    }

    void a(View arg8, int arg9, int arg10, boolean arg11, CharSequence arg12) {
        if(this.b()) {
            this.a();
        }

        this.c.setText(arg12);
        this.a(arg8, arg9, arg10, arg11, this.d);
        this.a.getSystemService("window").addView(this.b, this.d);
    }

    private static View a(View arg3) {
        View v0 = arg3.getRootView();
        ViewGroup$LayoutParams v1 = v0.getLayoutParams();
        if(((v1 instanceof WindowManager$LayoutParams)) && ((WindowManager$LayoutParams)v1).type == 2) {
            return v0;
        }

        Context v3;
        for(v3 = arg3.getContext(); (v3 instanceof ContextWrapper); v3 = ((ContextWrapper)v3).getBaseContext()) {
            if((v3 instanceof Activity)) {
                return ((Activity)v3).getWindow().getDecorView();
            }
        }

        return v0;
    }

    private void a(View arg9, int arg10, int arg11, boolean arg12, WindowManager$LayoutParams arg13) {
        int v1;
        arg13.token = arg9.getApplicationWindowToken();
        int v0 = this.a.getResources().getDimensionPixelOffset(d.tooltip_precise_anchor_threshold);
        if(arg9.getWidth() >= v0) {
        }
        else {
            arg10 = arg9.getWidth() / 2;
        }

        if(arg9.getHeight() >= v0) {
            v0 = this.a.getResources().getDimensionPixelOffset(d.tooltip_precise_anchor_extra_offset);
            v1 = arg11 + v0;
            arg11 -= v0;
        }
        else {
            v1 = arg9.getHeight();
            arg11 = 0;
        }

        arg13.gravity = 49;
        Resources v0_1 = this.a.getResources();
        int v3 = arg12 ? d.tooltip_y_offset_touch : d.tooltip_y_offset_non_touch;
        v0 = v0_1.getDimensionPixelOffset(v3);
        View v3_1 = bo.a(arg9);
        if(v3_1 == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }

        v3_1.getWindowVisibleDisplayFrame(this.e);
        if(this.e.left < 0 && this.e.top < 0) {
            Resources v4 = this.a.getResources();
            int v5 = v4.getIdentifier("status_bar_height", "dimen", "android");
            v5 = v5 != 0 ? v4.getDimensionPixelSize(v5) : 0;
            DisplayMetrics v4_1 = v4.getDisplayMetrics();
            this.e.set(0, v5, v4_1.widthPixels, v4_1.heightPixels);
        }

        v3_1.getLocationOnScreen(this.g);
        arg9.getLocationOnScreen(this.f);
        this.f[0] -= this.g[0];
        this.f[1] -= this.g[1];
        arg13.x = this.f[0] + arg10 - v3_1.getWidth() / 2;
        int v9 = View$MeasureSpec.makeMeasureSpec(0, 0);
        this.b.measure(v9, v9);
        v9 = this.b.getMeasuredHeight();
        arg10 = this.f[1] + arg11 - v0 - v9;
        arg11 = this.f[1] + v1 + v0;
        if(arg12) {
            if(arg10 < 0) {
                goto label_103;
            }

            goto label_101;
        }
        else if(v9 + arg11 <= this.e.height()) {
        label_103:
            arg13.y = arg11;
        }
        else {
        label_101:
            arg13.y = arg10;
        }
    }

    boolean b() {
        boolean v0 = this.b.getParent() != null ? true : false;
        return v0;
    }
}

