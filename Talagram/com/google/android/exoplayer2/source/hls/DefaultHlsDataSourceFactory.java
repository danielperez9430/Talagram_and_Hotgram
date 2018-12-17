package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.DataSource;

public final class DefaultHlsDataSourceFactory implements HlsDataSourceFactory {
    private final Factory dataSourceFactory;

    public DefaultHlsDataSourceFactory(Factory arg1) {
        super();
        this.dataSourceFactory = arg1;
    }

    public DataSource createDataSource(int arg1) {
        return this.dataSourceFactory.createDataSource();
    }
}

