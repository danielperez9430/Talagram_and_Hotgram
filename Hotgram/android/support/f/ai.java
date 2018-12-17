package android.support.f;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public abstract class ai extends m {
    class a extends AnimatorListenerAdapter implements android.support.f.a$a, c {
        boolean a;
        private final View b;
        private final int c;
        private final ViewGroup d;
        private final boolean e;
        private boolean f;

        a(View arg2, int arg3, boolean arg4) {
            super();
            this.a = false;
            this.b = arg2;
            this.c = arg3;
            this.d = arg2.getParent();
            this.e = arg4;
            this.a(true);
        }

        private void a(boolean arg2) {
            if((this.e) && this.f != arg2 && this.d != null) {
                this.f = arg2;
                x.a(this.d, arg2);
            }
        }

        private void a() {
            if(!this.a) {
                ad.a(this.b, this.c);
                if(this.d != null) {
                    this.d.invalidate();
                }
            }

            this.a(false);
        }

        public void a(m arg1) {
            this.a();
            arg1.b(((c)this));
        }

        public void b(m arg1) {
            this.a(false);
        }

        public void c(m arg1) {
            this.a(true);
        }

        public void d(m arg1) {
        }

        public void onAnimationCancel(Animator arg1) {
            this.a = true;
        }

        public void onAnimationEnd(Animator arg1) {
            this.a();
        }

        public void onAnimationPause(Animator arg2) {
            if(!this.a) {
                ad.a(this.b, this.c);
            }
        }

        public void onAnimationRepeat(Animator arg1) {
        }

        public void onAnimationResume(Animator arg2) {
            if(!this.a) {
                ad.a(this.b, 0);
            }
        }

        public void onAnimationStart(Animator arg1) {
        }
    }

    class b {
        boolean a;
        boolean b;
        int c;
        int d;
        ViewGroup e;
        ViewGroup f;

        b() {
            super();
        }
    }

    private static final String[] h;
    private int i;

    static {
        ai.h = new String[]{"android:visibility:visibility", "android:visibility:parent"};
    }

    public ai() {
        super();
        this.i = 3;
    }

    public Animator a(ViewGroup arg3, s arg4, int arg5, s arg6, int arg7) {
        Animator v0 = null;
        if((this.i & 1) == 1) {
            if(arg6 == null) {
            }
            else {
                if(arg4 == null) {
                    ViewParent v5 = arg6.b.getParent();
                    if(this.b(this.b(((View)v5), false), this.a(((View)v5), false)).a) {
                        return v0;
                    }
                }

                return this.a(arg3, arg6.b, arg4, arg6);
            }
        }

        return v0;
    }

    public Animator a(ViewGroup arg1, View arg2, s arg3, s arg4) {
        return null;
    }

    public Animator a(ViewGroup arg9, s arg10, s arg11) {
        b v0 = this.b(arg10, arg11);
        if((v0.a) && (v0.e != null || v0.f != null)) {
            if(v0.b) {
                return this.a(arg9, arg10, v0.c, arg11, v0.d);
            }

            return this.b(arg9, arg10, v0.c, arg11, v0.d);
        }

        return null;
    }

    public void a(int arg2) {
        if((arg2 & -4) == 0) {
            this.i = arg2;
            return;
        }

        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }

    public void a(s arg1) {
        this.d(arg1);
    }

    public boolean a(s arg5, s arg6) {
        boolean v0 = false;
        if(arg5 == null && arg6 == null) {
            return 0;
        }

        if(arg5 != null && arg6 != null && arg6.a.containsKey("android:visibility:visibility") != arg5.a.containsKey("android:visibility:visibility")) {
            return 0;
        }

        b v5 = this.b(arg5, arg6);
        if((v5.a) && (v5.c == 0 || v5.d == 0)) {
            v0 = true;
        }

        return v0;
    }

    public String[] a() {
        return ai.h;
    }

    private b b(s arg7, s arg8) {
        b v0 = new b();
        v0.a = false;
        v0.b = false;
        ViewGroup v2 = null;
        int v3 = -1;
        if(arg7 == null || !arg7.a.containsKey("android:visibility:visibility")) {
            v0.c = v3;
            v0.e = v2;
        }
        else {
            v0.c = arg7.a.get("android:visibility:visibility").intValue();
            v0.e = arg7.a.get("android:visibility:parent");
        }

        if(arg8 == null || !arg8.a.containsKey("android:visibility:visibility")) {
            v0.d = v3;
        }
        else {
            v0.d = arg8.a.get("android:visibility:visibility").intValue();
            Object v2_1 = arg8.a.get("android:visibility:parent");
        }

        v0.f = v2;
        if(arg7 == null || arg8 == null) {
            if(arg7 == null && v0.d == 0) {
            label_68:
                v0.b = true;
                goto label_69;
            }

            if(arg8 != null) {
                return v0;
            }

            if(v0.c != 0) {
                return v0;
            }

        label_74:
            v0.b = false;
        label_69:
            v0.a = true;
        }
        else {
            if(v0.c == v0.d && v0.e == v0.f) {
                return v0;
            }

            if(v0.c != v0.d) {
                if(v0.c == 0) {
                    goto label_74;
                }

                if(v0.d != 0) {
                    return v0;
                }

                goto label_68;
            }

            if(v0.f == null) {
                goto label_74;
            }

            if(v0.e != null) {
                return v0;
            }

            goto label_68;
        }

        return v0;
    }

    public Animator b(ViewGroup arg7, s arg8, int arg9, s arg10, int arg11) {
        Animator v7;
        ViewParent v2_1;
        int v0 = 2;
        Animator v1 = null;
        if((this.i & v0) != v0) {
            return v1;
        }

        View v9 = arg8 != null ? arg8.b : ((View)v1);
        View v2 = arg10 != null ? arg10.b : ((View)v1);
        if(v2 == null || v2.getParent() == null) {
            if(v2 != null) {
                v9 = v2;
                goto label_33;
            }

            if(v9 != null) {
                if(v9.getParent() == null) {
                    goto label_33;
                }
                else if((v9.getParent() instanceof View)) {
                    v2_1 = v9.getParent();
                    if(!this.b(this.a(((View)v2_1), true), this.b(((View)v2_1), true)).a) {
                    label_29:
                        v9 = r.a(arg7, v9, ((View)v2_1));
                        goto label_33;
                    }
                    else {
                        if(((View)v2_1).getParent() == null) {
                            int v2_2 = ((View)v2_1).getId();
                            if(v2_2 != -1 && arg7.findViewById(v2_2) != null && (this.e)) {
                                goto label_33;
                            }
                        }

                        v9 = ((View)v1);
                    label_33:
                        v2 = ((View)v1);
                        goto label_63;
                    }
                }
            }

            v9 = ((View)v1);
            v2 = v9;
        }
        else {
            if(arg11 == 4) {
            }
            else {
                if(v9 == v2) {
                    goto label_23;
                }

                goto label_25;
            }

        label_23:
            v9 = ((View)v1);
            goto label_63;
        label_25:
            if(this.e) {
                goto label_33;
            }

            v2_1 = v9.getParent();
            goto label_29;
        }

    label_63:
        if(v9 != null && arg8 != null) {
            Object v11 = arg8.a.get("android:visibility:screenLocation");
            int v1_1 = v11[0];
            arg11 = v11[1];
            int[] v0_1 = new int[v0];
            arg7.getLocationOnScreen(v0_1);
            v9.offsetLeftAndRight(v1_1 - v0_1[0] - v9.getLeft());
            v9.offsetTopAndBottom(arg11 - v0_1[1] - v9.getTop());
            w v11_1 = x.a(arg7);
            v11_1.a(v9);
            v7 = this.b(arg7, v9, arg8, arg10);
            if(v7 == null) {
                v11_1.b(v9);
            }
            else {
                v7.addListener(new AnimatorListenerAdapter(v11_1, v9) {
                    public void onAnimationEnd(Animator arg2) {
                        this.a.b(this.b);
                    }
                });
            }

            return v7;
        }

        if(v2 != null) {
            arg9 = v2.getVisibility();
            ad.a(v2, 0);
            v7 = this.b(arg7, v2, arg8, arg10);
            if(v7 != null) {
                a v8 = new a(v2, arg11, true);
                v7.addListener(((Animator$AnimatorListener)v8));
                android.support.f.a.a(v7, ((AnimatorListenerAdapter)v8));
                this.a(((c)v8));
            }
            else {
                ad.a(v2, arg9);
            }

            return v7;
        }

        return v1;
    }

    public Animator b(ViewGroup arg1, View arg2, s arg3, s arg4) {
        return null;
    }

    public void b(s arg1) {
        this.d(arg1);
    }

    private void d(s arg4) {
        arg4.a.put("android:visibility:visibility", Integer.valueOf(arg4.b.getVisibility()));
        arg4.a.put("android:visibility:parent", arg4.b.getParent());
        int[] v0 = new int[2];
        arg4.b.getLocationOnScreen(v0);
        arg4.a.put("android:visibility:screenLocation", v0);
    }
}

