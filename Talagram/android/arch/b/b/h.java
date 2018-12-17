package android.arch.b.b;

import android.arch.b.a.d;
import android.arch.b.a.e;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.TreeMap;

public class h implements d, e {
    final long[] a;
    final double[] b;
    final String[] c;
    final byte[][] d;
    final int e;
    int f;
    static final TreeMap g;
    private volatile String h;
    private final int[] i;

    static {
        h.g = new TreeMap();
    }

    private h(int arg2) {
        super();
        this.e = arg2;
        ++arg2;
        this.i = new int[arg2];
        this.a = new long[arg2];
        this.b = new double[arg2];
        this.c = new String[arg2];
        this.d = new byte[arg2][];
    }

    public static h a(String arg4, int arg5) {
        TreeMap v0 = h.g;
        __monitor_enter(v0);
        try {
            Map$Entry v1 = h.g.ceilingEntry(Integer.valueOf(arg5));
            if(v1 != null) {
                h.g.remove(v1.getKey());
                Object v1_1 = v1.getValue();
                ((h)v1_1).b(arg4, arg5);
                __monitor_exit(v0);
                return ((h)v1_1);
            }

            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            try {
            label_19:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_19;
            }

            throw v4;
        }

        h v0_1 = new h(arg5);
        v0_1.b(arg4, arg5);
        return v0_1;
    }

    public String a() {
        return this.h;
    }

    public void a(int arg3) {
        this.i[arg3] = 1;
    }

    public void a(int arg3, double arg4) {
        this.i[arg3] = 3;
        this.b[arg3] = arg4;
    }

    public void a(int arg3, long arg4) {
        this.i[arg3] = 2;
        this.a[arg3] = arg4;
    }

    public void a(int arg3, String arg4) {
        this.i[arg3] = 4;
        this.c[arg3] = arg4;
    }

    public void a(int arg3, byte[] arg4) {
        this.i[arg3] = 5;
        this.d[arg3] = arg4;
    }

    public void a(d arg5) {
        int v0;
        for(v0 = 1; v0 <= this.f; ++v0) {
            switch(this.i[v0]) {
                case 1: {
                    arg5.a(v0);
                    break;
                }
                case 2: {
                    arg5.a(v0, this.a[v0]);
                    break;
                }
                case 3: {
                    arg5.a(v0, this.b[v0]);
                    break;
                }
                case 4: {
                    arg5.a(v0, this.c[v0]);
                    break;
                }
                case 5: {
                    arg5.a(v0, this.d[v0]);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    void b(String arg1, int arg2) {
        this.h = arg1;
        this.f = arg2;
    }

    public void b() {
        TreeMap v0 = h.g;
        __monitor_enter(v0);
        try {
            h.g.put(Integer.valueOf(this.e), this);
            h.c();
            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_10;
        }

        throw v1;
    }

    private static void c() {
        if(h.g.size() > 15) {
            int v0 = h.g.size() - 10;
            Iterator v1 = h.g.descendingKeySet().iterator();
            while(true) {
                int v2 = v0 - 1;
                if(v0 > 0) {
                    v1.next();
                    v1.remove();
                    v0 = v2;
                    continue;
                }

                return;
            }
        }
    }

    public void close() {
    }
}

