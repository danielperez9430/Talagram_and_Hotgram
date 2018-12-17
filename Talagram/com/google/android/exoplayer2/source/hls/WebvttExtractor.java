package com.google.android.exoplayer2.source.hls;

import android.text.TextUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.webvtt.WebvttParserUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class WebvttExtractor implements Extractor {
    private static final Pattern LOCAL_TIMESTAMP;
    private static final Pattern MEDIA_TIMESTAMP;
    private final String language;
    private ExtractorOutput output;
    private byte[] sampleData;
    private final ParsableByteArray sampleDataWrapper;
    private int sampleSize;
    private final TimestampAdjuster timestampAdjuster;

    static {
        WebvttExtractor.LOCAL_TIMESTAMP = Pattern.compile("LOCAL:([^,]+)");
        WebvttExtractor.MEDIA_TIMESTAMP = Pattern.compile("MPEGTS:(\\d+)");
    }

    public WebvttExtractor(String arg1, TimestampAdjuster arg2) {
        super();
        this.language = arg1;
        this.timestampAdjuster = arg2;
        this.sampleDataWrapper = new ParsableByteArray();
        this.sampleData = new byte[1024];
    }

    private TrackOutput buildTrackOutput(long arg11) {
        TrackOutput v0 = this.output.track(0, 3);
        v0.format(Format.createTextSampleFormat(null, "text/vtt", null, -1, 0, this.language, null, arg11));
        this.output.endTracks();
        return v0;
    }

    public void init(ExtractorOutput arg4) {
        this.output = arg4;
        arg4.seekMap(new Unseekable(-9223372036854775807L));
    }

    private void processSample() {
        String v7;
        long v5;
        long v3;
        long v1;
        ParsableByteArray v0 = new ParsableByteArray(this.sampleData);
        try {
            WebvttParserUtil.validateWebvttHeaderLine(v0);
            v1 = 0;
            v3 = v1;
            v5 = v3;
        }
        catch(SubtitleDecoderException v0_1) {
            throw new ParserException(((Throwable)v0_1));
        }

        while(true) {
            v7 = v0.readLine();
            if(TextUtils.isEmpty(((CharSequence)v7))) {
                goto label_46;
            }

            if(!v7.startsWith("X-TIMESTAMP-MAP")) {
                continue;
            }

            Matcher v3_1 = WebvttExtractor.LOCAL_TIMESTAMP.matcher(((CharSequence)v7));
            if(!v3_1.find()) {
                goto label_37;
            }

            Matcher v4 = WebvttExtractor.MEDIA_TIMESTAMP.matcher(((CharSequence)v7));
            if(!v4.find()) {
                break;
            }

            v5 = WebvttParserUtil.parseTimestampUs(v3_1.group(1));
            v3 = TimestampAdjuster.ptsToUs(Long.parseLong(v4.group(1)));
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("X-TIMESTAMP-MAP doesn\'t contain media timestamp: ");
        v1_1.append(v7);
        throw new ParserException(v1_1.toString());
    label_37:
        v1_1 = new StringBuilder();
        v1_1.append("X-TIMESTAMP-MAP doesn\'t contain local timestamp: ");
        v1_1.append(v7);
        throw new ParserException(v1_1.toString());
    label_46:
        Matcher v0_2 = WebvttParserUtil.findNextCueHeader(v0);
        if(v0_2 == null) {
            this.buildTrackOutput(v1);
            return;
        }

        long v0_3 = WebvttParserUtil.parseTimestampUs(v0_2.group(1));
        long v6 = this.timestampAdjuster.adjustTsTimestamp(TimestampAdjuster.usToPts(v3 + v0_3 - v5));
        TrackOutput v5_1 = this.buildTrackOutput(v6 - v0_3);
        this.sampleDataWrapper.reset(this.sampleData, this.sampleSize);
        v5_1.sampleData(this.sampleDataWrapper, this.sampleSize);
        v5_1.sampleMetadata(v6, 1, this.sampleSize, 0, null);
    }

    public int read(ExtractorInput arg6, PositionHolder arg7) {
        int v7 = ((int)arg6.getLength());
        int v2 = -1;
        if(this.sampleSize == this.sampleData.length) {
            byte[] v0 = this.sampleData;
            int v1 = v7 != v2 ? v7 : this.sampleData.length;
            this.sampleData = Arrays.copyOf(v0, v1 * 3 / 2);
        }

        int v6 = arg6.read(this.sampleData, this.sampleSize, this.sampleData.length - this.sampleSize);
        if(v6 != v2) {
            this.sampleSize += v6;
            if(v7 != v2 && this.sampleSize == v7) {
                goto label_33;
            }

            return 0;
        }

    label_33:
        this.processSample();
        return v2;
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        throw new IllegalStateException();
    }

    public boolean sniff(ExtractorInput arg1) {
        throw new IllegalStateException();
    }
}

