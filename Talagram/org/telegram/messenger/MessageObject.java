package org.telegram.messenger;

import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.text.Layout$Alignment;
import android.text.Spannable$Factory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout$Builder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Base64;
import android.util.SparseArray;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.a.b;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$BotInlineResult;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$DecryptedMessageAction;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$InputStickerSet;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$MessageAction;
import org.telegram.tgnet.TLRPC$MessageEntity;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.tgnet.TLRPC$PageBlock;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$Photo;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEvent;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionChangeAbout;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionChangePhoto;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionChangeStickerSet;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionChangeTitle;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionChangeUsername;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionDeleteMessage;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionEditMessage;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionParticipantInvite;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionParticipantJoin;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionParticipantLeave;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionParticipantToggleAdmin;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionParticipantToggleBan;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionToggleInvites;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionTogglePreHistoryHidden;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionToggleSignatures;
import org.telegram.tgnet.TLRPC$TL_channelAdminLogEventActionUpdatePinned;
import org.telegram.tgnet.TLRPC$TL_channelAdminRights;
import org.telegram.tgnet.TLRPC$TL_channelBannedRights;
import org.telegram.tgnet.TLRPC$TL_chatPhotoEmpty;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionScreenshotMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAnimated;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAudio;
import org.telegram.tgnet.TLRPC$TL_documentAttributeImageSize;
import org.telegram.tgnet.TLRPC$TL_documentAttributeSticker;
import org.telegram.tgnet.TLRPC$TL_documentAttributeVideo;
import org.telegram.tgnet.TLRPC$TL_documentEmpty;
import org.telegram.tgnet.TLRPC$TL_game;
import org.telegram.tgnet.TLRPC$TL_inputMessageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetEmpty;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonBuy;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonRow;
import org.telegram.tgnet.TLRPC$TL_message;
import org.telegram.tgnet.TLRPC$TL_messageActionBotAllowed;
import org.telegram.tgnet.TLRPC$TL_messageActionChannelCreate;
import org.telegram.tgnet.TLRPC$TL_messageActionChannelMigrateFrom;
import org.telegram.tgnet.TLRPC$TL_messageActionChatAddUser;
import org.telegram.tgnet.TLRPC$TL_messageActionChatCreate;
import org.telegram.tgnet.TLRPC$TL_messageActionChatDeletePhoto;
import org.telegram.tgnet.TLRPC$TL_messageActionChatDeleteUser;
import org.telegram.tgnet.TLRPC$TL_messageActionChatEditPhoto;
import org.telegram.tgnet.TLRPC$TL_messageActionChatEditTitle;
import org.telegram.tgnet.TLRPC$TL_messageActionChatJoinedByLink;
import org.telegram.tgnet.TLRPC$TL_messageActionChatMigrateTo;
import org.telegram.tgnet.TLRPC$TL_messageActionCreatedBroadcastList;
import org.telegram.tgnet.TLRPC$TL_messageActionCustomAction;
import org.telegram.tgnet.TLRPC$TL_messageActionEmpty;
import org.telegram.tgnet.TLRPC$TL_messageActionGameScore;
import org.telegram.tgnet.TLRPC$TL_messageActionHistoryClear;
import org.telegram.tgnet.TLRPC$TL_messageActionLoginUnknownLocation;
import org.telegram.tgnet.TLRPC$TL_messageActionPaymentSent;
import org.telegram.tgnet.TLRPC$TL_messageActionPhoneCall;
import org.telegram.tgnet.TLRPC$TL_messageActionPinMessage;
import org.telegram.tgnet.TLRPC$TL_messageActionScreenshotTaken;
import org.telegram.tgnet.TLRPC$TL_messageActionSecureValuesSent;
import org.telegram.tgnet.TLRPC$TL_messageActionTTLChange;
import org.telegram.tgnet.TLRPC$TL_messageActionUserJoined;
import org.telegram.tgnet.TLRPC$TL_messageActionUserUpdatedPhoto;
import org.telegram.tgnet.TLRPC$TL_messageEmpty;
import org.telegram.tgnet.TLRPC$TL_messageEncryptedAction;
import org.telegram.tgnet.TLRPC$TL_messageEntityBold;
import org.telegram.tgnet.TLRPC$TL_messageEntityBotCommand;
import org.telegram.tgnet.TLRPC$TL_messageEntityCashtag;
import org.telegram.tgnet.TLRPC$TL_messageEntityCode;
import org.telegram.tgnet.TLRPC$TL_messageEntityEmail;
import org.telegram.tgnet.TLRPC$TL_messageEntityHashtag;
import org.telegram.tgnet.TLRPC$TL_messageEntityItalic;
import org.telegram.tgnet.TLRPC$TL_messageEntityMention;
import org.telegram.tgnet.TLRPC$TL_messageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_messageEntityPhone;
import org.telegram.tgnet.TLRPC$TL_messageEntityPre;
import org.telegram.tgnet.TLRPC$TL_messageEntityTextUrl;
import org.telegram.tgnet.TLRPC$TL_messageEntityUrl;
import org.telegram.tgnet.TLRPC$TL_messageForwarded_old2;
import org.telegram.tgnet.TLRPC$TL_messageForwarded_old;
import org.telegram.tgnet.TLRPC$TL_messageMediaContact;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument_layer68;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument_layer74;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument_old;
import org.telegram.tgnet.TLRPC$TL_messageMediaEmpty;
import org.telegram.tgnet.TLRPC$TL_messageMediaGame;
import org.telegram.tgnet.TLRPC$TL_messageMediaGeo;
import org.telegram.tgnet.TLRPC$TL_messageMediaGeoLive;
import org.telegram.tgnet.TLRPC$TL_messageMediaInvoice;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto_layer68;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto_layer74;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto_old;
import org.telegram.tgnet.TLRPC$TL_messageMediaUnsupported;
import org.telegram.tgnet.TLRPC$TL_messageMediaVenue;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_messageService;
import org.telegram.tgnet.TLRPC$TL_message_old2;
import org.telegram.tgnet.TLRPC$TL_message_old3;
import org.telegram.tgnet.TLRPC$TL_message_old4;
import org.telegram.tgnet.TLRPC$TL_message_old;
import org.telegram.tgnet.TLRPC$TL_message_secret;
import org.telegram.tgnet.TLRPC$TL_pageBlockCollage;
import org.telegram.tgnet.TLRPC$TL_pageBlockPhoto;
import org.telegram.tgnet.TLRPC$TL_pageBlockSlideshow;
import org.telegram.tgnet.TLRPC$TL_pageBlockVideo;
import org.telegram.tgnet.TLRPC$TL_peerChannel;
import org.telegram.tgnet.TLRPC$TL_phoneCallDiscardReasonBusy;
import org.telegram.tgnet.TLRPC$TL_phoneCallDiscardReasonMissed;
import org.telegram.tgnet.TLRPC$TL_photo;
import org.telegram.tgnet.TLRPC$TL_photoEmpty;
import org.telegram.tgnet.TLRPC$TL_photoSize;
import org.telegram.tgnet.TLRPC$TL_photoSizeEmpty;
import org.telegram.tgnet.TLRPC$TL_replyInlineMarkup;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeAddress;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeBankStatement;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeDriverLicense;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeEmail;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeIdentityCard;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeInternalPassport;
import org.telegram.tgnet.TLRPC$TL_secureValueTypePassport;
import org.telegram.tgnet.TLRPC$TL_secureValueTypePassportRegistration;
import org.telegram.tgnet.TLRPC$TL_secureValueTypePersonalDetails;
import org.telegram.tgnet.TLRPC$TL_secureValueTypePhone;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeRentalAgreement;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeTemporaryRegistration;
import org.telegram.tgnet.TLRPC$TL_secureValueTypeUtilityBill;
import org.telegram.tgnet.TLRPC$TL_webPage;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$WebDocument;
import org.telegram.tgnet.TLRPC$WebPage;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanBotCommand;
import org.telegram.ui.Components.URLSpanBrowser;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanNoUnderlineBold;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;

public class MessageObject {
    public class GroupedMessagePosition {
        public float aspectRatio;
        public boolean edge;
        public int flags;
        public boolean last;
        public int leftSpanOffset;
        public byte maxX;
        public byte maxY;
        public byte minX;
        public byte minY;
        public float ph;
        public int pw;
        public float[] siblingHeights;
        public int spanSize;

        public GroupedMessagePosition() {
            super();
        }

        public void set(int arg1, int arg2, int arg3, int arg4, int arg5, float arg6, int arg7) {
            this.minX = ((byte)arg1);
            this.maxX = ((byte)arg2);
            this.minY = ((byte)arg3);
            this.maxY = ((byte)arg4);
            this.pw = arg5;
            this.spanSize = arg5;
            this.ph = arg6;
            this.flags = ((byte)arg7);
        }
    }

    public class GroupedMessages {
        class MessageGroupedLayoutAttempt {
            public float[] heights;
            public int[] lineCounts;

            public MessageGroupedLayoutAttempt(GroupedMessages arg3, int arg4, int arg5, float arg6, float arg7) {
                GroupedMessages.this = arg3;
                super();
                this.lineCounts = new int[]{arg4, arg5};
                this.heights = new float[]{arg6, arg7};
            }

            public MessageGroupedLayoutAttempt(GroupedMessages arg3, int arg4, int arg5, int arg6, float arg7, float arg8, float arg9) {
                GroupedMessages.this = arg3;
                super();
                this.lineCounts = new int[]{arg4, arg5, arg6};
                this.heights = new float[]{arg7, arg8, arg9};
            }

            public MessageGroupedLayoutAttempt(GroupedMessages arg3, int arg4, int arg5, int arg6, int arg7, float arg8, float arg9, float arg10, float arg11) {
                GroupedMessages.this = arg3;
                super();
                this.lineCounts = new int[]{arg4, arg5, arg6, arg7};
                this.heights = new float[]{arg8, arg9, arg10, arg11};
            }
        }

        private int firstSpanAdditionalSize;
        public long groupId;
        public boolean hasSibling;
        private int maxSizeWidth;
        public ArrayList messages;
        public ArrayList posArray;
        public HashMap positions;

        public GroupedMessages() {
            super();
            this.messages = new ArrayList();
            this.posArray = new ArrayList();
            this.positions = new HashMap();
            this.maxSizeWidth = 800;
            this.firstSpanAdditionalSize = 200;
        }

        public void calculate() {
            int v36;
            Object v9_4;
            int v8_3;
            int v18_1;
            int v17;
            int v13_1;
            int v27;
            int v28;
            float v3_3;
            Object v8_2;
            float v5_2;
            int v23;
            Object v18;
            int v25;
            int v20;
            int v19;
            float v24;
            int v22;
            int v21;
            int v0_3;
            float v0_2;
            boolean v26;
            Object v3_2;
            Object v2_1;
            String v7_3;
            float v7_2;
            int v7;
            Object v6;
            float v16;
            GroupedMessages v10 = this;
            v10.posArray.clear();
            v10.positions.clear();
            int v11 = v10.messages.size();
            int v12 = 1;
            if(v11 <= 1) {
                return;
            }

            float v13 = 814f;
            StringBuilder v0 = new StringBuilder();
            v10.hasSibling = false;
            int v2 = 0;
            int v3 = 0;
            float v4 = 1f;
            int v5 = 0;
            boolean v15 = false;
            while(true) {
                v16 = 1.2f;
                if(v2 >= v11) {
                    break;
                }

                v6 = v10.messages.get(v2);
                if(v2 == 0) {
                    boolean v3_1 = ((MessageObject)v6).isOutOwner();
                    if(!v3_1) {
                        if(((MessageObject)v6).messageOwner.fwd_from == null || ((MessageObject)v6).messageOwner.fwd_from.saved_from_peer == null) {
                            if(((MessageObject)v6).messageOwner.from_id <= 0) {
                                goto label_55;
                            }
                            else if(((MessageObject)v6).messageOwner.to_id.channel_id == 0 && ((MessageObject)v6).messageOwner.to_id.chat_id == 0 && !(((MessageObject)v6).messageOwner.media instanceof TL_messageMediaGame)) {
                                if((((MessageObject)v6).messageOwner.media instanceof TL_messageMediaInvoice)) {
                                    goto label_53;
                                }

                                goto label_55;
                            }
                        }

                    label_53:
                        v7 = 1;
                    }
                    else {
                    label_55:
                        v7 = 0;
                    }

                    v15 = v3_1;
                    v3 = v7;
                }

                PhotoSize v7_1 = FileLoader.getClosestPhotoSizeWithSize(((MessageObject)v6).photoThumbs, AndroidUtilities.getPhotoSize());
                GroupedMessagePosition v8 = new GroupedMessagePosition();
                boolean v9 = v2 == v11 - 1 ? true : false;
                v8.last = v9;
                v7_2 = v7_1 == null ? 1f : (((float)v7_1.w)) / (((float)v7_1.h));
                v8.aspectRatio = v7_2;
                if(v8.aspectRatio > v16) {
                    v7_3 = "w";
                }
                else if(v8.aspectRatio < 0.8f) {
                    v7_3 = "n";
                }
                else {
                    v7_3 = "q";
                }

                v0.append(v7_3);
                v4 += v8.aspectRatio;
                if(v8.aspectRatio > 2f) {
                    v5 = 1;
                }

                v10.positions.put(v6, v8);
                v10.posArray.add(v8);
                ++v2;
            }

            if(v3 != 0) {
                v10.maxSizeWidth += -50;
                v10.firstSpanAdditionalSize += 50;
            }

            v3 = AndroidUtilities.dp(120f);
            int v9_1 = ((int)((((float)AndroidUtilities.dp(120f))) / ((((float)Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y))) / (((float)v10.maxSizeWidth)))));
            v2 = ((int)((((float)AndroidUtilities.dp(40f))) / ((((float)Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y))) / (((float)v10.maxSizeWidth)))));
            float v6_1 = (((float)v10.maxSizeWidth)) / v13;
            float v8_1 = v4 / (((float)v11));
            v7 = 4;
            int v4_1 = 3;
            int v1 = 2;
            if(v5 == 0) {
                if(v11 != v1 && v11 != v4_1 && v11 != v7) {
                    goto label_621;
                }

                v7_2 = 407f;
                if(v11 == v1) {
                    v2_1 = v10.posArray.get(0);
                    v3_2 = v10.posArray.get(1);
                    String v0_1 = v0.toString();
                    if(v0_1.equals("ww")) {
                        v26 = v15;
                        double v5_1 = ((double)v6_1);
                        Double.isNaN(v5_1);
                        if((((double)v8_1)) <= v5_1 * 1.4) {
                            goto label_206;
                        }
                        else if((((double)(((GroupedMessagePosition)v2_1).aspectRatio - ((GroupedMessagePosition)v3_2).aspectRatio))) < 0.2) {
                            v0_2 = (((float)Math.round(Math.min((((float)v10.maxSizeWidth)) / ((GroupedMessagePosition)v2_1).aspectRatio, Math.min((((float)v10.maxSizeWidth)) / ((GroupedMessagePosition)v3_2).aspectRatio, v7_2))))) / v13;
                            v2_1.set(0, 0, 0, 0, v10.maxSizeWidth, v0_2, 7);
                            v3_2.set(0, 0, 1, 1, v10.maxSizeWidth, v0_2, 11);
                            v0_3 = 0;
                        }
                        else {
                            goto label_206;
                        }
                    }
                    else {
                        v26 = v15;
                    label_206:
                        if((v0_1.equals("ww")) || (v0_1.equals("qq"))) {
                            v0_3 = v10.maxSizeWidth / v1;
                            v4 = ((float)v0_3);
                            v21 = 0;
                            v22 = 0;
                            v23 = v0_3;
                            v24 = (((float)Math.round(Math.min(v4 / ((GroupedMessagePosition)v2_1).aspectRatio, Math.min(v4 / ((GroupedMessagePosition)v3_2).aspectRatio, v13))))) / v13;
                            v2_1.set(0, 0, 0, 0, v23, v24, 13);
                            v19 = 1;
                            v20 = 1;
                            v25 = 14;
                            v18 = v3_2;
                        }
                        else {
                            v0_3 = ((int)Math.max((((float)v10.maxSizeWidth)) * 0.4f, ((float)Math.round((((float)v10.maxSizeWidth)) / ((GroupedMessagePosition)v2_1).aspectRatio / (1f / ((GroupedMessagePosition)v2_1).aspectRatio + 1f / ((GroupedMessagePosition)v3_2).aspectRatio)))));
                            v4_1 = v10.maxSizeWidth - v0_3;
                            if(v4_1 < v9_1) {
                                v0_3 -= v9_1 - v4_1;
                                v4_1 = v9_1;
                            }

                            v21 = 0;
                            v22 = 0;
                            v24 = Math.min(v13, ((float)Math.round(Math.min((((float)v4_1)) / ((GroupedMessagePosition)v2_1).aspectRatio, (((float)v0_3)) / ((GroupedMessagePosition)v3_2).aspectRatio)))) / v13;
                            v2_1.set(0, 0, 0, 0, v4_1, v24, 13);
                            v19 = 1;
                            v20 = 1;
                            v25 = 14;
                            v18 = v3_2;
                            v23 = v0_3;
                        }

                        ((GroupedMessagePosition)v18).set(v19, v20, v21, v22, v23, v24, v25);
                        v0_3 = 1;
                    }

                    v12 = v0_3;
                    goto label_292;
                }
                else {
                    v26 = v15;
                    v5_2 = 537.240051f;
                    if(v11 == v4_1) {
                        v3_2 = v10.posArray.get(0);
                        v6 = v10.posArray.get(1);
                        v8_2 = v10.posArray.get(v1);
                        if(v0.charAt(0) == 110) {
                            v0_2 = Math.min(v7_2, ((float)Math.round(((GroupedMessagePosition)v6).aspectRatio * (((float)v10.maxSizeWidth)) / (((GroupedMessagePosition)v8_2).aspectRatio + ((GroupedMessagePosition)v6).aspectRatio))));
                            v4 = v13 - v0_2;
                            v5 = ((int)Math.max(((float)v9_1), Math.min((((float)v10.maxSizeWidth)) * 0.5f, ((float)Math.round(Math.min(((GroupedMessagePosition)v8_2).aspectRatio * v0_2, ((GroupedMessagePosition)v6).aspectRatio * v4))))));
                            v2 = Math.round(Math.min(((GroupedMessagePosition)v3_2).aspectRatio * v13 + (((float)v2)), ((float)(v10.maxSizeWidth - v5))));
                            v3_2.set(0, 0, 0, 1, v2, 1f, 13);
                            v4 /= v13;
                            v6.set(1, 1, 0, 0, v5, v4, 6);
                            v0_2 /= v13;
                            v8_2.set(0, 1, 1, 1, v5, v0_2, 10);
                            ((GroupedMessagePosition)v8_2).spanSize = v10.maxSizeWidth;
                            float[] v7_4 = new float[v1];
                            v7_4[0] = v0_2;
                            v7_4[1] = v4;
                            ((GroupedMessagePosition)v3_2).siblingHeights = v7_4;
                            if(v26) {
                                ((GroupedMessagePosition)v3_2).spanSize = v10.maxSizeWidth - v5;
                            }
                            else {
                                ((GroupedMessagePosition)v6).spanSize = v10.maxSizeWidth - v2;
                                ((GroupedMessagePosition)v8_2).leftSpanOffset = v2;
                            }

                            v10.hasSibling = true;
                        }
                        else {
                            v0_2 = (((float)Math.round(Math.min((((float)v10.maxSizeWidth)) / ((GroupedMessagePosition)v3_2).aspectRatio, v5_2)))) / v13;
                            v3_2.set(0, 1, 0, 0, v10.maxSizeWidth, v0_2, 7);
                            v2 = v10.maxSizeWidth / v1;
                            v3_3 = ((float)v2);
                            float v20_1 = Math.min(v13 - v0_2, ((float)Math.round(Math.min(v3_3 / ((GroupedMessagePosition)v6).aspectRatio, v3_3 / ((GroupedMessagePosition)v8_2).aspectRatio)))) / v13;
                            v6.set(0, 0, 1, 1, v2, v20_1, 9);
                            v8_2.set(1, 1, 1, 1, v2, v20_1, 10);
                        }

                    label_292:
                        v28 = v11;
                        goto label_619;
                    }

                    if(v11 == 4) {
                        v6 = v10.posArray.get(0);
                        v8_2 = v10.posArray.get(1);
                        Object v14 = v10.posArray.get(v1);
                        Object v15_1 = v10.posArray.get(v4_1);
                        float v1_1 = 0.33f;
                        if(v0.charAt(0) == 119) {
                            v0_2 = (((float)Math.round(Math.min((((float)v10.maxSizeWidth)) / ((GroupedMessagePosition)v6).aspectRatio, v5_2)))) / v13;
                            v6.set(0, 2, 0, 0, v10.maxSizeWidth, v0_2, 7);
                            float v2_2 = ((float)Math.round((((float)v10.maxSizeWidth)) / (((GroupedMessagePosition)v8_2).aspectRatio + ((GroupedMessagePosition)v14).aspectRatio + ((GroupedMessagePosition)v15_1).aspectRatio)));
                            v3_3 = ((float)v9_1);
                            v4_1 = ((int)Math.max(v3_3, Math.min((((float)v10.maxSizeWidth)) * 0.4f, ((GroupedMessagePosition)v8_2).aspectRatio * v2_2)));
                            v1 = ((int)Math.max(Math.max(v3_3, (((float)v10.maxSizeWidth)) * v1_1), ((GroupedMessagePosition)v15_1).aspectRatio * v2_2));
                            v3 = v10.maxSizeWidth - v4_1 - v1;
                            v24 = Math.min(v13 - v0_2, v2_2) / v13;
                            v8_2.set(0, 0, 1, 1, v4_1, v24, 9);
                            v14.set(1, 1, 1, 1, v3, v24, 8);
                            v15_1.set(2, 2, 1, 1, v1, v24, 10);
                            v27 = 2;
                        }
                        else {
                            v0_3 = Math.max(v9_1, Math.round(v13 / (1f / ((GroupedMessagePosition)v8_2).aspectRatio + 1f / ((GroupedMessagePosition)v14).aspectRatio + 1f / v10.posArray.get(v4_1).aspectRatio)));
                            v3_3 = ((float)v3);
                            v5_2 = ((float)v0_3);
                            v7_2 = Math.min(v1_1, Math.max(v3_3, v5_2 / ((GroupedMessagePosition)v8_2).aspectRatio) / v13);
                            v1_1 = Math.min(v1_1, Math.max(v3_3, v5_2 / ((GroupedMessagePosition)v14).aspectRatio) / v13);
                            v3_3 = 1f - v7_2 - v1_1;
                            v2 = Math.round(Math.min(v13 * ((GroupedMessagePosition)v6).aspectRatio + (((float)v2)), ((float)(v10.maxSizeWidth - v0_3))));
                            v6.set(0, 0, 0, 2, v2, v7_2 + v1_1 + v3_3, 13);
                            v8_2.set(1, 1, 0, 0, v0_3, v7_2, 6);
                            v14.set(0, 1, 1, 1, v0_3, v1_1, 2);
                            ((GroupedMessagePosition)v14).spanSize = v10.maxSizeWidth;
                            v15_1.set(0, 1, 2, 2, v0_3, v3_3, 10);
                            ((GroupedMessagePosition)v15_1).spanSize = v10.maxSizeWidth;
                            if(v26) {
                                ((GroupedMessagePosition)v6).spanSize = v10.maxSizeWidth - v0_3;
                            }
                            else {
                                ((GroupedMessagePosition)v8_2).spanSize = v10.maxSizeWidth - v2;
                                ((GroupedMessagePosition)v14).leftSpanOffset = v2;
                                ((GroupedMessagePosition)v15_1).leftSpanOffset = v2;
                            }

                            float[] v0_4 = new float[v4_1];
                            v0_4[0] = v7_2;
                            v0_4[1] = v1_1;
                            v0_4[2] = v3_3;
                            ((GroupedMessagePosition)v6).siblingHeights = v0_4;
                            v10.hasSibling = true;
                            v27 = 1;
                        }

                        v28 = v11;
                        v12 = v27;
                        goto label_619;
                    }

                    v28 = v11;
                    v12 = 0;
                }

            label_619:
                v13_1 = 0;
            }
            else {
            label_621:
                v26 = v15;
                float[] v14_1 = new float[v10.posArray.size()];
                for(v0_3 = 0; v0_3 < v11; ++v0_3) {
                    v14_1[v0_3] = v8_1 > 1.1f ? Math.max(1f, v10.posArray.get(v0_3).aspectRatio) : Math.min(1f, v10.posArray.get(v0_3).aspectRatio);
                    v14_1[v0_3] = Math.max(0.66667f, Math.min(1.7f, v14_1[v0_3]));
                }

                ArrayList v15_2 = new ArrayList();
                int v6_2 = 1;
                while(v6_2 < v14_1.length) {
                    v3 = v14_1.length - v6_2;
                    if(v6_2 <= v4_1) {
                        if(v3 > v4_1) {
                        }
                        else {
                            v15_2.add(new MessageGroupedLayoutAttempt(this, v6_2, v3, v10.multiHeight(v14_1, 0, v6_2), v10.multiHeight(v14_1, v6_2, v14_1.length)));
                        }
                    }

                    ++v6_2;
                    v4_1 = 3;
                }

                v13_1 = 3;
                v7 = 1;
                while(v7 < v14_1.length - v12) {
                    v6_2 = 1;
                    while(v6_2 < v14_1.length - v7) {
                        v4_1 = v14_1.length - v7 - v6_2;
                        if(v7 <= v13_1) {
                            v0_3 = v8_1 < 0.85f ? 4 : 3;
                            if(v6_2 > v0_3) {
                                goto label_730;
                            }

                            if(v4_1 > v13_1) {
                                goto label_730;
                            }

                            v0_3 = v7 + v6_2;
                            v17 = v6_2;
                            v18_1 = v7;
                            v15_2.add(new MessageGroupedLayoutAttempt(this, v7, v6_2, v4_1, v10.multiHeight(v14_1, 0, v7), v10.multiHeight(v14_1, v7, v0_3), v10.multiHeight(v14_1, v0_3, v14_1.length)));
                        }
                        else {
                        label_730:
                            v17 = v6_2;
                            v18_1 = v7;
                        }

                        v6_2 = v17 + 1;
                        v7 = v18_1;
                    }

                    ++v7;
                    v12 = 1;
                }

                v12 = 1;
                while(v12 < v14_1.length - 2) {
                    v8_3 = 1;
                    while(v8_3 < v14_1.length - v12) {
                        v7 = 1;
                        while(v7 < v14_1.length - v12 - v8_3) {
                            v5 = v14_1.length - v12 - v8_3 - v7;
                            if(v12 > v13_1 || v8_3 > v13_1 || v7 > v13_1 || v5 > v13_1) {
                                v17 = v7;
                                v18_1 = v8_3;
                                v28 = v11;
                                v11 = v9_1;
                            }
                            else {
                                v0_3 = v12 + v8_3;
                                v1 = v0_3 + v7;
                                v17 = v7;
                                v18_1 = v8_3;
                                v28 = v11;
                                v11 = v9_1;
                                v15_2.add(new MessageGroupedLayoutAttempt(this, v12, v8_3, v7, v5, v10.multiHeight(v14_1, 0, v12), v10.multiHeight(v14_1, v12, v0_3), v10.multiHeight(v14_1, v0_3, v1), v10.multiHeight(v14_1, v1, v14_1.length)));
                            }

                            v7 = v17 + 1;
                            v9_1 = v11;
                            v8_3 = v18_1;
                            v11 = v28;
                            v13_1 = 3;
                        }

                        ++v8_3;
                        v11 = v11;
                        v13_1 = 3;
                    }

                    ++v12;
                    v11 = v11;
                    v13_1 = 3;
                }

                v28 = v11;
                v11 = v9_1;
                v0_2 = ((float)(v10.maxSizeWidth / 3 * 4));
                v3 = 0;
                Object v4_2 = null;
                v5_2 = 0f;
                while(v3 < v15_2.size()) {
                    v6 = v15_2.get(v3);
                    v7 = 0;
                    v8_1 = 0f;
                    float v9_2 = 340282346638528860000000000000000000000f;
                    while(v7 < ((MessageGroupedLayoutAttempt)v6).heights.length) {
                        v8_1 += ((MessageGroupedLayoutAttempt)v6).heights[v7];
                        if(((MessageGroupedLayoutAttempt)v6).heights[v7] < v9_2) {
                            v9_2 = ((MessageGroupedLayoutAttempt)v6).heights[v7];
                        }

                        ++v7;
                    }

                    v7_2 = Math.abs(v8_1 - v0_2);
                    if(((MessageGroupedLayoutAttempt)v6).lineCounts.length > 1) {
                        if(((MessageGroupedLayoutAttempt)v6).lineCounts[0] <= ((MessageGroupedLayoutAttempt)v6).lineCounts[1]) {
                            v8_3 = 2;
                            if(((MessageGroupedLayoutAttempt)v6).lineCounts.length > v8_3 && ((MessageGroupedLayoutAttempt)v6).lineCounts[1] > ((MessageGroupedLayoutAttempt)v6).lineCounts[v8_3]) {
                                goto label_879;
                            }

                            v12 = 3;
                            if(((MessageGroupedLayoutAttempt)v6).lineCounts.length <= v12) {
                                goto label_883;
                            }

                            if(((MessageGroupedLayoutAttempt)v6).lineCounts[v8_3] <= ((MessageGroupedLayoutAttempt)v6).lineCounts[v12]) {
                                goto label_883;
                            }
                        }

                    label_879:
                        v7_2 *= v16;
                    }

                label_883:
                    if(v9_2 < (((float)v11))) {
                        v7_2 *= 1.5f;
                    }

                    if(v4_2 == null || v7_2 < v5_2) {
                        v4_2 = v6;
                        v5_2 = v7_2;
                    }

                    ++v3;
                }

                v13_1 = 0;
                if(v4_2 == null) {
                    return;
                }

                v0_3 = 0;
                v1 = 0;
                v12 = 0;
                while(v0_3 < ((MessageGroupedLayoutAttempt)v4_2).lineCounts.length) {
                    v3 = ((MessageGroupedLayoutAttempt)v4_2).lineCounts[v0_3];
                    v5_2 = ((MessageGroupedLayoutAttempt)v4_2).heights[v0_3];
                    v6_2 = v10.maxSizeWidth;
                    v7 = v3 - 1;
                    v12 = Math.max(v12, v7);
                    v8_3 = v6_2;
                    GroupedMessagePosition v9_3 = null;
                    v6_2 = v1;
                    for(v1 = 0; v1 < v3; ++v1) {
                        v11 = ((int)(v14_1[v6_2] * v5_2));
                        v8_3 -= v11;
                        Object v29 = v10.posArray.get(v6_2);
                        int v15_3 = v0_3 == 0 ? 4 : 0;
                        if(v0_3 == ((MessageGroupedLayoutAttempt)v4_2).lineCounts.length - 1) {
                            v15_3 |= 8;
                        }

                        if(v1 == 0) {
                            v15_3 |= 1;
                            if(v26) {
                                v9_4 = v29;
                            }
                        }

                        if(v1 == v7) {
                            v2 = v15_3 | 2;
                            if(!v26) {
                                v36 = v2;
                                v9_4 = v29;
                            }
                            else {
                                v36 = v2;
                            }
                        }
                        else {
                            v36 = v15_3;
                        }

                        ((GroupedMessagePosition)v29).set(v1, v1, v0_3, v0_3, v11, v5_2 / 814f, v36);
                        ++v6_2;
                    }

                    ((GroupedMessagePosition)v9_4).pw += v8_3;
                    ((GroupedMessagePosition)v9_4).spanSize += v8_3;
                    ++v0_3;
                    v1 = v6_2;
                }
            }

            v0_3 = v28;
            while(v13_1 < v0_3) {
                Object v1_2 = v10.posArray.get(v13_1);
                if(v26) {
                    if(((GroupedMessagePosition)v1_2).minX == 0) {
                        ((GroupedMessagePosition)v1_2).spanSize += v10.firstSpanAdditionalSize;
                    }

                    if((((GroupedMessagePosition)v1_2).flags & 2) == 0) {
                        goto label_999;
                    }

                    ((GroupedMessagePosition)v1_2).edge = true;
                }
                else {
                    v3 = 2;
                    if(((GroupedMessagePosition)v1_2).maxX == v12 || (((GroupedMessagePosition)v1_2).flags & v3) != 0) {
                        ((GroupedMessagePosition)v1_2).spanSize += v10.firstSpanAdditionalSize;
                    }

                    if((((GroupedMessagePosition)v1_2).flags & 1) == 0) {
                        goto label_999;
                    }

                    ((GroupedMessagePosition)v1_2).edge = true;
                }

            label_999:
                v2_1 = v10.messages.get(v13_1);
                if(!v26 && (((MessageObject)v2_1).needDrawAvatar())) {
                    if(((GroupedMessagePosition)v1_2).edge) {
                        if(((GroupedMessagePosition)v1_2).spanSize != 1000) {
                            ((GroupedMessagePosition)v1_2).spanSize += 108;
                        }

                        ((GroupedMessagePosition)v1_2).pw += 108;
                    }
                    else {
                        if((((GroupedMessagePosition)v1_2).flags & 2) == 0) {
                            goto label_1034;
                        }

                        if(((GroupedMessagePosition)v1_2).spanSize != 1000) {
                            ((GroupedMessagePosition)v1_2).spanSize += -108;
                            goto label_1034;
                        }

                        if(((GroupedMessagePosition)v1_2).leftSpanOffset == 0) {
                            goto label_1034;
                        }

                        ((GroupedMessagePosition)v1_2).leftSpanOffset += 108;
                    }
                }

            label_1034:
                ++v13_1;
            }
        }

        private float multiHeight(float[] arg3, int arg4, int arg5) {
            float v0 = 0f;
            while(arg4 < arg5) {
                v0 += arg3[arg4];
                ++arg4;
            }

            return (((float)this.maxSizeWidth)) / v0;
        }
    }

    public class TextLayoutBlock {
        public int charactersEnd;
        public int charactersOffset;
        public byte directionFlags;
        public int height;
        public int heightByOffset;
        public StaticLayout textLayout;
        public float textYOffset;

        public TextLayoutBlock() {
            super();
        }

        public boolean isRtl() {
            boolean v1 = true;
            if((this.directionFlags & 1) == 0 || (this.directionFlags & 2) != 0) {
                v1 = false;
            }
            else {
            }

            return v1;
        }
    }

    public class VCardData {
        private String company;
        private ArrayList emails;
        private ArrayList phones;

        public VCardData() {
            super();
            this.emails = new ArrayList();
            this.phones = new ArrayList();
        }

        public static CharSequence parse(String arg16) {
            char v6_3;
            StringBuilder v0_2;
            int v11;
            String v13;
            String v12;
            String[] v6_1;
            String[] v10;
            int v9;
            String v1 = null;
            try {
                BufferedReader v0 = new BufferedReader(new StringReader(arg16));
                int v2 = 0;
                VCardData v4 = ((VCardData)v1);
                String v5 = ((String)v4);
                int v3 = 0;
                while(true) {
                label_10:
                    String v6 = v0.readLine();
                    if(v6 == null) {
                        break;
                    }

                    if(v6.startsWith("PHOTO")) {
                        continue;
                    }

                    if(v6.indexOf(58) >= 0) {
                        if(v6.startsWith("BEGIN:VCARD")) {
                            v4 = new VCardData();
                        }
                        else if((v6.startsWith("END:VCARD")) && v4 != null) {
                            v3 = 1;
                        }
                    }

                    if(v5 != null) {
                        v6 = v5 + v6;
                        v5 = v1;
                    }

                    if((v6.contains("=QUOTED-PRINTABLE")) && (v6.endsWith("="))) {
                        v5 = v6.substring(0, v6.length() - 1);
                        continue;
                    }

                    int v7_1 = v6.indexOf(":");
                    v9 = 2;
                    if(v7_1 >= 0) {
                        v10 = new String[v9];
                        v10[0] = v6.substring(0, v7_1);
                        v10[1] = v6.substring(v7_1 + 1, v6.length()).trim();
                    }
                    else {
                        v10 = new String[]{v6.trim()};
                    }

                    if(v10.length < v9) {
                        continue;
                    }

                    if(v4 == null) {
                        continue;
                    }

                    if(v10[0].startsWith("ORG")) {
                        v6_1 = v10[0].split(";");
                        v7_1 = v6_1.length;
                        v12 = v1;
                        v13 = v12;
                        v11 = 0;
                        goto label_79;
                    }

                    if(v10[0].startsWith("TEL")) {
                        if(v10[1].length() <= 0) {
                            continue;
                        }

                        v4.phones.add(v10[1]);
                        continue;
                    }

                    if(!v10[0].startsWith("EMAIL")) {
                        continue;
                    }

                    v6 = v10[1];
                    if(v6.length() <= 0) {
                        continue;
                    }

                    v4.emails.add(v6);
                }

                try {
                    v0.close();
                    goto label_149;
                }
                catch(Exception v0_1) {
                    Exception v5_1 = v0_1;
                    try {
                        FileLog.e(((Throwable)v5_1));
                    label_149:
                        if(v3 == 0) {
                            goto label_196;
                        }

                        v0_2 = new StringBuilder();
                        v3 = 0;
                        goto label_153;
                    label_79:
                        while(v11 < v7_1) {
                            String[] v14 = v6_1[v11].split("=");
                            if(v14.length != v9) {
                            }
                            else if(v14[0].equals("CHARSET")) {
                                v13 = v14[1];
                            }
                            else if(v14[0].equals("ENCODING")) {
                                v12 = v14[1];
                            }

                            ++v11;
                            v9 = 2;
                        }

                        v4.company = v10[1];
                        if(v12 != null && (v12.equalsIgnoreCase("QUOTED-PRINTABLE"))) {
                            byte[] v6_2 = AndroidUtilities.decodeQuotedPrintable(AndroidUtilities.getStringBytes(v4.company));
                            if(v6_2 != null && v6_2.length != 0) {
                                v4.company = new String(v6_2, v13);
                            }
                        }

                        v4.company = v4.company.replace(';', ' ');
                        goto label_10;
                        while(true) {
                        label_153:
                            v6_3 = '\n';
                            if(v3 < v4.phones.size()) {
                                if(v0_2.length() > 0) {
                                    v0_2.append(v6_3);
                                }

                                Object v5_2 = v4.phones.get(v3);
                                if(!((String)v5_2).contains("#")) {
                                    if(((String)v5_2).contains("*")) {
                                    }
                                    else {
                                        v5 = b.a().e(((String)v5_2));
                                    }
                                }

                                v0_2.append(v5);
                                ++v3;
                                continue;
                            }

                            break;
                        }

                        while(v2 < v4.emails.size()) {
                            if(v0_2.length() > 0) {
                                v0_2.append(v6_3);
                            }

                            v0_2.append(b.a().e(v4.emails.get(v2)));
                            ++v2;
                        }

                        if(!TextUtils.isEmpty(v4.company)) {
                            if(v0_2.length() > 0) {
                                v0_2.append(v6_3);
                            }

                            v0_2.append(v4.company);
                        }
                    }
                    catch(Throwable ) {
                        goto label_196;
                    }
                }
            }
            catch(Throwable ) {
                goto label_196;
            }

            return ((CharSequence)v0_2);
        label_196:
            return ((CharSequence)v1);
        }
    }

    private static final int LINES_PER_BLOCK = 10;
    public static final int MESSAGE_SEND_STATE_EDITING = 3;
    public static final int MESSAGE_SEND_STATE_SENDING = 1;
    public static final int MESSAGE_SEND_STATE_SEND_ERROR = 2;
    public static final int MESSAGE_SEND_STATE_SENT = 0;
    public static final int POSITION_FLAG_BOTTOM = 8;
    public static final int POSITION_FLAG_LEFT = 1;
    public static final int POSITION_FLAG_RIGHT = 2;
    public static final int POSITION_FLAG_TOP = 4;
    public boolean attachPathExists;
    public int audioPlayerDuration;
    public float audioProgress;
    public int audioProgressSec;
    public StringBuilder botButtonsLayout;
    public float bufferedProgress;
    public boolean cancelEditing;
    public CharSequence caption;
    public CharSequence captionBU;
    public int contentType;
    public int currentAccount;
    public TL_channelAdminLogEvent currentEvent;
    public String customReplyName;
    public String dateKey;
    public boolean deleted;
    public CharSequence editingMessage;
    public ArrayList editingMessageEntities;
    public long eventId;
    public boolean forceUpdate;
    private int generatedWithMinSize;
    public float gifState;
    public boolean hasRtl;
    public boolean isDateObject;
    private int isRoundVideoCached;
    public int lastLineWidth;
    private boolean layoutCreated;
    public int linesCount;
    public CharSequence linkDescription;
    public boolean localChannel;
    public long localGroupId;
    public String localName;
    public long localSentGroupId;
    public int localType;
    public String localUserName;
    public boolean mediaExists;
    public Message messageOwner;
    public String messageOwnerMessageBU;
    public CharSequence messageText;
    public CharSequence messageTextBU;
    public String monthKey;
    public ArrayList photoThumbs;
    public ArrayList photoThumbs2;
    public String previousAttachPath;
    public String previousCaption;
    public ArrayList previousCaptionEntities;
    public MessageMedia previousMedia;
    public MessageObject replyMessageObject;
    public boolean resendAsIs;
    public int textHeight;
    public ArrayList textLayoutBlocks;
    public int textWidth;
    public float textXOffset;
    public int type;
    public static Pattern urlPattern;
    public boolean useCustomPhoto;
    public CharSequence vCardData;
    public VideoEditedInfo videoEditedInfo;
    public boolean viewsReloaded;
    public int wantedBotKeyboardWidth;

    public MessageObject(int arg11, Message arg12, boolean arg13) {
        this(arg11, arg12, null, null, null, null, arg13, 0);
    }

    public MessageObject(int arg11, Message arg12, SparseArray arg13, SparseArray arg14, boolean arg15) {
        this(arg11, arg12, null, null, arg13, arg14, arg15, 0);
    }

    public MessageObject(int arg7, Message arg8, SparseArray arg9, boolean arg10) {
        this(arg7, arg8, arg9, null, arg10);
    }

    public MessageObject(int arg7, Message arg8, AbstractMap arg9, boolean arg10) {
        this(arg7, arg8, arg9, null, arg10);
    }

    public MessageObject(int arg19, Message arg20, AbstractMap arg21, AbstractMap arg22, SparseArray arg23, SparseArray arg24, boolean arg25, long arg26) {
        float v1_9;
        User v0_8;
        String v9_2;
        int[] v1_8;
        int v0_7;
        Object v0_4;
        Chat v0_5;
        Object[] v5_1;
        String v2_5;
        Object v2_4;
        Object[] v2_2;
        int v8;
        int v10;
        String v1_4;
        int v14;
        boolean v13;
        User v4_2;
        Object v4_1;
        int v7_1;
        int v2_1;
        User v1_3;
        Object v1_2;
        CharSequence v0_2;
        int v1_1;
        String v0_1;
        User v15_1;
        Object v15;
        Object v7;
        int v3;
        MessageObject v6 = this;
        Message v0 = arg20;
        AbstractMap v4 = arg21;
        AbstractMap v1 = arg22;
        SparseArray v5 = arg23;
        SparseArray v2 = arg24;
        super();
        v6.type = 1000;
        Theme.createChatResources(null, true);
        v6.currentAccount = arg19;
        v6.messageOwner = v0;
        v6.eventId = arg26;
        if(v0.replyMessage != null) {
            v3 = 1;
            v6.replyMessageObject = new MessageObject(arg19, v0.replyMessage, arg21, arg22, arg23, arg24, false, arg26);
        }
        else {
            v3 = 1;
        }

        if(v0.from_id > 0) {
            if(v4 != null) {
                v7 = v4.get(Integer.valueOf(v0.from_id));
                goto label_46;
            }
            else if(v5 != null) {
                v7 = v5.get(v0.from_id);
            label_46:
                v15 = v7;
            }
            else {
                v15 = null;
            }

            if(v15 != null) {
                goto label_60;
            }

            v15_1 = MessagesController.getInstance(arg19).getUser(Integer.valueOf(v0.from_id));
        }
        else {
            TLObject v15_2 = null;
        }

    label_60:
        int v11 = 2;
        if((v0 instanceof TL_messageService)) {
            if(v0.action != null) {
                if((v0.action instanceof TL_messageActionCustomAction)) {
                    v0_1 = v0.action.message;
                }
                else {
                    if((v0.action instanceof TL_messageActionChatCreate)) {
                        if(this.isOut()) {
                            v0_1 = "ActionYouCreateGroup";
                            v1_1 = 2131624004;
                            goto label_80;
                        }
                        else {
                            v0_1 = "ActionCreateGroup";
                            v1_1 = 2131623973;
                        }
                    }
                    else if(!(v0.action instanceof TL_messageActionChatDeleteUser)) {
                        goto label_149;
                    }
                    else if(v0.action.user_id != v0.from_id) {
                        goto label_103;
                    }
                    else if(this.isOut()) {
                        v0_1 = "ActionYouLeftUser";
                        v1_1 = 2131624006;
                    label_80:
                        v0_1 = LocaleController.getString(v0_1, v1_1);
                        goto label_71;
                    }
                    else {
                        v0_1 = "ActionLeftUser";
                        v1_1 = 2131623979;
                    }

                    v0_2 = v6.replaceWithLink(LocaleController.getString(v0_1, v1_1), "un1", ((TLObject)v15));
                    goto label_71;
                label_103:
                    if(v4 != null) {
                        v1_2 = v4.get(Integer.valueOf(v0.action.user_id));
                    }
                    else if(v5 != null) {
                        v1_2 = v5.get(v0.action.user_id);
                    }
                    else {
                        v1_2 = null;
                    }

                    if(v1_2 == null) {
                        v1_3 = MessagesController.getInstance(arg19).getUser(Integer.valueOf(v0.action.user_id));
                    }

                    if(this.isOut()) {
                        v0_2 = v6.replaceWithLink(LocaleController.getString("ActionYouKickUser", 2131624005), "un2", ((TLObject)v1_3));
                        goto label_71;
                    }

                    if(v0.action.user_id == UserConfig.getInstance(v6.currentAccount).getClientUserId()) {
                        v0_1 = LocaleController.getString("ActionKickUserYou", 2131623978);
                    }
                    else {
                        v6.messageText = v6.replaceWithLink(LocaleController.getString("ActionKickUser", 2131623977), "un2", ((TLObject)v1_3));
                        v0_2 = v6.messageText;
                    }

                    v0_2 = v6.replaceWithLink(((CharSequence)v0_1), "un1", ((TLObject)v15));
                }

            label_71:
                v6.messageText = ((CharSequence)v0_1);
                goto label_872;
            label_149:
                if((v0.action instanceof TL_messageActionChatAddUser)) {
                    v1_1 = v6.messageOwner.action.user_id;
                    if(v1_1 == 0 && v6.messageOwner.action.users.size() == v3) {
                        v1_1 = v6.messageOwner.action.users.get(0).intValue();
                    }

                    v2_1 = 2131623947;
                    v7_1 = 2131624001;
                    if(v1_1 != 0) {
                        if(v4 != null) {
                            v4_1 = v4.get(Integer.valueOf(v1_1));
                        }
                        else if(v5 != null) {
                            v4_1 = v5.get(v1_1);
                        }
                        else {
                            v4_1 = null;
                        }

                        if(v4_1 == null) {
                            v4_2 = MessagesController.getInstance(arg19).getUser(Integer.valueOf(v1_1));
                        }

                        if(v1_1 == v0.from_id) {
                            if(v0.to_id.channel_id == 0 || (this.isMegagroup())) {
                                if(v0.to_id.channel_id != 0 && (this.isMegagroup())) {
                                    if(v1_1 == UserConfig.getInstance(v6.currentAccount).getClientUserId()) {
                                        v0_1 = "ChannelMegaJoined";
                                        v1_1 = 2131624318;
                                        goto label_190;
                                    }
                                    else {
                                        v0_1 = "ActionAddUserSelfMega";
                                        v1_1 = 2131623949;
                                        goto label_214;
                                    }
                                }

                                if(!this.isOut()) {
                                    goto label_212;
                                }

                                v0_1 = "ActionAddUserSelfYou";
                                v1_1 = 2131623950;
                            }
                            else {
                                v0_1 = "ChannelJoined";
                                v1_1 = 2131624314;
                            }

                        label_190:
                            v0_1 = LocaleController.getString(v0_1, v1_1);
                            goto label_222;
                        label_212:
                            v0_1 = "ActionAddUserSelf";
                            v1_1 = 2131623948;
                            goto label_214;
                        }
                        else {
                            if(this.isOut()) {
                                v0_2 = v6.replaceWithLink(LocaleController.getString("ActionYouAddUser", v7_1), "un2", ((TLObject)v4_2));
                                goto label_222;
                            }

                            if(v1_1 == UserConfig.getInstance(v6.currentAccount).getClientUserId()) {
                                if(v0.to_id.channel_id == 0) {
                                    goto label_239;
                                }

                                if(this.isMegagroup()) {
                                    v0_1 = "MegaAddedBy";
                                    v1_1 = 2131625155;
                                }
                                else {
                                    v0_1 = "ChannelAddedBy";
                                    v1_1 = 2131624285;
                                    goto label_214;
                                label_239:
                                    v0_1 = "ActionAddUserYou";
                                    v1_1 = 2131623951;
                                }

                            label_214:
                                v0_1 = LocaleController.getString(v0_1, v1_1);
                            }
                            else {
                                v6.messageText = v6.replaceWithLink(LocaleController.getString("ActionAddUser", v2_1), "un2", ((TLObject)v4_2));
                                v0_2 = v6.messageText;
                            }

                            v0_2 = v6.replaceWithLink(((CharSequence)v0_1), "un1", ((TLObject)v15));
                        }

                    label_222:
                        v6.messageText = ((CharSequence)v0_1);
                        v13 = arg25;
                        v14 = 1;
                        goto label_296;
                    }

                    if(this.isOut()) {
                        v13 = arg25;
                        v14 = 1;
                        v0_2 = this.replaceWithLink(LocaleController.getString("ActionYouAddUser", v7_1), "un2", v0.action.users, arg21, arg23);
                        goto label_295;
                    }

                    v13 = arg25;
                    v14 = 1;
                    v6.messageText = this.replaceWithLink(LocaleController.getString("ActionAddUser", v2_1), "un2", v0.action.users, arg21, arg23);
                    v0_2 = v6.replaceWithLink(v6.messageText, "un1", ((TLObject)v15));
                    goto label_295;
                }
                else {
                    v13 = arg25;
                    v14 = 1;
                    if((v0.action instanceof TL_messageActionChatJoinedByLink)) {
                        if(this.isOut()) {
                            v0_1 = "ActionInviteYou";
                            v1_1 = 2131623976;
                        }
                        else {
                            v0_1 = "ActionInviteUser";
                            v1_1 = 2131623975;
                            goto label_300;
                        }
                    }
                    else if((v0.action instanceof TL_messageActionChatEditPhoto)) {
                        if(v0.to_id.channel_id != 0 && !this.isMegagroup()) {
                            v0_1 = "ActionChannelChangedPhoto";
                            v1_1 = 2131623969;
                            goto label_294;
                        }

                        if(this.isOut()) {
                            v0_1 = "ActionYouChangedPhoto";
                            v1_1 = 2131624002;
                            goto label_294;
                        }

                        v0_1 = "ActionChangedPhoto";
                        v1_1 = 2131623967;
                        goto label_300;
                    }
                    else {
                        if(!(v0.action instanceof TL_messageActionChatEditTitle)) {
                            goto label_352;
                        }

                        if(v0.to_id.channel_id != 0 && !this.isMegagroup()) {
                            v1_4 = "ActionChannelChangedTitle";
                            v2_1 = 2131623970;
                        }
                        else if(this.isOut()) {
                            v1_4 = "ActionYouChangedTitle";
                            v2_1 = 2131624003;
                        }
                        else {
                            goto label_344;
                        }

                        v0_1 = LocaleController.getString(v1_4, v2_1).replace("un2", v0.action.title);
                        goto label_295;
                    label_344:
                        v0_1 = LocaleController.getString("ActionChangedTitle", 2131623968).replace("un2", v0.action.title);
                        goto label_301;
                    label_352:
                        if((v0.action instanceof TL_messageActionChatDeletePhoto)) {
                            if(v0.to_id.channel_id != 0 && !this.isMegagroup()) {
                                v0_1 = "ActionChannelRemovedPhoto";
                                v1_1 = 2131623971;
                                goto label_294;
                            }

                            if(this.isOut()) {
                                v0_1 = "ActionYouRemovedPhoto";
                                v1_1 = 2131624007;
                                goto label_294;
                            }

                            v0_1 = "ActionRemovedPhoto";
                            v1_1 = 2131623996;
                        label_300:
                            v0_1 = LocaleController.getString(v0_1, v1_1);
                            goto label_301;
                        }

                        v7_1 = 2131625182;
                        v10 = 2131625184;
                        v8 = 2131625179;
                        int v9 = 2131625180;
                        if((v0.action instanceof TL_messageActionTTLChange)) {
                            if(v0.action.ttl != 0) {
                                if(this.isOut()) {
                                    v0_1 = LocaleController.formatString("MessageLifetimeChangedOutgoing", v9, new Object[]{LocaleController.formatTTLString(v0.action.ttl)});
                                    goto label_295;
                                }

                                v2_2 = new Object[v11];
                                v2_2[0] = UserObject.getFirstName(((User)v15));
                                v2_2[1] = LocaleController.formatTTLString(v0.action.ttl);
                                v0_1 = LocaleController.formatString("MessageLifetimeChanged", v8, v2_2);
                                goto label_295;
                            }

                            if(this.isOut()) {
                                v0_1 = LocaleController.getString("MessageLifetimeYouRemoved", v10);
                                goto label_295;
                            }

                            v0_1 = LocaleController.formatString("MessageLifetimeRemoved", v7_1, new Object[]{UserObject.getFirstName(((User)v15))});
                            goto label_295;
                        }

                        if((v0.action instanceof TL_messageActionLoginUnknownLocation)) {
                            long v1_5 = (((long)v0.date)) * 1000;
                            if(LocaleController.getInstance().formatterDay == null || LocaleController.getInstance().formatterYear == null) {
                                v1_4 = "" + v0.date;
                            }
                            else {
                                Object[] v8_1 = new Object[v11];
                                v8_1[0] = LocaleController.getInstance().formatterYear.format(v1_5);
                                v8_1[1] = LocaleController.getInstance().formatterDay.format(v1_5);
                                v1_4 = LocaleController.formatString("formatDateAtTime", 2131626775, v8_1);
                            }

                            User v2_3 = UserConfig.getInstance(v6.currentAccount).getCurrentUser();
                            if(v2_3 == null) {
                                if(v4 != null) {
                                    v2_4 = v4.get(Integer.valueOf(v6.messageOwner.to_id.user_id));
                                }
                                else if(v5 != null) {
                                    v2_4 = v5.get(v6.messageOwner.to_id.user_id);
                                }

                                if(v2_3 != null) {
                                    goto label_469;
                                }

                                v2_3 = MessagesController.getInstance(arg19).getUser(Integer.valueOf(v6.messageOwner.to_id.user_id));
                            }

                        label_469:
                            v2_5 = v2_3 != null ? UserObject.getFirstName(v2_3) : "";
                            v5_1 = new Object[4];
                            v5_1[0] = v2_5;
                            v5_1[1] = v1_4;
                            v5_1[v11] = v0.action.title;
                            v5_1[3] = v0.action.address;
                            v0_1 = LocaleController.formatString("NotificationUnrecognizedDevice", 2131625385, v5_1);
                            goto label_295;
                        }

                        if((v0.action instanceof TL_messageActionUserJoined)) {
                            v0_1 = "NotificationContactJoined";
                            v1_1 = 2131625334;
                            v2_2 = new Object[]{UserObject.getUserName(((User)v15))};
                        }
                        else if((v0.action instanceof TL_messageActionUserUpdatedPhoto)) {
                            v0_1 = "NotificationContactNewPhoto";
                            v1_1 = 2131625335;
                            v2_2 = new Object[]{UserObject.getUserName(((User)v15))};
                        }
                        else {
                            v7_1 = 2131623997;
                            v10 = 2131623998;
                            if((v0.action instanceof TL_messageEncryptedAction)) {
                                if((v0.action.encryptedAction instanceof TL_decryptedMessageActionScreenshotMessages)) {
                                    if(!this.isOut()) {
                                        goto label_522;
                                    }

                                    goto label_518;
                                }
                                else if((v0.action.encryptedAction instanceof TL_decryptedMessageActionSetMessageTTL)) {
                                    DecryptedMessageAction v0_3 = v0.action.encryptedAction;
                                    if(((TL_decryptedMessageActionSetMessageTTL)v0_3).ttl_seconds != 0) {
                                        if(this.isOut()) {
                                            v0_1 = LocaleController.formatString("MessageLifetimeChangedOutgoing", v9, new Object[]{LocaleController.formatTTLString(((TL_decryptedMessageActionSetMessageTTL)v0_3).ttl_seconds)});
                                        }
                                        else {
                                            v2_2 = new Object[v11];
                                            v2_2[0] = UserObject.getFirstName(((User)v15));
                                            v2_2[1] = LocaleController.formatTTLString(((TL_decryptedMessageActionSetMessageTTL)v0_3).ttl_seconds);
                                            v0_1 = LocaleController.formatString("MessageLifetimeChanged", v8, v2_2);
                                        }
                                    }
                                    else if(this.isOut()) {
                                        v0_1 = LocaleController.getString("MessageLifetimeYouRemoved", 2131625184);
                                    }
                                    else {
                                        v0_1 = LocaleController.formatString("MessageLifetimeRemoved", 2131625182, new Object[]{UserObject.getFirstName(((User)v15))});
                                    }

                                    goto label_295;
                                }
                                else {
                                    goto label_296;
                                }
                            }
                            else if(!(v0.action instanceof TL_messageActionScreenshotTaken)) {
                                goto label_570;
                            }
                            else if(this.isOut()) {
                            label_518:
                                v0_1 = LocaleController.formatString("ActionTakeScreenshootYou", v10, new Object[0]);
                                goto label_295;
                            }

                        label_522:
                            v0_1 = LocaleController.getString("ActionTakeScreenshoot", v7_1);
                        label_301:
                            v0_2 = v6.replaceWithLink(((CharSequence)v0_1), "un1", ((TLObject)v15));
                            goto label_295;
                        label_570:
                            if(!(v0.action instanceof TL_messageActionCreatedBroadcastList)) {
                                goto label_577;
                            }

                            v0_1 = "YouCreatedBroadcastList";
                            v1_1 = 2131626441;
                            v2_2 = new Object[0];
                        }

                        v0_1 = LocaleController.formatString(v0_1, v1_1, v2_2);
                        goto label_295;
                    label_577:
                        if((v0.action instanceof TL_messageActionChannelCreate)) {
                            if(this.isMegagroup()) {
                                v0_1 = "ActionCreateMega";
                                v1_1 = 2131623974;
                                goto label_294;
                            }

                            v0_1 = "ActionCreateChannel";
                            v1_1 = 2131623972;
                            goto label_294;
                        }

                        if(!(v0.action instanceof TL_messageActionChatMigrateTo)) {
                            if((v0.action instanceof TL_messageActionChannelMigrateFrom)) {
                            }
                            else {
                                goto label_598;
                            }
                        }

                        v0_1 = "ActionMigrateFromGroup";
                        v1_1 = 2131623980;
                        goto label_294;
                    label_598:
                        if((v0.action instanceof TL_messageActionPinMessage)) {
                            if(v15 != null) {
                            label_613:
                                v0_5 = null;
                            }
                            else if(v1 != null) {
                                v0_4 = v1.get(Integer.valueOf(v0.to_id.channel_id));
                            }
                            else if(v2 != null) {
                                v0_4 = v2.get(v0.to_id.channel_id);
                            }
                            else {
                                goto label_613;
                            }

                            v6.generatePinMessageText(((User)v15), v0_5);
                            goto label_296;
                        }

                        if(!(v0.action instanceof TL_messageActionHistoryClear)) {
                            goto label_622;
                        }

                        v0_1 = "HistoryCleared";
                        v1_1 = 2131624954;
                    }

                label_294:
                    v0_1 = LocaleController.getString(v0_1, v1_1);
                label_295:
                    v6.messageText = ((CharSequence)v0_1);
                    goto label_296;
                label_622:
                    if((v0.action instanceof TL_messageActionGameScore)) {
                        v6.generateGameMessageText(((User)v15));
                        goto label_296;
                    }

                    if(!(v0.action instanceof TL_messageActionPhoneCall)) {
                        goto label_702;
                    }

                    MessageAction v0_6 = v6.messageOwner.action;
                    boolean v1_7 = ((TL_messageActionPhoneCall)v0_6).reason instanceof TL_phoneCallDiscardReasonMissed;
                    if(v6.messageOwner.from_id == UserConfig.getInstance(v6.currentAccount).getClientUserId()) {
                        if(v1_7) {
                            v1_4 = "CallMessageOutgoingMissed";
                            v2_1 = 2131624243;
                        }
                        else {
                            v1_4 = "CallMessageOutgoing";
                            v2_1 = 2131624242;
                        }
                    }
                    else if(v1_7) {
                        v1_4 = "CallMessageIncomingMissed";
                        v2_1 = 2131624241;
                    }
                    else if((((TL_messageActionPhoneCall)v0_6).reason instanceof TL_phoneCallDiscardReasonBusy)) {
                        v1_4 = "CallMessageIncomingDeclined";
                        v2_1 = 2131624240;
                    }
                    else {
                        v1_4 = "CallMessageIncoming";
                        v2_1 = 2131624239;
                    }

                    v6.messageText = LocaleController.getString(v1_4, v2_1);
                    if(((TL_messageActionPhoneCall)v0_6).duration <= 0) {
                        goto label_296;
                    }

                    v0_1 = LocaleController.formatCallDuration(((TL_messageActionPhoneCall)v0_6).duration);
                    Object[] v3_1 = new Object[v11];
                    v3_1[0] = v6.messageText;
                    v3_1[1] = v0_1;
                    v6.messageText = LocaleController.formatString("CallMessageWithDuration", 2131624245, v3_1);
                    v1_4 = v6.messageText.toString();
                    v2_1 = v1_4.indexOf(v0_1);
                    if(v2_1 == -1) {
                        goto label_296;
                    }

                    SpannableString v3_2 = new SpannableString(v6.messageText);
                    v0_7 = v0_1.length() + v2_1;
                    if(v2_1 > 0 && v1_4.charAt(v2_1 - 1) == 40) {
                        --v2_1;
                    }

                    if(v0_7 < v1_4.length() && v1_4.charAt(v0_7) == 41) {
                        ++v0_7;
                    }

                    v3_2.setSpan(new TypefaceSpan(Typeface.DEFAULT), v2_1, v0_7, 0);
                    v6.messageText = ((CharSequence)v3_2);
                }

            label_296:
                v1_8 = null;
                goto label_1005;
            label_702:
                if((v0.action instanceof TL_messageActionPaymentSent)) {
                    v0_7 = ((int)this.getDialogId());
                    if(v4 != null) {
                        v1_2 = v4.get(Integer.valueOf(v0_7));
                        goto label_710;
                    }
                    else if(v5 != null) {
                        v1_2 = v5.get(v0_7);
                    label_710:
                        v15 = v1_2;
                    }

                    if(v15 == null) {
                        v15_1 = MessagesController.getInstance(arg19).getUser(Integer.valueOf(v0_7));
                    }

                    v1_3 = null;
                    v6.generatePaymentSentMessageText(v1_3);
                    goto label_1005;
                }

                v1_8 = null;
                if((v0.action instanceof TL_messageActionBotAllowed)) {
                    v0_1 = v0.action.domain;
                    v2_5 = LocaleController.getString("ActionBotAllowed", 2131623952);
                    v3 = v2_5.indexOf("%1$s");
                    SpannableString v4_3 = new SpannableString(String.format(v2_5, v0_1));
                    if(v3 >= 0) {
                        StringBuilder v5_2 = new StringBuilder();
                        v5_2.append("http://");
                        v5_2.append(v0_1);
                        v4_3.setSpan(new URLSpanNoUnderlineBold(v5_2.toString()), v3, v0_1.length() + v3, 33);
                    }

                    v6.messageText = ((CharSequence)v4_3);
                    goto label_1005;
                }

                if(!(v0.action instanceof TL_messageActionSecureValuesSent)) {
                    goto label_1005;
                }

                MessageAction v2_6 = v0.action;
                StringBuilder v3_3 = new StringBuilder();
                v7_1 = ((TL_messageActionSecureValuesSent)v2_6).types.size();
                for(v8 = 0; v8 < v7_1; ++v8) {
                    Object v9_1 = ((TL_messageActionSecureValuesSent)v2_6).types.get(v8);
                    if(v3_3.length() > 0) {
                        v3_3.append(", ");
                    }

                    if((v9_1 instanceof TL_secureValueTypePhone)) {
                        v9_2 = "ActionBotDocumentPhone";
                        v10 = 2131623962;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeEmail)) {
                        v9_2 = "ActionBotDocumentEmail";
                        v10 = 2131623956;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeAddress)) {
                        v9_2 = "ActionBotDocumentAddress";
                        v10 = 2131623953;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypePersonalDetails)) {
                        v9_2 = "ActionBotDocumentIdentity";
                        v10 = 2131623957;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypePassport)) {
                        v9_2 = "ActionBotDocumentPassport";
                        v10 = 2131623960;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeDriverLicense)) {
                        v9_2 = "ActionBotDocumentDriverLicence";
                        v10 = 2131623955;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeIdentityCard)) {
                        v9_2 = "ActionBotDocumentIdentityCard";
                        v10 = 2131623958;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeUtilityBill)) {
                        v9_2 = "ActionBotDocumentUtilityBill";
                        v10 = 2131623965;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeBankStatement)) {
                        v9_2 = "ActionBotDocumentBankStatement";
                        v10 = 2131623954;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeRentalAgreement)) {
                        v9_2 = "ActionBotDocumentRentalAgreement";
                        v10 = 2131623963;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeInternalPassport)) {
                        v9_2 = "ActionBotDocumentInternalPassport";
                        v10 = 2131623959;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypePassportRegistration)) {
                        v9_2 = "ActionBotDocumentPassportRegistration";
                        v10 = 2131623961;
                        goto label_774;
                    }
                    else if((v9_1 instanceof TL_secureValueTypeTemporaryRegistration)) {
                        v9_2 = "ActionBotDocumentTemporaryRegistration";
                        v10 = 2131623964;
                    label_774:
                        v3_3.append(LocaleController.getString(v9_2, v10));
                    }
                }

                if(v0.to_id != null) {
                    if(v4 != null) {
                        v2_4 = v4.get(Integer.valueOf(v0.to_id.user_id));
                    }
                    else if(v5 != null) {
                        v2_4 = v5.get(v0.to_id.user_id);
                    }
                    else {
                        v2_4 = v1_8;
                    }

                    if(v2_4 == null) {
                        v0_8 = MessagesController.getInstance(arg19).getUser(Integer.valueOf(v0.to_id.user_id));
                        goto label_863;
                    }

                    v0_4 = v2_4;
                }
                else {
                    v0_8 = ((User)v1_8);
                }

            label_863:
                v5_1 = new Object[v11];
                v5_1[0] = UserObject.getFirstName(v0_8);
                v5_1[1] = v3_3.toString();
                v0_1 = LocaleController.formatString("ActionBotDocuments", 2131623966, v5_1);
                goto label_975;
            }

        label_872:
            v1_8 = null;
            v13 = arg25;
            v14 = 1;
        }
        else {
            v1_8 = null;
            v13 = arg25;
            v14 = 1;
            if(!this.isMediaEmpty()) {
                if((v0.media instanceof TL_messageMediaPhoto)) {
                    v0_1 = "AttachPhoto";
                    v2_1 = 2131624151;
                }
                else {
                    if(!this.isVideo() && (!(v0.media instanceof TL_messageMediaDocument) || !(v0.media.document instanceof TL_documentEmpty) || v0.media.ttl_seconds == 0)) {
                        if(this.isVoice()) {
                            v0_1 = "AttachAudio";
                            v2_1 = 2131624133;
                            goto label_974;
                        }

                        if(this.isRoundVideo()) {
                            v0_1 = "AttachRound";
                            v2_1 = 2131624153;
                            goto label_974;
                        }

                        if(!(v0.media instanceof TL_messageMediaGeo)) {
                            if((v0.media instanceof TL_messageMediaVenue)) {
                            }
                            else if((v0.media instanceof TL_messageMediaGeoLive)) {
                                v0_1 = "AttachLiveLocation";
                                v2_1 = 2131624145;
                                goto label_974;
                            }
                            else if((v0.media instanceof TL_messageMediaContact)) {
                                v6.messageText = LocaleController.getString("AttachContact", 2131624136);
                                if(!TextUtils.isEmpty(v0.media.vcard)) {
                                    v6.vCardData = VCardData.parse(v0.media.vcard);
                                }
                                else {
                                }

                                goto label_1005;
                            }
                            else if((v0.media instanceof TL_messageMediaGame)) {
                                goto label_1003;
                            }
                            else if((v0.media instanceof TL_messageMediaInvoice)) {
                                v0_1 = v0.media.description;
                                goto label_975;
                            }
                            else if((v0.media instanceof TL_messageMediaUnsupported)) {
                                v0_1 = "UnsupportedMedia";
                                v2_1 = 2131626280;
                                goto label_974;
                            }
                            else if((v0.media instanceof TL_messageMediaDocument)) {
                                if(this.isSticker()) {
                                    v0_1 = this.getStrickerChar();
                                    v2_1 = 2131624154;
                                    if(v0_1 != null && v0_1.length() > 0) {
                                        v0_1 = String.format("%s %s", v0_1, LocaleController.getString("AttachSticker", v2_1));
                                        goto label_975;
                                    }

                                    v0_1 = "AttachSticker";
                                }
                                else {
                                    if(this.isMusic()) {
                                        v0_1 = "AttachMusic";
                                        v2_1 = 2131624150;
                                        goto label_974;
                                    }

                                    if(this.isGif()) {
                                        v0_1 = "AttachGif";
                                        v2_1 = 2131624142;
                                        goto label_974;
                                    }

                                    v0_1 = FileLoader.getDocumentFileName(v0.media.document);
                                    if(v0_1 != null && v0_1.length() > 0) {
                                        goto label_975;
                                    }

                                    v0_1 = "AttachDocument";
                                    v2_1 = 2131624139;
                                }

                                goto label_974;
                            }
                            else {
                                goto label_1005;
                            }
                        }

                        v0_1 = "AttachLocation";
                        v2_1 = 2131624147;
                        goto label_974;
                    }

                    v0_1 = "AttachVideo";
                    v2_1 = 2131624158;
                }

            label_974:
                v0_1 = LocaleController.getString(v0_1, v2_1);
            }
            else {
            label_1003:
                v0_1 = v0.message;
            }

        label_975:
            v6.messageText = ((CharSequence)v0_1);
        }

    label_1005:
        if(v6.messageText == null) {
            v6.messageText = "";
        }

        this.setType();
        this.measureInlineBotButtons();
        GregorianCalendar v0_9 = new GregorianCalendar();
        ((Calendar)v0_9).setTimeInMillis((((long)v6.messageOwner.date)) * 1000);
        v2_1 = ((Calendar)v0_9).get(6);
        v3 = ((Calendar)v0_9).get(v14);
        v0_7 = ((Calendar)v0_9).get(v11);
        Object[] v7_2 = new Object[3];
        v7_2[0] = Integer.valueOf(v3);
        v7_2[v14] = Integer.valueOf(v0_7);
        v7_2[v11] = Integer.valueOf(v2_1);
        v6.dateKey = String.format("%d_%02d_%02d", v7_2);
        v4_4 = new Object[v11];
        v4_4[0] = Integer.valueOf(v3);
        v4_4[v14] = Integer.valueOf(v0_7);
        v6.monthKey = String.format("%d_%02d", v4_4);
        this.createMessageSendInfo();
        this.generateCaption();
        if(v13) {
            TextPaint v0_10 = (v6.messageOwner.media instanceof TL_messageMediaGame) ? Theme.chat_msgGameTextPaint : Theme.chat_msgTextPaint;
            if(SharedConfig.allowBigEmoji) {
                v1_8 = new int[v14];
            }

            v6.messageText = Emoji.replaceEmoji(v6.messageText, v0_10.getFontMetricsInt(), AndroidUtilities.dp(20f), false, ((int[])v1_3));
            if((((int[])v1_3)) != null && ((int[])v1_3)[0] >= v14 && ((int[])v1_3)[0] <= 3) {
                switch(((int[])v1_3)[0]) {
                    case 1: {
                        v0_10 = Theme.chat_msgTextPaintOneEmoji;
                        v1_9 = 32f;
                        break;
                    }
                    case 2: {
                        v0_10 = Theme.chat_msgTextPaintTwoEmoji;
                        v1_9 = 28f;
                        break;
                    }
                    default: {
                        v0_10 = Theme.chat_msgTextPaintThreeEmoji;
                        v1_9 = 24f;
                        break;
                    }
                }

                v1_1 = AndroidUtilities.dp(v1_9);
                v2_2 = v6.messageText.getSpans(0, v6.messageText.length(), EmojiSpan.class);
                if(v2_2 != null && v2_2.length > 0) {
                    for(v3 = 0; v3 < v2_2.length; ++v3) {
                        v2_2[v3].replaceFontMetrics(v0_10.getFontMetricsInt(), v1_1);
                    }
                }
            }

            v6.generateLayout(((User)v15));
        }

        v6.layoutCreated = v13;
        v6.generateThumbs(false);
        this.checkMediaExistance();
    }

    public MessageObject(int arg2, Message arg3, String arg4, String arg5, String arg6, boolean arg7, boolean arg8) {
        super();
        this.type = 1000;
        int v7 = arg7 ? 2 : 1;
        this.localType = v7;
        this.currentAccount = arg2;
        this.localName = arg5;
        this.localUserName = arg6;
        this.messageText = ((CharSequence)arg4);
        this.messageOwner = arg3;
        this.localChannel = arg8;
    }

    public MessageObject(int arg9, Message arg10, AbstractMap arg11, AbstractMap arg12, boolean arg13) {
        this(arg9, arg10, arg11, arg12, arg13, 0);
    }

    public MessageObject(int arg11, Message arg12, AbstractMap arg13, AbstractMap arg14, boolean arg15, long arg16) {
        this(arg11, arg12, arg13, arg14, null, null, arg15, arg16);
    }

    public MessageObject(int arg26, TL_channelAdminLogEvent arg27, ArrayList arg28, HashMap arg29, Chat arg30, int[] arg31) {
        float v2_2;
        int[] v6_10;
        int v2_1;
        CharSequence v6_7;
        String v11_3;
        Message v7_10;
        int v9_3;
        TL_message v6_3;
        char v6_2;
        String v6_1;
        int v18;
        String v13_1;
        char v7_8;
        int v11_1;
        String v9_1;
        char v9;
        StringBuilder v12_1;
        User v7_5;
        int v12;
        Object[] v11;
        String v8_1;
        String v7_3;
        TLObject v5_1;
        MessageObject v0 = this;
        TL_channelAdminLogEvent v1 = arg27;
        ArrayList v2 = arg28;
        Chat v3 = arg30;
        super();
        v0.type = 1000;
        if(v1.user_id > 0) {
            User v5 = MessagesController.getInstance(arg26).getUser(Integer.valueOf(v1.user_id));
        }
        else {
            v5_1 = null;
        }

        v0.currentEvent = v1;
        GregorianCalendar v7 = new GregorianCalendar();
        ((Calendar)v7).setTimeInMillis((((long)v1.date)) * 1000);
        int v8 = ((Calendar)v7).get(6);
        int v10 = ((Calendar)v7).get(1);
        int v7_1 = ((Calendar)v7).get(2);
        int v13 = 3;
        Object[] v14 = new Object[v13];
        int v6 = 0;
        v14[0] = Integer.valueOf(v10);
        v14[1] = Integer.valueOf(v7_1);
        v14[2] = Integer.valueOf(v8);
        v0.dateKey = String.format("%d_%02d_%02d", v14);
        v0.monthKey = String.format("%d_%02d", Integer.valueOf(v10), Integer.valueOf(v7_1));
        TL_peerChannel v7_2 = new TL_peerChannel();
        ((Peer)v7_2).channel_id = v3.id;
        if((v1.action instanceof TL_channelAdminLogEventActionChangeTitle)) {
            v7_3 = v1.action.new_value;
            if(v3.megagroup) {
                v8_1 = "EventLogEditedGroupTitle";
                v10 = 2131624716;
                v11 = new Object[]{v7_3};
            }
            else {
                v8_1 = "EventLogEditedChannelTitle";
                v10 = 2131624713;
                v11 = new Object[]{v7_3};
            }

            v7_3 = LocaleController.formatString(v8_1, v10, v11);
            goto label_90;
        }
        else {
            if((v1.action instanceof TL_channelAdminLogEventActionChangePhoto)) {
                v0.messageOwner = new TL_messageService();
                if((v1.action.new_photo instanceof TL_chatPhotoEmpty)) {
                    v0.messageOwner.action = new TL_messageActionChatDeletePhoto();
                    if(v3.megagroup) {
                        v7_3 = "EventLogRemovedWGroupPhoto";
                        v8 = 2131624760;
                    }
                    else {
                        v7_3 = "EventLogRemovedChannelPhoto";
                        v8 = 2131624757;
                    }
                }
                else {
                    v0.messageOwner.action = new TL_messageActionChatEditPhoto();
                    v0.messageOwner.action.photo = new TL_photo();
                    TL_photoSize v7_4 = new TL_photoSize();
                    v7_4.location = v1.action.new_photo.photo_small;
                    v7_4.type = "s";
                    v7_4.h = 80;
                    v7_4.w = 80;
                    v0.messageOwner.action.photo.sizes.add(v7_4);
                    v7_4 = new TL_photoSize();
                    v7_4.location = v1.action.new_photo.photo_big;
                    v7_4.type = "m";
                    v7_4.h = 640;
                    v7_4.w = 640;
                    v0.messageOwner.action.photo.sizes.add(v7_4);
                    if(v3.megagroup) {
                        v7_3 = "EventLogEditedGroupPhoto";
                        v8 = 2131624715;
                    }
                    else {
                        v7_3 = "EventLogEditedChannelPhoto";
                        v8 = 2131624712;
                    }
                }
            }
            else {
                v10 = 2131624706;
                v12 = 2131624734;
                if((v1.action instanceof TL_channelAdminLogEventActionParticipantJoin)) {
                    if(!v3.megagroup) {
                        goto label_156;
                    }

                    goto label_153;
                }
                else if((v1.action instanceof TL_channelAdminLogEventActionParticipantLeave)) {
                    v0.messageOwner = new TL_messageService();
                    v0.messageOwner.action = new TL_messageActionChatDeleteUser();
                    v0.messageOwner.action.user_id = v1.user_id;
                    if(v3.megagroup) {
                        v7_3 = "EventLogLeftGroup";
                        v8 = 2131624739;
                    }
                    else {
                        v7_3 = "EventLogLeftChannel";
                        v8 = 2131624738;
                    }
                }
                else {
                    goto label_181;
                }
            }

            v7_3 = LocaleController.getString(v7_3, v8);
            goto label_90;
        label_181:
            if((v1.action instanceof TL_channelAdminLogEventActionParticipantInvite)) {
                v0.messageOwner = new TL_messageService();
                v0.messageOwner.action = new TL_messageActionChatAddUser();
                v7_5 = MessagesController.getInstance(arg26).getUser(Integer.valueOf(v1.action.participant.user_id));
                if(v1.action.participant.user_id != v0.messageOwner.from_id) {
                    goto label_206;
                }
                else if(v3.megagroup) {
                label_153:
                    v7_3 = LocaleController.getString("EventLogGroupJoined", v12);
                    goto label_90;
                }
            }
            else {
                goto label_214;
            }

        label_156:
            v7_3 = LocaleController.getString("EventLogChannelJoined", v10);
            goto label_90;
        label_206:
            v0.messageText = v0.replaceWithLink(LocaleController.getString("EventLogAdded", 2131624700), "un2", ((TLObject)v7_5));
            CharSequence v7_6 = v0.messageText;
        label_90:
            v7_6 = v0.replaceWithLink(((CharSequence)v7_3), "un1", v5_1);
            goto label_92;
        label_214:
            char v10_1 = ' ';
            char v15 = '\n';
            if((v1.action instanceof TL_channelAdminLogEventActionParticipantToggleAdmin)) {
                v0.messageOwner = new TL_message();
                v7_5 = MessagesController.getInstance(arg26).getUser(Integer.valueOf(v1.action.prev_participant.user_id));
                v8_1 = LocaleController.getString("EventLogPromoted", 2131624746);
                v12_1 = new StringBuilder(String.format(v8_1, v0.getUserName(v7_5, v0.messageOwner.entities, v8_1.indexOf("%1$s"))));
                v12_1.append("\n");
                TL_channelAdminRights v7_7 = v1.action.prev_participant.admin_rights;
                TL_channelAdminRights v8_2 = v1.action.new_participant.admin_rights;
                if(v7_7 == null) {
                    v7_7 = new TL_channelAdminRights();
                }

                if(v8_2 == null) {
                    v8_2 = new TL_channelAdminRights();
                }

                if(v7_7.change_info != v8_2.change_info) {
                    v12_1.append(v15);
                    v9 = v8_2.change_info ? '+' : '-';
                    v12_1.append(v9);
                    v12_1.append(v10_1);
                    if(v3.megagroup) {
                        v9_1 = "EventLogPromotedChangeGroupInfo";
                        v11_1 = 2131624751;
                    }
                    else {
                        v9_1 = "EventLogPromotedChangeChannelInfo";
                        v11_1 = 2131624750;
                    }

                    v9_1 = LocaleController.getString(v9_1, v11_1);
                    v12_1.append(v9_1);
                }

                if(!v3.megagroup) {
                    if(v7_7.post_messages != v8_2.post_messages) {
                        v12_1.append(v15);
                        v9 = v8_2.post_messages ? '+' : '-';
                        v12_1.append(v9);
                        v12_1.append(v10_1);
                        v12_1.append(LocaleController.getString("EventLogPromotedPostMessages", 2131624755));
                    }

                    if(v7_7.edit_messages == v8_2.edit_messages) {
                        goto label_308;
                    }

                    v12_1.append(v15);
                    v9 = v8_2.edit_messages ? '+' : '-';
                    v12_1.append(v9);
                    v12_1.append(v10_1);
                    v12_1.append(LocaleController.getString("EventLogPromotedEditMessages", 2131624753));
                }

            label_308:
                if(v7_7.delete_messages != v8_2.delete_messages) {
                    v12_1.append(v15);
                    v9 = v8_2.delete_messages ? '+' : '-';
                    v12_1.append(v9);
                    v12_1.append(v10_1);
                    v12_1.append(LocaleController.getString("EventLogPromotedDeleteMessages", 2131624752));
                }

                if(v7_7.add_admins != v8_2.add_admins) {
                    v12_1.append(v15);
                    v9 = v8_2.add_admins ? '+' : '-';
                    v12_1.append(v9);
                    v12_1.append(v10_1);
                    v12_1.append(LocaleController.getString("EventLogPromotedAddAdmins", 2131624747));
                }

                if((v3.megagroup) && v7_7.ban_users != v8_2.ban_users) {
                    v12_1.append(v15);
                    v9 = v8_2.ban_users ? '+' : '-';
                    v12_1.append(v9);
                    v12_1.append(v10_1);
                    v12_1.append(LocaleController.getString("EventLogPromotedBanUsers", 2131624749));
                }

                if(v7_7.invite_users != v8_2.invite_users) {
                    v12_1.append(v15);
                    v9 = v8_2.invite_users ? '+' : '-';
                    v12_1.append(v9);
                    v12_1.append(v10_1);
                    v12_1.append(LocaleController.getString("EventLogPromotedAddUsers", 2131624748));
                }

                if((v3.megagroup) && v7_7.pin_messages != v8_2.pin_messages) {
                    v12_1.append(v15);
                    v7_8 = v8_2.pin_messages ? '+' : '-';
                    v12_1.append(v7_8);
                    v12_1.append(v10_1);
                    v12_1.append(LocaleController.getString("EventLogPromotedPinMessages", 2131624754));
                }

                v7_3 = v12_1.toString();
            label_92:
                v0.messageText = ((CharSequence)v7_3);
                goto label_1066;
            }

            if((v1.action instanceof TL_channelAdminLogEventActionParticipantToggleBan)) {
                v0.messageOwner = new TL_message();
                v7_5 = MessagesController.getInstance(arg26).getUser(Integer.valueOf(v1.action.prev_participant.user_id));
                TL_channelBannedRights v8_3 = v1.action.prev_participant.banned_rights;
                TL_channelBannedRights v9_2 = v1.action.new_participant.banned_rights;
                if(v3.megagroup) {
                    if(v9_2 != null && (v9_2.view_messages)) {
                        if(v9_2 == null) {
                        }
                        else if(v8_3 == null) {
                        }
                        else if(v9_2.until_date != v8_3.until_date) {
                            goto label_417;
                        }

                        goto label_628;
                    }

                label_417:
                    if(v9_2 == null || (AndroidUtilities.isBannedForever(v9_2.until_date))) {
                        v12_1 = new StringBuilder(LocaleController.getString("UserRestrictionsUntilForever", 2131626334));
                    }
                    else {
                        v12_1 = new StringBuilder();
                        int v14_1 = v9_2.until_date - v1.date;
                        v10 = v14_1 / 60 / 60 / 24;
                        v14_1 -= v10 * 60 * 60 * 24;
                        int v15_1 = v14_1 / 60 / 60;
                        v14_1 = (v14_1 - v15_1 * 60 * 60) / 60;
                        int v16 = 0;
                        while(v6 < v13) {
                            if(v6 == 0) {
                                if(v10 != 0) {
                                    v13_1 = LocaleController.formatPluralString("Days", v10);
                                    goto label_445;
                                }
                                else {
                                    goto label_458;
                                }
                            }
                            else if(v6 == 1) {
                                if(v15_1 != 0) {
                                    v13_1 = LocaleController.formatPluralString("Hours", v15_1);
                                    goto label_445;
                                }
                                else {
                                    goto label_458;
                                }
                            }
                            else if(v14_1 != 0) {
                                v13_1 = LocaleController.formatPluralString("Minutes", v14_1);
                            label_445:
                                v11_1 = v16 + 1;
                            }
                            else {
                            label_458:
                                v11_1 = v16;
                                v13_1 = null;
                            }

                            if(v13_1 != null) {
                                if(v12_1.length() > 0) {
                                    v18 = v10;
                                    v12_1.append(", ");
                                }
                                else {
                                    v18 = v10;
                                }

                                v12_1.append(v13_1);
                            }
                            else {
                                v18 = v10;
                            }

                            if(v11_1 == 2) {
                                break;
                            }

                            ++v6;
                            v16 = v11_1;
                            v10 = v18;
                            v13 = 3;
                        }
                    }

                    v6_1 = LocaleController.getString("EventLogRestrictedUntil", 2131624766);
                    StringBuilder v11_2 = new StringBuilder(String.format(v6_1, v0.getUserName(v7_5, v0.messageOwner.entities, v6_1.indexOf("%1$s")), v12_1.toString()));
                    if(v8_3 == null) {
                        v8_3 = new TL_channelBannedRights();
                    }

                    if(v9_2 == null) {
                        v9_2 = new TL_channelBannedRights();
                    }

                    if(v8_3.view_messages != v9_2.view_messages) {
                        v11_2.append('\n');
                        v11_2.append('\n');
                        v6_2 = !v9_2.view_messages ? '+' : '-';
                        v11_2.append(v6_2);
                        v11_2.append(' ');
                        v11_2.append(LocaleController.getString("EventLogRestrictedReadMessages", 2131624761));
                        v6 = 1;
                    }
                    else {
                        v6 = 0;
                    }

                    if(v8_3.send_messages != v9_2.send_messages) {
                        if(v6 == 0) {
                            v7_8 = '\n';
                            v11_2.append(v7_8);
                            v6 = 1;
                        }
                        else {
                            v7_8 = '\n';
                        }

                        v11_2.append(v7_8);
                        v7_8 = !v9_2.send_messages ? '+' : '-';
                        v11_2.append(v7_8);
                        v11_2.append(' ');
                        v11_2.append(LocaleController.getString("EventLogRestrictedSendMessages", 2131624764));
                    }

                    if(v8_3.send_stickers != v9_2.send_stickers || v8_3.send_inline != v9_2.send_inline || v8_3.send_gifs != v9_2.send_gifs || v8_3.send_games != v9_2.send_games) {
                        if(v6 == 0) {
                            v7_8 = '\n';
                            v11_2.append(v7_8);
                            v6 = 1;
                        }
                        else {
                            v7_8 = '\n';
                        }

                        v11_2.append(v7_8);
                        v7_8 = !v9_2.send_stickers ? '+' : '-';
                        v11_2.append(v7_8);
                        v11_2.append(' ');
                        v11_2.append(LocaleController.getString("EventLogRestrictedSendStickers", 2131624765));
                    }

                    if(v8_3.send_media != v9_2.send_media) {
                        if(v6 == 0) {
                            v7_8 = '\n';
                            v11_2.append(v7_8);
                            v6 = 1;
                        }
                        else {
                            v7_8 = '\n';
                        }

                        v11_2.append(v7_8);
                        v7_8 = !v9_2.send_media ? '+' : '-';
                        v11_2.append(v7_8);
                        v11_2.append(' ');
                        v11_2.append(LocaleController.getString("EventLogRestrictedSendMedia", 2131624763));
                    }

                    if(v8_3.embed_links != v9_2.embed_links) {
                        if(v6 == 0) {
                            v6_2 = '\n';
                            v11_2.append(v6_2);
                        }
                        else {
                            v6_2 = '\n';
                        }

                        v11_2.append(v6_2);
                        v6_2 = !v9_2.embed_links ? '+' : '-';
                        v11_2.append(v6_2);
                        v11_2.append(' ');
                        v11_2.append(LocaleController.getString("EventLogRestrictedSendEmbed", 2131624762));
                    }

                    v6_1 = v11_2.toString();
                    goto label_661;
                }

            label_628:
                if(v9_2 != null) {
                    if(v8_3 != null && !v9_2.view_messages) {
                        goto label_635;
                    }

                    v6_1 = "EventLogChannelRestricted";
                    v8 = 2131624707;
                }
                else {
                label_635:
                    v6_1 = "EventLogChannelUnrestricted";
                    v8 = 2131624708;
                }

                v6_1 = LocaleController.getString(v6_1, v8);
                v6_1 = String.format(v6_1, v0.getUserName(v7_5, v0.messageOwner.entities, v6_1.indexOf("%1$s")));
            }
            else {
                if((v1.action instanceof TL_channelAdminLogEventActionUpdatePinned)) {
                    if((v1.action.message instanceof TL_messageEmpty)) {
                        v6_1 = "EventLogUnpinnedMessages";
                        v7_1 = 2131624774;
                    }
                    else {
                        v6_1 = "EventLogPinnedMessages";
                        v7_1 = 2131624743;
                    }
                }
                else if((v1.action instanceof TL_channelAdminLogEventActionToggleSignatures)) {
                    if(v1.action.new_value) {
                        v6_1 = "EventLogToggledSignaturesOn";
                        v7_1 = 2131624773;
                    }
                    else {
                        v6_1 = "EventLogToggledSignaturesOff";
                        v7_1 = 2131624772;
                    }
                }
                else if((v1.action instanceof TL_channelAdminLogEventActionToggleInvites)) {
                    if(v1.action.new_value) {
                        v6_1 = "EventLogToggledInvitesOn";
                        v7_1 = 2131624771;
                    }
                    else {
                        v6_1 = "EventLogToggledInvitesOff";
                        v7_1 = 2131624770;
                    }
                }
                else if((v1.action instanceof TL_channelAdminLogEventActionDeleteMessage)) {
                    v6_1 = "EventLogDeletedMessages";
                    v7_1 = 2131624709;
                }
                else if((v1.action instanceof TL_channelAdminLogEventActionTogglePreHistoryHidden)) {
                    if(v1.action.new_value) {
                        v6_1 = "EventLogToggledInvitesHistoryOff";
                        v7_1 = 2131624768;
                    }
                    else {
                        v6_1 = "EventLogToggledInvitesHistoryOn";
                        v7_1 = 2131624769;
                    }
                }
                else if((v1.action instanceof TL_channelAdminLogEventActionChangeAbout)) {
                    if(v3.megagroup) {
                        v6_1 = "EventLogEditedGroupDescription";
                        v8 = 2131624714;
                    }
                    else {
                        v6_1 = "EventLogEditedChannelDescription";
                        v8 = 2131624711;
                    }

                    v6_1 = LocaleController.getString(v6_1, v8);
                    v0.messageText = v0.replaceWithLink(((CharSequence)v6_1), "un1", v5_1);
                    v6_3 = new TL_message();
                    ((Message)v6_3).out = false;
                    ((Message)v6_3).unread = false;
                    ((Message)v6_3).from_id = v1.user_id;
                    ((Message)v6_3).to_id = ((Peer)v7_2);
                    ((Message)v6_3).date = v1.date;
                    ((Message)v6_3).message = v1.action.new_value;
                    if(!TextUtils.isEmpty(v1.action.prev_value)) {
                        ((Message)v6_3).media = new TL_messageMediaWebPage();
                        ((Message)v6_3).media.webpage = new TL_webPage();
                        ((Message)v6_3).media.webpage.flags = 10;
                        ((Message)v6_3).media.webpage.display_url = "";
                        ((Message)v6_3).media.webpage.url = "";
                        ((Message)v6_3).media.webpage.site_name = LocaleController.getString("EventLogPreviousGroupDescription", 2131624744);
                        ((Message)v6_3).media.webpage.description = v1.action.prev_value;
                        goto label_1067;
                    }

                    ((Message)v6_3).media = new TL_messageMediaEmpty();
                    goto label_1067;
                }
                else {
                    if((v1.action instanceof TL_channelAdminLogEventActionChangeUsername)) {
                        v6_1 = v1.action.new_value;
                        if(!TextUtils.isEmpty(((CharSequence)v6_1))) {
                            if(v3.megagroup) {
                                v8_1 = "EventLogChangedGroupLink";
                                v9_3 = 2131624704;
                            }
                            else {
                                v8_1 = "EventLogChangedChannelLink";
                                v9_3 = 2131624703;
                            }
                        }
                        else if(v3.megagroup) {
                            v8_1 = "EventLogRemovedGroupLink";
                            v9_3 = 2131624758;
                        }
                        else {
                            v8_1 = "EventLogRemovedChannelLink";
                            v9_3 = 2131624756;
                        }

                        v8_1 = LocaleController.getString(v8_1, v9_3);
                        v0.messageText = v0.replaceWithLink(((CharSequence)v8_1), "un1", v5_1);
                        TL_message v8_4 = new TL_message();
                        ((Message)v8_4).out = false;
                        ((Message)v8_4).unread = false;
                        ((Message)v8_4).from_id = v1.user_id;
                        ((Message)v8_4).to_id = ((Peer)v7_2);
                        ((Message)v8_4).date = v1.date;
                        v6_1 = !TextUtils.isEmpty(((CharSequence)v6_1)) ? "https://" + MessagesController.getInstance(arg26).linkPrefix + "/" + v6_1 : "";
                        ((Message)v8_4).message = v6_1;
                        TL_messageEntityUrl v6_4 = new TL_messageEntityUrl();
                        v6_4.offset = 0;
                        v6_4.length = ((Message)v8_4).message.length();
                        ((Message)v8_4).entities.add(v6_4);
                        if(!TextUtils.isEmpty(v1.action.prev_value)) {
                            ((Message)v8_4).media = new TL_messageMediaWebPage();
                            ((Message)v8_4).media.webpage = new TL_webPage();
                            ((Message)v8_4).media.webpage.flags = 10;
                            ((Message)v8_4).media.webpage.display_url = "";
                            ((Message)v8_4).media.webpage.url = "";
                            ((Message)v8_4).media.webpage.site_name = LocaleController.getString("EventLogPreviousLink", 2131624745);
                            WebPage v6_5 = ((Message)v8_4).media.webpage;
                            v6_5.description = "https://" + MessagesController.getInstance(arg26).linkPrefix + "/" + v1.action.prev_value;
                        }
                        else {
                            ((Message)v8_4).media = new TL_messageMediaEmpty();
                        }

                        v6_3 = v8_4;
                        goto label_1067;
                    }

                    if((v1.action instanceof TL_channelAdminLogEventActionEditMessage)) {
                        v6_3 = new TL_message();
                        ((Message)v6_3).out = false;
                        ((Message)v6_3).unread = false;
                        ((Message)v6_3).from_id = v1.user_id;
                        ((Message)v6_3).to_id = ((Peer)v7_2);
                        ((Message)v6_3).date = v1.date;
                        v7_10 = v1.action.new_message;
                        Message v8_5 = v1.action.prev_message;
                        v10 = 2131624741;
                        if(v7_10.media == null || ((v7_10.media instanceof TL_messageMediaEmpty)) || ((v7_10.media instanceof TL_messageMediaWebPage))) {
                            v0.messageText = v0.replaceWithLink(LocaleController.getString("EventLogEditedMessages", 2131624719), "un1", v5_1);
                            ((Message)v6_3).message = v7_10.message;
                            ((Message)v6_3).media = new TL_messageMediaWebPage();
                            ((Message)v6_3).media.webpage = new TL_webPage();
                            ((Message)v6_3).media.webpage.site_name = LocaleController.getString("EventLogOriginalMessages", 2131624742);
                            if(TextUtils.isEmpty(v8_5.message)) {
                            label_1013:
                                ((Message)v6_3).media.webpage.description = LocaleController.getString("EventLogOriginalCaptionEmpty", v10);
                                goto label_1023;
                            }

                        label_1019:
                            ((Message)v6_3).media.webpage.description = v8_5.message;
                        }
                        else {
                            v9_3 = TextUtils.equals(v7_10.message, v8_5.message) ^ 1;
                            if(v7_10.media.getClass() == v8_5.media.getClass()) {
                                if(v7_10.media.photo != null && v8_5.media.photo != null && v7_10.media.photo.id != v8_5.media.photo.id) {
                                    goto label_955;
                                }

                                if(v7_10.media.document != null && v8_5.media.document != null && v7_10.media.document.id != v8_5.media.document.id) {
                                    goto label_955;
                                }

                                v11_1 = 0;
                            }
                            else {
                            label_955:
                                v11_1 = 1;
                            }

                            if(v11_1 != 0 && v9_3 != 0) {
                                v11_3 = "EventLogEditedMediaCaption";
                                v12 = 2131624718;
                            }
                            else if(v9_3 != 0) {
                                v11_3 = "EventLogEditedCaption";
                                v12 = 2131624710;
                            }
                            else {
                                v11_3 = "EventLogEditedMedia";
                                v12 = 2131624717;
                            }

                            v0.messageText = v0.replaceWithLink(LocaleController.getString(v11_3, v12), "un1", v5_1);
                            ((Message)v6_3).media = v7_10.media;
                            if(v9_3 == 0) {
                                goto label_1023;
                            }

                            ((Message)v6_3).media.webpage = new TL_webPage();
                            ((Message)v6_3).media.webpage.site_name = LocaleController.getString("EventLogOriginalCaption", 2131624740);
                            if(!TextUtils.isEmpty(v8_5.message)) {
                                goto label_1019;
                            }

                            goto label_1013;
                        }

                    label_1023:
                        ((Message)v6_3).reply_markup = v7_10.reply_markup;
                        if(((Message)v6_3).media.webpage == null) {
                            goto label_1067;
                        }

                        ((Message)v6_3).media.webpage.flags = 10;
                        ((Message)v6_3).media.webpage.display_url = "";
                        ((Message)v6_3).media.webpage.url = "";
                        goto label_1067;
                    }

                    if(!(v1.action instanceof TL_channelAdminLogEventActionChangeStickerSet)) {
                        goto label_1058;
                    }

                    InputStickerSet v6_6 = v1.action.new_stickerset;
                    if(v6_6 != null) {
                        if((v6_6 instanceof TL_inputStickerSetEmpty)) {
                        }
                        else {
                            v6_1 = "EventLogChangedStickersSet";
                            v7_1 = 2131624705;
                            goto label_658;
                        }
                    }

                    v6_1 = "EventLogRemovedStickersSet";
                    v7_1 = 2131624759;
                }

            label_658:
                v6_7 = v0.replaceWithLink(LocaleController.getString(v6_1, v7_1), "un1", v5_1);
                goto label_661;
            label_1058:
                v6_1 = "unsupported " + v1.action;
            }

        label_661:
            v0.messageText = v6_7;
        label_1066:
            v6_3 = null;
        }

    label_1067:
        if(v0.messageOwner == null) {
            v0.messageOwner = new TL_messageService();
        }

        v0.messageOwner.message = v0.messageText.toString();
        v0.messageOwner.from_id = v1.user_id;
        v0.messageOwner.date = v1.date;
        v7_10 = v0.messageOwner;
        v9_3 = arg31[0];
        arg31[0] = v9_3 + 1;
        v7_10.id = v9_3;
        v0.eventId = v1.id;
        v0.messageOwner.out = false;
        v0.messageOwner.to_id = new TL_peerChannel();
        v0.messageOwner.to_id.channel_id = v3.id;
        v0.messageOwner.unread = false;
        v8 = -2147483648;
        if(v3.megagroup) {
            v0.messageOwner.flags |= v8;
        }

        MediaController v7_11 = MediaController.getInstance();
        if(v1.action.message != null && !(v1.action.message instanceof TL_messageEmpty)) {
            Message v6_9 = v1.action.message;
        }

        if(v6_3 != null) {
            ((Message)v6_3).out = false;
            v10 = arg31[0];
            arg31[0] = v10 + 1;
            ((Message)v6_3).id = v10;
            ((Message)v6_3).reply_to_msg_id = 0;
            ((Message)v6_3).flags &= -32769;
            if(v3.megagroup) {
                ((Message)v6_3).flags |= v8;
            }

            MessageObject v3_1 = new MessageObject(arg26, v6_3, null, null, true, v0.eventId);
            if(v3_1.contentType >= 0) {
                if(v7_11.isPlayingMessage(v3_1)) {
                    MessageObject v4 = v7_11.getPlayingMessageObject();
                    v3_1.audioProgress = v4.audioProgress;
                    v3_1.audioProgressSec = v4.audioProgressSec;
                }

                this.createDateArray(arg26, arg27, arg28, arg29);
                v6 = 1;
                v2.add(arg28.size() - 1, v3_1);
                goto label_1167;
            }

            v6 = 1;
            v0.contentType = -1;
        }
        else {
            v6 = 1;
        }

    label_1167:
        if(v0.contentType >= 0) {
            this.createDateArray(arg26, arg27, arg28, arg29);
            v2.add(arg28.size() - v6, v0);
            if(v0.messageText == null) {
                v0.messageText = "";
            }

            this.setType();
            this.measureInlineBotButtons();
            this.generateCaption();
            TextPaint v1_1 = (v0.messageOwner.media instanceof TL_messageMediaGame) ? Theme.chat_msgGameTextPaint : Theme.chat_msgTextPaint;
            if(SharedConfig.allowBigEmoji) {
                v2_1 = 1;
                v6_10 = new int[1];
            }
            else {
                v2_1 = 1;
                v6_10 = null;
            }

            v0.messageText = Emoji.replaceEmoji(v0.messageText, v1_1.getFontMetricsInt(), AndroidUtilities.dp(20f), false, v6_10);
            if(v6_10 != null && v6_10[0] >= v2_1 && v6_10[0] <= 3) {
                switch(v6_10[0]) {
                    case 1: {
                        v1_1 = Theme.chat_msgTextPaintOneEmoji;
                        v2_2 = 32f;
                        break;
                    }
                    case 2: {
                        v1_1 = Theme.chat_msgTextPaintTwoEmoji;
                        v2_2 = 28f;
                        break;
                    }
                    default: {
                        v1_1 = Theme.chat_msgTextPaintThreeEmoji;
                        v2_2 = 24f;
                        break;
                    }
                }

                v2_1 = AndroidUtilities.dp(v2_2);
                Object[] v3_2 = v0.messageText.getSpans(0, v0.messageText.length(), EmojiSpan.class);
                if(v3_2 != null && v3_2.length > 0) {
                    int v4_1;
                    for(v4_1 = 0; v4_1 < v3_2.length; ++v4_1) {
                        v3_2[v4_1].replaceFontMetrics(v1_1.getFontMetricsInt(), v2_1);
                    }
                }
            }

            if(v7_11.isPlayingMessage(v0)) {
                MessageObject v1_2 = v7_11.getPlayingMessageObject();
                v0.audioProgress = v1_2.audioProgress;
                v0.audioProgressSec = v1_2.audioProgressSec;
            }

            v0.generateLayout(((User)v5_1));
            v0.layoutCreated = true;
            v0.generateThumbs(false);
            this.checkMediaExistance();
        }
    }

    public static boolean addEntitiesToText(CharSequence arg14, ArrayList arg15, boolean arg16, int arg17, boolean arg18, boolean arg19, boolean arg20) {
        URLSpanBrowser v8_1;
        URLSpanBotCommand v11_2;
        URLSpanUserMention v2_1;
        StringBuilder v11_1;
        TypefaceSpan v2;
        byte v6;
        CharSequence v0 = arg14;
        if(!(v0 instanceof Spannable)) {
            return 0;
        }

        CharSequence v1 = v0;
        int v3 = arg15.size();
        Object[] v4 = ((Spannable)v1).getSpans(0, arg14.length(), URLSpan.class);
        boolean v7 = v4 == null || v4.length <= 0 ? false : true;
        if(arg19) {
            v6 = 2;
        }
        else if(arg16) {
            v6 = 1;
        }
        else {
            v6 = 0;
        }

        boolean v8 = v7;
        int v7_1;
        for(v7_1 = 0; v7_1 < v3; ++v7_1) {
            Object v10 = arg15.get(v7_1);
            if(((MessageEntity)v10).length > 0 && ((MessageEntity)v10).offset >= 0) {
                if(((MessageEntity)v10).offset >= arg14.length()) {
                }
                else {
                    if(((MessageEntity)v10).offset + ((MessageEntity)v10).length > arg14.length()) {
                        ((MessageEntity)v10).length = arg14.length() - ((MessageEntity)v10).offset;
                    }

                    if((!arg20 || ((v10 instanceof TL_messageEntityBold)) || ((v10 instanceof TL_messageEntityItalic)) || ((v10 instanceof TL_messageEntityCode)) || ((v10 instanceof TL_messageEntityPre)) || ((v10 instanceof TL_messageEntityMentionName)) || ((v10 instanceof TL_inputMessageEntityMentionName))) && (v4 != null && v4.length > 0)) {
                        int v11;
                        for(v11 = 0; v11 < v4.length; ++v11) {
                            if(v4[v11] == null) {
                            }
                            else {
                                int v12 = ((Spannable)v1).getSpanStart(v4[v11]);
                                int v13 = ((Spannable)v1).getSpanEnd(v4[v11]);
                                if(((MessageEntity)v10).offset > v12 || ((MessageEntity)v10).offset + ((MessageEntity)v10).length < v12) {
                                    if(((MessageEntity)v10).offset > v13) {
                                    }
                                    else if(((MessageEntity)v10).offset + ((MessageEntity)v10).length >= v13) {
                                        goto label_83;
                                    }

                                    goto label_87;
                                }

                            label_83:
                                ((Spannable)v1).removeSpan(v4[v11]);
                                v4[v11] = null;
                            }

                        label_87:
                        }
                    }

                    int v5 = 33;
                    if((v10 instanceof TL_messageEntityBold)) {
                        v2 = new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                    }
                    else if((v10 instanceof TL_messageEntityItalic)) {
                        v2 = new TypefaceSpan(AndroidUtilities.getTypeface("fonts/ritalic.ttf"));
                    }
                    else {
                        if(!(v10 instanceof TL_messageEntityCode)) {
                            if((v10 instanceof TL_messageEntityPre)) {
                            }
                            else if((v10 instanceof TL_messageEntityMentionName)) {
                                if(arg18) {
                                    v11_1 = new StringBuilder();
                                    v11_1.append("");
                                    v11_1.append(v10.user_id);
                                    v2_1 = new URLSpanUserMention(v11_1.toString(), v6);
                                    goto label_97;
                                }
                                else {
                                    goto label_257;
                                }
                            }
                            else if(!(v10 instanceof TL_inputMessageEntityMentionName)) {
                                if(!arg20) {
                                    String v2_2 = TextUtils.substring(arg14, ((MessageEntity)v10).offset, ((MessageEntity)v10).offset + ((MessageEntity)v10).length);
                                    if((v10 instanceof TL_messageEntityBotCommand)) {
                                        v11_2 = new URLSpanBotCommand(v2_2, v6);
                                    }
                                    else {
                                        if(!(v10 instanceof TL_messageEntityHashtag) && (!arg18 || !(v10 instanceof TL_messageEntityMention)) && !(v10 instanceof TL_messageEntityCashtag)) {
                                            if((v10 instanceof TL_messageEntityEmail)) {
                                                StringBuilder v12_1 = new StringBuilder();
                                                v12_1.append("mailto:");
                                                v12_1.append(v2_2);
                                                URLSpanReplacement v11_3 = new URLSpanReplacement(v12_1.toString());
                                                goto label_154;
                                            }

                                            if(!(v10 instanceof TL_messageEntityUrl)) {
                                                if(!(v10 instanceof TL_messageEntityPhone)) {
                                                    goto label_237;
                                                }

                                                String v8_2 = b.b(v2_2);
                                                if(v2_2.startsWith("+")) {
                                                    v8_2 = "+" + v8_2;
                                                }

                                                v11_1 = new StringBuilder();
                                                v11_1.append("tel:");
                                                v11_1.append(v8_2);
                                                ((Spannable)v1).setSpan(new URLSpanBrowser(v11_1.toString()), ((MessageEntity)v10).offset, ((MessageEntity)v10).offset + ((MessageEntity)v10).length, v5);
                                            }
                                            else if(Browser.isPassportUrl(((MessageEntity)v10).url)) {
                                                goto label_257;
                                            }
                                            else {
                                                if((v2_2.toLowerCase().startsWith("http")) || (v2_2.toLowerCase().startsWith("tg://"))) {
                                                    v8_1 = new URLSpanBrowser(v2_2);
                                                }
                                                else {
                                                    v11_1 = new StringBuilder();
                                                    v11_1.append("http://");
                                                    v11_1.append(v2_2);
                                                    v8_1 = new URLSpanBrowser(v11_1.toString());
                                                }

                                                ((Spannable)v1).setSpan(v8_1, ((MessageEntity)v10).offset, ((MessageEntity)v10).offset + ((MessageEntity)v10).length, v5);
                                            }

                                            v8 = true;
                                            goto label_257;
                                        label_237:
                                            if(!(v10 instanceof TL_messageEntityTextUrl)) {
                                                goto label_257;
                                            }

                                            if(Browser.isPassportUrl(((MessageEntity)v10).url)) {
                                                goto label_257;
                                            }

                                            URLSpanReplacement v2_4 = new URLSpanReplacement(((MessageEntity)v10).url);
                                            goto label_97;
                                        }

                                        URLSpanNoUnderline v11_4 = new URLSpanNoUnderline(v2_2);
                                    }

                                label_154:
                                    ((Spannable)v1).setSpan(v11_2, ((MessageEntity)v10).offset, ((MessageEntity)v10).offset + ((MessageEntity)v10).length, v5);
                                }
                                else {
                                }

                                goto label_257;
                            }
                            else if(arg18) {
                                v11_1 = new StringBuilder();
                                v11_1.append("");
                                v11_1.append(v10.user_id.user_id);
                                v2_1 = new URLSpanUserMention(v11_1.toString(), v6);
                                goto label_97;
                            }
                            else {
                                goto label_257;
                            }
                        }

                        URLSpanMono v2_5 = new URLSpanMono(v1, ((MessageEntity)v10).offset, ((MessageEntity)v10).offset + ((MessageEntity)v10).length, v6);
                    }

                label_97:
                    ((Spannable)v1).setSpan(v2_1, ((MessageEntity)v10).offset, ((MessageEntity)v10).offset + ((MessageEntity)v10).length, v5);
                }
            }

        label_257:
        }

        return v8;
    }

    private boolean addEntitiesToText(CharSequence arg9, boolean arg10) {
        return MessageObject.addEntitiesToText(arg9, this.messageOwner.entities, this.isOutOwner(), this.type, true, false, arg10);
    }

    public boolean addEntitiesToText(CharSequence arg9, boolean arg10, boolean arg11) {
        return MessageObject.addEntitiesToText(arg9, this.messageOwner.entities, this.isOutOwner(), this.type, true, arg10, arg11);
    }

    public static void addLinks(boolean arg1, CharSequence arg2) {
        MessageObject.addLinks(arg1, arg2, true);
    }

    public static void addLinks(boolean arg2, CharSequence arg3, boolean arg4) {
        if(((arg3 instanceof Spannable)) && (MessageObject.containsUrls(arg3))) {
            if(arg3.length() >= 1000) {
                goto label_14;
            }

            try {
                Linkify.addLinks(arg3, 5);
                goto label_17;
            label_14:
                Linkify.addLinks(arg3, 1);
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
            }

        label_17:
            MessageObject.addUsernamesAndHashtags(arg2, arg3, arg4);
        }
    }

    private static void addUsernamesAndHashtags(boolean arg6, CharSequence arg7, boolean arg8) {
        try {
            if(MessageObject.urlPattern == null) {
                MessageObject.urlPattern = Pattern.compile("(^|\\s)/[a-zA-Z@\\d_]{1,255}|(^|\\s)@[a-zA-Z\\d_]{1,32}|(^|\\s)#[\\w.]+|(^|\\s)\\$[A-Z]{3,8}([ ,.]|$)");
            }

            Matcher v0 = MessageObject.urlPattern.matcher(arg7);
            while(v0.find()) {
                int v1 = v0.start();
                int v2 = v0.end();
                int v3 = arg7.charAt(v1);
                int v5 = 47;
                if(v3 != 64 && v3 != 35 && v3 != v5 && v3 != 36) {
                    ++v1;
                }

                URLSpanBotCommand v3_1 = null;
                if(arg7.charAt(v1) != v5) {
                    URLSpanNoUnderline v3_2 = new URLSpanNoUnderline(arg7.subSequence(v1, v2).toString());
                }
                else if(arg8) {
                    v3_1 = new URLSpanBotCommand(arg7.subSequence(v1, v2).toString(), ((int)arg6));
                }

                if(v3_1 == null) {
                    continue;
                }

                arg7.setSpan(v3_1, v1, v2, 0);
            }
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public void applyNewText() {
        if(TextUtils.isEmpty(this.messageOwner.message)) {
            return;
        }

        User v0 = null;
        if(this.isFromUser()) {
            v0 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(this.messageOwner.from_id));
        }

        this.messageText = this.messageOwner.message;
        TextPaint v1 = (this.messageOwner.media instanceof TL_messageMediaGame) ? Theme.chat_msgGameTextPaint : Theme.chat_msgTextPaint;
        this.messageText = Emoji.replaceEmoji(this.messageText, v1.getFontMetricsInt(), AndroidUtilities.dp(20f), false);
        this.generateLayout(v0);
    }

    public void backupCaptionAndText() {
        this.messageTextBU = this.messageText;
        this.captionBU = this.caption;
        try {
            this.messageOwnerMessageBU = this.messageOwner.message;
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public static boolean canDeleteMessage(int arg2, Message arg3, Chat arg4) {
        if(arg3.id < 0) {
            return 1;
        }

        if(arg4 == null && arg3.to_id.channel_id != 0) {
            arg4 = MessagesController.getInstance(arg2).getChat(Integer.valueOf(arg3.to_id.channel_id));
        }

        boolean v0 = false;
        if(ChatObject.isChannel(arg4)) {
            if(arg3.id != 1) {
                if(!arg4.creator) {
                    if(arg4.admin_rights != null) {
                        if(arg4.admin_rights.delete_messages) {
                        }
                        else if(!arg3.out) {
                            goto label_27;
                        }

                        goto label_33;
                    }

                label_27:
                    if(!arg4.megagroup) {
                        return v0;
                    }

                    if(!arg3.out) {
                        return v0;
                    }

                    if(arg3.from_id <= 0) {
                        return v0;
                    }
                }

            label_33:
                v0 = true;
            }

            return v0;
        }

        if((MessageObject.isOut(arg3)) || !ChatObject.isChannel(arg4)) {
            v0 = true;
        }

        return v0;
    }

    public boolean canDeleteMessage(Chat arg6) {
        boolean v6 = this.eventId != 0 || !MessageObject.canDeleteMessage(this.currentAccount, this.messageOwner, arg6) ? false : true;
        return v6;
    }

    public boolean canEditMedia() {
        boolean v1 = false;
        if(this.isSecretMedia()) {
            return 0;
        }

        if((this.messageOwner.media instanceof TL_messageMediaPhoto)) {
            return 1;
        }

        if(((this.messageOwner.media instanceof TL_messageMediaDocument)) && !this.isVoice() && !this.isSticker() && !this.isRoundVideo()) {
            v1 = true;
        }

        return v1;
    }

    public static boolean canEditMessage(int arg4, Message arg5, Chat arg6) {
        boolean v0 = false;
        if(arg6 != null && ((arg6.left) || (arg6.kicked))) {
            return 0;
        }

        if(arg5 != null && arg5.to_id != null) {
            if(arg5.media != null) {
                if(MessageObject.isRoundVideoDocument(arg5.media.document)) {
                }
                else if(!MessageObject.isStickerDocument(arg5.media.document)) {
                    goto label_20;
                }

                return 0;
            }

        label_20:
            if(arg5.action != null && !(arg5.action instanceof TL_messageActionEmpty)) {
                return 0;
            }

            if(MessageObject.isForwardedMessage(arg5)) {
                return 0;
            }

            if(arg5.via_bot_id != 0) {
                return 0;
            }

            if(arg5.id < 0) {
                return 0;
            }

            if(arg5.from_id == arg5.to_id.user_id && arg5.from_id == UserConfig.getInstance(arg4).getClientUserId() && !MessageObject.isLiveLocationMessage(arg5) && !(arg5.media instanceof TL_messageMediaContact)) {
                return 1;
            }

            if(arg6 == null && arg5.to_id.channel_id != 0) {
                arg6 = MessagesController.getInstance(arg4).getChat(Integer.valueOf(arg5.to_id.channel_id));
                if(arg6 == null) {
                    return 0;
                }
            }

            if(arg5.media != null && !(arg5.media instanceof TL_messageMediaEmpty) && !(arg5.media instanceof TL_messageMediaPhoto) && !(arg5.media instanceof TL_messageMediaDocument) && !(arg5.media instanceof TL_messageMediaWebPage)) {
                return 0;
            }

            if((arg5.out) && arg6 != null && (arg6.megagroup)) {
                if(!arg6.creator) {
                    if(arg6.admin_rights == null) {
                    }
                    else if(arg6.admin_rights.pin_messages) {
                        return 1;
                    }

                    goto label_86;
                }

                return 1;
            }

        label_86:
            if(Math.abs(arg5.date - ConnectionsManager.getInstance(arg4).getCurrentTime()) > MessagesController.getInstance(arg4).maxEditTime) {
                return 0;
            }

            if(arg5.to_id.channel_id == 0) {
                if(((arg5.out) || arg5.from_id == UserConfig.getInstance(arg4).getClientUserId()) && (((arg5.media instanceof TL_messageMediaPhoto)) || ((arg5.media instanceof TL_messageMediaDocument)) && !MessageObject.isStickerMessage(arg5) || ((arg5.media instanceof TL_messageMediaEmpty)) || ((arg5.media instanceof TL_messageMediaWebPage)) || arg5.media == null)) {
                    v0 = true;
                }

                return v0;
            }

            if(!arg6.megagroup || !arg5.out) {
                if(!arg6.megagroup) {
                    if(!arg6.creator) {
                        if(arg6.admin_rights == null) {
                            return 0;
                        }
                        else if(!arg6.admin_rights.edit_messages) {
                            if(arg5.out) {
                                goto label_137;
                            }

                            return 0;
                        }
                    }

                label_137:
                    if(!arg5.post) {
                        return 0;
                    }
                }
                else {
                    return 0;
                }
            }

            if(!(arg5.media instanceof TL_messageMediaPhoto) && (!(arg5.media instanceof TL_messageMediaDocument) || (MessageObject.isStickerMessage(arg5))) && !(arg5.media instanceof TL_messageMediaEmpty) && !(arg5.media instanceof TL_messageMediaWebPage) && arg5.media != null) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public boolean canEditMessage(Chat arg3) {
        return MessageObject.canEditMessage(this.currentAccount, this.messageOwner, arg3);
    }

    public static boolean canEditMessageAnytime(int arg4, Message arg5, Chat arg6) {
        if(arg5 != null && arg5.to_id != null) {
            if(arg5.media != null) {
                if(MessageObject.isRoundVideoDocument(arg5.media.document)) {
                }
                else if(!MessageObject.isStickerDocument(arg5.media.document)) {
                    goto label_14;
                }

                return 0;
            }

        label_14:
            if(arg5.action != null && !(arg5.action instanceof TL_messageActionEmpty)) {
                return 0;
            }

            if(MessageObject.isForwardedMessage(arg5)) {
                return 0;
            }

            if(arg5.via_bot_id != 0) {
                return 0;
            }

            if(arg5.id < 0) {
                return 0;
            }

            if(arg5.from_id == arg5.to_id.user_id && arg5.from_id == UserConfig.getInstance(arg4).getClientUserId() && !MessageObject.isLiveLocationMessage(arg5)) {
                return 1;
            }

            if(arg6 == null && arg5.to_id.channel_id != 0) {
                arg6 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Integer.valueOf(arg5.to_id.channel_id));
                if(arg6 == null) {
                    return 0;
                }
            }

            if(!arg5.out) {
                return 0;
            }

            if(arg6 == null) {
                return 0;
            }

            if(!arg6.megagroup) {
                return 0;
            }

            if(!arg6.creator) {
                if(arg6.admin_rights == null) {
                }
                else if(arg6.admin_rights.pin_messages) {
                    return 1;
                }

                return 0;
            }

            return 1;
        }

        return 0;
    }

    public boolean canEditMessageAnytime(Chat arg3) {
        return MessageObject.canEditMessageAnytime(this.currentAccount, this.messageOwner, arg3);
    }

    public boolean canStreamVideo() {
        Document v0 = this.getDocument();
        if(v0 == null) {
            return 0;
        }

        if(SharedConfig.streamAllVideo) {
            return 1;
        }

        int v2;
        for(v2 = 0; v2 < v0.attributes.size(); ++v2) {
            Object v3 = v0.attributes.get(v2);
            if((v3 instanceof TL_documentAttributeVideo)) {
                return ((DocumentAttribute)v3).supports_streaming;
            }
        }

        return 0;
    }

    public boolean checkLayout() {
        if(this.type == 0 && this.messageOwner.to_id != null && this.messageText != null) {
            if(this.messageText.length() == 0) {
            }
            else {
                if(this.layoutCreated) {
                    int v0 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : AndroidUtilities.displaySize.x;
                    if(Math.abs(this.generatedWithMinSize - v0) <= AndroidUtilities.dp(52f)) {
                        goto label_27;
                    }

                    this.layoutCreated = false;
                }

            label_27:
                if(this.layoutCreated) {
                    return 0;
                }

                this.layoutCreated = true;
                User v2 = null;
                if(this.isFromUser()) {
                    v2 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(this.messageOwner.from_id));
                }

                TextPaint v3 = (this.messageOwner.media instanceof TL_messageMediaGame) ? Theme.chat_msgGameTextPaint : Theme.chat_msgTextPaint;
                this.messageText = Emoji.replaceEmoji(this.messageText, v3.getFontMetricsInt(), AndroidUtilities.dp(20f), false);
                this.generateLayout(v2);
                return 1;
            }
        }

        return 0;
    }

    public void checkMediaExistance() {
        StringBuilder v2;
        File v0;
        this.attachPathExists = false;
        this.mediaExists = false;
        if(this.type != 1) {
            int v3 = 3;
            if(this.type != 8 && this.type != v3 && this.type != 9 && this.type != 2 && this.type != 14) {
                if(this.type == 5) {
                }
                else {
                    Document v0_1 = this.getDocument();
                    if(v0_1 != null) {
                        v0 = FileLoader.getPathToAttach(((TLObject)v0_1));
                        goto label_50;
                    }
                    else if(this.type == 0) {
                        PhotoSize v0_2 = FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, AndroidUtilities.getPhotoSize());
                        if(v0_2 == null) {
                            return;
                        }
                        else if(v0_2 != null) {
                            v0 = FileLoader.getPathToAttach(((TLObject)v0_2), true);
                            goto label_50;
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

            if(this.messageOwner.attachPath != null && this.messageOwner.attachPath.length() > 0) {
                this.attachPathExists = new File(this.messageOwner.attachPath).exists();
            }

            if(this.attachPathExists) {
                return;
            }

            v0 = FileLoader.getPathToMessage(this.messageOwner);
            if(this.type == v3 && (this.needDrawBluredPreview())) {
                v2 = new StringBuilder();
                v2.append(v0.getAbsolutePath());
                v2.append(".enc");
                this.mediaExists = new File(v2.toString()).exists();
            }

            if(this.mediaExists) {
                return;
            }

        label_50:
            this.mediaExists = v0.exists();
        }
        else if(FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, AndroidUtilities.getPhotoSize()) != null) {
            v0 = FileLoader.getPathToMessage(this.messageOwner);
            if(this.needDrawBluredPreview()) {
                v2 = new StringBuilder();
                v2.append(v0.getAbsolutePath());
                v2.append(".enc");
                this.mediaExists = new File(v2.toString()).exists();
            }

            if(this.mediaExists) {
                return;
            }

            goto label_50;
        }
    }

    private static boolean containsUrls(CharSequence arg14) {
        int v9;
        if(arg14 != null) {
            int v2 = 2;
            if(arg14.length() >= v2) {
                if(arg14.length() > 20480) {
                }
                else {
                    int v1 = arg14.length();
                    int v3 = 0;
                    int v4 = 0;
                    int v5 = 0;
                    int v6 = 0;
                    int v7 = 0;
                    while(true) {
                        if(v3 < v1) {
                            int v8 = arg14.charAt(v3);
                            int v10 = 32;
                            if(v8 < 48 || v8 > 57) {
                                if(v8 != v10 && v4 > 0) {
                                    goto label_33;
                                }

                                v4 = 0;
                            }
                            else {
                                ++v4;
                                if(v4 >= 6) {
                                    return 1;
                                }
                                else {
                                    v5 = 0;
                                    v6 = 0;
                                }
                            }

                        label_33:
                            int v12 = 47;
                            if(v8 != 64 && v8 != 35 && v8 != v12 && v8 != 36 || v3 != 0) {
                                if(v3 != 0) {
                                    v9 = v3 - 1;
                                    if(arg14.charAt(v9) == v10) {
                                        return 1;
                                    }
                                    else if(arg14.charAt(v9) == 10) {
                                        return 1;
                                    }
                                }

                                if(v8 == 58) {
                                    if(v5 == 0) {
                                        v5 = 1;
                                    }
                                    else {
                                        goto label_55;
                                    }
                                }
                                else if(v8 != v12) {
                                    v9 = 46;
                                    if(v8 == v9) {
                                        if(v6 == 0 && v7 != v10) {
                                            ++v6;
                                            goto label_75;
                                        }
                                    }
                                    else if(v8 != v10 && v7 == v9 && v6 == 1) {
                                        return 1;
                                    }

                                    v6 = 0;
                                }
                                else if(v5 == v2) {
                                    return 1;
                                }
                                else if(v5 == 1) {
                                    ++v5;
                                }
                                else {
                                label_55:
                                    v5 = 0;
                                }

                            label_75:
                                ++v3;
                                v7 = v8;
                                continue;
                            }

                            return 1;
                        }

                        return 0;
                    }

                    return 1;
                }
            }
        }

        return 0;
    }

    private void createDateArray(int arg3, TL_channelAdminLogEvent arg4, ArrayList arg5, HashMap arg6) {
        if(arg6.get(this.dateKey) == null) {
            arg6.put(this.dateKey, new ArrayList());
            TL_message v6 = new TL_message();
            v6.message = LocaleController.formatDateChat(((long)arg4.date));
            v6.id = 0;
            v6.date = arg4.date;
            MessageObject v4 = new MessageObject(arg3, ((Message)v6), false);
            v4.type = 10;
            v4.contentType = 1;
            v4.isDateObject = true;
            arg5.add(v4);
        }
    }

    public void createMessageSendInfo() {
        if(this.messageOwner.message != null && (this.messageOwner.id < 0 || (this.isEditing())) && this.messageOwner.params != null) {
            Object v0 = this.messageOwner.params.get("ve");
            if(v0 != null && ((this.isVideo()) || (this.isNewGif()) || (this.isRoundVideo()))) {
                this.videoEditedInfo = new VideoEditedInfo();
                if(!this.videoEditedInfo.parseString(((String)v0))) {
                    this.videoEditedInfo = null;
                    goto label_34;
                }

                this.videoEditedInfo.roundVideo = this.isRoundVideo();
            }

        label_34:
            if(this.messageOwner.send_state != 3) {
                return;
            }

            v0 = this.messageOwner.params.get("prevMedia");
            if(v0 == null) {
                return;
            }

            SerializedData v1 = new SerializedData(Base64.decode(((String)v0), 0));
            this.previousMedia = MessageMedia.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false);
            this.previousCaption = v1.readString(false);
            this.previousAttachPath = v1.readString(false);
            int v0_1 = v1.readInt32(false);
            this.previousCaptionEntities = new ArrayList(v0_1);
            int v3;
            for(v3 = 0; v3 < v0_1; ++v3) {
                this.previousCaptionEntities.add(MessageEntity.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false));
            }

            v1.cleanup();
        }
    }

    public void generateCaption() {
        int v0;
        if(this.caption == null && !this.isRoundVideo() && !this.isMediaEmpty() && !(this.messageOwner.media instanceof TL_messageMediaGame) && !TextUtils.isEmpty(this.messageOwner.message)) {
            boolean v3 = false;
            this.caption = Emoji.replaceEmoji(this.messageOwner.message, Theme.chat_msgTextPaint.getFontMetricsInt(), AndroidUtilities.dp(20f), false);
            if(this.messageOwner.send_state != 0) {
                v0 = 0;
                while(true) {
                    if(v0 >= this.messageOwner.entities.size()) {
                        break;
                    }
                    else if(!(this.messageOwner.entities.get(v0) instanceof TL_inputMessageEntityMentionName)) {
                        v0 = 1;
                    }
                    else {
                        ++v0;
                        continue;
                    }

                    goto label_48;
                }

                v0 = 0;
            }
            else {
                v0 = this.messageOwner.entities.isEmpty() ^ 1;
            }

        label_48:
            if(v0 == 0 && (this.eventId != 0 || ((this.messageOwner.media instanceof TL_messageMediaPhoto_old)) || ((this.messageOwner.media instanceof TL_messageMediaPhoto_layer68)) || ((this.messageOwner.media instanceof TL_messageMediaPhoto_layer74)) || ((this.messageOwner.media instanceof TL_messageMediaDocument_old)) || ((this.messageOwner.media instanceof TL_messageMediaDocument_layer68)) || ((this.messageOwner.media instanceof TL_messageMediaDocument_layer74)) || (this.isOut()) && this.messageOwner.send_state != 0 || this.messageOwner.id < 0)) {
                v3 = true;
            }

            if(v3) {
                if(MessageObject.containsUrls(this.caption)) {
                    try {
                        Linkify.addLinks(this.caption, 5);
                    }
                    catch(Exception v0_1) {
                        FileLog.e(((Throwable)v0_1));
                    }
                }

                MessageObject.addUsernamesAndHashtags(this.isOutOwner(), this.caption, true);
                goto label_105;
            }

            try {
                Linkify.addLinks(this.caption, 4);
            }
            catch(Throwable v0_2) {
                FileLog.e(v0_2);
            }

        label_105:
            this.addEntitiesToText(this.caption, v3);
        }
    }

    public void generateGameMessageText(User arg8) {
        CharSequence v8_1;
        String v8;
        if(arg8 == null && this.messageOwner.from_id > 0) {
            arg8 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(this.messageOwner.from_id));
        }

        TL_game v0 = null;
        if(this.replyMessageObject != null && this.replyMessageObject.messageOwner.media != null && this.replyMessageObject.messageOwner.media.game != null) {
            v0 = this.replyMessageObject.messageOwner.media.game;
        }

        if(v0 == null) {
            if(arg8 != null && arg8.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                v8 = LocaleController.formatString("ActionYouScored", 2131624008, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score)});
                goto label_58;
            }

            v8_1 = this.replaceWithLink(LocaleController.formatString("ActionUserScored", 2131623999, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score)}), "un1", ((TLObject)arg8));
        }
        else {
            if(arg8 == null || arg8.id != UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                v8_1 = this.replaceWithLink(LocaleController.formatString("ActionUserScoredInGame", 2131624000, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score)}), "un1", ((TLObject)arg8));
            }
            else {
                v8 = LocaleController.formatString("ActionYouScoredInGame", 2131624009, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score)});
            }

            this.messageText = v8_1;
            v8_1 = this.replaceWithLink(this.messageText, "un2", ((TLObject)v0));
        }

    label_58:
        this.messageText = v8_1;
    }

    public void generateLayout(User arg31) {
        int v7_2;
        int v15_2;
        float v25;
        float v16;
        Layout$Alignment v15_1;
        int v14_2;
        TextPaint v13_1;
        int v11_1;
        CharSequence v10_1;
        StaticLayout v6_2;
        StaticLayout v26;
        int v27;
        float v6_1;
        StaticLayout v7_1;
        TextLayoutBlock v5_2;
        boolean v24;
        TextPaint v23;
        int v9_1;
        StaticLayout v9;
        StaticLayout v0_5;
        int v6;
        boolean v7;
        int v0;
        MessageObject v1 = this;
        User v2 = arg31;
        if(v1.type != 0) {
            return;
        }

        if(v1.messageOwner.to_id == null) {
            return;
        }

        if(TextUtils.isEmpty(v1.messageText)) {
            return;
        }

        this.generateLinkDescription();
        v1.textLayoutBlocks = new ArrayList();
        int v3 = 0;
        v1.textWidth = 0;
        int v4 = 1;
        if(v1.messageOwner.send_state != 0) {
            v0 = 0;
            goto label_22;
        }
        else {
            v0 = v1.messageOwner.entities.isEmpty() ^ 1;
            goto label_41;
            while(true) {
            label_22:
                if(v0 >= v1.messageOwner.entities.size()) {
                    break;
                }
                else if(!(v1.messageOwner.entities.get(v0) instanceof TL_inputMessageEntityMentionName)) {
                    v0 = 1;
                }
                else {
                    ++v0;
                    continue;
                }

                goto label_41;
            }

            v0 = 0;
        }

    label_41:
        long v5 = 0;
        if(v0 == 0) {
            if(v1.eventId == v5 && !(v1.messageOwner instanceof TL_message_old) && !(v1.messageOwner instanceof TL_message_old2) && !(v1.messageOwner instanceof TL_message_old3) && !(v1.messageOwner instanceof TL_message_old4) && !(v1.messageOwner instanceof TL_messageForwarded_old) && !(v1.messageOwner instanceof TL_messageForwarded_old2) && !(v1.messageOwner instanceof TL_message_secret) && !(v1.messageOwner.media instanceof TL_messageMediaInvoice) && (!this.isOut() || v1.messageOwner.send_state == 0) && v1.messageOwner.id >= 0 && !(v1.messageOwner.media instanceof TL_messageMediaUnsupported)) {
                goto label_84;
            }

            v7 = true;
        }
        else {
        label_84:
            v7 = false;
        }

        if(v7) {
            MessageObject.addLinks(this.isOutOwner(), v1.messageText);
            goto label_103;
        }

        if(((v1.messageText instanceof Spannable)) && v1.messageText.length() < 1000) {
            try {
                Linkify.addLinks(v1.messageText, 4);
            }
            catch(Throwable v0_1) {
                FileLog.e(v0_1);
            }
        }

    label_103:
        v7 = v1.addEntitiesToText(v1.messageText, v7);
        if(v1.eventId != v5 || (this.isOutOwner())) {
        label_145:
            v0 = 0;
        }
        else {
            if(v1.messageOwner.fwd_from == null || v1.messageOwner.fwd_from.saved_from_peer == null && v1.messageOwner.fwd_from.from_id == 0 && v1.messageOwner.fwd_from.channel_id == 0) {
                if(v1.messageOwner.from_id <= 0) {
                    goto label_145;
                }
                else if(v1.messageOwner.to_id.channel_id == 0 && v1.messageOwner.to_id.chat_id == 0 && !(v1.messageOwner.media instanceof TL_messageMediaGame)) {
                    if((v1.messageOwner.media instanceof TL_messageMediaInvoice)) {
                        goto label_143;
                    }

                    goto label_145;
                }
            }

        label_143:
            v0 = 1;
        }

        int v8 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : AndroidUtilities.displaySize.x;
        v1.generatedWithMinSize = v8;
        v8 = v1.generatedWithMinSize;
        float v0_2 = v0 != 0 || v1.eventId != v5 ? 132f : 80f;
        v8 -= AndroidUtilities.dp(v0_2);
        if(v2 == null || !v2.bot) {
            if(!this.isMegagroup()) {
                if(v1.messageOwner.fwd_from == null) {
                }
                else if(v1.messageOwner.fwd_from.channel_id != 0) {
                    goto label_175;
                }

                goto label_180;
            }

        label_175:
            if(this.isOut()) {
                goto label_180;
            }

        label_177:
            v8 -= AndroidUtilities.dp(20f);
        }
        else {
            goto label_177;
        }

    label_180:
        float v2_1 = 10f;
        if((v1.messageOwner.media instanceof TL_messageMediaGame)) {
            v8 -= AndroidUtilities.dp(v2_1);
        }

        TextPaint v0_3 = (v1.messageOwner.media instanceof TL_messageMediaGame) ? Theme.chat_msgGameTextPaint : Theme.chat_msgTextPaint;
        TextPaint v5_1 = v0_3;
        try {
            v6 = 24;
            if(Build$VERSION.SDK_INT >= v6) {
                v0_5 = StaticLayout$Builder.obtain(v1.messageText, 0, v1.messageText.length(), v5_1, v8).setBreakStrategy(1).setHyphenationFrequency(0).setAlignment(Layout$Alignment.ALIGN_NORMAL).build();
            }
            else {
                v9 = new StaticLayout(v1.messageText, v5_1, v8, Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
                goto label_219;
            }

            goto label_220;
        }
        catch(Exception v0_4) {
            goto label_557;
        }

    label_219:
        v0_5 = v9;
    label_220:
        StaticLayout v15 = v0_5;
        v1.textHeight = v15.getHeight();
        v1.linesCount = v15.getLineCount();
        int v2_2 = Build$VERSION.SDK_INT >= v6 ? 1 : ((int)Math.ceil(((double)((((float)v1.linesCount)) / v2_1))));
        float v14 = 0f;
        int v12 = 0;
        int v13 = 0;
        float v19 = 0f;
        goto label_240;
    label_557:
        FileLog.e(((Throwable)v0_4));
        return;
    label_240:
        if(v13 >= v2_2) {
            return;
        }

        v0 = Build$VERSION.SDK_INT >= v6 ? v1.linesCount : Math.min(10, v1.linesCount - v12);
        TextLayoutBlock v11 = new TextLayoutBlock();
        if(v2_2 == v4) {
            v11.textLayout = v15;
            v11.textYOffset = v14;
            v11.charactersOffset = v3;
            v11.height = v1.textHeight;
            v9_1 = v0;
            v23 = v5_1;
            v24 = v7;
            v5_2 = v11;
            v3 = v12;
            v4 = v13;
            v7_1 = v15;
            v6_1 = v19;
            goto label_376;
        }

        int v10 = v15.getLineStart(v12);
        v9_1 = v15.getLineEnd(v12 + v0 - v4);
        if(v9_1 < v10) {
            v23 = v5_1;
            v24 = v7;
            v27 = v12;
            v4 = v13;
            v26 = v15;
            goto label_545;
        }

        v11.charactersOffset = v10;
        v11.charactersEnd = v9_1;
        if(v7) {
            try {
                if(Build$VERSION.SDK_INT >= v6) {
                    v11.textLayout = StaticLayout$Builder.obtain(v1.messageText, v10, v9_1, v5_1, v8 + AndroidUtilities.dp(2f)).setBreakStrategy(v4).setHyphenationFrequency(v3).setAlignment(Layout$Alignment.ALIGN_NORMAL).build();
                    v23 = v5_1;
                    v24 = v7;
                    v5_2 = v11;
                    v3 = v12;
                    v4 = v13;
                    v7_1 = v15;
                }
                else {
                label_311:
                    v6_2 = null;
                    int v21 = v9_1;
                    v9 = v6_2;
                    int v22 = v10;
                    v10_1 = v1.messageText;
                    TextLayoutBlock v14_1 = v11;
                    v11_1 = v22;
                    v3 = v12;
                    v12 = v21;
                    v4 = v13;
                    v13_1 = v5_1;
                    v23 = v5_1;
                    v24 = v7;
                    v5_2 = v14_1;
                    v14_2 = v8;
                    v7_1 = v15;
                    v15_1 = Layout$Alignment.ALIGN_NORMAL;
                    v16 = 1f;
                    goto label_337;
                }

                goto label_339;
            }
            catch(Exception v0_4) {
                goto label_303;
            }
        }
        else {
            goto label_311;
            try {
            label_337:
                super(v10_1, v11_1, v12, v13_1, v14_2, v15_1, v16, 0f, false);
                v5_2.textLayout = v6_2;
            label_339:
                v5_2.textYOffset = ((float)v7_1.getLineTop(v3));
                if(v4 != 0) {
                    v5_2.height = ((int)(v5_2.textYOffset - v19));
                }

                v5_2.height = Math.max(v5_2.height, v5_2.textLayout.getLineBottom(v5_2.textLayout.getLineCount() - 1));
                v6_1 = v5_2.textYOffset;
                if(v4 != v2_2 - 1) {
                    goto label_375;
                }

                goto label_359;
            }
            catch(Exception v0_4) {
                goto label_541;
            }
        }

        goto label_339;
    label_359:
        v9_1 = Math.max(v0, v5_2.textLayout.getLineCount());
        try {
            v1.textHeight = Math.max(v1.textHeight, ((int)(v5_2.textYOffset + (((float)v5_2.textLayout.getHeight())))));
        }
        catch(Exception v0_4) {
            FileLog.e(((Throwable)v0_4));
        }

        goto label_376;
    label_375:
        v9_1 = v0;
    label_376:
        v1.textLayoutBlocks.add(v5_2);
        try {
            v14 = v5_2.textLayout.getLineLeft(v9_1 - 1);
            if(v4 != 0) {
                goto label_392;
            }

            if(v14 < 0f) {
                goto label_392;
            }

            v1.textXOffset = v14;
        }
        catch(Exception v0_4) {
            if(v4 == 0) {
                v1.textXOffset = 0f;
            }

            FileLog.e(((Throwable)v0_4));
            v14 = 0f;
        }

        try {
        label_392:
            v0_2 = v5_2.textLayout.getLineWidth(v9_1 - 1);
        }
        catch(Exception v0_4) {
            FileLog.e(((Throwable)v0_4));
            v0_2 = 0f;
        }

        v10 = ((int)Math.ceil(((double)v0_2)));
        v11_1 = v2_2 - 1;
        if(v4 == v11_1) {
            v1.lastLineWidth = v10;
        }

        v12 = ((int)Math.ceil(((double)(v0_2 + v14))));
        if(v9_1 > 1) {
            v25 = v6_1;
            v26 = v7_1;
            v15_2 = v10;
            v7_2 = v12;
            v6_1 = 0f;
            v10 = 0;
            v13 = 0;
            v14 = 0f;
            goto label_419;
        }

        v27 = v3;
        v25 = v6_1;
        v26 = v7_1;
        int v29 = v8;
        int v28 = v9_1;
        if(v14 > 0f) {
            v1.textXOffset = Math.min(v1.textXOffset, v14);
            if(v1.textXOffset == 0f) {
                v10 = ((int)((((float)v10)) + v14));
            }

            boolean v0_6 = v2_2 != 1 ? true : false;
            v1.hasRtl = v0_6;
            v0 = v5_2.directionFlags | 1;
        }
        else {
            v0 = v5_2.directionFlags | 2;
        }

        v5_2.directionFlags = ((byte)v0);
        v8 = v29;
        v1.textWidth = Math.max(v1.textWidth, Math.min(v8, v10));
        goto label_537;
    label_541:
        v27 = v3;
        v26 = v7_1;
        goto label_544;
        return;
    label_419:
        while(v10 < v9_1) {
            try {
                v0_2 = v5_2.textLayout.getLineWidth(v10);
            }
            catch(Exception v0_4) {
                FileLog.e(((Throwable)v0_4));
                v0_2 = 0f;
            }

            v27 = v3;
            if(v0_2 > (((float)(v8 + 20)))) {
                v0_2 = ((float)v8);
            }

            float v3_1 = v0_2;
            try {
                v0_2 = v5_2.textLayout.getLineLeft(v10);
            }
            catch(Exception v0_4) {
                FileLog.e(((Throwable)v0_4));
                v0_2 = 0f;
            }

            if(v0_2 > 0f) {
                v28 = v9_1;
                v1.textXOffset = Math.min(v1.textXOffset, v0_2);
                v29 = v8;
                v5_2.directionFlags = ((byte)(v5_2.directionFlags | 1));
                v1.hasRtl = true;
            }
            else {
                v29 = v8;
                v28 = v9_1;
                v5_2.directionFlags = ((byte)(v5_2.directionFlags | 2));
            }

            if(v13 == 0 && v0_2 == 0f) {
                try {
                    if(v5_2.textLayout.getParagraphDirection(v10) == 1) {
                        goto label_465;
                    }
                }
                catch(Exception ) {
                label_465:
                    v13 = 1;
                }
            }

            v6_1 = Math.max(v6_1, v3_1);
            v0_2 += v3_1;
            v14 = Math.max(v14, v0_2);
            v15_2 = Math.max(v15_2, ((int)Math.ceil(((double)v3_1))));
            v7_2 = Math.max(v7_2, ((int)Math.ceil(((double)v0_2))));
            ++v10;
            v3 = v27;
            v9_1 = v28;
            v8 = v29;
        }

        v27 = v3;
        v29 = v8;
        v28 = v9_1;
        if(v13 == 0) {
            if(v4 == v11_1) {
                v1.lastLineWidth = v15_2;
            }

            v14 = v6_1;
        }
        else if(v4 == v11_1) {
            v1.lastLineWidth = v12;
        }

        v1.textWidth = Math.max(v1.textWidth, ((int)Math.ceil(((double)v14))));
        v8 = v29;
    label_537:
        v12 = v27 + v28;
        v19 = v25;
        goto label_546;
    label_303:
        v23 = ((TextPaint)v5_2);
        v24 = v7;
        v27 = v12;
        TextPaint v4_1 = v13_1;
        v26 = v15;
    label_544:
        FileLog.e(((Throwable)v0_4));
        goto label_545;
    label_546:
        v13 = v4 + 1;
        v5_1 = v23;
        v7 = v24;
        v15 = v26;
        v3 = 0;
        v4 = 1;
        v6 = 24;
        v14 = 0f;
        goto label_240;
    label_545:
        v12 = v27;
        goto label_546;
    }

    public void generateLinkDescription() {
        String v1;
        Spannable$Factory v0;
        if(this.linkDescription != null) {
            return;
        }

        if(!(this.messageOwner.media instanceof TL_messageMediaWebPage) || !(this.messageOwner.media.webpage instanceof TL_webPage) || this.messageOwner.media.webpage.description == null) {
            if(((this.messageOwner.media instanceof TL_messageMediaGame)) && this.messageOwner.media.game.description != null) {
                v0 = Spannable$Factory.getInstance();
                v1 = this.messageOwner.media.game.description;
                goto label_22;
            }

            if(!(this.messageOwner.media instanceof TL_messageMediaInvoice)) {
                goto label_53;
            }

            if(this.messageOwner.media.description == null) {
                goto label_53;
            }

            v0 = Spannable$Factory.getInstance();
            v1 = this.messageOwner.media.description;
        label_22:
            this.linkDescription = v0.newSpannable(((CharSequence)v1));
        }
        else {
            v0 = Spannable$Factory.getInstance();
            v1 = this.messageOwner.media.webpage.description;
            goto label_22;
        }

    label_53:
        if(this.linkDescription != null) {
            if(MessageObject.containsUrls(this.linkDescription)) {
                try {
                    Linkify.addLinks(this.linkDescription, 1);
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }

            this.linkDescription = Emoji.replaceEmoji(this.linkDescription, Theme.chat_msgTextPaint.getFontMetricsInt(), AndroidUtilities.dp(20f), false);
        }
    }

    public void generatePaymentSentMessageText(User arg11) {
        if(arg11 == null) {
            arg11 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((int)this.getDialogId())));
        }

        String v11 = arg11 != null ? UserObject.getFirstName(arg11) : "";
        int v3 = 2;
        if(this.replyMessageObject == null || !(this.replyMessageObject.messageOwner.media instanceof TL_messageMediaInvoice)) {
            Object[] v3_1 = new Object[v3];
            v3_1[0] = LocaleController.getInstance().formatCurrencyString(this.messageOwner.action.total_amount, this.messageOwner.action.currency);
            v3_1[1] = v11;
            v11 = LocaleController.formatString("PaymentSuccessfullyPaidNoItem", 2131625726, v3_1);
        }
        else {
            Object[] v5 = new Object[3];
            v5[0] = LocaleController.getInstance().formatCurrencyString(this.messageOwner.action.total_amount, this.messageOwner.action.currency);
            v5[1] = v11;
            v5[v3] = this.replyMessageObject.messageOwner.media.title;
            v11 = LocaleController.formatString("PaymentSuccessfullyPaid", 2131625725, v5);
        }

        this.messageText = ((CharSequence)v11);
    }

    public void generatePinMessageText(User arg8, Chat arg9) {
        CharSequence v8_1;
        Chat v8;
        String v1_1;
        String v0;
        if(arg8 == null && arg9 == null) {
            if(this.messageOwner.from_id > 0) {
                arg8 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(this.messageOwner.from_id));
            }

            if(arg8 != null) {
                goto label_19;
            }

            arg9 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(this.messageOwner.to_id.channel_id));
        }

    label_19:
        int v1 = 2131623989;
        if(this.replyMessageObject == null || ((this.replyMessageObject.messageOwner instanceof TL_messageEmpty)) || ((this.replyMessageObject.messageOwner.action instanceof TL_messageActionHistoryClear))) {
            v0 = LocaleController.getString("ActionPinnedNoText", v1);
            v1_1 = "un1";
            if(arg8 != null) {
            }
            else {
            label_218:
                v8 = arg9;
            }

        label_219:
            v8_1 = this.replaceWithLink(((CharSequence)v0), v1_1, ((TLObject)arg8));
        }
        else if(this.replyMessageObject.isMusic()) {
            v0 = LocaleController.getString("ActionPinnedMusic", 2131623988);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if(this.replyMessageObject.isVideo()) {
            v0 = LocaleController.getString("ActionPinnedVideo", 2131623994);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if(this.replyMessageObject.isGif()) {
            v0 = LocaleController.getString("ActionPinnedGif", 2131623987);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if(this.replyMessageObject.isVoice()) {
            v0 = LocaleController.getString("ActionPinnedVoice", 2131623995);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if(this.replyMessageObject.isRoundVideo()) {
            v0 = LocaleController.getString("ActionPinnedRound", 2131623991);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if(this.replyMessageObject.isSticker()) {
            v0 = LocaleController.getString("ActionPinnedSticker", 2131623992);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if((this.replyMessageObject.messageOwner.media instanceof TL_messageMediaDocument)) {
            v0 = LocaleController.getString("ActionPinnedFile", 2131623983);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if((this.replyMessageObject.messageOwner.media instanceof TL_messageMediaGeo)) {
            v0 = LocaleController.getString("ActionPinnedGeo", 2131623985);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if((this.replyMessageObject.messageOwner.media instanceof TL_messageMediaGeoLive)) {
            v0 = LocaleController.getString("ActionPinnedGeoLive", 2131623986);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if((this.replyMessageObject.messageOwner.media instanceof TL_messageMediaContact)) {
            v0 = LocaleController.getString("ActionPinnedContact", 2131623982);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else if((this.replyMessageObject.messageOwner.media instanceof TL_messageMediaPhoto)) {
            v0 = LocaleController.getString("ActionPinnedPhoto", 2131623990);
            v1_1 = "un1";
            if(arg8 != null) {
                goto label_219;
            }
            else {
                goto label_218;
            }
        }
        else {
            float v2 = 20f;
            if((this.replyMessageObject.messageOwner.media instanceof TL_messageMediaGame)) {
                Object[] v3 = new Object[1];
                v3[0] = "🎮 " + this.replyMessageObject.messageOwner.media.game.title;
                v0 = LocaleController.formatString("ActionPinnedGame", 2131623984, v3);
                v1_1 = "un1";
                if(arg8 != null) {
                }
                else {
                    v8 = arg9;
                }

                this.messageText = this.replaceWithLink(((CharSequence)v0), v1_1, ((TLObject)arg8));
                v8_1 = Emoji.replaceEmoji(this.messageText, Theme.chat_msgTextPaint.getFontMetricsInt(), AndroidUtilities.dp(v2), false);
            }
            else {
                if(this.replyMessageObject.messageText != null && this.replyMessageObject.messageText.length() > 0) {
                    CharSequence v0_1 = this.replyMessageObject.messageText;
                    int v5_1 = 20;
                    if(v0_1.length() > v5_1) {
                        v0 = v0_1.subSequence(0, v5_1) + "...";
                    }

                    v0 = LocaleController.formatString("ActionPinnedText", 2131623993, new Object[]{Emoji.replaceEmoji(((CharSequence)v0), Theme.chat_msgTextPaint.getFontMetricsInt(), AndroidUtilities.dp(v2), false)});
                    v1_1 = "un1";
                    if(arg8 == null) {
                        goto label_218;
                    }

                    goto label_219;
                }

                v0 = LocaleController.getString("ActionPinnedNoText", v1);
                v1_1 = "un1";
                if(arg8 == null) {
                    goto label_218;
                }

                goto label_219;
            }
        }

        this.messageText = v8_1;
    }

    public void generateThumbs(boolean arg7) {
        Object v3;
        Object v0;
        ArrayList v7;
        Document v0_1;
        if(!(this.messageOwner instanceof TL_messageService)) {
            if(this.messageOwner.media == null) {
                return;
            }

            if((this.messageOwner.media instanceof TL_messageMediaEmpty)) {
                return;
            }

            if((this.messageOwner.media instanceof TL_messageMediaPhoto)) {
                if((arg7) && (this.photoThumbs == null || this.photoThumbs.size() == this.messageOwner.media.photo.sizes.size())) {
                    if(this.photoThumbs == null) {
                        return;
                    }

                    if(this.photoThumbs.isEmpty()) {
                        return;
                    }

                    for(v7_1 = 0; true; ++v7_1) {
                        if(v7_1 >= this.photoThumbs.size()) {
                            return;
                        }

                        v0 = this.photoThumbs.get(v7_1);
                        for(v2 = 0; v2 < this.messageOwner.media.photo.sizes.size(); ++v2) {
                            v3 = this.messageOwner.media.photo.sizes.get(v2);
                            if((v3 instanceof TL_photoSizeEmpty)) {
                            }
                            else if(((PhotoSize)v3).type.equals(((PhotoSize)v0).type)) {
                                ((PhotoSize)v0).location = ((PhotoSize)v3).location;
                                break;
                            }
                        }
                    }
                }

                v7 = new ArrayList(this.messageOwner.media.photo.sizes);
                goto label_15;
            }

            if(!(this.messageOwner.media instanceof TL_messageMediaDocument)) {
                if((this.messageOwner.media instanceof TL_messageMediaGame)) {
                    if(this.messageOwner.media.game.document != null && !(this.messageOwner.media.game.document.thumb instanceof TL_photoSizeEmpty)) {
                        if(!arg7) {
                            this.photoThumbs = new ArrayList();
                            this.photoThumbs.add(this.messageOwner.media.game.document.thumb);
                        }
                        else if(this.photoThumbs != null && !this.photoThumbs.isEmpty() && this.messageOwner.media.game.document.thumb != null) {
                            this.photoThumbs.get(0).location = this.messageOwner.media.game.document.thumb.location;
                        }
                    }

                    if(this.messageOwner.media.game.photo != null) {
                        if(arg7) {
                            if(this.photoThumbs2 == null) {
                            }
                            else if(!this.photoThumbs2.isEmpty()) {
                                v7_1 = 0;
                                while(true) {
                                    if(v7_1 < this.photoThumbs2.size()) {
                                        v0 = this.photoThumbs2.get(v7_1);
                                        for(v2 = 0; v2 < this.messageOwner.media.game.photo.sizes.size(); ++v2) {
                                            v3 = this.messageOwner.media.game.photo.sizes.get(v2);
                                            if((v3 instanceof TL_photoSizeEmpty)) {
                                            }
                                            else if(((PhotoSize)v3).type.equals(((PhotoSize)v0).type)) {
                                                ((PhotoSize)v0).location = ((PhotoSize)v3).location;
                                                break;
                                            }
                                        }

                                        ++v7_1;
                                        continue;
                                    }
                                    else {
                                        goto label_278;
                                    }
                                }
                            }
                            else {
                                goto label_278;
                            }
                        }

                        this.photoThumbs2 = new ArrayList(this.messageOwner.media.game.photo.sizes);
                    }

                label_278:
                    if(this.photoThumbs != null) {
                        return;
                    }

                    if(this.photoThumbs2 == null) {
                        return;
                    }

                    this.photoThumbs = this.photoThumbs2;
                    this.photoThumbs2 = null;
                    return;
                }

                if(!(this.messageOwner.media instanceof TL_messageMediaWebPage)) {
                    return;
                }

                if(this.messageOwner.media.webpage.photo != null) {
                    if(arg7) {
                        if(this.photoThumbs == null) {
                        }
                        else if(!this.photoThumbs.isEmpty()) {
                            v7_1 = 0;
                            while(true) {
                                if(v7_1 < this.photoThumbs.size()) {
                                    v0 = this.photoThumbs.get(v7_1);
                                    for(v2 = 0; v2 < this.messageOwner.media.webpage.photo.sizes.size(); ++v2) {
                                        v3 = this.messageOwner.media.webpage.photo.sizes.get(v2);
                                        if((v3 instanceof TL_photoSizeEmpty)) {
                                        }
                                        else if(((PhotoSize)v3).type.equals(((PhotoSize)v0).type)) {
                                            ((PhotoSize)v0).location = ((PhotoSize)v3).location;
                                            break;
                                        }
                                    }

                                    ++v7_1;
                                    continue;
                                }
                                else {
                                    return;
                                }
                            }
                        }
                        else {
                            return;
                        }
                    }

                    v7 = new ArrayList(this.messageOwner.media.webpage.photo.sizes);
                label_15:
                    this.photoThumbs = v7;
                    return;
                }

                if(this.messageOwner.media.webpage.document == null) {
                    return;
                }

                if((this.messageOwner.media.webpage.document.thumb instanceof TL_photoSizeEmpty)) {
                    return;
                }

                if(arg7) {
                    goto label_367;
                }

                this.photoThumbs = new ArrayList();
                v7 = this.photoThumbs;
                v0_1 = this.messageOwner.media.webpage.document;
            }
            else if(!(this.messageOwner.media.document.thumb instanceof TL_photoSizeEmpty)) {
                if(arg7) {
                    if(this.photoThumbs == null) {
                    }
                    else if(this.photoThumbs != null) {
                        if(this.photoThumbs.isEmpty()) {
                        }
                        else if(this.messageOwner.media.document.thumb != null) {
                            Object v7_2 = this.photoThumbs.get(0);
                            ((PhotoSize)v7_2).location = this.messageOwner.media.document.thumb.location;
                            ((PhotoSize)v7_2).w = this.messageOwner.media.document.thumb.w;
                            ((PhotoSize)v7_2).h = this.messageOwner.media.document.thumb.h;
                        }
                        else {
                        }

                        return;
                    }
                    else {
                        return;
                    }
                }

                this.photoThumbs = new ArrayList();
                v7 = this.photoThumbs;
                v0_1 = this.messageOwner.media.document;
            }
            else {
                return;
            }

            v7.add(v0_1.thumb);
            return;
        label_367:
            if(this.photoThumbs == null) {
                return;
            }

            if(this.photoThumbs.isEmpty()) {
                return;
            }

            if(this.messageOwner.media.webpage.document.thumb == null) {
                return;
            }

            this.photoThumbs.get(0).location = this.messageOwner.media.webpage.document.thumb.location;
        }
        else if((this.messageOwner.action instanceof TL_messageActionChatEditPhoto)) {
            if(!arg7) {
                v7 = new ArrayList(this.messageOwner.action.photo.sizes);
                goto label_15;
            }
            else if(this.photoThumbs != null && !this.photoThumbs.isEmpty()) {
                int v7_1;
                for(v7_1 = 0; v7_1 < this.photoThumbs.size(); ++v7_1) {
                    v0 = this.photoThumbs.get(v7_1);
                    int v2;
                    for(v2 = 0; v2 < this.messageOwner.action.photo.sizes.size(); ++v2) {
                        v3 = this.messageOwner.action.photo.sizes.get(v2);
                        if((v3 instanceof TL_photoSizeEmpty)) {
                        }
                        else if(((PhotoSize)v3).type.equals(((PhotoSize)v0).type)) {
                            ((PhotoSize)v0).location = ((PhotoSize)v3).location;
                            break;
                        }
                    }
                }
            }
        }
    }

    public int getApproximateHeight() {
        Object v6;
        float v0_1;
        int v0;
        int v1 = 0;
        float v2 = 100f;
        if(this.type == 0) {
            v0 = this.textHeight;
            if(((this.messageOwner.media instanceof TL_messageMediaWebPage)) && ((this.messageOwner.media.webpage instanceof TL_webPage))) {
                v1 = AndroidUtilities.dp(v2);
            }

            v0 += v1;
            if(this.isReply()) {
                v0 += AndroidUtilities.dp(42f);
            }

            return v0;
        }

        if(this.type == 2) {
            v0_1 = 72f;
        }
        else if(this.type == 12) {
            v0_1 = 71f;
        }
        else if(this.type == 9) {
            return AndroidUtilities.dp(v2);
        }
        else if(this.type == 4) {
            v0_1 = 114f;
        }
        else if(this.type == 14) {
            v0_1 = 82f;
        }
        else if(this.type == 10) {
            v0_1 = 30f;
        }
        else if(this.type == 11) {
            v0_1 = 50f;
        }
        else {
            goto label_58;
        }

        return AndroidUtilities.dp(v0_1);
    label_58:
        if(this.type == 5) {
            return AndroidUtilities.roundMessageSize;
        }

        float v4 = 14f;
        float v5 = 0.5f;
        if(this.type != 13) {
            goto label_116;
        }

        v0_1 = (((float)AndroidUtilities.displaySize.y)) * 0.4f;
        int v3 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : AndroidUtilities.displaySize.x;
        float v3_1 = (((float)v3)) * v5;
        Iterator v5_1 = this.messageOwner.media.document.attributes.iterator();
        do {
            if(v5_1.hasNext()) {
                v6 = v5_1.next();
                if(!(v6 instanceof TL_documentAttributeImageSize)) {
                    continue;
                }

                break;
            }
            else {
                goto label_95;
            }
        }
        while(true);

        v1 = ((DocumentAttribute)v6).w;
        int v5_2 = ((DocumentAttribute)v6).h;
        goto label_96;
    label_95:
        v5_2 = 0;
    label_96:
        if(v1 == 0) {
            v5_2 = ((int)v0_1);
            v1 = AndroidUtilities.dp(v2) + v5_2;
        }

        v2 = ((float)v5_2);
        if(v2 > v0_1) {
            v1 = ((int)((((float)v1)) * (v0_1 / v2)));
            v5_2 = ((int)v0_1);
        }

        v0_1 = ((float)v1);
        if(v0_1 > v3_1) {
            v5_2 = ((int)((((float)v5_2)) * (v3_1 / v0_1)));
        }

        return v5_2 + AndroidUtilities.dp(v4);
    label_116:
        float v1_1 = 0.7f;
        v0 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
        v0 = ((int)((((float)v0)) * v1_1));
        v1 = AndroidUtilities.dp(v2) + v0;
        if(v0 > AndroidUtilities.getPhotoSize()) {
            v0 = AndroidUtilities.getPhotoSize();
        }

        if(v1 > AndroidUtilities.getPhotoSize()) {
            v1 = AndroidUtilities.getPhotoSize();
        }

        PhotoSize v3_2 = FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, AndroidUtilities.getPhotoSize());
        if(v3_2 != null) {
            v0 = ((int)((((float)v3_2.h)) / ((((float)v3_2.w)) / (((float)v0)))));
            if(v0 == 0) {
                v0 = AndroidUtilities.dp(v2);
            }

            if(v0 > v1) {
            }
            else {
                v1_1 = 120f;
                v1 = v0 < AndroidUtilities.dp(v1_1) ? AndroidUtilities.dp(v1_1) : v0;
            }

            if(!this.needDrawBluredPreview()) {
                goto label_176;
            }

            v0 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
            v1 = ((int)((((float)v0)) * v5));
        }

    label_176:
        return v1 + AndroidUtilities.dp(v4);
    }

    public int getChannelId() {
        if(this.messageOwner.to_id != null) {
            return this.messageOwner.to_id.channel_id;
        }

        return 0;
    }

    public static long getDialogId(Message arg5) {
        int v0_1;
        long v0;
        if(arg5.dialog_id == 0 && arg5.to_id != null) {
            if(arg5.to_id.chat_id != 0) {
                if(arg5.to_id.chat_id < 0) {
                    v0 = AndroidUtilities.makeBroadcastId(arg5.to_id.chat_id);
                }
                else {
                    v0_1 = arg5.to_id.chat_id;
                    goto label_18;
                }
            }
            else if(arg5.to_id.channel_id != 0) {
                v0_1 = arg5.to_id.channel_id;
            label_18:
                v0_1 = -v0_1;
                goto label_19;
            }
            else if(MessageObject.isOut(arg5)) {
                v0_1 = arg5.to_id.user_id;
                goto label_19;
            }
            else {
                v0_1 = arg5.from_id;
            label_19:
                v0 = ((long)v0_1);
            }

            arg5.dialog_id = v0;
        }

        return arg5.dialog_id;
    }

    public long getDialogId() {
        return MessageObject.getDialogId(this.messageOwner);
    }

    public Document getDocument() {
        if((this.messageOwner.media instanceof TL_messageMediaWebPage)) {
            return this.messageOwner.media.webpage.document;
        }

        Document v0 = this.messageOwner.media != null ? this.messageOwner.media.document : null;
        return v0;
    }

    public String getDocumentName() {
        Document v0;
        if((this.messageOwner.media instanceof TL_messageMediaDocument)) {
            v0 = this.messageOwner.media.document;
        }
        else if((this.messageOwner.media instanceof TL_messageMediaWebPage)) {
            v0 = this.messageOwner.media.webpage.document;
        }
        else {
            return "";
        }

        return FileLoader.getDocumentFileName(v0);
    }

    private Document getDocumentWithId(WebPage arg7, long arg8) {
        Document v0 = null;
        if(arg7 != null) {
            if(arg7.cached_page == null) {
            }
            else {
                if(arg7.document != null && arg7.document.id == arg8) {
                    return arg7.document;
                }

                int v1;
                for(v1 = 0; v1 < arg7.cached_page.documents.size(); ++v1) {
                    Object v2 = arg7.cached_page.documents.get(v1);
                    if(((Document)v2).id == arg8) {
                        return ((Document)v2);
                    }
                }
            }
        }

        return v0;
    }

    public int getDuration() {
        Document v0 = this.type == 0 ? this.messageOwner.media.webpage.document : this.messageOwner.media.document;
        int v1;
        for(v1 = 0; v1 < v0.attributes.size(); ++v1) {
            Object v2 = v0.attributes.get(v1);
            if((v2 instanceof TL_documentAttributeAudio)) {
                return ((DocumentAttribute)v2).duration;
            }

            if((v2 instanceof TL_documentAttributeVideo)) {
                return ((DocumentAttribute)v2).duration;
            }
        }

        return this.audioPlayerDuration;
    }

    public String getExtension() {
        String v0 = this.getFileName();
        int v1 = v0.lastIndexOf(46);
        v0 = v1 != -1 ? v0.substring(v1 + 1) : null;
        if(v0 == null || v0.length() == 0) {
            v0 = this.messageOwner.media.document.mime_type;
        }

        if(v0 == null) {
            v0 = "";
        }

        return v0.toUpperCase();
    }

    public String getFileName() {
        Document v0;
        if((this.messageOwner.media instanceof TL_messageMediaDocument)) {
            v0 = this.messageOwner.media.document;
        }
        else {
            if((this.messageOwner.media instanceof TL_messageMediaPhoto)) {
                ArrayList v0_1 = this.messageOwner.media.photo.sizes;
                if(v0_1.size() > 0) {
                    PhotoSize v0_2 = FileLoader.getClosestPhotoSizeWithSize(v0_1, AndroidUtilities.getPhotoSize());
                    if(v0_2 != null) {
                        return FileLoader.getAttachFileName(((TLObject)v0_2));
                    }
                }
            }
            else if((this.messageOwner.media instanceof TL_messageMediaWebPage)) {
                v0 = this.messageOwner.media.webpage.document;
                goto label_7;
            }

            return "";
        }

    label_7:
        return FileLoader.getAttachFileName(((TLObject)v0));
    }

    public int getFileType() {
        if(this.isVideo()) {
            return 2;
        }

        if(this.isVoice()) {
            return 1;
        }

        if((this.messageOwner.media instanceof TL_messageMediaDocument)) {
            return 3;
        }

        if((this.messageOwner.media instanceof TL_messageMediaPhoto)) {
            return 0;
        }

        return 4;
    }

    public String getForwardedName() {
        if(this.messageOwner.fwd_from != null) {
            if(this.messageOwner.fwd_from.channel_id != 0) {
                Chat v0 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(this.messageOwner.fwd_from.channel_id));
                if(v0 != null) {
                    return v0.title;
                }
            }
            else if(this.messageOwner.fwd_from.from_id != 0) {
                User v0_1 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(this.messageOwner.fwd_from.from_id));
                if(v0_1 != null) {
                    return UserObject.getUserName(v0_1);
                }
            }
        }

        return null;
    }

    public int getFromId() {
        if(this.messageOwner.fwd_from == null || this.messageOwner.fwd_from.saved_from_peer == null) {
            if(this.messageOwner.from_id != 0) {
                return this.messageOwner.from_id;
            }

            if(!this.messageOwner.post) {
                return 0;
            }

            return this.messageOwner.to_id.channel_id;
        }
        else if(this.messageOwner.fwd_from.saved_from_peer.user_id != 0) {
            if(this.messageOwner.fwd_from.from_id != 0) {
                return this.messageOwner.fwd_from.from_id;
            }
            else {
                return this.messageOwner.fwd_from.saved_from_peer.user_id;
            }
        }
        else if(this.messageOwner.fwd_from.saved_from_peer.channel_id != 0) {
            if((this.isSavedFromMegagroup()) && this.messageOwner.fwd_from.from_id != 0) {
                return this.messageOwner.fwd_from.from_id;
            }

            if(this.messageOwner.fwd_from.channel_id != 0) {
                return -this.messageOwner.fwd_from.channel_id;
            }

            return -this.messageOwner.fwd_from.saved_from_peer.channel_id;
        }
        else {
            if(this.messageOwner.fwd_from.saved_from_peer.chat_id == 0) {
                return 0;
            }

            if(this.messageOwner.fwd_from.from_id != 0) {
                return this.messageOwner.fwd_from.from_id;
            }

            if(this.messageOwner.fwd_from.channel_id != 0) {
                return -this.messageOwner.fwd_from.channel_id;
            }

            return -this.messageOwner.fwd_from.saved_from_peer.chat_id;
        }

        return 0;
    }

    public long getGroupId() {
        long v0 = this.localGroupId != 0 ? this.localGroupId : this.getGroupIdForUse();
        return v0;
    }

    public long getGroupIdForUse() {
        long v0 = this.localSentGroupId != 0 ? this.localSentGroupId : this.messageOwner.grouped_id;
        return v0;
    }

    public int getId() {
        return this.messageOwner.id;
    }

    public long getIdWithChannel() {
        long v0 = ((long)this.messageOwner.id);
        if(this.messageOwner.to_id != null && this.messageOwner.to_id.channel_id != 0) {
            v0 |= (((long)this.messageOwner.to_id.channel_id)) << 32;
        }

        return v0;
    }

    public static int getInlineResultDuration(BotInlineResult arg1) {
        int v0 = MessageObject.getWebDocumentDuration(arg1.content);
        if(v0 == 0) {
            v0 = MessageObject.getWebDocumentDuration(arg1.thumb);
        }

        return v0;
    }

    public static int[] getInlineResultWidthAndHeight(BotInlineResult arg1) {
        int[] v0 = MessageObject.getWebDocumentWidthAndHeight(arg1.content);
        if(v0 == null) {
            v0 = MessageObject.getWebDocumentWidthAndHeight(arg1.thumb);
            if(v0 == null) {
                v0 = new int[]{0, 0};
            }
        }

        return v0;
    }

    public static InputStickerSet getInputStickerSet(Message arg3) {
        Object v0;
        InputStickerSet v1 = null;
        if(arg3.media != null && arg3.media.document != null) {
            Iterator v3 = arg3.media.document.attributes.iterator();
            do {
                if(v3.hasNext()) {
                    v0 = v3.next();
                    if(!(v0 instanceof TL_documentAttributeSticker)) {
                        continue;
                    }

                    break;
                }

                return v1;
            }
            while(true);

            if((((DocumentAttribute)v0).stickerset instanceof TL_inputStickerSetEmpty)) {
                return v1;
            }
            else {
                return ((DocumentAttribute)v0).stickerset;
            }
        }

        return v1;
    }

    public InputStickerSet getInputStickerSet() {
        return MessageObject.getInputStickerSet(this.messageOwner);
    }

    private MessageObject getMessageObjectForBlock(WebPage arg5, PageBlock arg6) {
        TL_message v5;
        if((arg6 instanceof TL_pageBlockPhoto)) {
            Photo v6 = this.getPhotoWithId(arg5, arg6.photo_id);
            if(v6 == arg5.photo) {
                return this;
            }
            else {
                v5 = new TL_message();
                v5.media = new TL_messageMediaPhoto();
                v5.media.photo = v6;
            }
        }
        else if(!(arg6 instanceof TL_pageBlockVideo)) {
            v5 = null;
        }
        else if(this.getDocumentWithId(arg5, arg6.video_id) == arg5.document) {
            return this;
        }
        else {
            TL_message v0 = new TL_message();
            v0.media = new TL_messageMediaDocument();
            v0.media.document = this.getDocumentWithId(arg5, arg6.video_id);
            v5 = v0;
        }

        v5.message = "";
        v5.id = Utilities.random.nextInt();
        v5.date = this.messageOwner.date;
        v5.to_id = this.messageOwner.to_id;
        v5.out = this.messageOwner.out;
        v5.from_id = this.messageOwner.from_id;
        return new MessageObject(this.currentAccount, ((Message)v5), false);
    }

    public static int getMessageSize(Message arg1) {
        if(arg1.media != null && arg1.media.document != null) {
            return arg1.media.document.size;
        }

        return 0;
    }

    public String getMimeType() {
        if(!(this.messageOwner.media instanceof TL_messageMediaDocument)) {
            if((this.messageOwner.media instanceof TL_messageMediaInvoice)) {
                WebDocument v0 = this.messageOwner.media.photo;
                if(v0 != null) {
                    return v0.mime_type;
                }
            }
            else if((this.messageOwner.media instanceof TL_messageMediaPhoto)) {
                return "image/jpeg";
            }
            else if((this.messageOwner.media instanceof TL_messageMediaWebPage)) {
                if(this.messageOwner.media.webpage.document != null) {
                    goto label_4;
                }
                else {
                    goto label_35;
                }
            }

            return "";
        }

    label_4:
        return this.messageOwner.media.document.mime_type;
    label_35:
        if(this.messageOwner.media.webpage.photo != null) {
            return "image/jpeg";
        }

        return "";
    }

    public String getMusicAuthor() {
        return this.getMusicAuthor(true);
    }

    public String getMusicAuthor(boolean arg9) {
        int v6;
        MessagesController v5;
        Chat v5_1;
        Document v0 = this.type == 0 ? this.messageOwner.media.webpage.document : this.messageOwner.media.document;
        int v1 = 2131624162;
        if(v0 != null) {
            int v2 = 0;
            int v3 = 0;
            while(v2 < v0.attributes.size()) {
                Object v4 = v0.attributes.get(v2);
                if(!(v4 instanceof TL_documentAttributeAudio)) {
                    if(!(v4 instanceof TL_documentAttributeVideo)) {
                        goto label_38;
                    }

                    if(!((DocumentAttribute)v4).round_message) {
                        goto label_38;
                    }

                label_24:
                    v3 = 1;
                }
                else if(!((DocumentAttribute)v4).voice) {
                    String v0_1 = ((DocumentAttribute)v4).performer;
                    if((TextUtils.isEmpty(((CharSequence)v0_1))) && (arg9)) {
                        v0_1 = LocaleController.getString("AudioUnknownArtist", v1);
                    }

                    return v0_1;
                }
                else {
                    goto label_24;
                }

            label_38:
                if(v3 != 0) {
                    String v4_1 = null;
                    if(!arg9) {
                        return v4_1;
                    }
                    else {
                        if(!this.isOutOwner() && (this.messageOwner.fwd_from == null || this.messageOwner.fwd_from.from_id != UserConfig.getInstance(this.currentAccount).getClientUserId())) {
                            if(this.messageOwner.fwd_from == null || this.messageOwner.fwd_from.channel_id == 0) {
                                if(this.messageOwner.fwd_from != null && this.messageOwner.fwd_from.from_id != 0) {
                                    v5 = MessagesController.getInstance(this.currentAccount);
                                    v6 = this.messageOwner.fwd_from.from_id;
                                }
                                else if(this.messageOwner.from_id < 0) {
                                    v5 = MessagesController.getInstance(this.currentAccount);
                                    v6 = -this.messageOwner.from_id;
                                    goto label_67;
                                }
                                else {
                                    if(this.messageOwner.from_id == 0 && this.messageOwner.to_id.channel_id != 0) {
                                        v5 = MessagesController.getInstance(this.currentAccount);
                                        v6 = this.messageOwner.to_id.channel_id;
                                    label_67:
                                        v5_1 = v5.getChat(Integer.valueOf(v6));
                                        goto label_115;
                                    }

                                    v5 = MessagesController.getInstance(this.currentAccount);
                                    v6 = this.messageOwner.from_id;
                                }

                                User v7 = v5.getUser(Integer.valueOf(v6));
                                v5_1 = ((Chat)v4_1);
                                User v4_2 = v7;
                            }
                            else {
                                v5 = MessagesController.getInstance(this.currentAccount);
                                v6 = this.messageOwner.fwd_from.channel_id;
                                goto label_67;
                            }

                        label_115:
                            if((((User)v4_1)) != null) {
                                return UserObject.getUserName(((User)v4_1));
                            }

                            if(v5_1 == null) {
                                goto label_125;
                            }

                            return v5_1.title;
                        }

                        return LocaleController.getString("FromYou", 2131624891);
                    }
                }

            label_125:
                ++v2;
            }
        }

        return LocaleController.getString("AudioUnknownArtist", v1);
    }

    public String getMusicTitle() {
        return this.getMusicTitle(true);
    }

    public String getMusicTitle(boolean arg6) {
        Document v0 = this.type == 0 ? this.messageOwner.media.webpage.document : this.messageOwner.media.document;
        int v1 = 2131624163;
        if(v0 != null) {
            int v2 = 0;
            while(true) {
                if(v2 < v0.attributes.size()) {
                    Object v3 = v0.attributes.get(v2);
                    if(!(v3 instanceof TL_documentAttributeAudio)) {
                        if(((v3 instanceof TL_documentAttributeVideo)) && (((DocumentAttribute)v3).round_message)) {
                            return LocaleController.formatDateAudio(((long)this.messageOwner.date));
                        }

                        ++v2;
                        continue;
                    }
                    else if(!((DocumentAttribute)v3).voice) {
                        String v2_1 = ((DocumentAttribute)v3).title;
                        if(v2_1 == null || v2_1.length() == 0) {
                            v2_1 = FileLoader.getDocumentFileName(v0);
                            if((TextUtils.isEmpty(((CharSequence)v2_1))) && (arg6)) {
                                v2_1 = LocaleController.getString("AudioUnknownTitle", v1);
                            }
                        }

                        return v2_1;
                    }
                    else if(!arg6) {
                        return null;
                    }
                    else {
                        return LocaleController.formatDateAudio(((long)this.messageOwner.date));
                    }
                }
                else {
                    break;
                }

                goto label_56;
            }

            String v6 = FileLoader.getDocumentFileName(v0);
            if(TextUtils.isEmpty(((CharSequence)v6))) {
                goto label_56;
            }

            return v6;
        }

    label_56:
        return LocaleController.getString("AudioUnknownTitle", v1);
    }

    private Photo getPhotoWithId(WebPage arg7, long arg8) {
        Photo v0 = null;
        if(arg7 != null) {
            if(arg7.cached_page == null) {
            }
            else {
                if(arg7.photo != null && arg7.photo.id == arg8) {
                    return arg7.photo;
                }

                int v1;
                for(v1 = 0; v1 < arg7.cached_page.photos.size(); ++v1) {
                    Object v2 = arg7.cached_page.photos.get(v1);
                    if(((Photo)v2).id == arg8) {
                        return ((Photo)v2);
                    }
                }
            }
        }

        return v0;
    }

    public int getSecretTimeLeft() {
        int v0 = this.messageOwner.ttl;
        if(this.messageOwner.destroyTime != 0) {
            v0 = Math.max(0, this.messageOwner.destroyTime - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        }

        return v0;
    }

    public String getSecretTimeString() {
        if(!this.isSecretMedia()) {
            return null;
        }

        int v0 = this.getSecretTimeLeft();
        int v1 = 60;
        String v0_1 = v0 < v1 ? v0 + "s" : v0 / v1 + "m";
        return v0_1;
    }

    public int getSize() {
        return MessageObject.getMessageSize(this.messageOwner);
    }

    public String getStickerEmoji() {
        String v2;
        int v0;
        for(v0 = 0; true; ++v0) {
            v2 = null;
            if(v0 >= this.messageOwner.media.document.attributes.size()) {
                return v2;
            }

            Object v1 = this.messageOwner.media.document.attributes.get(v0);
            if((v1 instanceof TL_documentAttributeSticker)) {
                if(((DocumentAttribute)v1).alt != null && ((DocumentAttribute)v1).alt.length() > 0) {
                    v2 = ((DocumentAttribute)v1).alt;
                }

                return v2;
            }
        }

        return v2;
    }

    public static long getStickerSetId(Document arg5) {
        long v0 = -1;
        if(arg5 == null) {
            return v0;
        }

        int v2;
        for(v2 = 0; v2 < arg5.attributes.size(); ++v2) {
            Object v3 = arg5.attributes.get(v2);
            if((v3 instanceof TL_documentAttributeSticker)) {
                if((((DocumentAttribute)v3).stickerset instanceof TL_inputStickerSetEmpty)) {
                    return v0;
                }

                return ((DocumentAttribute)v3).stickerset.id;
            }
        }

        return v0;
    }

    public String getStrickerChar() {
        Object v1;
        if(this.messageOwner.media != null && this.messageOwner.media.document != null) {
            Iterator v0 = this.messageOwner.media.document.attributes.iterator();
            do {
                if(v0.hasNext()) {
                    v1 = v0.next();
                    if(!(v1 instanceof TL_documentAttributeSticker)) {
                        continue;
                    }

                    break;
                }

                return null;
            }
            while(true);

            return ((DocumentAttribute)v1).alt;
        }

        return null;
    }

    public int getUnradFlags() {
        return MessageObject.getUnreadFlags(this.messageOwner);
    }

    public static int getUnreadFlags(Message arg1) {
        int v0 = !arg1.unread ? 1 : 0;
        if(!arg1.media_unread) {
            v0 |= 2;
        }

        return v0;
    }

    private String getUserName(User arg6, ArrayList arg7, int arg8) {
        String v0 = arg6 == null ? "" : ContactsController.formatName(arg6.first_name, arg6.last_name);
        if(arg8 >= 0) {
            TL_messageEntityMentionName v1 = new TL_messageEntityMentionName();
            v1.user_id = arg6.id;
            v1.offset = arg8;
            v1.length = v0.length();
            arg7.add(v1);
        }

        if(!TextUtils.isEmpty(arg6.username)) {
            int v2 = 2;
            if(arg8 >= 0) {
                TL_messageEntityMentionName v3 = new TL_messageEntityMentionName();
                v3.user_id = arg6.id;
                v3.offset = arg8 + v0.length() + v2;
                v3.length = arg6.username.length() + 1;
                arg7.add(v3);
            }

            return String.format("%1$s (@%2$s)", v0, arg6.username);
        }

        return v0;
    }

    public static int getWebDocumentDuration(WebDocument arg5) {
        if(arg5 == null) {
            return 0;
        }

        int v1 = arg5.attributes.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg5.attributes.get(v2);
            if((v3 instanceof TL_documentAttributeVideo)) {
                return ((DocumentAttribute)v3).duration;
            }

            if((v3 instanceof TL_documentAttributeAudio)) {
                return ((DocumentAttribute)v3).duration;
            }
        }

        return 0;
    }

    public static int[] getWebDocumentWidthAndHeight(WebDocument arg8) {
        int[] v8;
        int[] v0 = null;
        if(arg8 == null) {
            return v0;
        }

        int v1 = arg8.attributes.size();
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = arg8.attributes.get(v3);
            int v6 = 2;
            if((v4 instanceof TL_documentAttributeImageSize)) {
                v8 = new int[v6];
                v8[0] = ((DocumentAttribute)v4).w;
                v8[1] = ((DocumentAttribute)v4).h;
                return v8;
            }

            if((v4 instanceof TL_documentAttributeVideo)) {
                v8 = new int[v6];
                v8[0] = ((DocumentAttribute)v4).w;
                v8[1] = ((DocumentAttribute)v4).h;
                return v8;
            }
        }

        return v0;
    }

    public ArrayList getWebPagePhotos(ArrayList arg7, ArrayList arg8) {
        WebPage v0 = this.messageOwner.media.webpage;
        if(arg7 == null) {
            arg7 = new ArrayList();
        }

        if(v0.cached_page == null) {
            return arg7;
        }

        if(arg8 == null) {
            arg8 = v0.cached_page.blocks;
        }

        int v2;
        for(v2 = 0; v2 < arg8.size(); ++v2) {
            Object v3 = arg8.get(v2);
            if((v3 instanceof TL_pageBlockSlideshow)) {
                int v4;
                for(v4 = 0; v4 < ((TL_pageBlockSlideshow)v3).items.size(); ++v4) {
                    arg7.add(this.getMessageObjectForBlock(v0, ((TL_pageBlockSlideshow)v3).items.get(v4)));
                }
            }
            else if((v3 instanceof TL_pageBlockCollage)) {
                for(v4 = 0; v4 < ((TL_pageBlockCollage)v3).items.size(); ++v4) {
                    arg7.add(this.getMessageObjectForBlock(v0, ((TL_pageBlockCollage)v3).items.get(v4)));
                }
            }
        }

        return arg7;
    }

    public boolean hasPhotoStickers() {
        boolean v0 = this.messageOwner.media == null || this.messageOwner.media.photo == null || !this.messageOwner.media.photo.has_stickers ? false : true;
        return v0;
    }

    public boolean hasValidGroupId() {
        boolean v0 = this.getGroupId() == 0 || this.photoThumbs == null || (this.photoThumbs.isEmpty()) ? false : true;
        return v0;
    }

    public boolean hasValidReplyMessageObject() {
        boolean v0 = this.replyMessageObject == null || ((this.replyMessageObject.messageOwner instanceof TL_messageEmpty)) || ((this.replyMessageObject.messageOwner.action instanceof TL_messageActionHistoryClear)) ? false : true;
        return v0;
    }

    public boolean isContentUnread() {
        return this.messageOwner.media_unread;
    }

    public static boolean isContentUnread(Message arg0) {
        return arg0.media_unread;
    }

    public boolean isEditing() {
        boolean v0 = this.messageOwner.send_state != 3 || this.messageOwner.id <= 0 ? false : true;
        return v0;
    }

    public boolean isFcmMessage() {
        boolean v0 = this.localType != 0 ? true : false;
        return v0;
    }

    public boolean isForwarded() {
        return MessageObject.isForwardedMessage(this.messageOwner);
    }

    public static boolean isForwardedMessage(Message arg1) {
        boolean v1 = (arg1.flags & 4) == 0 || arg1.fwd_from == null ? false : true;
        return v1;
    }

    public boolean isFromUser() {
        boolean v0 = this.messageOwner.from_id <= 0 || (this.messageOwner.post) ? false : true;
        return v0;
    }

    public boolean isGame() {
        return MessageObject.isGameMessage(this.messageOwner);
    }

    public static boolean isGameMessage(Message arg0) {
        return arg0.media instanceof TL_messageMediaGame;
    }

    public boolean isGif() {
        return MessageObject.isGifMessage(this.messageOwner);
    }

    public static boolean isGifDocument(Document arg2) {
        boolean v2;
        if(arg2 == null || arg2.thumb == null || arg2.mime_type == null) {
        label_13:
            v2 = false;
        }
        else {
            if(!arg2.mime_type.equals("image/gif") && !MessageObject.isNewGifDocument(arg2)) {
                goto label_13;
            }

            v2 = true;
        }

        return v2;
    }

    public static boolean isGifDocument(WebFile arg2) {
        boolean v2;
        if(arg2 != null) {
            if(!arg2.mime_type.equals("image/gif") && !MessageObject.isNewGifDocument(arg2)) {
                goto label_9;
            }

            v2 = true;
        }
        else {
        label_9:
            v2 = false;
        }

        return v2;
    }

    public static boolean isGifMessage(Message arg1) {
        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isGifDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public static boolean isImageWebDocument(WebFile arg1) {
        boolean v1 = arg1 == null || (MessageObject.isGifDocument(arg1)) || !arg1.mime_type.startsWith("image/") ? false : true;
        return v1;
    }

    public boolean isInvoice() {
        return MessageObject.isInvoiceMessage(this.messageOwner);
    }

    public static boolean isInvoiceMessage(Message arg0) {
        return arg0.media instanceof TL_messageMediaInvoice;
    }

    public boolean isLiveLocation() {
        return MessageObject.isLiveLocationMessage(this.messageOwner);
    }

    public static boolean isLiveLocationMessage(Message arg0) {
        return arg0.media instanceof TL_messageMediaGeoLive;
    }

    public boolean isMask() {
        return MessageObject.isMaskMessage(this.messageOwner);
    }

    public static boolean isMaskDocument(Document arg4) {
        if(arg4 != null) {
            int v1;
            for(v1 = 0; v1 < arg4.attributes.size(); ++v1) {
                Object v2 = arg4.attributes.get(v1);
                if(((v2 instanceof TL_documentAttributeSticker)) && (((DocumentAttribute)v2).mask)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    public static boolean isMaskMessage(Message arg1) {
        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isMaskDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public boolean isMediaEmpty() {
        return MessageObject.isMediaEmpty(this.messageOwner);
    }

    public static boolean isMediaEmpty(Message arg1) {
        boolean v1 = arg1 == null || arg1.media == null || ((arg1.media instanceof TL_messageMediaEmpty)) || ((arg1.media instanceof TL_messageMediaWebPage)) ? true : false;
        return v1;
    }

    public static boolean isMegagroup(Message arg1) {
        boolean v1 = (arg1.flags & -2147483648) != 0 ? true : false;
        return v1;
    }

    public boolean isMegagroup() {
        return MessageObject.isMegagroup(this.messageOwner);
    }

    public boolean isMusic() {
        return MessageObject.isMusicMessage(this.messageOwner);
    }

    public static boolean isMusicDocument(Document arg5) {
        if(arg5 != null) {
            int v1 = 0;
            while(true) {
                if(v1 < arg5.attributes.size()) {
                    Object v2 = arg5.attributes.get(v1);
                    if((v2 instanceof TL_documentAttributeAudio)) {
                        return ((DocumentAttribute)v2).voice ^ 1;
                    }
                    else {
                        ++v1;
                        continue;
                    }
                }
                else {
                    break;
                }

                return 0;
            }

            if(!TextUtils.isEmpty(arg5.mime_type)) {
                String v1_1 = arg5.mime_type.toLowerCase();
                if(!v1_1.equals("audio/flac") && !v1_1.equals("audio/ogg") && !v1_1.equals("audio/opus")) {
                    if(v1_1.equals("audio/x-opus+ogg")) {
                    }
                    else {
                        if(!v1_1.equals("application/octet-stream")) {
                        }
                        else if(FileLoader.getDocumentFileName(arg5).endsWith(".opus")) {
                            return 1;
                        }

                        return 0;
                    }
                }

                return 1;
            }
        }

        return 0;
    }

    public static boolean isMusicMessage(Message arg1) {
        if((arg1.media instanceof TL_messageMediaWebPage)) {
            return MessageObject.isMusicDocument(arg1.media.webpage.document);
        }

        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isMusicDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public boolean isNewGif() {
        boolean v0 = this.messageOwner.media == null || !MessageObject.isNewGifDocument(this.messageOwner.media.document) ? false : true;
        return v0;
    }

    public static boolean isNewGifDocument(Document arg8) {
        if(arg8 != null && arg8.mime_type != null && (arg8.mime_type.equals("video/mp4"))) {
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;
            int v4 = 0;
            while(v1 < arg8.attributes.size()) {
                Object v5 = arg8.attributes.get(v1);
                if((v5 instanceof TL_documentAttributeAnimated)) {
                    v2 = 1;
                }
                else if((v5 instanceof TL_documentAttributeVideo)) {
                    v3 = ((DocumentAttribute)v5).w;
                    v4 = ((DocumentAttribute)v5).w;
                }

                ++v1;
            }

            if(v2 == 0) {
                return 0;
            }

            int v8 = 1280;
            if(v3 > v8) {
                return 0;
            }

            if(v4 > v8) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public static boolean isNewGifDocument(WebFile arg6) {
        if(arg6 != null && arg6.mime_type != null && (arg6.mime_type.equals("video/mp4"))) {
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;
            while(v1 < arg6.attributes.size()) {
                Object v4 = arg6.attributes.get(v1);
                if((v4 instanceof TL_documentAttributeAnimated)) {
                }
                else if((v4 instanceof TL_documentAttributeVideo)) {
                    v2 = ((DocumentAttribute)v4).w;
                    v3 = ((DocumentAttribute)v4).w;
                }

                ++v1;
            }

            int v6 = 1280;
            if(v2 > v6) {
                return 0;
            }

            if(v3 > v6) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public static boolean isNewGifMessage(Message arg1) {
        if((arg1.media instanceof TL_messageMediaWebPage)) {
            return MessageObject.isNewGifDocument(arg1.media.webpage.document);
        }

        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isNewGifDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public static boolean isOut(Message arg0) {
        return arg0.out;
    }

    public boolean isOut() {
        return this.messageOwner.out;
    }

    public boolean isOutOwner() {
        boolean v1 = false;
        if((this.messageOwner.out) && this.messageOwner.from_id > 0) {
            if(this.messageOwner.post) {
            }
            else if(this.messageOwner.fwd_from == null) {
                return 1;
            }
            else {
                int v0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
                if(this.getDialogId() == (((long)v0))) {
                    if(this.messageOwner.fwd_from.from_id == v0 || this.messageOwner.fwd_from.saved_from_peer != null && this.messageOwner.fwd_from.saved_from_peer.user_id == v0) {
                        v1 = true;
                    }

                    return v1;
                }
                else {
                    if(this.messageOwner.fwd_from.saved_from_peer != null && this.messageOwner.fwd_from.saved_from_peer.user_id != v0) {
                        return v1;
                    }

                    v1 = true;
                }
            }
        }

        return v1;
    }

    public static boolean isPhoto(Message arg1) {
        if((arg1.media instanceof TL_messageMediaWebPage)) {
            return arg1.media.webpage.photo instanceof TL_photo;
        }

        return arg1.media instanceof TL_messageMediaPhoto;
    }

    public boolean isReply() {
        boolean v0;
        if(this.replyMessageObject == null || !(this.replyMessageObject.messageOwner instanceof TL_messageEmpty)) {
            if((this.messageOwner.reply_to_msg_id != 0 || this.messageOwner.reply_to_random_id != 0) && (this.messageOwner.flags & 8) != 0) {
                v0 = true;
                return v0;
            }

        label_19:
            v0 = false;
        }
        else {
            goto label_19;
        }

        return v0;
    }

    public boolean isRoundVideo() {
        boolean v1 = true;
        if(this.isRoundVideoCached == 0) {
            int v0 = this.type == 5 || (MessageObject.isRoundVideoMessage(this.messageOwner)) ? 1 : 2;
            this.isRoundVideoCached = v0;
        }

        if(this.isRoundVideoCached == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public static boolean isRoundVideoDocument(Document arg8) {
        if(arg8 != null && arg8.mime_type != null && (arg8.mime_type.equals("video/mp4"))) {
            int v1 = 0;
            boolean v2 = false;
            int v3 = 0;
            int v4 = 0;
            while(v1 < arg8.attributes.size()) {
                Object v5 = arg8.attributes.get(v1);
                if((v5 instanceof TL_documentAttributeVideo)) {
                    int v2_1 = ((DocumentAttribute)v5).w;
                    int v7 = ((DocumentAttribute)v5).w;
                    v3 = v2_1;
                    v2 = ((DocumentAttribute)v5).round_message;
                    v4 = v7;
                }

                ++v1;
            }

            if(!v2) {
                return 0;
            }

            int v8 = 1280;
            if(v3 > v8) {
                return 0;
            }

            if(v4 > v8) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public static boolean isRoundVideoMessage(Message arg1) {
        if((arg1.media instanceof TL_messageMediaWebPage)) {
            return MessageObject.isRoundVideoDocument(arg1.media.webpage.document);
        }

        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isRoundVideoDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public boolean isSavedFromMegagroup() {
        if(this.messageOwner.fwd_from != null && this.messageOwner.fwd_from.saved_from_peer != null && this.messageOwner.fwd_from.saved_from_peer.channel_id != 0) {
            return ChatObject.isMegagroup(MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(this.messageOwner.fwd_from.saved_from_peer.channel_id)));
        }

        return 0;
    }

    public boolean isSecretMedia() {
        boolean v1 = true;
        if((this.messageOwner instanceof TL_message_secret)) {
            if((!(this.messageOwner.media instanceof TL_messageMediaPhoto) && !this.isGif() || (this.messageOwner.ttl <= 0 || this.messageOwner.ttl > 60)) && (!this.isVoice() && !this.isRoundVideo())) {
                if(this.isVideo()) {
                }
                else {
                    v1 = false;
                }
            }

            return v1;
        }

        if((this.messageOwner instanceof TL_message)) {
            if(!(this.messageOwner.media instanceof TL_messageMediaPhoto) && !(this.messageOwner.media instanceof TL_messageMediaDocument)) {
                goto label_43;
            }
            else if(this.messageOwner.media.ttl_seconds != 0) {
            }
            else {
            label_43:
                v1 = false;
            }

            return v1;
        }

        return 0;
    }

    public static boolean isSecretPhotoOrVideo(Message arg3) {
        boolean v1 = true;
        if((arg3 instanceof TL_message_secret)) {
            if(!(arg3.media instanceof TL_messageMediaPhoto) && !MessageObject.isRoundVideoMessage(arg3) && !MessageObject.isVideoMessage(arg3) || (arg3.ttl <= 0 || arg3.ttl > 60)) {
                v1 = false;
            }
            else {
            }

            return v1;
        }

        if((arg3 instanceof TL_message)) {
            if(!(arg3.media instanceof TL_messageMediaPhoto) && !(arg3.media instanceof TL_messageMediaDocument)) {
                goto label_31;
            }
            else if(arg3.media.ttl_seconds != 0) {
            }
            else {
            label_31:
                v1 = false;
            }

            return v1;
        }

        return 0;
    }

    public boolean isSendError() {
        boolean v0 = this.messageOwner.send_state != 2 || this.messageOwner.id >= 0 ? false : true;
        return v0;
    }

    public boolean isSending() {
        boolean v1 = true;
        if(this.messageOwner.send_state != 1 || this.messageOwner.id >= 0) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    public boolean isSent() {
        boolean v0 = this.messageOwner.send_state == 0 || this.messageOwner.id > 0 ? true : false;
        return v0;
    }

    public boolean isSticker() {
        if(this.type != 1000) {
            boolean v0 = this.type == 13 ? true : false;
            return v0;
        }

        return MessageObject.isStickerMessage(this.messageOwner);
    }

    public static boolean isStickerDocument(Document arg3) {
        if(arg3 != null) {
            int v1 = 0;
            while(v1 < arg3.attributes.size()) {
                if((arg3.attributes.get(v1) instanceof TL_documentAttributeSticker)) {
                    return 1;
                }
                else {
                    ++v1;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    public static boolean isStickerMessage(Message arg1) {
        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isStickerDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public static boolean isUnread(Message arg0) {
        return arg0.unread;
    }

    public boolean isUnread() {
        return this.messageOwner.unread;
    }

    public boolean isVideo() {
        return MessageObject.isVideoMessage(this.messageOwner);
    }

    public static boolean isVideoDocument(Document arg9) {
        boolean v0 = false;
        if(arg9 != null) {
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;
            int v4 = 0;
            int v5 = 0;
            while(v1 < arg9.attributes.size()) {
                Object v6 = arg9.attributes.get(v1);
                if((v6 instanceof TL_documentAttributeVideo)) {
                    if(((DocumentAttribute)v6).round_message) {
                        return 0;
                    }
                    else {
                        v3 = ((DocumentAttribute)v6).w;
                        v4 = ((DocumentAttribute)v6).h;
                        v5 = 1;
                    }
                }
                else if((v6 instanceof TL_documentAttributeAnimated)) {
                    v2 = 1;
                }

                ++v1;
            }

            if(v2 != 0) {
                int v9 = 1280;
                if(v3 <= v9 && v4 <= v9) {
                    goto label_32;
                }

                v2 = 0;
            }

        label_32:
            if(v5 == 0) {
                return v0;
            }

            if(v2 != 0) {
                return v0;
            }

            v0 = true;
        }

        return v0;
    }

    public static boolean isVideoMessage(Message arg1) {
        if((arg1.media instanceof TL_messageMediaWebPage)) {
            return MessageObject.isVideoDocument(arg1.media.webpage.document);
        }

        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isVideoDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public static boolean isVideoWebDocument(WebFile arg1) {
        boolean v1 = arg1 == null || !arg1.mime_type.startsWith("video/") ? false : true;
        return v1;
    }

    public boolean isVoice() {
        return MessageObject.isVoiceMessage(this.messageOwner);
    }

    public static boolean isVoiceDocument(Document arg4) {
        if(arg4 != null) {
            int v1 = 0;
            while(v1 < arg4.attributes.size()) {
                Object v2 = arg4.attributes.get(v1);
                if((v2 instanceof TL_documentAttributeAudio)) {
                    return ((DocumentAttribute)v2).voice;
                }
                else {
                    ++v1;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    public static boolean isVoiceMessage(Message arg1) {
        if((arg1.media instanceof TL_messageMediaWebPage)) {
            return MessageObject.isVoiceDocument(arg1.media.webpage.document);
        }

        boolean v1 = arg1.media == null || arg1.media.document == null || !MessageObject.isVoiceDocument(arg1.media.document) ? false : true;
        return v1;
    }

    public static boolean isVoiceWebDocument(WebFile arg1) {
        boolean v1 = arg1 == null || !arg1.mime_type.equals("audio/ogg") ? false : true;
        return v1;
    }

    public boolean isWebpage() {
        return this.messageOwner.media instanceof TL_messageMediaWebPage;
    }

    public boolean isWebpageDocument() {
        boolean v0 = !(this.messageOwner.media instanceof TL_messageMediaWebPage) || this.messageOwner.media.webpage.document == null || (MessageObject.isGifDocument(this.messageOwner.media.webpage.document)) ? false : true;
        return v0;
    }

    public void measureInlineBotButtons() {
        String v6_1;
        this.wantedBotKeyboardWidth = 0;
        if(!(this.messageOwner.reply_markup instanceof TL_replyInlineMarkup)) {
            return;
        }

        Theme.createChatResources(null, true);
        if(this.botButtonsLayout == null) {
            this.botButtonsLayout = new StringBuilder();
        }
        else {
            this.botButtonsLayout.setLength(0);
        }

        int v1;
        for(v1 = 0; v1 < this.messageOwner.reply_markup.rows.size(); ++v1) {
            Object v2 = this.messageOwner.reply_markup.rows.get(v1);
            int v3 = ((TL_keyboardButtonRow)v2).buttons.size();
            int v4 = 0;
            int v5 = 0;
            while(v4 < v3) {
                Object v6 = ((TL_keyboardButtonRow)v2).buttons.get(v4);
                StringBuilder v7 = this.botButtonsLayout;
                v7.append(v1);
                v7.append(v4);
                if(!(v6 instanceof TL_keyboardButtonBuy) || (this.messageOwner.media.flags & 4) == 0) {
                    CharSequence v6_2 = Emoji.replaceEmoji(((KeyboardButton)v6).text, Theme.chat_msgBotButtonPaint.getFontMetricsInt(), AndroidUtilities.dp(15f), false);
                }
                else {
                    v6_1 = LocaleController.getString("PaymentReceipt", 2131625709);
                }

                StaticLayout v6_3 = new StaticLayout(v6_1, Theme.chat_msgBotButtonPaint, AndroidUtilities.dp(2000f), Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
                if(v6_3.getLineCount() > 0) {
                    float v7_1 = v6_3.getLineWidth(0);
                    float v6_4 = v6_3.getLineLeft(0);
                    if(v6_4 < v7_1) {
                        v7_1 -= v6_4;
                    }

                    v5 = Math.max(v5, (((int)Math.ceil(((double)v7_1)))) + AndroidUtilities.dp(4f));
                }

                ++v4;
            }

            this.wantedBotKeyboardWidth = Math.max(this.wantedBotKeyboardWidth, (v5 + AndroidUtilities.dp(12f)) * v3 + AndroidUtilities.dp(5f) * (v3 - 1));
        }
    }

    public boolean needDrawAvatar() {
        boolean v0;
        if((this.isFromUser()) || this.eventId != 0) {
        label_15:
            v0 = true;
        }
        else {
            if(this.messageOwner.fwd_from != null && this.messageOwner.fwd_from.saved_from_peer != null) {
                goto label_15;
            }

            v0 = false;
        }

        return v0;
    }

    public boolean needDrawBluredPreview() {
        boolean v1 = true;
        if((this.messageOwner instanceof TL_message_secret)) {
            int v0 = Math.max(this.messageOwner.ttl, this.messageOwner.media.ttl_seconds);
            if(v0 > 0) {
                if((((this.messageOwner.media instanceof TL_messageMediaPhoto)) || (this.isVideo()) || (this.isGif())) && v0 <= 60) {
                    return v1;
                }

                if(!this.isRoundVideo()) {
                    goto label_25;
                }
            }
            else {
            label_25:
                v1 = false;
            }

            return v1;
        }

        if((this.messageOwner instanceof TL_message)) {
            if(!(this.messageOwner.media instanceof TL_messageMediaPhoto) && !(this.messageOwner.media instanceof TL_messageMediaDocument)) {
                goto label_43;
            }
            else if(this.messageOwner.media.ttl_seconds != 0) {
            }
            else {
            label_43:
                v1 = false;
            }

            return v1;
        }

        return 0;
    }

    public boolean needDrawForwarded() {
        boolean v0 = (this.messageOwner.flags & 4) == 0 || this.messageOwner.fwd_from == null || this.messageOwner.fwd_from.saved_from_peer != null || (((long)UserConfig.getInstance(this.currentAccount).getClientUserId())) == this.getDialogId() ? false : true;
        return v0;
    }

    public CharSequence replaceWithLink(CharSequence arg7, String arg8, TLObject arg9) {
        String v9_1;
        int v9;
        StringBuilder v2;
        String v1;
        int v0 = TextUtils.indexOf(arg7, ((CharSequence)arg8));
        if(v0 >= 0) {
            if((arg9 instanceof User)) {
                v1 = UserObject.getUserName(((User)arg9));
                v2 = new StringBuilder();
                v2.append("");
                v9 = ((User)arg9).id;
                goto label_10;
            }
            else if((arg9 instanceof Chat)) {
                v1 = ((Chat)arg9).title;
                v2 = new StringBuilder();
                v2.append("");
                v9 = -((Chat)arg9).id;
            label_10:
                v2.append(v9);
                v9_1 = v2.toString();
            }
            else if((arg9 instanceof TL_game)) {
                v1 = ((TL_game)arg9).title;
                v9_1 = "game";
            }
            else {
                v1 = "";
                v9_1 = "0";
            }

            v1 = v1.replace('\n', ' ');
            SpannableStringBuilder v2_1 = new SpannableStringBuilder(TextUtils.replace(arg7, new String[]{arg8}, new String[]{v1}));
            StringBuilder v8 = new StringBuilder();
            v8.append("");
            v8.append(v9_1);
            v2_1.setSpan(new URLSpanNoUnderlineBold(v8.toString()), v0, v1.length() + v0, 33);
            return ((CharSequence)v2_1);
        }

        return arg7;
    }

    public CharSequence replaceWithLink(CharSequence arg10, String arg11, ArrayList arg12, AbstractMap arg13, SparseArray arg14) {
        if(TextUtils.indexOf(arg10, ((CharSequence)arg11)) >= 0) {
            SpannableStringBuilder v0 = new SpannableStringBuilder("");
            int v2;
            for(v2 = 0; v2 < arg12.size(); ++v2) {
                Object v3 = null;
                if(arg13 != null) {
                    v3 = arg13.get(arg12.get(v2));
                }
                else if(arg14 != null) {
                    v3 = arg14.get(arg12.get(v2).intValue());
                }

                if(v3 == null) {
                    User v3_1 = MessagesController.getInstance(this.currentAccount).getUser(arg12.get(v2));
                }

                if(v3 != null) {
                    String v4 = UserObject.getUserName(((User)v3));
                    int v5 = v0.length();
                    if(v0.length() != 0) {
                        v0.append(", ");
                    }

                    v0.append(((CharSequence)v4));
                    StringBuilder v7 = new StringBuilder();
                    v7.append("");
                    v7.append(((User)v3).id);
                    v0.setSpan(new URLSpanNoUnderlineBold(v7.toString()), v5, v4.length() + v5, 33);
                }
            }

            arg10 = TextUtils.replace(arg10, new String[]{arg11}, new CharSequence[]{v0});
        }

        return arg10;
    }

    public void resetPlayingProgress() {
        this.audioProgress = 0f;
        this.audioProgressSec = 0;
        this.bufferedProgress = 0f;
    }

    public void restoreCaptionAndText() {
        if(!TextUtils.isEmpty(this.messageTextBU)) {
            this.messageText = this.messageTextBU;
        }

        if(!TextUtils.isEmpty(this.captionBU)) {
            this.caption = this.captionBU;
        }

        try {
            if(!TextUtils.isEmpty(this.messageOwnerMessageBU)) {
                this.messageOwner.message = this.messageOwnerMessageBU;
            }

            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public void setContentIsRead() {
        this.messageOwner.media_unread = false;
    }

    public void setIsRead() {
        this.messageOwner.unread = false;
    }

    public void setType() {
        int v2;
        int v0 = this.type;
        this.isRoundVideoCached = 0;
        int v3 = 10;
        if(((this.messageOwner instanceof TL_message)) || ((this.messageOwner instanceof TL_messageForwarded_old2))) {
            if(this.isMediaEmpty()) {
                this.type = 0;
                if(!TextUtils.isEmpty(this.messageText)) {
                    goto label_182;
                }

                if(this.eventId != 0) {
                    goto label_182;
                }

                this.messageText = "Empty message";
                goto label_182;
            }

            if(this.messageOwner.media.ttl_seconds != 0 && (((this.messageOwner.media.photo instanceof TL_photoEmpty)) || ((this.messageOwner.media.document instanceof TL_documentEmpty)))) {
            label_89:
                this.contentType = 1;
                goto label_90;
            }

            if((this.messageOwner.media instanceof TL_messageMediaPhoto)) {
                this.type = 1;
                goto label_182;
            }

            if(!(this.messageOwner.media instanceof TL_messageMediaGeo) && !(this.messageOwner.media instanceof TL_messageMediaVenue)) {
                if((this.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                }
                else if(this.isRoundVideo()) {
                    v2 = 5;
                    goto label_61;
                }
                else if(this.isVideo()) {
                    v2 = 3;
                    goto label_61;
                }
                else if(this.isVoice()) {
                    v2 = 2;
                    goto label_61;
                }
                else if(this.isMusic()) {
                    v2 = 14;
                    goto label_61;
                }
                else if((this.messageOwner.media instanceof TL_messageMediaContact)) {
                    v2 = 12;
                    goto label_61;
                }
                else if((this.messageOwner.media instanceof TL_messageMediaUnsupported)) {
                    goto label_19;
                }
                else if((this.messageOwner.media instanceof TL_messageMediaDocument)) {
                    v3 = 9;
                    if(this.messageOwner.media.document == null || this.messageOwner.media.document.mime_type == null || !this.messageOwner.media.document.mime_type.equals("image/webp")) {
                    label_90:
                        this.type = v3;
                        goto label_182;
                    }
                    else if(MessageObject.isGifDocument(this.messageOwner.media.document)) {
                        v2 = 8;
                        goto label_61;
                    }
                    else if(this.isSticker()) {
                        v2 = 13;
                        goto label_61;
                    }
                    else {
                        goto label_90;
                    }
                }
                else if((this.messageOwner.media instanceof TL_messageMediaGame)) {
                    goto label_19;
                }
                else {
                    if((this.messageOwner.media instanceof TL_messageMediaInvoice)) {
                    label_19:
                        this.type = 0;
                    }
                    else {
                    }

                    goto label_182;
                }
            }

            v2 = 4;
        label_61:
            this.type = v2;
        }
        else if((this.messageOwner instanceof TL_messageService)) {
            if(!(this.messageOwner.action instanceof TL_messageActionLoginUnknownLocation)) {
                if(!(this.messageOwner.action instanceof TL_messageActionChatEditPhoto)) {
                    if((this.messageOwner.action instanceof TL_messageActionUserUpdatedPhoto)) {
                    }
                    else {
                        int v5 = -1;
                        if(!(this.messageOwner.action instanceof TL_messageEncryptedAction)) {
                            if((this.messageOwner.action instanceof TL_messageActionHistoryClear)) {
                                goto label_50;
                            }

                            goto label_53;
                        }
                        else if((this.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionScreenshotMessages)) {
                            goto label_89;
                        }
                        else if((this.messageOwner.action.encryptedAction instanceof TL_decryptedMessageActionSetMessageTTL)) {
                            goto label_89;
                        }

                    label_50:
                        this.contentType = v5;
                        this.type = v5;
                        goto label_182;
                    label_53:
                        if(!(this.messageOwner.action instanceof TL_messageActionPhoneCall)) {
                            goto label_89;
                        }

                        v2 = 16;
                        goto label_61;
                    }
                }

                this.contentType = 1;
                v2 = 11;
                goto label_61;
            }
            else {
                goto label_19;
            }
        }

    label_182:
        if(v0 != 1000 && v0 != this.type) {
            this.generateThumbs(false);
        }
    }

    public static void setUnreadFlags(Message arg3, int arg4) {
        boolean v1 = false;
        boolean v0 = (arg4 & 1) == 0 ? true : false;
        arg3.unread = v0;
        if((arg4 & 2) == 0) {
            v1 = true;
        }

        arg3.media_unread = v1;
    }

    public static boolean shouldEncryptPhotoOrVideo(Message arg3) {
        boolean v1 = true;
        if((arg3 instanceof TL_message_secret)) {
            if(!(arg3.media instanceof TL_messageMediaPhoto) && !MessageObject.isVideoMessage(arg3) || (arg3.ttl <= 0 || arg3.ttl > 60)) {
                v1 = false;
            }
            else {
            }

            return v1;
        }

        if(!(arg3.media instanceof TL_messageMediaPhoto) && !(arg3.media instanceof TL_messageMediaDocument)) {
            goto label_27;
        }
        else if(arg3.media.ttl_seconds != 0) {
        }
        else {
        label_27:
            v1 = false;
        }

        return v1;
    }

    public boolean shouldEncryptPhotoOrVideo() {
        return MessageObject.shouldEncryptPhotoOrVideo(this.messageOwner);
    }
}

