package okhttp3.internal.g;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class e {
    private final Class a;
    private final String b;
    private final Class[] c;

    e(Class arg1, String arg2, Class[] arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public boolean a(Object arg1) {
        boolean v1 = this.a(arg1.getClass()) != null ? true : false;
        return v1;
    }

    private Method a(Class arg4) {
        Method v1 = null;
        if(this.b != null) {
            Method v4 = e.a(arg4, this.b, this.c);
            if(v4 != null && this.a != null && !this.a.isAssignableFrom(v4.getReturnType())) {
                return v1;
            }

            v1 = v4;
        }

        return v1;
    }

    private static Method a(Class arg1, String arg2, Class[] arg3) {
        Method v1;
        Method v0 = null;
        try {
            v1 = arg1.getMethod(arg2, arg3);
        }
        catch(NoSuchMethodException ) {
            return v0;
        }

        try {
            if((v1.getModifiers() & 1) == 0) {
                return v0;
            }
        }
        catch(NoSuchMethodException ) {
        }

        return v1;
    }

    public Object a(Object arg3, Object[] arg4) {
        Method v0 = this.a(arg3.getClass());
        Object v1 = null;
        if(v0 == null) {
            return v1;
        }

        try {
            return v0.invoke(arg3, arg4);
        }
        catch(IllegalAccessException ) {
            return v1;
        }
    }

    public Object b(Object arg2, Object[] arg3) {
        try {
            return this.a(arg2, arg3);
        }
        catch(InvocationTargetException v2) {
            Throwable v2_1 = v2.getTargetException();
            if((v2_1 instanceof RuntimeException)) {
                throw v2_1;
            }

            AssertionError v3 = new AssertionError("Unexpected exception");
            v3.initCause(v2_1);
            throw v3;
        }
    }

    public Object c(Object arg4, Object[] arg5) {
        Method v0 = this.a(arg4.getClass());
        if(v0 != null) {
            try {
                return v0.invoke(arg4, arg5);
            }
            catch(IllegalAccessException v4) {
                StringBuilder v1 = new StringBuilder();
                v1.append("Unexpectedly could not call: ");
                v1.append(v0);
                AssertionError v5 = new AssertionError(v1.toString());
                v5.initCause(((Throwable)v4));
                throw v5;
            }
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("Method ");
        v0_1.append(this.b);
        v0_1.append(" not supported for object ");
        v0_1.append(arg4);
        throw new AssertionError(v0_1.toString());
    }

    public Object d(Object arg2, Object[] arg3) {
        try {
            return this.c(arg2, arg3);
        }
        catch(InvocationTargetException v2) {
            Throwable v2_1 = v2.getTargetException();
            if((v2_1 instanceof RuntimeException)) {
                throw v2_1;
            }

            AssertionError v3 = new AssertionError("Unexpected exception");
            v3.initCause(v2_1);
            throw v3;
        }
    }
}

