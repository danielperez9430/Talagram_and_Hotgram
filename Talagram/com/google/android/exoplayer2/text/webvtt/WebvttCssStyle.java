package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout$Alignment;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class WebvttCssStyle {
    @Retention(value=RetentionPolicy.SOURCE) @public interface FontSizeUnit {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface OptionalBoolean {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface StyleFlags {
    }

    public static final int FONT_SIZE_UNIT_EM = 2;
    public static final int FONT_SIZE_UNIT_PERCENT = 3;
    public static final int FONT_SIZE_UNIT_PIXEL = 1;
    private static final int OFF = 0;
    private static final int ON = 1;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_BOLD_ITALIC = 3;
    public static final int STYLE_ITALIC = 2;
    public static final int STYLE_NORMAL = 0;
    public static final int UNSPECIFIED = -1;
    private int backgroundColor;
    private int bold;
    private int fontColor;
    private String fontFamily;
    private float fontSize;
    private int fontSizeUnit;
    private boolean hasBackgroundColor;
    private boolean hasFontColor;
    private int italic;
    private int linethrough;
    private List targetClasses;
    private String targetId;
    private String targetTag;
    private String targetVoice;
    private Layout$Alignment textAlign;
    private int underline;

    public WebvttCssStyle() {
        super();
        this.reset();
    }

    public void cascadeFrom(WebvttCssStyle arg3) {
        if(arg3.hasFontColor) {
            this.setFontColor(arg3.fontColor);
        }

        int v1 = -1;
        if(arg3.bold != v1) {
            this.bold = arg3.bold;
        }

        if(arg3.italic != v1) {
            this.italic = arg3.italic;
        }

        if(arg3.fontFamily != null) {
            this.fontFamily = arg3.fontFamily;
        }

        if(this.linethrough == v1) {
            this.linethrough = arg3.linethrough;
        }

        if(this.underline == v1) {
            this.underline = arg3.underline;
        }

        if(this.textAlign == null) {
            this.textAlign = arg3.textAlign;
        }

        if(this.fontSizeUnit == v1) {
            this.fontSizeUnit = arg3.fontSizeUnit;
            this.fontSize = arg3.fontSize;
        }

        if(arg3.hasBackgroundColor) {
            this.setBackgroundColor(arg3.backgroundColor);
        }
    }

    public int getBackgroundColor() {
        if(this.hasBackgroundColor) {
            return this.backgroundColor;
        }

        throw new IllegalStateException("Background color not defined.");
    }

    public int getFontColor() {
        if(this.hasFontColor) {
            return this.fontColor;
        }

        throw new IllegalStateException("Font color not defined");
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public int getFontSizeUnit() {
        return this.fontSizeUnit;
    }

    public int getSpecificityScore(String arg4, String arg5, String[] arg6, String arg7) {
        if((this.targetId.isEmpty()) && (this.targetTag.isEmpty()) && (this.targetClasses.isEmpty()) && (this.targetVoice.isEmpty())) {
            return arg5.isEmpty();
        }

        int v4 = WebvttCssStyle.updateScoreForMatch(WebvttCssStyle.updateScoreForMatch(WebvttCssStyle.updateScoreForMatch(0, this.targetId, arg4, 1073741824), this.targetTag, arg5, 2), this.targetVoice, arg7, 4);
        if(v4 != -1) {
            if(!Arrays.asList(((Object[])arg6)).containsAll(this.targetClasses)) {
            }
            else {
                return v4 + this.targetClasses.size() * 4;
            }
        }

        return 0;
    }

    public int getStyle() {
        int v1 = -1;
        if(this.bold == v1 && this.italic == v1) {
            return v1;
        }

        v1 = 0;
        int v0 = this.bold == 1 ? 1 : 0;
        if(this.italic == 1) {
            v1 = 2;
        }

        return v0 | v1;
    }

    public Layout$Alignment getTextAlign() {
        return this.textAlign;
    }

    public boolean hasBackgroundColor() {
        return this.hasBackgroundColor;
    }

    public boolean hasFontColor() {
        return this.hasFontColor;
    }

    public boolean isLinethrough() {
        boolean v1 = true;
        if(this.linethrough == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean isUnderline() {
        boolean v1 = true;
        if(this.underline == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public void reset() {
        this.targetId = "";
        this.targetTag = "";
        this.targetClasses = Collections.emptyList();
        this.targetVoice = "";
        this.fontFamily = null;
        this.hasFontColor = false;
        this.hasBackgroundColor = false;
        this.linethrough = -1;
        this.underline = -1;
        this.bold = -1;
        this.italic = -1;
        this.fontSizeUnit = -1;
        this.textAlign = null;
    }

    public WebvttCssStyle setBackgroundColor(int arg1) {
        this.backgroundColor = arg1;
        this.hasBackgroundColor = true;
        return this;
    }

    public WebvttCssStyle setBold(boolean arg1) {
        this.bold = ((int)arg1);
        return this;
    }

    public WebvttCssStyle setFontColor(int arg1) {
        this.fontColor = arg1;
        this.hasFontColor = true;
        return this;
    }

    public WebvttCssStyle setFontFamily(String arg1) {
        this.fontFamily = Util.toLowerInvariant(arg1);
        return this;
    }

    public WebvttCssStyle setFontSize(float arg1) {
        this.fontSize = arg1;
        return this;
    }

    public WebvttCssStyle setFontSizeUnit(short arg1) {
        this.fontSizeUnit = arg1;
        return this;
    }

    public WebvttCssStyle setItalic(boolean arg1) {
        this.italic = ((int)arg1);
        return this;
    }

    public WebvttCssStyle setLinethrough(boolean arg1) {
        this.linethrough = ((int)arg1);
        return this;
    }

    public void setTargetClasses(String[] arg1) {
        this.targetClasses = Arrays.asList(((Object[])arg1));
    }

    public void setTargetId(String arg1) {
        this.targetId = arg1;
    }

    public void setTargetTagName(String arg1) {
        this.targetTag = arg1;
    }

    public void setTargetVoice(String arg1) {
        this.targetVoice = arg1;
    }

    public WebvttCssStyle setTextAlign(Layout$Alignment arg1) {
        this.textAlign = arg1;
        return this;
    }

    public WebvttCssStyle setUnderline(boolean arg1) {
        this.underline = ((int)arg1);
        return this;
    }

    private static int updateScoreForMatch(int arg1, String arg2, String arg3, int arg4) {
        if(!arg2.isEmpty()) {
            int v0 = -1;
            if(arg1 == v0) {
            }
            else {
                if(arg2.equals(arg3)) {
                    v0 = arg1 + arg4;
                }

                return v0;
            }
        }

        return arg1;
    }
}

