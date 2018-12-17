package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;

public class NotificationCenter {
    class org.telegram.messenger.NotificationCenter$1 {
    }

    class DelayedPost {
        private Object[] args;
        private int id;

        DelayedPost(NotificationCenter arg1, int arg2, Object[] arg3, org.telegram.messenger.NotificationCenter$1 arg4) {
            this(arg1, arg2, arg3);
        }

        private DelayedPost(NotificationCenter arg1, int arg2, Object[] arg3) {
            NotificationCenter.this = arg1;
            super();
            this.id = arg2;
            this.args = arg3;
        }

        static int access$000(DelayedPost arg0) {
            return arg0.id;
        }

        static Object[] access$100(DelayedPost arg0) {
            return arg0.args;
        }
    }

    public interface NotificationCenterDelegate {
        void didReceivedNotification(int arg1, int arg2, Object[] arg3);
    }

    public static final int DownloadServiceStart = 0;
    public static final int DownloadServiceStop = 0;
    public static final int FileDidFailUpload = 0;
    public static final int FileDidFailedLoad = 0;
    public static final int FileDidLoaded = 0;
    public static final int FileDidUpload = 0;
    public static final int FileLoadProgressChanged = 0;
    public static final int FileNewChunkAvailable = 0;
    public static final int FilePreparingFailed = 0;
    public static final int FilePreparingStarted = 0;
    public static final int FileUploadProgressChanged = 0;
    private static volatile NotificationCenter[] Instance = null;
    private SparseArray addAfterBroadcast;
    public static final int albumsDidLoaded = 0;
    private int[] allowedNotifications;
    private boolean animationInProgress;
    public static final int appDidLogout = 0;
    public static final int archivedStickersCountDidLoaded = 0;
    public static final int audioDidSent = 0;
    public static final int audioRouteChanged = 0;
    public static final int blockedUsersDidLoaded = 0;
    public static final int botInfoDidLoaded = 0;
    public static final int botKeyboardDidLoaded = 0;
    private int broadcasting;
    public static final int cameraInitied = 0;
    public static final int channelRightsUpdated = 0;
    public static final int chatDidCreated = 0;
    public static final int chatDidFailCreate = 0;
    public static final int chatInfoCantLoad = 0;
    public static final int chatInfoDidLoaded = 0;
    public static final int chatSearchResultsAvailable = 0;
    public static final int chatSearchResultsLoading = 0;
    public static final int closeChats = 0;
    public static final int closeInCallActivity = 0;
    public static final int closeOtherAppActivities = 0;
    public static final int closeSearchByActiveAction = 0;
    public static final int contactsDidLoaded = 0;
    public static final int contactsImported = 0;
    private int currentAccount;
    private ArrayList delayedPosts;
    public static final int dialogPhotosLoaded = 0;
    public static final int dialogsNeedReload = 0;
    public static final int dialogsUnreadCounterChanged = 0;
    public static final int didCreatedNewDeleteTask = 0;
    public static final int didEndedCall = 0;
    public static final int didLoadedPinnedMessage = 0;
    public static final int didLoadedReplyMessages = 0;
    public static final int didReceiveCall = 0;
    public static final int didReceiveSmsCode = 0;
    public static final int didReceivedNewMessages = 0;
    public static final int didReceivedWebpages = 0;
    public static final int didReceivedWebpagesInUpdates = 0;
    public static final int didRemovedTwoStepPassword = 0;
    public static final int didReplacedPhotoInMemCache = 0;
    public static final int didSetNewTheme = 0;
    public static final int didSetNewWallpapper = 0;
    public static final int didSetPasscode = 0;
    public static final int didSetTwoStepPassword = 0;
    public static final int didStartedCall = 0;
    public static final int didUpdatedConnectionState = 0;
    public static final int didUpdatedMessagesViews = 0;
    public static final int emojiDidLoaded = 0;
    public static final int encryptedChatCreated = 0;
    public static final int encryptedChatUpdated = 0;
    public static final int featuredStickersDidLoaded = 0;
    public static final int finishedDownloadingFromQueue = 0;
    private static volatile NotificationCenter globalInstance = null;
    public static final int goToPayment = 0;
    public static final int groupStickersDidLoaded = 0;
    public static final int hasNewContactsToImport = 0;
    public static final int historyCleared = 0;
    public static final int httpFileDidFailedLoad = 0;
    public static final int httpFileDidLoaded = 0;
    public static final int isDownloadingFromQueue = 0;
    public static final int isFilter = 0;
    public static final int liveLocationsCacheChanged = 0;
    public static final int liveLocationsChanged = 0;
    public static final int locationPermissionGranted = 0;
    public static final int mainUserInfoChanged = 0;
    public static final int mediaCountDidLoaded = 0;
    public static final int mediaDidLoaded = 0;
    public static final int messagePlayingDidReset = 0;
    public static final int messagePlayingDidSeek = 0;
    public static final int messagePlayingDidStarted = 0;
    public static final int messagePlayingPlayStateChanged = 0;
    public static final int messagePlayingProgressDidChanged = 0;
    public static final int messageReceivedByAck = 0;
    public static final int messageReceivedByServer = 0;
    public static final int messageSendError = 0;
    public static final int messageThumbGenerated = 0;
    public static final int messagesDeleted = 0;
    public static final int messagesDidLoaded = 0;
    public static final int messagesRead = 0;
    public static final int messagesReadContent = 0;
    public static final int messagesReadEncrypted = 0;
    public static final int musicDidLoaded = 0;
    public static final int needReloadArchivedStickers = 0;
    public static final int needReloadRecentDialogsSearch = 0;
    public static final int needSetDayNightTheme = 0;
    public static final int needShowAlert = 0;
    public static final int newDraftReceived = 0;
    public static final int newSessionReceived = 0;
    public static final int notificationsCountUpdated = 0;
    public static final int notificationsSettingsUpdated = 0;
    private SparseArray observers;
    public static final int openArticle = 0;
    public static final int openedChatChanged = 0;
    public static final int paymentFinished = 0;
    public static final int paymentSuccessMessage = 0;
    public static final int peerSettingsDidLoaded = 0;
    public static final int playerDidStartPlaying = 0;
    public static final int privacyRulesUpdated = 0;
    public static final int proxyCheckDone = 0;
    public static final int proxySettingsChanged = 0;
    public static final int pushMessagesUpdated = 0;
    public static final int recentDocumentsDidLoaded = 0;
    public static final int recentImagesDidLoaded = 0;
    public static final int recordProgressChanged = 0;
    public static final int recordStartError = 0;
    public static final int recordStarted = 0;
    public static final int recordStopped = 0;
    public static final int refreshTabs = 0;
    public static final int reloadHints = 0;
    public static final int reloadInlineHints = 0;
    public static final int reloadInterface = 0;
    private SparseArray removeAfterBroadcast;
    public static final int removeAllMessagesFromDialog = 0;
    public static final int replaceMessagesObjects = 0;
    public static final int screenshotTook = 0;
    public static final int stickersDidLoaded = 0;
    public static final int stopEncodingService = 0;
    public static final int suggestedLangpack = 0;
    private static int totalEvents = 1;
    public static final int updateInterfaces;
    public static final int updateLog;
    public static final int updateMentionsCount;
    public static final int updateMessageMedia;
    public static final int userInfoDidLoaded;
    public static final int wallpapersDidLoaded;
    public static final int wasUnableToFindCurrentLocation;

    static {
        int v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didReceivedNewMessages = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.updateInterfaces = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.dialogsNeedReload = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.closeChats = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagesDeleted = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.historyCleared = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagesRead = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagesDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messageReceivedByAck = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messageReceivedByServer = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messageSendError = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.contactsDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.contactsImported = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.hasNewContactsToImport = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.chatDidCreated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.chatDidFailCreate = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.chatInfoDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.chatInfoCantLoad = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.mediaDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.mediaCountDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.encryptedChatUpdated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagesReadEncrypted = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.encryptedChatCreated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.dialogPhotosLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.removeAllMessagesFromDialog = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.notificationsSettingsUpdated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.blockedUsersDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.openedChatChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didCreatedNewDeleteTask = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.mainUserInfoChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.privacyRulesUpdated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.updateMessageMedia = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.recentImagesDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.replaceMessagesObjects = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didSetPasscode = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didSetTwoStepPassword = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didRemovedTwoStepPassword = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didLoadedReplyMessages = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didLoadedPinnedMessage = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.newSessionReceived = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didReceivedWebpages = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didReceivedWebpagesInUpdates = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.stickersDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.featuredStickersDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.groupStickersDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagesReadContent = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.botInfoDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.userInfoDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.botKeyboardDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.chatSearchResultsAvailable = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.chatSearchResultsLoading = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.musicDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.needShowAlert = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didUpdatedMessagesViews = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.needReloadRecentDialogsSearch = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.peerSettingsDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.wasUnableToFindCurrentLocation = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.reloadHints = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.reloadInlineHints = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.newDraftReceived = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.recentDocumentsDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.needReloadArchivedStickers = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.archivedStickersCountDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.paymentFinished = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.channelRightsUpdated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.openArticle = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.updateMentionsCount = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.httpFileDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.httpFileDidFailedLoad = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didUpdatedConnectionState = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FileDidUpload = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FileDidFailUpload = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FileUploadProgressChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FileLoadProgressChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FileDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FileDidFailedLoad = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FilePreparingStarted = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FileNewChunkAvailable = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.FilePreparingFailed = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.dialogsUnreadCounterChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagePlayingProgressDidChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagePlayingDidReset = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagePlayingPlayStateChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagePlayingDidStarted = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messagePlayingDidSeek = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.recordProgressChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.recordStarted = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.recordStartError = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.recordStopped = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.screenshotTook = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.albumsDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.audioDidSent = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.audioRouteChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didStartedCall = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didEndedCall = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.closeInCallActivity = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.appDidLogout = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.pushMessagesUpdated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.stopEncodingService = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.wallpapersDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didReceiveSmsCode = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didReceiveCall = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.emojiDidLoaded = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.closeOtherAppActivities = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.cameraInitied = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didReplacedPhotoInMemCache = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.messageThumbGenerated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didSetNewTheme = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.needSetDayNightTheme = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.locationPermissionGranted = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.reloadInterface = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.suggestedLangpack = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.didSetNewWallpapper = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.proxySettingsChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.proxyCheckDone = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.liveLocationsChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.liveLocationsCacheChanged = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.notificationsCountUpdated = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.playerDidStartPlaying = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.closeSearchByActiveAction = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.isDownloadingFromQueue = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.finishedDownloadingFromQueue = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.refreshTabs = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.DownloadServiceStart = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.DownloadServiceStop = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.paymentSuccessMessage = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.goToPayment = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.isFilter = v0;
        v0 = NotificationCenter.totalEvents;
        NotificationCenter.totalEvents = v0 + 1;
        NotificationCenter.updateLog = v0;
        NotificationCenter.Instance = new NotificationCenter[3];
    }

    public NotificationCenter(int arg3) {
        super();
        this.observers = new SparseArray();
        this.removeAfterBroadcast = new SparseArray();
        this.addAfterBroadcast = new SparseArray();
        this.delayedPosts = new ArrayList(10);
        this.broadcasting = 0;
        this.currentAccount = arg3;
    }

    public void addObserver(Object arg3, int arg4) {
        ArrayList v0_1;
        Object v0;
        if(BuildVars.DEBUG_VERSION) {
            if(Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) {
            }
            else {
                throw new RuntimeException("addObserver allowed only from MAIN thread");
            }
        }

        if(this.broadcasting != 0) {
            v0 = this.addAfterBroadcast.get(arg4);
            if(v0 == null) {
                v0_1 = new ArrayList();
                this.addAfterBroadcast.put(arg4, v0_1);
            }

            v0_1.add(arg3);
            return;
        }

        v0 = this.observers.get(arg4);
        if(v0 == null) {
            SparseArray v0_2 = this.observers;
            ArrayList v1 = new ArrayList();
            v0_2.put(arg4, v1);
            v0_1 = v1;
        }

        if(v0_1.contains(arg3)) {
            return;
        }

        v0_1.add(arg3);
    }

    public static NotificationCenter getGlobalInstance() {
        NotificationCenter v0 = NotificationCenter.globalInstance;
        if(v0 == null) {
            Class v1 = NotificationCenter.class;
            __monitor_enter(v1);
            try {
                v0 = NotificationCenter.globalInstance;
                if(v0 == null) {
                    v0 = new NotificationCenter(-1);
                    NotificationCenter.globalInstance = v0;
                }

                __monitor_exit(v1);
                return v0;
            label_13:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_13;
            }

            throw v0_1;
        }

        return v0;
    }

    public static NotificationCenter getInstance(int arg3) {
        NotificationCenter v0 = NotificationCenter.Instance[arg3];
        if(v0 == null) {
            Class v1 = NotificationCenter.class;
            __monitor_enter(v1);
            try {
                v0 = NotificationCenter.Instance[arg3];
                if(v0 == null) {
                    NotificationCenter[] v0_1 = NotificationCenter.Instance;
                    NotificationCenter v2 = new NotificationCenter(arg3);
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

    public boolean isAnimationInProgress() {
        return this.animationInProgress;
    }

    public void postNotificationName(int arg4, Object[] arg5) {
        boolean v1 = false;
        if(this.allowedNotifications != null) {
            int v0 = 0;
            while(v0 < this.allowedNotifications.length) {
                if(this.allowedNotifications[v0] == arg4) {
                    v1 = true;
                }
                else {
                    ++v0;
                    continue;
                }

                break;
            }
        }

        this.postNotificationNameInternal(arg4, v1, arg5);
    }

    public void postNotificationNameInternal(int arg5, boolean arg6, Object[] arg7) {
        Object v7;
        int v6_2;
        if(BuildVars.DEBUG_VERSION) {
            if(Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) {
            }
            else {
                throw new RuntimeException("postNotificationName allowed only from MAIN thread");
            }
        }

        if(!arg6 && (this.animationInProgress)) {
            this.delayedPosts.add(new DelayedPost(this, arg5, arg7, null));
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("delay post notification " + arg5 + " with args count = " + arg7.length);
            }

            return;
        }

        ++this.broadcasting;
        Object v6_1 = this.observers.get(arg5);
        if(v6_1 != null && !((ArrayList)v6_1).isEmpty()) {
            int v1;
            for(v1 = 0; v1 < ((ArrayList)v6_1).size(); ++v1) {
                ((ArrayList)v6_1).get(v1).didReceivedNotification(arg5, this.currentAccount, arg7);
            }
        }

        --this.broadcasting;
        if(this.broadcasting == 0) {
            if(this.removeAfterBroadcast.size() != 0) {
                for(arg5 = 0; arg5 < this.removeAfterBroadcast.size(); ++arg5) {
                    v6_2 = this.removeAfterBroadcast.keyAt(arg5);
                    v7 = this.removeAfterBroadcast.get(v6_2);
                    for(v1 = 0; v1 < ((ArrayList)v7).size(); ++v1) {
                        this.removeObserver(((ArrayList)v7).get(v1), v6_2);
                    }
                }

                this.removeAfterBroadcast.clear();
            }

            if(this.addAfterBroadcast.size() == 0) {
                return;
            }

            for(arg5 = 0; arg5 < this.addAfterBroadcast.size(); ++arg5) {
                v6_2 = this.addAfterBroadcast.keyAt(arg5);
                v7 = this.addAfterBroadcast.get(v6_2);
                for(v1 = 0; v1 < ((ArrayList)v7).size(); ++v1) {
                    this.addObserver(((ArrayList)v7).get(v1), v6_2);
                }
            }

            this.addAfterBroadcast.clear();
        }
    }

    public void removeObserver(Object arg3, int arg4) {
        if(BuildVars.DEBUG_VERSION) {
            if(Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) {
            }
            else {
                throw new RuntimeException("removeObserver allowed only from MAIN thread");
            }
        }

        if(this.broadcasting != 0) {
            Object v0 = this.removeAfterBroadcast.get(arg4);
            if(v0 == null) {
                ArrayList v0_1 = new ArrayList();
                this.removeAfterBroadcast.put(arg4, v0_1);
            }

            ((ArrayList)v0).add(arg3);
            return;
        }

        Object v4 = this.observers.get(arg4);
        if(v4 != null) {
            ((ArrayList)v4).remove(arg3);
        }
    }

    public void setAllowedNotificationsDutingAnimation(int[] arg1) {
        this.allowedNotifications = arg1;
    }

    public void setAnimationInProgress(boolean arg4) {
        this.animationInProgress = arg4;
        if(!this.animationInProgress && !this.delayedPosts.isEmpty()) {
            int v4;
            for(v4 = 0; v4 < this.delayedPosts.size(); ++v4) {
                Object v0 = this.delayedPosts.get(v4);
                this.postNotificationNameInternal(DelayedPost.access$000(((DelayedPost)v0)), true, DelayedPost.access$100(((DelayedPost)v0)));
            }

            this.delayedPosts.clear();
        }
    }
}

