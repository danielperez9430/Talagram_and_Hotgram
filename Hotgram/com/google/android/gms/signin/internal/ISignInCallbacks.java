package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISignInCallbacks extends IInterface {
    public abstract class Stub extends zzb implements ISignInCallbacks {
        public class Proxy extends zza implements ISignInCallbacks {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.signin.internal.ISignInCallbacks");
            }

            public void onAuthAccountComplete(ConnectionResult arg2, AuthAccountResult arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                zzc.zza(v0, ((Parcelable)arg3));
                ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
            }

            public void onGetCurrentAccountComplete(Status arg2, GoogleSignInAccount arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                zzc.zza(v0, ((Parcelable)arg3));
                ((zza)this).transactAndReadExceptionReturnVoid(7, v0);
            }

            public void onRecordConsentComplete(Status arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(6, v0);
            }

            public void onSaveAccountToSessionStoreComplete(Status arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(4, v0);
            }

            public void onSignInComplete(SignInResponse arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(8, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        public static ISignInCallbacks asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
            if((v0 instanceof ISignInCallbacks)) {
                return ((ISignInCallbacks)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 3: {
                    goto label_21;
                }
                case 4: {
                    goto label_17;
                }
                case 6: {
                    goto label_13;
                }
                case 7: {
                    goto label_7;
                }
                case 8: {
                    goto label_3;
                }
            }

            return 0;
        label_17:
            this.onSaveAccountToSessionStoreComplete(zzc.zza(arg2, Status.CREATOR));
            goto label_26;
        label_3:
            this.onSignInComplete(zzc.zza(arg2, SignInResponse.CREATOR));
            goto label_26;
        label_21:
            this.onAuthAccountComplete(zzc.zza(arg2, ConnectionResult.CREATOR), zzc.zza(arg2, AuthAccountResult.CREATOR));
            goto label_26;
        label_7:
            this.onGetCurrentAccountComplete(zzc.zza(arg2, Status.CREATOR), zzc.zza(arg2, GoogleSignInAccount.CREATOR));
            goto label_26;
        label_13:
            this.onRecordConsentComplete(zzc.zza(arg2, Status.CREATOR));
        label_26:
            arg3.writeNoException();
            return 1;
        }
    }

    void onAuthAccountComplete(ConnectionResult arg1, AuthAccountResult arg2);

    void onGetCurrentAccountComplete(Status arg1, GoogleSignInAccount arg2);

    void onRecordConsentComplete(Status arg1);

    void onSaveAccountToSessionStoreComplete(Status arg1);

    void onSignInComplete(SignInResponse arg1);
}

