package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Collections;
import java.util.List;

public class Period {
    public final List adaptationSets;
    public final List eventStreams;
    public final String id;
    public final long startMs;

    public Period(String arg7, long arg8, List arg10) {
        this(arg7, arg8, arg10, Collections.emptyList());
    }

    public Period(String arg1, long arg2, List arg4, List arg5) {
        super();
        this.id = arg1;
        this.startMs = arg2;
        this.adaptationSets = Collections.unmodifiableList(arg4);
        this.eventStreams = Collections.unmodifiableList(arg5);
    }

    public int getAdaptationSetIndex(int arg4) {
        int v0 = this.adaptationSets.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(this.adaptationSets.get(v1).type == arg4) {
                return v1;
            }
        }

        return -1;
    }
}

