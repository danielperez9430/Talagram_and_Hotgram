package org.telegram.customization.fetch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.os.Bundle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.customization.fetch.d.a;
import org.telegram.customization.fetch.d.c;

final class f {
    static long a() {
        return System.nanoTime();
    }

    static boolean a(Context arg2) {
        NetworkInfo v2 = arg2.getSystemService("connectivity").getActiveNetworkInfo();
        boolean v0 = false;
        if(v2 != null && (v2.isConnected()) && v2.getType() == 1) {
            v0 = true;
        }

        return v0;
    }

    static c a(Cursor arg3, boolean arg4, boolean arg5) {
        c v1_1;
        c v0 = null;
        if(arg3 != null) {
            try {
                if(!arg3.isClosed()) {
                    if(arg3.getCount() < 1) {
                    }
                    else {
                        arg3.moveToFirst();
                        v1_1 = f.a(arg3, arg5);
                        if(arg3 == null) {
                            return v1_1;
                        }

                        goto label_11;
                    }
                }

                goto label_29;
            }
            catch(Throwable v5) {
            label_21:
                if(arg3 != null && (arg4)) {
                    arg3.close();
                }

                throw v5;
            }
            catch(Exception v1) {
                if(arg5) {
                    try {
                        v1.printStackTrace();
                    }
                    catch(Throwable v5) {
                        goto label_21;
                    }
                }

                if(arg3 == null) {
                    return v0;
                }

                if(!arg4) {
                    return v0;
                }

                arg3.close();
                return v0;
            }

        label_11:
            if(arg4) {
                arg3.close();
            }

            return v1_1;
        }

    label_29:
        if(arg3 != null && (arg4)) {
            arg3.close();
        }

        return v0;
    }

    static void a(android.support.v4.content.c arg2, long arg3, int arg5, int arg6, long arg7, long arg9, int arg11) {
        if(arg2 == null) {
            return;
        }

        Intent v0 = new Intent("com.tonyodev.fetch.event_action_update");
        v0.putExtra("com.tonyodev.fetch.extra_id", arg3);
        v0.putExtra("com.tonyodev.fetch.extra_status", arg5);
        v0.putExtra("com.tonyodev.fetch.extra_progress", arg6);
        v0.putExtra("com.tonyodev.fetch.extra_downloaded_bytes", arg7);
        v0.putExtra("com.tonyodev.fetch.extra_file_size", arg9);
        v0.putExtra("com.tonyodev.fetch.extra_error", arg11);
        arg2.a(v0);
    }

    static void a(org.telegram.customization.fetch.c arg3) {
        if(arg3 != null) {
            if(!arg3.b()) {
                return;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Fetch instance: ");
            v1.append(arg3.toString());
            v1.append(" cannot be reused after calling its release() method.Call Fetch.getInstance() for a new instance of Fetch.");
            throw new org.telegram.customization.fetch.b.c(v1.toString(), -115);
        }

        throw new NullPointerException("Fetch cannot be null");
    }

    static String a(List arg3, boolean arg4) {
        String v3_2;
        if(arg3 == null) {
            return "{}";
        }

        try {
            JSONObject v0 = new JSONObject();
            Iterator v3_1 = arg3.iterator();
            while(v3_1.hasNext()) {
                Object v1 = v3_1.next();
                v0.put(((a)v1).a(), ((a)v1).b());
            }

            v3_2 = v0.toString();
        }
        catch(JSONException v3) {
            if(arg4) {
                v3.printStackTrace();
            }

            v3_2 = "{}";
        }

        return v3_2;
    }

    static int a(long arg3, long arg5) {
        long v0 = 1;
        if(arg5 >= v0) {
            if(arg3 < v0) {
            }
            else if(arg3 >= arg5) {
                return 100;
            }
            else {
                double v3 = ((double)arg3);
                double v5 = ((double)arg5);
                Double.isNaN(v3);
                Double.isNaN(v5);
                return ((int)(v3 / v5 * 100));
            }
        }

        return 0;
    }

    static List a(String arg5, boolean arg6) {
        ArrayList v0 = new ArrayList();
        try {
            JSONObject v1 = new JSONObject(arg5);
            Iterator v5_1 = v1.keys();
            while(v5_1.hasNext()) {
                Object v2 = v5_1.next();
                ((List)v0).add(new a(((String)v2), v1.getString(((String)v2))));
            }
        }
        catch(JSONException v5) {
            if(!arg6) {
                goto label_16;
            }

            v5.printStackTrace();
        }

    label_16:
        return ((List)v0);
    }

    static c a(Cursor arg17, boolean arg18) {
        Cursor v0 = arg17;
        if(v0 != null && !arg17.isClosed()) {
            if(arg17.getCount() < 1) {
            }
            else {
                long v4 = v0.getLong(0);
                int v6 = v0.getInt(3);
                String v7 = v0.getString(1);
                String v8 = v0.getString(2);
                int v14 = v0.getInt(7);
                long v12 = v0.getLong(6);
                int v16 = v0.getInt(8);
                long v10 = v0.getLong(5);
                return new c(v4, v6, v7, v8, f.a(v10, v12), v10, v12, v14, f.a(v0.getString(4), arg18), v16);
            }
        }

        return null;
    }

    static boolean a(long arg1, long arg3, long arg5) {
        if(TimeUnit.NANOSECONDS.toMillis(arg3 - arg1) >= arg5) {
            return 1;
        }

        return 0;
    }

    static boolean a(String arg1) {
        File v0 = new File(arg1);
        boolean v1 = !v0.exists() ? v0.createNewFile() : true;
        return v1;
    }

    static String b(List arg4, boolean arg5) {
        if(arg4 != null) {
            JSONObject v0 = new JSONObject();
            try {
                Iterator v4_1 = arg4.iterator();
                while(v4_1.hasNext()) {
                    Object v1 = v4_1.next();
                    String v2 = ((Bundle)v1).getString("com.tonyodev.fetch.extra_header_name");
                    String v1_1 = ((Bundle)v1).getString("com.tonyodev.fetch.extra_header_value");
                    if(v1_1 == null) {
                        v1_1 = "";
                    }

                    if(v2 == null) {
                        continue;
                    }

                    v0.put(v2, v1_1);
                }

                String v4_2 = v0.toString();
                return v4_2;
            }
            catch(JSONException v4) {
                if(!arg5) {
                    return "{}";
                }

                v4.printStackTrace();
            }
        }

        return "{}";
    }

    static boolean b(Context arg1) {
        NetworkInfo v1 = arg1.getSystemService("connectivity").getActiveNetworkInfo();
        boolean v1_1 = v1 == null || !v1.isConnected() ? false : true;
        return v1_1;
    }

    static List b(Cursor arg3, boolean arg4, boolean arg5) {
        ArrayList v0 = new ArrayList();
        if(arg3 != null) {
            try {
                if(!arg3.isClosed()) {
                    if(arg3.getCount() < 1) {
                    }
                    else {
                        arg3.moveToFirst();
                        while(true) {
                            if(!arg3.isAfterLast()) {
                                ((List)v0).add(f.a(arg3, arg5));
                                arg3.moveToNext();
                                continue;
                            }
                            else {
                                goto label_16;
                            }
                        }
                    }
                }

                goto label_33;
            }
            catch(Throwable v5) {
                goto label_25;
            }
            catch(Exception v1) {
                goto label_22;
            }

        label_16:
            if(arg3 != null) {
                if(arg4) {
                    goto label_31;
                label_22:
                    if(arg5) {
                        try {
                            v1.printStackTrace();
                        }
                        catch(Throwable v5) {
                            goto label_25;
                        }
                    }

                    if(arg3 == null) {
                        goto label_32;
                    }

                    if(!arg4) {
                        goto label_32;
                    }

                label_31:
                    arg3.close();
                }
                else {
                }

                goto label_32;
            }
            else {
            label_32:
                return ((List)v0);
            label_25:
                if(arg3 != null && (arg4)) {
                    arg3.close();
                }

                throw v5;
            }
        }

    label_33:
        if(arg3 != null && (arg4)) {
            arg3.close();
        }

        return ((List)v0);
    }

    static ArrayList b(String arg5, boolean arg6) {
        ArrayList v0 = new ArrayList();
        if(arg5 == null) {
            return v0;
        }

        try {
            JSONObject v1 = new JSONObject(arg5);
            Iterator v5_1 = v1.keys();
            while(v5_1.hasNext()) {
                Object v2 = v5_1.next();
                Bundle v3 = new Bundle();
                v3.putString("com.tonyodev.fetch.extra_header_name", ((String)v2));
                v3.putString("com.tonyodev.fetch.extra_header_value", v1.getString(((String)v2)));
                v0.add(v3);
            }
        }
        catch(JSONException v5) {
            if(!arg6) {
                return v0;
            }

            v5.printStackTrace();
        }

        return v0;
    }

    static boolean b(String arg1) {
        File v0 = new File(arg1);
        boolean v1 = !v0.exists() ? v0.mkdirs() : true;
        return v1;
    }

    static ArrayList c(Cursor arg16, boolean arg17, boolean arg18) {
        Cursor v1 = arg16;
        boolean v3 = arg18;
        ArrayList v4 = new ArrayList();
        if(v1 != null) {
            try {
                if(arg16.isClosed()) {
                }
                else {
                    arg16.moveToFirst();
                    while(true) {
                    label_9:
                        if(!arg16.isAfterLast()) {
                            goto label_11;
                        }
                        else {
                            goto label_64;
                        }
                    }
                }

                goto label_82;
            }
            catch(Throwable v0) {
                goto label_74;
            }
            catch(Exception v0_1) {
                goto label_70;
            }

        label_11:
            try {
                long v5 = v1.getLong(0);
                int v0_2 = v1.getInt(3);
                String v7 = v1.getString(1);
                String v8 = v1.getString(2);
                int v9 = v1.getInt(7);
                long v10 = v1.getLong(6);
                int v12 = v1.getInt(8);
                long v13 = v1.getLong(5);
                ArrayList v15 = f.b(v1.getString(4), v3);
                int v3_1 = f.a(v13, v10);
                Bundle v2 = new Bundle();
                v2.putLong("com.tonyodev.fetch.extra_id", v5);
                v2.putInt("com.tonyodev.fetch.extra_status", v0_2);
                v2.putString("com.tonyodev.fetch.extra_url", v7);
                v2.putString("com.tonyodev.fetch.extra_file_path", v8);
                v2.putInt("com.tonyodev.fetch.extra_error", v9);
                v2.putLong("com.tonyodev.fetch.extra_downloaded_bytes", v13);
                v2.putLong("com.tonyodev.fetch.extra_file_size", v10);
                v2.putInt("com.tonyodev.fetch.extra_progress", v3_1);
                v2.putInt("com.tonyodev.fetch.extra_priority", v12);
                v2.putParcelableArrayList("com.tonyodev.fetch.extra_headers", v15);
                v4.add(v2);
                arg16.moveToNext();
                v1 = arg16;
                v3 = arg18;
                goto label_9;
            }
            catch(Throwable v0) {
                v1 = arg16;
                goto label_74;
            }
            catch(Exception v0_1) {
                v1 = arg16;
                goto label_70;
            }

        label_64:
            if(v1 != null) {
                if(arg17) {
                    goto label_80;
                label_70:
                    if(arg18) {
                        try {
                            v0_1.printStackTrace();
                        }
                        catch(Throwable v0) {
                            goto label_74;
                        }
                    }

                    if(v1 == null) {
                        return v4;
                    }

                    if(!arg17) {
                        return v4;
                    }

                label_80:
                    arg16.close();
                }
                else {
                }

                return v4;
            }
            else {
                return v4;
            label_74:
                if(v1 != null && (arg17)) {
                    arg16.close();
                }

                throw v0;
            }
        }

    label_82:
        if(v1 != null && (arg17)) {
            arg16.close();
        }

        return v4;
    }

    static boolean c(String arg1) {
        return new File(arg1).delete();
    }

    static long d(String arg2) {
        return new File(arg2).length();
    }

    static boolean e(String arg1) {
        return new File(arg1).exists();
    }

    static File f(String arg1) {
        return new File(arg1);
    }

    static void g(String arg3) {
        File v0 = f.f(arg3);
        boolean v1 = f.b(v0.getParentFile().getAbsolutePath());
        boolean v0_1 = f.a(v0.getAbsolutePath());
        if((v1) && (v0_1)) {
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("File could not be created for the filePath:");
        v1_1.append(arg3);
        throw new IOException(v1_1.toString());
    }
}

