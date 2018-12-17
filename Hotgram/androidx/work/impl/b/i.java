package androidx.work.impl.b;

import android.arch.b.a.f;
import android.arch.b.b.b;
import android.arch.b.b.e;

public class i implements h {
    private final e a;
    private final b b;

    public i(e arg2) {
        super();
        this.a = arg2;
        this.b = new b(arg2) {
            public String a() {
                return "INSERT OR IGNORE INTO `WorkName`(`name`,`work_spec_id`) VALUES (?,?)";
            }

            public void a(f arg3, g arg4) {
                if(arg4.a == null) {
                    arg3.a(1);
                }
                else {
                    arg3.a(1, arg4.a);
                }

                int v1 = 2;
                if(arg4.b == null) {
                    arg3.a(v1);
                }
                else {
                    arg3.a(v1, arg4.b);
                }
            }

            public void a(f arg1, Object arg2) {
                this.a(arg1, ((g)arg2));
            }
        };
    }

    public void a(g arg2) {
        this.a.f();
        try {
            this.b.a(arg2);
            this.a.h();
        }
        catch(Throwable v2) {
            this.a.g();
            throw v2;
        }

        this.a.g();
    }
}

