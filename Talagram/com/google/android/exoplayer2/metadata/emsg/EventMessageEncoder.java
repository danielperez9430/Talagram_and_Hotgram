package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class EventMessageEncoder {
    private final ByteArrayOutputStream byteArrayOutputStream;
    private final DataOutputStream dataOutputStream;

    public EventMessageEncoder() {
        super();
        this.byteArrayOutputStream = new ByteArrayOutputStream(512);
        this.dataOutputStream = new DataOutputStream(this.byteArrayOutputStream);
    }

    public byte[] encode(EventMessage arg10, long arg11) {
        boolean v0 = arg11 >= 0 ? true : false;
        Assertions.checkArgument(v0);
        this.byteArrayOutputStream.reset();
        try {
            EventMessageEncoder.writeNullTerminatedString(this.dataOutputStream, arg10.schemeIdUri);
            String v0_1 = arg10.value != null ? arg10.value : "";
            EventMessageEncoder.writeNullTerminatedString(this.dataOutputStream, v0_1);
            EventMessageEncoder.writeUnsignedInt(this.dataOutputStream, arg11);
            EventMessageEncoder.writeUnsignedInt(this.dataOutputStream, Util.scaleLargeTimestamp(arg10.presentationTimeUs, arg11, 1000000));
            EventMessageEncoder.writeUnsignedInt(this.dataOutputStream, Util.scaleLargeTimestamp(arg10.durationMs, arg11, 1000));
            EventMessageEncoder.writeUnsignedInt(this.dataOutputStream, arg10.id);
            this.dataOutputStream.write(arg10.messageData);
            this.dataOutputStream.flush();
            return this.byteArrayOutputStream.toByteArray();
        }
        catch(IOException v10) {
            throw new RuntimeException(((Throwable)v10));
        }
    }

    private static void writeNullTerminatedString(DataOutputStream arg0, String arg1) {
        arg0.writeBytes(arg1);
        arg0.writeByte(0);
    }

    private static void writeUnsignedInt(DataOutputStream arg2, long arg3) {
        arg2.writeByte((((int)(arg3 >>> 24))) & 255);
        arg2.writeByte((((int)(arg3 >>> 16))) & 255);
        arg2.writeByte((((int)(arg3 >>> 8))) & 255);
        arg2.writeByte((((int)arg3)) & 255);
    }
}

