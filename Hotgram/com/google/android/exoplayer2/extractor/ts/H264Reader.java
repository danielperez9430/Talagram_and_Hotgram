package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.NalUnitUtil$PpsData;
import com.google.android.exoplayer2.util.NalUnitUtil$SpsData;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class H264Reader implements ElementaryStreamReader {
    class com.google.android.exoplayer2.extractor.ts.H264Reader$1 {
    }

    final class SampleReader {
        final class SliceHeaderData {
            private static final int SLICE_TYPE_ALL_I = 7;
            private static final int SLICE_TYPE_I = 2;
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            private SpsData spsData;

            SliceHeaderData(com.google.android.exoplayer2.extractor.ts.H264Reader$1 arg1) {
                this();
            }

            private SliceHeaderData() {
                super();
            }

            static boolean access$100(SliceHeaderData arg0, SliceHeaderData arg1) {
                return arg0.isFirstVclNalUnitOfPicture(arg1);
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            private boolean isFirstVclNalUnitOfPicture(SliceHeaderData arg4) {
                boolean v1 = true;
                if(!this.isComplete) {
                label_63:
                    v1 = false;
                }
                else if((arg4.isComplete) && this.frameNum == arg4.frameNum && this.picParameterSetId == arg4.picParameterSetId && this.fieldPicFlag == arg4.fieldPicFlag) {
                    if((this.bottomFieldFlagPresent) && (arg4.bottomFieldFlagPresent) && this.bottomFieldFlag != arg4.bottomFieldFlag) {
                        return v1;
                    }

                    if(this.nalRefIdc != arg4.nalRefIdc) {
                        if(this.nalRefIdc == 0) {
                        }
                        else if(arg4.nalRefIdc != 0) {
                            goto label_28;
                        }

                        return v1;
                    }

                label_28:
                    if(this.spsData.picOrderCountType == 0 && arg4.spsData.picOrderCountType == 0) {
                        if(this.picOrderCntLsb != arg4.picOrderCntLsb) {
                        }
                        else if(this.deltaPicOrderCntBottom == arg4.deltaPicOrderCntBottom) {
                            goto label_40;
                        }

                        return v1;
                    }

                label_40:
                    if(this.spsData.picOrderCountType == 1 && arg4.spsData.picOrderCountType == 1) {
                        if(this.deltaPicOrderCnt0 != arg4.deltaPicOrderCnt0) {
                        }
                        else if(this.deltaPicOrderCnt1 == arg4.deltaPicOrderCnt1) {
                            goto label_52;
                        }

                        return v1;
                    }

                label_52:
                    if(this.idrPicFlag != arg4.idrPicFlag) {
                        return v1;
                    }

                    if(!this.idrPicFlag) {
                        goto label_63;
                    }

                    if(!arg4.idrPicFlag) {
                        goto label_63;
                    }

                    if(this.idrPicId == arg4.idrPicId) {
                        goto label_63;
                    }
                }

                return v1;
            }

            public boolean isISlice() {
                boolean v0;
                if(this.hasSliceType) {
                    if(this.sliceType != 7 && this.sliceType != 2) {
                        goto label_10;
                    }

                    v0 = true;
                }
                else {
                label_10:
                    v0 = false;
                }

                return v0;
            }

            public void setAll(SpsData arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7, boolean arg8, boolean arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
                this.spsData = arg1;
                this.nalRefIdc = arg2;
                this.sliceType = arg3;
                this.frameNum = arg4;
                this.picParameterSetId = arg5;
                this.fieldPicFlag = arg6;
                this.bottomFieldFlagPresent = arg7;
                this.bottomFieldFlag = arg8;
                this.idrPicFlag = arg9;
                this.idrPicId = arg10;
                this.picOrderCntLsb = arg11;
                this.deltaPicOrderCntBottom = arg12;
                this.deltaPicOrderCnt0 = arg13;
                this.deltaPicOrderCnt1 = arg14;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public void setSliceType(int arg1) {
                this.sliceType = arg1;
                this.hasSliceType = true;
            }
        }

        private static final int DEFAULT_BUFFER_SIZE = 128;
        private static final int NAL_UNIT_TYPE_AUD = 9;
        private static final int NAL_UNIT_TYPE_IDR = 5;
        private static final int NAL_UNIT_TYPE_NON_IDR = 1;
        private static final int NAL_UNIT_TYPE_PARTITION_A = 2;
        private final boolean allowNonIdrKeyframes;
        private final ParsableNalUnitBitArray bitArray;
        private byte[] buffer;
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private final SparseArray pps;
        private SliceHeaderData previousSliceHeader;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader;
        private final SparseArray sps;

        public SampleReader(TrackOutput arg1, boolean arg2, boolean arg3) {
            super();
            this.output = arg1;
            this.allowNonIdrKeyframes = arg2;
            this.detectAccessUnits = arg3;
            this.sps = new SparseArray();
            this.pps = new SparseArray();
            this.previousSliceHeader = new SliceHeaderData(null);
            this.sliceHeader = new SliceHeaderData(null);
            this.buffer = new byte[128];
            this.bitArray = new ParsableNalUnitBitArray(this.buffer, 0, 0);
            this.reset();
        }

        public void appendToNalUnit(byte[] arg22, int arg23, int arg24) {
            int v17;
            int v18;
            int v19;
            int v20;
            int v16;
            boolean v13;
            boolean v14;
            boolean v12;
            SampleReader v0 = this;
            int v1 = arg23;
            if(!v0.isFilling) {
                return;
            }

            int v2 = arg24 - v1;
            int v5 = 2;
            if(v0.buffer.length < v0.bufferLength + v2) {
                v0.buffer = Arrays.copyOf(v0.buffer, (v0.bufferLength + v2) * 2);
            }

            System.arraycopy(arg22, v1, v0.buffer, v0.bufferLength, v2);
            v0.bufferLength += v2;
            v0.bitArray.reset(v0.buffer, 0, v0.bufferLength);
            if(!v0.bitArray.canReadBits(8)) {
                return;
            }

            v0.bitArray.skipBit();
            int v8 = v0.bitArray.readBits(v5);
            v2 = 5;
            v0.bitArray.skipBits(v2);
            if(!v0.bitArray.canReadExpGolombCodedNum()) {
                return;
            }

            v0.bitArray.readUnsignedExpGolombCodedInt();
            if(!v0.bitArray.canReadExpGolombCodedNum()) {
                return;
            }

            int v9 = v0.bitArray.readUnsignedExpGolombCodedInt();
            if(!v0.detectAccessUnits) {
                v0.isFilling = false;
                v0.sliceHeader.setSliceType(v9);
                return;
            }

            if(!v0.bitArray.canReadExpGolombCodedNum()) {
                return;
            }

            int v11 = v0.bitArray.readUnsignedExpGolombCodedInt();
            if(v0.pps.indexOfKey(v11) < 0) {
                v0.isFilling = false;
                return;
            }

            Object v1_1 = v0.pps.get(v11);
            Object v7 = v0.sps.get(((PpsData)v1_1).seqParameterSetId);
            if(((SpsData)v7).separateColorPlaneFlag) {
                if(!v0.bitArray.canReadBits(v5)) {
                    return;
                }
                else {
                    v0.bitArray.skipBits(v5);
                }
            }

            if(!v0.bitArray.canReadBits(((SpsData)v7).frameNumLength)) {
                return;
            }

            int v10 = v0.bitArray.readBits(((SpsData)v7).frameNumLength);
            if(((SpsData)v7).frameMbsOnlyFlag) {
                v12 = false;
            label_116:
                v13 = false;
                v14 = false;
            }
            else if(!v0.bitArray.canReadBits(1)) {
                return;
            }
            else {
                boolean v3 = v0.bitArray.readBit();
                if(!v3) {
                    v12 = v3;
                    goto label_116;
                }
                else if(!v0.bitArray.canReadBits(1)) {
                    return;
                }
                else {
                    v12 = v3;
                    v14 = v0.bitArray.readBit();
                    v13 = true;
                }
            }

            boolean v15 = v0.nalUnitType == v2 ? true : false;
            if(!v15) {
                v16 = 0;
            }
            else if(!v0.bitArray.canReadExpGolombCodedNum()) {
                return;
            }
            else {
                v16 = v0.bitArray.readUnsignedExpGolombCodedInt();
            }

            if(((SpsData)v7).picOrderCountType != 0) {
                if(((SpsData)v7).picOrderCountType == 1 && !((SpsData)v7).deltaPicOrderAlwaysZeroFlag) {
                    if(!v0.bitArray.canReadExpGolombCodedNum()) {
                        return;
                    }
                    else {
                        v2 = v0.bitArray.readSignedExpGolombCodedInt();
                        if((((PpsData)v1_1).bottomFieldPicOrderInFramePresentFlag) && !v12) {
                            if(!v0.bitArray.canReadExpGolombCodedNum()) {
                                return;
                            }
                            else {
                                v20 = v0.bitArray.readSignedExpGolombCodedInt();
                                v19 = v2;
                                v17 = 0;
                                v18 = 0;
                                goto label_189;
                            }
                        }

                        v19 = v2;
                        v17 = 0;
                        v18 = 0;
                        goto label_188;
                    }
                }

                v17 = 0;
            label_186:
                v18 = 0;
            label_187:
                v19 = 0;
            label_188:
                v20 = 0;
            }
            else if(!v0.bitArray.canReadBits(((SpsData)v7).picOrderCntLsbLength)) {
                return;
            }
            else {
                v2 = v0.bitArray.readBits(((SpsData)v7).picOrderCntLsbLength);
                if((((PpsData)v1_1).bottomFieldPicOrderInFramePresentFlag) && !v12) {
                    if(!v0.bitArray.canReadExpGolombCodedNum()) {
                        return;
                    }
                    else {
                        v18 = v0.bitArray.readSignedExpGolombCodedInt();
                        v17 = v2;
                        goto label_187;
                    }
                }

                v17 = v2;
                goto label_186;
            }

        label_189:
            v0.sliceHeader.setAll(((SpsData)v7), v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20);
            v0.isFilling = false;
        }

        public void endNalUnit(long arg6, int arg8) {
            int v1 = 0;
            if(this.nalUnitType == 9 || (this.detectAccessUnits) && (SliceHeaderData.access$100(this.sliceHeader, this.previousSliceHeader))) {
                if(this.readingSample) {
                    this.outputSample(arg8 + (((int)(arg6 - this.nalUnitStartPosition))));
                }

                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }

            boolean v6 = this.sampleIsKeyframe;
            if(this.nalUnitType == 5 || (this.allowNonIdrKeyframes) && this.nalUnitType == 1 && (this.sliceHeader.isISlice())) {
                v1 = 1;
            }

            this.sampleIsKeyframe = (((int)v6)) | v1;
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        private void outputSample(int arg8) {
            this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe, ((int)(this.nalUnitStartPosition - this.samplePosition)), arg8, null);
        }

        public void putPps(PpsData arg3) {
            this.pps.append(arg3.picParameterSetId, arg3);
        }

        public void putSps(SpsData arg3) {
            this.sps.append(arg3.seqParameterSetId, arg3);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long arg1, int arg3, long arg4) {
            this.nalUnitType = arg3;
            this.nalUnitTimeUs = arg4;
            this.nalUnitStartPosition = arg1;
            if((this.allowNonIdrKeyframes) && this.nalUnitType == 1 || (this.detectAccessUnits) && (this.nalUnitType == 5 || this.nalUnitType == 1 || this.nalUnitType == 2)) {
                SliceHeaderData v1 = this.previousSliceHeader;
                this.previousSliceHeader = this.sliceHeader;
                this.sliceHeader = v1;
                this.sliceHeader.clear();
                this.bufferLength = 0;
                this.isFilling = true;
            }
        }
    }

    private static final int NAL_UNIT_TYPE_PPS = 8;
    private static final int NAL_UNIT_TYPE_SEI = 6;
    private static final int NAL_UNIT_TYPE_SPS = 7;
    private final boolean allowNonIdrKeyframes;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final NalUnitTargetBuffer pps;
    private final boolean[] prefixFlags;
    private SampleReader sampleReader;
    private final NalUnitTargetBuffer sei;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper;
    private final NalUnitTargetBuffer sps;
    private long totalBytesWritten;

    public H264Reader(SeiReader arg1, boolean arg2, boolean arg3) {
        super();
        this.seiReader = arg1;
        this.allowNonIdrKeyframes = arg2;
        this.detectAccessUnits = arg3;
        this.prefixFlags = new boolean[3];
        this.sps = new NalUnitTargetBuffer(7, 128);
        this.pps = new NalUnitTargetBuffer(8, 128);
        this.sei = new NalUnitTargetBuffer(6, 128);
        this.seiWrapper = new ParsableByteArray();
    }

    public void consume(ParsableByteArray arg15) {
        int v0 = arg15.getPosition();
        int v1 = arg15.limit();
        byte[] v2 = arg15.data;
        this.totalBytesWritten += ((long)arg15.bytesLeft());
        this.output.sampleData(arg15, arg15.bytesLeft());
        while(true) {
            int v15 = NalUnitUtil.findNalUnit(v2, v0, v1, this.prefixFlags);
            if(v15 == v1) {
                break;
            }

            int v6 = NalUnitUtil.getNalUnitType(v2, v15);
            int v3 = v15 - v0;
            if(v3 > 0) {
                this.nalUnitData(v2, v0, v15);
            }

            int v10 = v1 - v15;
            long v4 = this.totalBytesWritten - (((long)v10));
            int v11 = v3 < 0 ? -v3 : 0;
            this.endNalUnit(v4, v10, v11, this.pesTimeUs);
            this.startNalUnit(v4, v6, this.pesTimeUs);
            v0 = v15 + 3;
        }

        this.nalUnitData(v2, v0, v1);
    }

    public void createTracks(ExtractorOutput arg5, TrackIdGenerator arg6) {
        arg6.generateNewId();
        this.formatId = arg6.getFormatId();
        this.output = arg5.track(arg6.getTrackId(), 2);
        this.sampleReader = new SampleReader(this.output, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(arg5, arg6);
    }

    private void endNalUnit(long arg18, int arg20, int arg21, long arg22) {
        NalUnitTargetBuffer v1_1;
        H264Reader v0 = this;
        int v1 = arg21;
        if(!v0.hasOutputFormat || (v0.sampleReader.needsSpsPps())) {
            v0.sps.endNalUnit(v1);
            v0.pps.endNalUnit(v1);
            int v3 = 3;
            if(!v0.hasOutputFormat) {
                if(!v0.sps.isCompleted()) {
                    goto label_94;
                }
                else if(v0.pps.isCompleted()) {
                    ArrayList v12 = new ArrayList();
                    ((List)v12).add(Arrays.copyOf(v0.sps.nalData, v0.sps.nalLength));
                    ((List)v12).add(Arrays.copyOf(v0.pps.nalData, v0.pps.nalLength));
                    SpsData v2 = NalUnitUtil.parseSpsNalUnit(v0.sps.nalData, v3, v0.sps.nalLength);
                    PpsData v3_1 = NalUnitUtil.parsePpsNalUnit(v0.pps.nalData, v3, v0.pps.nalLength);
                    v0.output.format(Format.createVideoSampleFormat(v0.formatId, "video/avc", null, -1, -1, v2.width, v2.height, -1f, ((List)v12), -1, v2.pixelWidthAspectRatio, null));
                    v0.hasOutputFormat = true;
                    v0.sampleReader.putSps(v2);
                    v0.sampleReader.putPps(v3_1);
                    v0.sps.reset();
                    goto label_68;
                }
                else {
                    goto label_94;
                }
            }
            else if(v0.sps.isCompleted()) {
                v0.sampleReader.putSps(NalUnitUtil.parseSpsNalUnit(v0.sps.nalData, v3, v0.sps.nalLength));
                v1_1 = v0.sps;
            }
            else if(v0.pps.isCompleted()) {
                v0.sampleReader.putPps(NalUnitUtil.parsePpsNalUnit(v0.pps.nalData, v3, v0.pps.nalLength));
            label_68:
                v1_1 = v0.pps;
            }
            else {
                goto label_94;
            }

            v1_1.reset();
        }

    label_94:
        if(v0.sei.endNalUnit(arg21)) {
            v0.seiWrapper.reset(v0.sei.nalData, NalUnitUtil.unescapeStream(v0.sei.nalData, v0.sei.nalLength));
            v0.seiWrapper.setPosition(4);
            v0.seiReader.consume(arg22, v0.seiWrapper);
        }

        v0.sampleReader.endNalUnit(arg18, arg20);
    }

    private void nalUnitData(byte[] arg2, int arg3, int arg4) {
        if(!this.hasOutputFormat || (this.sampleReader.needsSpsPps())) {
            this.sps.appendToNalUnit(arg2, arg3, arg4);
            this.pps.appendToNalUnit(arg2, arg3, arg4);
        }

        this.sei.appendToNalUnit(arg2, arg3, arg4);
        this.sampleReader.appendToNalUnit(arg2, arg3, arg4);
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.pesTimeUs = arg1;
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0;
    }

    private void startNalUnit(long arg8, int arg10, long arg11) {
        if(!this.hasOutputFormat || (this.sampleReader.needsSpsPps())) {
            this.sps.startNalUnit(arg10);
            this.pps.startNalUnit(arg10);
        }

        this.sei.startNalUnit(arg10);
        this.sampleReader.startNalUnit(arg8, arg10, arg11);
    }
}

