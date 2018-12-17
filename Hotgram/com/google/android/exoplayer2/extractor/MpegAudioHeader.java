package com.google.android.exoplayer2.extractor;

public final class MpegAudioHeader {
    private static final int[] BITRATE_V1_L1 = null;
    private static final int[] BITRATE_V1_L2 = null;
    private static final int[] BITRATE_V1_L3 = null;
    private static final int[] BITRATE_V2 = null;
    private static final int[] BITRATE_V2_L1 = null;
    public static final int MAX_FRAME_SIZE_BYTES = 4096;
    private static final String[] MIME_TYPE_BY_LAYER;
    private static final int[] SAMPLING_RATE_V1;
    public int bitrate;
    public int channels;
    public int frameSize;
    public String mimeType;
    public int sampleRate;
    public int samplesPerFrame;
    public int version;

    static {
        MpegAudioHeader.MIME_TYPE_BY_LAYER = new String[]{"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
        MpegAudioHeader.SAMPLING_RATE_V1 = new int[]{44100, 48000, 32000};
        MpegAudioHeader.BITRATE_V1_L1 = new int[]{32, 64, 96, 128, 160, 192, 224, 256, 288, 320, 352, 384, 416, 448};
        MpegAudioHeader.BITRATE_V2_L1 = new int[]{32, 48, 56, 64, 80, 96, 112, 128, 144, 160, 176, 192, 224, 256};
        MpegAudioHeader.BITRATE_V1_L2 = new int[]{32, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384};
        MpegAudioHeader.BITRATE_V1_L3 = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320};
        MpegAudioHeader.BITRATE_V2 = new int[]{8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160};
    }

    public MpegAudioHeader() {
        super();
    }

    public static int getFrameSize(int arg7) {
        int v2 = -1;
        if((arg7 & -2097152) != -2097152) {
            return v2;
        }

        int v1 = 3;
        int v0 = arg7 >>> 19 & v1;
        if(v0 == 1) {
            return v2;
        }

        int v4 = arg7 >>> 17 & v1;
        if(v4 == 0) {
            return v2;
        }

        int v6 = 15;
        int v5 = arg7 >>> 12 & v6;
        if(v5 != 0) {
            if(v5 == v6) {
            }
            else {
                v6 = arg7 >>> 10 & v1;
                if(v6 == v1) {
                    return v2;
                }
                else {
                    v2 = MpegAudioHeader.SAMPLING_RATE_V1[v6];
                    v6 = 2;
                    if(v0 == v6) {
                        v2 /= 2;
                    }
                    else if(v0 == 0) {
                        v2 /= 4;
                    }

                    arg7 = arg7 >>> 9 & 1;
                    if(v4 == v1) {
                        v0 = v0 == v1 ? MpegAudioHeader.BITRATE_V1_L1[v5 - 1] : MpegAudioHeader.BITRATE_V2_L1[v5 - 1];
                        return (v0 * 12000 / v2 + arg7) * 4;
                    }

                    if(v0 != v1) {
                        v5 = MpegAudioHeader.BITRATE_V2[v5 - 1];
                    }
                    else if(v4 == v6) {
                        v5 = MpegAudioHeader.BITRATE_V1_L2[v5 - 1];
                    }
                    else {
                        v5 = MpegAudioHeader.BITRATE_V1_L3[v5 - 1];
                    }

                    v6 = 144000;
                    if(v0 == v1) {
                        return v5 * v6 / v2 + arg7;
                    }

                    if(v4 == 1) {
                        v6 = 72000;
                    }

                    return v6 * v5 / v2 + arg7;
                }
            }
        }

        return v2;
    }

    public static boolean populateHeader(int arg11, MpegAudioHeader arg12) {
        int v10;
        int v9;
        if((arg11 & -2097152) != -2097152) {
            return 0;
        }

        int v1 = 3;
        int v4 = arg11 >>> 19 & 3;
        if(v4 == 1) {
            return 0;
        }

        int v3 = arg11 >>> 17 & v1;
        if(v3 == 0) {
            return 0;
        }

        int v6 = 15;
        int v5 = arg11 >>> 12 & v6;
        if(v5 != 0) {
            if(v5 == v6) {
            }
            else {
                v6 = arg11 >>> 10 & v1;
                if(v6 == v1) {
                    return 0;
                }
                else {
                    int v2 = MpegAudioHeader.SAMPLING_RATE_V1[v6];
                    v6 = 2;
                    if(v4 == v6) {
                        v2 /= 2;
                    }
                    else if(v4 == 0) {
                        v2 /= 4;
                    }

                    int v7 = v2;
                    v2 = arg11 >>> 9 & 1;
                    int v8 = 1152;
                    if(v3 == v1) {
                        v5 = v4 == v1 ? MpegAudioHeader.BITRATE_V1_L1[v5 - 1] : MpegAudioHeader.BITRATE_V2_L1[v5 - 1];
                        v9 = (v5 * 12000 / v7 + v2) * 4;
                        v10 = 384;
                    }
                    else {
                        v9 = 144000;
                        if(v4 == v1) {
                            v5 = v3 == v6 ? MpegAudioHeader.BITRATE_V1_L2[v5 - 1] : MpegAudioHeader.BITRATE_V1_L3[v5 - 1];
                            v9 = v9 * v5 / v7 + v2;
                            v10 = 1152;
                            goto label_81;
                        }

                        v5 = MpegAudioHeader.BITRATE_V2[v5 - 1];
                        if(v3 == 1) {
                            v8 = 576;
                        }

                        if(v3 == 1) {
                            v9 = 72000;
                        }

                        v9 = v9 * v5 / v7 + v2;
                        v10 = v8;
                    }

                label_81:
                    String v2_1 = MpegAudioHeader.MIME_TYPE_BY_LAYER[3 - v3];
                    v8 = (arg11 >> 6 & v1) == v1 ? 1 : 2;
                    arg12.setValues(v4, v2_1, v9, v7, v8, v5 * 1000, v10);
                    return 1;
                }
            }
        }

        return 0;
    }

    private void setValues(int arg1, String arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
        this.version = arg1;
        this.mimeType = arg2;
        this.frameSize = arg3;
        this.sampleRate = arg4;
        this.channels = arg5;
        this.bitrate = arg6;
        this.samplesPerFrame = arg7;
    }
}

