package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultHttpDataSource extends BaseDataSource implements HttpDataSource {
    private static final Pattern CONTENT_RANGE_HEADER = null;
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    private static final long MAX_BYTES_TO_DRAIN = 2048;
    private static final int MAX_REDIRECTS = 20;
    private static final String TAG = "DefaultHttpDataSource";
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    private final int connectTimeoutMillis;
    private HttpURLConnection connection;
    private final Predicate contentTypePredicate;
    private DataSpec dataSpec;
    private final RequestProperties defaultRequestProperties;
    private InputStream inputStream;
    private boolean opened;
    private final int readTimeoutMillis;
    private final RequestProperties requestProperties;
    private static final AtomicReference skipBufferReference;
    private final String userAgent;

    static {
        DefaultHttpDataSource.CONTENT_RANGE_HEADER = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
        DefaultHttpDataSource.skipBufferReference = new AtomicReference();
    }

    public DefaultHttpDataSource(String arg2, Predicate arg3, TransferListener arg4, int arg5, int arg6, boolean arg7, RequestProperties arg8) {
        super(true);
        this.userAgent = Assertions.checkNotEmpty(arg2);
        this.contentTypePredicate = arg3;
        this.requestProperties = new RequestProperties();
        this.connectTimeoutMillis = arg5;
        this.readTimeoutMillis = arg6;
        this.allowCrossProtocolRedirects = arg7;
        this.defaultRequestProperties = arg8;
        if(arg4 != null) {
            this.addTransferListener(arg4);
        }
    }

    public DefaultHttpDataSource(String arg2, Predicate arg3) {
        this(arg2, arg3, null);
    }

    public DefaultHttpDataSource(String arg7, Predicate arg8, TransferListener arg9) {
        this(arg7, arg8, arg9, 8000, 8000);
    }

    public DefaultHttpDataSource(String arg9, Predicate arg10, TransferListener arg11, int arg12, int arg13) {
        this(arg9, arg10, arg11, arg12, arg13, false, null);
    }

    protected final long bytesRead() {
        return this.bytesRead;
    }

    protected final long bytesRemaining() {
        long v0 = this.bytesToRead == -1 ? this.bytesToRead : this.bytesToRead - this.bytesRead;
        return v0;
    }

    protected final long bytesSkipped() {
        return this.bytesSkipped;
    }

    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    public void clearRequestProperty(String arg2) {
        Assertions.checkNotNull(arg2);
        this.requestProperties.remove(arg2);
    }

    public void close() {
        InputStream v1 = null;
        try {
            if(this.inputStream != null) {
                DefaultHttpDataSource.maybeTerminateInputStream(this.connection, this.bytesRemaining());
                try {
                    this.inputStream.close();
                }
                catch(IOException v2_1) {
                    try {
                        throw new HttpDataSourceException(v2_1, this.dataSpec, 3);
                    }
                    catch(Throwable v2) {
                    label_24:
                        this.inputStream = v1;
                        this.closeConnectionQuietly();
                        if(this.opened) {
                            this.opened = false;
                            this.transferEnded();
                        }

                        throw v2;
                    }
                }
            }
        }
        catch(Throwable v2) {
            goto label_24;
        }

        this.inputStream = v1;
        this.closeConnectionQuietly();
        if(this.opened) {
            this.opened = false;
            this.transferEnded();
        }
    }

    private void closeConnectionQuietly() {
        if(this.connection != null) {
            try {
                this.connection.disconnect();
            }
            catch(Exception v0) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", ((Throwable)v0));
            }

            this.connection = null;
        }
    }

    protected final HttpURLConnection getConnection() {
        return this.connection;
    }

    private static long getContentLength(HttpURLConnection arg8) {
        long v1;
        String v0 = arg8.getHeaderField("Content-Length");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            try {
                v1 = Long.parseLong(v0);
                goto label_17;
            }
            catch(NumberFormatException ) {
                Log.e("DefaultHttpDataSource", "Unexpected Content-Length [" + v0 + "]");
            }
        }

        v1 = -1;
    label_17:
        String v8 = arg8.getHeaderField("Content-Range");
        if(!TextUtils.isEmpty(((CharSequence)v8))) {
            Matcher v3 = DefaultHttpDataSource.CONTENT_RANGE_HEADER.matcher(((CharSequence)v8));
            if(!v3.find()) {
                return v1;
            }

            int v4 = 2;
            try {
                long v4_1 = Long.parseLong(v3.group(v4)) - Long.parseLong(v3.group(1)) + 1;
                if(v1 < 0) {
                    return v4_1;
                }

                if(v1 == v4_1) {
                    return v1;
                }

                Log.w("DefaultHttpDataSource", "Inconsistent headers [" + v0 + "] [" + v8 + "]");
                v1 = Math.max(v1, v4_1);
            }
            catch(NumberFormatException ) {
                Log.e("DefaultHttpDataSource", "Unexpected Content-Range [" + v8 + "]");
            }
        }

        return v1;
    }

    public Map getResponseHeaders() {
        Map v0 = this.connection == null ? Collections.emptyMap() : this.connection.getHeaderFields();
        return v0;
    }

    public Uri getUri() {
        Uri v0 = this.connection == null ? null : Uri.parse(this.connection.getURL().toString());
        return v0;
    }

    private static URL handleRedirect(URL arg2, String arg3) {
        if(arg3 != null) {
            URL v0 = new URL(arg2, arg3);
            String v2 = v0.getProtocol();
            if(!"https".equals(v2)) {
                if("http".equals(v2)) {
                }
                else {
                    StringBuilder v0_1 = new StringBuilder();
                    v0_1.append("Unsupported protocol redirect: ");
                    v0_1.append(v2);
                    throw new ProtocolException(v0_1.toString());
                }
            }

            return v0;
        }

        throw new ProtocolException("Null location redirect");
    }

    private HttpURLConnection makeConnection(DataSpec arg20) {
        int v10;
        URL v1 = new URL(arg20.uri.toString());
        byte[] v2 = arg20.postBody;
        long v12 = arg20.position;
        long v14 = arg20.length;
        boolean v16 = arg20.isFlagSet(1);
        if(!this.allowCrossProtocolRedirects) {
            return this.makeConnection(v1, v2, v12, v14, v16, true);
        }

        int v0 = 0;
        while(true) {
            v10 = v0 + 1;
            if(v0 > 20) {
                break;
            }

            long v6 = v12;
            long v17 = v12;
            int v12_1 = v10;
            HttpURLConnection v0_1 = this.makeConnection(v1, v2, v6, v14, v16, false);
            int v3 = v0_1.getResponseCode();
            if(v3 != 300 && v3 != 301 && v3 != 302 && v3 != 303) {
                if(v2 == null) {
                    if(v3 == 307) {
                        goto label_51;
                    }
                    else if(v3 == 308) {
                        goto label_51;
                    }
                }

                return v0_1;
            }

        label_51:
            v2 = null;
            String v3_1 = v0_1.getHeaderField("Location");
            v0_1.disconnect();
            v1 = DefaultHttpDataSource.handleRedirect(v1, v3_1);
            v0 = v12_1;
            v12 = v17;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Too many redirects: ");
        v1_1.append(v10);
        throw new NoRouteToHostException(v1_1.toString());
    }

    private HttpURLConnection makeConnection(URL arg5, byte[] arg6, long arg7, long arg9, boolean arg11, boolean arg12) {
        // Method was not decompiled
    }

    private static void maybeTerminateInputStream(HttpURLConnection arg3, long arg4) {
        if(Util.SDK_INT != 19 && Util.SDK_INT != 20) {
            return;
        }

        try {
            InputStream v3 = arg3.getInputStream();
            if(arg4 == -1) {
                if(v3.read() == -1) {
                    return;
                }
            }
            else if(arg4 <= 2048) {
                return;
            }

            String v4 = v3.getClass().getName();
            if(("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream".equals(v4)) || ("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream".equals(v4))) {
                Method v4_1 = v3.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput");
                v4_1.setAccessible(true);
                v4_1.invoke(v3);
            }

            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public long open(DataSpec arg8) {
        int v3_1;
        StringBuilder v3;
        this.dataSpec = arg8;
        long v0 = 0;
        this.bytesRead = v0;
        this.bytesSkipped = v0;
        this.transferInitializing(arg8);
        try {
            this.connection = this.makeConnection(arg8);
        }
        catch(IOException v0_1) {
            v3 = new StringBuilder();
            v3.append("Unable to connect to ");
            v3.append(arg8.uri.toString());
            throw new HttpDataSourceException(v3.toString(), v0_1, arg8, 1);
        }

        try {
            v3_1 = this.connection.getResponseCode();
            int v4 = 200;
            if(v3_1 < v4) {
                goto label_59;
            }
        }
        catch(IOException v0_1) {
            this.closeConnectionQuietly();
            v3 = new StringBuilder();
            v3.append("Unable to connect to ");
            v3.append(arg8.uri.toString());
            throw new HttpDataSourceException(v3.toString(), v0_1, arg8, 1);
        }

        if(v3_1 > 299) {
        }
        else {
            String v5 = this.connection.getContentType();
            if(this.contentTypePredicate != null) {
                if(this.contentTypePredicate.evaluate(v5)) {
                }
                else {
                    this.closeConnectionQuietly();
                    throw new InvalidContentTypeException(v5, arg8);
                }
            }

            if(v3_1 == v4 && arg8.position != v0) {
                v0 = arg8.position;
            }

            this.bytesToSkip = v0;
            if(!arg8.isFlagSet(1)) {
                long v3_2 = -1;
                if(arg8.length != v3_2) {
                    goto label_45;
                }
                else {
                    v0 = DefaultHttpDataSource.getContentLength(this.connection);
                    if(v0 != v3_2) {
                        v3_2 = v0 - this.bytesToSkip;
                    }

                    this.bytesToRead = v3_2;
                }
            }
            else {
            label_45:
                this.bytesToRead = arg8.length;
            }

            try {
                this.inputStream = this.connection.getInputStream();
            }
            catch(IOException v0_1) {
                this.closeConnectionQuietly();
                throw new HttpDataSourceException(v0_1, arg8, 1);
            }

            this.opened = true;
            this.transferStarted(arg8);
            return this.bytesToRead;
        }

    label_59:
        Map v0_2 = this.connection.getHeaderFields();
        this.closeConnectionQuietly();
        InvalidResponseCodeException v1 = new InvalidResponseCodeException(v3_1, v0_2, arg8);
        if(v3_1 == 416) {
            v1.initCause(new DataSourceException(0));
        }

        throw v1;
    }

    public int read(byte[] arg2, int arg3, int arg4) {
        try {
            this.skipInternal();
            return this.readInternal(arg2, arg3, arg4);
        }
        catch(IOException v2) {
            throw new HttpDataSourceException(v2, this.dataSpec, 2);
        }
    }

    private int readInternal(byte[] arg9, int arg10, int arg11) {
        // Method was not decompiled
    }

    public void setRequestProperty(String arg2, String arg3) {
        Assertions.checkNotNull(arg2);
        Assertions.checkNotNull(arg3);
        this.requestProperties.set(arg2, arg3);
    }

    private void skipInternal() {
        if(this.bytesSkipped == this.bytesToSkip) {
            return;
        }

        Object v0 = DefaultHttpDataSource.skipBufferReference.getAndSet(null);
        if(v0 == null) {
            byte[] v0_1 = new byte[4096];
        }

        while(true) {
            if(this.bytesSkipped == this.bytesToSkip) {
                goto label_40;
            }

            int v1 = this.inputStream.read(((byte[])v0), 0, ((int)Math.min(this.bytesToSkip - this.bytesSkipped, ((long)v0.length))));
            if(Thread.currentThread().isInterrupted()) {
                goto label_37;
            }

            if(v1 == -1) {
                break;
            }

            this.bytesSkipped += ((long)v1);
            this.bytesTransferred(v1);
        }

        throw new EOFException();
    label_37:
        throw new InterruptedIOException();
    label_40:
        DefaultHttpDataSource.skipBufferReference.set(v0);
    }
}

