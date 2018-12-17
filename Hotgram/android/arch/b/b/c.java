package android.arch.b.b;

import android.arch.b.a.b;
import android.arch.b.a.f;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

public class c {
    class android.arch.b.b.c$1 implements Runnable {
        android.arch.b.b.c$1(c arg1) {
            this.a = arg1;
            super();
        }

        private boolean a() {
            Cursor v0 = c.a(this.a).a("SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;", c.d(this.a));
            boolean v3 = false;
            try {
                while(v0.moveToNext()) {
                    long v3_1 = v0.getLong(0);
                    this.a.b[v0.getInt(1)] = v3_1;
                    c.a(this.a, v3_1);
                    v3 = true;
                }
            }
            catch(Throwable v1) {
                v0.close();
                throw v1;
            }

            v0.close();
            return v3;
        }

        public void run() {
            boolean v3_1;
            b v2_1;
            Lock v0 = c.a(this.a).a();
            boolean v1 = false;
            try {
                v0.lock();
                if(c.b(this.a)) {
                    goto label_10;
                }
            }
            catch(Throwable v1_1) {
                goto label_90;
            }
            catch(SQLiteException v2) {
                goto label_64;
            }

            v0.unlock();
            return;
            try {
            label_10:
                if(this.a.c.compareAndSet(true, false)) {
                    goto label_17;
                }
            }
            catch(Throwable v1_1) {
                goto label_90;
            }
            catch(SQLiteException v2) {
                goto label_64;
            }

            v0.unlock();
            return;
            try {
            label_17:
                if(!c.a(this.a).i()) {
                    goto label_23;
                }
            }
            catch(Throwable v1_1) {
                goto label_90;
            }
            catch(SQLiteException v2) {
                goto label_64;
            }

            v0.unlock();
            return;
            try {
            label_23:
                c.c(this.a).a();
                c.d(this.a)[0] = Long.valueOf(c.e(this.a));
                if(!c.a(this.a).b) {
                    goto label_59;
                }

                v2_1 = c.a(this.a).b().a();
            }
            catch(Throwable v1_1) {
                goto label_90;
            }
            catch(SQLiteException v2) {
                goto label_64;
            }

            try {
                v2_1.a();
                v3_1 = this.a();
            }
            catch(Throwable v3) {
                goto label_54;
            }

            try {
                v2_1.c();
                goto label_43;
            }
            catch(Throwable v1_1) {
                boolean v5 = v3_1;
                v3 = v1_1;
                v1 = v5;
            }

            try {
            label_54:
                v2_1.b();
                throw v3;
            }
            catch(Throwable v1_1) {
            }
            catch(SQLiteException v2) {
                v3_1 = v1;
                goto label_65;
                try {
                label_43:
                    v2_1.b();
                }
                catch(Throwable v1_1) {
                }
                catch(SQLiteException v1_2) {
                    v2 = v1_2;
                    goto label_65;
                    try {
                    label_59:
                        v3_1 = this.a();
                    }
                    catch(Throwable v1_1) {
                    }
                    catch(SQLiteException v2) {
                    label_64:
                        v3_1 = false;
                        try {
                        label_65:
                            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", ((Throwable)v2));
                        }
                        catch(Throwable v1_1) {
                        label_90:
                            v0.unlock();
                            throw v1_1;
                        }
                    }
                }
            }

            v0.unlock();
            if(v3_1) {
                android.arch.a.b.b v0_1 = this.a.d;
                __monitor_enter(v0_1);
                try {
                    Iterator v1_3 = this.a.d.iterator();
                    while(v1_3.hasNext()) {
                        v1_3.next().getValue().a(this.a.b);
                    }

                    __monitor_exit(v0_1);
                    return;
                label_87:
                    __monitor_exit(v0_1);
                }
                catch(Throwable v1_1) {
                    goto label_87;
                }

                throw v1_1;
            }
        }
    }

    class a {
        final long[] a;
        final boolean[] b;
        final int[] c;
        boolean d;
        boolean e;

        a(int arg3) {
            super();
            this.a = new long[arg3];
            this.b = new boolean[arg3];
            this.c = new int[arg3];
            Arrays.fill(this.a, 0);
            Arrays.fill(this.b, false);
        }

        int[] a() {
            __monitor_enter(this);
            try {
                if(this.d) {
                    if(this.e) {
                    }
                    else {
                        int v0_1 = this.a.length;
                        int v2;
                        for(v2 = 0; true; ++v2) {
                            int v3 = 1;
                            if(v2 >= v0_1) {
                                break;
                            }

                            boolean v4 = this.a[v2] > 0 ? true : false;
                            if(v4 != this.b[v2]) {
                                int[] v5 = this.c;
                                if(v4) {
                                }
                                else {
                                    v3 = 2;
                                }

                                v5[v2] = v3;
                            }
                            else {
                                this.c[v2] = 0;
                            }

                            this.b[v2] = v4;
                        }

                        this.e = true;
                        this.d = false;
                        __monitor_exit(this);
                        return this.c;
                    }
                }

                __monitor_exit(this);
                return null;
            label_43:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_43;
            }

            throw v0;
        }

        void b() {
            __monitor_enter(this);
            try {
                this.e = false;
                __monitor_exit(this);
                return;
            label_6:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_6;
            }

            throw v0;
        }
    }

    public abstract class android.arch.b.b.c$b {
        public abstract void a(Set arg1);
    }

    class android.arch.b.b.c$c {
        final int[] a;
        final android.arch.b.b.c$b b;
        private final String[] c;
        private final long[] d;
        private final Set e;

        void a(long[] arg9) {
            android.support.v4.f.b v1_1;
            int v0 = this.a.length;
            Set v1 = null;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                long v3 = arg9[this.a[v2]];
                if(this.d[v2] < v3) {
                    this.d[v2] = v3;
                    if(v0 == 1) {
                        v1 = this.e;
                    }
                    else {
                        if(v1 == null) {
                            v1_1 = new android.support.v4.f.b(v0);
                        }

                        ((Set)v1_1).add(this.c[v2]);
                    }
                }
            }

            if((((Set)v1_1)) != null) {
                this.b.a(((Set)v1_1));
            }
        }
    }

    android.support.v4.f.a a;
    long[] b;
    AtomicBoolean c;
    final android.arch.a.b.b d;
    Runnable e;
    private static final String[] f;
    private String[] g;
    private Object[] h;
    private long i;
    private final e j;
    private volatile boolean k;
    private volatile f l;
    private a m;

    static {
        c.f = new String[]{"UPDATE", "DELETE", "INSERT"};
    }

    public c(e arg7, String[] arg8) {
        super();
        this.h = new Object[1];
        long v0 = 0;
        this.i = v0;
        int v3 = 0;
        this.c = new AtomicBoolean(false);
        this.k = false;
        this.d = new android.arch.a.b.b();
        this.e = new android.arch.b.b.c$1(this);
        this.j = arg7;
        this.m = new a(arg8.length);
        this.a = new android.support.v4.f.a();
        int v7 = arg8.length;
        this.g = new String[v7];
        while(v3 < v7) {
            String v2 = arg8[v3].toLowerCase(Locale.US);
            this.a.put(v2, Integer.valueOf(v3));
            this.g[v3] = v2;
            ++v3;
        }

        this.b = new long[arg8.length];
        Arrays.fill(this.b, v0);
    }

    static long a(c arg0, long arg1) {
        arg0.i = arg1;
        return arg1;
    }

    static e a(c arg0) {
        return arg0.j;
    }

    private void a(b arg8, int arg9) {
        String v9 = this.g[arg9];
        StringBuilder v0 = new StringBuilder();
        String[] v1 = c.f;
        int v2 = v1.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            String v5 = v1[v4];
            v0.setLength(0);
            v0.append("DROP TRIGGER IF EXISTS ");
            c.a(v0, v9, v5);
            arg8.c(v0.toString());
        }
    }

    private static void a(StringBuilder arg1, String arg2, String arg3) {
        arg1.append("`");
        arg1.append("room_table_modification_trigger_");
        arg1.append(arg2);
        arg1.append("_");
        arg1.append(arg3);
        arg1.append("`");
    }

    public void a() {
        if(this.c.compareAndSet(false, true)) {
            android.arch.a.a.a.a().a(this.e);
        }
    }

    void a(b arg2) {
        __monitor_enter(this);
        try {
            if(this.k) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                __monitor_exit(this);
                return;
            }

            arg2.a();
        }
        catch(Throwable v2) {
            goto label_29;
        }

        try {
            arg2.c("PRAGMA temp_store = MEMORY;");
            arg2.c("PRAGMA recursive_triggers=\'ON\';");
            arg2.c("CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)");
            arg2.c();
            goto label_16;
        }
        catch(Throwable v0) {
            try {
                arg2.b();
                throw v0;
            label_16:
                arg2.b();
                this.b(arg2);
                this.l = arg2.a("DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)");
                this.k = true;
                __monitor_exit(this);
                return;
            label_29:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_29;
            }
        }

        throw v2;
    }

    private void b(b arg9, int arg10) {
        String v0 = this.g[arg10];
        StringBuilder v1 = new StringBuilder();
        String[] v2 = c.f;
        int v3 = v2.length;
        int v5;
        for(v5 = 0; v5 < v3; ++v5) {
            String v6 = v2[v5];
            v1.setLength(0);
            v1.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            c.a(v1, v0, v6);
            v1.append(" AFTER ");
            v1.append(v6);
            v1.append(" ON `");
            v1.append(v0);
            v1.append("` BEGIN INSERT OR REPLACE INTO ");
            v1.append("room_table_modification_log");
            v1.append(" VALUES(null, ");
            v1.append(arg10);
            v1.append("); END");
            arg9.c(v1.toString());
        }
    }

    private boolean b() {
        if(!this.j.d()) {
            return 0;
        }

        if(!this.k) {
            this.j.b().a();
        }

        if(!this.k) {
            Log.e("ROOM", "database is not initialized even though it is open");
            return 0;
        }

        return 1;
    }

    static boolean b(c arg0) {
        return arg0.b();
    }

    void b(b arg6) {
        int[] v1;
        Lock v0;
        if(arg6.d()) {
            return;
        }

        try {
            while(true) {
            label_3:
                v0 = this.j.a();
                v0.lock();
                break;
            }
        }
        catch(SQLiteException v6) {
            goto label_38;
        }

        try {
            v1 = this.m.a();
            if(v1 != null) {
                goto label_11;
            }
        }
        catch(Throwable v6_1) {
            goto label_33;
        }

        try {
            v0.unlock();
            return;
        }
        catch(SQLiteException v6) {
            goto label_38;
        }

        try {
        label_11:
            int v2 = v1.length;
        }
        catch(Throwable v6_1) {
            goto label_33;
        }

        try {
            arg6.a();
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                switch(v1[v3]) {
                    case 1: {
                        this.b(arg6, v3);
                        break;
                    }
                    case 2: {
                        this.a(arg6, v3);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

            arg6.c();
        }
        catch(Throwable v1_1) {
            goto label_30;
        }

        try {
            arg6.b();
            this.m.b();
        }
        catch(Throwable v6_1) {
            goto label_33;
        }

        try {
            v0.unlock();
            goto label_3;
        }
        catch(SQLiteException v6) {
            goto label_38;
        }

        try {
        label_30:
            arg6.b();
            throw v1_1;
        }
        catch(Throwable v6_1) {
            try {
            label_33:
                v0.unlock();
                throw v6_1;
            }
            catch(SQLiteException v6) {
            label_38:
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", ((Throwable)v6));
                return;
            }
        }
    }

    static f c(c arg0) {
        return arg0.l;
    }

    static Object[] d(c arg0) {
        return arg0.h;
    }

    static long e(c arg2) {
        return arg2.i;
    }
}

