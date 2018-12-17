package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import java.util.Arrays;

public final class aw implements Parcelable {
    class com.google.ads.interactivemedia.v3.internal.aw$1 implements Parcelable$Creator {
        com.google.ads.interactivemedia.v3.internal.aw$1() {
            super();
        }

        public aw a(Parcel arg2) {
            return new aw(arg2);
        }

        public aw[] a(int arg1) {
            return new aw[0];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public final int a;
    public final int b;
    public final int c;
    public final byte[] d;
    public static final Parcelable$Creator e;
    private int f;

    static {
        aw.e = new com.google.ads.interactivemedia.v3.internal.aw$1();
    }

    public aw(int arg1, int arg2, int arg3, byte[] arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    aw(Parcel arg2) {
        super();
        this.a = arg2.readInt();
        this.b = arg2.readInt();
        this.c = arg2.readInt();
        int v0 = arg2.readInt() != 0 ? 1 : 0;
        byte[] v2 = v0 != 0 ? arg2.createByteArray() : null;
        this.d = v2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg5) {
        if(this == (((aw)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if(this.a == ((aw)arg5).a && this.b == ((aw)arg5).b && this.c == ((aw)arg5).c) {
                if(!Arrays.equals(this.d, ((aw)arg5).d)) {
                }
                else {
                    return 1;
                }
            }
        }

        return 0;
    }

    public int hashCode() {
        if(this.f == 0) {
            this.f = (((527 + this.a) * 31 + this.b) * 31 + this.c) * 31 + Arrays.hashCode(this.d);
        }

        return this.f;
    }

    public String toString() {
        int v0 = this.a;
        int v1 = this.b;
        int v2 = this.c;
        boolean v3 = this.d != null ? true : false;
        StringBuilder v5 = new StringBuilder(55);
        v5.append("ColorInfo(");
        v5.append(v0);
        v5.append(", ");
        v5.append(v1);
        v5.append(", ");
        v5.append(v2);
        v5.append(", ");
        v5.append(v3);
        v5.append(")");
        return v5.toString();
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeInt(this.a);
        arg1.writeInt(this.b);
        arg1.writeInt(this.c);
        arg2 = this.d != null ? 1 : 0;
        arg1.writeInt(arg2);
        if(this.d != null) {
            arg1.writeByteArray(this.d);
        }
    }
}

