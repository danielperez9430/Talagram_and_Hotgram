package androidx.work.impl;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import androidx.work.b$a;
import androidx.work.q;

public class WorkManagerInitializer extends ContentProvider {
    public WorkManagerInitializer() {
        super();
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
        q.a(this.getContext(), new a().a());
        return 1;
    }

    public Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5) {
        return null;
    }

    public int update(Uri arg1, ContentValues arg2, String arg3, String[] arg4) {
        return 0;
    }
}

