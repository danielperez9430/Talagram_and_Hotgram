package com.google.android.gms.measurement;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import com.google.android.gms.measurement.internal.zzbt;

@Deprecated public class AppMeasurementContentProvider extends ContentProvider {
    public AppMeasurementContentProvider() {
        super();
    }

    public void attachInfo(Context arg1, ProviderInfo arg2) {
        super.attachInfo(arg1, arg2);
        if(!"com.google.android.gms.measurement.google_measurement_service".equals(arg2.authority)) {
            return;
        }

        throw new IllegalStateException("Incorrect provider authority in manifest. Most likely due to a missing applicationId variable in application\'s build.gradle.");
    }

    public int delete(Uri arg1, String arg2, String[] arg3) {
        return 0;
    }

    public String getType(Uri arg1) {
        return null;
    }

    public Uri insert(Uri arg1, ContentValues arg2) {
        return null;
    }

    public boolean onCreate() {
        zzbt.zza(this.getContext(), null);
        return 0;
    }

    public Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5) {
        return null;
    }

    public int update(Uri arg1, ContentValues arg2, String arg3, String[] arg4) {
        return 0;
    }
}

