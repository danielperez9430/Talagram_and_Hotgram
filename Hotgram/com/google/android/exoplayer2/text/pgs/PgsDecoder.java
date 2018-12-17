package com.google.android.exoplayer2.text.pgs;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class PgsDecoder extends SimpleSubtitleDecoder {
    final class CueBuilder {
        private final ParsableByteArray bitmapData;
        private int bitmapHeight;
        private int bitmapWidth;
        private int bitmapX;
        private int bitmapY;
        private final int[] colors;
        private boolean colorsSet;
        private int planeHeight;
        private int planeWidth;

        public CueBuilder() {
            super();
            this.bitmapData = new ParsableByteArray();
            this.colors = new int[256];
        }

        static void access$000(CueBuilder arg0, ParsableByteArray arg1, int arg2) {
            arg0.parsePaletteSection(arg1, arg2);
        }

        static void access$100(CueBuilder arg0, ParsableByteArray arg1, int arg2) {
            arg0.parseBitmapSection(arg1, arg2);
        }

        static void access$200(CueBuilder arg0, ParsableByteArray arg1, int arg2) {
            arg0.parseIdentifierSection(arg1, arg2);
        }

        public Cue build() {
            int v4;
            if(this.planeWidth != 0 && this.planeHeight != 0 && this.bitmapWidth != 0 && this.bitmapHeight != 0 && this.bitmapData.limit() != 0 && this.bitmapData.getPosition() == this.bitmapData.limit()) {
                if(!this.colorsSet) {
                }
                else {
                    this.bitmapData.setPosition(0);
                    int[] v0 = new int[this.bitmapWidth * this.bitmapHeight];
                    int v2;
                    for(v2 = 0; v2 < v0.length; v2 = v4) {
                        int v3 = this.bitmapData.readUnsignedByte();
                        if(v3 != 0) {
                            v4 = v2 + 1;
                            v0[v2] = this.colors[v3];
                        }
                        else {
                            v3 = this.bitmapData.readUnsignedByte();
                            if(v3 == 0) {
                                continue;
                            }

                            v4 = (v3 & 64) == 0 ? v3 & 63 : (v3 & 63) << 8 | this.bitmapData.readUnsignedByte();
                            v3 = (v3 & 128) == 0 ? 0 : this.colors[this.bitmapData.readUnsignedByte()];
                            v4 += v2;
                            Arrays.fill(v0, v2, v4, v3);
                        }
                    }

                    return new Cue(Bitmap.createBitmap(v0, this.bitmapWidth, this.bitmapHeight, Bitmap$Config.ARGB_8888), (((float)this.bitmapX)) / (((float)this.planeWidth)), 0, (((float)this.bitmapY)) / (((float)this.planeHeight)), 0, (((float)this.bitmapWidth)) / (((float)this.planeWidth)), (((float)this.bitmapHeight)) / (((float)this.planeHeight)));
                }
            }

            return null;
        }

        private void parseBitmapSection(ParsableByteArray arg4, int arg5) {
            int v0 = 4;
            if(arg5 < v0) {
                return;
            }

            arg4.skipBytes(3);
            int v1 = (arg4.readUnsignedByte() & 128) != 0 ? 1 : 0;
            arg5 += -4;
            if(v1 != 0) {
                if(arg5 < 7) {
                    return;
                }
                else {
                    v1 = arg4.readUnsignedInt24();
                    if(v1 < v0) {
                        return;
                    }
                    else {
                        this.bitmapWidth = arg4.readUnsignedShort();
                        this.bitmapHeight = arg4.readUnsignedShort();
                        this.bitmapData.reset(v1 - v0);
                        arg5 += -7;
                    }
                }
            }

            v0 = this.bitmapData.getPosition();
            v1 = this.bitmapData.limit();
            if(v0 < v1 && arg5 > 0) {
                arg5 = Math.min(arg5, v1 - v0);
                arg4.readBytes(this.bitmapData.data, v0, arg5);
                this.bitmapData.setPosition(v0 + arg5);
            }
        }

        private void parseIdentifierSection(ParsableByteArray arg2, int arg3) {
            if(arg3 < 19) {
                return;
            }

            this.planeWidth = arg2.readUnsignedShort();
            this.planeHeight = arg2.readUnsignedShort();
            arg2.skipBytes(11);
            this.bitmapX = arg2.readUnsignedShort();
            this.bitmapY = arg2.readUnsignedShort();
        }

        private void parsePaletteSection(ParsableByteArray arg19, int arg20) {
            CueBuilder v0 = this;
            int v3 = 2;
            if(arg20 % 5 != v3) {
                return;
            }

            arg19.skipBytes(v3);
            Arrays.fill(v0.colors, 0);
            int v1 = arg20 / 5;
            for(v3 = 0; v3 < v1; ++v3) {
                int v5 = arg19.readUnsignedByte();
                int v6 = arg19.readUnsignedByte();
                int v7 = arg19.readUnsignedByte();
                int v8 = arg19.readUnsignedByte();
                int v9 = arg19.readUnsignedByte();
                double v10 = ((double)v6);
                double v6_1 = ((double)(v7 - 128));
                Double.isNaN(v6_1);
                Double.isNaN(v10);
                int v15 = v5;
                double v4 = ((double)(v8 - 128));
                Double.isNaN(v4);
                Double.isNaN(v10);
                Double.isNaN(v6_1);
                Double.isNaN(v4);
                Double.isNaN(v10);
                v0.colors[v15] = Util.constrainValue(((int)(v10 + v4 * 1.772)), 0, 255) | (Util.constrainValue(((int)(v10 - 0.34414 * v4 - v6_1 * 0.71414)), 0, 255) << 8 | (v9 << 24 | Util.constrainValue(((int)(1.402 * v6_1 + v10)), 0, 255) << 16));
            }

            v0.colorsSet = true;
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }

    private static final byte INFLATE_HEADER = 120;
    private static final int SECTION_TYPE_BITMAP_PICTURE = 21;
    private static final int SECTION_TYPE_END = 128;
    private static final int SECTION_TYPE_IDENTIFIER = 22;
    private static final int SECTION_TYPE_PALETTE = 20;
    private final ParsableByteArray buffer;
    private final CueBuilder cueBuilder;
    private byte[] inflatedData;
    private int inflatedDataSize;
    private Inflater inflater;

    public PgsDecoder() {
        super("PgsDecoder");
        this.buffer = new ParsableByteArray();
        this.cueBuilder = new CueBuilder();
    }

    protected Subtitle decode(byte[] arg1, int arg2, boolean arg3) {
        if(this.maybeInflateData(arg1, arg2)) {
            this.buffer.reset(this.inflatedData, this.inflatedDataSize);
        }
        else {
            this.buffer.reset(arg1, arg2);
        }

        this.cueBuilder.reset();
        ArrayList v1 = new ArrayList();
        while(this.buffer.bytesLeft() >= 3) {
            Cue v2 = PgsDecoder.readNextSection(this.buffer, this.cueBuilder);
            if(v2 == null) {
                continue;
            }

            v1.add(v2);
        }

        return new PgsSubtitle(Collections.unmodifiableList(((List)v1)));
    }

    private boolean maybeInflateData(byte[] arg6, int arg7) {
        boolean v6_1;
        if(arg7 != 0 && arg6[0] == 120) {
            if(this.inflater == null) {
                this.inflater = new Inflater();
                this.inflatedData = new byte[arg7];
            }

            this.inflatedDataSize = 0;
            this.inflater.setInput(arg6, 0, arg7);
            try {
                while(!this.inflater.finished()) {
                    if(this.inflater.needsDictionary()) {
                        break;
                    }

                    if(this.inflater.needsInput()) {
                        break;
                    }

                    if(this.inflatedDataSize == this.inflatedData.length) {
                        this.inflatedData = Arrays.copyOf(this.inflatedData, this.inflatedData.length * 2);
                    }

                    this.inflatedDataSize += this.inflater.inflate(this.inflatedData, this.inflatedDataSize, this.inflatedData.length - this.inflatedDataSize);
                }

                v6_1 = this.inflater.finished();
            }
            catch(Throwable v6) {
                this.inflater.reset();
                throw v6;
            }
            catch(DataFormatException ) {
                this.inflater.reset();
                return 0;
            }

            this.inflater.reset();
            return v6_1;
        }

        return 0;
    }

    private static Cue readNextSection(ParsableByteArray arg5, CueBuilder arg6) {
        int v0 = arg5.limit();
        int v1 = arg5.readUnsignedByte();
        int v2 = arg5.readUnsignedShort();
        int v3 = arg5.getPosition() + v2;
        Cue v4 = null;
        if(v3 > v0) {
            arg5.setPosition(v0);
            return v4;
        }

        if(v1 != 128) {
            switch(v1) {
                case 20: {
                    goto label_17;
                }
                case 21: {
                    goto label_15;
                }
                case 22: {
                    goto label_13;
                }
            }

            goto label_21;
        label_17:
            CueBuilder.access$000(arg6, arg5, v2);
            goto label_21;
        label_13:
            CueBuilder.access$200(arg6, arg5, v2);
            goto label_21;
        label_15:
            CueBuilder.access$100(arg6, arg5, v2);
        }
        else {
            v4 = arg6.build();
            arg6.reset();
        }

    label_21:
        arg5.setPosition(v3);
        return v4;
    }
}

