package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R$string;
import javax.annotation.Nullable;

public class StringResourceValueReader {
    private final Resources zzvb;
    private final String zzvc;

    public StringResourceValueReader(Context arg2) {
        super();
        Preconditions.checkNotNull(arg2);
        this.zzvb = arg2.getResources();
        this.zzvc = this.zzvb.getResourcePackageName(string.common_google_play_services_unknown_issue);
    }

    @Nullable public String getString(String arg4) {
        int v4 = this.zzvb.getIdentifier(arg4, "string", this.zzvc);
        if(v4 == 0) {
            return null;
        }

        return this.zzvb.getString(v4);
    }
}

