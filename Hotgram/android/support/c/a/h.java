package android.support.c.a;

import android.content.res.Resources$Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.a;
import android.support.v4.graphics.drawable.b;

abstract class h extends Drawable implements b {
    Drawable c;

    h() {
        super();
    }

    public void applyTheme(Resources$Theme arg2) {
        if(this.c != null) {
            a.a(this.c, arg2);
        }
    }

    public void clearColorFilter() {
        if(this.c != null) {
            this.c.clearColorFilter();
            return;
        }

        super.clearColorFilter();
    }

    public ColorFilter getColorFilter() {
        if(this.c != null) {
            return a.e(this.c);
        }

        return null;
    }

    public Drawable getCurrent() {
        if(this.c != null) {
            return this.c.getCurrent();
        }

        return super.getCurrent();
    }

    public int getMinimumHeight() {
        if(this.c != null) {
            return this.c.getMinimumHeight();
        }

        return super.getMinimumHeight();
    }

    public int getMinimumWidth() {
        if(this.c != null) {
            return this.c.getMinimumWidth();
        }

        return super.getMinimumWidth();
    }

    public boolean getPadding(Rect arg2) {
        if(this.c != null) {
            return this.c.getPadding(arg2);
        }

        return super.getPadding(arg2);
    }

    public int[] getState() {
        if(this.c != null) {
            return this.c.getState();
        }

        return super.getState();
    }

    public Region getTransparentRegion() {
        if(this.c != null) {
            return this.c.getTransparentRegion();
        }

        return super.getTransparentRegion();
    }

    public void jumpToCurrentState() {
        if(this.c != null) {
            a.a(this.c);
        }
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.c != null) {
            this.c.setBounds(arg2);
            return;
        }

        super.onBoundsChange(arg2);
    }

    protected boolean onLevelChange(int arg2) {
        if(this.c != null) {
            return this.c.setLevel(arg2);
        }

        return super.onLevelChange(arg2);
    }

    public void setChangingConfigurations(int arg2) {
        if(this.c != null) {
            this.c.setChangingConfigurations(arg2);
            return;
        }

        super.setChangingConfigurations(arg2);
    }

    public void setColorFilter(int arg2, PorterDuff$Mode arg3) {
        if(this.c != null) {
            this.c.setColorFilter(arg2, arg3);
            return;
        }

        super.setColorFilter(arg2, arg3);
    }

    public void setFilterBitmap(boolean arg2) {
        if(this.c != null) {
            this.c.setFilterBitmap(arg2);
        }
    }

    public void setHotspot(float arg2, float arg3) {
        if(this.c != null) {
            a.a(this.c, arg2, arg3);
        }
    }

    public void setHotspotBounds(int arg2, int arg3, int arg4, int arg5) {
        if(this.c != null) {
            a.a(this.c, arg2, arg3, arg4, arg5);
        }
    }

    public boolean setState(int[] arg2) {
        if(this.c != null) {
            return this.c.setState(arg2);
        }

        return super.setState(arg2);
    }
}

