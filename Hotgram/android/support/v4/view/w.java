package android.support.v4.view;

import android.os.Build$VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public final class w {
    public static boolean a(ViewParent arg2, View arg3, float arg4, float arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            try {
                return arg2.onNestedPreFling(arg3, arg4, arg5);
            }
            catch(AbstractMethodError v3) {
                Log.e("ViewParentCompat", "ViewParent " + arg2 + " does not implement interface " + "method onNestedPreFling", ((Throwable)v3));
                return 0;
            }
        }

        if((arg2 instanceof m)) {
            return ((m)arg2).onNestedPreFling(arg3, arg4, arg5);
        }

        return 0;
    }

    public static boolean a(ViewParent arg2, View arg3, float arg4, float arg5, boolean arg6) {
        if(Build$VERSION.SDK_INT >= 21) {
            try {
                return arg2.onNestedFling(arg3, arg4, arg5, arg6);
            }
            catch(AbstractMethodError v3) {
                Log.e("ViewParentCompat", "ViewParent " + arg2 + " does not implement interface " + "method onNestedFling", ((Throwable)v3));
                return 0;
            }
        }

        if((arg2 instanceof m)) {
            return ((m)arg2).onNestedFling(arg3, arg4, arg5, arg6);
        }

        return 0;
    }

    public static boolean a(ViewParent arg1, View arg2, View arg3, int arg4, int arg5) {
        if((arg1 instanceof n)) {
            return ((n)arg1).a(arg2, arg3, arg4, arg5);
        }

        if(arg5 == 0) {
            if(Build$VERSION.SDK_INT >= 21) {
                try {
                    return arg1.onStartNestedScroll(arg2, arg3, arg4);
                }
                catch(AbstractMethodError v2) {
                    Log.e("ViewParentCompat", "ViewParent " + arg1 + " does not implement interface " + "method onStartNestedScroll", ((Throwable)v2));
                    return 0;
                }
            }

            if(!(arg1 instanceof m)) {
                return 0;
            }

            return ((m)arg1).onStartNestedScroll(arg2, arg3, arg4);
        }

        return 0;
    }

    public static void a(ViewParent arg8, View arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
        if((arg8 instanceof n)) {
            arg8.a(arg9, arg10, arg11, arg12, arg13, arg14);
            return;
        }

        if(arg14 == 0) {
            if(Build$VERSION.SDK_INT >= 21) {
                try {
                    arg8.onNestedScroll(arg9, arg10, arg11, arg12, arg13);
                }
                catch(AbstractMethodError v9) {
                    Log.e("ViewParentCompat", "ViewParent " + arg8 + " does not implement interface " + "method onNestedScroll", ((Throwable)v9));
                }

                return;
            }

            if((arg8 instanceof m)) {
                arg8.onNestedScroll(arg9, arg10, arg11, arg12, arg13);
            }
        }
    }

    public static void a(ViewParent arg7, View arg8, int arg9, int arg10, int[] arg11, int arg12) {
        if((arg7 instanceof n)) {
            arg7.a(arg8, arg9, arg10, arg11, arg12);
            return;
        }

        if(arg12 == 0) {
            if(Build$VERSION.SDK_INT >= 21) {
                try {
                    arg7.onNestedPreScroll(arg8, arg9, arg10, arg11);
                }
                catch(AbstractMethodError v8) {
                    Log.e("ViewParentCompat", "ViewParent " + arg7 + " does not implement interface " + "method onNestedPreScroll", ((Throwable)v8));
                }

                return;
            }

            if((arg7 instanceof m)) {
                ((m)arg7).onNestedPreScroll(arg8, arg9, arg10, arg11);
            }
        }
    }

    public static void a(ViewParent arg2, View arg3, int arg4) {
        if((arg2 instanceof n)) {
            ((n)arg2).c(arg3, arg4);
            return;
        }

        if(arg4 == 0) {
            if(Build$VERSION.SDK_INT >= 21) {
                try {
                    arg2.onStopNestedScroll(arg3);
                }
                catch(AbstractMethodError v3) {
                    Log.e("ViewParentCompat", "ViewParent " + arg2 + " does not implement interface " + "method onStopNestedScroll", ((Throwable)v3));
                }

                return;
            }

            if((arg2 instanceof m)) {
                ((m)arg2).onStopNestedScroll(arg3);
            }
        }
    }

    @Deprecated public static boolean a(ViewParent arg0, View arg1, AccessibilityEvent arg2) {
        return arg0.requestSendAccessibilityEvent(arg1, arg2);
    }

    public static void b(ViewParent arg1, View arg2, View arg3, int arg4, int arg5) {
        if((arg1 instanceof n)) {
            ((n)arg1).b(arg2, arg3, arg4, arg5);
            return;
        }

        if(arg5 == 0) {
            if(Build$VERSION.SDK_INT >= 21) {
                try {
                    arg1.onNestedScrollAccepted(arg2, arg3, arg4);
                }
                catch(AbstractMethodError v2) {
                    Log.e("ViewParentCompat", "ViewParent " + arg1 + " does not implement interface " + "method onNestedScrollAccepted", ((Throwable)v2));
                }

                return;
            }

            if((arg1 instanceof m)) {
                ((m)arg1).onNestedScrollAccepted(arg2, arg3, arg4);
            }
        }
    }
}

