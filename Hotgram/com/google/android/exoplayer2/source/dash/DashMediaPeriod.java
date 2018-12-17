package com.google.android.exoplayer2.source.dash;

import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader$Callback;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream$EmbeddedSampleStream;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream$ReleaseCallback;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.Descriptor;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

final class DashMediaPeriod implements MediaPeriod, Callback, ReleaseCallback {
    final class TrackGroupInfo {
        @Retention(value=RetentionPolicy.SOURCE) @public interface TrackGroupCategory {
        }

        private static final int CATEGORY_EMBEDDED = 1;
        private static final int CATEGORY_MANIFEST_EVENTS = 2;
        private static final int CATEGORY_PRIMARY;
        public final int[] adaptationSetIndices;
        public final int embeddedCea608TrackGroupIndex;
        public final int embeddedEventMessageTrackGroupIndex;
        public final int eventStreamGroupIndex;
        public final int primaryTrackGroupIndex;
        public final int trackGroupCategory;
        public final int trackType;

        private TrackGroupInfo(int arg1, int arg2, int[] arg3, int arg4, int arg5, int arg6, int arg7) {
            super();
            this.trackType = arg1;
            this.adaptationSetIndices = arg3;
            this.trackGroupCategory = arg2;
            this.primaryTrackGroupIndex = arg4;
            this.embeddedEventMessageTrackGroupIndex = arg5;
            this.embeddedCea608TrackGroupIndex = arg6;
            this.eventStreamGroupIndex = arg7;
        }

        public static TrackGroupInfo embeddedCea608Track(int[] arg9, int arg10) {
            return new TrackGroupInfo(3, 1, arg9, arg10, -1, -1, -1);
        }

        public static TrackGroupInfo embeddedEmsgTrack(int[] arg9, int arg10) {
            return new TrackGroupInfo(4, 1, arg9, arg10, -1, -1, -1);
        }

        public static TrackGroupInfo mpdEventTrack(int arg9) {
            return new TrackGroupInfo(4, 2, null, -1, -1, -1, arg9);
        }

        public static TrackGroupInfo primaryTrack(int arg9, int[] arg10, int arg11, int arg12, int arg13) {
            return new TrackGroupInfo(arg9, 0, arg10, arg11, arg12, arg13, -1);
        }
    }

    private final Allocator allocator;
    private com.google.android.exoplayer2.source.MediaPeriod$Callback callback;
    private final Factory chunkSourceFactory;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final long elapsedRealtimeOffset;
    private EventDispatcher eventDispatcher;
    private EventSampleStream[] eventSampleStreams;
    private List eventStreams;
    final int id;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int minLoadableRetryCount;
    private boolean notifiedReadingStarted;
    private int periodIndex;
    private final PlayerEmsgHandler playerEmsgHandler;
    private ChunkSampleStream[] sampleStreams;
    private final IdentityHashMap trackEmsgHandlerBySampleStream;
    private final TrackGroupInfo[] trackGroupInfos;
    private final TrackGroupArray trackGroups;
    private final TransferListener transferListener;

    public DashMediaPeriod(int arg1, DashManifest arg2, int arg3, Factory arg4, TransferListener arg5, int arg6, EventDispatcher arg7, long arg8, LoaderErrorThrower arg10, Allocator arg11, CompositeSequenceableLoaderFactory arg12, PlayerEmsgCallback arg13) {
        super();
        this.id = arg1;
        this.manifest = arg2;
        this.periodIndex = arg3;
        this.chunkSourceFactory = arg4;
        this.transferListener = arg5;
        this.minLoadableRetryCount = arg6;
        this.eventDispatcher = arg7;
        this.elapsedRealtimeOffset = arg8;
        this.manifestLoaderErrorThrower = arg10;
        this.allocator = arg11;
        this.compositeSequenceableLoaderFactory = arg12;
        this.playerEmsgHandler = new PlayerEmsgHandler(arg2, arg13, arg11);
        this.sampleStreams = DashMediaPeriod.newSampleStreamArray(0);
        this.eventSampleStreams = new EventSampleStream[0];
        this.trackEmsgHandlerBySampleStream = new IdentityHashMap();
        this.compositeSequenceableLoader = arg12.createCompositeSequenceableLoader(this.sampleStreams);
        Period v1 = arg2.getPeriod(arg3);
        this.eventStreams = v1.eventStreams;
        Pair v1_1 = DashMediaPeriod.buildTrackGroups(v1.adaptationSets, this.eventStreams);
        this.trackGroups = v1_1.first;
        this.trackGroupInfos = v1_1.second;
        arg7.mediaPeriodCreated();
    }

    private static void buildManifestEventTrackGroupInfos(List arg6, TrackGroup[] arg7, TrackGroupInfo[] arg8, int arg9) {
        int v1 = arg9;
        arg9 = 0;
        while(arg9 < arg6.size()) {
            arg7[v1] = new TrackGroup(new Format[]{Format.createSampleFormat(arg6.get(arg9).id(), "application/x-emsg", null, -1, null)});
            arg8[v1] = TrackGroupInfo.mpdEventTrack(arg9);
            ++arg9;
            ++v1;
        }
    }

    private static int buildPrimaryAndEmbeddedTrackGroupInfos(List arg14, int[][] arg15, int arg16, boolean[] arg17, boolean[] arg18, TrackGroup[] arg19, TrackGroupInfo[] arg20) {
        int v13;
        int v10;
        int v12;
        int v1 = 0;
        int v2 = arg16;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v2) {
            int[] v5 = arg15[v3];
            ArrayList v6 = new ArrayList();
            int v7 = v5.length;
            int v8;
            for(v8 = 0; v8 < v7; ++v8) {
                ((List)v6).addAll(arg14.get(v5[v8]).representations);
            }

            Format[] v7_1 = new Format[((List)v6).size()];
            for(v8 = 0; v8 < v7_1.length; ++v8) {
                v7_1[v8] = ((List)v6).get(v8).format;
            }

            Object v6_1 = arg14.get(v5[v1]);
            v8 = v4 + 1;
            int v11 = -1;
            if(arg17[v3]) {
                v12 = v8 + 1;
                v10 = v8;
            }
            else {
                v12 = v8;
                v10 = -1;
            }

            if(arg18[v3]) {
                v13 = v12 + 1;
            }
            else {
                v13 = v12;
                v12 = -1;
            }

            arg19[v4] = new TrackGroup(v7_1);
            arg20[v4] = TrackGroupInfo.primaryTrack(((AdaptationSet)v6_1).type, v5, v4, v10, v12);
            if(v10 != v11) {
                StringBuilder v7_2 = new StringBuilder();
                v7_2.append(((AdaptationSet)v6_1).id);
                v7_2.append(":emsg");
                arg19[v10] = new TrackGroup(new Format[]{Format.createSampleFormat(v7_2.toString(), "application/x-emsg", null, v11, null)});
                arg20[v10] = TrackGroupInfo.embeddedEmsgTrack(v5, v4);
            }

            if(v12 != -1) {
                StringBuilder v0 = new StringBuilder();
                v0.append(((AdaptationSet)v6_1).id);
                v0.append(":cea608");
                arg19[v12] = new TrackGroup(new Format[]{Format.createTextSampleFormat(v0.toString(), "application/cea-608", 0, null)});
                arg20[v12] = TrackGroupInfo.embeddedCea608Track(v5, v4);
            }

            ++v3;
            v4 = v13;
            v1 = 0;
        }

        return v4;
    }

    private ChunkSampleStream buildSampleStream(TrackGroupInfo arg27, TrackSelection arg28, long arg29) {
        int v3;
        DashMediaPeriod v12 = this;
        TrackGroupInfo v0 = arg27;
        int[] v2 = new int[2];
        Format[] v1 = new Format[2];
        int v4 = -1;
        boolean v22 = v0.embeddedEventMessageTrackGroupIndex != v4 ? true : false;
        if(v22) {
            v1[0] = v12.trackGroups.get(v0.embeddedEventMessageTrackGroupIndex).getFormat(0);
            v2[0] = 4;
            v3 = 1;
        }
        else {
            v3 = 0;
        }

        boolean v23 = v0.embeddedCea608TrackGroupIndex != v4 ? true : false;
        if(v23) {
            v1[v3] = v12.trackGroups.get(v0.embeddedCea608TrackGroupIndex).getFormat(0);
            v2[v3] = 3;
            ++v3;
        }

        if(v3 < v2.length) {
            Object[] v1_1 = Arrays.copyOf(((Object[])v1), v3);
            v2 = Arrays.copyOf(v2, v3);
        }

        Format[] v4_1 = v1;
        int[] v3_1 = v2;
        PlayerTrackEmsgHandler v1_2 = !v12.manifest.dynamic || !v22 ? null : v12.playerEmsgHandler.newPlayerTrackEmsgHandler();
        PlayerTrackEmsgHandler v14 = v1_2;
        ChunkSampleStream v13 = new ChunkSampleStream(v0.trackType, v3_1, v4_1, v12.chunkSourceFactory.createDashChunkSource(v12.manifestLoaderErrorThrower, v12.manifest, v12.periodIndex, v0.adaptationSetIndices, arg28, v0.trackType, v12.elapsedRealtimeOffset, v22, v23, v1_2, v12.transferListener), this, v12.allocator, arg29, v12.minLoadableRetryCount, v12.eventDispatcher);
        __monitor_enter(this);
        try {
            v12.trackEmsgHandlerBySampleStream.put(v13, v14);
            __monitor_exit(this);
            return v13;
        label_88:
            __monitor_exit(this);
        }
        catch(Throwable v0_1) {
            goto label_88;
        }

        throw v0_1;
    }

    private static Pair buildTrackGroups(List arg9, List arg10) {
        int[][] v1 = DashMediaPeriod.getGroupedAdaptationSetIndices(arg9);
        int v2 = v1.length;
        boolean[] v3 = new boolean[v2];
        boolean[] v4 = new boolean[v2];
        int v0 = DashMediaPeriod.identifyEmbeddedTracks(v2, arg9, v1, v3, v4) + v2 + arg10.size();
        TrackGroup[] v7 = new TrackGroup[v0];
        TrackGroupInfo[] v8 = new TrackGroupInfo[v0];
        DashMediaPeriod.buildManifestEventTrackGroupInfos(arg10, v7, v8, DashMediaPeriod.buildPrimaryAndEmbeddedTrackGroupInfos(arg9, v1, v2, v3, v4, v7, v8));
        return Pair.create(new TrackGroupArray(v7), v8);
    }

    public boolean continueLoading(long arg2) {
        return this.compositeSequenceableLoader.continueLoading(arg2);
    }

    public void discardBuffer(long arg5, boolean arg7) {
        ChunkSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].discardBuffer(arg5, arg7);
        }
    }

    private static Descriptor findAdaptationSetSwitchingProperty(List arg4) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            Object v1 = arg4.get(v0);
            if("urn:mpeg:dash:adaptation-set-switching:2016".equals(((Descriptor)v1).schemeIdUri)) {
                return ((Descriptor)v1);
            }
        }

        return null;
    }

    public long getAdjustedSeekPositionUs(long arg7, SeekParameters arg9) {
        ChunkSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            ChunkSampleStream v3 = v0[v2];
            if(v3.primaryTrackType == 2) {
                return v3.getAdjustedSeekPositionUs(arg7, arg9);
            }
        }

        return arg7;
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    private static int[][] getGroupedAdaptationSetIndices(List arg12) {
        Object[] v3_2;
        int v0 = arg12.size();
        SparseIntArray v1 = new SparseIntArray(v0);
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            v1.put(arg12.get(v3).id, v3);
        }

        int[][] v3_1 = new int[v0][];
        boolean[] v4 = new boolean[v0];
        int v5 = 0;
        int v6 = 0;
        while(v5 < v0) {
            if(v4[v5]) {
            }
            else {
                v4[v5] = true;
                Descriptor v8 = DashMediaPeriod.findAdaptationSetSwitchingProperty(arg12.get(v5).supplementalProperties);
                if(v8 == null) {
                    v3_1[v6] = new int[]{v5};
                    ++v6;
                }
                else {
                    String[] v8_1 = v8.value.split(",");
                    int[] v9 = new int[v8_1.length + 1];
                    v9[0] = v5;
                    int v10 = 0;
                    while(v10 < v8_1.length) {
                        int v11 = v1.get(Integer.parseInt(v8_1[v10]));
                        v4[v11] = true;
                        ++v10;
                        v9[v10] = v11;
                    }

                    v3_1[v6] = v9;
                    ++v6;
                }
            }

            ++v5;
        }

        if(v6 < v0) {
            v3_2 = Arrays.copyOf(((Object[])v3_1), v6);
        }

        return ((int[][])v3_2);
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    private int getPrimaryStreamIndex(int arg5, int[] arg6) {
        arg5 = arg6[arg5];
        int v0 = -1;
        if(arg5 == v0) {
            return v0;
        }

        arg5 = this.trackGroupInfos[arg5].primaryTrackGroupIndex;
        int v1;
        for(v1 = 0; v1 < arg6.length; ++v1) {
            int v2 = arg6[v1];
            if(v2 == arg5 && this.trackGroupInfos[v2].trackGroupCategory == 0) {
                return v1;
            }
        }

        return v0;
    }

    private int[] getStreamIndexToTrackGroupIndex(TrackSelection[] arg5) {
        int[] v0 = new int[arg5.length];
        int v1;
        for(v1 = 0; v1 < arg5.length; ++v1) {
            v0[v1] = arg5[v1] != null ? this.trackGroups.indexOf(arg5[v1].getTrackGroup()) : -1;
        }

        return v0;
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    private static boolean hasCea608Track(List arg7, int[] arg8) {
        int v0 = arg8.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            List v3 = arg7.get(arg8[v2]).accessibilityDescriptors;
            int v4;
            for(v4 = 0; v4 < v3.size(); ++v4) {
                if("urn:scte:dash:cc:cea-608:2015".equals(v3.get(v4).schemeIdUri)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private static boolean hasEventMessageTrack(List arg6, int[] arg7) {
        int v0 = arg7.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            List v3 = arg6.get(arg7[v2]).representations;
            int v4;
            for(v4 = 0; v4 < v3.size(); ++v4) {
                if(!v3.get(v4).inbandEventStreams.isEmpty()) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private static int identifyEmbeddedTracks(int arg4, List arg5, int[][] arg6, boolean[] arg7, boolean[] arg8) {
        int v0 = 0;
        int v1 = 0;
        while(v0 < arg4) {
            if(DashMediaPeriod.hasEventMessageTrack(arg5, arg6[v0])) {
                arg7[v0] = true;
                ++v1;
            }

            if(DashMediaPeriod.hasCea608Track(arg5, arg6[v0])) {
                arg8[v0] = true;
                ++v1;
            }

            ++v0;
        }

        return v1;
    }

    public void maybeThrowPrepareError() {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    private static ChunkSampleStream[] newSampleStreamArray(int arg0) {
        return new ChunkSampleStream[arg0];
    }

    public void onContinueLoadingRequested(SequenceableLoader arg1) {
        this.onContinueLoadingRequested(((ChunkSampleStream)arg1));
    }

    public void onContinueLoadingRequested(ChunkSampleStream arg1) {
        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
    }

    public void onSampleStreamReleased(ChunkSampleStream arg2) {
        __monitor_enter(this);
        try {
            Object v2_1 = this.trackEmsgHandlerBySampleStream.remove(arg2);
            if(v2_1 != null) {
                ((PlayerTrackEmsgHandler)v2_1).release();
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public void prepare(com.google.android.exoplayer2.source.MediaPeriod$Callback arg1, long arg2) {
        this.callback = arg1;
        arg1.onPrepared(((MediaPeriod)this));
    }

    public long readDiscontinuity() {
        if(!this.notifiedReadingStarted) {
            this.eventDispatcher.readingStarted();
            this.notifiedReadingStarted = true;
        }

        return -9223372036854775807L;
    }

    public void reevaluateBuffer(long arg2) {
        this.compositeSequenceableLoader.reevaluateBuffer(arg2);
    }

    public void release() {
        this.playerEmsgHandler.release();
        ChunkSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].release(((ReleaseCallback)this));
        }

        this.callback = null;
        this.eventDispatcher.mediaPeriodReleased();
    }

    private void releaseDisabledStreams(TrackSelection[] arg3, boolean[] arg4, SampleStream[] arg5) {
        int v0;
        for(v0 = 0; v0 < arg3.length; ++v0) {
            if(arg3[v0] == null || !arg4[v0]) {
                if((arg5[v0] instanceof ChunkSampleStream)) {
                    arg5[v0].release(((ReleaseCallback)this));
                }
                else if((arg5[v0] instanceof EmbeddedSampleStream)) {
                    arg5[v0].release();
                }

                arg5[v0] = null;
            }
        }
    }

    private void releaseOrphanEmbeddedStreams(TrackSelection[] arg5, SampleStream[] arg6, int[] arg7) {
        boolean v2_1;
        int v1;
        for(v1 = 0; v1 < arg5.length; ++v1) {
            if(((arg6[v1] instanceof EmptySampleStream)) || ((arg6[v1] instanceof EmbeddedSampleStream))) {
                int v2 = this.getPrimaryStreamIndex(v1, arg7);
                if(v2 == -1) {
                    v2_1 = arg6[v1] instanceof EmptySampleStream;
                }
                else {
                    if(((arg6[v1] instanceof EmbeddedSampleStream)) && arg6[v1].parent == arg6[v2]) {
                        v2_1 = true;
                        goto label_26;
                    }

                    v2_1 = false;
                }

            label_26:
                if(v2_1) {
                    goto label_34;
                }

                if((arg6[v1] instanceof EmbeddedSampleStream)) {
                    arg6[v1].release();
                }

                arg6[v1] = null;
            }

        label_34:
        }
    }

    public long seekToUs(long arg6) {
        ChunkSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v0[v3].seekToUs(arg6);
        }

        EventSampleStream[] v0_1 = this.eventSampleStreams;
        v1 = v0_1.length;
        while(v2 < v1) {
            v0_1[v2].seekToUs(arg6);
            ++v2;
        }

        return arg6;
    }

    private void selectNewStreams(TrackSelection[] arg7, SampleStream[] arg8, boolean[] arg9, long arg10, int[] arg12) {
        int v0 = 0;
        int v1;
        for(v1 = 0; v1 < arg7.length; ++v1) {
            if(arg8[v1] == null && arg7[v1] != null) {
                arg9[v1] = true;
                TrackGroupInfo v2 = this.trackGroupInfos[arg12[v1]];
                if(v2.trackGroupCategory == 0) {
                    arg8[v1] = this.buildSampleStream(v2, arg7[v1], arg10);
                }
                else if(v2.trackGroupCategory == 2) {
                    arg8[v1] = new EventSampleStream(this.eventStreams.get(v2.eventStreamGroupIndex), arg7[v1].getTrackGroup().getFormat(0), this.manifest.dynamic);
                }
            }
        }

        while(v0 < arg7.length) {
            if(arg8[v0] == null && arg7[v0] != null) {
                TrackGroupInfo v9 = this.trackGroupInfos[arg12[v0]];
                if(v9.trackGroupCategory == 1) {
                    v1 = this.getPrimaryStreamIndex(v0, arg12);
                    arg8[v0] = v1 == -1 ? new EmptySampleStream() : arg8[v1].selectEmbeddedTrack(arg10, v9.trackType);
                }
            }

            ++v0;
        }
    }

    public long selectTracks(TrackSelection[] arg8, boolean[] arg9, SampleStream[] arg10, boolean[] arg11, long arg12) {
        int[] v6 = this.getStreamIndexToTrackGroupIndex(arg8);
        this.releaseDisabledStreams(arg8, arg9, arg10);
        this.releaseOrphanEmbeddedStreams(arg8, arg10, v6);
        this.selectNewStreams(arg8, arg10, arg11, arg12, v6);
        ArrayList v8 = new ArrayList();
        ArrayList v9 = new ArrayList();
        int v11 = arg10.length;
        int v0;
        for(v0 = 0; v0 < v11; ++v0) {
            SampleStream v1 = arg10[v0];
            if((v1 instanceof ChunkSampleStream)) {
                v8.add(v1);
            }
            else if((v1 instanceof EventSampleStream)) {
                v9.add(v1);
            }
        }

        this.sampleStreams = DashMediaPeriod.newSampleStreamArray(v8.size());
        v8.toArray(this.sampleStreams);
        this.eventSampleStreams = new EventSampleStream[v9.size()];
        v9.toArray(this.eventSampleStreams);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.sampleStreams);
        return arg12;
    }

    public void updateManifest(DashManifest arg8, int arg9) {
        Object v4;
        this.manifest = arg8;
        int v1 = 0;
        if(this.periodIndex != arg9) {
            this.eventDispatcher = this.eventDispatcher.withParameters(0, this.eventDispatcher.mediaPeriodId.copyWithPeriodIndex(arg9), arg8.getPeriod(arg9).startMs);
        }

        this.periodIndex = arg9;
        this.playerEmsgHandler.updateManifest(arg8);
        if(this.sampleStreams != null) {
            ChunkSampleStream[] v0 = this.sampleStreams;
            int v2 = v0.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v0[v3].getChunkSource().updateManifest(arg8, arg9);
            }

            this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
        }

        this.eventStreams = arg8.getPeriod(arg9).eventStreams;
        EventSampleStream[] v9 = this.eventSampleStreams;
        int v0_1 = v9.length;
        while(v1 < v0_1) {
            EventSampleStream v2_1 = v9[v1];
            Iterator v3_1 = this.eventStreams.iterator();
            do {
                if(v3_1.hasNext()) {
                    v4 = v3_1.next();
                    if(!((EventStream)v4).id().equals(v2_1.eventStreamId())) {
                        continue;
                    }

                    break;
                }

                goto label_46;
            }
            while(true);

            v2_1.updateEventStream(((EventStream)v4), arg8.dynamic);
        label_46:
            ++v1;
        }
    }
}

