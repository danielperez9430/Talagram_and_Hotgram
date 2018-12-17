package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class AdPlaybackState {
    public final class AdGroup {
        public final int count;
        public final long[] durationsUs;
        public final int[] states;
        public final Uri[] uris;

        public AdGroup() {
            this(-1, new int[0], new Uri[0], new long[0]);
        }

        private AdGroup(int arg3, int[] arg4, Uri[] arg5, long[] arg6) {
            super();
            boolean v0 = arg4.length == arg5.length ? true : false;
            Assertions.checkArgument(v0);
            this.count = arg3;
            this.states = arg4;
            this.uris = arg5;
            this.durationsUs = arg6;
        }

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] arg3, int arg4) {
            int v0 = arg3.length;
            arg4 = Math.max(arg4, v0);
            arg3 = Arrays.copyOf(arg3, arg4);
            Arrays.fill(arg3, v0, arg4, -9223372036854775807L);
            return arg3;
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] arg2, int arg3) {
            int v0 = arg2.length;
            arg3 = Math.max(arg3, v0);
            arg2 = Arrays.copyOf(arg2, arg3);
            Arrays.fill(arg2, v0, arg3, 0);
            return arg2;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((AdGroup)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.count != ((AdGroup)arg5).count || !Arrays.equals(this.uris, ((AdGroup)arg5).uris) || !Arrays.equals(this.states, ((AdGroup)arg5).states) || !Arrays.equals(this.durationsUs, ((AdGroup)arg5).durationsUs)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int getFirstAdIndexToPlay() {
            return this.getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int arg3) {
            ++arg3;
            while(arg3 < this.states.length) {
                if(this.states[arg3] == 0) {
                    return arg3;
                }

                if(this.states[arg3] == 1) {
                }
                else {
                    ++arg3;
                    continue;
                }

                return arg3;
            }

            return arg3;
        }

        public boolean hasUnplayedAds() {
            boolean v0 = this.count == -1 || this.getFirstAdIndexToPlay() < this.count ? true : false;
            return v0;
        }

        public int hashCode() {
            return ((this.count * 31 + Arrays.hashCode(this.uris)) * 31 + Arrays.hashCode(this.states)) * 31 + Arrays.hashCode(this.durationsUs);
        }

        public AdGroup withAdCount(int arg5) {
            boolean v0 = this.count != -1 || this.states.length > arg5 ? false : true;
            Assertions.checkArgument(v0);
            return new AdGroup(arg5, AdGroup.copyStatesWithSpaceForAdCount(this.states, arg5), Arrays.copyOf(this.uris, arg5), AdGroup.copyDurationsUsWithSpaceForAdCount(this.durationsUs, arg5));
        }

        public AdGroup withAdDurationsUs(long[] arg5) {
            boolean v0 = this.count == -1 || arg5.length <= this.uris.length ? true : false;
            Assertions.checkArgument(v0);
            if(arg5.length < this.uris.length) {
                arg5 = AdGroup.copyDurationsUsWithSpaceForAdCount(arg5, this.uris.length);
            }

            return new AdGroup(this.count, this.states, this.uris, arg5);
        }

        public AdGroup withAdState(int arg5, int arg6) {
            Object[] v2_1;
            boolean v1 = false;
            boolean v0 = this.count == -1 || arg6 < this.count ? true : false;
            Assertions.checkArgument(v0);
            int[] v0_1 = AdGroup.copyStatesWithSpaceForAdCount(this.states, arg6 + 1);
            if(v0_1[arg6] == 0 || v0_1[arg6] == 1 || v0_1[arg6] == arg5) {
                v1 = true;
            }

            Assertions.checkArgument(v1);
            long[] v1_1 = this.durationsUs.length == v0_1.length ? this.durationsUs : AdGroup.copyDurationsUsWithSpaceForAdCount(this.durationsUs, v0_1.length);
            if(this.uris.length == v0_1.length) {
                Uri[] v2 = this.uris;
            }
            else {
                v2_1 = Arrays.copyOf(this.uris, v0_1.length);
            }

            v0_1[arg6] = arg5;
            return new AdGroup(this.count, v0_1, ((Uri[])v2_1), v1_1);
        }

        public AdGroup withAdUri(Uri arg6, int arg7) {
            boolean v1 = false;
            boolean v0 = this.count == -1 || arg7 < this.count ? true : false;
            Assertions.checkArgument(v0);
            int[] v0_1 = AdGroup.copyStatesWithSpaceForAdCount(this.states, arg7 + 1);
            if(v0_1[arg7] == 0) {
                v1 = true;
            }

            Assertions.checkArgument(v1);
            long[] v1_1 = this.durationsUs.length == v0_1.length ? this.durationsUs : AdGroup.copyDurationsUsWithSpaceForAdCount(this.durationsUs, v0_1.length);
            Object[] v3 = Arrays.copyOf(this.uris, v0_1.length);
            v3[arg7] = arg6;
            v0_1[arg7] = 1;
            return new AdGroup(this.count, v0_1, ((Uri[])v3), v1_1);
        }

        public AdGroup withAllAdsSkipped() {
            int v1 = 0;
            if(this.count == -1) {
                return new AdGroup(0, new int[0], new Uri[0], new long[0]);
            }

            int v0 = this.states.length;
            int[] v2 = Arrays.copyOf(this.states, v0);
            while(v1 < v0) {
                if(v2[v1] == 1 || v2[v1] == 0) {
                    v2[v1] = 2;
                }

                ++v1;
            }

            return new AdGroup(v0, v2, this.uris, this.durationsUs);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface AdState {
    }

    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE;
    public static final AdPlaybackState NONE;
    public final int adGroupCount;
    public final long[] adGroupTimesUs;
    public final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final long contentDurationUs;

    static {
        AdPlaybackState.NONE = new AdPlaybackState(new long[0]);
    }

    public AdPlaybackState(long[] arg4) {
        super();
        int v0 = arg4.length;
        this.adGroupCount = v0;
        this.adGroupTimesUs = Arrays.copyOf(arg4, v0);
        this.adGroups = new AdGroup[v0];
        int v4;
        for(v4 = 0; v4 < v0; ++v4) {
            this.adGroups[v4] = new AdGroup();
        }

        this.adResumePositionUs = 0;
        this.contentDurationUs = -9223372036854775807L;
    }

    private AdPlaybackState(long[] arg2, AdGroup[] arg3, long arg4, long arg6) {
        super();
        this.adGroupCount = arg3.length;
        this.adGroupTimesUs = arg2;
        this.adGroups = arg3;
        this.adResumePositionUs = arg4;
        this.contentDurationUs = arg6;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((AdPlaybackState)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else {
                if(this.adGroupCount != ((AdPlaybackState)arg8).adGroupCount || this.adResumePositionUs != ((AdPlaybackState)arg8).adResumePositionUs || this.contentDurationUs != ((AdPlaybackState)arg8).contentDurationUs || !Arrays.equals(this.adGroupTimesUs, ((AdPlaybackState)arg8).adGroupTimesUs) || !Arrays.equals(this.adGroups, ((AdPlaybackState)arg8).adGroups)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int getAdGroupIndexAfterPositionUs(long arg7) {
        int v0;
        for(v0 = 0; v0 < this.adGroupTimesUs.length; ++v0) {
            if(this.adGroupTimesUs[v0] == -9223372036854775808L) {
                break;
            }

            if(arg7 < this.adGroupTimesUs[v0] && (this.adGroups[v0].hasUnplayedAds())) {
                break;
            }
        }

        if(v0 < this.adGroupTimesUs.length) {
        }
        else {
            v0 = -1;
        }

        return v0;
    }

    public int getAdGroupIndexForPositionUs(long arg7) {
        int v0;
        for(v0 = this.adGroupTimesUs.length - 1; v0 >= 0; --v0) {
            if(this.adGroupTimesUs[v0] != -9223372036854775808L && this.adGroupTimesUs[v0] <= arg7) {
                break;
            }
        }

        if(v0 < 0 || !this.adGroups[v0].hasUnplayedAds()) {
            v0 = -1;
        }
        else {
        }

        return v0;
    }

    public int hashCode() {
        return (((this.adGroupCount * 31 + (((int)this.adResumePositionUs))) * 31 + (((int)this.contentDurationUs))) * 31 + Arrays.hashCode(this.adGroupTimesUs)) * 31 + Arrays.hashCode(this.adGroups);
    }

    public AdPlaybackState withAdCount(int arg9, int arg10) {
        boolean v0 = arg10 > 0 ? true : false;
        Assertions.checkArgument(v0);
        if(this.adGroups[arg9].count == arg10) {
            return this;
        }

        Object[] v3 = Arrays.copyOf(this.adGroups, this.adGroups.length);
        v3[arg9] = this.adGroups[arg9].withAdCount(arg10);
        return new AdPlaybackState(this.adGroupTimesUs, ((AdGroup[])v3), this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withAdDurationsUs(long[][] arg9) {
        Object[] v3 = Arrays.copyOf(this.adGroups, this.adGroups.length);
        int v0;
        for(v0 = 0; v0 < this.adGroupCount; ++v0) {
            v3[v0] = v3[v0].withAdDurationsUs(arg9[v0]);
        }

        return new AdPlaybackState(this.adGroupTimesUs, ((AdGroup[])v3), this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withAdLoadError(int arg9, int arg10) {
        Object[] v3 = Arrays.copyOf(this.adGroups, this.adGroups.length);
        v3[arg9] = v3[arg9].withAdState(4, arg10);
        return new AdPlaybackState(this.adGroupTimesUs, ((AdGroup[])v3), this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withAdResumePositionUs(long arg11) {
        if(this.adResumePositionUs == arg11) {
            return this;
        }

        return new AdPlaybackState(this.adGroupTimesUs, this.adGroups, arg11, this.contentDurationUs);
    }

    public AdPlaybackState withAdUri(int arg9, int arg10, Uri arg11) {
        Object[] v3 = Arrays.copyOf(this.adGroups, this.adGroups.length);
        v3[arg9] = v3[arg9].withAdUri(arg11, arg10);
        return new AdPlaybackState(this.adGroupTimesUs, ((AdGroup[])v3), this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withContentDurationUs(long arg11) {
        if(this.contentDurationUs == arg11) {
            return this;
        }

        return new AdPlaybackState(this.adGroupTimesUs, this.adGroups, this.adResumePositionUs, arg11);
    }

    public AdPlaybackState withPlayedAd(int arg9, int arg10) {
        Object[] v3 = Arrays.copyOf(this.adGroups, this.adGroups.length);
        v3[arg9] = v3[arg9].withAdState(3, arg10);
        return new AdPlaybackState(this.adGroupTimesUs, ((AdGroup[])v3), this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withSkippedAd(int arg9, int arg10) {
        Object[] v3 = Arrays.copyOf(this.adGroups, this.adGroups.length);
        v3[arg9] = v3[arg9].withAdState(2, arg10);
        return new AdPlaybackState(this.adGroupTimesUs, ((AdGroup[])v3), this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withSkippedAdGroup(int arg9) {
        Object[] v3 = Arrays.copyOf(this.adGroups, this.adGroups.length);
        v3[arg9] = v3[arg9].withAllAdsSkipped();
        return new AdPlaybackState(this.adGroupTimesUs, ((AdGroup[])v3), this.adResumePositionUs, this.contentDurationUs);
    }
}

