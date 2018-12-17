package org.telegram.customization.c;

import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.customization.dynamicadapter.data.SlsBaseMessage;
import org.telegram.customization.dynamicadapter.data.SlsTag;

public class b {
    SlsTag a;
    private List b;
    private boolean c;
    private String d;
    private int e;
    private long f;
    private long g;
    private boolean h;

    public b() {
        super();
        this.c = false;
        this.h = false;
    }

    public SlsBaseMessage a(int arg2) {
        if(arg2 >= this.e()) {
            return null;
        }

        return this.b().get(arg2);
    }

    public SlsTag a() {
        if(this.a == null) {
            this.a = new SlsTag();
            this.a.setId(0);
            this.a.setTitle("");
        }

        return this.a;
    }

    public void a(long arg1) {
        this.f = arg1;
    }

    public void a(Context arg1, SlsBaseMessage arg2, a arg3, boolean arg4) {
        __monitor_enter(this);
        try {
            if(this.a(arg1, arg2)) {
                goto label_3;
            }

            goto label_14;
        }
        catch(Throwable v1) {
            goto label_17;
        }

    label_3:
        if(arg3 != null) {
            if(arg1 != null) {
                try {
                    if((arg1 instanceof Activity)) {
                        ((Activity)arg1).runOnUiThread(new Runnable(arg2, arg3) {
                            public void run() {
                                this.c.b().add(this.a);
                                this.b.a();
                            }
                        });
                        goto label_14;
                    }

                label_11:
                    this.b().add(arg2);
                    arg3.a();
                    goto label_14;
                }
                catch(Exception ) {
                label_14:
                    __monitor_exit(this);
                    return;
                }
                catch(Throwable v1) {
                label_17:
                    __monitor_exit(this);
                    throw v1;
                }
            }

            goto label_11;
        }

        goto label_14;
    }

    public boolean a(Context arg5, SlsBaseMessage arg6) {
        ArrayList v5 = new ArrayList(this.b());
        if(((List)v5).size() > 0) {
            Iterator v5_1 = ((List)v5).iterator();
            do {
                if(v5_1.hasNext()) {
                    Object v0 = v5_1.next();
                    try {
                        if(((SlsBaseMessage)v0).getRow() != arg6.getRow()) {
                            continue;
                        }

                        break;
                    }
                    catch(Exception v5_2) {
                        v5_2.printStackTrace();
                        return 0;
                    }
                }
                else {
                    goto label_19;
                }

                goto label_20;
            }
            while(true);

            int v5_3 = 1;
            goto label_20;
        label_19:
            v5_3 = 0;
        label_20:
            if(v5_3 == 0) {
                return 1;
            }

            return 0;
        }

        return 1;
    }

    public void a(String arg2) {
        this.a().setColor(arg2);
    }

    public void a(a arg2) {
        this.b().clear();
        this.a(false);
        this.a("D81B60");
        if(arg2 != null) {
            arg2.a();
        }
    }

    public void a(boolean arg1) {
        this.c = arg1;
    }

    public List b() {
        if(this.b == null) {
            this.b = new ArrayList();
        }

        return this.b;
    }

    public void b(int arg4) {
        this.a().setId(((long)arg4));
    }

    public void b(long arg1) {
        this.g = arg1;
    }

    public void b(String arg1) {
        this.d = arg1;
    }

    public void b(boolean arg1) {
        this.h = arg1;
    }

    public long c() {
        if(this.d() == null) {
            return 0;
        }

        return ((long)this.d().getRow());
    }

    public void c(int arg1) {
        this.e = arg1;
    }

    public SlsBaseMessage d() {
        if(this.e() == 0) {
            return null;
        }

        return this.a(this.e() - 1);
    }

    public int e() {
        return this.b().size();
    }

    public boolean f() {
        return this.c;
    }

    public String g() {
        return this.d;
    }

    public int h() {
        return this.e;
    }

    public long i() {
        return this.f;
    }

    public long j() {
        return this.g;
    }

    public boolean k() {
        return this.h;
    }
}

