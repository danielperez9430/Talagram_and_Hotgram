package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

public class aq extends al {
    public aq(b arg1, HashSet arg2, JSONObject arg3, double arg4) {
        super(arg1, arg2, arg3, arg4);
    }

    protected String a(Object[] arg2) {
        if(ac.b(this.b, this.d.b())) {
            return null;
        }

        this.d.a(this.b);
        return this.b.toString();
    }

    protected void a(String arg2) {
        if(!TextUtils.isEmpty(((CharSequence)arg2))) {
            this.b(arg2);
        }

        super.a(arg2);
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

                ((g)v1).e().a(arg5, this.c);
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

