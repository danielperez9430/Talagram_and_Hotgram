package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.view.t;
import android.support.v4.view.v;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

public abstract class r {
    public r() {
        super();
    }

    void a(Map arg4, View arg5) {
        if(arg5.getVisibility() == 0) {
            String v0 = t.q(arg5);
            if(v0 != null) {
                arg4.put(v0, arg5);
            }

            if(!(arg5 instanceof ViewGroup)) {
                return;
            }

            int v0_1 = ((ViewGroup)arg5).getChildCount();
            int v1;
            for(v1 = 0; v1 < v0_1; ++v1) {
                this.a(arg4, ((ViewGroup)arg5).getChildAt(v1));
            }
        }
    }

    public abstract void a(Object arg1, View arg2, ArrayList arg3);

    public abstract void a(Object arg1, Rect arg2);

    protected void a(View arg7, Rect arg8) {
        int[] v0 = new int[2];
        arg7.getLocationOnScreen(v0);
        arg8.set(v0[0], v0[1], v0[0] + arg7.getWidth(), v0[1] + arg7.getHeight());
    }

    public abstract Object a(Object arg1, Object arg2, Object arg3);

    void a(ArrayList arg4, View arg5) {
        if(arg5.getVisibility() == 0) {
            if((arg5 instanceof ViewGroup)) {
                if(v.a(((ViewGroup)arg5))) {
                }
                else {
                    int v0 = ((ViewGroup)arg5).getChildCount();
                    int v1 = 0;
                    while(true) {
                        if(v1 < v0) {
                            this.a(arg4, ((ViewGroup)arg5).getChildAt(v1));
                            ++v1;
                            continue;
                        }
                        else {
                            return;
                        }
                    }
                }
            }

            arg4.add(arg5);
        }
    }

    public abstract void a(Object arg1, ArrayList arg2);

    ArrayList a(ArrayList arg6) {
        ArrayList v0 = new ArrayList();
        int v1 = arg6.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg6.get(v2);
            v0.add(t.q(((View)v3)));
            t.a(((View)v3), null);
        }

        return v0;
    }

    public abstract void a(Object arg1, Object arg2, ArrayList arg3, Object arg4, ArrayList arg5, Object arg6, ArrayList arg7);

    public abstract void a(ViewGroup arg1, Object arg2);

    void a(View arg9, ArrayList arg10, ArrayList arg11, ArrayList arg12, Map arg13) {
        int v2 = arg11.size();
        ArrayList v6 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < v2; ++v1) {
            Object v3 = arg10.get(v1);
            String v4 = t.q(((View)v3));
            v6.add(v4);
            if(v4 == null) {
            }
            else {
                t.a(((View)v3), null);
                v3 = arg13.get(v4);
                int v5 = 0;
                while(v5 < v2) {
                    if(((String)v3).equals(arg12.get(v5))) {
                        t.a(arg11.get(v5), v4);
                    }
                    else {
                        ++v5;
                        continue;
                    }

                    break;
                }
            }
        }

        aa.a(arg9, new Runnable(v2, arg11, arg12, arg10, v6) {
            public void run() {
                int v0;
                for(v0 = 0; v0 < this.a; ++v0) {
                    t.a(this.b.get(v0), this.c.get(v0));
                    t.a(this.d.get(v0), this.e.get(v0));
                }
            }
        });
    }

    public abstract void a(Object arg1, ArrayList arg2, ArrayList arg3);

    public abstract void a(Object arg1, View arg2);

    public abstract boolean a(Object arg1);

    void a(View arg2, ArrayList arg3, Map arg4) {
        aa.a(arg2, new Runnable(arg3, arg4) {
            public void run() {
                int v0 = this.a.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    Object v2 = this.a.get(v1);
                    String v3 = t.q(((View)v2));
                    if(v3 != null) {
                        t.a(((View)v2), r.a(this.b, v3));
                    }
                }
            }
        });
    }

    void a(ViewGroup arg2, ArrayList arg3, Map arg4) {
        aa.a(((View)arg2), new Runnable(arg3, arg4) {
            public void run() {
                int v0 = this.a.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    Object v2 = this.a.get(v1);
                    t.a(((View)v2), this.b.get(t.q(((View)v2))));
                }
            }
        });
    }

    static String a(Map arg2, String arg3) {
        Object v0;
        Iterator v2 = arg2.entrySet().iterator();
        do {
            if(!v2.hasNext()) {
                return null;
            }

            v0 = v2.next();
        }
        while(!arg3.equals(((Map$Entry)v0).getValue()));

        return ((Map$Entry)v0).getKey();
    }

    protected static void a(List arg6, View arg7) {
        int v0 = arg6.size();
        if(r.a(arg6, arg7, v0)) {
            return;
        }

        arg6.add(arg7);
        int v7;
        for(v7 = v0; v7 < arg6.size(); ++v7) {
            Object v1 = arg6.get(v7);
            if((v1 instanceof ViewGroup)) {
                int v2 = ((ViewGroup)v1).getChildCount();
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    View v4 = ((ViewGroup)v1).getChildAt(v3);
                    if(!r.a(arg6, v4, v0)) {
                        arg6.add(v4);
                    }
                }
            }
        }
    }

    private static boolean a(List arg3, View arg4, int arg5) {
        int v1;
        for(v1 = 0; v1 < arg5; ++v1) {
            if(arg3.get(v1) == arg4) {
                return 1;
            }
        }

        return 0;
    }

    protected static boolean a(List arg0) {
        boolean v0 = arg0 == null || (arg0.isEmpty()) ? true : false;
        return v0;
    }

    public abstract Object b(Object arg1);

    public abstract Object b(Object arg1, Object arg2, Object arg3);

    public abstract void b(Object arg1, ArrayList arg2, ArrayList arg3);

    public abstract void b(Object arg1, View arg2, ArrayList arg3);

    public abstract void b(Object arg1, View arg2);

    public abstract Object c(Object arg1);

    public abstract void c(Object arg1, View arg2);
}

