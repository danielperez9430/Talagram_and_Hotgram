package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.metadata.emsg.EventMessage;

public final class EventStream {
    public final EventMessage[] events;
    public final long[] presentationTimesUs;
    public final String schemeIdUri;
    public final long timescale;
    public final String value;

    public EventStream(String arg1, String arg2, long arg3, long[] arg5, EventMessage[] arg6) {
        super();
        this.schemeIdUri = arg1;
        this.value = arg2;
        this.timescale = arg3;
        this.presentationTimesUs = arg5;
        this.events = arg6;
    }

    public String id() {
        return this.schemeIdUri + "/" + this.value;
    }
}

