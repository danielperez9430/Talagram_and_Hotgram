package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.media.MediaCrypto;
import android.media.MediaDrm$KeyRequest;
import android.media.MediaDrm$KeyStatus;
import android.media.MediaDrm$OnEventListener;
import android.media.MediaDrm$OnKeyStatusChangeListener;
import android.media.MediaDrm$ProvisionRequest;
import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import android.os.Handler;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@TargetApi(value=23) public final class FrameworkMediaDrm implements ExoMediaDrm {
    private static final String CENC_SCHEME_MIME_TYPE = "cenc";
    private final MediaDrm mediaDrm;
    private final UUID uuid;

    private FrameworkMediaDrm(UUID arg3) {
        super();
        Assertions.checkNotNull(arg3);
        Assertions.checkArgument(C.COMMON_PSSH_UUID.equals(arg3) ^ 1, "Use C.CLEARKEY_UUID instead");
        if(Util.SDK_INT < 27 && (C.CLEARKEY_UUID.equals(arg3))) {
            arg3 = C.COMMON_PSSH_UUID;
        }

        this.uuid = arg3;
        this.mediaDrm = new MediaDrm(arg3);
        if((C.WIDEVINE_UUID.equals(arg3)) && (FrameworkMediaDrm.needsForceL3Workaround())) {
            this.mediaDrm.setPropertyString("securityLevel", "L3");
        }
    }

    public void closeSession(byte[] arg2) {
        this.mediaDrm.closeSession(arg2);
    }

    public ExoMediaCrypto createMediaCrypto(byte[] arg1) {
        return this.createMediaCrypto(arg1);
    }

    public FrameworkMediaCrypto createMediaCrypto(byte[] arg5) {
        boolean v0 = Util.SDK_INT >= 21 || !C.WIDEVINE_UUID.equals(this.uuid) || !"L3".equals(this.getPropertyString("securityLevel")) ? false : true;
        return new FrameworkMediaCrypto(new MediaCrypto(this.uuid, arg5), v0);
    }

    public KeyRequest getKeyRequest(byte[] arg8, byte[] arg9, String arg10, int arg11, HashMap arg12) {
        byte[] v3;
        if(Util.SDK_INT < 21 && (C.WIDEVINE_UUID.equals(this.uuid))) {
            goto label_27;
        }
        else if(!C.PLAYREADY_UUID.equals(this.uuid) || !"Amazon".equals(Util.MANUFACTURER) || !"AFTB".equals(Util.MODEL) && !"AFTS".equals(Util.MODEL) && !"AFTM".equals(Util.MODEL)) {
        label_33:
            v3 = arg9;
        }
        else {
        label_27:
            byte[] v0 = PsshAtomUtil.parseSchemeSpecificData(arg9, this.uuid);
            if(v0 == null) {
                goto label_33;
            }
            else {
                v3 = v0;
            }
        }

        if(Util.SDK_INT < 26 && (C.CLEARKEY_UUID.equals(this.uuid)) && (("video/mp4".equals(arg10)) || ("audio/mp4".equals(arg10)))) {
            arg10 = "cenc";
        }

        MediaDrm$KeyRequest v8 = this.mediaDrm.getKeyRequest(arg8, v3, arg10, arg11, arg12);
        arg9 = v8.getData();
        if(C.CLEARKEY_UUID.equals(this.uuid)) {
            arg9 = ClearKeyUtil.adjustRequestData(arg9);
        }

        return new DefaultKeyRequest(arg9, v8.getDefaultUrl());
    }

    public byte[] getPropertyByteArray(String arg2) {
        return this.mediaDrm.getPropertyByteArray(arg2);
    }

    public String getPropertyString(String arg2) {
        return this.mediaDrm.getPropertyString(arg2);
    }

    public ProvisionRequest getProvisionRequest() {
        MediaDrm$ProvisionRequest v0 = this.mediaDrm.getProvisionRequest();
        return new DefaultProvisionRequest(v0.getData(), v0.getDefaultUrl());
    }

    private static boolean needsForceL3Workaround() {
        return "ASUS_Z00AD".equals(Util.MODEL);
    }

    public static FrameworkMediaDrm newInstance(UUID arg2) {
        try {
            return new FrameworkMediaDrm(arg2);
        }
        catch(Exception v2) {
            throw new UnsupportedDrmException(2, v2);
        }
        catch(UnsupportedSchemeException v2_1) {
            throw new UnsupportedDrmException(1, ((Exception)v2_1));
        }
    }

    public byte[] openSession() {
        return this.mediaDrm.openSession();
    }

    public byte[] provideKeyResponse(byte[] arg3, byte[] arg4) {
        if(C.CLEARKEY_UUID.equals(this.uuid)) {
            arg4 = ClearKeyUtil.adjustResponseData(arg4);
        }

        return this.mediaDrm.provideKeyResponse(arg3, arg4);
    }

    public void provideProvisionResponse(byte[] arg2) {
        this.mediaDrm.provideProvisionResponse(arg2);
    }

    public Map queryKeyStatus(byte[] arg2) {
        return this.mediaDrm.queryKeyStatus(arg2);
    }

    public void release() {
        this.mediaDrm.release();
    }

    public void restoreKeys(byte[] arg2, byte[] arg3) {
        this.mediaDrm.restoreKeys(arg2, arg3);
    }

    public void setOnEventListener(OnEventListener arg3) {
        com.google.android.exoplayer2.drm.FrameworkMediaDrm$1 v3_1;
        MediaDrm v0 = this.mediaDrm;
        if(arg3 == null) {
            MediaDrm$OnEventListener v3 = null;
        }
        else {
            v3_1 = new MediaDrm$OnEventListener(arg3) {
                public void onEvent(MediaDrm arg7, byte[] arg8, int arg9, int arg10, byte[] arg11) {
                    this.val$listener.onEvent(FrameworkMediaDrm.this, arg8, arg9, arg10, arg11);
                }
            };
        }

        v0.setOnEventListener(((MediaDrm$OnEventListener)v3_1));
    }

    public void setOnKeyStatusChangeListener(OnKeyStatusChangeListener arg4) {
        com.google.android.exoplayer2.drm.FrameworkMediaDrm$2 v2_1;
        if(Util.SDK_INT >= 23) {
            MediaDrm v0 = this.mediaDrm;
            Handler v1 = null;
            if(arg4 == null) {
                MediaDrm$OnKeyStatusChangeListener v2 = ((MediaDrm$OnKeyStatusChangeListener)v1);
            }
            else {
                v2_1 = new MediaDrm$OnKeyStatusChangeListener(arg4) {
                    public void onKeyStatusChange(MediaDrm arg4, byte[] arg5, List arg6, boolean arg7) {
                        ArrayList v4 = new ArrayList();
                        Iterator v6 = arg6.iterator();
                        while(v6.hasNext()) {
                            Object v0 = v6.next();
                            ((List)v4).add(new DefaultKeyStatus(((MediaDrm$KeyStatus)v0).getStatusCode(), ((MediaDrm$KeyStatus)v0).getKeyId()));
                        }

                        this.val$listener.onKeyStatusChange(FrameworkMediaDrm.this, arg5, ((List)v4), arg7);
                    }
                };
            }

            v0.setOnKeyStatusChangeListener(((MediaDrm$OnKeyStatusChangeListener)v2_1), v1);
            return;
        }

        throw new UnsupportedOperationException();
    }

    public void setPropertyByteArray(String arg2, byte[] arg3) {
        this.mediaDrm.setPropertyByteArray(arg2, arg3);
    }

    public void setPropertyString(String arg2, String arg3) {
        this.mediaDrm.setPropertyString(arg2, arg3);
    }
}

