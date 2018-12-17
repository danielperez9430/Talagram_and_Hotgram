package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk public final class ListenerHolder {
    @KeepForSdk public final class ListenerKey {
        private final Object zzli;
        private final String zzll;

        @KeepForSdk ListenerKey(Object arg1, String arg2) {
            super();
            this.zzli = arg1;
            this.zzll = arg2;
        }

        public final boolean equals(Object arg5) {
            if(this == (((ListenerKey)arg5))) {
                return 1;
            }

            if(!(arg5 instanceof ListenerKey)) {
                return 0;
            }

            if(this.zzli == ((ListenerKey)arg5).zzli && (this.zzll.equals(((ListenerKey)arg5).zzll))) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return System.identityHashCode(this.zzli) * 31 + this.zzll.hashCode();
        }
    }

    @KeepForSdk public interface Notifier {
        @KeepForSdk void notifyListener(Object arg1);

        @KeepForSdk void onNotifyListenerFailed();
    }

    final class zza extends Handler {
        public zza(ListenerHolder arg1, Looper arg2) {
            this.zzlk = arg1;
            super(arg2);
        }

        public final void handleMessage(Message arg3) {
            boolean v1 = true;
            if(arg3.what == 1) {
            }
            else {
                v1 = false;
            }

            Preconditions.checkArgument(v1);
            this.zzlk.notifyListenerInternal(arg3.obj);
        }
    }

    private final zza zzlh;
    private volatile Object zzli;
    private final ListenerKey zzlj;

    @KeepForSdk ListenerHolder(Looper arg2, Object arg3, String arg4) {
        super();
        this.zzlh = new zza(this, arg2);
        this.zzli = Preconditions.checkNotNull(arg3, "Listener must not be null");
        this.zzlj = new ListenerKey(arg3, Preconditions.checkNotEmpty(arg4));
    }

    @KeepForSdk public final void clear() {
        this.zzli = null;
    }

    @KeepForSdk public final ListenerKey getListenerKey() {
        return this.zzlj;
    }

    @KeepForSdk public final boolean hasListener() {
        if(this.zzli != null) {
            return 1;
        }

        return 0;
    }

    @KeepForSdk public final void notifyListener(Notifier arg3) {
        Preconditions.checkNotNull(arg3, "Notifier must not be null");
        this.zzlh.sendMessage(this.zzlh.obtainMessage(1, arg3));
    }

    @KeepForSdk final void notifyListenerInternal(Notifier arg2) {
        Object v0 = this.zzli;
        if(v0 == null) {
            arg2.onNotifyListenerFailed();
            return;
        }

        try {
            arg2.notifyListener(v0);
            return;
        }
        catch(RuntimeException v0_1) {
            arg2.onNotifyListenerFailed();
            throw v0_1;
        }
    }
}

