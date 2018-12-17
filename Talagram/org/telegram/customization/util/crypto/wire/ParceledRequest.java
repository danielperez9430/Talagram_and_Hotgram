package org.telegram.customization.util.crypto.wire;

import android.util.Base64;
import com.google.a.f;
import com.google.a.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;
import java.util.Map;

public class ParceledRequest {
    private static final byte[] EMPTY_BODY;
    private static final Charset UTF8;
    private final byte[] body;
    private static final f gson;
    private final Map parameters;
    private final String path;

    static {
        ParceledRequest.gson = new g().a().b();
        ParceledRequest.UTF8 = Charset.forName("UTF8");
        ParceledRequest.EMPTY_BODY = new byte[0];
    }

    public ParceledRequest(String arg2) {
        this(arg2, ParceledRequest.EMPTY_BODY);
    }

    ParceledRequest(String arg3, byte[] arg4) {
        super();
        this.parameters = new LinkedHashMap();
        if(arg3 != null && !arg3.matches("\\s*")) {
            if(arg4.length <= 4194304) {
                this.path = arg3;
                this.body = arg4;
                this.setParam("tk", WireAccessLayer.getMessagesCount());
                return;
            }
            else {
                throw new IllegalArgumentException("Body is too big!");
            }
        }

        throw new IllegalArgumentException("Path should not be null or blank.");
    }

    public ParceledRequest(String arg2, String arg3) {
        this(arg2, arg3.getBytes(ParceledRequest.UTF8));
    }

    public static ParceledRequest decode(String arg5) {
        DataInputStream v5 = new DataInputStream(new ByteArrayInputStream(ParcelFormatter.decode(Base64.decode(arg5, 2))));
        try {
            int v0 = v5.readInt();
            int v1 = 4194304;
            if(v0 <= v1) {
                byte[] v0_1 = new byte[v0];
                v5.read(v0_1);
                String v2 = new String(v0_1);
                v0 = v5.readInt();
                if(v0 <= v1) {
                    v0_1 = new byte[v0];
                    v5.read(v0_1);
                    Object v0_2 = ParceledRequest.gson.a(new String(v0_1), Map.class);
                    int v3 = v5.readInt();
                    if(v3 <= v1) {
                        byte[] v1_1 = new byte[v3];
                        v5.read(v1_1);
                        ParceledRequest v5_2 = new ParceledRequest(v2, v1_1);
                        Iterator v0_3 = ((Map)v0_2).entrySet().iterator();
                        while(v0_3.hasNext()) {
                            Object v1_2 = v0_3.next();
                            v5_2.setParam(((Map$Entry)v1_2).getKey(), ((Map$Entry)v1_2).getValue());
                        }

                        return v5_2;
                    }

                    throw new IllegalArgumentException("Max allowable size is: 4194304");
                }

                throw new IllegalArgumentException("Max allowable size is: 4194304");
            }

            throw new IllegalArgumentException("Max allowable size is: 4194304");
        }
        catch(IOException v5_1) {
            throw new IllegalStateException(((Throwable)v5_1));
        }
    }

    public PackedRequest encode() {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        DataOutputStream v1 = new DataOutputStream(((OutputStream)v0));
        byte[] v2 = this.path.getBytes(ParceledRequest.UTF8);
        byte[] v3 = ParceledRequest.gson.a(this.parameters).getBytes(ParceledRequest.UTF8);
        try {
            v1.writeInt(v2.length);
            v1.write(v2);
            v1.writeInt(v3.length);
            v1.write(v3);
            v1.writeInt(this.body.length);
            v1.write(this.body);
            v1.flush();
            return PackedRequest.from(Base64.encodeToString(ParcelFormatter.encode(v0.toByteArray()), 2));
        }
        catch(IOException v0_1) {
            throw new IllegalStateException(((Throwable)v0_1));
        }
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((ParceledRequest)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!ParceledRequest.equalsHelper(this.path, ((ParceledRequest)arg5).path) || !Arrays.equals(this.body, ((ParceledRequest)arg5).body) || !ParceledRequest.equalsHelper(this.parameters, ((ParceledRequest)arg5).parameters)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public static boolean equalsHelper(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != null) {
            v0 = arg0.equals(arg1);
        }
        else if(arg1 == null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getBodyAsStr() {
        return new String(this.body, ParceledRequest.UTF8);
    }

    public String getParam(String arg2) {
        return this.parameters.get(arg2);
    }

    public Map getParameters() {
        return this.parameters;
    }

    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        return ParceledRequest.hashHelper(new Object[]{this.path, this.parameters}) * 31 + Arrays.hashCode(this.body);
    }

    public static int hashHelper(Object[] arg0) {
        return Arrays.hashCode(arg0);
    }

    public void setParam(String arg2, String arg3) {
        this.parameters.put(arg2, arg3);
    }

    public String toString() {
        return String.format("{path=\'%s\', params=%s, body=\'%s\'}", this.path, ParceledRequest.gson.a(this.parameters), new String(this.body, ParceledRequest.UTF8));
    }
}

