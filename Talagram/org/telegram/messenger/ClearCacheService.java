package org.telegram.messenger;

import android.app.IntentService;
import android.content.Intent;
import android.util.SparseArray;

public class ClearCacheService extends IntentService {
    public ClearCacheService() {
        super("ClearCacheService");
    }

    protected void onHandleIntent(Intent arg3) {
        ApplicationLoader.postInitApplication();
        int v3 = MessagesController.getGlobalMainSettings().getInt("keep_media", 1);
        if(v3 == 2) {
            return;
        }

        Utilities.globalQueue.postRunnable(new Runnable(v3) {
            public void run() {
                int v0;
                if(this.val$keepMedia == 0) {
                    v0 = 7;
                }
                else if(this.val$keepMedia == 1) {
                    v0 = 30;
                }
                else {
                    v0 = 3;
                }

                long v1 = System.currentTimeMillis() / 1000 - (((long)(v0 * 86400)));
                SparseArray v0_1 = ImageLoader.getInstance().createMediaPaths();
                int v4;
                for(v4 = 0; v4 < v0_1.size(); ++v4) {
                    if(v0_1.keyAt(v4) != 4) {
                        try {
                            Utilities.clearDir(v0_1.valueAt(v4).getAbsolutePath(), 0, v1);
                        }
                        catch(Throwable v5) {
                            FileLog.e(v5);
                        }
                    }
                }
            }
        });
    }
}

