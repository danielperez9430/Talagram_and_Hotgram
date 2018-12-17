package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface HttpDataSource extends DataSource {
    final class com.google.android.exoplayer2.upstream.HttpDataSource$1 implements Predicate {
        com.google.android.exoplayer2.upstream.HttpDataSource$1() {
            super();
        }

        public boolean evaluate(Object arg1) {
            return this.evaluate(((String)arg1));
        }

        public boolean evaluate(String arg2) {
            boolean v2;
            arg2 = Util.toLowerInvariant(arg2);
            if(!TextUtils.isEmpty(((CharSequence)arg2))) {
                if((arg2.contains("text")) && !arg2.contains("text/vtt")) {
                    goto label_17;
                }

                if(arg2.contains("html")) {
                    goto label_17;
                }

                if(arg2.contains("xml")) {
                    goto label_17;
                }

                v2 = true;
            }
            else {
            label_17:
                v2 = false;
            }

            return v2;
        }
    }

    public abstract class BaseFactory implements Factory {
        private final RequestProperties defaultRequestProperties;

        public BaseFactory() {
            super();
            this.defaultRequestProperties = new RequestProperties();
        }

        @Deprecated public final void clearAllDefaultRequestProperties() {
            this.defaultRequestProperties.clear();
        }

        @Deprecated public final void clearDefaultRequestProperty(String arg2) {
            this.defaultRequestProperties.remove(arg2);
        }

        public DataSource createDataSource() {
            return this.createDataSource();
        }

        public final HttpDataSource createDataSource() {
            return this.createDataSourceInternal(this.defaultRequestProperties);
        }

        protected abstract HttpDataSource createDataSourceInternal(RequestProperties arg1);

        public final RequestProperties getDefaultRequestProperties() {
            return this.defaultRequestProperties;
        }

        @Deprecated public final void setDefaultRequestProperty(String arg2, String arg3) {
            this.defaultRequestProperties.set(arg2, arg3);
        }
    }

    public interface Factory extends com.google.android.exoplayer2.upstream.DataSource$Factory {
        @Deprecated void clearAllDefaultRequestProperties();

        @Deprecated void clearDefaultRequestProperty(String arg1);

        HttpDataSource createDataSource();

        RequestProperties getDefaultRequestProperties();

        @Deprecated void setDefaultRequestProperty(String arg1, String arg2);
    }

    public class HttpDataSourceException extends IOException {
        @Retention(value=RetentionPolicy.SOURCE) @public interface Type {
        }

        public static final int TYPE_CLOSE = 3;
        public static final int TYPE_OPEN = 1;
        public static final int TYPE_READ = 2;
        public final DataSpec dataSpec;
        public final int type;

        public HttpDataSourceException(DataSpec arg1, int arg2) {
            super();
            this.dataSpec = arg1;
            this.type = arg2;
        }

        public HttpDataSourceException(IOException arg1, DataSpec arg2, int arg3) {
            super(((Throwable)arg1));
            this.dataSpec = arg2;
            this.type = arg3;
        }

        public HttpDataSourceException(String arg1, DataSpec arg2, int arg3) {
            super(arg1);
            this.dataSpec = arg2;
            this.type = arg3;
        }

        public HttpDataSourceException(String arg1, IOException arg2, DataSpec arg3, int arg4) {
            super(arg1, ((Throwable)arg2));
            this.dataSpec = arg3;
            this.type = arg4;
        }
    }

    public final class InvalidContentTypeException extends HttpDataSourceException {
        public final String contentType;

        public InvalidContentTypeException(String arg3, DataSpec arg4) {
            super("Invalid content type: " + arg3, arg4, 1);
            this.contentType = arg3;
        }
    }

    public final class InvalidResponseCodeException extends HttpDataSourceException {
        public final Map headerFields;
        public final int responseCode;

        public InvalidResponseCodeException(int arg3, Map arg4, DataSpec arg5) {
            super("Response code: " + arg3, arg5, 1);
            this.responseCode = arg3;
            this.headerFields = arg4;
        }
    }

    public final class RequestProperties {
        private final Map requestProperties;
        private Map requestPropertiesSnapshot;

        public RequestProperties() {
            super();
            this.requestProperties = new HashMap();
        }

        public void clear() {
            __monitor_enter(this);
            Map v0 = null;
            try {
                this.requestPropertiesSnapshot = v0;
                this.requestProperties.clear();
            }
            catch(Throwable v0_1) {
                __monitor_exit(this);
                throw v0_1;
            }

            __monitor_exit(this);
        }

        public void clearAndSet(Map arg2) {
            __monitor_enter(this);
            Map v0 = null;
            try {
                this.requestPropertiesSnapshot = v0;
                this.requestProperties.clear();
                this.requestProperties.putAll(arg2);
            }
            catch(Throwable v2) {
                __monitor_exit(this);
                throw v2;
            }

            __monitor_exit(this);
        }

        public Map getSnapshot() {
            Map v0_1;
            __monitor_enter(this);
            try {
                if(this.requestPropertiesSnapshot == null) {
                    this.requestPropertiesSnapshot = Collections.unmodifiableMap(new HashMap(this.requestProperties));
                }

                v0_1 = this.requestPropertiesSnapshot;
            }
            catch(Throwable v0) {
                __monitor_exit(this);
                throw v0;
            }

            __monitor_exit(this);
            return v0_1;
        }

        public void remove(String arg2) {
            __monitor_enter(this);
            Map v0 = null;
            try {
                this.requestPropertiesSnapshot = v0;
                this.requestProperties.remove(arg2);
            }
            catch(Throwable v2) {
                __monitor_exit(this);
                throw v2;
            }

            __monitor_exit(this);
        }

        public void set(String arg2, String arg3) {
            __monitor_enter(this);
            Map v0 = null;
            try {
                this.requestPropertiesSnapshot = v0;
                this.requestProperties.put(arg2, arg3);
            }
            catch(Throwable v2) {
                __monitor_exit(this);
                throw v2;
            }

            __monitor_exit(this);
        }

        public void set(Map arg2) {
            __monitor_enter(this);
            Map v0 = null;
            try {
                this.requestPropertiesSnapshot = v0;
                this.requestProperties.putAll(arg2);
            }
            catch(Throwable v2) {
                __monitor_exit(this);
                throw v2;
            }

            __monitor_exit(this);
        }
    }

    public static final Predicate REJECT_PAYWALL_TYPES;

    static {
        HttpDataSource.REJECT_PAYWALL_TYPES = new com.google.android.exoplayer2.upstream.HttpDataSource$1();
    }

    void clearAllRequestProperties();

    void clearRequestProperty(String arg1);

    void close();

    Map getResponseHeaders();

    long open(DataSpec arg1);

    int read(byte[] arg1, int arg2, int arg3);

    void setRequestProperty(String arg1, String arg2);
}

