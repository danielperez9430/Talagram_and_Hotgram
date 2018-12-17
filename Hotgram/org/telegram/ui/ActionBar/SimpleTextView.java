package org.telegram.ui.ActionBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.text.Layout$Alignment;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.view.View$MeasureSpec;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

public class SimpleTextView extends View implements Drawable$Callback {
    private int drawablePadding;
    private int gravity;
    private Layout layout;
    private Drawable leftDrawable;
    private int leftDrawableTopPadding;
    private int offsetX;
    private Drawable rightDrawable;
    private int rightDrawableTopPadding;
    private SpannableStringBuilder spannableStringBuilder;
    private CharSequence text;
    private int textHeight;
    private TextPaint textPaint;
    private int textWidth;
    private boolean wasLayout;

    public SimpleTextView(Context arg2) {
        super(arg2);
        this.gravity = 51;
        this.drawablePadding = AndroidUtilities.dp(4f);
        this.textPaint = new TextPaint(1);
    }

    private void calcOffset(int arg5) {
        if(this.layout.getLineCount() > 0) {
            this.textWidth = ((int)Math.ceil(((double)this.layout.getLineWidth(0))));
            this.textHeight = this.layout.getLineBottom(0);
            if((this.gravity & 7) == 3) {
                arg5 = ((int)this.layout.getLineLeft(0));
                goto label_20;
            }
            else if(this.layout.getLineLeft(0) == 0f) {
                arg5 -= this.textWidth;
            }
            else {
                arg5 = AndroidUtilities.dp(8f);
            label_20:
                arg5 = -arg5;
            }

            this.offsetX = arg5;
            this.offsetX += this.getPaddingLeft();
        }
    }

    private boolean createLayout(int arg15) {
        if(this.text != null) {
            try {
                if(this.leftDrawable != null) {
                    arg15 = arg15 - this.leftDrawable.getIntrinsicWidth() - this.drawablePadding;
                }

                if(this.rightDrawable != null) {
                    arg15 = arg15 - this.rightDrawable.getIntrinsicWidth() - this.drawablePadding;
                }

                CharSequence v5 = TextUtils.ellipsize(this.text, this.textPaint, ((float)arg15), TextUtils$TruncateAt.END);
                this.layout = new StaticLayout(v5, 0, v5.length(), this.textPaint, arg15 + AndroidUtilities.dp(8f), Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
                this.calcOffset(arg15);
            }
            catch(Exception ) {
            }
        }
        else {
            this.layout = null;
            this.textWidth = 0;
            this.textHeight = 0;
        }

        this.invalidate();
        return 1;
    }

    public Paint getPaint() {
        return this.textPaint;
    }

    public int getSideDrawablesSize() {
        int v1 = 0;
        if(this.leftDrawable != null) {
            v1 = this.leftDrawable.getIntrinsicWidth() + this.drawablePadding;
        }

        if(this.rightDrawable != null) {
            v1 += this.rightDrawable.getIntrinsicWidth() + this.drawablePadding;
        }

        return v1;
    }

    public CharSequence getText() {
        if(this.text == null) {
            return "";
        }

        return this.text;
    }

    public int getTextHeight() {
        return this.textHeight;
    }

    public TextPaint getTextPaint() {
        return this.textPaint;
    }

    public int getTextStartX() {
        int v1 = 0;
        if(this.layout == null) {
            return 0;
        }

        if(this.leftDrawable != null && (this.gravity & 7) == 3) {
            v1 = this.drawablePadding + this.leftDrawable.getIntrinsicWidth();
        }

        return (((int)this.getX())) + this.offsetX + v1;
    }

    public int getTextStartY() {
        if(this.layout == null) {
            return 0;
        }

        return ((int)this.getY());
    }

    public int getTextWidth() {
        return this.textWidth;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public void invalidateDrawable(Drawable arg2) {
        if(arg2 == this.leftDrawable) {
            arg2 = this.leftDrawable;
            goto label_3;
        }
        else if(arg2 == this.rightDrawable) {
            arg2 = this.rightDrawable;
        label_3:
            this.invalidate(arg2.getBounds());
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.wasLayout = false;
    }

    protected void onDraw(Canvas arg7) {
        int v0;
        int v1 = 0;
        if(this.leftDrawable != null) {
            v0 = (this.textHeight - this.leftDrawable.getIntrinsicHeight()) / 2 + this.leftDrawableTopPadding;
            this.leftDrawable.setBounds(0, v0, this.leftDrawable.getIntrinsicWidth(), this.leftDrawable.getIntrinsicHeight() + v0);
            this.leftDrawable.draw(arg7);
            if((this.gravity & 7) == 3) {
                v1 = this.drawablePadding + this.leftDrawable.getIntrinsicWidth();
            }
        }

        if(this.rightDrawable != null) {
            v0 = this.textWidth + v1 + this.drawablePadding;
            int v2 = (this.textHeight - this.rightDrawable.getIntrinsicHeight()) / 2 + this.rightDrawableTopPadding;
            this.rightDrawable.setBounds(v0, v2, this.rightDrawable.getIntrinsicWidth() + v0, this.rightDrawable.getIntrinsicHeight() + v2);
            this.rightDrawable.draw(arg7);
        }

        if(this.layout != null) {
            if(this.offsetX + v1 != 0) {
                arg7.save();
                arg7.translate(((float)(this.offsetX + v1)), 0f);
            }

            this.layout.draw(arg7);
            if(this.offsetX + v1 == 0) {
                return;
            }

            arg7.restore();
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        this.wasLayout = true;
    }

    protected void onMeasure(int arg4, int arg5) {
        arg4 = View$MeasureSpec.getSize(arg4);
        int v0 = View$MeasureSpec.getSize(arg5);
        this.createLayout(arg4 - this.getPaddingLeft() - this.getPaddingRight());
        if(View$MeasureSpec.getMode(arg5) == 1073741824) {
        }
        else {
            v0 = this.textHeight;
        }

        this.setMeasuredDimension(arg4, v0);
    }

    private boolean recreateLayoutMaybe() {
        if(this.wasLayout) {
            return this.createLayout(this.getMeasuredWidth());
        }

        this.requestLayout();
        return 1;
    }

    public void setDrawablePadding(int arg2) {
        if(this.drawablePadding == arg2) {
            return;
        }

        this.drawablePadding = arg2;
        if(!this.recreateLayoutMaybe()) {
            this.invalidate();
        }
    }

    public void setGravity(int arg1) {
        this.gravity = arg1;
    }

    public void setLeftDrawable(int arg2) {
        Drawable v2 = arg2 == 0 ? null : this.getContext().getResources().getDrawable(arg2);
        this.setLeftDrawable(v2);
    }

    public void setLeftDrawable(Drawable arg3) {
        if(this.leftDrawable == arg3) {
            return;
        }

        if(this.leftDrawable != null) {
            this.leftDrawable.setCallback(null);
        }

        this.leftDrawable = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
        }

        if(!this.recreateLayoutMaybe()) {
            this.invalidate();
        }
    }

    public void setLeftDrawableTopPadding(int arg1) {
        this.leftDrawableTopPadding = arg1;
    }

    public void setLinkTextColor(int arg2) {
        this.textPaint.linkColor = arg2;
        this.invalidate();
    }

    public void setRightDrawable(int arg2) {
        Drawable v2 = arg2 == 0 ? null : this.getContext().getResources().getDrawable(arg2);
        this.setRightDrawable(v2);
    }

    public void setRightDrawable(Drawable arg3) {
        if(this.rightDrawable == arg3) {
            return;
        }

        if(this.rightDrawable != null) {
            this.rightDrawable.setCallback(null);
        }

        this.rightDrawable = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
        }

        if(!this.recreateLayoutMaybe()) {
            this.invalidate();
        }
    }

    public void setRightDrawableTopPadding(int arg1) {
        this.rightDrawableTopPadding = arg1;
    }

    public void setText(CharSequence arg2) {
        this.setText(arg2, false);
    }

    public void setText(CharSequence arg2, boolean arg3) {
        if(this.text == null && arg2 == null || !arg3 && this.text != null && arg2 != null && (this.text.equals(arg2))) {
            return;
        }

        this.text = arg2;
        this.recreateLayoutMaybe();
    }

    public void setTextColor(int arg2) {
        this.textPaint.setColor(arg2);
        this.invalidate();
    }

    public void setTextSize(int arg2) {
        float v2 = ((float)AndroidUtilities.dp(((float)arg2)));
        if(v2 == this.textPaint.getTextSize()) {
            return;
        }

        this.textPaint.setTextSize(v2);
        if(!this.recreateLayoutMaybe()) {
            this.invalidate();
        }
    }

    public void setTypeface(Typeface arg2) {
        this.textPaint.setTypeface(AndroidUtilities.getTypeface(""));
    }
}

