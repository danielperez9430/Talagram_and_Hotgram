package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.HevcConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class AtomParsers {
    final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray arg1, ParsableByteArray arg2, boolean arg3) {
            super();
            this.stsc = arg1;
            this.chunkOffsets = arg2;
            this.chunkOffsetsAreLongs = arg3;
            arg2.setPosition(12);
            this.length = arg2.readUnsignedIntToInt();
            arg1.setPosition(12);
            this.remainingSamplesPerChunkChanges = arg1.readUnsignedIntToInt();
            boolean v2 = true;
            if(arg1.readInt() == 1) {
            }
            else {
                v2 = false;
            }

            Assertions.checkState(v2, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int v0 = this.index + 1;
            this.index = v0;
            if(v0 == this.length) {
                return 0;
            }

            long v2 = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            this.offset = v2;
            if(this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                v0 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = v0;
                v0 = v0 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
                this.nextSamplesPerChunkChangeIndex = v0;
            }

            return 1;
        }
    }

    interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int arg1) {
            super();
            this.trackEncryptionBoxes = new TrackEncryptionBox[arg1];
            this.requiredSampleTransformation = 0;
        }
    }

    final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(LeafAtom arg2) {
            super();
            this.data = arg2.data;
            this.data.setPosition(12);
            this.fixedSampleSize = this.data.readUnsignedIntToInt();
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public boolean isFixedSampleSize() {
            boolean v0 = this.fixedSampleSize != 0 ? true : false;
            return v0;
        }

        public int readNextSampleSize() {
            int v0 = this.fixedSampleSize == 0 ? this.data.readUnsignedIntToInt() : this.fixedSampleSize;
            return v0;
        }
    }

    final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public Stz2SampleSizeBox(LeafAtom arg2) {
            super();
            this.data = arg2.data;
            this.data.setPosition(12);
            this.fieldSize = this.data.readUnsignedIntToInt() & 255;
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public boolean isFixedSampleSize() {
            return 0;
        }

        public int readNextSampleSize() {
            if(this.fieldSize == 8) {
                return this.data.readUnsignedByte();
            }

            if(this.fieldSize == 16) {
                return this.data.readUnsignedShort();
            }

            int v0 = this.sampleIndex;
            this.sampleIndex = v0 + 1;
            if(v0 % 2 == 0) {
                this.currentByte = this.data.readUnsignedByte();
                return (this.currentByte & 240) >> 4;
            }

            return this.currentByte & 15;
        }
    }

    final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int arg1, long arg2, int arg4) {
            super();
            this.id = arg1;
            this.duration = arg2;
            this.rotationDegrees = arg4;
        }

        static long access$000(TkhdData arg2) {
            return arg2.duration;
        }

        static int access$100(TkhdData arg0) {
            return arg0.id;
        }

        static int access$200(TkhdData arg0) {
            return arg0.rotationDegrees;
        }
    }

    public final class UnhandledEditListException extends ParserException {
        public UnhandledEditListException() {
            super();
        }
    }

    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 3;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_clcp;
    private static final int TYPE_meta;
    private static final int TYPE_sbtl;
    private static final int TYPE_soun;
    private static final int TYPE_subt;
    private static final int TYPE_text;
    private static final int TYPE_vide;

    static {
        AtomParsers.TYPE_vide = Util.getIntegerCodeForString("vide");
        AtomParsers.TYPE_soun = Util.getIntegerCodeForString("soun");
        AtomParsers.TYPE_text = Util.getIntegerCodeForString("text");
        AtomParsers.TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
        AtomParsers.TYPE_subt = Util.getIntegerCodeForString("subt");
        AtomParsers.TYPE_clcp = Util.getIntegerCodeForString("clcp");
        AtomParsers.TYPE_meta = Util.getIntegerCodeForString("meta");
    }

    private AtomParsers() {
        super();
    }

    private static boolean canApplyEditWithGaplessInfo(long[] arg7, long arg8, long arg10, long arg12) {
        boolean v1 = true;
        int v0 = arg7.length - 1;
        int v4 = Util.constrainValue(3, 0, v0);
        v0 = Util.constrainValue(arg7.length - 3, 0, v0);
        if(arg7[0] > arg10 || arg10 >= arg7[v4] || arg7[v0] >= arg12 || arg12 > arg8) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    private static int findEsdsPosition(ParsableByteArray arg4, int arg5, int arg6) {
        int v0;
        for(v0 = arg4.getPosition(); v0 - arg5 < arg6; v0 += v1) {
            arg4.setPosition(v0);
            int v1 = arg4.readInt();
            boolean v2 = v1 > 0 ? true : false;
            Assertions.checkArgument(v2, "childAtomSize should be positive");
            if(arg4.readInt() == Atom.TYPE_esds) {
                return v0;
            }
        }

        return -1;
    }

    private static void parseAudioSampleEntry(ParsableByteArray arg26, int arg27, int arg28, int arg29, int arg30, String arg31, boolean arg32, DrmInitData arg33, StsdData arg34, int arg35) {
        String v4_2;
        int v25;
        int v23;
        StsdData v2_1;
        DrmInitData v19;
        String v24;
        Format v3_4;
        String v3_1;
        int v7;
        ParsableByteArray v0 = arg26;
        int v1 = arg28;
        int v2 = arg29;
        String v14 = arg31;
        DrmInitData v3 = arg33;
        StsdData v13 = arg34;
        int v5 = 8;
        v0.setPosition(v1 + 8 + v5);
        int v4 = 6;
        if(arg32) {
            v5 = arg26.readUnsignedShort();
            v0.skipBytes(v4);
        }
        else {
            v0.skipBytes(v5);
            v5 = 0;
        }

        int v6 = 16;
        int v11 = 2;
        if(v5 == 0 || v5 == 1) {
            v7 = arg26.readUnsignedShort();
            v0.skipBytes(v4);
            v4 = arg26.readUnsignedFixedPoint1616();
            if(v5 == 1) {
                v0.skipBytes(v6);
            }
        }
        else if(v5 == v11) {
            v0.skipBytes(v6);
            v4 = ((int)Math.round(arg26.readDouble()));
            v5 = arg26.readUnsignedIntToInt();
            v0.skipBytes(20);
            v7 = v5;
        }
        else {
            return;
        }

        v5 = arg26.getPosition();
        DrmInitData v15 = null;
        int v8 = arg27;
        if(v8 == Atom.TYPE_enca) {
            Pair v6_1 = AtomParsers.parseSampleEntryEncryptionData(v0, v1, v2);
            if(v6_1 != null) {
                v8 = v6_1.first.intValue();
                v3 = v3 == null ? v15 : v3.copyWithSchemeType(v6_1.second.schemeType);
                v13.trackEncryptionBoxes[arg35] = v6_1.second;
            }

            v0.setPosition(v5);
        }

        DrmInitData v9 = v3;
        if(v8 == Atom.TYPE_ac_3) {
            v3_1 = "audio/ac3";
        }
        else if(v8 == Atom.TYPE_ec_3) {
            v3_1 = "audio/eac3";
        }
        else if(v8 == Atom.TYPE_dtsc) {
            v3_1 = "audio/vnd.dts";
        }
        else {
            if(v8 != Atom.TYPE_dtsh) {
                if(v8 == Atom.TYPE_dtsl) {
                }
                else if(v8 == Atom.TYPE_dtse) {
                    v3_1 = "audio/vnd.dts.hd;profile=lbr";
                    goto label_107;
                }
                else {
                    if(v8 == Atom.TYPE_samr) {
                        v3_1 = "audio/3gpp";
                    }
                    else if(v8 == Atom.TYPE_sawb) {
                        v3_1 = "audio/amr-wb";
                    }
                    else {
                        if(v8 != Atom.TYPE_lpcm) {
                            if(v8 == Atom.TYPE_sowt) {
                            }
                            else {
                                if(v8 == Atom.TYPE__mp3) {
                                    v3_1 = "audio/mpeg";
                                }
                                else if(v8 == Atom.TYPE_alac) {
                                    v3_1 = "audio/alac";
                                }
                                else {
                                    v3_1 = ((String)v15);
                                }

                                goto label_107;
                            }
                        }

                        v3_1 = "audio/raw";
                    }

                    goto label_107;
                }
            }

            v3_1 = "audio/vnd.dts.hd";
        }

    label_107:
        int v17 = v4;
        v8 = v5;
        int v16 = v7;
        byte[] v18 = ((byte[])v15);
        String v7_1 = v3_1;
        while(true) {
            v4 = -1;
            if(v8 - v1 >= v2) {
                break;
            }

            v0.setPosition(v8);
            v6 = arg26.readInt();
            boolean v3_2 = v6 > 0 ? true : false;
            Assertions.checkArgument(v3_2, "childAtomSize should be positive");
            int v3_3 = arg26.readInt();
            if(v3_3 != Atom.TYPE_esds) {
                if((arg32) && v3_3 == Atom.TYPE_wave) {
                    goto label_200;
                }

                if(v3_3 == Atom.TYPE_dac3) {
                    v0.setPosition(v8 + 8);
                    v3_4 = Ac3Util.parseAc3AnnexFFormat(v0, Integer.toString(arg30), v14, v9);
                }
                else if(v3_3 == Atom.TYPE_dec3) {
                    v0.setPosition(v8 + 8);
                    v3_4 = Ac3Util.parseEAc3AnnexFFormat(v0, Integer.toString(arg30), v14, v9);
                }
                else {
                    goto label_153;
                }

                v13.format = v3_4;
                v5 = v6;
                v24 = v7_1;
                v6 = v8;
                v19 = v9;
                v2_1 = v13;
                goto label_229;
            label_153:
                if(v3_3 == Atom.TYPE_ddts) {
                    v23 = v6;
                    v24 = v7_1;
                    v25 = v8;
                    v19 = v9;
                    v2_1 = v13;
                    v2_1.format = Format.createAudioSampleFormat(Integer.toString(arg30), v7_1, null, -1, -1, v16, v17, null, v19, 0, arg31);
                }
                else {
                    v23 = v6;
                    v24 = v7_1;
                    v25 = v8;
                    v19 = v9;
                    v2_1 = v13;
                    if(v3_3 == Atom.TYPE_alac) {
                        v5 = v23;
                        byte[] v3_5 = new byte[v5];
                        v6 = v25;
                        v0.setPosition(v6);
                        v0.readBytes(v3_5, 0, v5);
                        v18 = v3_5;
                        goto label_229;
                    }
                }

                v5 = v23;
                v6 = v25;
            }
            else {
            label_200:
                v5 = v6;
                v24 = v7_1;
                v6 = v8;
                v19 = v9;
                v2_1 = v13;
                v8 = v3_3 == Atom.TYPE_esds ? v6 : AtomParsers.findEsdsPosition(v0, v6, v5);
                if(v8 != v4) {
                    Pair v3_6 = AtomParsers.parseEsdsFromParent(v0, v8);
                    Object v4_1 = v3_6.first;
                    Object v18_1 = v3_6.second;
                    if("audio/mp4a-latm".equals(v4_1)) {
                        v3_6 = CodecSpecificDataUtil.parseAacAudioSpecificConfig(((byte[])v18_1));
                        v17 = v3_6.first.intValue();
                        v16 = v3_6.second.intValue();
                    }
                }
                else {
                    v4_2 = v24;
                }

                Object v24_1 = v4_2;
            }

        label_229:
            v8 = v6 + v5;
            v13 = v2_1;
            v9 = v19;
            v7_1 = v24;
            v2 = arg29;
        }

        v24 = v7_1;
        v19 = v9;
        v2_1 = v13;
        if(v2_1.format == null) {
            v3_1 = v24;
            if(v3_1 != null) {
                v7 = "audio/raw".equals(v3_1) ? 2 : -1;
                String v0_1 = Integer.toString(arg30);
                v4_2 = null;
                v5 = -1;
                v6 = -1;
                List v8_1 = v18 == null ? ((List)v15) : Collections.singletonList(v18);
                v2_1.format = Format.createAudioSampleFormat(v0_1, v3_1, v4_2, v5, v6, v16, v17, v7, v8_1, v19, 0, arg31);
            }
        }
    }

    static Pair parseCommonEncryptionSinfFromParent(ParsableByteArray arg11, int arg12, int arg13) {
        int v0 = arg12 + 8;
        int v1 = -1;
        Pair v2 = null;
        Object v4 = v2;
        Integer v5 = ((Integer)v4);
        int v6 = -1;
        int v7 = 0;
        while(v0 - arg12 < arg13) {
            arg11.setPosition(v0);
            int v8 = arg11.readInt();
            int v9 = arg11.readInt();
            if(v9 == Atom.TYPE_frma) {
                v5 = Integer.valueOf(arg11.readInt());
            }
            else if(v9 == Atom.TYPE_schm) {
                arg11.skipBytes(4);
                String v4_1 = arg11.readString(4);
            }
            else if(v9 == Atom.TYPE_schi) {
                v6 = v0;
                v7 = v8;
            }

            v0 += v8;
        }

        if(!"cenc".equals(v4) && !"cbc1".equals(v4) && !"cens".equals(v4)) {
            if("cbcs".equals(v4)) {
            }
            else {
                return v2;
            }
        }

        boolean v12 = true;
        boolean v13 = v5 != null ? true : false;
        Assertions.checkArgument(v13, "frma atom is mandatory");
        v13 = v6 != v1 ? true : false;
        Assertions.checkArgument(v13, "schi atom is mandatory");
        TrackEncryptionBox v11 = AtomParsers.parseSchiFromParent(arg11, v6, v7, ((String)v4));
        if(v11 != null) {
        }
        else {
            v12 = false;
        }

        Assertions.checkArgument(v12, "tenc atom is mandatory");
        return Pair.create(v5, v11);
    }

    private static Pair parseEdts(ContainerAtom arg8) {
        if(arg8 != null) {
            LeafAtom v8 = arg8.getLeafAtomOfType(Atom.TYPE_elst);
            if(v8 == null) {
            }
            else {
                ParsableByteArray v8_1 = v8.data;
                v8_1.setPosition(8);
                int v0 = Atom.parseFullAtomVersion(v8_1.readInt());
                int v1 = v8_1.readUnsignedIntToInt();
                long[] v2 = new long[v1];
                long[] v3 = new long[v1];
                int v4 = 0;
                while(true) {
                    if(v4 < v1) {
                        long v6 = v0 == 1 ? v8_1.readUnsignedLongToLong() : v8_1.readUnsignedInt();
                        v2[v4] = v6;
                        v6 = v0 == 1 ? v8_1.readLong() : ((long)v8_1.readInt());
                        v3[v4] = v6;
                        if(v8_1.readShort() != 1) {
                            break;
                        }

                        v8_1.skipBytes(2);
                        ++v4;
                        continue;
                    }
                    else {
                        goto label_37;
                    }
                }

                throw new IllegalArgumentException("Unsupported media rate.");
            label_37:
                return Pair.create(v2, v3);
            }
        }

        return Pair.create(null, null);
    }

    private static Pair parseEsdsFromParent(ParsableByteArray arg3, int arg4) {
        arg3.setPosition(arg4 + 12);
        arg3.skipBytes(1);
        AtomParsers.parseExpandableClassSize(arg3);
        int v0 = 2;
        arg3.skipBytes(v0);
        int v1 = arg3.readUnsignedByte();
        if((v1 & 128) != 0) {
            arg3.skipBytes(v0);
        }

        if((v1 & 64) != 0) {
            arg3.skipBytes(arg3.readUnsignedShort());
        }

        if((v1 & 32) != 0) {
            arg3.skipBytes(v0);
        }

        arg3.skipBytes(1);
        AtomParsers.parseExpandableClassSize(arg3);
        String v0_1 = MimeTypes.getMimeTypeFromMp4ObjectType(arg3.readUnsignedByte());
        if(!"audio/mpeg".equals(v0_1) && !"audio/vnd.dts".equals(v0_1)) {
            if("audio/vnd.dts.hd".equals(v0_1)) {
            }
            else {
                arg3.skipBytes(12);
                arg3.skipBytes(1);
                arg4 = AtomParsers.parseExpandableClassSize(arg3);
                byte[] v1_1 = new byte[arg4];
                arg3.readBytes(v1_1, 0, arg4);
                return Pair.create(v0_1, v1_1);
            }
        }

        return Pair.create(v0_1, null);
    }

    private static int parseExpandableClassSize(ParsableByteArray arg3) {
        int v0 = arg3.readUnsignedByte();
        int v1;
        for(v1 = v0 & 127; (v0 & 128) == 128; v1 = v1 << 7 | v0 & 127) {
            v0 = arg3.readUnsignedByte();
        }

        return v1;
    }

    private static int parseHdlr(ParsableByteArray arg1) {
        arg1.setPosition(16);
        int v1 = arg1.readInt();
        if(v1 == AtomParsers.TYPE_soun) {
            return 1;
        }

        if(v1 == AtomParsers.TYPE_vide) {
            return 2;
        }

        if(v1 != AtomParsers.TYPE_text && v1 != AtomParsers.TYPE_sbtl && v1 != AtomParsers.TYPE_subt) {
            if(v1 == AtomParsers.TYPE_clcp) {
            }
            else if(v1 == AtomParsers.TYPE_meta) {
                return 4;
            }
            else {
                return -1;
            }
        }

        return 3;
    }

    private static Metadata parseIlst(ParsableByteArray arg2, int arg3) {
        arg2.skipBytes(8);
        ArrayList v0 = new ArrayList();
        while(arg2.getPosition() < arg3) {
            Entry v1 = MetadataUtil.parseIlstElement(arg2);
            if(v1 == null) {
                continue;
            }

            v0.add(v1);
        }

        Metadata v2 = v0.isEmpty() ? null : new Metadata(((List)v0));
        return v2;
    }

    private static Pair parseMdhd(ParsableByteArray arg4) {
        int v0 = 8;
        arg4.setPosition(v0);
        int v1 = Atom.parseFullAtomVersion(arg4.readInt());
        int v2 = v1 == 0 ? 8 : 16;
        arg4.skipBytes(v2);
        long v2_1 = arg4.readUnsignedInt();
        if(v1 == 0) {
            v0 = 4;
        }

        arg4.skipBytes(v0);
        int v4 = arg4.readUnsignedShort();
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("");
        v0_1.append(((char)((v4 >> 10 & 31) + 96)));
        v0_1.append(((char)((v4 >> 5 & 31) + 96)));
        v0_1.append(((char)((v4 & 31) + 96)));
        return Pair.create(Long.valueOf(v2_1), v0_1.toString());
    }

    private static Metadata parseMetaAtom(ParsableByteArray arg4, int arg5) {
        arg4.skipBytes(12);
        while(arg4.getPosition() < arg5) {
            int v0 = arg4.getPosition();
            int v1 = arg4.readInt();
            if(arg4.readInt() == Atom.TYPE_ilst) {
                arg4.setPosition(v0);
                return AtomParsers.parseIlst(arg4, v0 + v1);
            }

            arg4.skipBytes(v1 - 8);
        }

        return null;
    }

    private static long parseMvhd(ParsableByteArray arg2) {
        int v0 = 8;
        arg2.setPosition(v0);
        if(Atom.parseFullAtomVersion(arg2.readInt()) == 0) {
        }
        else {
            v0 = 16;
        }

        arg2.skipBytes(v0);
        return arg2.readUnsignedInt();
    }

    private static float parsePaspFromParent(ParsableByteArray arg0, int arg1) {
        arg0.setPosition(arg1 + 8);
        return (((float)arg0.readUnsignedIntToInt())) / (((float)arg0.readUnsignedIntToInt()));
    }

    private static byte[] parseProjFromParent(ParsableByteArray arg4, int arg5, int arg6) {
        int v0;
        for(v0 = arg5 + 8; v0 - arg5 < arg6; v0 += v1) {
            arg4.setPosition(v0);
            int v1 = arg4.readInt();
            if(arg4.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(arg4.data, v0, v1 + v0);
            }
        }

        return null;
    }

    private static Pair parseSampleEntryEncryptionData(ParsableByteArray arg4, int arg5, int arg6) {
        int v0;
        for(v0 = arg4.getPosition(); v0 - arg5 < arg6; v0 += v1) {
            arg4.setPosition(v0);
            int v1 = arg4.readInt();
            boolean v2 = v1 > 0 ? true : false;
            Assertions.checkArgument(v2, "childAtomSize should be positive");
            if(arg4.readInt() == Atom.TYPE_sinf) {
                Pair v2_1 = AtomParsers.parseCommonEncryptionSinfFromParent(arg4, v0, v1);
                if(v2_1 != null) {
                    return v2_1;
                }
            }
        }

        return null;
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray arg11, int arg12, int arg13, String arg14) {
        int v9;
        int v8;
        byte[] v2;
        int v0;
        for(v0 = arg12 + 8; true; v0 += v1) {
            v2 = null;
            if(v0 - arg12 >= arg13) {
                break;
            }

            arg11.setPosition(v0);
            int v1 = arg11.readInt();
            if(arg11.readInt() == Atom.TYPE_tenc) {
                arg12 = Atom.parseFullAtomVersion(arg11.readInt());
                arg11.skipBytes(1);
                if(arg12 == 0) {
                    arg11.skipBytes(1);
                    v8 = 0;
                    v9 = 0;
                }
                else {
                    arg12 = arg11.readUnsignedByte();
                    v9 = arg12 & 15;
                    v8 = (arg12 & 240) >> 4;
                }

                boolean v4 = arg11.readUnsignedByte() == 1 ? true : false;
                int v6 = arg11.readUnsignedByte();
                byte[] v7 = new byte[16];
                arg11.readBytes(v7, 0, v7.length);
                if((v4) && v6 == 0) {
                    arg12 = arg11.readUnsignedByte();
                    v2 = new byte[arg12];
                    arg11.readBytes(v2, 0, arg12);
                }

                return new TrackEncryptionBox(v4, arg14, v6, v7, v8, v9, v2);
            }
        }

        return ((TrackEncryptionBox)v2);
    }

    public static TrackSampleTable parseStbl(Track arg52, ContainerAtom arg53, GaplessInfoHolder arg54) {
        int[] v9_4;
        long[] v45;
        int v42;
        long[] v41;
        int v40;
        int[] v39;
        long v13_1;
        long v12_2;
        long v37;
        int[] v2_4;
        long[] v6_4;
        int[] v7_1;
        long[] v2_3;
        int[] v4_3;
        int v36;
        int v35;
        int v4_2;
        long[] v34;
        int v29;
        long v21_1;
        long v14_1;
        int v10_1;
        int[] v12_1;
        int v15;
        boolean v8;
        Stz2SampleSizeBox v4_1;
        StszSampleSizeBox v4;
        Track v1 = arg52;
        ContainerAtom v0 = arg53;
        LeafAtom v3 = v0.getLeafAtomOfType(Atom.TYPE_stsz);
        if(v3 != null) {
            v4 = new StszSampleSizeBox(v3);
        }
        else {
            v3 = v0.getLeafAtomOfType(Atom.TYPE_stz2);
            if(v3 != null) {
                v4_1 = new Stz2SampleSizeBox(v3);
            }
            else {
                goto label_610;
            }
        }

        int v3_1 = ((SampleSizeBox)v4_1).getSampleCount();
        if(v3_1 == 0) {
            return new TrackSampleTable(arg52, new long[0], new int[0], 0, new long[0], new int[0], -9223372036854775807L);
        }

        LeafAtom v6 = v0.getLeafAtomOfType(Atom.TYPE_stco);
        if(v6 == null) {
            v6 = v0.getLeafAtomOfType(Atom.TYPE_co64);
            v8 = true;
        }
        else {
            v8 = false;
        }

        ParsableByteArray v6_1 = v6.data;
        ParsableByteArray v9 = v0.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray v10 = v0.getLeafAtomOfType(Atom.TYPE_stts).data;
        LeafAtom v11 = v0.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray v12 = null;
        ParsableByteArray v11_1 = v11 != null ? v11.data : v12;
        LeafAtom v0_1 = v0.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray v0_2 = v0_1 != null ? v0_1.data : v12;
        ChunkIterator v13 = new ChunkIterator(v9, v6_1, v8);
        int v6_2 = 12;
        v10.setPosition(v6_2);
        int v8_1 = v10.readUnsignedIntToInt() - 1;
        int v9_1 = v10.readUnsignedIntToInt();
        int v14 = v10.readUnsignedIntToInt();
        if(v0_2 != null) {
            v0_2.setPosition(v6_2);
            v15 = v0_2.readUnsignedIntToInt();
        }
        else {
            v15 = 0;
        }

        int v16 = -1;
        if(v11_1 != null) {
            v11_1.setPosition(v6_2);
            v6_2 = v11_1.readUnsignedIntToInt();
            if(v6_2 > 0) {
                v16 = v11_1.readUnsignedIntToInt() - 1;
            }
            else {
                v11_1 = v12;
            }
        }
        else {
            v6_2 = 0;
        }

        int v5 = !((SampleSizeBox)v4_1).isFixedSampleSize() || !"audio/raw".equals(v1.format.sampleMimeType) || v8_1 != 0 || v15 != 0 || v6_2 != 0 ? 0 : 1;
        long v18 = 0;
        if(v5 == 0) {
            long[] v5_1 = new long[v3_1];
            v12_1 = new int[v3_1];
            long[] v7 = new long[v3_1];
            int v21 = v6_2;
            int[] v6_3 = new int[v3_1];
            int v28 = v8_1;
            int v27 = v9_1;
            ParsableByteArray v26 = v10;
            v10_1 = v14;
            int v23 = v15;
            int v2 = v16;
            v14_1 = v18;
            v9_1 = v21;
            int v1_1 = 0;
            v8_1 = 0;
            v16 = 0;
            int v24 = 0;
            int v25 = 0;
            v21_1 = v14_1;
            while(v8_1 < v3_1) {
                while(v24 == 0) {
                    Assertions.checkState(v13.moveNext());
                    long v31 = v13.offset;
                    v24 = v13.numSamples;
                    v9_1 = v9_1;
                    v10_1 = v10_1;
                    v21_1 = v31;
                }

                v29 = v9_1;
                int v30 = v10_1;
                if(v0_2 != null) {
                    while(v16 == 0) {
                        if(v23 <= 0) {
                            break;
                        }

                        v16 = v0_2.readUnsignedIntToInt();
                        v25 = v0_2.readInt();
                        --v23;
                    }

                    --v16;
                }

                v9_1 = v25;
                v5_1[v8_1] = v21_1;
                v12_1[v8_1] = ((SampleSizeBox)v4_1).readNextSampleSize();
                if(v12_1[v8_1] > v1_1) {
                    v1_1 = v12_1[v8_1];
                }

                StszSampleSizeBox v33 = ((StszSampleSizeBox)v4_1);
                v34 = v5_1;
                v7[v8_1] = (((long)v9_1)) + v14_1;
                v4_2 = v11_1 == null ? 1 : 0;
                v6_3[v8_1] = v4_2;
                if(v8_1 == v2) {
                    v6_3[v8_1] = 1;
                    v5 = v29 - 1;
                    if(v5 > 0) {
                        v2 = v11_1.readUnsignedIntToInt() - 1;
                    }

                    v35 = v2;
                    v36 = v5;
                }
                else {
                    v35 = v2;
                    v36 = v29;
                }

                v2 = v30;
                v14_1 += ((long)v2);
                --v27;
                if(v27 == 0) {
                    v4_2 = v28;
                    if(v4_2 > 0) {
                        v28 = v4_2 - 1;
                        v27 = v26.readUnsignedIntToInt();
                        v10_1 = v26.readInt();
                    }
                    else {
                        goto label_186;
                    }
                }
                else {
                    v4_2 = v28;
                label_186:
                    v10_1 = v2;
                    v28 = v4_2;
                }

                v21_1 += ((long)v12_1[v8_1]);
                --v24;
                ++v8_1;
                v25 = v9_1;
                v4 = v33;
                v5_1 = v34;
                v2 = v35;
                v9_1 = v36;
            }

            v34 = v5_1;
            v29 = v9_1;
            v4_2 = v28;
            v14_1 += ((long)v25);
            boolean v2_1 = v16 == 0 ? true : false;
            Assertions.checkArgument(v2_1);
            while(v23 > 0) {
                v2_1 = v0_2.readUnsignedIntToInt() == 0 ? true : false;
                Assertions.checkArgument(v2_1);
                v0_2.readInt();
                --v23;
            }

            if(v29 != 0 || v27 != 0 || v24 != 0 || v4_2 != 0) {
                StringBuilder v2_2 = new StringBuilder();
                v2_2.append("Inconsistent stbl box for track ");
                v5 = v1_1;
                v1 = arg52;
                v2_2.append(v1.id);
                v2_2.append(": remainingSynchronizationSamples ");
                v2_2.append(v29);
                v2_2.append(", remainingSamplesAtTimestampDelta ");
                v2_2.append(v27);
                v2_2.append(", remainingSamplesInChunk ");
                v2_2.append(v24);
                v2_2.append(", remainingTimestampDeltaChanges ");
                v2_2.append(v4_2);
                Log.w("AtomParsers", v2_2.toString());
            }
            else {
                v5 = v1_1;
                v1 = arg52;
            }

            v4_3 = v12_1;
            v2_3 = v34;
            long[] v50 = v7;
            v7_1 = v6_3;
            v6_4 = v50;
        }
        else {
            long[] v0_3 = new long[v13.length];
            v2_4 = new int[v13.length];
            while(v13.moveNext()) {
                v0_3[v13.index] = v13.offset;
                v2_4[v13.index] = v13.numSamples;
            }

            Results v0_4 = FixedSampleSizeRechunker.rechunk(Util.getPcmFrameSize(v1.format.pcmEncoding, v1.format.channelCount), v0_3, v2_4, ((long)v14));
            v2_3 = v0_4.offsets;
            v4_3 = v0_4.sizes;
            v5 = v0_4.maximumSize;
            v6_4 = v0_4.timestamps;
            v7_1 = v0_4.flags;
            v14_1 = v0_4.duration;
        }

        long v8_2 = Util.scaleLargeTimestamp(v14_1, 1000000, v1.timescale);
        if(v1.editListDurations != null) {
            if(arg54.hasGaplessInfo()) {
            }
            else {
                if(v1.editListDurations.length != 1 || v1.type != 1 || v6_4.length < 2) {
                label_364:
                    v37 = v14_1;
                }
                else {
                    long v28_1 = v1.editListMediaTimes[0];
                    long v10_2 = v28_1 + Util.scaleLargeTimestamp(v1.editListDurations[0], v1.timescale, v1.movieTimescale);
                    if(AtomParsers.canApplyEditWithGaplessInfo(v6_4, v14_1, v28_1, v10_2)) {
                        v21_1 = v14_1 - v10_2;
                        v10_2 = Util.scaleLargeTimestamp(v28_1 - v6_4[0], ((long)v1.format.sampleRate), v1.timescale);
                        v37 = v14_1;
                        v12_2 = Util.scaleLargeTimestamp(v21_1, ((long)v1.format.sampleRate), v1.timescale);
                        if(v10_2 == v18 && v12_2 == v18) {
                            goto label_365;
                        }

                        v14_1 = 2147483647;
                        if(v10_2 > v14_1) {
                            goto label_365;
                        }

                        if(v12_2 > v14_1) {
                            goto label_365;
                        }

                        arg54.encoderDelay = ((int)v10_2);
                        arg54.encoderPadding = ((int)v12_2);
                        Util.scaleLargeTimestampsInPlace(v6_4, 1000000, v1.timescale);
                        return new TrackSampleTable(arg52, v2_3, v4_3, v5, v6_4, v7_1, v8_2);
                    }
                    else {
                        goto label_364;
                    }
                }

            label_365:
                if(v1.editListDurations.length == 1 && v1.editListDurations[0] == v18) {
                    long v9_2 = v1.editListMediaTimes[0];
                    int v0_5;
                    for(v0_5 = 0; v0_5 < v6_4.length; ++v0_5) {
                        v6_4[v0_5] = Util.scaleLargeTimestamp(v6_4[v0_5] - v9_2, 1000000, v1.timescale);
                    }

                    return new TrackSampleTable(arg52, v2_3, v4_3, v5, v6_4, v7_1, Util.scaleLargeTimestamp(v37 - v9_2, 1000000, v1.timescale));
                }

                boolean v0_6 = v1.type == 1 ? true : false;
                v8_1 = 0;
                v9_1 = 0;
                v10_1 = 0;
                int v11_2 = 0;
                while(true) {
                    v13_1 = -1;
                    if(v8_1 >= v1.editListDurations.length) {
                        break;
                    }

                    v39 = v4_3;
                    v40 = v5;
                    long v4_4 = v1.editListMediaTimes[v8_1];
                    if(v4_4 != v13_1) {
                        v12_2 = Util.scaleLargeTimestamp(v1.editListDurations[v8_1], v1.timescale, v1.movieTimescale);
                        v15 = Util.binarySearchCeil(v6_4, v4_4, true, true);
                        v4_2 = Util.binarySearchCeil(v6_4, v4_4 + v12_2, v0_6, false);
                        v10_1 += v4_2 - v15;
                        v5 = v11_2 != v15 ? 1 : 0;
                        v11_2 = v4_2;
                        v9_1 |= v5;
                    }

                    ++v8_1;
                    v4_3 = v39;
                    v5 = v40;
                }

                v39 = v4_3;
                v40 = v5;
                v3_1 = v10_1 != v3_1 ? 1 : 0;
                v3_1 |= v9_1;
                long[] v4_5 = v3_1 != 0 ? new long[v10_1] : v2_3;
                int[] v5_2 = v3_1 != 0 ? new int[v10_1] : v39;
                if(v3_1 != 0) {
                    v40 = 0;
                }

                int[] v8_3 = v3_1 != 0 ? new int[v10_1] : v7_1;
                long[] v9_3 = new long[v10_1];
                v10_1 = 0;
                v11_2 = 0;
                while(v10_1 < v1.editListDurations.length) {
                    v13_1 = v1.editListMediaTimes[v10_1];
                    long v15_1 = v1.editListDurations[v10_1];
                    if(v13_1 != -1) {
                        v41 = v9_3;
                        v42 = v10_1;
                        int[] v43 = v7_1;
                        int[] v44 = v8_3;
                        long v7_2 = Util.scaleLargeTimestamp(v15_1, v1.timescale, v1.movieTimescale) + v13_1;
                        v10_1 = Util.binarySearchCeil(v6_4, v13_1, true, true);
                        int v7_3 = Util.binarySearchCeil(v6_4, v7_2, v0_6, false);
                        if(v3_1 != 0) {
                            v8_1 = v7_3 - v10_1;
                            System.arraycopy(v2_3, v10_1, v4_5, v11_2, v8_1);
                            v12_1 = v39;
                            System.arraycopy(v12_1, v10_1, v5_2, v11_2, v8_1);
                            v45 = v2_3;
                            v9_4 = v43;
                            v2_4 = v44;
                            System.arraycopy(v9_4, v10_1, v2_4, v11_2, v8_1);
                        }
                        else {
                            v45 = v2_3;
                            v12_1 = v39;
                            v9_4 = v43;
                            v2_4 = v44;
                        }

                        if(v10_1 < v7_3) {
                            if((v2_4[v11_2] & 1) != 0) {
                            }
                            else {
                                Log.w("AtomParsers", "Ignoring edit list: edit does not start with a sync sample.");
                                throw new UnhandledEditListException();
                            }
                        }

                        v8_1 = v40;
                        while(v10_1 < v7_3) {
                            int v46 = v7_3;
                            int v47 = v8_1;
                            long v48 = v13_1;
                            v41[v11_2] = Util.scaleLargeTimestamp(v18, 1000000, v1.movieTimescale) + Util.scaleLargeTimestamp(v6_4[v10_1] - v13_1, 1000000, v1.timescale);
                            if(v3_1 != 0) {
                                v8_1 = v47;
                                if(v5_2[v11_2] > v8_1) {
                                    v8_1 = v12_1[v10_1];
                                }
                            }
                            else {
                                v8_1 = v47;
                            }

                            ++v11_2;
                            ++v10_1;
                            v7_3 = v46;
                            v13_1 = v48;
                        }

                        v40 = v8_1;
                    }
                    else {
                        v45 = v2_3;
                        v2_4 = v8_3;
                        v41 = v9_3;
                        v42 = v10_1;
                        v12_1 = v39;
                        v9_4 = v7_1;
                    }

                    v18 += v15_1;
                    v10_1 = v42 + 1;
                    v8_3 = v2_4;
                    v7_1 = v9_4;
                    v39 = v12_1;
                    v9_3 = v41;
                    v2_3 = v45;
                }

                return new TrackSampleTable(arg52, v4_5, v5_2, v40, v9_3, v8_3, Util.scaleLargeTimestamp(v18, 1000000, v1.timescale));
            }
        }

        Util.scaleLargeTimestampsInPlace(v6_4, 1000000, v1.timescale);
        return new TrackSampleTable(arg52, v2_3, v4_3, v5, v6_4, v7_1, v8_2);
    label_610:
        throw new ParserException("Track has no sample table size information");
    }

    private static StsdData parseStsd(ParsableByteArray arg17, int arg18, int arg19, String arg20, DrmInitData arg21, boolean arg22) {
        ParsableByteArray v10 = arg17;
        v10.setPosition(12);
        int v11 = arg17.readInt();
        StsdData v12 = new StsdData(v11);
        int v14;
        for(v14 = 0; v14 < v11; ++v14) {
            int v15 = arg17.getPosition();
            int v16 = arg17.readInt();
            boolean v0 = v16 > 0 ? true : false;
            Assertions.checkArgument(v0, "childAtomSize should be positive");
            int v1 = arg17.readInt();
            if(v1 == Atom.TYPE_avc1 || v1 == Atom.TYPE_avc3 || v1 == Atom.TYPE_encv || v1 == Atom.TYPE_mp4v || v1 == Atom.TYPE_hvc1 || v1 == Atom.TYPE_hev1 || v1 == Atom.TYPE_s263 || v1 == Atom.TYPE_vp08 || v1 == Atom.TYPE_vp09) {
                AtomParsers.parseVideoSampleEntry(arg17, v1, v15, v16, arg18, arg19, arg21, v12, v14);
            }
            else {
                if(v1 != Atom.TYPE_mp4a && v1 != Atom.TYPE_enca && v1 != Atom.TYPE_ac_3 && v1 != Atom.TYPE_ec_3 && v1 != Atom.TYPE_dtsc && v1 != Atom.TYPE_dtse && v1 != Atom.TYPE_dtsh && v1 != Atom.TYPE_dtsl && v1 != Atom.TYPE_samr && v1 != Atom.TYPE_sawb && v1 != Atom.TYPE_lpcm && v1 != Atom.TYPE_sowt && v1 != Atom.TYPE__mp3) {
                    if(v1 == Atom.TYPE_alac) {
                    }
                    else {
                        if(v1 != Atom.TYPE_TTML && v1 != Atom.TYPE_tx3g && v1 != Atom.TYPE_wvtt && v1 != Atom.TYPE_stpp) {
                            if(v1 == Atom.TYPE_c608) {
                            }
                            else {
                                if(v1 == Atom.TYPE_camm) {
                                    v12.format = Format.createSampleFormat(Integer.toString(arg18), "application/x-camera-motion", null, -1, null);
                                }
                                else {
                                }

                                goto label_114;
                            }
                        }

                        AtomParsers.parseTextSampleEntry(arg17, v1, v15, v16, arg18, arg20, v12);
                        goto label_114;
                    }
                }

                AtomParsers.parseAudioSampleEntry(arg17, v1, v15, v16, arg18, arg20, arg22, arg21, v12, v14);
            }

        label_114:
            v10.setPosition(v15 + v16);
        }

        return v12;
    }

    private static void parseTextSampleEntry(ParsableByteArray arg18, int arg19, int arg20, int arg21, int arg22, String arg23, StsdData arg24) {
        String v8;
        String v0_1;
        ParsableByteArray v0 = arg18;
        int v1 = arg19;
        StsdData v2 = arg24;
        v0.setPosition(arg20 + 16);
        List v4 = null;
        long v5 = 9223372036854775807L;
        if(v1 == Atom.TYPE_TTML) {
            v0_1 = "application/ttml+xml";
            goto label_11;
        }
        else if(v1 == Atom.TYPE_tx3g) {
            int v3 = arg21 - 16;
            byte[] v4_1 = new byte[v3];
            v0.readBytes(v4_1, 0, v3);
            v4 = Collections.singletonList(v4_1);
            v8 = "application/x-quicktime-tx3g";
        }
        else if(v1 == Atom.TYPE_wvtt) {
            v0_1 = "application/x-mp4-vtt";
            goto label_11;
        }
        else if(v1 == Atom.TYPE_stpp) {
            v0_1 = "application/ttml+xml";
            v5 = 0;
            goto label_11;
        }
        else if(v1 == Atom.TYPE_c608) {
            v0_1 = "application/x-mp4-cea-608";
            v2.requiredSampleTransformation = 1;
        label_11:
            v8 = v0_1;
        }
        else {
            goto label_51;
        }

        List v17 = v4;
        long v15 = v5;
        v2.format = Format.createTextSampleFormat(Integer.toString(arg22), v8, null, -1, 0, arg23, -1, null, v15, v17);
        return;
    label_51:
        throw new IllegalStateException();
    }

    private static TkhdData parseTkhd(ParsableByteArray arg11) {
        int v0 = 8;
        arg11.setPosition(v0);
        int v1 = Atom.parseFullAtomVersion(arg11.readInt());
        int v2 = 16;
        int v3 = v1 == 0 ? 8 : 16;
        arg11.skipBytes(v3);
        v3 = arg11.readInt();
        int v4 = 4;
        arg11.skipBytes(v4);
        int v5 = arg11.getPosition();
        if(v1 == 0) {
            v0 = 4;
        }

        int v6 = 0;
        int v7 = 0;
        while(true) {
            if(v7 >= v0) {
                break;
            }
            else if(arg11.data[v5 + v7] != -1) {
                v5 = 0;
            }
            else {
                ++v7;
                continue;
            }

            goto label_29;
        }

        v5 = 1;
    label_29:
        long v7_1 = -9223372036854775807L;
        if(v5 != 0) {
            arg11.skipBytes(v0);
        }
        else {
            long v0_1 = v1 == 0 ? arg11.readUnsignedInt() : arg11.readUnsignedLongToLong();
            if(v0_1 == 0) {
                goto label_41;
            }

            v7_1 = v0_1;
        }

    label_41:
        arg11.skipBytes(v2);
        v0 = arg11.readInt();
        v1 = arg11.readInt();
        arg11.skipBytes(v4);
        v2 = arg11.readInt();
        int v11 = arg11.readInt();
        v4 = 65536;
        v5 = -65536;
        if(v0 != 0 || v1 != v4 || v2 != v5 || v11 != 0) {
            if(v0 == 0 && v1 == v5 && v2 == v4 && v11 == 0) {
                v6 = 270;
                goto label_66;
            }

            if(v0 != v5) {
                goto label_66;
            }

            if(v1 != 0) {
                goto label_66;
            }

            if(v2 != 0) {
                goto label_66;
            }

            if(v11 != v5) {
                goto label_66;
            }

            v6 = 180;
        }
        else {
            v6 = 90;
        }

    label_66:
        return new TkhdData(v3, v7_1, v6);
    }

    public static Track parseTrak(ContainerAtom arg22, LeafAtom arg23, long arg24, DrmInitData arg26, boolean arg27, boolean arg28) {
        long[] v16_1;
        Object v17;
        LeafAtom v4;
        long v8;
        ContainerAtom v0 = arg22;
        ContainerAtom v1 = v0.getContainerAtomOfType(Atom.TYPE_mdia);
        int v5 = AtomParsers.parseHdlr(v1.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        Track v2 = null;
        if(v5 == -1) {
            return v2;
        }

        TkhdData v3 = AtomParsers.parseTkhd(v0.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long v6 = -9223372036854775807L;
        if(arg24 == v6) {
            v8 = TkhdData.access$000(v3);
            v4 = arg23;
        }
        else {
            v4 = arg23;
            v8 = arg24;
        }

        long v14 = AtomParsers.parseMvhd(v4.data);
        if(v8 != v6) {
            v6 = Util.scaleLargeTimestamp(v8, 1000000, v14);
        }

        long v10 = v6;
        ContainerAtom v4_1 = v1.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair v1_1 = AtomParsers.parseMdhd(v1.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData v4_2 = AtomParsers.parseStsd(v4_1.getLeafAtomOfType(Atom.TYPE_stsd).data, TkhdData.access$100(v3), TkhdData.access$200(v3), v1_1.second, arg26, arg28);
        if(!arg27) {
            Pair v0_1 = AtomParsers.parseEdts(v0.getContainerAtomOfType(Atom.TYPE_edts));
            Object v6_1 = v0_1.first;
            v17 = v0_1.second;
            Object v16 = v6_1;
        }
        else {
            v16_1 = ((long[])v2);
            long[] v17_1 = v16_1;
        }

        Track v0_2 = v4_2.format == null ? v2 : new Track(TkhdData.access$100(v3), v5, v1_1.first.longValue(), v14, v10, v4_2.format, v4_2.requiredSampleTransformation, v4_2.trackEncryptionBoxes, v4_2.nalUnitLengthFieldLength, v16_1, ((long[])v17));
        return v0_2;
    }

    public static Metadata parseUdta(LeafAtom arg5, boolean arg6) {
        Metadata v0 = null;
        if(arg6) {
            return v0;
        }

        ParsableByteArray v5 = arg5.data;
        int v6 = 8;
        v5.setPosition(v6);
        while(v5.bytesLeft() >= v6) {
            int v1 = v5.getPosition();
            int v2 = v5.readInt();
            if(v5.readInt() == Atom.TYPE_meta) {
                v5.setPosition(v1);
                return AtomParsers.parseMetaAtom(v5, v1 + v2);
            }

            v5.skipBytes(v2 - 8);
        }

        return v0;
    }

    private static void parseVideoSampleEntry(ParsableByteArray arg22, int arg23, int arg24, int arg25, int arg26, int arg27, DrmInitData arg28, StsdData arg29, int arg30) {
        boolean v21;
        Pair v6;
        ParsableByteArray v0 = arg22;
        int v1 = arg24;
        int v2 = arg25;
        DrmInitData v3 = arg28;
        StsdData v4 = arg29;
        v0.setPosition(v1 + 16);
        v0.skipBytes(16);
        int v11 = arg22.readUnsignedShort();
        int v12 = arg22.readUnsignedShort();
        v0.skipBytes(50);
        int v5 = arg22.getPosition();
        String v7 = null;
        int v8 = arg23;
        if(v8 == Atom.TYPE_encv) {
            v6 = AtomParsers.parseSampleEntryEncryptionData(v0, v1, v2);
            if(v6 != null) {
                v8 = v6.first.intValue();
                v3 = v3 == null ? ((DrmInitData)v7) : v3.copyWithSchemeType(v6.second.schemeType);
                v4.trackEncryptionBoxes[arg30] = v6.second;
            }

            v0.setPosition(v5);
        }

        DrmInitData v20 = v3;
        List v14 = ((List)v7);
        byte[] v17 = ((byte[])v14);
        int v3_1 = 0;
        float v16 = 1f;
        int v18 = -1;
        while(v5 - v1 < v2) {
            v0.setPosition(v5);
            int v9 = arg22.getPosition();
            int v10 = arg22.readInt();
            if(v10 == 0 && arg22.getPosition() - v1 == v2) {
                break;
            }

            boolean v15 = v10 > 0 ? true : false;
            Assertions.checkArgument(v15, "childAtomSize should be positive");
            int v6_1 = arg22.readInt();
            int v13 = 3;
            if(v6_1 == Atom.TYPE_avcC) {
                v21 = v7 == null ? true : false;
                Assertions.checkState(v21);
                v7 = "video/avc";
                v0.setPosition(v9 + 8);
                AvcConfig v6_2 = AvcConfig.parse(arg22);
                v14 = v6_2.initializationData;
                v4.nalUnitLengthFieldLength = v6_2.nalUnitLengthFieldLength;
                if(v3_1 != 0) {
                    goto label_152;
                }

                v16 = v6_2.pixelWidthAspectRatio;
            }
            else {
                if(v6_1 == Atom.TYPE_hvcC) {
                    v21 = v7 == null ? true : false;
                    Assertions.checkState(v21);
                    v7 = "video/hevc";
                    v0.setPosition(v9 + 8);
                    HevcConfig v6_3 = HevcConfig.parse(arg22);
                    v14 = v6_3.initializationData;
                    v4.nalUnitLengthFieldLength = v6_3.nalUnitLengthFieldLength;
                    goto label_152;
                }

                if(v6_1 == Atom.TYPE_vpcC) {
                    v21 = v7 == null ? true : false;
                    Assertions.checkState(v21);
                    String v6_4 = v8 == Atom.TYPE_vp08 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
                    v7 = v6_4;
                    goto label_152;
                }

                if(v6_1 == Atom.TYPE_d263) {
                    v21 = v7 == null ? true : false;
                    Assertions.checkState(v21);
                    v7 = "video/3gpp";
                    goto label_152;
                }

                if(v6_1 == Atom.TYPE_esds) {
                    v21 = v7 == null ? true : false;
                    Assertions.checkState(v21);
                    v6 = AtomParsers.parseEsdsFromParent(v0, v9);
                    Object v7_1 = v6.first;
                    v14 = Collections.singletonList(v6.second);
                    goto label_152;
                }

                if(v6_1 == Atom.TYPE_pasp) {
                    v16 = AtomParsers.parsePaspFromParent(v0, v9);
                    v3_1 = 1;
                    goto label_152;
                }

                if(v6_1 == Atom.TYPE_sv3d) {
                    v17 = AtomParsers.parseProjFromParent(v0, v9, v10);
                    goto label_152;
                }

                if(v6_1 != Atom.TYPE_st3d) {
                    goto label_152;
                }

                v6_1 = arg22.readUnsignedByte();
                v0.skipBytes(v13);
                if(v6_1 != 0) {
                    goto label_152;
                }

                switch(arg22.readUnsignedByte()) {
                    case 0: {
                        goto label_151;
                    }
                    case 1: {
                        goto label_149;
                    }
                    case 2: {
                        goto label_146;
                    }
                    case 3: {
                        goto label_144;
                    }
                }

                goto label_152;
            label_146:
                v18 = 2;
                goto label_152;
            label_149:
                v18 = 1;
                goto label_152;
            label_151:
                v18 = 0;
                goto label_152;
            label_144:
                v18 = 3;
            }

        label_152:
            v5 += v10;
        }

        if(v7 == null) {
            return;
        }

        v4.format = Format.createVideoSampleFormat(Integer.toString(arg26), v7, null, -1, -1, v11, v12, -1f, v14, arg27, v16, v17, v18, null, v20);
    }
}

