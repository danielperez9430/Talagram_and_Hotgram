package com.google.android.exoplayer2.text.ttml;

import android.text.Layout$Alignment;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

final class TtmlStyle {
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
    private String id;
    private TtmlStyle inheritableStyle;
    private int italic;
    private int linethrough;
    private Layout$Alignment textAlign;
    private int underline;

    public TtmlStyle() {
        super();
        this.linethrough = -1;
        this.underline = -1;
        this.bold = -1;
        this.italic = -1;
        this.fontSizeUnit = -1;
    }

    public TtmlStyle chain(TtmlStyle arg2) {
        return this.inherit(arg2, true);
    }

    public int getBackgroundColor() {
        if(this.hasBackgroundColor) {
            return this.backgroundColor;
        }

        throw new IllegalStateException("Background color has not been defined.");
    }

    public int getFontColor() {
        if(this.hasFontColor) {
            return this.fontColor;
        }

        throw new IllegalStateException("Font color has not been defined.");
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

    public String getId() {
        return this.id;
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

    private TtmlStyle inherit(TtmlStyle arg3, boolean arg4) {
        if(arg3 != null) {
            if(!this.hasFontColor && (arg3.hasFontColor)) {
                this.setFontColor(arg3.fontColor);
            }

            int v1 = -1;
            if(this.bold == v1) {
                this.bold = arg3.bold;
            }

            if(this.italic == v1) {
                this.italic = arg3.italic;
            }

            if(this.fontFamily == null) {
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

            if(!arg4) {
                return this;
            }

            if(this.hasBackgroundColor) {
                return this;
            }

            if(!arg3.hasBackgroundColor) {
                return this;
            }

            this.setBackgroundColor(arg3.backgroundColor);
        }

        return this;
    }

    public TtmlStyle inherit(TtmlStyle arg2) {
        return this.inherit(arg2, false);
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

    public TtmlStyle setBackgroundColor(int arg1) {
        this.backgroundColor = arg1;
        this.hasBackgroundColor = true;
        return this;
    }

    public TtmlStyle setBold(boolean arg2) {
        boolean v0 = this.inheritableStyle == null ? true : false;
        Assertions.checkState(v0);
        this.bold = ((int)arg2);
        return this;
    }

    public TtmlStyle setFontColor(int arg3) {
        boolean v0 = this.inheritableStyle == null ? true : false;
        Assertions.checkState(v0);
        this.fontColor = arg3;
        this.hasFontColor = true;
        return this;
    }

    public TtmlStyle setFontFamily(String arg2) {
        boolean v0 = this.inheritableStyle == null ? true : false;
        Assertions.checkState(v0);
        this.fontFamily = arg2;
        return this;
    }

    public TtmlStyle setFontSize(float arg1) {
        this.fontSize = arg1;
        return this;
    }

    public TtmlStyle setFontSizeUnit(int arg1) {
        this.fontSizeUnit = arg1;
        return this;
    }

    public TtmlStyle setId(String arg1) {
        this.id = arg1;
        return this;
    }

    public TtmlStyle setItalic(boolean arg2) {
        boolean v0 = this.inheritableStyle == null ? true : false;
        Assertions.checkState(v0);
        this.italic = ((int)arg2);
        return this;
    }

    public TtmlStyle setLinethrough(boolean arg2) {
        boolean v0 = this.inheritableStyle == null ? true : false;
        Assertions.checkState(v0);
        this.linethrough = ((int)arg2);
        return this;
    }

    public TtmlStyle setTextAlign(Layout$Alignment arg1) {
        this.textAlign = arg1;
        return this;
    }

    public TtmlStyle setUnderline(boolean arg2) {
        boolean v0 = this.inheritableStyle == null ? true : false;
        Assertions.checkState(v0);
        this.underline = ((int)arg2);
        return this;
    }
}

