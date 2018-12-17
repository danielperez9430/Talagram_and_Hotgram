package c.a.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class a {
    class c.a.a.a.a$a {
        private final Set a;
        private final Application b;

        c.a.a.a.a$a(Application arg2) {
            super();
            this.a = new HashSet();
            this.b = arg2;
        }

        static void a(c.a.a.a.a$a arg0) {
            arg0.a();
        }

        static boolean a(c.a.a.a.a$a arg0, b arg1) {
            return arg0.a(arg1);
        }

        @TargetApi(value=14) private void a() {
            Iterator v0 = this.a.iterator();
            while(v0.hasNext()) {
                this.b.unregisterActivityLifecycleCallbacks(v0.next());
            }
        }

        @TargetApi(value=14) private boolean a(b arg2) {
            if(this.b != null) {
                c.a.a.a.a$a$1 v0 = new Application$ActivityLifecycleCallbacks(arg2) {
                    public void onActivityCreated(Activity arg2, Bundle arg3) {
                        this.a.a(arg2, arg3);
                    }

                    public void onActivityDestroyed(Activity arg2) {
                        this.a.e(arg2);
                    }

                    public void onActivityPaused(Activity arg2) {
                        this.a.c(arg2);
                    }

                    public void onActivityResumed(Activity arg2) {
                        this.a.b(arg2);
                    }

                    public void onActivitySaveInstanceState(Activity arg2, Bundle arg3) {
                        this.a.b(arg2, arg3);
                    }

                    public void onActivityStarted(Activity arg2) {
                        this.a.a(arg2);
                    }

                    public void onActivityStopped(Activity arg2) {
                        this.a.d(arg2);
                    }
                };
                this.b.registerActivityLifecycleCallbacks(((Application$ActivityLifecycleCallbacks)v0));
                this.a.add(v0);
                return 1;
            }

            return 0;
        }
    }

    public abstract class b {
        public b() {
            super();
        }

        public void a(Activity arg1, Bundle arg2) {
        }

        public void a(Activity arg1) {
        }

        public void b(Activity arg1) {
        }

        public void b(Activity arg1, Bundle arg2) {
        }

        public void c(Activity arg1) {
        }

        public void d(Activity arg1) {
        }

        public void e(Activity arg1) {
        }
    }

    private final Application a;
    private c.a.a.a.a$a b;

    public a(Context arg2) {
        super();
        this.a = arg2.getApplicationContext();
        if(Build$VERSION.SDK_INT >= 14) {
            this.b = new c.a.a.a.a$a(this.a);
        }
    }

    public void a() {
        if(this.b != null) {
            c.a.a.a.a$a.a(this.b);
        }
    }

    public boolean a(b arg2) {
        boolean v2 = this.b == null || !c.a.a.a.a$a.a(this.b, arg2) ? false : true;
        return v2;
    }
}

