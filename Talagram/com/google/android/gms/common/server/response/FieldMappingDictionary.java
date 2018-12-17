package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Class(creator="FieldMappingDictionaryCreator") public class FieldMappingDictionary extends AbstractSafeParcelable {
    @Class(creator="FieldMappingDictionaryEntryCreator") public class Entry extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=2) final String className;
        @VersionField(id=1) private final int versionCode;
        @Field(id=3) final ArrayList zzxn;

        static {
            Entry.CREATOR = new FieldMappingDictionaryEntryCreator();
        }

        @Constructor Entry(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) ArrayList arg3) {
            super();
            this.versionCode = arg1;
            this.className = arg2;
            this.zzxn = arg3;
        }

        Entry(String arg5, Map arg6) {
            ArrayList v5;
            super();
            this.versionCode = 1;
            this.className = arg5;
            if(arg6 == null) {
                v5 = null;
            }
            else {
                v5 = new ArrayList();
                Iterator v0 = arg6.keySet().iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    v5.add(new FieldMapPair(((String)v1), arg6.get(v1)));
                }
            }

            this.zzxn = v5;
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeInt(arg4, 1, this.versionCode);
            SafeParcelWriter.writeString(arg4, 2, this.className, false);
            SafeParcelWriter.writeTypedList(arg4, 3, this.zzxn, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="FieldMapPairCreator") public class FieldMapPair extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @VersionField(id=1) private final int versionCode;
        @Field(id=2) final String zzxo;
        @Field(id=3) final com.google.android.gms.common.server.response.FastJsonResponse$Field zzxp;

        static {
            FieldMapPair.CREATOR = new FieldMapPairCreator();
        }

        @Constructor FieldMapPair(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) com.google.android.gms.common.server.response.FastJsonResponse$Field arg3) {
            super();
            this.versionCode = arg1;
            this.zzxo = arg2;
            this.zzxp = arg3;
        }

        FieldMapPair(String arg2, com.google.android.gms.common.server.response.FastJsonResponse$Field arg3) {
            super();
            this.versionCode = 1;
            this.zzxo = arg2;
            this.zzxp = arg3;
        }

        public void writeToParcel(Parcel arg5, int arg6) {
            int v0 = SafeParcelWriter.beginObjectHeader(arg5);
            SafeParcelWriter.writeInt(arg5, 1, this.versionCode);
            SafeParcelWriter.writeString(arg5, 2, this.zzxo, false);
            SafeParcelWriter.writeParcelable(arg5, 3, this.zzxp, arg6, false);
            SafeParcelWriter.finishObjectHeader(arg5, v0);
        }
    }

    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    private final HashMap zzxk;
    @Field(getter="getSerializedDictionary", id=2) private final ArrayList zzxl;
    @Field(getter="getRootClassName", id=3) private final String zzxm;

    static {
        FieldMappingDictionary.CREATOR = new FieldMappingDictionaryCreator();
    }

    @Constructor FieldMappingDictionary(@Param(id=1) int arg11, @Param(id=2) ArrayList arg12, @Param(id=3) String arg13) {
        super();
        this.zzal = arg11;
        this.zzxl = null;
        HashMap v11 = new HashMap();
        int v0 = arg12.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = arg12.get(v2);
            String v4 = ((Entry)v3).className;
            HashMap v5 = new HashMap();
            int v6 = ((Entry)v3).zzxn.size();
            int v7;
            for(v7 = 0; v7 < v6; ++v7) {
                Object v8 = ((Entry)v3).zzxn.get(v7);
                v5.put(((FieldMapPair)v8).zzxo, ((FieldMapPair)v8).zzxp);
            }

            v11.put(v4, v5);
        }

        this.zzxk = v11;
        this.zzxm = Preconditions.checkNotNull(arg13);
        this.linkFields();
    }

    public FieldMappingDictionary(java.lang.Class arg2) {
        super();
        this.zzal = 1;
        this.zzxl = null;
        this.zzxk = new HashMap();
        this.zzxm = arg2.getCanonicalName();
    }

    public void copyInternalFieldMappings() {
        Iterator v0 = this.zzxk.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v2 = this.zzxk.get(v1);
            HashMap v3 = new HashMap();
            Iterator v4 = ((Map)v2).keySet().iterator();
            while(v4.hasNext()) {
                Object v5 = v4.next();
                v3.put(v5, ((Map)v2).get(v5).copyForDictionary());
            }

            this.zzxk.put(v1, v3);
        }
    }

    public Map getFieldMapping(String arg2) {
        return this.zzxk.get(arg2);
    }

    @VisibleForTesting public Map getFieldMapping(java.lang.Class arg2) {
        return this.zzxk.get(arg2.getCanonicalName());
    }

    public String getRootClassName() {
        return this.zzxm;
    }

    public boolean hasFieldMappingForClass(java.lang.Class arg2) {
        return this.zzxk.containsKey(arg2.getCanonicalName());
    }

    public void linkFields() {
        Iterator v0 = this.zzxk.keySet().iterator();
    label_3:
        if(v0.hasNext()) {
            Object v1 = this.zzxk.get(v0.next());
            Iterator v2 = ((Map)v1).keySet().iterator();
            while(true) {
                if(!v2.hasNext()) {
                    goto label_3;
                }

                ((Map)v1).get(v2.next()).setFieldMappingDictionary(this);
            }
        }
    }

    public void put(java.lang.Class arg2, Map arg3) {
        this.zzxk.put(arg2.getCanonicalName(), arg3);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        Iterator v1 = this.zzxk.keySet().iterator();
    label_5:
        if(v1.hasNext()) {
            Object v2 = v1.next();
            v0.append(((String)v2));
            v0.append(":\n");
            v2 = this.zzxk.get(v2);
            Iterator v3 = ((Map)v2).keySet().iterator();
            while(true) {
                if(!v3.hasNext()) {
                    goto label_5;
                }

                Object v4 = v3.next();
                v0.append("  ");
                v0.append(((String)v4));
                v0.append(": ");
                v0.append(((Map)v2).get(v4));
            }
        }

        return v0.toString();
    }

    public void writeToParcel(Parcel arg6, int arg7) {
        arg7 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeInt(arg6, 1, this.zzal);
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.zzxk.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.add(new Entry(((String)v2), this.zzxk.get(v2)));
        }

        SafeParcelWriter.writeTypedList(arg6, 2, ((List)v0), false);
        SafeParcelWriter.writeString(arg6, 3, this.getRootClassName(), false);
        SafeParcelWriter.finishObjectHeader(arg6, arg7);
    }
}

