package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;

final class MergingMediaPeriod implements Callback, MediaPeriod {
    private Callback callback;
    private final ArrayList childrenPendingPreparation;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private MediaPeriod[] enabledPeriods;
    public final MediaPeriod[] periods;
    private final IdentityHashMap streamPeriodIndices;
    private TrackGroupArray trackGroups;

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory arg1, MediaPeriod[] arg2) {
        super();
        this.compositeSequenceableLoaderFactory = arg1;
        this.periods = arg2;
        this.childrenPendingPreparation = new ArrayList();
        this.compositeSequenceableLoader = arg1.createCompositeSequenceableLoader(new SequenceableLoader[0]);
        this.streamPeriodIndices = new IdentityHashMap();
    }

    public boolean continueLoading(long arg5) {
        if(!this.childrenPendingPreparation.isEmpty()) {
            int v0 = this.childrenPendingPreparation.size();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                this.childrenPendingPreparation.get(v2).continueLoading(arg5);
            }

            return 0;
        }

        return this.compositeSequenceableLoader.continueLoading(arg5);
    }

    public void discardBuffer(long arg5, boolean arg7) {
        MediaPeriod[] v0 = this.enabledPeriods;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].discardBuffer(arg5, arg7);
        }
    }

    public long getAdjustedSeekPositionUs(long arg3, SeekParameters arg5) {
        return this.enabledPeriods[0].getAdjustedSeekPositionUs(arg3, arg5);
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public void maybeThrowPrepareError() {
        MediaPeriod[] v0 = this.periods;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].maybeThrowPrepareError();
        }
    }

    public void onContinueLoadingRequested(MediaPeriod arg1) {
        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
    }

    public void onContinueLoadingRequested(SequenceableLoader arg1) {
        this.onContinueLoadingRequested(((MediaPeriod)arg1));
    }

    public void onPrepared(MediaPeriod arg11) {
        this.childrenPendingPreparation.remove(arg11);
        if(!this.childrenPendingPreparation.isEmpty()) {
            return;
        }

        MediaPeriod[] v11 = this.periods;
        int v0 = v11.length;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            v3 += v11[v2].getTrackGroups().length;
            ++v2;
        }

        TrackGroup[] v11_1 = new TrackGroup[v3];
        MediaPeriod[] v0_1 = this.periods;
        v2 = v0_1.length;
        v3 = 0;
        int v4;
        for(v4 = 0; v3 < v2; v4 = v7) {
            TrackGroupArray v5 = v0_1[v3].getTrackGroups();
            int v6 = v5.length;
            int v7 = v4;
            v4 = 0;
            while(v4 < v6) {
                v11_1[v7] = v5.get(v4);
                ++v4;
                ++v7;
            }

            ++v3;
        }

        this.trackGroups = new TrackGroupArray(v11_1);
        this.callback.onPrepared(((MediaPeriod)this));
    }

    public void prepare(Callback arg4, long arg5) {
        this.callback = arg4;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        MediaPeriod[] v4 = this.periods;
        int v0 = v4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            v4[v1].prepare(((Callback)this), arg5);
        }
    }

    public long readDiscontinuity() {
        long v2 = this.periods[0].readDiscontinuity();
        int v0;
        for(v0 = 1; true; ++v0) {
            long v5 = -9223372036854775807L;
            if(v0 >= this.periods.length) {
                goto label_19;
            }

            if(this.periods[v0].readDiscontinuity() != v5) {
                break;
            }
        }

        throw new IllegalStateException("Child reported discontinuity.");
    label_19:
        if(v2 != v5) {
            MediaPeriod[] v0_1 = this.enabledPeriods;
            int v4 = v0_1.length;
            int v5_1;
            for(v5_1 = 0; v5_1 < v4; ++v5_1) {
                MediaPeriod v6 = v0_1[v5_1];
                if(v6 != this.periods[0]) {
                    if(v6.seekToUs(v2) == v2) {
                    }
                    else {
                        throw new IllegalStateException("Unexpected child seekToUs result.");
                    }
                }
            }
        }

        return v2;
    }

    public void reevaluateBuffer(long arg2) {
        this.compositeSequenceableLoader.reevaluateBuffer(arg2);
    }

    public long seekToUs(long arg5) {
        arg5 = this.enabledPeriods[0].seekToUs(arg5);
        int v0;
        for(v0 = 1; true; ++v0) {
            if(v0 >= this.enabledPeriods.length) {
                return arg5;
            }

            if(this.enabledPeriods[v0].seekToUs(arg5) != arg5) {
                break;
            }
        }

        throw new IllegalStateException("Unexpected child seekToUs result.");
        return arg5;
    }

    public long selectTracks(TrackSelection[] arg20, boolean[] arg21, SampleStream[] arg22, boolean[] arg23, long arg24) {
        int v9;
        int v8;
        MergingMediaPeriod v0 = this;
        TrackSelection[] v1 = arg20;
        SampleStream[] v2 = arg22;
        int[] v3 = new int[v1.length];
        int[] v4 = new int[v1.length];
        int v6;
        for(v6 = 0; v6 < v1.length; ++v6) {
            v8 = -1;
            int v7 = v2[v6] == null ? -1 : v0.streamPeriodIndices.get(v2[v6]).intValue();
            v3[v6] = v7;
            v4[v6] = v8;
            if(v1[v6] != null) {
                TrackGroup v7_1 = v1[v6].getTrackGroup();
                v9 = 0;
                while(v9 < v0.periods.length) {
                    if(v0.periods[v9].getTrackGroups().indexOf(v7_1) != v8) {
                        v4[v6] = v9;
                    }
                    else {
                        ++v9;
                        continue;
                    }

                    break;
                }
            }
        }

        v0.streamPeriodIndices.clear();
        SampleStream[] v6_1 = new SampleStream[v1.length];
        SampleStream[] v7_2 = new SampleStream[v1.length];
        TrackSelection[] v15 = new TrackSelection[v1.length];
        ArrayList v13 = new ArrayList(v0.periods.length);
        long v16 = arg24;
        int v14 = 0;
        while(v14 < v0.periods.length) {
            for(v8 = 0; v8 < v1.length; ++v8) {
                TrackSelection v10 = null;
                SampleStream v9_1 = v3[v8] == v14 ? v2[v8] : ((SampleStream)v10);
                v7_2[v8] = v9_1;
                if(v4[v8] == v14) {
                    v10 = v1[v8];
                }

                v15[v8] = v10;
            }

            TrackSelection[] v9_2 = v15;
            ArrayList v5 = v13;
            TrackSelection[] v18 = v15;
            int v15_1 = v14;
            long v8_1 = v0.periods[v14].selectTracks(v9_2, arg21, v7_2, arg23, v16);
            if(v15_1 == 0) {
                v16 = v8_1;
            }
            else {
                if(v8_1 == v16) {
                    goto label_88;
                }

                goto label_126;
            }

        label_88:
            v8 = 0;
            v9 = 0;
            goto label_90;
        label_126:
            throw new IllegalStateException("Children enabled at different positions.");
        label_90:
            while(v8 < v1.length) {
                boolean v11 = true;
                if(v4[v8] == v15_1) {
                    boolean v9_3 = v7_2[v8] != null ? true : false;
                    Assertions.checkState(v9_3);
                    v6_1[v8] = v7_2[v8];
                    v0.streamPeriodIndices.put(v7_2[v8], Integer.valueOf(v15_1));
                    v9 = 1;
                }
                else {
                    if(v3[v8] != v15_1) {
                        goto label_116;
                    }

                    if(v7_2[v8] == null) {
                    }
                    else {
                        v11 = false;
                    }

                    Assertions.checkState(v11);
                }

            label_116:
                ++v8;
            }

            if(v9 != 0) {
                v5.add(v0.periods[v15_1]);
            }

            v14 = v15_1 + 1;
            v13 = v5;
            v15 = v18;
        }

        System.arraycopy(v6_1, 0, v2, 0, v6_1.length);
        v0.enabledPeriods = new MediaPeriod[v13.size()];
        v13.toArray(v0.enabledPeriods);
        v0.compositeSequenceableLoader = v0.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(v0.enabledPeriods);
        return v16;
    }
}

