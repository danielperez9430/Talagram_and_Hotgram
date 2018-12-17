package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import javax.annotation.concurrent.GuardedBy;

public final class DynamiteModule {
    @DynamiteApi public class DynamiteLoaderClassLoader {
        @GuardedBy(value="DynamiteLoaderClassLoader.class") public static ClassLoader sClassLoader;

        public DynamiteLoaderClassLoader() {
            super();
        }
    }

    public class LoadingException extends Exception {
        LoadingException(String arg1, Throwable arg2, zza arg3) {
            this(arg1, arg2);
        }

        LoadingException(String arg1, zza arg2) {
            this(arg1);
        }

        private LoadingException(String arg1) {
            super(arg1);
        }

        private LoadingException(String arg1, Throwable arg2) {
            super(arg1, arg2);
        }
    }

    public interface VersionPolicy {
        public interface IVersions {
            int getLocalVersion(Context arg1, String arg2);

            int getRemoteVersion(Context arg1, String arg2, boolean arg3);
        }

        public class SelectionResult {
            public int localVersion;
            public int remoteVersion;
            public int selection;

            public SelectionResult() {
                super();
                this.localVersion = 0;
                this.remoteVersion = 0;
                this.selection = 0;
            }
        }

        SelectionResult selectModule(Context arg1, String arg2, IVersions arg3);
    }

    final class com.google.android.gms.dynamite.DynamiteModule$zza {
        public Cursor zzaby;

        com.google.android.gms.dynamite.DynamiteModule$zza(zza arg1) {
            this();
        }

        private com.google.android.gms.dynamite.DynamiteModule$zza() {
            super();
        }
    }

    final class zzb implements IVersions {
        private final int zzabz;
        private final int zzaca;

        public zzb(int arg1, int arg2) {
            super();
            this.zzabz = arg1;
            this.zzaca = 0;
        }

        public final int getLocalVersion(Context arg1, String arg2) {
            return this.zzabz;
        }

        public final int getRemoteVersion(Context arg1, String arg2, boolean arg3) {
            return 0;
        }
    }

    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION;
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING;
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION;
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING;
    public static final VersionPolicy PREFER_LOCAL;
    public static final VersionPolicy PREFER_REMOTE;
    @GuardedBy(value="DynamiteModule.class") private static Boolean zzabr;
    @GuardedBy(value="DynamiteModule.class") private static IDynamiteLoader zzabs;
    @GuardedBy(value="DynamiteModule.class") private static IDynamiteLoaderV2 zzabt;
    @GuardedBy(value="DynamiteModule.class") private static String zzabu;
    private static final ThreadLocal zzabv;
    private static final IVersions zzabw;
    private final Context zzabx;

    static {
        DynamiteModule.zzabv = new ThreadLocal();
        DynamiteModule.zzabw = new zza();
        DynamiteModule.PREFER_REMOTE = new com.google.android.gms.dynamite.zzb();
        DynamiteModule.PREFER_LOCAL = new zzc();
        DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
        DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
        DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
        DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING = new zzg();
    }

    private DynamiteModule(Context arg1) {
        super();
        this.zzabx = Preconditions.checkNotNull(arg1);
    }

    public static int getLocalVersion(Context arg4, String arg5) {
        String v1_1;
        StringBuilder v2;
        try {
            ClassLoader v4_1 = arg4.getApplicationContext().getClassLoader();
            v2 = new StringBuilder(String.valueOf(arg5).length() + 61);
            v2.append("com.google.android.gms.dynamite.descriptors.");
            v2.append(arg5);
            v2.append(".ModuleDescriptor");
            Class v4_2 = v4_1.loadClass(v2.toString());
            Field v1 = v4_2.getDeclaredField("MODULE_ID");
            Field v4_3 = v4_2.getDeclaredField("MODULE_VERSION");
            Object v2_1 = null;
            if(!v1.get(v2_1).equals(arg5)) {
                v1_1 = String.valueOf(v1.get(v2_1));
                StringBuilder v3 = new StringBuilder(String.valueOf(v1_1).length() + 51 + String.valueOf(arg5).length());
                v3.append("Module descriptor id \'");
                v3.append(v1_1);
                v3.append("\' didn\'t match expected id \'");
                v3.append(arg5);
                v3.append("\'");
                Log.e("DynamiteModule", v3.toString());
                return 0;
            }

            return v4_3.getInt(v2_1);
        }
        catch(Exception v4) {
            arg5 = "DynamiteModule";
            v1_1 = "Failed to load module descriptor class: ";
            String v4_4 = String.valueOf(v4.getMessage());
            v4_4 = v4_4.length() != 0 ? v1_1.concat(v4_4) : new String(v1_1);
            Log.e(arg5, v4_4);
        }
        catch(ClassNotFoundException ) {
            v2 = new StringBuilder(String.valueOf(arg5).length() + 45);
            v2.append("Local module descriptor class for ");
            v2.append(arg5);
            v2.append(" not found.");
            Log.w("DynamiteModule", v2.toString());
        }

        return 0;
    }

    public final Context getModuleContext() {
        return this.zzabx;
    }

    public static Uri getQueryUri(String arg2, boolean arg3) {
        String v3 = arg3 ? "api_force_staging" : "api";
        StringBuilder v1 = new StringBuilder(String.valueOf(v3).length() + 42 + String.valueOf(arg2).length());
        v1.append("content://com.google.android.gms.chimera/");
        v1.append(v3);
        v1.append("/");
        v1.append(arg2);
        return Uri.parse(v1.toString());
    }

    public static int getRemoteVersion(Context arg1, String arg2) {
        return DynamiteModule.getRemoteVersion(arg1, arg2, false);
    }

    public static int getRemoteVersion(Context arg8, String arg9, boolean arg10) {
        int v4_1;
        Boolean v2_2;
        Object v3;
        Field v2;
        Class v1_2;
        Boolean v1;
        Class v0 = DynamiteModule.class;
        __monitor_enter(v0);
        try {
            v1 = DynamiteModule.zzabr;
            if(v1 != null) {
                goto label_75;
            }

            try {
                v1_2 = arg8.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                v2 = v1_2.getDeclaredField("sClassLoader");
                __monitor_enter(v1_2);
                v3 = null;
            }
            catch(ClassNotFoundException v1_1) {
                goto label_61;
            }
        }
        catch(Throwable v8) {
            goto label_97;
        }

        try {
            Object v4 = v2.get(v3);
            if(v4 != null) {
                if(v4 == ClassLoader.getSystemClassLoader()) {
                    goto label_17;
                }

                try {
                    DynamiteModule.zza(((ClassLoader)v4));
                    goto label_20;
                }
                catch(LoadingException ) {
                label_20:
                    v2_2 = Boolean.TRUE;
                    goto label_54;
                }
            }

            if("com.google.android.gms".equals(arg8.getApplicationContext().getPackageName())) {
                v2.set(v3, ClassLoader.getSystemClassLoader());
                goto label_17;
            }

            try {
                v4_1 = DynamiteModule.zzb(arg8, arg9, arg10);
                if(DynamiteModule.zzabu != null) {
                    if(DynamiteModule.zzabu.isEmpty()) {
                    }
                    else {
                        zzh v5 = new zzh(DynamiteModule.zzabu, ClassLoader.getSystemClassLoader());
                        DynamiteModule.zza(((ClassLoader)v5));
                        v2.set(v3, v5);
                        DynamiteModule.zzabr = Boolean.TRUE;
                        goto label_45;
                    }
                }

                goto label_48;
            }
            catch(LoadingException ) {
                try {
                    v2.set(v3, ClassLoader.getSystemClassLoader());
                label_17:
                    v2_2 = Boolean.FALSE;
                label_54:
                    __monitor_exit(v1_2);
                    v1 = v2_2;
                    goto label_74;
                label_45:
                    __monitor_exit(v1_2);
                }
                catch(Throwable v2_1) {
                    goto label_58;
                }
            }
        }
        catch(Throwable v2_1) {
            goto label_58;
        }

        try {
            __monitor_exit(v0);
            return v4_1;
        }
        catch(Throwable v8) {
            goto label_97;
        }

        try {
        label_48:
            __monitor_exit(v1_2);
            goto label_49;
        label_58:
            __monitor_exit(((Class)v1));
        }
        catch(Throwable v2_1) {
            goto label_58;
        }

        try {
            throw v2_1;
        }
        catch(Throwable v8) {
        }
        catch(ClassNotFoundException v1_1) {
            try {
            label_61:
                String v1_3 = String.valueOf(v1_1);
                StringBuilder v4_2 = new StringBuilder(String.valueOf(v1_3).length() + 30);
                v4_2.append("Failed to load module via V2: ");
                v4_2.append(v1_3);
                Log.w("DynamiteModule", v4_2.toString());
                v1 = Boolean.FALSE;
            label_74:
                DynamiteModule.zzabr = v1;
                goto label_75;
            label_49:
                __monitor_exit(v0);
                return v4_1;
            label_75:
                __monitor_exit(v0);
            }
            catch(Throwable v8) {
                try {
                label_97:
                    __monitor_exit(v0);
                }
                catch(Throwable v8) {
                    goto label_97;
                }

                throw v8;
            }
        }

        if(v1.booleanValue()) {
            try {
                return DynamiteModule.zzb(arg8, arg9, arg10);
            }
            catch(LoadingException v8_1) {
                arg9 = "DynamiteModule";
                String v10 = "Failed to retrieve remote module version: ";
                String v8_2 = String.valueOf(v8_1.getMessage());
                v8_2 = v8_2.length() != 0 ? v10.concat(v8_2) : new String(v10);
                Log.w(arg9, v8_2);
                return 0;
            }
        }

        return DynamiteModule.zza(arg8, arg9, arg10);
    }

    @VisibleForTesting public static Boolean getUseV2ForTesting() {
        Boolean v1_1;
        Class v0 = DynamiteModule.class;
        __monitor_enter(v0);
        try {
            v1_1 = DynamiteModule.zzabr;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public final IBinder instantiate(String arg5) {
        try {
            return this.zzabx.getClassLoader().loadClass(arg5).newInstance();
        }
        catch(IllegalAccessException v0) {
            String v2 = "Failed to instantiate module class: ";
            arg5 = String.valueOf(arg5);
            arg5 = arg5.length() != 0 ? v2.concat(arg5) : new String(v2);
            throw new LoadingException(arg5, ((Throwable)v0), null);
        }
    }

    public static DynamiteModule load(Context arg10, VersionPolicy arg11, String arg12) {
        DynamiteModule v4_1;
        DynamiteModule v10_1;
        SelectionResult v3;
        Object v0 = DynamiteModule.zzabv.get();
        zza v2 = null;
        com.google.android.gms.dynamite.DynamiteModule$zza v1 = new com.google.android.gms.dynamite.DynamiteModule$zza(v2);
        DynamiteModule.zzabv.set(v1);
        try {
            v3 = arg11.selectModule(arg10, arg12, DynamiteModule.zzabw);
            int v5 = v3.localVersion;
            int v6 = v3.remoteVersion;
            StringBuilder v8 = new StringBuilder(String.valueOf(arg12).length() + 68 + String.valueOf(arg12).length());
            v8.append("Considering local module ");
            v8.append(arg12);
            v8.append(":");
            v8.append(v5);
            v8.append(" and remote module ");
            v8.append(arg12);
            v8.append(":");
            v8.append(v6);
            Log.i("DynamiteModule", v8.toString());
            if(v3.selection != 0) {
                v5 = -1;
                if(v3.selection == v5 && v3.localVersion == 0) {
                    goto label_108;
                }

                if(v3.selection == 1 && v3.remoteVersion == 0) {
                    goto label_108;
                }

                if(v3.selection != v5) {
                    goto label_56;
                }

                v10_1 = DynamiteModule.zzd(arg10, arg12);
                goto label_49;
            }

            goto label_108;
        }
        catch(Throwable v10) {
            goto label_127;
        }

    label_49:
        if(v1.zzaby == null) {
            goto label_53;
        }

        goto label_51;
        try {
        label_56:
            if(v3.selection != 1) {
                goto label_97;
            }

            try {
                v4_1 = DynamiteModule.zza(arg10, arg12, v3.remoteVersion);
                goto label_60;
            }
            catch(LoadingException v4) {
                try {
                    String v6_1 = "DynamiteModule";
                    String v7 = "Failed to load remote module: ";
                    String v8_1 = String.valueOf(v4.getMessage());
                    v7 = v8_1.length() != 0 ? v7.concat(v8_1) : new String(v7);
                    Log.w(v6_1, v7);
                    if(v3.localVersion != 0 && arg11.selectModule(arg10, arg12, new zzb(v3.localVersion, 0)).selection == v5) {
                        v10_1 = DynamiteModule.zzd(arg10, arg12);
                        goto label_90;
                    }

                    goto label_93;
                }
                catch(Throwable v10) {
                    goto label_127;
                }

            label_90:
                if(v1.zzaby != null) {
                }
                else {
                    goto label_53;
                }
            }
        }
        catch(Throwable v10) {
            goto label_127;
        }

    label_51:
        v1.zzaby.close();
    label_53:
        DynamiteModule.zzabv.set(v0);
        return v10_1;
        try {
        label_93:
            throw new LoadingException("Remote load failed. No local fallback found.", ((Throwable)v4), v2);
        }
        catch(Throwable v10) {
            goto label_127;
        }

    label_60:
        if(v1.zzaby != null) {
            v1.zzaby.close();
        }

        DynamiteModule.zzabv.set(v0);
        return v4_1;
        try {
        label_97:
            int v11 = v3.selection;
            StringBuilder v3_1 = new StringBuilder(47);
            v3_1.append("VersionPolicy returned invalid code:");
            v3_1.append(v11);
            throw new LoadingException(v3_1.toString(), v2);
        label_108:
            v11 = v3.localVersion;
            int v12 = v3.remoteVersion;
            StringBuilder v4_2 = new StringBuilder(91);
            v4_2.append("No acceptable module found. Local version is ");
            v4_2.append(v11);
            v4_2.append(" and remote version is ");
            v4_2.append(v12);
            v4_2.append(".");
            throw new LoadingException(v4_2.toString(), v2);
        }
        catch(Throwable v10) {
        label_127:
            if(v1.zzaby != null) {
                v1.zzaby.close();
            }

            DynamiteModule.zzabv.set(v0);
            throw v10;
        }
    }

    public static Cursor queryForDynamiteModule(Context arg6, String arg7, boolean arg8) {
        return arg6.getContentResolver().query(DynamiteModule.getQueryUri(arg7, arg8), null, null, null, null);
    }

    @VisibleForTesting public static void resetInternalStateForTesting() {
        Class v2;
        Class v0 = DynamiteModule.class;
        __monitor_enter(v0);
        IDynamiteLoader v1 = null;
        try {
            DynamiteModule.zzabs = v1;
            DynamiteModule.zzabt = ((IDynamiteLoaderV2)v1);
            DynamiteModule.zzabu = ((String)v1);
            DynamiteModule.zzabr = ((Boolean)v1);
            v2 = DynamiteLoaderClassLoader.class;
            __monitor_enter(v2);
        }
        catch(Throwable v1_1) {
            goto label_17;
        }

        try {
            DynamiteLoaderClassLoader.sClassLoader = ((ClassLoader)v1);
            __monitor_exit(v2);
        }
        catch(Throwable v1_1) {
            try {
            label_14:
                __monitor_exit(v2);
            }
            catch(Throwable v1_1) {
                goto label_14;
            }

            try {
                throw v1_1;
            }
            catch(Throwable v1_1) {
            label_17:
                __monitor_exit(v0);
                throw v1_1;
            }
        }

        __monitor_exit(v0);
    }

    @VisibleForTesting public static void setUseV2ForTesting(Boolean arg1) {
        Class v0 = DynamiteModule.class;
        __monitor_enter(v0);
        try {
            DynamiteModule.zzabr = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
    }

    @GuardedBy(value="DynamiteModule.class") private static void zza(ClassLoader arg3) {
        try {
            DynamiteModule.zzabt = Stub.asInterface(arg3.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor().newInstance());
            return;
        }
        catch(NoSuchMethodException v3) {
            throw new LoadingException("Failed to instantiate dynamite loader", ((Throwable)v3), null);
        }
    }

    private static int zza(Context arg2, String arg3, boolean arg4) {
        IDynamiteLoader v0 = DynamiteModule.zzg(arg2);
        if(v0 == null) {
            return 0;
        }

        try {
            return v0.getModuleVersion2(ObjectWrapper.wrap(arg2), arg3, arg4);
        }
        catch(RemoteException v2) {
            arg3 = "DynamiteModule";
            String v4 = "Failed to retrieve remote module version: ";
            String v2_1 = String.valueOf(v2.getMessage());
            v2_1 = v2_1.length() != 0 ? v4.concat(v2_1) : new String(v4);
            Log.w(arg3, v2_1);
            return 0;
        }
    }

    private static DynamiteModule zza(Context arg2, String arg3, int arg4) {
        Class v0 = DynamiteModule.class;
        __monitor_enter(v0);
        try {
            Boolean v1 = DynamiteModule.zzabr;
            __monitor_exit(v0);
            if(v1 == null) {
                goto label_11;
            }
        }
        catch(Throwable v2) {
            try {
            label_17:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_17;
            }

            throw v2;
        }

        if(v1.booleanValue()) {
            return DynamiteModule.zzc(arg2, arg3, arg4);
        }

        return DynamiteModule.zzb(arg2, arg3, arg4);
    label_11:
        throw new LoadingException("Failed to determine which loading route to use.", null);
    }

    private static Context zza(Context arg0, String arg1, int arg2, Cursor arg3, IDynamiteLoaderV2 arg4) {
        try {
            return ObjectWrapper.unwrap(arg4.loadModule2(ObjectWrapper.wrap(arg0), arg1, arg2, ObjectWrapper.wrap(arg3)));
        }
        catch(Exception v0) {
            arg1 = "DynamiteModule";
            String v2 = "Failed to load DynamiteLoader: ";
            String v0_1 = String.valueOf(v0.toString());
            v0_1 = v0_1.length() != 0 ? v2.concat(v0_1) : new String(v2);
            Log.e(arg1, v0_1);
            return null;
        }
    }

    private static int zzb(Context arg2, String arg3, boolean arg4) {
        int v1;
        Class v4;
        int v3_2;
        Cursor v2;
        zza v0 = null;
        try {
            v2 = DynamiteModule.queryForDynamiteModule(arg2, arg3, arg4);
            if(v2 == null) {
                goto label_30;
            }
        }
        catch(Throwable v3) {
            v2 = ((Cursor)v0);
            goto label_50;
        }
        catch(Exception v3_1) {
            v2 = ((Cursor)v0);
            goto label_42;
        }

        try {
            if(v2.moveToFirst()) {
                v3_2 = v2.getInt(0);
                if(v3_2 > 0) {
                    v4 = DynamiteModule.class;
                    __monitor_enter(v4);
                    v1 = 2;
                    goto label_11;
                }

                goto label_25;
            }

            goto label_30;
        }
        catch(Throwable v3) {
            goto label_50;
        }
        catch(Exception v3_1) {
            goto label_29;
        }

        try {
        label_11:
            DynamiteModule.zzabu = v2.getString(v1);
            __monitor_exit(v4);
            goto label_14;
        }
        catch(Throwable v3) {
            try {
            label_23:
                __monitor_exit(v4);
            }
            catch(Throwable v3) {
                goto label_23;
            }

            try {
                throw v3;
            label_14:
                Object v4_1 = DynamiteModule.zzabv.get();
                if(v4_1 != null && ((com.google.android.gms.dynamite.DynamiteModule$zza)v4_1).zzaby == null) {
                    ((com.google.android.gms.dynamite.DynamiteModule$zza)v4_1).zzaby = v2;
                    v2 = ((Cursor)v0);
                }
            }
            catch(Throwable v3) {
                goto label_50;
            }
            catch(Exception v3_1) {
                goto label_29;
            }
        }

    label_25:
        if(v2 != null) {
            v2.close();
        }

        return v3_2;
        try {
        label_30:
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.", v0);
        }
        catch(Throwable v3) {
        }
        catch(Exception v3_1) {
        label_29:
            try {
            label_42:
                if((v3_1 instanceof LoadingException)) {
                    throw v3_1;
                }

                throw new LoadingException("V2 version check failed", ((Throwable)v3_1), v0);
            }
            catch(Throwable v3) {
            }
        }

    label_50:
        if(v2 != null) {
            v2.close();
        }

        throw v3;
    }

    private static DynamiteModule zzb(Context arg3, String arg4, int arg5) {
        IObjectWrapper v3_1;
        StringBuilder v2 = new StringBuilder(String.valueOf(arg4).length() + 51);
        v2.append("Selected remote version of ");
        v2.append(arg4);
        v2.append(", version >= ");
        v2.append(arg5);
        Log.i("DynamiteModule", v2.toString());
        IDynamiteLoader v0 = DynamiteModule.zzg(arg3);
        zza v1 = null;
        if(v0 != null) {
            try {
                v3_1 = v0.createModuleContext(ObjectWrapper.wrap(arg3), arg4, arg5);
            }
            catch(RemoteException v3) {
                throw new LoadingException("Failed to load remote module.", ((Throwable)v3), v1);
            }

            if(ObjectWrapper.unwrap(v3_1) != null) {
                return new DynamiteModule(ObjectWrapper.unwrap(v3_1));
            }

            throw new LoadingException("Failed to load remote module.", v1);
        }

        throw new LoadingException("Failed to create IDynamiteLoader.", v1);
    }

    private static DynamiteModule zzc(Context arg4, String arg5, int arg6) {
        zza v0_1;
        IDynamiteLoaderV2 v1;
        StringBuilder v2 = new StringBuilder(String.valueOf(arg5).length() + 51);
        v2.append("Selected remote version of ");
        v2.append(arg5);
        v2.append(", version >= ");
        v2.append(arg6);
        Log.i("DynamiteModule", v2.toString());
        Class v0 = DynamiteModule.class;
        __monitor_enter(v0);
        try {
            v1 = DynamiteModule.zzabt;
            __monitor_exit(v0);
            v0_1 = null;
            if(v1 == null) {
                goto label_40;
            }
        }
        catch(Throwable v4) {
            try {
            label_45:
                __monitor_exit(v0_1);
            }
            catch(Throwable v4) {
                goto label_45;
            }

            throw v4;
        }

        Object v2_1 = DynamiteModule.zzabv.get();
        if(v2_1 != null && ((com.google.android.gms.dynamite.DynamiteModule$zza)v2_1).zzaby != null) {
            arg4 = DynamiteModule.zza(arg4.getApplicationContext(), arg5, arg6, ((com.google.android.gms.dynamite.DynamiteModule$zza)v2_1).zzaby, v1);
            if(arg4 != null) {
                return new DynamiteModule(arg4);
            }
            else {
                throw new LoadingException("Failed to get module context", v0_1);
            }
        }

        throw new LoadingException("No result cursor", v0_1);
    label_40:
        throw new LoadingException("DynamiteLoaderV2 was not cached.", v0_1);
    }

    private static DynamiteModule zzd(Context arg3, String arg4) {
        String v0 = "DynamiteModule";
        String v1 = "Selected local version of ";
        arg4 = String.valueOf(arg4);
        arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
        Log.i(v0, arg4);
        return new DynamiteModule(arg3.getApplicationContext());
    }

    private static IDynamiteLoader zzg(Context arg5) {
        IDynamiteLoader v5_2;
        Class v0 = DynamiteModule.class;
        __monitor_enter(v0);
        try {
            if(DynamiteModule.zzabs != null) {
                __monitor_exit(v0);
                return DynamiteModule.zzabs;
            }

            IDynamiteLoader v2 = null;
            if(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(arg5) != 0) {
                __monitor_exit(v0);
                return v2;
            }

            try {
                v5_2 = com.google.android.gms.dynamite.IDynamiteLoader$Stub.asInterface(arg5.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                if(v5_2 == null) {
                    goto label_37;
                }

                DynamiteModule.zzabs = v5_2;
            }
            catch(Exception v5_1) {
                String v1 = "DynamiteModule";
                String v3 = "Failed to load IDynamiteLoader from GmsCore: ";
                String v5_3 = String.valueOf(v5_1.getMessage());
                v5_3 = v5_3.length() != 0 ? v3.concat(v5_3) : new String(v3);
                Log.e(v1, v5_3);
                goto label_37;
            }

            __monitor_exit(v0);
            return v5_2;
        label_37:
            __monitor_exit(v0);
            return v2;
        label_40:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_40;
        }

        throw v5;
    }
}

