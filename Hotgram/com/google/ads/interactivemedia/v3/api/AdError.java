package com.google.ads.interactivemedia.v3.api;

public final class AdError extends Exception {
    public enum AdErrorCode {
        public static final enum AdErrorCode ADS_REQUEST_NETWORK_ERROR;
        public static final enum AdErrorCode COMPANION_AD_LOADING_FAILED;
        public static final enum AdErrorCode FAILED_TO_REQUEST_ADS;
        public static final enum AdErrorCode INTERNAL_ERROR;
        public static final enum AdErrorCode INVALID_ARGUMENTS;
        public static final enum AdErrorCode OVERLAY_AD_LOADING_FAILED;
        public static final enum AdErrorCode OVERLAY_AD_PLAYING_FAILED;
        public static final enum AdErrorCode PLAYLIST_NO_CONTENT_TRACKING;
        public static final enum AdErrorCode UNKNOWN_AD_RESPONSE;
        public static final enum AdErrorCode UNKNOWN_ERROR;
        public static final enum AdErrorCode VAST_ASSET_NOT_FOUND;
        public static final enum AdErrorCode VAST_EMPTY_RESPONSE;
        public static final enum AdErrorCode VAST_LINEAR_ASSET_MISMATCH;
        public static final enum AdErrorCode VAST_LOAD_TIMEOUT;
        public static final enum AdErrorCode VAST_MALFORMED_RESPONSE;
        public static final enum AdErrorCode VAST_MEDIA_LOAD_TIMEOUT;
        public static final enum AdErrorCode VAST_NONLINEAR_ASSET_MISMATCH;
        public static final enum AdErrorCode VAST_TOO_MANY_REDIRECTS;
        public static final enum AdErrorCode VIDEO_PLAY_ERROR;
        private final int a;

        static {
            AdErrorCode.INTERNAL_ERROR = new AdErrorCode("INTERNAL_ERROR", 0, -1);
            AdErrorCode.VAST_MALFORMED_RESPONSE = new AdErrorCode("VAST_MALFORMED_RESPONSE", 1, 100);
            AdErrorCode.UNKNOWN_AD_RESPONSE = new AdErrorCode("UNKNOWN_AD_RESPONSE", 2, 1010);
            AdErrorCode.VAST_LOAD_TIMEOUT = new AdErrorCode("VAST_LOAD_TIMEOUT", 3, 301);
            AdErrorCode.VAST_TOO_MANY_REDIRECTS = new AdErrorCode("VAST_TOO_MANY_REDIRECTS", 4, 302);
            AdErrorCode.VIDEO_PLAY_ERROR = new AdErrorCode("VIDEO_PLAY_ERROR", 5, 400);
            AdErrorCode.VAST_MEDIA_LOAD_TIMEOUT = new AdErrorCode("VAST_MEDIA_LOAD_TIMEOUT", 6, 402);
            AdErrorCode.VAST_LINEAR_ASSET_MISMATCH = new AdErrorCode("VAST_LINEAR_ASSET_MISMATCH", 7, 403);
            AdErrorCode.OVERLAY_AD_PLAYING_FAILED = new AdErrorCode("OVERLAY_AD_PLAYING_FAILED", 8, 500);
            AdErrorCode.OVERLAY_AD_LOADING_FAILED = new AdErrorCode("OVERLAY_AD_LOADING_FAILED", 9, 502);
            AdErrorCode.VAST_NONLINEAR_ASSET_MISMATCH = new AdErrorCode("VAST_NONLINEAR_ASSET_MISMATCH", 10, 503);
            AdErrorCode.COMPANION_AD_LOADING_FAILED = new AdErrorCode("COMPANION_AD_LOADING_FAILED", 11, 603);
            AdErrorCode.UNKNOWN_ERROR = new AdErrorCode("UNKNOWN_ERROR", 12, 900);
            AdErrorCode.VAST_EMPTY_RESPONSE = new AdErrorCode("VAST_EMPTY_RESPONSE", 13, 1009);
            AdErrorCode.FAILED_TO_REQUEST_ADS = new AdErrorCode("FAILED_TO_REQUEST_ADS", 14, 1005);
            AdErrorCode.VAST_ASSET_NOT_FOUND = new AdErrorCode("VAST_ASSET_NOT_FOUND", 15, 1007);
            AdErrorCode.ADS_REQUEST_NETWORK_ERROR = new AdErrorCode("ADS_REQUEST_NETWORK_ERROR", 16, 1012);
            AdErrorCode.INVALID_ARGUMENTS = new AdErrorCode("INVALID_ARGUMENTS", 17, 1101);
            AdErrorCode.PLAYLIST_NO_CONTENT_TRACKING = new AdErrorCode("PLAYLIST_NO_CONTENT_TRACKING", 18, 1205);
            AdErrorCode.b = new AdErrorCode[]{AdErrorCode.INTERNAL_ERROR, AdErrorCode.VAST_MALFORMED_RESPONSE, AdErrorCode.UNKNOWN_AD_RESPONSE, AdErrorCode.VAST_LOAD_TIMEOUT, AdErrorCode.VAST_TOO_MANY_REDIRECTS, AdErrorCode.VIDEO_PLAY_ERROR, AdErrorCode.VAST_MEDIA_LOAD_TIMEOUT, AdErrorCode.VAST_LINEAR_ASSET_MISMATCH, AdErrorCode.OVERLAY_AD_PLAYING_FAILED, AdErrorCode.OVERLAY_AD_LOADING_FAILED, AdErrorCode.VAST_NONLINEAR_ASSET_MISMATCH, AdErrorCode.COMPANION_AD_LOADING_FAILED, AdErrorCode.UNKNOWN_ERROR, AdErrorCode.VAST_EMPTY_RESPONSE, AdErrorCode.FAILED_TO_REQUEST_ADS, AdErrorCode.VAST_ASSET_NOT_FOUND, AdErrorCode.ADS_REQUEST_NETWORK_ERROR, AdErrorCode.INVALID_ARGUMENTS, AdErrorCode.PLAYLIST_NO_CONTENT_TRACKING};
        }

        private AdErrorCode(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.a = arg3;
        }

        static AdErrorCode a(int arg5) {
            AdErrorCode[] v0 = AdErrorCode.values();
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                AdErrorCode v3 = v0[v2];
                if(v3.getErrorNumber() == arg5) {
                    return v3;
                }
            }

            if(1204 == arg5) {
                return AdErrorCode.INTERNAL_ERROR;
            }

            return AdErrorCode.UNKNOWN_ERROR;
        }

        public boolean equals(int arg2) {
            boolean v2 = this.a == arg2 ? true : false;
            return v2;
        }

        public int getErrorNumber() {
            return this.a;
        }

        public String toString() {
            String v0 = this.name();
            int v1 = this.a;
            StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 41);
            v3.append("AdErrorCode [name: ");
            v3.append(v0);
            v3.append(", number: ");
            v3.append(v1);
            v3.append("]");
            return v3.toString();
        }

        public static AdErrorCode valueOf(String arg1) {
            return Enum.valueOf(AdErrorCode.class, arg1);
        }

        public static AdErrorCode[] values() {
            // Method was not decompiled
        }
    }

    public enum AdErrorType {
        public static final enum AdErrorType LOAD;
        public static final enum AdErrorType PLAY;

        static {
            AdErrorType.LOAD = new AdErrorType("LOAD", 0);
            AdErrorType.PLAY = new AdErrorType("PLAY", 1);
            AdErrorType.a = new AdErrorType[]{AdErrorType.LOAD, AdErrorType.PLAY};
        }

        private AdErrorType(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static AdErrorType valueOf(String arg1) {
            return Enum.valueOf(AdErrorType.class, arg1);
        }

        public static AdErrorType[] values() {
            // Method was not decompiled
        }
    }

    private final AdErrorCode a;
    private final AdErrorType b;

    public AdError(AdErrorType arg1, int arg2, String arg3) {
        this(arg1, AdErrorCode.a(arg2), arg3);
    }

    public AdError(AdErrorType arg1, AdErrorCode arg2, String arg3) {
        super(arg3);
        this.b = arg1;
        this.a = arg2;
    }

    public AdErrorCode getErrorCode() {
        return this.a;
    }

    public int getErrorCodeNumber() {
        return this.a.getErrorNumber();
    }

    public AdErrorType getErrorType() {
        return this.b;
    }

    public String getMessage() {
        return super.getMessage();
    }

    public String toString() {
        String v0 = String.valueOf(this.b);
        String v1 = String.valueOf(this.a);
        String v2 = this.getMessage();
        StringBuilder v4 = new StringBuilder(String.valueOf(v0).length() + 45 + String.valueOf(v1).length() + String.valueOf(v2).length());
        v4.append("AdError [errorType: ");
        v4.append(v0);
        v4.append(", errorCode: ");
        v4.append(v1);
        v4.append(", message: ");
        v4.append(v2);
        v4.append("]");
        return v4.toString();
    }
}

