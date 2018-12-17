package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.view.s;
import android.support.v4.widget.q;
import android.support.v7.a.a$a;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode$Callback;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;

public class l extends EditText implements s {
    private final g a;
    private final w b;

    public l(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.editTextStyle);
    }

    public l(Context arg2) {
        this(arg2, null);
    }

    public l(Context arg1, AttributeSet arg2, int arg3) {
        super(bh.a(arg1), arg2, arg3);
        this.a = new g(((View)this));
        this.a.a(arg2, arg3);
        this.b = new w(((TextView)this));
        this.b.a(arg2, arg3);
        this.b.a();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.a != null) {
            this.a.c();
        }

        if(this.b != null) {
            this.b.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.a != null ? this.a.a() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.a != null ? this.a.b() : null;
        return v0;
    }

    public Editable getText() {
        if(Build$VERSION.SDK_INT >= 28) {
            return super.getText();
        }

        return super.getEditableText();
    }

    public CharSequence getText() {
        return this.getText();
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return m.a(super.onCreateInputConnection(arg2), arg2, ((View)this));
    }

    public void setBackgroundDrawable(Drawable arg2) {
        super.setBackgroundDrawable(arg2);
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setBackgroundResource(int arg2) {
        super.setBackgroundResource(arg2);
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode$Callback arg1) {
        super.setCustomSelectionActionModeCallback(q.a(((TextView)this), arg1));
    }

    public void setSupportBackgroundTintList(ColorStateList arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setTextAppearance(Context arg2, int arg3) {
        super.setTextAppearance(arg2, arg3);
        if(this.b != null) {
            this.b.a(arg2, arg3);
        }
    }
}

