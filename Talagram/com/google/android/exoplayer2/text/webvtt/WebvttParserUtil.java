package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttParserUtil {
    private static final Pattern COMMENT;
    private static final Pattern HEADER;

    static {
        WebvttParserUtil.COMMENT = Pattern.compile("^NOTE(( |\t).*)?$");
        WebvttParserUtil.HEADER = Pattern.compile("^﻿?WEBVTT(( |\t).*)?$");
    }

    private WebvttParserUtil() {
        super();
    }

    public static Matcher findNextCueHeader(ParsableByteArray arg2) {
        Matcher v0_1;
        String v0;
        do {
        label_0:
            v0 = arg2.readLine();
            if(v0 == null) {
                return null;
            }

            if(!WebvttParserUtil.COMMENT.matcher(((CharSequence)v0)).matches()) {
                v0_1 = WebvttCueParser.CUE_HEADER_PATTERN.matcher(((CharSequence)v0));
                if(!v0_1.matches()) {
                    continue;
                }

                return v0_1;
            }

            goto label_6;
        }
        while(true);

        return v0_1;
        while(true) {
        label_6:
            v0 = arg2.readLine();
            if(v0 == null) {
                goto label_0;
            }

            if(v0.isEmpty()) {
                goto label_0;
            }
        }
    }

    public static float parsePercentage(String arg2) {
        if(arg2.endsWith("%")) {
            return Float.parseFloat(arg2.substring(0, arg2.length() - 1)) / 100f;
        }

        throw new NumberFormatException("Percentages must end with %");
    }

    public static long parseTimestampUs(String arg8) {
        String[] v8 = Util.splitAtFirst(arg8, "\\.");
        int v0 = 0;
        String[] v1 = Util.split(v8[0], ":");
        int v2 = v1.length;
        long v3 = 0;
        while(v0 < v2) {
            v3 = v3 * 60 + Long.parseLong(v1[v0]);
            ++v0;
        }

        long v0_1 = 1000;
        v3 *= v0_1;
        if(v8.length == 2) {
            v3 += Long.parseLong(v8[1]);
        }

        return v3 * v0_1;
    }

    public static void validateWebvttHeaderLine(ParsableByteArray arg3) {
        String v3 = arg3.readLine();
        if(v3 != null && (WebvttParserUtil.HEADER.matcher(((CharSequence)v3)).matches())) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected WEBVTT. Got ");
        v1.append(v3);
        throw new SubtitleDecoderException(v1.toString());
    }
}

