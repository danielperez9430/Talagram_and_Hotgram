package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource$InvalidResponseCodeException;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultDashChunkSource implements DashChunkSource {
    public final class Factory implements com.google.android.exoplayer2.source.dash.DashChunkSource$Factory {
        private final com.google.android.exoplayer2.upstream.DataSource$Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg2) {
            this(arg2, 1);
        }

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg1, int arg2) {
            super();
            this.dataSourceFactory = arg1;
            this.maxSegmentsPerLoad = arg2;
        }

        public DashChunkSource createDashChunkSource(LoaderErrorThrower arg18, DashManifest arg19, int arg20, int[] arg21, TrackSelection arg22, int arg23, long arg24, boolean arg26, boolean arg27, PlayerTrackEmsgHandler arg28, TransferListener arg29) {
            Factory v0 = this;
            TransferListener v1 = arg29;
            DataSource v10 = v0.dataSourceFactory.createDataSource();
            if(v1 != null) {
                v10.addTransferListener(v1);
            }

            return new DefaultDashChunkSource(arg18, arg19, arg20, arg21, arg22, arg23, v10, arg24, v0.maxSegmentsPerLoad, arg26, arg27, arg28);
        }
    }

    public final class RepresentationHolder {
        final ChunkExtractorWrapper extractorWrapper;
        private final long periodDurationUs;
        public final Representation representation;
        public final DashSegmentIndex segmentIndex;
        private final long segmentNumShift;

        RepresentationHolder(long arg9, int arg11, Representation arg12, boolean arg13, boolean arg14, TrackOutput arg15) {
            this(arg9, arg12, RepresentationHolder.createExtractorWrapper(arg11, arg12, arg13, arg14, arg15), 0, arg12.getIndex());
        }

        private RepresentationHolder(long arg1, Representation arg3, ChunkExtractorWrapper arg4, long arg5, DashSegmentIndex arg7) {
            super();
            this.periodDurationUs = arg1;
            this.representation = arg3;
            this.segmentNumShift = arg5;
            this.extractorWrapper = arg4;
            this.segmentIndex = arg7;
        }

        RepresentationHolder copyWithNewRepresentation(long arg19, Representation arg21) {
            RepresentationHolder v0 = this;
            long v2 = arg19;
            DashSegmentIndex v8 = v0.representation.getIndex();
            DashSegmentIndex v9 = arg21.getIndex();
            if(v8 == null) {
                return new RepresentationHolder(arg19, arg21, v0.extractorWrapper, v0.segmentNumShift, v8);
            }

            if(!v8.isExplicit()) {
                return new RepresentationHolder(arg19, arg21, v0.extractorWrapper, v0.segmentNumShift, v9);
            }

            int v1 = v8.getSegmentCount(v2);
            if(v1 == 0) {
                return new RepresentationHolder(arg19, arg21, v0.extractorWrapper, v0.segmentNumShift, v9);
            }

            long v4 = v8.getFirstSegmentNum() + (((long)v1)) - 1;
            long v10 = v8.getTimeUs(v4) + v8.getDurationUs(v4, v2);
            long v12 = v9.getFirstSegmentNum();
            long v14 = v9.getTimeUs(v12);
            long v6 = v0.segmentNumShift;
            if(v10 == v14) {
                ++v4;
            }
            else if(v10 >= v14) {
                v4 = v8.getSegmentNum(v14, v2);
            }
            else {
                goto label_68;
            }

            v6 += v4 - v12;
            return new RepresentationHolder(arg19, arg21, v0.extractorWrapper, v6, v9);
        label_68:
            throw new BehindLiveWindowException();
        }

        RepresentationHolder copyWithNewSegmentIndex(DashSegmentIndex arg10) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, this.extractorWrapper, this.segmentNumShift, arg10);
        }

        private static ChunkExtractorWrapper createExtractorWrapper(int arg10, Representation arg11, boolean arg12, boolean arg13, TrackOutput arg14) {
            FragmentedMp4Extractor v12_3;
            String v0 = arg11.format.containerMimeType;
            ChunkExtractorWrapper v2 = null;
            if(RepresentationHolder.mimeTypeIsRawText(v0)) {
                return v2;
            }

            if("application/x-rawcc".equals(v0)) {
                RawCcExtractor v12 = new RawCcExtractor(arg11.format);
            }
            else if(RepresentationHolder.mimeTypeIsWebm(v0)) {
                MatroskaExtractor v12_1 = new MatroskaExtractor(1);
            }
            else {
                int v4 = arg12 ? 4 : 0;
                List v12_2 = arg13 ? Collections.singletonList(Format.createTextSampleFormat(((String)v2), "application/cea-608", 0, ((String)v2))) : Collections.emptyList();
                List v8 = v12_2;
                v12_3 = new FragmentedMp4Extractor(v4, null, null, null, v8, arg14);
            }

            return new ChunkExtractorWrapper(((Extractor)v12_3), arg10, arg11.format);
        }

        public long getFirstSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public int getSegmentCount() {
            return this.segmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentEndTimeUs(long arg6) {
            return this.getSegmentStartTimeUs(arg6) + this.segmentIndex.getDurationUs(arg6 - this.segmentNumShift, this.periodDurationUs);
        }

        public long getSegmentNum(long arg4) {
            return this.segmentIndex.getSegmentNum(arg4, this.periodDurationUs) + this.segmentNumShift;
        }

        public long getSegmentStartTimeUs(long arg4) {
            return this.segmentIndex.getTimeUs(arg4 - this.segmentNumShift);
        }

        public RangedUri getSegmentUrl(long arg4) {
            return this.segmentIndex.getSegmentUrl(arg4 - this.segmentNumShift);
        }

        private static boolean mimeTypeIsRawText(String arg1) {
            boolean v1 = (MimeTypes.isText(arg1)) || ("application/ttml+xml".equals(arg1)) ? true : false;
            return v1;
        }

        private static boolean mimeTypeIsWebm(String arg1) {
            boolean v1 = (arg1.startsWith("video/webm")) || (arg1.startsWith("audio/webm")) || (arg1.startsWith("application/webm")) ? true : false;
            return v1;
        }
    }

    public final class RepresentationSegmentIterator extends BaseMediaChunkIterator {
        private final RepresentationHolder representationHolder;

        public RepresentationSegmentIterator(RepresentationHolder arg1, long arg2, long arg4) {
            super(arg2, arg4);
            this.representationHolder = arg1;
        }

        public long getChunkEndTimeUs() {
            this.checkInBounds();
            return this.representationHolder.getSegmentEndTimeUs(this.getCurrentIndex());
        }

        public long getChunkStartTimeUs() {
            this.checkInBounds();
            return this.representationHolder.getSegmentStartTimeUs(this.getCurrentIndex());
        }

        public DataSpec getDataSpec() {
            this.checkInBounds();
            Representation v0 = this.representationHolder.representation;
            RangedUri v1 = this.representationHolder.getSegmentUrl(this.getCurrentIndex());
            return new DataSpec(v1.resolveUri(v0.baseUrl), v1.start, v1.length, v0.getCacheKey());
        }
    }

    private final int[] adaptationSetIndices;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private long liveEdgeTimeUs;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private final TrackSelection trackSelection;
    private final int trackType;

    public DefaultDashChunkSource(LoaderErrorThrower arg19, DashManifest arg20, int arg21, int[] arg22, TrackSelection arg23, int arg24, DataSource arg25, long arg26, int arg28, boolean arg29, boolean arg30, PlayerTrackEmsgHandler arg31) {
        DefaultDashChunkSource v0 = this;
        TrackSelection v1 = arg23;
        super();
        v0.manifestLoaderErrorThrower = arg19;
        v0.manifest = arg20;
        v0.adaptationSetIndices = arg22;
        v0.trackSelection = v1;
        v0.trackType = arg24;
        v0.dataSource = arg25;
        v0.periodIndex = arg21;
        v0.elapsedRealtimeOffsetMs = arg26;
        v0.maxSegmentsPerLoad = arg28;
        v0.playerTrackEmsgHandler = arg31;
        long v12 = arg20.getPeriodDurationUs(arg21);
        v0.liveEdgeTimeUs = -9223372036854775807L;
        ArrayList v14 = this.getRepresentations();
        v0.representationHolders = new RepresentationHolder[arg23.length()];
        int v15;
        for(v15 = 0; v15 < v0.representationHolders.length; ++v15) {
            v0.representationHolders[v15] = new RepresentationHolder(v12, arg24, ((List)v14).get(v1.getIndexInTrackGroup(v15)), arg29, arg30, arg31);
        }
    }

    public long getAdjustedSeekPositionUs(long arg12, SeekParameters arg14) {
        RepresentationHolder[] v0 = this.representationHolders;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            RepresentationHolder v3 = v0[v2];
            if(v3.segmentIndex != null) {
                long v0_1 = v3.getSegmentNum(arg12);
                long v7 = v3.getSegmentStartTimeUs(v0_1);
                long v9 = v7 >= arg12 || v0_1 >= (((long)(v3.getSegmentCount() - 1))) ? v7 : v3.getSegmentStartTimeUs(v0_1 + 1);
                return Util.resolveSeekPositionUs(arg12, arg14, v7, v9);
            }
        }

        return arg12;
    }

    public void getNextChunk(long arg25, long arg27, List arg29, ChunkHolder arg30) {
        boolean v6_2;
        long v7_1;
        long v14;
        long v3_2;
        DefaultDashChunkSource v12 = this;
        long v0 = arg27;
        ChunkHolder v13 = arg30;
        if(v12.fatalError != null) {
            return;
        }

        long v6 = v0 - arg25;
        long v8 = this.resolveTimeToLiveEdgeUs(arg25);
        long v2 = C.msToUs(v12.manifest.availabilityStartTimeMs) + C.msToUs(v12.manifest.getPeriod(v12.periodIndex).startMs) + v0;
        if(v12.playerTrackEmsgHandler != null && (v12.playerTrackEmsgHandler.maybeRefreshManifestBeforeLoadingNextChunk(v2))) {
            return;
        }

        v12.trackSelection.updateSelectedTrack(arg25, v6, v8);
        RepresentationHolder v2_1 = v12.representationHolders[v12.trackSelection.getSelectedIndex()];
        if(v2_1.extractorWrapper != null) {
            Representation v3 = v2_1.representation;
            RangedUri v5 = null;
            RangedUri v6_1 = v2_1.extractorWrapper.getSampleFormats() == null ? v3.getInitializationUri() : v5;
            RangedUri v7 = v2_1.segmentIndex == null ? v3.getIndexUri() : v5;
            if(v6_1 == null && v7 == null) {
                goto label_67;
            }

            v13.chunk = this.newInitializationChunk(v2_1, v12.dataSource, v12.trackSelection.getSelectedFormat(), v12.trackSelection.getSelectionReason(), v12.trackSelection.getSelectionData(), v6_1, v7);
            return;
        }

    label_67:
        int v3_1 = v2_1.getSegmentCount();
        if(v3_1 == 0) {
            boolean v4 = !v12.manifest.dynamic || v12.periodIndex < v12.manifest.getPeriodCount() - 1 ? true : false;
            v13.endOfStream = v4;
            return;
        }

        v6 = v2_1.getFirstSegmentNum();
        long v9 = -9223372036854775807L;
        if(v3_1 == -1) {
            v3_2 = this.getNowUnixTimeUs() - C.msToUs(v12.manifest.availabilityStartTimeMs) - C.msToUs(v12.manifest.getPeriod(v12.periodIndex).startMs);
            if(v12.manifest.timeShiftBufferDepthMs != v9) {
                v6 = Math.max(v6, v2_1.getSegmentNum(v3_2 - C.msToUs(v12.manifest.timeShiftBufferDepthMs)));
            }

            v3_2 = v2_1.getSegmentNum(v3_2);
            v14 = 1;
        }
        else {
            v14 = 1;
            v3_2 = (((long)v3_1)) + v6;
        }

        v3_2 -= v14;
        long v20 = v6;
        v12.updateLiveEdgeTimeUs(v2_1, v3_2);
        if(arg29.isEmpty()) {
            v7_1 = Util.constrainValue(v2_1.getSegmentNum(v0), v20, v3_2);
        }
        else {
            v7_1 = arg29.get(arg29.size() - 1).getNextChunkIndex();
            if(v7_1 < v20) {
                v12.fatalError = new BehindLiveWindowException();
                return;
            }
        }

        if(v7_1 <= v3_2 && (!v12.missingLastSegment || v7_1 < v3_2)) {
            int v11 = ((int)Math.min(((long)v12.maxSegmentsPerLoad), v3_2 - v7_1 + 1));
            v14 = arg29.isEmpty() ? v0 : v9;
            v13.chunk = this.newMediaChunk(v2_1, v12.dataSource, v12.trackType, v12.trackSelection.getSelectedFormat(), v12.trackSelection.getSelectionReason(), v12.trackSelection.getSelectionData(), v7_1, v11, v14);
            return;
        }

        if(v12.manifest.dynamic) {
            v6_2 = true;
            if(v12.periodIndex < v12.manifest.getPeriodCount() - 1) {
            }
            else {
                v6_2 = false;
            }
        }
        else {
            v6_2 = true;
        }

        v13.endOfStream = v6_2;
    }

    private long getNowUnixTimeUs() {
        // Method was not decompiled
    }

    public int getPreferredQueueSize(long arg3, List arg5) {
        if(this.fatalError == null) {
            if(this.trackSelection.length() < 2) {
            }
            else {
                return this.trackSelection.evaluateQueueSize(arg3, arg5);
            }
        }

        return arg5.size();
    }

    private ArrayList getRepresentations() {
        List v0 = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList v1 = new ArrayList();
        int[] v2 = this.adaptationSetIndices;
        int v3 = v2.length;
        int v4;
        for(v4 = 0; v4 < v3; ++v4) {
            v1.addAll(v0.get(v2[v4]).representations);
        }

        return v1;
    }

    public void maybeThrowError() {
        if(this.fatalError == null) {
            this.manifestLoaderErrorThrower.maybeThrowError();
            return;
        }

        throw this.fatalError;
    }

    protected Chunk newInitializationChunk(RepresentationHolder arg9, DataSource arg10, Format arg11, int arg12, Object arg13, RangedUri arg14, RangedUri arg15) {
        String v0 = arg9.representation.baseUrl;
        if(arg14 != null) {
            arg15 = arg14.attemptMerge(arg15, v0);
            if(arg15 == null) {
                arg15 = arg14;
            }
        }

        return new InitializationChunk(arg10, new DataSpec(arg15.resolveUri(v0), arg15.start, arg15.length, arg9.representation.getCacheKey()), arg11, arg12, arg13, arg9.extractorWrapper);
    }

    protected Chunk newMediaChunk(RepresentationHolder arg24, DataSource arg25, int arg26, Format arg27, int arg28, Object arg29, long arg30, int arg32, long arg33) {
        RepresentationHolder v0 = arg24;
        long v13 = arg30;
        Representation v1 = v0.representation;
        long v7 = v0.getSegmentStartTimeUs(v13);
        RangedUri v2 = v0.getSegmentUrl(v13);
        String v4 = v1.baseUrl;
        if(v0.extractorWrapper == null) {
            return new SingleSampleMediaChunk(arg25, new DataSpec(v2.resolveUri(v4), v2.start, v2.length, v1.getCacheKey()), arg27, arg28, arg29, v7, v0.getSegmentEndTimeUs(v13), arg30, arg26, arg27);
        }

        int v3 = 1;
        RangedUri v5 = v2;
        int v15 = 1;
        int v2_1 = arg32;
        while(v3 < v2_1) {
            RangedUri v6 = v5.attemptMerge(v0.getSegmentUrl((((long)v3)) + v13), v4);
            if(v6 == null) {
            }
            else {
                ++v15;
                ++v3;
                v5 = v6;
                continue;
            }

            break;
        }

        long v9 = v0.getSegmentEndTimeUs((((long)v15)) + v13 - 1);
        new DataSpec(v5.resolveUri(v4), v5.start, v5.length, v1.getCacheKey());
        return new ContainerMediaChunk(arg25, null, arg27, arg28, arg29, v7, v9, arg33, arg30, v15, -v1.presentationTimeOffsetUs, v0.extractorWrapper);
    }

    public void onChunkLoadCompleted(Chunk arg8) {
        if((arg8 instanceof InitializationChunk)) {
            int v0 = this.trackSelection.indexOf(arg8.trackFormat);
            RepresentationHolder v1 = this.representationHolders[v0];
            if(v1.segmentIndex == null) {
                SeekMap v2 = v1.extractorWrapper.getSeekMap();
                if(v2 != null) {
                    this.representationHolders[v0] = v1.copyWithNewSegmentIndex(new DashWrappingSegmentIndex(((ChunkIndex)v2), v1.representation.presentationTimeOffsetUs));
                }
            }
        }

        if(this.playerTrackEmsgHandler != null) {
            this.playerTrackEmsgHandler.onChunkLoadCompleted(arg8);
        }
    }

    public boolean onChunkLoadError(Chunk arg7, boolean arg8, Exception arg9) {
        if(!arg8) {
            return 0;
        }

        if(this.playerTrackEmsgHandler != null && (this.playerTrackEmsgHandler.maybeRefreshManifestOnLoadingError(arg7))) {
            return 1;
        }

        if(!this.manifest.dynamic && ((arg7 instanceof MediaChunk)) && ((arg9 instanceof InvalidResponseCodeException)) && arg9.responseCode == 404) {
            RepresentationHolder v8 = this.representationHolders[this.trackSelection.indexOf(arg7.trackFormat)];
            int v1 = v8.getSegmentCount();
            if(v1 != -1 && v1 != 0 && arg7.getNextChunkIndex() > v8.getFirstSegmentNum() + (((long)v1)) - 1) {
                this.missingLastSegment = true;
                return 1;
            }
        }

        return ChunkedTrackBlacklistUtil.maybeBlacklistTrack(this.trackSelection, this.trackSelection.indexOf(arg7.trackFormat), arg9);
    }

    private long resolveTimeToLiveEdgeUs(long arg6) {
        long v1 = -9223372036854775807L;
        int v0 = !this.manifest.dynamic || this.liveEdgeTimeUs == v1 ? 0 : 1;
        return v0 != 0 ? this.liveEdgeTimeUs - arg6 : v1;
    }

    private void updateLiveEdgeTimeUs(RepresentationHolder arg2, long arg3) {
        long v2 = this.manifest.dynamic ? arg2.getSegmentEndTimeUs(arg3) : -9223372036854775807L;
        this.liveEdgeTimeUs = v2;
    }

    public void updateManifest(DashManifest arg6, int arg7) {
        try {
            this.manifest = arg6;
            this.periodIndex = arg7;
            long v6_1 = this.manifest.getPeriodDurationUs(this.periodIndex);
            ArrayList v0 = this.getRepresentations();
            int v1;
            for(v1 = 0; v1 < this.representationHolders.length; ++v1) {
                this.representationHolders[v1] = this.representationHolders[v1].copyWithNewRepresentation(v6_1, ((List)v0).get(this.trackSelection.getIndexInTrackGroup(v1)));
            }
        }
        catch(BehindLiveWindowException v6) {
            this.fatalError = ((IOException)v6);
        }
    }
}

