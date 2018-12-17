package com.google.android.gms.location.places;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class zzb extends AbstractSafeParcelable {
    public zzb() {
        super();
    }

    public abstract Set getPlaceIds();

    public boolean isRestrictedToPlacesOpenNow() {
        return 0;
    }

    static Set zzb(List arg1) {
        if(arg1 != null) {
            if(arg1.isEmpty()) {
            }
            else {
                return Collections.unmodifiableSet(new HashSet(((Collection)arg1)));
            }
        }

        return Collections.emptySet();
    }

    static List zzf(Collection arg1) {
        if(arg1 != null) {
            if(arg1.isEmpty()) {
            }
            else {
                return new ArrayList(arg1);
            }
        }

        return Collections.emptyList();
    }
}

