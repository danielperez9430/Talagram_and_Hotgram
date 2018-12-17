package com.persianswitch.sdk.base.security;

import android.content.Context;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import javax.crypto.Cipher;

public class RSACrypt {
    private final PublicKey a;

    protected RSACrypt(Context arg1) {
        super();
        try {
            this.a = RSACrypt.a(arg1);
            return;
        }
        catch(Exception ) {
            throw new RuntimeException();
        }
    }

    private static PublicKey a(Context arg2) {
        DataInputStream v0 = new DataInputStream(arg2.getResources().getAssets().open("public_key.crt"));
        byte[] v2 = new byte[v0.available()];
        v0.readFully(v2);
        v0.close();
        return CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(v2)).getPublicKey();
    }

    public String a(byte[] arg4) {
        Cipher v0 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        v0.init(1, this.a);
        return Base64.encodeToString(v0.doFinal(arg4), 2);
    }
}

