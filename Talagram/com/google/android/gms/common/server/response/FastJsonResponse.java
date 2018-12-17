package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public abstract class FastJsonResponse {
    @Class(creator="FieldCreator") @VisibleForTesting public class Field extends AbstractSafeParcelable {
        public static final FieldCreator CREATOR;
        protected final java.lang.Class mConcreteType;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="getConcreteTypeName", id=8) protected final String mConcreteTypeName;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="getOutputFieldName", id=6) protected final String mOutputFieldName;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="getSafeParcelableFieldId", id=7) protected final int mSafeParcelableFieldId;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="getTypeIn", id=2) protected final int mTypeIn;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="isTypeInArray", id=3) protected final boolean mTypeInArray;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="getTypeOut", id=4) protected final int mTypeOut;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="isTypeOutArray", id=5) protected final boolean mTypeOutArray;
        @VersionField(getter="getVersionCode", id=1) private final int zzal;
        private FieldMappingDictionary zzwn;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field(getter="getWrappedConverter", id=9, type="com.google.android.gms.common.server.converter.ConverterWrapper") private FieldConverter zzwo;

        static {
            Field.CREATOR = new FieldCreator();
        }

        @Constructor Field(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) boolean arg3, @Param(id=4) int arg4, @Param(id=5) boolean arg5, @Param(id=6) String arg6, @Param(id=7) int arg7, @Param(id=8) String arg8, @Param(id=9) ConverterWrapper arg9) {
            super();
            this.zzal = arg1;
            this.mTypeIn = arg2;
            this.mTypeInArray = arg3;
            this.mTypeOut = arg4;
            this.mTypeOutArray = arg5;
            this.mOutputFieldName = arg6;
            this.mSafeParcelableFieldId = arg7;
            java.lang.Class v1 = null;
            if(arg8 == null) {
                this.mConcreteType = v1;
                this.mConcreteTypeName = ((String)v1);
            }
            else {
                this.mConcreteType = SafeParcelResponse.class;
                this.mConcreteTypeName = arg8;
            }

            if(arg9 == null) {
                this.zzwo = ((FieldConverter)v1);
                return;
            }

            this.zzwo = arg9.unwrap();
        }

        protected Field(int arg2, boolean arg3, int arg4, boolean arg5, String arg6, int arg7, java.lang.Class arg8, FieldConverter arg9) {
            super();
            this.zzal = 1;
            this.mTypeIn = arg2;
            this.mTypeInArray = arg3;
            this.mTypeOut = arg4;
            this.mTypeOutArray = arg5;
            this.mOutputFieldName = arg6;
            this.mSafeParcelableFieldId = arg7;
            this.mConcreteType = arg8;
            String v2 = arg8 == null ? null : arg8.getCanonicalName();
            this.mConcreteTypeName = v2;
            this.zzwo = arg9;
        }

        public Object convert(Object arg2) {
            return this.zzwo.convert(arg2);
        }

        public Object convertBack(Object arg2) {
            return this.zzwo.convertBack(arg2);
        }

        public Field copyForDictionary() {
            return new Field(this.zzal, this.mTypeIn, this.mTypeInArray, this.mTypeOut, this.mTypeOutArray, this.mOutputFieldName, this.mSafeParcelableFieldId, this.mConcreteTypeName, this.zzda());
        }

        public static Field forBase64(String arg10) {
            return new Field(8, false, 8, false, arg10, -1, null, null);
        }

        @VisibleForTesting public static Field forBase64(String arg10, int arg11) {
            return new Field(8, false, 8, false, arg10, arg11, null, null);
        }

        @VisibleForTesting public static Field forBase64UrlSafe(String arg10) {
            return new Field(9, false, 9, false, arg10, -1, null, null);
        }

        @VisibleForTesting public static Field forBase64UrlSafe(String arg10, int arg11) {
            return new Field(9, false, 9, false, arg10, arg11, null, null);
        }

        public static Field forBigDecimal(String arg10) {
            return new Field(5, false, 5, false, arg10, -1, null, null);
        }

        @VisibleForTesting public static Field forBigDecimal(String arg10, int arg11) {
            return new Field(5, false, 5, false, arg10, arg11, null, null);
        }

        public static Field forBigDecimals(String arg10) {
            return new Field(5, true, 5, true, arg10, -1, null, null);
        }

        public static Field forBigDecimals(String arg10, int arg11) {
            return new Field(5, true, 5, true, arg10, arg11, null, null);
        }

        public static Field forBigInteger(String arg10) {
            return new Field(1, false, 1, false, arg10, -1, null, null);
        }

        public static Field forBigInteger(String arg10, int arg11) {
            return new Field(1, false, 1, false, arg10, arg11, null, null);
        }

        public static Field forBigIntegers(String arg10) {
            return new Field(0, true, 1, true, arg10, -1, null, null);
        }

        public static Field forBigIntegers(String arg10, int arg11) {
            return new Field(0, true, 1, true, arg10, arg11, null, null);
        }

        public static Field forBoolean(String arg10) {
            return new Field(6, false, 6, false, arg10, -1, null, null);
        }

        public static Field forBoolean(String arg10, int arg11) {
            return new Field(6, false, 6, false, arg10, arg11, null, null);
        }

        public static Field forBooleans(String arg10) {
            return new Field(6, true, 6, true, arg10, -1, null, null);
        }

        public static Field forBooleans(String arg10, int arg11) {
            return new Field(6, true, 6, true, arg10, arg11, null, null);
        }

        public static Field forConcreteType(String arg10, int arg11, java.lang.Class arg12) {
            return new Field(11, false, 11, false, arg10, arg11, arg12, null);
        }

        public static Field forConcreteType(String arg10, java.lang.Class arg11) {
            return new Field(11, false, 11, false, arg10, -1, arg11, null);
        }

        public static Field forConcreteTypeArray(String arg10, int arg11, java.lang.Class arg12) {
            return new Field(11, true, 11, true, arg10, arg11, arg12, null);
        }

        public static Field forConcreteTypeArray(String arg10, java.lang.Class arg11) {
            return new Field(11, true, 11, true, arg10, -1, arg11, null);
        }

        public static Field forDouble(String arg10) {
            return new Field(4, false, 4, false, arg10, -1, null, null);
        }

        public static Field forDouble(String arg10, int arg11) {
            return new Field(4, false, 4, false, arg10, arg11, null, null);
        }

        public static Field forDoubles(String arg10) {
            return new Field(4, true, 4, true, arg10, -1, null, null);
        }

        public static Field forDoubles(String arg10, int arg11) {
            return new Field(4, true, 4, true, arg10, arg11, null, null);
        }

        public static Field forFloat(String arg10) {
            return new Field(3, false, 3, false, arg10, -1, null, null);
        }

        public static Field forFloat(String arg10, int arg11) {
            return new Field(3, false, 3, false, arg10, arg11, null, null);
        }

        public static Field forFloats(String arg10) {
            return new Field(3, true, 3, true, arg10, -1, null, null);
        }

        public static Field forFloats(String arg10, int arg11) {
            return new Field(3, true, 3, true, arg10, arg11, null, null);
        }

        public static Field forInteger(String arg10) {
            return new Field(0, false, 0, false, arg10, -1, null, null);
        }

        @VisibleForTesting public static Field forInteger(String arg10, int arg11) {
            return new Field(0, false, 0, false, arg10, arg11, null, null);
        }

        public static Field forIntegers(String arg10) {
            return new Field(0, true, 0, true, arg10, -1, null, null);
        }

        @VisibleForTesting public static Field forIntegers(String arg10, int arg11) {
            return new Field(0, true, 0, true, arg10, arg11, null, null);
        }

        @VisibleForTesting public static Field forLong(String arg10) {
            return new Field(2, false, 2, false, arg10, -1, null, null);
        }

        public static Field forLong(String arg10, int arg11) {
            return new Field(2, false, 2, false, arg10, arg11, null, null);
        }

        @VisibleForTesting public static Field forLongs(String arg10) {
            return new Field(2, true, 2, true, arg10, -1, null, null);
        }

        public static Field forLongs(String arg10, int arg11) {
            return new Field(2, true, 2, true, arg10, arg11, null, null);
        }

        public static Field forString(String arg10) {
            return new Field(7, false, 7, false, arg10, -1, null, null);
        }

        public static Field forString(String arg10, int arg11) {
            return new Field(7, false, 7, false, arg10, arg11, null, null);
        }

        public static Field forStringMap(String arg10) {
            return new Field(10, false, 10, false, arg10, -1, null, null);
        }

        public static Field forStringMap(String arg10, int arg11) {
            return new Field(10, false, 10, false, arg10, arg11, null, null);
        }

        public static Field forStrings(String arg10) {
            return new Field(7, true, 7, true, arg10, -1, null, null);
        }

        public static Field forStrings(String arg10, int arg11) {
            return new Field(7, true, 7, true, arg10, arg11, null, null);
        }

        public java.lang.Class getConcreteType() {
            return this.mConcreteType;
        }

        public Map getConcreteTypeFieldMappingFromDictionary() {
            Preconditions.checkNotNull(this.mConcreteTypeName);
            Preconditions.checkNotNull(this.zzwn);
            return this.zzwn.getFieldMapping(this.mConcreteTypeName);
        }

        public String getOutputFieldName() {
            return this.mOutputFieldName;
        }

        public int getSafeParcelableFieldId() {
            return this.mSafeParcelableFieldId;
        }

        public int getTypeIn() {
            return this.mTypeIn;
        }

        public int getTypeOut() {
            return this.mTypeOut;
        }

        public int getVersionCode() {
            return this.zzal;
        }

        public boolean hasConverter() {
            if(this.zzwo != null) {
                return 1;
            }

            return 0;
        }

        public boolean isTypeInArray() {
            return this.mTypeInArray;
        }

        public boolean isTypeOutArray() {
            return this.mTypeOutArray;
        }

        public boolean isValidSafeParcelableFieldId() {
            if(this.mSafeParcelableFieldId != -1) {
                return 1;
            }

            return 0;
        }

        public FastJsonResponse newConcreteTypeInstance() {
            if(this.mConcreteType == SafeParcelResponse.class) {
                Preconditions.checkNotNull(this.zzwn, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
                return new SafeParcelResponse(this.zzwn, this.mConcreteTypeName);
            }

            return this.mConcreteType.newInstance();
        }

        public void setFieldMappingDictionary(FieldMappingDictionary arg1) {
            this.zzwn = arg1;
        }

        public String toString() {
            ToStringHelper v0 = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zzal)).add("typeIn", Integer.valueOf(this.mTypeIn)).add("typeInArray", Boolean.valueOf(this.mTypeInArray)).add("typeOut", Integer.valueOf(this.mTypeOut)).add("typeOutArray", Boolean.valueOf(this.mTypeOutArray)).add("outputFieldName", this.mOutputFieldName).add("safeParcelFieldId", Integer.valueOf(this.mSafeParcelableFieldId)).add("concreteTypeName", this.zzcz());
            java.lang.Class v1 = this.getConcreteType();
            if(v1 != null) {
                v0.add("concreteType.class", v1.getCanonicalName());
            }

            if(this.zzwo != null) {
                v0.add("converterName", this.zzwo.getClass().getCanonicalName());
            }

            return v0.toString();
        }

        public static Field withConverter(String arg10, int arg11, FieldConverter arg12, boolean arg13) {
            return new Field(arg12.getTypeIn(), arg13, arg12.getTypeOut(), false, arg10, arg11, null, arg12);
        }

        public static Field withConverter(String arg0, int arg1, java.lang.Class arg2, boolean arg3) {
            try {
                return Field.withConverter(arg0, arg1, arg2.newInstance(), arg3);
            }
            catch(IllegalAccessException v0) {
                throw new RuntimeException(((Throwable)v0));
            }
            catch(InstantiationException v0_1) {
                throw new RuntimeException(((Throwable)v0_1));
            }
        }

        public static Field withConverter(String arg1, FieldConverter arg2, boolean arg3) {
            return Field.withConverter(arg1, -1, arg2, arg3);
        }

        public static Field withConverter(String arg1, java.lang.Class arg2, boolean arg3) {
            return Field.withConverter(arg1, -1, arg2, arg3);
        }

        public void writeToParcel(Parcel arg5, int arg6) {
            int v0 = SafeParcelWriter.beginObjectHeader(arg5);
            SafeParcelWriter.writeInt(arg5, 1, this.getVersionCode());
            SafeParcelWriter.writeInt(arg5, 2, this.getTypeIn());
            SafeParcelWriter.writeBoolean(arg5, 3, this.isTypeInArray());
            SafeParcelWriter.writeInt(arg5, 4, this.getTypeOut());
            SafeParcelWriter.writeBoolean(arg5, 5, this.isTypeOutArray());
            SafeParcelWriter.writeString(arg5, 6, this.getOutputFieldName(), false);
            SafeParcelWriter.writeInt(arg5, 7, this.getSafeParcelableFieldId());
            SafeParcelWriter.writeString(arg5, 8, this.zzcz(), false);
            SafeParcelWriter.writeParcelable(arg5, 9, this.zzda(), arg6, false);
            SafeParcelWriter.finishObjectHeader(arg5, v0);
        }

        static FieldConverter zza(Field arg0) {
            return arg0.zzwo;
        }

        private final String zzcz() {
            if(this.mConcreteTypeName == null) {
                return null;
            }

            return this.mConcreteTypeName;
        }

        private final ConverterWrapper zzda() {
            if(this.zzwo == null) {
                return null;
            }

            return ConverterWrapper.wrap(this.zzwo);
        }
    }

    public interface FieldConverter {
        Object convert(Object arg1);

        Object convertBack(Object arg1);

        int getTypeIn();

        int getTypeOut();
    }

    public interface FieldType {
        public static final int BASE64 = 8;
        public static final int BASE64_URL_SAFE = 9;
        public static final int BIG_DECIMAL = 5;
        public static final int BIG_INTEGER = 1;
        public static final int BOOLEAN = 6;
        public static final int CONCRETE_TYPE = 11;
        public static final int DOUBLE = 4;
        public static final int FLOAT = 3;
        public static final int INT = 0;
        public static final int LONG = 2;
        public static final int STRING = 7;
        public static final int STRING_MAP = 10;

    }

    protected static final String QUOTE = "\"";
    private int zzwk;
    private byte[] zzwl;
    private boolean zzwm;

    public FastJsonResponse() {
        super();
    }

    public void addConcreteType(String arg1, FastJsonResponse arg2) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    public void addConcreteTypeArray(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    public void addConcreteTypeArrayInternal(Field arg1, String arg2, ArrayList arg3) {
        this.addConcreteTypeArray(arg2, arg3);
    }

    public void addConcreteTypeInternal(Field arg1, String arg2, FastJsonResponse arg3) {
        this.addConcreteType(arg2, arg3);
    }

    public HashMap getConcreteTypeArrays() {
        return null;
    }

    public HashMap getConcreteTypes() {
        return null;
    }

    public abstract Map getFieldMappings();

    protected Object getFieldValue(Field arg8) {
        String v0 = arg8.getOutputFieldName();
        if(arg8.getConcreteType() != null) {
            boolean v1 = this.getValueObject(arg8.getOutputFieldName()) == null ? true : false;
            Preconditions.checkState(v1, "Concrete field shouldn\'t be value object: %s", new Object[]{arg8.getOutputFieldName()});
            HashMap v8 = arg8.isTypeOutArray() ? this.getConcreteTypeArrays() : this.getConcreteTypes();
            if(v8 != null) {
                return ((Map)v8).get(v0);
            }

            try {
                char v8_2 = Character.toUpperCase(v0.charAt(0));
                v0 = v0.substring(1);
                StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 4);
                v2.append("get");
                v2.append(v8_2);
                v2.append(v0);
                return this.getClass().getMethod(v2.toString()).invoke(this);
            }
            catch(Exception v8_1) {
                throw new RuntimeException(((Throwable)v8_1));
            }
        }

        return this.getValueObject(arg8.getOutputFieldName());
    }

    protected Object getOriginalValue(Field arg2, Object arg3) {
        if(Field.zza(arg2) != null) {
            return arg2.convertBack(arg3);
        }

        return arg3;
    }

    public PostProcessor getPostProcessor() {
        return null;
    }

    public byte[] getResponseBody() {
        byte[] v1_2;
        byte[] v0_2;
        int v0;
        GZIPInputStream v1_1;
        Preconditions.checkState(this.zzwm);
        try {
            v1_1 = new GZIPInputStream(new ByteArrayInputStream(this.zzwl));
            v0 = 4096;
        }
        catch(Throwable v1) {
            goto label_27;
        }
        catch(IOException ) {
            goto label_31;
        }

        try {
            byte[] v2 = new byte[v0];
            ByteArrayOutputStream v3 = new ByteArrayOutputStream();
            while(true) {
                int v5 = ((InputStream)v1_1).read(v2, 0, v0);
                if(v5 == -1) {
                    break;
                }

                v3.write(v2, 0, v5);
            }

            v3.flush();
            v0_2 = v3.toByteArray();
        }
        catch(Throwable v0_1) {
            goto label_35;
        }
        catch(IOException ) {
            goto label_24;
        }

        try {
            ((InputStream)v1_1).close();
            return v0_2;
        }
        catch(IOException ) {
            return v0_2;
        }

    label_24:
        GZIPInputStream v0_3 = v1_1;
        try {
        label_31:
            v1_2 = this.zzwl;
            if((((GZIPInputStream)v0)) == null) {
                return v1_2;
            }
        }
        catch(Throwable v1) {
        label_27:
            Throwable v7 = v1;
            v1_1 = ((GZIPInputStream)v0);
            v0_1 = v7;
            goto label_35;
        }

        try {
            ((InputStream)v0).close();
            return v1_2;
        }
        catch(IOException ) {
            return v1_2;
        }

    label_35:
        if(v1_1 != null) {
            try {
                ((InputStream)v1_1).close();
                goto label_37;
            }
            catch(IOException ) {
            label_37:
                throw v0_1;
            }
        }

        goto label_37;
    }

    public int getResponseCode() {
        Preconditions.checkState(this.zzwm);
        return this.zzwk;
    }

    public static InputStream getUnzippedStream(byte[] arg1) {
        ByteArrayInputStream v0 = new ByteArrayInputStream(arg1);
        if(!IOUtils.isGzipByteBuffer(arg1)) {
            goto label_7;
        }

        try {
            return new GZIPInputStream(((InputStream)v0));
        }
        catch(IOException ) {
        label_7:
            return ((InputStream)v0);
        }
    }

    protected abstract Object getValueObject(String arg1);

    protected boolean isConcreteTypeArrayFieldSet(String arg2) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    protected boolean isConcreteTypeFieldSet(String arg2) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean isFieldSet(Field arg3) {
        if(arg3.getTypeOut() == 11) {
            if(arg3.isTypeOutArray()) {
                return this.isConcreteTypeArrayFieldSet(arg3.getOutputFieldName());
            }

            return this.isConcreteTypeFieldSet(arg3.getOutputFieldName());
        }

        return this.isPrimitiveFieldSet(arg3.getOutputFieldName());
    }

    protected abstract boolean isPrimitiveFieldSet(String arg1);

    public void parseNetworkResponse(int arg1, byte[] arg2) {
        this.zzwk = arg1;
        this.zzwl = arg2;
        this.zzwm = true;
        InputStream v1 = FastJsonResponse.getUnzippedStream(arg2);
        try {
            new FastParser().parse(v1, this);
        }
        catch(Throwable v2) {
            try {
                v1.close();
                goto label_12;
            }
            catch(IOException ) {
            label_12:
                throw v2;
            }
        }

        try {
            v1.close();
            return;
        }
        catch(IOException ) {
            return;
        }
    }

    public final void setBigDecimal(Field arg2, BigDecimal arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setBigDecimalInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setBigDecimal(String arg1, BigDecimal arg2) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    protected void setBigDecimalInternal(Field arg1, String arg2, BigDecimal arg3) {
        this.setBigDecimal(arg2, arg3);
    }

    public final void setBigDecimals(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setBigDecimalsInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setBigDecimals(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    protected void setBigDecimalsInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setBigDecimals(arg2, arg3);
    }

    public final void setBigInteger(Field arg2, BigInteger arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setBigIntegerInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setBigInteger(String arg1, BigInteger arg2) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    protected void setBigIntegerInternal(Field arg1, String arg2, BigInteger arg3) {
        this.setBigInteger(arg2, arg3);
    }

    public final void setBigIntegers(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setBigIntegersInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setBigIntegers(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    protected void setBigIntegersInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setBigIntegers(arg2, arg3);
    }

    public final void setBoolean(Field arg2, boolean arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, Boolean.valueOf(arg3));
            return;
        }

        this.setBooleanInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setBoolean(String arg1, boolean arg2) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    protected void setBooleanInternal(Field arg1, String arg2, boolean arg3) {
        this.setBoolean(arg2, arg3);
    }

    public final void setBooleans(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setBooleansInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setBooleans(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    protected void setBooleansInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setBooleans(arg2, arg3);
    }

    public final void setDecodedBytes(Field arg2, byte[] arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setDecodedBytesInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setDecodedBytes(String arg1, byte[] arg2) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    protected void setDecodedBytesInternal(Field arg1, String arg2, byte[] arg3) {
        this.setDecodedBytes(arg2, arg3);
    }

    public final void setDouble(Field arg2, double arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, Double.valueOf(arg3));
            return;
        }

        this.setDoubleInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setDouble(String arg1, double arg2) {
        throw new UnsupportedOperationException("Double not supported");
    }

    protected void setDoubleInternal(Field arg1, String arg2, double arg3) {
        this.setDouble(arg2, arg3);
    }

    public final void setDoubles(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setDoublesInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setDoubles(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    protected void setDoublesInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setDoubles(arg2, arg3);
    }

    public final void setFloat(Field arg2, float arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, Float.valueOf(arg3));
            return;
        }

        this.setFloatInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setFloat(String arg1, float arg2) {
        throw new UnsupportedOperationException("Float not supported");
    }

    protected void setFloatInternal(Field arg1, String arg2, float arg3) {
        this.setFloat(arg2, arg3);
    }

    public final void setFloats(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setFloatsInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setFloats(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    protected void setFloatsInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setFloats(arg2, arg3);
    }

    public final void setInteger(Field arg2, int arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, Integer.valueOf(arg3));
            return;
        }

        this.setIntegerInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setInteger(String arg1, int arg2) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    protected void setIntegerInternal(Field arg1, String arg2, int arg3) {
        this.setInteger(arg2, arg3);
    }

    public final void setIntegers(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setIntegersInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setIntegers(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    protected void setIntegersInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setIntegers(arg2, arg3);
    }

    public final void setLong(Field arg2, long arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, Long.valueOf(arg3));
            return;
        }

        this.setLongInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setLong(String arg1, long arg2) {
        throw new UnsupportedOperationException("Long not supported");
    }

    protected void setLongInternal(Field arg1, String arg2, long arg3) {
        this.setLong(arg2, arg3);
    }

    public final void setLongs(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setLongsInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setLongs(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    protected void setLongsInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setLongs(arg2, arg3);
    }

    public final void setString(Field arg2, String arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setStringInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setString(String arg1, String arg2) {
        throw new UnsupportedOperationException("String not supported");
    }

    protected void setStringInternal(Field arg1, String arg2, String arg3) {
        this.setString(arg2, arg3);
    }

    public final void setStringMap(Field arg2, Map arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setStringMapInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setStringMap(String arg1, Map arg2) {
        throw new UnsupportedOperationException("String map not supported");
    }

    protected void setStringMapInternal(Field arg1, String arg2, Map arg3) {
        this.setStringMap(arg2, arg3);
    }

    public final void setStrings(Field arg2, ArrayList arg3) {
        if(Field.zza(arg2) != null) {
            this.zza(arg2, arg3);
            return;
        }

        this.setStringsInternal(arg2, arg2.getOutputFieldName(), arg3);
    }

    protected void setStrings(String arg1, ArrayList arg2) {
        throw new UnsupportedOperationException("String list not supported");
    }

    protected void setStringsInternal(Field arg1, String arg2, ArrayList arg3) {
        this.setStrings(arg2, arg3);
    }

    public String toString() {
        int v3_2;
        String v3_1;
        Object v5;
        Object v4;
        Map v0 = this.getFieldMappings();
        StringBuilder v1 = new StringBuilder(100);
        Iterator v2 = v0.keySet().iterator();
        while(true) {
        label_6:
            if(!v2.hasNext()) {
                goto label_64;
            }

            Object v3 = v2.next();
            v4 = v0.get(v3);
            if(!this.isFieldSet(((Field)v4))) {
                continue;
            }

            v5 = this.getOriginalValue(((Field)v4), this.getFieldValue(((Field)v4)));
            String v6 = v1.length() == 0 ? "{" : ",";
            v1.append(v6);
            v1.append("\"");
            v1.append(((String)v3));
            v1.append("\":");
            if(v5 == null) {
                v3_1 = "null";
            }
            else {
                switch(((Field)v4).getTypeOut()) {
                    case 8: {
                        goto label_45;
                    }
                    case 9: {
                        goto label_41;
                    }
                    case 10: {
                        goto label_39;
                    }
                }

                if(((Field)v4).isTypeInArray()) {
                    v1.append("[");
                    v3_2 = 0;
                    int v6_1 = ((ArrayList)v5).size();
                    goto label_51;
                }
                else {
                    FastJsonResponse.zza(v1, ((Field)v4), v5);
                    continue;
                label_39:
                    MapUtils.writeStringMapToJson(v1, ((HashMap)v5));
                    continue;
                }
            }

            goto label_28;
        }

    label_41:
        v1.append("\"");
        v3_1 = Base64Utils.encodeUrlSafe(((byte[])v5));
        goto label_48;
    label_45:
        v1.append("\"");
        v3_1 = Base64Utils.encode(((byte[])v5));
    label_48:
        v1.append(v3_1);
        v3_1 = "\"";
        goto label_28;
    label_64:
        String v0_1 = v1.length() > 0 ? "}" : "{}";
        v1.append(v0_1);
        return v1.toString();
    label_51:
        while(v3_2 < v6_1) {
            if(v3_2 > 0) {
                v1.append(",");
            }

            Object v7 = ((ArrayList)v5).get(v3_2);
            if(v7 != null) {
                FastJsonResponse.zza(v1, ((Field)v4), v7);
            }

            ++v3_2;
        }

        v3_1 = "]";
    label_28:
        v1.append(v3_1);
        goto label_6;
    }

    private final void zza(Field arg4, Object arg5) {
        String v0 = arg4.getOutputFieldName();
        arg5 = arg4.convert(arg5);
        switch(arg4.getTypeOut()) {
            case 0: {
                goto label_40;
            }
            case 1: {
                goto label_38;
            }
            case 2: {
                goto label_33;
            }
            case 4: {
                goto label_28;
            }
            case 5: {
                goto label_26;
            }
            case 6: {
                goto label_21;
            }
            case 7: {
                goto label_19;
            }
            case 8: 
            case 9: {
                goto label_15;
            }
        }

        int v4 = arg4.getTypeOut();
        StringBuilder v1 = new StringBuilder(44);
        v1.append("Unsupported type for conversion: ");
        v1.append(v4);
        throw new IllegalStateException(v1.toString());
    label_33:
        if(!FastJsonResponse.zzb(v0, arg5)) {
            return;
        }

        this.setLongInternal(arg4, v0, ((Long)arg5).longValue());
        return;
    label_19:
        this.setStringInternal(arg4, v0, ((String)arg5));
        return;
    label_21:
        if(!FastJsonResponse.zzb(v0, arg5)) {
            return;
        }

        this.setBooleanInternal(arg4, v0, ((Boolean)arg5).booleanValue());
        return;
    label_38:
        this.setBigIntegerInternal(arg4, v0, ((BigInteger)arg5));
        return;
    label_40:
        if(!FastJsonResponse.zzb(v0, arg5)) {
            return;
        }

        this.setIntegerInternal(arg4, v0, ((Integer)arg5).intValue());
        return;
    label_26:
        this.setBigDecimalInternal(arg4, v0, ((BigDecimal)arg5));
        return;
    label_28:
        if(!FastJsonResponse.zzb(v0, arg5)) {
            return;
        }

        this.setDoubleInternal(arg4, v0, ((Double)arg5).doubleValue());
        return;
    label_15:
        if(FastJsonResponse.zzb(v0, arg5)) {
            this.setDecodedBytesInternal(arg4, v0, ((byte[])arg5));
            return;
        }
    }

    private static void zza(StringBuilder arg2, Field arg3, Object arg4) {
        String v3;
        if(arg3.getTypeIn() == 11) {
            v3 = arg3.getConcreteType().cast(arg4).toString();
        }
        else if(arg3.getTypeIn() == 7) {
            arg2.append("\"");
            arg2.append(JsonUtils.escapeString(((String)arg4)));
            v3 = "\"";
        }
        else {
            goto label_17;
        }

        arg2.append(v3);
        return;
    label_17:
        arg2.append(arg4);
    }

    private static boolean zzb(String arg2, Object arg3) {
        if(arg3 == null) {
            if(Log.isLoggable("FastJsonResponse", 6)) {
                StringBuilder v1 = new StringBuilder(String.valueOf(arg2).length() + 58);
                v1.append("Output field (");
                v1.append(arg2);
                v1.append(") has a null value, but expected a primitive");
                Log.e("FastJsonResponse", v1.toString());
            }

            return 0;
        }

        return 1;
    }
}

