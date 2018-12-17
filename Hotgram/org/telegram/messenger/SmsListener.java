package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsListener extends BroadcastReceiver {
    public SmsListener() {
        super();
    }

    public void onReceive(Context arg6, Intent arg7) {
        String v3;
        boolean v6;
        if(!arg7.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            v6 = arg7.getAction().equals("android.provider.Telephony.NEW_OUTGOING_SMS");
            if(v6) {
            }
            else {
                return;
            }
        }
        else {
            v6 = false;
        }

        if(!AndroidUtilities.isWaitingForSms()) {
            return;
        }

        Bundle v7 = arg7.getExtras();
        if(v7 != null) {
            try {
                Object v7_1 = v7.get("pdus");
                SmsMessage[] v1 = new SmsMessage[v7_1.length];
                v3 = "";
                int v2;
                for(v2 = 0; v2 < v1.length; ++v2) {
                    v1[v2] = SmsMessage.createFromPdu(v7_1[v2]);
                    v3 = v3 + v1[v2].getMessageBody();
                }
            }
            catch(Throwable v6_1) {
                goto label_56;
            }

            if(v6) {
                return;
            }

            try {
                Matcher v6_2 = Pattern.compile("[0-9]+").matcher(((CharSequence)v3));
                if(!v6_2.find()) {
                    return;
                }

                if(v6_2.group(0).length() < 3) {
                    return;
                }

                AndroidUtilities.runOnUIThread(new Runnable(v6_2) {
                    public void run() {
                        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReceiveSmsCode, new Object[]{this.val$matcher.group(0)});
                    }
                });
            }
            catch(Throwable v6_1) {
                try {
                    FileLog.e(v6_1);
                }
                catch(Throwable v6_1) {
                label_56:
                    FileLog.e(v6_1);
                }
            }
        }
    }
}

