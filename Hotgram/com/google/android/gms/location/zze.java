package com.google.android.gms.location;

import java.util.Comparator;

final class zze implements Comparator {
    zze() {
        super();
    }

    public final int compare(Object arg5, Object arg6) {
        int v0 = ((ActivityTransition)arg5).getActivityType();
        int v1 = ((ActivityTransition)arg6).getActivityType();
        int v3 = -1;
        if(v0 != v1) {
            if(v0 < v1) {
                return v3;
            }

            return 1;
        }

        int v5 = ((ActivityTransition)arg5).getTransitionType();
        int v6 = ((ActivityTransition)arg6).getTransitionType();
        if(v5 == v6) {
            return 0;
        }

        if(v5 < v6) {
            return v3;
        }

        return 1;
    }
}

