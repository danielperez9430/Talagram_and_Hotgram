package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;

public abstract class a {
    public a() {
        super();
    }

    public void a(boolean arg1, boolean arg2) {
    }

    public boolean a() {
        return 0;
    }

    public void a(int arg1, int arg2) {
        this.c(arg2);
        this.a(arg1);
    }

    public void a(byte[] arg1, int arg2) {
        this.c(arg2);
        this.a(arg1);
    }

    public void a(Parcelable arg1, int arg2) {
        this.c(arg2);
        this.a(arg1);
    }

    public void a(String arg1, int arg2) {
        this.c(arg2);
        this.a(arg1);
    }

    public void a(c arg1, int arg2) {
        this.c(arg2);
        this.a(arg1);
    }

    protected static c a(String arg5, a arg6) {
        try {
            return Class.forName(arg5, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class).invoke(null, arg6);
        }
        catch(ClassNotFoundException v5) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", ((Throwable)v5));
        }
        catch(NoSuchMethodException v5_1) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", ((Throwable)v5_1));
        }
        catch(InvocationTargetException v5_2) {
            if((v5_2.getCause() instanceof RuntimeException)) {
                throw v5_2.getCause();
            }

            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", ((Throwable)v5_2));
        }
        catch(IllegalAccessException v5_3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", ((Throwable)v5_3));
        }
    }

    private static Class a(Class arg5) {
        return Class.forName(String.format("%s.%sParcelizer", arg5.getPackage().getName(), arg5.getSimpleName()), false, arg5.getClassLoader());
    }

    protected static void a(c arg7, a arg8) {
        try {
            a.c(arg7).getDeclaredMethod("write", arg7.getClass(), a.class).invoke(null, arg7, arg8);
            return;
        }
        catch(ClassNotFoundException v7) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", ((Throwable)v7));
        }
        catch(NoSuchMethodException v7_1) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", ((Throwable)v7_1));
        }
        catch(InvocationTargetException v7_2) {
            if((v7_2.getCause() instanceof RuntimeException)) {
                throw v7_2.getCause();
            }

            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", ((Throwable)v7_2));
        }
        catch(IllegalAccessException v7_3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", ((Throwable)v7_3));
        }
    }

    protected abstract void a(String arg1);

    protected abstract void a(int arg1);

    protected abstract void a(Parcelable arg1);

    protected void a(c arg2) {
        if(arg2 == null) {
            this.a(null);
            return;
        }

        this.b(arg2);
        a v0 = this.c();
        a.a(arg2, v0);
        v0.b();
    }

    protected abstract void a(byte[] arg1);

    public int b(int arg1, int arg2) {
        if(!this.b(arg2)) {
            return arg1;
        }

        return this.d();
    }

    public byte[] b(byte[] arg1, int arg2) {
        if(!this.b(arg2)) {
            return arg1;
        }

        return this.f();
    }

    public Parcelable b(Parcelable arg1, int arg2) {
        if(!this.b(arg2)) {
            return arg1;
        }

        return this.g();
    }

    public String b(String arg1, int arg2) {
        if(!this.b(arg2)) {
            return arg1;
        }

        return this.e();
    }

    public c b(c arg1, int arg2) {
        if(!this.b(arg2)) {
            return arg1;
        }

        return this.h();
    }

    private void b(c arg4) {
        Class v0_1;
        try {
            v0_1 = a.a(arg4.getClass());
        }
        catch(ClassNotFoundException v0) {
            StringBuilder v2 = new StringBuilder();
            v2.append(arg4.getClass().getSimpleName());
            v2.append(" does not have a Parcelizer");
            throw new RuntimeException(v2.toString(), ((Throwable)v0));
        }

        this.a(v0_1.getName());
    }

    protected abstract void b();

    protected abstract boolean b(int arg1);

    private static Class c(c arg0) {
        return a.a(arg0.getClass());
    }

    protected abstract void c(int arg1);

    protected abstract a c();

    protected abstract int d();

    protected abstract String e();

    protected abstract byte[] f();

    protected abstract Parcelable g();

    protected c h() {
        String v0 = this.e();
        if(v0 == null) {
            return null;
        }

        return a.a(v0, this.c());
    }
}

