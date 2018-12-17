package com.google.android.gms.internal.wearable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class zzu {
    private static void zza(String arg12, Object arg13, StringBuffer arg14, StringBuffer arg15) {
        int v12;
        String v13;
        int v3_1;
        Method v7_3;
        String v8_2;
        String v7_2;
        int v4_1;
        if(arg13 != null) {
            if((arg13 instanceof zzt)) {
                int v0 = arg14.length();
                if(arg12 != null) {
                    arg15.append(arg14);
                    arg15.append(zzu.zzh(arg12));
                    arg15.append(" <\n");
                    arg14.append("  ");
                }

                Class v3 = arg13.getClass();
                Field[] v4 = v3.getFields();
                int v5 = v4.length;
                int v6;
                for(v6 = 0; v6 < v5; ++v6) {
                    Field v7 = v4[v6];
                    int v8 = v7.getModifiers();
                    String v9 = v7.getName();
                    if(!"cachedSize".equals(v9) && (v8 & 1) == 1 && (v8 & 8) != 8 && !v9.startsWith("_") && !v9.endsWith("_")) {
                        Class v8_1 = v7.getType();
                        Object v7_1 = v7.get(arg13);
                        if((v8_1.isArray()) && v8_1.getComponentType() != Byte.TYPE) {
                            v8 = v7_1 == null ? 0 : Array.getLength(v7_1);
                            int v10;
                            for(v10 = 0; true; ++v10) {
                                if(v10 >= v8) {
                                    goto label_54;
                                }

                                zzu.zza(v9, Array.get(v7_1, v10), arg14, arg15);
                            }
                        }

                        zzu.zza(v9, v7_1, arg14, arg15);
                    }

                label_54:
                }

                Method[] v1 = v3.getMethods();
                v4_1 = v1.length;
                for(v5 = 0; v5 < v4_1; ++v5) {
                    String v6_1 = v1[v5].getName();
                    if(v6_1.startsWith("set")) {
                        v6_1 = v6_1.substring(3);
                        try {
                            v7_2 = "has";
                            v8_2 = String.valueOf(v6_1);
                            v7_2 = v8_2.length() != 0 ? v7_2.concat(v8_2) : new String(v7_2);
                            v7_3 = v3.getMethod(v7_2);
                        }
                        catch(NoSuchMethodException ) {
                            goto label_96;
                        }

                        if(!v7_3.invoke(arg13).booleanValue()) {
                            goto label_96;
                        }

                        try {
                            v7_2 = "get";
                            v8_2 = String.valueOf(v6_1);
                            v7_2 = v8_2.length() != 0 ? v7_2.concat(v8_2) : new String(v7_2);
                            v7_3 = v3.getMethod(v7_2);
                        }
                        catch(NoSuchMethodException ) {
                            goto label_96;
                        }

                        zzu.zza(v6_1, v7_3.invoke(arg13), arg14, arg15);
                    }

                label_96:
                }

                if(arg12 != null) {
                    arg14.setLength(v0);
                    arg15.append(arg14);
                    arg15.append(">\n");
                }

                return;
            }
            else {
                arg12 = zzu.zzh(arg12);
                arg15.append(arg14);
                arg15.append(arg12);
                arg15.append(": ");
                int v14 = 32;
                char v0_1 = '\"';
                if((arg13 instanceof String)) {
                    if(!((String)arg13).startsWith("http")) {
                        v3_1 = 200;
                        if(((String)arg13).length() > v3_1) {
                            v13 = String.valueOf(((String)arg13).substring(0, v3_1)).concat("[...]");
                        }
                    }

                    v12 = v13.length();
                    StringBuilder v3_2 = new StringBuilder(v12);
                    for(v4_1 = 0; v4_1 < v12; ++v4_1) {
                        char v5_1 = v13.charAt(v4_1);
                        if(v5_1 < v14 || v5_1 > 126 || v5_1 == v0_1 || v5_1 == 39) {
                            v3_2.append(String.format("\\u%04x", Integer.valueOf(v5_1)));
                        }
                        else {
                            v3_2.append(v5_1);
                        }
                    }

                    arg12 = v3_2.toString();
                    arg15.append("\"");
                    arg15.append(arg12);
                    arg12 = "\"";
                    goto label_155;
                }
                else {
                    if((arg13 instanceof byte[])) {
                        if(arg13 == null) {
                            arg12 = "\"\"";
                        label_155:
                            arg15.append(arg12);
                            goto label_186;
                        }

                        arg15.append(v0_1);
                        for(v12 = 0; v12 < arg13.length; ++v12) {
                            v3_1 = arg13[v12] & 255;
                            char v4_2 = '\\';
                            if(v3_1 == v4_2 || v3_1 == v0_1) {
                                arg15.append(v4_2);
                            label_179:
                                arg15.append(((char)v3_1));
                            }
                            else {
                                if(v3_1 >= v14 && v3_1 < 127) {
                                    goto label_179;
                                }

                                arg15.append(String.format("\\%03o", Integer.valueOf(v3_1)));
                            }
                        }

                        arg15.append(v0_1);
                        goto label_186;
                    }

                    arg15.append(arg13);
                }

            label_186:
                arg15.append("\n");
            }
        }
    }

    public static String zzc(zzt arg3) {
        String v3_2;
        String v0_1;
        if(arg3 == null) {
            return "";
        }

        StringBuffer v0 = new StringBuffer();
        String v1 = null;
        try {
            zzu.zza(v1, arg3, new StringBuffer(), v0);
        }
        catch(InvocationTargetException v3) {
            v0_1 = "Error printing proto: ";
            v3_2 = String.valueOf(v3.getMessage());
            if(v3_2.length() != 0) {
                return v0_1.concat(v3_2);
            }

            return new String(v0_1);
        }
        catch(IllegalAccessException v3_1) {
            v0_1 = "Error printing proto: ";
            v3_2 = String.valueOf(v3_1.getMessage());
            if(v3_2.length() != 0) {
                return v0_1.concat(v3_2);
            }

            return new String(v0_1);
        }

        return v0.toString();
    }

    private static String zzh(String arg4) {
        StringBuffer v0 = new StringBuffer();
        int v1;
        for(v1 = 0; v1 < arg4.length(); ++v1) {
            char v2 = arg4.charAt(v1);
            if(v1 == 0) {
            label_7:
                v2 = Character.toLowerCase(v2);
            }
            else if(Character.isUpperCase(v2)) {
                v0.append('_');
                goto label_7;
            }

            v0.append(v2);
        }

        return v0.toString();
    }
}

