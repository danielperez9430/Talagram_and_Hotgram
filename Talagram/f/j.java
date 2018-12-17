package f;

import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles$Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

class j {
    class a extends j {
        class f.j$a$a implements Executor {
            private final Handler a;

            f.j$a$a() {
                super();
                this.a = new Handler(Looper.getMainLooper());
            }

            public void execute(Runnable arg2) {
                this.a.post(arg2);
            }
        }

        a() {
            super();
        }

        f.c$a a(@Nullable Executor arg2) {
            if(arg2 != null) {
                return new g(arg2);
            }

            throw new AssertionError();
        }

        public Executor b() {
            return new f.j$a$a();
        }
    }

    @IgnoreJRERequirement class b extends j {
        b() {
            super();
        }

        Object a(Method arg7, Class arg8, Object arg9, @Nullable Object[] arg10) {
            Constructor v0 = MethodHandles$Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            v0.setAccessible(true);
            return v0.newInstance(arg8, Integer.valueOf(-1)).unreflectSpecial(arg7, arg8).bindTo(arg9).invokeWithArguments(arg10);
        }

        boolean a(Method arg1) {
            return arg1.isDefault();
        }
    }

    private static final j a;

    static {
        j.a = j.c();
    }

    j() {
        super();
    }

    static j a() {
        return j.a;
    }

    f.c$a a(@Nullable Executor arg2) {
        if(arg2 != null) {
            return new g(arg2);
        }

        return f.a;
    }

    @Nullable Object a(Method arg1, Class arg2, Object arg3, @Nullable Object[] arg4) {
        throw new UnsupportedOperationException();
    }

    boolean a(Method arg1) {
        return 0;
    }

    @Nullable Executor b() {
        return null;
    }

    private static j c() {
        try {
            Class.forName("android.os.Build");
            if(Build$VERSION.SDK_INT == 0) {
                goto label_7;
            }

            return new a();
        }
        catch(ClassNotFoundException ) {
            try {
            label_7:
                Class.forName("java.util.Optional");
                return new b();
            }
            catch(ClassNotFoundException ) {
                return new j();
            }
        }
    }
}

