package androidx.work.impl.a;

public class b {
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;

    public b(boolean arg1, boolean arg2, boolean arg3, boolean arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public boolean a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((b)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.a != ((b)arg5).a || this.b != ((b)arg5).b || this.c != ((b)arg5).c || this.d != ((b)arg5).d) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v0 = this.a ? 1 : 0;
        if(this.b) {
            v0 += 16;
        }

        if(this.c) {
            v0 += 256;
        }

        if(this.d) {
            v0 += 4096;
        }

        return v0;
    }

    public String toString() {
        return String.format("[ Connected=%b Validated=%b Metered=%b NotRoaming=%b ]", Boolean.valueOf(this.a), Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d));
    }
}

