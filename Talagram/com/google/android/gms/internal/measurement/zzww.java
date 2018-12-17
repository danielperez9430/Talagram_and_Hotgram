package com.google.android.gms.internal.measurement;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

final class zzww {
    static String zza(zzwt arg2, String arg3) {
        StringBuilder v0 = new StringBuilder();
        v0.append("# ");
        v0.append(arg3);
        zzww.zza(arg2, v0, 0);
        return v0.toString();
    }

    private static void zza(zzwt arg12, StringBuilder arg13, int arg14) {
        boolean v4_4;
        zzud v4_3;
        String v8;
        String v6_1;
        HashMap v0 = new HashMap();
        HashMap v1 = new HashMap();
        TreeSet v2 = new TreeSet();
        Method[] v3 = arg12.getClass().getDeclaredMethods();
        int v4 = v3.length;
        int v6;
        for(v6 = 0; v6 < v4; ++v6) {
            Method v7 = v3[v6];
            ((Map)v1).put(v7.getName(), v7);
            if(v7.getParameterTypes().length == 0) {
                ((Map)v0).put(v7.getName(), v7);
                if(v7.getName().startsWith("get")) {
                    ((Set)v2).add(v7.getName());
                }
            }
        }

        Iterator v2_1 = ((Set)v2).iterator();
        while(v2_1.hasNext()) {
            Object v3_1 = v2_1.next();
            String v4_1 = ((String)v3_1).replaceFirst("get", "");
            if((v4_1.endsWith("List")) && !v4_1.endsWith("OrBuilderList") && !v4_1.equals("List")) {
                v6_1 = String.valueOf(v4_1.substring(0, 1).toLowerCase());
                v8 = String.valueOf(v4_1.substring(1, v4_1.length() - 4));
                v6_1 = v8.length() != 0 ? v6_1.concat(v8) : new String(v6_1);
                Object v8_1 = ((Map)v0).get(v3_1);
                if(v8_1 == null) {
                    goto label_70;
                }

                if(!((Method)v8_1).getReturnType().equals(List.class)) {
                    goto label_70;
                }

                zzww.zzb(arg13, arg14, zzww.zzga(v6_1), zzvm.zza(((Method)v8_1), arg12, new Object[0]));
                continue;
            }

        label_70:
            if((v4_1.endsWith("Map")) && !v4_1.equals("Map")) {
                v6_1 = String.valueOf(v4_1.substring(0, 1).toLowerCase());
                v8 = String.valueOf(v4_1.substring(1, v4_1.length() - 3));
                v6_1 = v8.length() != 0 ? v6_1.concat(v8) : new String(v6_1);
                v3_1 = ((Map)v0).get(v3_1);
                if(v3_1 == null) {
                    goto label_107;
                }

                if(!((Method)v3_1).getReturnType().equals(Map.class)) {
                    goto label_107;
                }

                if(((Method)v3_1).isAnnotationPresent(Deprecated.class)) {
                    goto label_107;
                }

                if(!Modifier.isPublic(((Method)v3_1).getModifiers())) {
                    goto label_107;
                }

                zzww.zzb(arg13, arg14, zzww.zzga(v6_1), zzvm.zza(((Method)v3_1), arg12, new Object[0]));
                continue;
            }

        label_107:
            String v3_2 = "set";
            v6_1 = String.valueOf(v4_1);
            v3_2 = v6_1.length() != 0 ? v3_2.concat(v6_1) : new String(v3_2);
            if(((Map)v1).get(v3_2) == null) {
                continue;
            }

            if(v4_1.endsWith("Bytes")) {
                v3_2 = "get";
                v6_1 = String.valueOf(v4_1.substring(0, v4_1.length() - 5));
                v3_2 = v6_1.length() != 0 ? v3_2.concat(v6_1) : new String(v3_2);
                if(((Map)v0).containsKey(v3_2)) {
                    continue;
                }
            }

            v3_2 = String.valueOf(v4_1.substring(0, 1).toLowerCase());
            v6_1 = String.valueOf(v4_1.substring(1));
            v3_2 = v6_1.length() != 0 ? v3_2.concat(v6_1) : new String(v3_2);
            v6_1 = "get";
            v8 = String.valueOf(v4_1);
            v6_1 = v8.length() != 0 ? v6_1.concat(v8) : new String(v6_1);
            Object v6_2 = ((Map)v0).get(v6_1);
            v8 = "has";
            v4_1 = String.valueOf(v4_1);
            v4_1 = v4_1.length() != 0 ? v8.concat(v4_1) : new String(v8);
            Object v4_2 = ((Map)v0).get(v4_1);
            if(v6_2 == null) {
                continue;
            }

            v6_2 = zzvm.zza(((Method)v6_2), arg12, new Object[0]);
            if(v4_2 == null) {
                if((v6_2 instanceof Boolean)) {
                    if(v6_2.booleanValue()) {
                        goto label_177;
                    }

                    goto label_175;
                }
                else if((v6_2 instanceof Integer)) {
                    if(v6_2.intValue() == 0) {
                        goto label_175;
                    }
                    else {
                        goto label_177;
                    }
                }
                else if((v6_2 instanceof Float)) {
                    if(v6_2.floatValue() == 0f) {
                        goto label_175;
                    }
                    else {
                        goto label_177;
                    }
                }
                else if(!(v6_2 instanceof Double)) {
                    if((v6_2 instanceof String)) {
                        v4_1 = "";
                    }
                    else if((v6_2 instanceof zzud)) {
                        v4_3 = zzud.zzbtz;
                    }
                    else {
                        goto label_208;
                    }

                    v4_4 = v6_2.equals(v4_3);
                    goto label_220;
                label_208:
                    if((v6_2 instanceof zzwt)) {
                        if(v6_2 != v6_2.zzwf()) {
                            goto label_177;
                        }

                        goto label_175;
                    }

                    if(((v6_2 instanceof Enum)) && v6_2.ordinal() == 0) {
                    label_175:
                        v4_4 = true;
                        goto label_220;
                    }

                label_177:
                    v4_4 = false;
                }
                else if(v6_2.doubleValue() == 0) {
                    goto label_175;
                }
                else {
                    goto label_177;
                }

            label_220:
                if(!v4_4) {
                    v4_4 = true;
                    goto label_228;
                }

                v4_4 = false;
            }
            else {
                v4_4 = zzvm.zza(((Method)v4_2), arg12, new Object[0]).booleanValue();
            }

        label_228:
            if(!v4_4) {
                continue;
            }

            zzww.zzb(arg13, arg14, zzww.zzga(v3_2), v6_2);
        }

        if((arg12 instanceof zzc)) {
            Iterator v0_1 = arg12.zzbys.iterator();
            if(!v0_1.hasNext()) {
            }
            else {
                v0_1.next().getKey();
                throw new NoSuchMethodError();
            }
        }

        if(((zzvm)arg12).zzbym != null) {
            ((zzvm)arg12).zzbym.zzb(arg13, arg14);
        }
    }

    static final void zzb(StringBuilder arg4, int arg5, String arg6, Object arg7) {
        char v2;
        Iterator v7;
        if((arg7 instanceof List)) {
            v7 = ((List)arg7).iterator();
            while(v7.hasNext()) {
                zzww.zzb(arg4, arg5, arg6, v7.next());
            }

            return;
        }

        if((arg7 instanceof Map)) {
            v7 = ((Map)arg7).entrySet().iterator();
            while(v7.hasNext()) {
                zzww.zzb(arg4, arg5, arg6, v7.next());
            }

            return;
        }

        arg4.append('\n');
        int v0 = 0;
        int v1;
        for(v1 = 0; true; ++v1) {
            v2 = ' ';
            if(v1 >= arg5) {
                break;
            }

            arg4.append(v2);
        }

        arg4.append(arg6);
        char v1_1 = '\"';
        if((arg7 instanceof String)) {
            arg4.append(": \"");
            arg4.append(zzxx.zzd(zzud.zzfv(((String)arg7))));
            arg4.append(v1_1);
            return;
        }

        if((arg7 instanceof zzud)) {
            arg4.append(": \"");
            arg4.append(zzxx.zzd(((zzud)arg7)));
            arg4.append(v1_1);
            return;
        }

        if((arg7 instanceof zzvm)) {
            arg4.append(" {");
            zzww.zza(((zzwt)arg7), arg4, arg5 + 2);
            arg4.append("\n");
            goto label_55;
        }

        if((arg7 instanceof Map$Entry)) {
            arg4.append(" {");
            int v6 = arg5 + 2;
            zzww.zzb(arg4, v6, "key", ((Map$Entry)arg7).getKey());
            zzww.zzb(arg4, v6, "value", ((Map$Entry)arg7).getValue());
            arg4.append("\n");
        }
        else {
            arg4.append(": ");
            arg4.append(arg7.toString());
            return;
        }

        while(v0 < arg5) {
            arg4.append(v2);
            ++v0;
        }

        arg4.append("}");
        return;
    label_55:
        while(v0 < arg5) {
            arg4.append(v2);
            ++v0;
        }

        arg4.append("}");
    }

    private static final String zzga(String arg4) {
        StringBuilder v0 = new StringBuilder();
        int v1;
        for(v1 = 0; v1 < arg4.length(); ++v1) {
            char v2 = arg4.charAt(v1);
            if(Character.isUpperCase(v2)) {
                v0.append("_");
            }

            v0.append(Character.toLowerCase(v2));
        }

        return v0.toString();
    }
}

