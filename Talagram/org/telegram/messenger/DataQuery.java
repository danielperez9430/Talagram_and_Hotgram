package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent$ShortcutIconResource;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.content.pm.ShortcutInfo$Builder;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path$Direction;
import android.graphics.Path;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map$Entry;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$BotInfo;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$DraftMessage;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$InputPeer;
import org.telegram.tgnet.TLRPC$InputStickerSet;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$MessageEntity;
import org.telegram.tgnet.TLRPC$MessagesFilter;
import org.telegram.tgnet.TLRPC$StickerSet;
import org.telegram.tgnet.TLRPC$StickerSetCovered;
import org.telegram.tgnet.TLRPC$TL_channels_getMessages;
import org.telegram.tgnet.TLRPC$TL_contacts_getTopPeers;
import org.telegram.tgnet.TLRPC$TL_contacts_resetTopPeerRating;
import org.telegram.tgnet.TLRPC$TL_contacts_topPeers;
import org.telegram.tgnet.TLRPC$TL_contacts_topPeersDisabled;
import org.telegram.tgnet.TLRPC$TL_documentAttributeSticker;
import org.telegram.tgnet.TLRPC$TL_documentEmpty;
import org.telegram.tgnet.TLRPC$TL_draftMessage;
import org.telegram.tgnet.TLRPC$TL_draftMessageEmpty;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_inputDocument;
import org.telegram.tgnet.TLRPC$TL_inputMessageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_inputMessagesFilterDocument;
import org.telegram.tgnet.TLRPC$TL_inputMessagesFilterEmpty;
import org.telegram.tgnet.TLRPC$TL_inputMessagesFilterMusic;
import org.telegram.tgnet.TLRPC$TL_inputMessagesFilterPhotoVideo;
import org.telegram.tgnet.TLRPC$TL_inputMessagesFilterRoundVoice;
import org.telegram.tgnet.TLRPC$TL_inputMessagesFilterUrl;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetID;
import org.telegram.tgnet.TLRPC$TL_message;
import org.telegram.tgnet.TLRPC$TL_messageActionGameScore;
import org.telegram.tgnet.TLRPC$TL_messageActionHistoryClear;
import org.telegram.tgnet.TLRPC$TL_messageActionPaymentSent;
import org.telegram.tgnet.TLRPC$TL_messageActionPinMessage;
import org.telegram.tgnet.TLRPC$TL_messageEmpty;
import org.telegram.tgnet.TLRPC$TL_messageEntityBold;
import org.telegram.tgnet.TLRPC$TL_messageEntityCode;
import org.telegram.tgnet.TLRPC$TL_messageEntityEmail;
import org.telegram.tgnet.TLRPC$TL_messageEntityItalic;
import org.telegram.tgnet.TLRPC$TL_messageEntityPre;
import org.telegram.tgnet.TLRPC$TL_messageEntityTextUrl;
import org.telegram.tgnet.TLRPC$TL_messageEntityUrl;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_message_secret;
import org.telegram.tgnet.TLRPC$TL_messages_allStickers;
import org.telegram.tgnet.TLRPC$TL_messages_archivedStickers;
import org.telegram.tgnet.TLRPC$TL_messages_channelMessages;
import org.telegram.tgnet.TLRPC$TL_messages_faveSticker;
import org.telegram.tgnet.TLRPC$TL_messages_favedStickers;
import org.telegram.tgnet.TLRPC$TL_messages_featuredStickers;
import org.telegram.tgnet.TLRPC$TL_messages_getAllDrafts;
import org.telegram.tgnet.TLRPC$TL_messages_getAllStickers;
import org.telegram.tgnet.TLRPC$TL_messages_getArchivedStickers;
import org.telegram.tgnet.TLRPC$TL_messages_getFavedStickers;
import org.telegram.tgnet.TLRPC$TL_messages_getFeaturedStickers;
import org.telegram.tgnet.TLRPC$TL_messages_getMaskStickers;
import org.telegram.tgnet.TLRPC$TL_messages_getMessages;
import org.telegram.tgnet.TLRPC$TL_messages_getRecentStickers;
import org.telegram.tgnet.TLRPC$TL_messages_getSavedGifs;
import org.telegram.tgnet.TLRPC$TL_messages_getStickerSet;
import org.telegram.tgnet.TLRPC$TL_messages_installStickerSet;
import org.telegram.tgnet.TLRPC$TL_messages_messages;
import org.telegram.tgnet.TLRPC$TL_messages_messagesSlice;
import org.telegram.tgnet.TLRPC$TL_messages_readFeaturedStickers;
import org.telegram.tgnet.TLRPC$TL_messages_recentStickers;
import org.telegram.tgnet.TLRPC$TL_messages_saveDraft;
import org.telegram.tgnet.TLRPC$TL_messages_saveGif;
import org.telegram.tgnet.TLRPC$TL_messages_savedGifs;
import org.telegram.tgnet.TLRPC$TL_messages_search;
import org.telegram.tgnet.TLRPC$TL_messages_stickerSet;
import org.telegram.tgnet.TLRPC$TL_messages_stickerSetInstallResultArchive;
import org.telegram.tgnet.TLRPC$TL_messages_uninstallStickerSet;
import org.telegram.tgnet.TLRPC$TL_peerChat;
import org.telegram.tgnet.TLRPC$TL_peerUser;
import org.telegram.tgnet.TLRPC$TL_stickerPack;
import org.telegram.tgnet.TLRPC$TL_topPeer;
import org.telegram.tgnet.TLRPC$TL_topPeerCategoryBotsInline;
import org.telegram.tgnet.TLRPC$TL_topPeerCategoryCorrespondents;
import org.telegram.tgnet.TLRPC$TL_topPeerCategoryPeers;
import org.telegram.tgnet.TLRPC$Updates;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$messages_Messages;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.StickersArchiveAlert;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.LaunchActivity;

public class DataQuery {
    final class org.telegram.messenger.DataQuery$52 implements Comparator {
        org.telegram.messenger.DataQuery$52() {
            super();
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((MessageEntity)arg1), ((MessageEntity)arg2));
        }

        public int compare(MessageEntity arg3, MessageEntity arg4) {
            if(arg3.offset > arg4.offset) {
                return 1;
            }

            if(arg3.offset < arg4.offset) {
                return -1;
            }

            return 0;
        }
    }

    private static volatile DataQuery[] Instance = null;
    public static final int MEDIA_AUDIO = 2;
    public static final int MEDIA_FILE = 1;
    public static final int MEDIA_MUSIC = 4;
    public static final int MEDIA_PHOTOVIDEO = 0;
    public static final int MEDIA_TYPES_COUNT = 5;
    public static final int MEDIA_URL = 3;
    public static final int TYPE_FAVE = 2;
    public static final int TYPE_FEATURED = 3;
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_MASK = 1;
    private static HashMap allStickers;
    private static HashMap allStickersFeatured;
    public static int[] archivedStickersCount;
    private static RectF bitmapRect;
    private SparseArray botInfos;
    private LongSparseArray botKeyboards;
    private SparseLongArray botKeyboardsByMids;
    private static int currentAccount;
    private LongSparseArray draftMessages;
    private LongSparseArray drafts;
    private static Comparator entityComparator;
    private static Paint erasePaint;
    public static ArrayList featuredStickerSets;
    private LongSparseArray featuredStickerSetsById;
    private boolean featuredStickersLoaded;
    private static LongSparseArray groupStickerSets;
    public ArrayList hints;
    private boolean inTransaction;
    public ArrayList inlineBots;
    private static LongSparseArray installedStickerSetsById;
    private long lastMergeDialogId;
    private int lastReqId;
    private int lastReturnedNum;
    private String lastSearchQuery;
    private static int[] loadDate;
    private int loadFeaturedDate;
    private static int loadFeaturedHash;
    private static int[] loadHash;
    boolean loaded;
    boolean loading;
    private boolean loadingDrafts;
    private boolean loadingFeaturedStickers;
    private boolean loadingRecentGifs;
    private static boolean[] loadingRecentStickers;
    public static boolean[] loadingStickers;
    private int mergeReqId;
    private int[] messagesSearchCount;
    private boolean[] messagesSearchEndReached;
    private SharedPreferences preferences;
    private ArrayList readingStickerSets;
    private ArrayList recentGifs;
    private boolean recentGifsLoaded;
    private static ArrayList[] recentStickers;
    private static boolean[] recentStickersLoaded;
    private int reqId;
    private static Paint roundPaint;
    private static Path roundPath;
    private ArrayList searchResultMessages;
    private SparseArray[] searchResultMessagesMap;
    private static ArrayList[] stickerSets;
    private static LongSparseArray stickerSetsById;
    private static HashMap stickerSetsByName;
    private static LongSparseArray stickersByEmoji;
    private static boolean[] stickersLoaded;
    private ArrayList unreadStickerSets;

    static {
        DataQuery.Instance = new DataQuery[3];
        DataQuery.stickerSets = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList(0), new ArrayList()};
        DataQuery.stickerSetsById = new LongSparseArray();
        DataQuery.installedStickerSetsById = new LongSparseArray();
        DataQuery.groupStickerSets = new LongSparseArray();
        DataQuery.stickerSetsByName = new HashMap();
        DataQuery.loadingStickers = new boolean[4];
        DataQuery.stickersLoaded = new boolean[4];
        DataQuery.loadHash = new int[4];
        DataQuery.loadDate = new int[4];
        DataQuery.archivedStickersCount = new int[2];
        DataQuery.stickersByEmoji = new LongSparseArray();
        DataQuery.allStickers = new HashMap();
        DataQuery.allStickersFeatured = new HashMap();
        DataQuery.recentStickers = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
        DataQuery.loadingRecentStickers = new boolean[3];
        DataQuery.recentStickersLoaded = new boolean[3];
        DataQuery.featuredStickerSets = new ArrayList();
        DataQuery.entityComparator = new org.telegram.messenger.DataQuery$52();
    }

    public DataQuery(int arg7) {
        LongSparseArray v1_1;
        Message v0_3;
        String v0;
        Context v7;
        super();
        this.recentGifs = new ArrayList();
        this.featuredStickerSetsById = new LongSparseArray();
        this.unreadStickerSets = new ArrayList();
        this.readingStickerSets = new ArrayList();
        this.messagesSearchCount = new int[]{0, 0};
        this.messagesSearchEndReached = new boolean[]{false, false};
        this.searchResultMessages = new ArrayList();
        this.searchResultMessagesMap = new SparseArray[]{new SparseArray(), new SparseArray()};
        this.hints = new ArrayList();
        this.inlineBots = new ArrayList();
        this.drafts = new LongSparseArray();
        this.draftMessages = new LongSparseArray();
        this.botInfos = new SparseArray();
        this.botKeyboards = new LongSparseArray();
        this.botKeyboardsByMids = new SparseLongArray();
        DataQuery.currentAccount = arg7;
        if(DataQuery.currentAccount == 0) {
            v7 = ApplicationLoader.applicationContext;
            v0 = "drafts";
        }
        else {
            v7 = ApplicationLoader.applicationContext;
            v0 = "drafts" + DataQuery.currentAccount;
        }

        this.preferences = v7.getSharedPreferences(v0, 0);
        Iterator v7_1 = this.preferences.getAll().entrySet().iterator();
        while(v7_1.hasNext()) {
            Object v0_2 = v7_1.next();
            try {
                Object v1 = ((Map$Entry)v0_2).getKey();
                long v4 = Utilities.parseLong(((String)v1)).longValue();
                SerializedData v2 = new SerializedData(Utilities.hexToBytes(((Map$Entry)v0_2).getValue()));
                if(((String)v1).startsWith("r_")) {
                    v0_3 = Message.TLdeserialize(((AbstractSerializedData)v2), v2.readInt32(true), true);
                    v0_3.readAttachPath(((AbstractSerializedData)v2), UserConfig.getInstance(DataQuery.currentAccount).clientUserId);
                    if(v0_3 != null) {
                        v1_1 = this.draftMessages;
                        goto label_94;
                    }
                }
                else {
                    DraftMessage v0_4 = DraftMessage.TLdeserialize(((AbstractSerializedData)v2), v2.readInt32(true), true);
                    if(v0_4 != null) {
                        v1_1 = this.drafts;
                    label_94:
                        v1_1.put(v4, v0_3);
                    }
                }

                v2.cleanup();
            }
            catch(Exception ) {
            }
        }
    }

    static int access$000() {
        return DataQuery.currentAccount;
    }

    static void access$100(DataQuery arg0, StickerSet arg1, boolean arg2) {
        arg0.loadGroupStickerSet(arg1, arg2);
    }

    static int access$1000(DataQuery arg0, ArrayList arg1) {
        return arg0.calcFeaturedStickersHash(arg1);
    }

    static void access$1100(DataQuery arg0, ArrayList arg1, ArrayList arg2, boolean arg3, int arg4, int arg5) {
        arg0.processLoadedFeaturedStickers(arg1, arg2, arg3, arg4, arg5);
    }

    static boolean access$1202(DataQuery arg0, boolean arg1) {
        arg0.loadingFeaturedStickers = arg1;
        return arg1;
    }

    static boolean access$1302(DataQuery arg0, boolean arg1) {
        arg0.featuredStickersLoaded = arg1;
        return arg1;
    }

    static int access$1400() {
        return DataQuery.loadFeaturedHash;
    }

    static int access$1402(int arg0) {
        DataQuery.loadFeaturedHash = arg0;
        return arg0;
    }

    static void access$1500(DataQuery arg0, ArrayList arg1, ArrayList arg2, int arg3, int arg4) {
        arg0.putFeaturedStickersToCache(arg1, arg2, arg3, arg4);
    }

    static ArrayList access$1600(DataQuery arg0) {
        return arg0.unreadStickerSets;
    }

    static ArrayList access$1602(DataQuery arg0, ArrayList arg1) {
        arg0.unreadStickerSets = arg1;
        return arg1;
    }

    static LongSparseArray access$1702(DataQuery arg0, LongSparseArray arg1) {
        arg0.featuredStickerSetsById = arg1;
        return arg1;
    }

    static int access$1800(DataQuery arg0) {
        return arg0.loadFeaturedDate;
    }

    static int access$1802(DataQuery arg0, int arg1) {
        arg0.loadFeaturedDate = arg1;
        return arg1;
    }

    static ArrayList access$1900(DataQuery arg0) {
        return arg0.readingStickerSets;
    }

    static LongSparseArray access$200() {
        return DataQuery.groupStickerSets;
    }

    static void access$2000(int arg0, ArrayList arg1, boolean arg2, int arg3, int arg4) {
        DataQuery.processLoadedStickers(arg0, arg1, arg2, arg3, arg4);
    }

    static int access$2100(ArrayList arg0) {
        return DataQuery.calcStickersHash(arg0);
    }

    static void access$2200(int arg0, TL_messages_allStickers arg1) {
        DataQuery.processLoadStickersResponse(arg0, arg1);
    }

    static boolean[] access$2300() {
        return DataQuery.stickersLoaded;
    }

    static int[] access$2400() {
        return DataQuery.loadHash;
    }

    static void access$2500(int arg0, ArrayList arg1, int arg2, int arg3) {
        DataQuery.putStickersToCache(arg0, arg1, arg2, arg3);
    }

    static ArrayList[] access$2600() {
        return DataQuery.stickerSets;
    }

    static LongSparseArray access$2700() {
        return DataQuery.stickerSetsById;
    }

    static LongSparseArray access$2800() {
        return DataQuery.installedStickerSetsById;
    }

    static HashMap access$2900() {
        return DataQuery.stickerSetsByName;
    }

    static int[] access$3000() {
        return DataQuery.loadDate;
    }

    static ArrayList access$302(DataQuery arg0, ArrayList arg1) {
        arg0.recentGifs = arg1;
        return arg1;
    }

    static HashMap access$3102(HashMap arg0) {
        DataQuery.allStickers = arg0;
        return arg0;
    }

    static LongSparseArray access$3202(LongSparseArray arg0) {
        DataQuery.stickersByEmoji = arg0;
        return arg0;
    }

    static HashMap access$3302(HashMap arg0) {
        DataQuery.allStickersFeatured = arg0;
        return arg0;
    }

    static long access$3400(DataQuery arg2) {
        return arg2.lastMergeDialogId;
    }

    static int access$3502(DataQuery arg0, int arg1) {
        arg0.mergeReqId = arg1;
        return arg1;
    }

    static boolean[] access$3600(DataQuery arg0) {
        return arg0.messagesSearchEndReached;
    }

    static int[] access$3700(DataQuery arg0) {
        return arg0.messagesSearchCount;
    }

    static void access$3800(DataQuery arg0, String arg1, long arg2, long arg4, int arg6, int arg7, boolean arg8, User arg9) {
        arg0.searchMessagesInChat(arg1, arg2, arg4, arg6, arg7, arg8, arg9);
    }

    static int access$3900(DataQuery arg0) {
        return arg0.lastReqId;
    }

    static int access$4002(DataQuery arg0, int arg1) {
        arg0.reqId = arg1;
        return arg1;
    }

    static boolean access$402(DataQuery arg0, boolean arg1) {
        arg0.loadingRecentGifs = arg1;
        return arg1;
    }

    static int access$4100(DataQuery arg0) {
        return arg0.lastReturnedNum;
    }

    static int access$4102(DataQuery arg0, int arg1) {
        arg0.lastReturnedNum = arg1;
        return arg1;
    }

    static ArrayList access$4200(DataQuery arg0) {
        return arg0.searchResultMessages;
    }

    static SparseArray[] access$4300(DataQuery arg0) {
        return arg0.searchResultMessagesMap;
    }

    static int access$4400(DataQuery arg0) {
        return arg0.getMask();
    }

    static String access$4500(DataQuery arg0) {
        return arg0.lastSearchQuery;
    }

    static void access$4600(DataQuery arg0, messages_Messages arg1, long arg2, int arg4, int arg5, int arg6, boolean arg7, int arg8, boolean arg9, boolean arg10) {
        arg0.processLoadedMedia(arg1, arg2, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
    }

    static void access$4700(DataQuery arg0, int arg1, long arg2, int arg4, int arg5, boolean arg6) {
        arg0.processLoadedMediaCount(arg1, arg2, arg4, arg5, arg6);
    }

    static void access$4800(DataQuery arg0, long arg1, int arg3, int arg4) {
        arg0.putMediaCountDatabase(arg1, arg3, arg4);
    }

    static Paint access$4900() {
        return DataQuery.roundPaint;
    }

    static Paint access$4902(Paint arg0) {
        DataQuery.roundPaint = arg0;
        return arg0;
    }

    static RectF access$5000() {
        return DataQuery.bitmapRect;
    }

    static RectF access$5002(RectF arg0) {
        DataQuery.bitmapRect = arg0;
        return arg0;
    }

    static boolean access$502(DataQuery arg0, boolean arg1) {
        arg0.recentGifsLoaded = arg1;
        return arg1;
    }

    static Paint access$5100() {
        return DataQuery.erasePaint;
    }

    static Paint access$5102(Paint arg0) {
        DataQuery.erasePaint = arg0;
        return arg0;
    }

    static Path access$5200() {
        return DataQuery.roundPath;
    }

    static Path access$5202(Path arg0) {
        DataQuery.roundPath = arg0;
        return arg0;
    }

    static void access$5300(DataQuery arg0, int arg1, int arg2, double arg3) {
        arg0.savePeer(arg1, arg2, arg3);
    }

    static MessageObject access$5400(DataQuery arg0, int arg1, int arg2, boolean arg3) {
        return arg0.loadPinnedMessageInternal(arg1, arg2, arg3);
    }

    static void access$5500(ArrayList arg0) {
        DataQuery.removeEmptyMessages(arg0);
    }

    static MessageObject access$5600(DataQuery arg0, Message arg1, ArrayList arg2, ArrayList arg3, boolean arg4, boolean arg5) {
        return arg0.broadcastPinnedMessage(arg1, arg2, arg3, arg4, arg5);
    }

    static void access$5700(DataQuery arg0, Message arg1) {
        arg0.savePinnedMessage(arg1);
    }

    static void access$5800(DataQuery arg0, ArrayList arg1, SparseArray arg2, ArrayList arg3, ArrayList arg4, long arg5, boolean arg7) {
        arg0.broadcastReplyMessages(arg1, arg2, arg3, arg4, arg5, arg7);
    }

    static void access$5900(DataQuery arg0, SparseArray arg1, ArrayList arg2) {
        arg0.saveReplyMessages(arg1, arg2);
    }

    static ArrayList[] access$600() {
        return DataQuery.recentStickers;
    }

    static boolean access$6002(DataQuery arg0, boolean arg1) {
        arg0.loadingDrafts = arg1;
        return arg1;
    }

    static void access$6100(DataQuery arg0, long arg1, Message arg3) {
        arg0.saveDraftReplyMessage(arg1, arg3);
    }

    static LongSparseArray access$6200(DataQuery arg0) {
        return arg0.drafts;
    }

    static LongSparseArray access$6300(DataQuery arg0) {
        return arg0.draftMessages;
    }

    static SharedPreferences access$6400(DataQuery arg0) {
        return arg0.preferences;
    }

    static SparseLongArray access$6500(DataQuery arg0) {
        return arg0.botKeyboardsByMids;
    }

    static LongSparseArray access$6600(DataQuery arg0) {
        return arg0.botKeyboards;
    }

    static boolean[] access$700() {
        return DataQuery.loadingRecentStickers;
    }

    static boolean[] access$800() {
        return DataQuery.recentStickersLoaded;
    }

    static void access$900(DataQuery arg0, int arg1, ArrayList arg2, boolean arg3, int arg4) {
        arg0.processLoadedRecentDocuments(arg1, arg2, arg3, arg4);
    }

    public void addNewStickerSet(TL_messages_stickerSet arg13) {
        ArrayList v5_1;
        Object v4;
        if(DataQuery.stickerSetsById.indexOfKey(arg13.set.id) < 0) {
            if(DataQuery.stickerSetsByName.containsKey(arg13.set.short_name)) {
            }
            else {
                boolean v0 = arg13.set.masks;
                DataQuery.stickerSets[((int)v0)].add(0, arg13);
                DataQuery.stickerSetsById.put(arg13.set.id, arg13);
                DataQuery.installedStickerSetsById.put(arg13.set.id, arg13);
                DataQuery.stickerSetsByName.put(arg13.set.short_name, arg13);
                LongSparseArray v1 = new LongSparseArray();
                int v3;
                for(v3 = 0; v3 < arg13.documents.size(); ++v3) {
                    v4 = arg13.documents.get(v3);
                    v1.put(((Document)v4).id, v4);
                }

                for(v3 = 0; v3 < arg13.packs.size(); ++v3) {
                    v4 = arg13.packs.get(v3);
                    ((TL_stickerPack)v4).emoticon = ((TL_stickerPack)v4).emoticon.replace("️", "");
                    Object v5 = DataQuery.allStickers.get(((TL_stickerPack)v4).emoticon);
                    if(v5 == null) {
                        v5_1 = new ArrayList();
                        DataQuery.allStickers.put(((TL_stickerPack)v4).emoticon, v5_1);
                    }

                    int v6;
                    for(v6 = 0; v6 < ((TL_stickerPack)v4).documents.size(); ++v6) {
                        Object v7 = ((TL_stickerPack)v4).documents.get(v6);
                        if(DataQuery.stickersByEmoji.indexOfKey(((Long)v7).longValue()) < 0) {
                            DataQuery.stickersByEmoji.put(((Long)v7).longValue(), ((TL_stickerPack)v4).emoticon);
                        }

                        v7 = v1.get(((Long)v7).longValue());
                        if(v7 != null) {
                            v5_1.add(v7);
                        }
                    }
                }

                DataQuery.loadHash[((int)v0)] = DataQuery.calcStickersHash(DataQuery.stickerSets[((int)v0)]);
                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.stickersDidLoaded, new Object[]{Integer.valueOf(((int)v0))});
                DataQuery.loadStickers(((int)v0), false, true);
            }
        }
    }

    public void addRecentGif(Document arg11, int arg12) {
        int v1 = 0;
        int v2 = 0;
        while(v1 < this.recentGifs.size()) {
            Object v3 = this.recentGifs.get(v1);
            if(((Document)v3).id == arg11.id) {
                this.recentGifs.remove(v1);
                this.recentGifs.add(0, v3);
                v2 = 1;
            }

            ++v1;
        }

        if(v2 == 0) {
            this.recentGifs.add(0, arg11);
        }

        if(this.recentGifs.size() > MessagesController.getInstance(DataQuery.currentAccount).maxRecentGifsCount) {
            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(this.recentGifs.remove(this.recentGifs.size() - 1)) {
                public void run() {
                    try {
                        SQLiteDatabase v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        StringBuilder v1 = new StringBuilder();
                        v1.append("DELETE FROM web_recent_v3 WHERE id = \'");
                        v1.append(this.val$old.id);
                        v1.append("\' AND type = 2");
                        v0_1.a(v1.toString()).c().e();
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            });
        }

        ArrayList v1_1 = new ArrayList();
        v1_1.add(arg11);
        this.processLoadedRecentDocuments(0, v1_1, true, arg12);
    }

    public void addRecentSticker(int arg11, Document arg12, int arg13, boolean arg14) {
        Document v2_3;
        int v5;
        String v3_1;
        Context v2_1;
        int v1 = 0;
        int v2 = 0;
        while(v1 < DataQuery.recentStickers[arg11].size()) {
            Object v3 = DataQuery.recentStickers[arg11].get(v1);
            if(((Document)v3).id == arg12.id) {
                DataQuery.recentStickers[arg11].remove(v1);
                if(!arg14) {
                    DataQuery.recentStickers[arg11].add(0, v3);
                }

                v2 = 1;
            }

            ++v1;
        }

        if(v2 == 0 && !arg14) {
            DataQuery.recentStickers[arg11].add(0, arg12);
        }

        v1 = 2;
        if(arg11 == v1) {
            if(arg14) {
                v2_1 = ApplicationLoader.applicationContext;
                v3_1 = "RemovedFromFavorites";
                v5 = 2131625856;
            }
            else {
                v2_1 = ApplicationLoader.applicationContext;
                v3_1 = "AddedToFavorites";
                v5 = 2131624044;
            }

            Toast.makeText(v2_1, LocaleController.getString(v3_1, v5), 0).show();
            TL_messages_faveSticker v2_2 = new TL_messages_faveSticker();
            v2_2.id = new TL_inputDocument();
            v2_2.id.id = arg12.id;
            v2_2.id.access_hash = arg12.access_hash;
            v2_2.unfave = arg14;
            ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v2_2), new RequestDelegate() {
                public void run(TLObject arg1, TL_error arg2) {
                }
            });
            v2 = MessagesController.getInstance(DataQuery.currentAccount).maxFaveStickersCount;
        }
        else {
            v2 = MessagesController.getInstance(DataQuery.currentAccount).maxRecentStickersCount;
        }

        if(DataQuery.recentStickers[arg11].size() > v2 || (arg14)) {
            if(arg14) {
                v2_3 = arg12;
            }
            else {
                Object v2_4 = DataQuery.recentStickers[arg11].remove(DataQuery.recentStickers[arg11].size() - 1);
            }

            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg11, v2_3) {
                public void run() {
                    int v0;
                    if(this.val$type == 0) {
                        v0 = 3;
                    }
                    else if(this.val$type == 1) {
                        v0 = 4;
                    }
                    else {
                        v0 = 5;
                    }

                    try {
                        SQLiteDatabase v1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        StringBuilder v2 = new StringBuilder();
                        v2.append("DELETE FROM web_recent_v3 WHERE id = \'");
                        v2.append(this.val$old.id);
                        v2.append("\' AND type = ");
                        v2.append(v0);
                        v1.a(v2.toString()).c().e();
                    }
                    catch(Exception v0_1) {
                        FileLog.e(((Throwable)v0_1));
                    }
                }
            });
        }

        if(!arg14) {
            ArrayList v14 = new ArrayList();
            v14.add(arg12);
            this.processLoadedRecentDocuments(arg11, v14, false, arg13);
        }

        if(arg11 == v1) {
            NotificationCenter v12 = NotificationCenter.getInstance(DataQuery.currentAccount);
            arg13 = NotificationCenter.recentDocumentsDidLoaded;
            Object[] v14_1 = new Object[v1];
            v14_1[0] = Boolean.valueOf(false);
            v14_1[1] = Integer.valueOf(arg11);
            v12.postNotificationName(arg13, v14_1);
        }
    }

    public void beginTransaction() {
        this.inTransaction = true;
    }

    private MessageObject broadcastPinnedMessage(Message arg9, ArrayList arg10, ArrayList arg11, boolean arg12, boolean arg13) {
        SparseArray v6 = new SparseArray();
        int v0 = 0;
        int v1;
        for(v1 = 0; v1 < arg10.size(); ++v1) {
            Object v2 = arg10.get(v1);
            v6.put(((User)v2).id, v2);
        }

        SparseArray v7 = new SparseArray();
        while(v0 < arg11.size()) {
            Object v1_1 = arg11.get(v0);
            v7.put(((Chat)v1_1).id, v1_1);
            ++v0;
        }

        if(arg13) {
            return new MessageObject(DataQuery.currentAccount, arg9, v6, v7, false);
        }

        AndroidUtilities.runOnUIThread(new Runnable(arg10, arg12, arg11, arg9, v6, v7) {
            public void run() {
                MessagesController.getInstance(DataQuery.currentAccount).putUsers(this.val$users, this.val$isCache);
                MessagesController.getInstance(DataQuery.currentAccount).putChats(this.val$chats, this.val$isCache);
                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.didLoadedPinnedMessage, new Object[]{new MessageObject(DataQuery.currentAccount, this.val$result, this.val$usersDict, this.val$chatsDict, false)});
            }
        });
        return null;
    }

    private void broadcastReplyMessages(ArrayList arg13, SparseArray arg14, ArrayList arg15, ArrayList arg16, long arg17, boolean arg19) {
        SparseArray v7 = new SparseArray();
        int v0 = 0;
        int v1;
        for(v1 = 0; v1 < arg15.size(); ++v1) {
            Object v3 = arg15.get(v1);
            v7.put(((User)v3).id, v3);
        }

        SparseArray v8 = new SparseArray();
        while(v0 < arg16.size()) {
            Object v1_1 = arg16.get(v0);
            v8.put(((Chat)v1_1).id, v1_1);
            ++v0;
        }

        AndroidUtilities.runOnUIThread(new Runnable(arg15, arg19, arg16, arg13, arg14, v7, v8, arg17) {
            public void run() {
                MessagesController.getInstance(DataQuery.currentAccount).putUsers(this.val$users, this.val$isCache);
                MessagesController.getInstance(DataQuery.currentAccount).putChats(this.val$chats, this.val$isCache);
                int v1 = 0;
                int v2 = 0;
                while(v1 < this.val$result.size()) {
                    Object v7 = this.val$result.get(v1);
                    Object v3 = this.val$replyMessageOwners.get(((Message)v7).id);
                    if(v3 != null) {
                        MessageObject v2_1 = new MessageObject(DataQuery.currentAccount, ((Message)v7), this.val$usersDict, this.val$chatsDict, false);
                        int v5;
                        for(v5 = 0; v5 < ((ArrayList)v3).size(); ++v5) {
                            Object v6 = ((ArrayList)v3).get(v5);
                            ((MessageObject)v6).replyMessageObject = v2_1;
                            User v8 = null;
                            if((((MessageObject)v6).messageOwner.action instanceof TL_messageActionPinMessage)) {
                                ((MessageObject)v6).generatePinMessageText(v8, ((Chat)v8));
                            }
                            else if((((MessageObject)v6).messageOwner.action instanceof TL_messageActionGameScore)) {
                                ((MessageObject)v6).generateGameMessageText(v8);
                            }
                            else if((((MessageObject)v6).messageOwner.action instanceof TL_messageActionPaymentSent)) {
                                ((MessageObject)v6).generatePaymentSentMessageText(v8);
                            }

                            if(((MessageObject)v6).isMegagroup()) {
                                ((MessageObject)v6).replyMessageObject.messageOwner.flags |= -2147483648;
                            }
                        }

                        v2 = 1;
                    }

                    ++v1;
                }

                if(v2 != 0) {
                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.didLoadedReplyMessages, new Object[]{Long.valueOf(this.val$dialog_id)});
                }
            }
        });
    }

    public void buildShortcuts() {
        if(Build$VERSION.SDK_INT < 25) {
            return;
        }

        ArrayList v0 = new ArrayList();
        int v1 = 0;
        while(v1 < this.hints.size()) {
            v0.add(this.hints.get(v1));
            if(v0.size() == 3) {
            }
            else {
                ++v1;
                continue;
            }

            break;
        }

        Utilities.globalQueue.postRunnable(new Runnable(v0) {
            @SuppressLint(value={"NewApi"}) public void run() {
                Bitmap v13;
                Bitmap v8_4;
                Bitmap v12;
                FileLocation v0_5;
                String v7_2;
                String v0_4;
                Chat v0_3;
                User v7_1;
                Intent v5_1;
                long v8_2;
                long v9;
                int v8_1;
                int v6;
                ArrayList v4;
                Object v2;
                org.telegram.messenger.DataQuery$42 v1 = this;
                try {
                    v2 = ApplicationLoader.applicationContext.getSystemService(ShortcutManager.class);
                    List v0 = ((ShortcutManager)v2).getDynamicShortcuts();
                    ArrayList v3 = new ArrayList();
                    v4 = new ArrayList();
                    ArrayList v5 = new ArrayList();
                    v6 = 0;
                    if(v0 != null && !v0.isEmpty()) {
                        v4.add("compose");
                        int v7;
                        for(v7 = 0; v7 < v1.val$hintsFinal.size(); ++v7) {
                            Object v8 = v1.val$hintsFinal.get(v7);
                            if(((TL_topPeer)v8).peer.user_id != 0) {
                                v8_1 = ((TL_topPeer)v8).peer.user_id;
                                goto label_29;
                            }
                            else {
                                v9 = ((long)(-((TL_topPeer)v8).peer.chat_id));
                                if(v9 == 0) {
                                    v8_1 = -((TL_topPeer)v8).peer.channel_id;
                                label_29:
                                    v8_2 = ((long)v8_1);
                                }
                                else {
                                    v8_2 = v9;
                                }
                            }

                            v4.add("did" + v8_2);
                        }

                        for(v7 = 0; v7 < v0.size(); ++v7) {
                            String v8_3 = v0.get(v7).getId();
                            if(!v4.remove(v8_3)) {
                                v5.add(v8_3);
                            }

                            v3.add(v8_3);
                        }

                        if(!v4.isEmpty()) {
                            goto label_67;
                        }

                        if(!v5.isEmpty()) {
                            goto label_67;
                        }

                        return;
                    }

                label_67:
                    Intent v0_1 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
                    v0_1.setAction("new_dialog");
                    v4 = new ArrayList();
                    v4.add(new ShortcutInfo$Builder(ApplicationLoader.applicationContext, "compose").setShortLabel(LocaleController.getString("NewConversationShortcut", 2131625237)).setLongLabel(LocaleController.getString("NewConversationShortcut", 2131625237)).setIcon(Icon.createWithResource(ApplicationLoader.applicationContext, 2131231560)).setIntent(v0_1).build());
                    if(v3.contains("compose")) {
                        ((ShortcutManager)v2).updateShortcuts(((List)v4));
                    }
                    else {
                        ((ShortcutManager)v2).addDynamicShortcuts(((List)v4));
                    }

                    v4.clear();
                    if(v5.isEmpty()) {
                        goto label_103;
                    }

                    ((ShortcutManager)v2).removeDynamicShortcuts(((List)v5));
                    while(true) {
                    label_103:
                        if(v6 >= v1.val$hintsFinal.size()) {
                            return;
                        }

                        v5_1 = new Intent(ApplicationLoader.applicationContext, OpenChatReceiver.class);
                        Object v0_2 = v1.val$hintsFinal.get(v6);
                        if(((TL_topPeer)v0_2).peer.user_id != 0) {
                            v5_1.putExtra("userId", ((TL_topPeer)v0_2).peer.user_id);
                            v7_1 = MessagesController.getInstance(DataQuery.currentAccount).getUser(Integer.valueOf(((TL_topPeer)v0_2).peer.user_id));
                            v9 = ((long)((TL_topPeer)v0_2).peer.user_id);
                            v0_3 = null;
                        }
                        else {
                            v7 = ((TL_topPeer)v0_2).peer.chat_id;
                            if(v7 == 0) {
                                v7 = ((TL_topPeer)v0_2).peer.channel_id;
                            }

                            v0_3 = MessagesController.getInstance(DataQuery.currentAccount).getChat(Integer.valueOf(v7));
                            v5_1.putExtra("chatId", v7);
                            v9 = ((long)(-v7));
                            v7_1 = null;
                        }

                        if(v7_1 == null && v0_3 == null) {
                            goto label_288;
                        }

                        if(v7_1 != null) {
                            v0_4 = ContactsController.formatName(v7_1.first_name, v7_1.last_name);
                            if(v7_1.photo != null) {
                                FileLocation v18 = v7_1.photo.photo_small;
                                v7_2 = v0_4;
                                v0_5 = v18;
                            }
                            else {
                                v7_2 = v0_4;
                                goto label_167;
                            }
                        }
                        else {
                            v7_2 = v0_3.title;
                            if(v0_3.photo != null) {
                                v0_5 = v0_3.photo.photo_small;
                            }
                            else {
                            label_167:
                                v0_5 = null;
                            }
                        }

                        v5_1.putExtra("currentAccount", DataQuery.currentAccount);
                        v5_1.setAction("com.tmessages.openchat" + v9);
                        v5_1.addFlags(67108864);
                        if(v0_5 == null) {
                            goto label_256;
                        }

                        break;
                    }
                }
                catch(Throwable ) {
                    return;
                }

                try {
                    v12 = BitmapFactory.decodeFile(FileLoader.getPathToAttach(((TLObject)v0_5), true).toString());
                    if(v12 == null) {
                        goto label_250;
                    }
                }
                catch(Throwable v0_6) {
                    v8_4 = null;
                    goto label_254;
                }

                float v0_7 = 48f;
                try {
                    int v0_8 = AndroidUtilities.dp(v0_7);
                    v13 = Bitmap.createBitmap(v0_8, v0_8, Bitmap$Config.ARGB_8888);
                    Canvas v14 = new Canvas(v13);
                    float v16 = 2f;
                    if(DataQuery.roundPaint == null) {
                        DataQuery.roundPaint = new Paint(3);
                        DataQuery.bitmapRect = new RectF();
                        DataQuery.erasePaint = new Paint(1);
                        DataQuery.erasePaint.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
                        DataQuery.roundPath = new Path();
                        DataQuery.roundPath.addCircle(((float)(v0_8 / 2)), ((float)(v0_8 / 2)), ((float)(v0_8 / 2 - AndroidUtilities.dp(v16))), Path$Direction.CW);
                        DataQuery.roundPath.toggleInverseFillType();
                    }

                    DataQuery.bitmapRect.set(((float)AndroidUtilities.dp(v16)), ((float)AndroidUtilities.dp(v16)), ((float)AndroidUtilities.dp(46f)), ((float)AndroidUtilities.dp(46f)));
                    Rect v8_5 = null;
                    v14.drawBitmap(v12, v8_5, DataQuery.bitmapRect, DataQuery.roundPaint);
                    v14.drawPath(DataQuery.roundPath, DataQuery.erasePaint);
                    try {
                        v14.setBitmap(((Bitmap)v8_5));
                    }
                    catch(Exception ) {
                    }

                    goto label_245;
                }
                catch(Throwable v0_6) {
                    v8_4 = v12;
                }

                try {
                label_254:
                    FileLog.e(v0_6);
                    goto label_257;
                }
                catch(Throwable ) {
                    return;
                }

            label_245:
                v8_4 = v13;
                goto label_257;
            label_250:
                v8_4 = v12;
                goto label_257;
                try {
                label_256:
                    v8_4 = null;
                label_257:
                    v0_4 = "did" + v9;
                    if(TextUtils.isEmpty(((CharSequence)v7_2))) {
                        v7_2 = " ";
                    }

                    ShortcutInfo$Builder v1_1 = new ShortcutInfo$Builder(ApplicationLoader.applicationContext, v0_4).setShortLabel(((CharSequence)v7_2)).setLongLabel(((CharSequence)v7_2)).setIntent(v5_1);
                    Icon v5_2 = v8_4 != null ? Icon.createWithBitmap(v8_4) : Icon.createWithResource(ApplicationLoader.applicationContext, 2131231561);
                    v1_1.setIcon(v5_2);
                    v4.add(v1_1.build());
                    if(v3.contains(v0_4)) {
                        ((ShortcutManager)v2).updateShortcuts(((List)v4));
                    }
                    else {
                        ((ShortcutManager)v2).addDynamicShortcuts(((List)v4));
                    }

                    v4.clear();
                }
                catch(Throwable ) {
                    return;
                }

            label_288:
                ++v6;
                v1 = this;
                goto label_103;
            }
        });
    }

    private static int calcDocumentsHash(ArrayList arg11) {
        int v0 = 0;
        if(arg11 == null) {
            return 0;
        }

        long v1 = 0;
        while(v0 < Math.min(200, arg11.size())) {
            Object v3 = arg11.get(v0);
            if(v3 == null) {
            }
            else {
                v1 = ((v1 * 20261 + 2147483648L + (((long)(((int)(((Document)v3).id >> 32)))))) % 2147483648L * 20261 + 2147483648L + (((long)(((int)((Document)v3).id))))) % 2147483648L;
            }

            ++v0;
        }

        return ((int)v1);
    }

    private int calcFeaturedStickersHash(ArrayList arg13) {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < arg13.size(); ++v2) {
            StickerSet v3 = arg13.get(v2).set;
            if(v3.archived) {
            }
            else {
                long v6 = 20261;
                long v8 = 2147483648L;
                v0 = ((v0 * v6 + v8 + (((long)(((int)(v3.id >> 32)))))) % v8 * v6 + v8 + (((long)(((int)v3.id))))) % v8;
                if(this.unreadStickerSets.contains(Long.valueOf(v3.id))) {
                    v0 = (v0 * v6 + v8 + 1) % v8;
                }
            }
        }

        return ((int)v0);
    }

    public void calcNewHash(int arg3) {
        DataQuery.loadHash[arg3] = DataQuery.calcStickersHash(DataQuery.stickerSets[arg3]);
    }

    private static int calcStickersHash(ArrayList arg8) {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < arg8.size(); ++v2) {
            StickerSet v3 = arg8.get(v2).set;
            if(v3.archived) {
            }
            else {
                v0 = (v0 * 20261 + 2147483648L + (((long)v3.hash))) % 2147483648L;
            }
        }

        return ((int)v0);
    }

    public static boolean canAddMessageToMedia(Message arg5) {
        boolean v0 = arg5 instanceof TL_message_secret;
        if((v0) && (((arg5.media instanceof TL_messageMediaPhoto)) || (MessageObject.isVideoMessage(arg5)) || (MessageObject.isGifMessage(arg5))) && arg5.media.ttl_seconds != 0 && arg5.media.ttl_seconds <= 60) {
            return 0;
        }

        if(!v0 && ((arg5 instanceof TL_message)) && (((arg5.media instanceof TL_messageMediaPhoto)) || ((arg5.media instanceof TL_messageMediaDocument))) && arg5.media.ttl_seconds != 0) {
            return 0;
        }

        if(!(arg5.media instanceof TL_messageMediaPhoto) && (!(arg5.media instanceof TL_messageMediaDocument) || (MessageObject.isGifDocument(arg5.media.document)))) {
            if(!arg5.entities.isEmpty()) {
                int v0_1 = 0;
                while(true) {
                    if(v0_1 < arg5.entities.size()) {
                        Object v3 = arg5.entities.get(v0_1);
                        if(!(v3 instanceof TL_messageEntityUrl) && !(v3 instanceof TL_messageEntityTextUrl)) {
                            if((v3 instanceof TL_messageEntityEmail)) {
                            }
                            else {
                                ++v0_1;
                                continue;
                            }
                        }

                        return 1;
                    }

                    return 0;
                }

                return 1;
            }

            return 0;
        }

        return 1;
    }

    public boolean canAddStickerToFavorites() {
        boolean v1 = false;
        if(!DataQuery.stickersLoaded[0] || DataQuery.stickerSets[0].size() >= 5 || !DataQuery.recentStickers[2].isEmpty()) {
            v1 = true;
        }

        return v1;
    }

    public void checkFeaturedStickers() {
        if(!this.loadingFeaturedStickers && (!this.featuredStickersLoaded || Math.abs(System.currentTimeMillis() / 1000 - (((long)this.loadFeaturedDate))) >= 3600)) {
            this.loadFeaturedStickers(true, false);
        }
    }

    private static boolean checkInclusion(int arg5, ArrayList arg6) {
        if(arg6 != null) {
            if(arg6.isEmpty()) {
            }
            else {
                int v1 = arg6.size();
                int v2;
                for(v2 = 0; v2 < v1; ++v2) {
                    Object v3 = arg6.get(v2);
                    if(((MessageEntity)v3).offset <= arg5 && ((MessageEntity)v3).offset + ((MessageEntity)v3).length > arg5) {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    private static boolean checkIntersection(int arg5, int arg6, ArrayList arg7) {
        if(arg7 != null) {
            if(arg7.isEmpty()) {
            }
            else {
                int v1 = arg7.size();
                int v2;
                for(v2 = 0; v2 < v1; ++v2) {
                    Object v3 = arg7.get(v2);
                    if(((MessageEntity)v3).offset > arg5 && ((MessageEntity)v3).offset + ((MessageEntity)v3).length <= arg6) {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    public void checkStickers(int arg6) {
        if(!DataQuery.loadingStickers[arg6] && (!DataQuery.stickersLoaded[arg6] || Math.abs(System.currentTimeMillis() / 1000 - (((long)DataQuery.loadDate[arg6]))) >= 3600)) {
            DataQuery.loadStickers(arg6, true, false);
        }
    }

    public void cleanDraft(long arg10, boolean arg12) {
        Object v0 = this.drafts.get(arg10);
        if(v0 == null) {
            return;
        }

        if(!arg12) {
            this.drafts.remove(arg10);
            this.draftMessages.remove(arg10);
            SharedPreferences$Editor v12 = this.preferences.edit();
            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("");
            v0_1.append(arg10);
            v12 = v12.remove(v0_1.toString());
            v0_1 = new StringBuilder();
            v0_1.append("r_");
            v0_1.append(arg10);
            v12.remove(v0_1.toString()).commit();
            MessagesController.getInstance(DataQuery.currentAccount).sortDialogs(null);
            NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }
        else if(((DraftMessage)v0).reply_to_msg_id != 0) {
            ((DraftMessage)v0).reply_to_msg_id = 0;
            ((DraftMessage)v0).flags &= -2;
            this.saveDraft(arg10, ((DraftMessage)v0).message, ((DraftMessage)v0).entities, null, ((DraftMessage)v0).no_webpage, true);
        }
    }

    public void cleanup() {
        int v1;
        for(v1 = 0; v1 < 3; ++v1) {
            DataQuery.recentStickers[v1].clear();
            DataQuery.loadingRecentStickers[v1] = false;
            DataQuery.recentStickersLoaded[v1] = false;
        }

        for(v1 = 0; v1 < 4; ++v1) {
            DataQuery.loadHash[v1] = 0;
            DataQuery.loadDate[v1] = 0;
            DataQuery.stickerSets[v1].clear();
            DataQuery.loadingStickers[v1] = false;
            DataQuery.stickersLoaded[v1] = false;
        }

        DataQuery.featuredStickerSets.clear();
        this.loadFeaturedDate = 0;
        DataQuery.loadFeaturedHash = 0;
        DataQuery.allStickers.clear();
        DataQuery.allStickersFeatured.clear();
        DataQuery.stickersByEmoji.clear();
        this.featuredStickerSetsById.clear();
        DataQuery.featuredStickerSets.clear();
        this.unreadStickerSets.clear();
        this.recentGifs.clear();
        DataQuery.stickerSetsById.clear();
        DataQuery.installedStickerSetsById.clear();
        DataQuery.stickerSetsByName.clear();
        this.loadingFeaturedStickers = false;
        this.featuredStickersLoaded = false;
        this.loadingRecentGifs = false;
        this.recentGifsLoaded = false;
        this.loading = false;
        this.loaded = false;
        this.hints.clear();
        this.inlineBots.clear();
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
        this.drafts.clear();
        this.draftMessages.clear();
        this.preferences.edit().clear().commit();
        this.botInfos.clear();
        this.botKeyboards.clear();
        this.botKeyboardsByMids.clear();
    }

    public void clearAllDrafts() {
        this.drafts.clear();
        this.draftMessages.clear();
        this.preferences.edit().clear().commit();
        MessagesController.getInstance(DataQuery.currentAccount).sortDialogs(null);
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public void clearBotKeyboard(long arg2, ArrayList arg4) {
        AndroidUtilities.runOnUIThread(new Runnable(arg4, arg2) {
            public void run() {
                Object v1 = null;
                int v2 = 2;
                if(this.val$messages != null) {
                    int v0;
                    for(v0 = 0; v0 < this.val$messages.size(); ++v0) {
                        long v5 = DataQuery.this.botKeyboardsByMids.get(this.val$messages.get(v0).intValue());
                        if(v5 != 0) {
                            DataQuery.this.botKeyboards.remove(v5);
                            DataQuery.this.botKeyboardsByMids.delete(this.val$messages.get(v0).intValue());
                            NotificationCenter v7 = NotificationCenter.getInstance(DataQuery.currentAccount);
                            int v8 = NotificationCenter.botKeyboardDidLoaded;
                            Object[] v9 = new Object[v2];
                            v9[0] = v1;
                            v9[1] = Long.valueOf(v5);
                            v7.postNotificationName(v8, v9);
                        }
                    }
                }
                else {
                    DataQuery.this.botKeyboards.remove(this.val$did);
                    NotificationCenter v0_1 = NotificationCenter.getInstance(DataQuery.currentAccount);
                    int v5_1 = NotificationCenter.botKeyboardDidLoaded;
                    Object[] v2_1 = new Object[v2];
                    v2_1[0] = v1;
                    v2_1[1] = Long.valueOf(this.val$did);
                    v0_1.postNotificationName(v5_1, v2_1);
                }
            }
        });
    }

    public void clearTopPeers() {
        this.hints.clear();
        this.inlineBots.clear();
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable() {
            public void run() {
                try {
                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("DELETE FROM chat_hints WHERE 1").c().e();
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        });
        this.buildShortcuts();
    }

    private Intent createIntrnalShortcutIntent(long arg5) {
        String v2_1;
        Intent v0 = new Intent(ApplicationLoader.applicationContext, OpenChatReceiver.class);
        int v1 = ((int)arg5);
        int v2 = ((int)(arg5 >> 32));
        Intent v3 = null;
        if(v1 == 0) {
            v0.putExtra("encId", v2);
            if(MessagesController.getInstance(DataQuery.currentAccount).getEncryptedChat(Integer.valueOf(v2)) == null) {
                return v3;
            }
        }
        else {
            if(v1 > 0) {
                v2_1 = "userId";
            }
            else if(v1 < 0) {
                v2_1 = "chatId";
                v1 = -v1;
            }
            else {
                return v3;
            }

            v0.putExtra(v2_1, v1);
        }

        v0.putExtra("currentAccount", DataQuery.currentAccount);
        v0.setAction("com.tmessages.openchat" + arg5);
        v0.addFlags(67108864);
        return v0;
    }

    private void deletePeer(int arg3, int arg4) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg3, arg4) {
            public void run() {
                try {
                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a(String.format(Locale.US, "DELETE FROM chat_hints WHERE did = %d AND type = %d", Integer.valueOf(this.val$did), Integer.valueOf(this.val$type))).c().e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    public void endTransaction() {
        this.inTransaction = false;
    }

    public HashMap getAllStickers() {
        return DataQuery.allStickers;
    }

    public HashMap getAllStickersFeatured() {
        return DataQuery.allStickersFeatured;
    }

    public int getArchivedStickersCount(int arg2) {
        return DataQuery.archivedStickersCount[arg2];
    }

    public DraftMessage getDraft(long arg2) {
        return this.drafts.get(arg2);
    }

    public Message getDraftMessage(long arg2) {
        return this.draftMessages.get(arg2);
    }

    public String getEmojiForSticker(long arg2) {
        String v2_1;
        Object v2 = DataQuery.stickersByEmoji.get(arg2);
        if(v2 != null) {
        }
        else {
            v2_1 = "";
        }

        return v2_1;
    }

    public ArrayList getEntities(CharSequence[] arg20) {
        TL_messageEntityItalic v7_5;
        CharSequence v8_3;
        CharSequence[] v7_2;
        int v6_1;
        ArrayList v9_2;
        int v17;
        CharSequence[] v8_2;
        int v14;
        TL_inputMessageEntityMentionName v5_5;
        TL_messageEntityItalic v5_4;
        int v11;
        TL_messageEntityCode v1_3;
        int v13;
        int v9;
        int v12;
        int v10;
        int v8_1;
        int v7_1;
        CharSequence v7;
        int v5;
        DataQuery v0 = this;
        ArrayList v1 = null;
        if(arg20 != null) {
            if(arg20[0] == null) {
            }
            else {
                int v3 = -1;
                ArrayList v6 = v1;
                int v1_1 = 0;
                int v4 = 0;
                while(true) {
                    v5 = -1;
                    while(true) {
                        v7 = arg20[0];
                        String v8 = v1_1 == 0 ? "`" : "```";
                        v4 = TextUtils.indexOf(v7, ((CharSequence)v8), v4);
                        v7_1 = 10;
                        v8_1 = 32;
                        v10 = 2;
                        if(v4 == v3) {
                            goto label_183;
                        }

                        v12 = 96;
                        if(v5 != v3) {
                            break;
                        }

                        v1_1 = arg20[0].length() - v4 <= v10 || arg20[0].charAt(v4 + 1) != v12 || arg20[0].charAt(v4 + 2) != v12 ? 0 : 1;
                        v9 = v1_1 != 0 ? 3 : 1;
                        v5 = v4;
                        v4 += v9;
                    }

                    if(v6 == null) {
                        v6 = new ArrayList();
                    }

                    v13 = v1_1 != 0 ? 3 : 1;
                    v13 += v4;
                    goto label_56;
                label_183:
                    if(v5 != v3 && v1_1 != 0) {
                        CharSequence[] v1_2 = new CharSequence[v10];
                        v1_2[0] = v0.substring(arg20[0], 0, v5);
                        v1_2[1] = v0.substring(arg20[0], v5 + 2, arg20[0].length());
                        arg20[0] = TextUtils.concat(v1_2);
                        if(v6 == null) {
                            v6 = new ArrayList();
                        }

                        v1_3 = new TL_messageEntityCode();
                        v1_3.offset = v5;
                        v1_3.length = 1;
                        v6.add(v1_3);
                    }

                    if((arg20[0] instanceof Spanned)) {
                        CharSequence v1_4 = arg20[0];
                        Object[] v3_1 = ((Spanned)v1_4).getSpans(0, arg20[0].length(), TypefaceSpan.class);
                        if(v3_1 != null && v3_1.length > 0) {
                            for(v4 = 0; v4 < v3_1.length; ++v4) {
                                Object v5_1 = v3_1[v4];
                                v9 = ((Spanned)v1_4).getSpanStart(v5_1);
                                v11 = ((Spanned)v1_4).getSpanEnd(v5_1);
                                if(!DataQuery.checkInclusion(v9, v6) && !DataQuery.checkInclusion(v11, v6)) {
                                    if(DataQuery.checkIntersection(v9, v11, v6)) {
                                    }
                                    else {
                                        if(v6 == null) {
                                            v6 = new ArrayList();
                                        }

                                        if(((TypefaceSpan)v5_1).isMono()) {
                                            TL_messageEntityCode v5_2 = new TL_messageEntityCode();
                                        }
                                        else if(((TypefaceSpan)v5_1).isBold()) {
                                            TL_messageEntityBold v5_3 = new TL_messageEntityBold();
                                        }
                                        else {
                                            v5_4 = new TL_messageEntityItalic();
                                        }

                                        ((MessageEntity)v5_4).offset = v9;
                                        ((MessageEntity)v5_4).length = v11 - v9;
                                        v6.add(v5_4);
                                    }
                                }
                            }
                        }

                        v3_1 = ((Spanned)v1_4).getSpans(0, arg20[0].length(), URLSpanUserMention.class);
                        if(v3_1 != null && v3_1.length > 0) {
                            if(v6 == null) {
                                v6 = new ArrayList();
                            }

                            for(v4 = 0; v4 < v3_1.length; ++v4) {
                                v5_5 = new TL_inputMessageEntityMentionName();
                                v5_5.user_id = MessagesController.getInstance(DataQuery.currentAccount).getInputUser(Utilities.parseInt(v3_1[v4].getURL()).intValue());
                                if(v5_5.user_id != null) {
                                    v5_5.offset = ((Spanned)v1_4).getSpanStart(v3_1[v4]);
                                    v5_5.length = Math.min(((Spanned)v1_4).getSpanEnd(v3_1[v4]), arg20[0].length()) - v5_5.offset;
                                    if(arg20[0].charAt(v5_5.offset + v5_5.length - 1) == v8_1) {
                                        --v5_5.length;
                                    }

                                    v6.add(v5_5);
                                }
                            }
                        }

                        v3_1 = ((Spanned)v1_4).getSpans(0, arg20[0].length(), URLSpanReplacement.class);
                        if(v3_1 == null) {
                            goto label_334;
                        }

                        if(v3_1.length <= 0) {
                            goto label_334;
                        }

                        if(v6 == null) {
                            v6 = new ArrayList();
                        }

                        for(v4 = 0; v4 < v3_1.length; ++v4) {
                            TL_messageEntityTextUrl v5_6 = new TL_messageEntityTextUrl();
                            v5_6.offset = ((Spanned)v1_4).getSpanStart(v3_1[v4]);
                            ((TL_messageEntityTextUrl)v5_5).length = Math.min(((Spanned)v1_4).getSpanEnd(v3_1[v4]), arg20[0].length()) - ((TL_messageEntityTextUrl)v5_5).offset;
                            v5_6.url = v3_1[v4].getURL();
                            v6.add(v5_6);
                        }
                    }

                label_334:
                    v1_1 = 0;
                    break;
                label_56:
                    while(v13 < arg20[0].length()) {
                        if(arg20[0].charAt(v13) != v12) {
                            break;
                        }

                        ++v4;
                        ++v13;
                    }

                    v12 = v1_1 != 0 ? 3 : 1;
                    v12 += v4;
                    if(v1_1 != 0) {
                        v1_1 = v5 > 0 ? arg20[0].charAt(v5 - 1) : 0;
                        v1_1 = v1_1 == v8_1 || v1_1 == v7_1 ? 1 : 0;
                        CharSequence v13_1 = v0.substring(arg20[0], 0, v5 - v1_1);
                        CharSequence v3_2 = v0.substring(arg20[0], v5 + 3, v4);
                        v14 = v4 + 3;
                        v9 = v14 < arg20[0].length() ? arg20[0].charAt(v14) : 0;
                        CharSequence v11_1 = arg20[0];
                        v7_1 = v9 == v8_1 || v9 == v7_1 ? 1 : 0;
                        v7 = v0.substring(v11_1, v14 + v7_1, arg20[0].length());
                        if(v13_1.length() != 0) {
                            v8_2 = new CharSequence[v10];
                            v8_2[0] = v13_1;
                            v17 = 1;
                            v8_2[1] = "\n";
                            v13_1 = TextUtils.concat(v8_2);
                        }
                        else {
                            v17 = 1;
                            v1_1 = 1;
                        }

                        if(v7.length() != 0) {
                            v8_2 = new CharSequence[v10];
                            v8_2[0] = "\n";
                            v8_2[v17] = v7;
                            v7 = TextUtils.concat(v8_2);
                        }

                        if(TextUtils.isEmpty(v3_2)) {
                            goto label_179;
                        }

                        CharSequence[] v9_1 = new CharSequence[3];
                        v9_1[0] = v13_1;
                        v9_1[v17] = v3_2;
                        v9_1[v10] = v7;
                        arg20[0] = TextUtils.concat(v9_1);
                        TL_messageEntityPre v3_3 = new TL_messageEntityPre();
                        v3_3.offset = (v1_1 ^ 1) + v5;
                        v3_3.length = v4 - v5 - 3 + (v1_1 ^ 1);
                        v3_3.language = "";
                        v6.add(v3_3);
                        v12 += -6;
                    }
                    else {
                        v1_1 = v5 + 1;
                        if(v1_1 == v4) {
                            goto label_179;
                        }

                        CharSequence[] v3_4 = new CharSequence[3];
                        v3_4[0] = v0.substring(arg20[0], 0, v5);
                        v3_4[1] = v0.substring(arg20[0], v1_1, v4);
                        v3_4[v10] = v0.substring(arg20[0], v4 + 1, arg20[0].length());
                        arg20[0] = TextUtils.concat(v3_4);
                        v1_3 = new TL_messageEntityCode();
                        v1_3.offset = v5;
                        v1_3.length = v4 - v5 - 1;
                        v6.add(v1_3);
                        v12 += -2;
                    }

                label_179:
                    v4 = v12;
                    v1_1 = 0;
                    v3 = -1;
                }

                while(true) {
                    if(v1_1 >= v10) {
                        return v6;
                    }

                    String v3_5 = v1_1 == 0 ? "**" : "__";
                    v4 = v1_1 == 0 ? 42 : 95;
                    v9_2 = v6;
                    v5 = 0;
                    v6_1 = -1;
                    while(true) {
                    label_347:
                        v5 = TextUtils.indexOf(arg20[0], ((CharSequence)v3_5), v5);
                        v11 = -1;
                        if(v5 == v11) {
                            goto label_450;
                        }

                        if(v6_1 != v11) {
                            break;
                        }

                        v12 = v5 == 0 ? 32 : arg20[0].charAt(v5 - 1);
                        if(!DataQuery.checkInclusion(v5, v9_2) && (v12 == v8_1 || v12 == v7_1)) {
                            v6_1 = v5;
                        }

                        v5 += 2;
                    }

                    v12 = v5 + 2;
                    break;
                label_450:
                    ++v1_1;
                    v6 = v9_2;
                    v7_1 = 10;
                    v8_1 = 32;
                }

                while(v12 < arg20[0].length()) {
                    if(arg20[0].charAt(v12) != v4) {
                        break;
                    }

                    ++v5;
                    ++v12;
                }

                v12 = v5 + 2;
                if(!DataQuery.checkInclusion(v5, v9_2) && !DataQuery.checkIntersection(v6_1, v5, v9_2)) {
                    v13 = v6_1 + 2;
                    if(v13 != v5) {
                        if(v9_2 == null) {
                            v9_2 = new ArrayList();
                        }

                        v14 = 3;
                        try {
                            v7_2 = new CharSequence[v14];
                            v7_2[0] = v0.substring(arg20[0], 0, v6_1);
                            v8_3 = v0.substring(arg20[0], v13, v5);
                        }
                        catch(Exception ) {
                            goto label_404;
                        }

                        try {
                            v7_2[1] = v8_3;
                            v7_2[v10] = v0.substring(arg20[0], v12, arg20[0].length());
                            arg20[0] = TextUtils.concat(v7_2);
                        }
                        catch(Exception ) {
                        label_404:
                            arg20[0] = v0.substring(arg20[0], 0, v6_1).toString() + v0.substring(arg20[0], v13, v5).toString() + v0.substring(arg20[0], v12, arg20[0].length()).toString();
                        }

                        if(v1_1 == 0) {
                            TL_messageEntityBold v7_4 = new TL_messageEntityBold();
                        }
                        else {
                            v7_5 = new TL_messageEntityItalic();
                        }

                        ((MessageEntity)v7_5).offset = v6_1;
                        ((MessageEntity)v7_5).length = v5 - v6_1 - v10;
                        DataQuery.removeOffsetAfter(((MessageEntity)v7_5).offset + ((MessageEntity)v7_5).length, 4, v9_2);
                        v9_2.add(v7_5);
                        v12 += -4;
                    }
                }

                v5 = v12;
                v6_1 = -1;
                v7_1 = 10;
                v8_1 = 32;
                goto label_347;
                return v6;
            }
        }

        return v1;
    }

    public ArrayList getFeaturedStickerSets() {
        return DataQuery.featuredStickerSets;
    }

    public int getFeaturesStickersHashWithoutUnread() {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < DataQuery.featuredStickerSets.size(); ++v2) {
            StickerSet v3 = DataQuery.featuredStickerSets.get(v2).set;
            if(v3.archived) {
            }
            else {
                v0 = ((v0 * 20261 + 2147483648L + (((long)(((int)(v3.id >> 32)))))) % 2147483648L * 20261 + 2147483648L + (((long)(((int)v3.id))))) % 2147483648L;
            }
        }

        return ((int)v0);
    }

    public TL_messages_stickerSet getGroupStickerSetById(StickerSet arg4) {
        boolean v1;
        Object v0 = DataQuery.stickerSetsById.get(arg4.id);
        if(v0 == null) {
            v0 = DataQuery.groupStickerSets.get(arg4.id);
            if(v0 == null || ((TL_messages_stickerSet)v0).set == null) {
                v1 = true;
            }
            else if(((TL_messages_stickerSet)v0).set.hash != arg4.hash) {
                v1 = false;
            }
            else {
                goto label_19;
            }

            this.loadGroupStickerSet(arg4, v1);
        }

    label_19:
        return ((TL_messages_stickerSet)v0);
    }

    public static DataQuery getInstance(int arg3) {
        DataQuery v0 = DataQuery.Instance[arg3];
        if(v0 == null) {
            Class v1 = DataQuery.class;
            __monitor_enter(v1);
            try {
                v0 = DataQuery.Instance[arg3];
                if(v0 == null) {
                    DataQuery[] v0_1 = DataQuery.Instance;
                    DataQuery v2 = new DataQuery(arg3);
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

    public String getLastSearchQuery() {
        return this.lastSearchQuery;
    }

    private int getMask() {
        int v2 = 1;
        if(this.lastReturnedNum >= this.searchResultMessages.size() - 1 && (this.messagesSearchEndReached[0])) {
            if(!this.messagesSearchEndReached[1]) {
            }
            else {
                v2 = 0;
            }
        }

        if(this.lastReturnedNum > 0) {
            v2 |= 2;
        }

        return v2;
    }

    public void getMediaCount(long arg9, int arg11, int arg12, boolean arg13) {
        int v0 = ((int)arg9);
        if((arg13) || v0 == 0) {
            this.getMediaCountDatabase(arg9, arg11, arg12);
        }
        else {
            TL_messages_search v13 = new TL_messages_search();
            v13.limit = 1;
            v13.offset_id = 0;
            if(arg11 == 0) {
                TL_inputMessagesFilterPhotoVideo v1 = new TL_inputMessagesFilterPhotoVideo();
                goto label_13;
            }
            else if(arg11 == 1) {
                TL_inputMessagesFilterDocument v1_1 = new TL_inputMessagesFilterDocument();
                goto label_13;
            }
            else if(arg11 == 2) {
                TL_inputMessagesFilterRoundVoice v1_2 = new TL_inputMessagesFilterRoundVoice();
                goto label_13;
            }
            else if(arg11 == 3) {
                TL_inputMessagesFilterUrl v1_3 = new TL_inputMessagesFilterUrl();
                goto label_13;
            }
            else if(arg11 == 4) {
                TL_inputMessagesFilterMusic v1_4 = new TL_inputMessagesFilterMusic();
            label_13:
                v13.filter = ((MessagesFilter)v1_4);
            }

            v13.q = "";
            v13.peer = MessagesController.getInstance(DataQuery.currentAccount).getInputPeer(v0);
            if(v13.peer == null) {
                return;
            }

            ConnectionsManager.getInstance(DataQuery.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v13), new RequestDelegate(arg9, arg11, arg12) {
                public void run(TLObject arg8, TL_error arg9) {
                    if(arg9 == null) {
                        MessagesStorage.getInstance(DataQuery.currentAccount).putUsersAndChats(((messages_Messages)arg8).users, ((messages_Messages)arg8).chats, true, true);
                        int v9 = (arg8 instanceof TL_messages_messages) ? ((messages_Messages)arg8).messages.size() : ((messages_Messages)arg8).count;
                        int v1 = v9;
                        AndroidUtilities.runOnUIThread(new Runnable(((messages_Messages)arg8)) {
                            public void run() {
                                MessagesController.getInstance(DataQuery.currentAccount).putUsers(this.val$res.users, false);
                                MessagesController.getInstance(DataQuery.currentAccount).putChats(this.val$res.chats, false);
                            }
                        });
                        DataQuery.this.processLoadedMediaCount(v1, this.val$uid, this.val$type, this.val$classGuid, false);
                    }
                }
            }), arg12);
        }
    }

    private void getMediaCountDatabase(long arg9, int arg11, int arg12) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg9, arg11, arg12) {
            public void run() {
                try {
                    SQLiteDatabase v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                    Locale v1 = Locale.US;
                    int v3 = 2;
                    SQLiteCursor v0_2 = v0_1.b(String.format(v1, "SELECT count FROM media_counts_v2 WHERE uid = %d AND type = %d LIMIT 1", Long.valueOf(this.val$uid), Integer.valueOf(this.val$type)), new Object[0]);
                    int v2 = -1;
                    int v1_1 = v0_2.a() ? v0_2.b(0) : -1;
                    v0_2.b();
                    int v0_3 = ((int)this.val$uid);
                    if(v1_1 == v2 && v0_3 == 0) {
                        v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        Locale v4_1 = Locale.US;
                        v0_2 = v0_1.b(String.format(v4_1, "SELECT COUNT(mid) FROM media_v2 WHERE uid = %d AND type = %d LIMIT 1", Long.valueOf(this.val$uid), Integer.valueOf(this.val$type)), new Object[0]);
                        if(v0_2.a()) {
                            v1_1 = v0_2.b(0);
                        }

                        v0_2.b();
                        if(v1_1 == v2) {
                            goto label_53;
                        }

                        DataQuery.this.putMediaCountDatabase(this.val$uid, this.val$type, v1_1);
                    }

                label_53:
                    DataQuery.this.processLoadedMediaCount(v1_1, this.val$uid, this.val$type, this.val$classGuid, true);
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    public static int getMediaType(Message arg4) {
        int v0 = -1;
        if(arg4 == null) {
            return v0;
        }

        int v2 = 0;
        if((arg4.media instanceof TL_messageMediaPhoto)) {
            return 0;
        }

        if((arg4.media instanceof TL_messageMediaDocument)) {
            if(!MessageObject.isVoiceMessage(arg4)) {
                if(MessageObject.isRoundVideoMessage(arg4)) {
                }
                else if(MessageObject.isVideoMessage(arg4)) {
                    return 0;
                }
                else if(MessageObject.isStickerMessage(arg4)) {
                    return v0;
                }
                else if(MessageObject.isMusicMessage(arg4)) {
                    return 4;
                }
                else {
                    return 1;
                }
            }

            return 2;
        }

        if(!arg4.entities.isEmpty()) {
            while(true) {
                if(v2 < arg4.entities.size()) {
                    Object v1 = arg4.entities.get(v2);
                    if(!(v1 instanceof TL_messageEntityUrl) && !(v1 instanceof TL_messageEntityTextUrl)) {
                        if((v1 instanceof TL_messageEntityEmail)) {
                        }
                        else {
                            ++v2;
                            continue;
                        }
                    }

                    return 3;
                }

                return v0;
            }

            return 3;
        }

        return v0;
    }

    public ArrayList getRecentGifs() {
        return new ArrayList(this.recentGifs);
    }

    public ArrayList getRecentStickers(int arg4) {
        return new ArrayList(DataQuery.recentStickers[arg4].subList(0, Math.min(DataQuery.recentStickers[arg4].size(), 20)));
    }

    public ArrayList getRecentStickersNoCopy(int arg2) {
        return DataQuery.recentStickers[arg2];
    }

    public TL_messages_stickerSet getStickerSetById(long arg2) {
        return DataQuery.stickerSetsById.get(arg2);
    }

    public TL_messages_stickerSet getStickerSetByName(String arg2) {
        return DataQuery.stickerSetsByName.get(arg2);
    }

    public static long getStickerSetId(Document arg3) {
        int v0 = 0;
        while(v0 < arg3.attributes.size()) {
            Object v1 = arg3.attributes.get(v0);
            if(!(v1 instanceof TL_documentAttributeSticker)) {
                ++v0;
                continue;
            }
            else if((((DocumentAttribute)v1).stickerset instanceof TL_inputStickerSetID)) {
                return ((DocumentAttribute)v1).stickerset.id;
            }

            return -1;
        }

        return -1;
    }

    public String getStickerSetName(long arg2) {
        StickerSet v2;
        Object v0 = DataQuery.stickerSetsById.get(arg2);
        if(v0 != null) {
            v2 = ((TL_messages_stickerSet)v0).set;
        }
        else {
            Object v2_1 = this.featuredStickerSetsById.get(arg2);
            if(v2_1 != null) {
                v2 = ((StickerSetCovered)v2_1).set;
            }
            else {
                return null;
            }
        }

        return v2.short_name;
    }

    public static ArrayList getStickerSets(int arg1) {
        if(arg1 == 3) {
            return DataQuery.stickerSets[2];
        }

        return DataQuery.stickerSets[arg1];
    }

    public ArrayList getUnreadStickerSets() {
        return this.unreadStickerSets;
    }

    public void increaseInlineRaiting(int arg9) {
        if(!UserConfig.getInstance(DataQuery.currentAccount).suggestContacts) {
            return;
        }

        int v0 = UserConfig.getInstance(DataQuery.currentAccount).botRatingLoadTime != 0 ? Math.max(1, (((int)(System.currentTimeMillis() / 1000))) - UserConfig.getInstance(DataQuery.currentAccount).botRatingLoadTime) : 60;
        Object v2 = null;
        int v4 = 0;
        while(v4 < this.inlineBots.size()) {
            Object v5 = this.inlineBots.get(v4);
            if(((TL_topPeer)v5).peer.user_id == arg9) {
                v2 = v5;
            }
            else {
                ++v4;
                continue;
            }

            break;
        }

        if(v2 == null) {
            TL_topPeer v2_1 = new TL_topPeer();
            v2_1.peer = new TL_peerUser();
            v2_1.peer.user_id = arg9;
            this.inlineBots.add(v2_1);
        }

        ((TL_topPeer)v2).rating += Math.exp(((double)(v0 / MessagesController.getInstance(DataQuery.currentAccount).ratingDecay)));
        Collections.sort(this.inlineBots, new Comparator() {
            public int compare(Object arg1, Object arg2) {
                return this.compare(((TL_topPeer)arg1), ((TL_topPeer)arg2));
            }

            public int compare(TL_topPeer arg6, TL_topPeer arg7) {
                if(arg6.rating > arg7.rating) {
                    return -1;
                }

                if(arg6.rating < arg7.rating) {
                    return 1;
                }

                return 0;
            }
        });
        if(this.inlineBots.size() > 20) {
            this.inlineBots.remove(this.inlineBots.size() - 1);
        }

        this.savePeer(arg9, 1, ((TL_topPeer)v2).rating);
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
    }

    public void increasePeerRaiting(long arg4) {
        if(!UserConfig.getInstance(DataQuery.currentAccount).suggestContacts) {
            return;
        }

        int v0 = ((int)arg4);
        if(v0 <= 0) {
            return;
        }

        User v1 = v0 > 0 ? MessagesController.getInstance(DataQuery.currentAccount).getUser(Integer.valueOf(v0)) : null;
        if(v1 != null) {
            if(v1.bot) {
            }
            else {
                MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg4, v0) {
                    public void run() {
                        int v3_1;
                        double v0 = 0;
                        try {
                            SQLiteDatabase v2_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                            Locale v3 = Locale.US;
                            Object[] v6 = new Object[1];
                            int v8 = 0;
                            v6[0] = Long.valueOf(this.val$did);
                            SQLiteCursor v2_2 = v2_1.b(String.format(v3, "SELECT MAX(mid), MAX(date) FROM messages WHERE uid = %d AND out = 1", v6), new Object[0]);
                            if(v2_2.a()) {
                                v8 = v2_2.b(0);
                                v3_1 = v2_2.b(1);
                            }
                            else {
                                v3_1 = 0;
                            }

                            v2_2.b();
                            if(v8 <= 0) {
                                goto label_35;
                            }

                            if(UserConfig.getInstance(DataQuery.currentAccount).ratingLoadTime == 0) {
                                goto label_35;
                            }

                            v0 = ((double)(v3_1 - UserConfig.getInstance(DataQuery.currentAccount).ratingLoadTime));
                        }
                        catch(Exception v2) {
                            FileLog.e(((Throwable)v2));
                        }

                    label_35:
                        AndroidUtilities.runOnUIThread(new Runnable(v0) {
                            public void run() {
                                TL_topPeer v2_1;
                                Object v2;
                                int v1;
                                for(v1 = 0; v1 < this.this$1.this$0.hints.size(); ++v1) {
                                    v2 = this.this$1.this$0.hints.get(v1);
                                    if(this.this$1.val$lower_id < 0) {
                                        if(((TL_topPeer)v2).peer.chat_id == -this.this$1.val$lower_id) {
                                        }
                                        else if(((TL_topPeer)v2).peer.channel_id != -this.this$1.val$lower_id) {
                                            goto label_26;
                                        }

                                        goto label_38;
                                    }

                                label_26:
                                    if(this.this$1.val$lower_id > 0 && ((TL_topPeer)v2).peer.user_id == this.this$1.val$lower_id) {
                                        goto label_38;
                                    }
                                }

                                v2 = null;
                            label_38:
                                if(v2 == null) {
                                    v2_1 = new TL_topPeer();
                                    if(this.this$1.val$lower_id > 0) {
                                        v2_1.peer = new TL_peerUser();
                                        v2_1.peer.user_id = this.this$1.val$lower_id;
                                    }
                                    else {
                                        v2_1.peer = new TL_peerChat();
                                        v2_1.peer.chat_id = -this.this$1.val$lower_id;
                                    }

                                    this.this$1.this$0.hints.add(v2_1);
                                }

                                double v3 = v2_1.rating;
                                double v5 = this.val$dtFinal;
                                double v7 = ((double)MessagesController.getInstance(DataQuery.currentAccount).ratingDecay);
                                Double.isNaN(v7);
                                v2_1.rating = v3 + Math.exp(v5 / v7);
                                Collections.sort(this.this$1.this$0.hints, new Comparator() {
                                    public int compare(Object arg1, Object arg2) {
                                        return this.compare(((TL_topPeer)arg1), ((TL_topPeer)arg2));
                                    }

                                    public int compare(TL_topPeer arg6, TL_topPeer arg7) {
                                        if(arg6.rating > arg7.rating) {
                                            return -1;
                                        }

                                        if(arg6.rating < arg7.rating) {
                                            return 1;
                                        }

                                        return 0;
                                    }
                                });
                                this.this$1.this$0.savePeer(((int)this.this$1.val$did), 0, v2_1.rating);
                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
                            }
                        });
                    }
                });
            }
        }
    }

    public void installShortcut(long arg17) {
        Intent$ShortcutIconResource v2;
        String v1_2;
        Icon v1_1;
        Canvas v13;
        Bitmap v12;
        int v11_1;
        Bitmap v9_1;
        String v8_1;
        int v8;
        String v10;
        FileLocation v9;
        Chat v6;
        User v4_2;
        Integer v0_3;
        MessagesController v4_1;
        Bitmap v5;
        Intent v3;
        long v1 = arg17;
        try {
            v3 = this.createIntrnalShortcutIntent(arg17);
            int v0_1 = ((int)v1);
            int v4 = ((int)(v1 >> 32));
            v5 = null;
            if(v0_1 == 0) {
                EncryptedChat v0_2 = MessagesController.getInstance(DataQuery.currentAccount).getEncryptedChat(Integer.valueOf(v4));
                if(v0_2 == null) {
                    return;
                }
                else {
                    v4_1 = MessagesController.getInstance(DataQuery.currentAccount);
                    v0_3 = Integer.valueOf(v0_2.user_id);
                    goto label_23;
                }
            }
            else if(v0_1 > 0) {
                v4_1 = MessagesController.getInstance(DataQuery.currentAccount);
                v0_3 = Integer.valueOf(v0_1);
            label_23:
                v4_2 = v4_1.getUser(v0_3);
                v6 = ((Chat)v5);
            }
            else if(v0_1 < 0) {
                v6 = MessagesController.getInstance(DataQuery.currentAccount).getChat(Integer.valueOf(-v0_1));
                v4_2 = ((User)v5);
            }
            else {
                return;
            }

            if(v4_2 == null && v6 == null) {
                return;
            }

            if(v4_2 == null) {
                v8_1 = v6.title;
                if(v6.photo != null) {
                    v9 = v6.photo.photo_small;
                    goto label_65;
                }
                else {
                    goto label_64;
                }
            }
            else if(UserObject.isUserSelf(v4_2)) {
                v9 = ((FileLocation)v5);
                v10 = LocaleController.getString("SavedMessages", 2131625950);
                v8 = 1;
            }
            else {
                v8_1 = ContactsController.formatName(v4_2.first_name, v4_2.last_name);
                if(v4_2.photo != null) {
                    v9 = v4_2.photo.photo_small;
                    goto label_65;
                }
                else {
                    goto label_64;
                }
            }

            goto label_67;
        }
        catch(Exception v0) {
            goto label_245;
        }

    label_64:
        v9 = ((FileLocation)v5);
    label_65:
        v10 = v8_1;
        v8 = 0;
    label_67:
        if(v8 == 0) {
            if(v9 != null) {
            }
            else {
                v9_1 = v5;
                goto label_153;
            }
        }

        if(v8 == 0) {
            try {
                v9_1 = BitmapFactory.decodeFile(FileLoader.getPathToAttach(((TLObject)v9), true).toString());
                goto label_81;
            }
            catch(Exception v0) {
            }
            catch(Throwable v0_4) {
                v9_1 = v5;
                goto label_152;
            label_80:
                v9_1 = v5;
            label_81:
                if(v8 == 0 && v9_1 == null) {
                    goto label_153;
                }

                float v11 = 58f;
                try {
                    v11_1 = AndroidUtilities.dp(v11);
                    v12 = Bitmap.createBitmap(v11_1, v11_1, Bitmap$Config.ARGB_8888);
                    v12.eraseColor(0);
                    v13 = new Canvas(v12);
                    if(v8 != 0) {
                        AvatarDrawable v8_2 = new AvatarDrawable(v4_2);
                        v8_2.setSavedMessages(1);
                        v8_2.setBounds(0, 0, v11_1, v11_1);
                        v8_2.draw(v13);
                    }
                    else {
                        BitmapShader v8_3 = new BitmapShader(v9_1, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
                        if(DataQuery.roundPaint == null) {
                            DataQuery.roundPaint = new Paint(1);
                            DataQuery.bitmapRect = new RectF();
                        }

                        float v0_5 = (((float)v11_1)) / (((float)v9_1.getWidth()));
                        v13.save();
                        v13.scale(v0_5, v0_5);
                        DataQuery.roundPaint.setShader(((Shader)v8_3));
                        DataQuery.bitmapRect.set(0f, 0f, ((float)v9_1.getWidth()), ((float)v9_1.getHeight()));
                        v13.drawRoundRect(DataQuery.bitmapRect, ((float)v9_1.getWidth()), ((float)v9_1.getHeight()), DataQuery.roundPaint);
                        v13.restore();
                    }

                    Drawable v0_6 = ApplicationLoader.applicationContext.getResources().getDrawable(2131230937);
                    v8 = AndroidUtilities.dp(15f);
                    v11_1 -= v8;
                    int v15 = v11_1 - AndroidUtilities.dp(2f);
                    v11_1 -= AndroidUtilities.dp(2f);
                    v0_6.setBounds(v15, v11_1, v15 + v8, v8 + v11_1);
                    v0_6.draw(v13);
                }
                catch(Throwable v0_4) {
                    goto label_152;
                }
                catch(Exception v0) {
                    goto label_245;
                }

                try {
                    v13.setBitmap(v5);
                    goto label_149;
                }
                catch(Exception ) {
                label_149:
                    v9_1 = v12;
                }
                catch(Throwable v0_4) {
                    try {
                    label_152:
                        FileLog.e(v0_4);
                    }
                    catch(Exception v0) {
                        goto label_245;
                    }
                }

                try {
                label_153:
                    v11_1 = 2131230935;
                    int v12_1 = 2131230936;
                    int v13_1 = 2131230938;
                    int v14 = 2131230934;
                    if(Build$VERSION.SDK_INT >= 26) {
                        Context v7 = ApplicationLoader.applicationContext;
                        StringBuilder v8_4 = new StringBuilder();
                        v8_4.append("sdid_");
                        v8_4.append(v1);
                        ShortcutInfo$Builder v0_7 = new ShortcutInfo$Builder(v7, v8_4.toString()).setShortLabel(((CharSequence)v10)).setIntent(v3);
                        if(v9_1 != null) {
                            v1_1 = Icon.createWithBitmap(v9_1);
                            goto label_173;
                        }
                        else if(v4_2 != null) {
                            v1_1 = v4_2.bot ? Icon.createWithResource(ApplicationLoader.applicationContext, v14) : Icon.createWithResource(ApplicationLoader.applicationContext, v13_1);
                            goto label_173;
                        }
                        else if(v6 != null) {
                            v1_1 = (ChatObject.isChannel(v6)) && !v6.megagroup ? Icon.createWithResource(ApplicationLoader.applicationContext, v11_1) : Icon.createWithResource(ApplicationLoader.applicationContext, v12_1);
                        label_173:
                            v0_7.setIcon(v1_1);
                        }

                        ApplicationLoader.applicationContext.getSystemService(ShortcutManager.class).requestPinShortcut(v0_7.build(), ((IntentSender)v5));
                        return;
                    }

                    Intent v0_8 = new Intent();
                    if(v9_1 != null) {
                        v0_8.putExtra("android.intent.extra.shortcut.ICON", ((Parcelable)v9_1));
                    }
                    else {
                        if(v4_2 != null) {
                            if(v4_2.bot) {
                                v1_2 = "android.intent.extra.shortcut.ICON_RESOURCE";
                                v2 = Intent$ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, v14);
                            }
                            else {
                                v1_2 = "android.intent.extra.shortcut.ICON_RESOURCE";
                                v2 = Intent$ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, v13_1);
                            }
                        }
                        else if(v6 != null) {
                            if((ChatObject.isChannel(v6)) && !v6.megagroup) {
                                v1_2 = "android.intent.extra.shortcut.ICON_RESOURCE";
                                v2 = Intent$ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, v11_1);
                                goto label_213;
                            }

                            v1_2 = "android.intent.extra.shortcut.ICON_RESOURCE";
                            v2 = Intent$ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, v12_1);
                        }
                        else {
                            goto label_232;
                        }

                    label_213:
                        v0_8.putExtra(v1_2, ((Parcelable)v2));
                    }

                label_232:
                    v0_8.putExtra("android.intent.extra.shortcut.INTENT", ((Parcelable)v3));
                    v0_8.putExtra("android.intent.extra.shortcut.NAME", v10);
                    v0_8.putExtra("duplicate", false);
                    v0_8.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
                    ApplicationLoader.applicationContext.sendBroadcast(v0_8);
                }
                catch(Exception v0) {
                label_245:
                    FileLog.e(((Throwable)v0));
                }

                return;
            }
        }
        else {
            goto label_80;
        }

        goto label_81;
    }

    public boolean isLoadingStickers(int arg2) {
        return DataQuery.loadingStickers[arg2];
    }

    public boolean isMessageFound(int arg2, boolean arg3) {
        boolean v2 = this.searchResultMessagesMap[((int)arg3)].indexOfKey(arg2) >= 0 ? true : false;
        return v2;
    }

    public boolean isStickerInFavorites(Document arg9) {
        int v1;
        for(v1 = 0; true; ++v1) {
            int v3 = 2;
            if(v1 >= DataQuery.recentStickers[v3].size()) {
                return 0;
            }

            Object v2 = DataQuery.recentStickers[v3].get(v1);
            if(((Document)v2).id == arg9.id && ((Document)v2).dc_id == arg9.dc_id) {
                return 1;
            }
        }

        return 0;
    }

    public boolean isStickerPackInstalled(long arg2) {
        boolean v2 = DataQuery.installedStickerSetsById.indexOfKey(arg2) >= 0 ? true : false;
        return v2;
    }

    public boolean isStickerPackInstalled(String arg2) {
        return DataQuery.stickerSetsByName.containsKey(arg2);
    }

    public boolean isStickerPackUnread(long arg2) {
        return this.unreadStickerSets.contains(Long.valueOf(arg2));
    }

    public static void loadArchivedStickersCount(int arg4, boolean arg5) {
        boolean v0 = true;
        if(arg5) {
            SharedPreferences v5 = MessagesController.getNotificationsSettings(DataQuery.currentAccount);
            StringBuilder v2 = new StringBuilder();
            v2.append("archivedStickersCount");
            v2.append(arg4);
            int v5_1 = v5.getInt(v2.toString(), -1);
            if(v5_1 == -1) {
                DataQuery.loadArchivedStickersCount(arg4, false);
            }
            else {
                DataQuery.archivedStickersCount[arg4] = v5_1;
                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.archivedStickersCountDidLoaded, new Object[]{Integer.valueOf(arg4)});
            }
        }
        else {
            TL_messages_getArchivedStickers v5_2 = new TL_messages_getArchivedStickers();
            v5_2.limit = 0;
            if(arg4 == 1) {
            }
            else {
                v0 = false;
            }

            v5_2.masks = v0;
            ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v5_2), new RequestDelegate(arg4) {
                public void run(TLObject arg2, TL_error arg3) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg3, arg2) {
                        public void run() {
                            if(this.val$error == null) {
                                TLObject v0 = this.val$response;
                                DataQuery.archivedStickersCount[org.telegram.messenger.DataQuery$22.this.val$type] = ((TL_messages_archivedStickers)v0).count;
                                SharedPreferences$Editor v1 = MessagesController.getNotificationsSettings(DataQuery.currentAccount).edit();
                                StringBuilder v2 = new StringBuilder();
                                v2.append("archivedStickersCount");
                                v2.append(org.telegram.messenger.DataQuery$22.this.val$type);
                                v1.putInt(v2.toString(), ((TL_messages_archivedStickers)v0).count).commit();
                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.archivedStickersCountDidLoaded, new Object[]{Integer.valueOf(org.telegram.messenger.DataQuery$22.this.val$type)});
                            }
                        }
                    });
                }
            });
        }
    }

    public void loadBotInfo(int arg4, boolean arg5, int arg6) {
        if(arg5) {
            Object v5 = this.botInfos.get(arg4);
            if(v5 != null) {
                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.botInfoDidLoaded, new Object[]{v5, Integer.valueOf(arg6)});
                return;
            }
        }

        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg4, arg6) {
            public void run() {
                BotInfo v0 = null;
                try {
                    SQLiteCursor v1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT info FROM bot_info WHERE uid = %d", Integer.valueOf(this.val$uid)), new Object[0]);
                    if((v1.a()) && !v1.a(0)) {
                        NativeByteBuffer v2 = v1.g(0);
                        if(v2 != null) {
                            v0 = BotInfo.TLdeserialize(((AbstractSerializedData)v2), v2.readInt32(false), false);
                            v2.reuse();
                        }
                    }

                    v1.b();
                    if(v0 == null) {
                        return;
                    }

                    AndroidUtilities.runOnUIThread(new Runnable(v0) {
                        public void run() {
                            NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.botInfoDidLoaded, new Object[]{this.val$botInfoFinal, Integer.valueOf(this.this$1.val$classGuid)});
                        }
                    });
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }
        });
    }

    public void loadBotKeyboard(long arg6) {
        Object v0 = this.botKeyboards.get(arg6);
        if(v0 != null) {
            NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.botKeyboardDidLoaded, new Object[]{v0, Long.valueOf(arg6)});
            return;
        }

        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg6) {
            public void run() {
                Message v0 = null;
                try {
                    SQLiteCursor v1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT info FROM bot_keyboard WHERE uid = %d", Long.valueOf(this.val$did)), new Object[0]);
                    if((v1.a()) && !v1.a(0)) {
                        NativeByteBuffer v2 = v1.g(0);
                        if(v2 != null) {
                            v0 = Message.TLdeserialize(((AbstractSerializedData)v2), v2.readInt32(false), false);
                            v2.reuse();
                        }
                    }

                    v1.b();
                    if(v0 == null) {
                        return;
                    }

                    AndroidUtilities.runOnUIThread(new Runnable(v0) {
                        public void run() {
                            NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.botKeyboardDidLoaded, new Object[]{this.val$botKeyboardFinal, Long.valueOf(this.this$1.val$did)});
                        }
                    });
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }
        });
    }

    public void loadDrafts() {
        if(!UserConfig.getInstance(DataQuery.currentAccount).draftsLoaded) {
            if(this.loadingDrafts) {
            }
            else {
                this.loadingDrafts = true;
                ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(new TL_messages_getAllDrafts(), new RequestDelegate() {
                    public void run(TLObject arg2, TL_error arg3) {
                        if(arg3 != null) {
                            return;
                        }

                        MessagesController.getInstance(DataQuery.currentAccount).processUpdates(((Updates)arg2), false);
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                UserConfig.getInstance(DataQuery.currentAccount).draftsLoaded = true;
                                this.this$1.this$0.loadingDrafts = false;
                                UserConfig.getInstance(DataQuery.currentAccount).saveConfig(false);
                            }
                        });
                    }
                });
            }
        }
    }

    public void loadFeaturedStickers(boolean arg2, boolean arg3) {
        if(this.loadingFeaturedStickers) {
            return;
        }

        this.loadingFeaturedStickers = true;
        if(arg2) {
            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable() {
                public void run() {
                    int v3_2;
                    int v4_2;
                    int v5_1;
                    NativeByteBuffer v4_1;
                    ArrayList v5;
                    SQLiteCursor v3_1;
                    ArrayList v2 = new ArrayList();
                    ArrayList v0 = null;
                    int v1 = 0;
                    try {
                        v3_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b("SELECT data, unread, date, hash FROM stickers_featured WHERE 1", new Object[0]);
                        goto label_10;
                    }
                    catch(Throwable v1_1) {
                    }
                    catch(Throwable v3) {
                        v5 = v0;
                        Throwable v4 = v3;
                        goto label_74;
                        try {
                        label_10:
                            if(v3_1.a()) {
                                v4_1 = v3_1.g(0);
                                if(v4_1 != null) {
                                    v5 = new ArrayList();
                                    goto label_16;
                                }

                                goto label_31;
                            }
                            else {
                                goto label_55;
                            }
                        }
                        catch(Throwable v4) {
                            goto label_64;
                        }

                        try {
                        label_16:
                            int v0_2 = v4_1.readInt32(false);
                            int v6;
                            for(v6 = 0; v6 < v0_2; ++v6) {
                                v5.add(StickerSetCovered.TLdeserialize(((AbstractSerializedData)v4_1), v4_1.readInt32(false), false));
                            }

                            v4_1.reuse();
                            v0 = v5;
                        }
                        catch(Throwable v0_1) {
                            v4 = v0_1;
                            goto label_65;
                        }

                        try {
                        label_31:
                            v4_1 = v3_1.g(1);
                            if(v4_1 != null) {
                                v5_1 = v4_1.readInt32(false);
                                for(v6 = 0; v6 < v5_1; ++v6) {
                                    v2.add(Long.valueOf(v4_1.readInt64(false)));
                                }

                                v4_1.reuse();
                            }

                            v4_2 = v3_1.b(2);
                            goto label_44;
                        }
                        catch(Throwable v4) {
                        label_64:
                            v5 = v0;
                        }

                    label_65:
                        SQLiteCursor v0_3 = v3_1;
                    label_74:
                        v3_2 = 0;
                        goto label_75;
                        try {
                        label_44:
                            v5_1 = DataQuery.this.calcFeaturedStickersHash(v0);
                            v1 = v4_2;
                            goto label_56;
                        }
                        catch(Throwable v5_2) {
                            Throwable v9 = v5_2;
                            v5 = v0;
                            v0_3 = v3_1;
                            v3_2 = v4_2;
                            v4 = v9;
                        }

                        try {
                        label_75:
                            FileLog.e(v4);
                            if((((SQLiteCursor)v0)) == null) {
                                goto label_78;
                            }
                        }
                        catch(Throwable v1_1) {
                            v3_1 = ((SQLiteCursor)v0);
                            v0_1 = v1_1;
                            goto label_85;
                        }
                    }

                    ((SQLiteCursor)v0).b();
                label_78:
                    v4_2 = v3_2;
                    ArrayList v1_2 = v5;
                    v5_1 = 0;
                    goto label_81;
                label_85:
                    if(v3_1 != null) {
                        v3_1.b();
                    }

                    throw v0_1;
                label_55:
                    v5_1 = 0;
                label_56:
                    if(v3_1 != null) {
                        v3_1.b();
                    }

                    v4_2 = v1;
                    v1_2 = v0;
                label_81:
                    DataQuery.this.processLoadedFeaturedStickers(v1_2, v2, true, v4_2, v5_1);
                }
            });
        }
        else {
            TL_messages_getFeaturedStickers v2 = new TL_messages_getFeaturedStickers();
            int v3 = arg3 ? 0 : DataQuery.loadFeaturedHash;
            v2.hash = v3;
            ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v2), new RequestDelegate(v2) {
                public void run(TLObject arg1, TL_error arg2) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                        public void run() {
                            long v1 = 1000;
                            if((this.val$response instanceof TL_messages_featuredStickers)) {
                                this.this$1.this$0.processLoadedFeaturedStickers(this.val$response.sets, this.val$response.unread, false, ((int)(System.currentTimeMillis() / v1)), this.val$response.hash);
                            }
                            else {
                                this.this$1.this$0.processLoadedFeaturedStickers(null, null, false, ((int)(System.currentTimeMillis() / v1)), this.this$1.val$req.hash);
                            }
                        }
                    });
                }
            });
        }
    }

    private void loadGroupStickerSet(StickerSet arg4, boolean arg5) {
        if(arg5) {
            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg4) {
                public void run() {
                    try {
                        SQLiteDatabase v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        StringBuilder v1 = new StringBuilder();
                        v1.append("SELECT document FROM web_recent_v3 WHERE id = \'s_");
                        v1.append(this.val$stickerSet.id);
                        v1.append("\'");
                        SQLiteCursor v0_2 = v0_1.b(v1.toString(), new Object[0]);
                        TL_messages_stickerSet v3 = null;
                        if((v0_2.a()) && !v0_2.a(0)) {
                            NativeByteBuffer v1_1 = v0_2.g(0);
                            if(v1_1 != null) {
                                v3 = TL_messages_stickerSet.TLdeserialize(((AbstractSerializedData)v1_1), v1_1.readInt32(false), false);
                                v1_1.reuse();
                            }
                        }

                        v0_2.b();
                        if(v3 == null || v3.set == null || v3.set.hash != this.val$stickerSet.hash) {
                            DataQuery.this.loadGroupStickerSet(this.val$stickerSet, false);
                        }

                        if(v3 == null) {
                            return;
                        }

                        if(v3.set == null) {
                            return;
                        }

                        AndroidUtilities.runOnUIThread(new Runnable(v3) {
                            public void run() {
                                DataQuery.groupStickerSets.put(this.val$set.set.id, this.val$set);
                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.groupStickersDidLoaded, new Object[]{Long.valueOf(this.val$set.set.id)});
                            }
                        });
                    }
                    catch(Throwable v0) {
                        FileLog.e(v0);
                    }
                }
            });
        }
        else {
            TL_messages_getStickerSet v5 = new TL_messages_getStickerSet();
            v5.stickerset = new TL_inputStickerSetID();
            v5.stickerset.id = arg4.id;
            v5.stickerset.access_hash = arg4.access_hash;
            ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v5), new RequestDelegate() {
                public void run(TLObject arg2, TL_error arg3) {
                    if(arg2 != null) {
                        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(((TL_messages_stickerSet)arg2)) {
                            public void run() {
                                try {
                                    SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                                    v0_1.d();
                                    v0_1.a(1, "s_" + this.val$set.set.id);
                                    v0_1.a(2, 6);
                                    v0_1.a(3, "");
                                    v0_1.a(4, "");
                                    v0_1.a(5, "");
                                    v0_1.a(6, 0);
                                    v0_1.a(7, 0);
                                    v0_1.a(8, 0);
                                    v0_1.a(9, 0);
                                    NativeByteBuffer v1 = new NativeByteBuffer(this.val$set.getObjectSize());
                                    this.val$set.serializeToStream(((AbstractSerializedData)v1));
                                    v0_1.a(10, v1);
                                    v0_1.b();
                                    v1.reuse();
                                    v0_1.e();
                                }
                                catch(Exception v0) {
                                    FileLog.e(((Throwable)v0));
                                }
                            }
                        });
                        AndroidUtilities.runOnUIThread(new Runnable(((TL_messages_stickerSet)arg2)) {
                            public void run() {
                                DataQuery.groupStickerSets.put(this.val$set.set.id, this.val$set);
                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.groupStickersDidLoaded, new Object[]{Long.valueOf(this.val$set.set.id)});
                            }
                        });
                    }
                }
            });
        }
    }

    public void loadHints(boolean arg3) {
        if(!this.loading) {
            if(!UserConfig.getInstance(DataQuery.currentAccount).suggestContacts) {
            }
            else if(!arg3) {
                this.loading = true;
                TL_contacts_getTopPeers v3 = new TL_contacts_getTopPeers();
                v3.hash = 0;
                v3.bots_pm = false;
                v3.correspondents = true;
                v3.groups = false;
                v3.channels = false;
                v3.bots_inline = true;
                v3.offset = 0;
                v3.limit = 20;
                ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v3), new RequestDelegate() {
                    public void run(TLObject arg1, TL_error arg2) {
                        if((arg1 instanceof TL_contacts_topPeers)) {
                            AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                                public void run() {
                                    TLObject v0 = this.val$response;
                                    MessagesController.getInstance(DataQuery.currentAccount).putUsers(((TL_contacts_topPeers)v0).users, false);
                                    MessagesController.getInstance(DataQuery.currentAccount).putChats(((TL_contacts_topPeers)v0).chats, false);
                                    int v1;
                                    for(v1 = 0; v1 < ((TL_contacts_topPeers)v0).categories.size(); ++v1) {
                                        Object v2 = ((TL_contacts_topPeers)v0).categories.get(v1);
                                        long v5 = 1000;
                                        if((((TL_topPeerCategoryPeers)v2).category instanceof TL_topPeerCategoryBotsInline)) {
                                            this.this$1.this$0.inlineBots = ((TL_topPeerCategoryPeers)v2).peers;
                                            UserConfig.getInstance(DataQuery.currentAccount).botRatingLoadTime = ((int)(System.currentTimeMillis() / v5));
                                        }
                                        else {
                                            this.this$1.this$0.hints = ((TL_topPeerCategoryPeers)v2).peers;
                                            int v2_1 = UserConfig.getInstance(DataQuery.currentAccount).getClientUserId();
                                            int v4 = 0;
                                            while(v4 < this.this$1.this$0.hints.size()) {
                                                if(this.this$1.this$0.hints.get(v4).peer.user_id == v2_1) {
                                                    this.this$1.this$0.hints.remove(v4);
                                                }
                                                else {
                                                    ++v4;
                                                    continue;
                                                }

                                                break;
                                            }

                                            UserConfig.getInstance(DataQuery.currentAccount).ratingLoadTime = ((int)(System.currentTimeMillis() / v5));
                                        }
                                    }

                                    UserConfig.getInstance(DataQuery.currentAccount).saveConfig(false);
                                    this.this$1.this$0.buildShortcuts();
                                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
                                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
                                    MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(((TL_contacts_topPeers)v0)) {
                                        public void run() {
                                            int v8;
                                            try {
                                                MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("DELETE FROM chat_hints WHERE 1").c().e();
                                                MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().d();
                                                MessagesStorage.getInstance(DataQuery.currentAccount).putUsersAndChats(this.val$topPeers.users, this.val$topPeers.chats, false, false);
                                                SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO chat_hints VALUES(?, ?, ?, ?)");
                                                int v1;
                                                for(v1 = 0; v1 < this.val$topPeers.categories.size(); ++v1) {
                                                    Object v2 = this.val$topPeers.categories.get(v1);
                                                    int v4 = (((TL_topPeerCategoryPeers)v2).category instanceof TL_topPeerCategoryBotsInline) ? 1 : 0;
                                                    int v6;
                                                    for(v6 = 0; v6 < ((TL_topPeerCategoryPeers)v2).peers.size(); ++v6) {
                                                        Object v7 = ((TL_topPeerCategoryPeers)v2).peers.get(v6);
                                                        if((((TL_topPeer)v7).peer instanceof TL_peerUser)) {
                                                            v8 = ((TL_topPeer)v7).peer.user_id;
                                                        }
                                                        else {
                                                            v8 = (((TL_topPeer)v7).peer instanceof TL_peerChat) ? ((TL_topPeer)v7).peer.chat_id : ((TL_topPeer)v7).peer.channel_id;
                                                            v8 = -v8;
                                                        }

                                                        v0_1.d();
                                                        v0_1.a(1, v8);
                                                        v0_1.a(2, v4);
                                                        v0_1.a(3, ((TL_topPeer)v7).rating);
                                                        v0_1.a(4, 0);
                                                        v0_1.b();
                                                    }
                                                }

                                                v0_1.e();
                                                MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().e();
                                                AndroidUtilities.runOnUIThread(new Runnable() {
                                                    public void run() {
                                                        UserConfig.getInstance(DataQuery.currentAccount).suggestContacts = true;
                                                        UserConfig.getInstance(DataQuery.currentAccount).lastHintsSyncTime = ((int)(System.currentTimeMillis() / 1000));
                                                        UserConfig.getInstance(DataQuery.currentAccount).saveConfig(false);
                                                    }
                                                });
                                            }
                                            catch(Exception v0) {
                                                FileLog.e(((Throwable)v0));
                                            }
                                        }
                                    });
                                }
                            });
                        }
                        else if((arg1 instanceof TL_contacts_topPeersDisabled)) {
                            AndroidUtilities.runOnUIThread(new Runnable() {
                                public void run() {
                                    UserConfig.getInstance(DataQuery.currentAccount).suggestContacts = false;
                                    UserConfig.getInstance(DataQuery.currentAccount).lastHintsSyncTime = ((int)(System.currentTimeMillis() / 1000));
                                    UserConfig.getInstance(DataQuery.currentAccount).saveConfig(false);
                                    this.this$1.this$0.clearTopPeers();
                                }
                            });
                        }
                    }
                });
            }
            else if(this.loaded) {
                return;
            }
            else {
                this.loading = true;
                MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable() {
                    public void run() {
                        ArrayList v4 = new ArrayList();
                        ArrayList v5 = new ArrayList();
                        ArrayList v2 = new ArrayList();
                        ArrayList v3 = new ArrayList();
                        int v0 = UserConfig.getInstance(DataQuery.currentAccount).getClientUserId();
                        try {
                            ArrayList v1 = new ArrayList();
                            ArrayList v6 = new ArrayList();
                            SQLiteCursor v7 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b("SELECT did, type, rating FROM chat_hints WHERE 1 ORDER BY rating DESC", new Object[0]);
                            while(v7.a()) {
                                int v8 = v7.b(0);
                                if(v8 == v0) {
                                    continue;
                                }

                                int v11 = v7.b(1);
                                TL_topPeer v12 = new TL_topPeer();
                                v12.rating = v7.c(2);
                                if(v8 > 0) {
                                    v12.peer = new TL_peerUser();
                                    v12.peer.user_id = v8;
                                    v1.add(Integer.valueOf(v8));
                                }
                                else {
                                    v12.peer = new TL_peerChat();
                                    v8 = -v8;
                                    v12.peer.chat_id = v8;
                                    v6.add(Integer.valueOf(v8));
                                }

                                if(v11 == 0) {
                                    v4.add(v12);
                                    continue;
                                }

                                if(v11 != 1) {
                                    continue;
                                }

                                v5.add(v12);
                            }

                            v7.b();
                            if(!v1.isEmpty()) {
                                MessagesStorage.getInstance(DataQuery.currentAccount).getUsersInternal(TextUtils.join(",", ((Iterable)v1)), v2);
                            }

                            if(!v6.isEmpty()) {
                                MessagesStorage.getInstance(DataQuery.currentAccount).getChatsInternal(TextUtils.join(",", ((Iterable)v6)), v3);
                            }

                            AndroidUtilities.runOnUIThread(new Runnable(v2, v3, v4, v5) {
                                public void run() {
                                    MessagesController.getInstance(DataQuery.currentAccount).putUsers(this.val$users, true);
                                    MessagesController.getInstance(DataQuery.currentAccount).putChats(this.val$chats, true);
                                    this.this$1.this$0.loading = false;
                                    this.this$1.this$0.loaded = true;
                                    this.this$1.this$0.hints = this.val$hintsNew;
                                    this.this$1.this$0.inlineBots = this.val$inlineBotsNew;
                                    this.this$1.this$0.buildShortcuts();
                                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
                                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
                                    if(Math.abs(UserConfig.getInstance(DataQuery.currentAccount).lastHintsSyncTime - (((int)(System.currentTimeMillis() / 1000)))) >= 86400) {
                                        this.this$1.this$0.loadHints(false);
                                    }
                                }
                            });
                        }
                        catch(Exception v0_1) {
                            FileLog.e(((Throwable)v0_1));
                        }
                    }
                });
                this.loaded = true;
            }
        }
    }

    public void loadMedia(long arg13, int arg15, int arg16, int arg17, boolean arg18, int arg19) {
        TL_inputMessagesFilterPhotoVideo v1;
        int v6 = arg17;
        int v0 = ((int)arg13);
        boolean v8 = v0 >= 0 || !ChatObject.isChannel(-v0, DataQuery.currentAccount) ? false : true;
        if((arg18) || v0 == 0) {
            this.loadMediaDatabase(arg13, arg15, arg16, arg17, arg19, v8);
        }
        else {
            TL_messages_search v9 = new TL_messages_search();
            v9.limit = arg15 + 1;
            v9.offset_id = arg16;
            if(v6 == 0) {
                v1 = new TL_inputMessagesFilterPhotoVideo();
                goto label_25;
            }
            else if(v6 == 1) {
                TL_inputMessagesFilterDocument v1_1 = new TL_inputMessagesFilterDocument();
                goto label_25;
            }
            else if(v6 == 2) {
                TL_inputMessagesFilterRoundVoice v1_2 = new TL_inputMessagesFilterRoundVoice();
                goto label_25;
            }
            else if(v6 == 3) {
                TL_inputMessagesFilterUrl v1_3 = new TL_inputMessagesFilterUrl();
                goto label_25;
            }
            else if(v6 == 4) {
                TL_inputMessagesFilterMusic v1_4 = new TL_inputMessagesFilterMusic();
            label_25:
                v9.filter = ((MessagesFilter)v1);
            }

            v9.q = "";
            v9.peer = MessagesController.getInstance(DataQuery.currentAccount).getInputPeer(v0);
            if(v9.peer == null) {
                return;
            }

            ConnectionsManager.getInstance(DataQuery.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v9), new RequestDelegate(arg15, arg13, arg16, arg17, arg19, v8) {
                public void run(TLObject arg12, TL_error arg13) {
                    boolean v10;
                    if(arg13 == null) {
                        TLObject v1 = arg12;
                        if(((messages_Messages)v1).messages.size() > this.val$count) {
                            ((messages_Messages)v1).messages.remove(((messages_Messages)v1).messages.size() - 1);
                            v10 = false;
                        }
                        else {
                            v10 = true;
                        }

                        DataQuery.this.processLoadedMedia(((messages_Messages)v1), this.val$uid, this.val$count, this.val$max_id, this.val$type, false, this.val$classGuid, this.val$isChannel, v10);
                    }
                }
            }), arg19);
        }
    }

    private void loadMediaDatabase(long arg12, int arg14, int arg15, int arg16, int arg17, boolean arg18) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg14, arg12, arg15, arg18, arg16, arg17) {
            public void run() {
                // Method was not decompiled
            }
        });
    }

    public void loadMusic(long arg9, long arg11) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg9, arg11) {
            public void run() {
                Object[] v3_1;
                String v2_1;
                Object[] v4_1;
                Locale v7;
                SQLiteDatabase v1_1;
                ArrayList v0 = new ArrayList();
                try {
                    int v2 = 4;
                    int v3 = 2;
                    int v4 = 3;
                    if((((int)this.val$uid)) != 0) {
                        v1_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        v7 = Locale.US;
                        v4_1 = new Object[v4];
                        v4_1[0] = Long.valueOf(this.val$uid);
                        v4_1[1] = Long.valueOf(this.val$max_id);
                        v4_1[v3] = Integer.valueOf(v2);
                        v2_1 = String.format(v7, "SELECT data, mid FROM media_v2 WHERE uid = %d AND mid < %d AND type = %d ORDER BY date DESC, mid DESC LIMIT 1000", v4_1);
                        v3_1 = new Object[0];
                    }
                    else {
                        v1_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        v7 = Locale.US;
                        v4_1 = new Object[v4];
                        v4_1[0] = Long.valueOf(this.val$uid);
                        v4_1[1] = Long.valueOf(this.val$max_id);
                        v4_1[v3] = Integer.valueOf(v2);
                        v2_1 = String.format(v7, "SELECT data, mid FROM media_v2 WHERE uid = %d AND mid > %d AND type = %d ORDER BY date DESC, mid DESC LIMIT 1000", v4_1);
                        v3_1 = new Object[0];
                    }

                    SQLiteCursor v1_2 = v1_1.b(v2_1, v3_1);
                    while(v1_2.a()) {
                        NativeByteBuffer v2_2 = v1_2.g(0);
                        if(v2_2 == null) {
                            continue;
                        }

                        Message v3_2 = Message.TLdeserialize(((AbstractSerializedData)v2_2), v2_2.readInt32(false), false);
                        v3_2.readAttachPath(((AbstractSerializedData)v2_2), UserConfig.getInstance(DataQuery.currentAccount).clientUserId);
                        v2_2.reuse();
                        if(!MessageObject.isMusicMessage(v3_2)) {
                            continue;
                        }

                        v3_2.id = v1_2.b(1);
                        v3_2.dialog_id = this.val$uid;
                        v0.add(0, new MessageObject(DataQuery.currentAccount, v3_2, false));
                    }

                    v1_2.b();
                }
                catch(Exception v1) {
                    FileLog.e(((Throwable)v1));
                }

                AndroidUtilities.runOnUIThread(new Runnable(v0) {
                    public void run() {
                        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.musicDidLoaded, new Object[]{Long.valueOf(this.this$1.val$uid), this.val$arrayList});
                    }
                });
            }
        });
    }

    public MessageObject loadPinnedMessage(int arg2, int arg3, boolean arg4) {
        if(arg4) {
            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg2, arg3) {
                public void run() {
                    DataQuery.this.loadPinnedMessageInternal(this.val$channelId, this.val$mid, false);
                }
            });
            return null;
        }

        return this.loadPinnedMessageInternal(arg2, arg3, true);
    }

    private MessageObject loadPinnedMessageInternal(int arg16, int arg17, boolean arg18) {
        ConnectionsManager v1_1;
        TL_channels_getMessages v2_2;
        Message v3;
        Message v10;
        NativeByteBuffer v9;
        ArrayList v6;
        ArrayList v5;
        ArrayList v8;
        ArrayList v7;
        int v0 = arg16;
        int v1 = arg17;
        long v2 = (((long)v1)) | (((long)v0)) << 32;
        MessageObject v4 = null;
        try {
            v7 = new ArrayList();
            v8 = new ArrayList();
            v5 = new ArrayList();
            v6 = new ArrayList();
            SQLiteCursor v2_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT data, mid, date FROM messages WHERE mid = %d", Long.valueOf(v2)), new Object[0]);
            if(v2_1.a()) {
                v9 = v2_1.g(0);
                if(v9 != null) {
                    v10 = Message.TLdeserialize(((AbstractSerializedData)v9), v9.readInt32(false), false);
                    v10.readAttachPath(((AbstractSerializedData)v9), UserConfig.getInstance(DataQuery.currentAccount).clientUserId);
                    v9.reuse();
                    if((v10.action instanceof TL_messageActionHistoryClear)) {
                        goto label_54;
                    }
                    else {
                        v10.id = v2_1.b(1);
                        v10.date = v2_1.b(2);
                        v10.dialog_id = ((long)(-v0));
                        MessagesStorage.addUsersAndChatsFromMessage(v10, v5, v6);
                    }
                }
                else {
                    goto label_54;
                }
            }
            else {
            label_54:
                v10 = ((Message)v4);
            }

            v2_1.b();
            if(v10 == null) {
                v2_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT data FROM chat_pinned WHERE uid = %d", Integer.valueOf(arg16)), new Object[0]);
                if(v2_1.a()) {
                    v9 = v2_1.g(0);
                    if(v9 != null) {
                        v3 = Message.TLdeserialize(((AbstractSerializedData)v9), v9.readInt32(false), false);
                        v3.readAttachPath(((AbstractSerializedData)v9), UserConfig.getInstance(DataQuery.currentAccount).clientUserId);
                        v9.reuse();
                        if(v3.id == v1) {
                            if((v3.action instanceof TL_messageActionHistoryClear)) {
                            }
                            else {
                                v3.dialog_id = ((long)(-v0));
                                MessagesStorage.addUsersAndChatsFromMessage(v3, v5, v6);
                                goto label_93;
                            }
                        }

                        v3 = ((Message)v4);
                    }
                    else {
                        goto label_92;
                    }
                }
                else {
                label_92:
                    v3 = v10;
                }

            label_93:
                v2_1.b();
            }
            else {
                v3 = v10;
            }

            if(v3 != null) {
                goto label_113;
            }

            v2_2 = new TL_channels_getMessages();
            v2_2.channel = MessagesController.getInstance(DataQuery.currentAccount).getInputChannel(v0);
            v2_2.id.add(Integer.valueOf(arg17));
            v1_1 = ConnectionsManager.getInstance(DataQuery.currentAccount);
        }
        catch(Exception v0_1) {
            goto label_145;
        }

        try {
            v1_1.sendRequest(((TLObject)v2_2), new RequestDelegate(v0) {
                public void run(TLObject arg9, TL_error arg10) {
                    int v0 = 1;
                    if(arg10 == null) {
                        DataQuery.removeEmptyMessages(((messages_Messages)arg9).messages);
                        if(!((messages_Messages)arg9).messages.isEmpty()) {
                            ImageLoader.saveMessagesThumbs(((messages_Messages)arg9).messages);
                            DataQuery.this.broadcastPinnedMessage(((messages_Messages)arg9).messages.get(0), ((messages_Messages)arg9).users, ((messages_Messages)arg9).chats, false, false);
                            MessagesStorage.getInstance(DataQuery.currentAccount).putUsersAndChats(((messages_Messages)arg9).users, ((messages_Messages)arg9).chats, true, true);
                            DataQuery.this.savePinnedMessage(((messages_Messages)arg9).messages.get(0));
                        }
                        else {
                            goto label_29;
                        }
                    }
                    else {
                    label_29:
                        v0 = 0;
                    }

                    if(v0 == 0) {
                        MessagesStorage.getInstance(DataQuery.currentAccount).updateChannelPinnedMessage(this.val$channelId, 0);
                    }
                }
            });
            return v4;
        label_113:
            if(arg18) {
                return this.broadcastPinnedMessage(v3, v7, v8, true, arg18);
            }

            if(!v5.isEmpty()) {
                MessagesStorage.getInstance(DataQuery.currentAccount).getUsersInternal(TextUtils.join(",", ((Iterable)v5)), v7);
            }

            if(!v6.isEmpty()) {
                MessagesStorage.getInstance(DataQuery.currentAccount).getChatsInternal(TextUtils.join(",", ((Iterable)v6)), v8);
            }

            this.broadcastPinnedMessage(v3, v7, v8, true, false);
            return v4;
        label_122:
        }
        catch(Exception v0_1) {
            goto label_122;
        }

    label_145:
        FileLog.e(((Throwable)v0_1));
        return v4;
    }

    public void loadRecents(int arg6, boolean arg7, boolean arg8, boolean arg9) {
        TL_messages_getFavedStickers v8_3;
        org.telegram.messenger.DataQuery$9 v0_1;
        ConnectionsManager v9_1;
        String v9;
        boolean v0 = false;
        if(arg7) {
            if(this.loadingRecentGifs) {
                return;
            }
            else {
                this.loadingRecentGifs = true;
            }
        }
        else if(DataQuery.loadingRecentStickers[arg6]) {
            return;
        }
        else {
            DataQuery.loadingRecentStickers[arg6] = true;
        }

        if(0 != 0) {
            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg7, arg6) {
                public void run() {
                    int v0_1;
                    try {
                        if(this.val$gif) {
                            v0_1 = 2;
                        }
                        else if(this.val$type == 0) {
                            v0_1 = 3;
                        }
                        else if(this.val$type == 1) {
                            v0_1 = 4;
                        }
                        else {
                            v0_1 = 5;
                        }

                        SQLiteDatabase v1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        StringBuilder v2 = new StringBuilder();
                        v2.append("SELECT document FROM web_recent_v3 WHERE type = ");
                        v2.append(v0_1);
                        v2.append(" ORDER BY date DESC");
                        SQLiteCursor v0_2 = v1.b(v2.toString(), new Object[0]);
                        ArrayList v1_1 = new ArrayList();
                        while(v0_2.a()) {
                            if(v0_2.a(0)) {
                                continue;
                            }

                            NativeByteBuffer v3 = v0_2.g(0);
                            if(v3 == null) {
                                continue;
                            }

                            Document v4 = Document.TLdeserialize(((AbstractSerializedData)v3), v3.readInt32(false), false);
                            if(v4 != null) {
                                v1_1.add(v4);
                            }

                            v3.reuse();
                        }

                        v0_2.b();
                        AndroidUtilities.runOnUIThread(new Runnable(v1_1) {
                            public void run() {
                                if(this.this$1.val$gif) {
                                    this.this$1.this$0.recentGifs = this.val$arrayList;
                                    this.this$1.this$0.loadingRecentGifs = false;
                                    this.this$1.this$0.recentGifsLoaded = true;
                                }
                                else {
                                    DataQuery.recentStickers[this.this$1.val$type] = this.val$arrayList;
                                    DataQuery.loadingRecentStickers[this.this$1.val$type] = false;
                                    DataQuery.recentStickersLoaded[this.this$1.val$type] = true;
                                }

                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.recentDocumentsDidLoaded, new Object[]{Boolean.valueOf(this.this$1.val$gif), Integer.valueOf(this.this$1.val$type)});
                                this.this$1.this$0.loadRecents(this.this$1.val$type, this.this$1.val$gif, false, false);
                            }
                        });
                    }
                    catch(Throwable v0) {
                        FileLog.e(v0);
                    }
                }
            });
        }
        else {
            SharedPreferences v8 = MessagesController.getEmojiSettings(DataQuery.currentAccount);
            if(!arg9) {
                long v2 = 0;
                if(arg7) {
                    v9 = "lastGifLoadTime";
                }
                else if(arg6 == 0) {
                    v9 = "lastStickersLoadTime";
                }
                else if(arg6 == 1) {
                    v9 = "lastStickersLoadTimeMask";
                }
                else {
                    v9 = "lastStickersLoadTimeFavs";
                }

                long v8_1 = v8.getLong(v9, v2);
                if(Math.abs(System.currentTimeMillis() - v8_1) >= 3600000) {
                    goto label_56;
                }

                if(arg7) {
                    this.loadingRecentGifs = false;
                }
                else {
                    DataQuery.loadingRecentStickers[arg6] = false;
                }

                return;
            }

        label_56:
            if(arg7) {
                TL_messages_getSavedGifs v8_2 = new TL_messages_getSavedGifs();
                v8_2.hash = DataQuery.calcDocumentsHash(this.recentGifs);
                v9_1 = ConnectionsManager.getInstance(DataQuery.currentAccount);
                v0_1 = new RequestDelegate(arg6, arg7) {
                    public void run(TLObject arg4, TL_error arg5) {
                        ArrayList v4 = (arg4 instanceof TL_messages_savedGifs) ? ((TL_messages_savedGifs)arg4).gifs : null;
                        DataQuery.this.processLoadedRecentDocuments(this.val$type, v4, this.val$gif, 0);
                    }
                };
            }
            else {
                if(arg6 == 2) {
                    v8_3 = new TL_messages_getFavedStickers();
                    v8_3.hash = DataQuery.calcDocumentsHash(DataQuery.recentStickers[arg6]);
                }
                else {
                    TL_messages_getRecentStickers v8_4 = new TL_messages_getRecentStickers();
                    v8_4.hash = DataQuery.calcDocumentsHash(DataQuery.recentStickers[arg6]);
                    if(arg6 == 1) {
                        v0 = true;
                    }

                    v8_4.attached = v0;
                }

                v9_1 = ConnectionsManager.getInstance(DataQuery.currentAccount);
                org.telegram.messenger.DataQuery$10 v0_2 = new RequestDelegate(arg6, arg7) {
                    public void run(TLObject arg4, TL_error arg5) {
                        ArrayList v4;
                        if(this.val$type == 2) {
                            if((arg4 instanceof TL_messages_favedStickers)) {
                                v4 = ((TL_messages_favedStickers)arg4).stickers;
                            }
                            else {
                                goto label_11;
                            }
                        }
                        else if((arg4 instanceof TL_messages_recentStickers)) {
                            v4 = ((TL_messages_recentStickers)arg4).stickers;
                        }
                        else {
                        label_11:
                            v4 = null;
                        }

                        DataQuery.this.processLoadedRecentDocuments(this.val$type, v4, this.val$gif, 0);
                    }
                };
            }

            v9_1.sendRequest(((TLObject)v8_3), ((RequestDelegate)v0_1));
        }
    }

    public void loadReplyMessagesForMessages(ArrayList arg15, long arg16) {
        Object v1;
        ArrayList v5;
        int v2 = 0;
        if((((int)arg16)) == 0) {
            v5 = new ArrayList();
            LongSparseArray v6 = new LongSparseArray();
            while(v2 < arg15.size()) {
                v1 = arg15.get(v2);
                if((((MessageObject)v1).isReply()) && ((MessageObject)v1).replyMessageObject == null) {
                    long v7 = ((MessageObject)v1).messageOwner.reply_to_random_id;
                    Object v9 = v6.get(v7);
                    if(v9 == null) {
                        ArrayList v9_1 = new ArrayList();
                        v6.put(v7, v9_1);
                    }

                    ((ArrayList)v9).add(v1);
                    if(v5.contains(Long.valueOf(v7))) {
                        goto label_29;
                    }

                    v5.add(Long.valueOf(v7));
                }

            label_29:
                ++v2;
            }

            if(v5.isEmpty()) {
                return;
            }

            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(v5, arg16, v6) {
                public void run() {
                    Object v1_1;
                    Message v2;
                    try {
                        SQLiteCursor v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT m.data, m.mid, m.date, r.random_id FROM randoms as r INNER JOIN messages as m ON r.mid = m.mid WHERE r.random_id IN(%s)", TextUtils.join(",", this.val$replyMessages)), new Object[0]);
                        do {
                        label_15:
                            if(!v0_1.a()) {
                                goto label_61;
                            }

                            NativeByteBuffer v1 = v0_1.g(0);
                            if(v1 == null) {
                                goto label_15;
                            }

                            v2 = Message.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false);
                            v2.readAttachPath(((AbstractSerializedData)v1), UserConfig.getInstance(DataQuery.currentAccount).clientUserId);
                            v1.reuse();
                            v2.id = v0_1.b(1);
                            v2.date = v0_1.b(2);
                            v2.dialog_id = this.val$dialogId;
                            long v4 = v0_1.d(3);
                            v1_1 = this.val$replyMessageRandomOwners.get(v4);
                            this.val$replyMessageRandomOwners.remove(v4);
                        }
                        while(v1_1 == null);

                        MessageObject v4_1 = new MessageObject(DataQuery.currentAccount, v2, false);
                        int v2_1 = 0;
                        goto label_44;
                    label_61:
                        v0_1.b();
                        if(this.val$replyMessageRandomOwners.size() != 0) {
                            int v0_2 = 0;
                            goto label_66;
                            while(true) {
                            label_44:
                                if(v2_1 >= ((ArrayList)v1_1).size()) {
                                    goto label_15;
                                }

                                Object v5 = ((ArrayList)v1_1).get(v2_1);
                                ((MessageObject)v5).replyMessageObject = v4_1;
                                ((MessageObject)v5).messageOwner.reply_to_msg_id = v4_1.getId();
                                if(((MessageObject)v5).isMegagroup()) {
                                    ((MessageObject)v5).replyMessageObject.messageOwner.flags |= -2147483648;
                                }

                                ++v2_1;
                            }

                        label_66:
                            while(v0_2 < this.val$replyMessageRandomOwners.size()) {
                                v1_1 = this.val$replyMessageRandomOwners.valueAt(v0_2);
                                for(v2_1 = 0; v2_1 < ((ArrayList)v1_1).size(); ++v2_1) {
                                    ((ArrayList)v1_1).get(v2_1).messageOwner.reply_to_random_id = 0;
                                }

                                ++v0_2;
                            }
                        }

                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.didLoadedReplyMessages, new Object[]{Long.valueOf(this.this$1.val$dialogId)});
                            }
                        });
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            });
        }
        else {
            v5 = new ArrayList();
            SparseArray v6_1 = new SparseArray();
            StringBuilder v7_1 = new StringBuilder();
            int v8 = 0;
            while(v2 < arg15.size()) {
                v1 = arg15.get(v2);
                if(((MessageObject)v1).getId() > 0 && (((MessageObject)v1).isReply()) && ((MessageObject)v1).replyMessageObject == null) {
                    int v9_2 = ((MessageObject)v1).messageOwner.reply_to_msg_id;
                    long v10 = ((long)v9_2);
                    if(((MessageObject)v1).messageOwner.to_id.channel_id != 0) {
                        v10 |= (((long)((MessageObject)v1).messageOwner.to_id.channel_id)) << 32;
                        v8 = ((MessageObject)v1).messageOwner.to_id.channel_id;
                    }

                    if(v7_1.length() > 0) {
                        v7_1.append(',');
                    }

                    v7_1.append(v10);
                    Object v10_1 = v6_1.get(v9_2);
                    if(v10_1 == null) {
                        ArrayList v10_2 = new ArrayList();
                        v6_1.put(v9_2, v10_2);
                    }

                    ((ArrayList)v10_1).add(v1);
                    if(v5.contains(Integer.valueOf(v9_2))) {
                        goto label_96;
                    }

                    v5.add(Integer.valueOf(v9_2));
                }

            label_96:
                ++v2;
            }

            if(v5.isEmpty()) {
                return;
            }

            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(v7_1, arg16, v5, v6_1, v8) {
                public void run() {
                    TL_messages_getMessages v0_3;
                    org.telegram.messenger.DataQuery$58$1 v2_1;
                    ConnectionsManager v1_1;
                    try {
                        ArrayList v1 = new ArrayList();
                        ArrayList v3 = new ArrayList();
                        ArrayList v4 = new ArrayList();
                        ArrayList v0_1 = new ArrayList();
                        ArrayList v2 = new ArrayList();
                        SQLiteCursor v5 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT data, mid, date FROM messages WHERE mid IN(%s)", this.val$stringBuilder.toString()), new Object[0]);
                        while(v5.a()) {
                            NativeByteBuffer v6 = v5.g(0);
                            if(v6 == null) {
                                continue;
                            }

                            Message v7 = Message.TLdeserialize(((AbstractSerializedData)v6), v6.readInt32(false), false);
                            v7.readAttachPath(((AbstractSerializedData)v6), UserConfig.getInstance(DataQuery.currentAccount).clientUserId);
                            v6.reuse();
                            v7.id = v5.b(1);
                            v7.date = v5.b(2);
                            v7.dialog_id = this.val$dialogId;
                            MessagesStorage.addUsersAndChatsFromMessage(v7, v0_1, v2);
                            v1.add(v7);
                            this.val$replyMessages.remove(Integer.valueOf(v7.id));
                        }

                        v5.b();
                        if(!v0_1.isEmpty()) {
                            MessagesStorage.getInstance(DataQuery.currentAccount).getUsersInternal(TextUtils.join(",", ((Iterable)v0_1)), v3);
                        }

                        if(!v2.isEmpty()) {
                            MessagesStorage.getInstance(DataQuery.currentAccount).getChatsInternal(TextUtils.join(",", ((Iterable)v2)), v4);
                        }

                        DataQuery.this.broadcastReplyMessages(v1, this.val$replyMessageOwners, v3, v4, this.val$dialogId, true);
                        if(this.val$replyMessages.isEmpty()) {
                            return;
                        }

                        if(this.val$channelIdFinal != 0) {
                            TL_channels_getMessages v0_2 = new TL_channels_getMessages();
                            v0_2.channel = MessagesController.getInstance(DataQuery.currentAccount).getInputChannel(this.val$channelIdFinal);
                            v0_2.id = this.val$replyMessages;
                            v1_1 = ConnectionsManager.getInstance(DataQuery.currentAccount);
                            v2_1 = new RequestDelegate() {
                                public void run(TLObject arg9, TL_error arg10) {
                                    if(arg10 == null) {
                                        DataQuery.removeEmptyMessages(((messages_Messages)arg9).messages);
                                        ImageLoader.saveMessagesThumbs(((messages_Messages)arg9).messages);
                                        this.this$1.this$0.broadcastReplyMessages(((messages_Messages)arg9).messages, this.this$1.val$replyMessageOwners, ((messages_Messages)arg9).users, ((messages_Messages)arg9).chats, this.this$1.val$dialogId, false);
                                        MessagesStorage.getInstance(DataQuery.currentAccount).putUsersAndChats(((messages_Messages)arg9).users, ((messages_Messages)arg9).chats, true, true);
                                        this.this$1.this$0.saveReplyMessages(this.this$1.val$replyMessageOwners, ((messages_Messages)arg9).messages);
                                    }
                                }
                            };
                        }
                        else {
                            v0_3 = new TL_messages_getMessages();
                            v0_3.id = this.val$replyMessages;
                            v1_1 = ConnectionsManager.getInstance(DataQuery.currentAccount);
                            org.telegram.messenger.DataQuery$58$2 v2_2 = new RequestDelegate() {
                                public void run(TLObject arg9, TL_error arg10) {
                                    if(arg10 == null) {
                                        DataQuery.removeEmptyMessages(((messages_Messages)arg9).messages);
                                        ImageLoader.saveMessagesThumbs(((messages_Messages)arg9).messages);
                                        this.this$1.this$0.broadcastReplyMessages(((messages_Messages)arg9).messages, this.this$1.val$replyMessageOwners, ((messages_Messages)arg9).users, ((messages_Messages)arg9).chats, this.this$1.val$dialogId, false);
                                        MessagesStorage.getInstance(DataQuery.currentAccount).putUsersAndChats(((messages_Messages)arg9).users, ((messages_Messages)arg9).chats, true, true);
                                        this.this$1.this$0.saveReplyMessages(this.this$1.val$replyMessageOwners, ((messages_Messages)arg9).messages);
                                    }
                                }
                            };
                        }

                        v1_1.sendRequest(((TLObject)v0_3), ((RequestDelegate)v2_1));
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            });
        }
    }

    public static void loadStickers(int arg3, boolean arg4, boolean arg5) {
        TL_messages_getAllStickers v0_1;
        if(DataQuery.loadingStickers[arg3]) {
            return;
        }

        int v0 = 3;
        if(arg3 == v0) {
            if(!DataQuery.featuredStickerSets.isEmpty() && (MessagesController.getInstance(DataQuery.currentAccount).preloadFeaturedStickers)) {
                goto label_15;
            }

            return;
        }
        else {
            DataQuery.loadArchivedStickersCount(arg3, arg4);
        }

    label_15:
        DataQuery.loadingStickers[arg3] = true;
        if(arg4) {
            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg3) {
                public void run() {
                    int v5_2;
                    SQLiteCursor v0_3;
                    int v4_3;
                    NativeByteBuffer v4_2;
                    ArrayList v5;
                    SQLiteCursor v3_2;
                    ArrayList v0 = null;
                    int v2 = 0;
                    try {
                        SQLiteDatabase v3_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        StringBuilder v4 = new StringBuilder();
                        v4.append("SELECT data, date, hash FROM stickers_v2 WHERE id = ");
                        v4.append(this.val$type + 1);
                        v3_2 = v3_1.b(v4.toString(), new Object[0]);
                        goto label_16;
                    }
                    catch(Throwable v1) {
                    }
                    catch(Throwable v3) {
                        v5 = v0;
                        goto label_66;
                        try {
                        label_16:
                            if(v3_2.a()) {
                                v4_2 = v3_2.g(0);
                                if(v4_2 != null) {
                                    v5 = new ArrayList();
                                    goto label_22;
                                }

                                goto label_39;
                            }
                            else {
                                goto label_49;
                            }
                        }
                        catch(Throwable v4_1) {
                            goto label_58;
                        }

                        try {
                        label_22:
                            int v0_2 = v4_2.readInt32(false);
                            int v6;
                            for(v6 = 0; v6 < v0_2; ++v6) {
                                v5.add(TL_messages_stickerSet.TLdeserialize(((AbstractSerializedData)v4_2), v4_2.readInt32(false), false));
                            }

                            v4_2.reuse();
                            v0 = v5;
                        }
                        catch(Throwable v0_1) {
                            v4_3 = 0;
                            SQLiteCursor v8 = v3_2;
                            v3 = v0_1;
                            v0_3 = v8;
                            goto label_67;
                        }

                        try {
                        label_39:
                            v4_3 = v3_2.b(1);
                            goto label_40;
                        }
                        catch(Throwable v4_1) {
                        label_58:
                            v5 = v0;
                            v0_3 = v3_2;
                            v3 = v4_1;
                        }

                    label_66:
                        v4_3 = 0;
                        goto label_67;
                        try {
                        label_40:
                            v5_2 = DataQuery.calcStickersHash(v0);
                            v2 = v4_3;
                            goto label_50;
                        }
                        catch(Throwable v5_1) {
                            Throwable v8_1 = v5_1;
                            v5 = v0;
                            v0_3 = v3_2;
                            v3 = v8_1;
                        }

                        try {
                        label_67:
                            FileLog.e(v3);
                            if((((SQLiteCursor)v0)) == null) {
                                goto label_70;
                            }
                        }
                        catch(Throwable v1) {
                            goto label_76;
                        }
                    }

                    ((SQLiteCursor)v0).b();
                label_70:
                    v2 = v4_3;
                    v0 = v5;
                    v5_2 = 0;
                    goto label_73;
                    v1 = v0_1;
                    v0_3 = v3_2;
                label_76:
                    if((((SQLiteCursor)v0)) != null) {
                        ((SQLiteCursor)v0).b();
                    }

                    throw v1;
                label_49:
                    v5_2 = 0;
                label_50:
                    if(v3_2 != null) {
                        v3_2.b();
                    }

                label_73:
                    DataQuery.processLoadedStickers(this.val$type, v0, true, v2, v5_2);
                }
            });
        }
        else {
            int v4 = 0;
            if(arg3 == v0) {
                TL_messages_allStickers v5 = new TL_messages_allStickers();
                v5.hash = DataQuery.loadFeaturedHash;
                v0 = DataQuery.featuredStickerSets.size();
                while(v4 < v0) {
                    v5.sets.add(DataQuery.featuredStickerSets.get(v4).set);
                    ++v4;
                }

                DataQuery.processLoadStickersResponse(arg3, v5);
                return;
            }
            else {
                if(arg3 == 0) {
                    v0_1 = new TL_messages_getAllStickers();
                    TL_messages_getAllStickers v1 = v0_1;
                    if(arg5) {
                    }
                    else {
                        v4 = DataQuery.loadHash[arg3];
                    }

                    v1.hash = v4;
                }
                else {
                    TL_messages_getMaskStickers v0_2 = new TL_messages_getMaskStickers();
                    TL_messages_getMaskStickers v1_1 = v0_2;
                    if(arg5) {
                    }
                    else {
                        v4 = DataQuery.loadHash[arg3];
                    }

                    v1_1.hash = v4;
                }

                ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v0_1), new RequestDelegate(arg3, v4) {
                    public void run(TLObject arg1, TL_error arg2) {
                        AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                            public void run() {
                                if((this.val$response instanceof TL_messages_allStickers)) {
                                    DataQuery.processLoadStickersResponse(org.telegram.messenger.DataQuery$25.this.val$type, this.val$response);
                                }
                                else {
                                    DataQuery.processLoadedStickers(org.telegram.messenger.DataQuery$25.this.val$type, null, false, ((int)(System.currentTimeMillis() / 1000)), org.telegram.messenger.DataQuery$25.this.val$hash);
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    public void markFaturedStickersAsRead(boolean arg5) {
        if(this.unreadStickerSets.isEmpty()) {
            return;
        }

        this.unreadStickerSets.clear();
        DataQuery.loadFeaturedHash = this.calcFeaturedStickersHash(DataQuery.featuredStickerSets);
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.featuredStickersDidLoaded, new Object[0]);
        this.putFeaturedStickersToCache(DataQuery.featuredStickerSets, this.unreadStickerSets, this.loadFeaturedDate, DataQuery.loadFeaturedHash);
        if(arg5) {
            ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(new TL_messages_readFeaturedStickers(), new RequestDelegate() {
                public void run(TLObject arg1, TL_error arg2) {
                }
            });
        }
    }

    public void markFaturedStickersByIdAsRead(long arg4) {
        if(this.unreadStickerSets.contains(Long.valueOf(arg4))) {
            if(this.readingStickerSets.contains(Long.valueOf(arg4))) {
            }
            else {
                this.readingStickerSets.add(Long.valueOf(arg4));
                TL_messages_readFeaturedStickers v0 = new TL_messages_readFeaturedStickers();
                v0.id.add(Long.valueOf(arg4));
                ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate() {
                    public void run(TLObject arg1, TL_error arg2) {
                    }
                });
                AndroidUtilities.runOnUIThread(new Runnable(arg4) {
                    public void run() {
                        DataQuery.this.unreadStickerSets.remove(Long.valueOf(this.val$id));
                        DataQuery.this.readingStickerSets.remove(Long.valueOf(this.val$id));
                        DataQuery.loadFeaturedHash = DataQuery.this.calcFeaturedStickersHash(DataQuery.featuredStickerSets);
                        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.featuredStickersDidLoaded, new Object[0]);
                        DataQuery.this.putFeaturedStickersToCache(DataQuery.featuredStickerSets, DataQuery.this.unreadStickerSets, DataQuery.this.loadFeaturedDate, DataQuery.loadFeaturedHash);
                    }
                }, 1000);
            }
        }
    }

    private static void processLoadStickersResponse(int arg16, TL_messages_allStickers arg17) {
        int v7 = arg16;
        TL_messages_allStickers v8 = arg17;
        ArrayList v9 = new ArrayList();
        long v10 = 1000;
        if(v8.sets.isEmpty()) {
            DataQuery.processLoadedStickers(v7, v9, false, ((int)(System.currentTimeMillis() / v10)), v8.hash);
        }
        else {
            LongSparseArray v13 = new LongSparseArray();
            int v14 = 0;
            while(v14 < v8.sets.size()) {
                Object v4 = v8.sets.get(v14);
                Object v0 = DataQuery.stickerSetsById.get(((StickerSet)v4).id);
                if(v0 == null || ((TL_messages_stickerSet)v0).set.hash != ((StickerSet)v4).hash) {
                    v9.add(null);
                    TL_messages_getStickerSet v15 = new TL_messages_getStickerSet();
                    v15.stickerset = new TL_inputStickerSetID();
                    v15.stickerset.id = ((StickerSet)v4).id;
                    v15.stickerset.access_hash = ((StickerSet)v4).access_hash;
                    ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v15), new RequestDelegate(v9, v14, v13, ((StickerSet)v4), arg17, arg16) {
                        public void run(TLObject arg1, TL_error arg2) {
                            AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                                public void run() {
                                    TLObject v0 = this.val$response;
                                    org.telegram.messenger.DataQuery$23.this.val$newStickerArray.set(org.telegram.messenger.DataQuery$23.this.val$index, v0);
                                    org.telegram.messenger.DataQuery$23.this.val$newStickerSets.put(org.telegram.messenger.DataQuery$23.this.val$stickerSet.id, v0);
                                    if(org.telegram.messenger.DataQuery$23.this.val$newStickerSets.size() == org.telegram.messenger.DataQuery$23.this.val$res.sets.size()) {
                                        int v1;
                                        for(v1 = 0; v1 < org.telegram.messenger.DataQuery$23.this.val$newStickerArray.size(); ++v1) {
                                            if(org.telegram.messenger.DataQuery$23.this.val$newStickerArray.get(v1) == null) {
                                                org.telegram.messenger.DataQuery$23.this.val$newStickerArray.remove(v1);
                                            }
                                        }

                                        DataQuery.processLoadedStickers(org.telegram.messenger.DataQuery$23.this.val$type, org.telegram.messenger.DataQuery$23.this.val$newStickerArray, false, ((int)(System.currentTimeMillis() / 1000)), org.telegram.messenger.DataQuery$23.this.val$res.hash);
                                    }
                                }
                            });
                        }
                    });
                }
                else {
                    ((TL_messages_stickerSet)v0).set.archived = ((StickerSet)v4).archived;
                    ((TL_messages_stickerSet)v0).set.installed = ((StickerSet)v4).installed;
                    ((TL_messages_stickerSet)v0).set.official = ((StickerSet)v4).official;
                    v13.put(((TL_messages_stickerSet)v0).set.id, v0);
                    v9.add(v0);
                    if(v13.size() == v8.sets.size()) {
                        DataQuery.processLoadedStickers(v7, v9, false, ((int)(System.currentTimeMillis() / v10)), v8.hash);
                    }
                }

                ++v14;
                v10 = 1000;
            }
        }
    }

    private void processLoadedFeaturedStickers(ArrayList arg10, ArrayList arg11, boolean arg12, int arg13, int arg14) {
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                DataQuery.this.loadingFeaturedStickers = false;
                DataQuery.this.featuredStickersLoaded = true;
            }
        });
        Utilities.stageQueue.postRunnable(new Runnable(arg12, arg10, arg13, arg14, arg11) {
            public void run() {
                long v1 = 1000;
                if(!this.val$cache || Math.abs(System.currentTimeMillis() / v1 - (((long)this.val$date))) < 3600) {
                    if(!this.val$cache && this.val$res == null && this.val$hash == 0) {
                    label_19:
                        org.telegram.messenger.DataQuery$17$1 v0 = new Runnable() {
                            public void run() {
                                if(this.this$1.val$res != null && this.this$1.val$hash != 0) {
                                    DataQuery.loadFeaturedHash = this.this$1.val$hash;
                                }

                                this.this$1.this$0.loadFeaturedStickers(false, false);
                            }
                        };
                        if(this.val$res != null || (this.val$cache)) {
                            v1 = 0;
                        }
                        else {
                        }

                        AndroidUtilities.runOnUIThread(((Runnable)v0), v1);
                        if(this.val$res != null) {
                            goto label_31;
                        }

                        return;
                    }
                }
                else if(this.val$res == null) {
                    goto label_19;
                }
                else {
                    goto label_19;
                }

            label_31:
                int v1_1 = 0;
                if(this.val$res != null) {
                    try {
                        ArrayList v0_2 = new ArrayList();
                        LongSparseArray v2 = new LongSparseArray();
                        while(v1_1 < this.val$res.size()) {
                            Object v3 = this.val$res.get(v1_1);
                            v0_2.add(v3);
                            v2.put(((StickerSetCovered)v3).set.id, v3);
                            ++v1_1;
                        }

                        if(!this.val$cache) {
                            DataQuery.this.putFeaturedStickersToCache(v0_2, this.val$unreadStickers, this.val$date, this.val$hash);
                        }

                        AndroidUtilities.runOnUIThread(new Runnable(v2, v0_2) {
                            public void run() {
                                this.this$1.this$0.unreadStickerSets = this.this$1.val$unreadStickers;
                                this.this$1.this$0.featuredStickerSetsById = this.val$stickerSetsByIdNew;
                                DataQuery.featuredStickerSets = this.val$stickerSetsNew;
                                DataQuery.loadFeaturedHash = this.this$1.val$hash;
                                this.this$1.this$0.loadFeaturedDate = this.this$1.val$date;
                                DataQuery.loadStickers(3, true, false);
                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.featuredStickersDidLoaded, new Object[0]);
                            }
                        });
                    }
                    catch(Throwable v0_1) {
                        FileLog.e(v0_1);
                    }

                    return;
                }

                if(!this.val$cache) {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            this.this$1.this$0.loadFeaturedDate = this.this$1.val$date;
                        }
                    });
                    DataQuery.this.putFeaturedStickersToCache(null, null, this.val$date, 0);
                }
            }
        });
    }

    private void processLoadedMedia(messages_Messages arg14, long arg15, int arg17, int arg18, int arg19, boolean arg20, int arg21, boolean arg22, boolean arg23) {
        messages_Messages v2 = arg14;
        int v0 = ((int)arg15);
        if(!arg20 || !v2.messages.isEmpty() || v0 == 0) {
            if(!arg20) {
                ImageLoader.saveMessagesThumbs(v2.messages);
                MessagesStorage.getInstance(DataQuery.currentAccount).putUsersAndChats(v2.users, v2.chats, true, true);
                this.putMediaDatabase(arg15, arg19, v2.messages, arg18, arg23);
            }

            SparseArray v1 = new SparseArray();
            int v4 = 0;
            int v5;
            for(v5 = 0; v5 < v2.users.size(); ++v5) {
                Object v6 = v2.users.get(v5);
                v1.put(((User)v6).id, v6);
            }

            ArrayList v6_1 = new ArrayList();
            while(v4 < v2.messages.size()) {
                v6_1.add(new MessageObject(DataQuery.currentAccount, v2.messages.get(v4), v1, true));
                ++v4;
            }

            AndroidUtilities.runOnUIThread(new Runnable(arg14, arg20, arg15, v6_1, arg21, arg19, arg23) {
                public void run() {
                    int v0 = this.val$res.count;
                    MessagesController.getInstance(DataQuery.currentAccount).putUsers(this.val$res.users, this.val$fromCache);
                    MessagesController.getInstance(DataQuery.currentAccount).putChats(this.val$res.chats, this.val$fromCache);
                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.mediaDidLoaded, new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(v0), this.val$objects, Integer.valueOf(this.val$classGuid), Integer.valueOf(this.val$type), Boolean.valueOf(this.val$topReached)});
                }
            });
        }
        else {
            this.loadMedia(arg15, arg17, arg18, arg19, false, arg21);
        }
    }

    private void processLoadedMediaCount(int arg10, long arg11, int arg13, int arg14, boolean arg15) {
        AndroidUtilities.runOnUIThread(new Runnable(arg11, arg15, arg10, arg13, arg14) {
            public void run() {
                int v0 = ((int)this.val$uid);
                int v2 = 2;
                int v3 = -1;
                if(this.val$fromCache) {
                    if(this.val$count != v3) {
                        if(this.val$count != 0) {
                        }
                        else if(this.val$type == v2) {
                            goto label_12;
                        }

                        goto label_20;
                    }

                label_12:
                    if(v0 == 0) {
                        goto label_20;
                    }

                    DataQuery.this.getMediaCount(this.val$uid, this.val$type, this.val$classGuid, false);
                }
                else {
                label_20:
                    if(!this.val$fromCache) {
                        DataQuery.this.putMediaCountDatabase(this.val$uid, this.val$type, this.val$count);
                    }

                    NotificationCenter v0_1 = NotificationCenter.getInstance(DataQuery.currentAccount);
                    int v1 = NotificationCenter.mediaCountDidLoaded;
                    Object[] v4 = new Object[4];
                    int v6 = 0;
                    v4[0] = Long.valueOf(this.val$uid);
                    if(!this.val$fromCache || this.val$count != v3) {
                        v6 = this.val$count;
                    }
                    else {
                    }

                    v4[1] = Integer.valueOf(v6);
                    v4[v2] = Boolean.valueOf(this.val$fromCache);
                    v4[3] = Integer.valueOf(this.val$type);
                    v0_1.postNotificationName(v1, v4);
                }
            }
        });
    }

    private void processLoadedRecentDocuments(int arg9, ArrayList arg10, boolean arg11, int arg12) {
        if(arg10 != null) {
            MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg11, arg9, arg10, arg12) {
                public void run() {
                    int v6;
                    int v2;
                    org.telegram.messenger.DataQuery$11 v1 = this;
                    try {
                        SQLiteDatabase v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                        int v3 = 2;
                        if(v1.val$gif) {
                            v2 = MessagesController.getInstance(DataQuery.currentAccount).maxRecentGifsCount;
                        }
                        else if(v1.val$type == v3) {
                            v2 = MessagesController.getInstance(DataQuery.currentAccount).maxFaveStickersCount;
                        }
                        else {
                            v2 = MessagesController.getInstance(DataQuery.currentAccount).maxRecentStickersCount;
                        }

                        v0_1.d();
                        SQLitePreparedStatement v4 = v0_1.a("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        int v5 = v1.val$documents.size();
                        int v7 = 5;
                        int v8 = 4;
                        int v9 = 3;
                        if(v1.val$gif) {
                            v6 = 2;
                        }
                        else if(v1.val$type == 0) {
                            v6 = 3;
                        }
                        else if(v1.val$type == 1) {
                            v6 = 4;
                        }
                        else {
                            v6 = 5;
                        }

                        int v12 = 0;
                        while(v12 < v5) {
                            if(v12 == v2) {
                            }
                            else {
                                Object v13 = v1.val$documents.get(v12);
                                v4.d();
                                StringBuilder v14 = new StringBuilder();
                                v14.append("");
                                int v16 = v12;
                                v14.append(((Document)v13).id);
                                v4.a(1, v14.toString());
                                v4.a(v3, v6);
                                v4.a(v9, "");
                                v4.a(v8, "");
                                v4.a(v7, "");
                                v4.a(6, 0);
                                v4.a(7, 0);
                                v4.a(8, 0);
                                int v11 = 9;
                                int v14_1 = v1.val$date != 0 ? v1.val$date : v5 - v16;
                                v4.a(v11, v14_1);
                                NativeByteBuffer v11_1 = new NativeByteBuffer(((Document)v13).getObjectSize());
                                ((Document)v13).serializeToStream(((AbstractSerializedData)v11_1));
                                v4.a(10, v11_1);
                                v4.b();
                                v11_1.reuse();
                                v12 = v16 + 1;
                                continue;
                            }

                            break;
                        }

                        v4.e();
                        v0_1.e();
                        if(v1.val$documents.size() < v2) {
                            return;
                        }

                        v0_1.d();
                        while(v2 < v1.val$documents.size()) {
                            StringBuilder v3_1 = new StringBuilder();
                            v3_1.append("DELETE FROM web_recent_v3 WHERE id = \'");
                            v3_1.append(v1.val$documents.get(v2).id);
                            v3_1.append("\' AND type = ");
                            v3_1.append(v6);
                            v0_1.a(v3_1.toString()).c().e();
                            ++v2;
                        }

                        v0_1.e();
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            });
        }

        if(arg12 == 0) {
            AndroidUtilities.runOnUIThread(new Runnable(arg11, arg9, arg10) {
                public void run() {
                    String v1;
                    SharedPreferences$Editor v0 = MessagesController.getEmojiSettings(DataQuery.currentAccount).edit();
                    if(this.val$gif) {
                        DataQuery.this.loadingRecentGifs = false;
                        DataQuery.this.recentGifsLoaded = true;
                        v1 = "lastGifLoadTime";
                    }
                    else {
                        DataQuery.loadingRecentStickers[this.val$type] = false;
                        DataQuery.recentStickersLoaded[this.val$type] = true;
                        if(this.val$type == 0) {
                            v1 = "lastStickersLoadTime";
                        }
                        else if(this.val$type == 1) {
                            v1 = "lastStickersLoadTimeMask";
                        }
                        else {
                            v1 = "lastStickersLoadTimeFavs";
                        }
                    }

                    v0.putLong(v1, System.currentTimeMillis()).commit();
                    if(this.val$documents != null) {
                        if(this.val$gif) {
                            DataQuery.this.recentGifs = this.val$documents;
                        }
                        else {
                            DataQuery.recentStickers[this.val$type] = this.val$documents;
                        }

                        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.recentDocumentsDidLoaded, new Object[]{Boolean.valueOf(this.val$gif), Integer.valueOf(this.val$type)});
                    }
                }
            });
        }
    }

    private static void processLoadedStickers(int arg8, ArrayList arg9, boolean arg10, int arg11, int arg12) {
        AndroidUtilities.runOnUIThread(new Runnable(arg8) {
            public void run() {
                DataQuery.loadingStickers[this.val$type] = false;
                DataQuery.stickersLoaded[this.val$type] = true;
            }
        });
        Utilities.stageQueue.postRunnable(new Runnable(arg10, arg9, arg11, arg12, arg8) {
            public void run() {
                int v16;
                ArrayList v12_1;
                Object v11;
                org.telegram.messenger.DataQuery$28 v8 = this;
                long v1 = 1000;
                if(!v8.val$cache || Math.abs(System.currentTimeMillis() / v1 - (((long)v8.val$date))) < 3600) {
                    if(!v8.val$cache && v8.val$res == null && v8.val$hash == 0) {
                    label_20:
                        org.telegram.messenger.DataQuery$28$1 v0 = new Runnable() {
                            public void run() {
                                if(org.telegram.messenger.DataQuery$28.this.val$res != null && org.telegram.messenger.DataQuery$28.this.val$hash != 0) {
                                    DataQuery.loadHash[org.telegram.messenger.DataQuery$28.this.val$type] = org.telegram.messenger.DataQuery$28.this.val$hash;
                                }

                                DataQuery.loadStickers(org.telegram.messenger.DataQuery$28.this.val$type, false, false);
                            }
                        };
                        if(v8.val$res != null || (v8.val$cache)) {
                            v1 = 0;
                        }
                        else {
                        }

                        AndroidUtilities.runOnUIThread(((Runnable)v0), v1);
                        if(v8.val$res != null) {
                            goto label_32;
                        }

                        return;
                    }
                }
                else if(v8.val$res == null) {
                    goto label_20;
                }
                else {
                    goto label_20;
                }

            label_32:
                if(v8.val$res != null) {
                    try {
                        ArrayList v5 = new ArrayList();
                        LongSparseArray v3 = new LongSparseArray();
                        HashMap v4 = new HashMap();
                        LongSparseArray v7 = new LongSparseArray();
                        LongSparseArray v0_2 = new LongSparseArray();
                        HashMap v6 = new HashMap();
                        int v2;
                        for(v2 = 0; v2 < v8.val$res.size(); v2 = v16 + 1) {
                            Object v9 = v8.val$res.get(v2);
                            if(v9 != null) {
                                v5.add(v9);
                                v3.put(((TL_messages_stickerSet)v9).set.id, v9);
                                v4.put(((TL_messages_stickerSet)v9).set.short_name, v9);
                                int v10;
                                for(v10 = 0; v10 < ((TL_messages_stickerSet)v9).documents.size(); ++v10) {
                                    v11 = ((TL_messages_stickerSet)v9).documents.get(v10);
                                    if(v11 != null) {
                                        if((v11 instanceof TL_documentEmpty)) {
                                        }
                                        else {
                                            v0_2.put(((Document)v11).id, v11);
                                        }
                                    }
                                }

                                if(((TL_messages_stickerSet)v9).set.archived) {
                                    goto label_53;
                                }

                                v10 = 0;
                                while(v10 < ((TL_messages_stickerSet)v9).packs.size()) {
                                    v11 = ((TL_messages_stickerSet)v9).packs.get(v10);
                                    if(v11 != null) {
                                        if(((TL_stickerPack)v11).emoticon == null) {
                                        }
                                        else {
                                            ((TL_stickerPack)v11).emoticon = ((TL_stickerPack)v11).emoticon.replace("️", "");
                                            Object v12 = v6.get(((TL_stickerPack)v11).emoticon);
                                            if(v12 == null) {
                                                v12_1 = new ArrayList();
                                                v6.put(((TL_stickerPack)v11).emoticon, v12_1);
                                            }

                                            int v13 = 0;
                                            while(v13 < ((TL_stickerPack)v11).documents.size()) {
                                                Object v14 = ((TL_stickerPack)v11).documents.get(v13);
                                                v16 = v2;
                                                if(v7.indexOfKey(((Long)v14).longValue()) < 0) {
                                                    v7.put(((Long)v14).longValue(), ((TL_stickerPack)v11).emoticon);
                                                }

                                                Object v1_1 = v0_2.get(((Long)v14).longValue());
                                                if(v1_1 != null) {
                                                    v12_1.add(v1_1);
                                                }

                                                ++v13;
                                                v2 = v16;
                                            }
                                        }
                                    }

                                    ++v10;
                                    v2 = v2;
                                }
                            }

                        label_53:
                            v16 = v2;
                        }

                        if(!v8.val$cache) {
                            DataQuery.putStickersToCache(v8.val$type, v5, v8.val$date, v8.val$hash);
                        }

                        AndroidUtilities.runOnUIThread(new Runnable(v3, v4, v5, v6, v7) {
                            public void run() {
                                int v1;
                                for(v1 = 0; v1 < DataQuery.stickerSets[org.telegram.messenger.DataQuery$28.this.val$type].size(); ++v1) {
                                    StickerSet v2 = DataQuery.stickerSets[org.telegram.messenger.DataQuery$28.this.val$type].get(v1).set;
                                    DataQuery.stickerSetsById.remove(v2.id);
                                    DataQuery.installedStickerSetsById.remove(v2.id);
                                    DataQuery.stickerSetsByName.remove(v2.short_name);
                                }

                                for(v1 = 0; true; ++v1) {
                                    int v3 = 3;
                                    if(v1 >= this.val$stickerSetsByIdNew.size()) {
                                        break;
                                    }

                                    DataQuery.stickerSetsById.put(this.val$stickerSetsByIdNew.keyAt(v1), this.val$stickerSetsByIdNew.valueAt(v1));
                                    if(org.telegram.messenger.DataQuery$28.this.val$type != v3) {
                                        DataQuery.installedStickerSetsById.put(this.val$stickerSetsByIdNew.keyAt(v1), this.val$stickerSetsByIdNew.valueAt(v1));
                                    }
                                }

                                DataQuery.stickerSetsByName.putAll(this.val$stickerSetsByNameNew);
                                DataQuery.stickerSets[org.telegram.messenger.DataQuery$28.this.val$type] = this.val$stickerSetsNew;
                                DataQuery.loadHash[org.telegram.messenger.DataQuery$28.this.val$type] = org.telegram.messenger.DataQuery$28.this.val$hash;
                                DataQuery.loadDate[org.telegram.messenger.DataQuery$28.this.val$type] = org.telegram.messenger.DataQuery$28.this.val$date;
                                if(org.telegram.messenger.DataQuery$28.this.val$type == 0) {
                                    DataQuery.allStickers = this.val$allStickersNew;
                                    DataQuery.stickersByEmoji = this.val$stickersByEmojiNew;
                                }
                                else if(org.telegram.messenger.DataQuery$28.this.val$type == v3) {
                                    DataQuery.allStickersFeatured = this.val$allStickersNew;
                                }

                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.stickersDidLoaded, new Object[]{Integer.valueOf(org.telegram.messenger.DataQuery$28.this.val$type)});
                            }
                        });
                    }
                    catch(Throwable v0_1) {
                        FileLog.e(v0_1);
                    }

                    return;
                }

                if(!v8.val$cache) {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            DataQuery.loadDate[org.telegram.messenger.DataQuery$28.this.val$type] = org.telegram.messenger.DataQuery$28.this.val$date;
                        }
                    });
                    DataQuery.putStickersToCache(v8.val$type, null, v8.val$date, 0);
                }
            }
        });
    }

    public void putBotInfo(BotInfo arg3) {
        if(arg3 == null) {
            return;
        }

        this.botInfos.put(arg3.user_id, arg3);
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg3) {
            public void run() {
                try {
                    SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO bot_info(uid, info) VALUES(?, ?)");
                    v0_1.d();
                    NativeByteBuffer v1 = new NativeByteBuffer(this.val$botInfo.getObjectSize());
                    this.val$botInfo.serializeToStream(((AbstractSerializedData)v1));
                    v0_1.a(1, this.val$botInfo.user_id);
                    v0_1.a(2, v1);
                    v0_1.b();
                    v1.reuse();
                    v0_1.e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    public void putBotKeyboard(long arg8, Message arg10) {
        if(arg10 == null) {
            return;
        }

        try {
            SQLiteDatabase v0 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
            Locale v1 = Locale.US;
            Object[] v4 = new Object[1];
            int v6 = 0;
            v4[0] = Long.valueOf(arg8);
            SQLiteCursor v0_1 = v0.b(String.format(v1, "SELECT mid FROM bot_keyboard WHERE uid = %d", v4), new Object[0]);
            if(v0_1.a()) {
                v6 = v0_1.b(0);
            }

            v0_1.b();
            if(v6 >= arg10.id) {
                return;
            }

            SQLitePreparedStatement v0_2 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO bot_keyboard VALUES(?, ?, ?)");
            v0_2.d();
            NativeByteBuffer v1_1 = new NativeByteBuffer(arg10.getObjectSize());
            arg10.serializeToStream(((AbstractSerializedData)v1_1));
            v0_2.a(1, arg8);
            v0_2.a(2, arg10.id);
            v0_2.a(3, v1_1);
            v0_2.b();
            v1_1.reuse();
            v0_2.e();
            AndroidUtilities.runOnUIThread(new Runnable(arg8, arg10) {
                public void run() {
                    Object v0 = DataQuery.this.botKeyboards.get(this.val$did);
                    DataQuery.this.botKeyboards.put(this.val$did, this.val$message);
                    if(v0 != null) {
                        DataQuery.this.botKeyboardsByMids.delete(((Message)v0).id);
                    }

                    DataQuery.this.botKeyboardsByMids.put(this.val$message.id, this.val$did);
                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.botKeyboardDidLoaded, new Object[]{this.val$message, Long.valueOf(this.val$did)});
                }
            });
        }
        catch(Exception v8) {
            FileLog.e(((Throwable)v8));
        }
    }

    private void putFeaturedStickersToCache(ArrayList arg8, ArrayList arg9, int arg10, int arg11) {
        ArrayList v0 = arg8 != null ? new ArrayList(((Collection)arg8)) : null;
        ArrayList v3 = v0;
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(v3, arg9, arg10, arg11) {
            public void run() {
                SQLitePreparedStatement v0_1;
                try {
                    if(this.val$stickersFinal != null) {
                        v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO stickers_featured VALUES(?, ?, ?, ?, ?)");
                        v0_1.d();
                        int v2 = 0;
                        int v3 = 4;
                        int v4 = 0;
                        int v5 = 4;
                        while(v4 < this.val$stickersFinal.size()) {
                            v5 += this.val$stickersFinal.get(v4).getObjectSize();
                            ++v4;
                        }

                        NativeByteBuffer v4_1 = new NativeByteBuffer(v5);
                        NativeByteBuffer v5_1 = new NativeByteBuffer(this.val$unreadStickers.size() * 8 + v3);
                        v4_1.writeInt32(this.val$stickersFinal.size());
                        int v6;
                        for(v6 = 0; v6 < this.val$stickersFinal.size(); ++v6) {
                            this.val$stickersFinal.get(v6).serializeToStream(((AbstractSerializedData)v4_1));
                        }

                        v5_1.writeInt32(this.val$unreadStickers.size());
                        while(v2 < this.val$unreadStickers.size()) {
                            v5_1.writeInt64(this.val$unreadStickers.get(v2).longValue());
                            ++v2;
                        }

                        v0_1.a(1, 1);
                        v0_1.a(2, v4_1);
                        v0_1.a(3, v5_1);
                        v0_1.a(v3, this.val$date);
                        v0_1.a(5, this.val$hash);
                        v0_1.b();
                        v4_1.reuse();
                        v5_1.reuse();
                    }
                    else {
                        v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("UPDATE stickers_featured SET date = ?");
                        v0_1.d();
                        v0_1.a(1, this.val$date);
                        v0_1.b();
                    }

                    v0_1.e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    public void putGroupStickerSet(TL_messages_stickerSet arg4) {
        DataQuery.groupStickerSets.put(arg4.set.id, arg4);
    }

    private void putMediaCountDatabase(long arg9, int arg11, int arg12) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg9, arg11, arg12) {
            public void run() {
                try {
                    SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO media_counts_v2 VALUES(?, ?, ?)");
                    v0_1.d();
                    v0_1.a(1, this.val$uid);
                    v0_1.a(2, this.val$type);
                    v0_1.a(3, this.val$count);
                    v0_1.b();
                    v0_1.e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    private void putMediaDatabase(long arg11, int arg13, ArrayList arg14, int arg15, boolean arg16) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg14, arg16, arg11, arg15, arg13) {
            public void run() {
                int v9;
                int v8;
                long v5;
                MessagesStorage v4_1;
                try {
                    if((this.val$messages.isEmpty()) || (this.val$topReached)) {
                        MessagesStorage.getInstance(DataQuery.currentAccount).doneHolesInMedia(this.val$uid, this.val$max_id, this.val$type);
                        if(this.val$messages.isEmpty()) {
                            return;
                        }
                    }

                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().d();
                    SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
                    Iterator v1 = this.val$messages.iterator();
                    while(v1.hasNext()) {
                        Object v2 = v1.next();
                        if(!DataQuery.canAddMessageToMedia(((Message)v2))) {
                            continue;
                        }

                        long v4 = ((long)((Message)v2).id);
                        if(((Message)v2).to_id.channel_id != 0) {
                            v4 |= (((long)((Message)v2).to_id.channel_id)) << 32;
                        }

                        v0_1.d();
                        NativeByteBuffer v6 = new NativeByteBuffer(((Message)v2).getObjectSize());
                        ((Message)v2).serializeToStream(((AbstractSerializedData)v6));
                        v0_1.a(1, v4);
                        v0_1.a(2, this.val$uid);
                        v0_1.a(3, ((Message)v2).date);
                        v0_1.a(4, this.val$type);
                        v0_1.a(5, v6);
                        v0_1.b();
                        v6.reuse();
                    }

                    v0_1.e();
                    if(!this.val$topReached || this.val$max_id != 0) {
                        int v7 = this.val$topReached ? 1 : this.val$messages.get(this.val$messages.size() - 1).id;
                        if(this.val$max_id != 0) {
                            v4_1 = MessagesStorage.getInstance(DataQuery.currentAccount);
                            v5 = this.val$uid;
                            v8 = this.val$max_id;
                            v9 = this.val$type;
                        }
                        else {
                            v4_1 = MessagesStorage.getInstance(DataQuery.currentAccount);
                            v5 = this.val$uid;
                            v8 = 2147483647;
                            v9 = this.val$type;
                        }

                        v4_1.closeHolesInMedia(v5, v7, v8, v9);
                    }

                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    private static void putStickersToCache(int arg2, ArrayList arg3, int arg4, int arg5) {
        ArrayList v0 = arg3 != null ? new ArrayList(((Collection)arg3)) : null;
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(v0, arg2, arg4, arg5) {
            public void run() {
                SQLitePreparedStatement v0_1;
                try {
                    if(this.val$stickersFinal != null) {
                        v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO stickers_v2 VALUES(?, ?, ?, ?)");
                        v0_1.d();
                        int v2 = 0;
                        int v3 = 4;
                        int v4 = 0;
                        int v5 = 4;
                        while(v4 < this.val$stickersFinal.size()) {
                            v5 += this.val$stickersFinal.get(v4).getObjectSize();
                            ++v4;
                        }

                        NativeByteBuffer v4_1 = new NativeByteBuffer(v5);
                        v4_1.writeInt32(this.val$stickersFinal.size());
                        while(v2 < this.val$stickersFinal.size()) {
                            this.val$stickersFinal.get(v2).serializeToStream(((AbstractSerializedData)v4_1));
                            ++v2;
                        }

                        v0_1.a(1, this.val$type + 1);
                        v0_1.a(2, v4_1);
                        v0_1.a(3, this.val$date);
                        v0_1.a(v3, this.val$hash);
                        v0_1.b();
                        v4_1.reuse();
                    }
                    else {
                        v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("UPDATE stickers_v2 SET date = ?");
                        v0_1.d();
                        v0_1.a(1, this.val$date);
                        v0_1.b();
                    }

                    v0_1.e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    private static void removeEmptyMessages(ArrayList arg3) {
        int v0;
        for(v0 = 0; v0 < arg3.size(); ++v0) {
            Object v1 = arg3.get(v0);
            if(v1 == null || ((v1 instanceof TL_messageEmpty)) || ((((Message)v1).action instanceof TL_messageActionHistoryClear))) {
                arg3.remove(v0);
                --v0;
            }
        }
    }

    public void removeInline(int arg5) {
        int v1;
        for(v1 = 0; v1 < this.inlineBots.size(); ++v1) {
            if(this.inlineBots.get(v1).peer.user_id == arg5) {
                this.inlineBots.remove(v1);
                TL_contacts_resetTopPeerRating v1_1 = new TL_contacts_resetTopPeerRating();
                v1_1.category = new TL_topPeerCategoryBotsInline();
                v1_1.peer = MessagesController.getInstance(DataQuery.currentAccount).getInputPeer(arg5);
                ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v1_1), new RequestDelegate() {
                    public void run(TLObject arg1, TL_error arg2) {
                    }
                });
                this.deletePeer(arg5, 1);
                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
                return;
            }
        }
    }

    private static void removeOffsetAfter(int arg4, int arg5, ArrayList arg6) {
        int v0 = arg6.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg6.get(v1);
            if(((MessageEntity)v2).offset > arg4) {
                ((MessageEntity)v2).offset -= arg5;
            }
        }
    }

    public void removePeer(int arg5) {
        int v1;
        for(v1 = 0; v1 < this.hints.size(); ++v1) {
            if(this.hints.get(v1).peer.user_id == arg5) {
                this.hints.remove(v1);
                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
                TL_contacts_resetTopPeerRating v1_1 = new TL_contacts_resetTopPeerRating();
                v1_1.category = new TL_topPeerCategoryCorrespondents();
                v1_1.peer = MessagesController.getInstance(DataQuery.currentAccount).getInputPeer(arg5);
                this.deletePeer(arg5, 0);
                ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v1_1), new RequestDelegate() {
                    public void run(TLObject arg1, TL_error arg2) {
                    }
                });
                return;
            }
        }
    }

    public void removeRecentGif(Document arg5) {
        this.recentGifs.remove(arg5);
        TL_messages_saveGif v0 = new TL_messages_saveGif();
        v0.id = new TL_inputDocument();
        v0.id.id = arg5.id;
        v0.id.access_hash = arg5.access_hash;
        v0.unsave = true;
        ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate() {
            public void run(TLObject arg1, TL_error arg2) {
            }
        });
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg5) {
            public void run() {
                try {
                    SQLiteDatabase v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase();
                    StringBuilder v1 = new StringBuilder();
                    v1.append("DELETE FROM web_recent_v3 WHERE id = \'");
                    v1.append(this.val$document.id);
                    v1.append("\' AND type = 2");
                    v0_1.a(v1.toString()).c().e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    public void removeStickersSet(Context arg11, StickerSet arg12, int arg13, BaseFragment arg14, boolean arg15) {
        boolean v2 = arg12.masks;
        TL_inputStickerSetID v0 = new TL_inputStickerSetID();
        v0.access_hash = arg12.access_hash;
        v0.id = arg12.id;
        if(arg13 != 0) {
            boolean v11 = false;
            boolean v3 = arg13 == 1 ? true : false;
            arg12.archived = v3;
            int v3_1 = 0;
            while(v3_1 < DataQuery.stickerSets[((int)v2)].size()) {
                Object v4 = DataQuery.stickerSets[((int)v2)].get(v3_1);
                if(((TL_messages_stickerSet)v4).set.id == arg12.id) {
                    DataQuery.stickerSets[((int)v2)].remove(v3_1);
                    if(arg13 == 2) {
                        DataQuery.stickerSets[((int)v2)].add(0, v4);
                    }
                    else {
                        DataQuery.stickerSetsById.remove(((TL_messages_stickerSet)v4).set.id);
                        DataQuery.installedStickerSetsById.remove(((TL_messages_stickerSet)v4).set.id);
                        DataQuery.stickerSetsByName.remove(((TL_messages_stickerSet)v4).set.short_name);
                    }
                }
                else {
                    ++v3_1;
                    continue;
                }

                break;
            }

            DataQuery.loadHash[((int)v2)] = DataQuery.calcStickersHash(DataQuery.stickerSets[((int)v2)]);
            DataQuery.putStickersToCache(((int)v2), DataQuery.stickerSets[((int)v2)], DataQuery.loadDate[((int)v2)], DataQuery.loadHash[((int)v2)]);
            NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.stickersDidLoaded, new Object[]{Integer.valueOf(((int)v2))});
            TL_messages_installStickerSet v12 = new TL_messages_installStickerSet();
            v12.stickerset = ((InputStickerSet)v0);
            if(arg13 == 1) {
                v11 = true;
            }

            v12.archived = v11;
            ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v12), new RequestDelegate(((int)v2), arg13, arg14, arg15) {
                public void run(TLObject arg3, TL_error arg4) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg3) {
                        public void run() {
                            if((this.val$response instanceof TL_messages_stickerSetInstallResultArchive)) {
                                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.needReloadArchivedStickers, new Object[]{Integer.valueOf(this.this$1.val$type)});
                                if(this.this$1.val$hide != 1 && this.this$1.val$baseFragment != null && this.this$1.val$baseFragment.getParentActivity() != null) {
                                    Activity v1 = this.this$1.val$baseFragment.getParentActivity();
                                    BaseFragment v2 = this.this$1.val$showSettings ? this.this$1.val$baseFragment : null;
                                    this.this$1.val$baseFragment.showDialog(new StickersArchiveAlert(((Context)v1), v2, this.val$response.sets).create());
                                }
                            }
                        }
                    });
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            DataQuery.loadStickers(this.this$1.val$type, false, false);
                        }
                    }, 1000);
                }
            });
        }
        else {
            TL_messages_uninstallStickerSet v13 = new TL_messages_uninstallStickerSet();
            v13.stickerset = ((InputStickerSet)v0);
            ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v13), new RequestDelegate(arg12, arg11, ((int)v2)) {
                public void run(TLObject arg1, TL_error arg2) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg2) {
                        public void run() {
                            Toast v1_1;
                            try {
                                if(this.val$error != null) {
                                    v1_1 = Toast.makeText(this.this$1.val$context, LocaleController.getString("ErrorOccurred", 2131624696), 0);
                                }
                                else if(this.this$1.val$stickerSet.masks) {
                                    v1_1 = Toast.makeText(this.this$1.val$context, LocaleController.getString("MasksRemoved", 2131625147), 0);
                                }
                                else {
                                    v1_1 = Toast.makeText(this.this$1.val$context, LocaleController.getString("StickersRemoved", 2131626144), 0);
                                }

                                v1_1.show();
                            }
                            catch(Exception v1) {
                                FileLog.e(((Throwable)v1));
                            }

                            DataQuery.loadStickers(this.this$1.val$type, false, true);
                        }
                    });
                }
            });
        }
    }

    public void reorderStickers(int arg6, ArrayList arg7) {
        Collections.sort(DataQuery.stickerSets[arg6], new Comparator(arg7) {
            public int compare(Object arg1, Object arg2) {
                return this.compare(((TL_messages_stickerSet)arg1), ((TL_messages_stickerSet)arg2));
            }

            public int compare(TL_messages_stickerSet arg4, TL_messages_stickerSet arg5) {
                int v4 = this.val$order.indexOf(Long.valueOf(arg4.set.id));
                int v5 = this.val$order.indexOf(Long.valueOf(arg5.set.id));
                if(v4 > v5) {
                    return 1;
                }

                if(v4 < v5) {
                    return -1;
                }

                return 0;
            }
        });
        DataQuery.loadHash[arg6] = DataQuery.calcStickersHash(DataQuery.stickerSets[arg6]);
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.stickersDidLoaded, new Object[]{Integer.valueOf(arg6)});
        DataQuery.loadStickers(arg6, false, true);
    }

    public void saveDraft(long arg10, DraftMessage arg12, Message arg13, boolean arg14) {
        int v5;
        long v3;
        User v13_1;
        SerializedData v1_1;
        SharedPreferences$Editor v0 = this.preferences.edit();
        if(arg12 != null) {
            if((arg12 instanceof TL_draftMessageEmpty)) {
            }
            else {
                this.drafts.put(arg10, arg12);
                try {
                    v1_1 = new SerializedData(arg12.getObjectSize());
                    arg12.serializeToStream(((AbstractSerializedData)v1_1));
                    v0.putString("" + arg10, Utilities.bytesToHex(v1_1.toByteArray()));
                    v1_1.cleanup();
                }
                catch(Exception v1) {
                    FileLog.e(((Throwable)v1));
                }

                goto label_47;
            }
        }

        this.drafts.remove(arg10);
        this.draftMessages.remove(arg10);
        SharedPreferences$Editor v1_2 = this.preferences.edit();
        v2 = new StringBuilder();
        v2.append("");
        v2.append(arg10);
        v1_2 = v1_2.remove(v2.toString());
        v2 = new StringBuilder();
        v2.append("r_");
        v2.append(arg10);
        v1_2.remove(v2.toString()).commit();
    label_47:
        if(arg13 == null) {
            this.draftMessages.remove(arg10);
            v0.remove("r_" + arg10);
        }
        else {
            this.draftMessages.put(arg10, arg13);
            v1_1 = new SerializedData(arg13.getObjectSize());
            arg13.serializeToStream(((AbstractSerializedData)v1_1));
            v0.putString("r_" + arg10, Utilities.bytesToHex(v1_1.toByteArray()));
            v1_1.cleanup();
        }

        v0.commit();
        if(arg14) {
            if(arg12.reply_to_msg_id != 0 && arg13 == null) {
                int v13 = ((int)arg10);
                Chat v14 = null;
                if(v13 > 0) {
                    v13_1 = MessagesController.getInstance(DataQuery.currentAccount).getUser(Integer.valueOf(v13));
                }
                else {
                    Chat v8 = v14;
                    v14 = MessagesController.getInstance(DataQuery.currentAccount).getChat(Integer.valueOf(-v13));
                    v13_1 = ((User)v8);
                }

                if(v13_1 == null && v14 == null) {
                    goto label_123;
                }

                long v12 = ((long)arg12.reply_to_msg_id);
                if(ChatObject.isChannel(v14)) {
                    v3 = v12 | (((long)v14.id)) << 32;
                    v5 = v14.id;
                }
                else {
                    v3 = v12;
                    v5 = 0;
                }

                MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(v3, v5, arg10) {
                    public void run() {
                        org.telegram.messenger.DataQuery$63$1 v2_1;
                        ConnectionsManager v1_1;
                        TL_channels_getMessages v0_2;
                        Message v0 = null;
                        try {
                            SQLiteCursor v1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().b(String.format(Locale.US, "SELECT data FROM messages WHERE mid = %d", Long.valueOf(this.val$messageIdFinal)), new Object[0]);
                            if(v1.a()) {
                                NativeByteBuffer v2 = v1.g(0);
                                if(v2 != null) {
                                    v0 = Message.TLdeserialize(((AbstractSerializedData)v2), v2.readInt32(false), false);
                                    v0.readAttachPath(((AbstractSerializedData)v2), UserConfig.getInstance(DataQuery.currentAccount).clientUserId);
                                    v2.reuse();
                                }
                            }

                            v1.b();
                            if(v0 == null) {
                                if(this.val$channelIdFinal != 0) {
                                    v0_2 = new TL_channels_getMessages();
                                    v0_2.channel = MessagesController.getInstance(DataQuery.currentAccount).getInputChannel(this.val$channelIdFinal);
                                    v0_2.id.add(Integer.valueOf(((int)this.val$messageIdFinal)));
                                    v1_1 = ConnectionsManager.getInstance(DataQuery.currentAccount);
                                    v2_1 = new RequestDelegate() {
                                        public void run(TLObject arg4, TL_error arg5) {
                                            if(arg5 == null && !((messages_Messages)arg4).messages.isEmpty()) {
                                                this.this$1.this$0.saveDraftReplyMessage(this.this$1.val$did, ((messages_Messages)arg4).messages.get(0));
                                            }
                                        }
                                    };
                                }
                                else {
                                    TL_messages_getMessages v0_3 = new TL_messages_getMessages();
                                    v0_3.id.add(Integer.valueOf(((int)this.val$messageIdFinal)));
                                    v1_1 = ConnectionsManager.getInstance(DataQuery.currentAccount);
                                    org.telegram.messenger.DataQuery$63$2 v2_2 = new RequestDelegate() {
                                        public void run(TLObject arg4, TL_error arg5) {
                                            if(arg5 == null && !((messages_Messages)arg4).messages.isEmpty()) {
                                                this.this$1.this$0.saveDraftReplyMessage(this.this$1.val$did, ((messages_Messages)arg4).messages.get(0));
                                            }
                                        }
                                    };
                                }

                                v1_1.sendRequest(((TLObject)v0_2), ((RequestDelegate)v2_1));
                                return;
                            }

                            DataQuery.this.saveDraftReplyMessage(this.val$did, v0);
                        }
                        catch(Exception v0_1) {
                            FileLog.e(((Throwable)v0_1));
                        }
                    }
                });
            }

        label_123:
            NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.newDraftReceived, new Object[]{Long.valueOf(arg10)});
        }
    }

    public void saveDraft(long arg8, CharSequence arg10, ArrayList arg11, Message arg12, boolean arg13, boolean arg14) {
        TL_draftMessage v0_1;
        if(!TextUtils.isEmpty(arg10) || arg12 != null) {
            v0_1 = new TL_draftMessage();
        }
        else {
            TL_draftMessageEmpty v0 = new TL_draftMessageEmpty();
        }

        ((DraftMessage)v0_1).date = ((int)(System.currentTimeMillis() / 1000));
        String v10 = arg10 == null ? "" : arg10.toString();
        ((DraftMessage)v0_1).message = v10;
        ((DraftMessage)v0_1).no_webpage = arg13;
        if(arg12 != null) {
            ((DraftMessage)v0_1).reply_to_msg_id = arg12.id;
            ((DraftMessage)v0_1).flags |= 1;
        }

        if(arg11 != null && !arg11.isEmpty()) {
            ((DraftMessage)v0_1).entities = arg11;
            ((DraftMessage)v0_1).flags |= 8;
        }

        Object v10_1 = this.drafts.get(arg8);
        if(!arg14) {
            if(v10_1 == null || !((DraftMessage)v10_1).message.equals(((DraftMessage)v0_1).message) || ((DraftMessage)v10_1).reply_to_msg_id != ((DraftMessage)v0_1).reply_to_msg_id || ((DraftMessage)v10_1).no_webpage != ((DraftMessage)v0_1).no_webpage) {
                if(v10_1 != null) {
                }
                else if(!TextUtils.isEmpty(((DraftMessage)v0_1).message)) {
                }
                else if(((DraftMessage)v0_1).reply_to_msg_id == 0) {
                    return;
                }

                goto label_54;
            }

            return;
        }

    label_54:
        this.saveDraft(arg8, ((TL_draftMessageEmpty)v0_1), arg12, false);
        int v8 = ((int)arg8);
        if(v8 != 0) {
            TL_messages_saveDraft v9 = new TL_messages_saveDraft();
            v9.peer = MessagesController.getInstance(DataQuery.currentAccount).getInputPeer(v8);
            if(v9.peer == null) {
                return;
            }
            else {
                v9.message = ((DraftMessage)v0_1).message;
                v9.no_webpage = ((DraftMessage)v0_1).no_webpage;
                v9.reply_to_msg_id = ((DraftMessage)v0_1).reply_to_msg_id;
                v9.entities = ((DraftMessage)v0_1).entities;
                v9.flags = ((DraftMessage)v0_1).flags;
                ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v9), new RequestDelegate() {
                    public void run(TLObject arg1, TL_error arg2) {
                    }
                });
            }
        }

        MessagesController.getInstance(DataQuery.currentAccount).sortDialogs(null);
        NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public void saveDraft(long arg9, CharSequence arg11, ArrayList arg12, Message arg13, boolean arg14) {
        this.saveDraft(arg9, arg11, arg12, arg13, arg14, false);
    }

    private void saveDraftReplyMessage(long arg2, Message arg4) {
        if(arg4 == null) {
            return;
        }

        AndroidUtilities.runOnUIThread(new Runnable(arg2, arg4) {
            public void run() {
                Object v0 = DataQuery.this.drafts.get(this.val$did);
                if(v0 != null && ((DraftMessage)v0).reply_to_msg_id == this.val$message.id) {
                    DataQuery.this.draftMessages.put(this.val$did, this.val$message);
                    SerializedData v0_1 = new SerializedData(this.val$message.getObjectSize());
                    this.val$message.serializeToStream(((AbstractSerializedData)v0_1));
                    SharedPreferences$Editor v1 = DataQuery.this.preferences.edit();
                    StringBuilder v2 = new StringBuilder();
                    v2.append("r_");
                    v2.append(this.val$did);
                    v1.putString(v2.toString(), Utilities.bytesToHex(v0_1.toByteArray())).commit();
                    NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.newDraftReceived, new Object[]{Long.valueOf(this.val$did)});
                    v0_1.cleanup();
                }
            }
        });
    }

    private void savePeer(int arg9, int arg10, double arg11) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg9, arg10, arg11) {
            public void run() {
                try {
                    SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO chat_hints VALUES(?, ?, ?, ?)");
                    v0_1.d();
                    v0_1.a(1, this.val$did);
                    v0_1.a(2, this.val$type);
                    v0_1.a(3, this.val$rating);
                    v0_1.a(4, (((int)System.currentTimeMillis())) / 1000);
                    v0_1.b();
                    v0_1.e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    private void savePinnedMessage(Message arg3) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg3) {
            public void run() {
                try {
                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().d();
                    SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("REPLACE INTO chat_pinned VALUES(?, ?, ?)");
                    NativeByteBuffer v1 = new NativeByteBuffer(this.val$result.getObjectSize());
                    this.val$result.serializeToStream(((AbstractSerializedData)v1));
                    v0_1.d();
                    v0_1.a(1, this.val$result.to_id.channel_id);
                    v0_1.a(2, this.val$result.id);
                    v0_1.a(3, v1);
                    v0_1.b();
                    v1.reuse();
                    v0_1.e();
                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    private void saveReplyMessages(SparseArray arg3, ArrayList arg4) {
        MessagesStorage.getInstance(DataQuery.currentAccount).getStorageQueue().postRunnable(new Runnable(arg4, arg3) {
            public void run() {
                try {
                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().d();
                    SQLitePreparedStatement v0_1 = MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().a("UPDATE messages SET replydata = ? WHERE mid = ?");
                    int v2;
                    for(v2 = 0; v2 < this.val$result.size(); ++v2) {
                        Object v3 = this.val$result.get(v2);
                        Object v4 = this.val$replyMessageOwners.get(((Message)v3).id);
                        if(v4 != null) {
                            NativeByteBuffer v5 = new NativeByteBuffer(((Message)v3).getObjectSize());
                            ((Message)v3).serializeToStream(((AbstractSerializedData)v5));
                            int v3_1;
                            for(v3_1 = 0; v3_1 < ((ArrayList)v4).size(); ++v3_1) {
                                Object v6 = ((ArrayList)v4).get(v3_1);
                                v0_1.d();
                                long v7 = ((long)((MessageObject)v6).getId());
                                if(((MessageObject)v6).messageOwner.to_id.channel_id != 0) {
                                    v7 |= (((long)((MessageObject)v6).messageOwner.to_id.channel_id)) << 32;
                                }

                                v0_1.a(1, v5);
                                v0_1.a(2, v7);
                                v0_1.b();
                            }

                            v5.reuse();
                        }
                    }

                    v0_1.e();
                    MessagesStorage.getInstance(DataQuery.currentAccount).getDatabase().e();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
    }

    private void searchMessagesInChat(String arg20, long arg21, long arg23, int arg25, int arg26, boolean arg27, User arg28) {
        int v18;
        long v15_1;
        String v2_2;
        long v4_1;
        String v1_1;
        Object[] v7;
        int v6;
        NotificationCenter v5;
        Object v4;
        int v14;
        DataQuery v12 = this;
        long v9 = arg23;
        int v8 = arg26;
        User v11 = arg28;
        int v1 = (((int)arg27)) ^ 1;
        if(v12.reqId != 0) {
            ConnectionsManager.getInstance(DataQuery.currentAccount).cancelRequest(v12.reqId, true);
            v12.reqId = 0;
        }

        if(v12.mergeReqId != 0) {
            ConnectionsManager.getInstance(DataQuery.currentAccount).cancelRequest(v12.mergeReqId, true);
            v12.mergeReqId = 0;
        }

        int v13 = 2;
        if(arg20 != null) {
            if(v1 != 0) {
                NotificationCenter.getInstance(DataQuery.currentAccount).postNotificationName(NotificationCenter.chatSearchResultsLoading, new Object[]{Integer.valueOf(arg25)});
                boolean[] v4_2 = v12.messagesSearchEndReached;
                v12.messagesSearchEndReached[1] = false;
                v4_2[0] = false;
                int[] v4_3 = v12.messagesSearchCount;
                v12.messagesSearchCount[1] = 0;
                v4_3[0] = 0;
                v12.searchResultMessages.clear();
                v12.searchResultMessagesMap[0].clear();
                v12.searchResultMessagesMap[1].clear();
            }

            v2_2 = arg20;
            v4_1 = arg21;
            v14 = 0;
        }
        else if(v12.searchResultMessages.isEmpty()) {
            return;
        }
        else {
            v1 = 5;
            int v2 = 4;
            v14 = 3;
            int v15 = 6;
            if(v8 == 1) {
                ++v12.lastReturnedNum;
                if(v12.lastReturnedNum < v12.searchResultMessages.size()) {
                    v4 = v12.searchResultMessages.get(v12.lastReturnedNum);
                    v5 = NotificationCenter.getInstance(DataQuery.currentAccount);
                    v6 = NotificationCenter.chatSearchResultsAvailable;
                    v7 = new Object[v15];
                    v7[0] = Integer.valueOf(arg25);
                    v7[1] = Integer.valueOf(((MessageObject)v4).getId());
                    v7[v13] = Integer.valueOf(this.getMask());
                    v7[v14] = Long.valueOf(((MessageObject)v4).getDialogId());
                    v7[v2] = Integer.valueOf(v12.lastReturnedNum);
                    v7[v1] = Integer.valueOf(v12.messagesSearchCount[0] + v12.messagesSearchCount[1]);
                    v5.postNotificationName(v6, v7);
                    return;
                }
                else {
                    if((v12.messagesSearchEndReached[0]) && v9 == 0 && (v12.messagesSearchEndReached[1])) {
                        --v12.lastReturnedNum;
                        return;
                    }

                    v1_1 = v12.lastSearchQuery;
                    Object v2_1 = v12.searchResultMessages.get(v12.searchResultMessages.size() - 1);
                    if(((MessageObject)v2_1).getDialogId() != arg21 || (v12.messagesSearchEndReached[0])) {
                        v2 = ((MessageObject)v2_1).getDialogId() == v9 ? ((MessageObject)v2_1).getId() : 0;
                        v12.messagesSearchEndReached[1] = false;
                        v4_1 = v9;
                    }
                    else {
                        v2 = ((MessageObject)v2_1).getId();
                        v4_1 = arg21;
                    }

                    v14 = v2;
                    v2_2 = v1_1;
                    v1 = 0;
                }
            }
            else {
                if(v8 == v13) {
                    --v12.lastReturnedNum;
                    if(v12.lastReturnedNum < 0) {
                        v12.lastReturnedNum = 0;
                        return;
                    }
                    else {
                        if(v12.lastReturnedNum >= v12.searchResultMessages.size()) {
                            v12.lastReturnedNum = v12.searchResultMessages.size() - 1;
                        }

                        v4 = v12.searchResultMessages.get(v12.lastReturnedNum);
                        v5 = NotificationCenter.getInstance(DataQuery.currentAccount);
                        v6 = NotificationCenter.chatSearchResultsAvailable;
                        v7 = new Object[v15];
                        v7[0] = Integer.valueOf(arg25);
                        v7[1] = Integer.valueOf(((MessageObject)v4).getId());
                        v7[v13] = Integer.valueOf(this.getMask());
                        v7[v14] = Long.valueOf(((MessageObject)v4).getDialogId());
                        v7[v2] = Integer.valueOf(v12.lastReturnedNum);
                        v7[v1] = Integer.valueOf(v12.messagesSearchCount[0] + v12.messagesSearchCount[1]);
                        v5.postNotificationName(v6, v7);
                    }
                }

                return;
            }
        }

        if(!v12.messagesSearchEndReached[0] || (v12.messagesSearchEndReached[1])) {
            v15_1 = 0;
        }
        else {
            v15_1 = 0;
            if(v9 != v15_1) {
                v4_1 = v9;
            }
        }

        if(v4_1 != arg21 || v1 == 0) {
            v18 = v14;
        }
        else if(v9 != v15_1) {
            InputPeer v1_2 = MessagesController.getInstance(DataQuery.currentAccount).getInputPeer(((int)v9));
            if(v1_2 == null) {
                return;
            }
            else {
                TL_messages_search v14_1 = new TL_messages_search();
                v14_1.peer = v1_2;
                v12.lastMergeDialogId = v9;
                v14_1.limit = 1;
                if(v2_2 != null) {
                }
                else {
                    v2_2 = "";
                }

                v14_1.q = v2_2;
                if(v11 != null) {
                    v14_1.from_id = MessagesController.getInstance(DataQuery.currentAccount).getInputUser(v11);
                    v14_1.flags |= 1;
                }

                v14_1.filter = new TL_inputMessagesFilterEmpty();
                v12.mergeReqId = ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v14_1), new RequestDelegate(arg23, v14_1, arg21, arg25, arg26, arg28) {
                    public void run(TLObject arg1, TL_error arg2) {
                        AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                            public void run() {
                                if(this.this$1.this$0.lastMergeDialogId == this.this$1.val$mergeDialogId) {
                                    this.this$1.this$0.mergeReqId = 0;
                                    if(this.val$response != null) {
                                        TLObject v0 = this.val$response;
                                        this.this$1.this$0.messagesSearchEndReached[1] = ((messages_Messages)v0).messages.isEmpty();
                                        int[] v1 = this.this$1.this$0.messagesSearchCount;
                                        int v0_1 = (v0 instanceof TL_messages_messagesSlice) ? ((messages_Messages)v0).count : ((messages_Messages)v0).messages.size();
                                        v1[1] = v0_1;
                                        this.this$1.this$0.searchMessagesInChat(this.this$1.val$req.q, this.this$1.val$dialog_id, this.this$1.val$mergeDialogId, this.this$1.val$guid, this.this$1.val$direction, true, this.this$1.val$user);
                                    }
                                }
                            }
                        });
                    }
                }, v13);
                return;
            }
        }
        else {
            v18 = v14;
            v12.lastMergeDialogId = 0;
            v12.messagesSearchEndReached[1] = true;
            v12.messagesSearchCount[1] = 0;
        }

        TL_messages_search v13_1 = new TL_messages_search();
        v13_1.peer = MessagesController.getInstance(DataQuery.currentAccount).getInputPeer(((int)v4_1));
        if(v13_1.peer == null) {
            return;
        }

        v13_1.limit = 21;
        v1_1 = v2_2 != null ? v2_2 : "";
        v13_1.q = v1_1;
        v13_1.offset_id = v18;
        if(v11 != null) {
            v13_1.from_id = MessagesController.getInstance(DataQuery.currentAccount).getInputUser(v11);
            v13_1.flags |= 1;
        }

        v13_1.filter = new TL_inputMessagesFilterEmpty();
        int v3 = v12.lastReqId + 1;
        v12.lastReqId = v3;
        v12.lastSearchQuery = v2_2;
        v12.reqId = ConnectionsManager.getInstance(DataQuery.currentAccount).sendRequest(((TLObject)v13_1), new RequestDelegate(v3, v13_1, v4_1, arg21, arg25, arg23, arg28) {
            public void run(TLObject arg1, TL_error arg2) {
                AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                    public void run() {
                        Object[] v10_1;
                        if(this.this$1.val$currentReqId == this.this$1.this$0.lastReqId) {
                            this.this$1.this$0.reqId = 0;
                            if(this.val$response != null) {
                                TLObject v0 = this.val$response;
                                int v2;
                                for(v2 = 0; v2 < ((messages_Messages)v0).messages.size(); ++v2) {
                                    Object v3 = ((messages_Messages)v0).messages.get(v2);
                                    if(((v3 instanceof TL_messageEmpty)) || ((((Message)v3).action instanceof TL_messageActionHistoryClear))) {
                                        ((messages_Messages)v0).messages.remove(v2);
                                        --v2;
                                    }
                                }

                                MessagesStorage.getInstance(DataQuery.currentAccount).putUsersAndChats(((messages_Messages)v0).users, ((messages_Messages)v0).chats, true, true);
                                MessagesController.getInstance(DataQuery.currentAccount).putUsers(((messages_Messages)v0).users, false);
                                MessagesController.getInstance(DataQuery.currentAccount).putChats(((messages_Messages)v0).chats, false);
                                if(this.this$1.val$req.offset_id == 0 && this.this$1.val$queryWithDialogFinal == this.this$1.val$dialog_id) {
                                    this.this$1.this$0.lastReturnedNum = 0;
                                    this.this$1.this$0.searchResultMessages.clear();
                                    this.this$1.this$0.searchResultMessagesMap[0].clear();
                                    this.this$1.this$0.searchResultMessagesMap[1].clear();
                                    this.this$1.this$0.messagesSearchCount[0] = 0;
                                }

                                v2 = 0;
                                int v3_1;
                                for(v3_1 = 0; v2 < Math.min(((messages_Messages)v0).messages.size(), 20); v3_1 = 1) {
                                    MessageObject v5 = new MessageObject(DataQuery.currentAccount, ((messages_Messages)v0).messages.get(v2), false);
                                    this.this$1.this$0.searchResultMessages.add(v5);
                                    SparseArray[] v3_2 = this.this$1.this$0.searchResultMessagesMap;
                                    int v6 = this.this$1.val$queryWithDialogFinal == this.this$1.val$dialog_id ? 0 : 1;
                                    v3_2[v6].put(v5.getId(), v5);
                                    ++v2;
                                }

                                boolean[] v2_1 = this.this$1.this$0.messagesSearchEndReached;
                                int v5_1 = this.this$1.val$queryWithDialogFinal == this.this$1.val$dialog_id ? 0 : 1;
                                boolean v6_1 = ((messages_Messages)v0).messages.size() != 21 ? true : false;
                                v2_1[v5_1] = v6_1;
                                int[] v2_2 = this.this$1.this$0.messagesSearchCount;
                                v5_1 = this.this$1.val$queryWithDialogFinal == this.this$1.val$dialog_id ? 0 : 1;
                                int v0_1 = ((v0 instanceof TL_messages_messagesSlice)) || ((v0 instanceof TL_messages_channelMessages)) ? ((messages_Messages)v0).count : ((messages_Messages)v0).messages.size();
                                v2_2[v5_1] = v0_1;
                                v2 = 5;
                                v5_1 = 4;
                                long v6_2 = 0;
                                int v8 = 3;
                                int v9 = 2;
                                int v10 = 6;
                                if(this.this$1.this$0.searchResultMessages.isEmpty()) {
                                    NotificationCenter v0_2 = NotificationCenter.getInstance(DataQuery.currentAccount);
                                    v3_1 = NotificationCenter.chatSearchResultsAvailable;
                                    v10_1 = new Object[v10];
                                    v10_1[0] = Integer.valueOf(this.this$1.val$guid);
                                    v10_1[1] = Integer.valueOf(0);
                                    v10_1[v9] = Integer.valueOf(this.this$1.this$0.getMask());
                                    v10_1[v8] = Long.valueOf(v6_2);
                                    v10_1[v5_1] = Integer.valueOf(0);
                                    v10_1[v2] = Integer.valueOf(0);
                                    v0_2.postNotificationName(v3_1, v10_1);
                                }
                                else if(v3_1 != 0) {
                                    if(this.this$1.this$0.lastReturnedNum >= this.this$1.this$0.searchResultMessages.size()) {
                                        this.this$1.this$0.lastReturnedNum = this.this$1.this$0.searchResultMessages.size() - 1;
                                    }

                                    Object v0_3 = this.this$1.this$0.searchResultMessages.get(this.this$1.this$0.lastReturnedNum);
                                    NotificationCenter v3_3 = NotificationCenter.getInstance(DataQuery.currentAccount);
                                    int v11 = NotificationCenter.chatSearchResultsAvailable;
                                    v10_1 = new Object[v10];
                                    v10_1[0] = Integer.valueOf(this.this$1.val$guid);
                                    v10_1[1] = Integer.valueOf(((MessageObject)v0_3).getId());
                                    v10_1[v9] = Integer.valueOf(this.this$1.this$0.getMask());
                                    v10_1[v8] = Long.valueOf(((MessageObject)v0_3).getDialogId());
                                    v10_1[v5_1] = Integer.valueOf(this.this$1.this$0.lastReturnedNum);
                                    v10_1[v2] = Integer.valueOf(this.this$1.this$0.messagesSearchCount[0] + this.this$1.this$0.messagesSearchCount[1]);
                                    v3_3.postNotificationName(v11, v10_1);
                                }

                                if(this.this$1.val$queryWithDialogFinal != this.this$1.val$dialog_id) {
                                    return;
                                }

                                if(!this.this$1.this$0.messagesSearchEndReached[0]) {
                                    return;
                                }

                                if(this.this$1.val$mergeDialogId == v6_2) {
                                    return;
                                }

                                if(this.this$1.this$0.messagesSearchEndReached[1]) {
                                    return;
                                }

                                this.this$1.this$0.searchMessagesInChat(this.this$1.this$0.lastSearchQuery, this.this$1.val$dialog_id, this.this$1.val$mergeDialogId, this.this$1.val$guid, 0, true, this.this$1.val$user);
                            }
                        }
                    }
                });
            }
        }, 2);
    }

    public void searchMessagesInChat(String arg11, long arg12, long arg14, int arg16, int arg17, User arg18) {
        this.searchMessagesInChat(arg11, arg12, arg14, arg16, arg17, false, arg18);
    }

    public static void sortEntities(ArrayList arg1) {
        Collections.sort(((List)arg1), DataQuery.entityComparator);
    }

    public CharSequence substring(CharSequence arg2, int arg3, int arg4) {
        if((arg2 instanceof SpannableStringBuilder)) {
            return ((SpannableStringBuilder)arg2).subSequence(arg3, arg4);
        }

        if((arg2 instanceof SpannedString)) {
            return ((SpannedString)arg2).subSequence(arg3, arg4);
        }

        return TextUtils.substring(arg2, arg3, arg4);
    }

    public void uninstallShortcut(long arg6) {
        User v0_4;
        Integer v0_3;
        MessagesController v1_2;
        try {
            if(Build$VERSION.SDK_INT >= 26) {
                Object v0 = ApplicationLoader.applicationContext.getSystemService(ShortcutManager.class);
                ArrayList v1 = new ArrayList();
                v1.add("sdid_" + arg6);
                ((ShortcutManager)v0).removeDynamicShortcuts(((List)v1));
                return;
            }

            int v0_1 = ((int)arg6);
            int v1_1 = ((int)(arg6 >> 32));
            Chat v2_1 = null;
            if(v0_1 == 0) {
                EncryptedChat v0_2 = MessagesController.getInstance(DataQuery.currentAccount).getEncryptedChat(Integer.valueOf(v1_1));
                if(v0_2 == null) {
                    return;
                }
                else {
                    v1_2 = MessagesController.getInstance(DataQuery.currentAccount);
                    v0_3 = Integer.valueOf(v0_2.user_id);
                    goto label_33;
                }
            }
            else if(v0_1 > 0) {
                v1_2 = MessagesController.getInstance(DataQuery.currentAccount);
                v0_3 = Integer.valueOf(v0_1);
            label_33:
                v0_4 = v1_2.getUser(v0_3);
            }
            else if(v0_1 < 0) {
                Chat v4 = v2_1;
                v2_1 = MessagesController.getInstance(DataQuery.currentAccount).getChat(Integer.valueOf(-v0_1));
                v0_4 = ((User)v4);
            }
            else {
                return;
            }

            if(v0_4 == null && v2_1 == null) {
                return;
            }

            String v0_5 = v0_4 != null ? ContactsController.formatName(v0_4.first_name, v0_4.last_name) : v2_1.title;
            Intent v1_3 = new Intent();
            v1_3.putExtra("android.intent.extra.shortcut.INTENT", this.createIntrnalShortcutIntent(arg6));
            v1_3.putExtra("android.intent.extra.shortcut.NAME", v0_5);
            v1_3.putExtra("duplicate", false);
            v1_3.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
            ApplicationLoader.applicationContext.sendBroadcast(v1_3);
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }
}

