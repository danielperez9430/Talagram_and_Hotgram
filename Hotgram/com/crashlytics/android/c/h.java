package com.crashlytics.android.c;

import android.app.Activity;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import c.a.a.a.a.b.p;
import c.a.a.a.a.g.o;
import c.a.a.a.a.g.t;
import c.a.a.a.i;
import c.a.a.a.l;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

class h {
    final class com.crashlytics.android.c.h$10 implements FilenameFilter {
        com.crashlytics.android.c.h$10() {
            super();
        }

        public boolean accept(File arg2, String arg3) {
            boolean v2 = arg3.length() != ".cls".length() + 35 || !arg3.endsWith(".cls") ? false : true;
            return v2;
        }
    }

    final class com.crashlytics.android.c.h$17 implements FileFilter {
        com.crashlytics.android.c.h$17() {
            super();
        }

        public boolean accept(File arg2) {
            boolean v2 = !arg2.isDirectory() || arg2.getName().length() != 35 ? false : true;
            return v2;
        }
    }

    final class com.crashlytics.android.c.h$18 implements Comparator {
        com.crashlytics.android.c.h$18() {
            super();
        }

        public int a(File arg1, File arg2) {
            return arg2.getName().compareTo(arg1.getName());
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((File)arg1), ((File)arg2));
        }
    }

    final class com.crashlytics.android.c.h$19 implements Comparator {
        com.crashlytics.android.c.h$19() {
            super();
        }

        public int a(File arg1, File arg2) {
            return arg1.getName().compareTo(arg2.getName());
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((File)arg1), ((File)arg2));
        }
    }

    final class com.crashlytics.android.c.h$1 extends c {
        com.crashlytics.android.c.h$1(String arg1) {
            super(arg1);
        }

        public boolean accept(File arg1, String arg2) {
            boolean v1 = !super.accept(arg1, arg2) || !arg2.endsWith(".cls") ? false : true;
            return v1;
        }
    }

    class a implements FilenameFilter {
        a(com.crashlytics.android.c.h$1 arg1) {
            this();
        }

        private a() {
            super();
        }

        public boolean accept(File arg2, String arg3) {
            boolean v2 = (h.b.accept(arg2, arg3)) || !h.j().matcher(((CharSequence)arg3)).matches() ? false : true;
            return v2;
        }
    }

    interface b {
        void a(e arg1);
    }

    class c implements FilenameFilter {
        private final String a;

        public c(String arg1) {
            super();
            this.a = arg1;
        }

        public boolean accept(File arg1, String arg2) {
            boolean v1 = !arg2.contains(this.a) || (arg2.endsWith(".cls_temp")) ? false : true;
            return v1;
        }
    }

    interface d {
        void a(FileOutputStream arg1);
    }

    class com.crashlytics.android.c.h$e implements FilenameFilter {
        com.crashlytics.android.c.h$e() {
            super();
        }

        public boolean accept(File arg2, String arg3) {
            boolean v2 = (com.crashlytics.android.c.d.a.accept(arg2, arg3)) || (arg3.contains("SessionMissingBinaryImages")) ? true : false;
            return v2;
        }
    }

    final class f implements com.crashlytics.android.c.u$a {
        private final c.a.a.a.a.f.a a;

        public f(c.a.a.a.a.f.a arg1) {
            super();
            this.a = arg1;
        }

        public File a() {
            File v0 = new File(this.a.a(), "log-files");
            if(!v0.exists()) {
                v0.mkdirs();
            }

            return v0;
        }
    }

    final class g implements com.crashlytics.android.c.af$d {
        private final i a;
        private final ab b;
        private final o c;

        public g(i arg1, ab arg2, o arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        static ab a(g arg0) {
            return arg0.b;
        }

        public boolean a() {
            Activity v0 = this.a.r().b();
            if(v0 != null) {
                if(v0.isFinishing()) {
                }
                else {
                    com.crashlytics.android.c.f v1 = com.crashlytics.android.c.f.a(v0, this.c, new com.crashlytics.android.c.f$a() {
                        public void a(boolean arg2) {
                            g.a(this.a).a(arg2);
                        }
                    });
                    v0.runOnUiThread(new Runnable(v1) {
                        public void run() {
                            this.a.a();
                        }
                    });
                    c.a.a.a.c.h().a("CrashlyticsCore", "Waiting for user opt-in.");
                    v1.b();
                    return v1.c();
                }
            }

            return 1;
        }
    }

    final class com.crashlytics.android.c.h$h implements com.crashlytics.android.c.af$c {
        com.crashlytics.android.c.h$h(h arg1, com.crashlytics.android.c.h$1 arg2) {
            this(arg1);
        }

        private com.crashlytics.android.c.h$h(h arg1) {
            this.a = arg1;
            super();
        }

        public File[] a() {
            return this.a.b();
        }

        public File[] b() {
            return this.a.i().listFiles();
        }
    }

    final class com.crashlytics.android.c.h$i implements com.crashlytics.android.c.af$b {
        com.crashlytics.android.c.h$i(h arg1, com.crashlytics.android.c.h$1 arg2) {
            this(arg1);
        }

        private com.crashlytics.android.c.h$i(h arg1) {
            this.a = arg1;
            super();
        }

        public boolean a() {
            return this.a.e();
        }
    }

    final class j implements Runnable {
        private final Context a;
        private final ae b;
        private final af c;

        public j(Context arg1, ae arg2, af arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public void run() {
            if(!c.a.a.a.a.b.i.n(this.a)) {
                return;
            }

            c.a.a.a.c.h().a("CrashlyticsCore", "Attempting to send crash report at time of crash...");
            this.c.a(this.b);
        }
    }

    class k implements FilenameFilter {
        private final String a;

        public k(String arg1) {
            super();
            this.a = arg1;
        }

        public boolean accept(File arg2, String arg3) {
            StringBuilder v2 = new StringBuilder();
            v2.append(this.a);
            v2.append(".cls");
            boolean v0 = false;
            if(arg3.equals(v2.toString())) {
                return 0;
            }

            if((arg3.contains(this.a)) && !arg3.endsWith(".cls_temp")) {
                v0 = true;
            }

            return v0;
        }
    }

    static final FilenameFilter a;
    static final FilenameFilter b;
    static final FileFilter c;
    static final Comparator d;
    static final Comparator e;
    private static final Pattern f;
    private static final Map g;
    private static final String[] h;
    private final AtomicInteger i;
    private final com.crashlytics.android.c.i j;
    private final com.crashlytics.android.c.g k;
    private final c.a.a.a.a.e.e l;
    private final p m;
    private final ab n;
    private final c.a.a.a.a.f.a o;
    private final com.crashlytics.android.c.a p;
    private final f q;
    private final u r;
    private final com.crashlytics.android.c.af$c s;
    private final com.crashlytics.android.c.af$b t;
    private final q u;
    private final ai v;
    private final String w;
    private final com.crashlytics.android.a.q x;
    private final boolean y;
    private m z;

    static {
        h.a = new com.crashlytics.android.c.h$1("BeginSession");
        h.b = new com.crashlytics.android.c.h$10();
        h.c = new com.crashlytics.android.c.h$17();
        h.d = new com.crashlytics.android.c.h$18();
        h.e = new com.crashlytics.android.c.h$19();
        h.f = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
        h.g = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
        h.h = new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"};
    }

    h(com.crashlytics.android.c.i arg3, com.crashlytics.android.c.g arg4, c.a.a.a.a.e.e arg5, p arg6, ab arg7, c.a.a.a.a.f.a arg8, com.crashlytics.android.c.a arg9, ak arg10, boolean arg11) {
        super();
        this.i = new AtomicInteger(0);
        this.j = arg3;
        this.k = arg4;
        this.l = arg5;
        this.m = arg6;
        this.n = arg7;
        this.o = arg8;
        this.p = arg9;
        this.w = arg10.a();
        this.y = arg11;
        Context v3 = arg3.q();
        this.q = new f(arg8);
        this.r = new u(v3, this.q);
        this.s = new com.crashlytics.android.c.h$h(this, null);
        this.t = new com.crashlytics.android.c.h$i(this, null);
        this.u = new q(v3);
        this.v = new x(1024, new ai[]{new ad(10)});
        this.x = com.crashlytics.android.a.k.a(v3);
    }

    static String a(File arg2) {
        return arg2.getName().substring(0, 35);
    }

    static com.crashlytics.android.c.i a(h arg0) {
        return arg0.j;
    }

    private void a(long arg4) {
        if(this.p()) {
            c.a.a.a.c.h().a("CrashlyticsCore", "Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
            return;
        }

        if(this.y) {
            if(this.x != null) {
                c.a.a.a.c.h().a("CrashlyticsCore", "Logging Crashlytics event to Firebase");
                Bundle v0 = new Bundle();
                v0.putInt("_r", 1);
                v0.putInt("fatal", 1);
                v0.putLong("timestamp", arg4);
                this.x.a("clx", "_ae", v0);
            }
            else {
                c.a.a.a.c.h().a("CrashlyticsCore", "Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
            }
        }
    }

    private void a(c.a.a.a.a.g.p arg3, boolean arg4) {
        this.b((((int)arg4)) + 8);
        File[] v0 = this.n();
        if(v0.length <= arg4) {
            c.a.a.a.c.h().a("CrashlyticsCore", "No open sessions to be closed.");
            return;
        }

        this.f(h.a(v0[((int)arg4)]));
        if(arg3 == null) {
            c.a.a.a.c.h().a("CrashlyticsCore", "Unable to close session. Settings are not loaded.");
            return;
        }

        this.a(v0, ((int)arg4), arg3.c);
    }

    private void a(File[] arg7, int arg8, int arg9) {
        c.a.a.a.c.h().a("CrashlyticsCore", "Closing open sessions.");
        while(arg8 < arg7.length) {
            File v0 = arg7[arg8];
            String v1 = h.a(v0);
            l v2 = c.a.a.a.c.h();
            v2.a("CrashlyticsCore", "Closing session: " + v1);
            this.a(v0, v1, arg9);
            ++arg8;
        }
    }

    private void a(com.crashlytics.android.c.d arg4) {
        if(arg4 == null) {
            return;
        }

        try {
            arg4.a();
        }
        catch(IOException v4) {
            c.a.a.a.c.h().e("CrashlyticsCore", "Error closing session file stream in the presence of an exception", ((Throwable)v4));
        }
    }

    private static void a(e arg4, File arg5) {
        FileInputStream v0;
        Throwable v1 = null;
        if(!arg5.exists()) {
            l v4 = c.a.a.a.c.h();
            v4.e("CrashlyticsCore", "Tried to include a file that doesn\'t exist: " + arg5.getName(), v1);
            return;
        }

        try {
            v0 = new FileInputStream(arg5);
        }
        catch(Throwable v4_1) {
            Closeable v0_1 = ((Closeable)v1);
            goto label_26;
        }

        try {
            h.a(((InputStream)v0), arg4, ((int)arg5.length()));
            goto label_19;
        }
        catch(Throwable v4_1) {
        }

    label_26:
        c.a.a.a.a.b.i.a(((Closeable)v0), "Failed to close file input stream.");
        throw v4_1;
    label_19:
        c.a.a.a.a.b.i.a(((Closeable)v0), "Failed to close file input stream.");
    }

    private static void a(InputStream arg2, e arg3, int arg4) {
        byte[] v4 = new byte[arg4];
        int v0;
        for(v0 = 0; v0 < v4.length; v0 += v1) {
            int v1 = arg2.read(v4, v0, v4.length - v0);
            if(v1 < 0) {
                break;
            }
        }

        arg3.a(v4);
    }

    private void a(e arg11, String arg12) {
        String[] v0 = h.h;
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            String v4 = v0[v3];
            StringBuilder v6 = new StringBuilder();
            v6.append(arg12);
            v6.append(v4);
            v6.append(".cls");
            File[] v5 = this.a(new c(v6.toString()));
            if(v5.length == 0) {
                l v5_1 = c.a.a.a.c.h();
                v5_1.e("CrashlyticsCore", "Can\'t find " + v4 + " data for session ID " + arg12, null);
            }
            else {
                l v6_1 = c.a.a.a.c.h();
                v6_1.a("CrashlyticsCore", "Collecting " + v4 + " data for session ID " + arg12);
                h.a(arg11, v5[0]);
            }
        }
    }

    private File[] a(FilenameFilter arg2) {
        return this.a(this.f(), arg2);
    }

    private void a(e arg25, Date arg26, Thread arg27, Throwable arg28, String arg29, boolean arg30) {
        TreeMap v10_1;
        TreeMap v1_1;
        Thread[] v8_2;
        boolean v6;
        h v0 = this;
        aj v5 = new aj(arg28, v0.v);
        Context v1 = v0.j.q();
        long v2 = arg26.getTime() / 1000;
        Float v16 = c.a.a.a.a.b.i.c(v1);
        int v17 = c.a.a.a.a.b.i.a(v1, v0.u.a());
        boolean v18 = c.a.a.a.a.b.i.d(v1);
        int v13 = v1.getResources().getConfiguration().orientation;
        long v19 = c.a.a.a.a.b.i.b() - c.a.a.a.a.b.i.b(v1);
        long v21 = c.a.a.a.a.b.i.c(Environment.getDataDirectory().getPath());
        ActivityManager$RunningAppProcessInfo v12 = c.a.a.a.a.b.i.a(v1.getPackageName(), v1);
        LinkedList v9 = new LinkedList();
        StackTraceElement[] v7 = v5.c;
        String v15 = v0.p.b;
        String v14 = v0.m.c();
        int v4 = 0;
        if(arg30) {
            Map v8 = Thread.getAllStackTraces();
            Thread[] v10 = new Thread[v8.size()];
            Iterator v8_1 = v8.entrySet().iterator();
            while(v8_1.hasNext()) {
                Object v11 = v8_1.next();
                v10[v4] = ((Map$Entry)v11).getKey();
                ((List)v9).add(v0.v.a(((Map$Entry)v11).getValue()));
                ++v4;
            }

            v6 = true;
            v8_2 = v10;
        }
        else {
            v6 = true;
            v8_2 = new Thread[0];
        }

        if(!c.a.a.a.a.b.i.a(v1, "com.crashlytics.CollectCustomKeys", v6)) {
            v1_1 = new TreeMap();
            goto label_63;
        }
        else {
            Map v1_2 = v0.j.f();
            if(v1_2 == null || v1_2.size() <= (((int)v6))) {
            label_63:
                v10_1 = v1_1;
            }
            else {
                v10_1 = new TreeMap(v1_2);
            }
        }

        ag.a(arg25, v2, arg29, v5, arg27, v7, v8_2, ((List)v9), ((Map)v10_1), v0.r, v12, v13, v14, v15, v16, v17, v18, v19, v21);
    }

    private static void a(e arg11, File[] arg12, String arg13) {
        Arrays.sort(((Object[])arg12), c.a.a.a.a.b.i.a);
        int v0 = arg12.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            File v3 = arg12[v2];
            try {
                c.a.a.a.c.h().a("CrashlyticsCore", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", arg13, v3.getName()));
                h.a(arg11, v3);
            }
            catch(Exception v3_1) {
                c.a.a.a.c.h().e("CrashlyticsCore", "Error writting non-fatal to session.", ((Throwable)v3_1));
            }
        }
    }

    static void a(h arg0, c.a.a.a.a.g.p arg1, boolean arg2) {
        arg0.a(arg1, arg2);
    }

    static void a(h arg0, com.crashlytics.android.c.a.a.d arg1) {
        arg0.b(arg1);
    }

    static void a(h arg0, Date arg1, Thread arg2, Throwable arg3) {
        arg0.a(arg1, arg2, arg3);
    }

    private void a(Date arg12, Thread arg13, Throwable arg14) {
        e v0_1;
        e v1_1;
        Closeable v2_1;
        com.crashlytics.android.c.d v2;
        String v1;
        Throwable v0 = null;
        try {
            v1 = this.k();
            if(v1 != null) {
                goto label_12;
            }

            c.a.a.a.c.h().e("CrashlyticsCore", "Tried to write a fatal exception while no session was open.", v0);
        }
        catch(Exception v12) {
            goto label_50;
        }
        catch(Throwable v12_1) {
            goto label_47;
        }

        c.a.a.a.a.b.i.a(((Flushable)v0), "Failed to flush to session begin file.");
        c.a.a.a.a.b.i.a(((Closeable)v0), "Failed to close fatal exception file output stream.");
        return;
        try {
        label_12:
            h.a(v1, arg14.getClass().getName());
            this.a(arg12.getTime());
            File v3 = this.f();
            StringBuilder v4 = new StringBuilder();
            v4.append(v1);
            v4.append("SessionCrash");
            v2 = new com.crashlytics.android.c.d(v3, v4.toString());
        }
        catch(Throwable v12_1) {
        label_47:
            v2_1 = ((Closeable)v0);
            goto label_61;
        }
        catch(Exception v12) {
        label_50:
            v2_1 = ((Closeable)v0);
            goto label_51;
        }

        try {
            v1_1 = e.a(((OutputStream)v2));
            goto label_27;
        }
        catch(Throwable v12_1) {
        }
        catch(Exception v12) {
            goto label_51;
            try {
            label_27:
                this.a(v1_1, arg12, arg13, arg14, "crash", true);
                goto label_35;
            }
            catch(Throwable v12_1) {
                v0_1 = v1_1;
            }
            catch(Exception v12) {
                v0_1 = v1_1;
                try {
                label_51:
                    c.a.a.a.c.h().e("CrashlyticsCore", "An error occurred in the fatal exception logger", ((Throwable)v12));
                }
                catch(Throwable v12_1) {
                    goto label_61;
                }

                c.a.a.a.a.b.i.a(((Flushable)v0_1), "Failed to flush to session begin file.");
                goto label_57;
            }
        }

    label_61:
        c.a.a.a.a.b.i.a(((Flushable)v0_1), "Failed to flush to session begin file.");
        c.a.a.a.a.b.i.a(v2_1, "Failed to close fatal exception file output stream.");
        throw v12_1;
    label_35:
        c.a.a.a.a.b.i.a(((Flushable)v1_1), "Failed to flush to session begin file.");
    label_57:
        c.a.a.a.a.b.i.a(v2_1, "Failed to close fatal exception file output stream.");
    }

    private void a(File arg13, String arg14, int arg15) {
        l v13;
        l v0 = c.a.a.a.c.h();
        v0.a("CrashlyticsCore", "Collecting session parts for ID " + arg14);
        StringBuilder v1 = new StringBuilder();
        v1.append(arg14);
        v1.append("SessionCrash");
        File[] v0_1 = this.a(new c(v1.toString()));
        boolean v3 = v0_1 == null || v0_1.length <= 0 ? false : true;
        l v4 = c.a.a.a.c.h();
        Locale v6 = Locale.US;
        int v8 = 2;
        v4.a("CrashlyticsCore", String.format(v6, "Session %s has fatal exception: %s", arg14, Boolean.valueOf(v3)));
        StringBuilder v5 = new StringBuilder();
        v5.append(arg14);
        v5.append("SessionEvent");
        File[] v4_1 = this.a(new c(v5.toString()));
        boolean v5_1 = v4_1 == null || v4_1.length <= 0 ? false : true;
        l v6_1 = c.a.a.a.c.h();
        Locale v9_1 = Locale.US;
        v6_1.a("CrashlyticsCore", String.format(v9_1, "Session %s has non-fatal exceptions: %s", arg14, Boolean.valueOf(v5_1)));
        if((v3) || (v5_1)) {
            File[] v15 = this.a(arg14, v4_1, arg15);
            File v0_3 = v3 ? v0_1[0] : null;
            this.a(arg13, arg14, v15, v0_3);
        }
        else {
            v13 = c.a.a.a.c.h();
            v13.a("CrashlyticsCore", "No events present for session ID " + arg14);
        }

        v13 = c.a.a.a.c.h();
        v13.a("CrashlyticsCore", "Removing session part files for ID " + arg14);
        this.a(arg14);
    }

    private File[] a(String arg7, File[] arg8, int arg9) {
        if(arg8.length > arg9) {
            c.a.a.a.c.h().a("CrashlyticsCore", String.format(Locale.US, "Trimming down to %d logged exceptions.", Integer.valueOf(arg9)));
            this.a(arg7, arg9);
            StringBuilder v9 = new StringBuilder();
            v9.append(arg7);
            v9.append("SessionEvent");
            arg8 = this.a(new c(v9.toString()));
        }

        return arg8;
    }

    private void a(File arg10, String arg11, File[] arg12, File arg13) {
        e v2_2;
        Closeable v4_1;
        Flushable v2_1;
        com.crashlytics.android.c.d v4;
        boolean v1 = arg13 != null ? true : false;
        File v2 = v1 ? this.g() : this.h();
        if(!v2.exists()) {
            v2.mkdirs();
        }

        Flushable v3 = null;
        try {
            v4 = new com.crashlytics.android.c.d(v2, arg11);
        }
        catch(Throwable v10) {
            v2_1 = v3;
            v4_1 = ((Closeable)v2_1);
            goto label_77;
        }
        catch(Exception v10_1) {
            v4 = ((com.crashlytics.android.c.d)v3);
            goto label_62;
        }

        try {
            v2_2 = e.a(((OutputStream)v4));
            goto label_16;
        }
        catch(Throwable v10) {
        }
        catch(Exception v10_1) {
            goto label_62;
            try {
            label_16:
                l v3_1 = c.a.a.a.c.h();
                v3_1.a("CrashlyticsCore", "Collecting SessionStart data for session ID " + arg11);
                h.a(v2_2, arg10);
                v2_2.a(4, new Date().getTime() / 1000);
                v2_2.a(5, v1);
                v2_2.a(11, 1);
                v2_2.b(12, 3);
                this.a(v2_2, arg11);
                h.a(v2_2, arg12, arg11);
                if(v1) {
                    h.a(v2_2, arg13);
                }

                goto label_44;
            }
            catch(Throwable v10) {
            }
            catch(Exception v10_1) {
                e v3_2 = v2_2;
                try {
                label_62:
                    l v12 = c.a.a.a.c.h();
                    v12.e("CrashlyticsCore", "Failed to write session file for session ID: " + arg11, ((Throwable)v10_1));
                }
                catch(Throwable v10) {
                    v2_2 = v3_2;
                    goto label_77;
                }

                c.a.a.a.a.b.i.a(((Flushable)v3_2), "Error flushing session file stream");
                this.a(v4);
                return;
            }
        }

    label_77:
        c.a.a.a.a.b.i.a(v2_1, "Error flushing session file stream");
        c.a.a.a.a.b.i.a(v4_1, "Failed to close CLS file");
        throw v10;
    label_44:
        c.a.a.a.a.b.i.a(((Flushable)v2_2), "Error flushing session file stream");
        c.a.a.a.a.b.i.a(((Closeable)v4), "Failed to close CLS file");
    }

    private void a(String arg4) {
        File[] v4 = this.b(arg4);
        int v0 = v4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            v4[v1].delete();
        }
    }

    private void a(String arg4, int arg5) {
        File v0 = this.f();
        StringBuilder v2 = new StringBuilder();
        v2.append(arg4);
        v2.append("SessionEvent");
        am.a(v0, new c(v2.toString()), arg5, h.e);
    }

    private static void a(String arg2, String arg3) {
        i v0 = c.a.a.a.c.a(com.crashlytics.android.a.b.class);
        if(v0 == null) {
            c.a.a.a.c.h().a("CrashlyticsCore", "Answers is not available");
            return;
        }

        ((com.crashlytics.android.a.b)v0).a(new c.a.a.a.a.b.j$a(arg2, arg3));
    }

    private void a(String arg5, String arg6, b arg7) {
        e v0_1;
        e v5;
        Closeable v1_1;
        com.crashlytics.android.c.d v1;
        Flushable v0 = null;
        try {
            File v2 = this.f();
            StringBuilder v3 = new StringBuilder();
            v3.append(arg5);
            v3.append(arg6);
            v1 = new com.crashlytics.android.c.d(v2, v3.toString());
        }
        catch(Throwable v7) {
            v1_1 = ((Closeable)v0);
            goto label_37;
        }

        try {
            v5 = e.a(((OutputStream)v1));
        }
        catch(Throwable v7) {
            goto label_37;
        }

        try {
            arg7.a(v5);
            goto label_11;
        }
        catch(Throwable v7) {
            v0_1 = v5;
        }

    label_37:
        c.a.a.a.a.b.i.a(((Flushable)v0_1), "Failed to flush to session " + arg6 + " file.");
        c.a.a.a.a.b.i.a(v1_1, "Failed to close session " + arg6 + " file.");
        throw v7;
    label_11:
        c.a.a.a.a.b.i.a(((Flushable)v5), "Failed to flush to session " + arg6 + " file.");
        c.a.a.a.a.b.i.a(((Closeable)v1), "Failed to close session " + arg6 + " file.");
    }

    private void a(String arg6, String arg7, d arg8) {
        FileOutputStream v1;
        Closeable v0 = null;
        try {
            File v3 = this.f();
            StringBuilder v4 = new StringBuilder();
            v4.append(arg6);
            v4.append(arg7);
            v1 = new FileOutputStream(new File(v3, v4.toString()));
        }
        catch(Throwable v6) {
            goto label_26;
        }

        try {
            arg8.a(v1);
            goto label_12;
        }
        catch(Throwable v6) {
            FileOutputStream v0_1 = v1;
        }

    label_26:
        c.a.a.a.a.b.i.a(v0, "Failed to close " + arg7 + " file.");
        throw v6;
    label_12:
        c.a.a.a.a.b.i.a(((Closeable)v1), "Failed to close " + arg7 + " file.");
    }

    private void a(String arg12, Date arg13) {
        String v0 = String.format(Locale.US, "Crashlytics Android SDK/%s", this.j.a());
        long v1 = arg13.getTime() / 1000;
        this.a(arg12, "BeginSession", new b(arg12, v0, v1) {
            public void a(e arg5) {
                ag.a(arg5, this.a, this.b, this.c);
            }
        });
        this.a(arg12, "BeginSession.json", new d(arg12, v0, v1) {
            public void a(FileOutputStream arg3) {
                arg3.write(new JSONObject(new HashMap() {
                }).toString().getBytes());
            }
        });
    }

    private void a(File[] arg9, Set arg10) {
        String v7;
        StringBuilder v6;
        String v5;
        l v4_1;
        int v0 = arg9.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            File v2 = arg9[v1];
            String v3 = v2.getName();
            Matcher v4 = h.f.matcher(((CharSequence)v3));
            if(!v4.matches()) {
                v4_1 = c.a.a.a.c.h();
                v5 = "CrashlyticsCore";
                v6 = new StringBuilder();
                v7 = "Deleting unknown file: ";
                goto label_14;
            }
            else if(!arg10.contains(v4.group(1))) {
                v4_1 = c.a.a.a.c.h();
                v5 = "CrashlyticsCore";
                v6 = new StringBuilder();
                v7 = "Trimming session file: ";
            label_14:
                v6.append(v7);
                v6.append(v3);
                v4_1.a(v5, v6.toString());
                v2.delete();
            }
        }
    }

    private boolean a(t arg2) {
        boolean v0 = false;
        if(arg2 == null) {
            return 0;
        }

        if((arg2.d.a) && !this.n.a()) {
            v0 = true;
        }

        return v0;
    }

    static boolean a(h arg0, t arg1) {
        return arg0.a(arg1);
    }

    static File[] a(h arg0, FilenameFilter arg1) {
        return arg0.a(arg1);
    }

    private File[] a(File arg1, FilenameFilter arg2) {
        return this.b(arg1.listFiles(arg2));
    }

    void a() {
        this.k.b(new Callable() {
            public Void a() {
                h.b(this.a);
                return null;
            }

            public Object call() {
                return this.a();
            }
        });
    }

    void a(float arg6, t arg7) {
        com.crashlytics.android.c.af$a v1_1;
        if(arg7 == null) {
            c.a.a.a.c.h().d("CrashlyticsCore", "Could not send reports. Settings are not available.");
            return;
        }

        com.crashlytics.android.c.o v0 = this.h(arg7.a.d);
        if(this.a(arg7)) {
            g v1 = new g(this.j, this.n, arg7.c);
        }
        else {
            v1_1 = new com.crashlytics.android.c.af$a();
        }

        new af(this.p.a, v0, this.s, this.t).a(arg6, ((com.crashlytics.android.c.af$d)v1_1));
    }

    void a(int arg4) {
        arg4 -= am.a(this.g(), arg4, h.e);
        am.a(this.f(), h.b, arg4 - am.a(this.h(), arg4, h.e), h.e);
    }

    void a(com.crashlytics.android.c.a.a.d arg3) {
        this.k.b(new Callable(arg3) {
            public Void a() {
                if(!this.b.e()) {
                    h.a(this.b, this.a);
                }

                return null;
            }

            public Object call() {
                return this.a();
            }
        });
    }

    void a(Thread$UncaughtExceptionHandler arg3) {
        this.a();
        this.z = new m(new com.crashlytics.android.c.m$a() {
            public void a(Thread arg2, Throwable arg3) {
                this.a.a(arg2, arg3);
            }
        }, arg3);
        Thread.setDefaultUncaughtExceptionHandler(this.z);
    }

    void a(Thread arg5, Throwable arg6) {
        __monitor_enter(this);
        try {
            l v0 = c.a.a.a.c.h();
            v0.a("CrashlyticsCore", "Crashlytics is handling uncaught exception \"" + arg6 + "\" from thread " + arg5.getName());
            this.u.b();
            this.k.a(new Callable(new Date(), arg5, arg6) {
                public Void a() {
                    h.a(this.d).n();
                    h.a(this.d, this.a, this.b, this.c);
                    t v0 = c.a.a.a.a.g.q.a().b();
                    Void v1 = null;
                    c.a.a.a.a.g.p v2 = v0 != null ? v0.b : ((c.a.a.a.a.g.p)v1);
                    this.d.b(v2);
                    h.b(this.d);
                    if(v2 != null) {
                        this.d.a(v2.g);
                    }

                    if(!h.a(this.d, v0)) {
                        h.b(this.d, v0);
                    }

                    return v1;
                }

                public Object call() {
                    return this.a();
                }
            });
        }
        catch(Throwable v5) {
            __monitor_exit(this);
            throw v5;
        }

        __monitor_exit(this);
    }

    void a(File[] arg10) {
        HashSet v0 = new HashSet();
        int v1 = arg10.length;
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            File v4 = arg10[v3];
            l v5 = c.a.a.a.c.h();
            v5.a("CrashlyticsCore", "Found invalid session part file: " + v4);
            ((Set)v0).add(h.a(v4));
        }

        if(((Set)v0).isEmpty()) {
            return;
        }

        File v10 = this.i();
        if(!v10.exists()) {
            v10.mkdir();
        }

        File[] v0_1 = this.a(new FilenameFilter(((Set)v0)) {
            public boolean accept(File arg3, String arg4) {
                int v1 = 35;
                if(arg4.length() < v1) {
                    return 0;
                }

                return this.a.contains(arg4.substring(0, v1));
            }
        });
        v1 = v0_1.length;
        while(v2 < v1) {
            File v3_1 = v0_1[v2];
            l v4_1 = c.a.a.a.c.h();
            v4_1.a("CrashlyticsCore", "Moving session file: " + v3_1);
            if(!v3_1.renameTo(new File(v10, v3_1.getName()))) {
                v4_1 = c.a.a.a.c.h();
                v4_1.a("CrashlyticsCore", "Could not move session file. Deleting " + v3_1);
                v3_1.delete();
            }

            ++v2;
        }

        this.o();
    }

    boolean a(c.a.a.a.a.g.p arg3) {
        return this.k.a(new Callable(arg3) {
            public Boolean a() {
                if(this.b.e()) {
                    c.a.a.a.c.h().a("CrashlyticsCore", "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }

                c.a.a.a.c.h().a("CrashlyticsCore", "Finalizing previously open sessions.");
                h.a(this.b, this.a, true);
                c.a.a.a.c.h().a("CrashlyticsCore", "Closed all previously open sessions");
                return Boolean.TRUE;
            }

            public Object call() {
                return this.a();
            }
        }).booleanValue();
    }

    private void b(int arg5) {
        HashSet v0 = new HashSet();
        File[] v1 = this.n();
        arg5 = Math.min(arg5, v1.length);
        int v2;
        for(v2 = 0; v2 < arg5; ++v2) {
            ((Set)v0).add(h.a(v1[v2]));
        }

        this.r.a(((Set)v0));
        this.a(this.a(new a(null)), ((Set)v0));
    }

    private void b(com.crashlytics.android.c.a.a.d arg9) {
        e v0_1;
        e v2_2;
        Closeable v3_1;
        com.crashlytics.android.c.d v3;
        String v1;
        Throwable v0 = null;
        try {
            v1 = this.l();
            if(v1 != null) {
                goto label_12;
            }

            c.a.a.a.c.h().e("CrashlyticsCore", "Tried to write a native crash while no session was open.", v0);
        }
        catch(Throwable v9) {
            goto label_67;
        }
        catch(Exception v9_1) {
            goto label_70;
        }

        c.a.a.a.a.b.i.a(((Flushable)v0), "Failed to flush to session begin file.");
        c.a.a.a.a.b.i.a(((Closeable)v0), "Failed to close fatal exception file output stream.");
        return;
        try {
        label_12:
            Locale v2 = Locale.US;
            Object[] v4 = new Object[2];
            int v6 = 0;
            v4[0] = arg9.b.b;
            v4[1] = arg9.b.a;
            h.a(v1, String.format(v2, "<native-crash [%s (%s)]>", v4));
            if(arg9.d != null && arg9.d.length > 0) {
                v6 = 1;
            }

            String v2_1 = v6 != 0 ? "SessionCrash" : "SessionMissingBinaryImages";
            File v4_1 = this.f();
            StringBuilder v5 = new StringBuilder();
            v5.append(v1);
            v5.append(v2_1);
            v3 = new com.crashlytics.android.c.d(v4_1, v5.toString());
        }
        catch(Throwable v9) {
        label_67:
            v3_1 = ((Closeable)v0);
            goto label_81;
        }
        catch(Exception v9_1) {
        label_70:
            v3_1 = ((Closeable)v0);
            goto label_71;
        }

        try {
            v2_2 = e.a(((OutputStream)v3));
            goto label_45;
        }
        catch(Throwable v9) {
        }
        catch(Exception v9_1) {
            goto label_71;
            try {
            label_45:
                z.a(arg9, new u(this.j.q(), this.q, v1), new w(this.f()).b(v1), v2_2);
                goto label_55;
            }
            catch(Throwable v9) {
                v0_1 = v2_2;
            }
            catch(Exception v9_1) {
                v0_1 = v2_2;
                try {
                label_71:
                    c.a.a.a.c.h().e("CrashlyticsCore", "An error occurred in the native crash logger", ((Throwable)v9_1));
                }
                catch(Throwable v9) {
                    goto label_81;
                }

                c.a.a.a.a.b.i.a(((Flushable)v0), "Failed to flush to session begin file.");
                goto label_77;
            }
        }

    label_81:
        c.a.a.a.a.b.i.a(((Flushable)v0), "Failed to flush to session begin file.");
        c.a.a.a.a.b.i.a(v3_1, "Failed to close fatal exception file output stream.");
        throw v9;
    label_55:
        c.a.a.a.a.b.i.a(((Flushable)v2_2), "Failed to flush to session begin file.");
    label_77:
        c.a.a.a.a.b.i.a(v3_1, "Failed to close fatal exception file output stream.");
    }

    private File[] b(String arg2) {
        return this.a(new k(arg2));
    }

    private File[] b(File[] arg1) {
        if(arg1 == null) {
            arg1 = new File[0];
        }

        return arg1;
    }

    private void b(t arg8) {
        if(arg8 == null) {
            c.a.a.a.c.h().d("CrashlyticsCore", "Cannot send reports. Settings are unavailable.");
            return;
        }

        Context v0 = this.j.q();
        af v1 = new af(this.p.a, this.h(arg8.a.d), this.s, this.t);
        File[] v8 = this.b();
        int v2 = v8.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            this.k.a(new j(v0, new ah(v8[v3], h.g), v1));
        }
    }

    File[] b() {
        LinkedList v0 = new LinkedList();
        Collections.addAll(((Collection)v0), this.a(this.g(), h.b));
        Collections.addAll(((Collection)v0), this.a(this.h(), h.b));
        Collections.addAll(((Collection)v0), this.a(this.f(), h.b));
        return ((List)v0).toArray(new File[((List)v0).size()]);
    }

    static void b(h arg0) {
        arg0.m();
    }

    static void b(h arg0, t arg1) {
        arg0.b(arg1);
    }

    private File[] b(File arg1) {
        return this.b(arg1.listFiles());
    }

    void b(c.a.a.a.a.g.p arg2) {
        this.a(arg2, false);
    }

    static com.crashlytics.android.c.a c(h arg0) {
        return arg0.p;
    }

    private void c(String arg15) {
        String v0 = this.m.c();
        String v8 = this.p.e;
        String v9 = this.p.f;
        String v10 = this.m.b();
        int v7 = c.a.a.a.a.b.l.a(this.p.c).a();
        this.a(arg15, "SessionApp", new b(v0, v8, v9, v10, v7) {
            public void a(e arg9) {
                ag.a(arg9, this.a, h.c(this.f).a, this.b, this.c, this.d, this.e, h.d(this.f));
            }
        });
        this.a(arg15, "SessionApp.json", new d(v0, v8, v9, v10, v7) {
            public void a(FileOutputStream arg3) {
                arg3.write(new JSONObject(new HashMap() {
                }).toString().getBytes());
            }
        });
    }

    File[] c() {
        return this.a(h.a);
    }

    static String d(h arg0) {
        return arg0.w;
    }

    private void d(String arg4) {
        boolean v0 = c.a.a.a.a.b.i.g(this.j.q());
        this.a(arg4, "SessionOS", new b(v0) {
            public void a(e arg4) {
                ag.a(arg4, Build$VERSION.RELEASE, Build$VERSION.CODENAME, this.a);
            }
        });
        this.a(arg4, "SessionOS.json", new d(v0) {
            public void a(FileOutputStream arg3) {
                arg3.write(new JSONObject(new HashMap() {
                }).toString().getBytes());
            }
        });
    }

    void d() {
        this.k.a(new Runnable() {
            public void run() {
                this.a.a(h.a(this.a, new com.crashlytics.android.c.h$e()));
            }
        });
    }

    private void e(String arg26) {
        Context v0 = this.j.q();
        StatFs v1 = new StatFs(Environment.getDataDirectory().getPath());
        int v13 = c.a.a.a.a.b.i.a();
        int v14 = Runtime.getRuntime().availableProcessors();
        long v15 = c.a.a.a.a.b.i.b();
        long v17 = (((long)v1.getBlockCount())) * (((long)v1.getBlockSize()));
        boolean v19 = c.a.a.a.a.b.i.f(v0);
        Map v20 = this.m.h();
        int v10 = c.a.a.a.a.b.i.h(v0);
        com.crashlytics.android.c.h$14 v0_1 = new b(v13, v14, v15, v17, v19, v20, v10) {
            public void a(e arg14) {
                ag.a(arg14, this.a, Build.MODEL, this.b, this.c, this.d, this.e, this.f, this.g, Build.MANUFACTURER, Build.PRODUCT);
            }
        };
        this.a(arg26, "SessionDevice", v0_1);
        this.a(arg26, "SessionDevice.json", new d(v13, v14, v15, v17, v19, v0_1, v10) {
            public void a(FileOutputStream arg3) {
                arg3.write(new JSONObject(new HashMap() {
                }).toString().getBytes());
            }
        });
    }

    boolean e() {
        boolean v0 = this.z == null || !this.z.a() ? false : true;
        return v0;
    }

    private void f(String arg4) {
        this.a(arg4, "SessionUser", new b(this.g(arg4)) {
            public void a(e arg4) {
                ag.a(arg4, this.a.b, this.a.c, this.a.d);
            }
        });
    }

    File f() {
        return this.o.a();
    }

    File g() {
        return new File(this.f(), "fatal-sessions");
    }

    private al g(String arg4) {
        al v4 = this.e() ? new al(this.j.g(), this.j.i(), this.j.h()) : new w(this.f()).a(arg4);
        return v4;
    }

    File h() {
        return new File(this.f(), "nonfatal-sessions");
    }

    private com.crashlytics.android.c.o h(String arg5) {
        return new com.crashlytics.android.c.p(this.j, c.a.a.a.a.b.i.b(this.j.q(), "com.crashlytics.ApiEndpoint"), arg5, this.l);
    }

    File i() {
        return new File(this.f(), "invalidClsFiles");
    }

    static Pattern j() {
        return h.f;
    }

    private String k() {
        File[] v0 = this.n();
        String v0_1 = v0.length > 0 ? h.a(v0[0]) : null;
        return v0_1;
    }

    private String l() {
        File[] v0 = this.n();
        String v0_1 = v0.length > 1 ? h.a(v0[1]) : null;
        return v0_1;
    }

    private void m() {
        Date v0 = new Date();
        String v1 = new com.crashlytics.android.c.c(this.m).toString();
        l v2 = c.a.a.a.c.h();
        v2.a("CrashlyticsCore", "Opening a new session with ID " + v1);
        this.a(v1, v0);
        this.c(v1);
        this.d(v1);
        this.e(v1);
        this.r.a(v1);
    }

    private File[] n() {
        File[] v0 = this.c();
        Arrays.sort(((Object[])v0), h.d);
        return v0;
    }

    private void o() {
        File v0 = this.i();
        if(!v0.exists()) {
            return;
        }

        File[] v1 = this.a(v0, new com.crashlytics.android.c.h$e());
        Arrays.sort(((Object[])v1), Collections.reverseOrder());
        HashSet v2 = new HashSet();
        int v3;
        for(v3 = 0; v3 < v1.length; ++v3) {
            if(((Set)v2).size() >= 4) {
                break;
            }

            ((Set)v2).add(h.a(v1[v3]));
        }

        this.a(this.b(v0), ((Set)v2));
    }

    private boolean p() {
        try {
            Class.forName("com.google.firebase.crash.FirebaseCrash");
            return 1;
        }
        catch(ClassNotFoundException ) {
            return 0;
        }
    }
}

