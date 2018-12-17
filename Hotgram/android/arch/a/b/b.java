package android.arch.a.b;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.WeakHashMap;

public class b implements Iterable {
    class android.arch.a.b.b$1 {
    }

    class a extends e {
        a(c arg1, c arg2) {
            super(arg1, arg2);
        }

        c a(c arg1) {
            return arg1.c;
        }

        c b(c arg1) {
            return arg1.d;
        }
    }

    class android.arch.a.b.b$b extends e {
        android.arch.a.b.b$b(c arg1, c arg2) {
            super(arg1, arg2);
        }

        c a(c arg1) {
            return arg1.d;
        }

        c b(c arg1) {
            return arg1.c;
        }
    }

    class c implements Map$Entry {
        final Object a;
        final Object b;
        c c;
        c d;

        c(Object arg1, Object arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if((((c)arg5)) == this) {
                return 1;
            }

            if(!(arg5 instanceof c)) {
                return 0;
            }

            if(!this.a.equals(((c)arg5).a) || !this.b.equals(((c)arg5).b)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        public Object getKey() {
            return this.a;
        }

        public Object getValue() {
            return this.b;
        }

        public Object setValue(Object arg2) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.a + "=" + this.b;
        }
    }

    class d implements f, Iterator {
        private c b;
        private boolean c;

        d(b arg1, android.arch.a.b.b$1 arg2) {
            this(arg1);
        }

        private d(b arg1) {
            this.a = arg1;
            super();
            this.c = true;
        }

        public Map$Entry a() {
            c v0;
            if(this.c) {
                this.c = false;
                v0 = b.a(this.a);
            }
            else if(this.b != null) {
                v0 = this.b.c;
            }
            else {
                v0 = null;
            }

            this.b = v0;
            return this.b;
        }

        public void a_(c arg2) {
            if(arg2 == this.b) {
                this.b = this.b.d;
                boolean v2 = this.b == null ? true : false;
                this.c = v2;
            }
        }

        public boolean hasNext() {
            boolean v1 = false;
            if(this.c) {
                if(b.a(this.a) != null) {
                    v1 = true;
                }

                return v1;
            }

            if(this.b != null && this.b.c != null) {
                v1 = true;
            }

            return v1;
        }

        public Object next() {
            return this.a();
        }
    }

    abstract class e implements f, Iterator {
        c a;
        c b;

        e(c arg1, c arg2) {
            super();
            this.a = arg2;
            this.b = arg1;
        }

        abstract c a(c arg1);

        public Map$Entry a() {
            c v0 = this.b;
            this.b = this.b();
            return ((Map$Entry)v0);
        }

        public void a_(c arg2) {
            if(this.a == arg2 && arg2 == this.b) {
                this.b = null;
                this.a = null;
            }

            if(this.a == arg2) {
                this.a = this.b(this.a);
            }

            if(this.b == arg2) {
                this.b = this.b();
            }
        }

        private c b() {
            if(this.b != this.a) {
                if(this.a == null) {
                }
                else {
                    return this.a(this.b);
                }
            }

            return null;
        }

        abstract c b(c arg1);

        public boolean hasNext() {
            boolean v0 = this.b != null ? true : false;
            return v0;
        }

        public Object next() {
            return this.a();
        }
    }

    interface f {
        void a_(c arg1);
    }

    private c a;
    private c b;
    private WeakHashMap c;
    private int d;

    public b() {
        super();
        this.c = new WeakHashMap();
        this.d = 0;
    }

    static c a(b arg0) {
        return arg0.a;
    }

    public int a() {
        return this.d;
    }

    protected c a(Object arg3) {
        c v0 = this.a;
        while(v0 != null) {
            if(v0.a.equals(arg3)) {
            }
            else {
                v0 = v0.c;
                continue;
            }

            return v0;
        }

        return v0;
    }

    public Object a(Object arg2, Object arg3) {
        c v0 = this.a(arg2);
        if(v0 != null) {
            return v0.b;
        }

        this.b(arg2, arg3);
        return null;
    }

    protected c b(Object arg2, Object arg3) {
        c v0 = new c(arg2, arg3);
        ++this.d;
        if(this.b == null) {
            this.a = v0;
            this.b = this.a;
            return v0;
        }

        this.b.c = v0;
        v0.d = this.b;
        this.b = v0;
        return v0;
    }

    public Object b(Object arg4) {
        c v4 = this.a(arg4);
        Object v0 = null;
        if(v4 == null) {
            return v0;
        }

        --this.d;
        if(!this.c.isEmpty()) {
            Iterator v1 = this.c.keySet().iterator();
            while(v1.hasNext()) {
                v1.next().a_(v4);
            }
        }

        if(v4.d != null) {
            v4.d.c = v4.c;
        }
        else {
            this.a = v4.c;
        }

        if(v4.c != null) {
            v4.c.d = v4.d;
        }
        else {
            this.b = v4.d;
        }

        v4.c = ((c)v0);
        v4.d = ((c)v0);
        return v4.b;
    }

    public Iterator b() {
        android.arch.a.b.b$b v0 = new android.arch.a.b.b$b(this.b, this.a);
        this.c.put(v0, Boolean.valueOf(false));
        return ((Iterator)v0);
    }

    public d c() {
        d v0 = new d(this, null);
        this.c.put(v0, Boolean.valueOf(false));
        return v0;
    }

    public Map$Entry d() {
        return this.a;
    }

    public Map$Entry e() {
        return this.b;
    }

    public boolean equals(Object arg6) {
        boolean v0 = true;
        if((((b)arg6)) == this) {
            return 1;
        }

        if(!(arg6 instanceof b)) {
            return 0;
        }

        if(this.a() != ((b)arg6).a()) {
            return 0;
        }

        Iterator v1 = this.iterator();
        Iterator v6 = ((b)arg6).iterator();
        do {
            if((v1.hasNext()) && (v6.hasNext())) {
                Object v3 = v1.next();
                Object v4 = v6.next();
                if(v3 != null || v4 == null) {
                    if(v3 == null) {
                        continue;
                    }

                    if(((Map$Entry)v3).equals(v4)) {
                        continue;
                    }
                }

                return 0;
            }

            goto label_25;
        }
        while(true);

        return 0;
    label_25:
        if((v1.hasNext()) || (v6.hasNext())) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public Iterator iterator() {
        a v0 = new a(this.a, this.b);
        this.c.put(v0, Boolean.valueOf(false));
        return ((Iterator)v0);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("[");
        Iterator v1 = this.iterator();
        while(v1.hasNext()) {
            v0.append(v1.next().toString());
            if(!v1.hasNext()) {
                continue;
            }

            v0.append(", ");
        }

        v0.append("]");
        return v0.toString();
    }
}

