package org.ocpsoft.prettytime.c;

import java.io.Serializable;
import java.util.Comparator;
import org.ocpsoft.prettytime.e;

public class k implements Serializable, Comparator {
    public k() {
        super();
    }

    public int a(e arg6, e arg7) {
        if(arg6.a() < arg7.a()) {
            return -1;
        }

        if(arg6.a() > arg7.a()) {
            return 1;
        }

        return 0;
    }

    public int compare(Object arg1, Object arg2) {
        return this.a(((e)arg1), ((e)arg2));
    }
}

