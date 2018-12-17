package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.util.UriUtil;

public final class RangedUri {
    private int hashCode;
    public final long length;
    private final String referenceUri;
    public final long start;

    public RangedUri(String arg1, long arg2, long arg4) {
        super();
        if(arg1 == null) {
            arg1 = "";
        }

        this.referenceUri = arg1;
        this.start = arg2;
        this.length = arg4;
    }

    public RangedUri attemptMerge(RangedUri arg9, String arg10) {
        long v2;
        RangedUri v10;
        String v1 = this.resolveUriString(arg10);
        RangedUri v0 = null;
        if(arg9 != null) {
            if(!v1.equals(arg9.resolveUriString(arg10))) {
            }
            else {
                long v4 = -1;
                if(this.length != v4 && this.start + this.length == arg9.start) {
                    v10 = null;
                    v2 = this.start;
                    if(arg9.length == v4) {
                    }
                    else {
                        v4 = this.length + arg9.length;
                    }

                    this(v1, v2, v4);
                    return v10;
                }

                if(arg9.length == v4) {
                    return v0;
                }

                if(arg9.start + arg9.length != this.start) {
                    return v0;
                }

                v10 = null;
                v2 = arg9.start;
                if(this.length == v4) {
                }
                else {
                    v4 = arg9.length + this.length;
                }

                this(v1, v2, v4);
                return v10;
            }
        }

        return v0;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((RangedUri)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else {
                if(this.start != ((RangedUri)arg8).start || this.length != ((RangedUri)arg8).length || !this.referenceUri.equals(((RangedUri)arg8).referenceUri)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            this.hashCode = ((527 + (((int)this.start))) * 31 + (((int)this.length))) * 31 + this.referenceUri.hashCode();
        }

        return this.hashCode;
    }

    public Uri resolveUri(String arg2) {
        return UriUtil.resolveToUri(arg2, this.referenceUri);
    }

    public String resolveUriString(String arg2) {
        return UriUtil.resolve(arg2, this.referenceUri);
    }

    public String toString() {
        return "RangedUri(referenceUri=" + this.referenceUri + ", start=" + this.start + ", length=" + this.length + ")";
    }
}

