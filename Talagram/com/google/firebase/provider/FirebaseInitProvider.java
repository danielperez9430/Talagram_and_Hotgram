package com.google.firebase.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.b;

public class FirebaseInitProvider extends ContentProvider {
    public FirebaseInitProvider() {
        super();
    }

    public void attachInfo(Context arg3, ProviderInfo arg4) {
        Preconditions.checkNotNull(arg4, "FirebaseInitProvider ProviderInfo cannot be null.");
        if(!"com.google.firebase.firebaseinitprovider".equals(arg4.authority)) {
            super.attachInfo(arg3, arg4);
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
        String v1;
        String v0;
        if(b.a(this.getContext()) == null) {
            v0 = "FirebaseInitProvider";
            v1 = "FirebaseApp initialization unsuccessful";
        }
        else {
            v0 = "FirebaseInitProvider";
            v1 = "FirebaseApp initialization successful";
        }

        Log.i(v0, v1);
        return 0;
    }

    public Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5) {
        return null;
    }

    public int update(Uri arg1, ContentValues arg2, String arg3, String[] arg4) {
        return 0;
    }
}

