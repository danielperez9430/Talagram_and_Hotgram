package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Collections;
import java.util.List;

public class AdaptationSet {
    public static final int ID_UNSET = -1;
    public final List accessibilityDescriptors;
    public final int id;
    public final List representations;
    public final List supplementalProperties;
    public final int type;

    public AdaptationSet(int arg1, int arg2, List arg3, List arg4, List arg5) {
        super();
        this.id = arg1;
        this.type = arg2;
        this.representations = Collections.unmodifiableList(arg3);
        List v1 = arg4 == null ? Collections.emptyList() : Collections.unmodifiableList(arg4);
        this.accessibilityDescriptors = v1;
        v1 = arg5 == null ? Collections.emptyList() : Collections.unmodifiableList(arg5);
        this.supplementalProperties = v1;
    }
}

