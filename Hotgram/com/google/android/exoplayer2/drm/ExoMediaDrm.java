package com.google.android.exoplayer2.drm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ExoMediaDrm {
    public final class DefaultKeyRequest implements KeyRequest {
        private final byte[] data;
        private final String defaultUrl;

        public DefaultKeyRequest(byte[] arg1, String arg2) {
            super();
            this.data = arg1;
            this.defaultUrl = arg2;
        }

        public byte[] getData() {
            return this.data;
        }

        public String getDefaultUrl() {
            return this.defaultUrl;
        }
    }

    public final class DefaultKeyStatus implements KeyStatus {
        private final byte[] keyId;
        private final int statusCode;

        DefaultKeyStatus(int arg1, byte[] arg2) {
            super();
            this.statusCode = arg1;
            this.keyId = arg2;
        }

        public byte[] getKeyId() {
            return this.keyId;
        }

        public int getStatusCode() {
            return this.statusCode;
        }
    }

    public final class DefaultProvisionRequest implements ProvisionRequest {
        private final byte[] data;
        private final String defaultUrl;

        public DefaultProvisionRequest(byte[] arg1, String arg2) {
            super();
            this.data = arg1;
            this.defaultUrl = arg2;
        }

        public byte[] getData() {
            return this.data;
        }

        public String getDefaultUrl() {
            return this.defaultUrl;
        }
    }

    public interface KeyRequest {
        byte[] getData();

        String getDefaultUrl();
    }

    public interface KeyStatus {
        byte[] getKeyId();

        int getStatusCode();
    }

    public interface OnEventListener {
        void onEvent(ExoMediaDrm arg1, byte[] arg2, int arg3, int arg4, byte[] arg5);
    }

    public interface OnKeyStatusChangeListener {
        void onKeyStatusChange(ExoMediaDrm arg1, byte[] arg2, List arg3, boolean arg4);
    }

    public interface ProvisionRequest {
        byte[] getData();

        String getDefaultUrl();
    }

    public static final int EVENT_KEY_EXPIRED = 3;
    public static final int EVENT_KEY_REQUIRED = 2;
    public static final int EVENT_PROVISION_REQUIRED = 1;
    public static final int KEY_TYPE_OFFLINE = 2;
    public static final int KEY_TYPE_RELEASE = 3;
    public static final int KEY_TYPE_STREAMING = 1;

    void closeSession(byte[] arg1);

    ExoMediaCrypto createMediaCrypto(byte[] arg1);

    KeyRequest getKeyRequest(byte[] arg1, byte[] arg2, String arg3, int arg4, HashMap arg5);

    byte[] getPropertyByteArray(String arg1);

    String getPropertyString(String arg1);

    ProvisionRequest getProvisionRequest();

    byte[] openSession();

    byte[] provideKeyResponse(byte[] arg1, byte[] arg2);

    void provideProvisionResponse(byte[] arg1);

    Map queryKeyStatus(byte[] arg1);

    void release();

    void restoreKeys(byte[] arg1, byte[] arg2);

    void setOnEventListener(OnEventListener arg1);

    void setOnKeyStatusChangeListener(OnKeyStatusChangeListener arg1);

    void setPropertyByteArray(String arg1, byte[] arg2);

    void setPropertyString(String arg1, String arg2);
}

