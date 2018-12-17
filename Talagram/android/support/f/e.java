package android.support.f;

import android.graphics.Rect;
import android.support.v4.app.r;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class e extends r {
    public e() {
        super();
    }

    private static boolean a(m arg1) {
        boolean v1 = !e.a(arg1.f()) || !e.a(arg1.h()) || !e.a(arg1.i()) ? true : false;
        return v1;
    }

    public Object a(Object arg2, Object arg3, Object arg4) {
        q v0 = new q();
        if(arg2 != null) {
            v0.a(((m)arg2));
        }

        if(arg3 != null) {
            v0.a(((m)arg3));
        }

        if(arg4 != null) {
            v0.a(((m)arg4));
        }

        return v0;
    }

    public void a(ViewGroup arg1, Object arg2) {
        o.a(arg1, ((m)arg2));
    }

    public void a(Object arg2, Rect arg3) {
        if(arg2 != null) {
            ((m)arg2).a(new b(arg3) {
            });
        }
    }

    public void a(Object arg2, View arg3) {
        if(arg3 != null) {
            Rect v0 = new Rect();
            this.a(arg3, v0);
            ((m)arg2).a(new b(v0) {
            });
        }
    }

    public void a(Object arg5, View arg6, ArrayList arg7) {
        List v0 = ((q)arg5).g();
        v0.clear();
        int v1 = arg7.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            e.a(v0, arg7.get(v2));
        }

        v0.add(arg6);
        arg7.add(arg6);
        this.a(arg5, arg7);
    }

    public void a(Object arg4, ArrayList arg5) {
        int v0;
        if(arg4 == null) {
            return;
        }

        int v1 = 0;
        if((arg4 instanceof q)) {
            v0 = ((q)arg4).q();
            while(v1 < v0) {
                this.a(((q)arg4).b(v1), arg5);
                ++v1;
            }
        }
        else if(!e.a(((m)arg4)) && (e.a(((m)arg4).g()))) {
            v0 = arg5.size();
            while(v1 < v0) {
                ((m)arg4).b(arg5.get(v1));
                ++v1;
            }
        }
    }

    public void a(Object arg11, Object arg12, ArrayList arg13, Object arg14, ArrayList arg15, Object arg16, ArrayList arg17) {
        arg11.a(new c(arg12, arg13, arg14, arg15, arg16, arg17) {
            public void a(m arg1) {
            }

            public void b(m arg1) {
            }

            public void c(m arg1) {
            }

            public void d(m arg4) {
                ArrayList v0 = null;
                if(this.a != null) {
                    this.g.b(this.a, this.b, v0);
                }

                if(this.c != null) {
                    this.g.b(this.c, this.d, v0);
                }

                if(this.e != null) {
                    this.g.b(this.e, this.f, v0);
                }
            }
        });
    }

    public void a(Object arg2, ArrayList arg3, ArrayList arg4) {
        if(arg2 != null) {
            ((q)arg2).g().clear();
            ((q)arg2).g().addAll(((Collection)arg4));
            this.b(arg2, arg3, arg4);
        }
    }

    public boolean a(Object arg1) {
        return arg1 instanceof m;
    }

    public void b(Object arg5, ArrayList arg6, ArrayList arg7) {
        int v0;
        int v1 = 0;
        if((arg5 instanceof q)) {
            v0 = ((q)arg5).q();
            while(v1 < v0) {
                this.b(((q)arg5).b(v1), arg6, arg7);
                ++v1;
            }
        }
        else if(!e.a(((m)arg5))) {
            List v0_1 = ((m)arg5).g();
            if(v0_1.size() == arg6.size() && (v0_1.containsAll(((Collection)arg6)))) {
                v0 = arg7 == null ? 0 : arg7.size();
                while(v1 < v0) {
                    ((m)arg5).b(arg7.get(v1));
                    ++v1;
                }

                int v7;
                for(v7 = arg6.size() - 1; v7 >= 0; --v7) {
                    ((m)arg5).c(arg6.get(v7));
                }
            }
        }
    }

    public Object b(Object arg1) {
        m v1;
        if(arg1 != null) {
            v1 = ((m)arg1).o();
        }
        else {
            arg1 = null;
        }

        return v1;
    }

    public Object b(Object arg2, Object arg3, Object arg4) {
        q v2;
        if(arg2 != null && arg3 != null) {
            v2 = new q().a(((m)arg2)).a(((m)arg3)).a(1);
        }
        else if(arg2 != null) {
        }
        else if(arg3 != null) {
            arg2 = arg3;
        }
        else {
            arg2 = null;
        }

        if(arg4 != null) {
            q v3 = new q();
            if(v2 != null) {
                v3.a(((m)v2));
            }

            v3.a(((m)arg4));
            return v3;
        }

        return v2;
    }

    public void b(Object arg1, View arg2) {
        if(arg1 != null) {
            ((m)arg1).b(arg2);
        }
    }

    public void b(Object arg2, View arg3, ArrayList arg4) {
        ((m)arg2).a(new c(arg3, arg4) {
            public void a(m arg4) {
                arg4.b(((c)this));
                this.a.setVisibility(8);
                int v4 = this.b.size();
                int v1;
                for(v1 = 0; v1 < v4; ++v1) {
                    this.b.get(v1).setVisibility(0);
                }
            }

            public void b(m arg1) {
            }

            public void c(m arg1) {
            }

            public void d(m arg1) {
            }
        });
    }

    public Object c(Object arg2) {
        if(arg2 == null) {
            return null;
        }

        q v0 = new q();
        v0.a(((m)arg2));
        return v0;
    }

    public void c(Object arg1, View arg2) {
        if(arg1 != null) {
            ((m)arg1).c(arg2);
        }
    }
}

