package android.support.design.widget;

import android.support.v7.widget.l;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class s extends l {
    public CharSequence getHint() {
        TextInputLayout v0 = this.getTextInputLayout();
        if(v0 != null && (v0.a())) {
            return v0.getHint();
        }

        return super.getHint();
    }

    private CharSequence getHintFromLayout() {
        TextInputLayout v0 = this.getTextInputLayout();
        CharSequence v0_1 = v0 != null ? v0.getHint() : null;
        return v0_1;
    }

    private TextInputLayout getTextInputLayout() {
        ViewParent v0;
        for(v0 = this.getParent(); (v0 instanceof View); v0 = v0.getParent()) {
            if((v0 instanceof TextInputLayout)) {
                return ((TextInputLayout)v0);
            }
        }

        return null;
    }

    public InputConnection onCreateInputConnection(EditorInfo arg3) {
        InputConnection v0 = super.onCreateInputConnection(arg3);
        if(v0 != null && arg3.hintText == null) {
            arg3.hintText = this.getHintFromLayout();
        }

        return v0;
    }
}

