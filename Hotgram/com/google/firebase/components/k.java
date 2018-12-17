package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class k {
    final class a implements b {
        a(byte arg1) {
            this();
        }

        private a() {
            super();
        }

        private static Bundle a(Context arg4) {
            Bundle v0 = null;
            try {
                PackageManager v1 = arg4.getPackageManager();
                if(v1 == null) {
                    Log.w("ComponentDiscovery", "Context has no PackageManager.");
                    return v0;
                }

                ServiceInfo v4 = v1.getServiceInfo(new ComponentName(arg4, ComponentDiscoveryService.class), 128);
                if(v4 == null) {
                    Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
                    return v0;
                }

                return v4.metaData;
            }
            catch(PackageManager$NameNotFoundException ) {
                Log.w("ComponentDiscovery", "Application info not found.");
                return v0;
            }
        }

        public final List a(Object arg6) {
            Bundle v6 = a.a(((Context)arg6));
            if(v6 == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }

            ArrayList v0 = new ArrayList();
            Iterator v1 = v6.keySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                if(!"com.google.firebase.components.ComponentRegistrar".equals(v6.get(((String)v2)))) {
                    continue;
                }

                if(!((String)v2).startsWith("com.google.firebase.components:")) {
                    continue;
                }

                ((List)v0).add(((String)v2).substring(31));
            }

            return ((List)v0);
        }
    }

    interface b {
        List a(Object arg1);
    }

    private final Object a;
    private final b b;

    private k(Object arg1, b arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public final List a() {
        return k.a(this.b.a(this.a));
    }

    public static k a(Context arg3) {
        return new k(arg3, new a(0));
    }

    private static List a(List arg8) {
        Object[] v3;
        String v6;
        String v5;
        ArrayList v0 = new ArrayList();
        Iterator v8 = arg8.iterator();
        while(v8.hasNext()) {
            Object v1 = v8.next();
            try {
                Class v4_3 = Class.forName(((String)v1));
                if(!e.class.isAssignableFrom(v4_3)) {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", v1, "com.google.firebase.components.ComponentRegistrar"));
                    continue;
                }

                ((List)v0).add(v4_3.newInstance());
                continue;
            }
            catch(InstantiationException v4) {
                v5 = "ComponentDiscovery";
                v6 = "Could not instantiate %s.";
                v3 = new Object[]{v1};
            }
            catch(IllegalAccessException v4_1) {
                v5 = "ComponentDiscovery";
                v6 = "Could not instantiate %s.";
                v3 = new Object[]{v1};
            }
            catch(ClassNotFoundException v4_2) {
                v5 = "ComponentDiscovery";
                v6 = "Class %s is not an found.";
                v3 = new Object[]{v1};
            }

            Log.w(v5, String.format(v6, v3), ((Throwable)v4));
        }

        return ((List)v0);
    }
}

