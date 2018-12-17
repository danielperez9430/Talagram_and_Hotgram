package com.google.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;

public final class H262Reader implements ElementaryStreamReader {
    final class CsdBuffer {
        private static final byte[] START_CODE;
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        static {
            CsdBuffer.START_CODE = new byte[]{0, 0, 1};
        }

        public CsdBuffer(int arg1) {
            super();
            this.data = new byte[arg1];
        }

        public void onData(byte[] arg3, int arg4, int arg5) {
            if(!this.isFilling) {
                return;
            }

            arg5 -= arg4;
            if(this.data.length < this.length + arg5) {
                this.data = Arrays.copyOf(this.data, (this.length + arg5) * 2);
            }

            System.arraycopy(arg3, arg4, this.data, this.length, arg5);
            this.length += arg5;
        }

        public boolean onStartCode(int arg4, int arg5) {
            if(this.isFilling) {
                this.length -= arg5;
                if(this.sequenceExtensionPosition == 0 && arg4 == 181) {
                    this.sequenceExtensionPosition = this.length;
                    goto label_19;
                }

                this.isFilling = false;
                return 1;
            }
            else {
                if(arg4 != 179) {
                    goto label_19;
                }

                this.isFilling = true;
            }

        label_19:
            this.onData(CsdBuffer.START_CODE, 0, CsdBuffer.START_CODE.length);
            return 0;
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }
    }

    private static final double[] FRAME_RATE_VALUES = null;
    private static final int START_EXTENSION = 181;
    private static final int START_GROUP = 184;
    private static final int START_PICTURE = 0;
    private static final int START_SEQUENCE_HEADER = 179;
    private static final int START_USER_DATA = 178;
    private final CsdBuffer csdBuffer;
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer userData;
    private final ParsableByteArray userDataParsable;
    private final UserDataReader userDataReader;

    static {
        H262Reader.FRAME_RATE_VALUES = new double[]{23.976024, 24, 25, 29.97003, 30, 50, 59.94006, 60};
    }

    public H262Reader(UserDataReader arg3) {
        ParsableByteArray v3;
        super();
        this.userDataReader = arg3;
        this.prefixFlags = new boolean[4];
        int v1 = 128;
        this.csdBuffer = new CsdBuffer(v1);
        if(arg3 != null) {
            this.userData = new NalUnitTargetBuffer(178, v1);
            v3 = new ParsableByteArray();
        }
        else {
            v3 = null;
            this.userData = ((NalUnitTargetBuffer)v3);
        }

        this.userDataParsable = v3;
    }

    public H262Reader() {
        this(null);
    }

    public void consume(ParsableByteArray arg20) {
        long v8_1;
        H262Reader v0 = this;
        ParsableByteArray v1 = arg20;
        int v2 = arg20.getPosition();
        int v3 = arg20.limit();
        byte[] v4 = v1.data;
        v0.totalBytesWritten += ((long)arg20.bytesLeft());
        v0.output.sampleData(v1, arg20.bytesLeft());
        while(true) {
            int v5 = NalUnitUtil.findNalUnit(v4, v2, v3, v0.prefixFlags);
            if(v5 == v3) {
                break;
            }

            int v7 = v5 + 3;
            int v6 = v1.data[v7] & 255;
            int v8 = v5 - v2;
            boolean v10 = false;
            if(!v0.hasOutputFormat) {
                if(v8 > 0) {
                    v0.csdBuffer.onData(v4, v2, v5);
                }

                int v9 = v8 < 0 ? -v8 : 0;
                if(!v0.csdBuffer.onStartCode(v6, v9)) {
                    goto label_54;
                }

                Pair v9_1 = H262Reader.parseCsdBuffer(v0.csdBuffer, v0.formatId);
                v0.output.format(v9_1.first);
                v0.frameDurationUs = v9_1.second.longValue();
                v0.hasOutputFormat = true;
            }

        label_54:
            if(v0.userDataReader != null) {
                if(v8 > 0) {
                    v0.userData.appendToNalUnit(v4, v2, v5);
                    v2 = 0;
                }
                else {
                    v2 = -v8;
                }

                if(v0.userData.endNalUnit(v2)) {
                    v0.userDataParsable.reset(v0.userData.nalData, NalUnitUtil.unescapeStream(v0.userData.nalData, v0.userData.nalLength));
                    v0.userDataReader.consume(v0.sampleTimeUs, v0.userDataParsable);
                }

                if(v6 != 178) {
                    goto label_86;
                }

                if(v1.data[v5 + 2] != 1) {
                    goto label_86;
                }

                v0.userData.startNalUnit(v6);
            }

        label_86:
            if(v6 == 0 || v6 == 179) {
                v2 = v3 - v5;
                if((v0.startedFirstSample) && (v0.sampleHasPicture) && (v0.hasOutputFormat)) {
                    v0.output.sampleMetadata(v0.sampleTimeUs, v0.sampleIsKeyframe, (((int)(v0.totalBytesWritten - v0.samplePosition))) - v2, v2, null);
                }

                if(!v0.startedFirstSample || (v0.sampleHasPicture)) {
                    v0.samplePosition = v0.totalBytesWritten - (((long)v2));
                    long v12 = -9223372036854775807L;
                    if(v0.pesTimeUs != v12) {
                        v8_1 = v0.pesTimeUs;
                    }
                    else if(v0.startedFirstSample) {
                        v8_1 = v0.sampleTimeUs + v0.frameDurationUs;
                    }
                    else {
                        v8_1 = 0;
                    }

                    v0.sampleTimeUs = v8_1;
                    v0.sampleIsKeyframe = false;
                    v0.pesTimeUs = v12;
                    v0.startedFirstSample = true;
                }

                if(v6 == 0) {
                    v10 = true;
                }

                v0.sampleHasPicture = v10;
            }
            else if(v6 == 184) {
                v0.sampleIsKeyframe = true;
            }

            v2 = v7;
        }

        if(!v0.hasOutputFormat) {
            v0.csdBuffer.onData(v4, v2, v3);
        }

        if(v0.userDataReader != null) {
            v0.userData.appendToNalUnit(v4, v2, v3);
        }
    }

    public void createTracks(ExtractorOutput arg3, TrackIdGenerator arg4) {
        arg4.generateNewId();
        this.formatId = arg4.getFormatId();
        this.output = arg3.track(arg4.getTrackId(), 2);
        if(this.userDataReader != null) {
            this.userDataReader.createTracks(arg3, arg4);
        }
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.pesTimeUs = arg1;
    }

    private static Pair parseCsdBuffer(CsdBuffer arg20, String arg21) {
        CsdBuffer v0 = arg20;
        byte[] v1 = Arrays.copyOf(v0.data, v0.length);
        int v3 = v1[4] & 255;
        int v4 = 5;
        int v5 = v1[v4] & 255;
        int v13 = v3 << 4 | v5 >> 4;
        int v14 = (v5 & 15) << 8 | v1[6] & 255;
        v3 = 7;
        switch((v1[v3] & 240) >> 4) {
            case 2: {
                goto label_35;
            }
            case 3: {
                goto label_31;
            }
            case 4: {
                goto label_27;
            }
        }

        float v18 = 1f;
        goto label_41;
    label_35:
        float v2 = ((float)(v14 * 4));
        v5 = v13 * 3;
        goto label_38;
    label_27:
        v2 = ((float)(v14 * 121));
        v5 = v13 * 100;
        goto label_38;
    label_31:
        v2 = ((float)(v14 * 16));
        v5 = v13 * 9;
    label_38:
        v18 = v2 / (((float)v5));
    label_41:
        Format v2_1 = Format.createVideoSampleFormat(arg21, "video/mpeg2", null, -1, -1, v13, v14, -1f, Collections.singletonList(v1), -1, v18, null);
        long v5_1 = 0;
        v3 = (v1[v3] & 15) - 1;
        if(v3 >= 0 && v3 < H262Reader.FRAME_RATE_VALUES.length) {
            double v6 = H262Reader.FRAME_RATE_VALUES[v3];
            int v0_1 = v0.sequenceExtensionPosition + 9;
            v3 = (v1[v0_1] & 96) >> v4;
            v0_1 = v1[v0_1] & 31;
            if(v3 != v0_1) {
                double v3_1 = ((double)v3);
                Double.isNaN(v3_1);
                double v0_2 = ((double)(v0_1 + 1));
                Double.isNaN(v0_2);
                v6 *= (v3_1 + 1) / v0_2;
            }

            v5_1 = ((long)(1000000 / v6));
        }

        return Pair.create(v2_1, Long.valueOf(v5_1));
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        if(this.userDataReader != null) {
            this.userData.reset();
        }

        this.totalBytesWritten = 0;
        this.startedFirstSample = false;
    }
}

