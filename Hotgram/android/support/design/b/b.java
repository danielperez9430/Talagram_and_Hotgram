package android.support.design.b;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;

@TargetApi(value=21) class b extends RippleDrawable {
    b(ColorStateList arg1, InsetDrawable arg2, Drawable arg3) {
        super(arg1, ((Drawable)arg2), arg3);
    }

    public void setColorFilter(ColorFilter arg3) {
        if(this.getDrawable(0) != null) {
            this.getDrawable(0).getDrawable().getDrawable(0).setColorFilter(arg3);
        }
    }
}

