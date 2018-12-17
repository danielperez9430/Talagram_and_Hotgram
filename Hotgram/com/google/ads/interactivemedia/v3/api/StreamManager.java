package com.google.ads.interactivemedia.v3.api;

import java.util.List;

public interface StreamManager extends BaseManager {
    double getContentTimeForStreamTime(double arg1);

    List getCuePoints();

    CuePoint getPreviousCuePointForStreamTime(double arg1);

    String getStreamId();

    double getStreamTimeForContentTime(double arg1);
}

