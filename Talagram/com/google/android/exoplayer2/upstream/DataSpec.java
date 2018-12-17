package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class DataSpec {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    public static final int FLAG_ALLOW_CACHING_UNKNOWN_LENGTH = 2;
    public static final int FLAG_ALLOW_GZIP = 1;
    public final long absoluteStreamPosition;
    public final int flags;
    public final String key;
    public final long length;
    public final long position;
    public final byte[] postBody;
    public final Uri uri;

    public DataSpec(Uri arg11, long arg12, long arg14, String arg16) {
        this(arg11, arg12, arg12, arg14, arg16, 0);
    }

    public DataSpec(Uri arg11, long arg12, long arg14, String arg16, int arg17) {
        this(arg11, arg12, arg12, arg14, arg16, arg17);
    }

    public DataSpec(Uri arg2) {
        this(arg2, 0);
    }

    public DataSpec(Uri arg9, int arg10) {
        this(arg9, 0, -1, null, arg10);
    }

    public DataSpec(Uri arg12, long arg13, long arg15, long arg17, String arg19, int arg20) {
        this(arg12, null, arg13, arg15, arg17, arg19, arg20);
    }

    public DataSpec(Uri arg6, byte[] arg7, long arg8, long arg10, long arg12, String arg14, int arg15) {
        super();
        long v0 = 0;
        boolean v3 = false;
        boolean v2 = Long.compare(arg8, v0) >= 0 ? true : false;
        Assertions.checkArgument(v2);
        v2 = arg10 >= v0 ? true : false;
        Assertions.checkArgument(v2);
        if(arg12 > v0 || arg12 == -1) {
            v3 = true;
        }

        Assertions.checkArgument(v3);
        this.uri = arg6;
        this.postBody = arg7;
        this.absoluteStreamPosition = arg8;
        this.position = arg10;
        this.length = arg12;
        this.key = arg14;
        this.flags = arg15;
    }

    public boolean isFlagSet(int arg2) {
        boolean v2 = (this.flags & arg2) == arg2 ? true : false;
        return v2;
    }

    public DataSpec subrange(long arg6) {
        long v2 = -1;
        if(this.length == v2) {
        }
        else {
            v2 = this.length - arg6;
        }

        return this.subrange(arg6, v2);
    }

    public DataSpec subrange(long arg15, long arg17) {
        DataSpec v0 = this;
        if(arg15 == 0 && v0.length == arg17) {
            return v0;
        }

        return new DataSpec(v0.uri, v0.postBody, v0.absoluteStreamPosition + arg15, v0.position + arg15, arg17, v0.key, v0.flags);
    }

    public String toString() {
        return "DataSpec[" + this.uri + ", " + Arrays.toString(this.postBody) + ", " + this.absoluteStreamPosition + ", " + this.position + ", " + this.length + ", " + this.key + ", " + this.flags + "]";
    }

    public DataSpec withUri(Uri arg13) {
        return new DataSpec(arg13, this.postBody, this.absoluteStreamPosition, this.position, this.length, this.key, this.flags);
    }
}

