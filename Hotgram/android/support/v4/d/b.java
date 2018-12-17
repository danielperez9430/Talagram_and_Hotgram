package android.support.v4.d;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.v4.f.g;
import android.support.v4.f.l;
import android.support.v4.f.m;
import android.support.v4.graphics.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class b {
    final class android.support.v4.d.b$4 implements Comparator {
        android.support.v4.d.b$4() {
            super();
        }

        public int a(byte[] arg5, byte[] arg6) {
            int v6;
            int v5;
            if(arg5.length != arg6.length) {
                v5 = arg5.length;
                v6 = arg6.length;
            }
            else {
                int v1 = 0;
                while(true) {
                    if(v1 >= arg5.length) {
                        return 0;
                    }
                    else if(arg5[v1] != arg6[v1]) {
                        v5 = arg5[v1];
                        v6 = arg6[v1];
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    goto label_5;
                }

                return 0;
            }

        label_5:
            return v5 - v6;
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((byte[])arg1), ((byte[])arg2));
        }
    }

    public class a {
        private final int a;
        private final android.support.v4.d.b$b[] b;

        public a(int arg1, android.support.v4.d.b$b[] arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public int a() {
            return this.a;
        }

        public android.support.v4.d.b$b[] b() {
            return this.b;
        }
    }

    public class android.support.v4.d.b$b {
        private final Uri a;
        private final int b;
        private final int c;
        private final boolean d;
        private final int e;

        public android.support.v4.d.b$b(Uri arg1, int arg2, int arg3, boolean arg4, int arg5) {
            super();
            this.a = l.a(arg1);
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
        }

        public Uri a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public boolean d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }
    }

    final class c {
        final Typeface a;
        final int b;

        c(Typeface arg1, int arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    static final g a;
    static final Object b;
    static final m c;
    private static final android.support.v4.d.c d;
    private static final Comparator e;

    static {
        b.a = new g(16);
        b.d = new android.support.v4.d.c("fonts", 10, 10000);
        b.b = new Object();
        b.c = new m();
        b.e = new android.support.v4.d.b$4();
    }

    public static ProviderInfo a(PackageManager arg5, android.support.v4.d.a arg6, Resources arg7) {
        String v0 = arg6.a();
        int v1 = 0;
        ProviderInfo v2 = arg5.resolveContentProvider(v0, 0);
        if(v2 != null) {
            if(v2.packageName.equals(arg6.b())) {
                List v5 = b.a(arg5.getPackageInfo(v2.packageName, 64).signatures);
                Collections.sort(v5, b.e);
                List v6 = b.a(arg6, arg7);
                while(v1 < v6.size()) {
                    ArrayList v7 = new ArrayList(v6.get(v1));
                    Collections.sort(((List)v7), b.e);
                    if(b.a(v5, ((List)v7))) {
                        return v2;
                    }

                    ++v1;
                }

                return null;
            }

            StringBuilder v7_1 = new StringBuilder();
            v7_1.append("Found content provider ");
            v7_1.append(v0);
            v7_1.append(", but package was not ");
            v7_1.append(arg6.b());
            throw new PackageManager$NameNotFoundException(v7_1.toString());
        }

        StringBuilder v6_1 = new StringBuilder();
        v6_1.append("No package found for authority: ");
        v6_1.append(v0);
        throw new PackageManager$NameNotFoundException(v6_1.toString());
    }

    private static List a(Signature[] arg3) {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            ((List)v0).add(arg3[v1].toByteArray());
        }

        return ((List)v0);
    }

    private static List a(android.support.v4.d.a arg1, Resources arg2) {
        if(arg1.d() != null) {
            return arg1.d();
        }

        return android.support.v4.content.a.c.a(arg2, arg1.e());
    }

    private static boolean a(List arg4, List arg5) {
        if(arg4.size() != arg5.size()) {
            return 0;
        }

        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            if(!Arrays.equals(arg4.get(v0), arg5.get(v0))) {
                return 0;
            }
        }

        return 1;
    }

    public static Typeface a(Context arg2, android.support.v4.d.a arg3, android.support.v4.content.a.f$a arg4, Handler arg5, boolean arg6, int arg7, int arg8) {
        String v0_1 = arg3.f() + "-" + arg8;
        Object v1 = b.a.get(v0_1);
        if(v1 != null) {
            if(arg4 != null) {
                arg4.a(((Typeface)v1));
            }

            return ((Typeface)v1);
        }

        if((arg6) && arg7 == -1) {
            c v2 = b.a(arg2, arg3, arg8);
            if(arg4 != null) {
                if(v2.b == 0) {
                    arg4.a(v2.a, arg5);
                }
                else {
                    arg4.a(v2.b, arg5);
                }
            }

            return v2.a;
        }

        android.support.v4.d.b$1 v1_1 = new Callable(arg2, arg3, arg8, v0_1) {
            public c a() {
                c v0 = b.a(this.a, this.b, this.c);
                if(v0.a != null) {
                    b.a.put(this.d, v0.a);
                }

                return v0;
            }

            public Object call() {
                return this.a();
            }
        };
        Typeface v2_1 = null;
        if(arg6) {
            try {
                return b.d.a(((Callable)v1_1), arg7).a;
            }
            catch(InterruptedException ) {
                return v2_1;
            }
        }

        android.support.v4.d.b$2 v3 = arg4 == null ? ((android.support.v4.d.b$2)v2_1) : new android.support.v4.d.c$a(arg4, arg5) {
            public void a(c arg3) {
                if(arg3 == null) {
                    this.a.a(1, this.b);
                }
                else if(arg3.b == 0) {
                    this.a.a(arg3.a, this.b);
                }
                else {
                    this.a.a(arg3.b, this.b);
                }
            }

            public void a(Object arg1) {
                this.a(((c)arg1));
            }
        };
        Object v4 = b.b;
        __monitor_enter(v4);
        try {
            if(b.c.containsKey(v0_1)) {
                if(v3 != null) {
                    b.c.get(v0_1).add(v3);
                }

                __monitor_exit(v4);
                return v2_1;
            }

            if(v3 != null) {
                ArrayList v5 = new ArrayList();
                v5.add(v3);
                b.c.put(v0_1, v5);
            }

            __monitor_exit(v4);
        }
        catch(Throwable v2_2) {
            try {
            label_66:
                __monitor_exit(v4);
            }
            catch(Throwable v2_2) {
                goto label_66;
            }

            throw v2_2;
        }

        b.d.a(((Callable)v1_1), new android.support.v4.d.c$a(v0_1) {
            public void a(c arg5) {
                int v0_1;
                Object v1;
                Object v0 = b.b;
                __monitor_enter(v0);
                try {
                    v1 = b.c.get(this.a);
                    if(v1 == null) {
                        __monitor_exit(v0);
                        return;
                    }

                    b.c.remove(this.a);
                    __monitor_exit(v0);
                    v0_1 = 0;
                }
                catch(Throwable v5) {
                    try {
                    label_21:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v5) {
                        goto label_21;
                    }

                    throw v5;
                }

                while(v0_1 < ((ArrayList)v1).size()) {
                    ((ArrayList)v1).get(v0_1).a(arg5);
                    ++v0_1;
                }
            }

            public void a(Object arg1) {
                this.a(((c)arg1));
            }
        });
        return v2_1;
    }

    static c a(Context arg3, android.support.v4.d.a arg4, int arg5) {
        a v4;
        CancellationSignal v0 = null;
        try {
            v4 = b.a(arg3, v0, arg4);
        }
        catch(PackageManager$NameNotFoundException ) {
            return new c(((Typeface)v0), -1);
        }

        int v2 = -3;
        if(v4.a() == 0) {
            Typeface v3 = android.support.v4.graphics.c.a(arg3, v0, v4.b(), arg5);
            if(v3 != null) {
                v2 = 0;
            }

            return new c(v3, v2);
        }

        if(v4.a() == 1) {
            v2 = -2;
        }

        return new c(((Typeface)v0), v2);
    }

    public static a a(Context arg2, CancellationSignal arg3, android.support.v4.d.a arg4) {
        ProviderInfo v0 = b.a(arg2.getPackageManager(), arg4, arg2.getResources());
        if(v0 == null) {
            return new a(1, null);
        }

        return new a(0, b.a(arg2, arg4, v0.authority, arg3));
    }

    static android.support.v4.d.b$b[] a(Context arg20, android.support.v4.d.a arg21, String arg22, CancellationSignal arg23) {
        ArrayList v3_2;
        Cursor v3_1;
        String[] v11_1;
        ContentResolver v3;
        ArrayList v1 = new ArrayList();
        Uri v2 = new Uri$Builder().scheme("content").authority(arg22).build();
        Uri v0 = new Uri$Builder().scheme("content").authority(arg22).appendPath("file").build();
        Cursor v10 = null;
        try {
            int v5 = 6;
            int v6 = 5;
            int v7 = 4;
            int v8 = 3;
            int v9 = 2;
            int v11 = 7;
            if(Build$VERSION.SDK_INT > 16) {
                v3 = arg20.getContentResolver();
                v11_1 = new String[v11];
                v11_1[0] = "_id";
                v11_1[1] = "file_id";
                v11_1[v9] = "font_ttc_index";
                v11_1[v8] = "font_variation_settings";
                v11_1[v7] = "font_weight";
                v11_1[v6] = "font_italic";
                v11_1[v5] = "result_code";
                v3_1 = v3.query(v2, v11_1, "query = ?", new String[]{arg21.c()}, null, arg23);
            }
            else {
                v3 = arg20.getContentResolver();
                v11_1 = new String[v11];
                v11_1[0] = "_id";
                v11_1[1] = "file_id";
                v11_1[v9] = "font_ttc_index";
                v11_1[v8] = "font_variation_settings";
                v11_1[v7] = "font_weight";
                v11_1[v6] = "font_italic";
                v11_1[v5] = "result_code";
                v3_1 = v3.query(v2, v11_1, "query = ?", new String[]{arg21.c()}, null);
            }

            v10 = v3_1;
            if(v10 != null && v10.getCount() > 0) {
                int v1_1 = v10.getColumnIndex("result_code");
                v3_2 = new ArrayList();
                int v4 = v10.getColumnIndex("_id");
                v5 = v10.getColumnIndex("file_id");
                v6 = v10.getColumnIndex("font_ttc_index");
                v7 = v10.getColumnIndex("font_weight");
                v8 = v10.getColumnIndex("font_italic");
                while(true) {
                    if(v10.moveToNext()) {
                        v9 = -1;
                        int v19 = v1_1 != v9 ? v10.getInt(v1_1) : 0;
                        int v16 = v6 != v9 ? v10.getInt(v6) : 0;
                        Uri v11_2 = v5 == v9 ? ContentUris.withAppendedId(v2, v10.getLong(v4)) : ContentUris.withAppendedId(v0, v10.getLong(v5));
                        Uri v15 = v11_2;
                        int v17 = v7 != v9 ? v10.getInt(v7) : 400;
                        boolean v18 = v8 == v9 || v10.getInt(v8) != 1 ? false : true;
                        v3_2.add(new android.support.v4.d.b$b(v15, v16, v17, v18, v19));
                        continue;
                    }
                    else {
                        goto label_137;
                    }
                }
            }

            goto label_138;
        }
        catch(Throwable v0_1) {
            goto label_144;
        }

    label_137:
        v1 = v3_2;
        goto label_138;
    label_144:
        if(v10 != null) {
            v10.close();
        }

        throw v0_1;
    label_138:
        if(v10 != null) {
            v10.close();
        }

        return v1.toArray(new android.support.v4.d.b$b[0]);
    }

    public static Map a(Context arg5, android.support.v4.d.b$b[] arg6, CancellationSignal arg7) {
        HashMap v0 = new HashMap();
        int v1 = arg6.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            android.support.v4.d.b$b v3 = arg6[v2];
            if(v3.e() != 0) {
            }
            else {
                Uri v3_1 = v3.a();
                if(v0.containsKey(v3_1)) {
                }
                else {
                    v0.put(v3_1, i.a(arg5, arg7, v3_1));
                }
            }
        }

        return Collections.unmodifiableMap(((Map)v0));
    }
}

