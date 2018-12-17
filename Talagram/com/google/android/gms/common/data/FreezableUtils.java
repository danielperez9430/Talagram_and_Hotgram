package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;

public final class FreezableUtils {
    public FreezableUtils() {
        super();
    }

    public static ArrayList freeze(ArrayList arg4) {
        ArrayList v0 = new ArrayList(arg4.size());
        int v1 = arg4.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.add(arg4.get(v2).freeze());
        }

        return v0;
    }

    public static ArrayList freeze(Freezable[] arg3) {
        ArrayList v0 = new ArrayList(arg3.length);
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0.add(arg3[v1].freeze());
        }

        return v0;
    }

    public static ArrayList freezeIterable(Iterable arg2) {
        ArrayList v0 = new ArrayList();
        Iterator v2 = arg2.iterator();
        while(v2.hasNext()) {
            v0.add(v2.next().freeze());
        }

        return v0;
    }
}

