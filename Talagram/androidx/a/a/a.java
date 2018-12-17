package androidx.a.a;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

public abstract class a implements com.google.common.a.a.a {
    class androidx.a.a.a$1 {
    }

    abstract class androidx.a.a.a$a {
        private androidx.a.a.a$a() {
            super();
        }

        androidx.a.a.a$a(androidx.a.a.a$1 arg1) {
            this();
        }

        abstract boolean a(a arg1, d arg2, d arg3);

        abstract boolean a(a arg1, i arg2, i arg3);

        abstract boolean a(a arg1, Object arg2, Object arg3);

        abstract void a(i arg1, i arg2);

        abstract void a(i arg1, Thread arg2);
    }

    final class b {
        static final b a;
        static final b b;
        final boolean c;
        final Throwable d;

        static {
            b v1 = null;
            if(a.a) {
                b.b = v1;
                b.a = v1;
            }
            else {
                b.b = new b(false, ((Throwable)v1));
                b.a = new b(true, ((Throwable)v1));
            }
        }

        b(boolean arg1, Throwable arg2) {
            super();
            this.c = arg1;
            this.d = arg2;
        }
    }

    final class c {
        final class androidx.a.a.a$c$1 extends Throwable {
            androidx.a.a.a$c$1(String arg1) {
                super(arg1);
            }

            public Throwable fillInStackTrace() {
                __monitor_enter(this);
                __monitor_exit(this);
                return this;
            }
        }

        static final c a;
        final Throwable b;

        static {
            c.a = new c(new androidx.a.a.a$c$1("Failure occurred while trying to finish a future."));
        }

        c(Throwable arg1) {
            super();
            this.b = a.b(arg1);
        }
    }

    final class d {
        static final d a;
        final Runnable b;
        final Executor c;
        d d;

        static {
            d.a = new d(null, null);
        }

        d(Runnable arg1, Executor arg2) {
            super();
            this.b = arg1;
            this.c = arg2;
        }
    }

    final class e extends androidx.a.a.a$a {
        final AtomicReferenceFieldUpdater a;
        final AtomicReferenceFieldUpdater b;
        final AtomicReferenceFieldUpdater c;
        final AtomicReferenceFieldUpdater d;
        final AtomicReferenceFieldUpdater e;

        e(AtomicReferenceFieldUpdater arg2, AtomicReferenceFieldUpdater arg3, AtomicReferenceFieldUpdater arg4, AtomicReferenceFieldUpdater arg5, AtomicReferenceFieldUpdater arg6) {
            super(null);
            this.a = arg2;
            this.b = arg3;
            this.c = arg4;
            this.d = arg5;
            this.e = arg6;
        }

        void a(i arg2, i arg3) {
            this.b.lazySet(arg2, arg3);
        }

        void a(i arg2, Thread arg3) {
            this.a.lazySet(arg2, arg3);
        }

        boolean a(a arg2, d arg3, d arg4) {
            return this.d.compareAndSet(arg2, arg3, arg4);
        }

        boolean a(a arg2, i arg3, i arg4) {
            return this.c.compareAndSet(arg2, arg3, arg4);
        }

        boolean a(a arg2, Object arg3, Object arg4) {
            return this.e.compareAndSet(arg2, arg3, arg4);
        }
    }

    final class f implements Runnable {
        final a a;
        final com.google.common.a.a.a b;

        f(a arg1, com.google.common.a.a.a arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public void run() {
            if(this.a.c != this) {
                return;
            }

            if(a.b.a(this.a, this, a.b(this.b))) {
                a.a(this.a);
            }
        }
    }

    final class g extends androidx.a.a.a$a {
        g() {
            super(null);
        }

        void a(i arg1, i arg2) {
            arg1.c = arg2;
        }

        void a(i arg1, Thread arg2) {
            arg1.b = arg2;
        }

        boolean a(a arg2, d arg3, d arg4) {
            __monitor_enter(arg2);
            try {
                if(arg2.d == arg3) {
                    arg2.d = arg4;
                    __monitor_exit(arg2);
                    return 1;
                }

                __monitor_exit(arg2);
                return 0;
            label_11:
                __monitor_exit(arg2);
            }
            catch(Throwable v3) {
                goto label_11;
            }

            throw v3;
        }

        boolean a(a arg2, i arg3, i arg4) {
            __monitor_enter(arg2);
            try {
                if(arg2.e == arg3) {
                    arg2.e = arg4;
                    __monitor_exit(arg2);
                    return 1;
                }

                __monitor_exit(arg2);
                return 0;
            label_11:
                __monitor_exit(arg2);
            }
            catch(Throwable v3) {
                goto label_11;
            }

            throw v3;
        }

        boolean a(a arg2, Object arg3, Object arg4) {
            __monitor_enter(arg2);
            try {
                if(arg2.c == arg3) {
                    arg2.c = arg4;
                    __monitor_exit(arg2);
                    return 1;
                }

                __monitor_exit(arg2);
                return 0;
            label_11:
                __monitor_exit(arg2);
            }
            catch(Throwable v3) {
                goto label_11;
            }

            throw v3;
        }
    }

    final class h extends androidx.a.a.a$a {
        final class androidx.a.a.a$h$1 implements PrivilegedExceptionAction {
            androidx.a.a.a$h$1() {
                super();
            }

            public Unsafe a() {
                Class v0 = Unsafe.class;
                Field[] v1 = v0.getDeclaredFields();
                int v2 = v1.length;
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    Field v4 = v1[v3];
                    v4.setAccessible(true);
                    Object v4_1 = v4.get(null);
                    if(v0.isInstance(v4_1)) {
                        return v0.cast(v4_1);
                    }
                }

                throw new NoSuchFieldError("the Unsafe");
            }

            public Object run() {
                return this.a();
            }
        }

        static final Unsafe a;
        static final long b;
        static final long c;
        static final long d;
        static final long e;
        static final long f;

        static {
            Class v1;
            Object v0_2;
            try {
                Unsafe v0 = Unsafe.getUnsafe();
            }
            catch(SecurityException ) {
                try {
                    v0_2 = AccessController.doPrivileged(new androidx.a.a.a$h$1());
                }
                catch(PrivilegedActionException v0_1) {
                    goto label_40;
                }
            }

            try {
                v1 = a.class;
                goto label_6;
            }
            catch(Exception v0_3) {
            }
            catch(PrivilegedActionException v0_1) {
            label_40:
                throw new RuntimeException("Could not initialize intrinsics", v0_1.getCause());
                try {
                label_6:
                    h.c = ((Unsafe)v0_2).objectFieldOffset(v1.getDeclaredField("waiters"));
                    h.b = ((Unsafe)v0_2).objectFieldOffset(v1.getDeclaredField("listeners"));
                    h.d = ((Unsafe)v0_2).objectFieldOffset(v1.getDeclaredField("value"));
                    h.e = ((Unsafe)v0_2).objectFieldOffset(i.class.getDeclaredField("b"));
                    h.f = ((Unsafe)v0_2).objectFieldOffset(i.class.getDeclaredField("c"));
                    h.a = ((Unsafe)v0_2);
                    return;
                }
                catch(Exception v0_3) {
                    a.b(((Throwable)v0_3));
                    throw new RuntimeException(((Throwable)v0_3));
                }
            }
        }

        h() {
            super(null);
        }

        void a(i arg4, i arg5) {
            h.a.putObject(arg4, h.f, arg5);
        }

        void a(i arg4, Thread arg5) {
            h.a.putObject(arg4, h.e, arg5);
        }

        boolean a(a arg7, d arg8, d arg9) {
            return h.a.compareAndSwapObject(arg7, h.b, arg8, arg9);
        }

        boolean a(a arg7, i arg8, i arg9) {
            return h.a.compareAndSwapObject(arg7, h.c, arg8, arg9);
        }

        boolean a(a arg7, Object arg8, Object arg9) {
            return h.a.compareAndSwapObject(arg7, h.d, arg8, arg9);
        }
    }

    final class i {
        static final i a;
        volatile Thread b;
        volatile i c;

        static {
            i.a = new i(false);
        }

        i() {
            super();
            a.b.a(this, Thread.currentThread());
        }

        i(boolean arg1) {
            super();
        }

        void a() {
            Thread v0 = this.b;
            if(v0 != null) {
                this.b = null;
                LockSupport.unpark(v0);
            }
        }

        void a(i arg2) {
            a.b.a(this, arg2);
        }
    }

    static final boolean a;
    static final androidx.a.a.a$a b;
    volatile Object c;
    volatile d d;
    volatile i e;
    private static final Logger f;
    private static final Object g;

    static {
        g v1_2;
        e v2_1;
        Throwable v2;
        a.a = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        a.f = Logger.getLogger(a.class.getName());
        Throwable v0 = null;
        try {
            h v1_1 = new h();
            v2 = v0;
        }
        catch(Throwable v1) {
            try {
                v2_1 = new e(AtomicReferenceFieldUpdater.newUpdater(i.class, Thread.class, "b"), AtomicReferenceFieldUpdater.newUpdater(i.class, i.class, "c"), AtomicReferenceFieldUpdater.newUpdater(a.class, i.class, "e"), AtomicReferenceFieldUpdater.newUpdater(a.class, d.class, "d"), AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "c"));
            }
            catch(Throwable v0) {
                v2 = v1;
                v1_2 = new g();
                goto label_48;
            }

            e v8 = v2_1;
            v2 = v1;
            e v1_3 = v8;
        }

    label_48:
        a.b = ((androidx.a.a.a$a)v1_2);
        if(v0 != null) {
            a.f.log(Level.SEVERE, "UnsafeAtomicHelper is broken!", v2);
            a.f.log(Level.SEVERE, "SafeAtomicHelper is broken!", v0);
        }

        a.g = new Object();
    }

    protected a() {
        super();
    }

    private d a(d arg5) {
        d v0;
        do {
            v0 = this.d;
        }
        while(!a.b.a(this, v0, d.a));

        d v3 = v0;
        v0 = arg5;
        for(arg5 = v3; arg5 != null; arg5 = v1) {
            d v1 = arg5.d;
            arg5.d = v0;
            v0 = arg5;
        }

        return v0;
    }

    private static Object a(Future arg2) {
        // Method was not decompiled
    }

    private static CancellationException a(String arg1, Throwable arg2) {
        CancellationException v0 = new CancellationException(arg1);
        v0.initCause(arg2);
        return v0;
    }

    private void a(i arg5) {
        i v2;
        Thread v0 = null;
        arg5.b = v0;
        while(true) {
        label_2:
            arg5 = this.e;
            if(arg5 == i.a) {
                return;
            }

            i v1 = ((i)v0);
            while(true) {
            label_7:
                if(arg5 == null) {
                    return;
                }

                v2 = arg5.c;
                if(arg5.b != null) {
                    v1 = arg5;
                }
                else if(v1 != null) {
                    v1.c = v2;
                    if(v1.b == null) {
                        goto label_2;
                    }
                }
                else if(!a.b.a(this, arg5, v2)) {
                    goto label_2;
                }

                break;
            }
        }

        arg5 = v2;
        goto label_7;
    }

    static void a(a arg4) {
        Runnable v1;
        d v4;
        d v0 = null;
        while(true) {
        label_1:
            arg4.d();
            arg4.b();
            v4 = arg4.a(v0);
            while(true) {
            label_4:
                if(v4 == null) {
                    return;
                }

                v0 = v4.d;
                v1 = v4.b;
                if((v1 instanceof f)) {
                    arg4 = ((f)v1).a;
                    if(arg4.c == v1 && (a.b.a(arg4, v1, a.b(((f)v1).b)))) {
                        goto label_1;
                    }
                }
                else {
                    break;
                }

                goto label_20;
            }
        }

        a.b(v1, v4.c);
    label_20:
        v4 = v0;
        goto label_4;
    }

    private void a(StringBuilder arg3) {
        String v0_3;
        try {
            Object v0_2 = a.a(((Future)this));
            arg3.append("SUCCESS, result=[");
            arg3.append(this.d(v0_2));
            arg3.append("]");
            return;
        }
        catch(RuntimeException v0) {
            arg3.append("UNKNOWN, cause=[");
            arg3.append(v0.getClass());
            v0_3 = " thrown from get()]";
        }
        catch(CancellationException ) {
            v0_3 = "CANCELLED";
        }
        catch(ExecutionException v0_1) {
            arg3.append("FAILURE, cause=[");
            arg3.append(v0_1.getCause());
            v0_3 = "]";
        }

        arg3.append(v0_3);
    }

    protected void a() {
    }

    public final void a(Runnable arg4, Executor arg5) {
        a.b(arg4);
        a.b(arg5);
        d v0 = this.d;
        if(v0 != d.a) {
            d v1 = new d(arg4, arg5);
            do {
                v1.d = v0;
                if(!a.b.a(this, v0, v1)) {
                    v0 = this.d;
                    if(v0 != d.a) {
                        continue;
                    }
                }
                else {
                    return;
                }

                goto label_15;
            }
            while(true);

            return;
        }

    label_15:
        a.b(arg4, arg5);
    }

    protected boolean a(com.google.common.a.a.a arg6) {
        c v1;
        a.b(arg6);
        Object v0 = this.c;
        if(v0 == null) {
            Object v3 = null;
            if(!arg6.isDone()) {
                f v0_1 = new f(this, arg6);
                if(a.b.a(this, v3, v0_1)) {
                    try {
                        arg6.a(((Runnable)v0_1), androidx.a.a.b.a);
                    }
                    catch(Throwable v6) {
                        try {
                            v1 = new c(v6);
                        }
                        catch(Throwable ) {
                            v1 = c.a;
                        }

                        a.b.a(this, v0_1, v1);
                    }

                    return 1;
                }
                else {
                    v0 = this.c;
                }
            }
            else if(a.b.a(this, v3, a.b(arg6))) {
                a.a(this);
                return 1;
            }
            else {
                return 0;
            }
        }

        if((v0 instanceof b)) {
            arg6.cancel(((b)v0).c);
        }

        return 0;
    }

    protected boolean a(Object arg3) {
        if(arg3 == null) {
            arg3 = a.g;
        }

        if(a.b.a(this, null, arg3)) {
            a.a(this);
            return 1;
        }

        return 0;
    }

    protected boolean a(Throwable arg3) {
        if(a.b.a(this, null, new c(a.b(arg3)))) {
            a.a(this);
            return 1;
        }

        return 0;
    }

    protected void b() {
    }

    static Object b(com.google.common.a.a.a arg5) {
        Object v2_1;
        b v5_1;
        if((arg5 instanceof a)) {
            Object v5 = ((a)arg5).c;
            if((v5 instanceof b)) {
                Object v0 = v5;
                if(((b)v0).c) {
                    v5_1 = ((b)v0).d != null ? new b(false, ((b)v0).d) : b.b;
                }
            }

            return v5_1;
        }

        boolean v0_1 = arg5.isCancelled();
        if(((a.a ^ 1) & (((int)v0_1))) != 0) {
            return b.b;
        }

        try {
            v2_1 = a.a(((Future)arg5));
            if(v2_1 == null) {
                v2_1 = a.g;
            }
        }
        catch(Throwable v5_2) {
            return new c(v5_2);
        }
        catch(CancellationException v2) {
            if(!v0_1) {
                StringBuilder v3 = new StringBuilder();
                v3.append("get() threw CancellationException, despite reporting isCancelled() == false: ");
                v3.append(arg5);
                return new c(new IllegalArgumentException(v3.toString(), ((Throwable)v2)));
            }
            else {
                return new b(false, ((Throwable)v2));
            }
        }
        catch(ExecutionException v5_3) {
            return new c(v5_3.getCause());
        }

        return v2_1;
    }

    private static void b(Runnable arg5, Executor arg6) {
        try {
            arg6.execute(arg5);
        }
        catch(RuntimeException v0) {
            Logger v1 = a.f;
            Level v2 = Level.SEVERE;
            v1.log(v2, "RuntimeException while executing runnable " + arg5 + " with executor " + arg6, ((Throwable)v0));
        }
    }

    static Object b(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    static void b(Throwable arg1) {
        a.b(arg1);
        if(!(arg1 instanceof RuntimeException)) {
            if(!(arg1 instanceof Error)) {
                return;
            }

            throw arg1;
        }

        throw arg1;
    }

    private Object c(Object arg2) {
        if(!(arg2 instanceof b)) {
            if(!(arg2 instanceof c)) {
                if(arg2 == a.g) {
                    arg2 = null;
                }

                return arg2;
            }

            throw new ExecutionException(((c)arg2).b);
        }

        throw a.a("Task was cancelled.", ((b)arg2).d);
    }

    protected String c() {
        Object v0 = this.c;
        if((v0 instanceof f)) {
            return "setFuture=[" + this.d(((f)v0).b) + "]";
        }

        if((this instanceof ScheduledFuture)) {
            return "remaining delay=[" + this.getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }

        return null;
    }

    public final boolean cancel(boolean arg8) {
        b v3_1;
        Object v0 = this.c;
        boolean v1 = true;
        int v3 = v0 == null ? 1 : 0;
        if((v3 | v0 instanceof f) != 0) {
            if(a.a) {
                v3_1 = new b(arg8, new CancellationException("Future.cancel() was called."));
            }
            else if(arg8) {
                v3_1 = b.a;
            }
            else {
                v3_1 = b.b;
            }

            boolean v5 = false;
            Object v4 = v0;
            a v0_1 = this;
            do {
            label_25:
                if(a.b.a(v0_1, v4, v3_1)) {
                    if(arg8) {
                        v0_1.a();
                    }

                    a.a(v0_1);
                    if(!(v4 instanceof f)) {
                        return v1;
                    }

                    com.google.common.a.a.a v0_2 = ((f)v4).b;
                    if((v0_2 instanceof a)) {
                        v4 = ((a)v0_2).c;
                        int v5_1 = v4 == null ? 1 : 0;
                        if((v5_1 | v4 instanceof f) == 0) {
                            return v1;
                        }

                        v5 = true;
                        goto label_25;
                    }

                    v0_2.cancel(arg8);
                    return v1;
                }

                v4 = v0_1.c;
            }
            while((v4 instanceof f));

            v1 = v5;
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private void d() {
        i v0;
        do {
            v0 = this.e;
        }
        while(!a.b.a(this, v0, i.a));

        while(v0 != null) {
            v0.a();
            v0 = v0.c;
        }
    }

    private String d(Object arg1) {
        if((((a)arg1)) == this) {
            return "this future";
        }

        return String.valueOf(arg1);
    }

    public final Object get() {
        if(!Thread.interrupted()) {
            Object v0 = this.c;
            int v3 = v0 != null ? 1 : 0;
            int v4 = !(v0 instanceof f) ? 1 : 0;
            if((v3 & v4) != 0) {
                return this.c(v0);
            }

            i v0_1 = this.e;
            if(v0_1 != i.a) {
                i v3_1 = new i();
                do {
                    v3_1.a(v0_1);
                    if(a.b.a(this, v0_1, v3_1)) {
                        break;
                    }

                    v0_1 = this.e;
                    if(v0_1 != i.a) {
                        continue;
                    }

                    goto label_51;
                }
                while(true);

                do {
                    LockSupport.park(this);
                    if(Thread.interrupted()) {
                        goto label_44;
                    }

                    v0 = this.c;
                    v4 = v0 != null ? 1 : 0;
                    int v5 = !(v0 instanceof f) ? 1 : 0;
                }
                while((v4 & v5) == 0);

                return this.c(v0);
            label_44:
                this.a(v3_1);
                throw new InterruptedException();
            }

        label_51:
            return this.c(this.c);
        }

        throw new InterruptedException();
    }

    public final Object get(long arg18, TimeUnit arg20) {
        StringBuilder v3_1;
        int v6_2;
        int v5;
        Object v4_1;
        a v0 = this;
        long v1 = arg18;
        TimeUnit v3 = arg20;
        long v4 = v3.toNanos(v1);
        if(Thread.interrupted()) {
            goto label_185;
        }

        Object v6 = v0.c;
        int v9 = v6 != null ? 1 : 0;
        int v10 = !(v6 instanceof f) ? 1 : 0;
        if((v9 & v10) != 0) {
            return v0.c(v6);
        }

        long v9_1 = 0;
        long v11 = v4 > v9_1 ? System.nanoTime() + v4 : v9_1;
        long v13 = 1000;
        if(v4 >= v13) {
            i v6_1 = v0.e;
            if(v6_1 != i.a) {
                i v15 = new i();
                do {
                    v15.a(v6_1);
                    if(a.b.a(v0, v6_1, v15)) {
                        break;
                    }

                    v6_1 = v0.e;
                    if(v6_1 != i.a) {
                        continue;
                    }

                    goto label_67;
                }
                while(true);

                do {
                    LockSupport.parkNanos(v0, v4);
                    if(Thread.interrupted()) {
                        goto label_60;
                    }

                    v4_1 = v0.c;
                    v5 = v4_1 != null ? 1 : 0;
                    v6_2 = !(v4_1 instanceof f) ? 1 : 0;
                    if((v5 & v6_2) != 0) {
                        return v0.c(v4_1);
                    }

                    v4 = v11 - System.nanoTime();
                }
                while(v4 >= v13);

                v0.a(v15);
                goto label_70;
            label_60:
                v0.a(v15);
                throw new InterruptedException();
            }

        label_67:
            return v0.c(v0.c);
        }

        while(true) {
        label_70:
            if(v4 <= v9_1) {
                goto label_93;
            }

            v4_1 = v0.c;
            v5 = v4_1 != null ? 1 : 0;
            v6_2 = !(v4_1 instanceof f) ? 1 : 0;
            if((v5 & v6_2) != 0) {
                return v0.c(v4_1);
            }

            if(Thread.interrupted()) {
                break;
            }

            v4 = v11 - System.nanoTime();
        }

        throw new InterruptedException();
    label_93:
        String v6_3 = this.toString();
        String v7 = arg20.toString().toLowerCase(Locale.ROOT);
        String v1_1 = "Waited " + v1 + " " + arg20.toString().toLowerCase(Locale.ROOT);
        if(v4 + v13 < v9_1) {
            v1_1 = v1_1 + " (plus ";
            v4 = -v4;
            v11 = v3.convert(v4, TimeUnit.NANOSECONDS);
            v4 -= v3.toNanos(v11);
            int v16 = v11 == v9_1 || v4 > v13 ? 1 : 0;
            if(v11 > v9_1) {
                v1_1 = v1_1 + v11 + " " + v7;
                if(v16 != 0) {
                    v1_1 = v1_1 + ",";
                }

                v1_1 = v1_1 + " ";
            }

            if(v16 != 0) {
                v1_1 = v1_1 + v4 + " nanoseconds ";
            }

            v1_1 = v1_1 + "delay)";
        }

        if(this.isDone()) {
            v3_1 = new StringBuilder();
            v3_1.append(v1_1);
            v3_1.append(" but future completed as timeout expired");
            throw new TimeoutException(v3_1.toString());
        }

        v3_1 = new StringBuilder();
        v3_1.append(v1_1);
        v3_1.append(" for ");
        v3_1.append(v6_3);
        throw new TimeoutException(v3_1.toString());
    label_185:
        throw new InterruptedException();
    }

    public final boolean isCancelled() {
        return this.c instanceof b;
    }

    public final boolean isDone() {
        Object v0 = this.c;
        int v1 = 0;
        int v3 = v0 != null ? 1 : 0;
        if(!(v0 instanceof f)) {
            v1 = 1;
        }

        return v3 & v1;
    }

    public String toString() {
        String v1;
        StringBuilder v0 = new StringBuilder();
        v0.append(super.toString());
        v0.append("[status=");
        if(this.isCancelled()) {
            v1 = "CANCELLED";
            goto label_9;
        }

        if(!this.isDone()) {
            try {
                v1 = this.c();
            }
            catch(RuntimeException v1_1) {
                v1 = "Exception thrown from implementation: " + v1_1.getClass();
            }

            if(v1 != null && !v1.isEmpty()) {
                v0.append("PENDING, info=[");
                v0.append(v1);
                v1 = "]";
                goto label_9;
            }

            if(!this.isDone()) {
                goto label_36;
            }
        }

        this.a(v0);
        goto label_38;
    label_36:
        v1 = "PENDING";
    label_9:
        v0.append(v1);
    label_38:
        v0.append("]");
        return v0.toString();
    }
}

