package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
import com.google.android.exoplayer2.util.Assertions;

public final class TrackEncryptionBox {
    private static final String TAG = "TrackEncryptionBox";
    public final CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final int initializationVectorSize;
    public final boolean isEncrypted;
    public final String schemeType;

    public TrackEncryptionBox(boolean arg4, String arg5, int arg6, byte[] arg7, int arg8, int arg9, byte[] arg10) {
        super();
        int v0 = 0;
        int v2 = arg6 == 0 ? 1 : 0;
        if(arg10 == null) {
            v0 = 1;
        }

        Assertions.checkArgument(v0 ^ v2);
        this.isEncrypted = arg4;
        this.schemeType = arg5;
        this.initializationVectorSize = arg6;
        this.defaultInitializationVector = arg10;
        this.cryptoData = new CryptoData(TrackEncryptionBox.schemeToCryptoMode(arg5), arg7, arg8, arg9);
    }

    private static int schemeToCryptoMode(String arg5) {
        if(arg5 == null) {
            return 1;
        }

        int v1 = -1;
        int v2 = arg5.hashCode();
        int v4 = 2;
        if(v2 != 3046605) {
            if(v2 != 3046671) {
                if(v2 != 3049879) {
                    if(v2 != 3049895) {
                    }
                    else if(arg5.equals("cens")) {
                        v1 = 1;
                    }
                }
                else if(arg5.equals("cenc")) {
                    v1 = 0;
                }
            }
            else if(arg5.equals("cbcs")) {
                v1 = 3;
            }
        }
        else if(arg5.equals("cbc1")) {
            v1 = 2;
        }

        switch(v1) {
            case 0: 
            case 1: {
                return 1;
            }
            case 2: 
            case 3: {
                return v4;
            }
        }

        Log.w("TrackEncryptionBox", "Unsupported protection scheme type \'" + arg5 + "\'. Assuming AES-CTR crypto mode.");
        return 1;
    }
}

