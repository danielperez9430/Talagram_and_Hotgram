package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.util.Util;

public final class Descriptor {
    public final String id;
    public final String schemeIdUri;
    public final String value;

    public Descriptor(String arg1, String arg2, String arg3) {
        super();
        this.schemeIdUri = arg1;
        this.value = arg2;
        this.id = arg3;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((Descriptor)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!Util.areEqual(this.schemeIdUri, ((Descriptor)arg5).schemeIdUri) || !Util.areEqual(this.value, ((Descriptor)arg5).value) || !Util.areEqual(this.id, ((Descriptor)arg5).id)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.schemeIdUri != null ? this.schemeIdUri.hashCode() : 0;
        v0 *= 31;
        int v2 = this.value != null ? this.value.hashCode() : 0;
        v0 = (v0 + v2) * 31;
        if(this.id != null) {
            v1 = this.id.hashCode();
        }

        return v0 + v1;
    }
}

