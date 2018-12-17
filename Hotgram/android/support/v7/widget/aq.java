package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.t;
import android.support.v4.widget.o;
import android.support.v7.a.a$j;
import android.support.v7.view.menu.s;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class aq implements s {
    class a implements Runnable {
        a(aq arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.m();
        }
    }

    class b extends DataSetObserver {
        b(aq arg1) {
            this.a = arg1;
            super();
        }

        public void onChanged() {
            if(this.a.d()) {
                this.a.a();
            }
        }

        public void onInvalidated() {
            this.a.c();
        }
    }

    class c implements AbsListView$OnScrollListener {
        c(aq arg1) {
            this.a = arg1;
            super();
        }

        public void onScroll(AbsListView arg1, int arg2, int arg3, int arg4) {
        }

        public void onScrollStateChanged(AbsListView arg1, int arg2) {
            if(arg2 == 1 && !this.a.n() && this.a.g.getContentView() != null) {
                this.a.f.removeCallbacks(this.a.e);
                this.a.e.run();
            }
        }
    }

    class d implements View$OnTouchListener {
        d(aq arg1) {
            this.a = arg1;
            super();
        }

        public boolean onTouch(View arg3, MotionEvent arg4) {
            int v3 = arg4.getAction();
            int v0 = ((int)arg4.getX());
            int v4 = ((int)arg4.getY());
            if(v3 == 0 && this.a.g != null && (this.a.g.isShowing()) && v0 >= 0 && v0 < this.a.g.getWidth() && v4 >= 0 && v4 < this.a.g.getHeight()) {
                this.a.f.postDelayed(this.a.e, 250);
            }
            else if(v3 == 1) {
                this.a.f.removeCallbacks(this.a.e);
            }

            return 0;
        }
    }

    class e implements Runnable {
        e(aq arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if(this.a.c != null && (t.D(this.a.c)) && this.a.c.getCount() > this.a.c.getChildCount() && this.a.c.getChildCount() <= this.a.d) {
                this.a.g.setInputMethodMode(2);
                this.a.a();
            }
        }
    }

    private Drawable A;
    private AdapterView$OnItemClickListener B;
    private AdapterView$OnItemSelectedListener C;
    private final d D;
    private final c E;
    private final a F;
    private Runnable G;
    private final Rect H;
    private Rect I;
    private boolean J;
    private static Method a;
    private static Method b;
    aj c;
    int d;
    final e e;
    final Handler f;
    PopupWindow g;
    private static Method h;
    private Context i;
    private ListAdapter j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;
    private boolean v;
    private View w;
    private int x;
    private DataSetObserver y;
    private View z;

    static {
        try {
            aq.a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        }
        catch(NoSuchMethodException ) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }

        try {
            aq.b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        }
        catch(NoSuchMethodException ) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }

        try {
            aq.h = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        }
        catch(NoSuchMethodException ) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public aq(Context arg3) {
        this(arg3, null, android.support.v7.a.a$a.listPopupWindowStyle);
    }

    public aq(Context arg2, AttributeSet arg3, int arg4) {
        this(arg2, arg3, arg4, 0);
    }

    public aq(Context arg5, AttributeSet arg6, int arg7, int arg8) {
        super();
        this.k = -2;
        this.l = -2;
        this.o = 1002;
        this.q = true;
        this.t = 0;
        this.u = false;
        this.v = false;
        this.d = 2147483647;
        this.x = 0;
        this.e = new e(this);
        this.D = new d(this);
        this.E = new c(this);
        this.F = new a(this);
        this.H = new Rect();
        this.i = arg5;
        this.f = new Handler(arg5.getMainLooper());
        TypedArray v2 = arg5.obtainStyledAttributes(arg6, j.ListPopupWindow, arg7, arg8);
        this.m = v2.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.n = v2.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if(this.n != 0) {
            this.p = true;
        }

        v2.recycle();
        this.g = new p(arg5, arg6, arg7, arg8);
        this.g.setInputMethodMode(1);
    }

    public void a() {
        int v1_2;
        PopupWindow v1_1;
        int v2;
        int v0 = this.f();
        boolean v1 = this.n();
        o.a(this.g, this.o);
        boolean v3 = true;
        int v4 = -2;
        int v6 = -1;
        if(this.g.isShowing()) {
            if(!t.D(this.i())) {
                return;
            }

            if(this.l == v6) {
                v2 = -1;
            }
            else if(this.l == v4) {
                v2 = this.i().getWidth();
            }
            else {
                v2 = this.l;
            }

            if(this.k == v6) {
                if(v1) {
                }
                else {
                    v0 = -1;
                }

                if(v1) {
                    v1_1 = this.g;
                    v4 = this.l == v6 ? -1 : 0;
                    v1_1.setWidth(v4);
                    this.g.setHeight(0);
                    goto label_56;
                }

                v1_1 = this.g;
                v4 = this.l == v6 ? -1 : 0;
                v1_1.setWidth(v4);
                this.g.setHeight(v6);
            }
            else {
                if(this.k == v4) {
                    goto label_56;
                }

                v0 = this.k;
            }

        label_56:
            v1_1 = this.g;
            if((this.v) || (this.u)) {
                v3 = false;
            }
            else {
            }

            v1_1.setOutsideTouchable(v3);
            PopupWindow v7 = this.g;
            View v8 = this.i();
            int v9 = this.m;
            int v10 = this.n;
            int v11 = v2 < 0 ? -1 : v2;
            int v12 = v0 < 0 ? -1 : v0;
            v7.update(v8, v9, v10, v11, v12);
            return;
        }

        if(this.l == v6) {
            v1_2 = -1;
        }
        else if(this.l == v4) {
            v1_2 = this.i().getWidth();
        }
        else {
            v1_2 = this.l;
        }

        if(this.k == v6) {
            v0 = -1;
        }
        else if(this.k == v4) {
        }
        else {
            v0 = this.k;
        }

        this.g.setWidth(v1_2);
        this.g.setHeight(v0);
        this.c(true);
        PopupWindow v0_1 = this.g;
        v1 = (this.v) || (this.u) ? false : true;
        v0_1.setOutsideTouchable(v1);
        this.g.setTouchInterceptor(this.D);
        if(this.s) {
            o.a(this.g, this.r);
        }

        if(aq.h != null) {
            try {
                aq.h.invoke(this.g, this.I);
            }
            catch(Exception v0_2) {
                Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", ((Throwable)v0_2));
            }
        }

        o.a(this.g, this.i(), this.m, this.n, this.t);
        this.c.setSelection(v6);
        if(!this.J || (this.c.isInTouchMode())) {
            this.m();
        }

        if(!this.J) {
            this.f.post(this.F);
        }
    }

    public void a(ListAdapter arg3) {
        if(this.y == null) {
            this.y = new b(this);
        }
        else if(this.j != null) {
            this.j.unregisterDataSetObserver(this.y);
        }

        this.j = arg3;
        if(arg3 != null) {
            arg3.registerDataSetObserver(this.y);
        }

        if(this.c != null) {
            this.c.setAdapter(this.j);
        }
    }

    public void a(boolean arg2) {
        this.J = arg2;
        this.g.setFocusable(arg2);
    }

    public void a(AdapterView$OnItemClickListener arg1) {
        this.B = arg1;
    }

    public void a(PopupWindow$OnDismissListener arg2) {
        this.g.setOnDismissListener(arg2);
    }

    private int a(View arg6, int arg7, boolean arg8) {
        if(aq.b != null) {
            try {
                return aq.b.invoke(this.g, arg6, Integer.valueOf(arg7), Boolean.valueOf(arg8)).intValue();
            }
            catch(Exception ) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }

        return this.g.getMaxAvailableHeight(arg6, arg7);
    }

    aj a(Context arg2, boolean arg3) {
        return new aj(arg2, arg3);
    }

    public void a(int arg1) {
        this.x = arg1;
    }

    public void a(Rect arg1) {
        this.I = arg1;
    }

    public void a(Drawable arg2) {
        this.g.setBackgroundDrawable(arg2);
    }

    public void b(View arg1) {
        this.z = arg1;
    }

    private void b() {
        if(this.w != null) {
            ViewParent v0 = this.w.getParent();
            if((v0 instanceof ViewGroup)) {
                ((ViewGroup)v0).removeView(this.w);
            }
        }
    }

    public void b(int arg2) {
        this.g.setAnimationStyle(arg2);
    }

    public void b(boolean arg2) {
        this.s = true;
        this.r = arg2;
    }

    public void c() {
        this.g.dismiss();
        this.b();
        this.g.setContentView(null);
        this.c = null;
        this.f.removeCallbacks(this.e);
    }

    private void c(boolean arg5) {
        if(aq.a != null) {
            try {
                aq.a.invoke(this.g, Boolean.valueOf(arg5));
            }
            catch(Exception ) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void c(int arg1) {
        this.m = arg1;
    }

    public boolean d() {
        return this.g.isShowing();
    }

    public void d(int arg1) {
        this.n = arg1;
        this.p = true;
    }

    public ListView e() {
        return this.c;
    }

    public void e(int arg1) {
        this.t = arg1;
    }

    private int f() {
        int v5_2;
        int v0_2;
        int v1 = -2147483648;
        int v2 = -1;
        boolean v3 = true;
        if(this.c == null) {
            Context v0 = this.i;
            this.G = new Runnable() {
                public void run() {
                    View v0 = this.a.i();
                    if(v0 != null && v0.getWindowToken() != null) {
                        this.a.a();
                    }
                }
            };
            this.c = this.a(v0, this.J ^ 1);
            if(this.A != null) {
                this.c.setSelector(this.A);
            }

            this.c.setAdapter(this.j);
            this.c.setOnItemClickListener(this.B);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(AdapterView arg1, View arg2, int arg3, long arg4) {
                    if(arg3 != -1) {
                        aj v1 = this.a.c;
                        if(v1 != null) {
                            v1.setListSelectionHidden(false);
                        }
                    }
                }

                public void onNothingSelected(AdapterView arg1) {
                }
            });
            this.c.setOnScrollListener(this.E);
            if(this.C != null) {
                this.c.setOnItemSelectedListener(this.C);
            }

            aj v5 = this.c;
            View v6 = this.w;
            if(v6 != null) {
                LinearLayout v7 = new LinearLayout(v0);
                v7.setOrientation(1);
                LinearLayout$LayoutParams v0_1 = new LinearLayout$LayoutParams(v2, 0, 1f);
                switch(this.x) {
                    case 0: {
                        v7.addView(v6);
                        v7.addView(((View)v5), ((ViewGroup$LayoutParams)v0_1));
                        break;
                    }
                    case 1: {
                        v7.addView(((View)v5), ((ViewGroup$LayoutParams)v0_1));
                        v7.addView(v6);
                        break;
                    }
                    default: {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.x);
                        break;
                    }
                }

                if(this.l >= 0) {
                    v0_2 = this.l;
                    v5_2 = -2147483648;
                }
                else {
                    v0_2 = 0;
                    v5_2 = 0;
                }

                v6.measure(View$MeasureSpec.makeMeasureSpec(v0_2, v5_2), 0);
                ViewGroup$LayoutParams v0_3 = v6.getLayoutParams();
                v0_2 = v6.getMeasuredHeight() + ((LinearLayout$LayoutParams)v0_3).topMargin + ((LinearLayout$LayoutParams)v0_3).bottomMargin;
                LinearLayout v5_3 = v7;
            }
            else {
                v0_2 = 0;
            }

            this.g.setContentView(((View)v5));
        }
        else {
            this.g.getContentView();
            View v0_4 = this.w;
            if(v0_4 != null) {
                ViewGroup$LayoutParams v5_4 = v0_4.getLayoutParams();
                v0_2 = v0_4.getMeasuredHeight() + ((LinearLayout$LayoutParams)v5_4).topMargin + ((LinearLayout$LayoutParams)v5_4).bottomMargin;
                goto label_101;
            }

            v0_2 = 0;
        }

    label_101:
        Drawable v5_5 = this.g.getBackground();
        if(v5_5 != null) {
            v5_5.getPadding(this.H);
            v5_2 = this.H.top + this.H.bottom;
            if(!this.p) {
                this.n = -this.H.top;
            }
        }
        else {
            this.H.setEmpty();
            v5_2 = 0;
        }

        if(this.g.getInputMethodMode() == 2) {
        }
        else {
            v3 = false;
        }

        int v3_1 = this.a(this.i(), this.n, v3);
        if(!this.u) {
            if(this.k == v2) {
            }
            else {
                int v4 = 1073741824;
                switch(this.l) {
                    case -2: {
                        goto label_153;
                    }
                    case -1: {
                        goto label_142;
                    }
                }

                v1 = this.l;
                goto label_139;
            label_153:
                v1 = View$MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), v1);
                goto label_140;
            label_142:
                v1 = this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right);
            label_139:
                v1 = View$MeasureSpec.makeMeasureSpec(v1, v4);
            label_140:
                int v7_1 = v1;
                v1 = this.c.a(v7_1, 0, -1, v3_1 - v0_2, -1);
                if(v1 > 0) {
                    v0_2 += v5_2 + (this.c.getPaddingTop() + this.c.getPaddingBottom());
                }

                return v1 + v0_2;
            }
        }

        return v3_1 + v5_2;
    }

    public void f(int arg1) {
        this.l = arg1;
    }

    public void g(int arg3) {
        Drawable v0 = this.g.getBackground();
        if(v0 != null) {
            v0.getPadding(this.H);
            this.l = this.H.left + this.H.right + arg3;
        }
        else {
            this.f(arg3);
        }
    }

    public boolean g() {
        return this.J;
    }

    public Drawable h() {
        return this.g.getBackground();
    }

    public void h(int arg2) {
        this.g.setInputMethodMode(arg2);
    }

    public View i() {
        return this.z;
    }

    public void i(int arg3) {
        aj v0 = this.c;
        if((this.d()) && v0 != null) {
            v0.setListSelectionHidden(false);
            v0.setSelection(arg3);
            if(v0.getChoiceMode() != 0) {
                v0.setItemChecked(arg3, true);
            }
        }
    }

    public int j() {
        return this.m;
    }

    public int k() {
        if(!this.p) {
            return 0;
        }

        return this.n;
    }

    public int l() {
        return this.l;
    }

    public void m() {
        aj v0 = this.c;
        if(v0 != null) {
            v0.setListSelectionHidden(true);
            v0.requestLayout();
        }
    }

    public boolean n() {
        boolean v0 = this.g.getInputMethodMode() == 2 ? true : false;
        return v0;
    }
}

