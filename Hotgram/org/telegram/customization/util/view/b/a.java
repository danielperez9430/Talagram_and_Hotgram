package org.telegram.customization.util.view.b;

import android.os.Build$VERSION;
import android.util.SparseArray;
import android.view.View$AccessibilityDelegate;
import android.view.View;

public class a {
    private View[] a;
    private int[] b;
    private SparseArray[] c;
    private int d;
    private SparseArray e;

    public a() {
        super();
        this.a = new View[0];
        this.b = new int[0];
    }

    static View a(SparseArray arg4, int arg5) {
        int v0 = arg4.size();
        if(v0 > 0) {
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                int v2 = arg4.keyAt(v1);
                Object v3 = arg4.get(v2);
                if(v2 == arg5) {
                    arg4.remove(v2);
                    return ((View)v3);
                }
            }

            --v0;
            Object v5 = arg4.valueAt(v0);
            arg4.remove(arg4.keyAt(v0));
            return ((View)v5);
        }

        return null;
    }

    View a(int arg3, int arg4) {
        SparseArray v4;
        if(this.d == 1) {
            v4 = this.e;
        }
        else {
            if(arg4 >= 0 && arg4 < this.c.length) {
                v4 = this.c[arg4];
                goto label_4;
            }

            return null;
        }

    label_4:
        return a.a(v4, arg3);
    }

    void a() {
        View[] v0 = this.a;
        int[] v1 = this.b;
        int v2 = this.d > 1 ? 1 : 0;
        SparseArray v4 = this.e;
        int v5;
        for(v5 = v0.length - 1; v5 >= 0; --v5) {
            View v3 = v0[v5];
            if(v3 != null) {
                int v6 = v1[v5];
                View v7 = null;
                v0[v5] = v7;
                v1[v5] = -1;
                if(!this.b(v6)) {
                }
                else {
                    if(v2 != 0) {
                        v4 = this.c[v6];
                    }

                    v4.put(v5, v3);
                    if(Build$VERSION.SDK_INT < 14) {
                        goto label_30;
                    }

                    v3.setAccessibilityDelegate(((View$AccessibilityDelegate)v7));
                }
            }

        label_30:
        }

        this.b();
    }

    public void a(int arg5) {
        if(arg5 >= 1) {
            SparseArray[] v0 = new SparseArray[arg5];
            int v2;
            for(v2 = 0; v2 < arg5; ++v2) {
                v0[v2] = new SparseArray();
            }

            this.d = arg5;
            this.e = v0[0];
            this.c = v0;
            return;
        }

        throw new IllegalArgumentException("Can\'t have a viewTypeCount < 1");
    }

    void a(View arg3, int arg4, int arg5) {
        SparseArray v5 = this.d == 1 ? this.e : this.c[arg5];
        v5.put(arg4, arg3);
        if(Build$VERSION.SDK_INT >= 14) {
            arg3.setAccessibilityDelegate(null);
        }
    }

    private void b() {
        int v0 = this.a.length;
        int v1 = this.d;
        SparseArray[] v2 = this.c;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            SparseArray v5 = v2[v4];
            int v6 = v5.size();
            int v7 = v6 - v0;
            int v8 = v6 - 1;
            v6 = 0;
            while(v6 < v7) {
                v5.remove(v5.keyAt(v8));
                ++v6;
                --v8;
            }
        }
    }

    protected boolean b(int arg1) {
        boolean v1 = arg1 >= 0 ? true : false;
        return v1;
    }
}

