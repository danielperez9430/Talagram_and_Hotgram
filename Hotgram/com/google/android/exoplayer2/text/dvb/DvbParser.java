package com.google.android.exoplayer2.text.dvb;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region$Op;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class DvbParser {
    final class ClutDefinition {
        public final int[] clutEntries2Bit;
        public final int[] clutEntries4Bit;
        public final int[] clutEntries8Bit;
        public final int id;

        public ClutDefinition(int arg1, int[] arg2, int[] arg3, int[] arg4) {
            super();
            this.id = arg1;
            this.clutEntries2Bit = arg2;
            this.clutEntries4Bit = arg3;
            this.clutEntries8Bit = arg4;
        }
    }

    final class DisplayDefinition {
        public final int height;
        public final int horizontalPositionMaximum;
        public final int horizontalPositionMinimum;
        public final int verticalPositionMaximum;
        public final int verticalPositionMinimum;
        public final int width;

        public DisplayDefinition(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
            super();
            this.width = arg1;
            this.height = arg2;
            this.horizontalPositionMinimum = arg3;
            this.horizontalPositionMaximum = arg4;
            this.verticalPositionMinimum = arg5;
            this.verticalPositionMaximum = arg6;
        }
    }

    final class ObjectData {
        public final byte[] bottomFieldData;
        public final int id;
        public final boolean nonModifyingColorFlag;
        public final byte[] topFieldData;

        public ObjectData(int arg1, boolean arg2, byte[] arg3, byte[] arg4) {
            super();
            this.id = arg1;
            this.nonModifyingColorFlag = arg2;
            this.topFieldData = arg3;
            this.bottomFieldData = arg4;
        }
    }

    final class PageComposition {
        public final SparseArray regions;
        public final int state;
        public final int timeOutSecs;
        public final int version;

        public PageComposition(int arg1, int arg2, int arg3, SparseArray arg4) {
            super();
            this.timeOutSecs = arg1;
            this.version = arg2;
            this.state = arg3;
            this.regions = arg4;
        }
    }

    final class PageRegion {
        public final int horizontalAddress;
        public final int verticalAddress;

        public PageRegion(int arg1, int arg2) {
            super();
            this.horizontalAddress = arg1;
            this.verticalAddress = arg2;
        }
    }

    final class RegionComposition {
        public final int clutId;
        public final int depth;
        public final boolean fillFlag;
        public final int height;
        public final int id;
        public final int levelOfCompatibility;
        public final int pixelCode2Bit;
        public final int pixelCode4Bit;
        public final int pixelCode8Bit;
        public final SparseArray regionObjects;
        public final int width;

        public RegionComposition(int arg1, boolean arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, SparseArray arg11) {
            super();
            this.id = arg1;
            this.fillFlag = arg2;
            this.width = arg3;
            this.height = arg4;
            this.levelOfCompatibility = arg5;
            this.depth = arg6;
            this.clutId = arg7;
            this.pixelCode8Bit = arg8;
            this.pixelCode4Bit = arg9;
            this.pixelCode2Bit = arg10;
            this.regionObjects = arg11;
        }

        public void mergeFrom(RegionComposition arg5) {
            if(arg5 == null) {
                return;
            }

            SparseArray v5 = arg5.regionObjects;
            int v0;
            for(v0 = 0; v0 < v5.size(); ++v0) {
                this.regionObjects.put(v5.keyAt(v0), v5.valueAt(v0));
            }
        }
    }

    final class RegionObject {
        public final int backgroundPixelCode;
        public final int foregroundPixelCode;
        public final int horizontalPosition;
        public final int provider;
        public final int type;
        public final int verticalPosition;

        public RegionObject(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
            super();
            this.type = arg1;
            this.provider = arg2;
            this.horizontalPosition = arg3;
            this.verticalPosition = arg4;
            this.foregroundPixelCode = arg5;
            this.backgroundPixelCode = arg6;
        }
    }

    final class SubtitleService {
        public final SparseArray ancillaryCluts;
        public final SparseArray ancillaryObjects;
        public final int ancillaryPageId;
        public final SparseArray cluts;
        public DisplayDefinition displayDefinition;
        public final SparseArray objects;
        public PageComposition pageComposition;
        public final SparseArray regions;
        public final int subtitlePageId;

        public SubtitleService(int arg2, int arg3) {
            super();
            this.regions = new SparseArray();
            this.cluts = new SparseArray();
            this.objects = new SparseArray();
            this.ancillaryCluts = new SparseArray();
            this.ancillaryObjects = new SparseArray();
            this.subtitlePageId = arg2;
            this.ancillaryPageId = arg3;
        }

        public void reset() {
            this.regions.clear();
            this.cluts.clear();
            this.objects.clear();
            this.ancillaryCluts.clear();
            this.ancillaryObjects.clear();
            this.displayDefinition = null;
            this.pageComposition = null;
        }
    }

    private static final int DATA_TYPE_24_TABLE_DATA = 32;
    private static final int DATA_TYPE_28_TABLE_DATA = 33;
    private static final int DATA_TYPE_2BP_CODE_STRING = 16;
    private static final int DATA_TYPE_48_TABLE_DATA = 34;
    private static final int DATA_TYPE_4BP_CODE_STRING = 17;
    private static final int DATA_TYPE_8BP_CODE_STRING = 18;
    private static final int DATA_TYPE_END_LINE = 240;
    private static final int OBJECT_CODING_PIXELS = 0;
    private static final int OBJECT_CODING_STRING = 1;
    private static final int PAGE_STATE_NORMAL = 0;
    private static final int REGION_DEPTH_4_BIT = 2;
    private static final int REGION_DEPTH_8_BIT = 3;
    private static final int SEGMENT_TYPE_CLUT_DEFINITION = 18;
    private static final int SEGMENT_TYPE_DISPLAY_DEFINITION = 20;
    private static final int SEGMENT_TYPE_OBJECT_DATA = 19;
    private static final int SEGMENT_TYPE_PAGE_COMPOSITION = 16;
    private static final int SEGMENT_TYPE_REGION_COMPOSITION = 17;
    private static final String TAG = "DvbParser";
    private Bitmap bitmap;
    private final Canvas canvas;
    private final ClutDefinition defaultClutDefinition;
    private final DisplayDefinition defaultDisplayDefinition;
    private static final byte[] defaultMap2To4;
    private static final byte[] defaultMap2To8;
    private static final byte[] defaultMap4To8;
    private final Paint defaultPaint;
    private final Paint fillRegionPaint;
    private final SubtitleService subtitleService;

    static {
        DvbParser.defaultMap2To4 = new byte[]{0, 7, 8, 15};
        DvbParser.defaultMap2To8 = new byte[]{0, 119, -120, -1};
        DvbParser.defaultMap4To8 = new byte[]{0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    }

    public DvbParser(int arg9, int arg10) {
        super();
        this.defaultPaint = new Paint();
        this.defaultPaint.setStyle(Paint$Style.FILL_AND_STROKE);
        this.defaultPaint.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.SRC));
        this.defaultPaint.setPathEffect(null);
        this.fillRegionPaint = new Paint();
        this.fillRegionPaint.setStyle(Paint$Style.FILL);
        this.fillRegionPaint.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.DST_OVER));
        this.fillRegionPaint.setPathEffect(null);
        this.canvas = new Canvas();
        this.defaultDisplayDefinition = new DisplayDefinition(719, 575, 0, 719, 0, 575);
        this.defaultClutDefinition = new ClutDefinition(0, DvbParser.generateDefault2BitClutEntries(), DvbParser.generateDefault4BitClutEntries(), DvbParser.generateDefault8BitClutEntries());
        this.subtitleService = new SubtitleService(arg9, arg10);
    }

    private static byte[] buildClutMapTable(int arg3, int arg4, ParsableBitArray arg5) {
        byte[] v0 = new byte[arg3];
        int v1;
        for(v1 = 0; v1 < arg3; ++v1) {
            v0[v1] = ((byte)arg5.readBits(arg4));
        }

        return v0;
    }

    public List decode(byte[] arg28, int arg29) {
        int v4_1;
        SparseArray v4;
        int v20;
        DvbParser v0 = this;
        ParsableBitArray v1 = new ParsableBitArray(arg28, arg29);
        while(v1.bitsLeft() >= 48) {
            if(v1.readBits(8) != 15) {
                break;
            }

            DvbParser.parseSubtitlingSegment(v1, v0.subtitleService);
        }

        if(v0.subtitleService.pageComposition == null) {
            return Collections.emptyList();
        }

        DisplayDefinition v1_1 = v0.subtitleService.displayDefinition != null ? v0.subtitleService.displayDefinition : v0.defaultDisplayDefinition;
        if(v0.bitmap == null || v1_1.width + 1 != v0.bitmap.getWidth() || v1_1.height + 1 != v0.bitmap.getHeight()) {
            v0.bitmap = Bitmap.createBitmap(v1_1.width + 1, v1_1.height + 1, Bitmap$Config.ARGB_8888);
            v0.canvas.setBitmap(v0.bitmap);
        }

        ArrayList v2 = new ArrayList();
        SparseArray v3 = v0.subtitleService.pageComposition.regions;
        int v5;
        for(v5 = 0; v5 < v3.size(); ++v5) {
            Object v6 = v3.valueAt(v5);
            Object v7 = v0.subtitleService.regions.get(v3.keyAt(v5));
            int v8 = ((PageRegion)v6).horizontalAddress + v1_1.horizontalPositionMinimum;
            int v6_1 = ((PageRegion)v6).verticalAddress + v1_1.verticalPositionMinimum;
            float v15 = ((float)v8);
            float v14 = ((float)v6_1);
            float v18 = v14;
            float v9 = v15;
            v0.canvas.clipRect(v15, v14, ((float)Math.min(((RegionComposition)v7).width + v8, v1_1.horizontalPositionMaximum)), ((float)Math.min(((RegionComposition)v7).height + v6_1, v1_1.verticalPositionMaximum)), Region$Op.REPLACE);
            Object v10 = v0.subtitleService.cluts.get(((RegionComposition)v7).clutId);
            if(v10 == null) {
                v10 = v0.subtitleService.ancillaryCluts.get(((RegionComposition)v7).clutId);
                if(v10 == null) {
                    ClutDefinition v10_1 = v0.defaultClutDefinition;
                }
            }

            SparseArray v15_1 = ((RegionComposition)v7).regionObjects;
            int v14_1 = 0;
            while(v14_1 < v15_1.size()) {
                int v11 = v15_1.keyAt(v14_1);
                Object v12 = v15_1.valueAt(v14_1);
                Object v13 = v0.subtitleService.objects.get(v11);
                Object v11_1 = v13 == null ? v0.subtitleService.ancillaryObjects.get(v11) : v13;
                if(v11_1 != null) {
                    Paint v13_1 = ((ObjectData)v11_1).nonModifyingColorFlag ? null : v0.defaultPaint;
                    Paint v16 = v13_1;
                    v20 = v14_1;
                    v4 = v15_1;
                    DvbParser.paintPixelDataSubBlocks(((ObjectData)v11_1), v10, ((RegionComposition)v7).depth, ((RegionObject)v12).horizontalPosition + v8, v6_1 + ((RegionObject)v12).verticalPosition, v16, v0.canvas);
                }
                else {
                    v20 = v14_1;
                    v4 = v15_1;
                }

                v14_1 = v20 + 1;
                v15_1 = v4;
            }

            if(((RegionComposition)v7).fillFlag) {
                if(((RegionComposition)v7).depth == 3) {
                    v4_1 = ((ClutDefinition)v10).clutEntries8Bit[((RegionComposition)v7).pixelCode8Bit];
                }
                else if(((RegionComposition)v7).depth == 2) {
                    v4_1 = ((ClutDefinition)v10).clutEntries4Bit[((RegionComposition)v7).pixelCode4Bit];
                }
                else {
                    v4_1 = ((ClutDefinition)v10).clutEntries2Bit[((RegionComposition)v7).pixelCode2Bit];
                }

                v0.fillRegionPaint.setColor(v4_1);
                v0.canvas.drawRect(v9, v18, ((float)(((RegionComposition)v7).width + v8)), ((float)(((RegionComposition)v7).height + v6_1)), v0.fillRegionPaint);
            }

            ((List)v2).add(new Cue(Bitmap.createBitmap(v0.bitmap, v8, v6_1, ((RegionComposition)v7).width, ((RegionComposition)v7).height), v9 / (((float)v1_1.width)), 0, v18 / (((float)v1_1.height)), 0, (((float)((RegionComposition)v7).width)) / (((float)v1_1.width)), (((float)((RegionComposition)v7).height)) / (((float)v1_1.height))));
            v0.canvas.drawColor(0, PorterDuff$Mode.CLEAR);
        }

        return ((List)v2);
    }

    private static int[] generateDefault2BitClutEntries() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static int[] generateDefault4BitClutEntries() {
        int v6;
        int v5;
        int v3;
        int[] v0 = new int[16];
        v0[0] = 0;
        int v2;
        for(v2 = 1; v2 < v0.length; ++v2) {
            int v4 = 255;
            if(v2 < 8) {
                v3 = (v2 & 1) != 0 ? 255 : 0;
                v5 = (v2 & 2) != 0 ? 255 : 0;
                v6 = (v2 & 4) != 0 ? 255 : 0;
                v0[v2] = DvbParser.getColor(v4, v3, v5, v6);
            }
            else {
                v5 = 127;
                v3 = (v2 & 1) != 0 ? 127 : 0;
                v6 = (v2 & 2) != 0 ? 127 : 0;
                if((v2 & 4) != 0) {
                }
                else {
                    v5 = 0;
                }

                v0[v2] = DvbParser.getColor(v4, v3, v6, v5);
            }
        }

        return v0;
    }

    private static int[] generateDefault8BitClutEntries() {
        int v8;
        int v6;
        int v5;
        int[] v0 = new int[256];
        v0[0] = 0;
        int v2;
        for(v2 = 0; v2 < v0.length; ++v2) {
            int v3 = 8;
            int v4 = 255;
            if(v2 < v3) {
                v3 = 63;
                v5 = (v2 & 1) != 0 ? 255 : 0;
                v6 = (v2 & 2) != 0 ? 255 : 0;
                if((v2 & 4) != 0) {
                }
                else {
                    v4 = 0;
                }

                v0[v2] = DvbParser.getColor(v3, v5, v6, v4);
            }
            else {
                v5 = v2 & 136;
                v6 = 170;
                int v7 = 85;
                if(v5 != 0) {
                    v8 = 127;
                    if(v5 != v3) {
                        v6 = 43;
                        if(v5 != 128) {
                            if(v5 != 136) {
                                goto label_178;
                            }

                            v3 = (v2 & 1) != 0 ? 43 : 0;
                            v5 = (v2 & 16) != 0 ? 85 : 0;
                            v3 += v5;
                            v5 = (v2 & 2) != 0 ? 43 : 0;
                            v8 = (v2 & 32) != 0 ? 85 : 0;
                            v5 += v8;
                            if((v2 & 4) != 0) {
                            }
                            else {
                                v6 = 0;
                            }

                            if((v2 & 64) != 0) {
                            }
                            else {
                                v7 = 0;
                            }

                            v0[v2] = DvbParser.getColor(v4, v3, v5, v6 + v7);
                            goto label_178;
                        }

                        v3 = (v2 & 1) != 0 ? 43 : 0;
                        v3 += v8;
                        v5 = (v2 & 16) != 0 ? 85 : 0;
                        v3 += v5;
                        v5 = (v2 & 2) != 0 ? 43 : 0;
                        v5 += v8;
                        int v9 = (v2 & 32) != 0 ? 85 : 0;
                        v5 += v9;
                        if((v2 & 4) != 0) {
                        }
                        else {
                            v6 = 0;
                        }

                        v6 += v8;
                        if((v2 & 64) != 0) {
                        }
                        else {
                            v7 = 0;
                        }

                        v0[v2] = DvbParser.getColor(v4, v3, v5, v6 + v7);
                        goto label_178;
                    }

                    v3 = (v2 & 1) != 0 ? 85 : 0;
                    v4 = (v2 & 16) != 0 ? 170 : 0;
                    v3 += v4;
                    v4 = (v2 & 2) != 0 ? 85 : 0;
                    v5 = (v2 & 32) != 0 ? 170 : 0;
                    v4 += v5;
                    if((v2 & 4) != 0) {
                    }
                    else {
                        v7 = 0;
                    }

                    if((v2 & 64) != 0) {
                    }
                    else {
                        v6 = 0;
                    }

                    v0[v2] = DvbParser.getColor(v8, v3, v4, v7 + v6);
                    goto label_178;
                }

                v3 = (v2 & 1) != 0 ? 85 : 0;
                v5 = (v2 & 16) != 0 ? 170 : 0;
                v3 += v5;
                v5 = (v2 & 2) != 0 ? 85 : 0;
                v8 = (v2 & 32) != 0 ? 170 : 0;
                v5 += v8;
                if((v2 & 4) != 0) {
                }
                else {
                    v7 = 0;
                }

                if((v2 & 64) != 0) {
                }
                else {
                    v6 = 0;
                }

                v0[v2] = DvbParser.getColor(v4, v3, v5, v7 + v6);
            }

        label_178:
        }

        return v0;
    }

    private static int getColor(int arg0, int arg1, int arg2, int arg3) {
        return arg0 << 24 | arg1 << 16 | arg2 << 8 | arg3;
    }

    private static int paint2BitPixelCodeString(ParsableBitArray arg13, int[] arg14, byte[] arg15, int arg16, int arg17, Paint arg18, Canvas arg19) {
        int v11;
        int v12;
        int v1 = arg17;
        Paint v8 = arg18;
        int v10 = arg16;
        int v2;
        for(v2 = 0; true; v2 = v12) {
            int v3 = 2;
            int v4 = arg13.readBits(v3);
            if(v4 != 0) {
                v12 = v2;
                v3 = v4;
                goto label_12;
            }
            else {
                if(arg13.readBit()) {
                    v4 = 3 + arg13.readBits(3);
                }
                else if(arg13.readBit()) {
                    v12 = v2;
                    v3 = 0;
                label_12:
                    v11 = 1;
                    goto label_49;
                }
                else {
                    switch(arg13.readBits(v3)) {
                        case 0: {
                            goto label_46;
                        }
                        case 1: {
                            goto label_42;
                        }
                        case 2: {
                            goto label_38;
                        }
                        case 3: {
                            goto label_34;
                        }
                    }

                    v12 = v2;
                    v3 = 0;
                    v11 = 0;
                    goto label_49;
                label_34:
                    v4 = arg13.readBits(8) + 29;
                    goto label_19;
                label_38:
                    v4 = arg13.readBits(4) + 12;
                }

            label_19:
                v3 = arg13.readBits(v3);
                v12 = v2;
                v11 = v4;
                goto label_49;
            label_42:
                v12 = v2;
                v3 = 0;
                v11 = 2;
                goto label_49;
            label_46:
                v3 = 0;
                v11 = 0;
                v12 = 1;
            }

        label_49:
            if(v11 != 0 && v8 != null) {
                if(arg15 != null) {
                    v3 = arg15[v3];
                }

                v8.setColor(arg14[v3]);
                arg19.drawRect(((float)v10), ((float)v1), ((float)(v10 + v11)), ((float)(v1 + 1)), arg18);
            }

            v10 += v11;
            if(v12 != 0) {
                return v10;
            }
        }
    }

    private static int paint4BitPixelCodeString(ParsableBitArray arg13, int[] arg14, byte[] arg15, int arg16, int arg17, Paint arg18, Canvas arg19) {
        int v11;
        int v12;
        int v1 = arg17;
        Paint v8 = arg18;
        int v10 = arg16;
        int v2;
        for(v2 = 0; true; v2 = v12) {
            int v3 = 4;
            int v4 = arg13.readBits(v3);
            int v5 = 2;
            if(v4 != 0) {
                v12 = v2;
                v3 = v4;
                goto label_13;
            }
            else if(!arg13.readBit()) {
                v3 = arg13.readBits(3);
                if(v3 != 0) {
                    v12 = v2;
                    v11 = v3 + 2;
                    v3 = 0;
                }
                else {
                    v3 = 0;
                    v11 = 0;
                    v12 = 1;
                }
            }
            else {
                if(!arg13.readBit()) {
                    v4 = arg13.readBits(v5) + v3;
                }
                else {
                    switch(arg13.readBits(v5)) {
                        case 0: {
                            goto label_54;
                        }
                        case 1: {
                            goto label_50;
                        }
                        case 2: {
                            goto label_47;
                        }
                        case 3: {
                            goto label_43;
                        }
                    }

                    v12 = v2;
                    v3 = 0;
                    v11 = 0;
                    goto label_57;
                label_50:
                    v12 = v2;
                    v3 = 0;
                    v11 = 2;
                    goto label_57;
                label_54:
                    v12 = v2;
                    v3 = 0;
                label_13:
                    v11 = 1;
                    goto label_57;
                label_43:
                    v4 = arg13.readBits(8) + 25;
                    goto label_33;
                label_47:
                    v4 = arg13.readBits(v3) + 9;
                }

            label_33:
                v3 = arg13.readBits(v3);
                v12 = v2;
                v11 = v4;
            }

        label_57:
            if(v11 != 0 && v8 != null) {
                if(arg15 != null) {
                    v3 = arg15[v3];
                }

                v8.setColor(arg14[v3]);
                arg19.drawRect(((float)v10), ((float)v1), ((float)(v10 + v11)), ((float)(v1 + 1)), arg18);
            }

            v10 += v11;
            if(v12 != 0) {
                return v10;
            }
        }
    }

    private static int paint8BitPixelCodeString(ParsableBitArray arg13, int[] arg14, byte[] arg15, int arg16, int arg17, Paint arg18, Canvas arg19) {
        int v11;
        int v12;
        int v1 = arg17;
        Paint v8 = arg18;
        int v10 = arg16;
        int v2;
        for(v2 = 0; true; v2 = v12) {
            int v3 = 8;
            int v4 = arg13.readBits(v3);
            if(v4 != 0) {
                v12 = v2;
                v3 = v4;
                v11 = 1;
            }
            else {
                int v6 = 7;
                if(!arg13.readBit()) {
                    v3 = arg13.readBits(v6);
                    if(v3 != 0) {
                        v12 = v2;
                        v11 = v3;
                        v3 = 0;
                    }
                    else {
                        v3 = 0;
                        v11 = 0;
                        v12 = 1;
                    }
                }
                else {
                    v4 = arg13.readBits(v6);
                    v3 = arg13.readBits(v3);
                    v12 = v2;
                    v11 = v4;
                }
            }

            if(v11 != 0 && v8 != null) {
                if(arg15 != null) {
                    v3 = arg15[v3];
                }

                v8.setColor(arg14[v3]);
                arg19.drawRect(((float)v10), ((float)v1), ((float)(v10 + v11)), ((float)(v1 + 1)), arg18);
            }

            v10 += v11;
            if(v12 != 0) {
                return v10;
            }
        }
    }

    private static void paintPixelDataSubBlock(byte[] arg13, int[] arg14, int arg15, int arg16, int arg17, Paint arg18, Canvas arg19) {
        byte[] v3_1;
        int v0 = arg15;
        ParsableBitArray v8 = new ParsableBitArray(arg13);
        byte[] v9 = null;
        int v4 = arg16;
        int v10 = arg17;
        byte[] v11 = v9;
        byte[] v12 = v11;
        while(v8.bitsLeft() != 0) {
            int v1 = 8;
            int v2 = v8.readBits(v1);
            if(v2 == 240) {
                goto label_73;
            }

            int v3 = 3;
            switch(v2) {
                case 16: {
                    goto label_51;
                }
                case 17: {
                    goto label_38;
                }
                case 18: {
                    goto label_29;
                }
            }

            v3 = 4;
            switch(v2) {
                case 32: {
                    goto label_26;
                }
                case 33: {
                    goto label_23;
                }
                case 34: {
                    goto label_20;
                }
            }

            continue;
        label_20:
            byte[] v1_1 = DvbParser.buildClutMapTable(16, v1, v8);
            goto label_24;
        label_23:
            v1_1 = DvbParser.buildClutMapTable(v3, v1, v8);
        label_24:
            v11 = v1_1;
            continue;
        label_26:
            v12 = DvbParser.buildClutMapTable(v3, v3, v8);
            continue;
        label_51:
            if(v0 == v3) {
                v1_1 = v11 == null ? DvbParser.defaultMap2To8 : v11;
                goto label_56;
            }
            else if(v0 != 2) {
                v3_1 = v9;
            }
            else if(v12 == null) {
                v1_1 = DvbParser.defaultMap2To4;
                goto label_56;
            }
            else {
                v1_1 = v12;
            label_56:
                v3_1 = v1_1;
            }

            v1 = DvbParser.paint2BitPixelCodeString(v8, arg14, v3_1, v4, v10, arg18, arg19);
            goto label_49;
        label_38:
            v3_1 = v0 == v3 ? DvbParser.defaultMap4To8 : v9;
            v1 = DvbParser.paint4BitPixelCodeString(v8, arg14, v3_1, v4, v10, arg18, arg19);
        label_49:
            v8.byteAlign();
            goto label_36;
        label_29:
            v1 = DvbParser.paint8BitPixelCodeString(v8, arg14, null, v4, v10, arg18, arg19);
        label_36:
            v4 = v1;
            continue;
        label_73:
            v10 += 2;
            v4 = arg16;
        }
    }

    private static void paintPixelDataSubBlocks(ObjectData arg7, ClutDefinition arg8, int arg9, int arg10, int arg11, Paint arg12, Canvas arg13) {
        int[] v8;
        if(arg9 == 3) {
            v8 = arg8.clutEntries8Bit;
        }
        else if(arg9 == 2) {
            v8 = arg8.clutEntries4Bit;
        }
        else {
            v8 = arg8.clutEntries2Bit;
        }

        DvbParser.paintPixelDataSubBlock(arg7.topFieldData, v8, arg9, arg10, arg11, arg12, arg13);
        DvbParser.paintPixelDataSubBlock(arg7.bottomFieldData, v8, arg9, arg10, arg11 + 1, arg12, arg13);
    }

    private static ClutDefinition parseClutDefinition(ParsableBitArray arg21, int arg22) {
        int v13;
        int v12;
        int v11;
        int[] v10;
        ParsableBitArray v0 = arg21;
        int v1 = 8;
        int v2 = v0.readBits(v1);
        v0.skipBits(v1);
        int v3 = 2;
        int v4 = arg22 - 2;
        int[] v5 = DvbParser.generateDefault2BitClutEntries();
        int[] v6 = DvbParser.generateDefault4BitClutEntries();
        int[] v7 = DvbParser.generateDefault8BitClutEntries();
        while(v4 > 0) {
            int v8 = v0.readBits(v1);
            int v9 = v0.readBits(v1);
            v4 += -2;
            if((v9 & 128) != 0) {
                v10 = v5;
            }
            else if((v9 & 64) != 0) {
                v10 = v6;
            }
            else {
                v10 = v7;
            }

            if((v9 & 1) != 0) {
                v9 = v0.readBits(v1);
                v11 = v0.readBits(v1);
                v12 = v0.readBits(v1);
                v13 = v0.readBits(v1);
                v4 += -4;
            }
            else {
                v11 = v0.readBits(6) << v3;
                v13 = v0.readBits(4) << 4;
                v12 = v0.readBits(4) << 4;
                v4 += -2;
                int v20 = v13;
                v13 = v0.readBits(v3) << 6;
                v9 = v11;
                v11 = v20;
            }

            int v15 = 255;
            if(v9 == 0) {
                v11 = 0;
                v12 = 0;
                v13 = 255;
            }

            int v16 = v4;
            double v3_1 = ((double)v9);
            int v19 = v2;
            double v1_1 = ((double)(v11 - 128));
            Double.isNaN(v1_1);
            Double.isNaN(v3_1);
            double v11_1 = ((double)(v12 - 128));
            Double.isNaN(v11_1);
            Double.isNaN(v3_1);
            Double.isNaN(v1_1);
            Double.isNaN(v11_1);
            Double.isNaN(v3_1);
            v10[v8] = DvbParser.getColor(((byte)(255 - (v13 & v15))), Util.constrainValue(((int)(v3_1 + 1.402 * v1_1)), 0, 255), Util.constrainValue(((int)(v3_1 - 0.34414 * v11_1 - v1_1 * 0.71414)), 0, 255), Util.constrainValue(((int)(v3_1 + v11_1 * 1.772)), 0, 255));
            v4 = v16;
            v2 = v19;
            v1 = 8;
            v3 = 2;
        }

        return new ClutDefinition(v2, v5, v6, v7);
    }

    private static DisplayDefinition parseDisplayDefinition(ParsableBitArray arg9) {
        int v7;
        int v6;
        int v8;
        int v5;
        arg9.skipBits(4);
        boolean v0 = arg9.readBit();
        arg9.skipBits(3);
        int v1 = 16;
        int v3 = arg9.readBits(v1);
        int v4 = arg9.readBits(v1);
        if(v0) {
            int v0_1 = arg9.readBits(v1);
            int v2 = arg9.readBits(v1);
            v5 = arg9.readBits(v1);
            v8 = arg9.readBits(v1);
            v6 = v2;
            v7 = v5;
            v5 = v0_1;
        }
        else {
            v6 = v3;
            v8 = v4;
            v5 = 0;
            v7 = 0;
        }

        return new DisplayDefinition(v3, v4, v5, v6, v7, v8);
    }

    private static ObjectData parseObjectData(ParsableBitArray arg6) {
        byte[] v2_1;
        int v0 = 16;
        int v1 = arg6.readBits(v0);
        arg6.skipBits(4);
        int v2 = arg6.readBits(2);
        boolean v3 = arg6.readBit();
        arg6.skipBits(1);
        byte[] v5 = null;
        if(v2 == 1) {
            arg6.skipBits(arg6.readBits(8) * 16);
            goto label_27;
        }
        else if(v2 == 0) {
            v2 = arg6.readBits(v0);
            v0 = arg6.readBits(v0);
            if(v2 > 0) {
                v5 = new byte[v2];
                arg6.readBytes(v5, 0, v2);
            }

            if(v0 <= 0) {
                goto label_27;
            }

            v2_1 = new byte[v0];
            arg6.readBytes(v2_1, 0, v0);
        }
        else {
        label_27:
            v2_1 = v5;
        }

        return new ObjectData(v1, v3, v5, v2_1);
    }

    private static PageComposition parsePageComposition(ParsableBitArray arg9, int arg10) {
        int v0 = 8;
        int v1 = arg9.readBits(v0);
        int v2 = arg9.readBits(4);
        int v4 = arg9.readBits(2);
        arg9.skipBits(2);
        arg10 -= 2;
        SparseArray v3 = new SparseArray();
        while(arg10 > 0) {
            int v5 = arg9.readBits(v0);
            arg9.skipBits(v0);
            arg10 += -6;
            v3.put(v5, new PageRegion(arg9.readBits(16), arg9.readBits(16)));
        }

        return new PageComposition(v1, v2, v4, v3);
    }

    private static RegionComposition parseRegionComposition(ParsableBitArray arg26, int arg27) {
        int v24;
        int v23;
        ParsableBitArray v0 = arg26;
        int v3 = v0.readBits(8);
        v0.skipBits(4);
        boolean v4 = arg26.readBit();
        v0.skipBits(3);
        int v6 = 16;
        int v7 = v0.readBits(v6);
        int v8 = v0.readBits(v6);
        int v9 = v0.readBits(3);
        int v10 = v0.readBits(3);
        int v5 = 2;
        v0.skipBits(v5);
        int v11 = v0.readBits(8);
        int v12 = v0.readBits(8);
        int v13 = v0.readBits(4);
        int v14 = v0.readBits(v5);
        v0.skipBits(v5);
        int v15 = arg27 - 10;
        SparseArray v1 = new SparseArray();
        while(v15 > 0) {
            int v2 = v0.readBits(v6);
            v6 = v0.readBits(v5);
            int v20 = v0.readBits(v5);
            int v21 = v0.readBits(12);
            int v25 = v14;
            v0.skipBits(4);
            int v22 = v0.readBits(12);
            v15 += -6;
            if(v6 == 1 || v6 == 2) {
                v15 += -2;
                v23 = v0.readBits(8);
                v24 = v0.readBits(8);
            }
            else {
                v23 = 0;
                v24 = 0;
            }

            v1.put(v2, new RegionObject(v6, v20, v21, v22, v23, v24));
            v14 = v25;
            v5 = 2;
            v6 = 16;
        }

        return new RegionComposition(v3, v4, v7, v8, v9, v10, v11, v12, v13, v14, v1);
    }

    private static void parseSubtitlingSegment(ParsableBitArray arg6, SubtitleService arg7) {
        PageComposition v0_3;
        ObjectData v0_2;
        SparseArray v7;
        ClutDefinition v0_1;
        int v0 = arg6.readBits(8);
        int v2 = arg6.readBits(16);
        int v1 = arg6.readBits(16);
        int v3 = arg6.getBytePosition() + v1;
        if(v1 * 8 > arg6.bitsLeft()) {
            Log.w("DvbParser", "Data field length exceeds limit");
            arg6.skipBits(arg6.bitsLeft());
            return;
        }

        switch(v0) {
            case 16: {
                if(v2 != arg7.subtitlePageId) {
                    goto label_80;
                }

                v0_3 = arg7.pageComposition;
                PageComposition v1_1 = DvbParser.parsePageComposition(arg6, v1);
                if(v1_1.state != 0) {
                    arg7.pageComposition = v1_1;
                    arg7.regions.clear();
                    arg7.cluts.clear();
                    arg7.objects.clear();
                    goto label_80;
                }

                if(v0_3 == null) {
                    goto label_80;
                }

                if(v0_3.version == v1_1.version) {
                    goto label_80;
                }

                arg7.pageComposition = v1_1;
                break;
            }
            case 17: {
                v0_3 = arg7.pageComposition;
                if(v2 != arg7.subtitlePageId) {
                    goto label_80;
                }

                if(v0_3 == null) {
                    goto label_80;
                }

                RegionComposition v1_2 = DvbParser.parseRegionComposition(arg6, v1);
                if(v0_3.state == 0) {
                    v1_2.mergeFrom(arg7.regions.get(v1_2.id));
                }

                arg7.regions.put(v1_2.id, v1_2);
                break;
            }
            case 18: {
                if(v2 == arg7.subtitlePageId) {
                    v0_1 = DvbParser.parseClutDefinition(arg6, v1);
                    v7 = arg7.cluts;
                }
                else if(v2 == arg7.ancillaryPageId) {
                    v0_1 = DvbParser.parseClutDefinition(arg6, v1);
                    v7 = arg7.ancillaryCluts;
                }
                else {
                    goto label_80;
                }

                v1 = v0_1.id;
                goto label_39;
            }
            case 19: {
                if(v2 == arg7.subtitlePageId) {
                    v0_2 = DvbParser.parseObjectData(arg6);
                    v7 = arg7.objects;
                }
                else if(v2 == arg7.ancillaryPageId) {
                    v0_2 = DvbParser.parseObjectData(arg6);
                    v7 = arg7.ancillaryObjects;
                }
                else {
                    goto label_80;
                }

                v1 = v0_2.id;
            label_39:
                v7.put(v1, v0_2);
                break;
            }
            case 20: {
                if(v2 != arg7.subtitlePageId) {
                    goto label_80;
                }

                arg7.displayDefinition = DvbParser.parseDisplayDefinition(arg6);
                break;
            }
            default: {
                break;
            }
        }

    label_80:
        arg6.skipBytes(v3 - arg6.getBytePosition());
    }

    public void reset() {
        this.subtitleService.reset();
    }
}

