package com.google.android.exoplayer2;

public class DefaultControlDispatcher implements ControlDispatcher {
    public DefaultControlDispatcher() {
        super();
    }

    public boolean dispatchSeekTo(Player arg1, int arg2, long arg3) {
        arg1.seekTo(arg2, arg3);
        return 1;
    }

    public boolean dispatchSetPlayWhenReady(Player arg1, boolean arg2) {
        arg1.setPlayWhenReady(arg2);
        return 1;
    }

    public boolean dispatchSetRepeatMode(Player arg1, int arg2) {
        arg1.setRepeatMode(arg2);
        return 1;
    }

    public boolean dispatchSetShuffleModeEnabled(Player arg1, boolean arg2) {
        arg1.setShuffleModeEnabled(arg2);
        return 1;
    }

    public boolean dispatchStop(Player arg1, boolean arg2) {
        arg1.stop(arg2);
        return 1;
    }
}

