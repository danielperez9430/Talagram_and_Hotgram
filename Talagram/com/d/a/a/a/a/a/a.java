package com.d.a.a.a.a.a;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

final class a implements Closeable {
    class com.d.a.a.a.a.a.a$1 implements Callable {
        com.d.a.a.a.a.a.a$1(a arg1) {
            this.a = arg1;
            super();
        }

        public Void a() {
            a v0 = this.a;
            __monitor_enter(v0);
            try {
                Void v2 = null;
                if(a.a(this.a) == null) {
                    __monitor_exit(v0);
                    return v2;
                }

                a.b(this.a);
                a.c(this.a);
                if(a.d(this.a)) {
                    a.e(this.a);
                    a.a(this.a, 0);
                }

                __monitor_exit(v0);
                return v2;
            label_23:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_23;
            }

            throw v1;
        }

        public Object call() {
            return this.a();
        }
    }

    final class com.d.a.a.a.a.a.a$2 extends OutputStream {
        com.d.a.a.a.a.a.a$2() {
            super();
        }

        public void write(int arg1) {
        }
    }

    public final class com.d.a.a.a.a.a.a$a {
        class com.d.a.a.a.a.a.a$a$a extends FilterOutputStream {
            com.d.a.a.a.a.a.a$a$a(com.d.a.a.a.a.a.a$a arg1, OutputStream arg2, com.d.a.a.a.a.a.a$1 arg3) {
                this(arg1, arg2);
            }

            private com.d.a.a.a.a.a.a$a$a(com.d.a.a.a.a.a.a$a arg1, OutputStream arg2) {
                this.a = arg1;
                super(arg2);
            }

            public void close() {
                try {
                    this.out.close();
                }
                catch(IOException ) {
                    com.d.a.a.a.a.a.a$a.a(this.a, true);
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                }
                catch(IOException ) {
                    com.d.a.a.a.a.a.a$a.a(this.a, true);
                }
            }

            public void write(int arg2) {
                try {
                    this.out.write(arg2);
                }
                catch(IOException ) {
                    com.d.a.a.a.a.a.a$a.a(this.a, true);
                }
            }

            public void write(byte[] arg2, int arg3, int arg4) {
                try {
                    this.out.write(arg2, arg3, arg4);
                }
                catch(IOException ) {
                    com.d.a.a.a.a.a.a$a.a(this.a, true);
                }
            }
        }

        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        com.d.a.a.a.a.a.a$a(a arg1, b arg2, com.d.a.a.a.a.a.a$1 arg3) {
            this(arg1, arg2);
        }

        private com.d.a.a.a.a.a.a$a(a arg1, b arg2) {
            this.a = arg1;
            super();
            this.b = arg2;
            boolean[] v1 = b.d(arg2) ? null : new boolean[a.f(arg1)];
            this.c = v1;
        }

        static b a(com.d.a.a.a.a.a.a$a arg0) {
            return arg0.b;
        }

        static boolean a(com.d.a.a.a.a.a.a$a arg0, boolean arg1) {
            arg0.d = arg1;
            return arg1;
        }

        public OutputStream a(int arg4) {
            FileOutputStream v1;
            a v0 = this.a;
            __monitor_enter(v0);
            try {
                if(b.a(this.b) == this) {
                    if(!b.d(this.b)) {
                        this.c[arg4] = true;
                    }

                    File v4_1 = this.b.b(arg4);
                    try {
                        v1 = new FileOutputStream(v4_1);
                    }
                    catch(FileNotFoundException ) {
                        a.g(this.a).mkdirs();
                        try {
                            v1 = new FileOutputStream(v4_1);
                        }
                        catch(FileNotFoundException ) {
                            __monitor_exit(v0);
                            return a.b();
                        }
                    }

                    __monitor_exit(v0);
                    return new com.d.a.a.a.a.a.a$a$a(this, ((OutputStream)v1), null);
                }

                throw new IllegalStateException();
            label_33:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_33;
            }

            throw v4;
        }

        public void a() {
            if(this.d) {
                a.a(this.a, this, false);
                this.a.c(b.c(this.b));
            }
            else {
                a.a(this.a, this, true);
            }

            this.e = true;
        }

        static boolean[] b(com.d.a.a.a.a.a.a$a arg0) {
            return arg0.c;
        }

        public void b() {
            a.a(this.a, this, false);
        }
    }

    final class b {
        private final String b;
        private final long[] c;
        private boolean d;
        private com.d.a.a.a.a.a.a$a e;
        private long f;

        b(a arg1, String arg2, com.d.a.a.a.a.a.a$1 arg3) {
            this(arg1, arg2);
        }

        private b(a arg1, String arg2) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = new long[a.f(arg1)];
        }

        static com.d.a.a.a.a.a.a$a a(b arg0) {
            return arg0.e;
        }

        public File a(int arg5) {
            File v1 = a.g(this.a);
            StringBuilder v2 = new StringBuilder();
            v2.append(this.b);
            v2.append("");
            v2.append(arg5);
            return new File(v1, v2.toString());
        }

        static com.d.a.a.a.a.a.a$a a(b arg0, com.d.a.a.a.a.a.a$a arg1) {
            arg0.e = arg1;
            return arg1;
        }

        static boolean a(b arg0, boolean arg1) {
            arg0.d = arg1;
            return arg1;
        }

        public String a() {
            StringBuilder v0 = new StringBuilder();
            long[] v1 = this.c;
            int v2 = v1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                long v4 = v1[v3];
                v0.append(' ');
                v0.append(v4);
            }

            return v0.toString();
        }

        static long a(b arg0, long arg1) {
            arg0.f = arg1;
            return arg1;
        }

        static void a(b arg0, String[] arg1) {
            arg0.a(arg1);
        }

        private void a(String[] arg5) {
            if(arg5.length == a.f(this.a)) {
                int v0 = 0;
                try {
                    while(v0 < arg5.length) {
                        this.c[v0] = Long.parseLong(arg5[v0]);
                        ++v0;
                    }
                }
                catch(NumberFormatException ) {
                    throw this.b(arg5);
                }

                return;
            }

            throw this.b(arg5);
        }

        public File b(int arg5) {
            File v1 = a.g(this.a);
            StringBuilder v2 = new StringBuilder();
            v2.append(this.b);
            v2.append("");
            v2.append(arg5);
            v2.append(".tmp");
            return new File(v1, v2.toString());
        }

        static long[] b(b arg0) {
            return arg0.c;
        }

        private IOException b(String[] arg4) {
            StringBuilder v1 = new StringBuilder();
            v1.append("unexpected journal line: ");
            v1.append(Arrays.toString(((Object[])arg4)));
            throw new IOException(v1.toString());
        }

        static String c(b arg0) {
            return arg0.b;
        }

        static boolean d(b arg0) {
            return arg0.d;
        }

        static long e(b arg2) {
            return arg2.f;
        }
    }

    public final class c implements Closeable {
        private final String b;
        private final long c;
        private File[] d;
        private final InputStream[] e;
        private final long[] f;

        c(a arg1, String arg2, long arg3, File[] arg5, InputStream[] arg6, long[] arg7, com.d.a.a.a.a.a.a$1 arg8) {
            this(arg1, arg2, arg3, arg5, arg6, arg7);
        }

        private c(a arg1, String arg2, long arg3, File[] arg5, InputStream[] arg6, long[] arg7) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
            this.d = arg5;
            this.e = arg6;
            this.f = arg7;
        }

        public File a(int arg2) {
            return this.d[arg2];
        }

        public void close() {
            InputStream[] v0 = this.e;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                d.a(v0[v2]);
            }
        }
    }

    static final Pattern a;
    final ThreadPoolExecutor b;
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private int i;
    private final int j;
    private long k;
    private int l;
    private Writer m;
    private final LinkedHashMap n;
    private int o;
    private long p;
    private final Callable q;
    private static final OutputStream r;

    static {
        a.a = Pattern.compile("[a-z0-9_-]{1,64}");
        a.r = new com.d.a.a.a.a.a.a$2();
    }

    private a(File arg16, int arg17, int arg18, long arg19, int arg21) {
        super();
        this.k = 0;
        this.l = 0;
        this.n = new LinkedHashMap(0, 0.75f, true);
        this.p = 0;
        this.b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.q = new com.d.a.a.a.a.a.a$1(this);
        this.c = arg16;
        this.g = arg17;
        this.d = new File(arg16, "journal");
        this.e = new File(arg16, "journal.tmp");
        this.f = new File(arg16, "journal.bkp");
        this.j = arg18;
        this.h = arg19;
        this.i = arg21;
    }

    static int a(a arg0, int arg1) {
        arg0.o = arg1;
        return arg1;
    }

    private com.d.a.a.a.a.a.a$a a(String arg6, long arg7) {
        // Method was not decompiled
    }

    public static a a(File arg10, int arg11, int arg12, long arg13, int arg15) {
        if(arg13 > 0) {
            if(arg15 > 0) {
                if(arg12 > 0) {
                    File v0 = new File(arg10, "journal.bkp");
                    if(v0.exists()) {
                        File v1 = new File(arg10, "journal");
                        if(v1.exists()) {
                            v0.delete();
                        }
                        else {
                            a.a(v0, v1, false);
                        }
                    }

                    a v0_1 = new a(arg10, arg11, arg12, arg13, arg15);
                    if(v0_1.d.exists()) {
                        try {
                            v0_1.c();
                            v0_1.d();
                            v0_1.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(v0_1.d, true), d.a));
                            return v0_1;
                        }
                        catch(IOException v1_1) {
                            PrintStream v2 = System.out;
                            v2.println("DiskLruCache " + arg10 + " is corrupt: " + v1_1.getMessage() + ", removing");
                            v0_1.a();
                        }
                    }

                    arg10.mkdirs();
                    v0_1 = new a(arg10, arg11, arg12, arg13, arg15);
                    v0_1.e();
                    return v0_1;
                }

                throw new IllegalArgumentException("valueCount <= 0");
            }

            throw new IllegalArgumentException("maxFileCount <= 0");
        }

        throw new IllegalArgumentException("maxSize <= 0");
    }

    private static void a(File arg0, File arg1, boolean arg2) {
        if(arg2) {
            a.a(arg1);
        }

        if(arg0.renameTo(arg1)) {
            return;
        }

        throw new IOException();
    }

    public void a() {
        this.close();
        d.a(this.c);
    }

    static Writer a(a arg0) {
        return arg0.m;
    }

    private void a(com.d.a.a.a.a.a.a$a arg11, boolean arg12) {
        Writer v11_2;
        int v2;
        int v1;
        b v0;
        __monitor_enter(this);
        try {
            v0 = com.d.a.a.a.a.a.a$a.a(arg11);
            if(b.a(v0) != arg11) {
                goto label_115;
            }

            v1 = 0;
            if((arg12) && !b.d(v0)) {
                v2 = 0;
                while(true) {
                label_9:
                    if(v2 < this.j) {
                        if(!com.d.a.a.a.a.a.a$a.b(arg11)[v2]) {
                            goto label_22;
                        }
                        else if(!v0.b(v2).exists()) {
                            arg11.b();
                            goto label_18;
                        }
                        else {
                            goto label_20;
                        }
                    }

                    goto label_32;
                }
            }

            goto label_32;
        }
        catch(Throwable v11) {
            goto label_119;
        }

    label_18:
        __monitor_exit(this);
        return;
    label_20:
        ++v2;
        goto label_9;
        try {
        label_22:
            arg11.b();
            StringBuilder v12 = new StringBuilder();
            v12.append("Newly created entry didn\'t create value for index ");
            v12.append(v2);
            throw new IllegalStateException(v12.toString());
        label_32:
            while(v1 < this.j) {
                File v11_1 = v0.b(v1);
                if(!arg12) {
                    a.a(v11_1);
                }
                else if(v11_1.exists()) {
                    File v3 = v0.a(v1);
                    v11_1.renameTo(v3);
                    long v4 = b.b(v0)[v1];
                    long v6 = v3.length();
                    b.b(v0)[v1] = v6;
                    this.k = this.k - v4 + v6;
                    ++this.l;
                }

                ++v1;
            }

            ++this.o;
            b.a(v0, null);
            char v1_1 = '\n';
            if((b.d(v0) | (((int)arg12))) != 0) {
                b.a(v0, true);
                v11_2 = this.m;
                v11_2.write("CLEAN " + b.c(v0) + v0.a() + v1_1);
                if(arg12) {
                    long v11_3 = this.p;
                    this.p = 1 + v11_3;
                    b.a(v0, v11_3);
                }
            }
            else {
                this.n.remove(b.c(v0));
                v11_2 = this.m;
                v11_2.write("REMOVE " + b.c(v0) + v1_1);
            }

            this.m.flush();
            if(this.k > this.h || this.l > this.i || (this.f())) {
                this.b.submit(this.q);
            }
        }
        catch(Throwable v11) {
            goto label_119;
        }

        __monitor_exit(this);
        return;
        try {
        label_115:
            throw new IllegalStateException();
        }
        catch(Throwable v11) {
        label_119:
            __monitor_exit(this);
            throw v11;
        }
    }

    private static void a(File arg1) {
        if(arg1.exists()) {
            if(arg1.delete()) {
            }
            else {
                throw new IOException();
            }
        }
    }

    static void a(a arg0, com.d.a.a.a.a.a.a$a arg1, boolean arg2) {
        arg0.a(arg1, arg2);
    }

    public c a(String arg13) {
        c v3_1;
        int v2;
        InputStream[] v9;
        c v1;
        Object v0;
        __monitor_enter(this);
        try {
            this.g();
            this.e(arg13);
            v0 = this.n.get(arg13);
            v1 = null;
            if(v0 != null) {
                goto label_9;
            }
        }
        catch(Throwable v13) {
            goto label_68;
        }

        __monitor_exit(this);
        return v1;
        try {
        label_9:
            if(b.d(((b)v0))) {
                goto label_13;
            }
        }
        catch(Throwable v13) {
            goto label_68;
        }

        __monitor_exit(this);
        return v1;
        try {
        label_13:
            File[] v8 = new File[this.j];
            v9 = new InputStream[this.j];
            v2 = 0;
            int v3 = 0;
            try {
                while(v3 < this.j) {
                    File v4 = ((b)v0).a(v3);
                    v8[v3] = v4;
                    v9[v3] = new FileInputStream(v4);
                    ++v3;
                }
            }
            catch(FileNotFoundException ) {
                goto label_57;
            }

            ++this.o;
            Writer v1_1 = this.m;
            v1_1.append("READ " + arg13 + '\n');
            if(this.f()) {
                this.b.submit(this.q);
            }

            v3_1 = new c(this, arg13, b.e(((b)v0)), v8, v9, b.b(((b)v0)), null);
        }
        catch(Throwable v13) {
            goto label_68;
        }

        __monitor_exit(this);
        return v3_1;
        try {
        label_57:
            while(v2 < this.j) {
                if(v9[v2] == null) {
                    break;
                }

                d.a(v9[v2]);
                ++v2;
            }
        }
        catch(Throwable v13) {
            goto label_68;
        }

        __monitor_exit(this);
        return v1;
    label_68:
        __monitor_exit(this);
        throw v13;
    }

    static OutputStream b() {
        return a.r;
    }

    static void b(a arg0) {
        arg0.h();
    }

    public com.d.a.a.a.a.a.a$a b(String arg3) {
        return this.a(arg3, -1);
    }

    private void c() {
        String v5;
        String v4;
        String v2;
        String v1_1;
        com.d.a.a.a.a.a.c v0 = new com.d.a.a.a.a.a.c(new FileInputStream(this.d), d.a);
        try {
            v1_1 = v0.a();
            v2 = v0.a();
            String v3 = v0.a();
            v4 = v0.a();
            v5 = v0.a();
            if(("libcore.io.DiskLruCache".equals(v1_1)) && ("1".equals(v2)) && (Integer.toString(this.g).equals(v3)) && (Integer.toString(this.j).equals(v4)) && ("".equals(v5))) {
                goto label_28;
            }

            goto label_39;
        }
        catch(Throwable v1) {
            goto label_60;
        }

    label_28:
        int v1_2 = 0;
        try {
            while(true) {
                this.d(v0.a());
                ++v1_2;
            }
        }
        catch(Throwable v1) {
        }
        catch(EOFException ) {
            try {
                this.o = v1_2 - this.n.size();
            }
            catch(Throwable v1) {
                goto label_60;
            }

            d.a(((Closeable)v0));
            return;
            try {
            label_39:
                StringBuilder v6 = new StringBuilder();
                v6.append("unexpected journal header: [");
                v6.append(v1_1);
                v6.append(", ");
                v6.append(v2);
                v6.append(", ");
                v6.append(v4);
                v6.append(", ");
                v6.append(v5);
                v6.append("]");
                throw new IOException(v6.toString());
            }
            catch(Throwable v1) {
            label_60:
                d.a(((Closeable)v0));
                throw v1;
            }
        }
    }

    static void c(a arg0) {
        arg0.i();
    }

    public boolean c(String arg9) {
        __monitor_enter(this);
        try {
            this.g();
            this.e(arg9);
            Object v0 = this.n.get(arg9);
            int v1 = 0;
            if(v0 != null) {
                if(b.a(((b)v0)) != null) {
                    goto label_64;
                }

                while(v1 < this.j) {
                    File v2 = ((b)v0).a(v1);
                    if(v2.exists()) {
                        if(v2.delete()) {
                        }
                        else {
                            StringBuilder v0_1 = new StringBuilder();
                            v0_1.append("failed to delete ");
                            v0_1.append(v2);
                            throw new IOException(v0_1.toString());
                        }
                    }

                    this.k -= b.b(((b)v0))[v1];
                    --this.l;
                    b.b(((b)v0))[v1] = 0;
                    ++v1;
                }

                ++this.o;
                Writer v0_2 = this.m;
                v0_2.append("REMOVE " + arg9 + '\n');
                this.n.remove(arg9);
                if(this.f()) {
                    this.b.submit(this.q);
                }

                goto label_62;
            }

            goto label_64;
        }
        catch(Throwable v9) {
            goto label_67;
        }

    label_62:
        __monitor_exit(this);
        return 1;
    label_67:
        __monitor_exit(this);
        throw v9;
    label_64:
        __monitor_exit(this);
        return 0;
    }

    public void close() {
        __monitor_enter(this);
        try {
            if(this.m != null) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_27;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            Iterator v0_1 = new ArrayList(this.n.values()).iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                if(b.a(((b)v1)) == null) {
                    continue;
                }

                b.a(((b)v1)).b();
            }

            this.h();
            this.i();
            this.m.close();
            this.m = null;
        }
        catch(Throwable v0) {
            goto label_27;
        }

        __monitor_exit(this);
        return;
    label_27:
        __monitor_exit(this);
        throw v0;
    }

    private void d() {
        a.a(this.e);
        Iterator v0 = this.n.values().iterator();
    label_5:
        if(v0.hasNext()) {
            Object v1 = v0.next();
            int v3 = 0;
            if(b.a(((b)v1)) == null) {
                while(true) {
                    if(v3 >= this.j) {
                        goto label_5;
                    }

                    this.k += b.b(((b)v1))[v3];
                    ++this.l;
                    ++v3;
                }
            }

            b.a(((b)v1), null);
            while(v3 < this.j) {
                a.a(((b)v1).a(v3));
                a.a(((b)v1).b(v3));
                ++v3;
            }

            v0.remove();
            goto label_5;
        }
    }

    private void d(String arg8) {
        String v3_1;
        int v0 = 32;
        int v1 = arg8.indexOf(v0);
        int v2 = -1;
        if(v1 == v2) {
            goto label_70;
        }

        int v3 = v1 + 1;
        v0 = arg8.indexOf(v0, v3);
        if(v0 == v2) {
            v3_1 = arg8.substring(v3);
            if(v1 == "REMOVE".length() && (arg8.startsWith("REMOVE"))) {
                this.n.remove(v3_1);
                return;
            }
        }
        else {
            v3_1 = arg8.substring(v3, v0);
        }

        Object v4 = this.n.get(v3_1);
        com.d.a.a.a.a.a.a$1 v5 = null;
        if(v4 == null) {
            b v4_1 = new b(this, v3_1, v5);
            this.n.put(v3_1, v4_1);
        }

        if(v0 == v2 || v1 != "CLEAN".length() || !arg8.startsWith("CLEAN")) {
            if(v0 == v2 && v1 == "DIRTY".length() && (arg8.startsWith("DIRTY"))) {
                b.a(((b)v4), new com.d.a.a.a.a.a.a$a(this, ((b)v4), v5));
                return;
            }

            if(v0 == v2 && v1 == "READ".length() && (arg8.startsWith("READ"))) {
                return;
            }

            goto label_61;
        }
        else {
            String[] v8 = arg8.substring(v0 + 1).split(" ");
            b.a(((b)v4), true);
            b.a(((b)v4), ((com.d.a.a.a.a.a.a$a)v5));
            b.a(((b)v4), v8);
        }

        return;
    label_61:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("unexpected journal line: ");
        v1_1.append(arg8);
        throw new IOException(v1_1.toString());
    label_70:
        v1_1 = new StringBuilder();
        v1_1.append("unexpected journal line: ");
        v1_1.append(arg8);
        throw new IOException(v1_1.toString());
    }

    static boolean d(a arg0) {
        return arg0.f();
    }

    private void e() {
        BufferedWriter v0_1;
        __monitor_enter(this);
        try {
            if(this.m != null) {
                this.m.close();
            }

            v0_1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), d.a));
        }
        catch(Throwable v0) {
            goto label_92;
        }

        try {
            ((Writer)v0_1).write("libcore.io.DiskLruCache");
            ((Writer)v0_1).write("\n");
            ((Writer)v0_1).write("1");
            ((Writer)v0_1).write("\n");
            ((Writer)v0_1).write(Integer.toString(this.g));
            ((Writer)v0_1).write("\n");
            ((Writer)v0_1).write(Integer.toString(this.j));
            ((Writer)v0_1).write("\n");
            ((Writer)v0_1).write("\n");
            Iterator v1_1 = this.n.values().iterator();
            while(v1_1.hasNext()) {
                Object v2 = v1_1.next();
                char v4 = '\n';
                String v2_1 = b.a(((b)v2)) != null ? "DIRTY " + b.c(((b)v2)) + v4 : "CLEAN " + b.c(((b)v2)) + ((b)v2).a() + v4;
                ((Writer)v0_1).write(v2_1);
            }
        }
        catch(Throwable v1) {
            goto label_89;
        }

        try {
            ((Writer)v0_1).close();
            if(this.d.exists()) {
                a.a(this.d, this.f, true);
            }

            a.a(this.e, this.d, false);
            this.f.delete();
            this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), d.a));
        }
        catch(Throwable v0) {
            goto label_92;
        }

        __monitor_exit(this);
        return;
        try {
        label_89:
            ((Writer)v0_1).close();
            throw v1;
        }
        catch(Throwable v0) {
        label_92:
            __monitor_exit(this);
            throw v0;
        }
    }

    static void e(a arg0) {
        arg0.e();
    }

    private void e(String arg4) {
        if(a.a.matcher(((CharSequence)arg4)).matches()) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("keys must match regex [a-z0-9_-]{1,64}: \"");
        v1.append(arg4);
        v1.append("\"");
        throw new IllegalArgumentException(v1.toString());
    }

    private boolean f() {
        boolean v0 = this.o < 2000 || this.o < this.n.size() ? false : true;
        return v0;
    }

    static int f(a arg0) {
        return arg0.j;
    }

    static File g(a arg0) {
        return arg0.c;
    }

    private void g() {
        if(this.m != null) {
            return;
        }

        throw new IllegalStateException("cache is closed");
    }

    private void h() {
        while(this.k > this.h) {
            this.c(this.n.entrySet().iterator().next().getKey());
        }
    }

    private void i() {
        while(this.l > this.i) {
            this.c(this.n.entrySet().iterator().next().getKey());
        }
    }
}

