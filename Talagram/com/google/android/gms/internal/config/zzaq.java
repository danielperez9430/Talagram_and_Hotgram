package com.google.android.gms.internal.config;

import com.google.firebase.e.a;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

public final class zzaq {
    public static final Charset UTF_8;
    private final byte[] zzbb;
    private final int zzbc;
    public static final Pattern zzl;
    public static final Pattern zzm;

    static {
        zzaq.UTF_8 = Charset.forName("UTF-8");
        zzaq.zzl = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
        zzaq.zzm = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
    }

    public zzaq(byte[] arg1, int arg2) {
        super();
        this.zzbb = arg1;
        this.zzbc = arg2;
    }

    public final boolean asBoolean() {
        if(this.zzbc == 0) {
            return 0;
        }

        String v0 = this.asString().trim();
        if(zzaq.zzl.matcher(((CharSequence)v0)).matches()) {
            return 1;
        }

        if(zzaq.zzm.matcher(((CharSequence)v0)).matches()) {
            return 0;
        }

        throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", v0, "boolean"));
    }

    public final byte[] asByteArray() {
        if(this.zzbc == 0) {
            return a.a;
        }

        return this.zzbb;
    }

    public final double asDouble() {
        if(this.zzbc == 0) {
            return 0;
        }

        String v0 = this.asString().trim();
        try {
            return Double.valueOf(v0).doubleValue();
        }
        catch(NumberFormatException v1) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", v0, "double"), ((Throwable)v1));
        }
    }

    public final long asLong() {
        if(this.zzbc == 0) {
            return 0;
        }

        String v0 = this.asString().trim();
        try {
            return Long.valueOf(v0).longValue();
        }
        catch(NumberFormatException v1) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", v0, "long"), ((Throwable)v1));
        }
    }

    public final String asString() {
        if(this.zzbc == 0) {
            return "";
        }

        if(this.zzbb != null) {
            return new String(this.zzbb, zzaq.UTF_8);
        }

        throw new IllegalArgumentException("Value is null, and cannot be converted to the desired type.");
    }

    public final int getSource() {
        return this.zzbc;
    }
}

