package com.google.a.b;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class k {
    public k() {
        super();
    }

    public static k a() {
        Object v0 = null;
        try {
            Class v3 = Class.forName("sun.misc.Unsafe");
            Field v4 = v3.getDeclaredField("theUnsafe");
            v4.setAccessible(true);
            return new k(v3.getMethod("allocateInstance", Class.class), v4.get(v0)) {
                public Object a(Class arg5) {
                    com.google.a.b.k$1.b(arg5);
                    return this.a.invoke(this.b, arg5);
                }
            };
        }
        catch(Exception ) {
            int v3_1 = 2;
            try {
                Method v4_1 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                v4_1.setAccessible(true);
                int v0_1 = v4_1.invoke(v0, Object.class).intValue();
                Class v4_2 = ObjectStreamClass.class;
                v4_1 = v4_2.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                v4_1.setAccessible(true);
                return new k(v4_1, v0_1) {
                    public Object a(Class arg4) {
                        com.google.a.b.k$2.b(arg4);
                        return this.a.invoke(null, arg4, Integer.valueOf(this.b));
                    }
                };
            }
            catch(Exception ) {
                try {
                    Class v0_2 = ObjectInputStream.class;
                    Method v0_3 = v0_2.getDeclaredMethod("newInstance", Class.class, Class.class);
                    v0_3.setAccessible(true);
                    return new k(v0_3) {
                        public Object a(Class arg4) {
                            com.google.a.b.k$3.b(arg4);
                            return this.a.invoke(null, arg4, Object.class);
                        }
                    };
                }
                catch(Exception ) {
                    return new k() {
                        public Object a(Class arg4) {
                            StringBuilder v1 = new StringBuilder();
                            v1.append("Cannot allocate ");
                            v1.append(arg4);
                            throw new UnsupportedOperationException(v1.toString());
                        }
                    };
                }
            }
        }
    }

    public abstract Object a(Class arg1);

    static void b(Class arg3) {
        StringBuilder v1;
        int v0 = arg3.getModifiers();
        if(!Modifier.isInterface(v0)) {
            if(!Modifier.isAbstract(v0)) {
                return;
            }

            v1 = new StringBuilder();
            v1.append("Abstract class can\'t be instantiated! Class name: ");
            v1.append(arg3.getName());
            throw new UnsupportedOperationException(v1.toString());
        }

        v1 = new StringBuilder();
        v1.append("Interface can\'t be instantiated! Interface name: ");
        v1.append(arg3.getName());
        throw new UnsupportedOperationException(v1.toString());
    }
}

