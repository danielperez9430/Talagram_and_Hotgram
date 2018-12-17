package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.RadialGradient;
import android.graphics.Shader$TileMode;
import android.graphics.SweepGradient;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.Keep;
import android.text.Editable;
import android.text.InputFilter$LengthFilter;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager$LayoutParams;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView$OnEditorActionListener;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView$Adapter;
import org.telegram.messenger.support.widget.RecyclerView$LayoutManager;
import org.telegram.messenger.support.widget.RecyclerView$LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView$OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView$ViewHolder;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.TextColorThemeCell;
import org.telegram.ui.LaunchActivity;

public class ThemeEditorView {
    public class EditorAlert extends BottomSheet {
        class ColorPicker extends FrameLayout {
            private float alpha;
            private LinearGradient alphaGradient;
            private boolean alphaPressed;
            private Drawable circleDrawable;
            private Paint circlePaint;
            private boolean circlePressed;
            private EditTextBoldCursor[] colorEditText;
            private LinearGradient colorGradient;
            private float[] colorHSV;
            private boolean colorPressed;
            private Bitmap colorWheelBitmap;
            private Paint colorWheelPaint;
            private int colorWheelRadius;
            private DecelerateInterpolator decelerateInterpolator;
            private float[] hsvTemp;
            private LinearLayout linearLayout;
            private final int paramValueSliderWidth;
            private Paint valueSliderPaint;

            public ColorPicker(EditorAlert arg18, Context arg19) {
                String v10_1;
                EditTextBoldCursor v9;
                ColorPicker v0 = this;
                EditorAlert v1 = arg18;
                Context v2 = arg19;
                v0.this$1 = v1;
                super(v2);
                float v3 = 20f;
                v0.paramValueSliderWidth = AndroidUtilities.dp(v3);
                int v4 = 4;
                v0.colorEditText = new EditTextBoldCursor[v4];
                int v5 = 3;
                v0.colorHSV = new float[]{0f, 0f, 1f};
                v0.alpha = 1f;
                v0.hsvTemp = new float[v5];
                v0.decelerateInterpolator = new DecelerateInterpolator();
                v0.setWillNotDraw(false);
                v0.circlePaint = new Paint(1);
                v0.circleDrawable = arg19.getResources().getDrawable(2131231297).mutate();
                v0.colorWheelPaint = new Paint();
                v0.colorWheelPaint.setAntiAlias(true);
                v0.colorWheelPaint.setDither(true);
                v0.valueSliderPaint = new Paint();
                v0.valueSliderPaint.setAntiAlias(true);
                v0.valueSliderPaint.setDither(true);
                v0.linearLayout = new LinearLayout(v2);
                v0.linearLayout.setOrientation(0);
                v0.addView(v0.linearLayout, LayoutHelper.createFrame(-2, -2, 49));
                int v7;
                for(v7 = 0; v7 < v4; ++v7) {
                    v0.colorEditText[v7] = new EditTextBoldCursor(v2);
                    int v10 = 2;
                    v0.colorEditText[v7].setInputType(v10);
                    v0.colorEditText[v7].setTextColor(-14606047);
                    v0.colorEditText[v7].setCursorColor(-14606047);
                    v0.colorEditText[v7].setCursorSize(AndroidUtilities.dp(v3));
                    v0.colorEditText[v7].setCursorWidth(1.5f);
                    v0.colorEditText[v7].setTextSize(1, 18f);
                    v0.colorEditText[v7].setBackgroundDrawable(Theme.createEditTextDrawable(v2, true));
                    v0.colorEditText[v7].setMaxLines(1);
                    v0.colorEditText[v7].setTag(Integer.valueOf(v7));
                    v0.colorEditText[v7].setGravity(17);
                    if(v7 == 0) {
                        v9 = v0.colorEditText[v7];
                        v10_1 = "red";
                        goto label_104;
                    }
                    else if(v7 == 1) {
                        v9 = v0.colorEditText[v7];
                        v10_1 = "green";
                        goto label_104;
                    }
                    else if(v7 == v10) {
                        v9 = v0.colorEditText[v7];
                        v10_1 = "blue";
                        goto label_104;
                    }
                    else if(v7 == v5) {
                        v9 = v0.colorEditText[v7];
                        v10_1 = "alpha";
                    label_104:
                        v9.setHint(((CharSequence)v10_1));
                    }

                    v9 = v0.colorEditText[v7];
                    v10 = v7 == v5 ? 6 : 5;
                    v9.setImeOptions(v10 | 268435456);
                    v0.colorEditText[v7].setFilters(new InputFilter[]{new InputFilter$LengthFilter(v5)});
                    LinearLayout v9_1 = v0.linearLayout;
                    EditTextBoldCursor v10_2 = v0.colorEditText[v7];
                    int v11 = 55;
                    int v12 = 36;
                    float v15 = v7 != v5 ? 16f : 0f;
                    v9_1.addView(((View)v10_2), LayoutHelper.createLinear(v11, v12, 0f, 0f, v15, 0f));
                    v0.colorEditText[v7].addTextChangedListener(new TextWatcher(v1, v7) {
                        public void afterTextChanged(Editable arg7) {
                            int v1;
                            EditTextBoldCursor v7_1;
                            if(this.this$2.this$1.ignoreTextChange) {
                                return;
                            }

                            this.this$2.this$1.ignoreTextChange = true;
                            int v7 = Utilities.parseInt(arg7.toString()).intValue();
                            int v2 = 255;
                            if(v7 < 0) {
                                v7_1 = ColorPicker.access$100(this.this$2)[this.val$num];
                                v7_1.setText("" + 0);
                                ColorPicker.access$100(this.this$2)[this.val$num].setSelection(ColorPicker.access$100(this.this$2)[this.val$num].length());
                                v7 = 0;
                            }
                            else if(v7 > v2) {
                                v7_1 = ColorPicker.access$100(this.this$2)[this.val$num];
                                v7_1.setText("" + v2);
                                ColorPicker.access$100(this.this$2)[this.val$num].setSelection(ColorPicker.access$100(this.this$2)[this.val$num].length());
                                v7 = 255;
                            }

                            int v3_1 = this.this$2.getColor();
                            if(this.val$num == 2) {
                                v1 = v3_1 & -256;
                                v7 &= v2;
                                goto label_68;
                            }
                            else if(this.val$num == 1) {
                                v1 = -65281 & v3_1;
                                v7 = (v7 & v2) << 8;
                                goto label_68;
                            }
                            else if(this.val$num == 0) {
                                v1 = -16711681 & v3_1;
                                v7 = (v7 & v2) << 16;
                                goto label_68;
                            }
                            else if(this.val$num == 3) {
                                v1 = 16777215 & v3_1;
                                v7 = (v7 & v2) << 24;
                            label_68:
                                v3_1 = v1 | v7;
                            }

                            this.this$2.setColor(v3_1);
                            for(v7 = 0; v7 < this.this$2.this$1.this$0.currentThemeDesription.size(); ++v7) {
                                this.this$2.this$1.this$0.currentThemeDesription.get(v7).setColor(this.this$2.getColor(), false);
                            }

                            this.this$2.this$1.ignoreTextChange = false;
                        }

                        public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
                        }

                        public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
                        }
                    });
                    v0.colorEditText[v7].setOnEditorActionListener(new TextView$OnEditorActionListener(v1) {
                        public boolean onEditorAction(TextView arg1, int arg2, KeyEvent arg3) {
                            if(arg2 == 6) {
                                AndroidUtilities.hideKeyboard(((View)arg1));
                                return 1;
                            }

                            return 0;
                        }
                    });
                }
            }

            static EditTextBoldCursor[] access$100(ColorPicker arg0) {
                return arg0.colorEditText;
            }

            private Bitmap createColorWheelBitmap(int arg11, int arg12) {
                Bitmap v0 = Bitmap.createBitmap(arg11, arg12, Bitmap$Config.ARGB_8888);
                int[] v1 = new int[13];
                float[] v2 = new float[]{0f, 1f, 1f};
                int v4;
                for(v4 = 0; v4 < v1.length; ++v4) {
                    v2[0] = ((float)((v4 * 30 + 180) % 360));
                    v1[v4] = Color.HSVToColor(v2);
                }

                v1[12] = v1[0];
                float v11 = ((float)(arg11 / 2));
                float v12 = ((float)(arg12 / 2));
                this.colorWheelPaint.setShader(new ComposeShader(new SweepGradient(v11, v12, v1, null), new RadialGradient(v11, v12, ((float)this.colorWheelRadius), -1, 16777215, Shader$TileMode.CLAMP), PorterDuff$Mode.SRC_OVER));
                new Canvas(v0).drawCircle(v11, v12, ((float)this.colorWheelRadius), this.colorWheelPaint);
                return v0;
            }

            private void drawPointerArrow(Canvas arg6, int arg7, int arg8, int arg9) {
                int v0 = AndroidUtilities.dp(13f);
                this.circleDrawable.setBounds(arg7 - v0, arg8 - v0, arg7 + v0, v0 + arg8);
                this.circleDrawable.draw(arg6);
                this.circlePaint.setColor(-1);
                float v7 = ((float)arg7);
                float v8 = ((float)arg8);
                arg6.drawCircle(v7, v8, ((float)AndroidUtilities.dp(11f)), this.circlePaint);
                this.circlePaint.setColor(arg9);
                arg6.drawCircle(v7, v8, ((float)AndroidUtilities.dp(9f)), this.circlePaint);
            }

            public int getColor() {
                return Color.HSVToColor(this.colorHSV) & 16777215 | (((int)(this.alpha * 255f))) << 24;
            }

            protected void onDraw(Canvas arg26) {
                float v6;
                ColorPicker v0 = this;
                Canvas v7 = arg26;
                int v8 = 2;
                int v1 = this.getWidth() / v8 - v0.paramValueSliderWidth * 2;
                int v2 = this.getHeight() / v8 - AndroidUtilities.dp(8f);
                v7.drawBitmap(v0.colorWheelBitmap, ((float)(v1 - v0.colorWheelRadius)), ((float)(v2 - v0.colorWheelRadius)), null);
                double v3 = ((double)(((float)Math.toRadians(((double)v0.colorHSV[0])))));
                double v5 = -Math.cos(v3);
                double v12 = ((double)v0.colorHSV[1]);
                Double.isNaN(v12);
                v5 *= v12;
                v12 = ((double)v0.colorWheelRadius);
                Double.isNaN(v12);
                int v5_1 = (((int)(v5 * v12))) + v1;
                v3 = -Math.sin(v3);
                v12 = ((double)v0.colorHSV[1]);
                Double.isNaN(v12);
                v3 *= v12;
                v12 = ((double)v0.colorWheelRadius);
                Double.isNaN(v12);
                v0.hsvTemp[0] = v0.colorHSV[0];
                v0.hsvTemp[1] = v0.colorHSV[1];
                v0.hsvTemp[v8] = 1f;
                v0.drawPointerArrow(v7, v5_1, (((int)(v3 * v12))) + v2, Color.HSVToColor(v0.hsvTemp));
                int v12_1 = v1 + v0.colorWheelRadius + v0.paramValueSliderWidth;
                v2 -= v0.colorWheelRadius;
                int v13 = AndroidUtilities.dp(9f);
                int v14 = v0.colorWheelRadius * 2;
                if(v0.colorGradient == null) {
                    int[] v15 = new int[v8];
                    v15[0] = -16777216;
                    v15[1] = Color.HSVToColor(v0.hsvTemp);
                    v0.colorGradient = new LinearGradient(((float)v12_1), ((float)v2), ((float)(v12_1 + v13)), ((float)(v2 + v14)), v15, null, Shader$TileMode.CLAMP);
                }

                v0.valueSliderPaint.setShader(v0.colorGradient);
                float v10 = ((float)v2);
                float v15_1 = ((float)(v2 + v14));
                arg26.drawRect(((float)v12_1), v10, ((float)(v12_1 + v13)), v15_1, v0.valueSliderPaint);
                int v23 = v13 / 2;
                float v14_1 = ((float)v14);
                v0.drawPointerArrow(v7, v12_1 + v23, ((int)(v0.colorHSV[v8] * v14_1 + v10)), Color.HSVToColor(v0.colorHSV));
                v12_1 += v0.paramValueSliderWidth * 2;
                int v24 = 16777215;
                if(v0.alphaGradient == null) {
                    v1 = Color.HSVToColor(v0.hsvTemp);
                    int[] v5_2 = new int[v8];
                    v5_2[0] = v1;
                    v5_2[1] = v1 & v24;
                    v6 = v15_1;
                    v0.alphaGradient = new LinearGradient(((float)v12_1), v10, ((float)(v12_1 + v13)), v6, v5_2, null, Shader$TileMode.CLAMP);
                }
                else {
                    v6 = v15_1;
                }

                v0.valueSliderPaint.setShader(v0.alphaGradient);
                arg26.drawRect(((float)v12_1), v10, ((float)(v13 + v12_1)), v6, v0.valueSliderPaint);
                v0.drawPointerArrow(v7, v12_1 + v23, ((int)(v10 + (1f - v0.alpha) * v14_1)), Color.HSVToColor(v0.colorHSV) & v24 | (((int)(v0.alpha * 255f))) << 24);
            }

            protected void onMeasure(int arg3, int arg4) {
                int v0 = Math.min(View$MeasureSpec.getSize(arg3), View$MeasureSpec.getSize(arg4));
                this.measureChild(this.linearLayout, arg3, arg4);
                this.setMeasuredDimension(v0, v0);
            }

            protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
                this.colorWheelRadius = Math.max(1, arg1 / 2 - this.paramValueSliderWidth * 2 - AndroidUtilities.dp(20f));
                this.colorWheelBitmap = this.createColorWheelBitmap(this.colorWheelRadius * 2, this.colorWheelRadius * 2);
                this.colorGradient = null;
                this.alphaGradient = null;
            }

            public boolean onTouchEvent(MotionEvent arg18) {
                int v16;
                ColorPicker v0 = this;
                switch(arg18.getAction()) {
                    case 1: {
                        goto label_5;
                    }
                    case 0: 
                    case 2: {
                        goto label_10;
                    }
                }

                goto label_253;
            label_5:
                v0.alphaPressed = false;
                v0.colorPressed = false;
                v0.circlePressed = false;
                v0.startColorChange(false);
            label_253:
                return super.onTouchEvent(arg18);
            label_10:
                int v1 = ((int)arg18.getX());
                int v3 = ((int)arg18.getY());
                int v4 = this.getWidth() / 2 - v0.paramValueSliderWidth * 2;
                int v6 = this.getHeight() / 2 - AndroidUtilities.dp(8f);
                int v7 = v1 - v4;
                int v8 = v3 - v6;
                double v9 = Math.sqrt(((double)(v7 * v7 + v8 * v8)));
                float v13 = 1f;
                if(!v0.circlePressed) {
                    if(!v0.alphaPressed && !v0.colorPressed) {
                        v16 = v6;
                        if(v9 <= (((double)v0.colorWheelRadius))) {
                            goto label_49;
                        }
                        else {
                            goto label_77;
                        }
                    }

                    v16 = v6;
                }
                else {
                    v16 = v6;
                label_49:
                    if(v9 > (((double)v0.colorWheelRadius))) {
                        v9 = ((double)v0.colorWheelRadius);
                    }

                    v0.circlePressed = true;
                    v0.colorHSV[0] = ((float)(Math.toDegrees(Math.atan2(((double)v8), ((double)v7))) + 180));
                    float[] v5 = v0.colorHSV;
                    double v6_1 = ((double)v0.colorWheelRadius);
                    Double.isNaN(v6_1);
                    v5[1] = Math.max(0f, Math.min(v13, ((float)(v9 / v6_1))));
                    v0.colorGradient = null;
                    v0.alphaGradient = null;
                }

            label_77:
                float v6_2 = 2f;
                if((v0.colorPressed) || !v0.circlePressed && !v0.alphaPressed && v1 >= v0.colorWheelRadius + v4 + v0.paramValueSliderWidth && v1 <= v0.colorWheelRadius + v4 + v0.paramValueSliderWidth * 2 && v3 >= v16 - v0.colorWheelRadius && v3 <= v16 + v0.colorWheelRadius) {
                    float v5_1 = (((float)(v3 - (v16 - v0.colorWheelRadius)))) / ((((float)v0.colorWheelRadius)) * v6_2);
                    if(v5_1 < 0f) {
                        v5_1 = 0f;
                    }
                    else if(v5_1 > v13) {
                        v5_1 = 1f;
                    }

                    v0.colorHSV[2] = v5_1;
                    v0.colorPressed = true;
                }

                v7 = 4;
                v8 = 3;
                if((v0.alphaPressed) || !v0.circlePressed && !v0.colorPressed && v1 >= v0.colorWheelRadius + v4 + v0.paramValueSliderWidth * 3 && v1 <= v4 + v0.colorWheelRadius + v0.paramValueSliderWidth * 4 && v3 >= v16 - v0.colorWheelRadius && v3 <= v16 + v0.colorWheelRadius) {
                    v0.alpha = v13 - (((float)(v3 - (v16 - v0.colorWheelRadius)))) / ((((float)v0.colorWheelRadius)) * v6_2);
                    if(v0.alpha < 0f) {
                        v0.alpha = 0f;
                    }
                    else if(v0.alpha > v13) {
                        v0.alpha = v13;
                    }

                    v0.alphaPressed = true;
                }

                if((v0.alphaPressed) || (v0.colorPressed) || (v0.circlePressed)) {
                    v0.startColorChange(true);
                    v1 = this.getColor();
                    for(v3 = 0; v3 < v0.this$1.this$0.currentThemeDesription.size(); ++v3) {
                        v0.this$1.this$0.currentThemeDesription.get(v3).setColor(v1, false);
                    }

                    v3 = Color.red(v1);
                    v4 = Color.green(v1);
                    int v5_2 = Color.blue(v1);
                    v1 = Color.alpha(v1);
                    if(!v0.this$1.ignoreTextChange) {
                        v0.this$1.ignoreTextChange = true;
                        EditTextBoldCursor v6_3 = v0.colorEditText[0];
                        v6_3.setText("" + v3);
                        EditTextBoldCursor v3_1 = v0.colorEditText[1];
                        v3_1.setText("" + v4);
                        v3_1 = v0.colorEditText[2];
                        v3_1.setText("" + v5_2);
                        v3_1 = v0.colorEditText[v8];
                        v3_1.setText("" + v1);
                        for(v1 = 0; v1 < v7; ++v1) {
                            v0.colorEditText[v1].setSelection(v0.colorEditText[v1].length());
                        }

                        v0.this$1.ignoreTextChange = false;
                    }

                    this.invalidate();
                }

                return 1;
            }

            public void setColor(int arg10) {
                int v0 = Color.red(arg10);
                int v1 = Color.green(arg10);
                int v2 = Color.blue(arg10);
                int v3 = Color.alpha(arg10);
                if(!this.this$1.ignoreTextChange) {
                    this.this$1.ignoreTextChange = true;
                    EditTextBoldCursor v4 = this.colorEditText[0];
                    v4.setText("" + v0);
                    EditTextBoldCursor v0_1 = this.colorEditText[1];
                    v0_1.setText("" + v1);
                    v0_1 = this.colorEditText[2];
                    v0_1.setText("" + v2);
                    v0_1 = this.colorEditText[3];
                    v0_1.setText("" + v3);
                    for(v0 = 0; v0 < 4; ++v0) {
                        this.colorEditText[v0].setSelection(this.colorEditText[v0].length());
                    }

                    this.this$1.ignoreTextChange = false;
                }

                this.alphaGradient = null;
                this.colorGradient = null;
                this.alpha = (((float)v3)) / 255f;
                Color.colorToHSV(arg10, this.colorHSV);
                this.invalidate();
            }

            private void startColorChange(boolean arg9) {
                if(this.this$1.startedColorChange == arg9) {
                    return;
                }

                if(this.this$1.colorChangeAnimation != null) {
                    this.this$1.colorChangeAnimation.cancel();
                }

                this.this$1.startedColorChange = arg9;
                this.this$1.colorChangeAnimation = new AnimatorSet();
                AnimatorSet v0 = this.this$1.colorChangeAnimation;
                Animator[] v1 = new Animator[2];
                ColorDrawable v2 = this.this$1.backDrawable;
                String v3 = "alpha";
                int[] v5 = new int[1];
                int v7 = arg9 ? 0 : 51;
                v5[0] = v7;
                v1[0] = ObjectAnimator.ofInt(v2, v3, v5);
                ViewGroup v2_1 = this.this$1.containerView;
                v3 = "alpha";
                float[] v5_1 = new float[1];
                float v9 = arg9 ? 0.2f : 1f;
                v5_1[0] = v9;
                v1[1] = ObjectAnimator.ofFloat(v2_1, v3, v5_1);
                v0.playTogether(v1);
                this.this$1.colorChangeAnimation.setDuration(150);
                this.this$1.colorChangeAnimation.setInterpolator(this.decelerateInterpolator);
                this.this$1.colorChangeAnimation.start();
            }
        }

        class ListAdapter extends SelectionAdapter {
            private Context context;
            private int currentCount;
            private ArrayList items;
            private HashMap itemsMap;

            public ListAdapter(EditorAlert arg4, Context arg5, ThemeDescription[] arg6) {
                ArrayList v1_1;
                this.this$1 = arg4;
                super();
                this.items = new ArrayList();
                this.itemsMap = new HashMap();
                this.context = arg5;
                int v4;
                for(v4 = 0; v4 < arg6.length; ++v4) {
                    ThemeDescription v5 = arg6[v4];
                    String v0 = v5.getCurrentKey();
                    Object v1 = this.itemsMap.get(v0);
                    if(v1 == null) {
                        v1_1 = new ArrayList();
                        this.itemsMap.put(v0, v1_1);
                        this.items.add(v1_1);
                    }

                    v1_1.add(v5);
                }
            }

            public ArrayList getItem(int arg2) {
                if(arg2 >= 0) {
                    if(arg2 >= this.items.size()) {
                    }
                    else {
                        return this.items.get(arg2);
                    }
                }

                return null;
            }

            public int getItemCount() {
                return this.items.size();
            }

            public int getItemViewType(int arg1) {
                return 0;
            }

            public boolean isEnabled(ViewHolder arg1) {
                return 1;
            }

            public void onBindViewHolder(ViewHolder arg4, int arg5) {
                int v0 = 0;
                Object v5 = this.items.get(arg5).get(0);
                if(((ThemeDescription)v5).getCurrentKey().equals("chat_wallpaper")) {
                }
                else {
                    v0 = ((ThemeDescription)v5).getSetColor();
                }

                arg4.itemView.setTextAndColor(((ThemeDescription)v5).getTitle(), v0);
            }

            public ViewHolder onCreateViewHolder(ViewGroup arg3, int arg4) {
                TextColorThemeCell v3 = new TextColorThemeCell(this.context);
                ((View)v3).setLayoutParams(new LayoutParams(-1, -2));
                return new Holder(((View)v3));
            }
        }

        private boolean animationInProgress;
        private FrameLayout bottomLayout;
        private FrameLayout bottomSaveLayout;
        private TextView cancelButton;
        private AnimatorSet colorChangeAnimation;
        private ColorPicker colorPicker;
        private TextView defaultButtom;
        private boolean ignoreTextChange;
        private LinearLayoutManager layoutManager;
        private ListAdapter listAdapter;
        private RecyclerListView listView;
        private int previousScrollPosition;
        private TextView saveButton;
        private int scrollOffsetY;
        private View shadow;
        private Drawable shadowDrawable;
        private boolean startedColorChange;
        private int topBeforeSwitch;

        public EditorAlert(ThemeEditorView arg18, Context arg19, ThemeDescription[] arg20) {
            ThemeEditorView.this = arg18;
            super(arg19, true);
            this.shadowDrawable = arg19.getResources().getDrawable(2131231559).mutate();
            this.containerView = new FrameLayout(arg19, arg18) {
                private boolean ignoreLayout;

                protected void onDraw(Canvas arg6) {
                    EditorAlert.access$1300(this.this$1).setBounds(0, EditorAlert.access$700(this.this$1) - EditorAlert.access$1200(), this.getMeasuredWidth(), this.getMeasuredHeight());
                    EditorAlert.access$1300(this.this$1).draw(arg6);
                }

                public boolean onInterceptTouchEvent(MotionEvent arg3) {
                    if(arg3.getAction() == 0 && EditorAlert.access$700(this.this$1) != 0 && arg3.getY() < (((float)EditorAlert.access$700(this.this$1)))) {
                        this.this$1.dismiss();
                        return 1;
                    }

                    return super.onInterceptTouchEvent(arg3);
                }

                protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
                    super.onLayout(arg1, arg2, arg3, arg4, arg5);
                    EditorAlert.access$1100(this.this$1);
                }

                protected void onMeasure(int arg5, int arg6) {
                    int v0 = View$MeasureSpec.getSize(arg5);
                    arg6 = View$MeasureSpec.getSize(arg6);
                    if(Build$VERSION.SDK_INT >= 21) {
                        arg6 -= AndroidUtilities.statusBarHeight;
                    }

                    v0 = arg6 - Math.min(v0, arg6);
                    if(EditorAlert.access$800(this.this$1).getPaddingTop() != v0) {
                        this.ignoreLayout = true;
                        EditorAlert.access$800(this.this$1).getPaddingTop();
                        EditorAlert.access$800(this.this$1).setPadding(0, v0, 0, AndroidUtilities.dp(48f));
                        if(EditorAlert.access$900(this.this$1).getVisibility() == 0) {
                            EditorAlert.access$702(this.this$1, EditorAlert.access$800(this.this$1).getPaddingTop());
                            EditorAlert.access$800(this.this$1).setTopGlowOffset(EditorAlert.access$700(this.this$1));
                            EditorAlert.access$900(this.this$1).setTranslationY(((float)EditorAlert.access$700(this.this$1)));
                            EditorAlert.access$1002(this.this$1, 0);
                        }

                        this.ignoreLayout = false;
                    }

                    super.onMeasure(arg5, View$MeasureSpec.makeMeasureSpec(arg6, 1073741824));
                }

                public boolean onTouchEvent(MotionEvent arg2) {
                    boolean v2 = (this.this$1.isDismissed()) || !super.onTouchEvent(arg2) ? false : true;
                    return v2;
                }

                public void requestLayout() {
                    if(this.ignoreLayout) {
                        return;
                    }

                    super.requestLayout();
                }
            };
            this.containerView.setWillNotDraw(false);
            this.containerView.setPadding(EditorAlert.backgroundPaddingLeft, 0, EditorAlert.backgroundPaddingLeft, 0);
            this.listView = new RecyclerListView(arg19);
            this.listView.setPadding(0, 0, 0, AndroidUtilities.dp(48f));
            this.listView.setClipToPadding(false);
            RecyclerListView v4 = this.listView;
            LinearLayoutManager v6 = new LinearLayoutManager(this.getContext());
            this.layoutManager = v6;
            v4.setLayoutManager(((LayoutManager)v6));
            this.listView.setHorizontalScrollBarEnabled(false);
            this.listView.setVerticalScrollBarEnabled(false);
            this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
            v4 = this.listView;
            ListAdapter v6_1 = new ListAdapter(this, arg19, arg20);
            this.listAdapter = v6_1;
            v4.setAdapter(((Adapter)v6_1));
            this.listView.setGlowColor(-657673);
            this.listView.setItemAnimator(null);
            this.listView.setLayoutAnimation(null);
            this.listView.setOnItemClickListener(new OnItemClickListener(arg18) {
                public void onItemClick(View arg4, int arg5) {
                    this.this$1.this$0.currentThemeDesription = EditorAlert.access$1400(this.this$1).getItem(arg5);
                    this.this$1.this$0.currentThemeDesriptionPosition = arg5;
                    int v4;
                    for(v4 = 0; v4 < this.this$1.this$0.currentThemeDesription.size(); ++v4) {
                        Object v5 = this.this$1.this$0.currentThemeDesription.get(v4);
                        if(((ThemeDescription)v5).getCurrentKey().equals("chat_wallpaper")) {
                            this.this$1.this$0.wallpaperUpdater.showAlert(true);
                            return;
                        }

                        ((ThemeDescription)v5).startEditing();
                        if(v4 == 0) {
                            EditorAlert.access$900(this.this$1).setColor(((ThemeDescription)v5).getCurrentColor());
                        }
                    }

                    EditorAlert.access$1700(this.this$1, true);
                }
            });
            this.listView.setOnScrollListener(new OnScrollListener(arg18) {
                public void onScrolled(RecyclerView arg1, int arg2, int arg3) {
                    EditorAlert.access$1100(this.this$1);
                }
            });
            this.colorPicker = new ColorPicker(this, arg19);
            this.colorPicker.setVisibility(8);
            this.containerView.addView(this.colorPicker, LayoutHelper.createFrame(-1, -1, 1));
            this.shadow = new View(arg19);
            this.shadow.setBackgroundResource(2131231099);
            this.containerView.addView(this.shadow, LayoutHelper.createFrame(-1, 3f, 83, 0f, 0f, 0f, 48f));
            this.bottomSaveLayout = new FrameLayout(arg19);
            this.bottomSaveLayout.setBackgroundColor(-1);
            this.containerView.addView(this.bottomSaveLayout, LayoutHelper.createFrame(-1, 48, 83));
            TextView v4_1 = new TextView(arg19);
            v4_1.setTextSize(1, 14f);
            v4_1.setTextColor(-15095832);
            v4_1.setGravity(17);
            v4_1.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            v4_1.setPadding(AndroidUtilities.dp(18f), 0, AndroidUtilities.dp(18f), 0);
            v4_1.setText(LocaleController.getString("CloseEditor", 2131624440).toUpperCase());
            v4_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.bottomSaveLayout.addView(((View)v4_1), LayoutHelper.createFrame(-2, -1, 51));
            v4_1.setOnClickListener(new View$OnClickListener(arg18) {
                public void onClick(View arg1) {
                    this.this$1.dismiss();
                }
            });
            v4_1 = new TextView(arg19);
            v4_1.setTextSize(1, 14f);
            v4_1.setTextColor(-15095832);
            v4_1.setGravity(17);
            v4_1.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            v4_1.setPadding(AndroidUtilities.dp(18f), 0, AndroidUtilities.dp(18f), 0);
            v4_1.setText(LocaleController.getString("SaveTheme", 2131625942).toUpperCase());
            v4_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.bottomSaveLayout.addView(((View)v4_1), LayoutHelper.createFrame(-2, -1, 53));
            v4_1.setOnClickListener(new View$OnClickListener(arg18) {
                public void onClick(View arg2) {
                    Theme.saveCurrentTheme(this.this$1.this$0.currentThemeName, true);
                    this.this$1.setOnDismissListener(null);
                    this.this$1.dismiss();
                    this.this$1.this$0.close();
                }
            });
            this.bottomLayout = new FrameLayout(arg19);
            this.bottomLayout.setVisibility(8);
            this.bottomLayout.setBackgroundColor(-1);
            this.containerView.addView(this.bottomLayout, LayoutHelper.createFrame(-1, 48, 83));
            this.cancelButton = new TextView(arg19);
            this.cancelButton.setTextSize(1, 14f);
            this.cancelButton.setTextColor(-15095832);
            this.cancelButton.setGravity(17);
            this.cancelButton.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            this.cancelButton.setPadding(AndroidUtilities.dp(18f), 0, AndroidUtilities.dp(18f), 0);
            this.cancelButton.setText(LocaleController.getString("Cancel", 2131624257).toUpperCase());
            this.cancelButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.bottomLayout.addView(this.cancelButton, LayoutHelper.createFrame(-2, -1, 51));
            this.cancelButton.setOnClickListener(new View$OnClickListener(arg18) {
                public void onClick(View arg3) {
                    int v0;
                    for(v0 = 0; v0 < this.this$1.this$0.currentThemeDesription.size(); ++v0) {
                        this.this$1.this$0.currentThemeDesription.get(v0).setPreviousColor();
                    }

                    EditorAlert.access$1700(this.this$1, false);
                }
            });
            LinearLayout v4_2 = new LinearLayout(arg19);
            v4_2.setOrientation(0);
            this.bottomLayout.addView(((View)v4_2), LayoutHelper.createFrame(-2, -1, 53));
            this.defaultButtom = new TextView(arg19);
            this.defaultButtom.setTextSize(1, 14f);
            this.defaultButtom.setTextColor(-15095832);
            this.defaultButtom.setGravity(17);
            this.defaultButtom.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            this.defaultButtom.setPadding(AndroidUtilities.dp(18f), 0, AndroidUtilities.dp(18f), 0);
            this.defaultButtom.setText(LocaleController.getString("Default", 2131624559).toUpperCase());
            this.defaultButtom.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            v4_2.addView(this.defaultButtom, LayoutHelper.createFrame(-2, -1, 51));
            this.defaultButtom.setOnClickListener(new View$OnClickListener(arg18) {
                public void onClick(View arg3) {
                    int v0;
                    for(v0 = 0; v0 < this.this$1.this$0.currentThemeDesription.size(); ++v0) {
                        this.this$1.this$0.currentThemeDesription.get(v0).setDefaultColor();
                    }

                    EditorAlert.access$1700(this.this$1, false);
                }
            });
            TextView v6_2 = new TextView(arg19);
            v6_2.setTextSize(1, 14f);
            v6_2.setTextColor(-15095832);
            v6_2.setGravity(17);
            v6_2.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            v6_2.setPadding(AndroidUtilities.dp(18f), 0, AndroidUtilities.dp(18f), 0);
            v6_2.setText(LocaleController.getString("Save", 2131625938).toUpperCase());
            v6_2.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            v4_2.addView(((View)v6_2), LayoutHelper.createFrame(-2, -1, 51));
            v6_2.setOnClickListener(new View$OnClickListener(arg18) {
                public void onClick(View arg2) {
                    EditorAlert.access$1700(this.this$1, false);
                }
            });
        }

        static boolean access$000(EditorAlert arg0) {
            return arg0.ignoreTextChange;
        }

        static boolean access$002(EditorAlert arg0, boolean arg1) {
            arg0.ignoreTextChange = arg1;
            return arg1;
        }

        static int access$1002(EditorAlert arg0, int arg1) {
            arg0.previousScrollPosition = arg1;
            return arg1;
        }

        static void access$1100(EditorAlert arg0) {
            arg0.updateLayout();
        }

        static int access$1200() {
            return EditorAlert.backgroundPaddingTop;
        }

        static Drawable access$1300(EditorAlert arg0) {
            return arg0.shadowDrawable;
        }

        static ListAdapter access$1400(EditorAlert arg0) {
            return arg0.listAdapter;
        }

        static void access$1700(EditorAlert arg0, boolean arg1) {
            arg0.setColorPickerVisible(arg1);
        }

        static FrameLayout access$2000(EditorAlert arg0) {
            return arg0.bottomSaveLayout;
        }

        static boolean access$2102(EditorAlert arg0, boolean arg1) {
            arg0.animationInProgress = arg1;
            return arg1;
        }

        static FrameLayout access$2300(EditorAlert arg0) {
            return arg0.bottomLayout;
        }

        static boolean access$300(EditorAlert arg0) {
            return arg0.startedColorChange;
        }

        static boolean access$302(EditorAlert arg0, boolean arg1) {
            arg0.startedColorChange = arg1;
            return arg1;
        }

        static AnimatorSet access$400(EditorAlert arg0) {
            return arg0.colorChangeAnimation;
        }

        static AnimatorSet access$402(EditorAlert arg0, AnimatorSet arg1) {
            arg0.colorChangeAnimation = arg1;
            return arg1;
        }

        static ColorDrawable access$500(EditorAlert arg0) {
            return arg0.backDrawable;
        }

        static ViewGroup access$600(EditorAlert arg0) {
            return arg0.containerView;
        }

        static int access$700(EditorAlert arg0) {
            return arg0.scrollOffsetY;
        }

        static int access$702(EditorAlert arg0, int arg1) {
            arg0.scrollOffsetY = arg1;
            return arg1;
        }

        static RecyclerListView access$800(EditorAlert arg0) {
            return arg0.listView;
        }

        static ColorPicker access$900(EditorAlert arg0) {
            return arg0.colorPicker;
        }

        protected boolean canDismissWithSwipe() {
            return 0;
        }

        private int getCurrentTop() {
            if(this.listView.getChildCount() != 0) {
                int v1 = 0;
                View v0 = this.listView.getChildAt(0);
                ViewHolder v2 = this.listView.findContainingViewHolder(v0);
                if(v2 != null) {
                    int v3 = this.listView.getPaddingTop();
                    if(((Holder)v2).getAdapterPosition() == 0 && v0.getTop() >= 0) {
                        v1 = v0.getTop();
                    }

                    return v3 - v1;
                }
            }

            return -1000;
        }

        public int getScrollOffsetY() {
            return this.scrollOffsetY;
        }

        private void setColorPickerVisible(boolean arg14) {
            Animator[] v5_1;
            AnimatorSet v14;
            long v0 = 150;
            int v2 = 4;
            int v3 = 3;
            int v4 = 2;
            int v5 = 5;
            float v6 = 1f;
            if(arg14) {
                this.animationInProgress = true;
                this.colorPicker.setVisibility(0);
                this.bottomLayout.setVisibility(0);
                this.colorPicker.setAlpha(0f);
                this.bottomLayout.setAlpha(0f);
                v14 = new AnimatorSet();
                v5_1 = new Animator[v5];
                v5_1[0] = ObjectAnimator.ofFloat(this.colorPicker, "alpha", new float[]{v6});
                v5_1[1] = ObjectAnimator.ofFloat(this.bottomLayout, "alpha", new float[]{v6});
                v5_1[v4] = ObjectAnimator.ofFloat(this.listView, "alpha", new float[]{0f});
                v5_1[v3] = ObjectAnimator.ofFloat(this.bottomSaveLayout, "alpha", new float[]{0f});
                v5_1[v2] = ObjectAnimator.ofInt(this, "scrollOffsetY", new int[]{this.listView.getPaddingTop()});
                v14.playTogether(v5_1);
                v14.setDuration(v0);
                v14.setInterpolator(ThemeEditorView.this.decelerateInterpolator);
                v14.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        this.this$1.listView.setVisibility(4);
                        this.this$1.bottomSaveLayout.setVisibility(4);
                        this.this$1.animationInProgress = false;
                    }
                });
                v14.start();
                this.previousScrollPosition = this.scrollOffsetY;
            }
            else {
                if(ThemeEditorView.this.parentActivity != null) {
                    ThemeEditorView.this.parentActivity.rebuildAllFragments(false);
                }

                Theme.saveCurrentTheme(ThemeEditorView.this.currentThemeName, false);
                AndroidUtilities.hideKeyboard(this.getCurrentFocus());
                this.animationInProgress = true;
                this.listView.setVisibility(0);
                this.bottomSaveLayout.setVisibility(0);
                this.listView.setAlpha(0f);
                v14 = new AnimatorSet();
                v5_1 = new Animator[v5];
                v5_1[0] = ObjectAnimator.ofFloat(this.colorPicker, "alpha", new float[]{0f});
                v5_1[1] = ObjectAnimator.ofFloat(this.bottomLayout, "alpha", new float[]{0f});
                v5_1[v4] = ObjectAnimator.ofFloat(this.listView, "alpha", new float[]{v6});
                v5_1[v3] = ObjectAnimator.ofFloat(this.bottomSaveLayout, "alpha", new float[]{v6});
                v5_1[v2] = ObjectAnimator.ofInt(this, "scrollOffsetY", new int[]{this.previousScrollPosition});
                v14.playTogether(v5_1);
                v14.setDuration(v0);
                v14.setInterpolator(ThemeEditorView.this.decelerateInterpolator);
                v14.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        this.this$1.colorPicker.setVisibility(8);
                        this.this$1.bottomLayout.setVisibility(8);
                        this.this$1.animationInProgress = false;
                    }
                });
                v14.start();
                this.listAdapter.notifyItemChanged(ThemeEditorView.this.currentThemeDesriptionPosition);
            }
        }

        @Keep public void setScrollOffsetY(int arg2) {
            RecyclerListView v0 = this.listView;
            this.scrollOffsetY = arg2;
            v0.setTopGlowOffset(arg2);
            this.colorPicker.setTranslationY(((float)this.scrollOffsetY));
            this.containerView.invalidate();
        }

        @SuppressLint(value={"NewApi"}) private void updateLayout() {
            if(this.listView.getChildCount() > 0 && this.listView.getVisibility() == 0) {
                if(this.animationInProgress) {
                }
                else {
                    View v0 = this.listView.getChildAt(0);
                    ViewHolder v2 = this.listView.findContainingViewHolder(v0);
                    int v0_1 = this.listView.getVisibility() != 0 || (this.animationInProgress) ? this.listView.getPaddingTop() : v0.getTop() - AndroidUtilities.dp(8f);
                    if(v0_1 <= 0 || v2 == null || ((Holder)v2).getAdapterPosition() != 0) {
                        v0_1 = 0;
                    }
                    else {
                    }

                    if(this.scrollOffsetY == v0_1) {
                        return;
                    }

                    this.setScrollOffsetY(v0_1);
                }
            }
        }
    }

    @SuppressLint(value={"StaticFieldLeak"}) private static volatile ThemeEditorView Instance;
    private ArrayList currentThemeDesription;
    private int currentThemeDesriptionPosition;
    private String currentThemeName;
    private DecelerateInterpolator decelerateInterpolator;
    private EditorAlert editorAlert;
    private final int editorHeight;
    private final int editorWidth;
    private boolean hidden;
    private Activity parentActivity;
    private SharedPreferences preferences;
    private WallpaperUpdater wallpaperUpdater;
    private WindowManager$LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    private FrameLayout windowView;

    static {
    }

    public ThemeEditorView() {
        super();
        this.editorWidth = AndroidUtilities.dp(54f);
        this.editorHeight = AndroidUtilities.dp(54f);
    }

    static int access$1500(ThemeEditorView arg0) {
        return arg0.currentThemeDesriptionPosition;
    }

    static int access$1502(ThemeEditorView arg0, int arg1) {
        arg0.currentThemeDesriptionPosition = arg1;
        return arg1;
    }

    static WallpaperUpdater access$1600(ThemeEditorView arg0) {
        return arg0.wallpaperUpdater;
    }

    static String access$1800(ThemeEditorView arg0) {
        return arg0.currentThemeName;
    }

    static DecelerateInterpolator access$1900(ThemeEditorView arg0) {
        return arg0.decelerateInterpolator;
    }

    static ArrayList access$200(ThemeEditorView arg0) {
        return arg0.currentThemeDesription;
    }

    static ArrayList access$202(ThemeEditorView arg0, ArrayList arg1) {
        arg0.currentThemeDesription = arg1;
        return arg1;
    }

    static Activity access$2200(ThemeEditorView arg0) {
        return arg0.parentActivity;
    }

    static EditorAlert access$2400(ThemeEditorView arg0) {
        return arg0.editorAlert;
    }

    static EditorAlert access$2402(ThemeEditorView arg0, EditorAlert arg1) {
        arg0.editorAlert = arg1;
        return arg1;
    }

    static void access$2500(ThemeEditorView arg0) {
        arg0.show();
    }

    static void access$2600(ThemeEditorView arg0) {
        arg0.hide();
    }

    static WindowManager$LayoutParams access$2700(ThemeEditorView arg0) {
        return arg0.windowLayoutParams;
    }

    static int access$2800(ThemeEditorView arg0) {
        return arg0.editorWidth;
    }

    static FrameLayout access$2900(ThemeEditorView arg0) {
        return arg0.windowView;
    }

    static WindowManager access$3000(ThemeEditorView arg0) {
        return arg0.windowManager;
    }

    static void access$3100(ThemeEditorView arg0) {
        arg0.animateToBoundsMaybe();
    }

    private void animateToBoundsMaybe() {
        ObjectAnimator v5_1;
        ArrayList v4_2;
        int[] v9;
        String v4_1;
        ArrayList v0_1;
        int v0 = ThemeEditorView.getSideCoord(true, 0, 0f, this.editorWidth);
        int v4 = ThemeEditorView.getSideCoord(true, 1, 0f, this.editorWidth);
        int v5 = ThemeEditorView.getSideCoord(false, 0, 0f, this.editorHeight);
        int v6 = ThemeEditorView.getSideCoord(false, 1, 0f, this.editorHeight);
        SharedPreferences$Editor v7 = this.preferences.edit();
        int v8 = AndroidUtilities.dp(20f);
        int v10 = 2;
        float v11 = 1f;
        if(Math.abs(v0 - this.windowLayoutParams.x) > v8) {
            if(this.windowLayoutParams.x < 0 && this.windowLayoutParams.x > -this.editorWidth / 4) {
                goto label_112;
            }

            if(Math.abs(v4 - this.windowLayoutParams.x) > v8) {
                if(this.windowLayoutParams.x > AndroidUtilities.displaySize.x - this.editorWidth && this.windowLayoutParams.x < AndroidUtilities.displaySize.x - this.editorWidth / 4 * 3) {
                    goto label_93;
                }

                if(this.windowView.getAlpha() != v11) {
                    v0_1 = new ArrayList();
                    if(this.windowLayoutParams.x < 0) {
                        v4_1 = "x";
                        v9 = new int[]{-this.editorWidth};
                    }
                    else {
                        v4_1 = "x";
                        v9 = new int[]{AndroidUtilities.displaySize.x};
                    }

                    v0_1.add(ObjectAnimator.ofInt(this, v4_1, v9));
                    v4_2 = v0_1;
                    v0 = 1;
                    goto label_131;
                }

                v7.putFloat("px", (((float)(this.windowLayoutParams.x - v0))) / (((float)(v4 - v0))));
                v7.putInt("sidex", v10);
                v0_1 = null;
            }
            else {
            label_93:
                v0_1 = new ArrayList();
                v7.putInt("sidex", 1);
                if(this.windowView.getAlpha() != v11) {
                    v0_1.add(ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{v11}));
                }

                v0_1.add(ObjectAnimator.ofInt(this, "x", new int[]{v4}));
            }

            v4_2 = v0_1;
            goto label_130;
        }
        else {
        label_112:
            v4_2 = new ArrayList();
            v7.putInt("sidex", 0);
            if(this.windowView.getAlpha() != v11) {
                v4_2.add(ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{v11}));
            }

            v4_2.add(ObjectAnimator.ofInt(this, "x", new int[]{v0}));
        label_130:
            v0 = 0;
        }

    label_131:
        if(v0 == 0) {
            if(Math.abs(v5 - this.windowLayoutParams.y) <= v8 || this.windowLayoutParams.y <= ActionBar.getCurrentActionBarHeight()) {
                if(v4_2 == null) {
                    v4_2 = new ArrayList();
                }

                v7.putInt("sidey", 0);
                v5_1 = ObjectAnimator.ofInt(this, "y", new int[]{v5});
            label_178:
                v4_2.add(v5_1);
            }
            else if(Math.abs(v6 - this.windowLayoutParams.y) <= v8) {
                if(v4_2 == null) {
                    v4_2 = new ArrayList();
                }

                v7.putInt("sidey", 1);
                v5_1 = ObjectAnimator.ofInt(this, "y", new int[]{v6});
                goto label_178;
            }
            else {
                v7.putFloat("py", (((float)(this.windowLayoutParams.y - v5))) / (((float)(v6 - v5))));
                v7.putInt("sidey", v10);
            }

            v7.commit();
        }

        if(v4_2 != null) {
            if(this.decelerateInterpolator == null) {
                this.decelerateInterpolator = new DecelerateInterpolator();
            }

            AnimatorSet v5_2 = new AnimatorSet();
            v5_2.setInterpolator(this.decelerateInterpolator);
            v5_2.setDuration(150);
            if(v0 != 0) {
                v4_2.add(ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{0f}));
                v5_2.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        Theme.saveCurrentTheme(ThemeEditorView.this.currentThemeName, true);
                        ThemeEditorView.this.destroy();
                    }
                });
            }

            v5_2.playTogether(((Collection)v4_2));
            v5_2.start();
        }
    }

    public void close() {
        try {
            this.windowManager.removeView(this.windowView);
            goto label_3;
        }
        catch(Exception ) {
        label_3:
            this.parentActivity = null;
            return;
        }
    }

    public void destroy() {
        this.wallpaperUpdater.cleanup();
        if(this.parentActivity != null && this.windowView != null) {
            FrameLayout v0 = null;
            try {
                this.windowManager.removeViewImmediate(this.windowView);
                this.windowView = v0;
            }
            catch(Exception v1) {
                FileLog.e(((Throwable)v1));
            }

            try {
                if(this.editorAlert == null) {
                    goto label_23;
                }

                this.editorAlert.dismiss();
                this.editorAlert = ((EditorAlert)v0);
            }
            catch(Exception v1) {
                FileLog.e(((Throwable)v1));
            }

        label_23:
            this.parentActivity = ((Activity)v0);
            ThemeEditorView.Instance = ((ThemeEditorView)v0);
        }
    }

    public static ThemeEditorView getInstance() {
        return ThemeEditorView.Instance;
    }

    private static int getSideCoord(boolean arg2, int arg3, float arg4, int arg5) {
        int v0;
        if(arg2) {
            v0 = AndroidUtilities.displaySize.x;
        }
        else {
            v0 = AndroidUtilities.displaySize.y - arg5;
            arg5 = ActionBar.getCurrentActionBarHeight();
        }

        v0 -= arg5;
        float v5 = 10f;
        if(arg3 == 0) {
            arg3 = AndroidUtilities.dp(v5);
        }
        else if(arg3 == 1) {
            arg3 = v0 - AndroidUtilities.dp(v5);
        }
        else {
            arg3 = Math.round((((float)(v0 - AndroidUtilities.dp(20f)))) * arg4) + AndroidUtilities.dp(v5);
        }

        if(!arg2) {
            arg3 += ActionBar.getCurrentActionBarHeight();
        }

        return arg3;
    }

    public int getX() {
        return this.windowLayoutParams.x;
    }

    public int getY() {
        return this.windowLayoutParams.y;
    }

    private void hide() {
        if(this.parentActivity == null) {
            return;
        }

        try {
            AnimatorSet v0 = new AnimatorSet();
            v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{1f, 0f}), ObjectAnimator.ofFloat(this.windowView, "scaleX", new float[]{1f, 0f}), ObjectAnimator.ofFloat(this.windowView, "scaleY", new float[]{1f, 0f})});
            v0.setInterpolator(this.decelerateInterpolator);
            v0.setDuration(150);
            v0.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    if(ThemeEditorView.this.windowView != null) {
                        ThemeEditorView.this.windowManager.removeView(ThemeEditorView.this.windowView);
                    }
                }
            });
            v0.start();
            this.hidden = true;
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public void onActivityResult(int arg2, int arg3, Intent arg4) {
        if(this.wallpaperUpdater != null) {
            this.wallpaperUpdater.onActivityResult(arg2, arg3, arg4);
        }
    }

    public void onConfigurationChanged() {
        int v0 = this.preferences.getInt("sidex", 1);
        int v1 = this.preferences.getInt("sidey", 0);
        float v3 = this.preferences.getFloat("px", 0f);
        float v5 = this.preferences.getFloat("py", 0f);
        this.windowLayoutParams.x = ThemeEditorView.getSideCoord(true, v0, v3, this.editorWidth);
        this.windowLayoutParams.y = ThemeEditorView.getSideCoord(false, v1, v5, this.editorHeight);
        try {
            if(this.windowView.getParent() == null) {
                return;
            }

            this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    @Keep public void setX(int arg3) {
        this.windowLayoutParams.x = arg3;
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
    }

    @Keep public void setY(int arg3) {
        this.windowLayoutParams.y = arg3;
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
    }

    private void show() {
        if(this.parentActivity == null) {
            return;
        }

        try {
            this.windowManager.addView(this.windowView, this.windowLayoutParams);
            this.hidden = false;
            this.showWithAnimation();
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public void show(Activity arg9, String arg10) {
        if(ThemeEditorView.Instance != null) {
            ThemeEditorView.Instance.destroy();
        }

        this.hidden = false;
        this.currentThemeName = arg10;
        this.windowView = new FrameLayout(((Context)arg9)) {
            private boolean dragging;
            private float startX;
            private float startY;

            public boolean onInterceptTouchEvent(MotionEvent arg1) {
                return 1;
            }

            public boolean onTouchEvent(MotionEvent arg11) {
                WindowManager$LayoutParams v2_3;
                ActionBarLayout v6_1;
                float v6;
                float v0 = arg11.getRawX();
                float v1 = arg11.getRawY();
                int v3 = 2;
                if(arg11.getAction() != 0) {
                    if(arg11.getAction() == v3 && !this.dragging) {
                        v6 = 0.3f;
                        if(Math.abs(this.startX - v0) < AndroidUtilities.getPixelsInCM(v6, true) && Math.abs(this.startY - v1) < AndroidUtilities.getPixelsInCM(v6, false)) {
                            goto label_89;
                        }

                        this.dragging = true;
                        goto label_7;
                    }

                    goto label_27;
                }
                else {
                label_7:
                    this.startX = v0;
                    this.startY = v1;
                    goto label_89;
                label_27:
                    if(arg11.getAction() != 1) {
                        goto label_89;
                    }

                    if(this.dragging) {
                        goto label_89;
                    }

                    if(ThemeEditorView.this.editorAlert != null) {
                        goto label_89;
                    }

                    Activity v2 = ThemeEditorView.this.parentActivity;
                    Object v7 = null;
                    if(AndroidUtilities.isTablet()) {
                        v6_1 = ((LaunchActivity)v2).getLayersActionBarLayout();
                        if(v6_1 != null && (v6_1.fragmentsStack.isEmpty())) {
                            v6_1 = ((ActionBarLayout)v7);
                        }

                        if(v6_1 != null) {
                            goto label_52;
                        }

                        v6_1 = ((LaunchActivity)v2).getRightActionBarLayout();
                        if(v6_1 == null) {
                            goto label_52;
                        }

                        if(!v6_1.fragmentsStack.isEmpty()) {
                            goto label_52;
                        }

                        goto label_51;
                    }
                    else {
                    label_51:
                        v6_1 = ((ActionBarLayout)v7);
                    }

                label_52:
                    if(v6_1 == null) {
                        v6_1 = ((LaunchActivity)v2).getActionBarLayout();
                    }

                    if(v6_1 == null) {
                        goto label_89;
                    }

                    if(!v6_1.fragmentsStack.isEmpty()) {
                        v7 = v6_1.fragmentsStack.get(v6_1.fragmentsStack.size() - 1);
                    }

                    if(v7 == null) {
                        goto label_89;
                    }

                    ThemeDescription[] v2_1 = ((BaseFragment)v7).getThemeDescriptions();
                    if(v2_1 == null) {
                        goto label_89;
                    }

                    ThemeEditorView.this.editorAlert = new EditorAlert(ThemeEditorView.this, ThemeEditorView.this.parentActivity, v2_1);
                    ThemeEditorView.this.editorAlert.setOnDismissListener(new DialogInterface$OnDismissListener() {
                        public void onDismiss(DialogInterface arg1) {
                        }
                    });
                    ThemeEditorView.this.editorAlert.setOnDismissListener(new DialogInterface$OnDismissListener() {
                        public void onDismiss(DialogInterface arg2) {
                            this.this$1.this$0.editorAlert = null;
                            this.this$1.this$0.show();
                        }
                    });
                    ThemeEditorView.this.editorAlert.show();
                    ThemeEditorView.this.hide();
                }

            label_89:
                if(this.dragging) {
                    if(arg11.getAction() == v3) {
                        float v11 = v0 - this.startX;
                        float v2_2 = v1 - this.startY;
                        WindowManager$LayoutParams v6_2 = ThemeEditorView.this.windowLayoutParams;
                        v6_2.x = ((int)((((float)v6_2.x)) + v11));
                        WindowManager$LayoutParams v11_1 = ThemeEditorView.this.windowLayoutParams;
                        ((WindowManager$LayoutParams)v11).y = ((int)((((float)((WindowManager$LayoutParams)v11).y)) + v2_2));
                        int v11_2 = ThemeEditorView.this.editorWidth / v3;
                        v3 = -v11_2;
                        if(ThemeEditorView.this.windowLayoutParams.x < v3) {
                            v2_3 = ThemeEditorView.this.windowLayoutParams;
                            goto label_121;
                        }
                        else if(ThemeEditorView.this.windowLayoutParams.x > AndroidUtilities.displaySize.x - ThemeEditorView.this.windowLayoutParams.width + v11_2) {
                            v2_3 = ThemeEditorView.this.windowLayoutParams;
                            v3 = AndroidUtilities.displaySize.x - ThemeEditorView.this.windowLayoutParams.width + v11_2;
                        label_121:
                            v2_3.x = v3;
                        }

                        float v3_1 = 0.5f;
                        v6 = 1f;
                        if(ThemeEditorView.this.windowLayoutParams.x < 0) {
                            v6 += (((float)ThemeEditorView.this.windowLayoutParams.x)) / (((float)v11_2)) * v3_1;
                        }
                        else if(ThemeEditorView.this.windowLayoutParams.x > AndroidUtilities.displaySize.x - ThemeEditorView.this.windowLayoutParams.width) {
                            v6 -= (((float)(ThemeEditorView.this.windowLayoutParams.x - AndroidUtilities.displaySize.x + ThemeEditorView.this.windowLayoutParams.width))) / (((float)v11_2)) * v3_1;
                        }

                        if(ThemeEditorView.this.windowView.getAlpha() != v6) {
                            ThemeEditorView.this.windowView.setAlpha(v6);
                        }

                        if(ThemeEditorView.this.windowLayoutParams.y < 0) {
                            ThemeEditorView.this.windowLayoutParams.y = 0;
                        }
                        else if(ThemeEditorView.this.windowLayoutParams.y > AndroidUtilities.displaySize.y - ThemeEditorView.this.windowLayoutParams.height) {
                            ThemeEditorView.this.windowLayoutParams.y = AndroidUtilities.displaySize.y - ThemeEditorView.this.windowLayoutParams.height;
                        }

                        ThemeEditorView.this.windowManager.updateViewLayout(ThemeEditorView.this.windowView, ThemeEditorView.this.windowLayoutParams);
                        this.startX = v0;
                        this.startY = v1;
                    }
                    else {
                        if(arg11.getAction() != 1) {
                            return 1;
                        }

                        this.dragging = false;
                        ThemeEditorView.this.animateToBoundsMaybe();
                    }
                }

                return 1;
            }
        };
        this.windowView.setBackgroundResource(2131231651);
        this.windowManager = arg9.getSystemService("window");
        this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        int v1 = this.preferences.getInt("sidex", 1);
        int v2 = this.preferences.getInt("sidey", 0);
        float v4 = this.preferences.getFloat("px", 0f);
        float v5 = this.preferences.getFloat("py", 0f);
        try {
            this.windowLayoutParams = new WindowManager$LayoutParams();
            this.windowLayoutParams.width = this.editorWidth;
            this.windowLayoutParams.height = this.editorHeight;
            this.windowLayoutParams.x = ThemeEditorView.getSideCoord(true, v1, v4, this.editorWidth);
            this.windowLayoutParams.y = ThemeEditorView.getSideCoord(false, v2, v5, this.editorHeight);
            this.windowLayoutParams.format = -3;
            this.windowLayoutParams.gravity = 51;
            this.windowLayoutParams.type = 99;
            this.windowLayoutParams.flags = 16777736;
            this.windowManager.addView(this.windowView, this.windowLayoutParams);
        }
        catch(Exception v9) {
            FileLog.e(((Throwable)v9));
            return;
        }

        this.wallpaperUpdater = new WallpaperUpdater(arg9, new WallpaperUpdaterDelegate(arg10) {
            public void didSelectWallpaper(File arg2, Bitmap arg3) {
                Theme.setThemeWallpaper(this.val$themeName, arg3, arg2);
            }

            public void needOpenColorPicker() {
                int v0;
                for(v0 = 0; v0 < ThemeEditorView.this.currentThemeDesription.size(); ++v0) {
                    Object v1 = ThemeEditorView.this.currentThemeDesription.get(v0);
                    ((ThemeDescription)v1).startEditing();
                    if(v0 == 0) {
                        EditorAlert.access$900(ThemeEditorView.this.editorAlert).setColor(((ThemeDescription)v1).getCurrentColor());
                    }
                }

                EditorAlert.access$1700(ThemeEditorView.this.editorAlert, true);
            }
        });
        ThemeEditorView.Instance = this;
        this.parentActivity = arg9;
        this.showWithAnimation();
    }

    private void showWithAnimation() {
        AnimatorSet v0 = new AnimatorSet();
        v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.windowView, "alpha", new float[]{0f, 1f}), ObjectAnimator.ofFloat(this.windowView, "scaleX", new float[]{0f, 1f}), ObjectAnimator.ofFloat(this.windowView, "scaleY", new float[]{0f, 1f})});
        v0.setInterpolator(this.decelerateInterpolator);
        v0.setDuration(150);
        v0.start();
    }
}

