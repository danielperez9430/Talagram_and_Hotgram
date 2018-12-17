package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.a.c;
import android.support.v4.view.t;
import android.support.v4.widget.j;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View$AccessibilityDelegate;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.mohamadamin.persianmaterialdatetimepicker.a.b;
import com.mohamadamin.persianmaterialdatetimepicker.b$f;
import com.mohamadamin.persianmaterialdatetimepicker.d;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;

public abstract class e extends View {
    public class a extends j {
        private final Rect d;
        private final b e;

        public a(e arg1, View arg2) {
            this.c = arg1;
            super(arg2);
            this.d = new Rect();
            this.e = new b();
        }

        protected int a(float arg2, float arg3) {
            int v2 = this.c.a(arg2, arg3);
            if(v2 >= 0) {
                return v2;
            }

            return -2147483648;
        }

        protected void a(int arg7, Rect arg8) {
            int v0 = this.c.k;
            int v1 = this.c.getMonthHeaderSize();
            int v2 = this.c.v;
            int v3 = (this.c.u - this.c.k * 2) / this.c.A;
            arg7 = arg7 - 1 + this.c.c();
            int v4 = arg7 / this.c.A;
            v0 += arg7 % this.c.A * v3;
            v1 += v4 * v2;
            arg8.set(v0, v1, v3 + v0, v2 + v1);
        }

        protected void a(int arg2, c arg3) {
            this.a(arg2, this.d);
            arg3.d(this.e(arg2));
            arg3.b(this.d);
            arg3.a(16);
            if(arg2 == this.c.x) {
                arg3.g(true);
            }
        }

        protected void a(int arg1, AccessibilityEvent arg2) {
            arg2.setContentDescription(this.e(arg1));
        }

        protected void a(List arg3) {
            int v0;
            for(v0 = 1; v0 <= this.c.B; ++v0) {
                arg3.add(Integer.valueOf(v0));
            }
        }

        protected boolean b(int arg1, int arg2, Bundle arg3) {
            if(arg2 != 16) {
                return 0;
            }

            e.a(this.c, arg1);
            return 1;
        }

        public void d(int arg4) {
            this.getAccessibilityNodeProvider(this.c).a(arg4, 64, null);
        }

        public void d() {
            int v0 = this.c();
            if(v0 != -2147483648) {
                this.getAccessibilityNodeProvider(this.c).a(v0, 128, null);
            }
        }

        protected CharSequence e(int arg5) {
            this.e.a(this.c.t, this.c.s, arg5);
            String v0 = com.mohamadamin.persianmaterialdatetimepicker.a.a.a(this.e.g());
            if(arg5 == this.c.x) {
                return this.c.getContext().getString(f.mdtp_item_is_selected, new Object[]{v0});
            }

            return ((CharSequence)v0);
        }
    }

    public interface com.mohamadamin.persianmaterialdatetimepicker.date.e$b {
        void a(e arg1, com.mohamadamin.persianmaterialdatetimepicker.date.d$a arg2);
    }

    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected final b E;
    protected int F;
    protected com.mohamadamin.persianmaterialdatetimepicker.date.e$b G;
    protected int H;
    protected int I;
    protected int J;
    protected int K;
    protected int L;
    protected int M;
    protected int N;
    private String O;
    private String P;
    private final StringBuilder Q;
    private final b R;
    private final a S;
    private boolean T;
    private int U;
    protected static int a = 32;
    protected static int b = 10;
    protected static int c = 1;
    protected static int d;
    protected static int e;
    protected static int f;
    protected static int g;
    protected static int h;
    protected static float i;
    protected com.mohamadamin.persianmaterialdatetimepicker.date.a j;
    protected int k;
    protected Paint l;
    protected Paint m;
    protected Paint n;
    protected Paint o;
    protected int p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected int v;
    protected boolean w;
    protected int x;
    protected int y;
    protected int z;

    static {
    }

    public e(Context arg3, AttributeSet arg4, com.mohamadamin.persianmaterialdatetimepicker.date.a arg5) {
        super(arg3, arg4);
        int v4 = 0;
        this.k = 0;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.v = e.a;
        this.w = false;
        this.x = -1;
        this.y = -1;
        this.z = 7;
        this.A = 7;
        this.B = this.A;
        this.C = -1;
        this.D = -1;
        int v0 = 6;
        this.F = v0;
        this.U = 0;
        this.j = arg5;
        Resources v3 = arg3.getResources();
        this.E = new b();
        this.R = new b();
        this.O = v3.getString(f.mdtp_day_of_week_label_typeface);
        this.P = v3.getString(f.mdtp_sans_serif);
        if(this.j != null && (this.j.b())) {
            v4 = 1;
        }

        if(v4 != 0) {
            this.H = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_text_normal_dark_theme);
            this.J = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_month_day_dark_theme);
            this.M = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_text_disabled_dark_theme);
            v4 = com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_text_highlighted_dark_theme;
        }
        else {
            this.H = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_text_normal);
            this.J = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_month_day);
            this.M = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_text_disabled);
            v4 = com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_date_picker_text_highlighted;
        }

        this.L = v3.getColor(v4);
        this.I = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white);
        this.K = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_accent_color);
        this.N = v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white);
        this.Q = new StringBuilder(50);
        e.d = v3.getDimensionPixelSize(com.mohamadamin.persianmaterialdatetimepicker.b$b.mdtp_day_number_size);
        e.e = v3.getDimensionPixelSize(com.mohamadamin.persianmaterialdatetimepicker.b$b.mdtp_month_label_size);
        e.f = v3.getDimensionPixelSize(com.mohamadamin.persianmaterialdatetimepicker.b$b.mdtp_month_day_label_text_size);
        e.g = v3.getDimensionPixelOffset(com.mohamadamin.persianmaterialdatetimepicker.b$b.mdtp_month_list_item_header_height);
        e.h = v3.getDimensionPixelSize(com.mohamadamin.persianmaterialdatetimepicker.b$b.mdtp_day_number_select_circle_radius);
        this.v = (v3.getDimensionPixelOffset(com.mohamadamin.persianmaterialdatetimepicker.b$b.mdtp_date_picker_view_animator_height) - this.getMonthHeaderSize()) / v0;
        this.S = this.getMonthViewTouchHelper();
        t.a(((View)this), this.S);
        t.b(((View)this), 1);
        this.T = true;
        this.a();
    }

    protected void a() {
        this.m = new Paint();
        this.m.setFakeBoldText(true);
        this.m.setAntiAlias(true);
        this.m.setTextSize(((float)e.e));
        this.m.setTypeface(Typeface.create(this.P, 1));
        this.m.setColor(this.H);
        this.m.setTextAlign(Paint$Align.CENTER);
        this.m.setStyle(Paint$Style.FILL);
        this.n = new Paint();
        this.n.setFakeBoldText(true);
        this.n.setAntiAlias(true);
        this.n.setColor(this.K);
        this.n.setTextAlign(Paint$Align.CENTER);
        this.n.setStyle(Paint$Style.FILL);
        this.n.setAlpha(255);
        this.o = new Paint();
        this.o.setAntiAlias(true);
        this.o.setTextSize(((float)e.f));
        this.o.setColor(this.J);
        this.o.setTypeface(com.mohamadamin.persianmaterialdatetimepicker.c.a(this.getContext(), "Roboto-Medium"));
        this.o.setStyle(Paint$Style.FILL);
        this.o.setTextAlign(Paint$Align.CENTER);
        this.o.setFakeBoldText(true);
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.l.setTextSize(((float)e.d));
        this.l.setStyle(Paint$Style.FILL);
        this.l.setTextAlign(Paint$Align.CENTER);
        this.l.setFakeBoldText(false);
    }

    private void a(int arg5) {
        if(this.a(this.t, this.s, arg5)) {
            return;
        }

        if(this.G != null) {
            this.G.a(this, new com.mohamadamin.persianmaterialdatetimepicker.date.d$a(this.t, this.s, arg5));
        }

        this.S.a(arg5, 1);
    }

    protected boolean a(int arg3, int arg4, int arg5) {
        if(this.j.d() != null) {
            return this.c(arg3, arg4, arg5) ^ 1;
        }

        if(this.d(arg3, arg4, arg5)) {
            return 1;
        }

        if(this.e(arg3, arg4, arg5)) {
            return 1;
        }

        return 0;
    }

    static void a(e arg0, int arg1) {
        arg0.a(arg1);
    }

    private boolean a(int arg3, b arg4) {
        boolean v3 = this.t != arg4.b() || this.s != arg4.c() || arg3 != arg4.e() ? false : true;
        return v3;
    }

    public int a(float arg1, float arg2) {
        int v1 = this.b(arg1, arg2);
        if(v1 >= 1) {
            if(v1 > this.B) {
            }
            else {
                return v1;
            }
        }

        return -1;
    }

    protected void a(Canvas arg5) {
        arg5.drawText(this.getMonthAndYearString(), ((float)((this.u + this.k * 2) / 2)), ((float)((this.getMonthHeaderSize() - e.f) / 2)), this.m);
    }

    public abstract void a(Canvas arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10);

    public boolean a(com.mohamadamin.persianmaterialdatetimepicker.date.d$a arg3) {
        if(arg3.a == this.t && arg3.b == this.s) {
            if(arg3.c > this.B) {
            }
            else {
                this.S.d(arg3.c);
                return 1;
            }
        }

        return 0;
    }

    protected int b(float arg5, float arg6) {
        int v0 = this.k;
        float v1 = ((float)v0);
        if(arg5 >= v1) {
            if(arg5 > (((float)(this.u - this.k)))) {
            }
            else {
                return (((int)((arg5 - v1) * (((float)this.A)) / (((float)(this.u - v0 - this.k)))))) - this.c() + 1 + (((int)(arg6 - (((float)this.getMonthHeaderSize()))))) / this.v * this.A;
            }
        }

        return -1;
    }

    public void b() {
        this.F = 6;
        this.requestLayout();
    }

    protected void b(Canvas arg10) {
        int v0 = this.getMonthHeaderSize() - e.f / 2;
        int v1 = (this.u - this.k * 2) / (this.A * 2);
        int v3;
        for(v3 = 0; v3 < this.A; ++v3) {
            int v4 = (this.z + v3) % this.A;
            int v5 = (v3 * 2 + 1) * v1 + this.k;
            this.E.set(7, v4);
            arg10.drawText(this.E.f().substring(0, 1), ((float)v5), ((float)v0), this.o);
        }
    }

    protected boolean b(int arg7, int arg8, int arg9) {
        b[] v0 = this.j.c();
        if(v0 == null) {
            return 0;
        }

        int v2 = v0.length;
        int v3 = 0;
        while(true) {
            if(v3 < v2) {
                b v4 = v0[v3];
                if(arg7 < v4.b()) {
                }
                else {
                    if(arg7 > v4.b()) {
                    }
                    else if(arg8 < v4.c()) {
                        return 0;
                    }
                    else if(arg8 > v4.c()) {
                    }
                    else if(arg9 < v4.e()) {
                        return 0;
                    }
                    else {
                        if(arg9 > v4.e()) {
                            goto label_26;
                        }

                        return 1;
                    }

                label_26:
                    ++v3;
                    continue;
                }
            }

            return 0;
        }

        return 1;
    }

    private boolean c(int arg7, int arg8, int arg9) {
        b[] v0 = this.j.d();
        int v1 = v0.length;
        int v3 = 0;
        while(true) {
            if(v3 < v1) {
                b v4 = v0[v3];
                if(arg7 < v4.b()) {
                }
                else {
                    if(arg7 > v4.b()) {
                    }
                    else if(arg8 < v4.c()) {
                        return 0;
                    }
                    else if(arg8 > v4.c()) {
                    }
                    else if(arg9 < v4.e()) {
                        return 0;
                    }
                    else {
                        if(arg9 > v4.e()) {
                            goto label_24;
                        }

                        return 1;
                    }

                label_24:
                    ++v3;
                    continue;
                }
            }

            return 0;
        }

        return 1;
    }

    protected int c() {
        int v0 = this.U < this.z ? this.U + this.A : this.U;
        return v0 - this.z;
    }

    protected void c(Canvas arg18) {
        e v11 = this;
        int v0 = (v11.v + e.d) / 2 - e.c + this.getMonthHeaderSize();
        float v12 = (((float)(v11.u - v11.k * 2))) / ((((float)v11.A)) * 2f);
        int v16 = v0;
        int v15 = this.c();
        int v14;
        for(v14 = 1; v14 <= v11.B; ++v14) {
            int v5 = ((int)((((float)(v15 * 2 + 1))) * v12 + (((float)v11.k))));
            float v1 = ((float)v5);
            int v9 = v16 - ((v11.v + e.d) / 2 - e.c);
            this.a(arg18, v11.t, v11.s, v14, v5, v16, ((int)(v1 - v12)), ((int)(v1 + v12)), v9, v9 + v11.v);
            ++v15;
            if(v15 == v11.A) {
                v16 += v11.v;
                v15 = 0;
            }
        }
    }

    private boolean d(int arg5, int arg6, int arg7) {
        if(this.j == null) {
            return 0;
        }

        b v0 = this.j.h();
        if(v0 == null) {
            return 0;
        }

        if(arg5 < v0.b()) {
            return 1;
        }

        if(arg5 > v0.b()) {
            return 0;
        }

        if(arg6 < v0.c()) {
            return 1;
        }

        if(arg6 > v0.c()) {
            return 0;
        }

        if(arg7 < v0.e()) {
            return 1;
        }

        return 0;
    }

    public void d() {
        this.S.d();
    }

    public boolean dispatchHoverEvent(MotionEvent arg2) {
        if(this.S.a(arg2)) {
            return 1;
        }

        return super.dispatchHoverEvent(arg2);
    }

    private int e() {
        int v0 = this.c();
        int v1 = (this.B + v0) / this.A;
        v0 = (v0 + this.B) % this.A > 0 ? 1 : 0;
        return v1 + v0;
    }

    private boolean e(int arg5, int arg6, int arg7) {
        if(this.j == null) {
            return 0;
        }

        b v0 = this.j.i();
        if(v0 == null) {
            return 0;
        }

        if(arg5 > v0.b()) {
            return 1;
        }

        if(arg5 < v0.b()) {
            return 0;
        }

        if(arg6 > v0.c()) {
            return 1;
        }

        if(arg6 < v0.c()) {
            return 0;
        }

        if(arg7 > v0.c()) {
            return 1;
        }

        return 0;
    }

    public com.mohamadamin.persianmaterialdatetimepicker.date.d$a getAccessibilityFocus() {
        int v0 = this.S.c();
        if(v0 >= 0) {
            return new com.mohamadamin.persianmaterialdatetimepicker.date.d$a(this.t, this.s, v0);
        }

        return null;
    }

    public int getMonth() {
        return this.s;
    }

    private String getMonthAndYearString() {
        this.Q.setLength(0);
        StringBuilder v0 = new StringBuilder();
        v0.append(this.R.d());
        v0.append(" ");
        v0.append(this.R.b());
        return com.mohamadamin.persianmaterialdatetimepicker.a.a.a(v0.toString());
    }

    protected int getMonthHeaderSize() {
        return e.g;
    }

    protected a getMonthViewTouchHelper() {
        return new a(this, ((View)this));
    }

    public int getYear() {
        return this.t;
    }

    protected void onDraw(Canvas arg1) {
        this.a(arg1);
        this.b(arg1);
        this.c(arg1);
    }

    protected void onMeasure(int arg2, int arg3) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg2), this.v * this.F + this.getMonthHeaderSize() + 5);
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        this.u = arg1;
        this.S.b();
    }

    public boolean onTouchEvent(MotionEvent arg3) {
        if(arg3.getAction() != 1) {
        }
        else {
            int v3 = this.a(arg3.getX(), arg3.getY());
            if(v3 >= 0) {
                this.a(v3);
            }
        }

        return 1;
    }

    public void setAccessibilityDelegate(View$AccessibilityDelegate arg2) {
        if(!this.T) {
            super.setAccessibilityDelegate(arg2);
        }
    }

    public void setDatePickerController(com.mohamadamin.persianmaterialdatetimepicker.date.a arg1) {
        this.j = arg1;
    }

    public void setMonthParams(HashMap arg7) {
        if(!arg7.containsKey("month")) {
            if(arg7.containsKey("year")) {
            }
            else {
                throw new InvalidParameterException("You must specify month and year for this view");
            }
        }

        this.setTag(arg7);
        if(arg7.containsKey("height")) {
            this.v = arg7.get("height").intValue();
            if(this.v < e.b) {
                this.v = e.b;
            }
        }

        if(arg7.containsKey("selected_day")) {
            this.x = arg7.get("selected_day").intValue();
        }

        this.s = arg7.get("month").intValue();
        this.t = arg7.get("year").intValue();
        b v0 = new b();
        int v1 = 0;
        this.w = false;
        this.y = -1;
        this.R.a(this.t, this.s, 1);
        int v3 = 7;
        this.U = this.R.get(v3);
        this.z = arg7.containsKey("week_start") ? arg7.get("week_start").intValue() : v3;
        this.B = d.a(this.s, this.t);
        while(v1 < this.B) {
            ++v1;
            if(!this.a(v1, v0)) {
                continue;
            }

            this.w = true;
            this.y = v1;
        }

        this.F = this.e();
        this.S.b();
    }

    public void setOnDayClickListener(com.mohamadamin.persianmaterialdatetimepicker.date.e$b arg1) {
        this.G = arg1;
    }

    public void setSelectedDay(int arg1) {
        this.x = arg1;
    }
}

