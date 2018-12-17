package com.googlecode.mp4parser.authoring.builder;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ByteBufferHelper {
    public ByteBufferHelper() {
        super();
    }

    public static List mergeAdjacentBuffers(List arg6) {
        ByteBuffer v1_1;
        Object v2_1;
        ArrayList v0 = new ArrayList(arg6.size());
        Iterator v6 = arg6.iterator();
        while(v6.hasNext()) {
            Object v1 = v6.next();
            int v2 = v0.size() - 1;
            if(v2 < 0 || !((ByteBuffer)v1).hasArray() || !v0.get(v2).hasArray() || ((ByteBuffer)v1).array() != v0.get(v2).array() || v0.get(v2).arrayOffset() + v0.get(v2).limit() != ((ByteBuffer)v1).arrayOffset()) {
                if(v2 >= 0 && ((v1 instanceof MappedByteBuffer)) && ((v0.get(v2) instanceof MappedByteBuffer)) && v0.get(v2).limit() == v0.get(v2).capacity() - ((ByteBuffer)v1).capacity()) {
                    v2_1 = v0.get(v2);
                    ((ByteBuffer)v2_1).limit(((ByteBuffer)v1).limit() + ((ByteBuffer)v2_1).limit());
                    continue;
                }

                ((ByteBuffer)v1).reset();
            }
            else {
                v2_1 = v0.remove(v2);
                v1_1 = ByteBuffer.wrap(((ByteBuffer)v1).array(), ((ByteBuffer)v2_1).arrayOffset(), ((ByteBuffer)v2_1).limit() + ((ByteBuffer)v1).limit()).slice();
            }

            v0.add(v1_1);
        }

        return ((List)v0);
    }
}

