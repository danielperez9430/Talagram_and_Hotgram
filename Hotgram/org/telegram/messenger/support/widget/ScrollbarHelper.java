package org.telegram.messenger.support.widget;

import android.view.View;

class ScrollbarHelper {
    ScrollbarHelper() {
        super();
    }

    static int computeScrollExtent(State arg1, OrientationHelper arg2, View arg3, View arg4, LayoutManager arg5, boolean arg6) {
        if(arg5.getChildCount() != 0 && arg1.getItemCount() != 0 && arg3 != null) {
            if(arg4 == null) {
            }
            else if(!arg6) {
                return Math.abs(arg5.getPosition(arg3) - arg5.getPosition(arg4)) + 1;
            }
            else {
                return Math.min(arg2.getTotalSpace(), arg2.getDecoratedEnd(arg4) - arg2.getDecoratedStart(arg3));
            }
        }

        return 0;
    }

    static int computeScrollOffset(State arg4, OrientationHelper arg5, View arg6, View arg7, LayoutManager arg8, boolean arg9, boolean arg10) {
        if(arg8.getChildCount() != 0 && arg4.getItemCount() != 0 && arg6 != null) {
            if(arg7 == null) {
            }
            else {
                int v0 = Math.min(arg8.getPosition(arg6), arg8.getPosition(arg7));
                int v2 = Math.max(arg8.getPosition(arg6), arg8.getPosition(arg7));
                int v4 = arg10 ? Math.max(0, arg4.getItemCount() - v2 - 1) : Math.max(0, v0);
                if(!arg9) {
                    return v4;
                }

                return Math.round((((float)v4)) * ((((float)Math.abs(arg5.getDecoratedEnd(arg7) - arg5.getDecoratedStart(arg6)))) / (((float)(Math.abs(arg8.getPosition(arg6) - arg8.getPosition(arg7)) + 1)))) + (((float)(arg5.getStartAfterPadding() - arg5.getDecoratedStart(arg6)))));
            }
        }

        return 0;
    }

    static int computeScrollRange(State arg1, OrientationHelper arg2, View arg3, View arg4, LayoutManager arg5, boolean arg6) {
        if(arg5.getChildCount() != 0 && arg1.getItemCount() != 0 && arg3 != null) {
            if(arg4 == null) {
            }
            else if(!arg6) {
                return arg1.getItemCount();
            }
            else {
                return ((int)((((float)(arg2.getDecoratedEnd(arg4) - arg2.getDecoratedStart(arg3)))) / (((float)(Math.abs(arg5.getPosition(arg3) - arg5.getPosition(arg4)) + 1))) * (((float)arg1.getItemCount()))));
            }
        }

        return 0;
    }
}

