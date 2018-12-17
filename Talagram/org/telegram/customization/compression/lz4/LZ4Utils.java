package org.telegram.customization.compression.lz4;

public enum LZ4Utils {
    public class Match {
        int len;
        int ref;
        int start;

        public Match() {
            super();
        }

        int end() {
            return this.start + this.len;
        }

        void fix(int arg2) {
            this.start += arg2;
            this.ref += arg2;
            this.len -= arg2;
        }
    }

    private static final int MAX_INPUT_SIZE = 2113929216;

    static {
        LZ4Utils.$VALUES = new LZ4Utils[0];
    }

    private LZ4Utils(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static void copyTo(Match arg1, Match arg2) {
        arg2.len = arg1.len;
        arg2.start = arg1.start;
        arg2.ref = arg1.ref;
    }

    public static int hash(int arg1) {
        return arg1 * -1640531535 >>> 20;
    }

    public static int hash64k(int arg1) {
        return arg1 * -1640531535 >>> 19;
    }

    public static int hashHC(int arg1) {
        return arg1 * -1640531535 >>> 17;
    }

    public static int maxCompressedLength(int arg3) {
        if(arg3 >= 0) {
            if(arg3 < 2113929216) {
                return arg3 + arg3 / 255 + 16;
            }

            throw new IllegalArgumentException("length must be < 2113929216");
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("length must be >= 0, got ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
    }

    public static LZ4Utils valueOf(String arg1) {
        return Enum.valueOf(LZ4Utils.class, arg1);
    }

    public static LZ4Utils[] values() {
        // Method was not decompiled
    }
}

