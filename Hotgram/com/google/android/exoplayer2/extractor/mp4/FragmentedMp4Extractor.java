package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData$SchemeData;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.text.cea.CeaUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class FragmentedMp4Extractor implements Extractor {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    final class MetadataSampleInfo {
        public final long presentationTimeDeltaUs;
        public final int size;

        public MetadataSampleInfo(long arg1, int arg3) {
            super();
            this.presentationTimeDeltaUs = arg1;
            this.size = arg3;
        }
    }

    final class TrackBundle {
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        private final ParsableByteArray defaultInitializationVector;
        public DefaultSampleValues defaultSampleValues;
        private final ParsableByteArray encryptionSignalByte;
        public int firstSampleToOutputIndex;
        public final TrackFragment fragment;
        public final TrackOutput output;
        public Track track;

        public TrackBundle(TrackOutput arg2) {
            super();
            this.output = arg2;
            this.fragment = new TrackFragment();
            this.encryptionSignalByte = new ParsableByteArray(1);
            this.defaultInitializationVector = new ParsableByteArray();
        }

        static void access$000(TrackBundle arg0) {
            arg0.skipSampleEncryptionData();
        }

        private TrackEncryptionBox getEncryptionBox() {
            int v0 = this.fragment.header.sampleDescriptionIndex;
            TrackEncryptionBox v0_1 = this.fragment.trackEncryptionBox != null ? this.fragment.trackEncryptionBox : this.track.getSampleDescriptionEncryptionBox(v0);
            return v0_1;
        }

        public void init(Track arg2, DefaultSampleValues arg3) {
            this.track = Assertions.checkNotNull(arg2);
            this.defaultSampleValues = Assertions.checkNotNull(arg3);
            this.output.format(arg2.format);
            this.reset();
        }

        public boolean next() {
            ++this.currentSampleIndex;
            ++this.currentSampleInTrackRun;
            if(this.currentSampleInTrackRun == this.fragment.trunLength[this.currentTrackRunIndex]) {
                ++this.currentTrackRunIndex;
                this.currentSampleInTrackRun = 0;
                return 0;
            }

            return 1;
        }

        public int outputSampleEncryptionData() {
            int v0_1;
            ParsableByteArray v2;
            if(!this.fragment.definesEncryptionData) {
                return 0;
            }

            TrackEncryptionBox v0 = this.getEncryptionBox();
            if(v0.initializationVectorSize != 0) {
                v2 = this.fragment.sampleEncryptionData;
                v0_1 = v0.initializationVectorSize;
            }
            else {
                byte[] v0_2 = v0.defaultInitializationVector;
                this.defaultInitializationVector.reset(v0_2, v0_2.length);
                v2 = this.defaultInitializationVector;
                v0_1 = v0_2.length;
            }

            boolean v3 = this.fragment.sampleHasSubsampleEncryptionTable[this.currentSampleIndex];
            byte[] v4 = this.encryptionSignalByte.data;
            int v5 = v3 ? 128 : 0;
            v4[0] = ((byte)(v5 | v0_1));
            this.encryptionSignalByte.setPosition(0);
            this.output.sampleData(this.encryptionSignalByte, 1);
            this.output.sampleData(v2, v0_1);
            if(!v3) {
                return v0_1 + 1;
            }

            ParsableByteArray v1 = this.fragment.sampleEncryptionData;
            int v2_1 = v1.readUnsignedShort();
            v1.skipBytes(-2);
            v2_1 = v2_1 * 6 + 2;
            this.output.sampleData(v1, v2_1);
            return v0_1 + 1 + v2_1;
        }

        public void reset() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
            this.firstSampleToOutputIndex = 0;
        }

        public void seek(long arg5) {
            arg5 = C.usToMs(arg5);
            int v0;
            for(v0 = this.currentSampleIndex; v0 < this.fragment.sampleCount; ++v0) {
                if(this.fragment.getSamplePresentationTime(v0) >= arg5) {
                    return;
                }

                if(this.fragment.sampleIsSyncFrameTable[v0]) {
                    this.firstSampleToOutputIndex = v0;
                }
            }
        }

        private void skipSampleEncryptionData() {
            if(!this.fragment.definesEncryptionData) {
                return;
            }

            ParsableByteArray v0 = this.fragment.sampleEncryptionData;
            TrackEncryptionBox v1 = this.getEncryptionBox();
            if(v1.initializationVectorSize != 0) {
                v0.skipBytes(v1.initializationVectorSize);
            }

            if(this.fragment.sampleHasSubsampleEncryptionTable[this.currentSampleIndex]) {
                v0.skipBytes(v0.readUnsignedShort() * 6);
            }
        }

        public void updateDrmInitData(DrmInitData arg4) {
            TrackEncryptionBox v0 = this.track.getSampleDescriptionEncryptionBox(this.fragment.header.sampleDescriptionIndex);
            String v0_1 = v0 != null ? v0.schemeType : null;
            this.output.format(this.track.format.copyWithDrmInitData(arg4.copyWithSchemeType(v0_1)));
        }
    }

    private static final Format EMSG_FORMAT = null;
    public static final ExtractorsFactory FACTORY = null;
    public static final int FLAG_ENABLE_EMSG_TRACK = 4;
    private static final int FLAG_SIDELOADED = 8;
    public static final int FLAG_WORKAROUND_EVERY_VIDEO_FRAME_IS_SYNC_FRAME = 1;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 16;
    public static final int FLAG_WORKAROUND_IGNORE_TFDT_BOX = 2;
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = null;
    private static final int SAMPLE_GROUP_TYPE_seig = 0;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_ENCRYPTION_DATA = 2;
    private static final int STATE_READING_SAMPLE_CONTINUE = 4;
    private static final int STATE_READING_SAMPLE_START = 3;
    private static final String TAG = "FragmentedMp4Extractor";
    private final TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] cea608TrackOutputs;
    private final List closedCaptionFormats;
    private final ArrayDeque containerAtoms;
    private TrackBundle currentTrackBundle;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private long endOfMdatPosition;
    private final byte[] extendedTypeScratch;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private final ParsableByteArray nalBuffer;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque pendingMetadataSampleInfos;
    private long pendingSeekTimeUs;
    private boolean processSeiNalUnitPayload;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private long segmentIndexEarliestPresentationTimeUs;
    private final DrmInitData sideloadedDrmInitData;
    private final Track sideloadedTrack;
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray trackBundles;

    static {
        FragmentedMp4Extractor.FACTORY = -$$Lambda$FragmentedMp4Extractor$i0zfpH_PcF0vytkdatCL0xeWFhQ.INSTANCE;
        FragmentedMp4Extractor.SAMPLE_GROUP_TYPE_seig = Util.getIntegerCodeForString("seig");
        FragmentedMp4Extractor.PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = new byte[]{-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12};
        FragmentedMp4Extractor.EMSG_FORMAT = Format.createSampleFormat(null, "application/x-emsg", 9223372036854775807L);
    }

    public FragmentedMp4Extractor() {
        this(0);
    }

    public FragmentedMp4Extractor(int arg2) {
        this(arg2, null);
    }

    public FragmentedMp4Extractor(int arg2, TimestampAdjuster arg3) {
        this(arg2, arg3, null, null);
    }

    public FragmentedMp4Extractor(int arg7, TimestampAdjuster arg8, Track arg9, DrmInitData arg10) {
        this(arg7, arg8, arg9, arg10, Collections.emptyList());
    }

    public FragmentedMp4Extractor(int arg8, TimestampAdjuster arg9, Track arg10, DrmInitData arg11, List arg12) {
        this(arg8, arg9, arg10, arg11, arg12, null);
    }

    public FragmentedMp4Extractor(int arg2, TimestampAdjuster arg3, Track arg4, DrmInitData arg5, List arg6, TrackOutput arg7) {
        super();
        int v0 = arg4 != null ? 8 : 0;
        this.flags = arg2 | v0;
        this.timestampAdjuster = arg3;
        this.sideloadedTrack = arg4;
        this.sideloadedDrmInitData = arg5;
        this.closedCaptionFormats = Collections.unmodifiableList(arg6);
        this.additionalEmsgTrackOutput = arg7;
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(5);
        this.nalBuffer = new ParsableByteArray();
        this.extendedTypeScratch = new byte[16];
        this.containerAtoms = new ArrayDeque();
        this.pendingMetadataSampleInfos = new ArrayDeque();
        this.trackBundles = new SparseArray();
        this.durationUs = -9223372036854775807L;
        this.pendingSeekTimeUs = -9223372036854775807L;
        this.segmentIndexEarliestPresentationTimeUs = -9223372036854775807L;
        this.enterReadingAtomHeaderState();
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private DefaultSampleValues getDefaultSampleValues(SparseArray arg3, int arg4) {
        Object v3 = arg3.size() == 1 ? arg3.valueAt(0) : Assertions.checkNotNull(arg3.get(arg4));
        return ((DefaultSampleValues)v3);
    }

    private static DrmInitData getDrmInitDataFromAtoms(List arg8) {
        int v0 = arg8.size();
        DrmInitData v1 = null;
        int v2 = 0;
        ArrayList v3 = ((ArrayList)v1);
        while(v2 < v0) {
            Object v4 = arg8.get(v2);
            if(((LeafAtom)v4).type == Atom.TYPE_pssh) {
                if(v3 == null) {
                    v3 = new ArrayList();
                }

                byte[] v4_1 = ((LeafAtom)v4).data.data;
                UUID v5 = PsshAtomUtil.parseUuid(v4_1);
                if(v5 == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                    goto label_24;
                }

                v3.add(new SchemeData(v5, "video/mp4", v4_1));
            }

        label_24:
            ++v2;
        }

        if(v3 == null) {
        }
        else {
            v1 = new DrmInitData(((List)v3));
        }

        return v1;
    }

    private static TrackBundle getNextFragmentRun(SparseArray arg9) {
        int v0 = arg9.size();
        TrackBundle v1 = null;
        long v2 = 9223372036854775807L;
        int v4;
        for(v4 = 0; v4 < v0; ++v4) {
            Object v5 = arg9.valueAt(v4);
            if(((TrackBundle)v5).currentTrackRunIndex == ((TrackBundle)v5).fragment.trunCount) {
            }
            else {
                long v7 = ((TrackBundle)v5).fragment.trunDataPosition[((TrackBundle)v5).currentTrackRunIndex];
                if(v7 < v2) {
                    Object v1_1 = v5;
                    v2 = v7;
                }
            }
        }

        return v1;
    }

    private static TrackBundle getTrackBundle(SparseArray arg2, int arg3) {
        Object v2 = arg2.size() == 1 ? arg2.valueAt(0) : arg2.get(arg3);
        return ((TrackBundle)v2);
    }

    public void init(ExtractorOutput arg4) {
        this.extractorOutput = arg4;
        if(this.sideloadedTrack != null) {
            TrackBundle v0 = new TrackBundle(arg4.track(0, this.sideloadedTrack.type));
            v0.init(this.sideloadedTrack, new DefaultSampleValues(0, 0, 0, 0));
            this.trackBundles.put(0, v0);
            this.maybeInitExtraTracks();
            this.extractorOutput.endTracks();
        }
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new FragmentedMp4Extractor()};
    }

    private void maybeInitExtraTracks() {
        int v0;
        int v1 = 0;
        if(this.emsgTrackOutputs == null) {
            this.emsgTrackOutputs = new TrackOutput[2];
            if(this.additionalEmsgTrackOutput != null) {
                this.emsgTrackOutputs[0] = this.additionalEmsgTrackOutput;
                v0 = 1;
            }
            else {
                v0 = 0;
            }

            int v4 = 4;
            if((this.flags & v4) != 0) {
                this.emsgTrackOutputs[v0] = this.extractorOutput.track(this.trackBundles.size(), v4);
                ++v0;
            }

            this.emsgTrackOutputs = Arrays.copyOf(this.emsgTrackOutputs, v0);
            TrackOutput[] v0_1 = this.emsgTrackOutputs;
            int v3 = v0_1.length;
            for(v4 = 0; v4 < v3; ++v4) {
                v0_1[v4].format(FragmentedMp4Extractor.EMSG_FORMAT);
            }
        }

        if(this.cea608TrackOutputs == null) {
            this.cea608TrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
            while(v1 < this.cea608TrackOutputs.length) {
                TrackOutput v0_2 = this.extractorOutput.track(this.trackBundles.size() + 1 + v1, 3);
                v0_2.format(this.closedCaptionFormats.get(v1));
                this.cea608TrackOutputs[v1] = v0_2;
                ++v1;
            }
        }
    }

    private void onContainerAtomRead(ContainerAtom arg3) {
        if(arg3.type == Atom.TYPE_moov) {
            this.onMoovContainerAtomRead(arg3);
        }
        else if(arg3.type == Atom.TYPE_moof) {
            this.onMoofContainerAtomRead(arg3);
        }
        else if(!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(arg3);
        }
    }

    private void onEmsgLeafAtomRead(ParsableByteArray arg13) {
        int v11;
        TrackOutput[] v13;
        if(this.emsgTrackOutputs != null) {
            if(this.emsgTrackOutputs.length == 0) {
            }
            else {
                int v0 = 12;
                arg13.setPosition(v0);
                int v8 = arg13.bytesLeft();
                arg13.readNullTerminatedString();
                arg13.readNullTerminatedString();
                long v9 = Util.scaleLargeTimestamp(arg13.readUnsignedInt(), 1000000, arg13.readUnsignedInt());
                TrackOutput[] v1 = this.emsgTrackOutputs;
                int v2 = v1.length;
                int v4;
                for(v4 = 0; v4 < v2; ++v4) {
                    TrackOutput v5 = v1[v4];
                    arg13.setPosition(v0);
                    v5.sampleData(arg13, v8);
                }

                if(this.segmentIndexEarliestPresentationTimeUs != -9223372036854775807L) {
                    v13 = this.emsgTrackOutputs;
                    v0 = v13.length;
                    v11 = 0;
                    goto label_31;
                }
                else {
                    this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(v9, v8));
                    this.pendingMetadataSampleBytes += v8;
                    return;
                label_31:
                    while(v11 < v0) {
                        v13[v11].sampleMetadata(this.segmentIndexEarliestPresentationTimeUs + v9, 1, v8, 0, null);
                        ++v11;
                    }
                }
            }
        }
    }

    private void onLeafAtomRead(LeafAtom arg3, long arg4) {
        if(!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(arg3);
        }
        else if(arg3.type == Atom.TYPE_sidx) {
            Pair v3 = FragmentedMp4Extractor.parseSidx(arg3.data, arg4);
            this.segmentIndexEarliestPresentationTimeUs = v3.first.longValue();
            this.extractorOutput.seekMap(v3.second);
            this.haveOutputSeekMap = true;
        }
        else if(arg3.type == Atom.TYPE_emsg) {
            this.onEmsgLeafAtomRead(arg3.data);
        }
    }

    private void onMoofContainerAtomRead(ContainerAtom arg8) {
        FragmentedMp4Extractor.parseMoof(arg8, this.trackBundles, this.flags, this.extendedTypeScratch);
        DrmInitData v8 = this.sideloadedDrmInitData != null ? null : FragmentedMp4Extractor.getDrmInitDataFromAtoms(arg8.leafChildren);
        int v0 = 0;
        if(v8 != null) {
            int v1 = this.trackBundles.size();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                this.trackBundles.valueAt(v2).updateDrmInitData(v8);
            }
        }

        long v3 = -9223372036854775807L;
        if(this.pendingSeekTimeUs != v3) {
            int v8_1 = this.trackBundles.size();
            while(v0 < v8_1) {
                this.trackBundles.valueAt(v0).seek(this.pendingSeekTimeUs);
                ++v0;
            }

            this.pendingSeekTimeUs = v3;
        }
    }

    private void onMoovContainerAtomRead(ContainerAtom arg20) {
        Object v2_2;
        int v16_1;
        int v18;
        FragmentedMp4Extractor v0 = this;
        ContainerAtom v1 = arg20;
        int v3 = 0;
        boolean v4 = true;
        boolean v2 = v0.sideloadedTrack == null ? true : false;
        Assertions.checkState(v2, "Unexpected moov box.");
        DrmInitData v2_1 = v0.sideloadedDrmInitData != null ? v0.sideloadedDrmInitData : FragmentedMp4Extractor.getDrmInitDataFromAtoms(v1.leafChildren);
        ContainerAtom v5 = v1.getContainerAtomOfType(Atom.TYPE_mvex);
        SparseArray v12 = new SparseArray();
        int v8 = v5.leafChildren.size();
        long v13 = -9223372036854775807L;
        int v6;
        for(v6 = 0; v6 < v8; ++v6) {
            Object v7 = v5.leafChildren.get(v6);
            if(((LeafAtom)v7).type == Atom.TYPE_trex) {
                Pair v7_1 = FragmentedMp4Extractor.parseTrex(((LeafAtom)v7).data);
                v12.put(v7_1.first.intValue(), v7_1.second);
            }
            else if(((LeafAtom)v7).type == Atom.TYPE_mehd) {
                v13 = FragmentedMp4Extractor.parseMehd(((LeafAtom)v7).data);
            }
        }

        SparseArray v15 = new SparseArray();
        int v11 = v1.containerChildren.size();
        int v10 = 0;
        while(v10 < v11) {
            Object v5_1 = v1.containerChildren.get(v10);
            if(((ContainerAtom)v5_1).type == Atom.TYPE_trak) {
                LeafAtom v6_1 = v1.getLeafAtomOfType(Atom.TYPE_mvhd);
                boolean v16 = (v0.flags & 16) != 0 ? true : false;
                v18 = v10;
                boolean v10_1 = v16;
                v16_1 = v11;
                Track v5_2 = AtomParsers.parseTrak(((ContainerAtom)v5_1), v6_1, v13, v2_1, v10_1, false);
                if(v5_2 == null) {
                    goto label_79;
                }

                v15.put(v5_2.id, v5_2);
            }
            else {
                v18 = v10;
                v16_1 = v11;
            }

        label_79:
            v10 = v18 + 1;
            v11 = v16_1;
        }

        int v1_1 = v15.size();
        if(v0.trackBundles.size() != 0) {
            if(v0.trackBundles.size() == v1_1) {
            }
            else {
                v4 = false;
            }

            Assertions.checkState(v4);
            while(v3 < v1_1) {
                v2_2 = v15.valueAt(v3);
                v0.trackBundles.get(((Track)v2_2).id).init(((Track)v2_2), v0.getDefaultSampleValues(v12, ((Track)v2_2).id));
                ++v3;
            }
        }
        else {
            while(v3 < v1_1) {
                v2_2 = v15.valueAt(v3);
                TrackBundle v4_1 = new TrackBundle(v0.extractorOutput.track(v3, ((Track)v2_2).type));
                v4_1.init(((Track)v2_2), v0.getDefaultSampleValues(v12, ((Track)v2_2).id));
                v0.trackBundles.put(((Track)v2_2).id, v4_1);
                v0.durationUs = Math.max(v0.durationUs, ((Track)v2_2).durationUs);
                ++v3;
            }

            this.maybeInitExtraTracks();
            v0.extractorOutput.endTracks();
        }
    }

    private void outputPendingMetadataSamples(long arg12) {
    label_0:
        if(!this.pendingMetadataSampleInfos.isEmpty()) {
            Object v0 = this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= ((MetadataSampleInfo)v0).size;
            TrackOutput[] v1 = this.emsgTrackOutputs;
            int v2 = v1.length;
            int v3;
            for(v3 = 0; true; ++v3) {
                if(v3 >= v2) {
                    goto label_0;
                }

                v1[v3].sampleMetadata(((MetadataSampleInfo)v0).presentationTimeDeltaUs + arg12, 1, ((MetadataSampleInfo)v0).size, this.pendingMetadataSampleBytes, null);
            }
        }
    }

    private static long parseMehd(ParsableByteArray arg2) {
        arg2.setPosition(8);
        long v0 = Atom.parseFullAtomVersion(arg2.readInt()) == 0 ? arg2.readUnsignedInt() : arg2.readUnsignedLongToLong();
        return v0;
    }

    private static void parseMoof(ContainerAtom arg5, SparseArray arg6, int arg7, byte[] arg8) {
        int v0 = arg5.containerChildren.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg5.containerChildren.get(v1);
            if(((ContainerAtom)v2).type == Atom.TYPE_traf) {
                FragmentedMp4Extractor.parseTraf(((ContainerAtom)v2), arg6, arg7, arg8);
            }
        }
    }

    private static void parseSaio(ParsableByteArray arg5, TrackFragment arg6) {
        int v0 = 8;
        arg5.setPosition(v0);
        int v1 = arg5.readInt();
        if((Atom.parseFullAtomFlags(v1) & 1) == 1) {
            arg5.skipBytes(v0);
        }

        v0 = arg5.readUnsignedIntToInt();
        if(v0 == 1) {
            v0 = Atom.parseFullAtomVersion(v1);
            long v1_1 = arg6.auxiliaryDataPosition;
            long v3 = v0 == 0 ? arg5.readUnsignedInt() : arg5.readUnsignedLongToLong();
            arg6.auxiliaryDataPosition = v1_1 + v3;
            return;
        }

        StringBuilder v6 = new StringBuilder();
        v6.append("Unexpected saio entry count: ");
        v6.append(v0);
        throw new ParserException(v6.toString());
    }

    private static void parseSaiz(TrackEncryptionBox arg7, ParsableByteArray arg8, TrackFragment arg9) {
        int v5;
        int v7 = arg7.initializationVectorSize;
        int v0 = 8;
        arg8.setPosition(v0);
        boolean v2 = true;
        if((Atom.parseFullAtomFlags(arg8.readInt()) & 1) == 1) {
            arg8.skipBytes(v0);
        }

        v0 = arg8.readUnsignedByte();
        int v1 = arg8.readUnsignedIntToInt();
        if(v1 == arg9.sampleCount) {
            if(v0 == 0) {
                boolean[] v0_1 = arg9.sampleHasSubsampleEncryptionTable;
                int v4 = 0;
                v5 = 0;
                while(v4 < v1) {
                    int v6 = arg8.readUnsignedByte();
                    v5 += v6;
                    boolean v6_1 = v6 > v7 ? true : false;
                    v0_1[v4] = v6_1;
                    ++v4;
                }
            }
            else {
                if(v0 > v7) {
                }
                else {
                    v2 = false;
                }

                v5 = v0 * v1;
                Arrays.fill(arg9.sampleHasSubsampleEncryptionTable, 0, v1, v2);
            }

            arg9.initEncryptionData(v5);
            return;
        }

        StringBuilder v8 = new StringBuilder();
        v8.append("Length mismatch: ");
        v8.append(v1);
        v8.append(", ");
        v8.append(arg9.sampleCount);
        throw new ParserException(v8.toString());
    }

    private static void parseSenc(ParsableByteArray arg3, int arg4, TrackFragment arg5) {
        arg3.setPosition(arg4 + 8);
        arg4 = Atom.parseFullAtomFlags(arg3.readInt());
        if((arg4 & 1) == 0) {
            boolean v4 = (arg4 & 2) != 0 ? true : false;
            int v1 = arg3.readUnsignedIntToInt();
            if(v1 == arg5.sampleCount) {
                Arrays.fill(arg5.sampleHasSubsampleEncryptionTable, 0, v1, v4);
                arg5.initEncryptionData(arg3.bytesLeft());
                arg5.fillEncryptionData(arg3);
                return;
            }

            StringBuilder v4_1 = new StringBuilder();
            v4_1.append("Length mismatch: ");
            v4_1.append(v1);
            v4_1.append(", ");
            v4_1.append(arg5.sampleCount);
            throw new ParserException(v4_1.toString());
        }

        throw new ParserException("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    private static void parseSenc(ParsableByteArray arg1, TrackFragment arg2) {
        FragmentedMp4Extractor.parseSenc(arg1, 0, arg2);
    }

    private static void parseSgpd(ParsableByteArray arg12, ParsableByteArray arg13, String arg14, TrackFragment arg15) {
        byte[] v11;
        int v0 = 8;
        arg12.setPosition(v0);
        int v1 = arg12.readInt();
        if(arg12.readInt() != FragmentedMp4Extractor.SAMPLE_GROUP_TYPE_seig) {
            return;
        }

        int v2 = 4;
        if(Atom.parseFullAtomVersion(v1) == 1) {
            arg12.skipBytes(v2);
        }

        if(arg12.readInt() == 1) {
            arg13.setPosition(v0);
            int v12 = arg13.readInt();
            if(arg13.readInt() != FragmentedMp4Extractor.SAMPLE_GROUP_TYPE_seig) {
                return;
            }

            v12 = Atom.parseFullAtomVersion(v12);
            if(v12 == 1) {
                if(arg13.readUnsignedInt() != 0) {
                }
                else {
                    throw new ParserException("Variable length description in sgpd found (unsupported)");
                }
            }
            else if(v12 >= 2) {
                arg13.skipBytes(v2);
            }

            if(arg13.readUnsignedInt() == 1) {
                arg13.skipBytes(1);
                v12 = arg13.readUnsignedByte();
                int v9 = (v12 & 240) >> 4;
                int v10 = v12 & 15;
                boolean v5 = arg13.readUnsignedByte() == 1 ? true : false;
                if(!v5) {
                    return;
                }

                int v7 = arg13.readUnsignedByte();
                byte[] v8 = new byte[16];
                arg13.readBytes(v8, 0, v8.length);
                byte[] v12_1 = null;
                if(!v5 || v7 != 0) {
                    v11 = v12_1;
                }
                else {
                    v12 = arg13.readUnsignedByte();
                    byte[] v1_1 = new byte[v12];
                    arg13.readBytes(v1_1, 0, v12);
                    v11 = v1_1;
                }

                arg15.definesEncryptionData = true;
                arg15.trackEncryptionBox = new TrackEncryptionBox(v5, arg14, v7, v8, v9, v10, v11);
                return;
            }

            throw new ParserException("Entry count in sgpd != 1 (unsupported).");
        }

        throw new ParserException("Entry count in sbgp != 1 (unsupported).");
    }

    private static Pair parseSidx(ParsableByteArray arg24, long arg25) {
        long v7;
        long v5;
        ParsableByteArray v0 = arg24;
        v0.setPosition(8);
        int v3 = Atom.parseFullAtomVersion(arg24.readInt());
        v0.skipBytes(4);
        long v11 = arg24.readUnsignedInt();
        if(v3 == 0) {
            v5 = arg24.readUnsignedInt();
            v7 = arg24.readUnsignedInt();
        }
        else {
            v5 = arg24.readUnsignedLongToLong();
            v7 = arg24.readUnsignedLongToLong();
        }

        long v13 = arg25 + v7;
        long v1 = v5;
        long v15 = Util.scaleLargeTimestamp(v1, 1000000, v11);
        v0.skipBytes(2);
        v3 = arg24.readUnsignedShort();
        int[] v9 = new int[v3];
        long[] v10 = new long[v3];
        long[] v7_1 = new long[v3];
        long[] v8 = new long[v3];
        long v17 = v1;
        v5 = v15;
        int v1_1 = 0;
        while(true) {
            if(v1_1 >= v3) {
                goto label_75;
            }

            int v2 = arg24.readInt();
            if((v2 & -2147483648) != 0) {
                break;
            }

            long v19 = arg24.readUnsignedInt();
            v9[v1_1] = v2 & 2147483647;
            v10[v1_1] = v13;
            v8[v1_1] = v5;
            v17 += v19;
            v5 = Util.scaleLargeTimestamp(v17, 1000000, v11);
            v7_1[v1_1] = v5 - v8[v1_1];
            v0.skipBytes(4);
            v13 += ((long)v9[v1_1]);
            ++v1_1;
            v7_1 = v7_1;
            v9 = v9;
            v8 = v8;
            v3 = v3;
            v10 = v10;
        }

        throw new ParserException("Unhandled indirect reference");
    label_75:
        return Pair.create(Long.valueOf(v15), new ChunkIndex(v9, v10, v7_1, v8));
    }

    private static long parseTfdt(ParsableByteArray arg2) {
        arg2.setPosition(8);
        long v0 = Atom.parseFullAtomVersion(arg2.readInt()) == 1 ? arg2.readUnsignedLongToLong() : arg2.readUnsignedInt();
        return v0;
    }

    private static TrackBundle parseTfhd(ParsableByteArray arg5, SparseArray arg6) {
        arg5.setPosition(8);
        int v0 = Atom.parseFullAtomFlags(arg5.readInt());
        TrackBundle v6 = FragmentedMp4Extractor.getTrackBundle(arg6, arg5.readInt());
        if(v6 == null) {
            return null;
        }

        if((v0 & 1) != 0) {
            long v1 = arg5.readUnsignedLongToLong();
            v6.fragment.dataPosition = v1;
            v6.fragment.auxiliaryDataPosition = v1;
        }

        DefaultSampleValues v1_1 = v6.defaultSampleValues;
        int v2 = (v0 & 2) != 0 ? arg5.readUnsignedIntToInt() - 1 : v1_1.sampleDescriptionIndex;
        int v3 = (v0 & 8) != 0 ? arg5.readUnsignedIntToInt() : v1_1.duration;
        int v4 = (v0 & 16) != 0 ? arg5.readUnsignedIntToInt() : v1_1.size;
        int v5 = (v0 & 32) != 0 ? arg5.readUnsignedIntToInt() : v1_1.flags;
        v6.fragment.header = new DefaultSampleValues(v2, v3, v4, v5);
        return v6;
    }

    private static void parseTraf(ContainerAtom arg4, SparseArray arg5, int arg6, byte[] arg7) {
        TrackBundle v5 = FragmentedMp4Extractor.parseTfhd(arg4.getLeafAtomOfType(Atom.TYPE_tfhd).data, arg5);
        if(v5 == null) {
            return;
        }

        TrackFragment v0 = v5.fragment;
        long v1 = v0.nextFragmentDecodeTime;
        v5.reset();
        if(arg4.getLeafAtomOfType(Atom.TYPE_tfdt) != null && (arg6 & 2) == 0) {
            v1 = FragmentedMp4Extractor.parseTfdt(arg4.getLeafAtomOfType(Atom.TYPE_tfdt).data);
        }

        FragmentedMp4Extractor.parseTruns(arg4, v5, v1, arg6);
        TrackEncryptionBox v5_1 = v5.track.getSampleDescriptionEncryptionBox(v0.header.sampleDescriptionIndex);
        LeafAtom v6 = arg4.getLeafAtomOfType(Atom.TYPE_saiz);
        if(v6 != null) {
            FragmentedMp4Extractor.parseSaiz(v5_1, v6.data, v0);
        }

        v6 = arg4.getLeafAtomOfType(Atom.TYPE_saio);
        if(v6 != null) {
            FragmentedMp4Extractor.parseSaio(v6.data, v0);
        }

        v6 = arg4.getLeafAtomOfType(Atom.TYPE_senc);
        if(v6 != null) {
            FragmentedMp4Extractor.parseSenc(v6.data, v0);
        }

        v6 = arg4.getLeafAtomOfType(Atom.TYPE_sbgp);
        LeafAtom v1_1 = arg4.getLeafAtomOfType(Atom.TYPE_sgpd);
        if(v6 != null && v1_1 != null) {
            ParsableByteArray v6_1 = v6.data;
            ParsableByteArray v1_2 = v1_1.data;
            String v5_2 = v5_1 != null ? v5_1.schemeType : null;
            FragmentedMp4Extractor.parseSgpd(v6_1, v1_2, v5_2, v0);
        }

        int v5_3 = arg4.leafChildren.size();
        for(arg6 = 0; arg6 < v5_3; ++arg6) {
            Object v1_3 = arg4.leafChildren.get(arg6);
            if(((LeafAtom)v1_3).type == Atom.TYPE_uuid) {
                FragmentedMp4Extractor.parseUuid(((LeafAtom)v1_3).data, v0, arg7);
            }
        }
    }

    private static Pair parseTrex(ParsableByteArray arg5) {
        arg5.setPosition(12);
        return Pair.create(Integer.valueOf(arg5.readInt()), new DefaultSampleValues(arg5.readUnsignedIntToInt() - 1, arg5.readUnsignedIntToInt(), arg5.readUnsignedIntToInt(), arg5.readInt()));
    }

    private static int parseTrun(TrackBundle arg35, int arg36, long arg37, int arg39, ParsableByteArray arg40, int arg41) {
        boolean v6_1;
        DefaultSampleValues v34;
        int v16;
        int v33;
        int v32;
        long v13_1;
        long v29;
        long[] v31;
        arg40.setPosition(8);
        int v2 = Atom.parseFullAtomFlags(arg40.readInt());
        Track v4 = arg35.track;
        TrackFragment v0 = arg35.fragment;
        DefaultSampleValues v5 = v0.header;
        v0.trunLength[arg36] = arg40.readUnsignedIntToInt();
        v0.trunDataPosition[arg36] = v0.dataPosition;
        if((v2 & 1) != 0) {
            v0.trunDataPosition[arg36] += ((long)arg40.readInt());
        }

        int v6 = (v2 & 4) != 0 ? 1 : 0;
        int v9 = v5.flags;
        if(v6 != 0) {
            v9 = arg40.readUnsignedIntToInt();
        }

        int v10 = (v2 & 256) != 0 ? 1 : 0;
        int v11 = (v2 & 512) != 0 ? 1 : 0;
        int v12 = (v2 & 1024) != 0 ? 1 : 0;
        v2 = (v2 & 2048) != 0 ? 1 : 0;
        long v14 = 0;
        if(v4.editListDurations != null && v4.editListDurations.length == 1 && v4.editListDurations[0] == v14) {
            v14 = Util.scaleLargeTimestamp(v4.editListMediaTimes[0], 1000, v4.timescale);
        }

        int[] v7 = v0.sampleSizeTable;
        int[] v8 = v0.sampleCompositionTimeOffsetTable;
        long[] v13 = v0.sampleDecodingTimeTable;
        int v20 = v9;
        boolean[] v21 = v0.sampleIsSyncFrameTable;
        int v3 = v4.type != 2 || (arg39 & 1) == 0 ? 0 : 1;
        v9 = arg41 + v0.trunLength[arg36];
        int v28 = v3;
        long v3_1 = v4.timescale;
        if(arg36 > 0) {
            v31 = v13;
            v29 = v14;
            v13_1 = v0.nextFragmentDecodeTime;
        }
        else {
            v31 = v13;
            v29 = v14;
            v13_1 = arg37;
        }

        int v1 = arg41;
        while(v1 < v9) {
            int v15 = v10 != 0 ? arg40.readUnsignedIntToInt() : v5.duration;
            if(v11 != 0) {
                v32 = v10;
                v10 = arg40.readUnsignedIntToInt();
            }
            else {
                v32 = v10;
                v10 = v5.size;
            }

            if(v1 == 0 && v6 != 0) {
                v33 = v6;
                v16 = v20;
            }
            else if(v12 != 0) {
                v16 = arg40.readInt();
                v33 = v6;
            }
            else {
                v33 = v6;
                v16 = v5.flags;
            }

            if(v2 != 0) {
                v34 = v5;
                v8[v1] = ((int)((((long)arg40.readInt())) * 1000 / v3_1));
            }
            else {
                v34 = v5;
                v8[v1] = 0;
            }

            v31[v1] = Util.scaleLargeTimestamp(v13_1, 1000, v3_1) - v29;
            v7[v1] = v10;
            if((v16 >> 16 & 1) == 0) {
                if(v28 != 0 && v1 != 0) {
                    goto label_150;
                }

                v6_1 = true;
            }
            else {
            label_150:
                v6_1 = false;
            }

            v21[v1] = v6_1;
            v13_1 += ((long)v15);
            ++v1;
            v10 = v32;
            v6 = v33;
            v5 = v34;
        }

        v0.nextFragmentDecodeTime = v13_1;
        return v9;
    }

    private static void parseTruns(ContainerAtom arg10, TrackBundle arg11, long arg12, int arg14) {
        List v10 = arg10.leafChildren;
        int v0 = v10.size();
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        while(v2 < v0) {
            Object v5 = v10.get(v2);
            if(((LeafAtom)v5).type == Atom.TYPE_trun) {
                ParsableByteArray v5_1 = ((LeafAtom)v5).data;
                v5_1.setPosition(12);
                int v5_2 = v5_1.readUnsignedIntToInt();
                if(v5_2 > 0) {
                    v4 += v5_2;
                    ++v3;
                }
            }

            ++v2;
        }

        arg11.currentTrackRunIndex = 0;
        arg11.currentSampleInTrackRun = 0;
        arg11.currentSampleIndex = 0;
        arg11.fragment.initTables(v3, v4);
        v3 = 0;
        int v8 = 0;
        while(v1 < v0) {
            Object v2_1 = v10.get(v1);
            if(((LeafAtom)v2_1).type == Atom.TYPE_trun) {
                v8 = FragmentedMp4Extractor.parseTrun(arg11, v3, arg12, arg14, ((LeafAtom)v2_1).data, v8);
                ++v3;
            }

            ++v1;
        }
    }

    private static void parseUuid(ParsableByteArray arg2, TrackFragment arg3, byte[] arg4) {
        arg2.setPosition(8);
        int v0 = 16;
        arg2.readBytes(arg4, 0, v0);
        if(!Arrays.equals(arg4, FragmentedMp4Extractor.PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            return;
        }

        FragmentedMp4Extractor.parseSenc(arg2, v0, arg3);
    }

    private void processAtomEnded(long arg4) {
        while(!this.containerAtoms.isEmpty()) {
            if(this.containerAtoms.peek().endPosition != arg4) {
                break;
            }

            this.onContainerAtomRead(this.containerAtoms.pop());
        }

        this.enterReadingAtomHeaderState();
    }

    public int read(ExtractorInput arg1, PositionHolder arg2) {
        do {
        label_0:
            switch(this.parserState) {
                case 0: {
                    goto label_10;
                }
                case 1: {
                    goto label_8;
                }
                case 2: {
                    goto label_6;
                }
            }

            if(!this.readSample(arg1)) {
                goto label_0;
            }

            return 0;
        label_6:
            this.readEncryptionData(arg1);
            goto label_0;
        label_8:
            this.readAtomPayload(arg1);
            goto label_0;
        label_10:
        }
        while(this.readAtomHeader(arg1));

        return -1;
    }

    private boolean readAtomHeader(ExtractorInput arg9) {
        long v4;
        int v1 = 8;
        if(this.atomHeaderBytesRead == 0) {
            if(!arg9.readFully(this.atomHeader.data, 0, v1, true)) {
                return 0;
            }
            else {
                this.atomHeaderBytesRead = v1;
                this.atomHeader.setPosition(0);
                this.atomSize = this.atomHeader.readUnsignedInt();
                this.atomType = this.atomHeader.readInt();
            }
        }

        if(this.atomSize == 1) {
            arg9.readFully(this.atomHeader.data, v1, v1);
            this.atomHeaderBytesRead += v1;
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
        if(this.atomSize < (((long)this.atomHeaderBytesRead))) {
            goto label_150;
        }

        v4 = arg9.getPosition() - (((long)this.atomHeaderBytesRead));
        if(this.atomType == Atom.TYPE_moof) {
            int v0 = this.trackBundles.size();
            int v6_1;
            for(v6_1 = 0; v6_1 < v0; ++v6_1) {
                TrackFragment v7 = this.trackBundles.valueAt(v6_1).fragment;
                v7.atomPosition = v4;
                v7.auxiliaryDataPosition = v4;
                v7.dataPosition = v4;
            }
        }

        TrackBundle v7_1 = null;
        if(this.atomType == Atom.TYPE_mdat) {
            this.currentTrackBundle = v7_1;
            this.endOfMdatPosition = this.atomSize + v4;
            if(!this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(new Unseekable(this.durationUs, v4));
                this.haveOutputSeekMap = true;
            }

            this.parserState = 2;
            return 1;
        }

        if(FragmentedMp4Extractor.shouldParseContainerAtom(this.atomType)) {
            long v0_1 = arg9.getPosition() + this.atomSize - 8;
            this.containerAtoms.push(new ContainerAtom(this.atomType, v0_1));
            if(this.atomSize == (((long)this.atomHeaderBytesRead))) {
                this.processAtomEnded(v0_1);
            }
            else {
                this.enterReadingAtomHeaderState();
            }
        }
        else {
            v4 = 2147483647;
            if(FragmentedMp4Extractor.shouldParseLeafAtom(this.atomType)) {
                if(this.atomHeaderBytesRead != v1) {
                    throw new ParserException("Leaf atom defines extended atom size (unsupported).");
                }
                else if(this.atomSize <= v4) {
                    this.atomData = new ParsableByteArray(((int)this.atomSize));
                    System.arraycopy(this.atomHeader.data, 0, this.atomData.data, 0, v1);
                }
                else {
                    throw new ParserException("Leaf atom with length > 2147483647 (unsupported).");
                }
            }
            else if(this.atomSize <= v4) {
                this.atomData = ((ParsableByteArray)v7_1);
            }
            else {
                goto label_146;
            }

            this.parserState = 1;
        }

        return 1;
    label_146:
        throw new ParserException("Skipping atom with length > 2147483647 (unsupported).");
    label_150:
        throw new ParserException("Atom size less than header length (unsupported).");
    }

    private void readAtomPayload(ExtractorInput arg4) {
        int v0 = (((int)this.atomSize)) - this.atomHeaderBytesRead;
        if(this.atomData != null) {
            arg4.readFully(this.atomData.data, 8, v0);
            this.onLeafAtomRead(new LeafAtom(this.atomType, this.atomData), arg4.getPosition());
        }
        else {
            arg4.skipFully(v0);
        }

        this.processAtomEnded(arg4.getPosition());
    }

    private void readEncryptionData(ExtractorInput arg12) {
        int v0 = this.trackBundles.size();
        Object v1 = null;
        long v2 = 9223372036854775807L;
        int v4;
        for(v4 = 0; v4 < v0; ++v4) {
            TrackFragment v5 = this.trackBundles.valueAt(v4).fragment;
            if((v5.sampleEncryptionDataNeedsFill) && v5.auxiliaryDataPosition < v2) {
                long v9 = v5.auxiliaryDataPosition;
                v1 = this.trackBundles.valueAt(v4);
                v2 = v9;
            }
        }

        if(v1 == null) {
            this.parserState = 3;
            return;
        }

        v0 = ((int)(v2 - arg12.getPosition()));
        if(v0 >= 0) {
            arg12.skipFully(v0);
            ((TrackBundle)v1).fragment.fillEncryptionData(arg12);
            return;
        }

        throw new ParserException("Offset to encryption data was negative.");
    }

    private boolean readSample(ExtractorInput arg18) {
        boolean v13_1;
        CryptoData v16;
        int v3_2;
        FragmentedMp4Extractor v0 = this;
        ExtractorInput v1 = arg18;
        TrackBundle v3 = null;
        int v4 = 3;
        int v5 = 4;
        int v6 = 1;
        int v7 = 0;
        if(v0.parserState == v4) {
            if(v0.currentTrackBundle == null) {
                TrackBundle v2 = FragmentedMp4Extractor.getNextFragmentRun(v0.trackBundles);
                if(v2 == null) {
                    int v2_1 = ((int)(v0.endOfMdatPosition - arg18.getPosition()));
                    if(v2_1 >= 0) {
                        v1.skipFully(v2_1);
                        this.enterReadingAtomHeaderState();
                        return 0;
                    }
                    else {
                        throw new ParserException("Offset to end of mdat was negative.");
                    }
                }
                else {
                    int v8 = ((int)(v2.fragment.trunDataPosition[v2.currentTrackRunIndex] - arg18.getPosition()));
                    if(v8 < 0) {
                        Log.w("FragmentedMp4Extractor", "Ignoring negative offset to sample data.");
                        v8 = 0;
                    }

                    v1.skipFully(v8);
                    v0.currentTrackBundle = v2;
                }
            }

            v0.sampleSize = v0.currentTrackBundle.fragment.sampleSizeTable[v0.currentTrackBundle.currentSampleIndex];
            if(v0.currentTrackBundle.currentSampleIndex < v0.currentTrackBundle.firstSampleToOutputIndex) {
                v1.skipFully(v0.sampleSize);
                TrackBundle.access$000(v0.currentTrackBundle);
                if(!v0.currentTrackBundle.next()) {
                    v0.currentTrackBundle = v3;
                }

                v0.parserState = v4;
                return 1;
            }

            if(v0.currentTrackBundle.track.sampleTransformation == 1) {
                v0.sampleSize -= 8;
                v1.skipFully(8);
            }

            v0.sampleBytesWritten = v0.currentTrackBundle.outputSampleEncryptionData();
            v0.sampleSize += v0.sampleBytesWritten;
            v0.parserState = v5;
            v0.sampleCurrentNalBytesRemaining = 0;
        }

        TrackFragment v2_2 = v0.currentTrackBundle.fragment;
        Track v8_1 = v0.currentTrackBundle.track;
        TrackOutput v10 = v0.currentTrackBundle.output;
        int v9 = v0.currentTrackBundle.currentSampleIndex;
        long v12 = 1000;
        if(v8_1.nalUnitLengthFieldLength != 0) {
            byte[] v11 = v0.nalPrefix.data;
            v11[0] = 0;
            v11[1] = 0;
            v11[2] = 0;
            int v14 = v8_1.nalUnitLengthFieldLength + 1;
            int v15 = 4 - v8_1.nalUnitLengthFieldLength;
            while(v0.sampleBytesWritten < v0.sampleSize) {
                if(v0.sampleCurrentNalBytesRemaining == 0) {
                    v1.readFully(v11, v15, v14);
                    v0.nalPrefix.setPosition(v7);
                    v0.sampleCurrentNalBytesRemaining = v0.nalPrefix.readUnsignedIntToInt() - v6;
                    v0.nalStartCode.setPosition(v7);
                    v10.sampleData(v0.nalStartCode, v5);
                    v10.sampleData(v0.nalPrefix, v6);
                    boolean v3_1 = v0.cea608TrackOutputs.length <= 0 || !NalUnitUtil.isNalUnitSei(v8_1.format.sampleMimeType, v11[v5]) ? false : true;
                    v0.processSeiNalUnitPayload = v3_1;
                    v0.sampleBytesWritten += 5;
                    v0.sampleSize += v15;
                }
                else {
                    if(v0.processSeiNalUnitPayload) {
                        v0.nalBuffer.reset(v0.sampleCurrentNalBytesRemaining);
                        v1.readFully(v0.nalBuffer.data, v7, v0.sampleCurrentNalBytesRemaining);
                        v10.sampleData(v0.nalBuffer, v0.sampleCurrentNalBytesRemaining);
                        v3_2 = v0.sampleCurrentNalBytesRemaining;
                        v4 = NalUnitUtil.unescapeStream(v0.nalBuffer.data, v0.nalBuffer.limit());
                        v0.nalBuffer.setPosition("video/hevc".equals(v8_1.format.sampleMimeType));
                        v0.nalBuffer.setLimit(v4);
                        CeaUtil.consume(v2_2.getSamplePresentationTime(v9) * v12, v0.nalBuffer, v0.cea608TrackOutputs);
                    }
                    else {
                        v3_2 = v10.sampleData(v1, v0.sampleCurrentNalBytesRemaining, false);
                    }

                    v0.sampleBytesWritten += v3_2;
                    v0.sampleCurrentNalBytesRemaining -= v3_2;
                    v5 = 4;
                    v6 = 1;
                    v7 = 0;
                }
            }
        }
        else {
            while(v0.sampleBytesWritten < v0.sampleSize) {
                v0.sampleBytesWritten += v10.sampleData(v1, v0.sampleSize - v0.sampleBytesWritten, false);
            }
        }

        long v3_3 = v2_2.getSamplePresentationTime(v9) * v12;
        if(v0.timestampAdjuster != null) {
            v3_3 = v0.timestampAdjuster.adjustSampleTimestamp(v3_3);
        }

        boolean v1_1 = v2_2.sampleIsSyncFrameTable[v9];
        if(v2_2.definesEncryptionData) {
            int v1_2 = (((int)v1_1)) | 1073741824;
            TrackEncryptionBox v2_3 = v2_2.trackEncryptionBox != null ? v2_2.trackEncryptionBox : v8_1.getSampleDescriptionEncryptionBox(v2_2.header.sampleDescriptionIndex);
            int v13 = v1_2;
            v16 = v2_3.cryptoData;
        }
        else {
            v13_1 = v1_1;
            v16 = null;
        }

        v10.sampleMetadata(v3_3, ((int)v13_1), v0.sampleSize, 0, v16);
        v0.outputPendingMetadataSamples(v3_3);
        if(!v0.currentTrackBundle.next()) {
            v0.currentTrackBundle = null;
        }

        v0.parserState = 3;
        return 1;
    }

    public void release() {
    }

    public void seek(long arg3, long arg5) {
        int v3 = this.trackBundles.size();
        int v0;
        for(v0 = 0; v0 < v3; ++v0) {
            this.trackBundles.valueAt(v0).reset();
        }

        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.pendingSeekTimeUs = arg5;
        this.containerAtoms.clear();
        this.enterReadingAtomHeaderState();
    }

    private static boolean shouldParseContainerAtom(int arg1) {
        boolean v1 = arg1 == Atom.TYPE_moov || arg1 == Atom.TYPE_trak || arg1 == Atom.TYPE_mdia || arg1 == Atom.TYPE_minf || arg1 == Atom.TYPE_stbl || arg1 == Atom.TYPE_moof || arg1 == Atom.TYPE_traf || arg1 == Atom.TYPE_mvex || arg1 == Atom.TYPE_edts ? true : false;
        return v1;
    }

    private static boolean shouldParseLeafAtom(int arg1) {
        boolean v1 = arg1 == Atom.TYPE_hdlr || arg1 == Atom.TYPE_mdhd || arg1 == Atom.TYPE_mvhd || arg1 == Atom.TYPE_sidx || arg1 == Atom.TYPE_stsd || arg1 == Atom.TYPE_tfdt || arg1 == Atom.TYPE_tfhd || arg1 == Atom.TYPE_tkhd || arg1 == Atom.TYPE_trex || arg1 == Atom.TYPE_trun || arg1 == Atom.TYPE_pssh || arg1 == Atom.TYPE_saiz || arg1 == Atom.TYPE_saio || arg1 == Atom.TYPE_senc || arg1 == Atom.TYPE_uuid || arg1 == Atom.TYPE_sbgp || arg1 == Atom.TYPE_sgpd || arg1 == Atom.TYPE_elst || arg1 == Atom.TYPE_mehd || arg1 == Atom.TYPE_emsg ? true : false;
        return v1;
    }

    public boolean sniff(ExtractorInput arg1) {
        return Sniffer.sniffFragmented(arg1);
    }
}

