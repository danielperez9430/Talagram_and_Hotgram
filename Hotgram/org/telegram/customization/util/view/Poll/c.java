package org.telegram.customization.util.view.Poll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class c {
    String a;
    String b;
    ArrayList c;
    String d;

    public c() {
        super();
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public ArrayList d() {
        Collections.sort(this.c, new Comparator() {
            public int a(b arg1, b arg2) {
                return arg1.d() - arg2.d();
            }

            public int compare(Object arg1, Object arg2) {
                return this.a(((b)arg1), ((b)arg2));
            }
        });
        return this.c;
    }
}

