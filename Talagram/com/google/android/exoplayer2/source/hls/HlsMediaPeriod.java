package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker$PlaylistEventListener;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

public final class HlsMediaPeriod implements MediaPeriod, Callback, PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private com.google.android.exoplayer2.source.MediaPeriod$Callback callback;
    private final LoadErrorHandlingPolicy chunkLoadErrorHandlingPolicy;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers;
    private final EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final TransferListener mediaTransferListener;
    private final int minLoadableRetryCount;
    private boolean notifiedReadingStarted;
    private int pendingPrepareCount;
    private final HlsPlaylistTracker playlistTracker;
    private HlsSampleStreamWrapper[] sampleStreamWrappers;
    private final IdentityHashMap streamWrapperIndices;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private TrackGroupArray trackGroups;

    public HlsMediaPeriod(HlsExtractorFactory arg1, HlsPlaylistTracker arg2, HlsDataSourceFactory arg3, TransferListener arg4, LoadErrorHandlingPolicy arg5, int arg6, EventDispatcher arg7, Allocator arg8, CompositeSequenceableLoaderFactory arg9, boolean arg10) {
        super();
        this.extractorFactory = arg1;
        this.playlistTracker = arg2;
        this.dataSourceFactory = arg3;
        this.mediaTransferListener = arg4;
        this.chunkLoadErrorHandlingPolicy = arg5;
        this.minLoadableRetryCount = arg6;
        this.eventDispatcher = arg7;
        this.allocator = arg8;
        this.compositeSequenceableLoaderFactory = arg9;
        this.allowChunklessPreparation = arg10;
        this.compositeSequenceableLoader = arg9.createCompositeSequenceableLoader(new SequenceableLoader[0]);
        this.streamWrapperIndices = new IdentityHashMap();
        this.timestampAdjusterProvider = new TimestampAdjusterProvider();
        this.sampleStreamWrappers = new HlsSampleStreamWrapper[0];
        this.enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
        arg7.mediaPeriodCreated();
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMasterPlaylist arg16, long arg17) {
        int v4_1;
        Format[] v1_2;
        ArrayList v3_1;
        int v2_1;
        ArrayList v12;
        int v10;
        HlsMediaPeriod v7 = this;
        HlsMasterPlaylist v8 = arg16;
        ArrayList v0 = new ArrayList(v8.variants);
        ArrayList v1 = new ArrayList();
        ArrayList v2 = new ArrayList();
        int v3;
        for(v3 = 0; true; ++v3) {
            v10 = 2;
            if(v3 >= ((List)v0).size()) {
                break;
            }

            Object v4 = ((List)v0).get(v3);
            Format v5 = ((HlsUrl)v4).format;
            if(v5.height > 0 || Util.getCodecsOfType(v5.codecs, v10) != null) {
                v1.add(v4);
            }
            else if(Util.getCodecsOfType(v5.codecs, 1) != null) {
                v2.add(v4);
            }
        }

        if(!v1.isEmpty()) {
            v12 = v1;
        }
        else {
            if(v2.size() < ((List)v0).size()) {
                ((List)v0).removeAll(((Collection)v2));
            }

            v12 = v0;
        }

        Assertions.checkArgument(((List)v12).isEmpty() ^ 1);
        Object[] v13 = ((List)v12).toArray(new HlsUrl[0]);
        String v14 = v13[0].format.codecs;
        HlsSampleStreamWrapper v0_1 = this.buildSampleStreamWrapper(0, v13, v8.muxedAudioFormat, v8.muxedCaptionFormats, arg17);
        v7.sampleStreamWrappers[0] = v0_1;
        if(!v7.allowChunklessPreparation || v14 == null) {
            v0_1.setIsTimestampMaster(true);
            v0_1.continuePreparing();
            return;
        label_120:
            while(v2_1 < v1_2.length) {
                v1_2[v2_1] = HlsMediaPeriod.deriveAudioFormat(v13[v2_1].format, v8.muxedAudioFormat, true);
                ++v2_1;
            }

            ((List)v3_1).add(new TrackGroup(v1_2));
            goto label_132;
        label_77:
            while(v4_1 < v1_2.length) {
                v1_2[v4_1] = HlsMediaPeriod.deriveVideoFormat(v13[v4_1].format);
                ++v4_1;
            }

            ((List)v3_1).add(new TrackGroup(v1_2));
            if(v2_1 != 0 && (v8.muxedAudioFormat != null || (v8.audios.isEmpty()))) {
                ((List)v3_1).add(new TrackGroup(new Format[]{HlsMediaPeriod.deriveAudioFormat(v13[0].format, v8.muxedAudioFormat, false)}));
            }

            List v1_4 = v8.muxedCaptionFormats;
            if(v1_4 != null) {
                for(v2_1 = 0; v2_1 < v1_4.size(); ++v2_1) {
                    ((List)v3_1).add(new TrackGroup(new Format[]{v1_4.get(v2_1)}));
                }
            }

        label_132:
            TrackGroup v1_5 = new TrackGroup(new Format[]{Format.createSampleFormat("ID3", "application/id3", null, -1, null)});
            ((List)v3_1).add(v1_5);
            v0_1.prepareWithMasterPlaylistInfo(new TrackGroupArray(((List)v3_1).toArray(new TrackGroup[0])), 0, new TrackGroupArray(new TrackGroup[]{v1_5}));
        }
        else {
            int v1_1 = Util.getCodecsOfType(v14, v10) != null ? 1 : 0;
            v2_1 = Util.getCodecsOfType(v14, 1) != null ? 1 : 0;
            v3_1 = new ArrayList();
            if(v1_1 != 0) {
                v1_2 = new Format[((List)v12).size()];
                v4_1 = 0;
                goto label_77;
            }
            else if(v2_1 != 0) {
                v1_2 = new Format[((List)v12).size()];
                v2_1 = 0;
                goto label_120;
            }
            else {
                StringBuilder v1_3 = new StringBuilder();
                v1_3.append("Unexpected codecs attribute: ");
                v1_3.append(v14);
                throw new IllegalArgumentException(v1_3.toString());
            }

            goto label_132;
        }
    }

    private void buildAndPrepareSampleStreamWrappers(long arg17) {
        HlsSampleStreamWrapper v0_1;
        Object v13_1;
        HlsMediaPeriod v7 = this;
        HlsMasterPlaylist v0 = v7.playlistTracker.getMasterPlaylist();
        List v8 = v0.audios;
        List v9 = v0.subtitles;
        int v1 = v8.size() + 1 + v9.size();
        v7.sampleStreamWrappers = new HlsSampleStreamWrapper[v1];
        v7.pendingPrepareCount = v1;
        v7.buildAndPrepareMainSampleStreamWrapper(v0, arg17);
        int v13 = 0;
        int v14 = 0;
        int v15 = 1;
        while(v14 < v8.size()) {
            Object v5 = v8.get(v14);
            HlsUrl[] v2 = new HlsUrl[1];
            v2[v13] = v5;
            v13_1 = v5;
            v0_1 = this.buildSampleStreamWrapper(1, v2, null, Collections.emptyList(), arg17);
            int v2_1 = v15 + 1;
            v7.sampleStreamWrappers[v15] = v0_1;
            Format v1_1 = ((HlsUrl)v13_1).format;
            if(!v7.allowChunklessPreparation || v1_1.codecs == null) {
                v0_1.continuePreparing();
            }
            else {
                v0_1.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup[]{new TrackGroup(new Format[]{((HlsUrl)v13_1).format})}), 0, TrackGroupArray.EMPTY);
            }

            ++v14;
            v15 = v2_1;
            v13 = 0;
        }

        int v8_1 = 0;
        while(v8_1 < v9.size()) {
            v13_1 = v9.get(v8_1);
            v0_1 = this.buildSampleStreamWrapper(3, new HlsUrl[]{v13_1}, null, Collections.emptyList(), arg17);
            v7.sampleStreamWrappers[v15] = v0_1;
            v0_1.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup[]{new TrackGroup(new Format[]{((HlsUrl)v13_1).format})}), 0, TrackGroupArray.EMPTY);
            ++v8_1;
            ++v15;
        }

        v7.enabledSampleStreamWrappers = v7.sampleStreamWrappers;
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(int arg15, HlsUrl[] arg16, Format arg17, List arg18, long arg19) {
        return new HlsSampleStreamWrapper(arg15, this, new HlsChunkSource(this.extractorFactory, this.playlistTracker, arg16, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, arg18), this.allocator, arg19, arg17, this.chunkLoadErrorHandlingPolicy, this.minLoadableRetryCount, this.eventDispatcher);
    }

    public boolean continueLoading(long arg4) {
        if(this.trackGroups == null) {
            HlsSampleStreamWrapper[] v4 = this.sampleStreamWrappers;
            int v5 = v4.length;
            int v1;
            for(v1 = 0; v1 < v5; ++v1) {
                v4[v1].continuePreparing();
            }

            return 0;
        }

        return this.compositeSequenceableLoader.continueLoading(arg4);
    }

    private static Format deriveAudioFormat(Format arg17, Format arg18, boolean arg19) {
        String v16;
        int v15;
        int v12;
        String v10;
        String v7;
        int v5;
        Format v0 = arg17;
        Format v1 = arg18;
        String v2 = null;
        if(v1 != null) {
            v2 = v1.codecs;
            int v4 = v1.channelCount;
            v5 = v1.selectionFlags;
            String v6 = v1.language;
            v7 = v1.label;
            v10 = v2;
            v12 = v4;
            v15 = v5;
            v16 = v6;
        }
        else {
            String v1_1 = Util.getCodecsOfType(v0.codecs, 1);
            if(arg19) {
                int v2_1 = v0.channelCount;
                v5 = v0.selectionFlags;
                v10 = v1_1;
                v12 = v2_1;
                v16 = v0.label;
                v15 = v5;
                v7 = v0.label;
            }
            else {
                v10 = v1_1;
                v7 = v2;
                v16 = v7;
                v12 = -1;
                v15 = 0;
            }
        }

        String v9 = MimeTypes.getMediaMimeType(v10);
        int v11 = arg19 ? v0.bitrate : -1;
        return Format.createAudioContainerFormat(v0.id, v7, v0.containerMimeType, v9, v10, v11, v12, -1, null, v15, v16);
    }

    private static Format deriveVideoFormat(Format arg13) {
        String v6 = Util.getCodecsOfType(arg13.codecs, 2);
        return Format.createVideoContainerFormat(arg13.id, arg13.label, arg13.containerMimeType, MimeTypes.getMediaMimeType(v6), v6, arg13.bitrate, arg13.width, arg13.height, arg13.frameRate, null, arg13.selectionFlags);
    }

    public void discardBuffer(long arg5, boolean arg7) {
        HlsSampleStreamWrapper[] v0 = this.enabledSampleStreamWrappers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].discardBuffer(arg5, arg7);
        }
    }

    public long getAdjustedSeekPositionUs(long arg1, SeekParameters arg3) {
        return arg1;
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public void maybeThrowPrepareError() {
        HlsSampleStreamWrapper[] v0 = this.sampleStreamWrappers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].maybeThrowPrepareError();
        }
    }

    public void onContinueLoadingRequested(SequenceableLoader arg1) {
        this.onContinueLoadingRequested(((HlsSampleStreamWrapper)arg1));
    }

    public void onContinueLoadingRequested(HlsSampleStreamWrapper arg1) {
        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
    }

    public void onPlaylistChanged() {
        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
    }

    public boolean onPlaylistError(HlsUrl arg6, boolean arg7) {
        HlsSampleStreamWrapper[] v0 = this.sampleStreamWrappers;
        int v1 = v0.length;
        int v2 = 1;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2 &= v0[v3].onPlaylistError(arg6, arg7);
        }

        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
        return ((boolean)v2);
    }

    public void onPlaylistRefreshRequired(HlsUrl arg2) {
        this.playlistTracker.refreshPlaylist(arg2);
    }

    public void onPrepared() {
        int v0 = this.pendingPrepareCount - 1;
        this.pendingPrepareCount = v0;
        if(v0 > 0) {
            return;
        }

        HlsSampleStreamWrapper[] v0_1 = this.sampleStreamWrappers;
        int v1 = v0_1.length;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v1) {
            v4 += v0_1[v3].getTrackGroups().length;
            ++v3;
        }

        TrackGroup[] v0_2 = new TrackGroup[v4];
        HlsSampleStreamWrapper[] v1_1 = this.sampleStreamWrappers;
        v3 = v1_1.length;
        v4 = 0;
        int v5;
        for(v5 = 0; v4 < v3; v5 = v8) {
            HlsSampleStreamWrapper v6 = v1_1[v4];
            int v7 = v6.getTrackGroups().length;
            int v8 = v5;
            v5 = 0;
            while(v5 < v7) {
                v0_2[v8] = v6.getTrackGroups().get(v5);
                ++v5;
                ++v8;
            }

            ++v4;
        }

        this.trackGroups = new TrackGroupArray(v0_2);
        this.callback.onPrepared(((MediaPeriod)this));
    }

    public void prepare(com.google.android.exoplayer2.source.MediaPeriod$Callback arg1, long arg2) {
        this.callback = arg1;
        this.playlistTracker.addListener(((PlaylistEventListener)this));
        this.buildAndPrepareSampleStreamWrappers(arg2);
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
        this.playlistTracker.removeListener(((PlaylistEventListener)this));
        HlsSampleStreamWrapper[] v0 = this.sampleStreamWrappers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].release();
        }

        this.callback = null;
        this.eventDispatcher.mediaPeriodReleased();
    }

    public long seekToUs(long arg4) {
        if(this.enabledSampleStreamWrappers.length > 0) {
            boolean v0 = this.enabledSampleStreamWrappers[0].seekToUs(arg4, false);
            int v1;
            for(v1 = 1; v1 < this.enabledSampleStreamWrappers.length; ++v1) {
                this.enabledSampleStreamWrappers[v1].seekToUs(arg4, v0);
            }

            if(v0) {
                this.timestampAdjusterProvider.reset();
            }
        }

        return arg4;
    }

    public long selectTracks(TrackSelection[] arg21, boolean[] arg22, SampleStream[] arg23, boolean[] arg24, long arg25) {
        int v9;
        int v8;
        HlsMediaPeriod v0 = this;
        TrackSelection[] v1 = arg21;
        SampleStream[] v2 = arg23;
        int[] v3 = new int[v1.length];
        int[] v4 = new int[v1.length];
        int v6;
        for(v6 = 0; v6 < v1.length; ++v6) {
            v8 = -1;
            int v7 = v2[v6] == null ? -1 : v0.streamWrapperIndices.get(v2[v6]).intValue();
            v3[v6] = v7;
            v4[v6] = v8;
            if(v1[v6] != null) {
                TrackGroup v7_1 = v1[v6].getTrackGroup();
                v9 = 0;
                while(v9 < v0.sampleStreamWrappers.length) {
                    if(v0.sampleStreamWrappers[v9].getTrackGroups().indexOf(v7_1) != v8) {
                        v4[v6] = v9;
                    }
                    else {
                        ++v9;
                        continue;
                    }

                    break;
                }
            }
        }

        v0.streamWrapperIndices.clear();
        SampleStream[] v6_1 = new SampleStream[v1.length];
        SampleStream[] v7_2 = new SampleStream[v1.length];
        TrackSelection[] v15 = new TrackSelection[v1.length];
        HlsSampleStreamWrapper[] v13 = new HlsSampleStreamWrapper[v0.sampleStreamWrappers.length];
        int v12 = 0;
        int v14 = 0;
        boolean v16 = false;
        while(v14 < v0.sampleStreamWrappers.length) {
            for(v8 = 0; v8 < v1.length; ++v8) {
                TrackSelection v10 = null;
                SampleStream v9_1 = v3[v8] == v14 ? v2[v8] : ((SampleStream)v10);
                v7_2[v8] = v9_1;
                if(v4[v8] == v14) {
                    v10 = v1[v8];
                }

                v15[v8] = v10;
            }

            HlsSampleStreamWrapper v8_1 = v0.sampleStreamWrappers[v14];
            HlsSampleStreamWrapper v5 = v0.sampleStreamWrappers[v14];
            int v17 = v12;
            HlsSampleStreamWrapper[] v18 = v13;
            int v2_1 = v14;
            TrackSelection[] v19 = v15;
            boolean v8_2 = v8_1.selectTracks(v15, arg22, v7_2, arg24, arg25, v16);
            v9 = 0;
            int v10_1 = 0;
            while(true) {
                boolean v12_1 = true;
                if(v9 >= v1.length) {
                    break;
                }

                if(v4[v9] == v2_1) {
                    boolean v10_2 = v7_2[v9] != null ? true : false;
                    Assertions.checkState(v10_2);
                    v6_1[v9] = v7_2[v9];
                    v0.streamWrapperIndices.put(v7_2[v9], Integer.valueOf(v2_1));
                    v10_1 = 1;
                }
                else {
                    if(v3[v9] != v2_1) {
                        goto label_117;
                    }

                    if(v7_2[v9] == null) {
                    }
                    else {
                        v12_1 = false;
                    }

                    Assertions.checkState(v12_1);
                }

            label_117:
                ++v9;
            }

            if(v10_1 != 0) {
                v18[v17] = v5;
                v9 = v17 + 1;
                if(v17 == 0) {
                    v5.setIsTimestampMaster(true);
                    if(!v8_2 && v0.enabledSampleStreamWrappers.length != 0) {
                        if(v5 != v0.enabledSampleStreamWrappers[0]) {
                        }
                        else {
                            goto label_141;
                        }
                    }

                    v0.timestampAdjusterProvider.reset();
                    v12 = v9;
                    v16 = true;
                    goto label_145;
                }
                else {
                    v5.setIsTimestampMaster(false);
                }

            label_141:
                v12 = v9;
            }
            else {
                v12 = v17;
            }

        label_145:
            v14 = v2_1 + 1;
            v13 = v18;
            v15 = v19;
            v2 = arg23;
        }

        System.arraycopy(v6_1, 0, arg23, 0, v6_1.length);
        v0.enabledSampleStreamWrappers = Arrays.copyOf(v13, v12);
        v0.compositeSequenceableLoader = v0.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(v0.enabledSampleStreamWrappers);
        return arg25;
    }
}

