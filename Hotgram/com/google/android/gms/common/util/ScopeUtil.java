package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class ScopeUtil {
    private ScopeUtil() {
        super();
    }

    public static Set fromScopeString(Collection arg1) {
        Preconditions.checkNotNull(arg1, "scopeStrings can\'t be null.");
        return ScopeUtil.fromScopeString(arg1.toArray(new String[arg1.size()]));
    }

    public static Set fromScopeString(String[] arg5) {
        Preconditions.checkNotNull(arg5, "scopeStrings can\'t be null.");
        HashSet v0 = new HashSet(arg5.length);
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            String v3 = arg5[v2];
            if(!TextUtils.isEmpty(((CharSequence)v3))) {
                ((Set)v0).add(new Scope(v3));
            }
        }

        return ((Set)v0);
    }

    public static String[] toScopeString(Set arg1) {
        Preconditions.checkNotNull(arg1, "scopes can\'t be null.");
        return ScopeUtil.toScopeString(arg1.toArray(new Scope[arg1.size()]));
    }

    public static String[] toScopeString(Scope[] arg3) {
        Preconditions.checkNotNull(arg3, "scopes can\'t be null.");
        String[] v0 = new String[arg3.length];
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = arg3[v1].getScopeUri();
        }

        return v0;
    }
}

