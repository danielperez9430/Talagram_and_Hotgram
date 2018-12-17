package org.telegram.messenger;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.widget.Toast;
import com.crashlytics.android.a.m;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.customization.Model.ChatTime;
import org.telegram.customization.Model.Favourite;
import org.telegram.customization.g.d;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$DialogPeer;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DraftMessage;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$ExportedChatInvite;
import org.telegram.tgnet.TLRPC$InputChannel;
import org.telegram.tgnet.TLRPC$InputChatPhoto;
import org.telegram.tgnet.TLRPC$InputDialogPeer;
import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$InputNotifyPeer;
import org.telegram.tgnet.TLRPC$InputPeer;
import org.telegram.tgnet.TLRPC$InputPhoto;
import org.telegram.tgnet.TLRPC$InputUser;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$PeerNotifySettings;
import org.telegram.tgnet.TLRPC$PhoneCall;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$SendMessageAction;
import org.telegram.tgnet.TLRPC$TL_account_getNotifySettings;
import org.telegram.tgnet.TLRPC$TL_account_registerDevice;
import org.telegram.tgnet.TLRPC$TL_account_unregisterDevice;
import org.telegram.tgnet.TLRPC$TL_account_updateStatus;
import org.telegram.tgnet.TLRPC$TL_auth_logOut;
import org.telegram.tgnet.TLRPC$TL_boolTrue;
import org.telegram.tgnet.TLRPC$TL_botInfo;
import org.telegram.tgnet.TLRPC$TL_channel;
import org.telegram.tgnet.TLRPC$TL_channelAdminRights;
import org.telegram.tgnet.TLRPC$TL_channelBannedRights;
import org.telegram.tgnet.TLRPC$TL_channelForbidden;
import org.telegram.tgnet.TLRPC$TL_channelMessagesFilterEmpty;
import org.telegram.tgnet.TLRPC$TL_channelParticipantSelf;
import org.telegram.tgnet.TLRPC$TL_channelParticipantsAdmins;
import org.telegram.tgnet.TLRPC$TL_channelParticipantsRecent;
import org.telegram.tgnet.TLRPC$TL_channels_channelParticipant;
import org.telegram.tgnet.TLRPC$TL_channels_channelParticipants;
import org.telegram.tgnet.TLRPC$TL_channels_createChannel;
import org.telegram.tgnet.TLRPC$TL_channels_deleteChannel;
import org.telegram.tgnet.TLRPC$TL_channels_deleteHistory;
import org.telegram.tgnet.TLRPC$TL_channels_deleteUserHistory;
import org.telegram.tgnet.TLRPC$TL_channels_editAbout;
import org.telegram.tgnet.TLRPC$TL_channels_editAdmin;
import org.telegram.tgnet.TLRPC$TL_channels_editBanned;
import org.telegram.tgnet.TLRPC$TL_channels_editPhoto;
import org.telegram.tgnet.TLRPC$TL_channels_editTitle;
import org.telegram.tgnet.TLRPC$TL_channels_getFullChannel;
import org.telegram.tgnet.TLRPC$TL_channels_getMessages;
import org.telegram.tgnet.TLRPC$TL_channels_getParticipant;
import org.telegram.tgnet.TLRPC$TL_channels_getParticipants;
import org.telegram.tgnet.TLRPC$TL_channels_inviteToChannel;
import org.telegram.tgnet.TLRPC$TL_channels_joinChannel;
import org.telegram.tgnet.TLRPC$TL_channels_leaveChannel;
import org.telegram.tgnet.TLRPC$TL_channels_readHistory;
import org.telegram.tgnet.TLRPC$TL_channels_readMessageContents;
import org.telegram.tgnet.TLRPC$TL_channels_toggleInvites;
import org.telegram.tgnet.TLRPC$TL_channels_togglePreHistoryHidden;
import org.telegram.tgnet.TLRPC$TL_channels_toggleSignatures;
import org.telegram.tgnet.TLRPC$TL_channels_updatePinnedMessage;
import org.telegram.tgnet.TLRPC$TL_channels_updateUsername;
import org.telegram.tgnet.TLRPC$TL_chat;
import org.telegram.tgnet.TLRPC$TL_chatFull;
import org.telegram.tgnet.TLRPC$TL_chatInviteEmpty;
import org.telegram.tgnet.TLRPC$TL_chatParticipant;
import org.telegram.tgnet.TLRPC$TL_chatParticipants;
import org.telegram.tgnet.TLRPC$TL_chatPhotoEmpty;
import org.telegram.tgnet.TLRPC$TL_config;
import org.telegram.tgnet.TLRPC$TL_contacts_block;
import org.telegram.tgnet.TLRPC$TL_contacts_getBlocked;
import org.telegram.tgnet.TLRPC$TL_contacts_resolveUsername;
import org.telegram.tgnet.TLRPC$TL_contacts_resolvedPeer;
import org.telegram.tgnet.TLRPC$TL_contacts_unblock;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_dialogPeer;
import org.telegram.tgnet.TLRPC$TL_documentEmpty;
import org.telegram.tgnet.TLRPC$TL_draftMessage;
import org.telegram.tgnet.TLRPC$TL_encryptedChat;
import org.telegram.tgnet.TLRPC$TL_encryptedChatRequested;
import org.telegram.tgnet.TLRPC$TL_encryptedChatWaiting;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_help_getAppChangelog;
import org.telegram.tgnet.TLRPC$TL_help_getProxyData;
import org.telegram.tgnet.TLRPC$TL_help_getRecentMeUrls;
import org.telegram.tgnet.TLRPC$TL_help_getTermsOfServiceUpdate;
import org.telegram.tgnet.TLRPC$TL_help_proxyDataEmpty;
import org.telegram.tgnet.TLRPC$TL_help_proxyDataPromo;
import org.telegram.tgnet.TLRPC$TL_help_recentMeUrls;
import org.telegram.tgnet.TLRPC$TL_help_termsOfServiceUpdate;
import org.telegram.tgnet.TLRPC$TL_help_termsOfServiceUpdateEmpty;
import org.telegram.tgnet.TLRPC$TL_inputChannel;
import org.telegram.tgnet.TLRPC$TL_inputChannelEmpty;
import org.telegram.tgnet.TLRPC$TL_inputChatPhotoEmpty;
import org.telegram.tgnet.TLRPC$TL_inputChatUploadedPhoto;
import org.telegram.tgnet.TLRPC$TL_inputDialogPeer;
import org.telegram.tgnet.TLRPC$TL_inputDocument;
import org.telegram.tgnet.TLRPC$TL_inputEncryptedChat;
import org.telegram.tgnet.TLRPC$TL_inputMessagesFilterChatPhotos;
import org.telegram.tgnet.TLRPC$TL_inputNotifyChats;
import org.telegram.tgnet.TLRPC$TL_inputNotifyUsers;
import org.telegram.tgnet.TLRPC$TL_inputPeerChannel;
import org.telegram.tgnet.TLRPC$TL_inputPeerChat;
import org.telegram.tgnet.TLRPC$TL_inputPeerEmpty;
import org.telegram.tgnet.TLRPC$TL_inputPeerUser;
import org.telegram.tgnet.TLRPC$TL_inputPhoneCall;
import org.telegram.tgnet.TLRPC$TL_inputPhotoEmpty;
import org.telegram.tgnet.TLRPC$TL_inputUser;
import org.telegram.tgnet.TLRPC$TL_inputUserEmpty;
import org.telegram.tgnet.TLRPC$TL_inputUserSelf;
import org.telegram.tgnet.TLRPC$TL_message;
import org.telegram.tgnet.TLRPC$TL_messageActionChannelCreate;
import org.telegram.tgnet.TLRPC$TL_messageActionChatAddUser;
import org.telegram.tgnet.TLRPC$TL_messageActionChatDeleteUser;
import org.telegram.tgnet.TLRPC$TL_messageActionChatMigrateTo;
import org.telegram.tgnet.TLRPC$TL_messageActionCreatedBroadcastList;
import org.telegram.tgnet.TLRPC$TL_messageActionHistoryClear;
import org.telegram.tgnet.TLRPC$TL_messageActionPinMessage;
import org.telegram.tgnet.TLRPC$TL_messageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_messageMediaEmpty;
import org.telegram.tgnet.TLRPC$TL_messageMediaUnsupported;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_messageService;
import org.telegram.tgnet.TLRPC$TL_messages_addChatUser;
import org.telegram.tgnet.TLRPC$TL_messages_affectedHistory;
import org.telegram.tgnet.TLRPC$TL_messages_affectedMessages;
import org.telegram.tgnet.TLRPC$TL_messages_channelMessages;
import org.telegram.tgnet.TLRPC$TL_messages_chatFull;
import org.telegram.tgnet.TLRPC$TL_messages_createChat;
import org.telegram.tgnet.TLRPC$TL_messages_deleteChatUser;
import org.telegram.tgnet.TLRPC$TL_messages_deleteHistory;
import org.telegram.tgnet.TLRPC$TL_messages_dialogs;
import org.telegram.tgnet.TLRPC$TL_messages_editChatAdmin;
import org.telegram.tgnet.TLRPC$TL_messages_editChatPhoto;
import org.telegram.tgnet.TLRPC$TL_messages_editChatTitle;
import org.telegram.tgnet.TLRPC$TL_messages_getDialogUnreadMarks;
import org.telegram.tgnet.TLRPC$TL_messages_getDialogs;
import org.telegram.tgnet.TLRPC$TL_messages_getFullChat;
import org.telegram.tgnet.TLRPC$TL_messages_getHistory;
import org.telegram.tgnet.TLRPC$TL_messages_getMessages;
import org.telegram.tgnet.TLRPC$TL_messages_getMessagesViews;
import org.telegram.tgnet.TLRPC$TL_messages_getPeerDialogs;
import org.telegram.tgnet.TLRPC$TL_messages_getPeerSettings;
import org.telegram.tgnet.TLRPC$TL_messages_getPinnedDialogs;
import org.telegram.tgnet.TLRPC$TL_messages_getUnreadMentions;
import org.telegram.tgnet.TLRPC$TL_messages_getWebPagePreview;
import org.telegram.tgnet.TLRPC$TL_messages_hideReportSpam;
import org.telegram.tgnet.TLRPC$TL_messages_markDialogUnread;
import org.telegram.tgnet.TLRPC$TL_messages_messages;
import org.telegram.tgnet.TLRPC$TL_messages_migrateChat;
import org.telegram.tgnet.TLRPC$TL_messages_peerDialogs;
import org.telegram.tgnet.TLRPC$TL_messages_readEncryptedHistory;
import org.telegram.tgnet.TLRPC$TL_messages_readHistory;
import org.telegram.tgnet.TLRPC$TL_messages_readMentions;
import org.telegram.tgnet.TLRPC$TL_messages_readMessageContents;
import org.telegram.tgnet.TLRPC$TL_messages_receivedQueue;
import org.telegram.tgnet.TLRPC$TL_messages_reportEncryptedSpam;
import org.telegram.tgnet.TLRPC$TL_messages_reportSpam;
import org.telegram.tgnet.TLRPC$TL_messages_saveGif;
import org.telegram.tgnet.TLRPC$TL_messages_saveRecentSticker;
import org.telegram.tgnet.TLRPC$TL_messages_search;
import org.telegram.tgnet.TLRPC$TL_messages_setEncryptedTyping;
import org.telegram.tgnet.TLRPC$TL_messages_setTyping;
import org.telegram.tgnet.TLRPC$TL_messages_startBot;
import org.telegram.tgnet.TLRPC$TL_messages_toggleChatAdmins;
import org.telegram.tgnet.TLRPC$TL_messages_toggleDialogPin;
import org.telegram.tgnet.TLRPC$TL_notifyChats;
import org.telegram.tgnet.TLRPC$TL_notifyPeer;
import org.telegram.tgnet.TLRPC$TL_notifyUsers;
import org.telegram.tgnet.TLRPC$TL_peerChannel;
import org.telegram.tgnet.TLRPC$TL_peerChat;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettings;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettingsEmpty_layer77;
import org.telegram.tgnet.TLRPC$TL_peerSettings;
import org.telegram.tgnet.TLRPC$TL_peerUser;
import org.telegram.tgnet.TLRPC$TL_phoneCallDiscardReasonBusy;
import org.telegram.tgnet.TLRPC$TL_phoneCallRequested;
import org.telegram.tgnet.TLRPC$TL_phone_discardCall;
import org.telegram.tgnet.TLRPC$TL_photoEmpty;
import org.telegram.tgnet.TLRPC$TL_photos_deletePhotos;
import org.telegram.tgnet.TLRPC$TL_photos_getUserPhotos;
import org.telegram.tgnet.TLRPC$TL_photos_photo;
import org.telegram.tgnet.TLRPC$TL_photos_photos;
import org.telegram.tgnet.TLRPC$TL_photos_updateProfilePhoto;
import org.telegram.tgnet.TLRPC$TL_photos_uploadProfilePhoto;
import org.telegram.tgnet.TLRPC$TL_privacyKeyChatInvite;
import org.telegram.tgnet.TLRPC$TL_privacyKeyPhoneCall;
import org.telegram.tgnet.TLRPC$TL_privacyKeyStatusTimestamp;
import org.telegram.tgnet.TLRPC$TL_replyKeyboardHide;
import org.telegram.tgnet.TLRPC$TL_sendMessageCancelAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageGamePlayAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageRecordAudioAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageRecordRoundAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageRecordVideoAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageTypingAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageUploadAudioAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageUploadDocumentAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageUploadPhotoAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageUploadRoundAction;
import org.telegram.tgnet.TLRPC$TL_sendMessageUploadVideoAction;
import org.telegram.tgnet.TLRPC$TL_updateChannel;
import org.telegram.tgnet.TLRPC$TL_updateChannelAvailableMessages;
import org.telegram.tgnet.TLRPC$TL_updateChannelMessageViews;
import org.telegram.tgnet.TLRPC$TL_updateChannelPinnedMessage;
import org.telegram.tgnet.TLRPC$TL_updateChannelReadMessagesContents;
import org.telegram.tgnet.TLRPC$TL_updateChannelTooLong;
import org.telegram.tgnet.TLRPC$TL_updateChannelWebPage;
import org.telegram.tgnet.TLRPC$TL_updateChatAdmins;
import org.telegram.tgnet.TLRPC$TL_updateContactsReset;
import org.telegram.tgnet.TLRPC$TL_updateDeleteChannelMessages;
import org.telegram.tgnet.TLRPC$TL_updateDeleteMessages;
import org.telegram.tgnet.TLRPC$TL_updateDialogPinned;
import org.telegram.tgnet.TLRPC$TL_updateDialogUnreadMark;
import org.telegram.tgnet.TLRPC$TL_updateDraftMessage;
import org.telegram.tgnet.TLRPC$TL_updateEditChannelMessage;
import org.telegram.tgnet.TLRPC$TL_updateEditMessage;
import org.telegram.tgnet.TLRPC$TL_updateFavedStickers;
import org.telegram.tgnet.TLRPC$TL_updateMessageID;
import org.telegram.tgnet.TLRPC$TL_updateNewChannelMessage;
import org.telegram.tgnet.TLRPC$TL_updateNewEncryptedMessage;
import org.telegram.tgnet.TLRPC$TL_updateNewMessage;
import org.telegram.tgnet.TLRPC$TL_updateNewStickerSet;
import org.telegram.tgnet.TLRPC$TL_updateNotifySettings;
import org.telegram.tgnet.TLRPC$TL_updatePhoneCall;
import org.telegram.tgnet.TLRPC$TL_updatePinnedDialogs;
import org.telegram.tgnet.TLRPC$TL_updatePrivacy;
import org.telegram.tgnet.TLRPC$TL_updateReadChannelInbox;
import org.telegram.tgnet.TLRPC$TL_updateReadChannelOutbox;
import org.telegram.tgnet.TLRPC$TL_updateReadFeaturedStickers;
import org.telegram.tgnet.TLRPC$TL_updateReadHistoryInbox;
import org.telegram.tgnet.TLRPC$TL_updateReadHistoryOutbox;
import org.telegram.tgnet.TLRPC$TL_updateReadMessagesContents;
import org.telegram.tgnet.TLRPC$TL_updateRecentStickers;
import org.telegram.tgnet.TLRPC$TL_updateSavedGifs;
import org.telegram.tgnet.TLRPC$TL_updateServiceNotification;
import org.telegram.tgnet.TLRPC$TL_updateShort;
import org.telegram.tgnet.TLRPC$TL_updateShortChatMessage;
import org.telegram.tgnet.TLRPC$TL_updateShortMessage;
import org.telegram.tgnet.TLRPC$TL_updateStickerSets;
import org.telegram.tgnet.TLRPC$TL_updateStickerSetsOrder;
import org.telegram.tgnet.TLRPC$TL_updateUserBlocked;
import org.telegram.tgnet.TLRPC$TL_updateUserName;
import org.telegram.tgnet.TLRPC$TL_updateUserPhone;
import org.telegram.tgnet.TLRPC$TL_updateUserPhoto;
import org.telegram.tgnet.TLRPC$TL_updateUserStatus;
import org.telegram.tgnet.TLRPC$TL_updateWebPage;
import org.telegram.tgnet.TLRPC$TL_updates;
import org.telegram.tgnet.TLRPC$TL_updatesCombined;
import org.telegram.tgnet.TLRPC$TL_updatesTooLong;
import org.telegram.tgnet.TLRPC$TL_updates_channelDifference;
import org.telegram.tgnet.TLRPC$TL_updates_channelDifferenceEmpty;
import org.telegram.tgnet.TLRPC$TL_updates_channelDifferenceTooLong;
import org.telegram.tgnet.TLRPC$TL_updates_difference;
import org.telegram.tgnet.TLRPC$TL_updates_differenceEmpty;
import org.telegram.tgnet.TLRPC$TL_updates_differenceSlice;
import org.telegram.tgnet.TLRPC$TL_updates_differenceTooLong;
import org.telegram.tgnet.TLRPC$TL_updates_getChannelDifference;
import org.telegram.tgnet.TLRPC$TL_updates_getDifference;
import org.telegram.tgnet.TLRPC$TL_updates_getState;
import org.telegram.tgnet.TLRPC$TL_updates_state;
import org.telegram.tgnet.TLRPC$TL_user;
import org.telegram.tgnet.TLRPC$TL_userForeign_old2;
import org.telegram.tgnet.TLRPC$TL_userFull;
import org.telegram.tgnet.TLRPC$TL_userProfilePhoto;
import org.telegram.tgnet.TLRPC$TL_userProfilePhotoEmpty;
import org.telegram.tgnet.TLRPC$TL_userStatusLastMonth;
import org.telegram.tgnet.TLRPC$TL_userStatusLastWeek;
import org.telegram.tgnet.TLRPC$TL_userStatusRecently;
import org.telegram.tgnet.TLRPC$TL_users_getFullUser;
import org.telegram.tgnet.TLRPC$TL_webPage;
import org.telegram.tgnet.TLRPC$TL_webPageEmpty;
import org.telegram.tgnet.TLRPC$TL_webPagePending;
import org.telegram.tgnet.TLRPC$TL_webPageUrlPending;
import org.telegram.tgnet.TLRPC$Update;
import org.telegram.tgnet.TLRPC$Updates;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$UserProfilePhoto;
import org.telegram.tgnet.TLRPC$UserStatus;
import org.telegram.tgnet.TLRPC$Vector;
import org.telegram.tgnet.TLRPC$WebPage;
import org.telegram.tgnet.TLRPC$contacts_Blocked;
import org.telegram.tgnet.TLRPC$messages_Dialogs;
import org.telegram.tgnet.TLRPC$messages_Messages;
import org.telegram.tgnet.TLRPC$photos_Photos;
import org.telegram.tgnet.TLRPC$updates_ChannelDifference;
import org.telegram.tgnet.TLRPC$updates_Difference;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.ProfileActivity;
import utils.a.b;

public class MessagesController implements NotificationCenterDelegate {
    class org.telegram.messenger.MessagesController$1 implements Runnable {
        org.telegram.messenger.MessagesController$1(MessagesController arg1) {
            MessagesController.this = arg1;
            super();
        }

        public void run() {
            UserConfig.getInstance(MessagesController.this.currentAccount).checkSavedPassword();
        }
    }

    class org.telegram.messenger.MessagesController$2 implements Comparator {
        org.telegram.messenger.MessagesController$2(MessagesController arg1) {
            MessagesController.this = arg1;
            super();
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((TL_dialog)arg1), ((TL_dialog)arg2));
        }

        public int compare(TL_dialog arg7, TL_dialog arg8) {
            if(!arg7.pinned && (arg8.pinned)) {
                return 1;
            }

            int v2 = -1;
            if((arg7.pinned) && !arg8.pinned) {
                return v2;
            }

            if((arg7.pinned) && (arg8.pinned)) {
                if(arg7.pinnedNum < arg8.pinnedNum) {
                    return 1;
                }
                else if(arg7.pinnedNum > arg8.pinnedNum) {
                    return v2;
                }
                else {
                    return 0;
                }
            }

            DraftMessage v0 = DataQuery.getInstance(MessagesController.this.currentAccount).getDraft(arg7.id);
            int v7 = v0 == null || v0.date < arg7.last_message_date ? arg7.last_message_date : v0.date;
            v0 = DataQuery.getInstance(MessagesController.this.currentAccount).getDraft(arg8.id);
            int v8 = v0 == null || v0.date < arg8.last_message_date ? arg8.last_message_date : v0.date;
            if(v7 < v8) {
                return 1;
            }

            if(v7 > v8) {
                return v2;
            }

            return 0;
        }
    }

    public class PrintingUser {
        public SendMessageAction action;
        public long lastTime;
        public int userId;

        public PrintingUser() {
            super();
        }
    }

    class ReadTask {
        public long dialogId;
        public int maxDate;
        public int maxId;
        public long sendRequestTime;

        ReadTask(MessagesController arg1, org.telegram.messenger.MessagesController$1 arg2) {
            this(arg1);
        }

        private ReadTask(MessagesController arg1) {
            MessagesController.this = arg1;
            super();
        }
    }

    class UserActionUpdatesPts extends Updates {
        UserActionUpdatesPts(MessagesController arg1, org.telegram.messenger.MessagesController$1 arg2) {
            this(arg1);
        }

        private UserActionUpdatesPts(MessagesController arg1) {
            MessagesController.this = arg1;
            super();
        }
    }

    class UserActionUpdatesSeq extends Updates {
        UserActionUpdatesSeq(MessagesController arg1, org.telegram.messenger.MessagesController$1 arg2) {
            this(arg1);
        }

        private UserActionUpdatesSeq(MessagesController arg1) {
            MessagesController.this = arg1;
            super();
        }
    }

    private static volatile MessagesController[] Instance = null;
    public static final int UPDATE_MASK_ALL = 1535;
    public static final int UPDATE_MASK_AVATAR = 2;
    public static final int UPDATE_MASK_CHANNEL = 8192;
    public static final int UPDATE_MASK_CHAT_ADMINS = 16384;
    public static final int UPDATE_MASK_CHAT_AVATAR = 8;
    public static final int UPDATE_MASK_CHAT_MEMBERS = 32;
    public static final int UPDATE_MASK_CHAT_NAME = 16;
    public static final int UPDATE_MASK_MESSAGE_TEXT = 32768;
    public static final int UPDATE_MASK_NAME = 1;
    public static final int UPDATE_MASK_NEW_MESSAGE = 2048;
    public static final int UPDATE_MASK_PHONE = 1024;
    public static final int UPDATE_MASK_READ_DIALOG_MESSAGE = 256;
    public static final int UPDATE_MASK_SELECT_DIALOG = 512;
    public static final int UPDATE_MASK_SEND_STATE = 4096;
    public static final int UPDATE_MASK_STATUS = 4;
    public static final int UPDATE_MASK_USER_PHONE = 128;
    public static final int UPDATE_MASK_USER_PRINT = 64;
    public int availableMapProviders;
    public boolean blockedCountry;
    public SparseIntArray blockedUsers;
    public int callConnectTimeout;
    public int callPacketTimeout;
    public int callReceiveTimeout;
    public int callRingTimeout;
    public boolean canRevokePmInbox;
    ArrayList categoriesIds;
    private SparseArray channelAdmins;
    private SparseArray channelViewsToSend;
    private SparseIntArray channelsPts;
    private ConcurrentHashMap chats;
    private SparseBooleanArray checkingLastMessagesDialogs;
    private boolean checkingProxyInfo;
    private int checkingProxyInfoRequestId;
    private boolean checkingTosUpdate;
    private ArrayList createdDialogIds;
    private ArrayList createdDialogMainThreadIds;
    private int currentAccount;
    private Runnable currentDeleteTaskRunnable;
    private int currentDeletingTaskChannelId;
    private ArrayList currentDeletingTaskMids;
    private int currentDeletingTaskTime;
    public String dcDomainName;
    public boolean defaultP2pContacts;
    private final Comparator dialogComparator;
    public LongSparseArray dialogMessage;
    public SparseArray dialogMessagesByIds;
    public LongSparseArray dialogMessagesByRandomIds;
    public ArrayList dialogs;
    public ArrayList dialogsAds;
    public ArrayList dialogsAll;
    public ArrayList dialogsBots;
    public ArrayList dialogsChannels;
    public boolean dialogsEndReached;
    public ArrayList dialogsFavs;
    public ArrayList dialogsForward;
    public ArrayList dialogsGroups;
    public ArrayList dialogsGroupsAll;
    public ArrayList dialogsGroupsOnly;
    public ArrayList dialogsHidden;
    public ArrayList dialogsMegaGroups;
    public ArrayList dialogsServerOnly;
    public ArrayList dialogsUnread;
    public ArrayList dialogsUsers;
    public LongSparseArray dialogs_dict;
    public ConcurrentHashMap dialogs_read_inbox_max;
    public ConcurrentHashMap dialogs_read_outbox_max;
    private SharedPreferences emojiPreferences;
    public boolean enableJoined;
    private ConcurrentHashMap encryptedChats;
    private SparseArray exportedChats;
    public boolean firstGettingTask;
    private SparseArray fullUsers;
    private boolean getDifferenceFirstSync;
    public boolean gettingDifference;
    private SparseBooleanArray gettingDifferenceChannels;
    private boolean gettingNewDeleteTask;
    private SparseBooleanArray gettingUnknownChannels;
    public String gifSearchBot;
    private List hiddensIds;
    public boolean hideJoinedGroup;
    public boolean hideLeftGroup;
    public ArrayList hintDialogs;
    public String imageSearchBot;
    private String installReferer;
    private boolean isLeftProxyChannel;
    private ArrayList joiningToChannels;
    private static volatile long lastPasswordCheckTime;
    private int lastPrintingStringCount;
    private long lastPushRegisterSendTime;
    private long lastStatusUpdateTime;
    private static volatile long lastThemeCheckTime;
    private long lastViewsCheckTime;
    public String linkPrefix;
    private ArrayList loadedFullChats;
    private ArrayList loadedFullParticipants;
    private ArrayList loadedFullUsers;
    public boolean loadingBlockedUsers;
    private SparseIntArray loadingChannelAdmins;
    public boolean loadingDialogs;
    private ArrayList loadingFullChats;
    private ArrayList loadingFullParticipants;
    private ArrayList loadingFullUsers;
    private int loadingNotificationSettings;
    private LongSparseArray loadingPeerSettings;
    private boolean loadingUnreadDialogs;
    private SharedPreferences mainPreferences;
    public String mapKey;
    public int mapProvider;
    public int maxBroadcastCount;
    public int maxCaptionLength;
    public int maxEditTime;
    public int maxFaveStickersCount;
    public int maxGroupCount;
    public int maxMegagroupCount;
    public int maxMessageLength;
    public int maxPinnedDialogsCount;
    public int maxRecentGifsCount;
    public int maxRecentStickersCount;
    private boolean migratingDialogs;
    public int minGroupConvertSize;
    private SparseIntArray needShortPollChannels;
    public int nextDialogsCacheOffset;
    private int nextProxyInfoCheckTime;
    private int nextTosCheckTime;
    private SharedPreferences notificationsPreferences;
    private ConcurrentHashMap objectsByUsernames;
    private boolean offlineSent;
    public ConcurrentHashMap onlinePrivacy;
    private Runnable passwordCheckRunnable;
    public boolean preloadFeaturedStickers;
    public LongSparseArray printingStrings;
    public LongSparseArray printingStringsTypes;
    public ConcurrentHashMap printingUsers;
    private TL_dialog proxyDialog;
    private long proxyDialogId;
    public int ratingDecay;
    private ArrayList readTasks;
    private LongSparseArray readTasksMap;
    public boolean registeringForPush;
    private LongSparseArray reloadingMessages;
    private HashMap reloadingWebpages;
    private LongSparseArray reloadingWebpagesPending;
    private messages_Dialogs resetDialogsAll;
    private TL_messages_peerDialogs resetDialogsPinned;
    private boolean resetingDialogs;
    public int revokeTimeLimit;
    public int revokeTimePmLimit;
    public int secretWebpagePreview;
    public SparseArray sendingTypings;
    public boolean serverDialogsEndReached;
    private SparseIntArray shortPollChannels;
    public static ArrayList staticBotArr;
    private int statusRequest;
    private int statusSettingState;
    public boolean suggestContacts;
    private Runnable themeCheckRunnable;
    public int unreadUnmutedDialogs;
    private final Comparator updatesComparator;
    private SparseArray updatesQueueChannels;
    private ArrayList updatesQueuePts;
    private ArrayList updatesQueueQts;
    private ArrayList updatesQueueSeq;
    private SparseLongArray updatesStartWaitTimeChannels;
    private long updatesStartWaitTimePts;
    private long updatesStartWaitTimeQts;
    private long updatesStartWaitTimeSeq;
    public boolean updatingState;
    private String uploadingAvatar;
    private ConcurrentHashMap users;
    public String venueSearchBot;
    private ArrayList visibleDialogMainThreadIds;
    public int webFileDatacenterId;

    static {
        MessagesController.Instance = new MessagesController[3];
        MessagesController.staticBotArr = new ArrayList();
    }

    public MessagesController(int arg9) {
        String v5;
        Context v9_1;
        super();
        int v3 = 2;
        this.chats = new ConcurrentHashMap(100, 1f, v3);
        this.encryptedChats = new ConcurrentHashMap(10, 1f, v3);
        this.users = new ConcurrentHashMap(100, 1f, v3);
        this.objectsByUsernames = new ConcurrentHashMap(100, 1f, v3);
        this.joiningToChannels = new ArrayList();
        this.exportedChats = new SparseArray();
        this.hintDialogs = new ArrayList();
        this.dialogs = new ArrayList();
        this.dialogsForward = new ArrayList();
        this.dialogsServerOnly = new ArrayList();
        this.dialogsGroupsOnly = new ArrayList();
        this.dialogsUsers = new ArrayList();
        this.dialogsGroups = new ArrayList();
        this.dialogsGroupsAll = new ArrayList();
        this.dialogsChannels = new ArrayList();
        this.dialogsMegaGroups = new ArrayList();
        this.dialogsBots = new ArrayList();
        this.dialogsFavs = new ArrayList();
        this.dialogsAll = new ArrayList();
        this.dialogsHidden = new ArrayList();
        this.dialogsUnread = new ArrayList();
        this.dialogsAds = new ArrayList();
        List v0 = null;
        this.hiddensIds = v0;
        this.categoriesIds = new ArrayList();
        this.dialogs_read_inbox_max = new ConcurrentHashMap(100, 1f, v3);
        this.dialogs_read_outbox_max = new ConcurrentHashMap(100, 1f, v3);
        this.dialogs_dict = new LongSparseArray();
        this.dialogMessage = new LongSparseArray();
        this.dialogMessagesByRandomIds = new LongSparseArray();
        this.dialogMessagesByIds = new SparseArray();
        this.printingUsers = new ConcurrentHashMap(20, 1f, v3);
        this.printingStrings = new LongSparseArray();
        this.printingStringsTypes = new LongSparseArray();
        this.sendingTypings = new SparseArray();
        this.onlinePrivacy = new ConcurrentHashMap(20, 1f, v3);
        this.loadingPeerSettings = new LongSparseArray();
        this.createdDialogIds = new ArrayList();
        this.createdDialogMainThreadIds = new ArrayList();
        this.visibleDialogMainThreadIds = new ArrayList();
        this.shortPollChannels = new SparseIntArray();
        this.needShortPollChannels = new SparseIntArray();
        this.loadingBlockedUsers = false;
        this.blockedUsers = new SparseIntArray();
        this.channelViewsToSend = new SparseArray();
        this.updatesQueueChannels = new SparseArray();
        this.updatesStartWaitTimeChannels = new SparseLongArray();
        this.channelsPts = new SparseIntArray();
        this.gettingDifferenceChannels = new SparseBooleanArray();
        this.gettingUnknownChannels = new SparseBooleanArray();
        this.checkingLastMessagesDialogs = new SparseBooleanArray();
        this.updatesQueueSeq = new ArrayList();
        this.updatesQueuePts = new ArrayList();
        this.updatesQueueQts = new ArrayList();
        this.fullUsers = new SparseArray();
        this.loadingFullUsers = new ArrayList();
        this.loadedFullUsers = new ArrayList();
        this.loadingFullChats = new ArrayList();
        this.loadingFullParticipants = new ArrayList();
        this.loadedFullParticipants = new ArrayList();
        this.loadedFullChats = new ArrayList();
        this.channelAdmins = new SparseArray();
        this.loadingChannelAdmins = new SparseIntArray();
        this.reloadingWebpages = new HashMap();
        this.reloadingWebpagesPending = new LongSparseArray();
        this.reloadingMessages = new LongSparseArray();
        this.readTasks = new ArrayList();
        this.readTasksMap = new LongSparseArray();
        this.getDifferenceFirstSync = true;
        this.suggestContacts = true;
        this.themeCheckRunnable = -$$Lambda$RQB0Jwr1FTqp6hrbGUHuOs-9k1I.INSTANCE;
        this.passwordCheckRunnable = new org.telegram.messenger.MessagesController$1(this);
        this.maxBroadcastCount = 100;
        int v1 = 200;
        this.minGroupConvertSize = v1;
        this.dialogComparator = new org.telegram.messenger.MessagesController$2(this);
        this.updatesComparator = new -$$Lambda$MessagesController$jdY-gZP_eY5WAh_nSc2UhUqpq4M(this);
        this.currentAccount = arg9;
        ImageLoader.getInstance();
        MessagesStorage.getInstance(this.currentAccount);
        LocationController.getInstance(this.currentAccount);
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$y42O_-dKOKyzNi5k5aHwMy0RjOs(this));
        this.addSupportUser();
        SharedPreferences v9 = ApplicationLoader.applicationContext.getSharedPreferences("plusconfig", 0);
        this.hideLeftGroup = v9.getBoolean("hideLeftGroup", false);
        this.hideJoinedGroup = v9.getBoolean("hideJoinedGroup", false);
        if(this.currentAccount == 0) {
            this.notificationsPreferences = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
            this.mainPreferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            v9_1 = ApplicationLoader.applicationContext;
            v5 = "emoji";
        }
        else {
            v9_1 = ApplicationLoader.applicationContext;
            StringBuilder v5_1 = new StringBuilder();
            v5_1.append("Notifications");
            v5_1.append(this.currentAccount);
            this.notificationsPreferences = v9_1.getSharedPreferences(v5_1.toString(), 0);
            v9_1 = ApplicationLoader.applicationContext;
            v5_1 = new StringBuilder();
            v5_1.append("mainconfig");
            v5_1.append(this.currentAccount);
            this.mainPreferences = v9_1.getSharedPreferences(v5_1.toString(), 0);
            v9_1 = ApplicationLoader.applicationContext;
            v5 = "emoji" + this.currentAccount;
        }

        this.emojiPreferences = v9_1.getSharedPreferences(v5, 0);
        this.enableJoined = this.notificationsPreferences.getBoolean("EnableContactJoined", true);
        this.secretWebpagePreview = this.mainPreferences.getInt("secretWebpage2", v3);
        this.maxGroupCount = this.mainPreferences.getInt("maxGroupCount", v1);
        this.maxMegagroupCount = this.mainPreferences.getInt("maxMegagroupCount", 10000);
        this.maxRecentGifsCount = this.mainPreferences.getInt("maxRecentGifsCount", v1);
        this.maxRecentStickersCount = this.mainPreferences.getInt("maxRecentStickersCount", 30);
        this.maxFaveStickersCount = this.mainPreferences.getInt("maxFaveStickersCount", 5);
        this.maxEditTime = this.mainPreferences.getInt("maxEditTime", 3600);
        this.ratingDecay = this.mainPreferences.getInt("ratingDecay", 2419200);
        this.linkPrefix = this.mainPreferences.getString("linkPrefix", "t.me");
        this.callReceiveTimeout = this.mainPreferences.getInt("callReceiveTimeout", 20000);
        this.callRingTimeout = this.mainPreferences.getInt("callRingTimeout", 90000);
        this.callConnectTimeout = this.mainPreferences.getInt("callConnectTimeout", 30000);
        this.callPacketTimeout = this.mainPreferences.getInt("callPacketTimeout", 10000);
        this.maxPinnedDialogsCount = this.mainPreferences.getInt("maxPinnedDialogsCount", 5);
        this.maxMessageLength = this.mainPreferences.getInt("maxMessageLength", 4096);
        this.maxCaptionLength = this.mainPreferences.getInt("maxCaptionLength", v1);
        this.mapProvider = this.mainPreferences.getInt("mapProvider", 0);
        this.availableMapProviders = this.mainPreferences.getInt("availableMapProviders", 3);
        this.mapKey = this.mainPreferences.getString("pk", ((String)v0));
        this.installReferer = this.mainPreferences.getString("installReferer", ((String)v0));
        this.defaultP2pContacts = this.mainPreferences.getBoolean("defaultP2pContacts", false);
        this.revokeTimeLimit = this.mainPreferences.getInt("revokeTimeLimit", this.revokeTimeLimit);
        this.revokeTimePmLimit = this.mainPreferences.getInt("revokeTimePmLimit", this.revokeTimePmLimit);
        this.canRevokePmInbox = this.mainPreferences.getBoolean("canRevokePmInbox", this.canRevokePmInbox);
        this.preloadFeaturedStickers = this.mainPreferences.getBoolean("preloadFeaturedStickers", false);
        this.proxyDialogId = this.mainPreferences.getLong("proxy_dialog", 0);
        this.nextTosCheckTime = this.notificationsPreferences.getInt("nextTosCheckTime", 0);
        this.venueSearchBot = this.mainPreferences.getString("venueSearchBot", "foursquare");
        this.gifSearchBot = this.mainPreferences.getString("gifSearchBot", "gif");
        this.imageSearchBot = this.mainPreferences.getString("imageSearchBot", "pic");
        this.blockedCountry = this.mainPreferences.getBoolean("blockedCountry", false);
        v9 = this.mainPreferences;
        String v0_1 = "dcDomainName";
        String v1_1 = ConnectionsManager.native_isTestBackend(this.currentAccount) != 0 ? "tapv2.stel.com" : "apv2.stel.com";
        this.dcDomainName = v9.getString(v0_1, v1_1);
        v9 = this.mainPreferences;
        v0_1 = "webFileDatacenterId";
        if(ConnectionsManager.native_isTestBackend(this.currentAccount) != 0) {
        }
        else {
            v3 = 4;
        }

        this.webFileDatacenterId = v9.getInt(v0_1, v3);
    }

    static int access$000(MessagesController arg0) {
        return arg0.currentAccount;
    }

    public void addSupportUser() {
        TL_userForeign_old2 v0 = new TL_userForeign_old2();
        v0.phone = "333";
        v0.id = 333000;
        v0.first_name = "Telegram";
        v0.last_name = "";
        v0.status = null;
        v0.photo = new TL_userProfilePhotoEmpty();
        this.putUser(((User)v0), true);
        v0 = new TL_userForeign_old2();
        v0.phone = "42777";
        v0.id = 777000;
        v0.first_name = "Telegram";
        v0.last_name = "Notifications";
        v0.status = null;
        v0.photo = new TL_userProfilePhotoEmpty();
        this.putUser(((User)v0), true);
    }

    public void addToViewsQueue(Message arg3) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$6NVpJ-TQRS_pHQpu5p4BLsUrGG8(this, arg3));
    }

    public void addUserToChat(int arg13, User arg14, ChatFull arg15, int arg16, String arg17, BaseFragment arg18) {
        TL_channels_joinChannel v0_2;
        MessagesController v8 = this;
        int v4 = arg13;
        User v0 = arg14;
        ChatFull v1 = arg15;
        String v2 = arg17;
        if(v0 == null) {
            return;
        }

        if(v4 > 0) {
            boolean v6 = ChatObject.isChannel(arg13, v8.currentAccount);
            boolean v7 = !v6 || !this.getChat(Integer.valueOf(arg13)).megagroup ? false : true;
            InputUser v3 = this.getInputUser(arg14);
            if(v2 != null) {
                if((v6) && !v7) {
                    goto label_43;
                }

                TL_messages_startBot v0_1 = new TL_messages_startBot();
                v0_1.bot = v3;
                if(v6) {
                    v0_1.peer = this.getInputPeer(-v4);
                }
                else {
                    v0_1.peer = new TL_inputPeerChat();
                    v0_1.peer.chat_id = v4;
                }

                v0_1.start_param = v2;
                v0_1.random_id = Utilities.random.nextLong();
            }
            else {
            label_43:
                if(v6) {
                    if((v3 instanceof TL_inputUserSelf)) {
                        if(v8.joiningToChannels.contains(Integer.valueOf(arg13))) {
                            return;
                        }

                        v0_2 = new TL_channels_joinChannel();
                        v0_2.channel = this.getInputChannel(arg13);
                        v8.joiningToChannels.add(Integer.valueOf(arg13));
                        goto label_72;
                    }

                    TL_channels_inviteToChannel v0_3 = new TL_channels_inviteToChannel();
                    v0_3.channel = this.getInputChannel(arg13);
                    v0_3.users.add(v3);
                    goto label_72;
                }

                TL_messages_addChatUser v0_4 = new TL_messages_addChatUser();
                v0_4.chat_id = v4;
                v0_4.fwd_limit = arg16;
                v0_4.user_id = v3;
            }

        label_72:
            ConnectionsManager.getInstance(v8.currentAccount).sendRequest(((TL_messages_startBot)v0_2), new -$$Lambda$MessagesController$J5mzrkW_EkUwZBwlzEOqQ3k5QHw(this, v6, v3, arg13, arg18, ((TL_messages_startBot)v0_2), v7));
        }
        else {
            if(!(v1 instanceof TL_chatFull)) {
                return;
            }

            int v2_1;
            for(v2_1 = 0; v2_1 < v1.participants.participants.size(); ++v2_1) {
                if(v1.participants.participants.get(v2_1).user_id == v0.id) {
                    return;
                }
            }

            Chat v2_2 = this.getChat(Integer.valueOf(arg13));
            ++v2_2.participants_count;
            ArrayList v4_1 = new ArrayList();
            v4_1.add(v2_2);
            MessagesStorage.getInstance(v8.currentAccount).putUsersAndChats(null, v4_1, true, true);
            TL_chatParticipant v2_3 = new TL_chatParticipant();
            v2_3.user_id = v0.id;
            v2_3.inviter_id = UserConfig.getInstance(v8.currentAccount).getClientUserId();
            v2_3.date = ConnectionsManager.getInstance(v8.currentAccount).getCurrentTime();
            v1.participants.participants.add(0, v2_3);
            MessagesStorage.getInstance(v8.currentAccount).updateChatInfo(arg15, true);
            NotificationCenter.getInstance(v8.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{v1, Integer.valueOf(0), Boolean.valueOf(false), null});
            NotificationCenter.getInstance(v8.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(32)});
        }
    }

    public void addUsersToChannel(int arg2, ArrayList arg3, BaseFragment arg4) {
        if(arg3 != null) {
            if(arg3.isEmpty()) {
            }
            else {
                TL_channels_inviteToChannel v0 = new TL_channels_inviteToChannel();
                v0.channel = this.getInputChannel(arg2);
                v0.users = arg3;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$ClJU6yS6OyGZuM1pwgXNOR7J2DA(this, arg4, v0));
            }
        }
    }

    private void applyDialogNotificationsSettings(long arg12, PeerNotifySettings arg14) {
        int v14_1;
        SharedPreferences v0 = this.notificationsPreferences;
        StringBuilder v1 = new StringBuilder();
        v1.append("notify2_");
        v1.append(arg12);
        int v2 = -1;
        int v0_1 = v0.getInt(v1.toString(), v2);
        SharedPreferences v1_1 = this.notificationsPreferences;
        StringBuilder v3 = new StringBuilder();
        v3.append("notifyuntil_");
        v3.append(arg12);
        int v1_2 = v1_1.getInt(v3.toString(), 0);
        SharedPreferences$Editor v3_1 = this.notificationsPreferences.edit();
        Object v5 = this.dialogs_dict.get(arg12);
        if(v5 != null) {
            ((TL_dialog)v5).notify_settings = arg14;
        }

        int v7 = 2;
        if((arg14.flags & v7) != 0) {
            v3_1.putBoolean("silent_" + arg12, arg14.silent);
        }
        else {
            v3_1.remove("silent_" + arg12);
        }

        long v8 = 0;
        int v10 = 1;
        if((arg14.flags & 4) == 0) {
            if(v0_1 != v2) {
                if(v5 != null) {
                    ((TL_dialog)v5).notify_settings.mute_until = 0;
                }

                v3_1.remove("notify2_" + arg12);
            }
            else {
            label_141:
                v10 = 0;
            }

        label_142:
            MessagesStorage.getInstance(this.currentAccount).setDialogFlags(arg12, v8);
        }
        else if(arg14.mute_until > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) {
            if(arg14.mute_until <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 31536000) {
                v2 = 3;
                if(v0_1 != v2 || v1_2 != arg14.mute_until) {
                    v3_1.putInt("notify2_" + arg12, v2);
                    v3_1.putInt("notifyuntil_" + arg12, arg14.mute_until);
                    if(v5 != null) {
                        ((TL_dialog)v5).notify_settings.mute_until = 0;
                    }
                }
                else {
                    v10 = 0;
                }

                v14_1 = arg14.mute_until;
            }
            else if(v0_1 != v7) {
                v3_1.putInt("notify2_" + arg12, v7);
                if(v5 != null) {
                    ((TL_dialog)v5).notify_settings.mute_until = 2147483647;
                }

                v14_1 = 0;
            }
            else {
                v14_1 = 0;
                v10 = 0;
            }

            MessagesStorage.getInstance(this.currentAccount).setDialogFlags(arg12, (((long)v14_1)) << 32 | 1);
            NotificationsController.getInstance(this.currentAccount).removeNotificationsForDialog(arg12);
        }
        else {
            if(v0_1 == 0) {
                goto label_141;
            }

            if(v0_1 == 1) {
                goto label_141;
            }

            if(v5 != null) {
                ((TL_dialog)v5).notify_settings.mute_until = 0;
            }

            v3_1.putInt("notify2_" + arg12, 0);
            goto label_142;
        }

        v3_1.commit();
        if(v10 != 0) {
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
        }
    }

    private void applyDialogsNotificationsSettings(ArrayList arg10) {
        int v4;
        SharedPreferences$Editor v2 = null;
        int v1;
        for(v1 = 0; v1 < arg10.size(); ++v1) {
            Object v3 = arg10.get(v1);
            if(((TL_dialog)v3).peer != null && ((((TL_dialog)v3).notify_settings instanceof TL_peerNotifySettings))) {
                if(v2 == null) {
                    v2 = this.notificationsPreferences.edit();
                }

                if(((TL_dialog)v3).peer.user_id != 0) {
                    v4 = ((TL_dialog)v3).peer.user_id;
                }
                else {
                    v4 = ((TL_dialog)v3).peer.chat_id != 0 ? ((TL_dialog)v3).peer.chat_id : ((TL_dialog)v3).peer.channel_id;
                    v4 = -v4;
                }

                int v6 = 2;
                if((((TL_dialog)v3).notify_settings.flags & v6) != 0) {
                    v2.putBoolean("silent_" + v4, ((TL_dialog)v3).notify_settings.silent);
                }
                else {
                    v2.remove("silent_" + v4);
                }

                if((((TL_dialog)v3).notify_settings.flags & 4) != 0) {
                    if(((TL_dialog)v3).notify_settings.mute_until > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) {
                        if(((TL_dialog)v3).notify_settings.mute_until > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 31536000) {
                            v2.putInt("notify2_" + v4, v6);
                            ((TL_dialog)v3).notify_settings.mute_until = 2147483647;
                            goto label_115;
                        }

                        v2.putInt("notify2_" + v4, 3);
                        v2.putInt("notifyuntil_" + v4, ((TL_dialog)v3).notify_settings.mute_until);
                        goto label_115;
                    }

                    v2.putInt("notify2_" + v4, 0);
                    goto label_115;
                }

                v2.remove("notify2_" + v4);
            }

        label_115:
        }

        if(v2 != null) {
            v2.commit();
        }
    }

    public void blockUser(int arg4) {
        User v0 = this.getUser(Integer.valueOf(arg4));
        if(v0 != null) {
            if(this.blockedUsers.indexOfKey(arg4) >= 0) {
            }
            else {
                this.blockedUsers.put(arg4, 1);
                if(v0.bot) {
                    DataQuery.getInstance(this.currentAccount).removeInline(arg4);
                }
                else {
                    DataQuery.getInstance(this.currentAccount).removePeer(arg4);
                }

                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoaded, new Object[0]);
                TL_contacts_block v4 = new TL_contacts_block();
                v4.id = this.getInputUser(v0);
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v4), new -$$Lambda$MessagesController$rx3tP18YKTCdcENU9_EQ1ZEJohY(this, v0));
            }
        }
    }

    public boolean canPinDialog(boolean arg7) {
        boolean v0 = false;
        int v1 = 0;
        int v2 = 0;
        while(v1 < this.dialogs.size()) {
            Object v3 = this.dialogs.get(v1);
            int v4 = ((int)((TL_dialog)v3).id);
            if((!arg7 || v4 == 0) && ((arg7) || v4 != 0) && (((TL_dialog)v3).pinned)) {
                ++v2;
            }

            ++v1;
        }

        if(v2 < this.maxPinnedDialogsCount) {
            v0 = true;
        }

        return v0;
    }

    public void cancelLoadFullChat(int arg2) {
        this.loadingFullChats.remove(Integer.valueOf(arg2));
    }

    public void cancelLoadFullUser(int arg2) {
        this.loadingFullUsers.remove(Integer.valueOf(arg2));
    }

    public void cancelTyping(int arg2, long arg3) {
        Object v2 = this.sendingTypings.get(arg2);
        if(v2 != null) {
            ((LongSparseArray)v2).remove(arg3);
        }
    }

    public void changeChatAvatar(int arg3, InputFile arg4) {
        InputChatPhoto v3;
        TL_channels_editPhoto v0;
        if(ChatObject.isChannel(arg3, this.currentAccount)) {
            v0 = new TL_channels_editPhoto();
            v0.channel = this.getInputChannel(arg3);
            if(arg4 != null) {
                v0.photo = new TL_inputChatUploadedPhoto();
                v3 = v0.photo;
                goto label_12;
            }
            else {
                v0.photo = new TL_inputChatPhotoEmpty();
            }
        }
        else {
            TL_messages_editChatPhoto v0_1 = new TL_messages_editChatPhoto();
            v0_1.chat_id = arg3;
            if(arg4 != null) {
                v0_1.photo = new TL_inputChatUploadedPhoto();
                v3 = v0_1.photo;
            label_12:
                v3.file = arg4;
            }
            else {
                v0_1.photo = new TL_inputChatPhotoEmpty();
            }
        }

        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$JhX02H37cAL8hy_53-znlkODRy8(this), 64);
    }

    public void changeChatTitle(int arg4, String arg5) {
        TL_messages_editChatTitle v0_1;
        if(arg4 > 0) {
            if(ChatObject.isChannel(arg4, this.currentAccount)) {
                TL_channels_editTitle v0 = new TL_channels_editTitle();
                v0.channel = this.getInputChannel(arg4);
                v0.title = arg5;
            }
            else {
                v0_1 = new TL_messages_editChatTitle();
                v0_1.chat_id = arg4;
                v0_1.title = arg5;
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0_1), new -$$Lambda$MessagesController$GVcJZl5obAME52ojAiGXQWiXmZ8(this), 64);
        }
        else {
            Chat v4 = this.getChat(Integer.valueOf(arg4));
            v4.title = arg5;
            ArrayList v5 = new ArrayList();
            v5.add(v4);
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(null, v5, true, true);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(16)});
        }
    }

    public boolean checkCanOpenChat(Bundle arg2, BaseFragment arg3) {
        return this.checkCanOpenChat(arg2, arg3, null);
    }

    public boolean checkCanOpenChat(Bundle arg9, BaseFragment arg10, MessageObject arg11) {
        ArrayList v0_1;
        TL_channels_getMessages v1_3;
        String v1_2;
        Chat v3_1;
        User v1_1;
        if(arg9 != null) {
            if(arg10 == null) {
            }
            else {
                int v1 = arg9.getInt("user_id", 0);
                int v3 = arg9.getInt("chat_id", 0);
                int v4 = arg9.getInt("message_id", 0);
                String v5 = null;
                if(v1 != 0) {
                    v1_1 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(v1));
                    v3_1 = ((Chat)v5);
                }
                else if(v3 != 0) {
                    v3_1 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Integer.valueOf(v3));
                    v1_1 = ((User)v5);
                }
                else {
                    v1_1 = ((User)v5);
                    v3_1 = ((Chat)v1_1);
                }

                if(v1_1 == null && v3_1 == null) {
                    return 1;
                }

                if(v3_1 != null) {
                    v1_2 = v3_1.restriction_reason;
                    goto label_34;
                }
                else if(v1_1 != null) {
                    v1_2 = v1_1.restriction_reason;
                label_34:
                    v5 = MessagesController.getRestrictionReason(v1_2);
                }

                if(v5 != null) {
                    MessagesController.showCantOpenAlert(arg10, v5);
                    return 0;
                }

                if(v4 == 0) {
                    return 1;
                }

                if(arg11 == null) {
                    return 1;
                }

                if(v3_1 == null) {
                    return 1;
                }

                if(v3_1.access_hash != 0) {
                    return 1;
                }

                v1 = ((int)arg11.getDialogId());
                if(v1 == 0) {
                    return 1;
                }

                AlertDialog v4_1 = new AlertDialog(arg10.getParentActivity(), 1);
                v4_1.setMessage(LocaleController.getString("Loading", 2131625103));
                v4_1.setCanceledOnTouchOutside(false);
                v4_1.setCancelable(false);
                if(v1 < 0) {
                    v3_1 = this.getChat(Integer.valueOf(-v1));
                }

                if(v1 > 0 || !ChatObject.isChannel(v3_1)) {
                    TL_messages_getMessages v1_4 = new TL_messages_getMessages();
                    v0_1 = v1_4.id;
                }
                else {
                    Chat v0 = this.getChat(Integer.valueOf(-v1));
                    v1_3 = new TL_channels_getMessages();
                    v1_3.channel = MessagesController.getInputChannel(v0);
                    v0_1 = v1_3.id;
                }

                v0_1.add(Integer.valueOf(arg11.getId()));
                v4_1.setButton(-2, LocaleController.getString("Cancel", 2131624257), new -$$Lambda$MessagesController$ov_8cd_UQ7e-iG00crefa_YP8ps(this, ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v1_3), new -$$Lambda$MessagesController$lDed6K4Cgs2_o_Bb7pCLt9gvJm0(this, v4_1, arg10, arg9)), arg10));
                arg10.setVisibleDialog(((Dialog)v4_1));
                v4_1.show();
                return 0;
            }
        }

        return 1;
    }

    private void checkChannelError(String arg6, int arg7) {
        int v6;
        int v0 = arg6.hashCode();
        int v4 = 2;
        if(v0 != -1809401834) {
            if(v0 != -795226617) {
                if(v0 != -471086771) {
                    goto label_26;
                }
                else if(arg6.equals("CHANNEL_PUBLIC_GROUP_NA")) {
                    v6 = 1;
                }
                else {
                    goto label_26;
                }
            }
            else if(arg6.equals("CHANNEL_PRIVATE")) {
                v6 = 0;
            }
            else {
                goto label_26;
            }
        }
        else if(arg6.equals("USER_BANNED_IN_CHANNEL")) {
            v6 = 2;
        }
        else {
        label_26:
            v6 = -1;
        }

        switch(v6) {
            case 0: {
                goto label_47;
            }
            case 1: {
                goto label_38;
            }
            case 2: {
                goto label_29;
            }
        }

        return;
    label_38:
        NotificationCenter v6_1 = NotificationCenter.getInstance(this.currentAccount);
        v0 = NotificationCenter.chatInfoCantLoad;
        Object[] v1 = new Object[v4];
        v1[0] = Integer.valueOf(arg7);
        v1[1] = Integer.valueOf(1);
        goto label_55;
    label_29:
        v6_1 = NotificationCenter.getInstance(this.currentAccount);
        v0 = NotificationCenter.chatInfoCantLoad;
        v1 = new Object[v4];
        v1[0] = Integer.valueOf(arg7);
        v1[1] = Integer.valueOf(v4);
        goto label_55;
    label_47:
        v6_1 = NotificationCenter.getInstance(this.currentAccount);
        v0 = NotificationCenter.chatInfoCantLoad;
        v1 = new Object[v4];
        v1[0] = Integer.valueOf(arg7);
        v1[1] = Integer.valueOf(0);
    label_55:
        v6_1.postNotificationName(v0, v1);
    }

    public void checkChannelInviter(int arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$8PXeieCG-Tt_sQWPclOFQ5H4Ixg(this, arg2));
    }

    private boolean checkDeletingTask(boolean arg4) {
        int v0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if(this.currentDeletingTaskMids != null) {
            if(!arg4) {
                if(this.currentDeletingTaskTime == 0) {
                }
                else if(this.currentDeletingTaskTime <= v0) {
                    goto label_11;
                }

                return 0;
            }

        label_11:
            this.currentDeletingTaskTime = 0;
            if(this.currentDeleteTaskRunnable != null && !arg4) {
                Utilities.stageQueue.cancelRunnable(this.currentDeleteTaskRunnable);
            }

            this.currentDeleteTaskRunnable = null;
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$6FSFlNlNgB-ZmmXYjdcs16JeTvs(this, new ArrayList(this.currentDeletingTaskMids)));
            return 1;
        }

        return 0;
    }

    protected void checkLastDialogMessage(TL_dialog arg9, InputPeer arg10, long arg11) {
        int v11;
        NativeByteBuffer v12;
        int v7 = ((int)arg9.id);
        if(v7 != 0 && this.checkingLastMessagesDialogs.indexOfKey(v7) < 0) {
            TL_messages_getHistory v0 = new TL_messages_getHistory();
            InputPeer v1 = arg10 == null ? this.getInputPeer(v7) : arg10;
            v0.peer = v1;
            if(v0.peer == null) {
                return;
            }

            if((v0.peer instanceof TL_inputPeerChannel)) {
                return;
            }

            v0.limit = 1;
            this.checkingLastMessagesDialogs.put(v7, true);
            if(arg11 == 0) {
                try {
                    v12 = new NativeByteBuffer(v0.peer.getObjectSize() + 48);
                    v11 = 10;
                }
                catch(Exception v10) {
                    v12 = ((NativeByteBuffer)v11);
                    goto label_64;
                }

                try {
                    v12.writeInt32(v11);
                    v12.writeInt64(arg9.id);
                    v12.writeInt32(arg9.top_message);
                    v12.writeInt32(arg9.read_inbox_max_id);
                    v12.writeInt32(arg9.read_outbox_max_id);
                    v12.writeInt32(arg9.unread_count);
                    v12.writeInt32(arg9.last_message_date);
                    v12.writeInt32(arg9.pts);
                    v12.writeInt32(arg9.flags);
                    v12.writeBool(arg9.pinned);
                    v12.writeInt32(arg9.pinnedNum);
                    v12.writeInt32(arg9.unread_mentions_count);
                    v12.writeBool(arg9.unread_mark);
                    arg10.serializeToStream(((AbstractSerializedData)v12));
                    goto label_65;
                }
                catch(Exception v10) {
                }

            label_64:
                FileLog.e(((Throwable)v10));
            label_65:
                arg11 = MessagesStorage.getInstance(this.currentAccount).createPendingTask(v12);
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$yaxteidqi0r6SOTAS9A8183Ft20(this, arg9, arg11, v7));
        }
    }

    public void checkProxyInfo(boolean arg3) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$f8HrOtzmDtx646lhrgtuYkcJbjs(this, arg3));
    }

    private void checkProxyInfoInternal(boolean arg7) {
        if((arg7) && (this.checkingProxyInfo)) {
            this.checkingProxyInfo = false;
        }

        if(!arg7 && this.nextProxyInfoCheckTime > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() || (this.checkingProxyInfo)) {
            return;
        }

        SharedPreferences v7 = MessagesController.getGlobalMainSettings();
        boolean v1 = v7.getBoolean("proxy_enabled", false);
        String v2 = v7.getString("proxy_ip", "");
        String v7_1 = v7.getString("proxy_secret", "");
        if(!v1 || (TextUtils.isEmpty(((CharSequence)v2))) || (TextUtils.isEmpty(((CharSequence)v7_1)))) {
            this.proxyDialogId = 0;
            MessagesController.getGlobalMainSettings().edit().putLong("proxy_dialog", this.proxyDialogId).commit();
            this.nextProxyInfoCheckTime = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 3600;
            this.checkingProxyInfo = false;
            if(this.checkingProxyInfoRequestId != 0) {
                ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkingProxyInfoRequestId, true);
                this.checkingProxyInfoRequestId = 0;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$X3iQpOpBCu9XhTdKzXpiVJZfYP4(this));
        }
        else {
            this.checkingProxyInfo = true;
            this.checkingProxyInfoRequestId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_help_getProxyData(), new -$$Lambda$MessagesController$_fyMloSxBpFlOUkpIv26yZEXXyc(this));
        }
    }

    private void checkReadTasks() {
        long v0 = SystemClock.elapsedRealtime();
        int v2 = this.readTasks.size();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            Object v4 = this.readTasks.get(v3);
            if(((ReadTask)v4).sendRequestTime > v0) {
            }
            else {
                this.completeReadTask(((ReadTask)v4));
                this.readTasks.remove(v3);
                this.readTasksMap.remove(((ReadTask)v4).dialogId);
                --v3;
                --v2;
            }
        }
    }

    private void checkTosUpdate() {
        if(this.nextTosCheckTime <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() && !this.checkingTosUpdate) {
            if(!UserConfig.getInstance(this.currentAccount).isClientActivated()) {
            }
            else {
                this.checkingTosUpdate = true;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_help_getTermsOfServiceUpdate(), new -$$Lambda$MessagesController$zza9fXw8nf_hJN4qiibEZxrUSKI(this));
            }
        }
    }

    public void cleanup() {
        ContactsController.getInstance(this.currentAccount).cleanup();
        MediaController.getInstance().cleanup();
        NotificationsController.getInstance(this.currentAccount).cleanup();
        SendMessagesHelper.getInstance(this.currentAccount).cleanup();
        SecretChatHelper.getInstance(this.currentAccount).cleanup();
        LocationController.getInstance(this.currentAccount).cleanup();
        DataQuery.getInstance(this.currentAccount).cleanup();
        this.dialogsUsers.clear();
        this.dialogsGroups.clear();
        this.dialogsGroupsAll.clear();
        this.dialogsChannels.clear();
        this.dialogsMegaGroups.clear();
        this.dialogsBots.clear();
        this.dialogsFavs.clear();
        this.dialogsHidden.clear();
        this.dialogsAll.clear();
        this.dialogsUnread.clear();
        this.dialogsAds.clear();
        DialogsActivity.dialogsLoaded[this.currentAccount] = false;
        this.notificationsPreferences.edit().clear().commit();
        this.emojiPreferences.edit().putLong("lastGifLoadTime", 0).putLong("lastStickersLoadTime", 0).putLong("lastStickersLoadTimeMask", 0).putLong("lastStickersLoadTimeFavs", 0).commit();
        this.mainPreferences.edit().remove("gifhint").remove("dcDomainName").remove("webFileDatacenterId").commit();
        this.reloadingWebpages.clear();
        this.reloadingWebpagesPending.clear();
        this.dialogs_dict.clear();
        this.dialogs_read_inbox_max.clear();
        this.dialogs_read_outbox_max.clear();
        this.exportedChats.clear();
        this.fullUsers.clear();
        this.dialogs.clear();
        this.unreadUnmutedDialogs = 0;
        this.joiningToChannels.clear();
        this.channelViewsToSend.clear();
        this.dialogsServerOnly.clear();
        this.dialogsForward.clear();
        this.dialogsGroupsOnly.clear();
        this.dialogMessagesByIds.clear();
        this.dialogMessagesByRandomIds.clear();
        this.channelAdmins.clear();
        this.loadingChannelAdmins.clear();
        this.users.clear();
        this.objectsByUsernames.clear();
        this.chats.clear();
        this.dialogMessage.clear();
        this.printingUsers.clear();
        this.printingStrings.clear();
        this.printingStringsTypes.clear();
        this.onlinePrivacy.clear();
        this.loadingPeerSettings.clear();
        this.lastPrintingStringCount = 0;
        this.nextDialogsCacheOffset = 0;
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$TXi-s51wx6h6AB-pDPqXMnp9kqk(this));
        this.createdDialogMainThreadIds.clear();
        this.visibleDialogMainThreadIds.clear();
        this.blockedUsers.clear();
        this.sendingTypings.clear();
        this.loadingFullUsers.clear();
        this.loadedFullUsers.clear();
        this.reloadingMessages.clear();
        this.loadingFullChats.clear();
        this.loadingFullParticipants.clear();
        this.loadedFullParticipants.clear();
        this.loadedFullChats.clear();
        this.checkingTosUpdate = false;
        this.nextTosCheckTime = 0;
        this.nextProxyInfoCheckTime = 0;
        this.checkingProxyInfo = false;
        this.loadingUnreadDialogs = false;
        this.currentDeletingTaskTime = 0;
        ArrayList v0 = null;
        this.currentDeletingTaskMids = v0;
        this.currentDeletingTaskChannelId = 0;
        this.gettingNewDeleteTask = false;
        this.loadingDialogs = false;
        this.dialogsEndReached = false;
        this.serverDialogsEndReached = false;
        this.loadingBlockedUsers = false;
        this.firstGettingTask = false;
        this.updatingState = false;
        this.resetingDialogs = false;
        this.lastStatusUpdateTime = 0;
        this.offlineSent = false;
        this.registeringForPush = false;
        this.getDifferenceFirstSync = true;
        this.uploadingAvatar = ((String)v0);
        this.statusRequest = 0;
        this.statusSettingState = 0;
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$0m8wxjajR0RUWNLkMfkRPUIY-Sc(this));
        if(this.currentDeleteTaskRunnable != null) {
            Utilities.stageQueue.cancelRunnable(this.currentDeleteTaskRunnable);
            this.currentDeleteTaskRunnable = ((Runnable)v0);
        }

        this.addSupportUser();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    protected void clearFullUsers() {
        this.loadedFullUsers.clear();
        this.loadedFullChats.clear();
    }

    protected void completeDialogsReset(messages_Dialogs arg11, int arg12, int arg13, int arg14, int arg15, int arg16, LongSparseArray arg17, LongSparseArray arg18, Message arg19) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$m9DTYjQC4xUG7iI1XktkyjEo_2A(this, arg14, arg15, arg16, arg11, arg17, arg18));
    }

    private void completeReadTask(ReadTask arg6) {
        TL_channels_readHistory v0_1;
        int v0 = ((int)arg6.dialogId);
        int v1 = ((int)(arg6.dialogId >> 32));
        if(v0 != 0) {
            InputPeer v1_1 = this.getInputPeer(v0);
            if((v1_1 instanceof TL_inputPeerChannel)) {
                TL_channels_readHistory v1_2 = new TL_channels_readHistory();
                v1_2.channel = this.getInputChannel(-v0);
                v1_2.max_id = arg6.maxId;
                v0_1 = v1_2;
            }
            else {
                TL_messages_readHistory v0_2 = new TL_messages_readHistory();
                v0_2.peer = v1_1;
                v0_2.max_id = arg6.maxId;
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0_1), new -$$Lambda$MessagesController$cukM8JCLNtumXeR2C2_0LmUkQq0(this));
        }
        else {
            EncryptedChat v0_3 = this.getEncryptedChat(Integer.valueOf(v1));
            if(v0_3 == null) {
                return;
            }

            if(v0_3.auth_key == null) {
                return;
            }

            if(v0_3.auth_key.length <= 1) {
                return;
            }

            if(!(v0_3 instanceof TL_encryptedChat)) {
                return;
            }

            TL_messages_readEncryptedHistory v1_3 = new TL_messages_readEncryptedHistory();
            v1_3.peer = new TL_inputEncryptedChat();
            v1_3.peer.chat_id = v0_3.id;
            v1_3.peer.access_hash = v0_3.access_hash;
            v1_3.max_date = arg6.maxDate;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v1_3), -$$Lambda$MessagesController$-Us8QcaUHG3-M-b6YH2H3AEIwPU.INSTANCE);
        }
    }

    public void convertToMegaGroup(Context arg4, int arg5) {
        TL_messages_migrateChat v0 = new TL_messages_migrateChat();
        v0.chat_id = arg5;
        AlertDialog v5 = new AlertDialog(arg4, 1);
        v5.setMessage(LocaleController.getString("Loading", 2131625103));
        v5.setCanceledOnTouchOutside(false);
        v5.setCancelable(false);
        v5.setButton(-2, LocaleController.getString("Cancel", 2131624257), new -$$Lambda$MessagesController$UVgaZmKj9Fg1VErNPPgEynS6i2M(this, ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$fCog05my5BKT4PMe7DtgUmQGlZ0(this, arg4, v5))));
        try {
            v5.show();
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public int createChat(String arg9, ArrayList arg10, String arg11, int arg12, BaseFragment arg13) {
        int v10;
        int v0 = 0;
        if(arg12 == 1) {
            TL_chat v11 = new TL_chat();
            v11.id = UserConfig.getInstance(this.currentAccount).lastBroadcastId;
            v11.title = arg9;
            v11.photo = new TL_chatPhotoEmpty();
            v11.participants_count = arg10.size();
            long v2 = 1000;
            v11.date = ((int)(System.currentTimeMillis() / v2));
            v11.version = 1;
            UserConfig v9 = UserConfig.getInstance(this.currentAccount);
            --v9.lastBroadcastId;
            this.putChat(((Chat)v11), false);
            ArrayList v9_1 = new ArrayList();
            v9_1.add(v11);
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(null, v9_1, true, true);
            TL_chatFull v9_2 = new TL_chatFull();
            v9_2.id = v11.id;
            v9_2.chat_photo = new TL_photoEmpty();
            v9_2.notify_settings = new TL_peerNotifySettingsEmpty_layer77();
            v9_2.exported_invite = new TL_chatInviteEmpty();
            v9_2.participants = new TL_chatParticipants();
            v9_2.participants.chat_id = v11.id;
            v9_2.participants.admin_id = UserConfig.getInstance(this.currentAccount).getClientUserId();
            v9_2.participants.version = 1;
            for(arg12 = 0; arg12 < arg10.size(); ++arg12) {
                TL_chatParticipant v13 = new TL_chatParticipant();
                v13.user_id = arg10.get(arg12).intValue();
                v13.inviter_id = UserConfig.getInstance(this.currentAccount).getClientUserId();
                v13.date = ((int)(System.currentTimeMillis() / v2));
                v9_2.participants.participants.add(v13);
            }

            MessagesStorage.getInstance(this.currentAccount).updateChatInfo(((ChatFull)v9_2), false);
            TL_messageService v9_3 = new TL_messageService();
            v9_3.action = new TL_messageActionCreatedBroadcastList();
            v10 = UserConfig.getInstance(this.currentAccount).getNewMessageId();
            v9_3.id = v10;
            v9_3.local_id = v10;
            v9_3.from_id = UserConfig.getInstance(this.currentAccount).getClientUserId();
            v9_3.dialog_id = AndroidUtilities.makeBroadcastId(v11.id);
            v9_3.to_id = new TL_peerChat();
            v9_3.to_id.chat_id = v11.id;
            v9_3.date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            v9_3.random_id = 0;
            v9_3.flags |= 256;
            UserConfig.getInstance(this.currentAccount).saveConfig(false);
            MessageObject v10_1 = new MessageObject(this.currentAccount, ((Message)v9_3), this.users, true);
            v10_1.messageOwner.send_state = 0;
            ArrayList v12 = new ArrayList();
            v12.add(v10_1);
            ArrayList v3 = new ArrayList();
            v3.add(v9_3);
            MessagesStorage.getInstance(this.currentAccount).putMessages(v3, false, true, false, 0);
            this.updateInterfaceWithMessages(v9_3.dialog_id, v12);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.chatDidCreated, new Object[]{Integer.valueOf(v11.id)});
            return 0;
        }

        int v2_1 = 2;
        if(arg12 == 0) {
            TL_messages_createChat v11_1 = new TL_messages_createChat();
            v11_1.title = arg9;
            while(v0 < arg10.size()) {
                User v9_4 = this.getUser(arg10.get(v0));
                if(v9_4 == null) {
                }
                else {
                    v11_1.users.add(this.getInputUser(v9_4));
                }

                ++v0;
            }

            return ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v11_1), new -$$Lambda$MessagesController$Styq68Mx2TMT_yM1ljFI2FRhibY(this, arg13, v11_1), v2_1);
        }

        v10 = 4;
        if(arg12 != v2_1) {
            if(arg12 == v10) {
            }
            else {
                return 0;
            }
        }

        TL_channels_createChannel v0_1 = new TL_channels_createChannel();
        v0_1.title = arg9;
        v0_1.about = arg11;
        if(arg12 == v10) {
            v0_1.megagroup = true;
        }
        else {
            v0_1.broadcast = true;
        }

        return ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0_1), new -$$Lambda$MessagesController$lnbu-l9eb726LihMu9bTxQeXuRI(this, arg13, v0_1), v2_1);
    }

    private void deleteDialog(long arg23, boolean arg25, int arg26, int arg27) {
        boolean v7_3;
        int v11_1;
        int v13_1;
        SparseArray v10;
        int v8_1;
        int v12;
        MessagesController v6 = this;
        long v2 = arg23;
        int v4 = arg26;
        int v0 = ((int)v2);
        int v1 = ((int)(v2 >> 32));
        int v5 = 2;
        if(v4 == v5) {
            MessagesStorage.getInstance(v6.currentAccount).deleteDialog(v2, v4);
            return;
        }

        int v7 = 3;
        if(v4 == 0 || v4 == v7) {
            DataQuery.getInstance(v6.currentAccount).uninstallShortcut(v2);
        }

        int v9 = 0;
        if(arg25) {
            MessagesStorage.getInstance(v6.currentAccount).deleteDialog(v2, v4);
            Object v11 = v6.dialogs_dict.get(v2);
            if(v11 != null) {
                v12 = arg27 == 0 ? Math.max(0, ((TL_dialog)v11).top_message) : arg27;
                long v13 = 0;
                if(v4 == 0 || v4 == v7) {
                    v7 = v6.proxyDialog == null || v6.proxyDialog.id != v2 ? 0 : 1;
                    if(v7 != 0) {
                        v6.isLeftProxyChannel = true;
                        if(v6.proxyDialog.id < v13) {
                            Chat v8 = v6.getChat(Integer.valueOf(-(((int)v6.proxyDialog.id))));
                            if(v8 != null) {
                                v8.left = true;
                            }
                        }

                        v6.sortDialogs(null);
                    }
                    else {
                        v6.dialogs.remove(v11);
                        if((v6.dialogsServerOnly.remove(v11)) && (DialogObject.isChannel(((TL_dialog)v11)))) {
                            Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$MN5WIY48TJct_NE92h8pMr3553o(v6, v2));
                        }

                        v6.dialogsUsers.remove(v11);
                        v6.dialogsGroups.remove(v11);
                        v6.dialogsGroupsAll.remove(v11);
                        v6.dialogsChannels.remove(v11);
                        v6.dialogsMegaGroups.remove(v11);
                        v6.dialogsBots.remove(v11);
                        v6.dialogsFavs.remove(v11);
                        v6.dialogsHidden.remove(v11);
                        v6.dialogsAll.remove(v11);
                        v6.dialogsUnread.remove(v11);
                        v6.dialogsAds.remove(v11);
                        v6.dialogsGroupsOnly.remove(v11);
                        v6.dialogs_dict.remove(v2);
                        v6.dialogs_read_inbox_max.remove(Long.valueOf(arg23));
                        v6.dialogs_read_outbox_max.remove(Long.valueOf(arg23));
                        --v6.nextDialogsCacheOffset;
                    }

                    v9 = v7;
                }
                else {
                    ((TL_dialog)v11).unread_count = 0;
                }

                if(v9 == 0) {
                    Object v7_1 = v6.dialogMessage.get(((TL_dialog)v11).id);
                    v6.dialogMessage.remove(((TL_dialog)v11).id);
                    if(v7_1 != null) {
                        v8_1 = ((MessageObject)v7_1).getId();
                        v10 = v6.dialogMessagesByIds;
                        v13_1 = ((MessageObject)v7_1).getId();
                    }
                    else {
                        v8_1 = ((TL_dialog)v11).top_message;
                        v7_1 = v6.dialogMessagesByIds.get(((TL_dialog)v11).top_message);
                        v10 = v6.dialogMessagesByIds;
                        v13_1 = ((TL_dialog)v11).top_message;
                    }

                    v10.remove(v13_1);
                    if(v7_1 != null && ((MessageObject)v7_1).messageOwner.random_id != 0) {
                        v6.dialogMessagesByRandomIds.remove(((MessageObject)v7_1).messageOwner.random_id);
                    }

                    if(v4 == 1 && v0 != 0 && v8_1 > 0) {
                        TL_messageService v7_2 = new TL_messageService();
                        v7_2.id = ((TL_dialog)v11).top_message;
                        boolean v8_2 = (((long)UserConfig.getInstance(v6.currentAccount).getClientUserId())) == v2 ? true : false;
                        v7_2.out = v8_2;
                        v7_2.from_id = UserConfig.getInstance(v6.currentAccount).getClientUserId();
                        v7_2.flags |= 256;
                        v7_2.action = new TL_messageActionHistoryClear();
                        v7_2.date = ((TL_dialog)v11).last_message_date;
                        if(v0 > 0) {
                            v7_2.to_id = new TL_peerUser();
                            v7_2.to_id.user_id = v0;
                        }
                        else {
                            v8_1 = -v0;
                            if(ChatObject.isChannel(v6.getChat(Integer.valueOf(v8_1)))) {
                                v7_2.to_id = new TL_peerChannel();
                                v7_2.to_id.channel_id = v8_1;
                            }
                            else {
                                v7_2.to_id = new TL_peerChat();
                                v7_2.to_id.chat_id = v8_1;
                            }
                        }

                        MessageObject v8_3 = new MessageObject(v6.currentAccount, ((Message)v7_2), v6.createdDialogIds.contains(Long.valueOf(v7_2.dialog_id)));
                        ArrayList v10_1 = new ArrayList();
                        v10_1.add(v8_3);
                        ArrayList v8_4 = new ArrayList();
                        v8_4.add(v7_2);
                        v6.updateInterfaceWithMessages(v2, v10_1);
                        MessagesStorage.getInstance(v6.currentAccount).putMessages(v8_4, false, true, false, 0);
                        goto label_219;
                    }

                    v7 = 0;
                    ((TL_dialog)v11).top_message = 0;
                    goto label_224;
                }

            label_219:
                v7 = 0;
            }
            else {
                v7 = 0;
                v12 = arg27;
                v9 = 0;
            }

        label_224:
            if(v9 != 0) {
                NotificationCenter v5_1 = NotificationCenter.getInstance(v6.currentAccount);
                v8_1 = NotificationCenter.dialogsNeedReload;
                Object[] v10_2 = new Object[1];
                v10_2[v7] = Boolean.valueOf(true);
                v5_1.postNotificationName(v8_1, v10_2);
                v11_1 = 1;
            }
            else {
                NotificationCenter.getInstance(v6.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[v7]);
                NotificationCenter v8_5 = NotificationCenter.getInstance(v6.currentAccount);
                v9 = NotificationCenter.removeAllMessagesFromDialog;
                Object[] v5_2 = new Object[v5];
                v5_2[v7] = Long.valueOf(arg23);
                v11_1 = 1;
                v5_2[1] = Boolean.valueOf(((boolean)v7));
                v8_5.postNotificationName(v9, v5_2);
            }

            MessagesStorage.getInstance(v6.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$kKOuPMmi3f7Kv0QAm74C9bNGXGA(v6, v2));
            v5 = v12;
        }
        else {
            v7_3 = false;
            v11_1 = 1;
            v5 = arg27;
        }

        if(v1 != v11_1) {
            if(v4 == 3) {
            }
            else if(v0 != 0) {
                InputPeer v0_1 = v6.getInputPeer(v0);
                if(v0_1 == null) {
                    return;
                }
                else {
                    v8_1 = 64;
                    v9 = 2147483647;
                    if(!(v0_1 instanceof TL_inputPeerChannel)) {
                        TL_messages_deleteHistory v10_3 = new TL_messages_deleteHistory();
                        v10_3.peer = v0_1;
                        if(v4 == 0) {
                        }
                        else {
                            v9 = v5;
                        }

                        v10_3.max_id = v9;
                        if(v4 != 0) {
                            v7_3 = true;
                        }

                        v10_3.just_clear = v7_3;
                        ConnectionsManager.getInstance(v6.currentAccount).sendRequest(((TLObject)v10_3), new -$$Lambda$MessagesController$-0rWghQqUvAbSyTz3dpmr8hdUOU(this, arg23, arg26, v5), v8_1);
                    }
                    else if(v4 == 0) {
                        return;
                    }
                    else {
                        TL_channels_deleteHistory v1_1 = new TL_channels_deleteHistory();
                        v1_1.channel = new TL_inputChannel();
                        v1_1.channel.channel_id = v0_1.channel_id;
                        v1_1.channel.access_hash = v0_1.access_hash;
                        if(v5 > 0) {
                        }
                        else {
                            v5 = 2147483647;
                        }

                        v1_1.max_id = v5;
                        ConnectionsManager.getInstance(v6.currentAccount).sendRequest(((TLObject)v1_1), -$$Lambda$MessagesController$Y_AYhAAx7hiH5UjmhU0B2CdZQkM.INSTANCE, v8_1);
                    }
                }
            }
            else {
                if(v4 == 1) {
                    SecretChatHelper.getInstance(v6.currentAccount).sendClearHistoryMessage(v6.getEncryptedChat(Integer.valueOf(v1)), null);
                    return;
                }

                SecretChatHelper.getInstance(v6.currentAccount).declineSecretChat(v1);
            }
        }
    }

    public void deleteDialog(long arg7, int arg9) {
        this.deleteDialog(arg7, true, arg9, 0);
    }

    public void deleteMessages(ArrayList arg8, ArrayList arg9, EncryptedChat arg10, int arg11, boolean arg12, long arg13, TLObject arg15) {
        // Method was not decompiled
    }

    public void deleteMessages(ArrayList arg10, ArrayList arg11, EncryptedChat arg12, int arg13, boolean arg14) {
        this.deleteMessages(arg10, arg11, arg12, arg13, arg14, 0, null);
    }

    public void deleteSelectedDialogs() {
        ArrayList v0 = this.getSelectedDialogs();
        int v2;
        for(v2 = 0; v2 < v0.size(); ++v2) {
            long v3 = v0.get(v2).id;
            this.deleteDialog(v3, 0);
            MessagesController.getInstance(this.currentAccount).deleteUserFromChat(((int)(-v3)), UserConfig.getInstance(this.currentAccount).getCurrentUser(), null);
            if(AndroidUtilities.isTablet()) {
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[]{Long.valueOf(v3)});
            }
        }
    }

    public void deleteUserChannelHistory(Chat arg3, User arg4, int arg5) {
        if(arg5 == 0) {
            MessagesStorage.getInstance(this.currentAccount).deleteUserChannelHistory(arg3.id, arg4.id);
        }

        TL_channels_deleteUserHistory v5 = new TL_channels_deleteUserHistory();
        v5.channel = MessagesController.getInputChannel(arg3);
        v5.user_id = this.getInputUser(arg4);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v5), new -$$Lambda$MessagesController$PEC9Sgnlqd-FPh88YWJVNNj2h6w(this, arg3, arg4));
    }

    public void deleteUserFromChat(int arg2, User arg3, ChatFull arg4) {
        this.deleteUserFromChat(arg2, arg3, arg4, false);
    }

    public void deleteUserFromChat(int arg8, User arg9, ChatFull arg10, boolean arg11) {
        int v9;
        TL_channels_deleteChannel v11;
        if(arg9 == null) {
            return;
        }

        Iterator v0 = b.I().iterator();
        do {
            if(v0.hasNext()) {
                Object v1 = v0.next();
                if(((ChatTime)v1).getcId() != (((long)Math.abs(arg8)))) {
                    continue;
                }

                break;
            }

            goto label_15;
        }
        while(true);

        if(((ChatTime)v1).getTime() > System.currentTimeMillis()) {
            return;
        }

    label_15:
        if(arg8 > 0) {
            InputUser v6 = this.getInputUser(arg9);
            Chat v10 = this.getChat(Integer.valueOf(arg8));
            boolean v5 = ChatObject.isChannel(v10);
            if(!v5) {
                TL_messages_deleteChatUser v11_3 = new TL_messages_deleteChatUser();
                v11_3.chat_id = arg8;
                v11_3.user_id = this.getInputUser(arg9);
            }
            else if((v6 instanceof TL_inputUserSelf)) {
                if((v10.creator) && (arg11)) {
                    v11 = new TL_channels_deleteChannel();
                    v11.channel = MessagesController.getInputChannel(v10);
                    goto label_67;
                }

                TL_channels_leaveChannel v11_1 = new TL_channels_leaveChannel();
                v11_1.channel = MessagesController.getInputChannel(v10);
            }
            else {
                TL_channels_editBanned v11_2 = new TL_channels_editBanned();
                v11_2.channel = MessagesController.getInputChannel(v10);
                v11_2.user_id = v6;
                v11_2.banned_rights = new TL_channelBannedRights();
                v11_2.banned_rights.view_messages = true;
                v11_2.banned_rights.send_media = true;
                v11_2.banned_rights.send_messages = true;
                v11_2.banned_rights.send_stickers = true;
                v11_2.banned_rights.send_gifs = true;
                v11_2.banned_rights.send_games = true;
                v11_2.banned_rights.send_inline = true;
                v11_2.banned_rights.embed_links = true;
            }

        label_67:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v11), new -$$Lambda$MessagesController$oKtWdTAN0dAt058skQlRH_zRN88(this, arg9, arg8, v5, v6), 64);
        }
        else {
            if(!(arg10 instanceof TL_chatFull)) {
                return;
            }

            Chat v8 = this.getChat(Integer.valueOf(arg8));
            --v8.participants_count;
            ArrayList v11_4 = new ArrayList();
            v11_4.add(v8);
            ArrayList v1_1 = null;
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(v1_1, v11_4, true, true);
            int v11_5 = 0;
            while(true) {
                if(v11_5 >= arg10.participants.participants.size()) {
                    break;
                }
                else if(arg10.participants.participants.get(v11_5).user_id == arg9.id) {
                    arg10.participants.participants.remove(v11_5);
                    v9 = 1;
                }
                else {
                    ++v11_5;
                    continue;
                }

                goto label_113;
            }

            v9 = 0;
        label_113:
            if(v9 != 0) {
                MessagesStorage.getInstance(this.currentAccount).updateChatInfo(arg10, true);
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{arg10, Integer.valueOf(0), Boolean.valueOf(false), v1_1});
            }

            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(32)});
        }
    }

    public void deleteUserPhoto(InputPhoto arg6) {
        if(arg6 == null) {
            TL_photos_updateProfilePhoto v6 = new TL_photos_updateProfilePhoto();
            v6.id = new TL_inputPhotoEmpty();
            UserConfig.getInstance(this.currentAccount).getCurrentUser().photo = new TL_userProfilePhotoEmpty();
            User v0 = this.getUser(Integer.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
            if(v0 == null) {
                v0 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
            }

            if(v0 == null) {
                return;
            }

            v0.photo = UserConfig.getInstance(this.currentAccount).getCurrentUser().photo;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(1535)});
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v6), new -$$Lambda$MessagesController$kgobNSbykwwIGKkjaVsZLgYVCZE(this));
        }
        else {
            TL_photos_deletePhotos v0_1 = new TL_photos_deletePhotos();
            v0_1.id.add(arg6);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0_1), -$$Lambda$MessagesController$-NyoRUyYt5sh7y6GxvJgvEzJ254.INSTANCE);
        }
    }

    public void didAddedNewTask(int arg3, SparseArray arg4) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$-pYomtjbGBxUXRvLzcfCZz-aBds(this, arg3));
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$e9rU29-iLCLLeTqvrz2z5q6wfnM(this, arg4));
    }

    public void didReceivedNotification(int arg5, int arg6, Object[] arg7) {
        Object v6;
        Object v5;
        if(arg5 == NotificationCenter.FileDidUpload) {
            v5 = arg7[0];
            v6 = arg7[1];
            if(this.uploadingAvatar != null && (this.uploadingAvatar.equals(v5))) {
                TL_photos_uploadProfilePhoto v5_1 = new TL_photos_uploadProfilePhoto();
                v5_1.file = ((InputFile)v6);
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v5_1), new -$$Lambda$MessagesController$avFJzHcfb0E8c4wljuXOjwoiTOA(this));
            }
        }
        else if(arg5 == NotificationCenter.FileDidFailUpload) {
            v5 = arg7[0];
            if(this.uploadingAvatar != null && (this.uploadingAvatar.equals(v5))) {
                this.uploadingAvatar = null;
            }
        }
        else if(arg5 == NotificationCenter.messageReceivedByServer) {
            v5 = arg7[0];
            v6 = arg7[1];
            Object v7 = arg7[3];
            Object v0 = this.dialogMessage.get(((Long)v7).longValue());
            if(v0 != null && (((MessageObject)v0).getId() == ((Integer)v5).intValue() || ((MessageObject)v0).messageOwner.local_id == ((Integer)v5).intValue())) {
                ((MessageObject)v0).messageOwner.id = ((Integer)v6).intValue();
                ((MessageObject)v0).messageOwner.send_state = 0;
            }

            v7 = this.dialogs_dict.get(((Long)v7).longValue());
            if(v7 != null && ((TL_dialog)v7).top_message == ((Integer)v5).intValue()) {
                ((TL_dialog)v7).top_message = ((Integer)v6).intValue();
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
            }

            v7 = this.dialogMessagesByIds.get(((Integer)v5).intValue());
            this.dialogMessagesByIds.remove(((Integer)v5).intValue());
            if(v7 == null) {
                return;
            }

            this.dialogMessagesByIds.put(((Integer)v6).intValue(), v7);
        }
        else {
            if(arg5 != NotificationCenter.updateMessageMedia) {
                return;
            }

            v5 = arg7[0];
            v6 = this.dialogMessagesByIds.get(((Message)v5).id);
            if(v6 == null) {
                return;
            }

            ((MessageObject)v6).messageOwner.media = ((Message)v5).media;
            if(((Message)v5).media.ttl_seconds == 0) {
                return;
            }

            if(!(((Message)v5).media.photo instanceof TL_photoEmpty) && !(((Message)v5).media.document instanceof TL_documentEmpty)) {
                return;
            }

            ((MessageObject)v6).setType();
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
        }
    }

    public void favSelectedDialogs() {
        ArrayList v0 = this.getSelectedDialogs();
        int v1;
        for(v1 = 0; v1 < v0.size(); ++v1) {
            long v2 = v0.get(v1).id;
            Object v4 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(v2);
            if(!Favourite.isFavourite(Long.valueOf(((TL_dialog)v4).id))) {
                Favourite.addFavourite(Long.valueOf(v2));
                MessagesController.getInstance(this.currentAccount).dialogsFavs.add(v4);
            }
        }
    }

    public void forceResetDialogs() {
        this.resetDialogs(true, MessagesStorage.getInstance(this.currentAccount).getLastSeqValue(), MessagesStorage.getInstance(this.currentAccount).getLastPtsValue(), MessagesStorage.getInstance(this.currentAccount).getLastDateValue(), MessagesStorage.getInstance(this.currentAccount).getLastQtsValue());
    }

    public void generateJoinMessage(int arg9, boolean arg10) {
        Chat v0 = this.getChat(Integer.valueOf(arg9));
        if(v0 != null && (ChatObject.isChannel(arg9, this.currentAccount)) && (!v0.left && !v0.kicked || (arg10))) {
            TL_messageService v10 = new TL_messageService();
            v10.flags = 256;
            int v1 = UserConfig.getInstance(this.currentAccount).getNewMessageId();
            v10.id = v1;
            v10.local_id = v1;
            v10.date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            v10.from_id = UserConfig.getInstance(this.currentAccount).getClientUserId();
            v10.to_id = new TL_peerChannel();
            v10.to_id.channel_id = arg9;
            v10.dialog_id = ((long)(-arg9));
            v10.post = true;
            v10.action = new TL_messageActionChatAddUser();
            v10.action.users.add(Integer.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
            if(v0.megagroup) {
                v10.flags |= -2147483648;
            }

            UserConfig.getInstance(this.currentAccount).saveConfig(false);
            ArrayList v0_1 = new ArrayList();
            ArrayList v3 = new ArrayList();
            v3.add(v10);
            v0_1.add(new MessageObject(this.currentAccount, ((Message)v10), true));
            MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$EMGnOYFDalGmKIMEWWin6VUaLx4(this, v0_1));
            MessagesStorage.getInstance(this.currentAccount).putMessages(v3, true, true, false, 0);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$6dMUrIexzzsr9q4TY8MHmZs6hJs(this, arg9, v0_1));
        }
    }

    public void generateUpdateMessage() {
        if(!BuildVars.DEBUG_VERSION && SharedConfig.lastUpdateVersion != null) {
            if(SharedConfig.lastUpdateVersion.equals(BuildVars.BUILD_VERSION_STRING)) {
            }
            else {
                TL_help_getAppChangelog v0 = new TL_help_getAppChangelog();
                v0.prev_app_version = SharedConfig.lastUpdateVersion;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$cRbTWs4PFG1pcMU-4X-ndp6lAI4(this));
            }
        }
    }

    public void getBlockedUsers(boolean arg3) {
        if(UserConfig.getInstance(this.currentAccount).isClientActivated()) {
            if(this.loadingBlockedUsers) {
            }
            else {
                this.loadingBlockedUsers = true;
                if(arg3) {
                    MessagesStorage.getInstance(this.currentAccount).getBlockedUsers();
                }
                else {
                    TL_contacts_getBlocked v3 = new TL_contacts_getBlocked();
                    v3.offset = 0;
                    v3.limit = 200;
                    ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v3), new -$$Lambda$MessagesController$CfljIetL-_tXP2H2NmLwM5jAtc4(this));
                }
            }
        }
    }

    protected void getChannelDifference(int arg16, int arg17, long arg18, InputChannel arg20) {
        int v0_2;
        NativeByteBuffer v10_1;
        InputChannel v2_1;
        int v9;
        int v8;
        MessagesController v7 = this;
        int v3 = arg16;
        int v4 = arg17;
        long v0 = arg18;
        if(v7.gettingDifferenceChannels.get(v3)) {
            return;
        }

        int v5 = 3;
        boolean v6 = true;
        if(v4 != 1) {
            v8 = v7.channelsPts.get(v3);
            if(v8 == 0) {
                v8 = MessagesStorage.getInstance(v7.currentAccount).getChannelPtsSync(v3);
                if(v8 != 0) {
                    v7.channelsPts.put(v3, v8);
                }

                if(v8 != 0) {
                    goto label_33;
                }

                if(v4 != 2 && v4 != v5) {
                    goto label_33;
                }

                return;
            }

        label_33:
            if(v8 == 0) {
                return;
            }

            v9 = v8;
            v8 = 100;
        }
        else if(v7.channelsPts.get(v3) != 0) {
            return;
        }
        else {
            v8 = 1;
            v9 = 1;
        }

        if(arg20 == null) {
            Chat v2 = this.getChat(Integer.valueOf(arg16));
            if(v2 == null) {
                v2 = MessagesStorage.getInstance(v7.currentAccount).getChatSync(v3);
                if(v2 != null) {
                    this.putChat(v2, true);
                }
            }

            v2_1 = MessagesController.getInputChannel(v2);
        }
        else {
            v2_1 = arg20;
        }

        long v10 = 0;
        if(v2_1 != null) {
            if(v2_1.access_hash == v10) {
            }
            else if(v0 == v10) {
                NativeByteBuffer v1 = null;
                try {
                    v10_1 = new NativeByteBuffer(v2_1.getObjectSize() + 12);
                    v0_2 = 6;
                }
                catch(Exception v0_1) {
                    v10_1 = v1;
                    goto label_70;
                }

                try {
                    v10_1.writeInt32(v0_2);
                    v10_1.writeInt32(v3);
                    v10_1.writeInt32(v4);
                    v2_1.serializeToStream(((AbstractSerializedData)v10_1));
                    goto label_71;
                }
                catch(Exception v0_1) {
                }

            label_70:
                FileLog.e(((Throwable)v0_1));
            label_71:
                v0 = MessagesStorage.getInstance(v7.currentAccount).createPendingTask(v10_1);
                goto label_74;
            }
            else {
            label_74:
                v10 = v0;
                v7.gettingDifferenceChannels.put(v3, true);
                TL_updates_getChannelDifference v0_3 = new TL_updates_getChannelDifference();
                v0_3.channel = v2_1;
                v0_3.filter = new TL_channelMessagesFilterEmpty();
                v0_3.pts = v9;
                v0_3.limit = v8;
                if(v4 != v5) {
                }
                else {
                    v6 = false;
                }

                v0_3.force = v6;
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("start getChannelDifference with pts = " + v9 + " channelId = " + v3);
                }

                ConnectionsManager.getInstance(v7.currentAccount).sendRequest(((TLObject)v0_3), new -$$Lambda$MessagesController$pXp2fbY7dK2jKlm5Yew-7uBFY8U(this, arg16, arg17, v10));
                return;
            }
        }

        if(v0 != v10) {
            MessagesStorage.getInstance(v7.currentAccount).removePendingTask(v0);
        }
    }

    private void getChannelDifference(int arg7) {
        this.getChannelDifference(arg7, 0, 0, null);
    }

    public Chat getChat(Integer arg2) {
        return this.chats.get(arg2);
    }

    public void getDifference() {
        this.getDifference(MessagesStorage.getInstance(this.currentAccount).getLastPtsValue(), MessagesStorage.getInstance(this.currentAccount).getLastDateValue(), MessagesStorage.getInstance(this.currentAccount).getLastQtsValue(), false);
    }

    public void getDifference(int arg4, int arg5, int arg6, boolean arg7) {
        this.registerForPush(SharedConfig.pushString);
        if(MessagesStorage.getInstance(this.currentAccount).getLastPtsValue() == 0) {
            this.loadCurrentState();
            return;
        }

        if(!arg7 && (this.gettingDifference)) {
            return;
        }

        this.gettingDifference = true;
        TL_updates_getDifference v0 = new TL_updates_getDifference();
        v0.pts = arg4;
        v0.date = arg5;
        v0.qts = arg6;
        if(this.getDifferenceFirstSync) {
            v0.flags |= 1;
            int v1 = ConnectionsManager.isConnectedOrConnectingToWiFi() ? 5000 : 1000;
            v0.pts_total_limit = v1;
            this.getDifferenceFirstSync = false;
        }

        if(v0.date == 0) {
            v0.date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("start getDifference with date = " + arg5 + " pts = " + arg4 + " qts = " + arg6);
        }

        ConnectionsManager.getInstance(this.currentAccount).setIsUpdating(true);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$kRqzlsQSI5t91XE6mM-NMTAz66o(this, arg5, arg6));
    }

    public static SharedPreferences getEmojiSettings(int arg0) {
        return MessagesController.getInstance(arg0).emojiPreferences;
    }

    public EncryptedChat getEncryptedChat(Integer arg2) {
        return this.encryptedChats.get(arg2);
    }

    public EncryptedChat getEncryptedChatDB(int arg5, boolean arg6) {
        Object v0 = this.encryptedChats.get(Integer.valueOf(arg5));
        if(v0 != null) {
            if(!arg6) {
                goto label_30;
            }
            else if(!(v0 instanceof TL_encryptedChatWaiting)) {
                if((v0 instanceof TL_encryptedChatRequested)) {
                    goto label_9;
                }

                goto label_30;
            }
        }

    label_9:
        CountDownLatch v6 = new CountDownLatch(1);
        ArrayList v2 = new ArrayList();
        MessagesStorage.getInstance(this.currentAccount).getEncryptedChat(arg5, v6, v2);
        try {
            v6.await();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

        if(v2.size() == 2) {
            v0 = v2.get(0);
            Object v6_1 = v2.get(1);
            this.putEncryptedChat(((EncryptedChat)v0), false);
            this.putUser(((User)v6_1), true);
        }

    label_30:
        return ((EncryptedChat)v0);
    }

    public ExportedChatInvite getExportedInvite(int arg2) {
        return this.exportedChats.get(arg2);
    }

    public static SharedPreferences getGlobalEmojiSettings() {
        return MessagesController.getInstance(0).emojiPreferences;
    }

    public static SharedPreferences getGlobalMainSettings() {
        return MessagesController.getInstance(0).mainPreferences;
    }

    public static SharedPreferences getGlobalNotificationsSettings() {
        return MessagesController.getInstance(0).notificationsPreferences;
    }

    public InputChannel getInputChannel(int arg1) {
        return MessagesController.getInputChannel(this.getChat(Integer.valueOf(arg1)));
    }

    public static InputChannel getInputChannel(Chat arg3) {
        if(!(arg3 instanceof TL_channel)) {
            if((arg3 instanceof TL_channelForbidden)) {
            }
            else {
                return new TL_inputChannelEmpty();
            }
        }

        TL_inputChannel v0 = new TL_inputChannel();
        ((InputChannel)v0).channel_id = arg3.id;
        ((InputChannel)v0).access_hash = arg3.access_hash;
        return ((InputChannel)v0);
    }

    public InputPeer getInputPeer(int arg5) {
        TL_inputPeerChat v1_1;
        long v2;
        if(arg5 < 0) {
            arg5 = -arg5;
            Chat v0 = this.getChat(Integer.valueOf(arg5));
            if(ChatObject.isChannel(v0)) {
                TL_inputPeerChannel v1 = new TL_inputPeerChannel();
                ((InputPeer)v1).channel_id = arg5;
                v2 = v0.access_hash;
                goto label_10;
            }
            else {
                v1_1 = new TL_inputPeerChat();
                ((InputPeer)v1_1).chat_id = arg5;
            }
        }
        else {
            User v0_1 = this.getUser(Integer.valueOf(arg5));
            TL_inputPeerUser v1_2 = new TL_inputPeerUser();
            ((InputPeer)v1_2).user_id = arg5;
            if(v0_1 != null) {
                v2 = v0_1.access_hash;
            label_10:
                ((InputPeer)v1_2).access_hash = v2;
            }
        }

        return ((InputPeer)v1_1);
    }

    public InputUser getInputUser(int arg2) {
        return this.getInputUser(MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(arg2)));
    }

    public InputUser getInputUser(User arg4) {
        TL_inputUser v4_1;
        if(arg4 == null) {
            return new TL_inputUserEmpty();
        }

        if(arg4.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
            TL_inputUserSelf v4 = new TL_inputUserSelf();
        }
        else {
            TL_inputUser v0 = new TL_inputUser();
            ((InputUser)v0).user_id = arg4.id;
            ((InputUser)v0).access_hash = arg4.access_hash;
            v4_1 = v0;
        }

        return ((InputUser)v4_1);
    }

    public static MessagesController getInstance(int arg3) {
        MessagesController v0 = MessagesController.Instance[arg3];
        if(v0 == null) {
            Class v1 = MessagesController.class;
            __monitor_enter(v1);
            try {
                v0 = MessagesController.Instance[arg3];
                if(v0 == null) {
                    MessagesController[] v0_1 = MessagesController.Instance;
                    MessagesController v2 = new MessagesController(arg3);
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

    public static SharedPreferences getMainSettings(int arg0) {
        return MessagesController.getInstance(arg0).mainPreferences;
    }

    public void getNewDeleteTask(ArrayList arg3, int arg4) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$IKcwspzADidL6yrdeJdxQe2pkBs(this, arg3, arg4));
    }

    public static SharedPreferences getNotificationsSettings(int arg0) {
        return MessagesController.getInstance(arg0).notificationsPreferences;
    }

    public Peer getPeer(int arg3) {
        TL_peerChat v0_1;
        if(arg3 < 0) {
            arg3 = -arg3;
            Chat v0 = this.getChat(Integer.valueOf(arg3));
            if(!(v0 instanceof TL_channel)) {
                if((v0 instanceof TL_channelForbidden)) {
                }
                else {
                    v0_1 = new TL_peerChat();
                    ((Peer)v0_1).chat_id = arg3;
                    goto label_22;
                }
            }

            TL_peerChannel v0_2 = new TL_peerChannel();
            ((Peer)v0_2).channel_id = arg3;
        }
        else {
            this.getUser(Integer.valueOf(arg3));
            TL_peerUser v0_3 = new TL_peerUser();
            ((Peer)v0_3).user_id = arg3;
        }

    label_22:
        return ((Peer)v0_1);
    }

    private static String getRestrictionReason(String arg4) {
        String v0 = null;
        if(arg4 != null) {
            if(arg4.length() == 0) {
            }
            else {
                int v1 = arg4.indexOf(": ");
                if(v1 > 0) {
                    String v2 = arg4.substring(0, v1);
                    if(!v2.contains("-all") && !v2.contains("-android")) {
                        return v0;
                    }

                    return arg4.substring(v1 + 2);
                }
            }
        }

        return v0;
    }

    public ArrayList getSelectedDialogs() {
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.dialogs.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            if(!((TL_dialog)v2).isSelected()) {
                continue;
            }

            v0.add(v2);
        }

        return v0;
    }

    private static int getUpdateChannelId(Update arg2) {
        if((arg2 instanceof TL_updateNewChannelMessage)) {
            return ((TL_updateNewChannelMessage)arg2).message.to_id.channel_id;
        }

        if((arg2 instanceof TL_updateEditChannelMessage)) {
            return ((TL_updateEditChannelMessage)arg2).message.to_id.channel_id;
        }

        if((arg2 instanceof TL_updateReadChannelOutbox)) {
            return ((TL_updateReadChannelOutbox)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateChannelMessageViews)) {
            return ((TL_updateChannelMessageViews)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateChannelTooLong)) {
            return ((TL_updateChannelTooLong)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateChannelPinnedMessage)) {
            return ((TL_updateChannelPinnedMessage)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateChannelReadMessagesContents)) {
            return ((TL_updateChannelReadMessagesContents)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateChannelAvailableMessages)) {
            return ((TL_updateChannelAvailableMessages)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateChannel)) {
            return ((TL_updateChannel)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateChannelWebPage)) {
            return ((TL_updateChannelWebPage)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateDeleteChannelMessages)) {
            return ((TL_updateDeleteChannelMessages)arg2).channel_id;
        }

        if((arg2 instanceof TL_updateReadChannelInbox)) {
            return ((TL_updateReadChannelInbox)arg2).channel_id;
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.e("trying to get unknown update channel_id for " + arg2);
        }

        return 0;
    }

    private static int getUpdatePts(Update arg1) {
        if((arg1 instanceof TL_updateDeleteMessages)) {
            return ((TL_updateDeleteMessages)arg1).pts;
        }

        if((arg1 instanceof TL_updateNewChannelMessage)) {
            return ((TL_updateNewChannelMessage)arg1).pts;
        }

        if((arg1 instanceof TL_updateReadHistoryOutbox)) {
            return ((TL_updateReadHistoryOutbox)arg1).pts;
        }

        if((arg1 instanceof TL_updateNewMessage)) {
            return ((TL_updateNewMessage)arg1).pts;
        }

        if((arg1 instanceof TL_updateEditMessage)) {
            return ((TL_updateEditMessage)arg1).pts;
        }

        if((arg1 instanceof TL_updateWebPage)) {
            return ((TL_updateWebPage)arg1).pts;
        }

        if((arg1 instanceof TL_updateReadHistoryInbox)) {
            return ((TL_updateReadHistoryInbox)arg1).pts;
        }

        if((arg1 instanceof TL_updateChannelWebPage)) {
            return ((TL_updateChannelWebPage)arg1).pts;
        }

        if((arg1 instanceof TL_updateDeleteChannelMessages)) {
            return ((TL_updateDeleteChannelMessages)arg1).pts;
        }

        if((arg1 instanceof TL_updateEditChannelMessage)) {
            return ((TL_updateEditChannelMessage)arg1).pts;
        }

        if((arg1 instanceof TL_updateReadMessagesContents)) {
            return ((TL_updateReadMessagesContents)arg1).pts;
        }

        if((arg1 instanceof TL_updateChannelTooLong)) {
            return ((TL_updateChannelTooLong)arg1).pts;
        }

        return 0;
    }

    private static int getUpdatePtsCount(Update arg1) {
        if((arg1 instanceof TL_updateDeleteMessages)) {
            return ((TL_updateDeleteMessages)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateNewChannelMessage)) {
            return ((TL_updateNewChannelMessage)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateReadHistoryOutbox)) {
            return ((TL_updateReadHistoryOutbox)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateNewMessage)) {
            return ((TL_updateNewMessage)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateEditMessage)) {
            return ((TL_updateEditMessage)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateWebPage)) {
            return ((TL_updateWebPage)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateReadHistoryInbox)) {
            return ((TL_updateReadHistoryInbox)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateChannelWebPage)) {
            return ((TL_updateChannelWebPage)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateDeleteChannelMessages)) {
            return ((TL_updateDeleteChannelMessages)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateEditChannelMessage)) {
            return ((TL_updateEditChannelMessage)arg1).pts_count;
        }

        if((arg1 instanceof TL_updateReadMessagesContents)) {
            return ((TL_updateReadMessagesContents)arg1).pts_count;
        }

        return 0;
    }

    private static int getUpdateQts(Update arg1) {
        if((arg1 instanceof TL_updateNewEncryptedMessage)) {
            return ((TL_updateNewEncryptedMessage)arg1).qts;
        }

        return 0;
    }

    private int getUpdateSeq(Updates arg2) {
        if((arg2 instanceof TL_updatesCombined)) {
            return arg2.seq_start;
        }

        return arg2.seq;
    }

    private int getUpdateType(Update arg2) {
        if(!(arg2 instanceof TL_updateNewMessage) && !(arg2 instanceof TL_updateReadMessagesContents) && !(arg2 instanceof TL_updateReadHistoryInbox) && !(arg2 instanceof TL_updateReadHistoryOutbox) && !(arg2 instanceof TL_updateDeleteMessages) && !(arg2 instanceof TL_updateWebPage)) {
            if((arg2 instanceof TL_updateEditMessage)) {
            }
            else if((arg2 instanceof TL_updateNewEncryptedMessage)) {
                return 1;
            }
            else {
                if(!(arg2 instanceof TL_updateNewChannelMessage) && !(arg2 instanceof TL_updateDeleteChannelMessages) && !(arg2 instanceof TL_updateEditChannelMessage)) {
                    if((arg2 instanceof TL_updateChannelWebPage)) {
                    }
                    else {
                        return 3;
                    }
                }

                return 2;
            }
        }

        return 0;
    }

    public long getUpdatesStartTime(int arg3) {
        if(arg3 == 0) {
            return this.updatesStartWaitTimeSeq;
        }

        if(arg3 == 1) {
            return this.updatesStartWaitTimePts;
        }

        if(arg3 == 2) {
            return this.updatesStartWaitTimeQts;
        }

        return 0;
    }

    public User getUser(Integer arg2) {
        return this.users.get(arg2);
    }

    public TL_userFull getUserFull(int arg2) {
        return this.fullUsers.get(arg2);
    }

    private String getUserNameForTyping(User arg2) {
        if(arg2 == null) {
            return "";
        }

        if(arg2.first_name != null && arg2.first_name.length() > 0) {
            return arg2.first_name;
        }

        if(arg2.last_name != null && arg2.last_name.length() > 0) {
            return arg2.last_name;
        }

        return "";
    }

    public TLObject getUserOrChat(String arg2) {
        if(arg2 != null) {
            if(arg2.length() == 0) {
            }
            else {
                return this.objectsByUsernames.get(arg2.toLowerCase());
            }
        }

        return null;
    }

    public ConcurrentHashMap getUsers() {
        return this.users;
    }

    public void hideReportSpam(long arg4, User arg6, Chat arg7) {
        int v5;
        if(arg6 == null && arg7 == null) {
            return;
        }

        SharedPreferences$Editor v0 = this.notificationsPreferences.edit();
        v0.putInt("spam3_" + arg4, 1);
        v0.commit();
        if((((int)arg4)) != 0) {
            TL_messages_hideReportSpam v4 = new TL_messages_hideReportSpam();
            if(arg6 != null) {
                v5 = arg6.id;
                goto label_20;
            }
            else if(arg7 != null) {
                v5 = -arg7.id;
            label_20:
                v4.peer = this.getInputPeer(v5);
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v4), -$$Lambda$MessagesController$CdfNy-4vWadAwS2mlkIXB5Nk9t4.INSTANCE);
        }
    }

    public boolean isChannelAdmin(int arg2, int arg3) {
        Object v2 = this.channelAdmins.get(arg2);
        boolean v2_1 = v2 == null || ((ArrayList)v2).indexOf(Integer.valueOf(arg3)) < 0 ? false : true;
        return v2_1;
    }

    public boolean isDialogCreated(long arg2) {
        return this.createdDialogMainThreadIds.contains(Long.valueOf(arg2));
    }

    public boolean isDialogMuted(long arg6) {
        SharedPreferences v0 = this.notificationsPreferences;
        StringBuilder v1 = new StringBuilder();
        v1.append("notify2_");
        v1.append(arg6);
        int v0_1 = v0.getInt(v1.toString(), -1);
        if(v0_1 == -1) {
            if((((int)arg6)) < 0) {
                if(!this.notificationsPreferences.getBoolean("EnableGroup", true)) {
                    return 1;
                }
            }
            else if(!this.notificationsPreferences.getBoolean("EnableAll", true)) {
                return 1;
            }
        }

        if(v0_1 == 2) {
            return 1;
        }

        if(v0_1 == 3) {
            v0 = this.notificationsPreferences;
            StringBuilder v2 = new StringBuilder();
            v2.append("notifyuntil_");
            v2.append(arg6);
            if(v0.getInt(v2.toString(), 0) >= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) {
                return 1;
            }
        }

        return 0;
    }

    public boolean isDialogVisible(long arg2) {
        return this.visibleDialogMainThreadIds.contains(Long.valueOf(arg2));
    }

    public boolean isProxyDialog(long arg4) {
        boolean v4 = this.proxyDialog == null || this.proxyDialog.id != arg4 || !this.isLeftProxyChannel ? false : true;
        return v4;
    }

    public static boolean isSupportId(int arg2) {
        boolean v2;
        if(arg2 / 1000 == 777 || arg2 == 333000) {
        label_63:
            v2 = true;
        }
        else {
            int v0 = 4240000;
            if(arg2 == v0) {
                goto label_63;
            }
            else if(arg2 == v0) {
                goto label_63;
            }
            else if(arg2 == 4244000) {
                goto label_63;
            }
            else if(arg2 == 4245000) {
                goto label_63;
            }
            else if(arg2 == 4246000) {
                goto label_63;
            }
            else if(arg2 == 410000) {
                goto label_63;
            }
            else if(arg2 == 420000) {
                goto label_63;
            }
            else if(arg2 == 431000) {
                goto label_63;
            }
            else if(arg2 == 431415000) {
                goto label_63;
            }
            else if(arg2 == 434000) {
                goto label_63;
            }
            else if(arg2 == 4243000) {
                goto label_63;
            }
            else if(arg2 == 439000) {
                goto label_63;
            }
            else if(arg2 == 449000) {
                goto label_63;
            }
            else if(arg2 == 450000) {
                goto label_63;
            }
            else if(arg2 == 452000) {
                goto label_63;
            }
            else if(arg2 == 454000) {
                goto label_63;
            }
            else if(arg2 == 4254000) {
                goto label_63;
            }
            else if(arg2 == 455000) {
                goto label_63;
            }
            else if(arg2 == 460000) {
                goto label_63;
            }
            else if(arg2 == 470000) {
                goto label_63;
            }
            else if(arg2 == 479000) {
                goto label_63;
            }
            else if(arg2 == 796000) {
                goto label_63;
            }
            else if(arg2 == 482000) {
                goto label_63;
            }
            else if(arg2 == 490000) {
                goto label_63;
            }
            else if(arg2 == 496000) {
                goto label_63;
            }
            else if(arg2 == 497000) {
                goto label_63;
            }
            else if(arg2 == 498000) {
                goto label_63;
            }
            else if(arg2 == 4298000) {
                goto label_63;
            }
            else {
                v2 = false;
            }
        }

        return v2;
    }

    private int isValidUpdate(Updates arg5, int arg6) {
        int v0 = 2;
        if(arg6 == 0) {
            int v5 = this.getUpdateSeq(arg5);
            if(MessagesStorage.getInstance(this.currentAccount).getLastSeqValue() + 1 != v5) {
                if(MessagesStorage.getInstance(this.currentAccount).getLastSeqValue() == v5) {
                }
                else if(MessagesStorage.getInstance(this.currentAccount).getLastSeqValue() < v5) {
                    return 1;
                }
                else {
                    return v0;
                }
            }

            return 0;
        }

        if(arg6 == 1) {
            if(arg5.pts <= MessagesStorage.getInstance(this.currentAccount).getLastPtsValue()) {
                return v0;
            }

            if(MessagesStorage.getInstance(this.currentAccount).getLastPtsValue() + arg5.pts_count == arg5.pts) {
                return 0;
            }

            return 1;
        }

        if(arg6 == v0) {
            if(arg5.pts <= MessagesStorage.getInstance(this.currentAccount).getLastQtsValue()) {
                return v0;
            }

            if(MessagesStorage.getInstance(this.currentAccount).getLastQtsValue() + arg5.updates.size() == arg5.pts) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public static void lambda$addToViewsQueue$118(MessagesController arg3, Message arg4) {
        ArrayList v1_1;
        int v0;
        if(arg4.to_id.channel_id != 0) {
            v0 = arg4.to_id.channel_id;
            goto label_5;
        }
        else if(arg4.to_id.chat_id != 0) {
            v0 = arg4.to_id.chat_id;
        label_5:
            v0 = -v0;
        }
        else {
            v0 = arg4.to_id.user_id;
        }

        Object v1 = arg3.channelViewsToSend.get(v0);
        if(v1 == null) {
            v1_1 = new ArrayList();
            arg3.channelViewsToSend.put(v0, v1_1);
        }

        if(!v1_1.contains(Integer.valueOf(arg4.id))) {
            v1_1.add(Integer.valueOf(arg4.id));
        }
    }

    public static void lambda$addUserToChat$161(MessagesController arg7, boolean arg8, InputUser arg9, int arg10, BaseFragment arg11, TLObject arg12, boolean arg13, TLObject arg14, TL_error arg15) {
        if((arg8) && ((arg9 instanceof TL_inputUserSelf))) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$Nqzw2yRpMxa4lZrl4LDMkNxC2z4(arg7, arg10));
        }

        if(arg15 != null) {
            com.crashlytics.android.a.b.c().a(new m("JOIN_ERROR").a("2.2.6", arg15.text));
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$UnmIdoyze-MPgyXQf53Tk1kXAMk(arg7, arg15, arg11, arg12, arg8, arg13));
            return;
        }

        int v12;
        for(v12 = 0; v12 < ((Updates)arg14).updates.size(); ++v12) {
            Object v13 = ((Updates)arg14).updates.get(v12);
            if(((v13 instanceof TL_updateNewChannelMessage)) && ((((TL_updateNewChannelMessage)v13).message.action instanceof TL_messageActionChatAddUser))) {
                v12 = 1;
                goto label_46;
            }
        }

        v12 = 0;
    label_46:
        arg7.processUpdates(((Updates)arg14), false);
        if(arg8) {
            if(v12 == 0 && ((arg9 instanceof TL_inputUserSelf))) {
                arg7.generateJoinMessage(arg10, true);
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$Yuptkzq8qOORC1f8MKf-ZP-xkwQ(arg7, arg10), 1000);
        }

        if((arg8) && ((arg9 instanceof TL_inputUserSelf))) {
            MessagesStorage.getInstance(arg7.currentAccount).updateDialogsWithDeletedMessages(new ArrayList(), null, true, arg10);
        }
    }

    public static void lambda$addUsersToChannel$145(MessagesController arg0, BaseFragment arg1, TL_channels_inviteToChannel arg2, TLObject arg3, TL_error arg4) {
        if(arg4 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$I18ZDb7fLVymMcPWB3305grA1Gk(arg0, arg4, arg1, arg2));
            return;
        }

        arg0.processUpdates(((Updates)arg3), false);
    }

    public static void lambda$blockUser$37(MessagesController arg0, User arg1, TLObject arg2, TL_error arg3) {
        if(arg3 == null) {
            SparseIntArray v2 = new SparseIntArray();
            v2.put(arg1.id, 1);
            MessagesStorage.getInstance(arg0.currentAccount).putBlockedUsers(v2, false);
        }
    }

    public static void lambda$changeChatAvatar$166(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg2 != null) {
            return;
        }

        arg0.processUpdates(((Updates)arg1), false);
    }

    public static void lambda$changeChatTitle$165(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg2 != null) {
            return;
        }

        arg0.processUpdates(((Updates)arg1), false);
    }

    public static void lambda$checkCanOpenChat$235(MessagesController arg6, AlertDialog arg7, BaseFragment arg8, Bundle arg9, TLObject arg10, TL_error arg11) {
        if(arg10 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$WPa0jjxOJFjDB3587Q5WJiO3WSM(arg6, arg7, arg10, arg8, arg9));
        }
    }

    public static void lambda$checkCanOpenChat$236(MessagesController arg1, int arg2, BaseFragment arg3, DialogInterface arg4, int arg5) {
        ConnectionsManager.getInstance(arg1.currentAccount).cancelRequest(arg2, true);
        try {
            arg4.dismiss();
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }

        if(arg3 != null) {
            arg3.setVisibleDialog(null);
        }
    }

    public static void lambda$checkChannelInviter$212(MessagesController arg4, int arg5) {
        Chat v0 = arg4.getChat(Integer.valueOf(arg5));
        if(v0 != null && (ChatObject.isChannel(arg5, arg4.currentAccount))) {
            if(v0.creator) {
            }
            else {
                TL_channels_getParticipant v1 = new TL_channels_getParticipant();
                v1.channel = arg4.getInputChannel(arg5);
                v1.user_id = new TL_inputUserSelf();
                ConnectionsManager.getInstance(arg4.currentAccount).sendRequest(((TLObject)v1), new -$$Lambda$MessagesController$TSOpBoxMCmuq5v1q_3xSBBCwzzI(arg4, v0, arg5));
            }
        }
    }

    public static void lambda$checkDeletingTask$32(MessagesController arg7, ArrayList arg8) {
        if((arg8.isEmpty()) || arg8.get(0).intValue() <= 0) {
            arg7.deleteMessages(arg8, null, null, 0, false);
        }
        else {
            MessagesStorage.getInstance(arg7.currentAccount).emptyMessagesMedia(arg8);
        }

        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$70aW84xOOra8h1CNC1bSMkblm5U(arg7, arg8));
    }

    public static void lambda$checkLastDialogMessage$115(MessagesController arg7, TL_dialog arg8, long arg9, int arg11, TLObject arg12, TL_error arg13) {
        if(arg12 != null) {
            if(!((messages_Messages)arg12).messages.isEmpty()) {
                TL_messages_dialogs v13 = new TL_messages_dialogs();
                Object v0 = ((messages_Messages)arg12).messages.get(0);
                TL_dialog v1 = new TL_dialog();
                v1.flags = arg8.flags;
                v1.top_message = ((Message)v0).id;
                v1.last_message_date = ((Message)v0).date;
                v1.notify_settings = arg8.notify_settings;
                v1.pts = arg8.pts;
                v1.unread_count = arg8.unread_count;
                v1.unread_mark = arg8.unread_mark;
                v1.unread_mentions_count = arg8.unread_mentions_count;
                v1.read_inbox_max_id = arg8.read_inbox_max_id;
                v1.read_outbox_max_id = arg8.read_outbox_max_id;
                v1.pinned = arg8.pinned;
                v1.pinnedNum = arg8.pinnedNum;
                long v2 = arg8.id;
                v1.id = v2;
                ((Message)v0).dialog_id = v2;
                v13.users.addAll(((messages_Messages)arg12).users);
                v13.chats.addAll(((messages_Messages)arg12).chats);
                v13.dialogs.add(v1);
                v13.messages.addAll(((messages_Messages)arg12).messages);
                v13.count = 1;
                arg7.processDialogsUpdate(((messages_Dialogs)v13), null);
                MessagesStorage.getInstance(arg7.currentAccount).putMessages(((messages_Messages)arg12).messages, true, true, false, DownloadController.getInstance(arg7.currentAccount).getAutodownloadMask(), true);
            }
            else {
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$nIgCJIzV_jOqPlp9ujSkWXETzbI(arg7, arg8));
            }
        }

        if(arg9 != 0) {
            MessagesStorage.getInstance(arg7.currentAccount).removePendingTask(arg9);
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$v855xxXlYGVOhwNK3O85NN_n1Bk(arg7, arg11));
    }

    public static void lambda$checkProxyInfo$73(MessagesController arg0, boolean arg1) {
        arg0.checkProxyInfoInternal(arg1);
    }

    public static void lambda$checkProxyInfoInternal$79(MessagesController arg7, TLObject arg8, TL_error arg9) {
        Object v4;
        int v9;
        long v2;
        int v8;
        if(arg7.checkingProxyInfoRequestId == 0) {
            return;
        }

        int v1 = 1;
        if((arg8 instanceof TL_help_proxyDataEmpty)) {
            v8 = ((TL_help_proxyDataEmpty)arg8).expires;
            goto label_8;
        }
        else if((arg8 instanceof TL_help_proxyDataPromo)) {
            if(((TL_help_proxyDataPromo)arg8).peer.user_id != 0) {
                v2 = ((long)((TL_help_proxyDataPromo)arg8).peer.user_id);
                goto label_19;
            }
            else if(((TL_help_proxyDataPromo)arg8).peer.chat_id != 0) {
                v2 = ((long)(-((TL_help_proxyDataPromo)arg8).peer.chat_id));
                v9 = 0;
                while(true) {
                    if(v9 < ((TL_help_proxyDataPromo)arg8).chats.size()) {
                        v4 = ((TL_help_proxyDataPromo)arg8).chats.get(v9);
                        if(((Chat)v4).id != ((TL_help_proxyDataPromo)arg8).peer.chat_id) {
                            ++v9;
                            continue;
                        }
                        else if(!((Chat)v4).kicked) {
                            if(((Chat)v4).restricted) {
                            }
                            else {
                                goto label_19;
                            }
                        }
                    }
                    else {
                        goto label_19;
                    }

                    break;
                }
            }
            else {
                v2 = ((long)(-((TL_help_proxyDataPromo)arg8).peer.channel_id));
                v9 = 0;
                while(true) {
                    if(v9 < ((TL_help_proxyDataPromo)arg8).chats.size()) {
                        v4 = ((TL_help_proxyDataPromo)arg8).chats.get(v9);
                        if(((Chat)v4).id != ((TL_help_proxyDataPromo)arg8).peer.channel_id) {
                            ++v9;
                            continue;
                        }
                        else if(!((Chat)v4).kicked) {
                            if(((Chat)v4).restricted) {
                            }
                            else {
                                break;
                            }
                        }
                    }
                    else {
                        break;
                    }

                    goto label_66;
                }

            label_19:
                v1 = 0;
            }

        label_66:
            arg7.proxyDialogId = v2;
            MessagesController.getGlobalMainSettings().edit().putLong("proxy_dialog", arg7.proxyDialogId).commit();
            arg7.nextProxyInfoCheckTime = ((TL_help_proxyDataPromo)arg8).expires;
            if(v1 != 0) {
                goto label_85;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$qYdsCM0PpjKvU2SGN0u8-HvdBUo(arg7, v2, ((TL_help_proxyDataPromo)arg8)));
        }
        else {
            v8 = ConnectionsManager.getInstance(arg7.currentAccount).getCurrentTime() + 3600;
        label_8:
            arg7.nextProxyInfoCheckTime = v8;
        }

    label_85:
        if(v1 != 0) {
            arg7.proxyDialogId = 0;
            MessagesController.getGlobalMainSettings().edit().putLong("proxy_dialog", arg7.proxyDialogId).commit();
            arg7.checkingProxyInfoRequestId = 0;
            arg7.checkingProxyInfo = false;
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$FHmuVW55FA_UJfuNlqGK-LiwC-A(arg7));
        }
    }

    public static void lambda$checkProxyInfoInternal$80(MessagesController arg5) {
        if(arg5.proxyDialog != null) {
            if(arg5.proxyDialog.id < 0) {
                Chat v0 = arg5.getChat(Integer.valueOf(-(((int)arg5.proxyDialog.id))));
                if(v0 != null && !v0.left && !v0.kicked && !v0.restricted) {
                    goto label_26;
                }

                arg5.dialogs_dict.remove(arg5.proxyDialog.id);
                arg5.dialogs.remove(arg5.proxyDialog);
            }

        label_26:
            arg5.proxyDialog = null;
            arg5.sortDialogs(null);
            NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }
    }

    public static void lambda$checkTosUpdate$72(MessagesController arg1, TLObject arg2, TL_error arg3) {
        int v2;
        arg1.checkingTosUpdate = false;
        if((arg2 instanceof TL_help_termsOfServiceUpdateEmpty)) {
            v2 = ((TL_help_termsOfServiceUpdateEmpty)arg2).expires;
            goto label_5;
        }
        else if((arg2 instanceof TL_help_termsOfServiceUpdate)) {
            arg1.nextTosCheckTime = ((TL_help_termsOfServiceUpdate)arg2).expires;
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$533rjyPjQwIPklbjPbNCFJqWcPo(arg1, ((TL_help_termsOfServiceUpdate)arg2)));
        }
        else {
            v2 = ConnectionsManager.getInstance(arg1.currentAccount).getCurrentTime() + 3600;
        label_5:
            arg1.nextTosCheckTime = v2;
        }

        arg1.notificationsPreferences.edit().putInt("nextTosCheckTime", arg1.nextTosCheckTime).commit();
    }

    public static void lambda$cleanup$5(MessagesController arg2) {
        arg2.readTasks.clear();
        arg2.readTasksMap.clear();
        arg2.updatesQueueSeq.clear();
        arg2.updatesQueuePts.clear();
        arg2.updatesQueueQts.clear();
        arg2.gettingUnknownChannels.clear();
        arg2.updatesStartWaitTimeSeq = 0;
        arg2.updatesStartWaitTimePts = 0;
        arg2.updatesStartWaitTimeQts = 0;
        arg2.createdDialogIds.clear();
        arg2.gettingDifference = false;
        arg2.resetDialogsPinned = null;
        arg2.resetDialogsAll = null;
    }

    public static void lambda$cleanup$6(MessagesController arg2) {
        ConnectionsManager.getInstance(arg2.currentAccount).setIsUpdating(false);
        arg2.updatesQueueChannels.clear();
        arg2.updatesStartWaitTimeChannels.clear();
        arg2.gettingDifferenceChannels.clear();
        arg2.channelsPts.clear();
        arg2.shortPollChannels.clear();
        arg2.needShortPollChannels.clear();
    }

    public static void lambda$completeDialogsReset$101(MessagesController arg1, int arg2, int arg3, int arg4, messages_Dialogs arg5, LongSparseArray arg6, LongSparseArray arg7) {
        arg1.gettingDifference = false;
        MessagesStorage.getInstance(arg1.currentAccount).setLastPtsValue(arg2);
        MessagesStorage.getInstance(arg1.currentAccount).setLastDateValue(arg3);
        MessagesStorage.getInstance(arg1.currentAccount).setLastQtsValue(arg4);
        arg1.getDifference();
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$WcHzBnwq2JmDMGp4H26fmKnB7L4(arg1, arg5, arg6, arg7));
    }

    public static void lambda$completeReadTask$125(MessagesController arg1, TLObject arg2, TL_error arg3) {
        if(arg3 == null && ((arg2 instanceof TL_messages_affectedMessages))) {
            arg1.processNewDifferenceParams(-1, ((TL_messages_affectedMessages)arg2).pts, -1, ((TL_messages_affectedMessages)arg2).pts_count);
        }
    }

    static void lambda$completeReadTask$126(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$convertToMegaGroup$142(MessagesController arg0, Context arg1, AlertDialog arg2, TLObject arg3, TL_error arg4) {
        if(arg4 == null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$7m7Bj4DTdmEzzfg3DIJ4Tid5nHM(arg1, arg2));
            arg0.processUpdates(((Updates)arg3), false);
        }
        else {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$EzGHxCShcLGa9L9E3-sw63SLRhk(arg1, arg2));
        }
    }

    public static void lambda$convertToMegaGroup$143(MessagesController arg1, int arg2, DialogInterface arg3, int arg4) {
        ConnectionsManager.getInstance(arg1.currentAccount).cancelRequest(arg2, true);
        try {
            arg3.dismiss();
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public static void lambda$createChat$136(MessagesController arg0, BaseFragment arg1, TL_messages_createChat arg2, TLObject arg3, TL_error arg4) {
        if(arg4 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$bv5fXaxJuSpyiN9_Jr7w_pheWP4(arg0, arg4, arg1, arg2));
            return;
        }

        arg0.processUpdates(((Updates)arg3), false);
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$VuJudBjneC2NrlVv0g40nsO8ifc(arg0, ((Updates)arg3)));
    }

    public static void lambda$createChat$139(MessagesController arg0, BaseFragment arg1, TL_channels_createChannel arg2, TLObject arg3, TL_error arg4) {
        if(arg4 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$h23eEOIYKX1ARJ3iKezV603ObW8(arg0, arg4, arg1, arg2));
            return;
        }

        arg0.processUpdates(((Updates)arg3), false);
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$VSlzj3SFJzBbo0nN9E0kJLFUJic(arg0, ((Updates)arg3)));
    }

    public static void lambda$deleteDialog$55(MessagesController arg1, long arg2) {
        int v2 = -(((int)arg2));
        arg1.channelsPts.delete(v2);
        arg1.shortPollChannels.delete(v2);
        arg1.needShortPollChannels.delete(v2);
    }

    public static void lambda$deleteDialog$57(MessagesController arg1, long arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$LEGEUxebVarlBE4S1xd8qXlwEp8(arg1, arg2));
    }

    static void lambda$deleteDialog$58(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$deleteDialog$59(MessagesController arg6, long arg7, int arg9, int arg10, TLObject arg11, TL_error arg12) {
        if(arg12 == null) {
            if(((TL_messages_affectedHistory)arg11).offset > 0) {
                arg6.deleteDialog(arg7, false, arg9, arg10);
            }

            arg6.processNewDifferenceParams(-1, ((TL_messages_affectedHistory)arg11).pts, -1, ((TL_messages_affectedHistory)arg11).pts_count);
        }
    }

    public static void lambda$deleteMessages$51(MessagesController arg0, int arg1, long arg2, TLObject arg4, TL_error arg5) {
        if(arg5 == null) {
            arg0.processNewChannelDifferenceParams(((TL_messages_affectedMessages)arg4).pts, ((TL_messages_affectedMessages)arg4).pts_count, arg1);
        }

        if(arg2 != 0) {
            MessagesStorage.getInstance(arg0.currentAccount).removePendingTask(arg2);
        }
    }

    public static void lambda$deleteMessages$52(MessagesController arg1, long arg2, TLObject arg4, TL_error arg5) {
        if(arg5 == null) {
            arg1.processNewDifferenceParams(-1, ((TL_messages_affectedMessages)arg4).pts, -1, ((TL_messages_affectedMessages)arg4).pts_count);
        }

        if(arg2 != 0) {
            MessagesStorage.getInstance(arg1.currentAccount).removePendingTask(arg2);
        }
    }

    public static void lambda$deleteUserChannelHistory$54(MessagesController arg0, Chat arg1, User arg2, TLObject arg3, TL_error arg4) {
        if(arg4 == null) {
            if(((TL_messages_affectedHistory)arg3).offset > 0) {
                arg0.deleteUserChannelHistory(arg1, arg2, ((TL_messages_affectedHistory)arg3).offset);
            }

            arg0.processNewChannelDifferenceParams(((TL_messages_affectedHistory)arg3).pts, ((TL_messages_affectedHistory)arg3).pts_count, arg1.id);
        }
    }

    public static void lambda$deleteUserFromChat$164(MessagesController arg1, User arg2, int arg3, boolean arg4, InputUser arg5, TLObject arg6, TL_error arg7) {
        if(arg2.id == UserConfig.getInstance(arg1.currentAccount).getClientUserId()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$7yumbynhftklRxaHZgAiLfrhNA4(arg1, arg3));
        }

        if(arg7 != null) {
            return;
        }

        arg1.processUpdates(((Updates)arg6), false);
        if((arg4) && !(arg5 instanceof TL_inputUserSelf)) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$Zm7fKbHrlwLlDYTmuYXOYmNR1zg(arg1, arg3), 1000);
        }
    }

    public static void lambda$deleteUserPhoto$48(MessagesController arg5, TLObject arg6, TL_error arg7) {
        if(arg7 == null) {
            User v7 = arg5.getUser(Integer.valueOf(UserConfig.getInstance(arg5.currentAccount).getClientUserId()));
            if(v7 == null) {
                v7 = UserConfig.getInstance(arg5.currentAccount).getCurrentUser();
                arg5.putUser(v7, false);
            }
            else {
                UserConfig.getInstance(arg5.currentAccount).setCurrentUser(v7);
            }

            if(v7 == null) {
                return;
            }

            MessagesStorage.getInstance(arg5.currentAccount).clearUserPhotos(v7.id);
            ArrayList v1 = new ArrayList();
            v1.add(v7);
            MessagesStorage.getInstance(arg5.currentAccount).putUsersAndChats(v1, null, false, true);
            v7.photo = ((UserProfilePhoto)arg6);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$NzBw577ZPmu5hz8jpkWWQmNPzxI(arg5));
        }
    }

    static void lambda$deleteUserPhoto$49(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$didAddedNewTask$28(MessagesController arg1, int arg2) {
        if(arg1.currentDeletingTaskMids == null && !arg1.gettingNewDeleteTask || arg1.currentDeletingTaskTime != 0 && arg2 < arg1.currentDeletingTaskTime) {
            arg1.getNewDeleteTask(null, 0);
        }
    }

    public static void lambda$didAddedNewTask$29(MessagesController arg4, SparseArray arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.didCreatedNewDeleteTask, new Object[]{arg5});
    }

    public static void lambda$didReceivedNotification$4(MessagesController arg6, TLObject arg7, TL_error arg8) {
        if(arg8 == null) {
            User v8 = arg6.getUser(Integer.valueOf(UserConfig.getInstance(arg6.currentAccount).getClientUserId()));
            if(v8 == null) {
                v8 = UserConfig.getInstance(arg6.currentAccount).getCurrentUser();
                arg6.putUser(v8, true);
            }
            else {
                UserConfig.getInstance(arg6.currentAccount).setCurrentUser(v8);
            }

            if(v8 == null) {
                return;
            }

            ArrayList v1 = ((TL_photos_photo)arg7).photo.sizes;
            PhotoSize v2 = FileLoader.getClosestPhotoSizeWithSize(v1, 100);
            PhotoSize v1_1 = FileLoader.getClosestPhotoSizeWithSize(v1, 1000);
            v8.photo = new TL_userProfilePhoto();
            v8.photo.photo_id = ((TL_photos_photo)arg7).photo.id;
            if(v2 != null) {
                v8.photo.photo_small = v2.location;
            }

            if(v1_1 != null) {
                v8.photo.photo_big = v1_1.location;
            }
            else if(v2 != null) {
                v8.photo.photo_small = v2.location;
            }

            MessagesStorage.getInstance(arg6.currentAccount).clearUserPhotos(v8.id);
            ArrayList v7 = new ArrayList();
            v7.add(v8);
            MessagesStorage.getInstance(arg6.currentAccount).putUsersAndChats(v7, null, false, true);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$VkYWRm7oeBHADU-2qscNSGE0Axs(arg6));
        }
    }

    public static void lambda$generateJoinMessage$205(MessagesController arg1, ArrayList arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$aYFpoaALVJ4D4EySEAN2TrR3ZWM(arg1, arg2));
    }

    public static void lambda$generateJoinMessage$206(MessagesController arg2, int arg3, ArrayList arg4) {
        arg2.updateInterfaceWithMessages(((long)(-arg3)), arg4);
        NotificationCenter.getInstance(arg2.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$generateUpdateMessage$169(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg2 == null) {
            SharedConfig.lastUpdateVersion = BuildVars.BUILD_VERSION_STRING;
            SharedConfig.saveConfig();
        }

        if((arg1 instanceof Updates)) {
            arg0.processUpdates(((Updates)arg1), false);
        }
    }

    public static void lambda$getBlockedUsers$45(MessagesController arg4, TLObject arg5, TL_error arg6) {
        ArrayList v6_1;
        SparseIntArray v0 = new SparseIntArray();
        ArrayList v1 = null;
        if(arg6 == null) {
            Iterator v6 = ((contacts_Blocked)arg5).blocked.iterator();
            while(v6.hasNext()) {
                v0.put(v6.next().user_id, 1);
            }

            v6_1 = ((contacts_Blocked)arg5).users;
            MessagesStorage.getInstance(arg4.currentAccount).putUsersAndChats(((contacts_Blocked)arg5).users, v1, true, true);
            MessagesStorage.getInstance(arg4.currentAccount).putBlockedUsers(v0, true);
        }
        else {
            v6_1 = v1;
        }

        arg4.processLoadedBlockedUsers(v0, v6_1, false);
    }

    public static void lambda$getChannelDifference$187(MessagesController arg13, int arg14, int arg15, long arg16, TLObject arg18, TL_error arg19) {
        Object v5;
        MessagesController v10 = arg13;
        int v3 = arg14;
        long v8 = arg16;
        TL_error v0 = arg19;
        if(v0 == null) {
            TLObject v4 = arg18;
            SparseArray v6 = new SparseArray();
            int v0_1 = 0;
            int v1;
            for(v1 = 0; v1 < ((updates_ChannelDifference)v4).users.size(); ++v1) {
                Object v2 = ((updates_ChannelDifference)v4).users.get(v1);
                v6.put(((User)v2).id, v2);
            }

            Chat v1_1 = null;
            int v2_1 = 0;
            while(true) {
                if(v2_1 < ((updates_ChannelDifference)v4).chats.size()) {
                    v5 = ((updates_ChannelDifference)v4).chats.get(v2_1);
                    if(((Chat)v5).id == v3) {
                    }
                    else {
                        ++v2_1;
                        continue;
                    }
                }
                else {
                    break;
                }

                goto label_32;
            }

            Chat v5_1 = v1_1;
        label_32:
            ArrayList v2_2 = new ArrayList();
            if(!((updates_ChannelDifference)v4).other_updates.isEmpty()) {
                while(v0_1 < ((updates_ChannelDifference)v4).other_updates.size()) {
                    Object v1_2 = ((updates_ChannelDifference)v4).other_updates.get(v0_1);
                    if((v1_2 instanceof TL_updateMessageID)) {
                        v2_2.add(v1_2);
                        ((updates_ChannelDifference)v4).other_updates.remove(v0_1);
                        --v0_1;
                    }

                    ++v0_1;
                }
            }

            MessagesStorage.getInstance(v10.currentAccount).putUsersAndChats(((updates_ChannelDifference)v4).users, ((updates_ChannelDifference)v4).chats, true, true);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$ty927wQ-IbfXL5_rgY0BtknV_xc(arg13, ((updates_ChannelDifference)v4)));
            MessagesStorage.getInstance(v10.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$rlN_p437Bf-NvqxV3PBWIwMMslQ(arg13, v2_2, arg14, ((updates_ChannelDifference)v4), ((Chat)v5), v6, arg15, arg16));
        }
        else {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$kmTsFgq1HunbwGsW2q4UxRgXQ6g(arg13, v0, arg14));
            v10.gettingDifferenceChannels.delete(arg14);
            if(v8 == 0) {
                return;
            }

            MessagesStorage.getInstance(v10.currentAccount).removePendingTask(v8);
        }
    }

    public static void lambda$getDifference$196(MessagesController arg7, int arg8, int arg9, TLObject arg10, TL_error arg11) {
        Object v10;
        int v0 = 0;
        if(arg11 == null) {
            TLObject v3 = arg10;
            if((v3 instanceof TL_updates_differenceTooLong)) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$PmOTQHYJrJIKpfRGWRqwyY9R3Sg(arg7, ((updates_Difference)v3), arg8, arg9));
            }
            else {
                if((v3 instanceof TL_updates_differenceSlice)) {
                    arg7.getDifference(((updates_Difference)v3).intermediate_state.pts, ((updates_Difference)v3).intermediate_state.date, ((updates_Difference)v3).intermediate_state.qts, true);
                }

                SparseArray v5 = new SparseArray();
                SparseArray v6 = new SparseArray();
                for(arg8 = 0; arg8 < ((updates_Difference)v3).users.size(); ++arg8) {
                    v10 = ((updates_Difference)v3).users.get(arg8);
                    v5.put(((User)v10).id, v10);
                }

                for(arg8 = 0; arg8 < ((updates_Difference)v3).chats.size(); ++arg8) {
                    v10 = ((updates_Difference)v3).chats.get(arg8);
                    v6.put(((Chat)v10).id, v10);
                }

                ArrayList v4 = new ArrayList();
                if(!((updates_Difference)v3).other_updates.isEmpty()) {
                    while(v0 < ((updates_Difference)v3).other_updates.size()) {
                        Object v8 = ((updates_Difference)v3).other_updates.get(v0);
                        if((v8 instanceof TL_updateMessageID)) {
                            v4.add(v8);
                            goto label_56;
                        }
                        else if(arg7.getUpdateType(((Update)v8)) == 2) {
                            int v10_1 = MessagesController.getUpdateChannelId(((Update)v8));
                            int v11 = arg7.channelsPts.get(v10_1);
                            if(v11 == 0) {
                                v11 = MessagesStorage.getInstance(arg7.currentAccount).getChannelPtsSync(v10_1);
                                if(v11 != 0) {
                                    arg7.channelsPts.put(v10_1, v11);
                                }
                            }

                            if(v11 == 0) {
                                goto label_77;
                            }

                            if(MessagesController.getUpdatePts(((Update)v8)) > v11) {
                                goto label_77;
                            }

                        label_56:
                            ((updates_Difference)v3).other_updates.remove(v0);
                            --v0;
                        }

                    label_77:
                        ++v0;
                    }
                }

                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$DDgJyq7PpANxlN8pCB1WQqGDjQU(arg7, ((updates_Difference)v3)));
                MessagesStorage.getInstance(arg7.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$o4ZbjMfd1MorWGhaPFW5Pjohb6o(arg7, ((updates_Difference)v3), v4, v5, v6));
            }
        }
        else {
            arg7.gettingDifference = false;
            ConnectionsManager.getInstance(arg7.currentAccount).setIsUpdating(false);
        }
    }

    public static void lambda$getNewDeleteTask$30(MessagesController arg1, ArrayList arg2, int arg3) {
        arg1.gettingNewDeleteTask = true;
        MessagesStorage.getInstance(arg1.currentAccount).getNewTask(arg2, arg3);
    }

    static void lambda$hideReportSpam$21(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$loadChannelAdmins$11(MessagesController arg3, int arg4, TLObject arg5, TL_error arg6) {
        if((arg5 instanceof TL_channels_channelParticipants)) {
            ArrayList v6 = new ArrayList(((TL_channels_channelParticipants)arg5).participants.size());
            int v1;
            for(v1 = 0; v1 < ((TL_channels_channelParticipants)arg5).participants.size(); ++v1) {
                v6.add(Integer.valueOf(((TL_channels_channelParticipants)arg5).participants.get(v1).user_id));
            }

            arg3.processLoadedChannelAdmins(v6, arg4, false);
        }
    }

    public static void lambda$loadChannelParticipants$63(MessagesController arg1, Integer arg2, TLObject arg3, TL_error arg4) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$sle9uhdK4Xmk1vzjgePBSeYGfu4(arg1, arg4, arg3, arg2));
    }

    public static void lambda$loadCurrentState$172(MessagesController arg3, TLObject arg4, TL_error arg5) {
        int v0 = 0;
        arg3.updatingState = false;
        if(arg5 == null) {
            MessagesStorage.getInstance(arg3.currentAccount).setLastDateValue(((TL_updates_state)arg4).date);
            MessagesStorage.getInstance(arg3.currentAccount).setLastPtsValue(((TL_updates_state)arg4).pts);
            MessagesStorage.getInstance(arg3.currentAccount).setLastSeqValue(((TL_updates_state)arg4).seq);
            MessagesStorage.getInstance(arg3.currentAccount).setLastQtsValue(((TL_updates_state)arg4).qts);
            while(v0 < 3) {
                arg3.processUpdatesQueue(v0, 2);
                ++v0;
            }

            MessagesStorage.getInstance(arg3.currentAccount).saveDiffParams(MessagesStorage.getInstance(arg3.currentAccount).getLastSeqValue(), MessagesStorage.getInstance(arg3.currentAccount).getLastPtsValue(), MessagesStorage.getInstance(arg3.currentAccount).getLastDateValue(), MessagesStorage.getInstance(arg3.currentAccount).getLastQtsValue());
        }
        else if(arg5.code != 401) {
            arg3.loadCurrentState();
        }
    }

    public static void lambda$loadDialogPhotos$35(MessagesController arg8, int arg9, int arg10, long arg11, int arg13, TLObject arg14, TL_error arg15) {
        if(arg15 == null) {
            arg8.processLoadedUserPhotos(arg14, arg9, arg10, arg11, false, arg13);
        }
    }

    public static void lambda$loadDialogPhotos$36(MessagesController arg8, int arg9, int arg10, long arg11, int arg13, TLObject arg14, TL_error arg15) {
        if(arg15 == null) {
            TL_photos_photos v1 = new TL_photos_photos();
            v1.count = ((messages_Messages)arg14).count;
            v1.users.addAll(((messages_Messages)arg14).users);
            int v15;
            for(v15 = 0; v15 < ((messages_Messages)arg14).messages.size(); ++v15) {
                Object v0 = ((messages_Messages)arg14).messages.get(v15);
                if(((Message)v0).action != null) {
                    if(((Message)v0).action.photo == null) {
                    }
                    else {
                        v1.photos.add(((Message)v0).action.photo);
                    }
                }
            }

            arg8.processLoadedUserPhotos(((photos_Photos)v1), arg9, arg10, arg11, false, arg13);
        }
    }

    public static void lambda$loadDialogs$95(MessagesController arg9, int arg10, TLObject arg11, TL_error arg12) {
        if(arg12 == null) {
            arg9.processLoadedDialogs(arg11, null, 0, arg10, 0, false, false, false);
        }
    }

    public static void lambda$loadFullChat$15(MessagesController arg5, Chat arg6, long arg7, int arg9, int arg10, TLObject arg11, TL_error arg12) {
        -$$Lambda$MessagesController$pYvzaw_mOmnzb3DKylDzhGnciwY v6_3;
        ArrayList v6_2;
        Integer v6_1;
        if(arg12 == null) {
            MessagesStorage.getInstance(arg5.currentAccount).putUsersAndChats(((TL_messages_chatFull)arg11).users, ((TL_messages_chatFull)arg11).chats, true, true);
            MessagesStorage.getInstance(arg5.currentAccount).updateChatInfo(((TL_messages_chatFull)arg11).full_chat, false);
            if(ChatObject.isChannel(arg6)) {
                Object v6 = arg5.dialogs_read_inbox_max.get(Long.valueOf(arg7));
                if(v6 == null) {
                    v6_1 = Integer.valueOf(MessagesStorage.getInstance(arg5.currentAccount).getDialogReadMax(false, arg7));
                }

                arg5.dialogs_read_inbox_max.put(Long.valueOf(arg7), Integer.valueOf(Math.max(((TL_messages_chatFull)arg11).full_chat.read_inbox_max_id, v6_1.intValue())));
                ArrayList v12 = null;
                if(v6_1.intValue() == 0) {
                    v6_2 = new ArrayList();
                    TL_updateReadChannelInbox v0 = new TL_updateReadChannelInbox();
                    v0.channel_id = arg9;
                    v0.max_id = ((TL_messages_chatFull)arg11).full_chat.read_inbox_max_id;
                    v6_2.add(v0);
                    arg5.processUpdateArray(v6_2, v12, v12, false);
                }

                v6 = arg5.dialogs_read_outbox_max.get(Long.valueOf(arg7));
                if(v6 == null) {
                    v6_1 = Integer.valueOf(MessagesStorage.getInstance(arg5.currentAccount).getDialogReadMax(true, arg7));
                }

                arg5.dialogs_read_outbox_max.put(Long.valueOf(arg7), Integer.valueOf(Math.max(((TL_messages_chatFull)arg11).full_chat.read_outbox_max_id, v6_1.intValue())));
                if(v6_1.intValue() != 0) {
                    goto label_71;
                }

                v6_2 = new ArrayList();
                TL_updateReadChannelOutbox v7 = new TL_updateReadChannelOutbox();
                v7.channel_id = arg9;
                v7.max_id = ((TL_messages_chatFull)arg11).full_chat.read_outbox_max_id;
                v6_2.add(v7);
                arg5.processUpdateArray(v6_2, v12, v12, false);
            }

        label_71:
            v6_3 = new -$$Lambda$MessagesController$pYvzaw_mOmnzb3DKylDzhGnciwY(arg5, arg9, ((TL_messages_chatFull)arg11), arg10);
        }
        else {
            -$$Lambda$MessagesController$ejj5oAKM9mVqK14AWDh-_yYQK4c v6_4 = new -$$Lambda$MessagesController$ejj5oAKM9mVqK14AWDh-_yYQK4c(arg5, arg12, arg9);
        }

        AndroidUtilities.runOnUIThread(((Runnable)v6_3));
    }

    public static void lambda$loadFullUser$18(MessagesController arg0, User arg1, int arg2, TLObject arg3, TL_error arg4) {
        if(arg4 == null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$jt57lz-9D_DdHsCaAKosEAWdpRw(arg0, arg3, arg1, arg2));
        }
        else {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$BcjmSK_mTKqDFcCQnDfNTbfS6Ks(arg0, arg1));
        }
    }

    public static void lambda$loadGlobalNotificationsSettings$97(MessagesController arg0, int arg1, TLObject arg2, TL_error arg3) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$78bzfAJRB-2pMTPh6nEs29m7skA(arg0, arg2, arg1));
    }

    public static void lambda$loadHintDialogs$94(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg2 == null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$gToLe_nnnhQkiHGEk-pBlrR8kgw(arg0, arg1));
        }
    }

    public static void lambda$loadMessagesInternal$86(MessagesController arg23, long arg24, int arg26, int arg27, int arg28, int arg29, int arg30, int arg31, boolean arg32, int arg33, int arg34, int arg35, boolean arg36, TLObject arg37, TL_error arg38) {
        if(arg37 != null) {
            TLObject v0 = arg37;
            if(!((TL_messages_peerDialogs)v0).dialogs.isEmpty()) {
                Object v1 = ((TL_messages_peerDialogs)v0).dialogs.get(0);
                if(((TL_dialog)v1).top_message != 0) {
                    TL_messages_dialogs v3 = new TL_messages_dialogs();
                    v3.chats = ((TL_messages_peerDialogs)v0).chats;
                    v3.users = ((TL_messages_peerDialogs)v0).users;
                    v3.dialogs = ((TL_messages_peerDialogs)v0).dialogs;
                    v3.messages = ((TL_messages_peerDialogs)v0).messages;
                    MessagesStorage.getInstance(arg23.currentAccount).putDialogs(((messages_Dialogs)v3), 0);
                }

                arg23.loadMessagesInternal(arg24, arg26, arg27, arg28, false, arg29, arg30, arg31, ((TL_dialog)v1).top_message, arg32, arg33, arg34, ((TL_dialog)v1).unread_count, arg35, arg36, ((TL_dialog)v1).unread_mentions_count, false);
            }
        }
    }

    public static void lambda$loadMessagesInternal$87(MessagesController arg19, int arg20, int arg21, int arg22, long arg23, int arg25, int arg26, int arg27, int arg28, int arg29, int arg30, boolean arg31, int arg32, boolean arg33, int arg34, TLObject arg35, TL_error arg36) {
        int v5;
        int v6 = arg22;
        if(arg35 != null) {
            TLObject v1 = arg35;
            if(((messages_Messages)v1).messages.size() > arg20) {
                ((messages_Messages)v1).messages.remove(0);
            }

            if(v6 == 0 || (((messages_Messages)v1).messages.isEmpty())) {
                v5 = arg21;
            }
            else {
                int v0 = ((messages_Messages)v1).messages.get(((messages_Messages)v1).messages.size() - 1).id;
                int v2 = ((messages_Messages)v1).messages.size() - 1;
                while(v2 >= 0) {
                    Object v3 = ((messages_Messages)v1).messages.get(v2);
                    if(((Message)v3).date > v6) {
                        v0 = ((Message)v3).id;
                    }
                    else {
                        --v2;
                        continue;
                    }

                    break;
                }

                v5 = v0;
            }

            arg19.processLoadedMessages(((messages_Messages)v1), arg23, arg20, v5, arg22, false, arg25, arg26, arg27, arg28, arg29, arg30, arg31, false, arg32, arg33, arg34);
        }
    }

    public static void lambda$loadPeerSettings$25(MessagesController arg0, long arg1, TLObject arg3, TL_error arg4) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$i-g_Q9vLkCROvHMo_Q0D8al_NAQ(arg0, arg1));
    }

    public static void lambda$loadPeerSettings$27(MessagesController arg0, long arg1, TLObject arg3, TL_error arg4) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$iL8S9VHPmutF0pPOTD12dOtC7ww(arg0, arg1, arg3));
    }

    public static void lambda$loadPinnedDialogs$203(MessagesController arg16, ArrayList arg17, long arg18, TLObject arg20, TL_error arg21) {
        Integer v6_3;
        int v6_2;
        Object v6;
        MessagesController v9 = arg16;
        if(arg20 != null) {
            TLObject v2 = arg20;
            TL_messages_dialogs v8 = new TL_messages_dialogs();
            v8.users.addAll(((TL_messages_peerDialogs)v2).users);
            v8.chats.addAll(((TL_messages_peerDialogs)v2).chats);
            v8.dialogs.addAll(((TL_messages_peerDialogs)v2).dialogs);
            v8.messages.addAll(((TL_messages_peerDialogs)v2).messages);
            LongSparseArray v7 = new LongSparseArray();
            SparseArray v0 = new SparseArray();
            SparseArray v1 = new SparseArray();
            ArrayList v4 = new ArrayList();
            int v5;
            for(v5 = 0; v5 < ((TL_messages_peerDialogs)v2).users.size(); ++v5) {
                v6 = ((TL_messages_peerDialogs)v2).users.get(v5);
                v0.put(((User)v6).id, v6);
            }

            for(v5 = 0; v5 < ((TL_messages_peerDialogs)v2).chats.size(); ++v5) {
                v6 = ((TL_messages_peerDialogs)v2).chats.get(v5);
                v1.put(((Chat)v6).id, v6);
            }

            for(v5 = 0; v5 < ((TL_messages_peerDialogs)v2).messages.size(); ++v5) {
                Object v12 = ((TL_messages_peerDialogs)v2).messages.get(v5);
                if(((Message)v12).to_id.channel_id != 0) {
                    v6 = v1.get(((Message)v12).to_id.channel_id);
                    if(v6 == null) {
                        goto label_73;
                    }
                    else if(((Chat)v6).left) {
                    }
                    else {
                        goto label_73;
                    }
                }
                else if(((Message)v12).to_id.chat_id != 0) {
                    v6 = v1.get(((Message)v12).to_id.chat_id);
                    if(v6 == null) {
                        goto label_73;
                    }
                    else if(((Chat)v6).migrated_to != null) {
                    }
                    else {
                        goto label_73;
                    }
                }
                else {
                label_73:
                    MessageObject v6_1 = new MessageObject(v9.currentAccount, ((Message)v12), v0, v1, false);
                    v7.put(v6_1.getDialogId(), v6_1);
                }
            }

            int v0_1;
            for(v0_1 = 0; v0_1 < ((TL_messages_peerDialogs)v2).dialogs.size(); ++v0_1) {
                Object v5_1 = ((TL_messages_peerDialogs)v2).dialogs.get(v0_1);
                if(((TL_dialog)v5_1).id == 0) {
                    if(((TL_dialog)v5_1).peer.user_id != 0) {
                        v6_2 = ((TL_dialog)v5_1).peer.user_id;
                    }
                    else {
                        if(((TL_dialog)v5_1).peer.chat_id != 0) {
                            v6_2 = ((TL_dialog)v5_1).peer.chat_id;
                        }
                        else if(((TL_dialog)v5_1).peer.channel_id != 0) {
                            v6_2 = ((TL_dialog)v5_1).peer.channel_id;
                        }
                        else {
                            goto label_115;
                        }

                        v6_2 = -v6_2;
                    }

                    ((TL_dialog)v5_1).id = ((long)v6_2);
                }

            label_115:
                v4.add(Long.valueOf(((TL_dialog)v5_1).id));
                if(DialogObject.isChannel(((TL_dialog)v5_1))) {
                    v6 = v1.get(-(((int)((TL_dialog)v5_1).id)));
                    if(v6 == null) {
                        goto label_139;
                    }
                    else if(((Chat)v6).left) {
                    }
                    else {
                        goto label_139;
                    }
                }
                else if((((int)((TL_dialog)v5_1).id)) < 0) {
                    v6 = v1.get(-(((int)((TL_dialog)v5_1).id)));
                    if(v6 == null) {
                        goto label_139;
                    }
                    else if(((Chat)v6).migrated_to != null) {
                    }
                    else {
                        goto label_139;
                    }
                }
                else {
                label_139:
                    if(((TL_dialog)v5_1).last_message_date == 0) {
                        v6 = v7.get(((TL_dialog)v5_1).id);
                        if(v6 != null) {
                            ((TL_dialog)v5_1).last_message_date = ((MessageObject)v6).messageOwner.date;
                        }
                    }

                    v6 = v9.dialogs_read_inbox_max.get(Long.valueOf(((TL_dialog)v5_1).id));
                    if(v6 == null) {
                        v6_3 = Integer.valueOf(0);
                    }

                    v9.dialogs_read_inbox_max.put(Long.valueOf(((TL_dialog)v5_1).id), Integer.valueOf(Math.max(((Integer)v6).intValue(), ((TL_dialog)v5_1).read_inbox_max_id)));
                    v6 = v9.dialogs_read_outbox_max.get(Long.valueOf(((TL_dialog)v5_1).id));
                    if(v6 == null) {
                        v6_3 = Integer.valueOf(0);
                    }

                    v9.dialogs_read_outbox_max.put(Long.valueOf(((TL_dialog)v5_1).id), Integer.valueOf(Math.max(((Integer)v6).intValue(), ((TL_dialog)v5_1).read_outbox_max_id)));
                }
            }

            MessagesStorage.getInstance(v9.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$A6I83usSbZinQxr1GSAjTnlJaC4(arg16, ((TL_messages_peerDialogs)v2), arg17, v4, arg18, v7, v8));
        }
    }

    public static void lambda$loadUnknownChannel$177(MessagesController arg9, long arg10, Chat arg12, TLObject arg13, TL_error arg14) {
        if(arg13 != null && !((TL_messages_peerDialogs)arg13).dialogs.isEmpty() && !((TL_messages_peerDialogs)arg13).chats.isEmpty()) {
            TL_messages_dialogs v1 = new TL_messages_dialogs();
            v1.dialogs.addAll(((TL_messages_peerDialogs)arg13).dialogs);
            v1.messages.addAll(((TL_messages_peerDialogs)arg13).messages);
            v1.users.addAll(((TL_messages_peerDialogs)arg13).users);
            v1.chats.addAll(((TL_messages_peerDialogs)arg13).chats);
            arg9.processLoadedDialogs(((messages_Dialogs)v1), null, 0, 1, 2, false, false, false);
        }

        if(arg10 != 0) {
            MessagesStorage.getInstance(arg9.currentAccount).removePendingTask(arg10);
        }

        arg9.gettingUnknownChannels.delete(arg12.id);
    }

    public static void lambda$loadUnreadDialogs$199(MessagesController arg0, TLObject arg1, TL_error arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$0pSxs26-KyGGVxmKPAMpaL_n4x8(arg0, arg1));
    }

    public static void lambda$markDialogAsRead$130(MessagesController arg8, long arg9, int arg11, int arg12, boolean arg13) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$GUs0W46L1au4f0fqM65EnahNx7Q(arg8, arg9, arg11, arg12, arg13));
    }

    public static void lambda$markDialogAsRead$132(MessagesController arg9, long arg10, int arg12, boolean arg13, int arg14, int arg15) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$5cDK088W9BXcE9albOxJnsxOg6Q(arg9, arg10, arg12, arg13, arg14, arg15));
    }

    public static void lambda$markDialogAsRead$133(MessagesController arg5, long arg6, boolean arg8, int arg9, int arg10) {
        ReadTask v0_1;
        Object v0 = arg5.readTasksMap.get(arg6);
        if(v0 == null) {
            v0_1 = new ReadTask(arg5, null);
            v0_1.dialogId = arg6;
            v0_1.sendRequestTime = SystemClock.elapsedRealtime() + 5000;
            if(!arg8) {
                arg5.readTasksMap.put(arg6, v0_1);
                arg5.readTasks.add(v0_1);
            }
        }

        v0_1.maxDate = arg9;
        v0_1.maxId = arg10;
        if(arg8) {
            arg5.completeReadTask(v0_1);
        }
    }

    public static void lambda$markDialogAsReadNow$127(MessagesController arg2, long arg3) {
        Object v0 = arg2.readTasksMap.get(arg3);
        if(v0 == null) {
            return;
        }

        arg2.completeReadTask(((ReadTask)v0));
        arg2.readTasks.remove(v0);
        arg2.readTasksMap.remove(arg3);
    }

    public static void lambda$markDialogAsUnread$197(MessagesController arg1, long arg2, TLObject arg4, TL_error arg5) {
        if(arg2 != 0) {
            MessagesStorage.getInstance(arg1.currentAccount).removePendingTask(arg2);
        }
    }

    static void lambda$markMentionMessageAsRead$121(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$markMentionMessageAsRead$122(MessagesController arg1, TLObject arg2, TL_error arg3) {
        if(arg3 == null) {
            arg1.processNewDifferenceParams(-1, ((TL_messages_affectedMessages)arg2).pts, -1, ((TL_messages_affectedMessages)arg2).pts_count);
        }
    }

    static void lambda$markMentionsAsRead$128(TLObject arg0, TL_error arg1) {
    }

    static void lambda$markMessageAsRead$123(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$markMessageAsRead$124(MessagesController arg1, TLObject arg2, TL_error arg3) {
        if(arg3 == null) {
            arg1.processNewDifferenceParams(-1, ((TL_messages_affectedMessages)arg2).pts, -1, ((TL_messages_affectedMessages)arg2).pts_count);
        }
    }

    static void lambda$markMessageContentAsRead$119(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$markMessageContentAsRead$120(MessagesController arg1, TLObject arg2, TL_error arg3) {
        if(arg3 == null) {
            arg1.processNewDifferenceParams(-1, ((TL_messages_affectedMessages)arg2).pts, -1, ((TL_messages_affectedMessages)arg2).pts_count);
        }
    }

    public static void lambda$migrateDialogs$105(MessagesController arg1, int arg2, TLObject arg3, TL_error arg4) {
        if(arg4 == null) {
            MessagesStorage.getInstance(arg1.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$U3fJVCatb4mU-1LjxV_K9pV2sbo(arg1, ((messages_Dialogs)arg3), arg2));
        }
        else {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$BvVbSxhdg0osSfiCuyO085KwJyE(arg1));
        }
    }

    public static int lambda$new$0(MessagesController arg2, Update arg3, Update arg4) {
        int v4;
        int v3;
        int v0 = arg2.getUpdateType(arg3);
        int v1 = arg2.getUpdateType(arg4);
        if(v0 != v1) {
            return AndroidUtilities.compare(v0, v1);
        }

        if(v0 == 0) {
        label_6:
            v3 = MessagesController.getUpdatePts(arg3);
            v4 = MessagesController.getUpdatePts(arg4);
        }
        else if(v0 == 1) {
            v3 = MessagesController.getUpdateQts(arg3);
            v4 = MessagesController.getUpdateQts(arg4);
        }
        else if(v0 == 2) {
            v0 = MessagesController.getUpdateChannelId(arg3);
            v1 = MessagesController.getUpdateChannelId(arg4);
            if(v0 == v1) {
                goto label_6;
            }
            else {
                goto label_21;
            }
        }
        else {
            return 0;
        }

        return AndroidUtilities.compare(v3, v4);
    label_21:
        return AndroidUtilities.compare(v0, v1);
    }

    public static void lambda$new$1(MessagesController arg3) {
        MessagesController v0 = MessagesController.getInstance(arg3.currentAccount);
        NotificationCenter.getInstance(arg3.currentAccount).addObserver(v0, NotificationCenter.FileDidUpload);
        NotificationCenter.getInstance(arg3.currentAccount).addObserver(v0, NotificationCenter.FileDidFailUpload);
        NotificationCenter.getInstance(arg3.currentAccount).addObserver(v0, NotificationCenter.FileDidLoaded);
        NotificationCenter.getInstance(arg3.currentAccount).addObserver(v0, NotificationCenter.FileDidFailedLoad);
        NotificationCenter.getInstance(arg3.currentAccount).addObserver(v0, NotificationCenter.messageReceivedByServer);
        NotificationCenter.getInstance(arg3.currentAccount).addObserver(v0, NotificationCenter.updateMessageMedia);
    }

    public static void lambda$null$100(MessagesController arg16, messages_Dialogs arg17, LongSparseArray arg18, LongSparseArray arg19) {
        Object v4;
        MessagesController v0 = arg16;
        messages_Dialogs v1 = arg17;
        LongSparseArray v2 = arg18;
        v0.resetingDialogs = false;
        v0.applyDialogsNotificationsSettings(v1.dialogs);
        if(!UserConfig.getInstance(v0.currentAccount).draftsLoaded) {
            DataQuery.getInstance(v0.currentAccount).loadDrafts();
        }

        v0.putUsers(v1.users, false);
        v0.putChats(v1.chats, false);
        int v1_1;
        for(v1_1 = 0; true; ++v1_1) {
            long v5 = 0;
            if(v1_1 >= v0.dialogs.size()) {
                break;
            }

            v4 = v0.dialogs.get(v1_1);
            if((((int)((TL_dialog)v4).id)) != 0) {
                v0.dialogs_dict.remove(((TL_dialog)v4).id);
                Object v7 = v0.dialogMessage.get(((TL_dialog)v4).id);
                v0.dialogMessage.remove(((TL_dialog)v4).id);
                if(v7 != null) {
                    v0.dialogMessagesByIds.remove(((MessageObject)v7).getId());
                    if(((MessageObject)v7).messageOwner.random_id != v5) {
                        v0.dialogMessagesByRandomIds.remove(((MessageObject)v7).messageOwner.random_id);
                    }
                }
            }
        }

        for(v1_1 = 0; v1_1 < arg18.size(); ++v1_1) {
            long v7_1 = v2.keyAt(v1_1);
            v4 = v2.valueAt(v1_1);
            if((((TL_dialog)v4).draft instanceof TL_draftMessage)) {
                DataQuery.getInstance(v0.currentAccount).saveDraft(((TL_dialog)v4).id, ((TL_dialog)v4).draft, null, false);
            }

            v0.dialogs_dict.put(v7_1, v4);
            Object v9 = arg19.get(((TL_dialog)v4).id);
            v0.dialogMessage.put(v7_1, v9);
            if(v9 != null && ((MessageObject)v9).messageOwner.to_id.channel_id == 0) {
                v0.dialogMessagesByIds.put(((MessageObject)v9).getId(), v9);
                if(((MessageObject)v9).messageOwner.random_id != v5) {
                    v0.dialogMessagesByRandomIds.put(((MessageObject)v9).messageOwner.random_id, v9);
                }
            }
        }

        v0.dialogs.clear();
        v1_1 = v0.dialogs_dict.size();
        int v2_1;
        for(v2_1 = 0; v2_1 < v1_1; ++v2_1) {
            v0.dialogs.add(v0.dialogs_dict.valueAt(v2_1));
        }

        v0.sortDialogs(null);
        v0.dialogsEndReached = true;
        v0.serverDialogsEndReached = false;
        if(UserConfig.getInstance(v0.currentAccount).totalDialogsLoadCount < 400 && UserConfig.getInstance(v0.currentAccount).dialogsLoadOffsetId != -1 && UserConfig.getInstance(v0.currentAccount).dialogsLoadOffsetId != 2147483647) {
            v0.loadDialogs(0, 100, false);
        }

        NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$null$102(MessagesController arg1) {
        arg1.migratingDialogs = false;
    }

    public static void lambda$null$103(MessagesController arg20, messages_Dialogs arg21, int arg22) {
        long v2_4;
        UserConfig v1_2;
        Object v2_3;
        Object v19;
        int v7;
        int v13;
        long v6;
        MessagesController v10 = arg20;
        messages_Dialogs v0 = arg21;
        int v1 = arg22;
        try {
            UserConfig v2 = UserConfig.getInstance(v10.currentAccount);
            v2.totalDialogsLoadCount += v0.dialogs.size();
            Object v4 = null;
            int v2_1;
            for(v2_1 = 0; true; ++v2_1) {
                v6 = 1000;
                if(v2_1 >= v0.messages.size()) {
                    break;
                }

                Object v5 = v0.messages.get(v2_1);
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("search migrate id " + ((Message)v5).id + " date " + LocaleController.getInstance().formatterStats.format((((long)((Message)v5).date)) * v6));
                }

                if(v4 == null || ((Message)v5).date < ((Message)v4).date) {
                    v4 = v5;
                }
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("migrate step with id " + ((Message)v4).id + " date " + LocaleController.getInstance().formatterStats.format((((long)((Message)v4).date)) * v6));
            }

            int v8_1 = -1;
            if(v0.dialogs.size() >= 100) {
                v2_1 = ((Message)v4).id;
            }
            else {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("migrate stop due to not 100 dialogs");
                }

                UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetId = 2147483647;
                UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetDate = UserConfig.getInstance(v10.currentAccount).migrateOffsetDate;
                UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetUserId = UserConfig.getInstance(v10.currentAccount).migrateOffsetUserId;
                UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetChatId = UserConfig.getInstance(v10.currentAccount).migrateOffsetChatId;
                UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetChannelId = UserConfig.getInstance(v10.currentAccount).migrateOffsetChannelId;
                UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetAccess = UserConfig.getInstance(v10.currentAccount).migrateOffsetAccess;
                v2_1 = -1;
            }

            StringBuilder v5_1 = new StringBuilder(v0.dialogs.size() * 12);
            LongSparseArray v9 = new LongSparseArray();
            int v11;
            for(v11 = 0; v11 < v0.dialogs.size(); ++v11) {
                Object v12 = v0.dialogs.get(v11);
                if(((TL_dialog)v12).peer.channel_id != 0) {
                    v13 = ((TL_dialog)v12).peer.channel_id;
                    goto label_129;
                }
                else if(((TL_dialog)v12).peer.chat_id != 0) {
                    v13 = ((TL_dialog)v12).peer.chat_id;
                label_129:
                    ((TL_dialog)v12).id = ((long)(-v13));
                }
                else {
                    ((TL_dialog)v12).id = ((long)((TL_dialog)v12).peer.user_id);
                }

                if(v5_1.length() > 0) {
                    v5_1.append(",");
                }

                v5_1.append(((TL_dialog)v12).id);
                v9.put(((TL_dialog)v12).id, v12);
            }

            SQLiteCursor v5_2 = MessagesStorage.getInstance(v10.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT did FROM dialogs WHERE did IN (%s)", v5_1.toString()), new Object[0]);
            while(v5_2.a()) {
                long v11_1 = v5_2.d(0);
                Object v13_1 = v9.get(v11_1);
                v9.remove(v11_1);
                if(v13_1 != null) {
                    v0.dialogs.remove(v13_1);
                    int v15;
                    for(v15 = 0; v15 < v0.messages.size(); ++v15) {
                        Object v6_1 = v0.messages.get(v15);
                        if(MessageObject.getDialogId(((Message)v6_1)) != v11_1) {
                        }
                        else {
                            v0.messages.remove(v15);
                            --v15;
                            if(((Message)v6_1).id == ((TL_dialog)v13_1).top_message) {
                                ((TL_dialog)v13_1).top_message = 0;
                                break;
                            }
                        }
                    }
                }
            }

            v5_2.b();
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("migrate found missing dialogs " + v0.dialogs.size());
            }

            v5_2 = MessagesStorage.getInstance(v10.currentAccount).getDatabase().b("SELECT min(date) FROM dialogs WHERE date != 0 AND did >> 32 IN (0, -1)", new Object[0]);
            if(v5_2.a()) {
                int v6_2 = Math.max(1441062000, v5_2.b(0));
                v7 = v2_1;
                v2_1 = 0;
                while(v2_1 < v0.messages.size()) {
                    Object v11_2 = v0.messages.get(v2_1);
                    if(((Message)v11_2).date < v6_2) {
                        if(v1 != v8_1) {
                            UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetId = UserConfig.getInstance(v10.currentAccount).migrateOffsetId;
                            UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetDate = UserConfig.getInstance(v10.currentAccount).migrateOffsetDate;
                            UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetUserId = UserConfig.getInstance(v10.currentAccount).migrateOffsetUserId;
                            UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetChatId = UserConfig.getInstance(v10.currentAccount).migrateOffsetChatId;
                            UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetChannelId = UserConfig.getInstance(v10.currentAccount).migrateOffsetChannelId;
                            UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetAccess = UserConfig.getInstance(v10.currentAccount).migrateOffsetAccess;
                            if(BuildVars.LOGS_ENABLED) {
                                StringBuilder v7_1 = new StringBuilder();
                                v7_1.append("migrate stop due to reached loaded dialogs ");
                                v19 = v4;
                                v7_1.append(LocaleController.getInstance().formatterStats.format((((long)v6_2)) * 1000));
                                FileLog.d(v7_1.toString());
                            }
                            else {
                                v19 = v4;
                            }

                            v7 = -1;
                        }
                        else {
                            v19 = v4;
                        }

                        v0.messages.remove(v2_1);
                        --v2_1;
                        long v3 = MessageObject.getDialogId(((Message)v11_2));
                        v11_2 = v9.get(v3);
                        v9.remove(v3);
                        if(v11_2 == null) {
                            goto label_295;
                        }

                        v0.dialogs.remove(v11_2);
                    }
                    else {
                        v19 = v4;
                    }

                label_295:
                    ++v2_1;
                    v4 = v19;
                }

                v19 = v4;
                if(v19 != null) {
                    v4 = v19;
                    if(((Message)v4).date >= v6_2) {
                        goto label_361;
                    }

                    if(v1 == v8_1) {
                        goto label_361;
                    }

                    UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetId = UserConfig.getInstance(v10.currentAccount).migrateOffsetId;
                    UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetDate = UserConfig.getInstance(v10.currentAccount).migrateOffsetDate;
                    UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetUserId = UserConfig.getInstance(v10.currentAccount).migrateOffsetUserId;
                    UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetChatId = UserConfig.getInstance(v10.currentAccount).migrateOffsetChatId;
                    UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetChannelId = UserConfig.getInstance(v10.currentAccount).migrateOffsetChannelId;
                    UserConfig.getInstance(v10.currentAccount).dialogsLoadOffsetAccess = UserConfig.getInstance(v10.currentAccount).migrateOffsetAccess;
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("migrate stop due to reached loaded dialogs " + LocaleController.getInstance().formatterStats.format((((long)v6_2)) * 1000));
                    }

                    v7 = -1;
                    goto label_361;
                }

                v4 = v19;
            }
            else {
                v7 = v2_1;
            }

        label_361:
            v5_2.b();
            UserConfig.getInstance(v10.currentAccount).migrateOffsetDate = ((Message)v4).date;
            if(((Message)v4).to_id.channel_id != 0) {
                UserConfig.getInstance(v10.currentAccount).migrateOffsetChannelId = ((Message)v4).to_id.channel_id;
                UserConfig.getInstance(v10.currentAccount).migrateOffsetChatId = 0;
                UserConfig.getInstance(v10.currentAccount).migrateOffsetUserId = 0;
                v1 = 0;
                while(v1 < v0.chats.size()) {
                    v2_3 = v0.chats.get(v1);
                    if(((Chat)v2_3).id == UserConfig.getInstance(v10.currentAccount).migrateOffsetChannelId) {
                        v1_2 = UserConfig.getInstance(v10.currentAccount);
                        v2_4 = ((Chat)v2_3).access_hash;
                        goto label_395;
                    }
                    else {
                        ++v1;
                        continue;
                    }
                }
            }
            else if(((Message)v4).to_id.chat_id != 0) {
                UserConfig.getInstance(v10.currentAccount).migrateOffsetChatId = ((Message)v4).to_id.chat_id;
                UserConfig.getInstance(v10.currentAccount).migrateOffsetChannelId = 0;
                UserConfig.getInstance(v10.currentAccount).migrateOffsetUserId = 0;
                v1 = 0;
                while(true) {
                    if(v1 < v0.chats.size()) {
                        v2_3 = v0.chats.get(v1);
                        if(((Chat)v2_3).id == UserConfig.getInstance(v10.currentAccount).migrateOffsetChatId) {
                            v1_2 = UserConfig.getInstance(v10.currentAccount);
                            v2_4 = ((Chat)v2_3).access_hash;
                            break;
                        }
                        else {
                            ++v1;
                            continue;
                        }
                    }

                    goto label_463;
                }

            label_395:
                v1_2.migrateOffsetAccess = v2_4;
            }
            else if(((Message)v4).to_id.user_id != 0) {
                UserConfig.getInstance(v10.currentAccount).migrateOffsetUserId = ((Message)v4).to_id.user_id;
                v2_1 = 0;
                UserConfig.getInstance(v10.currentAccount).migrateOffsetChatId = 0;
                UserConfig.getInstance(v10.currentAccount).migrateOffsetChannelId = 0;
                while(v2_1 < v0.users.size()) {
                    Object v1_3 = v0.users.get(v2_1);
                    if(((User)v1_3).id == UserConfig.getInstance(v10.currentAccount).migrateOffsetUserId) {
                        UserConfig.getInstance(v10.currentAccount).migrateOffsetAccess = ((User)v1_3).access_hash;
                    }
                    else {
                        ++v2_1;
                        continue;
                    }

                    break;
                }
            }

        label_463:
            arg20.processLoadedDialogs(arg21, null, v7, 0, 0, false, true, false);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$X4dMPg7NBqGBlF92jdcmFnc5sG4(v10));
        }
    }

    public static void lambda$null$104(MessagesController arg1) {
        arg1.migratingDialogs = false;
    }

    public static void lambda$null$106(MessagesController arg2, messages_Dialogs arg3, boolean arg4, int arg5) {
        arg2.putUsers(arg3.users, true);
        arg2.loadingDialogs = false;
        if(arg4) {
            arg2.dialogsEndReached = false;
            arg2.serverDialogsEndReached = false;
        }
        else if(UserConfig.getInstance(arg2.currentAccount).dialogsLoadOffsetId == 2147483647) {
            arg2.dialogsEndReached = true;
            arg2.serverDialogsEndReached = true;
        }
        else {
            arg2.loadDialogs(0, arg5, false);
        }

        NotificationCenter.getInstance(arg2.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$null$107(MessagesController arg24, int arg25, messages_Dialogs arg26, ArrayList arg27, boolean arg28, LongSparseArray arg29, LongSparseArray arg30, SparseArray arg31, int arg32, boolean arg33, int arg34, ArrayList arg35) {
        boolean v2_1;
        boolean v3_4;
        int v23;
        Object v8;
        Object v3_2;
        MessagesController v0 = arg24;
        int v1 = arg25;
        messages_Dialogs v2 = arg26;
        ArrayList v3 = arg27;
        LongSparseArray v5 = arg29;
        LongSparseArray v6 = arg30;
        int v7 = arg32;
        int v9 = 1;
        if(v1 != 1) {
            v0.applyDialogsNotificationsSettings(v2.dialogs);
            if(!UserConfig.getInstance(v0.currentAccount).draftsLoaded) {
                DataQuery.getInstance(v0.currentAccount).loadDrafts();
            }
        }

        ArrayList v10 = v2.users;
        boolean v12 = v1 == 1 ? true : false;
        v0.putUsers(v10, v12);
        v10 = v2.chats;
        v12 = v1 == 1 ? true : false;
        v0.putChats(v10, v12);
        Message v10_1 = null;
        if(v3 != null) {
            int v12_1;
            for(v12_1 = 0; v12_1 < arg27.size(); ++v12_1) {
                Object v13 = v3.get(v12_1);
                if(((v13 instanceof TL_encryptedChat)) && AndroidUtilities.getMyLayerVersion(((EncryptedChat)v13).layer) < 73) {
                    SecretChatHelper.getInstance(v0.currentAccount).sendNotifyLayerMessage(((EncryptedChat)v13), v10_1);
                }

                v0.putEncryptedChat(((EncryptedChat)v13), true);
            }
        }

        if(!arg28) {
            v0.loadingDialogs = false;
        }

        int v3_1 = !arg28 || (v0.dialogs.isEmpty()) ? 0 : v0.dialogs.get(v0.dialogs.size() - 1).last_message_date;
        v12_1 = 0;
        int v13_1 = 0;
        while(v12_1 < arg29.size()) {
            long v14 = v5.keyAt(v12_1);
            Object v10_2 = v5.valueAt(v12_1);
            if(!arg28 || v3_1 == 0 || ((TL_dialog)v10_2).last_message_date >= v3_1) {
                Object v11 = v0.dialogs_dict.get(v14);
                if(v1 == v9 || !(((TL_dialog)v10_2).draft instanceof TL_draftMessage)) {
                    v23 = v3_1;
                }
                else {
                    v23 = v3_1;
                    DataQuery.getInstance(v0.currentAccount).saveDraft(((TL_dialog)v10_2).id, ((TL_dialog)v10_2).draft, null, false);
                }

                if(v11 == null) {
                    v0.dialogs_dict.put(v14, v10_2);
                    v3_2 = v6.get(((TL_dialog)v10_2).id);
                    v0.dialogMessage.put(v14, v3_2);
                    if(v3_2 != null && ((MessageObject)v3_2).messageOwner.to_id.channel_id == 0) {
                        v0.dialogMessagesByIds.put(((MessageObject)v3_2).getId(), v3_2);
                        if(((MessageObject)v3_2).messageOwner.random_id != 0) {
                            v0.dialogMessagesByRandomIds.put(((MessageObject)v3_2).messageOwner.random_id, v3_2);
                        }
                    }

                    v13_1 = 1;
                    goto label_213;
                }

                if(v1 != 1) {
                    ((TL_dialog)v11).notify_settings = ((TL_dialog)v10_2).notify_settings;
                }

                ((TL_dialog)v11).pinned = ((TL_dialog)v10_2).pinned;
                ((TL_dialog)v11).pinnedNum = ((TL_dialog)v10_2).pinnedNum;
                v3_2 = v0.dialogMessage.get(v14);
                if(v3_2 != null && (((MessageObject)v3_2).deleted) || (v3_2 == null || ((TL_dialog)v11).top_message > 0)) {
                    if(((TL_dialog)v10_2).top_message < ((TL_dialog)v11).top_message) {
                        goto label_213;
                    }

                    v0.dialogs_dict.put(v14, v10_2);
                    v8 = v6.get(((TL_dialog)v10_2).id);
                    v0.dialogMessage.put(v14, v8);
                    if(v8 != null && ((MessageObject)v8).messageOwner.to_id.channel_id == 0) {
                        v0.dialogMessagesByIds.put(((MessageObject)v8).getId(), v8);
                        if(v8 != null && ((MessageObject)v8).messageOwner.random_id != 0) {
                            v0.dialogMessagesByRandomIds.put(((MessageObject)v8).messageOwner.random_id, v8);
                        }
                    }

                    if(v3_2 == null) {
                        goto label_213;
                    }

                    v0.dialogMessagesByIds.remove(((MessageObject)v3_2).getId());
                    if(((MessageObject)v3_2).messageOwner.random_id == 0) {
                        goto label_213;
                    }
                }
                else {
                    v8 = v6.get(((TL_dialog)v10_2).id);
                    if(!((MessageObject)v3_2).deleted && v8 != null && ((MessageObject)v8).messageOwner.date <= ((MessageObject)v3_2).messageOwner.date) {
                        goto label_213;
                    }

                    v0.dialogs_dict.put(v14, v10_2);
                    v0.dialogMessage.put(v14, v8);
                    if(v8 != null && ((MessageObject)v8).messageOwner.to_id.channel_id == 0) {
                        v0.dialogMessagesByIds.put(((MessageObject)v8).getId(), v8);
                        if(v8 != null && ((MessageObject)v8).messageOwner.random_id != 0) {
                            v0.dialogMessagesByRandomIds.put(((MessageObject)v8).messageOwner.random_id, v8);
                        }
                    }

                    v0.dialogMessagesByIds.remove(((MessageObject)v3_2).getId());
                    if(((MessageObject)v3_2).messageOwner.random_id == 0) {
                        goto label_213;
                    }
                }

                v0.dialogMessagesByRandomIds.remove(((MessageObject)v3_2).messageOwner.random_id);
            }
            else {
                v23 = v3_1;
            }

        label_213:
            ++v12_1;
            v3_1 = v23;
            v9 = 1;
        }

        v0.dialogs.clear();
        v3_1 = v0.dialogs_dict.size();
        int v5_1;
        for(v5_1 = 0; v5_1 < v3_1; ++v5_1) {
            v0.dialogs.add(v0.dialogs_dict.valueAt(v5_1));
        }

        SparseArray v3_3 = arg28 ? arg31 : null;
        v0.sortDialogs(v3_3);
        if(v1 != 2 && !arg28) {
            if(v2.dialogs.size() != 0 && v2.dialogs.size() == v7) {
                goto label_248;
            }
            else if(v1 == 0) {
                v3_4 = true;
            }
            else {
            label_248:
                v3_4 = false;
            }

            v0.dialogsEndReached = v3_4;
            if(arg33) {
                goto label_262;
            }

            if(v2.dialogs.size() != 0 && v2.dialogs.size() == v7) {
                goto label_260;
            }
            else if(v1 == 0) {
                v2_1 = true;
            }
            else {
            label_260:
                v2_1 = false;
            }

            v0.serverDialogsEndReached = v2_1;
        }

    label_262:
        if((arg33) || (arg28) || UserConfig.getInstance(v0.currentAccount).totalDialogsLoadCount >= 400 || UserConfig.getInstance(v0.currentAccount).dialogsLoadOffsetId == -1 || UserConfig.getInstance(v0.currentAccount).dialogsLoadOffsetId == 2147483647) {
            v3_1 = 0;
        }
        else {
            v3_1 = 0;
            v0.loadDialogs(0, 100, false);
        }

        NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[v3_1]);
        if(arg28) {
            UserConfig.getInstance(v0.currentAccount).migrateOffsetId = arg34;
            UserConfig.getInstance(v0.currentAccount).saveConfig(((boolean)v3_1));
            v0.migratingDialogs = ((boolean)v3_1);
            NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch, new Object[v3_1]);
        }
        else {
            arg24.generateUpdateMessage();
            if(v13_1 == 0 && v1 == 1) {
                v0.loadDialogs(v3_1, v7, ((boolean)v3_1));
            }
        }

        arg24.migrateDialogs(UserConfig.getInstance(v0.currentAccount).migrateOffsetId, UserConfig.getInstance(v0.currentAccount).migrateOffsetDate, UserConfig.getInstance(v0.currentAccount).migrateOffsetUserId, UserConfig.getInstance(v0.currentAccount).migrateOffsetChatId, UserConfig.getInstance(v0.currentAccount).migrateOffsetChannelId, UserConfig.getInstance(v0.currentAccount).migrateOffsetAccess);
        if(!arg35.isEmpty()) {
            v0.reloadDialogsReadValue(arg35, 0);
        }

        arg24.loadUnreadDialogs();
    }

    public static void lambda$null$109(MessagesController arg1, TLObject arg2, long arg3) {
        if(arg2 != null) {
            int v2 = ((messages_Messages)arg2).count != 0 ? ((messages_Messages)arg2).count : ((messages_Messages)arg2).messages.size();
            MessagesStorage.getInstance(arg1.currentAccount).resetMentionsCount(arg3, v2);
        }
    }

    public static void lambda$null$110(MessagesController arg0, long arg1, TLObject arg3, TL_error arg4) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$00DWK5js1qADBOzto1rMIKPWI8I(arg0, arg3, arg1));
    }

    public static void lambda$null$113(MessagesController arg3, TL_dialog arg4) {
        Object v0 = arg3.dialogs_dict.get(arg4.id);
        if(v0 != null && ((TL_dialog)v0).top_message == 0) {
            arg3.deleteDialog(arg4.id, 3);
        }
    }

    public static void lambda$null$114(MessagesController arg1, int arg2) {
        arg1.checkingLastMessagesDialogs.delete(arg2);
    }

    public static void lambda$null$116(MessagesController arg16, messages_Dialogs arg17, LongSparseArray arg18, LongSparseArray arg19, LongSparseArray arg20) {
        long v12;
        InputPeer v7;
        MessagesController v0 = arg16;
        LongSparseArray v2 = arg18;
        LongSparseArray v3 = arg19;
        v0.putUsers(arg17.users, true);
        v0.putChats(arg17.chats, true);
        int v4;
        for(v4 = 0; true; ++v4) {
            v7 = null;
            if(v4 >= arg18.size()) {
                break;
            }

            long v8 = v2.keyAt(v4);
            Object v6 = v2.valueAt(v4);
            Object v10 = v0.dialogs_dict.get(v8);
            long v11 = 0;
            if(v10 == null) {
                ++v0.nextDialogsCacheOffset;
                v0.dialogs_dict.put(v8, v6);
                v6 = v3.get(((TL_dialog)v6).id);
                v0.dialogMessage.put(v8, v6);
                if(v6 != null && ((MessageObject)v6).messageOwner.to_id.channel_id == 0) {
                    v0.dialogMessagesByIds.put(((MessageObject)v6).getId(), v6);
                    if(((MessageObject)v6).messageOwner.random_id != v11) {
                        v0.dialogMessagesByRandomIds.put(((MessageObject)v6).messageOwner.random_id, v6);
                    }
                }
            }
            else {
                ((TL_dialog)v10).unread_count = ((TL_dialog)v6).unread_count;
                if(((TL_dialog)v10).unread_mentions_count != ((TL_dialog)v6).unread_mentions_count) {
                    ((TL_dialog)v10).unread_mentions_count = ((TL_dialog)v6).unread_mentions_count;
                    if(v0.createdDialogMainThreadIds.contains(Long.valueOf(((TL_dialog)v10).id))) {
                        NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.updateMentionsCount, new Object[]{Long.valueOf(((TL_dialog)v10).id), Integer.valueOf(((TL_dialog)v10).unread_mentions_count)});
                    }
                }

                Object v11_1 = v0.dialogMessage.get(v8);
                if(v11_1 != null) {
                    if(((TL_dialog)v10).top_message > 0) {
                    }
                    else {
                        Object v7_1 = v3.get(((TL_dialog)v6).id);
                        if(!((MessageObject)v11_1).deleted && v7_1 != null && ((MessageObject)v7_1).messageOwner.date <= ((MessageObject)v11_1).messageOwner.date) {
                            goto label_161;
                        }

                        v0.dialogs_dict.put(v8, v6);
                        v0.dialogMessage.put(v8, v7_1);
                        if(v7_1 != null && ((MessageObject)v7_1).messageOwner.to_id.channel_id == 0) {
                            v0.dialogMessagesByIds.put(((MessageObject)v7_1).getId(), v7_1);
                            if(((MessageObject)v7_1).messageOwner.random_id != 0) {
                                v0.dialogMessagesByRandomIds.put(((MessageObject)v7_1).messageOwner.random_id, v7_1);
                            }
                        }

                        v0.dialogMessagesByIds.remove(((MessageObject)v11_1).getId());
                        if(((MessageObject)v11_1).messageOwner.random_id == 0) {
                            goto label_161;
                        }

                        v0.dialogMessagesByRandomIds.remove(((MessageObject)v11_1).messageOwner.random_id);
                        goto label_161;
                    }
                }

                if((v11_1 == null || !((MessageObject)v11_1).deleted) && ((TL_dialog)v6).top_message <= ((TL_dialog)v10).top_message) {
                    goto label_161;
                }

                v0.dialogs_dict.put(v8, v6);
                v10 = v3.get(((TL_dialog)v6).id);
                v0.dialogMessage.put(v8, v10);
                if(v10 != null && ((MessageObject)v10).messageOwner.to_id.channel_id == 0) {
                    v0.dialogMessagesByIds.put(((MessageObject)v10).getId(), v10);
                    if(((MessageObject)v10).messageOwner.random_id != 0) {
                        v0.dialogMessagesByRandomIds.put(((MessageObject)v10).messageOwner.random_id, v10);
                    }
                }

                if(v11_1 != null) {
                    v0.dialogMessagesByIds.remove(((MessageObject)v11_1).getId());
                    v12 = 0;
                    if(((MessageObject)v11_1).messageOwner.random_id != v12) {
                        v0.dialogMessagesByRandomIds.remove(((MessageObject)v11_1).messageOwner.random_id);
                    }
                }
                else {
                    v12 = 0;
                }

                if(v10 != null) {
                    goto label_161;
                }

                v0.checkLastDialogMessage(((TL_dialog)v6), v7, v12);
            }

        label_161:
        }

        v0.dialogs.clear();
        int v2_1 = v0.dialogs_dict.size();
        int v3_1;
        for(v3_1 = 0; v3_1 < v2_1; ++v3_1) {
            v0.dialogs.add(v0.dialogs_dict.valueAt(v3_1));
        }

        v0.sortDialogs(((SparseArray)v7));
        NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        NotificationsController.getInstance(v0.currentAccount).processDialogsUpdateRead(arg20);
    }

    public static void lambda$null$129(MessagesController arg10, long arg11, int arg13, int arg14, boolean arg15) {
        Integer v14;
        LongSparseArray v13;
        Object v0 = arg10.dialogs_dict.get(arg11);
        if(v0 != null) {
            int v3 = ((TL_dialog)v0).unread_count;
            if(arg13 == 0 || arg14 >= ((TL_dialog)v0).top_message) {
                ((TL_dialog)v0).unread_count = 0;
            }
            else {
                ((TL_dialog)v0).unread_count = Math.max(((TL_dialog)v0).unread_count - arg13, 0);
                if(arg14 != -2147483648 && ((TL_dialog)v0).unread_count > ((TL_dialog)v0).top_message - arg14) {
                    ((TL_dialog)v0).unread_count = ((TL_dialog)v0).top_message - arg14;
                }
            }

            if((v3 != 0 || (((TL_dialog)v0).unread_mark)) && (((TL_dialog)v0).unread_count == 0 && !arg10.isDialogMuted(arg11))) {
                --arg10.unreadUnmutedDialogs;
            }

            if(((TL_dialog)v0).unread_mark) {
                ((TL_dialog)v0).unread_mark = false;
                MessagesStorage.getInstance(arg10.currentAccount).setDialogUnread(((TL_dialog)v0).id, false);
            }

            NotificationCenter.getInstance(arg10.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(256)});
        }

        if(!arg15) {
            NotificationsController.getInstance(arg10.currentAccount).processReadMessages(null, arg11, 0, arg14, false);
            v13 = new LongSparseArray(1);
            v14 = Integer.valueOf(0);
        }
        else {
            NotificationsController.getInstance(arg10.currentAccount).processReadMessages(null, arg11, 0, arg14, true);
            v13 = new LongSparseArray(1);
            v14 = Integer.valueOf(-1);
        }

        v13.put(arg11, v14);
        NotificationsController.getInstance(arg10.currentAccount).processDialogsUpdateRead(v13);
    }

    public static void lambda$null$13(MessagesController arg4, int arg5, TL_messages_chatFull arg6, int arg7) {
        arg4.applyDialogNotificationsSettings(((long)(-arg5)), arg6.full_chat.notify_settings);
        int v1;
        for(v1 = 0; v1 < arg6.full_chat.bot_info.size(); ++v1) {
            DataQuery.getInstance(arg4.currentAccount).putBotInfo(arg6.full_chat.bot_info.get(v1));
        }

        arg4.exportedChats.put(arg5, arg6.full_chat.exported_invite);
        arg4.loadingFullChats.remove(Integer.valueOf(arg5));
        arg4.loadedFullChats.add(Integer.valueOf(arg5));
        arg4.putUsers(arg6.users, false);
        arg4.putChats(arg6.chats, false);
        if(arg6.full_chat.stickerset != null) {
            DataQuery.getInstance(arg4.currentAccount).getGroupStickerSetById(arg6.full_chat.stickerset);
        }

        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{arg6.full_chat, Integer.valueOf(arg7), Boolean.valueOf(false), null});
    }

    public static void lambda$null$131(MessagesController arg8, long arg9, int arg11, boolean arg12, int arg13, int arg14) {
        NotificationsController.getInstance(arg8.currentAccount).processReadMessages(null, arg9, arg11, 0, arg12);
        Object v11 = arg8.dialogs_dict.get(arg9);
        if(v11 != null) {
            int v1 = ((TL_dialog)v11).unread_count;
            if(arg13 == 0 || arg14 <= ((TL_dialog)v11).top_message) {
                ((TL_dialog)v11).unread_count = 0;
            }
            else {
                ((TL_dialog)v11).unread_count = Math.max(((TL_dialog)v11).unread_count - arg13, 0);
                if(arg14 != 2147483647 && ((TL_dialog)v11).unread_count > arg14 - ((TL_dialog)v11).top_message) {
                    ((TL_dialog)v11).unread_count = arg14 - ((TL_dialog)v11).top_message;
                }
            }

            if((v1 != 0 || (((TL_dialog)v11).unread_mark)) && (((TL_dialog)v11).unread_count == 0 && !arg8.isDialogMuted(arg9))) {
                --arg8.unreadUnmutedDialogs;
            }

            if(((TL_dialog)v11).unread_mark) {
                ((TL_dialog)v11).unread_mark = false;
                MessagesStorage.getInstance(arg8.currentAccount).setDialogUnread(((TL_dialog)v11).id, false);
            }

            NotificationCenter.getInstance(arg8.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(256)});
        }

        LongSparseArray v11_1 = new LongSparseArray(1);
        v11_1.put(arg9, Integer.valueOf(0));
        NotificationsController.getInstance(arg8.currentAccount).processDialogsUpdateRead(v11_1);
    }

    public static void lambda$null$134(MessagesController arg3, TL_error arg4, BaseFragment arg5, TL_messages_createChat arg6) {
        AlertsCreator.processError(arg3.currentAccount, arg4, arg5, ((TLObject)arg6), new Object[0]);
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate, new Object[0]);
    }

    public static void lambda$null$135(MessagesController arg4, Updates arg5) {
        arg4.putUsers(arg5.users, false);
        arg4.putChats(arg5.chats, false);
        if(arg5.chats == null || (arg5.chats.isEmpty())) {
            NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate, new Object[0]);
        }
        else {
            NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.chatDidCreated, new Object[]{Integer.valueOf(arg5.chats.get(0).id)});
        }
    }

    public static void lambda$null$137(MessagesController arg3, TL_error arg4, BaseFragment arg5, TL_channels_createChannel arg6) {
        AlertsCreator.processError(arg3.currentAccount, arg4, arg5, ((TLObject)arg6), new Object[0]);
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate, new Object[0]);
    }

    public static void lambda$null$138(MessagesController arg4, Updates arg5) {
        arg4.putUsers(arg5.users, false);
        arg4.putChats(arg5.chats, false);
        if(arg5.chats == null || (arg5.chats.isEmpty())) {
            NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.chatDidFailCreate, new Object[0]);
        }
        else {
            NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.chatDidCreated, new Object[]{Integer.valueOf(arg5.chats.get(0).id)});
        }
    }

    public static void lambda$null$14(MessagesController arg0, TL_error arg1, int arg2) {
        arg0.checkChannelError(arg1.text, arg2);
        arg0.loadingFullChats.remove(Integer.valueOf(arg2));
    }

    static void lambda$null$140(Context arg0, AlertDialog arg1) {
        if(!((Activity)arg0).isFinishing()) {
            try {
                arg1.dismiss();
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
            }
        }
    }

    static void lambda$null$141(Context arg1, AlertDialog arg2) {
        if(!arg1.isFinishing()) {
            try {
                arg2.dismiss();
            }
            catch(Exception v2) {
                FileLog.e(((Throwable)v2));
            }

            Builder v2_1 = new Builder(arg1);
            v2_1.setTitle(LocaleController.getString("AppName", 2131624086));
            v2_1.setMessage(LocaleController.getString("ErrorOccurred", 2131624696));
            v2_1.setPositiveButton(LocaleController.getString("OK", 2131625420), null);
            v2_1.show().setCanceledOnTouchOutside(true);
        }
    }

    public static void lambda$null$144(MessagesController arg4, TL_error arg5, BaseFragment arg6, TL_channels_inviteToChannel arg7) {
        AlertsCreator.processError(arg4.currentAccount, arg5, arg6, ((TLObject)arg7), new Object[]{Boolean.valueOf(true)});
    }

    public static void lambda$null$147(MessagesController arg5) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(8192)});
    }

    public static void lambda$null$149(MessagesController arg5) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(8192)});
    }

    public static void lambda$null$151(MessagesController arg4, ChatFull arg5, String arg6) {
        arg5.about = arg6;
        MessagesStorage.getInstance(arg4.currentAccount).updateChatInfo(arg5, false);
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{arg5, Integer.valueOf(0), Boolean.valueOf(false), null});
    }

    public static void lambda$null$153(MessagesController arg3, int arg4, String arg5) {
        Chat v4 = arg3.getChat(Integer.valueOf(arg4));
        int v0 = arg5.length() != 0 ? v4.flags | 64 : v4.flags & -65;
        v4.flags = v0;
        v4.username = arg5;
        ArrayList v5 = new ArrayList();
        v5.add(v4);
        MessagesStorage.getInstance(arg3.currentAccount).putUsersAndChats(null, v5, true, true);
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(8192)});
    }

    public static void lambda$null$158(MessagesController arg1, int arg2) {
        arg1.joiningToChannels.remove(Integer.valueOf(arg2));
    }

    public static void lambda$null$159(MessagesController arg4, TL_error arg5, BaseFragment arg6, TLObject arg7, boolean arg8, boolean arg9) {
        int v0 = arg4.currentAccount;
        boolean v1 = true;
        Object[] v2 = new Object[1];
        if(!arg8 || (arg9)) {
            v1 = false;
        }
        else {
        }

        v2[0] = Boolean.valueOf(v1);
        AlertsCreator.processError(v0, arg5, arg6, arg7, v2);
    }

    public static void lambda$null$16(MessagesController arg7, TLObject arg8, User arg9, int arg10) {
        arg7.applyDialogNotificationsSettings(((long)arg9.id), ((TL_userFull)arg8).notify_settings);
        if((((TL_userFull)arg8).bot_info instanceof TL_botInfo)) {
            DataQuery.getInstance(arg7.currentAccount).putBotInfo(((TL_userFull)arg8).bot_info);
        }

        int v0 = arg7.blockedUsers.indexOfKey(arg9.id);
        if(((TL_userFull)arg8).blocked) {
            if(v0 < 0) {
                SparseIntArray v0_1 = new SparseIntArray();
                v0_1.put(arg9.id, 1);
                MessagesStorage.getInstance(arg7.currentAccount).putBlockedUsers(v0_1, false);
                arg7.blockedUsers.put(arg9.id, 1);
                goto label_29;
            }
        }
        else if(v0 >= 0) {
            MessagesStorage.getInstance(arg7.currentAccount).deleteBlockedUser(arg9.id);
            arg7.blockedUsers.removeAt(v0);
        label_29:
            NotificationCenter.getInstance(arg7.currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoaded, new Object[0]);
        }

        arg7.fullUsers.put(arg9.id, arg8);
        arg7.loadingFullUsers.remove(Integer.valueOf(arg9.id));
        arg7.loadedFullUsers.add(Integer.valueOf(arg9.id));
        String v0_3 = arg9.first_name + arg9.last_name + arg9.username;
        ArrayList v1 = new ArrayList();
        v1.add(((TL_userFull)arg8).user);
        arg7.putUsers(v1, false);
        MessagesStorage.getInstance(arg7.currentAccount).putUsersAndChats(v1, null, false, true);
        if(v0_3 != null) {
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append(((TL_userFull)arg8).user.first_name);
            v1_1.append(((TL_userFull)arg8).user.last_name);
            v1_1.append(((TL_userFull)arg8).user.username);
            if(!v0_3.equals(v1_1.toString())) {
                NotificationCenter.getInstance(arg7.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(1)});
            }
        }

        int v1_2 = 2;
        if((((TL_userFull)arg8).bot_info instanceof TL_botInfo)) {
            NotificationCenter v0_4 = NotificationCenter.getInstance(arg7.currentAccount);
            int v4 = NotificationCenter.botInfoDidLoaded;
            Object[] v5 = new Object[v1_2];
            v5[0] = ((TL_userFull)arg8).bot_info;
            v5[1] = Integer.valueOf(arg10);
            v0_4.postNotificationName(v4, v5);
        }

        NotificationCenter v10 = NotificationCenter.getInstance(arg7.currentAccount);
        v0 = NotificationCenter.userInfoDidLoaded;
        Object[] v1_3 = new Object[v1_2];
        v1_3[0] = Integer.valueOf(arg9.id);
        v1_3[1] = arg8;
        v10.postNotificationName(v0, v1_3);
    }

    public static void lambda$null$160(MessagesController arg2, int arg3) {
        arg2.loadFullChat(arg3, 0, true);
    }

    public static void lambda$null$162(MessagesController arg2, int arg3) {
        arg2.deleteDialog(((long)(-arg3)), 0);
    }

    public static void lambda$null$163(MessagesController arg2, int arg3) {
        arg2.loadFullChat(arg3, 0, true);
    }

    public static void lambda$null$17(MessagesController arg1, User arg2) {
        arg1.loadingFullUsers.remove(Integer.valueOf(arg2.id));
    }

    public static void lambda$null$170(MessagesController arg1) {
        arg1.registeringForPush = false;
    }

    public static void lambda$null$179(MessagesController arg2, updates_ChannelDifference arg3) {
        arg2.putUsers(arg3.users, false);
        arg2.putChats(arg3.chats, false);
    }

    public static void lambda$null$180(MessagesController arg9, SparseArray arg10) {
        int v1;
        for(v1 = 0; v1 < arg10.size(); ++v1) {
            int v2 = arg10.keyAt(v1);
            Object v3 = arg10.valueAt(v1);
            int v5 = ((int)v3[1]);
            SendMessagesHelper.getInstance(arg9.currentAccount).processSentMessage(v5);
            NotificationCenter.getInstance(arg9.currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(v5), Integer.valueOf(v2), null, Long.valueOf(v3[0]), Long.valueOf(0)});
        }
    }

    public static void lambda$null$181(MessagesController arg5, LongSparseArray arg6) {
        int v1;
        for(v1 = 0; v1 < arg6.size(); ++v1) {
            arg5.updateInterfaceWithMessages(arg6.keyAt(v1), arg6.valueAt(v1));
        }

        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$null$182(MessagesController arg3, ArrayList arg4) {
        NotificationsController.getInstance(arg3.currentAccount).processNewMessages(arg4, true, false);
    }

    public static void lambda$null$183(MessagesController arg6, ArrayList arg7, updates_ChannelDifference arg8) {
        if(!arg7.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$mJeSL6D7ERMZfb_xJPLaA6RamlM(arg6, arg7));
        }

        MessagesStorage.getInstance(arg6.currentAccount).putMessages(arg8.new_messages, true, false, false, DownloadController.getInstance(arg6.currentAccount).getAutodownloadMask());
    }

    public static void lambda$null$184(MessagesController arg17, updates_ChannelDifference arg18, int arg19, Chat arg20, SparseArray arg21, int arg22, long arg23) {
        boolean v9;
        Object v15;
        Integer v12_1;
        Object v12;
        long v10;
        int v8_1;
        ArrayList v3_2;
        boolean v7_1;
        Integer v13_2;
        MessagesController v0 = arg17;
        updates_ChannelDifference v1 = arg18;
        int v2 = arg19;
        Chat v3 = arg20;
        long v4 = arg23;
        int v7 = -2147483648;
        if(((v1 instanceof TL_updates_channelDifference)) || ((v1 instanceof TL_updates_channelDifferenceEmpty))) {
            if(!v1.new_messages.isEmpty()) {
                LongSparseArray v6_2 = new LongSparseArray();
                ImageLoader.saveMessagesThumbs(v1.new_messages);
                ArrayList v9_1 = new ArrayList();
                v10 = ((long)(-v2));
                v12 = v0.dialogs_read_inbox_max.get(Long.valueOf(v10));
                if(v12 == null) {
                    v12_1 = Integer.valueOf(MessagesStorage.getInstance(v0.currentAccount).getDialogReadMax(false, v10));
                    v0.dialogs_read_inbox_max.put(Long.valueOf(v10), v12_1);
                }

                Object v13_1 = v0.dialogs_read_outbox_max.get(Long.valueOf(v10));
                if(v13_1 == null) {
                    v13_2 = Integer.valueOf(MessagesStorage.getInstance(v0.currentAccount).getDialogReadMax(true, v10));
                    v0.dialogs_read_outbox_max.put(Long.valueOf(v10), v13_2);
                }

                int v14_1 = 0;
                while(v14_1 < v1.new_messages.size()) {
                    v15 = v1.new_messages.get(v14_1);
                    if(v3 == null || !v3.left) {
                        Object v8 = ((Message)v15).out ? v13_2 : v12;
                        if(((Integer)v8).intValue() < ((Message)v15).id && !(((Message)v15).action instanceof TL_messageActionChannelCreate)) {
                            v7_1 = true;
                            goto label_136;
                        }

                    label_135:
                        v7_1 = false;
                    }
                    else {
                        goto label_135;
                    }

                label_136:
                    ((Message)v15).unread = v7_1;
                    if(v3 != null && (v3.megagroup)) {
                        ((Message)v15).flags |= -2147483648;
                    }

                    Object v16 = v12;
                    MessageObject v7_2 = new MessageObject(v0.currentAccount, ((Message)v15), arg21, v0.createdDialogIds.contains(Long.valueOf(v10)));
                    if(!v7_2.isOut() && (v7_2.isUnread())) {
                        v9_1.add(v7_2);
                    }

                    Object v3_1 = v6_2.get(v10);
                    if(v3_1 == null) {
                        v3_2 = new ArrayList();
                        v6_2.put(v10, v3_2);
                    }

                    v3_2.add(v7_2);
                    ++v14_1;
                    v12 = v16;
                    v3 = arg20;
                }

                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$8VgAqbBzfFdNkkoi0GpWbFxgjYQ(v0, v6_2));
                MessagesStorage.getInstance(v0.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$Un69-OuluA0NhgxFw9JeFZPdu1w(v0, v9_1, v1));
            }

            if(!v1.other_updates.isEmpty()) {
                v8_1 = 1;
                v0.processUpdateArray(v1.other_updates, v1.users, v1.chats, true);
            }
            else {
                v8_1 = 1;
            }

            v0.processChannelsUpdatesQueue(v2, v8_1);
            MessagesStorage.getInstance(v0.currentAccount).saveChannelPts(v2, v1.pts);
        }
        else if((v1 instanceof TL_updates_channelDifferenceTooLong)) {
            v10 = ((long)(-v2));
            Object v6 = v0.dialogs_read_inbox_max.get(Long.valueOf(v10));
            if(v6 == null) {
                Integer v6_1 = Integer.valueOf(MessagesStorage.getInstance(v0.currentAccount).getDialogReadMax(false, v10));
                v0.dialogs_read_inbox_max.put(Long.valueOf(v10), v6_1);
            }

            v12 = v0.dialogs_read_outbox_max.get(Long.valueOf(v10));
            if(v12 == null) {
                v12_1 = Integer.valueOf(MessagesStorage.getInstance(v0.currentAccount).getDialogReadMax(true, v10));
                v0.dialogs_read_outbox_max.put(Long.valueOf(v10), v12_1);
            }

            int v13;
            for(v13 = 0; v13 < v1.messages.size(); ++v13) {
                Object v14 = v1.messages.get(v13);
                ((Message)v14).dialog_id = v10;
                if(!(((Message)v14).action instanceof TL_messageActionChannelCreate)) {
                    if(v3 != null && (v3.left)) {
                        goto label_62;
                    }

                    v15 = ((Message)v14).out ? v12 : v6;
                    if(((Integer)v15).intValue() >= ((Message)v14).id) {
                        goto label_62;
                    }

                    v9 = true;
                }
                else {
                label_62:
                    v9 = false;
                }

                ((Message)v14).unread = v9;
                if(v3 != null && (v3.megagroup)) {
                    ((Message)v14).flags |= v7;
                }
            }

            MessagesStorage.getInstance(v0.currentAccount).overwriteChannel(v2, v1, arg22);
        }

        v0.gettingDifferenceChannels.delete(v2);
        v0.channelsPts.put(v2, v1.pts);
        if((v1.flags & 2) != 0) {
            v0.shortPollChannels.put(v2, (((int)(System.currentTimeMillis() / 1000))) + v1.timeout);
        }

        if(!v1.isFinal) {
            v0.getChannelDifference(v2);
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("received channel difference with pts = " + v1.pts + " channelId = " + v2);
            FileLog.d("new_messages = " + v1.new_messages.size() + " messages = " + v1.messages.size() + " users = " + v1.users.size() + " chats = " + v1.chats.size() + " other updates = " + v1.other_updates.size());
        }

        if(v4 != 0) {
            MessagesStorage.getInstance(v0.currentAccount).removePendingTask(v4);
        }
    }

    public static void lambda$null$185(MessagesController arg18, ArrayList arg19, int arg20, updates_ChannelDifference arg21, Chat arg22, SparseArray arg23, int arg24, long arg25) {
        MessagesController v9 = arg18;
        if(!arg19.isEmpty()) {
            SparseArray v0 = new SparseArray();
            Iterator v1 = arg19.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                long[] v3 = MessagesStorage.getInstance(v9.currentAccount).updateMessageStateAndId(((TL_updateMessageID)v2).random_id, null, ((TL_updateMessageID)v2).id, 0, false, arg20);
                if(v3 == null) {
                    continue;
                }

                v0.put(((TL_updateMessageID)v2).id, v3);
            }

            if(v0.size() != 0) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$L1rW9jSd7qPglef4pIejXotswIU(v9, v0));
            }
        }

        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$KB-3eWjo2Ie6kSuUNkVzMRDasrU(arg18, arg21, arg20, arg22, arg23, arg24, arg25));
    }

    public static void lambda$null$186(MessagesController arg0, TL_error arg1, int arg2) {
        arg0.checkChannelError(arg1.text, arg2);
    }

    public static void lambda$null$188(MessagesController arg7, updates_Difference arg8, int arg9, int arg10) {
        arg7.loadedFullUsers.clear();
        arg7.loadedFullChats.clear();
        arg7.resetDialogs(true, MessagesStorage.getInstance(arg7.currentAccount).getLastSeqValue(), arg8.pts, arg9, arg10);
    }

    public static void lambda$null$189(MessagesController arg2, updates_Difference arg3) {
        arg2.loadedFullUsers.clear();
        arg2.loadedFullChats.clear();
        arg2.putUsers(arg3.users, false);
        arg2.putChats(arg3.chats, false);
    }

    public static void lambda$null$19(MessagesController arg5, long arg6, ArrayList arg8, ArrayList arg9) {
        Object v0 = arg5.reloadingMessages.get(arg6);
        if(v0 != null) {
            ((ArrayList)v0).removeAll(((Collection)arg8));
            if(((ArrayList)v0).isEmpty()) {
                arg5.reloadingMessages.remove(arg6);
            }
        }

        Object v8 = arg5.dialogMessage.get(arg6);
        if(v8 != null) {
            int v1;
            for(v1 = 0; v1 < arg9.size(); ++v1) {
                Object v2 = arg9.get(v1);
                if(v8 != null && ((MessageObject)v8).getId() == ((MessageObject)v2).getId()) {
                    arg5.dialogMessage.put(arg6, v2);
                    if(((MessageObject)v2).messageOwner.to_id.channel_id == 0) {
                        v8 = arg5.dialogMessagesByIds.get(((MessageObject)v2).getId());
                        arg5.dialogMessagesByIds.remove(((MessageObject)v2).getId());
                        if(v8 != null) {
                            arg5.dialogMessagesByIds.put(((MessageObject)v8).getId(), v8);
                        }
                    }

                    NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
                    break;
                }
            }
        }

        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(arg6), arg9});
    }

    public static void lambda$null$190(MessagesController arg9, SparseArray arg10) {
        int v1;
        for(v1 = 0; v1 < arg10.size(); ++v1) {
            int v2 = arg10.keyAt(v1);
            Object v3 = arg10.valueAt(v1);
            int v5 = ((int)v3[1]);
            SendMessagesHelper.getInstance(arg9.currentAccount).processSentMessage(v5);
            NotificationCenter.getInstance(arg9.currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(v5), Integer.valueOf(v2), null, Long.valueOf(v3[0]), Long.valueOf(0)});
        }
    }

    public static void lambda$null$191(MessagesController arg5, LongSparseArray arg6) {
        int v1;
        for(v1 = 0; v1 < arg6.size(); ++v1) {
            arg5.updateInterfaceWithMessages(arg6.keyAt(v1), arg6.valueAt(v1));
        }

        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$null$192(MessagesController arg2, ArrayList arg3, updates_Difference arg4) {
        NotificationsController v0 = NotificationsController.getInstance(arg2.currentAccount);
        boolean v4 = !(arg4 instanceof TL_updates_differenceSlice) ? true : false;
        v0.processNewMessages(arg3, v4, false);
    }

    public static void lambda$null$193(MessagesController arg6, ArrayList arg7, updates_Difference arg8) {
        if(!arg7.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$v--cTdlWfbctL5-4FuAqUNLXcMc(arg6, arg7, arg8));
        }

        MessagesStorage.getInstance(arg6.currentAccount).putMessages(arg8.new_messages, true, false, false, DownloadController.getInstance(arg6.currentAccount).getAutodownloadMask());
    }

    public static void lambda$null$194(MessagesController arg18, updates_Difference arg19, SparseArray arg20, SparseArray arg21) {
        ArrayList v10_2;
        MessageObject v9_4;
        Integer v10_1;
        Object v10;
        int v9;
        MessagesController v0 = arg18;
        updates_Difference v1 = arg19;
        int v4 = 0;
        if(!v1.new_messages.isEmpty() || !v1.new_encrypted_messages.isEmpty()) {
            LongSparseArray v2 = new LongSparseArray();
            int v5;
            for(v5 = 0; v5 < v1.new_encrypted_messages.size(); ++v5) {
                ArrayList v6 = SecretChatHelper.getInstance(v0.currentAccount).decryptMessage(v1.new_encrypted_messages.get(v5));
                if(v6 != null && !v6.isEmpty()) {
                    v1.new_messages.addAll(((Collection)v6));
                }
            }

            ImageLoader.saveMessagesThumbs(v1.new_messages);
            ArrayList v5_1 = new ArrayList();
            int v6_1 = UserConfig.getInstance(v0.currentAccount).getClientUserId();
            int v7;
            for(v7 = 0; v7 < v1.new_messages.size(); ++v7) {
                Object v8 = v1.new_messages.get(v7);
                if(((Message)v8).dialog_id == 0) {
                    if(((Message)v8).to_id.chat_id != 0) {
                        v9 = -((Message)v8).to_id.chat_id;
                    }
                    else {
                        if(((Message)v8).to_id.user_id == UserConfig.getInstance(v0.currentAccount).getClientUserId()) {
                            ((Message)v8).to_id.user_id = ((Message)v8).from_id;
                        }

                        v9 = ((Message)v8).to_id.user_id;
                    }

                    ((Message)v8).dialog_id = ((long)v9);
                }

                if((((int)((Message)v8).dialog_id)) != 0) {
                    if((((Message)v8).action instanceof TL_messageActionChatDeleteUser)) {
                        if((v0.hideLeftGroup) && ((Message)v8).action.user_id == ((Message)v8).from_id) {
                            goto label_168;
                        }

                        Object v9_1 = arg20.get(((Message)v8).action.user_id);
                        if(v9_1 == null) {
                            goto label_94;
                        }

                        if(!((User)v9_1).bot) {
                            goto label_94;
                        }

                        ((Message)v8).reply_markup = new TL_replyKeyboardHide();
                        ((Message)v8).flags |= 64;
                    }

                label_94:
                    if(!(((Message)v8).action instanceof TL_messageActionChatMigrateTo)) {
                        if((((Message)v8).action instanceof TL_messageActionChannelCreate)) {
                        }
                        else {
                            ConcurrentHashMap v9_2 = ((Message)v8).out ? v0.dialogs_read_outbox_max : v0.dialogs_read_inbox_max;
                            v10 = v9_2.get(Long.valueOf(((Message)v8).dialog_id));
                            if(v10 == null) {
                                v10_1 = Integer.valueOf(MessagesStorage.getInstance(v0.currentAccount).getDialogReadMax(((Message)v8).out, ((Message)v8).dialog_id));
                                v9_2.put(Long.valueOf(((Message)v8).dialog_id), v10_1);
                            }

                            boolean v9_3 = v10_1.intValue() < ((Message)v8).id ? true : false;
                            ((Message)v8).unread = v9_3;
                            goto label_131;
                        }
                    }

                    ((Message)v8).unread = false;
                    ((Message)v8).media_unread = false;
                    goto label_131;
                }
                else {
                label_131:
                    if(((Message)v8).dialog_id == (((long)v6_1))) {
                        ((Message)v8).unread = false;
                        ((Message)v8).media_unread = false;
                        ((Message)v8).out = true;
                    }

                    MessageObject v17 = new MessageObject(v0.currentAccount, v8, arg20, arg21, v0.createdDialogIds.contains(Long.valueOf(((Message)v8).dialog_id)));
                    if((v17.isOut()) || !v17.isUnread()) {
                        v9_4 = v17;
                    }
                    else {
                        v9_4 = v17;
                        v5_1.add(v9_4);
                    }

                    v10 = v2.get(((Message)v8).dialog_id);
                    if(v10 == null) {
                        v10_2 = new ArrayList();
                        v2.put(((Message)v8).dialog_id, v10_2);
                    }

                    v10_2.add(v9_4);
                }

            label_168:
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$QH8cfrTDvSAvvZFhwAZLQBKmSls(v0, v2));
            MessagesStorage.getInstance(v0.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$_WtVGW0XQ0dGXDYt_X1lqvPe89o(v0, v5_1, v1));
            SecretChatHelper.getInstance(v0.currentAccount).processPendingEncMessages();
        }

        if(!v1.other_updates.isEmpty()) {
            v0.processUpdateArray(v1.other_updates, v1.users, v1.chats, true);
        }

        v5 = 3;
        if((v1 instanceof TL_updates_difference)) {
            v0.gettingDifference = false;
            MessagesStorage.getInstance(v0.currentAccount).setLastSeqValue(v1.state.seq);
            MessagesStorage.getInstance(v0.currentAccount).setLastDateValue(v1.state.date);
            MessagesStorage.getInstance(v0.currentAccount).setLastPtsValue(v1.state.pts);
            MessagesStorage.getInstance(v0.currentAccount).setLastQtsValue(v1.state.qts);
            ConnectionsManager.getInstance(v0.currentAccount).setIsUpdating(false);
            while(v4 < v5) {
                v0.processUpdatesQueue(v4, 1);
                ++v4;
            }
        }
        else if((v1 instanceof TL_updates_differenceSlice)) {
            MessagesStorage.getInstance(v0.currentAccount).setLastDateValue(v1.intermediate_state.date);
            MessagesStorage.getInstance(v0.currentAccount).setLastPtsValue(v1.intermediate_state.pts);
            MessagesStorage.getInstance(v0.currentAccount).setLastQtsValue(v1.intermediate_state.qts);
        }
        else if((v1 instanceof TL_updates_differenceEmpty)) {
            v0.gettingDifference = false;
            MessagesStorage.getInstance(v0.currentAccount).setLastSeqValue(v1.seq);
            MessagesStorage.getInstance(v0.currentAccount).setLastDateValue(v1.date);
            ConnectionsManager.getInstance(v0.currentAccount).setIsUpdating(false);
            while(v4 < v5) {
                v0.processUpdatesQueue(v4, 1);
                ++v4;
            }
        }

        MessagesStorage.getInstance(v0.currentAccount).saveDiffParams(MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue(), MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue(), MessagesStorage.getInstance(v0.currentAccount).getLastDateValue(), MessagesStorage.getInstance(v0.currentAccount).getLastQtsValue());
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("received difference with date = " + MessagesStorage.getInstance(v0.currentAccount).getLastDateValue() + " pts = " + MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() + " seq = " + MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue() + " messages = " + v1.new_messages.size() + " users = " + v1.users.size() + " chats = " + v1.chats.size() + " other updates = " + v1.other_updates.size());
        }
    }

    public static void lambda$null$195(MessagesController arg15, updates_Difference arg16, ArrayList arg17, SparseArray arg18, SparseArray arg19) {
        MessagesController v0 = arg15;
        updates_Difference v1 = arg16;
        int v5 = 0;
        MessagesStorage.getInstance(v0.currentAccount).putUsersAndChats(v1.users, v1.chats, true, false);
        if(!arg17.isEmpty()) {
            SparseArray v2 = new SparseArray();
            while(v5 < arg17.size()) {
                Object v4 = arg17.get(v5);
                long[] v6 = MessagesStorage.getInstance(v0.currentAccount).updateMessageStateAndId(((TL_updateMessageID)v4).random_id, null, ((TL_updateMessageID)v4).id, 0, false, 0);
                if(v6 != null) {
                    v2.put(((TL_updateMessageID)v4).id, v6);
                }

                ++v5;
            }

            if(v2.size() == 0) {
                goto label_36;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$mdaGD97b2EoerrIaNS78TUfm_fQ(arg15, v2));
        }

    label_36:
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$ILtVvIapmV0IlSAFyS0ueP9y5tw(arg15, v1, arg18, arg19));
    }

    public static void lambda$null$198(MessagesController arg8, TLObject arg9) {
        long v4_2;
        int v4_1;
        if(arg9 != null) {
            int v0 = ((Vector)arg9).objects.size();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                Object v4 = ((Vector)arg9).objects.get(v2);
                if((v4 instanceof TL_dialogPeer)) {
                    if(((TL_dialogPeer)v4).peer.user_id != 0) {
                        if(((TL_dialogPeer)v4).peer.user_id != 0) {
                            v4_1 = ((TL_dialogPeer)v4).peer.user_id;
                        }
                        else {
                            v4_1 = ((TL_dialogPeer)v4).peer.chat_id != 0 ? ((TL_dialogPeer)v4).peer.chat_id : ((TL_dialogPeer)v4).peer.channel_id;
                            v4_1 = -v4_1;
                        }

                        v4_2 = ((long)v4_1);
                    }
                    else {
                        v4_2 = 0;
                    }

                    MessagesStorage.getInstance(arg8.currentAccount).setDialogUnread(v4_2, true);
                    Object v6 = arg8.dialogs_dict.get(v4_2);
                    if(v6 == null) {
                        goto label_48;
                    }

                    if(((TL_dialog)v6).unread_mark) {
                        goto label_48;
                    }

                    ((TL_dialog)v6).unread_mark = true;
                    if(((TL_dialog)v6).unread_count != 0) {
                        goto label_48;
                    }

                    if(arg8.isDialogMuted(v4_2)) {
                        goto label_48;
                    }

                    ++arg8.unreadUnmutedDialogs;
                }

            label_48:
            }

            UserConfig.getInstance(arg8.currentAccount).unreadDialogsLoaded = true;
            UserConfig.getInstance(arg8.currentAccount).saveConfig(false);
            NotificationCenter.getInstance(arg8.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(256)});
            arg8.loadingUnreadDialogs = false;
        }
    }

    public static void lambda$null$201(MessagesController arg17, TL_messages_peerDialogs arg18, ArrayList arg19, ArrayList arg20, long arg21, LongSparseArray arg23, TL_messages_dialogs arg24) {
        Object v4_1;
        ArrayList v16;
        int v11;
        int v10;
        boolean v9;
        MessagesController v0 = arg17;
        TL_messages_peerDialogs v1 = arg18;
        v0.applyDialogsNotificationsSettings(v1.dialogs);
        LongSparseArray v2 = new LongSparseArray();
        ArrayList v3 = new ArrayList();
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        while(true) {
            v9 = true;
            if(v5 < v0.dialogs.size()) {
                Object v8 = v0.dialogs.get(v5);
                if((((int)((TL_dialog)v8).id)) == 0) {
                }
                else if(!((TL_dialog)v8).pinned) {
                    break;
                }
                else {
                    v6 = Math.max(((TL_dialog)v8).pinnedNum, v6);
                    v2.put(((TL_dialog)v8).id, Integer.valueOf(((TL_dialog)v8).pinnedNum));
                    v3.add(Long.valueOf(((TL_dialog)v8).id));
                    ((TL_dialog)v8).pinned = false;
                    ((TL_dialog)v8).pinnedNum = 0;
                    v7 = 1;
                }

                ++v5;
                continue;
            }

            break;
        }

        ArrayList v5_1 = new ArrayList();
        ArrayList v8_1 = arg19 != null ? arg19 : arg20;
        long v12 = 0;
        if(v8_1.size() < v3.size()) {
            v8_1.add(Long.valueOf(v12));
        }

        while(v3.size() < v8_1.size()) {
            v3.add(0, Long.valueOf(v12));
        }

        if(!v1.dialogs.isEmpty()) {
            v0.putUsers(v1.users, false);
            v0.putChats(v1.chats, false);
            v10 = v7;
            v7 = 0;
            v11 = 0;
            goto label_67;
        }
        else {
            v10 = v7;
            v11 = 0;
            goto label_160;
        label_67:
            while(v7 < v1.dialogs.size()) {
                Object v10_1 = v1.dialogs.get(v7);
                if(arg21 != v12) {
                    Object v12_1 = v2.get(((TL_dialog)v10_1).id);
                    if(v12_1 != null) {
                        ((TL_dialog)v10_1).pinnedNum = ((Integer)v12_1).intValue();
                    }

                    v16 = v5_1;
                }
                else {
                    int v12_2 = v3.indexOf(Long.valueOf(((TL_dialog)v10_1).id));
                    v16 = v5_1;
                    int v4 = v8_1.indexOf(Long.valueOf(((TL_dialog)v10_1).id));
                    v5 = -1;
                    if(v12_2 == v5) {
                        goto label_101;
                    }

                    if(v4 == v5) {
                        goto label_101;
                    }

                    if(v12_2 == v4) {
                        v4_1 = v2.get(((TL_dialog)v10_1).id);
                        if(v4_1 != null) {
                        }
                        else {
                            goto label_101;
                        }
                    }
                    else {
                        v4_1 = v2.get(v3.get(v4).longValue());
                        if(v4_1 != null) {
                            goto label_99;
                        }

                        goto label_101;
                    }

                label_99:
                    ((TL_dialog)v10_1).pinnedNum = ((Integer)v4_1).intValue();
                }

            label_101:
                if(((TL_dialog)v10_1).pinnedNum == 0) {
                    ((TL_dialog)v10_1).pinnedNum = v1.dialogs.size() - v7 + v6;
                }

                v5_1 = v16;
                v5_1.add(Long.valueOf(((TL_dialog)v10_1).id));
                v4_1 = v0.dialogs_dict.get(((TL_dialog)v10_1).id);
                if(v4_1 != null) {
                    ((TL_dialog)v4_1).pinned = v9;
                    ((TL_dialog)v4_1).pinnedNum = ((TL_dialog)v10_1).pinnedNum;
                    MessagesStorage.getInstance(v0.currentAccount).setDialogPinned(((TL_dialog)v10_1).id, ((TL_dialog)v10_1).pinnedNum);
                }
                else {
                    v0.dialogs_dict.put(((TL_dialog)v10_1).id, v10_1);
                    Object v11_1 = arg23.get(((TL_dialog)v10_1).id);
                    v0.dialogMessage.put(((TL_dialog)v10_1).id, v11_1);
                    if(v11_1 != null && ((MessageObject)v11_1).messageOwner.to_id.channel_id == 0) {
                        v0.dialogMessagesByIds.put(((MessageObject)v11_1).getId(), v11_1);
                        if(((MessageObject)v11_1).messageOwner.random_id != 0) {
                            v0.dialogMessagesByRandomIds.put(((MessageObject)v11_1).messageOwner.random_id, v11_1);
                        }
                    }

                    v11 = 1;
                }

                ++v7;
                v9 = true;
                v10 = 1;
                v12 = 0;
            }
        }

    label_160:
        if(v10 != 0) {
            if(v11 != 0) {
                v0.dialogs.clear();
                int v1_1 = v0.dialogs_dict.size();
                int v2_1;
                for(v2_1 = 0; v2_1 < v1_1; ++v2_1) {
                    v0.dialogs.add(v0.dialogs_dict.valueAt(v2_1));
                }
            }

            v0.sortDialogs(null);
            NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }

        MessagesStorage.getInstance(v0.currentAccount).unpinAllDialogsExceptNew(v5_1);
        MessagesStorage.getInstance(v0.currentAccount).putDialogs(arg24, 1);
        UserConfig.getInstance(v0.currentAccount).pinnedDialogsLoaded = true;
        UserConfig.getInstance(v0.currentAccount).saveConfig(false);
    }

    public static void lambda$null$202(MessagesController arg10, TL_messages_peerDialogs arg11, ArrayList arg12, ArrayList arg13, long arg14, LongSparseArray arg16, TL_messages_dialogs arg17) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$DIRUqEAwQudzmh5nC2UkNLmDJIo(arg10, arg11, arg12, arg13, arg14, arg16, arg17));
    }

    public static void lambda$null$204(MessagesController arg3, ArrayList arg4) {
        NotificationsController.getInstance(arg3.currentAccount).processNewMessages(arg4, true, false);
    }

    public static void lambda$null$207(MessagesController arg1, TL_channels_channelParticipant arg2) {
        arg1.putUsers(arg2.users, false);
    }

    public static void lambda$null$208(MessagesController arg3, ArrayList arg4) {
        NotificationsController.getInstance(arg3.currentAccount).processNewMessages(arg4, true, false);
    }

    public static void lambda$null$209(MessagesController arg1, ArrayList arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$ArxkNXH9u4WWo5Fu7PfVYGMNeGs(arg1, arg2));
    }

    public static void lambda$null$210(MessagesController arg2, int arg3, ArrayList arg4) {
        arg2.updateInterfaceWithMessages(((long)(-arg3)), arg4);
        NotificationCenter.getInstance(arg2.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$null$211(MessagesController arg9, Chat arg10, int arg11, TLObject arg12, TL_error arg13) {
        if(arg12 != null && ((((TL_channels_channelParticipant)arg12).participant instanceof TL_channelParticipantSelf)) && ((TL_channels_channelParticipant)arg12).participant.inviter_id != UserConfig.getInstance(arg9.currentAccount).getClientUserId()) {
            if((arg10.megagroup) && (MessagesStorage.getInstance(arg9.currentAccount).isMigratedChat(arg10.id))) {
                return;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$V_r618c1JT3f_zTZVsrCMF9q79M(arg9, ((TL_channels_channelParticipant)arg12)));
            MessagesStorage.getInstance(arg9.currentAccount).putUsersAndChats(((TL_channels_channelParticipant)arg12).users, null, true, true);
            TL_messageService v13 = new TL_messageService();
            v13.media_unread = true;
            v13.unread = true;
            v13.flags = 256;
            v13.post = true;
            if(arg10.megagroup) {
                v13.flags |= -2147483648;
            }

            int v10 = UserConfig.getInstance(arg9.currentAccount).getNewMessageId();
            v13.id = v10;
            v13.local_id = v10;
            v13.date = ((TL_channels_channelParticipant)arg12).participant.date;
            v13.action = new TL_messageActionChatAddUser();
            v13.from_id = ((TL_channels_channelParticipant)arg12).participant.inviter_id;
            v13.action.users.add(Integer.valueOf(UserConfig.getInstance(arg9.currentAccount).getClientUserId()));
            v13.to_id = new TL_peerChannel();
            v13.to_id.channel_id = arg11;
            v13.dialog_id = ((long)(-arg11));
            int v0 = 0;
            UserConfig.getInstance(arg9.currentAccount).saveConfig(false);
            ArrayList v10_1 = new ArrayList();
            ArrayList v4 = new ArrayList();
            ConcurrentHashMap v1 = new ConcurrentHashMap();
            while(v0 < ((TL_channels_channelParticipant)arg12).users.size()) {
                Object v3 = ((TL_channels_channelParticipant)arg12).users.get(v0);
                v1.put(Integer.valueOf(((User)v3).id), v3);
                ++v0;
            }

            v4.add(v13);
            v10_1.add(new MessageObject(arg9.currentAccount, ((Message)v13), ((AbstractMap)v1), true));
            MessagesStorage.getInstance(arg9.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$4B94adrwDzeoQZHTA9uMkF2EXhg(arg9, v10_1));
            MessagesStorage.getInstance(arg9.currentAccount).putMessages(v4, true, true, false, 0);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$b0kXsAMbYTzsCLbZhTDv_w2ZsQw(arg9, arg11, v10_1));
        }
    }

    public static void lambda$null$215(MessagesController arg3, ArrayList arg4) {
        NotificationsController.getInstance(arg3.currentAccount).processNewMessages(arg4, true, false);
    }

    public static void lambda$null$221(MessagesController arg2, TL_updateUserBlocked arg3) {
        if(!arg3.blocked) {
            arg2.blockedUsers.delete(arg3.user_id);
        }
        else if(arg2.blockedUsers.indexOfKey(arg3.user_id) < 0) {
            arg2.blockedUsers.put(arg3.user_id, 1);
        }

        NotificationCenter.getInstance(arg2.currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoaded, new Object[0]);
    }

    public static void lambda$null$224(MessagesController arg3, ArrayList arg4) {
        NotificationsController.getInstance(arg3.currentAccount).processNewMessages(arg4, true, false);
    }

    public static void lambda$null$226(MessagesController arg2, User arg3) {
        ContactsController.getInstance(arg2.currentAccount).addContactToPhoneBook(arg3, true);
    }

    public static void lambda$null$227(MessagesController arg6, TL_updateChannel arg7) {
        arg6.getChannelDifference(arg7.channel_id, 1, 0, null);
    }

    public static void lambda$null$228(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg1 != null) {
            arg0.processUpdates(((Updates)arg1), false);
        }
    }

    public static void lambda$null$230(MessagesController arg17, SparseLongArray arg18, SparseLongArray arg19, SparseIntArray arg20, ArrayList arg21, SparseArray arg22, SparseIntArray arg23) {
        long v5_1;
        Object v3_2;
        int v1_2;
        Object v6_1;
        int v5;
        int v4;
        int v3_1;
        int v2;
        MessagesController v0 = arg17;
        SparseLongArray v8 = arg18;
        SparseLongArray v9 = arg19;
        SparseIntArray v10 = arg20;
        SparseArray v12 = arg22;
        SparseIntArray v13 = arg23;
        int v14 = 2;
        int v15 = 0;
        if(v8 != null || v9 != null) {
            NotificationCenter v1 = NotificationCenter.getInstance(v0.currentAccount);
            v2 = NotificationCenter.messagesRead;
            Object[] v3 = new Object[v14];
            v3[0] = v8;
            v3[1] = v9;
            v1.postNotificationName(v2, v3);
            if(v8 != null) {
                NotificationsController.getInstance(v0.currentAccount).processReadMessages(arg18, 0, 0, 0, false);
                SharedPreferences$Editor v1_1 = v0.notificationsPreferences.edit();
                v2 = arg18.size();
                v3_1 = 0;
                v4 = 0;
                while(v3_1 < v2) {
                    v5 = v8.keyAt(v3_1);
                    int v6 = ((int)v8.valueAt(v3_1));
                    Object v7 = v0.dialogs_dict.get(((long)v5));
                    if(v7 != null && ((TL_dialog)v7).top_message > 0 && ((TL_dialog)v7).top_message <= v6) {
                        v6_1 = v0.dialogMessage.get(((TL_dialog)v7).id);
                        if(v6_1 != null && !((MessageObject)v6_1).isOut()) {
                            ((MessageObject)v6_1).setIsRead();
                            v4 |= 256;
                        }
                    }

                    if(v5 != UserConfig.getInstance(v0.currentAccount).getClientUserId()) {
                        v1_1.remove("diditem" + v5);
                        v1_1.remove("diditemo" + v5);
                    }

                    ++v3_1;
                }

                v1_1.commit();
                v15 = v4;
            }
            else {
                v15 = 0;
            }

            if(v9 == null) {
                goto label_104;
            }

            v1_2 = arg19.size();
            for(v2 = 0; v2 < v1_2; ++v2) {
                v3_1 = v9.keyAt(v2);
                v4 = ((int)v9.valueAt(v2));
                v3_2 = v0.dialogs_dict.get(((long)v3_1));
                if(v3_2 != null && ((TL_dialog)v3_2).top_message > 0 && ((TL_dialog)v3_2).top_message <= v4) {
                    v3_2 = v0.dialogMessage.get(((TL_dialog)v3_2).id);
                    if(v3_2 != null && (((MessageObject)v3_2).isOut())) {
                        ((MessageObject)v3_2).setIsRead();
                        v15 |= 256;
                    }
                }
            }
        }

    label_104:
        if(v10 != null) {
            v1_2 = arg20.size();
            for(v2 = 0; v2 < v1_2; ++v2) {
                v3_1 = v10.keyAt(v2);
                v4 = v10.valueAt(v2);
                NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.messagesReadEncrypted, new Object[]{Integer.valueOf(v3_1), Integer.valueOf(v4)});
                v5_1 = (((long)v3_1)) << 32;
                if(v0.dialogs_dict.get(v5_1) != null) {
                    v3_2 = v0.dialogMessage.get(v5_1);
                    if(v3_2 != null && ((MessageObject)v3_2).messageOwner.date <= v4) {
                        ((MessageObject)v3_2).setIsRead();
                        v15 |= 256;
                    }
                }
            }
        }

        if(arg21 != null) {
            NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.messagesReadContent, new Object[]{arg21});
        }

        if(v12 != null) {
            v1_2 = arg22.size();
            for(v2 = 0; v2 < v1_2; ++v2) {
                v3_1 = v12.keyAt(v2);
                Object v4_1 = v12.valueAt(v2);
                if(v4_1 == null) {
                }
                else {
                    NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.messagesDeleted, new Object[]{v4_1, Integer.valueOf(v3_1)});
                    if(v3_1 == 0) {
                        v3_1 = ((ArrayList)v4_1).size();
                        for(v5 = 0; v5 < v3_1; ++v5) {
                            v6_1 = v0.dialogMessagesByIds.get(((ArrayList)v4_1).get(v5).intValue());
                            if(v6_1 != null) {
                                ((MessageObject)v6_1).deleted = true;
                            }
                        }
                    }
                    else {
                        v3_2 = v0.dialogMessage.get(((long)(-v3_1)));
                        if(v3_2 == null) {
                            goto label_194;
                        }

                        v5 = ((ArrayList)v4_1).size();
                        for(v6 = 0; v6 < v5; ++v6) {
                            if(((MessageObject)v3_2).getId() == ((ArrayList)v4_1).get(v6).intValue()) {
                                ((MessageObject)v3_2).deleted = true;
                                break;
                            }
                        }
                    }
                }

            label_194:
            }

            NotificationsController.getInstance(v0.currentAccount).removeDeletedMessagesFromNotifications(v12);
        }

        if(v13 != null) {
            v1_2 = arg23.size();
            for(v2 = 0; v2 < v1_2; ++v2) {
                v3_1 = v13.keyAt(v2);
                v4 = v13.valueAt(v2);
                v5_1 = ((long)(-v3_1));
                NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.historyCleared, new Object[]{Long.valueOf(v5_1), Integer.valueOf(v4)});
                v3_2 = v0.dialogMessage.get(v5_1);
                if(v3_2 != null && ((MessageObject)v3_2).getId() <= v4) {
                    ((MessageObject)v3_2).deleted = true;
                    break;
                }
            }

            NotificationsController.getInstance(v0.currentAccount).removeDeletedHisoryFromNotifications(v13);
        }

        if(v15 != 0) {
            NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(v15)});
        }
    }

    public static void lambda$null$234(MessagesController arg2, AlertDialog arg3, TLObject arg4, BaseFragment arg5, Bundle arg6) {
        try {
            arg3.dismiss();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        arg2.putUsers(((messages_Messages)arg4).users, false);
        arg2.putChats(((messages_Messages)arg4).chats, false);
        MessagesStorage.getInstance(arg2.currentAccount).putUsersAndChats(((messages_Messages)arg4).users, ((messages_Messages)arg4).chats, true, true);
        arg5.presentFragment(new ChatActivity(arg6), true);
    }

    public static void lambda$null$237(MessagesController arg4, AlertDialog[] arg5, BaseFragment arg6, TL_error arg7, TLObject arg8, int arg9) {
        try {
            arg5[0].dismiss();
            goto label_3;
        }
        catch(Exception ) {
        label_3:
            AlertDialog v1 = null;
            arg5[0] = v1;
            arg6.setVisibleDialog(((Dialog)v1));
            if(arg7 == null) {
                arg4.putUsers(((TL_contacts_resolvedPeer)arg8).users, false);
                arg4.putChats(((TL_contacts_resolvedPeer)arg8).chats, false);
                MessagesStorage.getInstance(arg4.currentAccount).putUsersAndChats(((TL_contacts_resolvedPeer)arg8).users, ((TL_contacts_resolvedPeer)arg8).chats, false, true);
                if(!((TL_contacts_resolvedPeer)arg8).chats.isEmpty()) {
                    MessagesController.openChatOrProfileWith(((User)v1), ((TL_contacts_resolvedPeer)arg8).chats.get(0), arg6, 1, false);
                    return;
                }

                if(((TL_contacts_resolvedPeer)arg8).users.isEmpty()) {
                    return;
                }

                MessagesController.openChatOrProfileWith(((TL_contacts_resolvedPeer)arg8).users.get(0), ((Chat)v1), arg6, arg9, false);
                return;
            }

            if(arg6 != null && arg6.getParentActivity() != null) {
                try {
                    Toast.makeText(arg6.getParentActivity(), LocaleController.getString("NoUsernameFound", 2131625296), 0).show();
                }
                catch(Exception v5) {
                    FileLog.e(((Throwable)v5));
                }
            }

            return;
        }
    }

    public static void lambda$null$239(MessagesController arg1, int arg2, DialogInterface arg3, int arg4) {
        ConnectionsManager.getInstance(arg1.currentAccount).cancelRequest(arg2, true);
        try {
            arg3.dismiss();
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public static void lambda$null$24(MessagesController arg3, long arg4) {
        arg3.loadingPeerSettings.remove(arg4);
        SharedPreferences$Editor v0 = arg3.notificationsPreferences.edit();
        v0.remove("spam_" + arg4);
        v0.putInt("spam3_" + arg4, 1);
        v0.commit();
    }

    public static void lambda$null$26(MessagesController arg3, long arg4, TLObject arg6) {
        arg3.loadingPeerSettings.remove(arg4);
        if(arg6 != null) {
            SharedPreferences$Editor v0 = arg3.notificationsPreferences.edit();
            if(!((TL_peerSettings)arg6).report_spam) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("don\'t show spam button for " + arg4);
                }

                v0.putInt("spam3_" + arg4, 1);
                v0.commit();
            }
            else {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("show spam button for " + arg4);
                }

                v0.putInt("spam3_" + arg4, 2);
                v0.commit();
                NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.peerSettingsDidLoaded, new Object[]{Long.valueOf(arg4)});
            }
        }
    }

    public static void lambda$null$3(MessagesController arg6) {
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(2)});
        UserConfig.getInstance(arg6.currentAccount).saveConfig(true);
    }

    public static void lambda$null$31(MessagesController arg1, ArrayList arg2) {
        arg1.getNewDeleteTask(arg2, arg1.currentDeletingTaskChannelId);
        arg1.currentDeletingTaskTime = 0;
        arg1.currentDeletingTaskMids = null;
    }

    public static void lambda$null$33(MessagesController arg1) {
        arg1.checkDeletingTask(true);
    }

    public static void lambda$null$38(MessagesController arg2, int arg3) {
        arg2.loadFullChat(arg3, 0, true);
    }

    public static void lambda$null$39(MessagesController arg3, TL_error arg4, BaseFragment arg5, TL_channels_editBanned arg6, boolean arg7) {
        AlertsCreator.processError(arg3.currentAccount, arg4, arg5, ((TLObject)arg6), new Object[]{Boolean.valueOf((((int)arg7)) ^ 1)});
    }

    public static void lambda$null$41(MessagesController arg2, int arg3) {
        arg2.loadFullChat(arg3, 0, true);
    }

    public static void lambda$null$42(MessagesController arg3, TL_error arg4, BaseFragment arg5, TL_channels_editAdmin arg6, boolean arg7) {
        AlertsCreator.processError(arg3.currentAccount, arg4, arg5, ((TLObject)arg6), new Object[]{Boolean.valueOf((((int)arg7)) ^ 1)});
    }

    public static void lambda$null$47(MessagesController arg6) {
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(1535)});
        UserConfig.getInstance(arg6.currentAccount).saveConfig(true);
    }

    public static void lambda$null$56(MessagesController arg1, long arg2) {
        NotificationsController.getInstance(arg1.currentAccount).removeNotificationsForDialog(arg2);
    }

    public static void lambda$null$62(MessagesController arg3, TL_error arg4, TLObject arg5, Integer arg6) {
        if(arg4 == null) {
            arg3.putUsers(((TL_channels_channelParticipants)arg5).users, false);
            MessagesStorage.getInstance(arg3.currentAccount).putUsersAndChats(((TL_channels_channelParticipants)arg5).users, null, true, true);
            MessagesStorage.getInstance(arg3.currentAccount).updateChannelUsers(arg6.intValue(), ((TL_channels_channelParticipants)arg5).participants);
            arg3.loadedFullParticipants.add(arg6);
        }

        arg3.loadingFullParticipants.remove(arg6);
    }

    public static void lambda$null$67(MessagesController arg4, SparseArray arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.didUpdatedMessagesViews, new Object[]{arg5});
    }

    public static void lambda$null$71(MessagesController arg5, TL_help_termsOfServiceUpdate arg6) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.needShowAlert, new Object[]{Integer.valueOf(4), arg6.terms_of_service});
    }

    public static void lambda$null$74(MessagesController arg8, TL_help_proxyDataPromo arg9, TL_messages_peerDialogs arg10, long arg11) {
        Object v0;
        Integer v9_1;
        arg8.putUsers(arg9.users, false);
        arg8.putChats(arg9.chats, false);
        arg8.putUsers(arg10.users, false);
        arg8.putChats(arg10.chats, false);
        arg8.proxyDialog = arg10.dialogs.get(0);
        arg8.proxyDialog.id = arg11;
        if(DialogObject.isChannel(arg8.proxyDialog)) {
            arg8.channelsPts.put(-(((int)arg8.proxyDialog.id)), arg8.proxyDialog.pts);
        }

        Object v9 = arg8.dialogs_read_inbox_max.get(Long.valueOf(arg8.proxyDialog.id));
        if(v9 == null) {
            v9_1 = Integer.valueOf(0);
        }

        arg8.dialogs_read_inbox_max.put(Long.valueOf(arg8.proxyDialog.id), Integer.valueOf(Math.max(((Integer)v9).intValue(), arg8.proxyDialog.read_inbox_max_id)));
        v9 = arg8.dialogs_read_outbox_max.get(Long.valueOf(arg8.proxyDialog.id));
        if(v9 == null) {
            v9_1 = Integer.valueOf(0);
        }

        arg8.dialogs_read_outbox_max.put(Long.valueOf(arg8.proxyDialog.id), Integer.valueOf(Math.max(((Integer)v9).intValue(), arg8.proxyDialog.read_outbox_max_id)));
        arg8.dialogs_dict.put(arg11, arg8.proxyDialog);
        if(!arg10.messages.isEmpty()) {
            SparseArray v5 = new SparseArray();
            SparseArray v6 = new SparseArray();
            int v9_2;
            for(v9_2 = 0; v9_2 < arg10.users.size(); ++v9_2) {
                v0 = arg10.users.get(v9_2);
                v5.put(((User)v0).id, v0);
            }

            for(v9_2 = 0; v9_2 < arg10.chats.size(); ++v9_2) {
                v0 = arg10.chats.get(v9_2);
                v6.put(((Chat)v0).id, v0);
            }

            MessageObject v9_3 = new MessageObject(arg8.currentAccount, arg10.messages.get(0), v5, v6, false);
            arg8.dialogMessage.put(arg11, v9_3);
            if(arg8.proxyDialog.last_message_date == 0) {
                arg8.proxyDialog.last_message_date = v9_3.messageOwner.date;
            }
        }

        arg8.sortDialogs(null);
        NotificationCenter.getInstance(arg8.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[]{Boolean.valueOf(true)});
    }

    public static void lambda$null$75(MessagesController arg5) {
        if(arg5.proxyDialog != null) {
            if(arg5.proxyDialog.id < 0) {
                Chat v0 = arg5.getChat(Integer.valueOf(-(((int)arg5.proxyDialog.id))));
                if(v0 != null && !v0.left && !v0.kicked && !v0.restricted) {
                    goto label_26;
                }

                arg5.dialogs_dict.remove(arg5.proxyDialog.id);
                arg5.dialogs.remove(arg5.proxyDialog);
            }

        label_26:
            arg5.proxyDialog = null;
            arg5.sortDialogs(null);
            NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }
    }

    public static void lambda$null$76(MessagesController arg6, TL_help_proxyDataPromo arg7, long arg8, TLObject arg10, TL_error arg11) {
        if(arg6.checkingProxyInfoRequestId == 0) {
            return;
        }

        arg6.checkingProxyInfoRequestId = 0;
        TLObject v3 = arg10;
        if(v3 == null || (((TL_messages_peerDialogs)v3).dialogs.isEmpty())) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$sOCAb2LP5dQdv31ctO6j8birtUE(arg6));
        }
        else {
            MessagesStorage.getInstance(arg6.currentAccount).putUsersAndChats(arg7.users, arg7.chats, true, true);
            TL_messages_dialogs v10 = new TL_messages_dialogs();
            v10.chats = ((TL_messages_peerDialogs)v3).chats;
            v10.users = ((TL_messages_peerDialogs)v3).users;
            v10.dialogs = ((TL_messages_peerDialogs)v3).dialogs;
            v10.messages = ((TL_messages_peerDialogs)v3).messages;
            MessagesStorage.getInstance(arg6.currentAccount).putDialogs(((messages_Dialogs)v10), 2);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$5p2HUkVUCz66r4jUqqx8H4gY1-U(arg6, arg7, ((TL_messages_peerDialogs)v3), arg8));
        }

        arg6.checkingProxyInfo = false;
    }

    public static void lambda$null$77(MessagesController arg6, long arg7, TL_help_proxyDataPromo arg9) {
        long v4_1;
        InputPeer v2_1;
        Object v0_1;
        arg6.proxyDialog = arg6.dialogs_dict.get(arg7);
        int v1 = 0;
        if(arg6.proxyDialog != null) {
            arg6.checkingProxyInfo = false;
            arg6.sortDialogs(null);
            NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[]{Boolean.valueOf(true)});
        }
        else {
            SparseArray v0 = new SparseArray();
            SparseArray v2 = new SparseArray();
            int v3;
            for(v3 = 0; v3 < arg9.users.size(); ++v3) {
                Object v4 = arg9.users.get(v3);
                v0.put(((User)v4).id, v4);
            }

            while(v1 < arg9.chats.size()) {
                Object v3_1 = arg9.chats.get(v1);
                v2.put(((Chat)v3_1).id, v3_1);
                ++v1;
            }

            TL_messages_getPeerDialogs v1_1 = new TL_messages_getPeerDialogs();
            TL_inputDialogPeer v3_2 = new TL_inputDialogPeer();
            if(arg9.peer.user_id != 0) {
                v3_2.peer = new TL_inputPeerUser();
                v3_2.peer.user_id = arg9.peer.user_id;
                v0_1 = v0.get(arg9.peer.user_id);
                if(v0_1 != null) {
                    v2_1 = v3_2.peer;
                    v4_1 = ((User)v0_1).access_hash;
                    goto label_90;
                }
            }
            else {
                if(arg9.peer.chat_id != 0) {
                    v3_2.peer = new TL_inputPeerChat();
                    v3_2.peer.chat_id = arg9.peer.chat_id;
                    v0_1 = v2.get(arg9.peer.chat_id);
                    if(v0_1 != null) {
                    }
                    else {
                        goto label_91;
                    }
                }
                else {
                    v3_2.peer = new TL_inputPeerChannel();
                    v3_2.peer.channel_id = arg9.peer.channel_id;
                    v0_1 = v2.get(arg9.peer.channel_id);
                    if(v0_1 != null) {
                        goto label_88;
                    }

                    goto label_91;
                }

            label_88:
                v2_1 = v3_2.peer;
                v4_1 = ((Chat)v0_1).access_hash;
            label_90:
                v2_1.access_hash = v4_1;
            }

        label_91:
            v1_1.peers.add(v3_2);
            ConnectionsManager.getInstance(arg6.currentAccount).sendRequest(((TLObject)v1_1), new -$$Lambda$MessagesController$zUbvy1PC8LZv3dggfm853LrzbHo(arg6, arg9, arg7));
        }
    }

    public static void lambda$null$78(MessagesController arg5) {
        if(arg5.proxyDialog != null) {
            if(arg5.proxyDialog.id < 0) {
                Chat v0 = arg5.getChat(Integer.valueOf(-(((int)arg5.proxyDialog.id))));
                if(v0 != null && !v0.left && !v0.kicked && !v0.restricted) {
                    goto label_26;
                }

                arg5.dialogs_dict.remove(arg5.proxyDialog.id);
                arg5.dialogs.remove(arg5.proxyDialog);
            }

        label_26:
            arg5.proxyDialog = null;
            arg5.sortDialogs(null);
            NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }
    }

    public static void lambda$null$82(MessagesController arg1, int arg2, long arg3) {
        Object v2 = arg1.sendingTypings.get(arg2);
        if(v2 != null) {
            ((LongSparseArray)v2).remove(arg3);
        }
    }

    public static void lambda$null$84(MessagesController arg1, int arg2, long arg3) {
        Object v2 = arg1.sendingTypings.get(arg2);
        if(v2 != null) {
            ((LongSparseArray)v2).remove(arg3);
        }
    }

    public static void lambda$null$88(MessagesController arg8, String arg9, TLObject arg10, long arg11) {
        Object v9 = arg8.reloadingWebpages.remove(arg9);
        if(v9 == null) {
            return;
        }

        TL_messages_messages v1 = new TL_messages_messages();
        if(!(arg10 instanceof TL_messageMediaWebPage)) {
            int v10;
            for(v10 = 0; v10 < ((ArrayList)v9).size(); ++v10) {
                ((ArrayList)v9).get(v10).messageOwner.media.webpage = new TL_webPageEmpty();
                v1.messages.add(((ArrayList)v9).get(v10).messageOwner);
            }
        }
        else {
            if(!(((TL_messageMediaWebPage)arg10).webpage instanceof TL_webPage)) {
                if((((TL_messageMediaWebPage)arg10).webpage instanceof TL_webPageEmpty)) {
                }
                else {
                    arg8.reloadingWebpagesPending.put(((TL_messageMediaWebPage)arg10).webpage.id, v9);
                    goto label_54;
                }
            }

            int v0;
            for(v0 = 0; v0 < ((ArrayList)v9).size(); ++v0) {
                ((ArrayList)v9).get(v0).messageOwner.media.webpage = ((TL_messageMediaWebPage)arg10).webpage;
                if(v0 == 0) {
                    ImageLoader.saveMessageThumbs(((ArrayList)v9).get(v0).messageOwner);
                }

                v1.messages.add(((ArrayList)v9).get(v0).messageOwner);
            }
        }

    label_54:
        if(!v1.messages.isEmpty()) {
            MessagesStorage.getInstance(arg8.currentAccount).putMessages(((messages_Messages)v1), arg11, -2, 0, false);
            NotificationCenter.getInstance(arg8.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(arg11), v9});
        }
    }

    public static void lambda$null$90(MessagesController arg19, long arg20, int arg22, int arg23, boolean arg24, int arg25, int arg26, int arg27, int arg28, int arg29, boolean arg30, int arg31, int arg32, int arg33, int arg34) {
        int v5 = arg23 != 2 || !arg24 ? arg26 : arg25;
        arg19.loadMessages(arg20, arg22, v5, arg27, false, 0, arg28, arg23, arg29, arg30, arg31, arg25, arg32, arg33, arg24, arg34);
    }

    public static void lambda$null$91(MessagesController arg14, messages_Messages arg15, boolean arg16, boolean arg17, int arg18, int arg19, long arg20, int arg22, ArrayList arg23, int arg24, int arg25, int arg26, boolean arg27, int arg28, int arg29, int arg30, int arg31, ArrayList arg32, HashMap arg33) {
        int v13;
        int v10;
        MessagesController v0 = arg14;
        messages_Messages v1 = arg15;
        long v3 = arg20;
        arg14.putUsers(v1.users, arg16);
        arg14.putChats(v1.chats, arg16);
        int v6 = 2;
        int v7 = 2147483647;
        if(!arg17 || arg18 != v6) {
            v13 = arg19;
            v10 = 2147483647;
        }
        else {
            int v9 = 0;
            v10 = 2147483647;
            while(v9 < v1.messages.size()) {
                Object v11 = v1.messages.get(v9);
                if(!((Message)v11).out && ((Message)v11).id > arg19 && ((Message)v11).id < v10) {
                    v10 = ((Message)v11).id;
                }

                ++v9;
            }

            v13 = arg19;
        }

        if(v10 == v7) {
        }
        else {
            v13 = v10;
        }

        NotificationCenter v1_1 = NotificationCenter.getInstance(v0.currentAccount);
        v7 = NotificationCenter.messagesDidLoaded;
        Object[] v9_1 = new Object[14];
        v9_1[0] = Long.valueOf(arg20);
        v9_1[1] = Integer.valueOf(arg22);
        v9_1[v6] = arg23;
        v9_1[3] = Boolean.valueOf(arg16);
        v9_1[4] = Integer.valueOf(v13);
        v9_1[5] = Integer.valueOf(arg24);
        v9_1[6] = Integer.valueOf(arg25);
        v9_1[7] = Integer.valueOf(arg26);
        v9_1[8] = Integer.valueOf(arg18);
        v9_1[9] = Boolean.valueOf(arg27);
        v9_1[10] = Integer.valueOf(arg28);
        v9_1[11] = Integer.valueOf(arg29);
        v9_1[12] = Integer.valueOf(arg30);
        v9_1[13] = Integer.valueOf(arg31);
        v1_1.postNotificationName(v7, v9_1);
        if(!arg32.isEmpty()) {
            arg14.reloadMessages(arg32, v3);
        }

        if(!arg33.isEmpty()) {
            arg14.reloadWebPages(v3, arg33);
        }
    }

    public static void lambda$null$93(MessagesController arg2, TLObject arg3) {
        arg2.putUsers(((TL_help_recentMeUrls)arg3).users, false);
        arg2.putChats(((TL_help_recentMeUrls)arg3).chats, false);
        arg2.hintDialogs.clear();
        arg2.hintDialogs.addAll(((TL_help_recentMeUrls)arg3).urls);
        NotificationCenter.getInstance(arg2.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$null$96(MessagesController arg4, TLObject arg5, int arg6) {
        boolean v5;
        String v6;
        if(arg5 != null) {
            --arg4.loadingNotificationSettings;
            SharedPreferences$Editor v0 = arg4.notificationsPreferences.edit();
            if(arg6 == 0) {
                if((((TL_peerNotifySettings)arg5).flags & 1) != 0) {
                    v0.putBoolean("EnablePreviewGroup", ((TL_peerNotifySettings)arg5).show_previews);
                }

                if((((TL_peerNotifySettings)arg5).flags & 4) == 0) {
                    goto label_47;
                }

                v6 = "EnableGroup";
                if(((TL_peerNotifySettings)arg5).mute_until >= ConnectionsManager.getInstance(arg4.currentAccount).getCurrentTime()) {
                    goto label_27;
                }

                goto label_25;
            }
            else {
                if((((TL_peerNotifySettings)arg5).flags & 1) != 0) {
                    v0.putBoolean("EnablePreviewAll", ((TL_peerNotifySettings)arg5).show_previews);
                }

                if((((TL_peerNotifySettings)arg5).flags & 4) == 0) {
                    goto label_47;
                }

                v6 = "EnableAll";
                if(((TL_peerNotifySettings)arg5).mute_until < ConnectionsManager.getInstance(arg4.currentAccount).getCurrentTime()) {
                label_25:
                    v5 = true;
                }
                else {
                label_27:
                    v5 = false;
                }

                v0.putBoolean(v6, v5);
            }

        label_47:
            v0.commit();
            if(arg4.loadingNotificationSettings != 0) {
                return;
            }

            UserConfig.getInstance(arg4.currentAccount).notificationsSettingsLoaded = true;
            UserConfig.getInstance(arg4.currentAccount).saveConfig(false);
        }
    }

    public static void lambda$openByUserName$238(MessagesController arg8, AlertDialog[] arg9, BaseFragment arg10, int arg11, TLObject arg12, TL_error arg13) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$8ftGCrEhoqVG6HFQqaiVDFQ_BYI(arg8, arg9, arg10, arg13, arg12, arg11));
    }

    public static void lambda$openByUserName$240(MessagesController arg5, AlertDialog[] arg6, int arg7, BaseFragment arg8) {
        if(arg6[0] == null) {
            return;
        }

        arg6[0].setMessage(LocaleController.getString("Loading", 2131625103));
        arg6[0].setCanceledOnTouchOutside(false);
        arg6[0].setCancelable(false);
        arg6[0].setButton(-2, LocaleController.getString("Cancel", 2131624257), new -$$Lambda$MessagesController$sZQEn3zxVRfgWc-0ynS9ppjRf_U(arg5, arg7));
        arg8.showDialog(arg6[0]);
    }

    public static void lambda$performLogout$168(MessagesController arg0, TLObject arg1, TL_error arg2) {
        ConnectionsManager.getInstance(arg0.currentAccount).cleanup(false);
    }

    public static void lambda$pinChannelMessage$53(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg2 == null) {
            arg0.processUpdates(((Updates)arg1), false);
        }
    }

    public static void lambda$pinDialog$200(MessagesController arg1, long arg2, TLObject arg4, TL_error arg5) {
        if(arg2 != 0) {
            MessagesStorage.getInstance(arg1.currentAccount).removePendingTask(arg2);
        }
    }

    static int lambda$processChannelsUpdatesQueue$173(Updates arg0, Updates arg1) {
        return AndroidUtilities.compare(arg0.pts, arg1.pts);
    }

    public static void lambda$processChatInfo$64(MessagesController arg2, ArrayList arg3, boolean arg4, ChatFull arg5, boolean arg6, MessageObject arg7) {
        arg2.putUsers(arg3, arg4);
        if(arg5.stickerset != null) {
            DataQuery.getInstance(arg2.currentAccount).getGroupStickerSetById(arg5.stickerset);
        }

        NotificationCenter.getInstance(arg2.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{arg5, Integer.valueOf(0), Boolean.valueOf(arg6), arg7});
    }

    public static void lambda$processDialogsUpdate$117(MessagesController arg14, messages_Dialogs arg15) {
        Integer v8_2;
        int v8_1;
        Object v5_1;
        Object v8;
        Object v6;
        LongSparseArray v3 = new LongSparseArray();
        LongSparseArray v4 = new LongSparseArray();
        SparseArray v0 = new SparseArray(arg15.users.size());
        SparseArray v1 = new SparseArray(arg15.chats.size());
        LongSparseArray v11 = new LongSparseArray();
        int v5;
        for(v5 = 0; v5 < arg15.users.size(); ++v5) {
            v6 = arg15.users.get(v5);
            v0.put(((User)v6).id, v6);
        }

        for(v5 = 0; v5 < arg15.chats.size(); ++v5) {
            v6 = arg15.chats.get(v5);
            v1.put(((Chat)v6).id, v6);
        }

        int v12;
        for(v12 = 0; true; ++v12) {
            long v6_1 = 0;
            if(v12 >= arg15.messages.size()) {
                break;
            }

            v8 = arg15.messages.get(v12);
            if(arg14.proxyDialogId != v6_1 && arg14.proxyDialogId == ((Message)v8).dialog_id) {
                goto label_68;
            }
            else if(((Message)v8).to_id.channel_id != 0) {
                v5_1 = v1.get(((Message)v8).to_id.channel_id);
                if(v5_1 == null) {
                    goto label_68;
                }
                else if(((Chat)v5_1).left) {
                }
                else {
                    goto label_68;
                }
            }
            else if(((Message)v8).to_id.chat_id != 0) {
                v5_1 = v1.get(((Message)v8).to_id.chat_id);
                if(v5_1 == null) {
                    goto label_68;
                }
                else if(((Chat)v5_1).migrated_to != null) {
                }
                else {
                    goto label_68;
                }
            }
            else {
            label_68:
                MessageObject v13 = new MessageObject(arg14.currentAccount, v8, v0, v1, false);
                v4.put(v13.getDialogId(), v13);
            }
        }

        int v0_1;
        for(v0_1 = 0; v0_1 < arg15.dialogs.size(); ++v0_1) {
            v5_1 = arg15.dialogs.get(v0_1);
            if(((TL_dialog)v5_1).id == v6_1) {
                if(((TL_dialog)v5_1).peer.user_id != 0) {
                    v8_1 = ((TL_dialog)v5_1).peer.user_id;
                }
                else {
                    if(((TL_dialog)v5_1).peer.chat_id != 0) {
                        v8_1 = ((TL_dialog)v5_1).peer.chat_id;
                    }
                    else if(((TL_dialog)v5_1).peer.channel_id != 0) {
                        v8_1 = ((TL_dialog)v5_1).peer.channel_id;
                    }
                    else {
                        goto label_110;
                    }

                    v8_1 = -v8_1;
                }

                ((TL_dialog)v5_1).id = ((long)v8_1);
            }

        label_110:
            if(arg14.proxyDialogId != v6_1 && arg14.proxyDialogId == ((TL_dialog)v5_1).id) {
                goto label_136;
            }
            else if(DialogObject.isChannel(((TL_dialog)v5_1))) {
                v8 = v1.get(-(((int)((TL_dialog)v5_1).id)));
                if(v8 == null) {
                    goto label_136;
                }
                else if(((Chat)v8).left) {
                }
                else {
                    goto label_136;
                }
            }
            else if((((int)((TL_dialog)v5_1).id)) < 0) {
                v8 = v1.get(-(((int)((TL_dialog)v5_1).id)));
                if(v8 == null) {
                    goto label_136;
                }
                else if(((Chat)v8).migrated_to != null) {
                }
                else {
                    goto label_136;
                }
            }
            else {
            label_136:
                if(((TL_dialog)v5_1).last_message_date == 0) {
                    v8 = v4.get(((TL_dialog)v5_1).id);
                    if(v8 != null) {
                        ((TL_dialog)v5_1).last_message_date = ((MessageObject)v8).messageOwner.date;
                    }
                }

                v3.put(((TL_dialog)v5_1).id, v5_1);
                v11.put(((TL_dialog)v5_1).id, Integer.valueOf(((TL_dialog)v5_1).unread_count));
                v8 = arg14.dialogs_read_inbox_max.get(Long.valueOf(((TL_dialog)v5_1).id));
                if(v8 == null) {
                    v8_2 = Integer.valueOf(0);
                }

                arg14.dialogs_read_inbox_max.put(Long.valueOf(((TL_dialog)v5_1).id), Integer.valueOf(Math.max(((Integer)v8).intValue(), ((TL_dialog)v5_1).read_inbox_max_id)));
                v8 = arg14.dialogs_read_outbox_max.get(Long.valueOf(((TL_dialog)v5_1).id));
                if(v8 == null) {
                    v8_2 = Integer.valueOf(0);
                }

                arg14.dialogs_read_outbox_max.put(Long.valueOf(((TL_dialog)v5_1).id), Integer.valueOf(Math.max(((Integer)v8).intValue(), ((TL_dialog)v5_1).read_outbox_max_id)));
            }
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$6_IxPIEedPfMhN-27iZsoT7iR4A(arg14, arg15, v3, v4, v11));
    }

    public static void lambda$processDialogsUpdateRead$112(MessagesController arg9, LongSparseArray arg10, LongSparseArray arg11) {
        int v3_1;
        if(arg10 != null) {
            int v2;
            for(v2 = 0; v2 < arg10.size(); ++v2) {
                long v3 = arg10.keyAt(v2);
                Object v5 = arg9.dialogs_dict.get(v3);
                if(v5 != null) {
                    int v6 = ((TL_dialog)v5).unread_count;
                    ((TL_dialog)v5).unread_count = arg10.valueAt(v2).intValue();
                    if(v6 != 0 && ((TL_dialog)v5).unread_count == 0 && !arg9.isDialogMuted(v3)) {
                        v3_1 = arg9.unreadUnmutedDialogs - 1;
                    }
                    else if(v6 != 0) {
                        goto label_33;
                    }
                    else if(((TL_dialog)v5).unread_mark) {
                        goto label_33;
                    }
                    else if(((TL_dialog)v5).unread_count == 0) {
                        goto label_33;
                    }
                    else if(!arg9.isDialogMuted(v3)) {
                        v3_1 = arg9.unreadUnmutedDialogs + 1;
                    }
                    else {
                        goto label_33;
                    }

                    arg9.unreadUnmutedDialogs = v3_1;
                }

            label_33:
            }
        }

        if(arg11 != null) {
            for(v2 = 0; v2 < arg11.size(); ++v2) {
                Object v3_2 = arg9.dialogs_dict.get(arg11.keyAt(v2));
                if(v3_2 != null) {
                    ((TL_dialog)v3_2).unread_mentions_count = arg11.valueAt(v2).intValue();
                    if(arg9.createdDialogMainThreadIds.contains(Long.valueOf(((TL_dialog)v3_2).id))) {
                        NotificationCenter.getInstance(arg9.currentAccount).postNotificationName(NotificationCenter.updateMentionsCount, new Object[]{Long.valueOf(((TL_dialog)v3_2).id), Integer.valueOf(((TL_dialog)v3_2).unread_mentions_count)});
                    }
                }
            }
        }

        NotificationCenter.getInstance(arg9.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(256)});
        if(arg10 != null) {
            NotificationsController.getInstance(arg9.currentAccount).processDialogsUpdateRead(arg10);
        }
    }

    public static void lambda$processLoadedBlockedUsers$46(MessagesController arg1, ArrayList arg2, boolean arg3, SparseIntArray arg4) {
        if(arg2 != null) {
            arg1.putUsers(arg2, arg3);
        }

        arg1.loadingBlockedUsers = false;
        if(arg4.size() == 0 && (arg3) && !UserConfig.getInstance(arg1.currentAccount).blockedUsersLoaded) {
            arg1.getBlockedUsers(false);
            return;
        }

        if(!arg3) {
            UserConfig.getInstance(arg1.currentAccount).blockedUsersLoaded = true;
            UserConfig.getInstance(arg1.currentAccount).saveConfig(false);
        }

        arg1.blockedUsers = arg4;
        NotificationCenter.getInstance(arg1.currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoaded, new Object[0]);
    }

    public static void lambda$processLoadedChannelAdmins$12(MessagesController arg1, int arg2, ArrayList arg3, boolean arg4) {
        arg1.loadingChannelAdmins.delete(arg2);
        arg1.channelAdmins.put(arg2, arg3);
        if(arg4) {
            arg1.loadChannelAdmins(arg2, false);
        }
    }

    public static void lambda$processLoadedDeleteTask$34(MessagesController arg5, ArrayList arg6, int arg7) {
        arg5.gettingNewDeleteTask = false;
        Runnable v1 = null;
        if(arg6 != null) {
            arg5.currentDeletingTaskTime = arg7;
            arg5.currentDeletingTaskMids = arg6;
            if(arg5.currentDeleteTaskRunnable != null) {
                Utilities.stageQueue.cancelRunnable(arg5.currentDeleteTaskRunnable);
                arg5.currentDeleteTaskRunnable = v1;
            }

            if(arg5.checkDeletingTask(false)) {
                return;
            }

            arg5.currentDeleteTaskRunnable = new -$$Lambda$MessagesController$sdeaiq312X6ZJcuPxs3Rn9btHX8(arg5);
            Utilities.stageQueue.postRunnable(arg5.currentDeleteTaskRunnable, (((long)Math.abs(ConnectionsManager.getInstance(arg5.currentAccount).getCurrentTime() - arg5.currentDeletingTaskTime))) * 1000);
        }
        else {
            arg5.currentDeletingTaskTime = 0;
            arg5.currentDeletingTaskMids = ((ArrayList)v1);
        }
    }

    public static void lambda$processLoadedDialogs$108(MessagesController arg22, int arg23, messages_Dialogs arg24, boolean arg25, int arg26, int arg27, boolean arg28, boolean arg29, ArrayList arg30) {
        Integer v12_1;
        int v4_2;
        Integer v9_3;
        boolean v21;
        Object v9_2;
        long v16;
        UserConfig v1_2;
        long v9_1;
        Object v20;
        Object v4;
        Object v12;
        long v14;
        Object v11;
        MessagesController v13 = arg22;
        int v2 = arg23;
        messages_Dialogs v3 = arg24;
        int v9 = arg26;
        ArrayList v1 = null;
        if(!v13.firstGettingTask) {
            v13.getNewDeleteTask(v1, 0);
            v13.firstGettingTask = true;
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("loaded loadType " + v2 + " count " + v3.dialogs.size());
        }

        if(v2 == 1 && v3.dialogs.size() == 0) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$rlMXusyGib-ofi997sxbJTQ8Zbc(v13, v3, arg25, v9));
            return;
        }

        LongSparseArray v6 = new LongSparseArray();
        LongSparseArray v7 = new LongSparseArray();
        SparseArray v0_1 = new SparseArray();
        SparseArray v8 = new SparseArray();
        int v10;
        for(v10 = 0; v10 < v3.users.size(); ++v10) {
            v11 = v3.users.get(v10);
            v0_1.put(((User)v11).id, v11);
        }

        for(v10 = 0; v10 < v3.chats.size(); ++v10) {
            v11 = v3.chats.get(v10);
            v8.put(((Chat)v11).id, v11);
        }

        if(v2 == 1) {
            v13.nextDialogsCacheOffset = arg27 + v9;
        }

        Object v10_1 = v1;
        int v1_1 = 0;
        while(true) {
            v14 = 0;
            if(v1_1 >= v3.messages.size()) {
                break;
            }

            v12 = v3.messages.get(v1_1);
            if(v10_1 == null || ((Message)v12).date < ((Message)v10_1).date) {
                v10_1 = v12;
            }

            if(((Message)v12).to_id.channel_id != 0) {
                v4 = v8.get(((Message)v12).to_id.channel_id);
                if(v4 == null || !((Chat)v4).left) {
                    v20 = v10_1;
                }
                else {
                    v20 = v10_1;
                    if(v13.proxyDialogId == v14) {
                        goto label_127;
                    }
                    else if(v13.proxyDialogId != (((long)(-((Chat)v4).id)))) {
                        goto label_127;
                    }
                }

                if(v4 == null) {
                    goto label_116;
                }

                if(!((Chat)v4).megagroup) {
                    goto label_116;
                }

                ((Message)v12).flags |= -2147483648;
                goto label_116;
            }
            else {
                v20 = v10_1;
                if(((Message)v12).to_id.chat_id != 0) {
                    v4 = v8.get(((Message)v12).to_id.chat_id);
                    if(v4 == null) {
                        goto label_116;
                    }

                    if(((Chat)v4).migrated_to == null) {
                        goto label_116;
                    }

                    goto label_127;
                }

            label_116:
                MessageObject v4_1 = new MessageObject(v13.currentAccount, v12, v0_1, v8, false);
                v7.put(v4_1.getDialogId(), v4_1);
            }

        label_127:
            ++v1_1;
            v10_1 = v20;
        }

        if(!arg28 && !arg29 && UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetId != -1 && v2 == 0) {
            if(v10_1 == null || ((Message)v10_1).id == UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetId) {
                UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetId = 2147483647;
                goto label_260;
                while(true) {
                label_240:
                    if(v1_1 < v3.users.size()) {
                        v4 = v3.users.get(v1_1);
                        if(((User)v4).id == UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetUserId) {
                            v1_2 = UserConfig.getInstance(v13.currentAccount);
                            v9_1 = ((User)v4).access_hash;
                        }
                        else {
                            ++v1_1;
                            continue;
                        }
                    }
                    else {
                        goto label_260;
                    }

                    goto label_191;
                }

                while(true) {
                label_211:
                    if(v1_1 < v3.chats.size()) {
                        v4 = v3.chats.get(v1_1);
                        if(((Chat)v4).id == UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChatId) {
                            goto label_188;
                        }
                        else {
                            ++v1_1;
                            continue;
                        }
                    }
                    else {
                        goto label_260;
                    }

                    goto label_191;
                }

                while(true) {
                label_178:
                    if(v1_1 < v3.chats.size()) {
                        v4 = v3.chats.get(v1_1);
                        if(((Chat)v4).id != UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChannelId) {
                            ++v1_1;
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        goto label_260;
                    }

                    goto label_191;
                }

            label_188:
                v1_2 = UserConfig.getInstance(v13.currentAccount);
                v9_1 = ((Chat)v4).access_hash;
            label_191:
                v1_2.dialogsLoadOffsetAccess = v9_1;
            }
            else {
                v1_2 = UserConfig.getInstance(v13.currentAccount);
                v1_2.totalDialogsLoadCount += v3.dialogs.size();
                UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetId = ((Message)v10_1).id;
                UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetDate = ((Message)v10_1).date;
                if(((Message)v10_1).to_id.channel_id != 0) {
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChannelId = ((Message)v10_1).to_id.channel_id;
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChatId = 0;
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetUserId = 0;
                    v1_1 = 0;
                    goto label_178;
                }
                else if(((Message)v10_1).to_id.chat_id != 0) {
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChatId = ((Message)v10_1).to_id.chat_id;
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChannelId = 0;
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetUserId = 0;
                    v1_1 = 0;
                    goto label_211;
                }
                else if(((Message)v10_1).to_id.user_id != 0) {
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetUserId = ((Message)v10_1).to_id.user_id;
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChatId = 0;
                    UserConfig.getInstance(v13.currentAccount).dialogsLoadOffsetChannelId = 0;
                    v1_1 = 0;
                    goto label_240;
                }
                else {
                }
            }

        label_260:
            UserConfig.getInstance(v13.currentAccount).saveConfig(false);
        }

        ArrayList v10_2 = new ArrayList();
        v1_1 = 0;
        while(v1_1 < v3.dialogs.size()) {
            v4 = v3.dialogs.get(v1_1);
            if(((TL_dialog)v4).id == v14 && ((TL_dialog)v4).peer != null) {
                if(((TL_dialog)v4).peer.user_id != 0) {
                    v9 = ((TL_dialog)v4).peer.user_id;
                }
                else {
                    if(((TL_dialog)v4).peer.chat_id != 0) {
                        v9 = ((TL_dialog)v4).peer.chat_id;
                    }
                    else if(((TL_dialog)v4).peer.channel_id != 0) {
                        v9 = ((TL_dialog)v4).peer.channel_id;
                    }
                    else {
                        goto label_297;
                    }

                    v9 = -v9;
                }

                ((TL_dialog)v4).id = ((long)v9);
            }

        label_297:
            if(((TL_dialog)v4).id == v14) {
                v16 = v14;
            }
            else {
                if(v13.proxyDialogId != v14 && v13.proxyDialogId == ((TL_dialog)v4).id) {
                    v13.proxyDialog = ((TL_dialog)v4);
                }

                if(((TL_dialog)v4).last_message_date == 0) {
                    v9_2 = v7.get(((TL_dialog)v4).id);
                    if(v9_2 != null) {
                        ((TL_dialog)v4).last_message_date = ((MessageObject)v9_2).messageOwner.date;
                    }
                }

                if(DialogObject.isChannel(((TL_dialog)v4))) {
                    v9_2 = v8.get(-(((int)((TL_dialog)v4).id)));
                    if(v9_2 != null) {
                        boolean v11_1 = ((Chat)v9_2).megagroup;
                        if(((Chat)v9_2).left) {
                            v16 = 0;
                            if(v13.proxyDialogId != v16) {
                                v21 = v11_1;
                                if(v13.proxyDialogId != ((TL_dialog)v4).id) {
                                    goto label_400;
                                }
                            }
                            else {
                                goto label_400;
                            }
                        }
                        else {
                            v21 = v11_1;
                            v16 = 0;
                        }
                    }
                    else {
                        v16 = 0;
                        v21 = true;
                    }

                    v13.channelsPts.put(-(((int)((TL_dialog)v4).id)), ((TL_dialog)v4).pts);
                }
                else {
                    v16 = 0;
                    if((((int)((TL_dialog)v4).id)) < 0) {
                        v9_2 = v8.get(-(((int)((TL_dialog)v4).id)));
                        if(v9_2 != null && ((Chat)v9_2).migrated_to != null) {
                            goto label_400;
                        }
                    }

                    v21 = true;
                }

                v6.put(((TL_dialog)v4).id, v4);
                if((v21) && v2 == 1 && (((TL_dialog)v4).read_outbox_max_id == 0 || ((TL_dialog)v4).read_inbox_max_id == 0) && ((TL_dialog)v4).top_message != 0) {
                    v10_2.add(v4);
                }

                v9_2 = v13.dialogs_read_inbox_max.get(Long.valueOf(((TL_dialog)v4).id));
                if(v9_2 == null) {
                    v9_3 = Integer.valueOf(0);
                }

                v13.dialogs_read_inbox_max.put(Long.valueOf(((TL_dialog)v4).id), Integer.valueOf(Math.max(v9_3.intValue(), ((TL_dialog)v4).read_inbox_max_id)));
                v9_2 = v13.dialogs_read_outbox_max.get(Long.valueOf(((TL_dialog)v4).id));
                if(v9_2 == null) {
                    v9_3 = Integer.valueOf(0);
                }

                v13.dialogs_read_outbox_max.put(Long.valueOf(((TL_dialog)v4).id), Integer.valueOf(Math.max(v9_3.intValue(), ((TL_dialog)v4).read_outbox_max_id)));
            }

        label_400:
            ++v1_1;
            v14 = v16;
        }

        if(v2 != 1) {
            ImageLoader.saveMessagesThumbs(v3.messages);
            v4_2 = 0;
            goto label_408;
        }
        else {
            v1_1 = 0;
            goto label_482;
        label_408:
            while(v4_2 < v3.messages.size()) {
                v9_2 = v3.messages.get(v4_2);
                if((((Message)v9_2).action instanceof TL_messageActionChatDeleteUser)) {
                    if((v13.hideLeftGroup) && ((Message)v9_2).action.user_id == ((Message)v9_2).from_id) {
                        goto label_472;
                    }

                    v11 = v0_1.get(((Message)v9_2).action.user_id);
                    if(v11 == null) {
                        goto label_436;
                    }

                    if(!((User)v11).bot) {
                        goto label_436;
                    }

                    ((Message)v9_2).reply_markup = new TL_replyKeyboardHide();
                    ((Message)v9_2).flags |= 64;
                    goto label_436;
                }
                else {
                label_436:
                    if(!(((Message)v9_2).action instanceof TL_messageActionChatMigrateTo)) {
                        if((((Message)v9_2).action instanceof TL_messageActionChannelCreate)) {
                        }
                        else {
                            ConcurrentHashMap v11_2 = ((Message)v9_2).out ? v13.dialogs_read_outbox_max : v13.dialogs_read_inbox_max;
                            v12 = v11_2.get(Long.valueOf(((Message)v9_2).dialog_id));
                            if(v12 == null) {
                                v12_1 = Integer.valueOf(MessagesStorage.getInstance(v13.currentAccount).getDialogReadMax(((Message)v9_2).out, ((Message)v9_2).dialog_id));
                                v11_2.put(Long.valueOf(((Message)v9_2).dialog_id), v12_1);
                            }

                            boolean v1_3 = v12_1.intValue() < ((Message)v9_2).id ? true : false;
                            ((Message)v9_2).unread = v1_3;
                            goto label_472;
                        }
                    }

                    ((Message)v9_2).unread = false;
                    ((Message)v9_2).media_unread = false;
                }

            label_472:
                ++v4_2;
            }

            v1_1 = 0;
            MessagesStorage.getInstance(v13.currentAccount).putDialogs(v3, 0);
        }

    label_482:
        if(arg23 == 2) {
            Object v0_2 = v3.chats.get(v1_1);
            v13.getChannelDifference(((Chat)v0_2).id);
            v13.checkChannelInviter(((Chat)v0_2).id);
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$F9jdKdMA7w3TlMBkgZ3XGmzb9O4(arg22, arg23, arg24, arg30, arg29, v6, v7, v8, arg26, arg28, arg27, v10_2));
    }

    public static void lambda$processLoadedMessages$92(MessagesController arg24, messages_Messages arg25, long arg26, boolean arg28, int arg29, int arg30, boolean arg31, int arg32, int arg33, int arg34, int arg35, int arg36, boolean arg37, int arg38, int arg39, int arg40, int arg41, boolean arg42) {
        Object v11;
        MessagesController v12;
        int v3;
        Integer v2_2;
        Object v0_2;
        MessagesController v5;
        boolean v6_1;
        boolean v1_1;
        Object v2;
        boolean v0;
        MessagesController v15 = arg24;
        messages_Messages v7 = arg25;
        long v8 = arg26;
        if((v7 instanceof TL_messages_channelMessages)) {
            int v6 = -(((int)v8));
            if(v15.channelsPts.get(v6) != 0 || MessagesStorage.getInstance(v15.currentAccount).getChannelPtsSync(v6) != 0) {
                v0 = false;
            }
            else {
                v15.channelsPts.put(v6, v7.pts);
                if(v15.needShortPollChannels.indexOfKey(v6) < 0 || v15.shortPollChannels.indexOfKey(v6) >= 0) {
                    v15.getChannelDifference(v6);
                }
                else {
                    arg24.getChannelDifference(v6, 2, 0, null);
                }

                v0 = true;
            }

            int v1;
            for(v1 = 0; v1 < v7.chats.size(); ++v1) {
                v2 = v7.chats.get(v1);
                if(((Chat)v2).id == v6) {
                    v1_1 = ((Chat)v2).megagroup;
                    v6_1 = v0;
                    goto label_54;
                }
            }

            v6_1 = v0;
            v1_1 = false;
        }
        else {
            v1_1 = false;
            v6_1 = false;
        }

    label_54:
        int v0_1 = ((int)v8);
        int v2_1 = ((int)(v8 >> 32));
        if(!arg28) {
            ImageLoader.saveMessagesThumbs(v7.messages);
        }

        if(v2_1 != 1 && v0_1 != 0 && (arg28) && v7.messages.size() == 0) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$mfjH9uBuehFUc-32WIjFToUxhYA(arg24, arg26, arg29, arg30, arg31, arg32, arg33, arg34, arg35, arg36, arg37, arg38, arg39, arg40, arg41));
            return;
        }

        SparseArray v13 = new SparseArray();
        SparseArray v14 = new SparseArray();
        for(v0_1 = 0; v0_1 < v7.users.size(); ++v0_1) {
            v2 = v7.users.get(v0_1);
            v13.put(((User)v2).id, v2);
        }

        for(v0_1 = 0; v0_1 < v7.chats.size(); ++v0_1) {
            v2 = v7.chats.get(v0_1);
            v14.put(((Chat)v2).id, v2);
        }

        int v15_1 = v7.messages.size();
        if(!arg28) {
            v5 = arg24;
            v0_2 = v5.dialogs_read_inbox_max.get(Long.valueOf(arg26));
            if(v0_2 == null) {
                Integer v0_3 = Integer.valueOf(MessagesStorage.getInstance(v5.currentAccount).getDialogReadMax(false, v8));
                v5.dialogs_read_inbox_max.put(Long.valueOf(arg26), v0_3);
            }

            v2 = v5.dialogs_read_outbox_max.get(Long.valueOf(arg26));
            if(v2 == null) {
                v2_2 = Integer.valueOf(MessagesStorage.getInstance(v5.currentAccount).getDialogReadMax(true, v8));
                v5.dialogs_read_outbox_max.put(Long.valueOf(arg26), v2_2);
            }

            v3 = 0;
            goto label_139;
        }
        else {
            v12 = arg24;
            goto label_206;
        label_139:
            while(v3 < v15_1) {
                Object v4 = v7.messages.get(v3);
                if(v1_1) {
                    ((Message)v4).flags |= -2147483648;
                }

                if((((Message)v4).action instanceof TL_messageActionChatDeleteUser)) {
                    if((v5.hideLeftGroup) && ((Message)v4).action.user_id == ((Message)v4).from_id) {
                        goto label_192;
                    }

                    v11 = v13.get(((Message)v4).action.user_id);
                    if(v11 == null) {
                        goto label_169;
                    }

                    if(!((User)v11).bot) {
                        goto label_169;
                    }

                    ((Message)v4).reply_markup = new TL_replyKeyboardHide();
                    ((Message)v4).flags |= 64;
                    goto label_169;
                }
                else {
                label_169:
                    if(!(((Message)v4).action instanceof TL_messageActionChatMigrateTo)) {
                        if((((Message)v4).action instanceof TL_messageActionChannelCreate)) {
                        }
                        else {
                            v11 = ((Message)v4).out ? v2_2 : v0_2;
                            boolean v11_1 = ((Integer)v11).intValue() < ((Message)v4).id ? true : false;
                            ((Message)v4).unread = v11_1;
                            goto label_192;
                        }
                    }

                    ((Message)v4).unread = false;
                    ((Message)v4).media_unread = false;
                }

            label_192:
                ++v3;
            }

            v12 = v5;
            MessagesStorage.getInstance(v5.currentAccount).putMessages(arg25, arg26, arg30, arg33, v6_1);
        }

    label_206:
        ArrayList v11_2 = new ArrayList();
        ArrayList v6_2 = new ArrayList();
        HashMap v5_1 = new HashMap();
        for(v0_1 = 0; v0_1 < v15_1; ++v0_1) {
            Object v1_2 = v7.messages.get(v0_1);
            ((Message)v1_2).dialog_id = v8;
            MessageObject v2_3 = new MessageObject(v12.currentAccount, v1_2, v13, v14, true);
            v11_2.add(v2_3);
            if(arg28) {
                if((((Message)v1_2).media instanceof TL_messageMediaUnsupported)) {
                    if(((Message)v1_2).media.bytes == null) {
                        goto label_288;
                    }
                    else if(((Message)v1_2).media.bytes.length != 0) {
                        if(((Message)v1_2).media.bytes.length != 1) {
                            goto label_288;
                        }
                        else if(((Message)v1_2).media.bytes[0] < 85) {
                        }
                        else {
                            goto label_288;
                        }
                    }
                }
                else if((((Message)v1_2).media instanceof TL_messageMediaWebPage)) {
                    if(((((Message)v1_2).media.webpage instanceof TL_webPagePending)) && ((Message)v1_2).media.webpage.date <= ConnectionsManager.getInstance(v12.currentAccount).getCurrentTime()) {
                        goto label_252;
                    }

                    goto label_272;
                }
                else {
                    goto label_288;
                }

            label_252:
                v6_2.add(Integer.valueOf(((Message)v1_2).id));
                goto label_288;
            label_272:
                if(!(((Message)v1_2).media.webpage instanceof TL_webPageUrlPending)) {
                    goto label_288;
                }

                Object v3_1 = v5_1.get(((Message)v1_2).media.webpage.url);
                if(v3_1 == null) {
                    ArrayList v3_2 = new ArrayList();
                    v5_1.put(((Message)v1_2).media.webpage.url, v3_2);
                }

                ((ArrayList)v3_1).add(v2_3);
            }

        label_288:
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$pXjnpieOFIGinmIIMjGYDYEYlAg(arg24, arg25, arg28, arg31, arg30, arg32, arg26, arg29, v11_2, arg36, arg39, arg40, arg42, arg35, arg38, arg33, arg41, v6_2, v5_1));
    }

    public static void lambda$processLoadedUserPhotos$50(MessagesController arg4, photos_Photos arg5, boolean arg6, int arg7, int arg8, int arg9) {
        arg4.putUsers(arg5.users, arg6);
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.dialogPhotosLoaded, new Object[]{Integer.valueOf(arg7), Integer.valueOf(arg8), Boolean.valueOf(arg6), Integer.valueOf(arg9), arg5.photos});
    }

    public static void lambda$processUpdateArray$219(MessagesController arg1, ArrayList arg2, ArrayList arg3) {
        arg1.putUsers(arg2, false);
        arg1.putChats(arg3, false);
    }

    public static void lambda$processUpdateArray$220(MessagesController arg1, ArrayList arg2, ArrayList arg3) {
        arg1.putUsers(arg2, false);
        arg1.putChats(arg3, false);
    }

    public static void lambda$processUpdateArray$222(MessagesController arg1, TL_updateUserBlocked arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$7lWu9XfSKleLMebCWL3nzsG6BXM(arg1, arg2));
    }

    public static void lambda$processUpdateArray$223(MessagesController arg6, TL_updateServiceNotification arg7) {
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.needShowAlert, new Object[]{Integer.valueOf(2), arg7.message, arg7.type});
    }

    public static void lambda$processUpdateArray$225(MessagesController arg1, ArrayList arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$MpcKtgqIuHrS9gFic4NLXa9OUnw(arg1, arg2));
    }

    public static void lambda$processUpdateArray$229(MessagesController arg31, int arg32, ArrayList arg33, LongSparseArray arg34, LongSparseArray arg35, LongSparseArray arg36, boolean arg37, ArrayList arg38, ArrayList arg39, SparseArray arg40) {
        int v30;
        NotificationCenter v0_8;
        Object v6_2;
        int v0_6;
        String v4_6;
        SharedPreferences$Editor v0_4;
        boolean v0_3;
        String v5_2;
        Object v7_2;
        long v5_1;
        SharedPreferences$Editor v2_4;
        long v4_3;
        Peer v4_2;
        Object v4_1;
        ArrayList v6_1;
        ArrayList v14;
        int v29;
        ArrayList v28;
        int v1_2;
        long v1_1;
        ArrayList v23;
        ArrayList v15_2;
        TL_user v2_2;
        int v15_1;
        UserStatus v7_1;
        User v2_1;
        ArrayList v0_1;
        ContactsController v15;
        int v20;
        int v5;
        int v4;
        int v6;
        ArrayList v1;
        MessagesController v8 = arg31;
        ArrayList v9 = arg33;
        LongSparseArray v10 = arg34;
        LongSparseArray v11 = arg35;
        LongSparseArray v12 = arg36;
        int v7 = 1;
        int v2 = 0;
        if(v9 != null) {
            ArrayList v3 = new ArrayList();
            v1 = new ArrayList();
            v6 = arg33.size();
            v4 = arg32;
            v5 = 0;
            SharedPreferences$Editor v19 = null;
            v20 = 0;
            while(v5 < v6) {
                Object v0 = v9.get(v5);
                if((v0 instanceof TL_updatePrivacy)) {
                    if((((TL_updatePrivacy)v0).key instanceof TL_privacyKeyStatusTimestamp)) {
                        v15 = ContactsController.getInstance(v8.currentAccount);
                        v0_1 = ((TL_updatePrivacy)v0).rules;
                    }
                    else if((((TL_updatePrivacy)v0).key instanceof TL_privacyKeyChatInvite)) {
                        ContactsController.getInstance(v8.currentAccount).setPrivacyRules(((TL_updatePrivacy)v0).rules, v7);
                        goto label_46;
                    }
                    else if((((TL_updatePrivacy)v0).key instanceof TL_privacyKeyPhoneCall)) {
                        v15 = ContactsController.getInstance(v8.currentAccount);
                        v0_1 = ((TL_updatePrivacy)v0).rules;
                        v2 = 2;
                    }
                    else {
                        goto label_46;
                    }

                    v15.setPrivacyRules(v0_1, v2);
                }
                else {
                    if((v0 instanceof TL_updateUserStatus)) {
                        v2_1 = v8.getUser(Integer.valueOf(((TL_updateUserStatus)v0).user_id));
                        if((((TL_updateUserStatus)v0).status instanceof TL_userStatusRecently)) {
                            ((TL_updateUserStatus)v0).status.expires = -100;
                        }
                        else {
                            if((((TL_updateUserStatus)v0).status instanceof TL_userStatusLastWeek)) {
                                v7_1 = ((TL_updateUserStatus)v0).status;
                                v15_1 = -101;
                            }
                            else if((((TL_updateUserStatus)v0).status instanceof TL_userStatusLastMonth)) {
                                v7_1 = ((TL_updateUserStatus)v0).status;
                                v15_1 = -102;
                            }
                            else {
                                goto label_77;
                            }

                            v7_1.expires = v15_1;
                        }

                    label_77:
                        if(v2_1 != null) {
                            v2_1.id = ((TL_updateUserStatus)v0).user_id;
                            v2_1.status = ((TL_updateUserStatus)v0).status;
                        }

                        v2_2 = new TL_user();
                        ((User)v2_2).id = ((TL_updateUserStatus)v0).user_id;
                        ((User)v2_2).status = ((TL_updateUserStatus)v0).status;
                        v1.add(v2_2);
                        if(((TL_updateUserStatus)v0).user_id != UserConfig.getInstance(v8.currentAccount).getClientUserId()) {
                            goto label_46;
                        }

                        NotificationsController.getInstance(v8.currentAccount).setLastOnlineFromOtherDevice(((TL_updateUserStatus)v0).status.expires);
                        goto label_46;
                    }

                    if(!(v0 instanceof TL_updateUserName)) {
                        goto label_138;
                    }

                    v2_1 = v8.getUser(Integer.valueOf(((TL_updateUserName)v0).user_id));
                    if(v2_1 != null) {
                        if(!UserObject.isContact(v2_1)) {
                            v2_1.first_name = ((TL_updateUserName)v0).first_name;
                            v2_1.last_name = ((TL_updateUserName)v0).last_name;
                        }

                        if(!TextUtils.isEmpty(v2_1.username)) {
                            v8.objectsByUsernames.remove(v2_1.username);
                        }

                        if(TextUtils.isEmpty(((TL_updateUserName)v0).username)) {
                            v8.objectsByUsernames.put(((TL_updateUserName)v0).username, v2_1);
                        }

                        v2_1.username = ((TL_updateUserName)v0).username;
                    }

                    v2_2 = new TL_user();
                    ((User)v2_2).id = ((TL_updateUserName)v0).user_id;
                    ((User)v2_2).first_name = ((TL_updateUserName)v0).first_name;
                    ((User)v2_2).last_name = ((TL_updateUserName)v0).last_name;
                    ((User)v2_2).username = ((TL_updateUserName)v0).username;
                    v3.add(v2_2);
                }

            label_46:
                v15_2 = v1;
                v2 = v4;
                int v17 = v5;
                int v16 = v6;
                goto label_806;
            label_138:
                if((v0 instanceof TL_updateDialogPinned)) {
                    if((((TL_updateDialogPinned)v0).peer instanceof TL_dialogPeer)) {
                        Peer v2_3 = ((TL_updateDialogPinned)v0).peer.peer;
                        if((v2_3 instanceof TL_peerUser)) {
                            v23 = v1;
                            v1_1 = ((long)v2_3.user_id);
                        }
                        else {
                            v23 = v1;
                            v1_2 = (v2_3 instanceof TL_peerChat) ? v2_3.chat_id : v2_3.channel_id;
                            v1_1 = ((long)(-v1_2));
                        }
                    }
                    else {
                        v23 = v1;
                        v1_1 = 0;
                    }

                    long v26 = v1_1;
                    v15_2 = v23;
                    v28 = v3;
                    v29 = v4;
                    v17 = v5;
                    v16 = v6;
                    v14 = null;
                    if(arg31.pinDialog(v26, ((TL_updateDialogPinned)v0).pinned, null, -1)) {
                        goto label_192;
                    }

                    UserConfig.getInstance(v8.currentAccount).pinnedDialogsLoaded = false;
                    UserConfig.getInstance(v8.currentAccount).saveConfig(false);
                    v8.loadPinnedDialogs(v26, v14);
                    goto label_192;
                }
                else {
                    v15_2 = v1;
                    v28 = v3;
                    v29 = v4;
                    v17 = v5;
                    v16 = v6;
                    v14 = null;
                    if((v0 instanceof TL_updatePinnedDialogs)) {
                        UserConfig.getInstance(v8.currentAccount).pinnedDialogsLoaded = false;
                        UserConfig.getInstance(v8.currentAccount).saveConfig(false);
                        if((((TL_updatePinnedDialogs)v0).flags & 1) != 0) {
                            v6_1 = new ArrayList();
                            v0_1 = ((TL_updatePinnedDialogs)v0).order;
                            v2 = v0_1.size();
                            int v3_1;
                            for(v3_1 = 0; v3_1 < v2; ++v3_1) {
                                v4_1 = v0_1.get(v3_1);
                                if((v4_1 instanceof TL_dialogPeer)) {
                                    v4_2 = ((TL_dialogPeer)v4_1).peer;
                                    if(v4_2.user_id != 0) {
                                        v4 = v4_2.user_id;
                                    }
                                    else {
                                        v4 = v4_2.chat_id != 0 ? v4_2.chat_id : v4_2.channel_id;
                                        v4 = -v4;
                                    }

                                    v4_3 = ((long)v4);
                                }
                                else {
                                    v4_3 = 0;
                                }

                                v6_1.add(Long.valueOf(v4_3));
                            }
                        }
                        else {
                            v6_1 = v14;
                        }

                        v8.loadPinnedDialogs(0, v6_1);
                    label_192:
                        v3 = v28;
                        goto label_193;
                    }

                    if((v0 instanceof TL_updateUserPhoto)) {
                        v2_1 = v8.getUser(Integer.valueOf(((TL_updateUserPhoto)v0).user_id));
                        if(v2_1 != null) {
                            v2_1.photo = ((TL_updateUserPhoto)v0).photo;
                        }

                        v2_2 = new TL_user();
                        ((User)v2_2).id = ((TL_updateUserPhoto)v0).user_id;
                        ((User)v2_2).photo = ((TL_updateUserPhoto)v0).photo;
                        v3 = v28;
                    }
                    else {
                        v3 = v28;
                        if(!(v0 instanceof TL_updateUserPhone)) {
                            goto label_282;
                        }

                        v2_1 = v8.getUser(Integer.valueOf(((TL_updateUserPhone)v0).user_id));
                        if(v2_1 != null) {
                            v2_1.phone = ((TL_updateUserPhone)v0).phone;
                            Utilities.phoneBookQueue.postRunnable(new -$$Lambda$MessagesController$zmzVpECrd_vtjr1Rs8_HJeKt_uk(v8, v2_1));
                        }

                        v2_2 = new TL_user();
                        ((User)v2_2).id = ((TL_updateUserPhone)v0).user_id;
                        ((User)v2_2).phone = ((TL_updateUserPhone)v0).phone;
                    }

                    v3.add(v2_2);
                }

            label_193:
                v2 = v29;
                goto label_806;
            label_282:
                if((v0 instanceof TL_updateNotifySettings)) {
                    if((((TL_updateNotifySettings)v0).notify_settings instanceof TL_peerNotifySettings)) {
                        if(v19 == null) {
                            v19 = v8.notificationsPreferences.edit();
                        }

                        v2_4 = v19;
                        v4 = ConnectionsManager.getInstance(v8.currentAccount).getCurrentTime();
                        if((((TL_updateNotifySettings)v0).peer instanceof TL_notifyPeer)) {
                            if(((TL_updateNotifySettings)v0).peer.peer.user_id != 0) {
                                v5 = ((TL_updateNotifySettings)v0).peer.peer.user_id;
                            }
                            else {
                                v5 = ((TL_updateNotifySettings)v0).peer.peer.chat_id != 0 ? ((TL_updateNotifySettings)v0).peer.peer.chat_id : ((TL_updateNotifySettings)v0).peer.peer.channel_id;
                                v5 = -v5;
                            }

                            v5_1 = ((long)v5);
                            v7_2 = v8.dialogs_dict.get(v5_1);
                            if(v7_2 != null) {
                                ((TL_dialog)v7_2).notify_settings = ((TL_updateNotifySettings)v0).notify_settings;
                            }

                            if((((TL_updateNotifySettings)v0).notify_settings.flags & 2) != 0) {
                                v2_4.putBoolean("silent_" + v5_1, ((TL_updateNotifySettings)v0).notify_settings.silent);
                            }
                            else {
                                v2_4.remove("silent_" + v5_1);
                            }

                            if((((TL_updateNotifySettings)v0).notify_settings.flags & 4) == 0) {
                                if(v7_2 != null) {
                                    ((TL_updateNotifySettings)v0).notify_settings.mute_until = 0;
                                }

                                v2_4.remove("notify2_" + v5_1);
                            }
                            else if(((TL_updateNotifySettings)v0).notify_settings.mute_until > v4) {
                                if(((TL_updateNotifySettings)v0).notify_settings.mute_until > v4 + 31536000) {
                                    v2_4.putInt("notify2_" + v5_1, 2);
                                    if(v7_2 != null) {
                                        ((TL_updateNotifySettings)v0).notify_settings.mute_until = 2147483647;
                                    }

                                    v4 = 0;
                                }
                                else {
                                    v4 = ((TL_updateNotifySettings)v0).notify_settings.mute_until;
                                    v2_4.putInt("notify2_" + v5_1, 3);
                                    v2_4.putInt("notifyuntil_" + v5_1, ((TL_updateNotifySettings)v0).notify_settings.mute_until);
                                    if(v7_2 == null) {
                                        goto label_394;
                                    }

                                    ((TL_updateNotifySettings)v0).notify_settings.mute_until = v4;
                                }

                            label_394:
                                MessagesStorage.getInstance(v8.currentAccount).setDialogFlags(v5_1, (((long)v4)) << 32 | 1);
                                NotificationsController.getInstance(v8.currentAccount).removeNotificationsForDialog(v5_1);
                                goto label_483;
                            }
                            else {
                                if(v7_2 != null) {
                                    ((TL_updateNotifySettings)v0).notify_settings.mute_until = 0;
                                }

                                v2_4.putInt("notify2_" + v5_1, 0);
                            }

                            MessagesStorage.getInstance(v8.currentAccount).setDialogFlags(v5_1, 0);
                            goto label_483;
                        }

                        if((((TL_updateNotifySettings)v0).peer instanceof TL_notifyChats)) {
                            if((((TL_updateNotifySettings)v0).notify_settings.flags & 1) != 0) {
                                v2_4.putBoolean("EnablePreviewGroup", ((TL_updateNotifySettings)v0).notify_settings.show_previews);
                            }

                            if((((TL_updateNotifySettings)v0).notify_settings.flags & 4) == 0) {
                                goto label_483;
                            }

                            v5_2 = "EnableGroup";
                            if(((TL_updateNotifySettings)v0).notify_settings.mute_until >= v4) {
                                goto label_456;
                            }

                            goto label_454;
                        }
                        else {
                            if(!(((TL_updateNotifySettings)v0).peer instanceof TL_notifyUsers)) {
                                goto label_483;
                            }

                            if((((TL_updateNotifySettings)v0).notify_settings.flags & 1) != 0) {
                                v2_4.putBoolean("EnablePreviewAll", ((TL_updateNotifySettings)v0).notify_settings.show_previews);
                            }

                            if((((TL_updateNotifySettings)v0).notify_settings.flags & 4) == 0) {
                                goto label_483;
                            }

                            v5_2 = "EnableAll";
                            if(((TL_updateNotifySettings)v0).notify_settings.mute_until < v4) {
                            label_454:
                                v0_3 = true;
                                goto label_457;
                            }

                        label_456:
                            v0_3 = false;
                        }

                    label_457:
                        v2_4.putBoolean(v5_2, v0_3);
                    }
                    else {
                        v2_4 = v19;
                    }

                label_483:
                    v19 = v2_4;
                    v4 = v29;
                    goto label_807;
                }

                if((v0 instanceof TL_updateChannel)) {
                    Object v2_5 = v8.dialogs_dict.get(-(((long)((TL_updateChannel)v0).channel_id)));
                    Chat v4_5 = v8.getChat(Integer.valueOf(((TL_updateChannel)v0).channel_id));
                    if(v4_5 != null) {
                        if(v2_5 == null && ((v4_5 instanceof TL_channel)) && !v4_5.left) {
                            Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$4qwUaMzowz7Z0hSfxT_SBwdb_-Y(v8, ((TL_updateChannel)v0)));
                            goto label_518;
                        }

                        if(!v4_5.left) {
                            goto label_518;
                        }

                        if(v2_5 == null) {
                            goto label_518;
                        }

                        if(v8.proxyDialog != null && v8.proxyDialog.id == ((TL_dialog)v2_5).id) {
                            goto label_518;
                        }

                        v8.deleteDialog(((TL_dialog)v2_5).id, 0);
                    }

                label_518:
                    v4 = v29 | 8192;
                    v8.loadFullChat(((TL_updateChannel)v0).channel_id, 0, true);
                    goto label_807;
                }

                v2 = v29;
                if((v0 instanceof TL_updateChatAdmins)) {
                    v4 = v2 | 16384;
                    goto label_807;
                }

                if((v0 instanceof TL_updateStickerSets)) {
                    DataQuery.getInstance(v8.currentAccount);
                    DataQuery.loadStickers(0, false, true);
                    goto label_806;
                }

                if((v0 instanceof TL_updateStickerSetsOrder)) {
                    DataQuery.getInstance(v8.currentAccount).reorderStickers(((TL_updateStickerSetsOrder)v0).masks, ((TL_updateStickerSetsOrder)v0).order);
                    goto label_806;
                }

                if((v0 instanceof TL_updateFavedStickers)) {
                    DataQuery.getInstance(v8.currentAccount).loadRecents(2, false, false, true);
                    goto label_806;
                }

                if((v0 instanceof TL_updateContactsReset)) {
                    ContactsController.getInstance(v8.currentAccount).forceImportContacts();
                    goto label_806;
                }

                if((v0 instanceof TL_updateNewStickerSet)) {
                    DataQuery.getInstance(v8.currentAccount).addNewStickerSet(((TL_updateNewStickerSet)v0).stickerset);
                    goto label_806;
                }

                if((v0 instanceof TL_updateSavedGifs)) {
                    v0_4 = v8.emojiPreferences.edit();
                    v4_6 = "lastGifLoadTime";
                    v5_1 = 0;
                }
                else {
                    v5_1 = 0;
                    if((v0 instanceof TL_updateRecentStickers)) {
                        v0_4 = v8.emojiPreferences.edit();
                        v4_6 = "lastStickersLoadTime";
                    }
                    else {
                        goto label_581;
                    }
                }

                v0_4.putLong(v4_6, v5_1).commit();
                goto label_806;
            label_581:
                if((v0 instanceof TL_updateDraftMessage)) {
                    v4_2 = ((TL_updateDraftMessage)v0).peer;
                    if(v4_2.user_id != 0) {
                        v4 = v4_2.user_id;
                    }
                    else {
                        v4 = v4_2.channel_id != 0 ? v4_2.channel_id : v4_2.chat_id;
                        v4 = -v4;
                    }

                    long v22 = ((long)v4);
                    DataQuery.getInstance(v8.currentAccount).saveDraft(v22, ((TL_updateDraftMessage)v0).draft, null, true);
                    v4 = v2;
                    v20 = 1;
                    goto label_807;
                }

                if((v0 instanceof TL_updateReadFeaturedStickers)) {
                    DataQuery.getInstance(v8.currentAccount).markFaturedStickersAsRead(false);
                    goto label_806;
                }

                if((v0 instanceof TL_updatePhoneCall)) {
                    PhoneCall v0_5 = ((TL_updatePhoneCall)v0).phone_call;
                    VoIPService v4_7 = VoIPService.getSharedInstance();
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("Received call in update: " + v0_5);
                        FileLog.d("call id " + v0_5.id);
                    }

                    if((v0_5 instanceof TL_phoneCallRequested)) {
                        if(v0_5.date + v8.callRingTimeout / 1000 < ConnectionsManager.getInstance(v8.currentAccount).getCurrentTime()) {
                            if(!BuildVars.LOGS_ENABLED) {
                                goto label_806;
                            }

                            FileLog.d("ignoring too old call");
                            goto label_806;
                        }

                        v7_2 = ApplicationLoader.applicationContext.getSystemService("phone");
                        if(v4_7 == null && VoIPService.callIShouldHavePutIntoIntent == null) {
                            if(((TelephonyManager)v7_2).getCallState() != 0) {
                            }
                            else {
                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d("Starting service for call " + v0_5.id);
                                }

                                VoIPService.callIShouldHavePutIntoIntent = v0_5;
                                Intent v4_8 = new Intent(ApplicationLoader.applicationContext, VoIPService.class);
                                v4_8.putExtra("is_outgoing", false);
                                String v7_4 = "user_id";
                                v0_6 = v0_5.participant_id == UserConfig.getInstance(v8.currentAccount).getClientUserId() ? v0_5.admin_id : v0_5.participant_id;
                                v4_8.putExtra(v7_4, v0_6);
                                v4_8.putExtra("account", v8.currentAccount);
                                try {
                                    if(Build$VERSION.SDK_INT >= 26) {
                                        ApplicationLoader.applicationContext.startForegroundService(v4_8);
                                        goto label_806;
                                    }

                                    ApplicationLoader.applicationContext.startService(v4_8);
                                }
                                catch(Throwable v0_7) {
                                    FileLog.e(v0_7);
                                }

                                goto label_806;
                            }
                        }

                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("Auto-declining call " + v0_5.id + " because there\'s already active one");
                        }

                        TL_phone_discardCall v4_9 = new TL_phone_discardCall();
                        v4_9.peer = new TL_inputPhoneCall();
                        v4_9.peer.access_hash = v0_5.access_hash;
                        v4_9.peer.id = v0_5.id;
                        v4_9.reason = new TL_phoneCallDiscardReasonBusy();
                        ConnectionsManager.getInstance(v8.currentAccount).sendRequest(((TLObject)v4_9), new -$$Lambda$MessagesController$hGZIJRdntvMTvt2a8-YCFu3-5c8(v8));
                        goto label_806;
                    }

                    if(v4_7 != null && v0_5 != null) {
                        v4_7.onCallUpdated(v0_5);
                        goto label_806;
                    }

                    if(VoIPService.callIShouldHavePutIntoIntent == null) {
                        goto label_806;
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("Updated the call while the service is starting");
                    }

                    if(v0_5.id != VoIPService.callIShouldHavePutIntoIntent.id) {
                        goto label_806;
                    }

                    VoIPService.callIShouldHavePutIntoIntent = v0_5;
                    goto label_806;
                }

                if((v0 instanceof TL_updateDialogUnreadMark)) {
                    if((((TL_updateDialogUnreadMark)v0).peer instanceof TL_dialogPeer)) {
                        DialogPeer v4_10 = ((TL_updateDialogUnreadMark)v0).peer;
                        if(((TL_dialogPeer)v4_10).peer.user_id != 0) {
                            v4 = ((TL_dialogPeer)v4_10).peer.user_id;
                        }
                        else {
                            v4 = ((TL_dialogPeer)v4_10).peer.chat_id != 0 ? ((TL_dialogPeer)v4_10).peer.chat_id : ((TL_dialogPeer)v4_10).peer.channel_id;
                            v4 = -v4;
                        }

                        v4_3 = ((long)v4);
                    }
                    else {
                        v4_3 = 0;
                    }

                    MessagesStorage.getInstance(v8.currentAccount).setDialogUnread(v4_3, ((TL_updateDialogUnreadMark)v0).unread);
                    v6_2 = v8.dialogs_dict.get(v4_3);
                    if(v6_2 == null) {
                        goto label_806;
                    }

                    if(((TL_dialog)v6_2).unread_mark == ((TL_updateDialogUnreadMark)v0).unread) {
                        goto label_806;
                    }

                    ((TL_dialog)v6_2).unread_mark = ((TL_updateDialogUnreadMark)v0).unread;
                    if(((TL_dialog)v6_2).unread_count == 0 && !v8.isDialogMuted(v4_3)) {
                        v0_6 = ((TL_dialog)v6_2).unread_mark ? v8.unreadUnmutedDialogs + 1 : v8.unreadUnmutedDialogs - 1;
                        v8.unreadUnmutedDialogs = v0_6;
                    }

                    v4 = v2 | 256;
                }
                else {
                label_806:
                    v4 = v2;
                }

            label_807:
                v5 = v17 + 1;
                v1 = v15_2;
                v6 = v16;
                v2 = 0;
                v7 = 1;
            }

            v15_2 = v1;
            v2 = v4;
            v1_2 = 0;
            if(v19 != null) {
                v19.commit();
                NotificationCenter.getInstance(v8.currentAccount).postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
            }

            v4 = 1;
            MessagesStorage.getInstance(v8.currentAccount).updateUsers(v15_2, true, true, true);
            MessagesStorage.getInstance(v8.currentAccount).updateUsers(v3, false, true, true);
        }
        else {
            v1_2 = 0;
            v4 = 1;
            v2 = arg32;
            v20 = 0;
        }

        if(v10 != null) {
            v0_8 = NotificationCenter.getInstance(v8.currentAccount);
            v3_1 = NotificationCenter.didReceivedWebpagesInUpdates;
            Object[] v5_3 = new Object[v4];
            v5_3[v1_2] = v10;
            v0_8.postNotificationName(v3_1, v5_3);
            v0_6 = arg34.size();
            v3_1 = 0;
            while(v3_1 < v0_6) {
                v4_3 = v10.keyAt(v3_1);
                v6_2 = v8.reloadingWebpagesPending.get(v4_3);
                v8.reloadingWebpagesPending.remove(v4_3);
                if(v6_2 != null) {
                    v4_1 = v10.valueAt(v3_1);
                    v14 = new ArrayList();
                    if(((v4_1 instanceof TL_webPage)) || ((v4_1 instanceof TL_webPageEmpty))) {
                        v30 = v2;
                        v1_2 = ((ArrayList)v6_2).size();
                        v2 = 0;
                        long v15_3 = 0;
                        while(v2 < v1_2) {
                            ((ArrayList)v6_2).get(v2).messageOwner.media.webpage = ((WebPage)v4_1);
                            if(v2 == 0) {
                                v15_3 = ((ArrayList)v6_2).get(v2).getDialogId();
                                ImageLoader.saveMessageThumbs(((ArrayList)v6_2).get(v2).messageOwner);
                            }

                            v14.add(((ArrayList)v6_2).get(v2).messageOwner);
                            ++v2;
                        }

                        v1_1 = v15_3;
                    }
                    else {
                        v30 = v2;
                        v8.reloadingWebpagesPending.put(((WebPage)v4_1).id, v6_2);
                        v1_1 = 0;
                    }

                    if(v14.isEmpty()) {
                        goto label_911;
                    }

                    MessagesStorage.getInstance(v8.currentAccount).putMessages(v14, true, true, false, DownloadController.getInstance(v8.currentAccount).getAutodownloadMask());
                    NotificationCenter.getInstance(v8.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(v1_1), v6_2});
                }
                else {
                    v30 = v2;
                }

            label_911:
                ++v3_1;
                v2 = v30;
            }
        }

        v30 = v2;
        if(v11 != null) {
            v0_6 = arg35.size();
            v1_2 = 0;
            while(true) {
                if(v1_2 < v0_6) {
                    v8.updateInterfaceWithMessages(v11.keyAt(v1_2), v11.valueAt(v1_2));
                    ++v1_2;
                    continue;
                }
                else {
                    goto label_928;
                }
            }
        }
        else if(v20 != 0) {
            v8.sortDialogs(null);
        label_928:
            v0_6 = 1;
        }
        else {
            v0_6 = 0;
        }

        if(v12 != null) {
            v1_2 = arg36.size();
            v7 = v0_6;
            for(v0_6 = 0; v0_6 < v1_2; ++v0_6) {
                long v2_6 = v12.keyAt(v0_6);
                v4_1 = v12.valueAt(v0_6);
                Object v5_4 = v8.dialogMessage.get(v2_6);
                if(v5_4 != null) {
                    v6 = ((ArrayList)v4_1).size();
                    int v9_1 = 0;
                    while(v9_1 < v6) {
                        Object v10_1 = ((ArrayList)v4_1).get(v9_1);
                        if(((MessageObject)v5_4).getId() == ((MessageObject)v10_1).getId()) {
                            v8.dialogMessage.put(v2_6, v10_1);
                            if(((MessageObject)v10_1).messageOwner.to_id != null && ((MessageObject)v10_1).messageOwner.to_id.channel_id == 0) {
                                v8.dialogMessagesByIds.put(((MessageObject)v10_1).getId(), v10_1);
                            }

                            v7 = 1;
                        }
                        else {
                            if(((MessageObject)v5_4).getDialogId() == ((MessageObject)v10_1).getDialogId() && ((((MessageObject)v5_4).messageOwner.action instanceof TL_messageActionPinMessage)) && ((MessageObject)v5_4).replyMessageObject != null && ((MessageObject)v5_4).replyMessageObject.getId() == ((MessageObject)v10_1).getId()) {
                                ((MessageObject)v5_4).replyMessageObject = ((MessageObject)v10_1);
                                ((MessageObject)v5_4).generatePinMessageText(null, null);
                                v7 = 1;
                                break;
                            }

                            ++v9_1;
                            continue;
                        }

                        break;
                    }
                }

                DataQuery.getInstance(v8.currentAccount).loadReplyMessagesForMessages(((ArrayList)v4_1), v2_6);
                NotificationCenter.getInstance(v8.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(v2_6), v4_1});
            }
        }
        else {
            v7 = v0_6;
        }

        if(v7 != 0) {
            NotificationCenter.getInstance(v8.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }

        if(arg37) {
            v30 |= 64;
        }

        if(arg38 != null) {
            v30 = v30 | 1 | 128;
        }

        v1 = arg39;
        if(v1 != null) {
            v0_6 = arg39.size();
            for(v2 = 0; v2 < v0_6; ++v2) {
                MessagesStorage.getInstance(v8.currentAccount).updateChatParticipants(v1.get(v2));
            }
        }

        if(arg40 != null) {
            v0_8 = NotificationCenter.getInstance(v8.currentAccount);
            v2 = NotificationCenter.didUpdatedMessagesViews;
            v3_1 = 1;
            Object[] v4_11 = new Object[1];
            v5 = 0;
            v4_11[0] = arg40;
            v0_8.postNotificationName(v2, v4_11);
        }
        else {
            v3_1 = 1;
            v5 = 0;
        }

        if(v30 != 0) {
            v0_8 = NotificationCenter.getInstance(v8.currentAccount);
            v1_2 = NotificationCenter.updateInterfaces;
            Object[] v2_7 = new Object[v3_1];
            v2_7[v5] = Integer.valueOf(v30);
            v0_8.postNotificationName(v1_2, v2_7);
        }
    }

    public static void lambda$processUpdateArray$231(MessagesController arg9, SparseLongArray arg10, SparseLongArray arg11, SparseIntArray arg12, ArrayList arg13, SparseArray arg14, SparseIntArray arg15) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$9TP7HaYshPUu3OXRtgsu5WA2y-I(arg9, arg10, arg11, arg12, arg13, arg14, arg15));
    }

    public static void lambda$processUpdateArray$232(MessagesController arg3, ArrayList arg4, int arg5) {
        MessagesStorage.getInstance(arg3.currentAccount).updateDialogsWithDeletedMessages(arg4, MessagesStorage.getInstance(arg3.currentAccount).markMessagesAsDeleted(arg4, false, arg5), false, arg5);
    }

    public static void lambda$processUpdateArray$233(MessagesController arg3, int arg4, int arg5) {
        MessagesStorage.getInstance(arg3.currentAccount).updateDialogsWithDeletedMessages(new ArrayList(), MessagesStorage.getInstance(arg3.currentAccount).markMessagesAsDeleted(arg4, arg5, false), false, arg4);
    }

    public static void lambda$processUpdates$213(MessagesController arg4, boolean arg5, int arg6, ArrayList arg7) {
        if(arg5) {
            NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(64)});
        }

        arg4.updateInterfaceWithMessages(((long)arg6), arg7);
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$processUpdates$214(MessagesController arg4, boolean arg5, Updates arg6, ArrayList arg7) {
        if(arg5) {
            NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(64)});
        }

        arg4.updateInterfaceWithMessages(((long)(-arg6.chat_id)), arg7);
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$processUpdates$216(MessagesController arg1, ArrayList arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$RYB-E9dHjNkoPJrvoLAH4riEHJY(arg1, arg2));
    }

    static void lambda$processUpdates$217(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$processUpdates$218(MessagesController arg5) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(4)});
    }

    public static int lambda$processUpdatesQueue$174(MessagesController arg0, Updates arg1, Updates arg2) {
        return AndroidUtilities.compare(arg0.getUpdateSeq(arg1), arg0.getUpdateSeq(arg2));
    }

    static int lambda$processUpdatesQueue$175(Updates arg0, Updates arg1) {
        return AndroidUtilities.compare(arg0.pts, arg1.pts);
    }

    static int lambda$processUpdatesQueue$176(Updates arg0, Updates arg1) {
        return AndroidUtilities.compare(arg0.pts, arg1.pts);
    }

    public static void lambda$putChat$9(MessagesController arg4, Chat arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.channelRightsUpdated, new Object[]{arg5});
    }

    public static void lambda$putUsers$8(MessagesController arg5) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(4)});
    }

    public static void lambda$registerForPush$171(MessagesController arg0, String arg1, TLObject arg2, TL_error arg3) {
        if((arg2 instanceof TL_boolTrue)) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("account " + arg0.currentAccount + " registered for push");
            }

            UserConfig.getInstance(arg0.currentAccount).registeredForPush = true;
            SharedConfig.pushString = arg1;
            UserConfig.getInstance(arg0.currentAccount).saveConfig(false);
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$MQt495ngo0PePemjE4vQFdgondE(arg0));
    }

    public static void lambda$reloadDialogsReadValue$10(MessagesController arg8, TLObject arg9, TL_error arg10) {
        TL_updateReadHistoryOutbox v3_6;
        TL_updateReadHistoryInbox v3_4;
        Integer v3_2;
        int v3;
        if(arg9 != null) {
            ArrayList v10 = new ArrayList();
            int v1;
            for(v1 = 0; v1 < ((TL_messages_peerDialogs)arg9).dialogs.size(); ++v1) {
                Object v2 = ((TL_messages_peerDialogs)arg9).dialogs.get(v1);
                if(((TL_dialog)v2).read_inbox_max_id == 0) {
                    ((TL_dialog)v2).read_inbox_max_id = 1;
                }

                if(((TL_dialog)v2).read_outbox_max_id == 0) {
                    ((TL_dialog)v2).read_outbox_max_id = 1;
                }

                if(((TL_dialog)v2).id == 0 && ((TL_dialog)v2).peer != null) {
                    if(((TL_dialog)v2).peer.user_id != 0) {
                        v3 = ((TL_dialog)v2).peer.user_id;
                    }
                    else {
                        if(((TL_dialog)v2).peer.chat_id != 0) {
                            v3 = ((TL_dialog)v2).peer.chat_id;
                        }
                        else if(((TL_dialog)v2).peer.channel_id != 0) {
                            v3 = ((TL_dialog)v2).peer.channel_id;
                        }
                        else {
                            goto label_43;
                        }

                        v3 = -v3;
                    }

                    ((TL_dialog)v2).id = ((long)v3);
                }

            label_43:
                Object v3_1 = arg8.dialogs_read_inbox_max.get(Long.valueOf(((TL_dialog)v2).id));
                if(v3_1 == null) {
                    v3_2 = Integer.valueOf(0);
                }

                arg8.dialogs_read_inbox_max.put(Long.valueOf(((TL_dialog)v2).id), Integer.valueOf(Math.max(((TL_dialog)v2).read_inbox_max_id, ((Integer)v3_1).intValue())));
                if(((Integer)v3_1).intValue() == 0) {
                    if(((TL_dialog)v2).peer.channel_id != 0) {
                        TL_updateReadChannelInbox v3_3 = new TL_updateReadChannelInbox();
                        v3_3.channel_id = ((TL_dialog)v2).peer.channel_id;
                        v3_3.max_id = ((TL_dialog)v2).read_inbox_max_id;
                    }
                    else {
                        v3_4 = new TL_updateReadHistoryInbox();
                        v3_4.peer = ((TL_dialog)v2).peer;
                        v3_4.max_id = ((TL_dialog)v2).read_inbox_max_id;
                    }

                    v10.add(v3_4);
                }

                v3_1 = arg8.dialogs_read_outbox_max.get(Long.valueOf(((TL_dialog)v2).id));
                if(v3_1 == null) {
                    v3_2 = Integer.valueOf(0);
                }

                arg8.dialogs_read_outbox_max.put(Long.valueOf(((TL_dialog)v2).id), Integer.valueOf(Math.max(((TL_dialog)v2).read_outbox_max_id, ((Integer)v3_1).intValue())));
                if(((Integer)v3_1).intValue() == 0) {
                    if(((TL_dialog)v2).peer.channel_id != 0) {
                        TL_updateReadChannelOutbox v3_5 = new TL_updateReadChannelOutbox();
                        v3_5.channel_id = ((TL_dialog)v2).peer.channel_id;
                        v3_5.max_id = ((TL_dialog)v2).read_outbox_max_id;
                    }
                    else {
                        v3_6 = new TL_updateReadHistoryOutbox();
                        v3_6.peer = ((TL_dialog)v2).peer;
                        v3_6.max_id = ((TL_dialog)v2).read_outbox_max_id;
                    }

                    v10.add(v3_6);
                }
            }

            if(v10.isEmpty()) {
                return;
            }

            arg8.processUpdateArray(v10, null, null, false);
        }
    }

    public static void lambda$reloadMentionsCountForChannels$111(MessagesController arg6, ArrayList arg7) {
        int v0;
        for(v0 = 0; v0 < arg7.size(); ++v0) {
            long v1 = ((long)(-arg7.get(v0).intValue()));
            TL_messages_getUnreadMentions v3 = new TL_messages_getUnreadMentions();
            v3.peer = arg6.getInputPeer(((int)v1));
            v3.limit = 1;
            ConnectionsManager.getInstance(arg6.currentAccount).sendRequest(((TLObject)v3), new -$$Lambda$MessagesController$H1AdhCzGFMEn6WotXybxSo9gdyM(arg6, v1));
        }
    }

    public static void lambda$reloadMessages$20(MessagesController arg19, long arg20, Chat arg22, ArrayList arg23, TLObject arg24, TL_error arg25) {
        Integer v9_1;
        Object v9;
        Object v7;
        MessagesController v6 = arg19;
        long v2 = arg20;
        Chat v0 = arg22;
        if(arg25 == null) {
            TLObject v8 = arg24;
            SparseArray v1 = new SparseArray();
            int v5;
            for(v5 = 0; v5 < ((messages_Messages)v8).users.size(); ++v5) {
                v7 = ((messages_Messages)v8).users.get(v5);
                v1.put(((User)v7).id, v7);
            }

            SparseArray v5_1 = new SparseArray();
            int v7_1;
            for(v7_1 = 0; v7_1 < ((messages_Messages)v8).chats.size(); ++v7_1) {
                v9 = ((messages_Messages)v8).chats.get(v7_1);
                v5_1.put(((Chat)v9).id, v9);
            }

            v7 = v6.dialogs_read_inbox_max.get(Long.valueOf(arg20));
            if(v7 == null) {
                Integer v7_2 = Integer.valueOf(MessagesStorage.getInstance(v6.currentAccount).getDialogReadMax(false, v2));
                v6.dialogs_read_inbox_max.put(Long.valueOf(arg20), v7_2);
            }

            v9 = v6.dialogs_read_outbox_max.get(Long.valueOf(arg20));
            if(v9 == null) {
                v9_1 = Integer.valueOf(MessagesStorage.getInstance(v6.currentAccount).getDialogReadMax(true, v2));
                v6.dialogs_read_outbox_max.put(Long.valueOf(arg20), v9_1);
            }

            Object v16 = v9_1;
            ArrayList v14 = new ArrayList();
            int v13 = 0;
            while(v13 < ((messages_Messages)v8).messages.size()) {
                Object v11 = ((messages_Messages)v8).messages.get(v13);
                if(v0 != null && (v0.megagroup)) {
                    ((Message)v11).flags |= -2147483648;
                }

                ((Message)v11).dialog_id = v2;
                v9 = ((Message)v11).out ? v16 : v7;
                boolean v9_2 = ((Integer)v9).intValue() < ((Message)v11).id ? true : false;
                ((Message)v11).unread = v9_2;
                v14.add(new MessageObject(v6.currentAccount, ((Message)v11), v1, v5_1, true));
                ++v13;
                v14 = v14;
            }

            ImageLoader.saveMessagesThumbs(((messages_Messages)v8).messages);
            MessagesStorage.getInstance(v6.currentAccount).putMessages(((messages_Messages)v8), arg20, -1, 0, false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$AvgvY2klbpcPwGyMnF_mlATo0io(arg19, arg20, arg23, v14));
        }
    }

    public static void lambda$reloadWebPages$89(MessagesController arg6, String arg7, long arg8, TLObject arg10, TL_error arg11) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$4mDuvZMWcii9Tp1fJRD4AYxhOPQ(arg6, arg7, arg10, arg8));
    }

    static void lambda$reportSpam$22(TLObject arg0, TL_error arg1) {
    }

    static void lambda$reportSpam$23(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$resetDialogs$98(MessagesController arg6, int arg7, int arg8, int arg9, int arg10, TLObject arg11, TL_error arg12) {
        if(arg11 != null) {
            arg6.resetDialogsPinned = ((TL_messages_peerDialogs)arg11);
            arg6.resetDialogs(false, arg7, arg8, arg9, arg10);
        }
    }

    public static void lambda$resetDialogs$99(MessagesController arg6, int arg7, int arg8, int arg9, int arg10, TLObject arg11, TL_error arg12) {
        if(arg12 == null) {
            arg6.resetDialogsAll = ((messages_Dialogs)arg11);
            arg6.resetDialogs(false, arg7, arg8, arg9, arg10);
        }
    }

    static void lambda$saveGif$60(TLObject arg0, TL_error arg1) {
    }

    static void lambda$saveRecentSticker$61(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$sendBotStart$155(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg2 != null) {
            return;
        }

        arg0.processUpdates(((Updates)arg1), false);
    }

    public static void lambda$sendTyping$83(MessagesController arg0, int arg1, long arg2, TLObject arg4, TL_error arg5) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$OCw03SUZ92v3EuzsxRZeiXkl_2I(arg0, arg1, arg2));
    }

    public static void lambda$sendTyping$85(MessagesController arg0, int arg1, long arg2, TLObject arg4, TL_error arg5) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$jDOgw14wcfMnxd4h3ZHt__rdPEA(arg0, arg1, arg2));
    }

    public static void lambda$setLastCreatedDialogId$7(MessagesController arg1, boolean arg2, long arg3) {
        if(!arg2) {
            arg1.createdDialogIds.remove(Long.valueOf(arg3));
        }
        else if(arg1.createdDialogIds.contains(Long.valueOf(arg3))) {
            return;
        }
        else {
            arg1.createdDialogIds.add(Long.valueOf(arg3));
        }
    }

    public static void lambda$setUserAdminRole$43(MessagesController arg6, int arg7, BaseFragment arg8, TL_channels_editAdmin arg9, boolean arg10, TLObject arg11, TL_error arg12) {
        if(arg12 == null) {
            arg6.processUpdates(((Updates)arg11), false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$2g8nABpsTShnR-MOXX6WqlRmPJU(arg6, arg7), 1000);
        }
        else {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$oiiwcHTEUxoxb-i9SZp9LAGlHvY(arg6, arg12, arg8, arg9, arg10));
        }
    }

    public static void lambda$setUserBannedRole$40(MessagesController arg6, int arg7, BaseFragment arg8, TL_channels_editBanned arg9, boolean arg10, TLObject arg11, TL_error arg12) {
        if(arg12 == null) {
            arg6.processUpdates(((Updates)arg11), false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$2yiA-qSpC21sA9QtQZAVVIpabvI(arg6, arg7), 1000);
        }
        else {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$jTdTCyaMv8piU9ZLJgP2zBdvr4Y(arg6, arg12, arg8, arg9, arg10));
        }
    }

    public static void lambda$startShortPoll$178(MessagesController arg6, boolean arg7, int arg8) {
        if(arg7) {
            arg6.needShortPollChannels.delete(arg8);
        }
        else {
            arg6.needShortPollChannels.put(arg8, 0);
            if(arg6.shortPollChannels.indexOfKey(arg8) < 0) {
                arg6.getChannelDifference(arg8, 3, 0, null);
            }
        }
    }

    public static void lambda$toggleAdminMode$156(MessagesController arg0, int arg1, TLObject arg2, TL_error arg3) {
        if(arg3 == null) {
            arg0.processUpdates(((Updates)arg2), false);
            arg0.loadFullChat(arg1, 0, true);
        }
    }

    static void lambda$toggleUserAdmin$157(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$toogleChannelInvites$146(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg1 != null) {
            arg0.processUpdates(((Updates)arg1), false);
        }
    }

    public static void lambda$toogleChannelInvitesHistory$150(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg1 != null) {
            arg0.processUpdates(((Updates)arg1), false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$rvTnZgCcWEfzb2A1Nn7zUS10VnQ(arg0));
        }
    }

    public static void lambda$toogleChannelSignatures$148(MessagesController arg0, TLObject arg1, TL_error arg2) {
        if(arg1 != null) {
            arg0.processUpdates(((Updates)arg1), false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$5E7qBLUSugn9p1Ngj0yaoaOhnMM(arg0));
        }
    }

    public static void lambda$unblockUser$44(MessagesController arg0, User arg1, TLObject arg2, TL_error arg3) {
        MessagesStorage.getInstance(arg0.currentAccount).deleteBlockedUser(arg1.id);
    }

    static void lambda$unregistedPush$167(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$updateChannelAbout$152(MessagesController arg0, ChatFull arg1, String arg2, TLObject arg3, TL_error arg4) {
        if((arg3 instanceof TL_boolTrue)) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$kkStd17MrjLVulx_cPLrA43UmrI(arg0, arg1, arg2));
        }
    }

    public static void lambda$updateChannelUserName$154(MessagesController arg0, int arg1, String arg2, TLObject arg3, TL_error arg4) {
        if((arg3 instanceof TL_boolTrue)) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$VMGW_8Lz9X5bI9bIlp2l9M4P9wo(arg0, arg1, arg2));
        }
    }

    public static void lambda$updateConfig$2(MessagesController arg9, TL_config arg10) {
        int v4_1;
        int v3;
        String v0;
        LocaleController.getInstance().loadRemoteLanguages(arg9.currentAccount);
        arg9.maxMegagroupCount = arg10.megagroup_size_max;
        arg9.maxGroupCount = arg10.chat_size_max;
        arg9.maxEditTime = arg10.edit_time_limit;
        arg9.ratingDecay = arg10.rating_e_decay;
        arg9.maxRecentGifsCount = arg10.saved_gifs_limit;
        arg9.maxRecentStickersCount = arg10.stickers_recent_limit;
        arg9.maxFaveStickersCount = arg10.stickers_faved_limit;
        arg9.revokeTimeLimit = arg10.revoke_time_limit;
        arg9.revokeTimePmLimit = arg10.revoke_pm_time_limit;
        arg9.canRevokePmInbox = arg10.revoke_pm_inbox;
        arg9.linkPrefix = arg10.me_url_prefix;
        if(arg9.linkPrefix.endsWith("/")) {
            arg9.linkPrefix = arg9.linkPrefix.substring(0, arg9.linkPrefix.length() - 1);
        }

        if(arg9.linkPrefix.startsWith("https://")) {
            v0 = arg9.linkPrefix;
            v3 = 8;
            goto label_43;
        }
        else if(arg9.linkPrefix.startsWith("http://")) {
            v0 = arg9.linkPrefix;
            v3 = 7;
        label_43:
            arg9.linkPrefix = v0.substring(v3);
        }

        arg9.callReceiveTimeout = arg10.call_receive_timeout_ms;
        arg9.callRingTimeout = arg10.call_ring_timeout_ms;
        arg9.callConnectTimeout = arg10.call_connect_timeout_ms;
        arg9.callPacketTimeout = arg10.call_packet_timeout_ms;
        arg9.maxPinnedDialogsCount = arg10.pinned_dialogs_count_max;
        arg9.maxMessageLength = arg10.message_length_max;
        arg9.maxCaptionLength = arg10.caption_length_max;
        arg9.defaultP2pContacts = arg10.default_p2p_contacts;
        arg9.preloadFeaturedStickers = arg10.preload_featured_stickers;
        if(arg10.venue_search_username != null) {
            arg9.venueSearchBot = arg10.venue_search_username;
        }

        if(arg10.gif_search_username != null) {
            arg9.gifSearchBot = arg10.gif_search_username;
        }

        if(arg9.imageSearchBot != null) {
            arg9.imageSearchBot = arg10.img_search_username;
        }

        arg9.blockedCountry = arg10.blocked_mode;
        arg9.dcDomainName = arg10.dc_txt_domain_name;
        arg9.webFileDatacenterId = arg10.webfile_dc_id;
        if(arg10.static_maps_provider == null) {
            arg10.static_maps_provider = "google";
        }

        arg9.mapKey = null;
        arg9.mapProvider = 0;
        arg9.availableMapProviders = 0;
        String[] v0_1 = arg10.static_maps_provider.split(",");
        for(v3 = 0; v3 < v0_1.length; ++v3) {
            String[] v4 = v0_1[v3].split("\\+");
            if(v4.length > 0) {
                String[] v5 = v4[0].split(":");
                if(v5.length > 0) {
                    int v7 = 4;
                    if("yandex".equals(v5[0])) {
                        if(v3 == 0) {
                            arg9.mapProvider = v4.length > 1 ? 3 : 1;
                        }

                        v4_1 = arg9.availableMapProviders | v7;
                        goto label_127;
                    }
                    else {
                        if("google".equals(v5[0])) {
                            if(v3 == 0 && v4.length > 1) {
                                arg9.mapProvider = v7;
                            }

                            v4_1 = arg9.availableMapProviders | 1;
                            goto label_127;
                        }

                        if(!"telegram".equals(v5[0])) {
                            goto label_150;
                        }

                        v4_1 = 2;
                        if(v3 == 0) {
                            arg9.mapProvider = v4_1;
                        }

                        v4_1 |= arg9.availableMapProviders;
                    label_127:
                        arg9.availableMapProviders = v4_1;
                    }

                label_150:
                    if(v5.length <= 1) {
                        goto label_154;
                    }

                    arg9.mapKey = v5[1];
                }
            }

        label_154:
        }

        SharedPreferences$Editor v0_2 = arg9.mainPreferences.edit();
        v0_2.putInt("maxGroupCount", arg9.maxGroupCount);
        v0_2.putInt("maxMegagroupCount", arg9.maxMegagroupCount);
        v0_2.putInt("maxEditTime", arg9.maxEditTime);
        v0_2.putInt("ratingDecay", arg9.ratingDecay);
        v0_2.putInt("maxRecentGifsCount", arg9.maxRecentGifsCount);
        v0_2.putInt("maxRecentStickersCount", arg9.maxRecentStickersCount);
        v0_2.putInt("maxFaveStickersCount", arg9.maxFaveStickersCount);
        v0_2.putInt("callReceiveTimeout", arg9.callReceiveTimeout);
        v0_2.putInt("callRingTimeout", arg9.callRingTimeout);
        v0_2.putInt("callConnectTimeout", arg9.callConnectTimeout);
        v0_2.putInt("callPacketTimeout", arg9.callPacketTimeout);
        v0_2.putString("linkPrefix", arg9.linkPrefix);
        v0_2.putInt("maxPinnedDialogsCount", arg9.maxPinnedDialogsCount);
        v0_2.putInt("maxMessageLength", arg9.maxMessageLength);
        v0_2.putInt("maxCaptionLength", arg9.maxCaptionLength);
        v0_2.putBoolean("defaultP2pContacts", arg9.defaultP2pContacts);
        v0_2.putBoolean("preloadFeaturedStickers", arg9.preloadFeaturedStickers);
        v0_2.putInt("revokeTimeLimit", arg9.revokeTimeLimit);
        v0_2.putInt("revokeTimePmLimit", arg9.revokeTimePmLimit);
        v0_2.putInt("mapProvider", arg9.mapProvider);
        if(arg9.mapKey != null) {
            v0_2.putString("pk", arg9.mapKey);
        }
        else {
            v0_2.remove("pk");
        }

        v0_2.putBoolean("canRevokePmInbox", arg9.canRevokePmInbox);
        v0_2.putBoolean("blockedCountry", arg9.blockedCountry);
        v0_2.putString("venueSearchBot", arg9.venueSearchBot);
        v0_2.putString("gifSearchBot", arg9.gifSearchBot);
        v0_2.putString("imageSearchBot", arg9.imageSearchBot);
        v0_2.putString("dcDomainName", arg9.dcDomainName);
        v0_2.putInt("webFileDatacenterId", arg9.webFileDatacenterId);
        v0_2.commit();
        LocaleController.getInstance().checkUpdateForCurrentRemoteLocale(arg9.currentAccount, arg10.lang_pack_version);
    }

    public static void lambda$updatePrintingStrings$81(MessagesController arg0, LongSparseArray arg1, LongSparseArray arg2) {
        arg0.printingStrings = arg1;
        arg0.printingStringsTypes = arg2;
    }

    public static void lambda$updateTimerProc$65(MessagesController arg4, TLObject arg5, TL_error arg6) {
        if(arg6 == null) {
            arg4.lastStatusUpdateTime = System.currentTimeMillis();
            arg4.offlineSent = false;
            arg4.statusSettingState = 0;
        }
        else if(arg4.lastStatusUpdateTime != 0) {
            arg4.lastStatusUpdateTime += 5000;
        }

        arg4.statusRequest = 0;
    }

    public static void lambda$updateTimerProc$66(MessagesController arg3, TLObject arg4, TL_error arg5) {
        if(arg5 == null) {
            arg3.offlineSent = true;
        }
        else if(arg3.lastStatusUpdateTime != 0) {
            arg3.lastStatusUpdateTime += 5000;
        }

        arg3.statusRequest = 0;
    }

    public static void lambda$updateTimerProc$68(MessagesController arg3, int arg4, TL_messages_getMessagesViews arg5, TLObject arg6, TL_error arg7) {
        if(arg7 == null) {
            SparseArray v7 = new SparseArray();
            Object v0 = v7.get(arg4);
            if(v0 == null) {
                SparseIntArray v0_1 = new SparseIntArray();
                v7.put(arg4, v0_1);
            }

            arg4 = 0;
            while(arg4 < arg5.id.size()) {
                if(arg4 >= ((Vector)arg6).objects.size()) {
                }
                else {
                    ((SparseIntArray)v0).put(arg5.id.get(arg4).intValue(), ((Vector)arg6).objects.get(arg4).intValue());
                    ++arg4;
                    continue;
                }

                break;
            }

            MessagesStorage.getInstance(arg3.currentAccount).putChannelViews(v7, arg5.peer instanceof TL_inputPeerChannel);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$5eB5xvWVOzHbEaPUg3qNzZ1Tfqo(arg3, v7));
        }
    }

    public static void lambda$updateTimerProc$69(MessagesController arg5) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(4)});
    }

    public static void lambda$updateTimerProc$70(MessagesController arg5) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(64)});
    }

    public void loadChannelAdmins(int arg9, boolean arg10) {
        if(this.loadingChannelAdmins.indexOfKey(arg9) >= 0) {
            return;
        }

        int v1 = 0;
        this.loadingChannelAdmins.put(arg9, 0);
        if(arg10) {
            MessagesStorage.getInstance(this.currentAccount).loadChannelAdmins(arg9);
        }
        else {
            TL_channels_getParticipants v10 = new TL_channels_getParticipants();
            Object v0 = this.channelAdmins.get(arg9);
            if(v0 != null) {
                long v2 = 0;
                while(v1 < ((ArrayList)v0).size()) {
                    v2 = (v2 * 20261 + 2147483648L + (((long)((ArrayList)v0).get(v1).intValue()))) % 2147483648L;
                    ++v1;
                }

                v10.hash = ((int)v2);
            }

            v10.channel = this.getInputChannel(arg9);
            v10.limit = 100;
            v10.filter = new TL_channelParticipantsAdmins();
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v10), new -$$Lambda$MessagesController$-dC7KXxF4Zgc8rjAOxzZVItyJ1A(this, arg9));
        }
    }

    public static void loadChannelInfoByUsername(String arg2, d arg3) {
        if(arg2 == null) {
            return;
        }

        TL_contacts_resolveUsername v0 = new TL_contacts_resolveUsername();
        v0.username = arg2;
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(((TLObject)v0), new RequestDelegate(arg3) {
            public void run(TLObject arg2, TL_error arg3) {
                AndroidUtilities.runOnUIThread(new Runnable(arg3, arg2) {
                    public void run() {
                        if(this.val$error == null) {
                            TLObject v0 = this.val$response;
                            MessagesController.getInstance(UserConfig.selectedAccount).putUsers(((TL_contacts_resolvedPeer)v0).users, false);
                            MessagesController.getInstance(UserConfig.selectedAccount).putChats(((TL_contacts_resolvedPeer)v0).chats, false);
                            Log.d("LEE", "username:" + ((TL_contacts_resolvedPeer)v0).chats.size());
                            MessagesStorage.getInstance(UserConfig.selectedAccount).putUsersAndChats(((TL_contacts_resolvedPeer)v0).users, ((TL_contacts_resolvedPeer)v0).chats, false, true);
                            if(org.telegram.messenger.MessagesController$3.this.val$responseReceiver != null) {
                                org.telegram.messenger.MessagesController$3.this.val$responseReceiver.onResult(((TL_contacts_resolvedPeer)v0).chats, 0);
                            }
                        }
                    }
                });
            }
        });
    }

    public void loadChannelParticipants(Integer arg4) {
        if(!this.loadingFullParticipants.contains(arg4)) {
            if(this.loadedFullParticipants.contains(arg4)) {
            }
            else {
                this.loadingFullParticipants.add(arg4);
                TL_channels_getParticipants v0 = new TL_channels_getParticipants();
                v0.channel = this.getInputChannel(arg4.intValue());
                v0.filter = new TL_channelParticipantsRecent();
                v0.offset = 0;
                v0.limit = 32;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$8lH2VENbQNX-PlgPWMu1cnSflog(this, arg4));
            }
        }
    }

    public void loadChatInfo(int arg3, CountDownLatch arg4, boolean arg5) {
        MessagesStorage.getInstance(this.currentAccount).loadChatInfo(arg3, arg4, arg5, false);
    }

    public void loadCurrentState() {
        if(this.updatingState) {
            return;
        }

        this.updatingState = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_updates_getState(), new -$$Lambda$MessagesController$DJXfraK_AVWOwsInWJE4c_xqTlM(this));
    }

    public void loadDialogPhotos(int arg10, int arg11, long arg12, boolean arg14, int arg15) {
        if(arg14) {
            MessagesStorage.getInstance(this.currentAccount).getDialogPhotos(arg10, arg11, arg12, arg15);
        }
        else {
            if(arg10 > 0) {
                User v14 = this.getUser(Integer.valueOf(arg10));
                if(v14 == null) {
                    return;
                }
                else {
                    TL_photos_getUserPhotos v0 = new TL_photos_getUserPhotos();
                    v0.limit = arg11;
                    v0.offset = 0;
                    v0.max_id = ((long)(((int)arg12)));
                    v0.user_id = this.getInputUser(v14);
                    arg10 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$gSJ9qnqqOgU4jYIFRNohJqdSxhQ(this, arg10, arg11, arg12, arg15));
                }
            }
            else if(arg10 < 0) {
                TL_messages_search v14_1 = new TL_messages_search();
                v14_1.filter = new TL_inputMessagesFilterChatPhotos();
                v14_1.limit = arg11;
                v14_1.offset_id = ((int)arg12);
                v14_1.q = "";
                v14_1.peer = this.getInputPeer(arg10);
                arg10 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v14_1), new -$$Lambda$MessagesController$YPLgK-AlDGBTb9KPgcApuxn0__c(this, arg10, arg11, arg12, arg15));
            }
            else {
                return;
            }

            ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(arg10, arg15);
        }
    }

    public void loadDialogs(int arg8, int arg9, boolean arg10) {
        TL_inputPeerEmpty v10_1;
        if(!this.loadingDialogs) {
            if(this.resetingDialogs) {
            }
            else {
                int v0 = 1;
                this.loadingDialogs = true;
                int v3 = 0;
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("load cacheOffset = " + arg8 + " count = " + arg9 + " cache = " + arg10);
                }

                if(arg10) {
                    MessagesStorage v10 = MessagesStorage.getInstance(this.currentAccount);
                    if(arg8 == 0) {
                    }
                    else {
                        v3 = this.nextDialogsCacheOffset;
                    }

                    v10.getDialogs(v3, arg9);
                    return;
                }

                TL_messages_getDialogs v8 = new TL_messages_getDialogs();
                v8.limit = arg9;
                v8.exclude_pinned = true;
                if(UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetId == -1) {
                    int v10_2;
                    for(v10_2 = this.dialogs.size() - 1; v10_2 >= 0; --v10_2) {
                        Object v1_1 = this.dialogs.get(v10_2);
                        if(((TL_dialog)v1_1).pinned) {
                        }
                        else {
                            int v2 = ((int)((TL_dialog)v1_1).id);
                            int v4 = ((int)(((TL_dialog)v1_1).id >> 32));
                            if(v2 != 0 && v4 != 1 && ((TL_dialog)v1_1).top_message > 0) {
                                v1_1 = this.dialogMessage.get(((TL_dialog)v1_1).id);
                                if(v1_1 != null && ((MessageObject)v1_1).getId() > 0) {
                                    v8.offset_date = ((MessageObject)v1_1).messageOwner.date;
                                    v8.offset_id = ((MessageObject)v1_1).messageOwner.id;
                                    if(((MessageObject)v1_1).messageOwner.to_id.channel_id != 0) {
                                        v10_2 = ((MessageObject)v1_1).messageOwner.to_id.channel_id;
                                        goto label_151;
                                    }
                                    else if(((MessageObject)v1_1).messageOwner.to_id.chat_id != 0) {
                                        v10_2 = ((MessageObject)v1_1).messageOwner.to_id.chat_id;
                                    label_151:
                                        v10_2 = -v10_2;
                                    }
                                    else {
                                        v10_2 = ((MessageObject)v1_1).messageOwner.to_id.user_id;
                                    }

                                    v8.offset_peer = this.getInputPeer(v10_2);
                                    goto label_170;
                                }
                            }
                        }
                    }

                    v0 = 0;
                label_170:
                    if(v0 != 0) {
                        goto label_174;
                    }

                    v10_1 = new TL_inputPeerEmpty();
                label_71:
                    v8.offset_peer = ((InputPeer)v10_1);
                }
                else if(UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetId == 2147483647) {
                    this.dialogsEndReached = true;
                    this.serverDialogsEndReached = true;
                    this.loadingDialogs = false;
                    NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
                    return;
                }
                else {
                    v8.offset_id = UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetId;
                    v8.offset_date = UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetDate;
                    if(v8.offset_id == 0) {
                        v10_1 = new TL_inputPeerEmpty();
                        goto label_71;
                    }
                    else {
                        if(UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetChannelId != 0) {
                            v8.offset_peer = new TL_inputPeerChannel();
                            v8.offset_peer.channel_id = UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetChannelId;
                        }
                        else if(UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetUserId != 0) {
                            v8.offset_peer = new TL_inputPeerUser();
                            v8.offset_peer.user_id = UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetUserId;
                        }
                        else {
                            v8.offset_peer = new TL_inputPeerChat();
                            v8.offset_peer.chat_id = UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetChatId;
                        }

                        v8.offset_peer.access_hash = UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetAccess;
                    }
                }

            label_174:
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v8), new -$$Lambda$MessagesController$GC1HaMecsD7Qwmhw_7U72Qhh-uU(this, arg9));
            }
        }
    }

    public void loadFullChat(int arg10, int arg11, boolean arg12) {
        TL_channels_getFullChannel v12;
        boolean v0 = this.loadedFullChats.contains(Integer.valueOf(arg10));
        if(!this.loadingFullChats.contains(Integer.valueOf(arg10)) && ((arg12) || !v0)) {
            this.loadingFullChats.add(Integer.valueOf(arg10));
            long v4 = ((long)(-arg10));
            Chat v3 = this.getChat(Integer.valueOf(arg10));
            if(ChatObject.isChannel(v3)) {
                v12 = new TL_channels_getFullChannel();
                v12.channel = MessagesController.getInputChannel(v3);
                if(v3.megagroup) {
                    this.loadChannelAdmins(arg10, (((int)v0)) ^ 1);
                }
            }
            else {
                TL_messages_getFullChat v12_1 = new TL_messages_getFullChat();
                v12_1.chat_id = arg10;
                if(this.dialogs_read_inbox_max.get(Long.valueOf(v4)) != null && this.dialogs_read_outbox_max.get(Long.valueOf(v4)) != null) {
                    goto label_41;
                }

                this.reloadDialogsReadValue(null, v4);
            }

        label_41:
            arg10 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v12), new -$$Lambda$MessagesController$LGwVX-H7lAlIg01sEQnPSZojEQk(this, v3, v4, arg10, arg11));
            if(arg11 == 0) {
                return;
            }

            ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(arg10, arg11);
        }
    }

    public void loadFullUser(User arg5, int arg6, boolean arg7) {
        if(arg5 != null && !this.loadingFullUsers.contains(Integer.valueOf(arg5.id)) && ((arg7) || !this.loadedFullUsers.contains(Integer.valueOf(arg5.id)))) {
            this.loadingFullUsers.add(Integer.valueOf(arg5.id));
            TL_users_getFullUser v7 = new TL_users_getFullUser();
            v7.id = this.getInputUser(arg5);
            long v0 = ((long)arg5.id);
            if(this.dialogs_read_inbox_max.get(Long.valueOf(v0)) == null || this.dialogs_read_outbox_max.get(Long.valueOf(v0)) == null) {
                this.reloadDialogsReadValue(null, v0);
            }

            ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v7), new -$$Lambda$MessagesController$RrE_VcmUWoHa-Y0VNocG51Yo4qI(this, arg5, arg6)), arg6);
        }
    }

    public void loadGlobalNotificationsSettings() {
        TL_inputNotifyChats v3;
        if(this.loadingNotificationSettings == 0) {
            if(UserConfig.getInstance(this.currentAccount).notificationsSettingsLoaded) {
            }
            else {
                int v0 = 2;
                this.loadingNotificationSettings = v0;
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    TL_account_getNotifySettings v2 = new TL_account_getNotifySettings();
                    if(v1 == 0) {
                        v3 = new TL_inputNotifyChats();
                        goto label_16;
                    }
                    else if(v1 == 1) {
                        TL_inputNotifyUsers v3_1 = new TL_inputNotifyUsers();
                    label_16:
                        v2.peer = ((InputNotifyPeer)v3);
                    }

                    ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v2), new -$$Lambda$MessagesController$InkDs0rY2COmIND78AlqbPq3kLw(this, v1));
                }
            }
        }
    }

    public void loadHintDialogs() {
        if(this.hintDialogs.isEmpty()) {
            if(TextUtils.isEmpty(this.installReferer)) {
            }
            else {
                TL_help_getRecentMeUrls v0 = new TL_help_getRecentMeUrls();
                v0.referer = this.installReferer;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$xSY13iAL6btr-wsCz8jBT0p4gjk(this));
            }
        }
    }

    public void loadMessages(long arg20, int arg22, int arg23, int arg24, boolean arg25, int arg26, int arg27, int arg28, int arg29, boolean arg30, int arg31, int arg32, int arg33, int arg34, boolean arg35, int arg36) {
        this.loadMessagesInternal(arg20, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29, arg30, arg31, arg32, arg33, arg34, arg35, arg36, true);
    }

    public void loadMessages(long arg19, int arg21, int arg22, int arg23, boolean arg24, int arg25, int arg26, int arg27, int arg28, boolean arg29, int arg30) {
        this.loadMessages(arg19, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29, arg30, 0, 0, 0, false, 0);
    }

    private void loadMessagesInternal(long arg23, int arg25, int arg26, int arg27, boolean arg28, int arg29, int arg30, int arg31, int arg32, boolean arg33, int arg34, int arg35, int arg36, int arg37, boolean arg38, int arg39, boolean arg40) {
        MessagesController v15 = this;
        long v5 = arg23;
        int v4 = arg25;
        int v7 = arg26;
        boolean v0 = arg28;
        int v14 = arg30;
        int v12 = arg31;
        int v9 = arg32;
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("load messages in chat " + v5 + " count " + v4 + " max_id " + v7 + " cache " + v0 + " mindate = " + arg29 + " guid " + v14 + " load_type " + v12 + " last_message_id " + v9 + " index " + arg34 + " firstUnread " + arg35 + " unread_count " + arg36 + " last_date " + arg37 + " queryFromServer " + arg38);
        }

        int v1_1 = ((int)v5);
        if((v0) || v1_1 == 0) {
            MessagesStorage.getInstance(v15.currentAccount).getMessages(arg23, arg25, arg26, arg27, arg29, arg30, arg31, arg33, arg34);
        }
        else {
            int v0_1 = 3;
            int v2 = 2;
            if((arg40) && (v12 == v0_1 || v12 == v2) && v9 == 0) {
                TL_messages_getPeerDialogs v10 = new TL_messages_getPeerDialogs();
                InputPeer v0_2 = v15.getInputPeer(v1_1);
                TL_inputDialogPeer v1_2 = new TL_inputDialogPeer();
                v1_2.peer = v0_2;
                v10.peers.add(v1_2);
                ConnectionsManager.getInstance(v15.currentAccount);
                new -$$Lambda$MessagesController$s2-3abcCs4MHO6vWPXc41u3Mi2Q(this, arg23, arg25, arg26, arg27, arg29, arg30, arg31, arg33, arg34, arg35, arg37, arg38).sendRequest(v10, null);
                return;
            }

            TL_messages_getHistory v15_1 = new TL_messages_getHistory();
            MessagesController v11 = this;
            v15_1.peer = v11.getInputPeer(v1_1);
            if(v12 == 4) {
                v0_1 = -v4 + 5;
                goto label_125;
            }
            else if(v12 == v0_1) {
                v0_1 = -v4 / v2;
                goto label_125;
            }
            else if(v12 == 1) {
                v15_1.add_offset = -v4 - 1;
            }
            else {
                if(v12 == v2 && v7 != 0) {
                    v0_1 = -v4 + 6;
                label_125:
                    v15_1.add_offset = v0_1;
                    goto label_154;
                }

                if(v1_1 >= 0) {
                    goto label_154;
                }

                if(v7 == 0) {
                    goto label_154;
                }

                if(!ChatObject.isChannel(v11.getChat(Integer.valueOf(-v1_1)))) {
                    goto label_154;
                }

                v15_1.add_offset = -1;
                ++v15_1.limit;
            }

        label_154:
            v15_1.limit = v4;
            v15_1.offset_id = v7;
            v15_1.offset_date = arg27;
            ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(v11.currentAccount).sendRequest(v15_1, new -$$Lambda$MessagesController$LF5yTPFQIyzN9xAjWFL1yRr5ZFQ(this, arg25, arg26, arg27, arg23, arg30, arg35, arg32, arg36, arg37, arg31, arg33, arg34, arg38, arg39)), arg30);
        }
    }

    public void loadPeerSettings(User arg7, Chat arg8) {
        TL_messages_getPeerSettings v2_3;
        -$$Lambda$MessagesController$Up40O7k5yBQ9UP156SXFL0VZQss v8;
        ConnectionsManager v7_2;
        int v7_1;
        if(arg7 == null && arg8 == null) {
            return;
        }

        int v0 = arg7 != null ? arg7.id : -arg8.id;
        long v0_1 = ((long)v0);
        if(this.loadingPeerSettings.indexOfKey(v0_1) >= 0) {
            return;
        }

        this.loadingPeerSettings.put(v0_1, Boolean.valueOf(true));
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("request spam button for " + v0_1);
        }

        SharedPreferences v2_1 = this.notificationsPreferences;
        StringBuilder v4 = new StringBuilder();
        v4.append("spam3_");
        v4.append(v0_1);
        if(v2_1.getInt(v4.toString(), 0) == 1) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("spam button already hidden for " + v0_1);
            }

            return;
        }

        v2_1 = this.notificationsPreferences;
        StringBuilder v3 = new StringBuilder();
        v3.append("spam_");
        v3.append(v0_1);
        if(v2_1.getBoolean(v3.toString(), false)) {
            TL_messages_hideReportSpam v2_2 = new TL_messages_hideReportSpam();
            if(arg7 != null) {
                v7_1 = arg7.id;
                goto label_60;
            }
            else if(arg8 != null) {
                v7_1 = -arg8.id;
            label_60:
                v2_2.peer = this.getInputPeer(v7_1);
            }

            v7_2 = ConnectionsManager.getInstance(this.currentAccount);
            v8 = new -$$Lambda$MessagesController$Up40O7k5yBQ9UP156SXFL0VZQss(this, v0_1);
        }
        else {
            v2_3 = new TL_messages_getPeerSettings();
            if(arg7 != null) {
                v7_1 = arg7.id;
                goto label_77;
            }
            else if(arg8 != null) {
                v7_1 = -arg8.id;
            label_77:
                v2_3.peer = this.getInputPeer(v7_1);
            }

            v7_2 = ConnectionsManager.getInstance(this.currentAccount);
            -$$Lambda$MessagesController$earKZXcnSlbNSw2ZGT5Q1z7WPUw v8_1 = new -$$Lambda$MessagesController$earKZXcnSlbNSw2ZGT5Q1z7WPUw(this, v0_1);
        }

        v7_2.sendRequest(((TLObject)v2_3), ((RequestDelegate)v8));
    }

    public void loadPinnedDialogs(long arg4, ArrayList arg6) {
        if(UserConfig.getInstance(this.currentAccount).pinnedDialogsLoaded) {
            return;
        }

        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_messages_getPinnedDialogs(), new -$$Lambda$MessagesController$nXR2q5xWF9uj5BZ1agjbZ4dcuEk(this, arg6, arg4));
    }

    protected void loadUnknownChannel(Chat arg8, long arg9) {
        NativeByteBuffer v9;
        NativeByteBuffer v10_1;
        if(((arg8 instanceof TL_channel)) && this.gettingUnknownChannels.indexOfKey(arg8.id) < 0) {
            long v2 = 0;
            if(arg8.access_hash == v2) {
                if(arg9 != v2) {
                    MessagesStorage.getInstance(this.currentAccount).removePendingTask(arg9);
                }

                return;
            }

            TL_inputPeerChannel v0 = new TL_inputPeerChannel();
            v0.channel_id = arg8.id;
            v0.access_hash = arg8.access_hash;
            this.gettingUnknownChannels.put(arg8.id, true);
            TL_messages_getPeerDialogs v1 = new TL_messages_getPeerDialogs();
            TL_inputDialogPeer v4 = new TL_inputDialogPeer();
            v4.peer = ((InputPeer)v0);
            v1.peers.add(v4);
            if(arg9 == v2) {
                try {
                    v10_1 = new NativeByteBuffer(arg8.getObjectSize() + 4);
                    v9 = null;
                }
                catch(Exception v10) {
                    goto label_49;
                }

                try {
                    v10_1.writeInt32(0);
                    arg8.serializeToStream(((AbstractSerializedData)v10_1));
                    v9 = v10_1;
                    goto label_50;
                }
                catch(Exception v9_1) {
                    NativeByteBuffer v6 = v10_1;
                    v10 = v9_1;
                    v9 = v6;
                }

            label_49:
                FileLog.e(((Throwable)v10));
            label_50:
                arg9 = MessagesStorage.getInstance(this.currentAccount).createPendingTask(v9);
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v1), new -$$Lambda$MessagesController$AMnePJB9gnFzKsWKKdhGaqVPOXM(this, arg9, arg8));
        }
    }

    public void loadUnreadDialogs() {
        if(!this.loadingUnreadDialogs) {
            if(UserConfig.getInstance(this.currentAccount).unreadDialogsLoaded) {
            }
            else {
                this.loadingUnreadDialogs = true;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_messages_getDialogUnreadMarks(), new -$$Lambda$MessagesController$Q3c_jkH9qRjp4L8dI5BwzaSr1HA(this));
            }
        }
    }

    public void markChannelDialogMessageAsDeleted(ArrayList arg4, int arg5) {
        Object v5 = this.dialogMessage.get(((long)(-arg5)));
        if(v5 != null) {
            int v0 = 0;
            while(v0 < arg4.size()) {
                if(((MessageObject)v5).getId() == arg4.get(v0).intValue()) {
                    ((MessageObject)v5).deleted = true;
                }
                else {
                    ++v0;
                    continue;
                }

                return;
            }
        }
    }

    public void markDialogAsRead(long arg21, int arg23, int arg24, int arg25, boolean arg26, int arg27, boolean arg28) {
        Integer v3_3;
        boolean v17;
        long v12;
        long v0_1;
        int v18;
        MessagesController v8 = this;
        int v5 = arg23;
        int v4 = arg24;
        int v2 = arg25;
        int v0 = ((int)arg21);
        int v1 = 32;
        int v3 = ((int)(arg21 >> v1));
        if(v0 != 0) {
            if(v5 != 0) {
                if(v3 == 1) {
                }
                else {
                    long v9 = ((long)v5);
                    long v3_1 = ((long)v4);
                    v18 = 0;
                    if(v0 < 0) {
                        v0 = -v0;
                        if(ChatObject.isChannel(v8.getChat(Integer.valueOf(v0)))) {
                            v0_1 = (((long)v0)) << v1;
                            v9 |= v0_1;
                            v0_1 |= v3_1;
                            v12 = v9;
                            v17 = true;
                        }
                        else {
                            goto label_30;
                        }
                    }
                    else {
                    label_30:
                        v0_1 = v3_1;
                        v12 = v9;
                        v17 = false;
                    }

                    Object v3_2 = v8.dialogs_read_inbox_max.get(Long.valueOf(arg21));
                    if(v3_2 == null) {
                        v3_3 = Integer.valueOf(0);
                    }

                    v8.dialogs_read_inbox_max.put(Long.valueOf(arg21), Integer.valueOf(Math.max(v3_3.intValue(), v5)));
                    MessagesStorage.getInstance(v8.currentAccount).processPendingRead(arg21, v12, v0_1, arg25, v17);
                    MessagesStorage.getInstance(v8.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$HVgCF9ZwmiyQd8n8piLYkOgOgjo(this, arg21, arg27, arg23, arg26));
                    if(v5 == 2147483647) {
                        goto label_120;
                    }

                    v18 = 1;
                    goto label_120;
                }
            }

            return;
        }
        else {
            int v14 = v2;
            int v15 = v5;
            if(v14 == 0) {
                return;
            }

            EncryptedChat v7 = v8.getEncryptedChat(Integer.valueOf(v3));
            MessagesStorage.getInstance(v8.currentAccount).processPendingRead(arg21, ((long)v15), ((long)v4), arg25, false);
            int v11 = v14;
            EncryptedChat v12_1 = v7;
            MessagesStorage.getInstance(v8.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$Ft0kf9XeaXrEDQNqt7TfQeHz8co(this, arg21, arg25, arg26, arg27, arg24));
            if(v12_1 != null && v12_1.ttl > 0) {
                v4 = Math.max(ConnectionsManager.getInstance(v8.currentAccount).getCurrentTime(), v11);
                MessagesStorage.getInstance(v8.currentAccount).createTaskForSecretChat(v12_1.id, v4, v4, 0, null);
            }

            v18 = 1;
        }

    label_120:
        if(v18 != 0) {
            Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$3mZHCNQR1pUPjX7Qoy3SwGHhJco(this, arg21, arg28, arg25, arg23));
        }
    }

    public void markDialogAsReadNow(long arg3) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$nHwbGZoXvCaag8Ku920evlJjYHI(this, arg3));
    }

    public void markDialogAsUnread(long arg7, InputPeer arg9, long arg10) {
        int v10;
        NativeByteBuffer v11;
        Object v0 = this.dialogs_dict.get(arg7);
        if(v0 != null) {
            ((TL_dialog)v0).unread_mark = true;
            if(((TL_dialog)v0).unread_count == 0 && !this.isDialogMuted(arg7)) {
                ++this.unreadUnmutedDialogs;
            }

            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(256)});
            MessagesStorage.getInstance(this.currentAccount).setDialogUnread(arg7, true);
        }

        int v0_1 = ((int)arg7);
        if(v0_1 != 0) {
            TL_messages_markDialogUnread v2 = new TL_messages_markDialogUnread();
            v2.unread = true;
            if(arg9 == null) {
                arg9 = this.getInputPeer(v0_1);
            }

            if((arg9 instanceof TL_inputPeerEmpty)) {
                return;
            }

            TL_inputDialogPeer v0_2 = new TL_inputDialogPeer();
            v0_2.peer = arg9;
            v2.peer = ((InputDialogPeer)v0_2);
            if(arg10 == 0) {
                try {
                    v11 = new NativeByteBuffer(arg9.getObjectSize() + 12);
                    v10 = 9;
                }
                catch(Exception v7) {
                    v11 = ((NativeByteBuffer)v10);
                    goto label_54;
                }

                try {
                    v11.writeInt32(v10);
                    v11.writeInt64(arg7);
                    arg9.serializeToStream(((AbstractSerializedData)v11));
                    goto label_55;
                }
                catch(Exception v7) {
                }

            label_54:
                FileLog.e(((Throwable)v7));
            label_55:
                arg10 = MessagesStorage.getInstance(this.currentAccount).createPendingTask(v11);
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v2), new -$$Lambda$MessagesController$awDcsDxzaqR7ox75B5NF8762iGk(this, arg10));
        }
    }

    public void markMentionMessageAsRead(int arg2, int arg3, long arg4) {
        MessagesStorage.getInstance(this.currentAccount).markMentionMessageAsRead(arg2, arg3, arg4);
        if(arg3 != 0) {
            TL_channels_readMessageContents v4 = new TL_channels_readMessageContents();
            v4.channel = this.getInputChannel(arg3);
            if(v4.channel == null) {
                return;
            }
            else {
                v4.id.add(Integer.valueOf(arg2));
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v4), -$$Lambda$MessagesController$Lsua2O97Z1bK-SGh1OPmaeXK_Ts.INSTANCE);
            }
        }
        else {
            TL_messages_readMessageContents v3 = new TL_messages_readMessageContents();
            v3.id.add(Integer.valueOf(arg2));
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v3), new -$$Lambda$MessagesController$Uvq7hvm6raAAMr7m0rGOVX3EgGg(this));
        }
    }

    public void markMentionsAsRead(long arg4) {
        int v0 = ((int)arg4);
        if(v0 == 0) {
            return;
        }

        MessagesStorage.getInstance(this.currentAccount).resetMentionsCount(arg4, 0);
        TL_messages_readMentions v4 = new TL_messages_readMentions();
        v4.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(v0);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v4), -$$Lambda$MessagesController$JaKRMPgBLLo5h6qKzfrD8Cf527o.INSTANCE);
    }

    public void markMessageAsRead(int arg9, int arg10, int arg11) {
        if(arg9 != 0) {
            if(arg11 <= 0) {
            }
            else {
                int v5 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
                MessagesStorage.getInstance(this.currentAccount).createTaskForMid(arg9, arg10, v5, v5, arg11, false);
                if(arg10 != 0) {
                    TL_channels_readMessageContents v11 = new TL_channels_readMessageContents();
                    v11.channel = this.getInputChannel(arg10);
                    v11.id.add(Integer.valueOf(arg9));
                    ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v11), -$$Lambda$MessagesController$ksfYK0TxFmI6VuKZBubqsw81CUU.INSTANCE);
                }
                else {
                    TL_messages_readMessageContents v10 = new TL_messages_readMessageContents();
                    v10.id.add(Integer.valueOf(arg9));
                    ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v10), new -$$Lambda$MessagesController$h7j-h4E_vCZ8pyKwMu23usw8ucU(this));
                }
            }
        }
    }

    public void markMessageAsRead(long arg7, long arg9, int arg11) {
        long v0 = 0;
        if(arg9 != v0 && arg7 != v0 && (arg11 > 0 || arg11 == -2147483648)) {
            int v0_1 = ((int)arg7);
            int v7 = ((int)(arg7 >> 32));
            if(v0_1 != 0) {
                return;
            }

            EncryptedChat v7_1 = this.getEncryptedChat(Integer.valueOf(v7));
            if(v7_1 == null) {
                return;
            }

            ArrayList v5 = new ArrayList();
            v5.add(Long.valueOf(arg9));
            SecretChatHelper.getInstance(this.currentAccount).sendMessagesReadMessage(v7_1, v5, null);
            if(arg11 <= 0) {
                return;
            }

            int v3 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            MessagesStorage.getInstance(this.currentAccount).createTaskForSecretChat(v7_1.id, v3, v3, 0, v5);
        }
    }

    public void markMessageContentAsRead(MessageObject arg9) {
        -$$Lambda$MessagesController$roSGUM9foxXiYAiywfPiBJ0nFlg v1_2;
        ConnectionsManager v9;
        TL_channels_readMessageContents v0_1;
        ArrayList v0 = new ArrayList();
        long v1 = ((long)arg9.getId());
        if(arg9.messageOwner.to_id.channel_id != 0) {
            v1 |= (((long)arg9.messageOwner.to_id.channel_id)) << 32;
        }

        if(arg9.messageOwner.mentioned) {
            MessagesStorage.getInstance(this.currentAccount).markMentionMessageAsRead(arg9.getId(), arg9.messageOwner.to_id.channel_id, arg9.getDialogId());
        }

        v0.add(Long.valueOf(v1));
        MessagesStorage.getInstance(this.currentAccount).markMessagesContentAsRead(v0, 0);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messagesReadContent, new Object[]{v0});
        if(arg9.getId() < 0) {
            this.markMessageAsRead(arg9.getDialogId(), arg9.messageOwner.random_id, -2147483648);
        }
        else {
            if(arg9.messageOwner.to_id.channel_id != 0) {
                v0_1 = new TL_channels_readMessageContents();
                v0_1.channel = this.getInputChannel(arg9.messageOwner.to_id.channel_id);
                if(v0_1.channel == null) {
                    return;
                }
                else {
                    v0_1.id.add(Integer.valueOf(arg9.getId()));
                    v9 = ConnectionsManager.getInstance(this.currentAccount);
                    -$$Lambda$MessagesController$Fix7kxaf_rD445gkAjPcNLC716U v1_1 = -$$Lambda$MessagesController$Fix7kxaf_rD445gkAjPcNLC716U.INSTANCE;
                }
            }
            else {
                TL_messages_readMessageContents v0_2 = new TL_messages_readMessageContents();
                v0_2.id.add(Integer.valueOf(arg9.getId()));
                v9 = ConnectionsManager.getInstance(this.currentAccount);
                v1_2 = new -$$Lambda$MessagesController$roSGUM9foxXiYAiywfPiBJ0nFlg(this);
            }

            v9.sendRequest(((TLObject)v0_1), ((RequestDelegate)v1_2));
        }
    }

    public void markSelectedDialogAsRead() {
        ArrayList v0 = this.getSelectedDialogs();
        int v2;
        for(v2 = 0; v2 < v0.size(); ++v2) {
            Object v3 = v0.get(v2);
            if(((TL_dialog)v3).unread_count > 0) {
                MessagesController.getInstance(this.currentAccount).markDialogAsRead(((TL_dialog)v3).id, Math.max(0, ((TL_dialog)v3).top_message), ((TL_dialog)v3).top_message, ((TL_dialog)v3).last_message_date, true, 0, true);
            }
        }
    }

    private void migrateDialogs(int arg8, int arg9, int arg10, int arg11, int arg12, long arg13) {
        if(!this.migratingDialogs) {
            if(arg8 == -1) {
            }
            else {
                this.migratingDialogs = true;
                TL_messages_getDialogs v1 = new TL_messages_getDialogs();
                v1.exclude_pinned = true;
                v1.limit = 100;
                v1.offset_id = arg8;
                v1.offset_date = arg9;
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("start migrate with id " + arg8 + " date " + LocaleController.getInstance().formatterStats.format((((long)arg9)) * 1000));
                }

                if(arg8 == 0) {
                    v1.offset_peer = new TL_inputPeerEmpty();
                }
                else {
                    if(arg12 != 0) {
                        v1.offset_peer = new TL_inputPeerChannel();
                        v1.offset_peer.channel_id = arg12;
                    }
                    else if(arg10 != 0) {
                        v1.offset_peer = new TL_inputPeerUser();
                        v1.offset_peer.user_id = arg10;
                    }
                    else {
                        v1.offset_peer = new TL_inputPeerChat();
                        v1.offset_peer.chat_id = arg11;
                    }

                    v1.offset_peer.access_hash = arg13;
                }

                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v1), new -$$Lambda$MessagesController$6M5T_Uhrrq1cv4bJhtJHUnhIUvs(this, arg8));
            }
        }
    }

    public void muteSelectedDialogs() {
        ArrayList v0 = this.getSelectedDialogs();
        int v1;
        for(v1 = 0; v1 < v0.size(); ++v1) {
            long v2 = v0.get(v1).id;
            int v4 = 2147483647;
            SharedPreferences$Editor v5 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
            v5.putInt("notify2_" + v2, 2);
            NotificationsController.getInstance(this.currentAccount).removeNotificationsForDialog(v2);
            MessagesStorage.getInstance(this.currentAccount).setDialogFlags(v2, 1);
            v5.commit();
            Object v5_1 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(v2);
            if(v5_1 != null) {
                ((TL_dialog)v5_1).notify_settings = new TL_peerNotifySettings();
                ((TL_dialog)v5_1).notify_settings.mute_until = v4;
            }

            NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings(v2);
        }
    }

    public void openByUserName(String arg6, BaseFragment arg7, int arg8) {
        TLObject v1;
        if(arg6 != null) {
            if(arg7 == null) {
            }
            else {
                TLObject v0 = this.getUserOrChat(arg6);
                Chat v2 = null;
                if((v0 instanceof User)) {
                    if(((User)v0).min) {
                        goto label_20;
                    }
                    else {
                        v1 = ((TLObject)v2);
                    }
                }
                else if(!(v0 instanceof Chat) || (((Chat)v0).min)) {
                label_20:
                    v0 = ((TLObject)v2);
                    v1 = v0;
                }
                else {
                    v1 = v0;
                    v0 = ((TLObject)v2);
                }

                if(v0 != null) {
                    MessagesController.openChatOrProfileWith(((User)v0), v2, arg7, arg8, false);
                    return;
                }

                if(v1 != null) {
                    MessagesController.openChatOrProfileWith(((User)v2), ((Chat)v1), arg7, 1, false);
                    return;
                }

                if(arg7.getParentActivity() == null) {
                    return;
                }

                AlertDialog[] v1_1 = new AlertDialog[]{new AlertDialog(arg7.getParentActivity(), 1)};
                TL_contacts_resolveUsername v0_1 = new TL_contacts_resolveUsername();
                v0_1.username = arg6;
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$ocf3iu5hnKitc3I0hlNejWZnKEo(this, v1_1, ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0_1), new -$$Lambda$MessagesController$3egLU-F_rxzp13AUQlKW2H3gKGA(this, v1_1, arg7, arg8)), arg7), 500);
            }
        }
    }

    public static void openChatOrProfileWith(User arg3, Chat arg4, BaseFragment arg5, int arg6, boolean arg7) {
        if(arg3 == null && arg4 == null || arg5 == null) {
            return;
        }

        String v0 = null;
        if(arg4 != null) {
            v0 = MessagesController.getRestrictionReason(arg4.restriction_reason);
        }
        else if(arg3 != null) {
            v0 = MessagesController.getRestrictionReason(arg3.restriction_reason);
        }

        if(v0 != null) {
            MessagesController.showCantOpenAlert(arg5, v0);
        }
        else {
            Bundle v0_1 = new Bundle();
            if(arg4 != null) {
                v0_1.putInt("chat_id", arg4.id);
            }
            else {
                v0_1.putInt("user_id", arg3.id);
            }

            if(1 == 0) {
                arg5.presentFragment(new ProfileActivity(v0_1));
                return;
            }

            if(1 == 2) {
                arg5.presentFragment(new ChatActivity(v0_1), true, true);
                return;
            }

            arg5.presentFragment(new ChatActivity(v0_1), true);
        }
    }

    public void performLogout(int arg5) {
        boolean v0 = true;
        if(arg5 == 1) {
            this.unregistedPush();
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_auth_logOut(), new -$$Lambda$MessagesController$2W4Bem3rvAJ59eqKeWI8Ni-x-0o(this));
        }
        else {
            ConnectionsManager v2 = ConnectionsManager.getInstance(this.currentAccount);
            if(arg5 == 2) {
            }
            else {
                v0 = false;
            }

            v2.cleanup(v0);
        }

        UserConfig.getInstance(this.currentAccount).clearConfig();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.appDidLogout, new Object[0]);
        MessagesStorage.getInstance(this.currentAccount).cleanup(false);
        this.cleanup();
        ContactsController.getInstance(this.currentAccount).deleteUnknownAppAccounts();
    }

    public void pinChannelMessage(Chat arg2, int arg3, boolean arg4) {
        TL_channels_updatePinnedMessage v0 = new TL_channels_updatePinnedMessage();
        v0.channel = MessagesController.getInputChannel(arg2);
        v0.id = arg3;
        v0.silent = (((int)arg4)) ^ 1;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$1O-_PBm3XaqrJpcwsjBMNo9Ay98(this));
    }

    public boolean pinDialog(long arg9, boolean arg11, InputPeer arg12, long arg13) {
        int v14;
        NativeByteBuffer v13;
        int v0 = ((int)arg9);
        Object v1 = this.dialogs_dict.get(arg9);
        boolean v2 = false;
        if(v1 != null) {
            if(((TL_dialog)v1).pinned == arg11) {
            }
            else {
                ((TL_dialog)v1).pinned = arg11;
                if(arg11) {
                    int v4 = 0;
                    int v5 = 0;
                    while(v4 < this.dialogs.size()) {
                        Object v6 = this.dialogs.get(v4);
                        if(!((TL_dialog)v6).pinned) {
                        }
                        else {
                            v5 = Math.max(((TL_dialog)v6).pinnedNum, v5);
                            ++v4;
                            continue;
                        }

                        break;
                    }

                    ((TL_dialog)v1).pinnedNum = v5 + 1;
                }
                else {
                    ((TL_dialog)v1).pinnedNum = 0;
                }

                SparseArray v4_1 = null;
                this.sortDialogs(v4_1);
                if(!arg11 && this.dialogs.get(this.dialogs.size() - 1) == v1 && !this.dialogsEndReached) {
                    this.dialogs.remove(this.dialogs.size() - 1);
                }

                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
                if(v0 != 0 && arg13 != -1) {
                    TL_messages_toggleDialogPin v5_1 = new TL_messages_toggleDialogPin();
                    v5_1.pinned = arg11;
                    if(arg12 == null) {
                        arg12 = this.getInputPeer(v0);
                    }

                    if((arg12 instanceof TL_inputPeerEmpty)) {
                        return 0;
                    }

                    TL_inputDialogPeer v0_1 = new TL_inputDialogPeer();
                    v0_1.peer = arg12;
                    v5_1.peer = ((InputDialogPeer)v0_1);
                    if(arg13 == 0) {
                        try {
                            v13 = new NativeByteBuffer(arg12.getObjectSize() + 16);
                            v14 = 4;
                        }
                        catch(Exception v11) {
                            v13 = ((NativeByteBuffer)v4_1);
                            goto label_81;
                        }

                        try {
                            v13.writeInt32(v14);
                            v13.writeInt64(arg9);
                            v13.writeBool(arg11);
                            arg12.serializeToStream(((AbstractSerializedData)v13));
                            goto label_82;
                        }
                        catch(Exception v11) {
                        }

                    label_81:
                        FileLog.e(((Throwable)v11));
                    label_82:
                        arg13 = MessagesStorage.getInstance(this.currentAccount).createPendingTask(v13);
                    }

                    ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v5_1), new -$$Lambda$MessagesController$jszVaTS0IdLPPvVXBdHEptQDEfk(this, arg13));
                }

                MessagesStorage.getInstance(this.currentAccount).setDialogPinned(arg9, ((TL_dialog)v1).pinnedNum);
                return 1;
            }
        }

        if(v1 != null) {
            v2 = true;
        }

        return v2;
    }

    private void processChannelsUpdatesQueue(int arg9, int arg10) {
        int v5;
        Object v0 = this.updatesQueueChannels.get(arg9);
        if(v0 == null) {
            return;
        }

        int v1 = this.channelsPts.get(arg9);
        if(!((ArrayList)v0).isEmpty()) {
            if(v1 == 0) {
            }
            else {
                Collections.sort(((List)v0), -$$Lambda$MessagesController$IFqkOAXwiE-XLo_X5kBRCpf8ae4.INSTANCE);
                if(arg10 == 2) {
                    this.channelsPts.put(arg9, ((ArrayList)v0).get(0).pts);
                }

                arg10 = 0;
                while(((ArrayList)v0).size() > 0) {
                    Object v4 = ((ArrayList)v0).get(0);
                    if(((Updates)v4).pts <= v1) {
                        v5 = 2;
                    }
                    else if(((Updates)v4).pts_count + v1 == ((Updates)v4).pts) {
                        v5 = 0;
                    }
                    else {
                        v5 = 1;
                    }

                    if(v5 == 0) {
                        this.processUpdates(((Updates)v4), true);
                        ((ArrayList)v0).remove(0);
                        arg10 = 1;
                        continue;
                    }

                    if(v5 == 1) {
                        long v0_1 = this.updatesStartWaitTimeChannels.get(arg9);
                        if(v0_1 != 0 && (arg10 != 0 || Math.abs(System.currentTimeMillis() - v0_1) <= 1500)) {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.d("HOLE IN CHANNEL " + arg9 + " UPDATES QUEUE - will wait more time");
                            }

                            if(arg10 != 0) {
                                this.updatesStartWaitTimeChannels.put(arg9, System.currentTimeMillis());
                            }

                            return;
                        }

                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("HOLE IN CHANNEL " + arg9 + " UPDATES QUEUE - getChannelDifference ");
                        }

                        this.updatesStartWaitTimeChannels.delete(arg9);
                        this.updatesQueueChannels.remove(arg9);
                        this.getChannelDifference(arg9);
                        return;
                    }

                    ((ArrayList)v0).remove(0);
                }

                this.updatesQueueChannels.remove(arg9);
                this.updatesStartWaitTimeChannels.delete(arg9);
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("UPDATES CHANNEL " + arg9 + " QUEUE PROCEED - OK");
                }

                return;
            }
        }

        this.updatesQueueChannels.remove(arg9);
    }

    public void processChatInfo(int arg9, ChatFull arg10, ArrayList arg11, boolean arg12, boolean arg13, boolean arg14, MessageObject arg15) {
        if((arg12) && arg9 > 0 && !arg14) {
            this.loadFullChat(arg9, 0, arg13);
        }

        if(arg10 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$JvNgW-66JZWqmDhoB0SyKTnmbs0(this, arg11, arg12, arg10, arg14, arg15));
        }
    }

    public void processDialogsUpdate(messages_Dialogs arg2, ArrayList arg3) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$-yrsrwznFmeGSnRKgaZ8RBKmNKM(this, arg2));
    }

    public void processDialogsUpdateRead(LongSparseArray arg2, LongSparseArray arg3) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$io4Lfe51JqaE7nFDCfTltluA7ho(this, arg2, arg3));
    }

    public void processLoadedBlockedUsers(SparseIntArray arg2, ArrayList arg3, boolean arg4) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$We26HibsGo_XyxOq7of081q7mrg(this, arg3, arg4, arg2));
    }

    public void processLoadedChannelAdmins(ArrayList arg2, int arg3, boolean arg4) {
        Collections.sort(((List)arg2));
        if(!arg4) {
            MessagesStorage.getInstance(this.currentAccount).putChannelAdmins(arg3, arg2);
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$KfjRSu9f-juz9tWKlw0z6XYxOzk(this, arg3, arg2, arg4));
    }

    public void processLoadedDeleteTask(int arg2, ArrayList arg3, int arg4) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$RBptvCVqUfVAL2go6mvkSN5dLBE(this, arg3, arg2));
    }

    public void processLoadedDialogs(messages_Dialogs arg13, ArrayList arg14, int arg15, int arg16, int arg17, boolean arg18, boolean arg19, boolean arg20) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$xbWXXmtbkSNLW0cnO_Z6k_DgWPg(this, arg17, arg13, arg18, arg16, arg15, arg20, arg19, arg14));
    }

    public void processLoadedMessages(messages_Messages arg23, long arg24, int arg26, int arg27, int arg28, boolean arg29, int arg30, int arg31, int arg32, int arg33, int arg34, int arg35, boolean arg36, boolean arg37, int arg38, boolean arg39, int arg40) {
        MessagesController v4;
        Integer v3_1;
        messages_Messages v0 = arg23;
        long v14 = arg24;
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("processLoadedMessages size " + v0.messages.size() + " in chat " + v14 + " count " + arg26 + " max_id " + arg27 + " cache " + arg29 + " guid " + arg30 + " load_type " + arg35 + " last_message_id " + arg32 + " isChannel " + arg36 + " index " + arg38 + " firstUnread " + arg31 + " unread_count " + arg33 + " last_date " + arg34 + " queryFromServer " + arg39);
        }

        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$5IGqMVcF8X-Y_JuK8bZ6A-cGpkM(this, arg23, arg24, arg29, arg26, arg35, arg39, arg31, arg27, arg28, arg30, arg32, arg36, arg38, arg33, arg34, arg40, arg37));
        int v0_1 = 0;
        long v1_1 = arg24;
        int v3 = ((int)v1_1);
        try {
            v3_1 = Integer.valueOf(v3);
            v4 = this;
        }
        catch(Exception v0_2) {
            goto label_134;
        }

        try {
            User v3_2 = v4.getUser(v3_1);
            if(v3_2 != null && !v3_2.bot) {
                v0_1 = 1;
            }

            if(v0_1 != 0) {
                return;
            }

            if(!b.h(ApplicationLoader.applicationContext)) {
                return;
            }
        }
        catch(Exception v0_2) {
            goto label_131;
        }

        try {
            Log.d("LEE", "Debug1946 TShot Iam here, checkurl disable before ");
            utils.b.a(ApplicationLoader.applicationContext, arg23, v1_1);
            Log.d("LEE", "Debug1946 TShot Iam here, checkurl disable after");
            return;
        }
        catch(Exception v0_2) {
            try {
                v0_2.printStackTrace();
                return;
            }
            catch(Exception v0_2) {
            label_131:
            }
        }

    label_134:
        v0_2.printStackTrace();
    }

    public void processLoadedUserPhotos(photos_Photos arg9, int arg10, int arg11, long arg12, boolean arg14, int arg15) {
        if(!arg14) {
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(arg9.users, null, true, true);
            MessagesStorage.getInstance(this.currentAccount).putDialogPhotos(arg10, arg9);
        }
        else {
            if(arg9 != null) {
                if(arg9.photos.isEmpty()) {
                }
                else {
                    goto label_16;
                }
            }

            goto label_28;
        }

    label_16:
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$oYgeqvQBr9Eb54yWDdiE9cQgdVE(this, arg9, arg14, arg10, arg11, arg15));
        return;
    label_28:
        this.loadDialogPhotos(arg10, arg11, arg12, false, arg15);
    }

    protected void processNewChannelDifferenceParams(int arg10, int arg11, int arg12) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("processNewChannelDifferenceParams pts = " + arg10 + " pts_count = " + arg11 + " channeldId = " + arg12);
        }

        int v0_1 = this.channelsPts.get(arg12);
        if(v0_1 == 0) {
            v0_1 = MessagesStorage.getInstance(this.currentAccount).getChannelPtsSync(arg12);
            if(v0_1 == 0) {
                v0_1 = 1;
            }

            this.channelsPts.put(arg12, v0_1);
        }

        if(v0_1 + arg11 == arg10) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("APPLY CHANNEL PTS");
            }

            this.channelsPts.put(arg12, arg10);
            MessagesStorage.getInstance(this.currentAccount).saveChannelPts(arg12, arg10);
        }
        else {
            if(v0_1 == arg10) {
                return;
            }

            long v0_2 = this.updatesStartWaitTimeChannels.get(arg12);
            long v3 = 0;
            if(!this.gettingDifferenceChannels.get(arg12) && v0_2 != v3) {
                if(Math.abs(System.currentTimeMillis() - v0_2) <= 1500) {
                }
                else {
                    this.getChannelDifference(arg12);
                    return;
                }
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("ADD CHANNEL UPDATE TO QUEUE pts = " + arg10 + " pts_count = " + arg11);
            }

            if(v0_2 == v3) {
                this.updatesStartWaitTimeChannels.put(arg12, System.currentTimeMillis());
            }

            UserActionUpdatesPts v0_3 = new UserActionUpdatesPts(this, null);
            v0_3.pts = arg10;
            v0_3.pts_count = arg11;
            v0_3.chat_id = arg12;
            Object v10 = this.updatesQueueChannels.get(arg12);
            if(v10 == null) {
                ArrayList v10_1 = new ArrayList();
                this.updatesQueueChannels.put(arg12, v10_1);
            }

            ((ArrayList)v10).add(v0_3);
        }
    }

    protected void processNewDifferenceParams(int arg11, int arg12, int arg13, int arg14) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("processNewDifferenceParams seq = " + arg11 + " pts = " + arg12 + " date = " + arg13 + " pts_count = " + arg14);
        }

        org.telegram.messenger.MessagesController$1 v0_1 = null;
        long v1 = 1500;
        int v3 = -1;
        long v4 = 0;
        if(arg12 != v3) {
            if(MessagesStorage.getInstance(this.currentAccount).getLastPtsValue() + arg14 == arg12) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("APPLY PTS");
                }

                MessagesStorage.getInstance(this.currentAccount).setLastPtsValue(arg12);
                MessagesStorage.getInstance(this.currentAccount).saveDiffParams(MessagesStorage.getInstance(this.currentAccount).getLastSeqValue(), MessagesStorage.getInstance(this.currentAccount).getLastPtsValue(), MessagesStorage.getInstance(this.currentAccount).getLastDateValue(), MessagesStorage.getInstance(this.currentAccount).getLastQtsValue());
            }
            else {
                if(MessagesStorage.getInstance(this.currentAccount).getLastPtsValue() == arg12) {
                    goto label_89;
                }

                if(!this.gettingDifference && this.updatesStartWaitTimePts != v4) {
                    if(Math.abs(System.currentTimeMillis() - this.updatesStartWaitTimePts) <= v1) {
                    }
                    else {
                        this.getDifference();
                        goto label_89;
                    }
                }

                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("ADD UPDATE TO QUEUE pts = " + arg12 + " pts_count = " + arg14);
                }

                if(this.updatesStartWaitTimePts == v4) {
                    this.updatesStartWaitTimePts = System.currentTimeMillis();
                }

                UserActionUpdatesPts v6_1 = new UserActionUpdatesPts(this, v0_1);
                v6_1.pts = arg12;
                v6_1.pts_count = arg14;
                this.updatesQueuePts.add(v6_1);
            }
        }

    label_89:
        if(arg11 != v3) {
            if(MessagesStorage.getInstance(this.currentAccount).getLastSeqValue() + 1 == arg11) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("APPLY SEQ");
                }

                MessagesStorage.getInstance(this.currentAccount).setLastSeqValue(arg11);
                if(arg13 != v3) {
                    MessagesStorage.getInstance(this.currentAccount).setLastDateValue(arg13);
                }

                MessagesStorage.getInstance(this.currentAccount).saveDiffParams(MessagesStorage.getInstance(this.currentAccount).getLastSeqValue(), MessagesStorage.getInstance(this.currentAccount).getLastPtsValue(), MessagesStorage.getInstance(this.currentAccount).getLastDateValue(), MessagesStorage.getInstance(this.currentAccount).getLastQtsValue());
            }
            else {
                if(MessagesStorage.getInstance(this.currentAccount).getLastSeqValue() == arg11) {
                    return;
                }

                if(!this.gettingDifference && this.updatesStartWaitTimeSeq != v4) {
                    if(Math.abs(System.currentTimeMillis() - this.updatesStartWaitTimeSeq) <= v1) {
                    }
                    else {
                        this.getDifference();
                        return;
                    }
                }

                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("ADD UPDATE TO QUEUE seq = " + arg11);
                }

                if(this.updatesStartWaitTimeSeq == v4) {
                    this.updatesStartWaitTimeSeq = System.currentTimeMillis();
                }

                UserActionUpdatesSeq v12_1 = new UserActionUpdatesSeq(this, v0_1);
                v12_1.seq = arg11;
                this.updatesQueueSeq.add(v12_1);
            }
        }
    }

    public boolean processUpdateArray(ArrayList arg70, ArrayList arg71, ArrayList arg72, boolean arg73) {
        // Method was not decompiled
    }

    public void processUpdates(Updates arg20, boolean arg21) {
        int v18;
        boolean v8_5;
        long v7_5;
        User v9_1;
        User v8_2;
        Chat v7_3;
        User v6_3;
        int v2_3;
        StringBuilder v2_2;
        int v8_1;
        Object v15_1;
        int v6_2;
        int v7_2;
        int v13_2;
        Object v7;
        int v11_1;
        Object v12;
        int v1_1;
        MessagesController v0 = this;
        Updates v1 = arg20;
        ArrayList v3 = null;
        int v4 = 0;
        int v5 = 1;
        if((v1 instanceof TL_updateShort)) {
            ArrayList v2 = new ArrayList();
            v2.add(v1.update);
            v0.processUpdateArray(v2, v3, v3, false);
            goto label_58;
        }
        else {
            boolean v2_1 = v1 instanceof TL_updateShortChatMessage;
            long v8 = 0;
            if(!v2_1) {
                if((v1 instanceof TL_updateShortMessage)) {
                }
                else {
                    v2_1 = v1 instanceof TL_updatesCombined;
                    if(!v2_1) {
                        if((v1 instanceof TL_updates)) {
                        }
                        else {
                            if((v1 instanceof TL_updatesTooLong)) {
                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d("need get diff TL_updatesTooLong");
                                }

                                v1_1 = 0;
                                v4 = 1;
                            }
                            else {
                                if((v1 instanceof UserActionUpdatesSeq)) {
                                    MessagesStorage.getInstance(v0.currentAccount).setLastSeqValue(v1.seq);
                                    goto label_58;
                                }

                                if((v1 instanceof UserActionUpdatesPts)) {
                                    if(v1.chat_id != 0) {
                                        v0.channelsPts.put(v1.chat_id, v1.pts);
                                        MessagesStorage.getInstance(v0.currentAccount).saveChannelPts(v1.chat_id, v1.pts);
                                    }
                                    else {
                                        MessagesStorage.getInstance(v0.currentAccount).setLastPtsValue(v1.pts);
                                    }
                                }

                            label_58:
                                v1_1 = 0;
                            }

                            goto label_59;
                        }
                    }

                    SparseArray v11 = ((SparseArray)v3);
                    int v10;
                    for(v10 = 0; v10 < v1.chats.size(); ++v10) {
                        v12 = v1.chats.get(v10);
                        if(((v12 instanceof TL_channel)) && (((Chat)v12).min)) {
                            Chat v13 = v0.getChat(Integer.valueOf(((Chat)v12).id));
                            if(v13 == null || (v13.min)) {
                                v13 = MessagesStorage.getInstance(v0.currentAccount).getChatSync(v1.chat_id);
                                v0.putChat(v13, true);
                            }

                            if(v13 != null && !v13.min) {
                                goto label_91;
                            }

                            if(v11 == null) {
                                v11 = new SparseArray();
                            }

                            v11.put(((Chat)v12).id, v12);
                        }

                    label_91:
                    }

                    if(v11 != null) {
                        v10 = 0;
                        while(true) {
                            if(v10 < v1.updates.size()) {
                                v12 = v1.updates.get(v10);
                                if((v12 instanceof TL_updateNewChannelMessage)) {
                                    int v12_1 = ((TL_updateNewChannelMessage)v12).message.to_id.channel_id;
                                    if(v11.indexOfKey(v12_1) >= 0) {
                                        if(BuildVars.LOGS_ENABLED) {
                                            FileLog.d("need get diff because of min channel " + v12_1);
                                        }

                                        v10 = 1;
                                        break;
                                    }
                                }

                                ++v10;
                                continue;
                            }
                            else {
                                goto label_120;
                            }
                        }
                    }
                    else {
                    label_120:
                        v10 = 0;
                    }

                    if(v10 == 0) {
                        MessagesStorage.getInstance(v0.currentAccount).putUsersAndChats(v1.users, v1.chats, true, true);
                        Collections.sort(v1.updates, v0.updatesComparator);
                        v11_1 = 0;
                        while(v1.updates.size() > 0) {
                            v12 = v1.updates.get(v4);
                            if(v0.getUpdateType(((Update)v12)) == 0) {
                                TL_updates v13_1 = new TL_updates();
                                v13_1.updates.add(v12);
                                v13_1.pts = MessagesController.getUpdatePts(((Update)v12));
                                v13_1.pts_count = MessagesController.getUpdatePtsCount(((Update)v12));
                                while(v5 < v1.updates.size()) {
                                    Object v14 = v1.updates.get(v5);
                                    int v15 = MessagesController.getUpdatePts(((Update)v14));
                                    int v16 = MessagesController.getUpdatePtsCount(((Update)v14));
                                    if(v0.getUpdateType(((Update)v14)) != 0) {
                                        break;
                                    }

                                    if(v13_1.pts + v16 != v15) {
                                        break;
                                    }

                                    v13_1.updates.add(v14);
                                    v13_1.pts = v15;
                                    v13_1.pts_count += v16;
                                    v1.updates.remove(v5);
                                }

                                if(MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() + v13_1.pts_count == v13_1.pts) {
                                    if(!v0.processUpdateArray(v13_1.updates, v1.users, v1.chats, ((boolean)v4))) {
                                        if(!BuildVars.LOGS_ENABLED) {
                                            goto label_195;
                                        }

                                        FileLog.d("need get diff inner TL_updates, pts: " + MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() + " " + v1.seq);
                                        goto label_195;
                                    }

                                    MessagesStorage.getInstance(v0.currentAccount).setLastPtsValue(v13_1.pts);
                                    goto label_515;
                                }

                                if(MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() == v13_1.pts) {
                                    goto label_515;
                                }

                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d(v12 + " need get diff, pts: " + MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() + " " + v13_1.pts + " count = " + v13_1.pts_count);
                                }

                                if(!v0.gettingDifference && v0.updatesStartWaitTimePts != v8) {
                                    if(v0.updatesStartWaitTimePts == v8) {
                                    }
                                    else if(Math.abs(System.currentTimeMillis() - v0.updatesStartWaitTimePts) <= 1500) {
                                        goto label_240;
                                    }

                                    goto label_195;
                                }

                            label_240:
                                if(v0.updatesStartWaitTimePts == v8) {
                                    v0.updatesStartWaitTimePts = System.currentTimeMillis();
                                }

                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d("add to queue");
                                }

                                v0.updatesQueuePts.add(v13_1);
                            }
                            else {
                                if(v0.getUpdateType(((Update)v12)) != v5) {
                                    goto label_344;
                                }

                                TL_updates v6_1 = new TL_updates();
                                v6_1.updates.add(v12);
                                v6_1.pts = MessagesController.getUpdateQts(((Update)v12));
                                while(v5 < v1.updates.size()) {
                                    v7 = v1.updates.get(v5);
                                    v13_2 = MessagesController.getUpdateQts(((Update)v7));
                                    if(v0.getUpdateType(((Update)v7)) != v5) {
                                        break;
                                    }

                                    if(v6_1.pts + v5 != v13_2) {
                                        break;
                                    }

                                    v6_1.updates.add(v7);
                                    v6_1.pts = v13_2;
                                    v1.updates.remove(v5);
                                }

                                if(MessagesStorage.getInstance(v0.currentAccount).getLastQtsValue() != 0) {
                                    if(MessagesStorage.getInstance(v0.currentAccount).getLastQtsValue() + v6_1.updates.size() == v6_1.pts) {
                                    }
                                    else {
                                        if(MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() != v6_1.pts) {
                                            if(BuildVars.LOGS_ENABLED) {
                                                FileLog.d(v12 + " need get diff, qts: " + MessagesStorage.getInstance(v0.currentAccount).getLastQtsValue() + " " + v6_1.pts);
                                            }

                                            if(!v0.gettingDifference && v0.updatesStartWaitTimeQts != v8) {
                                                if(v0.updatesStartWaitTimeQts == v8) {
                                                }
                                                else if(Math.abs(System.currentTimeMillis() - v0.updatesStartWaitTimeQts) <= 1500) {
                                                    goto label_323;
                                                }

                                                goto label_195;
                                            }

                                        label_323:
                                            if(v0.updatesStartWaitTimeQts == v8) {
                                                v0.updatesStartWaitTimeQts = System.currentTimeMillis();
                                            }

                                            if(BuildVars.LOGS_ENABLED) {
                                                FileLog.d("add to queue");
                                            }

                                            v0.updatesQueueQts.add(v6_1);
                                        }
                                        else {
                                        }

                                        goto label_515;
                                    }
                                }

                                v0.processUpdateArray(v6_1.updates, v1.users, v1.chats, ((boolean)v4));
                                MessagesStorage.getInstance(v0.currentAccount).setLastQtsValue(v6_1.pts);
                                v11_1 = 1;
                                goto label_515;
                            label_195:
                                v10 = 1;
                                goto label_515;
                            label_344:
                                v7_2 = 2;
                                if(v0.getUpdateType(((Update)v12)) != v7_2) {
                                    break;
                                }

                                v6_2 = MessagesController.getUpdateChannelId(((Update)v12));
                                v13_2 = v0.channelsPts.get(v6_2);
                                if(v13_2 == 0) {
                                    v13_2 = MessagesStorage.getInstance(v0.currentAccount).getChannelPtsSync(v6_2);
                                    if(v13_2 == 0) {
                                        int v14_1 = 0;
                                        while(true) {
                                            if(v14_1 < v1.chats.size()) {
                                                v15_1 = v1.chats.get(v14_1);
                                                if(((Chat)v15_1).id == v6_2) {
                                                    v0.loadUnknownChannel(((Chat)v15_1), v8);
                                                    v4 = 1;
                                                }
                                                else {
                                                    ++v14_1;
                                                    continue;
                                                }
                                            }
                                            else {
                                                goto label_371;
                                            }

                                            break;
                                        }
                                    }
                                    else {
                                        v0.channelsPts.put(v6_2, v13_2);
                                        goto label_371;
                                    }
                                }
                                else {
                                label_371:
                                    v4 = 0;
                                }

                                TL_updates v14_2 = new TL_updates();
                                v14_2.updates.add(v12);
                                v14_2.pts = MessagesController.getUpdatePts(((Update)v12));
                                v14_2.pts_count = MessagesController.getUpdatePtsCount(((Update)v12));
                                while(v5 < v1.updates.size()) {
                                    v15_1 = v1.updates.get(v5);
                                    v8_1 = MessagesController.getUpdatePts(((Update)v15_1));
                                    int v9 = MessagesController.getUpdatePtsCount(((Update)v15_1));
                                    if(v0.getUpdateType(((Update)v15_1)) != v7_2) {
                                        break;
                                    }

                                    if(v6_2 != MessagesController.getUpdateChannelId(((Update)v15_1))) {
                                        break;
                                    }

                                    if(v14_2.pts + v9 != v8_1) {
                                        break;
                                    }

                                    v14_2.updates.add(v15_1);
                                    v14_2.pts = v8_1;
                                    v14_2.pts_count += v9;
                                    v1.updates.remove(1);
                                    v5 = 1;
                                }

                                if(v4 != 0) {
                                    goto label_506;
                                }

                                if(v14_2.pts_count + v13_2 != v14_2.pts) {
                                    if(v13_2 == v14_2.pts) {
                                        goto label_515;
                                    }

                                    if(BuildVars.LOGS_ENABLED) {
                                        FileLog.d(v12 + " need get channel diff, pts: " + v13_2 + " " + v14_2.pts + " count = " + v14_2.pts_count + " channelId = " + v6_2);
                                    }

                                    long v4_2 = v0.updatesStartWaitTimeChannels.get(v6_2);
                                    if(!v0.gettingDifferenceChannels.get(v6_2) && v4_2 != 0) {
                                        if(Math.abs(System.currentTimeMillis() - v4_2) <= 1500) {
                                        }
                                        else if(v3 == null) {
                                            v3 = new ArrayList();
                                            goto label_515;
                                        }
                                        else if(!v3.contains(Integer.valueOf(v6_2))) {
                                            goto label_485;
                                        }
                                        else {
                                            goto label_515;
                                        }
                                    }

                                    goto label_488;
                                }
                                else if(!v0.processUpdateArray(v14_2.updates, v1.users, v1.chats, false)) {
                                    if(BuildVars.LOGS_ENABLED) {
                                        FileLog.d("need get channel diff inner TL_updates, channel_id = " + v6_2);
                                    }

                                    if(v3 == null) {
                                        v3 = new ArrayList();
                                        goto label_515;
                                    }

                                    if(v3.contains(Integer.valueOf(v6_2))) {
                                        goto label_515;
                                    }
                                }
                                else {
                                    v0.channelsPts.put(v6_2, v14_2.pts);
                                    MessagesStorage.getInstance(v0.currentAccount).saveChannelPts(v6_2, v14_2.pts);
                                    goto label_515;
                                }

                            label_485:
                                v3.add(Integer.valueOf(v6_2));
                                goto label_515;
                            label_488:
                                if(v4_2 == 0) {
                                    v0.updatesStartWaitTimeChannels.put(v6_2, System.currentTimeMillis());
                                }

                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d("add to queue");
                                }

                                Object v4_3 = v0.updatesQueueChannels.get(v6_2);
                                if(v4_3 == null) {
                                    ArrayList v4_4 = new ArrayList();
                                    v0.updatesQueueChannels.put(v6_2, v4_4);
                                }

                                ((ArrayList)v4_3).add(v14_2);
                                goto label_515;
                            label_506:
                                if(!BuildVars.LOGS_ENABLED) {
                                    goto label_515;
                                }

                                FileLog.d("need load unknown channel = " + v6_2);
                            }

                        label_515:
                            v1.updates.remove(0);
                            v4 = 0;
                            v5 = 1;
                            v8 = 0;
                        }

                        if(v2_1) {
                            if(MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue() + 1 == v1.seq_start) {
                                goto label_538;
                            }
                            else if(MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue() == v1.seq_start) {
                                goto label_538;
                            }
                            else {
                                goto label_536;
                            }
                        }
                        else if(MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue() + 1 == v1.seq || v1.seq == 0 || v1.seq == MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue()) {
                        label_538:
                            v4 = 1;
                        }
                        else {
                        label_536:
                            v4 = 0;
                        }

                        if(v4 != 0) {
                            v0.processUpdateArray(v1.updates, v1.users, v1.chats, false);
                            if(v1.seq != 0) {
                                if(v1.date != 0) {
                                    MessagesStorage.getInstance(v0.currentAccount).setLastDateValue(v1.date);
                                }

                                MessagesStorage.getInstance(v0.currentAccount).setLastSeqValue(v1.seq);
                            }
                        }
                        else {
                            if(BuildVars.LOGS_ENABLED) {
                                if(v2_1) {
                                    v2_2 = new StringBuilder();
                                    v2_2.append("need get diff TL_updatesCombined, seq: ");
                                    v2_2.append(MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue());
                                    v2_2.append(" ");
                                    v4 = v1.seq_start;
                                }
                                else {
                                    v2_2 = new StringBuilder();
                                    v2_2.append("need get diff TL_updates, seq: ");
                                    v2_2.append(MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue());
                                    v2_2.append(" ");
                                    v4 = v1.seq;
                                }

                                v2_2.append(v4);
                                FileLog.d(v2_2.toString());
                            }

                            if(!v0.gettingDifference && v0.updatesStartWaitTimeSeq != 0) {
                                if(Math.abs(System.currentTimeMillis() - v0.updatesStartWaitTimeSeq) <= 1500) {
                                }
                                else {
                                    v4 = 1;
                                    goto label_633;
                                }
                            }

                            if(v0.updatesStartWaitTimeSeq == 0) {
                                v0.updatesStartWaitTimeSeq = System.currentTimeMillis();
                            }

                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.d("add TL_updates/Combined to queue");
                            }

                            v0.updatesQueueSeq.add(v1);
                        }

                        v4 = v10;
                    }
                    else {
                        v4 = v10;
                        v11_1 = 0;
                    }

                label_633:
                    v1_1 = 0;
                    goto label_1006;
                }
            }

            v2_3 = v2_1 ? v1.from_id : v1.user_id;
            User v4_5 = v0.getUser(Integer.valueOf(v2_3));
            if(v4_5 == null || (v4_5.min)) {
                v4_5 = MessagesStorage.getInstance(v0.currentAccount).getUserSync(v2_3);
                if(v4_5 != null && (v4_5.min)) {
                    v4_5 = ((User)v3);
                }

                v0.putUser(v4_5, true);
            }

            if(v1.fwd_from != null) {
                if(v1.fwd_from.from_id != 0) {
                    User v5_1 = v0.getUser(Integer.valueOf(v1.fwd_from.from_id));
                    if(v5_1 == null) {
                        v5_1 = MessagesStorage.getInstance(v0.currentAccount).getUserSync(v1.fwd_from.from_id);
                        v0.putUser(v5_1, true);
                    }

                    v6_3 = v5_1;
                    v5 = 1;
                }
                else {
                    v6_3 = ((User)v3);
                    v5 = 0;
                }

                if(v1.fwd_from.channel_id != 0) {
                    Chat v5_2 = v0.getChat(Integer.valueOf(v1.fwd_from.channel_id));
                    if(v5_2 == null) {
                        v5_2 = MessagesStorage.getInstance(v0.currentAccount).getChatSync(v1.fwd_from.channel_id);
                        v0.putChat(v5_2, true);
                    }

                    v7_3 = v5_2;
                    v5 = 1;
                    goto label_698;
                }

                v7_3 = ((Chat)v3);
            }
            else {
                v6_3 = ((User)v3);
                v7_3 = ((Chat)v6_3);
                v5 = 0;
            }

        label_698:
            if(v1.via_bot_id != 0) {
                v8_2 = v0.getUser(Integer.valueOf(v1.via_bot_id));
                if(v8_2 == null) {
                    v8_2 = MessagesStorage.getInstance(v0.currentAccount).getUserSync(v1.via_bot_id);
                    v0.putUser(v8_2, true);
                }

                v9_1 = v8_2;
                v8_1 = 1;
            }
            else {
                v9_1 = ((User)v3);
                v8_1 = 0;
            }

            boolean v10_2 = v1 instanceof TL_updateShortMessage;
            if(v10_2) {
                if(v4_5 != null) {
                    if(v5 != 0 && v6_3 == null && v7_3 == null) {
                        goto label_726;
                    }

                    if(v8_1 == 0) {
                        goto label_724;
                    }

                    if(v9_1 != null) {
                        goto label_724;
                    }
                }
                else {
                }

                goto label_726;
            }
            else {
                Chat v11_2 = v0.getChat(Integer.valueOf(v1.chat_id));
                if(v11_2 == null) {
                    v11_2 = MessagesStorage.getInstance(v0.currentAccount).getChatSync(v1.chat_id);
                    v0.putChat(v11_2, true);
                }

                if(v11_2 != null && v4_5 != null && (v5 == 0 || v6_3 != null || v7_3 != null) && (v8_1 == 0 || v9_1 != null)) {
                label_724:
                    v5 = 0;
                    goto label_746;
                }

            label_726:
                v5 = 1;
            }

        label_746:
            if(v5 == 0 && !v1.entities.isEmpty()) {
                for(v6_2 = 0; v6_2 < v1.entities.size(); ++v6_2) {
                    v7 = v1.entities.get(v6_2);
                    if((v7 instanceof TL_messageEntityMentionName)) {
                        v7_2 = ((TL_messageEntityMentionName)v7).user_id;
                        v8_2 = v0.getUser(Integer.valueOf(v7_2));
                        if(v8_2 != null && !v8_2.min) {
                            goto label_776;
                        }

                        User v7_4 = MessagesStorage.getInstance(v0.currentAccount).getUserSync(v7_2);
                        if(v7_4 != null && (v7_4.min)) {
                            v7_4 = ((User)v3);
                        }

                        if(v7_4 == null) {
                            v5 = 1;
                            break;
                        }

                        v0.putUser(v4_5, true);
                    }

                label_776:
                }
            }

            if(v4_5 == null || v4_5.status == null || v4_5.status.expires > 0) {
                v4 = 0;
            }
            else {
                v0.onlinePrivacy.put(Integer.valueOf(v4_5.id), Integer.valueOf(ConnectionsManager.getInstance(v0.currentAccount).getCurrentTime()));
                v4 = 1;
            }

            if(v5 == 0) {
                if(MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() + v1.pts_count == v1.pts) {
                    TL_message v5_3 = new TL_message();
                    v5_3.id = v1.id;
                    v6_2 = UserConfig.getInstance(v0.currentAccount).getClientUserId();
                    if(v10_2) {
                        v5_3.from_id = v1.out ? v6_2 : v2_3;
                        v5_3.to_id = new TL_peerUser();
                        v5_3.to_id.user_id = v2_3;
                        v7_5 = ((long)v2_3);
                    }
                    else {
                        v5_3.from_id = v2_3;
                        v5_3.to_id = new TL_peerChat();
                        v5_3.to_id.chat_id = v1.chat_id;
                        v7_5 = ((long)(-v1.chat_id));
                    }

                    v5_3.dialog_id = v7_5;
                    v5_3.fwd_from = v1.fwd_from;
                    v5_3.silent = v1.silent;
                    v5_3.out = v1.out;
                    v5_3.mentioned = v1.mentioned;
                    v5_3.media_unread = v1.media_unread;
                    v5_3.entities = v1.entities;
                    v5_3.message = v1.message;
                    v5_3.date = v1.date;
                    v5_3.via_bot_id = v1.via_bot_id;
                    v5_3.flags = v1.flags | 256;
                    v5_3.reply_to_msg_id = v1.reply_to_msg_id;
                    v5_3.media = new TL_messageMediaEmpty();
                    ConcurrentHashMap v7_6 = v5_3.out ? v0.dialogs_read_outbox_max : v0.dialogs_read_inbox_max;
                    Object v8_3 = v7_6.get(Long.valueOf(v5_3.dialog_id));
                    if(v8_3 == null) {
                        Integer v8_4 = Integer.valueOf(MessagesStorage.getInstance(v0.currentAccount).getDialogReadMax(v5_3.out, v5_3.dialog_id));
                        v7_6.put(Long.valueOf(v5_3.dialog_id), v8_4);
                    }

                    boolean v7_7 = ((Integer)v8_3).intValue() < v5_3.id ? true : false;
                    v5_3.unread = v7_7;
                    if(v5_3.dialog_id == (((long)v6_2))) {
                        v5_3.unread = false;
                        v5_3.media_unread = false;
                        v8_5 = true;
                        v5_3.out = true;
                    }
                    else {
                        v8_5 = true;
                    }

                    MessagesStorage.getInstance(v0.currentAccount).setLastPtsValue(v1.pts);
                    MessageObject v6_4 = new MessageObject(v0.currentAccount, ((Message)v5_3), v0.createdDialogIds.contains(Long.valueOf(v5_3.dialog_id)));
                    ArrayList v7_8 = new ArrayList();
                    v7_8.add(v6_4);
                    ArrayList v12_2 = new ArrayList();
                    v12_2.add(v5_3);
                    if(v10_2) {
                        if((v1.out) || !v0.updatePrintingUsersWithNewMessages(((long)v1.user_id), v7_8)) {
                            v8_5 = false;
                        }
                        else {
                        }

                        if(v8_5) {
                            this.updatePrintingStrings();
                        }

                        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$TxndG2kkSsiFk3LnBFq8FBjiG8A(v0, v8_5, v2_3, v7_8));
                    }
                    else {
                        v2_1 = v0.updatePrintingUsersWithNewMessages(((long)(-v1.chat_id)), v7_8);
                        if(v2_1) {
                            this.updatePrintingStrings();
                        }

                        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$6bgWWW0gpWPfWoH3Jy9ijGNkESc(v0, v2_1, v1, v7_8));
                    }

                    if(!v6_4.isOut()) {
                        MessagesStorage.getInstance(v0.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$MessagesController$VI2g9V3-dSyMGVSGQdUlabOSl70(v0, v7_8));
                    }

                    MessagesStorage.getInstance(v0.currentAccount).putMessages(v12_2, false, true, false, 0);
                }
                else {
                    if(MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() == v1.pts) {
                        goto label_1002;
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("need get diff short message, pts: " + MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue() + " " + v1.pts + " count = " + v1.pts_count);
                    }

                    if(!v0.gettingDifference && v0.updatesStartWaitTimePts != 0 && Math.abs(System.currentTimeMillis() - v0.updatesStartWaitTimePts) > 1500) {
                        goto label_796;
                    }

                    goto label_991;
                }

                goto label_1002;
            }
            else {
            label_796:
                v18 = 1;
                goto label_1003;
            label_991:
                if(v0.updatesStartWaitTimePts == 0) {
                    v0.updatesStartWaitTimePts = System.currentTimeMillis();
                }

                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("add to queue");
                }

                v0.updatesQueuePts.add(v1);
            label_1002:
                v18 = 0;
            }

        label_1003:
            v1_1 = v4;
            v4 = v18;
        label_59:
            v11_1 = 0;
        }

    label_1006:
        SecretChatHelper.getInstance(v0.currentAccount).processPendingEncMessages();
        if(!arg21) {
            for(v2_3 = 0; v2_3 < v0.updatesQueueChannels.size(); ++v2_3) {
                v5 = v0.updatesQueueChannels.keyAt(v2_3);
                if(v3 == null || !v3.contains(Integer.valueOf(v5))) {
                    v0.processChannelsUpdatesQueue(v5, 0);
                }
                else {
                    v0.getChannelDifference(v5);
                }
            }

            if(v4 != 0) {
                this.getDifference();
                goto label_1037;
            }

            for(v2_3 = 0; v2_3 < 3; ++v2_3) {
                v0.processUpdatesQueue(v2_3, 0);
            }
        }

    label_1037:
        if(v11_1 != 0) {
            TL_messages_receivedQueue v2_4 = new TL_messages_receivedQueue();
            v2_4.max_qts = MessagesStorage.getInstance(v0.currentAccount).getLastQtsValue();
            ConnectionsManager.getInstance(v0.currentAccount).sendRequest(((TLObject)v2_4), -$$Lambda$MessagesController$rpTZFuhrVc21m-B6q6tdueSXN2c.INSTANCE);
        }

        if(v1_1 != 0) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$i_yXEQkd7cSx0rA3R-370cvUqOA(v0));
        }

        MessagesStorage.getInstance(v0.currentAccount).saveDiffParams(MessagesStorage.getInstance(v0.currentAccount).getLastSeqValue(), MessagesStorage.getInstance(v0.currentAccount).getLastPtsValue(), MessagesStorage.getInstance(v0.currentAccount).getLastDateValue(), MessagesStorage.getInstance(v0.currentAccount).getLastQtsValue());
    }

    private void processUpdatesQueue(int arg9, int arg10) {
        -$$Lambda$MessagesController$zpaQhU1OxkqKk3fukxYvEw7gKt4 v3;
        ArrayList v2;
        int v0 = 2;
        if(arg9 == 0) {
            v2 = this.updatesQueueSeq;
            v3 = new -$$Lambda$MessagesController$zpaQhU1OxkqKk3fukxYvEw7gKt4(this);
            goto label_6;
        }
        else if(arg9 == 1) {
            v2 = this.updatesQueuePts;
            -$$Lambda$MessagesController$8fBZ1tBTHag-UMD1XqT63zouO5A v3_1 = -$$Lambda$MessagesController$8fBZ1tBTHag-UMD1XqT63zouO5A.INSTANCE;
            goto label_6;
        }
        else if(arg9 == v0) {
            v2 = this.updatesQueueQts;
            -$$Lambda$MessagesController$vO_40Mvv1YiENLtlpzTNpX-C_YQ v3_2 = -$$Lambda$MessagesController$vO_40Mvv1YiENLtlpzTNpX-C_YQ.INSTANCE;
        label_6:
            Collections.sort(((List)v2), ((Comparator)v3));
        }
        else {
            v2 = null;
        }

        long v3_3 = 0;
        if(v2 != null && !v2.isEmpty()) {
            if(arg10 == v0) {
                Object v10 = v2.get(0);
                if(arg9 == 0) {
                    MessagesStorage.getInstance(this.currentAccount).setLastSeqValue(this.getUpdateSeq(((Updates)v10)));
                }
                else if(arg9 == 1) {
                    MessagesStorage.getInstance(this.currentAccount).setLastPtsValue(((Updates)v10).pts);
                }
                else {
                    MessagesStorage.getInstance(this.currentAccount).setLastQtsValue(((Updates)v10).pts);
                }
            }

            arg10 = 0;
            while(v2.size() > 0) {
                Object v0_1 = v2.get(0);
                int v6 = this.isValidUpdate(((Updates)v0_1), arg9);
                if(v6 == 0) {
                    this.processUpdates(((Updates)v0_1), true);
                    v2.remove(0);
                    arg10 = 1;
                    continue;
                }

                if(v6 == 1) {
                    if(this.getUpdatesStartTime(arg9) != v3_3 && (arg10 != 0 || Math.abs(System.currentTimeMillis() - this.getUpdatesStartTime(arg9)) <= 1500)) {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("HOLE IN UPDATES QUEUE - will wait more time");
                        }

                        if(arg10 != 0) {
                            this.setUpdatesStartTime(arg9, System.currentTimeMillis());
                        }

                        return;
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("HOLE IN UPDATES QUEUE - getDifference");
                    }

                    this.setUpdatesStartTime(arg9, v3_3);
                    v2.clear();
                    this.getDifference();
                    return;
                }

                v2.remove(0);
            }

            v2.clear();
            if(!BuildVars.LOGS_ENABLED) {
                goto label_83;
            }

            FileLog.d("UPDATES QUEUE PROCEED - OK");
        }

    label_83:
        this.setUpdatesStartTime(arg9, v3_3);
    }

    public void putChat(Chat arg5, boolean arg6) {
        if(arg5 == null) {
            return;
        }

        Object v0 = this.chats.get(Integer.valueOf(arg5.id));
        if((((Chat)v0)) == arg5) {
            return;
        }

        if(v0 != null && !TextUtils.isEmpty(((Chat)v0).username)) {
            this.objectsByUsernames.remove(((Chat)v0).username.toLowerCase());
        }

        if(!TextUtils.isEmpty(arg5.username)) {
            this.objectsByUsernames.put(arg5.username.toLowerCase(), arg5);
        }

        String v2 = null;
        if(!arg5.min) {
            int v1 = 131072;
            int v3 = 0;
            if(!arg6) {
                if(v0 == null) {
                    goto label_57;
                }

                if(arg5.version != ((Chat)v0).version) {
                    this.loadedFullChats.remove(Integer.valueOf(arg5.id));
                }

                if(((Chat)v0).participants_count != 0 && arg5.participants_count == 0) {
                    arg5.participants_count = ((Chat)v0).participants_count;
                    arg5.flags |= v1;
                }

                int v6 = ((Chat)v0).banned_rights != null ? ((Chat)v0).banned_rights.flags : 0;
                if(arg5.banned_rights != null) {
                    v3 = arg5.banned_rights.flags;
                }

                if(v6 == v3) {
                    goto label_57;
                }

                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$dEiybRUVOZJkHtC46acA59-yM3M(this, arg5));
                goto label_57;
            }

            if(v0 != null) {
                if(!((Chat)v0).min) {
                    return;
                }

                arg5.min = false;
                arg5.title = ((Chat)v0).title;
                arg5.photo = ((Chat)v0).photo;
                arg5.broadcast = ((Chat)v0).broadcast;
                arg5.verified = ((Chat)v0).verified;
                arg5.megagroup = ((Chat)v0).megagroup;
                arg5.democracy = ((Chat)v0).democracy;
                if(((Chat)v0).username != null) {
                    arg5.username = ((Chat)v0).username;
                    arg5.flags |= 64;
                }
                else {
                    arg5.flags &= -65;
                    arg5.username = v2;
                }

                if(((Chat)v0).participants_count != 0 && arg5.participants_count == 0) {
                    arg5.participants_count = ((Chat)v0).participants_count;
                    arg5.flags |= v1;
                }
            }

        label_57:
            this.chats.put(Integer.valueOf(arg5.id), arg5);
        }
        else if(v0 == null) {
            goto label_57;
        }
        else if(!arg6) {
            ((Chat)v0).title = arg5.title;
            ((Chat)v0).photo = arg5.photo;
            ((Chat)v0).broadcast = arg5.broadcast;
            ((Chat)v0).verified = arg5.verified;
            ((Chat)v0).megagroup = arg5.megagroup;
            ((Chat)v0).democracy = arg5.democracy;
            if(arg5.username != null) {
                ((Chat)v0).username = arg5.username;
                ((Chat)v0).flags |= 64;
            }
            else {
                ((Chat)v0).flags &= -65;
                ((Chat)v0).username = v2;
            }

            if(arg5.participants_count == 0) {
                return;
            }

            ((Chat)v0).participants_count = arg5.participants_count;
        }
    }

    public void putChats(ArrayList arg4, boolean arg5) {
        if(arg4 != null) {
            if(arg4.isEmpty()) {
            }
            else {
                int v0 = arg4.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.putChat(arg4.get(v1), arg5);
                }
            }
        }
    }

    public void putEncryptedChat(EncryptedChat arg2, boolean arg3) {
        if(arg2 == null) {
            return;
        }

        if(arg3) {
            this.encryptedChats.putIfAbsent(Integer.valueOf(arg2.id), arg2);
        }
        else {
            this.encryptedChats.put(Integer.valueOf(arg2.id), arg2);
        }
    }

    public void putEncryptedChats(ArrayList arg4, boolean arg5) {
        if(arg4 != null) {
            if(arg4.isEmpty()) {
            }
            else {
                int v0 = arg4.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.putEncryptedChat(arg4.get(v1), arg5);
                }
            }
        }
    }

    public boolean putUser(User arg6, boolean arg7) {
        if(arg6 == null) {
            return 0;
        }

        int v7 = !arg7 || arg6.id / 1000 == 333 || arg6.id == 777000 ? 0 : 1;
        Object v2 = this.users.get(Integer.valueOf(arg6.id));
        if((((User)v2)) == arg6) {
            return 0;
        }

        if(v2 != null && !TextUtils.isEmpty(((User)v2).username)) {
            this.objectsByUsernames.remove(((User)v2).username.toLowerCase());
        }

        if(!TextUtils.isEmpty(arg6.username)) {
            this.objectsByUsernames.put(arg6.username.toLowerCase(), arg6);
        }

        String v4 = null;
        if(!arg6.min) {
            if(v7 == 0) {
                this.users.put(Integer.valueOf(arg6.id), arg6);
                if(arg6.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                    UserConfig.getInstance(this.currentAccount).setCurrentUser(arg6);
                    UserConfig.getInstance(this.currentAccount).saveConfig(true);
                }

                if(v2 == null) {
                    return 0;
                }

                if(arg6.status == null) {
                    return 0;
                }

                if(((User)v2).status == null) {
                    return 0;
                }

                if(arg6.status.expires == ((User)v2).status.expires) {
                    return 0;
                }

                return 1;
            }

            if(v2 != null) {
                if(!((User)v2).min) {
                    return 0;
                }

                arg6.min = false;
                if(((User)v2).bot) {
                    if(((User)v2).username != null) {
                        arg6.username = ((User)v2).username;
                        arg6.flags |= 8;
                    }
                    else {
                        arg6.flags &= -9;
                        arg6.username = v4;
                    }
                }

                if(((User)v2).photo != null) {
                    arg6.photo = ((User)v2).photo;
                    arg6.flags |= 32;
                }
                else {
                    arg6.flags &= -33;
                    arg6.photo = ((UserProfilePhoto)v4);
                }
            }

        label_68:
            this.users.put(Integer.valueOf(arg6.id), arg6);
        }
        else if(v2 == null) {
            goto label_68;
        }
        else if(v7 == 0) {
            if(arg6.bot) {
                if(arg6.username != null) {
                    ((User)v2).username = arg6.username;
                    ((User)v2).flags |= 8;
                }
                else {
                    ((User)v2).flags &= -9;
                    ((User)v2).username = v4;
                }
            }

            if(arg6.photo != null) {
                ((User)v2).photo = arg6.photo;
                ((User)v2).flags |= 32;
                return 0;
            }

            ((User)v2).flags &= -33;
            ((User)v2).photo = ((UserProfilePhoto)v4);
        }

        return 0;
    }

    public void putUsers(ArrayList arg5, boolean arg6) {
        if(arg5 != null) {
            if(arg5.isEmpty()) {
            }
            else {
                int v0 = arg5.size();
                int v1 = 0;
                int v2 = 0;
                while(v1 < v0) {
                    if(this.putUser(arg5.get(v1), arg6)) {
                        v2 = 1;
                    }

                    ++v1;
                }

                if(v2 == 0) {
                    return;
                }

                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$qK4RdlgFavhTo_PvYOoZyFgxn74(this));
            }
        }
    }

    public void registerForPush(String arg6) {
        if(!TextUtils.isEmpty(((CharSequence)arg6)) && !this.registeringForPush) {
            if(UserConfig.getInstance(this.currentAccount).getClientUserId() == 0) {
            }
            else {
                if((UserConfig.getInstance(this.currentAccount).registeredForPush) && (arg6.equals(SharedConfig.pushString))) {
                    return;
                }

                this.registeringForPush = true;
                this.lastPushRegisterSendTime = SystemClock.elapsedRealtime();
                if(SharedConfig.pushAuthKey == null) {
                    SharedConfig.pushAuthKey = new byte[256];
                    Utilities.random.nextBytes(SharedConfig.pushAuthKey);
                    SharedConfig.saveConfig();
                }

                TL_account_registerDevice v0 = new TL_account_registerDevice();
                v0.token_type = 2;
                v0.token = arg6;
                v0.secret = SharedConfig.pushAuthKey;
                int v1;
                for(v1 = 0; v1 < 3; ++v1) {
                    UserConfig v2 = UserConfig.getInstance(v1);
                    if(v1 != this.currentAccount && (v2.isClientActivated())) {
                        int v2_1 = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
                        v0.other_uids.add(Integer.valueOf(v2_1));
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("add other uid = " + v2_1 + " for account " + this.currentAccount);
                        }
                    }
                }

                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$RGMM38JHy-J2xsxwpQJiGPhHQ6w(this, arg6));
            }
        }
    }

    private void reloadDialogsReadValue(ArrayList arg7, long arg8) {
        long v0 = 0;
        if(arg8 == v0 && (arg7 == null || (arg7.isEmpty()))) {
            return;
        }

        TL_messages_getPeerDialogs v2 = new TL_messages_getPeerDialogs();
        if(arg7 != null) {
            int v8;
            for(v8 = 0; v8 < arg7.size(); ++v8) {
                InputPeer v9 = this.getInputPeer(((int)arg7.get(v8).id));
                if(!(v9 instanceof TL_inputPeerChannel) || v9.access_hash != v0) {
                    TL_inputDialogPeer v3 = new TL_inputDialogPeer();
                    v3.peer = v9;
                    v2.peers.add(v3);
                }
                else {
                }
            }
        }
        else {
            InputPeer v7 = this.getInputPeer(((int)arg8));
            if(((v7 instanceof TL_inputPeerChannel)) && v7.access_hash == v0) {
                return;
            }

            TL_inputDialogPeer v8_1 = new TL_inputDialogPeer();
            v8_1.peer = v7;
            v2.peers.add(v8_1);
        }

        if(v2.peers.isEmpty()) {
            return;
        }

        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v2), new -$$Lambda$MessagesController$poCHi3ajJ1OgG00CEx7rxwPUA6I(this));
    }

    public void reloadMentionsCountForChannels(ArrayList arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$efgOluvxGNICtDOoJHcF5LCZvH4(this, arg2));
    }

    private void reloadMessages(ArrayList arg9, long arg10) {
        TL_messages_getMessages v0_1;
        if(arg9.isEmpty()) {
            return;
        }

        ArrayList v6 = new ArrayList();
        Chat v5 = ChatObject.getChatByDialog(arg10, this.currentAccount);
        if(ChatObject.isChannel(v5)) {
            TL_channels_getMessages v0 = new TL_channels_getMessages();
            v0.channel = MessagesController.getInputChannel(v5);
            v0.id = v6;
        }
        else {
            v0_1 = new TL_messages_getMessages();
            v0_1.id = v6;
        }

        Object v1 = this.reloadingMessages.get(arg10);
        int v2;
        for(v2 = 0; v2 < arg9.size(); ++v2) {
            Object v3 = arg9.get(v2);
            if(v1 == null || !((ArrayList)v1).contains(v3)) {
                v6.add(v3);
            }
            else {
            }
        }

        if(v6.isEmpty()) {
            return;
        }

        if(v1 == null) {
            ArrayList v1_1 = new ArrayList();
            this.reloadingMessages.put(arg10, v1_1);
        }

        ((ArrayList)v1).addAll(((Collection)v6));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0_1), new -$$Lambda$MessagesController$QhJGNGV6q9NodF06WsHicwFciYQ(this, arg10, v5, v6));
    }

    public void reloadWebPages(long arg5, HashMap arg7) {
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            Object v1 = ((Map$Entry)v0).getKey();
            v0 = ((Map$Entry)v0).getValue();
            Object v2 = this.reloadingWebpages.get(v1);
            if(v2 == null) {
                ArrayList v2_1 = new ArrayList();
                this.reloadingWebpages.put(v1, v2_1);
            }

            ((ArrayList)v2).addAll(((Collection)v0));
            TL_messages_getWebPagePreview v0_1 = new TL_messages_getWebPagePreview();
            v0_1.message = ((String)v1);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0_1), new -$$Lambda$MessagesController$Fij5djSH_vsnr6kSX29dQd1wFbA(this, ((String)v1), arg5));
        }
    }

    public void reportSpam(long arg4, User arg6, Chat arg7, EncryptedChat arg8) {
        -$$Lambda$MessagesController$Mk6jHGSt4O4ay9JrE9e3NnwIk-U v7_1;
        int v6_1;
        TL_messages_reportSpam v4_2;
        ConnectionsManager v6;
        if(arg6 == null && arg7 == null && arg8 == null) {
            return;
        }

        SharedPreferences$Editor v0 = this.notificationsPreferences.edit();
        v0.putInt("spam3_" + arg4, 1);
        v0.commit();
        int v4 = ((int)arg4);
        int v5 = 2;
        if(v4 == 0) {
            if(arg8 != null) {
                if(arg8.access_hash == 0) {
                }
                else {
                    TL_messages_reportEncryptedSpam v4_1 = new TL_messages_reportEncryptedSpam();
                    v4_1.peer = new TL_inputEncryptedChat();
                    v4_1.peer.chat_id = arg8.id;
                    v4_1.peer.access_hash = arg8.access_hash;
                    v6 = ConnectionsManager.getInstance(this.currentAccount);
                    -$$Lambda$MessagesController$EV8hAbOwV6mBTH9mkLzXyKWHanw v7 = -$$Lambda$MessagesController$EV8hAbOwV6mBTH9mkLzXyKWHanw.INSTANCE;
                    goto label_37;
                }
            }

            return;
        }
        else {
            v4_2 = new TL_messages_reportSpam();
            if(arg7 != null) {
                v6_1 = -arg7.id;
                goto label_45;
            }
            else if(arg6 != null) {
                v6_1 = arg6.id;
            label_45:
                v4_2.peer = this.getInputPeer(v6_1);
            }

            v6 = ConnectionsManager.getInstance(this.currentAccount);
            v7_1 = -$$Lambda$MessagesController$Mk6jHGSt4O4ay9JrE9e3NnwIk-U.INSTANCE;
        }

    label_37:
        v6.sendRequest(((TLObject)v4_2), ((RequestDelegate)v7_1), v5);
    }

    private void resetDialogs(boolean arg26, int arg27, int arg28, int arg29, int arg30) {
        Integer v9_3;
        int v9_2;
        Object v9;
        Object v8_1;
        Object v5;
        MessagesController v6 = this;
        if(arg26) {
            if(v6.resetingDialogs) {
                return;
            }
            else {
                v6.resetingDialogs = true;
                ConnectionsManager.getInstance(v6.currentAccount).sendRequest(new TL_messages_getPinnedDialogs(), new -$$Lambda$MessagesController$rrdZkbUBkzrw5YVALJ7NYQg19D8(this, arg27, arg28, arg29, arg30));
                TL_messages_getDialogs v8 = new TL_messages_getDialogs();
                v8.limit = 100;
                v8.exclude_pinned = true;
                v8.offset_peer = new TL_inputPeerEmpty();
                ConnectionsManager.getInstance(v6.currentAccount).sendRequest(((TLObject)v8), new -$$Lambda$MessagesController$tqO-kdyfq_bYCkkE5TmmFgCmjdU(this, arg27, arg28, arg29, arg30));
            }
        }
        else if(v6.resetDialogsPinned != null && v6.resetDialogsAll != null) {
            int v10 = v6.resetDialogsAll.messages.size();
            int v18 = v6.resetDialogsAll.dialogs.size();
            v6.resetDialogsAll.dialogs.addAll(v6.resetDialogsPinned.dialogs);
            v6.resetDialogsAll.messages.addAll(v6.resetDialogsPinned.messages);
            v6.resetDialogsAll.users.addAll(v6.resetDialogsPinned.users);
            v6.resetDialogsAll.chats.addAll(v6.resetDialogsPinned.chats);
            LongSparseArray v15 = new LongSparseArray();
            LongSparseArray v0 = new LongSparseArray();
            SparseArray v1 = new SparseArray();
            SparseArray v2 = new SparseArray();
            int v4;
            for(v4 = 0; v4 < v6.resetDialogsAll.users.size(); ++v4) {
                v5 = v6.resetDialogsAll.users.get(v4);
                v1.put(((User)v5).id, v5);
            }

            for(v4 = 0; v4 < v6.resetDialogsAll.chats.size(); ++v4) {
                v5 = v6.resetDialogsAll.chats.get(v4);
                v2.put(((Chat)v5).id, v5);
            }

            TL_messages_peerDialogs v4_1 = null;
            Object v14 = v4_1;
            int v5_1;
            for(v5_1 = 0; v5_1 < v6.resetDialogsAll.messages.size(); ++v5_1) {
                v8_1 = v6.resetDialogsAll.messages.get(v5_1);
                if(v5_1 < v10 && (v14 == null || ((Message)v8_1).date < ((Message)v14).date)) {
                    v14 = v8_1;
                }

                if(((Message)v8_1).to_id.channel_id != 0) {
                    v9 = v2.get(((Message)v8_1).to_id.channel_id);
                    if(v9 != null && (((Chat)v9).left)) {
                        goto label_156;
                    }

                    if(v9 == null) {
                        goto label_144;
                    }

                    if(!((Chat)v9).megagroup) {
                        goto label_144;
                    }

                    ((Message)v8_1).flags |= -2147483648;
                    goto label_144;
                }
                else {
                    if(((Message)v8_1).to_id.chat_id != 0) {
                        v9 = v2.get(((Message)v8_1).to_id.chat_id);
                        if(v9 == null) {
                            goto label_144;
                        }

                        if(((Chat)v9).migrated_to == null) {
                            goto label_144;
                        }

                        goto label_156;
                    }

                label_144:
                    MessageObject v9_1 = new MessageObject(v6.currentAccount, v8_1, v1, v2, false);
                    v0.put(v9_1.getDialogId(), v9_1);
                }

            label_156:
            }

            for(v5_1 = 0; v5_1 < v6.resetDialogsAll.dialogs.size(); ++v5_1) {
                v8_1 = v6.resetDialogsAll.dialogs.get(v5_1);
                long v16 = 0;
                if(((TL_dialog)v8_1).id == v16 && ((TL_dialog)v8_1).peer != null) {
                    if(((TL_dialog)v8_1).peer.user_id != 0) {
                        v9_2 = ((TL_dialog)v8_1).peer.user_id;
                    }
                    else {
                        if(((TL_dialog)v8_1).peer.chat_id != 0) {
                            v9_2 = ((TL_dialog)v8_1).peer.chat_id;
                        }
                        else if(((TL_dialog)v8_1).peer.channel_id != 0) {
                            v9_2 = ((TL_dialog)v8_1).peer.channel_id;
                        }
                        else {
                            goto label_192;
                        }

                        v9_2 = -v9_2;
                    }

                    ((TL_dialog)v8_1).id = ((long)v9_2);
                }

            label_192:
                if(((TL_dialog)v8_1).id == v16) {
                }
                else {
                    if(((TL_dialog)v8_1).last_message_date == 0) {
                        v9 = v0.get(((TL_dialog)v8_1).id);
                        if(v9 != null) {
                            ((TL_dialog)v8_1).last_message_date = ((MessageObject)v9).messageOwner.date;
                        }
                    }

                    if(DialogObject.isChannel(((TL_dialog)v8_1))) {
                        v9 = v2.get(-(((int)((TL_dialog)v8_1).id)));
                        if(v9 != null && (((Chat)v9).left)) {
                            goto label_261;
                        }

                        v6.channelsPts.put(-(((int)((TL_dialog)v8_1).id)), ((TL_dialog)v8_1).pts);
                    }
                    else {
                        if((((int)((TL_dialog)v8_1).id)) >= 0) {
                            goto label_231;
                        }

                        v9 = v2.get(-(((int)((TL_dialog)v8_1).id)));
                        if(v9 == null) {
                            goto label_231;
                        }

                        if(((Chat)v9).migrated_to == null) {
                            goto label_231;
                        }

                        goto label_261;
                    }

                label_231:
                    v15.put(((TL_dialog)v8_1).id, v8_1);
                    v9 = v6.dialogs_read_inbox_max.get(Long.valueOf(((TL_dialog)v8_1).id));
                    if(v9 == null) {
                        v9_3 = Integer.valueOf(0);
                    }

                    v6.dialogs_read_inbox_max.put(Long.valueOf(((TL_dialog)v8_1).id), Integer.valueOf(Math.max(v9_3.intValue(), ((TL_dialog)v8_1).read_inbox_max_id)));
                    v9 = v6.dialogs_read_outbox_max.get(Long.valueOf(((TL_dialog)v8_1).id));
                    if(v9 == null) {
                        v9_3 = Integer.valueOf(0);
                    }

                    v6.dialogs_read_outbox_max.put(Long.valueOf(((TL_dialog)v8_1).id), Integer.valueOf(Math.max(v9_3.intValue(), ((TL_dialog)v8_1).read_outbox_max_id)));
                }

            label_261:
            }

            ImageLoader.saveMessagesThumbs(v6.resetDialogsAll.messages);
            int v2_1;
            for(v2_1 = 0; v2_1 < v6.resetDialogsAll.messages.size(); ++v2_1) {
                v5 = v6.resetDialogsAll.messages.get(v2_1);
                if((((Message)v5).action instanceof TL_messageActionChatDeleteUser)) {
                    if((v6.hideLeftGroup) && ((Message)v5).action.user_id == ((Message)v5).from_id) {
                        goto label_331;
                    }

                    v8_1 = v1.get(((Message)v5).action.user_id);
                    if(v8_1 == null) {
                        goto label_296;
                    }

                    if(!((User)v8_1).bot) {
                        goto label_296;
                    }

                    ((Message)v5).reply_markup = new TL_replyKeyboardHide();
                    ((Message)v5).flags |= 64;
                    goto label_296;
                }
                else {
                label_296:
                    if(!(((Message)v5).action instanceof TL_messageActionChatMigrateTo)) {
                        if((((Message)v5).action instanceof TL_messageActionChannelCreate)) {
                        }
                        else {
                            ConcurrentHashMap v8_2 = ((Message)v5).out ? v6.dialogs_read_outbox_max : v6.dialogs_read_inbox_max;
                            v9 = v8_2.get(Long.valueOf(((Message)v5).dialog_id));
                            if(v9 == null) {
                                v9_3 = Integer.valueOf(MessagesStorage.getInstance(v6.currentAccount).getDialogReadMax(((Message)v5).out, ((Message)v5).dialog_id));
                                v8_2.put(Long.valueOf(((Message)v5).dialog_id), v9_3);
                            }

                            boolean v8_3 = v9_3.intValue() < ((Message)v5).id ? true : false;
                            ((Message)v5).unread = v8_3;
                            goto label_331;
                        }
                    }

                    ((Message)v5).unread = false;
                    ((Message)v5).media_unread = false;
                }

            label_331:
            }

            MessagesStorage.getInstance(v6.currentAccount).resetDialogs(v6.resetDialogsAll, v10, arg27, arg28, arg29, arg30, v15, v0, v14, v18);
            v6.resetDialogsPinned = v4_1;
            v6.resetDialogsAll = ((messages_Dialogs)v4_1);
        }
    }

    public void saveGif(Document arg5) {
        TL_messages_saveGif v0 = new TL_messages_saveGif();
        v0.id = new TL_inputDocument();
        v0.id.id = arg5.id;
        v0.id.access_hash = arg5.access_hash;
        v0.unsave = false;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), -$$Lambda$MessagesController$tCUhxXW8DECfuGStmG55CaueLmk.INSTANCE);
    }

    public void saveRecentSticker(Document arg5, boolean arg6) {
        TL_messages_saveRecentSticker v0 = new TL_messages_saveRecentSticker();
        v0.id = new TL_inputDocument();
        v0.id.id = arg5.id;
        v0.id.access_hash = arg5.access_hash;
        v0.unsave = false;
        v0.attached = arg6;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), -$$Lambda$MessagesController$PuAV50MlcsCGtPiPPnzon28dPWQ.INSTANCE);
    }

    public void selectAllDialogs() {
        int v0;
        for(v0 = 0; v0 < this.dialogs.size(); ++v0) {
            this.dialogs.get(v0).setSelected(true);
        }

        this.sortDialogs(null);
    }

    public void sendBotStart(User arg3, String arg4) {
        if(arg3 == null) {
            return;
        }

        TL_messages_startBot v0 = new TL_messages_startBot();
        v0.bot = this.getInputUser(arg3);
        v0.peer = this.getInputPeer(arg3.id);
        v0.start_param = arg4;
        v0.random_id = Utilities.random.nextLong();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$LLpHuoP0JJJkSXLtIr9ic94hnxE(this));
    }

    public void sendTyping(long arg9, int arg11, int arg12) {
        int v9;
        TL_sendMessageGamePlayAction v1_8;
        LongSparseArray v0_1;
        if(arg9 == 0) {
            return;
        }

        Object v0 = this.sendingTypings.get(arg11);
        if(v0 != null && ((LongSparseArray)v0).get(arg9) != null) {
            return;
        }

        if(v0 == null) {
            v0_1 = new LongSparseArray();
            this.sendingTypings.put(arg11, v0_1);
        }

        int v1 = ((int)arg9);
        int v2 = ((int)(arg9 >> 32));
        int v3 = 2;
        if(v1 == 0) {
            if(arg11 != 0) {
                return;
            }

            EncryptedChat v1_12 = this.getEncryptedChat(Integer.valueOf(v2));
            if(v1_12.auth_key == null) {
                return;
            }

            if(v1_12.auth_key.length <= 1) {
                return;
            }

            if(!(v1_12 instanceof TL_encryptedChat)) {
                return;
            }

            TL_messages_setEncryptedTyping v2_2 = new TL_messages_setEncryptedTyping();
            v2_2.peer = new TL_inputEncryptedChat();
            v2_2.peer.chat_id = v1_12.id;
            v2_2.peer.access_hash = v1_12.access_hash;
            v2_2.typing = true;
            v0_1.put(arg9, Boolean.valueOf(true));
            v9 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v2_2), new -$$Lambda$MessagesController$6-l6-YtlZJgxOpFYw8e8eZ2LXJs(this, arg11, arg9), v3);
            if(arg12 == 0) {
                return;
            }

        label_129:
            ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(v9, arg12);
        }
        else if(v2 == 1) {
            return;
        }
        else {
            TL_messages_setTyping v2_1 = new TL_messages_setTyping();
            v2_1.peer = this.getInputPeer(v1);
            if((v2_1.peer instanceof TL_inputPeerChannel)) {
                Chat v1_1 = this.getChat(Integer.valueOf(v2_1.peer.channel_id));
                if(v1_1 != null && (v1_1.megagroup)) {
                    goto label_38;
                }

                return;
            }

        label_38:
            if(v2_1.peer == null) {
                return;
            }

            if(arg11 == 0) {
                TL_sendMessageTypingAction v1_2 = new TL_sendMessageTypingAction();
                goto label_44;
            }
            else if(arg11 == 1) {
                TL_sendMessageRecordAudioAction v1_3 = new TL_sendMessageRecordAudioAction();
                goto label_44;
            }
            else if(arg11 == v3) {
                TL_sendMessageCancelAction v1_4 = new TL_sendMessageCancelAction();
                goto label_44;
            }
            else if(arg11 == 3) {
                TL_sendMessageUploadDocumentAction v1_5 = new TL_sendMessageUploadDocumentAction();
                goto label_44;
            }
            else if(arg11 == 4) {
                TL_sendMessageUploadPhotoAction v1_6 = new TL_sendMessageUploadPhotoAction();
                goto label_44;
            }
            else if(arg11 == 5) {
                TL_sendMessageUploadVideoAction v1_7 = new TL_sendMessageUploadVideoAction();
                goto label_44;
            }
            else if(arg11 == 6) {
                v1_8 = new TL_sendMessageGamePlayAction();
                goto label_44;
            }
            else if(arg11 == 7) {
                TL_sendMessageRecordRoundAction v1_9 = new TL_sendMessageRecordRoundAction();
                goto label_44;
            }
            else if(arg11 == 8) {
                TL_sendMessageUploadRoundAction v1_10 = new TL_sendMessageUploadRoundAction();
                goto label_44;
            }
            else if(arg11 == 9) {
                TL_sendMessageUploadAudioAction v1_11 = new TL_sendMessageUploadAudioAction();
            label_44:
                v2_1.action = ((SendMessageAction)v1_8);
            }

            v0_1.put(arg9, Boolean.valueOf(true));
            v9 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v2_1), new -$$Lambda$MessagesController$JukBPw3IHsyuQtGai3fvVE1yMZg(this, arg11, arg9), v3);
            if(arg12 == 0) {
                return;
            }

            goto label_129;
        }
    }

    public void setLastCreatedDialogId(long arg3, boolean arg5) {
        if(!arg5) {
            this.createdDialogMainThreadIds.remove(Long.valueOf(arg3));
        }
        else if(this.createdDialogMainThreadIds.contains(Long.valueOf(arg3))) {
            return;
        }
        else {
            this.createdDialogMainThreadIds.add(Long.valueOf(arg3));
        }

        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$mdx8ZbppyciMBGYUzp4s4cTlgyc(this, arg5, arg3));
    }

    public void setLastVisibleDialogId(long arg2, boolean arg4) {
        if(!arg4) {
            this.visibleDialogMainThreadIds.remove(Long.valueOf(arg2));
        }
        else if(this.visibleDialogMainThreadIds.contains(Long.valueOf(arg2))) {
            return;
        }
        else {
            this.visibleDialogMainThreadIds.add(Long.valueOf(arg2));
        }
    }

    public void setReferer(String arg3) {
        if(arg3 == null) {
            return;
        }

        this.installReferer = arg3;
        this.mainPreferences.edit().putString("installReferer", arg3).commit();
    }

    private void setUpdatesStartTime(int arg2, long arg3) {
        if(arg2 == 0) {
            this.updatesStartWaitTimeSeq = arg3;
        }
        else if(arg2 == 1) {
            this.updatesStartWaitTimePts = arg3;
        }
        else if(arg2 == 2) {
            this.updatesStartWaitTimeQts = arg3;
        }
    }

    public void setUserAdminRole(int arg8, User arg9, TL_channelAdminRights arg10, boolean arg11, BaseFragment arg12) {
        if(arg9 != null) {
            if(arg10 == null) {
            }
            else {
                TL_channels_editAdmin v6 = new TL_channels_editAdmin();
                v6.channel = this.getInputChannel(arg8);
                v6.user_id = this.getInputUser(arg9);
                v6.admin_rights = arg10;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v6), new -$$Lambda$MessagesController$q6jEfeI1PqWjWqN9gWw4PJ3O6ek(this, arg8, arg12, v6, arg11));
            }
        }
    }

    public void setUserBannedRole(int arg8, User arg9, TL_channelBannedRights arg10, boolean arg11, BaseFragment arg12) {
        if(arg9 != null) {
            if(arg10 == null) {
            }
            else {
                TL_channels_editBanned v6 = new TL_channels_editBanned();
                v6.channel = this.getInputChannel(arg8);
                v6.user_id = this.getInputUser(arg9);
                v6.banned_rights = arg10;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v6), new -$$Lambda$MessagesController$UyE_Fi4uI9nVAiwFhdA19vVaXA0(this, arg8, arg12, v6, arg11));
            }
        }
    }

    private static void showCantOpenAlert(BaseFragment arg3, String arg4) {
        if(arg3 != null) {
            if(arg3.getParentActivity() == null) {
            }
            else {
                Builder v0 = new Builder(arg3.getParentActivity());
                v0.setTitle(LocaleController.getString("AppName", 2131624086));
                v0.setPositiveButton(LocaleController.getString("OK", 2131625420), null);
                v0.setMessage(((CharSequence)arg4));
                arg3.showDialog(v0.create());
            }
        }
    }

    public void sortDialogs(SparseArray arg17) {
        ArrayList v2_2;
        MessagesController v0 = this;
        SparseArray v1 = arg17;
        v0.dialogsServerOnly.clear();
        v0.dialogsGroupsOnly.clear();
        v0.dialogsForward.clear();
        int v2 = 0;
        v0.unreadUnmutedDialogs = 0;
        int v3 = UserConfig.getInstance(v0.currentAccount).getClientUserId();
        v0.dialogsUsers.clear();
        v0.dialogsGroups.clear();
        v0.dialogsGroupsAll.clear();
        v0.dialogsChannels.clear();
        v0.dialogsMegaGroups.clear();
        v0.dialogsBots.clear();
        v0.dialogsFavs.clear();
        v0.dialogsHidden.clear();
        v0.dialogsAll.clear();
        v0.dialogsUnread.clear();
        v0.dialogsAds.clear();
        int v4 = b.B(ApplicationLoader.applicationContext);
        v0.hiddensIds = b.b(ApplicationLoader.applicationContext);
        v0.categoriesIds = b.ab(ApplicationLoader.applicationContext);
        boolean v5 = b.y(ApplicationLoader.applicationContext);
        ArrayList v6 = Favourite.getFavouriteIds();
        Collections.sort(v0.dialogs, v0.dialogComparator);
        v0.isLeftProxyChannel = true;
        long v9 = 0;
        if(v0.proxyDialog != null && v0.proxyDialog.id < v9) {
            Chat v8 = v0.getChat(Integer.valueOf(-(((int)v0.proxyDialog.id))));
            if(v8 != null && !v8.left) {
                v0.isLeftProxyChannel = false;
            }
        }

        int v8_1 = 0;
        int v11 = 0;
        while(v8_1 < v0.dialogs.size()) {
            Object v12 = v0.dialogs.get(v8_1);
            if(v4 != 2 || ((TL_dialog)v12).unread_count > 0) {
                if(v4 == 4 && (MessagesController.getInstance(v0.currentAccount).isDialogMuted(((TL_dialog)v12).id))) {
                    goto label_251;
                }

                if(v4 == 3 && !MessagesController.getInstance(v0.currentAccount).isDialogMuted(((TL_dialog)v12).id)) {
                    goto label_251;
                }

                boolean v13 = v0.hiddensIds.contains(Long.valueOf(((TL_dialog)v12).id));
                if(v13) {
                    if(v5) {
                        v0.dialogsHidden.add(v12);
                    }
                    else {
                        goto label_251;
                    }
                }

                if((v5) || !v13) {
                    v0.dialogsAll.add(v12);
                }

                if(((TL_dialog)v12).unread_count > 0) {
                    v0.dialogsUnread.add(v12);
                }

                int v13_1 = ((int)(((TL_dialog)v12).id >> 32));
                int v14 = ((int)((TL_dialog)v12).id);
                if(v14 == v3) {
                    v0.dialogsForward.add(v2, v12);
                    v11 = 1;
                }
                else {
                    v0.dialogsForward.add(v12);
                }

                if(v14 != 0 && v13_1 != 1) {
                    v0.dialogsServerOnly.add(v12);
                    if(DialogObject.isChannel(((TL_dialog)v12))) {
                        Chat v14_1 = v0.getChat(Integer.valueOf(-v14));
                        if(v14_1 != null) {
                            if((v14_1.megagroup) && v14_1.admin_rights != null) {
                                if(v14_1.admin_rights.post_messages) {
                                }
                                else if(!v14_1.admin_rights.add_admins) {
                                    goto label_144;
                                }

                                goto label_158;
                            }

                        label_144:
                            if(!v14_1.creator) {
                                goto label_160;
                            }
                        }
                        else {
                            goto label_160;
                        }
                    }
                    else {
                        if(v14 >= 0) {
                            goto label_160;
                        }

                        if(v1 == null) {
                            goto label_158;
                        }

                        Object v14_2 = v1.get(-v14);
                        if(v14_2 == null) {
                            goto label_158;
                        }

                        if(((Chat)v14_2).migrated_to == null) {
                            goto label_158;
                        }

                        v0.dialogs.remove(v8_1);
                        --v8_1;
                        goto label_251;
                    }

                label_158:
                    v0.dialogsGroupsOnly.add(v12);
                }

            label_160:
                if(v0.proxyDialog != null && ((TL_dialog)v12).id == v0.proxyDialog.id && (v0.isLeftProxyChannel)) {
                    v0.dialogs.remove(v8_1);
                    --v8_1;
                }

                if((((TL_dialog)v12).unread_count != 0 || (((TL_dialog)v12).unread_mark)) && !v0.isDialogMuted(((TL_dialog)v12).id)) {
                    ++v0.unreadUnmutedDialogs;
                }

                if((((int)((TL_dialog)v12).id)) != 0 && v13_1 != 1 && ((v12 instanceof TL_dialog))) {
                    if(((TL_dialog)v12).id < 0) {
                        Chat v2_1 = v0.getChat(Integer.valueOf(-(((int)((TL_dialog)v12).id))));
                        if(v2_1 != null && (v2_1.megagroup) && (v2_1.creator)) {
                            v0.dialogsGroupsOnly.add(v12);
                        }

                        if(v2_1 == null) {
                            goto label_245;
                        }

                        if(v2_1.megagroup) {
                            v2_2 = v0.dialogsMegaGroups;
                            goto label_242;
                        }

                        if(!ChatObject.isChannel(v2_1)) {
                            goto label_241;
                        }

                        v0.dialogsChannels.add(v12);
                        Iterator v2_3 = v0.categoriesIds.iterator();
                        do {
                            if(!v2_3.hasNext()) {
                                goto label_245;
                            }
                        }
                        while((((long)v2_3.next().intValue())) != Math.abs(((TL_dialog)v12).id));

                        v2_2 = v0.dialogsAds;
                    }
                    else {
                        User v2_4 = v0.getUser(Integer.valueOf(((int)((TL_dialog)v12).id)));
                        if(v2_4 != null) {
                            if(v2_4.bot) {
                                v0.dialogsBots.add(v12);
                                if(MessagesController.staticBotArr.contains(v12)) {
                                    goto label_245;
                                }

                                v2_2 = MessagesController.staticBotArr;
                                goto label_244;
                            }

                            v2_2 = v0.dialogsUsers;
                            goto label_244;
                        }

                    label_241:
                        v2_2 = v0.dialogsGroups;
                    label_242:
                        v2_2.add(v12);
                        v2_2 = v0.dialogsGroupsAll;
                    }

                label_244:
                    v2_2.add(v12);
                }

            label_245:
                if(!v6.contains(Long.valueOf(((TL_dialog)v12).id))) {
                    goto label_251;
                }

                v0.dialogsFavs.add(v12);
            }
            else {
            }

        label_251:
            ++v8_1;
            v2 = 0;
        }

        if(v0.proxyDialog != null && (v0.isLeftProxyChannel)) {
            v0.dialogs.add(0, v0.proxyDialog);
        }

        if(v11 == 0) {
            User v1_1 = UserConfig.getInstance(v0.currentAccount).getCurrentUser();
            if(v1_1 != null) {
                TL_dialog v2_5 = new TL_dialog();
                v2_5.id = ((long)v1_1.id);
                v2_5.notify_settings = new TL_peerNotifySettings();
                v2_5.peer = new TL_peerUser();
                v2_5.peer.user_id = v1_1.id;
                v0.dialogsForward.add(0, v2_5);
            }
        }
    }

    public void startShortPoll(int arg3, boolean arg4) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesController$S5_kZiPduE_6M5udBllVfn8jaQI(this, arg4, arg3));
    }

    public void toggleAdminMode(int arg3, boolean arg4) {
        TL_messages_toggleChatAdmins v0 = new TL_messages_toggleChatAdmins();
        v0.chat_id = arg3;
        v0.enabled = arg4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$jRvRTaJUPPJs9SMQf7XuBxDQzYs(this, arg3));
    }

    public void toggleUserAdmin(int arg2, int arg3, boolean arg4) {
        TL_messages_editChatAdmin v0 = new TL_messages_editChatAdmin();
        v0.chat_id = arg2;
        v0.user_id = this.getInputUser(arg3);
        v0.is_admin = arg4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), -$$Lambda$MessagesController$lPbEN3OTQcQy84iTY_Z8ie7xtjU.INSTANCE);
    }

    public void toogleChannelInvites(int arg3, boolean arg4) {
        TL_channels_toggleInvites v0 = new TL_channels_toggleInvites();
        v0.channel = this.getInputChannel(arg3);
        v0.enabled = arg4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$AXcKFNAL8Ht7rN00ummgn99FBvg(this), 64);
    }

    public void toogleChannelInvitesHistory(int arg3, boolean arg4) {
        TL_channels_togglePreHistoryHidden v0 = new TL_channels_togglePreHistoryHidden();
        v0.channel = this.getInputChannel(arg3);
        v0.enabled = arg4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$lX17DL_1oylPgx52kDXHDDG-n1o(this), 64);
    }

    public void toogleChannelSignatures(int arg3, boolean arg4) {
        TL_channels_toggleSignatures v0 = new TL_channels_toggleSignatures();
        v0.channel = this.getInputChannel(arg3);
        v0.enabled = arg4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$EAQa1J5Q52j1X5IHNrCQElNVpss(this), 64);
    }

    public void unFavSelectedDialogs() {
        ArrayList v0 = this.getSelectedDialogs();
        int v1;
        for(v1 = 0; v1 < v0.size(); ++v1) {
            long v2 = v0.get(v1).id;
            Object v4 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(v2);
            if(v4 != null && (Favourite.isFavourite(Long.valueOf(((TL_dialog)v4).id)))) {
                Favourite.deleteFavourite(Long.valueOf(v2));
                MessagesController.getInstance(this.currentAccount).dialogsFavs.remove(v4);
            }
        }
    }

    public void unMuteSelectedDialogs() {
        ArrayList v0 = this.getSelectedDialogs();
        int v2;
        for(v2 = 0; v2 < v0.size(); ++v2) {
            long v3 = v0.get(v2).id;
            SharedPreferences$Editor v5 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
            v5.putInt("notify2_" + v3, 0);
            MessagesStorage.getInstance(this.currentAccount).setDialogFlags(v3, 0);
            v5.commit();
            Object v5_1 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(v3);
            if(v5_1 != null) {
                ((TL_dialog)v5_1).notify_settings = new TL_peerNotifySettings();
            }

            NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings(v3);
        }
    }

    public void unSelectAllDialogs() {
        int v1;
        for(v1 = 0; v1 < this.dialogs.size(); ++v1) {
            this.dialogs.get(v1).setSelected(false);
        }

        this.sortDialogs(null);
    }

    public void unblockUser(int arg5) {
        TL_contacts_unblock v0 = new TL_contacts_unblock();
        User v5 = this.getUser(Integer.valueOf(arg5));
        if(v5 == null) {
            return;
        }

        this.blockedUsers.delete(v5.id);
        v0.id = this.getInputUser(v5);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoaded, new Object[0]);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$1Cnz2TH9cpvZBRFDW7n_d6xUyyI(this, v5));
    }

    public void unregistedPush() {
        if((UserConfig.getInstance(this.currentAccount).registeredForPush) && SharedConfig.pushString.length() == 0) {
            TL_account_unregisterDevice v0 = new TL_account_unregisterDevice();
            v0.token = SharedConfig.pushString;
            v0.token_type = 2;
            int v1;
            for(v1 = 0; v1 < 3; ++v1) {
                UserConfig v2 = UserConfig.getInstance(v1);
                if(v1 != this.currentAccount && (v2.isClientActivated())) {
                    v0.other_uids.add(Integer.valueOf(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()));
                }
            }

            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), -$$Lambda$MessagesController$p-H7yUuEq-ctY0wbOGCLjbITKJo.INSTANCE);
        }
    }

    public void updateChannelAbout(int arg3, String arg4, ChatFull arg5) {
        if(arg5 == null) {
            return;
        }

        TL_channels_editAbout v0 = new TL_channels_editAbout();
        v0.channel = this.getInputChannel(arg3);
        v0.about = arg4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$MhtgbF1Yy6hl5261w3U145rMKug(this, arg5, arg4), 64);
    }

    public void updateChannelUserName(int arg4, String arg5) {
        TL_channels_updateUsername v0 = new TL_channels_updateUsername();
        v0.channel = this.getInputChannel(arg4);
        v0.username = arg5;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$MessagesController$Lfkfm_TXCo-YKrvyXq7RFkvhSqw(this, arg4, arg5), 64);
    }

    public void updateConfig(TL_config arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$E_B3T1gXoq4eD739GgHdBDLPpBs(this, arg2));
    }

    public void updateDialogSelectStatus(long arg7) {
        Iterator v0 = this.dialogs.iterator();
        int v1 = 0;
        while(v0.hasNext()) {
            Object v2 = v0.next();
            if(arg7 == ((TL_dialog)v2).id) {
                this.dialogs.get(v1).setSelected(((TL_dialog)v2).isSelected ^ 1);
            }
            else {
                ++v1;
                continue;
            }

            break;
        }

        this.sortDialogs(null);
    }

    public void updateDialogsForAddOrRemoveDialog() {
        ArrayList v3;
        Log.e("alireza", "updateDialogsForAddOrRemoveDialog dialogs");
        this.dialogsGroups.clear();
        this.dialogsUsers.clear();
        this.dialogsGroupsAll.clear();
        this.dialogsChannels.clear();
        this.dialogsMegaGroups.clear();
        this.dialogsBots.clear();
        this.dialogsFavs.clear();
        this.dialogsHidden.clear();
        this.dialogsAll.clear();
        this.dialogsUnread.clear();
        this.dialogsAds.clear();
        this.hiddensIds = b.b(ApplicationLoader.applicationContext);
        this.categoriesIds = b.ab(ApplicationLoader.applicationContext);
        int v0;
        for(v0 = 0; v0 < this.dialogs.size(); ++v0) {
            Object v1 = this.dialogs.get(v0);
            if(!this.hiddensIds.contains(Long.valueOf(((TL_dialog)v1).id))) {
            label_47:
                if(((TL_dialog)v1).unread_count > 0) {
                    this.dialogsUnread.add(v1);
                }

                if((b.y(ApplicationLoader.applicationContext)) || !this.hiddensIds.contains(Long.valueOf(((TL_dialog)v1).id))) {
                    this.dialogsAll.add(v1);
                }

                int v2 = ((int)(((TL_dialog)v1).id >> 32));
                if((((int)((TL_dialog)v1).id)) == 0) {
                    goto label_105;
                }

                if(v2 == 1) {
                    goto label_105;
                }

                if((v1 instanceof TL_dialog)) {
                    if(((TL_dialog)v1).id < 0) {
                        this.dialogsGroupsOnly.add(v1);
                        this.dialogsGroups.add(v1);
                        v3 = this.dialogsGroupsAll;
                    }
                    else {
                        User v3_1 = this.getUser(Integer.valueOf(((int)((TL_dialog)v1).id)));
                        if(v3_1 == null) {
                            goto label_93;
                        }
                        else if(v3_1.bot) {
                            v3 = this.dialogsBots;
                        }
                        else {
                            v3 = this.dialogsUsers;
                        }
                    }

                    v3.add(v1);
                }

            label_93:
                if((this.getEncryptedChat(Integer.valueOf(v2)) instanceof TL_encryptedChat)) {
                    this.dialogsUsers.add(v1);
                }

                if(!Favourite.isFavourite(Long.valueOf(((TL_dialog)v1).id))) {
                    goto label_105;
                }

                this.dialogsFavs.add(v1);
            }
            else if(b.y(ApplicationLoader.applicationContext)) {
                this.dialogsHidden.add(v1);
                goto label_47;
            }

        label_105:
        }
    }

    protected void updateInterfaceWithMessages(long arg2, ArrayList arg4) {
        this.updateInterfaceWithMessages(arg2, arg4, false);
    }

    protected void updateInterfaceWithMessages(long arg17, ArrayList arg19, boolean arg20) {
        MessagesController v0 = this;
        long v1 = arg17;
        ArrayList v3 = arg19;
        if(v3 != null) {
            if(arg19.isEmpty()) {
            }
            else {
                int v6 = 1;
                int v5 = (((int)v1)) == 0 ? 1 : 0;
                int v9 = 0;
                Object v10 = null;
                int v11 = 0;
                int v12 = 0;
                while(v9 < arg19.size()) {
                    Object v13 = v3.get(v9);
                    if(v10 != null) {
                        if(v5 == 0 && ((MessageObject)v13).getId() > ((MessageObject)v10).getId()) {
                            goto label_39;
                        }

                        if((v5 != 0 || ((MessageObject)v13).getId() < 0 && ((MessageObject)v10).getId() < 0) && ((MessageObject)v13).getId() < ((MessageObject)v10).getId()) {
                            goto label_39;
                        }

                        if(((MessageObject)v13).messageOwner.date <= ((MessageObject)v10).messageOwner.date) {
                            goto label_48;
                        }

                        goto label_39;
                    }
                    else {
                    label_39:
                        if(((MessageObject)v13).messageOwner.to_id.channel_id != 0) {
                            v11 = ((MessageObject)v13).messageOwner.to_id.channel_id;
                        }

                        v10 = v13;
                    }

                label_48:
                    if((((MessageObject)v13).isOut()) && !((MessageObject)v13).isSending() && !((MessageObject)v13).isForwarded()) {
                        if(((MessageObject)v13).isNewGif()) {
                            DataQuery.getInstance(v0.currentAccount).addRecentGif(((MessageObject)v13).messageOwner.media.document, ((MessageObject)v13).messageOwner.date);
                        }
                        else if(((MessageObject)v13).isSticker()) {
                            DataQuery.getInstance(v0.currentAccount).addRecentSticker(0, ((MessageObject)v13).messageOwner.media.document, ((MessageObject)v13).messageOwner.date, false);
                        }
                    }

                    if((((MessageObject)v13).isOut()) && (((MessageObject)v13).isSent())) {
                        v12 = 1;
                    }

                    ++v9;
                }

                DataQuery.getInstance(v0.currentAccount).loadReplyMessagesForMessages(v3, v1);
                NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.didReceivedNewMessages, new Object[]{Long.valueOf(arg17), v3});
                if(v10 == null) {
                    return;
                }

                Object v3_1 = v0.dialogs_dict.get(v1);
                long v8 = 0;
                if((((MessageObject)v10).messageOwner.action instanceof TL_messageActionChatMigrateTo)) {
                    if(v3_1 != null) {
                        v0.dialogs.remove(v3_1);
                        v0.dialogsServerOnly.remove(v3_1);
                        v0.dialogsGroupsOnly.remove(v3_1);
                        v0.dialogs_dict.remove(((TL_dialog)v3_1).id);
                        v0.dialogs_read_inbox_max.remove(Long.valueOf(((TL_dialog)v3_1).id));
                        v0.dialogs_read_outbox_max.remove(Long.valueOf(((TL_dialog)v3_1).id));
                        --v0.nextDialogsCacheOffset;
                        v0.dialogMessage.remove(((TL_dialog)v3_1).id);
                        Object v1_1 = v0.dialogMessagesByIds.get(((TL_dialog)v3_1).top_message);
                        v0.dialogMessagesByIds.remove(((TL_dialog)v3_1).top_message);
                        if(v1_1 != null && ((MessageObject)v1_1).messageOwner.random_id != v8) {
                            v0.dialogMessagesByRandomIds.remove(((MessageObject)v1_1).messageOwner.random_id);
                        }

                        ((TL_dialog)v3_1).top_message = 0;
                        NotificationsController.getInstance(v0.currentAccount).removeNotificationsForDialog(((TL_dialog)v3_1).id);
                        NotificationCenter.getInstance(v0.currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch, new Object[0]);
                    }

                    return;
                }

                if(v3_1 != null) {
                    if((((TL_dialog)v3_1).top_message <= 0 || ((MessageObject)v10).getId() <= 0 || ((MessageObject)v10).getId() <= ((TL_dialog)v3_1).top_message) && (((TL_dialog)v3_1).top_message >= 0 || ((MessageObject)v10).getId() >= 0 || ((MessageObject)v10).getId() >= ((TL_dialog)v3_1).top_message) && v0.dialogMessage.indexOfKey(v1) >= 0 && ((TL_dialog)v3_1).top_message >= 0 && ((TL_dialog)v3_1).last_message_date > ((MessageObject)v10).messageOwner.date) {
                    label_221:
                        v6 = 0;
                        goto label_261;
                    }

                    Object v5_1 = v0.dialogMessagesByIds.get(((TL_dialog)v3_1).top_message);
                    v0.dialogMessagesByIds.remove(((TL_dialog)v3_1).top_message);
                    if(v5_1 != null && ((MessageObject)v5_1).messageOwner.random_id != v8) {
                        v0.dialogMessagesByRandomIds.remove(((MessageObject)v5_1).messageOwner.random_id);
                    }

                    ((TL_dialog)v3_1).top_message = ((MessageObject)v10).getId();
                    if(!arg20) {
                        ((TL_dialog)v3_1).last_message_date = ((MessageObject)v10).messageOwner.date;
                    }
                    else {
                        v6 = 0;
                    }

                    v0.dialogMessage.put(v1, v10);
                    if(((MessageObject)v10).messageOwner.to_id.channel_id != 0) {
                        goto label_261;
                    }

                    v0.dialogMessagesByIds.put(((MessageObject)v10).getId(), v10);
                    if(((MessageObject)v10).messageOwner.random_id == v8) {
                        goto label_261;
                    }

                    v0.dialogMessagesByRandomIds.put(((MessageObject)v10).messageOwner.random_id, v10);
                }
                else if(!arg20) {
                    Chat v3_2 = v0.getChat(Integer.valueOf(v11));
                    if(v11 != 0 && v3_2 == null || v3_2 != null && (v3_2.left)) {
                        return;
                    }

                    TL_dialog v4 = new TL_dialog();
                    v4.id = v1;
                    v4.unread_count = 0;
                    v4.top_message = ((MessageObject)v10).getId();
                    v4.last_message_date = ((MessageObject)v10).messageOwner.date;
                    v4.flags = ChatObject.isChannel(v3_2);
                    v0.dialogs_dict.put(v1, v4);
                    v0.dialogs.add(v4);
                    v0.dialogMessage.put(v1, v10);
                    if(((MessageObject)v10).messageOwner.to_id.channel_id == 0) {
                        v0.dialogMessagesByIds.put(((MessageObject)v10).getId(), v10);
                        if(((MessageObject)v10).messageOwner.random_id != v8) {
                            v0.dialogMessagesByRandomIds.put(((MessageObject)v10).messageOwner.random_id, v10);
                        }
                    }

                    ++v0.nextDialogsCacheOffset;
                }
                else {
                    goto label_221;
                }

            label_261:
                if(v6 != 0) {
                    v0.sortDialogs(null);
                }

                if(v12 == 0) {
                    return;
                }

                DataQuery.getInstance(v0.currentAccount).increasePeerRaiting(v1);
            }
        }
    }

    private void updatePrintingStrings() {
        Object[] v8_1;
        int v3_3;
        Integer v3_2;
        String v3_1;
        int v12;
        Iterator v11;
        StringBuilder v6_1;
        User v11_1;
        int v8;
        int v7;
        int v6;
        long v4;
        Object v3;
        LongSparseArray v0 = new LongSparseArray();
        LongSparseArray v1 = new LongSparseArray();
        new ArrayList(this.printingUsers.keySet());
        Iterator v2 = this.printingUsers.entrySet().iterator();
        while(true) {
        label_11:
            if(!v2.hasNext()) {
                goto label_223;
            }

            v3 = v2.next();
            v4 = ((Map$Entry)v3).getKey().longValue();
            v3 = ((Map$Entry)v3).getValue();
            v6 = ((int)v4);
            v7 = 2131625021;
            v8 = 2;
            if(v6 > 0 || v6 == 0 || ((ArrayList)v3).size() == 1) {
                v3 = ((ArrayList)v3).get(0);
                v11_1 = this.getUser(Integer.valueOf(((PrintingUser)v3).userId));
                if(v11_1 == null) {
                    continue;
                }
                else {
                    break;
                }
            }
            else {
                v6_1 = new StringBuilder();
                v11 = ((ArrayList)v3).iterator();
                v12 = 0;
                goto label_31;
            }

            goto label_99;
        }

        if((((PrintingUser)v3).action instanceof TL_sendMessageRecordAudioAction)) {
            v3_1 = v6 < 0 ? LocaleController.formatString("IsRecordingAudio", 2131625013, new Object[]{this.getUserNameForTyping(v11_1)}) : LocaleController.getString("RecordingAudio", 2131625842);
            v0.put(v4, v3_1);
            v3_2 = Integer.valueOf(1);
        }
        else {
            if(((((PrintingUser)v3).action instanceof TL_sendMessageRecordRoundAction)) || ((((PrintingUser)v3).action instanceof TL_sendMessageUploadRoundAction))) {
                v3_1 = v6 < 0 ? LocaleController.formatString("IsRecordingRound", 2131625014, new Object[]{this.getUserNameForTyping(v11_1)}) : LocaleController.getString("RecordingRound", 2131625843);
                v0.put(v4, v3_1);
                v3_3 = 4;
            }
            else {
                if(!(((PrintingUser)v3).action instanceof TL_sendMessageUploadAudioAction)) {
                    if(!(((PrintingUser)v3).action instanceof TL_sendMessageUploadVideoAction)) {
                        if((((PrintingUser)v3).action instanceof TL_sendMessageRecordVideoAction)) {
                        }
                        else if((((PrintingUser)v3).action instanceof TL_sendMessageUploadDocumentAction)) {
                            v3_1 = v6 < 0 ? LocaleController.formatString("IsSendingFile", 2131625016, new Object[]{this.getUserNameForTyping(v11_1)}) : LocaleController.getString("SendingFile", 2131626025);
                            goto label_118;
                        }
                        else if((((PrintingUser)v3).action instanceof TL_sendMessageUploadPhotoAction)) {
                            v3_1 = v6 < 0 ? LocaleController.formatString("IsSendingPhoto", 2131625018, new Object[]{this.getUserNameForTyping(v11_1)}) : LocaleController.getString("SendingPhoto", 2131626028);
                            goto label_118;
                        }
                        else if((((PrintingUser)v3).action instanceof TL_sendMessageGamePlayAction)) {
                            v3_1 = v6 < 0 ? LocaleController.formatString("IsSendingGame", 2131625017, new Object[]{this.getUserNameForTyping(v11_1)}) : LocaleController.getString("SendingGame", 2131626026);
                            v0.put(v4, v3_1);
                            v3_3 = 3;
                            goto label_221;
                        }
                        else {
                            if(v6 < 0) {
                                v3_1 = LocaleController.formatString("IsTypingGroup", v7, new Object[]{this.getUserNameForTyping(v11_1)});
                                goto label_187;
                            }

                            v3_1 = LocaleController.getString("Typing", 2131626259);
                            goto label_187;
                        }
                    }

                    if(v6 < 0) {
                        v3_1 = LocaleController.formatString("IsSendingVideo", 2131625019, new Object[]{this.getUserNameForTyping(v11_1)});
                        goto label_118;
                    }

                    v3_1 = LocaleController.getString("SendingVideoStatus", 2131626030);
                }
                else if(v6 < 0) {
                    v3_1 = LocaleController.formatString("IsSendingAudio", 2131625015, new Object[]{this.getUserNameForTyping(v11_1)});
                }
                else {
                    v3_1 = LocaleController.getString("SendingAudio", 2131626024);
                }

            label_118:
                v0.put(v4, v3_1);
                v3_2 = Integer.valueOf(v8);
                goto label_99;
            }

        label_221:
            v3_2 = Integer.valueOf(v3_3);
            goto label_99;
        label_223:
            this.lastPrintingStringCount = v0.size();
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$9nu4jbFNB9Gwhe-eTYC2YUcvn-U(this, v0, v1));
            return;
            do {
            label_31:
                if(!v11.hasNext()) {
                    break;
                }

                User v13 = this.getUser(Integer.valueOf(v11.next().userId));
                if(v13 != null) {
                    if(v6_1.length() != 0) {
                        v6_1.append(", ");
                    }

                    v6_1.append(this.getUserNameForTyping(v13));
                    ++v12;
                }
            }
            while(v12 != v8);

            if(v6_1.length() == 0) {
                goto label_11;
            }

            if(v12 == 1) {
                v3_1 = "IsTypingGroup";
                v8_1 = new Object[]{v6_1.toString()};
            }
            else if(((ArrayList)v3).size() > v8) {
                String v7_1 = LocaleController.getPluralString("AndMoreTypingGroup", ((ArrayList)v3).size() - v8);
                v3_1 = String.format(v7_1, v6_1.toString(), Integer.valueOf(((ArrayList)v3).size() - v8));
                goto label_187;
            }
            else {
                v3_1 = "AreTypingGroup";
                v7 = 2131624105;
                v8_1 = new Object[]{v6_1.toString()};
            }

            v3_1 = LocaleController.formatString(v3_1, v7, v8_1);
        label_187:
            v0.put(v4, v3_1);
            v3_2 = Integer.valueOf(0);
        }

    label_99:
        v1.put(v4, v3_2);
        goto label_11;
    }

    private boolean updatePrintingUsersWithNewMessages(long arg7, ArrayList arg9) {
        int v2;
        int v1_1;
        long v0 = 0;
        if(Long.compare(arg7, v0) > 0) {
            if(this.printingUsers.get(Long.valueOf(arg7)) != null) {
                this.printingUsers.remove(Long.valueOf(arg7));
                return 1;
            }
        }
        else if(arg7 < v0) {
            ArrayList v0_1 = new ArrayList();
            Iterator v9 = arg9.iterator();
            while(v9.hasNext()) {
                Object v1 = v9.next();
                if(v0_1.contains(Integer.valueOf(((MessageObject)v1).messageOwner.from_id))) {
                    continue;
                }

                v0_1.add(Integer.valueOf(((MessageObject)v1).messageOwner.from_id));
            }

            Object v9_1 = this.printingUsers.get(Long.valueOf(arg7));
            if(v9_1 != null) {
                v1_1 = 0;
                v2 = 0;
                goto label_36;
            }
            else {
                v2 = 0;
                goto label_54;
            label_36:
                while(v1_1 < ((ArrayList)v9_1).size()) {
                    if(v0_1.contains(Integer.valueOf(((ArrayList)v9_1).get(v1_1).userId))) {
                        ((ArrayList)v9_1).remove(v1_1);
                        --v1_1;
                        if(((ArrayList)v9_1).isEmpty()) {
                            this.printingUsers.remove(Long.valueOf(arg7));
                        }

                        v2 = 1;
                    }

                    ++v1_1;
                }
            }

        label_54:
            if(v2 == 0) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public void updateTimerProc() {
        ArrayList v3;
        ArrayList v16;
        Object v12_3;
        ArrayList v4_2;
        long v10_2;
        -$$Lambda$MessagesController$6cgeYZHjs9rkp-FYyoUy_YvXud4 v11;
        ConnectionsManager v10;
        TL_account_updateStatus v4;
        int v10_1;
        MessagesController v0 = this;
        long v1 = System.currentTimeMillis();
        v0.checkDeletingTask(false);
        this.checkReadTasks();
        long v5 = 1000;
        long v7 = 0;
        if(UserConfig.getInstance(v0.currentAccount).isClientActivated()) {
            if(ConnectionsManager.getInstance(v0.currentAccount).getPauseTime() != v7 || !ApplicationLoader.isScreenOn || (ApplicationLoader.mainInterfacePausedStageQueue)) {
                v10_1 = 2;
                if(v0.statusSettingState == v10_1) {
                    goto label_83;
                }

                if(v0.offlineSent) {
                    goto label_83;
                }

                if(Math.abs(System.currentTimeMillis() - ConnectionsManager.getInstance(v0.currentAccount).getPauseTime()) < 2000) {
                    goto label_83;
                }

                v0.statusSettingState = v10_1;
                if(v0.statusRequest != 0) {
                    ConnectionsManager.getInstance(v0.currentAccount).cancelRequest(v0.statusRequest, true);
                }

                v4 = new TL_account_updateStatus();
                v4.offline = true;
                v10 = ConnectionsManager.getInstance(v0.currentAccount);
                -$$Lambda$MessagesController$BklYik2de1nT-vwwvsyix2QYV1k v11_1 = new -$$Lambda$MessagesController$BklYik2de1nT-vwwvsyix2QYV1k(v0);
            label_81:
                v0.statusRequest = v10.sendRequest(((TLObject)v4), ((RequestDelegate)v11));
            }
            else if(ApplicationLoader.mainInterfacePausedStageQueueTime != v7 && Math.abs(ApplicationLoader.mainInterfacePausedStageQueueTime - System.currentTimeMillis()) > v5 && v0.statusSettingState != 1) {
                if(v0.lastStatusUpdateTime != v7 && Math.abs(System.currentTimeMillis() - v0.lastStatusUpdateTime) < 55000 && !v0.offlineSent) {
                    goto label_83;
                }

                v0.statusSettingState = 1;
                if(v0.statusRequest != 0) {
                    ConnectionsManager.getInstance(v0.currentAccount).cancelRequest(v0.statusRequest, true);
                }

                v4 = new TL_account_updateStatus();
                v4.offline = false;
                v10 = ConnectionsManager.getInstance(v0.currentAccount);
                v11 = new -$$Lambda$MessagesController$6cgeYZHjs9rkp-FYyoUy_YvXud4(v0);
                goto label_81;
            }

        label_83:
            v10_2 = 1500;
            if(v0.updatesQueueChannels.size() != 0) {
                int v4_1;
                for(v4_1 = 0; v4_1 < v0.updatesQueueChannels.size(); ++v4_1) {
                    int v12 = v0.updatesQueueChannels.keyAt(v4_1);
                    if(v0.updatesStartWaitTimeChannels.valueAt(v4_1) + v10_2 < v1) {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("QUEUE CHANNEL " + v12 + " UPDATES WAIT TIMEOUT - CHECK QUEUE");
                        }

                        v0.processChannelsUpdatesQueue(v12, 0);
                    }
                }
            }

            for(v4_1 = 0; v4_1 < 3; ++v4_1) {
                if(v0.getUpdatesStartTime(v4_1) != v7 && v0.getUpdatesStartTime(v4_1) + v10_2 < v1) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d(v4_1 + " QUEUE UPDATES WAIT TIMEOUT - CHECK QUEUE");
                    }

                    v0.processUpdatesQueue(v4_1, 0);
                }
            }
        }

        if(v0.channelViewsToSend.size() != 0 && Math.abs(System.currentTimeMillis() - v0.lastViewsCheckTime) >= 5000) {
            v0.lastViewsCheckTime = System.currentTimeMillis();
            for(v4_1 = 0; v4_1 < v0.channelViewsToSend.size(); ++v4_1) {
                v10_1 = v0.channelViewsToSend.keyAt(v4_1);
                TL_messages_getMessagesViews v11_2 = new TL_messages_getMessagesViews();
                v11_2.peer = v0.getInputPeer(v10_1);
                v11_2.id = v0.channelViewsToSend.valueAt(v4_1);
                boolean v12_2 = v4_1 == 0 ? true : false;
                v11_2.increment = v12_2;
                ConnectionsManager.getInstance(v0.currentAccount).sendRequest(((TLObject)v11_2), new -$$Lambda$MessagesController$JuIdkrNH_ceYkAhSZcRLZNMAOHg(v0, v10_1, v11_2));
            }

            v0.channelViewsToSend.clear();
        }

        if(!v0.onlinePrivacy.isEmpty()) {
            v4_2 = null;
            v10_1 = ConnectionsManager.getInstance(v0.currentAccount).getCurrentTime();
            Iterator v11_3 = v0.onlinePrivacy.entrySet().iterator();
            while(v11_3.hasNext()) {
                v12_3 = v11_3.next();
                if(((Map$Entry)v12_3).getValue().intValue() >= v10_1 - 30) {
                    continue;
                }

                if(v4_2 == null) {
                    v4_2 = new ArrayList();
                }

                v4_2.add(((Map$Entry)v12_3).getKey());
            }

            if(v4_2 == null) {
                goto label_203;
            }

            Iterator v4_3 = v4_2.iterator();
            while(v4_3.hasNext()) {
                v0.onlinePrivacy.remove(v4_3.next());
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$7JzKRWeotzC9a1WMQdYsuMamZwE(v0));
        }

    label_203:
        if(v0.shortPollChannels.size() != 0) {
            for(v4_1 = 0; v4_1 < v0.shortPollChannels.size(); ++v4_1) {
                v10_1 = v0.shortPollChannels.keyAt(v4_1);
                if((((long)v0.shortPollChannels.valueAt(v4_1))) < System.currentTimeMillis() / v5) {
                    v0.shortPollChannels.delete(v10_1);
                    if(v0.needShortPollChannels.indexOfKey(v10_1) >= 0) {
                        v0.getChannelDifference(v10_1);
                    }
                }
            }
        }

        if(!v0.printingUsers.isEmpty() || v0.lastPrintingStringCount != v0.printingUsers.size()) {
            v4_2 = new ArrayList(v0.printingUsers.keySet());
            int v5_1 = 0;
            int v6 = 0;
            while(v5_1 < v4_2.size()) {
                v10_2 = v4_2.get(v5_1).longValue();
                v12_3 = v0.printingUsers.get(Long.valueOf(v10_2));
                if(v12_3 != null) {
                    int v13_1 = v6;
                    v6 = 0;
                    while(v6 < ((ArrayList)v12_3).size()) {
                        Object v14 = ((ArrayList)v12_3).get(v6);
                        int v15 = (((PrintingUser)v14).action instanceof TL_sendMessageGamePlayAction) ? 30000 : 5900;
                        v16 = v4_2;
                        if(((PrintingUser)v14).lastTime + (((long)v15)) < v1) {
                            ((ArrayList)v12_3).remove(v14);
                            --v6;
                            v13_1 = 1;
                        }

                        ++v6;
                        v4_2 = v16;
                    }

                    v16 = v4_2;
                    v6 = v13_1;
                }
                else {
                    v16 = v4_2;
                }

                if(v12_3 == null || (((ArrayList)v12_3).isEmpty())) {
                    v0.printingUsers.remove(Long.valueOf(v10_2));
                    v3 = v16;
                    v3.remove(v5_1);
                    --v5_1;
                }
                else {
                    v3 = v16;
                }

                ++v5_1;
                v4_2 = v3;
            }

            this.updatePrintingStrings();
            if(v6 == 0) {
                goto label_297;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesController$W1vw_WrYvsEsnhckDyMlWS-CZ1c(v0));
        }

    label_297:
        long v4_4 = 60;
        if(Theme.selectedAutoNightType == 1 && Math.abs(v1 - MessagesController.lastThemeCheckTime) >= v4_4) {
            AndroidUtilities.runOnUIThread(v0.themeCheckRunnable);
            MessagesController.lastThemeCheckTime = v1;
        }

        if(UserConfig.getInstance(v0.currentAccount).savedPasswordHash != null && Math.abs(v1 - MessagesController.lastPasswordCheckTime) >= v4_4) {
            AndroidUtilities.runOnUIThread(v0.passwordCheckRunnable);
            MessagesController.lastPasswordCheckTime = v1;
        }

        if(v0.lastPushRegisterSendTime != 0 && Math.abs(SystemClock.elapsedRealtime() - v0.lastPushRegisterSendTime) >= 10800000) {
            GcmInstanceIDListenerService.sendRegistrationToServer(SharedConfig.pushString);
        }

        LocationController.getInstance(v0.currentAccount).update();
        v0.checkProxyInfoInternal(false);
        this.checkTosUpdate();
    }

    public void uploadAndApplyUserAvatar(PhotoSize arg5) {
        if(arg5 != null) {
            this.uploadingAvatar = FileLoader.getDirectory(4) + "/" + arg5.location.volume_id + "_" + arg5.location.local_id + ".jpg";
            FileLoader.getInstance(this.currentAccount).uploadFile(this.uploadingAvatar, false, true, 16777216);
        }
    }
}

