package com.mp4parser.iso14496.part15;

import com.coremedia.iso.Hex;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.authoring.tracks.CleanInputStream;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitWriterBuffer;
import com.googlecode.mp4parser.h264.model.PictureParameterSet;
import com.googlecode.mp4parser.h264.model.SeqParameterSet;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public List f;
    public List g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    public List l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;

    public a() {
        super();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = true;
        this.i = 1;
        this.j = 0;
        this.k = 0;
        this.l = new ArrayList();
        this.m = 63;
        this.n = 7;
        this.o = 31;
        this.p = 31;
        this.q = 31;
    }

    public a(ByteBuffer arg12) {
        super();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = true;
        this.i = 1;
        int v0 = 0;
        this.j = 0;
        this.k = 0;
        this.l = new ArrayList();
        this.m = 63;
        this.n = 7;
        this.o = 31;
        this.p = 31;
        this.q = 31;
        this.a = IsoTypeReader.readUInt8(arg12);
        this.b = IsoTypeReader.readUInt8(arg12);
        this.c = IsoTypeReader.readUInt8(arg12);
        this.d = IsoTypeReader.readUInt8(arg12);
        BitReaderBuffer v1 = new BitReaderBuffer(arg12);
        int v2 = 6;
        this.m = v1.readBits(v2);
        int v3 = 2;
        this.e = v1.readBits(v3);
        int v4 = 3;
        this.n = v1.readBits(v4);
        int v5 = 5;
        int v1_1 = v1.readBits(v5);
        int v6;
        for(v6 = 0; v6 < v1_1; ++v6) {
            byte[] v7 = new byte[IsoTypeReader.readUInt16(arg12)];
            arg12.get(v7);
            this.f.add(v7);
        }

        long v7_1 = ((long)IsoTypeReader.readUInt8(arg12));
        for(v1_1 = 0; (((long)v1_1)) < v7_1; ++v1_1) {
            byte[] v6_1 = new byte[IsoTypeReader.readUInt16(arg12)];
            arg12.get(v6_1);
            this.g.add(v6_1);
        }

        if(arg12.remaining() < 4) {
            this.h = false;
        }

        if(this.h) {
            if(this.b != 100 && this.b != 110 && this.b != 122 && this.b != 144) {
                goto label_96;
            }

            v1 = new BitReaderBuffer(arg12);
            this.o = v1.readBits(v2);
            this.i = v1.readBits(v3);
            this.p = v1.readBits(v5);
            this.j = v1.readBits(v4);
            this.q = v1.readBits(v5);
            this.k = v1.readBits(v4);
            long v1_2 = ((long)IsoTypeReader.readUInt8(arg12));
            goto label_86;
        }
        else {
        label_96:
            this.i = -1;
            this.j = -1;
            this.k = -1;
            return;
        label_86:
            while((((long)v0)) < v1_2) {
                byte[] v3_1 = new byte[IsoTypeReader.readUInt16(arg12)];
                arg12.get(v3_1);
                this.l.add(v3_1);
                ++v0;
            }
        }
    }

    public long a() {
        long v4;
        Iterator v0 = this.f.iterator();
        long v1;
        for(v1 = 6; true; v1 = v1 + v4 + (((long)v0.next().length))) {
            v4 = 2;
            if(!v0.hasNext()) {
                break;
            }
        }

        ++v1;
        Iterator v3 = this.g.iterator();
        while(v3.hasNext()) {
            v1 = v1 + v4 + (((long)v3.next().length));
        }

        if((this.h) && (this.b == 100 || this.b == 110 || this.b == 122 || this.b == 144)) {
            v1 += 4;
            v0 = this.l.iterator();
            while(v0.hasNext()) {
                v1 = v1 + v4 + (((long)v0.next().length));
            }
        }

        return v1;
    }

    public void a(ByteBuffer arg8) {
        Object v1;
        IsoTypeWriter.writeUInt8(arg8, this.a);
        IsoTypeWriter.writeUInt8(arg8, this.b);
        IsoTypeWriter.writeUInt8(arg8, this.c);
        IsoTypeWriter.writeUInt8(arg8, this.d);
        BitWriterBuffer v0 = new BitWriterBuffer(arg8);
        int v2 = 6;
        v0.writeBits(this.m, v2);
        int v3 = 2;
        v0.writeBits(this.e, v3);
        int v4 = 3;
        v0.writeBits(this.n, v4);
        int v5 = 5;
        v0.writeBits(this.g.size(), v5);
        Iterator v0_1 = this.f.iterator();
        while(v0_1.hasNext()) {
            v1 = v0_1.next();
            IsoTypeWriter.writeUInt16(arg8, v1.length);
            arg8.put(((byte[])v1));
        }

        IsoTypeWriter.writeUInt8(arg8, this.g.size());
        Iterator v1_1 = this.g.iterator();
        while(v1_1.hasNext()) {
            Object v0_2 = v1_1.next();
            IsoTypeWriter.writeUInt16(arg8, v0_2.length);
            arg8.put(((byte[])v0_2));
        }

        if((this.h) && (this.b == 100 || this.b == 110 || this.b == 122 || this.b == 144)) {
            v0 = new BitWriterBuffer(arg8);
            v0.writeBits(this.o, v2);
            v0.writeBits(this.i, v3);
            v0.writeBits(this.p, v5);
            v0.writeBits(this.j, v4);
            v0.writeBits(this.q, v5);
            v0.writeBits(this.k, v4);
            v0_1 = this.l.iterator();
            while(v0_1.hasNext()) {
                v1 = v0_1.next();
                IsoTypeWriter.writeUInt16(arg8, v1.length);
                arg8.put(((byte[])v1));
            }
        }
    }

    public String[] b() {
        String v2_1;
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.g.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            try {
                v2_1 = PictureParameterSet.read(new ByteArrayInputStream(((byte[])v2), 1, v2.length - 1)).toString();
            }
            catch(IOException v0_1) {
                throw new RuntimeException(((Throwable)v0_1));
            }

            v0.add(v2_1);
        }

        return v0.toArray(new String[v0.size()]);
    }

    public String[] c() {
        String v2_1;
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.f.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            String v3 = "not parsable";
            try {
                v2_1 = SeqParameterSet.read(new CleanInputStream(new ByteArrayInputStream(((byte[])v2), 1, v2.length - 1))).toString();
            }
            catch(IOException ) {
                v2_1 = v3;
            }

            v0.add(v2_1);
        }

        return v0.toArray(new String[v0.size()]);
    }

    public List d() {
        ArrayList v0 = new ArrayList(this.f.size());
        Iterator v1 = this.f.iterator();
        while(v1.hasNext()) {
            ((List)v0).add(Hex.encodeHex(v1.next()));
        }

        return ((List)v0);
    }

    public List e() {
        ArrayList v0 = new ArrayList(this.l.size());
        Iterator v1 = this.l.iterator();
        while(v1.hasNext()) {
            ((List)v0).add(Hex.encodeHex(v1.next()));
        }

        return ((List)v0);
    }

    public List f() {
        ArrayList v0 = new ArrayList(this.g.size());
        Iterator v1 = this.g.iterator();
        while(v1.hasNext()) {
            ((List)v0).add(Hex.encodeHex(v1.next()));
        }

        return ((List)v0);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("AvcDecoderConfigurationRecord{configurationVersion=");
        v0.append(this.a);
        v0.append(", avcProfileIndication=");
        v0.append(this.b);
        v0.append(", profileCompatibility=");
        v0.append(this.c);
        v0.append(", avcLevelIndication=");
        v0.append(this.d);
        v0.append(", lengthSizeMinusOne=");
        v0.append(this.e);
        v0.append(", hasExts=");
        v0.append(this.h);
        v0.append(", chromaFormat=");
        v0.append(this.i);
        v0.append(", bitDepthLumaMinus8=");
        v0.append(this.j);
        v0.append(", bitDepthChromaMinus8=");
        v0.append(this.k);
        v0.append(", lengthSizeMinusOnePaddingBits=");
        v0.append(this.m);
        v0.append(", numberOfSequenceParameterSetsPaddingBits=");
        v0.append(this.n);
        v0.append(", chromaFormatPaddingBits=");
        v0.append(this.o);
        v0.append(", bitDepthLumaMinus8PaddingBits=");
        v0.append(this.p);
        v0.append(", bitDepthChromaMinus8PaddingBits=");
        v0.append(this.q);
        v0.append('}');
        return v0.toString();
    }
}

