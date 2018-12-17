package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class CacheDataSink implements DataSink {
    public class CacheDataSinkException extends CacheException {
        public CacheDataSinkException(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    public static final int DEFAULT_BUFFER_SIZE = 20480;
    private final int bufferSize;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private final Cache cache;
    private DataSpec dataSpec;
    private long dataSpecBytesWritten;
    private File file;
    private final long maxCacheFileSize;
    private OutputStream outputStream;
    private long outputStreamBytesWritten;
    private final boolean syncFileDescriptor;
    private FileOutputStream underlyingFileOutputStream;

    public CacheDataSink(Cache arg7, long arg8) {
        this(arg7, arg8, 20480, true);
    }

    public CacheDataSink(Cache arg1, long arg2, int arg4, boolean arg5) {
        super();
        this.cache = Assertions.checkNotNull(arg1);
        this.maxCacheFileSize = arg2;
        this.bufferSize = arg4;
        this.syncFileDescriptor = arg5;
    }

    public CacheDataSink(Cache arg7, long arg8, int arg10) {
        this(arg7, arg8, arg10, true);
    }

    public CacheDataSink(Cache arg7, long arg8, boolean arg10) {
        this(arg7, arg8, 20480, arg10);
    }

    public void close() {
        if(this.dataSpec == null) {
            return;
        }

        try {
            this.closeCurrentOutputStream();
            return;
        }
        catch(IOException v0) {
            throw new CacheDataSinkException(v0);
        }
    }

    private void closeCurrentOutputStream() {
        if(this.outputStream == null) {
            return;
        }

        OutputStream v0 = null;
        try {
            this.outputStream.flush();
            if(this.syncFileDescriptor) {
                this.underlyingFileOutputStream.getFD().sync();
            }
        }
        catch(Throwable v1) {
            Util.closeQuietly(this.outputStream);
            this.outputStream = v0;
            File v2 = this.file;
            this.file = ((File)v0);
            v2.delete();
            throw v1;
        }

        Util.closeQuietly(this.outputStream);
        this.outputStream = v0;
        File v1_1 = this.file;
        this.file = ((File)v0);
        this.cache.commitFile(v1_1);
    }

    public void open(DataSpec arg6) {
        if(arg6.length == -1 && !arg6.isFlagSet(2)) {
            this.dataSpec = null;
            return;
        }

        this.dataSpec = arg6;
        this.dataSpecBytesWritten = 0;
        try {
            this.openNextOutputStream();
            return;
        }
        catch(IOException v6) {
            throw new CacheDataSinkException(v6);
        }
    }

    private void openNextOutputStream() {
        ReusableBufferedOutputStream v0_1;
        long v0 = this.dataSpec.length == -1 ? this.maxCacheFileSize : Math.min(this.dataSpec.length - this.dataSpecBytesWritten, this.maxCacheFileSize);
        long v6 = v0;
        this.file = this.cache.startFile(this.dataSpec.key, this.dataSpecBytesWritten + this.dataSpec.absoluteStreamPosition, v6);
        this.underlyingFileOutputStream = new FileOutputStream(this.file);
        if(this.bufferSize > 0) {
            if(this.bufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(this.underlyingFileOutputStream, this.bufferSize);
            }
            else {
                this.bufferedOutputStream.reset(this.underlyingFileOutputStream);
            }

            v0_1 = this.bufferedOutputStream;
        }
        else {
            FileOutputStream v0_2 = this.underlyingFileOutputStream;
        }

        this.outputStream = ((OutputStream)v0_1);
        this.outputStreamBytesWritten = 0;
    }

    public void write(byte[] arg9, int arg10, int arg11) {
        if(this.dataSpec == null) {
            return;
        }

        int v0 = 0;
        while(v0 < arg11) {
            try {
                if(this.outputStreamBytesWritten == this.maxCacheFileSize) {
                    this.closeCurrentOutputStream();
                    this.openNextOutputStream();
                }

                int v1 = ((int)Math.min(((long)(arg11 - v0)), this.maxCacheFileSize - this.outputStreamBytesWritten));
                this.outputStream.write(arg9, arg10 + v0, v1);
                v0 += v1;
                long v4 = ((long)v1);
                this.outputStreamBytesWritten += v4;
                this.dataSpecBytesWritten += v4;
                continue;
            }
            catch(IOException v9) {
                throw new CacheDataSinkException(v9);
            }
        }
    }
}

