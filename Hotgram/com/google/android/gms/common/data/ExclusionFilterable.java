package com.google.android.gms.common.data;

public interface ExclusionFilterable {
    void clearFilters();

    void filterOut(String arg1);

    void unfilter(String arg1);
}

