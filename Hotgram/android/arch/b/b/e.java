package android.arch.b.b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.arch.b.a.c$c;
import android.arch.b.a.f;
import android.content.Context;
import android.database.Cursor;
import android.os.Build$VERSION;
import android.support.v4.f.n;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class e {
    public class a {
        private final Class a;
        private final String b;
        private final Context c;
        private ArrayList d;
        private c e;
        private boolean f;
        private android.arch.b.b.e$c g;
        private boolean h;
        private final d i;
        private Set j;
        private Set k;

        a(Context arg1, Class arg2, String arg3) {
            super();
            this.c = arg1;
            this.a = arg2;
            this.b = arg3;
            this.g = android.arch.b.b.e$c.a;
            this.h = true;
            this.i = new d();
        }

        public a a() {
            this.f = true;
            return this;
        }

        public a a(b arg2) {
            if(this.d == null) {
                this.d = new ArrayList();
            }

            this.d.add(arg2);
            return this;
        }

        public a a(android.arch.b.b.a.a[] arg6) {
            if(this.k == null) {
                this.k = new HashSet();
            }

            int v0 = arg6.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                android.arch.b.b.a.a v2 = arg6[v1];
                this.k.add(Integer.valueOf(v2.a));
                this.k.add(Integer.valueOf(v2.b));
            }

            this.i.a(arg6);
            return this;
        }

        public a b() {
            this.h = false;
            return this;
        }

        public e c() {
            Object v1;
            if(this.c != null) {
                if(this.a != null) {
                    if(this.k != null && this.j != null) {
                        Iterator v0 = this.k.iterator();
                        while(true) {
                            if(v0.hasNext()) {
                                v1 = v0.next();
                                if(!this.j.contains(v1)) {
                                    continue;
                                }
                                else {
                                    break;
                                }
                            }

                            goto label_26;
                        }

                        StringBuilder v2 = new StringBuilder();
                        v2.append("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ");
                        v2.append(v1);
                        throw new IllegalArgumentException(v2.toString());
                    }

                label_26:
                    if(this.e == null) {
                        this.e = new android.arch.b.a.a.c();
                    }

                    android.arch.b.b.a v0_1 = new android.arch.b.b.a(this.c, this.b, this.e, this.i, this.d, this.f, this.g.a(this.c), this.h, this.j);
                    v1 = android.arch.b.b.d.a(this.a, "_Impl");
                    ((e)v1).a(v0_1);
                    return ((e)v1);
                }

                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }

            throw new IllegalArgumentException("Cannot provide null context for the database.");
        }
    }

    public abstract class b {
        public b() {
            super();
        }

        public void a(android.arch.b.a.b arg1) {
        }

        public void b(android.arch.b.a.b arg1) {
        }
    }

    public enum android.arch.b.b.e$c {
        public static final enum android.arch.b.b.e$c a;
        public static final enum android.arch.b.b.e$c b;
        public static final enum android.arch.b.b.e$c c;

        static {
            android.arch.b.b.e$c.a = new android.arch.b.b.e$c("AUTOMATIC", 0);
            android.arch.b.b.e$c.b = new android.arch.b.b.e$c("TRUNCATE", 1);
            android.arch.b.b.e$c.c = new android.arch.b.b.e$c("WRITE_AHEAD_LOGGING", 2);
            android.arch.b.b.e$c.d = new android.arch.b.b.e$c[]{android.arch.b.b.e$c.a, android.arch.b.b.e$c.b, android.arch.b.b.e$c.c};
        }

        private android.arch.b.b.e$c(String arg1, int arg2) {
            super(arg1, arg2);
        }

        @SuppressLint(value={"NewApi"}) android.arch.b.b.e$c a(Context arg3) {
            if(this != android.arch.b.b.e$c.a) {
                return this;
            }

            if(Build$VERSION.SDK_INT >= 16) {
                Object v3 = arg3.getSystemService("activity");
                if(v3 != null && !android.support.v4.app.b.a(((ActivityManager)v3))) {
                    return android.arch.b.b.e$c.c;
                }
            }

            return android.arch.b.b.e$c.b;
        }

        public static android.arch.b.b.e$c valueOf(String arg1) {
            return Enum.valueOf(android.arch.b.b.e$c.class, arg1);
        }

        public static android.arch.b.b.e$c[] values() {
            // Method was not decompiled
        }
    }

    public class d {
        private n a;

        public d() {
            super();
            this.a = new n();
        }

        public void a(android.arch.b.b.a.a[] arg4) {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg4[v1]);
            }
        }

        private List a(List arg11, boolean arg12, int arg13, int arg14) {
            int v9;
            int v7;
            List v4;
            int v2 = arg12 ? -1 : 1;
            do {
                if(arg12) {
                    if(arg13 < arg14) {
                        goto label_10;
                    }
                }
                else if(arg13 > arg14) {
                label_10:
                    Object v3 = this.a.a(arg13);
                    v4 = null;
                    if(v3 == null) {
                        return v4;
                    }
                    else {
                        int v5 = ((n)v3).b();
                        int v6 = 0;
                        if(arg12) {
                            --v5;
                            v7 = -1;
                        }
                        else {
                            v7 = v5;
                            v5 = 0;
                        }

                        while(v5 != v7) {
                            int v8 = ((n)v3).d(v5);
                            if(arg12) {
                                if(v8 <= arg14) {
                                    if(v8 <= arg13) {
                                        goto label_30;
                                    }

                                    goto label_28;
                                }
                                else {
                                    goto label_30;
                                }
                            }
                            else if(v8 < arg14) {
                            label_30:
                                v9 = 0;
                            }
                            else if(v8 < arg13) {
                            label_28:
                                v9 = 1;
                            }
                            else {
                                goto label_30;
                            }

                            if(v9 != 0) {
                                arg11.add(((n)v3).e(v5));
                                arg13 = v8;
                                v6 = 1;
                                break;
                            }

                            v5 += v2;
                        }

                        if(v6 != 0) {
                            continue;
                        }

                        return v4;
                    }
                }

                return arg11;
            }
            while(true);

            return v4;
        }

        private void a(android.arch.b.b.a.a arg7) {
            int v0 = arg7.a;
            int v1 = arg7.b;
            Object v2 = this.a.a(v0);
            if(v2 == null) {
                n v2_1 = new n();
                this.a.b(v0, v2_1);
            }

            Object v0_1 = ((n)v2).a(v1);
            if(v0_1 != null) {
                Log.w("ROOM", "Overriding migration " + v0_1 + " with " + arg7);
            }

            ((n)v2).c(v1, arg7);
        }

        public List a(int arg3, int arg4) {
            if(arg3 == arg4) {
                return Collections.emptyList();
            }

            boolean v0 = arg4 > arg3 ? true : false;
            return this.a(new ArrayList(), v0, arg3, arg4);
        }
    }

    protected volatile android.arch.b.a.b a;
    boolean b;
    protected List c;
    private android.arch.b.a.c d;
    private final android.arch.b.b.c e;
    private boolean f;
    private final ReentrantLock g;

    public e() {
        super();
        this.g = new ReentrantLock();
        this.e = this.c();
    }

    Lock a() {
        return this.g;
    }

    public Cursor a(String arg3, Object[] arg4) {
        return this.d.a().a(new android.arch.b.a.a(arg3, arg4));
    }

    public f a(String arg2) {
        this.e();
        return this.d.a().a(arg2);
    }

    public Cursor a(android.arch.b.a.e arg2) {
        this.e();
        return this.d.a().a(arg2);
    }

    protected void a(android.arch.b.a.b arg2) {
        this.e.a(arg2);
    }

    public void a(android.arch.b.b.a arg4) {
        this.d = this.b(arg4);
        boolean v1 = false;
        if(Build$VERSION.SDK_INT >= 16) {
            if(arg4.g == android.arch.b.b.e$c.c) {
                v1 = true;
            }

            this.d.a(v1);
        }

        this.c = arg4.e;
        this.f = arg4.f;
        this.b = v1;
    }

    public android.arch.b.a.c b() {
        return this.d;
    }

    protected abstract android.arch.b.a.c b(android.arch.b.b.a arg1);

    protected abstract android.arch.b.b.c c();

    public boolean d() {
        android.arch.b.a.b v0 = this.a;
        boolean v0_1 = v0 == null || !v0.e() ? false : true;
        return v0_1;
    }

    public void e() {
        if(this.f) {
            return;
        }

        if(!android.arch.a.a.a.a().b()) {
            return;
        }

        throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
    }

    public void f() {
        this.e();
        android.arch.b.a.b v0 = this.d.a();
        this.e.b(v0);
        v0.a();
    }

    public void g() {
        this.d.a().b();
        if(!this.i()) {
            this.e.a();
        }
    }

    public void h() {
        this.d.a().c();
    }

    public boolean i() {
        return this.d.a().d();
    }
}

