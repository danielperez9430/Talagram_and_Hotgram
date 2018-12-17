package com.google.android.exoplayer2.text.ttml;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

final class TtmlNode {
    public static final String ANONYMOUS_REGION_ID = "";
    public static final String ATTR_ID = "id";
    public static final String ATTR_TTS_BACKGROUND_COLOR = "backgroundColor";
    public static final String ATTR_TTS_COLOR = "color";
    public static final String ATTR_TTS_DISPLAY_ALIGN = "displayAlign";
    public static final String ATTR_TTS_EXTENT = "extent";
    public static final String ATTR_TTS_FONT_FAMILY = "fontFamily";
    public static final String ATTR_TTS_FONT_SIZE = "fontSize";
    public static final String ATTR_TTS_FONT_STYLE = "fontStyle";
    public static final String ATTR_TTS_FONT_WEIGHT = "fontWeight";
    public static final String ATTR_TTS_ORIGIN = "origin";
    public static final String ATTR_TTS_TEXT_ALIGN = "textAlign";
    public static final String ATTR_TTS_TEXT_DECORATION = "textDecoration";
    public static final String BOLD = "bold";
    public static final String CENTER = "center";
    public static final String END = "end";
    public static final String ITALIC = "italic";
    public static final String LEFT = "left";
    public static final String LINETHROUGH = "linethrough";
    public static final String NO_LINETHROUGH = "nolinethrough";
    public static final String NO_UNDERLINE = "nounderline";
    public static final String RIGHT = "right";
    public static final String START = "start";
    public static final String TAG_BODY = "body";
    public static final String TAG_BR = "br";
    public static final String TAG_DIV = "div";
    public static final String TAG_HEAD = "head";
    public static final String TAG_LAYOUT = "layout";
    public static final String TAG_METADATA = "metadata";
    public static final String TAG_P = "p";
    public static final String TAG_REGION = "region";
    public static final String TAG_SMPTE_DATA = "smpte:data";
    public static final String TAG_SMPTE_IMAGE = "smpte:image";
    public static final String TAG_SMPTE_INFORMATION = "smpte:information";
    public static final String TAG_SPAN = "span";
    public static final String TAG_STYLE = "style";
    public static final String TAG_STYLING = "styling";
    public static final String TAG_TT = "tt";
    public static final String UNDERLINE = "underline";
    private List children;
    public final long endTimeUs;
    public final boolean isTextNode;
    private final HashMap nodeEndsByRegion;
    private final HashMap nodeStartsByRegion;
    public final String regionId;
    public final long startTimeUs;
    public final TtmlStyle style;
    private final String[] styleIds;
    public final String tag;
    public final String text;

    private TtmlNode(String arg1, String arg2, long arg3, long arg5, TtmlStyle arg7, String[] arg8, String arg9) {
        super();
        this.tag = arg1;
        this.text = arg2;
        this.style = arg7;
        this.styleIds = arg8;
        boolean v1 = arg2 != null ? true : false;
        this.isTextNode = v1;
        this.startTimeUs = arg3;
        this.endTimeUs = arg5;
        this.regionId = Assertions.checkNotNull(arg9);
        this.nodeStartsByRegion = new HashMap();
        this.nodeEndsByRegion = new HashMap();
    }

    public void addChild(TtmlNode arg2) {
        if(this.children == null) {
            this.children = new ArrayList();
        }

        this.children.add(arg2);
    }

    private void applyStyleToOutput(Map arg3, SpannableStringBuilder arg4, int arg5, int arg6) {
        TtmlStyle v3 = TtmlRenderUtil.resolveStyle(this.style, this.styleIds, arg3);
        if(v3 != null) {
            TtmlRenderUtil.applyStylesToSpan(arg4, arg5, arg6, v3);
        }
    }

    public static TtmlNode buildNode(String arg11, long arg12, long arg14, TtmlStyle arg16, String[] arg17, String arg18) {
        return new TtmlNode(arg11, null, arg12, arg14, arg16, arg17, arg18);
    }

    public static TtmlNode buildTextNode(String arg11) {
        return new TtmlNode(null, TtmlRenderUtil.applyTextElementSpacePolicy(arg11), -9223372036854775807L, -9223372036854775807L, null, null, "");
    }

    private SpannableStringBuilder cleanUpText(SpannableStringBuilder arg8) {
        int v4;
        int v1 = 0;
        int v2 = arg8.length();
        int v0;
        for(v0 = 0; true; ++v0) {
            int v3 = 32;
            if(v0 >= v2) {
                break;
            }

            if(arg8.charAt(v0) == v3) {
                v4 = v0 + 1;
                int v5;
                for(v5 = v4; v5 < arg8.length(); ++v5) {
                    if(arg8.charAt(v5) != v3) {
                        break;
                    }
                }

                v5 -= v4;
                if(v5 <= 0) {
                    goto label_21;
                }

                arg8.delete(v0, v0 + v5);
                v2 -= v5;
            }

        label_21:
        }

        if(v2 > 0 && arg8.charAt(0) == v3) {
            arg8.delete(0, 1);
            --v2;
        }

        for(v0 = 0; true; ++v0) {
            v4 = v2 - 1;
            v5 = 10;
            if(v0 >= v4) {
                break;
            }

            if(arg8.charAt(v0) == v5) {
                v4 = v0 + 1;
                if(arg8.charAt(v4) == v3) {
                    arg8.delete(v4, v0 + 2);
                    --v2;
                }
            }
        }

        if(v2 > 0 && arg8.charAt(v4) == v3) {
            arg8.delete(v4, v2);
            --v2;
        }

        while(true) {
            v0 = v2 - 1;
            if(v1 >= v0) {
                break;
            }

            if(arg8.charAt(v1) == v3) {
                v0 = v1 + 1;
                if(arg8.charAt(v0) == v5) {
                    arg8.delete(v1, v0);
                    --v2;
                }
            }

            ++v1;
        }

        if(v2 > 0 && arg8.charAt(v0) == v5) {
            arg8.delete(v0, v2);
        }

        return arg8;
    }

    public TtmlNode getChild(int arg2) {
        if(this.children != null) {
            return this.children.get(arg2);
        }

        throw new IndexOutOfBoundsException();
    }

    public int getChildCount() {
        int v0 = this.children == null ? 0 : this.children.size();
        return v0;
    }

    public List getCues(long arg19, Map arg21, Map arg22) {
        TtmlNode v6 = this;
        TreeMap v7 = new TreeMap();
        this.traverseForText(arg19, false, v6.regionId, v7);
        v6.traverseForStyle(arg19, arg21, ((Map)v7));
        ArrayList v0 = new ArrayList();
        Iterator v1 = v7.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            Object v3 = arg22.get(((Map$Entry)v2).getKey());
            ((List)v0).add(new Cue(v6.cleanUpText(((Map$Entry)v2).getValue()), null, ((TtmlRegion)v3).line, ((TtmlRegion)v3).lineType, ((TtmlRegion)v3).lineAnchor, ((TtmlRegion)v3).position, -2147483648, ((TtmlRegion)v3).width, ((TtmlRegion)v3).textSizeType, ((TtmlRegion)v3).textSize));
        }

        return ((List)v0);
    }

    private void getEventTimes(TreeSet arg7, boolean arg8) {
        boolean v0 = "p".equals(this.tag);
        if((arg8) || (v0)) {
            long v3 = -9223372036854775807L;
            if(this.startTimeUs != v3) {
                arg7.add(Long.valueOf(this.startTimeUs));
            }

            if(this.endTimeUs == v3) {
                goto label_16;
            }

            arg7.add(Long.valueOf(this.endTimeUs));
        }

    label_16:
        if(this.children == null) {
            return;
        }

        int v2;
        for(v2 = 0; v2 < this.children.size(); ++v2) {
            Object v3_1 = this.children.get(v2);
            boolean v4 = (arg8) || (v0) ? true : false;
            ((TtmlNode)v3_1).getEventTimes(arg7, v4);
        }
    }

    public long[] getEventTimesUs() {
        TreeSet v0 = new TreeSet();
        int v1 = 0;
        this.getEventTimes(v0, false);
        long[] v2 = new long[v0.size()];
        Iterator v0_1 = v0.iterator();
        while(v0_1.hasNext()) {
            v2[v1] = v0_1.next().longValue();
            ++v1;
        }

        return v2;
    }

    private static SpannableStringBuilder getRegionOutput(String arg1, Map arg2) {
        if(!arg2.containsKey(arg1)) {
            arg2.put(arg1, new SpannableStringBuilder());
        }

        return arg2.get(arg1);
    }

    public String[] getStyleIds() {
        return this.styleIds;
    }

    public boolean isActive(long arg6) {
        boolean v6;
        long v2 = -9223372036854775807L;
        if(this.startTimeUs != v2 || this.endTimeUs != v2) {
            if(this.startTimeUs <= arg6 && this.endTimeUs == v2) {
                goto label_17;
            }

            if(this.startTimeUs == v2 && arg6 < this.endTimeUs) {
                goto label_17;
            }

            if(this.startTimeUs <= arg6 && arg6 < this.endTimeUs) {
            label_17:
                v6 = true;
                return v6;
            }

            v6 = false;
        }
        else {
            goto label_17;
        }

        return v6;
    }

    private void traverseForStyle(long arg6, Map arg8, Map arg9) {
        int v2;
        if(!this.isActive(arg6)) {
            return;
        }

        Iterator v0 = this.nodeEndsByRegion.entrySet().iterator();
        while(true) {
            v2 = 0;
            if(v0.hasNext()) {
                Object v1 = v0.next();
                Object v3 = ((Map$Entry)v1).getKey();
                if(this.nodeStartsByRegion.containsKey(v3)) {
                    v2 = this.nodeStartsByRegion.get(v3).intValue();
                }

                int v1_1 = ((Map$Entry)v1).getValue().intValue();
                if(v2 == v1_1) {
                    continue;
                }

                this.applyStyleToOutput(arg8, arg9.get(v3), v2, v1_1);
                continue;
            }

            break;
        }

        while(v2 < this.getChildCount()) {
            this.getChild(v2).traverseForStyle(arg6, arg8, arg9);
            ++v2;
        }
    }

    private void traverseForText(long arg10, boolean arg12, String arg13, Map arg14) {
        this.nodeStartsByRegion.clear();
        this.nodeEndsByRegion.clear();
        if("metadata".equals(this.tag)) {
            return;
        }

        if("".equals(this.regionId)) {
        }
        else {
            arg13 = this.regionId;
        }

        if(!this.isTextNode || !arg12) {
            if(("br".equals(this.tag)) && (arg12)) {
                TtmlNode.getRegionOutput(arg13, arg14).append('\n');
                return;
            }

            if(!this.isActive(arg10)) {
                return;
            }

            Iterator v0 = arg14.entrySet().iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                this.nodeStartsByRegion.put(((Map$Entry)v1).getKey(), Integer.valueOf(((Map$Entry)v1).getValue().length()));
            }

            boolean v6 = "p".equals(this.tag);
            int v8;
            for(v8 = 0; v8 < this.getChildCount(); ++v8) {
                TtmlNode v0_1 = this.getChild(v8);
                boolean v3 = (arg12) || (v6) ? true : false;
                v0_1.traverseForText(arg10, v3, arg13, arg14);
            }

            if(v6) {
                TtmlRenderUtil.endParagraph(TtmlNode.getRegionOutput(arg13, arg14));
            }

            Iterator v10 = arg14.entrySet().iterator();
            while(v10.hasNext()) {
                Object v11 = v10.next();
                this.nodeEndsByRegion.put(((Map$Entry)v11).getKey(), Integer.valueOf(((Map$Entry)v11).getValue().length()));
            }
        }
        else {
            TtmlNode.getRegionOutput(arg13, arg14).append(this.text);
        }
    }
}

