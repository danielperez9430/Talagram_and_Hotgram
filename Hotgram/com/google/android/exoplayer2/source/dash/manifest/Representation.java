package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.DashSegmentIndex;
import java.util.Collections;
import java.util.List;

public abstract class Representation {
    class com.google.android.exoplayer2.source.dash.manifest.Representation$1 {
    }

    public class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        private final MultiSegmentBase segmentBase;

        public MultiSegmentRepresentation(String arg10, long arg11, Format arg13, String arg14, MultiSegmentBase arg15, List arg16) {
            super(arg10, arg11, arg13, arg14, arg15, arg16, null);
            this.segmentBase = arg15;
        }

        public String getCacheKey() {
            return null;
        }

        public long getDurationUs(long arg2, long arg4) {
            return this.segmentBase.getSegmentDurationUs(arg2, arg4);
        }

        public long getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        public DashSegmentIndex getIndex() {
            return this;
        }

        public RangedUri getIndexUri() {
            return null;
        }

        public int getSegmentCount(long arg2) {
            return this.segmentBase.getSegmentCount(arg2);
        }

        public long getSegmentNum(long arg2, long arg4) {
            return this.segmentBase.getSegmentNum(arg2, arg4);
        }

        public RangedUri getSegmentUrl(long arg2) {
            return this.segmentBase.getSegmentUrl(((Representation)this), arg2);
        }

        public long getTimeUs(long arg2) {
            return this.segmentBase.getSegmentTimeUs(arg2);
        }

        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }

    public class SingleSegmentRepresentation extends Representation {
        private final String cacheKey;
        public final long contentLength;
        private final RangedUri indexUri;
        private final SingleSegmentIndex segmentIndex;
        public final Uri uri;

        public SingleSegmentRepresentation(String arg12, long arg13, Format arg15, String arg16, SingleSegmentBase arg17, List arg18, String arg19, long arg20) {
            String v1;
            SingleSegmentRepresentation v9 = this;
            String v10 = arg12;
            super(arg12, arg13, arg15, arg16, arg17, arg18, null);
            v9.uri = Uri.parse(arg16);
            v9.indexUri = arg17.getIndex();
            SingleSegmentIndex v0 = null;
            if(arg19 != null) {
                v1 = arg19;
            }
            else if(v10 != null) {
                v1 = arg12 + "." + arg15.id + "." + arg13;
            }
            else {
                v1 = ((String)v0);
            }

            v9.cacheKey = v1;
            v9.contentLength = arg20;
            if(v9.indexUri != null) {
            }
            else {
                v0 = new SingleSegmentIndex(new RangedUri(null, 0, arg20));
            }

            v9.segmentIndex = v0;
        }

        public String getCacheKey() {
            return this.cacheKey;
        }

        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }

        public RangedUri getIndexUri() {
            return this.indexUri;
        }

        public static SingleSegmentRepresentation newInstance(String arg20, long arg21, Format arg23, String arg24, long arg25, long arg27, long arg29, long arg31, List arg33, String arg34, long arg35) {
            return new SingleSegmentRepresentation(arg20, arg21, arg23, arg24, new SingleSegmentBase(new RangedUri(null, arg25, arg27 - arg25 + 1), 1, 0, arg29, arg31 - arg29 + 1), arg33, arg34, arg35);
        }
    }

    public static final long REVISION_ID_DEFAULT = -1;
    public final String baseUrl;
    public final String contentId;
    public final Format format;
    public final List inbandEventStreams;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;

    private Representation(String arg1, long arg2, Format arg4, String arg5, SegmentBase arg6, List arg7) {
        super();
        this.contentId = arg1;
        this.revisionId = arg2;
        this.format = arg4;
        this.baseUrl = arg5;
        List v1 = arg7 == null ? Collections.emptyList() : Collections.unmodifiableList(arg7);
        this.inbandEventStreams = v1;
        this.initializationUri = arg6.getInitialization(this);
        this.presentationTimeOffsetUs = arg6.getPresentationTimeOffsetUs();
    }

    Representation(String arg1, long arg2, Format arg4, String arg5, SegmentBase arg6, List arg7, com.google.android.exoplayer2.source.dash.manifest.Representation$1 arg8) {
        this(arg1, arg2, arg4, arg5, arg6, arg7);
    }

    public abstract String getCacheKey();

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    public static Representation newInstance(String arg7, long arg8, Format arg10, String arg11, SegmentBase arg12) {
        return Representation.newInstance(arg7, arg8, arg10, arg11, arg12, null);
    }

    public static Representation newInstance(String arg8, long arg9, Format arg11, String arg12, SegmentBase arg13, List arg14) {
        return Representation.newInstance(arg8, arg9, arg11, arg12, arg13, arg14, null);
    }

    public static Representation newInstance(String arg13, long arg14, Format arg16, String arg17, SegmentBase arg18, List arg19, String arg20) {
        SegmentBase v0 = arg18;
        if((v0 instanceof SingleSegmentBase)) {
            return new SingleSegmentRepresentation(arg13, arg14, arg16, arg17, v0, arg19, arg20, -1);
        }

        if((v0 instanceof MultiSegmentBase)) {
            return new MultiSegmentRepresentation(arg13, arg14, arg16, arg17, v0, arg19);
        }

        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }
}

