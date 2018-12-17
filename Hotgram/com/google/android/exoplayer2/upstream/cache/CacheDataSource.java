package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource$-CC;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

public final class CacheDataSource implements DataSource {
    @Retention(value=RetentionPolicy.SOURCE) @public interface CacheIgnoredReason {
    }

    public interface EventListener {
        void onCacheIgnored(int arg1);

        void onCachedBytesRead(long arg1, long arg2);
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    public static final int CACHE_IGNORED_REASON_ERROR = 0;
    public static final int CACHE_IGNORED_REASON_UNSET_LENGTH = 1;
    private static final int CACHE_NOT_IGNORED = -1;
    public static final long DEFAULT_MAX_CACHE_FILE_SIZE = 2097152;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    private static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;
    private Uri actualUri;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final CacheKeyFactory cacheKeyFactory;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    private DataSource currentDataSource;
    private boolean currentDataSpecLengthUnset;
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    private final EventListener eventListener;
    private int flags;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private String key;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    public CacheDataSource(Cache arg9, DataSource arg10, DataSource arg11, DataSink arg12, int arg13, EventListener arg14) {
        this(arg9, arg10, arg11, arg12, arg13, arg14, null);
    }

    public CacheDataSource(Cache arg7, DataSource arg8) {
        this(arg7, arg8, 0, 2097152);
    }

    public CacheDataSource(Cache arg8, DataSource arg9, int arg10, long arg11) {
        this(arg8, arg9, new FileDataSource(), new CacheDataSink(arg8, arg11), arg10, null);
    }

    public CacheDataSource(Cache arg7, DataSource arg8, int arg9) {
        this(arg7, arg8, arg9, 2097152);
    }

    public CacheDataSource(Cache arg1, DataSource arg2, DataSource arg3, DataSink arg4, int arg5, EventListener arg6, CacheKeyFactory arg7) {
        DataSource v1_2;
        super();
        this.cache = arg1;
        this.cacheReadDataSource = arg3;
        if(arg7 != null) {
        }
        else {
            arg7 = CacheUtil.DEFAULT_CACHE_KEY_FACTORY;
        }

        this.cacheKeyFactory = arg7;
        boolean v3 = false;
        boolean v1 = (arg5 & 1) != 0 ? true : false;
        this.blockOnCache = v1;
        v1 = (arg5 & 2) != 0 ? true : false;
        this.ignoreCacheOnError = v1;
        if((arg5 & 4) != 0) {
            v3 = true;
        }

        this.ignoreCacheForUnsetLengthRequests = v3;
        this.upstreamDataSource = arg2;
        if(arg4 != null) {
            TeeDataSource v1_1 = new TeeDataSource(arg2, arg4);
        }
        else {
            v1_2 = null;
        }

        this.cacheWriteDataSource = v1_2;
        this.eventListener = arg6;
    }

    public void addTransferListener(TransferListener arg2) {
        this.cacheReadDataSource.addTransferListener(arg2);
        this.upstreamDataSource.addTransferListener(arg2);
    }

    public void close() {
        this.uri = null;
        this.actualUri = null;
        this.notifyBytesRead();
        try {
            this.closeCurrentSource();
            return;
        }
        catch(IOException v0) {
            this.handleBeforeThrow(v0);
            throw v0;
        }
    }

    private void closeCurrentSource() {
        if(this.currentDataSource == null) {
            return;
        }

        DataSource v1 = null;
        try {
            this.currentDataSource.close();
        }
        catch(Throwable v2) {
            this.currentDataSource = v1;
            this.currentDataSpecLengthUnset = false;
            if(this.currentHoleSpan != null) {
                this.cache.releaseHoleSpan(this.currentHoleSpan);
                this.currentHoleSpan = ((CacheSpan)v1);
            }

            throw v2;
        }

        this.currentDataSource = v1;
        this.currentDataSpecLengthUnset = false;
        if(this.currentHoleSpan != null) {
            this.cache.releaseHoleSpan(this.currentHoleSpan);
            this.currentHoleSpan = ((CacheSpan)v1);
        }
    }

    private static Uri getRedirectedUriOrDefault(Cache arg0, String arg1, Uri arg2) {
        Uri v0 = ContentMetadataInternal.getRedirectedUri(arg0.getContentMetadata(arg1));
        if(v0 == null) {
            v0 = arg2;
        }

        return v0;
    }

    public Map getResponseHeaders() {
        Map v0 = this.isReadingFromUpstream() ? this.upstreamDataSource.getResponseHeaders() : DataSource$-CC.$default$getResponseHeaders(((DataSource)this));
        return v0;
    }

    public Uri getUri() {
        return this.actualUri;
    }

    private void handleBeforeThrow(IOException arg2) {
        if((this.isReadingFromCache()) || ((arg2 instanceof CacheException))) {
            this.seenCacheError = true;
        }
    }

    private boolean isBypassingCache() {
        boolean v0 = this.currentDataSource == this.upstreamDataSource ? true : false;
        return v0;
    }

    private static boolean isCausedByPositionOutOfRange(IOException arg1) {
        while(arg1 != null) {
            if(((arg1 instanceof DataSourceException)) && arg1.reason == 0) {
                return 1;
            }

            Throwable v1 = v1.getCause();
        }

        return 0;
    }

    private boolean isReadingFromCache() {
        boolean v0 = this.currentDataSource == this.cacheReadDataSource ? true : false;
        return v0;
    }

    private boolean isReadingFromUpstream() {
        return this.isReadingFromCache() ^ 1;
    }

    private boolean isWritingToCache() {
        boolean v0 = this.currentDataSource == this.cacheWriteDataSource ? true : false;
        return v0;
    }

    private void notifyBytesRead() {
        if(this.eventListener != null) {
            long v2 = 0;
            if(this.totalCachedBytesRead > v2) {
                this.eventListener.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
                this.totalCachedBytesRead = v2;
            }
        }
    }

    private void notifyCacheIgnored(int arg2) {
        if(this.eventListener != null) {
            this.eventListener.onCacheIgnored(arg2);
        }
    }

    public long open(DataSpec arg7) {
        try {
            this.key = this.cacheKeyFactory.buildCacheKey(arg7);
            this.uri = arg7.uri;
            this.actualUri = CacheDataSource.getRedirectedUriOrDefault(this.cache, this.key, this.uri);
            this.flags = arg7.flags;
            this.readPosition = arg7.position;
            int v0 = this.shouldIgnoreCacheForRequest(arg7);
            boolean v1 = v0 != -1 ? true : false;
            this.currentRequestIgnoresCache = v1;
            if(this.currentRequestIgnoresCache) {
                this.notifyCacheIgnored(v0);
            }

            long v3 = -1;
            if(arg7.length != v3 || (this.currentRequestIgnoresCache)) {
                this.bytesRemaining = arg7.length;
            }
            else {
                this.bytesRemaining = this.cache.getContentLength(this.key);
                if(this.bytesRemaining != v3) {
                    this.bytesRemaining -= arg7.position;
                    if(this.bytesRemaining > 0) {
                    }
                    else {
                        throw new DataSourceException(0);
                    }
                }
            }

            this.openNextSource(false);
            return this.bytesRemaining;
        }
        catch(IOException v7) {
            this.handleBeforeThrow(v7);
            throw v7;
        }
    }

    private void openNextSource(boolean arg16) {
        // Method was not decompiled
    }

    public int read(byte[] arg11, int arg12, int arg13) {
        // Method was not decompiled
    }

    private void setNoBytesRemainingAndMaybeStoreLength() {
        this.bytesRemaining = 0;
        if(this.isWritingToCache()) {
            this.cache.setContentLength(this.key, this.readPosition);
        }
    }

    private int shouldIgnoreCacheForRequest(DataSpec arg5) {
        if((this.ignoreCacheOnError) && (this.seenCacheError)) {
            return 0;
        }

        if((this.ignoreCacheForUnsetLengthRequests) && arg5.length == -1) {
            return 1;
        }

        return -1;
    }
}

