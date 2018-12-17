package com.persianswitch.sdk.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertificateUtils {
    public CertificateUtils() {
        super();
    }

    public static String a(Context arg3) {
        Certificate v3_5;
        CertificateFactory v3_4;
        PackageInfo v3_2;
        PackageManager v0 = arg3.getPackageManager();
        String v3 = arg3.getPackageName();
        PackageInfo v1 = null;
        int v2 = 64;
        try {
            v3_2 = v0.getPackageInfo(v3, v2);
        }
        catch(PackageManager$NameNotFoundException v3_1) {
            v3_1.printStackTrace();
            v3_2 = v1;
        }

        ByteArrayInputStream v0_1 = new ByteArrayInputStream(v3_2.signatures[0].toByteArray());
        try {
            v3_4 = CertificateFactory.getInstance("X509");
        }
        catch(CertificateException v3_3) {
            v3_3.printStackTrace();
            v3_4 = ((CertificateFactory)v1);
        }

        try {
            v3_5 = v3_4.generateCertificate(((InputStream)v0_1));
        }
        catch(CertificateException v3_3) {
            v3_3.printStackTrace();
            X509Certificate v3_6 = ((X509Certificate)v1);
        }

        try {
            return CertificateUtils.a(MessageDigest.getInstance("SHA1").digest(((X509Certificate)v3_5).getEncoded()));
        }
        catch(CertificateEncodingException v3_7) {
            v3_7.printStackTrace();
        }
        catch(NoSuchAlgorithmException v3_8) {
            v3_8.printStackTrace();
        }

        return ((String)v1);
    }

    private static String a(byte[] arg8) {
        int v2 = 2;
        StringBuilder v0 = new StringBuilder(arg8.length * 2);
        int v1;
        for(v1 = 0; v1 < arg8.length; ++v1) {
            String v3 = Integer.toHexString(arg8[v1]);
            int v4 = v3.length();
            if(v4 == 1) {
                v3 = "0" + v3;
            }

            if(v4 > v2) {
                v3 = v3.substring(v4 - 2, v4);
            }

            v0.append(v3.toUpperCase());
            if(v1 < arg8.length - 1) {
                v0.append(':');
            }
        }

        return v0.toString();
    }
}

