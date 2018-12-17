package com.google.android.exoplayer2.upstream;

public final class FileDataSourceFactory implements Factory {
    private final TransferListener listener;

    public FileDataSourceFactory() {
        this(null);
    }

    public FileDataSourceFactory(TransferListener arg1) {
        super();
        this.listener = arg1;
    }

    public DataSource createDataSource() {
        return new FileDataSource(this.listener);
    }
}

