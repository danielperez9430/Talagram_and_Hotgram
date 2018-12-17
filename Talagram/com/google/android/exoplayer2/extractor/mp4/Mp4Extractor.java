package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class Mp4Extractor implements Extractor, SeekMap {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;

        public Mp4Track(Track arg1, TrackSampleTable arg2, TrackOutput arg3) {
            super();
            this.track = arg1;
            this.sampleTable = arg2;
            this.trackOutput = arg3;
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface State {
    }

    private static final int BRAND_QUICKTIME = 0;
    public static final ExtractorsFactory FACTORY = null;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 1;
    private static final long MAXIMUM_READ_AHEAD_BYTES_STREAM = 10485760;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final ArrayDeque containerAtoms;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int firstVideoTrackIndex;
    private final int flags;
    private boolean isQuickTime;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleTrackIndex;
    private Mp4Track[] tracks;

    static {
        Mp4Extractor.FACTORY = -$$Lambda$Mp4Extractor$quy71uYOGsneho91FZy1d2UGE1Q.INSTANCE;
        Mp4Extractor.BRAND_QUICKTIME = Util.getIntegerCodeForString("qt  ");
    }

    public Mp4Extractor(int arg2) {
        super();
        this.flags = arg2;
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleTrackIndex = -1;
    }

    public Mp4Extractor() {
        this(0);
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] arg15) {
        long[][] v0 = new long[arg15.length][];
        int[] v1 = new int[arg15.length];
        long[] v2 = new long[arg15.length];
        boolean[] v3 = new boolean[arg15.length];
        int v5;
        for(v5 = 0; v5 < arg15.length; ++v5) {
            v0[v5] = new long[arg15[v5].sampleTable.sampleCount];
            v2[v5] = arg15[v5].sampleTable.timestampsUs[0];
        }

        long v6 = 0;
        for(v5 = 0; v5 < arg15.length; ++v5) {
            int v10 = -1;
            long v11 = 9223372036854775807L;
            int v8;
            for(v8 = 0; v8 < arg15.length; ++v8) {
                if(!v3[v8] && v2[v8] <= v11) {
                    v11 = v2[v8];
                    v10 = v8;
                }
            }

            v8 = v1[v10];
            v0[v10][v8] = v6;
            v6 += ((long)arg15[v10].sampleTable.sizes[v8]);
            ++v8;
            v1[v10] = v8;
            if(v8 < v0[v10].length) {
                v2[v10] = arg15[v10].sampleTable.timestampsUs[v8];
                continue;
            }

            v3[v10] = true;
        }

        return v0;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekPoints getSeekPoints(long arg13) {
        long v2_1;
        long v10;
        long v8;
        int v6;
        if(this.tracks.length == 0) {
            return new SeekPoints(SeekPoint.START);
        }

        long v0 = -1;
        int v3 = -1;
        long v4 = -9223372036854775807L;
        if(this.firstVideoTrackIndex != v3) {
            TrackSampleTable v2 = this.tracks[this.firstVideoTrackIndex].sampleTable;
            v6 = Mp4Extractor.getSynchronizationSampleIndex(v2, arg13);
            if(v6 == v3) {
                return new SeekPoints(SeekPoint.START);
            }
            else {
                v8 = v2.timestampsUs[v6];
                v10 = v2.offsets[v6];
                if(v8 >= arg13 || v6 >= v2.sampleCount - 1) {
                label_38:
                    arg13 = v0;
                    v0 = v4;
                }
                else {
                    int v13 = v2.getIndexOfLaterOrEqualSynchronizationSample(arg13);
                    if(v13 == v3) {
                        goto label_38;
                    }
                    else if(v13 != v6) {
                        v0 = v2.timestampsUs[v13];
                        arg13 = v2.offsets[v13];
                    }
                    else {
                        goto label_38;
                    }
                }

                v2_1 = arg13;
                arg13 = v8;
            }
        }
        else {
            v10 = 9223372036854775807L;
            v2_1 = v0;
            v0 = v4;
        }

        for(v6 = 0; v6 < this.tracks.length; ++v6) {
            if(v6 != this.firstVideoTrackIndex) {
                TrackSampleTable v7 = this.tracks[v6].sampleTable;
                v8 = Mp4Extractor.maybeAdjustSeekOffset(v7, arg13, v10);
                if(v0 != v4) {
                    v2_1 = Mp4Extractor.maybeAdjustSeekOffset(v7, v0, v2_1);
                }

                v10 = v8;
            }
        }

        SeekPoint v6_1 = new SeekPoint(arg13, v10);
        if(v0 == v4) {
            return new SeekPoints(v6_1);
        }

        return new SeekPoints(v6_1, new SeekPoint(v0, v2_1));
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable arg2, long arg3) {
        int v0 = arg2.getIndexOfEarlierOrEqualSynchronizationSample(arg3);
        if(v0 == -1) {
            v0 = arg2.getIndexOfLaterOrEqualSynchronizationSample(arg3);
        }

        return v0;
    }

    private int getTrackIndexOfNextReadSample(long arg21) {
        Mp4Extractor v0 = this;
        int v2 = 0;
        long v6 = 9223372036854775807L;
        int v8 = 1;
        long v9 = 9223372036854775807L;
        int v11 = -1;
        int v12 = -1;
        int v13 = 1;
        long v14 = 9223372036854775807L;
        while(v2 < v0.tracks.length) {
            Mp4Track v1 = v0.tracks[v2];
            int v3 = v1.sampleIndex;
            if(v3 == v1.sampleTable.sampleCount) {
            }
            else {
                long v4 = v1.sampleTable.offsets[v3];
                long v16 = v0.accumulatedSampleSizes[v2][v3];
                v4 -= arg21;
                int v1_1 = v4 < 0 || v4 >= 262144 ? 1 : 0;
                if(v1_1 == 0 && v13 != 0 || v1_1 == v13 && v4 < v14) {
                    v13 = v1_1;
                    v11 = v2;
                    v14 = v4;
                    v9 = v16;
                }

                if(v16 >= v6) {
                    goto label_47;
                }

                v8 = v1_1;
                v12 = v2;
                v6 = v16;
            }

        label_47:
            ++v2;
        }

        if(v6 != 9223372036854775807L && v8 != 0) {
            if(v9 < v6 + 10485760) {
            }
            else {
                v11 = v12;
            }
        }

        return v11;
    }

    private ArrayList getTrackSampleTables(ContainerAtom arg11, GaplessInfoHolder arg12, boolean arg13) {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < arg11.containerChildren.size(); ++v1) {
            Object v2 = arg11.containerChildren.get(v1);
            if(((ContainerAtom)v2).type != Atom.TYPE_trak) {
            }
            else {
                Track v3 = AtomParsers.parseTrak(v2, arg11.getLeafAtomOfType(Atom.TYPE_mvhd), -9223372036854775807L, null, arg13, this.isQuickTime);
                if(v3 == null) {
                }
                else {
                    TrackSampleTable v2_1 = AtomParsers.parseStbl(v3, ((ContainerAtom)v2).getContainerAtomOfType(Atom.TYPE_mdia).getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl), arg12);
                    if(v2_1.sampleCount == 0) {
                    }
                    else {
                        v0.add(v2_1);
                    }
                }
            }
        }

        return v0;
    }

    public void init(ExtractorOutput arg1) {
        this.extractorOutput = arg1;
    }

    public boolean isSeekable() {
        return 1;
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp4Extractor()};
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable arg0, long arg1, long arg3) {
        int v1 = Mp4Extractor.getSynchronizationSampleIndex(arg0, arg1);
        if(v1 == -1) {
            return arg3;
        }

        return Math.min(arg0.offsets[v1], arg3);
    }

    private void processAtomEnded(long arg5) {
        while(true) {
            int v1 = 2;
            if(!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == arg5) {
                Object v0 = this.containerAtoms.pop();
                if(((ContainerAtom)v0).type == Atom.TYPE_moov) {
                    this.processMoovAtom(((ContainerAtom)v0));
                    this.containerAtoms.clear();
                    this.parserState = v1;
                }
                else {
                    if(this.containerAtoms.isEmpty()) {
                        continue;
                    }

                    this.containerAtoms.peek().add(((ContainerAtom)v0));
                }

                continue;
            }

            break;
        }

        if(this.parserState != v1) {
            this.enterReadingAtomHeaderState();
        }
    }

    private static boolean processFtypAtom(ParsableByteArray arg3) {
        arg3.setPosition(8);
        if(arg3.readInt() == Mp4Extractor.BRAND_QUICKTIME) {
            return 1;
        }

        arg3.skipBytes(4);
        do {
            if(arg3.bytesLeft() <= 0) {
                return 0;
            }
        }
        while(arg3.readInt() != Mp4Extractor.BRAND_QUICKTIME);

        return 1;
    }

    private void processMoovAtom(ContainerAtom arg19) {
        ArrayList v5_1;
        Metadata v4_1;
        Mp4Extractor v0 = this;
        ContainerAtom v1 = arg19;
        ArrayList v2 = new ArrayList();
        GaplessInfoHolder v3 = new GaplessInfoHolder();
        LeafAtom v4 = v1.getLeafAtomOfType(Atom.TYPE_udta);
        if(v4 != null) {
            v4_1 = AtomParsers.parseUdta(v4, v0.isQuickTime);
            if(v4_1 != null) {
                v3.setFromMetadata(v4_1);
            }
        }
        else {
            v4_1 = null;
        }

        int v6 = 1;
        int v7 = 0;
        boolean v5 = (v0.flags & 1) != 0 ? true : false;
        try {
            v5_1 = v0.getTrackSampleTables(v1, v3, v5);
        }
        catch(UnhandledEditListException ) {
            v3 = new GaplessInfoHolder();
            v5_1 = v0.getTrackSampleTables(v1, v3, true);
        }

        int v1_1 = v5_1.size();
        int v11 = -1;
        long v12 = -9223372036854775807L;
        while(v7 < v1_1) {
            Object v14 = v5_1.get(v7);
            Track v15 = ((TrackSampleTable)v14).track;
            Mp4Track v10 = new Mp4Track(v15, ((TrackSampleTable)v14), v0.extractorOutput.track(v7, v15.type));
            Format v8 = v15.format.copyWithMaxInputSize(((TrackSampleTable)v14).maximumSize + 30);
            if(v15.type == v6) {
                if(v3.hasGaplessInfo()) {
                    v8 = v8.copyWithGaplessInfo(v3.encoderDelay, v3.encoderPadding);
                }

                if(v4_1 == null) {
                    goto label_52;
                }

                v8 = v8.copyWithMetadata(v4_1);
            }

        label_52:
            v10.trackOutput.format(v8);
            long v8_1 = v15.durationUs != -9223372036854775807L ? v15.durationUs : ((TrackSampleTable)v14).durationUs;
            v12 = Math.max(v12, v8_1);
            if(v15.type == 2 && v11 == -1) {
                v11 = ((List)v2).size();
            }

            ((List)v2).add(v10);
            ++v7;
            v6 = 1;
        }

        v0.firstVideoTrackIndex = v11;
        v0.durationUs = v12;
        v0.tracks = ((List)v2).toArray(new Mp4Track[((List)v2).size()]);
        v0.accumulatedSampleSizes = Mp4Extractor.calculateAccumulatedSampleSizes(v0.tracks);
        v0.extractorOutput.endTracks();
        v0.extractorOutput.seekMap(((SeekMap)v0));
    }

    public int read(ExtractorInput arg2, PositionHolder arg3) {
        do {
        label_0:
            switch(this.parserState) {
                case 0: {
                    goto label_11;
                }
                case 1: {
                    goto label_7;
                }
                case 2: {
                    goto label_5;
                }
            }

            throw new IllegalStateException();
        label_5:
            return this.readSample(arg2, arg3);
        label_7:
            if(!this.readAtomPayload(arg2, arg3)) {
                goto label_0;
            }

            return 1;
        label_11:
        }
        while(this.readAtomHeader(arg2));

        return -1;
    }

    private boolean readAtomHeader(ExtractorInput arg9) {
        long v4;
        int v2 = 8;
        if(this.atomHeaderBytesRead == 0) {
            if(!arg9.readFully(this.atomHeader.data, 0, v2, true)) {
                return 0;
            }
            else {
                this.atomHeaderBytesRead = v2;
                this.atomHeader.setPosition(0);
                this.atomSize = this.atomHeader.readUnsignedInt();
                this.atomType = this.atomHeader.readInt();
            }
        }

        if(this.atomSize == 1) {
            arg9.readFully(this.atomHeader.data, v2, v2);
            this.atomHeaderBytesRead += v2;
            v4 = this.atomHeader.readUnsignedLongToLong();
            goto label_30;
        }
        else if(this.atomSize == 0) {
            v4 = arg9.getLength();
            long v6 = -1;
            if(v4 == v6 && !this.containerAtoms.isEmpty()) {
                v4 = this.containerAtoms.peek().endPosition;
            }

            if(v4 == v6) {
                goto label_51;
            }

            v4 = v4 - arg9.getPosition() + (((long)this.atomHeaderBytesRead));
        label_30:
            this.atomSize = v4;
        }

    label_51:
        if(this.atomSize >= (((long)this.atomHeaderBytesRead))) {
            if(Mp4Extractor.shouldParseContainerAtom(this.atomType)) {
                long v2_1 = arg9.getPosition() + this.atomSize - (((long)this.atomHeaderBytesRead));
                this.containerAtoms.push(new ContainerAtom(this.atomType, v2_1));
                if(this.atomSize == (((long)this.atomHeaderBytesRead))) {
                    this.processAtomEnded(v2_1);
                }
                else {
                    this.enterReadingAtomHeaderState();
                }
            }
            else {
                if(Mp4Extractor.shouldParseLeafAtom(this.atomType)) {
                    boolean v9 = this.atomHeaderBytesRead == v2 ? true : false;
                    Assertions.checkState(v9);
                    v9 = this.atomSize <= 2147483647 ? true : false;
                    Assertions.checkState(v9);
                    this.atomData = new ParsableByteArray(((int)this.atomSize));
                    System.arraycopy(this.atomHeader.data, 0, this.atomData.data, 0, v2);
                }
                else {
                    this.atomData = null;
                }

                this.parserState = 1;
            }

            return 1;
        }

        throw new ParserException("Atom size less than header length (unsupported).");
    }

    private boolean readAtomPayload(ExtractorInput arg10, PositionHolder arg11) {
        int v10;
        long v0 = this.atomSize - (((long)this.atomHeaderBytesRead));
        long v2 = arg10.getPosition() + v0;
        boolean v5 = true;
        if(this.atomData != null) {
            arg10.readFully(this.atomData.data, this.atomHeaderBytesRead, ((int)v0));
            if(this.atomType == Atom.TYPE_ftyp) {
                this.isQuickTime = Mp4Extractor.processFtypAtom(this.atomData);
            }
            else if(!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(new LeafAtom(this.atomType, this.atomData));
            }
            else {
            }

            goto label_37;
        }
        else if(v0 < 262144) {
            arg10.skipFully(((int)v0));
        label_37:
            v10 = 0;
        }
        else {
            arg11.position = arg10.getPosition() + v0;
            v10 = 1;
        }

        this.processAtomEnded(v2);
        if(v10 == 0 || this.parserState == 2) {
            v5 = false;
        }
        else {
        }

        return v5;
    }

    private int readSample(ExtractorInput arg14, PositionHolder arg15) {
        // Method was not decompiled
    }

    public void release() {
    }

    public void seek(long arg4, long arg6) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        if(arg4 == 0) {
            this.enterReadingAtomHeaderState();
        }
        else if(this.tracks != null) {
            this.updateSampleIndices(arg6);
        }
    }

    private static boolean shouldParseContainerAtom(int arg1) {
        boolean v1 = arg1 == Atom.TYPE_moov || arg1 == Atom.TYPE_trak || arg1 == Atom.TYPE_mdia || arg1 == Atom.TYPE_minf || arg1 == Atom.TYPE_stbl || arg1 == Atom.TYPE_edts ? true : false;
        return v1;
    }

    private static boolean shouldParseLeafAtom(int arg1) {
        boolean v1 = arg1 == Atom.TYPE_mdhd || arg1 == Atom.TYPE_mvhd || arg1 == Atom.TYPE_hdlr || arg1 == Atom.TYPE_stsd || arg1 == Atom.TYPE_stts || arg1 == Atom.TYPE_stss || arg1 == Atom.TYPE_ctts || arg1 == Atom.TYPE_elst || arg1 == Atom.TYPE_stsc || arg1 == Atom.TYPE_stsz || arg1 == Atom.TYPE_stz2 || arg1 == Atom.TYPE_stco || arg1 == Atom.TYPE_co64 || arg1 == Atom.TYPE_tkhd || arg1 == Atom.TYPE_ftyp || arg1 == Atom.TYPE_udta ? true : false;
        return v1;
    }

    public boolean sniff(ExtractorInput arg1) {
        return Sniffer.sniffUnfragmented(arg1);
    }

    private void updateSampleIndices(long arg8) {
        Mp4Track[] v0 = this.tracks;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Mp4Track v3 = v0[v2];
            TrackSampleTable v4 = v3.sampleTable;
            int v5 = v4.getIndexOfEarlierOrEqualSynchronizationSample(arg8);
            if(v5 == -1) {
                v5 = v4.getIndexOfLaterOrEqualSynchronizationSample(arg8);
            }

            v3.sampleIndex = v5;
        }
    }
}

