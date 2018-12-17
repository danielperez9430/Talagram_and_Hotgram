package com.f.a.e;

import com.f.a.b.a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class c {
    public static Map a(a arg4) {
        HashMap v0 = new HashMap();
        HashMap v1 = new HashMap();
        ((Map)v1).put("number", d.b(arg4.f()));
        ((Map)v1).put("cvc", d.b(arg4.g()));
        ((Map)v1).put("exp_month", arg4.h());
        ((Map)v1).put("exp_year", arg4.i());
        ((Map)v1).put("name", d.b(arg4.j()));
        ((Map)v1).put("currency", d.b(arg4.q()));
        ((Map)v1).put("address_line1", d.b(arg4.k()));
        ((Map)v1).put("address_line2", d.b(arg4.l()));
        ((Map)v1).put("address_city", d.b(arg4.m()));
        ((Map)v1).put("address_zip", d.b(arg4.n()));
        ((Map)v1).put("address_state", d.b(arg4.o()));
        ((Map)v1).put("address_country", d.b(arg4.p()));
        Iterator v4 = new HashSet(((Map)v1).keySet()).iterator();
        while(v4.hasNext()) {
            Object v2 = v4.next();
            if(((Map)v1).get(v2) != null) {
                continue;
            }

            ((Map)v1).remove(v2);
        }

        ((Map)v0).put("card", v1);
        return ((Map)v0);
    }
}

