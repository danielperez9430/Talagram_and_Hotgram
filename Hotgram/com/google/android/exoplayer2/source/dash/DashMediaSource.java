package com.google.android.exoplayer2.source.dash;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ads.AdsMediaSource$MediaSourceFactory;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.Loader$Callback;
import com.google.android.exoplayer2.upstream.Loader$LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower$Dummy;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DashMediaSource extends BaseMediaSource {
    final class DashTimeline extends Timeline {
        private final int firstPeriodId;
        private final DashManifest manifest;
        private final long offsetInFirstPeriodUs;
        private final long presentationStartTimeMs;
        private final long windowDefaultStartPositionUs;
        private final long windowDurationUs;
        private final long windowStartTimeMs;
        private final Object windowTag;

        public DashTimeline(long arg1, long arg3, int arg5, long arg6, long arg8, long arg10, DashManifest arg12, Object arg13) {
            super();
            this.presentationStartTimeMs = arg1;
            this.windowStartTimeMs = arg3;
            this.firstPeriodId = arg5;
            this.offsetInFirstPeriodUs = arg6;
            this.windowDurationUs = arg8;
            this.windowDefaultStartPositionUs = arg10;
            this.manifest = arg12;
            this.windowTag = arg13;
        }

        private long getAdjustedWindowDefaultStartPositionUs(long arg9) {
            long v0 = this.windowDefaultStartPositionUs;
            if(!this.manifest.dynamic) {
                return v0;
            }

            if(arg9 > 0) {
                v0 += arg9;
                if(v0 > this.windowDurationUs) {
                    return -9223372036854775807L;
                }
            }

            arg9 = this.offsetInFirstPeriodUs + v0;
            long v6 = this.manifest.getPeriodDurationUs(0);
            long v4 = arg9;
            int v9 = 0;
            while(v9 < this.manifest.getPeriodCount() - 1) {
                if(v4 < v6) {
                    break;
                }

                v4 -= v6;
                ++v9;
                v6 = this.manifest.getPeriodDurationUs(v9);
            }

            Period v9_1 = this.manifest.getPeriod(v9);
            int v10 = v9_1.getAdaptationSetIndex(2);
            if(v10 == -1) {
                return v0;
            }

            DashSegmentIndex v9_2 = v9_1.adaptationSets.get(v10).representations.get(0).getIndex();
            if(v9_2 != null) {
                if(v9_2.getSegmentCount(v6) == 0) {
                }
                else {
                    v0 = v0 + v9_2.getTimeUs(v9_2.getSegmentNum(v4, v6)) - v4;
                }
            }

            return v0;
        }

        public int getIndexOfPeriod(Object arg3) {
            int v1 = -1;
            if(!(arg3 instanceof Integer)) {
                return v1;
            }

            int v3 = ((Integer)arg3).intValue() - this.firstPeriodId;
            if(v3 < 0 || v3 >= this.getPeriodCount()) {
                v3 = -1;
            }

            return v3;
        }

        public com.google.android.exoplayer2.Timeline$Period getPeriod(int arg12, com.google.android.exoplayer2.Timeline$Period arg13, boolean arg14) {
            String v4;
            Assertions.checkIndex(arg12, 0, this.getPeriodCount());
            Integer v0 = null;
            if(arg14) {
                v4 = this.manifest.getPeriod(arg12).id;
            }
            else {
                Object v4_1 = v0;
            }

            if(arg14) {
                v0 = Integer.valueOf(this.firstPeriodId + arg12);
            }

            return arg13.set(v4, v0, 0, this.manifest.getPeriodDurationUs(arg12), C.msToUs(this.manifest.getPeriod(arg12).startMs - this.manifest.getPeriod(0).startMs) - this.offsetInFirstPeriodUs);
        }

        public int getPeriodCount() {
            return this.manifest.getPeriodCount();
        }

        public Object getUidOfPeriod(int arg3) {
            Assertions.checkIndex(arg3, 0, this.getPeriodCount());
            return Integer.valueOf(this.firstPeriodId + arg3);
        }

        public Window getWindow(int arg19, Window arg20, boolean arg21, long arg22) {
            DashTimeline v0 = this;
            Assertions.checkIndex(arg19, 0, 1);
            long v10 = v0.getAdjustedWindowDefaultStartPositionUs(arg22);
            Object v2 = arg21 ? v0.windowTag : null;
            Object v3 = v2;
            return arg20.set(v3, v0.presentationStartTimeMs, v0.windowStartTimeMs, true, v0.manifest.dynamic, v10, v0.windowDurationUs, 0, this.getPeriodCount() - 1, v0.offsetInFirstPeriodUs);
        }

        public int getWindowCount() {
            return 1;
        }
    }

    final class DefaultPlayerEmsgCallback implements PlayerEmsgCallback {
        DefaultPlayerEmsgCallback(DashMediaSource arg1, com.google.android.exoplayer2.source.dash.DashMediaSource$1 arg2) {
            this(arg1);
        }

        private DefaultPlayerEmsgCallback(DashMediaSource arg1) {
            DashMediaSource.this = arg1;
            super();
        }

        public void onDashLiveMediaPresentationEndSignalEncountered() {
            DashMediaSource.this.onDashLiveMediaPresentationEndSignalEncountered();
        }

        public void onDashManifestPublishTimeExpired(long arg2) {
            DashMediaSource.this.onDashManifestPublishTimeExpired(arg2);
        }

        public void onDashManifestRefreshRequested() {
            DashMediaSource.this.onDashManifestRefreshRequested();
        }
    }

    public final class Factory implements MediaSourceFactory {
        private final com.google.android.exoplayer2.source.dash.DashChunkSource$Factory chunkSourceFactory;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private boolean isCreateCalled;
        private long livePresentationDelayMs;
        private boolean livePresentationDelayOverridesManifest;
        private final com.google.android.exoplayer2.upstream.DataSource$Factory manifestDataSourceFactory;
        private Parser manifestParser;
        private int minLoadableRetryCount;
        private Object tag;

        public Factory(com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg1, com.google.android.exoplayer2.upstream.DataSource$Factory arg2) {
            super();
            this.chunkSourceFactory = Assertions.checkNotNull(arg1);
            this.manifestDataSourceFactory = arg2;
            this.minLoadableRetryCount = 3;
            this.livePresentationDelayMs = 30000;
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        }

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg2) {
            this(new com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$Factory(arg2), arg2);
        }

        public MediaSource createMediaSource(Uri arg1) {
            return this.createMediaSource(arg1);
        }

        public DashMediaSource createMediaSource(Uri arg15) {
            this.isCreateCalled = true;
            if(this.manifestParser == null) {
                this.manifestParser = new DashManifestParser();
            }

            return new DashMediaSource(null, Assertions.checkNotNull(arg15), this.manifestDataSourceFactory, this.manifestParser, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, this.livePresentationDelayOverridesManifest, this.tag, null);
        }

        @Deprecated public DashMediaSource createMediaSource(Uri arg1, Handler arg2, MediaSourceEventListener arg3) {
            DashMediaSource v1 = this.createMediaSource(arg1);
            if(arg2 != null && arg3 != null) {
                v1.addEventListener(arg2, arg3);
            }

            return v1;
        }

        public DashMediaSource createMediaSource(DashManifest arg16) {
            Assertions.checkArgument(arg16.dynamic ^ 1);
            this.isCreateCalled = true;
            return new DashMediaSource(arg16, null, null, null, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, this.livePresentationDelayOverridesManifest, this.tag, null);
        }

        @Deprecated public DashMediaSource createMediaSource(DashManifest arg1, Handler arg2, MediaSourceEventListener arg3) {
            DashMediaSource v1 = this.createMediaSource(arg1);
            if(arg2 != null && arg3 != null) {
                v1.addEventListener(arg2, arg3);
            }

            return v1;
        }

        public int[] getSupportedTypes() {
            return new int[]{0};
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.compositeSequenceableLoaderFactory = Assertions.checkNotNull(arg2);
            return this;
        }

        @Deprecated public Factory setLivePresentationDelayMs(long arg4) {
            boolean v0;
            if(arg4 == -1) {
                arg4 = 30000;
                v0 = false;
            }
            else {
                v0 = true;
            }

            return this.setLivePresentationDelayMs(arg4, v0);
        }

        public Factory setLivePresentationDelayMs(long arg2, boolean arg4) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.livePresentationDelayMs = arg2;
            this.livePresentationDelayOverridesManifest = arg4;
            return this;
        }

        public Factory setManifestParser(Parser arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.manifestParser = Assertions.checkNotNull(arg2);
            return this;
        }

        public Factory setMinLoadableRetryCount(int arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.minLoadableRetryCount = arg2;
            return this;
        }

        public Factory setTag(Object arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.tag = arg2;
            return this;
        }
    }

    final class Iso8601Parser implements Parser {
        private static final Pattern TIMESTAMP_WITH_TIMEZONE_PATTERN;

        static {
            Iso8601Parser.TIMESTAMP_WITH_TIMEZONE_PATTERN = Pattern.compile("(.+?)(Z|((\\+|-|−)(\\d\\d)(:?(\\d\\d))?))");
        }

        Iso8601Parser() {
            super();
        }

        public Long parse(Uri arg9, InputStream arg10) {
            String v9 = new BufferedReader(new InputStreamReader(arg10, Charset.forName("UTF-8"))).readLine();
            try {
                Matcher v10 = Iso8601Parser.TIMESTAMP_WITH_TIMEZONE_PATTERN.matcher(((CharSequence)v9));
                if(v10.matches()) {
                    v9 = v10.group(1);
                    SimpleDateFormat v0 = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss", Locale.US);
                    v0.setTimeZone(TimeZone.getTimeZone("UTC"));
                    long v0_1 = v0.parse(v9).getTime();
                    if("Z".equals(v10.group(2))) {
                    }
                    else {
                        long v2 = "+".equals(v10.group(4)) ? 1 : -1;
                        long v4 = Long.parseLong(v10.group(5));
                        v9 = v10.group(7);
                        long v9_2 = TextUtils.isEmpty(((CharSequence)v9)) ? 0 : Long.parseLong(v9);
                        v0_1 -= v2 * ((v4 * 60 + v9_2) * 60 * 1000);
                    }

                    return Long.valueOf(v0_1);
                }

                StringBuilder v0_2 = new StringBuilder();
                v0_2.append("Couldn\'t parse timestamp: ");
                v0_2.append(v9);
                throw new ParserException(v0_2.toString());
            }
            catch(ParseException v9_1) {
                throw new ParserException(((Throwable)v9_1));
            }
        }

        public Object parse(Uri arg1, InputStream arg2) {
            return this.parse(arg1, arg2);
        }
    }

    final class ManifestCallback implements Callback {
        ManifestCallback(DashMediaSource arg1, com.google.android.exoplayer2.source.dash.DashMediaSource$1 arg2) {
            this(arg1);
        }

        private ManifestCallback(DashMediaSource arg1) {
            DashMediaSource.this = arg1;
            super();
        }

        public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
            this.onLoadCanceled(((ParsingLoadable)arg1), arg2, arg4, arg6);
        }

        public void onLoadCanceled(ParsingLoadable arg7, long arg8, long arg10, boolean arg12) {
            DashMediaSource.this.onLoadCanceled(arg7, arg8, arg10);
        }

        public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
            this.onLoadCompleted(((ParsingLoadable)arg1), arg2, arg4);
        }

        public void onLoadCompleted(ParsingLoadable arg7, long arg8, long arg10) {
            DashMediaSource.this.onManifestLoadCompleted(arg7, arg8, arg10);
        }

        public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
            return this.onLoadError(((ParsingLoadable)arg1), arg2, arg4, arg6, arg7);
        }

        public LoadErrorAction onLoadError(ParsingLoadable arg8, long arg9, long arg11, IOException arg13, int arg14) {
            return DashMediaSource.this.onManifestLoadError(arg8, arg9, arg11, arg13);
        }
    }

    final class ManifestLoadErrorThrower implements LoaderErrorThrower {
        ManifestLoadErrorThrower(DashMediaSource arg1) {
            DashMediaSource.this = arg1;
            super();
        }

        public void maybeThrowError() {
            DashMediaSource.this.loader.maybeThrowError();
            this.maybeThrowManifestError();
        }

        public void maybeThrowError(int arg2) {
            DashMediaSource.this.loader.maybeThrowError(arg2);
            this.maybeThrowManifestError();
        }

        private void maybeThrowManifestError() {
            if(DashMediaSource.this.manifestFatalError == null) {
                return;
            }

            throw DashMediaSource.this.manifestFatalError;
        }
    }

    final class PeriodSeekInfo {
        public final long availableEndTimeUs;
        public final long availableStartTimeUs;
        public final boolean isIndexExplicit;

        private PeriodSeekInfo(boolean arg1, long arg2, long arg4) {
            super();
            this.isIndexExplicit = arg1;
            this.availableStartTimeUs = arg2;
            this.availableEndTimeUs = arg4;
        }

        public static PeriodSeekInfo createPeriodSeekInfo(Period arg18, long arg19) {
            int v16;
            int v17;
            int v7;
            Period v0 = arg18;
            long v4 = arg19;
            int v1 = v0.adaptationSets.size();
            int v2 = 0;
            int v3 = 0;
            while(true) {
                if(v3 < v1) {
                    v7 = v0.adaptationSets.get(v3).type;
                    if(v7 != 1) {
                        if(v7 == 2) {
                        }
                        else {
                            ++v3;
                            continue;
                        }
                    }

                    break;
                }
                else {
                    goto label_19;
                }
            }

            v3 = 1;
            goto label_20;
        label_19:
            v3 = 0;
        label_20:
            long v14 = 9223372036854775807L;
            int v9 = 0;
            int v10 = 0;
            int v11 = 0;
            long v12 = 0;
            while(v9 < v1) {
                Object v6 = v0.adaptationSets.get(v9);
                if(v3 == 0 || ((AdaptationSet)v6).type != 3) {
                    DashSegmentIndex v6_1 = ((AdaptationSet)v6).representations.get(v2).getIndex();
                    if(v6_1 == null) {
                        return new PeriodSeekInfo(true, 0, arg19);
                    }
                    else {
                        v7 = v6_1.isExplicit() | v11;
                        int v8 = v6_1.getSegmentCount(v4);
                        if(v8 == 0) {
                            v17 = v1;
                            v16 = v3;
                            v11 = v7;
                            v10 = 1;
                            v12 = 0;
                            v14 = 0;
                        }
                        else {
                            if(v10 == 0) {
                                v16 = v3;
                                long v2_1 = v6_1.getFirstSegmentNum();
                                v17 = v1;
                                long v0_1 = Math.max(v12, v6_1.getTimeUs(v2_1));
                                if(v8 != -1) {
                                    v2_1 = v2_1 + (((long)v8)) - 1;
                                    v12 = v0_1;
                                    v14 = Math.min(v14, v6_1.getTimeUs(v2_1) + v6_1.getDurationUs(v2_1, v4));
                                }
                                else {
                                    v12 = v0_1;
                                }
                            }
                            else {
                                v17 = v1;
                                v16 = v3;
                            }

                            v11 = v7;
                        }
                    }
                }
                else {
                    v17 = v1;
                    v16 = v3;
                }

                ++v9;
                v3 = v16;
                v1 = v17;
                v0 = arg18;
                v2 = 0;
            }

            return new PeriodSeekInfo(((boolean)v11), v12, v14);
        }
    }

    final class UtcTimestampCallback implements Callback {
        UtcTimestampCallback(DashMediaSource arg1, com.google.android.exoplayer2.source.dash.DashMediaSource$1 arg2) {
            this(arg1);
        }

        private UtcTimestampCallback(DashMediaSource arg1) {
            DashMediaSource.this = arg1;
            super();
        }

        public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
            this.onLoadCanceled(((ParsingLoadable)arg1), arg2, arg4, arg6);
        }

        public void onLoadCanceled(ParsingLoadable arg7, long arg8, long arg10, boolean arg12) {
            DashMediaSource.this.onLoadCanceled(arg7, arg8, arg10);
        }

        public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
            this.onLoadCompleted(((ParsingLoadable)arg1), arg2, arg4);
        }

        public void onLoadCompleted(ParsingLoadable arg7, long arg8, long arg10) {
            DashMediaSource.this.onUtcTimestampLoadCompleted(arg7, arg8, arg10);
        }

        public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
            return this.onLoadError(((ParsingLoadable)arg1), arg2, arg4, arg6, arg7);
        }

        public LoadErrorAction onLoadError(ParsingLoadable arg8, long arg9, long arg11, IOException arg13, int arg14) {
            return DashMediaSource.this.onUtcTimestampLoadError(arg8, arg9, arg11, arg13);
        }
    }

    final class XsDateTimeParser implements Parser {
        XsDateTimeParser(com.google.android.exoplayer2.source.dash.DashMediaSource$1 arg1) {
            this();
        }

        private XsDateTimeParser() {
            super();
        }

        public Long parse(Uri arg2, InputStream arg3) {
            return Long.valueOf(Util.parseXsDateTime(new BufferedReader(new InputStreamReader(arg3)).readLine()));
        }

        public Object parse(Uri arg1, InputStream arg2) {
            return this.parse(arg1, arg2);
        }
    }

    @Deprecated public static final long DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS = 30000;
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_MS = 30000;
    @Deprecated public static final long DEFAULT_LIVE_PRESENTATION_DELAY_PREFER_MANIFEST_MS = -1;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private static final long MIN_LIVE_DEFAULT_START_POSITION_US = 5000000;
    private static final int NOTIFY_MANIFEST_INTERVAL_MS = 5000;
    private static final String TAG = "DashMediaSource";
    private final com.google.android.exoplayer2.source.dash.DashChunkSource$Factory chunkSourceFactory;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private DataSource dataSource;
    private boolean dynamicMediaPresentationEnded;
    private long elapsedRealtimeOffsetMs;
    private long expiredManifestPublishTimeUs;
    private int firstPeriodId;
    private Handler handler;
    private Uri initialManifestUri;
    private final long livePresentationDelayMs;
    private final boolean livePresentationDelayOverridesManifest;
    private Loader loader;
    private DashManifest manifest;
    private final ManifestCallback manifestCallback;
    private final com.google.android.exoplayer2.upstream.DataSource$Factory manifestDataSourceFactory;
    private final EventDispatcher manifestEventDispatcher;
    private IOException manifestFatalError;
    private long manifestLoadEndTimestampMs;
    private final LoaderErrorThrower manifestLoadErrorThrower;
    private boolean manifestLoadPending;
    private long manifestLoadStartTimestampMs;
    private final Parser manifestParser;
    private Uri manifestUri;
    private final Object manifestUriLock;
    private TransferListener mediaTransferListener;
    private final int minLoadableRetryCount;
    private final SparseArray periodsById;
    private final PlayerEmsgCallback playerEmsgCallback;
    private final Runnable refreshManifestRunnable;
    private final boolean sideloadedManifest;
    private final Runnable simulateManifestRefreshRunnable;
    private int staleManifestReloadAttempt;
    private final Object tag;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.dash");
    }

    @Deprecated public DashMediaSource(Uri arg11, com.google.android.exoplayer2.upstream.DataSource$Factory arg12, com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg13, int arg14, long arg15, Handler arg17, MediaSourceEventListener arg18) {
        this(arg11, arg12, new DashManifestParser(), arg13, arg14, arg15, arg17, arg18);
    }

    @Deprecated public DashMediaSource(Uri arg16, com.google.android.exoplayer2.upstream.DataSource$Factory arg17, Parser arg18, com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg19, int arg20, long arg21, Handler arg23, MediaSourceEventListener arg24) {
        Handler v1 = arg23;
        MediaSourceEventListener v2 = arg24;
        DefaultCompositeSequenceableLoaderFactory v9 = new DefaultCompositeSequenceableLoaderFactory();
        long v3 = -1;
        long v11 = arg21 == v3 ? 30000 : arg21;
        boolean v13 = arg21 != v3 ? true : false;
        this(null, arg16, arg17, arg18, arg19, ((CompositeSequenceableLoaderFactory)v9), arg20, v11, v13, null);
        if(v1 != null && v2 != null) {
            this.addEventListener(v1, v2);
        }
    }

    @Deprecated public DashMediaSource(Uri arg10, com.google.android.exoplayer2.upstream.DataSource$Factory arg11, com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg12, Handler arg13, MediaSourceEventListener arg14) {
        this(arg10, arg11, arg12, 3, -1, arg13, arg14);
    }

    private DashMediaSource(DashManifest arg1, Uri arg2, com.google.android.exoplayer2.upstream.DataSource$Factory arg3, Parser arg4, com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg5, CompositeSequenceableLoaderFactory arg6, int arg7, long arg8, boolean arg10, Object arg11) {
        super();
        this.initialManifestUri = arg2;
        this.manifest = arg1;
        this.manifestUri = arg2;
        this.manifestDataSourceFactory = arg3;
        this.manifestParser = arg4;
        this.chunkSourceFactory = arg5;
        this.minLoadableRetryCount = arg7;
        this.livePresentationDelayMs = arg8;
        this.livePresentationDelayOverridesManifest = arg10;
        this.compositeSequenceableLoaderFactory = arg6;
        this.tag = arg11;
        boolean v3 = arg1 != null ? true : false;
        this.sideloadedManifest = v3;
        MediaPeriodId v3_1 = null;
        this.manifestEventDispatcher = this.createEventDispatcher(v3_1);
        this.manifestUriLock = new Object();
        this.periodsById = new SparseArray();
        this.playerEmsgCallback = new DefaultPlayerEmsgCallback(this, ((com.google.android.exoplayer2.source.dash.DashMediaSource$1)v3_1));
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        if(this.sideloadedManifest) {
            Assertions.checkState(arg1.dynamic ^ 1);
            this.manifestCallback = ((ManifestCallback)v3_1);
            this.refreshManifestRunnable = ((Runnable)v3_1);
            this.simulateManifestRefreshRunnable = ((Runnable)v3_1);
            this.manifestLoadErrorThrower = new Dummy();
        }
        else {
            this.manifestCallback = new ManifestCallback(this, ((com.google.android.exoplayer2.source.dash.DashMediaSource$1)v3_1));
            this.manifestLoadErrorThrower = new ManifestLoadErrorThrower(this);
            this.refreshManifestRunnable = new Runnable() {
                public void run() {
                    DashMediaSource.access$300(DashMediaSource.this);
                }
            };
            this.simulateManifestRefreshRunnable = new Runnable() {
                public void run() {
                    DashMediaSource.access$400(DashMediaSource.this, false);
                }
            };
        }
    }

    DashMediaSource(DashManifest arg1, Uri arg2, com.google.android.exoplayer2.upstream.DataSource$Factory arg3, Parser arg4, com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg5, CompositeSequenceableLoaderFactory arg6, int arg7, long arg8, boolean arg10, Object arg11, com.google.android.exoplayer2.source.dash.DashMediaSource$1 arg12) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg10, arg11);
    }

    @Deprecated public DashMediaSource(DashManifest arg15, com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg16, int arg17, Handler arg18, MediaSourceEventListener arg19) {
        Handler v0 = arg18;
        MediaSourceEventListener v1 = arg19;
        this(arg15, null, null, null, arg16, new DefaultCompositeSequenceableLoaderFactory(), arg17, 30000, false, null);
        if(v0 != null && v1 != null) {
            this.addEventListener(v0, v1);
        }
    }

    @Deprecated public DashMediaSource(DashManifest arg7, com.google.android.exoplayer2.source.dash.DashChunkSource$Factory arg8, Handler arg9, MediaSourceEventListener arg10) {
        this(arg7, arg8, 3, arg9, arg10);
    }

    static void access$300(DashMediaSource arg0) {
        arg0.startLoadingManifest();
    }

    static void access$400(DashMediaSource arg0, boolean arg1) {
        arg0.processManifest(arg1);
    }

    static Loader access$700(DashMediaSource arg0) {
        return arg0.loader;
    }

    static IOException access$800(DashMediaSource arg0) {
        return arg0.manifestFatalError;
    }

    public MediaPeriod createPeriod(MediaPeriodId arg17, Allocator arg18) {
        DashMediaPeriod v15 = new DashMediaPeriod(this.firstPeriodId + arg17.periodIndex, this.manifest, arg17.periodIndex, this.chunkSourceFactory, this.mediaTransferListener, this.minLoadableRetryCount, this.createEventDispatcher(arg17, this.manifest.getPeriod(arg17.periodIndex).startMs), this.elapsedRealtimeOffsetMs, this.manifestLoadErrorThrower, arg18, this.compositeSequenceableLoaderFactory, this.playerEmsgCallback);
        this.periodsById.put(v15.id, v15);
        return ((MediaPeriod)v15);
    }

    private long getManifestLoadRetryDelayMillis() {
        return ((long)Math.min((this.staleManifestReloadAttempt - 1) * 1000, 5000));
    }

    private long getNowUnixTimeUs() {
        long v0 = this.elapsedRealtimeOffsetMs != 0 ? SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs : System.currentTimeMillis();
        return C.msToUs(v0);
    }

    public void maybeThrowSourceInfoRefreshError() {
        this.manifestLoadErrorThrower.maybeThrowError();
    }

    void onDashLiveMediaPresentationEndSignalEncountered() {
        this.dynamicMediaPresentationEnded = true;
    }

    void onDashManifestPublishTimeExpired(long arg6) {
        if(this.expiredManifestPublishTimeUs == -9223372036854775807L || this.expiredManifestPublishTimeUs < arg6) {
            this.expiredManifestPublishTimeUs = arg6;
        }
    }

    void onDashManifestRefreshRequested() {
        this.handler.removeCallbacks(this.simulateManifestRefreshRunnable);
        this.startLoadingManifest();
    }

    void onLoadCanceled(ParsingLoadable arg11, long arg12, long arg14) {
        this.manifestEventDispatcher.loadCanceled(arg11.dataSpec, arg11.getUri(), arg11.type, arg12, arg14, arg11.bytesLoaded());
    }

    void onManifestLoadCompleted(ParsingLoadable arg11, long arg12, long arg14) {
        Object v12;
        int v3_2;
        String v6;
        String v3_1;
        this.manifestEventDispatcher.loadCompleted(arg11.dataSpec, arg11.getUri(), arg11.type, arg12, arg14, arg11.bytesLoaded());
        Object v0 = arg11.getResult();
        int v2 = 0;
        int v1 = this.manifest == null ? 0 : this.manifest.getPeriodCount();
        long v3 = ((DashManifest)v0).getPeriod(0).startMs;
        int v5;
        for(v5 = 0; v5 < v1; ++v5) {
            if(this.manifest.getPeriod(v5).startMs >= v3) {
                break;
            }
        }

        if(((DashManifest)v0).dynamic) {
            if(v1 - v5 > ((DashManifest)v0).getPeriodCount()) {
                v3_1 = "DashMediaSource";
                v6 = "Loaded out of sync manifest";
                goto label_34;
            }
            else {
                if(!this.dynamicMediaPresentationEnded && (this.expiredManifestPublishTimeUs == -9223372036854775807L || ((DashManifest)v0).publishTimeMs * 1000 > this.expiredManifestPublishTimeUs)) {
                    v3_2 = 0;
                    goto label_67;
                }

                v3_1 = "DashMediaSource";
                v6 = "Loaded stale dynamic manifest: " + ((DashManifest)v0).publishTimeMs + ", " + this.dynamicMediaPresentationEnded + ", " + this.expiredManifestPublishTimeUs;
            label_34:
                Log.w(v3_1, v6);
                v3_2 = 1;
            }

        label_67:
            if(v3_2 != 0) {
                int v11 = this.staleManifestReloadAttempt;
                this.staleManifestReloadAttempt = v11 + 1;
                if(v11 < this.minLoadableRetryCount) {
                    this.scheduleManifestRefresh(this.getManifestLoadRetryDelayMillis());
                }
                else {
                    this.manifestFatalError = new DashManifestStaleException();
                }

                return;
            }

            this.staleManifestReloadAttempt = 0;
        }

        this.manifest = ((DashManifest)v0);
        this.manifestLoadPending &= this.manifest.dynamic;
        this.manifestLoadStartTimestampMs = arg12 - arg14;
        this.manifestLoadEndTimestampMs = arg12;
        if(this.manifest.location != null) {
            v12 = this.manifestUriLock;
            __monitor_enter(v12);
            try {
                if(arg11.dataSpec.uri == this.manifestUri) {
                    v2 = 1;
                }

                if(v2 != 0) {
                    this.manifestUri = this.manifest.location;
                }

                __monitor_exit(v12);
            }
            catch(Throwable v11_1) {
                goto label_107;
            }
        }

        if(v1 != 0) {
            this.firstPeriodId += v5;
        label_120:
            this.processManifest(true);
        }
        else if(this.manifest.utcTiming != null) {
            this.resolveUtcTimingElement(this.manifest.utcTiming);
        }
        else {
            goto label_120;
        }

        return;
        try {
        label_107:
            __monitor_exit(v12);
        }
        catch(Throwable v11_1) {
            goto label_107;
        }

        throw v11_1;
    }

    LoadErrorAction onManifestLoadError(ParsingLoadable arg15, long arg16, long arg18, IOException arg20) {
        boolean v12 = arg20 instanceof ParserException;
        this.manifestEventDispatcher.loadError(arg15.dataSpec, arg15.getUri(), arg15.type, arg16, arg18, arg15.bytesLoaded(), arg20, v12);
        LoadErrorAction v0 = v12 ? Loader.DONT_RETRY_FATAL : Loader.RETRY;
        return v0;
    }

    void onUtcTimestampLoadCompleted(ParsingLoadable arg11, long arg12, long arg14) {
        this.manifestEventDispatcher.loadCompleted(arg11.dataSpec, arg11.getUri(), arg11.type, arg12, arg14, arg11.bytesLoaded());
        this.onUtcTimestampResolved(arg11.getResult().longValue() - arg12);
    }

    LoadErrorAction onUtcTimestampLoadError(ParsingLoadable arg14, long arg15, long arg17, IOException arg19) {
        this.manifestEventDispatcher.loadError(arg14.dataSpec, arg14.getUri(), arg14.type, arg15, arg17, arg14.bytesLoaded(), arg19, true);
        this.onUtcTimestampResolutionError(arg19);
        return Loader.DONT_RETRY;
    }

    private void onUtcTimestampResolutionError(IOException arg3) {
        Log.e("DashMediaSource", "Failed to resolve UtcTiming element.", ((Throwable)arg3));
        this.processManifest(true);
    }

    private void onUtcTimestampResolved(long arg1) {
        this.elapsedRealtimeOffsetMs = arg1;
        this.processManifest(true);
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        this.mediaTransferListener = arg3;
        if(this.sideloadedManifest) {
            this.processManifest(false);
        }
        else {
            this.dataSource = this.manifestDataSourceFactory.createDataSource();
            this.loader = new Loader("Loader:DashMediaSource");
            this.handler = new Handler();
            this.startLoadingManifest();
        }
    }

    private void processManifest(boolean arg29) {
        long v24;
        long v3_1;
        long v4_1;
        long v20;
        DashMediaSource v0 = this;
        int v2;
        for(v2 = 0; v2 < v0.periodsById.size(); ++v2) {
            int v3 = v0.periodsById.keyAt(v2);
            if(v3 >= v0.firstPeriodId) {
                v0.periodsById.valueAt(v2).updateManifest(v0.manifest, v3 - v0.firstPeriodId);
            }
        }

        v2 = v0.manifest.getPeriodCount() - 1;
        PeriodSeekInfo v4 = PeriodSeekInfo.createPeriodSeekInfo(v0.manifest.getPeriod(0), v0.manifest.getPeriodDurationUs(0));
        PeriodSeekInfo v5 = PeriodSeekInfo.createPeriodSeekInfo(v0.manifest.getPeriod(v2), v0.manifest.getPeriodDurationUs(v2));
        long v6 = v4.availableStartTimeUs;
        long v8 = v5.availableEndTimeUs;
        long v10 = -9223372036854775807L;
        long v12 = 0;
        if(!v0.manifest.dynamic || (v5.isIndexExplicit)) {
            v20 = v6;
            v2 = 0;
            goto label_78;
        label_59:
            while(v4_1 < v12) {
                if(v2 <= 0) {
                    break;
                }

                --v2;
                v4_1 += v0.manifest.getPeriodDurationUs(v2);
            }

            v4_1 = v2 == 0 ? Math.max(v6, v4_1) : v0.manifest.getPeriodDurationUs(0);
            v6 = v4_1;
        label_73:
            v20 = v6;
            v2 = 1;
        }
        else {
            v8 = Math.min(this.getNowUnixTimeUs() - C.msToUs(v0.manifest.availabilityStartTimeMs) - C.msToUs(v0.manifest.getPeriod(v2).startMs), v8);
            if(v0.manifest.timeShiftBufferDepthMs != v10) {
                v4_1 = v8 - C.msToUs(v0.manifest.timeShiftBufferDepthMs);
                goto label_59;
            }

            goto label_73;
        }

    label_78:
        long v22 = v8 - v20;
        int v4_2;
        for(v4_2 = 0; v4_2 < v0.manifest.getPeriodCount() - 1; ++v4_2) {
            v22 += v0.manifest.getPeriodDurationUs(v4_2);
        }

        if(v0.manifest.dynamic) {
            v3_1 = v0.livePresentationDelayMs;
            if(!v0.livePresentationDelayOverridesManifest && v0.manifest.suggestedPresentationDelayMs != v10) {
                v3_1 = v0.manifest.suggestedPresentationDelayMs;
            }

            v3_1 = v22 - C.msToUs(v3_1);
            long v5_1 = 5000000;
            if(v3_1 < v5_1) {
                v3_1 = Math.min(v5_1, v22 / 2);
            }

            v24 = v3_1;
        }
        else {
            v24 = v12;
        }

        v0.refreshSourceInfo(new DashTimeline(v0.manifest.availabilityStartTimeMs, v0.manifest.availabilityStartTimeMs + v0.manifest.getPeriod(0).startMs + C.usToMs(v20), v0.firstPeriodId, v20, v22, v24, v0.manifest, v0.tag), v0.manifest);
        if(!v0.sideloadedManifest) {
            v0.handler.removeCallbacks(v0.simulateManifestRefreshRunnable);
            v3_1 = 5000;
            if(v2 != 0) {
                v0.handler.postDelayed(v0.simulateManifestRefreshRunnable, v3_1);
            }

            if(v0.manifestLoadPending) {
                this.startLoadingManifest();
                return;
            }

            if(!arg29) {
                return;
            }

            if(!v0.manifest.dynamic) {
                return;
            }

            if(v0.manifest.minUpdatePeriodMs == v10) {
                return;
            }

            long v1 = v0.manifest.minUpdatePeriodMs;
            if(v1 == v12) {
                v1 = v3_1;
            }

            v0.scheduleManifestRefresh(Math.max(v12, v0.manifestLoadStartTimestampMs + v1 - SystemClock.elapsedRealtime()));
        }
    }

    public void releasePeriod(MediaPeriod arg2) {
        ((DashMediaPeriod)arg2).release();
        this.periodsById.remove(((DashMediaPeriod)arg2).id);
    }

    public void releaseSourceInternal() {
        this.manifestLoadPending = false;
        DataSource v1 = null;
        this.dataSource = v1;
        if(this.loader != null) {
            this.loader.release();
            this.loader = ((Loader)v1);
        }

        long v2 = 0;
        this.manifestLoadStartTimestampMs = v2;
        this.manifestLoadEndTimestampMs = v2;
        DashManifest v4 = this.sideloadedManifest ? this.manifest : ((DashManifest)v1);
        this.manifest = v4;
        this.manifestUri = this.initialManifestUri;
        this.manifestFatalError = ((IOException)v1);
        if(this.handler != null) {
            this.handler.removeCallbacksAndMessages(v1);
            this.handler = ((Handler)v1);
        }

        this.elapsedRealtimeOffsetMs = v2;
        this.staleManifestReloadAttempt = 0;
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        this.dynamicMediaPresentationEnded = false;
        this.firstPeriodId = 0;
        this.periodsById.clear();
    }

    public void replaceManifestUri(Uri arg2) {
        Object v0 = this.manifestUriLock;
        __monitor_enter(v0);
        try {
            this.manifestUri = arg2;
            this.initialManifestUri = arg2;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_7;
        }

        throw v2;
    }

    private void resolveUtcTimingElement(UtcTimingElement arg3) {
        Iso8601Parser v0_2;
        String v0 = arg3.schemeIdUri;
        if((Util.areEqual(v0, "urn:mpeg:dash:utc:direct:2014")) || (Util.areEqual(v0, "urn:mpeg:dash:utc:direct:2012"))) {
            this.resolveUtcTimingElementDirect(arg3);
        }
        else {
            if((Util.areEqual(v0, "urn:mpeg:dash:utc:http-iso:2014")) || (Util.areEqual(v0, "urn:mpeg:dash:utc:http-iso:2012"))) {
                v0_2 = new Iso8601Parser();
            }
            else {
                if(!Util.areEqual(v0, "urn:mpeg:dash:utc:http-xsdate:2014")) {
                    if(Util.areEqual(v0, "urn:mpeg:dash:utc:http-xsdate:2012")) {
                    }
                    else {
                        this.onUtcTimestampResolutionError(new IOException("Unsupported UTC timing scheme"));
                        return;
                    }
                }

                XsDateTimeParser v0_1 = new XsDateTimeParser(null);
            }

            this.resolveUtcTimingElementHttp(arg3, ((Parser)v0_2));
        }
    }

    private void resolveUtcTimingElementDirect(UtcTimingElement arg5) {
        try {
            this.onUtcTimestampResolved(Util.parseXsDateTime(arg5.value) - this.manifestLoadEndTimestampMs);
        }
        catch(ParserException v5) {
            this.onUtcTimestampResolutionError(((IOException)v5));
        }
    }

    private void resolveUtcTimingElementHttp(UtcTimingElement arg4, Parser arg5) {
        this.startLoading(new ParsingLoadable(this.dataSource, Uri.parse(arg4.value), 5, arg5), new UtcTimestampCallback(this, null), 1);
    }

    private void scheduleManifestRefresh(long arg3) {
        this.handler.postDelayed(this.refreshManifestRunnable, arg3);
    }

    private void startLoading(ParsingLoadable arg8, Callback arg9, int arg10) {
        this.manifestEventDispatcher.loadStarted(arg8.dataSpec, arg8.dataSpec.uri, arg8.type, this.loader.startLoading(((Loadable)arg8), arg9, arg10));
    }

    private void startLoadingManifest() {
        Uri v1_1;
        this.handler.removeCallbacks(this.refreshManifestRunnable);
        if(this.loader.isLoading()) {
            this.manifestLoadPending = true;
            return;
        }

        Object v0 = this.manifestUriLock;
        __monitor_enter(v0);
        try {
            v1_1 = this.manifestUri;
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_25:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_25;
            }

            throw v1;
        }

        this.manifestLoadPending = false;
        this.startLoading(new ParsingLoadable(this.dataSource, v1_1, 4, this.manifestParser), this.manifestCallback, this.minLoadableRetryCount);
    }
}

