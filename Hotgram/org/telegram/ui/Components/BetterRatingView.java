package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class BetterRatingView extends View {
    public interface OnRatingChangeListener {
        void onRatingChanged(int arg1);
    }

    private Bitmap filledStar;
    private Bitmap hollowStar;
    private OnRatingChangeListener listener;
    private int numStars;
    private Paint paint;
    private int selectedRating;

    public BetterRatingView(Context arg2) {
        super(arg2);
        this.paint = new Paint();
        this.numStars = 5;
        this.selectedRating = 0;
        this.filledStar = BitmapFactory.decodeResource(this.getResources(), 2131231228).extractAlpha();
        this.hollowStar = BitmapFactory.decodeResource(this.getResources(), 2131231227).extractAlpha();
    }

    public int getRating() {
        return this.selectedRating;
    }

    protected void onDraw(Canvas arg6) {
        int v0;
        for(v0 = 0; v0 < this.numStars; ++v0) {
            Paint v1 = this.paint;
            String v2 = v0 < this.selectedRating ? "calls_ratingStarSelected" : "calls_ratingStar";
            v1.setColor(Theme.getColor(v2));
            Bitmap v1_1 = v0 < this.selectedRating ? this.filledStar : this.hollowStar;
            arg6.drawBitmap(v1_1, ((float)(AndroidUtilities.dp(48f) * v0)), 0f, this.paint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        this.setMeasuredDimension(this.numStars * AndroidUtilities.dp(32f) + (this.numStars - 1) * AndroidUtilities.dp(16f), AndroidUtilities.dp(32f));
    }

    public boolean onTouchEvent(MotionEvent arg6) {
        // Method was not decompiled
    }

    public void setOnRatingChangeListener(OnRatingChangeListener arg1) {
        this.listener = arg1;
    }
}

