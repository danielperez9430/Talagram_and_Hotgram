package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class bj implements Parcelable {
    class com.google.ads.interactivemedia.v3.internal.bj$1 implements Parcelable$Creator {
        com.google.ads.interactivemedia.v3.internal.bj$1() {
            super();
        }

        public bj a(Parcel arg2) {
            return new bj(arg2);
        }

        public bj[] a(int arg1) {
            return new bj[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public final String a;
    public final String b;
    public final int c;
    public final int d;
    public final long e;
    public final List f;
    public final boolean g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final float m;
    public final int n;
    public final byte[] o;
    public final aw p;
    public final int q;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public final String v;
    public final long w;
    public static final Parcelable$Creator x;
    private int y;
    private MediaFormat z;

    static {
        bj.x = new com.google.ads.interactivemedia.v3.internal.bj$1();
    }

    bj(Parcel arg5) {
        super();
        this.a = arg5.readString();
        this.b = arg5.readString();
        this.c = arg5.readInt();
        this.d = arg5.readInt();
        this.e = arg5.readLong();
        this.h = arg5.readInt();
        this.i = arg5.readInt();
        this.l = arg5.readInt();
        this.m = arg5.readFloat();
        this.q = arg5.readInt();
        this.r = arg5.readInt();
        this.v = arg5.readString();
        this.w = arg5.readLong();
        this.f = new ArrayList();
        ClassLoader v1 = null;
        arg5.readList(this.f, v1);
        int v2 = 0;
        boolean v0 = arg5.readInt() == 1 ? true : false;
        this.g = v0;
        this.j = arg5.readInt();
        this.k = arg5.readInt();
        this.s = arg5.readInt();
        this.t = arg5.readInt();
        this.u = arg5.readInt();
        if(arg5.readInt() != 0) {
            v2 = 1;
        }

        if(v2 != 0) {
            byte[] v1_1 = arg5.createByteArray();
        }

        this.o = ((byte[])v1);
        this.n = arg5.readInt();
        this.p = arg5.readParcelable(aw.class.getClassLoader());
    }

    bj(String arg4, String arg5, int arg6, int arg7, long arg8, int arg10, int arg11, int arg12, float arg13, int arg14, int arg15, String arg16, long arg17, List arg19, boolean arg20, int arg21, int arg22, int arg23, int arg24, int arg25, byte[] arg26, int arg27, aw arg28) {
        bj v0 = this;
        super();
        v0.a = arg4;
        v0.b = fe.a(arg5);
        v0.c = arg6;
        v0.d = arg7;
        v0.e = arg8;
        v0.h = arg10;
        v0.i = arg11;
        v0.l = arg12;
        v0.m = arg13;
        v0.q = arg14;
        v0.r = arg15;
        v0.v = arg16;
        v0.w = arg17;
        List v1 = arg19 == null ? Collections.emptyList() : arg19;
        v0.f = v1;
        v0.g = arg20;
        v0.j = arg21;
        v0.k = arg22;
        v0.s = arg23;
        v0.t = arg24;
        v0.u = arg25;
        v0.o = arg26;
        v0.n = arg27;
        v0.p = arg28;
    }

    public static bj a() {
        return bj.a(null, "application/id3", -1, -1);
    }

    public static bj a(String arg27, String arg28, int arg29, long arg30) {
        return new bj(arg27, arg28, arg29, -1, arg30, -1, -1, -1, -1f, -1, -1, null, 9223372036854775807L, null, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    public static bj a(String arg27, String arg28, int arg29, int arg30, long arg31, int arg33, int arg34, List arg35, int arg36, float arg37) {
        return new bj(arg27, arg28, arg29, arg30, arg31, arg33, arg34, arg36, arg37, -1, -1, null, 9223372036854775807L, arg35, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    public static bj a(String arg27, String arg28, int arg29, int arg30, long arg31, int arg33, int arg34, List arg35, int arg36, float arg37, byte[] arg38, int arg39, aw arg40) {
        return new bj(arg27, arg28, arg29, arg30, arg31, arg33, arg34, arg36, arg37, -1, -1, null, 9223372036854775807L, arg35, false, -1, -1, -1, -1, -1, arg38, arg39, arg40);
    }

    public static bj a(String arg11, String arg12, int arg13, int arg14, long arg15, int arg17, int arg18, List arg19, String arg20) {
        return bj.a(arg11, arg12, arg13, arg14, arg15, arg17, arg18, arg19, arg20, -1);
    }

    public static bj a(String arg27, String arg28, int arg29, int arg30, long arg31, int arg33, int arg34, List arg35, String arg36, int arg37) {
        return new bj(arg27, arg28, arg29, arg30, arg31, -1, -1, -1, -1f, arg33, arg34, arg36, 9223372036854775807L, arg35, false, -1, -1, arg37, -1, -1, null, -1, null);
    }

    public static bj a(String arg8, String arg9, int arg10, long arg11, String arg13) {
        return bj.a(arg8, arg9, arg10, arg11, arg13, 9223372036854775807L);
    }

    public static bj a(String arg27, String arg28, int arg29, long arg30, String arg32, long arg33) {
        return new bj(arg27, arg28, arg29, -1, arg30, -1, -1, -1, -1f, -1, -1, arg32, arg33, null, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    public static bj a(String arg27, String arg28, int arg29, long arg30, List arg32, String arg33) {
        return new bj(arg27, arg28, arg29, -1, arg30, -1, -1, -1, -1f, -1, -1, arg33, 9223372036854775807L, arg32, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    @TargetApi(value=24) private static void a(MediaFormat arg2, aw arg3) {
        if(arg3 == null) {
            return;
        }

        bj.a(arg2, "color-transfer", arg3.c);
        bj.a(arg2, "color-standard", arg3.a);
        bj.a(arg2, "color-range", arg3.b);
        bj.a(arg2, "hdr-static-info", arg3.d);
    }

    @TargetApi(value=16) private static final void a(MediaFormat arg1, String arg2, int arg3) {
        if(arg3 != -1) {
            arg1.setInteger(arg2, arg3);
        }
    }

    @TargetApi(value=16) private static void a(MediaFormat arg0, String arg1, byte[] arg2) {
        if(arg2 != null) {
            arg0.setByteBuffer(arg1, ByteBuffer.wrap(arg2));
        }
    }

    @TargetApi(value=16) private static final void a(MediaFormat arg0, String arg1, String arg2) {
        if(arg2 != null) {
            arg0.setString(arg1, arg2);
        }
    }

    public bj a(int arg31) {
        return new bj(this.a, this.b, this.c, arg31, this.e, this.h, this.i, this.l, this.m, this.q, this.r, this.v, this.w, this.f, this.g, this.j, this.k, this.s, this.t, this.u, this.o, this.n, this.p);
    }

    public bj a(int arg31, int arg32) {
        return new bj(this.a, this.b, this.c, this.d, this.e, this.h, this.i, this.l, this.m, this.q, this.r, this.v, this.w, this.f, this.g, this.j, this.k, this.s, arg31, arg32, this.o, this.n, this.p);
    }

    @SuppressLint(value={"InlinedApi"}) @TargetApi(value=16) public final MediaFormat b() {
        if(this.z == null) {
            MediaFormat v0 = new MediaFormat();
            v0.setString("mime", this.b);
            bj.a(v0, "language", this.v);
            bj.a(v0, "max-input-size", this.d);
            bj.a(v0, "width", this.h);
            bj.a(v0, "height", this.i);
            bj.a(v0, "rotation-degrees", this.l);
            bj.a(v0, "max-width", this.j);
            bj.a(v0, "max-height", this.k);
            bj.a(v0, "channel-count", this.q);
            bj.a(v0, "sample-rate", this.r);
            bj.a(v0, "encoder-delay", this.t);
            bj.a(v0, "encoder-padding", this.u);
            int v1;
            for(v1 = 0; v1 < this.f.size(); ++v1) {
                StringBuilder v3 = new StringBuilder(15);
                v3.append("csd-");
                v3.append(v1);
                v0.setByteBuffer(v3.toString(), ByteBuffer.wrap(this.f.get(v1)));
            }

            if(this.e != -1) {
                v0.setLong("durationUs", this.e);
            }

            bj.a(v0, this.p);
            this.z = v0;
        }

        return this.z;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg8) {
        if(this == (((bj)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else if(this.g == ((bj)arg8).g && this.c == ((bj)arg8).c && this.d == ((bj)arg8).d && this.e == ((bj)arg8).e && this.h == ((bj)arg8).h && this.i == ((bj)arg8).i && this.l == ((bj)arg8).l && this.m == ((bj)arg8).m && this.j == ((bj)arg8).j && this.k == ((bj)arg8).k && this.q == ((bj)arg8).q && this.r == ((bj)arg8).r && this.s == ((bj)arg8).s && this.t == ((bj)arg8).t && this.u == ((bj)arg8).u && this.w == ((bj)arg8).w && (ft.a(this.a, ((bj)arg8).a)) && (ft.a(this.v, ((bj)arg8).v)) && (ft.a(this.b, ((bj)arg8).b)) && this.f.size() == ((bj)arg8).f.size() && (ft.a(this.p, ((bj)arg8).p)) && (Arrays.equals(this.o, ((bj)arg8).o))) {
                if(this.n != ((bj)arg8).n) {
                }
                else {
                    int v2 = 0;
                    while(true) {
                        if(v2 >= this.f.size()) {
                            return 1;
                        }
                        else if(!Arrays.equals(this.f.get(v2), ((bj)arg8).f.get(v2))) {
                            return 0;
                        }
                        else {
                            ++v2;
                            continue;
                        }

                        return 0;
                    }

                    return 1;
                }
            }
        }

        return 0;
    }

    public int hashCode() {
        if(this.y == 0) {
            int v0 = 527;
            int v2 = 0;
            int v1 = this.a == null ? 0 : this.a.hashCode();
            v0 = (v0 + v1) * 31;
            v1 = this.b == null ? 0 : this.b.hashCode();
            v0 = ((((((((v0 + v1) * 31 + this.c) * 31 + this.d) * 31 + this.h) * 31 + this.i) * 31 + this.l) * 31 + Float.floatToRawIntBits(this.m)) * 31 + (((int)this.e))) * 31;
            v1 = this.g ? 1231 : 1237;
            v0 = ((((((((v0 + v1) * 31 + this.j) * 31 + this.k) * 31 + this.q) * 31 + this.r) * 31 + this.s) * 31 + this.t) * 31 + this.u) * 31;
            v1 = this.v == null ? 0 : this.v.hashCode();
            v0 = (v0 + v1) * 31 + (((int)this.w));
            while(v2 < this.f.size()) {
                v0 = v0 * 31 + Arrays.hashCode(this.f.get(v2));
                ++v2;
            }

            this.y = (v0 * 31 + Arrays.hashCode(this.o)) * 31 + this.n;
        }

        return this.y;
    }

    public String toString() {
        String v1 = this.a;
        String v2 = this.b;
        int v3 = this.c;
        int v4 = this.d;
        int v5 = this.h;
        int v6 = this.i;
        int v7 = this.l;
        float v8 = this.m;
        int v9 = this.q;
        int v10 = this.r;
        String v11 = this.v;
        long v12 = this.e;
        boolean v14 = this.g;
        int v16 = this.j;
        int v17 = this.k;
        int v18 = this.s;
        int v19 = this.t;
        int v21 = this.u;
        StringBuilder v15 = new StringBuilder(String.valueOf(v1).length() + 219 + String.valueOf(v2).length() + String.valueOf(v11).length());
        v15.append("MediaFormat(");
        v15.append(v1);
        v15.append(", ");
        v15.append(v2);
        v15.append(", ");
        v15.append(v3);
        v15.append(", ");
        v15.append(v4);
        v15.append(", ");
        v15.append(v5);
        v15.append(", ");
        v15.append(v6);
        v15.append(", ");
        v15.append(v7);
        v15.append(", ");
        v15.append(v8);
        v15.append(", ");
        v15.append(v9);
        v15.append(", ");
        v15.append(v10);
        v15.append(", ");
        v15.append(v11);
        v15.append(", ");
        v15.append(v12);
        v15.append(", ");
        v15.append(v14);
        v15.append(", ");
        v15.append(v16);
        v15.append(", ");
        v15.append(v17);
        v15.append(", ");
        v15.append(v18);
        v15.append(", ");
        v15.append(v19);
        v15.append(", ");
        v15.append(v21);
        v15.append(")");
        return v15.toString();
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeString(this.a);
        arg3.writeString(this.b);
        arg3.writeInt(this.c);
        arg3.writeInt(this.d);
        arg3.writeLong(this.e);
        arg3.writeInt(this.h);
        arg3.writeInt(this.i);
        arg3.writeInt(this.l);
        arg3.writeFloat(this.m);
        arg3.writeInt(this.q);
        arg3.writeInt(this.r);
        arg3.writeString(this.v);
        arg3.writeLong(this.w);
        arg3.writeList(this.f);
        arg3.writeInt(this.g);
        arg3.writeInt(this.j);
        arg3.writeInt(this.k);
        arg3.writeInt(this.s);
        arg3.writeInt(this.t);
        arg3.writeInt(this.u);
        int v0 = this.o != null ? 1 : 0;
        arg3.writeInt(v0);
        if(this.o != null) {
            arg3.writeByteArray(this.o);
        }

        arg3.writeInt(this.n);
        arg3.writeParcelable(this.p, arg4);
    }
}

