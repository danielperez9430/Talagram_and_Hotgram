package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;

public final class TransientDataHolder {
    private String zzos;
    private String zzot;
    private Builder zzou;

    public TransientDataHolder(String[] arg2) {
        this(arg2, null, null, null);
    }

    public TransientDataHolder(String[] arg1, String arg2, String arg3, String arg4) {
        super();
        this.zzos = arg3;
        this.zzot = arg4;
        Builder v1 = arg2 != null ? DataHolder.builder(arg1, arg2) : DataHolder.builder(arg1);
        this.zzou = v1;
    }

    public final void addRow(ContentValues arg2) {
        this.zzou.withRow(arg2);
    }

    @VisibleForTesting public final DataHolder build(int arg3) {
        return this.build(arg3, new Bundle(), -1);
    }

    public final DataHolder build(int arg3, Bundle arg4, int arg5) {
        arg4.putString("next_page_token", this.zzot);
        arg4.putString("prev_page_token", this.zzos);
        return this.zzou.build(arg3, arg4, arg5);
    }

    public final boolean containsRowWithValue(String arg2, Object arg3) {
        return this.zzou.containsRowWithValue(arg2, arg3);
    }

    public final int getCount() {
        return this.zzou.getCount();
    }

    public final String getNextToken() {
        return this.zzot;
    }

    public final String getPrevToken() {
        return this.zzos;
    }

    public final void modifyUniqueRowValue(Object arg2, String arg3, Object arg4) {
        this.zzou.modifyUniqueRowValue(arg2, arg3, arg4);
    }

    @VisibleForTesting public final void removeRows(String arg2, Object arg3) {
        this.zzou.removeRowsWithValue(arg2, arg3);
    }

    public final void setNextToken(String arg1) {
        this.zzot = arg1;
    }

    public final void setPrevToken(String arg1) {
        this.zzos = arg1;
    }

    public final void sortData(String arg2) {
        this.zzou.sort(arg2);
    }

    public final void sortDataDescending(String arg2) {
        this.zzou.descendingSort(arg2);
    }
}

