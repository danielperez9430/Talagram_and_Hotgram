package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TsExtractor implements Extractor {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Mode {
    }

    class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch;

        public PatReader(TsExtractor arg2) {
            TsExtractor.this = arg2;
            super();
            this.patScratch = new ParsableBitArray(new byte[4]);
        }

        public void consume(ParsableByteArray arg10) {
            if(arg10.readUnsignedByte() != 0) {
                return;
            }

            arg10.skipBytes(7);
            int v1 = 4;
            int v0 = arg10.bytesLeft() / v1;
            int v3;
            for(v3 = 0; v3 < v0; ++v3) {
                arg10.readBytes(this.patScratch, v1);
                int v4 = this.patScratch.readBits(16);
                this.patScratch.skipBits(3);
                int v5 = 13;
                if(v4 == 0) {
                    this.patScratch.skipBits(v5);
                }
                else {
                    v4 = this.patScratch.readBits(v5);
                    TsExtractor.this.tsPayloadReaders.put(v4, new SectionReader(new PmtReader(TsExtractor.this, v4)));
                    TsExtractor.access$108(TsExtractor.this);
                }
            }

            if(TsExtractor.this.mode != 2) {
                TsExtractor.this.tsPayloadReaders.remove(0);
            }
        }

        public void init(TimestampAdjuster arg1, ExtractorOutput arg2, TrackIdGenerator arg3) {
        }
    }

    class PmtReader implements SectionPayloadReader {
        private static final int TS_PMT_DESC_AC3 = 106;
        private static final int TS_PMT_DESC_DTS = 123;
        private static final int TS_PMT_DESC_DVBSUBS = 89;
        private static final int TS_PMT_DESC_EAC3 = 122;
        private static final int TS_PMT_DESC_ISO639_LANG = 10;
        private static final int TS_PMT_DESC_REGISTRATION = 5;
        private final int pid;
        private final ParsableBitArray pmtScratch;
        private final SparseIntArray trackIdToPidScratch;
        private final SparseArray trackIdToReaderScratch;

        public PmtReader(TsExtractor arg2, int arg3) {
            TsExtractor.this = arg2;
            super();
            this.pmtScratch = new ParsableBitArray(new byte[5]);
            this.trackIdToReaderScratch = new SparseArray();
            this.trackIdToPidScratch = new SparseIntArray();
            this.pid = arg3;
        }

        public void consume(ParsableByteArray arg17) {
            TimestampAdjuster v2;
            PmtReader v0 = this;
            ParsableByteArray v1 = arg17;
            int v3 = 2;
            if(arg17.readUnsignedByte() != v3) {
                return;
            }

            int v4 = 0;
            if(v0.this$0.mode == 1 || v0.this$0.mode == v3 || v0.this$0.remainingPmts == 1) {
                Object v2_1 = v0.this$0.timestampAdjusters.get(0);
            }
            else {
                v2 = new TimestampAdjuster(v0.this$0.timestampAdjusters.get(0).getFirstSampleTimestampUs());
                v0.this$0.timestampAdjusters.add(v2);
            }

            v1.skipBytes(v3);
            int v6 = arg17.readUnsignedShort();
            int v7 = 3;
            v1.skipBytes(v7);
            v1.readBytes(v0.pmtScratch, v3);
            v0.pmtScratch.skipBits(v7);
            int v10 = 13;
            v0.this$0.pcrPid = v0.pmtScratch.readBits(v10);
            v1.readBytes(v0.pmtScratch, v3);
            int v9 = 4;
            v0.pmtScratch.skipBits(v9);
            int v11 = 12;
            v1.skipBytes(v0.pmtScratch.readBits(v11));
            int v12 = 8192;
            int v13 = 21;
            if(v0.this$0.mode == v3 && v0.this$0.id3Reader == null) {
                v0.this$0.id3Reader = v0.this$0.payloadReaderFactory.createPayloadReader(v13, new EsInfo(v13, null, null, new byte[0]));
                v0.this$0.id3Reader.init(v2, v0.this$0.output, new TrackIdGenerator(v6, v13, v12));
            }

            v0.trackIdToReaderScratch.clear();
            v0.trackIdToPidScratch.clear();
            int v8 = arg17.bytesLeft();
            while(v8 > 0) {
                v1.readBytes(v0.pmtScratch, 5);
                int v14 = v0.pmtScratch.readBits(8);
                v0.pmtScratch.skipBits(v7);
                int v15 = v0.pmtScratch.readBits(v10);
                v0.pmtScratch.skipBits(v9);
                v7 = v0.pmtScratch.readBits(v11);
                EsInfo v9_1 = v0.readEsInfo(v1, v7);
                if(v14 == 6) {
                    v14 = v9_1.streamType;
                }

                v8 -= v7 + 5;
                v7 = v0.this$0.mode == v3 ? v14 : v15;
                if(v0.this$0.trackIds.get(v7)) {
                }
                else {
                    TsPayloadReader v9_2 = v0.this$0.mode != v3 || v14 != v13 ? v0.this$0.payloadReaderFactory.createPayloadReader(v14, v9_1) : v0.this$0.id3Reader;
                    if(v0.this$0.mode == v3 && v15 >= v0.trackIdToPidScratch.get(v7, v12)) {
                        goto label_134;
                    }

                    v0.trackIdToPidScratch.put(v7, v15);
                    v0.trackIdToReaderScratch.put(v7, v9_2);
                }

            label_134:
                v7 = 3;
                v9 = 4;
                v10 = 13;
            }

            int v1_1 = v0.trackIdToPidScratch.size();
            for(v7 = 0; v7 < v1_1; ++v7) {
                v8 = v0.trackIdToPidScratch.keyAt(v7);
                v9 = v0.trackIdToPidScratch.valueAt(v7);
                v0.this$0.trackIds.put(v8, true);
                v0.this$0.trackPids.put(v9, true);
                Object v10_1 = v0.trackIdToReaderScratch.valueAt(v7);
                if(v10_1 != null) {
                    if(v10_1 != v0.this$0.id3Reader) {
                        ((TsPayloadReader)v10_1).init(v2, v0.this$0.output, new TrackIdGenerator(v6, v8, v12));
                    }

                    v0.this$0.tsPayloadReaders.put(v9, v10_1);
                }
            }

            if(v0.this$0.mode != v3) {
                v0.this$0.tsPayloadReaders.remove(v0.pid);
                TsExtractor v1_2 = v0.this$0;
                if(v0.this$0.mode == 1) {
                }
                else {
                    v4 = v0.this$0.remainingPmts - 1;
                }

                v1_2.remainingPmts = v4;
                if(v0.this$0.remainingPmts != 0) {
                    return;
                }

                v0.this$0.output.endTracks();
            label_179:
                v0.this$0.tracksEnded = true;
            }
            else if(!v0.this$0.tracksEnded) {
                v0.this$0.output.endTracks();
                v0.this$0.remainingPmts = 0;
                goto label_179;
            }
        }

        public void init(TimestampAdjuster arg1, ExtractorOutput arg2, TrackIdGenerator arg3) {
        }

        private EsInfo readEsInfo(ParsableByteArray arg13, int arg14) {
            int v0 = arg13.getPosition();
            arg14 += v0;
            String v1 = null;
            int v2 = -1;
            List v3 = ((List)v1);
            while(arg13.getPosition() < arg14) {
                int v4 = arg13.readUnsignedByte();
                int v6 = arg13.getPosition() + arg13.readUnsignedByte();
                int v7 = 89;
                if(v4 == 5) {
                    long v4_1 = arg13.readUnsignedInt();
                    if(v4_1 == TsExtractor.AC3_FORMAT_IDENTIFIER) {
                        goto label_29;
                    }
                    else if(v4_1 == TsExtractor.E_AC3_FORMAT_IDENTIFIER) {
                        goto label_33;
                    }
                    else if(v4_1 == TsExtractor.HEVC_FORMAT_IDENTIFIER) {
                        v2 = 36;
                    }
                }
                else if(v4 == 106) {
                label_29:
                    v2 = 129;
                }
                else if(v4 == 122) {
                label_33:
                    v2 = 135;
                }
                else if(v4 == 123) {
                    v2 = 138;
                }
                else {
                    int v8 = 3;
                    if(v4 == 10) {
                        v1 = arg13.readString(v8).trim();
                    }
                    else if(v4 == v7) {
                        ArrayList v2_1 = new ArrayList();
                        while(arg13.getPosition() < v6) {
                            String v3_1 = arg13.readString(v8).trim();
                            v4 = arg13.readUnsignedByte();
                            byte[] v9 = new byte[4];
                            arg13.readBytes(v9, 0, 4);
                            ((List)v2_1).add(new DvbSubtitleInfo(v3_1, v4, v9));
                        }

                        ArrayList v3_2 = v2_1;
                        v2 = 89;
                    }
                }

                arg13.skipBytes(v6 - arg13.getPosition());
            }

            arg13.setPosition(arg14);
            return new EsInfo(v2, v1, v3, Arrays.copyOfRange(arg13.data, v0, arg14));
        }
    }

    private static final long AC3_FORMAT_IDENTIFIER = 0;
    private static final int BUFFER_SIZE = 9400;
    private static final long E_AC3_FORMAT_IDENTIFIER = 0;
    public static final ExtractorsFactory FACTORY = null;
    private static final long HEVC_FORMAT_IDENTIFIER = 0;
    private static final int MAX_PID_PLUS_ONE = 8192;
    public static final int MODE_HLS = 2;
    public static final int MODE_MULTI_PMT = 0;
    public static final int MODE_SINGLE_PMT = 1;
    private static final int SNIFF_TS_PACKET_COUNT = 5;
    public static final int TS_PACKET_SIZE = 188;
    private static final int TS_PAT_PID = 0;
    public static final int TS_STREAM_TYPE_AAC_ADTS = 15;
    public static final int TS_STREAM_TYPE_AAC_LATM = 17;
    public static final int TS_STREAM_TYPE_AC3 = 129;
    public static final int TS_STREAM_TYPE_DTS = 138;
    public static final int TS_STREAM_TYPE_DVBSUBS = 89;
    public static final int TS_STREAM_TYPE_E_AC3 = 135;
    public static final int TS_STREAM_TYPE_H262 = 2;
    public static final int TS_STREAM_TYPE_H264 = 27;
    public static final int TS_STREAM_TYPE_H265 = 36;
    public static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    public static final int TS_STREAM_TYPE_ID3 = 21;
    public static final int TS_STREAM_TYPE_MPA = 3;
    public static final int TS_STREAM_TYPE_MPA_LSF = 4;
    public static final int TS_STREAM_TYPE_SPLICE_INFO = 134;
    public static final int TS_SYNC_BYTE = 71;
    private int bytesSinceLastSync;
    private final SparseIntArray continuityCounters;
    private final TsDurationReader durationReader;
    private boolean hasOutputSeekMap;
    private TsPayloadReader id3Reader;
    private final int mode;
    private ExtractorOutput output;
    private final Factory payloadReaderFactory;
    private int pcrPid;
    private boolean pendingSeekToStart;
    private int remainingPmts;
    private final List timestampAdjusters;
    private final SparseBooleanArray trackIds;
    private final SparseBooleanArray trackPids;
    private boolean tracksEnded;
    private final ParsableByteArray tsPacketBuffer;
    private final SparseArray tsPayloadReaders;

    static {
        TsExtractor.FACTORY = -$$Lambda$TsExtractor$f-UE6PC86cqq4V-qVoFQnPhfFZ8.INSTANCE;
        TsExtractor.AC3_FORMAT_IDENTIFIER = ((long)Util.getIntegerCodeForString("AC-3"));
        TsExtractor.E_AC3_FORMAT_IDENTIFIER = ((long)Util.getIntegerCodeForString("EAC3"));
        TsExtractor.HEVC_FORMAT_IDENTIFIER = ((long)Util.getIntegerCodeForString("HEVC"));
    }

    public TsExtractor(int arg4, int arg5) {
        this(arg4, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(arg5));
    }

    public TsExtractor() {
        this(0);
    }

    public TsExtractor(int arg2) {
        this(1, arg2);
    }

    public TsExtractor(int arg1, TimestampAdjuster arg2, Factory arg3) {
        super();
        this.payloadReaderFactory = Assertions.checkNotNull(arg3);
        this.mode = arg1;
        if(arg1 == 1 || arg1 == 2) {
            this.timestampAdjusters = Collections.singletonList(arg2);
        }
        else {
            this.timestampAdjusters = new ArrayList();
            this.timestampAdjusters.add(arg2);
        }

        this.tsPacketBuffer = new ParsableByteArray(new byte[9400], 0);
        this.trackIds = new SparseBooleanArray();
        this.trackPids = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray();
        this.continuityCounters = new SparseIntArray();
        this.durationReader = new TsDurationReader();
        this.pcrPid = -1;
        this.resetPayloadReaders();
    }

    static SparseArray access$000(TsExtractor arg0) {
        return arg0.tsPayloadReaders;
    }

    static int access$100(TsExtractor arg0) {
        return arg0.remainingPmts;
    }

    static boolean access$1000(TsExtractor arg0) {
        return arg0.tracksEnded;
    }

    static boolean access$1002(TsExtractor arg0, boolean arg1) {
        arg0.tracksEnded = arg1;
        return arg1;
    }

    static int access$102(TsExtractor arg0, int arg1) {
        arg0.remainingPmts = arg1;
        return arg1;
    }

    static int access$108(TsExtractor arg2) {
        int v0 = arg2.remainingPmts;
        arg2.remainingPmts = v0 + 1;
        return v0;
    }

    static long access$1100() {
        return TsExtractor.AC3_FORMAT_IDENTIFIER;
    }

    static long access$1200() {
        return TsExtractor.E_AC3_FORMAT_IDENTIFIER;
    }

    static long access$1300() {
        return TsExtractor.HEVC_FORMAT_IDENTIFIER;
    }

    static int access$200(TsExtractor arg0) {
        return arg0.mode;
    }

    static List access$300(TsExtractor arg0) {
        return arg0.timestampAdjusters;
    }

    static int access$402(TsExtractor arg0, int arg1) {
        arg0.pcrPid = arg1;
        return arg1;
    }

    static TsPayloadReader access$500(TsExtractor arg0) {
        return arg0.id3Reader;
    }

    static TsPayloadReader access$502(TsExtractor arg0, TsPayloadReader arg1) {
        arg0.id3Reader = arg1;
        return arg1;
    }

    static Factory access$600(TsExtractor arg0) {
        return arg0.payloadReaderFactory;
    }

    static ExtractorOutput access$700(TsExtractor arg0) {
        return arg0.output;
    }

    static SparseBooleanArray access$800(TsExtractor arg0) {
        return arg0.trackIds;
    }

    static SparseBooleanArray access$900(TsExtractor arg0) {
        return arg0.trackPids;
    }

    private boolean fillBufferWithAtLeastOnePacket(ExtractorInput arg7) {
        int v1;
        byte[] v0 = this.tsPacketBuffer.data;
        int v3 = 188;
        if(9400 - this.tsPacketBuffer.getPosition() < v3) {
            v1 = this.tsPacketBuffer.bytesLeft();
            if(v1 > 0) {
                System.arraycopy(v0, this.tsPacketBuffer.getPosition(), v0, 0, v1);
            }

            this.tsPacketBuffer.reset(v0, v1);
        }

        while(this.tsPacketBuffer.bytesLeft() < v3) {
            v1 = this.tsPacketBuffer.limit();
            int v4 = arg7.read(v0, v1, 9400 - v1);
            if(v4 == -1) {
                return 0;
            }

            this.tsPacketBuffer.setLimit(v1 + v4);
        }

        return 1;
    }

    private int findEndOfFirstTsPacketInBuffer() {
        int v0 = this.tsPacketBuffer.getPosition();
        int v1 = this.tsPacketBuffer.limit();
        int v2 = TsExtractor.findSyncBytePosition(this.tsPacketBuffer.data, v0, v1);
        this.tsPacketBuffer.setPosition(v2);
        int v3 = v2 + 188;
        if(v3 > v1) {
            this.bytesSinceLastSync += v2 - v0;
            if(this.mode == 2) {
                if(this.bytesSinceLastSync <= 376) {
                }
                else {
                    throw new ParserException("Cannot find sync byte. Most likely not a Transport Stream.");
                }
            }
        }
        else {
            this.bytesSinceLastSync = 0;
        }

        return v3;
    }

    private static int findSyncBytePosition(byte[] arg2, int arg3, int arg4) {
        while(arg3 < arg4) {
            if(arg2[arg3] == 71) {
                return arg3;
            }

            ++arg3;
        }

        return arg3;
    }

    public void init(ExtractorOutput arg1) {
        this.output = arg1;
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new TsExtractor()};
    }

    private void maybeOutputSeekMap() {
        if(!this.hasOutputSeekMap) {
            this.hasOutputSeekMap = true;
            this.output.seekMap(new Unseekable(this.durationReader.getDurationUs()));
        }
    }

    public int read(ExtractorInput arg11, PositionHolder arg12) {
        int v0;
        int v1 = 2;
        if(this.tracksEnded) {
            v0 = arg11.getLength() == -1 || this.mode == v1 ? 0 : 1;
            if(v0 != 0 && !this.durationReader.isDurationReadFinished()) {
                return this.durationReader.readDuration(arg11, arg12, this.pcrPid);
            }

            this.maybeOutputSeekMap();
            if(!this.pendingSeekToStart) {
                goto label_31;
            }

            this.pendingSeekToStart = false;
            long v4 = 0;
            this.seek(v4, v4);
            if(arg11.getPosition() == v4) {
                goto label_31;
            }

            arg12.position = v4;
            return 1;
        }

    label_31:
        if(!this.fillBufferWithAtLeastOnePacket(arg11)) {
            return -1;
        }

        int v11 = this.findEndOfFirstTsPacketInBuffer();
        int v12 = this.tsPacketBuffer.limit();
        if(v11 > v12) {
            return 0;
        }

        v0 = this.tsPacketBuffer.readInt();
        if((8388608 & v0) == 0) {
            boolean v4_1 = (4194304 & v0) != 0 ? true : false;
            int v5 = (2096896 & v0) >> 8;
            int v6 = (v0 & 32) != 0 ? 1 : 0;
            int v7 = (v0 & 16) != 0 ? 1 : 0;
            Object v7_1 = v7 != 0 ? this.tsPayloadReaders.get(v5) : null;
            if(v7_1 == null) {
                goto label_45;
            }

            if(this.mode != v1) {
                v0 &= 15;
                int v8 = this.continuityCounters.get(v5, v0 - 1);
                this.continuityCounters.put(v5, v0);
                if(v8 == v0) {
                    goto label_45;
                }
                else if(v0 != (v8 + 1 & 15)) {
                    ((TsPayloadReader)v7_1).seek();
                }
            }

            if(v6 != 0) {
                this.tsPacketBuffer.skipBytes(this.tsPacketBuffer.readUnsignedByte());
            }

            boolean v0_1 = this.tracksEnded;
            if(this.shouldConsumePacketPayload(v5)) {
                this.tsPacketBuffer.setLimit(v11);
                ((TsPayloadReader)v7_1).consume(this.tsPacketBuffer, v4_1);
                this.tsPacketBuffer.setLimit(v12);
            }

            if(this.mode == v1) {
                goto label_45;
            }

            if(v0_1) {
                goto label_45;
            }

            if(!this.tracksEnded) {
                goto label_45;
            }

            this.pendingSeekToStart = true;
        }

    label_45:
        this.tsPacketBuffer.setPosition(v11);
        return 0;
    }

    public void release() {
    }

    private void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray v0 = this.payloadReaderFactory.createInitialPayloadReaders();
        int v1 = v0.size();
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            this.tsPayloadReaders.put(v0.keyAt(v3), v0.valueAt(v3));
        }

        this.tsPayloadReaders.put(0, new SectionReader(new PatReader(this)));
        this.id3Reader = null;
    }

    public void seek(long arg1, long arg3) {
        boolean v1 = this.mode != 2 ? true : false;
        Assertions.checkState(v1);
        int v1_1 = this.timestampAdjusters.size();
        int v3;
        for(v3 = 0; v3 < v1_1; ++v3) {
            this.timestampAdjusters.get(v3).reset();
        }

        this.tsPacketBuffer.reset();
        this.continuityCounters.clear();
        for(v1_1 = 0; v1_1 < this.tsPayloadReaders.size(); ++v1_1) {
            this.tsPayloadReaders.valueAt(v1_1).seek();
        }

        this.bytesSinceLastSync = 0;
    }

    private boolean shouldConsumePacketPayload(int arg4) {
        boolean v1 = false;
        if(this.mode == 2 || (this.tracksEnded) || !this.trackPids.get(arg4, false)) {
            v1 = true;
        }

        return v1;
    }

    public boolean sniff(ExtractorInput arg8) {
        byte[] v0 = this.tsPacketBuffer.data;
        arg8.peekFully(v0, 0, 940);
        int v2;
        for(v2 = 0; v2 < 188; ++v2) {
            int v3 = 0;
            while(true) {
                if(v3 >= 5) {
                    break;
                }
                else if(v0[v3 * 188 + v2] != 71) {
                    v3 = 0;
                }
                else {
                    ++v3;
                    continue;
                }

                goto label_22;
            }

            v3 = 1;
        label_22:
            if(v3 != 0) {
                arg8.skipFully(v2);
                return 1;
            }
        }

        return 0;
    }
}

