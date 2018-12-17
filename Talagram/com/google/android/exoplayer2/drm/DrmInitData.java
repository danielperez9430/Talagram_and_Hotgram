package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class DrmInitData implements Parcelable, Comparator {
    final class com.google.android.exoplayer2.drm.DrmInitData$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.drm.DrmInitData$1() {
            super();
        }

        public DrmInitData createFromParcel(Parcel arg2) {
            return new DrmInitData(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public DrmInitData[] newArray(int arg1) {
            return new DrmInitData[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public final class SchemeData implements Parcelable {
        final class com.google.android.exoplayer2.drm.DrmInitData$SchemeData$1 implements Parcelable$Creator {
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData$1() {
                super();
            }

            public SchemeData createFromParcel(Parcel arg2) {
                return new SchemeData(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public SchemeData[] newArray(int arg1) {
                return new SchemeData[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        public final byte[] data;
        private int hashCode;
        public final String licenseServerUrl;
        public final String mimeType;
        public final boolean requiresSecureDecryption;
        private final UUID uuid;

        static {
            SchemeData.CREATOR = new com.google.android.exoplayer2.drm.DrmInitData$SchemeData$1();
        }

        SchemeData(Parcel arg6) {
            super();
            this.uuid = new UUID(arg6.readLong(), arg6.readLong());
            this.licenseServerUrl = arg6.readString();
            this.mimeType = arg6.readString();
            this.data = arg6.createByteArray();
            boolean v6 = arg6.readByte() != 0 ? true : false;
            this.requiresSecureDecryption = v6;
        }

        public SchemeData(UUID arg1, String arg2, String arg3, byte[] arg4, boolean arg5) {
            super();
            this.uuid = Assertions.checkNotNull(arg1);
            this.licenseServerUrl = arg2;
            this.mimeType = Assertions.checkNotNull(arg3);
            this.data = arg4;
            this.requiresSecureDecryption = arg5;
        }

        public SchemeData(UUID arg2, String arg3, byte[] arg4) {
            this(arg2, arg3, arg4, false);
        }

        public SchemeData(UUID arg7, String arg8, byte[] arg9, boolean arg10) {
            this(arg7, null, arg8, arg9, arg10);
        }

        static UUID access$000(SchemeData arg0) {
            return arg0.uuid;
        }

        public boolean canReplace(SchemeData arg2) {
            boolean v2 = !this.hasData() || (arg2.hasData()) || !this.matches(arg2.uuid) ? false : true;
            return v2;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object arg5) {
            if(!(arg5 instanceof SchemeData)) {
                return 0;
            }

            boolean v0 = true;
            if((((SchemeData)arg5)) == this) {
                return 1;
            }

            if(!Util.areEqual(this.licenseServerUrl, ((SchemeData)arg5).licenseServerUrl) || !Util.areEqual(this.mimeType, ((SchemeData)arg5).mimeType) || !Util.areEqual(this.uuid, ((SchemeData)arg5).uuid) || !Arrays.equals(this.data, ((SchemeData)arg5).data)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        public boolean hasData() {
            boolean v0 = this.data != null ? true : false;
            return v0;
        }

        public int hashCode() {
            if(this.hashCode == 0) {
                int v0 = this.uuid.hashCode() * 31;
                int v1 = this.licenseServerUrl == null ? 0 : this.licenseServerUrl.hashCode();
                this.hashCode = ((v0 + v1) * 31 + this.mimeType.hashCode()) * 31 + Arrays.hashCode(this.data);
            }

            return this.hashCode;
        }

        public boolean matches(UUID arg3) {
            boolean v3 = (C.UUID_NIL.equals(this.uuid)) || (arg3.equals(this.uuid)) ? true : false;
            return v3;
        }

        public void writeToParcel(Parcel arg3, int arg4) {
            arg3.writeLong(this.uuid.getMostSignificantBits());
            arg3.writeLong(this.uuid.getLeastSignificantBits());
            arg3.writeString(this.licenseServerUrl);
            arg3.writeString(this.mimeType);
            arg3.writeByteArray(this.data);
            arg3.writeByte(((byte)this.requiresSecureDecryption));
        }
    }

    public static final Parcelable$Creator CREATOR;
    private int hashCode;
    public final int schemeDataCount;
    private final SchemeData[] schemeDatas;
    public final String schemeType;

    static {
        DrmInitData.CREATOR = new com.google.android.exoplayer2.drm.DrmInitData$1();
    }

    DrmInitData(Parcel arg2) {
        super();
        this.schemeType = arg2.readString();
        this.schemeDatas = arg2.createTypedArray(SchemeData.CREATOR);
        this.schemeDataCount = this.schemeDatas.length;
    }

    public DrmInitData(String arg2, List arg3) {
        this(arg2, false, arg3.toArray(new SchemeData[arg3.size()]));
    }

    private DrmInitData(String arg1, boolean arg2, SchemeData[] arg3) {
        // Method was not decompiled
    }

    public DrmInitData(String arg2, SchemeData[] arg3) {
        this(arg2, true, arg3);
    }

    public DrmInitData(List arg3) {
        this(null, false, arg3.toArray(new SchemeData[arg3.size()]));
    }

    public DrmInitData(SchemeData[] arg2) {
        this(null, arg2);
    }

    public int compare(SchemeData arg3, SchemeData arg4) {
        int v3;
        if(!C.UUID_NIL.equals(SchemeData.access$000(arg3))) {
            v3 = SchemeData.access$000(arg3).compareTo(SchemeData.access$000(arg4));
        }
        else if(C.UUID_NIL.equals(SchemeData.access$000(arg4))) {
            v3 = 0;
        }
        else {
            v3 = 1;
        }

        return v3;
    }

    public int compare(Object arg1, Object arg2) {
        return this.compare(((SchemeData)arg1), ((SchemeData)arg2));
    }

    private static boolean containsSchemeDataWithUuid(ArrayList arg3, int arg4, UUID arg5) {
        int v1;
        for(v1 = 0; v1 < arg4; ++v1) {
            if(SchemeData.access$000(arg3.get(v1)).equals(arg5)) {
                return 1;
            }
        }

        return 0;
    }

    public DrmInitData copyWithSchemeType(String arg4) {
        if(Util.areEqual(this.schemeType, arg4)) {
            return this;
        }

        return new DrmInitData(arg4, false, this.schemeDatas);
    }

    public static DrmInitData createSessionCreationData(DrmInitData arg8, DrmInitData arg9) {
        int v4;
        String v3;
        ArrayList v0 = new ArrayList();
        int v1 = 0;
        DrmInitData v2 = null;
        if(arg8 != null) {
            v3 = arg8.schemeType;
            SchemeData[] v8 = arg8.schemeDatas;
            v4 = v8.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                SchemeData v6 = v8[v5];
                if(v6.hasData()) {
                    v0.add(v6);
                }
            }
        }
        else {
            v3 = ((String)v2);
        }

        if(arg9 != null) {
            if(v3 == null) {
                v3 = arg9.schemeType;
            }

            int v8_1 = v0.size();
            SchemeData[] v9 = arg9.schemeDatas;
            v4 = v9.length;
            while(v1 < v4) {
                SchemeData v5_1 = v9[v1];
                if((v5_1.hasData()) && !DrmInitData.containsSchemeDataWithUuid(v0, v8_1, SchemeData.access$000(v5_1))) {
                    v0.add(v5_1);
                }

                ++v1;
            }
        }

        if(v0.isEmpty()) {
        }
        else {
            v2 = new DrmInitData(v3, ((List)v0));
        }

        return v2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((DrmInitData)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!Util.areEqual(this.schemeType, ((DrmInitData)arg5).schemeType) || !Arrays.equals(this.schemeDatas, ((DrmInitData)arg5).schemeDatas)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public SchemeData get(int arg2) {
        return this.schemeDatas[arg2];
    }

    @Deprecated public SchemeData get(UUID arg6) {
        SchemeData[] v0 = this.schemeDatas;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            SchemeData v3 = v0[v2];
            if(v3.matches(arg6)) {
                return v3;
            }
        }

        return null;
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            int v0 = this.schemeType == null ? 0 : this.schemeType.hashCode();
            this.hashCode = v0 * 31 + Arrays.hashCode(this.schemeDatas);
        }

        return this.hashCode;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeString(this.schemeType);
        arg2.writeTypedArray(this.schemeDatas, 0);
    }
}

