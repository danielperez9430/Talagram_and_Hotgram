package com.google.android.exoplayer2.trackselection;

import android.util.Pair;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public abstract class MappingTrackSelector extends TrackSelector {
    public final class MappedTrackInfo {
        @Retention(value=RetentionPolicy.SOURCE) @interface RendererSupport {
        }

        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        @Deprecated public final int length;
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        MappedTrackInfo(int[] arg1, TrackGroupArray[] arg2, int[] arg3, int[][][] arg4, TrackGroupArray arg5) {
            super();
            this.rendererTrackTypes = arg1;
            this.rendererTrackGroups = arg2;
            this.rendererFormatSupports = arg4;
            this.rendererMixedMimeTypeAdaptiveSupports = arg3;
            this.unmappedTrackGroups = arg5;
            this.rendererCount = arg1.length;
            this.length = this.rendererCount;
        }

        public int getAdaptiveSupport(int arg7, int arg8, boolean arg9) {
            int v0 = this.rendererTrackGroups[arg7].get(arg8).length;
            int[] v1 = new int[v0];
            int v2 = 0;
            int v3 = 0;
            while(v2 < v0) {
                int v4 = this.getTrackSupport(arg7, arg8, v2);
                if(v4 == 4 || (arg9) && v4 == 3) {
                    v1[v3] = v2;
                    ++v3;
                }

                ++v2;
            }

            return this.getAdaptiveSupport(arg7, arg8, Arrays.copyOf(v1, v3));
        }

        public int getAdaptiveSupport(int arg8, int arg9, int[] arg10) {
            int v0 = 0;
            Object v3 = null;
            int v1 = 0;
            int v2 = 0;
            int v4 = 16;
            while(v0 < arg10.length) {
                String v5 = this.rendererTrackGroups[arg8].get(arg9).getFormat(arg10[v0]).sampleMimeType;
                int v6 = v1 + 1;
                if(v1 == 0) {
                    String v3_1 = v5;
                }
                else {
                    v2 |= Util.areEqual(v3, v5) ^ 1;
                }

                v4 = Math.min(v4, this.rendererFormatSupports[arg8][arg9][v0] & 24);
                ++v0;
                v1 = v6;
            }

            if(v2 != 0) {
                v4 = Math.min(v4, this.rendererMixedMimeTypeAdaptiveSupports[arg8]);
            }

            return v4;
        }

        public int getRendererCount() {
            return this.rendererCount;
        }

        public int getRendererSupport(int arg6) {
            int[][] v6 = this.rendererFormatSupports[arg6];
            int v1 = 0;
            int v2;
            for(v2 = 0; v1 < v6.length; v2 = v3) {
                int v3 = v2;
                for(v2 = 0; v2 < v6[v1].length; ++v2) {
                    switch(v6[v1][v2] & 7) {
                        case 3: {
                            goto label_20;
                        }
                        case 4: {
                            return 3;
                        }
                    }

                    int v4 = 1;
                    goto label_21;
                    return 3;
                label_20:
                    v4 = 2;
                label_21:
                    v3 = Math.max(v3, v4);
                }

                ++v1;
            }

            return v2;
        }

        public int getRendererType(int arg2) {
            return this.rendererTrackTypes[arg2];
        }

        @Deprecated public int getTrackFormatSupport(int arg1, int arg2, int arg3) {
            return this.getTrackSupport(arg1, arg2, arg3);
        }

        public TrackGroupArray getTrackGroups(int arg2) {
            return this.rendererTrackGroups[arg2];
        }

        public int getTrackSupport(int arg2, int arg3, int arg4) {
            return this.rendererFormatSupports[arg2][arg3][arg4] & 7;
        }

        @Deprecated public int getTrackTypeRendererSupport(int arg1) {
            return this.getTypeSupport(arg1);
        }

        public int getTypeSupport(int arg4) {
            int v0 = 0;
            int v1 = 0;
            while(v0 < this.rendererCount) {
                if(this.rendererTrackTypes[v0] == arg4) {
                    v1 = Math.max(v1, this.getRendererSupport(v0));
                }

                ++v0;
            }

            return v1;
        }

        @Deprecated public TrackGroupArray getUnassociatedTrackGroups() {
            return this.getUnmappedTrackGroups();
        }

        public TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }
    }

    private MappedTrackInfo currentMappedTrackInfo;

    public MappingTrackSelector() {
        super();
    }

    private static int findRenderer(RendererCapabilities[] arg7, TrackGroup arg8) {
        int v2 = arg7.length;
        int v0 = 0;
        int v3 = 0;
        while(v0 < arg7.length) {
            RendererCapabilities v4 = arg7[v0];
            int v5 = v2;
            for(v2 = 0; v2 < arg8.length; ++v2) {
                int v6 = v4.supportsFormat(arg8.getFormat(v2)) & 7;
                if(v6 > v3) {
                    if(v6 == 4) {
                        return v0;
                    }
                    else {
                        v5 = v0;
                        v3 = v6;
                    }
                }
            }

            ++v0;
            v2 = v5;
        }

        return v2;
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    private static int[] getFormatSupport(RendererCapabilities arg3, TrackGroup arg4) {
        int[] v0 = new int[arg4.length];
        int v1;
        for(v1 = 0; v1 < arg4.length; ++v1) {
            v0[v1] = arg3.supportsFormat(arg4.getFormat(v1));
        }

        return v0;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] arg3) {
        int[] v0 = new int[arg3.length];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = arg3[v1].supportsMixedMimeTypeAdaptation();
        }

        return v0;
    }

    public final void onSelectionActivated(Object arg1) {
        this.currentMappedTrackInfo = ((MappedTrackInfo)arg1);
    }

    protected abstract Pair selectTracks(MappedTrackInfo arg1, int[][][] arg2, int[] arg3);

    public final TrackSelectorResult selectTracks(RendererCapabilities[] arg12, TrackGroupArray arg13) {
        int[] v0 = new int[arg12.length + 1];
        TrackGroup[][] v1 = new TrackGroup[arg12.length + 1][];
        int[][][] v2 = new int[arg12.length + 1][][];
        int v3 = 0;
        int v4;
        for(v4 = 0; v4 < v1.length; ++v4) {
            v1[v4] = new TrackGroup[arg13.length];
            v2[v4] = new int[arg13.length][];
        }

        int[] v9 = MappingTrackSelector.getMixedMimeTypeAdaptationSupports(arg12);
        for(v4 = 0; v4 < arg13.length; ++v4) {
            TrackGroup v5 = arg13.get(v4);
            int v6 = MappingTrackSelector.findRenderer(arg12, v5);
            int[] v7 = v6 == arg12.length ? new int[v5.length] : MappingTrackSelector.getFormatSupport(arg12[v6], v5);
            int v8 = v0[v6];
            v1[v6][v8] = v5;
            v2[v6][v8] = v7;
            ++v0[v6];
        }

        TrackGroupArray[] v5_1 = new TrackGroupArray[arg12.length];
        int[] v4_1 = new int[arg12.length];
        while(v3 < arg12.length) {
            int v13 = v0[v3];
            v5_1[v3] = new TrackGroupArray(Util.nullSafeArrayCopy(v1[v3], v13));
            v2[v3] = Util.nullSafeArrayCopy(v2[v3], v13);
            v4_1[v3] = arg12[v3].getTrackType();
            ++v3;
        }

        MappedTrackInfo v12 = new MappedTrackInfo(v4_1, v5_1, v9, v2, new TrackGroupArray(Util.nullSafeArrayCopy(v1[arg12.length], v0[arg12.length])));
        Pair v13_1 = this.selectTracks(v12, v2, v9);
        return new TrackSelectorResult(v13_1.first, v13_1.second, v12);
    }
}

