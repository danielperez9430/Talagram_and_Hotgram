package android.support.v7.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

public abstract class b implements o {
    protected Context a;
    protected Context b;
    protected h c;
    protected LayoutInflater d;
    protected LayoutInflater e;
    protected p f;
    private a g;
    private int h;
    private int i;
    private int j;

    public b(Context arg1, int arg2, int arg3) {
        super();
        this.a = arg1;
        this.d = LayoutInflater.from(arg1);
        this.h = arg2;
        this.i = arg3;
    }

    public a a() {
        return this.g;
    }

    public p a(ViewGroup arg4) {
        if(this.f == null) {
            this.f = this.d.inflate(this.h, arg4, false);
            this.f.a(this.c);
            this.b(true);
        }

        return this.f;
    }

    public View a(j arg2, View arg3, ViewGroup arg4) {
        android.support.v7.view.menu.p$a v3;
        if((arg3 instanceof android.support.v7.view.menu.p$a)) {
        }
        else {
            v3 = this.b(arg4);
        }

        this.a(arg2, v3);
        return ((View)v3);
    }

    public abstract void a(j arg1, android.support.v7.view.menu.p$a arg2);

    public void a(int arg1) {
        this.j = arg1;
    }

    public void a(Context arg1, h arg2) {
        this.b = arg1;
        this.e = LayoutInflater.from(this.b);
        this.c = arg2;
    }

    public void a(h arg2, boolean arg3) {
        if(this.g != null) {
            this.g.a(arg2, arg3);
        }
    }

    public void a(a arg1) {
        this.g = arg1;
    }

    protected void a(View arg2, int arg3) {
        ViewParent v0 = arg2.getParent();
        if(v0 != null) {
            ((ViewGroup)v0).removeView(arg2);
        }

        this.f.addView(arg2, arg3);
    }

    public boolean a(int arg1, j arg2) {
        return 1;
    }

    public boolean a(h arg1, j arg2) {
        return 0;
    }

    public boolean a(u arg2) {
        if(this.g != null) {
            return this.g.a(((h)arg2));
        }

        return 0;
    }

    protected boolean a(ViewGroup arg1, int arg2) {
        arg1.removeViewAt(arg2);
        return 1;
    }

    public void b(boolean arg10) {
        p v10 = this.f;
        if(v10 == null) {
            return;
        }

        int v1 = 0;
        if(this.c != null) {
            this.c.k();
            ArrayList v0 = this.c.j();
            int v2 = v0.size();
            int v3 = 0;
            int v4 = 0;
            while(v3 < v2) {
                Object v5 = v0.get(v3);
                if(this.a(v4, ((j)v5))) {
                    View v6 = ((ViewGroup)v10).getChildAt(v4);
                    if((v6 instanceof android.support.v7.view.menu.p$a)) {
                        j v7 = v6.getItemData();
                    }
                    else {
                        Object v7_1 = null;
                    }

                    View v8 = this.a(((j)v5), v6, ((ViewGroup)v10));
                    if(v5 != v7) {
                        v8.setPressed(false);
                        v8.jumpDrawablesToCurrentState();
                    }

                    if(v8 != v6) {
                        this.a(v8, v4);
                    }

                    ++v4;
                }

                ++v3;
            }

            v1 = v4;
        }

        while(v1 < ((ViewGroup)v10).getChildCount()) {
            if(this.a(((ViewGroup)v10), v1)) {
                continue;
            }

            ++v1;
        }
    }

    public android.support.v7.view.menu.p$a b(ViewGroup arg4) {
        return this.d.inflate(this.i, arg4, false);
    }

    public boolean b() {
        return 0;
    }

    public boolean b(h arg1, j arg2) {
        return 0;
    }
}

