package com.google.android.exoplayer2.extractor.ogg;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

final class VorbisUtil {
    public final class CodeBook {
        public final int dimensions;
        public final int entries;
        public final boolean isOrdered;
        public final long[] lengthMap;
        public final int lookupType;

        public CodeBook(int arg1, int arg2, long[] arg3, int arg4, boolean arg5) {
            super();
            this.dimensions = arg1;
            this.entries = arg2;
            this.lengthMap = arg3;
            this.lookupType = arg4;
            this.isOrdered = arg5;
        }
    }

    public final class CommentHeader {
        public final String[] comments;
        public final int length;
        public final String vendor;

        public CommentHeader(String arg1, String[] arg2, int arg3) {
            super();
            this.vendor = arg1;
            this.comments = arg2;
            this.length = arg3;
        }
    }

    public final class Mode {
        public final boolean blockFlag;
        public final int mapping;
        public final int transformType;
        public final int windowType;

        public Mode(boolean arg1, int arg2, int arg3, int arg4) {
            super();
            this.blockFlag = arg1;
            this.windowType = arg2;
            this.transformType = arg3;
            this.mapping = arg4;
        }
    }

    public final class VorbisIdHeader {
        public final int bitrateMax;
        public final int bitrateMin;
        public final int bitrateNominal;
        public final int blockSize0;
        public final int blockSize1;
        public final int channels;
        public final byte[] data;
        public final boolean framingFlag;
        public final long sampleRate;
        public final long version;

        public VorbisIdHeader(long arg1, int arg3, long arg4, int arg6, int arg7, int arg8, int arg9, int arg10, boolean arg11, byte[] arg12) {
            super();
            this.version = arg1;
            this.channels = arg3;
            this.sampleRate = arg4;
            this.bitrateMax = arg6;
            this.bitrateNominal = arg7;
            this.bitrateMin = arg8;
            this.blockSize0 = arg9;
            this.blockSize1 = arg10;
            this.framingFlag = arg11;
            this.data = arg12;
        }

        public int getApproximateBitrate() {
            int v0 = this.bitrateNominal == 0 ? (this.bitrateMin + this.bitrateMax) / 2 : this.bitrateNominal;
            return v0;
        }
    }

    private static final String TAG = "VorbisUtil";

    private VorbisUtil() {
        super();
    }

    public static int iLog(int arg1) {
        int v0 = 0;
        while(arg1 > 0) {
            ++v0;
            arg1 >>>= 1;
        }

        return v0;
    }

    private static long mapType1QuantValues(long arg2, long arg4) {
        double v4 = ((double)arg4);
        Double.isNaN(v4);
        return ((long)Math.floor(Math.pow(((double)arg2), 1 / v4)));
    }

    private static CodeBook readBook(VorbisBitArray arg14) {
        int v9_1;
        int v0 = 24;
        if(arg14.readBits(v0) == 5653314) {
            int v3 = arg14.readBits(16);
            int v4 = arg14.readBits(v0);
            long[] v5 = new long[v4];
            boolean v7 = arg14.readBit();
            long v0_1 = 0;
            int v2 = 5;
            int v6 = 0;
            if(!v7) {
                boolean v9 = arg14.readBit();
                while(v6 < v5.length) {
                    if(!v9) {
                        v5[v6] = ((long)(arg14.readBits(v2) + 1));
                    }
                    else if(arg14.readBit()) {
                        v5[v6] = ((long)(arg14.readBits(v2) + 1));
                    }
                    else {
                        v5[v6] = v0_1;
                    }

                    ++v6;
                }
            }
            else {
                v9_1 = arg14.readBits(v2) + 1;
                for(v2 = 0; v2 < v5.length; v2 = v11) {
                    int v10 = arg14.readBits(VorbisUtil.iLog(v4 - v2));
                    int v11 = v2;
                    for(v2 = 0; v2 < v10; ++v2) {
                        if(v11 >= v5.length) {
                            break;
                        }

                        v5[v11] = ((long)v9_1);
                        ++v11;
                    }

                    ++v9_1;
                }
            }

            v2 = 4;
            v6 = arg14.readBits(v2);
            v9_1 = 2;
            if(v6 <= v9_1) {
                if(v6 == 1 || v6 == v9_1) {
                    arg14.skipBits(32);
                    arg14.skipBits(32);
                    v2 = arg14.readBits(v2) + 1;
                    arg14.skipBits(1);
                    if(v6 != 1) {
                        v0_1 = (((long)v4)) * (((long)v3));
                    }
                    else if(v3 != 0) {
                        v0_1 = VorbisUtil.mapType1QuantValues(((long)v4), ((long)v3));
                    }

                    arg14.skipBits(((int)(v0_1 * (((long)v2)))));
                }

                return new CodeBook(v3, v4, v5, v6, v7);
            }

            StringBuilder v0_2 = new StringBuilder();
            v0_2.append("lookup type greater than 2 not decodable: ");
            v0_2.append(v6);
            throw new ParserException(v0_2.toString());
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("expected code book to start with [0x56, 0x43, 0x42] at ");
        v1.append(arg14.getPosition());
        throw new ParserException(v1.toString());
    }

    private static void readFloors(VorbisBitArray arg14) {
        int v11;
        int v12;
        int v0 = 6;
        int v1 = arg14.readBits(v0) + 1;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            int v5 = 16;
            int v6 = arg14.readBits(v5);
            int v7 = 4;
            int v8 = 8;
            switch(v6) {
                case 0: {
                    goto label_71;
                }
                case 1: {
                    goto label_21;
                }
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("floor type greater than 1 not decodable: ");
            v0_1.append(v6);
            throw new ParserException(v0_1.toString());
        label_21:
            v5 = arg14.readBits(5);
            int[] v9 = new int[v5];
            v6 = 0;
            int v10 = -1;
            while(v6 < v5) {
                v9[v6] = arg14.readBits(v7);
                if(v9[v6] > v10) {
                    v10 = v9[v6];
                }

                ++v6;
            }

            int[] v6_1 = new int[v10 + 1];
            for(v10 = 0; true; ++v10) {
                v12 = 2;
                if(v10 >= v6_1.length) {
                    break;
                }

                v6_1[v10] = arg14.readBits(3) + 1;
                v11 = arg14.readBits(v12);
                if(v11 > 0) {
                    arg14.skipBits(v8);
                }

                for(v12 = 0; v12 < 1 << v11; ++v12) {
                    arg14.skipBits(v8);
                }
            }

            arg14.skipBits(v12);
            v7 = arg14.readBits(v7);
            v8 = 0;
            v10 = 0;
            v11 = 0;
            while(true) {
                if(v8 >= v5) {
                    goto label_83;
                }

                v10 += v6_1[v9[v8]];
                while(v11 < v10) {
                    arg14.skipBits(v7);
                    ++v11;
                }

                ++v8;
            }

        label_71:
            arg14.skipBits(v8);
            arg14.skipBits(v5);
            arg14.skipBits(v5);
            arg14.skipBits(v0);
            arg14.skipBits(v8);
            v5 = arg14.readBits(v7) + 1;
            for(v6 = 0; v6 < v5; ++v6) {
                arg14.skipBits(v8);
            }

        label_83:
        }
    }

    private static void readMappings(int arg11, VorbisBitArray arg12) {
        int v0 = arg12.readBits(6) + 1;
        int v3;
        for(v3 = 0; true; ++v3) {
            if(v3 >= v0) {
                return;
            }

            int v4 = arg12.readBits(16);
            if(v4 != 0) {
                Log.e("VorbisUtil", "mapping type other than 0 not supported: " + v4);
            }
            else {
                int v5 = 4;
                v4 = arg12.readBit() ? arg12.readBits(v5) + 1 : 1;
                int v7 = 8;
                if(arg12.readBit()) {
                    int v6_1 = arg12.readBits(v7) + 1;
                    int v8;
                    for(v8 = 0; v8 < v6_1; ++v8) {
                        int v9 = arg11 - 1;
                        arg12.skipBits(VorbisUtil.iLog(v9));
                        arg12.skipBits(VorbisUtil.iLog(v9));
                    }
                }

                if(arg12.readBits(2) != 0) {
                    break;
                }

                if(v4 > 1) {
                    for(v6_1 = 0; v6_1 < arg11; ++v6_1) {
                        arg12.skipBits(v5);
                    }
                }

                for(v5 = 0; v5 < v4; ++v5) {
                    arg12.skipBits(v7);
                    arg12.skipBits(v7);
                    arg12.skipBits(v7);
                }
            }
        }

        throw new ParserException("to reserved bits must be zero after mapping coupling steps");
    }

    private static Mode[] readModes(VorbisBitArray arg8) {
        int v0 = arg8.readBits(6) + 1;
        Mode[] v1 = new Mode[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = new Mode(arg8.readBit(), arg8.readBits(16), arg8.readBits(16), arg8.readBits(8));
        }

        return v1;
    }

    private static void readResidues(VorbisBitArray arg12) {
        int v0 = 6;
        int v1 = arg12.readBits(v0) + 1;
        int v4;
        for(v4 = 0; true; ++v4) {
            if(v4 >= v1) {
                return;
            }

            if(arg12.readBits(16) > 2) {
                break;
            }

            arg12.skipBits(24);
            arg12.skipBits(24);
            arg12.skipBits(24);
            int v5 = arg12.readBits(v0) + 1;
            int v6 = 8;
            arg12.skipBits(v6);
            int[] v7 = new int[v5];
            int v8;
            for(v8 = 0; v8 < v5; ++v8) {
                int v9 = arg12.readBits(3);
                int v10 = arg12.readBit() ? arg12.readBits(5) : 0;
                v7[v8] = v10 * 8 + v9;
            }

            for(v8 = 0; v8 < v5; ++v8) {
                for(v9 = 0; v9 < v6; ++v9) {
                    if((v7[v8] & 1 << v9) != 0) {
                        arg12.skipBits(v6);
                    }
                }
            }
        }

        throw new ParserException("residueType greater than 2 is not decodable");
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray arg9) {
        int v0 = 0;
        VorbisUtil.verifyVorbisHeaderCapturePattern(3, arg9, false);
        String v1 = arg9.readString(((int)arg9.readLittleEndianUnsignedInt()));
        int v3 = 11 + v1.length();
        long v4 = arg9.readLittleEndianUnsignedInt();
        String[] v2 = new String[((int)v4)];
        v3 += 4;
        while((((long)v0)) < v4) {
            v2[v0] = arg9.readString(((int)arg9.readLittleEndianUnsignedInt()));
            v3 = v3 + 4 + v2[v0].length();
            ++v0;
        }

        if((arg9.readUnsignedByte() & 1) != 0) {
            return new CommentHeader(v1, v2, v3 + 1);
        }

        throw new ParserException("framing bit expected to be set");
    }

    public static VorbisIdHeader readVorbisIdentificationHeader(ParsableByteArray arg16) {
        ParsableByteArray v0 = arg16;
        VorbisUtil.verifyVorbisHeaderCapturePattern(1, v0, false);
        long v4 = arg16.readLittleEndianUnsignedInt();
        int v6 = arg16.readUnsignedByte();
        long v7 = arg16.readLittleEndianUnsignedInt();
        int v9 = arg16.readLittleEndianInt();
        int v10 = arg16.readLittleEndianInt();
        int v11 = arg16.readLittleEndianInt();
        int v3 = arg16.readUnsignedByte();
        int v12 = ((int)Math.pow(2, ((double)(v3 & 15))));
        int v13 = ((int)Math.pow(2, ((double)((v3 & 240) >> 4))));
        boolean v14 = (arg16.readUnsignedByte() & 1) > 0 ? true : false;
        return new VorbisIdHeader(v4, v6, v7, v9, v10, v11, v12, v13, v14, Arrays.copyOf(v0.data, arg16.limit()));
    }

    public static Mode[] readVorbisModes(ParsableByteArray arg4, int arg5) {
        int v0 = 0;
        VorbisUtil.verifyVorbisHeaderCapturePattern(5, arg4, false);
        int v1 = arg4.readUnsignedByte() + 1;
        VorbisBitArray v2 = new VorbisBitArray(arg4.data);
        v2.skipBits(arg4.getPosition() * 8);
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            VorbisUtil.readBook(v2);
        }

        v4 = v2.readBits(6) + 1;
        while(true) {
            if(v0 >= v4) {
                goto label_29;
            }

            if(v2.readBits(16) != 0) {
                break;
            }

            ++v0;
        }

        throw new ParserException("placeholder of time domain transforms not zeroed out");
    label_29:
        VorbisUtil.readFloors(v2);
        VorbisUtil.readResidues(v2);
        VorbisUtil.readMappings(arg5, v2);
        Mode[] v4_1 = VorbisUtil.readModes(v2);
        if(v2.readBit()) {
            return v4_1;
        }

        throw new ParserException("framing bit after modes not set as expected");
    }

    public static boolean verifyVorbisHeaderCapturePattern(int arg3, ParsableByteArray arg4, boolean arg5) {
        StringBuilder v5;
        if(arg4.bytesLeft() < 7) {
            if(arg5) {
                return 0;
            }

            v5 = new StringBuilder();
            v5.append("too short header: ");
            v5.append(arg4.bytesLeft());
            throw new ParserException(v5.toString());
        }

        if(arg4.readUnsignedByte() != arg3) {
            if(arg5) {
                return 0;
            }

            v5 = new StringBuilder();
            v5.append("expected header type ");
            v5.append(Integer.toHexString(arg3));
            throw new ParserException(v5.toString());
        }

        if(arg4.readUnsignedByte() == 118 && arg4.readUnsignedByte() == 111 && arg4.readUnsignedByte() == 114 && arg4.readUnsignedByte() == 98 && arg4.readUnsignedByte() == 105) {
            if(arg4.readUnsignedByte() != 115) {
            }
            else {
                return 1;
            }
        }

        if(arg5) {
            return 0;
        }

        throw new ParserException("expected characters \'vorbis\'");
    }
}

