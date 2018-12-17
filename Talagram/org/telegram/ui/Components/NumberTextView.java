package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import java.util.ArrayList;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;

public class NumberTextView extends View {
    private ObjectAnimator animator;
    private int currentNumber;
    private ArrayList letters;
    private ArrayList oldLetters;
    private float progress;
    private TextPaint textPaint;

    public NumberTextView(Context arg2) {
        super(arg2);
        this.letters = new ArrayList();
        this.oldLetters = new ArrayList();
        this.textPaint = new TextPaint(1);
        this.progress = 0f;
        this.currentNumber = 1;
    }

    static ObjectAnimator access$002(NumberTextView arg0, ObjectAnimator arg1) {
        arg0.animator = arg1;
        return arg1;
    }

    static ArrayList access$100(NumberTextView arg0) {
        return arg0.oldLetters;
    }

    public float getProgress() {
        return this.progress;
    }

    protected void onDraw(Canvas arg13) {
        // Method was not decompiled
    }

    public void setNumber(int arg20, boolean arg21) {
        NumberTextView v0 = this;
        int v1 = arg20;
        if(v0.currentNumber == v1 && (arg21)) {
            return;
        }

        ObjectAnimator v4 = null;
        if(v0.animator != null) {
            v0.animator.cancel();
            v0.animator = v4;
        }

        v0.oldLetters.clear();
        v0.oldLetters.addAll(v0.letters);
        v0.letters.clear();
        String v3 = String.format(Locale.US, "%d", Integer.valueOf(v0.currentNumber));
        String v5 = String.format(Locale.US, "%d", Integer.valueOf(arg20));
        int v7 = v1 > v0.currentNumber ? 1 : 0;
        v0.currentNumber = v1;
        v0.progress = 0f;
        int v8;
        for(v8 = 0; v8 < v5.length(); v8 = v10) {
            int v10 = v8 + 1;
            String v12 = v5.substring(v8, v10);
            String v11 = (v0.oldLetters.isEmpty()) || v8 >= v3.length() ? ((String)v4) : v3.substring(v8, v10);
            if(v11 == null || !v11.equals(v12)) {
                v0.letters.add(new StaticLayout(((CharSequence)v12), v0.textPaint, ((int)Math.ceil(((double)v0.textPaint.measureText(v12)))), Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false));
            }
            else {
                v0.letters.add(v0.oldLetters.get(v8));
                v0.oldLetters.set(v8, v4);
            }
        }

        if((arg21) && !v0.oldLetters.isEmpty()) {
            String v2 = "progress";
            float[] v3_1 = new float[2];
            float v4_1 = v7 != 0 ? -1f : 1f;
            v3_1[0] = v4_1;
            v3_1[1] = 0f;
            v0.animator = ObjectAnimator.ofFloat(v0, v2, v3_1);
            v0.animator.setDuration(150);
            v0.animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    NumberTextView.this.animator = null;
                    NumberTextView.this.oldLetters.clear();
                }
            });
            v0.animator.start();
        }

        this.invalidate();
    }

    public void setProgress(float arg2) {
        if(this.progress == arg2) {
            return;
        }

        this.progress = arg2;
        this.invalidate();
    }

    public void setTextColor(int arg2) {
        this.textPaint.setColor(arg2);
        this.invalidate();
    }

    public void setTextSize(int arg2) {
        this.textPaint.setTextSize(((float)AndroidUtilities.dp(((float)arg2))));
        this.oldLetters.clear();
        this.letters.clear();
        this.setNumber(this.currentNumber, false);
    }

    public void setTypeface(Typeface arg2) {
        this.textPaint.setTypeface(arg2);
        this.oldLetters.clear();
        this.letters.clear();
        this.setNumber(this.currentNumber, false);
    }
}

