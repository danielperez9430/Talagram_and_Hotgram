package org.telegram.messenger;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;

public class SharedConfig {
    public class ProxyInfo {
        public String address;
        public boolean available;
        public long availableCheckTime;
        public boolean checking;
        public String password;
        public long ping;
        public int port;
        public long proxyCheckPingId;
        public String secret;
        public String username;

        public ProxyInfo(String arg1, int arg2, String arg3, String arg4, String arg5) {
            super();
            this.address = arg1;
            this.port = arg2;
            this.username = arg3;
            this.password = arg4;
            this.secret = arg5;
            if(this.address == null) {
                this.address = "";
            }

            if(this.password == null) {
                this.password = "";
            }

            if(this.username == null) {
                this.username = "";
            }

            if(this.secret == null) {
                this.secret = "";
            }
        }
    }

    public static boolean allowBigEmoji = false;
    public static boolean allowScreenCapture = false;
    public static boolean appLocked = false;
    public static int autoLockIn = 0;
    public static boolean autoplayGifs = false;
    public static int badPasscodeTries = 0;
    private static boolean configLoaded = false;
    public static ProxyInfo currentProxy = null;
    public static boolean customTabs = false;
    public static boolean directShare = false;
    public static int fontSize = 0;
    public static boolean groupPhotosEnabled = false;
    public static boolean hasCameraCache = false;
    public static boolean inappCamera = false;
    public static boolean isWaitingForPasscodeEnter = false;
    public static long lastAppPauseTime = 0;
    private static int lastLocalId = 0;
    public static int lastPauseTime = 0;
    public static String lastUpdateVersion = null;
    public static long lastUptimeMillis = 0;
    private static final Object localIdSync = null;
    public static int mapPreviewType = 0;
    public static String passcodeHash = "";
    public static long passcodeRetryInMs = 0;
    public static byte[] passcodeSalt = null;
    public static int passcodeType = 0;
    public static int passportConfigHash = 0;
    private static String passportConfigJson = null;
    private static HashMap passportConfigMap = null;
    public static boolean playOrderReversed = false;
    public static ArrayList proxyList = null;
    private static boolean proxyListLoaded = false;
    public static byte[] pushAuthKey = null;
    public static byte[] pushAuthKeyId = null;
    public static String pushString = "";
    public static boolean raiseToSpeak;
    public static int repeatMode;
    public static boolean roundCamera16to9;
    public static boolean saveIncomingPhotos;
    public static boolean saveStreamMedia;
    public static boolean saveToGallery;
    public static boolean shuffleMusic;
    public static boolean streamAllVideo;
    public static boolean streamMedia;
    public static int suggestStickers;
    private static final Object sync;
    public static boolean useFingerprint;
    public static boolean useSystemEmoji;

    static {
        SharedConfig.passcodeSalt = new byte[0];
        SharedConfig.autoLockIn = 3600;
        SharedConfig.useFingerprint = true;
        SharedConfig.lastLocalId = -210000;
        SharedConfig.passportConfigJson = "";
        SharedConfig.sync = new Object();
        SharedConfig.localIdSync = new Object();
        SharedConfig.mapPreviewType = 2;
        SharedConfig.autoplayGifs = true;
        SharedConfig.raiseToSpeak = true;
        SharedConfig.customTabs = true;
        SharedConfig.directShare = true;
        SharedConfig.inappCamera = true;
        SharedConfig.roundCamera16to9 = true;
        SharedConfig.groupPhotosEnabled = true;
        SharedConfig.streamMedia = true;
        SharedConfig.streamAllVideo = false;
        SharedConfig.saveStreamMedia = true;
        SharedConfig.fontSize = AndroidUtilities.dp(16f);
        SharedConfig.loadConfig();
        SharedConfig.proxyList = new ArrayList();
    }

    public SharedConfig() {
        super();
    }

    public static ProxyInfo addProxy(ProxyInfo arg5) {
        SharedConfig.loadProxyList();
        int v0 = SharedConfig.proxyList.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = SharedConfig.proxyList.get(v1);
            if((arg5.address.equals(((ProxyInfo)v2).address)) && arg5.port == ((ProxyInfo)v2).port && (arg5.username.equals(((ProxyInfo)v2).username)) && (arg5.password.equals(((ProxyInfo)v2).password)) && (arg5.secret.equals(((ProxyInfo)v2).secret))) {
                return ((ProxyInfo)v2);
            }
        }

        SharedConfig.proxyList.add(arg5);
        SharedConfig.saveProxyList();
        return arg5;
    }

    public static boolean checkPasscode(String arg5) {
        byte[] v5_1;
        int v1 = 16;
        if(SharedConfig.passcodeSalt.length == 0) {
            boolean v0 = Utilities.MD5(arg5).equals(SharedConfig.passcodeHash);
            if(v0) {
                try {
                    SharedConfig.passcodeSalt = new byte[v1];
                    Utilities.random.nextBytes(SharedConfig.passcodeSalt);
                    v5_1 = arg5.getBytes("UTF-8");
                    byte[] v3 = new byte[v5_1.length + 32];
                    System.arraycopy(SharedConfig.passcodeSalt, 0, v3, 0, v1);
                    System.arraycopy(v5_1, 0, v3, v1, v5_1.length);
                    System.arraycopy(SharedConfig.passcodeSalt, 0, v3, v5_1.length + v1, v1);
                    SharedConfig.passcodeHash = Utilities.bytesToHex(Utilities.computeSHA256(v3, 0, v3.length));
                    SharedConfig.saveConfig();
                }
                catch(Exception v5) {
                    FileLog.e(((Throwable)v5));
                }
            }

            return v0;
        }

        try {
            v5_1 = arg5.getBytes("UTF-8");
            byte[] v0_1 = new byte[v5_1.length + 32];
            System.arraycopy(SharedConfig.passcodeSalt, 0, v0_1, 0, v1);
            System.arraycopy(v5_1, 0, v0_1, v1, v5_1.length);
            System.arraycopy(SharedConfig.passcodeSalt, 0, v0_1, v5_1.length + v1, v1);
            return SharedConfig.passcodeHash.equals(Utilities.bytesToHex(Utilities.computeSHA256(v0_1, 0, v0_1.length)));
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
            return 0;
        }
    }

    public static void checkSaveToGalleryFiles() {
        try {
            File v0_1 = new File(Environment.getExternalStorageDirectory(), "Telegram");
            File v1 = new File(v0_1, "Telegram Images");
            v1.mkdir();
            File v2 = new File(v0_1, "Telegram Video");
            v2.mkdir();
            if(SharedConfig.saveToGallery) {
                if(v1.isDirectory()) {
                    new File(v1, ".nomedia").delete();
                }

                if(!v2.isDirectory()) {
                    return;
                }

                new File(v2, ".nomedia").delete();
                return;
            }

            if(v1.isDirectory()) {
                new File(v1, ".nomedia").createNewFile();
            }

            if(!v2.isDirectory()) {
                return;
            }

            new File(v2, ".nomedia").createNewFile();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void clearConfig() {
        SharedConfig.saveIncomingPhotos = false;
        SharedConfig.appLocked = false;
        SharedConfig.passcodeType = 0;
        SharedConfig.passcodeRetryInMs = 0;
        SharedConfig.lastUptimeMillis = 0;
        SharedConfig.badPasscodeTries = 0;
        SharedConfig.passcodeHash = "";
        SharedConfig.passcodeSalt = new byte[0];
        SharedConfig.autoLockIn = 3600;
        SharedConfig.lastPauseTime = 0;
        SharedConfig.useFingerprint = true;
        SharedConfig.isWaitingForPasscodeEnter = false;
        SharedConfig.allowScreenCapture = false;
        SharedConfig.lastUpdateVersion = BuildVars.BUILD_VERSION_STRING;
        SharedConfig.saveConfig();
    }

    public static void deleteProxy(ProxyInfo arg10) {
        if(SharedConfig.currentProxy == arg10) {
            SharedConfig.currentProxy = null;
            SharedPreferences v0 = MessagesController.getGlobalMainSettings();
            boolean v1 = v0.getBoolean("proxy_enabled", false);
            SharedPreferences$Editor v0_1 = v0.edit();
            v0_1.putString("proxy_ip", "");
            v0_1.putString("proxy_pass", "");
            v0_1.putString("proxy_user", "");
            v0_1.putString("proxy_secret", "");
            v0_1.putInt("proxy_port", 1080);
            v0_1.putBoolean("proxy_enabled", false);
            v0_1.putBoolean("proxy_enabled_calls", false);
            v0_1.commit();
            if(v1) {
                ConnectionsManager.setProxySettings(false, "", 0, "", "", "");
            }
        }

        SharedConfig.proxyList.remove(arg10);
        SharedConfig.saveProxyList();
    }

    public static HashMap getCountryLangs() {
        if(SharedConfig.passportConfigMap == null) {
            SharedConfig.passportConfigMap = new HashMap();
            try {
                JSONObject v0_1 = new JSONObject(SharedConfig.passportConfigJson);
                Iterator v1 = v0_1.keys();
                while(v1.hasNext()) {
                    Object v2 = v1.next();
                    SharedConfig.passportConfigMap.put(((String)v2).toUpperCase(), v0_1.getString(((String)v2)).toUpperCase());
                }
            }
            catch(Throwable v0) {
                FileLog.e(v0);
            }
        }

        return SharedConfig.passportConfigMap;
    }

    public static int getLastLocalId() {
        Object v0 = SharedConfig.localIdSync;
        __monitor_enter(v0);
        try {
            int v1_1 = SharedConfig.lastLocalId;
            SharedConfig.lastLocalId = v1_1 - 1;
            __monitor_exit(v0);
            return v1_1;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    public static void increaseBadPasscodeTries() {
        long v0;
        ++SharedConfig.badPasscodeTries;
        if(SharedConfig.badPasscodeTries >= 3) {
            switch(SharedConfig.badPasscodeTries) {
                case 3: {
                    v0 = 5000;
                    break;
                }
                case 4: {
                    v0 = 10000;
                    break;
                }
                case 5: {
                    v0 = 15000;
                    break;
                }
                case 6: {
                    v0 = 20000;
                    break;
                }
                case 7: {
                    v0 = 25000;
                    break;
                }
                default: {
                    v0 = 30000;
                    break;
                }
            }

            SharedConfig.passcodeRetryInMs = v0;
            SharedConfig.lastUptimeMillis = SystemClock.elapsedRealtime();
        }

        SharedConfig.saveConfig();
    }

    public static boolean isPassportConfigLoaded() {
        boolean v0 = SharedConfig.passportConfigMap != null ? true : false;
        return v0;
    }

    public static boolean isSecretMapPreviewSet() {
        return MessagesController.getGlobalMainSettings().contains("mapPreviewType");
    }

    public static void loadConfig() {
        Object v0 = SharedConfig.sync;
        __monitor_enter(v0);
        try {
            if(SharedConfig.configLoaded) {
                __monitor_exit(v0);
                return;
            }

            SharedPreferences v1_1 = ApplicationLoader.applicationContext.getSharedPreferences("userconfing", 0);
            SharedConfig.saveIncomingPhotos = v1_1.getBoolean("saveIncomingPhotos", false);
            SharedConfig.passcodeHash = v1_1.getString("passcodeHash1", "");
            SharedConfig.appLocked = v1_1.getBoolean("appLocked", false);
            SharedConfig.passcodeType = v1_1.getInt("passcodeType", 0);
            SharedConfig.passcodeRetryInMs = v1_1.getLong("passcodeRetryInMs", 0);
            SharedConfig.lastUptimeMillis = v1_1.getLong("lastUptimeMillis", 0);
            SharedConfig.badPasscodeTries = v1_1.getInt("badPasscodeTries", 0);
            SharedConfig.autoLockIn = v1_1.getInt("autoLockIn", 3600);
            SharedConfig.lastPauseTime = v1_1.getInt("lastPauseTime", 0);
            SharedConfig.lastAppPauseTime = v1_1.getLong("lastAppPauseTime", 0);
            SharedConfig.useFingerprint = v1_1.getBoolean("useFingerprint", true);
            SharedConfig.lastUpdateVersion = v1_1.getString("lastUpdateVersion2", "3.5");
            SharedConfig.allowScreenCapture = v1_1.getBoolean("allowScreenCapture", false);
            SharedConfig.lastLocalId = v1_1.getInt("lastLocalId", -210000);
            SharedConfig.pushString = v1_1.getString("pushString2", "");
            SharedConfig.passportConfigJson = v1_1.getString("passportConfigJson", "");
            SharedConfig.passportConfigHash = v1_1.getInt("passportConfigHash", 0);
            String v2 = v1_1.getString("pushAuthKey", null);
            if(!TextUtils.isEmpty(((CharSequence)v2))) {
                SharedConfig.pushAuthKey = Base64.decode(v2, 0);
            }

            if(SharedConfig.passcodeHash.length() > 0 && SharedConfig.lastPauseTime == 0) {
                SharedConfig.lastPauseTime = ((int)(System.currentTimeMillis() / 1000 - 600));
            }

            String v1_2 = v1_1.getString("passcodeSalt", "");
            byte[] v1_3 = v1_2.length() > 0 ? Base64.decode(v1_2, 0) : new byte[0];
            SharedConfig.passcodeSalt = v1_3;
            v1_1 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            SharedConfig.saveToGallery = v1_1.getBoolean("save_gallery", false);
            SharedConfig.autoplayGifs = v1_1.getBoolean("autoplay_gif", true);
            SharedConfig.mapPreviewType = v1_1.getInt("mapPreviewType", 2);
            SharedConfig.raiseToSpeak = v1_1.getBoolean("raise_to_speak", true);
            SharedConfig.customTabs = v1_1.getBoolean("custom_tabs", true);
            SharedConfig.directShare = v1_1.getBoolean("direct_share", true);
            SharedConfig.shuffleMusic = v1_1.getBoolean("shuffleMusic", false);
            SharedConfig.playOrderReversed = v1_1.getBoolean("playOrderReversed", false);
            SharedConfig.inappCamera = v1_1.getBoolean("inappCamera", true);
            SharedConfig.hasCameraCache = v1_1.contains("cameraCache");
            SharedConfig.roundCamera16to9 = true;
            SharedConfig.groupPhotosEnabled = v1_1.getBoolean("groupPhotosEnabled", true);
            SharedConfig.repeatMode = v1_1.getInt("repeatMode", 0);
            v2 = "fons_size";
            int v5 = AndroidUtilities.isTablet() ? 18 : 16;
            SharedConfig.fontSize = v1_1.getInt(v2, v5);
            SharedConfig.allowBigEmoji = v1_1.getBoolean("allowBigEmoji", false);
            SharedConfig.useSystemEmoji = v1_1.getBoolean("useSystemEmoji", false);
            SharedConfig.streamMedia = v1_1.getBoolean("streamMedia", true);
            SharedConfig.saveStreamMedia = v1_1.getBoolean("saveStreamMedia", true);
            SharedConfig.streamAllVideo = v1_1.getBoolean("streamAllVideo", BuildVars.DEBUG_VERSION);
            SharedConfig.suggestStickers = v1_1.getInt("suggestStickers", 0);
            SharedConfig.configLoaded = true;
            __monitor_exit(v0);
            return;
        label_170:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_170;
        }

        throw v1;
    }

    public static void loadProxyList() {
        if(SharedConfig.proxyListLoaded) {
            return;
        }

        SharedPreferences v0 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        String v5 = v0.getString("proxy_ip", "");
        String v7 = v0.getString("proxy_user", "");
        String v8 = v0.getString("proxy_pass", "");
        String v9 = v0.getString("proxy_secret", "");
        int v6 = v0.getInt("proxy_port", 1080);
        SharedConfig.proxyListLoaded = true;
        SharedConfig.proxyList.clear();
        SharedConfig.currentProxy = null;
        String v0_1 = v0.getString("proxy_list", null);
        if(!TextUtils.isEmpty(((CharSequence)v0_1))) {
            SerializedData v1 = new SerializedData(Base64.decode(v0_1, 0));
            int v0_2 = v1.readInt32(false);
            int v3;
            for(v3 = 0; v3 < v0_2; ++v3) {
                ProxyInfo v4 = new ProxyInfo(v1.readString(false), v1.readInt32(false), v1.readString(false), v1.readString(false), v1.readString(false));
                SharedConfig.proxyList.add(v4);
                if(SharedConfig.currentProxy == null && !TextUtils.isEmpty(((CharSequence)v5)) && (v5.equals(v4.address)) && v6 == v4.port && (v7.equals(v4.username)) && (v8.equals(v4.password))) {
                    SharedConfig.currentProxy = v4;
                }
            }

            v1.cleanup();
        }

        if(SharedConfig.currentProxy == null && !TextUtils.isEmpty(((CharSequence)v5))) {
            ProxyInfo v0_3 = new ProxyInfo(v5, v6, v7, v8, v9);
            SharedConfig.currentProxy = v0_3;
            SharedConfig.proxyList.add(0, v0_3);
        }
    }

    public static void saveConfig() {
        Object v0 = SharedConfig.sync;
        __monitor_enter(v0);
        try {
            SharedPreferences$Editor v1_2 = ApplicationLoader.applicationContext.getSharedPreferences("userconfing", 0).edit();
            v1_2.putBoolean("saveIncomingPhotos", SharedConfig.saveIncomingPhotos);
            v1_2.putString("passcodeHash1", SharedConfig.passcodeHash);
            String v2 = "passcodeSalt";
            String v4 = SharedConfig.passcodeSalt.length > 0 ? Base64.encodeToString(SharedConfig.passcodeSalt, 0) : "";
            v1_2.putString(v2, v4);
            v1_2.putBoolean("appLocked", SharedConfig.appLocked);
            v1_2.putInt("passcodeType", SharedConfig.passcodeType);
            v1_2.putLong("passcodeRetryInMs", SharedConfig.passcodeRetryInMs);
            v1_2.putLong("lastUptimeMillis", SharedConfig.lastUptimeMillis);
            v1_2.putInt("badPasscodeTries", SharedConfig.badPasscodeTries);
            v1_2.putInt("autoLockIn", SharedConfig.autoLockIn);
            v1_2.putInt("lastPauseTime", SharedConfig.lastPauseTime);
            v1_2.putLong("lastAppPauseTime", SharedConfig.lastAppPauseTime);
            v1_2.putString("lastUpdateVersion2", SharedConfig.lastUpdateVersion);
            v1_2.putBoolean("useFingerprint", SharedConfig.useFingerprint);
            v1_2.putBoolean("allowScreenCapture", SharedConfig.allowScreenCapture);
            v1_2.putString("pushString2", SharedConfig.pushString);
            v2 = "pushAuthKey";
            String v3 = SharedConfig.pushAuthKey != null ? Base64.encodeToString(SharedConfig.pushAuthKey, 0) : "";
            v1_2.putString(v2, v3);
            v1_2.putInt("lastLocalId", SharedConfig.lastLocalId);
            v1_2.putString("passportConfigJson", SharedConfig.passportConfigJson);
            v1_2.putInt("passportConfigHash", SharedConfig.passportConfigHash);
            v1_2.commit();
            goto label_81;
        }
        catch(Throwable v1) {
        }
        catch(Exception v1_1) {
            try {
                FileLog.e(((Throwable)v1_1));
            label_81:
                __monitor_exit(v0);
                return;
            }
            catch(Throwable v1) {
            label_78:
                try {
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_78;
                }

                throw v1;
            }
        }
    }

    public static void saveProxyList() {
        SerializedData v0 = new SerializedData();
        int v1 = SharedConfig.proxyList.size();
        v0.writeInt32(v1);
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = SharedConfig.proxyList.get(v3);
            String v5 = ((ProxyInfo)v4).address != null ? ((ProxyInfo)v4).address : "";
            v0.writeString(v5);
            v0.writeInt32(((ProxyInfo)v4).port);
            v5 = ((ProxyInfo)v4).username != null ? ((ProxyInfo)v4).username : "";
            v0.writeString(v5);
            v5 = ((ProxyInfo)v4).password != null ? ((ProxyInfo)v4).password : "";
            v0.writeString(v5);
            String v4_1 = ((ProxyInfo)v4).secret != null ? ((ProxyInfo)v4).secret : "";
            v0.writeString(v4_1);
        }

        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putString("proxy_list", Base64.encodeToString(v0.toByteArray(), 2)).commit();
        v0.cleanup();
    }

    public static void setPassportConfig(String arg1, int arg2) {
        SharedConfig.passportConfigMap = null;
        SharedConfig.passportConfigJson = arg1;
        SharedConfig.passportConfigHash = arg2;
        SharedConfig.saveConfig();
        SharedConfig.getCountryLangs();
    }

    public static void setSecretMapPreviewType(int arg2) {
        SharedConfig.mapPreviewType = arg2;
        SharedPreferences$Editor v2 = MessagesController.getGlobalMainSettings().edit();
        v2.putInt("mapPreviewType", SharedConfig.mapPreviewType);
        v2.commit();
    }

    public static void setSuggestStickers(int arg2) {
        SharedConfig.suggestStickers = arg2;
        SharedPreferences$Editor v2 = MessagesController.getGlobalMainSettings().edit();
        v2.putInt("suggestStickers", SharedConfig.suggestStickers);
        v2.commit();
    }

    public static void toggleAutoplayGifs() {
        SharedConfig.autoplayGifs ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("autoplay_gif", SharedConfig.autoplayGifs);
        v0.commit();
    }

    public static void toggleCustomTabs() {
        SharedConfig.customTabs ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("custom_tabs", SharedConfig.customTabs);
        v0.commit();
    }

    public static void toggleDirectShare() {
        SharedConfig.directShare ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("direct_share", SharedConfig.directShare);
        v0.commit();
    }

    public static void toggleGroupPhotosEnabled() {
        SharedConfig.groupPhotosEnabled ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("groupPhotosEnabled", SharedConfig.groupPhotosEnabled);
        v0.commit();
    }

    public static void toggleInappCamera() {
        SharedConfig.inappCamera ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("inappCamera", SharedConfig.inappCamera);
        v0.commit();
    }

    public static void toggleRepeatMode() {
        ++SharedConfig.repeatMode;
        if(SharedConfig.repeatMode > 2) {
            SharedConfig.repeatMode = 0;
        }

        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putInt("repeatMode", SharedConfig.repeatMode);
        v0.commit();
    }

    public static void toggleRoundCamera16to9() {
        SharedConfig.roundCamera16to9 ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("roundCamera16to9", SharedConfig.roundCamera16to9);
        v0.commit();
    }

    public static void toggleSaveStreamMedia() {
        SharedConfig.saveStreamMedia ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("saveStreamMedia", SharedConfig.saveStreamMedia);
        v0.commit();
    }

    public static void toggleSaveToGallery() {
        SharedConfig.saveToGallery ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("save_gallery", SharedConfig.saveToGallery);
        v0.commit();
        SharedConfig.checkSaveToGalleryFiles();
    }

    public static void toggleShuffleMusic(int arg2) {
        if(arg2 == 2) {
            SharedConfig.shuffleMusic ^= 1;
        }
        else {
            SharedConfig.playOrderReversed ^= 1;
        }

        MediaController.getInstance().checkIsNextMediaFileDownloaded();
        SharedPreferences$Editor v2 = MessagesController.getGlobalMainSettings().edit();
        v2.putBoolean("shuffleMusic", SharedConfig.shuffleMusic);
        v2.putBoolean("playOrderReversed", SharedConfig.playOrderReversed);
        v2.commit();
    }

    public static void toggleStreamAllVideo() {
        SharedConfig.streamAllVideo ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("streamAllVideo", SharedConfig.streamAllVideo);
        v0.commit();
    }

    public static void toggleStreamMedia() {
        SharedConfig.streamMedia ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("streamMedia", SharedConfig.streamMedia);
        v0.commit();
    }

    public static void toogleRaiseToSpeak() {
        SharedConfig.raiseToSpeak ^= 1;
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putBoolean("raise_to_speak", SharedConfig.raiseToSpeak);
        v0.commit();
    }
}

