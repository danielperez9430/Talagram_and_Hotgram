package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

@KeepName @Class(creator="DataHolderCreator", validate=true) public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public class Builder {
        private final String[] zznm;
        private final ArrayList zznu;
        private final String zznv;
        private final HashMap zznw;
        private boolean zznx;
        private String zzny;

        Builder(String[] arg1, String arg2, zza arg3) {
            this(arg1, arg2);
        }

        private Builder(String[] arg1, String arg2) {
            super();
            this.zznm = Preconditions.checkNotNull(arg1);
            this.zznu = new ArrayList();
            this.zznv = arg2;
            this.zznw = new HashMap();
            this.zznx = false;
            this.zzny = null;
        }

        public DataHolder build(int arg8, Bundle arg9, int arg10) {
            return new DataHolder(this, arg8, arg9, arg10, null);
        }

        public DataHolder build(int arg3) {
            return new DataHolder(this, arg3, null, null);
        }

        public DataHolder build(int arg8, Bundle arg9) {
            return new DataHolder(this, arg8, arg9, -1, null);
        }

        public boolean containsRowWithValue(String arg5, Object arg6) {
            int v0 = this.zznu.size();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                if(Objects.equal(this.zznu.get(v2).get(arg5), arg6)) {
                    return 1;
                }
            }

            return 0;
        }

        public Builder descendingSort(String arg2) {
            if(this.zzg(arg2)) {
                return this;
            }

            this.sort(arg2);
            Collections.reverse(this.zznu);
            return this;
        }

        public int getCount() {
            return this.zznu.size();
        }

        public void modifyUniqueRowValue(Object arg2, String arg3, Object arg4) {
            if(this.zznv == null) {
                return;
            }

            arg2 = this.zznw.get(arg2);
            if(arg2 == null) {
                return;
            }

            this.zznu.get(((Integer)arg2).intValue()).put(arg3, arg4);
        }

        public Builder removeRowsWithValue(String arg3, Object arg4) {
            int v0;
            for(v0 = this.zznu.size() - 1; v0 >= 0; --v0) {
                if(Objects.equal(this.zznu.get(v0).get(arg3), arg4)) {
                    this.zznu.remove(v0);
                }
            }

            return this;
        }

        public Builder sort(String arg6) {
            if(this.zzg(arg6)) {
                return this;
            }

            Collections.sort(this.zznu, new com.google.android.gms.common.data.DataHolder$zza(arg6));
            if(this.zznv != null) {
                this.zznw.clear();
                int v0 = 0;
                int v1 = this.zznu.size();
                while(v0 < v1) {
                    Object v2 = this.zznu.get(v0).get(this.zznv);
                    if(v2 != null) {
                        this.zznw.put(v2, Integer.valueOf(v0));
                    }

                    ++v0;
                }
            }

            this.zznx = true;
            this.zzny = arg6;
            return this;
        }

        public Builder withRow(ContentValues arg4) {
            Asserts.checkNotNull(arg4);
            HashMap v0 = new HashMap(arg4.size());
            Iterator v4 = arg4.valueSet().iterator();
            while(v4.hasNext()) {
                Object v1 = v4.next();
                v0.put(((Map$Entry)v1).getKey(), ((Map$Entry)v1).getValue());
            }

            return this.withRow(v0);
        }

        public Builder withRow(HashMap arg5) {
            int v0_1;
            Object v2;
            Asserts.checkNotNull(arg5);
            int v1 = -1;
            if(this.zznv != null) {
                Object v0 = arg5.get(this.zznv);
                if(v0 == null) {
                    goto label_4;
                }
                else {
                    v2 = this.zznw.get(v0);
                    if(v2 == null) {
                        this.zznw.put(v0, Integer.valueOf(this.zznu.size()));
                        goto label_4;
                    }
                    else {
                        goto label_19;
                    }
                }
            }
            else {
            label_4:
                v0_1 = -1;
                goto label_20;
            label_19:
                v0_1 = ((Integer)v2).intValue();
            }

        label_20:
            if(v0_1 == v1) {
                this.zznu.add(arg5);
            }
            else {
                this.zznu.remove(v0_1);
                this.zznu.add(v0_1, arg5);
            }

            this.zznx = false;
            return this;
        }

        static String[] zza(Builder arg0) {
            return arg0.zznm;
        }

        static ArrayList zzb(Builder arg0) {
            return arg0.zznu;
        }

        private final boolean zzg(String arg2) {
            Asserts.checkNotNull(arg2);
            if((this.zznx) && (arg2.equals(this.zzny))) {
                return 1;
            }

            return 0;
        }
    }

    public class DataHolderException extends RuntimeException {
        public DataHolderException(String arg1) {
            super(arg1);
        }
    }

    final class com.google.android.gms.common.data.DataHolder$zza implements Comparator {
        private final String zznz;

        com.google.android.gms.common.data.DataHolder$zza(String arg1) {
            super();
            this.zznz = Preconditions.checkNotNull(arg1);
        }

        public final int compare(Object arg3, Object arg4) {
            arg3 = Preconditions.checkNotNull(((HashMap)arg3).get(this.zznz));
            arg4 = Preconditions.checkNotNull(((HashMap)arg4).get(this.zznz));
            if(arg3.equals(arg4)) {
                return 0;
            }

            if((arg3 instanceof Boolean)) {
                return ((Boolean)arg3).compareTo(((Boolean)arg4));
            }

            if((arg3 instanceof Long)) {
                return ((Long)arg3).compareTo(((Long)arg4));
            }

            if((arg3 instanceof Integer)) {
                return ((Integer)arg3).compareTo(((Integer)arg4));
            }

            if((arg3 instanceof String)) {
                return ((String)arg3).compareTo(((String)arg4));
            }

            if((arg3 instanceof Double)) {
                return ((Double)arg3).compareTo(((Double)arg4));
            }

            if((arg3 instanceof Float)) {
                return ((Float)arg3).compareTo(((Float)arg4));
            }

            String v3 = String.valueOf(arg3);
            StringBuilder v1 = new StringBuilder(String.valueOf(v3).length() + 24);
            v1.append("Unknown type for lValue ");
            v1.append(v3);
            throw new IllegalArgumentException(v1.toString());
        }
    }

    public static final Parcelable$Creator CREATOR;
    private boolean mClosed;
    @VersionField(id=1000) private final int zzal;
    @Field(getter="getStatusCode", id=3) private final int zzam;
    @Field(getter="getColumns", id=1) private final String[] zznm;
    private Bundle zznn;
    @Field(getter="getWindows", id=2) private final CursorWindow[] zzno;
    @Field(getter="getMetadata", id=4) private final Bundle zznp;
    private int[] zznq;
    private int zznr;
    private boolean zzns;
    private static final Builder zznt;

    static {
        DataHolder.CREATOR = new DataHolderCreator();
        DataHolder.zznt = new zza(new String[0], null);
    }

    @Constructor DataHolder(@Param(id=1000) int arg2, @Param(id=1) String[] arg3, @Param(id=2) CursorWindow[] arg4, @Param(id=3) int arg5, @Param(id=4) Bundle arg6) {
        super();
        this.mClosed = false;
        this.zzns = true;
        this.zzal = arg2;
        this.zznm = arg3;
        this.zzno = arg4;
        this.zzam = arg5;
        this.zznp = arg6;
    }

    public DataHolder(Cursor arg2, int arg3, Bundle arg4) {
        this(new CursorWrapper(arg2), arg3, arg4);
    }

    public DataHolder(CursorWrapper arg2, int arg3, Bundle arg4) {
        this(arg2.getColumnNames(), DataHolder.zza(arg2), arg3, arg4);
    }

    private DataHolder(Builder arg3, int arg4, Bundle arg5) {
        this(Builder.zza(arg3), DataHolder.zza(arg3, -1), arg4, arg5);
    }

    public DataHolder(String[] arg2, CursorWindow[] arg3, int arg4, Bundle arg5) {
        super();
        this.mClosed = false;
        this.zzns = true;
        this.zzal = 1;
        this.zznm = Preconditions.checkNotNull(arg2);
        this.zzno = Preconditions.checkNotNull(arg3);
        this.zzam = arg4;
        this.zznp = arg5;
        this.validateContents();
    }

    private DataHolder(Builder arg2, int arg3, Bundle arg4, int arg5) {
        this(Builder.zza(arg2), DataHolder.zza(arg2, arg5), arg3, arg4);
    }

    DataHolder(Builder arg1, int arg2, Bundle arg3, int arg4, zza arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    DataHolder(Builder arg1, int arg2, Bundle arg3, zza arg4) {
        this(arg1, arg2, null);
    }

    public static Builder builder(String[] arg2, String arg3) {
        Preconditions.checkNotNull(arg3);
        return new Builder(arg2, arg3, null);
    }

    public static Builder builder(String[] arg2) {
        return new Builder(arg2, null, null);
    }

    public final void clearColumn(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        this.zzno[arg4].putNull(arg3, this.zznn.getInt(arg2));
    }

    public final void close() {
        __monitor_enter(this);
        try {
            if(!this.mClosed) {
                this.mClosed = true;
                int v0_1;
                for(v0_1 = 0; v0_1 < this.zzno.length; ++v0_1) {
                    this.zzno[v0_1].close();
                }
            }

            __monitor_exit(this);
            return;
        label_17:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_17;
        }

        throw v0;
    }

    public final void copyToBuffer(String arg2, int arg3, int arg4, CharArrayBuffer arg5) {
        this.zza(arg2, arg3);
        this.zzno[arg4].copyStringToBuffer(arg3, this.zznn.getInt(arg2), arg5);
    }

    public final void disableLeakDetection() {
        this.zzns = false;
    }

    public static DataHolder empty(int arg1) {
        return DataHolder.empty(arg1, null);
    }

    public static DataHolder empty(int arg2, Bundle arg3) {
        return new DataHolder(DataHolder.zznt, arg2, arg3);
    }

    protected final void finalize() {
        try {
            if((this.zzns) && this.zzno.length > 0 && !this.isClosed()) {
                this.close();
                String v1 = this.toString();
                StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 178);
                v3.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                v3.append(v1);
                v3.append(")");
                Log.e("DataBuffer", v3.toString());
            }
        }
        catch(Throwable v0) {
            super.finalize();
            throw v0;
        }

        super.finalize();
    }

    public final boolean getBoolean(String arg3, int arg4, int arg5) {
        this.zza(arg3, arg4);
        if(Long.valueOf(this.zzno[arg5].getLong(arg4, this.zznn.getInt(arg3))).longValue() == 1) {
            return 1;
        }

        return 0;
    }

    public final byte[] getByteArray(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        return this.zzno[arg4].getBlob(arg3, this.zznn.getInt(arg2));
    }

    public final int getCount() {
        return this.zznr;
    }

    public final double getDouble(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        return this.zzno[arg4].getDouble(arg3, this.zznn.getInt(arg2));
    }

    public final float getFloat(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        return this.zzno[arg4].getFloat(arg3, this.zznn.getInt(arg2));
    }

    public final int getInteger(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        return this.zzno[arg4].getInt(arg3, this.zznn.getInt(arg2));
    }

    public final long getLong(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        return this.zzno[arg4].getLong(arg3, this.zznn.getInt(arg2));
    }

    public final Bundle getMetadata() {
        return this.zznp;
    }

    public final int getStatusCode() {
        return this.zzam;
    }

    public final String getString(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        return this.zzno[arg4].getString(arg3, this.zznn.getInt(arg2));
    }

    public final int getWindowIndex(int arg3) {
        int v0 = 0;
        boolean v1 = arg3 < 0 || arg3 >= this.zznr ? false : true;
        Preconditions.checkState(v1);
        while(v0 < this.zznq.length) {
            if(arg3 < this.zznq[v0]) {
                --v0;
            }
            else {
                ++v0;
                continue;
            }

            break;
        }

        if(v0 == this.zznq.length) {
            --v0;
        }

        return v0;
    }

    public final boolean hasColumn(String arg2) {
        return this.zznn.containsKey(arg2);
    }

    public final boolean hasNull(String arg2, int arg3, int arg4) {
        this.zza(arg2, arg3);
        return this.zzno[arg4].isNull(arg3, this.zznn.getInt(arg2));
    }

    public final boolean isClosed() {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.mClosed;
        label_5:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_5;
        }

        throw v0;
    }

    public final void logCursorMetadataForDebugging() {
        Log.d("DataHolder", "*******************************************");
        int v1 = this.zzno.length;
        StringBuilder v2 = new StringBuilder(32);
        v2.append("num cursor windows : ");
        v2.append(v1);
        Log.d("DataHolder", v2.toString());
        v1 = this.zznr;
        int v3 = 46;
        v2 = new StringBuilder(v3);
        v2.append("total number of objects in holder: ");
        v2.append(v1);
        Log.d("DataHolder", v2.toString());
        v1 = this.zznq.length;
        v2 = new StringBuilder(42);
        v2.append("total mumber of windowOffsets: ");
        v2.append(v1);
        Log.d("DataHolder", v2.toString());
        int v0;
        for(v0 = 0; v0 < this.zznq.length; ++v0) {
            int v2_1 = this.zznq[v0];
            StringBuilder v5 = new StringBuilder(43);
            v5.append("offset for window ");
            v5.append(v0);
            v5.append(" : ");
            v5.append(v2_1);
            Log.d("DataHolder", v5.toString());
            v2_1 = this.zzno[v0].getNumRows();
            v5 = new StringBuilder(45);
            v5.append("num rows for window ");
            v5.append(v0);
            v5.append(" : ");
            v5.append(v2_1);
            Log.d("DataHolder", v5.toString());
            v2_1 = this.zzno[v0].getStartPosition();
            StringBuilder v4 = new StringBuilder(v3);
            v4.append("start pos for window ");
            v4.append(v0);
            v4.append(" : ");
            v4.append(v2_1);
            Log.d("DataHolder", v4.toString());
        }

        Log.d("DataHolder", "*******************************************");
    }

    public final Uri parseUri(String arg1, int arg2, int arg3) {
        arg1 = this.getString(arg1, arg2, arg3);
        if(arg1 == null) {
            return null;
        }

        return Uri.parse(arg1);
    }

    public final void replaceValue(String arg2, int arg3, int arg4, double arg5) {
        this.zza(arg2, arg3);
        this.zzno[arg4].putDouble(arg5, arg3, this.zznn.getInt(arg2));
    }

    public final void replaceValue(String arg2, int arg3, int arg4, long arg5) {
        this.zza(arg2, arg3);
        this.zzno[arg4].putLong(arg5, arg3, this.zznn.getInt(arg2));
    }

    public final void replaceValue(String arg2, int arg3, int arg4, String arg5) {
        this.zza(arg2, arg3);
        this.zzno[arg4].putString(arg5, arg3, this.zznn.getInt(arg2));
    }

    public final void replaceValue(String arg2, int arg3, int arg4, byte[] arg5) {
        this.zza(arg2, arg3);
        this.zzno[arg4].putBlob(arg5, arg3, this.zznn.getInt(arg2));
    }

    public final void validateContents() {
        this.zznn = new Bundle();
        int v0 = 0;
        int v1;
        for(v1 = 0; v1 < this.zznm.length; ++v1) {
            this.zznn.putInt(this.zznm[v1], v1);
        }

        this.zznq = new int[this.zzno.length];
        v1 = 0;
        while(v0 < this.zzno.length) {
            this.zznq[v0] = v1;
            v1 += this.zzno[v0].getNumRows() - (v1 - this.zzno[v0].getStartPosition());
            ++v0;
        }

        this.zznr = v1;
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeStringArray(arg6, 1, this.zznm, false);
        SafeParcelWriter.writeTypedArray(arg6, 2, this.zzno, arg7, false);
        SafeParcelWriter.writeInt(arg6, 3, this.getStatusCode());
        SafeParcelWriter.writeBundle(arg6, 4, this.getMetadata(), false);
        SafeParcelWriter.writeInt(arg6, 1000, this.zzal);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
        if((arg7 & 1) != 0) {
            this.close();
        }
    }

    private static CursorWindow[] zza(Builder arg12, int arg13) {
        long v9_2;
        Object v10;
        String v9_1;
        List v13;
        int v1 = 0;
        if(Builder.zza(arg12).length == 0) {
            return new CursorWindow[0];
        }

        if(arg13 < 0 || arg13 >= Builder.zzb(arg12).size()) {
            ArrayList v13_1 = Builder.zzb(arg12);
        }
        else {
            v13 = Builder.zzb(arg12).subList(0, arg13);
        }

        int v0 = v13.size();
        CursorWindow v2 = new CursorWindow(false);
        ArrayList v3 = new ArrayList();
        v3.add(v2);
        v2.setNumColumns(Builder.zza(arg12).length);
        CursorWindow v4 = v2;
        int v2_1 = 0;
        int v5 = 0;
        while(true) {
            if(v2_1 >= v0) {
                goto label_169;
            }

            try {
                if(!v4.allocRow()) {
                    StringBuilder v7 = new StringBuilder(72);
                    v7.append("Allocating additional cursor window for large data set (row ");
                    v7.append(v2_1);
                    v7.append(")");
                    Log.d("DataHolder", v7.toString());
                    v4 = new CursorWindow(false);
                    v4.setStartPosition(v2_1);
                    v4.setNumColumns(Builder.zza(arg12).length);
                    v3.add(v4);
                    if(!v4.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        v3.remove(v4);
                        return v3.toArray(new CursorWindow[v3.size()]);
                    }
                }

                Object v6 = v13.get(v2_1);
                int v8 = 0;
                boolean v9 = true;
                while(true) {
                    if(v8 < Builder.zza(arg12).length && (v9)) {
                        v9_1 = Builder.zza(arg12)[v8];
                        v10 = ((Map)v6).get(v9_1);
                        if(v10 == null) {
                            v9 = v4.putNull(v2_1, v8);
                        }
                        else if((v10 instanceof String)) {
                            v9 = v4.putString(((String)v10), v2_1, v8);
                        }
                        else {
                            if((v10 instanceof Long)) {
                                v9_2 = ((Long)v10).longValue();
                            }
                            else if((v10 instanceof Integer)) {
                                v9 = v4.putLong(((long)((Integer)v10).intValue()), v2_1, v8);
                                goto label_109;
                            }
                            else if(!(v10 instanceof Boolean)) {
                                goto label_95;
                            }
                            else if(((Boolean)v10).booleanValue()) {
                                v9_2 = 1;
                            }
                            else {
                                v9_2 = 0;
                            }

                            v9 = v4.putLong(v9_2, v2_1, v8);
                            goto label_109;
                        label_95:
                            if((v10 instanceof byte[])) {
                                v9 = v4.putBlob(((byte[])v10), v2_1, v8);
                                goto label_109;
                            }

                            if((v10 instanceof Double)) {
                                v9 = v4.putDouble(((Double)v10).doubleValue(), v2_1, v8);
                                goto label_109;
                            }

                            if(!(v10 instanceof Float)) {
                                break;
                            }

                            v9 = v4.putDouble(((double)((Float)v10).floatValue()), v2_1, v8);
                        }

                    label_109:
                        ++v8;
                        continue;
                    }

                    goto label_130;
                }

                String v13_2 = String.valueOf(v10);
                StringBuilder v2_2 = new StringBuilder(String.valueOf(v9_1).length() + 32 + String.valueOf(v13_2).length());
                v2_2.append("Unsupported object for column ");
                v2_2.append(v9_1);
                v2_2.append(": ");
                v2_2.append(v13_2);
                throw new IllegalArgumentException(v2_2.toString());
            label_130:
                if(v9) {
                    goto label_158;
                }
                else if(v5 == 0) {
                    StringBuilder v8_1 = new StringBuilder(74);
                    v8_1.append("Couldn\'t populate window data for row ");
                    v8_1.append(v2_1);
                    v8_1.append(" - allocating new window.");
                    Log.d("DataHolder", v8_1.toString());
                    v4.freeLastRow();
                    v4 = new CursorWindow(false);
                    v4.setStartPosition(v2_1);
                    v4.setNumColumns(Builder.zza(arg12).length);
                    v3.add(v4);
                    --v2_1;
                    v5 = 1;
                }
                else {
                    throw new DataHolderException("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                }

                goto label_159;
            }
            catch(RuntimeException v12) {
                break;
            }

        label_158:
            v5 = 0;
        label_159:
            ++v2_1;
        }

        arg13 = v3.size();
        while(v1 < arg13) {
            v3.get(v1).close();
            ++v1;
        }

        throw v12;
    label_169:
        return v3.toArray(new CursorWindow[v3.size()]);
    }

    private static CursorWindow[] zza(CursorWrapper arg6) {
        int v2_1;
        ArrayList v0 = new ArrayList();
        try {
            int v1 = arg6.getCount();
            CursorWindow v2 = arg6.getWindow();
            CursorWindow v3 = null;
            if(v2 == null || v2.getStartPosition() != 0) {
                v2_1 = 0;
            }
            else {
                v2.acquireReference();
                arg6.setWindow(v3);
                v0.add(v2);
                v2_1 = v2.getNumRows();
            }

            while(v2_1 < v1) {
                if(!arg6.moveToPosition(v2_1)) {
                    break;
                }

                CursorWindow v5 = arg6.getWindow();
                if(v5 != null) {
                    v5.acquireReference();
                    arg6.setWindow(v3);
                }
                else {
                    v5 = new CursorWindow(false);
                    v5.setStartPosition(v2_1);
                    arg6.fillWindow(v2_1, v5);
                }

                if(v5.getNumRows() == 0) {
                    break;
                }

                v0.add(v5);
                v2_1 = v5.getStartPosition() + v5.getNumRows();
            }
        }
        catch(Throwable v0_1) {
            goto label_40;
        }

        arg6.close();
        return v0.toArray(new CursorWindow[v0.size()]);
    label_40:
        arg6.close();
        throw v0_1;
    }

    private final void zza(String arg3, int arg4) {
        if(this.zznn != null) {
            if(!this.zznn.containsKey(arg3)) {
            }
            else if(!this.isClosed()) {
                if(arg4 >= 0 && arg4 < this.zznr) {
                    return;
                }

                throw new CursorIndexOutOfBoundsException(arg4, this.zznr);
            }
            else {
                throw new IllegalArgumentException("Buffer is closed.");
            }
        }

        String v0 = "No such column: ";
        arg3 = String.valueOf(arg3);
        arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
        throw new IllegalArgumentException(arg3);
    }
}

