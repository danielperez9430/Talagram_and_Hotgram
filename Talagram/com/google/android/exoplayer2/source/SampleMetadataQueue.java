package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

final class SampleMetadataQueue {
    public final class SampleExtrasHolder {
        public CryptoData cryptoData;
        public long offset;
        public int size;

        public SampleExtrasHolder() {
            super();
        }
    }

    private static final int SAMPLE_CAPACITY_INCREMENT = 1000;
    private int absoluteFirstIndex;
    private int capacity;
    private CryptoData[] cryptoDatas;
    private int[] flags;
    private Format[] formats;
    private long largestDiscardedTimestampUs;
    private long largestQueuedTimestampUs;
    private int length;
    private long[] offsets;
    private int readPosition;
    private int relativeFirstIndex;
    private int[] sizes;
    private int[] sourceIds;
    private long[] timesUs;
    private Format upstreamFormat;
    private boolean upstreamFormatRequired;
    private boolean upstreamKeyframeRequired;
    private int upstreamSourceId;

    public SampleMetadataQueue() {
        super();
        this.capacity = 1000;
        this.sourceIds = new int[this.capacity];
        this.offsets = new long[this.capacity];
        this.timesUs = new long[this.capacity];
        this.flags = new int[this.capacity];
        this.sizes = new int[this.capacity];
        this.cryptoDatas = new CryptoData[this.capacity];
        this.formats = new Format[this.capacity];
        this.largestDiscardedTimestampUs = -9223372036854775808L;
        this.largestQueuedTimestampUs = -9223372036854775808L;
        this.upstreamFormatRequired = true;
        this.upstreamKeyframeRequired = true;
    }

    public int advanceTo(long arg9, boolean arg11, boolean arg12) {
        int v9_1;
        int v7;
        __monitor_enter(this);
        try {
            int v2 = this.getRelativeIndex(this.readPosition);
            v7 = -1;
            if((this.hasNextSample()) && arg9 >= this.timesUs[v2]) {
                if(arg9 > this.largestQueuedTimestampUs && !arg12) {
                    goto label_28;
                }

                v9_1 = this.findSampleBefore(v2, this.length - this.readPosition, arg9, arg11);
                if(v9_1 != v7) {
                    goto label_23;
                }

                goto label_21;
            }

            goto label_28;
        }
        catch(Throwable v9) {
            goto label_31;
        }

    label_21:
        __monitor_exit(this);
        return v7;
        try {
        label_23:
            this.readPosition += v9_1;
        }
        catch(Throwable v9) {
        label_31:
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
        return v9_1;
    label_28:
        __monitor_exit(this);
        return v7;
    }

    public int advanceToEnd() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.length - this.readPosition;
            this.readPosition = this.length;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public boolean attemptSplice(long arg8) {
        boolean v1;
        __monitor_enter(this);
        try {
            v1 = false;
            if(this.length != 0) {
                goto label_10;
            }

            if(arg8 > this.largestDiscardedTimestampUs) {
                goto label_7;
            }

            goto label_8;
        }
        catch(Throwable v8) {
            goto label_39;
        }

    label_7:
        v1 = true;
    label_8:
        __monitor_exit(this);
        return v1;
        try {
        label_10:
            if(Math.max(this.largestDiscardedTimestampUs, this.getLargestTimestamp(this.readPosition)) < arg8) {
                goto label_17;
            }
        }
        catch(Throwable v8) {
            goto label_39;
        }

        __monitor_exit(this);
        return 0;
        try {
        label_17:
            int v0 = this.length;
            int v1_1;
            for(v1_1 = this.getRelativeIndex(this.length - 1); v0 > this.readPosition; v1_1 = this.capacity - 1) {
                if(this.timesUs[v1_1] < arg8) {
                    break;
                }

                --v0;
                --v1_1;
                if(v1_1 != -1) {
                    continue;
                }
            }

            this.discardUpstreamSamples(this.absoluteFirstIndex + v0);
        }
        catch(Throwable v8) {
            goto label_39;
        }

        __monitor_exit(this);
        return 1;
    label_39:
        __monitor_exit(this);
        throw v8;
    }

    public void commitSample(long arg6, int arg8, long arg9, int arg11, CryptoData arg12) {
        __monitor_enter(this);
        try {
            if(this.upstreamKeyframeRequired) {
                goto label_4;
            }

            goto label_9;
        }
        catch(Throwable v6) {
            goto label_99;
        }

    label_4:
        if((arg8 & 1) == 0) {
            __monitor_exit(this);
            return;
        }
        else {
            try {
                this.upstreamKeyframeRequired = false;
            label_9:
                Assertions.checkState(this.upstreamFormatRequired ^ 1);
                this.commitSampleTimestamp(arg6);
                int v0 = this.getRelativeIndex(this.length);
                this.timesUs[v0] = arg6;
                this.offsets[v0] = arg9;
                this.sizes[v0] = arg11;
                this.flags[v0] = arg8;
                this.cryptoDatas[v0] = arg12;
                this.formats[v0] = this.upstreamFormat;
                this.sourceIds[v0] = this.upstreamSourceId;
                ++this.length;
                if(this.length == this.capacity) {
                    int v6_1 = this.capacity + 1000;
                    int[] v7 = new int[v6_1];
                    long[] v8 = new long[v6_1];
                    long[] v9 = new long[v6_1];
                    int[] v10 = new int[v6_1];
                    int[] v11 = new int[v6_1];
                    CryptoData[] v12 = new CryptoData[v6_1];
                    Format[] v0_1 = new Format[v6_1];
                    int v2 = this.capacity - this.relativeFirstIndex;
                    System.arraycopy(this.offsets, this.relativeFirstIndex, v8, 0, v2);
                    System.arraycopy(this.timesUs, this.relativeFirstIndex, v9, 0, v2);
                    System.arraycopy(this.flags, this.relativeFirstIndex, v10, 0, v2);
                    System.arraycopy(this.sizes, this.relativeFirstIndex, v11, 0, v2);
                    System.arraycopy(this.cryptoDatas, this.relativeFirstIndex, v12, 0, v2);
                    System.arraycopy(this.formats, this.relativeFirstIndex, v0_1, 0, v2);
                    System.arraycopy(this.sourceIds, this.relativeFirstIndex, v7, 0, v2);
                    int v3 = this.relativeFirstIndex;
                    System.arraycopy(this.offsets, 0, v8, v2, v3);
                    System.arraycopy(this.timesUs, 0, v9, v2, v3);
                    System.arraycopy(this.flags, 0, v10, v2, v3);
                    System.arraycopy(this.sizes, 0, v11, v2, v3);
                    System.arraycopy(this.cryptoDatas, 0, v12, v2, v3);
                    System.arraycopy(this.formats, 0, v0_1, v2, v3);
                    System.arraycopy(this.sourceIds, 0, v7, v2, v3);
                    this.offsets = v8;
                    this.timesUs = v9;
                    this.flags = v10;
                    this.sizes = v11;
                    this.cryptoDatas = v12;
                    this.formats = v0_1;
                    this.sourceIds = v7;
                    this.relativeFirstIndex = 0;
                    this.length = this.capacity;
                    this.capacity = v6_1;
                }

                goto label_96;
            }
            catch(Throwable v6) {
            label_99:
                __monitor_exit(this);
                throw v6;
            }
        }

        goto label_9;
    label_96:
        __monitor_exit(this);
    }

    public void commitSampleTimestamp(long arg3) {
        __monitor_enter(this);
        try {
            this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, arg3);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    private long discardSamples(int arg6) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, this.getLargestTimestamp(arg6));
        this.length -= arg6;
        this.absoluteFirstIndex += arg6;
        this.relativeFirstIndex += arg6;
        if(this.relativeFirstIndex >= this.capacity) {
            this.relativeFirstIndex -= this.capacity;
        }

        this.readPosition -= arg6;
        if(this.readPosition < 0) {
            this.readPosition = 0;
        }

        if(this.length == 0) {
            arg6 = this.relativeFirstIndex == 0 ? this.capacity : this.relativeFirstIndex;
            --arg6;
            return this.offsets[arg6] + (((long)this.sizes[arg6]));
        }

        return this.offsets[this.relativeFirstIndex];
    }

    public long discardTo(long arg10, boolean arg12, boolean arg13) {
        int v10_1;
        long v1;
        __monitor_enter(this);
        try {
            v1 = -1;
            if(this.length != 0) {
                if(arg10 < this.timesUs[this.relativeFirstIndex]) {
                }
                else {
                    int v13 = !arg13 || this.readPosition == this.length ? this.length : this.readPosition + 1;
                    v10_1 = this.findSampleBefore(this.relativeFirstIndex, v13, arg10, arg12);
                    if(v10_1 != -1) {
                        goto label_27;
                    }

                    goto label_25;
                }
            }

            goto label_30;
        }
        catch(Throwable v10) {
            goto label_33;
        }

    label_25:
        __monitor_exit(this);
        return v1;
        try {
        label_27:
            arg10 = this.discardSamples(v10_1);
        }
        catch(Throwable v10) {
        label_33:
            __monitor_exit(this);
            throw v10;
        }

        __monitor_exit(this);
        return arg10;
    label_30:
        __monitor_exit(this);
        return v1;
    }

    public long discardToEnd() {
        long v0_1;
        __monitor_enter(this);
        try {
            if(this.length != 0) {
                goto label_6;
            }
        }
        catch(Throwable v0) {
            goto label_11;
        }

        __monitor_exit(this);
        return -1;
        try {
        label_6:
            v0_1 = this.discardSamples(this.length);
        }
        catch(Throwable v0) {
        label_11:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public long discardToRead() {
        long v0_1;
        __monitor_enter(this);
        try {
            if(this.readPosition != 0) {
                goto label_6;
            }
        }
        catch(Throwable v0) {
            goto label_11;
        }

        __monitor_exit(this);
        return -1;
        try {
        label_6:
            v0_1 = this.discardSamples(this.readPosition);
        }
        catch(Throwable v0) {
        label_11:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public long discardUpstreamSamples(int arg6) {
        int v0 = this.getWriteIndex() - arg6;
        boolean v1 = v0 < 0 || v0 > this.length - this.readPosition ? false : true;
        Assertions.checkArgument(v1);
        this.length -= v0;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, this.getLargestTimestamp(this.length));
        if(this.length == 0) {
            return 0;
        }

        arg6 = this.getRelativeIndex(this.length - 1);
        return this.offsets[arg6] + (((long)this.sizes[arg6]));
    }

    private int findSampleBefore(int arg7, int arg8, long arg9, boolean arg11) {
        int v1 = arg7;
        arg7 = 0;
        int v2 = -1;
        while(arg7 < arg8) {
            if(this.timesUs[v1] > arg9) {
                return v2;
            }

            if(!arg11 || (this.flags[v1] & 1) != 0) {
                v2 = arg7;
            }

            ++v1;
            if(v1 == this.capacity) {
                v1 = 0;
            }

            ++arg7;
        }

        return v2;
    }

    public boolean format(Format arg4) {
        __monitor_enter(this);
        if(arg4 == null) {
            try {
                this.upstreamFormatRequired = true;
            }
            catch(Throwable v4) {
                goto label_18;
            }

            __monitor_exit(this);
            return 0;
        }

        try {
            this.upstreamFormatRequired = false;
            if(!Util.areEqual(arg4, this.upstreamFormat)) {
                goto label_15;
            }
        }
        catch(Throwable v4) {
            goto label_18;
        }

        __monitor_exit(this);
        return 0;
        try {
        label_15:
            this.upstreamFormat = arg4;
        }
        catch(Throwable v4) {
        label_18:
            __monitor_exit(this);
            throw v4;
        }

        __monitor_exit(this);
        return 1;
    }

    public int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public long getFirstTimestampUs() {
        long v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.length == 0 ? -9223372036854775808L : this.timesUs[this.relativeFirstIndex];
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public long getLargestQueuedTimestampUs() {
        long v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.largestQueuedTimestampUs;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    private long getLargestTimestamp(int arg8) {
        long v0 = -9223372036854775808L;
        if(arg8 == 0) {
            return v0;
        }

        int v2 = this.getRelativeIndex(arg8 - 1);
        int v3 = 0;
        while(v3 < arg8) {
            v0 = Math.max(v0, this.timesUs[v2]);
            if((this.flags[v2] & 1) != 0) {
            }
            else {
                --v2;
                if(v2 == -1) {
                    v2 = this.capacity - 1;
                }

                ++v3;
                continue;
            }

            return v0;
        }

        return v0;
    }

    public int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    private int getRelativeIndex(int arg2) {
        int v0 = this.relativeFirstIndex + arg2;
        if(v0 < this.capacity) {
        }
        else {
            v0 -= this.capacity;
        }

        return v0;
    }

    public Format getUpstreamFormat() {
        Format v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.upstreamFormatRequired ? null : this.upstreamFormat;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public boolean hasNextSample() {
        __monitor_enter(this);
        try {
            if(this.readPosition == this.length) {
                goto label_6;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        boolean v0_1 = true;
        goto label_7;
    label_6:
        v0_1 = false;
    label_7:
        __monitor_exit(this);
        return v0_1;
    }

    public int peekSourceId() {
        int v0 = this.getRelativeIndex(this.readPosition);
        return this.hasNextSample() ? this.sourceIds[v0] : this.upstreamSourceId;
    }

    public int read(FormatHolder arg5, DecoderInputBuffer arg6, boolean arg7, boolean arg8, Format arg9, SampleExtrasHolder arg10) {
        int v8;
        int v3;
        int v2;
        int v1;
        __monitor_enter(this);
        try {
            v1 = -5;
            v2 = -3;
            v3 = -4;
            if(this.hasNextSample()) {
                goto label_22;
            }

            if(!arg8) {
                goto label_11;
            }

            arg6.setFlags(4);
        }
        catch(Throwable v5) {
            goto label_59;
        }

        __monitor_exit(this);
        return v3;
        try {
        label_11:
            if(this.upstreamFormat != null) {
                if(!arg7 && this.upstreamFormat == arg9) {
                    goto label_20;
                }

                arg5.format = this.upstreamFormat;
                goto label_18;
            }

            goto label_20;
        }
        catch(Throwable v5) {
            goto label_59;
        }

    label_18:
        __monitor_exit(this);
        return v1;
    label_20:
        __monitor_exit(this);
        return v2;
        try {
        label_22:
            v8 = this.getRelativeIndex(this.readPosition);
            if(!arg7) {
                if(this.formats[v8] != arg9) {
                }
                else if(arg6.isFlagsOnly()) {
                    goto label_31;
                }
                else {
                    goto label_33;
                }
            }

            goto label_53;
        }
        catch(Throwable v5) {
            goto label_59;
        }

    label_31:
        __monitor_exit(this);
        return v2;
        try {
        label_33:
            arg6.timeUs = this.timesUs[v8];
            arg6.setFlags(this.flags[v8]);
            arg10.size = this.sizes[v8];
            arg10.offset = this.offsets[v8];
            arg10.cryptoData = this.cryptoDatas[v8];
            ++this.readPosition;
        }
        catch(Throwable v5) {
            goto label_59;
        }

        __monitor_exit(this);
        return v3;
        try {
        label_53:
            arg5.format = this.formats[v8];
        }
        catch(Throwable v5) {
        label_59:
            __monitor_exit(this);
            throw v5;
        }

        __monitor_exit(this);
        return v1;
    }

    public void reset(boolean arg4) {
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.largestDiscardedTimestampUs = -9223372036854775808L;
        this.largestQueuedTimestampUs = -9223372036854775808L;
        if(arg4) {
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    public void rewind() {
        __monitor_enter(this);
        try {
            this.readPosition = 0;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public boolean setReadPosition(int arg3) {
        boolean v3_1;
        __monitor_enter(this);
        try {
            if(this.absoluteFirstIndex > arg3) {
                goto label_13;
            }
            else if(arg3 <= this.absoluteFirstIndex + this.length) {
                this.readPosition = arg3 - this.absoluteFirstIndex;
                v3_1 = true;
            }
            else {
                goto label_13;
            }

            goto label_11;
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

    label_13:
        v3_1 = false;
    label_11:
        __monitor_exit(this);
        return v3_1;
    }

    public void sourceId(int arg1) {
        this.upstreamSourceId = arg1;
    }
}

