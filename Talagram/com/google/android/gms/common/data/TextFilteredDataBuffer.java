package com.google.android.gms.common.data;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Locale;

public final class TextFilteredDataBuffer extends FilteredDataBuffer implements TextFilterable {
    private final ArrayList zzob;
    private AbstractDataBuffer zzoc;
    private final String zzoo;
    private String zzop;
    private StringFilter zzoq;
    private Locale zzor;

    public TextFilteredDataBuffer(AbstractDataBuffer arg2, String arg3) {
        super(((DataBuffer)arg2));
        this.zzob = new ArrayList();
        this.zzoc = arg2;
        this.zzoo = arg3;
    }

    public final int computeRealPosition(int arg4) {
        if(TextUtils.isEmpty(this.zzop)) {
            return arg4;
        }

        if(arg4 >= 0 && arg4 < this.zzob.size()) {
            return this.zzob.get(arg4).intValue();
        }

        StringBuilder v2 = new StringBuilder(53);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(v2.toString());
    }

    public final int getCount() {
        if(TextUtils.isEmpty(this.zzop)) {
            return this.mDataBuffer.getCount();
        }

        return this.zzob.size();
    }

    public final void setFilterTerm(Context arg6, StringFilter arg7, String arg8) {
        Preconditions.checkNotNull(arg7);
        this.zzop = arg8;
        this.zzoq = arg7;
        if(!TextUtils.isEmpty(this.zzop)) {
            this.zzor = arg6.getResources().getConfiguration().locale;
            this.zzop = this.zzh(this.zzop);
            this.zzob.clear();
            DataHolder v6 = this.zzoc.mDataHolder;
            String v7 = this.zzoo;
            boolean v8 = this.zzoc instanceof EntityBuffer;
            int v0 = 0;
            int v1 = this.zzoc.getCount();
            while(v0 < v1) {
                int v2 = v8 ? this.zzoc.zzi(v0) : v0;
                String v2_1 = v6.getString(v7, v2, v6.getWindowIndex(v2));
                if(!TextUtils.isEmpty(((CharSequence)v2_1)) && (this.zzoq.matches(this.zzh(v2_1), this.zzop))) {
                    this.zzob.add(Integer.valueOf(v0));
                }

                ++v0;
            }

            return;
        }

        this.zzob.clear();
    }

    @VisibleForTesting public final void setFilterTerm(Context arg2, String arg3) {
        this.setFilterTerm(arg2, TextFilteredDataBuffer.CONTAINS, arg3);
    }

    private final String zzh(String arg5) {
        arg5 = arg5.toLowerCase(this.zzor);
        StringBuilder v0 = new StringBuilder();
        int v1 = arg5.length();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(!Character.isIdentifierIgnorable(arg5.charAt(v2))) {
                v0.append(arg5.charAt(v2));
            }
        }

        return v0.toString();
    }
}

