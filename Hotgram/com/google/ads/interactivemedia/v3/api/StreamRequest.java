package com.google.ads.interactivemedia.v3.api;

import java.util.Map;

public interface StreamRequest {
    public enum StreamFormat {
        public static final enum StreamFormat DASH;
        public static final enum StreamFormat HLS;

        static {
            StreamFormat.DASH = new StreamFormat("DASH", 0);
            StreamFormat.HLS = new StreamFormat("HLS", 1);
            StreamFormat.a = new StreamFormat[]{StreamFormat.DASH, StreamFormat.HLS};
        }

        private StreamFormat(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static StreamFormat valueOf(String arg1) {
            return Enum.valueOf(StreamFormat.class, arg1);
        }

        public static StreamFormat[] values() {
            // Method was not decompiled
        }
    }

    Map getAdTagParameters();

    String getApiKey();

    String getAssetKey();

    String getAuthToken();

    String getContentSourceId();

    StreamFormat getFormat();

    String getManifestSuffix();

    String getStreamActivityMonitorId();

    StreamDisplayContainer getStreamDisplayContainer();

    Boolean getUseQAStreamBaseUrl();

    Object getUserRequestContext();

    String getVideoId();

    void setAdTagParameters(Map arg1);

    void setAuthToken(String arg1);

    void setFormat(StreamFormat arg1);

    void setManifestSuffix(String arg1);

    void setStreamActivityMonitorId(String arg1);

    void setUseQAStreamBaseUrl(Boolean arg1);

    void setUserRequestContext(Object arg1);
}

