package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SsManifest implements FilterableManifest {
    public class ProtectionElement {
        public final byte[] data;
        public final UUID uuid;

        public ProtectionElement(UUID arg1, byte[] arg2) {
            super();
            this.uuid = arg1;
            this.data = arg2;
        }
    }

    public class StreamElement {
        private static final String URL_PLACEHOLDER_BITRATE_1 = "{bitrate}";
        private static final String URL_PLACEHOLDER_BITRATE_2 = "{Bitrate}";
        private static final String URL_PLACEHOLDER_START_TIME_1 = "{start time}";
        private static final String URL_PLACEHOLDER_START_TIME_2 = "{start_time}";
        private final String baseUri;
        public final int chunkCount;
        private final List chunkStartTimes;
        private final long[] chunkStartTimesUs;
        private final String chunkTemplate;
        public final int displayHeight;
        public final int displayWidth;
        public final Format[] formats;
        public final String language;
        private final long lastChunkDurationUs;
        public final int maxHeight;
        public final int maxWidth;
        public final String name;
        public final String subType;
        public final long timescale;
        public final int type;

        public StreamElement(String arg23, String arg24, int arg25, String arg26, long arg27, String arg29, int arg30, int arg31, int arg32, int arg33, String arg34, Format[] arg35, List arg36, long arg37) {
            this(arg23, arg24, arg25, arg26, arg27, arg29, arg30, arg31, arg32, arg33, arg34, arg35, arg36, Util.scaleLargeTimestamps(arg36, 1000000, arg27), Util.scaleLargeTimestamp(arg37, 1000000, arg27));
        }

        private StreamElement(String arg5, String arg6, int arg7, String arg8, long arg9, String arg11, int arg12, int arg13, int arg14, int arg15, String arg16, Format[] arg17, List arg18, long[] arg19, long arg20) {
            super();
            this.baseUri = arg5;
            this.chunkTemplate = arg6;
            this.type = arg7;
            this.subType = arg8;
            this.timescale = arg9;
            this.name = arg11;
            this.maxWidth = arg12;
            this.maxHeight = arg13;
            this.displayWidth = arg14;
            this.displayHeight = arg15;
            this.language = arg16;
            this.formats = arg17;
            this.chunkStartTimes = arg18;
            this.chunkStartTimesUs = arg19;
            this.lastChunkDurationUs = arg20;
            this.chunkCount = arg18.size();
        }

        public Uri buildRequestUri(int arg4, int arg5) {
            boolean v1 = false;
            boolean v0 = this.formats != null ? true : false;
            Assertions.checkState(v0);
            v0 = this.chunkStartTimes != null ? true : false;
            Assertions.checkState(v0);
            if(arg5 < this.chunkStartTimes.size()) {
                v1 = true;
            }

            Assertions.checkState(v1);
            String v4 = Integer.toString(this.formats[arg4].bitrate);
            String v5 = this.chunkStartTimes.get(arg5).toString();
            return UriUtil.resolveToUri(this.baseUri, this.chunkTemplate.replace("{bitrate}", ((CharSequence)v4)).replace("{Bitrate}", ((CharSequence)v4)).replace("{start time}", ((CharSequence)v5)).replace("{start_time}", ((CharSequence)v5)));
        }

        public StreamElement copy(Format[] arg23) {
            return new StreamElement(this.baseUri, this.chunkTemplate, this.type, this.subType, this.timescale, this.name, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, arg23, this.chunkStartTimes, this.chunkStartTimesUs, this.lastChunkDurationUs);
        }

        public long getChunkDurationUs(int arg6) {
            long v0 = arg6 == this.chunkCount - 1 ? this.lastChunkDurationUs : this.chunkStartTimesUs[arg6 + 1] - this.chunkStartTimesUs[arg6];
            return v0;
        }

        public int getChunkIndex(long arg3) {
            return Util.binarySearchFloor(this.chunkStartTimesUs, arg3, true, true);
        }

        public long getStartTimeUs(int arg4) {
            return this.chunkStartTimesUs[arg4];
        }
    }

    public final class StreamElementIterator extends BaseMediaChunkIterator {
        private final StreamElement streamElement;
        private final int trackIndex;

        public StreamElementIterator(StreamElement arg5, int arg6, int arg7) {
            super(((long)arg7), ((long)(arg5.chunkCount - 1)));
            this.streamElement = arg5;
            this.trackIndex = arg6;
        }

        public long getChunkEndTimeUs() {
            return this.getChunkStartTimeUs() + this.streamElement.getChunkDurationUs(((int)this.getCurrentIndex()));
        }

        public long getChunkStartTimeUs() {
            this.checkInBounds();
            return this.streamElement.getStartTimeUs(((int)this.getCurrentIndex()));
        }

        public DataSpec getDataSpec() {
            this.checkInBounds();
            return new DataSpec(this.streamElement.buildRequestUri(this.trackIndex, ((int)this.getCurrentIndex())));
        }
    }

    public static final int UNSET_LOOKAHEAD = -1;
    public final long durationUs;
    public final long dvrWindowLengthUs;
    public final boolean isLive;
    public final int lookAheadCount;
    public final int majorVersion;
    public final int minorVersion;
    public final ProtectionElement protectionElement;
    public final StreamElement[] streamElements;

    private SsManifest(int arg1, int arg2, long arg3, long arg5, int arg7, boolean arg8, ProtectionElement arg9, StreamElement[] arg10) {
        super();
        this.majorVersion = arg1;
        this.minorVersion = arg2;
        this.durationUs = arg3;
        this.dvrWindowLengthUs = arg5;
        this.lookAheadCount = arg7;
        this.isLive = arg8;
        this.protectionElement = arg9;
        this.streamElements = arg10;
    }

    public SsManifest(int arg22, int arg23, long arg24, long arg26, long arg28, int arg30, boolean arg31, ProtectionElement arg32, StreamElement[] arg33) {
        long v0 = 0;
        long v8 = -9223372036854775807L;
        long v4 = Long.compare(arg26, v0) == 0 ? v8 : Util.scaleLargeTimestamp(arg26, 1000000, arg24);
        if(arg28 != v0) {
            v8 = Util.scaleLargeTimestamp(arg28, 1000000, arg24);
        }

        long v15 = v8;
        this(arg22, arg23, v4, v15, arg30, arg31, arg32, arg33);
    }

    public final SsManifest copy(List arg12) {
        ArrayList v0 = new ArrayList(((Collection)arg12));
        Collections.sort(((List)v0));
        ArrayList v12 = new ArrayList();
        ArrayList v1 = new ArrayList();
        StreamElement v4 = null;
        int v3 = 0;
        while(v3 < v0.size()) {
            Object v5 = v0.get(v3);
            StreamElement v6 = this.streamElements[((StreamKey)v5).groupIndex];
            if(v6 != v4 && v4 != null) {
                ((List)v12).add(v4.copy(((List)v1).toArray(new Format[0])));
                ((List)v1).clear();
            }

            ((List)v1).add(v6.formats[((StreamKey)v5).trackIndex]);
            ++v3;
            v4 = v6;
        }

        if(v4 != null) {
            ((List)v12).add(v4.copy(((List)v1).toArray(new Format[0])));
        }

        return new SsManifest(this.majorVersion, this.minorVersion, this.durationUs, this.dvrWindowLengthUs, this.lookAheadCount, this.isLive, this.protectionElement, ((List)v12).toArray(new StreamElement[0]));
    }

    public Object copy(List arg1) {
        return this.copy(arg1);
    }
}

