package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource$Factory;
import com.google.android.exoplayer2.upstream.HttpDataSource$InvalidResponseCodeException;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.UUID;

@TargetApi(value=18) public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private static final int MAX_MANUAL_REDIRECTS = 5;
    private final Factory dataSourceFactory;
    private final String defaultLicenseUrl;
    private final boolean forceDefaultLicenseUrl;
    private final Map keyRequestProperties;

    public HttpMediaDrmCallback(String arg1, boolean arg2, Factory arg3) {
        super();
        this.dataSourceFactory = arg3;
        this.defaultLicenseUrl = arg1;
        this.forceDefaultLicenseUrl = arg2;
        this.keyRequestProperties = new HashMap();
    }

    public HttpMediaDrmCallback(String arg2, Factory arg3) {
        this(arg2, false, arg3);
    }

    public void clearAllKeyRequestProperties() {
        Map v0 = this.keyRequestProperties;
        __monitor_enter(v0);
        try {
            this.keyRequestProperties.clear();
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    public void clearKeyRequestProperty(String arg3) {
        Assertions.checkNotNull(arg3);
        Map v0 = this.keyRequestProperties;
        __monitor_enter(v0);
        try {
            this.keyRequestProperties.remove(arg3);
            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    public byte[] executeKeyRequest(UUID arg4, KeyRequest arg5, String arg6) {
        String v1;
        String v0 = arg5.getDefaultUrl();
        if(TextUtils.isEmpty(((CharSequence)v0))) {
        }
        else {
            arg6 = v0;
        }

        if((this.forceDefaultLicenseUrl) || (TextUtils.isEmpty(((CharSequence)arg6)))) {
            arg6 = this.defaultLicenseUrl;
        }

        HashMap v0_1 = new HashMap();
        if(C.PLAYREADY_UUID.equals(arg4)) {
            v1 = "text/xml";
        }
        else if(C.CLEARKEY_UUID.equals(arg4)) {
            v1 = "application/json";
        }
        else {
            v1 = "application/octet-stream";
        }

        ((Map)v0_1).put("Content-Type", v1);
        if(C.PLAYREADY_UUID.equals(arg4)) {
            ((Map)v0_1).put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        }

        Map v4 = this.keyRequestProperties;
        __monitor_enter(v4);
        try {
            ((Map)v0_1).putAll(this.keyRequestProperties);
            __monitor_exit(v4);
        }
        catch(Throwable v5) {
            try {
            label_41:
                __monitor_exit(v4);
            }
            catch(Throwable v5) {
                goto label_41;
            }

            throw v5;
        }

        return HttpMediaDrmCallback.executePost(this.dataSourceFactory, arg6, arg5.getData(), ((Map)v0_1));
    }

    private static byte[] executePost(Factory arg17, String arg18, byte[] arg19, Map arg20) {
        String v3_1;
        int v0_5;
        byte[] v0_4;
        DataSourceInputStream v4;
        HttpDataSource v1 = arg17.createDataSource();
        if(arg20 != null) {
            Iterator v0 = arg20.entrySet().iterator();
            while(v0.hasNext()) {
                Object v2 = v0.next();
                v1.setRequestProperty(((Map$Entry)v2).getKey(), ((Map$Entry)v2).getValue());
            }
        }

        String v0_1 = arg18;
        int v3 = 0;
        while(true) {
            v4 = new DataSourceInputStream(((DataSource)v1), new DataSpec(Uri.parse(v0_1), arg19, 0, 0, -1, null, 1));
            try {
                v0_4 = Util.toByteArray(((InputStream)v4));
                break;
            }
            catch(Throwable v0_2) {
            }
            catch(InvalidResponseCodeException v0_3) {
                InvalidResponseCodeException v5 = v0_3;
                try {
                    if(v5.responseCode == 307 || v5.responseCode == 308) {
                        v0_5 = v3 + 1;
                        if(v3 < 5) {
                            v3 = 1;
                        }
                        else {
                        label_48:
                            v3 = 0;
                        }
                    }
                    else {
                        v0_5 = v3;
                        goto label_48;
                    }

                    if(v3 != 0) {
                        v3_1 = HttpMediaDrmCallback.getRedirectUrl(v5);
                    }
                    else {
                        goto label_52;
                    }

                    goto label_53;
                }
                catch(Throwable v0_2) {
                    goto label_60;
                }

            label_52:
                v3_1 = null;
            label_53:
                if(v3_1 != null) {
                    Util.closeQuietly(((Closeable)v4));
                    String v16 = v3_1;
                    v3 = v0_5;
                    v0_1 = v16;
                    continue;
                }

                try {
                    throw v5;
                }
                catch(Throwable v0_2) {
                label_60:
                    Util.closeQuietly(((Closeable)v4));
                    throw v0_2;
                }
            }
        }

        Util.closeQuietly(((Closeable)v4));
        return v0_4;
    }

    public byte[] executeProvisionRequest(UUID arg3, ProvisionRequest arg4) {
        StringBuilder v3 = new StringBuilder();
        v3.append(arg4.getDefaultUrl());
        v3.append("&signedRequest=");
        v3.append(Util.fromUtf8Bytes(arg4.getData()));
        return HttpMediaDrmCallback.executePost(this.dataSourceFactory, v3.toString(), new byte[0], null);
    }

    private static String getRedirectUrl(InvalidResponseCodeException arg1) {
        Map v1 = arg1.headerFields;
        if(v1 != null) {
            Object v1_1 = v1.get("Location");
            if(v1_1 != null && !((List)v1_1).isEmpty()) {
                return ((List)v1_1).get(0);
            }
        }

        return null;
    }

    public void setKeyRequestProperty(String arg3, String arg4) {
        Assertions.checkNotNull(arg3);
        Assertions.checkNotNull(arg4);
        Map v0 = this.keyRequestProperties;
        __monitor_enter(v0);
        try {
            this.keyRequestProperties.put(arg3, arg4);
            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_9;
        }

        throw v3;
    }
}

