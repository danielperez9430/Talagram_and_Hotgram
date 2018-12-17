package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.R$string;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.MetadataValueReader;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk @Deprecated public final class GoogleServices {
    private static final Object sLock;
    @GuardedBy(value="sLock") private static GoogleServices zzku;
    private final String zzkv;
    private final Status zzkw;
    private final boolean zzkx;
    private final boolean zzky;

    static {
        GoogleServices.sLock = new Object();
    }

    @KeepForSdk @VisibleForTesting GoogleServices(Context arg5) {
        super();
        Resources v0 = arg5.getResources();
        int v1 = v0.getIdentifier("google_app_measurement_enable", "integer", v0.getResourcePackageName(string.common_google_play_services_unknown_issue));
        int v3 = 1;
        if(v1 != 0) {
            if(v0.getInteger(v1) != 0) {
            }
            else {
                v3 = 0;
            }

            this.zzky = v3 ^ 1;
        }
        else {
            this.zzky = false;
        }

        this.zzkx = ((boolean)v3);
        String v0_1 = MetadataValueReader.getGoogleAppId(arg5);
        if(v0_1 == null) {
            v0_1 = new StringResourceValueReader(arg5).getString("google_app_id");
        }

        if(TextUtils.isEmpty(((CharSequence)v0_1))) {
            this.zzkw = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzkv = null;
            return;
        }

        this.zzkv = v0_1;
        this.zzkw = Status.RESULT_SUCCESS;
    }

    @KeepForSdk @VisibleForTesting GoogleServices(String arg1, boolean arg2) {
        super();
        this.zzkv = arg1;
        this.zzkw = Status.RESULT_SUCCESS;
        this.zzkx = arg2;
        this.zzky = (((int)arg2)) ^ 1;
    }

    @KeepForSdk @VisibleForTesting final Status checkGoogleAppId(String arg5) {
        if(this.zzkv != null && !this.zzkv.equals(arg5)) {
            String v1 = this.zzkv;
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 97);
            v3.append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: \'");
            v3.append(v1);
            v3.append("\'.");
            return new Status(10, v3.toString());
        }

        return Status.RESULT_SUCCESS;
    }

    @KeepForSdk private static GoogleServices checkInitialized(String arg4) {
        Object v0 = GoogleServices.sLock;
        __monitor_enter(v0);
        try {
            if(GoogleServices.zzku != null) {
                __monitor_exit(v0);
                return GoogleServices.zzku;
            }

            StringBuilder v3 = new StringBuilder(String.valueOf(arg4).length() + 34);
            v3.append("Initialize must be called before ");
            v3.append(arg4);
            v3.append(".");
            throw new IllegalStateException(v3.toString());
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_22;
        }

        throw v4;
    }

    @KeepForSdk @VisibleForTesting static void clearInstanceForTest() {
        Object v0 = GoogleServices.sLock;
        __monitor_enter(v0);
        GoogleServices v1 = null;
        try {
            GoogleServices.zzku = v1;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1_1) {
            goto label_7;
        }

        throw v1_1;
    }

    @KeepForSdk public static String getGoogleAppId() {
        return GoogleServices.checkInitialized("getGoogleAppId").zzkv;
    }

    @KeepForSdk public static Status initialize(Context arg2) {
        Preconditions.checkNotNull(arg2, "Context must not be null.");
        Object v0 = GoogleServices.sLock;
        __monitor_enter(v0);
        try {
            if(GoogleServices.zzku == null) {
                GoogleServices.zzku = new GoogleServices(arg2);
            }

            __monitor_exit(v0);
            return GoogleServices.zzku.zzkw;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_14;
        }

        throw v2;
    }

    @KeepForSdk public static Status initialize(Context arg1, String arg2, boolean arg3) {
        Preconditions.checkNotNull(arg1, "Context must not be null.");
        Preconditions.checkNotEmpty(arg2, "App ID must be nonempty.");
        Object v1 = GoogleServices.sLock;
        __monitor_enter(v1);
        try {
            if(GoogleServices.zzku != null) {
                __monitor_exit(v1);
                return GoogleServices.zzku.checkGoogleAppId(arg2);
            }

            GoogleServices v0 = new GoogleServices(arg2, arg3);
            GoogleServices.zzku = v0;
            __monitor_exit(v1);
            return v0.zzkw;
        label_19:
            __monitor_exit(v1);
        }
        catch(Throwable v2) {
            goto label_19;
        }

        throw v2;
    }

    @KeepForSdk public static boolean isMeasurementEnabled() {
        GoogleServices v0 = GoogleServices.checkInitialized("isMeasurementEnabled");
        if((v0.zzkw.isSuccess()) && (v0.zzkx)) {
            return 1;
        }

        return 0;
    }

    @KeepForSdk public static boolean isMeasurementExplicitlyDisabled() {
        return GoogleServices.checkInitialized("isMeasurementExplicitlyDisabled").zzky;
    }
}

