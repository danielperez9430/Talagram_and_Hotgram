package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse$FieldConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Class(creator="StringToIntConverterCreator") public final class StringToIntConverter extends AbstractSafeParcelable implements FieldConverter {
    @Class(creator="StringToIntConverterEntryCreator") public final class Entry extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @VersionField(id=1) private final int versionCode;
        @Field(id=2) final String zzwh;
        @Field(id=3) final int zzwi;

        static {
            Entry.CREATOR = new StringToIntConverterEntryCreator();
        }

        @Constructor Entry(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) int arg3) {
            super();
            this.versionCode = arg1;
            this.zzwh = arg2;
            this.zzwi = arg3;
        }

        Entry(String arg2, int arg3) {
            super();
            this.versionCode = 1;
            this.zzwh = arg2;
            this.zzwi = arg3;
        }

        public final void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeInt(arg4, 1, this.versionCode);
            SafeParcelWriter.writeString(arg4, 2, this.zzwh, false);
            SafeParcelWriter.writeInt(arg4, 3, this.zzwi);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    private final HashMap zzwe;
    private final SparseArray zzwf;
    @Field(getter="getSerializedMap", id=2) private final ArrayList zzwg;

    static {
        StringToIntConverter.CREATOR = new StringToIntConverterCreator();
    }

    @Constructor StringToIntConverter(@Param(id=1) int arg4, @Param(id=2) ArrayList arg5) {
        super();
        this.zzal = arg4;
        this.zzwe = new HashMap();
        this.zzwf = new SparseArray();
        this.zzwg = null;
        arg4 = arg5.size();
        int v0 = 0;
        while(v0 < arg4) {
            Object v1 = arg5.get(v0);
            ++v0;
            this.add(((Entry)v1).zzwh, ((Entry)v1).zzwi);
        }
    }

    public StringToIntConverter() {
        super();
        this.zzal = 1;
        this.zzwe = new HashMap();
        this.zzwf = new SparseArray();
        this.zzwg = null;
    }

    public final StringToIntConverter add(String arg3, int arg4) {
        this.zzwe.put(arg3, Integer.valueOf(arg4));
        this.zzwf.put(arg4, arg3);
        return this;
    }

    public final Integer convert(String arg2) {
        Object v2 = this.zzwe.get(arg2);
        if(v2 == null) {
            v2 = this.zzwe.get("gms_unknown");
        }

        return ((Integer)v2);
    }

    public final Object convert(Object arg1) {
        return this.convert(((String)arg1));
    }

    public final Object convertBack(Object arg1) {
        return this.convertBack(((Integer)arg1));
    }

    public final String convertBack(Integer arg3) {
        Object v3 = this.zzwf.get(arg3.intValue());
        if(v3 == null && (this.zzwe.containsKey("gms_unknown"))) {
            String v3_1 = "gms_unknown";
        }

        return ((String)v3);
    }

    public final int getTypeIn() {
        return 7;
    }

    public final int getTypeOut() {
        return 0;
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        arg7 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeInt(arg6, 1, this.zzal);
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.zzwe.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.add(new Entry(((String)v2), this.zzwe.get(v2).intValue()));
        }

        SafeParcelWriter.writeTypedList(arg6, 2, ((List)v0), false);
        SafeParcelWriter.finishObjectHeader(arg6, arg7);
    }
}

