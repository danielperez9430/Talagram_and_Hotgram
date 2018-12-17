package com.coremedia.iso;

import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.DataSource;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

public abstract class AbstractBoxParser implements BoxParser {
    class com.coremedia.iso.AbstractBoxParser$1 extends ThreadLocal {
        com.coremedia.iso.AbstractBoxParser$1(AbstractBoxParser arg1) {
            AbstractBoxParser.this = arg1;
            super();
        }

        protected Object initialValue() {
            return this.initialValue();
        }

        protected ByteBuffer initialValue() {
            return ByteBuffer.allocate(32);
        }
    }

    private static Logger LOG;
    ThreadLocal header;

    static {
        AbstractBoxParser.LOG = Logger.getLogger(AbstractBoxParser.class.getName());
    }

    public AbstractBoxParser() {
        super();
        this.header = new com.coremedia.iso.AbstractBoxParser$1(this);
    }

    public abstract Box createBox(String arg1, byte[] arg2, String arg3);

    public Box parseBox(DataSource arg14, Container arg15) {
        int v4_1;
        byte[] v3_1;
        long v0 = arg14.position();
        int v3 = 8;
        this.header.get().rewind().limit(v3);
        while(true) {
            int v2 = arg14.read(this.header.get());
            if(v2 == v3) {
                goto label_10;
            }

            if(v2 < 0) {
                break;
            }
        }

        arg14.position(v0);
        throw new EOFException();
    label_10:
        this.header.get().rewind();
        v0 = IsoTypeReader.readUInt32(this.header.get());
        long v4 = 8;
        Box v6 = null;
        long v7 = 1;
        if(Long.compare(v0, v4) < 0 && v0 > v7) {
            Logger v14 = AbstractBoxParser.LOG;
            StringBuilder v15 = new StringBuilder("Plausibility check failed: size < 8 (size = ");
            v15.append(v0);
            v15.append("). Stop parsing!");
            v14.severe(v15.toString());
            return v6;
        }

        String v2_1 = IsoTypeReader.read4cc(this.header.get());
        int v9 = Long.compare(v0, v7);
        v7 = 16;
        int v10 = 16;
        if(v9 == 0) {
            this.header.get().limit(v10);
            arg14.read(this.header.get());
            this.header.get().position(v3);
            v0 = IsoTypeReader.readUInt64(this.header.get()) - v7;
        }
        else if(v0 == 0) {
            v0 = arg14.size() - arg14.position();
        }
        else {
            v0 -= v4;
        }

        if("uuid".equals(v2_1)) {
            this.header.get().limit(this.header.get().limit() + v10);
            arg14.read(this.header.get());
            v3_1 = new byte[v10];
            v4_1 = this.header.get().position() - v10;
            goto label_79;
        }
        else {
            v7 = v0;
            v3_1 = ((byte[])v6);
            goto label_99;
        label_79:
            while(v4_1 < this.header.get().position()) {
                v3_1[v4_1 - (this.header.get().position() - v10)] = this.header.get().get(v4_1);
                ++v4_1;
            }

            v7 = v0 - v7;
        }

    label_99:
        String v0_1 = (arg15 instanceof Box) ? arg15.getType() : "";
        Box v0_2 = this.createBox(v2_1, v3_1, v0_1);
        v0_2.setParent(arg15);
        this.header.get().rewind();
        v0_2.parse(arg14, this.header.get(), v7, this);
        return v0_2;
    }
}

