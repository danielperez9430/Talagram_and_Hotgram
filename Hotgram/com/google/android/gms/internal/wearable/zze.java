package com.google.android.gms.internal.wearable;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@VisibleForTesting public final class zze {
    public static zzf zza(DataMap arg8) {
        zzg v0 = new zzg();
        ArrayList v1 = new ArrayList();
        TreeSet v2 = new TreeSet(arg8.keySet());
        zzh[] v3 = new zzh[v2.size()];
        Iterator v2_1 = v2.iterator();
        int v4;
        for(v4 = 0; v2_1.hasNext(); ++v4) {
            Object v5 = v2_1.next();
            Object v6 = arg8.get(((String)v5));
            v3[v4] = new zzh();
            v3[v4].name = ((String)v5);
            v3[v4].zzga = zze.zza(((List)v1), v6);
        }

        v0.zzfy = v3;
        return new zzf(v0, ((List)v1));
    }

    private static zzi zza(List arg13, Object arg14) {
        String v0_1;
        String v14;
        Object v10;
        zzi v0 = new zzi();
        int v1 = 14;
        if(arg14 == null) {
            v0.type = v1;
            return v0;
        }

        v0.zzgc = new zzj();
        int v3 = 2;
        if((arg14 instanceof String)) {
            v0.type = v3;
            v0.zzgc.zzge = ((String)arg14);
        }
        else {
            int v4 = 6;
            if((arg14 instanceof Integer)) {
                v0.type = v4;
                v0.zzgc.zzgi = ((Integer)arg14).intValue();
            }
            else if((arg14 instanceof Long)) {
                v0.type = 5;
                v0.zzgc.zzgh = ((Long)arg14).longValue();
            }
            else if((arg14 instanceof Double)) {
                v0.type = 3;
                v0.zzgc.zzgf = ((Double)arg14).doubleValue();
            }
            else if((arg14 instanceof Float)) {
                v0.type = 4;
                v0.zzgc.zzgg = ((Float)arg14).floatValue();
            }
            else if((arg14 instanceof Boolean)) {
                v0.type = 8;
                v0.zzgc.zzgk = ((Boolean)arg14).booleanValue();
            }
            else if((arg14 instanceof Byte)) {
                v0.type = 7;
                v0.zzgc.zzgj = ((Byte)arg14).byteValue();
            }
            else if((arg14 instanceof byte[])) {
                v0.type = 1;
                v0.zzgc.zzgd = ((byte[])arg14);
            }
            else if((arg14 instanceof String[])) {
                v0.type = 11;
                v0.zzgc.zzgn = ((String[])arg14);
            }
            else if((arg14 instanceof long[])) {
                v0.type = 12;
                v0.zzgc.zzgo = ((long[])arg14);
            }
            else if((arg14 instanceof float[])) {
                v0.type = 15;
                v0.zzgc.zzgp = ((float[])arg14);
            }
            else if((arg14 instanceof Asset)) {
                v0.type = 13;
                zzj v1_1 = v0.zzgc;
                arg13.add(arg14);
                v1_1.zzgq = ((long)(arg13.size() - 1));
            }
            else {
                int v6 = 0;
                int v7 = 9;
                if((arg14 instanceof DataMap)) {
                    v0.type = v7;
                    TreeSet v1_2 = new TreeSet(((DataMap)arg14).keySet());
                    zzh[] v2 = new zzh[v1_2.size()];
                    Iterator v1_3 = v1_2.iterator();
                    while(v1_3.hasNext()) {
                        Object v3_1 = v1_3.next();
                        v2[v6] = new zzh();
                        v2[v6].name = ((String)v3_1);
                        v2[v6].zzga = zze.zza(arg13, ((DataMap)arg14).get(((String)v3_1)));
                        ++v6;
                    }

                    v0.zzgc.zzgl = v2;
                }
                else if((arg14 instanceof ArrayList)) {
                    v0.type = 10;
                    zzi[] v2_1 = new zzi[((ArrayList)arg14).size()];
                    int v8 = ((ArrayList)arg14).size();
                    Object v9 = null;
                    int v5 = 14;
                    while(true) {
                        if(v6 < v8) {
                            v10 = ((ArrayList)arg14).get(v6);
                            zzi v11 = zze.zza(arg13, v10);
                            if(v11.type != v1 && v11.type != v3 && v11.type != v4) {
                                if(v11.type == v7) {
                                }
                                else {
                                    v14 = String.valueOf(v10.getClass());
                                    StringBuilder v1_4 = new StringBuilder(String.valueOf(v14).length() + 130);
                                    v1_4.append("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a ");
                                    v1_4.append(v14);
                                    throw new IllegalArgumentException(v1_4.toString());
                                }
                            }

                            if(v5 != v1 || v11.type == v1) {
                                if(v11.type == v5) {
                                    goto label_175;
                                }

                                break;
                            }
                            else {
                                v5 = v11.type;
                                v9 = v10;
                            }

                        label_175:
                            v2_1[v6] = v11;
                            ++v6;
                            continue;
                        }
                        else {
                            goto label_200;
                        }
                    }

                    v14 = String.valueOf(v9.getClass());
                    v0_1 = String.valueOf(v10.getClass());
                    StringBuilder v2_2 = new StringBuilder(String.valueOf(v14).length() + 80 + String.valueOf(v0_1).length());
                    v2_2.append("ArrayList elements must all be of the sameclass, but this one contains a ");
                    v2_2.append(v14);
                    v2_2.append(" and a ");
                    v2_2.append(v0_1);
                    throw new IllegalArgumentException(v2_2.toString());
                label_200:
                    v0.zzgc.zzgm = v2_1;
                }
                else {
                    goto label_203;
                }
            }
        }

        return v0;
    label_203:
        v0_1 = "newFieldValueFromValue: unexpected value ";
        v14 = String.valueOf(arg14.getClass().getSimpleName());
        v14 = v14.length() != 0 ? v0_1.concat(v14) : new String(v0_1);
        throw new RuntimeException(v14);
    }

    public static DataMap zza(zzf arg7) {
        DataMap v0 = new DataMap();
        zzh[] v1 = arg7.zzfw.zzfy;
        int v2 = v1.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            zze.zza(arg7.zzfx, v0, v1[v3].name, v1[v3].zzga);
        }

        return v0;
    }

    private static void zza(List arg16, DataMap arg17, String arg18, zzi arg19) {
        int v14_1;
        StringBuilder v4_3;
        int v1_2;
        zzi v14;
        int v5_1;
        List v0 = arg16;
        DataMap v1 = arg17;
        String v2 = arg18;
        zzi v3 = arg19;
        int v4 = v3.type;
        String v5 = null;
        int v6 = 14;
        if(v4 == v6) {
            v1.putString(v2, v5);
            return;
        }

        zzj v3_1 = v3.zzgc;
        if(v4 == 1) {
            v1.putByteArray(v2, v3_1.zzgd);
            return;
        }

        if(v4 == 11) {
            v1.putStringArray(v2, v3_1.zzgn);
            return;
        }

        if(v4 == 12) {
            v1.putLongArray(v2, v3_1.zzgo);
            return;
        }

        if(v4 == 15) {
            v1.putFloatArray(v2, v3_1.zzgp);
            return;
        }

        int v7 = 2;
        if(v4 == v7) {
            v1.putString(v2, v3_1.zzge);
            return;
        }

        if(v4 == 3) {
            v1.putDouble(v2, v3_1.zzgf);
            return;
        }

        if(v4 == 4) {
            v1.putFloat(v2, v3_1.zzgg);
            return;
        }

        if(v4 == 5) {
            v1.putLong(v2, v3_1.zzgh);
            return;
        }

        int v8 = 6;
        if(v4 == v8) {
            v1.putInt(v2, v3_1.zzgi);
            return;
        }

        if(v4 == 7) {
            v1.putByte(v2, ((byte)v3_1.zzgj));
            return;
        }

        if(v4 == 8) {
            v1.putBoolean(v2, v3_1.zzgk);
            return;
        }

        if(v4 == 13) {
            if(v0 == null) {
                String v1_1 = "populateBundle: unexpected type for: ";
                v2 = String.valueOf(arg18);
                v1_1 = v2.length() != 0 ? v1_1.concat(v2) : new String(v1_1);
                throw new RuntimeException(v1_1);
            }

            v1.putAsset(v2, v0.get(((int)v3_1.zzgq)));
            return;
        }

        int v10 = 9;
        if(v4 == v10) {
            DataMap v4_1 = new DataMap();
            zzh[] v3_2 = v3_1.zzgl;
            v5_1 = v3_2.length;
            int v9;
            for(v9 = 0; v9 < v5_1; ++v9) {
                zze.zza(v0, v4_1, v3_2[v9].name, v3_2[v9].zzga);
            }

            v1.putDataMap(v2, v4_1);
            return;
        }

        if(v4 != 10) {
            goto label_241;
        }

        zzi[] v4_2 = v3_1.zzgm;
        int v11 = v4_2.length;
        int v12 = 0;
        int v13 = 14;
        while(true) {
            if(v12 >= v11) {
                goto label_163;
            }

            v14 = v4_2[v12];
            if(v13 == v6) {
                if(v14.type != v10 && v14.type != v7) {
                    if(v14.type == v8) {
                    }
                    else if(v14.type == v6) {
                        goto label_142;
                    }
                    else {
                        v1_2 = v14.type;
                        v4_3 = new StringBuilder(String.valueOf(arg18).length() + 48);
                        v4_3.append("Unexpected TypedValue type: ");
                        v4_3.append(v1_2);
                        v4_3.append(" for key ");
                        v4_3.append(v2);
                        throw new IllegalArgumentException(v4_3.toString());
                    }
                }

                v13 = v14.type;
            }
            else if(v14.type != v13) {
                break;
            }

        label_142:
            ++v12;
        }

        v1_2 = v14.type;
        v4_3 = new StringBuilder(String.valueOf(arg18).length() + 126);
        v4_3.append("The ArrayList elements should all be the same type, but ArrayList with key ");
        v4_3.append(v2);
        v4_3.append(" contains items of type ");
        v4_3.append(v13);
        v4_3.append(" and ");
        v4_3.append(v1_2);
        throw new IllegalArgumentException(v4_3.toString());
    label_163:
        ArrayList v4_4 = new ArrayList(v3_1.zzgm.length);
        zzi[] v3_3 = v3_1.zzgm;
        v11 = v3_3.length;
        v12 = 0;
        while(true) {
            v14_1 = 39;
            if(v12 >= v11) {
                goto label_218;
            }

            zzi v15 = v3_3[v12];
            if(v15.type == v6) {
            label_175:
                v4_4.add(v5);
            }
            else if(v13 == v10) {
                DataMap v9_1 = new DataMap();
                zzh[] v14_2 = v15.zzgc.zzgl;
                int v15_1 = v14_2.length;
                for(v5_1 = 0; v5_1 < v15_1; ++v5_1) {
                    zze.zza(v0, v9_1, v14_2[v5_1].name, v14_2[v5_1].zzga);
                }

                v4_4.add(v9_1);
            }
            else if(v13 == v7) {
                v5 = v15.zzgc.zzge;
                goto label_175;
            }
            else if(v13 == v8) {
                Integer v5_2 = Integer.valueOf(v15.zzgc.zzgi);
                goto label_175;
            }
            else {
                break;
            }

            ++v12;
            Object v5_3 = null;
            v6 = 14;
            v10 = 9;
        }

        StringBuilder v1_3 = new StringBuilder(v14_1);
        v1_3.append("Unexpected typeOfArrayList: ");
        v1_3.append(v13);
        throw new IllegalArgumentException(v1_3.toString());
    label_218:
        if(v13 == 14) {
            v1.putStringArrayList(v2, v4_4);
            return;
        }

        if(v13 == 9) {
            v1.putDataMapArrayList(v2, v4_4);
            return;
        }

        if(v13 == v7) {
            v1.putStringArrayList(v2, v4_4);
            return;
        }

        if(v13 == v8) {
            v1.putIntegerArrayList(v2, v4_4);
            return;
        }

        v1_3 = new StringBuilder(v14_1);
        v1_3.append("Unexpected typeOfArrayList: ");
        v1_3.append(v13);
        throw new IllegalStateException(v1_3.toString());
    label_241:
        StringBuilder v2_1 = new StringBuilder(43);
        v2_1.append("populateBundle: unexpected type ");
        v2_1.append(v4);
        throw new RuntimeException(v2_1.toString());
    }
}

