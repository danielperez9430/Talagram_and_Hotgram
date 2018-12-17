package com.google.android.exoplayer2.upstream;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class ContentDataSource extends BaseDataSource {
    public class ContentDataSourceException extends IOException {
        public ContentDataSourceException(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private FileInputStream inputStream;
    private boolean opened;
    private final ContentResolver resolver;
    private Uri uri;

    public ContentDataSource(Context arg2) {
        this(arg2, null);
    }

    public ContentDataSource(Context arg2, TransferListener arg3) {
        super(false);
        this.resolver = arg2.getContentResolver();
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
                throw new ContentDataSourceException(v2_1);
            }
            catch(Throwable v2) {
                this.inputStream = ((FileInputStream)v0);
                try {
                    if(this.assetFileDescriptor != null) {
                        this.assetFileDescriptor.close();
                    }
                }
                catch(Throwable v2) {
                }
                catch(IOException v2_1) {
                    try {
                        throw new ContentDataSourceException(v2_1);
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

        this.inputStream = ((FileInputStream)v0);
        try {
            if(this.assetFileDescriptor != null) {
                this.assetFileDescriptor.close();
            }
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            try {
                throw new ContentDataSourceException(v2_1);
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

    public long open(DataSpec arg9) {
        try {
            this.uri = arg9.uri;
            this.transferInitializing(arg9);
            this.assetFileDescriptor = this.resolver.openAssetFileDescriptor(this.uri, "r");
            if(this.assetFileDescriptor == null) {
                goto label_58;
            }

            this.inputStream = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
            long v0 = this.assetFileDescriptor.getStartOffset();
            long v2 = this.inputStream.skip(arg9.position + v0) - v0;
            if(v2 != arg9.position) {
                goto label_55;
            }

            long v4 = -1;
            if(arg9.length != v4) {
                this.bytesRemaining = arg9.length;
            }
            else {
                v0 = this.assetFileDescriptor.getLength();
                if(v0 == v4) {
                    FileChannel v0_1 = this.inputStream.getChannel();
                    long v1 = v0_1.size();
                    if(v1 == 0) {
                        v1 = v4;
                    }
                    else {
                        v1 -= v0_1.position();
                    }

                    this.bytesRemaining = v1;
                }
                else {
                    this.bytesRemaining = v0 - v2;
                }
            }
        }
        catch(IOException v9) {
            goto label_71;
        }

        this.opened = true;
        this.transferStarted(arg9);
        return this.bytesRemaining;
        try {
        label_55:
            throw new EOFException();
        label_58:
            StringBuilder v0_2 = new StringBuilder();
            v0_2.append("Could not open file descriptor for: ");
            v0_2.append(this.uri);
            throw new FileNotFoundException(v0_2.toString());
        }
        catch(IOException v9) {
        label_71:
            throw new ContentDataSourceException(v9);
        }
    }

    public int read(byte[] arg8, int arg9, int arg10) {
        // Method was not decompiled
    }
}

