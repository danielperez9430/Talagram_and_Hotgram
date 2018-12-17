package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

class b extends a {
    private final SparseIntArray a;
    private final Parcel b;
    private final int c;
    private final int d;
    private final String e;
    private int f;
    private int g;

    b(Parcel arg4) {
        this(arg4, arg4.dataPosition(), arg4.dataSize(), "");
    }

    b(Parcel arg2, int arg3, int arg4, String arg5) {
        super();
        this.a = new SparseIntArray();
        this.f = -1;
        this.g = 0;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.g = this.c;
        this.e = arg5;
    }

    public void a(int arg2) {
        this.b.writeInt(arg2);
    }

    public void a(Parcelable arg3) {
        this.b.writeParcelable(arg3, 0);
    }

    public void a(String arg2) {
        this.b.writeString(arg2);
    }

    public void a(byte[] arg3) {
        if(arg3 != null) {
            this.b.writeInt(arg3.length);
            this.b.writeByteArray(arg3);
        }
        else {
            this.b.writeInt(-1);
        }
    }

    public void b() {
        if(this.f >= 0) {
            int v0 = this.a.get(this.f);
            int v1 = this.b.dataPosition();
            this.b.setDataPosition(v0);
            this.b.writeInt(v1 - v0);
            this.b.setDataPosition(v1);
        }
    }

    public boolean b(int arg2) {
        arg2 = this.d(arg2);
        if(arg2 == -1) {
            return 0;
        }

        this.b.setDataPosition(arg2);
        return 1;
    }

    protected a c() {
        Parcel v1 = this.b;
        int v2 = this.b.dataPosition();
        int v3 = this.g == this.c ? this.d : this.g;
        StringBuilder v4 = new StringBuilder();
        v4.append(this.e);
        v4.append("  ");
        return new b(v1, v2, v3, v4.toString());
    }

    public void c(int arg3) {
        this.b();
        this.f = arg3;
        this.a.put(arg3, this.b.dataPosition());
        this.a(0);
        this.a(arg3);
    }

    private int d(int arg4) {
        do {
            if(this.g >= this.d) {
                return -1;
            }

            this.b.setDataPosition(this.g);
            int v0 = this.b.readInt();
            int v1 = this.b.readInt();
            this.g += v0;
        }
        while(v1 != arg4);

        return this.b.dataPosition();
    }

    public int d() {
        return this.b.readInt();
    }

    public String e() {
        return this.b.readString();
    }

    public byte[] f() {
        int v0 = this.b.readInt();
        if(v0 < 0) {
            return null;
        }

        byte[] v0_1 = new byte[v0];
        this.b.readByteArray(v0_1);
        return v0_1;
    }

    public Parcelable g() {
        return this.b.readParcelable(this.getClass().getClassLoader());
    }
}

