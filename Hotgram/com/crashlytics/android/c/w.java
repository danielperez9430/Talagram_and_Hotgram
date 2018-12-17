package com.crashlytics.android.c;

import c.a.a.a.a.b.i;
import c.a.a.a.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

class w {
    private static final Charset a;
    private final File b;

    static {
        w.a = Charset.forName("UTF-8");
    }

    public w(File arg1) {
        super();
        this.b = arg1;
    }

    public al a(String arg5) {
        FileInputStream v0_1;
        al v5_3;
        FileInputStream v1;
        File v5 = this.c(arg5);
        if(!v5.exists()) {
            return al.a;
        }

        Closeable v0 = null;
        try {
            v1 = new FileInputStream(v5);
            goto label_8;
        }
        catch(Throwable v5_1) {
        }
        catch(Exception v5_2) {
            goto label_22;
            try {
            label_8:
                v5_3 = w.e(i.a(((InputStream)v1)));
                goto label_10;
            }
            catch(Throwable v5_1) {
                v0_1 = v1;
            }
            catch(Exception v5_2) {
                v0_1 = v1;
                try {
                label_22:
                    c.h().e("CrashlyticsCore", "Error deserializing user metadata.", ((Throwable)v5_2));
                }
                catch(Throwable v5_1) {
                    goto label_30;
                }

                i.a(v0, "Failed to close user metadata file.");
                return al.a;
            }
        }

    label_30:
        i.a(v0, "Failed to close user metadata file.");
        throw v5_1;
    label_10:
        i.a(((Closeable)v1), "Failed to close user metadata file.");
        return v5_3;
    }

    private static String a(JSONObject arg2, String arg3) {
        String v1 = null;
        if(!arg2.isNull(arg3)) {
            v1 = arg2.optString(arg3, v1);
        }

        return v1;
    }

    public Map b(String arg5) {
        FileInputStream v0_1;
        Map v5_3;
        FileInputStream v1;
        File v5 = this.d(arg5);
        if(!v5.exists()) {
            return Collections.emptyMap();
        }

        Closeable v0 = null;
        try {
            v1 = new FileInputStream(v5);
            goto label_8;
        }
        catch(Throwable v5_1) {
        }
        catch(Exception v5_2) {
            goto label_22;
            try {
            label_8:
                v5_3 = w.f(i.a(((InputStream)v1)));
                goto label_10;
            }
            catch(Throwable v5_1) {
                v0_1 = v1;
            }
            catch(Exception v5_2) {
                v0_1 = v1;
                try {
                label_22:
                    c.h().e("CrashlyticsCore", "Error deserializing user metadata.", ((Throwable)v5_2));
                }
                catch(Throwable v5_1) {
                    goto label_30;
                }

                i.a(((Closeable)v0_1), "Failed to close user metadata file.");
                return Collections.emptyMap();
            }
        }

    label_30:
        i.a(((Closeable)v0_1), "Failed to close user metadata file.");
        throw v5_1;
    label_10:
        i.a(((Closeable)v1), "Failed to close user metadata file.");
        return v5_3;
    }

    private File c(String arg4) {
        File v1 = this.b;
        StringBuilder v2 = new StringBuilder();
        v2.append(arg4);
        v2.append("user");
        v2.append(".meta");
        return new File(v1, v2.toString());
    }

    private File d(String arg4) {
        File v1 = this.b;
        StringBuilder v2 = new StringBuilder();
        v2.append(arg4);
        v2.append("keys");
        v2.append(".meta");
        return new File(v1, v2.toString());
    }

    private static al e(String arg3) {
        JSONObject v0 = new JSONObject(arg3);
        return new al(w.a(v0, "userId"), w.a(v0, "userName"), w.a(v0, "userEmail"));
    }

    private static Map f(String arg4) {
        JSONObject v0 = new JSONObject(arg4);
        HashMap v4 = new HashMap();
        Iterator v1 = v0.keys();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            ((Map)v4).put(v2, w.a(v0, ((String)v2)));
        }

        return ((Map)v4);
    }
}

