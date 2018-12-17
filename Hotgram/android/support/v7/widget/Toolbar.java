package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.d;
import android.support.v4.view.g;
import android.support.v4.view.t;
import android.support.v7.view.c;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.u;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
    class android.support.v7.widget.Toolbar$1 implements e {
        android.support.v7.widget.Toolbar$1(Toolbar arg1) {
            this.a = arg1;
            super();
        }

        public boolean a(MenuItem arg2) {
            if(this.a.e != null) {
                return this.a.e.a(arg2);
            }

            return 0;
        }
    }

    class android.support.v7.widget.Toolbar$2 implements Runnable {
        android.support.v7.widget.Toolbar$2(Toolbar arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.d();
        }
    }

    public class SavedState extends AbsSavedState {
        final class android.support.v7.widget.Toolbar$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v7.widget.Toolbar$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState a(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.a(arg1, arg2);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        int a;
        boolean b;

        static {
            SavedState.CREATOR = new android.support.v7.widget.Toolbar$SavedState$1();
        }

        public SavedState(Parcelable arg1) {
            super(arg1);
        }

        public SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.a = arg1.readInt();
            boolean v1 = arg1.readInt() != 0 ? true : false;
            this.b = v1;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.a);
            arg1.writeInt(this.b);
        }
    }

    class a implements o {
        h a;
        j b;

        a(Toolbar arg1) {
            this.c = arg1;
            super();
        }

        public void a(Context arg2, h arg3) {
            if(this.a != null && this.b != null) {
                this.a.d(this.b);
            }

            this.a = arg3;
        }

        public void a(h arg1, boolean arg2) {
        }

        public void a(android.support.v7.view.menu.o$a arg1) {
        }

        public boolean a(h arg3, j arg4) {
            this.c.i();
            ViewParent v3 = this.c.b.getParent();
            if(v3 != this.c) {
                if((v3 instanceof ViewGroup)) {
                    ((ViewGroup)v3).removeView(this.c.b);
                }

                this.c.addView(this.c.b);
            }

            this.c.c = arg4.getActionView();
            this.b = arg4;
            v3 = this.c.c.getParent();
            if(v3 != this.c) {
                if((v3 instanceof ViewGroup)) {
                    ((ViewGroup)v3).removeView(this.c.c);
                }

                b v3_1 = this.c.j();
                v3_1.a = 8388611 | this.c.d & 112;
                v3_1.b = 2;
                this.c.c.setLayoutParams(((ViewGroup$LayoutParams)v3_1));
                this.c.addView(this.c.c);
            }

            this.c.k();
            this.c.requestLayout();
            arg4.e(true);
            if((this.c.c instanceof c)) {
                this.c.c.a();
            }

            return 1;
        }

        public boolean a(u arg1) {
            return 0;
        }

        public void b(boolean arg5) {
            if(this.b != null) {
                int v0 = 0;
                if(this.a != null) {
                    int v5 = this.a.size();
                    int v1 = 0;
                    while(v1 < v5) {
                        if(this.a.getItem(v1) == this.b) {
                            v0 = 1;
                        }
                        else {
                            ++v1;
                            continue;
                        }

                        break;
                    }
                }

                if(v0 != 0) {
                    return;
                }

                this.b(this.a, this.b);
            }
        }

        public boolean b(h arg2, j arg3) {
            if((this.c.c instanceof c)) {
                this.c.c.b();
            }

            this.c.removeView(this.c.c);
            this.c.removeView(this.c.b);
            this.c.c = null;
            this.c.l();
            this.b = null;
            this.c.requestLayout();
            arg3.e(false);
            return 1;
        }

        public boolean b() {
            return 0;
        }
    }

    public class b extends android.support.v7.app.a$a {
        int b;

        public b(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
            this.b = 0;
        }

        public b(b arg2) {
            super(((android.support.v7.app.a$a)arg2));
            this.b = 0;
            this.b = arg2.b;
        }

        public b(android.support.v7.app.a$a arg1) {
            super(arg1);
            this.b = 0;
        }

        public b(ViewGroup$MarginLayoutParams arg2) {
            super(((ViewGroup$LayoutParams)arg2));
            this.b = 0;
            this.a(arg2);
        }

        public b(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.b = 0;
        }

        public b(int arg1, int arg2) {
            super(arg1, arg2);
            this.b = 0;
            this.a = 8388627;
        }

        void a(ViewGroup$MarginLayoutParams arg2) {
            this.leftMargin = arg2.leftMargin;
            this.topMargin = arg2.topMargin;
            this.rightMargin = arg2.rightMargin;
            this.bottomMargin = arg2.bottomMargin;
        }
    }

    public interface android.support.v7.widget.Toolbar$c {
        boolean a(MenuItem arg1);
    }

    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private final ArrayList E;
    private final ArrayList F;
    private final int[] G;
    private final e H;
    private bl I;
    private android.support.v7.widget.c J;
    private a K;
    private android.support.v7.view.menu.o$a L;
    private android.support.v7.view.menu.h$a M;
    private boolean N;
    private final Runnable O;
    private ActionMenuView a;
    ImageButton b;
    View c;
    int d;
    android.support.v7.widget.Toolbar$c e;
    private TextView f;
    private TextView g;
    private ImageButton h;
    private ImageView i;
    private Drawable j;
    private CharSequence k;
    private Context l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private ba u;
    private int v;
    private int w;
    private int x;
    private CharSequence y;
    private CharSequence z;

    public Toolbar(Context arg2) {
        this(arg2, null);
    }

    public Toolbar(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, android.support.v7.a.a$a.toolbarStyle);
    }

    public Toolbar(Context arg7, AttributeSet arg8, int arg9) {
        super(arg7, arg8, arg9);
        this.x = 8388627;
        this.E = new ArrayList();
        this.F = new ArrayList();
        this.G = new int[2];
        this.H = new android.support.v7.widget.Toolbar$1(this);
        this.O = new android.support.v7.widget.Toolbar$2(this);
        bk v7 = bk.a(this.getContext(), arg8, android.support.v7.a.a$j.Toolbar, arg9, 0);
        this.n = v7.g(android.support.v7.a.a$j.Toolbar_titleTextAppearance, 0);
        this.o = v7.g(android.support.v7.a.a$j.Toolbar_subtitleTextAppearance, 0);
        this.x = v7.c(android.support.v7.a.a$j.Toolbar_android_gravity, this.x);
        this.d = v7.c(android.support.v7.a.a$j.Toolbar_buttonGravity, 48);
        int v8 = v7.d(android.support.v7.a.a$j.Toolbar_titleMargin, 0);
        if(v7.g(android.support.v7.a.a$j.Toolbar_titleMargins)) {
            v8 = v7.d(android.support.v7.a.a$j.Toolbar_titleMargins, v8);
        }

        this.t = v8;
        this.s = v8;
        this.r = v8;
        this.q = v8;
        arg9 = -1;
        v8 = v7.d(android.support.v7.a.a$j.Toolbar_titleMarginStart, arg9);
        if(v8 >= 0) {
            this.q = v8;
        }

        v8 = v7.d(android.support.v7.a.a$j.Toolbar_titleMarginEnd, arg9);
        if(v8 >= 0) {
            this.r = v8;
        }

        v8 = v7.d(android.support.v7.a.a$j.Toolbar_titleMarginTop, arg9);
        if(v8 >= 0) {
            this.s = v8;
        }

        v8 = v7.d(android.support.v7.a.a$j.Toolbar_titleMarginBottom, arg9);
        if(v8 >= 0) {
            this.t = v8;
        }

        this.p = v7.e(android.support.v7.a.a$j.Toolbar_maxButtonHeight, arg9);
        int v0 = -2147483648;
        v8 = v7.d(android.support.v7.a.a$j.Toolbar_contentInsetStart, v0);
        int v2 = v7.d(android.support.v7.a.a$j.Toolbar_contentInsetEnd, v0);
        int v3 = v7.e(android.support.v7.a.a$j.Toolbar_contentInsetLeft, 0);
        int v4 = v7.e(android.support.v7.a.a$j.Toolbar_contentInsetRight, 0);
        this.s();
        this.u.b(v3, v4);
        if(v8 != v0 || v2 != v0) {
            this.u.a(v8, v2);
        }

        this.v = v7.d(android.support.v7.a.a$j.Toolbar_contentInsetStartWithNavigation, v0);
        this.w = v7.d(android.support.v7.a.a$j.Toolbar_contentInsetEndWithActions, v0);
        this.j = v7.a(android.support.v7.a.a$j.Toolbar_collapseIcon);
        this.k = v7.c(android.support.v7.a.a$j.Toolbar_collapseContentDescription);
        CharSequence v8_1 = v7.c(android.support.v7.a.a$j.Toolbar_title);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setTitle(v8_1);
        }

        v8_1 = v7.c(android.support.v7.a.a$j.Toolbar_subtitle);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setSubtitle(v8_1);
        }

        this.l = this.getContext();
        this.setPopupTheme(v7.g(android.support.v7.a.a$j.Toolbar_popupTheme, 0));
        Drawable v8_2 = v7.a(android.support.v7.a.a$j.Toolbar_navigationIcon);
        if(v8_2 != null) {
            this.setNavigationIcon(v8_2);
        }

        v8_1 = v7.c(android.support.v7.a.a$j.Toolbar_navigationContentDescription);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setNavigationContentDescription(v8_1);
        }

        v8_2 = v7.a(android.support.v7.a.a$j.Toolbar_logo);
        if(v8_2 != null) {
            this.setLogo(v8_2);
        }

        v8_1 = v7.c(android.support.v7.a.a$j.Toolbar_logoDescription);
        if(!TextUtils.isEmpty(v8_1)) {
            this.setLogoDescription(v8_1);
        }

        if(v7.g(android.support.v7.a.a$j.Toolbar_titleTextColor)) {
            this.setTitleTextColor(v7.b(android.support.v7.a.a$j.Toolbar_titleTextColor, arg9));
        }

        if(v7.g(android.support.v7.a.a$j.Toolbar_subtitleTextColor)) {
            this.setSubtitleTextColor(v7.b(android.support.v7.a.a$j.Toolbar_subtitleTextColor, arg9));
        }

        v7.a();
    }

    private int a(int arg2) {
        arg2 &= 112;
        if(arg2 != 16 && arg2 != 48 && arg2 != 80) {
            arg2 = this.x & 112;
        }

        return arg2;
    }

    private int a(View arg7, int arg8) {
        ViewGroup$LayoutParams v0 = arg7.getLayoutParams();
        int v7 = arg7.getMeasuredHeight();
        arg8 = arg8 > 0 ? (v7 - arg8) / 2 : 0;
        int v2 = this.a(((b)v0).a);
        if(v2 != 48) {
            if(v2 != 80) {
                arg8 = this.getPaddingTop();
                v2 = this.getPaddingBottom();
                int v3 = this.getHeight();
                int v4 = (v3 - arg8 - v2 - v7) / 2;
                if(v4 < ((b)v0).topMargin) {
                    v4 = ((b)v0).topMargin;
                }
                else {
                    v3 = v3 - v2 - v7 - v4 - arg8;
                    if(v3 < ((b)v0).bottomMargin) {
                        v4 = Math.max(0, v4 - (((b)v0).bottomMargin - v3));
                    }
                }

                return arg8 + v4;
            }

            return this.getHeight() - this.getPaddingBottom() - v7 - ((b)v0).bottomMargin - arg8;
        }

        return this.getPaddingTop() - arg8;
    }

    private int a(View arg8, int arg9, int arg10, int arg11, int arg12, int[] arg13) {
        ViewGroup$LayoutParams v0 = arg8.getLayoutParams();
        int v1 = ((ViewGroup$MarginLayoutParams)v0).leftMargin - arg13[0];
        int v3 = ((ViewGroup$MarginLayoutParams)v0).rightMargin - arg13[1];
        int v5 = Math.max(0, v1) + Math.max(0, v3);
        arg13[0] = Math.max(0, -v1);
        arg13[1] = Math.max(0, -v3);
        arg8.measure(Toolbar.getChildMeasureSpec(arg9, this.getPaddingLeft() + this.getPaddingRight() + v5 + arg10, ((ViewGroup$MarginLayoutParams)v0).width), Toolbar.getChildMeasureSpec(arg11, this.getPaddingTop() + this.getPaddingBottom() + ((ViewGroup$MarginLayoutParams)v0).topMargin + ((ViewGroup$MarginLayoutParams)v0).bottomMargin + arg12, ((ViewGroup$MarginLayoutParams)v0).height));
        return arg8.getMeasuredWidth() + v5;
    }

    private int a(View arg5, int arg6, int[] arg7, int arg8) {
        ViewGroup$LayoutParams v0 = arg5.getLayoutParams();
        int v1 = ((b)v0).leftMargin - arg7[0];
        arg6 += Math.max(0, v1);
        arg7[0] = Math.max(0, -v1);
        int v7 = this.a(arg5, arg8);
        arg8 = arg5.getMeasuredWidth();
        arg5.layout(arg6, v7, arg6 + arg8, arg5.getMeasuredHeight() + v7);
        return arg6 + (arg8 + ((b)v0).rightMargin);
    }

    private int a(List arg9, int[] arg10) {
        int v1 = arg10[0];
        int v10 = arg10[1];
        int v2 = arg9.size();
        int v3 = v10;
        v10 = 0;
        int v4 = 0;
        while(v10 < v2) {
            Object v5 = arg9.get(v10);
            ViewGroup$LayoutParams v6 = ((View)v5).getLayoutParams();
            int v7 = ((b)v6).leftMargin - v1;
            v1 = ((b)v6).rightMargin - v3;
            v3 = Math.max(0, v7);
            int v6_1 = Math.max(0, v1);
            v7 = Math.max(0, -v7);
            v1 = Math.max(0, -v1);
            v4 += v3 + ((View)v5).getMeasuredWidth() + v6_1;
            ++v10;
            v3 = v1;
            v1 = v7;
        }

        return v4;
    }

    private void a(View arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
        ViewGroup$LayoutParams v0 = arg4.getLayoutParams();
        arg5 = Toolbar.getChildMeasureSpec(arg5, this.getPaddingLeft() + this.getPaddingRight() + ((ViewGroup$MarginLayoutParams)v0).leftMargin + ((ViewGroup$MarginLayoutParams)v0).rightMargin + arg6, ((ViewGroup$MarginLayoutParams)v0).width);
        arg6 = Toolbar.getChildMeasureSpec(arg7, this.getPaddingTop() + this.getPaddingBottom() + ((ViewGroup$MarginLayoutParams)v0).topMargin + ((ViewGroup$MarginLayoutParams)v0).bottomMargin + arg8, ((ViewGroup$MarginLayoutParams)v0).height);
        arg7 = View$MeasureSpec.getMode(arg6);
        arg8 = 1073741824;
        if(arg7 != arg8 && arg9 >= 0) {
            if(arg7 != 0) {
                arg9 = Math.min(View$MeasureSpec.getSize(arg6), arg9);
            }

            arg6 = View$MeasureSpec.makeMeasureSpec(arg9, arg8);
        }

        arg4.measure(arg5, arg6);
    }

    private void a(View arg3, boolean arg4) {
        b v0_1;
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        if(v0 == null) {
            v0_1 = this.j();
        }
        else if(!this.checkLayoutParams(v0)) {
            v0_1 = this.a(v0);
        }

        v0_1.b = 1;
        if(!arg4 || this.c == null) {
            this.addView(arg3, ((ViewGroup$LayoutParams)v0_1));
        }
        else {
            arg3.setLayoutParams(((ViewGroup$LayoutParams)v0_1));
            this.F.add(arg3);
        }
    }

    protected b a(ViewGroup$LayoutParams arg2) {
        if((arg2 instanceof b)) {
            return new b(((b)arg2));
        }

        if((arg2 instanceof android.support.v7.app.a$a)) {
            return new b(((android.support.v7.app.a$a)arg2));
        }

        if((arg2 instanceof ViewGroup$MarginLayoutParams)) {
            return new b(((ViewGroup$MarginLayoutParams)arg2));
        }

        return new b(arg2);
    }

    private void a(List arg6, int arg7) {
        View v0_1;
        int v1 = 0;
        int v0 = t.f(((View)this)) == 1 ? 1 : 0;
        int v3 = this.getChildCount();
        arg7 = d.a(arg7, t.f(((View)this)));
        arg6.clear();
        if(v0 != 0) {
            --v3;
            while(v3 >= 0) {
                v0_1 = this.getChildAt(v3);
                ViewGroup$LayoutParams v1_1 = v0_1.getLayoutParams();
                if(((b)v1_1).b == 0 && (this.a(v0_1)) && this.b(((b)v1_1).a) == arg7) {
                    arg6.add(v0_1);
                }

                --v3;
            }
        }
        else {
            while(v1 < v3) {
                v0_1 = this.getChildAt(v1);
                ViewGroup$LayoutParams v2 = v0_1.getLayoutParams();
                if(((b)v2).b == 0 && (this.a(v0_1)) && this.b(((b)v2).a) == arg7) {
                    arg6.add(v0_1);
                }

                ++v1;
            }
        }
    }

    private boolean a(View arg2) {
        boolean v2 = arg2 == null || arg2.getParent() != this || arg2.getVisibility() == 8 ? false : true;
        return v2;
    }

    public b a(AttributeSet arg3) {
        return new b(this.getContext(), arg3);
    }

    public void a(int arg2, int arg3) {
        this.s();
        this.u.a(arg2, arg3);
    }

    public void a(Context arg2, int arg3) {
        this.n = arg3;
        if(this.f != null) {
            this.f.setTextAppearance(arg2, arg3);
        }
    }

    public void a(h arg4, android.support.v7.widget.c arg5) {
        if(arg4 == null && this.a == null) {
            return;
        }

        this.o();
        h v0 = this.a.d();
        if(v0 == arg4) {
            return;
        }

        if(v0 != null) {
            v0.b(this.J);
            v0.b(this.K);
        }

        if(this.K == null) {
            this.K = new a(this);
        }

        arg5.d(true);
        if(arg4 != null) {
            arg4.a(((o)arg5), this.l);
            arg4.a(this.K, this.l);
        }
        else {
            arg5.a(this.l, null);
            this.K.a(this.l, null);
            arg5.b(true);
            this.K.b(true);
        }

        this.a.setPopupTheme(this.m);
        this.a.setPresenter(arg5);
        this.J = arg5;
    }

    public void a(android.support.v7.view.menu.o$a arg2, android.support.v7.view.menu.h$a arg3) {
        this.L = arg2;
        this.M = arg3;
        if(this.a != null) {
            this.a.a(arg2, arg3);
        }
    }

    public boolean a() {
        boolean v0 = this.getVisibility() != 0 || this.a == null || !this.a.a() ? false : true;
        return v0;
    }

    private int b(int arg5) {
        int v0 = t.f(((View)this));
        arg5 = d.a(arg5, v0) & 7;
        if(arg5 != 1) {
            int v2 = 3;
            if(arg5 != v2 && arg5 != 5) {
                if(v0 == 1) {
                    v2 = 5;
                }

                return v2;
            }
        }

        return arg5;
    }

    private int b(View arg2) {
        ViewGroup$LayoutParams v2 = arg2.getLayoutParams();
        return g.a(((ViewGroup$MarginLayoutParams)v2)) + g.b(((ViewGroup$MarginLayoutParams)v2));
    }

    private int b(View arg6, int arg7, int[] arg8, int arg9) {
        ViewGroup$LayoutParams v0 = arg6.getLayoutParams();
        int v1 = ((b)v0).rightMargin - arg8[1];
        arg7 -= Math.max(0, v1);
        arg8[1] = Math.max(0, -v1);
        int v8 = this.a(arg6, arg9);
        arg9 = arg6.getMeasuredWidth();
        arg6.layout(arg7 - arg9, v8, arg7, arg6.getMeasuredHeight() + v8);
        return arg7 - (arg9 + ((b)v0).leftMargin);
    }

    public void b(Context arg2, int arg3) {
        this.o = arg3;
        if(this.g != null) {
            this.g.setTextAppearance(arg2, arg3);
        }
    }

    public boolean b() {
        boolean v0 = this.a == null || !this.a.g() ? false : true;
        return v0;
    }

    private int c(View arg2) {
        ViewGroup$LayoutParams v2 = arg2.getLayoutParams();
        return ((ViewGroup$MarginLayoutParams)v2).topMargin + ((ViewGroup$MarginLayoutParams)v2).bottomMargin;
    }

    public boolean c() {
        boolean v0 = this.a == null || !this.a.h() ? false : true;
        return v0;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg2) {
        boolean v2 = !super.checkLayoutParams(arg2) || !(arg2 instanceof b) ? false : true;
        return v2;
    }

    private boolean d(View arg2) {
        boolean v2 = arg2.getParent() == this || (this.F.contains(arg2)) ? true : false;
        return v2;
    }

    public boolean d() {
        boolean v0 = this.a == null || !this.a.e() ? false : true;
        return v0;
    }

    public boolean e() {
        boolean v0 = this.a == null || !this.a.f() ? false : true;
        return v0;
    }

    public void f() {
        if(this.a != null) {
            this.a.i();
        }
    }

    public boolean g() {
        boolean v0 = this.K == null || this.K.b == null ? false : true;
        return v0;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.j();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.a(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.a(arg1);
    }

    public int getContentInsetEnd() {
        int v0 = this.u != null ? this.u.d() : 0;
        return v0;
    }

    public int getContentInsetEndWithActions() {
        int v0 = this.w != -2147483648 ? this.w : this.getContentInsetEnd();
        return v0;
    }

    public int getContentInsetLeft() {
        int v0 = this.u != null ? this.u.a() : 0;
        return v0;
    }

    public int getContentInsetRight() {
        int v0 = this.u != null ? this.u.b() : 0;
        return v0;
    }

    public int getContentInsetStart() {
        int v0 = this.u != null ? this.u.c() : 0;
        return v0;
    }

    public int getContentInsetStartWithNavigation() {
        int v0 = this.v != -2147483648 ? this.v : this.getContentInsetStart();
        return v0;
    }

    public int getCurrentContentInsetEnd() {
        int v0_1;
        if(this.a != null) {
            h v0 = this.a.d();
            if(v0 == null) {
                goto label_10;
            }
            else if(v0.hasVisibleItems()) {
                v0_1 = 1;
            }
            else {
                goto label_10;
            }
        }
        else {
        label_10:
            v0_1 = 0;
        }

        return v0_1 != 0 ? Math.max(this.getContentInsetEnd(), Math.max(this.w, 0)) : this.getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        int v0 = t.f(((View)this)) == 1 ? this.getCurrentContentInsetEnd() : this.getCurrentContentInsetStart();
        return v0;
    }

    public int getCurrentContentInsetRight() {
        int v0 = t.f(((View)this)) == 1 ? this.getCurrentContentInsetStart() : this.getCurrentContentInsetEnd();
        return v0;
    }

    public int getCurrentContentInsetStart() {
        int v0 = this.getNavigationIcon() != null ? Math.max(this.getContentInsetStart(), Math.max(this.v, 0)) : this.getContentInsetStart();
        return v0;
    }

    public Drawable getLogo() {
        Drawable v0 = this.i != null ? this.i.getDrawable() : null;
        return v0;
    }

    public CharSequence getLogoDescription() {
        CharSequence v0 = this.i != null ? this.i.getContentDescription() : null;
        return v0;
    }

    public Menu getMenu() {
        this.n();
        return this.a.getMenu();
    }

    private MenuInflater getMenuInflater() {
        return new android.support.v7.view.g(this.getContext());
    }

    public CharSequence getNavigationContentDescription() {
        CharSequence v0 = this.h != null ? this.h.getContentDescription() : null;
        return v0;
    }

    public Drawable getNavigationIcon() {
        Drawable v0 = this.h != null ? this.h.getDrawable() : null;
        return v0;
    }

    android.support.v7.widget.c getOuterActionMenuPresenter() {
        return this.J;
    }

    public Drawable getOverflowIcon() {
        this.n();
        return this.a.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.l;
    }

    public int getPopupTheme() {
        return this.m;
    }

    public CharSequence getSubtitle() {
        return this.z;
    }

    public CharSequence getTitle() {
        return this.y;
    }

    public int getTitleMarginBottom() {
        return this.t;
    }

    public int getTitleMarginEnd() {
        return this.r;
    }

    public int getTitleMarginStart() {
        return this.q;
    }

    public int getTitleMarginTop() {
        return this.s;
    }

    public ag getWrapper() {
        if(this.I == null) {
            this.I = new bl(this, true);
        }

        return this.I;
    }

    public void h() {
        j v0 = this.K == null ? null : this.K.b;
        if(v0 != null) {
            v0.collapseActionView();
        }
    }

    void i() {
        if(this.b == null) {
            this.b = new AppCompatImageButton(this.getContext(), null, android.support.v7.a.a$a.toolbarNavigationButtonStyle);
            this.b.setImageDrawable(this.j);
            this.b.setContentDescription(this.k);
            b v0 = this.j();
            v0.a = 8388611 | this.d & 112;
            v0.b = 2;
            this.b.setLayoutParams(((ViewGroup$LayoutParams)v0));
            this.b.setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg1) {
                    this.a.h();
                }
            });
        }
    }

    protected b j() {
        return new b(-2, -2);
    }

    void k() {
        int v0;
        for(v0 = this.getChildCount() - 1; v0 >= 0; --v0) {
            View v1 = this.getChildAt(v0);
            if(v1.getLayoutParams().b != 2 && v1 != this.a) {
                this.removeViewAt(v0);
                this.F.add(v1);
            }
        }
    }

    void l() {
        int v0;
        for(v0 = this.F.size() - 1; v0 >= 0; --v0) {
            this.addView(this.F.get(v0));
        }

        this.F.clear();
    }

    private void m() {
        if(this.i == null) {
            this.i = new AppCompatImageView(this.getContext());
        }
    }

    private void n() {
        this.o();
        if(this.a.d() == null) {
            Menu v0 = this.a.getMenu();
            if(this.K == null) {
                this.K = new a(this);
            }

            this.a.setExpandedActionViewsExclusive(true);
            ((h)v0).a(this.K, this.l);
        }
    }

    private void o() {
        if(this.a == null) {
            this.a = new ActionMenuView(this.getContext());
            this.a.setPopupTheme(this.m);
            this.a.setOnMenuItemClickListener(this.H);
            this.a.a(this.L, this.M);
            b v0 = this.j();
            v0.a = 8388613 | this.d & 112;
            this.a.setLayoutParams(((ViewGroup$LayoutParams)v0));
            this.a(this.a, false);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.O);
    }

    public boolean onHoverEvent(MotionEvent arg6) {
        int v0 = arg6.getActionMasked();
        int v2 = 9;
        if(v0 == v2) {
            this.D = false;
        }

        if(!this.D) {
            boolean v6 = super.onHoverEvent(arg6);
            if(v0 == v2 && !v6) {
                this.D = true;
            }
        }

        if(v0 == 10 || v0 == 3) {
            this.D = false;
        }

        return 1;
    }

    protected void onLayout(boolean arg25, int arg26, int arg27, int arg28, int arg29) {
        int v22;
        int v21;
        int v18;
        ViewGroup$LayoutParams v1_1;
        int v23;
        int v20;
        ViewGroup$LayoutParams v7_1;
        int v3;
        int v19;
        int v14;
        int v13;
        Toolbar v0 = this;
        int v1 = t.f(((View)this)) == 1 ? 1 : 0;
        int v4 = this.getWidth();
        int v5 = this.getHeight();
        int v6 = this.getPaddingLeft();
        int v7 = this.getPaddingRight();
        int v8 = this.getPaddingTop();
        int v9 = this.getPaddingBottom();
        int v10 = v4 - v7;
        int[] v11 = v0.G;
        v11[1] = 0;
        v11[0] = 0;
        int v12 = t.l(((View)this));
        v12 = v12 >= 0 ? Math.min(v12, arg29 - arg27) : 0;
        if(!v0.a(v0.h)) {
            v14 = v6;
        label_37:
            v13 = v10;
        }
        else if(v1 != 0) {
            v13 = v0.b(v0.h, v10, v11, v12);
            v14 = v6;
        }
        else {
            v14 = v0.a(v0.h, v6, v11, v12);
            goto label_37;
        }

        if(v0.a(v0.b)) {
            if(v1 != 0) {
                v13 = v0.b(v0.b, v13, v11, v12);
            }
            else {
                v14 = v0.a(v0.b, v14, v11, v12);
            }
        }

        if(v0.a(v0.a)) {
            if(v1 != 0) {
                v14 = v0.a(v0.a, v14, v11, v12);
            }
            else {
                v13 = v0.b(v0.a, v13, v11, v12);
            }
        }

        int v15 = this.getCurrentContentInsetLeft();
        int v16 = this.getCurrentContentInsetRight();
        v11[0] = Math.max(0, v15 - v14);
        v11[1] = Math.max(0, v16 - (v10 - v13));
        int v2 = Math.max(v14, v15);
        v10 = Math.min(v13, v10 - v16);
        if(v0.a(v0.c)) {
            if(v1 != 0) {
                v10 = v0.b(v0.c, v10, v11, v12);
            }
            else {
                v2 = v0.a(v0.c, v2, v11, v12);
            }
        }

        if(v0.a(v0.i)) {
            if(v1 != 0) {
                v10 = v0.b(v0.i, v10, v11, v12);
            }
            else {
                v2 = v0.a(v0.i, v2, v11, v12);
            }
        }

        boolean v13_1 = v0.a(v0.f);
        boolean v14_1 = v0.a(v0.g);
        if(v13_1) {
            ViewGroup$LayoutParams v15_1 = v0.f.getLayoutParams();
            v19 = v7;
            v3 = ((b)v15_1).topMargin + v0.f.getMeasuredHeight() + ((b)v15_1).bottomMargin;
        }
        else {
            v19 = v7;
            v3 = 0;
        }

        if(v14_1) {
            v7_1 = v0.g.getLayoutParams();
            v20 = v4;
            v3 += ((b)v7_1).topMargin + v0.g.getMeasuredHeight() + ((b)v7_1).bottomMargin;
        }
        else {
            v20 = v4;
        }

        if((v13_1) || (v14_1)) {
            TextView v4_1 = v13_1 ? v0.f : v0.g;
            TextView v7_2 = v14_1 ? v0.g : v0.f;
            ViewGroup$LayoutParams v4_2 = ((View)v4_1).getLayoutParams();
            v7_1 = ((View)v7_2).getLayoutParams();
            if(!v13_1 || v0.f.getMeasuredWidth() <= 0) {
                if((v14_1) && v0.g.getMeasuredWidth() > 0) {
                label_144:
                    v21 = v6;
                    v15 = 1;
                    goto label_149;
                }

                v21 = v6;
                v15 = 0;
            }
            else {
                goto label_144;
            }

        label_149:
            v6 = v0.x & 112;
            v22 = v12;
            if(v6 == 48) {
                v23 = v2;
                v8 = this.getPaddingTop() + ((b)v4_2).topMargin + v0.s;
            }
            else if(v6 != 80) {
                v6 = (v5 - v8 - v9 - v3) / 2;
                v23 = v2;
                if(v6 < ((b)v4_2).topMargin + v0.s) {
                    v6 = ((b)v4_2).topMargin + v0.s;
                }
                else {
                    v5 = v5 - v9 - v3 - v6 - v8;
                    if(v5 < ((b)v4_2).bottomMargin + v0.t) {
                        v6 = Math.max(0, v6 - (((b)v7_1).bottomMargin + v0.t - v5));
                    }
                }

                v8 += v6;
            }
            else {
                v23 = v2;
                v8 = v5 - v9 - ((b)v7_1).bottomMargin - v0.t - v3;
            }

            if(v1 != 0) {
                if(v15 != 0) {
                    v3 = v0.q;
                    v1 = 1;
                }
                else {
                    v1 = 1;
                    v3 = 0;
                }

                v3 -= v11[v1];
                v10 -= Math.max(0, v3);
                v11[v1] = Math.max(0, -v3);
                if(v13_1) {
                    v1_1 = v0.f.getLayoutParams();
                    v2 = v10 - v0.f.getMeasuredWidth();
                    v3 = v0.f.getMeasuredHeight() + v8;
                    v0.f.layout(v2, v8, v10, v3);
                    v2 -= v0.r;
                    v8 = v3 + ((b)v1_1).bottomMargin;
                }
                else {
                    v2 = v10;
                }

                if(v14_1) {
                    v8 += v0.g.getLayoutParams().topMargin;
                    v0.g.layout(v10 - v0.g.getMeasuredWidth(), v8, v10, v0.g.getMeasuredHeight() + v8);
                    v3 = v10 - v0.r;
                }
                else {
                    v3 = v10;
                }

                if(v15 != 0) {
                    v10 = Math.min(v2, v3);
                }

                v2 = v23;
            label_124:
                v7 = 0;
                goto label_306;
            }

            if(v15 != 0) {
                v18 = v0.q;
                v7 = 0;
            }
            else {
                v7 = 0;
                v18 = 0;
            }

            v1 = v18 - v11[v7];
            v2 = v23 + Math.max(v7, v1);
            v11[v7] = Math.max(v7, -v1);
            if(v13_1) {
                v1_1 = v0.f.getLayoutParams();
                v3 = v0.f.getMeasuredWidth() + v2;
                v4 = v0.f.getMeasuredHeight() + v8;
                v0.f.layout(v2, v8, v3, v4);
                v3 += v0.r;
                v8 = v4 + ((b)v1_1).bottomMargin;
            }
            else {
                v3 = v2;
            }

            if(v14_1) {
                v8 += v0.g.getLayoutParams().topMargin;
                v4 = v0.g.getMeasuredWidth() + v2;
                v0.g.layout(v2, v8, v4, v0.g.getMeasuredHeight() + v8);
                v4 += v0.r;
            }
            else {
                v4 = v2;
            }

            if(v15 == 0) {
                goto label_306;
            }

            v2 = Math.max(v3, v4);
        }
        else {
            v21 = v6;
            v22 = v12;
            goto label_124;
        }

    label_306:
        v0.a(v0.E, 3);
        v1 = v0.E.size();
        v3 = v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v3 = v0.a(v0.E.get(v2), v3, v11, v22);
        }

        v12 = v22;
        v0.a(v0.E, 5);
        v1 = v0.E.size();
        for(v2 = 0; v2 < v1; ++v2) {
            v10 = v0.b(v0.E.get(v2), v10, v11, v12);
        }

        v0.a(v0.E, 1);
        v1 = v0.a(v0.E, v11);
        v2 = v21 + (v20 - v21 - v19) / 2 - v1 / 2;
        v1 += v2;
        if(v2 < v3) {
        }
        else if(v1 > v10) {
            v3 = v2 - (v1 - v10);
        }
        else {
            v3 = v2;
        }

        v1 = v0.E.size();
        while(v7 < v1) {
            v3 = v0.a(v0.E.get(v7), v3, v11, v12);
            ++v7;
        }

        v0.E.clear();
    }

    protected void onMeasure(int arg17, int arg18) {
        int v6;
        int v15_1;
        int v13;
        int v12;
        int v0;
        int v11;
        int v10;
        Toolbar v7 = this;
        int[] v8 = v7.G;
        if(bs.a(((View)this))) {
            v10 = 1;
            v11 = 0;
        }
        else {
            v10 = 0;
            v11 = 1;
        }

        if(v7.a(v7.h)) {
            this.a(v7.h, arg17, 0, arg18, 0, v7.p);
            v0 = v7.h.getMeasuredWidth() + v7.b(v7.h);
            v12 = Math.max(0, v7.h.getMeasuredHeight() + v7.c(v7.h));
            v13 = View.combineMeasuredStates(0, v7.h.getMeasuredState());
        }
        else {
            v0 = 0;
            v12 = 0;
            v13 = 0;
        }

        if(v7.a(v7.b)) {
            this.a(v7.b, arg17, 0, arg18, 0, v7.p);
            v0 = v7.b.getMeasuredWidth() + v7.b(v7.b);
            v12 = Math.max(v12, v7.b.getMeasuredHeight() + v7.c(v7.b));
            v13 = View.combineMeasuredStates(v13, v7.b.getMeasuredState());
        }

        int v1 = this.getCurrentContentInsetStart();
        int v14 = Math.max(v1, v0);
        v8[v10] = Math.max(0, v1 - v0);
        if(v7.a(v7.a)) {
            this.a(v7.a, arg17, v14, arg18, 0, v7.p);
            v0 = v7.a.getMeasuredWidth() + v7.b(v7.a);
            v12 = Math.max(v12, v7.a.getMeasuredHeight() + v7.c(v7.a));
            v13 = View.combineMeasuredStates(v13, v7.a.getMeasuredState());
        }
        else {
            v0 = 0;
        }

        v1 = this.getCurrentContentInsetEnd();
        v10 = v14 + Math.max(v1, v0);
        v8[v11] = Math.max(0, v1 - v0);
        if(v7.a(v7.c)) {
            v10 += this.a(v7.c, arg17, v10, arg18, 0, v8);
            v12 = Math.max(v12, v7.c.getMeasuredHeight() + v7.c(v7.c));
            v13 = View.combineMeasuredStates(v13, v7.c.getMeasuredState());
        }

        if(v7.a(v7.i)) {
            v10 += this.a(v7.i, arg17, v10, arg18, 0, v8);
            v12 = Math.max(v12, v7.i.getMeasuredHeight() + v7.c(v7.i));
            v13 = View.combineMeasuredStates(v13, v7.i.getMeasuredState());
        }

        v11 = this.getChildCount();
        v14 = v12;
        v12 = v10;
        for(v10 = 0; v10 < v11; ++v10) {
            View v15 = v7.getChildAt(v10);
            if(v15.getLayoutParams().b == 0) {
                if(!v7.a(v15)) {
                }
                else {
                    v12 += this.a(v15, arg17, v12, arg18, 0, v8);
                    v14 = Math.max(v14, v15.getMeasuredHeight() + v7.c(v15));
                    v13 = View.combineMeasuredStates(v13, v15.getMeasuredState());
                }
            }
        }

        v10 = v7.s + v7.t;
        v11 = v7.q + v7.r;
        if(v7.a(v7.f)) {
            this.a(v7.f, arg17, v12 + v11, arg18, v10, v8);
            v0 = v7.f.getMeasuredWidth() + v7.b(v7.f);
            v15_1 = v7.f.getMeasuredHeight() + v7.c(v7.f);
            v6 = View.combineMeasuredStates(v13, v7.f.getMeasuredState());
            v13 = v0;
        }
        else {
            v6 = v13;
            v13 = 0;
            v15_1 = 0;
        }

        if(v7.a(v7.g)) {
            v13 = Math.max(v13, this.a(v7.g, arg17, v12 + v11, arg18, v15_1 + v10, v8));
            v15_1 += v7.g.getMeasuredHeight() + v7.c(v7.g);
            v6 = View.combineMeasuredStates(v6, v7.g.getMeasuredState());
        }

        v0 = Math.max(v14, v15_1);
        v12 = v12 + v13 + (this.getPaddingLeft() + this.getPaddingRight());
        v0 += this.getPaddingTop() + this.getPaddingBottom();
        v1 = View.resolveSizeAndState(Math.max(v12, this.getSuggestedMinimumWidth()), arg17, -16777216 & v6);
        v0 = View.resolveSizeAndState(Math.max(v0, this.getSuggestedMinimumHeight()), arg18, v6 << 16);
        if(this.r()) {
            v0 = 0;
        }

        v7.setMeasuredDimension(v1, v0);
    }

    protected void onRestoreInstanceState(Parcelable arg3) {
        if(!(arg3 instanceof SavedState)) {
            super.onRestoreInstanceState(arg3);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg3).getSuperState());
        h v0 = this.a != null ? this.a.d() : null;
        if(((SavedState)arg3).a != 0 && this.K != null && v0 != null) {
            MenuItem v0_1 = ((Menu)v0).findItem(((SavedState)arg3).a);
            if(v0_1 != null) {
                v0_1.expandActionView();
            }
        }

        if(((SavedState)arg3).b) {
            this.q();
        }
    }

    public void onRtlPropertiesChanged(int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(arg3);
        }

        this.s();
        ba v0 = this.u;
        boolean v1 = true;
        if(arg3 == 1) {
        }
        else {
            v1 = false;
        }

        v0.a(v1);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v0 = new SavedState(super.onSaveInstanceState());
        if(this.K != null && this.K.b != null) {
            v0.a = this.K.b.getItemId();
        }

        v0.b = this.b();
        return ((Parcelable)v0);
    }

    public boolean onTouchEvent(MotionEvent arg5) {
        int v0 = arg5.getActionMasked();
        if(v0 == 0) {
            this.C = false;
        }

        if(!this.C) {
            boolean v5 = super.onTouchEvent(arg5);
            if(v0 == 0 && !v5) {
                this.C = true;
            }
        }

        if(v0 == 1 || v0 == 3) {
            this.C = false;
        }

        return 1;
    }

    private void p() {
        if(this.h == null) {
            this.h = new AppCompatImageButton(this.getContext(), null, android.support.v7.a.a$a.toolbarNavigationButtonStyle);
            b v0 = this.j();
            v0.a = 8388611 | this.d & 112;
            this.h.setLayoutParams(((ViewGroup$LayoutParams)v0));
        }
    }

    private void q() {
        this.removeCallbacks(this.O);
        this.post(this.O);
    }

    private boolean r() {
        if(!this.N) {
            return 0;
        }

        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getChildAt(v2);
            if((this.a(v3)) && v3.getMeasuredWidth() > 0 && v3.getMeasuredHeight() > 0) {
                return 0;
            }
        }

        return 1;
    }

    private void s() {
        if(this.u == null) {
            this.u = new ba();
        }
    }

    public void setCollapsible(boolean arg1) {
        this.N = arg1;
        this.requestLayout();
    }

    public void setContentInsetEndWithActions(int arg2) {
        if(arg2 < 0) {
            arg2 = -2147483648;
        }

        if(arg2 != this.w) {
            this.w = arg2;
            if(this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int arg2) {
        if(arg2 < 0) {
            arg2 = -2147483648;
        }

        if(arg2 != this.v) {
            this.v = arg2;
            if(this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }

    public void setLogo(Drawable arg3) {
        if(arg3 != null) {
            this.m();
            if(!this.d(this.i)) {
                this.a(this.i, true);
            }
        }
        else if(this.i != null && (this.d(this.i))) {
            this.removeView(this.i);
            this.F.remove(this.i);
        }

        if(this.i != null) {
            this.i.setImageDrawable(arg3);
        }
    }

    public void setLogo(int arg2) {
        this.setLogo(android.support.v7.c.a.a.b(this.getContext(), arg2));
    }

    public void setLogoDescription(CharSequence arg2) {
        if(!TextUtils.isEmpty(arg2)) {
            this.m();
        }

        if(this.i != null) {
            this.i.setContentDescription(arg2);
        }
    }

    public void setLogoDescription(int arg2) {
        this.setLogoDescription(this.getContext().getText(arg2));
    }

    public void setNavigationContentDescription(CharSequence arg2) {
        if(!TextUtils.isEmpty(arg2)) {
            this.p();
        }

        if(this.h != null) {
            this.h.setContentDescription(arg2);
        }
    }

    public void setNavigationContentDescription(int arg2) {
        CharSequence v2 = arg2 != 0 ? this.getContext().getText(arg2) : null;
        this.setNavigationContentDescription(v2);
    }

    public void setNavigationIcon(Drawable arg3) {
        if(arg3 != null) {
            this.p();
            if(!this.d(this.h)) {
                this.a(this.h, true);
            }
        }
        else if(this.h != null && (this.d(this.h))) {
            this.removeView(this.h);
            this.F.remove(this.h);
        }

        if(this.h != null) {
            this.h.setImageDrawable(arg3);
        }
    }

    public void setNavigationIcon(int arg2) {
        this.setNavigationIcon(android.support.v7.c.a.a.b(this.getContext(), arg2));
    }

    public void setNavigationOnClickListener(View$OnClickListener arg2) {
        this.p();
        this.h.setOnClickListener(arg2);
    }

    public void setOnMenuItemClickListener(android.support.v7.widget.Toolbar$c arg1) {
        this.e = arg1;
    }

    public void setOverflowIcon(Drawable arg2) {
        this.n();
        this.a.setOverflowIcon(arg2);
    }

    public void setPopupTheme(int arg3) {
        if(this.m != arg3) {
            this.m = arg3;
            this.l = arg3 == 0 ? this.getContext() : new ContextThemeWrapper(this.getContext(), arg3);
        }
    }

    public void setSubtitle(CharSequence arg4) {
        if(!TextUtils.isEmpty(arg4)) {
            if(this.g == null) {
                Context v0 = this.getContext();
                this.g = new x(v0);
                this.g.setSingleLine();
                this.g.setEllipsize(TextUtils$TruncateAt.END);
                if(this.o != 0) {
                    this.g.setTextAppearance(v0, this.o);
                }

                if(this.B == 0) {
                    goto label_23;
                }

                this.g.setTextColor(this.B);
            }

        label_23:
            if(this.d(this.g)) {
                goto label_40;
            }

            this.a(this.g, true);
        }
        else {
            if(this.g == null) {
                goto label_40;
            }

            if(!this.d(this.g)) {
                goto label_40;
            }

            this.removeView(this.g);
            this.F.remove(this.g);
        }

    label_40:
        if(this.g != null) {
            this.g.setText(arg4);
        }

        this.z = arg4;
    }

    public void setSubtitle(int arg2) {
        this.setSubtitle(this.getContext().getText(arg2));
    }

    public void setSubtitleTextColor(int arg2) {
        this.B = arg2;
        if(this.g != null) {
            this.g.setTextColor(arg2);
        }
    }

    public void setTitle(CharSequence arg4) {
        if(!TextUtils.isEmpty(arg4)) {
            if(this.f == null) {
                Context v0 = this.getContext();
                this.f = new x(v0);
                this.f.setSingleLine();
                this.f.setEllipsize(TextUtils$TruncateAt.END);
                if(this.n != 0) {
                    this.f.setTextAppearance(v0, this.n);
                }

                if(this.A == 0) {
                    goto label_23;
                }

                this.f.setTextColor(this.A);
            }

        label_23:
            if(this.d(this.f)) {
                goto label_40;
            }

            this.a(this.f, true);
        }
        else {
            if(this.f == null) {
                goto label_40;
            }

            if(!this.d(this.f)) {
                goto label_40;
            }

            this.removeView(this.f);
            this.F.remove(this.f);
        }

    label_40:
        if(this.f != null) {
            this.f.setText(arg4);
        }

        this.y = arg4;
    }

    public void setTitle(int arg2) {
        this.setTitle(this.getContext().getText(arg2));
    }

    public void setTitleMarginBottom(int arg1) {
        this.t = arg1;
        this.requestLayout();
    }

    public void setTitleMarginEnd(int arg1) {
        this.r = arg1;
        this.requestLayout();
    }

    public void setTitleMarginStart(int arg1) {
        this.q = arg1;
        this.requestLayout();
    }

    public void setTitleMarginTop(int arg1) {
        this.s = arg1;
        this.requestLayout();
    }

    public void setTitleTextColor(int arg2) {
        this.A = arg2;
        if(this.f != null) {
            this.f.setTextColor(arg2);
        }
    }
}

