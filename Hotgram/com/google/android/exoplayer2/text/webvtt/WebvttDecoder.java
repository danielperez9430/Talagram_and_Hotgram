package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

public final class WebvttDecoder extends SimpleSubtitleDecoder {
    private static final String COMMENT_START = "NOTE";
    private static final int EVENT_COMMENT = 1;
    private static final int EVENT_CUE = 3;
    private static final int EVENT_END_OF_FILE = 0;
    private static final int EVENT_NONE = -1;
    private static final int EVENT_STYLE_BLOCK = 2;
    private static final String STYLE_START = "STYLE";
    private final CssParser cssParser;
    private final WebvttCueParser cueParser;
    private final List definedStyles;
    private final ParsableByteArray parsableWebvttData;
    private final Builder webvttCueBuilder;

    public WebvttDecoder() {
        super("WebvttDecoder");
        this.cueParser = new WebvttCueParser();
        this.parsableWebvttData = new ParsableByteArray();
        this.webvttCueBuilder = new Builder();
        this.cssParser = new CssParser();
        this.definedStyles = new ArrayList();
    }

    protected Subtitle decode(byte[] arg1, int arg2, boolean arg3) {
        return this.decode(arg1, arg2, arg3);
    }

    protected WebvttSubtitle decode(byte[] arg3, int arg4, boolean arg5) {
        this.parsableWebvttData.reset(arg3, arg4);
        this.webvttCueBuilder.reset();
        this.definedStyles.clear();
        WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
        while(!TextUtils.isEmpty(this.parsableWebvttData.readLine())) {
        }

        ArrayList v3 = new ArrayList();
        while(true) {
            arg4 = WebvttDecoder.getNextEvent(this.parsableWebvttData);
            if(arg4 == 0) {
                break;
            }

            if(arg4 == 1) {
                WebvttDecoder.skipComment(this.parsableWebvttData);
                continue;
            }

            if(arg4 == 2) {
                if(v3.isEmpty()) {
                    this.parsableWebvttData.readLine();
                    WebvttCssStyle v4 = this.cssParser.parseBlock(this.parsableWebvttData);
                    if(v4 == null) {
                        continue;
                    }

                    this.definedStyles.add(v4);
                    continue;
                }

                throw new SubtitleDecoderException("A style block was found after the first cue.");
            }

            if(arg4 != 3) {
                continue;
            }

            if(!this.cueParser.parseCue(this.parsableWebvttData, this.webvttCueBuilder, this.definedStyles)) {
                continue;
            }

            v3.add(this.webvttCueBuilder.build());
            this.webvttCueBuilder.reset();
        }

        return new WebvttSubtitle(((List)v3));
    }

    private static int getNextEvent(ParsableByteArray arg5) {
        int v1 = -1;
        int v2 = -1;
        int v3 = 0;
        while(v2 == v1) {
            v3 = arg5.getPosition();
            String v2_1 = arg5.readLine();
            if(v2_1 == null) {
                v2 = 0;
                continue;
            }

            if("STYLE".equals(v2_1)) {
                v2 = 2;
                continue;
            }

            if("NOTE".startsWith(v2_1)) {
                v2 = 1;
                continue;
            }

            v2 = 3;
        }

        arg5.setPosition(v3);
        return v2;
    }

    private static void skipComment(ParsableByteArray arg1) {
        while(!TextUtils.isEmpty(arg1.readLine())) {
        }
    }
}

