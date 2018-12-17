package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.s;
import android.support.v4.widget.b;
import android.support.v4.widget.q;
import android.support.v7.a.a$a;
import android.util.AttributeSet;
import android.view.ActionMode$Callback;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;

public class AppCompatButton extends Button implements s, b {
    private final g b;
    private final w c;

    public AppCompatButton(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.buttonStyle);
    }

    public AppCompatButton(Context arg2) {
        this(arg2, null);
    }

    public AppCompatButton(Context arg1, AttributeSet arg2, int arg3) {
        super(bh.a(arg1), arg2, arg3);
        this.b = new g(((View)this));
        this.b.a(arg2, arg3);
        this.c = new w(((TextView)this));
        this.c.a(arg2, arg3);
        this.c.a();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.b != null) {
            this.b.c();
        }

        if(this.c != null) {
            this.c.a();
        }
    }

    public int getAutoSizeMaxTextSize() {
        if(AppCompatButton.a) {
            return super.getAutoSizeMaxTextSize();
        }

        if(this.c != null) {
            return this.c.g();
        }

        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if(AppCompatButton.a) {
            return super.getAutoSizeMinTextSize();
        }

        if(this.c != null) {
            return this.c.f();
        }

        return -1;
    }

    public int getAutoSizeStepGranularity() {
        if(AppCompatButton.a) {
            return super.getAutoSizeStepGranularity();
        }

        if(this.c != null) {
            return this.c.e();
        }

        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if(AppCompatButton.a) {
            return super.getAutoSizeTextAvailableSizes();
        }

        if(this.c != null) {
            return this.c.h();
        }

        return new int[0];
    }

    public int getAutoSizeTextType() {
        int v1 = 0;
        if(AppCompatButton.a) {
            if(super.getAutoSizeTextType() == 1) {
                v1 = 1;
            }

            return v1;
        }

        if(this.c != null) {
            return this.c.d();
        }

        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.b != null ? this.b.a() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.b != null ? this.b.b() : null;
        return v0;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent arg2) {
        super.onInitializeAccessibilityEvent(arg2);
        arg2.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg2) {
        super.onInitializeAccessibilityNodeInfo(arg2);
        arg2.setClassName(Button.class.getName());
    }

    protected void onLayout(boolean arg8, int arg9, int arg10, int arg11, int arg12) {
        super.onLayout(arg8, arg9, arg10, arg11, arg12);
        if(this.c != null) {
            this.c.a(arg8, arg9, arg10, arg11, arg12);
        }
    }

    protected void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        super.onTextChanged(arg1, arg2, arg3, arg4);
        if(this.c != null && !AppCompatButton.a && (this.c.c())) {
            this.c.b();
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int arg2, int arg3, int arg4, int arg5) {
        if(AppCompatButton.a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(arg2, arg3, arg4, arg5);
        }
        else if(this.c != null) {
            this.c.a(arg2, arg3, arg4, arg5);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] arg2, int arg3) {
        if(AppCompatButton.a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(arg2, arg3);
        }
        else if(this.c != null) {
            this.c.a(arg2, arg3);
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int arg2) {
        if(AppCompatButton.a) {
            super.setAutoSizeTextTypeWithDefaults(arg2);
        }
        else if(this.c != null) {
            this.c.a(arg2);
        }
    }

    public void setBackgroundDrawable(Drawable arg2) {
        super.setBackgroundDrawable(arg2);
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setBackgroundResource(int arg2) {
        super.setBackgroundResource(arg2);
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode$Callback arg1) {
        super.setCustomSelectionActionModeCallback(q.a(((TextView)this), arg1));
    }

    public void setSupportAllCaps(boolean arg2) {
        if(this.c != null) {
            this.c.a(arg2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList arg2) {
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setTextAppearance(Context arg2, int arg3) {
        super.setTextAppearance(arg2, arg3);
        if(this.c != null) {
            this.c.a(arg2, arg3);
        }
    }

    public void setTextSize(int arg2, float arg3) {
        if(AppCompatButton.a) {
            super.setTextSize(arg2, arg3);
        }
        else if(this.c != null) {
            this.c.a(arg2, arg3);
        }
    }
}

