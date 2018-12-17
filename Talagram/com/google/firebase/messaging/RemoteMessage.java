package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Iterator;
import java.util.Map;

@Class(creator="RemoteMessageCreator") @Reserved(value={1}) public final class RemoteMessage extends AbstractSafeParcelable {
    public class a {
        private final String a;
        private final String b;
        private final String[] c;
        private final String d;
        private final String e;
        private final String[] f;
        private final String g;
        private final String h;
        private final String i;
        private final String j;
        private final String k;
        private final Uri l;

        a(Bundle arg1, f arg2) {
            this(arg1);
        }

        private a(Bundle arg2) {
            super();
            this.a = e.a(arg2, "gcm.n.title");
            this.b = e.b(arg2, "gcm.n.title");
            this.c = a.a(arg2, "gcm.n.title");
            this.d = e.a(arg2, "gcm.n.body");
            this.e = e.b(arg2, "gcm.n.body");
            this.f = a.a(arg2, "gcm.n.body");
            this.g = e.a(arg2, "gcm.n.icon");
            this.h = e.d(arg2);
            this.i = e.a(arg2, "gcm.n.tag");
            this.j = e.a(arg2, "gcm.n.color");
            this.k = e.a(arg2, "gcm.n.click_action");
            this.l = e.b(arg2);
        }

        private static String[] a(Bundle arg2, String arg3) {
            Object[] v2 = e.c(arg2, arg3);
            if(v2 == null) {
                return null;
            }

            String[] v3 = new String[v2.length];
            int v0;
            for(v0 = 0; v0 < v2.length; ++v0) {
                v3[v0] = String.valueOf(v2[v0]);
            }

            return v3;
        }

        public String a() {
            return this.d;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) Bundle a;
    private Map b;
    private a c;

    static {
        RemoteMessage.CREATOR = new g();
    }

    @Constructor public RemoteMessage(@Param(id=2) Bundle arg1) {
        super();
        this.a = arg1;
    }

    public final String a() {
        return this.a.getString("from");
    }

    public final Map b() {
        if(this.b == null) {
            Bundle v0 = this.a;
            android.support.v4.f.a v1 = new android.support.v4.f.a();
            Iterator v2 = v0.keySet().iterator();
            while(v2.hasNext()) {
                Object v3 = v2.next();
                Object v4 = v0.get(((String)v3));
                if(!(v4 instanceof String)) {
                    continue;
                }

                if(((String)v3).startsWith("google.")) {
                    continue;
                }

                if(((String)v3).startsWith("gcm.")) {
                    continue;
                }

                if(((String)v3).equals("from")) {
                    continue;
                }

                if(((String)v3).equals("message_type")) {
                    continue;
                }

                if(((String)v3).equals("collapse_key")) {
                    continue;
                }

                v1.put(v3, v4);
            }

            this.b = ((Map)v1);
        }

        return this.b;
    }

    public final long c() {
        Object v0 = this.a.get("google.sent_time");
        if((v0 instanceof Long)) {
            return ((Long)v0).longValue();
        }

        if((v0 instanceof String)) {
            try {
                return Long.parseLong(v0);
            }
            catch(NumberFormatException ) {
                String v0_1 = String.valueOf(v0);
                StringBuilder v3 = new StringBuilder(String.valueOf(v0_1).length() + 19);
                v3.append("Invalid sent time: ");
                v3.append(v0_1);
                Log.w("FirebaseMessaging", v3.toString());
            }
        }

        return 0;
    }

    public final a d() {
        if(this.c == null && (e.a(this.a))) {
            this.c = new a(this.a, null);
        }

        return this.c;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeBundle(arg4, 2, this.a, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

