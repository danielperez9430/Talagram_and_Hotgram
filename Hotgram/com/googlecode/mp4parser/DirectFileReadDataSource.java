package com.googlecode.mp4parser;

import com.googlecode.mp4parser.util.CastUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class DirectFileReadDataSource implements DataSource {
    private static final int TRANSFER_SIZE = 8192;
    private String filename;
    private RandomAccessFile raf;

    public DirectFileReadDataSource(File arg3) {
        super();
        this.raf = new RandomAccessFile(arg3, "r");
        this.filename = arg3.getName();
    }

    public void close() {
        this.raf.close();
    }

    public ByteBuffer map(long arg2, long arg4) {
        this.raf.seek(arg2);
        byte[] v2 = new byte[CastUtils.l2i(arg4)];
        this.raf.readFully(v2);
        return ByteBuffer.wrap(v2);
    }

    public long position() {
        return this.raf.getFilePointer();
    }

    public void position(long arg2) {
        this.raf.seek(arg2);
    }

    public int read(ByteBuffer arg8) {
        int v0 = arg8.remaining();
        int v1 = 8192;
        byte[] v2 = new byte[v1];
        int v4 = 0;
        int v5 = 0;
        while(true) {
            if(v4 < v0) {
                v5 = this.raf.read(v2, 0, Math.min(v0 - v4, v1));
                if(v5 >= 0) {
                    v4 += v5;
                    arg8.put(v2, 0, v5);
                    continue;
                }
            }
            else {
            }

            break;
        }

        if(v5 < 0 && v4 == 0) {
            v4 = -1;
        }

        return v4;
    }

    public int readAllInOnce(ByteBuffer arg4) {
        byte[] v0 = new byte[arg4.remaining()];
        int v1 = this.raf.read(v0);
        arg4.put(v0, 0, v1);
        return v1;
    }

    public long size() {
        return this.raf.length();
    }

    public String toString() {
        return this.filename;
    }

    public long transferTo(long arg1, long arg3, WritableByteChannel arg5) {
        return ((long)arg5.write(this.map(arg1, arg3)));
    }
}

