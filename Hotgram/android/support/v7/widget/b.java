package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

class b extends Drawable {
    final ActionBarContainer a;

    public b(ActionBarContainer arg1) {
        super();
        this.a = arg1;
    }

    public void draw(Canvas arg2) {
        Drawable v0;
        if(!this.a.d) {
            if(this.a.a != null) {
                this.a.a.draw(arg2);
            }

            if(this.a.b == null) {
                return;
            }

            if(!this.a.e) {
                return;
            }

            v0 = this.a.b;
        label_8:
            v0.draw(arg2);
        }
        else if(this.a.c != null) {
            v0 = this.a.c;
            goto label_8;
        }
    }

    public int getOpacity() {
        return 0;
    }

    public void getOutline(Outline arg2) {
        Drawable v0;
        if(this.a.d) {
            if(this.a.c != null) {
                v0 = this.a.c;
                goto label_8;
            }
        }
        else if(this.a.a != null) {
            v0 = this.a.a;
        label_8:
            v0.getOutline(arg2);
        }
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg1) {
    }
}

