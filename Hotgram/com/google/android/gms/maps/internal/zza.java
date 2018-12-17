package com.google.android.gms.maps.internal;

public final class zza {
    public static byte zza(Boolean arg0) {
        if(arg0 != null) {
            if(arg0.booleanValue()) {
                return 1;
            }

            return 0;
        }

        return -1;
    }

    public static Boolean zza(byte arg0) {
        switch(arg0) {
            case 0: {
                goto label_5;
            }
            case 1: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return Boolean.TRUE;
    label_5:
        return Boolean.FALSE;
    }
}

