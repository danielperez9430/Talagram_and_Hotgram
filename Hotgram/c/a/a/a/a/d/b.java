package c.a.a.a.a.d;

import android.content.Context;
import c.a.a.a.a.b.i;
import c.a.a.a.a.b.k;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class b {
    class a {
        final File a;
        final long b;

        public a(File arg1, long arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    protected final Context a;
    protected final c.a.a.a.a.d.a b;
    protected final k c;
    protected final c d;
    protected volatile long e;
    protected final List f;
    private final int g;

    public b(Context arg2, c.a.a.a.a.d.a arg3, k arg4, c arg5, int arg6) {
        super();
        this.f = new CopyOnWriteArrayList();
        this.a = arg2.getApplicationContext();
        this.b = arg3;
        this.d = arg5;
        this.c = arg4;
        this.e = this.c.a();
        this.g = arg6;
    }

    private void a(int arg6) {
        if(!this.d.a(arg6, this.c())) {
            i.a(this.a, 4, "Fabric", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", Integer.valueOf(this.d.a()), Integer.valueOf(arg6), Integer.valueOf(this.c())));
            this.d();
        }
    }

    public long a(String arg6) {
        String[] v6 = arg6.split("_");
        long v1 = 0;
        if(v6.length != 3) {
            return v1;
        }

        int v0 = 2;
        try {
            return Long.valueOf(v6[v0]).longValue();
        }
        catch(NumberFormatException ) {
            return v1;
        }
    }

    protected abstract String a();

    public void a(d arg2) {
        if(arg2 != null) {
            this.f.add(arg2);
        }
    }

    public void a(Object arg2) {
        byte[] v2 = this.b.a(arg2);
        this.a(v2.length);
        this.d.a(v2);
    }

    public void a(List arg2) {
        this.d.a(arg2);
    }

    private void b(String arg5) {
        Iterator v0 = this.f.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            try {
                ((d)v1).a(arg5);
            }
            catch(Exception v1_1) {
                i.a(this.a, "One of the roll over listeners threw an exception", ((Throwable)v1_1));
            }
        }
    }

    protected int b() {
        return this.g;
    }

    protected int c() {
        return 8000;
    }

    public boolean d() {
        String v0;
        boolean v1 = true;
        if(!this.d.b()) {
            v0 = this.a();
            this.d.a(v0);
            i.a(this.a, 4, "Fabric", String.format(Locale.US, "generated new file %s", v0));
            this.e = this.c.a();
        }
        else {
            v0 = null;
            v1 = false;
        }

        this.b(v0);
        return v1;
    }

    public List e() {
        return this.d.a(1);
    }

    public void f() {
        this.d.a(this.d.c());
        this.d.d();
    }

    public void g() {
        List v0 = this.d.c();
        int v1 = this.b();
        if(v0.size() <= v1) {
            return;
        }

        int v2 = v0.size() - v1;
        i.a(this.a, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", Integer.valueOf(v0.size()), Integer.valueOf(v1), Integer.valueOf(v2)));
        TreeSet v1_1 = new TreeSet(new Comparator() {
            public int a(a arg3, a arg4) {
                return ((int)(arg3.b - arg4.b));
            }

            public int compare(Object arg1, Object arg2) {
                return this.a(((a)arg1), ((a)arg2));
            }
        });
        Iterator v0_1 = v0.iterator();
        while(v0_1.hasNext()) {
            Object v3 = v0_1.next();
            v1_1.add(new a(((File)v3), this.a(((File)v3).getName())));
        }

        ArrayList v0_2 = new ArrayList();
        Iterator v1_2 = v1_1.iterator();
        do {
            if(!v1_2.hasNext()) {
                break;
            }

            v0_2.add(v1_2.next().a);
        }
        while(v0_2.size() != v2);

        this.d.a(((List)v0_2));
    }
}

