package androidx.work.impl.b;

import android.arch.a.c.a;
import androidx.work.c;
import androidx.work.e;
import androidx.work.o;

public class j {
    final class androidx.work.impl.b.j$1 implements a {
        androidx.work.impl.b.j$1() {
            super();
        }
    }

    public class androidx.work.impl.b.j$a {
        public String a;
        public o b;

        public androidx.work.impl.b.j$a() {
            super();
        }

        public boolean equals(Object arg4) {
            if(this == (((androidx.work.impl.b.j$a)arg4))) {
                return 1;
            }

            if(arg4 != null) {
                if(this.getClass() != arg4.getClass()) {
                }
                else if(this.b != ((androidx.work.impl.b.j$a)arg4).b) {
                    return 0;
                }
                else {
                    return this.a.equals(((androidx.work.impl.b.j$a)arg4).a);
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.a.hashCode() * 31 + this.b.hashCode();
        }
    }

    public String a;
    public o b;
    public String c;
    public String d;
    public e e;
    public e f;
    public long g;
    public long h;
    public long i;
    public c j;
    public int k;
    public androidx.work.a l;
    public long m;
    public long n;
    public long o;
    public long p;
    public static final a q;

    static {
        j.q = new androidx.work.impl.b.j$1();
    }

    public j(j arg3) {
        super();
        this.b = o.a;
        this.e = e.a;
        this.f = e.a;
        this.j = c.a;
        this.l = androidx.work.a.a;
        this.m = 30000;
        this.p = -1;
        this.a = arg3.a;
        this.c = arg3.c;
        this.b = arg3.b;
        this.d = arg3.d;
        this.e = new e(arg3.e);
        this.f = new e(arg3.f);
        this.g = arg3.g;
        this.h = arg3.h;
        this.i = arg3.i;
        this.j = new c(arg3.j);
        this.k = arg3.k;
        this.l = arg3.l;
        this.m = arg3.m;
        this.n = arg3.n;
        this.o = arg3.o;
        this.p = arg3.p;
    }

    public j(String arg3, String arg4) {
        super();
        this.b = o.a;
        this.e = e.a;
        this.f = e.a;
        this.j = c.a;
        this.l = androidx.work.a.a;
        this.m = 30000;
        this.p = -1;
        this.a = arg3;
        this.c = arg4;
    }

    public void a(long arg6) {
        long v0 = 900000;
        if(arg6 < v0) {
            androidx.work.j.d("WorkSpec", String.format("Interval duration lesser than minimum allowed value; Changed to %s", Long.valueOf(v0)), new Throwable[0]);
            arg6 = v0;
        }

        this.a(arg6, arg6);
    }

    public void a(long arg7, long arg9) {
        long v0 = 900000;
        if(Long.compare(arg7, v0) < 0) {
            androidx.work.j.d("WorkSpec", String.format("Interval duration lesser than minimum allowed value; Changed to %s", Long.valueOf(v0)), new Throwable[0]);
            arg7 = v0;
        }

        v0 = 300000;
        if(arg9 < v0) {
            androidx.work.j.d("WorkSpec", String.format("Flex duration lesser than minimum allowed value; Changed to %s", Long.valueOf(v0)), new Throwable[0]);
            arg9 = v0;
        }

        if(arg9 > arg7) {
            androidx.work.j.d("WorkSpec", String.format("Flex duration greater than interval duration; Changed to %s", Long.valueOf(arg7)), new Throwable[0]);
            arg9 = arg7;
        }

        this.h = arg7;
        this.i = arg9;
    }

    public boolean a() {
        boolean v0 = this.h != 0 ? true : false;
        return v0;
    }

    public boolean b() {
        boolean v0 = this.b != o.a || this.k <= 0 ? false : true;
        return v0;
    }

    public long c() {
        if(this.b()) {
            int v0 = this.l == androidx.work.a.b ? 1 : 0;
            long v0_1 = v0 != 0 ? this.m * (((long)this.k)) : ((long)Math.scalb(((float)this.m), this.k - 1));
            return this.n + Math.min(18000000, v0_1);
        }

        if(this.a()) {
            return this.n + this.h - this.i;
        }

        return this.n + this.g;
    }

    public boolean d() {
        return c.a.equals(this.j) ^ 1;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((j)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else if(this.g != ((j)arg8).g) {
                return 0;
            }
            else if(this.h != ((j)arg8).h) {
                return 0;
            }
            else if(this.i != ((j)arg8).i) {
                return 0;
            }
            else if(this.k != ((j)arg8).k) {
                return 0;
            }
            else if(this.m != ((j)arg8).m) {
                return 0;
            }
            else if(this.n != ((j)arg8).n) {
                return 0;
            }
            else if(this.o != ((j)arg8).o) {
                return 0;
            }
            else if(this.p != ((j)arg8).p) {
                return 0;
            }
            else if(!this.a.equals(((j)arg8).a)) {
                return 0;
            }
            else if(this.b != ((j)arg8).b) {
                return 0;
            }
            else if(!this.c.equals(((j)arg8).c)) {
                return 0;
            }
            else {
                if(this.d != null) {
                    if(!this.d.equals(((j)arg8).d)) {
                        return 0;
                    }
                }
                else if(((j)arg8).d != null) {
                    return 0;
                }

                if(!this.e.equals(((j)arg8).e)) {
                    return 0;
                }

                if(!this.f.equals(((j)arg8).f)) {
                    return 0;
                }

                if(!this.j.equals(((j)arg8).j)) {
                    return 0;
                }

                if(this.l == ((j)arg8).l) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v0 = ((this.a.hashCode() * 31 + this.b.hashCode()) * 31 + this.c.hashCode()) * 31;
        int v1 = this.d != null ? this.d.hashCode() : 0;
        return ((((((((((((v0 + v1) * 31 + this.e.hashCode()) * 31 + this.f.hashCode()) * 31 + (((int)(this.g ^ this.g >>> 32)))) * 31 + (((int)(this.h ^ this.h >>> 32)))) * 31 + (((int)(this.i ^ this.i >>> 32)))) * 31 + this.j.hashCode()) * 31 + this.k) * 31 + this.l.hashCode()) * 31 + (((int)(this.m ^ this.m >>> 32)))) * 31 + (((int)(this.n ^ this.n >>> 32)))) * 31 + (((int)(this.o ^ this.o >>> 32)))) * 31 + (((int)(this.p ^ this.p >>> 32)));
    }

    public String toString() {
        return "{WorkSpec: " + this.a + "}";
    }
}

