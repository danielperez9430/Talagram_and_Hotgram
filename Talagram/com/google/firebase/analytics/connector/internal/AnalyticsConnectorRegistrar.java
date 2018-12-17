package com.google.firebase.analytics.connector.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.b.d;
import com.google.firebase.b;
import com.google.firebase.components.a;
import com.google.firebase.components.e;
import com.google.firebase.components.f;
import java.util.Collections;
import java.util.List;

@Keep @KeepForSdk public class AnalyticsConnectorRegistrar implements e {
    public AnalyticsConnectorRegistrar() {
        super();
    }

    @SuppressLint(value={"MissingPermission"}) @Keep @KeepForSdk public List getComponents() {
        return Collections.singletonList(a.a(com.google.firebase.analytics.connector.a.class).a(f.a(b.class)).a(f.a(Context.class)).a(f.a(d.class)).a(com.google.firebase.analytics.connector.internal.a.a).b().c());
    }
}

