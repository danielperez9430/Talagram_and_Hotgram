package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class EventMessageDecoder implements MetadataDecoder {
    public EventMessageDecoder() {
        super();
    }

    public Metadata decode(MetadataInputBuffer arg17) {
        ByteBuffer v0 = arg17.data;
        byte[] v1 = v0.array();
        int v0_1 = v0.limit();
        ParsableByteArray v2 = new ParsableByteArray(v1, v0_1);
        String v4 = v2.readNullTerminatedString();
        String v5 = v2.readNullTerminatedString();
        long v12 = v2.readUnsignedInt();
        return new Metadata(new Entry[]{new EventMessage(v4, v5, Util.scaleLargeTimestamp(v2.readUnsignedInt(), 1000, v12), v2.readUnsignedInt(), Arrays.copyOfRange(v1, v2.getPosition(), v0_1), Util.scaleLargeTimestamp(v2.readUnsignedInt(), 1000000, v12))});
    }
}

