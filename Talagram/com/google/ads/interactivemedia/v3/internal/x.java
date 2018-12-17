package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class x implements u {
    private final int[] a;

    public x() {
        super();
        this.a = new int[2];
    }

    private void a(ViewGroup arg3, JSONObject arg4, a arg5) {
        int v0;
        for(v0 = 0; v0 < arg3.getChildCount(); ++v0) {
            arg5.a(arg3.getChildAt(v0), ((u)this), arg4);
        }
    }

    public JSONObject a(View arg5) {
        int v0 = arg5.getWidth();
        int v1 = arg5.getHeight();
        arg5.getLocationOnScreen(this.a);
        return ac.a(this.a[0], this.a[1], v0, v1);
    }

    public void a(View arg2, JSONObject arg3, a arg4, boolean arg5) {
        if(!(arg2 instanceof ViewGroup)) {
            return;
        }

        if(!arg5 || Build$VERSION.SDK_INT < 21) {
            this.a(((ViewGroup)arg2), arg3, arg4);
        }
        else {
            this.b(((ViewGroup)arg2), arg3, arg4);
        }
    }

    @TargetApi(value=21) private void b(ViewGroup arg6, JSONObject arg7, a arg8) {
        HashMap v0 = new HashMap();
        int v1;
        for(v1 = 0; v1 < arg6.getChildCount(); ++v1) {
            View v2 = arg6.getChildAt(v1);
            Object v3 = v0.get(Float.valueOf(v2.getZ()));
            if(v3 == null) {
                ArrayList v3_1 = new ArrayList();
                v0.put(Float.valueOf(v2.getZ()), v3_1);
            }

            ((ArrayList)v3).add(v2);
        }

        ArrayList v6 = new ArrayList(v0.keySet());
        Collections.sort(((List)v6));
        Iterator v6_1 = v6.iterator();
    label_23:
        if(v6_1.hasNext()) {
            Iterator v1_1 = v0.get(v6_1.next()).iterator();
            while(true) {
                if(!v1_1.hasNext()) {
                    goto label_23;
                }

                arg8.a(v1_1.next(), ((u)this), arg7);
            }
        }
    }
}

