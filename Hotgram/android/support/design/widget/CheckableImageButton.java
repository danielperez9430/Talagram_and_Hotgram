package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.a.c;
import android.support.v4.view.t;
import android.support.v7.a.a$a;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;

public class CheckableImageButton extends AppCompatImageButton implements Checkable {
    private static final int[] a;
    private boolean b;

    static {
        CheckableImageButton.a = new int[]{16842912};
    }

    public CheckableImageButton(Context arg2) {
        this(arg2, null);
    }

    public CheckableImageButton(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.imageButtonStyle);
    }

    public CheckableImageButton(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        t.a(((View)this), new android.support.v4.view.a() {
            public void onInitializeAccessibilityEvent(View arg1, AccessibilityEvent arg2) {
                super.onInitializeAccessibilityEvent(arg1, arg2);
                arg2.setChecked(this.a.isChecked());
            }

            public void onInitializeAccessibilityNodeInfo(View arg1, c arg2) {
                super.onInitializeAccessibilityNodeInfo(arg1, arg2);
                arg2.a(true);
                arg2.b(this.a.isChecked());
            }
        });
    }

    public boolean isChecked() {
        return this.b;
    }

    public int[] onCreateDrawableState(int arg2) {
        if(this.b) {
            return CheckableImageButton.mergeDrawableStates(super.onCreateDrawableState(arg2 + CheckableImageButton.a.length), CheckableImageButton.a);
        }

        return super.onCreateDrawableState(arg2);
    }

    public void setChecked(boolean arg2) {
        if(this.b != arg2) {
            this.b = arg2;
            this.refreshDrawableState();
            this.sendAccessibilityEvent(2048);
        }
    }

    public void toggle() {
        this.setChecked(this.b ^ 1);
    }
}

