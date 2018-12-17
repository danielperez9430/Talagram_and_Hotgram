package com.d.a.c;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public final class e {
    public static File a(Context arg1) {
        return e.a(arg1, true);
    }

    public static File a(Context arg2, boolean arg3) {
        String v0;
        try {
            v0 = Environment.getExternalStorageState();
        }
        catch(IncompatibleClassChangeError ) {
            v0 = "";
        }

        File v3 = !arg3 || !"mounted".equals(v0) || !e.d(arg2) ? null : e.c(arg2);
        if(v3 == null) {
            v3 = arg2.getCacheDir();
        }

        if(v3 == null) {
            String v2 = "/data/data/" + arg2.getPackageName() + "/cache/";
            c.c("Can\'t define system cache directory! \'%s\' will be used.", new Object[]{v2});
            v3 = new File(v2);
        }

        return v3;
    }

    public static File a(Context arg1, String arg2) {
        File v1 = e.a(arg1);
        File v0 = new File(v1, arg2);
        if((v0.exists()) || (v0.mkdir())) {
            v1 = v0;
        }
        else {
        }

        return v1;
    }

    public static File b(Context arg1) {
        return e.a(arg1, "uil-images");
    }

    private static File c(Context arg4) {
        File v1 = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), arg4.getPackageName()), "cache");
        if(!v1.exists()) {
            if(!v1.mkdirs()) {
                c.c("Unable to create external cache directory", new Object[0]);
                return null;
            }

            try {
                new File(v1, ".nomedia").createNewFile();
            }
            catch(IOException ) {
                c.b("Can\'t create \".nomedia\" file in application external cache directory", new Object[0]);
            }
        }

        return v1;
    }

    private static boolean d(Context arg1) {
        boolean v1 = arg1.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0 ? true : false;
        return v1;
    }
}

