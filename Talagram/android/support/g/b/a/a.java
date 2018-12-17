package android.support.g.b.a;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

public final class a {
    private static final String[] a;

    static {
        a.a = new String[0];
    }

    public static void a(EditorInfo arg2, String[] arg3) {
        if(Build$VERSION.SDK_INT >= 25) {
            arg2.contentMimeTypes = arg3;
        }
        else {
            if(arg2.extras == null) {
                arg2.extras = new Bundle();
            }

            arg2.extras.putStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", arg3);
        }
    }

    public static String[] a(EditorInfo arg2) {
        String[] v2;
        if(Build$VERSION.SDK_INT >= 25) {
            v2 = arg2.contentMimeTypes;
            if(v2 != null) {
            }
            else {
                v2 = a.a;
            }

            return v2;
        }

        if(arg2.extras == null) {
            return a.a;
        }

        v2 = arg2.extras.getStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
        if(v2 != null) {
        }
        else {
            v2 = a.a;
        }

        return v2;
    }
}

