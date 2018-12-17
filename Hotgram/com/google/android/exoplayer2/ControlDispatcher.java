package com.google.android.exoplayer2;

public interface ControlDispatcher {
    boolean dispatchSeekTo(Player arg1, int arg2, long arg3);

    boolean dispatchSetPlayWhenReady(Player arg1, boolean arg2);

    boolean dispatchSetRepeatMode(Player arg1, int arg2);

    boolean dispatchSetShuffleModeEnabled(Player arg1, boolean arg2);

    boolean dispatchStop(Player arg1, boolean arg2);
}

