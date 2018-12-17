package androidx.work.impl.b;

public class d {
    public final String a;
    public final int b;

    public d(String arg1, int arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public boolean equals(Object arg4) {
        if(this == (((d)arg4))) {
            return 1;
        }

        if(arg4 != null) {
            if(this.getClass() != arg4.getClass()) {
            }
            else if(this.b != ((d)arg4).b) {
                return 0;
            }
            else {
                return this.a.equals(((d)arg4).a);
            }
        }

        return 0;
    }

    public int hashCode() {
        return this.a.hashCode() * 31 + this.b;
    }
}

