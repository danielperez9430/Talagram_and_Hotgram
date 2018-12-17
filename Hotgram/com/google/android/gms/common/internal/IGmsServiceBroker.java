package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IGmsServiceBroker extends IInterface {
    public abstract class Stub extends Binder implements IGmsServiceBroker {
        final class zza implements IGmsServiceBroker {
            private final IBinder zza;

            zza(IBinder arg1) {
                super();
                this.zza = arg1;
            }

            public final IBinder asBinder() {
                return this.zza;
            }

            public final void getService(IGmsCallbacks arg4, GetServiceRequest arg5) {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder v4_1 = arg4 != null ? arg4.asBinder() : null;
                    v0.writeStrongBinder(v4_1);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.zza.transact(46, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }
        }

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IGmsServiceBroker asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if(v0 != null && ((v0 instanceof IGmsServiceBroker))) {
                return ((IGmsServiceBroker)v0);
            }

            return new zza(arg2);
        }

        protected void getLegacyService(int arg1, IGmsCallbacks arg2, int arg3, String arg4, String arg5, String[] arg6, Bundle arg7, IBinder arg8, String arg9, String arg10) {
            throw new UnsupportedOperationException();
        }

        public boolean onTransact(int arg12, Parcel arg13, Parcel arg14, int arg15) {
            Object v7_2;
            String v10;
            IBinder v8;
            String v9;
            Bundle v7;
            String v5;
            String[] v6;
            Object v0_1;
            if(arg12 > 16777215) {
                return super.onTransact(arg12, arg13, arg14, arg15);
            }

            arg13.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IGmsCallbacks v2 = com.google.android.gms.common.internal.IGmsCallbacks$Stub.asInterface(arg13.readStrongBinder());
            GetServiceRequest v0 = null;
            if(arg12 == 46) {
                if(arg13.readInt() != 0) {
                    v0_1 = GetServiceRequest.CREATOR.createFromParcel(arg13);
                }

                this.getService(v2, v0);
            }
            else {
                if(arg12 == 47) {
                    if(arg13.readInt() != 0) {
                        v0_1 = ValidateAccountRequest.CREATOR.createFromParcel(arg13);
                    }

                    this.validateAccount(v2, ((ValidateAccountRequest)v0));
                    goto label_152;
                }

                int v3 = arg13.readInt();
                String v4 = arg12 != 4 ? arg13.readString() : ((String)v0);
                if(arg12 == 23 || arg12 == 25 || arg12 == 27) {
                label_134:
                    if(arg13.readInt() != 0) {
                        v7_2 = Bundle.CREATOR.createFromParcel(arg13);
                        v5 = ((String)v0);
                        v6 = ((String[])v5);
                        v8 = ((IBinder)v6);
                    }
                    else {
                    label_143:
                        v5 = ((String)v0);
                        v6 = ((String[])v5);
                    label_145:
                        v7 = ((Bundle)v6);
                    label_146:
                        v8 = ((IBinder)v7);
                    }

                    v9 = ((String)v8);
                label_148:
                    v10 = v9;
                }
                else {
                    if(arg12 != 30) {
                        if(arg12 == 34) {
                            goto label_113;
                        }
                        else if(arg12 == 41) {
                            goto label_134;
                        }
                        else if(arg12 != 43) {
                            switch(arg12) {
                                case 1: {
                                    goto label_96;
                                }
                                case 2: {
                                    goto label_134;
                                }
                                default: {
                                    switch(arg12) {
                                        case 9: {
                                            goto label_75;
                                        }
                                        case 10: {
                                            goto label_69;
                                        }
                                        case 5: 
                                        case 6: 
                                        case 7: 
                                        case 8: 
                                        case 11: 
                                        case 12: 
                                        case 13: 
                                        case 14: 
                                        case 15: 
                                        case 16: 
                                        case 17: 
                                        case 18: {
                                            goto label_134;
                                        }
                                        case 19: {
                                            goto label_52;
                                        }
                                        case 20: {
                                            goto label_117;
                                        }
                                        default: {
                                            switch(arg12) {
                                                case 37: 
                                                case 38: {
                                                    goto label_134;
                                                }
                                                default: {
                                                    goto label_143;
                                                label_69:
                                                    String v15 = arg13.readString();
                                                    v6 = arg13.createStringArray();
                                                    v5 = v15;
                                                    v7 = ((Bundle)v0);
                                                    goto label_146;
                                                label_75:
                                                    v15 = arg13.readString();
                                                    String[] v1 = arg13.createStringArray();
                                                    v5 = arg13.readString();
                                                    IBinder v6_1 = arg13.readStrongBinder();
                                                    String v7_1 = arg13.readString();
                                                    if(arg13.readInt() != 0) {
                                                        v9 = v5;
                                                        v8 = v6_1;
                                                        v10 = v7_1;
                                                        v7_2 = Bundle.CREATOR.createFromParcel(arg13);
                                                        v5 = v15;
                                                        goto label_111;
                                                    }
                                                    else {
                                                        v9 = v5;
                                                        v8 = v6_1;
                                                        v10 = v7_1;
                                                        v5 = v15;
                                                        v7 = ((Bundle)v0);
                                                        goto label_111;
                                                    label_52:
                                                        IBinder v15_1 = arg13.readStrongBinder();
                                                        if(arg13.readInt() != 0) {
                                                            v7_2 = Bundle.CREATOR.createFromParcel(arg13);
                                                            v8 = v15_1;
                                                            v5 = ((String)v0);
                                                            v6 = ((String[])v5);
                                                            v9 = ((String)v6);
                                                            goto label_148;
                                                        }
                                                        else {
                                                            v8 = v15_1;
                                                            v5 = ((String)v0);
                                                            v6 = ((String[])v5);
                                                            v7 = ((Bundle)v6);
                                                            v9 = ((String)v7);
                                                            goto label_148;
                                                        label_96:
                                                            v15 = arg13.readString();
                                                            v1 = arg13.createStringArray();
                                                            v5 = arg13.readString();
                                                            if(arg13.readInt() != 0) {
                                                                v7_2 = Bundle.CREATOR.createFromParcel(arg13);
                                                                v9 = v15;
                                                                v8 = ((IBinder)v0);
                                                            }
                                                            else {
                                                                v9 = v15;
                                                                v7 = ((Bundle)v0);
                                                                v8 = ((IBinder)v7);
                                                            }

                                                            v10 = ((String)v8);
                                                        label_111:
                                                            v6 = v1;
                                                            goto label_149;
                                                        label_113:
                                                            v5 = arg13.readString();
                                                            v6 = ((String[])v0);
                                                            goto label_145;
                                                        }
                                                    }

                                                label_117:
                                                    String[] v15_2 = arg13.createStringArray();
                                                    String v1_1 = arg13.readString();
                                                    if(arg13.readInt() != 0) {
                                                        v7_2 = Bundle.CREATOR.createFromParcel(arg13);
                                                        v6 = v15_2;
                                                        v8 = ((IBinder)v0);
                                                    }
                                                    else {
                                                        v6 = v15_2;
                                                        v7 = ((Bundle)v0);
                                                        v8 = ((IBinder)v7);
                                                    }

                                                    v9 = ((String)v8);
                                                    v10 = v9;
                                                    v5 = v1_1;
                                                    goto label_149;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            goto label_134;
                        }
                    }

                    goto label_117;
                }

            label_149:
                this.getLegacyService(arg12, v2, v3, v4, v5, v6, v7, v8, v9, v10);
            }

        label_152:
            arg14.writeNoException();
            return 1;
        }

        protected void validateAccount(IGmsCallbacks arg1, ValidateAccountRequest arg2) {
            throw new UnsupportedOperationException();
        }
    }

    void getService(IGmsCallbacks arg1, GetServiceRequest arg2);
}

