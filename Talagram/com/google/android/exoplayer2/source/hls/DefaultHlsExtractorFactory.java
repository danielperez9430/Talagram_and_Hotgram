package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.Track;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class DefaultHlsExtractorFactory implements HlsExtractorFactory {
    public static final String AAC_FILE_EXTENSION = ".aac";
    public static final String AC3_FILE_EXTENSION = ".ac3";
    public static final String EC3_FILE_EXTENSION = ".ec3";
    public static final String M4_FILE_EXTENSION_PREFIX = ".m4";
    public static final String MP3_FILE_EXTENSION = ".mp3";
    public static final String MP4_FILE_EXTENSION = ".mp4";
    public static final String MP4_FILE_EXTENSION_PREFIX = ".mp4";
    public static final String VTT_FILE_EXTENSION = ".vtt";
    public static final String WEBVTT_FILE_EXTENSION = ".webvtt";

    public DefaultHlsExtractorFactory() {
        super();
    }

    public Pair createExtractor(Extractor arg9, Uri arg10, Format arg11, List arg12, DrmInitData arg13, TimestampAdjuster arg14, Map arg15) {
        Object v9_4;
        String v10 = arg10.getLastPathSegment();
        if(v10 == null) {
            v10 = "";
        }

        boolean v1 = false;
        if(("text/vtt".equals(arg11.sampleMimeType)) || (v10.endsWith(".webvtt")) || (v10.endsWith(".vtt"))) {
            WebvttExtractor v9_6 = new WebvttExtractor(arg11.language, arg14);
        }
        else {
            if(v10.endsWith(".aac")) {
                AdtsExtractor v9 = new AdtsExtractor();
            }
            else {
                if(!v10.endsWith(".ac3")) {
                    if(v10.endsWith(".ec3")) {
                    }
                    else if(v10.endsWith(".mp3")) {
                        Mp3Extractor v9_1 = new Mp3Extractor(0, 0);
                        goto label_21;
                    }
                    else {
                        if(arg9 != null) {
                        }
                        else {
                            if(!v10.endsWith(".mp4") && !v10.startsWith(".m4", v10.length() - 4)) {
                                if(v10.startsWith(".mp4", v10.length() - 5)) {
                                }
                                else {
                                    int v9_2 = 16;
                                    if(arg12 != null) {
                                        v9_2 = 48;
                                    }
                                    else {
                                        arg12 = Collections.singletonList(Format.createTextSampleFormat(null, "application/cea-608", 0, null));
                                    }

                                    v10 = arg11.codecs;
                                    if(!TextUtils.isEmpty(((CharSequence)v10))) {
                                        if(!"audio/mp4a-latm".equals(MimeTypes.getAudioMediaMimeType(v10))) {
                                            v9_2 |= 2;
                                        }

                                        if("video/avc".equals(MimeTypes.getVideoMediaMimeType(v10))) {
                                            goto label_74;
                                        }

                                        v9_2 |= 4;
                                    }

                                label_74:
                                    TsExtractor v9_3 = new TsExtractor(2, arg14, new DefaultTsPayloadReaderFactory(v9_2, arg12));
                                    goto label_100;
                                }
                            }

                            v9_4 = null;
                            Track v5 = null;
                            if(arg12 == null) {
                                arg12 = Collections.emptyList();
                            }

                            List v7 = arg12;
                            super(0, arg14, v5, arg13, v7);
                        }

                        goto label_100;
                    }
                }

                Ac3Extractor v9_5 = new Ac3Extractor();
            }

        label_21:
            v1 = true;
        }

    label_100:
        return Pair.create(v9_4, Boolean.valueOf(v1));
    }
}

