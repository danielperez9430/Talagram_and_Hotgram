package android.support.f;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.t;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Map;

public class c extends m {
    final class android.support.f.c$1 extends Property {
        private Rect a;

        android.support.f.c$1(Class arg1, String arg2) {
            super(arg1, arg2);
            this.a = new Rect();
        }

        public PointF a(Drawable arg3) {
            arg3.copyBounds(this.a);
            return new PointF(((float)this.a.left), ((float)this.a.top));
        }

        public void a(Drawable arg3, PointF arg4) {
            arg3.copyBounds(this.a);
            this.a.offsetTo(Math.round(arg4.x), Math.round(arg4.y));
            arg3.setBounds(this.a);
        }

        public Object get(Object arg1) {
            return this.a(((Drawable)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((Drawable)arg1), ((PointF)arg2));
        }
    }

    final class android.support.f.c$3 extends Property {
        android.support.f.c$3(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public PointF a(a arg1) {
            return null;
        }

        public void a(a arg1, PointF arg2) {
            arg1.a(arg2);
        }

        public Object get(Object arg1) {
            return this.a(((a)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((a)arg1), ((PointF)arg2));
        }
    }

    final class android.support.f.c$4 extends Property {
        android.support.f.c$4(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public PointF a(a arg1) {
            return null;
        }

        public void a(a arg1, PointF arg2) {
            arg1.b(arg2);
        }

        public Object get(Object arg1) {
            return this.a(((a)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((a)arg1), ((PointF)arg2));
        }
    }

    final class android.support.f.c$5 extends Property {
        android.support.f.c$5(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public PointF a(View arg1) {
            return null;
        }

        public void a(View arg4, PointF arg5) {
            ad.a(arg4, arg4.getLeft(), arg4.getTop(), Math.round(arg5.x), Math.round(arg5.y));
        }

        public Object get(Object arg1) {
            return this.a(((View)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((View)arg1), ((PointF)arg2));
        }
    }

    final class android.support.f.c$6 extends Property {
        android.support.f.c$6(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public PointF a(View arg1) {
            return null;
        }

        public void a(View arg4, PointF arg5) {
            ad.a(arg4, Math.round(arg5.x), Math.round(arg5.y), arg4.getRight(), arg4.getBottom());
        }

        public Object get(Object arg1) {
            return this.a(((View)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((View)arg1), ((PointF)arg2));
        }
    }

    final class android.support.f.c$7 extends Property {
        android.support.f.c$7(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public PointF a(View arg1) {
            return null;
        }

        public void a(View arg4, PointF arg5) {
            int v0 = Math.round(arg5.x);
            int v5 = Math.round(arg5.y);
            ad.a(arg4, v0, v5, arg4.getWidth() + v0, arg4.getHeight() + v5);
        }

        public Object get(Object arg1) {
            return this.a(((View)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((View)arg1), ((PointF)arg2));
        }
    }

    class a {
        private int a;
        private int b;
        private int c;
        private int d;
        private View e;
        private int f;
        private int g;

        a(View arg1) {
            super();
            this.e = arg1;
        }

        void a(PointF arg2) {
            this.a = Math.round(arg2.x);
            this.b = Math.round(arg2.y);
            ++this.f;
            if(this.f == this.g) {
                this.a();
            }
        }

        private void a() {
            ad.a(this.e, this.a, this.b, this.c, this.d);
            this.f = 0;
            this.g = 0;
        }

        void b(PointF arg2) {
            this.c = Math.round(arg2.x);
            this.d = Math.round(arg2.y);
            ++this.g;
            if(this.f == this.g) {
                this.a();
            }
        }
    }

    private static final String[] h;
    private static final Property i;
    private static final Property j;
    private static final Property k;
    private static final Property l;
    private static final Property m;
    private static final Property n;
    private int[] o;
    private boolean p;
    private boolean q;
    private static k r;

    static {
        c.h = new String[]{"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
        c.i = new android.support.f.c$1(PointF.class, "boundsOrigin");
        c.j = new android.support.f.c$3(PointF.class, "topLeft");
        c.k = new android.support.f.c$4(PointF.class, "bottomRight");
        c.l = new android.support.f.c$5(PointF.class, "bottomRight");
        c.m = new android.support.f.c$6(PointF.class, "topLeft");
        c.n = new android.support.f.c$7(PointF.class, "position");
        c.r = new k();
    }

    public c() {
        super();
        this.o = new int[2];
        this.p = false;
        this.q = false;
    }

    private boolean a(View arg4, View arg5) {
        boolean v1 = true;
        if(this.q) {
            s v0 = this.b(arg4, true);
            if(v0 != null) {
                if(arg5 != v0.b) {
                    goto label_8;
                }

                return v1;
            }
            else if(arg4 == arg5) {
                return v1;
            }

        label_8:
            v1 = false;
        }

        return v1;
    }

    public Animator a(ViewGroup arg21, s arg22, s arg23) {
        Animator v0_8;
        ObjectAnimator v10_1;
        Rect v2_2;
        Object v1_5;
        ObjectAnimator v11_2;
        Property v1_2;
        Path v0_3;
        int v0_2;
        int v1_1;
        int v2;
        int v3_2;
        c v8 = this;
        s v0 = arg22;
        s v1 = arg23;
        if(v0 != null) {
            if(v1 == null) {
            }
            else {
                Map v3 = v0.a;
                Map v4 = v1.a;
                Object v3_1 = v3.get("android:changeBounds:parent");
                Object v4_1 = v4.get("android:changeBounds:parent");
                if(v3_1 != null) {
                    if(v4_1 == null) {
                    }
                    else {
                        View v9 = v1.b;
                        if(v8.a(((View)v3_1), ((View)v4_1))) {
                            v3_1 = v0.a.get("android:changeBounds:bounds");
                            Object v5 = v1.a.get("android:changeBounds:bounds");
                            int v6 = ((Rect)v3_1).left;
                            int v7 = ((Rect)v5).left;
                            int v11 = ((Rect)v3_1).top;
                            int v12 = ((Rect)v5).top;
                            int v13 = ((Rect)v3_1).right;
                            int v14 = ((Rect)v5).right;
                            v3_2 = ((Rect)v3_1).bottom;
                            int v15 = ((Rect)v5).bottom;
                            int v5_1 = v13 - v6;
                            v2 = v3_2 - v11;
                            int v10 = v14 - v7;
                            int v4_2 = v15 - v12;
                            View v16 = v9;
                            Object v0_1 = v0.a.get("android:changeBounds:clip");
                            Object v9_1 = v1.a.get("android:changeBounds:clip");
                            if(v5_1 == 0 || v2 == 0) {
                                if(v10 != 0 && v4_2 != 0) {
                                label_48:
                                    v1_1 = v6 != v7 || v11 != v12 ? 1 : 0;
                                    if(v13 == v14 && v3_2 == v15) {
                                        goto label_59;
                                    }

                                    ++v1_1;
                                    goto label_59;
                                }

                                v1_1 = 0;
                            }
                            else {
                                goto label_48;
                            }

                        label_59:
                            if(v0_1 != null && !((Rect)v0_1).equals(v9_1) || v0_1 == null && v9_1 != null) {
                                ++v1_1;
                            }

                            if(v1_1 <= 0) {
                                return null;
                            }

                            Object v18 = v9_1;
                            Object v19 = v0_1;
                            v0_2 = 2;
                            if(!v8.p) {
                                v9 = v16;
                                ad.a(v9, v6, v11, v13, v3_2);
                                if(v1_1 == v0_2) {
                                    if(v5_1 == v10 && v2 == v4_2) {
                                        v0_3 = this.l().a(((float)v6), ((float)v11), ((float)v7), ((float)v12));
                                        v1_2 = c.n;
                                        goto label_133;
                                    }

                                    a v1_3 = new a(v9);
                                    ObjectAnimator v2_1 = f.a(v1_3, c.j, this.l().a(((float)v6), ((float)v11), ((float)v7), ((float)v12)));
                                    ObjectAnimator v3_3 = f.a(v1_3, c.k, this.l().a(((float)v13), ((float)v3_2), ((float)v14), ((float)v15)));
                                    AnimatorSet v4_3 = new AnimatorSet();
                                    Animator[] v0_4 = new Animator[v0_2];
                                    v0_4[0] = v2_1;
                                    v0_4[1] = v3_3;
                                    v4_3.playTogether(v0_4);
                                    v4_3.addListener(new AnimatorListenerAdapter(v1_3) {
                                        private a mViewBounds;

                                    });
                                    AnimatorSet v0_5 = v4_3;
                                    goto label_192;
                                }
                                else {
                                    if(v6 == v7) {
                                        if(v11 != v12) {
                                        }
                                        else {
                                            v0_3 = this.l().a(((float)v13), ((float)v3_2), ((float)v14), ((float)v15));
                                            v1_2 = c.l;
                                            goto label_133;
                                        }
                                    }

                                    v0_3 = this.l().a(((float)v6), ((float)v11), ((float)v7), ((float)v12));
                                    v1_2 = c.m;
                                }

                            label_133:
                                ObjectAnimator v0_6 = f.a(v9, v1_2, v0_3);
                            }
                            else {
                                v9 = v16;
                                ad.a(v9, v6, v11, Math.max(v5_1, v10) + v6, Math.max(v2, v4_2) + v11);
                                if(v6 != v7 || v11 != v12) {
                                    v11_2 = f.a(v9, c.n, this.l().a(((float)v6), ((float)v11), ((float)v7), ((float)v12)));
                                }
                                else {
                                    Animator v11_1 = null;
                                }

                                if(v19 == null) {
                                    v3_2 = 0;
                                    Rect v1_4 = new Rect(0, 0, v5_1, v2);
                                }
                                else {
                                    v3_2 = 0;
                                    v1_5 = v19;
                                }

                                if(v18 == null) {
                                    v2_2 = new Rect(v3_2, v3_2, v10, v4_2);
                                }
                                else {
                                    Object v2_3 = v18;
                                }

                                if(!((Rect)v1_5).equals(v2_2)) {
                                    t.a(v9, ((Rect)v1_5));
                                    k v5_2 = c.r;
                                    Object[] v0_7 = new Object[v0_2];
                                    v0_7[v3_2] = ((Rect)v1_5);
                                    v0_7[1] = v2_2;
                                    v10_1 = ObjectAnimator.ofObject(v9, "clipBounds", ((TypeEvaluator)v5_2), v0_7);
                                    v10_1.addListener(new AnimatorListenerAdapter(v9, v18, v7, v12, v14, v15) {
                                        private boolean h;

                                        public void onAnimationCancel(Animator arg1) {
                                            this.h = true;
                                        }

                                        public void onAnimationEnd(Animator arg5) {
                                            if(!this.h) {
                                                t.a(this.a, this.b);
                                                ad.a(this.a, this.c, this.d, this.e, this.f);
                                            }
                                        }
                                    });
                                }
                                else {
                                    Animator v10_2 = null;
                                }

                                v0_8 = r.a(((Animator)v11_2), ((Animator)v10_1));
                            }

                        label_192:
                            if((v9.getParent() instanceof ViewGroup)) {
                                ViewParent v1_6 = v9.getParent();
                                x.a(((ViewGroup)v1_6), true);
                                v8.a(new n(((ViewGroup)v1_6)) {
                                    boolean a;

                                    public void a(m arg3) {
                                        if(!this.a) {
                                            x.a(this.b, false);
                                        }

                                        arg3.b(((android.support.f.m$c)this));
                                    }

                                    public void b(m arg2) {
                                        x.a(this.b, false);
                                    }

                                    public void c(m arg2) {
                                        x.a(this.b, true);
                                    }
                                });
                            }

                            return v0_8;
                        }
                        else {
                            v2 = v0.a.get("android:changeBounds:windowX").intValue();
                            v0_2 = v0.a.get("android:changeBounds:windowY").intValue();
                            v3_2 = v1.a.get("android:changeBounds:windowX").intValue();
                            v1_1 = v1.a.get("android:changeBounds:windowY").intValue();
                            if(v2 == v3_2) {
                                if(v0_2 != v1_1) {
                                }
                                else {
                                    return null;
                                }
                            }

                            goto label_223;
                        }

                        return null;
                    label_223:
                        arg21.getLocationInWindow(v8.o);
                        Bitmap v4_4 = Bitmap.createBitmap(v9.getWidth(), v9.getHeight(), Bitmap$Config.ARGB_8888);
                        v9.draw(new Canvas(v4_4));
                        BitmapDrawable v6_1 = new BitmapDrawable(v4_4);
                        float v7_1 = ad.c(v9);
                        ad.a(v9, 0f);
                        ad.a(((View)arg21)).a(((Drawable)v6_1));
                        v10_1 = ObjectAnimator.ofPropertyValuesHolder(v6_1, new PropertyValuesHolder[]{i.a(c.i, this.l().a(((float)(v2 - v8.o[0])), ((float)(v0_2 - v8.o[1])), ((float)(v3_2 - v8.o[0])), ((float)(v1_1 - v8.o[1]))))});
                        v10_1.addListener(new AnimatorListenerAdapter(arg21, v6_1, v9, v7_1) {
                            public void onAnimationEnd(Animator arg2) {
                                ad.a(this.a).b(this.b);
                                ad.a(this.c, this.d);
                            }
                        });
                        return ((Animator)v10_1);
                    }
                }

                return null;
            }
        }

        return null;
    }

    public void a(s arg1) {
        this.d(arg1);
    }

    public String[] a() {
        return c.h;
    }

    public void b(s arg1) {
        this.d(arg1);
    }

    private void d(s arg9) {
        View v0 = arg9.b;
        if((t.A(v0)) || v0.getWidth() != 0 || v0.getHeight() != 0) {
            arg9.a.put("android:changeBounds:bounds", new Rect(v0.getLeft(), v0.getTop(), v0.getRight(), v0.getBottom()));
            arg9.a.put("android:changeBounds:parent", arg9.b.getParent());
            if(this.q) {
                arg9.b.getLocationInWindow(this.o);
                arg9.a.put("android:changeBounds:windowX", Integer.valueOf(this.o[0]));
                arg9.a.put("android:changeBounds:windowY", Integer.valueOf(this.o[1]));
            }

            if(!this.p) {
                return;
            }

            arg9.a.put("android:changeBounds:clip", t.C(v0));
        }
    }
}

