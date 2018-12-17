package org.telegram.messenger;

import org.telegram.tgnet.SerializedData;

public class MessageKeyData {
    public byte[] aesIv;
    public byte[] aesKey;

    public MessageKeyData() {
        super();
    }

    public static MessageKeyData generateMessageKeyData(byte[] arg8, byte[] arg9, boolean arg10, int arg11) {
        MessageKeyData v0 = new MessageKeyData();
        if(arg8 != null) {
            if(arg8.length == 0) {
            }
            else {
                int v2 = 8;
                int v10 = arg10 ? 8 : 0;
                int v3 = 16;
                switch(arg11) {
                    case 1: {
                        goto label_46;
                    }
                    case 2: {
                        goto label_15;
                    }
                }

                return v0;
            label_46:
                SerializedData v11 = new SerializedData();
                v11.writeBytes(arg9);
                v11.writeBytes(arg8, v10, 32);
                byte[] v5 = Utilities.computeSHA1(v11.toByteArray());
                v11.cleanup();
                v11 = new SerializedData();
                v11.writeBytes(arg8, v10 + 32, v3);
                v11.writeBytes(arg9);
                v11.writeBytes(arg8, v10 + 48, v3);
                byte[] v6 = Utilities.computeSHA1(v11.toByteArray());
                v11.cleanup();
                v11 = new SerializedData();
                v11.writeBytes(arg8, v10 + 64, 32);
                v11.writeBytes(arg9);
                byte[] v7 = Utilities.computeSHA1(v11.toByteArray());
                v11.cleanup();
                v11 = new SerializedData();
                v11.writeBytes(arg9);
                v11.writeBytes(arg8, v10 + 96, 32);
                arg8 = Utilities.computeSHA1(v11.toByteArray());
                v11.cleanup();
                SerializedData v9 = new SerializedData();
                v9.writeBytes(v5, 0, v2);
                v9.writeBytes(v6, v2, 12);
                v9.writeBytes(v7, 4, 12);
                v0.aesKey = v9.toByteArray();
                v9.cleanup();
                v9 = new SerializedData();
                v9.writeBytes(v5, v2, 12);
                v9.writeBytes(v6, 0, v2);
                v9.writeBytes(v7, v3, 4);
                v9.writeBytes(arg8, 0, v2);
                goto label_96;
            label_15:
                v11 = new SerializedData();
                v11.writeBytes(arg9, 0, v3);
                v11.writeBytes(arg8, v10, 36);
                v5 = Utilities.computeSHA256(v11.toByteArray());
                v11.cleanup();
                v11 = new SerializedData();
                v11.writeBytes(arg8, v10 + 40, 36);
                v11.writeBytes(arg9, 0, v3);
                arg8 = Utilities.computeSHA256(v11.toByteArray());
                v11.cleanup();
                v9 = new SerializedData();
                v9.writeBytes(v5, 0, v2);
                v9.writeBytes(arg8, v2, v3);
                v9.writeBytes(v5, 24, v2);
                v0.aesKey = v9.toByteArray();
                v9.cleanup();
                v9 = new SerializedData();
                v9.writeBytes(arg8, 0, v2);
                v9.writeBytes(v5, v2, v3);
                v9.writeBytes(arg8, 24, v2);
            label_96:
                v0.aesIv = v9.toByteArray();
                v9.cleanup();
                return v0;
            }
        }

        v0.aesIv = null;
        v0.aesKey = null;
        return v0;
    }
}

