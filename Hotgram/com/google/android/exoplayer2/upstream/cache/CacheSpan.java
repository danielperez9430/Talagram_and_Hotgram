package com.google.android.exoplayer2.upstream.cache;

import java.io.File;

public class CacheSpan implements Comparable {
    public final File file;
    public final boolean isCached;
    public final String key;
    public final long lastAccessTimestamp;
    public final long length;
    public final long position;

    public CacheSpan(String arg10, long arg11, long arg13) {
        this(arg10, arg11, arg13, -9223372036854775807L, null);
    }

    public CacheSpan(String arg1, long arg2, long arg4, long arg6, File arg8) {
        super();
        this.key = arg1;
        this.position = arg2;
        this.length = arg4;
        boolean v1 = arg8 != null ? true : false;
        this.isCached = v1;
        this.file = arg8;
        this.lastAccessTimestamp = arg6;
    }

    public int compareTo(CacheSpan arg5) {
        int v5;
        if(!this.key.equals(arg5.key)) {
            return this.key.compareTo(arg5.key);
        }

        long v0 = this.position - arg5.position;
        long v2 = 0;
        if(v0 == v2) {
            v5 = 0;
        }
        else if(v0 < v2) {
            v5 = -1;
        }
        else {
            v5 = 1;
        }

        return v5;
    }

    public int compareTo(Object arg1) {
        return this.compareTo(((CacheSpan)arg1));
    }

    public boolean isHoleSpan() {
        return this.isCached ^ 1;
    }

    public boolean isOpenEnded() {
        boolean v0 = this.length == -1 ? true : false;
        return v0;
    }
}

