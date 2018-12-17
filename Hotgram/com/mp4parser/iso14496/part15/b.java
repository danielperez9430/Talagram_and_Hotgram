package com.mp4parser.iso14496.part15;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class b {
    public class a {
        public boolean a;
        public boolean b;
        public int c;
        public List d;

        public a() {
            super();
        }

        public boolean equals(Object arg6) {
            boolean v0 = true;
            if(this == (((a)arg6))) {
                return 1;
            }

            if(arg6 != null) {
                if(this.getClass() != arg6.getClass()) {
                }
                else if(this.a != ((a)arg6).a) {
                    return 0;
                }
                else if(this.c != ((a)arg6).c) {
                    return 0;
                }
                else if(this.b != ((a)arg6).b) {
                    return 0;
                }
                else {
                    ListIterator v2 = this.d.listIterator();
                    ListIterator v6 = ((a)arg6).d.listIterator();
                    do {
                        if(v2.hasNext()) {
                            if(!v6.hasNext()) {
                            }
                            else {
                                Object v3 = v2.next();
                                Object v4 = v6.next();
                                if(v3 == null) {
                                    if(v4 == null) {
                                        continue;
                                    }
                                }
                                else if(Arrays.equals(((byte[])v3), ((byte[])v4))) {
                                    continue;
                                }

                                return 0;
                            }
                        }

                        goto label_38;
                    }
                    while(true);

                    return 0;
                label_38:
                    if((v2.hasNext()) || (v6.hasNext())) {
                        v0 = false;
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            int v0 = ((this.a * 31 + this.b) * 31 + this.c) * 31;
            int v1 = this.d != null ? this.d.hashCode() : 0;
            return v0 + v1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Array{nal_unit_type=");
            v0.append(this.c);
            v0.append(", reserved=");
            v0.append(this.b);
            v0.append(", array_completeness=");
            v0.append(this.a);
            v0.append(", num_nals=");
            v0.append(this.d.size());
            v0.append('}');
            return v0.toString();
        }
    }

    boolean A;
    int a;
    int b;
    boolean c;
    int d;
    long e;
    long f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    boolean u;
    int v;
    List w;
    boolean x;
    boolean y;
    boolean z;

    public b() {
        super();
        this.h = 15;
        this.j = 63;
        this.l = 63;
        this.n = 31;
        this.p = 31;
        this.w = new ArrayList();
    }

    public void a(List arg1) {
        this.w = arg1;
    }

    public void a(int arg1) {
        this.r = arg1;
    }

    public int a() {
        Iterator v0 = this.w.iterator();
        int v1 = 23;
        while(v0.hasNext()) {
            v1 += 3;
            Iterator v2 = v0.next().d.iterator();
            while(v2.hasNext()) {
                v1 = v1 + 2 + v2.next().length;
            }
        }

        return v1;
    }

    public void a(ByteBuffer arg10) {
        this.a = IsoTypeReader.readUInt8(arg10);
        int v0 = IsoTypeReader.readUInt8(arg10);
        this.b = (v0 & 192) >> 6;
        boolean v1 = (v0 & 32) > 0 ? true : false;
        this.c = v1;
        this.d = v0 & 31;
        this.e = IsoTypeReader.readUInt32(arg10);
        this.f = IsoTypeReader.readUInt48(arg10);
        int v4 = 44;
        long v5 = 0;
        boolean v0_1 = (this.f >> v4 & 8) > v5 ? true : false;
        this.x = v0_1;
        v0_1 = (this.f >> v4 & 4) > v5 ? true : false;
        this.y = v0_1;
        v0_1 = (this.f >> v4 & 2) > v5 ? true : false;
        this.z = v0_1;
        v0_1 = (this.f >> v4 & 1) > v5 ? true : false;
        this.A = v0_1;
        this.f &= 140737488355327L;
        this.g = IsoTypeReader.readUInt8(arg10);
        v0 = IsoTypeReader.readUInt16(arg10);
        this.h = (61440 & v0) >> 12;
        this.i = v0 & 4095;
        v0 = IsoTypeReader.readUInt8(arg10);
        this.j = (v0 & 252) >> 2;
        this.k = v0 & 3;
        v0 = IsoTypeReader.readUInt8(arg10);
        this.l = (v0 & 252) >> 2;
        this.m = v0 & 3;
        v0 = IsoTypeReader.readUInt8(arg10);
        this.n = (v0 & 248) >> 3;
        this.o = v0 & 7;
        v0 = IsoTypeReader.readUInt8(arg10);
        this.p = (v0 & 248) >> 3;
        this.q = v0 & 7;
        this.r = IsoTypeReader.readUInt16(arg10);
        v0 = IsoTypeReader.readUInt8(arg10);
        this.s = (v0 & 192) >> 6;
        this.t = (v0 & 56) >> 3;
        v1 = (v0 & 4) > 0 ? true : false;
        this.u = v1;
        this.v = v0 & 3;
        v0 = IsoTypeReader.readUInt8(arg10);
        this.w = new ArrayList();
        int v1_1;
        for(v1_1 = 0; v1_1 < v0; ++v1_1) {
            a v4_1 = new a();
            int v5_1 = IsoTypeReader.readUInt8(arg10);
            boolean v6 = (v5_1 & 128) > 0 ? true : false;
            v4_1.a = v6;
            v6 = (v5_1 & 64) > 0 ? true : false;
            v4_1.b = v6;
            v4_1.c = v5_1 & 63;
            v5_1 = IsoTypeReader.readUInt16(arg10);
            v4_1.d = new ArrayList();
            int v6_1;
            for(v6_1 = 0; v6_1 < v5_1; ++v6_1) {
                byte[] v7 = new byte[IsoTypeReader.readUInt16(arg10)];
                arg10.get(v7);
                v4_1.d.add(v7);
            }

            this.w.add(v4_1);
        }
    }

    public void b(ByteBuffer arg6) {
        IsoTypeWriter.writeUInt8(arg6, this.a);
        int v0 = this.b << 6;
        int v1 = this.c ? 32 : 0;
        IsoTypeWriter.writeUInt8(arg6, v0 + v1 + this.d);
        IsoTypeWriter.writeUInt32(arg6, this.e);
        long v0_1 = this.f;
        if(this.x) {
            v0_1 |= 140737488355328L;
        }

        if(this.y) {
            v0_1 |= 70368744177664L;
        }

        if(this.z) {
            v0_1 |= 35184372088832L;
        }

        if(this.A) {
            v0_1 |= 17592186044416L;
        }

        IsoTypeWriter.writeUInt48(arg6, v0_1);
        IsoTypeWriter.writeUInt8(arg6, this.g);
        IsoTypeWriter.writeUInt16(arg6, (this.h << 12) + this.i);
        IsoTypeWriter.writeUInt8(arg6, (this.j << 2) + this.k);
        IsoTypeWriter.writeUInt8(arg6, (this.l << 2) + this.m);
        IsoTypeWriter.writeUInt8(arg6, (this.n << 3) + this.o);
        IsoTypeWriter.writeUInt8(arg6, (this.p << 3) + this.q);
        IsoTypeWriter.writeUInt16(arg6, this.r);
        v0 = (this.s << 6) + (this.t << 3);
        v1 = this.u ? 4 : 0;
        IsoTypeWriter.writeUInt8(arg6, v0 + v1 + this.v);
        IsoTypeWriter.writeUInt8(arg6, this.w.size());
        Iterator v0_2 = this.w.iterator();
        while(v0_2.hasNext()) {
            Object v1_1 = v0_2.next();
            int v3 = ((a)v1_1).a ? 128 : 0;
            int v4 = ((a)v1_1).b ? 64 : 0;
            IsoTypeWriter.writeUInt8(arg6, v3 + v4 + ((a)v1_1).c);
            IsoTypeWriter.writeUInt16(arg6, ((a)v1_1).d.size());
            Iterator v1_2 = ((a)v1_1).d.iterator();
            while(v1_2.hasNext()) {
                Object v3_1 = v1_2.next();
                IsoTypeWriter.writeUInt16(arg6, v3_1.length);
                arg6.put(((byte[])v3_1));
            }
        }
    }

    public boolean equals(Object arg8) {
        if(this == (((b)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else if(this.r != ((b)arg8).r) {
                return 0;
            }
            else if(this.q != ((b)arg8).q) {
                return 0;
            }
            else if(this.o != ((b)arg8).o) {
                return 0;
            }
            else if(this.m != ((b)arg8).m) {
                return 0;
            }
            else if(this.a != ((b)arg8).a) {
                return 0;
            }
            else if(this.s != ((b)arg8).s) {
                return 0;
            }
            else if(this.f != ((b)arg8).f) {
                return 0;
            }
            else if(this.g != ((b)arg8).g) {
                return 0;
            }
            else if(this.e != ((b)arg8).e) {
                return 0;
            }
            else if(this.d != ((b)arg8).d) {
                return 0;
            }
            else if(this.b != ((b)arg8).b) {
                return 0;
            }
            else if(this.c != ((b)arg8).c) {
                return 0;
            }
            else if(this.v != ((b)arg8).v) {
                return 0;
            }
            else if(this.i != ((b)arg8).i) {
                return 0;
            }
            else if(this.t != ((b)arg8).t) {
                return 0;
            }
            else if(this.k != ((b)arg8).k) {
                return 0;
            }
            else if(this.h != ((b)arg8).h) {
                return 0;
            }
            else if(this.j != ((b)arg8).j) {
                return 0;
            }
            else if(this.l != ((b)arg8).l) {
                return 0;
            }
            else if(this.n != ((b)arg8).n) {
                return 0;
            }
            else if(this.p != ((b)arg8).p) {
                return 0;
            }
            else if(this.u != ((b)arg8).u) {
                return 0;
            }
            else {
                if(this.w != null) {
                    if(!this.w.equals(((b)arg8).w)) {
                        return 0;
                    }
                }
                else if(((b)arg8).w != null) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v0 = (((((((((((((((((((((this.a * 31 + this.b) * 31 + this.c) * 31 + this.d) * 31 + (((int)(this.e ^ this.e >>> 32)))) * 31 + (((int)(this.f ^ this.f >>> 32)))) * 31 + this.g) * 31 + this.h) * 31 + this.i) * 31 + this.j) * 31 + this.k) * 31 + this.l) * 31 + this.m) * 31 + this.n) * 31 + this.o) * 31 + this.p) * 31 + this.q) * 31 + this.r) * 31 + this.s) * 31 + this.t) * 31 + this.u) * 31 + this.v) * 31;
        int v1 = this.w != null ? this.w.hashCode() : 0;
        return v0 + v1;
    }

    public String toString() {
        String v1_1;
        StringBuilder v1;
        StringBuilder v0 = new StringBuilder("HEVCDecoderConfigurationRecord{configurationVersion=");
        v0.append(this.a);
        v0.append(", general_profile_space=");
        v0.append(this.b);
        v0.append(", general_tier_flag=");
        v0.append(this.c);
        v0.append(", general_profile_idc=");
        v0.append(this.d);
        v0.append(", general_profile_compatibility_flags=");
        v0.append(this.e);
        v0.append(", general_constraint_indicator_flags=");
        v0.append(this.f);
        v0.append(", general_level_idc=");
        v0.append(this.g);
        if(this.h != 15) {
            v1 = new StringBuilder(", reserved1=");
            v1.append(this.h);
            v1_1 = v1.toString();
        }
        else {
            v1_1 = "";
        }

        v0.append(v1_1);
        v0.append(", min_spatial_segmentation_idc=");
        v0.append(this.i);
        int v2 = 63;
        if(this.j != v2) {
            v1 = new StringBuilder(", reserved2=");
            v1.append(this.j);
            v1_1 = v1.toString();
        }
        else {
            v1_1 = "";
        }

        v0.append(v1_1);
        v0.append(", parallelismType=");
        v0.append(this.k);
        if(this.l != v2) {
            v1 = new StringBuilder(", reserved3=");
            v1.append(this.l);
            v1_1 = v1.toString();
        }
        else {
            v1_1 = "";
        }

        v0.append(v1_1);
        v0.append(", chromaFormat=");
        v0.append(this.m);
        v2 = 31;
        if(this.n != v2) {
            v1 = new StringBuilder(", reserved4=");
            v1.append(this.n);
            v1_1 = v1.toString();
        }
        else {
            v1_1 = "";
        }

        v0.append(v1_1);
        v0.append(", bitDepthLumaMinus8=");
        v0.append(this.o);
        if(this.p != v2) {
            v1 = new StringBuilder(", reserved5=");
            v1.append(this.p);
            v1_1 = v1.toString();
        }
        else {
            v1_1 = "";
        }

        v0.append(v1_1);
        v0.append(", bitDepthChromaMinus8=");
        v0.append(this.q);
        v0.append(", avgFrameRate=");
        v0.append(this.r);
        v0.append(", constantFrameRate=");
        v0.append(this.s);
        v0.append(", numTemporalLayers=");
        v0.append(this.t);
        v0.append(", temporalIdNested=");
        v0.append(this.u);
        v0.append(", lengthSizeMinusOne=");
        v0.append(this.v);
        v0.append(", arrays=");
        v0.append(this.w);
        v0.append('}');
        return v0.toString();
    }
}

