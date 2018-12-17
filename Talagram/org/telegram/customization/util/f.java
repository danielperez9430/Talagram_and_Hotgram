package org.telegram.customization.util;

import java.util.ArrayList;
import java.util.Iterator;

public class f {
    public static ArrayList a;
    private static f b;

    static {
        f.a = new ArrayList();
        f.b = null;
    }

    public f() {
        super();
    }

    public ArrayList a() {
        return f.a;
    }

    public void a(String arg4) {
        Iterator v0 = f.a.iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); v1 = 1) {
            if(!v0.next().contentEquals(((CharSequence)arg4))) {
                continue;
            }
        }

        if(v1 != 0) {
            f.a.remove(arg4);
        }

        f.a.add(arg4);
    }

    public static f b() {
        if(f.b == null) {
            f.b = new f();
        }

        return f.b;
    }
}

