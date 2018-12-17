package com.google.android.gms.wearable;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.wearable.zze;
import com.google.android.gms.internal.wearable.zzf;
import com.google.android.gms.internal.wearable.zzg;
import com.google.android.gms.internal.wearable.zzs;
import com.google.android.gms.internal.wearable.zzt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

@VisibleForTesting public class DataMap {
    public static final String TAG = "DataMap";
    private final HashMap zzq;

    public DataMap() {
        super();
        this.zzq = new HashMap();
    }

    public static ArrayList arrayListFromBundleArrayList(ArrayList arg4) {
        ArrayList v0 = new ArrayList();
        int v1 = arg4.size();
        int v2 = 0;
        while(v2 < v1) {
            Object v3 = arg4.get(v2);
            ++v2;
            v0.add(DataMap.fromBundle(((Bundle)v3)));
        }

        return v0;
    }

    public void clear() {
        this.zzq.clear();
    }

    public boolean containsKey(String arg2) {
        return this.zzq.containsKey(arg2);
    }

    public boolean equals(Object arg7) {
        boolean v2_1;
        if(!(arg7 instanceof DataMap)) {
            return 0;
        }

        if(this.size() != ((DataMap)arg7).size()) {
            return 0;
        }

        Iterator v0 = this.keySet().iterator();
        do {
            if(v0.hasNext()) {
                Object v2 = v0.next();
                Object v4 = this.get(((String)v2));
                v2 = ((DataMap)arg7).get(((String)v2));
                if(!(v4 instanceof Asset)) {
                    if((v4 instanceof String[])) {
                        if(!(v2 instanceof String[])) {
                            return 0;
                        }

                        if(Arrays.equals(((Object[])v4), ((Object[])v2))) {
                            continue;
                        }

                        return 0;
                    }

                    if((v4 instanceof long[])) {
                        if(!(v2 instanceof long[])) {
                            return 0;
                        }

                        if(Arrays.equals(((long[])v4), ((long[])v2))) {
                            continue;
                        }

                        return 0;
                    }

                    if((v4 instanceof float[])) {
                        if(!(v2 instanceof float[])) {
                            return 0;
                        }

                        if(Arrays.equals(((float[])v4), ((float[])v2))) {
                            continue;
                        }

                        return 0;
                    }

                    if((v4 instanceof byte[])) {
                        if(!(v2 instanceof byte[])) {
                            return 0;
                        }

                        if(Arrays.equals(((byte[])v4), ((byte[])v2))) {
                            continue;
                        }

                        return 0;
                    }

                    if(v4 != null) {
                        if(v2 == null) {
                        }
                        else {
                            if(v4.equals(v2)) {
                                continue;
                            }

                            return 0;
                        }
                    }

                    goto label_79;
                }
                else if(!(v2 instanceof Asset)) {
                    return 0;
                }
                else {
                    if(v4 == null || v2 == null) {
                        v2_1 = v4 == v2 ? true : false;
                    }
                    else if(!TextUtils.isEmpty(((Asset)v4).getDigest())) {
                        v2_1 = ((Asset)v4).getDigest().equals(((Asset)v2).getDigest());
                    }
                    else {
                        v2_1 = Arrays.equals(((Asset)v4).getData(), ((Asset)v2).getData());
                    }

                    if(v2_1) {
                        continue;
                    }

                    return 0;
                }
            }

            return 1;
        }
        while(true);

        return 0;
    label_79:
        if(v4 != v2) {
            return 0;
        }

        return 1;
    }

    public static DataMap fromBundle(Bundle arg6) {
        arg6.setClassLoader(Asset.class.getClassLoader());
        DataMap v0 = new DataMap();
        Iterator v1 = arg6.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            Object v3 = arg6.get(((String)v2));
            if((v3 instanceof String)) {
                v0.putString(((String)v2), ((String)v3));
                continue;
            }

            if((v3 instanceof Integer)) {
                v0.putInt(((String)v2), ((Integer)v3).intValue());
                continue;
            }

            if((v3 instanceof Long)) {
                v0.putLong(((String)v2), ((Long)v3).longValue());
                continue;
            }

            if((v3 instanceof Double)) {
                v0.putDouble(((String)v2), ((Double)v3).doubleValue());
                continue;
            }

            if((v3 instanceof Float)) {
                v0.putFloat(((String)v2), ((Float)v3).floatValue());
                continue;
            }

            if((v3 instanceof Boolean)) {
                v0.putBoolean(((String)v2), ((Boolean)v3).booleanValue());
                continue;
            }

            if((v3 instanceof Byte)) {
                v0.putByte(((String)v2), ((Byte)v3).byteValue());
                continue;
            }

            if((v3 instanceof byte[])) {
                v0.putByteArray(((String)v2), ((byte[])v3));
                continue;
            }

            if((v3 instanceof String[])) {
                v0.putStringArray(((String)v2), ((String[])v3));
                continue;
            }

            if((v3 instanceof long[])) {
                v0.putLongArray(((String)v2), ((long[])v3));
                continue;
            }

            if((v3 instanceof float[])) {
                v0.putFloatArray(((String)v2), ((float[])v3));
                continue;
            }

            if((v3 instanceof Asset)) {
                v0.putAsset(((String)v2), ((Asset)v3));
                continue;
            }

            if((v3 instanceof Bundle)) {
                v0.putDataMap(((String)v2), DataMap.fromBundle(((Bundle)v3)));
                continue;
            }

            if(!(v3 instanceof ArrayList)) {
                continue;
            }

            int v4 = DataMap.zza(((ArrayList)v3));
            if(v4 != 5) {
                switch(v4) {
                    case 2: {
                        goto label_77;
                    }
                    case 0: 
                    case 1: 
                    case 3: {
                        goto label_79;
                    }
                }

                continue;
            label_77:
                v0.putIntegerArrayList(((String)v2), ((ArrayList)v3));
                continue;
            label_79:
                v0.putStringArrayList(((String)v2), ((ArrayList)v3));
                continue;
            }

            v0.putDataMapArrayList(((String)v2), DataMap.arrayListFromBundleArrayList(((ArrayList)v3)));
        }

        return v0;
    }

    public static DataMap fromByteArray(byte[] arg2) {
        try {
            return zze.zza(new zzf(zzg.zza(arg2), new ArrayList()));
        }
        catch(zzs v2) {
            throw new IllegalArgumentException("Unable to convert data", ((Throwable)v2));
        }
    }

    public Object get(String arg2) {
        return this.zzq.get(arg2);
    }

    public Asset getAsset(String arg5) {
        Object v0 = this.zzq.get(arg5);
        Asset v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "Asset", v2);
        return v1;
    }

    public boolean getBoolean(String arg2) {
        return this.getBoolean(arg2, false);
    }

    public boolean getBoolean(String arg5, boolean arg6) {
        Object v0 = this.zzq.get(arg5);
        if(v0 == null) {
            return arg6;
        }

        try {
            return v0.booleanValue();
        }
        catch(ClassCastException v1) {
            DataMap.zza(arg5, v0, "Boolean", Boolean.valueOf(arg6), v1);
            return arg6;
        }
    }

    public byte getByte(String arg2) {
        return this.getByte(arg2, 0);
    }

    public byte getByte(String arg5, byte arg6) {
        Object v0 = this.zzq.get(arg5);
        if(v0 == null) {
            return arg6;
        }

        try {
            return v0.byteValue();
        }
        catch(ClassCastException v1) {
            DataMap.zza(arg5, v0, "Byte", Byte.valueOf(arg6), v1);
            return arg6;
        }
    }

    public byte[] getByteArray(String arg5) {
        Object v0 = this.zzq.get(arg5);
        byte[] v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "byte[]", v2);
        return v1;
    }

    public DataMap getDataMap(String arg5) {
        Object v0 = this.zzq.get(arg5);
        DataMap v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "DataMap", v2);
        return v1;
    }

    public ArrayList getDataMapArrayList(String arg5) {
        Object v0 = this.zzq.get(arg5);
        ArrayList v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "ArrayList<DataMap>", v2);
        return v1;
    }

    public double getDouble(String arg3) {
        return this.getDouble(arg3, 0);
    }

    public double getDouble(String arg5, double arg6) {
        Object v0 = this.zzq.get(arg5);
        if(v0 == null) {
            return arg6;
        }

        try {
            return v0.doubleValue();
        }
        catch(ClassCastException v1) {
            DataMap.zza(arg5, v0, "Double", Double.valueOf(arg6), v1);
            return arg6;
        }
    }

    public float getFloat(String arg2) {
        return this.getFloat(arg2, 0f);
    }

    public float getFloat(String arg5, float arg6) {
        Object v0 = this.zzq.get(arg5);
        if(v0 == null) {
            return arg6;
        }

        try {
            return v0.floatValue();
        }
        catch(ClassCastException v1) {
            DataMap.zza(arg5, v0, "Float", Float.valueOf(arg6), v1);
            return arg6;
        }
    }

    public float[] getFloatArray(String arg5) {
        Object v0 = this.zzq.get(arg5);
        float[] v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "float[]", v2);
        return v1;
    }

    public int getInt(String arg2) {
        return this.getInt(arg2, 0);
    }

    public int getInt(String arg4, int arg5) {
        Object v0 = this.zzq.get(arg4);
        if(v0 == null) {
            return arg5;
        }

        try {
            return v0.intValue();
        }
        catch(ClassCastException v1) {
            DataMap.zza(arg4, v0, "Integer", v1);
            return arg5;
        }
    }

    public ArrayList getIntegerArrayList(String arg5) {
        Object v0 = this.zzq.get(arg5);
        ArrayList v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "ArrayList<Integer>", v2);
        return v1;
    }

    public long getLong(String arg3) {
        return this.getLong(arg3, 0);
    }

    public long getLong(String arg4, long arg5) {
        Object v0 = this.zzq.get(arg4);
        if(v0 == null) {
            return arg5;
        }

        try {
            return v0.longValue();
        }
        catch(ClassCastException v1) {
            DataMap.zza(arg4, v0, "long", v1);
            return arg5;
        }
    }

    public long[] getLongArray(String arg5) {
        Object v0 = this.zzq.get(arg5);
        long[] v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "long[]", v2);
        return v1;
    }

    public String getString(String arg5) {
        Object v0 = this.zzq.get(arg5);
        String v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "String", v2);
        return v1;
    }

    public String getString(String arg1, String arg2) {
        arg1 = this.getString(arg1);
        if(arg1 == null) {
            return arg2;
        }

        return arg1;
    }

    public String[] getStringArray(String arg5) {
        Object v0 = this.zzq.get(arg5);
        String[] v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "String[]", v2);
        return v1;
    }

    public ArrayList getStringArrayList(String arg5) {
        Object v0 = this.zzq.get(arg5);
        ArrayList v1 = null;
        if(v0 == null) {
            return v1;
        }

        return v0;
        DataMap.zza(arg5, v0, "ArrayList<String>", v2);
        return v1;
    }

    public int hashCode() {
        return this.zzq.hashCode() * 29;
    }

    public boolean isEmpty() {
        return this.zzq.isEmpty();
    }

    public Set keySet() {
        return this.zzq.keySet();
    }

    public void putAll(DataMap arg5) {
        Iterator v0 = arg5.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            this.zzq.put(v1, arg5.get(((String)v1)));
        }
    }

    public void putAsset(String arg2, Asset arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putBoolean(String arg2, boolean arg3) {
        this.zzq.put(arg2, Boolean.valueOf(arg3));
    }

    public void putByte(String arg2, byte arg3) {
        this.zzq.put(arg2, Byte.valueOf(arg3));
    }

    public void putByteArray(String arg2, byte[] arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putDataMap(String arg2, DataMap arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putDataMapArrayList(String arg2, ArrayList arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putDouble(String arg2, double arg3) {
        this.zzq.put(arg2, Double.valueOf(arg3));
    }

    public void putFloat(String arg2, float arg3) {
        this.zzq.put(arg2, Float.valueOf(arg3));
    }

    public void putFloatArray(String arg2, float[] arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putInt(String arg2, int arg3) {
        this.zzq.put(arg2, Integer.valueOf(arg3));
    }

    public void putIntegerArrayList(String arg2, ArrayList arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putLong(String arg2, long arg3) {
        this.zzq.put(arg2, Long.valueOf(arg3));
    }

    public void putLongArray(String arg2, long[] arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putString(String arg2, String arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putStringArray(String arg2, String[] arg3) {
        this.zzq.put(arg2, arg3);
    }

    public void putStringArrayList(String arg2, ArrayList arg3) {
        this.zzq.put(arg2, arg3);
    }

    public Object remove(String arg2) {
        return this.zzq.remove(arg2);
    }

    public int size() {
        return this.zzq.size();
    }

    public Bundle toBundle() {
        Bundle v3_1;
        Object v3;
        Object v2;
        Bundle v0 = new Bundle();
        Iterator v1 = this.zzq.keySet().iterator();
        while(true) {
        label_5:
            if(!v1.hasNext()) {
                return v0;
            }

            v2 = v1.next();
            v3 = this.zzq.get(v2);
            if((v3 instanceof String)) {
                v0.putString(((String)v2), ((String)v3));
                continue;
            }

            if((v3 instanceof Integer)) {
                v0.putInt(((String)v2), ((Integer)v3).intValue());
                continue;
            }

            if((v3 instanceof Long)) {
                v0.putLong(((String)v2), ((Long)v3).longValue());
                continue;
            }

            if((v3 instanceof Double)) {
                v0.putDouble(((String)v2), ((Double)v3).doubleValue());
                continue;
            }

            if((v3 instanceof Float)) {
                v0.putFloat(((String)v2), ((Float)v3).floatValue());
                continue;
            }

            if((v3 instanceof Boolean)) {
                v0.putBoolean(((String)v2), ((Boolean)v3).booleanValue());
                continue;
            }

            if((v3 instanceof Byte)) {
                v0.putByte(((String)v2), ((Byte)v3).byteValue());
                continue;
            }

            if((v3 instanceof byte[])) {
                v0.putByteArray(((String)v2), ((byte[])v3));
                continue;
            }

            if((v3 instanceof String[])) {
                v0.putStringArray(((String)v2), ((String[])v3));
                continue;
            }

            if((v3 instanceof long[])) {
                v0.putLongArray(((String)v2), ((long[])v3));
                continue;
            }

            if((v3 instanceof float[])) {
                v0.putFloatArray(((String)v2), ((float[])v3));
                continue;
            }

            if(!(v3 instanceof Asset)) {
                if((v3 instanceof DataMap)) {
                    v3_1 = ((DataMap)v3).toBundle();
                }
                else {
                    goto label_68;
                }
            }

            v0.putParcelable(((String)v2), ((Parcelable)v3_1));
            continue;
        label_68:
            if(!(v3 instanceof ArrayList)) {
                continue;
            }

            switch(DataMap.zza(((ArrayList)v3))) {
                case 2: {
                    goto label_85;
                }
                case 0: 
                case 1: 
                case 3: {
                    goto label_87;
                }
                case 4: {
                    goto label_73;
                }
            }

            continue;
        label_85:
            v0.putIntegerArrayList(((String)v2), ((ArrayList)v3));
            continue;
        label_87:
            v0.putStringArrayList(((String)v2), ((ArrayList)v3));
        }

    label_73:
        ArrayList v4 = new ArrayList();
        int v5 = ((ArrayList)v3).size();
        int v6 = 0;
        goto label_77;
        return v0;
    label_77:
        while(v6 < v5) {
            Object v7 = ((ArrayList)v3).get(v6);
            ++v6;
            v4.add(((DataMap)v7).toBundle());
        }

        v0.putParcelableArrayList(((String)v2), v4);
        goto label_5;
    }

    public byte[] toByteArray() {
        return zzt.zzb(zze.zza(this).zzfw);
    }

    public String toString() {
        return this.zzq.toString();
    }

    private static int zza(ArrayList arg4) {
        int v1 = 0;
        if(arg4.isEmpty()) {
            return 0;
        }

        int v0 = arg4.size();
        do {
        label_5:
            if(v1 >= v0) {
                return 1;
            }

            Object v2 = arg4.get(v1);
            ++v1;
            if(v2 == null) {
                goto label_5;
            }

            if((v2 instanceof Integer)) {
                return 2;
            }

            if((v2 instanceof String)) {
                return 3;
            }

            if((v2 instanceof DataMap)) {
                return 4;
            }
        }
        while(!(v2 instanceof Bundle));

        return 5;
    }

    private static void zza(String arg1, Object arg2, String arg3, ClassCastException arg4) {
        DataMap.zza(arg1, arg2, arg3, "<null>", arg4);
    }

    private static void zza(String arg2, Object arg3, String arg4, Object arg5, ClassCastException arg6) {
        Log.w("DataMap", "Key " + arg2 + " expected " + arg4 + " but value was a " + arg3.getClass().getName() + ".  The default value " + arg5 + " was returned.");
        Log.w("DataMap", "Attempt to cast generated internal exception:", ((Throwable)arg6));
    }
}

