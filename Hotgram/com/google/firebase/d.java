package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

public final class d {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;

    private d(String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9) {
        super();
        Preconditions.checkState(Strings.isEmptyOrWhitespace(arg3) ^ 1, "ApplicationId must be set.");
        this.b = arg3;
        this.a = arg4;
        this.c = arg5;
        this.d = arg6;
        this.e = arg7;
        this.f = arg8;
        this.g = arg9;
    }

    public static d a(Context arg9) {
        StringResourceValueReader v0 = new StringResourceValueReader(arg9);
        String v2 = v0.getString("google_app_id");
        if(TextUtils.isEmpty(((CharSequence)v2))) {
            return null;
        }

        return new d(v2, v0.getString("google_api_key"), v0.getString("firebase_database_url"), v0.getString("ga_trackingId"), v0.getString("gcm_defaultSenderId"), v0.getString("google_storage_bucket"), v0.getString("project_id"));
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.e;
    }

    public final boolean equals(Object arg4) {
        if(!(arg4 instanceof d)) {
            return 0;
        }

        if((Objects.equal(this.b, ((d)arg4).b)) && (Objects.equal(this.a, ((d)arg4).a)) && (Objects.equal(this.c, ((d)arg4).c)) && (Objects.equal(this.d, ((d)arg4).d)) && (Objects.equal(this.e, ((d)arg4).e)) && (Objects.equal(this.f, ((d)arg4).f)) && (Objects.equal(this.g, ((d)arg4).g))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.b, this.a, this.c, this.d, this.e, this.f, this.g});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.b).add("apiKey", this.a).add("databaseUrl", this.c).add("gcmSenderId", this.e).add("storageBucket", this.f).add("projectId", this.g).toString();
    }
}

