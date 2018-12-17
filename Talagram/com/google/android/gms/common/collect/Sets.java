package com.google.android.gms.common.collect;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Sets {
    public Sets() {
        super();
    }

    public static Set difference(Set arg3, Set arg4) {
        Preconditions.checkNotNull(arg3);
        Preconditions.checkNotNull(arg4);
        HashSet v0 = new HashSet();
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            Object v1 = v3.next();
            if(arg4.contains(v1)) {
                continue;
            }

            ((Set)v0).add(v1);
        }

        return ((Set)v0);
    }

    public static Set union(Iterable arg2) {
        Preconditions.checkNotNull(arg2);
        HashSet v0 = new HashSet();
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            ((Set)v0).addAll(v2.next());
        }

        return ((Set)v0);
    }

    public static Set union(Set arg1, Set arg2) {
        Preconditions.checkNotNull(arg1);
        Preconditions.checkNotNull(arg2);
        HashSet v0 = new HashSet(((Collection)arg1));
        ((Set)v0).addAll(((Collection)arg2));
        return ((Set)v0);
    }
}

