package com.google.firebase.iid;

import android.support.v4.f.a;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

final class ac {
    private int a;
    private final Map b;
    private final y c;

    ac(y arg2) {
        super();
        this.a = 0;
        this.b = new a();
        this.c = arg2;
    }

    final boolean a() {
        __monitor_enter(this);
        try {
            if(this.b() == null) {
                goto label_6;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        boolean v0_1 = true;
        goto label_4;
    label_6:
        v0_1 = false;
    label_4:
        __monitor_exit(this);
        return v0_1;
    }

    final Task a(String arg6) {
        Task v6_2;
        String v1;
        y v0;
        __monitor_enter(this);
        try {
            v0 = this.c;
            __monitor_enter(v0);
        }
        catch(Throwable v6) {
            goto label_43;
        }

        try {
            v1 = this.c.a();
            y v2 = this.c;
            StringBuilder v4 = new StringBuilder(String.valueOf(v1).length() + 1 + String.valueOf(arg6).length());
            v4.append(v1);
            v4.append(",");
            v4.append(arg6);
            v2.a(v4.toString());
            __monitor_exit(v0);
            goto label_21;
        }
        catch(Throwable v6) {
            try {
            label_40:
                __monitor_exit(v0);
            }
            catch(Throwable v6) {
                goto label_40;
            }

            try {
                throw v6;
            label_21:
                TaskCompletionSource v6_1 = new TaskCompletionSource();
                Map v0_1 = this.b;
                int v1_1 = TextUtils.isEmpty(((CharSequence)v1)) ? 0 : v1.split(",").length - 1;
                v0_1.put(Integer.valueOf(this.a + v1_1), v6_1);
                v6_2 = v6_1.getTask();
            }
            catch(Throwable v6) {
            label_43:
                __monitor_exit(this);
                throw v6;
            }
        }

        __monitor_exit(this);
        return v6_2;
    }

    final boolean a(FirebaseInstanceId arg5) {
        Object v2;
        String v0;
        while(true) {
            __monitor_enter(this);
            try {
                v0 = this.b();
                if(v0 == null) {
                    Log.d("FirebaseInstanceId", "topic sync succeeded");
                    __monitor_exit(this);
                    return 1;
                }

                __monitor_exit(this);
            }
            catch(Throwable v5) {
                goto label_32;
            }

            if(!ac.a(arg5, v0)) {
                return 0;
            }

            __monitor_enter(this);
            try {
                v2 = this.b.remove(Integer.valueOf(this.a));
                this.b(v0);
                ++this.a;
                __monitor_exit(this);
                if(v2 == null) {
                    continue;
                }
            }
            catch(Throwable v5) {
                break;
            }

            ((TaskCompletionSource)v2).setResult(null);
        }

        try {
        label_29:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_29;
        }

        throw v5;
        try {
        label_32:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_32;
        }

        throw v5;
    }

    private static boolean a(FirebaseInstanceId arg6, String arg7) {
        String v6_1;
        String[] v7 = arg7.split("!");
        if(v7.length == 2) {
            String v2 = v7[0];
            arg7 = v7[1];
            int v3 = -1;
            try {
                int v4 = v2.hashCode();
                if(v4 != 83) {
                    if(v4 != 85) {
                    }
                    else if(v2.equals("U")) {
                        v3 = 1;
                    }
                }
                else if(v2.equals("S")) {
                    v3 = 0;
                }

                switch(v3) {
                    case 0: {
                        goto label_34;
                    }
                    case 1: {
                        goto label_27;
                    }
                }

                return 1;
            label_34:
                arg6.b(arg7);
                if(!FirebaseInstanceId.g()) {
                    return 1;
                }

                v6_1 = "FirebaseInstanceId";
                arg7 = "subscribe operation succeeded";
                goto label_32;
            label_27:
                arg6.c(arg7);
                if(!FirebaseInstanceId.g()) {
                    return 1;
                }

                v6_1 = "FirebaseInstanceId";
                arg7 = "unsubscribe operation succeeded";
            label_32:
                Log.d(v6_1, arg7);
            }
            catch(IOException v6) {
                arg7 = "FirebaseInstanceId";
                String v1 = "Topic sync failed: ";
                v6_1 = String.valueOf(v6.getMessage());
                v6_1 = v6_1.length() != 0 ? v1.concat(v6_1) : new String(v1);
                Log.e(arg7, v6_1);
                return 0;
            }
        }

        return 1;
    }

    private final String b() {
        String v1_1;
        y v0 = this.c;
        __monitor_enter(v0);
        try {
            v1_1 = this.c.a();
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_20:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_20;
            }

            throw v1;
        }

        if(!TextUtils.isEmpty(((CharSequence)v1_1))) {
            String[] v0_1 = v1_1.split(",");
            if(v0_1.length > 1 && !TextUtils.isEmpty(v0_1[1])) {
                return v0_1[1];
            }
        }

        return null;
    }

    private final boolean b(String arg6) {
        y v0;
        __monitor_enter(this);
        try {
            v0 = this.c;
            __monitor_enter(v0);
        }
        catch(Throwable v6) {
            goto label_42;
        }

        try {
            String v1 = this.c.a();
            String v2 = String.valueOf(",");
            String v3 = String.valueOf(arg6);
            v2 = v3.length() != 0 ? v2.concat(v3) : new String(v2);
            if(!v1.startsWith(v2)) {
                goto label_35;
            }

            v2 = String.valueOf(",");
            arg6 = String.valueOf(arg6);
            arg6 = arg6.length() != 0 ? v2.concat(arg6) : new String(v2);
            this.c.a(v1.substring(arg6.length()));
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_39;
        }

        __monitor_exit(this);
        return 1;
        try {
        label_35:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            try {
            label_39:
                __monitor_exit(v0);
            }
            catch(Throwable v6) {
                goto label_39;
            }

            try {
                throw v6;
            }
            catch(Throwable v6) {
            label_42:
                __monitor_exit(this);
                throw v6;
            }
        }

        __monitor_exit(this);
        return 0;
    }
}

