package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.design.a$f;
import android.support.design.a.h;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.design.c.d$c;
import android.support.design.c.d;
import android.support.design.widget.CoordinatorLayout$e;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.l;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    public class a {
        public h a;
        public j b;

        protected a() {
            super();
        }
    }

    private final Rect a;
    private final RectF b;
    private final RectF c;
    private final int[] d;

    public FabTransformationBehavior() {
        super();
        this.a = new Rect();
        this.b = new RectF();
        this.c = new RectF();
        this.d = new int[2];
    }

    public FabTransformationBehavior(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.a = new Rect();
        this.b = new RectF();
        this.c = new RectF();
        this.d = new int[2];
    }

    private float a(a arg9, i arg10, float arg11, float arg12) {
        long v0 = arg10.a();
        long v2 = arg10.b();
        i v9 = arg9.a.b("expansion");
        return android.support.design.a.a.a(arg11, arg12, arg10.c().getInterpolation((((float)(v9.a() + v9.b() + 17 - v0))) / (((float)v2))));
    }

    private float a(View arg3, View arg4, j arg5) {
        float v4;
        float v3_1;
        RectF v0 = this.b;
        RectF v1 = this.c;
        this.a(arg3, v0);
        this.a(arg4, v1);
        int v3 = arg5.a & 7;
        if(v3 == 1) {
            v3_1 = v1.centerX();
            v4 = v0.centerX();
        label_22:
            v3_1 -= v4;
        }
        else if(v3 == 3) {
            v3_1 = v1.left;
            v4 = v0.left;
            goto label_22;
        }
        else if(v3 != 5) {
            v3_1 = 0f;
        }
        else {
            v3_1 = v1.right;
            v4 = v0.right;
            goto label_22;
        }

        return v3_1 + arg5.b;
    }

    private void a(View arg4, RectF arg5) {
        arg5.set(0f, 0f, ((float)arg4.getWidth()), ((float)arg4.getHeight()));
        int[] v0 = this.d;
        arg4.getLocationInWindow(v0);
        arg5.offsetTo(((float)v0[0]), ((float)v0[1]));
        arg5.offset(((float)(((int)(-arg4.getTranslationX())))), ((float)(((int)(-arg4.getTranslationY())))));
    }

    private ViewGroup a(View arg2) {
        View v0 = arg2.findViewById(f.mtrl_child_content_container);
        if(v0 != null) {
            return this.b(v0);
        }

        if(!(arg2 instanceof b)) {
            if((arg2 instanceof android.support.design.transformation.a)) {
            }
            else {
                return this.b(arg2);
            }
        }

        return this.b(((ViewGroup)arg2).getChildAt(0));
    }

    private void a(View arg4, long arg5, int arg7, int arg8, float arg9, List arg10) {
        if(Build$VERSION.SDK_INT >= 21) {
            long v0 = 0;
            if(arg5 > v0) {
                Animator v4 = ViewAnimationUtils.createCircularReveal(arg4, arg7, arg8, arg9, arg9);
                v4.setStartDelay(v0);
                v4.setDuration(arg5);
                arg10.add(v4);
            }
        }
    }

    private void a(View arg3, long arg4, long arg6, long arg8, int arg10, int arg11, float arg12, List arg13) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg4 += arg6;
            if(arg4 < arg8) {
                Animator v3 = ViewAnimationUtils.createCircularReveal(arg3, arg10, arg11, arg12, arg12);
                v3.setStartDelay(arg4);
                v3.setDuration(arg8 - arg4);
                arg13.add(v3);
            }
        }
    }

    private void a(View arg1, a arg2, i arg3, i arg4, float arg5, float arg6, float arg7, float arg8, RectF arg9) {
        float v3 = this.a(arg2, arg3, arg5, arg7);
        float v2 = this.a(arg2, arg4, arg6, arg8);
        Rect v4 = this.a;
        arg1.getWindowVisibleDisplayFrame(v4);
        RectF v5 = this.b;
        v5.set(v4);
        RectF v4_1 = this.c;
        this.a(arg1, v4_1);
        v4_1.offset(v3, v2);
        v4_1.intersect(v5);
        arg9.set(v4_1);
    }

    private void a(View arg21, View arg22, boolean arg23, boolean arg24, a arg25, float arg26, float arg27, List arg28, List arg29) {
        i v15;
        Animator v9_1;
        float v6;
        FabTransformationBehavior v12 = this;
        View v0 = arg21;
        View v8 = arg22;
        a v9 = arg25;
        if(!(v8 instanceof d)) {
            return;
        }

        View v13 = v8;
        float v2 = v12.c(v0, v8, v9.b);
        float v3 = v12.d(v0, v8, v9.b);
        ((FloatingActionButton)v0).a(v12.a);
        float v10 = (((float)v12.a.width())) / 2f;
        i v11 = v9.a.b("expansion");
        if(arg23) {
            if(!arg24) {
                ((d)v13).setRevealInfo(new android.support.design.c.d$d(v2, v3, v10));
            }

            v6 = arg24 ? ((d)v13).getRevealInfo().c : v10;
            v9_1 = android.support.design.c.a.a(((d)v13), v2, v3, l.a(v2, v3, 0f, 0f, arg26, arg27));
            v9_1.addListener(new AnimatorListenerAdapter(((d)v13)) {
                public void onAnimationEnd(Animator arg2) {
                    android.support.design.c.d$d v2 = this.a.getRevealInfo();
                    v2.c = 340282346638528860000000000000000000000f;
                    this.a.setRevealInfo(v2);
                }
            });
            this.a(arg22, v11.a(), ((int)v2), ((int)v3), v6, arg28);
            v15 = v11;
        }
        else {
            v6 = ((d)v13).getRevealInfo().c;
            Animator v14 = android.support.design.c.a.a(((d)v13), v2, v3, v10);
            int v15_1 = ((int)v2);
            int v7 = ((int)v3);
            this.a(arg22, v11.a(), v15_1, v7, v6, arg28);
            int v8_1 = v15_1;
            v15 = v11;
            this.a(arg22, v11.a(), v11.b(), v9.a.a(), v8_1, v7, v10, arg28);
            v9_1 = v14;
        }

        v15.a(v9_1);
        arg28.add(v9_1);
        arg29.add(android.support.design.c.a.a(((d)v13)));
    }

    @TargetApi(value=21) private void a(View arg2, View arg3, boolean arg4, boolean arg5, a arg6, List arg7, List arg8) {
        float[] v5;
        Property v4;
        float v8 = t.p(arg3) - t.p(arg2);
        if(arg4) {
            if(!arg5) {
                arg3.setTranslationZ(-v8);
            }

            v4 = View.TRANSLATION_Z;
            v5 = new float[]{0f};
        }
        else {
            v4 = View.TRANSLATION_Z;
            v5 = new float[]{-v8};
        }

        ObjectAnimator v2 = ObjectAnimator.ofFloat(arg3, v4, v5);
        arg6.a.b("elevation").a(((Animator)v2));
        arg7.add(v2);
    }

    private void a(View arg17, View arg18, boolean arg19, boolean arg20, a arg21, List arg22, List arg23, RectF arg24) {
        ObjectAnimator v15;
        ObjectAnimator v14;
        String v8;
        h v7;
        i v6;
        View v1 = arg18;
        a v3 = arg21;
        List v11 = arg22;
        float v4 = this.a(arg17, v1, v3.b);
        float v0 = this.b(arg17, v1, v3.b);
        if(v4 == 0f || v0 == 0f) {
            v6 = v3.a.b("translationXLinear");
            v7 = v3.a;
            v8 = "translationYLinear";
        }
        else {
            if((arg19) && v0 < 0f || !arg19 && v0 > 0f) {
                v6 = v3.a.b("translationXCurveUpwards");
                v7 = v3.a;
                v8 = "translationYCurveUpwards";
                goto label_34;
            }

            v6 = v3.a.b("translationXCurveDownwards");
            v7 = v3.a;
            v8 = "translationYCurveDownwards";
        }

    label_34:
        i v12 = v6;
        i v13 = v7.b(v8);
        if(arg19) {
            if(!arg20) {
                v1.setTranslationX(-v4);
                v1.setTranslationY(-v0);
            }

            v14 = ObjectAnimator.ofFloat(v1, View.TRANSLATION_X, new float[]{0f});
            v15 = ObjectAnimator.ofFloat(v1, View.TRANSLATION_Y, new float[]{0f});
            this.a(arg18, arg21, v12, v13, -v4, -v0, 0f, 0f, arg24);
        }
        else {
            v14 = ObjectAnimator.ofFloat(v1, View.TRANSLATION_X, new float[]{-v4});
            v15 = ObjectAnimator.ofFloat(v1, View.TRANSLATION_Y, new float[]{-v0});
        }

        v12.a(((Animator)v14));
        v13.a(((Animator)v15));
        v11.add(v14);
        v11.add(v15);
    }

    protected abstract a a(Context arg1, boolean arg2);

    public void a(e arg2) {
        if(arg2.h == 0) {
            arg2.h = 80;
        }
    }

    public boolean a(CoordinatorLayout arg2, View arg3, View arg4) {
        if(arg3.getVisibility() != 8) {
            boolean v0 = false;
            if((arg4 instanceof FloatingActionButton)) {
                int v2 = ((FloatingActionButton)arg4).getExpandedComponentIdHint();
                if(v2 != 0 && v2 != arg3.getId()) {
                    return v0;
                }

                v0 = true;
            }

            return v0;
        }

        throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
    }

    private ViewGroup b(View arg2) {
        if((arg2 instanceof ViewGroup)) {
            return arg2;
        }

        return null;
    }

    private float b(View arg3, View arg4, j arg5) {
        float v4;
        float v3_1;
        RectF v0 = this.b;
        RectF v1 = this.c;
        this.a(arg3, v0);
        this.a(arg4, v1);
        int v3 = arg5.a & 112;
        if(v3 == 16) {
            v3_1 = v1.centerY();
            v4 = v0.centerY();
        label_22:
            v3_1 -= v4;
        }
        else if(v3 == 48) {
            v3_1 = v1.top;
            v4 = v0.top;
            goto label_22;
        }
        else if(v3 != 80) {
            v3_1 = 0f;
        }
        else {
            v3_1 = v1.bottom;
            v4 = v0.bottom;
            goto label_22;
        }

        return v3_1 + arg5.c;
    }

    private void b(View arg5, View arg6, boolean arg7, boolean arg8, a arg9, List arg10, List arg11) {
        int[] v8;
        Property v7;
        if((arg6 instanceof d)) {
            if(!(arg5 instanceof ImageView)) {
            }
            else {
                View v0 = arg6;
                Drawable v5 = ((ImageView)arg5).getDrawable();
                if(v5 == null) {
                    return;
                }
                else {
                    v5.mutate();
                    int v1 = 255;
                    if(arg7) {
                        if(!arg8) {
                            v5.setAlpha(v1);
                        }

                        v7 = android.support.design.a.e.a;
                        v8 = new int[]{0};
                    }
                    else {
                        v7 = android.support.design.a.e.a;
                        v8 = new int[]{v1};
                    }

                    ObjectAnimator v7_1 = ObjectAnimator.ofInt(v5, v7, v8);
                    v7_1.addUpdateListener(new ValueAnimator$AnimatorUpdateListener(arg6) {
                        public void onAnimationUpdate(ValueAnimator arg1) {
                            this.a.invalidate();
                        }
                    });
                    arg9.a.b("iconFade").a(((Animator)v7_1));
                    arg10.add(v7_1);
                    arg11.add(new AnimatorListenerAdapter(((d)v0), v5) {
                        public void onAnimationEnd(Animator arg2) {
                            this.a.setCircularRevealOverlayDrawable(null);
                        }

                        public void onAnimationStart(Animator arg2) {
                            this.a.setCircularRevealOverlayDrawable(this.b);
                        }
                    });
                }
            }
        }
    }

    protected AnimatorSet b(View arg16, View arg17, boolean arg18, boolean arg19) {
        FabTransformationBehavior v10 = this;
        boolean v11 = arg18;
        a v12 = this.a(arg17.getContext(), v11);
        ArrayList v13 = new ArrayList();
        ArrayList v14 = new ArrayList();
        if(Build$VERSION.SDK_INT >= 21) {
            this.a(arg16, arg17, arg18, arg19, v12, v13, v14);
        }

        RectF v9 = v10.b;
        this.a(arg16, arg17, arg18, arg19, v12, v13, v14, v9);
        float v8 = v9.width();
        float v9_1 = v9.height();
        this.b(arg16, arg17, arg18, arg19, v12, v13, v14);
        this.a(arg16, arg17, arg18, arg19, v12, v8, v9_1, v13, v14);
        this.c(arg16, arg17, arg18, arg19, v12, v13, v14);
        this.d(arg16, arg17, arg18, arg19, v12, v13, v14);
        AnimatorSet v0 = new AnimatorSet();
        android.support.design.a.b.a(v0, ((List)v13));
        v0.addListener(new AnimatorListenerAdapter(v11, arg17, arg16) {
            public void onAnimationEnd(Animator arg2) {
                if(!this.a) {
                    this.b.setVisibility(4);
                    this.c.setAlpha(1f);
                    this.c.setVisibility(0);
                }
            }

            public void onAnimationStart(Animator arg2) {
                if(this.a) {
                    this.b.setVisibility(0);
                    this.c.setAlpha(0f);
                    this.c.setVisibility(4);
                }
            }
        });
        int v1 = 0;
        int v2 = ((List)v14).size();
        while(v1 < v2) {
            v0.addListener(((List)v14).get(v1));
            ++v1;
        }

        return v0;
    }

    private float c(View arg3, View arg4, j arg5) {
        RectF v0 = this.b;
        RectF v1 = this.c;
        this.a(arg3, v0);
        this.a(arg4, v1);
        v1.offset(-this.a(arg3, arg4, arg5), 0f);
        return v0.centerX() - v1.left;
    }

    private int c(View arg3) {
        ColorStateList v0 = t.w(arg3);
        if(v0 != null) {
            return v0.getColorForState(arg3.getDrawableState(), v0.getDefaultColor());
        }

        return 0;
    }

    private void c(View arg3, View arg4, boolean arg5, boolean arg6, a arg7, List arg8, List arg9) {
        ObjectAnimator v3_1;
        if(!(arg4 instanceof d)) {
            return;
        }

        int v3 = this.c(arg3);
        int v9 = 16777215 & v3;
        if(arg5) {
            if(!arg6) {
                ((d)arg4).setCircularRevealScrimColor(v3);
            }

            v3_1 = ObjectAnimator.ofInt(arg4, c.a, new int[]{v9});
        }
        else {
            v3_1 = ObjectAnimator.ofInt(arg4, c.a, new int[]{v3});
        }

        v3_1.setEvaluator(android.support.design.a.c.a());
        arg7.a.b("color").a(((Animator)v3_1));
        arg8.add(v3_1);
    }

    private float d(View arg3, View arg4, j arg5) {
        RectF v0 = this.b;
        RectF v1 = this.c;
        this.a(arg3, v0);
        this.a(arg4, v1);
        v1.offset(0f, -this.b(arg3, arg4, arg5));
        return v0.centerY() - v1.top;
    }

    private void d(View arg2, View arg3, boolean arg4, boolean arg5, a arg6, List arg7, List arg8) {
        ObjectAnimator v2_1;
        if(!(arg3 instanceof ViewGroup)) {
            return;
        }

        if(((arg3 instanceof d)) && android.support.design.c.c.a == 0) {
            return;
        }

        ViewGroup v2 = this.a(arg3);
        if(v2 == null) {
            return;
        }

        if(arg4) {
            if(!arg5) {
                android.support.design.a.d.a.set(v2, Float.valueOf(0f));
            }

            v2_1 = ObjectAnimator.ofFloat(v2, android.support.design.a.d.a, new float[]{1f});
        }
        else {
            v2_1 = ObjectAnimator.ofFloat(v2, android.support.design.a.d.a, new float[]{0f});
        }

        arg6.a.b("contentFade").a(((Animator)v2_1));
        arg7.add(v2_1);
    }
}

