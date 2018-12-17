package org.telegram.ui;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.net.sip.SipProfile;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.d.b;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils$TruncateAt;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View$OnClickListener;
import android.view.View$OnLongClickListener;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar$OnRatingBarChangeListener;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.a.f;
import de.hdodenhof.circleimageview.CircleImageView;
import f.l;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import okhttp3.aa;
import org.telegram.customization.Activities.SlsVoIPFeedbackActivity;
import org.telegram.customization.Activities.e;
import org.telegram.customization.Model.CallRequestModel;
import org.telegram.customization.g.d;
import org.telegram.customization.i.h;
import org.telegram.customization.j.a.a;
import org.telegram.customization.voip.LinphoneSipWrapper$SipCallback;
import org.telegram.customization.voip.LinphoneSipWrapper$SipManagerState;
import org.telegram.customization.voip.LinphoneSipWrapper;
import org.telegram.customization.voip.linphoneSip.linphone.PhoneServiceCallback;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji$EmojiDrawable;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver$ImageReceiverDelegate;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.EncryptionKeyEmojifier;
import org.telegram.messenger.voip.SlsVoIPService;
import org.telegram.messenger.voip.VoIPBaseService$StateListener;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$TL_encryptedChat;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CorrectlyMeasuringTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.IdenticonDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.voip.CallSwipeView$Listener;
import org.telegram.ui.Components.voip.CallSwipeView;
import org.telegram.ui.Components.voip.CheckableImageView;
import org.telegram.ui.Components.voip.FabBackgroundDrawable;
import org.telegram.ui.Components.voip.VoIPHelper;
import utils.view.FarsiButton;
import utils.view.FarsiEditText;
import utils.view.FarsiTextView;
import utils.view.RoundedImageView;
import utils.view.ToastUtil;

public class SlsVoIPActivity extends e implements d, NotificationCenterDelegate, StateListener {
    class org.telegram.ui.SlsVoIPActivity$33 extends h {
        org.telegram.ui.SlsVoIPActivity$33(SlsVoIPActivity arg1) {
            SlsVoIPActivity.this = arg1;
            super();
        }

        public void callRequestFailure(Object arg1, aa arg2, Object arg3, l arg4) {
            ToastUtil.a(SlsVoIPActivity.this.getApplicationContext(), "User is busy").show();
            SlsVoIPService.getSharedInstance().publicCallEnded();
            try {
                Log.d("slsCall callrequest", "callRequestFailure " + arg1.toString());
            }
            catch(Exception v1) {
                v1.printStackTrace();
            }

            SlsVoIPActivity.this.finish();
        }

        public void callRequestResult(CallRequestModel arg1, aa arg2, Object arg3, l arg4) {
            SlsVoIPActivity.this.callRequestModel = arg1;
            Log.d("slsCall callrequest", "callRequestResult " + arg4.a() + "\n" + arg4.c().toString());
            SlsVoIPActivity.this.sipWrapper.setCallRequestModel(SlsVoIPActivity.this.callRequestModel);
            LinphoneSipWrapper.callId = SlsVoIPActivity.this.callRequestModel.getCallId();
            if(SlsVoIPActivity.this.callRequestModel != null && (SlsVoIPActivity.this.callRequestModel.getCalleePresence().contentEquals("ONLINE"))) {
                Log.d("LEE", "CallerInfo" + new f().a(SlsVoIPActivity.this.callRequestModel));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        this.this$1.this$0.sipWrapper.endCall(false, false);
                        this.this$1.this$0.sipWrapper.register(this.this$1.this$0.callRequestModel.getDomain(), this.this$1.this$0.callRequestModel.getPort(), this.this$1.this$0.callRequestModel.getCallerUser(), this.this$1.this$0.callRequestModel.getCallerPassword());
                    }
                }, 500);
            }
        }
    }

    class org.telegram.ui.SlsVoIPActivity$3 implements SipCallback {
        volatile boolean isCallPlanned;

        org.telegram.ui.SlsVoIPActivity$3(SlsVoIPActivity arg1) {
            SlsVoIPActivity.this = arg1;
            super();
            this.isCallPlanned = false;
        }

        public void hideAnswer() {
        }

        public void onSipManagerStateChanged(SipManagerState arg4) {
            if(!SlsVoIPActivity.this.sipWrapper.isInCall() && (SlsVoIPActivity.this.amICaller) && SlsVoIPActivity.this.callRequestModel != null && !this.isCallPlanned) {
                this.isCallPlanned = true;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        this.this$1.this$0.sipWrapper.call(this.this$1.this$0.callRequestModel.getCalleeUser(), this.this$1.this$0.callRequestModel.getDomain(), this.this$1.this$0.callRequestModel.getPort());
                        this.this$1.isCallPlanned = false;
                    }
                }, 2000);
            }
        }

        public void onSipStateChanged(int arg1, int arg2) {
            SlsVoIPActivity.this.onStateChanged(arg2);
        }

        public void showAnswer(SipProfile arg1) {
        }

        public void showCallBtn() {
            Log.d("LEE", "showCallBtn");
        }

        public void showEndCallBtn() {
        }
    }

    class SignalBarsDrawable extends Drawable {
        private int[] barHeights;
        private int offsetStart;
        private Paint paint;
        private RectF rect;

        SignalBarsDrawable(SlsVoIPActivity arg1, org.telegram.ui.SlsVoIPActivity$1 arg2) {
            this(arg1);
        }

        private SignalBarsDrawable(SlsVoIPActivity arg4) {
            SlsVoIPActivity.this = arg4;
            super();
            this.barHeights = new int[]{AndroidUtilities.dp(3f), AndroidUtilities.dp(6f), AndroidUtilities.dp(9f), AndroidUtilities.dp(12f)};
            this.paint = new Paint(1);
            this.rect = new RectF();
            this.offsetStart = 6;
        }

        public void draw(Canvas arg9) {
            if(SlsVoIPActivity.this.callState != 3 && SlsVoIPActivity.this.callState != 5) {
                return;
            }

            this.paint.setColor(-1);
            int v0 = this.getBounds().left;
            float v1 = LocaleController.isRTL ? 0f : ((float)this.offsetStart);
            v0 += AndroidUtilities.dp(v1);
            int v1_1 = this.getBounds().top;
            int v2;
            for(v2 = 0; v2 < 4; v2 = v4) {
                Paint v3 = this.paint;
                int v4 = v2 + 1;
                int v5 = v4 <= SlsVoIPActivity.this.signalBarsCount ? 242 : 102;
                v3.setAlpha(v5);
                this.rect.set(((float)(AndroidUtilities.dp(((float)(v2 * 4))) + v0)), ((float)(this.getIntrinsicHeight() + v1_1 - this.barHeights[v2])), ((float)(AndroidUtilities.dp(4f) * v2 + v0 + AndroidUtilities.dp(3f))), ((float)(this.getIntrinsicHeight() + v1_1)));
                arg9.drawRoundRect(this.rect, ((float)AndroidUtilities.dp(0.3f)), ((float)AndroidUtilities.dp(0.3f)), this.paint);
            }
        }

        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(12f);
        }

        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(((float)(this.offsetStart + 15)));
        }

        public int getOpacity() {
            return -3;
        }

        public void setAlpha(int arg1) {
        }

        public void setColorFilter(ColorFilter arg1) {
        }
    }

    class TextAlphaSpan extends CharacterStyle {
        private int alpha;

        public TextAlphaSpan(SlsVoIPActivity arg1) {
            SlsVoIPActivity.this = arg1;
            super();
            this.alpha = 0;
        }

        public int getAlpha() {
            return this.alpha;
        }

        public void setAlpha(int arg1) {
            this.alpha = arg1;
            SlsVoIPActivity.this.stateText.invalidate();
            SlsVoIPActivity.this.stateText2.invalidate();
        }

        public void updateDrawState(TextPaint arg2) {
            arg2.setAlpha(this.alpha);
        }
    }

    private static final String TAG = "tg-voip-ui";
    private View acceptBtn;
    private CallSwipeView acceptSwipe;
    boolean amICaller;
    private ImageView blurOverlayView1;
    private ImageView blurOverlayView2;
    private Bitmap blurredPhoto1;
    private Bitmap blurredPhoto2;
    private TextView brandingText;
    CallRequestModel callRequestModel;
    private int callState;
    private View cancelBtn;
    private ImageView chatBtn;
    private FrameLayout content;
    private Animator currentAcceptAnim;
    private Animator currentDeclineAnim;
    private View declineBtn;
    private CallSwipeView declineSwipe;
    RatingBar dialogCallRatingBar;
    FarsiTextView dialogCallerName;
    FarsiEditText dialogExplanation;
    CircleImageView dialogProfilePic;
    FarsiTextView dialogRateStatus;
    FarsiButton dialogSubmitRate;
    private boolean didAcceptFromHere;
    boolean didAnswered;
    private TextView durationText;
    private AnimatorSet ellAnimator;
    private TextAlphaSpan[] ellSpans;
    private AnimatorSet emojiAnimator;
    boolean emojiExpanded;
    private TextView emojiExpandedText;
    boolean emojiTooltipVisible;
    private LinearLayout emojiWrap;
    private View endBtn;
    private FabBackgroundDrawable endBtnBg;
    private View endBtnIcon;
    Dialog endCalllDialog;
    EditText etMessage;
    private boolean firstStateChange;
    private boolean firsttime;
    private TextView hintTextView;
    private boolean isIncomingWaiting;
    RoundedImageView ivMapLocation;
    private ImageView[] keyEmojiViews;
    private boolean keyEmojiVisible;
    private String lastStateText;
    LinearLayout llExtraDataContainer;
    Boolean mHasEarpiece;
    private CheckableImageView micToggle;
    private TextView nameText;
    private h onResult;
    private BackupImageView photoView;
    private AnimatorSet retryAnim;
    private boolean retrying;
    private int signalBarsCount;
    private SignalBarsDrawable signalBarsDrawable;
    SipCallback sipCallback;
    private CheckableImageView spkToggle;
    private TextView stateText;
    private TextView stateText2;
    private LinearLayout swipeViewsWrap;
    private Animator textChangingAnim;
    private Animator tooltipAnim;
    private Runnable tooltipHider;
    private User user;

    public SlsVoIPActivity() {
        super();
        this.firsttime = false;
        this.firstStateChange = true;
        this.didAcceptFromHere = false;
        this.keyEmojiViews = new ImageView[4];
        this.amICaller = false;
        this.didAnswered = false;
        this.sipCallback = new org.telegram.ui.SlsVoIPActivity$3(this);
        this.mHasEarpiece = Boolean.TRUE;
        this.onResult = new org.telegram.ui.SlsVoIPActivity$33(this);
    }

    static void access$000(SlsVoIPActivity arg0, Bitmap arg1) {
        arg0.updateBlurredPhotos(arg1);
    }

    static boolean access$100(SlsVoIPActivity arg0) {
        return arg0.retrying;
    }

    static void access$1000(SlsVoIPActivity arg0) {
        arg0.callAccepted();
    }

    static Animator access$1100(SlsVoIPActivity arg0) {
        return arg0.currentDeclineAnim;
    }

    static Animator access$1102(SlsVoIPActivity arg0, Animator arg1) {
        arg0.currentDeclineAnim = arg1;
        return arg1;
    }

    static View access$1200(SlsVoIPActivity arg0) {
        return arg0.declineBtn;
    }

    static Animator access$1300(SlsVoIPActivity arg0) {
        return arg0.currentAcceptAnim;
    }

    static Animator access$1302(SlsVoIPActivity arg0, Animator arg1) {
        arg0.currentAcceptAnim = arg1;
        return arg1;
    }

    static View access$1400(SlsVoIPActivity arg0) {
        return arg0.acceptBtn;
    }

    static void access$1600(SlsVoIPActivity arg0, boolean arg1) {
        arg0.setEmojiTooltipVisible(arg1);
    }

    static Runnable access$1700(SlsVoIPActivity arg0) {
        return arg0.tooltipHider;
    }

    static Runnable access$1702(SlsVoIPActivity arg0, Runnable arg1) {
        arg0.tooltipHider = arg1;
        return arg1;
    }

    static TextView access$1800(SlsVoIPActivity arg0) {
        return arg0.hintTextView;
    }

    static void access$1900(SlsVoIPActivity arg0, boolean arg1) {
        arg0.setEmojiExpanded(arg1);
    }

    static User access$200(SlsVoIPActivity arg0) {
        return arg0.user;
    }

    static AnimatorSet access$2000(SlsVoIPActivity arg0) {
        return arg0.ellAnimator;
    }

    static FrameLayout access$2100(SlsVoIPActivity arg0) {
        return arg0.content;
    }

    static int access$2200(SlsVoIPActivity arg0) {
        return arg0.callState;
    }

    static TextView access$2300(SlsVoIPActivity arg0) {
        return arg0.durationText;
    }

    static TextView access$2302(SlsVoIPActivity arg0, TextView arg1) {
        arg0.durationText = arg1;
        return arg1;
    }

    static LinearLayout access$2400(SlsVoIPActivity arg0) {
        return arg0.swipeViewsWrap;
    }

    static ImageView access$2500(SlsVoIPActivity arg0) {
        return arg0.chatBtn;
    }

    static AnimatorSet access$2602(SlsVoIPActivity arg0, AnimatorSet arg1) {
        arg0.retryAnim = arg1;
        return arg1;
    }

    static View access$2700(SlsVoIPActivity arg0) {
        return arg0.cancelBtn;
    }

    static boolean access$2800(SlsVoIPActivity arg0) {
        return arg0.firstStateChange;
    }

    static boolean access$2802(SlsVoIPActivity arg0, boolean arg1) {
        arg0.firstStateChange = arg1;
        return arg1;
    }

    static boolean access$2900(SlsVoIPActivity arg0) {
        return arg0.isIncomingWaiting;
    }

    static boolean access$2902(SlsVoIPActivity arg0, boolean arg1) {
        arg0.isIncomingWaiting = arg1;
        return arg1;
    }

    static void access$300(SlsVoIPActivity arg0) {
        arg0.hideRetry();
    }

    static LinearLayout access$3000(SlsVoIPActivity arg0) {
        return arg0.emojiWrap;
    }

    static void access$3100(SlsVoIPActivity arg0, String arg1, boolean arg2) {
        arg0.setStateTextAnimated(arg1, arg2);
    }

    static View access$3200(SlsVoIPActivity arg0) {
        return arg0.endBtnIcon;
    }

    static TextView access$3300(SlsVoIPActivity arg0) {
        return arg0.stateText;
    }

    static void access$3400(SlsVoIPActivity arg0) {
        arg0.showRetry();
    }

    static void access$3500(SlsVoIPActivity arg0) {
        arg0.startUpdatingCallDuration();
    }

    static void access$3600(SlsVoIPActivity arg0) {
        arg0.updateKeyView();
    }

    static void access$3700(SlsVoIPActivity arg0, CharSequence arg1) {
        arg0.showErrorDialog(arg1);
    }

    static TextView access$3800(SlsVoIPActivity arg0) {
        return arg0.brandingText;
    }

    static int access$3900(SlsVoIPActivity arg0) {
        return arg0.signalBarsCount;
    }

    static int access$3902(SlsVoIPActivity arg0, int arg1) {
        arg0.signalBarsCount = arg1;
        return arg1;
    }

    static View access$400(SlsVoIPActivity arg0) {
        return arg0.endBtn;
    }

    static Animator access$4002(SlsVoIPActivity arg0, Animator arg1) {
        arg0.textChangingAnim = arg1;
        return arg1;
    }

    static TextView access$4100(SlsVoIPActivity arg0) {
        return arg0.stateText2;
    }

    static Animator access$4202(SlsVoIPActivity arg0, Animator arg1) {
        arg0.tooltipAnim = arg1;
        return arg1;
    }

    static AnimatorSet access$4302(SlsVoIPActivity arg0, AnimatorSet arg1) {
        arg0.emojiAnimator = arg1;
        return arg1;
    }

    static Bitmap access$4400(SlsVoIPActivity arg0) {
        return arg0.blurredPhoto1;
    }

    static Bitmap access$4402(SlsVoIPActivity arg0, Bitmap arg1) {
        arg0.blurredPhoto1 = arg1;
        return arg1;
    }

    static Bitmap access$4500(SlsVoIPActivity arg0) {
        return arg0.blurredPhoto2;
    }

    static Bitmap access$4502(SlsVoIPActivity arg0, Bitmap arg1) {
        arg0.blurredPhoto2 = arg1;
        return arg1;
    }

    static ImageView access$4600(SlsVoIPActivity arg0) {
        return arg0.blurOverlayView1;
    }

    static ImageView access$4700(SlsVoIPActivity arg0) {
        return arg0.blurOverlayView2;
    }

    static CheckableImageView access$500(SlsVoIPActivity arg0) {
        return arg0.spkToggle;
    }

    static CheckableImageView access$600(SlsVoIPActivity arg0) {
        return arg0.micToggle;
    }

    static CallSwipeView access$700(SlsVoIPActivity arg0) {
        return arg0.acceptSwipe;
    }

    static CallSwipeView access$800(SlsVoIPActivity arg0) {
        return arg0.declineSwipe;
    }

    static boolean access$900(SlsVoIPActivity arg0) {
        return arg0.didAcceptFromHere;
    }

    static boolean access$902(SlsVoIPActivity arg0, boolean arg1) {
        arg0.didAcceptFromHere = arg1;
        return arg1;
    }

    private void callAccepted() {
        AnimatorSet v1_1;
        ObjectAnimator v1;
        SlsVoIPActivity v0 = this;
        v0.endBtn.setVisibility(0);
        v0.micToggle.setVisibility(0);
        if(this.hasEarpiece()) {
            v0.spkToggle.setVisibility(0);
        }

        v0.chatBtn.setVisibility(0);
        long v5 = 500;
        int v8 = 3;
        int v10 = 2;
        if(v0.didAcceptFromHere) {
            v0.acceptBtn.setVisibility(8);
            if(Build$VERSION.SDK_INT >= 21) {
                v1 = ObjectAnimator.ofArgb(v0.endBtnBg, "color", new int[]{-12207027, -1696188});
            }
            else {
                v1 = ObjectAnimator.ofInt(v0.endBtnBg, "color", new int[]{-12207027, -1696188});
                v1.setEvaluator(new ArgbEvaluator());
            }

            AnimatorSet v11 = new AnimatorSet();
            AnimatorSet v12 = new AnimatorSet();
            Animator[] v13 = new Animator[5];
            v13[0] = ObjectAnimator.ofFloat(v0.micToggle, "alpha", new float[]{0f, 1f});
            v13[1] = ObjectAnimator.ofFloat(v0.spkToggle, "alpha", new float[]{0f, 1f});
            v13[v10] = ObjectAnimator.ofFloat(v0.chatBtn, "alpha", new float[]{0f, 1f});
            v13[v8] = ObjectAnimator.ofFloat(v0.endBtnIcon, "rotation", new float[]{-135f, 0f});
            v13[4] = v1;
            v12.playTogether(v13);
            v12.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            v12.setDuration(v5);
            v1_1 = new AnimatorSet();
            Animator[] v3 = new Animator[v10];
            v3[0] = ObjectAnimator.ofFloat(v0.swipeViewsWrap, "alpha", new float[]{1f, 0f});
            v3[1] = ObjectAnimator.ofFloat(v0.declineBtn, "alpha", new float[]{0f});
            v1_1.playTogether(v3);
            v1_1.setInterpolator(CubicBezierInterpolator.EASE_IN);
            v1_1.setDuration(125);
            v3 = new Animator[v10];
            v3[0] = v12;
            v3[1] = v1_1;
            v11.playTogether(v3);
            v11.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    SlsVoIPActivity.this.swipeViewsWrap.setVisibility(8);
                    SlsVoIPActivity.this.declineBtn.setVisibility(8);
                }
            });
            v11.start();
        }
        else {
            v1_1 = new AnimatorSet();
            AnimatorSet v3_1 = new AnimatorSet();
            Animator[] v4 = new Animator[v8];
            v4[0] = ObjectAnimator.ofFloat(v0.micToggle, "alpha", new float[]{0f, 1f});
            v4[1] = ObjectAnimator.ofFloat(v0.spkToggle, "alpha", new float[]{0f, 1f});
            v4[v10] = ObjectAnimator.ofFloat(v0.chatBtn, "alpha", new float[]{0f, 1f});
            v3_1.playTogether(v4);
            v3_1.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            v3_1.setDuration(v5);
            AnimatorSet v4_1 = new AnimatorSet();
            Animator[] v5_1 = new Animator[v8];
            v5_1[0] = ObjectAnimator.ofFloat(v0.swipeViewsWrap, "alpha", new float[]{1f, 0f});
            v5_1[1] = ObjectAnimator.ofFloat(v0.declineBtn, "alpha", new float[]{0f});
            v5_1[v10] = ObjectAnimator.ofFloat(v0.acceptBtn, "alpha", new float[]{0f});
            v4_1.playTogether(v5_1);
            v4_1.setInterpolator(CubicBezierInterpolator.EASE_IN);
            v4_1.setDuration(125);
            v5_1 = new Animator[v10];
            v5_1[0] = v3_1;
            v5_1[1] = v4_1;
            v1_1.playTogether(v5_1);
            v1_1.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    SlsVoIPActivity.this.swipeViewsWrap.setVisibility(8);
                    SlsVoIPActivity.this.declineBtn.setVisibility(8);
                    SlsVoIPActivity.this.acceptBtn.setVisibility(8);
                }
            });
            v1_1.start();
        }
    }

    @SuppressLint(value={"ObjectAnimatorBinding"}) private ObjectAnimator createAlphaAnimator(Object arg4, int arg5, int arg6, int arg7, int arg8) {
        ObjectAnimator v4 = ObjectAnimator.ofInt(arg4, "alpha", new int[]{arg5, arg6});
        v4.setDuration(((long)arg8));
        v4.setStartDelay(((long)arg7));
        v4.setInterpolator(CubicBezierInterpolator.DEFAULT);
        return v4;
    }

    private View createContentView() {
        SignalBarsDrawable v3_1;
        SignalBarsDrawable v9;
        SlsVoIPActivity v6 = this;
        FrameLayout v7 = new FrameLayout(((Context)v6));
        v7.setBackgroundColor(0);
        org.telegram.ui.SlsVoIPActivity$14 v0 = new BackupImageView(((Context)v6)) {
            private Drawable bottomGradient;
            private Paint paint;
            private Drawable topGradient;

            protected void onDraw(Canvas arg8) {
                super.onDraw(arg8);
                this.paint.setColor(1275068416);
                arg8.drawRect(0f, 0f, ((float)this.getWidth()), ((float)this.getHeight()), this.paint);
                this.topGradient.setBounds(0, 0, this.getWidth(), AndroidUtilities.dp(170f));
                this.topGradient.setAlpha(128);
                this.topGradient.draw(arg8);
                this.bottomGradient.setBounds(0, this.getHeight() - AndroidUtilities.dp(220f), this.getWidth(), this.getHeight());
                this.bottomGradient.setAlpha(178);
                this.bottomGradient.draw(arg8);
            }
        };
        v6.photoView = ((BackupImageView)v0);
        v7.addView(((View)v0));
        v6.blurOverlayView1 = new ImageView(((Context)v6));
        v6.blurOverlayView1.setScaleType(ImageView$ScaleType.CENTER_CROP);
        v6.blurOverlayView1.setAlpha(0f);
        v7.addView(v6.blurOverlayView1);
        v6.blurOverlayView2 = new ImageView(((Context)v6));
        v6.blurOverlayView2.setScaleType(ImageView$ScaleType.CENTER_CROP);
        v6.blurOverlayView2.setAlpha(0f);
        v7.addView(v6.blurOverlayView2);
        TextView v0_1 = new TextView(((Context)v6));
        int v2 = -855638017;
        v0_1.setTextColor(v2);
        v0_1.setText(LocaleController.getString("HotgramCall", 2131624957));
        Drawable v3 = this.getResources().getDrawable(2131231416).mutate();
        int v4 = 204;
        v3.setAlpha(v4);
        float v5 = 15f;
        v3.setBounds(0, 0, AndroidUtilities.dp(v5), AndroidUtilities.dp(v5));
        org.telegram.ui.SlsVoIPActivity$1 v10 = null;
        v6.signalBarsDrawable = new SignalBarsDrawable(v6, v10);
        v6.signalBarsDrawable.setBounds(0, 0, v6.signalBarsDrawable.getIntrinsicWidth(), v6.signalBarsDrawable.getIntrinsicHeight());
        if(LocaleController.isRTL) {
            v9 = v6.signalBarsDrawable;
        }
        else {
            Drawable v9_1 = v3;
        }

        if(LocaleController.isRTL) {
        }
        else {
            v3_1 = v6.signalBarsDrawable;
        }

        v0_1.setCompoundDrawables(((Drawable)v9), ((Drawable)v10), ((Drawable)v3_1), ((Drawable)v10));
        v0_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        int v10_1 = 3;
        int v3_2 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v3_2);
        v0_1.setCompoundDrawablePadding(AndroidUtilities.dp(5f));
        v0_1.setTextSize(1, 14f);
        int v12 = -2;
        float v13 = -2f;
        int v14 = LocaleController.isRTL ? 5 : 3;
        v7.addView(((View)v0_1), LayoutHelper.createFrame(v12, v13, v14 | 48, 18f, 18f, 18f, 0f));
        v6.brandingText = v0_1;
        v0_1 = new TextView(((Context)v6));
        v0_1.setSingleLine();
        v12 = -1;
        v0_1.setTextColor(v12);
        v0_1.setTextSize(1, 40f);
        v0_1.setEllipsize(TextUtils$TruncateAt.END);
        int v13_1 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v13_1);
        v13 = 3f;
        float v15 = 0.666667f;
        v0_1.setShadowLayer(((float)AndroidUtilities.dp(v13)), 0f, ((float)AndroidUtilities.dp(v15)), 1275068416);
        v0_1.setTypeface(Typeface.create("sans-serif-light", 0));
        v6.nameText = v0_1;
        v7.addView(((View)v0_1), LayoutHelper.createFrame(-1, -2f, 51, 18f, 43f, 18f, 0f));
        v0_1 = new TextView(((Context)v6));
        v0_1.setTextColor(v2);
        v0_1.setSingleLine();
        v0_1.setEllipsize(TextUtils$TruncateAt.END);
        v0_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0_1.setShadowLayer(((float)AndroidUtilities.dp(v13)), 0f, ((float)AndroidUtilities.dp(v15)), 1275068416);
        v0_1.setTextSize(1, v5);
        v3_2 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v3_2);
        v6.stateText = v0_1;
        v7.addView(((View)v0_1), LayoutHelper.createFrame(-1, -2f, 51, 18f, 98f, 18f, 0f));
        v6.durationText = v0_1;
        v0_1 = new TextView(((Context)v6));
        v0_1.setTextColor(v2);
        v0_1.setSingleLine();
        v0_1.setEllipsize(TextUtils$TruncateAt.END);
        v0_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0_1.setShadowLayer(((float)AndroidUtilities.dp(v13)), 0f, ((float)AndroidUtilities.dp(v15)), 1275068416);
        v0_1.setTextSize(1, v5);
        v2 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v2);
        v0_1.setVisibility(8);
        v6.stateText2 = v0_1;
        v7.addView(((View)v0_1), LayoutHelper.createFrame(-1, -2f, 51, 18f, 98f, 18f, 0f));
        TextAlphaSpan[] v0_2 = new TextAlphaSpan[v10_1];
        v0_2[0] = new TextAlphaSpan(v6);
        v0_2[1] = new TextAlphaSpan(v6);
        int v9_2 = 2;
        v0_2[v9_2] = new TextAlphaSpan(v6);
        v6.ellSpans = v0_2;
        CheckableImageView v0_3 = new CheckableImageView(((Context)v6));
        v0_3.setBackgroundResource(2131230927);
        Drawable v2_1 = this.getResources().getDrawable(2131231198).mutate();
        v2_1.setAlpha(v4);
        v0_3.setImageDrawable(v2_1);
        v0_3.setScaleType(ImageView$ScaleType.CENTER);
        v6.micToggle = v0_3;
        v7.addView(((View)v0_3), LayoutHelper.createFrame(38, 38f, 83, 16f, 0f, 0f, 10f));
        v0_3 = new CheckableImageView(((Context)v6));
        v0_3.setBackgroundResource(2131230927);
        v2_1 = this.getResources().getDrawable(2131231265).mutate();
        v2_1.setAlpha(v4);
        v0_3.setImageDrawable(v2_1);
        v0_3.setScaleType(ImageView$ScaleType.CENTER);
        v6.spkToggle = v0_3;
        v7.addView(((View)v0_3), LayoutHelper.createFrame(38, 38f, 85, 0f, 0f, 16f, 10f));
        ImageView v0_4 = new ImageView(((Context)v6));
        v2_1 = this.getResources().getDrawable(2131231152).mutate();
        v2_1.setAlpha(v4);
        v0_4.setImageDrawable(v2_1);
        v0_4.setScaleType(ImageView$ScaleType.CENTER);
        v6.chatBtn = v0_4;
        v7.addView(((View)v0_4), LayoutHelper.createFrame(38, 38f, 81, 0f, 0f, 0f, 10f));
        LinearLayout v0_5 = new LinearLayout(((Context)v6));
        v0_5.setOrientation(0);
        CallSwipeView v2_2 = new CallSwipeView(((Context)v6));
        v2_2.setColor(-12207027);
        v6.acceptSwipe = v2_2;
        v0_5.addView(((View)v2_2), LayoutHelper.createLinear(-1, 70, 1f, 4, 4, -35, 4));
        CallSwipeView v3_3 = new CallSwipeView(((Context)v6));
        v3_3.setColor(-1696188);
        v6.declineSwipe = v3_3;
        v0_5.addView(((View)v3_3), LayoutHelper.createLinear(-1, 70, 1f, -35, 4, 4, 4));
        v6.swipeViewsWrap = v0_5;
        v7.addView(((View)v0_5), LayoutHelper.createFrame(-1, -2f, 80, 20f, 0f, 20f, 68f));
        v0_4 = new ImageView(((Context)v6));
        FabBackgroundDrawable v4_1 = new FabBackgroundDrawable();
        v4_1.setColor(-12207027);
        v0_4.setBackgroundDrawable(((Drawable)v4_1));
        v0_4.setImageResource(2131231136);
        v0_4.setScaleType(ImageView$ScaleType.MATRIX);
        Matrix v4_2 = new Matrix();
        v4_2.setTranslate(((float)AndroidUtilities.dp(17f)), ((float)AndroidUtilities.dp(17f)));
        v4_2.postRotate(-135f, ((float)AndroidUtilities.dp(35f)), ((float)AndroidUtilities.dp(35f)));
        v0_4.setImageMatrix(v4_2);
        v6.acceptBtn = ((View)v0_4);
        v7.addView(((View)v0_4), LayoutHelper.createFrame(78, 78f, 83, 20f, 0f, 0f, 68f));
        ImageView v4_3 = new ImageView(((Context)v6));
        FabBackgroundDrawable v5_1 = new FabBackgroundDrawable();
        v5_1.setColor(-1696188);
        v4_3.setBackgroundDrawable(((Drawable)v5_1));
        v4_3.setImageResource(2131231136);
        v4_3.setScaleType(ImageView$ScaleType.CENTER);
        v6.declineBtn = ((View)v4_3);
        v7.addView(((View)v4_3), LayoutHelper.createFrame(78, 78f, 85, 0f, 0f, 20f, 68f));
        v2_2.setViewToDrag(((View)v0_4), false);
        v3_3.setViewToDrag(((View)v4_3), true);
        FrameLayout v0_6 = new FrameLayout(((Context)v6));
        FabBackgroundDrawable v2_3 = new FabBackgroundDrawable();
        v2_3.setColor(-1696188);
        v6.endBtnBg = v2_3;
        v0_6.setBackgroundDrawable(((Drawable)v2_3));
        ImageView v2_4 = new ImageView(((Context)v6));
        v2_4.setImageResource(2131231136);
        v2_4.setScaleType(ImageView$ScaleType.CENTER);
        v6.endBtnIcon = ((View)v2_4);
        v0_6.addView(((View)v2_4), LayoutHelper.createFrame(70, 70f));
        v0_6.setForeground(this.getResources().getDrawable(2131231059));
        v6.endBtn = ((View)v0_6);
        v7.addView(((View)v0_6), LayoutHelper.createFrame(78, 78f, 81, 0f, 0f, 0f, 68f));
        v0_4 = new ImageView(((Context)v6));
        v2_3 = new FabBackgroundDrawable();
        v2_3.setColor(v12);
        v0_4.setBackgroundDrawable(((Drawable)v2_3));
        v0_4.setImageResource(2131231053);
        v0_4.setColorFilter(-1996488704);
        v0_4.setScaleType(ImageView$ScaleType.CENTER);
        v0_4.setVisibility(8);
        v6.cancelBtn = ((View)v0_4);
        v7.addView(((View)v0_4), LayoutHelper.createFrame(78, 78f, 83, 52f, 0f, 0f, 68f));
        v6.emojiWrap = new LinearLayout(((Context)v6));
        v6.emojiWrap.setOrientation(0);
        v6.emojiWrap.setClipToPadding(false);
        v6.emojiWrap.setPivotX(0f);
        v6.emojiWrap.setPivotY(0f);
        float v4_4 = 10f;
        v6.emojiWrap.setPadding(AndroidUtilities.dp(14f), AndroidUtilities.dp(v4_4), AndroidUtilities.dp(14f), AndroidUtilities.dp(v4_4));
        int v0_7;
        for(v0_7 = 0; v0_7 < 4; ++v0_7) {
            v2_4 = new ImageView(((Context)v6));
            v2_4.setScaleType(ImageView$ScaleType.FIT_XY);
            LinearLayout v3_4 = v6.emojiWrap;
            int v19 = 22;
            int v20 = 22;
            float v21 = v0_7 == 0 ? 0f : 4f;
            v3_4.addView(((View)v2_4), LayoutHelper.createLinear(v19, v20, v21, 0f, 0f, 0f));
            v6.keyEmojiViews[v0_7] = v2_4;
        }

        v6.emojiWrap.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg2) {
                if(SlsVoIPActivity.this.emojiTooltipVisible) {
                    SlsVoIPActivity.this.setEmojiTooltipVisible(false);
                    if(SlsVoIPActivity.this.tooltipHider != null) {
                        SlsVoIPActivity.this.hintTextView.removeCallbacks(SlsVoIPActivity.this.tooltipHider);
                        SlsVoIPActivity.this.tooltipHider = null;
                    }
                }

                SlsVoIPActivity.this.setEmojiExpanded(SlsVoIPActivity.this.emojiExpanded ^ 1);
            }
        });
        v0_5 = v6.emojiWrap;
        v2 = -2;
        v3_2 = -2;
        int v5_2 = LocaleController.isRTL ? 3 : 5;
        v7.addView(((View)v0_5), LayoutHelper.createFrame(v2, v3_2, v5_2 | 48));
        v6.emojiWrap.setOnLongClickListener(new View$OnLongClickListener() {
            public boolean onLongClick(View arg5) {
                if(SlsVoIPActivity.this.emojiExpanded) {
                    return 0;
                }

                if(SlsVoIPActivity.this.tooltipHider != null) {
                    SlsVoIPActivity.this.hintTextView.removeCallbacks(SlsVoIPActivity.this.tooltipHider);
                    SlsVoIPActivity.this.tooltipHider = null;
                }

                SlsVoIPActivity.this.setEmojiTooltipVisible(SlsVoIPActivity.this.emojiTooltipVisible ^ 1);
                if(SlsVoIPActivity.this.emojiTooltipVisible) {
                    SlsVoIPActivity.this.hintTextView.postDelayed(SlsVoIPActivity.access$1702(SlsVoIPActivity.this, new Runnable() {
                        public void run() {
                            this.this$1.this$0.tooltipHider = null;
                            this.this$1.this$0.setEmojiTooltipVisible(false);
                        }
                    }), 5000);
                }

                return 1;
            }
        });
        v6.emojiExpandedText = new TextView(((Context)v6));
        v6.emojiExpandedText.setTextSize(1, 16f);
        v6.emojiExpandedText.setTextColor(v12);
        v6.emojiExpandedText.setGravity(17);
        v6.emojiExpandedText.setAlpha(0f);
        v7.addView(v6.emojiExpandedText, LayoutHelper.createFrame(-1, -2f, 17, 10f, 32f, 10f, 0f));
        v6.hintTextView = new CorrectlyMeasuringTextView(((Context)v6));
        v6.hintTextView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(v13), -231525581));
        v6.hintTextView.setTextColor(Theme.getColor("chat_gifSaveHintText"));
        v6.hintTextView.setTextSize(1, 14f);
        v6.hintTextView.setPadding(AndroidUtilities.dp(v4_4), AndroidUtilities.dp(v4_4), AndroidUtilities.dp(v4_4), AndroidUtilities.dp(v4_4));
        v6.hintTextView.setGravity(17);
        v6.hintTextView.setMaxWidth(AndroidUtilities.dp(300f));
        v6.hintTextView.setAlpha(0f);
        v7.addView(v6.hintTextView, LayoutHelper.createFrame(-2, -2f, 53, 0f, 42f, 10f, 0f));
        v12 = v6.stateText.getPaint().getAlpha();
        v6.ellAnimator = new AnimatorSet();
        AnimatorSet v13_2 = v6.ellAnimator;
        Animator[] v14_1 = new Animator[6];
        v14_1[0] = this.createAlphaAnimator(v6.ellSpans[0], 0, v12, 0, 300);
        v14_1[1] = this.createAlphaAnimator(v6.ellSpans[1], 0, v12, 150, 300);
        v14_1[v9_2] = this.createAlphaAnimator(v6.ellSpans[v9_2], 0, v12, 300, 300);
        v14_1[v10_1] = this.createAlphaAnimator(v6.ellSpans[0], v12, 0, 1000, 400);
        v14_1[4] = this.createAlphaAnimator(v6.ellSpans[1], v12, 0, 1000, 400);
        v14_1[5] = this.createAlphaAnimator(v6.ellSpans[v9_2], v12, 0, 1000, 400);
        v13_2.playTogether(v14_1);
        v6.ellAnimator.addListener(new AnimatorListenerAdapter() {
            class org.telegram.ui.SlsVoIPActivity$17$1 implements Runnable {
                org.telegram.ui.SlsVoIPActivity$17$1(org.telegram.ui.SlsVoIPActivity$17 arg1) {
                    this.this$1 = arg1;
                    super();
                }

                public void run() {
                    if(!this.this$1.this$0.isFinishing()) {
                        this.this$1.this$0.ellAnimator.start();
                    }
                }
            }

            private Runnable restarter;

            public void onAnimationEnd(Animator arg4) {
                if(!SlsVoIPActivity.this.isFinishing()) {
                    SlsVoIPActivity.this.content.postDelayed(this.restarter, 300);
                }
            }
        });
        v7.setClipChildren(false);
        v6.content = v7;
        return ((View)v7);
    }

    public void didReceivedNotification(int arg3, int arg4, Object[] arg5) {
        if(arg3 == NotificationCenter.emojiDidLoaded) {
            ImageView[] v4 = this.keyEmojiViews;
            int v5 = v4.length;
            int v0;
            for(v0 = 0; v0 < v5; ++v0) {
                v4[v0].invalidate();
            }
        }

        if(arg3 == NotificationCenter.closeInCallActivity) {
            this.finish();
        }
    }

    public void endCallFromService() {
        try {
            SlsVoIPService.getSharedInstance().publicCallEnded();
        }
        catch(Exception ) {
            this.sipWrapper.endIncomingCall(true);
            this.sipWrapper.endCall(true, true);
        }
    }

    protected h getApiCallback() {
        return this.onResult;
    }

    private CharSequence getFormattedDebugString() {
        String v0 = VoIPService.getSharedInstance().getDebugString();
        SpannableString v1 = new SpannableString(((CharSequence)v0));
        int v3 = 0;
        do {
            int v4 = v3 + 1;
            int v5 = 10;
            int v6 = v0.indexOf(v5, v4);
            int v7 = -1;
            if(v6 == v7) {
                v6 = v0.length();
            }

            String v8 = v0.substring(v3, v6);
            if(v8.contains("IN_USE")) {
                v1.setSpan(new ForegroundColorSpan(-16711936), v3, v6, 0);
            }
            else if(v8.contains(": ")) {
                v1.setSpan(new ForegroundColorSpan(-1426063361), v3, v8.indexOf(58) + v3 + 1, 0);
            }

            v3 = v0.indexOf(v5, v4);
        }
        while(v3 != v7);

        return ((CharSequence)v1);
    }

    public boolean hasEarpiece() {
        if(this.getSystemService("phone").getPhoneType() != 0) {
            return 1;
        }

        if(this.mHasEarpiece == null) {
            try {
                Object v0_1 = this.getSystemService("audio");
                Method v2 = AudioManager.class.getMethod("getDevicesForStream", Integer.TYPE);
                int v3 = AudioManager.class.getField("DEVICE_OUT_EARPIECE").getInt(null);
                Boolean v0_2 = (v2.invoke(v0_1, Integer.valueOf(0)).intValue() & v3) == v3 ? Boolean.TRUE : Boolean.FALSE;
                this.mHasEarpiece = v0_2;
            }
            catch(Throwable v0) {
                FileLog.e("Error while checking earpiece! ", v0);
                this.mHasEarpiece = Boolean.TRUE;
            }
        }

        return this.mHasEarpiece.booleanValue();
    }

    private void hideRetry() {
        ObjectAnimator v1;
        if(this.retryAnim != null) {
            this.retryAnim.cancel();
        }

        this.retrying = false;
        this.spkToggle.setVisibility(0);
        this.micToggle.setVisibility(0);
        this.chatBtn.setVisibility(0);
        int v3 = 2;
        if(Build$VERSION.SDK_INT >= 21) {
            v1 = ObjectAnimator.ofArgb(this.endBtnBg, "color", new int[]{-12207027, -1696188});
        }
        else {
            v1 = ObjectAnimator.ofInt(this.endBtnBg, "color", new int[]{-12207027, -1696188});
            v1.setEvaluator(new ArgbEvaluator());
        }

        AnimatorSet v2 = new AnimatorSet();
        Animator[] v4 = new Animator[7];
        v4[0] = v1;
        v4[1] = ObjectAnimator.ofFloat(this.endBtnIcon, "rotation", new float[]{-135f, 0f});
        v4[v3] = ObjectAnimator.ofFloat(this.endBtn, "translationX", new float[]{0f});
        v4[3] = ObjectAnimator.ofFloat(this.cancelBtn, "alpha", new float[]{0f});
        v4[4] = ObjectAnimator.ofFloat(this.spkToggle, "alpha", new float[]{1f});
        v4[5] = ObjectAnimator.ofFloat(this.micToggle, "alpha", new float[]{1f});
        v4[6] = ObjectAnimator.ofFloat(this.chatBtn, "alpha", new float[]{1f});
        v2.playTogether(v4);
        v2.setStartDelay(200);
        v2.setDuration(300);
        v2.setInterpolator(CubicBezierInterpolator.DEFAULT);
        v2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                SlsVoIPActivity.this.cancelBtn.setVisibility(8);
                SlsVoIPActivity.this.endBtn.setEnabled(true);
                SlsVoIPActivity.this.retryAnim = null;
            }
        });
        this.retryAnim = v2;
        v2.start();
    }

    public static void launchMe(User arg1, boolean arg2, boolean arg3) {
        new a().app.startActivity(SlsVoIPActivity.myIntent(arg1, arg2, arg3));
    }

    public static Intent myIntent(User arg3, boolean arg4, boolean arg5) {
        Intent v1 = new Intent(new a().app, SlsVoIPActivity.class);
        if(arg3 != null) {
            SerializedData v0 = new SerializedData();
            arg3.serializeToStream(((AbstractSerializedData)v0));
            v1.putExtra("EXTRA_TELEGRAM_USER", v0.toByteArray());
        }

        v1.putExtra("EXTRA_AM_I_CALLER", arg4);
        v1.putExtra("EXTRA_DID_ANSWERED", arg5);
        v1.setFlags(268435456);
        return v1;
    }

    public void onAudioSettingsChanged() {
        boolean v0_1;
        CheckableImageView v1;
        if(VoIPService.getSharedInstance() == null) {
            return;
        }

        this.micToggle.setChecked(VoIPService.getSharedInstance().isMicMute());
        if((this.hasEarpiece()) || (VoIPService.getSharedInstance().isBluetoothHeadsetConnected())) {
            this.spkToggle.setVisibility(0);
            Object v0 = this.getSystemService("audio");
            int v3 = 2131231130;
            if(!this.hasEarpiece()) {
                this.spkToggle.setImageResource(v3);
                v1 = this.spkToggle;
                v0_1 = ((AudioManager)v0).isBluetoothScoOn();
            }
            else {
                int v4 = 2131231265;
                if(VoIPService.getSharedInstance().isBluetoothHeadsetConnected()) {
                    if(((AudioManager)v0).isBluetoothScoOn()) {
                        this.spkToggle.setImageResource(v3);
                    }
                    else if(((AudioManager)v0).isSpeakerphoneOn()) {
                        this.spkToggle.setImageResource(v4);
                    }
                    else {
                        this.spkToggle.setImageResource(2131231216);
                    }

                    this.spkToggle.setChecked(false);
                    return;
                }
                else {
                    this.spkToggle.setImageResource(v4);
                    v1 = this.spkToggle;
                    v0_1 = ((AudioManager)v0).isSpeakerphoneOn();
                }
            }

            v1.setChecked(v0_1);
        }
        else {
            this.spkToggle.setVisibility(4);
        }
    }

    public void onBackPressed() {
        if(this.emojiExpanded) {
            this.setEmojiExpanded(false);
            return;
        }

        if(!this.isIncomingWaiting) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle arg10) {
        this.requestWindowFeature(1);
        this.getWindow().addFlags(524288);
        super.onCreate(arg10);
        this.sipWrapper.addSipCallback(this.sipCallback);
        int v1 = 15;
        int v2 = 3;
        if((this.getResources().getConfiguration().screenLayout & v1) < v2) {
            this.setRequestedOrientation(1);
        }

        View v10 = this.createContentView();
        this.setContentView(v10);
        int v5 = -16777216;
        if(Build$VERSION.SDK_INT >= 21) {
            this.getWindow().addFlags(-2147483648);
            this.getWindow().setStatusBarColor(v5);
        }

        Bundle v3 = this.getIntent().getExtras();
        if(v3 != null) {
            this.amICaller = v3.getBoolean("EXTRA_AM_I_CALLER", false);
            this.didAnswered = v3.getBoolean("EXTRA_DID_ANSWERED", false);
            byte[] v3_1 = v3.getByteArray("EXTRA_TELEGRAM_USER");
            if(v3_1 != null) {
                SerializedData v6 = new SerializedData(v3_1);
                this.user = User.TLdeserialize(((AbstractSerializedData)v6), v6.readInt32(false), false);
                v6.cleanup();
            }
        }

        if(!this.amICaller) {
            try {
                if(this.getIntent().getStringExtra("EXTRA_CALL_INFO") == null) {
                    goto label_65;
                }

                Intent v3_3 = new Intent(((Context)this), SlsVoIPService.class);
                v3_3.putExtra("EXTRA_CALL_INFO", this.getIntent().getStringExtra("EXTRA_CALL_INFO"));
                this.startService(v3_3);
            }
            catch(Exception v3_2) {
                v3_2.printStackTrace();
            }
        }

    label_65:
        if(this.user.photo != null) {
            this.photoView.getImageReceiver().setDelegate(new ImageReceiverDelegate() {
                public void didSetImage(ImageReceiver arg1, boolean arg2, boolean arg3) {
                    Bitmap v1 = arg1.getBitmap();
                    if(v1 != null) {
                        SlsVoIPActivity.this.updateBlurredPhotos(v1);
                    }
                }
            });
            this.photoView.setImage(this.user.photo.photo_big, null, new ColorDrawable(v5));
        }
        else {
            this.photoView.setVisibility(8);
            v10.setBackgroundDrawable(new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[]{-14994098, -14328963}));
        }

        this.setVolumeControlStream(0);
        this.nameText.setOnClickListener(new View$OnClickListener() {
            private int tapCount;

            public void onClick(View arg1) {
            }
        });
        this.endBtn.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                if(!SlsVoIPActivity.this.retrying) {
                    SlsVoIPActivity.this.endBtn.setEnabled(false);
                    SlsVoIPActivity.this.endCallFromService();
                }
                else if(SlsVoIPActivity.this.amICaller) {
                    SlsVoIPActivity.this.api.a(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId(), SlsVoIPActivity.this.user.id);
                    SlsVoIPActivity.this.hideRetry();
                }
            }
        });
        this.spkToggle.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg2) {
                int v2 = SlsVoIPActivity.this.spkToggle.isChecked() ^ 1;
                SlsVoIPActivity.this.spkToggle.setChecked(((boolean)v2));
                SlsVoIPActivity.this.sipWrapper.speakerToggle(((boolean)v2));
            }
        });
        this.micToggle.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg2) {
                int v2 = SlsVoIPActivity.this.micToggle.isChecked() ^ 1;
                SlsVoIPActivity.this.micToggle.setChecked(((boolean)v2));
                SlsVoIPActivity.this.sipWrapper.toggleMicrophone(((boolean)v2));
            }
        });
        this.chatBtn.setOnClickListener(new View$OnClickListener() {
            @SuppressLint(value={"WrongConstant"}) public void onClick(View arg4) {
                Intent v4 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
                v4.setAction("com.tmessages.openchat" + Math.random() + 2147483647);
                v4.setFlags(32768);
                v4.putExtra("userId", SlsVoIPActivity.this.user.id);
                SlsVoIPActivity.this.startActivity(v4);
            }
        });
        this.spkToggle.setChecked(false);
        this.micToggle.setChecked(false);
        this.onAudioSettingsChanged();
        this.nameText.setText(ContactsController.formatName(this.user.first_name, this.user.last_name));
        this.acceptSwipe.setListener(new Listener() {
            public void onDragCancel() {
                if(SlsVoIPActivity.this.currentDeclineAnim != null) {
                    SlsVoIPActivity.this.currentDeclineAnim.cancel();
                }

                AnimatorSet v0 = new AnimatorSet();
                v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(SlsVoIPActivity.this.declineSwipe, "alpha", new float[]{1f}), ObjectAnimator.ofFloat(SlsVoIPActivity.this.declineBtn, "alpha", new float[]{1f})});
                v0.setDuration(200);
                v0.setInterpolator(CubicBezierInterpolator.DEFAULT);
                v0.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        this.this$1.this$0.currentDeclineAnim = null;
                    }
                });
                SlsVoIPActivity.this.currentDeclineAnim = ((Animator)v0);
                v0.start();
                SlsVoIPActivity.this.declineSwipe.startAnimatingArrows();
            }

            public void onDragComplete() {
                SlsVoIPActivity.this.acceptSwipe.setEnabled(false);
                SlsVoIPActivity.this.declineSwipe.setEnabled(false);
                SlsVoIPActivity.this.didAcceptFromHere = true;
                if(Build$VERSION.SDK_INT < 23 || SlsVoIPActivity.this.checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
                    try {
                        SlsVoIPService.getSharedInstance().acceptIncomingCall();
                        SlsVoIPActivity.this.callAccepted();
                        return;
                    }
                    catch(Exception ) {
                        return;
                    }
                }
                else {
                    SlsVoIPActivity.this.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 101);
                }
            }

            public void onDragStart() {
                if(SlsVoIPActivity.this.currentDeclineAnim != null) {
                    SlsVoIPActivity.this.currentDeclineAnim.cancel();
                }

                AnimatorSet v0 = new AnimatorSet();
                v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(SlsVoIPActivity.this.declineSwipe, "alpha", new float[]{0.2f}), ObjectAnimator.ofFloat(SlsVoIPActivity.this.declineBtn, "alpha", new float[]{0.2f})});
                v0.setDuration(200);
                v0.setInterpolator(CubicBezierInterpolator.DEFAULT);
                v0.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        this.this$1.this$0.currentDeclineAnim = null;
                    }
                });
                SlsVoIPActivity.this.currentDeclineAnim = ((Animator)v0);
                v0.start();
                SlsVoIPActivity.this.declineSwipe.stopAnimatingArrows();
            }
        });
        this.declineSwipe.setListener(new Listener() {
            public void onDragCancel() {
                if(SlsVoIPActivity.this.currentAcceptAnim != null) {
                    SlsVoIPActivity.this.currentAcceptAnim.cancel();
                }

                AnimatorSet v0 = new AnimatorSet();
                v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(SlsVoIPActivity.this.acceptSwipe, "alpha", new float[]{1f}), ObjectAnimator.ofFloat(SlsVoIPActivity.this.acceptBtn, "alpha", new float[]{1f})});
                v0.setDuration(200);
                v0.setInterpolator(CubicBezierInterpolator.DEFAULT);
                v0.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        this.this$1.this$0.currentAcceptAnim = null;
                    }
                });
                SlsVoIPActivity.this.currentAcceptAnim = ((Animator)v0);
                v0.start();
                SlsVoIPActivity.this.acceptSwipe.startAnimatingArrows();
            }

            public void onDragComplete() {
                SlsVoIPActivity.this.acceptSwipe.setEnabled(false);
                SlsVoIPActivity.this.declineSwipe.setEnabled(false);
                SlsVoIPActivity.this.endCallFromService();
            }

            public void onDragStart() {
                if(SlsVoIPActivity.this.currentAcceptAnim != null) {
                    SlsVoIPActivity.this.currentAcceptAnim.cancel();
                }

                AnimatorSet v0 = new AnimatorSet();
                v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(SlsVoIPActivity.this.acceptSwipe, "alpha", new float[]{0.2f}), ObjectAnimator.ofFloat(SlsVoIPActivity.this.acceptBtn, "alpha", new float[]{0.2f})});
                v0.setDuration(200);
                v0.setInterpolator(new DecelerateInterpolator());
                v0.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        this.this$1.this$0.currentAcceptAnim = null;
                    }
                });
                SlsVoIPActivity.this.currentAcceptAnim = ((Animator)v0);
                v0.start();
                SlsVoIPActivity.this.acceptSwipe.stopAnimatingArrows();
            }
        });
        this.cancelBtn.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                SlsVoIPActivity.this.finish();
            }
        });
        this.getWindow().getDecorView().setKeepScreenOn(true);
        this.hintTextView.setText(LocaleController.formatString("CallEmojiKeyTooltip", 2131624237, new Object[]{this.user.first_name}));
        this.emojiExpandedText.setText(LocaleController.formatString("CallEmojiKeyTooltip", 2131624237, new Object[]{this.user.first_name}));
        if(this.amICaller) {
            this.api.a(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId(), this.user.id);
            Intent v10_1 = new Intent(((Context)this), SlsVoIPService.class);
            v10_1.putExtra("user_id", this.user.id);
            v10_1.putExtra("is_outgoing", true);
            v10_1.putExtra("start_incall_activity", false);
            v10_1.putExtra("account", UserConfig.selectedAccount);
            try {
                this.startService(v10_1);
            }
            catch(Throwable v10_2) {
                FileLog.e(v10_2);
            }

            this.onStateChanged(1);
            goto label_190;
        }

        if(this.didAnswered) {
            this.onStateChanged(v2);
        }
        else {
            this.onStateChanged(v1);
        }

    label_190:
        LinphoneSipWrapper.addCallback(new PhoneServiceCallback() {
            public void callReleased() {
                super.callReleased();
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        this.sipWrapper.removeSipCallback(this.sipCallback);
    }

    public boolean onKeyDown(int arg1, KeyEvent arg2) {
        return super.onKeyDown(arg1, arg2);
    }

    protected void onPause() {
        super.onPause();
    }

    @TargetApi(value=23) public void onRequestPermissionsResult(int arg1, String[] arg2, int[] arg3) {
        if(arg1 == 101) {
            if(SlsVoIPService.getSharedInstance() == null) {
                this.finish();
                return;
            }
            else {
                if(arg3.length > 0 && arg3[0] == 0) {
                    SlsVoIPService.getSharedInstance().acceptIncomingCall();
                    this.callAccepted();
                    return;
                }

                if(!this.shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO")) {
                    SlsVoIPService.getSharedInstance().declineIncomingCall();
                    VoIPHelper.permissionDenied(((Activity)this), new Runnable() {
                        public void run() {
                            SlsVoIPActivity.this.finish();
                        }
                    });
                    return;
                }

                this.acceptSwipe.reset();
            }
        }
    }

    public void onResult(Object arg3, int arg4) {
        if(arg4 != 39) {
        }
        else {
            this.callRequestModel = ((CallRequestModel)arg3);
            this.sipWrapper.setCallRequestModel(this.callRequestModel);
            if(this.callRequestModel.getCalleePresence().contentEquals("ONLINE")) {
                Log.d("LEE", "CallerInfo" + new f().a(this.callRequestModel));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        SlsVoIPActivity.this.sipWrapper.endCall(false, false);
                        SlsVoIPActivity.this.sipWrapper.register(SlsVoIPActivity.this.callRequestModel.getDomain(), SlsVoIPActivity.this.callRequestModel.getPort(), SlsVoIPActivity.this.callRequestModel.getCallerUser(), SlsVoIPActivity.this.callRequestModel.getCallerPassword());
                    }
                }, 500);
            }
            else {
                ToastUtil.a(this.getApplicationContext(), "User is busy").show();
                this.finish();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if(this.firsttime) {
            this.sipWrapper.notifyCurrentState();
        }

        this.firsttime = true;
    }

    public void onSignalBarsCountChanged(int arg2) {
        this.runOnUiThread(new Runnable(arg2) {
            public void run() {
                SlsVoIPActivity.this.signalBarsCount = this.val$count;
                SlsVoIPActivity.this.brandingText.invalidate();
            }
        });
    }

    public void onStateChanged(int arg3) {
        int v0 = this.callState;
        this.callState = arg3;
        this.runOnUiThread(new Runnable(arg3, v0) {
            public void run() {
                SpannableStringBuilder v1_3;
                Object[] v3_1;
                int v0_2;
                String v1_2;
                SlsVoIPActivity v0_1;
                int v7_1;
                boolean v0 = SlsVoIPActivity.this.firstStateChange;
                int v2 = 2097152;
                int v3 = 15;
                int v4 = 3;
                if(SlsVoIPActivity.this.firstStateChange) {
                    SlsVoIPActivity.this.spkToggle.setChecked(SlsVoIPActivity.this.getSystemService("audio").isSpeakerphoneOn());
                    SlsVoIPActivity v1 = SlsVoIPActivity.this;
                    boolean v7 = this.val$state == v3 ? true : false;
                    boolean v1_1 = SlsVoIPActivity.access$2902(v1, v7);
                    v7_1 = 8;
                    if(v1_1) {
                        SlsVoIPActivity.this.swipeViewsWrap.setVisibility(0);
                        SlsVoIPActivity.this.endBtn.setVisibility(v7_1);
                        SlsVoIPActivity.this.micToggle.setVisibility(v7_1);
                        SlsVoIPActivity.this.spkToggle.setVisibility(v7_1);
                        SlsVoIPActivity.this.chatBtn.setVisibility(v7_1);
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                this.this$1.this$0.acceptSwipe.startAnimatingArrows();
                                this.this$1.this$0.declineSwipe.startAnimatingArrows();
                            }
                        }, 500);
                        SlsVoIPActivity.this.getWindow().addFlags(v2);
                    }
                    else {
                        SlsVoIPActivity.this.swipeViewsWrap.setVisibility(v7_1);
                        SlsVoIPActivity.this.acceptBtn.setVisibility(v7_1);
                        SlsVoIPActivity.this.declineBtn.setVisibility(v7_1);
                        SlsVoIPActivity.this.getWindow().clearFlags(v2);
                    }

                    if(this.val$state != v4) {
                        SlsVoIPActivity.this.emojiWrap.setVisibility(v7_1);
                    }

                    SlsVoIPActivity.this.firstStateChange = false;
                }

                v7_1 = 10;
                int v8 = 11;
                if((SlsVoIPActivity.this.isIncomingWaiting) && this.val$state != v3 && this.val$state != v8 && this.val$state != v7_1) {
                    SlsVoIPActivity.this.isIncomingWaiting = false;
                    if(!SlsVoIPActivity.this.didAcceptFromHere) {
                        SlsVoIPActivity.this.callAccepted();
                    }
                }

                if(this.val$state == v3) {
                    SlsVoIPActivity.this.setStateTextAnimated(LocaleController.getString("VoipIncoming", 2131626383), false);
                    SlsVoIPActivity.this.getWindow().addFlags(v2);
                }
                else {
                    if(this.val$state == 1 || this.val$state == 2) {
                        v0_1 = SlsVoIPActivity.this;
                        v1_2 = "VoipConnecting";
                        v2 = 2131626372;
                    }
                    else if(this.val$state == 12) {
                        v0_1 = SlsVoIPActivity.this;
                        v1_2 = "VoipExchangingKeys";
                        v2 = 2131626376;
                    }
                    else {
                        v3 = 2131626404;
                        if(this.val$state == 13) {
                            SlsVoIPActivity.this.setStateTextAnimated(LocaleController.getString("VoipWaiting", v3), true);
                            SlsVoIPService.getSharedInstance().stopConnectingSound();
                            goto label_322;
                        }
                        else if(this.val$state == 16) {
                            v0_1 = SlsVoIPActivity.this;
                            v1_2 = "VoipRinging";
                            v2 = 2131626400;
                        }
                        else if(this.val$state == 14) {
                            v0_1 = SlsVoIPActivity.this;
                            v1_2 = "VoipRequesting";
                            v2 = 2131626399;
                        }
                        else if(this.val$state == v7_1) {
                            SlsVoIPActivity.this.setStateTextAnimated(LocaleController.getString("VoipHangingUp", 2131626380), true);
                            SlsVoIPActivity.this.endBtnIcon.setAlpha(0.5f);
                            SlsVoIPActivity.this.endBtn.setEnabled(false);
                            goto label_322;
                        }
                        else {
                            long v9 = 200;
                            if(this.val$state == v8) {
                                SlsVoIPActivity.this.setStateTextAnimated(LocaleController.getString("VoipCallEnded", 2131626371), false);
                                SlsVoIPActivity.this.stateText.postDelayed(new Runnable() {
                                    public void run() {
                                        this.this$1.this$0.finish();
                                        if(this.this$1.this$0.linphonePrefs.c()) {
                                            Intent v0 = new Intent(this.this$1.this$0.getApplicationContext(), SlsVoIPFeedbackActivity.class);
                                            SerializedData v1 = new SerializedData();
                                            this.this$1.this$0.user.serializeToStream(((AbstractSerializedData)v1));
                                            v0.putExtra("EXTRA_TELEGRAM_USER", v1.toByteArray());
                                            this.this$1.this$0.linphonePrefs.a(false);
                                            this.this$1.this$0.startActivity(v0);
                                        }
                                    }
                                }, v9);
                            }
                            else if(this.val$state == 17) {
                                SlsVoIPActivity.this.setStateTextAnimated(LocaleController.getString("VoipBusy", 2131626370), false);
                                SlsVoIPActivity.this.showRetry();
                            }
                            else {
                                v2 = 5;
                                if(this.val$state != v4) {
                                    if(this.val$state == v2) {
                                    }
                                    else {
                                        if(this.val$state == 4) {
                                            SlsVoIPActivity.this.setStateTextAnimated(LocaleController.getString("VoipWaiting", v3), true);
                                            v0_2 = VoIPService.getSharedInstance() != null ? VoIPService.getSharedInstance().getLastError() : 0;
                                            if(v0_2 == 1) {
                                                v0_1 = SlsVoIPActivity.this;
                                                v1_2 = "VoipPeerIncompatible";
                                                v2 = 2131626394;
                                                v3_1 = new Object[]{ContactsController.formatName(SlsVoIPActivity.this.user.first_name, SlsVoIPActivity.this.user.last_name)};
                                                goto label_207;
                                            }
                                            else if(v0_2 == -1) {
                                                v0_1 = SlsVoIPActivity.this;
                                                v1_2 = "VoipPeerOutdated";
                                                v2 = 2131626395;
                                                v3_1 = new Object[]{ContactsController.formatName(SlsVoIPActivity.this.user.first_name, SlsVoIPActivity.this.user.last_name)};
                                                goto label_207;
                                            }
                                            else if(v0_2 == -2) {
                                                v0_1 = SlsVoIPActivity.this;
                                                v1_2 = "CallNotAvailable";
                                                v2 = 2131624246;
                                                v3_1 = new Object[]{ContactsController.formatName(SlsVoIPActivity.this.user.first_name, SlsVoIPActivity.this.user.last_name)};
                                            label_207:
                                                v1_3 = AndroidUtilities.replaceTags(LocaleController.formatString(v1_2, v2, v3_1));
                                            }
                                            else if(v0_2 == v4) {
                                                v0_1 = SlsVoIPActivity.this;
                                                v1_2 = "Error initializing audio hardware";
                                            }
                                            else {
                                                goto label_245;
                                            }

                                            v0_1.showErrorDialog(((CharSequence)v1_3));
                                            goto label_322;
                                        label_245:
                                            if(v0_2 == -3) {
                                                SlsVoIPActivity.this.finish();
                                                goto label_322;
                                            }

                                            SlsVoIPActivity.this.stateText.postDelayed(new Runnable() {
                                                public void run() {
                                                }
                                            }, 1000);
                                        }
                                        else {
                                        }

                                        goto label_322;
                                    }
                                }

                                if(!v0 && this.val$state == v4) {
                                    v0_2 = SlsVoIPActivity.this.getSharedPreferences("mainconfig", 0).getInt("call_emoji_tooltip_count", 0);
                                    if(v0_2 < v4) {
                                        SlsVoIPActivity.this.setEmojiTooltipVisible(true);
                                        SlsVoIPActivity.this.hintTextView.postDelayed(SlsVoIPActivity.access$1702(SlsVoIPActivity.this, new Runnable() {
                                            public void run() {
                                                this.this$1.this$0.tooltipHider = null;
                                                this.this$1.this$0.setEmojiTooltipVisible(false);
                                            }
                                        }), 5000);
                                        SlsVoIPActivity.this.getSharedPreferences("mainconfig", 0).edit().putInt("call_emoji_tooltip_count", v0_2 + 1).apply();
                                    }
                                }

                                if(this.val$prevState == v4) {
                                    goto label_322;
                                }

                                if(this.val$prevState == v2) {
                                    goto label_322;
                                }

                                SlsVoIPActivity.this.setStateTextAnimated("0:00", false);
                                SlsVoIPActivity.this.startUpdatingCallDuration();
                                SlsVoIPActivity.this.updateKeyView();
                                if(SlsVoIPActivity.this.emojiWrap.getVisibility() == 0) {
                                    goto label_322;
                                }

                                SlsVoIPActivity.this.emojiWrap.setVisibility(0);
                                SlsVoIPActivity.this.emojiWrap.setAlpha(0f);
                                SlsVoIPActivity.this.emojiWrap.animate().alpha(1f).setDuration(v9).setInterpolator(new DecelerateInterpolator()).start();
                            }

                            goto label_322;
                        }
                    }

                    v0_1.setStateTextAnimated(LocaleController.getString(v1_2, v2), true);
                }

            label_322:
                SlsVoIPActivity.this.brandingText.invalidate();
            }
        });
    }

    private void setEmojiExpanded(boolean arg17) {
        org.telegram.ui.SlsVoIPActivity$30 v2_4;
        float[] v6_1;
        float[] v7_1;
        AnimatorSet v1_2;
        SlsVoIPActivity v0 = this;
        boolean v1 = arg17;
        if(v0.emojiExpanded == v1) {
            return;
        }

        v0.emojiExpanded = v1;
        if(v0.emojiAnimator != null) {
            v0.emojiAnimator.cancel();
        }

        int v5 = 5;
        int v6 = 4;
        int v7 = 7;
        float v8 = 1f;
        int v9 = 3;
        int v10 = 2;
        if(v1) {
            int[] v1_1 = new int[]{0, 0};
            int[] v13 = new int[]{0, 0};
            v0.emojiWrap.getLocationInWindow(v1_1);
            v0.emojiExpandedText.getLocationInWindow(v13);
            Rect v14 = new Rect();
            this.getWindow().getDecorView().getGlobalVisibleRect(v14);
            int v13_1 = v13[1] - (v1_1[1] + v0.emojiWrap.getHeight()) - AndroidUtilities.dp(32f) - v0.emojiWrap.getHeight();
            int v2 = v14.width() / v10 - Math.round((((float)v0.emojiWrap.getWidth())) * 2.5f) / v10 - v1_1[0];
            v1_2 = new AnimatorSet();
            Animator[] v3 = new Animator[v7];
            v3[0] = ObjectAnimator.ofFloat(v0.emojiWrap, "translationY", new float[]{((float)v13_1)});
            v3[1] = ObjectAnimator.ofFloat(v0.emojiWrap, "translationX", new float[]{((float)v2)});
            v3[v10] = ObjectAnimator.ofFloat(v0.emojiWrap, "scaleX", new float[]{2.5f});
            v3[v9] = ObjectAnimator.ofFloat(v0.emojiWrap, "scaleY", new float[]{2.5f});
            ImageView v2_1 = v0.blurOverlayView1;
            v7_1 = new float[v9];
            v7_1[0] = v0.blurOverlayView1.getAlpha();
            v7_1[1] = v8;
            v7_1[v10] = v8;
            v3[v6] = ObjectAnimator.ofFloat(v2_1, "alpha", v7_1);
            v2_1 = v0.blurOverlayView2;
            v6_1 = new float[v9];
            v6_1[0] = v0.blurOverlayView2.getAlpha();
            v6_1[1] = v0.blurOverlayView2.getAlpha();
            v6_1[v10] = v8;
            v3[v5] = ObjectAnimator.ofFloat(v2_1, "alpha", v6_1);
            v3[6] = ObjectAnimator.ofFloat(v0.emojiExpandedText, "alpha", new float[]{v8});
            v1_2.playTogether(v3);
            v1_2.setDuration(300);
            v1_2.setInterpolator(CubicBezierInterpolator.DEFAULT);
            v0.emojiAnimator = v1_2;
            org.telegram.ui.SlsVoIPActivity$29 v2_2 = new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    SlsVoIPActivity.this.emojiAnimator = null;
                }
            };
        }
        else {
            v1_2 = new AnimatorSet();
            Animator[] v2_3 = new Animator[v7];
            v2_3[0] = ObjectAnimator.ofFloat(v0.emojiWrap, "translationX", new float[]{0f});
            v2_3[1] = ObjectAnimator.ofFloat(v0.emojiWrap, "translationY", new float[]{0f});
            v2_3[v10] = ObjectAnimator.ofFloat(v0.emojiWrap, "scaleX", new float[]{v8});
            v2_3[v9] = ObjectAnimator.ofFloat(v0.emojiWrap, "scaleY", new float[]{v8});
            ImageView v3_1 = v0.blurOverlayView1;
            v7_1 = new float[v9];
            v7_1[0] = v0.blurOverlayView1.getAlpha();
            v7_1[1] = v0.blurOverlayView1.getAlpha();
            v7_1[v10] = 0f;
            v2_3[v6] = ObjectAnimator.ofFloat(v3_1, "alpha", v7_1);
            v3_1 = v0.blurOverlayView2;
            v6_1 = new float[v9];
            v6_1[0] = v0.blurOverlayView2.getAlpha();
            v6_1[1] = 0f;
            v6_1[v10] = 0f;
            v2_3[v5] = ObjectAnimator.ofFloat(v3_1, "alpha", v6_1);
            v2_3[6] = ObjectAnimator.ofFloat(v0.emojiExpandedText, "alpha", new float[]{0f});
            v1_2.playTogether(v2_3);
            v1_2.setDuration(300);
            v1_2.setInterpolator(CubicBezierInterpolator.DEFAULT);
            v0.emojiAnimator = v1_2;
            v2_4 = new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    SlsVoIPActivity.this.emojiAnimator = null;
                }
            };
        }

        v1_2.addListener(((Animator$AnimatorListener)v2_4));
        v1_2.start();
    }

    private void setEmojiTooltipVisible(boolean arg5) {
        this.emojiTooltipVisible = arg5;
        if(this.tooltipAnim != null) {
            this.tooltipAnim.cancel();
        }

        this.hintTextView.setVisibility(0);
        TextView v0 = this.hintTextView;
        String v2 = "alpha";
        float[] v3 = new float[1];
        float v5 = arg5 ? 1f : 0f;
        v3[0] = v5;
        ObjectAnimator v5_1 = ObjectAnimator.ofFloat(v0, v2, v3);
        v5_1.setDuration(300);
        v5_1.setInterpolator(CubicBezierInterpolator.DEFAULT);
        v5_1.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                SlsVoIPActivity.this.tooltipAnim = null;
            }
        });
        this.tooltipAnim = ((Animator)v5_1);
        v5_1.start();
    }

    private void setStateTextAnimated(String arg10, boolean arg11) {
        String v11_1;
        if(arg10.equals(this.lastStateText)) {
            return;
        }

        this.lastStateText = arg10;
        if(this.textChangingAnim != null) {
            this.textChangingAnim.cancel();
        }

        int v0 = 3;
        int v3 = 2;
        if(arg11) {
            if(!this.ellAnimator.isRunning()) {
                this.ellAnimator.start();
            }

            SpannableStringBuilder v11 = new SpannableStringBuilder(arg10.toUpperCase());
            TextAlphaSpan[] v10 = this.ellSpans;
            int v4 = v10.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                v10[v5].setAlpha(0);
            }

            SpannableString v10_1 = new SpannableString("...");
            v10_1.setSpan(this.ellSpans[0], 0, 1, 0);
            v10_1.setSpan(this.ellSpans[1], 1, v3, 0);
            v10_1.setSpan(this.ellSpans[v3], v3, v0, 0);
            v11.append(((CharSequence)v10_1));
        }
        else {
            if(this.ellAnimator.isRunning()) {
                this.ellAnimator.cancel();
            }

            v11_1 = arg10.toUpperCase();
        }

        this.stateText2.setText(((CharSequence)v11_1));
        this.stateText2.setVisibility(0);
        TextView v10_2 = this.stateText;
        float v11_2 = LocaleController.isRTL ? ((float)this.stateText.getWidth()) : 0f;
        v10_2.setPivotX(v11_2);
        this.stateText.setPivotY(((float)(this.stateText.getHeight() / v3)));
        v10_2 = this.stateText2;
        v11_2 = LocaleController.isRTL ? ((float)this.stateText.getWidth()) : 0f;
        v10_2.setPivotX(v11_2);
        this.stateText2.setPivotY(((float)(this.stateText.getHeight() / v3)));
        this.durationText = this.stateText2;
        AnimatorSet v10_3 = new AnimatorSet();
        Animator[] v11_3 = new Animator[8];
        v11_3[0] = ObjectAnimator.ofFloat(this.stateText2, "alpha", new float[]{0f, 1f});
        TextView v5_1 = this.stateText2;
        float[] v7 = new float[v3];
        v7[0] = ((float)(this.stateText.getHeight() / v3));
        v7[1] = 0f;
        v11_3[1] = ObjectAnimator.ofFloat(v5_1, "translationY", v7);
        v11_3[v3] = ObjectAnimator.ofFloat(this.stateText2, "scaleX", new float[]{0.7f, 1f});
        v11_3[v0] = ObjectAnimator.ofFloat(this.stateText2, "scaleY", new float[]{0.7f, 1f});
        v11_3[4] = ObjectAnimator.ofFloat(this.stateText, "alpha", new float[]{1f, 0f});
        v5_1 = this.stateText;
        v7 = new float[v3];
        v7[0] = 0f;
        v7[1] = ((float)(-this.stateText.getHeight() / v3));
        v11_3[5] = ObjectAnimator.ofFloat(v5_1, "translationY", v7);
        v11_3[6] = ObjectAnimator.ofFloat(this.stateText, "scaleX", new float[]{1f, 0.7f});
        v11_3[7] = ObjectAnimator.ofFloat(this.stateText, "scaleY", new float[]{1f, 0.7f});
        v10_3.playTogether(v11_3);
        v10_3.setDuration(200);
        v10_3.setInterpolator(CubicBezierInterpolator.DEFAULT);
        v10_3.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                SlsVoIPActivity.this.textChangingAnim = null;
                SlsVoIPActivity.this.stateText2.setVisibility(8);
                SlsVoIPActivity.this.durationText = SlsVoIPActivity.this.stateText;
                SlsVoIPActivity.this.stateText.setTranslationY(0f);
                SlsVoIPActivity.this.stateText.setScaleX(1f);
                SlsVoIPActivity.this.stateText.setScaleY(1f);
                SlsVoIPActivity.this.stateText.setAlpha(1f);
                SlsVoIPActivity.this.stateText.setText(SlsVoIPActivity.this.stateText2.getText());
            }
        });
        this.textChangingAnim = ((Animator)v10_3);
        v10_3.start();
    }

    void showEndCallDialog() {
        this.endCalllDialog = new Dialog(((Context)this));
        this.endCalllDialog.requestWindowFeature(1);
        this.endCalllDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.endCalllDialog.setContentView(2131492979);
        this.endCalllDialog.getWindow().setSoftInputMode(4);
        this.dialogCallerName = this.endCalllDialog.findViewById(2131296508);
        this.dialogProfilePic = this.endCalllDialog.findViewById(2131296606);
        this.dialogCallRatingBar = this.endCalllDialog.findViewById(2131296389);
        this.dialogRateStatus = this.endCalllDialog.findViewById(2131296823);
        this.dialogExplanation = this.endCalllDialog.findViewById(2131296487);
        this.dialogSubmitRate = this.endCalllDialog.findViewById(2131296478);
        this.dialogSubmitRate.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
            }
        });
        this.dialogCallRatingBar.setOnRatingBarChangeListener(new RatingBar$OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar arg1, float arg2, boolean arg3) {
                if(arg2 == 5f) {
                    SlsVoIPActivity.this.dialogRateStatus.setText("عالی");
                }

                if(arg2 == 4f) {
                    SlsVoIPActivity.this.dialogRateStatus.setText("خیلی خوب");
                }

                if(arg2 == 3f) {
                    SlsVoIPActivity.this.dialogRateStatus.setText("خوب");
                }

                if(arg2 == 2f) {
                    SlsVoIPActivity.this.dialogRateStatus.setText("متوسط");
                }

                if(arg2 == 1f) {
                    SlsVoIPActivity.this.dialogRateStatus.setText("ضعیف");
                }
            }
        });
        this.endCalllDialog.show();
        this.endCalllDialog.getWindow().setLayout(-1, -2);
    }

    private void showErrorDialog(CharSequence arg4) {
        AlertDialog v4 = new Builder(((Context)this)).setTitle(LocaleController.getString("VoipFailed", 2131626377)).setMessage(arg4).setPositiveButton(LocaleController.getString("OK", 2131625420), null).show();
        v4.setCanceledOnTouchOutside(true);
        v4.setOnDismissListener(new DialogInterface$OnDismissListener() {
            public void onDismiss(DialogInterface arg1) {
                SlsVoIPActivity.this.finish();
            }
        });
    }

    private void showRetry() {
        ObjectAnimator v4;
        if(this.retryAnim != null) {
            this.retryAnim.cancel();
        }

        this.endBtn.setEnabled(false);
        this.retrying = true;
        this.cancelBtn.setVisibility(0);
        this.cancelBtn.setAlpha(0f);
        AnimatorSet v2 = new AnimatorSet();
        int v6 = 2;
        if(Build$VERSION.SDK_INT >= 21) {
            v4 = ObjectAnimator.ofArgb(this.endBtnBg, "color", new int[]{-1696188, -12207027});
        }
        else {
            v4 = ObjectAnimator.ofInt(this.endBtnBg, "color", new int[]{-1696188, -12207027});
            v4.setEvaluator(new ArgbEvaluator());
        }

        Animator[] v5 = new Animator[7];
        v5[0] = ObjectAnimator.ofFloat(this.cancelBtn, "alpha", new float[]{0f, 1f});
        View v7 = this.endBtn;
        float[] v9 = new float[v6];
        v9[0] = 0f;
        v9[1] = ((float)(this.content.getWidth() / v6 - AndroidUtilities.dp(52f) - this.endBtn.getWidth() / v6));
        v5[1] = ObjectAnimator.ofFloat(v7, "translationX", v9);
        v5[v6] = v4;
        v5[3] = ObjectAnimator.ofFloat(this.endBtnIcon, "rotation", new float[]{0f, -135f});
        v5[4] = ObjectAnimator.ofFloat(this.spkToggle, "alpha", new float[]{0f});
        v5[5] = ObjectAnimator.ofFloat(this.micToggle, "alpha", new float[]{0f});
        v5[6] = ObjectAnimator.ofFloat(this.chatBtn, "alpha", new float[]{0f});
        v2.playTogether(v5);
        v2.setStartDelay(200);
        v2.setDuration(300);
        v2.setInterpolator(CubicBezierInterpolator.DEFAULT);
        v2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                SlsVoIPActivity.this.spkToggle.setVisibility(8);
                SlsVoIPActivity.this.micToggle.setVisibility(8);
                SlsVoIPActivity.this.chatBtn.setVisibility(8);
                SlsVoIPActivity.this.retryAnim = null;
                SlsVoIPActivity.this.endBtn.setEnabled(true);
            }
        });
        this.retryAnim = v2;
        v2.start();
    }

    private void startUpdatingCallDuration() {
        new Runnable() {
            public void run() {
                String v1_2;
                if(!SlsVoIPActivity.this.isFinishing()) {
                    if(!SlsVoIPActivity.this.sipWrapper.isInCall()) {
                    }
                    else {
                        int v1 = 3;
                        if(SlsVoIPActivity.this.callState != v1 && SlsVoIPActivity.this.callState != 5) {
                            return;
                        }

                        long v2 = SlsVoIPActivity.this.sipWrapper.getCallDuration() / 1000;
                        TextView v0 = SlsVoIPActivity.this.durationText;
                        long v4 = 3600;
                        int v7 = 2;
                        long v10 = 60;
                        if(Long.compare(v2, v4) > 0) {
                            Object[] v1_1 = new Object[v1];
                            v1_1[0] = Long.valueOf(v2 / v4);
                            v1_1[1] = Long.valueOf(v2 % v4 / v10);
                            v1_1[v7] = Long.valueOf(v2 % v10);
                            v1_2 = String.format("%d:%02d:%02d", v1_1);
                        }
                        else {
                            v1_2 = String.format("%d:%02d", Long.valueOf(v2 / v10), Long.valueOf(v2 % v10));
                        }

                        v0.setText(((CharSequence)v1_2));
                        SlsVoIPActivity.this.durationText.postDelayed(((Runnable)this), 500);
                    }
                }
            }
        }.run();
    }

    private void updateBlurredPhotos(Bitmap arg3) {
        new Thread(new Runnable(arg3) {
            public void run() {
                Bitmap v0 = Bitmap.createBitmap(150, 150, Bitmap$Config.ARGB_8888);
                Canvas v8 = new Canvas(v0);
                v8.drawBitmap(this.val$src, null, new Rect(0, 0, 150, 150), new Paint(2));
                Utilities.blurBitmap(v0, 3, 0, v0.getWidth(), v0.getHeight(), v0.getRowBytes());
                b v1 = b.a(this.val$src).a();
                Paint v12 = new Paint();
                v12.setColor(v1.a(-11242343) & 16777215 | 1140850688);
                v8.drawColor(637534208);
                v8.drawRect(0f, 0f, ((float)v8.getWidth()), ((float)v8.getHeight()), v12);
                Bitmap v1_1 = Bitmap.createBitmap(50, 50, Bitmap$Config.ARGB_8888);
                Canvas v13 = new Canvas(v1_1);
                v13.drawBitmap(this.val$src, null, new Rect(0, 0, 50, 50), new Paint(2));
                Utilities.blurBitmap(v1_1, 3, 0, v1_1.getWidth(), v1_1.getHeight(), v1_1.getRowBytes());
                v12.setAlpha(102);
                v13.drawRect(0f, 0f, ((float)v13.getWidth()), ((float)v13.getHeight()), v12);
                SlsVoIPActivity.this.blurredPhoto1 = v0;
                SlsVoIPActivity.this.blurredPhoto2 = v1_1;
                SlsVoIPActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        this.this$1.this$0.blurOverlayView1.setImageBitmap(this.this$1.this$0.blurredPhoto1);
                        this.this$1.this$0.blurOverlayView2.setImageBitmap(this.this$1.this$0.blurredPhoto2);
                    }
                });
            }
        }).start();
    }

    private void updateKeyView() {
        if(VoIPService.getSharedInstance() == null) {
            return;
        }

        int v1 = 4;
        new IdenticonDrawable().setColors(new int[]{16777215, -1, -1711276033, 872415231});
        TL_encryptedChat v0 = new TL_encryptedChat();
        try {
            ByteArrayOutputStream v2 = new ByteArrayOutputStream();
            v2.write(VoIPService.getSharedInstance().getEncryptionKey());
            v2.write(VoIPService.getSharedInstance().getGA());
            ((EncryptedChat)v0).auth_key = v2.toByteArray();
            goto label_20;
        }
        catch(Exception ) {
        label_20:
            String[] v0_1 = EncryptionKeyEmojifier.emojifyForCall(Utilities.computeSHA256(((EncryptedChat)v0).auth_key, 0, ((EncryptedChat)v0).auth_key.length));
            int v2_1;
            for(v2_1 = 0; v2_1 < v1; ++v2_1) {
                EmojiDrawable v4 = Emoji.getEmojiDrawable(v0_1[v2_1]);
                if(v4 != null) {
                    ((Drawable)v4).setBounds(0, 0, AndroidUtilities.dp(22f), AndroidUtilities.dp(22f));
                    this.keyEmojiViews[v2_1].setImageDrawable(((Drawable)v4));
                }
            }

            return;
        }
    }
}

