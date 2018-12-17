package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

final class zzh extends PathClassLoader {
    zzh(String arg1, ClassLoader arg2) {
        super(arg1, arg2);
    }

    protected final Class loadClass(String arg2, boolean arg3) {
        if(!arg2.startsWith("java.") && !arg2.startsWith("android.")) {
            try {
                return this.findClass(arg2);
            }
            catch(ClassNotFoundException ) {
            label_8:
                return super.loadClass(arg2, arg3);
            }
        }

        goto label_8;
    }
}

