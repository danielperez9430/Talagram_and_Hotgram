package com.google.android.gms.common.data;

import android.content.Context;

public interface TextFilterable {
    public interface StringFilter {
        boolean matches(String arg1, String arg2);
    }

    public static final StringFilter CONTAINS;
    public static final StringFilter STARTS_WITH;
    public static final StringFilter WORD_STARTS_WITH;

    static {
        TextFilterable.CONTAINS = new zzc();
        TextFilterable.STARTS_WITH = new zzd();
        TextFilterable.WORD_STARTS_WITH = new zze();
    }

    void setFilterTerm(Context arg1, StringFilter arg2, String arg3);

    void setFilterTerm(Context arg1, String arg2);
}

