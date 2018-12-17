package c.a.a.a;

import android.os.SystemClock;
import android.text.TextUtils;
import c.a.a.a.a.b.i;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class e implements Callable {
    final String a;

    e(String arg1) {
        super();
        this.a = arg1;
    }

    private k a(ZipEntry arg7, ZipFile arg8) {
        k v4;
        Closeable v8_1;
        InputStream v8;
        k v0 = null;
        try {
            v8 = arg8.getInputStream(arg7);
        }
        catch(Throwable v7) {
            v8_1 = ((Closeable)v0);
            goto label_50;
        }
        catch(IOException v1) {
            v8_1 = ((Closeable)v0);
            goto label_38;
        }

        try {
            Properties v1_1 = new Properties();
            v1_1.load(v8);
            String v2 = v1_1.getProperty("fabric-identifier");
            String v3 = v1_1.getProperty("fabric-version");
            String v1_2 = v1_1.getProperty("fabric-build-type");
            if(!TextUtils.isEmpty(((CharSequence)v2)) && !TextUtils.isEmpty(((CharSequence)v3))) {
                v4 = new k(v2, v3, v1_2);
                goto label_17;
            }

            goto label_19;
        }
        catch(Throwable v7) {
            goto label_30;
        }
        catch(IOException v1) {
            goto label_32;
        }

    label_17:
        i.a(((Closeable)v8));
        return v4;
        try {
        label_19:
            StringBuilder v2_1 = new StringBuilder();
            v2_1.append("Invalid format of fabric file,");
            v2_1.append(arg7.getName());
            throw new IllegalStateException(v2_1.toString());
        }
        catch(Throwable v7) {
        label_30:
        }
        catch(IOException v1) {
        label_32:
            try {
            label_38:
                l v2_2 = c.h();
                v2_2.e("Fabric", "Error when parsing fabric properties " + arg7.getName(), ((Throwable)v1));
            }
            catch(Throwable v7) {
                goto label_30;
            }

            i.a(((Closeable)v8));
            return v0;
        }

    label_50:
        i.a(((Closeable)v8));
        throw v7;
    }

    public Map a() {
        HashMap v0 = new HashMap();
        long v1 = SystemClock.elapsedRealtime();
        ((Map)v0).putAll(this.c());
        ((Map)v0).putAll(this.d());
        l v3 = c.h();
        v3.b("Fabric", "finish scanning in " + (SystemClock.elapsedRealtime() - v1));
        return ((Map)v0);
    }

    protected ZipFile b() {
        return new ZipFile(this.a);
    }

    private Map c() {
        HashMap v0 = new HashMap();
        try {
            Class.forName("com.google.android.gms.ads.AdView");
            k v1 = new k("com.google.firebase.firebase-ads", "0.0.0", "binary");
            ((Map)v0).put(v1.a(), v1);
            c.h().b("Fabric", "Found kit: com.google.firebase.firebase-ads");
            goto label_15;
        }
        catch(Exception ) {
        label_15:
            return ((Map)v0);
        }
    }

    public Object call() {
        return this.a();
    }

    private Map d() {
        HashMap v0 = new HashMap();
        ZipFile v1 = this.b();
        Enumeration v2 = v1.entries();
        while(v2.hasMoreElements()) {
            Object v3 = v2.nextElement();
            if(!((ZipEntry)v3).getName().startsWith("fabric/")) {
                continue;
            }

            if(((ZipEntry)v3).getName().length() <= "fabric/".length()) {
                continue;
            }

            k v3_1 = this.a(((ZipEntry)v3), v1);
            if(v3_1 == null) {
                continue;
            }

            ((Map)v0).put(v3_1.a(), v3_1);
            c.h().b("Fabric", String.format("Found kit:[%s] version:[%s]", v3_1.a(), v3_1.b()));
        }

        if(v1 != null) {
            try {
                v1.close();
                goto label_36;
            }
            catch(IOException ) {
            label_36:
                return ((Map)v0);
            }
        }

        goto label_36;
    }
}

