package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class w implements u {
    private final u a;

    public w(u arg1) {
        super();
        this.a = arg1;
    }

    ArrayList a() {
        View v3;
        ArrayList v0 = new ArrayList();
        p v1 = p.a();
        if(v1 != null) {
            Collection v1_1 = v1.c();
            IdentityHashMap v2 = new IdentityHashMap(v1_1.size() * 2 + 3);
            Iterator v1_2 = v1_1.iterator();
            do {
            label_11:
                if(v1_2.hasNext()) {
                    v3 = v1_2.next().g();
                    if(v3 == null) {
                        continue;
                    }

                    if(!ag.c(v3)) {
                        continue;
                    }

                    v3 = v3.getRootView();
                    if(v3 == null) {
                        continue;
                    }

                    if(v2.containsKey(v3)) {
                        continue;
                    }

                    break;
                }

                return v0;
            }
            while(true);

            v2.put(v3, v3);
            float v4 = ag.a(v3);
            int v5;
            for(v5 = v0.size(); v5 > 0; --v5) {
                if(ag.a(v0.get(v5 - 1)) <= v4) {
                    break;
                }
            }

            v0.add(v5, v3);
            goto label_11;
        }

        return v0;
    }

    public JSONObject a(View arg1) {
        return ac.a(0, 0, 0, 0);
    }

    public void a(View arg2, JSONObject arg3, a arg4, boolean arg5) {
        Iterator v2 = this.a().iterator();
        while(v2.hasNext()) {
            arg4.a(v2.next(), this.a, arg3);
        }
    }
}

