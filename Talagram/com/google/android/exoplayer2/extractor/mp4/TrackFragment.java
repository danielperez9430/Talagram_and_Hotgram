package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;

final class TrackFragment {
    public long atomPosition;
    public long auxiliaryDataPosition;
    public long dataPosition;
    public boolean definesEncryptionData;
    public DefaultSampleValues header;
    public long nextFragmentDecodeTime;
    public int[] sampleCompositionTimeOffsetTable;
    public int sampleCount;
    public long[] sampleDecodingTimeTable;
    public ParsableByteArray sampleEncryptionData;
    public int sampleEncryptionDataLength;
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable;
    public boolean[] sampleIsSyncFrameTable;
    public int[] sampleSizeTable;
    public TrackEncryptionBox trackEncryptionBox;
    public int trunCount;
    public long[] trunDataPosition;
    public int[] trunLength;

    TrackFragment() {
        super();
    }

    public void fillEncryptionData(ExtractorInput arg4) {
        arg4.readFully(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public void fillEncryptionData(ParsableByteArray arg4) {
        arg4.readBytes(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public long getSamplePresentationTime(int arg6) {
        return this.sampleDecodingTimeTable[arg6] + (((long)this.sampleCompositionTimeOffsetTable[arg6]));
    }

    public void initEncryptionData(int arg2) {
        if(this.sampleEncryptionData == null || this.sampleEncryptionData.limit() < arg2) {
            this.sampleEncryptionData = new ParsableByteArray(arg2);
        }

        this.sampleEncryptionDataLength = arg2;
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }

    public void initTables(int arg2, int arg3) {
        this.trunCount = arg2;
        this.sampleCount = arg3;
        if(this.trunLength == null || this.trunLength.length < arg2) {
            this.trunDataPosition = new long[arg2];
            this.trunLength = new int[arg2];
        }

        if(this.sampleSizeTable == null || this.sampleSizeTable.length < arg3) {
            arg3 = arg3 * 125 / 100;
            this.sampleSizeTable = new int[arg3];
            this.sampleCompositionTimeOffsetTable = new int[arg3];
            this.sampleDecodingTimeTable = new long[arg3];
            this.sampleIsSyncFrameTable = new boolean[arg3];
            this.sampleHasSubsampleEncryptionTable = new boolean[arg3];
        }
    }

    public void reset() {
        this.trunCount = 0;
        this.nextFragmentDecodeTime = 0;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
        this.trackEncryptionBox = null;
    }
}

