package com.googlecode.mp4parser;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel$MapMode;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class FileDataSourceImpl implements DataSource {
    FileChannel fc;
    String filename;

    public FileDataSourceImpl(FileChannel arg1) {
        super();
        this.fc = arg1;
        this.filename = "unknown";
    }

    public FileDataSourceImpl(File arg2) {
        super();
        this.fc = new FileInputStream(arg2).getChannel();
        this.filename = arg2.getName();
    }

    public FileDataSourceImpl(String arg2) {
        super();
        File v0 = new File(arg2);
        this.fc = new FileInputStream(v0).getChannel();
        this.filename = v0.getName();
    }

    public FileDataSourceImpl(FileChannel arg1, String arg2) {
        super();
        this.fc = arg1;
        this.filename = arg2;
    }

    public void close() {
        this.fc.close();
    }

    public ByteBuffer map(long arg7, long arg9) {
        return this.fc.map(FileChannel$MapMode.READ_ONLY, arg7, arg9);
    }

    public long position() {
        return this.fc.position();
    }

    public void position(long arg2) {
        this.fc.position(arg2);
    }

    public int read(ByteBuffer arg2) {
        return this.fc.read(arg2);
    }

    public long size() {
        return this.fc.size();
    }

    public String toString() {
        return this.filename;
    }

    public long transferTo(long arg7, long arg9, WritableByteChannel arg11) {
        return this.fc.transferTo(arg7, arg9, arg11);
    }
}

