package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

@Class(creator="SafeParcelResponseCreator") @VisibleForTesting public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
    public static final Parcelable$Creator CREATOR;
    private final String mClassName;
    @VersionField(getter="getVersionCode", id=1) private final int zzal;
    @Field(getter="getFieldMappingDictionary", id=3) private final FieldMappingDictionary zzwn;
    @Field(getter="getParcel", id=2) private final Parcel zzxq;
    private final int zzxr;
    private int zzxs;
    private int zzxt;

    static {
        SafeParcelResponse.CREATOR = new SafeParcelResponseCreator();
    }

    public SafeParcelResponse(FieldMappingDictionary arg2, String arg3) {
        super();
        this.zzal = 1;
        this.zzxq = Parcel.obtain();
        this.zzxr = 0;
        this.zzwn = Preconditions.checkNotNull(arg2);
        this.mClassName = Preconditions.checkNotNull(arg3);
        this.zzxs = 0;
    }

    @Constructor SafeParcelResponse(@Param(id=1) int arg1, @Param(id=2) Parcel arg2, @Param(id=3) FieldMappingDictionary arg3) {
        super();
        this.zzal = arg1;
        this.zzxq = Preconditions.checkNotNull(arg2);
        arg1 = 2;
        this.zzxr = arg1;
        this.zzwn = arg3;
        String v2 = this.zzwn == null ? null : this.zzwn.getRootClassName();
        this.mClassName = v2;
        this.zzxs = arg1;
    }

    private SafeParcelResponse(SafeParcelable arg4, FieldMappingDictionary arg5, String arg6) {
        super();
        this.zzal = 1;
        this.zzxq = Parcel.obtain();
        arg4.writeToParcel(this.zzxq, 0);
        this.zzxr = 1;
        this.zzwn = Preconditions.checkNotNull(arg5);
        this.mClassName = Preconditions.checkNotNull(arg6);
        this.zzxs = 2;
    }

    public SafeParcelResponse(FieldMappingDictionary arg2) {
        this(arg2, arg2.getRootClassName());
    }

    public void addConcreteTypeArrayInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, ArrayList arg6) {
        this.zzb(arg4);
        ArrayList v5 = new ArrayList();
        arg6.size();
        int v0 = arg6.size();
        int v1 = 0;
        while(v1 < v0) {
            Object v2 = arg6.get(v1);
            ++v1;
            v5.add(((SafeParcelResponse)v2).getParcel());
        }

        SafeParcelWriter.writeParcelList(this.zzxq, arg4.getSafeParcelableFieldId(), ((List)v5), true);
    }

    public void addConcreteTypeInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg2, String arg3, FastJsonResponse arg4) {
        this.zzb(arg2);
        SafeParcelWriter.writeParcel(this.zzxq, arg2.getSafeParcelableFieldId(), ((SafeParcelResponse)arg4).getParcel(), true);
    }

    public static HashMap convertBundleToStringMap(Bundle arg4) {
        HashMap v0 = new HashMap();
        Iterator v1 = arg4.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.put(v2, arg4.getString(((String)v2)));
        }

        return v0;
    }

    public static Bundle convertStringMapToBundle(HashMap arg4) {
        Bundle v0 = new Bundle();
        Iterator v1 = arg4.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.putString(((String)v2), arg4.get(v2));
        }

        return v0;
    }

    public static SafeParcelResponse from(FastJsonResponse arg3) {
        return new SafeParcelResponse(((SafeParcelable)arg3), SafeParcelResponse.zza(arg3), arg3.getClass().getCanonicalName());
    }

    public static FieldMappingDictionary generateDictionary(java.lang.Class arg4) {
        String v4;
        String v2;
        try {
            return SafeParcelResponse.zza(arg4.newInstance());
        }
        catch(IllegalAccessException v0) {
            v2 = "Could not access object of type ";
            v4 = String.valueOf(arg4.getCanonicalName());
            v4 = v4.length() != 0 ? v2.concat(v4) : new String(v2);
            throw new IllegalStateException(v4, ((Throwable)v0));
        }
        catch(InstantiationException v0_1) {
            v2 = "Could not instantiate an object of type ";
            v4 = String.valueOf(arg4.getCanonicalName());
            v4 = v4.length() != 0 ? v2.concat(v4) : new String(v2);
            throw new IllegalStateException(v4, ((Throwable)v0_1));
        }
    }

    public Map getFieldMappings() {
        if(this.zzwn == null) {
            return null;
        }

        return this.zzwn.getFieldMapping(this.mClassName);
    }

    public Parcel getParcel() {
        switch(this.zzxs) {
            case 0: {
                goto label_3;
            }
            case 1: {
                goto label_6;
            }
        }

        goto label_11;
    label_3:
        this.zzxt = SafeParcelWriter.beginObjectHeader(this.zzxq);
    label_6:
        SafeParcelWriter.finishObjectHeader(this.zzxq, this.zzxt);
        this.zzxs = 2;
    label_11:
        return this.zzxq;
    }

    public Object getValueObject(String arg2) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public int getVersionCode() {
        return this.zzal;
    }

    public SafeParcelable inflate(Parcelable$Creator arg3) {
        Parcel v0 = this.getParcel();
        v0.setDataPosition(0);
        return arg3.createFromParcel(v0);
    }

    public boolean isPrimitiveFieldSet(String arg2) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    protected void setBigDecimalInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg2, String arg3, BigDecimal arg4) {
        this.zzb(arg2);
        SafeParcelWriter.writeBigDecimal(this.zzxq, arg2.getSafeParcelableFieldId(), arg4, true);
    }

    protected void setBigDecimalsInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, ArrayList arg6) {
        this.zzb(arg4);
        int v5 = arg6.size();
        BigDecimal[] v0 = new BigDecimal[v5];
        int v1;
        for(v1 = 0; v1 < v5; ++v1) {
            v0[v1] = arg6.get(v1);
        }

        SafeParcelWriter.writeBigDecimalArray(this.zzxq, arg4.getSafeParcelableFieldId(), v0, true);
    }

    protected void setBigIntegerInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg2, String arg3, BigInteger arg4) {
        this.zzb(arg2);
        SafeParcelWriter.writeBigInteger(this.zzxq, arg2.getSafeParcelableFieldId(), arg4, true);
    }

    protected void setBigIntegersInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, ArrayList arg6) {
        this.zzb(arg4);
        int v5 = arg6.size();
        BigInteger[] v0 = new BigInteger[v5];
        int v1;
        for(v1 = 0; v1 < v5; ++v1) {
            v0[v1] = arg6.get(v1);
        }

        SafeParcelWriter.writeBigIntegerArray(this.zzxq, arg4.getSafeParcelableFieldId(), v0, true);
    }

    protected void setBooleanInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg1, String arg2, boolean arg3) {
        this.zzb(arg1);
        SafeParcelWriter.writeBoolean(this.zzxq, arg1.getSafeParcelableFieldId(), arg3);
    }

    protected void setBooleansInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, ArrayList arg6) {
        this.zzb(arg4);
        int v5 = arg6.size();
        boolean[] v0 = new boolean[v5];
        int v1;
        for(v1 = 0; v1 < v5; ++v1) {
            v0[v1] = arg6.get(v1).booleanValue();
        }

        SafeParcelWriter.writeBooleanArray(this.zzxq, arg4.getSafeParcelableFieldId(), v0, true);
    }

    protected void setDecodedBytesInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg2, String arg3, byte[] arg4) {
        this.zzb(arg2);
        SafeParcelWriter.writeByteArray(this.zzxq, arg2.getSafeParcelableFieldId(), arg4, true);
    }

    protected void setDoubleInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg1, String arg2, double arg3) {
        this.zzb(arg1);
        SafeParcelWriter.writeDouble(this.zzxq, arg1.getSafeParcelableFieldId(), arg3);
    }

    protected void setDoublesInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg5, String arg6, ArrayList arg7) {
        this.zzb(arg5);
        int v6 = arg7.size();
        double[] v0 = new double[v6];
        int v1;
        for(v1 = 0; v1 < v6; ++v1) {
            v0[v1] = arg7.get(v1).doubleValue();
        }

        SafeParcelWriter.writeDoubleArray(this.zzxq, arg5.getSafeParcelableFieldId(), v0, true);
    }

    protected void setFloatInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg1, String arg2, float arg3) {
        this.zzb(arg1);
        SafeParcelWriter.writeFloat(this.zzxq, arg1.getSafeParcelableFieldId(), arg3);
    }

    protected void setFloatsInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, ArrayList arg6) {
        this.zzb(arg4);
        int v5 = arg6.size();
        float[] v0 = new float[v5];
        int v1;
        for(v1 = 0; v1 < v5; ++v1) {
            v0[v1] = arg6.get(v1).floatValue();
        }

        SafeParcelWriter.writeFloatArray(this.zzxq, arg4.getSafeParcelableFieldId(), v0, true);
    }

    protected void setIntegerInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg1, String arg2, int arg3) {
        this.zzb(arg1);
        SafeParcelWriter.writeInt(this.zzxq, arg1.getSafeParcelableFieldId(), arg3);
    }

    protected void setIntegersInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, ArrayList arg6) {
        this.zzb(arg4);
        int v5 = arg6.size();
        int[] v0 = new int[v5];
        int v1;
        for(v1 = 0; v1 < v5; ++v1) {
            v0[v1] = arg6.get(v1).intValue();
        }

        SafeParcelWriter.writeIntArray(this.zzxq, arg4.getSafeParcelableFieldId(), v0, true);
    }

    protected void setLongInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg1, String arg2, long arg3) {
        this.zzb(arg1);
        SafeParcelWriter.writeLong(this.zzxq, arg1.getSafeParcelableFieldId(), arg3);
    }

    protected void setLongsInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg5, String arg6, ArrayList arg7) {
        this.zzb(arg5);
        int v6 = arg7.size();
        long[] v0 = new long[v6];
        int v1;
        for(v1 = 0; v1 < v6; ++v1) {
            v0[v1] = arg7.get(v1).longValue();
        }

        SafeParcelWriter.writeLongArray(this.zzxq, arg5.getSafeParcelableFieldId(), v0, true);
    }

    protected void setStringInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg2, String arg3, String arg4) {
        this.zzb(arg2);
        SafeParcelWriter.writeString(this.zzxq, arg2.getSafeParcelableFieldId(), arg4, true);
    }

    protected void setStringMapInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, Map arg6) {
        this.zzb(arg4);
        Bundle v5 = new Bundle();
        Iterator v0 = arg6.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            v5.putString(((String)v1), arg6.get(v1));
        }

        SafeParcelWriter.writeBundle(this.zzxq, arg4.getSafeParcelableFieldId(), v5, true);
    }

    protected void setStringsInternal(com.google.android.gms.common.server.response.FastJsonResponse$Field arg4, String arg5, ArrayList arg6) {
        this.zzb(arg4);
        int v5 = arg6.size();
        String[] v0 = new String[v5];
        int v1;
        for(v1 = 0; v1 < v5; ++v1) {
            v0[v1] = arg6.get(v1);
        }

        SafeParcelWriter.writeStringArray(this.zzxq, arg4.getSafeParcelableFieldId(), v0, true);
    }

    public String toString() {
        Preconditions.checkNotNull(this.zzwn, "Cannot convert to JSON on client side.");
        Parcel v0 = this.getParcel();
        v0.setDataPosition(0);
        StringBuilder v1 = new StringBuilder(100);
        this.zza(v1, this.zzwn.getFieldMapping(this.mClassName), v0);
        return v1.toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.getVersionCode());
        SafeParcelWriter.writeParcel(arg5, 2, this.getParcel(), false);
        switch(this.zzxr) {
            case 0: {
                goto label_23;
            }
            case 1: 
            case 2: {
                goto label_21;
            }
        }

        arg6 = this.zzxr;
        StringBuilder v1 = new StringBuilder(34);
        v1.append("Invalid creation type: ");
        v1.append(arg6);
        throw new IllegalStateException(v1.toString());
    label_21:
        FieldMappingDictionary v1_1 = this.zzwn;
        goto label_24;
    label_23:
        Parcelable v1_2 = null;
    label_24:
        SafeParcelWriter.writeParcelable(arg5, 3, ((Parcelable)v1_1), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    private static FieldMappingDictionary zza(FastJsonResponse arg2) {
        FieldMappingDictionary v0 = new FieldMappingDictionary(arg2.getClass());
        SafeParcelResponse.zza(v0, arg2);
        v0.copyInternalFieldMappings();
        v0.linkFields();
        return v0;
    }

    private static void zza(FieldMappingDictionary arg3, FastJsonResponse arg4) {
        String v1_1;
        String v0_2;
        java.lang.Class v0 = arg4.getClass();
        if(!arg3.hasFieldMappingForClass(v0)) {
            Map v4 = arg4.getFieldMappings();
            arg3.put(v0, v4);
            Iterator v0_1 = v4.keySet().iterator();
            while(v0_1.hasNext()) {
                Object v1 = v4.get(v0_1.next());
                java.lang.Class v2 = ((com.google.android.gms.common.server.response.FastJsonResponse$Field)v1).getConcreteType();
                if(v2 != null) {
                    goto label_13;
                }
                else {
                    continue;
                    try {
                    label_13:
                        SafeParcelResponse.zza(arg3, v2.newInstance());
                        continue;
                    }
                    catch(IllegalAccessException v3) {
                        v0_2 = "Could not access object of type ";
                        v1_1 = String.valueOf(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v1).getConcreteType().getCanonicalName());
                        v0_2 = v1_1.length() != 0 ? v0_2.concat(v1_1) : new String(v0_2);
                        throw new IllegalStateException(v0_2, ((Throwable)v3));
                    }
                    catch(InstantiationException v3_1) {
                        v0_2 = "Could not instantiate an object of type ";
                        v1_1 = String.valueOf(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v1).getConcreteType().getCanonicalName());
                        v0_2 = v1_1.length() != 0 ? v0_2.concat(v1_1) : new String(v0_2);
                        throw new IllegalStateException(v0_2, ((Throwable)v3_1));
                    }
                }

                return;
            }
        }
    }

    private static void zza(StringBuilder arg1, int arg2, Object arg3) {
        switch(arg2) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                goto label_39;
            }
            case 7: {
                goto label_31;
            }
            case 8: {
                goto label_24;
            }
            case 9: {
                goto label_17;
            }
            case 10: {
                goto label_15;
            }
            case 11: {
                goto label_11;
            }
        }

        StringBuilder v0 = new StringBuilder(26);
        v0.append("Unknown type = ");
        v0.append(arg2);
        throw new IllegalArgumentException(v0.toString());
    label_39:
        arg1.append(arg3);
        return;
    label_11:
        throw new IllegalArgumentException("Method does not accept concrete type.");
    label_15:
        MapUtils.writeStringMapToJson(arg1, ((HashMap)arg3));
        return;
    label_17:
        arg1.append("\"");
        arg1.append(Base64Utils.encodeUrlSafe(((byte[])arg3)));
        arg1.append("\"");
        return;
    label_24:
        arg1.append("\"");
        arg1.append(Base64Utils.encode(((byte[])arg3)));
        arg1.append("\"");
        return;
    label_31:
        arg1.append("\"");
        arg1.append(JsonUtils.escapeString(arg3.toString()));
        arg1.append("\"");
    }

    private final void zza(StringBuilder arg10, Map arg11, Parcel arg12) {
        int v6;
        Parcel[] v3_13;
        BigDecimal v3_10;
        String v3_8;
        byte[] v3_7;
        BigInteger v3_4;
        Object v5;
        int v4;
        SparseArray v0 = new SparseArray();
        Iterator v11 = arg11.entrySet().iterator();
        while(v11.hasNext()) {
            Object v1 = v11.next();
            v0.put(((Map$Entry)v1).getValue().getSafeParcelableFieldId(), v1);
        }

        arg10.append('{');
        int v11_1 = SafeParcelReader.validateObjectHeader(arg12);
        int v3 = 0;
        do {
        label_17:
            if(arg12.dataPosition() >= v11_1) {
                goto label_213;
            }

            v4 = SafeParcelReader.readHeader(arg12);
            v5 = v0.get(SafeParcelReader.getFieldId(v4));
        }
        while(v5 == null);

        if(v3 != 0) {
            arg10.append(",");
        }

        Object v3_1 = ((Map$Entry)v5).getKey();
        v5 = ((Map$Entry)v5).getValue();
        arg10.append("\"");
        arg10.append(((String)v3_1));
        arg10.append("\":");
        if(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).hasConverter()) {
            switch(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).getTypeOut()) {
                case 0: {
                    goto label_75;
                }
                case 1: {
                    goto label_73;
                }
                case 2: {
                    goto label_70;
                }
                case 3: {
                    goto label_67;
                }
                case 4: {
                    goto label_64;
                }
                case 5: {
                    goto label_62;
                }
                case 6: {
                    goto label_59;
                }
                case 7: {
                    goto label_57;
                }
                case 8: 
                case 9: {
                    goto label_55;
                }
                case 10: {
                    goto label_52;
                }
                case 11: {
                    goto label_48;
                }
            }

            v11_1 = ((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).getTypeOut();
            StringBuilder v0_1 = new StringBuilder(36);
            v0_1.append("Unknown field out type = ");
            v0_1.append(v11_1);
            throw new IllegalArgumentException(v0_1.toString());
        label_67:
            Float v3_2 = Float.valueOf(SafeParcelReader.readFloat(arg12, v4));
            goto label_77;
        label_70:
            Long v3_3 = Long.valueOf(SafeParcelReader.readLong(arg12, v4));
            goto label_77;
        label_73:
            v3_4 = SafeParcelReader.createBigInteger(arg12, v4);
            goto label_77;
        label_75:
            Integer v3_5 = Integer.valueOf(SafeParcelReader.readInt(arg12, v4));
            goto label_77;
        label_48:
            throw new IllegalArgumentException("Method does not accept concrete type.");
        label_52:
            HashMap v3_6 = SafeParcelResponse.convertBundleToStringMap(SafeParcelReader.createBundle(arg12, v4));
            goto label_77;
        label_55:
            v3_7 = SafeParcelReader.createByteArray(arg12, v4);
            goto label_77;
        label_57:
            v3_8 = SafeParcelReader.createString(arg12, v4);
            goto label_77;
        label_59:
            Boolean v3_9 = Boolean.valueOf(SafeParcelReader.readBoolean(arg12, v4));
            goto label_77;
        label_62:
            v3_10 = SafeParcelReader.createBigDecimal(arg12, v4);
            goto label_77;
        label_64:
            Double v3_11 = Double.valueOf(SafeParcelReader.readDouble(arg12, v4));
        label_77:
            this.zzb(arg10, ((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5), ((FastJsonResponse)this).getOriginalValue(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5), v3_5));
        }
        else {
            if(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).isTypeOutArray()) {
                arg10.append("[");
                switch(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).getTypeOut()) {
                    case 0: {
                        goto label_128;
                    }
                    case 1: {
                        goto label_125;
                    }
                    case 2: {
                        goto label_122;
                    }
                    case 3: {
                        goto label_119;
                    }
                    case 4: {
                        goto label_116;
                    }
                    case 5: {
                        goto label_114;
                    }
                    case 6: {
                        goto label_111;
                    }
                    case 7: {
                        goto label_108;
                    }
                    case 8: 
                    case 9: 
                    case 10: {
                        goto label_104;
                    }
                    case 11: {
                        goto label_90;
                    }
                }

                throw new IllegalStateException("Unknown field type out.");
            label_104:
                throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
            label_108:
                ArrayUtils.writeStringArray(arg10, SafeParcelReader.createStringArray(arg12, v4));
                goto label_130;
            label_111:
                ArrayUtils.writeArray(arg10, SafeParcelReader.createBooleanArray(arg12, v4));
                goto label_130;
            label_114:
                BigDecimal[] v3_12 = SafeParcelReader.createBigDecimalArray(arg12, v4);
                goto label_126;
            label_116:
                ArrayUtils.writeArray(arg10, SafeParcelReader.createDoubleArray(arg12, v4));
                goto label_130;
            label_119:
                ArrayUtils.writeArray(arg10, SafeParcelReader.createFloatArray(arg12, v4));
                goto label_130;
            label_122:
                ArrayUtils.writeArray(arg10, SafeParcelReader.createLongArray(arg12, v4));
                goto label_130;
            label_90:
                v3_13 = SafeParcelReader.createParcelArray(arg12, v4);
                v4 = v3_13.length;
                v6 = 0;
                goto label_93;
            label_125:
                BigInteger[] v3_14 = SafeParcelReader.createBigIntegerArray(arg12, v4);
            label_126:
                ArrayUtils.writeArray(arg10, ((Object[])v3_12));
                goto label_130;
            label_128:
                ArrayUtils.writeArray(arg10, SafeParcelReader.createIntArray(arg12, v4));
                goto label_130;
            }
            else {
                switch(((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).getTypeOut()) {
                    case 0: {
                        goto label_209;
                    }
                    case 1: {
                        goto label_206;
                    }
                    case 2: {
                        goto label_203;
                    }
                    case 3: {
                        goto label_200;
                    }
                    case 4: {
                        goto label_197;
                    }
                    case 5: {
                        goto label_195;
                    }
                    case 6: {
                        goto label_192;
                    }
                    case 7: {
                        goto label_184;
                    }
                    case 8: {
                        goto label_179;
                    }
                    case 9: {
                        goto label_174;
                    }
                    case 10: {
                        goto label_143;
                    }
                    case 11: {
                        goto label_138;
                    }
                }

                throw new IllegalStateException("Unknown field type out");
            label_195:
                v3_10 = SafeParcelReader.createBigDecimal(arg12, v4);
                goto label_207;
            label_197:
                arg10.append(SafeParcelReader.readDouble(arg12, v4));
                goto label_211;
            label_200:
                arg10.append(SafeParcelReader.readFloat(arg12, v4));
                goto label_211;
            label_138:
                Parcel v3_15 = SafeParcelReader.createParcel(arg12, v4);
                v3_15.setDataPosition(0);
                this.zza(arg10, ((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).getConcreteTypeFieldMappingFromDictionary(), v3_15);
                goto label_211;
            label_203:
                arg10.append(SafeParcelReader.readLong(arg12, v4));
                goto label_211;
            label_206:
                v3_4 = SafeParcelReader.createBigInteger(arg12, v4);
            label_207:
                arg10.append(v3_4);
                goto label_211;
            label_174:
                v3_7 = SafeParcelReader.createByteArray(arg12, v4);
                arg10.append("\"");
                v3_8 = Base64Utils.encodeUrlSafe(v3_7);
                goto label_188;
            label_143:
                Bundle v3_16 = SafeParcelReader.createBundle(arg12, v4);
                Set v4_1 = v3_16.keySet();
                v4_1.size();
                arg10.append("{");
                Iterator v4_2 = v4_1.iterator();
                int v5_1 = 1;
                goto label_150;
            label_209:
                arg10.append(SafeParcelReader.readInt(arg12, v4));
                goto label_211;
            label_179:
                v3_7 = SafeParcelReader.createByteArray(arg12, v4);
                arg10.append("\"");
                v3_8 = Base64Utils.encode(v3_7);
                goto label_188;
            label_184:
                v3_8 = SafeParcelReader.createString(arg12, v4);
                arg10.append("\"");
                v3_8 = JsonUtils.escapeString(v3_8);
            label_188:
                arg10.append(v3_8);
                v3_8 = "\"";
                goto label_190;
            label_192:
                arg10.append(SafeParcelReader.readBoolean(arg12, v4));
                goto label_211;
            label_213:
                if(arg12.dataPosition() == v11_1) {
                    arg10.append('}');
                    return;
                }
                else {
                    StringBuilder v1_1 = new StringBuilder(37);
                    v1_1.append("Overread allowed size end=");
                    v1_1.append(v11_1);
                    throw new ParseException(v1_1.toString(), arg12);
                label_150:
                    while(v4_2.hasNext()) {
                        Object v6_1 = v4_2.next();
                        if(v5_1 == 0) {
                            arg10.append(",");
                        }

                        arg10.append("\"");
                        arg10.append(((String)v6_1));
                        arg10.append("\"");
                        arg10.append(":");
                        arg10.append("\"");
                        arg10.append(JsonUtils.escapeString(v3_16.getString(((String)v6_1))));
                        arg10.append("\"");
                        v5_1 = 0;
                    }

                    v3_8 = "}";
                    goto label_190;
                label_93:
                    while(v6 < v4) {
                        if(v6 > 0) {
                            arg10.append(",");
                        }

                        v3_13[v6].setDataPosition(0);
                        this.zza(arg10, ((com.google.android.gms.common.server.response.FastJsonResponse$Field)v5).getConcreteTypeFieldMappingFromDictionary(), v3_13[v6]);
                        ++v6;
                    }

                label_130:
                    v3_8 = "]";
                }
            }

        label_190:
            arg10.append(v3_8);
        }

    label_211:
        v3 = 1;
        goto label_17;
    }

    private final void zzb(StringBuilder arg5, com.google.android.gms.common.server.response.FastJsonResponse$Field arg6, Object arg7) {
        if(arg6.isTypeInArray()) {
            arg5.append("[");
            int v0 = ((ArrayList)arg7).size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                if(v1 != 0) {
                    arg5.append(",");
                }

                SafeParcelResponse.zza(arg5, arg6.getTypeIn(), ((ArrayList)arg7).get(v1));
            }

            arg5.append("]");
            return;
        }

        SafeParcelResponse.zza(arg5, arg6.getTypeIn(), arg7);
    }

    private final void zzb(com.google.android.gms.common.server.response.FastJsonResponse$Field arg2) {
        if(!arg2.isValidSafeParcelableFieldId()) {
            goto label_25;
        }

        if(this.zzxq == null) {
            goto label_21;
        }

        switch(this.zzxs) {
            case 0: {
                goto label_15;
            }
            case 1: {
                return;
            }
            case 2: {
                goto label_10;
            }
        }

        throw new IllegalStateException("Unknown parse state in SafeParcelResponse.");
    label_10:
        throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
        return;
    label_15:
        this.zzxt = SafeParcelWriter.beginObjectHeader(this.zzxq);
        this.zzxs = 1;
        return;
    label_21:
        throw new IllegalStateException("Internal Parcel object is null.");
    label_25:
        throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
    }
}

