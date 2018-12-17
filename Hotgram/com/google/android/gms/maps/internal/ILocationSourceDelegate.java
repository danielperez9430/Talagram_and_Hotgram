package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;

public interface ILocationSourceDelegate extends IInterface {
    public abstract class zza extends zzb implements ILocationSourceDelegate {
        public zza() {
            super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
        }

        protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            zzai v1_3;
            switch(arg1) {
                case 1: {
                    goto label_5;
                }
                case 2: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            this.deactivate();
            goto label_19;
        label_5:
            IBinder v1 = arg2.readStrongBinder();
            if(v1 == null) {
                zzah v1_1 = null;
            }
            else {
                IInterface v2 = v1.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                if((v2 instanceof zzah)) {
                    IInterface v1_2 = v2;
                }
                else {
                    v1_3 = new zzai(v1);
                }
            }

            this.activate(((zzah)v1_3));
        label_19:
            arg3.writeNoException();
            return 1;
        }
    }

    void activate(zzah arg1);

    void deactivate();
}

