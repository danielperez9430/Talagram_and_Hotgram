package com.google.android.exoplayer2.upstream;

import android.content.Context;

public final class DefaultDataSourceFactory implements Factory {
    private final Factory baseDataSourceFactory;
    private final Context context;
    private final TransferListener listener;

    public DefaultDataSourceFactory(Context arg2, Factory arg3) {
        this(arg2, null, arg3);
    }

    public DefaultDataSourceFactory(Context arg1, TransferListener arg2, Factory arg3) {
        super();
        this.context = arg1.getApplicationContext();
        this.listener = arg2;
        this.baseDataSourceFactory = arg3;
    }

    public DefaultDataSourceFactory(Context arg2, String arg3) {
        this(arg2, arg3, null);
    }

    public DefaultDataSourceFactory(Context arg2, String arg3, TransferListener arg4) {
        this(arg2, arg4, new DefaultHttpDataSourceFactory(arg3, arg4));
    }

    public DataSource createDataSource() {
        return this.createDataSource();
    }

    public DefaultDataSource createDataSource() {
        return new DefaultDataSource(this.context, this.listener, this.baseDataSourceFactory.createDataSource());
    }
}

