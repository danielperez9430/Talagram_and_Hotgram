package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.d;
import android.support.v4.view.t;
import android.support.v7.a.a$f;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;

public class AlertDialogLayout extends ap {
    public AlertDialogLayout(Context arg1) {
        super(arg1);
    }

    public AlertDialogLayout(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    private void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
        arg1.layout(arg2, arg3, arg4 + arg2, arg5 + arg3);
    }

    private static int c(View arg3) {
        int v0 = t.l(arg3);
        if(v0 > 0) {
            return v0;
        }

        if(((arg3 instanceof ViewGroup)) && ((ViewGroup)arg3).getChildCount() == 1) {
            return AlertDialogLayout.c(((ViewGroup)arg3).getChildAt(0));
        }

        return 0;
    }

    private boolean c(int arg17, int arg18) {
        int v15;
        int v14;
        int v13;
        int v6_1;
        AlertDialogLayout v0 = this;
        int v1 = arg17;
        int v2 = arg18;
        int v3 = this.getChildCount();
        View v6 = null;
        View v7 = v6;
        View v8 = v7;
        int v4;
        for(v4 = 0; true; ++v4) {
            int v9 = 8;
            if(v4 >= v3) {
                break;
            }

            View v10 = v0.getChildAt(v4);
            if(v10.getVisibility() == v9) {
            }
            else {
                v9 = v10.getId();
                if(v9 == f.topPanel) {
                    v6 = v10;
                }
                else if(v9 == f.buttonPanel) {
                    v7 = v10;
                }
                else {
                    if(v9 != f.contentPanel) {
                        if(v9 == f.customPanel) {
                        }
                        else {
                            return 0;
                        }
                    }

                    if(v8 != null) {
                        return 0;
                    }

                    v8 = v10;
                }
            }
        }

        v4 = View$MeasureSpec.getMode(arg18);
        int v10_1 = View$MeasureSpec.getSize(arg18);
        int v11 = View$MeasureSpec.getMode(arg17);
        int v12 = this.getPaddingTop() + this.getPaddingBottom();
        if(v6 != null) {
            v6.measure(v1, 0);
            v12 += v6.getMeasuredHeight();
            v6_1 = View.combineMeasuredStates(0, v6.getMeasuredState());
        }
        else {
            v6_1 = 0;
        }

        if(v7 != null) {
            v7.measure(v1, 0);
            v13 = AlertDialogLayout.c(v7);
            v14 = v7.getMeasuredHeight() - v13;
            v12 += v13;
            v6_1 = View.combineMeasuredStates(v6_1, v7.getMeasuredState());
        }
        else {
            v13 = 0;
            v14 = 0;
        }

        if(v8 != null) {
            v15 = v4 == 0 ? 0 : View$MeasureSpec.makeMeasureSpec(Math.max(0, v10_1 - v12), v4);
            v8.measure(v1, v15);
            v15 = v8.getMeasuredHeight();
            v12 += v15;
            v6_1 = View.combineMeasuredStates(v6_1, v8.getMeasuredState());
        }
        else {
            v15 = 0;
        }

        v10_1 -= v12;
        int v5 = 1073741824;
        if(v7 != null) {
            v12 -= v13;
            v14 = Math.min(v10_1, v14);
            if(v14 > 0) {
                v10_1 -= v14;
                v13 += v14;
            }

            v7.measure(v1, View$MeasureSpec.makeMeasureSpec(v13, v5));
            v12 += v7.getMeasuredHeight();
            v6_1 = View.combineMeasuredStates(v6_1, v7.getMeasuredState());
        }

        if(v8 != null && v10_1 > 0) {
            v8.measure(v1, View$MeasureSpec.makeMeasureSpec(v15 + v10_1, v4));
            v12 = v12 - v15 + v8.getMeasuredHeight();
            v6_1 = View.combineMeasuredStates(v6_1, v8.getMeasuredState());
        }

        v4 = 0;
        int v7_1 = 0;
        while(v4 < v3) {
            v8 = v0.getChildAt(v4);
            if(v8.getVisibility() != v9) {
                v7_1 = Math.max(v7_1, v8.getMeasuredWidth());
            }

            ++v4;
        }

        v0.setMeasuredDimension(View.resolveSizeAndState(v7_1 + (this.getPaddingLeft() + this.getPaddingRight()), v1, v6_1), View.resolveSizeAndState(v12, v2, 0));
        if(v11 != v5) {
            v0.d(v3, v2);
        }

        return 1;
    }

    private void d(int arg11, int arg12) {
        int v0 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        int v1;
        for(v1 = 0; v1 < arg11; ++v1) {
            View v3 = this.getChildAt(v1);
            if(v3.getVisibility() != 8) {
                ViewGroup$LayoutParams v8 = v3.getLayoutParams();
                if(((a)v8).width == -1) {
                    int v9 = ((a)v8).height;
                    ((a)v8).height = v3.getMeasuredHeight();
                    this.measureChildWithMargins(v3, v0, 0, arg12, 0);
                    ((a)v8).height = v9;
                }
            }
        }
    }

    protected void onLayout(boolean arg18, int arg19, int arg20, int arg21, int arg22) {
        int v0;
        AlertDialogLayout v6 = this;
        int v7 = this.getPaddingLeft();
        int v2 = arg21 - arg19;
        int v8 = v2 - this.getPaddingRight();
        int v9 = v2 - v7 - this.getPaddingRight();
        v2 = this.getMeasuredHeight();
        int v10 = this.getChildCount();
        int v3 = this.getGravity();
        int v4 = v3 & 112;
        int v11 = v3 & 8388615;
        if(v4 == 16) {
            v0 = (arg22 - arg20 - v2) / 2 + this.getPaddingTop();
        }
        else if(v4 != 80) {
            v0 = this.getPaddingTop();
        }
        else {
            v0 = this.getPaddingTop() + arg22 - arg20 - v2;
        }

        Drawable v1 = this.getDividerDrawable();
        int v12 = v1 == null ? 0 : v1.getIntrinsicHeight();
        int v13;
        for(v13 = 0; v13 < v10; ++v13) {
            View v1_1 = v6.getChildAt(v13);
            if(v1_1 != null && v1_1.getVisibility() != 8) {
                v4 = v1_1.getMeasuredWidth();
                int v14 = v1_1.getMeasuredHeight();
                ViewGroup$LayoutParams v15 = v1_1.getLayoutParams();
                v2 = ((a)v15).h;
                if(v2 < 0) {
                    v2 = v11;
                }

                v2 = d.a(v2, t.f(((View)this))) & 7;
                if(v2 == 1) {
                    v2 = (v9 - v4) / 2 + v7 + ((a)v15).leftMargin;
                label_68:
                    v2 -= ((a)v15).rightMargin;
                }
                else if(v2 != 5) {
                    v2 = ((a)v15).leftMargin + v7;
                }
                else {
                    v2 = v8 - v4;
                    goto label_68;
                }

                if(v6.c(v13)) {
                    v0 += v12;
                }

                int v16 = v0 + ((a)v15).topMargin;
                this.a(v1_1, v2, v16, v4, v14);
                v0 = v16 + (v14 + ((a)v15).bottomMargin);
            }
        }
    }

    protected void onMeasure(int arg2, int arg3) {
        if(!this.c(arg2, arg3)) {
            super.onMeasure(arg2, arg3);
        }
    }
}

