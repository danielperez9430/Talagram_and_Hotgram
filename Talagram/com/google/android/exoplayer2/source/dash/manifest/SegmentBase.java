package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.util.Util;
import java.util.List;

public abstract class SegmentBase {
    public abstract class MultiSegmentBase extends SegmentBase {
        final long duration;
        final List segmentTimeline;
        final long startNumber;

        public MultiSegmentBase(RangedUri arg1, long arg2, long arg4, long arg6, long arg8, List arg10) {
            super(arg1, arg2, arg4);
            this.startNumber = arg6;
            this.duration = arg8;
            this.segmentTimeline = arg10;
        }

        public long getFirstSegmentNum() {
            return this.startNumber;
        }

        public abstract int getSegmentCount(long arg1);

        public final long getSegmentDurationUs(long arg8, long arg10) {
            long v1 = 1000000;
            if(this.segmentTimeline != null) {
                return this.segmentTimeline.get(((int)(arg8 - this.startNumber))).duration * v1 / this.timescale;
            }

            int v0 = this.getSegmentCount(arg10);
            if(v0 == -1 || arg8 != this.getFirstSegmentNum() + (((long)v0)) - 1) {
                arg10 = this.duration * v1 / this.timescale;
            }
            else {
                arg10 -= this.getSegmentTimeUs(arg8);
            }

            return arg10;
        }

        public long getSegmentNum(long arg12, long arg14) {
            long v7;
            long v0 = this.getFirstSegmentNum();
            arg14 = ((long)this.getSegmentCount(arg14));
            if(arg14 == 0) {
                return v0;
            }

            long v3 = 1;
            if(this.segmentTimeline == null) {
                arg12 = arg12 / (this.duration * 1000000 / this.timescale) + this.startNumber;
                if(arg12 < v0) {
                }
                else if(arg14 == -1) {
                    v0 = arg12;
                }
                else {
                    v0 = Math.min(arg12, v0 + arg14 - v3);
                }

                return v0;
            }

            long v5 = arg14 + v0 - v3;
            arg14 = v0;
            while(true) {
                if(arg14 > v5) {
                    goto label_48;
                }

                v7 = (v5 - arg14) / 2 + arg14;
                long v9 = this.getSegmentTimeUs(v7);
                if(v9 < arg12) {
                    arg14 = v7 + v3;
                    continue;
                }

                if(v9 <= arg12) {
                    return v7;
                }

                v5 = v7 - v3;
            }

            return v7;
        label_48:
            if(arg14 == v0) {
            }
            else {
                arg14 = v5;
            }

            return arg14;
        }

        public final long getSegmentTimeUs(long arg7) {
            arg7 = this.segmentTimeline != null ? this.segmentTimeline.get(((int)(arg7 - this.startNumber))).startTime - this.presentationTimeOffset : (arg7 - this.startNumber) * this.duration;
            long v0 = arg7;
            return Util.scaleLargeTimestamp(v0, 1000000, this.timescale);
        }

        public abstract RangedUri getSegmentUrl(Representation arg1, long arg2);

        public boolean isExplicit() {
            boolean v0 = this.segmentTimeline != null ? true : false;
            return v0;
        }
    }

    public class SegmentList extends MultiSegmentBase {
        final List mediaSegments;

        public SegmentList(RangedUri arg1, long arg2, long arg4, long arg6, long arg8, List arg10, List arg11) {
            super(arg1, arg2, arg4, arg6, arg8, arg10);
            this.mediaSegments = arg11;
        }

        public int getSegmentCount(long arg1) {
            return this.mediaSegments.size();
        }

        public RangedUri getSegmentUrl(Representation arg3, long arg4) {
            return this.mediaSegments.get(((int)(arg4 - this.startNumber)));
        }

        public boolean isExplicit() {
            return 1;
        }
    }

    public class SegmentTemplate extends MultiSegmentBase {
        final UrlTemplate initializationTemplate;
        final UrlTemplate mediaTemplate;

        public SegmentTemplate(RangedUri arg1, long arg2, long arg4, long arg6, long arg8, List arg10, UrlTemplate arg11, UrlTemplate arg12) {
            super(arg1, arg2, arg4, arg6, arg8, arg10);
            this.initializationTemplate = arg11;
            this.mediaTemplate = arg12;
        }

        public RangedUri getInitialization(Representation arg15) {
            if(this.initializationTemplate != null) {
                return new RangedUri(this.initializationTemplate.buildUri(arg15.format.id, 0, arg15.format.bitrate, 0), 0, -1);
            }

            return super.getInitialization(arg15);
        }

        public int getSegmentCount(long arg5) {
            if(this.segmentTimeline != null) {
                return this.segmentTimeline.size();
            }

            if(arg5 != -9223372036854775807L) {
                return ((int)Util.ceilDivide(arg5, this.duration * 1000000 / this.timescale));
            }

            return -1;
        }

        public RangedUri getSegmentUrl(Representation arg16, long arg17) {
            SegmentTemplate v0 = this;
            Representation v1 = arg16;
            long v5 = v0.segmentTimeline != null ? v0.segmentTimeline.get(((int)(arg17 - v0.startNumber))).startTime : (arg17 - v0.startNumber) * v0.duration;
            long v6 = v5;
            return new RangedUri(v0.mediaTemplate.buildUri(v1.format.id, arg17, v1.format.bitrate, v6), 0, -1);
        }
    }

    public class SegmentTimelineElement {
        final long duration;
        final long startTime;

        public SegmentTimelineElement(long arg1, long arg3) {
            super();
            this.startTime = arg1;
            this.duration = arg3;
        }
    }

    public class SingleSegmentBase extends SegmentBase {
        final long indexLength;
        final long indexStart;

        public SingleSegmentBase(RangedUri arg1, long arg2, long arg4, long arg6, long arg8) {
            super(arg1, arg2, arg4);
            this.indexStart = arg6;
            this.indexLength = arg8;
        }

        public SingleSegmentBase() {
            this(null, 1, 0, 0, 0);
        }

        public RangedUri getIndex() {
            RangedUri v0 = this.indexLength <= 0 ? null : new RangedUri(null, this.indexStart, this.indexLength);
            return v0;
        }
    }

    final RangedUri initialization;
    final long presentationTimeOffset;
    final long timescale;

    public SegmentBase(RangedUri arg1, long arg2, long arg4) {
        super();
        this.initialization = arg1;
        this.timescale = arg2;
        this.presentationTimeOffset = arg4;
    }

    public RangedUri getInitialization(Representation arg1) {
        return this.initialization;
    }

    public long getPresentationTimeOffsetUs() {
        return Util.scaleLargeTimestamp(this.presentationTimeOffset, 1000000, this.timescale);
    }
}

