package com.googlecode.mp4parser.authoring.samples;

import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.SampleSizeBox;
import com.coremedia.iso.boxes.TrackBox;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.util.CastUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class DefaultMp4SampleList extends AbstractList {
    private static final long MAX_MAP_SIZE = 268435456;
    ByteBuffer[][] cache;
    int[] chunkNumsStartSampleNum;
    long[] chunkOffsets;
    long[] chunkSizes;
    int lastChunk;
    long[][] sampleOffsetsWithinChunks;
    SampleSizeBox ssb;
    Container topLevel;
    TrackBox trackBox;

    public DefaultMp4SampleList(long arg21, Container arg23) {
        // Method was not decompiled
    }

    public Sample get(int arg23) {
        ByteBuffer v5;
        long v20;
        long v18;
        Object[] v1_3;
        DefaultMp4SampleList v8 = this;
        int v0 = arg23;
        if((((long)v0)) < v8.ssb.getSampleCount()) {
            int v1 = this.getChunkForSample(arg23);
            int v2 = v8.chunkNumsStartSampleNum[v1] - 1;
            long v4 = ((long)v1);
            long v6 = v8.chunkOffsets[CastUtils.l2i(v4)];
            long[] v3 = v8.sampleOffsetsWithinChunks[CastUtils.l2i(v4)];
            long v9 = v3[v0 - v2];
            ByteBuffer[] v1_1 = v8.cache[CastUtils.l2i(v4)];
            if(v1_1 == null) {
                ArrayList v1_2 = new ArrayList();
                long v13 = 0;
                int v12 = 0;
                try {
                    while(true) {
                    label_28:
                        if(v12 >= v3.length) {
                            ((List)v1_2).add(v8.topLevel.getByteBuffer(v6 + v13, -v13 + v3[v3.length - 1] + v8.ssb.getSampleSizeAtIndex(v2 + v3.length - 1)));
                            v1_3 = ((List)v1_2).toArray(new ByteBuffer[((List)v1_2).size()]);
                            v8.cache[CastUtils.l2i(v4)] = v1_3;
                        }
                        else {
                            v18 = v4;
                            if(v3[v12] + v8.ssb.getSampleSizeAtIndex(v12 + v2) - v13 > 268435456) {
                                v20 = v9;
                                ((List)v1_2).add(v8.topLevel.getByteBuffer(v6 + v13, v3[v12] - v13));
                                v13 = v3[v12];
                            }
                            else {
                                break;
                            }

                            goto label_78;
                        }

                        goto label_87;
                    }
                }
                catch(IOException v0_1) {
                    throw new IndexOutOfBoundsException(v0_1.getMessage());
                }

                v20 = v9;
            label_78:
                ++v12;
                v4 = v18;
                v9 = v20;
                goto label_28;
            }

        label_87:
            ByteBuffer v2_1 = null;
            int v3_1 = ((ByteBuffer[])v1_3).length;
            v6 = v9;
            int v4_1 = 0;
            while(true) {
                if(v4_1 < v3_1) {
                    v5 = ((ByteBuffer[])v1_3)[v4_1];
                    if(v6 >= (((long)v5.limit()))) {
                        v6 -= ((long)v5.limit());
                        ++v4_1;
                        continue;
                    }
                }
                else {
                    break;
                }

                goto label_99;
            }

            v5 = v2_1;
        label_99:
            return new Sample(v8.ssb.getSampleSizeAtIndex(v0), v5, v6) {
                public ByteBuffer asByteBuffer() {
                    return this.val$finalCorrectPartOfChunk.position(CastUtils.l2i(this.val$finalOffsetWithInChunk)).slice().limit(CastUtils.l2i(this.val$sampleSize));
                }

                public long getSize() {
                    return this.val$sampleSize;
                }

                public String toString() {
                    StringBuilder v0 = new StringBuilder("DefaultMp4Sample(size:");
                    v0.append(this.val$sampleSize);
                    v0.append(")");
                    return v0.toString();
                }

                public void writeTo(WritableByteChannel arg2) {
                    arg2.write(this.asByteBuffer());
                }
            };
        }

        throw new IndexOutOfBoundsException();
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    int getChunkForSample(int arg3) {
        __monitor_enter(this);
        ++arg3;
        try {
            if(arg3 >= this.chunkNumsStartSampleNum[this.lastChunk] && arg3 < this.chunkNumsStartSampleNum[this.lastChunk + 1]) {
                arg3 = this.lastChunk;
                goto label_12;
            }

            goto label_14;
        }
        catch(Throwable v3) {
            goto label_46;
        }

    label_12:
        __monitor_exit(this);
        return arg3;
        try {
        label_14:
            if(arg3 >= this.chunkNumsStartSampleNum[this.lastChunk]) {
                goto label_32;
            }

            this.lastChunk = 0;
            while(this.chunkNumsStartSampleNum[this.lastChunk + 1] <= arg3) {
                ++this.lastChunk;
            }

            arg3 = this.lastChunk;
        }
        catch(Throwable v3) {
            goto label_46;
        }

        __monitor_exit(this);
        return arg3;
        try {
        label_32:
            int v0;
            for(v0 = this.lastChunk; true; v0 = this.lastChunk) {
                this.lastChunk = v0 + 1;
                if(this.chunkNumsStartSampleNum[this.lastChunk + 1] > arg3) {
                    break;
                }
            }

            arg3 = this.lastChunk;
        }
        catch(Throwable v3) {
            goto label_46;
        }

        __monitor_exit(this);
        return arg3;
    label_46:
        __monitor_exit(this);
        throw v3;
    }

    public int size() {
        return CastUtils.l2i(this.trackBox.getSampleTableBox().getSampleSizeBox().getSampleCount());
    }
}

