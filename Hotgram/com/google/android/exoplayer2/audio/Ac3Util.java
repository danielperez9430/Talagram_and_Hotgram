package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public final class Ac3Util {
    class com.google.android.exoplayer2.audio.Ac3Util$1 {
    }

    public final class SyncFrameInfo {
        @Retention(value=RetentionPolicy.SOURCE) @public interface StreamType {
        }

        public static final int STREAM_TYPE_TYPE0 = 0;
        public static final int STREAM_TYPE_TYPE1 = 1;
        public static final int STREAM_TYPE_TYPE2 = 2;
        public static final int STREAM_TYPE_UNDEFINED = -1;
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        SyncFrameInfo(String arg1, int arg2, int arg3, int arg4, int arg5, int arg6, com.google.android.exoplayer2.audio.Ac3Util$1 arg7) {
            this(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        private SyncFrameInfo(String arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
            super();
            this.mimeType = arg1;
            this.streamType = arg2;
            this.channelCount = arg3;
            this.sampleRate = arg4;
            this.frameSize = arg5;
            this.sampleCount = arg6;
        }
    }

    private static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    private static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = null;
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = null;
    private static final int[] CHANNEL_COUNT_BY_ACMOD = null;
    private static final int[] SAMPLE_RATE_BY_FSCOD = null;
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = null;
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = null;
    public static final int TRUEHD_RECHUNK_SAMPLE_COUNT = 16;
    public static final int TRUEHD_SYNCFRAME_PREFIX_LENGTH = 10;

    static {
        Ac3Util.BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = new int[]{1, 2, 3, 6};
        Ac3Util.SAMPLE_RATE_BY_FSCOD = new int[]{48000, 44100, 32000};
        Ac3Util.SAMPLE_RATE_BY_FSCOD2 = new int[]{24000, 22050, 16000};
        Ac3Util.CHANNEL_COUNT_BY_ACMOD = new int[]{2, 1, 2, 3, 3, 4, 4, 5};
        Ac3Util.BITRATE_BY_HALF_FRMSIZECOD = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384, 448, 512, 576, 640};
        Ac3Util.SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = new int[]{69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};
    }

    private Ac3Util() {
        super();
    }

    public static int findTrueHdSyncframeOffset(ByteBuffer arg5) {
        int v0 = arg5.position();
        int v1 = arg5.limit() - 10;
        int v2;
        for(v2 = v0; v2 <= v1; ++v2) {
            if((arg5.getInt(v2 + 4) & -16777217) == -1167101192) {
                return v2 - v0;
            }
        }

        return -1;
    }

    public static int getAc3SyncframeAudioSampleCount() {
        return 1536;
    }

    private static int getAc3SyncframeSize(int arg2, int arg3) {
        int v0 = arg3 / 2;
        if(arg2 >= 0 && arg2 < Ac3Util.SAMPLE_RATE_BY_FSCOD.length && arg3 >= 0) {
            if(v0 >= Ac3Util.SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1.length) {
            }
            else {
                arg2 = Ac3Util.SAMPLE_RATE_BY_FSCOD[arg2];
                if(arg2 == 44100) {
                    return (Ac3Util.SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1[v0] + arg3 % 2) * 2;
                }
                else {
                    arg3 = Ac3Util.BITRATE_BY_HALF_FRMSIZECOD[v0];
                    if(arg2 == 32000) {
                        return arg3 * 6;
                    }
                    else {
                        return arg3 * 4;
                    }
                }
            }
        }

        return -1;
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray arg13, String arg14, String arg15, DrmInitData arg16) {
        int v8 = Ac3Util.SAMPLE_RATE_BY_FSCOD[(arg13.readUnsignedByte() & 192) >> 6];
        int v0 = arg13.readUnsignedByte();
        int v1 = Ac3Util.CHANNEL_COUNT_BY_ACMOD[(v0 & 56) >> 3];
        if((v0 & 4) != 0) {
            ++v1;
        }

        return Format.createAudioSampleFormat(arg14, "audio/ac3", null, -1, -1, v1, v8, null, arg16, 0, arg15);
    }

    public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray arg18) {
        String v6_1;
        String v2_1;
        int v4;
        int v13;
        int v12;
        int v14;
        int v11;
        ParsableBitArray v0 = arg18;
        int v1 = arg18.getPosition();
        v0.skipBits(40);
        int v2 = 5;
        int v5 = 16;
        int v3 = v0.readBits(v2) == v5 ? 1 : 0;
        v0.setPosition(v1);
        v1 = -1;
        int v7 = 8;
        int v8 = 3;
        int v9 = 6;
        int v10 = 2;
        if(v3 != 0) {
            v0.skipBits(v5);
            switch(v0.readBits(v10)) {
                case 0: {
                    v1 = 0;
                    break;
                }
                case 1: {
                    v1 = 1;
                    break;
                }
                case 2: {
                    v1 = 2;
                    break;
                }
                default: {
                    break;
                }
            }

            v0.skipBits(v8);
            v3 = (v0.readBits(11) + 1) * 2;
            v11 = v0.readBits(v10);
            if(v11 == v8) {
                v14 = Ac3Util.SAMPLE_RATE_BY_FSCOD2[v0.readBits(v10)];
                v12 = 3;
                v13 = 6;
            }
            else {
                v12 = v0.readBits(v10);
                v13 = Ac3Util.BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[v12];
                v14 = Ac3Util.SAMPLE_RATE_BY_FSCOD[v11];
            }

            int v15 = v13 * 256;
            v4 = v0.readBits(v8);
            boolean v16 = arg18.readBit();
            int v17 = Ac3Util.CHANNEL_COUNT_BY_ACMOD[v4] + (((int)v16));
            v0.skipBits(10);
            if(arg18.readBit()) {
                v0.skipBits(v7);
            }

            if(v4 == 0) {
                v0.skipBits(v2);
                if(arg18.readBit()) {
                    v0.skipBits(v7);
                }
            }

            if(v1 == 1 && (arg18.readBit())) {
                v0.skipBits(v5);
            }

            v8 = 4;
            if(arg18.readBit()) {
                if(v4 > v10) {
                    v0.skipBits(v10);
                }

                if((v4 & 1) != 0 && v4 > v10) {
                    v0.skipBits(v9);
                }

                if((v4 & 4) != 0) {
                    v0.skipBits(v9);
                }

                if((v16) && (arg18.readBit())) {
                    v0.skipBits(v2);
                }

                if(v1 != 0) {
                    goto label_172;
                }

                if(arg18.readBit()) {
                    v0.skipBits(v9);
                }

                if(v4 == 0 && (arg18.readBit())) {
                    v0.skipBits(v9);
                }

                if(arg18.readBit()) {
                    v0.skipBits(v9);
                }

                v5 = v0.readBits(v10);
                if(v5 == 1) {
                    v0.skipBits(v2);
                }
                else if(v5 == v10) {
                    v0.skipBits(12);
                }
                else if(v5 == 3) {
                    v5 = v0.readBits(v2);
                    if(arg18.readBit()) {
                        v0.skipBits(v2);
                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(!arg18.readBit()) {
                            goto label_137;
                        }

                        if(arg18.readBit()) {
                            v0.skipBits(v8);
                        }

                        if(!arg18.readBit()) {
                            goto label_137;
                        }

                        v0.skipBits(v8);
                    }

                label_137:
                    if(arg18.readBit()) {
                        v0.skipBits(v2);
                        if(arg18.readBit()) {
                            v0.skipBits(7);
                            if(arg18.readBit()) {
                                v0.skipBits(v7);
                            }
                        }
                    }

                    v0.skipBits((v5 + v10) * 8);
                    arg18.byteAlign();
                }

                if(v4 < v10) {
                    int v6 = 14;
                    if(arg18.readBit()) {
                        v0.skipBits(v6);
                    }

                    if(v4 != 0) {
                        goto label_160;
                    }

                    if(!arg18.readBit()) {
                        goto label_160;
                    }

                    v0.skipBits(v6);
                }

            label_160:
                if(!arg18.readBit()) {
                    goto label_172;
                }

                if(v12 == 0) {
                    v0.skipBits(v2);
                    goto label_172;
                }

                for(v5 = 0; v5 < v13; ++v5) {
                    if(arg18.readBit()) {
                        v0.skipBits(v2);
                    }
                }
            }

        label_172:
            if(arg18.readBit()) {
                v0.skipBits(v2);
                if(v4 == v10) {
                    v0.skipBits(v8);
                }

                if(v4 >= v9) {
                    v0.skipBits(v10);
                }

                if(arg18.readBit()) {
                    v0.skipBits(v7);
                }

                if(v4 == 0 && (arg18.readBit())) {
                    v0.skipBits(v7);
                }

                v2 = 3;
                if(v11 >= v2) {
                    goto label_191;
                }

                arg18.skipBit();
            }
            else {
                v2 = 3;
            }

        label_191:
            if(v1 == 0 && v12 != v2) {
                arg18.skipBit();
            }

            if(v1 == v10 && (v12 == v2 || (arg18.readBit()))) {
                v0.skipBits(v9);
            }

            v2_1 = "audio/eac3";
            if((arg18.readBit()) && v0.readBits(v9) == 1 && v0.readBits(v7) == 1) {
                v2_1 = "audio/eac3-joc";
            }

            v7 = v1;
            v6_1 = v2_1;
            v10 = v3;
            v9 = v14;
            v11 = v15;
            v8 = v17;
        }
        else {
            v2_1 = "audio/ac3";
            v0.skipBits(32);
            v3 = v0.readBits(v10);
            v4 = Ac3Util.getAc3SyncframeSize(v3, v0.readBits(v9));
            v0.skipBits(v7);
            v5 = v0.readBits(3);
            if((v5 & 1) != 0 && v5 != 1) {
                v0.skipBits(v10);
            }

            if((v5 & 4) != 0) {
                v0.skipBits(v10);
            }

            if(v5 == v10) {
                v0.skipBits(v10);
            }

            v6_1 = v2_1;
            v10 = v4;
            v9 = Ac3Util.SAMPLE_RATE_BY_FSCOD[v3];
            v8 = Ac3Util.CHANNEL_COUNT_BY_ACMOD[v5] + arg18.readBit();
            v7 = -1;
            v11 = 1536;
        }

        return new SyncFrameInfo(v6_1, v7, v8, v9, v10, v11, null);
    }

    public static int parseAc3SyncframeSize(byte[] arg2) {
        if(arg2.length < 5) {
            return -1;
        }

        return Ac3Util.getAc3SyncframeSize((arg2[4] & 192) >> 6, arg2[4] & 63);
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray arg15, String arg16, String arg17, DrmInitData arg18) {
        int v0 = 2;
        arg15.skipBytes(v0);
        int v10 = Ac3Util.SAMPLE_RATE_BY_FSCOD[(arg15.readUnsignedByte() & 192) >> 6];
        int v2 = arg15.readUnsignedByte();
        int v3 = Ac3Util.CHANNEL_COUNT_BY_ACMOD[(v2 & 14) >> 1];
        if((v2 & 1) != 0) {
            ++v3;
        }

        if((arg15.readUnsignedByte() & 30) >> 1 > 0 && (v0 & arg15.readUnsignedByte()) != 0) {
            v3 += 2;
        }

        int v9 = v3;
        String v0_1 = "audio/eac3";
        if(arg15.bytesLeft() > 0 && (arg15.readUnsignedByte() & 1) != 0) {
            v0_1 = "audio/eac3-joc";
        }

        return Format.createAudioSampleFormat(arg16, v0_1, null, -1, -1, v9, v10, null, arg18, 0, arg17);
    }

    public static int parseEAc3SyncframeAudioSampleCount(ByteBuffer arg3) {
        int v1 = 6;
        if((arg3.get(arg3.position() + 4) & 192) >> v1 == 3) {
        }
        else {
            v1 = Ac3Util.BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(arg3.get(arg3.position() + 4) & 48) >> 4];
        }

        return v1 * 256;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer arg3, int arg4) {
        int v0 = (arg3.get(arg3.position() + arg4 + 7) & 255) == 187 ? 1 : 0;
        int v1 = 40;
        int v2 = arg3.position() + arg4;
        arg4 = v0 != 0 ? 9 : 8;
        return v1 << (arg3.get(v2 + arg4) >> 4 & 7);
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] arg5) {
        int v0 = 4;
        int v2 = 0;
        if(arg5[v0] == -8 && arg5[5] == 114 && arg5[6] == 111) {
            int v1 = 7;
            if((arg5[v1] & 254) != 186) {
            }
            else {
                if((arg5[v1] & 255) == 187) {
                    v2 = 1;
                }

                int v3 = 40;
                v2 = v2 != 0 ? 9 : 8;
                return v3 << (arg5[v2] >> v0 & v1);
            }
        }

        return 0;
    }
}

