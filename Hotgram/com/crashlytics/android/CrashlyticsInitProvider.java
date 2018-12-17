package com.crashlytics.android;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import c.a.a.a.a.b.o;
import c.a.a.a.c;
import c.a.a.a.i;

public class CrashlyticsInitProvider extends ContentProvider {
    interface a {
        boolean a(Context arg1);
    }

    public CrashlyticsInitProvider() {
        super();
    }

    boolean a(Context arg1, o arg2, a arg3) {
        boolean v1 = !arg2.b(arg1) || !arg3.a(arg1) ? false : true;
        return v1;
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
        Context v0 = this.getContext();
        if(this.a(v0, new o(), new b())) {
            try {
                c.a(v0, new i[]{new com.crashlytics.android.a()});
                c.h().c("CrashlyticsInitProvider", "CrashlyticsInitProvider initialization successful");
            }
            catch(IllegalStateException ) {
                c.h().c("CrashlyticsInitProvider", "CrashlyticsInitProvider initialization unsuccessful");
                return 0;
            }
        }

        return 1;
    }

    public Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5) {
        return null;
    }

    public int update(Uri arg1, ContentValues arg2, String arg3, String[] arg4) {
        return 0;
    }
}

