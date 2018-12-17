package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.ParsableBitArray;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class DtsUtil {
    private static final int[] CHANNELS_BY_AMODE = null;
    private static final byte FIRST_BYTE_14B_BE = 31;
    private static final byte FIRST_BYTE_14B_LE = -1;
    private static final byte FIRST_BYTE_BE = 127;
    private static final byte FIRST_BYTE_LE = -2;
    private static final int[] SAMPLE_RATE_BY_SFREQ = null;
    private static final int SYNC_VALUE_14B_BE = 536864768;
    private static final int SYNC_VALUE_14B_LE = -14745368;
    private static final int SYNC_VALUE_BE = 2147385345;
    private static final int SYNC_VALUE_LE = -25230976;
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE;

    static {
        DtsUtil.CHANNELS_BY_AMODE = new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
        DtsUtil.SAMPLE_RATE_BY_SFREQ = new int[]{-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
        DtsUtil.TWICE_BITRATE_KBPS_BY_RATE = new int[]{64, 112, 128, 192, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
    }

    private DtsUtil() {
        super();
    }

    public static int getDtsFrameSize(byte[] arg7) {
        int v7;
        int v0 = 0;
        int v1 = arg7[0];
        int v3 = 7;
        int v4 = 6;
        int v6 = 4;
        if(v1 != 31) {
            switch(v1) {
                case -2: {
                    goto label_38;
                }
                case -1: {
                    goto label_23;
                }
            }

            v1 = (arg7[5] & 3) << 12 | (arg7[v4] & 255) << v6;
            v7 = arg7[v3];
            goto label_18;
        label_38:
            v1 = (arg7[v6] & 3) << 12 | (arg7[v3] & 255) << v6;
            v7 = arg7[v4];
        label_18:
            v7 = ((v7 & 240) >> v6 | v1) + 1;
            goto label_57;
        label_23:
            v0 = (arg7[v3] & 3) << 12 | (arg7[v4] & 255) << v6;
            v7 = arg7[9];
            goto label_32;
        }
        else {
            v0 = (arg7[v4] & 3) << 12 | (arg7[v3] & 255) << v6;
            v7 = arg7[8];
        label_32:
            v7 = ((v7 & 60) >> 2 | v0) + 1;
            v0 = 1;
        }

    label_57:
        if(v0 != 0) {
            v7 = v7 * 16 / 14;
        }

        return v7;
    }

    private static ParsableBitArray getNormalizedFrameHeader(byte[] arg5) {
        if(arg5[0] == 127) {
            return new ParsableBitArray(arg5);
        }

        arg5 = Arrays.copyOf(arg5, arg5.length);
        if(DtsUtil.isLittleEndianFrameHeader(arg5)) {
            int v1;
            for(v1 = 0; v1 < arg5.length - 1; v1 += 2) {
                byte v2 = arg5[v1];
                int v3 = v1 + 1;
                arg5[v1] = arg5[v3];
                arg5[v3] = v2;
            }
        }

        ParsableBitArray v1_1 = new ParsableBitArray(arg5);
        if(arg5[0] == 31) {
            ParsableBitArray v0 = new ParsableBitArray(arg5);
            while(v0.bitsLeft() >= 16) {
                v0.skipBits(2);
                v1_1.putInt(v0.readBits(14), 14);
            }
        }

        v1_1.reset(arg5);
        return v1_1;
    }

    private static boolean isLittleEndianFrameHeader(byte[] arg3) {
        boolean v0 = false;
        if(arg3[0] == -2 || arg3[0] == -1) {
            v0 = true;
        }

        return v0;
    }

    public static boolean isSyncWord(int arg1) {
        boolean v1 = arg1 == 2147385345 || arg1 == -25230976 || arg1 == 536864768 || arg1 == -14745368 ? true : false;
        return v1;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer arg3) {
        int v3;
        int v0 = arg3.position();
        int v1 = arg3.get(v0);
        if(v1 != 31) {
            switch(v1) {
                case -2: {
                    goto label_23;
                }
                case -1: {
                    goto label_15;
                }
            }

            v1 = (arg3.get(v0 + 4) & 1) << 6;
            v0 += 5;
            goto label_10;
        label_23:
            v1 = (arg3.get(v0 + 5) & 1) << 6;
            v0 += 4;
        label_10:
            v3 = arg3.get(v0) & 252;
            goto label_12;
        label_15:
            v1 = (arg3.get(v0 + 4) & 7) << 4;
            v0 += 7;
            goto label_20;
        }
        else {
            v1 = (arg3.get(v0 + 5) & 7) << 4;
            v0 += 6;
        label_20:
            v3 = arg3.get(v0) & 60;
        }

    label_12:
        v3 = v3 >> 2 | v1;
        return (v3 + 1) * 32;
    }

    public static int parseDtsAudioSampleCount(byte[] arg6) {
        int v6;
        int v0 = arg6[0];
        int v2 = 7;
        int v3 = 5;
        int v4 = 6;
        int v5 = 4;
        if(v0 != 31) {
            switch(v0) {
                case -2: {
                    goto label_23;
                }
                case -1: {
                    goto label_17;
                }
            }

            v0 = (arg6[v5] & 1) << v4;
            v6 = arg6[v3];
            goto label_13;
        label_17:
            v0 = (arg6[v5] & v2) << v5;
            v6 = arg6[v2];
            goto label_21;
        label_23:
            v0 = (arg6[v3] & 1) << v4;
            v6 = arg6[v5];
        label_13:
            v6 &= 252;
        }
        else {
            v0 = (arg6[v3] & v2) << v5;
            v6 = arg6[v4];
        label_21:
            v6 &= 60;
        }

        v6 = v6 >> 2 | v0;
        return (v6 + 1) * 32;
    }

    public static Format parseDtsFormat(byte[] arg15, String arg16, String arg17, DrmInitData arg18) {
        ParsableBitArray v0 = DtsUtil.getNormalizedFrameHeader(arg15);
        v0.skipBits(60);
        int v1 = DtsUtil.CHANNELS_BY_AMODE[v0.readBits(6)];
        int v10 = DtsUtil.SAMPLE_RATE_BY_SFREQ[v0.readBits(4)];
        int v2 = v0.readBits(5);
        int v4 = 2;
        int v7 = v2 >= DtsUtil.TWICE_BITRATE_KBPS_BY_RATE.length ? -1 : DtsUtil.TWICE_BITRATE_KBPS_BY_RATE[v2] * 1000 / v4;
        v0.skipBits(10);
        int v0_1 = v0.readBits(v4) > 0 ? 1 : 0;
        return Format.createAudioSampleFormat(arg16, "audio/vnd.dts", null, v7, -1, v1 + v0_1, v10, null, arg18, 0, arg17);
    }
}

