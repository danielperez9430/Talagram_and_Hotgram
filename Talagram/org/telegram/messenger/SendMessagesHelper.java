package org.telegram.messenger;

import android.app.AlertDialog$Builder;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaCodecInfo;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.g.b.a.c;
import android.text.TextUtils;
import android.util.Base64;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.MediaBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.googlecode.mp4parser.util.Matrix;
import com.googlecode.mp4parser.util.Path;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel$MapMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.QuickAckDelegate;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$BotInlineResult;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$DecryptedMessage;
import org.telegram.tgnet.TLRPC$DecryptedMessageMedia;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$InputDocument;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$InputMedia;
import org.telegram.tgnet.TLRPC$InputPeer;
import org.telegram.tgnet.TLRPC$InputStickerSet;
import org.telegram.tgnet.TLRPC$InputUser;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$MessageEntity;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$Photo;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$ReplyMarkup;
import org.telegram.tgnet.TLRPC$TL_botInlineMediaResult;
import org.telegram.tgnet.TLRPC$TL_botInlineMessageMediaAuto;
import org.telegram.tgnet.TLRPC$TL_botInlineMessageMediaContact;
import org.telegram.tgnet.TLRPC$TL_botInlineMessageMediaGeo;
import org.telegram.tgnet.TLRPC$TL_botInlineMessageMediaVenue;
import org.telegram.tgnet.TLRPC$TL_botInlineMessageText;
import org.telegram.tgnet.TLRPC$TL_decryptedMessage;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionAbortKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionAcceptKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionCommitKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionDeleteMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionFlushHistory;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionNoop;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionNotifyLayer;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionReadMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionRequestKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionResend;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionScreenshotMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionTyping;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaContact;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaEmpty;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaExternalDocument;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaGeoPoint;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaVenue;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaVideo;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_decryptedMessage_layer45;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAnimated;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAudio;
import org.telegram.tgnet.TLRPC$TL_documentAttributeFilename;
import org.telegram.tgnet.TLRPC$TL_documentAttributeImageSize;
import org.telegram.tgnet.TLRPC$TL_documentAttributeSticker;
import org.telegram.tgnet.TLRPC$TL_documentAttributeSticker_layer55;
import org.telegram.tgnet.TLRPC$TL_documentAttributeVideo;
import org.telegram.tgnet.TLRPC$TL_documentAttributeVideo_layer65;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_fileLocationUnavailable;
import org.telegram.tgnet.TLRPC$TL_game;
import org.telegram.tgnet.TLRPC$TL_geoPoint;
import org.telegram.tgnet.TLRPC$TL_inputDocument;
import org.telegram.tgnet.TLRPC$TL_inputEncryptedFile;
import org.telegram.tgnet.TLRPC$TL_inputGeoPoint;
import org.telegram.tgnet.TLRPC$TL_inputMediaContact;
import org.telegram.tgnet.TLRPC$TL_inputMediaDocument;
import org.telegram.tgnet.TLRPC$TL_inputMediaEmpty;
import org.telegram.tgnet.TLRPC$TL_inputMediaGame;
import org.telegram.tgnet.TLRPC$TL_inputMediaGeoLive;
import org.telegram.tgnet.TLRPC$TL_inputMediaGeoPoint;
import org.telegram.tgnet.TLRPC$TL_inputMediaGifExternal;
import org.telegram.tgnet.TLRPC$TL_inputMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_inputMediaUploadedDocument;
import org.telegram.tgnet.TLRPC$TL_inputMediaUploadedPhoto;
import org.telegram.tgnet.TLRPC$TL_inputMediaVenue;
import org.telegram.tgnet.TLRPC$TL_inputPeerChannel;
import org.telegram.tgnet.TLRPC$TL_inputPeerEmpty;
import org.telegram.tgnet.TLRPC$TL_inputPeerUser;
import org.telegram.tgnet.TLRPC$TL_inputPhoto;
import org.telegram.tgnet.TLRPC$TL_inputSingleMedia;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetEmpty;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetShortName;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonBuy;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonGame;
import org.telegram.tgnet.TLRPC$TL_message;
import org.telegram.tgnet.TLRPC$TL_messageActionScreenshotTaken;
import org.telegram.tgnet.TLRPC$TL_messageEncryptedAction;
import org.telegram.tgnet.TLRPC$TL_messageEntityBold;
import org.telegram.tgnet.TLRPC$TL_messageEntityCode;
import org.telegram.tgnet.TLRPC$TL_messageEntityItalic;
import org.telegram.tgnet.TLRPC$TL_messageEntityPre;
import org.telegram.tgnet.TLRPC$TL_messageEntityTextUrl;
import org.telegram.tgnet.TLRPC$TL_messageEntityUrl;
import org.telegram.tgnet.TLRPC$TL_messageFwdHeader;
import org.telegram.tgnet.TLRPC$TL_messageMediaContact;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaEmpty;
import org.telegram.tgnet.TLRPC$TL_messageMediaGame;
import org.telegram.tgnet.TLRPC$TL_messageMediaGeo;
import org.telegram.tgnet.TLRPC$TL_messageMediaGeoLive;
import org.telegram.tgnet.TLRPC$TL_messageMediaInvoice;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaVenue;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_messageService;
import org.telegram.tgnet.TLRPC$TL_message_secret;
import org.telegram.tgnet.TLRPC$TL_messages_botCallbackAnswer;
import org.telegram.tgnet.TLRPC$TL_messages_editMessage;
import org.telegram.tgnet.TLRPC$TL_messages_forwardMessages;
import org.telegram.tgnet.TLRPC$TL_messages_getBotCallbackAnswer;
import org.telegram.tgnet.TLRPC$TL_messages_messages;
import org.telegram.tgnet.TLRPC$TL_messages_sendBroadcast;
import org.telegram.tgnet.TLRPC$TL_messages_sendEncryptedMultiMedia;
import org.telegram.tgnet.TLRPC$TL_messages_sendInlineBotResult;
import org.telegram.tgnet.TLRPC$TL_messages_sendMedia;
import org.telegram.tgnet.TLRPC$TL_messages_sendMessage;
import org.telegram.tgnet.TLRPC$TL_messages_sendMultiMedia;
import org.telegram.tgnet.TLRPC$TL_messages_sendScreenshotNotification;
import org.telegram.tgnet.TLRPC$TL_messages_uploadMedia;
import org.telegram.tgnet.TLRPC$TL_payments_getPaymentForm;
import org.telegram.tgnet.TLRPC$TL_payments_getPaymentReceipt;
import org.telegram.tgnet.TLRPC$TL_payments_paymentForm;
import org.telegram.tgnet.TLRPC$TL_payments_paymentReceipt;
import org.telegram.tgnet.TLRPC$TL_peerChannel;
import org.telegram.tgnet.TLRPC$TL_peerChat;
import org.telegram.tgnet.TLRPC$TL_peerUser;
import org.telegram.tgnet.TLRPC$TL_photo;
import org.telegram.tgnet.TLRPC$TL_photoCachedSize;
import org.telegram.tgnet.TLRPC$TL_photoSize;
import org.telegram.tgnet.TLRPC$TL_photoSizeEmpty;
import org.telegram.tgnet.TLRPC$TL_updateEditChannelMessage;
import org.telegram.tgnet.TLRPC$TL_updateEditMessage;
import org.telegram.tgnet.TLRPC$TL_updateMessageID;
import org.telegram.tgnet.TLRPC$TL_updateNewChannelMessage;
import org.telegram.tgnet.TLRPC$TL_updateNewMessage;
import org.telegram.tgnet.TLRPC$TL_updateShortSentMessage;
import org.telegram.tgnet.TLRPC$TL_user;
import org.telegram.tgnet.TLRPC$TL_userContact_old2;
import org.telegram.tgnet.TLRPC$TL_userRequest_old2;
import org.telegram.tgnet.TLRPC$TL_webPagePending;
import org.telegram.tgnet.TLRPC$TL_webPageUrlPending;
import org.telegram.tgnet.TLRPC$Updates;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$WebPage;
import org.telegram.tgnet.TLRPC$messages_Messages;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.PaymentFormActivity;

public class SendMessagesHelper implements NotificationCenterDelegate {
    class org.telegram.messenger.SendMessagesHelper$1 implements LocationProviderDelegate {
        org.telegram.messenger.SendMessagesHelper$1(SendMessagesHelper arg1) {
            SendMessagesHelper.this = arg1;
            super();
        }

        public void onLocationAcquired(Location arg2) {
            SendMessagesHelper.this.sendLocation(arg2);
            SendMessagesHelper.this.waitingForLocation.clear();
        }

        public void onUnableLocationAcquire() {
            NotificationCenter.getInstance(SendMessagesHelper.this.currentAccount).postNotificationName(NotificationCenter.wasUnableToFindCurrentLocation, new Object[]{new HashMap(SendMessagesHelper.this.waitingForLocation)});
            SendMessagesHelper.this.waitingForLocation.clear();
        }
    }

    public class DelayedMessage {
        public EncryptedChat encryptedChat;
        public HashMap extraHashMap;
        public int finalGroupMessage;
        public long groupId;
        public String httpLocation;
        public FileLocation location;
        public ArrayList messageObjects;
        public ArrayList messages;
        public MessageObject obj;
        public String originalPath;
        public ArrayList originalPaths;
        public long peer;
        ArrayList requests;
        public TLObject sendEncryptedRequest;
        public TLObject sendRequest;
        public int type;
        public boolean upload;
        public VideoEditedInfo videoEditedInfo;

        public DelayedMessage(SendMessagesHelper arg1, long arg2) {
            SendMessagesHelper.this = arg1;
            super();
            this.peer = arg2;
        }

        public void addDelayedRequest(TLObject arg3, MessageObject arg4, String arg5) {
            DelayedMessageSendAfterRequest v0 = new DelayedMessageSendAfterRequest(SendMessagesHelper.this);
            v0.request = arg3;
            v0.msgObj = arg4;
            v0.originalPath = arg5;
            if(this.requests == null) {
                this.requests = new ArrayList();
            }

            this.requests.add(v0);
        }

        public void addDelayedRequest(TLObject arg3, ArrayList arg4, ArrayList arg5) {
            DelayedMessageSendAfterRequest v0 = new DelayedMessageSendAfterRequest(SendMessagesHelper.this);
            v0.request = arg3;
            v0.msgObjs = arg4;
            v0.originalPaths = arg5;
            if(this.requests == null) {
                this.requests = new ArrayList();
            }

            this.requests.add(v0);
        }

        public void markAsError() {
            int v1 = 2;
            if(this.type == 4) {
                int v0;
                for(v0 = 0; v0 < this.messageObjects.size(); ++v0) {
                    Object v4 = this.messageObjects.get(v0);
                    MessagesStorage.getInstance(SendMessagesHelper.this.currentAccount).markMessageAsSendError(((MessageObject)v4).messageOwner);
                    ((MessageObject)v4).messageOwner.send_state = v1;
                    NotificationCenter.getInstance(SendMessagesHelper.this.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(((MessageObject)v4).getId())});
                    SendMessagesHelper.this.processSentMessage(((MessageObject)v4).getId());
                }

                HashMap v0_1 = SendMessagesHelper.this.delayedMessages;
                v0_1.remove("group_" + this.groupId);
            }
            else {
                MessagesStorage.getInstance(SendMessagesHelper.this.currentAccount).markMessageAsSendError(this.obj.messageOwner);
                this.obj.messageOwner.send_state = v1;
                NotificationCenter.getInstance(SendMessagesHelper.this.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(this.obj.getId())});
                SendMessagesHelper.this.processSentMessage(this.obj.getId());
            }

            this.sendDelayedRequests();
        }

        public void sendDelayedRequests() {
            if(this.requests != null && (this.type == 4 || this.type == 0)) {
                int v0 = this.requests.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    Object v2 = this.requests.get(v1);
                    if((((DelayedMessageSendAfterRequest)v2).request instanceof TL_messages_sendEncryptedMultiMedia)) {
                        SecretChatHelper.getInstance(SendMessagesHelper.this.currentAccount).performSendEncryptedRequest(((DelayedMessageSendAfterRequest)v2).request, this);
                    }
                    else if((((DelayedMessageSendAfterRequest)v2).request instanceof TL_messages_sendMultiMedia)) {
                        SendMessagesHelper.this.performSendMessageRequestMulti(((DelayedMessageSendAfterRequest)v2).request, ((DelayedMessageSendAfterRequest)v2).msgObjs, ((DelayedMessageSendAfterRequest)v2).originalPaths);
                    }
                    else {
                        SendMessagesHelper.this.performSendMessageRequest(((DelayedMessageSendAfterRequest)v2).request, ((DelayedMessageSendAfterRequest)v2).msgObj, ((DelayedMessageSendAfterRequest)v2).originalPath);
                    }
                }

                this.requests = null;
            }
        }
    }

    public class DelayedMessageSendAfterRequest {
        public MessageObject msgObj;
        public ArrayList msgObjs;
        public String originalPath;
        public ArrayList originalPaths;
        public TLObject request;

        protected DelayedMessageSendAfterRequest(SendMessagesHelper arg1) {
            SendMessagesHelper.this = arg1;
            super();
        }
    }

    public class LocationProvider {
        class GpsLocationListener implements LocationListener {
            GpsLocationListener(LocationProvider arg1, org.telegram.messenger.SendMessagesHelper$1 arg2) {
                this(arg1);
            }

            private GpsLocationListener(LocationProvider arg1) {
                LocationProvider.this = arg1;
                super();
            }

            public void onLocationChanged(Location arg3) {
                if(arg3 != null) {
                    if(LocationProvider.this.locationQueryCancelRunnable == null) {
                    }
                    else {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("found location " + arg3);
                        }

                        LocationProvider.this.lastKnownLocation = arg3;
                        if(arg3.getAccuracy() >= 100f) {
                            return;
                        }

                        if(LocationProvider.this.delegate != null) {
                            LocationProvider.this.delegate.onLocationAcquired(arg3);
                        }

                        if(LocationProvider.this.locationQueryCancelRunnable != null) {
                            AndroidUtilities.cancelRunOnUIThread(LocationProvider.this.locationQueryCancelRunnable);
                        }

                        LocationProvider.this.cleanup();
                    }
                }
            }

            public void onProviderDisabled(String arg1) {
            }

            public void onProviderEnabled(String arg1) {
            }

            public void onStatusChanged(String arg1, int arg2, Bundle arg3) {
            }
        }

        public interface LocationProviderDelegate {
            void onLocationAcquired(Location arg1);

            void onUnableLocationAcquire();
        }

        private LocationProviderDelegate delegate;
        private GpsLocationListener gpsLocationListener;
        private Location lastKnownLocation;
        private LocationManager locationManager;
        private Runnable locationQueryCancelRunnable;
        private GpsLocationListener networkLocationListener;

        public LocationProvider(LocationProviderDelegate arg3) {
            super();
            this.gpsLocationListener = new GpsLocationListener(this, null);
            this.networkLocationListener = new GpsLocationListener(this, null);
            this.delegate = arg3;
        }

        public LocationProvider() {
            super();
            this.gpsLocationListener = new GpsLocationListener(this, null);
            this.networkLocationListener = new GpsLocationListener(this, null);
        }

        static Runnable access$400(LocationProvider arg0) {
            return arg0.locationQueryCancelRunnable;
        }

        static Location access$500(LocationProvider arg0) {
            return arg0.lastKnownLocation;
        }

        static Location access$502(LocationProvider arg0, Location arg1) {
            arg0.lastKnownLocation = arg1;
            return arg1;
        }

        static LocationProviderDelegate access$600(LocationProvider arg0) {
            return arg0.delegate;
        }

        static void access$700(LocationProvider arg0) {
            arg0.cleanup();
        }

        private void cleanup() {
            this.locationManager.removeUpdates(this.gpsLocationListener);
            this.locationManager.removeUpdates(this.networkLocationListener);
            this.lastKnownLocation = null;
            this.locationQueryCancelRunnable = null;
        }

        public void setDelegate(LocationProviderDelegate arg1) {
            this.delegate = arg1;
        }

        public void start() {
            if(this.locationManager == null) {
                this.locationManager = ApplicationLoader.applicationContext.getSystemService("location");
            }

            try {
                this.locationManager.requestLocationUpdates("gps", 1, 0f, this.gpsLocationListener);
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
            }

            try {
                this.locationManager.requestLocationUpdates("network", 1, 0f, this.networkLocationListener);
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
            }

            try {
                this.lastKnownLocation = this.locationManager.getLastKnownLocation("gps");
                if(this.lastKnownLocation != null) {
                    goto label_37;
                }

                this.lastKnownLocation = this.locationManager.getLastKnownLocation("network");
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
            }

        label_37:
            if(this.locationQueryCancelRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(this.locationQueryCancelRunnable);
            }

            this.locationQueryCancelRunnable = new Runnable() {
                public void run() {
                    if(LocationProvider.this.locationQueryCancelRunnable != this) {
                        return;
                    }

                    if(LocationProvider.this.delegate != null) {
                        if(LocationProvider.this.lastKnownLocation != null) {
                            LocationProvider.this.delegate.onLocationAcquired(LocationProvider.this.lastKnownLocation);
                        }
                        else {
                            LocationProvider.this.delegate.onUnableLocationAcquire();
                        }
                    }

                    LocationProvider.this.cleanup();
                }
            };
            AndroidUtilities.runOnUIThread(this.locationQueryCancelRunnable, 5000);
        }

        public void stop() {
            if(this.locationManager == null) {
                return;
            }

            if(this.locationQueryCancelRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(this.locationQueryCancelRunnable);
            }

            this.cleanup();
        }
    }

    class MediaSendPrepareWorker {
        public volatile TL_photo photo;
        public CountDownLatch sync;

        MediaSendPrepareWorker(org.telegram.messenger.SendMessagesHelper$1 arg1) {
            this();
        }

        private MediaSendPrepareWorker() {
            super();
        }
    }

    public class SendingMediaInfo {
        public String caption;
        public ArrayList entities;
        public boolean isVideo;
        public ArrayList masks;
        public String path;
        public SearchImage searchImage;
        public int ttl;
        public Uri uri;
        public VideoEditedInfo videoEditedInfo;

        public SendingMediaInfo() {
            super();
        }
    }

    private static volatile SendMessagesHelper[] Instance;
    private int currentAccount;
    private ChatFull currentChatInfo;
    private HashMap delayedMessages;
    private LocationProvider locationProvider;
    private static DispatchQueue mediaSendQueue;
    private static ThreadPoolExecutor mediaSendThreadPool;
    private SparseArray sendingMessages;
    private SparseArray unsentMessages;
    private HashMap waitingForCallback;
    private HashMap waitingForLocation;

    static {
        SendMessagesHelper.mediaSendQueue = new DispatchQueue("mediaSendQueue");
        int v3 = Build$VERSION.SDK_INT >= 17 ? Runtime.getRuntime().availableProcessors() : 2;
        SendMessagesHelper.mediaSendThreadPool = new ThreadPoolExecutor(v3, v3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        SendMessagesHelper.Instance = new SendMessagesHelper[3];
    }

    public SendMessagesHelper(int arg3) {
        super();
        this.currentChatInfo = null;
        this.delayedMessages = new HashMap();
        this.unsentMessages = new SparseArray();
        this.sendingMessages = new SparseArray();
        this.waitingForLocation = new HashMap();
        this.waitingForCallback = new HashMap();
        this.locationProvider = new LocationProvider(new org.telegram.messenger.SendMessagesHelper$1(this));
        this.currentAccount = arg3;
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$F3OpGpLNH47E9LDuBXXNWIdgYDE(this));
    }

    static void access$000(SendMessagesHelper arg0, Location arg1) {
        arg0.sendLocation(arg1);
    }

    static HashMap access$100(SendMessagesHelper arg0) {
        return arg0.waitingForLocation;
    }

    static HashMap access$1000(SendMessagesHelper arg0) {
        return arg0.delayedMessages;
    }

    static int access$200(SendMessagesHelper arg0) {
        return arg0.currentAccount;
    }

    static void access$800(SendMessagesHelper arg0, TL_messages_sendMultiMedia arg1, ArrayList arg2, ArrayList arg3) {
        arg0.performSendMessageRequestMulti(arg1, arg2, arg3);
    }

    static void access$900(SendMessagesHelper arg0, TLObject arg1, MessageObject arg2, String arg3) {
        arg0.performSendMessageRequest(arg1, arg2, arg3);
    }

    public void cancelSendingMessage(ArrayList arg24) {
        ArrayList v11_2;
        int v11_1;
        int v3;
        Object v14;
        SendMessagesHelper v0 = this;
        ArrayList v1 = arg24;
        ArrayList v2 = new ArrayList();
        ArrayList v4 = new ArrayList();
        int v5 = 0;
        boolean v6 = false;
        int v7;
        for(v7 = 0; v5 < arg24.size(); v7 = v8) {
            Object v7_1 = v1.get(v5);
            v4.add(Integer.valueOf(((MessageObject)v7_1).getId()));
            int v8 = ((MessageObject)v7_1).messageOwner.to_id.channel_id;
            Message v10 = v0.removeFromSendingMessages(((MessageObject)v7_1).getId());
            if(v10 != null) {
                ConnectionsManager.getInstance(v0.currentAccount).cancelRequest(v10.reqId, true);
            }

            Iterator v10_1 = v0.delayedMessages.entrySet().iterator();
        label_29:
            while(v10_1.hasNext()) {
                Object v11 = v10_1.next();
                Object v12 = ((Map$Entry)v11).getValue();
                int v13;
                for(v13 = 0; true; ++v13) {
                    if(v13 >= ((ArrayList)v12).size()) {
                        goto label_29;
                    }

                    v14 = ((ArrayList)v12).get(v13);
                    if(((DelayedMessage)v14).type == 4) {
                        v3 = -1;
                        v12 = null;
                        v11_1 = 0;
                        break;
                    }

                    if(((DelayedMessage)v14).obj.getId() == ((MessageObject)v7_1).getId()) {
                        ((ArrayList)v12).remove(v13);
                        ((DelayedMessage)v14).sendDelayedRequests();
                        MediaController.getInstance().cancelVideoConvert(((DelayedMessage)v14).obj);
                        if(((ArrayList)v12).size() != 0) {
                            goto label_29;
                        }

                        v2.add(((Map$Entry)v11).getKey());
                        if(((DelayedMessage)v14).sendEncryptedRequest == null) {
                            goto label_29;
                        }

                        v6 = true;
                        goto label_29;
                    }
                }

                while(v11_1 < ((DelayedMessage)v14).messageObjects.size()) {
                    v12 = ((DelayedMessage)v14).messageObjects.get(v11_1);
                    if(((MessageObject)v12).getId() == ((MessageObject)v7_1).getId()) {
                        v3 = v11_1;
                    }
                    else {
                        ++v11_1;
                        continue;
                    }

                    break;
                }

                if(v3 < 0) {
                    continue;
                }

                ((DelayedMessage)v14).messageObjects.remove(v3);
                ((DelayedMessage)v14).messages.remove(v3);
                ((DelayedMessage)v14).originalPaths.remove(v3);
                if(((DelayedMessage)v14).sendRequest != null) {
                    v11_2 = ((DelayedMessage)v14).sendRequest.multi_media;
                }
                else {
                    TLObject v11_3 = ((DelayedMessage)v14).sendEncryptedRequest;
                    ((TL_messages_sendEncryptedMultiMedia)v11_3).messages.remove(v3);
                    v11_2 = ((TL_messages_sendEncryptedMultiMedia)v11_3).files;
                }

                v11_2.remove(v3);
                MediaController.getInstance().cancelVideoConvert(((MessageObject)v7_1));
                Object v3_1 = ((DelayedMessage)v14).extraHashMap.get(v12);
                if(v3_1 != null) {
                    v2.add(v3_1);
                }

                if(((DelayedMessage)v14).messageObjects.isEmpty()) {
                    ((DelayedMessage)v14).sendDelayedRequests();
                    continue;
                }

                if(((DelayedMessage)v14).finalGroupMessage == ((MessageObject)v7_1).getId()) {
                    v3_1 = ((DelayedMessage)v14).messageObjects.get(((DelayedMessage)v14).messageObjects.size() - 1);
                    ((DelayedMessage)v14).finalGroupMessage = ((MessageObject)v3_1).getId();
                    ((MessageObject)v3_1).messageOwner.params.put("final", "1");
                    TL_messages_messages v11_4 = new TL_messages_messages();
                    v11_4.messages.add(((MessageObject)v3_1).messageOwner);
                    MessagesStorage.getInstance(v0.currentAccount).putMessages(v11_4, ((DelayedMessage)v14).peer, -2, 0, false);
                }

                v0.sendReadyToSendGroup(((DelayedMessage)v14), false, true);
            }

            ++v5;
        }

        for(v3 = 0; v3 < v2.size(); ++v3) {
            Object v5_1 = v2.get(v3);
            if(((String)v5_1).startsWith("http")) {
                ImageLoader.getInstance().cancelLoadHttpFile(((String)v5_1));
            }
            else {
                FileLoader.getInstance(v0.currentAccount).cancelUploadFile(((String)v5_1), v6);
            }

            v0.stopVideoService(((String)v5_1));
            v0.delayedMessages.remove(v5_1);
        }

        if(arg24.size() != 1 || !v1.get(0).isEditing() || v1.get(0).previousMedia == null) {
            MessagesController.getInstance(v0.currentAccount).deleteMessages(v4, null, null, v7, false);
        }
        else {
            v0.revertEditingMessageObject(v1.get(0));
        }
    }

    public void cancelSendingMessage(MessageObject arg2) {
        ArrayList v0 = new ArrayList();
        v0.add(arg2);
        this.cancelSendingMessage(v0);
    }

    public void checkUnsentMessages() {
        MessagesStorage.getInstance(this.currentAccount).getUnsentMessages(1000);
    }

    public void cleanup() {
        this.delayedMessages.clear();
        this.unsentMessages.clear();
        this.sendingMessages.clear();
        this.waitingForLocation.clear();
        this.waitingForCallback.clear();
        this.currentChatInfo = null;
        this.locationProvider.stop();
    }

    private static VideoEditedInfo createCompressionSettings(String arg31) {
        long v0_7;
        float v4_1;
        int v5_4;
        double v2_2;
        int v30;
        int v8_1;
        int v6;
        List v29;
        TrackHeaderBox v0_4;
        long v5;
        float v0_3;
        long v21;
        int v2_1;
        long[] v0_2;
        MediaHeaderBox v19;
        MediaBox v18;
        Object v17;
        int v4;
        int v16;
        int v15;
        float v14;
        int v13;
        long v11;
        long v9;
        int v7;
        TrackHeaderBox v8;
        List v3;
        String v1 = arg31;
        VideoEditedInfo v2 = null;
        try {
            IsoFile v0_1 = new IsoFile(v1);
            v3 = Path.getPaths(((Container)v0_1), "/moov/trak/");
            if(Path.getPath(((Container)v0_1), "/moov/trak/mdia/minf/stbl/stsd/mp4a/") == null && (BuildVars.LOGS_ENABLED)) {
                FileLog.d("video hasn\'t mp4a atom");
            }

            if(Path.getPath(((Container)v0_1), "/moov/trak/mdia/minf/stbl/stsd/avc1/") == null) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("video hasn\'t avc1 atom");
                }

                return v2;
            }

            v8 = ((TrackHeaderBox)v2);
            v7 = 0;
            v9 = 0;
            v11 = 0;
            v13 = 0;
            v14 = 0f;
            v15 = 25;
            v16 = 0;
            while(true) {
            label_31:
                v4 = 900000;
                if(v7 >= v3.size()) {
                    goto label_154;
                }

                v17 = v3.get(v7);
                try {
                    v18 = ((TrackBox)v17).getMediaBox();
                    break;
                }
                catch(Exception v0) {
                    v18 = null;
                    goto label_72;
                }
            }
        }
        catch(Exception v0) {
            goto label_382;
        }

        try {
            v19 = v18.getMediaHeaderBox();
            goto label_38;
        }
        catch(Exception v0) {
        }

    label_72:
        v19 = null;
        goto label_73;
        try {
        label_38:
            v0_2 = v18.getMediaInformationBox().getSampleTableBox().getSampleSizeBox().getSampleSizes();
            v2_1 = 0;
            v21 = 0;
            goto label_44;
        }
        catch(Exception v0) {
        }

    label_73:
        v21 = 0;
        goto label_74;
        try {
        label_44:
            while(v2_1 < v0_2.length) {
                v21 += v0_2[v2_1];
                ++v2_1;
            }

            v0_3 = (((float)v19.getDuration())) / (((float)v19.getTimescale()));
            v5 = ((long)(((int)((((float)(8 * v21))) / v0_3))));
            v14 = v0_3;
            goto label_76;
        }
        catch(Exception v0) {
        }

        try {
        label_74:
            FileLog.e(((Throwable)v0));
            v5 = 0;
        label_76:
            v0_4 = ((TrackBox)v17).getTrackHeaderBox();
            double v27 = 0;
            if(v0_4.getWidth() == v27) {
                goto label_146;
            }
            else if(v0_4.getHeight() != v27) {
                if(v8 != null && v8.getWidth() >= v0_4.getWidth()) {
                    if(v8.getHeight() < v0_4.getHeight()) {
                    }
                    else {
                        v29 = v3;
                        goto label_150;
                    }
                }

                v2_1 = ((int)(v5 / 100000 * 100000));
                if(v2_1 > v4) {
                }
                else {
                    v4 = v2_1;
                }

                v11 += v21;
                if(v18 == null) {
                    goto label_139;
                }
                else if(v19 != null) {
                    TimeToSampleBox v5_1 = v18.getMediaInformationBox().getSampleTableBox().getTimeToSampleBox();
                    if(v5_1 != null) {
                        List v5_2 = v5_1.getEntries();
                        v6 = Math.min(v5_2.size(), 11);
                        v8_1 = 1;
                        long v17_1 = 0;
                        while(v8_1 < v6) {
                            v17_1 += v5_2.get(v8_1).getDelta();
                            ++v8_1;
                        }

                        if(v17_1 != 0) {
                            v30 = v2_1;
                            v29 = v3;
                            v2_2 = ((double)v19.getTimescale());
                            v5 = v17_1 / (((long)(v6 - 1)));
                            goto label_130;
                        }
                        else {
                            goto label_136;
                        }
                    }
                    else {
                        goto label_139;
                    }
                }
                else {
                    goto label_139;
                }

                goto label_142;
            }
            else {
                goto label_146;
            }
        }
        catch(Exception v0) {
            goto label_382;
        }

    label_130:
        double v5_3 = ((double)v5);
        Double.isNaN(v2_2);
        Double.isNaN(v5_3);
        v15 = ((int)(v2_2 / v5_3));
        goto label_142;
    label_136:
        v30 = v2_1;
        v29 = v3;
        goto label_142;
    label_139:
        v30 = v2_1;
        v29 = v3;
    label_142:
        v8 = v0_4;
        v13 = v4;
        v16 = v30;
        goto label_150;
    label_146:
        v29 = v3;
        v9 += v21;
    label_150:
        ++v7;
        v3 = v29;
        goto label_31;
    label_154:
        if(v8 == null) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("video hasn\'t trackHeaderBox atom");
            }

            return null;
        }

        if(Build$VERSION.SDK_INT < 18) {
            try {
                MediaCodecInfo v0_5 = MediaController.selectCodec("video/avc");
                if(v0_5 == null) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("no codec info for video/avc");
                    }

                    return null;
                }
                else {
                    String v2_3 = v0_5.getName();
                    if(!v2_3.equals("OMX.google.h264.encoder") && !v2_3.equals("OMX.ST.VFM.H264Enc") && !v2_3.equals("OMX.Exynos.avc.enc") && !v2_3.equals("OMX.MARVELL.VIDEO.HW.CODA7542ENCODER") && !v2_3.equals("OMX.MARVELL.VIDEO.H264ENCODER") && !v2_3.equals("OMX.k3.video.encoder.avc")) {
                        if(v2_3.equals("OMX.TI.DUCATI1.VIDEO.H264E")) {
                        }
                        else if(MediaController.selectColorFormat(v0_5, "video/avc") == 0) {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.d("no color format for video/avc");
                            }

                            return null;
                        }
                        else {
                            goto label_218;
                        }
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("unsupported encoder = " + v2_3);
                    }

                    return null;
                }

                goto label_218;
            }
            catch(Exception ) {
                return null;
            }

            return null;
        }

    label_218:
        v0_3 = 1000f;
        v14 *= v0_3;
        v2 = new VideoEditedInfo();
        v2.startTime = -1;
        v2.endTime = -1;
        v2.bitrate = v13;
        v2.originalPath = v1;
        v2.framerate = v15;
        v2.estimatedDuration = ((long)Math.ceil(((double)v14)));
        int v3_1 = ((int)v8.getWidth());
        v2.originalWidth = v3_1;
        v2.resultWidth = v3_1;
        v3_1 = ((int)v8.getHeight());
        v2.originalHeight = v3_1;
        v2.resultHeight = v3_1;
        Matrix v3_2 = v8.getMatrix();
        if(v3_2.equals(Matrix.ROTATE_90)) {
            v3_1 = 90;
        }
        else if(v3_2.equals(Matrix.ROTATE_180)) {
            v3_1 = 180;
        }
        else if(v3_2.equals(Matrix.ROTATE_270)) {
            v3_1 = 270;
        }
        else {
            v3_1 = 0;
        }

        v2.rotationValue = v3_1;
        v3_1 = MessagesController.getGlobalMainSettings().getInt("compress_video2", 1);
        v6 = 1280;
        if(v2.originalWidth > v6 || v2.originalHeight > v6) {
            v5_4 = 5;
        }
        else {
            v6 = 848;
            if(v2.originalWidth <= v6) {
                if(v2.originalHeight > v6) {
                }
                else {
                    v6 = 640;
                    if(v2.originalWidth <= v6) {
                        if(v2.originalHeight > v6) {
                        }
                        else {
                            v6 = 480;
                            if(v2.originalWidth <= v6) {
                                if(v2.originalHeight > v6) {
                                }
                                else {
                                    v5_4 = 1;
                                    goto label_297;
                                }
                            }

                            v5_4 = 2;
                            goto label_297;
                        }
                    }

                    v5_4 = 3;
                    goto label_297;
                }
            }

            v5_4 = 4;
        }

    label_297:
        if(v3_1 >= v5_4) {
            v3_1 = v5_4 - 1;
        }

        --v5_4;
        if(v3_1 != v5_4) {
            switch(v3_1) {
                case 0: {
                    v4_1 = 432f;
                    v6 = 400000;
                    break;
                }
                case 1: {
                    v4_1 = 640f;
                    v6 = 900000;
                    break;
                }
                case 2: {
                    v4_1 = 848f;
                    v6 = 1100000;
                    break;
                }
                default: {
                    v4_1 = 1280f;
                    v6 = 2500000;
                    break;
                }
            }

            v8_1 = v2.originalWidth > v2.originalHeight ? v2.originalWidth : v2.originalHeight;
            v4_1 /= ((float)v8_1);
            v2.resultWidth = Math.round((((float)v2.originalWidth)) * v4_1 / 2f) * 2;
            v2.resultHeight = Math.round((((float)v2.originalHeight)) * v4_1 / 2f) * 2;
            if(v13 == 0) {
                goto label_353;
            }

            v7 = v16;
            v13 = Math.min(v6, ((int)((((float)v7)) / v4_1)));
            v11 = ((long)((((float)(v13 / 8))) * v14 / v0_3));
        }
        else {
        label_353:
            v7 = v16;
        }

        if(v3_1 == v5_4) {
            v2.resultWidth = v2.originalWidth;
            v2.resultHeight = v2.originalHeight;
            v2.bitrate = v7;
            v0_7 = ((long)(((int)new File(v1).length())));
        }
        else {
            v2.bitrate = v13;
            v2.estimatedSize = ((long)(((int)(v9 + v11))));
            v0_7 = v2.estimatedSize + v2.estimatedSize / 32768 * 16;
        }

        v2.estimatedSize = v0_7;
        return v2;
    label_382:
        FileLog.e(((Throwable)v0));
        return null;
    }

    private static Bitmap createVideoThumbnail(String arg3, long arg4) {
        Bitmap v3_1;
        MediaMetadataRetriever v0 = new MediaMetadataRetriever();
        Bitmap v2 = null;
        try {
            v0.setDataSource(arg3);
            v3_1 = v0.getFrameAtTime(arg4, 1);
        }
        catch(Throwable v3) {
            try {
                v0.release();
                goto label_10;
            }
            catch(RuntimeException ) {
            label_10:
                throw v3;
            }
        }
        catch(Exception ) {
            try {
                v0.release();
                goto label_12;
            }
            catch(RuntimeException ) {
            label_12:
                v3_1 = v2;
                goto label_13;
            }
        }

        try {
            v0.release();
            goto label_13;
        }
        catch(RuntimeException ) {
        label_13:
            if(v3_1 == null) {
                return v2;
            }

            int v4 = v3_1.getWidth();
            int v5 = v3_1.getHeight();
            int v0_1 = Math.max(v4, v5);
            if(v0_1 > 90) {
                float v2_1 = 90f / (((float)v0_1));
                Bitmap v4_1 = Bitmaps.createScaledBitmap(v3_1, Math.round((((float)v4)) * v2_1), Math.round(v2_1 * (((float)v5))), true);
                if(v4_1 != v3_1) {
                    v3_1.recycle();
                    v3_1 = v4_1;
                }
            }

            return v3_1;
        }
    }

    public void didReceivedNotification(int arg28, int arg29, Object[] arg30) {
        StringBuilder v0_5;
        MessageObject v0_4;
        MessageObject v3_5;
        Object v8_1;
        HashMap v1_2;
        int v3_4;
        Object v2_2;
        StringBuilder v3_1;
        HashMap v2;
        Object v1;
        Object v25;
        Object v24;
        HashMap v4_2;
        int v26;
        int v5_1;
        TLObject v3_3;
        Object v0_2;
        InputMedia v0_1;
        Object v3;
        Object v4;
        Object v5;
        Object v15;
        SendMessagesHelper v6 = this;
        int v0 = arg28;
        int v8 = 3;
        int v9 = 2;
        InputEncryptedFile v10 = null;
        int v11 = 4;
        int v12 = 1;
        int v13 = 0;
        if(v0 == NotificationCenter.FileDidUpload) {
            Object v14 = arg30[0];
            v15 = arg30[1];
            v5 = arg30[v9];
            v4 = v6.delayedMessages.get(v14);
            if(v4 == null) {
                return;
            }

            while(v13 < ((ArrayList)v4).size()) {
                v3 = ((ArrayList)v4).get(v13);
                if((((DelayedMessage)v3).sendRequest instanceof TL_messages_sendMedia)) {
                    v0_1 = ((DelayedMessage)v3).sendRequest.media;
                }
                else if((((DelayedMessage)v3).sendRequest instanceof TL_messages_editMessage)) {
                    v0_1 = ((DelayedMessage)v3).sendRequest.media;
                }
                else if((((DelayedMessage)v3).sendRequest instanceof TL_messages_sendBroadcast)) {
                    v0_1 = ((DelayedMessage)v3).sendRequest.media;
                }
                else if((((DelayedMessage)v3).sendRequest instanceof TL_messages_sendMultiMedia)) {
                    v0_2 = ((DelayedMessage)v3).extraHashMap.get(v14);
                }
                else {
                    v0_1 = ((InputMedia)v10);
                }

                if(v15 == null || (((InputMedia)v0_2)) == null) {
                    v1 = v3;
                    v0_2 = v4;
                    v2_2 = v5;
                    if(v2_2 == null) {
                        goto label_257;
                    }

                    if(((DelayedMessage)v1).sendEncryptedRequest == null) {
                        goto label_257;
                    }

                    if(((DelayedMessage)v1).type == v11) {
                        v3_3 = ((DelayedMessage)v1).sendEncryptedRequest;
                        v4 = ((DelayedMessage)v1).extraHashMap.get(v14);
                        v5_1 = ((TL_messages_sendEncryptedMultiMedia)v3_3).files.indexOf(v4);
                        if(v5_1 >= 0) {
                            ((TL_messages_sendEncryptedMultiMedia)v3_3).files.set(v5_1, v2_2);
                            v26 = v13;
                            if(((InputEncryptedFile)v4).id == 1) {
                                v4_2 = ((DelayedMessage)v1).extraHashMap;
                                v4_2.get((((String)v14)) + "_i");
                                v4_2 = ((DelayedMessage)v1).extraHashMap;
                                v9_1 = new StringBuilder();
                                v9_1.append(((String)v14));
                                v9_1.append("_t");
                                ((DelayedMessage)v1).location = v4_2.get(v9_1.toString());
                                v6.stopVideoService(((DelayedMessage)v1).messageObjects.get(v5_1).messageOwner.attachPath);
                            }

                            v3 = ((TL_messages_sendEncryptedMultiMedia)v3_3).messages.get(v5_1);
                        }
                        else {
                            v26 = v13;
                            v3 = v10;
                        }
                    }
                    else {
                        v26 = v13;
                        v3_3 = ((DelayedMessage)v1).sendEncryptedRequest;
                    }

                    if(v3_3 != null) {
                        if(((((TL_decryptedMessage)v3_3).media instanceof TL_decryptedMessageMediaVideo)) || ((((TL_decryptedMessage)v3_3).media instanceof TL_decryptedMessageMediaPhoto)) || ((((TL_decryptedMessage)v3_3).media instanceof TL_decryptedMessageMediaDocument))) {
                            ((TL_decryptedMessage)v3_3).media.size = ((int)arg30[5].longValue());
                        }

                        ((TL_decryptedMessage)v3_3).media.key = arg30[v8];
                        ((TL_decryptedMessage)v3_3).media.iv = arg30[v11];
                        if(((DelayedMessage)v1).type == v11) {
                            v6.uploadMultiMedia(((DelayedMessage)v1), ((InputMedia)v10), ((InputEncryptedFile)v2_2), ((String)v14));
                            goto label_254;
                        }

                        SecretChatHelper.getInstance(v6.currentAccount).performSendEncryptedRequest(v3_3, ((DelayedMessage)v1).obj.messageOwner, ((DelayedMessage)v1).encryptedChat, v2_2, ((DelayedMessage)v1).originalPath, ((DelayedMessage)v1).obj);
                    }

                label_254:
                    ((ArrayList)v0_2).remove(v26);
                    v13 = v26 - 1;
                }
                else {
                    if(((DelayedMessage)v3).type == 0) {
                        ((InputMedia)v0_2).file = ((InputFile)v15);
                        v24 = v4;
                        v25 = v5;
                        this.performSendMessageRequest(((DelayedMessage)v3).sendRequest, ((DelayedMessage)v3).obj, ((DelayedMessage)v3).originalPath, v3, true);
                    }
                    else {
                        v1 = v3;
                        v24 = v4;
                        v25 = v5;
                        if(((DelayedMessage)v1).type == v12) {
                            if(((InputMedia)v0_2).file == null) {
                                ((InputMedia)v0_2).file = ((InputFile)v15);
                                if(((InputMedia)v0_2).thumb == null) {
                                    if(((DelayedMessage)v1).location == null) {
                                        goto label_87;
                                    }

                                    goto label_81;
                                }
                            }
                            else {
                                goto label_83;
                            }
                        }
                        else if(((DelayedMessage)v1).type == v9) {
                            if(((InputMedia)v0_2).file == null) {
                                ((InputMedia)v0_2).file = ((InputFile)v15);
                                if(((InputMedia)v0_2).thumb == null && ((DelayedMessage)v1).location != null) {
                                label_81:
                                    v6.performSendDelayedMessage(((DelayedMessage)v1));
                                    goto label_67;
                                }
                            }
                            else {
                            label_83:
                                ((InputMedia)v0_2).thumb = ((InputFile)v15);
                                ((InputMedia)v0_2).flags |= v11;
                            }
                        }
                        else if(((DelayedMessage)v1).type == v8) {
                            ((InputMedia)v0_2).file = ((InputFile)v15);
                        }
                        else {
                            goto label_106;
                        }

                    label_87:
                        v6.performSendMessageRequest(((DelayedMessage)v1).sendRequest, ((DelayedMessage)v1).obj, ((DelayedMessage)v1).originalPath);
                        goto label_67;
                    label_106:
                        if(((DelayedMessage)v1).type != v11) {
                            goto label_67;
                        }

                        if(!((((InputMedia)v0_2)) instanceof TL_inputMediaUploadedDocument)) {
                            ((InputMedia)v0_2).file = ((InputFile)v15);
                        }
                        else if(((InputMedia)v0_2).file == null) {
                            ((InputMedia)v0_2).file = ((InputFile)v15);
                            v2 = ((DelayedMessage)v1).extraHashMap;
                            v3_1 = new StringBuilder();
                            v3_1.append(((String)v14));
                            v3_1.append("_i");
                            int v2_1 = ((DelayedMessage)v1).messageObjects.indexOf(v2.get(v3_1.toString()));
                            HashMap v3_2 = ((DelayedMessage)v1).extraHashMap;
                            StringBuilder v4_1 = new StringBuilder();
                            v4_1.append(((String)v14));
                            v4_1.append("_t");
                            ((DelayedMessage)v1).location = v3_2.get(v4_1.toString());
                            v6.stopVideoService(((DelayedMessage)v1).messageObjects.get(v2_1).messageOwner.attachPath);
                            if(((InputMedia)v0_2).thumb == null && ((DelayedMessage)v1).location != null) {
                                v6.performSendDelayedMessage(((DelayedMessage)v1), v2_1);
                                goto label_67;
                            }
                        }
                        else {
                            ((InputMedia)v0_2).thumb = ((InputFile)v15);
                            ((InputMedia)v0_2).flags |= v11;
                            v2 = ((DelayedMessage)v1).extraHashMap;
                            v3_1 = new StringBuilder();
                            v3_1.append(((String)v14));
                            v3_1.append("_o");
                            v6.uploadMultiMedia(((DelayedMessage)v1), ((InputMedia)v0_2), v10, v2.get(v3_1.toString()));
                            goto label_67;
                        }

                        v6.uploadMultiMedia(((DelayedMessage)v1), ((InputMedia)v0_2), v10, ((String)v14));
                    }

                label_67:
                    v0_2 = v24;
                    ((ArrayList)v0_2).remove(v13);
                    --v13;
                    v2_2 = v25;
                }

            label_257:
                ++v13;
                v4 = v0_2;
                v5 = v2_2;
                v9 = 2;
                v12 = 1;
            }

            if(!v4.isEmpty()) {
                return;
            }

            v6.delayedMessages.remove(v14);
        }
        else {
            if(v0 == NotificationCenter.FileDidFailUpload) {
                v0_2 = arg30[0];
                boolean v1_1 = arg30[1].booleanValue();
                v2_2 = v6.delayedMessages.get(v0_2);
                if(v2_2 == null) {
                    return;
                }

                while(v13 < ((ArrayList)v2_2).size()) {
                    v3 = ((ArrayList)v2_2).get(v13);
                    if((v1_1) && ((DelayedMessage)v3).sendEncryptedRequest != null || !v1_1 && ((DelayedMessage)v3).sendRequest != null) {
                        ((DelayedMessage)v3).markAsError();
                        ((ArrayList)v2_2).remove(v13);
                        --v13;
                    }

                    ++v13;
                }

                if(!((ArrayList)v2_2).isEmpty()) {
                    return;
                }

                goto label_595;
            }
            else {
                if(v0 == NotificationCenter.FilePreparingStarted) {
                    v0_2 = arg30[0];
                    if(((MessageObject)v0_2).getId() == 0) {
                        return;
                    }

                    v1 = v6.delayedMessages.get(((MessageObject)v0_2).messageOwner.attachPath);
                    if(v1 == null) {
                        return;
                    }

                    while(v13 < ((ArrayList)v1).size()) {
                        v2_2 = ((ArrayList)v1).get(v13);
                        if(((DelayedMessage)v2_2).type == v11) {
                            v3_4 = ((DelayedMessage)v2_2).messageObjects.indexOf(v0_2);
                            v4_2 = ((DelayedMessage)v2_2).extraHashMap;
                            StringBuilder v5_2 = new StringBuilder();
                            v5_2.append(((MessageObject)v0_2).messageOwner.attachPath);
                            v5_2.append("_t");
                            ((DelayedMessage)v2_2).location = v4_2.get(v5_2.toString());
                            v6.performSendDelayedMessage(((DelayedMessage)v2_2), v3_4);
                        }
                        else if(((DelayedMessage)v2_2).obj == v0_2) {
                            ((DelayedMessage)v2_2).videoEditedInfo = ((VideoEditedInfo)v10);
                            v6.performSendDelayedMessage(((DelayedMessage)v2_2));
                        }
                        else {
                            goto label_336;
                        }

                        ((ArrayList)v1).remove(v13);
                        break;
                    label_336:
                        ++v13;
                    }

                    if(!((ArrayList)v1).isEmpty()) {
                        return;
                    }

                    v1_2 = v6.delayedMessages;
                    String v0_3 = ((MessageObject)v0_2).messageOwner.attachPath;
                    goto label_596;
                }

                if(v0 == NotificationCenter.FileNewChunkAvailable) {
                    v0_2 = arg30[0];
                    if(((MessageObject)v0_2).getId() == 0) {
                        return;
                    }

                    v15 = arg30[1];
                    long v17 = arg30[2].longValue();
                    long v1_3 = arg30[v8].longValue();
                    boolean v16 = (((int)((MessageObject)v0_2).getDialogId())) == 0 ? true : false;
                    FileLoader.getInstance(v6.currentAccount).checkUploadNewDataAvailable(((String)v15), v16, v17, v1_3);
                    if(v1_3 == 0) {
                        return;
                    }

                    v3 = v6.delayedMessages.get(((MessageObject)v0_2).messageOwner.attachPath);
                    if(v3 == null) {
                        return;
                    }

                    int v4_3;
                    for(v4_3 = 0; true; ++v4_3) {
                        if(v4_3 >= ((ArrayList)v3).size()) {
                            return;
                        }

                        v5 = ((ArrayList)v3).get(v4_3);
                        if(((DelayedMessage)v5).type == v11) {
                            int v7 = 0;
                            while(v7 < ((DelayedMessage)v5).messageObjects.size()) {
                                v8_1 = ((DelayedMessage)v5).messageObjects.get(v7);
                                if(v8_1 == v0_2) {
                                    ((MessageObject)v8_1).videoEditedInfo = ((VideoEditedInfo)v10);
                                    ((MessageObject)v8_1).messageOwner.params.remove("ve");
                                    ((MessageObject)v8_1).messageOwner.media.document.size = ((int)v1_3);
                                    ArrayList v15_1 = new ArrayList();
                                    v15_1.add(((MessageObject)v8_1).messageOwner);
                                    MessagesStorage.getInstance(v6.currentAccount).putMessages(v15_1, false, true, false, 0);
                                }
                                else {
                                    ++v7;
                                    continue;
                                }

                                break;
                            }
                        }
                        else if(((DelayedMessage)v5).obj == v0_2) {
                            ((DelayedMessage)v5).obj.videoEditedInfo = ((VideoEditedInfo)v10);
                            ((DelayedMessage)v5).obj.messageOwner.params.remove("ve");
                            ((DelayedMessage)v5).obj.messageOwner.media.document.size = ((int)v1_3);
                            ArrayList v8_2 = new ArrayList();
                            v8_2.add(((DelayedMessage)v5).obj.messageOwner);
                            MessagesStorage.getInstance(v6.currentAccount).putMessages(v8_2, false, true, false, 0);
                            return;
                        }
                    }
                }

                if(v0 == NotificationCenter.FilePreparingFailed) {
                    v0_2 = arg30[0];
                    if(((MessageObject)v0_2).getId() == 0) {
                        return;
                    }

                    v2_2 = arg30[1];
                    v6.stopVideoService(((MessageObject)v0_2).messageOwner.attachPath);
                    v1 = v6.delayedMessages.get(v2_2);
                    if(v1 == null) {
                        return;
                    }

                    for(v3_4 = 0; v3_4 < ((ArrayList)v1).size(); ++v3_4) {
                        v4 = ((ArrayList)v1).get(v3_4);
                        if(((DelayedMessage)v4).type == v11) {
                            v5_1 = 0;
                            while(v5_1 < ((DelayedMessage)v4).messages.size()) {
                                if(((DelayedMessage)v4).messageObjects.get(v5_1) == v0_2) {
                                    goto label_474;
                                }
                                else {
                                    ++v5_1;
                                    continue;
                                }
                            }
                        }
                        else if(((DelayedMessage)v4).obj == v0_2) {
                        label_474:
                            ((DelayedMessage)v4).markAsError();
                            ((ArrayList)v1).remove(v3_4);
                            --v3_4;
                        }
                    }

                    if(!((ArrayList)v1).isEmpty()) {
                        return;
                    }

                    v6.delayedMessages.remove(v2_2);
                    return;
                }

                if(v0 == NotificationCenter.httpFileDidLoaded) {
                    Object v7_1 = arg30[0];
                    v8_1 = v6.delayedMessages.get(v7_1);
                    if(v8_1 == null) {
                        return;
                    }

                    for(v9 = 0; v9 < ((ArrayList)v8_1).size(); ++v9) {
                        v4 = ((ArrayList)v8_1).get(v9);
                        if(((DelayedMessage)v4).type == 0) {
                            v3_5 = ((DelayedMessage)v4).obj;
                            v0 = 0;
                        }
                        else {
                            if(((DelayedMessage)v4).type == 2) {
                                v0_4 = ((DelayedMessage)v4).obj;
                            }
                            else if(((DelayedMessage)v4).type == v11) {
                                v0_2 = ((DelayedMessage)v4).extraHashMap.get(v7_1);
                                if(((MessageObject)v0_2).getDocument() != null) {
                                }
                                else {
                                    goto label_519;
                                }
                            }
                            else {
                                goto label_522;
                            }

                            v3_5 = v0_4;
                            v0 = 1;
                            goto label_524;
                        label_519:
                            v3 = v0_2;
                            v0 = 0;
                            goto label_524;
                        label_522:
                            v0 = -1;
                            v3_5 = ((MessageObject)v10);
                        }

                    label_524:
                        if(v0 == 0) {
                            v0_5 = new StringBuilder();
                            v0_5.append(Utilities.MD5(((String)v7_1)));
                            v0_5.append(".");
                            v0_5.append(ImageLoader.getHttpUrlExtension(((String)v7_1), "file"));
                            Utilities.globalQueue.postRunnable(new -$$Lambda$SendMessagesHelper$1kOX99gMEbip9sYs_E7UQv-97eY(this, new File(FileLoader.getDirectory(v11), v0_5.toString()), ((MessageObject)v3), ((DelayedMessage)v4), v7_1));
                        }
                        else if(v0 == 1) {
                            v0_5 = new StringBuilder();
                            v0_5.append(Utilities.MD5(((String)v7_1)));
                            v0_5.append(".gif");
                            Utilities.globalQueue.postRunnable(new -$$Lambda$SendMessagesHelper$pp0U4GJ1r75dDYF4YGnbf9kI6EU(v6, ((DelayedMessage)v4), new File(FileLoader.getDirectory(v11), v0_5.toString()), ((MessageObject)v3)));
                        }
                    }

                    v6.delayedMessages.remove(v7_1);
                    return;
                }

                if(v0 == NotificationCenter.FileDidLoaded) {
                    v0_2 = arg30[0];
                    v1 = v6.delayedMessages.get(v0_2);
                    if(v1 == null) {
                        return;
                    }

                    while(true) {
                        if(v13 >= ((ArrayList)v1).size()) {
                            goto label_595;
                        }

                        v6.performSendDelayedMessage(((ArrayList)v1).get(v13));
                        ++v13;
                    }
                }

                if(v0 != NotificationCenter.httpFileDidFailedLoad && v0 != NotificationCenter.FileDidFailedLoad) {
                    return;
                }

                v0_2 = arg30[0];
                v1 = v6.delayedMessages.get(v0_2);
                if(v1 == null) {
                    return;
                }

                while(v13 < ((ArrayList)v1).size()) {
                    ((ArrayList)v1).get(v13).markAsError();
                    ++v13;
                }

            label_595:
                v1_2 = v6.delayedMessages;
            }

        label_596:
            v1_2.remove(v0_2);
        }
    }

    public int editMessage(MessageObject arg5, String arg6, boolean arg7, BaseFragment arg8, ArrayList arg9, Runnable arg10) {
        if(arg8 != null && arg8.getParentActivity() != null) {
            if(arg10 == null) {
            }
            else {
                TL_messages_editMessage v0 = new TL_messages_editMessage();
                v0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(((int)arg5.getDialogId()));
                v0.message = arg6;
                v0.flags |= 2048;
                v0.id = arg5.getId();
                v0.no_webpage = (((int)arg7)) ^ 1;
                if(arg9 != null) {
                    v0.entities = arg9;
                    v0.flags |= 8;
                }

                return ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$SendMessagesHelper$0k3RdsQSyxpPqVyuBg3ZosCuce8(this, arg8, v0, arg10));
            }
        }

        return 0;
    }

    private void editMessageMedia(MessageObject arg21, TL_photo arg22, VideoEditedInfo arg23, TL_document arg24, String arg25, HashMap arg26, boolean arg27) {
        int v7_3;
        TL_messages_editMessage v6_5;
        DelayedMessage v5_2;
        long v6_4;
        InputDocument v5_4;
        TL_inputMediaDocument v3_5;
        TL_inputMediaUploadedDocument v3_4;
        TL_inputMediaGifExternal v3_6;
        Object v13_1;
        ArrayList v6;
        HashMap v11;
        int v12_1;
        int v3_1;
        Document v4_1;
        int v5_1;
        VideoEditedInfo v3;
        Photo v0_2;
        SendMessagesHelper v1 = this;
        MessageObject v2 = arg21;
        TL_photo v0 = arg22;
        TL_document v4 = arg24;
        String v5 = arg25;
        if(v2 == null) {
            return;
        }

        Message v7 = v2.messageOwner;
        v2.cancelEditing = false;
        try {
            long v9 = arg21.getDialogId();
            int v14 = 3;
            if(arg27) {
                if((v2.messageOwner.media instanceof TL_messageMediaPhoto)) {
                    v0_2 = v2.messageOwner.media.photo;
                    v3 = arg23;
                    v5_1 = 2;
                }
                else {
                    v4_1 = v2.messageOwner.media.document;
                    v3_1 = (MessageObject.isVideoDocument(v4_1)) || arg23 != null ? 3 : 7;
                    v5_1 = v3_1;
                    v3 = v2.videoEditedInfo;
                }

                HashMap v12 = v7.params;
                v2.editingMessage = v7.message;
                v2.editingMessageEntities = v7.entities;
                HashMap v19 = v12;
                v12_1 = v5_1;
                v5 = v7.attachPath;
                v11 = v19;
            }
            else {
                v2.previousMedia = v7.media;
                v2.previousCaption = v7.message;
                v2.previousCaptionEntities = v7.entities;
                v2.previousAttachPath = v7.attachPath;
                SerializedData v11_1 = new SerializedData(true);
                v1.writePreviousMessageData(v7, v11_1);
                SerializedData v12_2 = new SerializedData(v11_1.length());
                v1.writePreviousMessageData(v7, v12_2);
                v11 = arg26 == null ? new HashMap() : arg26;
                v11.put("prevMedia", Base64.encodeToString(v12_2.toByteArray(), 0));
                v12_2.cleanup();
                if(v0 != null) {
                    v7.media = new TL_messageMediaPhoto();
                    v7.media.flags |= v14;
                    v7.media.photo = ((Photo)v0);
                    v7.attachPath = v5 == null || arg25.length() <= 0 || !v5.startsWith("http") ? FileLoader.getPathToAttach(v0.sizes.get(v0.sizes.size() - 1).location, true).toString() : v5;
                    v12_1 = 2;
                }
                else {
                    if(v4 != null) {
                        v7.media = new TL_messageMediaDocument();
                        v7.media.flags |= v14;
                        v7.media.document = ((Document)v4);
                        v12_1 = (MessageObject.isVideoDocument(((Document)arg24))) || arg23 != null ? 3 : 7;
                        if(arg23 != null) {
                            v11.put("ve", arg23.getString());
                        }

                        v7.attachPath = v5;
                        goto label_128;
                    }

                    v12_1 = -1;
                }

            label_128:
                v7.params = v11;
                v7.send_state = v14;
                v3 = arg23;
            }

            if(v7.attachPath == null) {
                v7.attachPath = "";
            }

            v7.local_id = 0;
            if((v2.type == v14 || v3 != null || v2.type == 2) && !TextUtils.isEmpty(v7.attachPath)) {
                v2.attachPathExists = true;
            }

            if(v2.videoEditedInfo != null && v3 == null) {
                v3 = v2.videoEditedInfo;
            }

            CharSequence v13 = null;
            if(!arg27) {
                if(v2.editingMessage != null) {
                    v7.message = v2.editingMessage.toString();
                    if(v2.editingMessageEntities != null) {
                        v6 = v2.editingMessageEntities;
                        goto label_161;
                    }
                    else {
                        v6 = DataQuery.getInstance(v1.currentAccount).getEntities(new CharSequence[]{v2.editingMessage});
                        if(v6 != null && !v6.isEmpty()) {
                        label_161:
                            v7.entities = v6;
                        }
                    }

                    v2.caption = v13;
                    arg21.generateCaption();
                }

                v6 = new ArrayList();
                v6.add(v7);
                MessagesStorage.getInstance(v1.currentAccount).putMessages(v6, false, true, false, 0);
                v2.type = -1;
                arg21.setType();
                arg21.createMessageSendInfo();
                v6 = new ArrayList();
                v6.add(v2);
                NotificationCenter.getInstance(v1.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(v9), v6});
            }

            if(v11 == null || !v11.containsKey("originalPath")) {
                String v13_2 = null;
            }
            else {
                v13_1 = v11.get("originalPath");
            }

            int v6_1 = 8;
            if(v12_1 < 1 || v12_1 > 3) {
                if(v12_1 < 5) {
                }
                else if(v12_1 <= v6_1) {
                    goto label_226;
                }

                return;
            }

        label_226:
            long v14_1 = 0;
            if(v12_1 != 2) {
                if(v12_1 != 3) {
                    if(v12_1 != 7) {
                        goto label_390;
                    }

                    if(((TL_document)v4_1).access_hash == 0) {
                        if(v13_1 == null || ((String)v13_1).length() <= 0 || !((String)v13_1).startsWith("http") || v11 == null) {
                            v3_4 = new TL_inputMediaUploadedDocument();
                            v5_2 = new DelayedMessage(v1, v9);
                            v5_2.originalPath = ((String)v13_1);
                            v5_2.type = 2;
                            v5_2.obj = v2;
                            v5_2.location = ((TL_document)v4_1).thumb.location;
                        }
                        else {
                            v3_6 = new TL_inputMediaGifExternal();
                            String[] v5_5 = v11.get("url").split("\\|");
                            if(v5_5.length == 2) {
                                v3_6.url = v5_5[0];
                                ((InputMedia)v3_6).q = v5_5[1];
                            }

                            v5_2 = null;
                        }

                        ((InputMedia)v3_6).mime_type = ((TL_document)v4_1).mime_type;
                        ((InputMedia)v3_6).attributes = ((TL_document)v4_1).attributes;
                        goto label_392;
                    }

                    v3_5 = new TL_inputMediaDocument();
                    v3_5.id = new TL_inputDocument();
                    v3_5.id.id = ((TL_document)v4_1).id;
                    v5_4 = v3_5.id;
                    v6_4 = ((TL_document)v4_1).access_hash;
                }
                else if(((TL_document)v4_1).access_hash == 0) {
                    TL_inputMediaUploadedDocument v5_3 = new TL_inputMediaUploadedDocument();
                    ((InputMedia)v5_3).mime_type = ((TL_document)v4_1).mime_type;
                    ((InputMedia)v5_3).attributes = ((TL_document)v4_1).attributes;
                    if(!arg21.isGif()) {
                        if(v3 != null && (v3.muted)) {
                            goto label_309;
                        }

                        v6_1 = 1;
                        ((InputMedia)v5_3).nosound_video = true;
                    }
                    else {
                    label_309:
                        v6_1 = 1;
                    }

                    DelayedMessage v7_2 = new DelayedMessage(v1, v9);
                    v7_2.type = v6_1;
                    v7_2.obj = v2;
                    v7_2.originalPath = ((String)v13_1);
                    v7_2.location = ((TL_document)v4_1).thumb.location;
                    v7_2.videoEditedInfo = v3;
                    v3_4 = v5_3;
                    v5_2 = v7_2;
                    goto label_392;
                }
                else {
                    v3_5 = new TL_inputMediaDocument();
                    v3_5.id = new TL_inputDocument();
                    v3_5.id.id = ((TL_document)v4_1).id;
                    v5_4 = v3_5.id;
                    v6_4 = ((TL_document)v4_1).access_hash;
                }

                v5_4.access_hash = v6_4;
                goto label_391;
            label_390:
                InputMedia v3_7 = null;
            label_391:
                v5_2 = null;
            }
            else if(((TL_photo)v0_2).access_hash == v14_1) {
                TL_inputMediaUploadedPhoto v3_2 = new TL_inputMediaUploadedPhoto();
                if(v11 != null) {
                    Object v6_2 = v11.get("masks");
                    if(v6_2 != null) {
                        SerializedData v7_1 = new SerializedData(Utilities.hexToBytes(((String)v6_2)));
                        v6_1 = v7_1.readInt32(false);
                        int v11_2;
                        for(v11_2 = 0; v11_2 < v6_1; ++v11_2) {
                            ((InputMedia)v3_2).stickers.add(InputDocument.TLdeserialize(((AbstractSerializedData)v7_1), v7_1.readInt32(false), false));
                        }

                        ((InputMedia)v3_2).flags |= 1;
                        v7_1.cleanup();
                    }
                }

                DelayedMessage v6_3 = new DelayedMessage(v1, v9);
                v6_3.type = 0;
                v6_3.obj = v2;
                v6_3.originalPath = ((String)v13_1);
                if(v5 == null || v5.length() <= 0 || !v5.startsWith("http")) {
                    v6_3.location = ((TL_photo)v0_2).sizes.get(((TL_photo)v0_2).sizes.size() - 1).location;
                }
                else {
                    v6_3.httpLocation = v5;
                }

                v5_2 = v6_3;
            }
            else {
                TL_inputMediaPhoto v3_3 = new TL_inputMediaPhoto();
                v3_3.id = new TL_inputPhoto();
                v3_3.id.id = ((TL_photo)v0_2).id;
                v3_3.id.access_hash = ((TL_photo)v0_2).access_hash;
                goto label_391;
            }

        label_392:
            v6_5 = new TL_messages_editMessage();
            v6_5.id = arg21.getId();
            v6_5.peer = MessagesController.getInstance(v1.currentAccount).getInputPeer(((int)v9));
            ((TL_messages_editMessage)v6_1).flags |= 16384;
            v6_5.media = ((InputMedia)v3_5);
            if(v2.editingMessage != null) {
                v6_5.message = v2.editingMessage.toString();
                v6_5.flags |= 2048;
                if(v2.editingMessageEntities != null) {
                    v6_5.entities = v2.editingMessageEntities;
                    v3_1 = v6_5.flags;
                    v7_3 = 8;
                    goto label_419;
                }
                else {
                    ArrayList v3_8 = DataQuery.getInstance(v1.currentAccount).getEntities(new CharSequence[]{v2.editingMessage});
                    if(v3_8 != null && !v3_8.isEmpty()) {
                        v6_5.entities = v3_8;
                        v3_1 = v6_5.flags;
                        v7_3 = 8;
                    label_419:
                        v6_5.flags = v3_1 | v7_3;
                    }
                }

                CharSequence v3_9 = null;
                v2.editingMessage = v3_9;
                v2.editingMessageEntities = ((ArrayList)v3_9);
            }

            if(v5_2 != null) {
                v5_2.sendRequest = ((TLObject)v6_5);
            }

            if(v12_1 == 1) {
                v1.performSendMessageRequest(((TLObject)v6_5), v2, null);
                return;
            }

            if(v12_1 != 2) {
                if(v12_1 != 3) {
                    String v0_3 = null;
                    if(v12_1 == 6) {
                        v1.performSendMessageRequest(((TLObject)v6_5), v2, v0_3);
                        return;
                    }
                    else if(v12_1 == 7) {
                        if(((TL_document)v4_1).access_hash == 0 && v5_2 != null) {
                            goto label_451;
                        }

                        v1.performSendMessageRequest(((TLObject)v6_5), v2, ((String)v13_1));
                        return;
                    }
                    else {
                        if(v12_1 != 8) {
                            return;
                        }

                        if(((TL_document)v4_1).access_hash != 0) {
                            goto label_470;
                        }

                        goto label_492;
                    }
                }
                else if(((TL_document)v4_1).access_hash == 0) {
                    goto label_451;
                }

                goto label_470;
            }
            else if(((TL_photo)v0_2).access_hash != 0) {
                this.performSendMessageRequest(v6_5, arg21, null, null, true);
                return;
            }
        }
        catch(Exception v0_1) {
            goto label_494;
        }

    label_492:
        try {
        label_451:
            v1.performSendDelayedMessage(v5_2);
            return;
        label_470:
            v1.performSendMessageRequest(((TLObject)v6_5), v2, null);
        }
        catch(Exception v0_1) {
        label_494:
            FileLog.e(((Throwable)v0_1));
            this.revertEditingMessageObject(arg21);
        }
    }

    private static void fillVideoAttribute(String arg5, TL_documentAttributeVideo arg6, VideoEditedInfo arg7) {
        int v7_1;
        MediaMetadataRetriever v2;
        float v0 = 1000f;
        MediaMetadataRetriever v1 = null;
        try {
            v2 = new MediaMetadataRetriever();
            goto label_4;
        }
        catch(Throwable v5) {
        }
        catch(Exception v7) {
            goto label_59;
            try {
            label_4:
                v2.setDataSource(arg5);
                String v1_1 = v2.extractMetadata(18);
                if(v1_1 != null) {
                    arg6.w = Integer.parseInt(v1_1);
                }

                v1_1 = v2.extractMetadata(19);
                if(v1_1 != null) {
                    arg6.h = Integer.parseInt(v1_1);
                }

                v1_1 = v2.extractMetadata(9);
                if(v1_1 != null) {
                    arg6.duration = ((int)Math.ceil(((double)((((float)Long.parseLong(v1_1))) / v0))));
                }

                if(Build$VERSION.SDK_INT >= 17) {
                    v1_1 = v2.extractMetadata(24);
                    if(v1_1 != null) {
                        int v1_2 = Utilities.parseInt(v1_1).intValue();
                        if(arg7 != null) {
                            arg7.rotationValue = v1_2;
                        }
                        else {
                            if(v1_2 != 90 && v1_2 != 270) {
                                goto label_44;
                            }

                            v7_1 = arg6.w;
                            arg6.w = arg6.h;
                            arg6.h = v7_1;
                        }
                    }
                }

                goto label_44;
            }
            catch(Throwable v5) {
            }
            catch(Exception v7) {
                v1 = v2;
                try {
                label_59:
                    FileLog.e(((Throwable)v7));
                    if(v1 == null) {
                        goto label_65;
                    }
                }
                catch(Throwable v5) {
                    v2 = v1;
                    goto label_89;
                }

                try {
                    v1.release();
                }
                catch(Exception v7) {
                    FileLog.e(((Throwable)v7));
                }

            label_65:
                v7_1 = 0;
                goto label_66;
            }
        }

    label_89:
        if(v2 != null) {
            try {
                v2.release();
            }
            catch(Exception v6) {
                FileLog.e(((Throwable)v6));
            }
        }

        throw v5;
    label_44:
        v7_1 = 1;
        try {
            v2.release();
        }
        catch(Exception v1_3) {
            FileLog.e(((Throwable)v1_3));
        }

    label_66:
        if(v7_1 == 0) {
            try {
                MediaPlayer v5_2 = MediaPlayer.create(ApplicationLoader.applicationContext, Uri.fromFile(new File(arg5)));
                if(v5_2 == null) {
                    return;
                }

                arg6.duration = ((int)Math.ceil(((double)((((float)v5_2.getDuration())) / v0))));
                arg6.w = v5_2.getVideoWidth();
                arg6.h = v5_2.getVideoHeight();
                v5_2.release();
            }
            catch(Exception v5_1) {
                FileLog.e(((Throwable)v5_1));
            }
        }
    }

    private DelayedMessage findMaxDelayedMessageForMessageId(int arg12, long arg13) {
        int v8_2;
        MessageObject v8;
        Iterator v0 = this.delayedMessages.entrySet().iterator();
        Object v1 = null;
        int v2;
        for(v2 = -2147483648; v0.hasNext(); v2 = v6) {
            Object v3 = v0.next().getValue();
            int v4 = ((ArrayList)v3).size();
            int v6 = v2;
            Object v2_1 = v1;
            int v1_1;
            for(v1_1 = 0; v1_1 < v4; ++v1_1) {
                Object v7 = ((ArrayList)v3).get(v1_1);
                if((((DelayedMessage)v7).type == 4 || ((DelayedMessage)v7).type == 0) && ((DelayedMessage)v7).peer == arg13) {
                    if(((DelayedMessage)v7).obj != null) {
                        v8 = ((DelayedMessage)v7).obj;
                        goto label_26;
                    }
                    else {
                        if(((DelayedMessage)v7).messageObjects != null && !((DelayedMessage)v7).messageObjects.isEmpty()) {
                            Object v8_1 = ((DelayedMessage)v7).messageObjects.get(((DelayedMessage)v7).messageObjects.size() - 1);
                        label_26:
                            v8_2 = v8.getId();
                            goto label_40;
                        }

                        v8_2 = 0;
                    }

                label_40:
                    if(v8_2 == 0) {
                        goto label_46;
                    }

                    if(v8_2 <= arg12) {
                        goto label_46;
                    }

                    if(v2_1 != null) {
                        goto label_46;
                    }

                    if(v6 >= v8_2) {
                        goto label_46;
                    }

                    v2_1 = v7;
                    v6 = v8_2;
                }

            label_46:
            }

            v1 = v2_1;
        }

        return ((DelayedMessage)v1);
    }

    public TL_photo generatePhotoSizes(String arg11, Uri arg12) {
        Bitmap v0 = ImageLoader.loadBitmap(arg11, arg12, ((float)AndroidUtilities.getPhotoSize()), ((float)AndroidUtilities.getPhotoSize()), true);
        if(v0 == null && AndroidUtilities.getPhotoSize() != 800) {
            v0 = ImageLoader.loadBitmap(arg11, arg12, 800f, 800f, true);
        }

        ArrayList v11 = new ArrayList();
        PhotoSize v12 = ImageLoader.scaleAndSaveImage(v0, 90f, 90f, 55, true);
        if(v12 != null) {
            v11.add(v12);
        }

        v12 = ImageLoader.scaleAndSaveImage(v0, ((float)AndroidUtilities.getPhotoSize()), ((float)AndroidUtilities.getPhotoSize()), 80, false, 101, 101);
        if(v12 != null) {
            v11.add(v12);
        }

        if(v0 != null) {
            v0.recycle();
        }

        if(v11.isEmpty()) {
            return null;
        }

        UserConfig.getInstance(this.currentAccount).saveConfig(false);
        TL_photo v12_1 = new TL_photo();
        v12_1.date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        v12_1.sizes = v11;
        return v12_1;
    }

    protected ArrayList getDelayedMessages(String arg2) {
        return this.delayedMessages.get(arg2);
    }

    public static SendMessagesHelper getInstance(int arg3) {
        SendMessagesHelper v0 = SendMessagesHelper.Instance[arg3];
        if(v0 == null) {
            Class v1 = SendMessagesHelper.class;
            __monitor_enter(v1);
            try {
                v0 = SendMessagesHelper.Instance[arg3];
                if(v0 == null) {
                    SendMessagesHelper[] v0_1 = SendMessagesHelper.Instance;
                    SendMessagesHelper v2 = new SendMessagesHelper(arg3);
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

    protected long getNextRandomId() {
        long v0 = 0;
        long v2;
        for(v2 = v0; v2 == v0; v2 = Utilities.random.nextLong()) {
        }

        return v2;
    }

    private static String getTrimmedString(String arg3) {
        String v0 = arg3.trim();
        if(v0.length() == 0) {
            return v0;
        }

        while(arg3.startsWith("\n")) {
            arg3 = arg3.substring(1);
        }

        while(arg3.endsWith("\n")) {
            arg3 = arg3.substring(0, arg3.length() - 1);
        }

        return arg3;
    }

    public boolean isSendingCallback(MessageObject arg5, KeyboardButton arg6) {
        int v0 = 0;
        if(arg5 != null) {
            if(arg6 == null) {
            }
            else {
                if((arg6 instanceof TL_keyboardButtonGame)) {
                    v0 = 1;
                }
                else if((arg6 instanceof TL_keyboardButtonBuy)) {
                    v0 = 2;
                }

                StringBuilder v1 = new StringBuilder();
                v1.append(arg5.getDialogId());
                v1.append("_");
                v1.append(arg5.getId());
                v1.append("_");
                v1.append(Utilities.bytesToHex(arg6.data));
                v1.append("_");
                v1.append(v0);
                return this.waitingForCallback.containsKey(v1.toString());
            }
        }

        return 0;
    }

    public boolean isSendingCurrentLocation(MessageObject arg4, KeyboardButton arg5) {
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                StringBuilder v0 = new StringBuilder();
                v0.append(arg4.getDialogId());
                v0.append("_");
                v0.append(arg4.getId());
                v0.append("_");
                v0.append(Utilities.bytesToHex(arg5.data));
                v0.append("_");
                String v4 = (arg5 instanceof TL_keyboardButtonGame) ? "1" : "0";
                v0.append(v4);
                return this.waitingForLocation.containsKey(v0.toString());
            }
        }

        return 0;
    }

    public boolean isSendingMessage(int arg2) {
        boolean v2 = this.sendingMessages.indexOfKey(arg2) >= 0 ? true : false;
        return v2;
    }

    public static void lambda$didReceivedNotification$2(SendMessagesHelper arg9, File arg10, MessageObject arg11, DelayedMessage arg12, String arg13) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$a2su0LhJhPbUXm5fG2WcDQ_Npn4(arg9, arg9.generatePhotoSizes(arg10.toString(), null), arg11, arg10, arg12, arg13));
    }

    public static void lambda$didReceivedNotification$4(SendMessagesHelper arg7, DelayedMessage arg8, File arg9, MessageObject arg10) {
        Document v5 = arg8.obj.getDocument();
        if((v5.thumb.location instanceof TL_fileLocationUnavailable)) {
            Uri v0 = null;
            try {
                boolean v2 = true;
                float v3 = 90f;
                Bitmap v1_1 = ImageLoader.loadBitmap(arg9.getAbsolutePath(), v0, v3, v3, true);
                if(v1_1 == null) {
                    goto label_24;
                }

                int v4 = 55;
                if(arg8.sendEncryptedRequest != null) {
                }
                else {
                    v2 = false;
                }

                v5.thumb = ImageLoader.scaleAndSaveImage(v1_1, v3, v3, v4, v2);
                v1_1.recycle();
            }
            catch(Exception v1) {
                v5.thumb = ((PhotoSize)v0);
                FileLog.e(((Throwable)v1));
            }

        label_24:
            if(v5.thumb != null) {
                goto label_32;
            }

            v5.thumb = new TL_photoSizeEmpty();
            v5.thumb.type = "s";
        }

    label_32:
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$ivsY9c3O0F76RgXSqAIraHVU0Fk(arg7, arg8, arg9, v5, arg10));
    }

    public static void lambda$editMessage$11(SendMessagesHelper arg0, BaseFragment arg1, TL_messages_editMessage arg2, Runnable arg3, TLObject arg4, TL_error arg5) {
        if(arg5 == null) {
            MessagesController.getInstance(arg0.currentAccount).processUpdates(((Updates)arg4), false);
        }
        else {
            AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$ag0T9ipOWU0deF9XfslwNyap3gA(arg0, arg5, arg1, arg2));
        }

        AndroidUtilities.runOnUIThread(arg3);
    }

    public static void lambda$new$0(SendMessagesHelper arg2) {
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.FileDidUpload);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.FileDidFailUpload);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.FilePreparingStarted);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.FileNewChunkAvailable);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.FilePreparingFailed);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.httpFileDidFailedLoad);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.httpFileDidLoaded);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.FileDidLoaded);
        NotificationCenter.getInstance(arg2.currentAccount).addObserver(arg2, NotificationCenter.FileDidFailedLoad);
    }

    public static void lambda$null$1(SendMessagesHelper arg6, TL_photo arg7, MessageObject arg8, File arg9, DelayedMessage arg10, String arg11) {
        if(arg7 != null) {
            arg8.messageOwner.media.photo = ((Photo)arg7);
            arg8.messageOwner.attachPath = arg9.toString();
            ArrayList v1 = new ArrayList();
            v1.add(arg8.messageOwner);
            MessagesStorage.getInstance(arg6.currentAccount).putMessages(v1, false, true, false, 0);
            NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.updateMessageMedia, new Object[]{arg8.messageOwner});
            arg10.location = arg7.sizes.get(arg7.sizes.size() - 1).location;
            arg10.httpLocation = null;
            if(arg10.type == 4) {
                arg6.performSendDelayedMessage(arg10, arg10.messageObjects.indexOf(arg8));
            }
            else {
                arg6.performSendDelayedMessage(arg10);
            }
        }
        else {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("can\'t load image " + arg11 + " to file " + arg9.toString());
            }

            arg10.markAsError();
        }
    }

    public static void lambda$null$10(SendMessagesHelper arg2, TL_error arg3, BaseFragment arg4, TL_messages_editMessage arg5) {
        AlertsCreator.processError(arg2.currentAccount, arg3, arg4, ((TLObject)arg5), new Object[0]);
    }

    public static void lambda$null$12(SendMessagesHelper arg1, String arg2) {
        arg1.waitingForCallback.remove(arg2);
    }

    public static void lambda$null$13(SendMessagesHelper arg0, String arg1, TLObject arg2, TL_error arg3) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$Vs48x-dR1zvnzDoILUm1BxyX1bc(arg0, arg1));
    }

    public static void lambda$null$15(SendMessagesHelper arg7, String arg8, boolean arg9, TLObject arg10, MessageObject arg11, KeyboardButton arg12, ChatActivity arg13) {
        boolean v5;
        String v9_1;
        User v8_3;
        int v8_2;
        PaymentFormActivity v8;
        arg7.waitingForCallback.remove(arg8);
        if((arg9) && arg10 == null) {
            arg7.sendCallback(false, arg11, arg12, arg13);
        }
        else if(arg10 != null) {
            if((arg12 instanceof TL_keyboardButtonBuy)) {
                if((arg10 instanceof TL_payments_paymentForm)) {
                    MessagesController.getInstance(arg7.currentAccount).putUsers(((TL_payments_paymentForm)arg10).users, false);
                    v8 = new PaymentFormActivity(((TL_payments_paymentForm)arg10), arg11);
                }
                else if((arg10 instanceof TL_payments_paymentReceipt)) {
                    v8 = new PaymentFormActivity(arg11, ((TL_payments_paymentReceipt)arg10));
                }
                else {
                    return;
                }

                arg13.presentFragment(((BaseFragment)v8));
            }
            else {
                if(!arg9 && ((TL_messages_botCallbackAnswer)arg10).cache_time != 0) {
                    MessagesStorage.getInstance(arg7.currentAccount).saveBotCache(arg8, arg10);
                }

                DialogInterface$OnClickListener v9 = null;
                if(((TL_messages_botCallbackAnswer)arg10).message != null) {
                    if(((TL_messages_botCallbackAnswer)arg10).alert) {
                        if(arg13.getParentActivity() == null) {
                            return;
                        }

                        Builder v8_1 = new Builder(arg13.getParentActivity());
                        v8_1.setTitle(LocaleController.getString("AppName", 2131624086));
                        v8_1.setPositiveButton(LocaleController.getString("OK", 2131625420), v9);
                        v8_1.setMessage(((TL_messages_botCallbackAnswer)arg10).message);
                        arg13.showDialog(v8_1.create());
                        return;
                    }

                    v8_2 = arg11.messageOwner.from_id;
                    if(arg11.messageOwner.via_bot_id != 0) {
                        v8_2 = arg11.messageOwner.via_bot_id;
                    }

                    if(v8_2 > 0) {
                        v8_3 = MessagesController.getInstance(arg7.currentAccount).getUser(Integer.valueOf(v8_2));
                        if(v8_3 != null) {
                            v9_1 = ContactsController.formatName(v8_3.first_name, v8_3.last_name);
                        }
                    }
                    else {
                        Chat v8_4 = MessagesController.getInstance(arg7.currentAccount).getChat(Integer.valueOf(-v8_2));
                        if(v8_4 != null) {
                            v9_1 = v8_4.title;
                        }
                    }

                    if((((String)v9)) == null) {
                        v9_1 = "bot";
                    }

                    arg13.showAlert(((String)v9), ((TL_messages_botCallbackAnswer)arg10).message);
                    return;
                }

                if(((TL_messages_botCallbackAnswer)arg10).url == null) {
                    return;
                }

                if(arg13.getParentActivity() == null) {
                    return;
                }

                v8_2 = arg11.messageOwner.from_id;
                if(arg11.messageOwner.via_bot_id != 0) {
                    v8_2 = arg11.messageOwner.via_bot_id;
                }

                int v6 = v8_2;
                v8_3 = MessagesController.getInstance(arg7.currentAccount).getUser(Integer.valueOf(v6));
                v8_2 = v8_3 == null || !v8_3.verified ? 0 : 1;
                if((arg12 instanceof TL_keyboardButtonGame)) {
                    if((arg11.messageOwner.media instanceof TL_messageMediaGame)) {
                        TL_game v9_2 = arg11.messageOwner.media.game;
                    }

                    TL_game v2 = ((TL_game)v9);
                    if(v2 == null) {
                        return;
                    }

                    String v4 = ((TL_messages_botCallbackAnswer)arg10).url;
                    if(v8_2 == 0) {
                        SharedPreferences v8_5 = MessagesController.getNotificationsSettings(arg7.currentAccount);
                        StringBuilder v9_3 = new StringBuilder();
                        v9_3.append("askgame_");
                        v9_3.append(v6);
                        if(v8_5.getBoolean(v9_3.toString(), true)) {
                            v5 = true;
                        }
                        else {
                            goto label_135;
                        }
                    }
                    else {
                    label_135:
                        v5 = false;
                    }

                    arg13.showOpenGameAlert(v2, arg11, v4, v5, v6);
                    return;
                }

                arg13.showOpenUrlAlert(((TL_messages_botCallbackAnswer)arg10).url, false);
            }
        }
    }

    public static void lambda$null$18(SendMessagesHelper arg5, TLObject arg6, InputMedia arg7, DelayedMessage arg8) {
        TL_inputMediaDocument v0_1;
        TL_inputMediaPhoto v0;
        if(arg6 != null) {
            if(((arg7 instanceof TL_inputMediaUploadedPhoto)) && ((arg6 instanceof TL_messageMediaPhoto))) {
                v0 = new TL_inputMediaPhoto();
                v0.id = new TL_inputPhoto();
                v0.id.id = ((MessageMedia)arg6).photo.id;
                v0.id.access_hash = ((MessageMedia)arg6).photo.access_hash;
                goto label_38;
            }

            if(!(arg7 instanceof TL_inputMediaUploadedDocument)) {
                goto label_37;
            }

            if(!(arg6 instanceof TL_messageMediaDocument)) {
                goto label_37;
            }

            v0_1 = new TL_inputMediaDocument();
            v0_1.id = new TL_inputDocument();
            v0_1.id.id = ((MessageMedia)arg6).document.id;
            v0_1.id.access_hash = ((MessageMedia)arg6).document.access_hash;
        }
        else {
        label_37:
            v0 = null;
        }

    label_38:
        if((((TL_inputMediaPhoto)v0_1)) != null) {
            if(arg7.ttl_seconds != 0) {
                ((InputMedia)v0_1).ttl_seconds = arg7.ttl_seconds;
                ((InputMedia)v0_1).flags |= 1;
            }

            arg6 = arg8.sendRequest;
            int v3 = 0;
            while(v3 < ((TL_messages_sendMultiMedia)arg6).multi_media.size()) {
                if(((TL_messages_sendMultiMedia)arg6).multi_media.get(v3).media == arg7) {
                    ((TL_messages_sendMultiMedia)arg6).multi_media.get(v3).media = ((InputMedia)v0_1);
                }
                else {
                    ++v3;
                    continue;
                }

                break;
            }

            arg5.sendReadyToSendGroup(arg8, false, true);
        }
        else {
            arg8.markAsError();
        }
    }

    public static void lambda$null$20(SendMessagesHelper arg4, String arg5) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopEncodingService, new Object[]{arg5, Integer.valueOf(arg4.currentAccount)});
    }

    public static void lambda$null$22(SendMessagesHelper arg3, TL_updateNewMessage arg4) {
        MessagesController.getInstance(arg3.currentAccount).processNewDifferenceParams(-1, arg4.pts, -1, arg4.pts_count);
    }

    public static void lambda$null$23(SendMessagesHelper arg3, TL_updateNewChannelMessage arg4) {
        MessagesController.getInstance(arg3.currentAccount).processNewChannelDifferenceParams(arg4.pts, arg4.pts_count, arg4.message.to_id.channel_id);
    }

    public static void lambda$null$24(SendMessagesHelper arg5, Message arg6, int arg7, long arg8) {
        DataQuery.getInstance(arg5.currentAccount).increasePeerRaiting(arg6.dialog_id);
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(arg7), Integer.valueOf(arg6.id), arg6, Long.valueOf(arg6.dialog_id), Long.valueOf(arg8)});
        arg5.processSentMessage(arg7);
        arg5.removeFromSendingMessages(arg7);
    }

    public static void lambda$null$25(SendMessagesHelper arg15, Message arg16, int arg17, ArrayList arg18, long arg19) {
        MessagesStorage.getInstance(arg15.currentAccount).updateMessageStateAndId(arg16.random_id, Integer.valueOf(arg17), arg16.id, 0, false, arg16.to_id.channel_id);
        MessagesStorage.getInstance(arg15.currentAccount).putMessages(arg18, true, false, false, 0);
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$-rl6MMYW4Zu1z_upuLLWFHp_MPQ(arg15, arg16, arg17, arg19));
    }

    public static void lambda$null$26(SendMessagesHelper arg2, Updates arg3) {
        MessagesController.getInstance(arg2.currentAccount).processUpdates(arg3, false);
    }

    public static void lambda$null$27(SendMessagesHelper arg19, TL_error arg20, TLObject arg21, ArrayList arg22, ArrayList arg23, TL_messages_sendMultiMedia arg24) {
        int v11_3;
        Integer v11_1;
        TLObject v18;
        -$$Lambda$SendMessagesHelper$n0-tAuaTMMaOAxWpJda9yIvC7OY v4_1;
        DispatchQueue v3;
        SendMessagesHelper v7 = arg19;
        TL_error v0 = arg20;
        ArrayList v8 = arg22;
        if(v0 == null) {
            SparseArray v12 = new SparseArray();
            LongSparseArray v13 = new LongSparseArray();
            TLObject v14 = arg21;
            ArrayList v0_1 = ((Updates)v14).updates;
            int v1;
            for(v1 = 0; v1 < v0_1.size(); ++v1) {
                Object v2 = v0_1.get(v1);
                if((v2 instanceof TL_updateMessageID)) {
                    v13.put(((TL_updateMessageID)v2).random_id, Integer.valueOf(((TL_updateMessageID)v2).id));
                    goto label_21;
                }
                else {
                    if((v2 instanceof TL_updateNewMessage)) {
                        v12.put(((TL_updateNewMessage)v2).message.id, ((TL_updateNewMessage)v2).message);
                        v3 = Utilities.stageQueue;
                        -$$Lambda$SendMessagesHelper$qOPyYxSHeph0d7COHO2wWPkhDkU v4 = new -$$Lambda$SendMessagesHelper$qOPyYxSHeph0d7COHO2wWPkhDkU(v7, ((TL_updateNewMessage)v2));
                    }
                    else if((v2 instanceof TL_updateNewChannelMessage)) {
                        v12.put(((TL_updateNewChannelMessage)v2).message.id, ((TL_updateNewChannelMessage)v2).message);
                        v3 = Utilities.stageQueue;
                        v4_1 = new -$$Lambda$SendMessagesHelper$n0-tAuaTMMaOAxWpJda9yIvC7OY(v7, ((TL_updateNewChannelMessage)v2));
                    }
                    else {
                        goto label_45;
                    }

                    v3.postRunnable(((Runnable)v4_1));
                label_21:
                    v0_1.remove(v1);
                    --v1;
                }

            label_45:
            }

            int v15 = 0;
            while(true) {
                if(v15 < arg22.size()) {
                    Object v0_2 = v8.get(v15);
                    Object v1_1 = arg23.get(v15);
                    Message v2_1 = ((MessageObject)v0_2).messageOwner;
                    int v3_1 = v2_1.id;
                    ArrayList v4_2 = new ArrayList();
                    Object v6 = v13.get(v2_1.random_id);
                    if(v6 != null) {
                        v6 = v12.get(((Integer)v6).intValue());
                        if(v6 != null) {
                            v4_2.add(v6);
                            v2_1.id = ((Message)v6).id;
                            int v10 = -2147483648;
                            if((v2_1.flags & v10) != 0) {
                                ((Message)v6).flags |= v10;
                            }

                            long v9 = ((Message)v6).grouped_id;
                            SparseArray v16 = v12;
                            LongSparseArray v17 = v13;
                            Object v11 = MessagesController.getInstance(v7.currentAccount).dialogs_read_outbox_max.get(Long.valueOf(((Message)v6).dialog_id));
                            if(v11 == null) {
                                v18 = v14;
                                v11_1 = Integer.valueOf(MessagesStorage.getInstance(v7.currentAccount).getDialogReadMax(((Message)v6).out, ((Message)v6).dialog_id));
                                MessagesController.getInstance(v7.currentAccount).dialogs_read_outbox_max.put(Long.valueOf(((Message)v6).dialog_id), v11_1);
                            }
                            else {
                                v18 = v14;
                            }

                            boolean v11_2 = v11_1.intValue() < ((Message)v6).id ? true : false;
                            ((Message)v6).unread = v11_2;
                            v7.updateMediaPaths(((MessageObject)v0_2), ((Message)v6), ((String)v1_1), false);
                            StatsController.getInstance(v7.currentAccount).incrementSentItemsCount(ConnectionsManager.getCurrentNetworkType(), 1, 1);
                            v2_1.send_state = 0;
                            NotificationCenter.getInstance(v7.currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(v3_1), Integer.valueOf(v2_1.id), v2_1, Long.valueOf(v2_1.dialog_id), Long.valueOf(v9)});
                            MessagesStorage.getInstance(v7.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SendMessagesHelper$EY6uIGr8AoU6I4pO5daCv6Dcnrc(arg19, v2_1, v3_1, v4_2, v9));
                            ++v15;
                            v12 = v16;
                            v13 = v17;
                            v14 = v18;
                            continue;
                        }
                    }

                    break;
                }
                else {
                    goto label_153;
                }
            }

            v18 = v14;
            v11_3 = 1;
            goto label_155;
        label_153:
            v18 = v14;
            v11_3 = 0;
        label_155:
            Utilities.stageQueue.postRunnable(new -$$Lambda$SendMessagesHelper$MTNoqofH7sl8g9SU8KBlwFJl_Kg(v7, v18));
        }
        else {
            AlertsCreator.processError(v7.currentAccount, v0, null, arg24, new Object[0]);
            v11_3 = 1;
        }

        if(v11_3 != 0) {
            int v0_3;
            for(v0_3 = 0; v0_3 < arg22.size(); ++v0_3) {
                Message v1_2 = v8.get(v0_3).messageOwner;
                MessagesStorage.getInstance(v7.currentAccount).markMessageAsSendError(v1_2);
                v1_2.send_state = 2;
                NotificationCenter.getInstance(v7.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(v1_2.id)});
                v7.processSentMessage(v1_2.id);
                v7.removeFromSendingMessages(v1_2.id);
            }
        }
    }

    public static void lambda$null$29(SendMessagesHelper arg1, Message arg2) {
        arg1.processSentMessage(arg2.id);
        arg1.removeFromSendingMessages(arg2.id);
    }

    public static void lambda$null$3(SendMessagesHelper arg6, DelayedMessage arg7, File arg8, Document arg9, MessageObject arg10) {
        arg7.httpLocation = null;
        arg7.obj.messageOwner.attachPath = arg8.toString();
        arg7.location = arg9.thumb.location;
        ArrayList v1 = new ArrayList();
        v1.add(arg10.messageOwner);
        MessagesStorage.getInstance(arg6.currentAccount).putMessages(v1, false, true, false, 0);
        arg6.performSendDelayedMessage(arg7);
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.updateMessageMedia, new Object[]{arg7.obj.messageOwner});
    }

    public static void lambda$null$30(SendMessagesHelper arg2, Updates arg3, Message arg4) {
        MessagesController.getInstance(arg2.currentAccount).processUpdates(arg3, false);
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$ufx9diXgE22amdo7dq3r1ViH-CU(arg2, arg4));
    }

    public static void lambda$null$31(SendMessagesHelper arg5, TL_error arg6, Message arg7, TLObject arg8, MessageObject arg9, String arg10, TLObject arg11) {
        Message v0 = null;
        if(arg6 == null) {
            String v6 = arg7.attachPath;
            ArrayList v11 = ((Updates)arg8).updates;
            int v2 = 0;
            while(v2 < v11.size()) {
                Object v3 = v11.get(v2);
                if((v3 instanceof TL_updateEditMessage)) {
                    v0 = ((TL_updateEditMessage)v3).message;
                }
                else if((v3 instanceof TL_updateEditChannelMessage)) {
                    v0 = ((TL_updateEditChannelMessage)v3).message;
                }
                else {
                    ++v2;
                    continue;
                }

                break;
            }

            if(v0 != null) {
                ImageLoader.saveMessageThumbs(v0);
                arg5.updateMediaPaths(arg9, v0, arg10, false);
            }

            Utilities.stageQueue.postRunnable(new -$$Lambda$SendMessagesHelper$IfU3KKFrrxZqgcr_vd45AjuvXk4(arg5, ((Updates)arg8), arg7));
            if(!MessageObject.isVideoMessage(arg7) && !MessageObject.isRoundVideoMessage(arg7) && !MessageObject.isNewGifMessage(arg7)) {
                return;
            }

            arg5.stopVideoService(v6);
        }
        else {
            AlertsCreator.processError(arg5.currentAccount, arg6, ((BaseFragment)v0), arg11, new Object[0]);
            if((MessageObject.isVideoMessage(arg7)) || (MessageObject.isRoundVideoMessage(arg7)) || (MessageObject.isNewGifMessage(arg7))) {
                arg5.stopVideoService(arg7.attachPath);
            }

            arg5.removeFromSendingMessages(arg7.id);
            arg5.revertEditingMessageObject(arg9);
        }
    }

    public static void lambda$null$32(SendMessagesHelper arg4, TL_updateShortSentMessage arg5) {
        MessagesController.getInstance(arg4.currentAccount).processNewDifferenceParams(-1, arg5.pts, arg5.date, arg5.pts_count);
    }

    public static void lambda$null$33(SendMessagesHelper arg3, TL_updateNewMessage arg4) {
        MessagesController.getInstance(arg3.currentAccount).processNewDifferenceParams(-1, arg4.pts, -1, arg4.pts_count);
    }

    public static void lambda$null$34(SendMessagesHelper arg3, TL_updateNewChannelMessage arg4) {
        MessagesController.getInstance(arg3.currentAccount).processNewChannelDifferenceParams(arg4.pts, arg4.pts_count, arg4.message.to_id.channel_id);
    }

    public static void lambda$null$35(SendMessagesHelper arg2, Updates arg3) {
        MessagesController.getInstance(arg2.currentAccount).processUpdates(arg3, false);
    }

    public static void lambda$null$36(SendMessagesHelper arg7, boolean arg8, ArrayList arg9, Message arg10, int arg11) {
        if(arg8) {
            int v2;
            for(v2 = 0; v2 < arg9.size(); ++v2) {
                Object v3 = arg9.get(v2);
                ArrayList v4 = new ArrayList();
                MessageObject v5 = new MessageObject(arg7.currentAccount, ((Message)v3), false);
                v4.add(v5);
                MessagesController.getInstance(arg7.currentAccount).updateInterfaceWithMessages(v5.getDialogId(), v4, true);
            }

            NotificationCenter.getInstance(arg7.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }

        DataQuery.getInstance(arg7.currentAccount).increasePeerRaiting(arg10.dialog_id);
        NotificationCenter v9 = NotificationCenter.getInstance(arg7.currentAccount);
        v2 = NotificationCenter.messageReceivedByServer;
        Object[] v3_1 = new Object[5];
        v3_1[0] = Integer.valueOf(arg11);
        int v8 = arg8 ? arg11 : arg10.id;
        v3_1[1] = Integer.valueOf(v8);
        v3_1[2] = arg10;
        v3_1[3] = Long.valueOf(arg10.dialog_id);
        v3_1[4] = Long.valueOf(0);
        v9.postNotificationName(v2, v3_1);
        arg7.processSentMessage(arg11);
        arg7.removeFromSendingMessages(arg11);
    }

    public static void lambda$null$37(SendMessagesHelper arg16, Message arg17, int arg18, boolean arg19, ArrayList arg20, String arg21) {
        SendMessagesHelper v6 = arg16;
        Message v7 = arg17;
        MessagesStorage v8 = MessagesStorage.getInstance(v6.currentAccount);
        long v9 = v7.random_id;
        Integer v11 = Integer.valueOf(arg18);
        int v12 = arg19 ? arg18 : v7.id;
        v8.updateMessageStateAndId(v9, v11, v12, 0, false, v7.to_id.channel_id);
        MessagesStorage.getInstance(v6.currentAccount).putMessages(arg20, true, false, arg19, 0);
        if(arg19) {
            ArrayList v9_1 = new ArrayList();
            v9_1.add(v7);
            MessagesStorage.getInstance(v6.currentAccount).putMessages(v9_1, true, false, false, 0);
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$GQahKJNS--kWmavmxJx3yejWkmY(arg16, arg19, arg20, arg17, arg18));
        if((MessageObject.isVideoMessage(arg17)) || (MessageObject.isRoundVideoMessage(arg17)) || (MessageObject.isNewGifMessage(arg17))) {
            v6.stopVideoService(arg21);
        }
    }

    public static void lambda$null$38(SendMessagesHelper arg16, TL_error arg17, Message arg18, TLObject arg19, TLObject arg20, MessageObject arg21, String arg22) {
        int v9;
        Integer v1_4;
        int v1_1;
        TLObject v0_1;
        SendMessagesHelper v7 = arg16;
        TL_error v0 = arg17;
        Message v8 = arg18;
        TLObject v1 = arg19;
        TLObject v2 = arg20;
        Message v3 = null;
        if(v0 == null) {
            int v6 = v8.id;
            boolean v12 = v1 instanceof TL_messages_sendBroadcast;
            ArrayList v13 = new ArrayList();
            String v14 = v8.attachPath;
            if((v2 instanceof TL_updateShortSentMessage)) {
                v0_1 = v2;
                v1_1 = ((TL_updateShortSentMessage)v0_1).id;
                v8.id = v1_1;
                v8.local_id = v1_1;
                v8.date = ((TL_updateShortSentMessage)v0_1).date;
                v8.entities = ((TL_updateShortSentMessage)v0_1).entities;
                v8.out = ((TL_updateShortSentMessage)v0_1).out;
                if(((TL_updateShortSentMessage)v0_1).media != null) {
                    v8.media = ((TL_updateShortSentMessage)v0_1).media;
                    v8.flags |= 512;
                    ImageLoader.saveMessageThumbs(arg18);
                }

                if(((((TL_updateShortSentMessage)v0_1).media instanceof TL_messageMediaGame)) && !TextUtils.isEmpty(((TL_updateShortSentMessage)v0_1).message)) {
                    v8.message = ((TL_updateShortSentMessage)v0_1).message;
                }

                if(!v8.entities.isEmpty()) {
                    v8.flags |= 128;
                }

                Utilities.stageQueue.postRunnable(new -$$Lambda$SendMessagesHelper$86b1r-J0K-ukrKyfcKWBwPzcK3k(v7, ((TL_updateShortSentMessage)v0_1)));
                v13.add(v8);
                goto label_133;
            }
            else {
                if((v2 instanceof Updates)) {
                    v0_1 = v2;
                    ArrayList v1_2 = ((Updates)v0_1).updates;
                    int v2_1;
                    for(v2_1 = 0; v2_1 < v1_2.size(); ++v2_1) {
                        Object v4 = v1_2.get(v2_1);
                        if((v4 instanceof TL_updateNewMessage)) {
                            v3 = ((TL_updateNewMessage)v4).message;
                            v13.add(v3);
                            Utilities.stageQueue.postRunnable(new -$$Lambda$SendMessagesHelper$Z3gHiQMdya18NQeYSSZDhwTuoz8(v7, ((TL_updateNewMessage)v4)));
                        }
                        else if((v4 instanceof TL_updateNewChannelMessage)) {
                            v3 = ((TL_updateNewChannelMessage)v4).message;
                            v13.add(v3);
                            int v15 = -2147483648;
                            if((v8.flags & v15) != 0) {
                                ((TL_updateNewChannelMessage)v4).message.flags |= v15;
                            }

                            Utilities.stageQueue.postRunnable(new -$$Lambda$SendMessagesHelper$W_aKqq76bvHYH9HO3yeel-ZApxA(v7, ((TL_updateNewChannelMessage)v4)));
                        }
                        else {
                            goto label_89;
                        }

                        v1_2.remove(v2_1);
                        break;
                    label_89:
                    }

                    if(v3 != null) {
                        ImageLoader.saveMessageThumbs(v3);
                        Object v1_3 = MessagesController.getInstance(v7.currentAccount).dialogs_read_outbox_max.get(Long.valueOf(v3.dialog_id));
                        if(v1_3 == null) {
                            v1_4 = Integer.valueOf(MessagesStorage.getInstance(v7.currentAccount).getDialogReadMax(v3.out, v3.dialog_id));
                            MessagesController.getInstance(v7.currentAccount).dialogs_read_outbox_max.put(Long.valueOf(v3.dialog_id), v1_4);
                        }

                        boolean v1_5 = v1_4.intValue() < v3.id ? true : false;
                        v3.unread = v1_5;
                        v8.id = v3.id;
                        v7.updateMediaPaths(arg21, v3, arg22, false);
                        v1_1 = 0;
                    }
                    else {
                        v1_1 = 1;
                    }

                    Utilities.stageQueue.postRunnable(new -$$Lambda$SendMessagesHelper$89w2_l5kydd1wq6NNXnJqBjlLnA(v7, ((Updates)v0_1)));
                    v9 = v1_1;
                    goto label_134;
                }

            label_133:
                v9 = 0;
            }

        label_134:
            if(MessageObject.isLiveLocationMessage(arg18)) {
                LocationController.getInstance(v7.currentAccount).addSharingLocation(v8.dialog_id, v8.id, v8.media.period, arg18);
            }

            if(v9 != 0) {
                goto label_195;
            }

            StatsController.getInstance(v7.currentAccount).incrementSentItemsCount(ConnectionsManager.getCurrentNetworkType(), 1, 1);
            v8.send_state = 0;
            NotificationCenter v0_2 = NotificationCenter.getInstance(v7.currentAccount);
            v1_1 = NotificationCenter.messageReceivedByServer;
            Object[] v2_2 = new Object[5];
            v2_2[0] = Integer.valueOf(v6);
            int v3_1 = v12 ? v6 : v8.id;
            v2_2[1] = Integer.valueOf(v3_1);
            v2_2[2] = v8;
            v2_2[3] = Long.valueOf(v8.dialog_id);
            v2_2[4] = Long.valueOf(0);
            v0_2.postNotificationName(v1_1, v2_2);
            MessagesStorage.getInstance(v7.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SendMessagesHelper$c6XFmiRxzMtX4ylPGbGlYv5IPtU(arg16, arg18, v6, v12, v13, v14));
        }
        else {
            AlertsCreator.processError(v7.currentAccount, v0, ((BaseFragment)v3), v1, new Object[0]);
            v9 = 1;
        }

    label_195:
        if(v9 != 0) {
            MessagesStorage.getInstance(v7.currentAccount).markMessageAsSendError(v8);
            v8.send_state = 2;
            NotificationCenter.getInstance(v7.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(v8.id)});
            v7.processSentMessage(v8.id);
            if((MessageObject.isVideoMessage(arg18)) || (MessageObject.isRoundVideoMessage(arg18)) || (MessageObject.isNewGifMessage(arg18))) {
                v7.stopVideoService(v8.attachPath);
            }

            v7.removeFromSendingMessages(v8.id);
        }
    }

    public static void lambda$null$40(SendMessagesHelper arg3, Message arg4, int arg5) {
        arg4.send_state = 0;
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.messageReceivedByAck, new Object[]{Integer.valueOf(arg5)});
    }

    static void lambda$null$44(MessageObject arg20, int arg21, TL_document arg22, MessageObject arg23, HashMap arg24, long arg25, MessageObject arg27) {
        MessageObject v0 = arg23;
        if(arg20 != null) {
            SendMessagesHelper.getInstance(arg21).editMessageMedia(arg20, null, null, arg22, v0.messageOwner.attachPath, arg24, false);
        }
        else {
            SendMessagesHelper.getInstance(arg21).sendMessage(arg22, null, v0.messageOwner.attachPath, arg25, arg27, null, null, null, arg24, 0);
        }
    }

    static void lambda$null$46() {
        try {
            Toast.makeText(ApplicationLoader.applicationContext, LocaleController.getString("UnsupportedAttachment", 2131626279), 0).show();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    static void lambda$null$48(TL_document arg23, int arg24, String arg25, long arg26, MessageObject arg28, BotInlineResult arg29, HashMap arg30, TL_photo arg31, TL_game arg32) {
        BotInlineResult v0 = arg29;
        if(arg23 != null) {
            SendMessagesHelper.getInstance(arg24).sendMessage(arg23, null, arg25, arg26, arg28, v0.send_message.message, v0.send_message.entities, v0.send_message.reply_markup, arg30, 0);
        }
        else if(arg31 != null) {
            SendMessagesHelper v12 = SendMessagesHelper.getInstance(arg24);
            String v2 = v0.content != null ? v0.content.url : null;
            String v14 = v2;
            v12.sendMessage(arg31, v14, arg26, arg28, v0.send_message.message, v0.send_message.entities, v0.send_message.reply_markup, arg30, 0);
        }
        else {
            if(arg32 == null) {
                return;
            }

            SendMessagesHelper.getInstance(arg24).sendMessage(arg32, arg26, v0.send_message.reply_markup, arg30);
        }
    }

    public static void lambda$null$5(SendMessagesHelper arg4, Message arg5, long arg6, int arg8, Message arg9) {
        arg5.send_state = 0;
        DataQuery.getInstance(arg4.currentAccount).increasePeerRaiting(arg6);
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(arg8), Integer.valueOf(arg9.id), arg9, Long.valueOf(arg6), Long.valueOf(0)});
        arg4.processSentMessage(arg8);
        arg4.removeFromSendingMessages(arg8);
    }

    static void lambda$null$50(String arg15, int arg16, long arg17) {
        String v0 = SendMessagesHelper.getTrimmedString(arg15);
        if(v0.length() != 0) {
            int v1 = ((int)Math.ceil(((double)((((float)v0.length())) / 4096f))));
            int v2 = 0;
            while(v2 < v1) {
                int v3 = v2 * 4096;
                ++v2;
                SendMessagesHelper.getInstance(arg16).sendMessage(v0.substring(v3, Math.min(v2 * 4096, v0.length())), arg17, null, null, true, null, null, null);
            }
        }
    }

    static void lambda$null$51(String arg1, int arg2, long arg3) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$EsGJwSp0YzznLse0ZTlP5A6z7OU(arg1, arg2, arg3));
    }

    static void lambda$null$53(MediaSendPrepareWorker arg1, int arg2, SendingMediaInfo arg3) {
        arg1.photo = SendMessagesHelper.getInstance(arg2).generatePhotoSizes(arg3.path, arg3.uri);
        arg1.sync.countDown();
    }

    static void lambda$null$54(MessageObject arg20, int arg21, TL_document arg22, String arg23, HashMap arg24, long arg25, MessageObject arg27, SendingMediaInfo arg28) {
        SendingMediaInfo v0 = arg28;
        if(arg20 != null) {
            SendMessagesHelper.getInstance(arg21).editMessageMedia(arg20, null, null, arg22, arg23, arg24, false);
        }
        else {
            SendMessagesHelper.getInstance(arg21).sendMessage(arg22, null, arg23, arg25, arg27, v0.caption, v0.entities, null, arg24, 0);
        }
    }

    static void lambda$null$55(MessageObject arg22, int arg23, TL_photo arg24, boolean arg25, SendingMediaInfo arg26, HashMap arg27, long arg28, MessageObject arg30) {
        SendingMediaInfo v1 = arg26;
        String v2 = null;
        if(arg22 != null) {
            SendMessagesHelper v3 = SendMessagesHelper.getInstance(arg23);
            VideoEditedInfo v6 = null;
            TL_document v7 = null;
            if(arg25) {
                v2 = v1.searchImage.imageUrl;
            }

            v3.editMessageMedia(arg22, arg24, v6, v7, v2, arg27, false);
        }
        else {
            SendMessagesHelper v11 = SendMessagesHelper.getInstance(arg23);
            if(arg25) {
                v2 = v1.searchImage.imageUrl;
            }

            v11.sendMessage(arg24, v2, arg28, arg30, v1.caption, v1.entities, null, arg27, v1.ttl);
        }
    }

    static void lambda$null$56(Bitmap arg25, String arg26, MessageObject arg27, int arg28, VideoEditedInfo arg29, TL_document arg30, String arg31, HashMap arg32, long arg33, MessageObject arg35, SendingMediaInfo arg36) {
        Bitmap v0 = arg25;
        String v1 = arg26;
        SendingMediaInfo v2 = arg36;
        if(v0 != null && v1 != null) {
            ImageLoader.getInstance().putImageToCache(new BitmapDrawable(v0), v1);
        }

        if(arg27 != null) {
            SendMessagesHelper.getInstance(arg28).editMessageMedia(arg27, null, arg29, arg30, arg31, arg32, false);
        }
        else {
            SendMessagesHelper.getInstance(arg28).sendMessage(arg30, arg29, arg31, arg33, arg35, v2.caption, v2.entities, null, arg32, v2.ttl);
        }
    }

    static void lambda$null$57(MessageObject arg19, int arg20, TL_photo arg21, HashMap arg22, long arg23, MessageObject arg25, SendingMediaInfo arg26) {
        SendingMediaInfo v0 = arg26;
        if(arg19 != null) {
            SendMessagesHelper.getInstance(arg20).editMessageMedia(arg19, arg21, null, null, null, arg22, false);
        }
        else {
            SendMessagesHelper.getInstance(arg20).sendMessage(arg21, null, arg23, arg25, v0.caption, v0.entities, null, arg22, v0.ttl);
        }
    }

    static void lambda$null$58(int arg12, long arg13) {
        SendMessagesHelper v0 = SendMessagesHelper.getInstance(arg12);
        HashMap v1 = v0.delayedMessages;
        StringBuilder v2 = new StringBuilder();
        v2.append("group_");
        v2.append(arg13);
        Object v13 = v1.get(v2.toString());
        if(v13 != null && !((ArrayList)v13).isEmpty()) {
            v13 = ((ArrayList)v13).get(0);
            Object v14 = ((DelayedMessage)v13).messageObjects.get(((DelayedMessage)v13).messageObjects.size() - 1);
            ((DelayedMessage)v13).finalGroupMessage = ((MessageObject)v14).getId();
            ((MessageObject)v14).messageOwner.params.put("final", "1");
            TL_messages_messages v6 = new TL_messages_messages();
            v6.messages.add(((MessageObject)v14).messageOwner);
            MessagesStorage.getInstance(arg12).putMessages(((messages_Messages)v6), ((DelayedMessage)v13).peer, -2, 0, false);
            v0.sendReadyToSendGroup(((DelayedMessage)v13), true, true);
        }
    }

    public static void lambda$null$6(SendMessagesHelper arg16, Message arg17, int arg18, Peer arg19, ArrayList arg20, long arg21, Message arg23) {
        MessagesStorage.getInstance(arg16.currentAccount).updateMessageStateAndId(arg17.random_id, Integer.valueOf(arg18), arg17.id, 0, false, arg19.channel_id);
        MessagesStorage.getInstance(arg16.currentAccount).putMessages(arg20, true, false, false, 0);
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$MGPt-qFXoHbXBeZf4V8-4UPRTVw(arg16, arg17, arg21, arg18, arg23));
    }

    static void lambda$null$60(Bitmap arg24, String arg25, MessageObject arg26, int arg27, VideoEditedInfo arg28, TL_document arg29, String arg30, HashMap arg31, long arg32, MessageObject arg34, String arg35, ArrayList arg36, int arg37) {
        Bitmap v0 = arg24;
        String v1 = arg25;
        if(v0 != null && v1 != null) {
            ImageLoader.getInstance().putImageToCache(new BitmapDrawable(v0), v1);
        }

        if(arg26 != null) {
            SendMessagesHelper.getInstance(arg27).editMessageMedia(arg26, null, arg28, arg29, arg30, arg31, false);
        }
        else {
            SendMessagesHelper.getInstance(arg27).sendMessage(arg29, arg28, arg30, arg32, arg34, arg35, arg36, null, arg31, arg37);
        }
    }

    public static void lambda$null$7(SendMessagesHelper arg3, TL_error arg4, TL_messages_forwardMessages arg5) {
        AlertsCreator.processError(arg3.currentAccount, arg4, null, ((TLObject)arg5), new Object[0]);
    }

    public static void lambda$null$8(SendMessagesHelper arg5, Message arg6) {
        arg6.send_state = 2;
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(arg6.id)});
        arg5.processSentMessage(arg6.id);
        arg5.removeFromSendingMessages(arg6.id);
    }

    public static void lambda$performSendMessageRequest$39(SendMessagesHelper arg9, TLObject arg10, Message arg11, MessageObject arg12, String arg13, TLObject arg14, TL_error arg15) {
        -$$Lambda$SendMessagesHelper$_VEctUoqz2Ju1DWusW06IpqoYCM v0;
        if((arg10 instanceof TL_messages_editMessage)) {
            v0 = new -$$Lambda$SendMessagesHelper$_VEctUoqz2Ju1DWusW06IpqoYCM(arg9, arg15, arg11, arg14, arg12, arg13, arg10);
        }
        else {
            -$$Lambda$SendMessagesHelper$JzHPMv1zxGjgZi0McML88SKn2E0 v0_1 = new -$$Lambda$SendMessagesHelper$JzHPMv1zxGjgZi0McML88SKn2E0(arg9, arg15, arg11, arg10, arg14, arg12, arg13);
        }

        AndroidUtilities.runOnUIThread(((Runnable)v0));
    }

    public static void lambda$performSendMessageRequest$41(SendMessagesHelper arg2, Message arg3) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$yFlVqhvB4upufAKiSsa85Qgl8Xk(arg2, arg3, arg3.id));
    }

    public static void lambda$performSendMessageRequestMulti$28(SendMessagesHelper arg8, ArrayList arg9, ArrayList arg10, TL_messages_sendMultiMedia arg11, TLObject arg12, TL_error arg13) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$S_Fi0VqcnZwVfugDr5twdLb50uQ(arg8, arg13, arg12, arg9, arg10, arg11));
    }

    static void lambda$prepareSendingAudioDocuments$45(ArrayList arg16, long arg17, int arg19, MessageObject arg20, MessageObject arg21) {
        Document v1_3;
        long v9 = arg17;
        int v11 = arg16.size();
        int v13;
        for(v13 = 0; v13 < v11; ++v13) {
            Object v4 = arg16.get(v13);
            String v0 = ((MessageObject)v4).messageOwner.attachPath;
            File v1 = new File(v0);
            int v3 = 1;
            int v2 = (((int)v9)) == 0 ? 1 : 0;
            if(v0 != null) {
                v0 = v0 + "audio" + v1.length();
            }

            TLObject v1_1 = null;
            if(v2 == 0) {
                MessagesStorage v1_2 = MessagesStorage.getInstance(arg19);
                if(v2 == 0) {
                }
                else {
                    v3 = 4;
                }

                v1_1 = v1_2.getSentFile(v0, v3);
            }

            if(v1_1 == null) {
                v1_3 = ((MessageObject)v4).messageOwner.media.document;
            }

            TLObject v3_1 = ((TLObject)v1_3);
            if(v2 != 0 && MessagesController.getInstance(arg19).getEncryptedChat(Integer.valueOf(((int)(v9 >> 32)))) == null) {
                return;
            }

            HashMap v5_1 = new HashMap();
            if(v0 != null) {
                v5_1.put("originalPath", v0);
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$UjZuu4mGt36dwBa1SyqOFMB9d5A(arg20, arg19, ((TL_document)v3_1), ((MessageObject)v4), v5_1, arg17, arg21));
        }
    }

    static void lambda$prepareSendingBotContextResult$49(BotInlineResult arg17, long arg18, int arg20, HashMap arg21, MessageObject arg22) {
        TL_photo v10;
        String v4;
        TL_document v2;
        TL_game v11;
        Bitmap v0_8;
        File v3_2;
        int v16;
        int v7_2;
        BotInlineResult v8 = arg17;
        HashMap v9 = arg21;
        Uri v1 = null;
        if(!(v8 instanceof TL_botInlineMediaResult)) {
            if(v8.content == null) {
                goto label_424;
            }

            int v3 = 4;
            File v4_1 = FileLoader.getDirectory(v3);
            StringBuilder v7 = new StringBuilder();
            v7.append(Utilities.MD5(v8.content.url));
            v7.append(".");
            v7.append(ImageLoader.getHttpUrlExtension(v8.content.url, "file"));
            File v0_1 = new File(v4_1, v7.toString());
            v4 = v0_1.exists() ? v0_1.getAbsolutePath() : v8.content.url;
            String v7_1 = v8.type;
            int v13 = 2;
            int v14 = -1;
            switch(v7_1.hashCode()) {
                case -1890252483: {
                    if(!v7_1.equals("sticker")) {
                        goto label_127;
                    }

                    v7_2 = 4;
                    break;
                }
                case 102340: {
                    if(!v7_1.equals("gif")) {
                        goto label_127;
                    }

                    v7_2 = 5;
                    break;
                }
                case 3143036: {
                    if(v7_1.equals("file")) {
                        v7_2 = 2;
                        goto label_128;
                    }

                label_127:
                    v7_2 = -1;
                    break;
                }
                case 93166550: {
                    if(!v7_1.equals("audio")) {
                        goto label_127;
                    }

                    v7_2 = 0;
                    break;
                }
                case 106642994: {
                    if(!v7_1.equals("photo")) {
                        goto label_127;
                    }

                    v7_2 = 6;
                    break;
                }
                case 112202875: {
                    if(!v7_1.equals("video")) {
                        goto label_127;
                    }

                    v7_2 = 3;
                    break;
                }
                case 112386354: {
                    if(!v7_1.equals("voice")) {
                        goto label_127;
                    }

                    v7_2 = 1;
                    break;
                }
                default: {
                    goto label_127;
                }
            }

        label_128:
            switch(v7_2) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: {
                    goto label_163;
                }
                case 6: {
                    goto label_132;
                }
            }

            v2 = ((TL_document)v1);
            v10 = ((TL_photo)v2);
            goto label_427;
        label_163:
            TL_document v7_3 = new TL_document();
            v7_3.id = 0;
            v7_3.size = 0;
            v7_3.dc_id = 0;
            v7_3.mime_type = v8.content.mime_type;
            v7_3.date = ConnectionsManager.getInstance(arg20).getCurrentTime();
            TL_documentAttributeFilename v10_2 = new TL_documentAttributeFilename();
            v7_3.attributes.add(v10_2);
            String v0_2 = v8.type;
            switch(v0_2.hashCode()) {
                case -1890252483: {
                    if(v0_2.equals("sticker")) {
                        v16 = 5;
                        goto label_214;
                    }

                label_213:
                    v16 = -1;
                    break;
                }
                case 102340: {
                    if(!v0_2.equals("gif")) {
                        goto label_213;
                    }

                    v16 = 0;
                    break;
                }
                case 3143036: {
                    if(!v0_2.equals("file")) {
                        goto label_213;
                    }

                    v16 = 3;
                    break;
                }
                case 93166550: {
                    if(!v0_2.equals("audio")) {
                        goto label_213;
                    }

                    v16 = 2;
                    break;
                }
                case 112202875: {
                    if(!v0_2.equals("video")) {
                        goto label_213;
                    }

                    v16 = 4;
                    break;
                }
                case 112386354: {
                    if(!v0_2.equals("voice")) {
                        goto label_213;
                    }

                    v16 = 1;
                    break;
                }
                default: {
                    goto label_213;
                }
            }

        label_214:
            float v11_1 = 90f;
            switch(v16) {
                case 0: {
                    goto label_366;
                }
                case 1: {
                    goto label_350;
                }
                case 2: {
                    goto label_327;
                }
                case 3: {
                    goto label_308;
                }
                case 4: {
                    goto label_264;
                }
                case 5: {
                    goto label_217;
                }
            }

            goto label_391;
        label_308:
            int v0_3 = v8.content.mime_type.lastIndexOf(47);
            v0_2 = v0_3 != v14 ? "file." + v8.content.mime_type.substring(v0_3 + 1) : "file";
            v10_2.file_name = v0_2;
            goto label_391;
        label_327:
            TL_documentAttributeAudio v0_4 = new TL_documentAttributeAudio();
            v0_4.duration = MessageObject.getInlineResultDuration(arg17);
            v0_4.title = v8.title;
            ((TL_documentAttributeAudio)v0_2).flags |= 1;
            if(v8.description != null) {
                v0_4.performer = v8.description;
                v0_4.flags |= v13;
            }

            v10_2.file_name = "audio.mp3";
            v7_3.attributes.add(v0_4);
            TL_photoSizeEmpty v0_5 = new TL_photoSizeEmpty();
            goto label_361;
        label_264:
            v10_2.file_name = "video.mp4";
            TL_documentAttributeVideo v0_6 = new TL_documentAttributeVideo();
            int[] v12 = MessageObject.getInlineResultWidthAndHeight(arg17);
            v0_6.w = v12[0];
            v0_6.h = v12[1];
            v0_6.duration = MessageObject.getInlineResultDuration(arg17);
            v0_6.supports_streaming = true;
            v7_3.attributes.add(v0_6);
            try {
                if(v8.thumb == null) {
                    goto label_391;
                }

                v3_2 = FileLoader.getDirectory(v3);
                StringBuilder v12_1 = new StringBuilder();
                v12_1.append(Utilities.MD5(v8.thumb.url));
                v12_1.append(".");
                v12_1.append(ImageLoader.getHttpUrlExtension(v8.thumb.url, "jpg"));
                v0_8 = ImageLoader.loadBitmap(new File(v3_2, v12_1.toString()).getAbsolutePath(), v1, v11_1, v11_1, true);
                if(v0_8 == null) {
                    goto label_391;
                }

                v7_3.thumb = ImageLoader.scaleAndSaveImage(v0_8, v11_1, v11_1, 55, false);
                v0_8.recycle();
                goto label_391;
            }
            catch(Throwable v0_7) {
                goto label_306;
            }

        label_217:
            TL_documentAttributeSticker v12_2 = new TL_documentAttributeSticker();
            v12_2.alt = "";
            v12_2.stickerset = new TL_inputStickerSetEmpty();
            v7_3.attributes.add(v12_2);
            TL_documentAttributeImageSize v12_3 = new TL_documentAttributeImageSize();
            int[] v13_1 = MessageObject.getInlineResultWidthAndHeight(arg17);
            v12_3.w = v13_1[0];
            v12_3.h = v13_1[1];
            v7_3.attributes.add(v12_3);
            v10_2.file_name = "sticker.webp";
            try {
                if(v8.thumb == null) {
                    goto label_391;
                }

                v3_2 = FileLoader.getDirectory(v3);
                StringBuilder v13_2 = new StringBuilder();
                v13_2.append(Utilities.MD5(v8.thumb.url));
                v13_2.append(".");
                v13_2.append(ImageLoader.getHttpUrlExtension(v8.thumb.url, "webp"));
                v0_8 = ImageLoader.loadBitmap(new File(v3_2, v13_2.toString()).getAbsolutePath(), v1, v11_1, v11_1, true);
                if(v0_8 == null) {
                    goto label_391;
                }

                v7_3.thumb = ImageLoader.scaleAndSaveImage(v0_8, v11_1, v11_1, 55, false);
                v0_8.recycle();
                goto label_391;
            }
            catch(Throwable v0_7) {
                goto label_306;
            }

        label_366:
            v10_2.file_name = "animation.gif";
            if(v4.endsWith("mp4")) {
                v7_3.mime_type = "video/mp4";
                v7_3.attributes.add(new TL_documentAttributeAnimated());
            }
            else {
                v7_3.mime_type = "image/gif";
            }

            try {
                v0_8 = v4.endsWith("mp4") ? ThumbnailUtils.createVideoThumbnail(v4, 1) : ImageLoader.loadBitmap(v4, v1, v11_1, v11_1, true);
                if(v0_8 == null) {
                    goto label_391;
                }

                v7_3.thumb = ImageLoader.scaleAndSaveImage(v0_8, v11_1, v11_1, 55, false);
                v0_8.recycle();
            }
            catch(Throwable v0_7) {
            label_306:
                FileLog.e(v0_7);
            }

            goto label_391;
        label_350:
            v0_4 = new TL_documentAttributeAudio();
            v0_4.duration = MessageObject.getInlineResultDuration(arg17);
            v0_4.voice = true;
            v10_2.file_name = "audio.ogg";
            v7_3.attributes.add(v0_4);
            v0_5 = new TL_photoSizeEmpty();
        label_361:
            v7_3.thumb = ((PhotoSize)v0_5);
            v7_3.thumb.type = "s";
        label_391:
            if(v10_2.file_name == null) {
                v10_2.file_name = "file";
            }

            if(v7_3.mime_type == null) {
                v7_3.mime_type = "application/octet-stream";
            }

            if(v7_3.thumb == null) {
                v7_3.thumb = new TL_photoSize();
                int[] v0_9 = MessageObject.getInlineResultWidthAndHeight(arg17);
                v7_3.thumb.w = v0_9[0];
                v7_3.thumb.h = v0_9[1];
                v7_3.thumb.size = 0;
                v7_3.thumb.location = new TL_fileLocationUnavailable();
                v7_3.thumb.type = "x";
            }

            v10 = ((TL_photo)v1);
            v11 = ((TL_game)v10);
            v2 = v7_3;
            goto label_428;
        label_132:
            TL_photo v0_10 = v0_1.exists() ? SendMessagesHelper.getInstance(arg20).generatePhotoSizes(v4, v1) : ((TL_photo)v1);
            if(v0_10 == null) {
                v0_10 = new TL_photo();
                v0_10.date = ConnectionsManager.getInstance(arg20).getCurrentTime();
                TL_photoSize v3_3 = new TL_photoSize();
                int[] v7_4 = MessageObject.getInlineResultWidthAndHeight(arg17);
                v3_3.w = v7_4[0];
                v3_3.h = v7_4[1];
                v3_3.size = 1;
                v3_3.location = new TL_fileLocationUnavailable();
                v3_3.type = "x";
                v0_10.sizes.add(v3_3);
            }

            v10 = v0_10;
            v2 = ((TL_document)v1);
            v11 = ((TL_game)v2);
            goto label_428;
        label_424:
            v2 = ((TL_document)v1);
            v4 = ((String)v2);
        label_426:
            v10 = ((TL_photo)v4);
        }
        else if(!v8.type.equals("game")) {
            if(v8.document != null) {
                if(!(v8.document instanceof TL_document)) {
                    goto label_424;
                }

                Document v2_1 = v8.document;
                v4 = ((String)v1);
                goto label_426;
            }

            if(v8.photo == null) {
                goto label_424;
            }

            if(!(v8.photo instanceof TL_photo)) {
                goto label_424;
            }

            Photo v10_1 = v8.photo;
            v2 = ((TL_document)v1);
            v4 = ((String)v2);
            v11 = ((TL_game)v4);
            goto label_428;
        }
        else if((((int)arg18)) == 0) {
            return;
        }
        else {
            TL_game v0 = new TL_game();
            v0.title = v8.title;
            v0.description = v8.description;
            v0.short_name = v8.id;
            v0.photo = v8.photo;
            if((v8.document instanceof TL_document)) {
                v0.document = v8.document;
                v0.flags |= 1;
            }

            v11 = v0;
            v2 = ((TL_document)v1);
            v4 = ((String)v2);
            v10 = ((TL_photo)v4);
            goto label_428;
        }

    label_427:
        v11 = ((TL_game)v10);
    label_428:
        if(v9 != null && v8.content != null) {
            v9.put("originalPath", v8.content.url);
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$fIGKzyw2eqJXTv-lIBLnX76sGYc(v2, arg20, v4, arg18, arg22, arg17, arg21, v10, v11));
    }

    static void lambda$prepareSendingDocumentInternal$43(MessageObject arg20, int arg21, TL_document arg22, String arg23, HashMap arg24, long arg25, MessageObject arg27, String arg28, ArrayList arg29) {
        if(arg20 != null) {
            SendMessagesHelper.getInstance(arg21).editMessageMedia(arg20, null, null, arg22, arg23, arg24, false);
        }
        else {
            SendMessagesHelper.getInstance(arg21).sendMessage(arg22, null, arg23, arg25, arg27, arg28, arg29, null, arg24, 0);
        }
    }

    static void lambda$prepareSendingDocuments$47(ArrayList arg18, int arg19, ArrayList arg20, String arg21, long arg22, MessageObject arg24, MessageObject arg25, ArrayList arg26, c arg27) {
        int v5;
        ArrayList v0 = arg18;
        ArrayList v1 = arg26;
        int v2 = 0;
        if(v0 != null) {
            int v4 = 0;
            v5 = 0;
            while(v4 < arg18.size()) {
                if(!SendMessagesHelper.prepareSendingDocumentInternal(arg19, v0.get(v4), arg20.get(v4), null, arg21, arg22, arg24, null, null, arg25)) {
                    v5 = 1;
                }

                ++v4;
            }
        }
        else {
            v5 = 0;
        }

        if(v1 != null) {
            while(v2 < arg26.size()) {
                if(!SendMessagesHelper.prepareSendingDocumentInternal(arg19, null, null, v1.get(v2), arg21, arg22, arg24, null, null, arg25)) {
                    v5 = 1;
                }

                ++v2;
            }
        }

        if(arg27 != null) {
            arg27.d();
        }

        if(v5 != 0) {
            AndroidUtilities.runOnUIThread(-$$Lambda$SendMessagesHelper$X2amZOBUOFDoz7x0910lZES04Qc.INSTANCE);
        }
    }

    static void lambda$prepareSendingMedia$59(ArrayList arg50, long arg51, int arg53, boolean arg54, boolean arg55, MessageObject arg56, MessageObject arg57, c arg58) {
        TL_photo v0_15;
        ArrayList v12_1;
        String v3_6;
        TL_photo v37;
        long v46;
        int v45;
        Bitmap v3_5;
        boolean v44;
        TL_documentAttributeVideo_layer65 v4_5;
        TL_documentAttributeVideo v4_4;
        int v8_4;
        TL_document v9_1;
        boolean v10_4;
        long v8_2;
        boolean v5_5;
        VideoEditedInfo v7_3;
        int v11_2;
        Photo v5_4;
        TL_photo v5_3;
        boolean v6_6;
        File v3_2;
        boolean v0_12;
        TL_photo v6_5;
        Photo v6_4;
        int v3_1;
        String v2_2;
        Photo v0_11;
        long v29;
        int v43;
        HashMap v42;
        int v40;
        boolean v13_2;
        Object v1_5;
        ArrayList v38;
        int v34;
        int v31;
        int v15_1;
        long v13_1;
        boolean v36;
        Uri v14_1;
        String v0_10;
        float v1_4;
        Bitmap v0_9;
        StringBuilder v0_7;
        File v15;
        TL_document v10_2;
        TLObject v0_5;
        int v1_2;
        MessagesStorage v0_4;
        File v1_1;
        int v20;
        HashMap v11_1;
        TLObject v6_1;
        int v5_2;
        String v6;
        Object v3;
        HashMap v0_1;
        int v9;
        ArrayList v1 = arg50;
        long v14 = arg51;
        int v13 = arg53;
        long v16 = System.currentTimeMillis();
        int v12 = arg50.size();
        int v10 = 1;
        boolean v8 = (((int)v14)) == 0 ? true : false;
        if(v8) {
            EncryptedChat v0 = MessagesController.getInstance(arg53).getEncryptedChat(Integer.valueOf(((int)(v14 >> 32))));
            if(v0 != null) {
                v9 = AndroidUtilities.getPeerLayerVersion(v0.layer);
            }
            else {
                goto label_23;
            }
        }
        else {
        label_23:
            v9 = 0;
        }

        int v7 = 73;
        if((v8) && v9 < v7 || ((arg54) || !arg55)) {
            v11_1 = null;
        }
        else {
            v0_1 = new HashMap();
            int v2;
            for(v2 = 0; v2 < v12; ++v2) {
                v3 = v1.get(v2);
                if(((SendingMediaInfo)v3).searchImage == null && !((SendingMediaInfo)v3).isVideo) {
                    String v4 = ((SendingMediaInfo)v3).path;
                    String v5 = ((SendingMediaInfo)v3).path;
                    if(v5 == null && ((SendingMediaInfo)v3).uri != null) {
                        v5 = AndroidUtilities.getPath(((SendingMediaInfo)v3).uri);
                        v4 = ((SendingMediaInfo)v3).uri.toString();
                    }

                    if(v5 != null) {
                        if(v5.endsWith(".gif")) {
                            goto label_119;
                        }
                        else if(v5.endsWith(".webp")) {
                            goto label_119;
                        }
                    }

                    if(v5 == null && ((SendingMediaInfo)v3).uri != null) {
                        if(MediaController.isGif(((SendingMediaInfo)v3).uri)) {
                            goto label_119;
                        }
                        else if(MediaController.isWebp(((SendingMediaInfo)v3).uri)) {
                            goto label_119;
                        }
                    }

                    if(v5 != null) {
                        File v11 = new File(v5);
                        v6 = v4 + v11.length() + "_" + v11.lastModified();
                    }
                    else {
                        v6 = null;
                    }

                    if((v8) || ((SendingMediaInfo)v3).ttl != 0) {
                        v6_1 = null;
                    }
                    else {
                        MessagesStorage v4_1 = MessagesStorage.getInstance(arg53);
                        v5_2 = !v8 ? 0 : 3;
                        v6_1 = v4_1.getSentFile(v6, v5_2);
                        if(v6_1 != null) {
                            goto label_105;
                        }

                        if(((SendingMediaInfo)v3).uri == null) {
                            goto label_105;
                        }

                        v4_1 = MessagesStorage.getInstance(arg53);
                        v5 = AndroidUtilities.getPath(((SendingMediaInfo)v3).uri);
                        int v6_2 = !v8 ? 0 : 3;
                        v6_1 = v4_1.getSentFile(v5, v6_2);
                    }

                label_105:
                    MediaSendPrepareWorker v4_2 = new MediaSendPrepareWorker(null);
                    v0_1.put(v3, v4_2);
                    if(v6_1 != null) {
                        v4_2.photo = ((TL_photo)v6_1);
                        goto label_119;
                    }

                    v4_2.sync = new CountDownLatch(1);
                    SendMessagesHelper.mediaSendThreadPool.execute(new -$$Lambda$SendMessagesHelper$m0Z455auncDILeCxmKRqcZxelFQ(v4_2, v13, ((SendingMediaInfo)v3)));
                }

            label_119:
            }

            v11_1 = v0_1;
        }

        int v0_2 = 0;
        long v2_1 = 0;
        ArrayList v4_3 = null;
        v5_2 = 0;
        ArrayList v22 = null;
        ArrayList v23 = null;
        ArrayList v24 = null;
        String v25 = null;
        long v26 = 0;
        while(v5_2 < v12) {
            Object v6_3 = v1.get(v5_2);
            if(arg55) {
                if(v8) {
                    if(v9 >= 73) {
                    }
                    else {
                        goto label_152;
                    }
                }

                if(v12 <= v10) {
                    goto label_152;
                }

                if(v0_2 % 10 != 0) {
                    goto label_152;
                }

                v2_1 = Utilities.random.nextLong();
                v26 = v2_1;
                v20 = 0;
            }
            else {
            label_152:
                v20 = v0_2;
                long v48 = v2_1;
                v2_1 = v26;
                v26 = v48;
            }

            int v28 = 4;
            if(((SendingMediaInfo)v6_3).searchImage != null) {
                if(((SendingMediaInfo)v6_3).searchImage.type == v10) {
                    HashMap v7_1 = new HashMap();
                    if((((SendingMediaInfo)v6_3).searchImage.document instanceof TL_document)) {
                        Document v0_3 = ((SendingMediaInfo)v6_3).searchImage.document;
                        v1_1 = FileLoader.getPathToAttach(((TLObject)v0_3), ((boolean)v10));
                    }
                    else {
                        if(!v8) {
                            v0_4 = MessagesStorage.getInstance(arg53);
                            String v10_1 = ((SendingMediaInfo)v6_3).searchImage.imageUrl;
                            v1_2 = !v8 ? 1 : 4;
                            v0_5 = v0_4.getSentFile(v10_1, v1_2);
                            if(!(v0_5 instanceof TL_document)) {
                                goto label_185;
                            }
                        }
                        else {
                        label_185:
                            v0_5 = null;
                        }

                        StringBuilder v1_3 = new StringBuilder();
                        v1_3.append(Utilities.MD5(((SendingMediaInfo)v6_3).searchImage.imageUrl));
                        v1_3.append(".");
                        v1_3.append(ImageLoader.getHttpUrlExtension(((SendingMediaInfo)v6_3).searchImage.imageUrl, "jpg"));
                        v1_1 = new File(FileLoader.getDirectory(v28), v1_3.toString());
                        v0_5 = v0_5;
                    }

                    if((((Document)v0_5)) == null) {
                        if(((SendingMediaInfo)v6_3).searchImage.localUrl != null) {
                            v7_1.put("url", ((SendingMediaInfo)v6_3).searchImage.localUrl);
                        }

                        v10_2 = new TL_document();
                        v10_2.id = 0;
                        v10_2.date = ConnectionsManager.getInstance(arg53).getCurrentTime();
                        TL_documentAttributeFilename v0_6 = new TL_documentAttributeFilename();
                        v0_6.file_name = "animation.gif";
                        v10_2.attributes.add(v0_6);
                        v10_2.size = ((SendingMediaInfo)v6_3).searchImage.size;
                        v10_2.dc_id = 0;
                        if(v1_1.toString().endsWith("mp4")) {
                            v10_2.mime_type = "video/mp4";
                            v10_2.attributes.add(new TL_documentAttributeAnimated());
                        }
                        else {
                            v10_2.mime_type = "image/gif";
                        }

                        if(v1_1.exists()) {
                            v15 = v1_1;
                        }
                        else {
                            v1_1 = null;
                            v15 = null;
                        }

                        if(v1_1 == null) {
                            v0_7 = new StringBuilder();
                            v0_7.append(Utilities.MD5(((SendingMediaInfo)v6_3).searchImage.thumbUrl));
                            v0_7.append(".");
                            v0_7.append(ImageLoader.getHttpUrlExtension(((SendingMediaInfo)v6_3).searchImage.thumbUrl, "jpg"));
                            v1_1 = new File(FileLoader.getDirectory(v28), v0_7.toString());
                            if(!v1_1.exists()) {
                                v1_1 = null;
                            }
                        }

                        if(v1_1 != null) {
                            try {
                                if(v1_1.getAbsolutePath().endsWith("mp4")) {
                                    v0_9 = ThumbnailUtils.createVideoThumbnail(v1_1.getAbsolutePath(), 1);
                                    v1_4 = 90f;
                                }
                                else {
                                    v0_10 = v1_1.getAbsolutePath();
                                    v1_4 = 90f;
                                    v14_1 = null;
                                    goto label_286;
                                }

                                goto label_287;
                            }
                            catch(Exception v0_8) {
                                goto label_297;
                            }

                            try {
                            label_286:
                                v0_9 = ImageLoader.loadBitmap(v0_10, v14_1, v1_4, v1_4, true);
                            label_287:
                                if(v0_9 == null) {
                                    goto label_300;
                                }

                                v10_2.thumb = ImageLoader.scaleAndSaveImage(v0_9, v1_4, v1_4, 55, v8);
                                v0_9.recycle();
                                goto label_300;
                            }
                            catch(Exception v0_8) {
                            }

                        label_297:
                            FileLog.e(((Throwable)v0_8));
                        }

                    label_300:
                        if(v10_2.thumb != null) {
                            goto label_330;
                        }

                        v10_2.thumb = new TL_photoSize();
                        v10_2.thumb.w = ((SendingMediaInfo)v6_3).searchImage.width;
                        v10_2.thumb.h = ((SendingMediaInfo)v6_3).searchImage.height;
                        v10_2.thumb.size = 0;
                        v10_2.thumb.location = new TL_fileLocationUnavailable();
                        v10_2.thumb.type = "x";
                    }
                    else {
                        Document v10_3 = ((Document)v0_5);
                        v15 = v1_1;
                    }

                label_330:
                    v0_10 = v15 == null ? ((SendingMediaInfo)v6_3).searchImage.imageUrl : v15.toString();
                    if(((SendingMediaInfo)v6_3).searchImage.imageUrl != null) {
                        v7_1.put("originalPath", ((SendingMediaInfo)v6_3).searchImage.imageUrl);
                    }

                    AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$pyzbL9QzPDKm7g5QRkhs7lp9wLM(arg56, arg53, v10_2, v0_10, v7_1, arg51, arg57, v6_3));
                    v36 = v8;
                    v13_1 = v2_1;
                    v0_2 = v20;
                    v2_1 = v26;
                    v4_3 = v4_3;
                    v15_1 = v5_2;
                    v31 = v9;
                    v11_1 = v11_1;
                    v34 = v12;
                    goto label_1115;
                }

                v14 = v2_1;
                v38 = v4_3;
                int v39 = v5_2;
                v1_5 = v6_3;
                v13_2 = v8;
                v40 = v9;
                v42 = v11_1;
                v43 = v12;
                v29 = 0;
                if((((SendingMediaInfo)v1_5).searchImage.photo instanceof TL_photo)) {
                    v0_11 = ((SendingMediaInfo)v1_5).searchImage.photo;
                    goto label_400;
                }
                else {
                    if(!v13_2 && ((SendingMediaInfo)v1_5).ttl == 0) {
                        v0_4 = MessagesStorage.getInstance(arg53);
                        v2_2 = ((SendingMediaInfo)v1_5).searchImage.imageUrl;
                        v3_1 = !v13_2 ? 0 : 3;
                        v0_5 = v0_4.getSentFile(v2_2, v3_1);
                    label_400:
                        v6_4 = v0_11;
                        goto label_415;
                    }

                    v6_4 = null;
                }

            label_415:
                if(v6_4 == null) {
                    v0_7 = new StringBuilder();
                    v0_7.append(Utilities.MD5(((SendingMediaInfo)v1_5).searchImage.imageUrl));
                    v0_7.append(".");
                    v0_7.append(ImageLoader.getHttpUrlExtension(((SendingMediaInfo)v1_5).searchImage.imageUrl, "jpg"));
                    File v2_3 = new File(FileLoader.getDirectory(v28), v0_7.toString());
                    if(!v2_3.exists() || v2_3.length() == v29) {
                    label_444:
                        v0_12 = true;
                    }
                    else {
                        v6_5 = SendMessagesHelper.getInstance(arg53).generatePhotoSizes(v2_3.toString(), null);
                        if(v6_5 != null) {
                            v0_12 = false;
                        }
                        else {
                            goto label_444;
                        }
                    }

                    if((((Photo)v6_5)) == null) {
                        StringBuilder v2_4 = new StringBuilder();
                        v2_4.append(Utilities.MD5(((SendingMediaInfo)v1_5).searchImage.thumbUrl));
                        v2_4.append(".");
                        v2_4.append(ImageLoader.getHttpUrlExtension(((SendingMediaInfo)v1_5).searchImage.thumbUrl, "jpg"));
                        v3_2 = new File(FileLoader.getDirectory(v28), v2_4.toString());
                        if(v3_2.exists()) {
                            v6_5 = SendMessagesHelper.getInstance(arg53).generatePhotoSizes(v3_2.toString(), null);
                        }

                        if((((Photo)v6_5)) != null) {
                            goto label_494;
                        }

                        TL_photo v2_5 = new TL_photo();
                        v2_5.date = ConnectionsManager.getInstance(arg53).getCurrentTime();
                        TL_photoSize v3_3 = new TL_photoSize();
                        v3_3.w = ((SendingMediaInfo)v1_5).searchImage.width;
                        v3_3.h = ((SendingMediaInfo)v1_5).searchImage.height;
                        v3_3.size = 0;
                        v3_3.location = new TL_fileLocationUnavailable();
                        v3_3.type = "x";
                        v2_5.sizes.add(v3_3);
                        v6_6 = v0_12;
                        v5_3 = v2_5;
                        goto label_499;
                    }

                label_494:
                    v5_4 = ((Photo)v6_5);
                    v6_6 = v0_12;
                }
                else {
                    v5_4 = v6_4;
                    v6_6 = true;
                }

            label_499:
                if((((TL_photo)v5_4)) != null) {
                    HashMap v8_1 = new HashMap();
                    if(((SendingMediaInfo)v1_5).searchImage.imageUrl != null) {
                        v8_1.put("originalPath", ((SendingMediaInfo)v1_5).searchImage.imageUrl);
                    }

                    if(arg55) {
                        v0_2 = v20 + 1;
                        v8_1.put("groupId", "" + v14);
                        if(v0_2 != 10) {
                            v11_2 = v39;
                            if(v11_2 == v43 - 1) {
                            }
                            else {
                                v20 = v0_2;
                                goto label_535;
                            }
                        }
                        else {
                            v11_2 = v39;
                        }

                        v8_1.put("final", "1");
                        v20 = v0_2;
                        v26 = v29;
                    }
                    else {
                        v11_2 = v39;
                    }

                label_535:
                    Object v7_2 = v1_5;
                    v1_2 = v11_2;
                    AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$KsXw_2zaSWowIFroEE4Y4d6dhEE(arg56, arg53, ((TL_photo)v5_4), v6_6, ((SendingMediaInfo)v7_2), v8_1, arg51, arg57));
                }
                else {
                    v1_2 = v39;
                }

                v36 = v13_2;
                v13_1 = v14;
                v0_2 = v20;
                v2_1 = v26;
                v4_3 = v38;
                v31 = v40;
                v11_1 = v42;
                v34 = v43;
                v15_1 = v1_2;
                goto label_1115;
            }

            v14 = v2_1;
            v38 = v4_3;
            v1_5 = v6_3;
            v13_2 = v8;
            v40 = v9;
            v42 = v11_1;
            v43 = v12;
            v29 = 0;
            v11_2 = v5_2;
            if(((SendingMediaInfo)v1_5).isVideo) {
                if(arg54) {
                    v7_3 = null;
                }
                else {
                    VideoEditedInfo v0_13 = ((SendingMediaInfo)v1_5).videoEditedInfo != null ? ((SendingMediaInfo)v1_5).videoEditedInfo : SendMessagesHelper.createCompressionSettings(((SendingMediaInfo)v1_5).path);
                    v7_3 = v0_13;
                }

                if(!arg54) {
                    if(v7_3 == null && !((SendingMediaInfo)v1_5).path.endsWith("mp4")) {
                        goto label_835;
                    }

                    v0_10 = ((SendingMediaInfo)v1_5).path;
                    v2_2 = ((SendingMediaInfo)v1_5).path;
                    v3_2 = new File(v2_2);
                    v2_2 = v2_2 + v3_2.length() + "_" + v3_2.lastModified();
                    if(v7_3 != null) {
                        v5_5 = v7_3.muted;
                        StringBuilder v6_7 = new StringBuilder();
                        v6_7.append(v2_2);
                        v6_7.append(v7_3.estimatedDuration);
                        v6_7.append("_");
                        v6_7.append(v7_3.startTime);
                        v6_7.append("_");
                        v6_7.append(v7_3.endTime);
                        v2_2 = v7_3.muted ? "_m" : "";
                        v6_7.append(v2_2);
                        v2_2 = v6_7.toString();
                        if(v7_3.resultWidth != v7_3.originalWidth) {
                            v2_2 = v2_2 + "_" + v7_3.resultWidth;
                        }

                        if(v7_3.startTime >= v29) {
                            v8_2 = v7_3.startTime;
                            goto label_645;
                        }

                        v8_2 = v29;
                    }
                    else {
                        v8_2 = v29;
                        v5_5 = false;
                    }

                label_645:
                    if((v13_2) || ((SendingMediaInfo)v1_5).ttl != 0) {
                        v6_1 = null;
                    }
                    else {
                        MessagesStorage v6_8 = MessagesStorage.getInstance(arg53);
                        v10 = !v13_2 ? 2 : 5;
                        v6_1 = v6_8.getSentFile(v2_2, v10);
                    }

                    if(v6_1 == null) {
                        Bitmap v6_9 = SendMessagesHelper.createVideoThumbnail(((SendingMediaInfo)v1_5).path, v8_2);
                        if(v6_9 == null) {
                            v10_4 = true;
                            v6_9 = ThumbnailUtils.createVideoThumbnail(((SendingMediaInfo)v1_5).path, 1);
                        }
                        else {
                            v10_4 = true;
                        }

                        PhotoSize v8_3 = ImageLoader.scaleAndSaveImage(v6_9, 90f, 90f, 55, v13_2);
                        if(v6_9 != null && v8_3 != null) {
                            v6_9 = null;
                        }

                        v9_1 = new TL_document();
                        v9_1.thumb = v8_3;
                        if(v9_1.thumb == null) {
                            v9_1.thumb = new TL_photoSizeEmpty();
                            v9_1.thumb.type = "s";
                        }
                        else {
                            v9_1.thumb.type = "s";
                        }

                        v9_1.mime_type = "video/mp4";
                        UserConfig.getInstance(arg53).saveConfig(false);
                        if(v13_2) {
                            v8_4 = v40;
                            if(v8_4 >= 66) {
                                v4_4 = new TL_documentAttributeVideo();
                            }
                            else {
                                v4_5 = new TL_documentAttributeVideo_layer65();
                            }
                        }
                        else {
                            v8_4 = v40;
                            v4_4 = new TL_documentAttributeVideo();
                            v4_4.supports_streaming = v10_4;
                        }

                        v9_1.attributes.add(v4_5);
                        if(v7_3 != null && (v7_3.needConvert())) {
                            if(v7_3.muted) {
                                v9_1.attributes.add(new TL_documentAttributeAnimated());
                                SendMessagesHelper.fillVideoAttribute(((SendingMediaInfo)v1_5).path, ((TL_documentAttributeVideo)v4_5), v7_3);
                                v7_3.originalWidth = ((TL_documentAttributeVideo)v4_5).w;
                                v7_3.originalHeight = ((TL_documentAttributeVideo)v4_5).h;
                                ((TL_documentAttributeVideo)v4_5).w = v7_3.resultWidth;
                                ((TL_documentAttributeVideo)v4_5).h = v7_3.resultHeight;
                                v44 = v13_2;
                            }
                            else {
                                v44 = v13_2;
                                ((TL_documentAttributeVideo)v4_5).duration = ((int)(v7_3.estimatedDuration / 1000));
                                if(v7_3.rotationValue == 90 || v7_3.rotationValue == 270) {
                                    ((TL_documentAttributeVideo)v4_5).w = v7_3.resultHeight;
                                    v0_2 = v7_3.resultWidth;
                                }
                                else {
                                    ((TL_documentAttributeVideo)v4_5).w = v7_3.resultWidth;
                                    v0_2 = v7_3.resultHeight;
                                }

                                ((TL_documentAttributeVideo)v4_5).h = v0_2;
                            }

                            v9_1.size = ((int)v7_3.estimatedSize);
                            v0_7 = new StringBuilder();
                            v0_7.append("-2147483648_");
                            v0_7.append(SharedConfig.getLastLocalId());
                            v0_7.append(".mp4");
                            v3_2 = new File(FileLoader.getDirectory(v28), v0_7.toString());
                            SharedConfig.saveConfig();
                            v0_10 = v3_2.getAbsolutePath();
                            v3_5 = v6_9;
                            goto label_784;
                        }

                        v44 = v13_2;
                        if(v3_2.exists()) {
                            v9_1.size = ((int)v3_2.length());
                        }

                        SendMessagesHelper.fillVideoAttribute(((SendingMediaInfo)v1_5).path, ((TL_documentAttributeVideo)v4_5), null);
                        v3_5 = v6_9;
                    }
                    else {
                        v44 = v13_2;
                        v8_4 = v40;
                        TLObject v9_2 = v6_1;
                        v3_5 = null;
                    }

                label_784:
                    HashMap v10_5 = new HashMap();
                    if(v2_2 != null) {
                        v10_5.put("originalPath", v2_2);
                    }

                    if(!v5_5 && (arg55)) {
                        v2 = v20 + 1;
                        v10_5.put("groupId", "" + v14);
                        if(v2 != 10) {
                            if(v11_2 == v43 - 1) {
                            }
                            else {
                                v20 = v2;
                                goto label_812;
                            }
                        }

                        v10_5.put("final", "1");
                        v20 = v2;
                        v26 = v29;
                    }

                label_812:
                    v31 = v8_4;
                    v34 = v43;
                    v45 = v11_2;
                    v36 = v44;
                    v46 = v14;
                    AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$btcy01Yx0GMXbaT4vik9t2XRreI(v3_5, null, arg56, arg53, v7_3, v9_1, v0_10, v10_5, arg51, arg57, v1_5));
                }
                else {
                label_835:
                    v45 = v11_2;
                    v36 = v13_2;
                    v46 = v14;
                    v31 = v40;
                    v34 = v43;
                    SendMessagesHelper.prepareSendingDocumentInternal(arg53, ((SendingMediaInfo)v1_5).path, ((SendingMediaInfo)v1_5).path, null, null, arg51, arg57, ((SendingMediaInfo)v1_5).caption, ((SendingMediaInfo)v1_5).entities, arg56);
                }

                v0_2 = v20;
                v2_1 = v26;
                v4_3 = v38;
            }
            else {
                v45 = v11_2;
                v36 = v13_2;
                v46 = v14;
                v31 = v40;
                v34 = v43;
                v37 = null;
                v0_10 = ((SendingMediaInfo)v1_5).path;
                v2_2 = ((SendingMediaInfo)v1_5).path;
                if(v2_2 == null && ((SendingMediaInfo)v1_5).uri != null) {
                    v2_2 = AndroidUtilities.getPath(((SendingMediaInfo)v1_5).uri);
                    v0_10 = ((SendingMediaInfo)v1_5).uri.toString();
                }

                if(arg54) {
                    v3_6 = FileLoader.getFileExtension(new File(v2_2));
                    goto label_882;
                }
                else {
                    if(v2_2 != null) {
                        if(!v2_2.endsWith(".gif") && !v2_2.endsWith(".webp")) {
                            goto label_897;
                        }

                        if(!v2_2.endsWith(".gif")) {
                            goto label_918;
                        }

                        goto label_908;
                    }
                    else {
                    label_897:
                        if(v2_2 == null && ((SendingMediaInfo)v1_5).uri != null) {
                            if(MediaController.isGif(((SendingMediaInfo)v1_5).uri)) {
                                v0_10 = ((SendingMediaInfo)v1_5).uri.toString();
                                v2_2 = MediaController.copyFileToCache(((SendingMediaInfo)v1_5).uri, "gif");
                            label_908:
                                v3_6 = "gif";
                                goto label_882;
                            }
                            else if(MediaController.isWebp(((SendingMediaInfo)v1_5).uri)) {
                                v0_10 = ((SendingMediaInfo)v1_5).uri.toString();
                                v2_2 = MediaController.copyFileToCache(((SendingMediaInfo)v1_5).uri, "webp");
                                goto label_918;
                            }
                        }

                        goto label_920;
                    }

                label_918:
                    v3_6 = "webp";
                label_882:
                    v25 = v3_6;
                    v3_6 = v0_10;
                    v0_2 = 1;
                    goto label_922;
                label_920:
                    v3_6 = v0_10;
                    v0_2 = 0;
                }

            label_922:
                if(v0_2 == 0) {
                    goto label_950;
                }

                v12_1 = v38;
                if(v12_1 == null) {
                    v4_3 = new ArrayList();
                    v22 = new ArrayList();
                    v23 = new ArrayList();
                    v24 = new ArrayList();
                    v12_1 = v4_3;
                }

                v12_1.add(v2_2);
                v22.add(v3_6);
                v23.add(((SendingMediaInfo)v1_5).caption);
                v24.add(((SendingMediaInfo)v1_5).entities);
                v22 = v22;
                v23 = v23;
                v24 = v24;
                v4_3 = v12_1;
                v0_2 = v20;
                v2_1 = v26;
            }

            v11_1 = v42;
            v15_1 = v45;
            v13_1 = v46;
            goto label_1115;
        label_950:
            v12_1 = v38;
            if(v2_2 != null) {
                File v0_14 = new File(v2_2);
                v6 = v3_6 + v0_14.length() + "_" + v0_14.lastModified();
            }
            else {
                v6 = ((String)v37);
            }

            v11_1 = v42;
            if(v11_1 != null) {
                v3 = v11_1.get(v1_5);
                v0_15 = ((MediaSendPrepareWorker)v3).photo;
                if(v0_15 != null) {
                    goto label_979;
                }

                try {
                    ((MediaSendPrepareWorker)v3).sync.await();
                }
                catch(Exception v0_8) {
                    FileLog.e(((Throwable)v0_8));
                }

                v0_15 = ((MediaSendPrepareWorker)v3).photo;
                goto label_979;
            }

            if((v36) || ((SendingMediaInfo)v1_5).ttl != 0) {
                v0_15 = v37;
            }
            else {
                v0_4 = MessagesStorage.getInstance(arg53);
                v3_1 = !v36 ? 0 : 3;
                v0_5 = v0_4.getSentFile(v6, v3_1);
                if(v0_5 != null) {
                    goto label_1003;
                }

                if(((SendingMediaInfo)v1_5).uri == null) {
                    goto label_1003;
                }

                v0_4 = MessagesStorage.getInstance(arg53);
                v3_6 = AndroidUtilities.getPath(((SendingMediaInfo)v1_5).uri);
                int v4_7 = !v36 ? 0 : 3;
                v0_5 = v0_4.getSentFile(v3_6, v4_7);
            }

        label_1003:
            if(v0_5 == null) {
                v0_15 = SendMessagesHelper.getInstance(arg53).generatePhotoSizes(((SendingMediaInfo)v1_5).path, ((SendingMediaInfo)v1_5).uri);
            }

        label_979:
            v5_3 = v0_15;
            if(v5_3 != null) {
                v0_1 = new HashMap();
                boolean v2_6 = ((SendingMediaInfo)v1_5).masks == null || (((SendingMediaInfo)v1_5).masks.isEmpty()) ? false : true;
                v5_3.has_stickers = v2_6;
                if(v2_6) {
                    SerializedData v2_7 = new SerializedData(((SendingMediaInfo)v1_5).masks.size() * 20 + 4);
                    v2_7.writeInt32(((SendingMediaInfo)v1_5).masks.size());
                    for(v3_1 = 0; v3_1 < ((SendingMediaInfo)v1_5).masks.size(); ++v3_1) {
                        ((SendingMediaInfo)v1_5).masks.get(v3_1).serializeToStream(((AbstractSerializedData)v2_7));
                    }

                    v0_1.put("masks", Utilities.bytesToHex(v2_7.toByteArray()));
                    v2_7.cleanup();
                }

                if(v6 != null) {
                    v0_1.put("originalPath", v6);
                }

                if(arg55) {
                    v2 = v20 + 1;
                    v4_6 = new StringBuilder();
                    v4_6.append("");
                    v13_1 = v46;
                    v4_6.append(v13_1);
                    v0_1.put("groupId", v4_6.toString());
                    if(v2 != 10) {
                        v15_1 = v45;
                        if(v15_1 == v34 - 1) {
                        }
                        else {
                            v20 = v2;
                            goto label_1076;
                        }
                    }
                    else {
                        v15_1 = v45;
                    }

                    v0_1.put("final", "1");
                    v20 = v2;
                    v26 = v29;
                }
                else {
                    v15_1 = v45;
                    v13_1 = v46;
                }

            label_1076:
                AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$PSxcRQwoLdCLt6n3OfpykwY_9LY(arg56, arg53, v5_3, v0_1, arg51, arg57, v1_5));
            }
            else {
                v15_1 = v45;
                v13_1 = v46;
                if(v12_1 == null) {
                    v4_3 = new ArrayList();
                    v22 = new ArrayList();
                    v23 = new ArrayList();
                    v24 = new ArrayList();
                    v12_1 = v4_3;
                }

                v12_1.add(v2_2);
                v22.add(v6);
                v23.add(((SendingMediaInfo)v1_5).caption);
                v24.add(((SendingMediaInfo)v1_5).entities);
                v22 = v22;
                v23 = v23;
                v24 = v24;
            }

            v4_3 = v12_1;
            v0_2 = v20;
            v2_1 = v26;
        label_1115:
            v5_2 = v15_1 + 1;
            v26 = v13_1;
            v9 = v31;
            v12 = v34;
            v8 = v36;
            v1 = arg50;
            v10 = 1;
        }

        v12_1 = v4_3;
        if(v2_1 != 0) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$HDzhUUoPPqooKS4aLtcACCaFByw(arg53, v2_1));
        }

        if(arg58 != null) {
            arg58.d();
        }

        if(v12_1 != null && !v12_1.isEmpty()) {
            v0_2 = 0;
            while(v0_2 < v12_1.size()) {
                SendMessagesHelper.prepareSendingDocumentInternal(arg53, v12_1.get(v0_2), v22.get(v0_2), null, v25, arg51, arg57, v23.get(v0_2), v24.get(v0_2), arg56);
                ++v0_2;
                v24 = v24;
            }
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("total send time = " + (System.currentTimeMillis() - v16));
        }
    }

    static void lambda$prepareSendingText$52(String arg2, int arg3, long arg4) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$SendMessagesHelper$y8bHJEytFFXgLJSkwvsFDf0WaNI(arg2, arg3, arg4));
    }

    static void lambda$prepareSendingVideo$61(VideoEditedInfo arg24, String arg25, long arg26, long arg28, int arg30, int arg31, int arg32, int arg33, long arg34, CharSequence arg36, MessageObject arg37, MessageObject arg38, ArrayList arg39) {
        Bitmap v1_1;
        TL_document v6_4;
        String v0_2;
        TL_documentAttributeVideo_layer65 v6_3;
        TL_documentAttributeVideo v6_2;
        Object[] v7_3;
        Bitmap v23;
        int v19;
        int v18;
        TLObject v9_2;
        String v7_1;
        String v8;
        String v2 = arg25;
        long v9 = arg26;
        long v0 = arg28;
        int v3 = arg32;
        int v4 = arg33;
        VideoEditedInfo v5 = arg24 != null ? arg24 : SendMessagesHelper.createCompressionSettings(arg25);
        boolean v6 = (((int)v9)) == 0 ? true : false;
        boolean v11 = v5 == null || !v5.roundVideo ? false : true;
        if(v5 != null || (v2.endsWith("mp4")) || (v11)) {
            File v12 = new File(v2);
            String v13_1 = v2 + v12.length() + "_" + v12.lastModified();
            long v14 = 0;
            if(v5 != null) {
                if(!v11) {
                    StringBuilder v7 = new StringBuilder();
                    v7.append(v13_1);
                    v7.append(v0);
                    v7.append("_");
                    v7.append(v5.startTime);
                    v7.append("_");
                    v7.append(v5.endTime);
                    v8 = v5.muted ? "_m" : "";
                    v7.append(v8);
                    v7_1 = v7.toString();
                    if(v5.resultWidth != v5.originalWidth) {
                        v7_1 = v7_1 + "_" + v5.resultWidth;
                    }

                    v13_1 = v7_1;
                }

                if(v5.startTime < v14) {
                    goto label_88;
                }

                v14 = v5.startTime;
            }

        label_88:
            int v7_2 = 2;
            if((v6) || arg30 != 0) {
                v9_2 = null;
            }
            else {
                MessagesStorage v9_1 = MessagesStorage.getInstance(arg31);
                int v10 = !v6 ? 2 : 5;
                v9_2 = v9_1.getSentFile(v13_1, v10);
            }

            if(v9_2 == null) {
                Bitmap v9_3 = SendMessagesHelper.createVideoThumbnail(v2, v14);
                if(v9_3 == null) {
                    v9_3 = ThumbnailUtils.createVideoThumbnail(v2, 1);
                }

                PhotoSize v10_1 = ImageLoader.scaleAndSaveImage(v9_3, 90f, 90f, 55, v6);
                if(v9_3 == null || v10_1 == null) {
                    v23 = v9_3;
                    v8 = null;
                }
                else if(v11) {
                    int v14_1 = 21;
                    if(v6) {
                        v18 = 7;
                        v19 = Build$VERSION.SDK_INT < v14_1 ? 0 : 1;
                        Utilities.blurBitmap(v9_3, v18, v19, v9_3.getWidth(), v9_3.getHeight(), v9_3.getRowBytes());
                        StringBuilder v14_2 = new StringBuilder();
                        v23 = v9_3;
                        v14_2.append(v10_1.location.volume_id);
                        v14_2.append("_");
                        v14_2.append(v10_1.location.local_id);
                        v14_2.append("@%d_%d_b2");
                        v8 = v14_2.toString();
                        v7_3 = new Object[v7_2];
                        v7_3[0] = Integer.valueOf(((int)((((float)AndroidUtilities.roundMessageSize)) / AndroidUtilities.density)));
                        v7_3[1] = Integer.valueOf(((int)((((float)AndroidUtilities.roundMessageSize)) / AndroidUtilities.density)));
                    }
                    else {
                        v23 = v9_3;
                        v18 = 3;
                        v19 = Build$VERSION.SDK_INT < v14_1 ? 0 : 1;
                        Utilities.blurBitmap(v23, v18, v19, v23.getWidth(), v23.getHeight(), v23.getRowBytes());
                        v8 = v10_1.location.volume_id + "_" + v10_1.location.local_id + "@%d_%d_b";
                        v7_3 = new Object[v7_2];
                        v7_3[0] = Integer.valueOf(((int)((((float)AndroidUtilities.roundMessageSize)) / AndroidUtilities.density)));
                        v7_3[1] = Integer.valueOf(((int)((((float)AndroidUtilities.roundMessageSize)) / AndroidUtilities.density)));
                    }

                    v8 = String.format(v8, v7_3);
                }
                else {
                    v8 = null;
                    v23 = null;
                }

                TL_document v7_4 = new TL_document();
                v7_4.thumb = v10_1;
                if(v7_4.thumb == null) {
                    v7_4.thumb = new TL_photoSizeEmpty();
                }

                v7_4.thumb.type = "s";
                v7_4.mime_type = "video/mp4";
                UserConfig.getInstance(arg31).saveConfig(false);
                if(v6) {
                    EncryptedChat v6_1 = MessagesController.getInstance(arg31).getEncryptedChat(Integer.valueOf(((int)(arg26 >> 32))));
                    if(v6_1 == null) {
                        return;
                    }
                    else if(AndroidUtilities.getPeerLayerVersion(v6_1.layer) >= 66) {
                        v6_2 = new TL_documentAttributeVideo();
                    }
                    else {
                        v6_3 = new TL_documentAttributeVideo_layer65();
                    }
                }
                else {
                    v6_2 = new TL_documentAttributeVideo();
                    v6_2.supports_streaming = true;
                }

                ((TL_documentAttributeVideo)v6_3).round_message = v11;
                v7_4.attributes.add(v6_3);
                if(v5 != null && (v5.needConvert())) {
                    if(v5.muted) {
                        v7_4.attributes.add(new TL_documentAttributeAnimated());
                        SendMessagesHelper.fillVideoAttribute(v2, ((TL_documentAttributeVideo)v6_3), v5);
                        v5.originalWidth = ((TL_documentAttributeVideo)v6_3).w;
                        v5.originalHeight = ((TL_documentAttributeVideo)v6_3).h;
                        ((TL_documentAttributeVideo)v6_3).w = v5.resultWidth;
                        ((TL_documentAttributeVideo)v6_3).h = v5.resultHeight;
                    }
                    else {
                        ((TL_documentAttributeVideo)v6_3).duration = ((int)(v0 / 1000));
                        if(v5.rotationValue != 90) {
                            if(v5.rotationValue == 270) {
                            }
                            else {
                                ((TL_documentAttributeVideo)v6_3).w = v4;
                                ((TL_documentAttributeVideo)v6_3).h = v3;
                                goto label_266;
                            }
                        }

                        ((TL_documentAttributeVideo)v6_3).w = v3;
                        ((TL_documentAttributeVideo)v6_3).h = v4;
                    }

                label_266:
                    v0 = arg34;
                    v7_4.size = ((int)v0);
                    StringBuilder v0_1 = new StringBuilder();
                    v0_1.append("-2147483648_");
                    v0_1.append(SharedConfig.getLastLocalId());
                    v0_1.append(".mp4");
                    File v1 = new File(FileLoader.getDirectory(4), v0_1.toString());
                    SharedConfig.saveConfig();
                    v0_2 = v1.getAbsolutePath();
                    v6_4 = v7_4;
                    v2 = v8;
                    v1_1 = v23;
                    v7_1 = v0_2;
                    goto label_325;
                }

                if(v12.exists()) {
                    v7_4.size = ((int)v12.length());
                }

                SendMessagesHelper.fillVideoAttribute(v2, ((TL_documentAttributeVideo)v6_3), null);
                v6_4 = v7_4;
                v1_1 = v23;
                v7_1 = v2;
                v2 = v8;
            }
            else {
                v1_1 = null;
                v7_1 = v2;
                TLObject v6_5 = v9_2;
                v2 = ((String)v1_1);
            }

        label_325:
            HashMap v8_2 = new HashMap();
            v0_2 = arg36 != null ? arg36.toString() : "";
            String v12_1 = v0_2;
            if(v13_1 != null) {
                v8_2.put("originalPath", v13_1);
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$XcOnpfnEsV552xcUIEzn53B_a2I(v1_1, v2, arg37, arg31, v5, v6_4, v7_1, v8_2, arg26, arg38, v12_1, arg39, arg30));
        }
        else {
            SendMessagesHelper.prepareSendingDocumentInternal(arg31, arg25, arg25, null, null, arg26, arg38, arg36, arg39, arg37);
        }
    }

    public static void lambda$processUnsentMessages$42(SendMessagesHelper arg3, ArrayList arg4, ArrayList arg5, ArrayList arg6, ArrayList arg7) {
        MessagesController.getInstance(arg3.currentAccount).putUsers(arg4, true);
        MessagesController.getInstance(arg3.currentAccount).putChats(arg5, true);
        MessagesController.getInstance(arg3.currentAccount).putEncryptedChats(arg6, true);
        int v5;
        for(v5 = 0; v5 < arg7.size(); ++v5) {
            arg3.retrySendMessage(new MessageObject(arg3.currentAccount, arg7.get(v5), false), true);
        }
    }

    public static void lambda$sendCallback$16(SendMessagesHelper arg8, String arg9, boolean arg10, MessageObject arg11, KeyboardButton arg12, ChatActivity arg13, TLObject arg14, TL_error arg15) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$5zZ6Shakv_Fag_4_-ZEueYXewKM(arg8, arg9, arg10, arg14, arg11, arg12, arg13));
    }

    public static void lambda$sendGame$17(SendMessagesHelper arg1, long arg2, TLObject arg4, TL_error arg5) {
        if(arg5 == null) {
            MessagesController.getInstance(arg1.currentAccount).processUpdates(((Updates)arg4), false);
        }

        if(arg2 != 0) {
            MessagesStorage.getInstance(arg1.currentAccount).removePendingTask(arg2);
        }
    }

    public static void lambda$sendMessage$9(SendMessagesHelper arg20, long arg21, boolean arg23, boolean arg24, LongSparseArray arg25, ArrayList arg26, ArrayList arg27, Peer arg28, TL_messages_forwardMessages arg29, TLObject arg30, TL_error arg31) {
        int v2_3;
        int v17;
        Message v3_1;
        Object v0_2;
        boolean v15;
        SendMessagesHelper v9 = arg20;
        ArrayList v10 = arg26;
        ArrayList v11 = arg27;
        TL_error v0 = arg31;
        boolean v12 = false;
        if(v0 == null) {
            SparseLongArray v13 = new SparseLongArray();
            TLObject v14 = arg30;
            int v0_1;
            for(v0_1 = 0; true; ++v0_1) {
                v15 = true;
                if(v0_1 >= ((Updates)v14).updates.size()) {
                    break;
                }

                Object v1 = ((Updates)v14).updates.get(v0_1);
                if((v1 instanceof TL_updateMessageID)) {
                    v13.put(((TL_updateMessageID)v1).id, ((TL_updateMessageID)v1).random_id);
                    ((Updates)v14).updates.remove(v0_1);
                    --v0_1;
                }
            }

            v0_2 = MessagesController.getInstance(v9.currentAccount).dialogs_read_outbox_max.get(Long.valueOf(arg21));
            if(v0_2 == null) {
                Integer v0_3 = Integer.valueOf(MessagesStorage.getInstance(v9.currentAccount).getDialogReadMax(true, arg21));
                MessagesController.getInstance(v9.currentAccount).dialogs_read_outbox_max.put(Long.valueOf(arg21), v0_3);
            }

            Object v16 = v0_2;
            v0_1 = 0;
            int v1_1 = 0;
            while(v0_1 < ((Updates)v14).updates.size()) {
                Object v2 = ((Updates)v14).updates.get(v0_1);
                boolean v3 = v2 instanceof TL_updateNewMessage;
                if((v3) || ((v2 instanceof TL_updateNewChannelMessage))) {
                    ((Updates)v14).updates.remove(v0_1);
                    v17 = v0_1 - 1;
                    v0_1 = -1;
                    if(v3) {
                        v3_1 = ((TL_updateNewMessage)v2).message;
                        MessagesController.getInstance(v9.currentAccount).processNewDifferenceParams(v0_1, ((TL_updateNewMessage)v2).pts, v0_1, ((TL_updateNewMessage)v2).pts_count);
                    }
                    else {
                        v3_1 = ((TL_updateNewChannelMessage)v2).message;
                        MessagesController.getInstance(v9.currentAccount).processNewChannelDifferenceParams(((TL_updateNewChannelMessage)v2).pts, ((TL_updateNewChannelMessage)v2).pts_count, v3_1.to_id.channel_id);
                        if(arg23) {
                            v3_1.flags |= -2147483648;
                        }
                    }

                    Message v8 = v3_1;
                    ImageLoader.saveMessageThumbs(v8);
                    boolean v2_1 = ((Integer)v16).intValue() < v8.id ? true : false;
                    v8.unread = v2_1;
                    if(arg24) {
                        v8.out = v15;
                        v8.unread = v12;
                        v8.media_unread = v12;
                    }

                    long v2_2 = v13.get(v8.id);
                    if(v2_2 == 0) {
                        goto label_137;
                    }

                    v2 = arg25.get(v2_2);
                    if(v2 == null) {
                        goto label_137;
                    }

                    int v3_2 = v10.indexOf(v2);
                    if(v3_2 == v0_1) {
                        goto label_137;
                    }

                    v0_2 = v11.get(v3_2);
                    v10.remove(v3_2);
                    v11.remove(v3_2);
                    v3_2 = ((Message)v2).id;
                    ArrayList v4 = new ArrayList();
                    v4.add(v8);
                    ((Message)v2).id = v8.id;
                    v9.updateMediaPaths(((MessageObject)v0_2), v8, null, v15);
                    MessagesStorage.getInstance(v9.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SendMessagesHelper$lUrJ94YxNDkiev73TI2IYe5flE8(arg20, ((Message)v2), v3_2, arg28, v4, arg21, v8));
                    ++v1_1;
                }
                else {
                    v17 = v0_1;
                }

            label_137:
                v0_1 = v17 + 1;
                v12 = false;
                v15 = true;
            }

            if(!((Updates)v14).updates.isEmpty()) {
                v2_3 = 0;
                MessagesController.getInstance(v9.currentAccount).processUpdates(((Updates)v14), false);
            }
            else {
                v2_3 = 0;
            }

            StatsController.getInstance(v9.currentAccount).incrementSentItemsCount(ConnectionsManager.getCurrentNetworkType(), 1, v1_1);
        }
        else {
            v2_3 = 0;
            AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$l40Sz3n3XBnqm5WNgM6XcCJnSAw(v9, v0, arg29));
        }

        while(v2_3 < arg26.size()) {
            v0_2 = v10.get(v2_3);
            MessagesStorage.getInstance(v9.currentAccount).markMessageAsSendError(((Message)v0_2));
            AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$OpZpi03weztqM5vuYHFqnb91tB4(v9, ((Message)v0_2)));
            ++v2_3;
        }
    }

    public static void lambda$sendNotificationCallback$14(SendMessagesHelper arg9, long arg10, int arg12, byte[] arg13) {
        int v0 = ((int)arg10);
        String v1_1 = arg10 + "_" + arg12 + "_" + Utilities.bytesToHex(arg13) + "_" + 0;
        arg9.waitingForCallback.put(v1_1, Boolean.valueOf(true));
        if(v0 <= 0) {
            int v5 = -v0;
            if(MessagesController.getInstance(arg9.currentAccount).getChat(Integer.valueOf(v5)) == null) {
                Chat v3_1 = MessagesStorage.getInstance(arg9.currentAccount).getChatSync(v5);
                if(v3_1 != null) {
                    MessagesController.getInstance(arg9.currentAccount).putChat(v3_1, true);
                }
            }
        }
        else if(MessagesController.getInstance(arg9.currentAccount).getUser(Integer.valueOf(v0)) == null) {
            User v3 = MessagesStorage.getInstance(arg9.currentAccount).getUserSync(v0);
            if(v3 != null) {
                MessagesController.getInstance(arg9.currentAccount).putUser(v3, true);
            }
        }

        TL_messages_getBotCallbackAnswer v3_2 = new TL_messages_getBotCallbackAnswer();
        v3_2.peer = MessagesController.getInstance(arg9.currentAccount).getInputPeer(v0);
        v3_2.msg_id = arg12;
        v3_2.game = false;
        if(arg13 != null) {
            v3_2.flags |= 1;
            v3_2.data = arg13;
        }

        ConnectionsManager.getInstance(arg9.currentAccount).sendRequest(((TLObject)v3_2), new -$$Lambda$SendMessagesHelper$t77TIdvR5oVS88nA5fB-WrU7owc(arg9, v1_1), 2);
        MessagesController.getInstance(arg9.currentAccount).markDialogAsRead(arg10, arg12, arg12, 0, false, 0, true);
    }

    public static void lambda$stopVideoService$21(SendMessagesHelper arg1, String arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$CFCizp_MclmL6eNViZyH4yOwDgg(arg1, arg2));
    }

    public static void lambda$uploadMultiMedia$19(SendMessagesHelper arg0, InputMedia arg1, DelayedMessage arg2, TLObject arg3, TL_error arg4) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$SGXN1MYJttGp6Mdn1ytEHF-Am3E(arg0, arg3, arg1, arg2));
    }

    private void performSendDelayedMessage(DelayedMessage arg2) {
        this.performSendDelayedMessage(arg2, -1);
    }

    private void performSendDelayedMessage(DelayedMessage arg20, int arg21) {
        String v2_1;
        String v3;
        String v1_1;
        ImageLoader v2;
        Object v2_6;
        String v7_2;
        boolean v15;
        FileLoader v13;
        HashMap v2_5;
        int v3_3;
        boolean v10_1;
        FileLoader v8_1;
        String v9_1;
        InputMedia v2_3;
        Document v3_2;
        SendMessagesHelper v0 = this;
        DelayedMessage v1 = arg20;
        int v4 = 16777216;
        boolean v5 = false;
        boolean v6 = true;
        if(v1.type != 0) {
            int v7 = 33554432;
            InputFile v8 = null;
            int v9 = 4;
            if(v1.type == 1) {
                if(v1.videoEditedInfo != null && (v1.videoEditedInfo.needConvert())) {
                    v2_1 = v1.obj.messageOwner.attachPath;
                    v3_2 = v1.obj.getDocument();
                    if(v2_1 == null) {
                        v2_1 = FileLoader.getDirectory(v9) + "/" + v3_2.id + ".mp4";
                    }

                    v0.putToDelayedMessages(v2_1, v1);
                    MediaController.getInstance().scheduleVideoConvert(v1.obj);
                    return;
                }

                if(v1.videoEditedInfo != null) {
                    if(v1.videoEditedInfo.file != null) {
                        if((v1.sendRequest instanceof TL_messages_sendMedia)) {
                            v2_3 = v1.sendRequest.media;
                        }
                        else if((v1.sendRequest instanceof TL_messages_editMessage)) {
                            v2_3 = v1.sendRequest.media;
                        }
                        else {
                            v2_3 = v1.sendRequest.media;
                        }

                        v2_3.file = v1.videoEditedInfo.file;
                        v1.videoEditedInfo.file = v8;
                    }
                    else {
                        if(v1.videoEditedInfo.encryptedFile == null) {
                            goto label_148;
                        }

                        TLObject v10 = v1.sendEncryptedRequest;
                        ((TL_decryptedMessage)v10).media.size = ((int)v1.videoEditedInfo.estimatedSize);
                        ((TL_decryptedMessage)v10).media.key = v1.videoEditedInfo.key;
                        ((TL_decryptedMessage)v10).media.iv = v1.videoEditedInfo.iv;
                        SecretChatHelper.getInstance(v0.currentAccount).performSendEncryptedRequest(((DecryptedMessage)v10), v1.obj.messageOwner, v1.encryptedChat, v1.videoEditedInfo.encryptedFile, v1.originalPath, v1.obj);
                        v1.videoEditedInfo.encryptedFile = ((InputEncryptedFile)v8);
                        return;
                    }
                }

            label_148:
                if(v1.sendRequest != null) {
                    if((v1.sendRequest instanceof TL_messages_sendMedia)) {
                        v2_3 = v1.sendRequest.media;
                    }
                    else if((v1.sendRequest instanceof TL_messages_editMessage)) {
                        v2_3 = v1.sendRequest.media;
                    }
                    else {
                        v2_3 = v1.sendRequest.media;
                    }

                    if(v2_3.file == null) {
                        v2_1 = v1.obj.messageOwner.attachPath;
                        v3_2 = v1.obj.getDocument();
                        if(v2_1 == null) {
                            v2_1 = FileLoader.getDirectory(v9) + "/" + v3_2.id + ".mp4";
                        }

                        v9_1 = v2_1;
                        v0.putToDelayedMessages(v9_1, v1);
                        if(v1.obj.videoEditedInfo != null && (v1.obj.videoEditedInfo.needConvert())) {
                            v8_1 = FileLoader.getInstance(v0.currentAccount);
                            v10_1 = false;
                            goto label_195;
                        }

                        FileLoader.getInstance(v0.currentAccount).uploadFile(v9_1, false, false, v7);
                        return;
                    }

                    v2_2 = new StringBuilder();
                    goto label_206;
                }
                else {
                    v2_1 = v1.obj.messageOwner.attachPath;
                    v3_2 = v1.obj.getDocument();
                    if(v2_1 == null) {
                        v2_1 = FileLoader.getDirectory(v9) + "/" + v3_2.id + ".mp4";
                    }

                    v9_1 = v2_1;
                    if(v1.sendEncryptedRequest != null && v3_2.dc_id != 0 && !new File(v9_1).exists()) {
                        v0.putToDelayedMessages(FileLoader.getAttachFileName(((TLObject)v3_2)), v1);
                        FileLoader.getInstance(v0.currentAccount).loadFile(v3_2, true, 0);
                        return;
                    }

                    v0.putToDelayedMessages(v9_1, v1);
                    if(v1.obj.videoEditedInfo != null && (v1.obj.videoEditedInfo.needConvert())) {
                        v8_1 = FileLoader.getInstance(v0.currentAccount);
                        v10_1 = true;
                        goto label_195;
                    }

                    goto label_266;
                }

            label_195:
                v8_1.uploadFile(v9_1, v10_1, false, v3_2.size, 33554432);
                return;
            label_266:
                FileLoader.getInstance(v0.currentAccount).uploadFile(v9_1, true, false, v7);
                return;
            }
            else {
                if(v1.type != 2) {
                    goto label_343;
                }

                if(v1.httpLocation != null) {
                    v0.putToDelayedMessages(v1.httpLocation, v1);
                    v2 = ImageLoader.getInstance();
                    v1_1 = v1.httpLocation;
                    v3 = "gif";
                label_14:
                    v2.loadHttpFile(v1_1, v3, v0.currentAccount);
                    return;
                }

                v3_3 = 67108864;
                if(v1.sendRequest == null) {
                    goto label_319;
                }

                if((v1.sendRequest instanceof TL_messages_sendMedia)) {
                    v2_3 = v1.sendRequest.media;
                }
                else if((v1.sendRequest instanceof TL_messages_editMessage)) {
                    v2_3 = v1.sendRequest.media;
                }
                else {
                    v2_3 = v1.sendRequest.media;
                }

                if(v2_3.file == null) {
                    v2_1 = v1.obj.messageOwner.attachPath;
                    v0.putToDelayedMessages(v2_1, v1);
                    FileLoader v4_1 = FileLoader.getInstance(v0.currentAccount);
                    if(v1.sendRequest == null) {
                    }
                    else {
                        v6 = false;
                    }

                    v4_1.uploadFile(v2_1, v6, false, v3_3);
                    return;
                }

                if(v2_3.thumb != null) {
                    return;
                }

                if(v1.location == null) {
                    return;
                }

                v2_2 = new StringBuilder();
            }

        label_206:
            v2_2.append(FileLoader.getDirectory(v9));
            v2_2.append("/");
            v2_2.append(v1.location.volume_id);
            v2_2.append("_");
            v2_2.append(v1.location.local_id);
            v2_2.append(".jpg");
            v2_1 = v2_2.toString();
        label_22:
            v0.putToDelayedMessages(v2_1, v1);
            FileLoader.getInstance(v0.currentAccount).uploadFile(v2_1, false, true, v4);
            return;
        label_319:
            v2_1 = v1.obj.messageOwner.attachPath;
            Document v4_2 = v1.obj.getDocument();
            if(v1.sendEncryptedRequest != null && v4_2.dc_id != 0 && !new File(v2_1).exists()) {
                v0.putToDelayedMessages(FileLoader.getAttachFileName(((TLObject)v4_2)), v1);
                FileLoader.getInstance(v0.currentAccount).loadFile(v4_2, true, 0);
                return;
            }

            v0.putToDelayedMessages(v2_1, v1);
            FileLoader.getInstance(v0.currentAccount).uploadFile(v2_1, true, false, v3_3);
            return;
        label_343:
            if(v1.type == 3) {
                v2_1 = v1.obj.messageOwner.attachPath;
                v0.putToDelayedMessages(v2_1, v1);
                FileLoader v3_4 = FileLoader.getInstance(v0.currentAccount);
                if(v1.sendRequest == null) {
                    v5 = true;
                }

                v3_4.uploadFile(v2_1, v5, true, 50331648);
                return;
            }

            if(v1.type != v9) {
                return;
            }

            boolean v3_5 = arg21 < 0 ? true : false;
            if(v1.location != null || v1.httpLocation != null || (v1.upload) || arg21 >= 0) {
                int v2_4 = arg21 < 0 ? v1.messageObjects.size() - 1 : arg21;
                Object v10_2 = v1.messageObjects.get(v2_4);
                if(((MessageObject)v10_2).getDocument() != null) {
                    if(v1.videoEditedInfo != null) {
                        v2_1 = ((MessageObject)v10_2).messageOwner.attachPath;
                        v4_2 = ((MessageObject)v10_2).getDocument();
                        if(v2_1 == null) {
                            v2_1 = FileLoader.getDirectory(v9) + "/" + v4_2.id + ".mp4";
                        }

                        v0.putToDelayedMessages(v2_1, v1);
                        v1.extraHashMap.put(v10_2, v2_1);
                        HashMap v4_3 = v1.extraHashMap;
                        v4_3.put(v2_1 + "_i", v10_2);
                        if(v1.location != null) {
                            v4_3 = v1.extraHashMap;
                            v4_3.put(v2_1 + "_t", v1.location);
                        }

                        MediaController.getInstance().scheduleVideoConvert(((MessageObject)v10_2));
                    }
                    else {
                        Document v11 = ((MessageObject)v10_2).getDocument();
                        String v12 = ((MessageObject)v10_2).messageOwner.attachPath;
                        if(v12 == null) {
                            v12 = FileLoader.getDirectory(v9) + "/" + v11.id + ".mp4";
                        }

                        String v14 = v12;
                        if(v1.sendRequest != null) {
                            v2_3 = v1.sendRequest.multi_media.get(v2_4).media;
                            if(v2_3.file == null) {
                                v0.putToDelayedMessages(v14, v1);
                                v1.extraHashMap.put(v10_2, v14);
                                v1.extraHashMap.put(v14, v2_3);
                                v2_5 = v1.extraHashMap;
                                v2_5.put(v14 + "_i", v10_2);
                                if(v1.location != null) {
                                    v2_5 = v1.extraHashMap;
                                    v2_5.put(v14 + "_t", v1.location);
                                }

                                if(((MessageObject)v10_2).videoEditedInfo != null && (((MessageObject)v10_2).videoEditedInfo.needConvert())) {
                                    v13 = FileLoader.getInstance(v0.currentAccount);
                                    v15 = false;
                                    goto label_565;
                                }

                                FileLoader.getInstance(v0.currentAccount).uploadFile(v14, false, false, v7);
                            }
                            else {
                                v7_2 = FileLoader.getDirectory(v9) + "/" + v1.location.volume_id + "_" + v1.location.local_id + ".jpg";
                                v0.putToDelayedMessages(v7_2, v1);
                                HashMap v9_2 = v1.extraHashMap;
                                v9_2.put(v7_2 + "_o", v14);
                                v1.extraHashMap.put(v10_2, v7_2);
                                v1.extraHashMap.put(v7_2, v2_3);
                                FileLoader.getInstance(v0.currentAccount).uploadFile(v7_2, false, true, v4);
                            }

                            goto label_574;
                        }
                        else {
                            TLObject v4_5 = v1.sendEncryptedRequest;
                            v0.putToDelayedMessages(v14, v1);
                            v1.extraHashMap.put(v10_2, v14);
                            v1.extraHashMap.put(v14, ((TL_messages_sendEncryptedMultiMedia)v4_5).files.get(v2_4));
                            v2_5 = v1.extraHashMap;
                            v2_5.put(v14 + "_i", v10_2);
                            if(v1.location != null) {
                                v2_5 = v1.extraHashMap;
                                v2_5.put(v14 + "_t", v1.location);
                            }

                            if(((MessageObject)v10_2).videoEditedInfo != null && (((MessageObject)v10_2).videoEditedInfo.needConvert())) {
                                v13 = FileLoader.getInstance(v0.currentAccount);
                                v15 = true;
                                goto label_565;
                            }

                            goto label_571;
                        }

                    label_565:
                        v13.uploadFile(v14, v15, false, v11.size, 33554432);
                        goto label_574;
                    label_571:
                        FileLoader.getInstance(v0.currentAccount).uploadFile(v14, true, false, v7);
                    }

                label_574:
                    v1.videoEditedInfo = ((VideoEditedInfo)v8);
                    goto label_575;
                }
                else {
                    if(v1.httpLocation != null) {
                        v0.putToDelayedMessages(v1.httpLocation, v1);
                        v1.extraHashMap.put(v10_2, v1.httpLocation);
                        v1.extraHashMap.put(v1.httpLocation, v10_2);
                        ImageLoader.getInstance().loadHttpFile(v1.httpLocation, "file", v0.currentAccount);
                        v1.httpLocation = ((String)v8);
                        goto label_635;
                    }

                    if(v1.sendRequest != null) {
                        v2_3 = v1.sendRequest.multi_media.get(v2_4).media;
                    }
                    else {
                        v2_6 = v1.sendEncryptedRequest.files.get(v2_4);
                    }

                    v7_2 = FileLoader.getDirectory(v9) + "/" + v1.location.volume_id + "_" + v1.location.local_id + ".jpg";
                    v0.putToDelayedMessages(v7_2, v1);
                    v1.extraHashMap.put(v7_2, v2_6);
                    v1.extraHashMap.put(v10_2, v7_2);
                    FileLoader v2_7 = FileLoader.getInstance(v0.currentAccount);
                    boolean v9_3 = v1.sendEncryptedRequest != null ? true : false;
                    v2_7.uploadFile(v7_2, v9_3, true, v4);
                label_575:
                    v1.location = ((FileLocation)v8);
                }

            label_635:
                v1.upload = false;
            }
            else if(!v1.messageObjects.isEmpty()) {
                v0.putToSendingMessages(v1.messageObjects.get(v1.messageObjects.size() - 1).messageOwner);
            }

            v0.sendReadyToSendGroup(v1, v3_5, true);
        }
        else if(v1.httpLocation != null) {
            v0.putToDelayedMessages(v1.httpLocation, v1);
            v2 = ImageLoader.getInstance();
            v1_1 = v1.httpLocation;
            v3 = "file";
            goto label_14;
        }
        else if(v1.sendRequest != null) {
            v2_1 = FileLoader.getPathToAttach(v1.location).toString();
            goto label_22;
        }
        else {
            v2_1 = FileLoader.getPathToAttach(v1.location).toString();
            if(v1.sendEncryptedRequest != null && v1.location.dc_id != 0) {
                File v3_1 = new File(v2_1);
                if(!v3_1.exists()) {
                    v2_1 = FileLoader.getPathToAttach(v1.location, true).toString();
                    v3_1 = new File(v2_1);
                }

                if(v3_1.exists()) {
                    goto label_55;
                }

                v0.putToDelayedMessages(FileLoader.getAttachFileName(v1.location), v1);
                FileLoader.getInstance(v0.currentAccount).loadFile(v1.location, "jpg", 0, 0);
                return;
            }

        label_55:
            v0.putToDelayedMessages(v2_1, v1);
            FileLoader.getInstance(v0.currentAccount).uploadFile(v2_1, true, true, v4);
        }
    }

    private void performSendMessageRequest(TLObject arg7, MessageObject arg8, String arg9) {
        this.performSendMessageRequest(arg7, arg8, arg9, null, false);
    }

    private void performSendMessageRequest(TLObject arg9, MessageObject arg10, String arg11, DelayedMessage arg12, boolean arg13) {
        if(!(arg9 instanceof TL_messages_editMessage) && (arg13)) {
            DelayedMessage v13 = this.findMaxDelayedMessageForMessageId(arg10.getId(), arg10.getDialogId());
            if(v13 != null) {
                v13.addDelayedRequest(arg9, arg10, arg11);
                if(arg12 != null && arg12.requests != null) {
                    v13.requests.addAll(arg12.requests);
                }

                return;
            }
        }

        Message v13_1 = arg10.messageOwner;
        this.putToSendingMessages(v13_1);
        ConnectionsManager v6 = ConnectionsManager.getInstance(this.currentAccount);
        -$$Lambda$SendMessagesHelper$Hz_E06bUdMZsW84eZSY_8HIZh4k v7 = new -$$Lambda$SendMessagesHelper$Hz_E06bUdMZsW84eZSY_8HIZh4k(this, arg9, v13_1, arg10, arg11);
        -$$Lambda$SendMessagesHelper$4ObYcq3yVdOxJJ3iJojlDdjE6zw v10 = new -$$Lambda$SendMessagesHelper$4ObYcq3yVdOxJJ3iJojlDdjE6zw(this, v13_1);
        int v11 = (arg9 instanceof TL_messages_sendMessage) ? 128 : 0;
        v13_1.reqId = v6.sendRequest(arg9, ((RequestDelegate)v7), ((QuickAckDelegate)v10), v11 | 68);
        if(arg12 != null) {
            arg12.sendDelayedRequests();
        }
    }

    private void performSendMessageRequestMulti(TL_messages_sendMultiMedia arg3, ArrayList arg4, ArrayList arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.putToSendingMessages(arg4.get(v0).messageOwner);
        }

        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)arg3), new -$$Lambda$SendMessagesHelper$wHAYgN-UjT-E_q4Fq230ufX_yqg(this, arg4, arg5, arg3), null, 68);
    }

    public static void prepareSendingAudioDocuments(ArrayList arg9, long arg10, MessageObject arg12, MessageObject arg13) {
        new Thread(new -$$Lambda$SendMessagesHelper$NqV7rlsY8-Cdm1sa2YoPQCEw_Sk(arg9, arg10, UserConfig.selectedAccount, arg13, arg12)).start();
    }

    public static void prepareSendingBotContextResult(BotInlineResult arg15, HashMap arg16, long arg17, MessageObject arg19) {
        TL_messageMediaGeo v6_3;
        BotInlineResult v1 = arg15;
        if(v1 == null) {
            return;
        }

        int v4 = UserConfig.selectedAccount;
        if((v1.send_message instanceof TL_botInlineMessageMediaAuto)) {
            new Thread(new -$$Lambda$SendMessagesHelper$tnAO7DAKS1rJO7OGQCHMUmpYo8w(arg15, arg17, v4, arg16, arg19)).run();
        }
        else if((v1.send_message instanceof TL_botInlineMessageText)) {
            TL_webPagePending v0 = null;
            if((((int)arg17)) == 0) {
                int v5 = 0;
                while(v5 < v1.send_message.entities.size()) {
                    Object v6 = v1.send_message.entities.get(v5);
                    if((v6 instanceof TL_messageEntityUrl)) {
                        v0 = new TL_webPagePending();
                        ((WebPage)v0).url = v1.send_message.message.substring(((MessageEntity)v6).offset, ((MessageEntity)v6).offset + ((MessageEntity)v6).length);
                    }
                    else {
                        ++v5;
                        continue;
                    }

                    break;
                }
            }

            SendMessagesHelper.getInstance(v4).sendMessage(v1.send_message.message, arg17, arg19, v0, v1.send_message.no_webpage ^ 1, v1.send_message.entities, v1.send_message.reply_markup, arg16);
        }
        else {
            if((v1.send_message instanceof TL_botInlineMessageMediaVenue)) {
                TL_messageMediaVenue v6_1 = new TL_messageMediaVenue();
                v6_1.geo = v1.send_message.geo;
                v6_1.address = v1.send_message.address;
                v6_1.title = v1.send_message.title;
                v6_1.provider = v1.send_message.provider;
                v6_1.venue_id = v1.send_message.venue_id;
                String v0_1 = v1.send_message.venue_type;
                v6_1.venue_id = v0_1;
                v6_1.venue_type = v0_1;
                if(v6_1.venue_type == null) {
                    v6_1.venue_type = "";
                }
            }
            else if(!(v1.send_message instanceof TL_botInlineMessageMediaGeo)) {
                goto label_123;
            }
            else if(v1.send_message.period != 0) {
                TL_messageMediaGeoLive v6_2 = new TL_messageMediaGeoLive();
                v6_2.period = v1.send_message.period;
                v6_2.geo = v1.send_message.geo;
            }
            else {
                v6_3 = new TL_messageMediaGeo();
                v6_3.geo = v1.send_message.geo;
            }

            SendMessagesHelper.getInstance(v4).sendMessage(((MessageMedia)v6_3), arg17, arg19, v1.send_message.reply_markup, arg16);
            return;
        label_123:
            if(!(v1.send_message instanceof TL_botInlineMessageMediaContact)) {
                return;
            }

            TL_user v6_4 = new TL_user();
            ((User)v6_4).phone = v1.send_message.phone_number;
            ((User)v6_4).first_name = v1.send_message.first_name;
            ((User)v6_4).last_name = v1.send_message.last_name;
            SendMessagesHelper.getInstance(v4).sendMessage(((User)v6_4), arg17, arg19, v1.send_message.reply_markup, arg16);
        }
    }

    public static void prepareSendingDocument(String arg9, String arg10, Uri arg11, String arg12, long arg13, MessageObject arg15, c arg16, MessageObject arg17) {
        String v0 = arg9;
        String v1 = arg10;
        Uri v2 = arg11;
        if((v0 == null || v1 == null) && v2 == null) {
            return;
        }

        ArrayList v3 = new ArrayList();
        ArrayList v4 = new ArrayList();
        ArrayList v5 = null;
        if(v2 != null) {
            v5 = new ArrayList();
            v5.add(arg11);
        }

        ArrayList v2_1 = v5;
        if(v0 != null) {
            v3.add(arg9);
            v4.add(arg10);
        }

        SendMessagesHelper.prepareSendingDocuments(v3, v4, v2_1, arg12, arg13, arg15, arg16, arg17);
    }

    private static boolean prepareSendingDocumentInternal(int arg26, String arg27, String arg28, Uri arg29, String arg30, long arg31, MessageObject arg33, CharSequence arg34, ArrayList arg35, MessageObject arg36) {
        int v25;
        TL_document v1_5;
        String v12_3;
        TLObject v1_4;
        StringBuilder v5_2;
        int v1_2;
        int v24;
        TL_documentAttributeAudio v0_5;
        int v23;
        int v13_1;
        String v6_2;
        int v5_1;
        Throwable v1_1;
        MediaMetadataRetriever v12;
        String v13;
        int v6_1;
        String v5;
        MimeTypeMap v21;
        int v0_1;
        String v8;
        int v3_1;
        String v0 = arg27;
        String v1 = arg28;
        Uri v2 = arg29;
        String v3 = arg30;
        if((v0 == null || arg27.length() == 0) && v2 == null) {
            return 0;
        }

        if(v2 != null && (AndroidUtilities.isInternalUri(arg29))) {
            return 0;
        }

        if(v0 != null && (AndroidUtilities.isInternalUri(Uri.fromFile(new File(v0))))) {
            return 0;
        }

        MimeTypeMap v6 = MimeTypeMap.getSingleton();
        if(v2 != null) {
            v0 = v3 != null ? v6.getExtensionFromMimeType(v3) : null;
            if(v0 == null) {
                v0 = "txt";
                v3_1 = 0;
            }
            else {
                v3_1 = 1;
            }

            String v2_1 = MediaController.copyFileToCache(v2, v0);
            if(v2_1 == null) {
                return 0;
            }

            if(v3_1 == 0) {
                v8 = v2_1;
                goto label_42;
            }

            v8 = v2_1;
        }
        else {
            v8 = v0;
        label_42:
            v0 = null;
        }

        File v2_2 = new File(v8);
        if(v2_2.exists()) {
            if(v2_2.length() == 0) {
            }
            else {
                v3_1 = (((int)arg31)) == 0 ? 1 : 0;
                int v14 = v3_1 ^ 1;
                String v15 = v2_2.getName();
                String v16 = "";
                int v7 = -1;
                if(v0 == null) {
                    v0_1 = v8.lastIndexOf(46);
                    if(v0_1 != v7) {
                        v0 = v8.substring(v0_1 + 1);
                        goto label_62;
                    }
                }
                else {
                label_62:
                    v16 = v0;
                }

                String v7_1 = v16.toLowerCase();
                if(!v7_1.equals("mp3")) {
                    if(v7_1.equals("m4a")) {
                    }
                    else {
                        if(!v7_1.equals("opus") && !v7_1.equals("ogg")) {
                            if(v7_1.equals("flac")) {
                            }
                            else {
                                v21 = v6;
                                v5 = null;
                                v6_1 = 0;
                                v13 = null;
                                goto label_194;
                            }
                        }

                        try {
                            v12 = new MediaMetadataRetriever();
                        }
                        catch(Throwable v0_2) {
                            v1_1 = v0_2;
                            v12 = null;
                            goto label_174;
                        }
                        catch(Exception v0_3) {
                            v21 = v6;
                            v5 = null;
                            v6_1 = 0;
                            v12 = null;
                            goto label_163;
                        }

                        try {
                            v12.setDataSource(v2_2.getAbsolutePath());
                            v0 = v12.extractMetadata(9);
                            if(v0 == null) {
                                goto label_127;
                            }

                            goto label_100;
                        }
                        catch(Throwable v0_2) {
                        }
                        catch(Exception v0_3) {
                            v21 = v6;
                            goto label_151;
                        label_100:
                            v21 = v6;
                            try {
                                v5_1 = ((int)Math.ceil(((double)((((float)Long.parseLong(v0))) / 1000f))));
                                v0_1 = 7;
                                goto label_109;
                            }
                            catch(Exception v0_3) {
                            }

                        label_151:
                            v5 = null;
                            v6_1 = 0;
                            goto label_163;
                            try {
                            label_109:
                                v6_2 = v12.extractMetadata(v0_1);
                                v13_1 = 2;
                                goto label_111;
                            }
                            catch(Exception v0_3) {
                                v6_1 = v5_1;
                                v5 = null;
                            }

                        label_163:
                            v13 = null;
                            goto label_164;
                            try {
                            label_111:
                                v0 = v12.extractMetadata(v13_1);
                                v13 = v6_2;
                                v6_1 = v5_1;
                                v5 = v0;
                                goto label_131;
                            }
                            catch(Exception v0_3) {
                                v13 = ((String)v6_1);
                                v6_2 = v5;
                                v5 = null;
                                goto label_164;
                            }

                        label_127:
                            v21 = v6;
                            v5 = null;
                            v6_1 = 0;
                            v13 = null;
                        label_131:
                            if(arg36 == null) {
                                try {
                                    if(v7_1.equals("ogg")) {
                                        if(MediaController.isOpusFile(v2_2.getAbsolutePath()) != 1) {
                                            goto label_142;
                                        }

                                        goto label_138;
                                    }
                                    else {
                                        goto label_142;
                                    }
                                }
                                catch(Throwable v0_2) {
                                label_173:
                                    v1_1 = v0_2;
                                    goto label_174;
                                }
                                catch(Exception v0_3) {
                                    try {
                                    label_164:
                                        FileLog.e(((Throwable)v0_3));
                                        if(v12 != null) {
                                        }
                                        else {
                                            goto label_194;
                                        }
                                    }
                                    catch(Throwable v0_2) {
                                        goto label_173;
                                    }

                                    try {
                                        v12.release();
                                    }
                                    catch(Exception v0_3) {
                                        FileLog.e(v0_3);
                                    }

                                    goto label_194;
                                }
                            }
                            else {
                                goto label_142;
                            }

                            goto label_143;
                        }

                    label_174:
                        if(v12 != null) {
                            try {
                                v12.release();
                            }
                            catch(Exception v0_3) {
                                FileLog.e(v0_3);
                            }

                            goto label_180;
                        }
                        else {
                        label_180:
                            throw v1_1;
                        label_138:
                            v23 = 1;
                            goto label_143;
                        label_142:
                            v23 = 0;
                        }

                        try {
                        label_143:
                            v12.release();
                        }
                        catch(Exception v0_3) {
                            FileLog.e(v0_3);
                        }

                        goto label_195;
                    }
                }

                v21 = v6;
                AudioInfo v0_4 = AudioInfo.getAudioInfo(v2_2);
                if(v0_4 == null || v0_4.getDuration() == 0) {
                    v0 = null;
                    v5 = null;
                }
                else {
                    v5 = v0_4.getArtist();
                    v0 = v0_4.getTitle();
                }

                v13 = v0;
                v6_1 = 0;
            label_194:
                v23 = 0;
            label_195:
                if((((int)v6_2)) != 0) {
                    v0_5 = new TL_documentAttributeAudio();
                    v0_5.duration = ((int)v6_2);
                    v0_5.title = v13;
                    v0_5.performer = v5;
                    if(v0_5.title == null) {
                        v0_5.title = "";
                    }

                    v0_5.flags |= 1;
                    if(v0_5.performer == null) {
                        v0_5.performer = "";
                    }

                    v0_5.flags |= 2;
                    if(v23 == 0) {
                        goto label_221;
                    }

                    v0_5.voice = true;
                }
                else {
                    v0_5 = null;
                }

            label_221:
                if(v1 == null) {
                    v24 = v14;
                label_250:
                    v5 = v1;
                    v1_2 = 0;
                }
                else if(v1.endsWith("attheme")) {
                    v5 = v1;
                    v24 = v14;
                    v1_2 = 1;
                }
                else {
                    if(v0_5 != null) {
                        v5_2 = new StringBuilder();
                        v5_2.append(v1);
                        v5_2.append("audio");
                        v24 = v14;
                        v5_2.append(v2_2.length());
                    }
                    else {
                        v24 = v14;
                        v5_2 = new StringBuilder();
                        v5_2.append(v1);
                        v5_2.append("");
                        v5_2.append(v2_2.length());
                    }

                    v1 = v5_2.toString();
                    goto label_250;
                }

                if(v1_2 != 0 || v3_1 != 0) {
                    v1_4 = null;
                }
                else {
                    MessagesStorage v1_3 = MessagesStorage.getInstance(arg26);
                    v6_1 = 4;
                    int v12_1 = v3_1 == 0 ? 1 : 4;
                    v1_4 = v1_3.getSentFile(v5, v12_1);
                    if(v1_4 != null) {
                        goto label_277;
                    }

                    if(v8.equals(v5)) {
                        goto label_277;
                    }

                    if(v3_1 != 0) {
                        goto label_277;
                    }

                    v1_3 = MessagesStorage.getInstance(arg26);
                    v12_3 = v8 + v2_2.length();
                    if(v3_1 == 0) {
                        v6_1 = 1;
                    }

                    v1_4 = v1_3.getSentFile(v12_3, v6_1);
                }

            label_277:
                if(v1_4 == null) {
                    v1_5 = new TL_document();
                    v1_5.id = 0;
                    v1_5.date = ConnectionsManager.getInstance(arg26).getCurrentTime();
                    TL_documentAttributeFilename v6_3 = new TL_documentAttributeFilename();
                    v6_3.file_name = v15;
                    v1_5.attributes.add(v6_3);
                    v1_5.size = ((int)v2_2.length());
                    v1_5.dc_id = 0;
                    if(v0_5 != null) {
                        v1_5.attributes.add(v0_5);
                    }

                    if(v16.length() != 0) {
                        v0_1 = v7_1.hashCode();
                        if(v0_1 != 109967) {
                            if(v0_1 != 3145576) {
                                if(v0_1 != 3418175) {
                                    if(v0_1 != 3645340) {
                                        goto label_331;
                                    }
                                    else if(v7_1.equals("webp")) {
                                        v25 = 0;
                                    }
                                    else {
                                        goto label_331;
                                    }
                                }
                                else if(v7_1.equals("opus")) {
                                    v25 = 1;
                                }
                                else {
                                    goto label_331;
                                }
                            }
                            else if(v7_1.equals("flac")) {
                                v25 = 3;
                            }
                            else {
                                goto label_331;
                            }
                        }
                        else if(v7_1.equals("ogg")) {
                            v25 = 2;
                        }
                        else {
                        label_331:
                            v25 = -1;
                        }

                        switch(v25) {
                            case 0: {
                                goto label_343;
                            }
                            case 1: {
                                goto label_341;
                            }
                            case 2: {
                                goto label_339;
                            }
                            case 3: {
                                goto label_337;
                            }
                        }

                        v0 = v21.getMimeTypeFromExtension(v7_1);
                        if(v0 == null) {
                            goto label_345;
                        }

                        goto label_346;
                    label_337:
                        v0 = "audio/flac";
                        goto label_346;
                    label_339:
                        v0 = "audio/ogg";
                        goto label_346;
                    label_341:
                        v0 = "audio/opus";
                        goto label_346;
                    label_343:
                        v0 = "image/webp";
                    }
                    else {
                    label_345:
                        v0 = "application/octet-stream";
                    }

                label_346:
                    v1_5.mime_type = v0;
                    if(v1_5.mime_type.equals("image/gif")) {
                        if(arg36 != null) {
                            if(arg36.getGroupIdForUse() == 0) {
                                goto label_355;
                            }

                            goto label_373;
                        }

                        try {
                        label_355:
                            v0 = v2_2.getAbsolutePath();
                            float v2_3 = 90f;
                            Bitmap v0_6 = ImageLoader.loadBitmap(v0, null, v2_3, v2_3, true);
                            if(v0_6 == null) {
                                goto label_373;
                            }

                            v6_3.file_name = "animation.gif";
                            v1_5.attributes.add(new TL_documentAttributeAnimated());
                            v1_5.thumb = ImageLoader.scaleAndSaveImage(v0_6, v2_3, v2_3, 55, ((boolean)v3_1));
                            v0_6.recycle();
                        }
                        catch(Exception v0_3) {
                            FileLog.e(((Throwable)v0_3));
                        }
                    }

                label_373:
                    if((v1_5.mime_type.equals("image/webp")) && v24 != 0 && arg36 == null) {
                        BitmapFactory$Options v2_4 = new BitmapFactory$Options();
                        try {
                            v2_4.inJustDecodeBounds = true;
                            RandomAccessFile v0_7 = new RandomAccessFile(v8, "r");
                            MappedByteBuffer v3_2 = v0_7.getChannel().map(FileChannel$MapMode.READ_ONLY, 0, ((long)v8.length()));
                            Utilities.loadWebpImage(null, ((ByteBuffer)v3_2), ((ByteBuffer)v3_2).limit(), v2_4, true);
                            v0_7.close();
                        }
                        catch(Exception v0_3) {
                            FileLog.e(((Throwable)v0_3));
                        }

                        if(v2_4.outWidth == 0) {
                            goto label_425;
                        }

                        if(v2_4.outHeight == 0) {
                            goto label_425;
                        }

                        v3_1 = 800;
                        if(v2_4.outWidth > v3_1) {
                            goto label_425;
                        }

                        if(v2_4.outHeight > v3_1) {
                            goto label_425;
                        }

                        TL_documentAttributeSticker v0_8 = new TL_documentAttributeSticker();
                        v0_8.alt = "";
                        v0_8.stickerset = new TL_inputStickerSetEmpty();
                        v1_5.attributes.add(v0_8);
                        TL_documentAttributeImageSize v0_9 = new TL_documentAttributeImageSize();
                        v0_9.w = v2_4.outWidth;
                        v0_9.h = v2_4.outHeight;
                        v1_5.attributes.add(v0_9);
                    }

                label_425:
                    if(v1_5.thumb != null) {
                        goto label_433;
                    }

                    v1_5.thumb = new TL_photoSizeEmpty();
                    v1_5.thumb.type = "s";
                }

            label_433:
                TLObject v3_3 = ((TLObject)v1_5);
                v0 = arg34 != null ? arg34.toString() : "";
                v12_3 = v0;
                HashMap v6_4 = new HashMap();
                if(v5 != null) {
                    v6_4.put("originalPath", v5);
                }

                AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$tZNUu9eAmtsHIf9IiaCFIGV2KtM(arg36, arg26, ((TL_document)v3_3), v8, v6_4, arg31, arg33, v12_3, arg35));
                return 1;
            }
        }

        return 0;
    }

    public static void prepareSendingDocuments(ArrayList arg13, ArrayList arg14, ArrayList arg15, String arg16, long arg17, MessageObject arg19, c arg20, MessageObject arg21) {
        if(arg13 == null && arg14 == null && arg15 == null || arg13 != null && arg14 != null && arg13.size() != arg14.size()) {
            return;
        }

        new Thread(new -$$Lambda$SendMessagesHelper$o6KY0OhfMVgu4xHccYtz2SZbo4c(arg13, UserConfig.selectedAccount, arg14, arg16, arg17, arg19, arg21, arg15, arg20)).start();
    }

    public static void prepareSendingMedia(ArrayList arg12, long arg13, MessageObject arg15, c arg16, boolean arg17, boolean arg18, MessageObject arg19) {
        if(arg12.isEmpty()) {
            return;
        }

        SendMessagesHelper.mediaSendQueue.postRunnable(new -$$Lambda$SendMessagesHelper$UYCGMs1FznCviXK5ETy6y_PR41o(arg12, arg13, UserConfig.selectedAccount, arg17, arg18, arg19, arg15, arg16));
    }

    public static void prepareSendingPhoto(String arg11, Uri arg12, long arg13, MessageObject arg15, CharSequence arg16, ArrayList arg17, ArrayList arg18, c arg19, int arg20, MessageObject arg21) {
        ArrayList v0 = arg18;
        SendingMediaInfo v1 = new SendingMediaInfo();
        v1.path = arg11;
        v1.uri = arg12;
        if(arg16 != null) {
            v1.caption = arg16.toString();
        }

        v1.entities = arg17;
        v1.ttl = arg20;
        if(v0 != null && !arg18.isEmpty()) {
            v1.masks = new ArrayList(((Collection)v0));
        }

        ArrayList v3 = new ArrayList();
        v3.add(v1);
        SendMessagesHelper.prepareSendingMedia(v3, arg13, arg15, arg19, false, false, arg21);
    }

    public static void prepareSendingText(String arg3, long arg4) {
        MessagesStorage.getInstance(UserConfig.selectedAccount).getStorageQueue().postRunnable(new -$$Lambda$SendMessagesHelper$DSxe-7hOs4fVfDWT-QrPY2DJq5I(arg3, UserConfig.selectedAccount, arg4));
    }

    public static void prepareSendingVideo(String arg19, long arg20, long arg22, int arg24, int arg25, VideoEditedInfo arg26, long arg27, MessageObject arg29, CharSequence arg30, ArrayList arg31, int arg32, MessageObject arg33) {
        if(arg19 != null) {
            if(arg19.length() == 0) {
            }
            else {
                new Thread(new -$$Lambda$SendMessagesHelper$kf1nqWkrrrL8PaWK9dJ8xeXcjX4(arg26, arg19, arg27, arg22, arg32, UserConfig.selectedAccount, arg25, arg24, arg20, arg30, arg33, arg29, arg31)).start();
            }
        }
    }

    public void processForwardFromMyName(MessageObject arg14, long arg15) {
        ArrayList v1_1;
        ArrayList v7;
        MessageObject v0 = arg14;
        long v4 = arg15;
        if(v0 == null) {
            return;
        }

        if(v0.messageOwner.media == null || ((v0.messageOwner.media instanceof TL_messageMediaEmpty)) || ((v0.messageOwner.media instanceof TL_messageMediaWebPage)) || ((v0.messageOwner.media instanceof TL_messageMediaGame)) || ((v0.messageOwner.media instanceof TL_messageMediaInvoice))) {
            if(v0.messageOwner.message != null) {
                WebPage v2 = null;
                WebPage v6 = (v0.messageOwner.media instanceof TL_messageMediaWebPage) ? v0.messageOwner.media.webpage : v2;
                if(v0.messageOwner.entities == null || (v0.messageOwner.entities.isEmpty())) {
                    v7 = ((ArrayList)v2);
                }
                else {
                    v1_1 = new ArrayList();
                    int v2_1;
                    for(v2_1 = 0; v2_1 < v0.messageOwner.entities.size(); ++v2_1) {
                        Object v3 = v0.messageOwner.entities.get(v2_1);
                        if(((v3 instanceof TL_messageEntityBold)) || ((v3 instanceof TL_messageEntityItalic)) || ((v3 instanceof TL_messageEntityPre)) || ((v3 instanceof TL_messageEntityCode)) || ((v3 instanceof TL_messageEntityTextUrl))) {
                            v1_1.add(v3);
                        }
                    }

                    v7 = v1_1;
                }

                this.sendMessage(v0.messageOwner.message, arg15, v0.replyMessageObject, v6, true, v7, null, null);
                return;
            }

            if((((int)v4)) == 0) {
                return;
            }

            v1_1 = new ArrayList();
        label_202:
            v1_1.add(arg14);
            this.sendMessage(v1_1, v4);
        }
        else if((v0.messageOwner.media.photo instanceof TL_photo)) {
            this.sendMessage(v0.messageOwner.media.photo, null, arg15, v0.replyMessageObject, v0.messageOwner.message, v0.messageOwner.entities, null, null, v0.messageOwner.media.ttl_seconds);
        }
        else if((v0.messageOwner.media.document instanceof TL_document)) {
            this.sendMessage(v0.messageOwner.media.document, null, v0.messageOwner.attachPath, arg15, v0.replyMessageObject, v0.messageOwner.message, v0.messageOwner.entities, null, null, v0.messageOwner.media.ttl_seconds);
        }
        else {
            if(!(v0.messageOwner.media instanceof TL_messageMediaVenue)) {
                if((v0.messageOwner.media instanceof TL_messageMediaGeo)) {
                }
                else if(v0.messageOwner.media.phone_number != null) {
                    TL_userContact_old2 v1 = new TL_userContact_old2();
                    ((User)v1).phone = v0.messageOwner.media.phone_number;
                    ((User)v1).first_name = v0.messageOwner.media.first_name;
                    ((User)v1).last_name = v0.messageOwner.media.last_name;
                    ((User)v1).id = v0.messageOwner.media.user_id;
                    this.sendMessage(((User)v1), arg15, v0.replyMessageObject, null, null);
                    return;
                }
                else if((((int)v4)) != 0) {
                    v1_1 = new ArrayList();
                    goto label_202;
                }
                else {
                    return;
                }
            }

            this.sendMessage(v0.messageOwner.media, arg15, v0.replyMessageObject, null, null);
        }
    }

    public void processForwardFromMyName(MessageObject arg14, long arg15, boolean arg17) {
        ArrayList v1_2;
        MessageObject v0 = arg14;
        long v4 = arg15;
        if(v0 == null) {
            return;
        }

        if(v0.messageOwner.media == null || ((v0.messageOwner.media instanceof TL_messageMediaEmpty)) || ((v0.messageOwner.media instanceof TL_messageMediaWebPage))) {
            if(v0.messageOwner.message != null) {
                WebPage v1_3 = null;
                if((v0.messageOwner.media instanceof TL_messageMediaWebPage)) {
                    v1_3 = v0.messageOwner.media.webpage;
                }

                this.sendMessage(v0.messageOwner.message, arg15, v0.replyMessageObject, v1_3, true, v0.messageOwner.entities, null, null);
                return;
            }

            v1_2 = new ArrayList();
        label_171:
            v1_2.add(arg14);
            this.sendMessage(v1_2, v4);
        }
        else {
            String v1 = "";
            if((v0.messageOwner.media.photo instanceof TL_photo)) {
                if(!TextUtils.isEmpty(v0.caption)) {
                    v0.messageOwner.media.captionLegacy = v0.caption.toString();
                    v1 = v0.caption.toString();
                }

                this.sendMessage(v0.messageOwner.media.photo, null, arg15, v0.replyMessageObject, v1, null, null, null, 0);
            }
            else {
                if((v0.messageOwner.media.document instanceof TL_document)) {
                    v1 = "";
                    if(!TextUtils.isEmpty(v0.caption)) {
                        v0.messageOwner.media.captionLegacy = v0.caption.toString();
                        v1 = v0.caption.toString();
                    }

                    this.sendMessage(v0.messageOwner.media.document, null, v0.messageOwner.attachPath, arg15, v0.replyMessageObject, v1, null, null, null, 0);
                    return;
                }

                if(!(v0.messageOwner.media instanceof TL_messageMediaVenue)) {
                    if((v0.messageOwner.media instanceof TL_messageMediaGeo)) {
                    }
                    else if(v0.messageOwner.media.phone_number != null) {
                        TL_userContact_old2 v1_1 = new TL_userContact_old2();
                        ((User)v1_1).phone = v0.messageOwner.media.phone_number;
                        ((User)v1_1).first_name = v0.messageOwner.media.first_name;
                        ((User)v1_1).last_name = v0.messageOwner.media.last_name;
                        ((User)v1_1).id = v0.messageOwner.media.user_id;
                        this.sendMessage(((User)v1_1), arg15, v0.replyMessageObject, null, null);
                        return;
                    }
                    else {
                        v1_2 = new ArrayList();
                        goto label_171;
                    }
                }

                this.sendMessage(v0.messageOwner.media, arg15, v0.replyMessageObject, null, null);
            }
        }
    }

    protected void processSentMessage(int arg3) {
        int v0 = this.unsentMessages.size();
        this.unsentMessages.remove(arg3);
        if(v0 != 0 && this.unsentMessages.size() == 0) {
            this.checkUnsentMessages();
        }
    }

    protected void processUnsentMessages(ArrayList arg8, ArrayList arg9, ArrayList arg10, ArrayList arg11) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$I2G0YcB9hOXR0FOEqoOuafnlw9U(this, arg9, arg10, arg11, arg8));
    }

    private void putToDelayedMessages(String arg3, DelayedMessage arg4) {
        ArrayList v0_1;
        Object v0 = this.delayedMessages.get(arg3);
        if(v0 == null) {
            v0_1 = new ArrayList();
            this.delayedMessages.put(arg3, v0_1);
        }

        v0_1.add(arg4);
    }

    protected void putToSendingMessages(Message arg3) {
        this.sendingMessages.put(arg3.id, arg3);
    }

    protected Message removeFromSendingMessages(int arg3) {
        Object v0 = this.sendingMessages.get(arg3);
        if(v0 != null) {
            this.sendingMessages.remove(arg3);
        }

        return ((Message)v0);
    }

    public boolean retrySendMessage(MessageObject arg11, boolean arg12) {
        if(arg11.getId() >= 0) {
            if(arg11.isEditing()) {
                this.editMessageMedia(arg11, null, null, null, null, null, true);
            }

            return 0;
        }

        if((arg11.messageOwner.action instanceof TL_messageEncryptedAction)) {
            EncryptedChat v12 = MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(((int)(arg11.getDialogId() >> 32))));
            if(v12 == null) {
                MessagesStorage.getInstance(this.currentAccount).markMessageAsSendError(arg11.messageOwner);
                arg11.messageOwner.send_state = 2;
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(arg11.getId())});
                this.processSentMessage(arg11.getId());
                return 0;
            }

            long v3 = 0;
            if(arg11.messageOwner.random_id == v3) {
                arg11.messageOwner.random_id = this.getNextRandomId();
            }

            if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionSetMessageTTL)) {
                SecretChatHelper.getInstance(this.currentAccount).sendTTLMessage(v12, arg11.messageOwner);
            }
            else {
                ArrayList v1 = null;
                if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionDeleteMessages)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendMessagesDeleteMessage(v12, v1, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionFlushHistory)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendClearHistoryMessage(v12, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionNotifyLayer)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendNotifyLayerMessage(v12, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionReadMessages)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendMessagesReadMessage(v12, v1, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionScreenshotMessages)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendScreenshotMessage(v12, v1, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionTyping)) {
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionResend)) {
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionCommitKey)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendCommitKeyMessage(v12, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionAbortKey)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendAbortKeyMessage(v12, arg11.messageOwner, v3);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionRequestKey)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendRequestKeyMessage(v12, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionAcceptKey)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendAcceptKeyMessage(v12, arg11.messageOwner);
                }
                else if((arg11.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionNoop)) {
                    SecretChatHelper.getInstance(this.currentAccount).sendNoopMessage(v12, arg11.messageOwner);
                }
            }

            return 1;
        }

        if((arg11.messageOwner.action instanceof TL_messageActionScreenshotTaken)) {
            this.sendScreenshotMessage(MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((int)arg11.getDialogId()))), arg11.messageOwner.reply_to_msg_id, arg11.messageOwner);
        }

        if(arg12) {
            this.unsentMessages.put(arg11.getId(), arg11);
        }

        this.sendMessage(arg11);
        return 1;
    }

    private void revertEditingMessageObject(MessageObject arg11) {
        arg11.cancelEditing = true;
        arg11.messageOwner.media = arg11.previousMedia;
        arg11.messageOwner.message = arg11.previousCaption;
        arg11.messageOwner.entities = arg11.previousCaptionEntities;
        arg11.messageOwner.attachPath = arg11.previousAttachPath;
        arg11.messageOwner.send_state = 0;
        arg11.previousMedia = null;
        arg11.previousCaption = null;
        arg11.previousCaptionEntities = null;
        arg11.previousAttachPath = null;
        arg11.videoEditedInfo = null;
        arg11.type = -1;
        arg11.setType();
        arg11.caption = null;
        arg11.generateCaption();
        ArrayList v5 = new ArrayList();
        v5.add(arg11.messageOwner);
        MessagesStorage.getInstance(this.currentAccount).putMessages(v5, false, true, false, 0);
        ArrayList v1 = new ArrayList();
        v1.add(arg11);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(arg11.getDialogId()), v1});
    }

    public void sendCallback(boolean arg17, MessageObject arg18, KeyboardButton arg19, ChatActivity arg20) {
        TL_payments_getPaymentReceipt v0_2;
        boolean v13;
        SendMessagesHelper v7 = this;
        MessageObject v8 = arg18;
        KeyboardButton v9 = arg19;
        if(v8 != null && v9 != null) {
            if(arg20 == null) {
            }
            else {
                boolean v10 = v9 instanceof TL_keyboardButtonGame;
                int v0 = 0;
                int v12 = 2;
                if(v10) {
                    v0 = 1;
                    v13 = false;
                }
                else if((v9 instanceof TL_keyboardButtonBuy)) {
                    v13 = arg17;
                    v0 = 2;
                }
                else {
                    v13 = arg17;
                }

                String v14 = arg18.getDialogId() + "_" + arg18.getId() + "_" + Utilities.bytesToHex(v9.data) + "_" + v0;
                v7.waitingForCallback.put(v14, Boolean.valueOf(true));
                -$$Lambda$SendMessagesHelper$66c143xkThr4CF5RKI4zZlojpiI v15 = new -$$Lambda$SendMessagesHelper$66c143xkThr4CF5RKI4zZlojpiI(this, v14, v13, arg18, arg19, arg20);
                if(v13) {
                    MessagesStorage.getInstance(v7.currentAccount).getBotCache(v14, ((RequestDelegate)v15));
                    return;
                }

                if(!(v9 instanceof TL_keyboardButtonBuy)) {
                    TL_messages_getBotCallbackAnswer v0_3 = new TL_messages_getBotCallbackAnswer();
                    v0_3.peer = MessagesController.getInstance(v7.currentAccount).getInputPeer(((int)arg18.getDialogId()));
                    v0_3.msg_id = arg18.getId();
                    v0_3.game = v10;
                    if(v9.data != null) {
                        v0_3.flags |= 1;
                        v0_3.data = v9.data;
                    }
                }
                else if((v8.messageOwner.media.flags & 4) == 0) {
                    TL_payments_getPaymentForm v0_1 = new TL_payments_getPaymentForm();
                    v0_1.msg_id = arg18.getId();
                }
                else {
                    v0_2 = new TL_payments_getPaymentReceipt();
                    v0_2.msg_id = v8.messageOwner.media.receipt_msg_id;
                }

                ConnectionsManager.getInstance(v7.currentAccount).sendRequest(((TLObject)v0_2), ((RequestDelegate)v15), v12);
            }
        }
    }

    public void sendCurrentLocation(MessageObject arg4, KeyboardButton arg5) {
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                StringBuilder v0 = new StringBuilder();
                v0.append(arg4.getDialogId());
                v0.append("_");
                v0.append(arg4.getId());
                v0.append("_");
                v0.append(Utilities.bytesToHex(arg5.data));
                v0.append("_");
                String v5 = (arg5 instanceof TL_keyboardButtonGame) ? "1" : "0";
                v0.append(v5);
                this.waitingForLocation.put(v0.toString(), arg4);
                this.locationProvider.start();
            }
        }
    }

    public void sendGame(InputPeer arg6, TL_inputMediaGame arg7, long arg8, long arg10) {
        int v10;
        NativeByteBuffer v11;
        if(arg6 != null && arg7 != null) {
            TL_messages_sendMedia v0 = new TL_messages_sendMedia();
            v0.peer = arg6;
            if((v0.peer instanceof TL_inputPeerChannel)) {
                SharedPreferences v1 = MessagesController.getNotificationsSettings(this.currentAccount);
                StringBuilder v2 = new StringBuilder();
                v2.append("silent_");
                v2.append(arg6.channel_id);
                v0.silent = v1.getBoolean(v2.toString(), false);
            }

            long v1_1 = 0;
            long v3 = arg8 != v1_1 ? arg8 : this.getNextRandomId();
            v0.random_id = v3;
            v0.message = "";
            v0.media = ((InputMedia)arg7);
            if(arg10 == v1_1) {
                try {
                    v11 = new NativeByteBuffer(arg6.getObjectSize() + arg7.getObjectSize() + 12);
                    v10 = 3;
                }
                catch(Exception v6) {
                    v11 = ((NativeByteBuffer)v10);
                    goto label_49;
                }

                try {
                    v11.writeInt32(v10);
                    v11.writeInt64(arg8);
                    arg6.serializeToStream(((AbstractSerializedData)v11));
                    arg7.serializeToStream(((AbstractSerializedData)v11));
                    goto label_50;
                }
                catch(Exception v6) {
                }

            label_49:
                FileLog.e(((Throwable)v6));
            label_50:
                arg10 = MessagesStorage.getInstance(this.currentAccount).createPendingTask(v11);
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$SendMessagesHelper$y5J0pDdfbi9jqC3Ft0cps5ryQDM(this, arg10));
        }
    }

    private void sendLocation(Location arg9) {
        TL_messageMediaGeo v7 = new TL_messageMediaGeo();
        v7.geo = new TL_geoPoint();
        v7.geo.lat = AndroidUtilities.fixLocationCoord(arg9.getLatitude());
        v7.geo._long = AndroidUtilities.fixLocationCoord(arg9.getLongitude());
        Iterator v9 = this.waitingForLocation.entrySet().iterator();
        while(v9.hasNext()) {
            Object v4 = v9.next().getValue();
            this.sendMessage(v7, ((MessageObject)v4).getDialogId(), ((MessageObject)v4), null, null);
        }
    }

    public void sendMessage(TL_document arg21, VideoEditedInfo arg22, String arg23, long arg24, MessageObject arg26, String arg27, ArrayList arg28, ReplyMarkup arg29, HashMap arg30, int arg31) {
        this.sendMessage(null, arg27, null, null, arg22, null, arg21, null, arg24, arg23, arg26, null, true, null, arg28, arg29, arg30, arg31);
    }

    public void sendMessage(TL_photo arg21, String arg22, long arg23, MessageObject arg25, String arg26, ArrayList arg27, ReplyMarkup arg28, HashMap arg29, int arg30) {
        this.sendMessage(null, arg26, null, arg21, null, null, null, null, arg23, arg22, arg25, null, true, null, arg27, arg28, arg29, arg30);
    }

    public void sendMessage(TL_game arg21, long arg22, ReplyMarkup arg24, HashMap arg25) {
        this.sendMessage(null, null, null, null, null, null, null, arg21, arg22, null, null, null, true, null, null, arg24, arg25, 0);
    }

    public void sendMessage(String arg21, long arg22, MessageObject arg24, WebPage arg25, boolean arg26, ArrayList arg27, ReplyMarkup arg28, HashMap arg29) {
        this.sendMessage(arg21, null, null, null, null, null, null, null, arg22, null, arg24, arg25, arg26, null, arg27, arg28, arg29, 0);
    }

    public void sendMessage(MessageMedia arg21, long arg22, MessageObject arg24, ReplyMarkup arg25, HashMap arg26) {
        this.sendMessage(null, null, arg21, null, null, null, null, null, arg22, null, arg24, null, true, null, null, arg25, arg26, 0);
    }

    public void sendMessage(User arg21, long arg22, MessageObject arg24, ReplyMarkup arg25, HashMap arg26) {
        this.sendMessage(null, null, null, null, null, arg21, null, null, arg22, null, arg24, null, true, null, null, arg25, arg26, 0);
    }

    private void sendMessage(String arg52, String arg53, MessageMedia arg54, TL_photo arg55, VideoEditedInfo arg56, User arg57, TL_document arg58, TL_game arg59, long arg60, String arg62, MessageObject arg63, WebPage arg64, boolean arg65, MessageObject arg66, ArrayList arg67, ReplyMarkup arg68, HashMap arg69, int arg70) {
        TL_decryptedMessage_layer45 v10_10;
        Integer v2_24;
        ArrayList v4_13;
        SharedPreferences v6_21;
        TL_messages_forwardMessages v3_10;
        TL_messages_sendEncryptedMultiMedia v7_14;
        int v50;
        String v7_12;
        DecryptedMessageMedia v2_21;
        DecryptedMessageMedia v7_11;
        Message v7_10;
        SecretChatHelper v2_20;
        VideoEditedInfo v2_19;
        DelayedMessage v7_8;
        DelayedMessage v2_18;
        TL_inputEncryptedFile v2_17;
        TL_decryptedMessage_layer45 v3_7;
        Object v47;
        long v5_1;
        Object v2_15;
        TL_document v14_5;
        Object v43;
        StringBuilder v9_5;
        TLObject v4_8;
        ArrayList v7_6;
        String v10_6;
        int v9_4;
        long v11_3;
        InputDocument v6_15;
        TL_inputMediaUploadedDocument v2_8;
        HashMap v7_4;
        int v41;
        TL_photo v11_2;
        TL_document v42;
        Object v14_4;
        long v38;
        int v12_2;
        InputPeer v40;
        TL_inputMediaGeoLive v6_13;
        DelayedMessage v3_5;
        Object v3_3;
        MessageObject v37;
        ArrayList v12_1;
        HashMap v36;
        ArrayList v10_3;
        long v14_3;
        ArrayList v35;
        Object v10_2;
        VideoEditedInfo v6_11;
        Object v13_5;
        MessageMedia v34;
        TL_photo v32;
        int v15_2;
        int v14_1;
        User v31;
        MessageObject v10_1;
        long v4_6;
        int v7_3;
        TL_inputStickerSetEmpty v6_9;
        HashMap v26;
        String v6_8;
        Object v6_7;
        String v4_2;
        String v3_2;
        MessageMedia v2_5;
        TL_message v8_4;
        int v19_2;
        TL_webPageUrlPending v13_2;
        TL_message_secret v8_3;
        TL_message v6_5;
        TL_message_secret v6_4;
        Exception v2_1;
        int v3_1;
        Document v19_1;
        TL_userRequest_old2 v7_2;
        Photo v4_1;
        int v22;
        TL_document v19;
        User v7_1;
        InputPeer v25;
        MessageMedia v6_3;
        Message v8_2;
        TL_photo v13_1;
        TL_document v30;
        User v15_1;
        int v11_1;
        String v28;
        WebPage v29;
        int v24;
        Message v6_2;
        int v10;
        int v23;
        EncryptedChat v9_1;
        SendMessagesHelper v1 = this;
        String v2 = arg52;
        MessageMedia v3 = arg54;
        TL_photo v4 = arg55;
        User v6 = arg57;
        long v9 = arg60;
        String v11 = arg62;
        WebPage v13 = arg64;
        MessageObject v14 = arg66;
        ArrayList v15 = arg67;
        HashMap v12 = arg69;
        if(v6 != null && v6.phone == null) {
            return;
        }

        long v16 = 0;
        if(v9 == v16) {
            return;
        }

        String v18 = v2 != null || arg53 != null ? arg53 : "";
        Object v8 = v12 == null || !v12.containsKey("originalPath") ? null : v12.get("originalPath");
        Object v20 = v8;
        int v8_1 = ((int)v9);
        int v6_1 = ((int)(v9 >> 32));
        InputPeer v7 = v8_1 != 0 ? MessagesController.getInstance(v1.currentAccount).getInputPeer(v8_1) : null;
        if(v8_1 == 0) {
            v9_1 = MessagesController.getInstance(v1.currentAccount).getEncryptedChat(Integer.valueOf(v6_1));
            if(v9_1 == null) {
                if(v14 != null) {
                    MessagesStorage.getInstance(v1.currentAccount).markMessageAsSendError(v14.messageOwner);
                    v14.messageOwner.send_state = 2;
                    NotificationCenter.getInstance(v1.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(arg66.getId())});
                    v1.processSentMessage(arg66.getId());
                }

                return;
            }
            else {
                v23 = v6_1;
                goto label_76;
            }
        }
        else {
            if((v7 instanceof TL_inputPeerChannel)) {
                Chat v9_2 = MessagesController.getInstance(v1.currentAccount).getChat(Integer.valueOf(v7.channel_id));
                v10 = v9_2 == null || (v9_2.megagroup) ? 0 : 1;
                v23 = v6_1;
                v9_1 = null;
                goto label_97;
            }

            v23 = v6_1;
            v9_1 = null;
        label_76:
            v10 = 0;
        }

    label_97:
        if(v14 != null) {
            try {
                v6_2 = v14.messageOwner;
            }
            catch(Exception v0) {
                goto label_245;
            }

            try {
                if(arg66.isForwarded()) {
                    v24 = v8_1;
                    v29 = v13;
                    v28 = v18;
                    v11_1 = 4;
                    v15_1 = arg57;
                    v30 = arg58;
                    v13_1 = v4;
                    v8_2 = v6_2;
                    v6_3 = v3;
                }
                else {
                    v24 = v8_1;
                    if(v14.type == 0) {
                        if((v14.messageOwner.media instanceof TL_messageMediaGame)) {
                        }
                        else {
                            v2 = v6_2.message;
                        }

                        v25 = v7;
                        v7_1 = arg57;
                        v19 = arg58;
                        v22 = 0;
                    }
                    else {
                        v25 = v7;
                        if(v14.type == 4) {
                            v3 = v6_2.media;
                            v7_1 = arg57;
                            v19 = arg58;
                            v22 = 1;
                            goto label_218;
                        }

                        if(v14.type == 1) {
                            v4_1 = v6_2.media.photo;
                            v7_1 = arg57;
                            v19 = arg58;
                            v22 = 2;
                            goto label_218;
                        }

                        if(v14.type != 3 && v14.type != 5) {
                            if(arg56 != null) {
                            }
                            else {
                                if(v14.type == 12) {
                                    v7_2 = new TL_userRequest_old2();
                                    ((User)v7_2).phone = v6_2.media.phone_number;
                                    ((User)v7_2).first_name = v6_2.media.first_name;
                                    ((User)v7_2).last_name = v6_2.media.last_name;
                                    ((User)v7_2).restriction_reason = v6_2.media.vcard;
                                    ((User)v7_2).id = v6_2.media.user_id;
                                    v19 = arg58;
                                    v22 = 6;
                                }
                                else {
                                    if(v14.type != 8 && v14.type != 9 && v14.type != 13) {
                                        if(v14.type == 14) {
                                        }
                                        else {
                                            if(v14.type == 2) {
                                                v19_1 = v6_2.media.document;
                                                v7_1 = arg57;
                                                v22 = 8;
                                            }
                                            else {
                                                v7_1 = arg57;
                                                v19 = arg58;
                                                v22 = -1;
                                            }

                                            goto label_218;
                                        }
                                    }

                                    v19_1 = v6_2.media.document;
                                    v7_1 = arg57;
                                    v22 = 7;
                                }

                                goto label_218;
                            }
                        }

                        v19_1 = v6_2.media.document;
                        v7_1 = arg57;
                        v22 = 3;
                    }

                label_218:
                    if(v12 == null) {
                        goto label_230;
                    }
                    else if(v12.containsKey("query_id")) {
                        goto label_222;
                    }
                    else {
                        goto label_230;
                    }
                }

                goto label_110;
            }
            catch(Exception v0) {
                goto label_241;
            }

        label_222:
            v8_2 = v6_2;
            v15_1 = ((User)v7_2);
            v29 = v13;
            v28 = v18;
            v30 = v19;
            v7 = v25;
            v11_1 = 9;
            goto label_237;
        label_230:
            v8_2 = v6_2;
            v15_1 = ((User)v7_2);
            v29 = v13;
            v28 = v18;
            v30 = v19;
            v11_1 = v22;
            v7 = v25;
        label_237:
            v6_3 = v3;
            v13_1 = ((TL_photo)v4_1);
        label_110:
            v3_1 = arg70;
            goto label_642;
        label_241:
            v2_1 = v0;
            v8_2 = v6_2;
            goto label_247;
        }
        else {
            v25 = v7;
            v24 = v8_1;
            if(v2 != null) {
                if(v9_1 != null) {
                    try {
                        v6_4 = new TL_message_secret();
                        goto label_255;
                    label_257:
                        v6_5 = new TL_message();
                    label_255:
                        v8_3 = v6_4;
                        goto label_260;
                    }
                    catch(Exception v0) {
                        goto label_245;
                    }
                }
                else {
                    goto label_257;
                }

                goto label_255;
            label_260:
                if(v9_1 != null) {
                    try {
                        if((v13 instanceof TL_webPagePending)) {
                            if(v13.url != null) {
                                TL_webPageUrlPending v6_6 = new TL_webPageUrlPending();
                                ((WebPage)v6_6).url = v13.url;
                                v13_2 = v6_6;
                            }
                            else {
                                v13 = null;
                            }
                        }

                    label_272:
                        if((((WebPage)v13_2)) == null) {
                            ((Message)v8_3).media = new TL_messageMediaEmpty();
                        }
                        else {
                            ((Message)v8_3).media = new TL_messageMediaWebPage();
                            ((Message)v8_3).media.webpage = ((WebPage)v13_2);
                        }

                        v19_2 = v12 == null || !v12.containsKey("query_id") ? 0 : 9;
                        ((Message)v8_3).message = v2;
                        v6_1 = v19_2;
                        v3_1 = arg70;
                        goto label_587;
                    }
                    catch(Exception v0) {
                        goto label_648;
                    }
                }

                goto label_272;
            }
            else {
                if(v3 != null) {
                    if(v9_1 != null) {
                        try {
                            v6_4 = new TL_message_secret();
                            goto label_298;
                        label_300:
                            v6_5 = new TL_message();
                        label_298:
                            v8_3 = v6_4;
                            goto label_303;
                        }
                        catch(Exception v0) {
                            goto label_245;
                        }
                    }
                    else {
                        goto label_300;
                    }

                    goto label_298;
                    try {
                    label_303:
                        ((Message)v8_3).media = v3;
                        if(v12 != null && (v12.containsKey("query_id"))) {
                            goto label_308;
                        }

                        goto label_312;
                    }
                    catch(Exception v0) {
                        goto label_648;
                    }

                label_308:
                    v3_1 = arg70;
                    goto label_310;
                label_312:
                    v3_1 = arg70;
                    v6_1 = 1;
                    goto label_587;
                }
                else {
                    if(v4 == null) {
                        goto label_376;
                    }

                    if(v9_1 != null) {
                        try {
                            v6_4 = new TL_message_secret();
                            goto label_320;
                        label_322:
                            v6_5 = new TL_message();
                        label_320:
                            v8_3 = v6_4;
                            goto label_325;
                        }
                        catch(Exception v0) {
                            goto label_245;
                        }
                    }
                    else {
                        goto label_322;
                    }

                    goto label_320;
                    try {
                    label_325:
                        ((Message)v8_3).media = new TL_messageMediaPhoto();
                        ((Message)v8_3).media.flags |= 3;
                        if(v15 != null) {
                            ((Message)v8_3).entities = v15;
                        }

                        v6_1 = arg70;
                        if(v6_1 != 0) {
                            ((Message)v8_3).media.ttl_seconds = v6_1;
                            ((Message)v8_3).ttl = v6_1;
                            ((Message)v8_3).media.flags |= 4;
                        }

                        ((Message)v8_3).media.photo = ((Photo)v4);
                        v19_2 = v12 == null || !v12.containsKey("query_id") ? 2 : 9;
                        ((Message)v8_3).attachPath = v11 == null || arg62.length() <= 0 || !v11.startsWith("http") ? FileLoader.getPathToAttach(v4.sizes.get(v4.sizes.size() - 1).location, true).toString() : v11;
                    }
                    catch(Exception v0) {
                        goto label_648;
                    }

                    v3_1 = v6_1;
                    v6_1 = v19_2;
                    goto label_587;
                label_376:
                    TL_game v2_2 = arg59;
                    v6_1 = arg70;
                    if(v2_2 != null) {
                        try {
                            v8_4 = new TL_message();
                        }
                        catch(Exception v0) {
                            goto label_245;
                        }

                        try {
                            ((Message)v8_4).media = new TL_messageMediaGame();
                            ((Message)v8_4).media.game = v2_2;
                            if(v12 != null) {
                                if(!v12.containsKey("query_id")) {
                                    goto label_393;
                                }

                                goto label_390;
                            }
                        }
                        catch(Exception v0) {
                            goto label_648;
                        }

                    label_393:
                        v3_1 = v6_1;
                        v6_1 = -1;
                        goto label_587;
                    }
                    else {
                        v7_1 = arg57;
                        if(v7_1 == null) {
                            goto label_458;
                        }

                        if(v9_1 != null) {
                            try {
                                TL_message_secret v2_3 = new TL_message_secret();
                                goto label_402;
                            label_404:
                                TL_message v2_4 = new TL_message();
                            label_402:
                                v8_3 = v2_3;
                                goto label_407;
                            }
                            catch(Exception v0) {
                                goto label_245;
                            }
                        }
                        else {
                            goto label_404;
                        }

                        goto label_402;
                        try {
                        label_407:
                            ((Message)v8_3).media = new TL_messageMediaContact();
                            ((Message)v8_3).media.phone_number = v7_1.phone;
                            ((Message)v8_3).media.first_name = v7_1.first_name;
                            ((Message)v8_3).media.last_name = v7_1.last_name;
                            ((Message)v8_3).media.user_id = v7_1.id;
                            if(v7_1.restriction_reason == null || !v7_1.restriction_reason.startsWith("BEGIN:VCARD")) {
                                v2_5 = ((Message)v8_3).media;
                                v3_2 = "";
                            }
                            else {
                                v2_5 = ((Message)v8_3).media;
                                v3_2 = v7_1.restriction_reason;
                            }

                            v2_5.vcard = v3_2;
                            if(((Message)v8_3).media.first_name == null) {
                                ((Message)v8_3).media.first_name = "";
                                v7_1.first_name = "";
                            }

                            if(((Message)v8_3).media.last_name == null) {
                                ((Message)v8_3).media.last_name = "";
                                v7_1.last_name = "";
                            }

                            if(v12 != null && (v12.containsKey("query_id"))) {
                                goto label_453;
                            }

                            goto label_454;
                        }
                        catch(Exception v0) {
                            goto label_648;
                        }

                    label_453:
                    }

                label_390:
                    v3_1 = v6_1;
                }

            label_310:
                v6_1 = 9;
                goto label_587;
            label_454:
                v3_1 = v6_1;
                v6_1 = 6;
                goto label_587;
            label_458:
                v3_1 = v6_1;
                TL_document v2_6 = arg58;
                if(v2_6 != null) {
                    if(v9_1 != null) {
                        try {
                            v6_4 = new TL_message_secret();
                            goto label_464;
                        label_466:
                            v6_5 = new TL_message();
                        label_464:
                            v8_3 = v6_4;
                            goto label_469;
                        }
                        catch(Exception v0) {
                        label_245:
                            v2_1 = v0;
                            v8_2 = null;
                            goto label_247;
                        }
                    }
                    else {
                        goto label_466;
                    }

                    goto label_464;
                    try {
                    label_469:
                        ((Message)v8_3).media = new TL_messageMediaDocument();
                        ((Message)v8_3).media.flags |= 3;
                        if(v3_1 != 0) {
                            ((Message)v8_3).media.ttl_seconds = v3_1;
                            ((Message)v8_3).ttl = v3_1;
                            ((Message)v8_3).media.flags |= 4;
                        }

                        ((Message)v8_3).media.document = ((Document)v2_6);
                        if(v12 == null || !v12.containsKey("query_id")) {
                            if(!MessageObject.isVideoDocument(((Document)arg58)) && !MessageObject.isRoundVideoDocument(((Document)arg58))) {
                                if(arg56 != null) {
                                }
                                else {
                                    v19_2 = MessageObject.isVoiceDocument(((Document)arg58)) ? 8 : 7;
                                    goto label_507;
                                }
                            }

                            v19_2 = 3;
                        }
                        else {
                            v19_2 = 9;
                        }

                    label_507:
                        if(arg56 != null) {
                            v4_2 = arg56.getString();
                            if(v12 == null) {
                                v12 = new HashMap();
                            }

                            v12.put("ve", v4_2);
                        }

                        ((Message)v8_3).attachPath = v9_1 == null || v2_6.dc_id <= 0 || (MessageObject.isStickerDocument(((Document)arg58))) ? v11 : FileLoader.getPathToAttach(((TLObject)arg58)).toString();
                        if(v9_1 == null || !MessageObject.isStickerDocument(((Document)arg58))) {
                        label_580:
                            v26 = v12;
                        }
                        else {
                            int v4_3 = 0;
                            while(true) {
                                if(v4_3 < v2_6.attributes.size()) {
                                    v6_7 = v2_6.attributes.get(v4_3);
                                    if((v6_7 instanceof TL_documentAttributeSticker)) {
                                        v2_6.attributes.remove(v4_3);
                                        TL_documentAttributeSticker_layer55 v4_4 = new TL_documentAttributeSticker_layer55();
                                        v2_6.attributes.add(v4_4);
                                        v4_4.alt = ((DocumentAttribute)v6_7).alt;
                                        if(((DocumentAttribute)v6_7).stickerset != null) {
                                            if((((DocumentAttribute)v6_7).stickerset instanceof TL_inputStickerSetShortName)) {
                                                v6_8 = ((DocumentAttribute)v6_7).stickerset.short_name;
                                                v26 = v12;
                                            }
                                            else {
                                                v26 = v12;
                                                v6_8 = DataQuery.getInstance(v1.currentAccount).getStickerSetName(((DocumentAttribute)v6_7).stickerset.id);
                                            }

                                            if(!TextUtils.isEmpty(((CharSequence)v6_8))) {
                                                v4_4.stickerset = new TL_inputStickerSetShortName();
                                                v4_4.stickerset.short_name = v6_8;
                                                break;
                                            }

                                            v6_9 = new TL_inputStickerSetEmpty();
                                        }
                                        else {
                                            v26 = v12;
                                            v6_9 = new TL_inputStickerSetEmpty();
                                        }

                                        v4_4.stickerset = ((InputStickerSet)v6_9);
                                    }
                                    else {
                                        ++v4_3;
                                        continue;
                                    }
                                }
                                else {
                                    goto label_580;
                                }

                                break;
                            }
                        }

                        v6_1 = v19_2;
                        v12 = v26;
                        goto label_586;
                    label_584:
                        v6_1 = -1;
                        v8_2 = null;
                    label_586:
                        v13 = arg64;
                    label_587:
                        if(v15 != null && !arg67.isEmpty()) {
                            ((Message)v8_4).entities = v15;
                            ((Message)v8_4).flags |= 128;
                        }

                        if(v18 != null) {
                            v4_2 = v18;
                            ((Message)v8_4).message = v4_2;
                        }
                        else {
                            goto label_598;
                        }

                        goto label_603;
                    }
                    catch(Exception v0) {
                        goto label_648;
                    }
                }
                else {
                    goto label_584;
                }

                goto label_586;
            }

        label_598:
            v4_2 = v18;
            try {
                if(((Message)v8_4).message == null) {
                    goto label_601;
                }

                goto label_603;
            }
            catch(Exception v0) {
                goto label_2557;
            }

            try {
            label_601:
                ((Message)v8_4).message = "";
            }
            catch(Exception v0) {
                goto label_648;
            }

            try {
            label_603:
                if(((Message)v8_4).attachPath == null) {
                    goto label_605;
                }

                goto label_607;
            }
            catch(Exception v0) {
                goto label_2557;
            }

            try {
            label_605:
                ((Message)v8_4).attachPath = "";
            }
            catch(Exception v0) {
                goto label_648;
            }

            try {
            label_607:
                v7_3 = UserConfig.getInstance(v1.currentAccount).getNewMessageId();
                ((Message)v8_4).id = v7_3;
                ((Message)v8_4).local_id = v7_3;
                ((Message)v8_4).out = true;
                if(v10 != 0) {
                }
                else {
                    goto label_621;
                }
            }
            catch(Exception v0) {
                goto label_2557;
            }

            if(v25 != null) {
                v7 = v25;
                try {
                    ((Message)v8_4).from_id = -v7.channel_id;
                    goto label_629;
                }
                catch(Exception v0) {
                    goto label_648;
                }
            }
            else {
            label_621:
                v7 = v25;
                try {
                    ((Message)v8_4).from_id = UserConfig.getInstance(v1.currentAccount).getClientUserId();
                    ((Message)v8_4).flags |= 256;
                label_629:
                    UserConfig.getInstance(v1.currentAccount).saveConfig(false);
                    v28 = v4_2;
                    v29 = ((WebPage)v13_2);
                    v11_1 = v6_1;
                    v2 = arg52;
                    v6_3 = arg54;
                    v13_1 = arg55;
                    v15_1 = arg57;
                    v30 = arg58;
                label_642:
                    if(((Message)v8_4).random_id == v16) {
                        goto label_644;
                    }

                    goto label_650;
                }
                catch(Exception v0) {
                    goto label_2557;
                }
            }

            goto label_629;
        }

        try {
        label_644:
            ((Message)v8_4).random_id = this.getNextRandomId();
        label_650:
            if(v12 != null && (v12.containsKey("bot"))) {
                if(v9_1 != null) {
                    ((Message)v8_4).via_bot_name = v12.get("bot_name");
                    if(((Message)v8_4).via_bot_name == null) {
                        ((Message)v8_4).via_bot_name = "";
                    }
                }
                else {
                    ((Message)v8_4).via_bot_id = Utilities.parseInt(v12.get("bot")).intValue();
                }

                ((Message)v8_4).flags |= 2048;
            }
        }
        catch(Exception v0) {
            goto label_648;
        }

        try {
            ((Message)v8_4).params = v12;
            if(v14 != null) {
            }
            else {
                goto label_675;
            }
        }
        catch(Exception v0) {
            goto label_2557;
        }

        try {
            if(!v14.resendAsIs) {
                goto label_675;
            }

            goto label_713;
        }
        catch(Exception v0) {
            goto label_648;
        }

        try {
        label_675:
            ((Message)v8_4).date = ConnectionsManager.getInstance(v1.currentAccount).getCurrentTime();
            if((v7 instanceof TL_inputPeerChannel)) {
            }
            else {
                goto label_712;
            }
        }
        catch(Exception v0) {
            goto label_2557;
        }

        if(v10 != 0) {
            try {
                ((Message)v8_4).views = 1;
                ((Message)v8_4).flags |= 1024;
            label_687:
                Chat v4_5 = MessagesController.getInstance(v1.currentAccount).getChat(Integer.valueOf(v7.channel_id));
                if(v4_5 == null) {
                    goto label_713;
                }

                if(v4_5.megagroup) {
                    ((Message)v8_4).flags |= -2147483648;
                    ((Message)v8_4).unread = true;
                    goto label_713;
                }

                ((Message)v8_4).post = true;
                if(!v4_5.signatures) {
                    goto label_713;
                }

                ((Message)v8_4).from_id = UserConfig.getInstance(v1.currentAccount).getClientUserId();
                goto label_713;
            }
            catch(Exception v0) {
                goto label_648;
            }
        }

        goto label_687;
        try {
        label_712:
            ((Message)v8_4).unread = true;
        label_713:
            ((Message)v8_4).flags |= 512;
            v4_6 = arg60;
            ((Message)v8_4).dialog_id = v4_6;
            v10_1 = arg63;
            if(v10_1 != null) {
            }
            else {
                goto label_741;
            }
        }
        catch(Exception v0) {
            goto label_2557;
        }

        if(v9_1 != null) {
            try {
                v31 = v15_1;
                if(v10_1.messageOwner.random_id != v16) {
                    ((Message)v8_4).reply_to_random_id = v10_1.messageOwner.random_id;
                    v14_1 = ((Message)v8_4).flags;
                    v15_2 = 8;
                    goto label_731;
                label_734:
                    v31 = v15_1;
                    goto label_735;
                }
                else {
                label_735:
                    v14_1 = ((Message)v8_4).flags;
                    v15_2 = 8;
                }

            label_731:
                ((Message)v8_4).flags = v14_1 | v15_2;
                ((Message)v8_4).reply_to_msg_id = arg63.getId();
                goto label_742;
            label_741:
                v31 = v15_1;
            label_742:
                ReplyMarkup v14_2 = arg68;
                if(v14_2 != null && v9_1 == null) {
                    ((Message)v8_4).flags |= 64;
                    ((Message)v8_4).reply_markup = v14_2;
                }

                if(v24 != 0) {
                    v14_1 = v23;
                    if(v14_1 != 1) {
                        v34 = v6_3;
                        v32 = v13_1;
                        int v13_4 = v24;
                        ((Message)v8_4).to_id = MessagesController.getInstance(v1.currentAccount).getPeer(v13_4);
                        if(v13_4 <= 0) {
                            goto label_918;
                        }

                        v6 = MessagesController.getInstance(v1.currentAccount).getUser(Integer.valueOf(v13_4));
                        if(v6 == null) {
                            v1.processSentMessage(((Message)v8_4).id);
                            return;
                        }

                        if(!v6.bot) {
                            goto label_918;
                        }

                        ((Message)v8_4).unread = false;
                        goto label_918;
                    }
                    else if(v1.currentChatInfo == null) {
                        MessagesStorage.getInstance(v1.currentAccount).markMessageAsSendError(((Message)v8_4));
                        NotificationCenter.getInstance(v1.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(((Message)v8_4).id)});
                        v1.processSentMessage(((Message)v8_4).id);
                        return;
                    }
                    else {
                        v15 = new ArrayList();
                        v32 = v13_1;
                        Iterator v13_3 = v1.currentChatInfo.participants.participants.iterator();
                        while(v13_3.hasNext()) {
                            Iterator v33 = v13_3;
                            v34 = v6_3;
                            InputUser v6_10 = MessagesController.getInstance(v1.currentAccount).getInputUser(MessagesController.getInstance(v1.currentAccount).getUser(Integer.valueOf(v13_3.next().user_id)));
                            if(v6_10 != null) {
                                v15.add(v6_10);
                            }

                            v13_3 = v33;
                            v6_3 = v34;
                        }

                        v34 = v6_3;
                        ((Message)v8_4).to_id = new TL_peerChat();
                        ((Message)v8_4).to_id.chat_id = v24;
                        v6_1 = 1;
                    }
                }
                else {
                    goto label_827;
                }

                goto label_920;
            }
            catch(Exception v0) {
                goto label_648;
            }
        }
        else {
            goto label_734;
        }

        goto label_731;
    label_827:
        v34 = v6_3;
        v32 = v13_1;
        v14_1 = v23;
        try {
            ((Message)v8_4).to_id = new TL_peerUser();
            if(v9_1.participant_id == UserConfig.getInstance(v1.currentAccount).getClientUserId()) {
            }
            else {
                goto label_842;
            }
        }
        catch(Exception v0) {
            goto label_2557;
        }

        try {
            ((Message)v8_4).to_id.user_id = v9_1.admin_id;
            goto label_845;
        }
        catch(Exception v0) {
            goto label_648;
        }

        try {
        label_842:
            ((Message)v8_4).to_id.user_id = v9_1.participant_id;
        }
        catch(Exception v0) {
            goto label_2557;
        }

    label_845:
        if(v3_1 != 0) {
            try {
                ((Message)v8_4).ttl = v3_1;
            }
            catch(Exception v0) {
                goto label_648;
            }
        }
        else {
            try {
                ((Message)v8_4).ttl = v9_1.ttl;
                if(((Message)v8_4).ttl != 0) {
                    goto label_852;
                }

                goto label_862;
            }
            catch(Exception v0) {
                goto label_2557;
            }

            try {
            label_852:
                if(((Message)v8_4).media != null) {
                    ((Message)v8_4).media.ttl_seconds = ((Message)v8_4).ttl;
                    ((Message)v8_4).media.flags |= 4;
                }
            }
            catch(Exception v0) {
                goto label_648;
            }
        }

        try {
        label_862:
            if(((Message)v8_4).ttl == 0) {
                goto label_918;
            }
        }
        catch(Exception v0) {
            goto label_2557;
        }

        try {
            if(((Message)v8_4).media.document != null) {
                if(MessageObject.isVoiceMessage(((Message)v8_4))) {
                    v6_1 = 0;
                    while(true) {
                        if(v6_1 < ((Message)v8_4).media.document.attributes.size()) {
                            v13_5 = ((Message)v8_4).media.document.attributes.get(v6_1);
                            if((v13_5 instanceof TL_documentAttributeAudio)) {
                                v6_1 = ((DocumentAttribute)v13_5).duration;
                            }
                            else {
                                ++v6_1;
                                continue;
                            }
                        }
                        else {
                            break;
                        }

                        goto label_886;
                    }

                    v6_1 = 0;
                label_886:
                    v6_1 = Math.max(((Message)v8_4).ttl, v6_1 + 1);
                }
                else {
                    if(!MessageObject.isVideoMessage(((Message)v8_4)) && !MessageObject.isRoundVideoMessage(((Message)v8_4))) {
                        goto label_918;
                    }

                    v6_1 = 0;
                    while(true) {
                        if(v6_1 < ((Message)v8_4).media.document.attributes.size()) {
                            v13_5 = ((Message)v8_4).media.document.attributes.get(v6_1);
                            if((v13_5 instanceof TL_documentAttributeVideo)) {
                                v6_1 = ((DocumentAttribute)v13_5).duration;
                            }
                            else {
                                ++v6_1;
                                continue;
                            }
                        }
                        else {
                            break;
                        }

                        goto label_913;
                    }

                    v6_1 = 0;
                label_913:
                    v6_1 = Math.max(((Message)v8_4).ttl, v6_1 + 1);
                }

                ((Message)v8_4).ttl = v6_1;
            }

        label_918:
            v6_1 = 1;
            v15 = null;
        label_920:
            if(v14_1 != v6_1) {
                if(!MessageObject.isVoiceMessage(((Message)v8_4))) {
                    if(MessageObject.isRoundVideoMessage(((Message)v8_4))) {
                    }
                    else {
                        v6_1 = 1;
                        goto label_930;
                    }
                }

                v6_1 = 1;
                ((Message)v8_4).media_unread = true;
                goto label_930;
            label_648:
                v2_1 = v0;
                goto label_247;
            }

            goto label_930;
        }
        catch(Exception v0) {
            goto label_648;
        }

    label_247:
        MessageObject v13_6 = null;
        goto label_2559;
        try {
        label_930:
            ((Message)v8_4).send_state = v6_1;
            v13_6 = new MessageObject(v1.currentAccount, ((Message)v8_4), ((boolean)v6_1));
        }
        catch(Exception v0) {
        label_2557:
            v13_6 = null;
            goto label_2558;
        }

        try {
            v13_6.replyMessageObject = v10_1;
            if(!v13_6.isForwarded() && (v13_6.type == 3 || arg56 != null || v13_6.type == 2) && !TextUtils.isEmpty(((Message)v8_4).attachPath)) {
                v13_6.attachPathExists = true;
            }

            v6_11 = v13_6.videoEditedInfo == null || arg56 != null ? arg56 : v13_6.videoEditedInfo;
            if(v12 != null) {
                v10_2 = v12.get("groupId");
                if(v10_2 != null) {
                    v35 = v15;
                    v14_3 = Utilities.parseLong(((String)v10_2)).longValue();
                    ((Message)v8_4).grouped_id = v14_3;
                    ((Message)v8_4).flags |= 131072;
                }
                else {
                    v35 = v15;
                    v14_3 = v16;
                }

                if(v12.get("final") == null) {
                    goto label_977;
                }
            }
            else {
                goto label_975;
            }
        }
        catch(Exception v0) {
            goto label_2554;
        }

        v10 = 1;
        goto label_978;
    label_975:
        v35 = v15;
        v14_3 = v16;
    label_977:
        v10 = 0;
    label_978:
        if(v14_3 == v16) {
            try {
                v10_3 = new ArrayList();
                v10_3.add(v13_6);
                v36 = v12;
                v12_1 = new ArrayList();
                v12_1.add(v8_4);
                v37 = v13_6;
            }
            catch(Exception v0) {
                goto label_2558;
            }

            try {
                MessagesStorage.getInstance(v1.currentAccount).putMessages(v12_1, false, true, false, 0);
                MessagesController.getInstance(v1.currentAccount).updateInterfaceWithMessages(v4_6, v10_3);
                NotificationCenter.getInstance(v1.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
                v3_3 = null;
            }
            catch(Exception v0) {
                goto label_1012;
            }
        }
        else {
            v36 = v12;
            v37 = v13_6;
            try {
                StringBuilder v3_4 = new StringBuilder();
                v3_4.append("group_");
                v3_4.append(v14_3);
                v3_3 = v1.delayedMessages.get(v3_4.toString());
                if(v3_3 != null) {
                }
                else {
                    goto label_1032;
                }
            }
            catch(Exception v0) {
                goto label_2551;
            }

            try {
                v3_3 = ((ArrayList)v3_3).get(0);
                goto label_1033;
            label_1032:
                v3_3 = null;
            label_1033:
                if(v3_3 == null) {
                    v3_5 = new DelayedMessage(v1, v4_6);
                    v3_5.type = 4;
                    v3_5.groupId = v14_3;
                    v3_5.messageObjects = new ArrayList();
                    v3_5.messages = new ArrayList();
                    v3_5.originalPaths = new ArrayList();
                    v3_5.extraHashMap = new HashMap();
                    v3_5.encryptedChat = v9_1;
                }

                if(v10 == 0) {
                    goto label_1055;
                }

                ((DelayedMessage)v3_3).finalGroupMessage = ((Message)v8_4).id;
            }
            catch(Exception v0) {
                goto label_1012;
            }
        }

        try {
        label_1055:
            if(BuildVars.LOGS_ENABLED) {
                goto label_1057;
            }

            goto label_1078;
        }
        catch(Exception v0) {
            goto label_2551;
        }

    label_1057:
        if(v7 != null) {
            try {
                FileLog.d("send message user_id = " + v7.user_id + " chat_id = " + v7.chat_id + " channel_id = " + v7.channel_id + " access_hash = " + v7.access_hash);
            label_1078:
                if(v11_1 != 0) {
                    if(v11_1 == 9 && v2 != null && v9_1 != null) {
                        goto label_2396;
                    }

                    if((v11_1 < 1 || v11_1 > 3) && (v11_1 < 5 || v11_1 > 8) && (v11_1 != 9 || v9_1 == null)) {
                        goto label_2265;
                    }

                    if(v9_1 == null) {
                        if(v11_1 == 1) {
                            v2_5 = v34;
                            if((v2_5 instanceof TL_messageMediaVenue)) {
                                TL_inputMediaVenue v6_12 = new TL_inputMediaVenue();
                                ((InputMedia)v6_12).address = v2_5.address;
                                ((InputMedia)v6_12).title = v2_5.title;
                                ((InputMedia)v6_12).provider = v2_5.provider;
                                ((InputMedia)v6_12).venue_id = v2_5.venue_id;
                                ((InputMedia)v6_12).venue_type = "";
                            }
                            else if((v2_5 instanceof TL_messageMediaGeoLive)) {
                                v6_13 = new TL_inputMediaGeoLive();
                                ((InputMedia)v6_13).period = v2_5.period;
                            }
                            else {
                                TL_inputMediaGeoPoint v6_14 = new TL_inputMediaGeoPoint();
                            }

                            ((InputMedia)v6_13).geo_point = new TL_inputGeoPoint();
                            ((InputMedia)v6_13).geo_point.lat = v2_5.geo.lat;
                            ((InputMedia)v6_13).geo_point._long = v2_5.geo._long;
                            TL_inputMediaVenue v2_7 = ((TL_inputMediaVenue)v6_13);
                            v40 = v7;
                            v12_2 = v11_1;
                            v38 = v14_3;
                            v14_4 = v20;
                            v42 = v30;
                            v11_2 = v32;
                            v13_6 = v37;
                        }
                        else {
                            goto label_1143;
                        }

                        goto label_1454;
                    }
                    else {
                        goto label_1638;
                    }
                }
                else {
                    goto label_2396;
                }

                goto label_2263;
            }
            catch(Exception v0) {
                goto label_1012;
            }
        }

        goto label_1078;
    label_1143:
        if(v11_1 != 2) {
            if(v11_1 == 9 && v32 != null) {
                v40 = v7;
                v41 = v11_1;
                v38 = v14_3;
                v14_4 = v20;
                v42 = v30;
                v11_2 = v32;
                v7_4 = v36;
                v13_6 = v37;
                v12_2 = arg70;
                goto label_1382;
            }

            if(v11_1 == 3) {
                TL_document v10_5 = v30;
                try {
                    if(v10_5.access_hash == v16) {
                        v2_8 = new TL_inputMediaUploadedDocument();
                        ((InputMedia)v2_8).mime_type = v10_5.mime_type;
                        ((InputMedia)v2_8).attributes = v10_5.attributes;
                        if(!MessageObject.isRoundVideoDocument(((Document)v10_5))) {
                            goto label_1171;
                        }

                        goto label_1178;
                    }
                    else {
                        goto label_1209;
                    }
                }
                catch(Exception v0) {
                    goto label_2551;
                }

            label_1171:
                if(v6_11 != null) {
                    try {
                        if(v6_11.muted) {
                        }
                        else if(!v6_11.roundVideo) {
                            goto label_1176;
                        }

                        goto label_1178;
                    label_1176:
                        ((InputMedia)v2_8).nosound_video = true;
                    label_1178:
                        v12_2 = arg70;
                        if(v12_2 != 0) {
                            ((InputMedia)v2_8).ttl_seconds = v12_2;
                            ((Message)v8_4).ttl = v12_2;
                            ((InputMedia)v2_8).flags |= 2;
                        }

                        goto label_1186;
                    }
                    catch(Exception v0) {
                    label_1012:
                        v2_1 = v0;
                        v13_6 = v37;
                        goto label_2559;
                    }
                }

                goto label_1176;
            label_1186:
                if(v3_3 == null) {
                    try {
                        v3_5 = new DelayedMessage(v1, v4_6);
                        v3_5.type = 1;
                        v13_6 = v37;
                    }
                    catch(Exception v0) {
                    label_2551:
                        v13_6 = v37;
                        goto label_2558;
                    }

                    try {
                        v3_5.obj = v13_6;
                        Object v9_3 = v20;
                        v3_5.originalPath = ((String)v9_3);
                        goto label_1198;
                    label_1196:
                        v9_3 = v20;
                        v13_6 = v37;
                    label_1198:
                        ((DelayedMessage)v3_3).location = v10_5.thumb.location;
                        ((DelayedMessage)v3_3).videoEditedInfo = v6_11;
                        v40 = v7;
                        v42 = v10_5;
                        v12_2 = v11_1;
                        v38 = v14_3;
                        v11_2 = v32;
                        v14_4 = v9_3;
                        goto label_1454;
                    label_1209:
                        v13_6 = v37;
                        TL_inputMediaDocument v2_9 = new TL_inputMediaDocument();
                        v2_9.id = new TL_inputDocument();
                        v38 = v14_3;
                        v2_9.id.id = v10_5.id;
                        v2_9.id.access_hash = v10_5.access_hash;
                        v40 = v7;
                        v14_4 = v20;
                        goto label_1225;
                    label_1229:
                        v38 = v14_3;
                        v14_4 = v20;
                        v10_5 = v30;
                        v13_6 = v37;
                        v12_2 = arg70;
                        if(v11_1 == 6) {
                            TL_inputMediaContact v2_10 = new TL_inputMediaContact();
                            v6 = v31;
                            ((InputMedia)v2_10).phone_number = v6.phone;
                            ((InputMedia)v2_10).first_name = v6.first_name;
                            ((InputMedia)v2_10).last_name = v6.last_name;
                            v6_8 = v6.restriction_reason == null || !v6.restriction_reason.startsWith("BEGIN:VCARD") ? "" : v6.restriction_reason;
                            ((InputMedia)v2_10).vcard = v6_8;
                            v40 = v7;
                            goto label_1225;
                        }

                        if(v11_1 == 7 || v11_1 == 9) {
                            v40 = v7;
                            v41 = v11_1;
                            if(v10_5.access_hash == v16) {
                                if(v9_1 != null || v14_4 == null || ((String)v14_4).length() <= 0 || !((String)v14_4).startsWith("http") || v36 == null) {
                                    v2_8 = new TL_inputMediaUploadedDocument();
                                    if(v12_2 != 0) {
                                        ((InputMedia)v2_8).ttl_seconds = v12_2;
                                        ((Message)v8_4).ttl = v12_2;
                                        ((InputMedia)v2_8).flags |= 2;
                                    }

                                    v3_5 = new DelayedMessage(v1, v4_6);
                                    v3_5.originalPath = ((String)v14_4);
                                    v3_5.type = 2;
                                    v3_5.obj = v13_6;
                                    v3_5.location = v10_5.thumb.location;
                                }
                                else {
                                    TL_inputMediaGifExternal v2_12 = new TL_inputMediaGifExternal();
                                    String[] v6_16 = v36.get("url").split("\\|");
                                    if(v6_16.length == 2) {
                                        v2_12.url = v6_16[0];
                                        ((InputMedia)v2_12).q = v6_16[1];
                                    }
                                }

                                ((InputMedia)v2_8).mime_type = v10_5.mime_type;
                                ((InputMedia)v2_8).attributes = v10_5.attributes;
                                goto label_1369;
                            }

                            v2_9 = new TL_inputMediaDocument();
                            v2_9.id = new TL_inputDocument();
                            v2_9.id.id = v10_5.id;
                            v6_15 = v2_9.id;
                            v11_3 = v10_5.access_hash;
                        label_1368:
                            v6_15.access_hash = v11_3;
                        }
                        else if(v11_1 == 8) {
                            v40 = v7;
                            if(v10_5.access_hash == v16) {
                                v2_8 = new TL_inputMediaUploadedDocument();
                                ((InputMedia)v2_8).mime_type = v10_5.mime_type;
                                ((InputMedia)v2_8).attributes = v10_5.attributes;
                                if(v12_2 != 0) {
                                    ((InputMedia)v2_8).ttl_seconds = v12_2;
                                    ((Message)v8_4).ttl = v12_2;
                                    ((InputMedia)v2_8).flags |= 2;
                                }

                                v3_5 = new DelayedMessage(v1, v4_6);
                                v3_5.type = 3;
                                v3_5.obj = v13_6;
                            label_1225:
                                v42 = v10_5;
                                v12_2 = v11_1;
                                v11_2 = v32;
                                goto label_1454;
                            }
                            else {
                                v2_9 = new TL_inputMediaDocument();
                                v2_9.id = new TL_inputDocument();
                                v41 = v11_1;
                                v2_9.id.id = v10_5.id;
                                v6_15 = v2_9.id;
                                v11_3 = v10_5.access_hash;
                                goto label_1368;
                            }
                        }
                        else {
                            v40 = v7;
                            v42 = v10_5;
                            v12_2 = v11_1;
                            v11_2 = v32;
                            InputMedia v2_11 = null;
                            goto label_1454;
                        }

                    label_1369:
                        v42 = v10_5;
                        v11_2 = v32;
                        v12_2 = v41;
                        goto label_1454;
                    label_1373:
                        v40 = v7;
                        v41 = v11_1;
                        v38 = v14_3;
                        v14_4 = v20;
                        v7_4 = v36;
                        v13_6 = v37;
                        v12_2 = arg70;
                        v42 = v30;
                        v11_2 = v32;
                    label_1382:
                        if(v11_2.access_hash == v16) {
                            TL_inputMediaUploadedPhoto v2_13 = new TL_inputMediaUploadedPhoto();
                            if(v12_2 != 0) {
                                ((InputMedia)v2_13).ttl_seconds = v12_2;
                                ((Message)v8_4).ttl = v12_2;
                                ((InputMedia)v2_13).flags |= 2;
                            }

                            if(v7_4 != null) {
                                v6_7 = v7_4.get("masks");
                                if(v6_7 != null) {
                                    SerializedData v7_5 = new SerializedData(Utilities.hexToBytes(((String)v6_7)));
                                    boolean v6_17 = false;
                                    v9_4 = v7_5.readInt32(false);
                                    v10 = 0;
                                    while(v10 < v9_4) {
                                        ((InputMedia)v2_13).stickers.add(InputDocument.TLdeserialize(((AbstractSerializedData)v7_5), v7_5.readInt32(v6_17), v6_17));
                                        ++v10;
                                        v6_17 = false;
                                    }

                                    ((InputMedia)v2_13).flags |= 1;
                                    v7_5.cleanup();
                                }
                            }

                            if(v3_3 == null) {
                                v3_5 = new DelayedMessage(v1, v4_6);
                                v3_5.type = 0;
                                v3_5.obj = v13_6;
                                v3_5.originalPath = ((String)v14_4);
                            }

                            v12_2 = v41;
                            v10_6 = arg62;
                            if(v10_6 != null && arg62.length() > 0 && (v10_6.startsWith("http"))) {
                                ((DelayedMessage)v3_3).httpLocation = v10_6;
                                goto label_1454;
                            }

                            ((DelayedMessage)v3_3).location = v11_2.sizes.get(v11_2.sizes.size() - 1).location;
                        }
                        else {
                            v12_2 = v41;
                            TL_inputMediaPhoto v2_14 = new TL_inputMediaPhoto();
                            v2_14.id = new TL_inputPhoto();
                            v2_14.id.id = v11_2.id;
                            v2_14.id.access_hash = v11_2.access_hash;
                        }

                    label_1454:
                        if(v35 != null) {
                            TL_messages_sendBroadcast v6_18 = new TL_messages_sendBroadcast();
                            v7_6 = new ArrayList();
                            for(v9_4 = 0; v9_4 < v35.size(); ++v9_4) {
                                v7_6.add(Long.valueOf(Utilities.random.nextLong()));
                            }

                            v6_18.contacts = v35;
                            v6_18.media = ((InputMedia)v2_8);
                            v6_18.random_id = v7_6;
                            v6_18.message = "";
                            if(v3_3 != null) {
                                ((DelayedMessage)v3_3).sendRequest = ((TLObject)v6_18);
                            }

                            if(arg66 == null) {
                                DataQuery.getInstance(v1.currentAccount).cleanDraft(v4_6, false);
                            }

                            TL_messages_sendBroadcast v4_7 = v6_18;
                            goto label_1483;
                        }
                        else {
                            if(v38 != v16) {
                                if(((DelayedMessage)v3_3).sendRequest != null) {
                                    v4_8 = ((DelayedMessage)v3_3).sendRequest;
                                }
                                else {
                                    TL_messages_sendMultiMedia v6_19 = new TL_messages_sendMultiMedia();
                                    v6_19.peer = v40;
                                    if((((Message)v8_4).to_id instanceof TL_peerChannel)) {
                                        SharedPreferences v7_7 = MessagesController.getNotificationsSettings(v1.currentAccount);
                                        v9_5 = new StringBuilder();
                                        v9_5.append("silent_");
                                        v9_5.append(v4_6);
                                        v6_19.silent = v7_7.getBoolean(v9_5.toString(), false);
                                    }

                                    if(((Message)v8_4).reply_to_msg_id != 0) {
                                        v6_19.flags |= 1;
                                        v6_19.reply_to_msg_id = ((Message)v8_4).reply_to_msg_id;
                                    }

                                    ((DelayedMessage)v3_3).sendRequest = ((TLObject)v6_19);
                                    TL_messages_sendMultiMedia v4_9 = v6_19;
                                }

                                ((DelayedMessage)v3_3).messageObjects.add(v13_6);
                                ((DelayedMessage)v3_3).messages.add(v8_4);
                                ((DelayedMessage)v3_3).originalPaths.add(v14_4);
                                TL_inputSingleMedia v5 = new TL_inputSingleMedia();
                                v5.random_id = ((Message)v8_4).random_id;
                                v5.media = ((InputMedia)v2_8);
                                v5.message = v28;
                                v7_6 = arg67;
                                if(v7_6 != null && !arg67.isEmpty()) {
                                    v5.entities = v7_6;
                                    v5.flags |= 1;
                                }

                                ((TL_messages_sendMultiMedia)v4_8).multi_media.add(v5);
                            label_1483:
                                v43 = v14_4;
                                goto label_1589;
                            }

                            v6_8 = v28;
                            v7_6 = arg67;
                            TL_messages_sendMedia v9_6 = new TL_messages_sendMedia();
                            v9_6.peer = v40;
                            if((((Message)v8_4).to_id instanceof TL_peerChannel)) {
                                SharedPreferences v10_7 = MessagesController.getNotificationsSettings(v1.currentAccount);
                                StringBuilder v15_3 = new StringBuilder();
                                v43 = v14_4;
                                v15_3.append("silent_");
                                v15_3.append(v4_6);
                                v9_6.silent = v10_7.getBoolean(v15_3.toString(), false);
                            }
                            else {
                                v43 = v14_4;
                            }

                            if(((Message)v8_4).reply_to_msg_id != 0) {
                                v9_6.flags |= 1;
                                v9_6.reply_to_msg_id = ((Message)v8_4).reply_to_msg_id;
                            }

                            v9_6.random_id = ((Message)v8_4).random_id;
                            v9_6.media = ((InputMedia)v2_8);
                            v9_6.message = v6_8;
                            if(v7_6 != null && !arg67.isEmpty()) {
                                v9_6.entities = v7_6;
                                v9_6.flags |= 8;
                            }

                            if(v3_3 != null) {
                                ((DelayedMessage)v3_3).sendRequest = ((TLObject)v9_6);
                            }

                            TL_messages_sendMedia v4_10 = v9_6;
                        }

                    label_1589:
                        if(v38 != v16) {
                        label_1590:
                            v1.performSendDelayedMessage(((DelayedMessage)v3_3));
                            return;
                        }
                        else if(v12_2 != 1) {
                            if(v12_2 == 2) {
                                if(v11_2.access_hash == v16) {
                                    goto label_1590;
                                }
                                else {
                                    this.performSendMessageRequest(((TL_messages_sendBroadcast)v4_8), v13_6, null, null, true);
                                    return;
                                }
                            }
                            else if(v12_2 != 3) {
                                v14_5 = v42;
                                v2 = null;
                                if(v12_2 != 6) {
                                    if(v12_2 == 7) {
                                        if(v14_5.access_hash == v16 && v3_3 != null) {
                                            goto label_1590;
                                        }

                                        v2_15 = v43;
                                    }
                                    else {
                                        goto label_1633;
                                    }
                                }

                                v1.performSendMessageRequest(v4_8, v13_6, v2);
                                return;
                            label_1633:
                                if(v12_2 != 8) {
                                    return;
                                }

                                if(v14_5.access_hash != v16) {
                                    goto label_1594;
                                }

                                goto label_1590;
                            }
                            else if(v42.access_hash == v16) {
                                goto label_1590;
                            }
                        }

                    label_1594:
                        v1.performSendMessageRequest(v4_8, v13_6, null);
                        return;
                    label_1638:
                        VideoEditedInfo v45 = v6_11;
                        v12_2 = v11_1;
                        v38 = v14_3;
                        Object v46 = v20;
                        v10_6 = v28;
                        v14_5 = v30;
                        User v44 = v31;
                        v11_2 = v32;
                        v2_5 = v34;
                        v7_4 = v36;
                        v13_6 = v37;
                        v15 = arg67;
                        if(AndroidUtilities.getPeerLayerVersion(v9_1.layer) >= 73) {
                            TL_decryptedMessage v4_11 = new TL_decryptedMessage();
                            if(v38 != v16) {
                                v5_1 = v38;
                                v4_11.grouped_id = v5_1;
                                v47 = v3_3;
                                v4_11.flags |= 131072;
                            }
                            else {
                                v47 = v3_3;
                                v5_1 = v38;
                            }

                            TL_decryptedMessage v3_6 = v4_11;
                        }
                        else {
                            v47 = v3_3;
                            v5_1 = v38;
                            v3_7 = new TL_decryptedMessage_layer45();
                        }

                        ((TL_decryptedMessage)v3_7).ttl = ((Message)v8_4).ttl;
                        if(v15 != null && !arg67.isEmpty()) {
                            ((TL_decryptedMessage)v3_7).entities = v15;
                            ((TL_decryptedMessage)v3_7).flags |= 128;
                        }

                        long v48 = v5_1;
                        if(((Message)v8_4).reply_to_random_id != v16) {
                            ((TL_decryptedMessage)v3_7).reply_to_random_id = ((Message)v8_4).reply_to_random_id;
                            ((TL_decryptedMessage)v3_7).flags |= 8;
                        }

                        ((TL_decryptedMessage)v3_7).flags |= 512;
                        if(v7_4 != null && v7_4.get("bot_name") != null) {
                            ((TL_decryptedMessage)v3_7).via_bot_name = v7_4.get("bot_name");
                            ((TL_decryptedMessage)v3_7).flags |= 2048;
                        }

                        ((TL_decryptedMessage)v3_7).random_id = ((Message)v8_4).random_id;
                        ((TL_decryptedMessage)v3_7).message = "";
                        if(v12_2 == 1) {
                            if((v2_5 instanceof TL_messageMediaVenue)) {
                                ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaVenue();
                                ((TL_decryptedMessage)v3_7).media.address = v2_5.address;
                                ((TL_decryptedMessage)v3_7).media.title = v2_5.title;
                                ((TL_decryptedMessage)v3_7).media.provider = v2_5.provider;
                                ((TL_decryptedMessage)v3_7).media.venue_id = v2_5.venue_id;
                            }
                            else {
                                ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaGeoPoint();
                            }

                            ((TL_decryptedMessage)v3_7).media.lat = v2_5.geo.lat;
                            ((TL_decryptedMessage)v3_7).media._long = v2_5.geo._long;
                            SecretChatHelper.getInstance(v1.currentAccount).performSendEncryptedRequest(((TL_decryptedMessage)v3_7), v13_6.messageOwner, v9_1, null, null, v13_6);
                            goto label_1752;
                        }
                        else {
                            if(v12_2 != 2 && (v12_2 != 9 || v11_2 == null)) {
                                if(v12_2 == 3) {
                                    ImageLoader.fillPhotoSizeWithBytes(v14_5.thumb);
                                    if((MessageObject.isNewGifDocument(((Document)v14_5))) || (MessageObject.isRoundVideoDocument(((Document)v14_5)))) {
                                        ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaDocument();
                                        ((TL_decryptedMessage)v3_7).media.attributes = v14_5.attributes;
                                        if(v14_5.thumb != null && v14_5.thumb.bytes != null) {
                                            ((TL_decryptedMessage)v3_7).media.thumb = v14_5.thumb.bytes;
                                            goto label_1808;
                                        }

                                        ((TL_decryptedMessage)v3_7).media.thumb = new byte[0];
                                    }
                                    else {
                                        ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaVideo();
                                        if(v14_5.thumb != null && v14_5.thumb.bytes != null) {
                                            ((TL_decryptedMessage)v3_7).media.thumb = v14_5.thumb.bytes;
                                            goto label_1808;
                                        }

                                        ((TL_decryptedMessage)v3_7).media.thumb = new byte[0];
                                    }

                                label_1808:
                                    ((TL_decryptedMessage)v3_7).media.caption = v10_6;
                                    ((TL_decryptedMessage)v3_7).media.mime_type = "video/mp4";
                                    ((TL_decryptedMessage)v3_7).media.size = v14_5.size;
                                    int v2_16 = 0;
                                    while(v2_16 < v14_5.attributes.size()) {
                                        Object v4_12 = v14_5.attributes.get(v2_16);
                                        if((v4_12 instanceof TL_documentAttributeVideo)) {
                                            ((TL_decryptedMessage)v3_7).media.w = ((DocumentAttribute)v4_12).w;
                                            ((TL_decryptedMessage)v3_7).media.h = ((DocumentAttribute)v4_12).h;
                                            ((TL_decryptedMessage)v3_7).media.duration = ((DocumentAttribute)v4_12).duration;
                                        }
                                        else {
                                            ++v2_16;
                                            continue;
                                        }

                                        break;
                                    }

                                    ((TL_decryptedMessage)v3_7).media.thumb_h = v14_5.thumb.h;
                                    ((TL_decryptedMessage)v3_7).media.thumb_w = v14_5.thumb.w;
                                    if(v14_5.key != null) {
                                        if(v48 != v16) {
                                        }
                                        else {
                                            v2_17 = new TL_inputEncryptedFile();
                                            v2_17.id = v14_5.id;
                                            v2_17.access_hash = v14_5.access_hash;
                                            ((TL_decryptedMessage)v3_7).media.key = v14_5.key;
                                            ((TL_decryptedMessage)v3_7).media.iv = v14_5.iv;
                                            SecretChatHelper.getInstance(v1.currentAccount).performSendEncryptedRequest(((TL_decryptedMessage)v3_7), v13_6.messageOwner, v9_1, v2_17, null, v13_6);
                                            goto label_1752;
                                        }
                                    }

                                    if(v47 == null) {
                                        v4_6 = arg60;
                                        v2_18 = new DelayedMessage(v1, v4_6);
                                        v2_18.encryptedChat = v9_1;
                                        v2_18.type = 1;
                                        v2_18.sendEncryptedRequest = ((TLObject)v3_7);
                                        v6_7 = v46;
                                        v2_18.originalPath = ((String)v6_7);
                                        v2_18.obj = v13_6;
                                        v7_8 = v2_18;
                                        v2_19 = v45;
                                    }
                                    else {
                                        v6_7 = v46;
                                        v4_6 = arg60;
                                        v2_19 = v45;
                                        Object v7_9 = v47;
                                    }

                                    v7_8.videoEditedInfo = v2_19;
                                    if(v48 == v16) {
                                        v1.performSendDelayedMessage(v7_8);
                                    }

                                    v2_18 = v7_8;
                                    goto label_1987;
                                label_1752:
                                    v6_7 = v46;
                                    v4_6 = arg60;
                                    goto label_2118;
                                }
                                else {
                                    v6_7 = v46;
                                    v4_6 = arg60;
                                    if(v12_2 == 6) {
                                        ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaContact();
                                        ((TL_decryptedMessage)v3_7).media.phone_number = v44.phone;
                                        ((TL_decryptedMessage)v3_7).media.first_name = v44.first_name;
                                        ((TL_decryptedMessage)v3_7).media.last_name = v44.last_name;
                                        ((TL_decryptedMessage)v3_7).media.user_id = v44.id;
                                        v2_20 = SecretChatHelper.getInstance(v1.currentAccount);
                                        v7_10 = v13_6.messageOwner;
                                    }
                                    else {
                                        if(v12_2 != 7) {
                                            if(v12_2 == 9 && v14_5 != null) {
                                                goto label_1989;
                                            }

                                            if(v12_2 != 8) {
                                                goto label_2118;
                                            }

                                            v2_18 = new DelayedMessage(v1, v4_6);
                                            v2_18.encryptedChat = v9_1;
                                            v2_18.sendEncryptedRequest = ((TLObject)v3_7);
                                            v2_18.obj = v13_6;
                                            v2_18.type = 3;
                                            ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaDocument();
                                            ((TL_decryptedMessage)v3_7).media.attributes = v14_5.attributes;
                                            ((TL_decryptedMessage)v3_7).media.caption = v10_6;
                                            if(v14_5.thumb == null || v14_5.thumb.bytes == null) {
                                                v9_4 = 0;
                                                ((TL_decryptedMessage)v3_7).media.thumb = new byte[0];
                                                ((TL_decryptedMessage)v3_7).media.thumb_h = 0;
                                                v7_11 = ((TL_decryptedMessage)v3_7).media;
                                            }
                                            else {
                                                ((TL_decryptedMessage)v3_7).media.thumb = v14_5.thumb.bytes;
                                                ((TL_decryptedMessage)v3_7).media.thumb_h = v14_5.thumb.h;
                                                v7_11 = ((TL_decryptedMessage)v3_7).media;
                                                v9_4 = v14_5.thumb.w;
                                            }

                                            v7_11.thumb_w = v9_4;
                                            ((TL_decryptedMessage)v3_7).media.mime_type = v14_5.mime_type;
                                            ((TL_decryptedMessage)v3_7).media.size = v14_5.size;
                                            v2_18.originalPath = ((String)v6_7);
                                        }
                                        else {
                                        label_1989:
                                            if(!MessageObject.isStickerDocument(((Document)v14_5))) {
                                                goto label_2033;
                                            }

                                            ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaExternalDocument();
                                            ((TL_decryptedMessage)v3_7).media.id = v14_5.id;
                                            ((TL_decryptedMessage)v3_7).media.date = v14_5.date;
                                            ((TL_decryptedMessage)v3_7).media.access_hash = v14_5.access_hash;
                                            ((TL_decryptedMessage)v3_7).media.mime_type = v14_5.mime_type;
                                            ((TL_decryptedMessage)v3_7).media.size = v14_5.size;
                                            ((TL_decryptedMessage)v3_7).media.dc_id = v14_5.dc_id;
                                            ((TL_decryptedMessage)v3_7).media.attributes = v14_5.attributes;
                                            if(v14_5.thumb == null) {
                                                ((TL_decryptedMessage)v3_7).media.thumb = new TL_photoSizeEmpty();
                                                ((TL_decryptedMessage)v3_7).media.thumb.type = "s";
                                            }
                                            else {
                                                ((TL_decryptedMessage)v3_7).media.thumb = v14_5.thumb;
                                            }

                                            v2_20 = SecretChatHelper.getInstance(v1.currentAccount);
                                            v7_10 = v13_6.messageOwner;
                                            goto label_1919;
                                        }

                                        goto label_1986;
                                    }

                                label_1919:
                                    v2_20.performSendEncryptedRequest(((TL_decryptedMessage)v3_7), v7_10, v9_1, null, null, v13_6);
                                    goto label_2118;
                                label_2033:
                                    ImageLoader.fillPhotoSizeWithBytes(v14_5.thumb);
                                    ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaDocument();
                                    ((TL_decryptedMessage)v3_7).media.attributes = v14_5.attributes;
                                    ((TL_decryptedMessage)v3_7).media.caption = v10_6;
                                    if(v14_5.thumb == null || v14_5.thumb.bytes == null) {
                                        v7_3 = 0;
                                        ((TL_decryptedMessage)v3_7).media.thumb = new byte[0];
                                        ((TL_decryptedMessage)v3_7).media.thumb_h = 0;
                                        v2_21 = ((TL_decryptedMessage)v3_7).media;
                                    }
                                    else {
                                        ((TL_decryptedMessage)v3_7).media.thumb = v14_5.thumb.bytes;
                                        ((TL_decryptedMessage)v3_7).media.thumb_h = v14_5.thumb.h;
                                        v2_21 = ((TL_decryptedMessage)v3_7).media;
                                        v7_3 = v14_5.thumb.w;
                                    }

                                    v2_21.thumb_w = v7_3;
                                    ((TL_decryptedMessage)v3_7).media.size = v14_5.size;
                                    ((TL_decryptedMessage)v3_7).media.mime_type = v14_5.mime_type;
                                    if(v14_5.key != null) {
                                        goto label_2094;
                                    }

                                    v2_18 = new DelayedMessage(v1, v4_6);
                                    v2_18.originalPath = ((String)v6_7);
                                    v2_18.sendEncryptedRequest = ((TLObject)v3_7);
                                    v2_18.type = 2;
                                    v2_18.obj = v13_6;
                                    v2_18.encryptedChat = v9_1;
                                    v7_12 = arg62;
                                    if(v7_12 != null && arg62.length() > 0 && (v7_12.startsWith("http"))) {
                                        v2_18.httpLocation = v7_12;
                                    }

                                label_1986:
                                    v1.performSendDelayedMessage(v2_18);
                                }

                            label_1987:
                                v50 = v12_2;
                                goto label_2229;
                            label_2094:
                                v2_17 = new TL_inputEncryptedFile();
                                v2_17.id = v14_5.id;
                                v2_17.access_hash = v14_5.access_hash;
                                ((TL_decryptedMessage)v3_7).media.key = v14_5.key;
                                ((TL_decryptedMessage)v3_7).media.iv = v14_5.iv;
                                SecretChatHelper.getInstance(v1.currentAccount).performSendEncryptedRequest(((TL_decryptedMessage)v3_7), v13_6.messageOwner, v9_1, v2_17, null, v13_6);
                            label_2118:
                                v50 = v12_2;
                                goto label_2119;
                            }

                            v2 = v10_6;
                            v6_7 = v46;
                            v4_6 = arg60;
                            v7_12 = arg62;
                            v10_2 = v11_2.sizes.get(0);
                            v14_4 = v11_2.sizes.get(v11_2.sizes.size() - 1);
                            ImageLoader.fillPhotoSizeWithBytes(((PhotoSize)v10_2));
                            ((TL_decryptedMessage)v3_7).media = new TL_decryptedMessageMediaPhoto();
                            ((TL_decryptedMessage)v3_7).media.caption = v2;
                            if(((PhotoSize)v10_2).bytes != null) {
                                ((TL_decryptedMessage)v3_7).media.thumb = ((PhotoSize)v10_2).bytes;
                                v50 = v12_2;
                            }
                            else {
                                v50 = v12_2;
                                ((TL_decryptedMessage)v3_7).media.thumb = new byte[0];
                            }

                            ((TL_decryptedMessage)v3_7).media.thumb_h = ((PhotoSize)v10_2).h;
                            ((TL_decryptedMessage)v3_7).media.thumb_w = ((PhotoSize)v10_2).w;
                            ((TL_decryptedMessage)v3_7).media.w = ((PhotoSize)v14_4).w;
                            ((TL_decryptedMessage)v3_7).media.h = ((PhotoSize)v14_4).h;
                            ((TL_decryptedMessage)v3_7).media.size = ((PhotoSize)v14_4).size;
                            if(((PhotoSize)v14_4).location.key != null) {
                                if(v48 != v16) {
                                }
                                else {
                                    v2_17 = new TL_inputEncryptedFile();
                                    v2_17.id = ((PhotoSize)v14_4).location.volume_id;
                                    v2_17.access_hash = ((PhotoSize)v14_4).location.secret;
                                    ((TL_decryptedMessage)v3_7).media.key = ((PhotoSize)v14_4).location.key;
                                    ((TL_decryptedMessage)v3_7).media.iv = ((PhotoSize)v14_4).location.iv;
                                    SecretChatHelper.getInstance(v1.currentAccount).performSendEncryptedRequest(((TL_decryptedMessage)v3_7), v13_6.messageOwner, v9_1, v2_17, null, v13_6);
                                label_2119:
                                    v2_15 = v47;
                                    goto label_2229;
                                }
                            }

                            if(v47 == null) {
                                v2_18 = new DelayedMessage(v1, v4_6);
                                v2_18.encryptedChat = v9_1;
                                v2_18.type = 0;
                                v2_18.originalPath = ((String)v6_7);
                                v2_18.sendEncryptedRequest = ((TLObject)v3_7);
                                v2_18.obj = v13_6;
                            }
                            else {
                                v2_15 = v47;
                            }

                            if((TextUtils.isEmpty(((CharSequence)arg62))) || !v7_12.startsWith("http")) {
                                ((DelayedMessage)v2_15).location = v11_2.sizes.get(v11_2.sizes.size() - 1).location;
                            }
                            else {
                                ((DelayedMessage)v2_15).httpLocation = v7_12;
                            }

                            if(v48 != v16) {
                                goto label_2229;
                            }

                            v1.performSendDelayedMessage(((DelayedMessage)v2_15));
                        }

                    label_2229:
                        if(v48 != v16) {
                            if(((DelayedMessage)v2_15).sendEncryptedRequest != null) {
                                TLObject v7_13 = ((DelayedMessage)v2_15).sendEncryptedRequest;
                            }
                            else {
                                v7_14 = new TL_messages_sendEncryptedMultiMedia();
                                ((DelayedMessage)v2_15).sendEncryptedRequest = ((TLObject)v7_14);
                            }

                            ((DelayedMessage)v2_15).messageObjects.add(v13_6);
                            ((DelayedMessage)v2_15).messages.add(v8_4);
                            ((DelayedMessage)v2_15).originalPaths.add(v6_7);
                            ((DelayedMessage)v2_15).upload = true;
                            v7_14.messages.add(v3_7);
                            TL_inputEncryptedFile v3_8 = new TL_inputEncryptedFile();
                            if(v50 == 3) {
                                v16 = 1;
                            }

                            v3_8.id = v16;
                            v7_14.files.add(v3_8);
                            v1.performSendDelayedMessage(((DelayedMessage)v2_15));
                        }

                        if(arg66 != null) {
                            return;
                        }

                        DataQuery v2_22 = DataQuery.getInstance(v1.currentAccount);
                        boolean v3_9 = false;
                        goto label_2263;
                    label_2265:
                        InputPeer v10_8 = v7;
                        v6_1 = v11_1;
                        v7_4 = v36;
                        v13_6 = v37;
                        MessageObject v2_23 = arg66;
                        if(v6_1 == 4) {
                            v3_10 = new TL_messages_forwardMessages();
                            v3_10.to_peer = v10_8;
                            v3_10.with_my_score = v2_23.messageOwner.with_my_score;
                            if(v2_23.messageOwner.ttl != 0) {
                                Chat v6_20 = MessagesController.getInstance(v1.currentAccount).getChat(Integer.valueOf(-v2_23.messageOwner.ttl));
                                v3_10.from_peer = new TL_inputPeerChannel();
                                v3_10.from_peer.channel_id = -v2_23.messageOwner.ttl;
                                if(v6_20 != null) {
                                    v3_10.from_peer.access_hash = v6_20.access_hash;
                                }
                            }
                            else {
                                v3_10.from_peer = new TL_inputPeerEmpty();
                            }

                            if((v2_23.messageOwner.to_id instanceof TL_peerChannel)) {
                                v6_21 = MessagesController.getNotificationsSettings(v1.currentAccount);
                                StringBuilder v7_15 = new StringBuilder();
                                v7_15.append("silent_");
                                v7_15.append(v4_6);
                                v3_10.silent = v6_21.getBoolean(v7_15.toString(), false);
                            }

                            v3_10.random_id.add(Long.valueOf(((Message)v8_4).random_id));
                            if(arg66.getId() >= 0) {
                                v4_13 = v3_10.id;
                                v2_24 = Integer.valueOf(arg66.getId());
                                goto label_2328;
                            }
                            else if(v2_23.messageOwner.fwd_msg_id != 0) {
                                v4_13 = v3_10.id;
                                v2_24 = Integer.valueOf(v2_23.messageOwner.fwd_msg_id);
                                goto label_2328;
                            }
                            else if(v2_23.messageOwner.fwd_from != null) {
                                v4_13 = v3_10.id;
                                v2_24 = Integer.valueOf(v2_23.messageOwner.fwd_from.channel_post);
                            label_2328:
                                v4_13.add(v2_24);
                            }

                            v2 = null;
                            goto label_2348;
                        }

                        if(v6_1 != 9) {
                            return;
                        }

                        TL_messages_sendInlineBotResult v3_11 = new TL_messages_sendInlineBotResult();
                        v3_11.peer = v10_8;
                        v3_11.random_id = ((Message)v8_4).random_id;
                        if(((Message)v8_4).reply_to_msg_id != 0) {
                            v3_11.flags |= 1;
                            v3_11.reply_to_msg_id = ((Message)v8_4).reply_to_msg_id;
                        }

                        if((((Message)v8_4).to_id instanceof TL_peerChannel)) {
                            v6_21 = MessagesController.getNotificationsSettings(v1.currentAccount);
                            v9_5 = new StringBuilder();
                            v9_5.append("silent_");
                            v9_5.append(v4_6);
                            v3_11.silent = v6_21.getBoolean(v9_5.toString(), false);
                        }

                        v3_11.query_id = Utilities.parseLong(v7_4.get("query_id")).longValue();
                        v3_11.id = v7_4.get("id");
                        if(v2_23 == null) {
                            v3_11.clear_draft = true;
                            DataQuery.getInstance(v1.currentAccount).cleanDraft(v4_6, false);
                        }

                        v2 = null;
                        goto label_2348;
                    label_2396:
                        v10_8 = v7;
                        v15 = v35;
                        v7_4 = v36;
                        v13_6 = v37;
                        MessageObject v3_12 = arg66;
                        ArrayList v6_22 = arg67;
                        if(v9_1 == null) {
                            if(v15 != null) {
                                TL_messages_sendBroadcast v3_13 = new TL_messages_sendBroadcast();
                                v4_13 = new ArrayList();
                                int v5_2;
                                for(v5_2 = 0; v5_2 < v15.size(); ++v5_2) {
                                    v4_13.add(Long.valueOf(Utilities.random.nextLong()));
                                }

                                v3_13.message = v2;
                                v3_13.contacts = v15;
                                v3_13.media = new TL_inputMediaEmpty();
                                v3_13.random_id = v4_13;
                                v2 = null;
                            label_2348:
                                v1.performSendMessageRequest(((TLObject)v3_10), v13_6, v2);
                                return;
                            }

                            TL_messages_sendMessage v7_16 = new TL_messages_sendMessage();
                            v7_16.message = v2;
                            boolean v2_25 = v3_12 == null ? true : false;
                            v7_16.clear_draft = v2_25;
                            if((((Message)v8_4).to_id instanceof TL_peerChannel)) {
                                SharedPreferences v2_26 = MessagesController.getNotificationsSettings(v1.currentAccount);
                                v9_5 = new StringBuilder();
                                v9_5.append("silent_");
                                v9_5.append(v4_6);
                                v7_16.silent = v2_26.getBoolean(v9_5.toString(), false);
                            }

                            v7_16.peer = v10_8;
                            v7_16.random_id = ((Message)v8_4).random_id;
                            if(((Message)v8_4).reply_to_msg_id != 0) {
                                v7_16.flags |= 1;
                                v7_16.reply_to_msg_id = ((Message)v8_4).reply_to_msg_id;
                            }

                            if(!arg65) {
                                v7_16.no_webpage = true;
                            }

                            if(v6_22 != null && !arg67.isEmpty()) {
                                v7_16.entities = v6_22;
                                v7_16.flags |= 8;
                            }

                            v1.performSendMessageRequest(((TLObject)v7_16), v13_6, null);
                            if(v3_12 != null) {
                                return;
                            }

                            v2_22 = DataQuery.getInstance(v1.currentAccount);
                            v3_9 = false;
                            goto label_2263;
                        }

                        if(AndroidUtilities.getPeerLayerVersion(v9_1.layer) >= 73) {
                            TL_decryptedMessage v10_9 = new TL_decryptedMessage();
                        }
                        else {
                            v10_10 = new TL_decryptedMessage_layer45();
                        }

                        ((TL_decryptedMessage)v10_10).ttl = ((Message)v8_4).ttl;
                        if(v6_22 != null && !arg67.isEmpty()) {
                            ((TL_decryptedMessage)v10_10).entities = v6_22;
                            ((TL_decryptedMessage)v10_10).flags |= 128;
                        }

                        if(((Message)v8_4).reply_to_random_id != v16) {
                            ((TL_decryptedMessage)v10_10).reply_to_random_id = ((Message)v8_4).reply_to_random_id;
                            ((TL_decryptedMessage)v10_10).flags |= 8;
                        }

                        if(v7_4 != null && v7_4.get("bot_name") != null) {
                            ((TL_decryptedMessage)v10_10).via_bot_name = v7_4.get("bot_name");
                            ((TL_decryptedMessage)v10_10).flags |= 2048;
                        }

                        ((TL_decryptedMessage)v10_10).random_id = ((Message)v8_4).random_id;
                        ((TL_decryptedMessage)v10_10).message = v2;
                        WebPage v2_27 = v29;
                        if(v2_27 == null || v2_27.url == null) {
                            ((TL_decryptedMessage)v10_10).media = new TL_decryptedMessageMediaEmpty();
                        }
                        else {
                            ((TL_decryptedMessage)v10_10).media = new TL_decryptedMessageMediaWebPage();
                            ((TL_decryptedMessage)v10_10).media.url = v2_27.url;
                            ((TL_decryptedMessage)v10_10).flags |= 512;
                        }

                        SecretChatHelper.getInstance(v1.currentAccount).performSendEncryptedRequest(((TL_decryptedMessage)v10_10), v13_6.messageOwner, v9_1, null, null, v13_6);
                        if(v3_12 != null) {
                            return;
                        }

                        v2_22 = DataQuery.getInstance(v1.currentAccount);
                        v3_9 = false;
                    label_2263:
                        v2_22.cleanDraft(v4_6, v3_9);
                        return;
                    }
                    catch(Exception v0) {
                    label_2554:
                        goto label_2558;
                    }
                }
                else {
                    goto label_1196;
                }

                goto label_1198;
            }
            else {
                goto label_1229;
            }
        }
        else {
            goto label_1373;
        }

        goto label_1382;
    label_2558:
        v2_1 = v0;
    label_2559:
        FileLog.e(((Throwable)v2_1));
        MessagesStorage.getInstance(v1.currentAccount).markMessageAsSendError(((Message)v8_4));
        if(v13_6 != null) {
            v13_6.messageOwner.send_state = 2;
        }

        NotificationCenter.getInstance(v1.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(((Message)v8_4).id)});
        v1.processSentMessage(((Message)v8_4).id);
    }

    public int sendMessage(ArrayList arg44, long arg45) {
        LongSparseArray v26;
        ArrayList v27;
        ArrayList v25_1;
        ArrayList v21_1;
        ArrayList v22;
        ArrayList v38;
        LongSparseArray v39;
        LongSparseArray v37;
        long v35;
        TL_messageMediaEmpty v1_4;
        int v1_2;
        ArrayList v30;
        ArrayList v29_1;
        Peer v32;
        long v29;
        LongSparseArray v28;
        InputPeer v40;
        int v13_1;
        int v24;
        int v17;
        int v16;
        boolean v20;
        boolean v19;
        int v18;
        boolean v3;
        Chat v1;
        SendMessagesHelper v11 = this;
        ArrayList v12 = arg44;
        long v13 = arg45;
        if(v12 != null) {
            if(arg44.isEmpty()) {
            }
            else {
                int v0 = ((int)v13);
                if(v0 != 0) {
                    Peer v10 = MessagesController.getInstance(v11.currentAccount).getPeer(v0);
                    if(v0 <= 0) {
                        v1 = MessagesController.getInstance(v11.currentAccount).getChat(Integer.valueOf(-v0));
                        if(ChatObject.isChannel(v1)) {
                            boolean v2 = v1.megagroup;
                            v3 = v1.signatures;
                            if(v1.banned_rights != null) {
                                int v4 = v1.banned_rights.send_stickers ^ 1;
                                int v5 = v1.banned_rights.send_media ^ 1;
                                v18 = v1.banned_rights.embed_links ^ 1;
                                v19 = v2;
                                v20 = v3;
                                v16 = v4;
                                v17 = v5;
                            }
                            else {
                                v19 = v2;
                                v20 = v3;
                                v16 = 1;
                                v17 = 1;
                                v18 = 1;
                            }
                        }
                        else {
                        label_53:
                            v16 = 1;
                            v17 = 1;
                            v18 = 1;
                            v19 = false;
                            v20 = false;
                        }
                    }
                    else if(MessagesController.getInstance(v11.currentAccount).getUser(Integer.valueOf(v0)) == null) {
                        return 0;
                    }
                    else {
                        goto label_53;
                    }

                    LongSparseArray v8 = new LongSparseArray();
                    ArrayList v1_1 = new ArrayList();
                    ArrayList v2_1 = new ArrayList();
                    ArrayList v3_1 = new ArrayList();
                    ArrayList v4_1 = new ArrayList();
                    LongSparseArray v5_1 = new LongSparseArray();
                    InputPeer v7 = MessagesController.getInstance(v11.currentAccount).getInputPeer(v0);
                    Peer v21 = v10;
                    long v9 = ((long)UserConfig.getInstance(v11.currentAccount).getClientUserId());
                    boolean v23 = v13 == v9 ? true : false;
                    int v6 = 0;
                    v24 = 0;
                    ArrayList v41 = v3_1;
                    v3_1 = v1_1;
                    v1_1 = v2_1;
                    v2_1 = v41;
                    LongSparseArray v42 = v5_1;
                    ArrayList v5_2 = v4_1;
                    LongSparseArray v4_2 = v42;
                    while(v6 < arg44.size()) {
                        Object v0_1 = v12.get(v6);
                        if(((MessageObject)v0_1).getId() <= 0 || (((MessageObject)v0_1).needDrawBluredPreview())) {
                        label_666:
                            v22 = v2_1;
                            v25_1 = v3_1;
                            v26 = v4_2;
                            v27 = v5_2;
                            v13_1 = v6;
                            v40 = v7;
                            v28 = v8;
                            v29 = v9;
                            v32 = v21;
                            v21_1 = v1_1;
                        label_677:
                            v1_1 = v21_1;
                            v2_1 = v22;
                            v3_1 = v25_1;
                            v4_2 = v26;
                            v5_2 = v27;
                        }
                        else {
                            if(v16 == 0 && ((((MessageObject)v0_1).isSticker()) || (((MessageObject)v0_1).isGif()) || (((MessageObject)v0_1).isGame()))) {
                                if(v24 != 0) {
                                    goto label_666;
                                }

                                v13_1 = v6;
                                v40 = v7;
                                v28 = v8;
                                v29 = v9;
                                v32 = v21;
                                v24 = 1;
                                goto label_682;
                            }

                            if(v17 == 0 && (((((MessageObject)v0_1).messageOwner.media instanceof TL_messageMediaPhoto)) || ((((MessageObject)v0_1).messageOwner.media instanceof TL_messageMediaDocument)))) {
                                if(v24 != 0) {
                                    goto label_666;
                                }

                                v13_1 = v6;
                                v40 = v7;
                                v28 = v8;
                                v29 = v9;
                                v32 = v21;
                                v24 = 2;
                                goto label_682;
                            }

                            TL_message v15 = new TL_message();
                            if(((MessageObject)v0_1).getDialogId() == v9) {
                                v29_1 = v1_1;
                                v30 = v3_1;
                                if(((MessageObject)v0_1).messageOwner.from_id == UserConfig.getInstance(v11.currentAccount).getClientUserId()) {
                                    v1_2 = 1;
                                }
                                else {
                                    goto label_149;
                                }
                            }
                            else {
                                v29_1 = v1_1;
                                v30 = v3_1;
                            label_149:
                                v1_2 = 0;
                            }

                            InputPeer v31 = v7;
                            int v7_1 = 4;
                            if(((MessageObject)v0_1).isForwarded()) {
                                ((Message)v15).fwd_from = new TL_messageFwdHeader();
                                ((Message)v15).fwd_from.flags = ((MessageObject)v0_1).messageOwner.fwd_from.flags;
                                ((Message)v15).fwd_from.from_id = ((MessageObject)v0_1).messageOwner.fwd_from.from_id;
                                ((Message)v15).fwd_from.date = ((MessageObject)v0_1).messageOwner.fwd_from.date;
                                ((Message)v15).fwd_from.channel_id = ((MessageObject)v0_1).messageOwner.fwd_from.channel_id;
                                ((Message)v15).fwd_from.channel_post = ((MessageObject)v0_1).messageOwner.fwd_from.channel_post;
                                ((Message)v15).fwd_from.post_author = ((MessageObject)v0_1).messageOwner.fwd_from.post_author;
                                ((Message)v15).flags = v7_1;
                            }
                            else if(v1_2 == 0) {
                                ((Message)v15).fwd_from = new TL_messageFwdHeader();
                                ((Message)v15).fwd_from.channel_post = ((MessageObject)v0_1).getId();
                                ((Message)v15).fwd_from.flags |= v7_1;
                                if(!((MessageObject)v0_1).isFromUser()) {
                                    ((Message)v15).fwd_from.channel_id = ((MessageObject)v0_1).messageOwner.to_id.channel_id;
                                    ((Message)v15).fwd_from.flags |= 2;
                                    if((((MessageObject)v0_1).messageOwner.post) && ((MessageObject)v0_1).messageOwner.from_id > 0) {
                                        goto label_202;
                                    }
                                }
                                else {
                                label_202:
                                    ((Message)v15).fwd_from.from_id = ((MessageObject)v0_1).messageOwner.from_id;
                                    ((Message)v15).fwd_from.flags |= 1;
                                }

                                if(((MessageObject)v0_1).messageOwner.post_author != null) {
                                    ((Message)v15).fwd_from.post_author = ((MessageObject)v0_1).messageOwner.post_author;
                                    goto label_235;
                                }
                                else if(!((MessageObject)v0_1).isOutOwner() && ((MessageObject)v0_1).messageOwner.from_id > 0 && (((MessageObject)v0_1).messageOwner.post)) {
                                    User v1_3 = MessagesController.getInstance(v11.currentAccount).getUser(Integer.valueOf(((MessageObject)v0_1).messageOwner.from_id));
                                    if(v1_3 != null) {
                                        ((Message)v15).fwd_from.post_author = ContactsController.formatName(v1_3.first_name, v1_3.last_name);
                                    label_235:
                                        ((Message)v15).fwd_from.flags |= 8;
                                    }
                                }

                                ((Message)v15).date = ((MessageObject)v0_1).messageOwner.date;
                                ((Message)v15).flags = 4;
                            }

                            if(v13 == v9 && ((Message)v15).fwd_from != null) {
                                ((Message)v15).fwd_from.flags |= 16;
                                ((Message)v15).fwd_from.saved_from_msg_id = ((MessageObject)v0_1).getId();
                                ((Message)v15).fwd_from.saved_from_peer = ((MessageObject)v0_1).messageOwner.to_id;
                            }

                            if(v18 != 0 || !(((MessageObject)v0_1).messageOwner.media instanceof TL_messageMediaWebPage)) {
                                MessageMedia v1_5 = ((MessageObject)v0_1).messageOwner.media;
                            }
                            else {
                                v1_4 = new TL_messageMediaEmpty();
                            }

                            ((Message)v15).media = ((MessageMedia)v1_4);
                            if(((Message)v15).media != null) {
                                ((Message)v15).flags |= 512;
                            }

                            if(v19) {
                                ((Message)v15).flags |= -2147483648;
                            }

                            if(((MessageObject)v0_1).messageOwner.via_bot_id != 0) {
                                ((Message)v15).via_bot_id = ((MessageObject)v0_1).messageOwner.via_bot_id;
                                ((Message)v15).flags |= 2048;
                            }

                            ((Message)v15).message = ((MessageObject)v0_1).messageOwner.message;
                            ((Message)v15).fwd_msg_id = ((MessageObject)v0_1).getId();
                            ((Message)v15).attachPath = ((MessageObject)v0_1).messageOwner.attachPath;
                            ((Message)v15).entities = ((MessageObject)v0_1).messageOwner.entities;
                            if(!((Message)v15).entities.isEmpty()) {
                                ((Message)v15).flags |= 128;
                            }

                            if(((Message)v15).attachPath == null) {
                                ((Message)v15).attachPath = "";
                            }

                            v1_2 = UserConfig.getInstance(v11.currentAccount).getNewMessageId();
                            ((Message)v15).id = v1_2;
                            ((Message)v15).local_id = v1_2;
                            ((Message)v15).out = true;
                            long v33 = v9;
                            v9 = ((MessageObject)v0_1).messageOwner.grouped_id;
                            long v25 = 0;
                            if(v9 != v25) {
                                v35 = v9;
                                Object v1_6 = v8.get(((MessageObject)v0_1).messageOwner.grouped_id);
                                if(v1_6 == null) {
                                    Long v1_7 = Long.valueOf(Utilities.random.nextLong());
                                    v8.put(((MessageObject)v0_1).messageOwner.grouped_id, v1_7);
                                }

                                ((Message)v15).grouped_id = ((Long)v1_6).longValue();
                                ((Message)v15).flags |= 131072;
                            }
                            else {
                                v35 = v9;
                            }

                            if(v6 != arg44.size() - 1) {
                                v37 = v8;
                                if(v12.get(v6 + 1).messageOwner.grouped_id != ((MessageObject)v0_1).messageOwner.grouped_id) {
                                    v10 = v21;
                                    v1_2 = 1;
                                }
                                else {
                                    goto label_378;
                                }
                            }
                            else {
                                v37 = v8;
                            label_378:
                                v10 = v21;
                                v1_2 = 0;
                            }

                            if(v10.channel_id == 0 || (v19)) {
                                ((Message)v15).from_id = UserConfig.getInstance(v11.currentAccount).getClientUserId();
                                ((Message)v15).flags |= 256;
                            }
                            else {
                                int v3_2 = v20 ? UserConfig.getInstance(v11.currentAccount).getClientUserId() : -v10.channel_id;
                                ((Message)v15).from_id = v3_2;
                                ((Message)v15).post = true;
                            }

                            if(((Message)v15).random_id == v25) {
                                ((Message)v15).random_id = this.getNextRandomId();
                            }

                            v2_1.add(Long.valueOf(((Message)v15).random_id));
                            v4_2.put(((Message)v15).random_id, v15);
                            v5_2.add(Integer.valueOf(((Message)v15).fwd_msg_id));
                            ((Message)v15).date = ConnectionsManager.getInstance(v11.currentAccount).getCurrentTime();
                            v7 = v31;
                            v3 = v7 instanceof TL_inputPeerChannel;
                            if(!v3 || (v19)) {
                                if((((MessageObject)v0_1).messageOwner.flags & 1024) != 0) {
                                    ((Message)v15).views = ((MessageObject)v0_1).messageOwner.views;
                                    ((Message)v15).flags |= 1024;
                                }

                                ((Message)v15).unread = true;
                            }
                            else {
                                ((Message)v15).views = 1;
                                ((Message)v15).flags |= 1024;
                            }

                            ((Message)v15).dialog_id = v13;
                            ((Message)v15).to_id = v10;
                            if((MessageObject.isVoiceMessage(((Message)v15))) || (MessageObject.isRoundVideoMessage(((Message)v15)))) {
                                v3 = !v3 || ((MessageObject)v0_1).getChannelId() == 0 ? true : ((MessageObject)v0_1).isContentUnread();
                                ((Message)v15).media_unread = v3;
                            }

                            if((((MessageObject)v0_1).messageOwner.to_id instanceof TL_peerChannel)) {
                                ((Message)v15).ttl = -((MessageObject)v0_1).messageOwner.to_id.channel_id;
                            }

                            MessageObject v3_3 = new MessageObject(v11.currentAccount, ((Message)v15), true);
                            v3_3.messageOwner.send_state = 1;
                            ArrayList v8_1 = v30;
                            v8_1.add(v3_3);
                            v3_1 = v29_1;
                            v3_1.add(v15);
                            v11.putToSendingMessages(((Message)v15));
                            if(BuildVars.LOGS_ENABLED) {
                                StringBuilder v9_1 = new StringBuilder();
                                v9_1.append("forward message user_id = ");
                                v9_1.append(v7.user_id);
                                v9_1.append(" chat_id = ");
                                v9_1.append(v7.chat_id);
                                v9_1.append(" channel_id = ");
                                v9_1.append(v7.channel_id);
                                v9_1.append(" access_hash = ");
                                v39 = v4_2;
                                v38 = v5_2;
                                v9_1.append(v7.access_hash);
                                FileLog.d(v9_1.toString());
                            }
                            else {
                                v39 = v4_2;
                                v38 = v5_2;
                            }

                            if((v1_2 == 0 || v3_1.size() <= 0) && (v3_1.size() != 100 && v6 != arg44.size() - 1) && (v6 == arg44.size() - 1 || v12.get(v6 + 1).getDialogId() == ((MessageObject)v0_1).getDialogId())) {
                                v22 = v2_1;
                                v21_1 = v3_1;
                                v13_1 = v6;
                                v40 = v7;
                                v25_1 = v8_1;
                                v32 = v10;
                                v29 = v33;
                                v28 = v37;
                                v27 = v38;
                                v26 = v39;
                                goto label_677;
                            }

                            MessagesStorage.getInstance(v11.currentAccount).putMessages(new ArrayList(((Collection)v3_1)), false, true, false, 0);
                            MessagesController.getInstance(v11.currentAccount).updateInterfaceWithMessages(v13, v8_1);
                            NotificationCenter.getInstance(v11.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
                            UserConfig.getInstance(v11.currentAccount).saveConfig(false);
                            TL_messages_forwardMessages v15_1 = new TL_messages_forwardMessages();
                            v15_1.to_peer = v7;
                            boolean v1_8 = v35 != v25 ? true : false;
                            v15_1.grouped = v1_8;
                            if((v15_1.to_peer instanceof TL_inputPeerChannel)) {
                                SharedPreferences v1_9 = MessagesController.getNotificationsSettings(v11.currentAccount);
                                StringBuilder v4_3 = new StringBuilder();
                                v4_3.append("silent_");
                                v4_3.append(v13);
                                v15_1.silent = v1_9.getBoolean(v4_3.toString(), false);
                            }

                            if((((MessageObject)v0_1).messageOwner.to_id instanceof TL_peerChannel)) {
                                v1 = MessagesController.getInstance(v11.currentAccount).getChat(Integer.valueOf(((MessageObject)v0_1).messageOwner.to_id.channel_id));
                                v15_1.from_peer = new TL_inputPeerChannel();
                                v15_1.from_peer.channel_id = ((MessageObject)v0_1).messageOwner.to_id.channel_id;
                                if(v1 != null) {
                                    v15_1.from_peer.access_hash = v1.access_hash;
                                }
                            }
                            else {
                                v15_1.from_peer = new TL_inputPeerEmpty();
                            }

                            v15_1.random_id = v2_1;
                            v5_2 = v38;
                            v15_1.id = v5_2;
                            boolean v0_2 = arg44.size() != 1 || !v12.get(0).messageOwner.with_my_score ? false : true;
                            v15_1.with_my_score = v0_2;
                            v21_1 = v3_1;
                            v22 = v2_1;
                            v25_1 = v8_1;
                            ConnectionsManager v11_1 = ConnectionsManager.getInstance(v11.currentAccount);
                            v26 = v39;
                            v27 = v5_2;
                            v13_1 = v6;
                            v40 = v7;
                            v28 = v37;
                            v29 = v33;
                            v32 = v10;
                            new -$$Lambda$SendMessagesHelper$GtPQ6DFMMI1Gm-S7QANSsM7url8(this, arg45, v19, v23, v26, v21_1, v25_1, v10, v15_1);
                            v11_1.sendRequest(((TLObject)v15_1), null, 68);
                            if(v13_1 == arg44.size() - 1) {
                                goto label_677;
                            }

                            v3_1 = new ArrayList();
                            v1_1 = new ArrayList();
                            ArrayList v0_3 = new ArrayList();
                            v2_1 = new ArrayList();
                            v4_2 = new LongSparseArray();
                            v5_2 = v2_1;
                            v2_1 = v0_3;
                        }

                    label_682:
                        v6 = v13_1 + 1;
                        v8 = v28;
                        v9 = v29;
                        v21 = v32;
                        v7 = v40;
                        v11 = this;
                        v13 = arg45;
                    }
                }
                else {
                    for(v0 = 0; v0 < arg44.size(); ++v0) {
                        this.processForwardFromMyName(v12.get(v0), arg45);
                    }

                    v24 = 0;
                }

                return v24;
            }
        }

        return 0;
    }

    public void sendMessage(MessageObject arg21) {
        this.sendMessage(null, null, null, null, null, null, null, null, arg21.getDialogId(), arg21.messageOwner.attachPath, null, null, true, arg21, null, arg21.messageOwner.reply_markup, arg21.messageOwner.params, 0);
    }

    public void sendNotificationCallback(long arg8, int arg10, byte[] arg11) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SendMessagesHelper$HbRNU4Jc_Y0XpAaKW1pVIkZttzI(this, arg8, arg10, arg11));
    }

    private void sendReadyToSendGroup(DelayedMessage arg9, boolean arg10, boolean arg11) {
        TLObject v10;
        if(arg9.messageObjects.isEmpty()) {
            arg9.markAsError();
            return;
        }

        String v0_1 = "group_" + arg9.groupId;
        if(arg9.finalGroupMessage != arg9.messageObjects.get(arg9.messageObjects.size() - 1).getId()) {
            if(arg10) {
                this.putToDelayedMessages(v0_1, arg9);
            }

            return;
        }

        int v1 = 0;
        if(arg10) {
            this.delayedMessages.remove(v0_1);
            MessagesStorage.getInstance(this.currentAccount).putMessages(arg9.messages, false, true, false, 0);
            MessagesController.getInstance(this.currentAccount).updateInterfaceWithMessages(arg9.peer, arg9.messageObjects);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }

        if((arg9.sendRequest instanceof TL_messages_sendMultiMedia)) {
            v10 = arg9.sendRequest;
            while(true) {
                if(v1 < ((TL_messages_sendMultiMedia)v10).multi_media.size()) {
                    InputMedia v0_2 = ((TL_messages_sendMultiMedia)v10).multi_media.get(v1).media;
                    if(!(v0_2 instanceof TL_inputMediaUploadedPhoto)) {
                        if((v0_2 instanceof TL_inputMediaUploadedDocument)) {
                        }
                        else {
                            ++v1;
                            continue;
                        }
                    }

                    return;
                }
                else {
                    goto label_63;
                }
            }

            return;
        label_63:
            if(!arg11) {
                goto label_89;
            }

            DelayedMessage v10_1 = this.findMaxDelayedMessageForMessageId(arg9.finalGroupMessage, arg9.peer);
            if(v10_1 == null) {
                goto label_89;
            }

            v10_1.addDelayedRequest(arg9.sendRequest, arg9.messageObjects, arg9.originalPaths);
            if(arg9.requests != null) {
                v10_1.requests.addAll(arg9.requests);
            }

            return;
        }
        else {
            v10 = arg9.sendEncryptedRequest;
            while(v1 < ((TL_messages_sendEncryptedMultiMedia)v10).files.size()) {
                if((((TL_messages_sendEncryptedMultiMedia)v10).files.get(v1) instanceof TL_inputEncryptedFile)) {
                    return;
                }

                ++v1;
            }
        }

    label_89:
        if((arg9.sendRequest instanceof TL_messages_sendMultiMedia)) {
            this.performSendMessageRequestMulti(arg9.sendRequest, arg9.messageObjects, arg9.originalPaths);
        }
        else {
            SecretChatHelper.getInstance(this.currentAccount).performSendEncryptedRequest(arg9.sendEncryptedRequest, arg9);
        }

        arg9.sendDelayedRequests();
    }

    public void sendScreenshotMessage(User arg10, int arg11, Message arg12) {
        TL_messageService v12;
        if(arg10 != null && arg11 != 0) {
            if(arg10.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
            }
            else {
                TL_messages_sendScreenshotNotification v0 = new TL_messages_sendScreenshotNotification();
                v0.peer = new TL_inputPeerUser();
                v0.peer.access_hash = arg10.access_hash;
                v0.peer.user_id = arg10.id;
                if(arg12 != null) {
                    v0.reply_to_msg_id = arg11;
                    v0.random_id = arg12.random_id;
                }
                else {
                    v12 = new TL_messageService();
                    ((Message)v12).random_id = this.getNextRandomId();
                    ((Message)v12).dialog_id = ((long)arg10.id);
                    ((Message)v12).unread = true;
                    ((Message)v12).out = true;
                    int v3 = UserConfig.getInstance(this.currentAccount).getNewMessageId();
                    ((Message)v12).id = v3;
                    ((Message)v12).local_id = v3;
                    ((Message)v12).from_id = UserConfig.getInstance(this.currentAccount).getClientUserId();
                    ((Message)v12).flags |= 256;
                    ((Message)v12).flags |= 8;
                    ((Message)v12).reply_to_msg_id = arg11;
                    ((Message)v12).to_id = new TL_peerUser();
                    ((Message)v12).to_id.user_id = arg10.id;
                    ((Message)v12).date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
                    ((Message)v12).action = new TL_messageActionScreenshotTaken();
                    UserConfig.getInstance(this.currentAccount).saveConfig(false);
                }

                v0.random_id = ((Message)v12).random_id;
                MessageObject v10 = new MessageObject(this.currentAccount, ((Message)v12), false);
                v10.messageOwner.send_state = 1;
                ArrayList v11 = new ArrayList();
                v11.add(v10);
                MessagesController.getInstance(this.currentAccount).updateInterfaceWithMessages(((Message)v12).dialog_id, v11);
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
                ArrayList v4 = new ArrayList();
                v4.add(v12);
                MessagesStorage.getInstance(this.currentAccount).putMessages(v4, false, true, false, 0);
                this.performSendMessageRequest(((TLObject)v0), v10, null);
            }
        }
    }

    public void sendSticker(Document arg17, long arg18, MessageObject arg20, Context arg21) {
        SendMessagesHelper v13 = this;
        Document v0 = arg17;
        long v14 = arg18;
        if(v0 == null) {
            return;
        }

        if((((int)v14)) == 0) {
            if(MessagesController.getInstance(v13.currentAccount).getEncryptedChat(Integer.valueOf(((int)(v14 >> 32)))) == null) {
                return;
            }

            TL_document v1 = new TL_document();
            v1.id = v0.id;
            v1.access_hash = v0.access_hash;
            v1.date = v0.date;
            v1.mime_type = v0.mime_type;
            v1.size = v0.size;
            v1.dc_id = v0.dc_id;
            v1.attributes = new ArrayList(v0.attributes);
            if(v1.mime_type == null) {
                v1.mime_type = "";
            }

            if((v0.thumb instanceof TL_photoSize)) {
                File v3 = FileLoader.getPathToAttach(v0.thumb, true);
                if(!v3.exists()) {
                    goto label_82;
                }

                try {
                    v3.length();
                    byte[] v4 = new byte[((int)v3.length())];
                    new RandomAccessFile(v3, "r").readFully(v4);
                    v1.thumb = new TL_photoCachedSize();
                    v1.thumb.location = v0.thumb.location;
                    v1.thumb.size = v0.thumb.size;
                    v1.thumb.w = v0.thumb.w;
                    v1.thumb.h = v0.thumb.h;
                    v1.thumb.type = v0.thumb.type;
                    v1.thumb.bytes = v4;
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }

        label_82:
            if(v1.thumb == null) {
                v1.thumb = new TL_photoSizeEmpty();
                v1.thumb.type = "s";
            }

            TL_document v0_2 = v1;
        }

        if((v0 instanceof TL_document)) {
            if(!ApplicationLoader.applicationContext.getSharedPreferences("plusconfig", 0).getBoolean("confirmForStickers", true)) {
                goto label_127;
            }

            try {
                AlertDialog$Builder v7 = new AlertDialog$Builder(arg21);
                v7.setTitle(LocaleController.getString("MyAppName", 2131625224));
                v7.setMessage("برای ارسال مطمینید؟").setCancelable(true).setPositiveButton("بله", new DialogInterface$OnClickListener(v0, arg18, arg20) {
                    public void onClick(DialogInterface arg13, int arg14) {
                        SendMessagesHelper.this.sendMessage(this.val$finalDocument, null, null, this.val$peer, this.val$replyingMessageObject, null, null, null, null, 0);
                    }
                }).setNegativeButton("خیر", new DialogInterface$OnClickListener() {
                    public void onClick(DialogInterface arg1, int arg2) {
                        arg1.cancel();
                    }
                });
                v7.create().show();
                return;
            label_127:
                this.sendMessage(v0, null, null, arg18, arg20, null, null, null, null, 0);
            }
            catch(Exception ) {
                this.sendMessage(v0, null, null, arg18, arg20, null, null, null, null, 0);
            }
        }
    }

    public void setCurrentChatInfo(ChatFull arg1) {
        this.currentChatInfo = arg1;
    }

    protected void stopVideoService(String arg3) {
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SendMessagesHelper$ILu6hLcZoS2lx77eTUYVnVC5uqs(this, arg3));
    }

    private void updateMediaPaths(MessageObject arg17, Message arg18, String arg19, boolean arg20) {
        File v7_1;
        File v14;
        int v6;
        Object v7_3;
        SendMessagesHelper v0 = this;
        MessageObject v1 = arg17;
        Message v2 = arg18;
        String v3 = arg19;
        boolean v4 = arg20;
        Message v5 = v1.messageOwner;
        if(v2 == null) {
            return;
        }

        long v7 = -2147483648;
        int v9 = 4;
        if(!(v2.media instanceof TL_messageMediaPhoto) || v2.media.photo == null || !(v5.media instanceof TL_messageMediaPhoto) || v5.media.photo == null) {
            if(((v2.media instanceof TL_messageMediaDocument)) && v2.media.document != null && ((v5.media instanceof TL_messageMediaDocument)) && v5.media.document != null) {
                if(MessageObject.isVideoMessage(arg18)) {
                    if(v2.media.ttl_seconds == 0) {
                        MessagesStorage.getInstance(v0.currentAccount).putSentFile(v3, v2.media.document, 2);
                    }

                    v2.attachPath = v5.attachPath;
                }
                else {
                    if(MessageObject.isVoiceMessage(arg18)) {
                        goto label_228;
                    }

                    if(MessageObject.isRoundVideoMessage(arg18)) {
                        goto label_228;
                    }

                    if(v2.media.ttl_seconds != 0) {
                        goto label_228;
                    }

                    MessagesStorage.getInstance(v0.currentAccount).putSentFile(v3, v2.media.document, 1);
                }

            label_228:
                PhotoSize v6_3 = v5.media.document.thumb;
                PhotoSize v7_2 = v2.media.document.thumb;
                if(v6_3 == null || v6_3.location == null || v6_3.location.volume_id != -2147483648 || v7_2 == null || v7_2.location == null || ((v7_2 instanceof TL_photoSizeEmpty)) || ((v6_3 instanceof TL_photoSizeEmpty))) {
                    if(v6_3 != null && (MessageObject.isStickerMessage(arg18)) && v6_3.location != null) {
                        v7_2.location = v6_3.location;
                        goto label_319;
                    }

                    if((v6_3 == null || !(v6_3.location instanceof TL_fileLocationUnavailable)) && !(v6_3 instanceof TL_photoSizeEmpty)) {
                        goto label_319;
                    }

                    v5.media.document.thumb = v2.media.document.thumb;
                }
                else {
                    String v8_3 = v6_3.location.volume_id + "_" + v6_3.location.local_id;
                    String v11_1 = v7_2.location.volume_id + "_" + v7_2.location.local_id;
                    if(!v8_3.equals(v11_1)) {
                        File v13_2 = FileLoader.getDirectory(v9);
                        StringBuilder v14_1 = new StringBuilder();
                        v14_1.append(v8_3);
                        v14_1.append(".jpg");
                        File v12_1 = new File(v13_2, v14_1.toString());
                        v14 = FileLoader.getDirectory(v9);
                        StringBuilder v15_1 = new StringBuilder();
                        v15_1.append(v11_1);
                        v15_1.append(".jpg");
                        v12_1.renameTo(new File(v14, v15_1.toString()));
                        ImageLoader.getInstance().replaceImageInCache(v8_3, v11_1, v7_2.location, v4);
                        v6_3.location = v7_2.location;
                        v6_3.size = v7_2.size;
                    }
                }

            label_319:
                v5.media.document.dc_id = v2.media.document.dc_id;
                v5.media.document.id = v2.media.document.id;
                v5.media.document.access_hash = v2.media.document.access_hash;
                byte[] v4_1 = null;
                v6 = 0;
                while(v6 < v5.media.document.attributes.size()) {
                    v7_3 = v5.media.document.attributes.get(v6);
                    if((v7_3 instanceof TL_documentAttributeAudio)) {
                        v4_1 = ((DocumentAttribute)v7_3).waveform;
                    }
                    else {
                        ++v6;
                        continue;
                    }

                    break;
                }

                v5.media.document.attributes = v2.media.document.attributes;
                if(v4_1 != null) {
                    for(v6 = 0; v6 < v5.media.document.attributes.size(); ++v6) {
                        v7_3 = v5.media.document.attributes.get(v6);
                        if((v7_3 instanceof TL_documentAttributeAudio)) {
                            ((DocumentAttribute)v7_3).waveform = v4_1;
                            ((DocumentAttribute)v7_3).flags |= v9;
                        }
                    }
                }

                v5.media.document.size = v2.media.document.size;
                v5.media.document.mime_type = v2.media.document.mime_type;
                if((v2.flags & v9) == 0 && (MessageObject.isOut(arg18))) {
                    if(MessageObject.isNewGifDocument(v2.media.document)) {
                        DataQuery.getInstance(v0.currentAccount).addRecentGif(v2.media.document, v2.date);
                    }
                    else if(MessageObject.isStickerDocument(v2.media.document)) {
                        DataQuery.getInstance(v0.currentAccount).addRecentSticker(0, v2.media.document, v2.date, false);
                    }
                }

                if(v5.attachPath != null && (v5.attachPath.startsWith(FileLoader.getDirectory(v9).getAbsolutePath()))) {
                    File v4_2 = new File(v5.attachPath);
                    Document v6_4 = v2.media.document;
                    boolean v7_4 = v2.media.ttl_seconds != 0 ? true : false;
                    File v6_5 = FileLoader.getPathToAttach(((TLObject)v6_4), v7_4);
                    if(!v4_2.renameTo(v6_5)) {
                        goto label_439;
                    }

                    if(MessageObject.isVideoMessage(arg18)) {
                        v1.attachPathExists = true;
                        return;
                    }

                    v1.mediaExists = v1.attachPathExists;
                    v1.attachPathExists = false;
                    v5.attachPath = "";
                    if(v3 == null) {
                        return;
                    }

                    if(!v3.startsWith("http")) {
                        return;
                    }

                    MessagesStorage.getInstance(v0.currentAccount).addRecentLocalFile(v3, v6_5.toString(), v5.media.document);
                    return;
                }

            label_439:
                v2.attachPath = v5.attachPath;
                v2.message = v5.message;
                return;
            }

            if(!(v2.media instanceof TL_messageMediaContact) || !(v5.media instanceof TL_messageMediaContact)) {
                if((v2.media instanceof TL_messageMediaWebPage)) {
                }
                else {
                    goto label_479;
                }
            }

            v5.media = v2.media;
            return;
        label_479:
            if((v2.media instanceof TL_messageMediaGeo)) {
                v2.media.geo.lat = v5.media.geo.lat;
                v2.media.geo._long = v5.media.geo._long;
                return;
            }

            if(!(v2.media instanceof TL_messageMediaGame)) {
                return;
            }

            v5.media = v2.media;
            if(!(v5.media instanceof TL_messageMediaGame)) {
                return;
            }

            if(TextUtils.isEmpty(v2.message)) {
                return;
            }

            v5.entities = v2.entities;
            v5.message = v2.message;
        }
        else {
            if(v2.media.ttl_seconds == 0) {
                MessagesStorage.getInstance(v0.currentAccount).putSentFile(v3, v2.media.photo, 0);
            }

            if(v5.media.photo.sizes.size() != 1 || !(v5.media.photo.sizes.get(0).location instanceof TL_fileLocationUnavailable)) {
                int v1_1 = 0;
                while(v1_1 < v2.media.photo.sizes.size()) {
                    Object v3_1 = v2.media.photo.sizes.get(v1_1);
                    if(v3_1 != null && ((PhotoSize)v3_1).location != null && !(v3_1 instanceof TL_photoSizeEmpty)) {
                        if(((PhotoSize)v3_1).type == null) {
                        }
                        else {
                            v6 = 0;
                            while(v6 < v5.media.photo.sizes.size()) {
                                Object v12 = v5.media.photo.sizes.get(v6);
                                if(v12 != null && ((PhotoSize)v12).location != null) {
                                    if(((PhotoSize)v12).type == null) {
                                    }
                                    else {
                                        if(((PhotoSize)v12).location.volume_id != v7 || !((PhotoSize)v3_1).type.equals(((PhotoSize)v12).type)) {
                                            if(((PhotoSize)v3_1).w != ((PhotoSize)v12).w) {
                                            }
                                            else if(((PhotoSize)v3_1).h == ((PhotoSize)v12).h) {
                                                goto label_98;
                                            }

                                            goto label_165;
                                        }

                                    label_98:
                                        String v6_2 = ((PhotoSize)v12).location.volume_id + "_" + ((PhotoSize)v12).location.local_id;
                                        String v13_1 = ((PhotoSize)v3_1).location.volume_id + "_" + ((PhotoSize)v3_1).location.local_id;
                                        if(v6_2.equals(v13_1)) {
                                            break;
                                        }

                                        File v15 = FileLoader.getDirectory(v9);
                                        StringBuilder v11 = new StringBuilder();
                                        v11.append(v6_2);
                                        v11.append(".jpg");
                                        v14 = new File(v15, v11.toString());
                                        if(v2.media.ttl_seconds == 0) {
                                            if(v2.media.photo.sizes.size() != 1) {
                                                int v8 = 90;
                                                if(((PhotoSize)v3_1).w <= v8) {
                                                    if(((PhotoSize)v3_1).h > v8) {
                                                        goto label_145;
                                                    }

                                                    goto label_147;
                                                }
                                            }

                                        label_145:
                                            v7_1 = FileLoader.getPathToAttach(((TLObject)v3_1));
                                        }
                                        else {
                                        label_147:
                                            File v8_1 = FileLoader.getDirectory(v9);
                                            v11 = new StringBuilder();
                                            v11.append(v13_1);
                                            v11.append(".jpg");
                                            v7_1 = new File(v8_1, v11.toString());
                                        }

                                        v14.renameTo(v7_1);
                                        ImageLoader.getInstance().replaceImageInCache(v6_2, v13_1, ((PhotoSize)v3_1).location, v4);
                                        ((PhotoSize)v12).location = ((PhotoSize)v3_1).location;
                                        ((PhotoSize)v12).size = ((PhotoSize)v3_1).size;
                                        break;
                                    }
                                }

                            label_165:
                                ++v6;
                                v7 = -2147483648;
                            }
                        }
                    }

                    ++v1_1;
                    v7 = -2147483648;
                }
            }
            else {
                v5.media.photo.sizes = v2.media.photo.sizes;
            }

            v2.message = v5.message;
            v2.attachPath = v5.attachPath;
            v5.media.photo.id = v2.media.photo.id;
            v5.media.photo.access_hash = v2.media.photo.access_hash;
        }
    }

    private void uploadMultiMedia(DelayedMessage arg8, InputMedia arg9, InputEncryptedFile arg10, String arg11) {
        Object[] v2_1;
        int v5;
        int v0 = 2;
        float v1 = 1f;
        int v2 = 3;
        if(arg9 != null) {
            TLObject v10 = arg8.sendRequest;
            v5 = 0;
            while(v5 < ((TL_messages_sendMultiMedia)v10).multi_media.size()) {
                if(((TL_messages_sendMultiMedia)v10).multi_media.get(v5).media == arg9) {
                    this.putToSendingMessages(arg8.messages.get(v5));
                    NotificationCenter v10_1 = NotificationCenter.getInstance(this.currentAccount);
                    v5 = NotificationCenter.FileUploadProgressChanged;
                    v2_1 = new Object[v2];
                    v2_1[0] = arg11;
                    v2_1[1] = Float.valueOf(v1);
                    v2_1[v0] = Boolean.valueOf(false);
                    v10_1.postNotificationName(v5, v2_1);
                }
                else {
                    ++v5;
                    continue;
                }

                break;
            }

            TL_messages_uploadMedia v10_2 = new TL_messages_uploadMedia();
            v10_2.media = arg9;
            v10_2.peer = arg8.sendRequest.peer;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v10_2), new -$$Lambda$SendMessagesHelper$6rG3L4brQuIUzjg1gn78nAV6RBU(this, arg9, arg8));
        }
        else {
            if(arg10 == null) {
                return;
            }

            TLObject v9 = arg8.sendEncryptedRequest;
            v5 = 0;
            while(v5 < ((TL_messages_sendEncryptedMultiMedia)v9).files.size()) {
                if(((TL_messages_sendEncryptedMultiMedia)v9).files.get(v5) == arg10) {
                    this.putToSendingMessages(arg8.messages.get(v5));
                    NotificationCenter v9_1 = NotificationCenter.getInstance(this.currentAccount);
                    int v10_3 = NotificationCenter.FileUploadProgressChanged;
                    v2_1 = new Object[v2];
                    v2_1[0] = arg11;
                    v2_1[1] = Float.valueOf(v1);
                    v2_1[v0] = Boolean.valueOf(false);
                    v9_1.postNotificationName(v10_3, v2_1);
                }
                else {
                    ++v5;
                    continue;
                }

                break;
            }

            this.sendReadyToSendGroup(arg8, false, true);
        }
    }

    private void writePreviousMessageData(Message arg4, SerializedData arg5) {
        arg4.media.serializeToStream(((AbstractSerializedData)arg5));
        String v0 = arg4.message != null ? arg4.message : "";
        arg5.writeString(v0);
        v0 = arg4.attachPath != null ? arg4.attachPath : "";
        arg5.writeString(v0);
        int v0_1 = arg4.entities.size();
        arg5.writeInt32(v0_1);
        int v1;
        for(v1 = 0; v1 < v0_1; ++v1) {
            arg4.entities.get(v1).serializeToStream(((AbstractSerializedData)arg5));
        }
    }
}

