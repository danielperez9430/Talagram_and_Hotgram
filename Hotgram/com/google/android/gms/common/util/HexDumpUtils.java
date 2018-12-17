package com.google.android.gms.common.util;

import java.io.ByteArrayOutputStream;
import java.util.StringTokenizer;

public final class HexDumpUtils {
    public HexDumpUtils() {
        super();
    }

    public static byte[] bytesFromString(String arg3) {
        StringTokenizer v0 = new StringTokenizer(arg3, " \t\n");
        ByteArrayOutputStream v3 = new ByteArrayOutputStream();
        while(v0.hasMoreTokens()) {
            try {
                v3.write(Integer.parseInt(v0.nextToken(), 16) & 255);
                continue;
            }
            catch(NumberFormatException ) {
                return null;
            }
        }

        return v3.toByteArray();
    }

    public static String dump(byte[] arg11, int arg12, int arg13, boolean arg14) {
        Object[] v8;
        String v6_1;
        if(arg11 != null && arg11.length != 0 && arg12 >= 0 && arg13 > 0) {
            if(arg12 + arg13 > arg11.length) {
            }
            else {
                int v0 = 57;
                if(arg14) {
                    v0 = 75;
                }

                int v3 = 16;
                StringBuilder v1 = new StringBuilder(v0 * ((arg13 + 16 - 1) / v3));
                int v5 = arg12;
                arg12 = arg13;
                int v4 = 0;
                int v6 = 0;
                while(arg12 > 0) {
                    int v7 = 8;
                    if(v4 == 0) {
                        if(arg13 < 65536) {
                            v6_1 = "%04X:";
                            v8 = new Object[]{Integer.valueOf(v5)};
                        }
                        else {
                            v6_1 = "%08X:";
                            v8 = new Object[]{Integer.valueOf(v5)};
                        }

                        v1.append(String.format(v6_1, v8));
                        v6 = v5;
                    }
                    else {
                        if(v4 != v7) {
                            goto label_47;
                        }

                        v1.append(" -");
                    }

                label_47:
                    v1.append(String.format(" %02X", Integer.valueOf(arg11[v5] & 255)));
                    --arg12;
                    ++v4;
                    if((arg14) && (v4 == v3 || arg12 == 0)) {
                        int v8_1 = 16 - v4;
                        if(v8_1 > 0) {
                            int v9;
                            for(v9 = 0; v9 < v8_1; ++v9) {
                                v1.append("   ");
                            }
                        }

                        if(v8_1 >= v7) {
                            v1.append("  ");
                        }

                        v1.append("  ");
                        for(v7 = 0; v7 < v4; ++v7) {
                            char v8_2 = ((char)arg11[v6 + v7]);
                            if(v8_2 < 32 || v8_2 > 126) {
                                v8_2 = '.';
                            }
                            else {
                            }

                            v1.append(v8_2);
                        }
                    }

                    if(v4 == v3 || arg12 == 0) {
                        v1.append('\n');
                        v4 = 0;
                    }

                    ++v5;
                }

                return v1.toString();
            }
        }

        return null;
    }
}

