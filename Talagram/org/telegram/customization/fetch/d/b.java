package org.telegram.customization.fetch.d;

import android.support.v4.f.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class b {
    private final String a;
    private final String b;
    private final Map c;
    private int d;

    public b(String arg2, String arg3) {
        super();
        this.c = new a();
        this.d = 600;
        this.a = arg2;
        this.b = arg3;
    }

    public String a() {
        return this.a;
    }

    public b a(int arg2) {
        this.d = 600;
        int v0 = 601;
        if(arg2 == v0) {
            this.d = v0;
        }

        return this;
    }

    public String b() {
        return this.b;
    }

    public List c() {
        ArrayList v0 = new ArrayList(this.c.size());
        Iterator v1 = this.c.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            ((List)v0).add(new org.telegram.customization.fetch.d.a(((String)v2), this.c.get(v2)));
        }

        return ((List)v0);
    }

    public int d() {
        return this.d;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        Iterator v1 = this.c().iterator();
        while(v1.hasNext()) {
            v0.append(v1.next().toString());
            v0.append(",");
        }

        if(this.c.size() > 0) {
            v0.deleteCharAt(v0.length() - 1);
        }

        return "{url:" + this.a + " ,filePath:" + this.b + ",headers:{" + v0.toString() + "},priority:" + this.d + "}";
    }
}

