package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;

public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    public FastSafeParcelableJsonResponse() {
        super();
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object arg6) {
        if(this == (((FastSafeParcelableJsonResponse)arg6))) {
            return 1;
        }

        if(!this.getClass().isInstance(arg6)) {
            return 0;
        }

        Iterator v1 = ((FastJsonResponse)this).getFieldMappings().values().iterator();
        do {
        label_11:
            if(!v1.hasNext()) {
                return 1;
            }

            Object v3 = v1.next();
            if(((FastJsonResponse)this).isFieldSet(((Field)v3))) {
                if((((FastJsonResponse)arg6).isFieldSet(((Field)v3))) && (((FastJsonResponse)this).getFieldValue(((Field)v3)).equals(((FastJsonResponse)arg6).getFieldValue(((Field)v3))))) {
                    goto label_11;
                }

                return 0;
            }
        }
        while(!((FastJsonResponse)arg6).isFieldSet(((Field)v3)));

        return 0;
    }

    @VisibleForTesting public Object getValueObject(String arg1) {
        return null;
    }

    public int hashCode() {
        Iterator v0 = ((FastJsonResponse)this).getFieldMappings().values().iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); v1 = v1 * 31 + ((FastJsonResponse)this).getFieldValue(((Field)v2)).hashCode()) {
            Object v2 = v0.next();
            if(!((FastJsonResponse)this).isFieldSet(((Field)v2))) {
                continue;
            }
        }

        return v1;
    }

    @VisibleForTesting public boolean isPrimitiveFieldSet(String arg1) {
        return 0;
    }

    public byte[] toByteArray() {
        Parcel v0 = Parcel.obtain();
        this.writeToParcel(v0, 0);
        byte[] v1 = v0.marshall();
        v0.recycle();
        return v1;
    }
}

