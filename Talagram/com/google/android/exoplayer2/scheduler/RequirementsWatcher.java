package com.google.android.exoplayer2.scheduler;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager$NetworkCallback;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest$Builder;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class RequirementsWatcher {
    class com.google.android.exoplayer2.scheduler.RequirementsWatcher$1 {
    }

    final class CapabilityValidatedCallback extends ConnectivityManager$NetworkCallback {
        CapabilityValidatedCallback(RequirementsWatcher arg1, com.google.android.exoplayer2.scheduler.RequirementsWatcher$1 arg2) {
            this(arg1);
        }

        private CapabilityValidatedCallback(RequirementsWatcher arg1) {
            RequirementsWatcher.this = arg1;
            super();
        }

        public void onAvailable(Network arg2) {
            super.onAvailable(arg2);
            RequirementsWatcher.logd(RequirementsWatcher.this + " NetworkCallback.onAvailable");
            RequirementsWatcher.this.checkRequirements();
        }

        public void onLost(Network arg2) {
            super.onLost(arg2);
            RequirementsWatcher.logd(RequirementsWatcher.this + " NetworkCallback.onLost");
            RequirementsWatcher.this.checkRequirements();
        }
    }

    class DeviceStatusChangeReceiver extends BroadcastReceiver {
        DeviceStatusChangeReceiver(RequirementsWatcher arg1, com.google.android.exoplayer2.scheduler.RequirementsWatcher$1 arg2) {
            this(arg1);
        }

        private DeviceStatusChangeReceiver(RequirementsWatcher arg1) {
            RequirementsWatcher.this = arg1;
            super();
        }

        public void onReceive(Context arg2, Intent arg3) {
            if(!this.isInitialStickyBroadcast()) {
                RequirementsWatcher.logd(RequirementsWatcher.this + " received " + arg3.getAction());
                RequirementsWatcher.this.checkRequirements();
            }
        }
    }

    public interface Listener {
        void requirementsMet(RequirementsWatcher arg1);

        void requirementsNotMet(RequirementsWatcher arg1);
    }

    private static final String TAG = "RequirementsWatcher";
    private final Context context;
    private final Listener listener;
    private CapabilityValidatedCallback networkCallback;
    private DeviceStatusChangeReceiver receiver;
    private final Requirements requirements;
    private boolean requirementsWereMet;

    public RequirementsWatcher(Context arg1, Listener arg2, Requirements arg3) {
        super();
        this.requirements = arg3;
        this.listener = arg2;
        this.context = arg1.getApplicationContext();
        RequirementsWatcher.logd(this + " created");
    }

    static void access$200(String arg0) {
        RequirementsWatcher.logd(arg0);
    }

    static void access$300(RequirementsWatcher arg0) {
        arg0.checkRequirements();
    }

    private void checkRequirements() {
        boolean v0 = this.requirements.checkRequirements(this.context);
        if(v0 == this.requirementsWereMet) {
            RequirementsWatcher.logd("requirementsAreMet is still " + v0);
            return;
        }

        this.requirementsWereMet = v0;
        if(v0) {
            RequirementsWatcher.logd("start job");
            this.listener.requirementsMet(this);
        }
        else {
            RequirementsWatcher.logd("stop job");
            this.listener.requirementsNotMet(this);
        }
    }

    public Requirements getRequirements() {
        return this.requirements;
    }

    private static void logd(String arg0) {
    }

    @TargetApi(value=23) private void registerNetworkCallbackV23() {
        Object v0 = this.context.getSystemService("connectivity");
        NetworkRequest v1 = new NetworkRequest$Builder().addCapability(16).build();
        this.networkCallback = new CapabilityValidatedCallback(this, null);
        ((ConnectivityManager)v0).registerNetworkCallback(v1, this.networkCallback);
    }

    public void start() {
        String v1;
        Assertions.checkNotNull(Looper.myLooper());
        this.requirementsWereMet = this.requirements.checkRequirements(this.context);
        IntentFilter v0 = new IntentFilter();
        int v2 = 23;
        if(this.requirements.getRequiredNetworkType() != 0) {
            if(Util.SDK_INT >= v2) {
                this.registerNetworkCallbackV23();
            }
            else {
                v0.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
        }

        if(this.requirements.isChargingRequired()) {
            v0.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            v0.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        }

        if(this.requirements.isIdleRequired()) {
            if(Util.SDK_INT >= v2) {
                v1 = "android.os.action.DEVICE_IDLE_MODE_CHANGED";
            }
            else {
                v0.addAction("android.intent.action.SCREEN_ON");
                v1 = "android.intent.action.SCREEN_OFF";
            }

            v0.addAction(v1);
        }

        this.receiver = new DeviceStatusChangeReceiver(this, null);
        this.context.registerReceiver(this.receiver, v0, null, new Handler());
        RequirementsWatcher.logd(this + " started");
    }

    public void stop() {
        this.context.unregisterReceiver(this.receiver);
        this.receiver = null;
        if(this.networkCallback != null) {
            this.unregisterNetworkCallback();
        }

        RequirementsWatcher.logd(this + " stopped");
    }

    public String toString() {
        return super.toString();
    }

    private void unregisterNetworkCallback() {
        if(Util.SDK_INT >= 21) {
            this.context.getSystemService("connectivity").unregisterNetworkCallback(this.networkCallback);
            this.networkCallback = null;
        }
    }
}

