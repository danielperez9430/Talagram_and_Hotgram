package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.os.Build$VERSION;
import android.text.SpannableStringBuilder;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities$LinkMovementMethodMy;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_deleteAccount;
import org.telegram.tgnet.TLRPC$TL_boolTrue;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_help_acceptTermsOfService;
import org.telegram.tgnet.TLRPC$TL_help_termsOfService;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

public class TermsOfServiceView extends FrameLayout {
    public interface TermsOfServiceViewDelegate {
        void onAcceptTerms(int arg1);

        void onDeclineTerms(int arg1);
    }

    private int currentAccount;
    private TL_help_termsOfService currentTos;
    private TermsOfServiceViewDelegate delegate;
    private TextView textView;

    public TermsOfServiceView(Context arg24) {
        TermsOfServiceView v0 = this;
        Context v1 = arg24;
        super(arg24);
        v0.setBackgroundColor(Theme.getColor("windowBackgroundWhite"));
        int v3 = 21;
        int v2 = Build$VERSION.SDK_INT >= v3 ? ((int)((((float)AndroidUtilities.statusBarHeight)) / AndroidUtilities.density)) : 0;
        int v6 = -1;
        if(Build$VERSION.SDK_INT >= v3) {
            View v5 = new View(v1);
            v5.setBackgroundColor(-16777216);
            v0.addView(v5, new FrameLayout$LayoutParams(v6, AndroidUtilities.statusBarHeight));
        }

        ImageView v5_1 = new ImageView(v1);
        v5_1.setImageResource(2131231334);
        v0.addView(((View)v5_1), LayoutHelper.createFrame(-2, -2f, 49, 0f, ((float)(v2 + 30)), 0f, 0f));
        TextView v5_2 = new TextView(v1);
        v5_2.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v5_2.setTextSize(1, 17f);
        v5_2.setGravity(51);
        v5_2.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v5_2.setText(LocaleController.getString("PrivacyPolicyAndTerms", 2131625807));
        v0.addView(((View)v5_2), LayoutHelper.createFrame(-2, -2f, 51, 27f, ((float)(v2 + 126)), 27f, 75f));
        ScrollView v5_3 = new ScrollView(v1);
        AndroidUtilities.setScrollViewEdgeEffectColor(v5_3, Theme.getColor("actionBarDefault"));
        v0.addView(((View)v5_3), LayoutHelper.createFrame(-2, -1f, 51, 27f, ((float)(v2 + 160)), 27f, 75f));
        v0.textView = new TextView(v1);
        v0.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.textView.setLinkTextColor(Theme.getColor("windowBackgroundWhiteLinkText"));
        v0.textView.setTextSize(1, 15f);
        v0.textView.setMovementMethod(new LinkMovementMethodMy());
        v0.textView.setGravity(51);
        float v7 = 2f;
        v0.textView.setLineSpacing(((float)AndroidUtilities.dp(v7)), 1f);
        v5_3.addView(v0.textView, new FrameLayout$LayoutParams(-2, -2));
        TextView v2_1 = new TextView(v1);
        v2_1.setText(LocaleController.getString("Decline", 2131624557).toUpperCase());
        v2_1.setGravity(17);
        v2_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v2_1.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText"));
        v2_1.setTextSize(1, 16f);
        float v10 = 20f;
        float v12 = 10f;
        v2_1.setPadding(AndroidUtilities.dp(v10), AndroidUtilities.dp(v12), AndroidUtilities.dp(v10), AndroidUtilities.dp(v12));
        v0.addView(((View)v2_1), LayoutHelper.createFrame(-2, -2f, 83, 16f, 0f, 16f, 16f));
        v2_1.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                Builder v0 = new Builder(arg3.getContext());
                v0.setTitle(LocaleController.getString("TermsOfService", 2131626216));
                v0.setPositiveButton(LocaleController.getString("DeclineDeactivate", 2131624558), new DialogInterface$OnClickListener() {
                    public void onClick(DialogInterface arg2, int arg3) {
                        Builder v2 = new Builder(this.this$1.this$0.getContext());
                        v2.setMessage(LocaleController.getString("TosDeclineDeleteAccount", 2131626251));
                        v2.setTitle(LocaleController.getString("AppName", 2131624086));
                        v2.setPositiveButton(LocaleController.getString("Deactivate", 2131624542), new DialogInterface$OnClickListener() {
                            public void onClick(DialogInterface arg3, int arg4) {
                                AlertDialog v3 = new AlertDialog(this.this$2.this$1.this$0.getContext(), 1);
                                v3.setMessage(LocaleController.getString("Loading", 2131625103));
                                v3.setCanceledOnTouchOutside(false);
                                v3.setCancelable(false);
                                TL_account_deleteAccount v4 = new TL_account_deleteAccount();
                                v4.reason = "Decline ToS update";
                                ConnectionsManager.getInstance(TermsOfServiceView.access$000(this.this$2.this$1.this$0)).sendRequest(((TLObject)v4), new RequestDelegate(v3) {
                                    public void run(TLObject arg2, TL_error arg3) {
                                        AndroidUtilities.runOnUIThread(new Runnable(arg2, arg3) {
                                            public void run() {
                                                try {
                                                    this.this$4.val$progressDialog.dismiss();
                                                }
                                                catch(Exception v0) {
                                                    FileLog.e(((Throwable)v0));
                                                }

                                                if((this.val$response instanceof TL_boolTrue)) {
                                                    MessagesController.getInstance(TermsOfServiceView.access$000(this.this$4.this$3.this$2.this$1.this$0)).performLogout(0);
                                                }
                                                else {
                                                    String v0_1 = LocaleController.getString("ErrorOccurred", 2131624696);
                                                    if(this.val$error != null) {
                                                        v0_1 = v0_1 + "\n" + this.val$error.text;
                                                    }

                                                    Builder v1_1 = new Builder(this.this$4.this$3.this$2.this$1.this$0.getContext());
                                                    v1_1.setTitle(LocaleController.getString("AppName", 2131624086));
                                                    v1_1.setMessage(((CharSequence)v0_1));
                                                    v1_1.setPositiveButton(LocaleController.getString("OK", 2131625420), null);
                                                    v1_1.show();
                                                }
                                            }
                                        });
                                    }
                                });
                                v3.show();
                            }
                        });
                        v2.setNegativeButton(LocaleController.getString("Cancel", 2131624257), null);
                        v2.show();
                    }
                });
                v0.setNegativeButton(LocaleController.getString("Back", 2131624198), null);
                v0.setMessage(LocaleController.getString("TosUpdateDecline", 2131626252));
                v0.show();
            }
        });
        v2_1 = new TextView(v1);
        v2_1.setText(LocaleController.getString("Accept", 2131623941).toUpperCase());
        v2_1.setGravity(17);
        v2_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v2_1.setTextColor(v6);
        v2_1.setTextSize(1, 16f);
        v2_1.setBackgroundResource(2131231520);
        if(Build$VERSION.SDK_INT >= v3) {
            StateListAnimator v1_1 = new StateListAnimator();
            v1_1.addState(new int[]{16842919}, ObjectAnimator.ofFloat(v2_1, "translationZ", new float[]{((float)AndroidUtilities.dp(v7)), ((float)AndroidUtilities.dp(4f))}).setDuration(200));
            v1_1.addState(new int[0], ObjectAnimator.ofFloat(v2_1, "translationZ", new float[]{((float)AndroidUtilities.dp(4f)), ((float)AndroidUtilities.dp(v7))}).setDuration(200));
            v2_1.setStateListAnimator(v1_1);
        }

        v2_1.setPadding(AndroidUtilities.dp(v10), AndroidUtilities.dp(v12), AndroidUtilities.dp(v10), AndroidUtilities.dp(v12));
        v0.addView(((View)v2_1), LayoutHelper.createFrame(-2, -2f, 85, 16f, 0f, 16f, 16f));
        v2_1.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg7) {
                if(TermsOfServiceView.access$100(TermsOfServiceView.this).min_age_confirm != 0) {
                    Builder v0 = new Builder(arg7.getContext());
                    v0.setTitle(LocaleController.getString("TosAgeTitle", 2131626249));
                    v0.setPositiveButton(LocaleController.getString("Agree", 2131624048), new DialogInterface$OnClickListener() {
                        public void onClick(DialogInterface arg1, int arg2) {
                            TermsOfServiceView.access$200(this.this$1.this$0);
                        }
                    });
                    v0.setNegativeButton(LocaleController.getString("Cancel", 2131624257), null);
                    v0.setMessage(LocaleController.formatString("TosAgeText", 2131626248, new Object[]{LocaleController.formatPluralString("Years", TermsOfServiceView.access$100(TermsOfServiceView.this).min_age_confirm)}));
                    v0.show();
                }
                else {
                    TermsOfServiceView.access$200(TermsOfServiceView.this);
                }
            }
        });
    }

    private void accept() {
        this.delegate.onAcceptTerms(this.currentAccount);
        TL_help_acceptTermsOfService v0 = new TL_help_acceptTermsOfService();
        v0.id = this.currentTos.id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate() {
            public void run(TLObject arg1, TL_error arg2) {
            }
        });
    }

    static int access$000(TermsOfServiceView arg0) {
        return arg0.currentAccount;
    }

    static TL_help_termsOfService access$100(TermsOfServiceView arg0) {
        return arg0.currentTos;
    }

    static void access$200(TermsOfServiceView arg0) {
        arg0.accept();
    }

    public void setDelegate(TermsOfServiceViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void show(int arg9, TL_help_termsOfService arg10) {
        if(this.getVisibility() != 0) {
            this.setVisibility(0);
        }

        SpannableStringBuilder v0 = new SpannableStringBuilder(arg10.text);
        MessageObject.addEntitiesToText(v0, arg10.entities, false, 0, false, false, false);
        this.textView.setText(((CharSequence)v0));
        this.currentTos = arg10;
        this.currentAccount = arg9;
    }
}

