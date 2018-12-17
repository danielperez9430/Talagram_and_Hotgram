package com.google.android.gms.common.server.response;

import android.content.ContentValues;
import com.google.android.gms.common.util.VisibleForTesting;

public abstract class FastContentValuesJsonResponse extends FastJsonResponse {
    private final ContentValues zzwj;

    public FastContentValuesJsonResponse() {
        super();
        this.zzwj = new ContentValues();
    }

    @VisibleForTesting public FastContentValuesJsonResponse(ContentValues arg1) {
        super();
        this.zzwj = arg1;
    }

    protected Object getValueObject(String arg2) {
        return this.zzwj.get(arg2);
    }

    public ContentValues getValues() {
        return this.zzwj;
    }

    protected boolean isPrimitiveFieldSet(String arg2) {
        return this.zzwj.containsKey(arg2);
    }

    protected void setBoolean(String arg2, boolean arg3) {
        this.zzwj.put(arg2, Boolean.valueOf(arg3));
    }

    protected void setDecodedBytes(String arg2, byte[] arg3) {
        this.zzwj.put(arg2, arg3);
    }

    protected void setDouble(String arg2, double arg3) {
        this.zzwj.put(arg2, Double.valueOf(arg3));
    }

    protected void setFloat(String arg2, float arg3) {
        this.zzwj.put(arg2, Float.valueOf(arg3));
    }

    protected void setInteger(String arg2, int arg3) {
        this.zzwj.put(arg2, Integer.valueOf(arg3));
    }

    protected void setLong(String arg2, long arg3) {
        this.zzwj.put(arg2, Long.valueOf(arg3));
    }

    protected void setString(String arg2, String arg3) {
        this.zzwj.put(arg2, arg3);
    }
}

