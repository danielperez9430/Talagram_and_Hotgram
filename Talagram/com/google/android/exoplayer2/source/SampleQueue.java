package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.Allocation;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;
import java.nio.ByteBuffer;

public final class SampleQueue implements TrackOutput {
    final class AllocationNode {
        public Allocation allocation;
        public final long endPosition;
        public AllocationNode next;
        public final long startPosition;
        public boolean wasInitialized;

        public AllocationNode(long arg3, int arg5) {
            super();
            this.startPosition = arg3;
            this.endPosition = arg3 + (((long)arg5));
        }

        public AllocationNode clear() {
            this.allocation = null;
            AllocationNode v1 = this.next;
            this.next = null;
            return v1;
        }

        public void initialize(Allocation arg1, AllocationNode arg2) {
            this.allocation = arg1;
            this.next = arg2;
            this.wasInitialized = true;
        }

        public int translateOffset(long arg3) {
            return (((int)(arg3 - this.startPosition))) + this.allocation.offset;
        }
    }

    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format arg1);
    }

    public static final int ADVANCE_FAILED = -1;
    private static final int INITIAL_SCRATCH_SIZE = 32;
    private final int allocationLength;
    private final Allocator allocator;
    private Format downstreamFormat;
    private final SampleExtrasHolder extrasHolder;
    private AllocationNode firstAllocationNode;
    private Format lastUnadjustedFormat;
    private final SampleMetadataQueue metadataQueue;
    private boolean pendingFormatAdjustment;
    private boolean pendingSplice;
    private AllocationNode readAllocationNode;
    private long sampleOffsetUs;
    private final ParsableByteArray scratch;
    private long totalBytesWritten;
    private UpstreamFormatChangedListener upstreamFormatChangeListener;
    private AllocationNode writeAllocationNode;

    public SampleQueue(Allocator arg4) {
        super();
        this.allocator = arg4;
        this.allocationLength = arg4.getIndividualAllocationLength();
        this.metadataQueue = new SampleMetadataQueue();
        this.extrasHolder = new SampleExtrasHolder();
        this.scratch = new ParsableByteArray(32);
        this.firstAllocationNode = new AllocationNode(0, this.allocationLength);
        this.readAllocationNode = this.firstAllocationNode;
        this.writeAllocationNode = this.firstAllocationNode;
    }

    private void advanceReadTo(long arg4) {
        while(arg4 >= this.readAllocationNode.endPosition) {
            this.readAllocationNode = this.readAllocationNode.next;
        }
    }

    public int advanceTo(long arg2, boolean arg4, boolean arg5) {
        return this.metadataQueue.advanceTo(arg2, arg4, arg5);
    }

    public int advanceToEnd() {
        return this.metadataQueue.advanceToEnd();
    }

    private void clearAllocationNodes(AllocationNode arg6) {
        if(!arg6.wasInitialized) {
            return;
        }

        Allocation[] v0 = new Allocation[this.writeAllocationNode.wasInitialized + (((int)(this.writeAllocationNode.startPosition - arg6.startPosition))) / this.allocationLength];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = arg6.allocation;
            arg6 = arg6.clear();
        }

        this.allocator.release(v0);
    }

    private void discardDownstreamTo(long arg4) {
        if(arg4 == -1) {
            return;
        }

        while(arg4 >= this.firstAllocationNode.endPosition) {
            this.allocator.release(this.firstAllocationNode.allocation);
            this.firstAllocationNode = this.firstAllocationNode.clear();
        }

        if(this.readAllocationNode.startPosition < this.firstAllocationNode.startPosition) {
            this.readAllocationNode = this.firstAllocationNode;
        }
    }

    public void discardTo(long arg2, boolean arg4, boolean arg5) {
        this.discardDownstreamTo(this.metadataQueue.discardTo(arg2, arg4, arg5));
    }

    public void discardToEnd() {
        this.discardDownstreamTo(this.metadataQueue.discardToEnd());
    }

    public void discardToRead() {
        this.discardDownstreamTo(this.metadataQueue.discardToRead());
    }

    public void discardUpstreamSamples(int arg7) {
        this.totalBytesWritten = this.metadataQueue.discardUpstreamSamples(arg7);
        if(this.totalBytesWritten == 0 || this.totalBytesWritten == this.firstAllocationNode.startPosition) {
            this.clearAllocationNodes(this.firstAllocationNode);
            this.firstAllocationNode = new AllocationNode(this.totalBytesWritten, this.allocationLength);
            this.readAllocationNode = this.firstAllocationNode;
            this.writeAllocationNode = this.firstAllocationNode;
        }
        else {
            AllocationNode v7;
            for(v7 = this.firstAllocationNode; this.totalBytesWritten > v7.endPosition; v7 = v7.next) {
            }

            AllocationNode v0 = v7.next;
            this.clearAllocationNodes(v0);
            v7.next = new AllocationNode(v7.endPosition, this.allocationLength);
            AllocationNode v1 = this.totalBytesWritten == v7.endPosition ? v7.next : v7;
            this.writeAllocationNode = v1;
            if(this.readAllocationNode != v0) {
                return;
            }

            this.readAllocationNode = v7.next;
        }
    }

    public void format(Format arg3) {
        Format v0 = SampleQueue.getAdjustedSampleFormat(arg3, this.sampleOffsetUs);
        boolean v1 = this.metadataQueue.format(v0);
        this.lastUnadjustedFormat = arg3;
        this.pendingFormatAdjustment = false;
        if(this.upstreamFormatChangeListener != null && (v1)) {
            this.upstreamFormatChangeListener.onUpstreamFormatChanged(v0);
        }
    }

    private static Format getAdjustedSampleFormat(Format arg5, long arg6) {
        if(arg5 == null) {
            return null;
        }

        if(arg6 != 0 && arg5.subsampleOffsetUs != 9223372036854775807L) {
            arg5 = arg5.copyWithSubsampleOffsetUs(arg5.subsampleOffsetUs + arg6);
        }

        return arg5;
    }

    public int getFirstIndex() {
        return this.metadataQueue.getFirstIndex();
    }

    public long getFirstTimestampUs() {
        return this.metadataQueue.getFirstTimestampUs();
    }

    public long getLargestQueuedTimestampUs() {
        return this.metadataQueue.getLargestQueuedTimestampUs();
    }

    public int getReadIndex() {
        return this.metadataQueue.getReadIndex();
    }

    public Format getUpstreamFormat() {
        return this.metadataQueue.getUpstreamFormat();
    }

    public int getWriteIndex() {
        return this.metadataQueue.getWriteIndex();
    }

    public boolean hasNextSample() {
        return this.metadataQueue.hasNextSample();
    }

    public int peekSourceId() {
        return this.metadataQueue.peekSourceId();
    }

    private void postAppend(int arg5) {
        this.totalBytesWritten += ((long)arg5);
        if(this.totalBytesWritten == this.writeAllocationNode.endPosition) {
            this.writeAllocationNode = this.writeAllocationNode.next;
        }
    }

    private int preAppend(int arg7) {
        if(!this.writeAllocationNode.wasInitialized) {
            this.writeAllocationNode.initialize(this.allocator.allocate(), new AllocationNode(this.writeAllocationNode.endPosition, this.allocationLength));
        }

        return Math.min(arg7, ((int)(this.writeAllocationNode.endPosition - this.totalBytesWritten)));
    }

    public int read(FormatHolder arg8, DecoderInputBuffer arg9, boolean arg10, boolean arg11, long arg12) {
        switch(this.metadataQueue.read(arg8, arg9, arg10, arg11, this.downstreamFormat, this.extrasHolder)) {
            case -5: {
                goto label_35;
            }
            case -4: {
                goto label_14;
            }
            case -3: {
                return -3;
            }
        }

        throw new IllegalStateException();
    label_35:
        this.downstreamFormat = arg8.format;
        return -5;
    label_14:
        if(!arg9.isEndOfStream()) {
            if(arg9.timeUs < arg12) {
                arg9.addFlag(-2147483648);
            }

            if(arg9.isEncrypted()) {
                this.readEncryptionData(arg9, this.extrasHolder);
            }

            arg9.ensureSpaceForWrite(this.extrasHolder.size);
            this.readData(this.extrasHolder.offset, arg9.data, this.extrasHolder.size);
        }

        return -4;
    }

    private void readData(long arg4, ByteBuffer arg6, int arg7) {
        this.advanceReadTo(arg4);
        while(arg7 > 0) {
            int v0 = Math.min(arg7, ((int)(this.readAllocationNode.endPosition - arg4)));
            arg6.put(this.readAllocationNode.allocation.data, this.readAllocationNode.translateOffset(arg4), v0);
            arg7 -= v0;
            arg4 += ((long)v0);
            if(arg4 != this.readAllocationNode.endPosition) {
                continue;
            }

            this.readAllocationNode = this.readAllocationNode.next;
        }
    }

    private void readData(long arg6, byte[] arg8, int arg9) {
        this.advanceReadTo(arg6);
        long v0 = arg6;
        int v6 = arg9;
        while(v6 > 0) {
            int v7 = Math.min(v6, ((int)(this.readAllocationNode.endPosition - v0)));
            System.arraycopy(this.readAllocationNode.allocation.data, this.readAllocationNode.translateOffset(v0), arg8, arg9 - v6, v7);
            v6 -= v7;
            v0 += ((long)v7);
            if(v0 != this.readAllocationNode.endPosition) {
                continue;
            }

            this.readAllocationNode = this.readAllocationNode.next;
        }
    }

    private void readEncryptionData(DecoderInputBuffer arg19, SampleExtrasHolder arg20) {
        int v10;
        SampleQueue v0 = this;
        DecoderInputBuffer v1 = arg19;
        SampleExtrasHolder v2 = arg20;
        long v3 = v2.offset;
        v0.scratch.reset(1);
        v0.readData(v3, v0.scratch.data, 1);
        ++v3;
        int v7 = 0;
        int v5 = v0.scratch.data[0];
        int v8 = (v5 & 128) != 0 ? 1 : 0;
        v5 &= 127;
        if(v1.cryptoInfo.iv == null) {
            v1.cryptoInfo.iv = new byte[16];
        }

        v0.readData(v3, v1.cryptoInfo.iv, v5);
        v3 += ((long)v5);
        if(v8 != 0) {
            v0.scratch.reset(2);
            v0.readData(v3, v0.scratch.data, 2);
            v3 += 2;
            v10 = v0.scratch.readUnsignedShort();
        }
        else {
            v10 = 1;
        }

        int[] v5_1 = v1.cryptoInfo.numBytesOfClearData;
        if(v5_1 == null || v5_1.length < v10) {
            v5_1 = new int[v10];
        }

        int[] v11 = v5_1;
        v5_1 = v1.cryptoInfo.numBytesOfEncryptedData;
        if(v5_1 == null || v5_1.length < v10) {
            v5_1 = new int[v10];
        }

        int[] v12 = v5_1;
        if(v8 != 0) {
            v5 = v10 * 6;
            v0.scratch.reset(v5);
            v0.readData(v3, v0.scratch.data, v5);
            v3 += ((long)v5);
            v0.scratch.setPosition(0);
            while(v7 < v10) {
                v11[v7] = v0.scratch.readUnsignedShort();
                v12[v7] = v0.scratch.readUnsignedIntToInt();
                ++v7;
            }
        }
        else {
            v11[0] = 0;
            v12[0] = v2.size - (((int)(v3 - v2.offset)));
        }

        v1.cryptoInfo.set(v10, v11, v12, v2.cryptoData.encryptionKey, v1.cryptoInfo.iv, v2.cryptoData.cryptoMode, v2.cryptoData.encryptedBlocks, v2.cryptoData.clearBlocks);
        int v1_1 = ((int)(v3 - v2.offset));
        v2.offset += ((long)v1_1);
        v2.size -= v1_1;
    }

    public void reset() {
        this.reset(false);
    }

    public void reset(boolean arg4) {
        this.metadataQueue.reset(arg4);
        this.clearAllocationNodes(this.firstAllocationNode);
        this.firstAllocationNode = new AllocationNode(0, this.allocationLength);
        this.readAllocationNode = this.firstAllocationNode;
        this.writeAllocationNode = this.firstAllocationNode;
        this.totalBytesWritten = 0;
        this.allocator.trim();
    }

    public void rewind() {
        this.metadataQueue.rewind();
        this.readAllocationNode = this.firstAllocationNode;
    }

    public int sampleData(ExtractorInput arg5, int arg6, boolean arg7) {
        int v5 = arg5.read(this.writeAllocationNode.allocation.data, this.writeAllocationNode.translateOffset(this.totalBytesWritten), this.preAppend(arg6));
        arg6 = -1;
        if(v5 == arg6) {
            if(arg7) {
                return arg6;
            }

            throw new EOFException();
        }

        this.postAppend(v5);
        return v5;
    }

    public void sampleData(ParsableByteArray arg6, int arg7) {
        while(arg7 > 0) {
            int v0 = this.preAppend(arg7);
            arg6.readBytes(this.writeAllocationNode.allocation.data, this.writeAllocationNode.translateOffset(this.totalBytesWritten), v0);
            arg7 -= v0;
            this.postAppend(v0);
        }
    }

    public void sampleMetadata(long arg13, int arg15, int arg16, int arg17, CryptoData arg18) {
        SampleQueue v0 = this;
        long v1 = arg13;
        if(v0.pendingFormatAdjustment) {
            this.format(v0.lastUnadjustedFormat);
        }

        if(v0.pendingSplice) {
            if((arg15 & 1) != 0) {
                if(!v0.metadataQueue.attemptSplice(arg13)) {
                }
                else {
                    v0.pendingSplice = false;
                    goto label_18;
                }
            }

            return;
        }

    label_18:
        v0.metadataQueue.commitSample(v1 + v0.sampleOffsetUs, arg15, v0.totalBytesWritten - (((long)arg16)) - (((long)arg17)), arg16, arg18);
    }

    public boolean setReadPosition(int arg2) {
        return this.metadataQueue.setReadPosition(arg2);
    }

    public void setSampleOffsetUs(long arg4) {
        if(this.sampleOffsetUs != arg4) {
            this.sampleOffsetUs = arg4;
            this.pendingFormatAdjustment = true;
        }
    }

    public void setUpstreamFormatChangeListener(UpstreamFormatChangedListener arg1) {
        this.upstreamFormatChangeListener = arg1;
    }

    public void sourceId(int arg2) {
        this.metadataQueue.sourceId(arg2);
    }

    public void splice() {
        this.pendingSplice = true;
    }
}

