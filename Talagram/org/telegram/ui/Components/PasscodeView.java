package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Vibrator;
import android.support.v4.os.b;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View$OnLongClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView$OnEditorActionListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.support.fingerprint.FingerprintManagerCompat$AuthenticationCallback;
import org.telegram.messenger.support.fingerprint.FingerprintManagerCompat$AuthenticationResult;
import org.telegram.messenger.support.fingerprint.FingerprintManagerCompat;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

public class PasscodeView extends FrameLayout {
    class org.telegram.ui.Components.PasscodeView$9 implements Runnable {
        org.telegram.ui.Components.PasscodeView$9(PasscodeView arg1) {
            PasscodeView.this = arg1;
            super();
        }

        public void run() {
            PasscodeView.this.checkRetryTextView();
            AndroidUtilities.runOnUIThread(PasscodeView.this.checkRunnable, 100);
        }
    }

    class AnimatingTextView extends FrameLayout {
        private String DOT;
        private ArrayList characterTextViews;
        private AnimatorSet currentAnimation;
        private Runnable dotRunnable;
        private ArrayList dotTextViews;
        private StringBuilder stringBuilder;

        public AnimatingTextView(PasscodeView arg12, Context arg13) {
            PasscodeView.this = arg12;
            super(arg13);
            this.DOT = "•";
            int v0 = 4;
            this.characterTextViews = new ArrayList(v0);
            this.dotTextViews = new ArrayList(v0);
            this.stringBuilder = new StringBuilder(v0);
            int v12;
            for(v12 = 0; v12 < v0; ++v12) {
                TextView v1 = new TextView(arg13);
                v1.setTextColor(-1);
                v1.setTextSize(1, 36f);
                v1.setGravity(17);
                v1.setAlpha(0f);
                v1.setPivotX(((float)AndroidUtilities.dp(25f)));
                v1.setPivotY(((float)AndroidUtilities.dp(25f)));
                this.addView(((View)v1));
                ViewGroup$LayoutParams v8 = v1.getLayoutParams();
                ((FrameLayout$LayoutParams)v8).width = AndroidUtilities.dp(50f);
                ((FrameLayout$LayoutParams)v8).height = AndroidUtilities.dp(50f);
                ((FrameLayout$LayoutParams)v8).gravity = 51;
                v1.setLayoutParams(v8);
                this.characterTextViews.add(v1);
                v1 = new TextView(arg13);
                v1.setTextColor(-1);
                v1.setTextSize(1, 36f);
                v1.setGravity(17);
                v1.setAlpha(0f);
                v1.setText(this.DOT);
                v1.setPivotX(((float)AndroidUtilities.dp(25f)));
                v1.setPivotY(((float)AndroidUtilities.dp(25f)));
                this.addView(((View)v1));
                ViewGroup$LayoutParams v2 = v1.getLayoutParams();
                ((FrameLayout$LayoutParams)v2).width = AndroidUtilities.dp(50f);
                ((FrameLayout$LayoutParams)v2).height = AndroidUtilities.dp(50f);
                ((FrameLayout$LayoutParams)v2).gravity = 51;
                v1.setLayoutParams(v2);
                this.dotTextViews.add(v1);
            }
        }

        static Runnable access$000(AnimatingTextView arg0) {
            return arg0.dotRunnable;
        }

        static ArrayList access$100(AnimatingTextView arg0) {
            return arg0.characterTextViews;
        }

        static ArrayList access$200(AnimatingTextView arg0) {
            return arg0.dotTextViews;
        }

        static AnimatorSet access$300(AnimatingTextView arg0) {
            return arg0.currentAnimation;
        }

        static AnimatorSet access$302(AnimatingTextView arg0, AnimatorSet arg1) {
            arg0.currentAnimation = arg1;
            return arg1;
        }

        static void access$700(AnimatingTextView arg0, boolean arg1) {
            arg0.eraseAllCharacters(arg1);
        }

        public void appendCharacter(String arg11) {
            int v1 = 4;
            if(this.stringBuilder.length() == v1) {
                return;
            }

            int v0 = 3;
            try {
                this.performHapticFeedback(v0);
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }

            ArrayList v0_2 = new ArrayList();
            int v2 = this.stringBuilder.length();
            this.stringBuilder.append(arg11);
            Object v3 = this.characterTextViews.get(v2);
            ((TextView)v3).setText(((CharSequence)arg11));
            ((TextView)v3).setTranslationX(((float)this.getXForTextView(v2)));
            v0_2.add(ObjectAnimator.ofFloat(v3, "scaleX", new float[]{0f, 1f}));
            v0_2.add(ObjectAnimator.ofFloat(v3, "scaleY", new float[]{0f, 1f}));
            v0_2.add(ObjectAnimator.ofFloat(v3, "alpha", new float[]{0f, 1f}));
            v0_2.add(ObjectAnimator.ofFloat(v3, "translationY", new float[]{((float)AndroidUtilities.dp(20f)), 0f}));
            Object v11 = this.dotTextViews.get(v2);
            ((TextView)v11).setTranslationX(((float)this.getXForTextView(v2)));
            ((TextView)v11).setAlpha(0f);
            v0_2.add(ObjectAnimator.ofFloat(v11, "scaleX", new float[]{0f, 1f}));
            v0_2.add(ObjectAnimator.ofFloat(v11, "scaleY", new float[]{0f, 1f}));
            v0_2.add(ObjectAnimator.ofFloat(v11, "translationY", new float[]{((float)AndroidUtilities.dp(20f)), 0f}));
            int v11_1;
            for(v11_1 = v2 + 1; v11_1 < v1; ++v11_1) {
                v3 = this.characterTextViews.get(v11_1);
                if(((TextView)v3).getAlpha() != 0f) {
                    v0_2.add(ObjectAnimator.ofFloat(v3, "scaleX", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v3, "scaleY", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v3, "alpha", new float[]{0f}));
                }

                v3 = this.dotTextViews.get(v11_1);
                if(((TextView)v3).getAlpha() != 0f) {
                    v0_2.add(ObjectAnimator.ofFloat(v3, "scaleX", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v3, "scaleY", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v3, "alpha", new float[]{0f}));
                }
            }

            if(this.dotRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(this.dotRunnable);
            }

            this.dotRunnable = new Runnable(v2) {
                public void run() {
                    if(this.this$1.dotRunnable != this) {
                        return;
                    }

                    ArrayList v0 = new ArrayList();
                    Object v1 = this.this$1.characterTextViews.get(this.val$newPos);
                    v0.add(ObjectAnimator.ofFloat(v1, "scaleX", new float[]{0f}));
                    v0.add(ObjectAnimator.ofFloat(v1, "scaleY", new float[]{0f}));
                    v0.add(ObjectAnimator.ofFloat(v1, "alpha", new float[]{0f}));
                    v1 = this.this$1.dotTextViews.get(this.val$newPos);
                    v0.add(ObjectAnimator.ofFloat(v1, "scaleX", new float[]{1f}));
                    v0.add(ObjectAnimator.ofFloat(v1, "scaleY", new float[]{1f}));
                    v0.add(ObjectAnimator.ofFloat(v1, "alpha", new float[]{1f}));
                    this.this$1.currentAnimation = new AnimatorSet();
                    this.this$1.currentAnimation.setDuration(150);
                    this.this$1.currentAnimation.playTogether(((Collection)v0));
                    this.this$1.currentAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator arg2) {
                            if(this.this$2.this$1.currentAnimation != null && (this.this$2.this$1.currentAnimation.equals(arg2))) {
                                this.this$2.this$1.currentAnimation = null;
                            }
                        }
                    });
                    this.this$1.currentAnimation.start();
                }
            };
            AndroidUtilities.runOnUIThread(this.dotRunnable, 1500);
            for(v11_1 = 0; v11_1 < v2; ++v11_1) {
                Object v1_1 = this.characterTextViews.get(v11_1);
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "translationX", new float[]{((float)this.getXForTextView(v11_1))}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "scaleX", new float[]{0f}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "scaleY", new float[]{0f}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "alpha", new float[]{0f}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "translationY", new float[]{0f}));
                v1_1 = this.dotTextViews.get(v11_1);
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "translationX", new float[]{((float)this.getXForTextView(v11_1))}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "scaleX", new float[]{1f}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "scaleY", new float[]{1f}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "alpha", new float[]{1f}));
                v0_2.add(ObjectAnimator.ofFloat(v1_1, "translationY", new float[]{0f}));
            }

            if(this.currentAnimation != null) {
                this.currentAnimation.cancel();
            }

            this.currentAnimation = new AnimatorSet();
            this.currentAnimation.setDuration(150);
            this.currentAnimation.playTogether(((Collection)v0_2));
            this.currentAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    if(this.this$1.currentAnimation != null && (this.this$1.currentAnimation.equals(arg2))) {
                        this.this$1.currentAnimation = null;
                    }
                }
            });
            this.currentAnimation.start();
        }

        private void eraseAllCharacters(boolean arg9) {
            // Method was not decompiled
        }

        public void eraseLastCharacter() {
            if(this.stringBuilder.length() == 0) {
                return;
            }

            int v0 = 3;
            try {
                this.performHapticFeedback(v0);
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }

            ArrayList v0_2 = new ArrayList();
            int v1 = this.stringBuilder.length() - 1;
            if(v1 != 0) {
                this.stringBuilder.deleteCharAt(v1);
            }

            int v3;
            for(v3 = v1; v3 < 4; ++v3) {
                Object v4 = this.characterTextViews.get(v3);
                if(((TextView)v4).getAlpha() != 0f) {
                    v0_2.add(ObjectAnimator.ofFloat(v4, "scaleX", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "scaleY", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "alpha", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "translationY", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "translationX", new float[]{((float)this.getXForTextView(v3))}));
                }

                v4 = this.dotTextViews.get(v3);
                if(((TextView)v4).getAlpha() != 0f) {
                    v0_2.add(ObjectAnimator.ofFloat(v4, "scaleX", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "scaleY", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "alpha", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "translationY", new float[]{0f}));
                    v0_2.add(ObjectAnimator.ofFloat(v4, "translationX", new float[]{((float)this.getXForTextView(v3))}));
                }
            }

            if(v1 == 0) {
                this.stringBuilder.deleteCharAt(v1);
            }

            for(v3 = 0; v3 < v1; ++v3) {
                v0_2.add(ObjectAnimator.ofFloat(this.characterTextViews.get(v3), "translationX", new float[]{((float)this.getXForTextView(v3))}));
                v0_2.add(ObjectAnimator.ofFloat(this.dotTextViews.get(v3), "translationX", new float[]{((float)this.getXForTextView(v3))}));
            }

            if(this.dotRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(this.dotRunnable);
                this.dotRunnable = null;
            }

            if(this.currentAnimation != null) {
                this.currentAnimation.cancel();
            }

            this.currentAnimation = new AnimatorSet();
            this.currentAnimation.setDuration(150);
            this.currentAnimation.playTogether(((Collection)v0_2));
            this.currentAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    if(this.this$1.currentAnimation != null && (this.this$1.currentAnimation.equals(arg2))) {
                        this.this$1.currentAnimation = null;
                    }
                }
            });
            this.currentAnimation.start();
        }

        public String getString() {
            return this.stringBuilder.toString();
        }

        private int getXForTextView(int arg5) {
            return (this.getMeasuredWidth() - this.stringBuilder.length() * AndroidUtilities.dp(30f)) / 2 + arg5 * AndroidUtilities.dp(30f) - AndroidUtilities.dp(10f);
        }

        public int length() {
            return this.stringBuilder.length();
        }

        protected void onLayout(boolean arg6, int arg7, int arg8, int arg9, int arg10) {
            Runnable v1 = null;
            if(this.dotRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(this.dotRunnable);
                this.dotRunnable = v1;
            }

            if(this.currentAnimation != null) {
                this.currentAnimation.cancel();
                this.currentAnimation = ((AnimatorSet)v1);
            }

            int v0;
            for(v0 = 0; v0 < 4; ++v0) {
                if(v0 < this.stringBuilder.length()) {
                    Object v1_1 = this.characterTextViews.get(v0);
                    ((TextView)v1_1).setAlpha(0f);
                    ((TextView)v1_1).setScaleX(1f);
                    ((TextView)v1_1).setScaleY(1f);
                    ((TextView)v1_1).setTranslationY(0f);
                    ((TextView)v1_1).setTranslationX(((float)this.getXForTextView(v0)));
                    v1_1 = this.dotTextViews.get(v0);
                    ((TextView)v1_1).setAlpha(1f);
                    ((TextView)v1_1).setScaleX(1f);
                    ((TextView)v1_1).setScaleY(1f);
                    ((TextView)v1_1).setTranslationY(0f);
                    ((TextView)v1_1).setTranslationX(((float)this.getXForTextView(v0)));
                }
                else {
                    this.characterTextViews.get(v0).setAlpha(0f);
                    this.dotTextViews.get(v0).setAlpha(0f);
                }
            }

            super.onLayout(arg6, arg7, arg8, arg9, arg10);
        }
    }

    public interface PasscodeViewDelegate {
        void didAcceptedPassword();
    }

    private Drawable backgroundDrawable;
    private FrameLayout backgroundFrameLayout;
    private b cancellationSignal;
    private ImageView checkImage;
    private Runnable checkRunnable;
    private PasscodeViewDelegate delegate;
    private ImageView eraseView;
    private AlertDialog fingerprintDialog;
    private ImageView fingerprintImageView;
    private TextView fingerprintStatusTextView;
    private static final int id_fingerprint_imageview = 1001;
    private static final int id_fingerprint_textview = 1000;
    private int keyboardHeight;
    private int lastValue;
    private ArrayList lettersTextViews;
    private ArrayList numberFrameLayouts;
    private ArrayList numberTextViews;
    private FrameLayout numbersFrameLayout;
    private TextView passcodeTextView;
    private EditTextBoldCursor passwordEditText;
    private AnimatingTextView passwordEditText2;
    private FrameLayout passwordFrameLayout;
    private Rect rect;
    private TextView retryTextView;
    private boolean selfCancelled;

    public PasscodeView(Context arg19) {
        String v11_1;
        float v11;
        PasscodeView v0 = this;
        Context v1 = arg19;
        super(arg19);
        int v2 = 0;
        v0.keyboardHeight = 0;
        v0.rect = new Rect();
        v0.checkRunnable = new org.telegram.ui.Components.PasscodeView$9(v0);
        v0.setWillNotDraw(false);
        v0.setVisibility(8);
        v0.backgroundFrameLayout = new FrameLayout(v1);
        v0.addView(v0.backgroundFrameLayout);
        ViewGroup$LayoutParams v3 = v0.backgroundFrameLayout.getLayoutParams();
        int v4 = -1;
        ((FrameLayout$LayoutParams)v3).width = v4;
        ((FrameLayout$LayoutParams)v3).height = v4;
        v0.backgroundFrameLayout.setLayoutParams(v3);
        v0.passwordFrameLayout = new FrameLayout(v1);
        v0.addView(v0.passwordFrameLayout);
        v3 = v0.passwordFrameLayout.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).width = v4;
        ((FrameLayout$LayoutParams)v3).height = v4;
        int v5 = 51;
        ((FrameLayout$LayoutParams)v3).gravity = v5;
        v0.passwordFrameLayout.setLayoutParams(v3);
        ImageView v3_1 = new ImageView(v1);
        v3_1.setScaleType(ImageView$ScaleType.FIT_XY);
        v3_1.setImageResource(2131231454);
        v0.passwordFrameLayout.addView(((View)v3_1));
        ViewGroup$LayoutParams v6 = v3_1.getLayoutParams();
        float v8 = 1f;
        float v7 = AndroidUtilities.density < v8 ? 30f : 40f;
        ((FrameLayout$LayoutParams)v6).width = AndroidUtilities.dp(v7);
        ((FrameLayout$LayoutParams)v6).height = AndroidUtilities.dp(v7);
        ((FrameLayout$LayoutParams)v6).gravity = 81;
        float v9 = 100f;
        ((FrameLayout$LayoutParams)v6).bottomMargin = AndroidUtilities.dp(v9);
        v3_1.setLayoutParams(v6);
        v0.passcodeTextView = new TextView(v1);
        v0.passcodeTextView.setTextColor(v4);
        v0.passcodeTextView.setTextSize(1, 14f);
        v0.passcodeTextView.setGravity(1);
        v0.passwordFrameLayout.addView(v0.passcodeTextView, LayoutHelper.createFrame(-2, -2f, 81, 0f, 0f, 0f, 62f));
        v0.retryTextView = new TextView(v1);
        v0.retryTextView.setTextColor(v4);
        v0.retryTextView.setTextSize(1, 15f);
        v0.retryTextView.setGravity(1);
        v0.retryTextView.setVisibility(4);
        int v6_1 = 17;
        v0.addView(v0.retryTextView, LayoutHelper.createFrame(-2, -2, v6_1));
        v0.passwordEditText2 = new AnimatingTextView(v0, v1);
        v0.passwordFrameLayout.addView(v0.passwordEditText2);
        v3 = v0.passwordEditText2.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).height = -2;
        ((FrameLayout$LayoutParams)v3).width = v4;
        ((FrameLayout$LayoutParams)v3).leftMargin = AndroidUtilities.dp(70f);
        ((FrameLayout$LayoutParams)v3).rightMargin = AndroidUtilities.dp(70f);
        ((FrameLayout$LayoutParams)v3).bottomMargin = AndroidUtilities.dp(6f);
        ((FrameLayout$LayoutParams)v3).gravity = 81;
        v0.passwordEditText2.setLayoutParams(v3);
        v0.passwordEditText = new EditTextBoldCursor(v1);
        v0.passwordEditText.setTextSize(1, 36f);
        v0.passwordEditText.setTextColor(v4);
        v0.passwordEditText.setMaxLines(1);
        v0.passwordEditText.setLines(1);
        v0.passwordEditText.setGravity(1);
        v0.passwordEditText.setSingleLine(true);
        v0.passwordEditText.setImeOptions(6);
        v0.passwordEditText.setTypeface(Typeface.DEFAULT);
        v0.passwordEditText.setBackgroundDrawable(null);
        v0.passwordEditText.setCursorColor(v4);
        v0.passwordEditText.setCursorSize(AndroidUtilities.dp(32f));
        v0.passwordFrameLayout.addView(v0.passwordEditText);
        v3 = v0.passwordEditText.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).height = -2;
        ((FrameLayout$LayoutParams)v3).width = v4;
        ((FrameLayout$LayoutParams)v3).leftMargin = AndroidUtilities.dp(70f);
        ((FrameLayout$LayoutParams)v3).rightMargin = AndroidUtilities.dp(70f);
        ((FrameLayout$LayoutParams)v3).gravity = 81;
        v0.passwordEditText.setLayoutParams(v3);
        v0.passwordEditText.setOnEditorActionListener(new TextView$OnEditorActionListener() {
            public boolean onEditorAction(TextView arg1, int arg2, KeyEvent arg3) {
                boolean v1 = false;
                if(arg2 == 6) {
                    PasscodeView.access$400(PasscodeView.this, false);
                    v1 = true;
                }

                return v1;
            }
        });
        v0.passwordEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable arg2) {
                if(PasscodeView.access$500(PasscodeView.this).length() == 4 && SharedConfig.passcodeType == 0) {
                    PasscodeView.access$400(PasscodeView.this, false);
                }
            }

            public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
            }

            public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
            }
        });
        v0.passwordEditText.setCustomSelectionActionModeCallback(new ActionMode$Callback() {
            public boolean onActionItemClicked(ActionMode arg1, MenuItem arg2) {
                return 0;
            }

            public boolean onCreateActionMode(ActionMode arg1, Menu arg2) {
                return 0;
            }

            public void onDestroyActionMode(ActionMode arg1) {
            }

            public boolean onPrepareActionMode(ActionMode arg1, Menu arg2) {
                return 0;
            }
        });
        v0.checkImage = new ImageView(v1);
        v0.checkImage.setImageResource(2131231452);
        v0.checkImage.setScaleType(ImageView$ScaleType.CENTER);
        v0.checkImage.setBackgroundResource(2131230915);
        v0.passwordFrameLayout.addView(v0.checkImage);
        v3 = v0.checkImage.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).width = AndroidUtilities.dp(60f);
        ((FrameLayout$LayoutParams)v3).height = AndroidUtilities.dp(60f);
        ((FrameLayout$LayoutParams)v3).bottomMargin = AndroidUtilities.dp(4f);
        ((FrameLayout$LayoutParams)v3).rightMargin = AndroidUtilities.dp(10f);
        ((FrameLayout$LayoutParams)v3).gravity = 85;
        v0.checkImage.setLayoutParams(v3);
        v0.checkImage.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg2) {
                PasscodeView.access$400(PasscodeView.this, false);
            }
        });
        FrameLayout v3_2 = new FrameLayout(v1);
        v3_2.setBackgroundColor(654311423);
        v0.passwordFrameLayout.addView(((View)v3_2));
        ViewGroup$LayoutParams v7_1 = v3_2.getLayoutParams();
        ((FrameLayout$LayoutParams)v7_1).width = v4;
        ((FrameLayout$LayoutParams)v7_1).height = AndroidUtilities.dp(v8);
        ((FrameLayout$LayoutParams)v7_1).gravity = 83;
        v8 = 20f;
        ((FrameLayout$LayoutParams)v7_1).leftMargin = AndroidUtilities.dp(v8);
        ((FrameLayout$LayoutParams)v7_1).rightMargin = AndroidUtilities.dp(v8);
        v3_2.setLayoutParams(v7_1);
        v0.numbersFrameLayout = new FrameLayout(v1);
        v0.addView(v0.numbersFrameLayout);
        v3 = v0.numbersFrameLayout.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).width = v4;
        ((FrameLayout$LayoutParams)v3).height = v4;
        ((FrameLayout$LayoutParams)v3).gravity = v5;
        v0.numbersFrameLayout.setLayoutParams(v3);
        int v7_2 = 10;
        v0.lettersTextViews = new ArrayList(v7_2);
        v0.numberTextViews = new ArrayList(v7_2);
        v0.numberFrameLayouts = new ArrayList(v7_2);
        int v3_3;
        for(v3_3 = 0; true; ++v3_3) {
            v11 = 50f;
            if(v3_3 >= v7_2) {
                break;
            }

            TextView v12 = new TextView(v1);
            v12.setTextColor(v4);
            v12.setTextSize(1, 36f);
            v12.setGravity(v6_1);
            v12.setText(String.format(Locale.US, "%d", Integer.valueOf(v3_3)));
            v0.numbersFrameLayout.addView(((View)v12));
            ViewGroup$LayoutParams v13 = v12.getLayoutParams();
            ((FrameLayout$LayoutParams)v13).width = AndroidUtilities.dp(v11);
            ((FrameLayout$LayoutParams)v13).height = AndroidUtilities.dp(v11);
            ((FrameLayout$LayoutParams)v13).gravity = v5;
            v12.setLayoutParams(v13);
            v0.numberTextViews.add(v12);
            v12 = new TextView(v1);
            v12.setTextSize(1, 12f);
            v12.setTextColor(2147483647);
            v12.setGravity(v6_1);
            v0.numbersFrameLayout.addView(((View)v12));
            v13 = v12.getLayoutParams();
            ((FrameLayout$LayoutParams)v13).width = AndroidUtilities.dp(v11);
            ((FrameLayout$LayoutParams)v13).height = AndroidUtilities.dp(v8);
            ((FrameLayout$LayoutParams)v13).gravity = v5;
            v12.setLayoutParams(v13);
            if(v3_3 != 0) {
                switch(v3_3) {
                    case 2: {
                        goto label_320;
                    }
                    case 3: {
                        goto label_318;
                    }
                    case 4: {
                        goto label_316;
                    }
                    case 5: {
                        goto label_314;
                    }
                    case 6: {
                        goto label_312;
                    }
                    case 7: {
                        goto label_310;
                    }
                    case 8: {
                        goto label_308;
                    }
                    case 9: {
                        goto label_306;
                    }
                }

                goto label_324;
            label_306:
                v11_1 = "WXYZ";
                goto label_323;
            label_308:
                v11_1 = "TUV";
                goto label_323;
            label_310:
                v11_1 = "PQRS";
                goto label_323;
            label_312:
                v11_1 = "MNO";
                goto label_323;
            label_314:
                v11_1 = "JKL";
                goto label_323;
            label_316:
                v11_1 = "GHI";
                goto label_323;
            label_318:
                v11_1 = "DEF";
                goto label_323;
            label_320:
                v11_1 = "ABC";
                goto label_323;
            }
            else {
                v11_1 = "+";
            label_323:
                v12.setText(((CharSequence)v11_1));
            }

        label_324:
            v0.lettersTextViews.add(v12);
        }

        v0.eraseView = new ImageView(v1);
        v0.eraseView.setScaleType(ImageView$ScaleType.CENTER);
        v0.eraseView.setImageResource(2131231453);
        v0.numbersFrameLayout.addView(v0.eraseView);
        v3 = v0.eraseView.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).width = AndroidUtilities.dp(v11);
        ((FrameLayout$LayoutParams)v3).height = AndroidUtilities.dp(v11);
        ((FrameLayout$LayoutParams)v3).gravity = v5;
        v0.eraseView.setLayoutParams(v3);
        while(v2 < 11) {
            v3_2 = new FrameLayout(v1);
            v3_2.setBackgroundResource(2131230915);
            v3_2.setTag(Integer.valueOf(v2));
            if(v2 == v7_2) {
                v3_2.setOnLongClickListener(new View$OnLongClickListener() {
                    public boolean onLongClick(View arg2) {
                        PasscodeView.access$500(PasscodeView.this).setText("");
                        AnimatingTextView.access$700(PasscodeView.access$600(PasscodeView.this), true);
                        return 1;
                    }
                });
            }

            v3_2.setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg2) {
                    switch(arg2.getTag().intValue()) {
                        case 0: {
                            goto label_44;
                        }
                        case 1: {
                            goto label_40;
                        }
                        case 2: {
                            goto label_36;
                        }
                        case 3: {
                            goto label_32;
                        }
                        case 4: {
                            goto label_28;
                        }
                        case 5: {
                            goto label_24;
                        }
                        case 6: {
                            goto label_20;
                        }
                        case 7: {
                            goto label_16;
                        }
                        case 8: {
                            goto label_12;
                        }
                        case 9: {
                            goto label_8;
                        }
                        case 10: {
                            goto label_4;
                        }
                    }

                    goto label_48;
                label_36:
                    AnimatingTextView v2 = PasscodeView.access$600(PasscodeView.this);
                    String v0 = "2";
                    goto label_47;
                label_20:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "6";
                    goto label_47;
                label_4:
                    PasscodeView.access$600(PasscodeView.this).eraseLastCharacter();
                    goto label_48;
                label_40:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "1";
                    goto label_47;
                label_24:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "5";
                    goto label_47;
                label_8:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "9";
                    goto label_47;
                label_44:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "0";
                    goto label_47;
                label_28:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "4";
                    goto label_47;
                label_12:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "8";
                    goto label_47;
                label_32:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "3";
                    goto label_47;
                label_16:
                    v2 = PasscodeView.access$600(PasscodeView.this);
                    v0 = "7";
                label_47:
                    v2.appendCharacter(v0);
                label_48:
                    if(PasscodeView.access$600(PasscodeView.this).length() == 4) {
                        PasscodeView.access$400(PasscodeView.this, false);
                    }
                }
            });
            v0.numberFrameLayouts.add(v3_2);
            ++v2;
        }

        while(v7_2 >= 0) {
            Object v1_1 = v0.numberFrameLayouts.get(v7_2);
            v0.numbersFrameLayout.addView(((View)v1_1));
            ViewGroup$LayoutParams v2_1 = ((FrameLayout)v1_1).getLayoutParams();
            ((FrameLayout$LayoutParams)v2_1).width = AndroidUtilities.dp(v9);
            ((FrameLayout$LayoutParams)v2_1).height = AndroidUtilities.dp(v9);
            ((FrameLayout$LayoutParams)v2_1).gravity = v5;
            ((FrameLayout)v1_1).setLayoutParams(v2_1);
            --v7_2;
        }
    }

    static Runnable access$1000(PasscodeView arg0) {
        return arg0.checkRunnable;
    }

    static TextView access$1100(PasscodeView arg0) {
        return arg0.retryTextView;
    }

    static b access$1200(PasscodeView arg0) {
        return arg0.cancellationSignal;
    }

    static b access$1202(PasscodeView arg0, b arg1) {
        arg0.cancellationSignal = arg1;
        return arg1;
    }

    static boolean access$1300(PasscodeView arg0) {
        return arg0.selfCancelled;
    }

    static boolean access$1302(PasscodeView arg0, boolean arg1) {
        arg0.selfCancelled = arg1;
        return arg1;
    }

    static void access$1400(PasscodeView arg0, CharSequence arg1) {
        arg0.showFingerprintError(arg1);
    }

    static AlertDialog access$1500(PasscodeView arg0) {
        return arg0.fingerprintDialog;
    }

    static AlertDialog access$1502(PasscodeView arg0, AlertDialog arg1) {
        arg0.fingerprintDialog = arg1;
        return arg1;
    }

    static void access$400(PasscodeView arg0, boolean arg1) {
        arg0.processDone(arg1);
    }

    static EditTextBoldCursor access$500(PasscodeView arg0) {
        return arg0.passwordEditText;
    }

    static AnimatingTextView access$600(PasscodeView arg0) {
        return arg0.passwordEditText2;
    }

    static void access$800(PasscodeView arg0, float arg1, int arg2) {
        arg0.shakeTextView(arg1, arg2);
    }

    static void access$900(PasscodeView arg0) {
        arg0.checkRetryTextView();
    }

    private void checkFingerprint() {
        FingerprintManagerCompat v1;
        Context v0 = this.getContext();
        if(Build$VERSION.SDK_INT < 23) {
            return;
        }

        if(v0 == null) {
            return;
        }

        if(!SharedConfig.useFingerprint) {
            return;
        }

        if(ApplicationLoader.mainInterfacePaused) {
            return;
        }

        try {
            if(this.fingerprintDialog == null) {
                goto label_17;
            }

            if(!this.fingerprintDialog.isShowing()) {
                goto label_17;
            }

            return;
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }

        try {
        label_17:
            v1 = FingerprintManagerCompat.from(ApplicationLoader.applicationContext);
            if(!v1.isHardwareDetected()) {
                return;
            }

            goto label_21;
        }
        catch(Throwable ) {
            return;
        }

        return;
        try {
        label_21:
            if(!v1.hasEnrolledFingerprints()) {
                return;
            }

            RelativeLayout v0_2 = new RelativeLayout(this.getContext());
            v0_2.setPadding(AndroidUtilities.dp(24f), 0, AndroidUtilities.dp(24f), 0);
            TextView v2 = new TextView(this.getContext());
            v2.setId(1000);
            v2.setTextAppearance(16974344);
            v2.setTextColor(Theme.getColor("dialogTextBlack"));
            v2.setText(LocaleController.getString("FingerprintInfo", 2131624794));
            v0_2.addView(((View)v2));
            RelativeLayout$LayoutParams v5 = LayoutHelper.createRelative(-2, -2);
            v5.addRule(10);
            v5.addRule(20);
            v2.setLayoutParams(((ViewGroup$LayoutParams)v5));
            this.fingerprintImageView = new ImageView(this.getContext());
            this.fingerprintImageView.setImageResource(2131231170);
            this.fingerprintImageView.setId(1001);
            v0_2.addView(this.fingerprintImageView, LayoutHelper.createRelative(-2f, -2f, 0, 20, 0, 0, 20, 3, 1000));
            this.fingerprintStatusTextView = new TextView(this.getContext());
            this.fingerprintStatusTextView.setGravity(16);
            this.fingerprintStatusTextView.setText(LocaleController.getString("FingerprintHelp", 2131624793));
            this.fingerprintStatusTextView.setTextAppearance(16974320);
            this.fingerprintStatusTextView.setTextColor(Theme.getColor("dialogTextBlack") & 1124073471);
            v0_2.addView(this.fingerprintStatusTextView);
            RelativeLayout$LayoutParams v2_1 = LayoutHelper.createRelative(-2, -2);
            v2_1.setMarginStart(AndroidUtilities.dp(16f));
            v2_1.addRule(8, 1001);
            v2_1.addRule(6, 1001);
            v2_1.addRule(17, 1001);
            this.fingerprintStatusTextView.setLayoutParams(((ViewGroup$LayoutParams)v2_1));
            Builder v2_2 = new Builder(this.getContext());
            v2_2.setTitle(LocaleController.getString("AppName", 2131624086));
            v2_2.setView(((View)v0_2));
            v2_2.setNegativeButton(LocaleController.getString("Cancel", 2131624257), null);
            v2_2.setOnDismissListener(new DialogInterface$OnDismissListener() {
                public void onDismiss(DialogInterface arg2) {
                    if(PasscodeView.this.cancellationSignal != null) {
                        PasscodeView.this.selfCancelled = true;
                        PasscodeView.this.cancellationSignal.a();
                        PasscodeView.this.cancellationSignal = null;
                    }
                }
            });
            if(this.fingerprintDialog == null) {
                goto label_136;
            }

            try {
                if(!this.fingerprintDialog.isShowing()) {
                    goto label_136;
                }

                this.fingerprintDialog.dismiss();
                goto label_136;
            }
            catch(Exception v0_1) {
                try {
                    FileLog.e(((Throwable)v0_1));
                label_136:
                    this.fingerprintDialog = v2_2.show();
                    this.cancellationSignal = new b();
                    this.selfCancelled = false;
                    v1.authenticate(null, 0, this.cancellationSignal, new AuthenticationCallback() {
                        public void onAuthenticationError(int arg1, CharSequence arg2) {
                            if(!PasscodeView.this.selfCancelled) {
                                PasscodeView.this.showFingerprintError(arg2);
                            }
                        }

                        public void onAuthenticationFailed() {
                            PasscodeView.this.showFingerprintError(LocaleController.getString("FingerprintNotRecognized", 2131624795));
                        }

                        public void onAuthenticationHelp(int arg1, CharSequence arg2) {
                            PasscodeView.this.showFingerprintError(arg2);
                        }

                        public void onAuthenticationSucceeded(AuthenticationResult arg2) {
                            try {
                                if(!PasscodeView.this.fingerprintDialog.isShowing()) {
                                    goto label_10;
                                }

                                PasscodeView.this.fingerprintDialog.dismiss();
                            }
                            catch(Exception v2) {
                                FileLog.e(((Throwable)v2));
                            }

                        label_10:
                            PasscodeView.this.fingerprintDialog = null;
                            PasscodeView.this.processDone(true);
                        }
                    }, null);
                    return;
                }
                catch(Throwable ) {
                    return;
                }
            }
        }
        catch(Throwable ) {
            return;
        }
    }

    private void checkRetryTextView() {
        // Method was not decompiled
    }

    protected void onDraw(Canvas arg6) {
        if(this.getVisibility() != 0) {
            return;
        }

        if(this.backgroundDrawable != null) {
            if((this.backgroundDrawable instanceof ColorDrawable)) {
                this.backgroundDrawable.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
            else {
                float v0 = (((float)this.getMeasuredWidth())) / (((float)this.backgroundDrawable.getIntrinsicWidth()));
                float v1 = (((float)(this.getMeasuredHeight() + this.keyboardHeight))) / (((float)this.backgroundDrawable.getIntrinsicHeight()));
                if(v0 < v1) {
                    v0 = v1;
                }

                int v1_1 = ((int)Math.ceil(((double)((((float)this.backgroundDrawable.getIntrinsicWidth())) * v0))));
                int v0_1 = ((int)Math.ceil(((double)((((float)this.backgroundDrawable.getIntrinsicHeight())) * v0))));
                int v2 = (this.getMeasuredWidth() - v1_1) / 2;
                int v3 = (this.getMeasuredHeight() - v0_1 + this.keyboardHeight) / 2;
                this.backgroundDrawable.setBounds(v2, v3, v1_1 + v2, v0_1 + v3);
            }

            this.backgroundDrawable.draw(arg6);
        }
        else {
            super.onDraw(arg6);
        }
    }

    protected void onLayout(boolean arg6, int arg7, int arg8, int arg9, int arg10) {
        View v0 = this.getRootView();
        int v1 = v0.getHeight() - AndroidUtilities.statusBarHeight - AndroidUtilities.getViewInset(v0);
        this.getWindowVisibleDisplayFrame(this.rect);
        this.keyboardHeight = v1 - (this.rect.bottom - this.rect.top);
        if(SharedConfig.passcodeType == 1) {
            v1 = 2;
            if(!AndroidUtilities.isTablet() && this.getContext().getResources().getConfiguration().orientation == v1) {
                goto label_50;
            }

            int v2 = 0;
            int v0_1 = this.passwordFrameLayout.getTag() != null ? this.passwordFrameLayout.getTag().intValue() : 0;
            ViewGroup$LayoutParams v3 = this.passwordFrameLayout.getLayoutParams();
            v0_1 = v0_1 + ((FrameLayout$LayoutParams)v3).height - this.keyboardHeight / v1;
            if(Build$VERSION.SDK_INT >= 21) {
                v2 = AndroidUtilities.statusBarHeight;
            }

            ((FrameLayout$LayoutParams)v3).topMargin = v0_1 - v2;
            this.passwordFrameLayout.setLayoutParams(v3);
        }

    label_50:
        super.onLayout(arg6, arg7, arg8, arg9, arg10);
    }

    protected void onMeasure(int arg13, int arg14) {
        int v10;
        ViewGroup$LayoutParams v8;
        int v5;
        ViewGroup$LayoutParams v2_1;
        int v11;
        int v0 = View$MeasureSpec.getSize(arg13);
        int v1 = AndroidUtilities.displaySize.y;
        int v3 = 0;
        int v2 = Build$VERSION.SDK_INT >= 21 ? 0 : AndroidUtilities.statusBarHeight;
        v1 -= v2;
        int v4 = 2;
        if((AndroidUtilities.isTablet()) || this.getContext().getResources().getConfiguration().orientation != v4) {
            if(AndroidUtilities.isTablet()) {
                float v2_2 = 498f;
                if(v0 > AndroidUtilities.dp(v2_2)) {
                    v0 = (v0 - AndroidUtilities.dp(v2_2)) / v4;
                    v11 = AndroidUtilities.dp(v2_2);
                    v2 = v0;
                    v0 = v11;
                }
                else {
                    v2 = 0;
                }

                float v5_1 = 528f;
                if(v1 > AndroidUtilities.dp(v5_1)) {
                    v11 = v2;
                    v2 = (v1 - AndroidUtilities.dp(v5_1)) / v4;
                    v1 = AndroidUtilities.dp(v5_1);
                    v5 = v11;
                    goto label_79;
                }

                v5 = v2;
                v2 = 0;
            }
            else {
                v2 = 0;
                v5 = 0;
            }

        label_79:
            ViewGroup$LayoutParams v6 = this.passwordFrameLayout.getLayoutParams();
            int v7 = v1 / 3;
            ((FrameLayout$LayoutParams)v6).height = v7;
            ((FrameLayout$LayoutParams)v6).width = v0;
            ((FrameLayout$LayoutParams)v6).topMargin = v2;
            ((FrameLayout$LayoutParams)v6).leftMargin = v5;
            this.passwordFrameLayout.setTag(Integer.valueOf(v2));
            this.passwordFrameLayout.setLayoutParams(v6);
            v6 = this.numbersFrameLayout.getLayoutParams();
            ((FrameLayout$LayoutParams)v6).height = v7 * 2;
            ((FrameLayout$LayoutParams)v6).leftMargin = v5;
            ((FrameLayout$LayoutParams)v6).topMargin = v1 - ((FrameLayout$LayoutParams)v6).height + v2;
            ((FrameLayout$LayoutParams)v6).width = v0;
            this.numbersFrameLayout.setLayoutParams(v6);
            v2_1 = v6;
        }
        else {
            v2_1 = this.passwordFrameLayout.getLayoutParams();
            v5 = SharedConfig.passcodeType == 0 ? v0 / 2 : v0;
            ((FrameLayout$LayoutParams)v2_1).width = v5;
            ((FrameLayout$LayoutParams)v2_1).height = AndroidUtilities.dp(140f);
            ((FrameLayout$LayoutParams)v2_1).topMargin = (v1 - AndroidUtilities.dp(140f)) / v4;
            this.passwordFrameLayout.setLayoutParams(v2_1);
            v2_1 = this.numbersFrameLayout.getLayoutParams();
            ((FrameLayout$LayoutParams)v2_1).height = v1;
            v0 /= 2;
            ((FrameLayout$LayoutParams)v2_1).leftMargin = v0;
            ((FrameLayout$LayoutParams)v2_1).topMargin = v1 - ((FrameLayout$LayoutParams)v2_1).height;
            ((FrameLayout$LayoutParams)v2_1).width = v0;
            this.numbersFrameLayout.setLayoutParams(v2_1);
        }

        float v1_1 = 50f;
        v0 = (((FrameLayout$LayoutParams)v2_1).width - AndroidUtilities.dp(v1_1) * 3) / 4;
        v2 = (((FrameLayout$LayoutParams)v2_1).height - AndroidUtilities.dp(v1_1) * 4) / 5;
        while(true) {
            v4 = 11;
            if(v3 >= v4) {
                break;
            }

            v5 = 10;
            if(v3 == 0) {
                v4 = 10;
            }
            else if(v3 == v5) {
            }
            else {
                v4 = v3 - 1;
            }

            int v6_1 = v4 / 3;
            v4 %= 3;
            if(v3 < v5) {
                Object v5_2 = this.numberTextViews.get(v3);
                Object v7_1 = this.lettersTextViews.get(v3);
                v8 = ((TextView)v5_2).getLayoutParams();
                ViewGroup$LayoutParams v9 = ((TextView)v7_1).getLayoutParams();
                v10 = (AndroidUtilities.dp(v1_1) + v2) * v6_1 + v2;
                ((FrameLayout$LayoutParams)v8).topMargin = v10;
                ((FrameLayout$LayoutParams)v9).topMargin = v10;
                v6_1 = (AndroidUtilities.dp(v1_1) + v0) * v4 + v0;
                ((FrameLayout$LayoutParams)v8).leftMargin = v6_1;
                ((FrameLayout$LayoutParams)v9).leftMargin = v6_1;
                ((FrameLayout$LayoutParams)v9).topMargin += AndroidUtilities.dp(40f);
                ((TextView)v5_2).setLayoutParams(v8);
                ((TextView)v7_1).setLayoutParams(v9);
            }
            else {
                v8 = this.eraseView.getLayoutParams();
                v5 = (AndroidUtilities.dp(v1_1) + v2) * v6_1 + v2 + AndroidUtilities.dp(8f);
                ((FrameLayout$LayoutParams)v8).topMargin = v5;
                ((FrameLayout$LayoutParams)v8).leftMargin = (AndroidUtilities.dp(v1_1) + v0) * v4 + v0;
                v10 = v5 - AndroidUtilities.dp(8f);
                this.eraseView.setLayoutParams(v8);
            }

            Object v4_1 = this.numberFrameLayouts.get(v3);
            ViewGroup$LayoutParams v5_3 = ((FrameLayout)v4_1).getLayoutParams();
            ((FrameLayout$LayoutParams)v5_3).topMargin = v10 - AndroidUtilities.dp(17f);
            ((FrameLayout$LayoutParams)v5_3).leftMargin = ((FrameLayout$LayoutParams)v8).leftMargin - AndroidUtilities.dp(25f);
            ((FrameLayout)v4_1).setLayoutParams(v5_3);
            ++v3;
        }

        super.onMeasure(arg13, arg14);
    }

    private void onPasscodeError() {
        Object v0 = this.getContext().getSystemService("vibrator");
        if(v0 != null) {
            ((Vibrator)v0).vibrate(200);
        }

        this.shakeTextView(2f, 0);
    }

    public void onPause() {
        AndroidUtilities.cancelRunOnUIThread(this.checkRunnable);
        AlertDialog v1 = null;
        if(this.fingerprintDialog == null) {
            goto label_14;
        }

        try {
            if(this.fingerprintDialog.isShowing()) {
                this.fingerprintDialog.dismiss();
            }

            this.fingerprintDialog = v1;
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }

        try {
        label_14:
            if(Build$VERSION.SDK_INT < 23) {
                return;
            }

            if(this.cancellationSignal == null) {
                return;
            }

            this.cancellationSignal.a();
            this.cancellationSignal = ((b)v1);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public void onResume() {
        this.checkRetryTextView();
        if(this.retryTextView.getVisibility() != 0) {
            if(SharedConfig.passcodeType == 1) {
                if(this.passwordEditText != null) {
                    this.passwordEditText.requestFocus();
                    AndroidUtilities.showKeyboard(this.passwordEditText);
                }

                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        if(PasscodeView.this.retryTextView.getVisibility() != 0 && PasscodeView.this.passwordEditText != null) {
                            PasscodeView.this.passwordEditText.requestFocus();
                            AndroidUtilities.showKeyboard(PasscodeView.this.passwordEditText);
                        }
                    }
                }, 200);
            }

            this.checkFingerprint();
        }
    }

    public void onShow() {
        this.checkRetryTextView();
        Context v0 = this.getContext();
        if(SharedConfig.passcodeType == 1) {
            if(this.retryTextView.getVisibility() != 0 && this.passwordEditText != null) {
                this.passwordEditText.requestFocus();
                AndroidUtilities.showKeyboard(this.passwordEditText);
            }
        }
        else if(v0 != null) {
            View v0_1 = ((Activity)v0).getCurrentFocus();
            if(v0_1 != null) {
                v0_1.clearFocus();
                AndroidUtilities.hideKeyboard(this.getContext().getCurrentFocus());
            }
        }

        if(this.retryTextView.getVisibility() != 0) {
            this.checkFingerprint();
        }

        if(this.getVisibility() == 0) {
            return;
        }

        this.setAlpha(1f);
        this.setTranslationY(0f);
        int v1 = -1090519040;
        if(Theme.isCustomTheme()) {
            this.backgroundDrawable = Theme.getCachedWallpaper();
            goto label_38;
        }
        else {
            int v3 = -11436898;
            if(MessagesController.getGlobalMainSettings().getInt("selectedBackground", 1000001) != 1000001) {
                this.backgroundDrawable = Theme.getCachedWallpaper();
                if(this.backgroundDrawable != null) {
                label_38:
                    this.backgroundFrameLayout.setBackgroundColor(v1);
                    goto label_55;
                }
            }

            this.backgroundFrameLayout.setBackgroundColor(v3);
        }

    label_55:
        this.passcodeTextView.setText(LocaleController.getString("EnterYourPasscode", 2131624695));
        v1 = 8;
        if(SharedConfig.passcodeType == 0) {
            if(this.retryTextView.getVisibility() != 0) {
                this.numbersFrameLayout.setVisibility(0);
            }

            this.passwordEditText.setVisibility(v1);
            this.passwordEditText2.setVisibility(0);
            this.checkImage.setVisibility(v1);
        }
        else {
            if(SharedConfig.passcodeType != 1) {
                goto label_96;
            }

            this.passwordEditText.setFilters(new InputFilter[0]);
            this.passwordEditText.setInputType(129);
            this.numbersFrameLayout.setVisibility(v1);
            this.passwordEditText.setFocusable(true);
            this.passwordEditText.setFocusableInTouchMode(true);
            this.passwordEditText.setVisibility(0);
            this.passwordEditText2.setVisibility(v1);
            this.checkImage.setVisibility(0);
        }

    label_96:
        this.setVisibility(0);
        this.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.passwordEditText.setText("");
        AnimatingTextView.access$700(this.passwordEditText2, false);
        this.setOnTouchListener(new View$OnTouchListener() {
            public boolean onTouch(View arg1, MotionEvent arg2) {
                return 1;
            }
        });
    }

    private void processDone(boolean arg7) {
        if(!arg7) {
            long v3 = 0;
            if(SharedConfig.passcodeRetryInMs > v3) {
                return;
            }
            else {
                String v7 = "";
                if(SharedConfig.passcodeType == 0) {
                    v7 = this.passwordEditText2.getString();
                }
                else if(SharedConfig.passcodeType == 1) {
                    v7 = this.passwordEditText.getText().toString();
                }

                if(v7.length() == 0) {
                    this.onPasscodeError();
                    return;
                }

                if(SharedConfig.checkPasscode(v7)) {
                    goto label_34;
                }

                SharedConfig.increaseBadPasscodeTries();
                if(SharedConfig.passcodeRetryInMs > v3) {
                    this.checkRetryTextView();
                }

                this.passwordEditText.setText("");
                AnimatingTextView.access$700(this.passwordEditText2, true);
                this.onPasscodeError();
                return;
            }
        }

    label_34:
        SharedConfig.badPasscodeTries = 0;
        this.passwordEditText.clearFocus();
        AndroidUtilities.hideKeyboard(this.passwordEditText);
        AnimatorSet v1 = new AnimatorSet();
        v1.setDuration(200);
        v1.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "translationY", new float[]{((float)AndroidUtilities.dp(20f))}), ObjectAnimator.ofFloat(this, "alpha", new float[]{((float)AndroidUtilities.dp(0f))})});
        v1.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                PasscodeView.this.setVisibility(8);
            }
        });
        v1.start();
        SharedConfig.appLocked = false;
        SharedConfig.saveConfig();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetPasscode, new Object[0]);
        this.setOnTouchListener(null);
        if(this.delegate != null) {
            this.delegate.didAcceptedPassword();
        }
    }

    public void setDelegate(PasscodeViewDelegate arg1) {
        this.delegate = arg1;
    }

    private void shakeTextView(float arg8, int arg9) {
        if(arg9 == 6) {
            return;
        }

        AnimatorSet v0 = new AnimatorSet();
        v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.passcodeTextView, "translationX", new float[]{((float)AndroidUtilities.dp(arg8))})});
        v0.setDuration(50);
        v0.addListener(new AnimatorListenerAdapter(arg9, arg8) {
            public void onAnimationEnd(Animator arg3) {
                PasscodeView v3 = PasscodeView.this;
                float v0 = this.val$num == 5 ? 0f : -this.val$x;
                v3.shakeTextView(v0, this.val$num + 1);
            }
        });
        v0.start();
    }

    private void showFingerprintError(CharSequence arg3) {
        this.fingerprintImageView.setImageResource(2131231168);
        this.fingerprintStatusTextView.setText(arg3);
        this.fingerprintStatusTextView.setTextColor(-765666);
        Object v3 = this.getContext().getSystemService("vibrator");
        if(v3 != null) {
            ((Vibrator)v3).vibrate(200);
        }

        AndroidUtilities.shakeView(this.fingerprintStatusTextView, 2f, 0);
    }
}

