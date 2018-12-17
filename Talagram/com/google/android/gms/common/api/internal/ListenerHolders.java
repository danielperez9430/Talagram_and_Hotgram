package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

@KeepForSdk public class ListenerHolders {
    private final Set zzlm;

    public ListenerHolders() {
        super();
        this.zzlm = Collections.newSetFromMap(new WeakHashMap());
    }

    @KeepForSdk public static ListenerHolder createListenerHolder(Object arg1, Looper arg2, String arg3) {
        Preconditions.checkNotNull(arg1, "Listener must not be null");
        Preconditions.checkNotNull(arg2, "Looper must not be null");
        Preconditions.checkNotNull(arg3, "Listener type must not be null");
        return new ListenerHolder(arg2, arg1, arg3);
    }

    @KeepForSdk public static ListenerKey createListenerKey(Object arg1, String arg2) {
        Preconditions.checkNotNull(arg1, "Listener must not be null");
        Preconditions.checkNotNull(arg2, "Listener type must not be null");
        Preconditions.checkNotEmpty(arg2, "Listener type must not be empty");
        return new ListenerKey(arg1, arg2);
    }

    public final void release() {
        Iterator v0 = this.zzlm.iterator();
        while(v0.hasNext()) {
            v0.next().clear();
        }

        this.zzlm.clear();
    }

    public final ListenerHolder zza(Object arg1, Looper arg2, String arg3) {
        ListenerHolder v1 = ListenerHolders.createListenerHolder(arg1, arg2, arg3);
        this.zzlm.add(v1);
        return v1;
    }
}

