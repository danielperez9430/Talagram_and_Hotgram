package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import java.nio.ByteBuffer;

@Descriptor(tags={0}) public abstract class BaseDescriptor {
    int sizeBytes;
    int sizeOfInstance;
    int tag;

    static {
    }

    public BaseDescriptor() {
        super();
    }

    public int getSize() {
        return this.sizeOfInstance + 1 + this.sizeBytes;
    }

    public int getSizeBytes() {
        return this.sizeBytes;
    }

    public int getSizeOfInstance() {
        return this.sizeOfInstance;
    }

    public int getTag() {
        return this.tag;
    }

    public final void parse(int arg5, ByteBuffer arg6) {
        this.tag = arg5;
        arg5 = IsoTypeReader.readUInt8(arg6);
        this.sizeOfInstance = arg5 & 127;
        int v1 = 1;
        while(arg5 >>> 7 == 1) {
            arg5 = IsoTypeReader.readUInt8(arg6);
            ++v1;
            this.sizeOfInstance = this.sizeOfInstance << 7 | arg5 & 127;
        }

        this.sizeBytes = v1;
        ByteBuffer v5 = arg6.slice();
        v5.limit(this.sizeOfInstance);
        this.parseDetail(v5);
        arg6.position(arg6.position() + this.sizeOfInstance);
    }

    public abstract void parseDetail(ByteBuffer arg1);

    public String toString() {
        return "BaseDescriptor" + "{tag=" + this.tag + ", sizeOfInstance=" + this.sizeOfInstance + '}';
    }
}

