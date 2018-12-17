package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import java.util.List;

public interface TrackSelection {
    public interface Factory {
        TrackSelection createTrackSelection(TrackGroup arg1, BandwidthMeter arg2, int[] arg3);
    }

    boolean blacklist(int arg1, long arg2);

    void disable();

    void enable();

    int evaluateQueueSize(long arg1, List arg2);

    Format getFormat(int arg1);

    int getIndexInTrackGroup(int arg1);

    Format getSelectedFormat();

    int getSelectedIndex();

    int getSelectedIndexInTrackGroup();

    Object getSelectionData();

    int getSelectionReason();

    TrackGroup getTrackGroup();

    int indexOf(int arg1);

    int indexOf(Format arg1);

    int length();

    void onPlaybackSpeed(float arg1);

    void updateSelectedTrack(long arg1, long arg2, long arg3);
}

