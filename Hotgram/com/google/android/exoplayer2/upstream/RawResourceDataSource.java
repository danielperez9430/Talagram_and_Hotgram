package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class RawResourceDataSource extends BaseDataSource {
    public class RawResourceDataSourceException extends IOException {
        public RawResourceDataSourceException(IOException arg1) {
            super(((Throwable)arg1));
        }

        public RawResourceDataSourceException(String arg1) {
            super(arg1);
        }
    }

    public static final String RAW_RESOURCE_SCHEME = "rawresource";
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private InputStream inputStream;
    private boolean opened;
    private final Resources resources;
    private Uri uri;

    public RawResourceDataSource(Context arg2) {
        this(arg2, null);
    }

    public RawResourceDataSource(Context arg2, TransferListener arg3) {
        super(false);
        this.resources = arg2.getResources();
        if(arg3 != null) {
            this.addTransferListener(arg3);
        }
    }

    public static Uri buildRawResourceUri(int arg2) {
        StringBuilder v0 = new StringBuilder();
        v0.append("rawresource:///");
        v0.append(arg2);
        return Uri.parse(v0.toString());
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
                throw new RawResourceDataSourceException(v2_1);
            }
            catch(Throwable v2) {
                this.inputStream = ((InputStream)v0);
                try {
                    if(this.assetFileDescriptor != null) {
                        this.assetFileDescriptor.close();
                    }
                }
                catch(Throwable v2) {
                }
                catch(IOException v2_1) {
                    try {
                        throw new RawResourceDataSourceException(v2_1);
                    }
                    catch(Throwable v2) {
                        this.assetFileDescriptor = ((AssetFileDescriptor)v0);
                        if(this.opened) {
                            this.opened = false;
                            this.transferEnded();
                        }

                        throw v2;
                    }
                }

                this.assetFileDescriptor = ((AssetFileDescriptor)v0);
                if(this.opened) {
                    this.opened = false;
                    this.transferEnded();
                }

                throw v2;
            }
        }

        this.inputStream = ((InputStream)v0);
        try {
            if(this.assetFileDescriptor != null) {
                this.assetFileDescriptor.close();
            }
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            try {
                throw new RawResourceDataSourceException(v2_1);
            }
            catch(Throwable v2) {
                this.assetFileDescriptor = ((AssetFileDescriptor)v0);
                if(this.opened) {
                    this.opened = false;
                    this.transferEnded();
                }

                throw v2;
            }
        }

        this.assetFileDescriptor = ((AssetFileDescriptor)v0);
        if(this.opened) {
            this.opened = false;
            this.transferEnded();
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec arg6) {
        int v0;
        try {
            this.uri = arg6.uri;
            if(!TextUtils.equals("rawresource", this.uri.getScheme())) {
                goto label_54;
            }

            try {
                v0 = Integer.parseInt(this.uri.getLastPathSegment());
                goto label_10;
            }
            catch(NumberFormatException ) {
                try {
                    throw new RawResourceDataSourceException("Resource identifier must be an integer.");
                label_10:
                    this.transferInitializing(arg6);
                    this.assetFileDescriptor = this.resources.openRawResourceFd(v0);
                    this.inputStream = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
                    this.inputStream.skip(this.assetFileDescriptor.getStartOffset());
                    if(this.inputStream.skip(arg6.position) < arg6.position) {
                        goto label_47;
                    }

                    long v2 = -1;
                    if(arg6.length != v2) {
                        this.bytesRemaining = arg6.length;
                    }
                    else {
                        long v0_1 = this.assetFileDescriptor.getLength();
                        if(v0_1 == v2) {
                        }
                        else {
                            v2 = v0_1 - arg6.position;
                        }

                        this.bytesRemaining = v2;
                    }
                }
                catch(IOException v6) {
                    goto label_61;
                }
            }
        }
        catch(IOException v6) {
            goto label_61;
        }

        this.opened = true;
        this.transferStarted(arg6);
        return this.bytesRemaining;
        try {
        label_47:
            throw new EOFException();
        label_54:
            throw new RawResourceDataSourceException("URI must use scheme rawresource");
        }
        catch(IOException v6) {
        label_61:
            throw new RawResourceDataSourceException(v6);
        }
    }

    public int read(byte[] arg8, int arg9, int arg10) {
        // Method was not decompiled
    }
}

