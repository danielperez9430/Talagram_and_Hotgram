package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.IResolveAccountCallbacks;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface ISignInService extends IInterface {
    public abstract class Stub extends zzb implements ISignInService {
        public class Proxy extends zza implements ISignInService {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.signin.internal.ISignInService");
            }

            public void authAccount(AuthAccountRequest arg2, ISignInCallbacks arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                zzc.zza(v0, ((IInterface)arg3));
                ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
            }

            public void clearAccountFromSessionStore(int arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeInt(arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(7, v0);
            }

            public void getCurrentAccount(ISignInCallbacks arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(11, v0);
            }

            public void onCheckServerAuthorization(CheckServerAuthResult arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
            }

            public void onUploadServerAuthCode(boolean arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(4, v0);
            }

            public void recordConsent(RecordConsentRequest arg2, ISignInCallbacks arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                zzc.zza(v0, ((IInterface)arg3));
                ((zza)this).transactAndReadExceptionReturnVoid(10, v0);
            }

            public void resolveAccount(ResolveAccountRequest arg2, IResolveAccountCallbacks arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                zzc.zza(v0, ((IInterface)arg3));
                ((zza)this).transactAndReadExceptionReturnVoid(5, v0);
            }

            public void saveAccountToSessionStore(int arg2, Account arg3, ISignInCallbacks arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                v0.writeInt(arg2);
                zzc.zza(v0, ((Parcelable)arg3));
                zzc.zza(v0, ((IInterface)arg4));
                ((zza)this).transactAndReadExceptionReturnVoid(8, v0);
            }

            public void saveDefaultAccountToSharedPref(IAccountAccessor arg2, int arg3, boolean arg4) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                v0.writeInt(arg3);
                zzc.zza(v0, arg4);
                ((zza)this).transactAndReadExceptionReturnVoid(9, v0);
            }

            public void setGamesHasBeenGreeted(boolean arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(13, v0);
            }

            public void signIn(SignInRequest arg2, ISignInCallbacks arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                zzc.zza(v0, ((IInterface)arg3));
                ((zza)this).transactAndReadExceptionReturnVoid(12, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.signin.internal.ISignInService");
        }

        public static ISignInService asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
            if((v0 instanceof ISignInService)) {
                return ((ISignInService)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 2: {
                    goto label_51;
                }
                case 3: {
                    goto label_47;
                }
                case 4: {
                    goto label_44;
                }
                case 5: {
                    goto label_38;
                }
                case 7: {
                    goto label_35;
                }
                case 8: {
                    goto label_28;
                }
                case 9: {
                    goto label_22;
                }
                case 10: {
                    goto label_16;
                }
                case 11: {
                    goto label_12;
                }
                case 12: {
                    goto label_6;
                }
                case 13: {
                    goto label_3;
                }
            }

            return 0;
        label_51:
            this.authAccount(zzc.zza(arg2, AuthAccountRequest.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks$Stub.asInterface(arg2.readStrongBinder()));
            goto label_56;
        label_35:
            this.clearAccountFromSessionStore(arg2.readInt());
            goto label_56;
        label_3:
            this.setGamesHasBeenGreeted(zzc.zza(arg2));
            goto label_56;
        label_38:
            this.resolveAccount(zzc.zza(arg2, ResolveAccountRequest.CREATOR), com.google.android.gms.common.internal.IResolveAccountCallbacks$Stub.asInterface(arg2.readStrongBinder()));
            goto label_56;
        label_22:
            this.saveDefaultAccountToSharedPref(com.google.android.gms.common.internal.IAccountAccessor$Stub.asInterface(arg2.readStrongBinder()), arg2.readInt(), zzc.zza(arg2));
            goto label_56;
        label_6:
            this.signIn(zzc.zza(arg2, SignInRequest.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks$Stub.asInterface(arg2.readStrongBinder()));
            goto label_56;
        label_44:
            this.onUploadServerAuthCode(zzc.zza(arg2));
            goto label_56;
        label_28:
            this.saveAccountToSessionStore(arg2.readInt(), zzc.zza(arg2, Account.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks$Stub.asInterface(arg2.readStrongBinder()));
            goto label_56;
        label_12:
            this.getCurrentAccount(com.google.android.gms.signin.internal.ISignInCallbacks$Stub.asInterface(arg2.readStrongBinder()));
            goto label_56;
        label_47:
            this.onCheckServerAuthorization(zzc.zza(arg2, CheckServerAuthResult.CREATOR));
            goto label_56;
        label_16:
            this.recordConsent(zzc.zza(arg2, RecordConsentRequest.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks$Stub.asInterface(arg2.readStrongBinder()));
        label_56:
            arg3.writeNoException();
            return 1;
        }
    }

    void authAccount(AuthAccountRequest arg1, ISignInCallbacks arg2);

    void clearAccountFromSessionStore(int arg1);

    void getCurrentAccount(ISignInCallbacks arg1);

    void onCheckServerAuthorization(CheckServerAuthResult arg1);

    void onUploadServerAuthCode(boolean arg1);

    void recordConsent(RecordConsentRequest arg1, ISignInCallbacks arg2);

    void resolveAccount(ResolveAccountRequest arg1, IResolveAccountCallbacks arg2);

    void saveAccountToSessionStore(int arg1, Account arg2, ISignInCallbacks arg3);

    void saveDefaultAccountToSharedPref(IAccountAccessor arg1, int arg2, boolean arg3);

    void setGamesHasBeenGreeted(boolean arg1);

    void signIn(SignInRequest arg1, ISignInCallbacks arg2);
}

