package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.FlacStreamInfo;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.Collections;

final class FlacReader extends StreamReader {
    class FlacOggSeeker implements SeekMap, OggSeeker {
        private static final int METADATA_LENGTH_OFFSET = 1;
        private static final int SEEK_POINT_SIZE = 18;
        private long firstFrameOffset;
        private long pendingSeekGranule;
        private long[] seekPointGranules;
        private long[] seekPointOffsets;

        public FlacOggSeeker(FlacReader arg3) {
            FlacReader.this = arg3;
            super();
            this.firstFrameOffset = -1;
            this.pendingSeekGranule = -1;
        }

        public SeekMap createSeekMap() {
            return this;
        }

        public long getDurationUs() {
            return FlacReader.this.streamInfo.durationUs();
        }

        public SeekPoints getSeekPoints(long arg10) {
            int v0 = Util.binarySearchFloor(this.seekPointGranules, FlacReader.this.convertTimeToGranule(arg10), true, true);
            long v1 = FlacReader.this.convertGranuleToTime(this.seekPointGranules[v0]);
            SeekPoint v6 = new SeekPoint(v1, this.firstFrameOffset + this.seekPointOffsets[v0]);
            if(v1 < arg10) {
                if(v0 == this.seekPointGranules.length - 1) {
                }
                else {
                    ++v0;
                    return new SeekPoints(v6, new SeekPoint(FlacReader.this.convertGranuleToTime(this.seekPointGranules[v0]), this.firstFrameOffset + this.seekPointOffsets[v0]));
                }
            }

            return new SeekPoints(v6);
        }

        public boolean isSeekable() {
            return 1;
        }

        public void parseSeekTable(ParsableByteArray arg6) {
            arg6.skipBytes(1);
            int v0 = arg6.readUnsignedInt24() / 18;
            this.seekPointGranules = new long[v0];
            this.seekPointOffsets = new long[v0];
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.seekPointGranules[v1] = arg6.readLong();
                this.seekPointOffsets[v1] = arg6.readLong();
                arg6.skipBytes(2);
            }
        }

        public long read(ExtractorInput arg7) {
            // Method was not decompiled
        }

        public void setFirstFrameOffset(long arg1) {
            this.firstFrameOffset = arg1;
        }

        public long startSeek(long arg3) {
            arg3 = FlacReader.this.convertTimeToGranule(arg3);
            this.pendingSeekGranule = this.seekPointGranules[Util.binarySearchFloor(this.seekPointGranules, arg3, true, true)];
            return arg3;
        }
    }

    private static final byte AUDIO_PACKET_TYPE = -1;
    private static final int FRAME_HEADER_SAMPLE_NUMBER_OFFSET = 4;
    private static final byte SEEKTABLE_PACKET_TYPE = 3;
    private FlacOggSeeker flacOggSeeker;
    private FlacStreamInfo streamInfo;

    FlacReader() {
        super();
    }

    static FlacStreamInfo access$000(FlacReader arg0) {
        return arg0.streamInfo;
    }

    private int getFlacFrameBlockSize(ParsableByteArray arg4) {
        int v1 = 2;
        int v2 = 4;
        int v0 = (arg4.data[v1] & 255) >> v2;
        switch(v0) {
            case 1: {
                return 192;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                goto label_24;
            }
            case 6: 
            case 7: {
                goto label_13;
            }
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                goto label_9;
            }
        }

        return -1;
    label_9:
        int v4 = 256;
        v0 += -8;
        goto label_11;
    label_13:
        arg4.skipBytes(v2);
        arg4.readUtf8EncodedLong();
        v0 = v0 == 6 ? arg4.readUnsignedByte() : arg4.readUnsignedShort();
        arg4.setPosition(0);
        return v0 + 1;
    label_24:
        v4 = 576;
        v0 -= v1;
    label_11:
        return v4 << v0;
    }

    private static boolean isAudioPacket(byte[] arg2) {
        boolean v0 = false;
        if(arg2[0] == -1) {
            v0 = true;
        }

        return v0;
    }

    protected long preparePayload(ParsableByteArray arg3) {
        if(!FlacReader.isAudioPacket(arg3.data)) {
            return -1;
        }

        return ((long)this.getFlacFrameBlockSize(arg3));
    }

    protected boolean readHeaders(ParsableByteArray arg12, long arg13, SetupData arg15) {
        byte[] v0 = arg12.data;
        if(this.streamInfo == null) {
            this.streamInfo = new FlacStreamInfo(v0, 17);
            byte[] v12 = Arrays.copyOfRange(v0, 9, arg12.limit());
            v12[4] = -128;
            arg15.format = Format.createAudioSampleFormat(null, "audio/flac", null, -1, this.streamInfo.bitRate(), this.streamInfo.channels, this.streamInfo.sampleRate, Collections.singletonList(v12), null, 0, null);
        }
        else if((v0[0] & 127) == 3) {
            this.flacOggSeeker = new FlacOggSeeker(this);
            this.flacOggSeeker.parseSeekTable(arg12);
        }
        else if(FlacReader.isAudioPacket(v0)) {
            if(this.flacOggSeeker != null) {
                this.flacOggSeeker.setFirstFrameOffset(arg13);
                arg15.oggSeeker = this.flacOggSeeker;
            }

            return 0;
        }

        return 1;
    }

    protected void reset(boolean arg1) {
        super.reset(arg1);
        if(arg1) {
            this.streamInfo = null;
            this.flacOggSeeker = null;
        }
    }

    public static boolean verifyBitstreamType(ParsableByteArray arg4) {
        boolean v4 = arg4.bytesLeft() < 5 || arg4.readUnsignedByte() != 127 || arg4.readUnsignedInt() != 1179402563 ? false : true;
        return v4;
    }
}

