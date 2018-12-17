package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v7.widget.a.a$d;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class LinearLayoutManager extends i implements b, d {
    public class SavedState implements Parcelable {
        final class android.support.v7.widget.LinearLayoutManager$SavedState$1 implements Parcelable$Creator {
            android.support.v7.widget.LinearLayoutManager$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg2) {
                return new SavedState(arg2);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        int a;
        int b;
        boolean c;

        static {
            SavedState.CREATOR = new android.support.v7.widget.LinearLayoutManager$SavedState$1();
        }

        public SavedState(SavedState arg2) {
            super();
            this.a = arg2.a;
            this.b = arg2.b;
            this.c = arg2.c;
        }

        public SavedState() {
            super();
        }

        SavedState(Parcel arg2) {
            super();
            this.a = arg2.readInt();
            this.b = arg2.readInt();
            boolean v0 = true;
            if(arg2.readInt() == 1) {
            }
            else {
                v0 = false;
            }

            this.c = v0;
        }

        boolean a() {
            boolean v0 = this.a >= 0 ? true : false;
            return v0;
        }

        void b() {
            this.a = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            arg1.writeInt(this.a);
            arg1.writeInt(this.b);
            arg1.writeInt(this.c);
        }
    }

    class a {
        au a;
        int b;
        int c;
        boolean d;
        boolean e;

        a() {
            super();
            this.a();
        }

        boolean a(View arg2, t arg3) {
            ViewGroup$LayoutParams v2 = arg2.getLayoutParams();
            boolean v2_1 = (((j)v2).d()) || ((j)v2).f() < 0 || ((j)v2).f() >= arg3.e() ? false : true;
            return v2_1;
        }

        public void a(View arg5, int arg6) {
            int v2;
            int v0 = this.a.b();
            if(v0 >= 0) {
                this.b(arg5, arg6);
                return;
            }

            this.b = arg6;
            if(this.d) {
                arg6 = this.a.d() - v0 - this.a.b(arg5);
                this.c = this.a.d() - arg6;
                if(arg6 > 0) {
                    v2 = this.c - this.a.e(arg5);
                    v0 = this.a.c();
                    v2 -= v0 + Math.min(this.a.a(arg5) - v0, 0);
                    if(v2 < 0) {
                        this.c += Math.min(arg6, -v2);
                    }
                }
            }
            else {
                arg6 = this.a.a(arg5);
                v2 = arg6 - this.a.c();
                this.c = arg6;
                if(v2 > 0) {
                    int v5 = this.a.d() - Math.min(0, this.a.d() - v0 - this.a.b(arg5)) - (arg6 + this.a.e(arg5));
                    if(v5 < 0) {
                        this.c -= Math.min(v2, -v5);
                    }
                }
            }
        }

        void a() {
            this.b = -1;
            this.c = -2147483648;
            this.d = false;
            this.e = false;
        }

        void b() {
            int v0 = this.d ? this.a.d() : this.a.c();
            this.c = v0;
        }

        public void b(View arg2, int arg3) {
            int v2 = this.d ? this.a.b(arg2) + this.a.b() : this.a.a(arg2);
            this.c = v2;
            this.b = arg3;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
        }
    }

    public class android.support.v7.widget.LinearLayoutManager$b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;

        protected android.support.v7.widget.LinearLayoutManager$b() {
            super();
        }

        void a() {
            this.a = 0;
            this.b = false;
            this.c = false;
            this.d = false;
        }
    }

    class c {
        boolean a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        boolean i;
        int j;
        List k;
        boolean l;

        c() {
            super();
            this.a = true;
            this.h = 0;
            this.i = false;
            this.k = null;
        }

        public void a() {
            this.a(null);
        }

        boolean a(t arg2) {
            boolean v2 = this.d < 0 || this.d >= arg2.e() ? false : true;
            return v2;
        }

        View a(p arg3) {
            if(this.k != null) {
                return this.b();
            }

            View v3 = arg3.c(this.d);
            this.d += this.e;
            return v3;
        }

        public void a(View arg1) {
            arg1 = this.b(arg1);
            int v1 = arg1 == null ? -1 : arg1.getLayoutParams().f();
            this.d = v1;
        }

        private View b() {
            int v0 = this.k.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                View v2 = this.k.get(v1).itemView;
                ViewGroup$LayoutParams v3 = v2.getLayoutParams();
                if(((j)v3).d()) {
                }
                else if(this.d == ((j)v3).f()) {
                    this.a(v2);
                    return v2;
                }
            }

            return null;
        }

        public View b(View arg8) {
            int v0 = this.k.size();
            View v1 = null;
            int v2 = 2147483647;
            int v3;
            for(v3 = 0; v3 < v0; ++v3) {
                View v4 = this.k.get(v3).itemView;
                ViewGroup$LayoutParams v5 = v4.getLayoutParams();
                if(v4 != arg8) {
                    if(((j)v5).d()) {
                    }
                    else {
                        int v5_1 = (((j)v5).f() - this.d) * this.e;
                        if(v5_1 < 0) {
                        }
                        else if(v5_1 < v2) {
                            if(v5_1 == 0) {
                                return v4;
                            }
                            else {
                                v1 = v4;
                                v2 = v5_1;
                            }
                        }
                    }
                }
            }

            return v1;
        }
    }

    private c a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private final android.support.v7.widget.LinearLayoutManager$b g;
    private int h;
    int i;
    au j;
    boolean k;
    int l;
    int m;
    SavedState n;
    final a o;

    public LinearLayoutManager(Context arg3) {
        this(arg3, 1, false);
    }

    public LinearLayoutManager(Context arg2, int arg3, boolean arg4) {
        super();
        this.i = 1;
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = -2147483648;
        this.n = null;
        this.o = new a();
        this.g = new android.support.v7.widget.LinearLayoutManager$b();
        this.h = 2;
        this.b(arg3);
        this.b(arg4);
    }

    public LinearLayoutManager(Context arg3, AttributeSet arg4, int arg5, int arg6) {
        super();
        this.i = 1;
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = -2147483648;
        this.n = null;
        this.o = new a();
        this.g = new android.support.v7.widget.LinearLayoutManager$b();
        this.h = 2;
        android.support.v7.widget.RecyclerView$i$b v3 = LinearLayoutManager.a(arg3, arg4, arg5, arg6);
        this.b(v3.a);
        this.b(v3.c);
        this.a(v3.d);
    }

    private void N() {
        int v0;
        if(this.i == 1 || !this.h()) {
            boolean v0_1 = this.c;
        }
        else {
            v0 = this.c ^ 1;
        }

        this.k = ((boolean)v0);
    }

    private View O() {
        int v0 = this.k ? this.x() - 1 : 0;
        return this.i(v0);
    }

    private View P() {
        int v0 = this.k ? 0 : this.x() - 1;
        return this.i(v0);
    }

    public void a(boolean arg2) {
        this.a(null);
        if(this.d == arg2) {
            return;
        }

        this.d = arg2;
        this.q();
    }

    private int a(int arg2, p arg3, t arg4, boolean arg5) {
        int v0 = this.j.d() - arg2;
        if(v0 > 0) {
            int v3 = -this.c(-v0, arg3, arg4);
            arg2 += v3;
            if(arg5) {
                int v4 = this.j.d() - arg2;
                if(v4 > 0) {
                    this.j.a(v4);
                    return v4 + v3;
                }
            }

            return v3;
        }

        return 0;
    }

    private View a(boolean arg3, boolean arg4) {
        int v1;
        int v0;
        if(this.k) {
            v0 = this.x() - 1;
            v1 = -1;
        }
        else {
            v0 = 0;
            v1 = this.x();
        }

        return this.a(v0, v1, arg3, arg4);
    }

    View a(int arg2, int arg3, boolean arg4, boolean arg5) {
        this.i();
        int v0 = 320;
        int v4 = arg4 ? 24579 : 320;
        if(arg5) {
        }
        else {
            v0 = 0;
        }

        bq v5 = this.i == 0 ? this.r : this.s;
        View v2 = v5.a(arg2, arg3, v4, v0);
        return v2;
    }

    private void a(int arg4, int arg5) {
        this.a.c = this.j.d() - arg5;
        c v0 = this.a;
        int v1 = this.k ? -1 : 1;
        v0.e = v1;
        this.a.d = arg4;
        this.a.f = 1;
        this.a.b = arg5;
        this.a.g = -2147483648;
    }

    private void a(int arg5, int arg6, boolean arg7, t arg8) {
        c v1;
        View v5;
        this.a.l = this.k();
        this.a.h = this.b(arg8);
        this.a.f = arg5;
        int v8 = -1;
        if(arg5 == 1) {
            this.a.h += this.j.g();
            v5 = this.P();
            v1 = this.a;
            if(this.k) {
            }
            else {
                v8 = 1;
            }

            v1.e = v8;
            this.a.d = this.d(v5) + this.a.e;
            this.a.b = this.j.b(v5);
            arg5 = this.j.b(v5) - this.j.d();
        }
        else {
            v5 = this.O();
            this.a.h += this.j.c();
            v1 = this.a;
            if(this.k) {
                v8 = 1;
            }

            v1.e = v8;
            this.a.d = this.d(v5) + this.a.e;
            this.a.b = this.j.a(v5);
            arg5 = -this.j.a(v5) + this.j.c();
        }

        this.a.c = arg6;
        if(arg7) {
            this.a.c -= arg5;
        }

        this.a.g = arg5;
    }

    private void a(a arg2) {
        this.a(arg2.b, arg2.c);
    }

    private void a(p arg6, int arg7) {
        if(arg7 < 0) {
            return;
        }

        int v0 = this.x();
        if(this.k) {
            --v0;
            int v1 = v0;
            while(true) {
                if(v1 >= 0) {
                    View v2 = this.i(v1);
                    if(this.j.b(v2) <= arg7) {
                        if(this.j.c(v2) > arg7) {
                        }
                        else {
                            --v1;
                            continue;
                        }
                    }

                    break;
                }

                return;
            }

            this.a(arg6, v0, v1);
            return;
        }
        else {
            int v2_1 = 0;
            while(true) {
                if(v2_1 >= v0) {
                    return;
                }

                View v3 = this.i(v2_1);
                if(this.j.b(v3) <= arg7) {
                    if(this.j.c(v3) > arg7) {
                    }
                    else {
                        ++v2_1;
                        continue;
                    }
                }

                break;
            }

            this.a(arg6, 0, v2_1);
        }
    }

    private void a(p arg1, int arg2, int arg3) {
        if(arg2 == arg3) {
            return;
        }

        if(arg3 > arg2) {
            --arg3;
            while(arg3 >= arg2) {
                this.a(arg3, arg1);
                --arg3;
            }
        }
        else {
            while(arg2 > arg3) {
                this.a(arg2, arg1);
                --arg2;
            }
        }
    }

    private void a(p arg3, c arg4) {
        if(arg4.a) {
            if(arg4.l) {
            }
            else if(arg4.f == -1) {
                this.b(arg3, arg4.g);
            }
            else {
                this.a(arg3, arg4.g);
            }
        }
    }

    private void a(p arg2, t arg3, a arg4) {
        if(this.a(arg3, arg4)) {
            return;
        }

        if(this.b(arg2, arg3, arg4)) {
            return;
        }

        arg4.b();
        int v2 = this.d ? arg3.e() - 1 : 0;
        arg4.b = v2;
    }

    private boolean a(t arg5, a arg6) {
        int v5;
        boolean v1 = false;
        if(!arg5.a()) {
            int v2 = -1;
            if(this.l == v2) {
            }
            else {
                int v3 = -2147483648;
                if(this.l >= 0) {
                    if(this.l >= arg5.e()) {
                    }
                    else {
                        arg6.b = this.l;
                        if(this.n != null && (this.n.a())) {
                            arg6.d = this.n.c;
                            v5 = arg6.d ? this.j.d() - this.n.b : this.j.c() + this.n.b;
                            arg6.c = v5;
                            return 1;
                        }

                        if(this.m == v3) {
                            View v5_1 = this.c(this.l);
                            if(v5_1 == null) {
                                if(this.x() > 0) {
                                    boolean v5_2 = this.l < this.d(this.i(0)) ? true : false;
                                    if(v5_2 == this.k) {
                                        v1 = true;
                                    }

                                    arg6.d = v1;
                                }

                                arg6.b();
                            }
                            else if(this.j.e(v5_1) > this.j.f()) {
                                arg6.b();
                                return 1;
                            }
                            else if(this.j.a(v5_1) - this.j.c() < 0) {
                                arg6.c = this.j.c();
                                arg6.d = false;
                                return 1;
                            }
                            else if(this.j.d() - this.j.b(v5_1) < 0) {
                                arg6.c = this.j.d();
                                arg6.d = true;
                                return 1;
                            }
                            else {
                                v5 = arg6.d ? this.j.b(v5_1) + this.j.b() : this.j.a(v5_1);
                                arg6.c = v5;
                            }

                            return 1;
                        }

                        arg6.d = this.k;
                        v5 = this.k ? this.j.d() - this.m : this.j.c() + this.m;
                        arg6.c = v5;
                        return 1;
                    }
                }

                this.l = v2;
                this.m = v3;
            }
        }

        return 0;
    }

    int a(p arg8, c arg9, t arg10, boolean arg11) {
        int v0 = arg9.c;
        int v2 = -2147483648;
        if(arg9.g != v2) {
            if(arg9.c < 0) {
                arg9.g += arg9.c;
            }

            this.a(arg8, arg9);
        }

        int v1 = arg9.c + arg9.h;
        android.support.v7.widget.LinearLayoutManager$b v3 = this.g;
        do {
            if(!arg9.l && v1 <= 0) {
                break;
            }

            if(!arg9.a(arg10)) {
                break;
            }

            v3.a();
            this.a(arg8, arg10, arg9, v3);
            if(v3.b) {
            }
            else {
                arg9.b += v3.a * arg9.f;
                if(!v3.c || this.a.k != null || !arg10.a()) {
                    arg9.c -= v3.a;
                    v1 -= v3.a;
                }

                if(arg9.g != v2) {
                    arg9.g += v3.a;
                    if(arg9.c < 0) {
                        arg9.g += arg9.c;
                    }

                    this.a(arg8, arg9);
                }

                if(!arg11) {
                    continue;
                }

                if(!v3.d) {
                    continue;
                }
            }

            break;
        }
        while(true);

        return v0 - arg9.c;
    }

    View a(p arg6, t arg7, int arg8, int arg9, int arg10) {
        this.i();
        int v6 = this.j.c();
        int v7 = this.j.d();
        int v0 = arg9 > arg8 ? 1 : -1;
        View v1 = null;
        View v2 = v1;
        while(arg8 != arg9) {
            View v3 = this.i(arg8);
            int v4 = this.d(v3);
            if(v4 >= 0 && v4 < arg10) {
                if(!v3.getLayoutParams().d()) {
                    if(this.j.a(v3) < v7) {
                        if(this.j.b(v3) < v6) {
                        }
                        else {
                            return v3;
                        }
                    }

                    if(v1 != null) {
                        goto label_32;
                    }

                    v1 = v3;
                }
                else if(v2 == null) {
                    v2 = v3;
                }
            }

        label_32:
            arg8 += v0;
        }

        if(v1 != null) {
        }
        else {
            v1 = v2;
        }

        return v1;
    }

    public int a(int arg3, p arg4, t arg5) {
        if(this.i == 1) {
            return 0;
        }

        return this.c(arg3, arg4, arg5);
    }

    void a(p arg8, t arg9, c arg10, android.support.v7.widget.LinearLayoutManager$b arg11) {
        int v5;
        int v4;
        int v3_1;
        int v2;
        int v0_1;
        boolean v3;
        boolean v0;
        View v8 = arg10.a(arg8);
        if(v8 == null) {
            arg11.b = true;
            return;
        }

        ViewGroup$LayoutParams v6 = v8.getLayoutParams();
        int v1 = -1;
        if(arg10.k == null) {
            v0 = this.k;
            v3 = arg10.f == v1 ? true : false;
            if(v0 == v3) {
                this.b(v8);
                goto label_32;
            }

            this.b(v8, 0);
        }
        else {
            v0 = this.k;
            v3 = arg10.f == v1 ? true : false;
            if(v0 == v3) {
                this.a(v8);
                goto label_32;
            }

            this.a(v8, 0);
        }

    label_32:
        this.a(v8, 0, 0);
        arg11.a = this.j.e(v8);
        if(this.i == 1) {
            if(this.h()) {
                v0_1 = this.A() - this.E();
                v2 = v0_1 - this.j.f(v8);
            }
            else {
                v2 = this.C();
                v0_1 = this.j.f(v8) + v2;
            }

            if(arg10.f == v1) {
                v1 = arg10.b;
                v3_1 = arg10.b - arg11.a;
                v4 = v0_1;
                v5 = v1;
                goto label_92;
            }

            v1 = arg10.b;
            v5 = arg10.b + arg11.a;
            v4 = v0_1;
            v3_1 = v1;
        }
        else {
            v0_1 = this.D();
            v2 = this.j.f(v8) + v0_1;
            if(arg10.f == v1) {
                v3_1 = v0_1;
                v4 = arg10.b;
                v5 = v2;
                v2 = arg10.b - arg11.a;
                goto label_92;
            }

            v1 = arg10.b;
            v4 = arg10.b + arg11.a;
            v3_1 = v0_1;
            v5 = v2;
            v2 = v1;
        }

    label_92:
        this.a(v8, v2, v3_1, v4, v5);
        if((((j)v6).d()) || (((j)v6).e())) {
            arg11.c = true;
        }

        arg11.d = v8.hasFocusable();
    }

    public j a() {
        return new j(-2, -2);
    }

    public View a(View arg4, int arg5, p arg6, t arg7) {
        this.N();
        View v0 = null;
        if(this.x() == 0) {
            return v0;
        }

        int v4 = this.f(arg5);
        arg5 = -2147483648;
        if(v4 == arg5) {
            return v0;
        }

        this.i();
        this.i();
        this.a(v4, ((int)((((float)this.j.f())) * 0.333333f)), false, arg7);
        this.a.g = arg5;
        this.a.a = false;
        this.a(arg6, this.a, arg7, true);
        arg5 = -1;
        View v6 = v4 == arg5 ? this.k(arg6, arg7) : this.j(arg6, arg7);
        arg4 = v4 == arg5 ? this.O() : this.P();
        if(arg4.hasFocusable()) {
            if(v6 == null) {
                return v0;
            }

            return arg4;
        }

        return v6;
    }

    public void a(int arg2, int arg3, t arg4, android.support.v7.widget.RecyclerView$i$a arg5) {
        if(this.i == 0) {
        }
        else {
            arg2 = arg3;
        }

        if(this.x() != 0) {
            if(arg2 == 0) {
            }
            else {
                this.i();
                int v0 = arg2 > 0 ? 1 : -1;
                this.a(v0, Math.abs(arg2), true, arg4);
                this.a(arg4, this.a, arg5);
            }
        }
    }

    void a(t arg2, c arg3, android.support.v7.widget.RecyclerView$i$a arg4) {
        int v0 = arg3.d;
        if(v0 >= 0 && v0 < arg2.e()) {
            arg4.b(v0, Math.max(0, arg3.g));
        }
    }

    public void a(int arg6, android.support.v7.widget.RecyclerView$i$a arg7) {
        int v3;
        boolean v0;
        int v1 = -1;
        if(this.n == null || !this.n.a()) {
            this.N();
            v0 = this.k;
            if(this.l != v1) {
                v3 = this.l;
            }
            else if(v0) {
                v3 = arg6 - 1;
            }
            else {
                v3 = 0;
            }
        }
        else {
            v0 = this.n.c;
            v3 = this.n.a;
        }

        if(v0) {
        }
        else {
            v1 = 1;
        }

        int v0_1;
        for(v0_1 = 0; v0_1 < this.h; ++v0_1) {
            if(v3 < 0) {
                return;
            }

            if(v3 >= arg6) {
                return;
            }

            arg7.b(v3, 0);
            v3 += v1;
        }
    }

    public void a(Parcelable arg2) {
        if((arg2 instanceof SavedState)) {
            this.n = ((SavedState)arg2);
            this.q();
        }
    }

    void a(p arg1, t arg2, a arg3, int arg4) {
    }

    public void a(t arg1) {
        super.a(arg1);
        this.n = null;
        this.l = -1;
        this.m = -2147483648;
        this.o.a();
    }

    public void a(RecyclerView arg1, p arg2) {
        super.a(arg1, arg2);
        if(this.f) {
            this.c(arg2);
            arg2.a();
        }
    }

    public void a(View arg4, View arg5, int arg6, int arg7) {
        int v4;
        this.a("Cannot drop a view during a scroll or layout calculation");
        this.i();
        this.N();
        arg6 = this.d(arg4);
        arg7 = this.d(arg5);
        int v0 = -1;
        arg6 = arg6 < arg7 ? 1 : -1;
        if(this.k) {
            if(arg6 == 1) {
                this.b(arg7, this.j.d() - (this.j.a(arg5) + this.j.e(arg4)));
            }
            else {
                v4 = this.j.d() - this.j.b(arg5);
                goto label_30;
            }
        }
        else if(arg6 == v0) {
            v4 = this.j.a(arg5);
        label_30:
            this.b(arg7, v4);
        }
        else {
            this.b(arg7, this.j.b(arg5) - this.j.e(arg4));
        }
    }

    public void a(String arg2) {
        if(this.n == null) {
            super.a(arg2);
        }
    }

    public void a(AccessibilityEvent arg2) {
        super.a(arg2);
        if(this.x() > 0) {
            arg2.setFromIndex(this.m());
            arg2.setToIndex(this.o());
        }
    }

    public void b(int arg4) {
        if(arg4 != 0) {
            if(arg4 == 1) {
            }
            else {
                StringBuilder v1 = new StringBuilder();
                v1.append("invalid orientation:");
                v1.append(arg4);
                throw new IllegalArgumentException(v1.toString());
            }
        }

        this.a(null);
        if(arg4 != this.i || this.j == null) {
            this.j = au.a(((i)this), arg4);
            this.o.a = this.j;
            this.i = arg4;
            this.q();
        }
    }

    public void b(boolean arg2) {
        this.a(null);
        if(arg2 == this.c) {
            return;
        }

        this.c = arg2;
        this.q();
    }

    protected int b(t arg1) {
        if(arg1.d()) {
            return this.j.f();
        }

        return 0;
    }

    private void b(p arg6, int arg7) {
        int v0 = this.x();
        if(arg7 < 0) {
            return;
        }

        int v1 = this.j.e() - arg7;
        if(this.k) {
            int v2 = 0;
            while(true) {
                if(v2 < v0) {
                    View v3 = this.i(v2);
                    if(this.j.a(v3) >= v1) {
                        if(this.j.d(v3) < v1) {
                        }
                        else {
                            ++v2;
                            continue;
                        }
                    }

                    break;
                }

                return;
            }

            this.a(arg6, 0, v2);
            return;
        }
        else {
            --v0;
            arg7 = v0;
            while(true) {
                if(arg7 < 0) {
                    return;
                }

                View v2_1 = this.i(arg7);
                if(this.j.a(v2_1) >= v1) {
                    if(this.j.d(v2_1) < v1) {
                    }
                    else {
                        --arg7;
                        continue;
                    }
                }

                break;
            }

            this.a(arg6, v0, arg7);
        }
    }

    private boolean b(p arg5, t arg6, a arg7) {
        int v1 = 0;
        if(this.x() == 0) {
            return 0;
        }

        View v0 = this.G();
        if(v0 != null && (arg7.a(v0, arg6))) {
            arg7.a(v0, this.d(v0));
            return 1;
        }

        if(this.b != this.d) {
            return 0;
        }

        View v5 = arg7.d ? this.f(arg5, arg6) : this.g(arg5, arg6);
        if(v5 != null) {
            arg7.b(v5, this.d(v5));
            if(!arg6.a() && (this.b())) {
                if(this.j.a(v5) >= this.j.d() || this.j.b(v5) < this.j.c()) {
                    v1 = 1;
                }

                if(v1 == 0) {
                    return 1;
                }

                int v5_1 = arg7.d ? this.j.d() : this.j.c();
                arg7.c = v5_1;
            }

            return 1;
        }

        return 0;
    }

    private int b(int arg2, p arg3, t arg4, boolean arg5) {
        int v0 = arg2 - this.j.c();
        if(v0 > 0) {
            int v3 = -this.c(v0, arg3, arg4);
            arg2 += v3;
            if(arg5) {
                arg2 -= this.j.c();
                if(arg2 > 0) {
                    this.j.a(-arg2);
                    v3 -= arg2;
                }
            }

            return v3;
        }

        return 0;
    }

    private View b(boolean arg3, boolean arg4) {
        int v1;
        int v0;
        if(this.k) {
            v0 = 0;
            v1 = this.x();
        }
        else {
            v0 = this.x() - 1;
            v1 = -1;
        }

        return this.a(v0, v1, arg3, arg4);
    }

    private void b(a arg2) {
        this.h(arg2.b, arg2.c);
    }

    private void b(p arg16, t arg17, int arg18, int arg19) {
        LinearLayoutManager v0 = this;
        p v1 = arg16;
        t v2 = arg17;
        if((arg17.b()) && this.x() != 0 && !arg17.a()) {
            if(!this.b()) {
            }
            else {
                List v3 = arg16.c();
                int v4 = v3.size();
                int v6 = this.d(this.i(0));
                int v7 = 0;
                int v8 = 0;
                int v9 = 0;
                while(v7 < v4) {
                    Object v10 = v3.get(v7);
                    if(((w)v10).isRemoved()) {
                    }
                    else {
                        int v12 = 1;
                        boolean v11 = ((w)v10).getLayoutPosition() < v6 ? true : false;
                        int v14 = -1;
                        if(v11 != v0.k) {
                            v12 = -1;
                        }

                        if(v12 == v14) {
                            v8 += v0.j.e(((w)v10).itemView);
                            goto label_45;
                        }

                        v9 += v0.j.e(((w)v10).itemView);
                    }

                label_45:
                    ++v7;
                }

                v0.a.k = v3;
                if(v8 > 0) {
                    this.h(this.d(this.O()), arg18);
                    v0.a.h = v8;
                    v0.a.c = 0;
                    v0.a.a();
                    this.a(v1, v0.a, v2, false);
                }

                if(v9 > 0) {
                    this.a(this.d(this.P()), arg19);
                    v0.a.h = v9;
                    v0.a.c = 0;
                    v0.a.a();
                    this.a(v1, v0.a, v2, false);
                }

                v0.a.k = null;
            }
        }
    }

    public boolean b() {
        boolean v0 = this.n != null || this.b != this.d ? false : true;
        return v0;
    }

    public void b(int arg1, int arg2) {
        this.l = arg1;
        this.m = arg2;
        if(this.n != null) {
            this.n.b();
        }

        this.q();
    }

    public int b(int arg2, p arg3, t arg4) {
        if(this.i == 0) {
            return 0;
        }

        return this.c(arg2, arg3, arg4);
    }

    int c(int arg6, p arg7, t arg8) {
        if(this.x() != 0) {
            if(arg6 == 0) {
            }
            else {
                this.a.a = true;
                this.i();
                int v0 = arg6 > 0 ? 1 : -1;
                int v3 = Math.abs(arg6);
                this.a(v0, v3, true, arg8);
                int v2 = this.a.g + this.a(arg7, this.a, arg8, false);
                if(v2 < 0) {
                    return 0;
                }

                if(v3 > v2) {
                    arg6 = v0 * v2;
                }

                this.j.a(-arg6);
                this.a.j = arg6;
                return arg6;
            }
        }

        return 0;
    }

    public View c(int arg3) {
        int v0 = this.x();
        if(v0 == 0) {
            return null;
        }

        int v1 = arg3 - this.d(this.i(0));
        if(v1 >= 0 && v1 < v0) {
            View v0_1 = this.i(v1);
            if(this.d(v0_1) == arg3) {
                return v0_1;
            }
        }

        return super.c(arg3);
    }

    View c(int arg4, int arg5) {
        int v1;
        int v0;
        this.i();
        if(arg5 > arg4) {
            v0 = 1;
        }
        else if(arg5 < arg4) {
            v0 = -1;
        }
        else {
            v0 = 0;
        }

        if(v0 == 0) {
            return this.i(arg4);
        }

        if(this.j.a(this.i(arg4)) < this.j.c()) {
            v0 = 16644;
            v1 = 16388;
        }
        else {
            v0 = 4161;
            v1 = 4097;
        }

        bq v2 = this.i == 0 ? this.r : this.s;
        View v4 = v2.a(arg4, arg5, v0, v1);
        return v4;
    }

    public int c(t arg1) {
        return this.i(arg1);
    }

    public void c(p arg9, t arg10) {
        int v5_1;
        int v6;
        int v3;
        int v1 = -1;
        if((this.n != null || this.l != v1) && arg10.e() == 0) {
            this.c(arg9);
            return;
        }

        if(this.n != null && (this.n.a())) {
            this.l = this.n.a;
        }

        this.i();
        this.a.a = false;
        this.N();
        View v0 = this.G();
        if(!this.o.e || this.l != v1 || this.n != null) {
            this.o.a();
            this.o.d = this.k ^ this.d;
            this.a(arg9, arg10, this.o);
            this.o.e = true;
        }
        else if(v0 != null) {
            if(this.j.a(v0) < this.j.d() && this.j.b(v0) > this.j.c()) {
                goto label_58;
            }

            this.o.a(v0, this.d(v0));
        }

    label_58:
        int v0_1 = this.b(arg10);
        if(this.a.j >= 0) {
            v3 = v0_1;
            v0_1 = 0;
        }
        else {
            v3 = 0;
        }

        v0_1 += this.j.c();
        v3 += this.j.g();
        if((arg10.a()) && this.l != v1 && this.m != -2147483648) {
            View v5 = this.c(this.l);
            if(v5 != null) {
                if(this.k) {
                    v6 = this.j.d() - this.j.b(v5);
                    v5_1 = this.m;
                }
                else {
                    v5_1 = this.j.a(v5) - this.j.c();
                    v6 = this.m;
                }

                v6 -= v5_1;
                if(v6 > 0) {
                    v0_1 += v6;
                    goto label_103;
                }

                v3 -= v6;
            }
        }

    label_103:
        if(this.o.d) {
            if(!this.k) {
                goto label_112;
            }

            goto label_108;
        }
        else if(!this.k) {
        label_108:
            v1 = 1;
        }

    label_112:
        this.a(arg9, arg10, this.o, v1);
        this.a(arg9);
        this.a.l = this.k();
        this.a.i = arg10.a();
        if(this.o.d) {
            this.b(this.o);
            this.a.h = v0_1;
            this.a(arg9, this.a, arg10, false);
            v0_1 = this.a.b;
            v1 = this.a.d;
            if(this.a.c > 0) {
                v3 += this.a.c;
            }

            this.a(this.o);
            this.a.h = v3;
            this.a.d += this.a.e;
            this.a(arg9, this.a, arg10, false);
            v3 = this.a.b;
            if(this.a.c <= 0) {
                goto label_209;
            }

            v5_1 = this.a.c;
            this.h(v1, v0_1);
            this.a.h = v5_1;
            this.a(arg9, this.a, arg10, false);
            v0_1 = this.a.b;
        }
        else {
            this.a(this.o);
            this.a.h = v3;
            this.a(arg9, this.a, arg10, false);
            v3 = this.a.b;
            v1 = this.a.d;
            if(this.a.c > 0) {
                v0_1 += this.a.c;
            }

            this.b(this.o);
            this.a.h = v0_1;
            this.a.d += this.a.e;
            this.a(arg9, this.a, arg10, false);
            v0_1 = this.a.b;
            if(this.a.c <= 0) {
                goto label_209;
            }

            v5_1 = this.a.c;
            this.a(v1, v3);
            this.a.h = v5_1;
            this.a(arg9, this.a, arg10, false);
            v3 = this.a.b;
        }

    label_209:
        if(this.x() > 0) {
            if((this.k ^ this.d) != 0) {
                v1 = this.a(v3, arg9, arg10, true);
                v0_1 += v1;
                v3 += v1;
                v1 = this.b(v0_1, arg9, arg10, false);
            }
            else {
                v1 = this.b(v0_1, arg9, arg10, true);
                v0_1 += v1;
                v3 += v1;
                v1 = this.a(v3, arg9, arg10, false);
            }

            v0_1 += v1;
            v3 += v1;
        }

        this.b(arg9, arg10, v0_1, v3);
        if(!arg10.a()) {
            this.j.a();
        }
        else {
            this.o.a();
        }

        this.b = this.d;
    }

    public boolean c() {
        return 1;
    }

    public int d(t arg1) {
        return this.i(arg1);
    }

    public PointF d(int arg4) {
        if(this.x() == 0) {
            return null;
        }

        boolean v0 = false;
        int v2 = 1;
        if(arg4 < this.d(this.i(0))) {
            v0 = true;
        }

        if(v0 != this.k) {
            v2 = -1;
        }

        if(this.i == 0) {
            return new PointF(((float)v2), 0f);
        }

        return new PointF(0f, ((float)v2));
    }

    public Parcelable d() {
        View v1_1;
        if(this.n != null) {
            return new SavedState(this.n);
        }

        SavedState v0 = new SavedState();
        if(this.x() > 0) {
            this.i();
            int v1 = this.b ^ this.k;
            v0.c = ((boolean)v1);
            if(v1 != 0) {
                v1_1 = this.P();
                v0.b = this.j.d() - this.j.b(v1_1);
                v0.a = this.d(v1_1);
            }
            else {
                v1_1 = this.O();
                v0.a = this.d(v1_1);
                v0.b = this.j.a(v1_1) - this.j.c();
            }
        }
        else {
            v0.b();
        }

        return ((Parcelable)v0);
    }

    public int e(t arg1) {
        return this.j(arg1);
    }

    public void e(int arg1) {
        this.l = arg1;
        this.m = -2147483648;
        if(this.n != null) {
            this.n.b();
        }

        this.q();
    }

    public boolean e() {
        boolean v0 = this.i == 0 ? true : false;
        return v0;
    }

    private View f(p arg2, t arg3) {
        View v2 = this.k ? this.h(arg2, arg3) : this.i(arg2, arg3);
        return v2;
    }

    int f(int arg5) {
        int v1 = -1;
        int v2 = -2147483648;
        if(arg5 == 17) {
            goto label_40;
        }

        if(arg5 == 33) {
            goto label_35;
        }

        if(arg5 == 66) {
            goto label_31;
        }

        if(arg5 == 130) {
            goto label_27;
        }

        switch(arg5) {
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_13;
            }
        }

        return v2;
    label_20:
        if(this.i == 1) {
            return v1;
        }

        if(this.h()) {
            return 1;
        }

        return v1;
    label_13:
        if(this.i == 1) {
            return 1;
        }

        if(this.h()) {
            return v1;
        }

        return 1;
    label_27:
        if(this.i == 1) {
            v2 = 1;
        }

        return v2;
    label_31:
        if(this.i == 0) {
            v2 = 1;
        }

        return v2;
    label_35:
        if(this.i == 1) {
        }
        else {
            v1 = -2147483648;
        }

        return v1;
    label_40:
        if(this.i == 0) {
        }
        else {
            v1 = -2147483648;
        }

        return v1;
    }

    public int f(t arg1) {
        return this.j(arg1);
    }

    public boolean f() {
        boolean v1 = true;
        if(this.i == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private View g(p arg2, t arg3) {
        View v2 = this.k ? this.i(arg2, arg3) : this.h(arg2, arg3);
        return v2;
    }

    public int g() {
        return this.i;
    }

    public int g(t arg1) {
        return this.k(arg1);
    }

    protected boolean h() {
        boolean v1 = true;
        if(this.v() == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private void h(int arg3, int arg4) {
        this.a.c = arg4 - this.j.c();
        this.a.d = arg3;
        c v3 = this.a;
        int v1 = -1;
        int v0 = this.k ? 1 : -1;
        v3.e = v0;
        this.a.f = v1;
        this.a.b = arg4;
        this.a.g = -2147483648;
    }

    private View h(p arg7, t arg8) {
        return this.a(arg7, arg8, 0, this.x(), arg8.e());
    }

    public int h(t arg1) {
        return this.k(arg1);
    }

    private View i(p arg8, t arg9) {
        return this.a(arg8, arg9, this.x() - 1, -1, arg9.e());
    }

    private int i(t arg8) {
        if(this.x() == 0) {
            return 0;
        }

        this.i();
        return bb.a(arg8, this.j, this.a(this.e ^ 1, true), this.b(this.e ^ 1, true), this, this.e, this.k);
    }

    void i() {
        if(this.a == null) {
            this.a = this.j();
        }
    }

    private int j(t arg7) {
        if(this.x() == 0) {
            return 0;
        }

        this.i();
        return bb.a(arg7, this.j, this.a(this.e ^ 1, true), this.b(this.e ^ 1, true), this, this.e);
    }

    private View j(p arg2, t arg3) {
        View v2 = this.k ? this.l(arg2, arg3) : this.m(arg2, arg3);
        return v2;
    }

    c j() {
        return new c();
    }

    boolean k() {
        boolean v0 = this.j.h() != 0 || this.j.e() != 0 ? false : true;
        return v0;
    }

    private int k(t arg7) {
        if(this.x() == 0) {
            return 0;
        }

        this.i();
        return bb.b(arg7, this.j, this.a(this.e ^ 1, true), this.b(this.e ^ 1, true), this, this.e);
    }

    private View k(p arg2, t arg3) {
        View v2 = this.k ? this.m(arg2, arg3) : this.l(arg2, arg3);
        return v2;
    }

    private View l(p arg1, t arg2) {
        return this.c(0, this.x());
    }

    boolean l() {
        int v1 = 1073741824;
        boolean v0 = this.z() == v1 || this.y() == v1 || !this.M() ? false : true;
        return v0;
    }

    private View m(p arg1, t arg2) {
        return this.c(this.x() - 1, -1);
    }

    public int m() {
        View v0 = this.a(0, this.x(), false, true);
        int v0_1 = v0 == null ? -1 : this.d(v0);
        return v0_1;
    }

    public int n() {
        View v0 = this.a(0, this.x(), true, false);
        int v0_1 = v0 == null ? -1 : this.d(v0);
        return v0_1;
    }

    public int o() {
        int v2 = -1;
        View v0 = this.a(this.x() - 1, v2, false, true);
        if(v0 == null) {
        }
        else {
            v2 = this.d(v0);
        }

        return v2;
    }

    public int p() {
        int v2 = -1;
        View v0 = this.a(this.x() - 1, v2, true, false);
        if(v0 == null) {
        }
        else {
            v2 = this.d(v0);
        }

        return v2;
    }
}

