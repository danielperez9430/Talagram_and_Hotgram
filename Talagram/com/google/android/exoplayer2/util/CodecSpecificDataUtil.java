package com.google.android.exoplayer2.util;

import android.util.Pair;
import com.google.android.exoplayer2.ParserException;
import java.util.ArrayList;
import java.util.List;

public final class CodecSpecificDataUtil {
    private static final int AUDIO_OBJECT_TYPE_AAC_LC = 2;
    private static final int AUDIO_OBJECT_TYPE_ER_BSAC = 22;
    private static final int AUDIO_OBJECT_TYPE_ESCAPE = 31;
    private static final int AUDIO_OBJECT_TYPE_PS = 29;
    private static final int AUDIO_OBJECT_TYPE_SBR = 5;
    private static final int AUDIO_SPECIFIC_CONFIG_CHANNEL_CONFIGURATION_INVALID = -1;
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = null;
    private static final int AUDIO_SPECIFIC_CONFIG_FREQUENCY_INDEX_ARBITRARY = 15;
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE;
    private static final byte[] NAL_START_CODE;

    static {
        CodecSpecificDataUtil.NAL_START_CODE = new byte[]{0, 0, 0, 1};
        CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = new int[]{96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
        CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = new int[]{0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};
    }

    private CodecSpecificDataUtil() {
        super();
    }

    public static byte[] buildAacAudioSpecificConfig(int arg2, int arg3, int arg4) {
        return new byte[]{((byte)(arg2 << 3 & 248 | arg3 >> 1 & 7)), ((byte)(arg3 << 7 & 128 | arg4 << 3 & 120))};
    }

    public static byte[] buildAacLcAudioSpecificConfig(int arg5, int arg6) {
        int v0 = 0;
        int v1 = -1;
        int v2 = 0;
        int v3 = -1;
        while(v2 < CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE.length) {
            if(arg5 == CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[v2]) {
                v3 = v2;
            }

            ++v2;
        }

        v2 = -1;
        while(v0 < CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE.length) {
            if(arg6 == CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[v0]) {
                v2 = v0;
            }

            ++v0;
        }

        if(arg5 != v1 && v2 != v1) {
            return CodecSpecificDataUtil.buildAacAudioSpecificConfig(2, v3, v2);
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Invalid sample rate or number of channels: ");
        v1_1.append(arg5);
        v1_1.append(", ");
        v1_1.append(arg6);
        throw new IllegalArgumentException(v1_1.toString());
    }

    public static byte[] buildNalUnit(byte[] arg4, int arg5, int arg6) {
        byte[] v0 = new byte[CodecSpecificDataUtil.NAL_START_CODE.length + arg6];
        System.arraycopy(CodecSpecificDataUtil.NAL_START_CODE, 0, v0, 0, CodecSpecificDataUtil.NAL_START_CODE.length);
        System.arraycopy(arg4, arg5, v0, CodecSpecificDataUtil.NAL_START_CODE.length, arg6);
        return v0;
    }

    private static int findNalStartCode(byte[] arg2, int arg3) {
        int v0 = arg2.length - CodecSpecificDataUtil.NAL_START_CODE.length;
        while(arg3 <= v0) {
            if(CodecSpecificDataUtil.isNalStartCode(arg2, arg3)) {
                return arg3;
            }

            ++arg3;
        }

        return -1;
    }

    private static int getAacAudioObjectType(ParsableBitArray arg2) {
        int v0 = arg2.readBits(5);
        if(v0 == 31) {
            v0 = arg2.readBits(6) + 32;
        }

        return v0;
    }

    private static int getAacSamplingFrequency(ParsableBitArray arg2) {
        int v2;
        int v0 = arg2.readBits(4);
        if(v0 == 15) {
            v2 = arg2.readBits(24);
        }
        else {
            boolean v2_1 = v0 < 13 ? true : false;
            Assertions.checkArgument(v2_1);
            v2 = CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[v0];
        }

        return v2;
    }

    private static boolean isNalStartCode(byte[] arg4, int arg5) {
        if(arg4.length - arg5 <= CodecSpecificDataUtil.NAL_START_CODE.length) {
            return 0;
        }

        int v0;
        for(v0 = 0; v0 < CodecSpecificDataUtil.NAL_START_CODE.length; ++v0) {
            if(arg4[arg5 + v0] != CodecSpecificDataUtil.NAL_START_CODE[v0]) {
                return 0;
            }
        }

        return 1;
    }

    public static Pair parseAacAudioSpecificConfig(byte[] arg1) {
        return CodecSpecificDataUtil.parseAacAudioSpecificConfig(new ParsableBitArray(arg1), false);
    }

    public static Pair parseAacAudioSpecificConfig(ParsableBitArray arg5, boolean arg6) {
        int v5;
        int v0 = CodecSpecificDataUtil.getAacAudioObjectType(arg5);
        int v1 = CodecSpecificDataUtil.getAacSamplingFrequency(arg5);
        int v2 = 4;
        int v3 = arg5.readBits(v2);
        if(v0 == 5 || v0 == 29) {
            v1 = CodecSpecificDataUtil.getAacSamplingFrequency(arg5);
            v0 = CodecSpecificDataUtil.getAacAudioObjectType(arg5);
            if(v0 == 22) {
                v3 = arg5.readBits(v2);
            }
        }

        if(arg6) {
            if(v0 != 17) {
                switch(v0) {
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: {
                        goto label_28;
                    }
                    default: {
                        switch(v0) {
                            case 6: 
                            case 7: {
                                goto label_28;
                            }
                            default: {
                                switch(v0) {
                                    case 19: 
                                    case 20: 
                                    case 21: 
                                    case 22: 
                                    case 23: {
                                        goto label_28;
                                    }
                                    default: {
                                        StringBuilder v6 = new StringBuilder();
                                        v6.append("Unsupported audio object type: ");
                                        v6.append(v0);
                                        throw new ParserException(v6.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        label_28:
            CodecSpecificDataUtil.parseGaSpecificConfig(arg5, v0, v3);
            switch(v0) {
                case 17: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: {
                    goto label_31;
                }
            }

            goto label_46;
        label_31:
            v5 = arg5.readBits(2);
            if(v5 != 2 && v5 != 3) {
                goto label_46;
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("Unsupported epConfig: ");
            v0_1.append(v5);
            throw new ParserException(v0_1.toString());
        }

    label_46:
        v5 = CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[v3];
        arg6 = v5 != -1 ? true : false;
        Assertions.checkArgument(arg6);
        return Pair.create(Integer.valueOf(v1), Integer.valueOf(v5));
    }

    private static void parseGaSpecificConfig(ParsableBitArray arg4, int arg5, int arg6) {
        arg4.skipBits(1);
        if(arg4.readBit()) {
            arg4.skipBits(14);
        }

        boolean v1 = arg4.readBit();
        if(arg6 != 0) {
            int v2 = 3;
            int v3 = 20;
            if(arg5 == 6 || arg5 == v3) {
                arg4.skipBits(v2);
            }

            if(v1) {
                if(arg5 == 22) {
                    arg4.skipBits(16);
                }

                if(arg5 == 17 || arg5 == 19 || arg5 == v3 || arg5 == 23) {
                    arg4.skipBits(v2);
                }

                arg4.skipBits(1);
            }

            return;
        }

        throw new UnsupportedOperationException();
    }

    public static byte[][] splitNalUnits(byte[] arg7) {
        if(!CodecSpecificDataUtil.isNalStartCode(arg7, 0)) {
            return null;
        }

        ArrayList v1 = new ArrayList();
        int v2 = 0;
        do {
            ((List)v1).add(Integer.valueOf(v2));
            v2 = CodecSpecificDataUtil.findNalStartCode(arg7, v2 + CodecSpecificDataUtil.NAL_START_CODE.length);
        }
        while(v2 != -1);

        byte[][] v2_1 = new byte[((List)v1).size()][];
        int v3;
        for(v3 = 0; v3 < ((List)v1).size(); ++v3) {
            int v4 = ((List)v1).get(v3).intValue();
            int v5 = v3 < ((List)v1).size() - 1 ? ((List)v1).get(v3 + 1).intValue() : arg7.length;
            byte[] v5_1 = new byte[v5 - v4];
            System.arraycopy(arg7, v4, v5_1, 0, v5_1.length);
            v2_1[v3] = v5_1;
        }

        return v2_1;
    }
}

