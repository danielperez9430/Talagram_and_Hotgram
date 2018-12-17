package android.support.design.theme;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.design.b.a;
import android.support.v7.app.AppCompatViewInflater;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

@Keep public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    public MaterialComponentsViewInflater() {
        super();
    }

    protected AppCompatButton createButton(Context arg2, AttributeSet arg3) {
        return new a(arg2, arg3);
    }
}

