package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class BaseTrackSelection implements TrackSelection {
    class com.google.android.exoplayer2.trackselection.BaseTrackSelection$1 {
    }

    final class DecreasingBandwidthComparator implements Comparator {
        DecreasingBandwidthComparator(com.google.android.exoplayer2.trackselection.BaseTrackSelection$1 arg1) {
            this();
        }

        private DecreasingBandwidthComparator() {
            super();
        }

        public int compare(Format arg1, Format arg2) {
            return arg2.bitrate - arg1.bitrate;
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Format)arg1), ((Format)arg2));
        }
    }

    private final long[] blacklistUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    protected final int[] tracks;

    public BaseTrackSelection(TrackGroup arg5, int[] arg6) {
        super();
        int v1 = 0;
        boolean v0 = arg6.length > 0 ? true : false;
        Assertions.checkState(v0);
        this.group = Assertions.checkNotNull(arg5);
        this.length = arg6.length;
        this.formats = new Format[this.length];
        int v0_1;
        for(v0_1 = 0; v0_1 < arg6.length; ++v0_1) {
            this.formats[v0_1] = arg5.getFormat(arg6[v0_1]);
        }

        Arrays.sort(this.formats, new DecreasingBandwidthComparator(null));
        this.tracks = new int[this.length];
        while(v1 < this.length) {
            this.tracks[v1] = arg5.indexOf(this.formats[v1]);
            ++v1;
        }

        this.blacklistUntilTimes = new long[this.length];
    }

    public final boolean blacklist(int arg8, long arg9) {
        long v0 = SystemClock.elapsedRealtime();
        boolean v4 = this.isBlacklisted(arg8, v0);
        int v2;
        for(v2 = 0; v2 < this.length; ++v2) {
            if(v4) {
                break;
            }

            v4 = v2 == arg8 || (this.isBlacklisted(v2, v0)) ? false : true;
        }

        if(!v4) {
            return 0;
        }

        this.blacklistUntilTimes[arg8] = Math.max(this.blacklistUntilTimes[arg8], v0 + arg9);
        return 1;
    }

    public void disable() {
    }

    public void enable() {
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((BaseTrackSelection)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.group != ((BaseTrackSelection)arg5).group || !Arrays.equals(this.tracks, ((BaseTrackSelection)arg5).tracks)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int evaluateQueueSize(long arg1, List arg3) {
        return arg3.size();
    }

    public final Format getFormat(int arg2) {
        return this.formats[arg2];
    }

    public final int getIndexInTrackGroup(int arg2) {
        return this.tracks[arg2];
    }

    public final Format getSelectedFormat() {
        return this.formats[this.getSelectedIndex()];
    }

    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[this.getSelectedIndex()];
    }

    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            this.hashCode = System.identityHashCode(this.group) * 31 + Arrays.hashCode(this.tracks);
        }

        return this.hashCode;
    }

    public final int indexOf(int arg3) {
        int v0;
        for(v0 = 0; v0 < this.length; ++v0) {
            if(this.tracks[v0] == arg3) {
                return v0;
            }
        }

        return -1;
    }

    public final int indexOf(Format arg3) {
        int v0;
        for(v0 = 0; v0 < this.length; ++v0) {
            if(this.formats[v0] == arg3) {
                return v0;
            }
        }

        return -1;
    }

    protected final boolean isBlacklisted(int arg4, long arg5) {
        boolean v4 = this.blacklistUntilTimes[arg4] > arg5 ? true : false;
        return v4;
    }

    public final int length() {
        return this.tracks.length;
    }

    public void onPlaybackSpeed(float arg1) {
    }
}

