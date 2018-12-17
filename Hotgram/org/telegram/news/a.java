package org.telegram.news;

import android.graphics.Bitmap;
import android.view.View;
import com.d.a.b.c;
import com.d.a.b.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class a {
    class org.telegram.news.a$a {
        String a;
        ArrayList b;

        org.telegram.news.a$a(a arg1, String arg2) {
            this.c = arg1;
            super();
            this.b = new ArrayList();
            this.a = arg2;
        }

        void a(com.d.a.b.f.a arg2) {
            this.b.add(arg2);
        }
    }

    public static a a;
    public d b;
    public Map c;

    public a() {
        super();
        this.c = new HashMap();
        this.b = d.a();
    }

    public static a a() {
        if(a.a == null) {
            a.a = new a();
        }

        return a.a;
    }

    public void a(String arg5, Bitmap arg6) {
        ArrayList v5 = this.c.remove(arg5).b;
        int v0;
        for(v0 = 0; v0 < v5.size(); ++v0) {
            v5.get(v0).onLoadingComplete("", null, arg6);
        }

        v5.clear();
    }

    public void a(String arg2, c arg3, com.d.a.b.f.a arg4) {
        Object v0 = this.c.get(arg2);
        if(v0 == null) {
            org.telegram.news.a$a v0_1 = new org.telegram.news.a$a(this, arg2);
            v0_1.a(arg4);
            this.c.put(arg2, v0_1);
            this.b.a(arg2, arg3, new com.d.a.b.f.c(arg2) {
                public void onLoadingComplete(String arg1, View arg2, Bitmap arg3) {
                    this.b.a(this.a, arg3);
                }
            });
        }
        else {
            ((org.telegram.news.a$a)v0).a(arg4);
        }
    }
}

