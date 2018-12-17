package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;

final class DefaultEbmlReader implements EbmlReader {
    class com.google.android.exoplayer2.extractor.mkv.DefaultEbmlReader$1 {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ElementState {
    }

    final class MasterElement {
        private final long elementEndPosition;
        private final int elementId;

        MasterElement(int arg1, long arg2, com.google.android.exoplayer2.extractor.mkv.DefaultEbmlReader$1 arg4) {
            this(arg1, arg2);
        }

        private MasterElement(int arg1, long arg2) {
            super();
            this.elementId = arg1;
            this.elementEndPosition = arg2;
        }

        static long access$000(MasterElement arg2) {
            return arg2.elementEndPosition;
        }

        static int access$100(MasterElement arg0) {
            return arg0.elementId;
        }
    }

    private static final int ELEMENT_STATE_READ_CONTENT = 2;
    private static final int ELEMENT_STATE_READ_CONTENT_SIZE = 1;
    private static final int ELEMENT_STATE_READ_ID = 0;
    private static final int MAX_ID_BYTES = 4;
    private static final int MAX_INTEGER_ELEMENT_SIZE_BYTES = 8;
    private static final int MAX_LENGTH_BYTES = 8;
    private static final int VALID_FLOAT32_ELEMENT_SIZE_BYTES = 4;
    private static final int VALID_FLOAT64_ELEMENT_SIZE_BYTES = 8;
    private long elementContentSize;
    private int elementId;
    private int elementState;
    private final ArrayDeque masterElementsStack;
    private EbmlReaderOutput output;
    private final byte[] scratch;
    private final VarintReader varintReader;

    public DefaultEbmlReader() {
        super();
        this.scratch = new byte[8];
        this.masterElementsStack = new ArrayDeque();
        this.varintReader = new VarintReader();
    }

    public void init(EbmlReaderOutput arg1) {
        this.output = arg1;
    }

    private long maybeResyncToNextLevel1Element(ExtractorInput arg5) {
        arg5.resetPeekPosition();
        while(true) {
            int v1 = 4;
            arg5.peekFully(this.scratch, 0, v1);
            int v0 = VarintReader.parseUnsignedVarintLength(this.scratch[0]);
            if(v0 != -1 && v0 <= v1) {
                v1 = ((int)VarintReader.assembleVarint(this.scratch, v0, false));
                if(this.output.isLevel1Element(v1)) {
                    arg5.skipFully(v0);
                    return ((long)v1);
                }
            }

            arg5.skipFully(1);
        }
    }

    public boolean read(ExtractorInput arg10) {
        long v3;
        boolean v0 = this.output != null ? true : false;
        Assertions.checkState(v0);
        while(true) {
            if(!this.masterElementsStack.isEmpty() && arg10.getPosition() >= MasterElement.access$000(this.masterElementsStack.peek())) {
                this.output.endMasterElement(MasterElement.access$100(this.masterElementsStack.pop()));
                return 1;
            }

            if(this.elementState == 0) {
                v3 = this.varintReader.readUnsignedVarint(arg10, true, false, 4);
                if(v3 == -2) {
                    v3 = this.maybeResyncToNextLevel1Element(arg10);
                }

                if(v3 == -1) {
                    return 0;
                }

                this.elementId = ((int)v3);
                this.elementState = 1;
            }

            if(this.elementState == 1) {
                this.elementContentSize = this.varintReader.readUnsignedVarint(arg10, false, true, 8);
                this.elementState = 2;
            }

            int v0_1 = this.output.getElementType(this.elementId);
            v3 = 8;
            switch(v0_1) {
                case 0: {
                    goto label_145;
                }
                case 1: {
                    goto label_130;
                }
                case 2: {
                    goto label_110;
                }
                case 3: {
                    goto label_89;
                }
                case 4: {
                    goto label_82;
                }
                case 5: {
                    goto label_58;
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Invalid element type ");
            v1.append(v0_1);
            throw new ParserException(v1.toString());
        label_145:
            arg10.skipFully(((int)this.elementContentSize));
            this.elementState = 0;
        }

    label_130:
        long v5 = arg10.getPosition();
        this.masterElementsStack.push(new MasterElement(this.elementId, this.elementContentSize + v5, null));
        this.output.startMasterElement(this.elementId, v5, this.elementContentSize);
        this.elementState = 0;
        return 1;
    label_82:
        this.output.binaryElement(this.elementId, ((int)this.elementContentSize), arg10);
        this.elementState = 0;
        return 1;
    label_89:
        if(this.elementContentSize <= 2147483647) {
            this.output.stringElement(this.elementId, this.readString(arg10, ((int)this.elementContentSize)));
            this.elementState = 0;
            return 1;
        }

        StringBuilder v0_2 = new StringBuilder();
        v0_2.append("String element size: ");
        v0_2.append(this.elementContentSize);
        throw new ParserException(v0_2.toString());
    label_58:
        if(this.elementContentSize != 4) {
            if(this.elementContentSize == v3) {
            }
            else {
                v0_2 = new StringBuilder();
                v0_2.append("Invalid float size: ");
                v0_2.append(this.elementContentSize);
                throw new ParserException(v0_2.toString());
            }
        }

        this.output.floatElement(this.elementId, this.readFloat(arg10, ((int)this.elementContentSize)));
        this.elementState = 0;
        return 1;
    label_110:
        if(this.elementContentSize <= v3) {
            this.output.integerElement(this.elementId, this.readInteger(arg10, ((int)this.elementContentSize)));
            this.elementState = 0;
            return 1;
        }

        v0_2 = new StringBuilder();
        v0_2.append("Invalid integer size: ");
        v0_2.append(this.elementContentSize);
        throw new ParserException(v0_2.toString());
    }

    private double readFloat(ExtractorInput arg3, int arg4) {
        long v0 = this.readInteger(arg3, arg4);
        double v3 = arg4 == 4 ? ((double)Float.intBitsToFloat(((int)v0))) : Double.longBitsToDouble(v0);
        return v3;
    }

    private long readInteger(ExtractorInput arg7, int arg8) {
        int v1 = 0;
        arg7.readFully(this.scratch, 0, arg8);
        long v2 = 0;
        while(v1 < arg8) {
            v2 = v2 << 8 | (((long)(this.scratch[v1] & 255)));
            ++v1;
        }

        return v2;
    }

    private String readString(ExtractorInput arg3, int arg4) {
        if(arg4 == 0) {
            return "";
        }

        byte[] v0 = new byte[arg4];
        arg3.readFully(v0, 0, arg4);
        while(arg4 > 0) {
            if(v0[arg4 - 1] != 0) {
                break;
            }

            --arg4;
        }

        return new String(v0, 0, arg4);
    }

    public void reset() {
        this.elementState = 0;
        this.masterElementsStack.clear();
        this.varintReader.reset();
    }
}

