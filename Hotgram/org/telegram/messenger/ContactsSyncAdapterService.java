package org.telegram.messenger;

import android.accounts.Account;
import android.accounts.OperationCanceledException;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;

public class ContactsSyncAdapterService extends Service {
    class SyncAdapterImpl extends AbstractThreadedSyncAdapter {
        private Context mContext;

        public SyncAdapterImpl(Context arg2) {
            super(arg2, true);
            this.mContext = arg2;
        }

        public void onPerformSync(Account arg7, Bundle arg8, String arg9, ContentProviderClient arg10, SyncResult arg11) {
            try {
                ContactsSyncAdapterService.performSync(this.mContext, arg7, arg8, arg9, arg10, arg11);
            }
            catch(OperationCanceledException v7) {
                FileLog.e(((Throwable)v7));
            }
        }
    }

    private static SyncAdapterImpl sSyncAdapter;

    static {
    }

    public ContactsSyncAdapterService() {
        super();
    }

    static void access$000(Context arg0, Account arg1, Bundle arg2, String arg3, ContentProviderClient arg4, SyncResult arg5) {
        ContactsSyncAdapterService.performSync(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    private SyncAdapterImpl getSyncAdapter() {
        if(ContactsSyncAdapterService.sSyncAdapter == null) {
            ContactsSyncAdapterService.sSyncAdapter = new SyncAdapterImpl(((Context)this));
        }

        return ContactsSyncAdapterService.sSyncAdapter;
    }

    public IBinder onBind(Intent arg1) {
        return this.getSyncAdapter().getSyncAdapterBinder();
    }

    private static void performSync(Context arg0, Account arg1, Bundle arg2, String arg3, ContentProviderClient arg4, SyncResult arg5) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("performSync: " + arg1.toString());
        }
    }
}

