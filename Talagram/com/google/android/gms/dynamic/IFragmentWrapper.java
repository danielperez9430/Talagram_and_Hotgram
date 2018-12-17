package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IFragmentWrapper extends IInterface {
    public abstract class Stub extends zzb implements IFragmentWrapper {
        public class Proxy extends zza implements IFragmentWrapper {
            Proxy(IBinder arg2) {
                super(arg2, "com.google.android.gms.dynamic.IFragmentWrapper");
            }

            public IObjectWrapper getActivity() {
                Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
                IObjectWrapper v1 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public Bundle getArguments() {
                Parcel v0 = ((zza)this).transactAndReadException(3, ((zza)this).obtainAndWriteInterfaceToken());
                Parcelable v1 = zzc.zza(v0, Bundle.CREATOR);
                v0.recycle();
                return ((Bundle)v1);
            }

            public int getId() {
                Parcel v0 = ((zza)this).transactAndReadException(4, ((zza)this).obtainAndWriteInterfaceToken());
                int v1 = v0.readInt();
                v0.recycle();
                return v1;
            }

            public IFragmentWrapper getParentFragment() {
                Parcel v0 = ((zza)this).transactAndReadException(5, ((zza)this).obtainAndWriteInterfaceToken());
                IFragmentWrapper v1 = Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public IObjectWrapper getResources() {
                Parcel v0 = ((zza)this).transactAndReadException(6, ((zza)this).obtainAndWriteInterfaceToken());
                IObjectWrapper v1 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public boolean getRetainInstance() {
                Parcel v0 = ((zza)this).transactAndReadException(7, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public String getTag() {
                Parcel v0 = ((zza)this).transactAndReadException(8, ((zza)this).obtainAndWriteInterfaceToken());
                String v1 = v0.readString();
                v0.recycle();
                return v1;
            }

            public IFragmentWrapper getTargetFragment() {
                Parcel v0 = ((zza)this).transactAndReadException(9, ((zza)this).obtainAndWriteInterfaceToken());
                IFragmentWrapper v1 = Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public int getTargetRequestCode() {
                Parcel v0 = ((zza)this).transactAndReadException(10, ((zza)this).obtainAndWriteInterfaceToken());
                int v1 = v0.readInt();
                v0.recycle();
                return v1;
            }

            public boolean getUserVisibleHint() {
                Parcel v0 = ((zza)this).transactAndReadException(11, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public IObjectWrapper getView() {
                Parcel v0 = ((zza)this).transactAndReadException(12, ((zza)this).obtainAndWriteInterfaceToken());
                IObjectWrapper v1 = com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(v0.readStrongBinder());
                v0.recycle();
                return v1;
            }

            public boolean isAdded() {
                Parcel v0 = ((zza)this).transactAndReadException(13, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public boolean isDetached() {
                Parcel v0 = ((zza)this).transactAndReadException(14, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public boolean isHidden() {
                Parcel v0 = ((zza)this).transactAndReadException(15, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public boolean isInLayout() {
                Parcel v0 = ((zza)this).transactAndReadException(16, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public boolean isRemoving() {
                Parcel v0 = ((zza)this).transactAndReadException(17, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public boolean isResumed() {
                Parcel v0 = ((zza)this).transactAndReadException(18, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public boolean isVisible() {
                Parcel v0 = ((zza)this).transactAndReadException(19, ((zza)this).obtainAndWriteInterfaceToken());
                boolean v1 = zzc.zza(v0);
                v0.recycle();
                return v1;
            }

            public void registerForContextMenu(IObjectWrapper arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(20, v0);
            }

            public void setHasOptionsMenu(boolean arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(21, v0);
            }

            public void setMenuVisibility(boolean arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(22, v0);
            }

            public void setRetainInstance(boolean arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(23, v0);
            }

            public void setUserVisibleHint(boolean arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, arg2);
                ((zza)this).transactAndReadExceptionReturnVoid(24, v0);
            }

            public void startActivity(Intent arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(25, v0);
            }

            public void startActivityForResult(Intent arg2, int arg3) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((Parcelable)arg2));
                v0.writeInt(arg3);
                ((zza)this).transactAndReadExceptionReturnVoid(26, v0);
            }

            public void unregisterForContextMenu(IObjectWrapper arg2) {
                Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
                zzc.zza(v0, ((IInterface)arg2));
                ((zza)this).transactAndReadExceptionReturnVoid(27, v0);
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static IFragmentWrapper asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            if((v0 instanceof IFragmentWrapper)) {
                return ((IFragmentWrapper)v0);
            }

            return new Proxy(arg2);
        }

        protected boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
            switch(arg1) {
                case 2: {
                    goto label_75;
                }
                case 3: {
                    goto label_71;
                }
                case 4: {
                    goto label_67;
                }
                case 5: {
                    goto label_65;
                }
                case 6: {
                    goto label_63;
                }
                case 7: {
                    goto label_59;
                }
                case 8: {
                    goto label_55;
                }
                case 9: {
                    goto label_53;
                }
                case 10: {
                    goto label_51;
                }
                case 11: {
                    goto label_49;
                }
                case 12: {
                    goto label_47;
                }
                case 13: {
                    goto label_45;
                }
                case 14: {
                    goto label_43;
                }
                case 15: {
                    goto label_41;
                }
                case 16: {
                    goto label_39;
                }
                case 17: {
                    goto label_37;
                }
                case 18: {
                    goto label_35;
                }
                case 19: {
                    goto label_33;
                }
                case 20: {
                    goto label_28;
                }
                case 21: {
                    goto label_25;
                }
                case 22: {
                    goto label_22;
                }
                case 23: {
                    goto label_19;
                }
                case 24: {
                    goto label_16;
                }
                case 25: {
                    goto label_12;
                }
                case 26: {
                    goto label_7;
                }
                case 27: {
                    goto label_3;
                }
            }

            return 0;
        label_33:
            boolean v1 = this.isVisible();
            goto label_60;
        label_35:
            v1 = this.isResumed();
            goto label_60;
        label_37:
            v1 = this.isRemoving();
            goto label_60;
        label_39:
            v1 = this.isInLayout();
            goto label_60;
        label_41:
            v1 = this.isHidden();
            goto label_60;
        label_43:
            v1 = this.isDetached();
            goto label_60;
        label_45:
            v1 = this.isAdded();
            goto label_60;
        label_47:
            IObjectWrapper v1_1 = this.getView();
            goto label_76;
        label_49:
            v1 = this.getUserVisibleHint();
            goto label_60;
        label_51:
            arg1 = this.getTargetRequestCode();
            goto label_68;
        label_53:
            IFragmentWrapper v1_2 = this.getTargetFragment();
            goto label_76;
        label_55:
            String v1_3 = this.getTag();
            arg3.writeNoException();
            arg3.writeString(v1_3);
            return 1;
        label_59:
            v1 = this.getRetainInstance();
        label_60:
            arg3.writeNoException();
            zzc.zza(arg3, v1);
            return 1;
        label_63:
            v1_1 = this.getResources();
            goto label_76;
        label_65:
            v1_2 = this.getParentFragment();
            goto label_76;
        label_67:
            arg1 = this.getId();
        label_68:
            arg3.writeNoException();
            arg3.writeInt(arg1);
            return 1;
        label_3:
            this.unregisterForContextMenu(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()));
            goto label_31;
        label_71:
            Bundle v1_4 = this.getArguments();
            arg3.writeNoException();
            zzc.zzb(arg3, ((Parcelable)v1_4));
            return 1;
        label_7:
            this.startActivityForResult(zzc.zza(arg2, Intent.CREATOR), arg2.readInt());
            goto label_31;
        label_75:
            v1_1 = this.getActivity();
        label_76:
            arg3.writeNoException();
            zzc.zza(arg3, ((IInterface)v1_2));
            return 1;
        label_12:
            this.startActivity(zzc.zza(arg2, Intent.CREATOR));
            goto label_31;
        label_16:
            this.setUserVisibleHint(zzc.zza(arg2));
            goto label_31;
        label_19:
            this.setRetainInstance(zzc.zza(arg2));
            goto label_31;
        label_22:
            this.setMenuVisibility(zzc.zza(arg2));
            goto label_31;
        label_25:
            this.setHasOptionsMenu(zzc.zza(arg2));
            goto label_31;
        label_28:
            this.registerForContextMenu(com.google.android.gms.dynamic.IObjectWrapper$Stub.asInterface(arg2.readStrongBinder()));
        label_31:
            arg3.writeNoException();
            return 1;
        }
    }

    IObjectWrapper getActivity();

    Bundle getArguments();

    int getId();

    IFragmentWrapper getParentFragment();

    IObjectWrapper getResources();

    boolean getRetainInstance();

    String getTag();

    IFragmentWrapper getTargetFragment();

    int getTargetRequestCode();

    boolean getUserVisibleHint();

    IObjectWrapper getView();

    boolean isAdded();

    boolean isDetached();

    boolean isHidden();

    boolean isInLayout();

    boolean isRemoving();

    boolean isResumed();

    boolean isVisible();

    void registerForContextMenu(IObjectWrapper arg1);

    void setHasOptionsMenu(boolean arg1);

    void setMenuVisibility(boolean arg1);

    void setRetainInstance(boolean arg1);

    void setUserVisibleHint(boolean arg1);

    void startActivity(Intent arg1);

    void startActivityForResult(Intent arg1, int arg2);

    void unregisterForContextMenu(IObjectWrapper arg1);
}

