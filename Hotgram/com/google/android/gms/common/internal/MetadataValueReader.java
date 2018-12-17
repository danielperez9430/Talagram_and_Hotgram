package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.concurrent.GuardedBy;

public class MetadataValueReader {
    public static final String KEY_METADATA_APP_ID = "com.google.app.id";
    private static Object sLock;
    @GuardedBy(value="sLock") private static boolean zzui;
    private static String zzuj;
    private static int zzuk;

    static {
        MetadataValueReader.sLock = new Object();
    }

    public MetadataValueReader() {
        super();
    }

    public static String getGoogleAppId(Context arg0) {
        MetadataValueReader.zze(arg0);
        return MetadataValueReader.zzuj;
    }

    public static int getGooglePlayServicesVersion(Context arg0) {
        MetadataValueReader.zze(arg0);
        return MetadataValueReader.zzuk;
    }

    @VisibleForTesting public static void resetForTesting() {
        Object v0 = MetadataValueReader.sLock;
        __monitor_enter(v0);
        try {
            MetadataValueReader.zzui = false;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    @VisibleForTesting public static void setValuesForTesting(String arg1, int arg2) {
        Object v0 = MetadataValueReader.sLock;
        __monitor_enter(v0);
        try {
            MetadataValueReader.zzuj = arg1;
            MetadataValueReader.zzuk = arg2;
            MetadataValueReader.zzui = true;
            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_9;
        }

        throw v1;
    }

    private static void zze(Context arg3) {
        Bundle v3_3;
        Object v0 = MetadataValueReader.sLock;
        __monitor_enter(v0);
        try {
            if(MetadataValueReader.zzui) {
                __monitor_exit(v0);
                return;
            }

            MetadataValueReader.zzui = true;
            String v1 = arg3.getPackageName();
            PackageManagerWrapper v3_1 = Wrappers.packageManager(arg3);
            int v2 = 128;
            try {
                v3_3 = v3_1.getApplicationInfo(v1, v2).metaData;
                if(v3_3 != null) {
                    goto label_16;
                }
            }
            catch(PackageManager$NameNotFoundException v3_2) {
                goto label_24;
            }

            __monitor_exit(v0);
            return;
            try {
            label_16:
                MetadataValueReader.zzuj = v3_3.getString("com.google.app.id");
                MetadataValueReader.zzuk = v3_3.getInt("com.google.android.gms.version");
                goto label_27;
            }
            catch(PackageManager$NameNotFoundException v3_2) {
                try {
                label_24:
                    Log.wtf("MetadataValueReader", "This should never happen.", ((Throwable)v3_2));
                label_27:
                    __monitor_exit(v0);
                    return;
                label_30:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_30;
                }
            }
        }
        catch(Throwable v3) {
            goto label_30;
        }

        throw v3;
    }
}

