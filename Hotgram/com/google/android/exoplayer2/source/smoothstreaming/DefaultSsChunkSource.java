package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.mp4.Track;
import com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest$StreamElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;

public class DefaultSsChunkSource implements SsChunkSource {
    public final class Factory implements com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory {
        private final com.google.android.exoplayer2.upstream.DataSource$Factory dataSourceFactory;

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg1) {
            super();
            this.dataSourceFactory = arg1;
        }

        public SsChunkSource createChunkSource(LoaderErrorThrower arg9, SsManifest arg10, int arg11, TrackSelection arg12, TrackEncryptionBox[] arg13, TransferListener arg14) {
            DataSource v6 = this.dataSourceFactory.createDataSource();
            if(arg14 != null) {
                v6.addTransferListener(arg14);
            }

            return new DefaultSsChunkSource(arg9, arg10, arg11, arg12, v6, arg13);
        }
    }

    private int currentManifestChunkOffset;
    private final DataSource dataSource;
    private final ChunkExtractorWrapper[] extractorWrappers;
    private IOException fatalError;
    private SsManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int streamElementIndex;
    private final TrackSelection trackSelection;

    public DefaultSsChunkSource(LoaderErrorThrower arg25, SsManifest arg26, int arg27, TrackSelection arg28, DataSource arg29, TrackEncryptionBox[] arg30) {
        DefaultSsChunkSource v0 = this;
        SsManifest v1 = arg26;
        TrackSelection v3 = arg28;
        super();
        v0.manifestLoaderErrorThrower = arg25;
        v0.manifest = v1;
        v0.streamElementIndex = arg27;
        v0.trackSelection = v3;
        v0.dataSource = arg29;
        StreamElement v2 = v1.streamElements[arg27];
        v0.extractorWrappers = new ChunkExtractorWrapper[arg28.length()];
        int v5;
        for(v5 = 0; v5 < v0.extractorWrappers.length; ++v5) {
            int v8 = v3.getIndexInTrackGroup(v5);
            Format v6 = v2.formats[v8];
            int v19 = v2.type == 2 ? 4 : 0;
            v0.extractorWrappers[v5] = new ChunkExtractorWrapper(new FragmentedMp4Extractor(3, null, new Track(v8, v2.type, v2.timescale, -9223372036854775807L, v1.durationUs, v6, 0, arg30, v19, null, null), null), v2.type, v6);
        }
    }

    public long getAdjustedSeekPositionUs(long arg10, SeekParameters arg12) {
        StreamElement v0 = this.manifest.streamElements[this.streamElementIndex];
        int v1 = v0.getChunkIndex(arg10);
        long v5 = v0.getStartTimeUs(v1);
        long v7 = v5 >= arg10 || v1 >= v0.chunkCount - 1 ? v5 : v0.getStartTimeUs(v1 + 1);
        return Util.resolveSeekPositionUs(arg10, arg12, v5, v7);
    }

    public final void getNextChunk(long arg30, long arg32, List arg34, ChunkHolder arg35) {
        int v5;
        DefaultSsChunkSource v0 = this;
        long v1 = arg32;
        ChunkHolder v3 = arg35;
        if(v0.fatalError != null) {
            return;
        }

        StreamElement v4 = v0.manifest.streamElements[v0.streamElementIndex];
        if(v4.chunkCount == 0) {
            v3.endOfStream = v0.manifest.isLive ^ 1;
            return;
        }

        if(arg34.isEmpty()) {
            v5 = v4.getChunkIndex(v1);
        }
        else {
            v5 = ((int)(arg34.get(arg34.size() - 1).getNextChunkIndex() - (((long)v0.currentManifestChunkOffset))));
            if(v5 < 0) {
                v0.fatalError = new BehindLiveWindowException();
                return;
            }
        }

        if(v5 >= v4.chunkCount) {
            v3.endOfStream = v0.manifest.isLive ^ 1;
            return;
        }

        v0.trackSelection.updateSelectedTrack(arg30, v1 - arg30, this.resolveTimeToLiveEdgeUs(arg30));
        long v20 = v4.getStartTimeUs(v5);
        long v22 = v20 + v4.getChunkDurationUs(v5);
        if(!arg34.isEmpty()) {
            v1 = -9223372036854775807L;
        }

        long v24 = v1;
        int v19 = v5 + v0.currentManifestChunkOffset;
        int v1_1 = v0.trackSelection.getSelectedIndex();
        v3.chunk = DefaultSsChunkSource.newMediaChunk(v0.trackSelection.getSelectedFormat(), v0.dataSource, v4.buildRequestUri(v0.trackSelection.getIndexInTrackGroup(v1_1), v5), null, v19, v20, v22, v24, v0.trackSelection.getSelectionReason(), v0.trackSelection.getSelectionData(), v0.extractorWrappers[v1_1]);
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

    public void maybeThrowError() {
        if(this.fatalError == null) {
            this.manifestLoaderErrorThrower.maybeThrowError();
            return;
        }

        throw this.fatalError;
    }

    private static MediaChunk newMediaChunk(Format arg20, DataSource arg21, Uri arg22, String arg23, int arg24, long arg25, long arg27, long arg29, int arg31, Object arg32, ChunkExtractorWrapper arg33) {
        return new ContainerMediaChunk(arg21, new DataSpec(arg22, 0, -1, arg23), arg20, arg31, arg32, arg25, arg27, arg29, ((long)arg24), 1, arg25, arg33);
    }

    public void onChunkLoadCompleted(Chunk arg1) {
    }

    public boolean onChunkLoadError(Chunk arg2, boolean arg3, Exception arg4) {
        boolean v2 = !arg3 || !ChunkedTrackBlacklistUtil.maybeBlacklistTrack(this.trackSelection, this.trackSelection.indexOf(arg2.trackFormat), arg4) ? false : true;
        return v2;
    }

    private long resolveTimeToLiveEdgeUs(long arg5) {
        if(!this.manifest.isLive) {
            return -9223372036854775807L;
        }

        StreamElement v0 = this.manifest.streamElements[this.streamElementIndex];
        int v1 = v0.chunkCount - 1;
        return v0.getStartTimeUs(v1) + v0.getChunkDurationUs(v1) - arg5;
    }

    public void updateManifest(SsManifest arg9) {
        StreamElement v0 = this.manifest.streamElements[this.streamElementIndex];
        int v1 = v0.chunkCount;
        StreamElement v2 = arg9.streamElements[this.streamElementIndex];
        if(v1 == 0 || v2.chunkCount == 0) {
        label_25:
            this.currentManifestChunkOffset += v1;
        }
        else {
            int v3 = v1 - 1;
            long v4 = v0.getStartTimeUs(v3) + v0.getChunkDurationUs(v3);
            long v2_1 = v2.getStartTimeUs(0);
            if(v4 <= v2_1) {
                goto label_25;
            }
            else {
                this.currentManifestChunkOffset += v0.getChunkIndex(v2_1);
            }
        }

        this.manifest = arg9;
    }
}

