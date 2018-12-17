package org.telegram.customization.util.crypto.wire;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ParcelHeader {
    public static final byte GZIP = 103;
    public static final byte LAST_VERSION = 1;
    public static final byte NONE = 110;
    public static final long NO_COMPRESSION = 0;
    public static final int SIZE = 14;
    public final byte compression;
    public final int crc32;
    public final long key;
    public final byte version;

    public ParcelHeader(byte[] arg2, long arg3, byte arg5) {
        super();
        this.version = 1;
        this.crc32 = Crc32.hash(arg2);
        this.key = arg3;
        this.compression = arg5;
    }

    public ParcelHeader(byte arg1, int arg2, long arg3, byte arg5) {
        super();
        this.version = arg1;
        this.crc32 = arg2;
        this.key = arg3;
        this.compression = arg5;
    }

    public static ParcelHeader from(DataInputStream arg7) {
        return new ParcelHeader(((byte)arg7.read()), arg7.readInt(), arg7.readLong(), ((byte)arg7.read()));
    }

    public boolean isCompressed() {
        boolean v0 = this.compression != 110 ? true : false;
        return v0;
    }

    public boolean isEncrypted() {
        boolean v0 = this.key != 0 ? true : false;
        return v0;
    }

    public static ParcelHeader parse(byte[] arg7) {
        ByteBuffer v7 = ByteBuffer.wrap(arg7);
        return new ParcelHeader(v7.get(), v7.getInt(), v7.getLong(), v7.get());
    }

    public void writeTo(OutputStream arg4) {
        DataOutputStream v0 = new DataOutputStream(arg4);
        v0.write(this.version);
        v0.writeInt(this.crc32);
        v0.writeLong(this.key);
        v0.write(this.compression);
    }
}

