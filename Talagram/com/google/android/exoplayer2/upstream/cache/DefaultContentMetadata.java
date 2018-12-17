package com.google.android.exoplayer2.upstream.cache;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

public final class DefaultContentMetadata implements ContentMetadata {
    public static final DefaultContentMetadata EMPTY = null;
    private static final int MAX_VALUE_LENGTH = 10485760;
    private int hashCode;
    private final Map metadata;

    static {
        DefaultContentMetadata.EMPTY = new DefaultContentMetadata(Collections.emptyMap());
    }

    private DefaultContentMetadata(Map arg1) {
        super();
        this.metadata = Collections.unmodifiableMap(arg1);
    }

    private static void addValues(HashMap arg5, Map arg6) {
        int v4;
        byte[] v2;
        Object v1;
        Iterator v0 = arg6.keySet().iterator();
        while(true) {
            if(!v0.hasNext()) {
                return;
            }

            v1 = v0.next();
            v2 = DefaultContentMetadata.getBytes(arg6.get(v1));
            v4 = 10485760;
            if(v2.length > v4) {
                break;
            }

            arg5.put(v1, v2);
        }

        throw new IllegalArgumentException(String.format("The size of %s (%d) is greater than maximum allowed: %d", v1, Integer.valueOf(v2.length), Integer.valueOf(v4)));
    }

    private static Map applyMutations(Map arg1, ContentMetadataMutations arg2) {
        HashMap v0 = new HashMap(arg1);
        DefaultContentMetadata.removeValues(v0, arg2.getRemovedValues());
        DefaultContentMetadata.addValues(v0, arg2.getEditedValues());
        return ((Map)v0);
    }

    public final boolean contains(String arg2) {
        return this.metadata.containsKey(arg2);
    }

    public DefaultContentMetadata copyWithMutationsApplied(ContentMetadataMutations arg2) {
        Map v2 = DefaultContentMetadata.applyMutations(this.metadata, arg2);
        if(this.isMetadataEqual(v2)) {
            return this;
        }

        return new DefaultContentMetadata(v2);
    }

    public boolean equals(Object arg3) {
        if(this == (((DefaultContentMetadata)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return this.isMetadataEqual(((DefaultContentMetadata)arg3).metadata);
            }
        }

        return 0;
    }

    public final long get(String arg2, long arg3) {
        if(this.metadata.containsKey(arg2)) {
            return ByteBuffer.wrap(this.metadata.get(arg2)).getLong();
        }

        return arg3;
    }

    public final String get(String arg2, String arg3) {
        if(this.metadata.containsKey(arg2)) {
            arg3 = new String(this.metadata.get(arg2), Charset.forName("UTF-8"));
        }

        return arg3;
    }

    public final byte[] get(String arg2, byte[] arg3) {
        if(this.metadata.containsKey(arg2)) {
            Object v2 = this.metadata.get(arg2);
            return Arrays.copyOf(((byte[])v2), v2.length);
        }

        return arg3;
    }

    private static byte[] getBytes(Object arg3) {
        if((arg3 instanceof Long)) {
            return ByteBuffer.allocate(8).putLong(((Long)arg3).longValue()).array();
        }

        if((arg3 instanceof String)) {
            return ((String)arg3).getBytes(Charset.forName("UTF-8"));
        }

        if((arg3 instanceof byte[])) {
            return arg3;
        }

        throw new IllegalArgumentException();
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            int v0 = 0;
            Iterator v1 = this.metadata.entrySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                v0 += Arrays.hashCode(((Map$Entry)v2).getValue()) ^ ((Map$Entry)v2).getKey().hashCode();
            }

            this.hashCode = v0;
        }

        return this.hashCode;
    }

    private boolean isMetadataEqual(Map arg5) {
        if(this.metadata.size() != arg5.size()) {
            return 0;
        }

        Iterator v0 = this.metadata.entrySet().iterator();
        do {
            if(!v0.hasNext()) {
                return 1;
            }

            Object v1 = v0.next();
        }
        while(Arrays.equals(((Map$Entry)v1).getValue(), arg5.get(((Map$Entry)v1).getKey())));

        return 0;
    }

    public static DefaultContentMetadata readFromStream(DataInputStream arg6) {
        int v4;
        int v0 = arg6.readInt();
        HashMap v1 = new HashMap();
        int v2 = 0;
        while(true) {
            if(v2 >= v0) {
                goto label_24;
            }

            String v3 = arg6.readUTF();
            v4 = arg6.readInt();
            if(v4 >= 0 && v4 <= 10485760) {
                byte[] v4_1 = new byte[v4];
                arg6.readFully(v4_1);
                v1.put(v3, v4_1);
                ++v2;
                continue;
            }

            break;
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("Invalid value size: ");
        v0_1.append(v4);
        throw new IOException(v0_1.toString());
    label_24:
        return new DefaultContentMetadata(((Map)v1));
    }

    private static void removeValues(HashMap arg2, List arg3) {
        int v0;
        for(v0 = 0; v0 < arg3.size(); ++v0) {
            arg2.remove(arg3.get(v0));
        }
    }

    public void writeToStream(DataOutputStream arg4) {
        arg4.writeInt(this.metadata.size());
        Iterator v0 = this.metadata.entrySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            arg4.writeUTF(((Map$Entry)v1).getKey());
            v1 = ((Map$Entry)v1).getValue();
            arg4.writeInt(v1.length);
            arg4.write(((byte[])v1));
        }
    }
}

