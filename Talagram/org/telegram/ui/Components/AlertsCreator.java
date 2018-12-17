package org.telegram.ui.Components;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage$IntCallback;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.SecretChatHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$InputPeer;
import org.telegram.tgnet.TLRPC$ReportReason;
import org.telegram.tgnet.TLRPC$TL_account_changePhone;
import org.telegram.tgnet.TLRPC$TL_account_confirmPhone;
import org.telegram.tgnet.TLRPC$TL_account_getAuthorizationForm;
import org.telegram.tgnet.TLRPC$TL_account_getPassword;
import org.telegram.tgnet.TLRPC$TL_account_getTmpPassword;
import org.telegram.tgnet.TLRPC$TL_account_reportPeer;
import org.telegram.tgnet.TLRPC$TL_account_saveSecureValue;
import org.telegram.tgnet.TLRPC$TL_account_sendChangePhoneCode;
import org.telegram.tgnet.TLRPC$TL_account_sendConfirmPhoneCode;
import org.telegram.tgnet.TLRPC$TL_account_verifyEmail;
import org.telegram.tgnet.TLRPC$TL_account_verifyPhone;
import org.telegram.tgnet.TLRPC$TL_auth_resendCode;
import org.telegram.tgnet.TLRPC$TL_channels_createChannel;
import org.telegram.tgnet.TLRPC$TL_channels_editAdmin;
import org.telegram.tgnet.TLRPC$TL_channels_editBanned;
import org.telegram.tgnet.TLRPC$TL_channels_inviteToChannel;
import org.telegram.tgnet.TLRPC$TL_channels_joinChannel;
import org.telegram.tgnet.TLRPC$TL_contacts_importContacts;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_inputReportReasonPornography;
import org.telegram.tgnet.TLRPC$TL_inputReportReasonSpam;
import org.telegram.tgnet.TLRPC$TL_inputReportReasonViolence;
import org.telegram.tgnet.TLRPC$TL_messages_addChatUser;
import org.telegram.tgnet.TLRPC$TL_messages_createChat;
import org.telegram.tgnet.TLRPC$TL_messages_editMessage;
import org.telegram.tgnet.TLRPC$TL_messages_forwardMessages;
import org.telegram.tgnet.TLRPC$TL_messages_getAttachedStickers;
import org.telegram.tgnet.TLRPC$TL_messages_importChatInvite;
import org.telegram.tgnet.TLRPC$TL_messages_report;
import org.telegram.tgnet.TLRPC$TL_messages_sendBroadcast;
import org.telegram.tgnet.TLRPC$TL_messages_sendInlineBotResult;
import org.telegram.tgnet.TLRPC$TL_messages_sendMedia;
import org.telegram.tgnet.TLRPC$TL_messages_sendMessage;
import org.telegram.tgnet.TLRPC$TL_messages_startBot;
import org.telegram.tgnet.TLRPC$TL_payments_sendPaymentForm;
import org.telegram.tgnet.TLRPC$TL_payments_validateRequestedInfo;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettings;
import org.telegram.tgnet.TLRPC$TL_updateUserName;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.CacheControlActivity;
import org.telegram.ui.Cells.AccountSelectCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.TextColorCell;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileNotificationsActivity;
import org.telegram.ui.ReportOtherActivity;

public class AlertsCreator {
    public interface AccountSelectDelegate {
        void didSelectAccount(int arg1);
    }

    public interface DatePickerDelegate {
        void didSelectDate(int arg1, int arg2, int arg3);
    }

    public interface PaymentAlertDelegate {
        void didPressedNewCard();
    }

    public AlertsCreator() {
        super();
    }

    private static void checkPickerDate(NumberPicker arg4, NumberPicker arg5, NumberPicker arg6) {
        Calendar v0 = Calendar.getInstance();
        v0.setTimeInMillis(System.currentTimeMillis());
        int v1 = v0.get(1);
        int v2 = v0.get(2);
        int v0_1 = v0.get(5);
        if(v1 > arg6.getValue()) {
            arg6.setValue(v1);
        }

        if(arg6.getValue() == v1) {
            if(v2 > arg5.getValue()) {
                arg5.setValue(v2);
            }

            if(v2 != arg5.getValue()) {
                return;
            }

            if(v0_1 <= arg4.getValue()) {
                return;
            }

            arg4.setValue(v0_1);
        }
    }

    public static AlertDialog createAccountSelectDialog(Activity arg10, AccountSelectDelegate arg11) {
        AlertDialog v1 = null;
        if(UserConfig.getActivatedAccountsCount() < 2) {
            return v1;
        }

        Builder v0 = new Builder(((Context)arg10));
        Runnable v2 = v0.getDismissRunnable();
        AlertDialog[] v4 = new AlertDialog[1];
        LinearLayout v5 = new LinearLayout(((Context)arg10));
        v5.setOrientation(1);
        int v6;
        for(v6 = 0; v6 < 3; ++v6) {
            if(UserConfig.getInstance(v6).getCurrentUser() != null) {
                AccountSelectCell v7 = new AccountSelectCell(((Context)arg10));
                v7.setAccount(v6, false);
                v7.setPadding(AndroidUtilities.dp(14f), 0, AndroidUtilities.dp(14f), 0);
                v7.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                v5.addView(((View)v7), LayoutHelper.createLinear(-1, 48));
                v7.setOnClickListener(new -$$Lambda$AlertsCreator$bLmDqrwlye6kRN6M9YsVd__VSdM(v4, v2, arg11));
            }
        }

        v0.setTitle(LocaleController.getString("SelectAccount", 2131625990));
        v0.setView(((View)v5));
        v0.setPositiveButton(LocaleController.getString("Cancel", 2131624257), ((DialogInterface$OnClickListener)v1));
        AlertDialog v10 = v0.create();
        v4[0] = v10;
        return v10;
    }

    public static Dialog createColorSelectDialog(Activity arg14, long arg15, boolean arg17, boolean arg18, Runnable arg19) {
        String v3_1;
        long v7 = arg15;
        SharedPreferences v1 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
        int v2 = -16776961;
        if(!arg17) {
            if(!arg18) {
                StringBuilder v3 = new StringBuilder();
                v3.append("color_");
                v3.append(v7);
                if(v1.contains(v3.toString())) {
                    v3_1 = "color_" + v7;
                    goto label_7;
                }
                else if((((int)v7)) < 0) {
                    goto label_6;
                }
            }

            goto label_10;
        }
        else {
        label_6:
            v3_1 = "GroupLed";
            goto label_7;
        label_10:
            v3_1 = "MessagesLed";
        }

    label_7:
        int v1_1 = v1.getInt(v3_1, v2);
        LinearLayout v2_1 = new LinearLayout(((Context)arg14));
        v2_1.setOrientation(1);
        int v4 = 9;
        String[] v5 = new String[v4];
        v5[0] = LocaleController.getString("ColorRed", 2131624449);
        v5[1] = LocaleController.getString("ColorOrange", 2131624447);
        v5[2] = LocaleController.getString("ColorYellow", 2131624453);
        v5[3] = LocaleController.getString("ColorGreen", 2131624446);
        v5[4] = LocaleController.getString("ColorCyan", 2131624444);
        v5[5] = LocaleController.getString("ColorBlue", 2131624443);
        v5[6] = LocaleController.getString("ColorViolet", 2131624451);
        v5[7] = LocaleController.getString("ColorPink", 2131624448);
        v5[8] = LocaleController.getString("ColorWhite", 2131624452);
        int[] v6 = new int[]{v1_1};
        int v10;
        for(v10 = 0; v10 < v4; ++v10) {
            RadioColorCell v11 = new RadioColorCell(((Context)arg14));
            v11.setPadding(AndroidUtilities.dp(4f), 0, AndroidUtilities.dp(4f), 0);
            v11.setTag(Integer.valueOf(v10));
            v11.setCheckColor(TextColorCell.colors[v10], TextColorCell.colors[v10]);
            String v12 = v5[v10];
            boolean v13 = v1_1 == TextColorCell.colorsToSave[v10] ? true : false;
            v11.setTextAndValue(v12, v13);
            v2_1.addView(((View)v11));
            v11.setOnClickListener(new -$$Lambda$AlertsCreator$7nnIHgrIVWXZctIYdmrSXkd3tuw(v2_1, v6));
        }

        Builder v9 = new Builder(((Context)arg14));
        v9.setTitle(LocaleController.getString("LedColor", 2131625083));
        v9.setView(((View)v2_1));
        v9.setPositiveButton(LocaleController.getString("Set", 2131626038), new -$$Lambda$AlertsCreator$CppK7qvWPqDtEeVHx43ZUlorspQ(arg18, v6, arg17, arg15, arg19));
        v9.setNeutralButton(LocaleController.getString("LedDisabled", 2131625084), new -$$Lambda$AlertsCreator$a_A49UgIOXqouvPmbP-bvfY0gTI(arg18, arg17, arg15, arg19));
        if(!arg18 && !arg17) {
            v9.setNegativeButton(LocaleController.getString("Default", 2131624559), new -$$Lambda$AlertsCreator$x-ufj3OuwmPOMHnDBgVBxPWImeQ(v7, arg19));
        }

        return v9.create();
    }

    public static Builder createContactsPermissionDialog(Activity arg2, IntCallback arg3) {
        Builder v0 = new Builder(((Context)arg2));
        v0.setTopImage(2131231455, Theme.getColor("dialogTopBackground"));
        v0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString("ContactsPermissionAlert", 2131624478)));
        v0.setPositiveButton(LocaleController.getString("ContactsPermissionAlertContinue", 2131624479), new -$$Lambda$AlertsCreator$JDZkbq30O4EpHB-BLTVLO1vfYCY(arg3));
        v0.setNegativeButton(LocaleController.getString("ContactsPermissionAlertNotNow", 2131624480), new -$$Lambda$AlertsCreator$LjwoM7LIkjtGu4zLTnQVOkF_swE(arg3));
        return v0;
    }

    public static Builder createDatePickerDialog(Context arg13, int arg14, int arg15, int arg16, int arg17, int arg18, int arg19, String arg20, boolean arg21, DatePickerDelegate arg22) {
        int v1 = arg17;
        boolean v2 = arg21;
        Builder v3 = null;
        if(arg13 == null) {
            return v3;
        }

        LinearLayout v4 = new LinearLayout(arg13);
        v4.setOrientation(0);
        v4.setWeightSum(1f);
        NumberPicker v6 = new NumberPicker(arg13);
        NumberPicker v7 = new NumberPicker(arg13);
        NumberPicker v8 = new NumberPicker(arg13);
        v4.addView(((View)v7), LayoutHelper.createLinear(0, -2, 0.3f));
        v7.setOnScrollListener(new -$$Lambda$AlertsCreator$v1Wfn8eO9VPKsq2uILPs9qUfzHc(v2, v7, v6, v8));
        v6.setMinValue(0);
        v6.setMaxValue(11);
        v4.addView(((View)v6), LayoutHelper.createLinear(0, -2, 0.3f));
        v6.setFormatter(-$$Lambda$AlertsCreator$dbaE5u7cvkFhf-oz2bxlHkAwsQg.INSTANCE);
        v6.setOnValueChangedListener(new -$$Lambda$AlertsCreator$HEggqc5B19ZI7DIdbnRmtRmZa_o(v7, v6, v8));
        v6.setOnScrollListener(new -$$Lambda$AlertsCreator$LekFzTdYPWlF1R1fsPh6EDACGlw(v2, v7, v6, v8));
        Calendar v9 = Calendar.getInstance();
        v9.setTimeInMillis(System.currentTimeMillis());
        int v9_1 = v9.get(1);
        v8.setMinValue(v9_1 + arg14);
        v8.setMaxValue(v9_1 + arg15);
        v8.setValue(v9_1 + arg16);
        v4.addView(((View)v8), LayoutHelper.createLinear(0, -2, 0.4f));
        v8.setOnValueChangedListener(new -$$Lambda$AlertsCreator$Wxn3wKhEV-_fC0S-EhVdmvk74GI(v7, v6, v8));
        v8.setOnScrollListener(new -$$Lambda$AlertsCreator$i3PmtYK6Q0f4I4qw-qAJknVMIjc(v2, v7, v6, v8));
        AlertsCreator.updateDayPicker(v7, v6, v8);
        if(v2) {
            AlertsCreator.checkPickerDate(v7, v6, v8);
        }

        if(v1 != -1) {
            v7.setValue(v1);
            v6.setValue(arg18);
            v8.setValue(arg19);
        }

        Builder v1_1 = new Builder(arg13);
        v1_1.setTitle(arg20);
        v1_1.setView(((View)v4));
        v1_1.setPositiveButton(LocaleController.getString("Set", 2131626038), new -$$Lambda$AlertsCreator$ekXUEjitbQ6cLCKK4eWI_cJwDyg(arg21, v7, v6, v8, arg22));
        v1_1.setNegativeButton(LocaleController.getString("Cancel", 2131624257), ((DialogInterface$OnClickListener)v3));
        return v1_1;
    }

    public static Dialog createFreeSpaceDialog(LaunchActivity arg17) {
        LaunchActivity v0 = arg17;
        int[] v2 = new int[1];
        int v3 = MessagesController.getGlobalMainSettings().getInt("keep_media", 1);
        int v4 = 2;
        int v5 = 3;
        if(v3 == v4) {
            v2[0] = v5;
        }
        else if(v3 == 0) {
            v2[0] = 1;
        }
        else if(v3 == 1) {
            v2[0] = v4;
        }
        else if(v3 == v5) {
            v2[0] = 0;
        }

        String[] v3_1 = new String[4];
        v3_1[0] = LocaleController.formatPluralString("Days", v5);
        v3_1[1] = LocaleController.formatPluralString("Weeks", 1);
        v3_1[v4] = LocaleController.formatPluralString("Months", 1);
        v3_1[v5] = LocaleController.getString("LowDiskSpaceNeverRemove", 2131625122);
        LinearLayout v4_1 = new LinearLayout(((Context)v0));
        v4_1.setOrientation(1);
        TextView v7 = new TextView(((Context)v0));
        v7.setText(LocaleController.getString("LowDiskSpaceTitle2", 2131625124));
        v7.setTextColor(Theme.getColor("dialogTextBlack"));
        v7.setTextSize(1, 16f);
        v7.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        int v8 = LocaleController.isRTL ? 5 : 3;
        v7.setGravity(v8 | 48);
        int v10 = -2;
        int v11 = -2;
        if(LocaleController.isRTL) {
            v5 = 5;
        }

        v4_1.addView(((View)v7), LayoutHelper.createLinear(v10, v11, v5 | 48, 24, 0, 24, 8));
        for(v5 = 0; v5 < v3_1.length; ++v5) {
            RadioColorCell v7_1 = new RadioColorCell(((Context)v0));
            v7_1.setPadding(AndroidUtilities.dp(4f), 0, AndroidUtilities.dp(4f), 0);
            v7_1.setTag(Integer.valueOf(v5));
            v7_1.setCheckColor(Theme.getColor("radioBackground"), Theme.getColor("dialogRadioBackgroundChecked"));
            String v8_1 = v3_1[v5];
            boolean v9 = v2[0] == v5 ? true : false;
            v7_1.setTextAndValue(v8_1, v9);
            v4_1.addView(((View)v7_1));
            v7_1.setOnClickListener(new -$$Lambda$AlertsCreator$r8mncNwGPO4r0GiBryZaLsAy1R4(v2, v4_1));
        }

        Builder v1 = new Builder(((Context)v0));
        v1.setTitle(LocaleController.getString("LowDiskSpaceTitle", 2131625123));
        v1.setMessage(LocaleController.getString("LowDiskSpaceMessage", 2131625121));
        v1.setView(((View)v4_1));
        v1.setPositiveButton(LocaleController.getString("OK", 2131625420), new -$$Lambda$AlertsCreator$-fmHBvM0Ffh5XPjpoOphbKLfZAI(v2));
        v1.setNeutralButton(LocaleController.getString("ClearMediaCache", 2131624431), new -$$Lambda$AlertsCreator$uBx1UxZoaaCrvuIoDqebAHrVcsk(v0));
        return v1.create();
    }

    public static Dialog createLocationUpdateDialog(Activity arg17, User arg18, IntCallback arg19) {
        Activity v0 = arg17;
        int[] v2 = new int[1];
        int v3 = 3;
        String[] v4 = new String[v3];
        v4[0] = LocaleController.getString("SendLiveLocationFor15m", 2131626008);
        v4[1] = LocaleController.getString("SendLiveLocationFor1h", 2131626009);
        v4[2] = LocaleController.getString("SendLiveLocationFor8h", 2131626010);
        LinearLayout v5 = new LinearLayout(((Context)v0));
        v5.setOrientation(1);
        TextView v7 = new TextView(((Context)v0));
        String v8 = arg18 != null ? LocaleController.formatString("LiveLocationAlertPrivate", 2131625101, new Object[]{UserObject.getFirstName(arg18)}) : LocaleController.getString("LiveLocationAlertGroup", 2131625100);
        v7.setText(((CharSequence)v8));
        v7.setTextColor(Theme.getColor("dialogTextBlack"));
        v7.setTextSize(1, 16f);
        int v8_1 = LocaleController.isRTL ? 5 : 3;
        v7.setGravity(v8_1 | 48);
        int v10 = -2;
        int v11 = -2;
        if(LocaleController.isRTL) {
            v3 = 5;
        }

        v5.addView(((View)v7), LayoutHelper.createLinear(v10, v11, v3 | 48, 24, 0, 24, 8));
        for(v3 = 0; v3 < v4.length; ++v3) {
            RadioColorCell v7_1 = new RadioColorCell(((Context)v0));
            v7_1.setPadding(AndroidUtilities.dp(4f), 0, AndroidUtilities.dp(4f), 0);
            v7_1.setTag(Integer.valueOf(v3));
            v7_1.setCheckColor(Theme.getColor("radioBackground"), Theme.getColor("dialogRadioBackgroundChecked"));
            v8 = v4[v3];
            boolean v9 = v2[0] == v3 ? true : false;
            v7_1.setTextAndValue(v8, v9);
            v5.addView(((View)v7_1));
            v7_1.setOnClickListener(new -$$Lambda$AlertsCreator$MYGFxZ3VbUhlHumhFH7QCasYC2k(v2, v5));
        }

        Builder v1 = new Builder(((Context)v0));
        v1.setTopImage(new ShareLocationDrawable(((Context)v0), false), Theme.getColor("dialogTopBackground"));
        v1.setView(((View)v5));
        v1.setPositiveButton(LocaleController.getString("ShareFile", 2131626060), new -$$Lambda$AlertsCreator$fE6q1h2zZKtHbEhStJdvDcFTv9w(v2, arg19));
        v1.setNeutralButton(LocaleController.getString("Cancel", 2131624257), null);
        return v1.create();
    }

    public static Dialog createMuteAlert(Context arg8, long arg9) {
        if(arg8 == null) {
            return null;
        }

        org.telegram.ui.ActionBar.BottomSheet$Builder v0 = new org.telegram.ui.ActionBar.BottomSheet$Builder(arg8);
        v0.setTitle(LocaleController.getString("Notifications", 2131625386));
        v0.setItems(new CharSequence[]{LocaleController.formatString("MuteFor", 2131625222, new Object[]{LocaleController.formatPluralString("Hours", 1)}), LocaleController.formatString("MuteFor", 2131625222, new Object[]{LocaleController.formatPluralString("Hours", 8)}), LocaleController.formatString("MuteFor", 2131625222, new Object[]{LocaleController.formatPluralString("Days", 2)}), LocaleController.getString("MuteDisable", 2131625221)}, new -$$Lambda$AlertsCreator$cK27HVkMkKHdzfcNVdnV58o4oYo(arg9));
        return v0.create();
    }

    public static Dialog createPopupSelectDialog(Activity arg8, BaseFragment arg9, boolean arg10, boolean arg11, Runnable arg12) {
        SharedPreferences v0 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
        int[] v2 = new int[1];
        if(arg11) {
            v2[0] = v0.getInt("popupAll", 0);
        }
        else if(arg10) {
            v2[0] = v0.getInt("popupGroup", 0);
        }

        String[] v11 = new String[]{LocaleController.getString("NoPopup", 2131625279), LocaleController.getString("OnlyWhenScreenOn", 2131625445), LocaleController.getString("OnlyWhenScreenOff", 2131625444), LocaleController.getString("AlwaysShowPopup", 2131624066)};
        LinearLayout v0_1 = new LinearLayout(((Context)arg8));
        v0_1.setOrientation(1);
        int v4;
        for(v4 = 0; v4 < v11.length; ++v4) {
            RadioColorCell v5 = new RadioColorCell(((Context)arg8));
            v5.setTag(Integer.valueOf(v4));
            v5.setPadding(AndroidUtilities.dp(4f), 0, AndroidUtilities.dp(4f), 0);
            v5.setCheckColor(Theme.getColor("radioBackground"), Theme.getColor("dialogRadioBackgroundChecked"));
            String v6 = v11[v4];
            boolean v7 = v2[0] == v4 ? true : false;
            v5.setTextAndValue(v6, v7);
            v0_1.addView(((View)v5));
            v5.setOnClickListener(new -$$Lambda$AlertsCreator$BHfBHSL45YWRE0Rlasv5AIsbMUw(v2, arg10, arg9, arg12));
        }

        Builder v9 = new Builder(((Context)arg8));
        v9.setTitle(LocaleController.getString("PopupNotification", 2131625791));
        v9.setView(((View)v0_1));
        v9.setPositiveButton(LocaleController.getString("Cancel", 2131624257), null);
        return v9.create();
    }

    public static Dialog createPrioritySelectDialog(Activity arg16, BaseFragment arg17, long arg18, boolean arg20, boolean arg21, Runnable arg22) {
        // Method was not decompiled
    }

    public static Dialog createReportAlert(Context arg10, long arg11, int arg13, BaseFragment arg14) {
        if(arg10 != null) {
            if(arg14 == null) {
            }
            else {
                org.telegram.ui.ActionBar.BottomSheet$Builder v0 = new org.telegram.ui.ActionBar.BottomSheet$Builder(arg10);
                v0.setTitle(LocaleController.getString("ReportChat", 2131625863));
                v0.setItems(new CharSequence[]{LocaleController.getString("ReportChatSpam", 2131625868), LocaleController.getString("ReportChatViolence", 2131625869), LocaleController.getString("ReportChatPornography", 2131625866), LocaleController.getString("ReportChatOther", 2131625865)}, new -$$Lambda$AlertsCreator$EHQ_UgoatWF72pKL5NH2K0EDGZc(arg11, arg13, arg14, arg10));
                return v0.create();
            }
        }

        return null;
    }

    public static Builder createSimpleAlert(Context arg3, String arg4) {
        Builder v0 = null;
        if(arg4 == null) {
            return v0;
        }

        Builder v1 = new Builder(arg3);
        v1.setTitle(LocaleController.getString("AppName", 2131624086));
        v1.setMessage(((CharSequence)arg4));
        v1.setPositiveButton(LocaleController.getString("OK", 2131625420), ((DialogInterface$OnClickListener)v0));
        return v1;
    }

    public static Dialog createSingleChoiceDialog(Activity arg8, BaseFragment arg9, String[] arg10, String arg11, int arg12, DialogInterface$OnClickListener arg13) {
        LinearLayout v0 = new LinearLayout(((Context)arg8));
        v0.setOrientation(1);
        Builder v2 = new Builder(((Context)arg8));
        int v4;
        for(v4 = 0; v4 < arg10.length; ++v4) {
            RadioColorCell v5 = new RadioColorCell(((Context)arg8));
            v5.setPadding(AndroidUtilities.dp(4f), 0, AndroidUtilities.dp(4f), 0);
            v5.setTag(Integer.valueOf(v4));
            v5.setCheckColor(Theme.getColor("radioBackground"), Theme.getColor("dialogRadioBackgroundChecked"));
            String v6 = arg10[v4];
            boolean v7 = arg12 == v4 ? true : false;
            v5.setTextAndValue(v6, v7);
            v0.addView(((View)v5));
            v5.setOnClickListener(new -$$Lambda$AlertsCreator$rro_SvqoV1WyQ8bHeaGHjwbe79o(arg9, arg13));
        }

        v2.setTitle(((CharSequence)arg11));
        v2.setView(((View)v0));
        v2.setPositiveButton(LocaleController.getString("Cancel", 2131624257), null);
        return v2.create();
    }

    public static Builder createTTLAlert(Context arg6, EncryptedChat arg7) {
        Builder v0 = new Builder(arg6);
        v0.setTitle(LocaleController.getString("MessageLifetime", 2131625178));
        NumberPicker v1 = new NumberPicker(arg6);
        int v6 = 0;
        v1.setMinValue(0);
        int v2 = 20;
        v1.setMaxValue(v2);
        int v4 = 16;
        if(arg7.ttl > 0 && arg7.ttl < v4) {
            v6 = arg7.ttl;
            goto label_18;
        }
        else if(arg7.ttl == 30) {
            v1.setValue(v4);
        }
        else if(arg7.ttl == 60) {
            v6 = 17;
            goto label_18;
        }
        else if(arg7.ttl == 3600) {
            v6 = 18;
            goto label_18;
        }
        else if(arg7.ttl == 86400) {
            v6 = 19;
            goto label_18;
        }
        else if(arg7.ttl == 604800) {
            v1.setValue(v2);
        }
        else if(arg7.ttl == 0) {
        label_18:
            v1.setValue(v6);
        }

        v1.setFormatter(-$$Lambda$AlertsCreator$2U3JIu9ra3wYHEe7H1aUTmcjxf4.INSTANCE);
        v0.setView(((View)v1));
        v0.setNegativeButton(LocaleController.getString("Done", 2131624614), new -$$Lambda$AlertsCreator$dTydlE5-bK3tgSspcU2cHxFstKA(arg7, v1));
        return v0;
    }

    public static Dialog createVibrationSelectDialog(Activity arg17, BaseFragment arg18, long arg19, String arg21, Runnable arg22) {
        // Method was not decompiled
    }

    public static Dialog createVibrationSelectDialog(Activity arg6, BaseFragment arg7, long arg8, boolean arg10, boolean arg11, Runnable arg12) {
        String v10;
        if(arg8 != 0) {
            v10 = "vibrate_";
        }
        else if(arg10) {
            v10 = "vibrate_group";
        }
        else {
            v10 = "vibrate_messages";
        }

        String v4 = v10;
        return AlertsCreator.createVibrationSelectDialog(arg6, arg7, arg8, v4, arg12);
    }

    private static String getFloodWaitString(String arg4) {
        int v4 = Utilities.parseInt(arg4).intValue();
        int v0 = 60;
        arg4 = v4 < v0 ? LocaleController.formatPluralString("Seconds", v4) : LocaleController.formatPluralString("Minutes", v4 / v0);
        return LocaleController.formatString("FloodWaitTime", 2131624800, new Object[]{arg4});
    }

    static void lambda$createAccountSelectDialog$31(AlertDialog[] arg2, Runnable arg3, AccountSelectDelegate arg4, View arg5) {
        if(arg2[0] != null) {
            arg2[0].setOnDismissListener(null);
        }

        arg3.run();
        arg4.didSelectAccount(((AccountSelectCell)arg5).getAccountNumber());
    }

    static void lambda$createColorSelectDialog$14(LinearLayout arg6, int[] arg7, View arg8) {
        int v0 = arg6.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = arg6.getChildAt(v2);
            boolean v5 = v3 == arg8 ? true : false;
            ((RadioColorCell)v3).setChecked(v5, true);
        }

        arg7[0] = TextColorCell.colorsToSave[arg8.getTag().intValue()];
    }

    static void lambda$createColorSelectDialog$15(boolean arg0, int[] arg1, boolean arg2, long arg3, Runnable arg5, DialogInterface arg6, int arg7) {
        int v1;
        String v0;
        SharedPreferences$Editor v6 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        if(arg0) {
            v0 = "MessagesLed";
            v1 = arg1[0];
        }
        else if(arg2) {
            v0 = "GroupLed";
            v1 = arg1[0];
        }
        else {
            v0 = "color_" + arg3;
            v1 = arg1[0];
        }

        v6.putInt(v0, v1);
        v6.commit();
        if(arg5 != null) {
            arg5.run();
        }
    }

    static void lambda$createColorSelectDialog$16(boolean arg0, boolean arg1, long arg2, Runnable arg4, DialogInterface arg5, int arg6) {
        String v0;
        SharedPreferences$Editor v5 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        if(arg0) {
            v0 = "MessagesLed";
        }
        else if(arg1) {
            v0 = "GroupLed";
        }
        else {
            v0 = "color_" + arg2;
        }

        v5.putInt(v0, 0);
        v5.commit();
        if(arg4 != null) {
            arg4.run();
        }
    }

    static void lambda$createColorSelectDialog$17(long arg1, Runnable arg3, DialogInterface arg4, int arg5) {
        SharedPreferences$Editor v4 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        v4.remove("color_" + arg1);
        v4.commit();
        if(arg3 != null) {
            arg3.run();
        }
    }

    static void lambda$createContactsPermissionDialog$21(IntCallback arg0, DialogInterface arg1, int arg2) {
        arg0.run(1);
    }

    static void lambda$createContactsPermissionDialog$22(IntCallback arg0, DialogInterface arg1, int arg2) {
        arg0.run(0);
    }

    static void lambda$createDatePickerDialog$3(boolean arg0, NumberPicker arg1, NumberPicker arg2, NumberPicker arg3, NumberPicker arg4, int arg5) {
        if((arg0) && arg5 == 0) {
            AlertsCreator.checkPickerDate(arg1, arg2, arg3);
        }
    }

    static String lambda$createDatePickerDialog$4(int arg3) {
        Calendar v0 = Calendar.getInstance();
        v0.set(5, 1);
        v0.set(2, arg3);
        return v0.getDisplayName(2, 1, Locale.getDefault());
    }

    static void lambda$createDatePickerDialog$5(NumberPicker arg0, NumberPicker arg1, NumberPicker arg2, NumberPicker arg3, int arg4, int arg5) {
        AlertsCreator.updateDayPicker(arg0, arg1, arg2);
    }

    static void lambda$createDatePickerDialog$6(boolean arg0, NumberPicker arg1, NumberPicker arg2, NumberPicker arg3, NumberPicker arg4, int arg5) {
        if((arg0) && arg5 == 0) {
            AlertsCreator.checkPickerDate(arg1, arg2, arg3);
        }
    }

    static void lambda$createDatePickerDialog$7(NumberPicker arg0, NumberPicker arg1, NumberPicker arg2, NumberPicker arg3, int arg4, int arg5) {
        AlertsCreator.updateDayPicker(arg0, arg1, arg2);
    }

    static void lambda$createDatePickerDialog$8(boolean arg0, NumberPicker arg1, NumberPicker arg2, NumberPicker arg3, NumberPicker arg4, int arg5) {
        if((arg0) && arg5 == 0) {
            AlertsCreator.checkPickerDate(arg1, arg2, arg3);
        }
    }

    static void lambda$createDatePickerDialog$9(boolean arg0, NumberPicker arg1, NumberPicker arg2, NumberPicker arg3, DatePickerDelegate arg4, DialogInterface arg5, int arg6) {
        if(arg0) {
            AlertsCreator.checkPickerDate(arg1, arg2, arg3);
        }

        arg4.didSelectDate(arg3.getValue(), arg2.getValue(), arg1.getValue());
    }

    static void lambda$createFreeSpaceDialog$23(int[] arg5, LinearLayout arg6, View arg7) {
        int v0 = arg7.getTag().intValue();
        int v1 = 3;
        if(v0 == 0) {
            arg5[0] = v1;
        }
        else if(v0 == 1) {
            arg5[0] = 0;
        }
        else {
            int v4 = 2;
            if(v0 == v4) {
                arg5[0] = 1;
            }
            else if(v0 == v1) {
                arg5[0] = v4;
            }
        }

        int v5 = arg6.getChildCount();
        for(v0 = 0; v0 < v5; ++v0) {
            View v1_1 = arg6.getChildAt(v0);
            if((v1_1 instanceof RadioColorCell)) {
                View v4_1 = v1_1;
                boolean v1_2 = v1_1 == arg7 ? true : false;
                ((RadioColorCell)v4_1).setChecked(v1_2, true);
            }
        }
    }

    static void lambda$createFreeSpaceDialog$24(int[] arg1, DialogInterface arg2, int arg3) {
        MessagesController.getGlobalMainSettings().edit().putInt("keep_media", arg1[0]).commit();
    }

    static void lambda$createFreeSpaceDialog$25(LaunchActivity arg0, DialogInterface arg1, int arg2) {
        arg0.presentFragment(new CacheControlActivity());
    }

    static void lambda$createLocationUpdateDialog$19(int[] arg5, LinearLayout arg6, View arg7) {
        arg5[0] = arg7.getTag().intValue();
        int v5 = arg6.getChildCount();
        int v0;
        for(v0 = 0; v0 < v5; ++v0) {
            View v2 = arg6.getChildAt(v0);
            if((v2 instanceof RadioColorCell)) {
                View v3 = v2;
                boolean v2_1 = v2 == arg7 ? true : false;
                ((RadioColorCell)v3).setChecked(v2_1, true);
            }
        }
    }

    static void lambda$createLocationUpdateDialog$20(int[] arg0, IntCallback arg1, DialogInterface arg2, int arg3) {
        int v0;
        if(arg0[0] == 0) {
            v0 = 900;
        }
        else if(arg0[0] == 1) {
            v0 = 3600;
        }
        else {
            v0 = 28800;
        }

        arg1.run(v0);
    }

    static void lambda$createMuteAlert$10(long arg5, DialogInterface arg7, int arg8) {
        int v7 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
        int v0 = 2;
        int v1 = 3;
        if(arg8 == 0) {
            v7 += 3600;
        }
        else if(arg8 == 1) {
            v7 += 28800;
        }
        else if(arg8 == v0) {
            v7 += 172800;
        }
        else if(arg8 == v1) {
            v7 = 2147483647;
        }

        SharedPreferences$Editor v2 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        long v3 = 1;
        if(arg8 == v1) {
            v2.putInt("notify2_" + arg5, v0);
        }
        else {
            v2.putInt("notify2_" + arg5, v1);
            v2.putInt("notifyuntil_" + arg5, v7);
            v3 |= (((long)v7)) << 32;
        }

        NotificationsController.getInstance(UserConfig.selectedAccount).removeNotificationsForDialog(arg5);
        MessagesStorage.getInstance(UserConfig.selectedAccount).setDialogFlags(arg5, v3);
        v2.commit();
        Object v8_1 = MessagesController.getInstance(UserConfig.selectedAccount).dialogs_dict.get(arg5);
        if(v8_1 != null) {
            ((TL_dialog)v8_1).notify_settings = new TL_peerNotifySettings();
            ((TL_dialog)v8_1).notify_settings.mute_until = v7;
        }

        NotificationsController.getInstance(UserConfig.selectedAccount).updateServerNotificationsSettings(arg5);
    }

    static void lambda$createPopupSelectDialog$27(int[] arg1, boolean arg2, BaseFragment arg3, Runnable arg4, View arg5) {
        arg1[0] = arg5.getTag().intValue();
        SharedPreferences$Editor v5 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        String v2 = arg2 ? "popupGroup" : "popupAll";
        v5.putInt(v2, arg1[0]);
        v5.commit();
        if(arg3 != null) {
            arg3.dismissCurrentDialig();
        }

        if(arg4 != null) {
            arg4.run();
        }
    }

    static void lambda$createPrioritySelectDialog$26(int[] arg6, long arg7, boolean arg9, BaseFragment arg10, Runnable arg11, View arg12) {
        // Method was not decompiled
    }

    static void lambda$createReportAlert$12(long arg0, int arg2, BaseFragment arg3, Context arg4, DialogInterface arg5, int arg6) {
        TL_account_reportPeer v5_1;
        TL_inputReportReasonPornography v0_3;
        TL_inputReportReasonViolence v0_2;
        TL_inputReportReasonSpam v0_1;
        if(arg6 == 3) {
            Bundle v4 = new Bundle();
            v4.putLong("dialog_id", arg0);
            v4.putLong("message_id", ((long)arg2));
            arg3.presentFragment(new ReportOtherActivity(v4));
            return;
        }

        InputPeer v0 = MessagesController.getInstance(UserConfig.selectedAccount).getInputPeer(((int)arg0));
        int v1 = 2;
        if(arg2 != 0) {
            TL_messages_report v5 = new TL_messages_report();
            v5.peer = v0;
            v5.id.add(Integer.valueOf(arg2));
            if(arg6 == 0) {
                v0_1 = new TL_inputReportReasonSpam();
            }
            else if(arg6 == 1) {
                v0_2 = new TL_inputReportReasonViolence();
            }
            else if(arg6 == v1) {
                v0_3 = new TL_inputReportReasonPornography();
            }
            else {
                goto label_55;
            }

            v5.reason = ((ReportReason)v0_1);
        }
        else {
            v5_1 = new TL_account_reportPeer();
            v5_1.peer = v0;
            if(arg6 == 0) {
                v0_1 = new TL_inputReportReasonSpam();
            }
            else if(arg6 == 1) {
                v0_2 = new TL_inputReportReasonViolence();
            }
            else if(arg6 == v1) {
                v0_3 = new TL_inputReportReasonPornography();
            }
            else {
                goto label_55;
            }

            v5_1.reason = ((ReportReason)v0_1);
        }

    label_55:
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(((TLObject)v5_1), -$$Lambda$AlertsCreator$OHq7qwmFnlUIXXljkiyBEPJ4Kj4.INSTANCE);
        Toast.makeText(arg4, LocaleController.getString("ReportChatSent", 2131625867), 0).show();
    }

    static void lambda$createSingleChoiceDialog$28(BaseFragment arg0, DialogInterface$OnClickListener arg1, View arg2) {
        int v2 = arg2.getTag().intValue();
        if(arg0 != null) {
            arg0.dismissCurrentDialig();
        }

        arg1.onClick(null, v2);
    }

    static String lambda$createTTLAlert$29(int arg2) {
        if(arg2 == 0) {
            return LocaleController.getString("ShortMessageLifetimeForever", 2131626085);
        }

        int v1 = 16;
        if(arg2 >= 1 && arg2 < v1) {
            return LocaleController.formatTTLString(arg2);
        }

        if(arg2 == v1) {
            return LocaleController.formatTTLString(30);
        }

        if(arg2 == 17) {
            return LocaleController.formatTTLString(60);
        }

        if(arg2 == 18) {
            return LocaleController.formatTTLString(3600);
        }

        if(arg2 == 19) {
            return LocaleController.formatTTLString(86400);
        }

        if(arg2 == 20) {
            return LocaleController.formatTTLString(604800);
        }

        return "";
    }

    static void lambda$createTTLAlert$30(EncryptedChat arg0, NumberPicker arg1, DialogInterface arg2, int arg3) {
        int v2 = arg0.ttl;
        int v1 = arg1.getValue();
        arg3 = 16;
        if(v1 >= 0 && v1 < arg3) {
            goto label_5;
        }
        else if(v1 == arg3) {
            v1 = 30;
            goto label_5;
        }
        else if(v1 == 17) {
            v1 = 60;
            goto label_5;
        }
        else if(v1 == 18) {
            v1 = 3600;
            goto label_5;
        }
        else if(v1 == 19) {
            v1 = 86400;
            goto label_5;
        }
        else if(v1 == 20) {
            v1 = 604800;
        label_5:
            arg0.ttl = v1;
        }

        if(v2 != arg0.ttl) {
            SecretChatHelper.getInstance(UserConfig.selectedAccount).sendTTLMessage(arg0, null);
            MessagesStorage.getInstance(UserConfig.selectedAccount).updateEncryptedChatTTL(arg0);
        }
    }

    static void lambda$createVibrationSelectDialog$18(int[] arg5, long arg6, String arg8, BaseFragment arg9, Runnable arg10, View arg11) {
        // Method was not decompiled
    }

    static void lambda$null$11(TLObject arg0, TL_error arg1) {
    }

    static void lambda$showAddUserAlert$13(BaseFragment arg1, DialogInterface arg2, int arg3) {
        MessagesController.getInstance(arg1.getCurrentAccount()).openByUserName("spambot", arg1, 1);
    }

    static void lambda$showCustomNotificationsDialog$1(int arg7, long arg8, BaseFragment arg10, IntCallback arg11, View arg12) {
        int v12 = arg12.getTag().intValue();
        if(v12 == 0 || v12 == 1) {
            SharedPreferences$Editor v0_1 = MessagesController.getNotificationsSettings(arg7).edit();
            if(v12 == 0) {
                v0_1.remove("notify2_" + arg8);
            }
            else {
                v0_1.putInt("notify2_" + arg8, 0);
            }

            MessagesStorage.getInstance(arg7).setDialogFlags(arg8, 0);
            v0_1.commit();
            Object v0_2 = MessagesController.getInstance(arg7).dialogs_dict.get(arg8);
            if(v0_2 != null) {
                ((TL_dialog)v0_2).notify_settings = new TL_peerNotifySettings();
            }

        label_105:
            NotificationsController.getInstance(arg7).updateServerNotificationsSettings(arg8);
        }
        else if(v12 == 4) {
            Bundle v7 = new Bundle();
            v7.putLong("dialog_id", arg8);
            arg10.presentFragment(new ProfileNotificationsActivity(v7));
        }
        else {
            int v0 = ConnectionsManager.getInstance(arg7).getCurrentTime();
            int v1 = 5;
            int v2 = 3;
            int v3 = 2;
            if(v12 == v3) {
                v0 += 3600;
            }
            else if(v12 == v2) {
                v0 += 172800;
            }
            else if(v12 == v1) {
                v0 = 2147483647;
            }

            SharedPreferences$Editor v4 = MessagesController.getNotificationsSettings(arg7).edit();
            long v5 = 1;
            if(v12 == v1) {
                v4.putInt("notify2_" + arg8, v3);
            }
            else {
                v4.putInt("notify2_" + arg8, v2);
                v4.putInt("notifyuntil_" + arg8, v0);
                v5 |= (((long)v0)) << 32;
            }

            NotificationsController.getInstance(arg7).removeNotificationsForDialog(arg8);
            MessagesStorage.getInstance(arg7).setDialogFlags(arg8, v5);
            v4.commit();
            Object v1_2 = MessagesController.getInstance(arg7).dialogs_dict.get(arg8);
            if(v1_2 == null) {
                goto label_105;
            }

            ((TL_dialog)v1_2).notify_settings = new TL_peerNotifySettings();
            ((TL_dialog)v1_2).notify_settings.mute_until = v0;
            goto label_105;
        }

        if(arg11 != null) {
            arg11.run(v12);
        }

        arg10.dismissCurrentDialig();
    }

    static void lambda$showSecretLocationAlert$2(Runnable arg0, DialogInterface arg1, int arg2) {
        SharedConfig.setSecretMapPreviewType(arg2);
        if(arg0 != null) {
            arg0.run();
        }
    }

    static void lambda$showUpdateAppAlert$0(Context arg0, DialogInterface arg1, int arg2) {
        Browser.openUrl(arg0, BuildVars.PLAYSTORE_APP_URL);
    }

    public static Dialog processError(int arg8, TL_error arg9, BaseFragment arg10, TLObject arg11, Object[] arg12) {
        Object[] v10_1;
        NotificationCenter v8_3;
        String v8;
        int v12;
        int v7;
        int v0;
        int v9;
        Dialog v1 = null;
        if(arg9.code != 406) {
            if(arg9.text == null) {
            }
            else {
                int v2 = 2131624993;
                int v3 = 2131624696;
                int v5 = 2131624799;
                if(((arg11 instanceof TL_account_saveSecureValue)) || ((arg11 instanceof TL_account_getAuthorizationForm))) {
                    if(arg9.text.contains("PHONE_NUMBER_INVALID")) {
                    label_422:
                        v8 = LocaleController.getString("InvalidPhoneNumber", v2);
                        goto label_424;
                    }

                    if(arg9.text.startsWith("FLOOD_WAIT")) {
                    label_430:
                        v8 = LocaleController.getString("FloodWait", v5);
                        goto label_424;
                    }

                    if("APP_VERSION_OUTDATED".equals(arg9.text)) {
                        AlertsCreator.showUpdateAppAlert(arg10.getParentActivity(), LocaleController.getString("UpdateAppAlert", 2131626283), true);
                        return v1;
                    }

                    v8_2 = new StringBuilder();
                label_445:
                    v8_2.append(LocaleController.getString("ErrorOccurred", v3));
                    v8_2.append("\n");
                    v8_2.append(arg9.text);
                    v8 = v8_2.toString();
                }
                else {
                    if(((arg11 instanceof TL_channels_joinChannel)) || ((arg11 instanceof TL_channels_editAdmin)) || ((arg11 instanceof TL_channels_inviteToChannel)) || ((arg11 instanceof TL_messages_addChatUser)) || ((arg11 instanceof TL_messages_startBot)) || ((arg11 instanceof TL_channels_editBanned))) {
                        if(arg10 != null) {
                            AlertsCreator.showAddUserAlert(arg9.text, arg10, arg12[0].booleanValue());
                            return v1;
                        }

                        if(!arg9.text.equals("PEER_FLOOD")) {
                            return v1;
                        }

                        v8_3 = NotificationCenter.getInstance(arg8);
                        v9 = NotificationCenter.needShowAlert;
                        v10_1 = new Object[]{Integer.valueOf(1)};
                    }
                    else {
                        if((arg11 instanceof TL_messages_createChat)) {
                            if(!arg9.text.startsWith("FLOOD_WAIT")) {
                                goto label_39;
                            }

                            goto label_36;
                        }
                        else if(!(arg11 instanceof TL_channels_createChannel)) {
                            goto label_49;
                        }
                        else if(arg9.text.startsWith("FLOOD_WAIT")) {
                        label_36:
                            AlertsCreator.showFloodWaitAlert(arg9.text, arg10);
                            return v1;
                        }

                    label_39:
                        AlertsCreator.showAddUserAlert(arg9.text, arg10, false);
                        return v1;
                    label_49:
                        if(!(arg11 instanceof TL_messages_editMessage)) {
                            if(!(arg11 instanceof TL_messages_sendMessage) && !(arg11 instanceof TL_messages_sendMedia) && !(arg11 instanceof TL_messages_sendBroadcast) && !(arg11 instanceof TL_messages_sendInlineBotResult)) {
                                if((arg11 instanceof TL_messages_forwardMessages)) {
                                }
                                else {
                                    if((arg11 instanceof TL_messages_importChatInvite)) {
                                        if(arg9.text.startsWith("FLOOD_WAIT")) {
                                            goto label_430;
                                        }
                                        else if(arg9.text.equals("USERS_TOO_MUCH")) {
                                            v8 = "JoinToGroupErrorFull";
                                            v9 = 2131625026;
                                        }
                                        else {
                                            v8 = "JoinToGroupErrorNotExist";
                                            v9 = 2131625027;
                                        }
                                    }
                                    else if((arg11 instanceof TL_messages_getAttachedStickers)) {
                                        if(arg10 == null) {
                                        }
                                        else if(arg10.getParentActivity() != null) {
                                            Activity v8_1 = arg10.getParentActivity();
                                            StringBuilder v10 = new StringBuilder();
                                            v10.append(LocaleController.getString("ErrorOccurred", v3));
                                            v10.append("\n");
                                            v10.append(arg9.text);
                                            Toast.makeText(((Context)v8_1), v10.toString(), 0).show();
                                        }
                                        else {
                                        }

                                        return v1;
                                    }
                                    else {
                                        v0 = 2131624442;
                                        v7 = 2131624989;
                                        if((arg11 instanceof TL_account_confirmPhone)) {
                                            goto label_358;
                                        }
                                        else if((arg11 instanceof TL_account_verifyPhone)) {
                                            goto label_358;
                                        }
                                        else if((arg11 instanceof TL_account_verifyEmail)) {
                                            goto label_358;
                                        }
                                        else if((arg11 instanceof TL_auth_resendCode)) {
                                            if(arg9.text.contains("PHONE_NUMBER_INVALID")) {
                                                v8 = LocaleController.getString("InvalidPhoneNumber", v2);
                                            }
                                            else if(arg9.text.contains("PHONE_CODE_EMPTY")) {
                                                goto label_168;
                                            }
                                            else if(arg9.text.contains("PHONE_CODE_INVALID")) {
                                                goto label_168;
                                            }
                                            else if(arg9.text.contains("PHONE_CODE_EXPIRED")) {
                                                goto label_144;
                                            }
                                            else if(arg9.text.startsWith("FLOOD_WAIT")) {
                                                goto label_151;
                                            }
                                            else if(arg9.code != -1000) {
                                                v8 = LocaleController.getString("ErrorOccurred", v3) + "\n" + arg9.text;
                                            }
                                            else {
                                                return v1;
                                            }
                                        }
                                        else if(!(arg11 instanceof TL_account_sendConfirmPhoneCode)) {
                                            if(!(arg11 instanceof TL_account_changePhone)) {
                                                if(!(arg11 instanceof TL_account_sendChangePhoneCode)) {
                                                    goto label_263;
                                                }
                                                else if(arg9.text.contains("PHONE_NUMBER_INVALID")) {
                                                    goto label_422;
                                                }
                                                else if(arg9.text.contains("PHONE_CODE_EMPTY")) {
                                                    goto label_220;
                                                }
                                                else if(arg9.text.contains("PHONE_CODE_INVALID")) {
                                                    goto label_220;
                                                }
                                                else if(arg9.text.contains("PHONE_CODE_EXPIRED")) {
                                                label_210:
                                                    v8 = LocaleController.getString("CodeExpired", v0);
                                                    goto label_424;
                                                }
                                                else if(arg9.text.startsWith("FLOOD_WAIT")) {
                                                    goto label_430;
                                                }
                                                else if(arg9.text.startsWith("PHONE_NUMBER_OCCUPIED")) {
                                                    v8 = LocaleController.formatString("ChangePhoneNumberOccupied", 2131624275, new Object[]{arg12[0]});
                                                    goto label_424;
                                                }

                                                goto label_260;
                                            }
                                            else if(arg9.text.contains("PHONE_NUMBER_INVALID")) {
                                                goto label_422;
                                            }
                                            else if(!arg9.text.contains("PHONE_CODE_EMPTY")) {
                                                if(arg9.text.contains("PHONE_CODE_INVALID")) {
                                                }
                                                else if(arg9.text.contains("PHONE_CODE_EXPIRED")) {
                                                    goto label_210;
                                                }
                                                else if(arg9.text.startsWith("FLOOD_WAIT")) {
                                                    goto label_430;
                                                }
                                                else {
                                                    v8 = arg9.text;
                                                    goto label_424;
                                                }
                                            }

                                        label_220:
                                            v8 = LocaleController.getString("InvalidCode", v7);
                                            goto label_424;
                                        label_263:
                                            v12 = -1;
                                            if((arg11 instanceof TL_updateUserName)) {
                                                v8 = arg9.text;
                                                v9 = v8.hashCode();
                                                if(v9 != 288843630) {
                                                    if(v9 != 533175271) {
                                                    }
                                                    else if(v8.equals("USERNAME_OCCUPIED")) {
                                                        v12 = 1;
                                                    }
                                                }
                                                else if(v8.equals("USERNAME_INVALID")) {
                                                    v12 = 0;
                                                }

                                                switch(v12) {
                                                    case 0: {
                                                        goto label_287;
                                                    }
                                                    case 1: {
                                                        goto label_284;
                                                    }
                                                }
                                            }
                                            else {
                                                goto label_290;
                                            }

                                        label_260:
                                            v8 = LocaleController.getString("ErrorOccurred", v3);
                                            goto label_424;
                                        label_284:
                                            v8 = "UsernameInUse";
                                            v9 = 2131626341;
                                            goto label_88;
                                        label_287:
                                            v8 = "UsernameInvalid";
                                            v9 = 2131626342;
                                            goto label_88;
                                        }
                                        else if(arg9.code == 400) {
                                            v8 = LocaleController.getString("CancelLinkExpired", 2131624260);
                                        }
                                        else if(arg9.text == null) {
                                            return v1;
                                        }
                                        else if(arg9.text.startsWith("FLOOD_WAIT")) {
                                            goto label_151;
                                        }
                                        else {
                                            v8 = LocaleController.getString("ErrorOccurred", v3);
                                        }

                                        goto label_129;
                                    }

                                label_88:
                                    v8 = LocaleController.getString(v8, v9);
                                    goto label_424;
                                label_290:
                                    if((arg11 instanceof TL_contacts_importContacts)) {
                                        if(arg9 == null) {
                                            goto label_430;
                                        }

                                        if(arg9.text.startsWith("FLOOD_WAIT")) {
                                            goto label_430;
                                        }

                                        v8_2 = new StringBuilder();
                                        goto label_445;
                                    }

                                    if(((arg11 instanceof TL_account_getPassword)) || ((arg11 instanceof TL_account_getTmpPassword))) {
                                        if(!arg9.text.startsWith("FLOOD_WAIT")) {
                                            goto label_356;
                                        }

                                        v8 = AlertsCreator.getFloodWaitString(arg9.text);
                                    }
                                    else {
                                        if((arg11 instanceof TL_payments_sendPaymentForm)) {
                                            v8 = arg9.text;
                                            int v11 = v8.hashCode();
                                            if(v11 != -1144062453) {
                                                if(v11 != -784238410) {
                                                }
                                                else if(v8.equals("PAYMENT_FAILED")) {
                                                    v12 = 1;
                                                }
                                            }
                                            else if(v8.equals("BOT_PRECHECKOUT_FAILED")) {
                                                v12 = 0;
                                            }

                                            switch(v12) {
                                                case 0: {
                                                    goto label_329;
                                                }
                                                case 1: {
                                                    goto label_326;
                                                }
                                            }

                                            goto label_356;
                                        label_326:
                                            v8 = "PaymentFailed";
                                            v9 = 2131625695;
                                            goto label_347;
                                        label_329:
                                            v8 = "PaymentPrecheckoutFailed";
                                            v9 = 2131625708;
                                        }
                                        else {
                                            if(!(arg11 instanceof TL_payments_validateRequestedInfo)) {
                                                return v1;
                                            }

                                            v8 = arg9.text;
                                            if(v8.hashCode() != 1758025548) {
                                            }
                                            else if(v8.equals("SHIPPING_NOT_AVAILABLE")) {
                                                v12 = 0;
                                            }

                                            if(v12 != 0) {
                                                goto label_356;
                                            }

                                            v8 = "PaymentNoShippingMethod";
                                            v9 = 2131625697;
                                        }

                                    label_347:
                                        v8 = LocaleController.getString(v8, v9);
                                    }

                                    goto label_62;
                                label_356:
                                    v8 = arg9.text;
                                    goto label_62;
                                }
                            }

                            goto label_391;
                        }
                        else if(!arg9.text.equals("MESSAGE_NOT_MODIFIED")) {
                            arg8 = 2131624642;
                            if(arg10 != null) {
                                v8 = LocaleController.getString("EditMessageError", arg8);
                                goto label_424;
                            }
                            else {
                                v8 = LocaleController.getString("EditMessageError", arg8);
                            }
                        }
                        else {
                            return v1;
                        }

                    label_62:
                        AlertsCreator.showSimpleToast(arg10, v8);
                        return v1;
                    label_358:
                        if(arg9.text.contains("PHONE_CODE_EMPTY")) {
                            goto label_168;
                        }

                        if(arg9.text.contains("PHONE_CODE_INVALID")) {
                            goto label_168;
                        }

                        if(arg9.text.contains("CODE_INVALID")) {
                            goto label_168;
                        }

                        if(arg9.text.contains("CODE_EMPTY")) {
                            goto label_168;
                        }

                        if(arg9.text.contains("PHONE_CODE_EXPIRED")) {
                            goto label_144;
                        }

                        if(arg9.text.contains("EMAIL_VERIFY_EXPIRED")) {
                            goto label_144;
                        }

                        if(arg9.text.startsWith("FLOOD_WAIT")) {
                        label_151:
                            v8 = LocaleController.getString("FloodWait", v5);
                        }
                        else {
                            v8 = arg9.text;
                            goto label_129;
                        label_144:
                            v8 = LocaleController.getString("CodeExpired", v0);
                            goto label_129;
                        label_168:
                            v8 = LocaleController.getString("InvalidCode", v7);
                        }

                    label_129:
                        return AlertsCreator.showSimpleAlert(arg10, v8);
                    label_391:
                        if(!arg9.text.equals("PEER_FLOOD")) {
                            return v1;
                        }

                        v8_3 = NotificationCenter.getInstance(arg8);
                        v9 = NotificationCenter.needShowAlert;
                        v10_1 = new Object[]{Integer.valueOf(0)};
                    }

                    v8_3.postNotificationName(v9, v10_1);
                    return v1;
                }

            label_424:
                AlertsCreator.showSimpleAlert(arg10, v8);
            }
        }

        return v1;
    }

    public static void showAddUserAlert(String arg4, BaseFragment arg5, boolean arg6) {
        int v6_1;
        if(arg4 != null && arg5 != null) {
            if(arg5.getParentActivity() == null) {
            }
            else {
                Builder v0 = new Builder(arg5.getParentActivity());
                v0.setTitle(LocaleController.getString("AppName", 2131624086));
                int v1 = -1;
                switch(arg4.hashCode()) {
                    case -538116776: {
                        if(arg4.equals("USER_BLOCKED")) {
                            v1 = 1;
                        }
                        else {
                        }

                        break;
                    }
                    case -512775857: {
                        if(arg4.equals("USER_RESTRICTED")) {
                            v1 = 10;
                        }
                        else {
                        }

                        break;
                    }
                    case -454039871: {
                        if(arg4.equals("PEER_FLOOD")) {
                            v1 = 0;
                        }
                        else {
                        }

                        break;
                    }
                    case -420079733: {
                        if(arg4.equals("BOTS_TOO_MUCH")) {
                            v1 = 7;
                        }
                        else {
                        }

                        break;
                    }
                    case 98635865: {
                        if(arg4.equals("USER_KICKED")) {
                            v1 = 13;
                        }
                        else {
                        }

                        break;
                    }
                    case 517420851: {
                        if(arg4.equals("USER_BOT")) {
                            v1 = 2;
                        }
                        else {
                        }

                        break;
                    }
                    case 845559454: {
                        if(arg4.equals("YOU_BLOCKED_USER")) {
                            v1 = 11;
                        }
                        else {
                        }

                        break;
                    }
                    case 916342611: {
                        if(arg4.equals("USER_ADMIN_INVALID")) {
                            v1 = 15;
                        }
                        else {
                        }

                        break;
                    }
                    case 1047173446: {
                        if(arg4.equals("CHAT_ADMIN_BAN_REQUIRED")) {
                            v1 = 12;
                        }
                        else {
                        }

                        break;
                    }
                    case 1167301807: {
                        if(arg4.equals("USERS_TOO_MUCH")) {
                            v1 = 4;
                        }
                        else {
                        }

                        break;
                    }
                    case 1227003815: {
                        if(arg4.equals("USER_ID_INVALID")) {
                            v1 = 3;
                        }
                        else {
                        }

                        break;
                    }
                    case 1253103379: {
                        if(arg4.equals("ADMINS_TOO_MUCH")) {
                            v1 = 6;
                        }
                        else {
                        }

                        break;
                    }
                    case 1623167701: {
                        if(arg4.equals("USER_NOT_MUTUAL_CONTACT")) {
                            v1 = 5;
                        }
                        else {
                        }

                        break;
                    }
                    case 1754587486: {
                        if(arg4.equals("CHAT_ADMIN_INVITE_REQUIRED")) {
                            v1 = 14;
                        }
                        else {
                        }

                        break;
                    }
                    case 1916725894: {
                        if(arg4.equals("USER_PRIVACY_RESTRICTED")) {
                            v1 = 8;
                        }
                        else {
                        }

                        break;
                    }
                    case -1763467626: {
                        if(arg4.equals("USERS_TOO_FEW")) {
                            v1 = 9;
                        }
                        else {
                        }

                        break;
                    }
                    default: {
                        break;
                    }
                }

                switch(v1) {
                    case 0: {
                        v0.setMessage(LocaleController.getString("NobodyLikesSpam2", 2131625299));
                        v0.setNegativeButton(LocaleController.getString("MoreInfo", 2131625217), new -$$Lambda$AlertsCreator$7oxBdBo-z60AOCHO0SuiR8gEjxs(arg5));
                        goto label_180;
                    }
                    case 1: 
                    case 2: 
                    case 3: {
                        if(arg6) {
                            arg4 = "ChannelUserCantAdd";
                            v6_1 = 2131624370;
                        }
                        else {
                            arg4 = "GroupUserCantAdd";
                            v6_1 = 2131624918;
                        }

                        break;
                    }
                    case 4: {
                        if(arg6) {
                            arg4 = "ChannelUserAddLimit";
                            v6_1 = 2131624369;
                        }
                        else {
                            arg4 = "GroupUserAddLimit";
                            v6_1 = 2131624917;
                        }

                        break;
                    }
                    case 5: {
                        if(arg6) {
                            arg4 = "ChannelUserLeftError";
                            v6_1 = 2131624373;
                        }
                        else {
                            arg4 = "GroupUserLeftError";
                            v6_1 = 2131624921;
                        }

                        break;
                    }
                    case 6: {
                        if(arg6) {
                            arg4 = "ChannelUserCantAdmin";
                            v6_1 = 2131624371;
                        }
                        else {
                            arg4 = "GroupUserCantAdmin";
                            v6_1 = 2131624919;
                        }

                        break;
                    }
                    case 7: {
                        if(arg6) {
                            arg4 = "ChannelUserCantBot";
                            v6_1 = 2131624372;
                        }
                        else {
                            arg4 = "GroupUserCantBot";
                            v6_1 = 2131624920;
                        }

                        break;
                    }
                    case 8: {
                        if(arg6) {
                            arg4 = "InviteToChannelError";
                            v6_1 = 2131625008;
                        }
                        else {
                            arg4 = "InviteToGroupError";
                            v6_1 = 2131625010;
                        }

                        break;
                    }
                    case 9: {
                        arg4 = "CreateGroupError";
                        v6_1 = 2131624502;
                        break;
                    }
                    case 10: {
                        arg4 = "UserRestricted";
                        v6_1 = 2131626323;
                        break;
                    }
                    case 11: {
                        arg4 = "YouBlockedUser";
                        v6_1 = 2131626440;
                        break;
                    }
                    case 12: 
                    case 13: {
                        arg4 = "AddAdminErrorBlacklisted";
                        v6_1 = 2131624012;
                        break;
                    }
                    case 14: {
                        arg4 = "AddAdminErrorNotAMember";
                        v6_1 = 2131624013;
                        break;
                    }
                    case 15: {
                        arg4 = "AddBannedErrorAdmin";
                        v6_1 = 2131624014;
                        break;
                    }
                    default: {
                        arg4 = LocaleController.getString("ErrorOccurred", 2131624696) + "\n" + arg4;
                        goto label_107;
                    }
                }

                arg4 = LocaleController.getString(arg4, v6_1);
            label_107:
                v0.setMessage(((CharSequence)arg4));
            label_180:
                v0.setPositiveButton(LocaleController.getString("OK", 2131625420), null);
                arg5.showDialog(v0.create(), true, null);
            }
        }
    }

    public static void showCustomNotificationsDialog(BaseFragment arg16, long arg17, int arg19, IntCallback arg20) {
        int v3;
        String v2;
        String v1;
        SharedPreferences v0;
        BaseFragment v6 = arg16;
        if(v6 != null) {
            if(arg16.getParentActivity() == null) {
            }
            else {
                int v9 = 1;
                if((((int)arg17)) < 0) {
                    v0 = MessagesController.getNotificationsSettings(arg19);
                    v1 = "EnableGroup";
                }
                else {
                    v0 = MessagesController.getNotificationsSettings(arg19);
                    v1 = "EnableAll";
                }

                boolean v0_1 = v0.getBoolean(v1, true);
                int v1_1 = 6;
                String[] v10 = new String[v1_1];
                if(v0_1) {
                    v2 = "NotificationsDefaultOn";
                    v3 = 2131625391;
                }
                else {
                    v2 = "NotificationsDefaultOff";
                    v3 = 2131625390;
                }

                v2 = LocaleController.getString(v2, v3);
                v10[0] = v2;
                v10[1] = LocaleController.getString("NotificationsTurnOn", 2131625417);
                v3 = 2;
                v10[v3] = LocaleController.formatString("MuteFor", 2131625222, new Object[]{LocaleController.formatPluralString("Hours", 1)});
                int v4 = 3;
                v10[v4] = LocaleController.formatString("MuteFor", 2131625222, new Object[]{LocaleController.formatPluralString("Days", v3)});
                int v5 = 4;
                v10[v5] = LocaleController.getString("NotificationsCustomize", 2131625389);
                int v12 = 5;
                v10[v12] = LocaleController.getString("NotificationsTurnOff", 2131625416);
                int[] v13 = new int[v1_1];
                v1_1 = 2131231431;
                int v2_1 = 2131231432;
                int v0_2 = v0_1 ? 2131231432 : 2131231431;
                v13[0] = v0_2;
                v13[1] = v2_1;
                v13[v3] = 2131231428;
                v13[v4] = 2131231429;
                v13[v5] = 2131231430;
                v13[v12] = v1_1;
                LinearLayout v12_1 = new LinearLayout(arg16.getParentActivity());
                v12_1.setOrientation(1);
                int v14 = 0;
                while(v14 < v10.length) {
                    TextView v15 = new TextView(arg16.getParentActivity());
                    v15.setTextColor(Theme.getColor("dialogTextBlack"));
                    v15.setTextSize(v9, 16f);
                    v15.setLines(v9);
                    v15.setMaxLines(v9);
                    Drawable v0_3 = arg16.getParentActivity().getResources().getDrawable(v13[v14]);
                    v0_3.setColorFilter(new PorterDuffColorFilter(Theme.getColor("dialogIcon"), PorterDuff$Mode.MULTIPLY));
                    v15.setCompoundDrawablesWithIntrinsicBounds(v0_3, null, null, null);
                    v15.setTag(Integer.valueOf(v14));
                    v15.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                    v15.setPadding(AndroidUtilities.dp(24f), 0, AndroidUtilities.dp(24f), 0);
                    v15.setSingleLine(((boolean)v9));
                    v15.setGravity(19);
                    v15.setCompoundDrawablePadding(AndroidUtilities.dp(26f));
                    v15.setText(v10[v14]);
                    v12_1.addView(((View)v15), LayoutHelper.createLinear(-1, 48, 51));
                    v15.setOnClickListener(new -$$Lambda$AlertsCreator$GOZVRJSCM6QSEWl5_zJZF2-PnNk(arg19, arg17, arg16, arg20));
                    ++v14;
                    v9 = 1;
                }

                Builder v0_4 = new Builder(arg16.getParentActivity());
                v0_4.setTitle(LocaleController.getString("Notifications", 2131625386));
                v0_4.setView(((View)v12_1));
                v6.showDialog(v0_4.create());
            }
        }
    }

    public static void showFloodWaitAlert(String arg6, BaseFragment arg7) {
        if(arg6 != null && (arg6.startsWith("FLOOD_WAIT")) && arg7 != null) {
            if(arg7.getParentActivity() == null) {
            }
            else {
                int v6 = Utilities.parseInt(arg6).intValue();
                int v0 = 60;
                arg6 = v6 < v0 ? LocaleController.formatPluralString("Seconds", v6) : LocaleController.formatPluralString("Minutes", v6 / v0);
                Builder v0_1 = new Builder(arg7.getParentActivity());
                v0_1.setTitle(LocaleController.getString("AppName", 2131624086));
                v0_1.setMessage(LocaleController.formatString("FloodWaitTime", 2131624800, new Object[]{arg6}));
                v0_1.setPositiveButton(LocaleController.getString("OK", 2131625420), null);
                arg7.showDialog(v0_1.create(), true, null);
            }
        }
    }

    public static AlertDialog showSecretLocationAlert(Context arg3, int arg4, Runnable arg5, boolean arg6) {
        ArrayList v0 = new ArrayList();
        arg4 = MessagesController.getInstance(arg4).availableMapProviders;
        if((arg4 & 1) != 0) {
            v0.add(LocaleController.getString("MapPreviewProviderTelegram", 2131625139));
        }

        if((arg4 & 2) != 0) {
            v0.add(LocaleController.getString("MapPreviewProviderGoogle", 2131625137));
        }

        if((arg4 & 4) != 0) {
            v0.add(LocaleController.getString("MapPreviewProviderYandex", 2131625140));
        }

        v0.add(LocaleController.getString("MapPreviewProviderNobody", 2131625138));
        Builder v3 = new Builder(arg3).setTitle(LocaleController.getString("ChooseMapPreviewProvider", 2131624415)).setItems(v0.toArray(new String[v0.size()]), new -$$Lambda$AlertsCreator$lpO1-8CQqnFnUzHMuq860437l60(arg5));
        if(!arg6) {
            v3.setNegativeButton(LocaleController.getString("Cancel", 2131624257), null);
        }

        AlertDialog v3_1 = v3.show();
        if(arg6) {
            v3_1.setCanceledOnTouchOutside(false);
        }

        return v3_1;
    }

    public static void showSendMediaAlert(int arg3, BaseFragment arg4) {
        int v2;
        String v3;
        if(arg3 == 0) {
            return;
        }

        Builder v0 = new Builder(arg4.getParentActivity());
        v0.setTitle(LocaleController.getString("AppName", 2131624086));
        if(arg3 == 1) {
            v3 = "ErrorSendRestrictedStickers";
            v2 = 2131624698;
            goto label_13;
        }
        else if(arg3 == 2) {
            v3 = "ErrorSendRestrictedMedia";
            v2 = 2131624697;
        label_13:
            v0.setMessage(LocaleController.getString(v3, v2));
        }

        v0.setPositiveButton(LocaleController.getString("OK", 2131625420), null);
        arg4.showDialog(v0.create(), true, null);
    }

    public static Dialog showSimpleAlert(BaseFragment arg1, String arg2) {
        if(arg2 != null && arg1 != null) {
            if(arg1.getParentActivity() == null) {
            }
            else {
                AlertDialog v2 = AlertsCreator.createSimpleAlert(arg1.getParentActivity(), arg2).create();
                arg1.showDialog(((Dialog)v2));
                return ((Dialog)v2);
            }
        }

        return null;
    }

    public static Toast showSimpleToast(BaseFragment arg1, String arg2) {
        Context v1_1;
        if(arg2 == null) {
            return null;
        }

        if(arg1 == null || arg1.getParentActivity() == null) {
            v1_1 = ApplicationLoader.applicationContext;
        }
        else {
            Activity v1 = arg1.getParentActivity();
        }

        Toast v1_2 = Toast.makeText(v1_1, ((CharSequence)arg2), 1);
        v1_2.show();
        return v1_2;
    }

    public static AlertDialog showUpdateAppAlert(Context arg4, String arg5, boolean arg6) {
        DialogInterface$OnClickListener v0 = null;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                Builder v1 = new Builder(arg4);
                v1.setTitle(LocaleController.getString("AppName", 2131624086));
                v1.setMessage(((CharSequence)arg5));
                v1.setPositiveButton(LocaleController.getString("OK", 2131625420), v0);
                if(arg6) {
                    v1.setNegativeButton(LocaleController.getString("UpdateApp", 2131626282), new -$$Lambda$AlertsCreator$msGS4QN_R2Ivdo98cFI--iWFJUI(arg4));
                }

                return v1.show();
            }
        }

        return ((AlertDialog)v0);
    }

    private static void updateDayPicker(NumberPicker arg2, NumberPicker arg3, NumberPicker arg4) {
        Calendar v0 = Calendar.getInstance();
        v0.set(2, arg3.getValue());
        v0.set(1, arg4.getValue());
        arg2.setMinValue(1);
        arg2.setMaxValue(v0.getActualMaximum(5));
    }
}

