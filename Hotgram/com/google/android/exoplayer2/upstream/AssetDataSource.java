package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class AssetDataSource extends BaseDataSource {
    public final class AssetDataSourceException extends IOException {
        public AssetDataSourceException(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    private final AssetManager assetManager;
    private long bytesRemaining;
    private InputStream inputStream;
    private boolean opened;
    private Uri uri;

    public AssetDataSource(Context arg2) {
        this(arg2, null);
    }

    public AssetDataSource(Context arg2, TransferListener arg3) {
        super(false);
        this.assetManager = arg2.getAssets();
        if(arg3 != null) {
            this.addTransferListener(arg3);
        }
    }

    public void close() {
        Uri v0 = null;
        this.uri = v0;
        try {
            if(this.inputStream != null) {
                this.inputStream.close();
            }
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            try {
                throw new AssetDataSourceException(v2_1);
            }
            catch(Throwable v2) {
                this.inputStream = ((InputStream)v0);
                if(this.opened) {
                    this.opened = false;
                    this.transferEnded();
                }

                throw v2;
            }
        }

        this.inputStream = ((InputStream)v0);
        if(this.opened) {
            this.opened = false;
            this.transferEnded();
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec arg9) {
        try {
            this.uri = arg9.uri;
            String v0 = this.uri.getPath();
            if(v0.startsWith("/android_asset/")) {
                v0 = v0.substring(15);
            }
            else if(v0.startsWith("/")) {
                v0 = v0.substring(1);
            }

            this.transferInitializing(arg9);
            this.inputStream = this.assetManager.open(v0, 1);
            if(this.inputStream.skip(arg9.position) < arg9.position) {
                goto label_42;
            }

            long v3 = -1;
            if(arg9.length != v3) {
                this.bytesRemaining = arg9.length;
            }
            else {
                this.bytesRemaining = ((long)this.inputStream.available());
                if(this.bytesRemaining == 2147483647) {
                    this.bytesRemaining = v3;
                }
            }
        }
        catch(IOException v9) {
            goto label_48;
        }

        this.opened = true;
        this.transferStarted(arg9);
        return this.bytesRemaining;
        try {
        label_42:
            throw new EOFException();
        }
        catch(IOException v9) {
        label_48:
            throw new AssetDataSourceException(v9);
        }
    }

    public int read(byte[] arg8, int arg9, int arg10) {
        // Method was not decompiled
    }
}

