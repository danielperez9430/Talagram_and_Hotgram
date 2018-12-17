package android.support.v4.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface;
import android.os.Build$VERSION;
import android.view.KeyEvent$Callback;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window$Callback;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class e {
    public interface a {
        boolean a(KeyEvent arg1);
    }

    private static boolean a = false;
    private static Method b = null;
    private static boolean c = false;
    private static Field d;

    static {
    }

    private static DialogInterface$OnKeyListener a(Dialog arg3) {
        if(!e.c) {
            try {
                e.d = Dialog.class.getDeclaredField("mOnKeyListener");
                e.d.setAccessible(true);
                goto label_9;
            }
            catch(NoSuchFieldException ) {
            label_9:
                e.c = true;
            }
        }

        if(e.d == null) {
            return null;
        }

        try {
            return e.d.get(arg3);
        }
        catch(IllegalAccessException ) {
            return null;
        }
    }

    private static boolean a(ActionBar arg6, KeyEvent arg7) {
        if(!e.a) {
            try {
                e.b = arg6.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                goto label_11;
            }
            catch(NoSuchMethodException ) {
            label_11:
                e.a = true;
            }
        }

        if(e.b == null) {
            return 0;
        }

        try {
            return e.b.invoke(arg6, arg7).booleanValue();
        }
        catch(InvocationTargetException ) {
            return 0;
        }
    }

    private static boolean a(Activity arg5, KeyEvent arg6) {
        arg5.onUserInteraction();
        Window v0 = arg5.getWindow();
        if(v0.hasFeature(8)) {
            ActionBar v1 = arg5.getActionBar();
            if(arg6.getKeyCode() == 82 && v1 != null && (e.a(v1, arg6))) {
                return 1;
            }
        }

        if(v0.superDispatchKeyEvent(arg6)) {
            return 1;
        }

        View v0_1 = v0.getDecorView();
        if(t.b(v0_1, arg6)) {
            return 1;
        }

        KeyEvent$DispatcherState v0_2 = v0_1 != null ? v0_1.getKeyDispatcherState() : null;
        return arg6.dispatch(((KeyEvent$Callback)arg5), v0_2, arg5);
    }

    private static boolean a(Dialog arg3, KeyEvent arg4) {
        DialogInterface$OnKeyListener v0 = e.a(arg3);
        if(v0 != null && (v0.onKey(((DialogInterface)arg3), arg4.getKeyCode(), arg4))) {
            return 1;
        }

        Window v0_1 = arg3.getWindow();
        if(v0_1.superDispatchKeyEvent(arg4)) {
            return 1;
        }

        View v0_2 = v0_1.getDecorView();
        if(t.b(v0_2, arg4)) {
            return 1;
        }

        KeyEvent$DispatcherState v0_3 = v0_2 != null ? v0_2.getKeyDispatcherState() : null;
        return arg4.dispatch(((KeyEvent$Callback)arg3), v0_3, arg3);
    }

    public static boolean a(a arg3, View arg4, Window$Callback arg5, KeyEvent arg6) {
        boolean v0 = false;
        if(arg3 == null) {
            return 0;
        }

        if(Build$VERSION.SDK_INT >= 28) {
            return arg3.a(arg6);
        }

        if((arg5 instanceof Activity)) {
            return e.a(((Activity)arg5), arg6);
        }

        if((arg5 instanceof Dialog)) {
            return e.a(((Dialog)arg5), arg6);
        }

        if(arg4 != null && (t.b(arg4, arg6)) || (arg3.a(arg6))) {
            v0 = true;
        }

        return v0;
    }

    public static boolean a(View arg0, KeyEvent arg1) {
        return t.a(arg0, arg1);
    }
}

