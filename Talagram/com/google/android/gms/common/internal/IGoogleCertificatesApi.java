package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.GoogleCertificatesQuery;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IGoogleCertificatesApi extends IInterface {
    public abstract class Stub extends zzb implements IGoogleCertificatesApi {
        public class Proxy extends zza implements IGoogleCertificatesApi {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
            }

            public IObjectWrapper getGoogleCertificates() {
                Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
                IObjectWrapper v1 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public IObjectWrapper getGoogleReleaseCertificates() {
                Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
                IObjectWrapper v1 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public boolean isGoogleOrPlatformSigned(GoogleCertificatesQuery arg2, IObjectWrapper arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                zzc.zza(v0, ((IInterface)arg3));
                Parcel v2 = ((zza)this).transactAndReadException(5, v0);
                boolean v3 = zzc.zza(v2);
                v2.recycle();
                return v3;
            }

            public boolean isGoogleReleaseSigned(String arg2, IObjectWrapper arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                zzc.zza(v0, ((IInterface)arg3));
                Parcel v2 = ((zza)this).transactAndReadException(3, v0);
                boolean v3 = zzc.zza(v2);
                v2.recycle();
                return v3;
            }

            public boolean isGoogleSigned(String arg2, IObjectWrapper arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeString(arg2);
                zzc.zza(v0, ((IInterface)arg3));
                Parcel v2 = ((zza)this).transactAndReadException(4, v0);
                boolean v3 = zzc.zza(v2);
                v2.recycle();
                return v3;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        }

        public static IGoogleCertificatesApi asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
            if((v0 instanceof IGoogleCertificatesApi)) {
                return ((IGoogleCertificatesApi)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 1: {
                    goto label_23;
                }
                case 2: {
                    goto label_21;
                }
                case 3: {
                    goto label_14;
                }
                case 4: {
                    goto label_9;
                }
                case 5: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            boolean v1 = this.isGoogleOrPlatformSigned(zzc.zza(arg2, GoogleCertificatesQuery.CREATOR), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()));
            goto label_18;
        label_21:
            IObjectWrapper v1_1 = this.getGoogleReleaseCertificates();
            goto label_24;
        label_23:
            v1_1 = this.getGoogleCertificates();
        label_24:
            arg3.writeNoException();
            zzc.zza(arg3, ((IInterface)v1_1));
            return 1;
        label_9:
            v1 = this.isGoogleSigned(arg2.readString(), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()));
            goto label_18;
        label_14:
            v1 = this.isGoogleReleaseSigned(arg2.readString(), com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()));
        label_18:
            arg3.writeNoException();
            zzc.zza(arg3, v1);
            return 1;
        }
    }

    IObjectWrapper getGoogleCertificates();

    IObjectWrapper getGoogleReleaseCertificates();

    boolean isGoogleOrPlatformSigned(GoogleCertificatesQuery arg1, IObjectWrapper arg2);

    boolean isGoogleReleaseSigned(String arg1, IObjectWrapper arg2);

    boolean isGoogleSigned(String arg1, IObjectWrapper arg2);
}

