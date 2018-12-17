package android.support.v8.renderscript;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RenderScript {
    public enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        int d;

        static {
            a.a = new a("NORMAL", 0, 0);
            a.b = new a("DEBUG", 1, 1);
            a.c = new a("PROFILE", 2, 2);
            a.e = new a[]{a.a, a.b, a.c};
        }

        private a(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.d = arg3;
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    class b extends Thread {
        RenderScript a;
        boolean b;
        int[] c;

        b(RenderScript arg2) {
            super("RSMessageThread");
            this.b = true;
            this.c = new int[2];
            this.a = arg2;
        }

        public void run() {
            int[] v0 = new int[16];
            this.a.nContextInitToClient(this.a.i);
            while(this.b) {
                v0[0] = 0;
                int v2 = this.a.nContextPeekMessage(this.a.i, this.c);
                int v3 = this.c[1];
                int v4 = this.c[0];
                int v5 = 4;
                if(v2 == v5) {
                    if(v3 >> 2 >= v0.length) {
                        v0 = new int[v3 + 3 >> 2];
                    }

                    if(this.a.nContextGetUserMessage(this.a.i, v0) == v5) {
                        if(this.a.t != null) {
                            this.a.t.a = v0;
                            this.a.t.b = v4;
                            this.a.t.c = v3;
                            this.a.t.run();
                            continue;
                        }

                        throw new g("Received a message from the script with no message handler installed.");
                    }

                    throw new e("Error processing message from RenderScript.");
                }

                if(v2 == 3) {
                    String v1 = this.a.nContextGetErrorMessage(this.a.i);
                    if(v4 < 4096) {
                        if(v4 >= 2048) {
                            if(this.a.h != a.b) {
                            }
                            else if(this.a.u != null) {
                                goto label_74;
                            }

                            goto label_96;
                        }

                    label_74:
                        if(this.a.u != null) {
                            this.a.u.a = v1;
                            this.a.u.b = v4;
                            this.a.u.run();
                            continue;
                        }

                        Log.e("RenderScript_jni", "non fatal RS error, " + v1);
                        continue;
                    }

                label_96:
                    Log.e("RenderScript_jni", "fatal RS error, " + v1);
                    StringBuilder v2_1 = new StringBuilder();
                    v2_1.append("Fatal error ");
                    v2_1.append(v4);
                    v2_1.append(", details: ");
                    v2_1.append(v1);
                    throw new h(v2_1.toString());
                }

                long v2_2 = 1;
                try {
                    b.sleep(v2_2, 0);
                }
                catch(InterruptedException ) {
                }
            }
        }
    }

    public class c implements Runnable {
        protected String a;
        protected int b;

        public void run() {
        }
    }

    public class d implements Runnable {
        protected int[] a;
        protected int b;
        protected int c;

        public void run() {
        }
    }

    private int A;
    private Context B;
    private String C;
    private static String D;
    private static int E;
    private static int F;
    private static boolean G;
    private static boolean H;
    private boolean I;
    static boolean a;
    static boolean b;
    static Object c;
    static Method d;
    static Method e;
    static Object f;
    static int g;
    a h;
    long i;
    long j;
    boolean k;
    ReentrantReadWriteLock l;
    b m;
    android.support.v8.renderscript.c n;
    android.support.v8.renderscript.c o;
    android.support.v8.renderscript.c p;
    android.support.v8.renderscript.c q;
    android.support.v8.renderscript.c r;
    android.support.v8.renderscript.c s;
    d t;
    c u;
    private static ArrayList v;
    private boolean w;
    private boolean x;
    private int y;
    private int z;

    static {
        RenderScript.v = new ArrayList();
        RenderScript.D = "";
        RenderScript.f = new Object();
        RenderScript.E = -1;
        RenderScript.F = -1;
        RenderScript.G = false;
    }

    RenderScript(Context arg4) {
        super();
        this.w = false;
        this.x = false;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.I = false;
        this.t = null;
        this.u = null;
        this.h = a.a;
        if(arg4 != null) {
            this.B = arg4.getApplicationContext();
            this.C = this.B.getApplicationInfo().nativeLibraryDir;
        }

        this.j = 0;
        this.k = false;
        this.l = new ReentrantReadWriteLock();
    }

    public static RenderScript a(Context arg1) {
        return RenderScript.a(arg1, a.a);
    }

    public static RenderScript a(Context arg1, a arg2) {
        return RenderScript.a(arg1, arg2, 0);
    }

    public static RenderScript a(Context arg4, int arg5, a arg6, int arg7) {
        Object v2;
        ArrayList v0 = RenderScript.v;
        __monitor_enter(v0);
        try {
            Iterator v1 = RenderScript.v.iterator();
            do {
            label_4:
                if(!v1.hasNext()) {
                    goto label_15;
                }

                v2 = v1.next();
                if(((RenderScript)v2).h != arg6) {
                    goto label_4;
                }

                if(((RenderScript)v2).z != arg7) {
                    goto label_4;
                }
            }
            while(((RenderScript)v2).A != arg5);

            __monitor_exit(v0);
            return ((RenderScript)v2);
        label_15:
            RenderScript v4_1 = RenderScript.b(arg4, arg5, arg6, arg7);
            v4_1.w = true;
            RenderScript.v.add(v4_1);
            __monitor_exit(v0);
            return v4_1;
        label_23:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_23;
        }

        throw v4;
    }

    public static RenderScript a(Context arg1, a arg2, int arg3) {
        return RenderScript.a(arg1, arg1.getApplicationInfo().targetSdkVersion, arg2, arg3);
    }

    private static boolean a(int arg9, Context arg10) {
        long v7;
        long v5;
        int v0;
        if(Build$VERSION.SDK_INT < arg9 && Build$VERSION.SDK_INT < 21) {
            RenderScript.E = 0;
        }

        if(RenderScript.E == -1) {
            Object v9 = null;
            try {
                v0 = Class.forName("android.os.SystemProperties").getDeclaredMethod("getInt", String.class, Integer.TYPE).invoke(v9, "debug.rs.forcecompat", new Integer(0)).intValue();
            }
            catch(Exception ) {
                v0 = 0;
            }

            int v4 = 19;
            RenderScript.E = Build$VERSION.SDK_INT < v4 || v0 != 0 ? 0 : 1;
            if(RenderScript.E != 1) {
                goto label_73;
            }

            try {
                ApplicationInfo v10 = arg10.getPackageManager().getApplicationInfo(arg10.getPackageName(), 128);
                v5 = 0;
            }
            catch(PackageManager$NameNotFoundException ) {
                return 1;
            }

            try {
                v7 = Class.forName("android.renderscript.RenderScript").getDeclaredMethod("getMinorID").invoke(v9).longValue();
            }
            catch(Exception ) {
                v7 = v5;
            }

            if(v10.metaData == null) {
                goto label_73;
            }

            if((v10.metaData.getBoolean("com.android.support.v8.renderscript.EnableAsyncTeardown")) && v7 == v5) {
                RenderScript.E = 0;
            }

            if(!v10.metaData.getBoolean("com.android.support.v8.renderscript.EnableBlurWorkaround")) {
                goto label_73;
            }

            if(Build$VERSION.SDK_INT > v4) {
                goto label_73;
            }

            RenderScript.E = 0;
        }

    label_73:
        if(RenderScript.E == 1) {
            if(RenderScript.D.length() > 0) {
                StringBuilder v9_1 = new StringBuilder();
                v9_1.append('(');
                v9_1.append(Build.MANUFACTURER);
                v9_1.append(':');
                v9_1.append(Build.PRODUCT);
                v9_1.append(':');
                v9_1.append(Build.MODEL);
                v9_1.append(')');
                if(RenderScript.D.contains(v9_1.toString())) {
                    RenderScript.E = 0;
                    return 0;
                }
            }

            return 1;
        }

        return 0;
    }

    long a(long arg1, int arg3, int arg4, int arg5, String arg6) {
        __monitor_enter(this);
        try {
            arg1 = this.rsnContextCreate(arg1, arg3, arg4, arg5, arg6);
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return arg1;
    }

    long a(int arg12, long arg13, boolean arg15) {
        long v12_2;
        __monitor_enter(this);
        try {
            this.f();
            if(!arg15) {
                goto label_70;
            }

            if(Build$VERSION.SDK_INT < 21) {
                goto label_63;
            }

            if(!this.k) {
                try {
                    System.loadLibrary("RSSupport");
                    int v0 = 23;
                }
                catch(UnsatisfiedLinkError v12_1) {
                    Log.e("RenderScript_jni", "Error loading RS Compat library for Incremental Intrinsic Support: " + v12_1);
                    v14 = new StringBuilder();
                    v14.append("Error loading RS Compat library for Incremental Intrinsic Support: ");
                    v14.append(v12_1);
                    throw new h(v14.toString());
                }

                StringBuilder v1 = new StringBuilder();
                v1.append(this.C);
                v1.append("/libRSSupport.so");
                if(this.nIncLoadSO(v0, v1.toString())) {
                    this.k = true;
                }
                else {
                    throw new h("Error loading libRSSupport library for Incremental Intrinsic Support");
                }
            }

            if(this.j == 0) {
                this.j = this.a(this.nIncDeviceCreate(), 0, 0, 0);
            }

            v12_2 = this.rsnScriptIntrinsicCreate(this.j, arg12, arg13, arg15);
        }
        catch(Throwable v12) {
            goto label_79;
        }

        __monitor_exit(this);
        return v12_2;
        try {
        label_63:
            Log.e("RenderScript_jni", "Incremental Intrinsics are not supported, please change targetSdkVersion to >= 21");
            throw new h("Incremental Intrinsics are not supported before Lollipop (API 21)");
        label_70:
            v12_2 = this.rsnScriptIntrinsicCreate(this.i, arg12, arg13, arg15);
        }
        catch(Throwable v12) {
        label_79:
            __monitor_exit(this);
            throw v12;
        }

        __monitor_exit(this);
        return v12_2;
    }

    long a(long arg1, int arg3, int arg4, int arg5) {
        __monitor_enter(this);
        try {
            arg1 = this.rsnIncContextCreate(arg1, arg3, arg4, arg5);
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return arg1;
    }

    long a(long arg14, int arg16, int arg17, int arg18, boolean arg19, boolean arg20, int arg21) {
        long v0_1;
        RenderScript v12 = this;
        __monitor_enter(this);
        try {
            this.f();
            v0_1 = this.rsnTypeCreate(v12.i, arg14, arg16, arg17, arg18, arg19, arg20, arg21);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    long a(long arg9, int arg11, Bitmap arg12, int arg13) {
        __monitor_enter(this);
        try {
            this.f();
            arg9 = this.rsnAllocationCreateFromBitmap(this.i, arg9, arg11, arg12, arg13);
        }
        catch(Throwable v9) {
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
        return arg9;
    }

    long a(long arg9, int arg11, boolean arg12, int arg13) {
        __monitor_enter(this);
        try {
            this.f();
            arg9 = this.rsnElementCreate(this.i, arg9, arg11, arg12, arg13);
        }
        catch(Throwable v9) {
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
        return arg9;
    }

    long a(long arg11, long arg13, int arg15) {
        __monitor_enter(this);
        try {
            this.f();
            arg11 = this.rsnIncAllocationCreateTyped(this.i, this.j, arg11, arg13, arg15);
        }
        catch(Throwable v11) {
            __monitor_exit(this);
            throw v11;
        }

        __monitor_exit(this);
        return arg11;
    }

    void a(long arg6) {
        if(this.i != 0) {
            this.rsnObjDestroy(this.i, arg6);
        }
    }

    void a(long arg9, int arg11, float arg12, boolean arg13) {
        __monitor_enter(this);
        try {
            this.f();
            long v0 = this.i;
            if(arg13) {
                v0 = this.j;
            }

            this.rsnScriptSetVarF(v0, arg9, arg11, arg12, arg13);
        }
        catch(Throwable v9) {
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
    }

    void a(long arg17, int arg19, long arg20, long arg22, byte[] arg24, boolean arg25) {
        RenderScript v15 = this;
        __monitor_enter(this);
        try {
            this.f();
            if(arg24 == null) {
                this.rsnScriptForEach(v15.i, v15.j, arg17, arg19, arg20, arg22, arg25);
            }
            else {
                this.rsnScriptForEach(v15.i, v15.j, arg17, arg19, arg20, arg22, arg24, arg25);
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    void a(long arg10, int arg12, long arg13, boolean arg15) {
        __monitor_enter(this);
        try {
            this.f();
            long v0 = this.i;
            if(arg15) {
                v0 = this.j;
            }

            this.rsnScriptSetVarObj(v0, arg10, arg12, arg13, arg15);
        }
        catch(Throwable v10) {
            __monitor_exit(this);
            throw v10;
        }

        __monitor_exit(this);
    }

    void a(long arg7, Bitmap arg9) {
        __monitor_enter(this);
        try {
            this.f();
            this.rsnAllocationCopyToBitmap(this.i, arg7, arg9);
        }
        catch(Throwable v7) {
            __monitor_exit(this);
            throw v7;
        }

        __monitor_exit(this);
    }

    boolean a() {
        return RenderScript.H;
    }

    private static RenderScript b(Context arg10, int arg11, a arg12, int arg13) {
        String v1_1;
        String v0_2;
        String v10_1;
        int v2;
        RenderScript v7 = new RenderScript(arg10);
        if(RenderScript.F == -1) {
            RenderScript.F = arg11;
        }
        else {
            if(RenderScript.F == arg11) {
                goto label_9;
            }

            goto label_224;
        }

    label_9:
        RenderScript.H = RenderScript.a(RenderScript.F, arg10);
        Object v10 = RenderScript.f;
        __monitor_enter(v10);
        try {
            Object v1 = null;
            v2 = 2301;
            int v3 = 23;
            if(RenderScript.a) {
                goto label_87;
            }

            try {
                Class v0 = Class.forName("dalvik.system.VMRuntime");
                RenderScript.c = v0.getDeclaredMethod("getRuntime").invoke(v1);
                RenderScript.d = v0.getDeclaredMethod("registerNativeAllocation", Integer.TYPE);
                RenderScript.e = v0.getDeclaredMethod("registerNativeFree", Integer.TYPE);
                RenderScript.b = true;
            }
            catch(Exception ) {
                Log.e("RenderScript_jni", "No GC methods");
                RenderScript.b = false;
            }

            try {
                if(Build$VERSION.SDK_INT >= v3 || v7.C == null) {
                    System.loadLibrary("rsjni");
                }
                else {
                    System.load(v7.C + "/librsjni.so");
                }

                RenderScript.a = true;
                RenderScript.g = RenderScript.rsnSystemGetPointerSize();
                goto label_87;
            }
            catch(UnsatisfiedLinkError v11_1) {
                try {
                    Log.e("RenderScript_jni", "Error loading RS jni library: " + v11_1);
                    v13 = new StringBuilder();
                    v13.append("Error loading RS jni library: ");
                    v13.append(v11_1);
                    v13.append(" Support lib API: ");
                    v13.append(v2);
                    throw new h(v13.toString());
                label_87:
                    __monitor_exit(v10);
                }
                catch(Throwable v11) {
                    try {
                    label_222:
                        __monitor_exit(v10);
                    }
                    catch(Throwable v11) {
                        goto label_222;
                    }

                    throw v11;
                }
            }
        }
        catch(Throwable v11) {
            goto label_222;
        }

        if(RenderScript.H) {
            v10_1 = "RenderScript_jni";
            v0_2 = "RS native mode";
        }
        else {
            v10_1 = "RenderScript_jni";
            v0_2 = "RS compat mode";
        }

        Log.v(v10_1, v0_2);
        if(Build$VERSION.SDK_INT >= 14) {
            RenderScript.G = true;
        }

        int v10_2 = arg11 < Build$VERSION.SDK_INT ? Build$VERSION.SDK_INT : arg11;
        if(Build$VERSION.SDK_INT < v3 && v7.C != null) {
            v1_1 = v7.C + "/libRSSupport.so";
        }

        if(!v7.nLoadSO(RenderScript.H, v10_2, v1_1)) {
            if(RenderScript.H) {
                Log.v("RenderScript_jni", "Unable to load libRS.so, falling back to compat mode");
                RenderScript.H = false;
            }

            try {
                if(Build$VERSION.SDK_INT >= v3 || v7.C == null) {
                    System.loadLibrary("RSSupport");
                }
                else {
                    System.load(v1_1);
                }
            }
            catch(UnsatisfiedLinkError v10_3) {
                Log.e("RenderScript_jni", "Error loading RS Compat library: " + v10_3 + " Support lib version: " + v2);
                StringBuilder v12 = new StringBuilder();
                v12.append("Error loading RS Compat library: ");
                v12.append(v10_3);
                v12.append(" Support lib version: ");
                v12.append(v2);
                throw new h(v12.toString());
            }

            if(v7.nLoadSO(false, v10_2, v1_1)) {
                goto label_168;
            }

            Log.e("RenderScript_jni", "Error loading RS Compat library: nLoadSO() failed; Support lib version: 2301");
            throw new h("Error loading libRSSupport library, Support lib version: 2301");
        }

    label_168:
        if(RenderScript.G) {
            try {
                System.loadLibrary("RSSupportIO");
            }
            catch(UnsatisfiedLinkError ) {
                RenderScript.G = false;
            }

            if((RenderScript.G) && (v7.nLoadIOSO())) {
                goto label_182;
            }

            Log.v("RenderScript_jni", "Unable to load libRSSupportIO.so, USAGE_IO not supported");
            RenderScript.G = false;
        }

    label_182:
        if(v10_2 >= v3) {
            v7.x = true;
            try {
                System.loadLibrary("blasV8");
            }
            catch(UnsatisfiedLinkError v0_3) {
                Log.v("RenderScript_jni", "Unable to load BLAS lib, ONLY BNNM will be supported: " + v0_3);
            }
        }

        v7.i = v7.a(v7.nDeviceCreate(), 0, arg11, arg12.d, v7.C);
        v7.h = arg12;
        v7.z = arg13;
        v7.A = arg11;
        v7.y = v10_2;
        if(v7.i != 0) {
            v7.m = new b(v7);
            v7.m.start();
            return v7;
        }

        throw new e("Failed to create RS context.");
    label_224:
        throw new h("Can\'t have two contexts with different SDK versions in support lib");
    }

    void b() {
        __monitor_enter(this);
        try {
            this.f();
            ReentrantReadWriteLock$WriteLock v0_1 = this.l.writeLock();
            v0_1.lock();
            long v1 = this.i;
            this.i = 0;
            v0_1.unlock();
            this.rsnContextDestroy(v1);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    long b(long arg14, int arg16, int arg17, int arg18, boolean arg19, boolean arg20, int arg21) {
        long v0_1;
        RenderScript v12 = this;
        __monitor_enter(this);
        try {
            this.f();
            v0_1 = this.rsnIncTypeCreate(v12.j, arg14, arg16, arg17, arg18, arg19, arg20, arg21);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    long b(long arg9, int arg11, Bitmap arg12, int arg13) {
        __monitor_enter(this);
        try {
            this.f();
            arg9 = this.rsnAllocationCreateBitmapBackedAllocation(this.i, arg9, arg11, arg12, arg13);
        }
        catch(Throwable v9) {
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
        return arg9;
    }

    long b(long arg9, int arg11, boolean arg12, int arg13) {
        __monitor_enter(this);
        try {
            this.f();
            arg9 = this.rsnIncElementCreate(this.j, arg9, arg11, arg12, arg13);
        }
        catch(Throwable v9) {
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
        return arg9;
    }

    void c() {
        __monitor_enter(this);
        try {
            this.f();
            this.rsnContextFinish(this.i);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    void d() {
        __monitor_enter(this);
        try {
            this.f();
            ReentrantReadWriteLock$WriteLock v0_1 = this.l.writeLock();
            v0_1.lock();
            long v1 = this.j;
            this.j = 0;
            v0_1.unlock();
            this.rsnIncContextDestroy(v1);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    void e() {
        __monitor_enter(this);
        try {
            this.f();
            this.rsnIncContextFinish(this.j);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    void f() {
        if(this.i != 0) {
            return;
        }

        throw new g("Calling RS with no Context active.");
    }

    protected void finalize() {
        this.h();
        super.finalize();
    }

    boolean g() {
        boolean v0 = this.i != 0 ? true : false;
        return v0;
    }

    private void h() {
        int v0_1;
        int v2;
        __monitor_enter(this);
        try {
            v2 = 0;
            if(!this.I) {
                this.I = true;
                v0_1 = 1;
            }
            else {
                v0_1 = 0;
            }

            __monitor_exit(this);
            if(v0_1 == 0) {
                return;
            }
        }
        catch(Throwable v0) {
            try {
            label_41:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_41;
            }

            throw v0;
        }

        this.c();
        long v5 = 0;
        if(this.j != v5) {
            this.e();
            this.d();
            this.j = v5;
        }

        this.nContextDeinitToClient(this.i);
        this.m.b = false;
        this.m.interrupt();
        v0_1 = 0;
        while(v2 == 0) {
            try {
                this.m.join();
                v2 = 1;
            }
            catch(InterruptedException ) {
                v0_1 = 1;
            }
        }

        if(v0_1 != 0) {
            Log.v("RenderScript_jni", "Interrupted during wait for MessageThread to join");
            Thread.currentThread().interrupt();
        }

        this.b();
    }

    native void nContextDeinitToClient(long arg1) {
    }

    native String nContextGetErrorMessage(long arg1) {
    }

    native int nContextGetUserMessage(long arg1, int[] arg2) {
    }

    native void nContextInitToClient(long arg1) {
    }

    native int nContextPeekMessage(long arg1, int[] arg2) {
    }

    native long nDeviceCreate() {
    }

    native void nDeviceDestroy(long arg1) {
    }

    native void nDeviceSetConfig(long arg1, int arg2, int arg3) {
    }

    native long nIncDeviceCreate() {
    }

    native void nIncDeviceDestroy(long arg1) {
    }

    native boolean nIncLoadSO(int arg1, String arg2) {
    }

    native boolean nLoadIOSO() {
    }

    native boolean nLoadSO(boolean arg1, int arg2, String arg3) {
    }

    native void rsnAllocationCopyFromBitmap(long arg1, long arg2, Bitmap arg3) {
    }

    native void rsnAllocationCopyToBitmap(long arg1, long arg2, Bitmap arg3) {
    }

    native long rsnAllocationCreateBitmapBackedAllocation(long arg1, long arg2, int arg3, Bitmap arg4, int arg5) {
    }

    native long rsnAllocationCreateBitmapRef(long arg1, long arg2, Bitmap arg3) {
    }

    native long rsnAllocationCreateFromAssetStream(long arg1, int arg2, int arg3, int arg4) {
    }

    native long rsnAllocationCreateFromBitmap(long arg1, long arg2, int arg3, Bitmap arg4, int arg5) {
    }

    native long rsnAllocationCreateTyped(long arg1, long arg2, int arg3, int arg4, long arg5) {
    }

    native long rsnAllocationCubeCreateFromBitmap(long arg1, long arg2, int arg3, Bitmap arg4, int arg5) {
    }

    native void rsnAllocationData1D(long arg1, long arg2, int arg3, int arg4, int arg5, Object arg6, int arg7, int arg8, int arg9, boolean arg10) {
    }

    native void rsnAllocationData2D(long arg1, long arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, long arg9, int arg10, int arg11, int arg12, int arg13) {
    }

    native void rsnAllocationData2D(long arg1, long arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, Object arg9, int arg10, int arg11, int arg12, boolean arg13) {
    }

    native void rsnAllocationData2D(long arg1, long arg2, int arg3, int arg4, int arg5, int arg6, Bitmap arg7) {
    }

    native void rsnAllocationData3D(long arg1, long arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, long arg10, int arg11, int arg12, int arg13, int arg14) {
    }

    native void rsnAllocationData3D(long arg1, long arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, Object arg10, int arg11, int arg12, int arg13, boolean arg14) {
    }

    native void rsnAllocationElementData1D(long arg1, long arg2, int arg3, int arg4, int arg5, byte[] arg6, int arg7) {
    }

    native void rsnAllocationGenerateMipmaps(long arg1, long arg2) {
    }

    native ByteBuffer rsnAllocationGetByteBuffer(long arg1, long arg2, int arg3, int arg4, int arg5) {
    }

    native long rsnAllocationGetStride(long arg1, long arg2) {
    }

    native long rsnAllocationGetType(long arg1, long arg2) {
    }

    native void rsnAllocationIoReceive(long arg1, long arg2) {
    }

    native void rsnAllocationIoSend(long arg1, long arg2) {
    }

    native void rsnAllocationRead(long arg1, long arg2, Object arg3, int arg4, int arg5, boolean arg6) {
    }

    native void rsnAllocationRead1D(long arg1, long arg2, int arg3, int arg4, int arg5, Object arg6, int arg7, int arg8, int arg9, boolean arg10) {
    }

    native void rsnAllocationRead2D(long arg1, long arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, Object arg9, int arg10, int arg11, int arg12, boolean arg13) {
    }

    native void rsnAllocationResize1D(long arg1, long arg2, int arg3) {
    }

    native void rsnAllocationResize2D(long arg1, long arg2, int arg3, int arg4) {
    }

    native void rsnAllocationSetSurface(long arg1, long arg2, Surface arg3) {
    }

    native void rsnAllocationSyncAll(long arg1, long arg2, int arg3) {
    }

    native long rsnClosureCreate(long arg1, long arg2, long arg3, long[] arg4, long[] arg5, int[] arg6, long[] arg7, long[] arg8) {
    }

    native void rsnClosureSetArg(long arg1, long arg2, int arg3, long arg4, int arg5) {
    }

    native void rsnClosureSetGlobal(long arg1, long arg2, long arg3, long arg4, int arg5) {
    }

    native long rsnContextCreate(long arg1, int arg2, int arg3, int arg4, String arg5) {
    }

    native void rsnContextDestroy(long arg1) {
    }

    native void rsnContextDump(long arg1, int arg2) {
    }

    native void rsnContextFinish(long arg1) {
    }

    native void rsnContextSendMessage(long arg1, int arg2, int[] arg3) {
    }

    native void rsnContextSetPriority(long arg1, int arg2) {
    }

    native long rsnElementCreate(long arg1, long arg2, int arg3, boolean arg4, int arg5) {
    }

    native long rsnElementCreate2(long arg1, long[] arg2, String[] arg3, int[] arg4) {
    }

    native void rsnElementGetNativeData(long arg1, long arg2, int[] arg3) {
    }

    native void rsnElementGetSubElements(long arg1, long arg2, long[] arg3, String[] arg4, int[] arg5) {
    }

    native long rsnIncAllocationCreateTyped(long arg1, long arg2, long arg3, long arg4, int arg5) {
    }

    native long rsnIncContextCreate(long arg1, int arg2, int arg3, int arg4) {
    }

    native void rsnIncContextDestroy(long arg1) {
    }

    native void rsnIncContextFinish(long arg1) {
    }

    native long rsnIncElementCreate(long arg1, long arg2, int arg3, boolean arg4, int arg5) {
    }

    native void rsnIncObjDestroy(long arg1, long arg2) {
    }

    native long rsnIncTypeCreate(long arg1, long arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7, int arg8) {
    }

    native long rsnInvokeClosureCreate(long arg1, long arg2, byte[] arg3, long[] arg4, long[] arg5, int[] arg6) {
    }

    native void rsnObjDestroy(long arg1, long arg2) {
    }

    native long rsnSamplerCreate(long arg1, int arg2, int arg3, int arg4, int arg5, int arg6, float arg7) {
    }

    native void rsnScriptBindAllocation(long arg1, long arg2, long arg3, int arg4, boolean arg5) {
    }

    native long rsnScriptCCreate(long arg1, String arg2, String arg3, byte[] arg4, int arg5) {
    }

    native long rsnScriptFieldIDCreate(long arg1, long arg2, int arg3, boolean arg4) {
    }

    native void rsnScriptForEach(long arg1, long arg2, long arg3, int arg4, long arg5, long arg6, boolean arg7) {
    }

    native void rsnScriptForEach(long arg1, long arg2, long arg3, int arg4, long arg5, long arg6, byte[] arg7, boolean arg8) {
    }

    native void rsnScriptForEach(long arg1, long arg2, int arg3, long[] arg4, long arg5, byte[] arg6, int[] arg7) {
    }

    native void rsnScriptForEachClipped(long arg1, long arg2, long arg3, int arg4, long arg5, long arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, boolean arg13) {
    }

    native void rsnScriptForEachClipped(long arg1, long arg2, long arg3, int arg4, long arg5, long arg6, byte[] arg7, int arg8, int arg9, int arg10, int arg11, int arg12, int arg13, boolean arg14) {
    }

    native long rsnScriptGroup2Create(long arg1, String arg2, String arg3, long[] arg4) {
    }

    native void rsnScriptGroup2Execute(long arg1, long arg2) {
    }

    native long rsnScriptGroupCreate(long arg1, long[] arg2, long[] arg3, long[] arg4, long[] arg5, long[] arg6) {
    }

    native void rsnScriptGroupExecute(long arg1, long arg2) {
    }

    native void rsnScriptGroupSetInput(long arg1, long arg2, long arg3, long arg4) {
    }

    native void rsnScriptGroupSetOutput(long arg1, long arg2, long arg3, long arg4) {
    }

    native void rsnScriptIntrinsicBLAS_BNNM(long arg1, long arg2, long arg3, int arg4, int arg5, int arg6, long arg7, int arg8, long arg9, int arg10, long arg11, int arg12, int arg13, boolean arg14) {
    }

    native void rsnScriptIntrinsicBLAS_Complex(long arg1, long arg2, long arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, float arg13, float arg14, long arg15, long arg16, float arg17, float arg18, long arg19, int arg20, int arg21, int arg22, int arg23, boolean arg24) {
    }

    native void rsnScriptIntrinsicBLAS_Double(long arg1, long arg2, long arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, double arg13, long arg14, long arg15, double arg16, long arg17, int arg18, int arg19, int arg20, int arg21, boolean arg22) {
    }

    native void rsnScriptIntrinsicBLAS_Single(long arg1, long arg2, long arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, float arg13, long arg14, long arg15, float arg16, long arg17, int arg18, int arg19, int arg20, int arg21, boolean arg22) {
    }

    native void rsnScriptIntrinsicBLAS_Z(long arg1, long arg2, long arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, double arg13, double arg14, long arg15, long arg16, double arg17, double arg18, long arg19, int arg20, int arg21, int arg22, int arg23, boolean arg24) {
    }

    native long rsnScriptIntrinsicCreate(long arg1, int arg2, long arg3, boolean arg4) {
    }

    native void rsnScriptInvoke(long arg1, long arg2, int arg3, boolean arg4) {
    }

    native long rsnScriptInvokeIDCreate(long arg1, long arg2, int arg3) {
    }

    native void rsnScriptInvokeV(long arg1, long arg2, int arg3, byte[] arg4, boolean arg5) {
    }

    native long rsnScriptKernelIDCreate(long arg1, long arg2, int arg3, int arg4, boolean arg5) {
    }

    native void rsnScriptReduce(long arg1, long arg2, int arg3, long[] arg4, long arg5, int[] arg6) {
    }

    native void rsnScriptSetTimeZone(long arg1, long arg2, byte[] arg3, boolean arg4) {
    }

    native void rsnScriptSetVarD(long arg1, long arg2, int arg3, double arg4, boolean arg5) {
    }

    native void rsnScriptSetVarF(long arg1, long arg2, int arg3, float arg4, boolean arg5) {
    }

    native void rsnScriptSetVarI(long arg1, long arg2, int arg3, int arg4, boolean arg5) {
    }

    native void rsnScriptSetVarJ(long arg1, long arg2, int arg3, long arg4, boolean arg5) {
    }

    native void rsnScriptSetVarObj(long arg1, long arg2, int arg3, long arg4, boolean arg5) {
    }

    native void rsnScriptSetVarV(long arg1, long arg2, int arg3, byte[] arg4, boolean arg5) {
    }

    native void rsnScriptSetVarVE(long arg1, long arg2, int arg3, byte[] arg4, long arg5, int[] arg6, boolean arg7) {
    }

    static native int rsnSystemGetPointerSize() {
    }

    native long rsnTypeCreate(long arg1, long arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7, int arg8) {
    }

    native void rsnTypeGetNativeData(long arg1, long arg2, long[] arg3) {
    }
}

