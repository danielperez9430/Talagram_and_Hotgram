package com.persianswitch.a.a.a;

import java.io.ByteArrayOutputStream;

class j {
    final class a {
        private final a[] a;
        private final int b;
        private final int c;

        a() {
            super();
            this.a = new a[256];
            this.b = 0;
            this.c = 0;
        }

        a(int arg2, int arg3) {
            super();
            this.a = null;
            this.b = arg2;
            arg2 = arg3 & 7;
            if(arg2 == 0) {
                arg2 = 8;
            }

            this.c = arg2;
        }

        static a[] a(a arg0) {
            return arg0.a;
        }

        static int b(a arg0) {
            return arg0.b;
        }

        static int c(a arg0) {
            return arg0.c;
        }
    }

    private static final int[] a;
    private static final byte[] b;
    private static final j c;
    private final a d;

    static {
        j.a = new int[]{8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, 1016, 1017, 4090, 8185, 21, 248, 2042, 1018, 1019, 249, 2043, 250, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, 1020, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};
        j.b = new byte[]{13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, 12, 13, 6, 8, 11, 10, 10, 8, 11, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, 15, 6, 12, 10, 13, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, 19, 13, 14, 6, 15, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, 15, 11, 14, 13, 28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 26, 20, 19, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, 19, 21, 26, 27, 27, 26, 27, 24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26};
        j.c = new j();
    }

    private j() {
        super();
        this.d = new a();
        this.b();
    }

    byte[] a(byte[] arg8) {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        int v2 = 0;
        a v4 = this.d;
        int v1 = 0;
        int v3 = 0;
        while(true) {
            int v6 = 8;
            if(v2 < arg8.length) {
                v1 = v1 << 8 | arg8[v2] & 255;
                v3 += 8;
                while(v3 >= v6) {
                    v4 = a.a(v4)[v1 >>> v3 - 8 & 255];
                    if(a.a(v4) == null) {
                        v0.write(a.b(v4));
                        v3 -= a.c(v4);
                        v4 = this.d;
                    }
                    else {
                        v3 += -8;
                    }
                }

                ++v2;
                continue;
            }

            break;
        }

        while(v3 > 0) {
            a v8 = a.a(v4)[v1 << 8 - v3 & 255];
            if(a.a(v8) != null) {
                break;
            }

            if(a.c(v8) > v3) {
            }
            else {
                v0.write(a.b(v8));
                v3 -= a.c(v8);
                v4 = this.d;
                continue;
            }

            break;
        }

        return v0.toByteArray();
    }

    public static j a() {
        return j.c;
    }

    private void a(int arg5, int arg6, byte arg7) {
        int v1;
        a v0 = new a(arg5, arg7);
        a v5;
        for(v5 = this.d; true; v5 = a.a(v5)[v1]) {
            v1 = 8;
            if(arg7 <= v1) {
                goto label_25;
            }

            arg7 = ((byte)(arg7 - 8));
            v1 = arg6 >>> arg7 & 255;
            if(a.a(v5) == null) {
                break;
            }

            if(a.a(v5)[v1] == null) {
                a.a(v5)[v1] = new a();
            }
        }

        throw new IllegalStateException("invalid dictionary: prefix not unique");
    label_25:
        v1 -= arg7;
        arg6 = arg6 << v1 & 255;
        int v7 = 1 << v1;
        for(v1 = arg6; v1 < arg6 + v7; ++v1) {
            a.a(v5)[v1] = v0;
        }
    }

    private void b() {
        int v0;
        for(v0 = 0; v0 < j.b.length; ++v0) {
            this.a(v0, j.a[v0], j.b[v0]);
        }
    }
}

