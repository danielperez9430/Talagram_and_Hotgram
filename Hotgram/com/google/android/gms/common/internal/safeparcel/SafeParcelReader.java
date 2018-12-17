package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SafeParcelReader {
    public class ParseException extends RuntimeException {
        public ParseException(String arg4, Parcel arg5) {
            int v0 = arg5.dataPosition();
            int v5 = arg5.dataSize();
            StringBuilder v2 = new StringBuilder(String.valueOf(arg4).length() + 41);
            v2.append(arg4);
            v2.append(" Parcel: pos=");
            v2.append(v0);
            v2.append(" size=");
            v2.append(v5);
            super(v2.toString());
        }
    }

    private SafeParcelReader() {
        super();
    }

    public static BigDecimal createBigDecimal(Parcel arg3, int arg4) {
        arg4 = SafeParcelReader.readSize(arg3, arg4);
        int v0 = arg3.dataPosition();
        if(arg4 == 0) {
            return null;
        }

        byte[] v1 = arg3.createByteArray();
        int v2 = arg3.readInt();
        arg3.setDataPosition(v0 + arg4);
        return new BigDecimal(new BigInteger(v1), v2);
    }

    public static BigDecimal[] createBigDecimalArray(Parcel arg8, int arg9) {
        arg9 = SafeParcelReader.readSize(arg8, arg9);
        int v0 = arg8.dataPosition();
        if(arg9 == 0) {
            return null;
        }

        int v1 = arg8.readInt();
        BigDecimal[] v2 = new BigDecimal[v1];
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2[v3] = new BigDecimal(new BigInteger(arg8.createByteArray()), arg8.readInt());
        }

        arg8.setDataPosition(v0 + arg9);
        return v2;
    }

    public static BigInteger createBigInteger(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        byte[] v1 = arg2.createByteArray();
        arg2.setDataPosition(v0 + arg3);
        return new BigInteger(v1);
    }

    public static BigInteger[] createBigIntegerArray(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        int v1 = arg6.readInt();
        BigInteger[] v2 = new BigInteger[v1];
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2[v3] = new BigInteger(arg6.createByteArray());
        }

        arg6.setDataPosition(v0 + arg7);
        return v2;
    }

    public static boolean[] createBooleanArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        boolean[] v1 = arg2.createBooleanArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static ArrayList createBooleanList(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        ArrayList v1 = new ArrayList();
        int v2 = arg6.readInt();
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            boolean v5 = arg6.readInt() != 0 ? true : false;
            v1.add(Boolean.valueOf(v5));
        }

        arg6.setDataPosition(v0 + arg7);
        return v1;
    }

    public static Bundle createBundle(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        Bundle v1 = arg2.readBundle();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static byte[] createByteArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        byte[] v1 = arg2.createByteArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static byte[][] createByteArrayArray(Parcel arg5, int arg6) {
        arg6 = SafeParcelReader.readSize(arg5, arg6);
        int v0 = arg5.dataPosition();
        if(arg6 == 0) {
            return null;
        }

        int v1 = arg5.readInt();
        byte[][] v2 = new byte[v1][];
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2[v3] = arg5.createByteArray();
        }

        arg5.setDataPosition(v0 + arg6);
        return v2;
    }

    public static SparseArray createByteArraySparseArray(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        int v1 = arg6.readInt();
        SparseArray v2 = new SparseArray(v1);
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2.append(arg6.readInt(), arg6.createByteArray());
        }

        arg6.setDataPosition(v0 + arg7);
        return v2;
    }

    public static char[] createCharArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        char[] v1 = arg2.createCharArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static double[] createDoubleArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        double[] v1 = arg2.createDoubleArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static ArrayList createDoubleList(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        ArrayList v1 = new ArrayList();
        int v2 = arg6.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.add(Double.valueOf(arg6.readDouble()));
        }

        arg6.setDataPosition(v0 + arg7);
        return v1;
    }

    public static SparseArray createDoubleSparseArray(Parcel arg7, int arg8) {
        arg8 = SafeParcelReader.readSize(arg7, arg8);
        int v0 = arg7.dataPosition();
        if(arg8 == 0) {
            return null;
        }

        SparseArray v1 = new SparseArray();
        int v2 = arg7.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.append(arg7.readInt(), Double.valueOf(arg7.readDouble()));
        }

        arg7.setDataPosition(v0 + arg8);
        return v1;
    }

    public static float[] createFloatArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        float[] v1 = arg2.createFloatArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static ArrayList createFloatList(Parcel arg5, int arg6) {
        arg6 = SafeParcelReader.readSize(arg5, arg6);
        int v0 = arg5.dataPosition();
        if(arg6 == 0) {
            return null;
        }

        ArrayList v1 = new ArrayList();
        int v2 = arg5.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.add(Float.valueOf(arg5.readFloat()));
        }

        arg5.setDataPosition(v0 + arg6);
        return v1;
    }

    public static SparseArray createFloatSparseArray(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        SparseArray v1 = new SparseArray();
        int v2 = arg6.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.append(arg6.readInt(), Float.valueOf(arg6.readFloat()));
        }

        arg6.setDataPosition(v0 + arg7);
        return v1;
    }

    public static IBinder[] createIBinderArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        IBinder[] v1 = arg2.createBinderArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static ArrayList createIBinderList(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        ArrayList v1 = arg2.createBinderArrayList();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static SparseArray createIBinderSparseArray(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        int v1 = arg6.readInt();
        SparseArray v2 = new SparseArray(v1);
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2.append(arg6.readInt(), arg6.readStrongBinder());
        }

        arg6.setDataPosition(v0 + arg7);
        return v2;
    }

    public static int[] createIntArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        int[] v1 = arg2.createIntArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static ArrayList createIntegerList(Parcel arg5, int arg6) {
        arg6 = SafeParcelReader.readSize(arg5, arg6);
        int v0 = arg5.dataPosition();
        if(arg6 == 0) {
            return null;
        }

        ArrayList v1 = new ArrayList();
        int v2 = arg5.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.add(Integer.valueOf(arg5.readInt()));
        }

        arg5.setDataPosition(v0 + arg6);
        return v1;
    }

    public static long[] createLongArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        long[] v1 = arg2.createLongArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static ArrayList createLongList(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        ArrayList v1 = new ArrayList();
        int v2 = arg6.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.add(Long.valueOf(arg6.readLong()));
        }

        arg6.setDataPosition(v0 + arg7);
        return v1;
    }

    public static Parcel createParcel(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        Parcel v1 = Parcel.obtain();
        v1.appendFrom(arg2, v0, arg3);
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static Parcel[] createParcelArray(Parcel arg8, int arg9) {
        arg9 = SafeParcelReader.readSize(arg8, arg9);
        int v0 = arg8.dataPosition();
        Parcel[] v1 = null;
        if(arg9 == 0) {
            return v1;
        }

        int v2 = arg8.readInt();
        Parcel[] v3 = new Parcel[v2];
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            int v5 = arg8.readInt();
            if(v5 != 0) {
                int v6 = arg8.dataPosition();
                Parcel v7 = Parcel.obtain();
                v7.appendFrom(arg8, v6, v5);
                v3[v4] = v7;
                arg8.setDataPosition(v6 + v5);
            }
            else {
                v3[v4] = ((Parcel)v1);
            }
        }

        arg8.setDataPosition(v0 + arg9);
        return v3;
    }

    public static ArrayList createParcelList(Parcel arg8, int arg9) {
        arg9 = SafeParcelReader.readSize(arg8, arg9);
        int v0 = arg8.dataPosition();
        ArrayList v1 = null;
        if(arg9 == 0) {
            return v1;
        }

        int v2 = arg8.readInt();
        ArrayList v3 = new ArrayList();
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            int v5 = arg8.readInt();
            if(v5 != 0) {
                int v6 = arg8.dataPosition();
                Parcel v7 = Parcel.obtain();
                v7.appendFrom(arg8, v6, v5);
                v3.add(v7);
                arg8.setDataPosition(v6 + v5);
            }
            else {
                v3.add(v1);
            }
        }

        arg8.setDataPosition(v0 + arg9);
        return v3;
    }

    public static SparseArray createParcelSparseArray(Parcel arg9, int arg10) {
        arg10 = SafeParcelReader.readSize(arg9, arg10);
        int v0 = arg9.dataPosition();
        SparseArray v1 = null;
        if(arg10 == 0) {
            return v1;
        }

        int v2 = arg9.readInt();
        SparseArray v3 = new SparseArray();
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            int v5 = arg9.readInt();
            int v6 = arg9.readInt();
            if(v6 != 0) {
                int v7 = arg9.dataPosition();
                Parcel v8 = Parcel.obtain();
                v8.appendFrom(arg9, v7, v6);
                v3.append(v5, v8);
                arg9.setDataPosition(v7 + v6);
            }
            else {
                v3.append(v5, v1);
            }
        }

        arg9.setDataPosition(v0 + arg10);
        return v3;
    }

    public static Parcelable createParcelable(Parcel arg1, int arg2, Parcelable$Creator arg3) {
        arg2 = SafeParcelReader.readSize(arg1, arg2);
        int v0 = arg1.dataPosition();
        if(arg2 == 0) {
            return null;
        }

        Object v3 = arg3.createFromParcel(arg1);
        arg1.setDataPosition(v0 + arg2);
        return ((Parcelable)v3);
    }

    public static SparseBooleanArray createSparseBooleanArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        SparseBooleanArray v1 = arg2.readSparseBooleanArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static SparseIntArray createSparseIntArray(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        SparseIntArray v1 = new SparseIntArray();
        int v2 = arg6.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.append(arg6.readInt(), arg6.readInt());
        }

        arg6.setDataPosition(v0 + arg7);
        return v1;
    }

    public static SparseLongArray createSparseLongArray(Parcel arg7, int arg8) {
        arg8 = SafeParcelReader.readSize(arg7, arg8);
        int v0 = arg7.dataPosition();
        if(arg8 == 0) {
            return null;
        }

        SparseLongArray v1 = new SparseLongArray();
        int v2 = arg7.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.append(arg7.readInt(), arg7.readLong());
        }

        arg7.setDataPosition(v0 + arg8);
        return v1;
    }

    public static String createString(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        String v1 = arg2.readString();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static String[] createStringArray(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        String[] v1 = arg2.createStringArray();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static ArrayList createStringList(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        ArrayList v1 = arg2.createStringArrayList();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static SparseArray createStringSparseArray(Parcel arg6, int arg7) {
        arg7 = SafeParcelReader.readSize(arg6, arg7);
        int v0 = arg6.dataPosition();
        if(arg7 == 0) {
            return null;
        }

        SparseArray v1 = new SparseArray();
        int v2 = arg6.readInt();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.append(arg6.readInt(), arg6.readString());
        }

        arg6.setDataPosition(v0 + arg7);
        return v1;
    }

    public static Object[] createTypedArray(Parcel arg1, int arg2, Parcelable$Creator arg3) {
        arg2 = SafeParcelReader.readSize(arg1, arg2);
        int v0 = arg1.dataPosition();
        if(arg2 == 0) {
            return null;
        }

        Object[] v3 = arg1.createTypedArray(arg3);
        arg1.setDataPosition(v0 + arg2);
        return v3;
    }

    public static ArrayList createTypedList(Parcel arg1, int arg2, Parcelable$Creator arg3) {
        arg2 = SafeParcelReader.readSize(arg1, arg2);
        int v0 = arg1.dataPosition();
        if(arg2 == 0) {
            return null;
        }

        ArrayList v3 = arg1.createTypedArrayList(arg3);
        arg1.setDataPosition(v0 + arg2);
        return v3;
    }

    public static SparseArray createTypedSparseArray(Parcel arg7, int arg8, Parcelable$Creator arg9) {
        arg8 = SafeParcelReader.readSize(arg7, arg8);
        int v0 = arg7.dataPosition();
        SparseArray v1 = null;
        if(arg8 == 0) {
            return v1;
        }

        int v2 = arg7.readInt();
        SparseArray v3 = new SparseArray();
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            int v5 = arg7.readInt();
            Object v6 = arg7.readInt() != 0 ? arg9.createFromParcel(arg7) : v1;
            v3.append(v5, v6);
        }

        arg7.setDataPosition(v0 + arg8);
        return v3;
    }

    public static void ensureAtEnd(Parcel arg3, int arg4) {
        if(arg3.dataPosition() == arg4) {
            return;
        }

        StringBuilder v2 = new StringBuilder(37);
        v2.append("Overread allowed size end=");
        v2.append(arg4);
        throw new ParseException(v2.toString(), arg3);
    }

    public static int getFieldId(int arg1) {
        return arg1 & 65535;
    }

    public static boolean readBoolean(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 4);
        if(arg1.readInt() != 0) {
            return 1;
        }

        return 0;
    }

    public static Boolean readBooleanObject(Parcel arg2, int arg3) {
        int v0 = SafeParcelReader.readSize(arg2, arg3);
        if(v0 == 0) {
            return null;
        }

        SafeParcelReader.zza(arg2, arg3, v0, 4);
        boolean v2 = arg2.readInt() != 0 ? true : false;
        return Boolean.valueOf(v2);
    }

    public static byte readByte(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 4);
        return ((byte)arg1.readInt());
    }

    public static char readChar(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 4);
        return ((char)arg1.readInt());
    }

    public static double readDouble(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 8);
        return arg1.readDouble();
    }

    public static Double readDoubleObject(Parcel arg2, int arg3) {
        int v0 = SafeParcelReader.readSize(arg2, arg3);
        if(v0 == 0) {
            return null;
        }

        SafeParcelReader.zza(arg2, arg3, v0, 8);
        return Double.valueOf(arg2.readDouble());
    }

    public static float readFloat(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 4);
        return arg1.readFloat();
    }

    public static Float readFloatObject(Parcel arg2, int arg3) {
        int v0 = SafeParcelReader.readSize(arg2, arg3);
        if(v0 == 0) {
            return null;
        }

        SafeParcelReader.zza(arg2, arg3, v0, 4);
        return Float.valueOf(arg2.readFloat());
    }

    public static int readHeader(Parcel arg0) {
        return arg0.readInt();
    }

    public static IBinder readIBinder(Parcel arg2, int arg3) {
        arg3 = SafeParcelReader.readSize(arg2, arg3);
        int v0 = arg2.dataPosition();
        if(arg3 == 0) {
            return null;
        }

        IBinder v1 = arg2.readStrongBinder();
        arg2.setDataPosition(v0 + arg3);
        return v1;
    }

    public static int readInt(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 4);
        return arg1.readInt();
    }

    public static Integer readIntegerObject(Parcel arg2, int arg3) {
        int v0 = SafeParcelReader.readSize(arg2, arg3);
        if(v0 == 0) {
            return null;
        }

        SafeParcelReader.zza(arg2, arg3, v0, 4);
        return Integer.valueOf(arg2.readInt());
    }

    public static void readList(Parcel arg1, int arg2, List arg3, ClassLoader arg4) {
        arg2 = SafeParcelReader.readSize(arg1, arg2);
        int v0 = arg1.dataPosition();
        if(arg2 == 0) {
            return;
        }

        arg1.readList(arg3, arg4);
        arg1.setDataPosition(v0 + arg2);
    }

    public static long readLong(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 8);
        return arg1.readLong();
    }

    public static Long readLongObject(Parcel arg2, int arg3) {
        int v0 = SafeParcelReader.readSize(arg2, arg3);
        if(v0 == 0) {
            return null;
        }

        SafeParcelReader.zza(arg2, arg3, v0, 8);
        return Long.valueOf(arg2.readLong());
    }

    public static short readShort(Parcel arg1, int arg2) {
        SafeParcelReader.zza(arg1, arg2, 4);
        return ((short)arg1.readInt());
    }

    public static int readSize(Parcel arg2, int arg3) {
        if((arg3 & -65536) != -65536) {
            return arg3 >> 16 & 65535;
        }

        return arg2.readInt();
    }

    public static void skipUnknownField(Parcel arg1, int arg2) {
        arg1.setDataPosition(arg1.dataPosition() + SafeParcelReader.readSize(arg1, arg2));
    }

    public static int validateObjectHeader(Parcel arg5) {
        int v0 = SafeParcelReader.readHeader(arg5);
        int v1 = SafeParcelReader.readSize(arg5, v0);
        int v2 = arg5.dataPosition();
        if(SafeParcelReader.getFieldId(v0) != 20293) {
            String v2_1 = "Expected object header. Got 0x";
            String v0_1 = String.valueOf(Integer.toHexString(v0));
            v0_1 = v0_1.length() != 0 ? v2_1.concat(v0_1) : new String(v2_1);
            throw new ParseException(v0_1, arg5);
        }

        v1 += v2;
        if(v1 >= v2 && v1 <= arg5.dataSize()) {
            return v1;
        }

        StringBuilder v4 = new StringBuilder(54);
        v4.append("Size read is invalid start=");
        v4.append(v2);
        v4.append(" end=");
        v4.append(v1);
        throw new ParseException(v4.toString(), arg5);
    }

    private static void zza(Parcel arg4, int arg5, int arg6) {
        arg5 = SafeParcelReader.readSize(arg4, arg5);
        if(arg5 == arg6) {
            return;
        }

        String v1 = Integer.toHexString(arg5);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 46);
        v3.append("Expected size ");
        v3.append(arg6);
        v3.append(" got ");
        v3.append(arg5);
        v3.append(" (0x");
        v3.append(v1);
        v3.append(")");
        throw new ParseException(v3.toString(), arg4);
    }

    private static void zza(Parcel arg3, int arg4, int arg5, int arg6) {
        if(arg5 == arg6) {
            return;
        }

        String v0 = Integer.toHexString(arg5);
        StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 46);
        v2.append("Expected size ");
        v2.append(arg6);
        v2.append(" got ");
        v2.append(arg5);
        v2.append(" (0x");
        v2.append(v0);
        v2.append(")");
        throw new ParseException(v2.toString(), arg3);
    }
}

