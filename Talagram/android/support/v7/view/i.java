package android.support.v7.view;

import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window$Callback;
import android.view.WindowManager$LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class i implements Window$Callback {
    final Window$Callback b;

    public i(Window$Callback arg2) {
        super();
        if(arg2 != null) {
            this.b = arg2;
            return;
        }

        throw new IllegalArgumentException("Window callback may not be null");
    }

    public boolean dispatchGenericMotionEvent(MotionEvent arg2) {
        return this.b.dispatchGenericMotionEvent(arg2);
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        return this.b.dispatchKeyEvent(arg2);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent arg2) {
        return this.b.dispatchKeyShortcutEvent(arg2);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg2) {
        return this.b.dispatchPopulateAccessibilityEvent(arg2);
    }

    public boolean dispatchTouchEvent(MotionEvent arg2) {
        return this.b.dispatchTouchEvent(arg2);
    }

    public boolean dispatchTrackballEvent(MotionEvent arg2) {
        return this.b.dispatchTrackballEvent(arg2);
    }

    public void onActionModeFinished(ActionMode arg2) {
        this.b.onActionModeFinished(arg2);
    }

    public void onActionModeStarted(ActionMode arg2) {
        this.b.onActionModeStarted(arg2);
    }

    public void onAttachedToWindow() {
        this.b.onAttachedToWindow();
    }

    public void onContentChanged() {
        this.b.onContentChanged();
    }

    public boolean onCreatePanelMenu(int arg2, Menu arg3) {
        return this.b.onCreatePanelMenu(arg2, arg3);
    }

    public View onCreatePanelView(int arg2) {
        return this.b.onCreatePanelView(arg2);
    }

    public void onDetachedFromWindow() {
        this.b.onDetachedFromWindow();
    }

    public boolean onMenuItemSelected(int arg2, MenuItem arg3) {
        return this.b.onMenuItemSelected(arg2, arg3);
    }

    public boolean onMenuOpened(int arg2, Menu arg3) {
        return this.b.onMenuOpened(arg2, arg3);
    }

    public void onPanelClosed(int arg2, Menu arg3) {
        this.b.onPanelClosed(arg2, arg3);
    }

    public void onPointerCaptureChanged(boolean arg2) {
        this.b.onPointerCaptureChanged(arg2);
    }

    public boolean onPreparePanel(int arg2, View arg3, Menu arg4) {
        return this.b.onPreparePanel(arg2, arg3, arg4);
    }

    public void onProvideKeyboardShortcuts(List arg2, Menu arg3, int arg4) {
        this.b.onProvideKeyboardShortcuts(arg2, arg3, arg4);
    }

    public boolean onSearchRequested() {
        return this.b.onSearchRequested();
    }

    public boolean onSearchRequested(SearchEvent arg2) {
        return this.b.onSearchRequested(arg2);
    }

    public void onWindowAttributesChanged(WindowManager$LayoutParams arg2) {
        this.b.onWindowAttributesChanged(arg2);
    }

    public void onWindowFocusChanged(boolean arg2) {
        this.b.onWindowFocusChanged(arg2);
    }

    public ActionMode onWindowStartingActionMode(ActionMode$Callback arg2) {
        return this.b.onWindowStartingActionMode(arg2);
    }

    public ActionMode onWindowStartingActionMode(ActionMode$Callback arg2, int arg3) {
        return this.b.onWindowStartingActionMode(arg2, arg3);
    }
}

