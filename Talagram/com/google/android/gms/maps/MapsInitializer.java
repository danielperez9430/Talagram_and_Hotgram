package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import javax.annotation.concurrent.GuardedBy;

public final class MapsInitializer {
    @GuardedBy(value="MapsInitializer.class") private static boolean zzbl = false;

    static {
    }

    private MapsInitializer() {
        super();
    }

    public static int initialize(Context arg3) {
        int v3_3;
        zze v3_2;
        Class v0 = MapsInitializer.class;
        __monitor_enter(v0);
        try {
            Preconditions.checkNotNull(arg3, "Context is null");
            if(!MapsInitializer.zzbl) {
                goto label_9;
            }
        }
        catch(Throwable v3) {
            goto label_27;
        }

        __monitor_exit(v0);
        return 0;
        try {
        label_9:
            v3_2 = zzbz.zza(arg3);
            goto label_10;
        }
        catch(Throwable v3) {
        }
        catch(GooglePlayServicesNotAvailableException v3_1) {
            try {
                v3_3 = v3_1.errorCode;
            }
            catch(Throwable v3) {
                goto label_27;
            }

            __monitor_exit(v0);
            return v3_3;
            try {
            label_10:
                CameraUpdateFactory.zza(v3_2.zzd());
                BitmapDescriptorFactory.zza(v3_2.zze());
                goto label_15;
            }
            catch(Throwable v3) {
            }
            catch(RemoteException v3_4) {
                try {
                    throw new RuntimeRemoteException(v3_4);
                label_15:
                    MapsInitializer.zzbl = true;
                }
                catch(Throwable v3) {
                label_27:
                    __monitor_exit(v0);
                    throw v3;
                }
            }
        }

        __monitor_exit(v0);
        return 0;
    }
}

