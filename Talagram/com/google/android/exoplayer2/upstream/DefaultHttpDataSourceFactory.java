package com.google.android.exoplayer2.upstream;

public final class DefaultHttpDataSourceFactory extends BaseFactory {
    private final boolean allowCrossProtocolRedirects;
    private final int connectTimeoutMillis;
    private final TransferListener listener;
    private final int readTimeoutMillis;
    private final String userAgent;

    public DefaultHttpDataSourceFactory(String arg7, TransferListener arg8) {
        this(arg7, arg8, 8000, 8000, false);
    }

    public DefaultHttpDataSourceFactory(String arg2) {
        this(arg2, null);
    }

    public DefaultHttpDataSourceFactory(String arg7, int arg8, int arg9, boolean arg10) {
        this(arg7, null, arg8, arg9, arg10);
    }

    public DefaultHttpDataSourceFactory(String arg1, TransferListener arg2, int arg3, int arg4, boolean arg5) {
        super();
        this.userAgent = arg1;
        this.listener = arg2;
        this.connectTimeoutMillis = arg3;
        this.readTimeoutMillis = arg4;
        this.allowCrossProtocolRedirects = arg5;
    }

    protected DefaultHttpDataSource createDataSourceInternal(RequestProperties arg10) {
        return new DefaultHttpDataSource(this.userAgent, null, this.listener, this.connectTimeoutMillis, this.readTimeoutMillis, this.allowCrossProtocolRedirects, arg10);
    }

    protected HttpDataSource createDataSourceInternal(RequestProperties arg1) {
        return this.createDataSourceInternal(arg1);
    }
}

