package com.google.android.gms.internal.vision;

import java.io.IOException;

public class zzcx extends IOException {
    private zzdx zzlr;

    public zzcx(String arg1) {
        super(arg1);
        this.zzlr = null;
    }

    static zzcx zzcb() {
        return new zzcx("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzcx zzcc() {
        return new zzcx("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzcx zzcd() {
        return new zzcx("Protocol message contained an invalid tag (zero).");
    }

    static zzcy zzce() {
        return new zzcy("Protocol message tag had invalid wire type.");
    }

    static zzcx zzcf() {
        return new zzcx("Failed to parse the message.");
    }

    static zzcx zzcg() {
        return new zzcx("Protocol message had invalid UTF-8.");
    }

    public final zzcx zzg(zzdx arg1) {
        this.zzlr = arg1;
        return this;
    }
}

