package org.telegram.tgnet;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo$State;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.e.a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.customization.Model.CUrl;
import org.telegram.customization.g.e;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.KeepAliveJob;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.StatsController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import utils.a.b;

public class ConnectionsManager {
    final class org.telegram.tgnet.ConnectionsManager$1 extends ThreadLocal {
        org.telegram.tgnet.ConnectionsManager$1() {
            super();
        }

        protected Object initialValue() {
            return this.initialValue();
        }

        protected HashMap initialValue() {
            return new HashMap();
        }
    }

    class AzureLoadTask extends AsyncTask {
        private int currentAccount;

        public AzureLoadTask(int arg1) {
            super();
            this.currentAccount = arg1;
        }

        static int access$800(AzureLoadTask arg0) {
            return arg0.currentAccount;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected NativeByteBuffer doInBackground(Void[] arg7) {
            NativeByteBuffer v3_1;
            Throwable v5;
            InputStream v7_1;
            int v2_1;
            ByteArrayOutputStream v2;
            InputStream v1;
            ByteArrayOutputStream v1_1;
            InputStream v0_3;
            NativeByteBuffer v7 = null;
            try {
                URL v0_1 = ConnectionsManager.native_isTestBackend(this.currentAccount) != 0 ? new URL("https://software-download.microsoft.com/testv2/config.txt") : new URL("https://software-download.microsoft.com/prodv2/config.txt");
                URLConnection v0_2 = v0_1.openConnection();
                v0_2.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
                v0_2.addRequestProperty("Host", "tcdnb.azureedge.net");
                v0_2.setConnectTimeout(5000);
                v0_2.setReadTimeout(5000);
                v0_2.connect();
                v0_3 = v0_2.getInputStream();
            }
            catch(Throwable v0) {
                v1_1 = ((ByteArrayOutputStream)v7);
                goto label_90;
            }
            catch(Throwable v0) {
                v1 = ((InputStream)v7);
                v2 = ((ByteArrayOutputStream)v1);
                goto label_78;
            }

            try {
                v1_1 = new ByteArrayOutputStream();
                v2_1 = 32768;
            }
            catch(Throwable v1_2) {
                v5 = v1_2;
                v1_1 = ((ByteArrayOutputStream)v7);
                v7_1 = v0_3;
                v0 = v5;
                goto label_90;
            }
            catch(Throwable v1_2) {
                v2 = ((ByteArrayOutputStream)v7);
                v5 = v1_2;
                v1 = v0_3;
                v0 = v5;
                goto label_78;
            }

            try {
                byte[] v2_3 = new byte[v2_1];
                while(true) {
                    if(!this.isCancelled()) {
                        int v3 = v0_3.read(v2_3);
                        if(v3 > 0) {
                            v1_1.write(v2_3, 0, v3);
                            continue;
                        }
                        else {
                        }
                    }
                    else {
                    }

                    break;
                }

                v2_3 = Base64.decode(v1_1.toByteArray(), 0);
                v3_1 = new NativeByteBuffer(v2_3.length);
                v3_1.writeBytes(v2_3);
                if(v0_3 == null) {
                    goto label_47;
                }
            }
            catch(Throwable v7_2) {
                goto label_50;
            }
            catch(Throwable v2_2) {
                goto label_55;
            }

            try {
                v0_3.close();
            }
            catch(Throwable v7_2) {
                FileLog.e(v7_2);
            }

            try {
            label_47:
                v1_1.close();
                return v3_1;
            }
            catch(Exception ) {
                return v3_1;
            }

        label_55:
            ByteArrayOutputStream v5_1 = v1_1;
            v1 = v0_3;
            v0 = v2_2;
            v2 = v5_1;
            try {
            label_78:
                FileLog.e(v0);
                if(v1 == null) {
                    goto label_84;
                }
            }
            catch(Throwable v0) {
                v7_1 = v1;
                v1_1 = v2;
                goto label_90;
            }

            try {
                v1.close();
            }
            catch(Throwable v0) {
                FileLog.e(v0);
            }

        label_84:
            if(v2 != null) {
                try {
                    v2.close();
                    return v7;
                }
                catch(Exception ) {
                    return v7;
                }
            }

            return v7;
        label_50:
            InputStream v5_2 = v0_3;
            v0 = v7_2;
            v7_1 = v5_2;
        label_90:
            if(v7_1 != null) {
                try {
                    v7_1.close();
                }
                catch(Throwable v7_2) {
                    FileLog.e(v7_2);
                }
            }

            if(v1_1 != null) {
                try {
                    v1_1.close();
                    goto label_97;
                }
                catch(Exception ) {
                label_97:
                    throw v0;
                }
            }

            goto label_97;
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((NativeByteBuffer)arg1));
        }

        protected void onPostExecute(NativeByteBuffer arg3) {
            Utilities.stageQueue.postRunnable(new Runnable(arg3) {
                public void run() {
                    if(this.val$result != null) {
                        ConnectionsManager.native_applyDnsConfig(AzureLoadTask.this.currentAccount, this.val$result.address, UserConfig.getInstance(AzureLoadTask.this.currentAccount).getClientPhone());
                    }
                    else if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("failed to get azure result");
                    }

                    ConnectionsManager.currentTask = null;
                }
            });
        }
    }

    class DnsTxtLoadTask extends AsyncTask {
        private int currentAccount;

        public DnsTxtLoadTask(int arg1) {
            super();
            this.currentAccount = arg1;
        }

        static int access$500(DnsTxtLoadTask arg0) {
            return arg0.currentAccount;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected NativeByteBuffer doInBackground(Void[] arg12) {
            NativeByteBuffer v5_2;
            int v5_1;
            byte[] v2_1;
            ByteArrayOutputStream v3_2;
            InputStream v4_3;
            NativeByteBuffer v12 = null;
            Throwable v2 = ((Throwable)v12);
            InputStream v3 = ((InputStream)v2);
            int v1 = 0;
        label_5:
            if(v1 >= 3) {
                goto label_152;
            }

            if(v1 == 0) {
                try {
                    String v4_1 = "www.google.com";
                    goto label_24;
                label_19:
                    v4_1 = v1 == 1 ? "www.google.ru" : "google.com";
                label_24:
                    String v5 = ConnectionsManager.native_isTestBackend(this.currentAccount) != 0 ? "tapv2.stel.com" : MessagesController.getInstance(this.currentAccount).dcDomainName;
                    StringBuilder v7 = new StringBuilder();
                    v7.append("https://");
                    v7.append(v4_1);
                    v7.append("/resolve?name=");
                    v7.append(v5);
                    v7.append("&type=16");
                    URLConnection v4_2 = new URL(v7.toString()).openConnection();
                    v4_2.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
                    v4_2.addRequestProperty("Host", "dns.google.com");
                    v4_2.setConnectTimeout(5000);
                    v4_2.setReadTimeout(5000);
                    v4_2.connect();
                    v4_3 = v4_2.getInputStream();
                    goto label_57;
                }
                catch(Throwable v12_1) {
                    goto label_11;
                }
                catch(Throwable v4) {
                    goto label_15;
                }
            }
            else {
                goto label_19;
            }

            goto label_24;
            try {
            label_57:
                v3_2 = new ByteArrayOutputStream();
                v2 = null;
            }
            catch(Throwable v12_1) {
                goto label_123;
            }
            catch(Throwable v3_1) {
                goto label_125;
            }

            try {
                v2_1 = new byte[((int)v2)];
                goto label_61;
            }
            catch(Throwable v12_1) {
                goto label_117;
            }
            catch(Throwable v2) {
                goto label_119;
            }

        label_123:
            goto label_12;
        label_125:
            InputStream v10 = v4_3;
            ByteArrayOutputStream v4_4 = ((ByteArrayOutputStream)v2);
            v2 = v3_1;
            goto label_128;
        label_152:
            return ((NativeByteBuffer)v12_1);
            try {
                while(true) {
                label_61:
                    if(!this.isCancelled()) {
                        v5_1 = v4_3.read(v2_1);
                        if(v5_1 > 0) {
                            v3_2.write(v2_1, 0, v5_1);
                            continue;
                        }
                        else {
                        }
                    }
                    else {
                    }

                    break;
                }

                JSONArray v2_2 = new JSONObject(new String(v3_2.toByteArray(), "UTF-8")).getJSONArray("Answer");
                v5_1 = v2_2.length();
                ArrayList v6 = new ArrayList(v5_1);
                int v7_1;
                for(v7_1 = 0; v7_1 < v5_1; ++v7_1) {
                    v6.add(v2_2.getJSONObject(v7_1).getString("data"));
                }

                Collections.sort(((List)v6), new Comparator() {
                    public int compare(Object arg1, Object arg2) {
                        return this.compare(((String)arg1), ((String)arg2));
                    }

                    public int compare(String arg1, String arg2) {
                        int v1 = arg1.length();
                        int v2 = arg2.length();
                        if(v1 > v2) {
                            return -1;
                        }

                        if(v1 < v2) {
                            return 1;
                        }

                        return 0;
                    }
                });
                StringBuilder v2_3 = new StringBuilder();
                for(v5_1 = 0; v5_1 < v6.size(); ++v5_1) {
                    v2_3.append(v6.get(v5_1).replace("\"", ""));
                }

                v2_1 = Base64.decode(v2_3.toString(), 0);
                v5_2 = new NativeByteBuffer(v2_1.length);
                v5_2.writeBytes(v2_1);
                if(v4_3 == null) {
                    goto label_114;
                }
            }
            catch(Throwable v12_1) {
                goto label_117;
            }
            catch(Throwable v2) {
                goto label_119;
            }

            try {
                v4_3.close();
            }
            catch(Throwable v12_1) {
                FileLog.e(v12_1);
            }

            try {
            label_114:
                v3_2.close();
                return v5_2;
            }
            catch(Exception ) {
                return v5_2;
            }

        label_119:
            v10 = v4_3;
            v4_4 = v3_2;
            goto label_128;
        label_117:
            goto label_144;
        label_128:
            v3 = v10;
            goto label_129;
        label_11:
            v4_3 = v3;
        label_12:
            v3_1 = v2;
            goto label_144;
        label_15:
            Throwable v10_1 = v4;
            v4 = v2;
            v2 = v10_1;
            goto label_129;
        label_144:
            if(v4_3 != null) {
                try {
                    v4_3.close();
                }
                catch(Throwable v0) {
                    FileLog.e(v0);
                }
            }

            if((((ByteArrayOutputStream)v3_1)) != null) {
                try {
                    ((ByteArrayOutputStream)v3_1).close();
                    goto label_151;
                }
                catch(Exception ) {
                label_151:
                    throw v12_1;
                }
            }

            goto label_151;
            try {
            label_129:
                FileLog.e(v2);
                if(v3 == null) {
                    goto label_135;
                }
            }
            catch(Throwable v12_1) {
                goto label_141;
            }

            try {
                v3.close();
            }
            catch(Throwable v2) {
                FileLog.e(v2);
            }

        label_135:
            if(v4_4 != null) {
                try {
                    v4_4.close();
                    goto label_137;
                }
                catch(Exception ) {
                label_137:
                    ++v1;
                    ByteArrayOutputStream v2_4 = v4_4;
                    goto label_5;
                }
            }

            goto label_137;
        label_141:
            ByteArrayOutputStream v10_2 = v4_4;
            v4_3 = v3;
            v3_2 = v10_2;
            goto label_144;
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((NativeByteBuffer)arg1));
        }

        protected void onPostExecute(NativeByteBuffer arg3) {
            Utilities.stageQueue.postRunnable(new Runnable(arg3) {
                public void run() {
                    AsyncTask v1 = null;
                    if(this.val$result != null) {
                        ConnectionsManager.currentTask = v1;
                        ConnectionsManager.native_applyDnsConfig(DnsTxtLoadTask.this.currentAccount, this.val$result.address, UserConfig.getInstance(DnsTxtLoadTask.this.currentAccount).getClientPhone());
                    }
                    else {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("failed to get dns txt result");
                            FileLog.d("start azure task");
                        }

                        AzureLoadTask v0 = new AzureLoadTask(DnsTxtLoadTask.this.currentAccount);
                        v0.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{((Void)v1), ((Void)v1), ((Void)v1)});
                        ConnectionsManager.currentTask = ((AsyncTask)v0);
                    }
                }
            });
        }
    }

    class FirebaseTask extends AsyncTask {
        private int currentAccount;
        private a firebaseRemoteConfig;

        public FirebaseTask(int arg1) {
            super();
            this.currentAccount = arg1;
        }

        static a access$600(FirebaseTask arg0) {
            return arg0.firebaseRemoteConfig;
        }

        static int access$700(FirebaseTask arg0) {
            return arg0.currentAccount;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected NativeByteBuffer doInBackground(Void[] arg3) {
            try {
                if(ConnectionsManager.native_isTestBackend(this.currentAccount) == 0) {
                    this.firebaseRemoteConfig = a.a();
                    this.firebaseRemoteConfig.a(new com.google.firebase.e.e$a().a(false).a());
                    String v3_1 = this.firebaseRemoteConfig.a("ipconfigv2");
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("current firebase value = " + v3_1);
                    }

                    this.firebaseRemoteConfig.a(0).addOnCompleteListener(new OnCompleteListener() {
                        public void onComplete(Task arg3) {
                            Utilities.stageQueue.postRunnable(new Runnable(arg3.isSuccessful()) {
                                public void run() {
                                    CharSequence v1_1;
                                    AsyncTask v0 = null;
                                    ConnectionsManager.currentTask = v0;
                                    if(this.val$success) {
                                        this.this$1.this$0.firebaseRemoteConfig.b();
                                        String v1 = this.this$1.this$0.firebaseRemoteConfig.a("ipconfigv2");
                                    }
                                    else {
                                        v1_1 = ((CharSequence)v0);
                                    }

                                    if(!TextUtils.isEmpty(v1_1)) {
                                        byte[] v0_1 = Base64.decode(((String)v1_1), 0);
                                        try {
                                            NativeByteBuffer v1_2 = new NativeByteBuffer(v0_1.length);
                                            v1_2.writeBytes(v0_1);
                                            ConnectionsManager.native_applyDnsConfig(this.this$1.this$0.currentAccount, v1_2.address, UserConfig.getInstance(this.this$1.this$0.currentAccount).getClientPhone());
                                        }
                                        catch(Exception v0_2) {
                                            FileLog.e(((Throwable)v0_2));
                                        }

                                        return;
                                    }

                                    if(BuildVars.LOGS_ENABLED) {
                                        FileLog.d("failed to get firebase result");
                                        FileLog.d("start dns txt task");
                                    }

                                    DnsTxtLoadTask v1_3 = new DnsTxtLoadTask(this.this$1.this$0.currentAccount);
                                    v1_3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{((Void)v0), ((Void)v0), ((Void)v0)});
                                    ConnectionsManager.currentTask = ((AsyncTask)v1_3);
                                }
                            });
                        }
                    });
                    return null;
                }

                throw new Exception("test backend");
            }
            catch(Throwable v3) {
                Utilities.stageQueue.postRunnable(new Runnable() {
                    public void run() {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("failed to get firebase result");
                            FileLog.d("start dns txt task");
                        }

                        DnsTxtLoadTask v0 = new DnsTxtLoadTask(FirebaseTask.this.currentAccount);
                        v0.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
                        ConnectionsManager.currentTask = ((AsyncTask)v0);
                    }
                });
                FileLog.e(v3);
            }

            return null;
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((NativeByteBuffer)arg1));
        }

        protected void onPostExecute(NativeByteBuffer arg1) {
        }
    }

    class ResolvedDomain {
        public String address;
        long ttl;

        public ResolvedDomain(String arg1, long arg2) {
            super();
            this.address = arg1;
            this.ttl = arg2;
        }
    }

    public static final int ConnectionStateConnected = 3;
    public static final int ConnectionStateConnecting = 1;
    public static final int ConnectionStateConnectingToProxy = 4;
    public static final int ConnectionStateUpdating = 5;
    public static final int ConnectionStateWaitingForNetwork = 2;
    public static final int ConnectionTypeDownload = 2;
    public static final int ConnectionTypeDownload2 = 65538;
    public static final int ConnectionTypeGeneric = 1;
    public static final int ConnectionTypePush = 8;
    public static final int ConnectionTypeUpload = 4;
    public static final int DEFAULT_DATACENTER_ID = 2147483647;
    public static final int FileTypeAudio = 50331648;
    public static final int FileTypeFile = 67108864;
    public static final int FileTypePhoto = 16777216;
    public static final int FileTypeVideo = 33554432;
    private static volatile ConnectionsManager[] Instance = null;
    public static final int RequestFlagCanCompress = 4;
    public static final int RequestFlagEnableUnauthorized = 1;
    public static final int RequestFlagFailOnServerErrors = 2;
    public static final int RequestFlagForceDownload = 32;
    public static final int RequestFlagInvokeAfter = 64;
    public static final int RequestFlagNeedQuickAck = 128;
    public static final int RequestFlagTryDifferentDc = 16;
    public static final int RequestFlagWithoutLogin = 8;
    private boolean appPaused;
    private int appResumeCount;
    private int connectionState;
    private int currentAccount;
    private static AsyncTask currentTask;
    private static ThreadLocal dnsCache;
    private boolean isUpdating;
    private static int lastClassGuid;
    private static long lastDnsRequestTime;
    private long lastPauseTime;
    private AtomicInteger lastRequestToken;
    public static long startServiceTime;
    public static long startVoiceCall;

    static {
        ConnectionsManager.dnsCache = new org.telegram.tgnet.ConnectionsManager$1();
        ConnectionsManager.lastClassGuid = 1;
        ConnectionsManager.Instance = new ConnectionsManager[3];
        ConnectionsManager.startServiceTime = 0;
        ConnectionsManager.startVoiceCall = 0;
    }

    public ConnectionsManager(int arg16) {
        String v4_1;
        String v3_2;
        String v2_2;
        String v1;
        String v0_1;
        ConnectionsManager v13 = this;
        int v0 = arg16;
        super();
        v13.lastPauseTime = System.currentTimeMillis();
        v13.appPaused = true;
        v13.lastRequestToken = new AtomicInteger(1);
        v13.currentAccount = v0;
        v13.connectionState = ConnectionsManager.native_getConnectionState(v13.currentAccount);
        File v2 = ApplicationLoader.getFilesDirFixed();
        if(v0 != 0) {
            StringBuilder v4 = new StringBuilder();
            v4.append("account");
            v4.append(v0);
            File v3 = new File(v2, v4.toString());
            v3.mkdirs();
            v2 = v3;
        }

        String v9 = v2.toString();
        boolean v12 = MessagesController.getGlobalNotificationsSettings().getBoolean("pushConnection", true);
        try {
            v0_1 = LocaleController.getSystemLocaleStringIso639().toLowerCase();
            v1 = LocaleController.getLocaleStringIso639().toLowerCase();
            v2_2 = Build.MANUFACTURER + Build.MODEL;
            PackageInfo v3_1 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);
            v3_2 = v3_1.versionName + " (" + v3_1.versionCode + ")";
            v4_1 = "SDK " + Build$VERSION.SDK_INT;
        }
        catch(Exception ) {
            v0_1 = "en";
            v1 = "";
            v2_2 = "Android unknown";
            v3_2 = "App version unknown";
            v4_1 = "SDK " + Build$VERSION.SDK_INT;
        }

        String v7 = v1;
        if(v0_1.trim().length() == 0) {
            v0_1 = "en";
        }

        String v8 = v0_1;
        String v5 = v2_2.trim().length() == 0 ? "Android unknown" : v2_2;
        String v6 = v3_2.trim().length() == 0 ? "App version unknown" : v3_2;
        String v10 = v4_1.trim().length() == 0 ? "SDK Unknown" : v4_1;
        UserConfig.getInstance(v13.currentAccount).loadConfig();
        if(!b.z(ApplicationLoader.applicationContext)) {
            this.init(BuildVars.BUILD_VERSION, 85, b.Q(ApplicationLoader.applicationContext), v5, v10, v6, v7, v8, v9, FileLog.getNetworkLogPath(), UserConfig.getInstance(v13.currentAccount).getClientUserId(), v12);
        }
    }

    static int access$000(ConnectionsManager arg0) {
        return arg0.currentAccount;
    }

    static int access$100(ConnectionsManager arg0) {
        return arg0.connectionState;
    }

    static int access$102(ConnectionsManager arg0, int arg1) {
        arg0.connectionState = arg1;
        return arg1;
    }

    static AsyncTask access$200() {
        return ConnectionsManager.currentTask;
    }

    static AsyncTask access$202(AsyncTask arg0) {
        ConnectionsManager.currentTask = arg0;
        return arg0;
    }

    static long access$300() {
        return ConnectionsManager.lastDnsRequestTime;
    }

    static long access$302(long arg0) {
        ConnectionsManager.lastDnsRequestTime = arg0;
        return arg0;
    }

    static boolean access$400(ConnectionsManager arg0) {
        return arg0.isUpdating;
    }

    static boolean access$402(ConnectionsManager arg0, boolean arg1) {
        arg0.isUpdating = arg1;
        return arg1;
    }

    public void applyDatacenterAddress(int arg2, String arg3, int arg4) {
        ConnectionsManager.native_applyDatacenterAddress(this.currentAccount, arg2, arg3, arg4);
    }

    public void bindRequestToGuid(int arg2, int arg3) {
        ConnectionsManager.native_bindRequestToGuid(this.currentAccount, arg2, arg3);
    }

    public void cancelRequest(int arg2, boolean arg3) {
        ConnectionsManager.native_cancelRequest(this.currentAccount, arg2, arg3);
    }

    public void cancelRequestsForGuid(int arg2) {
        ConnectionsManager.native_cancelRequestsForGuid(this.currentAccount, arg2);
    }

    public void checkConnection() {
        ConnectionsManager.native_setUseIpv6(this.currentAccount, ConnectionsManager.useIpv6Address());
        ConnectionsManager.native_setNetworkAvailable(this.currentAccount, ConnectionsManager.isNetworkOnline(), ConnectionsManager.getCurrentNetworkType(), ConnectionsManager.isConnectionSlow());
    }

    public long checkProxy(String arg8, int arg9, String arg10, String arg11, String arg12, RequestTimeDelegate arg13) {
        if(TextUtils.isEmpty(((CharSequence)arg8))) {
            return 0;
        }

        if(arg8 == null) {
            arg8 = "";
        }

        String v1 = arg8;
        if(arg10 == null) {
            arg10 = "";
        }

        String v3 = arg10;
        if(arg11 == null) {
            arg11 = "";
        }

        String v4 = arg11;
        if(arg12 == null) {
            arg12 = "";
        }

        return ConnectionsManager.native_checkProxy(this.currentAccount, v1, arg9, v3, v4, arg12, arg13);
    }

    public void cleanup(boolean arg2) {
        ConnectionsManager.native_cleanUp(this.currentAccount, arg2);
    }

    public static int generateClassGuid() {
        int v0 = ConnectionsManager.lastClassGuid;
        ConnectionsManager.lastClassGuid = v0 + 1;
        return v0;
    }

    public int getConnectionState() {
        if(this.connectionState == 3 && (this.isUpdating)) {
            return 5;
        }

        return this.connectionState;
    }

    public static int getCurrentNetworkType() {
        if(ConnectionsManager.isConnectedOrConnectingToWiFi()) {
            return 1;
        }

        if(ConnectionsManager.isRoaming()) {
            return 2;
        }

        return 0;
    }

    public int getCurrentTime() {
        return ConnectionsManager.native_getCurrentTime(this.currentAccount);
    }

    public long getCurrentTimeMillis() {
        return ConnectionsManager.native_getCurrentTimeMillis(this.currentAccount);
    }

    public static String getHostByName(String arg6, int arg7) {
        String v0_5;
        int v0_2;
        ByteArrayOutputStream v2_1;
        InputStream v1_1;
        Object v7 = ConnectionsManager.dnsCache.get();
        Object v0 = ((HashMap)v7).get(arg6);
        if(v0 != null && SystemClock.elapsedRealtime() - ((ResolvedDomain)v0).ttl < 300000) {
            return ((ResolvedDomain)v0).address;
        }

        ByteArrayOutputStream v0_1 = null;
        try {
            StringBuilder v2 = new StringBuilder();
            v2.append("https://www.google.com/resolve?name=");
            v2.append(arg6);
            v2.append("&type=A");
            URLConnection v1 = new URL(v2.toString()).openConnection();
            v1.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
            v1.addRequestProperty("Host", "dns.google.com");
            v1.setConnectTimeout(1000);
            v1.setReadTimeout(2000);
            v1.connect();
            v1_1 = v1.getInputStream();
        }
        catch(Throwable v6) {
            v1_1 = ((InputStream)v0_1);
            goto label_106;
        }
        catch(Throwable v6) {
            v2_1 = v0_1;
            goto label_93;
        }

        try {
            v2_1 = new ByteArrayOutputStream();
            v0_2 = 32768;
        }
        catch(Throwable v6) {
            goto label_106;
        }
        catch(Throwable v6) {
            v2_1 = ((ByteArrayOutputStream)v0_2);
            goto label_86;
        }

        try {
            byte[] v0_3 = new byte[v0_2];
            while(true) {
                int v3 = v1_1.read(v0_3);
                if(v3 <= 0) {
                    break;
                }

                v2_1.write(v0_3, 0, v3);
            }

            JSONArray v0_4 = new JSONObject(new String(v2_1.toByteArray())).getJSONArray("Answer");
            if(v0_4.length() <= 0) {
                goto label_71;
            }

            v0_5 = v0_4.getJSONObject(Utilities.random.nextInt(v0_4.length())).getString("data");
            ((HashMap)v7).put(arg6, new ResolvedDomain(v0_5, SystemClock.elapsedRealtime()));
            if(v1_1 == null) {
                goto label_69;
            }
        }
        catch(Throwable v6) {
            goto label_105;
        }
        catch(Throwable v6) {
            goto label_86;
        }

        try {
            v1_1.close();
        }
        catch(Throwable v6) {
            FileLog.e(v6);
        }

        try {
        label_69:
            v2_1.close();
            return v0_5;
        }
        catch(Exception ) {
            return v0_5;
        }

    label_71:
        if(v1_1 == null) {
            goto label_76;
        }

        try {
            v1_1.close();
        }
        catch(Throwable v6) {
            FileLog.e(v6);
        }

        goto label_76;
    label_86:
        InputStream v0_6 = v1_1;
        try {
        label_93:
            FileLog.e(v6);
            if(v0_6 == null) {
                goto label_99;
            }
        }
        catch(Throwable v6) {
            v1_1 = v0_6;
            goto label_105;
        }

        try {
            v0_6.close();
        }
        catch(Throwable v6) {
            FileLog.e(v6);
        }

    label_99:
        if(v2_1 == null) {
            return "";
        }

        try {
        label_76:
            v2_1.close();
            return "";
        }
        catch(Exception ) {
            return "";
        }

    label_105:
        v0_1 = v2_1;
    label_106:
        if(v1_1 != null) {
            try {
                v1_1.close();
            }
            catch(Throwable v7_1) {
                FileLog.e(v7_1);
            }
        }

        if((((ByteArrayOutputStream)v0_2)) != null) {
            try {
                ((ByteArrayOutputStream)v0_2).close();
                goto label_113;
            }
            catch(Exception ) {
            label_113:
                throw v6;
            }
        }

        goto label_113;
    }

    public static int getInitFlags() {
        return 0;
    }

    public static ConnectionsManager getInstance(int arg3) {
        ConnectionsManager v0 = ConnectionsManager.Instance[arg3];
        if(v0 == null) {
            Class v1 = ConnectionsManager.class;
            __monitor_enter(v1);
            try {
                v0 = ConnectionsManager.Instance[arg3];
                if(v0 == null) {
                    ConnectionsManager[] v0_1 = ConnectionsManager.Instance;
                    ConnectionsManager v2 = new ConnectionsManager(arg3);
                    v0_1[arg3] = v2;
                    v0 = v2;
                }

                __monitor_exit(v1);
                return v0;
            label_16:
                __monitor_exit(v1);
            }
            catch(Throwable v3) {
                goto label_16;
            }

            throw v3;
        }

        return v0;
    }

    public static NetworkInfo getNetworkInfo(Context arg1) {
        return arg1.getSystemService("connectivity").getActiveNetworkInfo();
    }

    public long getPauseTime() {
        return this.lastPauseTime;
    }

    public int getTimeDifference() {
        return ConnectionsManager.native_getTimeDifference(this.currentAccount);
    }

    public void init(int arg27, int arg28, int arg29, String arg30, String arg31, String arg32, String arg33, String arg34, String arg35, String arg36, int arg37, boolean arg38) {
        ConnectionsManager v0 = this;
        SharedPreferences v1 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        String v6 = v1.getString("proxy_ip", "");
        String v8 = v1.getString("proxy_user", "");
        String v9 = v1.getString("proxy_pass", "");
        String v10 = v1.getString("proxy_secret", "");
        int v7 = v1.getInt("proxy_port", 1080);
        if((v1.getBoolean("proxy_enabled", false)) && !TextUtils.isEmpty(((CharSequence)v6))) {
            ConnectionsManager.native_setProxySettings(v0.currentAccount, v6, v7, v8, v9, v10);
        }

        ConnectionsManager.native_init(v0.currentAccount, arg27, arg28, arg29, arg30, arg31, arg32, arg33, arg34, arg35, arg36, arg37, arg38, ConnectionsManager.isNetworkOnline(), ConnectionsManager.getCurrentNetworkType());
        this.checkConnection();
        ApplicationLoader.applicationContext.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                ConnectionsManager.this.checkConnection();
                FileLoader.getInstance(ConnectionsManager.this.currentAccount).onNetworkChanged(ConnectionsManager.isConnectionSlow());
            }
        }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public boolean isAppPaused() {
        return this.appPaused;
    }

    public static boolean isConnectedMobile(Context arg1) {
        NetworkInfo v1 = ConnectionsManager.getNetworkInfo(arg1);
        boolean v1_1 = v1 == null || !v1.isConnected() || v1.getType() != 0 ? false : true;
        return v1_1;
    }

    public static boolean isConnectedOrConnectingToWiFi() {
        try {
            NetworkInfo v0_1 = ApplicationLoader.applicationContext.getSystemService("connectivity").getNetworkInfo(1);
            NetworkInfo$State v2 = v0_1.getState();
            if(v0_1 == null) {
                return 0;
            }

            if(v2 != NetworkInfo$State.CONNECTED && v2 != NetworkInfo$State.CONNECTING && v2 != NetworkInfo$State.SUSPENDED) {
                return 0;
            }
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            return 0;
        }

        return 1;
    }

    public static boolean isConnectedToWiFi() {
        try {
            NetworkInfo v0_1 = ApplicationLoader.applicationContext.getSystemService("connectivity").getNetworkInfo(1);
            if(v0_1 == null) {
                return 0;
            }

            if(v0_1.getState() != NetworkInfo$State.CONNECTED) {
                return 0;
            }
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            return 0;
        }

        return 1;
    }

    public static boolean isConnectionSlow() {
        try {
            NetworkInfo v0 = ApplicationLoader.applicationContext.getSystemService("connectivity").getActiveNetworkInfo();
            if(v0.getType() == 0) {
                int v0_1 = v0.getSubtype();
                if(v0_1 != 4) {
                    goto label_9;
                }

                return 1;
            }
        }
        catch(Throwable ) {
        }

        return 0;
    label_9:
        if(v0_1 != 7 && v0_1 != 11) {
            switch(v0_1) {
                case 1: 
                case 2: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        return 1;
    }

    public static boolean isNetworkOnline() {
        try {
            Object v1_1 = ApplicationLoader.applicationContext.getSystemService("connectivity");
            NetworkInfo v2 = ((ConnectivityManager)v1_1).getActiveNetworkInfo();
            if(v2 != null && ((v2.isConnectedOrConnecting()) || (v2.isAvailable()))) {
                return 1;
            }

            NetworkInfo v3 = ((ConnectivityManager)v1_1).getNetworkInfo(0);
            if(v3 != null && (v3.isConnectedOrConnecting())) {
                return 1;
            }

            NetworkInfo v1_2 = ((ConnectivityManager)v1_1).getNetworkInfo(1);
            if(v1_2 != null) {
                if(!v1_2.isConnectedOrConnecting()) {
                    return 0;
                }

                return 1;
            }

            return 0;
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
            return 1;
        }

        return 1;
    }

    public static boolean isRoaming() {
        try {
            NetworkInfo v0_1 = ApplicationLoader.applicationContext.getSystemService("connectivity").getActiveNetworkInfo();
            if(v0_1 == null) {
                return 0;
            }

            return v0_1.isRoaming();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }

        return 0;
    }

    public static native void native_applyDatacenterAddress(int arg0, int arg1, String arg2, int arg3) {
    }

    public static native void native_applyDnsConfig(int arg0, long arg1, String arg2) {
    }

    public static native void native_bindRequestToGuid(int arg0, int arg1, int arg2) {
    }

    public static native void native_cancelRequest(int arg0, int arg1, boolean arg2) {
    }

    public static native void native_cancelRequestsForGuid(int arg0, int arg1) {
    }

    public static native long native_checkProxy(int arg0, String arg1, int arg2, String arg3, String arg4, String arg5, RequestTimeDelegate arg6) {
    }

    public static native void native_cleanUp(int arg0, boolean arg1) {
    }

    public static native int native_getConnectionState(int arg0) {
    }

    public static native int native_getCurrentTime(int arg0) {
    }

    public static native long native_getCurrentTimeMillis(int arg0) {
    }

    public static native int native_getTimeDifference(int arg0) {
    }

    public static native void native_init(int arg0, int arg1, int arg2, int arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, int arg11, boolean arg12, boolean arg13, int arg14) {
    }

    public static native int native_isTestBackend(int arg0) {
    }

    public static native void native_pauseNetwork(int arg0) {
    }

    public static native void native_resumeNetwork(int arg0, boolean arg1) {
    }

    public static native void native_sendRequest(int arg0, long arg1, RequestDelegateInternal arg2, QuickAckDelegate arg3, WriteToSocketDelegate arg4, int arg5, int arg6, int arg7, boolean arg8, int arg9) {
    }

    public static native void native_setJava(boolean arg0) {
    }

    public static native void native_setLangCode(int arg0, String arg1) {
    }

    public static native void native_setNetworkAvailable(int arg0, boolean arg1, int arg2, boolean arg3) {
    }

    public static native void native_setProxySettings(int arg0, String arg1, int arg2, String arg3, String arg4, String arg5) {
    }

    public static native void native_setPushConnectionEnabled(int arg0, boolean arg1) {
    }

    public static native void native_setUseIpv6(int arg0, boolean arg1) {
    }

    public static native void native_setUserId(int arg0, int arg1) {
    }

    public static native void native_switchBackend(int arg0) {
    }

    public static native void native_updateDcSettings(int arg0) {
    }

    public static void onBytesReceived(int arg3, int arg4, int arg5) {
        try {
            StatsController.getInstance(arg5).incrementReceivedBytesCount(arg4, 6, ((long)arg3));
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void onBytesSent(int arg3, int arg4, int arg5) {
        try {
            StatsController.getInstance(arg5).incrementSentBytesCount(arg4, 6, ((long)arg3));
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void onConnectionStateChanged(int arg1, int arg2) {
        AndroidUtilities.runOnUIThread(new Runnable(arg2, arg1) {
            public void run() {
                ConnectionsManager.getInstance(this.val$currentAccount).connectionState = this.val$state;
                NotificationCenter.getInstance(this.val$currentAccount).postNotificationName(NotificationCenter.didUpdatedConnectionState, new Object[0]);
            }
        });
    }

    public static void onInternalPushReceived(int arg0) {
        KeepAliveJob.startJob();
    }

    public static void onLogout(int arg1) {
        AndroidUtilities.runOnUIThread(new Runnable(arg1) {
            public void run() {
                if(UserConfig.getInstance(this.val$currentAccount).getClientUserId() != 0) {
                    UserConfig.getInstance(this.val$currentAccount).clearConfig();
                    MessagesController.getInstance(this.val$currentAccount).performLogout(0);
                }
            }
        });
    }

    public static void onProxyError() {
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShowAlert, new Object[]{Integer.valueOf(3)});
            }
        });
    }

    public static void onRequestNewServerIpAndPort(int arg2, int arg3) {
        Utilities.stageQueue.postRunnable(new Runnable(arg2, arg3) {
            public void run() {
                Void[] v2_1;
                Executor v6;
                AzureLoadTask v0;
                if(ConnectionsManager.currentTask == null && (this.val$second != 0 || Math.abs(ConnectionsManager.lastDnsRequestTime - System.currentTimeMillis()) >= 10000) && (ConnectionsManager.isNetworkOnline())) {
                    ConnectionsManager.lastDnsRequestTime = System.currentTimeMillis();
                    int v2 = 3;
                    int v4 = 2;
                    Void v5 = null;
                    if(this.val$second == v4) {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("start azure dns task");
                        }

                        v0 = new AzureLoadTask(this.val$currentAccount);
                        v6 = AsyncTask.THREAD_POOL_EXECUTOR;
                        v2_1 = new Void[v2];
                        v2_1[0] = v5;
                        v2_1[1] = v5;
                        v2_1[v4] = v5;
                        v0.executeOnExecutor(v6, ((Object[])v2_1));
                    }
                    else {
                        if(this.val$second == 1) {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.d("start dns txt task");
                            }

                            DnsTxtLoadTask v0_1 = new DnsTxtLoadTask(this.val$currentAccount);
                            v6 = AsyncTask.THREAD_POOL_EXECUTOR;
                            v2_1 = new Void[v2];
                            v2_1[0] = v5;
                            v2_1[1] = v5;
                            v2_1[v4] = v5;
                            v0_1.executeOnExecutor(v6, ((Object[])v2_1));
                            goto label_35;
                        }

                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("start firebase task");
                        }

                        FirebaseTask v0_2 = new FirebaseTask(this.val$currentAccount);
                        v6 = AsyncTask.THREAD_POOL_EXECUTOR;
                        v2_1 = new Void[v2];
                        v2_1[0] = v5;
                        v2_1[1] = v5;
                        v2_1[v4] = v5;
                        v0_2.executeOnExecutor(v6, ((Object[])v2_1));
                    }

                label_35:
                    ConnectionsManager.currentTask = ((AsyncTask)v0);
                    return;
                }

                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("don\'t start task, current task = " + ConnectionsManager.currentTask + " next task = " + this.val$second + " time diff = " + Math.abs(ConnectionsManager.lastDnsRequestTime - System.currentTimeMillis()) + " network = " + ConnectionsManager.isNetworkOnline());
                }
            }
        });
    }

    public static void onSessionCreated(int arg2) {
        Utilities.stageQueue.postRunnable(new Runnable(arg2) {
            public void run() {
                MessagesController.getInstance(this.val$currentAccount).getDifference();
            }
        });
    }

    public static void onUnparsedMessageReceived(long arg2, int arg4) {
        try {
            NativeByteBuffer v2_1 = NativeByteBuffer.wrap(arg2);
            v2_1.reused = true;
            TLObject v2_2 = TLClassStore.Instance().TLdeserialize(v2_1, v2_1.readInt32(true), true);
            if(!(v2_2 instanceof Updates)) {
                return;
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("java received " + v2_2);
            }

            KeepAliveJob.finishJob();
            Utilities.stageQueue.postRunnable(new Runnable(arg4, v2_2) {
                public void run() {
                    MessagesController.getInstance(this.val$currentAccount).processUpdates(this.val$message, false);
                }
            });
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public static void onUpdate(int arg5) {
        Utilities.stageQueue.postRunnable(new Runnable(arg5) {
            public void run() {
                MessagesController.getInstance(this.val$currentAccount).updateTimerProc();
            }
        });
        if((ConnectionsManager.getInstance(UserConfig.selectedAccount).connectionState == 4 || ConnectionsManager.getInstance(UserConfig.selectedAccount).connectionState == 1) && System.currentTimeMillis() - ConnectionsManager.startServiceTime > 10000) {
            org.telegram.customization.work.a.a();
            ConnectionsManager.startServiceTime = System.currentTimeMillis();
        }

        try {
            if(b.D()) {
                org.telegram.customization.j.a.a v5_1 = new org.telegram.customization.j.a.a();
                if(System.currentTimeMillis() - ConnectionsManager.startVoiceCall > v5_1.sipPrefs.d() && !v5_1.sipWrapper.isInCall()) {
                    ConnectionsManager.startVoiceCall = System.currentTimeMillis();
                    org.telegram.customization.work.a.b();
                }
            }

            CUrl v5_2 = b.v();
            if(v5_2 == null) {
                return;
            }

            if(System.currentTimeMillis() - b.u() <= v5_2.getPrd()) {
                return;
            }

            org.telegram.customization.work.a.c();
        }
        catch(Exception v5) {
            v5.printStackTrace();
        }
    }

    public static void onUpdateConfig(long arg1, int arg3) {
        try {
            NativeByteBuffer v1_1 = NativeByteBuffer.wrap(arg1);
            v1_1.reused = true;
            TL_config v1_2 = TL_config.TLdeserialize(((AbstractSerializedData)v1_1), v1_1.readInt32(true), true);
            if(v1_2 == null) {
                return;
            }

            Utilities.stageQueue.postRunnable(new Runnable(arg3, v1_2) {
                public void run() {
                    MessagesController.getInstance(this.val$currentAccount).updateConfig(this.val$message);
                }
            });
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }
    }

    public void resumeNetworkMaybe() {
        ConnectionsManager.native_resumeNetwork(this.currentAccount, true);
    }

    public int sendRequest(TLObject arg10, RequestDelegate arg11, int arg12) {
        return this.sendRequest(arg10, arg11, null, null, arg12, 2147483647, 1, true);
    }

    public int sendRequest(TLObject arg3, RequestDelegate arg4) {
        return this.sendRequest(arg3, arg4, null, 0);
    }

    public int sendRequest(TLObject arg10, RequestDelegate arg11, QuickAckDelegate arg12, int arg13) {
        return this.sendRequest(arg10, arg11, arg12, null, arg13, 2147483647, 1, true);
    }

    public int sendRequest(TLObject arg16, RequestDelegate arg17, QuickAckDelegate arg18, WriteToSocketDelegate arg19, int arg20, int arg21, int arg22, boolean arg23) {
        int v12 = this.lastRequestToken.getAndIncrement();
        Utilities.stageQueue.postRunnable(new Runnable(arg16, v12, arg17, arg18, arg19, arg20, arg21, arg22, arg23) {
            public void run() {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("send request " + this.val$object + " with token = " + this.val$requestToken);
                }

                try {
                    NativeByteBuffer v0_2 = new NativeByteBuffer(this.val$object.getObjectSize());
                    this.val$object.serializeToStream(((AbstractSerializedData)v0_2));
                    this.val$object.freeResources();
                    ConnectionsManager.native_sendRequest(ConnectionsManager.this.currentAccount, v0_2.address, new RequestDelegateInternal() {
                        public void run(long arg5, int arg7, String arg8, int arg9) {
                            // Method was not decompiled
                        }
                    }, this.val$onQuickAck, this.val$onWriteToSocket, this.val$flags, this.val$datacenterId, this.val$connetionType, this.val$immediate, this.val$requestToken);
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }
        });
        return v12;
    }

    public int sendRequest(TLObject arg10, RequestDelegate arg11, int arg12, int arg13) {
        return this.sendRequest(arg10, arg11, null, null, arg12, 2147483647, arg13, true);
    }

    public void setAppPaused(boolean arg8, boolean arg9) {
        if(!arg9) {
            this.appPaused = arg8;
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("app paused = " + arg8);
            }

            int v9_1 = arg8 ? this.appResumeCount - 1 : this.appResumeCount + 1;
            this.appResumeCount = v9_1;
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("app resume count " + this.appResumeCount);
            }

            if(this.appResumeCount >= 0) {
                goto label_33;
            }

            this.appResumeCount = 0;
        }

    label_33:
        long v1 = 0;
        if(this.appResumeCount == 0) {
            if(this.lastPauseTime == v1) {
                this.lastPauseTime = System.currentTimeMillis();
            }

            ConnectionsManager.native_pauseNetwork(this.currentAccount);
        }
        else {
            if(this.appPaused) {
                return;
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("reset app pause time");
            }

            if(this.lastPauseTime != v1 && System.currentTimeMillis() - this.lastPauseTime > 5000) {
                ContactsController.getInstance(this.currentAccount).checkContacts();
            }

            this.lastPauseTime = v1;
            ConnectionsManager.native_resumeNetwork(this.currentAccount, false);
        }

        if(!arg8) {
            Log.d("LEE", "ConnectionManager getProxyServer");
            e.a(ApplicationLoader.applicationContext);
        }
    }

    public void setIsUpdating(boolean arg2) {
        AndroidUtilities.runOnUIThread(new Runnable(arg2) {
            public void run() {
                if(ConnectionsManager.this.isUpdating == this.val$value) {
                    return;
                }

                ConnectionsManager.this.isUpdating = this.val$value;
                if(ConnectionsManager.this.connectionState == 3) {
                    NotificationCenter.getInstance(ConnectionsManager.this.currentAccount).postNotificationName(NotificationCenter.didUpdatedConnectionState, new Object[0]);
                }
            }
        });
    }

    public static void setLangCode(String arg2) {
        arg2 = arg2.replace('_', '-').toLowerCase();
        int v0;
        for(v0 = 0; v0 < 3; ++v0) {
            ConnectionsManager.native_setLangCode(v0, arg2);
        }
    }

    public static void setProxySettings(boolean arg8, String arg9, int arg10, String arg11, String arg12, String arg13) {
        if(arg9 == null) {
            arg9 = "";
        }

        if(arg11 == null) {
            arg11 = "";
        }

        if(arg12 == null) {
            arg12 = "";
        }

        if(arg13 == null) {
            arg13 = "";
        }

        int v7;
        for(v7 = 0; v7 < 3; ++v7) {
            if(!arg8 || (TextUtils.isEmpty(((CharSequence)arg9)))) {
                ConnectionsManager.native_setProxySettings(v7, "", 1080, "", "", "");
            }
            else {
                ConnectionsManager.native_setProxySettings(v7, arg9, arg10, arg11, arg12, arg13);
            }

            if(UserConfig.getInstance(v7).isClientActivated()) {
                MessagesController.getInstance(v7).checkProxyInfo(true);
            }
        }
    }

    public void setPushConnectionEnabled(boolean arg2) {
        ConnectionsManager.native_setPushConnectionEnabled(this.currentAccount, arg2);
    }

    public void setUserId(int arg2) {
        ConnectionsManager.native_setUserId(this.currentAccount, arg2);
    }

    public void switchBackend() {
        MessagesController.getGlobalMainSettings().edit().remove("language_showed2").commit();
        ConnectionsManager.native_switchBackend(this.currentAccount);
    }

    public void updateDcSettings() {
        ConnectionsManager.native_updateDcSettings(this.currentAccount);
    }

    @SuppressLint(value={"NewApi"}) protected static boolean useIpv6Address() {
        int v6;
        List v4_2;
        Object v4_1;
        int v2_2;
        int v3_1;
        Object v2;
        Enumeration v0_1;
        if(Build$VERSION.SDK_INT < 19) {
            return 0;
        }

        if(!BuildVars.LOGS_ENABLED) {
            goto label_59;
        }

        try {
            v0_1 = NetworkInterface.getNetworkInterfaces();
            while(true) {
            label_8:
                if(!v0_1.hasMoreElements()) {
                    goto label_59;
                }

                v2 = v0_1.nextElement();
                if(!((NetworkInterface)v2).isUp()) {
                    continue;
                }

                if(((NetworkInterface)v2).isLoopback()) {
                    continue;
                }

                if(!((NetworkInterface)v2).getInterfaceAddresses().isEmpty()) {
                    break;
                }
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("valid interface: " + v2);
            }

            List v2_1 = ((NetworkInterface)v2).getInterfaceAddresses();
            v3_1 = 0;
            while(true) {
            label_30:
                if(v3_1 >= v2_1.size()) {
                    goto label_8;
                }

                InetAddress v4 = v2_1.get(v3_1).getAddress();
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("address: " + v4.getHostAddress());
                }

                if(!v4.isLinkLocalAddress() && !v4.isLoopbackAddress()) {
                    if(v4.isMulticastAddress()) {
                    }
                    else if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("address is good");
                    }
                }

                break;
            }
        }
        catch(Throwable v0) {
            goto label_58;
        }

        ++v3_1;
        goto label_30;
    label_58:
        FileLog.e(v0);
        try {
        label_59:
            v0_1 = NetworkInterface.getNetworkInterfaces();
            v2_2 = 0;
            v3_1 = 0;
            while(true) {
            label_62:
                if(!v0_1.hasMoreElements()) {
                    goto label_102;
                }

                v4_1 = v0_1.nextElement();
                if(!((NetworkInterface)v4_1).isUp()) {
                    continue;
                }

                if(!((NetworkInterface)v4_1).isLoopback()) {
                    break;
                }
            }

            v4_2 = ((NetworkInterface)v4_1).getInterfaceAddresses();
            v6 = v3_1;
            v3_1 = v2_2;
            v2_2 = 0;
            goto label_75;
        }
        catch(Throwable v0) {
            goto label_106;
        }

    label_102:
        if(v2_2 != 0) {
            return 0;
        }

        if(v3_1 == 0) {
            return 0;
        }

        return 1;
        try {
            while(true) {
            label_75:
                if(v2_2 >= v4_2.size()) {
                    goto label_99;
                }

                InetAddress v7 = v4_2.get(v2_2).getAddress();
                if(!v7.isLinkLocalAddress() && !v7.isLoopbackAddress()) {
                    if(v7.isMulticastAddress()) {
                    }
                    else if((v7 instanceof Inet6Address)) {
                        v6 = 1;
                    }
                    else if(((v7 instanceof Inet4Address)) && !v7.getHostAddress().startsWith("192.0.0.")) {
                        break;
                    }
                }

                goto label_97;
            }
        }
        catch(Throwable v0) {
            goto label_106;
        }

        v3_1 = 1;
    label_97:
        ++v2_2;
        goto label_75;
    label_99:
        v2_2 = v3_1;
        v3_1 = v6;
        goto label_62;
    label_106:
        FileLog.e(v0);
        return 0;
    }
}

