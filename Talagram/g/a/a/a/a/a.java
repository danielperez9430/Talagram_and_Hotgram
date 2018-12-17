package g.a.a.a.a;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

class a extends CharacterStyle {
    private final float a;

    a(float arg1) {
        super();
        this.a = arg1;
    }

    public void updateDrawState(TextPaint arg5) {
        arg5.setAlpha(((int)((((float)arg5.getAlpha())) * this.a)));
        arg5.bgColor = Color.argb(((int)((((float)Color.alpha(arg5.bgColor))) * this.a)), Color.red(arg5.bgColor), Color.green(arg5.bgColor), Color.blue(arg5.bgColor));
    }
}

