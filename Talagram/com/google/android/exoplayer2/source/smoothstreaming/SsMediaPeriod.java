package com.google.android.exoplayer2.source.smoothstreaming;

import android.util.Base64;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader$Callback;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest$ProtectionElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.util.ArrayList;

final class SsMediaPeriod implements MediaPeriod, Callback {
    private static final int INITIALIZATION_VECTOR_SIZE = 8;
    private final Allocator allocator;
    private com.google.android.exoplayer2.source.MediaPeriod$Callback callback;
    private final Factory chunkSourceFactory;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final EventDispatcher eventDispatcher;
    private SsManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int minLoadableRetryCount;
    private boolean notifiedReadingStarted;
    private ChunkSampleStream[] sampleStreams;
    private final TrackEncryptionBox[] trackEncryptionBoxes;
    private final TrackGroupArray trackGroups;
    private final TransferListener transferListener;

    public SsMediaPeriod(SsManifest arg16, Factory arg17, TransferListener arg18, CompositeSequenceableLoaderFactory arg19, int arg20, EventDispatcher arg21, LoaderErrorThrower arg22, Allocator arg23) {
        SsMediaPeriod v0 = this;
        SsManifest v1 = arg16;
        CompositeSequenceableLoaderFactory v2 = arg19;
        super();
        v0.chunkSourceFactory = arg17;
        v0.transferListener = arg18;
        v0.manifestLoaderErrorThrower = arg22;
        v0.minLoadableRetryCount = arg20;
        v0.eventDispatcher = arg21;
        v0.allocator = arg23;
        v0.compositeSequenceableLoaderFactory = v2;
        v0.trackGroups = SsMediaPeriod.buildTrackGroups(arg16);
        ProtectionElement v4 = v1.protectionElement;
        TrackEncryptionBox[] v4_1 = v4 != null ? new TrackEncryptionBox[]{new TrackEncryptionBox(true, null, 8, SsMediaPeriod.getProtectionElementKeyId(v4.data), 0, 0, null)} : null;
        v0.trackEncryptionBoxes = v4_1;
        v0.manifest = v1;
        v0.sampleStreams = SsMediaPeriod.newSampleStreamArray(0);
        v0.compositeSequenceableLoader = v2.createCompositeSequenceableLoader(v0.sampleStreams);
        arg21.mediaPeriodCreated();
    }

    private ChunkSampleStream buildSampleStream(TrackSelection arg14, long arg15) {
        int v0 = this.trackGroups.indexOf(arg14.getTrackGroup());
        return new ChunkSampleStream(this.manifest.streamElements[v0].type, null, null, this.chunkSourceFactory.createChunkSource(this.manifestLoaderErrorThrower, this.manifest, v0, arg14, this.trackEncryptionBoxes, this.transferListener), this, this.allocator, arg15, this.minLoadableRetryCount, this.eventDispatcher);
    }

    private static TrackGroupArray buildTrackGroups(SsManifest arg4) {
        TrackGroup[] v0 = new TrackGroup[arg4.streamElements.length];
        int v1;
        for(v1 = 0; v1 < arg4.streamElements.length; ++v1) {
            v0[v1] = new TrackGroup(arg4.streamElements[v1].formats);
        }

        return new TrackGroupArray(v0);
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

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    private static byte[] getProtectionElementKeyId(byte[] arg4) {
        StringBuilder v0 = new StringBuilder();
        int v2;
        for(v2 = 0; v2 < arg4.length; v2 += 2) {
            v0.append(((char)arg4[v2]));
        }

        String v4 = v0.toString();
        arg4 = Base64.decode(v4.substring(v4.indexOf("<KID>") + 5, v4.indexOf("</KID>")), 0);
        SsMediaPeriod.swap(arg4, 0, 3);
        SsMediaPeriod.swap(arg4, 1, 2);
        SsMediaPeriod.swap(arg4, 4, 5);
        SsMediaPeriod.swap(arg4, 6, 7);
        return arg4;
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
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
        ChunkSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].release();
        }

        this.callback = null;
        this.eventDispatcher.mediaPeriodReleased();
    }

    public long seekToUs(long arg5) {
        ChunkSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].seekToUs(arg5);
        }

        return arg5;
    }

    public long selectTracks(TrackSelection[] arg5, boolean[] arg6, SampleStream[] arg7, boolean[] arg8, long arg9) {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < arg5.length; ++v1) {
            if(arg7[v1] != null) {
                SampleStream v2 = arg7[v1];
                if(arg5[v1] != null) {
                    if(!arg6[v1]) {
                    }
                    else {
                        v0.add(v2);
                        goto label_18;
                    }
                }

                ((ChunkSampleStream)v2).release();
                arg7[v1] = null;
            }

        label_18:
            if(arg7[v1] == null && arg5[v1] != null) {
                ChunkSampleStream v2_1 = this.buildSampleStream(arg5[v1], arg9);
                v0.add(v2_1);
                arg7[v1] = ((SampleStream)v2_1);
                arg8[v1] = true;
            }
        }

        this.sampleStreams = SsMediaPeriod.newSampleStreamArray(v0.size());
        v0.toArray(this.sampleStreams);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.sampleStreams);
        return arg9;
    }

    private static void swap(byte[] arg2, int arg3, int arg4) {
        byte v0 = arg2[arg3];
        arg2[arg3] = arg2[arg4];
        arg2[arg4] = v0;
    }

    public void updateManifest(SsManifest arg5) {
        this.manifest = arg5;
        ChunkSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].getChunkSource().updateManifest(arg5);
        }

        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
    }
}

