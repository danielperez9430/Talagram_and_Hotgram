package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.e.c$a;
import android.support.v4.e.c;
import android.support.v4.view.s;
import android.support.v4.widget.b;
import android.support.v4.widget.q;
import android.util.AttributeSet;
import android.view.ActionMode$Callback;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class x extends TextView implements s, b {
    private final g b;
    private final w c;
    private Future d;

    public x(Context arg2) {
        this(arg2, null);
    }

    public x(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 16842884);
    }

    public x(Context arg1, AttributeSet arg2, int arg3) {
        super(bh.a(arg1), arg2, arg3);
        this.b = new g(((View)this));
        this.b.a(arg2, arg3);
        this.c = new w(((TextView)this));
        this.c.a(arg2, arg3);
        this.c.a();
    }

    private void a() {
        if(this.d != null) {
            try {
                Future v0 = this.d;
                this.d = null;
                q.a(((TextView)this), v0.get());
                return;
            }
            catch(ExecutionException ) {
                return;
            }
        }
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
        if(x.a) {
            return super.getAutoSizeMaxTextSize();
        }

        if(this.c != null) {
            return this.c.g();
        }

        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if(x.a) {
            return super.getAutoSizeMinTextSize();
        }

        if(this.c != null) {
            return this.c.f();
        }

        return -1;
    }

    public int getAutoSizeStepGranularity() {
        if(x.a) {
            return super.getAutoSizeStepGranularity();
        }

        if(this.c != null) {
            return this.c.e();
        }

        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if(x.a) {
            return super.getAutoSizeTextAvailableSizes();
        }

        if(this.c != null) {
            return this.c.h();
        }

        return new int[0];
    }

    public int getAutoSizeTextType() {
        int v1 = 0;
        if(x.a) {
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

    public int getFirstBaselineToTopHeight() {
        return q.c(((TextView)this));
    }

    public int getLastBaselineToBottomHeight() {
        return q.d(((TextView)this));
    }

    public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.b != null ? this.b.a() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.b != null ? this.b.b() : null;
        return v0;
    }

    public CharSequence getText() {
        this.a();
        return super.getText();
    }

    public a getTextMetricsParamsCompat() {
        return q.e(((TextView)this));
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return m.a(super.onCreateInputConnection(arg2), arg2, ((View)this));
    }

    protected void onLayout(boolean arg8, int arg9, int arg10, int arg11, int arg12) {
        super.onLayout(arg8, arg9, arg10, arg11, arg12);
        if(this.c != null) {
            this.c.a(arg8, arg9, arg10, arg11, arg12);
        }
    }

    protected void onMeasure(int arg1, int arg2) {
        this.a();
        super.onMeasure(arg1, arg2);
    }

    protected void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        super.onTextChanged(arg1, arg2, arg3, arg4);
        if(this.c != null && !x.a && (this.c.c())) {
            this.c.b();
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int arg2, int arg3, int arg4, int arg5) {
        if(x.a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(arg2, arg3, arg4, arg5);
        }
        else if(this.c != null) {
            this.c.a(arg2, arg3, arg4, arg5);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] arg2, int arg3) {
        if(x.a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(arg2, arg3);
        }
        else if(this.c != null) {
            this.c.a(arg2, arg3);
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int arg2) {
        if(x.a) {
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

    public void setFirstBaselineToTopHeight(int arg3) {
        if(Build$VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(arg3);
        }
        else {
            q.b(((TextView)this), arg3);
        }
    }

    public void setLastBaselineToBottomHeight(int arg3) {
        if(Build$VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(arg3);
        }
        else {
            q.c(((TextView)this), arg3);
        }
    }

    public void setLineHeight(int arg1) {
        q.d(((TextView)this), arg1);
    }

    public void setPrecomputedText(c arg1) {
        q.a(((TextView)this), arg1);
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

    public void setTextFuture(Future arg1) {
        this.d = arg1;
        this.requestLayout();
    }

    public void setTextMetricsParamsCompat(a arg1) {
        q.a(((TextView)this), arg1);
    }

    public void setTextSize(int arg2, float arg3) {
        if(x.a) {
            super.setTextSize(arg2, arg3);
        }
        else if(this.c != null) {
            this.c.a(arg2, arg3);
        }
    }
}

