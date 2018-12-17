package com.google.android.gms.common.server.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastMapJsonResponse extends FastJsonResponse {
    private final HashMap zzwp;

    public FastMapJsonResponse() {
        super();
        this.zzwp = new HashMap();
    }

    public Object getValueObject(String arg2) {
        return this.zzwp.get(arg2);
    }

    public HashMap getValues() {
        return this.zzwp;
    }

    protected boolean isPrimitiveFieldSet(String arg2) {
        return this.zzwp.containsKey(arg2);
    }

    public void setBigDecimal(String arg2, BigDecimal arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setBigDecimals(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setBigInteger(String arg2, BigInteger arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setBigIntegers(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setBoolean(String arg2, boolean arg3) {
        this.zzwp.put(arg2, Boolean.valueOf(arg3));
    }

    public void setBooleans(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setDecodedBytes(String arg2, byte[] arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setDouble(String arg2, double arg3) {
        this.zzwp.put(arg2, Double.valueOf(arg3));
    }

    public void setDoubles(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }

    protected void setFloat(String arg2, float arg3) {
        this.zzwp.put(arg2, Float.valueOf(arg3));
    }

    protected void setFloats(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setInteger(String arg2, int arg3) {
        this.zzwp.put(arg2, Integer.valueOf(arg3));
    }

    public void setIntegers(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setLong(String arg2, long arg3) {
        this.zzwp.put(arg2, Long.valueOf(arg3));
    }

    public void setLongs(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setString(String arg2, String arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setStringMap(String arg2, Map arg3) {
        this.zzwp.put(arg2, arg3);
    }

    public void setStrings(String arg2, ArrayList arg3) {
        this.zzwp.put(arg2, arg3);
    }
}

