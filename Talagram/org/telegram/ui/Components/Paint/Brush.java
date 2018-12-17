package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import org.telegram.messenger.ApplicationLoader;

public interface Brush {
    public class Elliptical implements Brush {
        public Elliptical() {
            super();
        }

        public float getAlpha() {
            return 0.3f;
        }

        public float getAngle() {
            return ((float)Math.toRadians(125));
        }

        public float getScale() {
            return 1.5f;
        }

        public float getSpacing() {
            return 0.04f;
        }

        public Bitmap getStamp() {
            BitmapFactory$Options v0 = new BitmapFactory$Options();
            v0.inScaled = false;
            return BitmapFactory.decodeResource(ApplicationLoader.applicationContext.getResources(), 2131231445, v0);
        }

        public boolean isLightSaber() {
            return 0;
        }
    }

    public class Neon implements Brush {
        public Neon() {
            super();
        }

        public float getAlpha() {
            return 0.7f;
        }

        public float getAngle() {
            return 0;
        }

        public float getScale() {
            return 1.45f;
        }

        public float getSpacing() {
            return 0.07f;
        }

        public Bitmap getStamp() {
            BitmapFactory$Options v0 = new BitmapFactory$Options();
            v0.inScaled = false;
            return BitmapFactory.decodeResource(ApplicationLoader.applicationContext.getResources(), 2131231447, v0);
        }

        public boolean isLightSaber() {
            return 1;
        }
    }

    public class Radial implements Brush {
        public Radial() {
            super();
        }

        public float getAlpha() {
            return 0.85f;
        }

        public float getAngle() {
            return 0;
        }

        public float getScale() {
            return 1f;
        }

        public float getSpacing() {
            return 0.15f;
        }

        public Bitmap getStamp() {
            BitmapFactory$Options v0 = new BitmapFactory$Options();
            v0.inScaled = false;
            return BitmapFactory.decodeResource(ApplicationLoader.applicationContext.getResources(), 2131231449, v0);
        }

        public boolean isLightSaber() {
            return 0;
        }
    }

    float getAlpha();

    float getAngle();

    float getScale();

    float getSpacing();

    Bitmap getStamp();

    boolean isLightSaber();
}

