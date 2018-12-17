package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.LongSparseArray;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$PhotoSize;

public class DownloadController implements NotificationCenterDelegate {
    public interface FileDownloadProgressListener {
        int getObserverTag();

        void onFailedDownload(String arg1);

        void onProgressDownload(String arg1, float arg2);

        void onProgressUpload(String arg1, float arg2, boolean arg3);

        void onSuccessDownload(String arg1);
    }

    public static final int AUTODOWNLOAD_MASK_AUDIO = 2;
    public static final int AUTODOWNLOAD_MASK_DOCUMENT = 8;
    public static final int AUTODOWNLOAD_MASK_GIF = 32;
    public static final int AUTODOWNLOAD_MASK_MUSIC = 16;
    public static final int AUTODOWNLOAD_MASK_PHOTO = 1;
    public static final int AUTODOWNLOAD_MASK_VIDEO = 4;
    public static final int AUTODOWNLOAD_MASK_VIDEOMESSAGE = 64;
    private static volatile DownloadController[] Instance;
    private HashMap addLaterArray;
    private ArrayList audioDownloadQueue;
    private int currentAccount;
    private ArrayList deleteLaterArray;
    private ArrayList documentDownloadQueue;
    private HashMap downloadQueueKeys;
    private ArrayList gifDownloadQueue;
    public boolean globalAutodownloadEnabled;
    private int lastCheckMask;
    private int lastTag;
    private boolean listenerInProgress;
    private HashMap loadingFileMessagesObservers;
    private HashMap loadingFileObservers;
    public int[] mobileDataDownloadMask;
    public int[] mobileMaxFileSize;
    private ArrayList musicDownloadQueue;
    private SparseArray observersByTag;
    private ArrayList photoDownloadQueue;
    public int[] roamingDownloadMask;
    public int[] roamingMaxFileSize;
    private LongSparseArray typingTimes;
    private ArrayList videoDownloadQueue;
    private ArrayList videoMessageDownloadQueue;
    public int[] wifiDownloadMask;
    public int[] wifiMaxFileSize;

    static {
        DownloadController.Instance = new DownloadController[3];
    }

    public DownloadController(int arg9) {
        String v6_1;
        Integer v7_1;
        Integer v5_1;
        StringBuilder v4;
        super();
        int v0 = 4;
        this.mobileDataDownloadMask = new int[v0];
        this.wifiDownloadMask = new int[v0];
        this.roamingDownloadMask = new int[v0];
        int v1 = 7;
        this.mobileMaxFileSize = new int[v1];
        this.wifiMaxFileSize = new int[v1];
        this.roamingMaxFileSize = new int[v1];
        int v2 = 0;
        this.lastCheckMask = 0;
        this.photoDownloadQueue = new ArrayList();
        this.audioDownloadQueue = new ArrayList();
        this.videoMessageDownloadQueue = new ArrayList();
        this.documentDownloadQueue = new ArrayList();
        this.musicDownloadQueue = new ArrayList();
        this.gifDownloadQueue = new ArrayList();
        this.videoDownloadQueue = new ArrayList();
        this.downloadQueueKeys = new HashMap();
        this.loadingFileObservers = new HashMap();
        this.loadingFileMessagesObservers = new HashMap();
        this.observersByTag = new SparseArray();
        this.listenerInProgress = false;
        this.addLaterArray = new HashMap();
        this.deleteLaterArray = new ArrayList();
        this.lastTag = 0;
        this.typingTimes = new LongSparseArray();
        this.currentAccount = arg9;
        SharedPreferences v9 = MessagesController.getMainSettings(this.currentAccount);
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            v4 = new StringBuilder();
            v4.append("mobileDataDownloadMask");
            if(v3 == 0) {
                String v5 = "";
            }
            else {
                v5_1 = Integer.valueOf(v3);
            }

            v4.append(v5_1);
            String v4_1 = v4.toString();
            if(v3 == 0 || (v9.contains(v4_1))) {
                int v6 = 115;
                this.mobileDataDownloadMask[v3] = v9.getInt(v4_1, v6);
                int[] v4_2 = this.wifiDownloadMask;
                StringBuilder v5_2 = new StringBuilder();
                v5_2.append("wifiDownloadMask");
                if(v3 == 0) {
                    String v7 = "";
                }
                else {
                    v7_1 = Integer.valueOf(v3);
                }

                v5_2.append(v7_1);
                v4_2[v3] = v9.getInt(v5_2.toString(), v6);
                v4_2 = this.roamingDownloadMask;
                v5_2 = new StringBuilder();
                v5_2.append("roamingDownloadMask");
                if(v3 == 0) {
                    v6_1 = "";
                }
                else {
                    Integer v6_2 = Integer.valueOf(v3);
                }

                v5_2.append(v6_1);
                v4_2[v3] = v9.getInt(v5_2.toString(), 0);
            }
            else {
                this.mobileDataDownloadMask[v3] = this.mobileDataDownloadMask[0];
                this.wifiDownloadMask[v3] = this.wifiDownloadMask[0];
                this.roamingDownloadMask[v3] = this.roamingDownloadMask[0];
            }
        }

        while(v2 < v1) {
            if(v2 == 1) {
                v0 = 2097152;
            }
            else if(v2 == 6) {
                v0 = 5242880;
            }
            else {
                v0 = 10485760;
            }

            int[] v3_1 = this.mobileMaxFileSize;
            v4 = new StringBuilder();
            v4.append("mobileMaxDownloadSize");
            v4.append(v2);
            v3_1[v2] = v9.getInt(v4.toString(), v0);
            v3_1 = this.wifiMaxFileSize;
            v4 = new StringBuilder();
            v4.append("wifiMaxDownloadSize");
            v4.append(v2);
            v3_1[v2] = v9.getInt(v4.toString(), v0);
            v3_1 = this.roamingMaxFileSize;
            v4 = new StringBuilder();
            v4.append("roamingMaxDownloadSize");
            v4.append(v2);
            v3_1[v2] = v9.getInt(v4.toString(), v0);
            ++v2;
        }

        this.globalAutodownloadEnabled = v9.getBoolean("globalAutodownloadEnabled", true);
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                NotificationCenter.getInstance(DownloadController.access$000(DownloadController.this)).addObserver(DownloadController.this, NotificationCenter.FileDidFailedLoad);
                NotificationCenter.getInstance(DownloadController.access$000(DownloadController.this)).addObserver(DownloadController.this, NotificationCenter.FileDidLoaded);
                NotificationCenter.getInstance(DownloadController.access$000(DownloadController.this)).addObserver(DownloadController.this, NotificationCenter.FileLoadProgressChanged);
                NotificationCenter.getInstance(DownloadController.access$000(DownloadController.this)).addObserver(DownloadController.this, NotificationCenter.FileUploadProgressChanged);
                NotificationCenter.getInstance(DownloadController.access$000(DownloadController.this)).addObserver(DownloadController.this, NotificationCenter.httpFileDidLoaded);
                NotificationCenter.getInstance(DownloadController.access$000(DownloadController.this)).addObserver(DownloadController.this, NotificationCenter.httpFileDidFailedLoad);
            }
        });
        ApplicationLoader.applicationContext.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                DownloadController.this.checkAutodownloadSettings();
            }
        }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        if(UserConfig.getInstance(this.currentAccount).isClientActivated()) {
            this.checkAutodownloadSettings();
        }
    }

    static int access$000(DownloadController arg0) {
        return arg0.currentAccount;
    }

    public void addLoadingFileObserver(String arg2, FileDownloadProgressListener arg3) {
        this.addLoadingFileObserver(arg2, null, arg3);
    }

    public void addLoadingFileObserver(String arg3, MessageObject arg4, FileDownloadProgressListener arg5) {
        ArrayList v0_1;
        if(this.listenerInProgress) {
            this.addLaterArray.put(arg3, arg5);
            return;
        }

        this.removeLoadingFileObserver(arg5);
        Object v0 = this.loadingFileObservers.get(arg3);
        if(v0 == null) {
            v0_1 = new ArrayList();
            this.loadingFileObservers.put(arg3, v0_1);
        }

        v0_1.add(new WeakReference(arg5));
        if(arg4 != null) {
            v0 = this.loadingFileMessagesObservers.get(arg3);
            if(v0 == null) {
                v0_1 = new ArrayList();
                this.loadingFileMessagesObservers.put(arg3, v0_1);
            }

            v0_1.add(arg4);
        }

        this.observersByTag.put(arg5.getObserverTag(), arg3);
    }

    public boolean canDownloadMedia(Message arg7) {
        int v4_1;
        int v0;
        boolean v1 = false;
        if(!this.globalAutodownloadEnabled) {
            return 0;
        }

        int v2 = 2;
        if(MessageObject.isPhoto(arg7)) {
            v0 = 1;
        }
        else if(MessageObject.isVoiceMessage(arg7)) {
            v0 = 2;
        }
        else if(MessageObject.isRoundVideoMessage(arg7)) {
            v0 = 64;
        }
        else if(MessageObject.isVideoMessage(arg7)) {
            v0 = 4;
        }
        else if(MessageObject.isMusicMessage(arg7)) {
            v0 = 16;
        }
        else if(MessageObject.isGifMessage(arg7)) {
            v0 = 32;
        }
        else {
            v0 = 8;
        }

        Peer v4 = arg7.to_id;
        if(v4 == null) {
        label_52:
            v2 = 1;
        }
        else if(v4.user_id != 0) {
            if(ContactsController.getInstance(this.currentAccount).contactsDict.containsKey(Integer.valueOf(v4.user_id))) {
                v2 = 0;
            }
            else {
                goto label_52;
            }
        }
        else if(v4.chat_id != 0) {
        }
        else if(MessageObject.isMegagroup(arg7)) {
        }
        else {
            v2 = 3;
        }

        if(ConnectionsManager.isConnectedToWiFi()) {
            v2 = this.wifiDownloadMask[v2];
            v4_1 = this.wifiMaxFileSize[DownloadController.maskToIndex(v0)];
        }
        else if(ConnectionsManager.isRoaming()) {
            v2 = this.roamingDownloadMask[v2];
            v4_1 = this.roamingMaxFileSize[DownloadController.maskToIndex(v0)];
        }
        else {
            v2 = this.mobileDataDownloadMask[v2];
            v4_1 = this.mobileMaxFileSize[DownloadController.maskToIndex(v0)];
        }

        if((v0 == 1 || MessageObject.getMessageSize(arg7) <= v4_1) && (v2 & v0) != 0) {
            v1 = true;
        }

        return v1;
    }

    public boolean canDownloadMedia(MessageObject arg1) {
        return this.canDownloadMedia(arg1.messageOwner);
    }

    public void checkAutodownloadSettings() {
        int v0 = this.getCurrentDownloadMask();
        if(v0 == this.lastCheckMask) {
            return;
        }

        this.lastCheckMask = v0;
        if((v0 & 1) == 0) {
            int v1;
            for(v1 = 0; v1 < this.photoDownloadQueue.size(); ++v1) {
                FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.photoDownloadQueue.get(v1).object);
            }

            this.photoDownloadQueue.clear();
        }
        else if(this.photoDownloadQueue.isEmpty()) {
            this.newDownloadObjectsAvailable(1);
        }

        int v4 = 2;
        if((v0 & 2) == 0) {
            for(v1 = 0; v1 < this.audioDownloadQueue.size(); ++v1) {
                FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.audioDownloadQueue.get(v1).object);
            }

            this.audioDownloadQueue.clear();
        }
        else if(this.audioDownloadQueue.isEmpty()) {
            this.newDownloadObjectsAvailable(v4);
        }

        int v5 = 64;
        if((v0 & 64) == 0) {
            for(v1 = 0; v1 < this.videoMessageDownloadQueue.size(); ++v1) {
                FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.videoMessageDownloadQueue.get(v1).object);
            }

            this.videoMessageDownloadQueue.clear();
        }
        else if(this.videoMessageDownloadQueue.isEmpty()) {
            this.newDownloadObjectsAvailable(v5);
        }

        int v6 = 8;
        if((v0 & 8) == 0) {
            for(v1 = 0; v1 < this.documentDownloadQueue.size(); ++v1) {
                FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.documentDownloadQueue.get(v1).object);
            }

            this.documentDownloadQueue.clear();
        }
        else if(this.documentDownloadQueue.isEmpty()) {
            this.newDownloadObjectsAvailable(v6);
        }

        int v7 = 4;
        if((v0 & 4) == 0) {
            for(v1 = 0; v1 < this.videoDownloadQueue.size(); ++v1) {
                FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.videoDownloadQueue.get(v1).object);
            }

            this.videoDownloadQueue.clear();
        }
        else if(this.videoDownloadQueue.isEmpty()) {
            this.newDownloadObjectsAvailable(v7);
        }

        int v8 = 16;
        if((v0 & 16) == 0) {
            for(v1 = 0; v1 < this.musicDownloadQueue.size(); ++v1) {
                FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.musicDownloadQueue.get(v1).object);
            }

            this.musicDownloadQueue.clear();
        }
        else if(this.musicDownloadQueue.isEmpty()) {
            this.newDownloadObjectsAvailable(v8);
        }

        v1 = 32;
        if((v0 & v1) == 0) {
            for(v0 = 0; v0 < this.gifDownloadQueue.size(); ++v0) {
                FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.gifDownloadQueue.get(v0).object);
            }

            this.gifDownloadQueue.clear();
        }
        else if(this.gifDownloadQueue.isEmpty()) {
            this.newDownloadObjectsAvailable(v1);
        }

        v0 = this.getAutodownloadMaskAll();
        if(v0 == 0) {
            MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(0);
        }
        else {
            if((v0 & 1) == 0) {
                MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(1);
            }

            if((v0 & 2) == 0) {
                MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(v4);
            }

            if((v0 & 64) == 0) {
                MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(v5);
            }

            if((v0 & 4) == 0) {
                MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(v7);
            }

            if((v0 & 8) == 0) {
                MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(v6);
            }

            if((v0 & 16) == 0) {
                MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(v8);
            }

            if((v0 & v1) != 0) {
                return;
            }

            MessagesStorage.getInstance(this.currentAccount).clearDownloadQueue(v1);
        }
    }

    private void checkDownloadFinished(String arg6, int arg7) {
        Object v0 = this.downloadQueueKeys.get(arg6);
        if(v0 != null) {
            this.downloadQueueKeys.remove(arg6);
            int v6 = 2;
            if(arg7 == 0 || arg7 == v6) {
                MessagesStorage.getInstance(this.currentAccount).removeFromDownloadQueue(((DownloadObject)v0).id, ((DownloadObject)v0).type, false);
            }

            if(((DownloadObject)v0).type == 1) {
                this.photoDownloadQueue.remove(v0);
                if(!this.photoDownloadQueue.isEmpty()) {
                    return;
                }

                this.newDownloadObjectsAvailable(1);
                return;
            }

            if(((DownloadObject)v0).type == v6) {
                this.audioDownloadQueue.remove(v0);
                if(!this.audioDownloadQueue.isEmpty()) {
                    return;
                }

                this.newDownloadObjectsAvailable(v6);
                return;
            }

            arg7 = 64;
            if(((DownloadObject)v0).type == arg7) {
                this.videoMessageDownloadQueue.remove(v0);
                if(!this.videoMessageDownloadQueue.isEmpty()) {
                    return;
                }
            }
            else {
                arg7 = 4;
                if(((DownloadObject)v0).type == arg7) {
                    this.videoDownloadQueue.remove(v0);
                    if(this.videoDownloadQueue.isEmpty()) {
                    }
                    else {
                        return;
                    }
                }
                else {
                    arg7 = 8;
                    if(((DownloadObject)v0).type == arg7) {
                        this.documentDownloadQueue.remove(v0);
                        if(this.documentDownloadQueue.isEmpty()) {
                        }
                        else {
                            return;
                        }
                    }
                    else {
                        arg7 = 16;
                        if(((DownloadObject)v0).type == arg7) {
                            this.musicDownloadQueue.remove(v0);
                            if(this.musicDownloadQueue.isEmpty()) {
                            }
                            else {
                                return;
                            }
                        }
                        else {
                            arg7 = 32;
                            if(((DownloadObject)v0).type == arg7) {
                                this.gifDownloadQueue.remove(v0);
                                if(this.gifDownloadQueue.isEmpty()) {
                                }
                                else {
                                    return;
                                }
                            }
                            else {
                                return;
                            }
                        }
                    }
                }
            }

            this.newDownloadObjectsAvailable(arg7);
        }
    }

    public void cleanup() {
        this.photoDownloadQueue.clear();
        this.audioDownloadQueue.clear();
        this.videoMessageDownloadQueue.clear();
        this.documentDownloadQueue.clear();
        this.videoDownloadQueue.clear();
        this.musicDownloadQueue.clear();
        this.gifDownloadQueue.clear();
        this.downloadQueueKeys.clear();
        this.typingTimes.clear();
    }

    public void didReceivedNotification(int arg12, int arg13, Object[] arg14) {
        Long v4_2;
        LongSparseArray v0_3;
        int v14_1;
        Object v4;
        Object v0_1;
        int v0;
        Object v14;
        Object v13;
        Object v12;
        if(arg12 != NotificationCenter.FileDidFailedLoad) {
            if(arg12 == NotificationCenter.httpFileDidFailedLoad) {
            }
            else {
                if(arg12 != NotificationCenter.FileDidLoaded) {
                    if(arg12 == NotificationCenter.httpFileDidLoaded) {
                    }
                    else {
                        if(arg12 == NotificationCenter.FileLoadProgressChanged) {
                            this.listenerInProgress = true;
                            v12 = arg14[0];
                            v13 = this.loadingFileObservers.get(v12);
                            if(v13 != null) {
                                v14 = arg14[1];
                                v0 = ((ArrayList)v13).size();
                                int v2;
                                for(v2 = 0; v2 < v0; ++v2) {
                                    Object v3 = ((ArrayList)v13).get(v2);
                                    if(((WeakReference)v3).get() != null) {
                                        ((WeakReference)v3).get().onProgressDownload(((String)v12), ((Float)v14).floatValue());
                                    }
                                }
                            }

                            this.listenerInProgress = false;
                            this.processLaterArrays();
                        }
                        else {
                            if(arg12 != NotificationCenter.FileUploadProgressChanged) {
                                return;
                            }

                            this.listenerInProgress = true;
                            v12 = arg14[0];
                            v13 = this.loadingFileObservers.get(v12);
                            if(v13 != null) {
                                v0_1 = arg14[1];
                                v14 = arg14[2];
                                v2 = ((ArrayList)v13).size();
                                int v3_1;
                                for(v3_1 = 0; v3_1 < v2; ++v3_1) {
                                    v4 = ((ArrayList)v13).get(v3_1);
                                    if(((WeakReference)v4).get() != null) {
                                        ((WeakReference)v4).get().onProgressUpload(((String)v12), ((Float)v0_1).floatValue(), ((Boolean)v14).booleanValue());
                                    }
                                }
                            }

                            this.listenerInProgress = false;
                            this.processLaterArrays();
                            try {
                                ArrayList v13_1 = SendMessagesHelper.getInstance(this.currentAccount).getDelayedMessages(((String)v12));
                                if(v13_1 == null) {
                                    return;
                                }

                                v14_1 = 0;
                                while(true) {
                                label_63:
                                    if(v14_1 >= v13_1.size()) {
                                        return;
                                    }

                                    v0_1 = v13_1.get(v14_1);
                                    if(((DelayedMessage)v0_1).encryptedChat == null) {
                                        long v2_1 = ((DelayedMessage)v0_1).peer;
                                        int v5 = 5;
                                        long v6 = 4000;
                                        int v8 = 4;
                                        if(((DelayedMessage)v0_1).type == v8) {
                                            v4 = this.typingTimes.get(v2_1);
                                            if(v4 != null && ((Long)v4).longValue() + v6 >= System.currentTimeMillis()) {
                                                break;
                                            }

                                            HashMap v0_2 = ((DelayedMessage)v0_1).extraHashMap;
                                            StringBuilder v4_1 = new StringBuilder();
                                            v4_1.append(((String)v12));
                                            v4_1.append("_i");
                                            v0_1 = v0_2.get(v4_1.toString());
                                            if(v0_1 == null || !((MessageObject)v0_1).isVideo()) {
                                                MessagesController.getInstance(this.currentAccount).sendTyping(v2_1, v8, 0);
                                            }
                                            else {
                                                MessagesController.getInstance(this.currentAccount).sendTyping(v2_1, v5, 0);
                                            }

                                            v0_3 = this.typingTimes;
                                            v4_2 = Long.valueOf(System.currentTimeMillis());
                                        }
                                        else {
                                            v4 = this.typingTimes.get(v2_1);
                                            ((DelayedMessage)v0_1).obj.getDocument();
                                            if(v4 != null && ((Long)v4).longValue() + v6 >= System.currentTimeMillis()) {
                                                break;
                                            }

                                            if(((DelayedMessage)v0_1).obj.isRoundVideo()) {
                                                MessagesController.getInstance(this.currentAccount).sendTyping(v2_1, 8, 0);
                                            }
                                            else if(((DelayedMessage)v0_1).obj.isVideo()) {
                                                MessagesController.getInstance(this.currentAccount).sendTyping(v2_1, v5, 0);
                                            }
                                            else if(((DelayedMessage)v0_1).obj.isVoice()) {
                                                MessagesController.getInstance(this.currentAccount).sendTyping(v2_1, 9, 0);
                                            }
                                            else if(((DelayedMessage)v0_1).obj.getDocument() != null) {
                                                MessagesController.getInstance(this.currentAccount).sendTyping(v2_1, 3, 0);
                                            }
                                            else if(((DelayedMessage)v0_1).location != null) {
                                                MessagesController.getInstance(this.currentAccount).sendTyping(v2_1, v8, 0);
                                            }

                                            v0_3 = this.typingTimes;
                                            v4_2 = Long.valueOf(System.currentTimeMillis());
                                        }

                                        v0_3.put(v2_1, v4_2);
                                    }

                                    break;
                                }
                            }
                            catch(Exception v12_1) {
                                goto label_158;
                            }

                            ++v14_1;
                            goto label_63;
                        label_158:
                            FileLog.e(((Throwable)v12_1));
                        }

                        return;
                    }
                }

                this.listenerInProgress = true;
                v12 = arg14[0];
                v13 = this.loadingFileMessagesObservers.get(v12);
                if(v13 != null) {
                    v14_1 = ((ArrayList)v13).size();
                    for(v2 = 0; v2 < v14_1; ++v2) {
                        ((ArrayList)v13).get(v2).mediaExists = true;
                    }

                    this.loadingFileMessagesObservers.remove(v12);
                }

                v13 = this.loadingFileObservers.get(v12);
                if(v13 != null) {
                    v14_1 = ((ArrayList)v13).size();
                    for(v0 = 0; v0 < v14_1; ++v0) {
                        Object v2_2 = ((ArrayList)v13).get(v0);
                        if(((WeakReference)v2_2).get() != null) {
                            ((WeakReference)v2_2).get().onSuccessDownload(((String)v12));
                            this.observersByTag.remove(((WeakReference)v2_2).get().getObserverTag());
                        }
                    }

                    this.loadingFileObservers.remove(v12);
                }

                this.listenerInProgress = false;
                this.processLaterArrays();
                this.checkDownloadFinished(((String)v12), 0);
                return;
            }
        }

        this.listenerInProgress = true;
        v12 = arg14[0];
        v13 = this.loadingFileObservers.get(v12);
        if(v13 != null) {
            v2 = ((ArrayList)v13).size();
            for(v3_1 = 0; v3_1 < v2; ++v3_1) {
                v4 = ((ArrayList)v13).get(v3_1);
                if(((WeakReference)v4).get() != null) {
                    ((WeakReference)v4).get().onFailedDownload(((String)v12));
                    this.observersByTag.remove(((WeakReference)v4).get().getObserverTag());
                }
            }

            this.loadingFileObservers.remove(v12);
        }

        this.listenerInProgress = false;
        this.processLaterArrays();
        this.checkDownloadFinished(((String)v12), arg14[1].intValue());
    }

    public int generateObserverTag() {
        int v0 = this.lastTag;
        this.lastTag = v0 + 1;
        return v0;
    }

    protected int getAutodownloadMask() {
        int[] v0;
        if(!this.globalAutodownloadEnabled) {
            return 0;
        }

        if(ConnectionsManager.isConnectedToWiFi()) {
            v0 = this.wifiDownloadMask;
        }
        else if(ConnectionsManager.isRoaming()) {
            v0 = this.roamingDownloadMask;
        }
        else {
            v0 = this.mobileDataDownloadMask;
        }

        int v2 = 0;
        int v3 = 0;
        while(true) {
            int v4 = 4;
            if(v2 >= v4) {
                return v3;
            }

            int v6 = 1;
            if((v0[v2] & 1) != 0) {
            }
            else {
                v6 = 0;
            }

            if((v0[v2] & 2) != 0) {
                v6 |= 2;
            }

            if((v0[v2] & 64) != 0) {
                v6 |= 64;
            }

            if((v4 & v0[v2]) != 0) {
                v6 |= 4;
            }

            if((v0[v2] & 8) != 0) {
                v6 |= 8;
            }

            if((v0[v2] & 16) != 0) {
                v6 |= 16;
            }

            if((v0[v2] & 32) != 0) {
                v6 |= 32;
            }

            v3 |= v6 << v2 * 8;
            ++v2;
        }

        return v3;
    }

    protected int getAutodownloadMaskAll() {
        int v1 = 0;
        if(!this.globalAutodownloadEnabled) {
            return 0;
        }

        int v0 = 0;
        while(true) {
            int v2 = 4;
            if(v1 >= v2) {
                return v0;
            }

            if((this.mobileDataDownloadMask[v1] & 1) != 0 || (this.wifiDownloadMask[v1] & 1) != 0 || (this.roamingDownloadMask[v1] & 1) != 0) {
                v0 |= 1;
            }

            if((this.mobileDataDownloadMask[v1] & 2) != 0 || (this.wifiDownloadMask[v1] & 2) != 0 || (this.roamingDownloadMask[v1] & 2) != 0) {
                v0 |= 2;
            }

            if((this.mobileDataDownloadMask[v1] & 64) != 0 || (this.wifiDownloadMask[v1] & 64) != 0 || (this.roamingDownloadMask[v1] & 64) != 0) {
                v0 |= 64;
            }

            if((this.mobileDataDownloadMask[v1] & v2) != 0 || (this.wifiDownloadMask[v1] & v2) != 0 || (v2 & this.roamingDownloadMask[v1]) != 0) {
                v0 |= 4;
            }

            if((this.mobileDataDownloadMask[v1] & 8) != 0 || (this.wifiDownloadMask[v1] & 8) != 0 || (this.roamingDownloadMask[v1] & 8) != 0) {
                v0 |= 8;
            }

            if((this.mobileDataDownloadMask[v1] & 16) != 0 || (this.wifiDownloadMask[v1] & 16) != 0 || (this.roamingDownloadMask[v1] & 16) != 0) {
                v0 |= 16;
            }

            if((this.mobileDataDownloadMask[v1] & 32) != 0 || (this.wifiDownloadMask[v1] & 32) != 0 || (this.roamingDownloadMask[v1] & 32) != 0) {
                v0 |= 32;
            }

            ++v1;
        }

        return v0;
    }

    protected int getCurrentDownloadMask() {
        int v0;
        int v1 = 0;
        if(!this.globalAutodownloadEnabled) {
            return 0;
        }

        int v2 = 4;
        if(ConnectionsManager.isConnectedToWiFi()) {
            v0 = 0;
            while(v1 < v2) {
                v0 |= this.wifiDownloadMask[v1];
                ++v1;
            }

            return v0;
        }

        if(ConnectionsManager.isRoaming()) {
            v0 = 0;
            while(v1 < v2) {
                v0 |= this.roamingDownloadMask[v1];
                ++v1;
            }

            return v0;
        }

        v0 = 0;
        while(v1 < v2) {
            v0 |= this.mobileDataDownloadMask[v1];
            ++v1;
        }

        return v0;
    }

    public static DownloadController getInstance(int arg3) {
        DownloadController v0 = DownloadController.Instance[arg3];
        if(v0 == null) {
            Class v1 = DownloadController.class;
            __monitor_enter(v1);
            try {
                v0 = DownloadController.Instance[arg3];
                if(v0 == null) {
                    DownloadController[] v0_1 = DownloadController.Instance;
                    DownloadController v2 = new DownloadController(arg3);
                    v0_1[arg3] = v2;
                    v0 = v2;
                }

                __monitor_exit(v1);
                return v0;
            label_16:
                __monitor_exit(v1);
            }
            catch(Throwable v3) {
                goto label_16;
            }

            throw v3;
        }

        return v0;
    }

    public static int maskToIndex(int arg3) {
        if(arg3 == 1) {
            return 0;
        }

        int v2 = 2;
        if(arg3 == v2) {
            return 1;
        }

        int v1 = 4;
        if(arg3 == v1) {
            return v2;
        }

        if(arg3 == 8) {
            return 3;
        }

        if(arg3 == 16) {
            return v1;
        }

        if(arg3 == 32) {
            return 5;
        }

        if(arg3 == 64) {
            return 6;
        }

        return 0;
    }

    protected void newDownloadObjectsAvailable(int arg4) {
        int v0 = this.getCurrentDownloadMask();
        if((v0 & 1) != 0 && (arg4 & 1) != 0 && (this.photoDownloadQueue.isEmpty())) {
            MessagesStorage.getInstance(this.currentAccount).getDownloadQueue(1);
        }

        if((v0 & 2) != 0 && (arg4 & 2) != 0 && (this.audioDownloadQueue.isEmpty())) {
            MessagesStorage.getInstance(this.currentAccount).getDownloadQueue(2);
        }

        if((v0 & 64) != 0 && (arg4 & 64) != 0 && (this.videoMessageDownloadQueue.isEmpty())) {
            MessagesStorage.getInstance(this.currentAccount).getDownloadQueue(64);
        }

        if((v0 & 4) != 0 && (arg4 & 4) != 0 && (this.videoDownloadQueue.isEmpty())) {
            MessagesStorage.getInstance(this.currentAccount).getDownloadQueue(4);
        }

        if((v0 & 8) != 0 && (arg4 & 8) != 0 && (this.documentDownloadQueue.isEmpty())) {
            MessagesStorage.getInstance(this.currentAccount).getDownloadQueue(8);
        }

        if((v0 & 16) != 0 && (arg4 & 16) != 0 && (this.musicDownloadQueue.isEmpty())) {
            MessagesStorage.getInstance(this.currentAccount).getDownloadQueue(16);
        }

        int v1 = 32;
        if((v0 & v1) != 0 && (arg4 & v1) != 0 && (this.gifDownloadQueue.isEmpty())) {
            MessagesStorage.getInstance(this.currentAccount).getDownloadQueue(v1);
        }
    }

    protected void processDownloadObjects(int arg11, ArrayList arg12) {
        int v7_2;
        int v9;
        ArrayList v11;
        if(arg12.isEmpty()) {
            return;
        }

        String v0 = null;
        int v1 = 2;
        if(arg11 == 1) {
            v11 = this.photoDownloadQueue;
        }
        else if(arg11 == v1) {
            v11 = this.audioDownloadQueue;
        }
        else if(arg11 == 64) {
            v11 = this.videoMessageDownloadQueue;
        }
        else if(arg11 == 4) {
            v11 = this.videoDownloadQueue;
        }
        else if(arg11 == 8) {
            v11 = this.documentDownloadQueue;
        }
        else if(arg11 == 16) {
            v11 = this.musicDownloadQueue;
        }
        else if(arg11 == 32) {
            v11 = this.gifDownloadQueue;
        }
        else {
            v11 = ((ArrayList)v0);
        }

        int v4;
        for(v4 = 0; v4 < arg12.size(); ++v4) {
            Object v5 = arg12.get(v4);
            TLObject v6 = (((DownloadObject)v5).object instanceof Document) ? ((DownloadObject)v5).object : ((DownloadObject)v5).object;
            String v6_1 = FileLoader.getAttachFileName(v6);
            if(this.downloadQueueKeys.containsKey(v6_1)) {
            }
            else {
                if((((DownloadObject)v5).object instanceof PhotoSize)) {
                    FileLoader v7 = FileLoader.getInstance(this.currentAccount);
                    TLObject v8 = ((DownloadObject)v5).object;
                    v9 = ((DownloadObject)v5).secret ? 2 : 0;
                    v7.loadFile(((PhotoSize)v8), v0, v9);
                    goto label_75;
                }
                else {
                    if((((DownloadObject)v5).object instanceof Document)) {
                        TLObject v7_1 = ((DownloadObject)v5).object;
                        FileLoader v8_1 = FileLoader.getInstance(this.currentAccount);
                        v9 = ((DownloadObject)v5).secret ? 2 : 0;
                        v8_1.loadFile(((Document)v7_1), false, v9);
                    label_75:
                        v7_2 = 1;
                        goto label_78;
                    }

                    v7_2 = 0;
                }

            label_78:
                if(v7_2 == 0) {
                    goto label_82;
                }

                v11.add(v5);
                this.downloadQueueKeys.put(v6_1, v5);
            }

        label_82:
        }
    }

    private void processLaterArrays() {
        Iterator v0 = this.addLaterArray.entrySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            this.addLoadingFileObserver(((Map$Entry)v1).getKey(), ((Map$Entry)v1).getValue());
        }

        this.addLaterArray.clear();
        v0 = this.deleteLaterArray.iterator();
        while(v0.hasNext()) {
            this.removeLoadingFileObserver(v0.next());
        }

        this.deleteLaterArray.clear();
    }

    public void removeLoadingFileObserver(FileDownloadProgressListener arg6) {
        if(this.listenerInProgress) {
            this.deleteLaterArray.add(arg6);
            return;
        }

        Object v0 = this.observersByTag.get(arg6.getObserverTag());
        if(v0 != null) {
            Object v1 = this.loadingFileObservers.get(v0);
            if(v1 != null) {
                int v2;
                for(v2 = 0; v2 < ((ArrayList)v1).size(); ++v2) {
                    Object v3 = ((ArrayList)v1).get(v2);
                    if(((WeakReference)v3).get() == null || ((WeakReference)v3).get() == arg6) {
                        ((ArrayList)v1).remove(v2);
                        --v2;
                    }
                }

                if(!((ArrayList)v1).isEmpty()) {
                    goto label_28;
                }

                this.loadingFileObservers.remove(v0);
            }

        label_28:
            this.observersByTag.remove(arg6.getObserverTag());
        }
    }
}

