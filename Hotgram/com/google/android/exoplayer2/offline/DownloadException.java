package com.google.android.exoplayer2.offline;

import java.io.IOException;

public final class DownloadException extends IOException {
    public DownloadException(String arg1) {
        super(arg1);
    }

    public DownloadException(Throwable arg1) {
        super(arg1);
    }
}

