package org.telegram.messenger.voip;

import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build$VERSION;
import android.os.SystemClock;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC$TL_phoneConnection;
import org.telegram.ui.Components.voip.VoIPHelper;

public class VoIPController {
    public interface ConnectionStateListener {
        void onCallUpgradeRequestReceived();

        void onConnectionStateChanged(int arg1);

        void onGroupCallKeyReceived(byte[] arg1);

        void onGroupCallKeySent();

        void onSignalBarCountChanged(int arg1);
    }

    public class Stats {
        public long bytesRecvdMobile;
        public long bytesRecvdWifi;
        public long bytesSentMobile;
        public long bytesSentWifi;

        public Stats() {
            super();
        }

        public String toString() {
            return "Stats{bytesRecvdMobile=" + this.bytesRecvdMobile + ", bytesSentWifi=" + this.bytesSentWifi + ", bytesRecvdWifi=" + this.bytesRecvdWifi + ", bytesSentMobile=" + this.bytesSentMobile + '}';
        }
    }

    public static final int DATA_SAVING_ALWAYS = 2;
    public static final int DATA_SAVING_MOBILE = 1;
    public static final int DATA_SAVING_NEVER = 0;
    public static final int ERROR_AUDIO_IO = 3;
    public static final int ERROR_CONNECTION_SERVICE = -5;
    public static final int ERROR_INCOMPATIBLE = 1;
    public static final int ERROR_INSECURE_UPGRADE = -4;
    public static final int ERROR_LOCALIZED = -3;
    public static final int ERROR_PEER_OUTDATED = -1;
    public static final int ERROR_PRIVACY = -2;
    public static final int ERROR_TIMEOUT = 2;
    public static final int ERROR_UNKNOWN = 0;
    public static final int NET_TYPE_3G = 3;
    public static final int NET_TYPE_DIALUP = 10;
    public static final int NET_TYPE_EDGE = 2;
    public static final int NET_TYPE_ETHERNET = 7;
    public static final int NET_TYPE_GPRS = 1;
    public static final int NET_TYPE_HSPA = 4;
    public static final int NET_TYPE_LTE = 5;
    public static final int NET_TYPE_OTHER_HIGH_SPEED = 8;
    public static final int NET_TYPE_OTHER_LOW_SPEED = 9;
    public static final int NET_TYPE_OTHER_MOBILE = 11;
    public static final int NET_TYPE_UNKNOWN = 0;
    public static final int NET_TYPE_WIFI = 6;
    public static final int PEER_CAP_GROUP_CALLS = 1;
    public static final int STATE_ESTABLISHED = 3;
    public static final int STATE_FAILED = 4;
    public static final int STATE_RECONNECTING = 5;
    public static final int STATE_WAIT_INIT = 1;
    public static final int STATE_WAIT_INIT_ACK = 2;
    protected long callStartTime;
    protected ConnectionStateListener listener;
    protected long nativeInst;

    public VoIPController() {
        super();
        this.nativeInst = 0;
        this.nativeInst = this.nativeInit();
    }

    private void callUpgradeRequestReceived() {
        if(this.listener != null) {
            this.listener.onCallUpgradeRequestReceived();
        }
    }

    public void connect() {
        this.ensureNativeInstance();
        this.nativeConnect(this.nativeInst);
    }

    public void debugCtl(int arg3, int arg4) {
        this.ensureNativeInstance();
        this.nativeDebugCtl(this.nativeInst, arg3, arg4);
    }

    protected void ensureNativeInstance() {
        if(this.nativeInst != 0) {
            return;
        }

        throw new IllegalStateException("Native instance is not valid");
    }

    public long getCallDuration() {
        return SystemClock.elapsedRealtime() - this.callStartTime;
    }

    public String getDebugLog() {
        this.ensureNativeInstance();
        return this.nativeGetDebugLog(this.nativeInst);
    }

    public String getDebugString() {
        this.ensureNativeInstance();
        return this.nativeGetDebugString(this.nativeInst);
    }

    public int getLastError() {
        this.ensureNativeInstance();
        return this.nativeGetLastError(this.nativeInst);
    }

    private String getLogFilePath(long arg11) {
        File v0 = VoIPHelper.getLogsDir();
        File[] v1 = v0.listFiles();
        ArrayList v2 = new ArrayList();
        v2.addAll(Arrays.asList(((Object[])v1)));
        while(v2.size() > 20) {
            Object v1_1 = v2.get(0);
            Iterator v3 = v2.iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                if(!((File)v4).getName().endsWith(".log")) {
                    continue;
                }

                if(((File)v4).lastModified() >= ((File)v1_1).lastModified()) {
                    continue;
                }

                v1_1 = v4;
            }

            ((File)v1_1).delete();
            v2.remove(v1_1);
        }

        StringBuilder v2_1 = new StringBuilder();
        v2_1.append(arg11);
        v2_1.append(".log");
        return new File(v0, v2_1.toString()).getAbsolutePath();
    }

    private String getLogFilePath(String arg11) {
        Calendar v0 = Calendar.getInstance();
        return new File(ApplicationLoader.applicationContext.getExternalFilesDir(null), String.format(Locale.US, "logs/%02d_%02d_%04d_%02d_%02d_%02d_%s.txt", Integer.valueOf(v0.get(5)), Integer.valueOf(v0.get(2) + 1), Integer.valueOf(v0.get(1)), Integer.valueOf(v0.get(11)), Integer.valueOf(v0.get(12)), Integer.valueOf(v0.get(13)), arg11)).getAbsolutePath();
    }

    public int getPeerCapabilities() {
        this.ensureNativeInstance();
        return this.nativeGetPeerCapabilities(this.nativeInst);
    }

    public long getPreferredRelayID() {
        this.ensureNativeInstance();
        return this.nativeGetPreferredRelayID(this.nativeInst);
    }

    public void getStats(Stats arg3) {
        this.ensureNativeInstance();
        if(arg3 != null) {
            this.nativeGetStats(this.nativeInst, arg3);
            return;
        }

        throw new NullPointerException("You\'re not supposed to pass null here");
    }

    public static String getVersion() {
        return VoIPController.nativeGetVersion();
    }

    private void groupCallKeyReceived(byte[] arg2) {
        if(this.listener != null) {
            this.listener.onGroupCallKeyReceived(arg2);
        }
    }

    private void groupCallKeySent() {
        if(this.listener != null) {
            this.listener.onGroupCallKeySent();
        }
    }

    private void handleSignalBarsChange(int arg2) {
        if(this.listener != null) {
            this.listener.onSignalBarCountChanged(arg2);
        }
    }

    private void handleStateChange(int arg6) {
        if(arg6 == 3 && this.callStartTime == 0) {
            this.callStartTime = SystemClock.elapsedRealtime();
        }

        if(this.listener != null) {
            this.listener.onConnectionStateChanged(arg6);
        }
    }

    private native void nativeConnect(long arg1) {
    }

    private native void nativeDebugCtl(long arg1, int arg2, int arg3) {
    }

    private native String nativeGetDebugLog(long arg1) {
    }

    private native String nativeGetDebugString(long arg1) {
    }

    private native int nativeGetLastError(long arg1) {
    }

    private native int nativeGetPeerCapabilities(long arg1) {
    }

    private native long nativeGetPreferredRelayID(long arg1) {
    }

    private native void nativeGetStats(long arg1, Stats arg2) {
    }

    private static native String nativeGetVersion() {
    }

    private native long nativeInit() {
    }

    private native void nativeRelease(long arg1) {
    }

    private native void nativeRequestCallUpgrade(long arg1) {
    }

    private native void nativeSendGroupCallKey(long arg1, byte[] arg2) {
    }

    private native void nativeSetAudioOutputGainControlEnabled(long arg1, boolean arg2) {
    }

    private native void nativeSetConfig(long arg1, double arg2, double arg3, int arg4, boolean arg5, boolean arg6, boolean arg7, String arg8, String arg9) {
    }

    private native void nativeSetEchoCancellationStrength(long arg1, int arg2) {
    }

    private native void nativeSetEncryptionKey(long arg1, byte[] arg2, boolean arg3) {
    }

    private native void nativeSetMicMute(long arg1, boolean arg2) {
    }

    private static native void nativeSetNativeBufferSize(int arg0) {
    }

    private native void nativeSetNetworkType(long arg1, int arg2) {
    }

    private native void nativeSetProxy(long arg1, String arg2, int arg3, String arg4, String arg5) {
    }

    private native void nativeSetRemoteEndpoints(long arg1, TL_phoneConnection[] arg2, boolean arg3, boolean arg4, int arg5) {
    }

    private native void nativeStart(long arg1) {
    }

    public void release() {
        this.ensureNativeInstance();
        this.nativeRelease(this.nativeInst);
        this.nativeInst = 0;
    }

    public void requestCallUpgrade() {
        this.ensureNativeInstance();
        this.nativeRequestCallUpgrade(this.nativeInst);
    }

    public void sendGroupCallKey(byte[] arg4) {
        if(arg4 != null) {
            if(arg4.length == 256) {
                this.ensureNativeInstance();
                this.nativeSendGroupCallKey(this.nativeInst, arg4);
                return;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("key must be 256 bytes long, got ");
            v1.append(arg4.length);
            throw new IllegalArgumentException(v1.toString());
        }

        throw new NullPointerException("key can not be null");
    }

    public void setAudioOutputGainControlEnabled(boolean arg3) {
        this.ensureNativeInstance();
        this.nativeSetAudioOutputGainControlEnabled(this.nativeInst, arg3);
    }

    public void setConfig(double arg15, double arg17, int arg19, long arg20) {
        boolean v2;
        boolean v0;
        VoIPController v13 = this;
        this.ensureNativeInstance();
        if(Build$VERSION.SDK_INT >= 16) {
            try {
                v0 = AcousticEchoCanceler.isAvailable();
            }
            catch(Throwable ) {
                goto label_9;
            }

            try {
                v2 = AcousticEchoCanceler.isAvailable();
            }
            catch(Throwable ) {
                goto label_10;
            }
        }
        else {
        label_9:
            v0 = false;
        label_10:
            v2 = false;
        }

        MessagesController.getGlobalMainSettings().getBoolean("dbg_dump_call_stats", false);
        long v3 = v13.nativeInst;
        boolean v8 = !v0 || !VoIPServerConfig.getBoolean("use_system_aec", true) ? true : false;
        boolean v9 = !v2 || !VoIPServerConfig.getBoolean("use_system_ns", true) ? true : false;
        this.nativeSetConfig(v3, arg15, arg17, arg19, v8, v9, true, this.getLogFilePath(arg20), null);
    }

    public void setConnectionStateListener(ConnectionStateListener arg1) {
        this.listener = arg1;
    }

    public void setEchoCancellationStrength(int arg3) {
        this.ensureNativeInstance();
        this.nativeSetEchoCancellationStrength(this.nativeInst, arg3);
    }

    public void setEncryptionKey(byte[] arg3, boolean arg4) {
        if(arg3.length == 256) {
            this.ensureNativeInstance();
            this.nativeSetEncryptionKey(this.nativeInst, arg3, arg4);
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("key length must be exactly 256 bytes but is ");
        v0.append(arg3.length);
        throw new IllegalArgumentException(v0.toString());
    }

    public void setMicMute(boolean arg3) {
        this.ensureNativeInstance();
        this.nativeSetMicMute(this.nativeInst, arg3);
    }

    public static void setNativeBufferSize(int arg0) {
        VoIPController.nativeSetNativeBufferSize(arg0);
    }

    public void setNetworkType(int arg3) {
        this.ensureNativeInstance();
        this.nativeSetNetworkType(this.nativeInst, arg3);
    }

    public void setProxy(String arg8, int arg9, String arg10, String arg11) {
        this.ensureNativeInstance();
        if(arg8 != null) {
            this.nativeSetProxy(this.nativeInst, arg8, arg9, arg10, arg11);
            return;
        }

        throw new NullPointerException("address can\'t be null");
    }

    public void setRemoteEndpoints(TL_phoneConnection[] arg8, boolean arg9, boolean arg10, int arg11) {
        StringBuilder v9;
        TL_phoneConnection v1;
        if(arg8.length == 0) {
            goto label_51;
        }

        int v0 = 0;
        while(true) {
            if(v0 >= arg8.length) {
                goto label_42;
            }

            v1 = arg8[v0];
            if(v1.ip != null && v1.ip.length() != 0) {
                if(v1.peer_tag != null) {
                    if(v1.peer_tag.length == 16) {
                    }
                    else {
                        v9 = new StringBuilder();
                        v9.append("endpoint ");
                        v9.append(v1);
                        v9.append(" has peer_tag of wrong length");
                        throw new IllegalArgumentException(v9.toString());
                    }
                }

                ++v0;
                continue;
            }

            break;
        }

        v9 = new StringBuilder();
        v9.append("endpoint ");
        v9.append(v1);
        v9.append(" has empty/null ipv4");
        throw new IllegalArgumentException(v9.toString());
    label_42:
        this.ensureNativeInstance();
        this.nativeSetRemoteEndpoints(this.nativeInst, arg8, arg9, arg10, arg11);
        return;
    label_51:
        throw new IllegalArgumentException("endpoints size is 0");
    }

    public void start() {
        this.ensureNativeInstance();
        this.nativeStart(this.nativeInst);
    }
}

