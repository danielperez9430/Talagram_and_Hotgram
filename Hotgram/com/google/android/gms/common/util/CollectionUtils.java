package com.google.android.gms.common.util;

import android.support.v4.f.a;
import android.support.v4.f.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CollectionUtils {
    private CollectionUtils() {
        super();
    }

    public static Map inOrderMapOf() {
        return CollectionUtils.mapOf();
    }

    public static Map inOrderMapOf(Object arg0, Object arg1) {
        return CollectionUtils.mapOf(arg0, arg1);
    }

    public static Map inOrderMapOf(Object arg2, Object arg3, Object arg4, Object arg5) {
        Map v0 = CollectionUtils.zzg(2, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        return Collections.unmodifiableMap(v0);
    }

    public static Map inOrderMapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        Map v0 = CollectionUtils.zzg(3, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        return Collections.unmodifiableMap(v0);
    }

    public static Map inOrderMapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) {
        Map v0 = CollectionUtils.zzg(4, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        v0.put(arg8, arg9);
        return Collections.unmodifiableMap(v0);
    }

    public static Map inOrderMapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11) {
        Map v0 = CollectionUtils.zzg(5, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        v0.put(arg8, arg9);
        v0.put(arg10, arg11);
        return Collections.unmodifiableMap(v0);
    }

    public static Map inOrderMapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13) {
        Map v0 = CollectionUtils.zzg(6, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        v0.put(arg8, arg9);
        v0.put(arg10, arg11);
        v0.put(arg12, arg13);
        return Collections.unmodifiableMap(v0);
    }

    public static Map inOrderMapOfKeyValueArrays(Object[] arg2, Object[] arg3) {
        CollectionUtils.zza(arg2, arg3);
        int v0 = arg2.length;
        switch(v0) {
            case 0: {
                goto label_11;
            }
            case 1: {
                goto label_7;
            }
        }

        return Collections.unmodifiableMap(CollectionUtils.zzb(v0, false, arg2, arg3));
    label_7:
        return CollectionUtils.inOrderMapOf(arg2[0], arg3[0]);
    label_11:
        return CollectionUtils.inOrderMapOf();
    }

    public static Set inOrderSetOf() {
        return CollectionUtils.setOf();
    }

    public static Set inOrderSetOf(Object arg0) {
        return CollectionUtils.setOf(arg0);
    }

    public static Set inOrderSetOf(Object arg2, Object arg3) {
        Set v0 = CollectionUtils.zze(2, false);
        v0.add(arg2);
        v0.add(arg3);
        return Collections.unmodifiableSet(v0);
    }

    public static Set inOrderSetOf(Object arg2, Object arg3, Object arg4) {
        Set v0 = CollectionUtils.zze(3, false);
        v0.add(arg2);
        v0.add(arg3);
        v0.add(arg4);
        return Collections.unmodifiableSet(v0);
    }

    public static Set inOrderSetOf(Object arg2, Object arg3, Object arg4, Object arg5) {
        Set v0 = CollectionUtils.zze(4, false);
        v0.add(arg2);
        v0.add(arg3);
        v0.add(arg4);
        v0.add(arg5);
        return Collections.unmodifiableSet(v0);
    }

    public static Set inOrderSetOf(Object[] arg4) {
        int v1 = 2;
        switch(arg4.length) {
            case 0: {
                goto label_28;
            }
            case 1: {
                goto label_25;
            }
            case 2: {
                goto label_21;
            }
            case 3: {
                goto label_16;
            }
            case 4: {
                goto label_9;
            }
        }

        return Collections.unmodifiableSet(CollectionUtils.zzb(arg4.length, false, arg4));
    label_21:
        return CollectionUtils.inOrderSetOf(arg4[0], arg4[1]);
    label_25:
        return CollectionUtils.inOrderSetOf(arg4[0]);
    label_9:
        return CollectionUtils.inOrderSetOf(arg4[0], arg4[1], arg4[v1], arg4[3]);
    label_28:
        return CollectionUtils.inOrderSetOf();
    label_16:
        return CollectionUtils.inOrderSetOf(arg4[0], arg4[1], arg4[v1]);
    }

    public static boolean isEmpty(Collection arg0) {
        if(arg0 == null) {
            return 1;
        }

        return arg0.isEmpty();
    }

    public static boolean isEmpty(Map arg0) {
        if(arg0 == null) {
            return 1;
        }

        return arg0.isEmpty();
    }

    @Deprecated public static List listOf() {
        return Collections.emptyList();
    }

    @Deprecated public static List listOf(Object arg0) {
        return Collections.singletonList(arg0);
    }

    @Deprecated public static List listOf(Object[] arg1) {
        switch(arg1.length) {
            case 0: {
                goto label_9;
            }
            case 1: {
                goto label_5;
            }
        }

        return Collections.unmodifiableList(Arrays.asList(arg1));
    label_5:
        return CollectionUtils.listOf(arg1[0]);
    label_9:
        return CollectionUtils.listOf();
    }

    public static Map mapOf() {
        return Collections.emptyMap();
    }

    public static Map mapOf(Object arg0, Object arg1) {
        return Collections.singletonMap(arg0, arg1);
    }

    public static Map mapOf(Object arg2, Object arg3, Object arg4, Object arg5) {
        Map v0 = CollectionUtils.zzf(2, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        return Collections.unmodifiableMap(v0);
    }

    public static Map mapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        Map v0 = CollectionUtils.zzf(3, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        return Collections.unmodifiableMap(v0);
    }

    public static Map mapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) {
        Map v0 = CollectionUtils.zzf(4, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        v0.put(arg8, arg9);
        return Collections.unmodifiableMap(v0);
    }

    public static Map mapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11) {
        Map v0 = CollectionUtils.zzf(5, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        v0.put(arg8, arg9);
        v0.put(arg10, arg11);
        return Collections.unmodifiableMap(v0);
    }

    public static Map mapOf(Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13) {
        Map v0 = CollectionUtils.zzf(6, false);
        v0.put(arg2, arg3);
        v0.put(arg4, arg5);
        v0.put(arg6, arg7);
        v0.put(arg8, arg9);
        v0.put(arg10, arg11);
        v0.put(arg12, arg13);
        return Collections.unmodifiableMap(v0);
    }

    public static Map mapOfKeyValueArrays(Object[] arg2, Object[] arg3) {
        CollectionUtils.zza(arg2, arg3);
        switch(arg2.length) {
            case 0: {
                goto label_12;
            }
            case 1: {
                goto label_8;
            }
        }

        return Collections.unmodifiableMap(CollectionUtils.zza(arg2.length, false, arg2, arg3));
    label_8:
        return CollectionUtils.mapOf(arg2[0], arg3[0]);
    label_12:
        return CollectionUtils.mapOf();
    }

    public static Map mutableInOrderMapOf() {
        return new LinkedHashMap();
    }

    public static Map mutableInOrderMapOf(Object arg1, Object arg2) {
        return CollectionUtils.mutableInOrderMapOfWithSize(1, arg1, arg2);
    }

    public static Map mutableInOrderMapOf(Object arg1, Object arg2, Object arg3, Object arg4) {
        return CollectionUtils.mutableInOrderMapOfWithSize(2, arg1, arg2, arg3, arg4);
    }

    public static Map mutableInOrderMapOf(Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12) {
        return CollectionUtils.mutableInOrderMapOfWithSize(3, arg7, arg8, arg9, arg10, arg11, arg12);
    }

    public static Map mutableInOrderMapOfKeyValueArrays(Object[] arg2, Object[] arg3) {
        CollectionUtils.zza(arg2, arg3);
        int v0 = arg2.length;
        if(v0 == 0) {
            return CollectionUtils.mutableInOrderMapOf();
        }

        return CollectionUtils.zzb(v0, true, arg2, arg3);
    }

    public static Map mutableInOrderMapOfKeyValueArraysWithSize(int arg2, Object[] arg3, Object[] arg4) {
        CollectionUtils.zza(arg3, arg4);
        int v0 = Math.max(arg2, arg3.length);
        if(v0 == 0) {
            return CollectionUtils.mutableInOrderMapOf();
        }

        if(arg3.length == 0) {
            return CollectionUtils.mutableInOrderMapOfWithSize(v0);
        }

        return CollectionUtils.zzb(arg2, true, arg3, arg4);
    }

    public static Map mutableInOrderMapOfWithSize(int arg1, Object arg2, Object arg3) {
        Map v1 = CollectionUtils.zzg(Math.max(arg1, 1), true);
        v1.put(arg2, arg3);
        return v1;
    }

    public static Map mutableInOrderMapOfWithSize(int arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        Map v1 = CollectionUtils.zzg(Math.max(arg1, 2), true);
        v1.put(arg2, arg3);
        v1.put(arg4, arg5);
        return v1;
    }

    public static Map mutableInOrderMapOfWithSize(int arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        Map v1 = CollectionUtils.zzg(Math.max(arg1, 3), true);
        v1.put(arg2, arg3);
        v1.put(arg4, arg5);
        v1.put(arg6, arg7);
        return v1;
    }

    public static Map mutableInOrderMapOfWithSize(int arg1) {
        if(arg1 == 0) {
            return CollectionUtils.mutableInOrderMapOf();
        }

        return CollectionUtils.zzg(arg1, true);
    }

    public static Set mutableInOrderSetOf() {
        return new LinkedHashSet();
    }

    public static Set mutableInOrderSetOf(Object arg1) {
        return CollectionUtils.mutableInOrderSetOfWithSize(1, arg1);
    }

    public static Set mutableInOrderSetOf(Object arg1, Object arg2) {
        return CollectionUtils.mutableInOrderSetOfWithSize(2, arg1, arg2);
    }

    public static Set mutableInOrderSetOf(Object[] arg2) {
        if(arg2.length == 0) {
            return CollectionUtils.mutableInOrderSetOf();
        }

        return CollectionUtils.zzb(arg2.length, true, arg2);
    }

    public static Set mutableInOrderSetOfWithSize(int arg1, Object arg2) {
        Set v1 = CollectionUtils.zze(Math.max(arg1, 1), true);
        v1.add(arg2);
        return v1;
    }

    public static Set mutableInOrderSetOfWithSize(int arg1, Object arg2, Object arg3) {
        Set v1 = CollectionUtils.zze(Math.max(arg1, 2), true);
        v1.add(arg2);
        v1.add(arg3);
        return v1;
    }

    public static Set mutableInOrderSetOfWithSize(int arg1) {
        if(arg1 == 0) {
            return CollectionUtils.mutableInOrderSetOf();
        }

        return CollectionUtils.zze(arg1, true);
    }

    public static Set mutableInOrderSetOfWithSize(int arg2, Object[] arg3) {
        int v0 = Math.max(arg2, arg3.length);
        if(v0 == 0) {
            return CollectionUtils.mutableSetOf();
        }

        if(arg3.length == 0) {
            return CollectionUtils.mutableInOrderSetOfWithSize(arg2);
        }

        return CollectionUtils.zzb(v0, true, arg3);
    }

    public static List mutableListOf() {
        return new ArrayList();
    }

    public static List mutableListOf(Object arg1) {
        return CollectionUtils.mutableListOfWithSize(1, arg1);
    }

    public static List mutableListOf(Object arg1, Object arg2) {
        return CollectionUtils.mutableListOfWithSize(2, arg1, arg2);
    }

    public static List mutableListOf(Object[] arg1) {
        if(arg1.length == 0) {
            return CollectionUtils.mutableListOf();
        }

        return new ArrayList(Arrays.asList(arg1));
    }

    public static List mutableListOfWithSize(int arg1, Object arg2) {
        List v1 = CollectionUtils.zzc(Math.max(arg1, 1), true);
        v1.add(arg2);
        return v1;
    }

    public static List mutableListOfWithSize(int arg1, Object arg2, Object arg3) {
        List v1 = CollectionUtils.zzc(Math.max(arg1, 2), true);
        v1.add(arg2);
        v1.add(arg3);
        return v1;
    }

    public static List mutableListOfWithSize(int arg1) {
        if(arg1 == 0) {
            return CollectionUtils.mutableListOf();
        }

        return CollectionUtils.zzc(arg1, true);
    }

    public static List mutableListOfWithSize(int arg2, Object[] arg3) {
        int v0 = Math.max(arg2, arg3.length);
        if(v0 == 0) {
            return CollectionUtils.mutableListOf();
        }

        if(arg3.length == 0) {
            return CollectionUtils.mutableListOfWithSize(arg2);
        }

        if(arg3.length == v0) {
            return new ArrayList(Arrays.asList(arg3));
        }

        List v2 = CollectionUtils.zzc(v0, true);
        v2.addAll(Arrays.asList(arg3));
        return v2;
    }

    public static Map mutableMapOf() {
        return new a();
    }

    public static Map mutableMapOf(Object arg1, Object arg2) {
        return CollectionUtils.mutableMapOfWithSize(1, arg1, arg2);
    }

    public static Map mutableMapOf(Object arg1, Object arg2, Object arg3, Object arg4) {
        return CollectionUtils.mutableMapOfWithSize(2, arg1, arg2, arg3, arg4);
    }

    public static Map mutableMapOf(Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12) {
        return CollectionUtils.mutableMapOfWithSize(3, arg7, arg8, arg9, arg10, arg11, arg12);
    }

    public static Map mutableMapOfKeyValueArrays(Object[] arg2, Object[] arg3) {
        CollectionUtils.zza(arg2, arg3);
        int v0 = arg2.length;
        if(v0 == 0) {
            return CollectionUtils.mutableMapOf();
        }

        return CollectionUtils.zza(v0, true, arg2, arg3);
    }

    public static Map mutableMapOfKeyValueArraysWithSize(int arg2, Object[] arg3, Object[] arg4) {
        CollectionUtils.zza(arg3, arg4);
        int v0 = Math.max(arg2, arg3.length);
        if(v0 == 0) {
            return CollectionUtils.mutableMapOf();
        }

        if(arg3.length == 0) {
            return CollectionUtils.mutableMapOfWithSize(arg2);
        }

        return CollectionUtils.zza(v0, true, arg3, arg4);
    }

    public static Map mutableMapOfWithSize(int arg1, Object arg2, Object arg3) {
        Map v1 = CollectionUtils.zzf(Math.max(arg1, 1), true);
        v1.put(arg2, arg3);
        return v1;
    }

    public static Map mutableMapOfWithSize(int arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        Map v1 = CollectionUtils.zzf(Math.max(arg1, 2), true);
        v1.put(arg2, arg3);
        v1.put(arg4, arg5);
        return v1;
    }

    public static Map mutableMapOfWithSize(int arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        Map v1 = CollectionUtils.zzf(Math.max(arg1, 3), true);
        v1.put(arg2, arg3);
        v1.put(arg4, arg5);
        v1.put(arg6, arg7);
        return v1;
    }

    public static Map mutableMapOfWithSize(int arg1) {
        if(arg1 == 0) {
            return CollectionUtils.mutableMapOf();
        }

        return CollectionUtils.zzf(arg1, true);
    }

    public static Set mutableSetOf() {
        return new b();
    }

    public static Set mutableSetOf(Object arg1) {
        return CollectionUtils.mutableSetOfWithSize(1, arg1);
    }

    public static Set mutableSetOf(Object arg1, Object arg2) {
        return CollectionUtils.mutableSetOfWithSize(2, arg1, arg2);
    }

    public static Set mutableSetOf(Object[] arg2) {
        if(arg2.length == 0) {
            return CollectionUtils.mutableSetOf();
        }

        return CollectionUtils.zza(arg2.length, true, arg2);
    }

    public static Set mutableSetOfWithSize(int arg1, Object arg2) {
        Set v1 = CollectionUtils.zzd(Math.max(arg1, 1), true);
        v1.add(arg2);
        return v1;
    }

    public static Set mutableSetOfWithSize(int arg1, Object arg2, Object arg3) {
        Set v1 = CollectionUtils.zzd(Math.max(arg1, 2), true);
        v1.add(arg2);
        v1.add(arg3);
        return v1;
    }

    public static Set mutableSetOfWithSize(int arg1) {
        if(arg1 == 0) {
            return CollectionUtils.mutableSetOf();
        }

        return CollectionUtils.zzd(arg1, true);
    }

    public static Set mutableSetOfWithSize(int arg2, Object[] arg3) {
        int v0 = Math.max(arg2, arg3.length);
        if(v0 == 0) {
            return CollectionUtils.mutableSetOf();
        }

        if(arg3.length == 0) {
            return CollectionUtils.mutableSetOfWithSize(arg2);
        }

        return CollectionUtils.zza(v0, true, arg3);
    }

    @Deprecated public static Set setOf() {
        return Collections.emptySet();
    }

    @Deprecated public static Set setOf(Object arg0) {
        return Collections.singleton(arg0);
    }

    @Deprecated public static Set setOf(Object arg2, Object arg3) {
        Set v0 = CollectionUtils.zzd(2, false);
        v0.add(arg2);
        v0.add(arg3);
        return Collections.unmodifiableSet(v0);
    }

    @Deprecated public static Set setOf(Object arg2, Object arg3, Object arg4) {
        Set v0 = CollectionUtils.zzd(3, false);
        v0.add(arg2);
        v0.add(arg3);
        v0.add(arg4);
        return Collections.unmodifiableSet(v0);
    }

    @Deprecated public static Set setOf(Object arg2, Object arg3, Object arg4, Object arg5) {
        Set v0 = CollectionUtils.zzd(4, false);
        v0.add(arg2);
        v0.add(arg3);
        v0.add(arg4);
        v0.add(arg5);
        return Collections.unmodifiableSet(v0);
    }

    @Deprecated public static Set setOf(Object[] arg4) {
        int v1 = 2;
        switch(arg4.length) {
            case 0: {
                goto label_28;
            }
            case 1: {
                goto label_25;
            }
            case 2: {
                goto label_21;
            }
            case 3: {
                goto label_16;
            }
            case 4: {
                goto label_9;
            }
        }

        return Collections.unmodifiableSet(CollectionUtils.zza(arg4.length, false, arg4));
    label_21:
        return CollectionUtils.setOf(arg4[0], arg4[1]);
    label_25:
        return CollectionUtils.setOf(arg4[0]);
    label_9:
        return CollectionUtils.setOf(arg4[0], arg4[1], arg4[v1], arg4[3]);
    label_28:
        return CollectionUtils.setOf();
    label_16:
        return CollectionUtils.setOf(arg4[0], arg4[1], arg4[v1]);
    }

    private static void zza(Object[] arg3, Object[] arg4) {
        if(arg3.length == arg4.length) {
            return;
        }

        int v3 = arg3.length;
        int v4 = arg4.length;
        StringBuilder v2 = new StringBuilder(66);
        v2.append("Key and values array lengths not equal: ");
        v2.append(v3);
        v2.append(" != ");
        v2.append(v4);
        throw new IllegalArgumentException(v2.toString());
    }

    private static Map zza(int arg0, boolean arg1, Object[] arg2, Object[] arg3) {
        Map v0 = CollectionUtils.zzf(arg0, arg1);
        CollectionUtils.zza(v0, arg2, arg3);
        return v0;
    }

    private static Set zza(int arg0, boolean arg1, Object[] arg2) {
        Set v0 = CollectionUtils.zzd(arg0, arg1);
        Collections.addAll(((Collection)v0), arg2);
        return v0;
    }

    private static void zza(Map arg3, Object[] arg4, Object[] arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.length; ++v0) {
            arg3.put(arg4[v0], arg5[v0]);
        }
    }

    private static Map zzb(int arg0, boolean arg1, Object[] arg2, Object[] arg3) {
        Map v0 = CollectionUtils.zzg(arg0, arg1);
        CollectionUtils.zza(v0, arg2, arg3);
        return v0;
    }

    private static Set zzb(int arg0, boolean arg1, Object[] arg2) {
        Set v0 = CollectionUtils.zze(arg0, arg1);
        Collections.addAll(((Collection)v0), arg2);
        return v0;
    }

    private static List zzc(int arg0, boolean arg1) {
        return new ArrayList(arg0);
    }

    private static Set zzd(int arg1, boolean arg2) {
        float v0 = arg2 ? 0.75f : 1f;
        int v2 = arg2 ? 128 : 256;
        if(arg1 <= v2) {
            return new b(arg1);
        }

        return new HashSet(arg1, v0);
    }

    private static Set zze(int arg1, boolean arg2) {
        float v2 = arg2 ? 0.75f : 1f;
        return new LinkedHashSet(arg1, v2);
    }

    private static Map zzf(int arg1, boolean arg2) {
        float v0 = arg2 ? 0.75f : 1f;
        int v2 = arg2 ? 128 : 256;
        if(arg1 <= v2) {
            return new a(arg1);
        }

        return new HashMap(arg1, v0);
    }

    private static Map zzg(int arg1, boolean arg2) {
        float v2 = arg2 ? 0.75f : 1f;
        return new LinkedHashMap(arg1, v2);
    }
}

