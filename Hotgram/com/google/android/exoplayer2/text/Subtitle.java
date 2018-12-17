package com.google.android.exoplayer2.text;

import java.util.List;

public interface Subtitle {
    List getCues(long arg1);

    long getEventTime(int arg1);

    int getEventTimeCount();

    int getNextEventTimeIndex(long arg1);
}

