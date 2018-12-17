package org.telegram.messenger.support.widget.helper;

import android.graphics.Canvas;
import android.support.v4.view.t;
import android.view.View;
import org.telegram.messenger.support.widget.RecyclerView;

class ItemTouchUIUtilImpl {
    class Api21Impl extends BaseImpl {
        Api21Impl() {
            super();
        }

        public void clearView(View arg3) {
            Object v0 = arg3.getTag();
            if(v0 != null && ((v0 instanceof Float))) {
                t.k(arg3, ((Float)v0).floatValue());
            }

            arg3.setTag(null);
            super.clearView(arg3);
        }

        private float findMaxElevation(RecyclerView arg6, View arg7) {
            int v0 = arg6.getChildCount();
            float v1 = 0f;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                View v3 = arg6.getChildAt(v2);
                if(v3 == arg7) {
                }
                else {
                    float v3_1 = t.p(v3);
                    if(v3_1 > v1) {
                        v1 = v3_1;
                    }
                }
            }

            return v1;
        }

        public void onDraw(Canvas arg4, RecyclerView arg5, View arg6, float arg7, float arg8, int arg9, boolean arg10) {
            if((arg10) && arg6.getTag() == null) {
                Float v0 = Float.valueOf(t.p(arg6));
                t.k(arg6, this.findMaxElevation(arg5, arg6) + 1f);
                arg6.setTag(v0);
            }

            super.onDraw(arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        }
    }

    class BaseImpl implements ItemTouchUIUtil {
        BaseImpl() {
            super();
        }

        public void clearView(View arg2) {
            arg2.setTranslationX(0f);
            arg2.setTranslationY(0f);
        }

        public void onDraw(Canvas arg1, RecyclerView arg2, View arg3, float arg4, float arg5, int arg6, boolean arg7) {
            arg3.setTranslationX(arg4);
            arg3.setTranslationY(arg5);
        }

        public void onDrawOver(Canvas arg1, RecyclerView arg2, View arg3, float arg4, float arg5, int arg6, boolean arg7) {
        }

        public void onSelected(View arg1) {
        }
    }

    ItemTouchUIUtilImpl() {
        super();
    }
}

