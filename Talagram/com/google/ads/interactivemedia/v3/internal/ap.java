package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

public class ap extends al {
    public ap(b arg1, HashSet arg2, JSONObject arg3, double arg4) {
        super(arg1, arg2, arg3, arg4);
    }

    protected String a(Object[] arg1) {
        return this.b.toString();
    }

    protected void a(String arg1) {
        this.b(arg1);
        super.a(arg1);
    }

    private void b(String arg5) {
        p v0 = p.a();
        if(v0 != null) {
            Iterator v0_1 = v0.b().iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                if(!this.a.contains(((g)v1).f())) {
                    continue;
                }

                ((g)v1).e().b(arg5, this.c);
            }
        }
    }

    protected Object doInBackground(Object[] arg1) {
        return this.a(arg1);
    }

    protected void onPostExecute(Object arg1) {
        this.a(((String)arg1));
    }
}

