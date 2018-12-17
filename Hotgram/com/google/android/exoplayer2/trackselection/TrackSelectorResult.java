package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.util.Util;

public final class TrackSelectorResult {
    public final Object info;
    public final int length;
    public final RendererConfiguration[] rendererConfigurations;
    public final TrackSelectionArray selections;

    public TrackSelectorResult(RendererConfiguration[] arg2, TrackSelection[] arg3, Object arg4) {
        super();
        this.rendererConfigurations = arg2;
        this.selections = new TrackSelectionArray(arg3);
        this.info = arg4;
        this.length = arg2.length;
    }

    public boolean isEquivalent(TrackSelectorResult arg4, int arg5) {
        boolean v0 = false;
        if(arg4 == null) {
            return 0;
        }

        if((Util.areEqual(this.rendererConfigurations[arg5], arg4.rendererConfigurations[arg5])) && (Util.areEqual(this.selections.get(arg5), arg4.selections.get(arg5)))) {
            v0 = true;
        }

        return v0;
    }

    public boolean isEquivalent(TrackSelectorResult arg4) {
        if(arg4 != null) {
            if(arg4.selections.length != this.selections.length) {
            }
            else {
                int v1 = 0;
                while(true) {
                    if(v1 >= this.selections.length) {
                        return 1;
                    }
                    else if(!this.isEquivalent(arg4, v1)) {
                        return 0;
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public boolean isRendererEnabled(int arg2) {
        boolean v2 = this.rendererConfigurations[arg2] != null ? true : false;
        return v2;
    }
}

