package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileDataSource extends BaseDataSource {
    public class FileDataSourceException extends IOException {
        public FileDataSourceException(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    private long bytesRemaining;
    private RandomAccessFile file;
    private boolean opened;
    private Uri uri;

    public FileDataSource() {
        this(null);
    }

    public FileDataSource(TransferListener arg2) {
        super(false);
        if(arg2 != null) {
            this.addTransferListener(arg2);
        }
    }

    public void close() {
        Uri v0 = null;
        this.uri = v0;
        try {
            if(this.file != null) {
                this.file.close();
            }
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            try {
                throw new FileDataSourceException(v2_1);
            }
            catch(Throwable v2) {
                this.file = ((RandomAccessFile)v0);
                if(this.opened) {
                    this.opened = false;
                    this.transferEnded();
                }

                throw v2;
            }
        }

        this.file = ((RandomAccessFile)v0);
        if(this.opened) {
            this.opened = false;
            this.transferEnded();
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec arg6) {
        try {
            this.uri = arg6.uri;
            this.transferInitializing(arg6);
            this.file = new RandomAccessFile(arg6.uri.getPath(), "r");
            this.file.seek(arg6.position);
            long v0 = arg6.length == -1 ? this.file.length() - arg6.position : arg6.length;
            this.bytesRemaining = v0;
            if(this.bytesRemaining < 0) {
                goto label_31;
            }
        }
        catch(IOException v6) {
            goto label_37;
        }

        this.opened = true;
        this.transferStarted(arg6);
        return this.bytesRemaining;
        try {
        label_31:
            throw new EOFException();
        }
        catch(IOException v6) {
        label_37:
            throw new FileDataSourceException(v6);
        }
    }

    public int read(byte[] arg6, int arg7, int arg8) {
        int v6_1;
        if(arg8 == 0) {
            return 0;
        }

        if(this.bytesRemaining == 0) {
            return -1;
        }

        try {
            v6_1 = this.file.read(arg6, arg7, ((int)Math.min(this.bytesRemaining, ((long)arg8))));
            if(v6_1 <= 0) {
                return v6_1;
            }
        }
        catch(IOException v6) {
            throw new FileDataSourceException(v6);
        }

        this.bytesRemaining -= ((long)v6_1);
        this.bytesTransferred(v6_1);
        return v6_1;
    }
}

