package org.telegram.a;

import java.util.ArrayList;
import java.util.Iterator;

public class a {
    public ArrayList a;
    public String b;
    public ArrayList c;
    public ArrayList d;
    public ArrayList e;

    public a() {
        super();
        this.a = new ArrayList();
        this.b = "";
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
    }

    String a(String arg4) {
        Object v1;
        Iterator v0 = this.d.iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!arg4.startsWith(((String)v1)));

        return ((String)v1);
    }

    String b(String arg4) {
        Object v1;
        Iterator v0 = this.c.iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!arg4.startsWith(((String)v1)));

        return ((String)v1);
    }

    String c(String arg9) {
        String v4;
        String v2;
        String v0;
        String v1 = null;
        if(arg9.startsWith(this.b)) {
            v0 = this.b;
            v2 = arg9.substring(v0.length());
        }
        else {
            v0 = this.b(arg9);
            if(v0 != null) {
                v2 = arg9.substring(v0.length());
                String v7 = v1;
                v1 = v0;
                v0 = v7;
            }
            else {
                v2 = arg9;
                v0 = v1;
            }
        }

        Iterator v3 = this.e.iterator();
        do {
            if(!v3.hasNext()) {
                goto label_27;
            }

            v4 = v3.next().a(v2, v0, v1, true);
        }
        while(v4 == null);

        return v4;
    label_27:
        v3 = this.e.iterator();
        do {
            if(!v3.hasNext()) {
                goto label_36;
            }

            v4 = v3.next().a(v2, v0, v1, false);
        }
        while(v4 == null);

        return v4;
    label_36:
        if(v0 != null && v2.length() != 0) {
            arg9 = String.format("%s %s", v0, v2);
        }

        return arg9;
    }
}

