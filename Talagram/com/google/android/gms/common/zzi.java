package com.google.android.gms.common;

import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;

final class zzi extends zzg {
    private final String packageName;
    private final CertData zzbn;
    private final boolean zzbo;
    private final boolean zzbp;

    zzi(String arg1, CertData arg2, boolean arg3, boolean arg4, zzh arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    private zzi(String arg3, CertData arg4, boolean arg5, boolean arg6) {
        super(false, null, null);
        this.packageName = arg3;
        this.zzbn = arg4;
        this.zzbo = arg5;
        this.zzbp = arg6;
    }

    final String getErrorMessage() {
        String v0 = this.zzbp ? "debug cert rejected" : "not whitelisted";
        String v1 = this.packageName;
        String v2 = Hex.bytesToStringLowercase(AndroidUtilsLight.getMessageDigest("SHA-1").digest(this.zzbn.getBytes()));
        boolean v3 = this.zzbo;
        StringBuilder v5 = new StringBuilder(String.valueOf(v0).length() + 44 + String.valueOf(v1).length() + String.valueOf(v2).length());
        v5.append(v0);
        v5.append(": pkg=");
        v5.append(v1);
        v5.append(", sha1=");
        v5.append(v2);
        v5.append(", atk=");
        v5.append(v3);
        v5.append(", ver=12451009.false");
        return v5.toString();
    }
}

