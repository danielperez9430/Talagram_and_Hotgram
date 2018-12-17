package com.google.android.exoplayer2.source.dash.manifest;

public final class UtcTimingElement {
    public final String schemeIdUri;
    public final String value;

    public UtcTimingElement(String arg1, String arg2) {
        super();
        this.schemeIdUri = arg1;
        this.value = arg2;
    }

    public String toString() {
        return this.schemeIdUri + ", " + this.value;
    }
}

