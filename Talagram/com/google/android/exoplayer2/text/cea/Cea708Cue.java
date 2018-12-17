package com.google.android.exoplayer2.text.cea;

import android.text.Layout$Alignment;
import com.google.android.exoplayer2.text.Cue;

final class Cea708Cue extends Cue implements Comparable {
    public static final int PRIORITY_UNSET = -1;
    public final int priority;

    public Cea708Cue(CharSequence arg1, Layout$Alignment arg2, float arg3, int arg4, int arg5, float arg6, int arg7, float arg8, boolean arg9, int arg10, int arg11) {
        super(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        this.priority = arg11;
    }

    public int compareTo(Cea708Cue arg3) {
        if(arg3.priority < this.priority) {
            return -1;
        }

        if(arg3.priority > this.priority) {
            return 1;
        }

        return 0;
    }

    public int compareTo(Object arg1) {
        return this.compareTo(((Cea708Cue)arg1));
    }
}

