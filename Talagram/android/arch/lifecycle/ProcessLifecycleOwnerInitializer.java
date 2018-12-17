package android.arch.lifecycle;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class ProcessLifecycleOwnerInitializer extends ContentProvider {
    public ProcessLifecycleOwnerInitializer() {
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
        e.a(this.getContext());
        p.a(this.getContext());
        return 1;
    }

    public Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5) {
        return null;
    }

    public int update(Uri arg1, ContentValues arg2, String arg3, String[] arg4) {
        return 0;
    }
}

