package org.telegram.customization.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import org.telegram.customization.g.d;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.LaunchActivity;

public class g {
    static void a(long arg0, long arg2, Activity arg4) {
        g.b(arg0, arg2, arg4);
    }

    public static void a(long arg10, long arg12, Activity arg14, String arg15) {
        if(MessagesController.getInstance(UserConfig.selectedAccount).getChat(Integer.valueOf(((int)arg10))) == null) {
            FileLog.d("goToChannel 1");
            ArrayList v2 = new ArrayList();
            ProgressDialog v3 = new ProgressDialog(((Context)arg14));
            v3.setMessage(LocaleController.getString("Loading", 2131625103));
            v3.setCanceledOnTouchOutside(true);
            v3.setCancelable(true);
            v3.show();
            v3.setOnCancelListener(new DialogInterface$OnCancelListener(v2) {
                public void onCancel(DialogInterface arg2) {
                    FileLog.d("goToChannel 2");
                    this.a.add(Boolean.valueOf(true));
                }
            });
            v3.setOnDismissListener(new DialogInterface$OnDismissListener(v2) {
                public void onDismiss(DialogInterface arg2) {
                    FileLog.d("goToChannel 3");
                    this.a.add(Boolean.valueOf(true));
                }
            });
            MessagesController.loadChannelInfoByUsername(arg15, new d(v2, v3, arg10, arg15, arg12, arg14) {
                public void onResult(Object arg4, int arg5) {
                    FileLog.d("goToChannel 4");
                    if(!this.a.isEmpty()) {
                        return;
                    }

                    if(this.b != null) {
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                try {
                                    if(this.a.b != null) {
                                        this.a.b.dismiss();
                                    }

                                    return;
                                }
                                catch(Exception ) {
                                    return;
                                }
                            }
                        });
                    }

                    FileLog.d("goToChannel 5");
                    if(MessagesController.getInstance(UserConfig.selectedAccount).getChat(Integer.valueOf(((int)this.c))) == null) {
                        FileLog.d("goToChannel 6");
                        if(!TextUtils.isEmpty(this.d)) {
                            FileLog.d("goToChannel 7");
                            MessagesController.getInstance(UserConfig.selectedAccount).openByUserName(this.d, LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1), 1);
                        }
                    }
                    else {
                        FileLog.d("goToChannel 8");
                        g.a(this.c, this.e, this.f);
                    }
                }
            });
        }
        else {
            g.b(arg10, arg12, arg14);
        }
    }

    private static void b(long arg2, long arg4, Activity arg6) {
        Bundle v0 = new Bundle();
        v0.putInt("chat_id", ((int)arg2));
        if(arg4 != 0) {
            v0.putInt("message_id", ((int)arg4));
        }

        if(MessagesController.getInstance(UserConfig.selectedAccount).checkCanOpenChat(v0, new BaseFragment())) {
            ((LaunchActivity)arg6).presentFragment(new ChatActivity(v0));
        }
    }
}

