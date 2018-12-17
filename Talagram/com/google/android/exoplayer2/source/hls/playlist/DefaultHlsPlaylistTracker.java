package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader$Callback;
import com.google.android.exoplayer2.upstream.Loader$LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public final class DefaultHlsPlaylistTracker implements HlsPlaylistTracker, Callback {
    final class MediaPlaylistBundle implements Callback, Runnable {
        private long blacklistUntilMs;
        private long earliestNextLoadTimeMs;
        private long lastSnapshotChangeMs;
        private long lastSnapshotLoadMs;
        private boolean loadPending;
        private final ParsingLoadable mediaPlaylistLoadable;
        private final Loader mediaPlaylistLoader;
        private IOException playlistError;
        private HlsMediaPlaylist playlistSnapshot;
        private final HlsUrl playlistUrl;

        public MediaPlaylistBundle(DefaultHlsPlaylistTracker arg5, HlsUrl arg6) {
            DefaultHlsPlaylistTracker.this = arg5;
            super();
            this.playlistUrl = arg6;
            this.mediaPlaylistLoader = new Loader("DefaultHlsPlaylistTracker:MediaPlaylist");
            this.mediaPlaylistLoadable = new ParsingLoadable(arg5.dataSourceFactory.createDataSource(4), UriUtil.resolveToUri(arg5.masterPlaylist.baseUri, arg6.url), 4, arg5.playlistParser);
        }

        static void access$000(MediaPlaylistBundle arg0, HlsMediaPlaylist arg1) {
            arg0.processLoadedPlaylist(arg1);
        }

        static long access$100(MediaPlaylistBundle arg2) {
            return arg2.blacklistUntilMs;
        }

        static HlsUrl access$200(MediaPlaylistBundle arg0) {
            return arg0.playlistUrl;
        }

        private boolean blacklistPlaylist() {
            this.blacklistUntilMs = SystemClock.elapsedRealtime() + 60000;
            boolean v0 = DefaultHlsPlaylistTracker.this.primaryHlsUrl != this.playlistUrl || (DefaultHlsPlaylistTracker.this.maybeSelectNewPrimaryUrl()) ? false : true;
            return v0;
        }

        public HlsMediaPlaylist getPlaylistSnapshot() {
            return this.playlistSnapshot;
        }

        public boolean isSnapshotValid() {
            boolean v1 = false;
            if(this.playlistSnapshot == null) {
                return 0;
            }

            long v2 = SystemClock.elapsedRealtime();
            long v4 = Math.max(30000, C.usToMs(this.playlistSnapshot.durationUs));
            if((this.playlistSnapshot.hasEndTag) || this.playlistSnapshot.playlistType == 2 || this.playlistSnapshot.playlistType == 1 || this.lastSnapshotLoadMs + v4 > v2) {
                v1 = true;
            }

            return v1;
        }

        public void loadPlaylist() {
            this.blacklistUntilMs = 0;
            if(!this.loadPending) {
                if(this.mediaPlaylistLoader.isLoading()) {
                }
                else {
                    long v0 = SystemClock.elapsedRealtime();
                    if(v0 < this.earliestNextLoadTimeMs) {
                        this.loadPending = true;
                        DefaultHlsPlaylistTracker.this.playlistRefreshHandler.postDelayed(((Runnable)this), this.earliestNextLoadTimeMs - v0);
                    }
                    else {
                        this.loadPlaylistImmediately();
                    }
                }
            }
        }

        private void loadPlaylistImmediately() {
            DefaultHlsPlaylistTracker.this.eventDispatcher.loadStarted(this.mediaPlaylistLoadable.dataSpec, this.mediaPlaylistLoadable.dataSpec.uri, this.mediaPlaylistLoadable.type, this.mediaPlaylistLoader.startLoading(this.mediaPlaylistLoadable, ((Callback)this), DefaultHlsPlaylistTracker.this.minRetryCount));
        }

        public void maybeThrowPlaylistRefreshError() {
            this.mediaPlaylistLoader.maybeThrowError();
            if(this.playlistError == null) {
                return;
            }

            throw this.playlistError;
        }

        public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
            this.onLoadCanceled(((ParsingLoadable)arg1), arg2, arg4, arg6);
        }

        public void onLoadCanceled(ParsingLoadable arg13, long arg14, long arg16, boolean arg18) {
            DefaultHlsPlaylistTracker.this.eventDispatcher.loadCanceled(arg13.dataSpec, arg13.getUri(), 4, arg14, arg16, arg13.bytesLoaded());
        }

        public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
            this.onLoadCompleted(((ParsingLoadable)arg1), arg2, arg4);
        }

        public void onLoadCompleted(ParsingLoadable arg13, long arg14, long arg16) {
            MediaPlaylistBundle v0 = this;
            Object v1 = arg13.getResult();
            if((v1 instanceof HlsMediaPlaylist)) {
                this.processLoadedPlaylist(((HlsMediaPlaylist)v1));
                v0.this$0.eventDispatcher.loadCompleted(arg13.dataSpec, arg13.getUri(), 4, arg14, arg16, arg13.bytesLoaded());
            }
            else {
                v0.playlistError = new ParserException("Loaded playlist has unexpected type.");
            }
        }

        public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
            return this.onLoadError(((ParsingLoadable)arg1), arg2, arg4, arg6, arg7);
        }

        public LoadErrorAction onLoadError(ParsingLoadable arg17, long arg18, long arg20, IOException arg22, int arg23) {
            // Method was not decompiled
        }

        private void processLoadedPlaylist(HlsMediaPlaylist arg10) {
            arg10 = arg10.copyWithMasterPlaylistInfo(DefaultHlsPlaylistTracker.this.masterPlaylist);
            HlsMediaPlaylist v0 = this.playlistSnapshot;
            long v1 = SystemClock.elapsedRealtime();
            this.lastSnapshotLoadMs = v1;
            this.playlistSnapshot = DefaultHlsPlaylistTracker.this.getLatestPlaylistSnapshot(v0, arg10);
            if(this.playlistSnapshot != v0) {
                this.playlistError = null;
                this.lastSnapshotChangeMs = v1;
                DefaultHlsPlaylistTracker.this.onPlaylistUpdated(this.playlistUrl, this.playlistSnapshot);
            }
            else if(!this.playlistSnapshot.hasEndTag) {
                if(arg10.mediaSequence + (((long)arg10.segments.size())) < this.playlistSnapshot.mediaSequence) {
                    this.playlistError = new PlaylistResetException(this.playlistUrl.url);
                    DefaultHlsPlaylistTracker.this.notifyPlaylistError(this.playlistUrl, false);
                }
                else {
                    double v3 = ((double)(v1 - this.lastSnapshotChangeMs));
                    double v5 = ((double)C.usToMs(this.playlistSnapshot.targetDurationUs));
                    Double.isNaN(v5);
                    if(v3 > v5 * 3.5) {
                        this.playlistError = new PlaylistStuckException(this.playlistUrl.url);
                        DefaultHlsPlaylistTracker.this.notifyPlaylistError(this.playlistUrl, true);
                        this.blacklistPlaylist();
                    }
                }
            }

            long v3_1 = this.playlistSnapshot != v0 ? this.playlistSnapshot.targetDurationUs : this.playlistSnapshot.targetDurationUs / 2;
            this.earliestNextLoadTimeMs = v1 + C.usToMs(v3_1);
            if(this.playlistUrl == DefaultHlsPlaylistTracker.this.primaryHlsUrl && !this.playlistSnapshot.hasEndTag) {
                this.loadPlaylist();
            }
        }

        public void release() {
            this.mediaPlaylistLoader.release();
        }

        public void run() {
            this.loadPending = false;
            this.loadPlaylistImmediately();
        }
    }

    private static final double PLAYLIST_STUCK_TARGET_DURATION_COEFFICIENT = 0;
    private final HlsDataSourceFactory dataSourceFactory;
    private EventDispatcher eventDispatcher;
    private Loader initialPlaylistLoader;
    private long initialStartTimeUs;
    private boolean isLive;
    private final List listeners;
    private HlsMasterPlaylist masterPlaylist;
    private final int minRetryCount;
    private final IdentityHashMap playlistBundles;
    private final LoadErrorHandlingPolicy playlistLoadErrorHandlingPolicy;
    private final Parser playlistParser;
    private Handler playlistRefreshHandler;
    private HlsUrl primaryHlsUrl;
    private PrimaryPlaylistListener primaryPlaylistListener;
    private HlsMediaPlaylist primaryUrlSnapshot;

    public DefaultHlsPlaylistTracker(HlsDataSourceFactory arg1, LoadErrorHandlingPolicy arg2, int arg3, Parser arg4) {
        super();
        this.dataSourceFactory = arg1;
        this.minRetryCount = arg3;
        this.playlistParser = arg4;
        this.playlistLoadErrorHandlingPolicy = arg2;
        this.listeners = new ArrayList();
        this.playlistBundles = new IdentityHashMap();
        this.initialStartTimeUs = -9223372036854775807L;
    }

    static int access$1000(DefaultHlsPlaylistTracker arg0) {
        return arg0.minRetryCount;
    }

    static HlsMediaPlaylist access$1100(DefaultHlsPlaylistTracker arg0, HlsMediaPlaylist arg1, HlsMediaPlaylist arg2) {
        return arg0.getLatestPlaylistSnapshot(arg1, arg2);
    }

    static void access$1200(DefaultHlsPlaylistTracker arg0, HlsUrl arg1, HlsMediaPlaylist arg2) {
        arg0.onPlaylistUpdated(arg1, arg2);
    }

    static HlsUrl access$1300(DefaultHlsPlaylistTracker arg0) {
        return arg0.primaryHlsUrl;
    }

    static boolean access$1400(DefaultHlsPlaylistTracker arg0) {
        return arg0.maybeSelectNewPrimaryUrl();
    }

    static HlsDataSourceFactory access$300(DefaultHlsPlaylistTracker arg0) {
        return arg0.dataSourceFactory;
    }

    static HlsMasterPlaylist access$400(DefaultHlsPlaylistTracker arg0) {
        return arg0.masterPlaylist;
    }

    static Parser access$500(DefaultHlsPlaylistTracker arg0) {
        return arg0.playlistParser;
    }

    static Handler access$600(DefaultHlsPlaylistTracker arg0) {
        return arg0.playlistRefreshHandler;
    }

    static EventDispatcher access$700(DefaultHlsPlaylistTracker arg0) {
        return arg0.eventDispatcher;
    }

    static LoadErrorHandlingPolicy access$800(DefaultHlsPlaylistTracker arg0) {
        return arg0.playlistLoadErrorHandlingPolicy;
    }

    static boolean access$900(DefaultHlsPlaylistTracker arg0, HlsUrl arg1, boolean arg2) {
        return arg0.notifyPlaylistError(arg1, arg2);
    }

    public void addListener(PlaylistEventListener arg2) {
        this.listeners.add(arg2);
    }

    private void createBundles(List arg6) {
        int v0 = arg6.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg6.get(v1);
            this.playlistBundles.put(v2, new MediaPlaylistBundle(this, ((HlsUrl)v2)));
        }
    }

    private static Segment getFirstOldOverlappingSegment(HlsMediaPlaylist arg4, HlsMediaPlaylist arg5) {
        Segment v4_2;
        int v5 = ((int)(arg5.mediaSequence - arg4.mediaSequence));
        List v4 = arg4.segments;
        if(v5 < v4.size()) {
            Object v4_1 = v4.get(v5);
        }
        else {
            v4_2 = null;
        }

        return v4_2;
    }

    public long getInitialStartTimeUs() {
        return this.initialStartTimeUs;
    }

    private HlsMediaPlaylist getLatestPlaylistSnapshot(HlsMediaPlaylist arg3, HlsMediaPlaylist arg4) {
        if(!arg4.isNewerThan(arg3)) {
            if(arg4.hasEndTag) {
                arg3 = arg3.copyWithEndTag();
            }

            return arg3;
        }

        return arg4.copyWith(this.getLoadedPlaylistStartTimeUs(arg3, arg4), this.getLoadedPlaylistDiscontinuitySequence(arg3, arg4));
    }

    private int getLoadedPlaylistDiscontinuitySequence(HlsMediaPlaylist arg4, HlsMediaPlaylist arg5) {
        if(arg5.hasDiscontinuitySequence) {
            return arg5.discontinuitySequence;
        }

        int v0 = this.primaryUrlSnapshot != null ? this.primaryUrlSnapshot.discontinuitySequence : 0;
        if(arg4 == null) {
            return v0;
        }

        Segment v2 = DefaultHlsPlaylistTracker.getFirstOldOverlappingSegment(arg4, arg5);
        if(v2 != null) {
            return arg4.discontinuitySequence + v2.relativeDiscontinuitySequence - arg5.segments.get(0).relativeDiscontinuitySequence;
        }

        return v0;
    }

    private long getLoadedPlaylistStartTimeUs(HlsMediaPlaylist arg9, HlsMediaPlaylist arg10) {
        if(arg10.hasProgramDateTime) {
            return arg10.startTimeUs;
        }

        long v0 = this.primaryUrlSnapshot != null ? this.primaryUrlSnapshot.startTimeUs : 0;
        if(arg9 == null) {
            return v0;
        }

        int v2 = arg9.segments.size();
        Segment v3 = DefaultHlsPlaylistTracker.getFirstOldOverlappingSegment(arg9, arg10);
        if(v3 != null) {
            return arg9.startTimeUs + v3.relativeStartTimeUs;
        }

        if((((long)v2)) == arg10.mediaSequence - arg9.mediaSequence) {
            return arg9.getEndTimeUs();
        }

        return v0;
    }

    public HlsMasterPlaylist getMasterPlaylist() {
        return this.masterPlaylist;
    }

    public HlsMediaPlaylist getPlaylistSnapshot(HlsUrl arg2) {
        HlsMediaPlaylist v0 = this.playlistBundles.get(arg2).getPlaylistSnapshot();
        if(v0 != null) {
            this.maybeSetPrimaryUrl(arg2);
        }

        return v0;
    }

    public boolean isLive() {
        return this.isLive;
    }

    public boolean isSnapshotValid(HlsUrl arg2) {
        return this.playlistBundles.get(arg2).isSnapshotValid();
    }

    private boolean maybeSelectNewPrimaryUrl() {
        List v0 = this.masterPlaylist.variants;
        int v1 = v0.size();
        long v2 = SystemClock.elapsedRealtime();
        int v5;
        for(v5 = 0; v5 < v1; ++v5) {
            Object v6 = this.playlistBundles.get(v0.get(v5));
            if(v2 > MediaPlaylistBundle.access$100(((MediaPlaylistBundle)v6))) {
                this.primaryHlsUrl = MediaPlaylistBundle.access$200(((MediaPlaylistBundle)v6));
                ((MediaPlaylistBundle)v6).loadPlaylist();
                return 1;
            }
        }

        return 0;
    }

    private void maybeSetPrimaryUrl(HlsUrl arg2) {
        if(arg2 != this.primaryHlsUrl && (this.masterPlaylist.variants.contains(arg2)) && (this.primaryUrlSnapshot == null || !this.primaryUrlSnapshot.hasEndTag)) {
            this.primaryHlsUrl = arg2;
            this.playlistBundles.get(this.primaryHlsUrl).loadPlaylist();
        }
    }

    public void maybeThrowPlaylistRefreshError(HlsUrl arg2) {
        this.playlistBundles.get(arg2).maybeThrowPlaylistRefreshError();
    }

    public void maybeThrowPrimaryPlaylistRefreshError() {
        if(this.initialPlaylistLoader != null) {
            this.initialPlaylistLoader.maybeThrowError();
        }

        if(this.primaryHlsUrl != null) {
            this.maybeThrowPlaylistRefreshError(this.primaryHlsUrl);
        }
    }

    private boolean notifyPlaylistError(HlsUrl arg5, boolean arg6) {
        int v0 = this.listeners.size();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            v2 |= this.listeners.get(v1).onPlaylistError(arg5, arg6) ^ 1;
            ++v1;
        }

        return ((boolean)v2);
    }

    public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
        this.onLoadCanceled(((ParsingLoadable)arg1), arg2, arg4, arg6);
    }

    public void onLoadCanceled(ParsingLoadable arg12, long arg13, long arg15, boolean arg17) {
        this.eventDispatcher.loadCanceled(arg12.dataSpec, arg12.getUri(), 4, arg13, arg15, arg12.bytesLoaded());
    }

    public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
        this.onLoadCompleted(((ParsingLoadable)arg1), arg2, arg4);
    }

    public void onLoadCompleted(ParsingLoadable arg15, long arg16, long arg18) {
        Object v3_1;
        DefaultHlsPlaylistTracker v0 = this;
        Object v1 = arg15.getResult();
        boolean v2 = v1 instanceof HlsMediaPlaylist;
        if(v2) {
            HlsMasterPlaylist v3 = HlsMasterPlaylist.createSingleVariantMasterPlaylist(((HlsPlaylist)v1).baseUri);
        }
        else {
            v3_1 = v1;
        }

        v0.masterPlaylist = ((HlsMasterPlaylist)v3_1);
        v0.primaryHlsUrl = ((HlsMasterPlaylist)v3_1).variants.get(0);
        ArrayList v4 = new ArrayList();
        v4.addAll(((HlsMasterPlaylist)v3_1).variants);
        v4.addAll(((HlsMasterPlaylist)v3_1).audios);
        v4.addAll(((HlsMasterPlaylist)v3_1).subtitles);
        this.createBundles(((List)v4));
        v3_1 = v0.playlistBundles.get(v0.primaryHlsUrl);
        if(v2) {
            MediaPlaylistBundle.access$000(((MediaPlaylistBundle)v3_1), ((HlsMediaPlaylist)v1));
        }
        else {
            ((MediaPlaylistBundle)v3_1).loadPlaylist();
        }

        v0.eventDispatcher.loadCompleted(arg15.dataSpec, arg15.getUri(), 4, arg16, arg18, arg15.bytesLoaded());
    }

    public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
        return this.onLoadError(((ParsingLoadable)arg1), arg2, arg4, arg6, arg7);
    }

    public LoadErrorAction onLoadError(ParsingLoadable arg18, long arg19, long arg21, IOException arg23, int arg24) {
        // Method was not decompiled
    }

    private void onPlaylistUpdated(HlsUrl arg3, HlsMediaPlaylist arg4) {
        if(arg3 == this.primaryHlsUrl) {
            if(this.primaryUrlSnapshot == null) {
                this.isLive = arg4.hasEndTag ^ 1;
                this.initialStartTimeUs = arg4.startTimeUs;
            }

            this.primaryUrlSnapshot = arg4;
            this.primaryPlaylistListener.onPrimaryPlaylistRefreshed(arg4);
        }

        int v3 = this.listeners.size();
        int v4;
        for(v4 = 0; v4 < v3; ++v4) {
            this.listeners.get(v4).onPlaylistChanged();
        }
    }

    public void refreshPlaylist(HlsUrl arg2) {
        this.playlistBundles.get(arg2).loadPlaylist();
    }

    public void removeListener(PlaylistEventListener arg2) {
        this.listeners.remove(arg2);
    }

    public void start(Uri arg8, EventDispatcher arg9, PrimaryPlaylistListener arg10) {
        this.playlistRefreshHandler = new Handler();
        this.eventDispatcher = arg9;
        this.primaryPlaylistListener = arg10;
        ParsingLoadable v10 = new ParsingLoadable(this.dataSourceFactory.createDataSource(4), arg8, 4, this.playlistParser);
        boolean v8 = this.initialPlaylistLoader == null ? true : false;
        Assertions.checkState(v8);
        this.initialPlaylistLoader = new Loader("DefaultHlsPlaylistTracker:MasterPlaylist");
        arg9.loadStarted(v10.dataSpec, v10.dataSpec.uri, v10.type, this.initialPlaylistLoader.startLoading(((Loadable)v10), ((Callback)this), this.minRetryCount));
    }

    public void stop() {
        HlsUrl v0 = null;
        this.primaryHlsUrl = v0;
        this.primaryUrlSnapshot = ((HlsMediaPlaylist)v0);
        this.masterPlaylist = ((HlsMasterPlaylist)v0);
        this.initialStartTimeUs = -9223372036854775807L;
        this.initialPlaylistLoader.release();
        this.initialPlaylistLoader = ((Loader)v0);
        Iterator v1 = this.playlistBundles.values().iterator();
        while(v1.hasNext()) {
            v1.next().release();
        }

        this.playlistRefreshHandler.removeCallbacksAndMessages(v0);
        this.playlistRefreshHandler = ((Handler)v0);
        this.playlistBundles.clear();
    }
}

