package android.support.v7.widget;

import android.content.Context;
import android.support.v4.widget.q;
import android.support.v7.c.a.a;
import android.util.AttributeSet;
import android.view.ActionMode$Callback;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class i extends CheckedTextView {
    private static final int[] a;
    private final w b;

    static {
        i.a = new int[]{16843016};
    }

    public i(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 16843720);
    }

    public i(Context arg3, AttributeSet arg4, int arg5) {
        super(bh.a(arg3), arg4, arg5);
        this.b = new w(((TextView)this));
        this.b.a(arg4, arg5);
        this.b.a();
        bk v3 = bk.a(this.getContext(), arg4, i.a, arg5, 0);
        this.setCheckMarkDrawable(v3.a(0));
        v3.a();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.b != null) {
            this.b.a();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return m.a(super.onCreateInputConnection(arg2), arg2, ((View)this));
    }

    public void setCheckMarkDrawable(int arg2) {
        this.setCheckMarkDrawable(a.b(this.getContext(), arg2));
    }

    public void setCustomSelectionActionModeCallback(ActionMode$Callback arg1) {
        super.setCustomSelectionActionModeCallback(q.a(((TextView)this), arg1));
    }

    public void setTextAppearance(Context arg2, int arg3) {
        super.setTextAppearance(arg2, arg3);
        if(this.b != null) {
            this.b.a(arg2, arg3);
        }
    }
}

