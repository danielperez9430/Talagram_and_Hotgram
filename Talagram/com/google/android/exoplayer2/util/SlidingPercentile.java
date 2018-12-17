package com.google.android.exoplayer2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SlidingPercentile {
    final class com.google.android.exoplayer2.util.SlidingPercentile$1 implements Comparator {
        com.google.android.exoplayer2.util.SlidingPercentile$1() {
            super();
        }

        public int compare(Sample arg1, Sample arg2) {
            return arg1.index - arg2.index;
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Sample)arg1), ((Sample)arg2));
        }
    }

    final class com.google.android.exoplayer2.util.SlidingPercentile$2 implements Comparator {
        com.google.android.exoplayer2.util.SlidingPercentile$2() {
            super();
        }

        public int compare(Sample arg3, Sample arg4) {
            int v3;
            if(arg3.value < arg4.value) {
                v3 = -1;
            }
            else if(arg4.value < arg3.value) {
                v3 = 1;
            }
            else {
                v3 = 0;
            }

            return v3;
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Sample)arg1), ((Sample)arg2));
        }
    }

    class Sample {
        public int index;
        public float value;
        public int weight;

        Sample(com.google.android.exoplayer2.util.SlidingPercentile$1 arg1) {
            this();
        }

        private Sample() {
            super();
        }
    }

    private static final Comparator INDEX_COMPARATOR = null;
    private static final int MAX_RECYCLED_SAMPLES = 5;
    private static final int SORT_ORDER_BY_INDEX = 1;
    private static final int SORT_ORDER_BY_VALUE = 0;
    private static final int SORT_ORDER_NONE = -1;
    private static final Comparator VALUE_COMPARATOR;
    private int currentSortOrder;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private final Sample[] recycledSamples;
    private final ArrayList samples;
    private int totalWeight;

    static {
        SlidingPercentile.INDEX_COMPARATOR = new com.google.android.exoplayer2.util.SlidingPercentile$1();
        SlidingPercentile.VALUE_COMPARATOR = new com.google.android.exoplayer2.util.SlidingPercentile$2();
    }

    public SlidingPercentile(int arg1) {
        super();
        this.maxWeight = arg1;
        this.recycledSamples = new Sample[5];
        this.samples = new ArrayList();
        this.currentSortOrder = -1;
    }

    public void addSample(int arg4, float arg5) {
        Object v5_1;
        Sample v0_1;
        int v1;
        this.ensureSortedByIndex();
        if(this.recycledSampleCount > 0) {
            Sample[] v0 = this.recycledSamples;
            v1 = this.recycledSampleCount - 1;
            this.recycledSampleCount = v1;
            v0_1 = v0[v1];
        }
        else {
            v0_1 = new Sample(null);
        }

        v1 = this.nextSampleIndex;
        this.nextSampleIndex = v1 + 1;
        v0_1.index = v1;
        v0_1.weight = arg4;
        v0_1.value = arg5;
        this.samples.add(v0_1);
        int v5;
        for(v5 = this.totalWeight + arg4; true; v5 = this.totalWeight - arg4) {
            this.totalWeight = v5;
            while(true) {
                if(this.totalWeight <= this.maxWeight) {
                    return;
                }

                arg4 = this.totalWeight - this.maxWeight;
                v5_1 = this.samples.get(0);
                if(((Sample)v5_1).weight > arg4) {
                    break;
                }

                this.totalWeight -= ((Sample)v5_1).weight;
                this.samples.remove(0);
                if(this.recycledSampleCount >= 5) {
                    continue;
                }

                Sample[] v4 = this.recycledSamples;
                int v0_2 = this.recycledSampleCount;
                this.recycledSampleCount = v0_2 + 1;
                v4[v0_2] = v5_1;
            }

            ((Sample)v5_1).weight -= arg4;
        }
    }

    private void ensureSortedByIndex() {
        if(this.currentSortOrder != 1) {
            Collections.sort(this.samples, SlidingPercentile.INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }

    private void ensureSortedByValue() {
        if(this.currentSortOrder != 0) {
            Collections.sort(this.samples, SlidingPercentile.VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }

    public float getPercentile(float arg5) {
        this.ensureSortedByValue();
        arg5 *= ((float)this.totalWeight);
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.samples.size()) {
            Object v2 = this.samples.get(v0);
            v1 += ((Sample)v2).weight;
            if((((float)v1)) >= arg5) {
                return ((Sample)v2).value;
            }

            ++v0;
        }

        return this.samples.isEmpty() ? NaNf : this.samples.get(this.samples.size() - 1).value;
    }
}

