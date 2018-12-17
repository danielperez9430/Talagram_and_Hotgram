package android.support.v4.media;

import java.util.Arrays;

class c implements a {
    int a;
    int b;
    int c;
    int d;

    c() {
        super();
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = -1;
    }

    public int a() {
        if(this.d != -1) {
            return this.d;
        }

        return AudioAttributesCompat.a(false, this.c, this.a);
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.a;
    }

    public int d() {
        int v0 = this.c;
        int v1 = this.a();
        if(v1 == 6) {
            v0 |= 4;
        }
        else if(v1 == 7) {
            v0 |= 1;
        }

        return v0 & 273;
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(!(arg4 instanceof c)) {
            return 0;
        }

        if(this.b == ((c)arg4).b() && this.c == ((c)arg4).d() && this.a == ((c)arg4).c() && this.d == ((c)arg4).d) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.a), Integer.valueOf(this.d)});
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("AudioAttributesCompat:");
        if(this.d != -1) {
            v0.append(" stream=");
            v0.append(this.d);
            v0.append(" derived");
        }

        v0.append(" usage=");
        v0.append(AudioAttributesCompat.a(this.a));
        v0.append(" content=");
        v0.append(this.b);
        v0.append(" flags=0x");
        v0.append(Integer.toHexString(this.c).toUpperCase());
        return v0.toString();
    }
}

