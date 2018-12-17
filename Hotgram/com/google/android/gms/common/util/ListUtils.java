package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class ListUtils {
    private ListUtils() {
        super();
    }

    public static ArrayList copyAndRemoveElementFromListIfPresent(List arg5, Object arg6) {
        int v0 = arg5.size();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = arg5.get(v2);
            if(arg6 == null || !arg6.equals(v3)) {
                v1.add(v3);
            }
        }

        return v1;
    }

    public static ArrayList copyAndRemoveElementsFromListIfPresent(List arg3, Collection arg4) {
        Preconditions.checkNotNull(arg4);
        ArrayList v1 = new ArrayList(arg3.size());
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            Object v0 = v3.next();
            if(arg4.contains(v0)) {
                continue;
            }

            v1.add(v0);
        }

        return v1;
    }
}

