package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult$StatusListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk @KeepName public abstract class BasePendingResult extends PendingResult {
    @VisibleForTesting public class CallbackHandler extends Handler {
        public CallbackHandler(Looper arg1) {
            super(arg1);
        }

        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        public void handleMessage(Message arg4) {
            switch(arg4.what) {
                case 1: {
                    goto label_19;
                }
                case 2: {
                    goto label_15;
                }
            }

            int v4 = arg4.what;
            StringBuilder v2 = new StringBuilder(45);
            v2.append("Don\'t know how to handle message: ");
            v2.append(v4);
            Log.wtf("BasePendingResult", v2.toString(), new Exception());
            return;
        label_19:
            Object v4_1 = arg4.obj;
            Object v0 = ((Pair)v4_1).first;
            v4_1 = ((Pair)v4_1).second;
            try {
                ((ResultCallback)v0).onResult(((Result)v4_1));
                return;
            }
            catch(RuntimeException v0_1) {
                BasePendingResult.zzb(((Result)v4_1));
                throw v0_1;
            }

        label_15:
            arg4.obj.zzb(Status.RESULT_TIMEOUT);
        }

        public final void zza(ResultCallback arg2, Result arg3) {
            this.sendMessage(this.obtainMessage(1, new Pair(arg2, arg3)));
        }
    }

    final class zza {
        zza(BasePendingResult arg1, zzo arg2) {
            this(arg1);
        }

        private zza(BasePendingResult arg1) {
            this.zzfn = arg1;
            super();
        }

        protected final void finalize() {
            BasePendingResult.zzb(BasePendingResult.zza(this.zzfn));
            super.finalize();
        }
    }

    @KeepName private zza mResultGuardian;
    private Status mStatus;
    private Result zzdm;
    static final ThreadLocal zzez;
    private final Object zzfa;
    private final CallbackHandler zzfb;
    private final WeakReference zzfc;
    private final CountDownLatch zzfd;
    private final ArrayList zzfe;
    private ResultCallback zzff;
    private final AtomicReference zzfg;
    private volatile boolean zzfh;
    private boolean zzfi;
    private boolean zzfj;
    private ICancelToken zzfk;
    private volatile zzch zzfl;
    private boolean zzfm;

    static {
        BasePendingResult.zzez = new zzo();
    }

    @KeepForSdk @Deprecated protected BasePendingResult(Looper arg3) {
        super();
        this.zzfa = new Object();
        this.zzfd = new CountDownLatch(1);
        this.zzfe = new ArrayList();
        this.zzfg = new AtomicReference();
        this.zzfm = false;
        this.zzfb = new CallbackHandler(arg3);
        this.zzfc = new WeakReference(null);
    }

    @KeepForSdk protected BasePendingResult(GoogleApiClient arg3) {
        super();
        this.zzfa = new Object();
        this.zzfd = new CountDownLatch(1);
        this.zzfe = new ArrayList();
        this.zzfg = new AtomicReference();
        this.zzfm = false;
        Looper v0 = arg3 != null ? arg3.getLooper() : Looper.getMainLooper();
        this.zzfb = new CallbackHandler(v0);
        this.zzfc = new WeakReference(arg3);
    }

    @KeepForSdk @VisibleForTesting protected BasePendingResult(CallbackHandler arg3) {
        super();
        this.zzfa = new Object();
        this.zzfd = new CountDownLatch(1);
        this.zzfe = new ArrayList();
        this.zzfg = new AtomicReference();
        this.zzfm = false;
        this.zzfb = Preconditions.checkNotNull(arg3, "CallbackHandler must not be null");
        this.zzfc = new WeakReference(null);
    }

    @Deprecated BasePendingResult() {
        super();
        this.zzfa = new Object();
        this.zzfd = new CountDownLatch(1);
        this.zzfe = new ArrayList();
        this.zzfg = new AtomicReference();
        this.zzfm = false;
        this.zzfb = new CallbackHandler(Looper.getMainLooper());
        this.zzfc = new WeakReference(null);
    }

    public final void addStatusListener(StatusListener arg3) {
        boolean v0 = arg3 != null ? true : false;
        Preconditions.checkArgument(v0, "Callback cannot be null.");
        Object v0_1 = this.zzfa;
        __monitor_enter(v0_1);
        try {
            if(this.isReady()) {
                arg3.onComplete(this.mStatus);
            }
            else {
                this.zzfe.add(arg3);
            }

            __monitor_exit(v0_1);
            return;
        label_18:
            __monitor_exit(v0_1);
        }
        catch(Throwable v3) {
            goto label_18;
        }

        throw v3;
    }

    public final Result await() {
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        boolean v1 = true;
        Preconditions.checkState(this.zzfh ^ 1, "Result has already been consumed");
        if(this.zzfl == null) {
        }
        else {
            v1 = false;
        }

        Preconditions.checkState(v1, "Cannot await if then() has been called.");
        try {
            this.zzfd.await();
        }
        catch(InterruptedException ) {
            this.zzb(Status.RESULT_INTERRUPTED);
        }

        Preconditions.checkState(this.isReady(), "Result is not ready.");
        return this.get();
    }

    public final Result await(long arg4, TimeUnit arg6) {
        if(arg4 > 0) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }

        boolean v1 = true;
        Preconditions.checkState(this.zzfh ^ 1, "Result has already been consumed.");
        if(this.zzfl == null) {
        }
        else {
            v1 = false;
        }

        Preconditions.checkState(v1, "Cannot await if then() has been called.");
        try {
            if(this.zzfd.await(arg4, arg6)) {
                goto label_23;
            }

            this.zzb(Status.RESULT_TIMEOUT);
        }
        catch(InterruptedException ) {
            this.zzb(Status.RESULT_INTERRUPTED);
        }

    label_23:
        Preconditions.checkState(this.isReady(), "Result is not ready.");
        return this.get();
    }

    @KeepForSdk public void cancel() {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            if(!this.zzfi) {
                if(this.zzfh) {
                }
                else {
                    if(this.zzfk != null) {
                        try {
                            this.zzfk.cancel();
                            goto label_11;
                        }
                        catch(RemoteException ) {
                        label_11:
                            BasePendingResult.zzb(this.zzdm);
                            this.zzfi = true;
                            this.zza(this.createFailedResult(Status.RESULT_CANCELED));
                            __monitor_exit(v0);
                            return;
                        }
                    }

                    goto label_11;
                }
            }

            __monitor_exit(v0);
            return;
        label_23:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_23;
        }

        throw v1;
    }

    @KeepForSdk protected abstract Result createFailedResult(Status arg1);

    private final Result get() {
        Result v3;
        Result v1_1;
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            Preconditions.checkState(this.zzfh ^ 1, "Result has already been consumed.");
            Preconditions.checkState(this.isReady(), "Result is not ready.");
            v1_1 = this.zzdm;
            v3 = null;
            this.zzdm = v3;
            this.zzff = ((ResultCallback)v3);
            this.zzfh = true;
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_22:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_22;
            }

            throw v1;
        }

        v0 = this.zzfg.getAndSet(v3);
        if(v0 != null) {
            ((zzcn)v0).zzc(this);
        }

        return v1_1;
    }

    public boolean isCanceled() {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zzfi;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @KeepForSdk public final boolean isReady() {
        if(this.zzfd.getCount() == 0) {
            return 1;
        }

        return 0;
    }

    @KeepForSdk protected final void setCancelToken(ICancelToken arg2) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            this.zzfk = arg2;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }

    @KeepForSdk public final void setResult(Result arg4) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            if(!this.zzfj && !this.zzfi) {
                this.isReady();
                Preconditions.checkState(this.isReady() ^ 1, "Results have already been set");
                Preconditions.checkState(this.zzfh ^ 1, "Result has already been consumed");
                this.zza(arg4);
                __monitor_exit(v0);
                return;
            }

            BasePendingResult.zzb(arg4);
            __monitor_exit(v0);
            return;
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_22;
        }

        throw v4;
    }

    @KeepForSdk public final void setResultCallback(ResultCallback arg5) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        if(arg5 != null) {
            goto label_9;
        }

        arg5 = null;
        try {
            this.zzff = arg5;
            __monitor_exit(v0);
            return;
        label_9:
            boolean v2 = true;
            Preconditions.checkState(this.zzfh ^ 1, "Result has already been consumed.");
            if(this.zzfl == null) {
            }
            else {
                v2 = false;
            }

            Preconditions.checkState(v2, "Cannot set callbacks if then() has been called.");
            if(((PendingResult)this).isCanceled()) {
                __monitor_exit(v0);
                return;
            }

            if(this.isReady()) {
                this.zzfb.zza(arg5, this.get());
            }
            else {
                this.zzff = arg5;
            }

            __monitor_exit(v0);
            return;
        label_33:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_33;
        }

        throw v5;
    }

    @KeepForSdk public final void setResultCallback(ResultCallback arg5, long arg6, TimeUnit arg8) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        if(arg5 != null) {
            goto label_9;
        }

        arg5 = null;
        try {
            this.zzff = arg5;
            __monitor_exit(v0);
            return;
        label_9:
            boolean v2 = true;
            Preconditions.checkState(this.zzfh ^ 1, "Result has already been consumed.");
            if(this.zzfl == null) {
            }
            else {
                v2 = false;
            }

            Preconditions.checkState(v2, "Cannot set callbacks if then() has been called.");
            if(((PendingResult)this).isCanceled()) {
                __monitor_exit(v0);
                return;
            }

            if(this.isReady()) {
                this.zzfb.zza(arg5, this.get());
            }
            else {
                this.zzff = arg5;
                this.zzfb.sendMessageDelayed(this.zzfb.obtainMessage(2, this), arg8.toMillis(arg6));
            }

            __monitor_exit(v0);
            return;
        label_38:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_38;
        }

        throw v5;
    }

    public TransformedResult then(ResultTransform arg6) {
        Preconditions.checkState(this.zzfh ^ 1, "Result has already been consumed.");
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            boolean v3 = false;
            boolean v2 = this.zzfl == null ? true : false;
            Preconditions.checkState(v2, "Cannot call then() twice.");
            if(this.zzff == null) {
                v3 = true;
            }

            Preconditions.checkState(v3, "Cannot call then() if callbacks are set.");
            Preconditions.checkState(this.zzfi ^ 1, "Cannot call then() if result was canceled.");
            this.zzfm = true;
            this.zzfl = new zzch(this.zzfc);
            TransformedResult v6_1 = this.zzfl.then(arg6);
            if(this.isReady()) {
                this.zzfb.zza(this.zzfl, this.get());
            }
            else {
                this.zzff = this.zzfl;
            }

            __monitor_exit(v0);
            return v6_1;
        label_43:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_43;
        }

        throw v6;
    }

    public final void zza(zzcn arg2) {
        this.zzfg.set(arg2);
    }

    static Result zza(BasePendingResult arg0) {
        return arg0.zzdm;
    }

    private final void zza(Result arg5) {
        this.zzdm = arg5;
        ICancelToken v5 = null;
        this.zzfk = v5;
        this.zzfd.countDown();
        this.mStatus = this.zzdm.getStatus();
        if(this.zzfi) {
            this.zzff = ((ResultCallback)v5);
        }
        else if(this.zzff != null) {
            this.zzfb.removeMessages(2);
            this.zzfb.zza(this.zzff, this.get());
        }
        else if((this.zzdm instanceof Releasable)) {
            this.mResultGuardian = new zza(this, ((zzo)v5));
        }

        ArrayList v5_1 = this.zzfe;
        int v0 = v5_1.size();
        int v1 = 0;
        while(v1 < v0) {
            Object v2 = v5_1.get(v1);
            ++v1;
            ((StatusListener)v2).onComplete(this.mStatus);
        }

        this.zzfe.clear();
    }

    public final void zzb(Status arg3) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            if(!this.isReady()) {
                this.setResult(this.createFailedResult(arg3));
                this.zzfj = true;
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    public static void zzb(Result arg4) {
        if((arg4 instanceof Releasable)) {
            try {
                arg4.release();
                return;
            }
            catch(RuntimeException v0) {
                String v4 = String.valueOf(arg4);
                StringBuilder v3 = new StringBuilder(String.valueOf(v4).length() + 18);
                v3.append("Unable to release ");
                v3.append(v4);
                Log.w("BasePendingResult", v3.toString(), ((Throwable)v0));
            }
        }
    }

    public final Integer zzo() {
        return null;
    }

    public final boolean zzw() {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            if(this.zzfc.get() == null || !this.zzfm) {
                ((PendingResult)this).cancel();
            }

            __monitor_exit(v0);
            return ((PendingResult)this).isCanceled();
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    public final void zzx() {
        boolean v0 = (this.zzfm) || (BasePendingResult.zzez.get().booleanValue()) ? true : false;
        this.zzfm = v0;
    }
}

