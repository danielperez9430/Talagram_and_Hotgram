package com.google.ads.interactivemedia.v3.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class ll {
    static int a(Set arg3) {
        Iterator v3 = arg3.iterator();
        int v1;
        for(v1 = 0; v3.hasNext(); v1 = v1 + v2_1 ^ -1 ^ -1) {
            Object v2 = v3.next();
            int v2_1 = v2 != null ? v2.hashCode() : 0;
        }

        return v1;
    }

    static boolean a(Set arg4, Object arg5) {
        boolean v0 = true;
        if(arg4 == (((Set)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof Set)) {
            return 0;
        }

        try {
            if(arg4.size() != ((Set)arg5).size()) {
                return false;
            }
            else if(arg4.containsAll(((Collection)arg5))) {
            }
            else {
                return false;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }

        return v0;
    }
}

