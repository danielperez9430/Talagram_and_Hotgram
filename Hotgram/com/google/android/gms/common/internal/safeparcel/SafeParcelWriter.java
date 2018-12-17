package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SafeParcelWriter {
    private SafeParcelWriter() {
        super();
    }

    public static int beginObjectHeader(Parcel arg1) {
        return SafeParcelWriter.zza(arg1, 20293);
    }

    public static void finishObjectHeader(Parcel arg0, int arg1) {
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeBigDecimal(Parcel arg0, int arg1, BigDecimal arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeByteArray(arg2.unscaledValue().toByteArray());
        arg0.writeInt(arg2.scale());
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeBigDecimalArray(Parcel arg2, int arg3, BigDecimal[] arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.length;
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeByteArray(arg4[v0].unscaledValue().toByteArray());
            arg2.writeInt(arg4[v0].scale());
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeBigInteger(Parcel arg0, int arg1, BigInteger arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeByteArray(arg2.toByteArray());
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeBigIntegerArray(Parcel arg2, int arg3, BigInteger[] arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.length;
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeByteArray(arg4[v0].toByteArray());
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeBoolean(Parcel arg1, int arg2, boolean arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 4);
        arg1.writeInt(((int)arg3));
    }

    public static void writeBooleanArray(Parcel arg0, int arg1, boolean[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeBooleanArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeBooleanList(Parcel arg2, int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeInt(arg4.get(v0).booleanValue());
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeBooleanObject(Parcel arg0, int arg1, Boolean arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        SafeParcelWriter.zzb(arg0, arg1, 4);
        arg0.writeInt(arg2.booleanValue());
    }

    public static void writeBundle(Parcel arg0, int arg1, Bundle arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeBundle(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeByte(Parcel arg1, int arg2, byte arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 4);
        arg1.writeInt(arg3);
    }

    public static void writeByteArray(Parcel arg0, int arg1, byte[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeByteArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeByteArrayArray(Parcel arg2, int arg3, byte[][] arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.length;
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeByteArray(arg4[v0]);
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeByteArraySparseArray(Parcel arg2, int arg3, SparseArray arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeInt(arg4.keyAt(v0));
            arg2.writeByteArray(arg4.valueAt(v0));
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeChar(Parcel arg1, int arg2, char arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 4);
        arg1.writeInt(arg3);
    }

    public static void writeCharArray(Parcel arg0, int arg1, char[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeCharArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeDouble(Parcel arg1, int arg2, double arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 8);
        arg1.writeDouble(arg3);
    }

    public static void writeDoubleArray(Parcel arg0, int arg1, double[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeDoubleArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeDoubleList(Parcel arg3, int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg5 == null) {
            if(arg6) {
                SafeParcelWriter.zzb(arg3, arg4, 0);
            }

            return;
        }

        arg4 = SafeParcelWriter.zza(arg3, arg4);
        int v6 = arg5.size();
        arg3.writeInt(v6);
        while(v0 < v6) {
            arg3.writeDouble(arg5.get(v0).doubleValue());
            ++v0;
        }

        SafeParcelWriter.zzb(arg3, arg4);
    }

    public static void writeDoubleObject(Parcel arg0, int arg1, Double arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        SafeParcelWriter.zzb(arg0, arg1, 8);
        arg0.writeDouble(arg2.doubleValue());
    }

    public static void writeDoubleSparseArray(Parcel arg3, int arg4, SparseArray arg5, boolean arg6) {
        int v0 = 0;
        if(arg5 == null) {
            if(arg6) {
                SafeParcelWriter.zzb(arg3, arg4, 0);
            }

            return;
        }

        arg4 = SafeParcelWriter.zza(arg3, arg4);
        int v6 = arg5.size();
        arg3.writeInt(v6);
        while(v0 < v6) {
            arg3.writeInt(arg5.keyAt(v0));
            arg3.writeDouble(arg5.valueAt(v0).doubleValue());
            ++v0;
        }

        SafeParcelWriter.zzb(arg3, arg4);
    }

    public static void writeFloat(Parcel arg1, int arg2, float arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 4);
        arg1.writeFloat(arg3);
    }

    public static void writeFloatArray(Parcel arg0, int arg1, float[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeFloatArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeFloatList(Parcel arg2, int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeFloat(arg4.get(v0).floatValue());
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeFloatObject(Parcel arg0, int arg1, Float arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        SafeParcelWriter.zzb(arg0, arg1, 4);
        arg0.writeFloat(arg2.floatValue());
    }

    public static void writeFloatSparseArray(Parcel arg2, int arg3, SparseArray arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeInt(arg4.keyAt(v0));
            arg2.writeFloat(arg4.valueAt(v0).floatValue());
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeIBinder(Parcel arg0, int arg1, IBinder arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeStrongBinder(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeIBinderArray(Parcel arg0, int arg1, IBinder[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeBinderArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeIBinderList(Parcel arg0, int arg1, List arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeBinderList(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeIBinderSparseArray(Parcel arg2, int arg3, SparseArray arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeInt(arg4.keyAt(v0));
            arg2.writeStrongBinder(arg4.valueAt(v0));
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeInt(Parcel arg1, int arg2, int arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 4);
        arg1.writeInt(arg3);
    }

    public static void writeIntArray(Parcel arg0, int arg1, int[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeIntArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeIntegerList(Parcel arg2, int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeInt(arg4.get(v0).intValue());
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeIntegerObject(Parcel arg0, int arg1, Integer arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        SafeParcelWriter.zzb(arg0, arg1, 4);
        arg0.writeInt(arg2.intValue());
    }

    public static void writeList(Parcel arg0, int arg1, List arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeList(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeLong(Parcel arg1, int arg2, long arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 8);
        arg1.writeLong(arg3);
    }

    public static void writeLongArray(Parcel arg0, int arg1, long[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeLongArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeLongList(Parcel arg3, int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg5 == null) {
            if(arg6) {
                SafeParcelWriter.zzb(arg3, arg4, 0);
            }

            return;
        }

        arg4 = SafeParcelWriter.zza(arg3, arg4);
        int v6 = arg5.size();
        arg3.writeInt(v6);
        while(v0 < v6) {
            arg3.writeLong(arg5.get(v0).longValue());
            ++v0;
        }

        SafeParcelWriter.zzb(arg3, arg4);
    }

    public static void writeLongObject(Parcel arg0, int arg1, Long arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        SafeParcelWriter.zzb(arg0, arg1, 8);
        arg0.writeLong(arg2.longValue());
    }

    public static void writeParcel(Parcel arg1, int arg2, Parcel arg3, boolean arg4) {
        if(arg3 == null) {
            if(arg4) {
                SafeParcelWriter.zzb(arg1, arg2, 0);
            }

            return;
        }

        arg2 = SafeParcelWriter.zza(arg1, arg2);
        arg1.appendFrom(arg3, 0, arg3.dataSize());
        SafeParcelWriter.zzb(arg1, arg2);
    }

    public static void writeParcelArray(Parcel arg4, int arg5, Parcel[] arg6, boolean arg7) {
        if(arg6 == null) {
            if(arg7) {
                SafeParcelWriter.zzb(arg4, arg5, 0);
            }

            return;
        }

        arg5 = SafeParcelWriter.zza(arg4, arg5);
        int v7 = arg6.length;
        arg4.writeInt(v7);
        int v1;
        for(v1 = 0; v1 < v7; ++v1) {
            Parcel v2 = arg6[v1];
            if(v2 != null) {
                arg4.writeInt(v2.dataSize());
                arg4.appendFrom(v2, 0, v2.dataSize());
            }
            else {
                arg4.writeInt(0);
            }
        }

        SafeParcelWriter.zzb(arg4, arg5);
    }

    public static void writeParcelList(Parcel arg4, int arg5, List arg6, boolean arg7) {
        if(arg6 == null) {
            if(arg7) {
                SafeParcelWriter.zzb(arg4, arg5, 0);
            }

            return;
        }

        arg5 = SafeParcelWriter.zza(arg4, arg5);
        int v7 = arg6.size();
        arg4.writeInt(v7);
        int v1;
        for(v1 = 0; v1 < v7; ++v1) {
            Object v2 = arg6.get(v1);
            if(v2 != null) {
                arg4.writeInt(((Parcel)v2).dataSize());
                arg4.appendFrom(((Parcel)v2), 0, ((Parcel)v2).dataSize());
            }
            else {
                arg4.writeInt(0);
            }
        }

        SafeParcelWriter.zzb(arg4, arg5);
    }

    public static void writeParcelSparseArray(Parcel arg4, int arg5, SparseArray arg6, boolean arg7) {
        if(arg6 == null) {
            if(arg7) {
                SafeParcelWriter.zzb(arg4, arg5, 0);
            }

            return;
        }

        arg5 = SafeParcelWriter.zza(arg4, arg5);
        int v7 = arg6.size();
        arg4.writeInt(v7);
        int v1;
        for(v1 = 0; v1 < v7; ++v1) {
            arg4.writeInt(arg6.keyAt(v1));
            Object v2 = arg6.valueAt(v1);
            if(v2 != null) {
                arg4.writeInt(((Parcel)v2).dataSize());
                arg4.appendFrom(((Parcel)v2), 0, ((Parcel)v2).dataSize());
            }
            else {
                arg4.writeInt(0);
            }
        }

        SafeParcelWriter.zzb(arg4, arg5);
    }

    public static void writeParcelable(Parcel arg0, int arg1, Parcelable arg2, int arg3, boolean arg4) {
        if(arg2 == null) {
            if(arg4) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg2.writeToParcel(arg0, arg3);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeShort(Parcel arg1, int arg2, short arg3) {
        SafeParcelWriter.zzb(arg1, arg2, 4);
        arg1.writeInt(arg3);
    }

    public static void writeSparseBooleanArray(Parcel arg0, int arg1, SparseBooleanArray arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeSparseBooleanArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeSparseIntArray(Parcel arg2, int arg3, SparseIntArray arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeInt(arg4.keyAt(v0));
            arg2.writeInt(arg4.valueAt(v0));
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeSparseLongArray(Parcel arg3, int arg4, SparseLongArray arg5, boolean arg6) {
        int v0 = 0;
        if(arg5 == null) {
            if(arg6) {
                SafeParcelWriter.zzb(arg3, arg4, 0);
            }

            return;
        }

        arg4 = SafeParcelWriter.zza(arg3, arg4);
        int v6 = arg5.size();
        arg3.writeInt(v6);
        while(v0 < v6) {
            arg3.writeInt(arg5.keyAt(v0));
            arg3.writeLong(arg5.valueAt(v0));
            ++v0;
        }

        SafeParcelWriter.zzb(arg3, arg4);
    }

    public static void writeString(Parcel arg0, int arg1, String arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeString(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeStringArray(Parcel arg0, int arg1, String[] arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeStringArray(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeStringList(Parcel arg0, int arg1, List arg2, boolean arg3) {
        if(arg2 == null) {
            if(arg3) {
                SafeParcelWriter.zzb(arg0, arg1, 0);
            }

            return;
        }

        arg1 = SafeParcelWriter.zza(arg0, arg1);
        arg0.writeStringList(arg2);
        SafeParcelWriter.zzb(arg0, arg1);
    }

    public static void writeStringSparseArray(Parcel arg2, int arg3, SparseArray arg4, boolean arg5) {
        int v0 = 0;
        if(arg4 == null) {
            if(arg5) {
                SafeParcelWriter.zzb(arg2, arg3, 0);
            }

            return;
        }

        arg3 = SafeParcelWriter.zza(arg2, arg3);
        int v5 = arg4.size();
        arg2.writeInt(v5);
        while(v0 < v5) {
            arg2.writeInt(arg4.keyAt(v0));
            arg2.writeString(arg4.valueAt(v0));
            ++v0;
        }

        SafeParcelWriter.zzb(arg2, arg3);
    }

    public static void writeTypedArray(Parcel arg3, int arg4, Parcelable[] arg5, int arg6, boolean arg7) {
        if(arg5 == null) {
            if(arg7) {
                SafeParcelWriter.zzb(arg3, arg4, 0);
            }

            return;
        }

        arg4 = SafeParcelWriter.zza(arg3, arg4);
        int v7 = arg5.length;
        arg3.writeInt(v7);
        int v1;
        for(v1 = 0; v1 < v7; ++v1) {
            Parcelable v2 = arg5[v1];
            if(v2 == null) {
                arg3.writeInt(0);
            }
            else {
                SafeParcelWriter.zza(arg3, v2, arg6);
            }
        }

        SafeParcelWriter.zzb(arg3, arg4);
    }

    public static void writeTypedList(Parcel arg3, int arg4, List arg5, boolean arg6) {
        if(arg5 == null) {
            if(arg6) {
                SafeParcelWriter.zzb(arg3, arg4, 0);
            }

            return;
        }

        arg4 = SafeParcelWriter.zza(arg3, arg4);
        int v6 = arg5.size();
        arg3.writeInt(v6);
        int v1;
        for(v1 = 0; v1 < v6; ++v1) {
            Object v2 = arg5.get(v1);
            if(v2 == null) {
                arg3.writeInt(0);
            }
            else {
                SafeParcelWriter.zza(arg3, ((Parcelable)v2), 0);
            }
        }

        SafeParcelWriter.zzb(arg3, arg4);
    }

    public static void writeTypedSparseArray(Parcel arg3, int arg4, SparseArray arg5, boolean arg6) {
        if(arg5 == null) {
            if(arg6) {
                SafeParcelWriter.zzb(arg3, arg4, 0);
            }

            return;
        }

        arg4 = SafeParcelWriter.zza(arg3, arg4);
        int v6 = arg5.size();
        arg3.writeInt(v6);
        int v1;
        for(v1 = 0; v1 < v6; ++v1) {
            arg3.writeInt(arg5.keyAt(v1));
            Object v2 = arg5.valueAt(v1);
            if(v2 == null) {
                arg3.writeInt(0);
            }
            else {
                SafeParcelWriter.zza(arg3, ((Parcelable)v2), 0);
            }
        }

        SafeParcelWriter.zzb(arg3, arg4);
    }

    private static int zza(Parcel arg1, int arg2) {
        arg1.writeInt(arg2 | -65536);
        arg1.writeInt(0);
        return arg1.dataPosition();
    }

    private static void zza(Parcel arg2, Parcelable arg3, int arg4) {
        int v0 = arg2.dataPosition();
        arg2.writeInt(1);
        int v1 = arg2.dataPosition();
        arg3.writeToParcel(arg2, arg4);
        int v3 = arg2.dataPosition();
        arg2.setDataPosition(v0);
        arg2.writeInt(v3 - v1);
        arg2.setDataPosition(v3);
    }

    private static void zzb(Parcel arg2, int arg3) {
        int v0 = arg2.dataPosition();
        arg2.setDataPosition(arg3 - 4);
        arg2.writeInt(v0 - arg3);
        arg2.setDataPosition(v0);
    }

    private static void zzb(Parcel arg1, int arg2, int arg3) {
        if(arg3 >= 65535) {
            arg1.writeInt(arg2 | -65536);
            arg1.writeInt(arg3);
            return;
        }

        arg1.writeInt(arg2 | arg3 << 16);
    }
}

