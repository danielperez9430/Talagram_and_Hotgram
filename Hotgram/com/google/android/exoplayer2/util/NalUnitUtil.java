package com.google.android.exoplayer2.util;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class NalUnitUtil {
    public final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int arg1, int arg2, boolean arg3) {
            super();
            this.picParameterSetId = arg1;
            this.seqParameterSetId = arg2;
            this.bottomFieldPicOrderInFramePresentFlag = arg3;
        }
    }

    public final class SpsData {
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthAspectRatio;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int arg1, int arg2, int arg3, float arg4, boolean arg5, boolean arg6, int arg7, int arg8, int arg9, boolean arg10) {
            super();
            this.seqParameterSetId = arg1;
            this.width = arg2;
            this.height = arg3;
            this.pixelWidthAspectRatio = arg4;
            this.separateColorPlaneFlag = arg5;
            this.frameMbsOnlyFlag = arg6;
            this.frameNumLength = arg7;
            this.picOrderCountType = arg8;
            this.picOrderCntLsbLength = arg9;
            this.deltaPicOrderAlwaysZeroFlag = arg10;
        }
    }

    public static final float[] ASPECT_RATIO_IDC_VALUES = null;
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final byte[] NAL_START_CODE = null;
    private static final String TAG = "NalUnitUtil";
    private static int[] scratchEscapePositions;
    private static final Object scratchEscapePositionsLock;

    static {
        NalUnitUtil.NAL_START_CODE = new byte[]{0, 0, 0, 1};
        NalUnitUtil.ASPECT_RATIO_IDC_VALUES = new float[]{1f, 1f, 1.090909f, 0.909091f, 1.454545f, 1.212121f, 2.181818f, 1.818182f, 2.909091f, 2.424242f, 1.636364f, 1.363636f, 1.939394f, 1.616162f, 1.333333f, 1.5f, 2f};
        NalUnitUtil.scratchEscapePositionsLock = new Object();
        NalUnitUtil.scratchEscapePositions = new int[10];
    }

    private NalUnitUtil() {
        super();
    }

    public static void clearPrefixFlags(boolean[] arg2) {
        arg2[0] = false;
        arg2[1] = false;
        arg2[2] = false;
    }

    public static void discardToSps(ByteBuffer arg9) {
        int v0 = arg9.position();
        int v2 = 0;
        int v3 = 0;
        while(true) {
            int v4 = v2 + 1;
            if(v4 >= v0) {
                break;
            }

            int v5 = arg9.get(v2) & 255;
            int v6 = 3;
            if(v3 == v6) {
                if(v5 == 1 && (arg9.get(v4) & 31) == 7) {
                    ByteBuffer v3_1 = arg9.duplicate();
                    v3_1.position(v2 - v6);
                    v3_1.limit(v0);
                    arg9.position(0);
                    arg9.put(v3_1);
                    return;
                }
            }
            else if(v5 == 0) {
                ++v3;
            }

            if(v5 != 0) {
                v3 = 0;
            }

            v2 = v4;
        }

        arg9.clear();
    }

    public static int findNalUnit(byte[] arg7, int arg8, int arg9, boolean[] arg10) {
        boolean v8;
        int v0 = arg9 - arg8;
        boolean v1 = false;
        boolean v3 = v0 >= 0 ? true : false;
        Assertions.checkState(v3);
        if(v0 == 0) {
            return arg9;
        }

        int v3_1 = 2;
        if(arg10 != null) {
            if(arg10[0]) {
                NalUnitUtil.clearPrefixFlags(arg10);
                return arg8 - 3;
            }
            else {
                if(v0 > 1 && (arg10[1]) && arg7[arg8] == 1) {
                    NalUnitUtil.clearPrefixFlags(arg10);
                    return arg8 - v3_1;
                }

                if(v0 <= v3_1) {
                    goto label_36;
                }

                if(!arg10[v3_1]) {
                    goto label_36;
                }

                if(arg7[arg8] != 0) {
                    goto label_36;
                }

                if(arg7[arg8 + 1] != 1) {
                    goto label_36;
                }

                NalUnitUtil.clearPrefixFlags(arg10);
                return arg8 - 1;
            }
        }

    label_36:
        int v4 = arg9 - 1;
        arg8 += v3_1;
        while(arg8 < v4) {
            if((arg7[arg8] & 254) != 0) {
            }
            else {
                int v5 = arg8 - 2;
                if(arg7[v5] == 0 && arg7[arg8 - 1] == 0 && arg7[arg8] == 1) {
                    if(arg10 != null) {
                        NalUnitUtil.clearPrefixFlags(arg10);
                    }

                    return v5;
                }

                arg8 += -2;
            }

            arg8 += 3;
        }

        if(arg10 != null) {
            if(v0 > v3_1) {
                if(arg7[arg9 - 3] != 0) {
                    goto label_69;
                }
                else if(arg7[arg9 - 2] == 0) {
                    if(arg7[v4] != 1) {
                        goto label_69;
                    }

                    goto label_67;
                }
                else {
                    goto label_69;
                }
            }
            else if(v0 == v3_1) {
                if(!arg10[v3_1]) {
                    goto label_69;
                }
                else if(arg7[arg9 - 2] != 0) {
                    goto label_69;
                }
                else if(arg7[v4] == 1) {
                    goto label_67;
                }
                else {
                    goto label_69;
                }
            }
            else if(!arg10[1]) {
            label_69:
                v8 = false;
            }
            else if(arg7[v4] == 1) {
            label_67:
                v8 = true;
            }
            else {
                goto label_69;
            }

            arg10[0] = v8;
            if(v0 > 1) {
                if(arg7[arg9 - 2] == 0) {
                    if(arg7[v4] != 0) {
                        goto label_94;
                    }

                    goto label_92;
                }
                else {
                    goto label_94;
                }
            }
            else if(!arg10[v3_1]) {
            label_94:
                v8 = false;
            }
            else if(arg7[v4] == 0) {
            label_92:
                v8 = true;
            }
            else {
                goto label_94;
            }

            arg10[1] = v8;
            if(arg7[v4] == 0) {
                v1 = true;
            }

            arg10[v3_1] = v1;
        }

        return arg9;
    }

    private static int findNextUnescapeIndex(byte[] arg2, int arg3, int arg4) {
        while(arg3 < arg4 - 2) {
            if(arg2[arg3] == 0 && arg2[arg3 + 1] == 0 && arg2[arg3 + 2] == 3) {
                return arg3;
            }

            ++arg3;
        }

        return arg4;
    }

    public static int getH265NalUnitType(byte[] arg0, int arg1) {
        return (arg0[arg1 + 3] & 126) >> 1;
    }

    public static int getNalUnitType(byte[] arg0, int arg1) {
        return arg0[arg1 + 3] & 31;
    }

    public static boolean isNalUnitSei(String arg3, byte arg4) {
        boolean v1 = true;
        if((!"video/avc".equals(arg3) || (arg4 & 31) != 6) && (!"video/hevc".equals(arg3) || (arg4 & 126) >> 1 != 39)) {
            v1 = false;
        }

        return v1;
    }

    public static PpsData parsePpsNalUnit(byte[] arg1, int arg2, int arg3) {
        ParsableNalUnitBitArray v0 = new ParsableNalUnitBitArray(arg1, arg2, arg3);
        v0.skipBits(8);
        int v1 = v0.readUnsignedExpGolombCodedInt();
        arg2 = v0.readUnsignedExpGolombCodedInt();
        v0.skipBit();
        return new PpsData(v1, arg2, v0.readBit());
    }

    public static SpsData parseSpsNalUnit(byte[] arg19, int arg20, int arg21) {
        float v8_2;
        int v3;
        boolean v14;
        int v13;
        int v15;
        boolean v9;
        int v11;
        boolean v8;
        ParsableNalUnitBitArray v0 = new ParsableNalUnitBitArray(arg19, arg20, arg21);
        int v1 = 8;
        v0.skipBits(v1);
        int v2 = v0.readBits(v1);
        v0.skipBits(16);
        int v5 = v0.readUnsignedExpGolombCodedInt();
        int v4 = 3;
        int v7 = 1;
        if(v2 == 100 || v2 == 110 || v2 == 122 || v2 == 244 || v2 == 44 || v2 == 83 || v2 == 86 || v2 == 118 || v2 == 128 || v2 == 138) {
            v2 = v0.readUnsignedExpGolombCodedInt();
            v8 = v2 == v4 ? v0.readBit() : false;
            v0.readUnsignedExpGolombCodedInt();
            v0.readUnsignedExpGolombCodedInt();
            v0.skipBit();
            if(v0.readBit()) {
                int v9_1 = v2 != v4 ? 8 : 12;
                int v10;
                for(v10 = 0; v10 < v9_1; ++v10) {
                    if(v0.readBit()) {
                        v11 = v10 < 6 ? 16 : 64;
                        NalUnitUtil.skipScalingList(v0, v11);
                    }
                }
            }

            v9 = v8;
        }
        else {
            v2 = 1;
            v9 = false;
        }

        v11 = v0.readUnsignedExpGolombCodedInt() + 4;
        int v12 = v0.readUnsignedExpGolombCodedInt();
        if(v12 == 0) {
            v15 = v5;
            v13 = v0.readUnsignedExpGolombCodedInt() + 4;
            goto label_73;
        }
        else if(v12 == 1) {
            v8 = v0.readBit();
            v0.readSignedExpGolombCodedInt();
            v0.readSignedExpGolombCodedInt();
            long v13_1 = ((long)v0.readUnsignedExpGolombCodedInt());
            v15 = v5;
            for(v10 = 0; (((long)v10)) < v13_1; ++v10) {
                v0.readUnsignedExpGolombCodedInt();
            }

            v14 = v8;
            v13 = 0;
        }
        else {
            v15 = v5;
            v13 = 0;
        label_73:
            v14 = false;
        }

        v0.readUnsignedExpGolombCodedInt();
        v0.skipBit();
        v4 = v0.readUnsignedExpGolombCodedInt() + 1;
        v5 = v0.readUnsignedExpGolombCodedInt() + 1;
        boolean v10_1 = v0.readBit();
        int v6 = 2;
        int v8_1 = (2 - (((int)v10_1))) * v5;
        if(!v10_1) {
            v0.skipBit();
        }

        v0.skipBit();
        v4 *= 16;
        v8_1 *= 16;
        if(v0.readBit()) {
            v5 = v0.readUnsignedExpGolombCodedInt();
            int v16 = v0.readUnsignedExpGolombCodedInt();
            int v17 = v0.readUnsignedExpGolombCodedInt();
            int v18 = v0.readUnsignedExpGolombCodedInt();
            if(v2 == 0) {
                v6 -= ((int)v10_1);
                v3 = 1;
            }
            else {
                v3 = v2 == 3 ? 1 : 2;
                if(v2 == 1) {
                    v7 = 2;
                }

                v6 = (v6 - (((int)v10_1))) * v7;
            }

            v4 -= (v5 + v16) * v3;
            v8_1 -= (v17 + v18) * v6;
        }

        v6 = v4;
        v7 = v8_1;
        float v2_1 = 1f;
        if(!v0.readBit() || !v0.readBit()) {
        label_169:
            v8_2 = 1f;
        }
        else {
            v1 = v0.readBits(v1);
            if(v1 == 255) {
                v1 = v0.readBits(16);
                int v0_1 = v0.readBits(16);
                if(v1 != 0 && v0_1 != 0) {
                    v2_1 = (((float)v1)) / (((float)v0_1));
                }

                v8_2 = v2_1;
            }
            else {
                if(v1 < NalUnitUtil.ASPECT_RATIO_IDC_VALUES.length) {
                    v8_2 = NalUnitUtil.ASPECT_RATIO_IDC_VALUES[v1];
                    goto label_170;
                }

                Log.w("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + v1);
                goto label_169;
            }
        }

    label_170:
        return new SpsData(v15, v6, v7, v8_2, v9, v10_1, v11, v12, v13, v14);
    }

    private static void skipScalingList(ParsableNalUnitBitArray arg3, int arg4) {
        int v0 = 8;
        int v1 = 0;
        int v2 = 8;
        while(v1 < arg4) {
            if(v0 != 0) {
                v0 = (arg3.readSignedExpGolombCodedInt() + v2 + 256) % 256;
            }

            if(v0 == 0) {
            }
            else {
                v2 = v0;
            }

            ++v1;
        }
    }

    public static int unescapeStream(byte[] arg8, int arg9) {
        Object v0 = NalUnitUtil.scratchEscapePositionsLock;
        __monitor_enter(v0);
        int v2 = 0;
        int v3 = 0;
        while(true) {
            if(v2 >= arg9) {
                goto label_25;
            }

            try {
                v2 = NalUnitUtil.findNextUnescapeIndex(arg8, v2, arg9);
                if(v2 >= arg9) {
                    continue;
                }

                if(NalUnitUtil.scratchEscapePositions.length <= v3) {
                    NalUnitUtil.scratchEscapePositions = Arrays.copyOf(NalUnitUtil.scratchEscapePositions, NalUnitUtil.scratchEscapePositions.length * 2);
                }

                NalUnitUtil.scratchEscapePositions[v3] = v2;
                v2 += 3;
                ++v3;
                continue;
            label_25:
                arg9 -= v3;
                v2 = 0;
                int v4 = 0;
                int v5 = 0;
                while(v2 < v3) {
                    int v6 = NalUnitUtil.scratchEscapePositions[v2] - v5;
                    System.arraycopy(arg8, v5, arg8, v4, v6);
                    v4 += v6;
                    int v7 = v4 + 1;
                    arg8[v4] = 0;
                    v4 = v7 + 1;
                    arg8[v7] = 0;
                    v5 += v6 + 3;
                    ++v2;
                }

                System.arraycopy(arg8, v5, arg8, v4, arg9 - v4);
                __monitor_exit(v0);
                return arg9;
            label_47:
                __monitor_exit(v0);
                break;
            }
            catch(Throwable v8) {
                goto label_47;
            }
        }

        throw v8;
    }
}

