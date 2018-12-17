package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
    public final class a extends c {
        public a() {
            super();
        }

        public int a(int arg1) {
            return 1;
        }

        public int a(int arg1, int arg2) {
            return arg1 % arg2;
        }
    }

    public class b extends j {
        int a;
        int b;

        public b(int arg1, int arg2) {
            super(arg1, arg2);
            this.a = -1;
            this.b = 0;
        }

        public b(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
            this.a = -1;
            this.b = 0;
        }

        public b(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
            this.a = -1;
            this.b = 0;
        }

        public b(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.a = -1;
            this.b = 0;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    public abstract class c {
        final SparseIntArray a;
        private boolean b;

        public c() {
            super();
            this.a = new SparseIntArray();
            this.b = false;
        }

        public abstract int a(int arg1);

        public void a() {
            this.a.clear();
        }

        public int a(int arg6, int arg7) {
            int v3;
            int v2;
            int v0 = this.a(arg6);
            if(v0 == arg7) {
                return 0;
            }

            if(!this.b || this.a.size() <= 0) {
            label_16:
                v2 = 0;
                v3 = 0;
                goto label_18;
            label_26:
                ++v2;
            }
            else {
                v2 = this.b(arg6);
                if(v2 >= 0) {
                    v3 = this.a.get(v2) + this.a(v2);
                    goto label_26;
                }
                else {
                    goto label_16;
                }
            }

        label_18:
            if(v2 < arg6) {
                int v4 = this.a(v2);
                v3 += v4;
                if(v3 == arg7) {
                    v3 = 0;
                    goto label_26;
                }

                if(v3 <= arg7) {
                    goto label_26;
                }

                v3 = v4;
                goto label_26;
            }

            if(v0 + v3 <= arg7) {
                return v3;
            }

            return 0;
        }

        int b(int arg3, int arg4) {
            if(!this.b) {
                return this.a(arg3, arg4);
            }

            int v0 = this.a.get(arg3, -1);
            if(v0 != -1) {
                return v0;
            }

            arg4 = this.a(arg3, arg4);
            this.a.put(arg3, arg4);
            return arg4;
        }

        int b(int arg5) {
            int v0 = this.a.size() - 1;
            int v1 = 0;
            while(v1 <= v0) {
                int v2 = v1 + v0 >>> 1;
                if(this.a.keyAt(v2) < arg5) {
                    v1 = v2 + 1;
                    continue;
                }

                v0 = v2 - 1;
            }

            --v1;
            if(v1 >= 0 && v1 < this.a.size()) {
                return this.a.keyAt(v1);
            }

            return -1;
        }

        public int c(int arg7, int arg8) {
            int v0 = this.a(arg7);
            int v2 = 0;
            int v3 = 0;
            int v4 = 0;
            while(v2 < arg7) {
                int v5 = this.a(v2);
                v3 += v5;
                if(v3 == arg8) {
                    ++v4;
                    v3 = 0;
                }
                else if(v3 > arg8) {
                    ++v4;
                    v3 = v5;
                }

                ++v2;
            }

            if(v3 + v0 > arg8) {
                ++v4;
            }

            return v4;
        }
    }

    boolean a;
    int b;
    int[] c;
    View[] d;
    final SparseIntArray e;
    final SparseIntArray f;
    c g;
    final Rect h;

    public GridLayoutManager(Context arg1, int arg2) {
        super(arg1);
        this.a = false;
        this.b = -1;
        this.e = new SparseIntArray();
        this.f = new SparseIntArray();
        this.g = new a();
        this.h = new Rect();
        this.a(arg2);
    }

    public GridLayoutManager(Context arg2, AttributeSet arg3, int arg4, int arg5) {
        super(arg2, arg3, arg4, arg5);
        this.a = false;
        this.b = -1;
        this.e = new SparseIntArray();
        this.f = new SparseIntArray();
        this.g = new a();
        this.h = new Rect();
        this.a(GridLayoutManager.a(arg2, arg3, arg4, arg5).b);
    }

    private void N() {
        this.e.clear();
        this.f.clear();
    }

    private void O() {
        int v0 = this.x();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            ViewGroup$LayoutParams v2 = this.i(v1).getLayoutParams();
            int v3 = ((b)v2).f();
            this.e.put(v3, ((b)v2).b());
            this.f.put(v3, ((b)v2).a());
        }
    }

    private void P() {
        int v1;
        int v0;
        if(this.g() == 1) {
            v0 = this.A() - this.E();
            v1 = this.C();
        }
        else {
            v0 = this.B() - this.F();
            v1 = this.D();
        }

        v0 -= v1;
        this.m(v0);
    }

    private void Q() {
        if(this.d == null || this.d.length != this.b) {
            this.d = new View[this.b];
        }
    }

    public void a(int arg4) {
        if(arg4 == this.b) {
            return;
        }

        this.a = true;
        if(arg4 >= 1) {
            this.b = arg4;
            this.g.a();
            this.q();
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Span count should be at least 1. Provided ");
        v1.append(arg4);
        throw new IllegalArgumentException(v1.toString());
    }

    private int a(p arg2, t arg3, int arg4) {
        if(!arg3.a()) {
            return this.g.c(arg4, this.b);
        }

        int v2 = arg2.b(arg4);
        if(v2 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + arg4);
            return 0;
        }

        return this.g.c(v2, this.b);
    }

    private void a(float arg2, int arg3) {
        this.m(Math.max(Math.round(arg2 * (((float)this.b))), arg3));
    }

    private void a(p arg4, t arg5, int arg6, int arg7, boolean arg8) {
        int v8;
        arg7 = -1;
        int v0 = 0;
        if(arg8) {
            arg7 = arg6;
            arg6 = 0;
            v8 = 1;
        }
        else {
            --arg6;
            v8 = -1;
        }

        while(arg6 != arg7) {
            View v1 = this.d[arg6];
            ViewGroup$LayoutParams v2 = v1.getLayoutParams();
            ((b)v2).b = this.c(arg4, arg5, this.d(v1));
            ((b)v2).a = v0;
            v0 += ((b)v2).b;
            arg6 += v8;
        }
    }

    private void a(View arg2, int arg3, int arg4, boolean arg5) {
        ViewGroup$LayoutParams v0 = arg2.getLayoutParams();
        arg5 = arg5 ? this.a(arg2, arg3, arg4, ((j)v0)) : this.b(arg2, arg3, arg4, ((j)v0));
        if(arg5) {
            arg2.measure(arg3, arg4);
        }
    }

    private void a(View arg9, int arg10, boolean arg11) {
        int v0_1;
        ViewGroup$LayoutParams v0 = arg9.getLayoutParams();
        Rect v1 = ((b)v0).d;
        int v2 = v1.top + v1.bottom + ((b)v0).topMargin + ((b)v0).bottomMargin;
        int v3 = v1.left + v1.right + ((b)v0).leftMargin + ((b)v0).rightMargin;
        int v1_1 = this.a(((b)v0).a, ((b)v0).b);
        if(this.i == 1) {
            arg10 = GridLayoutManager.a(v1_1, arg10, v3, ((b)v0).width, false);
            v0_1 = GridLayoutManager.a(this.j.f(), this.z(), v2, ((b)v0).height, true);
        }
        else {
            arg10 = GridLayoutManager.a(v1_1, arg10, v2, ((b)v0).height, false);
            int v7 = GridLayoutManager.a(this.j.f(), this.y(), v3, ((b)v0).width, true);
            v0_1 = arg10;
            arg10 = v7;
        }

        this.a(arg9, arg10, v0_1, arg11);
    }

    int a(int arg4, int arg5) {
        if(this.i == 1 && (this.h())) {
            return this.c[this.b - arg4] - this.c[this.b - arg4 - arg5];
        }

        return this.c[arg5 + arg4] - this.c[arg4];
    }

    static int[] a(int[] arg5, int arg6, int arg7) {
        int v4;
        int v0 = 1;
        if(arg5 == null || arg5.length != arg6 + 1 || arg5[arg5.length - 1] != arg7) {
            arg5 = new int[arg6 + 1];
        }

        int v1 = 0;
        arg5[v1] = v1;
        int v2 = arg7 / arg6;
        arg7 %= arg6;
        int v3 = 0;
        while(v0 <= arg6) {
            v1 += arg7;
            if(v1 <= 0 || arg6 - v1 >= arg7) {
                v4 = v2;
            }
            else {
                v4 = v2 + 1;
                v1 -= arg6;
            }

            v3 += v4;
            arg5[v0] = v3;
            ++v0;
        }

        return arg5;
    }

    public int a(int arg1, p arg2, t arg3) {
        this.P();
        this.Q();
        return super.a(arg1, arg2, arg3);
    }

    public int a(p arg3, t arg4) {
        if(this.i == 0) {
            return this.b;
        }

        if(arg4.e() < 1) {
            return 0;
        }

        return this.a(arg3, arg4, arg4.e() - 1) + 1;
    }

    public j a() {
        int v1 = -1;
        int v2 = -2;
        if(this.i == 0) {
            return new b(v2, v1);
        }

        return new b(v1, v2);
    }

    public j a(Context arg2, AttributeSet arg3) {
        return new b(arg2, arg3);
    }

    public j a(ViewGroup$LayoutParams arg2) {
        if((arg2 instanceof ViewGroup$MarginLayoutParams)) {
            return new b(((ViewGroup$MarginLayoutParams)arg2));
        }

        return new b(arg2);
    }

    View a(p arg8, t arg9, int arg10, int arg11, int arg12) {
        this.i();
        int v0 = this.j.c();
        int v1 = this.j.d();
        int v2 = arg11 > arg10 ? 1 : -1;
        View v3 = null;
        View v4 = v3;
        while(arg10 != arg11) {
            View v5 = this.i(arg10);
            int v6 = this.d(v5);
            if(v6 >= 0 && v6 < arg12) {
                if(this.b(arg8, arg9, v6) != 0) {
                }
                else if(!v5.getLayoutParams().d()) {
                    if(this.j.a(v5) < v1) {
                        if(this.j.b(v5) < v0) {
                        }
                        else {
                            return v5;
                        }
                    }

                    if(v3 != null) {
                        goto label_35;
                    }

                    v3 = v5;
                }
                else if(v4 == null) {
                    v4 = v5;
                }
            }

        label_35:
            arg10 += v2;
        }

        if(v3 != null) {
        }
        else {
            v3 = v4;
        }

        return v3;
    }

    public View a(View arg26, int arg27, p arg28, t arg29) {
        int v22;
        View v24;
        int v23;
        View v21;
        int v19;
        int v12;
        int v11;
        GridLayoutManager v0 = this;
        p v1 = arg28;
        t v2 = arg29;
        View v3 = this.e(arg26);
        View v4 = null;
        if(v3 == null) {
            return v4;
        }

        ViewGroup$LayoutParams v5 = v3.getLayoutParams();
        int v6 = ((b)v5).a;
        int v7 = ((b)v5).a + ((b)v5).b;
        if(super.a(arg26, arg27, arg28, arg29) == null) {
            return v4;
        }

        boolean v5_1 = v0.f(arg27) == 1 ? true : false;
        int v5_2 = v5_1 != v0.k ? 1 : 0;
        if(v5_2 != 0) {
            v5_2 = this.x() - 1;
            v11 = -1;
            v12 = -1;
        }
        else {
            v11 = this.x();
            v5_2 = 0;
            v12 = 1;
        }

        int v13 = v0.i != 1 || !this.h() ? 0 : 1;
        int v14 = v0.a(v1, v2, v5_2);
        View v10 = v4;
        int v8 = -1;
        int v15 = 0;
        int v17 = 0;
        int v18 = -1;
        while(v5_2 != v11) {
            int v9 = v0.a(v1, v2, v5_2);
            View v1_1 = v0.i(v5_2);
            if(v1_1 == v3) {
            }
            else {
                if(!v1_1.hasFocusable() || v9 == v14) {
                    ViewGroup$LayoutParams v9_1 = v1_1.getLayoutParams();
                    int v2_1 = ((b)v9_1).a;
                    v21 = v3;
                    v22 = v11;
                    int v3_1 = ((b)v9_1).a + ((b)v9_1).b;
                    if((v1_1.hasFocusable()) && v2_1 == v6 && v3_1 == v7) {
                        return v1_1;
                    }

                    if(!v1_1.hasFocusable() || v4 != null) {
                        if(!v1_1.hasFocusable() && v10 == null) {
                            goto label_86;
                        }

                        v11 = Math.min(v3_1, v7) - Math.max(v2_1, v6);
                        if(!v1_1.hasFocusable()) {
                            if(v4 == null) {
                                v23 = v8;
                                v24 = v10;
                                int v10_1 = 0;
                                if(!v0.a(v1_1, false, true)) {
                                    goto label_124;
                                }

                                v8 = v17;
                                if(v11 > v8) {
                                label_89:
                                    v11 = v18;
                                    goto label_90;
                                }

                                if(v11 != v8) {
                                    goto label_125;
                                }

                                v11 = v18;
                                if(v2_1 > v11) {
                                    v10_1 = 1;
                                }

                                if(v13 != v10_1) {
                                    goto label_126;
                                }

                            label_90:
                                v19 = 1;
                                goto label_127;
                            }

                        label_122:
                            v23 = v8;
                            v24 = v10;
                        label_124:
                            v8 = v17;
                        label_125:
                            v11 = v18;
                        }
                        else if(v11 > v15) {
                            goto label_86;
                        }
                        else if(v11 == v15) {
                            v11 = v2_1 > v8 ? 1 : 0;
                            if(v13 != v11) {
                                goto label_122;
                            }

                        label_86:
                            v23 = v8;
                            v24 = v10;
                            v8 = v17;
                            goto label_89;
                        }
                        else {
                            goto label_122;
                        }

                    label_126:
                        v19 = 0;
                    }
                    else {
                        goto label_86;
                    }

                label_127:
                    if(v19 != 0) {
                        if(v1_1.hasFocusable()) {
                            int v4_1 = ((b)v9_1).a;
                            v15 = Math.min(v3_1, v7) - Math.max(v2_1, v6);
                            v17 = v8;
                            v18 = v11;
                            v10 = v24;
                            v8 = v4_1;
                            v4 = v1_1;
                            goto label_154;
                        }

                        v8 = ((b)v9_1).a;
                        v10 = v1_1;
                        v17 = Math.min(v3_1, v7) - Math.max(v2_1, v6);
                        v18 = v8;
                        v8 = v23;
                        goto label_154;
                    }

                label_150:
                    v17 = v8;
                    v18 = v11;
                    v8 = v23;
                    v10 = v24;
                }
                else if(v4 != null) {
                    break;
                }
                else {
                    v21 = v3;
                    v23 = v8;
                    v24 = v10;
                    v22 = v11;
                    v8 = v17;
                    v11 = v18;
                    goto label_150;
                }

            label_154:
                v5_2 += v12;
                v3 = v21;
                v11 = v22;
                v1 = arg28;
                v2 = arg29;
                continue;
            }

            break;
        }

        v24 = v10;
        if(v4 != null) {
        }
        else {
            v4 = v24;
        }

        return v4;
    }

    public void a(Rect arg5, int arg6, int arg7) {
        int v5;
        if(this.c == null) {
            super.a(arg5, arg6, arg7);
        }

        int v0 = this.C() + this.E();
        int v1 = this.D() + this.F();
        if(this.i == 1) {
            v5 = GridLayoutManager.a(arg7, arg5.height() + v1, this.J());
            arg6 = GridLayoutManager.a(arg6, this.c[this.c.length - 1] + v0, this.I());
        }
        else {
            arg6 = GridLayoutManager.a(arg6, arg5.width() + v0, this.I());
            v5 = GridLayoutManager.a(arg7, this.c[this.c.length - 1] + v1, this.J());
        }

        this.g(arg6, v5);
    }

    void a(p arg2, t arg3, android.support.v7.widget.LinearLayoutManager$a arg4, int arg5) {
        super.a(arg2, arg3, arg4, arg5);
        this.P();
        if(arg3.e() > 0 && !arg3.a()) {
            this.b(arg2, arg3, arg4, arg5);
        }

        this.Q();
    }

    void a(p arg19, t arg20, android.support.v7.widget.LinearLayoutManager$c arg21, android.support.v7.widget.LinearLayoutManager$b arg22) {
        int v15_1;
        int v2_3;
        boolean v3_2;
        View v2_1;
        int v10;
        int v3;
        GridLayoutManager v6 = this;
        p v1 = arg19;
        t v2 = arg20;
        android.support.v7.widget.LinearLayoutManager$c v7 = arg21;
        android.support.v7.widget.LinearLayoutManager$b v8 = arg22;
        int v9 = v6.j.i();
        int v13 = v9 != 1073741824 ? 1 : 0;
        int v14 = this.x() > 0 ? v6.c[v6.b] : 0;
        if(v13 != 0) {
            this.P();
        }

        boolean v15 = v7.e == 1 ? true : false;
        int v0 = v6.b;
        if(!v15) {
            v0 = v6.b(v1, v2, v7.d) + v6.c(v1, v2, v7.d);
        }

        int v4 = 0;
        int v5 = 0;
        while(true) {
            if(v5 < v6.b && (v7.a(v2)) && v0 > 0) {
                v3 = v7.d;
                v10 = v6.c(v1, v2, v3);
                if(v10 <= v6.b) {
                    v0 -= v10;
                    if(v0 < 0) {
                    }
                    else {
                        View v3_1 = v7.a(v1);
                        if(v3_1 == null) {
                        }
                        else {
                            v4 += v10;
                            v6.d[v5] = v3_1;
                            ++v5;
                            continue;
                        }
                    }
                }
                else {
                    break;
                }
            }

            goto label_76;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Item at position ");
        v1_1.append(v3);
        v1_1.append(" requires ");
        v1_1.append(v10);
        v1_1.append(" spans but GridLayoutManager has only ");
        v1_1.append(v6.b);
        v1_1.append(" spans.");
        throw new IllegalArgumentException(v1_1.toString());
    label_76:
        if(v5 == 0) {
            v8.b = true;
            return;
        }

        float v10_1 = 0f;
        int v12 = v5;
        this.a(arg19, arg20, v5, v4, v15);
        v0 = 0;
        int v1_2 = 0;
        while(v0 < v12) {
            v2_1 = v6.d[v0];
            if(v7.k != null) {
                v3_2 = false;
                if(v15) {
                    v6.a(v2_1);
                }
                else {
                    v6.a(v2_1, 0);
                }
            }
            else if(v15) {
                v6.b(v2_1);
                v3_2 = false;
            }
            else {
                v3_2 = false;
                v6.b(v2_1, 0);
            }

            v6.b(v2_1, v6.h);
            v6.a(v2_1, v9, v3_2);
            v3 = v6.j.e(v2_1);
            if(v3 > v1_2) {
                v1_2 = v3;
            }

            float v2_2 = (((float)v6.j.f(v2_1))) * 1f / (((float)v2_1.getLayoutParams().b));
            if(v2_2 > v10_1) {
                v10_1 = v2_2;
            }

            ++v0;
        }

        if(v13 != 0) {
            v6.a(v10_1, v14);
            v0 = 0;
            v1_2 = 0;
            while(v0 < v12) {
                v2_1 = v6.d[v0];
                v6.a(v2_1, 1073741824, true);
                v2_3 = v6.j.e(v2_1);
                if(v2_3 > v1_2) {
                    v1_2 = v2_3;
                }

                ++v0;
            }
        }

        for(v0 = 0; v0 < v12; ++v0) {
            v2_1 = v6.d[v0];
            if(v6.j.e(v2_1) != v1_2) {
                ViewGroup$LayoutParams v3_3 = v2_1.getLayoutParams();
                Rect v4_1 = ((b)v3_3).d;
                v5 = v4_1.top + v4_1.bottom + ((b)v3_3).topMargin + ((b)v3_3).bottomMargin;
                v9 = v4_1.left + v4_1.right + ((b)v3_3).leftMargin + ((b)v3_3).rightMargin;
                v4 = v6.a(((b)v3_3).a, ((b)v3_3).b);
                if(v6.i == 1) {
                    v3 = GridLayoutManager.a(v4, 1073741824, v9, ((b)v3_3).width, false);
                    v4 = View$MeasureSpec.makeMeasureSpec(v1_2 - v5, 1073741824);
                }
                else {
                    v9 = View$MeasureSpec.makeMeasureSpec(v1_2 - v9, 1073741824);
                    v4 = GridLayoutManager.a(v4, 1073741824, v5, ((b)v3_3).height, false);
                    v3 = v9;
                }

                v6.a(v2_1, v3, v4, true);
            }
        }

        v10 = 0;
        v8.a = v1_2;
        v2_3 = -1;
        if(v6.i == 1) {
            if(v7.f == v2_3) {
                v1_2 = v7.b - v1_2;
                v3 = v7.b;
                v2_3 = v1_2;
            }
            else {
                v1_2 += v7.b;
                v2_3 = v7.b;
                v3 = v1_2;
            }

            v0 = 0;
            v1_2 = 0;
        }
        else {
            if(v7.f == v2_3) {
                v2_3 = 0;
                v3 = 0;
                int v17 = v7.b - v1_2;
                v1_2 = v7.b;
                v0 = v17;
                goto label_222;
            }

            v0 = v7.b;
            v1_2 += v0;
            v2_3 = 0;
            v3 = 0;
        }

    label_222:
        while(v10 < v12) {
            View v7_1 = v6.d[v10];
            ViewGroup$LayoutParams v9_1 = v7_1.getLayoutParams();
            if(v6.i != 1) {
                v2_3 = this.D() + v6.c[((b)v9_1).a];
                v3 = v6.j.f(v7_1) + v2_3;
            label_261:
                v13 = v0;
                v15_1 = v1_2;
            }
            else if(this.h()) {
                v0 = this.C() + v6.c[v6.b - ((b)v9_1).a];
                v15_1 = v0;
                v13 = v0 - v6.j.f(v7_1);
            }
            else {
                v0 = this.C() + v6.c[((b)v9_1).a];
                v1_2 = v6.j.f(v7_1) + v0;
                goto label_261;
            }

            v14 = v2_3;
            int v16 = v3;
            this.a(v7_1, v13, v14, v15_1, v16);
            if((((b)v9_1).d()) || (((b)v9_1).e())) {
                v8.c = true;
            }

            v8.d |= v7_1.hasFocusable();
            ++v10;
            v0 = v13;
            v2_3 = v14;
            v1_2 = v15_1;
            v3 = v16;
        }

        Arrays.fill(v6.d, null);
    }

    public void a(p arg8, t arg9, View arg10, android.support.v4.view.a.c arg11) {
        int v3;
        int v1;
        boolean v6;
        boolean v5;
        int v4;
        int v2;
        ViewGroup$LayoutParams v0 = arg10.getLayoutParams();
        if(!(v0 instanceof b)) {
            super.a(arg10, arg11);
            return;
        }

        int v8 = this.a(arg8, arg9, ((b)v0).f());
        if(this.i == 0) {
            int v9 = ((b)v0).a();
            v2 = ((b)v0).b();
            v4 = 1;
            v5 = this.b <= 1 || ((b)v0).b() != this.b ? false : true;
            v6 = false;
            v1 = v9;
            v3 = v8;
        }
        else {
            v2 = 1;
            v3 = ((b)v0).a();
            v4 = ((b)v0).b();
            v5 = this.b <= 1 || ((b)v0).b() != this.b ? false : true;
            v6 = false;
            v1 = v8;
        }

        arg11.b(android.support.v4.view.a.c$b.a(v1, v2, v3, v4, v5, v6));
    }

    public void a(t arg1) {
        super.a(arg1);
        this.a = false;
    }

    void a(t arg6, android.support.v7.widget.LinearLayoutManager$c arg7, android.support.v7.widget.RecyclerView$i$a arg8) {
        int v2 = this.b;
        int v0;
        for(v0 = 0; v0 < this.b; ++v0) {
            if(!arg7.a(arg6)) {
                return;
            }

            if(v2 <= 0) {
                return;
            }

            int v3 = arg7.d;
            arg8.b(v3, Math.max(0, arg7.g));
            v2 -= this.g.a(v3);
            arg7.d += arg7.e;
        }
    }

    public void a(RecyclerView arg1) {
        this.g.a();
    }

    public void a(RecyclerView arg1, int arg2, int arg3) {
        this.g.a();
    }

    public void a(RecyclerView arg1, int arg2, int arg3, int arg4) {
        this.g.a();
    }

    public void a(RecyclerView arg1, int arg2, int arg3, Object arg4) {
        this.g.a();
    }

    public void a(boolean arg2) {
        if(!arg2) {
            super.a(false);
            return;
        }

        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    public boolean a(j arg1) {
        return arg1 instanceof b;
    }

    private int b(p arg2, t arg3, int arg4) {
        if(!arg3.a()) {
            return this.g.b(arg4, this.b);
        }

        int v0 = -1;
        int v3 = this.f.get(arg4, v0);
        if(v3 != v0) {
            return v3;
        }

        int v2 = arg2.b(arg4);
        if(v2 == v0) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + arg4);
            return 0;
        }

        return this.g.b(v2, this.b);
    }

    private void b(p arg5, t arg6, android.support.v7.widget.LinearLayoutManager$a arg7, int arg8) {
        arg8 = arg8 == 1 ? 1 : 0;
        int v1 = this.b(arg5, arg6, arg7.b);
        if(arg8 != 0) {
            while(v1 > 0) {
                if(arg7.b <= 0) {
                    return;
                }

                --arg7.b;
                v1 = this.b(arg5, arg6, arg7.b);
            }
        }
        else {
            arg8 = arg6.e() - 1;
            int v0 = arg7.b;
            while(v0 < arg8) {
                int v2 = v0 + 1;
                int v3 = this.b(arg5, arg6, v2);
                if(v3 <= v1) {
                    break;
                }

                v0 = v2;
                v1 = v3;
            }

            arg7.b = v0;
        }
    }

    public int b(int arg1, p arg2, t arg3) {
        this.P();
        this.Q();
        return super.b(arg1, arg2, arg3);
    }

    public int b(p arg3, t arg4) {
        if(this.i == 1) {
            return this.b;
        }

        if(arg4.e() < 1) {
            return 0;
        }

        return this.a(arg3, arg4, arg4.e() - 1) + 1;
    }

    public void b(RecyclerView arg1, int arg2, int arg3) {
        this.g.a();
    }

    public boolean b() {
        boolean v0 = this.n != null || (this.a) ? false : true;
        return v0;
    }

    private int c(p arg2, t arg3, int arg4) {
        if(!arg3.a()) {
            return this.g.a(arg4);
        }

        int v0 = -1;
        int v3 = this.e.get(arg4, v0);
        if(v3 != v0) {
            return v3;
        }

        int v2 = arg2.b(arg4);
        if(v2 == v0) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + arg4);
            return 1;
        }

        return this.g.a(v2);
    }

    public void c(p arg2, t arg3) {
        if(arg3.a()) {
            this.O();
        }

        super.c(arg2, arg3);
        this.N();
    }

    private void m(int arg3) {
        this.c = GridLayoutManager.a(this.c, this.b, arg3);
    }
}

