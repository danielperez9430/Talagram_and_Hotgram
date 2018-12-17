package org.telegram.customization.util.crypto.wire;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ParceledResponse {
    private static final Charset UTF8;
    private final byte[] body;

    static {
        ParceledResponse.UTF8 = Charset.forName("UTF8");
    }

    public ParceledResponse(String arg2) {
        this(arg2.getBytes(ParceledResponse.UTF8));
    }

    public ParceledResponse(byte[] arg1) {
        super();
        this.body = arg1;
    }

    public static ParceledResponse decode(String arg1) {
        DataInputStream v1 = new DataInputStream(new ByteArrayInputStream(ParcelFormatter.decode(Base64.decode(arg1, 0))));
        try {
            byte[] v0 = new byte[v1.readInt()];
            v1.read(v0);
            return new ParceledResponse(v0);
        }
        catch(IOException v1_1) {
            throw new IllegalStateException(((Throwable)v1_1));
        }
    }

    public static ParceledResponse decode(byte[] arg2) {
        try {
            return ParceledResponse.decode(new String(arg2, "UTF-8"));
        }
        catch(UnsupportedEncodingException ) {
            return ParceledResponse.decode(new String(arg2));
        }
    }

    public String encode() {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        DataOutputStream v1 = new DataOutputStream(((OutputStream)v0));
        try {
            v1.writeInt(this.body.length);
            v1.write(this.body);
            v1.flush();
            return Base64.encodeToString(ParcelFormatter.encode(v0.toByteArray()), 0);
        }
        catch(IOException ) {
            return "";
        }
    }

    public boolean equals(Object arg3) {
        if(this == (((ParceledResponse)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return Arrays.equals(this.body, ((ParceledResponse)arg3).body);
            }
        }

        return 0;
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getBodyAsStr() {
        return new String(this.body, ParceledResponse.UTF8);
    }

    public int hashCode() {
        return Arrays.hashCode(this.body);
    }

    public String string() {
        return new String(this.body, ParceledResponse.UTF8);
    }

    public String toString() {
        return String.format("{body=\'%s\'}", new String(this.body, ParceledResponse.UTF8));
    }
}

