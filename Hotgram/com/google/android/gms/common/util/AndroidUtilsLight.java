package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AndroidUtilsLight {
    public static final String DIGEST_ALGORITHM_SHA1 = "SHA1";
    public static final String DIGEST_ALGORITHM_SHA512 = "SHA-512";

    public AndroidUtilsLight() {
        super();
    }

    public static MessageDigest getMessageDigest(String arg2) {
        MessageDigest v1;
        int v0;
        for(v0 = 0; v0 < 2; ++v0) {
            try {
                v1 = MessageDigest.getInstance(arg2);
                if(v1 == null) {
                    goto label_6;
                }
            }
            catch(NoSuchAlgorithmException ) {
                goto label_6;
            }

            return v1;
        label_6:
        }

        return null;
    }

    public static byte[] getPackageCertificateHashBytes(Context arg1, String arg2) {
        return AndroidUtilsLight.getPackageCertificateHashBytes(arg1, arg2, "SHA1");
    }

    public static byte[] getPackageCertificateHashBytes(Context arg1, String arg2, String arg3) {
        PackageInfo v1 = Wrappers.packageManager(arg1).getPackageInfo(arg2, 64);
        if(v1.signatures != null && v1.signatures.length == 1) {
            MessageDigest v2 = AndroidUtilsLight.getMessageDigest(arg3);
            if(v2 != null) {
                return v2.digest(v1.signatures[0].toByteArray());
            }
        }

        return null;
    }
}

