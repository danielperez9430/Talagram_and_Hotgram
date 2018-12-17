package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

public abstract class BaseGmsClient {
    public interface BaseConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle arg1);

        void onConnectionSuspended(int arg1);
    }

    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult arg1);
    }

    public abstract class CallbackProxy {
        private Object zzli;
        private boolean zzrv;

        public CallbackProxy(BaseGmsClient arg1, Object arg2) {
            this.zzru = arg1;
            super();
            this.zzli = arg2;
            this.zzrv = false;
        }

        public void deliverCallback() {
            Object v0_1;
            __monitor_enter(this);
            try {
                v0_1 = this.zzli;
                if(this.zzrv) {
                    String v2 = String.valueOf(this);
                    StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 47);
                    v4.append("Callback proxy ");
                    v4.append(v2);
                    v4.append(" being reused. This is not safe.");
                    Log.w("GmsClient", v4.toString());
                }

                __monitor_exit(this);
                if(v0_1 == null) {
                    goto label_25;
                }
            }
            catch(Throwable v0) {
                try {
                label_36:
                    __monitor_exit(this);
                }
                catch(Throwable v0) {
                    goto label_36;
                }

                throw v0;
            }

            try {
                this.deliverCallback(v0_1);
                goto label_26;
            }
            catch(RuntimeException v0_2) {
                this.onDeliverCallbackFailed();
                throw v0_2;
            }

        label_25:
            this.onDeliverCallbackFailed();
        label_26:
            __monitor_enter(this);
            try {
                this.zzrv = true;
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                try {
                label_33:
                    __monitor_exit(this);
                }
                catch(Throwable v0) {
                    goto label_33;
                }

                throw v0;
            }

            this.unregister();
        }

        protected abstract void deliverCallback(Object arg1);

        protected abstract void onDeliverCallbackFailed();

        public void removeListener() {
            __monitor_enter(this);
            Object v0 = null;
            try {
                this.zzli = v0;
                __monitor_exit(this);
                return;
            label_6:
                __monitor_exit(this);
            }
            catch(Throwable v0_1) {
                goto label_6;
            }

            throw v0_1;
        }

        public void unregister() {
            this.removeListener();
            ArrayList v0 = BaseGmsClient.zzf(this.zzru);
            __monitor_enter(v0);
            try {
                BaseGmsClient.zzf(this.zzru).remove(this);
                __monitor_exit(v0);
                return;
            label_10:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_10;
            }

            throw v1;
        }
    }

    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(ConnectionResult arg1);
    }

    @VisibleForTesting public final class GmsCallbacks extends Stub {
        private BaseGmsClient zzrw;
        private final int zzrx;

        public GmsCallbacks(BaseGmsClient arg1, int arg2) {
            super();
            this.zzrw = arg1;
            this.zzrx = arg2;
        }

        public final void onAccountValidationComplete(int arg2, Bundle arg3) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        public final void onPostInitComplete(int arg3, IBinder arg4, Bundle arg5) {
            Preconditions.checkNotNull(this.zzrw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzrw.onPostInitHandler(arg3, arg4, arg5, this.zzrx);
            this.zzrw = null;
        }

        public final void onPostInitCompleteWithConnectionInfo(int arg3, IBinder arg4, ConnectionInfo arg5) {
            Preconditions.checkNotNull(this.zzrw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(arg5);
            BaseGmsClient.zza(this.zzrw, arg5);
            this.onPostInitComplete(arg3, arg4, arg5.getResolutionBundle());
        }
    }

    @VisibleForTesting public final class GmsServiceConnection implements ServiceConnection {
        private final int zzrx;

        public GmsServiceConnection(BaseGmsClient arg1, int arg2) {
            this.zzru = arg1;
            super();
            this.zzrx = arg2;
        }

        public final void onServiceConnected(ComponentName arg3, IBinder arg4) {
            if(arg4 == null) {
                BaseGmsClient.zza(this.zzru, 16);
                return;
            }

            Object v3 = BaseGmsClient.zza(this.zzru);
            __monitor_enter(v3);
            try {
                BaseGmsClient.zza(this.zzru, com.google.android.gms.common.internal.IGmsServiceBroker$Stub.asInterface(arg4));
                __monitor_exit(v3);
            }
            catch(Throwable v4) {
                try {
                label_19:
                    __monitor_exit(v3);
                }
                catch(Throwable v4) {
                    goto label_19;
                }

                throw v4;
            }

            this.zzru.onPostServiceBindingHandler(0, null, this.zzrx);
        }

        public final void onServiceDisconnected(ComponentName arg5) {
            Object v5 = BaseGmsClient.zza(this.zzru);
            __monitor_enter(v5);
            try {
                BaseGmsClient.zza(this.zzru, null);
                __monitor_exit(v5);
            }
            catch(Throwable v0) {
                try {
                label_18:
                    __monitor_exit(v5);
                }
                catch(Throwable v0) {
                    goto label_18;
                }

                throw v0;
            }

            this.zzru.mHandler.sendMessage(this.zzru.mHandler.obtainMessage(6, this.zzrx, 1));
        }
    }

    public class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public LegacyClientCallbackAdapter(BaseGmsClient arg1) {
            this.zzru = arg1;
            super();
        }

        public void onReportServiceBinding(ConnectionResult arg3) {
            if(arg3.isSuccess()) {
                this.zzru.getRemoteService(null, this.zzru.getScopes());
                return;
            }

            if(BaseGmsClient.zzg(this.zzru) != null) {
                BaseGmsClient.zzg(this.zzru).onConnectionFailed(arg3);
            }
        }
    }

    public final class PostInitCallback extends zza {
        public final IBinder service;

        public PostInitCallback(BaseGmsClient arg1, int arg2, IBinder arg3, Bundle arg4) {
            this.zzru = arg1;
            super(arg1, arg2, arg4);
            this.service = arg3;
        }

        protected final void handleServiceFailure(ConnectionResult arg2) {
            if(BaseGmsClient.zzg(this.zzru) != null) {
                BaseGmsClient.zzg(this.zzru).onConnectionFailed(arg2);
            }

            this.zzru.onConnectionFailed(arg2);
        }

        protected final boolean handleServiceSuccess() {
            String v1;
            boolean v0 = false;
            try {
                v1 = this.service.getInterfaceDescriptor();
            }
            catch(RemoteException ) {
                Log.w("GmsClient", "service probably died");
                return 0;
            }

            if(!this.zzru.getServiceDescriptor().equals(v1)) {
                String v3 = this.zzru.getServiceDescriptor();
                StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 34 + String.valueOf(v1).length());
                v5.append("service descriptor mismatch: ");
                v5.append(v3);
                v5.append(" vs. ");
                v5.append(v1);
                Log.e("GmsClient", v5.toString());
                return 0;
            }

            IInterface v1_1 = this.zzru.createServiceInterface(this.service);
            if(v1_1 != null) {
                int v4 = 4;
                if(!BaseGmsClient.zza(this.zzru, 2, v4, v1_1) && !BaseGmsClient.zza(this.zzru, 3, v4, v1_1)) {
                    return v0;
                }

                BaseGmsClient.zza(this.zzru, null);
                Bundle v0_1 = this.zzru.getConnectionHint();
                if(BaseGmsClient.zze(this.zzru) != null) {
                    BaseGmsClient.zze(this.zzru).onConnected(v0_1);
                }

                v0 = true;
            }

            return v0;
        }
    }

    public final class PostServiceBindingCallback extends zza {
        public PostServiceBindingCallback(BaseGmsClient arg1, int arg2, Bundle arg3) {
            this.zzru = arg1;
            super(arg1, arg2, arg3);
        }

        protected final void handleServiceFailure(ConnectionResult arg2) {
            this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(arg2);
            this.zzru.onConnectionFailed(arg2);
        }

        protected final boolean handleServiceSuccess() {
            this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return 1;
        }
    }

    public interface SignOutCallbacks {
        void onSignOutComplete();
    }

    abstract class zza extends CallbackProxy {
        public final Bundle resolution;
        public final int statusCode;

        protected zza(BaseGmsClient arg2, int arg3, Bundle arg4) {
            this.zzru = arg2;
            super(arg2, Boolean.valueOf(true));
            this.statusCode = arg3;
            this.resolution = arg4;
        }

        protected void deliverCallback(Boolean arg4) {
            IInterface v1 = null;
            if(arg4 == null) {
                BaseGmsClient.zza(this.zzru, 1, v1);
                return;
            }

            int v4 = this.statusCode;
            if(v4 == 0) {
                if(this.handleServiceSuccess()) {
                    return;
                }

                BaseGmsClient.zza(this.zzru, 1, v1);
                this.handleServiceFailure(new ConnectionResult(8, ((PendingIntent)v1)));
            }
            else if(v4 != 10) {
                BaseGmsClient.zza(this.zzru, 1, v1);
                if(this.resolution != null) {
                    Parcelable v1_1 = this.resolution.getParcelable("pendingIntent");
                }

                this.handleServiceFailure(new ConnectionResult(this.statusCode, ((PendingIntent)v1)));
            }
            else {
                BaseGmsClient.zza(this.zzru, 1, v1);
                throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
            }
        }

        protected void deliverCallback(Object arg1) {
            this.deliverCallback(((Boolean)arg1));
        }

        protected abstract void handleServiceFailure(ConnectionResult arg1);

        protected abstract boolean handleServiceSuccess();

        protected void onDeliverCallbackFailed() {
        }
    }

    final class zzb extends Handler {
        public zzb(BaseGmsClient arg1, Looper arg2) {
            this.zzru = arg1;
            super(arg2);
        }

        public final void handleMessage(Message arg8) {
            Object v6_1;
            ConnectionResult v8;
            if(this.zzru.mDisconnectCount.get() != arg8.arg1) {
                if(zzb.zzb(arg8)) {
                    zzb.zza(arg8);
                }

                return;
            }

            int v1 = 4;
            int v3 = 5;
            if((arg8.what == 1 || arg8.what == 7 || arg8.what == v1 || arg8.what == v3) && !this.zzru.isConnecting()) {
                zzb.zza(arg8);
                return;
            }

            int v4 = 8;
            int v5 = 3;
            IInterface v6 = null;
            if(arg8.what == v1) {
                BaseGmsClient.zza(this.zzru, new ConnectionResult(arg8.arg2));
                if((BaseGmsClient.zzb(this.zzru)) && !BaseGmsClient.zzc(this.zzru)) {
                    BaseGmsClient.zza(this.zzru, v5, v6);
                    return;
                }

                v8 = BaseGmsClient.zzd(this.zzru) != null ? BaseGmsClient.zzd(this.zzru) : new ConnectionResult(v4);
                this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(v8);
                this.zzru.onConnectionFailed(v8);
                return;
            }

            if(arg8.what == v3) {
                v8 = BaseGmsClient.zzd(this.zzru) != null ? BaseGmsClient.zzd(this.zzru) : new ConnectionResult(v4);
                this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(v8);
                this.zzru.onConnectionFailed(v8);
                return;
            }

            if(arg8.what == v5) {
                if((arg8.obj instanceof PendingIntent)) {
                    v6_1 = arg8.obj;
                }

                ConnectionResult v0 = new ConnectionResult(arg8.arg2, ((PendingIntent)v6_1));
                this.zzru.mConnectionProgressReportCallbacks.onReportServiceBinding(v0);
                this.zzru.onConnectionFailed(v0);
                return;
            }

            if(arg8.what == 6) {
                BaseGmsClient.zza(this.zzru, v3, v6);
                if(BaseGmsClient.zze(this.zzru) != null) {
                    BaseGmsClient.zze(this.zzru).onConnectionSuspended(arg8.arg2);
                }

                this.zzru.onConnectionSuspended(arg8.arg2);
                BaseGmsClient.zza(this.zzru, v3, 1, v6);
                return;
            }

            if(arg8.what == 2 && !this.zzru.isConnected()) {
                zzb.zza(arg8);
                return;
            }

            if(zzb.zzb(arg8)) {
                arg8.obj.deliverCallback();
                return;
            }

            int v8_1 = arg8.what;
            StringBuilder v2 = new StringBuilder(45);
            v2.append("Don\'t know how to handle message: ");
            v2.append(v8_1);
            Log.wtf("GmsClient", v2.toString(), new Exception());
        }

        private static void zza(Message arg0) {
            Object v0 = arg0.obj;
            ((CallbackProxy)v0).onDeliverCallbackFailed();
            ((CallbackProxy)v0).unregister();
        }

        private static boolean zzb(Message arg3) {
            if(arg3.what != 2 && arg3.what != 1) {
                if(arg3.what == 7) {
                }
                else {
                    return 0;
                }
            }

            return 1;
        }
    }

    public static final int CONNECT_STATE_CONNECTED = 4;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    public static final int CONNECT_STATE_LOCAL_CONNECTING = 3;
    public static final int CONNECT_STATE_REMOTE_CONNECTING = 2;
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final String FEATURE_GOOGLE_ME = "service_googleme";
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = null;
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    @VisibleForTesting protected ConnectionProgressReportCallbacks mConnectionProgressReportCallbacks;
    private final Context mContext;
    @VisibleForTesting protected AtomicInteger mDisconnectCount;
    final Handler mHandler;
    private final Object mLock;
    private final Looper zzcn;
    private final GoogleApiAvailabilityLight zzgk;
    private static final Feature[] zzqz;
    private int zzra;
    private long zzrb;
    private long zzrc;
    private int zzrd;
    private long zzre;
    @VisibleForTesting private GmsServiceEndpoint zzrf;
    private final GmsClientSupervisor zzrg;
    private final Object zzrh;
    @GuardedBy(value="mServiceBrokerLock") private IGmsServiceBroker zzri;
    @GuardedBy(value="mLock") private IInterface zzrj;
    private final ArrayList zzrk;
    @GuardedBy(value="mLock") private GmsServiceConnection zzrl;
    @GuardedBy(value="mLock") private int zzrm;
    private final BaseConnectionCallbacks zzrn;
    private final BaseOnConnectionFailedListener zzro;
    private final int zzrp;
    private final String zzrq;
    private ConnectionResult zzrr;
    private boolean zzrs;
    private volatile ConnectionInfo zzrt;

    static {
        BaseGmsClient.zzqz = new Feature[0];
        BaseGmsClient.GOOGLE_PLUS_REQUIRED_FEATURES = new String[]{"service_esmobile", "service_googleme"};
    }

    @VisibleForTesting protected BaseGmsClient(Context arg4, Handler arg5, GmsClientSupervisor arg6, GoogleApiAvailabilityLight arg7, int arg8, BaseConnectionCallbacks arg9, BaseOnConnectionFailedListener arg10) {
        super();
        this.mLock = new Object();
        this.zzrh = new Object();
        this.zzrk = new ArrayList();
        this.zzrm = 1;
        this.zzrr = null;
        this.zzrs = false;
        this.zzrt = null;
        this.mDisconnectCount = new AtomicInteger(0);
        this.mContext = Preconditions.checkNotNull(arg4, "Context must not be null");
        this.mHandler = Preconditions.checkNotNull(arg5, "Handler must not be null");
        this.zzcn = arg5.getLooper();
        this.zzrg = Preconditions.checkNotNull(arg6, "Supervisor must not be null");
        this.zzgk = Preconditions.checkNotNull(arg7, "API availability must not be null");
        this.zzrp = arg8;
        this.zzrn = arg9;
        this.zzro = arg10;
        this.zzrq = null;
    }

    protected BaseGmsClient(Context arg10, Looper arg11, int arg12, BaseConnectionCallbacks arg13, BaseOnConnectionFailedListener arg14, String arg15) {
        this(arg10, arg11, GmsClientSupervisor.getInstance(arg10), GoogleApiAvailabilityLight.getInstance(), arg12, Preconditions.checkNotNull(arg13), Preconditions.checkNotNull(arg14), arg15);
    }

    @VisibleForTesting protected BaseGmsClient(Context arg3, Looper arg4, GmsClientSupervisor arg5, GoogleApiAvailabilityLight arg6, int arg7, BaseConnectionCallbacks arg8, BaseOnConnectionFailedListener arg9, String arg10) {
        super();
        this.mLock = new Object();
        this.zzrh = new Object();
        this.zzrk = new ArrayList();
        this.zzrm = 1;
        this.zzrr = null;
        this.zzrs = false;
        this.zzrt = null;
        this.mDisconnectCount = new AtomicInteger(0);
        this.mContext = Preconditions.checkNotNull(arg3, "Context must not be null");
        this.zzcn = Preconditions.checkNotNull(arg4, "Looper must not be null");
        this.zzrg = Preconditions.checkNotNull(arg5, "Supervisor must not be null");
        this.zzgk = Preconditions.checkNotNull(arg6, "API availability must not be null");
        this.mHandler = new zzb(this, arg4);
        this.zzrp = arg7;
        this.zzrn = arg8;
        this.zzro = arg9;
        this.zzrq = arg10;
    }

    public void checkAvailabilityAndConnect() {
        int v0 = this.zzgk.isGooglePlayServicesAvailable(this.mContext, this.getMinApkVersion());
        if(v0 != 0) {
            this.zza(1, null);
            this.triggerNotAvailable(new LegacyClientCallbackAdapter(this), v0, null);
            return;
        }

        this.connect(new LegacyClientCallbackAdapter(this));
    }

    protected final void checkConnected() {
        if(this.isConnected()) {
            return;
        }

        throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }

    public void connect(ConnectionProgressReportCallbacks arg2) {
        this.mConnectionProgressReportCallbacks = Preconditions.checkNotNull(arg2, "Connection progress callbacks cannot be null.");
        this.zza(2, null);
    }

    protected abstract IInterface createServiceInterface(IBinder arg1);

    public void disconnect() {
        this.mDisconnectCount.incrementAndGet();
        ArrayList v0 = this.zzrk;
        __monitor_enter(v0);
        try {
            int v1_1 = this.zzrk.size();
            int v2;
            for(v2 = 0; v2 < v1_1; ++v2) {
                this.zzrk.get(v2).removeListener();
            }

            this.zzrk.clear();
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_28;
        }

        Object v1_2 = this.zzrh;
        __monitor_enter(v1_2);
        IGmsServiceBroker v0_1 = null;
        try {
            this.zzri = v0_1;
            __monitor_exit(v1_2);
        }
        catch(Throwable v0_2) {
            goto label_25;
        }

        this.zza(1, ((IInterface)v0_1));
        return;
        try {
        label_25:
            __monitor_exit(v1_2);
        }
        catch(Throwable v0_2) {
            goto label_25;
        }

        throw v0_2;
        try {
        label_28:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_28;
        }

        throw v1;
    }

    @Deprecated public final void doCallbackDEPRECATED(CallbackProxy arg6) {
        ArrayList v0 = this.zzrk;
        __monitor_enter(v0);
        try {
            this.zzrk.add(arg6);
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            try {
            label_15:
                __monitor_exit(v0);
            }
            catch(Throwable v6) {
                goto label_15;
            }

            throw v6;
        }

        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, this.mDisconnectCount.get(), -1, arg6));
    }

    public void dump(String arg8, FileDescriptor arg9, PrintWriter arg10, String[] arg11) {
        StringBuilder v6;
        String v4;
        long v0_1;
        PrintWriter v11_2;
        String v11_1;
        IGmsServiceBroker v9_1;
        IInterface v0;
        Object v9 = this.mLock;
        __monitor_enter(v9);
        try {
            int v11 = this.zzrm;
            v0 = this.zzrj;
            __monitor_exit(v9);
        }
        catch(Throwable v8) {
            try {
            label_142:
                __monitor_exit(v9);
            }
            catch(Throwable v8) {
                goto label_142;
            }

            throw v8;
        }

        Object v1 = this.zzrh;
        __monitor_enter(v1);
        try {
            v9_1 = this.zzri;
            __monitor_exit(v1);
        }
        catch(Throwable v8) {
            try {
            label_139:
                __monitor_exit(v1);
            }
            catch(Throwable v8) {
                goto label_139;
            }

            throw v8;
        }

        arg10.append(((CharSequence)arg8)).append("mConnectState=");
        switch(v11) {
            case 1: {
                v11_1 = "DISCONNECTED";
                break;
            }
            case 2: {
                v11_1 = "REMOTE_CONNECTING";
                break;
            }
            case 3: {
                v11_1 = "LOCAL_CONNECTING";
                break;
            }
            case 4: {
                v11_1 = "CONNECTED";
                break;
            }
            case 5: {
                v11_1 = "DISCONNECTING";
                break;
            }
            default: {
                v11_1 = "UNKNOWN";
                break;
            }
        }

        arg10.print(v11_1);
        arg10.append(" mService=");
        if(v0 == null) {
            arg10.append("null");
        }
        else {
            arg10.append(this.getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(v0.asBinder())));
        }

        arg10.append(" mServiceBroker=");
        if(v9_1 == null) {
            arg10.println("null");
        }
        else {
            arg10.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(v9_1.asBinder())));
        }

        SimpleDateFormat v9_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        long v2 = 0;
        if(this.zzrc > v2) {
            v11_2 = arg10.append(((CharSequence)arg8)).append("lastConnectedTime=");
            v0_1 = this.zzrc;
            v4 = v9_2.format(new Date(this.zzrc));
            v6 = new StringBuilder(String.valueOf(v4).length() + 21);
            v6.append(v0_1);
            v6.append(" ");
            v6.append(v4);
            v11_2.println(v6.toString());
        }

        if(this.zzrb > v2) {
            arg10.append(((CharSequence)arg8)).append("lastSuspendedCause=");
            switch(this.zzra) {
                case 1: {
                    v11_1 = "CAUSE_SERVICE_DISCONNECTED";
                    break;
                }
                case 2: {
                    v11_1 = "CAUSE_NETWORK_LOST";
                    break;
                }
                default: {
                    v11_1 = String.valueOf(this.zzra);
                    break;
                }
            }

            arg10.append(((CharSequence)v11_1));
            v11_2 = arg10.append(" lastSuspendedTime=");
            v0_1 = this.zzrb;
            v4 = v9_2.format(new Date(this.zzrb));
            v6 = new StringBuilder(String.valueOf(v4).length() + 21);
            v6.append(v0_1);
            v6.append(" ");
            v6.append(v4);
            v11_2.println(v6.toString());
        }

        if(this.zzre > v2) {
            arg10.append(((CharSequence)arg8)).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzrd));
            PrintWriter v8_1 = arg10.append(" lastFailedTime=");
            long v10 = this.zzre;
            String v9_3 = v9_2.format(new Date(this.zzre));
            StringBuilder v1_1 = new StringBuilder(String.valueOf(v9_3).length() + 21);
            v1_1.append(v10);
            v1_1.append(" ");
            v1_1.append(v9_3);
            v8_1.println(v1_1.toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Account getAccountOrDefault() {
        if(this.getAccount() != null) {
            return this.getAccount();
        }

        return new Account("<<default account>>", "com.google");
    }

    public Feature[] getApiFeatures() {
        return BaseGmsClient.zzqz;
    }

    public final Feature[] getAvailableFeatures() {
        ConnectionInfo v0 = this.zzrt;
        if(v0 == null) {
            return null;
        }

        return v0.getAvailableFeatures();
    }

    public Bundle getConnectionHint() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public String getEndpointPackageName() {
        if((this.isConnected()) && this.zzrf != null) {
            return this.zzrf.getPackageName();
        }

        throw new RuntimeException("Failed to connect when checking package");
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @VisibleForTesting public final Handler getHandlerForTesting() {
        return this.mHandler;
    }

    protected String getLocalStartServiceAction() {
        return null;
    }

    public final Looper getLooper() {
        return this.zzcn;
    }

    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    protected final String getRealClientName() {
        if(this.zzrq == null) {
            return this.mContext.getClass().getName();
        }

        return this.zzrq;
    }

    public void getRemoteService(IAccountAccessor arg4, Set arg5) {
        Object v4_3;
        GetServiceRequest v0 = new GetServiceRequest(this.zzrp).setCallingPackage(this.mContext.getPackageName()).setExtraArgs(this.getGetServiceRequestExtraArgs());
        if(arg5 != null) {
            v0.setScopes(((Collection)arg5));
        }

        if(this.requiresSignIn()) {
            v0.setClientRequestedAccount(this.getAccountOrDefault()).setAuthenticatedAccount(arg4);
        }
        else if(this.requiresAccount()) {
            v0.setClientRequestedAccount(this.getAccount());
        }

        v0.setClientRequiredFeatures(this.getRequiredFeatures());
        v0.setClientApiFeatures(this.getApiFeatures());
        try {
            v4_3 = this.zzrh;
            __monitor_enter(v4_3);
        }
        catch(SecurityException v4) {
            goto label_54;
        }
        catch(DeadObjectException v4_1) {
            goto label_58;
        }
        catch(RuntimeException v4_2) {
            goto label_46;
        }

        try {
            if(this.zzri != null) {
                this.zzri.getService(new GmsCallbacks(this, this.mDisconnectCount.get()), v0);
            }
            else {
                Log.w("GmsClient", "mServiceBroker is null, client disconnected");
            }

            __monitor_exit(v4_3);
            return;
        label_41:
            __monitor_exit(v4_3);
        }
        catch(Throwable v5) {
            goto label_41;
        }

        try {
            throw v5;
        }
        catch(RuntimeException v4_2) {
        label_46:
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", ((Throwable)v4_2));
            this.onPostInitHandler(8, null, null, this.mDisconnectCount.get());
            return;
        }
        catch(SecurityException v4) {
        label_54:
            throw v4;
        }
        catch(DeadObjectException v4_1) {
        label_58:
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", ((Throwable)v4_1));
            this.triggerConnectionSuspended(1);
            return;
        }
    }

    public Feature[] getRequiredFeatures() {
        return BaseGmsClient.zzqz;
    }

    protected Set getScopes() {
        return Collections.EMPTY_SET;
    }

    public final IInterface getService() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzrm != 5) {
                this.checkConnected();
                boolean v1_1 = this.zzrj != null ? true : false;
                Preconditions.checkState(v1_1, "Client is connected but service is null");
                __monitor_exit(v0);
                return this.zzrj;
            }

            throw new DeadObjectException();
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_20;
        }

        throw v1;
    }

    protected int getServiceBindFlags() {
        return 129;
    }

    public IBinder getServiceBrokerBinder() {
        Object v0 = this.zzrh;
        __monitor_enter(v0);
        try {
            if(this.zzri == null) {
                __monitor_exit(v0);
                return null;
            }

            __monitor_exit(v0);
            return this.zzri.asBinder();
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    @VisibleForTesting public final IGmsServiceBroker getServiceBrokerForTesting() {
        Object v0 = this.zzrh;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zzri;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    protected abstract String getServiceDescriptor();

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    protected abstract String getStartServiceAction();

    protected String getStartServicePackage() {
        return "com.google.android.gms";
    }

    public boolean isConnected() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = this.zzrm == 4 ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    public boolean isConnecting() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = this.zzrm == 2 || this.zzrm == 3 ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_15;
        }

        throw v1;
    }

    protected void onConnectedLocked(IInterface arg3) {
        this.zzrc = System.currentTimeMillis();
    }

    protected void onConnectionFailed(ConnectionResult arg3) {
        this.zzrd = arg3.getErrorCode();
        this.zzre = System.currentTimeMillis();
    }

    protected void onConnectionSuspended(int arg3) {
        this.zzra = arg3;
        this.zzrb = System.currentTimeMillis();
    }

    protected void onPostInitHandler(int arg4, IBinder arg5, Bundle arg6, int arg7) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, arg7, -1, new PostInitCallback(this, arg4, arg5, arg6)));
    }

    protected void onPostServiceBindingHandler(int arg4, Bundle arg5, int arg6) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, arg6, -1, new PostServiceBindingCallback(this, arg4, arg5)));
    }

    void onSetConnectState(int arg1, IInterface arg2) {
    }

    public void onUserSignOut(SignOutCallbacks arg1) {
        arg1.onSignOutComplete();
    }

    public boolean providesSignIn() {
        return 0;
    }

    public boolean requiresAccount() {
        return 0;
    }

    public boolean requiresGooglePlayServices() {
        return 1;
    }

    public boolean requiresSignIn() {
        return 0;
    }

    @VisibleForTesting public void setConnectionInfoForTesting(ConnectionInfo arg1) {
        this.zzrt = arg1;
    }

    @VisibleForTesting public final void setServiceBrokerForTesting(IGmsServiceBroker arg2) {
        Object v0 = this.zzrh;
        __monitor_enter(v0);
        try {
            this.zzri = arg2;
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

    @VisibleForTesting public final void setServiceForTesting(IInterface arg2) {
        int v0 = arg2 != null ? 4 : 1;
        this.zza(v0, arg2);
    }

    public void triggerConnectionSuspended(int arg5) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.mDisconnectCount.get(), arg5));
    }

    @VisibleForTesting protected void triggerNotAvailable(ConnectionProgressReportCallbacks arg4, int arg5, PendingIntent arg6) {
        this.mConnectionProgressReportCallbacks = Preconditions.checkNotNull(arg4, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.mDisconnectCount.get(), arg5, arg6));
    }

    static ConnectionResult zza(BaseGmsClient arg0, ConnectionResult arg1) {
        arg0.zzrr = arg1;
        return arg1;
    }

    static IGmsServiceBroker zza(BaseGmsClient arg0, IGmsServiceBroker arg1) {
        arg0.zzri = arg1;
        return arg1;
    }

    static Object zza(BaseGmsClient arg0) {
        return arg0.zzrh;
    }

    private final void zza(int arg11, IInterface arg12) {
        String v12;
        int v2 = arg11 == 4 ? 1 : 0;
        int v3 = arg12 != null ? 1 : 0;
        boolean v2_1 = v2 == v3 ? true : false;
        Preconditions.checkArgument(v2_1);
        Object v2_2 = this.mLock;
        __monitor_enter(v2_2);
        try {
            this.zzrm = arg11;
            this.zzrj = arg12;
            this.onSetConnectState(arg11, arg12);
            Bundle v3_1 = null;
            switch(arg11) {
                case 1: {
                    if(this.zzrl == null) {
                        goto label_133;
                    }

                    this.zzrg.unbindService(this.getStartServiceAction(), this.getStartServicePackage(), this.getServiceBindFlags(), this.zzrl, this.getRealClientName());
                    this.zzrl = ((GmsServiceConnection)v3_1);
                    break;
                }
                case 2: 
                case 3: {
                    if(this.zzrl != null && this.zzrf != null) {
                        v12 = this.zzrf.zzcw();
                        String v4 = this.zzrf.getPackageName();
                        StringBuilder v6 = new StringBuilder(String.valueOf(v12).length() + 70 + String.valueOf(v4).length());
                        v6.append("Calling connect() while still connected, missing disconnect() for ");
                        v6.append(v12);
                        v6.append(" on ");
                        v6.append(v4);
                        Log.e("GmsClient", v6.toString());
                        this.zzrg.unbindService(this.zzrf.zzcw(), this.zzrf.getPackageName(), this.zzrf.getBindFlags(), this.zzrl, this.getRealClientName());
                        this.mDisconnectCount.incrementAndGet();
                    }

                    this.zzrl = new GmsServiceConnection(this, this.mDisconnectCount.get());
                    GmsServiceEndpoint v11_1 = this.zzrm != 3 || this.getLocalStartServiceAction() == null ? new GmsServiceEndpoint(this.getStartServicePackage(), this.getStartServiceAction(), false, this.getServiceBindFlags()) : new GmsServiceEndpoint(this.getContext().getPackageName(), this.getLocalStartServiceAction(), true, this.getServiceBindFlags());
                    this.zzrf = v11_1;
                    if(this.zzrg.bindService(this.zzrf.zzcw(), this.zzrf.getPackageName(), this.zzrf.getBindFlags(), this.zzrl, this.getRealClientName())) {
                        goto label_133;
                    }

                    v12 = this.zzrf.zzcw();
                    String v0 = this.zzrf.getPackageName();
                    StringBuilder v4_1 = new StringBuilder(String.valueOf(v12).length() + 34 + String.valueOf(v0).length());
                    v4_1.append("unable to connect to service: ");
                    v4_1.append(v12);
                    v4_1.append(" on ");
                    v4_1.append(v0);
                    Log.e("GmsClient", v4_1.toString());
                    this.onPostServiceBindingHandler(16, v3_1, this.mDisconnectCount.get());
                    break;
                }
                case 4: {
                    this.onConnectedLocked(arg12);
                    break;
                }
                default: {
                    break;
                }
            }

        label_133:
            __monitor_exit(v2_2);
            return;
        label_136:
            __monitor_exit(v2_2);
        }
        catch(Throwable v11) {
            goto label_136;
        }

        throw v11;
    }

    static void zza(BaseGmsClient arg0, int arg1) {
        arg0.zzj(16);
    }

    static void zza(BaseGmsClient arg0, int arg1, IInterface arg2) {
        arg0.zza(arg1, null);
    }

    static void zza(BaseGmsClient arg0, ConnectionInfo arg1) {
        arg0.zza(arg1);
    }

    private final void zza(ConnectionInfo arg1) {
        this.zzrt = arg1;
    }

    private final boolean zza(int arg3, int arg4, IInterface arg5) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzrm != arg3) {
                __monitor_exit(v0);
                return 0;
            }

            this.zza(arg4, arg5);
            __monitor_exit(v0);
            return 1;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    static boolean zza(BaseGmsClient arg0, int arg1, int arg2, IInterface arg3) {
        return arg0.zza(arg1, arg2, arg3);
    }

    static boolean zzb(BaseGmsClient arg0) {
        return arg0.zzcr();
    }

    static boolean zzc(BaseGmsClient arg0) {
        return arg0.zzrs;
    }

    private final boolean zzcq() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = this.zzrm == 3 ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    private final boolean zzcr() {
        if(this.zzrs) {
            return 0;
        }

        if(TextUtils.isEmpty(this.getServiceDescriptor())) {
            return 0;
        }

        if(TextUtils.isEmpty(this.getLocalStartServiceAction())) {
            return 0;
        }

        try {
            Class.forName(this.getServiceDescriptor());
            return 1;
        }
        catch(ClassNotFoundException ) {
            return 0;
        }
    }

    static ConnectionResult zzd(BaseGmsClient arg0) {
        return arg0.zzrr;
    }

    static BaseConnectionCallbacks zze(BaseGmsClient arg0) {
        return arg0.zzrn;
    }

    static ArrayList zzf(BaseGmsClient arg0) {
        return arg0.zzrk;
    }

    static BaseOnConnectionFailedListener zzg(BaseGmsClient arg0) {
        return arg0.zzro;
    }

    private final void zzj(int arg5) {
        if(this.zzcq()) {
            arg5 = 5;
            this.zzrs = true;
        }
        else {
            arg5 = 4;
        }

        this.mHandler.sendMessage(this.mHandler.obtainMessage(arg5, this.mDisconnectCount.get(), 16));
    }
}

