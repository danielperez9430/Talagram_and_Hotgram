package org.telegram.messenger.voip;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.PhoneAccountHandle;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

@TargetApi(value=26) public class TelegramConnectionService extends ConnectionService {
    public TelegramConnectionService() {
        super();
    }

    public void onCreate() {
        super.onCreate();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.w("ConnectionService created");
        }
    }

    public Connection onCreateIncomingConnection(PhoneAccountHandle arg3, ConnectionRequest arg4) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("onCreateIncomingConnection ");
        }

        Bundle v3 = arg4.getExtras();
        Connection v1 = null;
        if(v3.getInt("call_type") == 1) {
            VoIPService v3_1 = VoIPService.getSharedInstance();
            if(v3_1 == null) {
                return v1;
            }

            if(v3_1.isOutgoing()) {
                return v1;
            }

            return v3_1.getConnectionAndStartCall();
        }

        v3.getInt("call_type");
        return v1;
    }

    public void onCreateIncomingConnectionFailed(PhoneAccountHandle arg1, ConnectionRequest arg2) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.e("onCreateIncomingConnectionFailed ");
        }

        if(VoIPBaseService.getSharedInstance() != null) {
            VoIPBaseService.getSharedInstance().callFailedFromConnectionService();
        }
    }

    public Connection onCreateOutgoingConnection(PhoneAccountHandle arg3, ConnectionRequest arg4) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("onCreateOutgoingConnection ");
        }

        Bundle v3 = arg4.getExtras();
        Connection v1 = null;
        if(v3.getInt("call_type") == 1) {
            VoIPService v3_1 = VoIPService.getSharedInstance();
            if(v3_1 == null) {
                return v1;
            }

            return v3_1.getConnectionAndStartCall();
        }

        v3.getInt("call_type");
        return v1;
    }

    public void onCreateOutgoingConnectionFailed(PhoneAccountHandle arg1, ConnectionRequest arg2) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.e("onCreateOutgoingConnectionFailed ");
        }

        if(VoIPBaseService.getSharedInstance() != null) {
            VoIPBaseService.getSharedInstance().callFailedFromConnectionService();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.w("ConnectionService destroyed");
        }
    }
}

