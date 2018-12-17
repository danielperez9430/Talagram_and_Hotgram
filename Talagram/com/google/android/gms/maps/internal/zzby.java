package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;

public final class zzby {
    private zzby() {
        super();
    }

    public static void zza(Bundle arg3, Bundle arg4) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                Parcelable v0 = zzby.zza(arg3, "MapOptions");
                if(v0 != null) {
                    zzby.zza(arg4, "MapOptions", v0);
                }

                v0 = zzby.zza(arg3, "StreetViewPanoramaOptions");
                if(v0 != null) {
                    zzby.zza(arg4, "StreetViewPanoramaOptions", v0);
                }

                v0 = zzby.zza(arg3, "camera");
                if(v0 != null) {
                    zzby.zza(arg4, "camera", v0);
                }

                if(arg3.containsKey("position")) {
                    arg4.putString("position", arg3.getString("position"));
                }

                if(!arg3.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                    return;
                }

                arg4.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", arg3.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    public static void zza(Bundle arg2, String arg3, Parcelable arg4) {
        arg2.setClassLoader(zzby.class.getClassLoader());
        Bundle v0 = arg2.getBundle("map_state");
        if(v0 == null) {
            v0 = new Bundle();
        }

        v0.setClassLoader(zzby.class.getClassLoader());
        v0.putParcelable(arg3, arg4);
        arg2.putBundle("map_state", v0);
    }

    private static Parcelable zza(Bundle arg2, String arg3) {
        Parcelable v0 = null;
        if(arg2 == null) {
            return v0;
        }

        arg2.setClassLoader(zzby.class.getClassLoader());
        arg2 = arg2.getBundle("map_state");
        if(arg2 == null) {
            return v0;
        }

        arg2.setClassLoader(zzby.class.getClassLoader());
        return arg2.getParcelable(arg3);
    }
}

