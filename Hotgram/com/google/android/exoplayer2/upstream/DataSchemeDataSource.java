package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.Util;
import java.net.URLDecoder;

public final class DataSchemeDataSource extends BaseDataSource {
    public static final String SCHEME_DATA = "data";
    private int bytesRead;
    private byte[] data;
    private DataSpec dataSpec;

    public DataSchemeDataSource() {
        super(false);
    }

    public void close() {
        byte[] v1 = null;
        if(this.data != null) {
            this.data = v1;
            this.transferEnded();
        }

        this.dataSpec = ((DataSpec)v1);
    }

    public Uri getUri() {
        Uri v0 = this.dataSpec != null ? this.dataSpec.uri : null;
        return v0;
    }

    public long open(DataSpec arg5) {
        this.transferInitializing(arg5);
        this.dataSpec = arg5;
        Uri v0 = arg5.uri;
        String v1 = v0.getScheme();
        if("data".equals(v1)) {
            String[] v1_1 = Util.split(v0.getSchemeSpecificPart(), ",");
            if(v1_1.length == 2) {
                String v0_1 = v1_1[1];
                if(v1_1[0].contains(";base64")) {
                    try {
                        this.data = Base64.decode(v0_1, 0);
                    }
                    catch(IllegalArgumentException v5) {
                        StringBuilder v2 = new StringBuilder();
                        v2.append("Error while parsing Base64 encoded string: ");
                        v2.append(v0_1);
                        throw new ParserException(v2.toString(), ((Throwable)v5));
                    }
                }
                else {
                    this.data = URLDecoder.decode(v0_1, "US-ASCII").getBytes();
                }

                this.transferStarted(arg5);
                return ((long)this.data.length);
            }

            StringBuilder v1_2 = new StringBuilder();
            v1_2.append("Unexpected URI format: ");
            v1_2.append(v0);
            throw new ParserException(v1_2.toString());
        }

        StringBuilder v0_2 = new StringBuilder();
        v0_2.append("Unsupported scheme: ");
        v0_2.append(v1);
        throw new ParserException(v0_2.toString());
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        if(arg5 == 0) {
            return 0;
        }

        int v0 = this.data.length - this.bytesRead;
        if(v0 == 0) {
            return -1;
        }

        arg5 = Math.min(arg5, v0);
        System.arraycopy(this.data, this.bytesRead, arg3, arg4, arg5);
        this.bytesRead += arg5;
        this.bytesTransferred(arg5);
        return arg5;
    }
}

