package com.google.android.exoplayer2.text.cea;

import java.util.Collections;
import java.util.List;

public final class Cea708InitializationData {
    public final boolean isWideAspectRatio;

    private Cea708InitializationData(List arg2) {
        super();
        boolean v0 = false;
        if(arg2.get(0)[0] != 0) {
            v0 = true;
        }

        this.isWideAspectRatio = v0;
    }

    public static List buildData(boolean arg2) {
        return Collections.singletonList(new byte[]{((byte)(((int)arg2)))});
    }

    public static Cea708InitializationData fromData(List arg1) {
        return new Cea708InitializationData(arg1);
    }
}

