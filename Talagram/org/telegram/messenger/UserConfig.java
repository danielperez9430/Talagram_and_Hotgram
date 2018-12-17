package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.util.Base64;
import java.io.File;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC$TL_account_tmpPassword;
import org.telegram.tgnet.TLRPC$TL_help_appUpdate;
import org.telegram.tgnet.TLRPC$TL_help_termsOfService;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$help_AppUpdate;

public class UserConfig {
    private static volatile UserConfig[] Instance = null;
    public static final int MAX_ACCOUNT_COUNT = 3;
    public boolean blockedUsersLoaded;
    public int botRatingLoadTime;
    public int clientUserId;
    private boolean configLoaded;
    public boolean contactsReimported;
    public int contactsSavedCount;
    private int currentAccount;
    private User currentUser;
    public long dialogsLoadOffsetAccess;
    public int dialogsLoadOffsetChannelId;
    public int dialogsLoadOffsetChatId;
    public int dialogsLoadOffsetDate;
    public int dialogsLoadOffsetId;
    public int dialogsLoadOffsetUserId;
    public boolean draftsLoaded;
    public boolean hasSecureData;
    public int lastBroadcastId;
    public int lastContactsSyncTime;
    public int lastHintsSyncTime;
    public int lastSendMessageId;
    public long lastUpdateCheckTime;
    public int loginTime;
    public long migrateOffsetAccess;
    public int migrateOffsetChannelId;
    public int migrateOffsetChatId;
    public int migrateOffsetDate;
    public int migrateOffsetId;
    public int migrateOffsetUserId;
    public boolean notificationsSettingsLoaded;
    public TL_help_appUpdate pendingAppUpdate;
    public int pendingAppUpdateBuildVersion;
    public long pendingAppUpdateInstallTime;
    public boolean pinnedDialogsLoaded;
    public int ratingLoadTime;
    public boolean registeredForPush;
    public volatile byte[] savedPasswordHash;
    public volatile long savedPasswordTime;
    public volatile byte[] savedSaltedPassword;
    public static int selectedAccount;
    public boolean suggestContacts;
    private final Object sync;
    public boolean syncContacts;
    public TL_account_tmpPassword tmpPassword;
    public int totalDialogsLoadCount;
    public TL_help_termsOfService unacceptedTermsOfService;
    public boolean unreadDialogsLoaded;

    static {
        UserConfig.Instance = new UserConfig[3];
    }

    public UserConfig(int arg5) {
        super();
        this.sync = new Object();
        this.lastSendMessageId = -210000;
        this.lastBroadcastId = -1;
        this.pinnedDialogsLoaded = true;
        this.unreadDialogsLoaded = true;
        this.migrateOffsetId = -1;
        this.migrateOffsetDate = -1;
        this.migrateOffsetUserId = -1;
        this.migrateOffsetChatId = -1;
        this.migrateOffsetChannelId = -1;
        this.migrateOffsetAccess = -1;
        this.totalDialogsLoadCount = 0;
        this.dialogsLoadOffsetId = 0;
        this.dialogsLoadOffsetDate = 0;
        this.dialogsLoadOffsetUserId = 0;
        this.dialogsLoadOffsetChatId = 0;
        this.dialogsLoadOffsetChannelId = 0;
        this.dialogsLoadOffsetAccess = 0;
        this.syncContacts = true;
        this.suggestContacts = true;
        this.currentAccount = arg5;
    }

    public void checkSavedPassword() {
        if(this.savedSaltedPassword == null && this.savedPasswordHash == null || Math.abs(SystemClock.elapsedRealtime() - this.savedPasswordTime) < 1800000) {
            return;
        }

        this.resetSavedPassword();
    }

    public void clearConfig() {
        this.currentUser = null;
        int v1 = 0;
        this.clientUserId = 0;
        this.registeredForPush = false;
        this.contactsSavedCount = 0;
        this.lastSendMessageId = -210000;
        this.lastBroadcastId = -1;
        this.blockedUsersLoaded = false;
        this.notificationsSettingsLoaded = false;
        this.migrateOffsetId = -1;
        this.migrateOffsetDate = -1;
        this.migrateOffsetUserId = -1;
        this.migrateOffsetChatId = -1;
        this.migrateOffsetChannelId = -1;
        this.migrateOffsetAccess = -1;
        this.dialogsLoadOffsetId = 0;
        this.totalDialogsLoadCount = 0;
        this.dialogsLoadOffsetDate = 0;
        this.dialogsLoadOffsetUserId = 0;
        this.dialogsLoadOffsetChatId = 0;
        this.dialogsLoadOffsetChannelId = 0;
        this.dialogsLoadOffsetAccess = 0;
        this.ratingLoadTime = 0;
        this.botRatingLoadTime = 0;
        this.draftsLoaded = true;
        this.contactsReimported = true;
        this.syncContacts = true;
        this.suggestContacts = true;
        this.pinnedDialogsLoaded = false;
        this.unreadDialogsLoaded = true;
        this.unacceptedTermsOfService = null;
        this.pendingAppUpdate = null;
        this.hasSecureData = false;
        this.loginTime = ((int)(System.currentTimeMillis() / 1000));
        this.lastContactsSyncTime = (((int)(System.currentTimeMillis() / 1000))) - 82800;
        this.lastHintsSyncTime = (((int)(System.currentTimeMillis() / 1000))) - 90000;
        this.resetSavedPassword();
        int v0 = 0;
        while(v0 < 3) {
            if(UserConfig.getInstance(v0).isClientActivated()) {
                v1 = 1;
            }
            else {
                ++v0;
                continue;
            }

            break;
        }

        if(v1 == 0) {
            SharedConfig.clearConfig();
        }

        this.saveConfig(true);
    }

    public static int getActivatedAccountsCount() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < 3) {
            if(UserConfig.getInstance(v0).isClientActivated()) {
                ++v1;
            }

            ++v0;
        }

        return v1;
    }

    public String getClientPhone() {
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            String v1_1 = this.currentUser == null || this.currentUser.phone == null ? "" : this.currentUser.phone;
            __monitor_exit(v0);
            return v1_1;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }

    public int getClientUserId() {
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            int v1_1 = this.currentUser != null ? this.currentUser.id : 0;
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

    public User getCurrentUser() {
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.currentUser;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public static UserConfig getInstance(int arg3) {
        UserConfig v0 = UserConfig.Instance[arg3];
        if(v0 == null) {
            Class v1 = UserConfig.class;
            __monitor_enter(v1);
            try {
                v0 = UserConfig.Instance[arg3];
                if(v0 == null) {
                    UserConfig[] v0_1 = UserConfig.Instance;
                    UserConfig v2 = new UserConfig(arg3);
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

    public int getNewMessageId() {
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            int v1_1 = this.lastSendMessageId;
            --this.lastSendMessageId;
            __monitor_exit(v0);
            return v1_1;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_9;
        }

        throw v1;
    }

    public boolean isClientActivated() {
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            boolean v1_1 = this.currentUser != null ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_10;
        }

        throw v1;
    }

    public void loadConfig() {
        long v9_1;
        byte[] v6_2;
        String v6_1;
        SharedPreferences v1_1;
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            if(this.configLoaded) {
                __monitor_exit(v0);
                return;
            }

            if(this.currentAccount == 0) {
                v1_1 = ApplicationLoader.applicationContext.getSharedPreferences("userconfing", 0);
                UserConfig.selectedAccount = v1_1.getInt("selectedAccount", 0);
            }
            else {
                Context v1_2 = ApplicationLoader.applicationContext;
                StringBuilder v3 = new StringBuilder();
                v3.append("userconfig");
                v3.append(this.currentAccount);
                v1_1 = v1_2.getSharedPreferences(v3.toString(), 0);
            }

            this.registeredForPush = v1_1.getBoolean("registeredForPush", false);
            this.lastSendMessageId = v1_1.getInt("lastSendMessageId", -210000);
            this.contactsSavedCount = v1_1.getInt("contactsSavedCount", 0);
            int v4 = -1;
            this.lastBroadcastId = v1_1.getInt("lastBroadcastId", v4);
            this.blockedUsersLoaded = v1_1.getBoolean("blockedUsersLoaded", false);
            this.lastContactsSyncTime = v1_1.getInt("lastContactsSyncTime", (((int)(System.currentTimeMillis() / 1000))) - 82800);
            this.lastHintsSyncTime = v1_1.getInt("lastHintsSyncTime", (((int)(System.currentTimeMillis() / 1000))) - 90000);
            this.draftsLoaded = v1_1.getBoolean("draftsLoaded", false);
            this.pinnedDialogsLoaded = v1_1.getBoolean("pinnedDialogsLoaded", false);
            this.unreadDialogsLoaded = v1_1.getBoolean("unreadDialogsLoaded", false);
            this.contactsReimported = v1_1.getBoolean("contactsReimported", false);
            this.ratingLoadTime = v1_1.getInt("ratingLoadTime", 0);
            this.botRatingLoadTime = v1_1.getInt("botRatingLoadTime", 0);
            this.loginTime = v1_1.getInt("loginTime", this.currentAccount);
            this.syncContacts = v1_1.getBoolean("syncContacts", true);
            this.suggestContacts = v1_1.getBoolean("suggestContacts", true);
            this.hasSecureData = v1_1.getBoolean("hasSecureData", false);
            this.notificationsSettingsLoaded = v1_1.getBoolean("notificationsSettingsLoaded", false);
            String v3_1 = null;
            try {
                v6_1 = v1_1.getString("terms", v3_1);
                if(v6_1 == null) {
                    goto label_109;
                }

                v6_2 = Base64.decode(v6_1, 0);
                if(v6_2 == null) {
                    goto label_109;
                }

                SerializedData v7 = new SerializedData(v6_2);
                this.unacceptedTermsOfService = TL_help_termsOfService.TLdeserialize(((AbstractSerializedData)v7), v7.readInt32(false), false);
                v7.cleanup();
                goto label_109;
            }
            catch(Exception v6) {
                try {
                    FileLog.e(((Throwable)v6));
                label_109:
                    long v7_1 = 0;
                    if(this.currentAccount != 0) {
                        goto label_161;
                    }

                    this.lastUpdateCheckTime = v1_1.getLong("appUpdateCheckTime", System.currentTimeMillis());
                    try {
                        v6_1 = v1_1.getString("appUpdate", v3_1);
                        if(v6_1 != null) {
                            this.pendingAppUpdateBuildVersion = v1_1.getInt("appUpdateBuild", BuildVars.BUILD_VERSION);
                            this.pendingAppUpdateInstallTime = v1_1.getLong("appUpdateTime", System.currentTimeMillis());
                            v6_2 = Base64.decode(v6_1, 0);
                            if(v6_2 != null) {
                                SerializedData v9 = new SerializedData(v6_2);
                                this.pendingAppUpdate = help_AppUpdate.TLdeserialize(((AbstractSerializedData)v9), v9.readInt32(false), false);
                                v9.cleanup();
                            }
                        }

                        if(this.pendingAppUpdate == null) {
                            goto label_161;
                        }
                    }
                    catch(Exception v6) {
                        goto label_160;
                    }

                    try {
                        PackageInfo v6_3 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);
                        v9_1 = Math.max(v6_3.lastUpdateTime, v6_3.firstInstallTime);
                        goto label_149;
                    }
                    catch(Exception v6) {
                        try {
                            FileLog.e(((Throwable)v6));
                            v9_1 = v7_1;
                        label_149:
                            if(this.pendingAppUpdateBuildVersion == BuildVars.BUILD_VERSION && this.pendingAppUpdateInstallTime >= v9_1) {
                                goto label_161;
                            }

                            this.pendingAppUpdate = ((TL_help_appUpdate)v3_1);
                            AndroidUtilities.runOnUIThread(new Runnable() {
                                public void run() {
                                    UserConfig.this.saveConfig(false);
                                }
                            });
                            goto label_161;
                        }
                        catch(Throwable v1) {
                        label_235:
                            throw v1;
                        }
                        catch(Exception v6) {
                            try {
                            label_160:
                                FileLog.e(((Throwable)v6));
                            label_161:
                                this.migrateOffsetId = v1_1.getInt("3migrateOffsetId", 0);
                                if(this.migrateOffsetId != v4) {
                                    this.migrateOffsetDate = v1_1.getInt("3migrateOffsetDate", 0);
                                    this.migrateOffsetUserId = v1_1.getInt("3migrateOffsetUserId", 0);
                                    this.migrateOffsetChatId = v1_1.getInt("3migrateOffsetChatId", 0);
                                    this.migrateOffsetChannelId = v1_1.getInt("3migrateOffsetChannelId", 0);
                                    this.migrateOffsetAccess = v1_1.getLong("3migrateOffsetAccess", v7_1);
                                }

                                this.dialogsLoadOffsetId = v1_1.getInt("2dialogsLoadOffsetId", v4);
                                this.totalDialogsLoadCount = v1_1.getInt("2totalDialogsLoadCount", 0);
                                this.dialogsLoadOffsetDate = v1_1.getInt("2dialogsLoadOffsetDate", v4);
                                this.dialogsLoadOffsetUserId = v1_1.getInt("2dialogsLoadOffsetUserId", v4);
                                this.dialogsLoadOffsetChatId = v1_1.getInt("2dialogsLoadOffsetChatId", v4);
                                this.dialogsLoadOffsetChannelId = v1_1.getInt("2dialogsLoadOffsetChannelId", v4);
                                this.dialogsLoadOffsetAccess = v1_1.getLong("2dialogsLoadOffsetAccess", -1);
                                String v4_1 = v1_1.getString("tmpPassword", v3_1);
                                if(v4_1 != null) {
                                    byte[] v4_2 = Base64.decode(v4_1, 0);
                                    if(v4_2 != null) {
                                        SerializedData v6_4 = new SerializedData(v4_2);
                                        this.tmpPassword = TL_account_tmpPassword.TLdeserialize(((AbstractSerializedData)v6_4), v6_4.readInt32(false), false);
                                        v6_4.cleanup();
                                    }
                                }

                                String v1_3 = v1_1.getString("user", v3_1);
                                if(v1_3 != null) {
                                    byte[] v1_4 = Base64.decode(v1_3, 0);
                                    if(v1_4 != null) {
                                        SerializedData v3_2 = new SerializedData(v1_4);
                                        this.currentUser = User.TLdeserialize(((AbstractSerializedData)v3_2), v3_2.readInt32(false), false);
                                        v3_2.cleanup();
                                    }
                                }

                                if(this.currentUser != null) {
                                    this.clientUserId = this.currentUser.id;
                                }

                                this.configLoaded = true;
                                __monitor_exit(v0);
                                return;
                            label_234:
                                __monitor_exit(v0);
                                goto label_235;
                            }
                            catch(Throwable v1) {
                                goto label_234;
                            }
                        }
                    }
                }
                catch(Throwable v1) {
                    goto label_234;
                }
            }
        }
        catch(Throwable v1) {
            goto label_234;
        }
    }

    public void resetSavedPassword() {
        this.savedPasswordTime = 0;
        byte[] v1 = null;
        if(this.savedPasswordHash != null) {
            int v0;
            for(v0 = 0; v0 < this.savedPasswordHash.length; ++v0) {
                this.savedPasswordHash[v0] = 0;
            }

            this.savedPasswordHash = v1;
        }

        if(this.savedSaltedPassword != null) {
            for(v0 = 0; v0 < this.savedSaltedPassword.length; ++v0) {
                this.savedSaltedPassword[v0] = 0;
            }

            this.savedSaltedPassword = v1;
        }
    }

    public void saveConfig(boolean arg2) {
        this.saveConfig(arg2, null);
    }

    public void saveConfig(boolean arg8, File arg9) {
        SerializedData v3_2;
        SharedPreferences$Editor v1_2;
        String v3;
        Context v1;
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            if(this.currentAccount == 0) {
                v1 = ApplicationLoader.applicationContext;
                v3 = "userconfing";
            }
            else {
                v1 = ApplicationLoader.applicationContext;
                v3 = "userconfig" + this.currentAccount;
            }

            SharedPreferences v1_1 = v1.getSharedPreferences(v3, 0);
            v1_2 = v1_1.edit();
            if(this.currentAccount == 0) {
                v1_2.putInt("selectedAccount", UserConfig.selectedAccount);
            }

            v1_2.putBoolean("registeredForPush", this.registeredForPush);
            v1_2.putInt("lastSendMessageId", this.lastSendMessageId);
            v1_2.putInt("contactsSavedCount", this.contactsSavedCount);
            v1_2.putInt("lastBroadcastId", this.lastBroadcastId);
            v1_2.putBoolean("blockedUsersLoaded", this.blockedUsersLoaded);
            v1_2.putInt("lastContactsSyncTime", this.lastContactsSyncTime);
            v1_2.putInt("lastHintsSyncTime", this.lastHintsSyncTime);
            v1_2.putBoolean("draftsLoaded", this.draftsLoaded);
            v1_2.putBoolean("pinnedDialogsLoaded", this.pinnedDialogsLoaded);
            v1_2.putBoolean("unreadDialogsLoaded", this.unreadDialogsLoaded);
            v1_2.putInt("ratingLoadTime", this.ratingLoadTime);
            v1_2.putInt("botRatingLoadTime", this.botRatingLoadTime);
            v1_2.putBoolean("contactsReimported", this.contactsReimported);
            v1_2.putInt("loginTime", this.loginTime);
            v1_2.putBoolean("syncContacts", this.syncContacts);
            v1_2.putBoolean("suggestContacts", this.suggestContacts);
            v1_2.putBoolean("hasSecureData", this.hasSecureData);
            v1_2.putBoolean("notificationsSettingsLoaded", this.notificationsSettingsLoaded);
            v1_2.putInt("3migrateOffsetId", this.migrateOffsetId);
            if(this.migrateOffsetId != -1) {
                v1_2.putInt("3migrateOffsetDate", this.migrateOffsetDate);
                v1_2.putInt("3migrateOffsetUserId", this.migrateOffsetUserId);
                v1_2.putInt("3migrateOffsetChatId", this.migrateOffsetChatId);
                v1_2.putInt("3migrateOffsetChannelId", this.migrateOffsetChannelId);
                v1_2.putLong("3migrateOffsetAccess", this.migrateOffsetAccess);
            }

            if(this.unacceptedTermsOfService != null) {
            }
            else {
                goto label_113;
            }
        }
        catch(Exception v8) {
            goto label_200;
        }

        try {
            v3_2 = new SerializedData(this.unacceptedTermsOfService.getObjectSize());
            this.unacceptedTermsOfService.serializeToStream(((AbstractSerializedData)v3_2));
            v1_2.putString("terms", Base64.encodeToString(v3_2.toByteArray(), 0));
            v3_2.cleanup();
        }
        catch(Exception ) {
        }

        goto label_115;
        try {
        label_113:
            v1_2.remove("terms");
        label_115:
            if(this.currentAccount == 0) {
                if(this.pendingAppUpdate != null) {
                    goto label_119;
                }
                else {
                    goto label_140;
                }
            }

            goto label_142;
        }
        catch(Exception v8) {
            goto label_200;
        }

        try {
        label_119:
            v3_2 = new SerializedData(this.pendingAppUpdate.getObjectSize());
            this.pendingAppUpdate.serializeToStream(((AbstractSerializedData)v3_2));
            v1_2.putString("appUpdate", Base64.encodeToString(v3_2.toByteArray(), 0));
            v1_2.putInt("appUpdateBuild", this.pendingAppUpdateBuildVersion);
            v1_2.putLong("appUpdateTime", this.pendingAppUpdateInstallTime);
            v1_2.putLong("appUpdateCheckTime", this.lastUpdateCheckTime);
            v3_2.cleanup();
        }
        catch(Exception ) {
        }

        goto label_142;
        try {
        label_140:
            v1_2.remove("appUpdate");
        label_142:
            v1_2.putInt("2totalDialogsLoadCount", this.totalDialogsLoadCount);
            v1_2.putInt("2dialogsLoadOffsetId", this.dialogsLoadOffsetId);
            v1_2.putInt("2dialogsLoadOffsetDate", this.dialogsLoadOffsetDate);
            v1_2.putInt("2dialogsLoadOffsetUserId", this.dialogsLoadOffsetUserId);
            v1_2.putInt("2dialogsLoadOffsetChatId", this.dialogsLoadOffsetChatId);
            v1_2.putInt("2dialogsLoadOffsetChannelId", this.dialogsLoadOffsetChannelId);
            v1_2.putLong("2dialogsLoadOffsetAccess", this.dialogsLoadOffsetAccess);
            SharedConfig.saveConfig();
            if(this.tmpPassword != null) {
                v3_2 = new SerializedData();
                this.tmpPassword.serializeToStream(((AbstractSerializedData)v3_2));
                v1_2.putString("tmpPassword", Base64.encodeToString(v3_2.toByteArray(), 0));
                v3_2.cleanup();
            }
            else {
                v1_2.remove("tmpPassword");
            }

            if(this.currentUser == null) {
                v1_2.remove("user");
            }
            else if(arg8) {
                SerializedData v8_1 = new SerializedData();
                this.currentUser.serializeToStream(((AbstractSerializedData)v8_1));
                v1_2.putString("user", Base64.encodeToString(v8_1.toByteArray(), 0));
                v8_1.cleanup();
            }

            v1_2.commit();
            if(arg9 == null) {
                goto label_201;
            }

            arg9.delete();
            goto label_201;
        }
        catch(Exception v8) {
            try {
            label_200:
                FileLog.e(((Throwable)v8));
            label_201:
                __monitor_exit(v0);
                return;
            }
            catch(Throwable v8_2) {
            label_198:
                try {
                    __monitor_exit(v0);
                }
                catch(Throwable v8_2) {
                    goto label_198;
                }

                throw v8_2;
            }
        }
    }

    public void savePassword(byte[] arg3, byte[] arg4) {
        this.savedPasswordTime = SystemClock.elapsedRealtime();
        this.savedPasswordHash = arg3;
        this.savedSaltedPassword = arg4;
    }

    public void setCurrentUser(User arg2) {
        Object v0 = this.sync;
        __monitor_enter(v0);
        try {
            this.currentUser = arg2;
            this.clientUserId = arg2.id;
            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_8;
        }

        throw v2;
    }
}

