package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.InputStream;

public final class ParsingLoadable implements Loadable {
    public interface Parser {
        Object parse(Uri arg1, InputStream arg2);
    }

    private final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    private final Parser parser;
    private volatile Object result;
    public final int type;

    public ParsingLoadable(DataSource arg3, Uri arg4, int arg5, Parser arg6) {
        this(arg3, new DataSpec(arg4, 3), arg5, arg6);
    }

    public ParsingLoadable(DataSource arg2, DataSpec arg3, int arg4, Parser arg5) {
        super();
        this.dataSource = new StatsDataSource(arg2);
        this.dataSpec = arg3;
        this.type = arg4;
        this.parser = arg5;
    }

    public long bytesLoaded() {
        return this.dataSource.getBytesRead();
    }

    public final void cancelLoad() {
    }

    public final Object getResult() {
        return this.result;
    }

    public Uri getUri() {
        return this.dataSource.getLastOpenedUri();
    }

    public static Object load(DataSource arg1, Parser arg2, Uri arg3, int arg4) {
        ParsingLoadable v0 = new ParsingLoadable(arg1, arg3, arg4, arg2);
        v0.load();
        return Assertions.checkNotNull(v0.getResult());
    }

    public final void load() {
        this.dataSource.resetBytesRead();
        DataSourceInputStream v0 = new DataSourceInputStream(this.dataSource, this.dataSpec);
        try {
            v0.open();
            this.result = this.parser.parse(Assertions.checkNotNull(this.dataSource.getUri()), ((InputStream)v0));
        }
        catch(Throwable v1) {
            Util.closeQuietly(((Closeable)v0));
            throw v1;
        }

        Util.closeQuietly(((Closeable)v0));
    }
}

