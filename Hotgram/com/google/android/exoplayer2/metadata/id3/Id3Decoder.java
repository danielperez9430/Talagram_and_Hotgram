package com.google.android.exoplayer2.metadata.id3;

import android.util.Log;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Id3Decoder implements MetadataDecoder {
    final class com.google.android.exoplayer2.metadata.id3.Id3Decoder$1 implements FramePredicate {
        com.google.android.exoplayer2.metadata.id3.Id3Decoder$1() {
            super();
        }

        public boolean evaluate(int arg1, int arg2, int arg3, int arg4, int arg5) {
            return 0;
        }
    }

    public interface FramePredicate {
        boolean evaluate(int arg1, int arg2, int arg3, int arg4, int arg5);
    }

    final class Id3Header {
        private final int framesSize;
        private final boolean isUnsynchronized;
        private final int majorVersion;

        public Id3Header(int arg1, boolean arg2, int arg3) {
            super();
            this.majorVersion = arg1;
            this.isUnsynchronized = arg2;
            this.framesSize = arg3;
        }

        static int access$000(Id3Header arg0) {
            return arg0.majorVersion;
        }

        static int access$100(Id3Header arg0) {
            return arg0.framesSize;
        }

        static boolean access$200(Id3Header arg0) {
            return arg0.isUnsynchronized;
        }
    }

    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = 0;
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    public static final FramePredicate NO_FRAMES_PREDICATE = null;
    private static final String TAG = "Id3Decoder";
    private final FramePredicate framePredicate;

    static {
        Id3Decoder.NO_FRAMES_PREDICATE = new com.google.android.exoplayer2.metadata.id3.Id3Decoder$1();
        Id3Decoder.ID3_TAG = Util.getIntegerCodeForString("ID3");
    }

    public Id3Decoder(FramePredicate arg1) {
        super();
        this.framePredicate = arg1;
    }

    public Id3Decoder() {
        this(null);
    }

    private static byte[] copyOfRangeIfValid(byte[] arg0, int arg1, int arg2) {
        if(arg2 <= arg1) {
            return new byte[0];
        }

        return Arrays.copyOfRange(arg0, arg1, arg2);
    }

    public Metadata decode(byte[] arg7, int arg8) {
        ArrayList v0 = new ArrayList();
        ParsableByteArray v1 = new ParsableByteArray(arg7, arg8);
        Id3Header v7 = Id3Decoder.decodeHeader(v1);
        Metadata v8 = null;
        if(v7 == null) {
            return v8;
        }

        int v2 = v1.getPosition();
        int v3 = Id3Header.access$000(v7) == 2 ? 6 : 10;
        int v4 = Id3Header.access$100(v7);
        if(Id3Header.access$200(v7)) {
            v4 = Id3Decoder.removeUnsynchronization(v1, Id3Header.access$100(v7));
        }

        v1.setLimit(v2 + v4);
        boolean v4_1 = false;
        if(!Id3Decoder.validateFrames(v1, Id3Header.access$000(v7), v3, false)) {
            v4 = 4;
            if(Id3Header.access$000(v7) == v4 && (Id3Decoder.validateFrames(v1, v4, v3, true))) {
                v4_1 = true;
                goto label_44;
            }

            Log.w("Id3Decoder", "Failed to validate ID3 tag with majorVersion=" + Id3Header.access$000(v7));
            return v8;
        }

    label_44:
        while(v1.bytesLeft() >= v3) {
            Id3Frame v8_1 = Id3Decoder.decodeFrame(Id3Header.access$000(v7), v1, v4_1, v3, this.framePredicate);
            if(v8_1 == null) {
                continue;
            }

            ((List)v0).add(v8_1);
        }

        return new Metadata(((List)v0));
    }

    public Metadata decode(MetadataInputBuffer arg2) {
        return this.decode(arg2.data.array(), arg2.data.limit());
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray arg6, int arg7, int arg8) {
        String v8;
        int v0 = arg6.readUnsignedByte();
        String v1 = Id3Decoder.getCharsetName(v0);
        --arg7;
        byte[] v2 = new byte[arg7];
        arg6.readBytes(v2, 0, arg7);
        int v6 = 2;
        if(arg8 == v6) {
            String v7_1 = "image/" + Util.toLowerInvariant(new String(v2, 0, 3, "ISO-8859-1"));
            if("image/jpg".equals(v7_1)) {
                v7_1 = "image/jpeg";
            }

            v8 = v7_1;
            arg7 = 2;
        }
        else {
            arg7 = Id3Decoder.indexOfZeroByte(v2, 0);
            v8 = Util.toLowerInvariant(new String(v2, 0, arg7, "ISO-8859-1"));
            if(v8.indexOf(47) != -1) {
                goto label_41;
            }

            v8 = "image/" + v8;
        }

    label_41:
        int v3_1 = v2[arg7 + 1] & 255;
        arg7 += v6;
        v6 = Id3Decoder.indexOfEos(v2, arg7, v0);
        return new ApicFrame(v8, new String(v2, arg7, v6 - arg7, v1), v3_1, Id3Decoder.copyOfRangeIfValid(v2, v6 + Id3Decoder.delimiterLength(v0), v2.length));
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray arg2, int arg3, String arg4) {
        byte[] v0 = new byte[arg3];
        arg2.readBytes(v0, 0, arg3);
        return new BinaryFrame(arg4, v0);
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray arg15, int arg16, int arg17, boolean arg18, int arg19, FramePredicate arg20) {
        int v1 = arg15.getPosition();
        int v2 = Id3Decoder.indexOfZeroByte(arg15.data, v1);
        String v4 = new String(arg15.data, v1, v2 - v1, "ISO-8859-1");
        arg15.setPosition(v2 + 1);
        int v5 = arg15.readInt();
        int v6 = arg15.readInt();
        long v2_1 = arg15.readUnsignedInt();
        long v7 = 4294967295L;
        long v10 = -1;
        long v12 = Long.compare(v2_1, v7) == 0 ? v10 : v2_1;
        v2_1 = arg15.readUnsignedInt();
        long v9 = v2_1 == v7 ? v10 : v2_1;
        ArrayList v2_2 = new ArrayList();
        v1 += arg16;
        while(arg15.getPosition() < v1) {
            Id3Frame v14 = Id3Decoder.decodeFrame(arg17, arg15, arg18, arg19, arg20);
            if(v14 == null) {
                continue;
            }

            v2_2.add(v14);
        }

        Id3Frame[] v11 = new Id3Frame[v2_2.size()];
        v2_2.toArray(((Object[])v11));
        return new ChapterFrame(v4, v5, v6, v12, v9, v11);
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray arg15, int arg16, int arg17, boolean arg18, int arg19, FramePredicate arg20) {
        ParsableByteArray v0 = arg15;
        int v1 = arg15.getPosition();
        int v2 = Id3Decoder.indexOfZeroByte(v0.data, v1);
        String v3 = new String(v0.data, v1, v2 - v1, "ISO-8859-1");
        arg15.setPosition(v2 + 1);
        v2 = arg15.readUnsignedByte();
        int v6 = 0;
        boolean v5 = (v2 & 2) != 0 ? true : false;
        boolean v2_1 = (v2 & 1) != 0 ? true : false;
        int v7 = arg15.readUnsignedByte();
        String[] v8 = new String[v7];
        while(v6 < v7) {
            int v9 = arg15.getPosition();
            int v10 = Id3Decoder.indexOfZeroByte(v0.data, v9);
            v8[v6] = new String(v0.data, v9, v10 - v9, "ISO-8859-1");
            arg15.setPosition(v10 + 1);
            ++v6;
        }

        ArrayList v4 = new ArrayList();
        v1 += arg16;
        while(arg15.getPosition() < v1) {
            Id3Frame v11 = Id3Decoder.decodeFrame(arg17, arg15, arg18, arg19, arg20);
            if(v11 == null) {
                continue;
            }

            v4.add(v11);
        }

        Id3Frame[] v0_1 = new Id3Frame[v4.size()];
        v4.toArray(((Object[])v0_1));
        return new ChapterTocFrame(v3, v5, v2_1, v8, v0_1);
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray arg7, int arg8) {
        int v0 = 4;
        if(arg8 < v0) {
            return null;
        }

        int v1 = arg7.readUnsignedByte();
        String v2 = Id3Decoder.getCharsetName(v1);
        byte[] v4 = new byte[3];
        arg7.readBytes(v4, 0, 3);
        String v6 = new String(v4, 0, 3);
        arg8 -= v0;
        byte[] v0_1 = new byte[arg8];
        arg7.readBytes(v0_1, 0, arg8);
        int v7 = Id3Decoder.indexOfEos(v0_1, 0, v1);
        String v8 = new String(v0_1, 0, v7, v2);
        v7 += Id3Decoder.delimiterLength(v1);
        return new CommentFrame(v6, v8, Id3Decoder.decodeStringIfValid(v0_1, v7, Id3Decoder.indexOfEos(v0_1, v7, v1), v2));
    }

    private static Id3Frame decodeFrame(int arg19, ParsableByteArray arg20, boolean arg21, int arg22, FramePredicate arg23) {
        BinaryFrame v1_9;
        UrlLinkFrame v1_2;
        int v17;
        int v4;
        int v3;
        int v2;
        int v14;
        int v12;
        int v1;
        int v0 = arg19;
        ParsableByteArray v7 = arg20;
        int v8 = arg20.readUnsignedByte();
        int v9 = arg20.readUnsignedByte();
        int v10 = arg20.readUnsignedByte();
        int v11 = 3;
        int v13 = v0 >= v11 ? arg20.readUnsignedByte() : 0;
        if(v0 == 4) {
            v1 = arg20.readUnsignedIntToInt();
            if(!arg21) {
                v1 = (v1 >> 24 & 255) << 21 | (v1 & 255 | (v1 >> 8 & 255) << 7 | (v1 >> 16 & 255) << 14);
            }
        }
        else if(v0 == v11) {
            v1 = arg20.readUnsignedIntToInt();
        }
        else {
            v1 = arg20.readUnsignedInt24();
        }

        int v15 = v1;
        int v6 = v0 >= v11 ? arg20.readUnsignedShort() : 0;
        Id3Frame v16 = null;
        if(v8 == 0 && v9 == 0 && v10 == 0 && v13 == 0 && v15 == 0 && v6 == 0) {
            v7.setPosition(arg20.limit());
            return v16;
        }

        int v5 = arg20.getPosition() + v15;
        if(v5 > arg20.limit()) {
            Log.w("Id3Decoder", "Frame size exceeds remaining tag data");
            v7.setPosition(arg20.limit());
            return v16;
        }

        if(arg23 != null) {
            v12 = v5;
            v14 = v6;
            if(!arg23.evaluate(arg19, v8, v9, v10, v13)) {
                v7.setPosition(v12);
                return v16;
            }
        }
        else {
            v12 = v5;
            v14 = v6;
        }

        if(v0 == v11) {
            v2 = (v14 & 128) != 0 ? 1 : 0;
            v3 = (v14 & 64) != 0 ? 1 : 0;
            v4 = (v14 & 32) != 0 ? 1 : 0;
            v17 = v3;
            v5 = 0;
            v3 = v2;
        }
        else {
            if(v0 == 4) {
                v2 = (v14 & 64) != 0 ? 1 : 0;
                v3 = (v14 & 8) != 0 ? 1 : 0;
                v4 = (v14 & 4) != 0 ? 1 : 0;
                v5 = (v14 & 2) != 0 ? 1 : 0;
                v17 = (v14 & 1) != 0 ? 1 : 0;
                int v18 = v4;
                v4 = v2;
                v2 = v3;
                v3 = v17;
                v17 = v18;
                goto label_134;
            }

            v2 = 0;
            v3 = 0;
            v4 = 0;
            v5 = 0;
            v17 = 0;
        }

    label_134:
        if(v2 == 0) {
            if(v17 != 0) {
            }
            else {
                if(v4 != 0) {
                    --v15;
                    v7.skipBytes(1);
                }

                if(v3 != 0) {
                    v15 += -4;
                    v7.skipBytes(4);
                }

                v1 = v15;
                if(v5 != 0) {
                    v1 = Id3Decoder.removeUnsynchronization(v7, v1);
                }

                v11 = v1;
                v1 = 84;
                v2 = 88;
                v3 = 2;
                if(v8 != v1) {
                    goto label_158;
                }
                else if(v9 != v2) {
                    goto label_158;
                }
                else if(v10 == v2) {
                    if(v0 != v3) {
                        if(v13 == v2) {
                            goto label_156;
                        }

                        goto label_158;
                    }

                    try {
                    label_156:
                        TextInformationFrame v1_1 = Id3Decoder.decodeTxxxFrame(v7, v11);
                        goto label_248;
                    label_158:
                        if(v8 == v1) {
                            v1_1 = Id3Decoder.decodeTextInformationFrame(v7, v11, Id3Decoder.getFrameId(v0, v8, v9, v10, v13));
                            goto label_248;
                        }

                        if(v8 == 87 && v9 == v2 && v10 == v2 && (v0 == v3 || v13 == v2)) {
                            v1_2 = Id3Decoder.decodeWxxxFrame(v7, v11);
                            goto label_248;
                        }

                        if(v8 == 87) {
                            v1_2 = Id3Decoder.decodeUrlLinkFrame(v7, v11, Id3Decoder.getFrameId(v0, v8, v9, v10, v13));
                            goto label_248;
                        }

                        v2 = 73;
                        v4 = 80;
                        if(v8 == v4 && v9 == 82 && v10 == v2 && v13 == 86) {
                            PrivFrame v1_3 = Id3Decoder.decodePrivFrame(v7, v11);
                            goto label_248;
                        }

                        v6 = 79;
                        if(v8 == 71 && v9 == 69 && v10 == v6 && (v13 == 66 || v0 == v3)) {
                            GeobFrame v1_4 = Id3Decoder.decodeGeobFrame(v7, v11);
                            goto label_248;
                        }

                        v5 = 67;
                        if(v0 == v3) {
                            if(v8 == v4 && v9 == v2 && v10 == v5) {
                                goto label_209;
                            }
                        }
                        else if(v8 == 65 && v9 == v4 && v10 == v2 && v13 == v5) {
                        label_209:
                            ApicFrame v1_5 = Id3Decoder.decodeApicFrame(v7, v11, v0);
                            goto label_248;
                        }

                        if(v8 == v5 && v9 == v6 && v10 == 77 && (v13 == 77 || v0 == v3)) {
                            CommentFrame v1_6 = Id3Decoder.decodeCommentFrame(v7, v11);
                            goto label_248;
                        }

                        if(v8 == v5 && v9 == 72 && v10 == 65 && v13 == v4) {
                            ChapterFrame v1_7 = Id3Decoder.decodeChapterFrame(arg20, v11, arg19, arg21, arg22, arg23);
                            goto label_248;
                        }

                        if(v8 == v5 && v9 == v1 && v10 == v6 && v13 == v5) {
                            ChapterTocFrame v1_8 = Id3Decoder.decodeChapterTOCFrame(arg20, v11, arg19, arg21, arg22, arg23);
                        }
                        else {
                            v1_9 = Id3Decoder.decodeBinaryFrame(v7, v11, Id3Decoder.getFrameId(v0, v8, v9, v10, v13));
                        }

                    label_248:
                        if((((TextInformationFrame)v1_9)) == null) {
                            Log.w("Id3Decoder", "Failed to decode frame: id=" + Id3Decoder.getFrameId(v0, v8, v9, v10, v13) + ", frameSize=" + v11);
                        }

                        goto label_261;
                    }
                    catch(Throwable v0_1) {
                        goto label_268;
                    }
                    catch(UnsupportedEncodingException ) {
                        goto label_263;
                    }
                }
                else {
                    goto label_158;
                }

                goto label_248;
            label_261:
                v7.setPosition(v12);
                return ((Id3Frame)v1_9);
            }
        }

        Log.w("Id3Decoder", "Skipping unsupported compressed or encrypted frame");
        v7.setPosition(v12);
        return v16;
    label_268:
        v7.setPosition(v12);
        throw v0_1;
        try {
        label_263:
            Log.w("Id3Decoder", "Unsupported character encoding");
        }
        catch(Throwable v0_1) {
            goto label_268;
        }

        v7.setPosition(v12);
        return v16;
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray arg5, int arg6) {
        int v0 = arg5.readUnsignedByte();
        String v1 = Id3Decoder.getCharsetName(v0);
        --arg6;
        byte[] v2 = new byte[arg6];
        arg5.readBytes(v2, 0, arg6);
        int v5 = Id3Decoder.indexOfZeroByte(v2, 0);
        String v6 = new String(v2, 0, v5, "ISO-8859-1");
        ++v5;
        int v3 = Id3Decoder.indexOfEos(v2, v5, v0);
        String v5_1 = Id3Decoder.decodeStringIfValid(v2, v5, v3, v1);
        v3 += Id3Decoder.delimiterLength(v0);
        int v4 = Id3Decoder.indexOfEos(v2, v3, v0);
        return new GeobFrame(v6, v5_1, Id3Decoder.decodeStringIfValid(v2, v3, v4, v1), Id3Decoder.copyOfRangeIfValid(v2, v4 + Id3Decoder.delimiterLength(v0), v2.length));
    }

    private static Id3Header decodeHeader(ParsableByteArray arg8) {
        int v1_1;
        int v8_1;
        String v3;
        StringBuilder v2;
        String v0;
        String v8;
        Id3Header v1 = null;
        if(arg8.bytesLeft() < 10) {
            v8 = "Id3Decoder";
            v0 = "Data too short to be an ID3 tag";
        }
        else {
            int v0_1 = arg8.readUnsignedInt24();
            if(v0_1 != Id3Decoder.ID3_TAG) {
                v8 = "Id3Decoder";
                v2 = new StringBuilder();
                v3 = "Unexpected first three bytes of ID3 tag header: ";
            }
            else {
                v0_1 = arg8.readUnsignedByte();
                boolean v2_1 = true;
                arg8.skipBytes(1);
                int v3_1 = arg8.readUnsignedByte();
                int v4 = arg8.readSynchSafeInt();
                int v6 = 4;
                if(v0_1 == 2) {
                    v8_1 = (v3_1 & 64) != 0 ? 1 : 0;
                    if(v8_1 == 0) {
                        goto label_68;
                    }

                    v8 = "Id3Decoder";
                    v0 = "Skipped ID3 tag with majorVersion=2 and undefined compression scheme";
                    goto label_6;
                }
                else {
                    if(v0_1 == 3) {
                        v1_1 = (v3_1 & 64) != 0 ? 1 : 0;
                        if(v1_1 == 0) {
                            goto label_68;
                        }

                        v1_1 = arg8.readInt();
                        arg8.skipBytes(v1_1);
                        v4 -= v1_1 + v6;
                        goto label_68;
                    }

                    if(v0_1 != v6) {
                        goto label_76;
                    }

                    v1_1 = (v3_1 & 64) != 0 ? 1 : 0;
                    if(v1_1 != 0) {
                        v1_1 = arg8.readSynchSafeInt();
                        arg8.skipBytes(v1_1 - 4);
                        v4 -= v1_1;
                    }

                    v8_1 = (v3_1 & 16) != 0 ? 1 : 0;
                    if(v8_1 == 0) {
                        goto label_68;
                    }

                    v4 += -10;
                }

            label_68:
                if(v0_1 >= v6 || (v3_1 & 128) == 0) {
                    v2_1 = false;
                }
                else {
                }

                return new Id3Header(v0_1, v2_1, v4);
            label_76:
                v8 = "Id3Decoder";
                v2 = new StringBuilder();
                v3 = "Skipped ID3 tag with unsupported majorVersion=";
            }

            v2.append(v3);
            v2.append(v0_1);
            v0 = v2.toString();
        }

    label_6:
        Log.w(v8, v0);
        return v1;
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray arg3, int arg4) {
        byte[] v0 = new byte[arg4];
        arg3.readBytes(v0, 0, arg4);
        int v3 = Id3Decoder.indexOfZeroByte(v0, 0);
        return new PrivFrame(new String(v0, 0, v3, "ISO-8859-1"), Id3Decoder.copyOfRangeIfValid(v0, v3 + 1, v0.length));
    }

    private static String decodeStringIfValid(byte[] arg1, int arg2, int arg3, String arg4) {
        if(arg3 > arg2) {
            if(arg3 > arg1.length) {
            }
            else {
                return new String(arg1, arg2, arg3 - arg2, arg4);
            }
        }

        return "";
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray arg5, int arg6, String arg7) {
        TextInformationFrame v0 = null;
        if(arg6 < 1) {
            return v0;
        }

        int v2 = arg5.readUnsignedByte();
        String v3 = Id3Decoder.getCharsetName(v2);
        --arg6;
        byte[] v1 = new byte[arg6];
        arg5.readBytes(v1, 0, arg6);
        return new TextInformationFrame(arg7, ((String)v0), new String(v1, 0, Id3Decoder.indexOfEos(v1, 0, v2), v3));
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray arg4, int arg5) {
        if(arg5 < 1) {
            return null;
        }

        int v1 = arg4.readUnsignedByte();
        String v2 = Id3Decoder.getCharsetName(v1);
        --arg5;
        byte[] v0 = new byte[arg5];
        arg4.readBytes(v0, 0, arg5);
        int v4 = Id3Decoder.indexOfEos(v0, 0, v1);
        String v5 = new String(v0, 0, v4, v2);
        v4 += Id3Decoder.delimiterLength(v1);
        return new TextInformationFrame("TXXX", v5, Id3Decoder.decodeStringIfValid(v0, v4, Id3Decoder.indexOfEos(v0, v4, v1), v2));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray arg3, int arg4, String arg5) {
        byte[] v0 = new byte[arg4];
        arg3.readBytes(v0, 0, arg4);
        return new UrlLinkFrame(arg5, null, new String(v0, 0, Id3Decoder.indexOfZeroByte(v0, 0), "ISO-8859-1"));
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray arg4, int arg5) {
        if(arg5 < 1) {
            return null;
        }

        int v1 = arg4.readUnsignedByte();
        String v2 = Id3Decoder.getCharsetName(v1);
        --arg5;
        byte[] v0 = new byte[arg5];
        arg4.readBytes(v0, 0, arg5);
        int v4 = Id3Decoder.indexOfEos(v0, 0, v1);
        String v5 = new String(v0, 0, v4, v2);
        v4 += Id3Decoder.delimiterLength(v1);
        return new UrlLinkFrame("WXXX", v5, Id3Decoder.decodeStringIfValid(v0, v4, Id3Decoder.indexOfZeroByte(v0, v4), "ISO-8859-1"));
    }

    private static int delimiterLength(int arg1) {
        return arg1 == 0 || arg1 == 3 ? 1 : 2;
    }

    private static String getCharsetName(int arg0) {
        switch(arg0) {
            case 0: {
                return "ISO-8859-1";
            }
            case 1: {
                return "UTF-16";
            }
            case 2: {
                return "UTF-16BE";
            }
            case 3: {
                return "UTF-8";
            }
        }

        return "ISO-8859-1";
    }

    private static String getFrameId(int arg6, int arg7, int arg8, int arg9, int arg10) {
        String v6_1;
        Locale v6;
        int v2 = 3;
        int v3 = 2;
        if(arg6 == v3) {
            v6 = Locale.US;
            Object[] v2_1 = new Object[v2];
            v2_1[0] = Integer.valueOf(arg7);
            v2_1[1] = Integer.valueOf(arg8);
            v2_1[v3] = Integer.valueOf(arg9);
            v6_1 = String.format(v6, "%c%c%c", v2_1);
        }
        else {
            v6 = Locale.US;
            Object[] v5 = new Object[4];
            v5[0] = Integer.valueOf(arg7);
            v5[1] = Integer.valueOf(arg8);
            v5[v3] = Integer.valueOf(arg9);
            v5[v2] = Integer.valueOf(arg10);
            v6_1 = String.format(v6, "%c%c%c%c", v5);
        }

        return v6_1;
    }

    private static int indexOfEos(byte[] arg1, int arg2, int arg3) {
        arg2 = Id3Decoder.indexOfZeroByte(arg1, arg2);
        if(arg3 != 0 && arg3 != 3) {
            while(arg2 < arg1.length - 1) {
                if(arg2 % 2 == 0 && arg1[arg2 + 1] == 0) {
                    return arg2;
                }

                arg2 = Id3Decoder.indexOfZeroByte(arg1, arg2 + 1);
            }

            return arg1.length;
        }

        return arg2;
    }

    private static int indexOfZeroByte(byte[] arg1, int arg2) {
        while(arg2 < arg1.length) {
            if(arg1[arg2] == 0) {
                return arg2;
            }

            ++arg2;
        }

        return arg1.length;
    }

    private static int removeUnsynchronization(ParsableByteArray arg4, int arg5) {
        byte[] v0 = arg4.data;
        int v4;
        for(v4 = arg4.getPosition(); true; v4 = v1) {
            int v1 = v4 + 1;
            if(v1 >= arg5) {
                return arg5;
            }

            if((v0[v4] & 255) == 255 && v0[v1] == 0) {
                System.arraycopy(v0, v4 + 2, v0, v1, arg5 - v4 - 2);
                --arg5;
            }
        }

        return arg5;
    }

    private static boolean validateFrames(ParsableByteArray arg18, int arg19, int arg20, boolean arg21) {
        int v10;
        long v8;
        int v7;
        int v3;
        int v4;
        ParsableByteArray v1 = arg18;
        int v0 = arg19;
        int v2 = arg18.getPosition();
        try {
            while(true) {
            label_3:
                v4 = 1;
                if(arg18.bytesLeft() < arg20) {
                    goto label_90;
                }

                v3 = 3;
                if(v0 >= v3) {
                    v7 = arg18.readInt();
                    v8 = arg18.readUnsignedInt();
                    v10 = arg18.readUnsignedShort();
                }
                else {
                    v7 = arg18.readUnsignedInt24();
                    v8 = ((long)arg18.readUnsignedInt24());
                    v10 = 0;
                }

                break;
            }
        }
        catch(Throwable v0_1) {
            goto label_93;
        }

        long v11 = 0;
        if(v7 == 0 && v8 == v11 && v10 == 0) {
            v1.setPosition(v2);
            return 1;
        }

        v7 = 4;
        if(v0 == v7 && !arg21) {
            if((8421504 & v8) != v11) {
                v1.setPosition(v2);
                return 0;
            }
            else {
                v8 = (v8 >> 24 & 255) << 21 | (v8 & 255 | (v8 >> 8 & 255) << 7 | (v8 >> 16 & 255) << 14);
            }
        }

        if(v0 == v7) {
            v3 = (v10 & 64) != 0 ? 1 : 0;
            if((v10 & 1) == 0) {
                goto label_72;
            }

            goto label_60;
        }
        else {
            if(v0 == v3) {
                v3 = (v10 & 32) != 0 ? 1 : 0;
                if((v10 & 128) == 0) {
                    goto label_72;
                }

            label_60:
                v7 = 1;
                goto label_73;
            }

            v3 = 0;
        label_72:
            v7 = 0;
        }

    label_73:
        if(v3 != 0) {
        }
        else {
            v4 = 0;
        }

        if(v7 != 0) {
            v4 += 4;
        }

        if(v8 < (((long)v4))) {
            v1.setPosition(v2);
            return 0;
        }

        try {
            if((((long)arg18.bytesLeft())) >= v8) {
                goto label_87;
            }
        }
        catch(Throwable v0_1) {
            goto label_93;
        }

        v1.setPosition(v2);
        return 0;
    label_87:
        v3 = ((int)v8);
        try {
            v1.skipBytes(v3);
            goto label_3;
        }
        catch(Throwable v0_1) {
        label_93:
            v1.setPosition(v2);
            throw v0_1;
        }

    label_90:
        v1.setPosition(v2);
        return 1;
    }
}

