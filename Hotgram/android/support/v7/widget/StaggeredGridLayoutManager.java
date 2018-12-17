package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends i implements b {
    class android.support.v7.widget.StaggeredGridLayoutManager$1 implements Runnable {
        android.support.v7.widget.StaggeredGridLayoutManager$1(StaggeredGridLayoutManager arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.g();
        }
    }

    class LazySpanLookup {
        class FullSpanItem implements Parcelable {
            final class android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem$1 implements Parcelable$Creator {
                android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem$1() {
                    super();
                }

                public FullSpanItem a(Parcel arg2) {
                    return new FullSpanItem(arg2);
                }

                public FullSpanItem[] a(int arg1) {
                    return new FullSpanItem[arg1];
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
            int[] c;
            boolean d;

            static {
                FullSpanItem.CREATOR = new android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem$1();
            }

            FullSpanItem() {
                super();
            }

            FullSpanItem(Parcel arg3) {
                super();
                this.a = arg3.readInt();
                this.b = arg3.readInt();
                boolean v1 = true;
                if(arg3.readInt() == 1) {
                }
                else {
                    v1 = false;
                }

                this.d = v1;
                int v0 = arg3.readInt();
                if(v0 > 0) {
                    this.c = new int[v0];
                    arg3.readIntArray(this.c);
                }
            }

            int a(int arg2) {
                return this.c == null ? 0 : this.c[arg2];
            }

            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + '}';
            }

            public void writeToParcel(Parcel arg1, int arg2) {
                arg1.writeInt(this.a);
                arg1.writeInt(this.b);
                arg1.writeInt(this.d);
                if(this.c == null || this.c.length <= 0) {
                    arg1.writeInt(0);
                }
                else {
                    arg1.writeInt(this.c.length);
                    arg1.writeIntArray(this.c);
                }
            }
        }

        int[] a;
        List b;

        LazySpanLookup() {
            super();
        }

        void a(int arg2, c arg3) {
            this.e(arg2);
            this.a[arg2] = arg3.e;
        }

        public void a(FullSpanItem arg6) {
            if(this.b == null) {
                this.b = new ArrayList();
            }

            int v0 = this.b.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.b.get(v1);
                if(((FullSpanItem)v2).a == arg6.a) {
                    this.b.remove(v1);
                }

                if(((FullSpanItem)v2).a >= arg6.a) {
                    this.b.add(v1, arg6);
                    return;
                }
            }

            this.b.add(arg6);
        }

        void a() {
            if(this.a != null) {
                Arrays.fill(this.a, -1);
            }

            this.b = null;
        }

        void a(int arg5, int arg6) {
            if(this.a != null) {
                if(arg5 >= this.a.length) {
                }
                else {
                    int v0 = arg5 + arg6;
                    this.e(v0);
                    System.arraycopy(this.a, v0, this.a, arg5, this.a.length - arg5 - arg6);
                    Arrays.fill(this.a, this.a.length - arg6, this.a.length, -1);
                    this.c(arg5, arg6);
                }
            }
        }

        public FullSpanItem a(int arg6, int arg7, int arg8, boolean arg9) {
            FullSpanItem v1 = null;
            if(this.b == null) {
                return v1;
            }

            int v0 = this.b.size();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                Object v3 = this.b.get(v2);
                if(((FullSpanItem)v3).a >= arg7) {
                    return v1;
                }

                if(((FullSpanItem)v3).a >= arg6) {
                    if(arg8 != 0 && ((FullSpanItem)v3).b != arg8) {
                        if(!arg9) {
                        }
                        else if(((FullSpanItem)v3).d) {
                            goto label_21;
                        }

                        goto label_22;
                    }

                label_21:
                    return ((FullSpanItem)v3);
                }

            label_22:
            }

            return v1;
        }

        int a(int arg3) {
            if(this.b != null) {
                int v0;
                for(v0 = this.b.size() - 1; v0 >= 0; --v0) {
                    if(this.b.get(v0).a >= arg3) {
                        this.b.remove(v0);
                    }
                }
            }

            return this.b(arg3);
        }

        int b(int arg4) {
            int v1 = -1;
            if(this.a == null) {
                return v1;
            }

            if(arg4 >= this.a.length) {
                return v1;
            }

            int v0 = this.g(arg4);
            if(v0 == v1) {
                Arrays.fill(this.a, arg4, this.a.length, v1);
                return this.a.length;
            }

            ++v0;
            Arrays.fill(this.a, arg4, v0, v1);
            return v0;
        }

        void b(int arg5, int arg6) {
            if(this.a != null) {
                if(arg5 >= this.a.length) {
                }
                else {
                    int v0 = arg5 + arg6;
                    this.e(v0);
                    System.arraycopy(this.a, arg5, this.a, v0, this.a.length - arg5 - arg6);
                    Arrays.fill(this.a, arg5, v0, -1);
                    this.d(arg5, arg6);
                }
            }
        }

        int c(int arg2) {
            if(this.a != null) {
                if(arg2 >= this.a.length) {
                }
                else {
                    return this.a[arg2];
                }
            }

            return -1;
        }

        private void c(int arg5, int arg6) {
            if(this.b == null) {
                return;
            }

            int v0 = arg5 + arg6;
            int v1;
            for(v1 = this.b.size() - 1; v1 >= 0; --v1) {
                Object v2 = this.b.get(v1);
                if(((FullSpanItem)v2).a < arg5) {
                }
                else if(((FullSpanItem)v2).a < v0) {
                    this.b.remove(v1);
                }
                else {
                    ((FullSpanItem)v2).a -= arg6;
                }
            }
        }

        private void d(int arg4, int arg5) {
            if(this.b == null) {
                return;
            }

            int v0;
            for(v0 = this.b.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.b.get(v0);
                if(((FullSpanItem)v1).a < arg4) {
                }
                else {
                    ((FullSpanItem)v1).a += arg5;
                }
            }
        }

        int d(int arg2) {
            int v0;
            for(v0 = this.a.length; v0 <= arg2; v0 *= 2) {
            }

            return v0;
        }

        void e(int arg5) {
            int v1 = -1;
            if(this.a == null) {
                this.a = new int[Math.max(arg5, 10) + 1];
                Arrays.fill(this.a, v1);
            }
            else if(arg5 >= this.a.length) {
                int[] v0 = this.a;
                this.a = new int[this.d(arg5)];
                System.arraycopy(v0, 0, this.a, 0, v0.length);
                Arrays.fill(this.a, v0.length, this.a.length, v1);
            }
        }

        public FullSpanItem f(int arg5) {
            FullSpanItem v1 = null;
            if(this.b == null) {
                return v1;
            }

            int v0;
            for(v0 = this.b.size() - 1; v0 >= 0; --v0) {
                Object v2 = this.b.get(v0);
                if(((FullSpanItem)v2).a == arg5) {
                    return ((FullSpanItem)v2);
                }
            }

            return v1;
        }

        private int g(int arg5) {
            int v1 = -1;
            if(this.b == null) {
                return v1;
            }

            FullSpanItem v0 = this.f(arg5);
            if(v0 != null) {
                this.b.remove(v0);
            }

            int v0_1 = this.b.size();
            int v2 = 0;
            while(true) {
                if(v2 >= v0_1) {
                    break;
                }
                else if(this.b.get(v2).a >= arg5) {
                }
                else {
                    ++v2;
                    continue;
                }

                goto label_20;
            }

            v2 = -1;
        label_20:
            if(v2 != v1) {
                Object v5 = this.b.get(v2);
                this.b.remove(v2);
                return ((FullSpanItem)v5).a;
            }

            return v1;
        }
    }

    public class SavedState implements Parcelable {
        final class android.support.v7.widget.StaggeredGridLayoutManager$SavedState$1 implements Parcelable$Creator {
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState$1() {
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
        int c;
        int[] d;
        int e;
        int[] f;
        List g;
        boolean h;
        boolean i;
        boolean j;

        static {
            SavedState.CREATOR = new android.support.v7.widget.StaggeredGridLayoutManager$SavedState$1();
        }

        public SavedState(SavedState arg2) {
            super();
            this.c = arg2.c;
            this.a = arg2.a;
            this.b = arg2.b;
            this.d = arg2.d;
            this.e = arg2.e;
            this.f = arg2.f;
            this.h = arg2.h;
            this.i = arg2.i;
            this.j = arg2.j;
            this.g = arg2.g;
        }

        public SavedState() {
            super();
        }

        SavedState(Parcel arg4) {
            super();
            this.a = arg4.readInt();
            this.b = arg4.readInt();
            this.c = arg4.readInt();
            if(this.c > 0) {
                this.d = new int[this.c];
                arg4.readIntArray(this.d);
            }

            this.e = arg4.readInt();
            if(this.e > 0) {
                this.f = new int[this.e];
                arg4.readIntArray(this.f);
            }

            boolean v1 = false;
            boolean v0 = arg4.readInt() == 1 ? true : false;
            this.h = v0;
            v0 = arg4.readInt() == 1 ? true : false;
            this.i = v0;
            if(arg4.readInt() == 1) {
                v1 = true;
            }

            this.j = v1;
            this.g = arg4.readArrayList(FullSpanItem.class.getClassLoader());
        }

        void a() {
            this.d = null;
            this.c = 0;
            this.e = 0;
            this.f = null;
            this.g = null;
        }

        void b() {
            this.d = null;
            this.c = 0;
            this.a = -1;
            this.b = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            arg1.writeInt(this.a);
            arg1.writeInt(this.b);
            arg1.writeInt(this.c);
            if(this.c > 0) {
                arg1.writeIntArray(this.d);
            }

            arg1.writeInt(this.e);
            if(this.e > 0) {
                arg1.writeIntArray(this.f);
            }

            arg1.writeInt(this.h);
            arg1.writeInt(this.i);
            arg1.writeInt(this.j);
            arg1.writeList(this.g);
        }
    }

    class a {
        int a;
        int b;
        boolean c;
        boolean d;
        boolean e;
        int[] f;

        a(StaggeredGridLayoutManager arg1) {
            this.g = arg1;
            super();
            this.a();
        }

        void a() {
            int v0 = -1;
            this.a = v0;
            this.b = -2147483648;
            this.c = false;
            this.d = false;
            this.e = false;
            if(this.f != null) {
                Arrays.fill(this.f, v0);
            }
        }

        void a(c[] arg6) {
            int v0 = arg6.length;
            if(this.f == null || this.f.length < v0) {
                this.f = new int[this.g.a.length];
            }

            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.f[v1] = arg6[v1].a(-2147483648);
            }
        }

        void a(int arg2) {
            int v0 = this.c ? this.g.b.d() - arg2 : this.g.b.c() + arg2;
            this.b = v0;
        }

        void b() {
            int v0 = this.c ? this.g.b.d() : this.g.b.c();
            this.b = v0;
        }
    }

    public class android.support.v7.widget.StaggeredGridLayoutManager$b extends j {
        c a;
        boolean b;

        public android.support.v7.widget.StaggeredGridLayoutManager$b(int arg1, int arg2) {
            super(arg1, arg2);
        }

        public android.support.v7.widget.StaggeredGridLayoutManager$b(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        public android.support.v7.widget.StaggeredGridLayoutManager$b(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
        }

        public android.support.v7.widget.StaggeredGridLayoutManager$b(ViewGroup$LayoutParams arg1) {
            super(arg1);
        }

        public boolean a() {
            return this.b;
        }

        public final int b() {
            if(this.a == null) {
                return -1;
            }

            return this.a.e;
        }
    }

    class c {
        ArrayList a;
        int b;
        int c;
        int d;
        final int e;

        c(StaggeredGridLayoutManager arg1, int arg2) {
            this.f = arg1;
            super();
            this.a = new ArrayList();
            this.b = -2147483648;
            this.c = -2147483648;
            this.d = 0;
            this.e = arg2;
        }

        int a(int arg3) {
            if(this.b != -2147483648) {
                return this.b;
            }

            if(this.a.size() == 0) {
                return arg3;
            }

            this.a();
            return this.b;
        }

        void a(boolean arg4, int arg5) {
            int v0 = -2147483648;
            int v1 = arg4 ? this.b(v0) : this.a(v0);
            this.e();
            if(v1 == v0) {
                return;
            }

            if((arg4) && v1 < this.f.b.d() || !arg4 && v1 > this.f.b.c()) {
                return;
            }

            if(arg5 != v0) {
                v1 += arg5;
            }

            this.c = v1;
            this.b = v1;
        }

        void a(View arg5) {
            android.support.v7.widget.StaggeredGridLayoutManager$b v0 = this.c(arg5);
            v0.a = this;
            this.a.add(0, arg5);
            int v1 = -2147483648;
            this.b = v1;
            if(this.a.size() == 1) {
                this.c = v1;
            }

            if((v0.d()) || (v0.e())) {
                this.d += this.f.b.e(arg5);
            }
        }

        public View a(int arg5, int arg6) {
            Object v1_1;
            View v1 = null;
            if(arg6 == -1) {
                arg6 = this.a.size();
                int v0 = 0;
                while(v0 < arg6) {
                    Object v2 = this.a.get(v0);
                    if((this.f.d) && this.f.d(((View)v2)) <= arg5) {
                        break;
                    }

                    if(!this.f.d && this.f.d(((View)v2)) >= arg5) {
                        break;
                    }

                    if(!((View)v2).hasFocusable()) {
                        break;
                    }

                    ++v0;
                    v1_1 = v2;
                }
            }
            else {
                arg6 = this.a.size() - 1;
                while(arg6 >= 0) {
                    Object v0_1 = this.a.get(arg6);
                    if((this.f.d) && this.f.d(((View)v0_1)) >= arg5) {
                        break;
                    }

                    if(!this.f.d && this.f.d(((View)v0_1)) <= arg5) {
                        break;
                    }

                    if(!((View)v0_1).hasFocusable()) {
                        break;
                    }

                    --arg6;
                    v1_1 = v0_1;
                }
            }

            return ((View)v1_1);
        }

        void a() {
            Object v0 = this.a.get(0);
            android.support.v7.widget.StaggeredGridLayoutManager$b v1 = this.c(((View)v0));
            this.b = this.f.b.a(((View)v0));
            if(v1.b) {
                FullSpanItem v0_1 = this.f.h.f(v1.f());
                if(v0_1 != null && v0_1.b == -1) {
                    this.b -= v0_1.a(this.e);
                }
            }
        }

        int a(int arg7, int arg8, boolean arg9) {
            return this.a(arg7, arg8, false, false, arg9);
        }

        int a(int arg11, int arg12, boolean arg13, boolean arg14, boolean arg15) {
            int v9;
            int v0 = this.f.b.c();
            int v1 = this.f.b.d();
            int v2 = -1;
            int v4 = arg12 > arg11 ? 1 : -1;
            while(arg11 != arg12) {
                Object v5 = this.a.get(arg11);
                int v6 = this.f.b.a(((View)v5));
                int v7 = this.f.b.b(((View)v5));
                int v8 = 0;
                if(arg15) {
                    if(v6 > v1) {
                        goto label_26;
                    }

                    goto label_24;
                }
                else if(v6 < v1) {
                label_24:
                    v9 = 1;
                }
                else {
                label_26:
                    v9 = 0;
                }

                if(arg15) {
                    if(v7 < v0) {
                        goto label_36;
                    }

                    goto label_32;
                }
                else if(v7 > v0) {
                label_32:
                    v8 = 1;
                }

            label_36:
                if(v9 != 0 && v8 != 0) {
                    if(!arg13 || !arg14) {
                        if(arg14) {
                        }
                        else if(v6 >= v0) {
                            if(v7 > v1) {
                            }
                            else {
                                goto label_50;
                            }
                        }
                    }
                    else if(v6 < v0) {
                        goto label_50;
                    }
                    else if(v7 > v1) {
                        goto label_50;
                    }

                    return this.f.d(((View)v5));
                }

            label_50:
                arg11 += v4;
            }

            return v2;
        }

        int b(int arg3) {
            if(this.c != -2147483648) {
                return this.c;
            }

            if(this.a.size() == 0) {
                return arg3;
            }

            this.c();
            return this.c;
        }

        int b() {
            if(this.b != -2147483648) {
                return this.b;
            }

            this.a();
            return this.b;
        }

        void b(View arg5) {
            android.support.v7.widget.StaggeredGridLayoutManager$b v0 = this.c(arg5);
            v0.a = this;
            this.a.add(arg5);
            int v1 = -2147483648;
            this.c = v1;
            if(this.a.size() == 1) {
                this.b = v1;
            }

            if((v0.d()) || (v0.e())) {
                this.d += this.f.b.e(arg5);
            }
        }

        void c(int arg1) {
            this.b = arg1;
            this.c = arg1;
        }

        android.support.v7.widget.StaggeredGridLayoutManager$b c(View arg1) {
            return arg1.getLayoutParams();
        }

        void c() {
            Object v0 = this.a.get(this.a.size() - 1);
            android.support.v7.widget.StaggeredGridLayoutManager$b v1 = this.c(((View)v0));
            this.c = this.f.b.b(((View)v0));
            if(v1.b) {
                FullSpanItem v0_1 = this.f.h.f(v1.f());
                if(v0_1 != null && v0_1.b == 1) {
                    this.c += v0_1.a(this.e);
                }
            }
        }

        int d() {
            if(this.c != -2147483648) {
                return this.c;
            }

            this.c();
            return this.c;
        }

        void d(int arg3) {
            int v1 = -2147483648;
            if(this.b != v1) {
                this.b += arg3;
            }

            if(this.c != v1) {
                this.c += arg3;
            }
        }

        void e() {
            this.a.clear();
            this.f();
            this.d = 0;
        }

        void f() {
            this.b = -2147483648;
            this.c = -2147483648;
        }

        void g() {
            int v0 = this.a.size();
            Object v1 = this.a.remove(v0 - 1);
            android.support.v7.widget.StaggeredGridLayoutManager$b v2 = this.c(((View)v1));
            v2.a = null;
            if((v2.d()) || (v2.e())) {
                this.d -= this.f.b.e(((View)v1));
            }

            int v1_1 = -2147483648;
            if(v0 == 1) {
                this.b = v1_1;
            }

            this.c = v1_1;
        }

        void h() {
            Object v0 = this.a.remove(0);
            android.support.v7.widget.StaggeredGridLayoutManager$b v1 = this.c(((View)v0));
            v1.a = null;
            int v3 = -2147483648;
            if(this.a.size() == 0) {
                this.c = v3;
            }

            if((v1.d()) || (v1.e())) {
                this.d -= this.f.b.e(((View)v0));
            }

            this.b = v3;
        }

        public int i() {
            return this.d;
        }

        public int j() {
            int v2;
            int v0;
            if(this.f.d) {
                v0 = this.a.size() - 1;
                v2 = -1;
            }
            else {
                v0 = 0;
                v2 = this.a.size();
            }

            return this.a(v0, v2, true);
        }

        public int k() {
            int v2;
            int v0;
            if(this.f.d) {
                v0 = 0;
                v2 = this.a.size();
            }
            else {
                v0 = this.a.size() - 1;
                v2 = -1;
            }

            return this.a(v0, v2, true);
        }
    }

    private SavedState A;
    private int B;
    private final Rect C;
    private final a D;
    private boolean E;
    private boolean F;
    private int[] G;
    private final Runnable H;
    c[] a;
    au b;
    au c;
    boolean d;
    boolean e;
    int f;
    int g;
    LazySpanLookup h;
    private int i;
    private int j;
    private int k;
    private final ao l;
    private BitSet m;
    private int n;
    private boolean o;
    private boolean z;

    public StaggeredGridLayoutManager(Context arg3, AttributeSet arg4, int arg5, int arg6) {
        super();
        this.i = -1;
        this.d = false;
        this.e = false;
        this.f = -1;
        this.g = -2147483648;
        this.h = new LazySpanLookup();
        this.n = 2;
        this.C = new Rect();
        this.D = new a(this);
        this.E = false;
        this.F = true;
        this.H = new android.support.v7.widget.StaggeredGridLayoutManager$1(this);
        android.support.v7.widget.RecyclerView$i$b v3 = StaggeredGridLayoutManager.a(arg3, arg4, arg5, arg6);
        this.b(v3.a);
        this.a(v3.b);
        this.a(v3.c);
        this.l = new ao();
        this.N();
    }

    private void N() {
        this.b = au.a(((i)this), this.j);
        this.c = au.a(((i)this), 1 - this.j);
    }

    private void O() {
        int v0;
        if(this.j == 1 || !this.j()) {
            boolean v0_1 = this.d;
        }
        else {
            v0 = this.d ^ 1;
        }

        this.e = ((boolean)v0);
    }

    private void P() {
        int v4_2;
        int v5_1;
        if(this.c.h() == 1073741824) {
            return;
        }

        int v1 = this.x();
        int v2 = 0;
        int v0 = 0;
        float v3 = 0f;
        while(v0 < v1) {
            View v4 = this.i(v0);
            float v5 = ((float)this.c.e(v4));
            if(v5 < v3) {
            }
            else {
                if(v4.getLayoutParams().a()) {
                    v5 = v5 * 1f / (((float)this.i));
                }

                v3 = Math.max(v3, v5);
            }

            ++v0;
        }

        v0 = this.k;
        int v3_1 = Math.round(v3 * (((float)this.i)));
        if(this.c.h() == -2147483648) {
            v3_1 = Math.min(v3_1, this.c.f());
        }

        this.f(v3_1);
        if(this.k == v0) {
            return;
        }

        while(v2 < v1) {
            View v3_2 = this.i(v2);
            ViewGroup$LayoutParams v4_1 = v3_2.getLayoutParams();
            if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v4_1).b) {
            }
            else {
                if(!this.j() || this.j != 1) {
                    v5_1 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v4_1).a.e * this.k;
                    v4_2 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v4_1).a.e * v0;
                    if(this.j == 1) {
                    }
                    else {
                        goto label_83;
                    }
                }
                else {
                    v5_1 = -(this.i - 1 - ((android.support.v7.widget.StaggeredGridLayoutManager$b)v4_1).a.e) * this.k;
                    v4_2 = -(this.i - 1 - ((android.support.v7.widget.StaggeredGridLayoutManager$b)v4_1).a.e) * v0;
                }

                v3_2.offsetLeftAndRight(v5_1 - v4_2);
                goto label_85;
            label_83:
                v3_2.offsetTopAndBottom(v5_1 - v4_2);
            }

        label_85:
            ++v2;
        }
    }

    public void a(int arg3) {
        this.a(null);
        if(arg3 != this.i) {
            this.i();
            this.i = arg3;
            this.m = new BitSet(this.i);
            this.a = new c[this.i];
            for(arg3 = 0; arg3 < this.i; ++arg3) {
                this.a[arg3] = new c(this, arg3);
            }

            this.q();
        }
    }

    public void a(boolean arg2) {
        this.a(null);
        if(this.A != null && this.A.h != arg2) {
            this.A.h = arg2;
        }

        this.d = arg2;
        this.q();
    }

    private int a(p arg17, ao arg18, t arg19) {
        View v1_3;
        StaggeredGridLayoutManager v0_2;
        int v5_1;
        FullSpanItem v5;
        int v4;
        c v1_1;
        int v3;
        int v1;
        int v2;
        int v11;
        int v0;
        StaggeredGridLayoutManager v6 = this;
        p v7 = arg17;
        ao v8 = arg18;
        int v9 = 0;
        v6.m.set(0, v6.i, true);
        if(!v6.l.i) {
            v0 = v8.e == 1 ? v8.g + v8.b : v8.f - v8.b;
            v11 = v0;
        }
        else if(v8.e == 1) {
            v11 = 2147483647;
        }
        else {
            v11 = -2147483648;
        }

        v6.a(v8.e, v11);
        v0 = v6.e ? v6.b.d() : v6.b.c();
        int v12 = v0;
        v0 = 0;
        while(true) {
            v2 = -1;
            if(arg18.a(arg19)) {
                if(!v6.l.i && (v6.m.isEmpty())) {
                    break;
                }

                View v13 = v8.a(v7);
                ViewGroup$LayoutParams v14 = v13.getLayoutParams();
                v0 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).f();
                v1 = v6.h.c(v0);
                v3 = v1 == v2 ? 1 : 0;
                if(v3 != 0) {
                    v1_1 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b ? v6.a[v9] : v6.a(v8);
                    v6.h.a(v0, v1_1);
                }
                else {
                    v1_1 = v6.a[v1];
                }

                c v15 = v1_1;
                ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).a = v15;
                if(v8.e == 1) {
                    v6.b(v13);
                }
                else {
                    v6.b(v13, v9);
                }

                v6.a(v13, ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14), ((boolean)v9));
                if(v8.e == 1) {
                    v1 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b ? v6.r(v12) : v15.b(v12);
                    v4 = v6.b.e(v13) + v1;
                    if(v3 != 0 && (((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b)) {
                        v5 = v6.n(v1);
                        v5.b = v2;
                        v5.a = v0;
                        v6.h.a(v5);
                    }

                    v5_1 = v4;
                    v4 = v1;
                }
                else {
                    v1 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b ? v6.q(v12) : v15.a(v12);
                    v4 = v1 - v6.b.e(v13);
                    if(v3 != 0 && (((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b)) {
                        v5 = v6.o(v1);
                        v5.b = 1;
                        v5.a = v0;
                        v6.h.a(v5);
                    }

                    v5_1 = v1;
                }

                if((((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b) && v8.d == v2) {
                    if(v3 == 0) {
                        boolean v1_2 = v8.e == 1 ? this.m() : this.n();
                        v1 = (((int)v1_2)) ^ 1;
                        if(v1 == 0) {
                            goto label_139;
                        }

                        FullSpanItem v0_1 = v6.h.f(v0);
                        if(v0_1 == null) {
                            goto label_124;
                        }

                        v0_1.d = true;
                    }

                label_124:
                    v6.E = true;
                }

            label_139:
                v6.a(v13, ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14), v8);
                if(!this.j() || v6.j != 1) {
                    v0 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b ? v6.c.c() : v15.e * v6.k + v6.c.c();
                    v3 = v0;
                    v9 = v6.c.e(v13) + v0;
                }
                else {
                    v0 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b ? v6.c.d() : v6.c.d() - (v6.i - 1 - v15.e) * v6.k;
                    v9 = v0;
                    v3 = v0 - v6.c.e(v13);
                }

                if(v6.j == 1) {
                    v0_2 = this;
                    v1_3 = v13;
                    v2 = v3;
                    v3 = v4;
                    v4 = v9;
                }
                else {
                    v0_2 = this;
                    v1_3 = v13;
                    v2 = v4;
                    v4 = v5_1;
                    v5_1 = v9;
                }

                v0_2.a(v1_3, v2, v3, v4, v5_1);
                if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b) {
                    v6.a(v6.l.e, v11);
                }
                else {
                    v6.a(v15, v6.l.e, v11);
                }

                v6.a(v7, v6.l);
                if((v6.l.h) && (v13.hasFocusable())) {
                    if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v14).b) {
                        v6.m.clear();
                    }
                    else {
                        v6.m.set(v15.e, false);
                    }
                }

                v0 = 1;
                v9 = 0;
                continue;
            }

            break;
        }

        v3 = 0;
        if(v0 == 0) {
            v6.a(v7, v6.l);
        }

        v1 = v6.l.e == v2 ? v6.b.c() - v6.q(v6.b.c()) : v6.r(v6.b.d()) - v6.b.d();
        if(v1 > 0) {
            v3 = Math.min(v8.b, v1);
        }

        return v3;
    }

    private void a(int arg3, int arg4) {
        int v0;
        for(v0 = 0; v0 < this.i; ++v0) {
            if(this.a[v0].a.isEmpty()) {
            }
            else {
                this.a(this.a[v0], arg3, arg4);
            }
        }
    }

    private c a(ao arg8) {
        int v6;
        c v5;
        int v2;
        int v8;
        int v3;
        int v0;
        int v1 = -1;
        if(this.t(arg8.e)) {
            v0 = this.i - 1;
            v3 = -1;
        }
        else {
            v0 = 0;
            v1 = this.i;
            v3 = 1;
        }

        c v4 = null;
        if(arg8.e == 1) {
            v8 = 2147483647;
            v2 = this.b.c();
            while(v0 != v1) {
                v5 = this.a[v0];
                v6 = v5.b(v2);
                if(v6 < v8) {
                    v4 = v5;
                    v8 = v6;
                }

                v0 += v3;
            }

            return v4;
        }

        v8 = -2147483648;
        v2 = this.b.d();
        while(v0 != v1) {
            v5 = this.a[v0];
            v6 = v5.a(v2);
            if(v6 > v8) {
                v4 = v5;
                v8 = v6;
            }

            v0 += v3;
        }

        return v4;
    }

    private void a(View arg7, android.support.v7.widget.StaggeredGridLayoutManager$b arg8, boolean arg9) {
        int v8;
        int v0;
        if(arg8.b) {
            if(this.j == 1) {
                v0 = this.B;
                goto label_6;
            }
            else {
                this.a(arg7, StaggeredGridLayoutManager.a(this.A(), this.y(), this.C() + this.E(), arg8.width, true), this.B, arg9);
            }
        }
        else if(this.j == 1) {
            v0 = StaggeredGridLayoutManager.a(this.k, this.y(), 0, arg8.width, false);
        label_6:
            v8 = StaggeredGridLayoutManager.a(this.B(), this.z(), this.D() + this.F(), arg8.height, true);
            goto label_13;
        }
        else {
            v0 = StaggeredGridLayoutManager.a(this.A(), this.y(), this.C() + this.E(), arg8.width, true);
            v8 = StaggeredGridLayoutManager.a(this.k, this.z(), 0, arg8.height, false);
        label_13:
            this.a(arg7, v0, v8, arg9);
        }
    }

    private void a(View arg2, android.support.v7.widget.StaggeredGridLayoutManager$b arg3, ao arg4) {
        if(arg4.e == 1) {
            if(arg3.b) {
                this.p(arg2);
            }
            else {
                arg3.a.b(arg2);
            }
        }
        else if(arg3.b) {
            this.q(arg2);
        }
        else {
            arg3.a.a(arg2);
        }
    }

    private void a(c arg4, int arg5, int arg6) {
        int v0 = arg4.i();
        if(arg5 == -1) {
            if(arg4.b() + v0 <= arg6) {
                goto label_11;
            }
        }
        else if(arg4.d() - v0 >= arg6) {
        label_11:
            this.m.set(arg4.e, false);
        }
    }

    private void a(p arg3, ao arg4) {
        int v4;
        int v0;
        if(arg4.a) {
            if(arg4.i) {
            }
            else {
                int v1 = -1;
                if(arg4.b == 0) {
                    if(arg4.e != v1) {
                        goto label_13;
                    }

                    goto label_10;
                }
                else if(arg4.e == v1) {
                    v0 = arg4.f - this.p(arg4.f);
                    if(v0 < 0) {
                    label_10:
                        v4 = arg4.g;
                        goto label_11;
                    }
                    else {
                        v4 = arg4.g - Math.min(v0, arg4.b);
                    label_11:
                        this.b(arg3, v4);
                        return;
                    }
                }
                else {
                    v0 = this.s(arg4.g) - arg4.g;
                    if(v0 < 0) {
                    label_13:
                        v4 = arg4.f;
                    }
                    else {
                        v4 = Math.min(v0, arg4.b) + arg4.f;
                    }
                }

                this.a(arg3, v4);
            }
        }
    }

    private void a(p arg6, int arg7) {
        while(this.x() > 0) {
            int v0 = 0;
            View v1 = this.i(0);
            if(this.b.b(v1) > arg7) {
                return;
            }

            if(this.b.c(v1) > arg7) {
                return;
            }

            ViewGroup$LayoutParams v2 = v1.getLayoutParams();
            if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v2).b) {
                int v2_1 = 0;
                while(v2_1 < this.i) {
                    if(this.a[v2_1].a.size() == 1) {
                        return;
                    }
                    else {
                        ++v2_1;
                        continue;
                    }

                    break;
                }

                while(v0 < this.i) {
                    this.a[v0].h();
                    ++v0;
                }
            }
            else {
                if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v2).a.a.size() == 1) {
                    return;
                }

                ((android.support.v7.widget.StaggeredGridLayoutManager$b)v2).a.h();
            }

            this.a(v1, arg6);
        }
    }

    private void a(p arg9, t arg10, boolean arg11) {
        int v11;
        a v0 = this.D;
        int v2 = -1;
        if((this.A != null || this.f != v2) && arg10.e() == 0) {
            this.c(arg9);
            v0.a();
            return;
        }

        int v4 = 1;
        int v1 = !v0.e || this.f != v2 || this.A != null ? 1 : 0;
        if(v1 != 0) {
            v0.a();
            if(this.A != null) {
                this.a(v0);
            }
            else {
                this.O();
                v0.c = this.e;
            }

            this.a(arg10, v0);
            v0.e = true;
        }

        if(this.A == null && this.f == v2 && (v0.c != this.o || this.j() != this.z)) {
            this.h.a();
            v0.d = true;
        }

        if(this.x() > 0 && (this.A == null || this.A.c < 1)) {
            if(v0.d) {
                for(v1 = 0; true; ++v1) {
                    if(v1 >= this.i) {
                        goto label_101;
                    }

                    this.a[v1].e();
                    if(v0.b != -2147483648) {
                        this.a[v1].c(v0.b);
                    }
                }
            }

            if(v1 == 0) {
                if(this.D.f == null) {
                }
                else {
                    v1 = 0;
                    while(true) {
                        if(v1 < this.i) {
                            c v5 = this.a[v1];
                            v5.e();
                            v5.c(this.D.f[v1]);
                            ++v1;
                            continue;
                        }
                        else {
                            goto label_101;
                        }
                    }
                }
            }

            for(v1 = 0; v1 < this.i; ++v1) {
                this.a[v1].a(this.e, v0.b);
            }

            this.D.a(this.a);
        }

    label_101:
        this.a(arg9);
        this.l.a = false;
        this.E = false;
        this.f(this.c.f());
        this.b(v0.a, arg10);
        if(v0.c) {
            this.m(v2);
            this.a(arg9, this.l, arg10);
            this.m(1);
        }
        else {
            this.m(1);
            this.a(arg9, this.l, arg10);
            this.m(v2);
        }

        this.l.c = v0.a + this.l.d;
        this.a(arg9, this.l, arg10);
        this.P();
        if(this.x() > 0) {
            if(this.e) {
                this.b(arg9, arg10, true);
                this.c(arg9, arg10, false);
            }
            else {
                this.c(arg9, arg10, true);
                this.b(arg9, arg10, false);
            }
        }

        if(!arg11 || (arg10.a())) {
        label_160:
            v4 = 0;
        }
        else {
            if(this.n == 0 || this.x() <= 0) {
            label_153:
                v11 = 0;
            }
            else {
                if(!this.E && this.h() == null) {
                    goto label_153;
                }

                v11 = 1;
            }

            if(v11 == 0) {
                goto label_160;
            }

            this.a(this.H);
            if(!this.g()) {
                goto label_160;
            }
        }

        if(arg10.a()) {
            this.D.a();
        }

        this.o = v0.c;
        this.z = this.j();
        if(v4 != 0) {
            this.D.a();
            this.a(arg9, arg10, false);
        }
    }

    private void a(a arg4) {
        boolean v0_1;
        if(this.A.c > 0) {
            if(this.A.c == this.i) {
                int v0;
                for(v0 = 0; v0 < this.i; ++v0) {
                    this.a[v0].e();
                    int v1 = this.A.d[v0];
                    if(v1 != -2147483648) {
                        int v2 = this.A.i ? this.b.d() : this.b.c();
                        v1 += v2;
                    }

                    this.a[v0].c(v1);
                }
            }
            else {
                this.A.a();
                this.A.a = this.A.b;
            }
        }

        this.z = this.A.j;
        this.a(this.A.h);
        this.O();
        if(this.A.a != -1) {
            this.f = this.A.a;
            v0_1 = this.A.i;
        }
        else {
            v0_1 = this.e;
        }

        arg4.c = v0_1;
        if(this.A.e > 1) {
            this.h.a = this.A.f;
            this.h.b = this.A.g;
        }
    }

    void a(t arg2, a arg3) {
        if(this.b(arg2, arg3)) {
            return;
        }

        if(this.c(arg2, arg3)) {
            return;
        }

        arg3.b();
        arg3.a = 0;
    }

    private void a(View arg5, int arg6, int arg7, boolean arg8) {
        this.b(arg5, this.C);
        ViewGroup$LayoutParams v0 = arg5.getLayoutParams();
        arg6 = this.b(arg6, ((android.support.v7.widget.StaggeredGridLayoutManager$b)v0).leftMargin + this.C.left, ((android.support.v7.widget.StaggeredGridLayoutManager$b)v0).rightMargin + this.C.right);
        arg7 = this.b(arg7, ((android.support.v7.widget.StaggeredGridLayoutManager$b)v0).topMargin + this.C.top, ((android.support.v7.widget.StaggeredGridLayoutManager$b)v0).bottomMargin + this.C.bottom);
        arg8 = arg8 ? this.a(arg5, arg6, arg7, ((j)v0)) : this.b(arg5, arg6, arg7, ((j)v0));
        if(arg8) {
            arg5.measure(arg6, arg7);
        }
    }

    private boolean a(c arg4) {
        if(this.e) {
            if(arg4.d() < this.b.d()) {
                return arg4.c(arg4.a.get(arg4.a.size() - 1)).b ^ 1;
            }
        }
        else if(arg4.b() > this.b.c()) {
            return arg4.c(arg4.a.get(0)).b ^ 1;
        }

        return 0;
    }

    public int a(int arg1, p arg2, t arg3) {
        return this.c(arg1, arg2, arg3);
    }

    public int a(p arg2, t arg3) {
        if(this.j == 0) {
            return this.i;
        }

        return super.a(arg2, arg3);
    }

    public j a() {
        int v1 = -1;
        int v2 = -2;
        if(this.j == 0) {
            return new android.support.v7.widget.StaggeredGridLayoutManager$b(v2, v1);
        }

        return new android.support.v7.widget.StaggeredGridLayoutManager$b(v1, v2);
    }

    public j a(Context arg2, AttributeSet arg3) {
        return new android.support.v7.widget.StaggeredGridLayoutManager$b(arg2, arg3);
    }

    public j a(ViewGroup$LayoutParams arg2) {
        if((arg2 instanceof ViewGroup$MarginLayoutParams)) {
            return new android.support.v7.widget.StaggeredGridLayoutManager$b(((ViewGroup$MarginLayoutParams)arg2));
        }

        return new android.support.v7.widget.StaggeredGridLayoutManager$b(arg2);
    }

    public View a(View arg9, int arg10, p arg11, t arg12) {
        View v12;
        View v1 = null;
        if(this.x() == 0) {
            return v1;
        }

        arg9 = this.e(arg9);
        if(arg9 == null) {
            return v1;
        }

        this.O();
        arg10 = this.x(arg10);
        if(arg10 == -2147483648) {
            return v1;
        }

        ViewGroup$LayoutParams v0 = arg9.getLayoutParams();
        boolean v2 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v0).b;
        c v0_1 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v0).a;
        int v4 = arg10 == 1 ? this.o() : this.p();
        this.b(v4, arg12);
        this.m(arg10);
        this.l.c = this.l.d + v4;
        this.l.b = ((int)((((float)this.b.f())) * 0.333333f));
        this.l.h = true;
        int v6 = 0;
        this.l.a = false;
        this.a(arg11, this.l, arg12);
        this.o = this.e;
        if(!v2) {
            View v11 = v0_1.a(v4, arg10);
            if(v11 != null && v11 != arg9) {
                return v11;
            }
        }

        if(this.t(arg10)) {
            int v11_1;
            for(v11_1 = this.i - 1; v11_1 >= 0; --v11_1) {
                v12 = this.a[v11_1].a(v4, arg10);
                if(v12 != null && v12 != arg9) {
                    return v12;
                }
            }
        }
        else {
            for(v11_1 = 0; v11_1 < this.i; ++v11_1) {
                v12 = this.a[v11_1].a(v4, arg10);
                if(v12 != null && v12 != arg9) {
                    return v12;
                }
            }
        }

        v11_1 = this.d ^ 1;
        int v12_1 = arg10 == -1 ? 1 : 0;
        v11_1 = v11_1 == v12_1 ? 1 : 0;
        if(!v2) {
            v12_1 = v11_1 != 0 ? v0_1.j() : v0_1.k();
            v12 = this.c(v12_1);
            if(v12 == null) {
                goto label_93;
            }

            if(v12 == arg9) {
                goto label_93;
            }

            return v12;
        }

    label_93:
        if(this.t(arg10)) {
            for(arg10 = this.i - 1; arg10 >= 0; --arg10) {
                if(arg10 == v0_1.e) {
                }
                else {
                    v12_1 = v11_1 != 0 ? this.a[arg10].j() : this.a[arg10].k();
                    v12 = this.c(v12_1);
                    if(v12 == null) {
                        goto label_113;
                    }

                    if(v12 == arg9) {
                        goto label_113;
                    }

                    return v12;
                }

            label_113:
            }
        }
        else {
            while(v6 < this.i) {
                arg10 = v11_1 != 0 ? this.a[v6].j() : this.a[v6].k();
                View v10 = this.c(arg10);
                if(v10 != null && v10 != arg9) {
                    return v10;
                }

                ++v6;
            }
        }

        return v1;
    }

    public void a(String arg2) {
        if(this.A == null) {
            super.a(arg2);
        }
    }

    public void a(int arg5, int arg6, t arg7, android.support.v7.widget.RecyclerView$i$a arg8) {
        int v2;
        int v1;
        if(this.j == 0) {
        }
        else {
            arg5 = arg6;
        }

        if(this.x() != 0) {
            if(arg5 == 0) {
            }
            else {
                this.a(arg5, arg7);
                if(this.G == null || this.G.length < this.i) {
                    this.G = new int[this.i];
                }

                arg5 = 0;
                arg6 = 0;
                int v0 = 0;
                while(arg6 < this.i) {
                    if(this.l.d == -1) {
                        v1 = this.l.f;
                        v2 = this.a[arg6].a(this.l.f);
                    }
                    else {
                        v1 = this.a[arg6].b(this.l.g);
                        v2 = this.l.g;
                    }

                    v1 -= v2;
                    if(v1 >= 0) {
                        this.G[v0] = v1;
                        ++v0;
                    }

                    ++arg6;
                }

                Arrays.sort(this.G, 0, v0);
                while(arg5 < v0) {
                    if(!this.l.a(arg7)) {
                        return;
                    }

                    arg8.b(this.l.c, this.G[arg5]);
                    this.l.c += this.l.d;
                    ++arg5;
                }
            }
        }
    }

    void a(int arg5, t arg6) {
        int v2;
        int v1;
        if(arg5 > 0) {
            v1 = this.o();
            v2 = 1;
        }
        else {
            v1 = this.p();
            v2 = -1;
        }

        this.l.a = true;
        this.b(v1, arg6);
        this.m(v2);
        this.l.c = v1 + this.l.d;
        this.l.b = Math.abs(arg5);
    }

    public void a(Rect arg5, int arg6, int arg7) {
        int v5;
        int v0 = this.C() + this.E();
        int v1 = this.D() + this.F();
        if(this.j == 1) {
            v5 = StaggeredGridLayoutManager.a(arg7, arg5.height() + v1, this.J());
            arg6 = StaggeredGridLayoutManager.a(arg6, this.k * this.i + v0, this.I());
        }
        else {
            arg6 = StaggeredGridLayoutManager.a(arg6, arg5.width() + v0, this.I());
            v5 = StaggeredGridLayoutManager.a(arg7, this.k * this.i + v1, this.J());
        }

        this.g(arg6, v5);
    }

    public void a(Parcelable arg2) {
        if((arg2 instanceof SavedState)) {
            this.A = ((SavedState)arg2);
            this.q();
        }
    }

    public void a(p arg7, t arg8, View arg9, android.support.v4.view.a.c arg10) {
        int v3;
        int v2;
        int v1;
        int v0;
        ViewGroup$LayoutParams v7 = arg9.getLayoutParams();
        if(!(v7 instanceof android.support.v7.widget.StaggeredGridLayoutManager$b)) {
            super.a(arg9, arg10);
            return;
        }

        if(this.j == 0) {
            v0 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v7).b();
            v1 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v7).b ? this.i : 1;
            v2 = -1;
            v3 = -1;
        }
        else {
            v0 = -1;
            v1 = -1;
            v2 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v7).b();
            if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v7).b) {
                v3 = this.i;
                goto label_17;
            }

            v3 = 1;
        }

    label_17:
        arg10.b(android.support.v4.view.a.c$b.a(v0, v1, v2, v3, ((android.support.v7.widget.StaggeredGridLayoutManager$b)v7).b, false));
    }

    public void a(t arg1) {
        super.a(arg1);
        this.f = -1;
        this.g = -2147483648;
        this.A = null;
        this.D.a();
    }

    public void a(RecyclerView arg1) {
        this.h.a();
        this.q();
    }

    public void a(RecyclerView arg1, int arg2, int arg3) {
        this.c(arg2, arg3, 1);
    }

    public void a(RecyclerView arg1, int arg2, int arg3, int arg4) {
        this.c(arg2, arg3, 8);
    }

    public void a(RecyclerView arg1, int arg2, int arg3, Object arg4) {
        this.c(arg2, arg3, 4);
    }

    public void a(RecyclerView arg2, p arg3) {
        super.a(arg2, arg3);
        this.a(this.H);
        int v3;
        for(v3 = 0; v3 < this.i; ++v3) {
            this.a[v3].e();
        }

        arg2.requestLayout();
    }

    public void a(AccessibilityEvent arg3) {
        super.a(arg3);
        if(this.x() > 0) {
            View v1 = this.b(false);
            View v0 = this.c(false);
            if(v1 != null) {
                if(v0 == null) {
                }
                else {
                    int v1_1 = this.d(v1);
                    int v0_1 = this.d(v0);
                    if(v1_1 < v0_1) {
                        arg3.setFromIndex(v1_1);
                        arg3.setToIndex(v0_1);
                    }
                    else {
                        arg3.setFromIndex(v0_1);
                        arg3.setToIndex(v1_1);
                    }
                }
            }
        }
    }

    public boolean a(j arg1) {
        return arg1 instanceof android.support.v7.widget.StaggeredGridLayoutManager$b;
    }

    public void b(int arg2) {
        if(arg2 != 0) {
            if(arg2 == 1) {
            }
            else {
                throw new IllegalArgumentException("invalid orientation.");
            }
        }

        this.a(null);
        if(arg2 == this.j) {
            return;
        }

        this.j = arg2;
        au v2 = this.b;
        this.b = this.c;
        this.c = v2;
        this.q();
    }

    private void b(int arg5, t arg6) {
        int v6;
        boolean v1 = false;
        this.l.b = 0;
        this.l.c = arg5;
        if(this.u()) {
            v6 = arg6.c();
            if(v6 != -1) {
                boolean v0 = this.e;
                boolean v5 = v6 < arg5 ? true : false;
                if(v0 == v5) {
                    v6 = this.b.f();
                    arg5 = 0;
                    goto label_27;
                }

                arg5 = this.b.f();
                goto label_26;
            }
            else {
                goto label_25;
            }
        }
        else {
        label_25:
            arg5 = 0;
        label_26:
            v6 = 0;
        }

    label_27:
        if(this.t()) {
            this.l.f = this.b.c() - arg5;
            this.l.g = this.b.d() + v6;
        }
        else {
            this.l.g = this.b.e() + v6;
            this.l.f = -arg5;
        }

        this.l.h = false;
        this.l.a = true;
        ao v5_1 = this.l;
        if(this.b.h() == 0 && this.b.e() == 0) {
            v1 = true;
        }

        v5_1.i = v1;
    }

    private void b(p arg3, t arg4, boolean arg5) {
        int v1 = this.r(-2147483648);
        if(v1 == -2147483648) {
            return;
        }

        int v0 = this.b.d() - v1;
        if(v0 > 0) {
            v0 -= -this.c(-v0, arg3, arg4);
            if((arg5) && v0 > 0) {
                this.b.a(v0);
            }
        }
    }

    private void b(p arg7, int arg8) {
        int v0;
        for(v0 = this.x() - 1; v0 >= 0; --v0) {
            View v2 = this.i(v0);
            if(this.b.a(v2) < arg8) {
                return;
            }

            if(this.b.d(v2) < arg8) {
                return;
            }

            ViewGroup$LayoutParams v3 = v2.getLayoutParams();
            if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v3).b) {
                int v3_1 = 0;
                int v4 = 0;
                while(v4 < this.i) {
                    if(this.a[v4].a.size() == 1) {
                        return;
                    }
                    else {
                        ++v4;
                        continue;
                    }

                    break;
                }

                while(v3_1 < this.i) {
                    this.a[v3_1].g();
                    ++v3_1;
                }
            }
            else {
                if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v3).a.a.size() == 1) {
                    return;
                }

                ((android.support.v7.widget.StaggeredGridLayoutManager$b)v3).a.g();
            }

            this.a(v2, arg7);
        }
    }

    private int b(int arg3, int arg4, int arg5) {
        if(arg4 == 0 && arg5 == 0) {
            return arg3;
        }

        int v0 = View$MeasureSpec.getMode(arg3);
        if(v0 != -2147483648) {
            if(v0 == 1073741824) {
            }
            else {
                return arg3;
            }
        }

        return View$MeasureSpec.makeMeasureSpec(Math.max(0, View$MeasureSpec.getSize(arg3) - arg4 - arg5), v0);
    }

    private int b(t arg8) {
        if(this.x() == 0) {
            return 0;
        }

        return bb.a(arg8, this.b, this.b(this.F ^ 1), this.c(this.F ^ 1), this, this.F, this.e);
    }

    View b(boolean arg9) {
        int v0 = this.b.c();
        int v1 = this.b.d();
        int v2 = this.x();
        View v3 = null;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            View v5 = this.i(v4);
            int v6 = this.b.a(v5);
            if(this.b.b(v5) > v0) {
                if(v6 >= v1) {
                }
                else {
                    if(v6 < v0) {
                        if(!arg9) {
                        }
                        else {
                            if(v3 == null) {
                                v3 = v5;
                            }
                            else {
                            }

                            goto label_23;
                        }
                    }

                    return v5;
                }
            }

        label_23:
        }

        return v3;
    }

    boolean b(t arg5, a arg6) {
        int v5_1;
        boolean v1 = false;
        if(!arg5.a()) {
            int v2 = -1;
            if(this.f == v2) {
            }
            else {
                int v3 = -2147483648;
                if(this.f >= 0) {
                    if(this.f >= arg5.e()) {
                    }
                    else {
                        if(this.A == null || this.A.a == v2 || this.A.c < 1) {
                            View v5 = this.c(this.f);
                            if(v5 != null) {
                                int v1_1 = this.e ? this.o() : this.p();
                                arg6.a = v1_1;
                                if(this.g != v3) {
                                    if(arg6.c) {
                                        v1_1 = this.b.d() - this.g;
                                        v5_1 = this.b.b(v5);
                                    }
                                    else {
                                        v1_1 = this.b.c() + this.g;
                                        v5_1 = this.b.a(v5);
                                    }

                                    arg6.b = v1_1 - v5_1;
                                    return 1;
                                }

                                if(this.b.e(v5) > this.b.f()) {
                                    v5_1 = arg6.c ? this.b.d() : this.b.c();
                                    arg6.b = v5_1;
                                    return 1;
                                }

                                v1_1 = this.b.a(v5) - this.b.c();
                                if(v1_1 < 0) {
                                    arg6.b = -v1_1;
                                    return 1;
                                }

                                v1_1 = this.b.d() - this.b.b(v5);
                                if(v1_1 < 0) {
                                    arg6.b = v1_1;
                                    return 1;
                                }

                                arg6.b = v3;
                            }
                            else {
                                arg6.a = this.f;
                                if(this.g == v3) {
                                    if(this.u(arg6.a) == 1) {
                                        v1 = true;
                                    }

                                    arg6.c = v1;
                                    arg6.b();
                                }
                                else {
                                    arg6.a(this.g);
                                }

                                arg6.d = true;
                            }
                        }
                        else {
                            arg6.b = v3;
                            arg6.a = this.f;
                        }

                        return 1;
                    }
                }

                this.f = v2;
                this.g = v3;
            }
        }

        return 0;
    }

    public int b(int arg1, p arg2, t arg3) {
        return this.c(arg1, arg2, arg3);
    }

    public int b(p arg3, t arg4) {
        if(this.j == 1) {
            return this.i;
        }

        return super.b(arg3, arg4);
    }

    public void b(RecyclerView arg1, int arg2, int arg3) {
        this.c(arg2, arg3, 2);
    }

    public boolean b() {
        boolean v0 = this.A == null ? true : false;
        return v0;
    }

    private void c(p arg3, t arg4, boolean arg5) {
        int v1 = this.q(2147483647);
        if(v1 == 2147483647) {
            return;
        }

        v1 -= this.b.c();
        if(v1 > 0) {
            v1 -= this.c(v1, arg3, arg4);
            if((arg5) && v1 > 0) {
                this.b.a(-v1);
            }
        }
    }

    View c(boolean arg8) {
        int v0 = this.b.c();
        int v1 = this.b.d();
        int v2 = this.x() - 1;
        View v3 = null;
        while(v2 >= 0) {
            View v4 = this.i(v2);
            int v5 = this.b.a(v4);
            int v6 = this.b.b(v4);
            if(v6 > v0) {
                if(v5 >= v1) {
                }
                else {
                    if(v6 > v1) {
                        if(!arg8) {
                        }
                        else {
                            if(v3 == null) {
                                v3 = v4;
                            }
                            else {
                            }

                            goto label_23;
                        }
                    }

                    return v4;
                }
            }

        label_23:
            --v2;
        }

        return v3;
    }

    int c(int arg3, p arg4, t arg5) {
        if(this.x() != 0) {
            if(arg3 == 0) {
            }
            else {
                this.a(arg3, arg5);
                int v5 = this.a(arg4, this.l, arg5);
                if(this.l.b < v5) {
                }
                else if(arg3 < 0) {
                    arg3 = -v5;
                }
                else {
                    arg3 = v5;
                }

                this.b.a(-arg3);
                this.o = this.e;
                this.l.b = 0;
                this.a(arg4, this.l);
                return arg3;
            }
        }

        return 0;
    }

    private void c(int arg6, int arg7, int arg8) {
        int v3;
        int v2;
        int v0 = this.e ? this.o() : this.p();
        int v1 = 8;
        if(arg8 != v1) {
            v2 = arg6 + arg7;
        label_15:
            v3 = v2;
            v2 = arg6;
        }
        else if(arg6 < arg7) {
            v2 = arg7 + 1;
            goto label_15;
        }
        else {
            v3 = arg6 + 1;
            v2 = arg7;
        }

        this.h.b(v2);
        if(arg8 != v1) {
            switch(arg8) {
                case 1: {
                    goto label_25;
                }
                case 2: {
                    goto label_22;
                }
            }

            goto label_33;
        label_22:
            this.h.a(arg6, arg7);
            goto label_33;
        label_25:
            this.h.b(arg6, arg7);
        }
        else {
            this.h.a(arg6, 1);
            this.h.b(arg7, 1);
        }

    label_33:
        if(v3 <= v0) {
            return;
        }

        arg6 = this.e ? this.p() : this.o();
        if(v2 <= arg6) {
            this.q();
        }
    }

    private boolean c(t arg2, a arg3) {
        int v2 = this.o ? this.w(arg2.e()) : this.v(arg2.e());
        arg3.a = v2;
        arg3.b = -2147483648;
        return 1;
    }

    public int c(t arg1) {
        return this.b(arg1);
    }

    public void c(p arg2, t arg3) {
        this.a(arg2, arg3, true);
    }

    public boolean c() {
        boolean v0 = this.n != 0 ? true : false;
        return v0;
    }

    public int d(t arg1) {
        return this.b(arg1);
    }

    public PointF d(int arg4) {
        arg4 = this.u(arg4);
        PointF v0 = new PointF();
        if(arg4 == 0) {
            return null;
        }

        if(this.j == 0) {
            v0.x = ((float)arg4);
            v0.y = 0f;
        }
        else {
            v0.x = 0f;
            v0.y = ((float)arg4);
        }

        return v0;
    }

    public Parcelable d() {
        if(this.A != null) {
            return new SavedState(this.A);
        }

        SavedState v0 = new SavedState();
        v0.h = this.d;
        v0.i = this.o;
        v0.j = this.z;
        int v2 = 0;
        if(this.h == null || this.h.a == null) {
            v0.e = 0;
        }
        else {
            v0.f = this.h.a;
            v0.e = v0.f.length;
            v0.g = this.h.b;
        }

        if(this.x() > 0) {
            int v1 = this.o ? this.o() : this.p();
            v0.a = v1;
            v0.b = this.k();
            v0.c = this.i;
            v0.d = new int[this.i];
            while(v2 < this.i) {
                int v3 = -2147483648;
                if(this.o) {
                    v1 = this.a[v2].b(v3);
                    if(v1 != v3) {
                        v3 = this.b.d();
                        goto label_57;
                    }
                }
                else {
                    v1 = this.a[v2].a(v3);
                    if(v1 != v3) {
                        v3 = this.b.c();
                    label_57:
                        v1 -= v3;
                    }
                }

                v0.d[v2] = v1;
                ++v2;
            }
        }
        else {
            v0.a = -1;
            v0.b = -1;
            v0.c = 0;
        }

        return ((Parcelable)v0);
    }

    public int e(t arg1) {
        return this.i(arg1);
    }

    public void e(int arg2) {
        if(this.A != null && this.A.a != arg2) {
            this.A.b();
        }

        this.f = arg2;
        this.g = -2147483648;
        this.q();
    }

    public boolean e() {
        boolean v0 = this.j == 0 ? true : false;
        return v0;
    }

    void f(int arg2) {
        this.k = arg2 / this.i;
        this.B = View$MeasureSpec.makeMeasureSpec(arg2, this.c.h());
    }

    public int f(t arg1) {
        return this.i(arg1);
    }

    public boolean f() {
        boolean v1 = true;
        if(this.j == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    boolean g() {
        int v2;
        int v0;
        if(this.x() != 0 && this.n != 0) {
            if(!this.s()) {
            }
            else {
                if(this.e) {
                    v0 = this.o();
                    v2 = this.p();
                }
                else {
                    v0 = this.p();
                    v2 = this.o();
                }

                if(v0 == 0 && this.h() != null) {
                    this.h.a();
                }
                else if(!this.E) {
                    return 0;
                }
                else {
                    int v4 = this.e ? -1 : 1;
                    ++v2;
                    FullSpanItem v6 = this.h.a(v0, v2, v4, true);
                    if(v6 == null) {
                        this.E = false;
                        this.h.a(v2);
                        return 0;
                    }

                    FullSpanItem v0_1 = this.h.a(v0, v6.a, v4 * -1, true);
                    if(v0_1 == null) {
                        this.h.a(v6.a);
                        goto label_21;
                    }

                    this.h.a(v0_1.a + 1);
                }

            label_21:
                this.L();
                this.q();
                return 1;
            }
        }

        return 0;
    }

    public int g(t arg1) {
        return this.j(arg1);
    }

    View h() {
        int v11;
        int v10;
        int v6;
        int v0 = this.x() - 1;
        BitSet v2 = new BitSet(this.i);
        v2.set(0, this.i, true);
        int v5 = -1;
        int v3 = this.j != 1 || !this.j() ? -1 : 1;
        if(this.e) {
            v6 = -1;
        }
        else {
            v6 = v0 + 1;
            v0 = 0;
        }

        if(v0 < v6) {
            v5 = 1;
        }

        while(v0 != v6) {
            View v7 = this.i(v0);
            ViewGroup$LayoutParams v8 = v7.getLayoutParams();
            if(v2.get(((android.support.v7.widget.StaggeredGridLayoutManager$b)v8).a.e)) {
                if(this.a(((android.support.v7.widget.StaggeredGridLayoutManager$b)v8).a)) {
                    return v7;
                }
                else {
                    v2.clear(((android.support.v7.widget.StaggeredGridLayoutManager$b)v8).a.e);
                }
            }

            if(((android.support.v7.widget.StaggeredGridLayoutManager$b)v8).b) {
            }
            else {
                int v9 = v0 + v5;
                if(v9 != v6) {
                    View v9_1 = this.i(v9);
                    if(this.e) {
                        v10 = this.b.b(v7);
                        v11 = this.b.b(v9_1);
                        if(v10 < v11) {
                            return v7;
                        }
                        else if(v10 == v11) {
                            goto label_63;
                        }
                        else {
                            goto label_65;
                        }
                    }
                    else {
                        v10 = this.b.a(v7);
                        v11 = this.b.a(v9_1);
                        if(v10 > v11) {
                            return v7;
                        }
                        else if(v10 == v11) {
                        label_63:
                            v10 = 1;
                        }
                        else {
                        label_65:
                            v10 = 0;
                        }
                    }

                    if(v10 == 0) {
                        goto label_83;
                    }

                    int v8_1 = ((android.support.v7.widget.StaggeredGridLayoutManager$b)v8).a.e - v9_1.getLayoutParams().a.e < 0 ? 1 : 0;
                    v9 = v3 < 0 ? 1 : 0;
                    if(v8_1 == v9) {
                        goto label_83;
                    }

                    return v7;
                }
            }

        label_83:
            v0 += v5;
        }

        return null;
    }

    public int h(t arg1) {
        return this.j(arg1);
    }

    private int i(t arg7) {
        if(this.x() == 0) {
            return 0;
        }

        return bb.a(arg7, this.b, this.b(this.F ^ 1), this.c(this.F ^ 1), this, this.F);
    }

    public void i() {
        this.h.a();
        this.q();
    }

    boolean j() {
        boolean v1 = true;
        if(this.v() == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private int j(t arg7) {
        if(this.x() == 0) {
            return 0;
        }

        return bb.b(arg7, this.b, this.b(this.F ^ 1), this.c(this.F ^ 1), this, this.F);
    }

    public void j(int arg3) {
        super.j(arg3);
        int v0;
        for(v0 = 0; v0 < this.i; ++v0) {
            this.a[v0].d(arg3);
        }
    }

    int k() {
        View v0 = this.e ? this.c(true) : this.b(true);
        int v0_1 = v0 == null ? -1 : this.d(v0);
        return v0_1;
    }

    public void k(int arg3) {
        super.k(arg3);
        int v0;
        for(v0 = 0; v0 < this.i; ++v0) {
            this.a[v0].d(arg3);
        }
    }

    public void l(int arg1) {
        if(arg1 == 0) {
            this.g();
        }
    }

    boolean m() {
        int v2 = -2147483648;
        int v0 = this.a[0].b(v2);
        int v4;
        for(v4 = 1; v4 < this.i; ++v4) {
            if(this.a[v4].b(v2) != v0) {
                return 0;
            }
        }

        return 1;
    }

    private void m(int arg5) {
        this.l.e = arg5;
        ao v0 = this.l;
        boolean v1 = this.e;
        int v2 = 1;
        boolean v5 = arg5 == -1 ? true : false;
        if(v1 == v5) {
        }
        else {
            v2 = -1;
        }

        v0.d = v2;
    }

    private FullSpanItem n(int arg5) {
        FullSpanItem v0 = new FullSpanItem();
        v0.c = new int[this.i];
        int v1;
        for(v1 = 0; v1 < this.i; ++v1) {
            v0.c[v1] = arg5 - this.a[v1].b(arg5);
        }

        return v0;
    }

    boolean n() {
        int v2 = -2147483648;
        int v0 = this.a[0].a(v2);
        int v4;
        for(v4 = 1; v4 < this.i; ++v4) {
            if(this.a[v4].a(v2) != v0) {
                return 0;
            }
        }

        return 1;
    }

    private FullSpanItem o(int arg5) {
        FullSpanItem v0 = new FullSpanItem();
        v0.c = new int[this.i];
        int v1;
        for(v1 = 0; v1 < this.i; ++v1) {
            v0.c[v1] = this.a[v1].a(arg5) - arg5;
        }

        return v0;
    }

    int o() {
        int v0 = this.x();
        return v0 == 0 ? 0 : this.d(this.i(v0 - 1));
    }

    private int p(int arg4) {
        int v0 = this.a[0].a(arg4);
        int v1;
        for(v1 = 1; v1 < this.i; ++v1) {
            int v2 = this.a[v1].a(arg4);
            if(v2 > v0) {
                v0 = v2;
            }
        }

        return v0;
    }

    private void p(View arg3) {
        int v0;
        for(v0 = this.i - 1; v0 >= 0; --v0) {
            this.a[v0].b(arg3);
        }
    }

    int p() {
        int v1 = 0;
        if(this.x() == 0) {
        }
        else {
            v1 = this.d(this.i(0));
        }

        return v1;
    }

    private int q(int arg4) {
        int v0 = this.a[0].a(arg4);
        int v1;
        for(v1 = 1; v1 < this.i; ++v1) {
            int v2 = this.a[v1].a(arg4);
            if(v2 < v0) {
                v0 = v2;
            }
        }

        return v0;
    }

    private void q(View arg3) {
        int v0;
        for(v0 = this.i - 1; v0 >= 0; --v0) {
            this.a[v0].a(arg3);
        }
    }

    private int r(int arg4) {
        int v0 = this.a[0].b(arg4);
        int v1;
        for(v1 = 1; v1 < this.i; ++v1) {
            int v2 = this.a[v1].b(arg4);
            if(v2 > v0) {
                v0 = v2;
            }
        }

        return v0;
    }

    private int s(int arg4) {
        int v0 = this.a[0].b(arg4);
        int v1;
        for(v1 = 1; v1 < this.i; ++v1) {
            int v2 = this.a[v1].b(arg4);
            if(v2 < v0) {
                v0 = v2;
            }
        }

        return v0;
    }

    private boolean t(int arg5) {
        boolean v5;
        int v1 = -1;
        boolean v2 = false;
        if(this.j == 0) {
            v5 = arg5 == v1 ? true : false;
            if(v5 != this.e) {
                v2 = true;
            }

            return v2;
        }

        v5 = arg5 == v1 ? true : false;
        v5 = v5 == this.e ? true : false;
        if(v5 == this.j()) {
            v2 = true;
        }

        return v2;
    }

    private int u(int arg4) {
        int v1 = -1;
        if(this.x() == 0) {
            if(this.e) {
                v1 = 1;
            }

            return v1;
        }

        boolean v4 = arg4 < this.p() ? true : false;
        if(v4 != this.e) {
        }
        else {
            v1 = 1;
        }

        return v1;
    }

    private int v(int arg5) {
        int v0 = this.x();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            int v3 = this.d(this.i(v2));
            if(v3 >= 0 && v3 < arg5) {
                return v3;
            }
        }

        return 0;
    }

    private int w(int arg3) {
        int v0;
        for(v0 = this.x() - 1; v0 >= 0; --v0) {
            int v1 = this.d(this.i(v0));
            if(v1 >= 0 && v1 < arg3) {
                return v1;
            }
        }

        return 0;
    }

    private int x(int arg5) {
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
        if(this.j == 1) {
            return v1;
        }

        if(this.j()) {
            return 1;
        }

        return v1;
    label_13:
        if(this.j == 1) {
            return 1;
        }

        if(this.j()) {
            return v1;
        }

        return 1;
    label_27:
        if(this.j == 1) {
            v2 = 1;
        }

        return v2;
    label_31:
        if(this.j == 0) {
            v2 = 1;
        }

        return v2;
    label_35:
        if(this.j == 1) {
        }
        else {
            v1 = -2147483648;
        }

        return v1;
    label_40:
        if(this.j == 0) {
        }
        else {
            v1 = -2147483648;
        }

        return v1;
    }
}

