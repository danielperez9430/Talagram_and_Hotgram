package com.google.android.exoplayer2.extractor.ts;

import android.util.Log;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
import java.util.Collections;

public final class H265Reader implements ElementaryStreamReader {
    final class SampleReader {
        private static final int FIRST_SLICE_FLAG_OFFSET = 2;
        private boolean isFirstParameterSet;
        private boolean isFirstSlice;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private boolean writingParameterSets;

        public SampleReader(TrackOutput arg1) {
            super();
            this.output = arg1;
        }

        public void endNalUnit(long arg3, int arg5) {
            if(!this.writingParameterSets || !this.isFirstSlice) {
                if(!this.isFirstParameterSet && !this.isFirstSlice) {
                    return;
                }

                if(this.readingSample) {
                    this.outputSample(arg5 + (((int)(arg3 - this.nalUnitStartPosition))));
                }

                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.readingSample = true;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
            }
            else {
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.writingParameterSets = false;
            }
        }

        private void outputSample(int arg8) {
            this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe, ((int)(this.nalUnitStartPosition - this.samplePosition)), arg8, null);
        }

        public void readNalUnitData(byte[] arg3, int arg4, int arg5) {
            if(this.lookingForFirstSliceFlag) {
                int v0 = arg4 + 2 - this.nalUnitBytesRead;
                if(v0 < arg5) {
                    boolean v3 = (arg3[v0] & 128) != 0 ? true : false;
                    this.isFirstSlice = v3;
                    this.lookingForFirstSliceFlag = false;
                }
                else {
                    this.nalUnitBytesRead += arg5 - arg4;
                }
            }
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstParameterSet = false;
            this.readingSample = false;
            this.writingParameterSets = false;
        }

        public void startNalUnit(long arg2, int arg4, int arg5, long arg6) {
            this.isFirstSlice = false;
            this.isFirstParameterSet = false;
            this.nalUnitTimeUs = arg6;
            this.nalUnitBytesRead = 0;
            this.nalUnitStartPosition = arg2;
            boolean v2 = true;
            if(arg5 >= 32) {
                if(!this.writingParameterSets && (this.readingSample)) {
                    this.outputSample(arg4);
                    this.readingSample = false;
                }

                if(arg5 > 34) {
                    goto label_21;
                }

                this.isFirstParameterSet = this.writingParameterSets ^ 1;
                this.writingParameterSets = true;
            }

        label_21:
            boolean v3 = arg5 < 16 || arg5 > 21 ? false : true;
            this.nalUnitHasKeyframeData = v3;
            if(!this.nalUnitHasKeyframeData) {
                if(arg5 <= 9) {
                }
                else {
                    v2 = false;
                }
            }

            this.lookingForFirstSliceFlag = v2;
        }
    }

    private static final int BLA_W_LP = 16;
    private static final int CRA_NUT = 21;
    private static final int PPS_NUT = 34;
    private static final int PREFIX_SEI_NUT = 39;
    private static final int RASL_R = 9;
    private static final int SPS_NUT = 33;
    private static final int SUFFIX_SEI_NUT = 40;
    private static final String TAG = "H265Reader";
    private static final int VPS_NUT = 32;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final NalUnitTargetBuffer pps;
    private final boolean[] prefixFlags;
    private final NalUnitTargetBuffer prefixSei;
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper;
    private final NalUnitTargetBuffer sps;
    private final NalUnitTargetBuffer suffixSei;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer vps;

    public H265Reader(SeiReader arg3) {
        super();
        this.seiReader = arg3;
        this.prefixFlags = new boolean[3];
        this.vps = new NalUnitTargetBuffer(32, 128);
        this.sps = new NalUnitTargetBuffer(33, 128);
        this.pps = new NalUnitTargetBuffer(34, 128);
        this.prefixSei = new NalUnitTargetBuffer(39, 128);
        this.suffixSei = new NalUnitTargetBuffer(40, 128);
        this.seiWrapper = new ParsableByteArray();
    }

    public void consume(ParsableByteArray arg17) {
        H265Reader v7 = this;
        ParsableByteArray v8 = arg17;
    label_2:
        if(arg17.bytesLeft() > 0) {
            int v0 = arg17.getPosition();
            int v9 = arg17.limit();
            byte[] v10 = v8.data;
            v7.totalBytesWritten += ((long)arg17.bytesLeft());
            v7.output.sampleData(v8, arg17.bytesLeft());
            while(true) {
                if(v0 >= v9) {
                    goto label_2;
                }

                int v11 = NalUnitUtil.findNalUnit(v10, v0, v9, v7.prefixFlags);
                if(v11 == v9) {
                    v7.nalUnitData(v10, v0, v9);
                    return;
                }

                int v12 = NalUnitUtil.getH265NalUnitType(v10, v11);
                int v1 = v11 - v0;
                if(v1 > 0) {
                    v7.nalUnitData(v10, v0, v11);
                }

                int v13 = v9 - v11;
                long v14 = v7.totalBytesWritten - (((long)v13));
                int v4 = v1 < 0 ? -v1 : 0;
                this.endNalUnit(v14, v13, v4, v7.pesTimeUs);
                this.startNalUnit(v14, v13, v12, v7.pesTimeUs);
                v0 = v11 + 3;
            }
        }
    }

    public void createTracks(ExtractorOutput arg3, TrackIdGenerator arg4) {
        arg4.generateNewId();
        this.formatId = arg4.getFormatId();
        this.output = arg3.track(arg4.getTrackId(), 2);
        this.sampleReader = new SampleReader(this.output);
        this.seiReader.createTracks(arg3, arg4);
    }

    private void endNalUnit(long arg3, int arg5, int arg6, long arg7) {
        if(this.hasOutputFormat) {
            this.sampleReader.endNalUnit(arg3, arg5);
        }
        else {
            this.vps.endNalUnit(arg6);
            this.sps.endNalUnit(arg6);
            this.pps.endNalUnit(arg6);
            if((this.vps.isCompleted()) && (this.sps.isCompleted()) && (this.pps.isCompleted())) {
                this.output.format(H265Reader.parseMediaFormat(this.formatId, this.vps, this.sps, this.pps));
                this.hasOutputFormat = true;
            }
        }

        int v4 = 5;
        if(this.prefixSei.endNalUnit(arg6)) {
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(this.prefixSei.nalData, this.prefixSei.nalLength));
            this.seiWrapper.skipBytes(v4);
            this.seiReader.consume(arg7, this.seiWrapper);
        }

        if(this.suffixSei.endNalUnit(arg6)) {
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(this.suffixSei.nalData, this.suffixSei.nalLength));
            this.seiWrapper.skipBytes(v4);
            this.seiReader.consume(arg7, this.seiWrapper);
        }
    }

    private void nalUnitData(byte[] arg2, int arg3, int arg4) {
        if(this.hasOutputFormat) {
            this.sampleReader.readNalUnitData(arg2, arg3, arg4);
        }
        else {
            this.vps.appendToNalUnit(arg2, arg3, arg4);
            this.sps.appendToNalUnit(arg2, arg3, arg4);
            this.pps.appendToNalUnit(arg2, arg3, arg4);
        }

        this.prefixSei.appendToNalUnit(arg2, arg3, arg4);
        this.suffixSei.appendToNalUnit(arg2, arg3, arg4);
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.pesTimeUs = arg1;
    }

    private static Format parseMediaFormat(String arg23, NalUnitTargetBuffer arg24, NalUnitTargetBuffer arg25, NalUnitTargetBuffer arg26) {
        float v21;
        byte[] v3 = new byte[arg24.nalLength + arg25.nalLength + arg26.nalLength];
        int v6 = 0;
        System.arraycopy(arg24.nalData, 0, v3, 0, arg24.nalLength);
        System.arraycopy(arg25.nalData, 0, v3, arg24.nalLength, arg25.nalLength);
        System.arraycopy(arg26.nalData, 0, v3, arg24.nalLength + arg25.nalLength, arg26.nalLength);
        ParsableNalUnitBitArray v0 = new ParsableNalUnitBitArray(arg25.nalData, 0, arg25.nalLength);
        v0.skipBits(44);
        int v1 = 3;
        int v2 = v0.readBits(v1);
        v0.skipBit();
        v0.skipBits(88);
        int v4 = 8;
        v0.skipBits(v4);
        int v5 = 0;
        int v7 = 0;
        while(v5 < v2) {
            if(v0.readBit()) {
                v7 += 89;
            }

            if(v0.readBit()) {
                v7 += 8;
            }

            ++v5;
        }

        v0.skipBits(v7);
        v5 = 2;
        if(v2 > 0) {
            v0.skipBits((8 - v2) * 2);
        }

        v0.readUnsignedExpGolombCodedInt();
        v7 = v0.readUnsignedExpGolombCodedInt();
        if(v7 == v1) {
            v0.skipBit();
        }

        v1 = v0.readUnsignedExpGolombCodedInt();
        int v8 = v0.readUnsignedExpGolombCodedInt();
        if(v0.readBit()) {
            int v9 = v0.readUnsignedExpGolombCodedInt();
            int v11 = v0.readUnsignedExpGolombCodedInt();
            int v12 = v0.readUnsignedExpGolombCodedInt();
            int v13 = v0.readUnsignedExpGolombCodedInt();
            int v14 = v7 == 1 || v7 == v5 ? 2 : 1;
            v7 = v7 == 1 ? 2 : 1;
            v1 -= v14 * (v9 + v11);
            v8 -= v7 * (v12 + v13);
        }

        int v16 = v1;
        int v17 = v8;
        v0.readUnsignedExpGolombCodedInt();
        v0.readUnsignedExpGolombCodedInt();
        v1 = v0.readUnsignedExpGolombCodedInt();
        for(v7 = v0.readBit() ? 0 : v2; v7 <= v2; ++v7) {
            v0.readUnsignedExpGolombCodedInt();
            v0.readUnsignedExpGolombCodedInt();
            v0.readUnsignedExpGolombCodedInt();
        }

        v0.readUnsignedExpGolombCodedInt();
        v0.readUnsignedExpGolombCodedInt();
        v0.readUnsignedExpGolombCodedInt();
        v0.readUnsignedExpGolombCodedInt();
        v0.readUnsignedExpGolombCodedInt();
        v0.readUnsignedExpGolombCodedInt();
        if((v0.readBit()) && (v0.readBit())) {
            H265Reader.skipScalingList(v0);
        }

        v0.skipBits(v5);
        if(v0.readBit()) {
            v0.skipBits(v4);
            v0.readUnsignedExpGolombCodedInt();
            v0.readUnsignedExpGolombCodedInt();
            v0.skipBit();
        }

        H265Reader.skipShortTermRefPicSets(v0);
        if(v0.readBit()) {
            while(v6 < v0.readUnsignedExpGolombCodedInt()) {
                v0.skipBits(v1 + 5);
                ++v6;
            }
        }

        v0.skipBits(v5);
        float v1_1 = 1f;
        if(!v0.readBit() || !v0.readBit()) {
        label_160:
            v21 = 1f;
        }
        else {
            v2 = v0.readBits(v4);
            if(v2 == 255) {
                v4 = v0.readBits(16);
                int v0_1 = v0.readBits(16);
                if(v4 != 0 && v0_1 != 0) {
                    v1_1 = (((float)v4)) / (((float)v0_1));
                }

                v21 = v1_1;
            }
            else {
                if(v2 < NalUnitUtil.ASPECT_RATIO_IDC_VALUES.length) {
                    v21 = NalUnitUtil.ASPECT_RATIO_IDC_VALUES[v2];
                    goto label_161;
                }

                Log.w("H265Reader", "Unexpected aspect_ratio_idc value: " + v2);
                goto label_160;
            }
        }

    label_161:
        return Format.createVideoSampleFormat(arg23, "video/hevc", null, -1, -1, v16, v17, -1f, Collections.singletonList(v3), -1, v21, null);
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0;
    }

    private static void skipScalingList(ParsableNalUnitBitArray arg7) {
        int v4;
        int v1;
        for(v1 = 0; true; ++v1) {
            int v2 = 4;
            if(v1 >= v2) {
                return;
            }

            int v3;
            for(v3 = 0; v3 < 6; v3 += v4) {
                if(!arg7.readBit()) {
                    arg7.readUnsignedExpGolombCodedInt();
                }
                else {
                    v4 = Math.min(64, 1 << (v1 << 1) + v2);
                    if(v1 > 1) {
                        arg7.readSignedExpGolombCodedInt();
                    }

                    int v6;
                    for(v6 = 0; v6 < v4; ++v6) {
                        arg7.readSignedExpGolombCodedInt();
                    }
                }

                v4 = 3;
                if(v1 == v4) {
                }
                else {
                    v4 = 1;
                }
            }
        }
    }

    private static void skipShortTermRefPicSets(ParsableNalUnitBitArray arg8) {
        int v0 = arg8.readUnsignedExpGolombCodedInt();
        int v2 = 0;
        boolean v3 = false;
        int v4 = 0;
        while(v2 < v0) {
            if(v2 != 0) {
                v3 = arg8.readBit();
            }

            if(v3) {
                arg8.skipBit();
                arg8.readUnsignedExpGolombCodedInt();
                int v5;
                for(v5 = 0; v5 <= v4; ++v5) {
                    if(arg8.readBit()) {
                        arg8.skipBit();
                    }
                }
            }
            else {
                v4 = arg8.readUnsignedExpGolombCodedInt();
                v5 = arg8.readUnsignedExpGolombCodedInt();
                int v6 = v4 + v5;
                int v7;
                for(v7 = 0; v7 < v4; ++v7) {
                    arg8.readUnsignedExpGolombCodedInt();
                    arg8.skipBit();
                }

                for(v4 = 0; v4 < v5; ++v4) {
                    arg8.readUnsignedExpGolombCodedInt();
                    arg8.skipBit();
                }

                v4 = v6;
            }

            ++v2;
        }
    }

    private void startNalUnit(long arg9, int arg11, int arg12, long arg13) {
        if(this.hasOutputFormat) {
            this.sampleReader.startNalUnit(arg9, arg11, arg12, arg13);
        }
        else {
            this.vps.startNalUnit(arg12);
            this.sps.startNalUnit(arg12);
            this.pps.startNalUnit(arg12);
        }

        this.prefixSei.startNalUnit(arg12);
        this.suffixSei.startNalUnit(arg12);
    }
}

