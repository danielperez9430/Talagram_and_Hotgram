package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ah {
    private final HashMap a;
    private final HashMap b;
    private final HashSet c;
    private final HashSet d;
    private final HashSet e;
    private boolean f;

    public ah() {
        super();
        this.a = new HashMap();
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new HashSet();
        this.e = new HashSet();
    }

    private void a(View arg3, g arg4) {
        ArrayList v0_1;
        Object v0 = this.b.get(arg3);
        if(v0 == null) {
            v0_1 = new ArrayList();
            this.b.put(arg3, v0_1);
        }

        v0_1.add(arg4.f());
    }

    private void a(g arg3) {
        Iterator v0 = arg3.d().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next().get();
            if(v1 == null) {
                continue;
            }

            this.a(((View)v1), arg3);
        }
    }

    public String a(View arg3) {
        if(this.a.size() == 0) {
            return null;
        }

        Object v0 = this.a.get(arg3);
        if(v0 != null) {
            this.a.remove(arg3);
        }

        return ((String)v0);
    }

    public HashSet a() {
        return this.d;
    }

    public ArrayList b(View arg3) {
        if(this.b.size() == 0) {
            return null;
        }

        Object v0 = this.b.get(arg3);
        if(v0 != null) {
            this.b.remove(arg3);
            Collections.sort(((List)v0));
        }

        return ((ArrayList)v0);
    }

    public HashSet b() {
        return this.e;
    }

    public ak c(View arg2) {
        if(this.c.contains(arg2)) {
            return ak.a;
        }

        ak v2 = this.f ? ak.b : ak.c;
        return v2;
    }

    public void c() {
        p v0 = p.a();
        if(v0 != null) {
            Iterator v0_1 = v0.c().iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                View v2 = ((g)v1).g();
                if(!((g)v1).h()) {
                    continue;
                }

                if(v2 != null && (this.d(v2))) {
                    this.d.add(((g)v1).f());
                    this.a.put(v2, ((g)v1).f());
                    this.a(((g)v1));
                    continue;
                }

                this.e.add(((g)v1).f());
            }
        }
    }

    private boolean d(View arg4) {
        if(!arg4.hasWindowFocus()) {
            return 0;
        }

        HashSet v0 = new HashSet();
        while(true) {
            if(arg4 == null) {
                goto label_17;
            }

            if(!ag.d(arg4)) {
                return 0;
            }

            v0.add(arg4);
            ViewParent v4 = ((View)v4).getParent();
            if((v4 instanceof View)) {
                continue;
            }

            arg4 = null;
        }

        return 0;
    label_17:
        this.c.addAll(((Collection)v0));
        return 1;
    }

    public void d() {
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f = false;
    }

    public void e() {
        this.f = true;
    }
}

