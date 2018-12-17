package androidx.work;

import androidx.work.impl.b.j;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class r {
    public abstract class a {
        boolean a;
        UUID b;
        j c;
        Set d;

        a(Class arg4) {
            super();
            this.a = false;
            this.d = new HashSet();
            this.b = UUID.randomUUID();
            this.c = new j(this.b.toString(), arg4.getName());
            this.a(arg4.getName());
        }

        public final a a(String arg2) {
            this.d.add(arg2);
            return this.c();
        }

        public final a a(e arg2) {
            this.c.e = arg2;
            return this.c();
        }

        abstract a c();

        abstract r d();

        public final r e() {
            r v0 = this.d();
            this.b = UUID.randomUUID();
            this.c = new j(this.c);
            this.c.a = this.b.toString();
            return v0;
        }
    }

    private UUID a;
    private j b;
    private Set c;

    protected r(UUID arg1, j arg2, Set arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public String a() {
        return this.a.toString();
    }

    public j b() {
        return this.b;
    }

    public Set c() {
        return this.c;
    }
}

