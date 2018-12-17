package android.support.v4.f;

public class j {
    public final Object a;
    public final Object b;

    public j(Object arg1, Object arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public static j a(Object arg1, Object arg2) {
        return new j(arg1, arg2);
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(!(arg4 instanceof j)) {
            return 0;
        }

        if((i.a(((j)arg4).a, this.a)) && (i.a(((j)arg4).b, this.b))) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.a == null ? 0 : this.a.hashCode();
        if(this.b == null) {
        }
        else {
            v1 = this.b.hashCode();
        }

        return v0 ^ v1;
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.a) + " " + String.valueOf(this.b) + "}";
    }
}

