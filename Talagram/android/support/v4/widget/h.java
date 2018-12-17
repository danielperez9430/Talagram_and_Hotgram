package android.support.v4.widget;

import android.support.v4.f.k$a;
import android.support.v4.f.k$b;
import android.support.v4.f.m;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class h {
    private final a a;
    private final m b;
    private final ArrayList c;
    private final HashSet d;

    public h() {
        super();
        this.a = new b(10);
        this.b = new m();
        this.c = new ArrayList();
        this.d = new HashSet();
    }

    private void a(Object arg5, ArrayList arg6, HashSet arg7) {
        if(arg6.contains(arg5)) {
            return;
        }

        if(!arg7.contains(arg5)) {
            arg7.add(arg5);
            Object v0 = this.b.get(arg5);
            if(v0 != null) {
                int v1 = 0;
                int v2 = ((ArrayList)v0).size();
                while(v1 < v2) {
                    this.a(((ArrayList)v0).get(v1), arg6, arg7);
                    ++v1;
                }
            }

            arg7.remove(arg5);
            arg6.add(arg5);
            return;
        }

        throw new RuntimeException("This graph contains cyclic dependencies");
    }

    private void a(ArrayList arg2) {
        arg2.clear();
        this.a.a(arg2);
    }

    public void a() {
        int v0 = this.b.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.b.c(v1);
            if(v2 != null) {
                this.a(((ArrayList)v2));
            }
        }

        this.b.clear();
    }

    public void a(Object arg3) {
        if(!this.b.containsKey(arg3)) {
            this.b.put(arg3, null);
        }
    }

    public void a(Object arg3, Object arg4) {
        if((this.b.containsKey(arg3)) && (this.b.containsKey(arg4))) {
            Object v0 = this.b.get(arg3);
            if(v0 == null) {
                ArrayList v0_1 = this.c();
                this.b.put(arg3, v0_1);
            }

            ((ArrayList)v0).add(arg4);
            return;
        }

        throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
    }

    public ArrayList b() {
        this.c.clear();
        this.d.clear();
        int v0 = this.b.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.a(this.b.b(v1), this.c, this.d);
        }

        return this.c;
    }

    public boolean b(Object arg2) {
        return this.b.containsKey(arg2);
    }

    private ArrayList c() {
        ArrayList v0_1;
        Object v0 = this.a.a();
        if(v0 == null) {
            v0_1 = new ArrayList();
        }

        return v0_1;
    }

    public List c(Object arg2) {
        return this.b.get(arg2);
    }

    public List d(Object arg5) {
        int v0 = this.b.size();
        ArrayList v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.b.c(v2);
            if(v3 != null && (((ArrayList)v3).contains(arg5))) {
                if(v1 == null) {
                    v1 = new ArrayList();
                }

                v1.add(this.b.b(v2));
            }
        }

        return ((List)v1);
    }

    public boolean e(Object arg5) {
        int v0 = this.b.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.b.c(v2);
            if(v3 != null && (((ArrayList)v3).contains(arg5))) {
                return 1;
            }
        }

        return 0;
    }
}

