package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class c {
    final class a {
        final Intent a;
        final ArrayList b;

        a(Intent arg1, ArrayList arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    final class b {
        final IntentFilter a;
        final BroadcastReceiver b;
        boolean c;
        boolean d;

        b(IntentFilter arg1, BroadcastReceiver arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder(128);
            v0.append("Receiver{");
            v0.append(this.b);
            v0.append(" filter=");
            v0.append(this.a);
            if(this.d) {
                v0.append(" DEAD");
            }

            v0.append("}");
            return v0.toString();
        }
    }

    private final Context a;
    private final HashMap b;
    private final HashMap c;
    private final ArrayList d;
    private final Handler e;
    private static final Object f;
    private static c g;

    static {
        c.f = new Object();
    }

    private c(Context arg2) {
        super();
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new ArrayList();
        this.a = arg2;
        this.e = new Handler(arg2.getMainLooper()) {
            public void handleMessage(Message arg3) {
                if(arg3.what != 1) {
                    super.handleMessage(arg3);
                }
                else {
                    this.a.a();
                }
            }
        };
    }

    void a() {
        int v2;
        a[] v1_2;
    label_0:
        HashMap v0 = this.b;
        __monitor_enter(v0);
        try {
            int v1_1 = this.d.size();
            if(v1_1 <= 0) {
                __monitor_exit(v0);
                return;
            }

            v1_2 = new a[v1_1];
            this.d.toArray(((Object[])v1_2));
            this.d.clear();
            __monitor_exit(v0);
            v0 = null;
            v2 = 0;
        }
        catch(Throwable v1) {
            try {
            label_35:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_35;
            }

            throw v1;
        }

        while(true) {
            if(v2 >= v1_2.length) {
                goto label_0;
            }

            a v3 = v1_2[v2];
            int v4 = v3.b.size();
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                Object v6 = v3.b.get(v5);
                if(!((b)v6).d) {
                    ((b)v6).b.onReceive(this.a, v3.a);
                }
            }

            ++v2;
        }
    }

    public static c a(Context arg2) {
        Object v0 = c.f;
        __monitor_enter(v0);
        try {
            if(c.g == null) {
                c.g = new c(arg2.getApplicationContext());
            }

            __monitor_exit(v0);
            return c.g;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }

    public void a(BroadcastReceiver arg12) {
        HashMap v0 = this.b;
        __monitor_enter(v0);
        try {
            Object v1 = this.b.remove(arg12);
            if(v1 == null) {
                __monitor_exit(v0);
                return;
            }

            int v2;
            for(v2 = ((ArrayList)v1).size() - 1; v2 >= 0; --v2) {
                Object v4 = ((ArrayList)v1).get(v2);
                ((b)v4).d = true;
                int v5;
                for(v5 = 0; v5 < ((b)v4).a.countActions(); ++v5) {
                    String v6 = ((b)v4).a.getAction(v5);
                    Object v7 = this.c.get(v6);
                    if(v7 != null) {
                        int v8;
                        for(v8 = ((ArrayList)v7).size() - 1; v8 >= 0; --v8) {
                            Object v9 = ((ArrayList)v7).get(v8);
                            if(((b)v9).b == arg12) {
                                ((b)v9).d = true;
                                ((ArrayList)v7).remove(v8);
                            }
                        }

                        if(((ArrayList)v7).size() > 0) {
                            goto label_36;
                        }

                        this.c.remove(v6);
                    }

                label_36:
                }
            }

            __monitor_exit(v0);
            return;
        label_43:
            __monitor_exit(v0);
        }
        catch(Throwable v12) {
            goto label_43;
        }

        throw v12;
    }

    public void a(BroadcastReceiver arg7, IntentFilter arg8) {
        ArrayList v4_1;
        HashMap v0 = this.b;
        __monitor_enter(v0);
        try {
            b v1 = new b(arg8, arg7);
            Object v2 = this.b.get(arg7);
            if(v2 == null) {
                ArrayList v2_1 = new ArrayList(1);
                this.b.put(arg7, v2_1);
            }

            ((ArrayList)v2).add(v1);
            int v7_1;
            for(v7_1 = 0; v7_1 < arg8.countActions(); ++v7_1) {
                String v2_2 = arg8.getAction(v7_1);
                Object v4 = this.c.get(v2_2);
                if(v4 == null) {
                    v4_1 = new ArrayList(1);
                    this.c.put(v2_2, v4_1);
                }

                v4_1.add(v1);
            }

            __monitor_exit(v0);
            return;
        label_30:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_30;
        }

        throw v7;
    }

    public boolean a(Intent arg22) {
        String v3_1;
        int v3;
        ArrayList v10_1;
        String v20;
        String v17;
        Object v19;
        int v18;
        c v1 = this;
        Intent v0 = arg22;
        HashMap v2 = v1.b;
        __monitor_enter(v2);
        try {
            String v10 = arg22.getAction();
            String v11 = v0.resolveTypeIfNeeded(v1.a.getContentResolver());
            Uri v12 = arg22.getData();
            String v13 = arg22.getScheme();
            Set v14 = arg22.getCategories();
            int v16 = (arg22.getFlags() & 8) != 0 ? 1 : 0;
            if(v16 != 0) {
                Log.v("LocalBroadcastManager", "Resolving type " + v11 + " scheme " + v13 + " of intent " + v0);
            }

            Object v8 = v1.c.get(arg22.getAction());
            if(v8 != null) {
                if(v16 != 0) {
                    Log.v("LocalBroadcastManager", "Action list: " + v8);
                }

                ArrayList v6 = null;
                int v7 = 0;
                while(v7 < ((ArrayList)v8).size()) {
                    Object v5 = ((ArrayList)v8).get(v7);
                    if(v16 != 0) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + ((b)v5).a);
                    }

                    if(((b)v5).c) {
                        if(v16 != 0) {
                            Log.v("LocalBroadcastManager", "  Filter\'s target already added");
                        }

                        v18 = v7;
                        v19 = v8;
                        v17 = v10;
                        v20 = v11;
                        v10_1 = v6;
                        goto label_129;
                    }
                    else {
                        String v4_1 = v10;
                        Object v15 = v5;
                        v17 = v10;
                        v10_1 = v6;
                        v18 = v7;
                        v19 = v8;
                        v20 = v11;
                        v3 = ((b)v5).a.match(v4_1, v11, v13, v12, v14, "LocalBroadcastManager");
                        if(v3 >= 0) {
                            if(v16 != 0) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(v3));
                            }

                            v6 = v10_1 == null ? new ArrayList() : v10_1;
                            v6.add(v15);
                            ((b)v15).c = true;
                            goto label_130;
                        }

                        if(v16 != 0) {
                            switch(v3) {
                                case -4: {
                                    v3_1 = "category";
                                    break;
                                }
                                case -3: {
                                    v3_1 = "action";
                                    break;
                                }
                                case -2: {
                                    v3_1 = "data";
                                    break;
                                }
                                case -1: {
                                    v3_1 = "type";
                                    break;
                                }
                                default: {
                                    v3_1 = "unknown reason";
                                    break;
                                }
                            }

                            Log.v("LocalBroadcastManager", "  Filter did not match: " + v3_1);
                        }

                    label_129:
                        v6 = v10_1;
                    }

                label_130:
                    v7 = v18 + 1;
                    v10 = v17;
                    v8 = v19;
                    v11 = v20;
                }

                v10_1 = v6;
                if(v10_1 == null) {
                    goto label_157;
                }

                for(v3 = 0; v3 < v10_1.size(); ++v3) {
                    v10_1.get(v3).c = false;
                }

                v1.d.add(new a(v0, v10_1));
                if(!v1.e.hasMessages(1)) {
                    v1.e.sendEmptyMessage(1);
                }

                __monitor_exit(v2);
                return 1;
            }

        label_157:
            __monitor_exit(v2);
            return 0;
        label_161:
            __monitor_exit(v2);
        }
        catch(Throwable v0_1) {
            goto label_161;
        }

        throw v0_1;
    }
}

