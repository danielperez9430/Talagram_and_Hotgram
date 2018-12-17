package com.googlecode.mp4parser.boxes.piff;

import com.coremedia.iso.Hex;
import com.googlecode.mp4parser.contentprotection.GenericHeader;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ProtectionSpecificHeader {
    protected static Map uuidRegistry;

    static {
        ProtectionSpecificHeader.uuidRegistry = new HashMap();
    }

    public ProtectionSpecificHeader() {
        super();
    }

    public static ProtectionSpecificHeader createFor(UUID arg1, ByteBuffer arg2) {
        GenericHeader v1_3;
        Object v1 = ProtectionSpecificHeader.uuidRegistry.get(arg1);
        if(v1 != null) {
            try {
                v1 = ((Class)v1).newInstance();
            }
            catch(IllegalAccessException v1_1) {
                throw new RuntimeException(((Throwable)v1_1));
            }
            catch(InstantiationException v1_2) {
                throw new RuntimeException(((Throwable)v1_2));
            }
        }
        else {
            v1 = null;
        }

        if(v1 == null) {
            v1_3 = new GenericHeader();
        }

        ((ProtectionSpecificHeader)v1_3).parse(arg2);
        return ((ProtectionSpecificHeader)v1_3);
    }

    public boolean equals(Object arg2) {
        throw new RuntimeException("somebody called equals on me but that\'s not supposed to happen.");
    }

    public abstract ByteBuffer getData();

    public abstract UUID getSystemId();

    public abstract void parse(ByteBuffer arg1);

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("ProtectionSpecificHeader");
        v0.append("{data=");
        ByteBuffer v1 = this.getData().duplicate();
        v1.rewind();
        byte[] v2 = new byte[v1.limit()];
        v1.get(v2);
        v0.append(Hex.encodeHex(v2));
        v0.append('}');
        return v0.toString();
    }
}

