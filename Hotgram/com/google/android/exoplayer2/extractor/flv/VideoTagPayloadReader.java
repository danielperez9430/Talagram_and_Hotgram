package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.video.AvcConfig;

final class VideoTagPayloadReader extends TagPayloadReader {
    private static final int AVC_PACKET_TYPE_AVC_NALU = 1;
    private static final int AVC_PACKET_TYPE_SEQUENCE_HEADER = 0;
    private static final int VIDEO_CODEC_AVC = 7;
    private static final int VIDEO_FRAME_KEYFRAME = 1;
    private static final int VIDEO_FRAME_VIDEO_INFO = 5;
    private int frameType;
    private boolean hasOutputFormat;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int nalUnitLengthFieldLength;

    public VideoTagPayloadReader(TrackOutput arg2) {
        super(arg2);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
    }

    protected boolean parseHeader(ParsableByteArray arg4) {
        int v4 = arg4.readUnsignedByte();
        int v0 = v4 >> 4 & 15;
        v4 &= 15;
        if(v4 == 7) {
            this.frameType = v0;
            boolean v4_1 = v0 != 5 ? true : false;
            return v4_1;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Video format not supported: ");
        v1.append(v4);
        throw new UnsupportedFormatException(v1.toString());
    }

    protected void parsePayload(ParsableByteArray arg13, long arg14) {
        int v0 = arg13.readUnsignedByte();
        long v4 = arg14 + (((long)arg13.readInt24())) * 1000;
        if(v0 == 0 && !this.hasOutputFormat) {
            ParsableByteArray v0_1 = new ParsableByteArray(new byte[arg13.bytesLeft()]);
            arg13.readBytes(v0_1.data, 0, arg13.bytesLeft());
            AvcConfig v13 = AvcConfig.parse(v0_1);
            this.nalUnitLengthFieldLength = v13.nalUnitLengthFieldLength;
            this.output.format(Format.createVideoSampleFormat(null, "video/avc", null, -1, -1, v13.width, v13.height, -1f, v13.initializationData, -1, v13.pixelWidthAspectRatio, null));
            this.hasOutputFormat = true;
        }
        else if(v0 == 1 && (this.hasOutputFormat)) {
            byte[] v0_2 = this.nalLength.data;
            v0_2[0] = 0;
            v0_2[1] = 0;
            v0_2[2] = 0;
            int v1 = 4;
            v0 = 4 - this.nalUnitLengthFieldLength;
            int v7;
            for(v7 = 0; arg13.bytesLeft() > 0; v7 = v7 + 4 + v2) {
                arg13.readBytes(this.nalLength.data, v0, this.nalUnitLengthFieldLength);
                this.nalLength.setPosition(0);
                int v2 = this.nalLength.readUnsignedIntToInt();
                this.nalStartCode.setPosition(0);
                this.output.sampleData(this.nalStartCode, v1);
                this.output.sampleData(arg13, v2);
            }

            TrackOutput v3 = this.output;
            int v6 = this.frameType == 1 ? 1 : 0;
            v3.sampleMetadata(v4, v6, v7, 0, null);
        }
    }

    public void seek() {
    }
}

