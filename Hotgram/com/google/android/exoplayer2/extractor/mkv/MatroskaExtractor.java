package com.google.android.exoplayer2.extractor.mkv;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData$SchemeData;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.ColorInfo;
import com.google.android.exoplayer2.video.HevcConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class MatroskaExtractor implements Extractor {
    class com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$1 {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        InnerEbmlReaderOutput(MatroskaExtractor arg1, com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$1 arg2) {
            this(arg1);
        }

        private InnerEbmlReaderOutput(MatroskaExtractor arg1) {
            MatroskaExtractor.this = arg1;
            super();
        }

        public void binaryElement(int arg2, int arg3, ExtractorInput arg4) {
            MatroskaExtractor.this.binaryElement(arg2, arg3, arg4);
        }

        public void endMasterElement(int arg2) {
            MatroskaExtractor.this.endMasterElement(arg2);
        }

        public void floatElement(int arg2, double arg3) {
            MatroskaExtractor.this.floatElement(arg2, arg3);
        }

        public int getElementType(int arg1) {
            switch(arg1) {
                case 181: 
                case 17545: 
                case 21969: 
                case 21970: 
                case 21971: 
                case 21972: 
                case 21973: 
                case 21974: 
                case 21975: 
                case 21976: 
                case 21977: 
                case 21978: {
                    return 5;
                }
                case 161: 
                case 163: 
                case 16981: 
                case 18402: 
                case 21419: 
                case 25506: 
                case 30322: {
                    return 4;
                }
                case 134: 
                case 17026: 
                case 2274716: {
                    return 3;
                }
                case 131: 
                case 136: 
                case 155: 
                case 159: 
                case 176: 
                case 179: 
                case 186: 
                case 215: 
                case 231: 
                case 241: 
                case 251: 
                case 16980: 
                case 17029: 
                case 17143: 
                case 18401: 
                case 18408: 
                case 20529: 
                case 20530: 
                case 21420: 
                case 21432: 
                case 21680: 
                case 21682: 
                case 21690: 
                case 21930: 
                case 21945: 
                case 21946: 
                case 21947: 
                case 21948: 
                case 21949: 
                case 22186: 
                case 22203: 
                case 25188: 
                case 2352003: 
                case 2807729: {
                    return 2;
                }
                case 160: 
                case 174: 
                case 183: 
                case 187: 
                case 224: 
                case 225: 
                case 18407: 
                case 19899: 
                case 20532: 
                case 20533: 
                case 21936: 
                case 21968: 
                case 25152: 
                case 28032: 
                case 30320: 
                case 290298740: 
                case 357149030: 
                case 374648427: 
                case 408125543: 
                case 440786851: 
                case 475249515: 
                case 524531317: {
                    return 1;
                }
            }

            return 0;
        }

        public void integerElement(int arg2, long arg3) {
            MatroskaExtractor.this.integerElement(arg2, arg3);
        }

        public boolean isLevel1Element(int arg2) {
            boolean v2 = arg2 == 357149030 || arg2 == 524531317 || arg2 == 475249515 || arg2 == 374648427 ? true : false;
            return v2;
        }

        public void startMasterElement(int arg7, long arg8, long arg10) {
            MatroskaExtractor.this.startMasterElement(arg7, arg8, arg10);
        }

        public void stringElement(int arg2, String arg3) {
            MatroskaExtractor.this.stringElement(arg2, arg3);
        }
    }

    final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        private String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        Track(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$1 arg1) {
            this();
        }

        private Track() {
            super();
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1f;
            this.primaryRChromaticityY = -1f;
            this.primaryGChromaticityX = -1f;
            this.primaryGChromaticityY = -1f;
            this.primaryBChromaticityX = -1f;
            this.primaryBChromaticityY = -1f;
            this.whitePointChromaticityX = -1f;
            this.whitePointChromaticityY = -1f;
            this.maxMasteringLuminance = -1f;
            this.minMasteringLuminance = -1f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        static String access$202(Track arg0, String arg1) {
            arg0.language = arg1;
            return arg1;
        }

        private byte[] getHdrStaticInfo() {
            float v1 = -1f;
            if(this.primaryRChromaticityX != v1 && this.primaryRChromaticityY != v1 && this.primaryGChromaticityX != v1 && this.primaryGChromaticityY != v1 && this.primaryBChromaticityX != v1 && this.primaryBChromaticityY != v1 && this.whitePointChromaticityX != v1 && this.whitePointChromaticityY != v1 && this.maxMasteringLuminance != v1) {
                if(this.minMasteringLuminance == v1) {
                }
                else {
                    byte[] v0 = new byte[25];
                    ByteBuffer v1_1 = ByteBuffer.wrap(v0);
                    v1_1.put(0);
                    v1_1.putShort(((short)(((int)(this.primaryRChromaticityX * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.primaryRChromaticityY * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.primaryGChromaticityX * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.primaryGChromaticityY * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.primaryBChromaticityX * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.primaryBChromaticityY * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.whitePointChromaticityX * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.whitePointChromaticityY * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.maxMasteringLuminance + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.minMasteringLuminance + 0.5f)))));
                    v1_1.putShort(((short)this.maxContentLuminance));
                    v1_1.putShort(((short)this.maxFrameAverageLuminance));
                    return v0;
                }
            }

            return null;
        }

        public void initializeOutput(ExtractorOutput arg27, int arg28) {
            Format v1_3;
            String v4_1;
            int v1_1;
            Track v0 = this;
            String v1 = v0.codecId;
            int v3 = 4;
            int v4 = 8;
            int v6 = 0;
            int v7 = 2;
            int v8 = 3;
            int v9 = -1;
            switch(v1.hashCode()) {
                case 1809237540: {
                    if(!v1.equals("V_MPEG2")) {
                        goto label_157;
                    }

                    v1_1 = 2;
                    break;
                }
                case 1950749482: {
                    if(!v1.equals("A_EAC3")) {
                        goto label_157;
                    }

                    v1_1 = 16;
                    break;
                }
                case 1950789798: {
                    if(!v1.equals("A_FLAC")) {
                        goto label_157;
                    }

                    v1_1 = 21;
                    break;
                }
                case 1951062397: {
                    if(!v1.equals("A_OPUS")) {
                        goto label_157;
                    }

                    v1_1 = 11;
                    break;
                }
                case -2095576542: {
                    if(!v1.equals("V_MPEG4/ISO/AP")) {
                        goto label_157;
                    }

                    v1_1 = 5;
                    break;
                }
                case -2095575984: {
                    if(!v1.equals("V_MPEG4/ISO/SP")) {
                        goto label_157;
                    }

                    v1_1 = 3;
                    break;
                }
                case -1985379776: {
                    if(!v1.equals("A_MS/ACM")) {
                        goto label_157;
                    }

                    v1_1 = 22;
                    break;
                }
                case -1784763192: {
                    if(!v1.equals("A_TRUEHD")) {
                        goto label_157;
                    }

                    v1_1 = 17;
                    break;
                }
                case -1730367663: {
                    if(!v1.equals("A_VORBIS")) {
                        goto label_157;
                    }

                    v1_1 = 10;
                    break;
                }
                case -1482641358: {
                    if(!v1.equals("A_MPEG/L2")) {
                        goto label_157;
                    }

                    v1_1 = 13;
                    break;
                }
                case -1482641357: {
                    if(!v1.equals("A_MPEG/L3")) {
                        goto label_157;
                    }

                    v1_1 = 14;
                    break;
                }
                case -1373388978: {
                    if(!v1.equals("V_MS/VFW/FOURCC")) {
                        goto label_157;
                    }

                    v1_1 = 8;
                    break;
                }
                case -933872740: {
                    if(!v1.equals("S_DVBSUB")) {
                        goto label_157;
                    }

                    v1_1 = 28;
                    break;
                }
                case -538363189: {
                    if(!v1.equals("V_MPEG4/ISO/ASP")) {
                        goto label_157;
                    }

                    v1_1 = 4;
                    break;
                }
                case -538363109: {
                    if(!v1.equals("V_MPEG4/ISO/AVC")) {
                        goto label_157;
                    }

                    v1_1 = 6;
                    break;
                }
                case -425012669: {
                    if(!v1.equals("S_VOBSUB")) {
                        goto label_157;
                    }

                    v1_1 = 26;
                    break;
                }
                case -356037306: {
                    if(!v1.equals("A_DTS/LOSSLESS")) {
                        goto label_157;
                    }

                    v1_1 = 20;
                    break;
                }
                case 62923557: {
                    if(!v1.equals("A_AAC")) {
                        goto label_157;
                    }

                    v1_1 = 12;
                    break;
                }
                case 62923603: {
                    if(!v1.equals("A_AC3")) {
                        goto label_157;
                    }

                    v1_1 = 15;
                    break;
                }
                case 62927045: {
                    if(!v1.equals("A_DTS")) {
                        goto label_157;
                    }

                    v1_1 = 18;
                    break;
                }
                case 82338133: {
                    if(!v1.equals("V_VP8")) {
                        goto label_157;
                    }

                    v1_1 = 0;
                    break;
                }
                case 82338134: {
                    if(!v1.equals("V_VP9")) {
                        goto label_157;
                    }

                    v1_1 = 1;
                    break;
                }
                case 99146302: {
                    if(!v1.equals("S_HDMV/PGS")) {
                        goto label_157;
                    }

                    v1_1 = 27;
                    break;
                }
                case 444813526: {
                    if(!v1.equals("V_THEORA")) {
                        goto label_157;
                    }

                    v1_1 = 9;
                    break;
                }
                case 542569478: {
                    if(!v1.equals("A_DTS/EXPRESS")) {
                        goto label_157;
                    }

                    v1_1 = 19;
                    break;
                }
                case 725957860: {
                    if(!v1.equals("A_PCM/INT/LIT")) {
                        goto label_157;
                    }

                    v1_1 = 23;
                    break;
                }
                case 738597099: {
                    if(!v1.equals("S_TEXT/ASS")) {
                        goto label_157;
                    }

                    v1_1 = 25;
                    break;
                }
                case 855502857: {
                    if(!v1.equals("V_MPEGH/ISO/HEVC")) {
                        goto label_157;
                    }

                    v1_1 = 7;
                    break;
                }
                case 1422270023: {
                    if(v1.equals("S_TEXT/UTF8")) {
                        v1_1 = 24;
                        goto label_158;
                    }

                label_157:
                    v1_1 = -1;
                    break;
                }
                default: {
                    goto label_157;
                }
            }

        label_158:
            ColorInfo v10 = null;
            switch(v1_1) {
                case 0: {
                    goto label_333;
                }
                case 1: {
                    goto label_331;
                }
                case 2: {
                    goto label_329;
                }
                case 3: 
                case 4: 
                case 5: {
                    goto label_322;
                }
                case 6: {
                    goto label_311;
                }
                case 7: {
                    goto label_303;
                }
                case 8: {
                    goto label_292;
                }
                case 9: {
                    goto label_290;
                }
                case 10: {
                    goto label_282;
                }
                case 11: {
                    goto label_258;
                }
                case 12: {
                    goto label_253;
                }
                case 13: {
                    goto label_248;
                }
                case 14: {
                    goto label_246;
                }
                case 15: {
                    goto label_244;
                }
                case 16: {
                    goto label_242;
                }
                case 17: {
                    goto label_237;
                }
                case 18: 
                case 19: {
                    goto label_235;
                }
                case 20: {
                    goto label_233;
                }
                case 21: {
                    goto label_231;
                }
                case 22: {
                    goto label_207;
                }
                case 23: {
                    goto label_188;
                }
                case 24: {
                    goto label_186;
                }
                case 25: {
                    goto label_184;
                }
                case 26: {
                    goto label_182;
                }
                case 27: {
                    goto label_180;
                }
                case 28: {
                    goto label_165;
                }
            }

            throw new ParserException("Unrecognized codec identifier.");
        label_290:
            v1 = "video/x-unknown";
            goto label_334;
        label_292:
            Pair v1_2 = Track.parseFourCcPrivate(new ParsableByteArray(v0.codecPrivate));
            Object v12 = v1_2.first;
            int v15 = -1;
            int v18 = -1;
            Object v2 = v1_2.second;
            goto label_338;
        label_165:
            v1 = "application/dvbsubs";
            byte[] v2_1 = new byte[v3];
            v2_1[0] = v0.codecPrivate[0];
            v2_1[1] = v0.codecPrivate[1];
            v2_1[v7] = v0.codecPrivate[v7];
            v2_1[v8] = v0.codecPrivate[v8];
            goto label_255;
        label_231:
            v1 = "audio/flac";
            goto label_254;
        label_233:
            v1 = "audio/vnd.dts.hd";
            goto label_334;
        label_235:
            v1 = "audio/vnd.dts";
            goto label_334;
        label_237:
            v1 = "audio/true-hd";
            v0.trueHdSampleRechunker = new TrueHdSampleRechunker();
            goto label_334;
        label_303:
            v1 = "video/hevc";
            HevcConfig v2_2 = HevcConfig.parse(new ParsableByteArray(v0.codecPrivate));
            List v3_1 = v2_2.initializationData;
            int v2_3 = v2_2.nalUnitLengthFieldLength;
            goto label_318;
        label_242:
            v1 = "audio/eac3";
            goto label_334;
        label_244:
            v1 = "audio/ac3";
            goto label_334;
        label_180:
            v1 = "application/pgs";
            goto label_334;
        label_246:
            v1 = "audio/mpeg";
            goto label_249;
        label_182:
            v1 = "application/vobsub";
            goto label_254;
        label_311:
            v1 = "video/avc";
            AvcConfig v2_4 = AvcConfig.parse(new ParsableByteArray(v0.codecPrivate));
            v3_1 = v2_4.initializationData;
            v2_3 = v2_4.nalUnitLengthFieldLength;
        label_318:
            v0.nalUnitLengthFieldLength = v2_3;
            String v12_1 = v1;
            List v2_5 = v3_1;
            goto label_336;
        label_248:
            v1 = "audio/mpeg-L2";
        label_249:
            v12_1 = v1;
            v2_5 = ((List)v10);
            v15 = 4096;
            goto label_337;
        label_184:
            v1 = "text/x-ssa";
            goto label_334;
        label_186:
            v1 = "application/x-subrip";
            goto label_334;
        label_188:
            v1 = "audio/raw";
            v2_3 = Util.getPcmEncoding(v0.audioBitDepth);
            if(v2_3 != 0) {
                goto label_202;
            }

            v1 = "audio/x-unknown";
            String v2_6 = "MatroskaExtractor";
            StringBuilder v3_2 = new StringBuilder();
            goto label_196;
        label_253:
            v1 = "audio/mp4a-latm";
        label_254:
            v2_1 = v0.codecPrivate;
            goto label_255;
        label_322:
            v1 = "video/mp4v-es";
            if(v0.codecPrivate == null) {
                v2_5 = ((List)v10);
            }
            else {
                v2_1 = v0.codecPrivate;
            label_255:
                v2_5 = Collections.singletonList(v2_1);
            }

            v12_1 = v1;
            goto label_336;
        label_258:
            ArrayList v3_3 = new ArrayList(v8);
            ((List)v3_3).add(v0.codecPrivate);
            ((List)v3_3).add(ByteBuffer.allocate(v4).order(ByteOrder.nativeOrder()).putLong(v0.codecDelayNs).array());
            ((List)v3_3).add(ByteBuffer.allocate(v4).order(ByteOrder.nativeOrder()).putLong(v0.seekPreRollNs).array());
            v12_1 = "audio/opus";
            ArrayList v2_7 = v3_3;
            v15 = 5760;
            goto label_337;
        label_329:
            v1 = "video/mpeg2";
            goto label_334;
        label_331:
            v1 = "video/x-vnd.on2.vp9";
            goto label_334;
        label_333:
            v1 = "video/x-vnd.on2.vp8";
            goto label_334;
        label_207:
            v1 = "audio/raw";
            if(!Track.parseMsAcmCodecPrivate(new ParsableByteArray(v0.codecPrivate))) {
                goto label_221;
            }

            v2_3 = Util.getPcmEncoding(v0.audioBitDepth);
            if(v2_3 == 0) {
                v1 = "audio/x-unknown";
                v2_6 = "MatroskaExtractor";
                v3_2 = new StringBuilder();
            label_196:
                v3_2.append("Unsupported PCM bit depth: ");
                v3_2.append(v0.audioBitDepth);
                v4_1 = ". Setting mimeType to ";
            }
            else {
            label_202:
                v12_1 = v1;
                v18 = v2_3;
                v2_5 = ((List)v10);
                v15 = -1;
                goto label_338;
            label_221:
                v1 = "audio/x-unknown";
                v2_6 = "MatroskaExtractor";
                v3_2 = new StringBuilder();
                v4_1 = "Non-PCM MS/ACM is unsupported. Setting mimeType to ";
            }

            v3_2.append(v4_1);
            v3_2.append(v1);
            Log.w(v2_6, v3_2.toString());
        label_334:
            v12_1 = v1;
            v2_5 = ((List)v10);
        label_336:
            v15 = -1;
            goto label_337;
        label_282:
            v12_1 = "audio/vorbis";
            v2_5 = Track.parseVorbisCodecPrivate(v0.codecPrivate);
            v15 = 8192;
        label_337:
            v18 = -1;
        label_338:
            v1_1 = v0.flagDefault | 0;
            if(v0.flagForced) {
                v6 = 2;
            }

            v1_1 |= v6;
            if(MimeTypes.isAudio(((String)v12))) {
                v1_3 = Format.createAudioSampleFormat(Integer.toString(arg28), ((String)v12), null, -1, v15, v0.channelCount, v0.sampleRate, v18, ((List)v2), v0.drmInitData, v1_1, v0.language);
                v8 = 1;
            }
            else if(MimeTypes.isVideo(((String)v12))) {
                if(v0.displayUnit == 0) {
                    v1_1 = v0.displayWidth == v9 ? v0.width : v0.displayWidth;
                    v0.displayWidth = v1_1;
                    v1_1 = v0.displayHeight == v9 ? v0.height : v0.displayHeight;
                    v0.displayHeight = v1_1;
                }

                float v21 = v0.displayWidth == v9 || v0.displayHeight == v9 ? -1f : (((float)(v0.height * v0.displayWidth))) / (((float)(v0.width * v0.displayHeight)));
                if(v0.hasColorInfo) {
                    v10 = new ColorInfo(v0.colorSpace, v0.colorRange, v0.colorTransfer, this.getHdrStaticInfo());
                }

                v1_3 = Format.createVideoSampleFormat(Integer.toString(arg28), ((String)v12), null, -1, v15, v0.width, v0.height, -1f, ((List)v2), -1, v21, v0.projectionData, v0.stereoMode, v10, v0.drmInitData);
                v8 = 2;
            }
            else {
                if("application/x-subrip".equals(v12)) {
                    v1_3 = Format.createTextSampleFormat(Integer.toString(arg28), ((String)v12), v1_1, v0.language, v0.drmInitData);
                    goto label_477;
                }

                if("text/x-ssa".equals(v12)) {
                    v2_7 = new ArrayList(v7);
                    ((List)v2_7).add(MatroskaExtractor.SSA_DIALOGUE_FORMAT);
                    ((List)v2_7).add(v0.codecPrivate);
                    v1_3 = Format.createTextSampleFormat(Integer.toString(arg28), ((String)v12), null, -1, v1_1, v0.language, -1, v0.drmInitData, 9223372036854775807L, v2_7);
                    goto label_477;
                }

                if(!"application/vobsub".equals(v12) && !"application/pgs".equals(v12)) {
                    if("application/dvbsubs".equals(v12)) {
                    }
                    else {
                        throw new ParserException("Unexpected MIME type.");
                    }
                }

                v1_3 = Format.createImageSampleFormat(Integer.toString(arg28), ((String)v12), null, -1, v1_1, ((List)v2), v0.language, v0.drmInitData);
            }

        label_477:
            v0.output = arg27.track(v0.number, v8);
            v0.output.format(v1_3);
        }

        public void outputPendingSampleMetadata() {
            if(this.trueHdSampleRechunker != null) {
                this.trueHdSampleRechunker.outputPendingSampleMetadata(this);
            }
        }

        private static Pair parseFourCcPrivate(ParsableByteArray arg6) {
            // Method was not decompiled
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray arg8) {
            boolean v1;
            try {
                int v0 = arg8.readLittleEndianUnsignedShort();
                v1 = true;
                if(v0 == 1) {
                    return 1;
                }

                if(v0 != 65534) {
                    return 0;
                }

                arg8.setPosition(24);
                if(arg8.readLong() != MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits()) {
                    return false;
                }
                else if(arg8.readLong() != MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return false;
                }
            }
            catch(ArrayIndexOutOfBoundsException ) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }

            return v1;
        }

        private static List parseVorbisCodecPrivate(byte[] arg8) {
            int v6;
            try {
                int v2 = 2;
                if(arg8[0] == v2) {
                    int v3 = 1;
                    int v4 = 0;
                    while(true) {
                        v6 = -1;
                        if(arg8[v3] != v6) {
                            break;
                        }

                        v4 += 255;
                        ++v3;
                    }

                    int v5 = v3 + 1;
                    v4 += arg8[v3];
                    v3 = 0;
                    while(arg8[v5] == v6) {
                        v3 += 255;
                        ++v5;
                    }

                    v6 = v5 + 1;
                    v3 += arg8[v5];
                    if(arg8[v6] == 1) {
                        byte[] v1 = new byte[v4];
                        System.arraycopy(arg8, v6, v1, 0, v4);
                        v6 += v4;
                        if(arg8[v6] == 3) {
                            v6 += v3;
                            if(arg8[v6] == 5) {
                                byte[] v3_1 = new byte[arg8.length - v6];
                                System.arraycopy(arg8, v6, v3_1, 0, arg8.length - v6);
                                ArrayList v8 = new ArrayList(v2);
                                ((List)v8).add(v1);
                                ((List)v8).add(v3_1);
                                return ((List)v8);
                            }

                            throw new ParserException("Error parsing vorbis codec private");
                        }

                        throw new ParserException("Error parsing vorbis codec private");
                    }

                    throw new ParserException("Error parsing vorbis codec private");
                }

                throw new ParserException("Error parsing vorbis codec private");
            }
            catch(ArrayIndexOutOfBoundsException ) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        public void reset() {
            if(this.trueHdSampleRechunker != null) {
                this.trueHdSampleRechunker.reset();
            }
        }
    }

    final class TrueHdSampleRechunker {
        private int blockFlags;
        private int chunkSize;
        private boolean foundSyncframe;
        private int sampleCount;
        private final byte[] syncframePrefix;
        private long timeUs;

        public TrueHdSampleRechunker() {
            super();
            this.syncframePrefix = new byte[10];
        }

        public void outputPendingSampleMetadata(Track arg9) {
            if((this.foundSyncframe) && this.sampleCount > 0) {
                arg9.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, arg9.cryptoData);
                this.sampleCount = 0;
            }
        }

        public void reset() {
            this.foundSyncframe = false;
        }

        public void sampleMetadata(Track arg8, long arg9) {
            if(!this.foundSyncframe) {
                return;
            }

            int v0 = this.sampleCount;
            this.sampleCount = v0 + 1;
            if(v0 == 0) {
                this.timeUs = arg9;
            }

            if(this.sampleCount < 16) {
                return;
            }

            arg8.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, arg8.cryptoData);
            this.sampleCount = 0;
        }

        public void startSample(ExtractorInput arg4, int arg5, int arg6) {
            if(!this.foundSyncframe) {
                arg4.peekFully(this.syncframePrefix, 0, 10);
                arg4.resetPeekPosition();
                if(Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) == -1) {
                    return;
                }
                else {
                    this.foundSyncframe = true;
                    this.sampleCount = 0;
                }
            }

            if(this.sampleCount == 0) {
                this.blockFlags = arg5;
                this.chunkSize = 0;
            }

            this.chunkSize += arg6;
        }
    }

    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = null;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final byte[] SSA_DIALOGUE_FORMAT = null;
    private static final byte[] SSA_PREFIX = null;
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final byte[] SSA_TIMECODE_EMPTY = null;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = null;
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final byte[] SUBRIP_TIMECODE_EMPTY = null;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private static final UUID WAVE_SUBFORMAT_PCM;
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    static {
        MatroskaExtractor.FACTORY = -$$Lambda$MatroskaExtractor$jNXW0tyYIOPE6N2jicocV6rRvBs.INSTANCE;
        MatroskaExtractor.SUBRIP_PREFIX = new byte[]{49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
        MatroskaExtractor.SUBRIP_TIMECODE_EMPTY = new byte[]{32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
        MatroskaExtractor.SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
        MatroskaExtractor.SSA_PREFIX = new byte[]{68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
        MatroskaExtractor.SSA_TIMECODE_EMPTY = new byte[]{32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
        MatroskaExtractor.WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int arg2) {
        this(new DefaultEbmlReader(), arg2);
    }

    MatroskaExtractor(EbmlReader arg5, int arg6) {
        super();
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = arg5;
        this.reader.init(new InnerEbmlReaderOutput(this, null));
        boolean v5 = true;
        if((arg6 & 1) == 0) {
        }
        else {
            v5 = false;
        }

        this.seekForCuesEnabled = v5;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    static byte[] access$300() {
        return MatroskaExtractor.SSA_DIALOGUE_FORMAT;
    }

    static UUID access$400() {
        return MatroskaExtractor.WAVE_SUBFORMAT_PCM;
    }

    void binaryElement(int arg20, int arg21, ExtractorInput arg22) {
        byte[] v1_1;
        long v7_1;
        int v14;
        MatroskaExtractor v0 = this;
        int v1 = arg20;
        int v2 = arg21;
        ExtractorInput v3 = arg22;
        int v5 = 163;
        int v6 = 4;
        int v7 = 0;
        int v8 = 1;
        if(v1 == 161 || v1 == v5) {
            int v9 = 8;
            if(v0.blockState == 0) {
                v0.blockTrackNumber = ((int)v0.varintReader.readUnsignedVarint(v3, false, true, v9));
                v0.blockTrackNumberLength = v0.varintReader.getLastLength();
                v0.blockDurationUs = -9223372036854775807L;
                v0.blockState = 1;
                v0.scratch.reset();
            }

            Object v4 = v0.tracks.get(v0.blockTrackNumber);
            if(v4 == null) {
                v3.skipFully(v2 - v0.blockTrackNumberLength);
                v0.blockState = 0;
                return;
            }

            if(v0.blockState == 1) {
                int v10 = 3;
                v0.readScratch(v3, v10);
                int v12 = 2;
                int v11 = (v0.scratch.data[v12] & 6) >> 1;
                int v13 = 255;
                if(v11 == 0) {
                    v0.blockLacingSampleCount = 1;
                    v0.blockLacingSampleSizes = MatroskaExtractor.ensureArrayCapacity(v0.blockLacingSampleSizes, 1);
                    v0.blockLacingSampleSizes[0] = v2 - v0.blockTrackNumberLength - v10;
                }
                else if(v1 == v5) {
                    v0.readScratch(v3, v6);
                    v0.blockLacingSampleCount = (v0.scratch.data[v10] & v13) + 1;
                    v0.blockLacingSampleSizes = MatroskaExtractor.ensureArrayCapacity(v0.blockLacingSampleSizes, v0.blockLacingSampleCount);
                    if(v11 == v12) {
                        Arrays.fill(v0.blockLacingSampleSizes, 0, v0.blockLacingSampleCount, (v2 - v0.blockTrackNumberLength - v6) / v0.blockLacingSampleCount);
                    }
                    else if(v11 == 1) {
                        v6 = 0;
                        v10 = 4;
                        v11 = 0;
                        while(v6 < v0.blockLacingSampleCount - 1) {
                            v0.blockLacingSampleSizes[v6] = 0;
                            do {
                                ++v10;
                                v0.readScratch(v3, v10);
                                v14 = v0.scratch.data[v10 - 1] & v13;
                                v0.blockLacingSampleSizes[v6] += v14;
                            }
                            while(v14 == v13);

                            v11 += v0.blockLacingSampleSizes[v6];
                            ++v6;
                        }

                        v0.blockLacingSampleSizes[v0.blockLacingSampleCount - 1] = v2 - v0.blockTrackNumberLength - v10 - v11;
                    }
                    else if(v11 == v10) {
                        v6 = 0;
                        v10 = 4;
                        v11 = 0;
                        while(true) {
                            if(v6 < v0.blockLacingSampleCount - v8) {
                                v0.blockLacingSampleSizes[v6] = v7;
                                ++v10;
                                v0.readScratch(v3, v10);
                                int v15 = v10 - 1;
                                if(v0.scratch.data[v15] != 0) {
                                    long v16 = 0;
                                    v14 = 0;
                                    while(true) {
                                        if(v14 < v9) {
                                            int v18 = v8 << 7 - v14;
                                            if((v0.scratch.data[v15] & v18) != 0) {
                                                v10 += v14;
                                                v0.readScratch(v3, v10);
                                                v7_1 = ((long)(v0.scratch.data[v15] & v13 & (v18 ^ -1)));
                                                v5 = v15 + 1;
                                                break;
                                            }
                                            else {
                                                ++v14;
                                                v8 = 1;
                                                v13 = 255;
                                                continue;
                                            }
                                        }

                                        goto label_239;
                                    }

                                    while(true) {
                                        v16 = v7_1;
                                        if(v5 >= v10) {
                                            break;
                                        }

                                        v7_1 = v16 << v9 | (((long)(v0.scratch.data[v5] & v13)));
                                        ++v5;
                                        v13 = 255;
                                    }

                                    if(v6 > 0) {
                                        v16 -= (1 << v14 * 7 + 6) - 1;
                                    }

                                label_239:
                                    v7_1 = v16;
                                    if(v7_1 >= -2147483648 && v7_1 <= 2147483647) {
                                        v5 = ((int)v7_1);
                                        int[] v7_2 = v0.blockLacingSampleSizes;
                                        if(v6 == 0) {
                                        }
                                        else {
                                            v5 += v0.blockLacingSampleSizes[v6 - 1];
                                        }

                                        v7_2[v6] = v5;
                                        v11 += v0.blockLacingSampleSizes[v6];
                                        ++v6;
                                        v7 = 0;
                                        v8 = 1;
                                        v13 = 255;
                                        continue;
                                    }

                                    break;
                                }
                                else {
                                    goto label_267;
                                }
                            }
                            else {
                                goto label_271;
                            }

                            goto label_280;
                        }

                        throw new ParserException("EBML lacing sample size out of range.");
                    label_267:
                        throw new ParserException("No valid varint length mask found");
                    label_271:
                        v0.blockLacingSampleSizes[v0.blockLacingSampleCount - 1] = v2 - v0.blockTrackNumberLength - v10 - v11;
                    }
                    else {
                        goto label_331;
                    }
                }
                else {
                    goto label_340;
                }

            label_280:
                v0.blockTimeUs = v0.clusterTimecodeUs + v0.scaleTimecodeToUs(((long)(v0.scratch.data[0] << v9 | v0.scratch.data[1] & 255)));
                v5 = 2;
                v2 = (v0.scratch.data[v5] & v9) == v9 ? 1 : 0;
                if(((Track)v4).type != v5) {
                    if(v1 == 163 && (v0.scratch.data[v5] & 128) == 128) {
                        goto label_319;
                    }

                    v5 = 0;
                }
                else {
                label_319:
                    v5 = 1;
                }

                v7 = v2 != 0 ? -2147483648 : 0;
                v0.blockFlags = v5 | v7;
                v0.blockState = 2;
                v0.blockLacingSampleIndex = 0;
                goto label_344;
            label_331:
                StringBuilder v2_1 = new StringBuilder();
                v2_1.append("Unexpected lacing value: ");
                v2_1.append(v11);
                throw new ParserException(v2_1.toString());
            label_340:
                throw new ParserException("Lacing only supported in SimpleBlocks.");
            }

        label_344:
            if(v1 == 163) {
                while(v0.blockLacingSampleIndex < v0.blockLacingSampleCount) {
                    v0.writeSampleData(v3, ((Track)v4), v0.blockLacingSampleSizes[v0.blockLacingSampleIndex]);
                    v0.commitSampleToOutput(((Track)v4), v0.blockTimeUs + (((long)(v0.blockLacingSampleIndex * ((Track)v4).defaultSampleDurationNs / 1000))));
                    ++v0.blockLacingSampleIndex;
                }

                v0.blockState = 0;
                return;
            }

            v0.writeSampleData(v3, ((Track)v4), v0.blockLacingSampleSizes[0]);
        }
        else {
            if(v1 == 16981) {
                v0.currentTrack.sampleStrippedBytes = new byte[v2];
                v1_1 = v0.currentTrack.sampleStrippedBytes;
            }
            else if(v1 == 18402) {
                v1_1 = new byte[v2];
                v3.readFully(v1_1, 0, v2);
                v0.currentTrack.cryptoData = new CryptoData(1, v1_1, 0, 0);
                return;
            }
            else if(v1 == 21419) {
                Arrays.fill(v0.seekEntryIdBytes.data, 0);
                v3.readFully(v0.seekEntryIdBytes.data, v6 - v2, v2);
                v0.seekEntryIdBytes.setPosition(0);
                v0.seekEntryId = ((int)v0.seekEntryIdBytes.readUnsignedInt());
                return;
            }
            else if(v1 == 25506) {
                v0.currentTrack.codecPrivate = new byte[v2];
                v1_1 = v0.currentTrack.codecPrivate;
            }
            else if(v1 == 30322) {
                v0.currentTrack.projectionData = new byte[v2];
                v1_1 = v0.currentTrack.projectionData;
            }
            else {
                StringBuilder v3_1 = new StringBuilder();
                v3_1.append("Unexpected id: ");
                v3_1.append(v1);
                throw new ParserException(v3_1.toString());
            }

            v3.readFully(v1_1, 0, v2);
        }
    }

    private SeekMap buildSeekMap() {
        // Method was not decompiled
    }

    private void commitSampleToOutput(Track arg16, long arg17) {
        byte[] v6;
        long v4;
        int v3;
        String v2;
        MatroskaExtractor v7 = this;
        Track v8 = arg16;
        if(v8.trueHdSampleRechunker != null) {
            v8.trueHdSampleRechunker.sampleMetadata(v8, arg17);
        }
        else {
            if("S_TEXT/UTF8".equals(v8.codecId)) {
                v2 = "%02d:%02d:%02d,%03d";
                v3 = 19;
                v4 = 1000;
                v6 = MatroskaExtractor.SUBRIP_TIMECODE_EMPTY;
                goto label_17;
            }
            else if("S_TEXT/ASS".equals(v8.codecId)) {
                v2 = "%01d:%02d:%02d:%02d";
                v3 = 21;
                v4 = 10000;
                v6 = MatroskaExtractor.SSA_TIMECODE_EMPTY;
            label_17:
                this.commitSubtitleSample(arg16, v2, v3, v4, v6);
            }

            v8.output.sampleMetadata(arg17, v7.blockFlags, v7.sampleBytesWritten, 0, v8.cryptoData);
        }

        v7.sampleRead = true;
        this.resetSample();
    }

    private void commitSubtitleSample(Track arg10, String arg11, int arg12, long arg13, byte[] arg15) {
        MatroskaExtractor.setSampleDuration(this.subtitleSample.data, this.blockDurationUs, arg11, arg12, arg13, arg15);
        arg10.output.sampleData(this.subtitleSample, this.subtitleSample.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    void endMasterElement(int arg8) {
        if(arg8 == 160) {
            if(this.blockState != 2) {
                return;
            }

            if(!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }

            this.commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
        else if(arg8 != 174) {
            int v3 = 475249515;
            if(arg8 == 19899) {
                if(this.seekEntryId != -1 && this.seekEntryPosition != -1) {
                    if(this.seekEntryId == v3) {
                        this.cuesContentPosition = this.seekEntryPosition;
                    }
                    else {
                    }

                    return;
                }

                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            }
            else if(arg8 == 25152) {
                if(!this.currentTrack.hasContentEncryption) {
                    return;
                }

                if(this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new SchemeData[]{new SchemeData(C.UUID_NIL, "video/webm", this.currentTrack.cryptoData.encryptionKey)});
                    return;
                }

                throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
            }
            else if(arg8 == 28032) {
                if(!this.currentTrack.hasContentEncryption) {
                    return;
                }

                if(this.currentTrack.sampleStrippedBytes == null) {
                    return;
                }

                throw new ParserException("Combining encryption and compression is not supported");
            }
            else if(arg8 == 357149030) {
                long v2 = -9223372036854775807L;
                if(this.timecodeScale == v2) {
                    this.timecodeScale = 1000000;
                }

                if(this.durationTimecode == v2) {
                    return;
                }

                this.durationUs = this.scaleTimecodeToUs(this.durationTimecode);
            }
            else if(arg8 != 374648427) {
                if(arg8 != v3) {
                }
                else if(!this.sentSeekMap) {
                    this.extractorOutput.seekMap(this.buildSeekMap());
                    this.sentSeekMap = true;
                }
            }
            else if(this.tracks.size() != 0) {
                this.extractorOutput.endTracks();
            }
            else {
                throw new ParserException("No valid tracks were found");
            }
        }
        else {
            if(MatroskaExtractor.isCodecSupported(this.currentTrack.codecId)) {
                this.currentTrack.initializeOutput(this.extractorOutput, this.currentTrack.number);
                this.tracks.put(this.currentTrack.number, this.currentTrack);
            }

            this.currentTrack = null;
        }
    }

    private static int[] ensureArrayCapacity(int[] arg1, int arg2) {
        if(arg1 == null) {
            return new int[arg2];
        }

        if(arg1.length >= arg2) {
            return arg1;
        }

        return new int[Math.max(arg1.length * 2, arg2)];
    }

    void floatElement(int arg2, double arg3) {
        if(arg2 == 181) {
            this.currentTrack.sampleRate = ((int)arg3);
        }
        else if(arg2 != 17545) {
            switch(arg2) {
                case 21969: {
                    goto label_42;
                }
                case 21970: {
                    goto label_38;
                }
                case 21971: {
                    goto label_34;
                }
                case 21972: {
                    goto label_30;
                }
                case 21973: {
                    goto label_26;
                }
                case 21974: {
                    goto label_22;
                }
                case 21975: {
                    goto label_18;
                }
                case 21976: {
                    goto label_14;
                }
                case 21977: {
                    goto label_10;
                }
                case 21978: {
                    goto label_6;
                }
            }

            return;
        label_34:
            this.currentTrack.primaryGChromaticityX = ((float)arg3);
            return;
        label_18:
            this.currentTrack.whitePointChromaticityX = ((float)arg3);
            return;
        label_38:
            this.currentTrack.primaryRChromaticityY = ((float)arg3);
            return;
        label_22:
            this.currentTrack.primaryBChromaticityY = ((float)arg3);
            return;
        label_6:
            this.currentTrack.minMasteringLuminance = ((float)arg3);
            return;
        label_42:
            this.currentTrack.primaryRChromaticityX = ((float)arg3);
            return;
        label_26:
            this.currentTrack.primaryBChromaticityX = ((float)arg3);
            return;
        label_10:
            this.currentTrack.maxMasteringLuminance = ((float)arg3);
            return;
        label_30:
            this.currentTrack.primaryGChromaticityY = ((float)arg3);
            return;
        label_14:
            this.currentTrack.whitePointChromaticityY = ((float)arg3);
        }
        else {
            this.durationTimecode = ((long)arg3);
        }
    }

    public void init(ExtractorOutput arg1) {
        this.extractorOutput = arg1;
    }

    void integerElement(int arg8, long arg9) {
        Track v8;
        StringBuilder v0_1;
        int v0 = 6;
        int v1 = 2;
        boolean v2 = false;
        int v3 = 3;
        long v4 = 1;
        switch(arg8) {
            case 131: {
                this.currentTrack.type = ((int)arg9);
                break;
            }
            case 136: {
                v8 = this.currentTrack;
                if(arg9 == v4) {
                    v2 = true;
                }

                v8.flagDefault = v2;
                break;
            }
            case 155: {
                this.blockDurationUs = this.scaleTimecodeToUs(arg9);
                break;
            }
            case 159: {
                this.currentTrack.channelCount = ((int)arg9);
                break;
            }
            case 176: {
                this.currentTrack.width = ((int)arg9);
                break;
            }
            case 179: {
                this.cueTimesUs.add(this.scaleTimecodeToUs(arg9));
                break;
            }
            case 186: {
                this.currentTrack.height = ((int)arg9);
                break;
            }
            case 215: {
                goto label_222;
            }
            case 231: {
                goto label_219;
            }
            case 241: {
                goto label_213;
            }
            case 251: {
                goto label_211;
            }
            case 16980: {
                goto label_197;
            }
            case 17029: {
                if(arg9 >= v4 && arg9 <= 2) {
                    return;
                }

                v0_1 = new StringBuilder();
                v0_1.append("DocTypeReadVersion ");
                v0_1.append(arg9);
                v0_1.append(" not supported");
                throw new ParserException(v0_1.toString());
            }
            case 17143: {
                if(arg9 == v4) {
                    return;
                }

                v0_1 = new StringBuilder();
                v0_1.append("EBMLReadVersion ");
                v0_1.append(arg9);
                v0_1.append(" not supported");
                throw new ParserException(v0_1.toString());
            }
            case 18401: {
                goto label_155;
            }
            case 18408: {
                goto label_142;
            }
            case 20529: {
                if(arg9 == 0) {
                    return;
                }

                v0_1 = new StringBuilder();
                v0_1.append("ContentEncodingOrder ");
                v0_1.append(arg9);
                v0_1.append(" not supported");
                throw new ParserException(v0_1.toString());
            }
            case 20530: {
                if(arg9 == v4) {
                    return;
                }

                v0_1 = new StringBuilder();
                v0_1.append("ContentEncodingScope ");
                v0_1.append(arg9);
                v0_1.append(" not supported");
                throw new ParserException(v0_1.toString());
            }
            case 21420: {
                this.seekEntryPosition = arg9 + this.segmentContentPosition;
                break;
            }
            case 21432: {
                goto label_93;
            }
            case 21680: {
                goto label_89;
            }
            case 21682: {
                goto label_85;
            }
            case 21690: {
                goto label_81;
            }
            case 21930: {
                goto label_76;
            }
            case 21945: {
                switch(((int)arg9)) {
                    case 1: {
                        this.currentTrack.colorRange = v1;
                        return;
                    label_197:
                        if(arg9 == 3) {
                            return;
                        }

                        v0_1 = new StringBuilder();
                        v0_1.append("ContentCompAlgo ");
                        v0_1.append(arg9);
                        v0_1.append(" not supported");
                        throw new ParserException(v0_1.toString());
                    label_8:
                        this.timecodeScale = arg9;
                        return;
                    label_10:
                        this.currentTrack.defaultSampleDurationNs = ((int)arg9);
                        return;
                    label_76:
                        v8 = this.currentTrack;
                        if(arg9 == v4) {
                            v2 = true;
                        }

                        v8.flagForced = v2;
                        return;
                    label_14:
                        this.currentTrack.audioBitDepth = ((int)arg9);
                        return;
                    label_142:
                        if(arg9 == v4) {
                            return;
                        }

                        v0_1 = new StringBuilder();
                        v0_1.append("AESSettingsCipherMode ");
                        v0_1.append(arg9);
                        v0_1.append(" not supported");
                        throw new ParserException(v0_1.toString());
                    label_81:
                        this.currentTrack.displayHeight = ((int)arg9);
                        return;
                    label_18:
                        this.currentTrack.seekPreRollNs = arg9;
                        return;
                    label_211:
                        this.sampleSeenReferenceBlock = true;
                        return;
                    label_21:
                        this.currentTrack.codecDelayNs = arg9;
                        return;
                    label_213:
                        if(this.seenClusterPositionForCurrentCuePoint) {
                            return;
                        }

                        this.cueClusterPositions.add(arg9);
                        this.seenClusterPositionForCurrentCuePoint = true;
                        return;
                    label_85:
                        this.currentTrack.displayUnit = ((int)arg9);
                        return;
                    label_24:
                        this.currentTrack.maxFrameAverageLuminance = ((int)arg9);
                        return;
                    label_89:
                        this.currentTrack.displayWidth = ((int)arg9);
                        return;
                    label_155:
                        if(arg9 == 5) {
                            return;
                        }

                        v0_1 = new StringBuilder();
                        v0_1.append("ContentEncAlgo ");
                        v0_1.append(arg9);
                        v0_1.append(" not supported");
                        throw new ParserException(v0_1.toString());
                    label_219:
                        this.clusterTimecodeUs = this.scaleTimecodeToUs(arg9);
                        return;
                    label_28:
                        this.currentTrack.maxContentLuminance = ((int)arg9);
                        return;
                    label_93:
                        arg8 = ((int)arg9);
                        if(arg8 == v3) {
                            goto label_108;
                        }

                        if(arg8 == 15) {
                            goto label_105;
                        }

                        switch(arg8) {
                            case 0: {
                                this.currentTrack.stereoMode = 0;
                                return;
                            label_105:
                                this.currentTrack.stereoMode = v3;
                                return;
                            label_108:
                                this.currentTrack.stereoMode = 1;
                                return;
                            label_222:
                                this.currentTrack.number = ((int)arg9);
                                return;
                            label_32:
                                this.currentTrack.hasColorInfo = true;
                                arg8 = ((int)arg9);
                                if(arg8 == 1) {
                                    goto label_46;
                                }

                                if(arg8 == 9) {
                                    goto label_43;
                                }

                                switch(arg8) {
                                    case 4: 
                                    case 5: 
                                    case 6: 
                                    case 7: {
                                        goto label_40;
                                    }
                                }

                                return;
                            label_40:
                                this.currentTrack.colorSpace = v1;
                                return;
                            label_43:
                                this.currentTrack.colorSpace = v0;
                                return;
                            label_46:
                                this.currentTrack.colorSpace = 1;
                                return;
                            }
                            case 1: {
                                this.currentTrack.stereoMode = v1;
                                return;
                            }
                            default: {
                                return;
                            }
                        }
                    }
                    case 2: {
                        this.currentTrack.colorRange = 1;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            case 21946: {
                arg8 = ((int)arg9);
                if(arg8 != 1) {
                    if(arg8 == 16) {
                        goto label_61;
                    }
                    else if(arg8 != 18) {
                        switch(arg8) {
                            case 6: 
                            case 7: {
                                goto label_64;
                            }
                            default: {
                                return;
                            label_57:
                                this.currentTrack.colorTransfer = 7;
                                return;
                            label_61:
                                this.currentTrack.colorTransfer = v0;
                                return;
                            }
                        }
                    }
                    else {
                        goto label_57;
                    }
                }

            label_64:
                this.currentTrack.colorTransfer = v3;
                break;
            }
            case 21947: {
                goto label_32;
            }
            case 21948: {
                goto label_28;
            }
            case 21949: {
                goto label_24;
            }
            case 22186: {
                goto label_21;
            }
            case 22203: {
                goto label_18;
            }
            case 25188: {
                goto label_14;
            }
            case 2352003: {
                goto label_10;
            }
            case 2807729: {
                goto label_8;
            }
            default: {
                break;
            }
        }
    }

    private static boolean isCodecSupported(String arg1) {
        boolean v1 = ("V_VP8".equals(arg1)) || ("V_VP9".equals(arg1)) || ("V_MPEG2".equals(arg1)) || ("V_MPEG4/ISO/SP".equals(arg1)) || ("V_MPEG4/ISO/ASP".equals(arg1)) || ("V_MPEG4/ISO/AP".equals(arg1)) || ("V_MPEG4/ISO/AVC".equals(arg1)) || ("V_MPEGH/ISO/HEVC".equals(arg1)) || ("V_MS/VFW/FOURCC".equals(arg1)) || ("V_THEORA".equals(arg1)) || ("A_OPUS".equals(arg1)) || ("A_VORBIS".equals(arg1)) || ("A_AAC".equals(arg1)) || ("A_MPEG/L2".equals(arg1)) || ("A_MPEG/L3".equals(arg1)) || ("A_AC3".equals(arg1)) || ("A_EAC3".equals(arg1)) || ("A_TRUEHD".equals(arg1)) || ("A_DTS".equals(arg1)) || ("A_DTS/EXPRESS".equals(arg1)) || ("A_DTS/LOSSLESS".equals(arg1)) || ("A_FLAC".equals(arg1)) || ("A_MS/ACM".equals(arg1)) || ("A_PCM/INT/LIT".equals(arg1)) || ("S_TEXT/UTF8".equals(arg1)) || ("S_TEXT/ASS".equals(arg1)) || ("S_VOBSUB".equals(arg1)) || ("S_HDMV/PGS".equals(arg1)) || ("S_DVBSUB".equals(arg1)) ? true : false;
        return v1;
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    private boolean maybeSeekForCues(PositionHolder arg6, long arg7) {
        if(this.seekForCues) {
            this.seekPositionAfterBuildingCues = arg7;
            arg6.position = this.cuesContentPosition;
            this.seekForCues = false;
            return 1;
        }

        if(this.sentSeekMap) {
            long v3 = -1;
            if(this.seekPositionAfterBuildingCues != v3) {
                arg6.position = this.seekPositionAfterBuildingCues;
                this.seekPositionAfterBuildingCues = v3;
                return 1;
            }
        }

        return 0;
    }

    public int read(ExtractorInput arg6, PositionHolder arg7) {
        int v0 = 0;
        this.sampleRead = false;
        boolean v2 = true;
        do {
            if((v2) && !this.sampleRead) {
                v2 = this.reader.read(arg6);
                if(!v2) {
                    continue;
                }

                if(!this.maybeSeekForCues(arg7, arg6.getPosition())) {
                    continue;
                }

                return 1;
            }

            goto label_14;
        }
        while(true);

        return 1;
    label_14:
        if(v2) {
            return 0;
        }

        while(v0 < this.tracks.size()) {
            this.tracks.valueAt(v0).outputPendingSampleMetadata();
            ++v0;
        }

        return -1;
    }

    private void readScratch(ExtractorInput arg4, int arg5) {
        if(this.scratch.limit() >= arg5) {
            return;
        }

        if(this.scratch.capacity() < arg5) {
            this.scratch.reset(Arrays.copyOf(this.scratch.data, Math.max(this.scratch.data.length * 2, arg5)), this.scratch.limit());
        }

        arg4.readFully(this.scratch.data, this.scratch.limit(), arg5 - this.scratch.limit());
        this.scratch.setLimit(arg5);
    }

    private int readToOutput(ExtractorInput arg2, TrackOutput arg3, int arg4) {
        int v2;
        int v0 = this.sampleStrippedBytes.bytesLeft();
        if(v0 > 0) {
            v2 = Math.min(arg4, v0);
            arg3.sampleData(this.sampleStrippedBytes, v2);
        }
        else {
            v2 = arg3.sampleData(arg2, arg4, false);
        }

        this.sampleBytesRead += v2;
        this.sampleBytesWritten += v2;
        return v2;
    }

    private void readToTarget(ExtractorInput arg4, byte[] arg5, int arg6, int arg7) {
        int v0 = Math.min(arg7, this.sampleStrippedBytes.bytesLeft());
        arg4.readFully(arg5, arg6 + v0, arg7 - v0);
        if(v0 > 0) {
            this.sampleStrippedBytes.readBytes(arg5, arg6, v0);
        }

        this.sampleBytesRead += arg7;
    }

    public void release() {
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private long scaleTimecodeToUs(long arg12) {
        if(this.timecodeScale != -9223372036854775807L) {
            return Util.scaleLargeTimestamp(arg12, this.timecodeScale, 1000);
        }

        throw new ParserException("Can\'t scale timecode prior to timecodeScale being set.");
    }

    public void seek(long arg1, long arg3) {
        this.clusterTimecodeUs = -9223372036854775807L;
        int v1 = 0;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        this.resetSample();
        while(v1 < this.tracks.size()) {
            this.tracks.valueAt(v1).reset();
            ++v1;
        }
    }

    private static void setSampleDuration(byte[] arg8, long arg9, String arg11, int arg12, long arg13, byte[] arg15) {
        // Method was not decompiled
    }

    public boolean sniff(ExtractorInput arg2) {
        return new Sniffer().sniff(arg2);
    }

    void startMasterElement(int arg6, long arg7, long arg9) {
        if(arg6 == 160) {
            this.sampleSeenReferenceBlock = false;
        }
        else if(arg6 == 174) {
            this.currentTrack = new Track(null);
        }
        else if(arg6 != 187) {
            long v1 = -1;
            if(arg6 == 19899) {
                this.seekEntryId = -1;
                this.seekEntryPosition = v1;
            }
            else if(arg6 == 20533) {
                this.currentTrack.hasContentEncryption = true;
            }
            else if(arg6 == 21968) {
                this.currentTrack.hasColorInfo = true;
            }
            else if(arg6 != 25152) {
                if(arg6 == 408125543) {
                    if(this.segmentContentPosition != v1) {
                        if(this.segmentContentPosition == arg7) {
                        }
                        else {
                            throw new ParserException("Multiple Segment elements not supported");
                        }
                    }

                    this.segmentContentPosition = arg7;
                    this.segmentContentSize = arg9;
                }
                else if(arg6 == 475249515) {
                    this.cueTimesUs = new LongArray();
                    this.cueClusterPositions = new LongArray();
                }
                else if(arg6 != 524531317) {
                }
                else if(!this.sentSeekMap) {
                    if((this.seekForCuesEnabled) && this.cuesContentPosition != v1) {
                        this.seekForCues = true;
                        return;
                    }

                    this.extractorOutput.seekMap(new Unseekable(this.durationUs));
                    this.sentSeekMap = true;
                }
            }
        }
        else {
            this.seenClusterPositionForCurrentCuePoint = false;
        }
    }

    void stringElement(int arg3, String arg4) {
        if(arg3 == 134) {
            this.currentTrack.codecId = arg4;
        }
        else if(arg3 != 17026) {
            if(arg3 != 2274716) {
            }
            else {
                Track.access$202(this.currentTrack, arg4);
            }
        }
        else if(!"webm".equals(arg4)) {
            if("matroska".equals(arg4)) {
            }
            else {
                StringBuilder v0 = new StringBuilder();
                v0.append("DocType ");
                v0.append(arg4);
                v0.append(" not supported");
                throw new ParserException(v0.toString());
            }
        }
    }

    private void writeSampleData(ExtractorInput arg11, Track arg12, int arg13) {
        int v8;
        byte[] v12;
        if("S_TEXT/UTF8".equals(arg12.codecId)) {
            v12 = MatroskaExtractor.SUBRIP_PREFIX;
        }
        else if("S_TEXT/ASS".equals(arg12.codecId)) {
            v12 = MatroskaExtractor.SSA_PREFIX;
        }
        else {
            goto label_13;
        }

        this.writeSubtitleSampleData(arg11, v12, arg13);
        return;
    label_13:
        TrackOutput v0 = arg12.output;
        int v2 = 4;
        int v3 = 2;
        boolean v4 = true;
        if(!this.sampleEncodingHandled) {
            if(arg12.hasContentEncryption) {
                this.blockFlags &= -1073741825;
                int v6 = 128;
                if(!this.sampleSignalByteRead) {
                    arg11.readFully(this.scratch.data, 0, 1);
                    ++this.sampleBytesRead;
                    if((this.scratch.data[0] & v6) != v6) {
                        this.sampleSignalByte = this.scratch.data[0];
                        this.sampleSignalByteRead = true;
                    }
                    else {
                        throw new ParserException("Extension bit is set in signal byte");
                    }
                }

                int v1 = (this.sampleSignalByte & 1) == 1 ? 1 : 0;
                if(v1 == 0) {
                    goto label_191;
                }

                v1 = (this.sampleSignalByte & v3) == v3 ? 1 : 0;
                this.blockFlags |= 1073741824;
                if(!this.sampleInitializationVectorRead) {
                    v8 = 8;
                    arg11.readFully(this.encryptionInitializationVector.data, 0, v8);
                    this.sampleBytesRead += v8;
                    this.sampleInitializationVectorRead = true;
                    byte[] v7 = this.scratch.data;
                    if(v1 != 0) {
                    }
                    else {
                        v6 = 0;
                    }

                    v7[0] = ((byte)(v6 | v8));
                    this.scratch.setPosition(0);
                    v0.sampleData(this.scratch, 1);
                    ++this.sampleBytesWritten;
                    this.encryptionInitializationVector.setPosition(0);
                    v0.sampleData(this.encryptionInitializationVector, v8);
                    this.sampleBytesWritten += v8;
                }

                if(v1 == 0) {
                    goto label_191;
                }

                if(!this.samplePartitionCountRead) {
                    arg11.readFully(this.scratch.data, 0, 1);
                    ++this.sampleBytesRead;
                    this.scratch.setPosition(0);
                    this.samplePartitionCount = this.scratch.readUnsignedByte();
                    this.samplePartitionCountRead = true;
                }

                v1 = this.samplePartitionCount * 4;
                this.scratch.reset(v1);
                arg11.readFully(this.scratch.data, 0, v1);
                this.sampleBytesRead += v1;
                short v1_1 = ((short)(this.samplePartitionCount / v3 + 1));
                v6 = v1_1 * 6 + v3;
                if(this.encryptionSubsampleDataBuffer == null || this.encryptionSubsampleDataBuffer.capacity() < v6) {
                    this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(v6);
                }

                this.encryptionSubsampleDataBuffer.position(0);
                this.encryptionSubsampleDataBuffer.putShort(v1_1);
                v1 = 0;
                int v7_1;
                for(v7_1 = 0; v1 < this.samplePartitionCount; v7_1 = v8) {
                    v8 = this.scratch.readUnsignedIntToInt();
                    if(v1 % 2 == 0) {
                        this.encryptionSubsampleDataBuffer.putShort(((short)(v8 - v7_1)));
                    }
                    else {
                        this.encryptionSubsampleDataBuffer.putInt(v8 - v7_1);
                    }

                    ++v1;
                }

                v1 = arg13 - this.sampleBytesRead - v7_1;
                if(this.samplePartitionCount % v3 == 1) {
                    this.encryptionSubsampleDataBuffer.putInt(v1);
                }
                else {
                    this.encryptionSubsampleDataBuffer.putShort(((short)v1));
                    this.encryptionSubsampleDataBuffer.putInt(0);
                }

                this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), v6);
                v0.sampleData(this.encryptionSubsampleData, v6);
                this.sampleBytesWritten += v6;
            }
            else {
                if(arg12.sampleStrippedBytes == null) {
                    goto label_191;
                }

                this.sampleStrippedBytes.reset(arg12.sampleStrippedBytes, arg12.sampleStrippedBytes.length);
            }

        label_191:
            this.sampleEncodingHandled = true;
        }

        arg13 += this.sampleStrippedBytes.limit();
        if(("V_MPEG4/ISO/AVC".equals(arg12.codecId)) || ("V_MPEGH/ISO/HEVC".equals(arg12.codecId))) {
            byte[] v1_2 = this.nalLength.data;
            v1_2[0] = 0;
            v1_2[1] = 0;
            v1_2[v3] = 0;
            v3 = arg12.nalUnitLengthFieldLength;
            int v4_1 = 4 - arg12.nalUnitLengthFieldLength;
            while(this.sampleBytesRead < arg13) {
                if(this.sampleCurrentNalBytesRemaining == 0) {
                    this.readToTarget(arg11, v1_2, v4_1, v3);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    v0.sampleData(this.nalStartCode, v2);
                    this.sampleBytesWritten += v2;
                    continue;
                }

                this.sampleCurrentNalBytesRemaining -= this.readToOutput(arg11, v0, this.sampleCurrentNalBytesRemaining);
            }
        }
        else {
            if(arg12.trueHdSampleRechunker != null) {
                if(this.sampleStrippedBytes.limit() == 0) {
                }
                else {
                    v4 = false;
                }

                Assertions.checkState(v4);
                arg12.trueHdSampleRechunker.startSample(arg11, this.blockFlags, arg13);
            }

            while(this.sampleBytesRead < arg13) {
                this.readToOutput(arg11, v0, arg13 - this.sampleBytesRead);
            }
        }

        if("A_VORBIS".equals(arg12.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            v0.sampleData(this.vorbisNumPageSamples, v2);
            this.sampleBytesWritten += v2;
        }
    }

    private void writeSubtitleSampleData(ExtractorInput arg5, byte[] arg6, int arg7) {
        int v0 = arg6.length + arg7;
        if(this.subtitleSample.capacity() < v0) {
            this.subtitleSample.data = Arrays.copyOf(arg6, v0 + arg7);
        }
        else {
            System.arraycopy(arg6, 0, this.subtitleSample.data, 0, arg6.length);
        }

        arg5.readFully(this.subtitleSample.data, arg6.length, arg7);
        this.subtitleSample.reset(v0);
    }
}

