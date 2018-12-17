package com.google.android.exoplayer2.source.dash;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.TreeMap;

public final class PlayerEmsgHandler implements Handler$Callback {
    final class ManifestExpiryEventInfo {
        public final long eventTimeUs;
        public final long manifestPublishTimeMsInEmsg;

        public ManifestExpiryEventInfo(long arg1, long arg3) {
            super();
            this.eventTimeUs = arg1;
            this.manifestPublishTimeMsInEmsg = arg3;
        }
    }

    public interface PlayerEmsgCallback {
        void onDashLiveMediaPresentationEndSignalEncountered();

        void onDashManifestPublishTimeExpired(long arg1);

        void onDashManifestRefreshRequested();
    }

    public final class PlayerTrackEmsgHandler implements TrackOutput {
        private final MetadataInputBuffer buffer;
        private final FormatHolder formatHolder;
        private final SampleQueue sampleQueue;

        PlayerTrackEmsgHandler(PlayerEmsgHandler arg1, SampleQueue arg2) {
            PlayerEmsgHandler.this = arg1;
            super();
            this.sampleQueue = arg2;
            this.formatHolder = new FormatHolder();
            this.buffer = new MetadataInputBuffer();
        }

        private MetadataInputBuffer dequeueSample() {
            this.buffer.clear();
            if(this.sampleQueue.read(this.formatHolder, this.buffer, false, false, 0) == -4) {
                this.buffer.flip();
                return this.buffer;
            }

            return null;
        }

        public void format(Format arg2) {
            this.sampleQueue.format(arg2);
        }

        public boolean maybeRefreshManifestBeforeLoadingNextChunk(long arg2) {
            return PlayerEmsgHandler.this.maybeRefreshManifestBeforeLoadingNextChunk(arg2);
        }

        public boolean maybeRefreshManifestOnLoadingError(Chunk arg2) {
            return PlayerEmsgHandler.this.maybeRefreshManifestOnLoadingError(arg2);
        }

        public void onChunkLoadCompleted(Chunk arg2) {
            PlayerEmsgHandler.this.onChunkLoadCompleted(arg2);
        }

        private void onManifestExpiredMessageEncountered(long arg2, long arg4) {
            PlayerEmsgHandler.this.handler.sendMessage(PlayerEmsgHandler.this.handler.obtainMessage(2, new ManifestExpiryEventInfo(arg2, arg4)));
        }

        private void onMediaPresentationEndedMessageEncountered() {
            PlayerEmsgHandler.this.handler.sendMessage(PlayerEmsgHandler.this.handler.obtainMessage(1));
        }

        private void parseAndDiscardSamples() {
            while(this.sampleQueue.hasNextSample()) {
                MetadataInputBuffer v0 = this.dequeueSample();
                if(v0 == null) {
                    continue;
                }

                long v1 = v0.timeUs;
                Entry v0_1 = PlayerEmsgHandler.this.decoder.decode(v0).get(0);
                if(!PlayerEmsgHandler.isPlayerEmsgEvent(((EventMessage)v0_1).schemeIdUri, ((EventMessage)v0_1).value)) {
                    continue;
                }

                this.parsePlayerEmsgEvent(v1, ((EventMessage)v0_1));
            }

            this.sampleQueue.discardToRead();
        }

        private void parsePlayerEmsgEvent(long arg6, EventMessage arg8) {
            long v0 = PlayerEmsgHandler.getManifestPublishTimeMsInEmsg(arg8);
            if(v0 == -9223372036854775807L) {
                return;
            }

            if(PlayerEmsgHandler.isMessageSignalingMediaPresentationEnded(arg8)) {
                this.onMediaPresentationEndedMessageEncountered();
            }
            else {
                this.onManifestExpiredMessageEncountered(arg6, v0);
            }
        }

        public void release() {
            this.sampleQueue.reset();
        }

        public int sampleData(ExtractorInput arg2, int arg3, boolean arg4) {
            return this.sampleQueue.sampleData(arg2, arg3, arg4);
        }

        public void sampleData(ParsableByteArray arg2, int arg3) {
            this.sampleQueue.sampleData(arg2, arg3);
        }

        public void sampleMetadata(long arg8, int arg10, int arg11, int arg12, CryptoData arg13) {
            this.sampleQueue.sampleMetadata(arg8, arg10, arg11, arg12, arg13);
            this.parseAndDiscardSamples();
        }
    }

    private static final int EMSG_MANIFEST_EXPIRED = 2;
    private static final int EMSG_MEDIA_PRESENTATION_ENDED = 1;
    private final Allocator allocator;
    private final EventMessageDecoder decoder;
    private boolean dynamicMediaPresentationEnded;
    private long expiredManifestPublishTimeUs;
    private final Handler handler;
    private boolean isWaitingForManifestRefresh;
    private long lastLoadedChunkEndTimeBeforeRefreshUs;
    private long lastLoadedChunkEndTimeUs;
    private DashManifest manifest;
    private final TreeMap manifestPublishTimeToExpiryTimeUs;
    private final PlayerEmsgCallback playerEmsgCallback;
    private boolean released;

    public PlayerEmsgHandler(DashManifest arg1, PlayerEmsgCallback arg2, Allocator arg3) {
        super();
        this.manifest = arg1;
        this.playerEmsgCallback = arg2;
        this.allocator = arg3;
        this.manifestPublishTimeToExpiryTimeUs = new TreeMap();
        this.handler = Util.createHandler(((Handler$Callback)this));
        this.decoder = new EventMessageDecoder();
        this.lastLoadedChunkEndTimeUs = -9223372036854775807L;
        this.lastLoadedChunkEndTimeBeforeRefreshUs = -9223372036854775807L;
    }

    static EventMessageDecoder access$000(PlayerEmsgHandler arg0) {
        return arg0.decoder;
    }

    static long access$100(EventMessage arg2) {
        return PlayerEmsgHandler.getManifestPublishTimeMsInEmsg(arg2);
    }

    static boolean access$200(EventMessage arg0) {
        return PlayerEmsgHandler.isMessageSignalingMediaPresentationEnded(arg0);
    }

    static Handler access$300(PlayerEmsgHandler arg0) {
        return arg0.handler;
    }

    private Map$Entry ceilingExpiryEntryForPublishTime(long arg2) {
        return this.manifestPublishTimeToExpiryTimeUs.ceilingEntry(Long.valueOf(arg2));
    }

    private static long getManifestPublishTimeMsInEmsg(EventMessage arg2) {
        try {
            return Util.parseXsDateTime(new String(arg2.messageData));
        }
        catch(ParserException ) {
            return -9223372036854775807L;
        }
    }

    private void handleManifestExpiredMessage(long arg4, long arg6) {
        Object v0 = this.manifestPublishTimeToExpiryTimeUs.get(Long.valueOf(arg6));
        if(v0 == null || ((Long)v0).longValue() > arg4) {
            this.manifestPublishTimeToExpiryTimeUs.put(Long.valueOf(arg6), Long.valueOf(arg4));
        }
    }

    private void handleMediaPresentationEndedMessageEncountered() {
        this.dynamicMediaPresentationEnded = true;
        this.notifySourceMediaPresentationEnded();
    }

    public boolean handleMessage(Message arg7) {
        if(this.released) {
            return 1;
        }

        switch(arg7.what) {
            case 1: {
                goto label_13;
            }
            case 2: {
                goto label_8;
            }
        }

        return 0;
    label_8:
        this.handleManifestExpiredMessage(arg7.obj.eventTimeUs, arg7.obj.manifestPublishTimeMsInEmsg);
        return 1;
    label_13:
        this.handleMediaPresentationEndedMessageEncountered();
        return 1;
    }

    private static boolean isMessageSignalingMediaPresentationEnded(EventMessage arg5) {
        long v2 = 0;
        boolean v5 = arg5.presentationTimeUs != v2 || arg5.durationMs != v2 ? false : true;
        return v5;
    }

    public static boolean isPlayerEmsgEvent(String arg1, String arg2) {
        boolean v1;
        if("urn:mpeg:dash:event:2012".equals(arg1)) {
            if(!"1".equals(arg2) && !"2".equals(arg2) && !"3".equals(arg2)) {
                goto label_14;
            }

            v1 = true;
        }
        else {
        label_14:
            v1 = false;
        }

        return v1;
    }

    private void maybeNotifyDashManifestRefreshNeeded() {
        if(this.lastLoadedChunkEndTimeBeforeRefreshUs != -9223372036854775807L && this.lastLoadedChunkEndTimeBeforeRefreshUs == this.lastLoadedChunkEndTimeUs) {
            return;
        }

        this.isWaitingForManifestRefresh = true;
        this.lastLoadedChunkEndTimeBeforeRefreshUs = this.lastLoadedChunkEndTimeUs;
        this.playerEmsgCallback.onDashManifestRefreshRequested();
    }

    boolean maybeRefreshManifestBeforeLoadingNextChunk(long arg7) {
        if(!this.manifest.dynamic) {
            return 0;
        }

        boolean v2 = true;
        if(this.isWaitingForManifestRefresh) {
            return 1;
        }

        if(this.dynamicMediaPresentationEnded) {
        }
        else {
            Map$Entry v0 = this.ceilingExpiryEntryForPublishTime(this.manifest.publishTimeMs);
            if(v0 != null && v0.getValue().longValue() < arg7) {
                this.expiredManifestPublishTimeUs = v0.getKey().longValue();
                this.notifyManifestPublishTimeExpired();
                goto label_25;
            }

            v2 = false;
        }

    label_25:
        if(v2) {
            this.maybeNotifyDashManifestRefreshNeeded();
        }

        return v2;
    }

    boolean maybeRefreshManifestOnLoadingError(Chunk arg8) {
        if(!this.manifest.dynamic) {
            return 0;
        }

        if(this.isWaitingForManifestRefresh) {
            return 1;
        }

        int v8 = this.lastLoadedChunkEndTimeUs == -9223372036854775807L || this.lastLoadedChunkEndTimeUs >= arg8.startTimeUs ? 0 : 1;
        if(v8 != 0) {
            this.maybeNotifyDashManifestRefreshNeeded();
            return 1;
        }

        return 0;
    }

    public PlayerTrackEmsgHandler newPlayerTrackEmsgHandler() {
        return new PlayerTrackEmsgHandler(this, new SampleQueue(this.allocator));
    }

    private void notifyManifestPublishTimeExpired() {
        this.playerEmsgCallback.onDashManifestPublishTimeExpired(this.expiredManifestPublishTimeUs);
    }

    private void notifySourceMediaPresentationEnded() {
        this.playerEmsgCallback.onDashLiveMediaPresentationEndSignalEncountered();
    }

    void onChunkLoadCompleted(Chunk arg6) {
        if(this.lastLoadedChunkEndTimeUs != -9223372036854775807L || arg6.endTimeUs > this.lastLoadedChunkEndTimeUs) {
            this.lastLoadedChunkEndTimeUs = arg6.endTimeUs;
        }
    }

    public void release() {
        this.released = true;
        this.handler.removeCallbacksAndMessages(null);
    }

    private void removePreviouslyExpiredManifestPublishTimeValues() {
        Iterator v0 = this.manifestPublishTimeToExpiryTimeUs.entrySet().iterator();
        while(v0.hasNext()) {
            if(v0.next().getKey().longValue() >= this.manifest.publishTimeMs) {
                continue;
            }

            v0.remove();
        }
    }

    public void updateManifest(DashManifest arg3) {
        this.isWaitingForManifestRefresh = false;
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        this.manifest = arg3;
        this.removePreviouslyExpiredManifestPublishTimeValues();
    }
}

