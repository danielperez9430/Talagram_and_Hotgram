package org.telegram.messenger;

import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.SQLite.a;
import org.telegram.customization.Model.UserState;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$BotInfo;
import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$ChatParticipant;
import org.telegram.tgnet.TLRPC$ChatParticipants;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$InputChannel;
import org.telegram.tgnet.TLRPC$InputMedia;
import org.telegram.tgnet.TLRPC$InputPeer;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.tgnet.TLRPC$PeerNotifySettings;
import org.telegram.tgnet.TLRPC$Photo;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_channelFull;
import org.telegram.tgnet.TLRPC$TL_channels_deleteMessages;
import org.telegram.tgnet.TLRPC$TL_chatChannelParticipant;
import org.telegram.tgnet.TLRPC$TL_chatFull;
import org.telegram.tgnet.TLRPC$TL_chatInviteEmpty;
import org.telegram.tgnet.TLRPC$TL_chatParticipant;
import org.telegram.tgnet.TLRPC$TL_chatParticipantAdmin;
import org.telegram.tgnet.TLRPC$TL_chatParticipants;
import org.telegram.tgnet.TLRPC$TL_contact;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionScreenshotMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_documentEmpty;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_inputMessageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_message;
import org.telegram.tgnet.TLRPC$TL_messageActionGameScore;
import org.telegram.tgnet.TLRPC$TL_messageActionHistoryClear;
import org.telegram.tgnet.TLRPC$TL_messageActionPaymentSent;
import org.telegram.tgnet.TLRPC$TL_messageActionPinMessage;
import org.telegram.tgnet.TLRPC$TL_messageEncryptedAction;
import org.telegram.tgnet.TLRPC$TL_messageEntityMentionName;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaUnsupported;
import org.telegram.tgnet.TLRPC$TL_messageMediaUnsupported_old;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_message_secret;
import org.telegram.tgnet.TLRPC$TL_messages_botCallbackAnswer;
import org.telegram.tgnet.TLRPC$TL_messages_botResults;
import org.telegram.tgnet.TLRPC$TL_messages_deleteMessages;
import org.telegram.tgnet.TLRPC$TL_messages_dialogs;
import org.telegram.tgnet.TLRPC$TL_peerChannel;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettings;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettingsEmpty_layer77;
import org.telegram.tgnet.TLRPC$TL_photoEmpty;
import org.telegram.tgnet.TLRPC$TL_replyInlineMarkup;
import org.telegram.tgnet.TLRPC$TL_updates_channelDifferenceTooLong;
import org.telegram.tgnet.TLRPC$TL_userStatusLastMonth;
import org.telegram.tgnet.TLRPC$TL_userStatusLastWeek;
import org.telegram.tgnet.TLRPC$TL_userStatusRecently;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$UserProfilePhoto;
import org.telegram.tgnet.TLRPC$UserStatus;
import org.telegram.tgnet.TLRPC$WallPaper;
import org.telegram.tgnet.TLRPC$messages_BotResults;
import org.telegram.tgnet.TLRPC$messages_Dialogs;
import org.telegram.tgnet.TLRPC$messages_Messages;
import org.telegram.tgnet.TLRPC$photos_Photos;
import utils.a.b;

public class MessagesStorage {
    class Hole {
        public int end;
        public int start;
        public int type;

        public Hole(MessagesStorage arg1, int arg2, int arg3) {
            MessagesStorage.this = arg1;
            super();
            this.start = arg2;
            this.end = arg3;
        }

        public Hole(MessagesStorage arg1, int arg2, int arg3, int arg4) {
            MessagesStorage.this = arg1;
            super();
            this.type = arg2;
            this.start = arg3;
            this.end = arg4;
        }
    }

    public interface IntCallback {
        void run(int arg1);
    }

    private static volatile MessagesStorage[] Instance;
    private File cacheFile;
    private int currentAccount;
    private SQLiteDatabase database;
    private int lastDateValue;
    private int lastPtsValue;
    private int lastQtsValue;
    private int lastSavedDate;
    private int lastSavedPts;
    private int lastSavedQts;
    private int lastSavedSeq;
    private int lastSecretVersion;
    private int lastSeqValue;
    private AtomicLong lastTaskId;
    private CountDownLatch openSync;
    private int secretG;
    private byte[] secretPBytes;
    private File shmCacheFile;
    private DispatchQueue storageQueue;
    private File walCacheFile;

    static {
        MessagesStorage.Instance = new MessagesStorage[3];
    }

    public MessagesStorage(int arg4) {
        super();
        this.storageQueue = new DispatchQueue("storageQueue");
        this.lastTaskId = new AtomicLong(System.currentTimeMillis());
        this.lastDateValue = 0;
        this.lastPtsValue = 0;
        this.lastQtsValue = 0;
        this.lastSeqValue = 0;
        this.lastSecretVersion = 0;
        this.secretPBytes = null;
        this.secretG = 0;
        this.lastSavedSeq = 0;
        this.lastSavedPts = 0;
        this.lastSavedDate = 0;
        this.lastSavedQts = 0;
        this.openSync = new CountDownLatch(1);
        this.currentAccount = arg4;
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$l_EMBf4E_fIloSDlPGVY7ULYtZw(this));
    }

    public void addRecentLocalFile(String arg3, String arg4, Document arg5) {
        if(arg3 != null && arg3.length() != 0 && (arg4 != null && arg4.length() != 0 || arg5 != null)) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$uI3pGn2hMrPD4GgXGpEcOb1k3J0(this, arg5, arg3, arg4));
        }
    }

    public static void addUsersAndChatsFromMessage(Message arg4, ArrayList arg5, ArrayList arg6) {
        int v5;
        if(arg4.from_id != 0) {
            if(arg4.from_id > 0) {
                if(!arg5.contains(Integer.valueOf(arg4.from_id))) {
                    arg5.add(Integer.valueOf(arg4.from_id));
                }
            }
            else if(!arg6.contains(Integer.valueOf(-arg4.from_id))) {
                arg6.add(Integer.valueOf(-arg4.from_id));
            }
        }

        if(arg4.via_bot_id != 0 && !arg5.contains(Integer.valueOf(arg4.via_bot_id))) {
            arg5.add(Integer.valueOf(arg4.via_bot_id));
        }

        int v1 = 0;
        if(arg4.action != null) {
            if(arg4.action.user_id != 0 && !arg5.contains(Integer.valueOf(arg4.action.user_id))) {
                arg5.add(Integer.valueOf(arg4.action.user_id));
            }

            if(arg4.action.channel_id != 0 && !arg6.contains(Integer.valueOf(arg4.action.channel_id))) {
                arg6.add(Integer.valueOf(arg4.action.channel_id));
            }

            if(arg4.action.chat_id != 0 && !arg6.contains(Integer.valueOf(arg4.action.chat_id))) {
                arg6.add(Integer.valueOf(arg4.action.chat_id));
            }

            if(arg4.action.users.isEmpty()) {
                goto label_86;
            }

            int v0;
            for(v0 = 0; v0 < arg4.action.users.size(); ++v0) {
                Object v2 = arg4.action.users.get(v0);
                if(!arg5.contains(v2)) {
                    arg5.add(v2);
                }
            }
        }

    label_86:
        if(!arg4.entities.isEmpty()) {
            while(v1 < arg4.entities.size()) {
                Object v0_1 = arg4.entities.get(v1);
                if((v0_1 instanceof TL_messageEntityMentionName)) {
                    v0 = ((TL_messageEntityMentionName)v0_1).user_id;
                    goto label_97;
                }
                else if((v0_1 instanceof TL_inputMessageEntityMentionName)) {
                    v0 = ((TL_inputMessageEntityMentionName)v0_1).user_id.user_id;
                label_97:
                    arg5.add(Integer.valueOf(v0));
                }

                ++v1;
            }
        }

        if(arg4.media != null && arg4.media.user_id != 0 && !arg5.contains(Integer.valueOf(arg4.media.user_id))) {
            arg5.add(Integer.valueOf(arg4.media.user_id));
        }

        if(arg4.fwd_from != null) {
            if(arg4.fwd_from.from_id != 0 && !arg5.contains(Integer.valueOf(arg4.fwd_from.from_id))) {
                arg5.add(Integer.valueOf(arg4.fwd_from.from_id));
            }

            if(arg4.fwd_from.channel_id != 0 && !arg6.contains(Integer.valueOf(arg4.fwd_from.channel_id))) {
                arg6.add(Integer.valueOf(arg4.fwd_from.channel_id));
            }

            if(arg4.fwd_from.saved_from_peer == null) {
                goto label_196;
            }

            if(arg4.fwd_from.saved_from_peer.user_id != 0) {
                if(arg6.contains(Integer.valueOf(arg4.fwd_from.saved_from_peer.user_id))) {
                    goto label_196;
                }

                arg5.add(Integer.valueOf(arg4.fwd_from.saved_from_peer.user_id));
                goto label_196;
            }

            if(arg4.fwd_from.saved_from_peer.channel_id != 0) {
                if(!arg6.contains(Integer.valueOf(arg4.fwd_from.saved_from_peer.channel_id))) {
                    v5 = arg4.fwd_from.saved_from_peer.channel_id;
                }
                else {
                    goto label_196;
                }
            }
            else if(arg4.fwd_from.saved_from_peer.chat_id == 0) {
                goto label_196;
            }
            else if(!arg6.contains(Integer.valueOf(arg4.fwd_from.saved_from_peer.chat_id))) {
                v5 = arg4.fwd_from.saved_from_peer.chat_id;
            }
            else {
                goto label_196;
            }

            arg6.add(Integer.valueOf(v5));
        }

    label_196:
        if(arg4.ttl < 0 && !arg6.contains(Integer.valueOf(-arg4.ttl))) {
            arg6.add(Integer.valueOf(-arg4.ttl));
        }
    }

    public void applyPhoneBookUpdates(String arg3, String arg4) {
        if(arg3.length() == 0 && arg4.length() == 0) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$4WasZhsuFWHuokWw9bSYrSw0o-M(this, arg3, arg4));
    }

    public boolean checkMessageByRandomId(long arg11) {
        boolean[] v7 = new boolean[1];
        CountDownLatch v8 = new CountDownLatch(1);
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$o2-SVUcVB7JV00E_C_LV-Z0NoPI(this, arg11, v7, v8));
        try {
            v8.await();
        }
        catch(Exception v11) {
            FileLog.e(((Throwable)v11));
        }

        return v7[0];
    }

    public boolean checkMessageId(long arg12, int arg14) {
        boolean[] v8 = new boolean[1];
        CountDownLatch v9 = new CountDownLatch(1);
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$z3ocD_e4Q9JgeXOqOLyEhzzWyXs(this, arg12, arg14, v8, v9));
        try {
            v9.await();
        }
        catch(Exception v12) {
            FileLog.e(((Throwable)v12));
        }

        return v8[0];
    }

    public void cleanup(boolean arg3) {
        if(!arg3) {
            this.storageQueue.cleanupQueue();
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$ekOTDfJDLwKOj7vRXd-V4MXUJZc(this, arg3));
    }

    private void cleanupInternal() {
        this.lastDateValue = 0;
        this.lastSeqValue = 0;
        this.lastPtsValue = 0;
        this.lastQtsValue = 0;
        this.lastSecretVersion = 0;
        this.lastSavedSeq = 0;
        this.lastSavedPts = 0;
        this.lastSavedDate = 0;
        this.lastSavedQts = 0;
        byte[] v1 = null;
        this.secretPBytes = v1;
        this.secretG = 0;
        if(this.database != null) {
            this.database.b();
            this.database = ((SQLiteDatabase)v1);
        }

        if(this.cacheFile != null) {
            this.cacheFile.delete();
            this.cacheFile = ((File)v1);
        }

        if(this.walCacheFile != null) {
            this.walCacheFile.delete();
            this.walCacheFile = ((File)v1);
        }

        if(this.shmCacheFile != null) {
            this.shmCacheFile.delete();
            this.shmCacheFile = ((File)v1);
        }
    }

    public void clearDownloadQueue(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$hig2wtSkQmzDEx3PKS1RnbCqCis(this, arg3));
    }

    public void clearSentMedia() {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$dNQ7uYInDURyk--_72CFtvYIUjk(this));
    }

    public void clearUserPhoto(int arg3, long arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$z-qlm9wMgBv-r987i7dbO_O26s0(this, arg3, arg4));
    }

    public void clearUserPhotos(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$6h6ql4nj21Nu7Zv7b-xEfesaDjg(this, arg3));
    }

    public void clearWebRecent(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$qnZrqt7BPH6HuLabn_3pWYi3xtQ(this, arg3));
    }

    public void closeHolesInMedia(long arg20, int arg22, int arg23, int arg24) {
        Object[] v15_2;
        Locale v9_1;
        SQLiteDatabase v8_2;
        Object v0_4;
        MessagesStorage v1 = this;
        long v2 = arg20;
        int v4 = arg22;
        int v5 = arg23;
        int v7 = 6;
        int v8 = 9;
        int v9 = 5;
        int v10 = 4;
        int v11 = 3;
        int v12 = 2;
        if(arg24 < 0) {
            try {
                SQLiteDatabase v15 = v1.database;
                Locale v0_1 = Locale.US;
                Object[] v8_1 = new Object[v8];
                v8_1[0] = Long.valueOf(arg20);
                v8_1[1] = Integer.valueOf(arg22);
                v8_1[v12] = Integer.valueOf(arg23);
                v8_1[v11] = Integer.valueOf(arg22);
                v8_1[v10] = Integer.valueOf(arg23);
                v8_1[v9] = Integer.valueOf(arg22);
                v8_1[v7] = Integer.valueOf(arg23);
                v8_1[7] = Integer.valueOf(arg22);
                v8_1[8] = Integer.valueOf(arg23);
                SQLiteCursor v0_2 = v15.b(String.format(v0_1, "SELECT type, start, end FROM media_holes_v2 WHERE uid = %d AND type >= 0 AND ((end >= %d AND end <= %d) OR (start >= %d AND start <= %d) OR (start >= %d AND end <= %d) OR (start <= %d AND end >= %d))", v8_1), new Object[0]);
                goto label_75;
            label_43:
                SQLiteDatabase v0_3 = v1.database;
                Locale v6 = Locale.US;
                Object[] v7_1 = new Object[10];
                v7_1[0] = Long.valueOf(arg20);
                v7_1[1] = Integer.valueOf(arg24);
                v7_1[v12] = Integer.valueOf(arg22);
                v7_1[v11] = Integer.valueOf(arg23);
                v7_1[v10] = Integer.valueOf(arg22);
                v7_1[v9] = Integer.valueOf(arg23);
                v7_1[6] = Integer.valueOf(arg22);
                v7_1[7] = Integer.valueOf(arg23);
                v7_1[8] = Integer.valueOf(arg22);
                v7_1[9] = Integer.valueOf(arg23);
                v0_2 = v0_3.b(String.format(v6, "SELECT type, start, end FROM media_holes_v2 WHERE uid = %d AND type = %d AND ((end >= %d AND end <= %d) OR (start >= %d AND start <= %d) OR (start >= %d AND end <= %d) OR (start <= %d AND end >= %d))", v7_1), new Object[0]);
            label_75:
                ArrayList v6_1 = null;
                while(v0_2.a()) {
                    if(v6_1 == null) {
                        v6_1 = new ArrayList();
                    }

                    v7 = v0_2.b(0);
                    v8 = v0_2.b(1);
                    int v15_1 = v0_2.b(v12);
                    if(v8 != v15_1 || v8 != 1) {
                        v6_1.add(new Hole(v1, v7, v8, v15_1));
                    }
                    else {
                    }
                }

                v0_2.b();
                if(v6_1 == null) {
                    return;
                }

                v7 = 0;
                while(true) {
                label_95:
                    if(v7 >= v6_1.size()) {
                        return;
                    }

                    v0_4 = v6_1.get(v7);
                    if(v5 >= ((Hole)v0_4).end - 1 && v4 <= ((Hole)v0_4).start + 1) {
                        v8_2 = v1.database;
                        v9_1 = Locale.US;
                        Object[] v11_1 = new Object[v10];
                        v11_1[0] = Long.valueOf(arg20);
                        v11_1[1] = Integer.valueOf(((Hole)v0_4).type);
                        v11_1[v12] = Integer.valueOf(((Hole)v0_4).start);
                        v11_1[3] = Integer.valueOf(((Hole)v0_4).end);
                        v8_2.a(String.format(v9_1, "DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d AND start = %d AND end = %d", v11_1)).c().e();
                        goto label_157;
                    }

                    if(v5 < ((Hole)v0_4).end - 1) {
                        goto label_160;
                    }

                    if(((Hole)v0_4).end == v4) {
                        goto label_157;
                    }

                    goto label_130;
                }
            }
            catch(Exception v0) {
                goto label_240;
            }
        }
        else {
            goto label_43;
        }

        goto label_75;
        try {
        label_130:
            v8_2 = v1.database;
            v9_1 = Locale.US;
            v15_2 = new Object[5];
            v15_2[0] = Integer.valueOf(arg22);
            v15_2[1] = Long.valueOf(arg20);
            v15_2[v12] = Integer.valueOf(((Hole)v0_4).type);
            v15_2[3] = Integer.valueOf(((Hole)v0_4).start);
            v15_2[4] = Integer.valueOf(((Hole)v0_4).end);
            v8_2.a(String.format(v9_1, "UPDATE media_holes_v2 SET end = %d WHERE uid = %d AND type = %d AND start = %d AND end = %d", v15_2)).c().e();
            goto label_157;
        }
        catch(Exception v0) {
            goto label_156;
        }

        try {
        label_160:
            if(v4 > ((Hole)v0_4).start + 1) {
                goto label_192;
            }

            if(((Hole)v0_4).start == v5) {
                goto label_157;
            }
        }
        catch(Exception v0) {
            goto label_240;
        }

        try {
            v8_2 = v1.database;
            v9_1 = Locale.US;
            v15_2 = new Object[5];
            v15_2[0] = Integer.valueOf(arg23);
            v15_2[1] = Long.valueOf(arg20);
            v15_2[v12] = Integer.valueOf(((Hole)v0_4).type);
            v15_2[3] = Integer.valueOf(((Hole)v0_4).start);
            v15_2[4] = Integer.valueOf(((Hole)v0_4).end);
            v8_2.a(String.format(v9_1, "UPDATE media_holes_v2 SET start = %d WHERE uid = %d AND type = %d AND start = %d AND end = %d", v15_2)).c().e();
            goto label_157;
        }
        catch(Exception v0) {
        }

        try {
        label_156:
            FileLog.e(((Throwable)v0));
        label_157:
            v10 = 4;
            goto label_237;
        label_192:
            v8_2 = v1.database;
            v9_1 = Locale.US;
            v15_2 = new Object[4];
            v15_2[0] = Long.valueOf(arg20);
            v15_2[1] = Integer.valueOf(((Hole)v0_4).type);
            v15_2[v12] = Integer.valueOf(((Hole)v0_4).start);
            v15_2[3] = Integer.valueOf(((Hole)v0_4).end);
            v8_2.a(String.format(v9_1, "DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d AND start = %d AND end = %d", v15_2)).c().e();
            SQLitePreparedStatement v8_3 = v1.database.a("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
            v8_3.d();
            v8_3.a(1, v2);
            v8_3.a(v12, ((Hole)v0_4).type);
            v8_3.a(3, ((Hole)v0_4).start);
            v8_3.a(4, v4);
            v8_3.b();
            v8_3.d();
            v8_3.a(1, v2);
            v8_3.a(v12, ((Hole)v0_4).type);
            v8_3.a(3, v5);
            v10 = 4;
            v8_3.a(v10, ((Hole)v0_4).end);
            v8_3.b();
            v8_3.e();
        }
        catch(Exception v0) {
            goto label_240;
        }

    label_237:
        ++v7;
        goto label_95;
    label_240:
        FileLog.e(((Throwable)v0));
    }

    private void closeHolesInTable(String arg18, long arg19, int arg21, int arg22) {
        StringBuilder v13_1;
        Locale v10;
        SQLiteDatabase v9_2;
        Object v0_3;
        int v8_2;
        int v14;
        MessagesStorage v1 = this;
        String v2 = arg18;
        long v3 = arg19;
        int v5 = arg21;
        int v6 = arg22;
        try {
            SQLiteDatabase v0_1 = v1.database;
            Locale v7 = Locale.US;
            String v8_1 = "SELECT start, end FROM " + v2 + " WHERE uid = %d AND ((end >= %d AND end <= %d) OR (start >= %d AND start <= %d) OR (start >= %d AND end <= %d) OR (start <= %d AND end >= %d))";
            Object[] v9 = new Object[9];
            v9[0] = Long.valueOf(arg19);
            v9[1] = Integer.valueOf(arg21);
            v9[2] = Integer.valueOf(arg22);
            v14 = 3;
            v9[v14] = Integer.valueOf(arg21);
            v9[4] = Integer.valueOf(arg22);
            v9[5] = Integer.valueOf(arg21);
            v9[6] = Integer.valueOf(arg22);
            v9[7] = Integer.valueOf(arg21);
            v9[8] = Integer.valueOf(arg22);
            SQLiteCursor v0_2 = v0_1.b(String.format(v7, v8_1, v9), new Object[0]);
            ArrayList v7_1 = null;
            while(v0_2.a()) {
                if(v7_1 == null) {
                    v7_1 = new ArrayList();
                }

                v8_2 = v0_2.b(0);
                int v9_1 = v0_2.b(1);
                if(v8_2 == v9_1 && v8_2 == 1) {
                    continue;
                }

                v7_1.add(new Hole(v1, v8_2, v9_1));
            }

            v0_2.b();
            if(v7_1 == null) {
                return;
            }

            v8_2 = 0;
            while(true) {
            label_65:
                if(v8_2 >= v7_1.size()) {
                    return;
                }

                v0_3 = v7_1.get(v8_2);
                if(v6 >= ((Hole)v0_3).end - 1 && v5 <= ((Hole)v0_3).start + 1) {
                    v9_2 = v1.database;
                    v10 = Locale.US;
                    String v13 = "DELETE FROM " + v2 + " WHERE uid = %d AND start = %d AND end = %d";
                    v9_2.a(String.format(v10, v13, Long.valueOf(arg19), Integer.valueOf(((Hole)v0_3).start), Integer.valueOf(((Hole)v0_3).end))).c().e();
                    goto label_135;
                }

                if(v6 < ((Hole)v0_3).end - 1) {
                    goto label_138;
                }

                if(((Hole)v0_3).end == v5) {
                    goto label_135;
                }

                break;
            }
        }
        catch(Exception v0) {
            goto label_230;
        }

        try {
            v9_2 = v1.database;
            v10 = Locale.US;
            v13_1 = new StringBuilder();
            v13_1.append("UPDATE ");
            v13_1.append(v2);
            v13_1.append(" SET end = %d WHERE uid = %d AND start = %d AND end = %d");
            v9_2.a(String.format(v10, v13_1.toString(), Integer.valueOf(arg21), Long.valueOf(arg19), Integer.valueOf(((Hole)v0_3).start), Integer.valueOf(((Hole)v0_3).end))).c().e();
            goto label_135;
        }
        catch(Exception v0) {
            goto label_134;
        }

        try {
        label_138:
            if(v5 > ((Hole)v0_3).start + 1) {
                goto label_174;
            }

            if(((Hole)v0_3).start == v6) {
                goto label_135;
            }
        }
        catch(Exception v0) {
            goto label_230;
        }

        try {
            v9_2 = v1.database;
            v10 = Locale.US;
            v13_1 = new StringBuilder();
            v13_1.append("UPDATE ");
            v13_1.append(v2);
            v13_1.append(" SET start = %d WHERE uid = %d AND start = %d AND end = %d");
            v9_2.a(String.format(v10, v13_1.toString(), Integer.valueOf(arg22), Long.valueOf(arg19), Integer.valueOf(((Hole)v0_3).start), Integer.valueOf(((Hole)v0_3).end))).c().e();
            goto label_135;
        }
        catch(Exception v0) {
        }

        try {
        label_134:
            FileLog.e(((Throwable)v0));
        label_135:
            goto label_225;
        label_174:
            v9_2 = v1.database;
            v10 = Locale.US;
            v13_1 = new StringBuilder();
            v13_1.append("DELETE FROM ");
            v13_1.append(v2);
            v13_1.append(" WHERE uid = %d AND start = %d AND end = %d");
            v9_2.a(String.format(v10, v13_1.toString(), Long.valueOf(arg19), Integer.valueOf(((Hole)v0_3).start), Integer.valueOf(((Hole)v0_3).end))).c().e();
            v9_2 = v1.database;
            StringBuilder v10_1 = new StringBuilder();
            v10_1.append("REPLACE INTO ");
            v10_1.append(v2);
            v10_1.append(" VALUES(?, ?, ?)");
            SQLitePreparedStatement v9_3 = v9_2.a(v10_1.toString());
            v9_3.d();
            v9_3.a(1, v3);
            v9_3.a(2, ((Hole)v0_3).start);
            v9_3.a(3, v5);
            v9_3.b();
            v9_3.d();
            v9_3.a(1, v3);
            v9_3.a(2, v6);
            v9_3.a(3, ((Hole)v0_3).end);
            v9_3.b();
            v9_3.e();
        }
        catch(Exception v0) {
            goto label_230;
        }

    label_225:
        ++v8_2;
        v14 = 3;
        goto label_65;
    label_230:
        FileLog.e(((Throwable)v0));
    }

    public static void createFirstHoles(long arg5, SQLitePreparedStatement arg7, SQLitePreparedStatement arg8, int arg9) {
        arg7.d();
        arg7.a(1, arg5);
        int v2 = arg9 == 1 ? 1 : 0;
        int v3 = 2;
        arg7.a(v3, v2);
        v2 = 3;
        arg7.a(v2, arg9);
        arg7.b();
        int v7;
        for(v7 = 0; v7 < 5; ++v7) {
            arg8.d();
            arg8.a(1, arg5);
            arg8.a(v3, v7);
            int v4 = arg9 == 1 ? 1 : 0;
            arg8.a(v2, v4);
            arg8.a(4, arg9);
            arg8.b();
        }
    }

    public long createPendingTask(NativeByteBuffer arg5) {
        if(arg5 == null) {
            return 0;
        }

        long v0 = this.lastTaskId.getAndAdd(1);
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$TxzHvDLT8O_Bs3_-p-EFkrwY3ws(this, v0, arg5));
        return v0;
    }

    public void createTaskForMid(int arg12, int arg13, int arg14, int arg15, int arg16, boolean arg17) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$I-ew0okH9cQLXsVNtPtWba8DgxQ(this, arg14, arg15, arg16, arg12, arg13, arg17));
    }

    public void createTaskForSecretChat(int arg10, int arg11, int arg12, int arg13, ArrayList arg14) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$f-oPeHbKnZ5OJ3Cw1RIF2yX74QY(this, arg14, arg10, arg13, arg11, arg12));
    }

    public void deleteBlockedUser(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$M-FwDWj6LmGja7eJuhjyNZwcUrQ(this, arg3));
    }

    public void deleteContacts(ArrayList arg3) {
        if(arg3 != null) {
            if(arg3.isEmpty()) {
            }
            else {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$TW-eQEYbB_QNmU6yA_z3GD5x19E(this, arg3));
            }
        }
    }

    public void deleteDialog(long arg3, int arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$mEVnY-MIUAgD6prbVauOiLPYlYI(this, arg5, arg3));
    }

    public void deleteUserChannelHistory(int arg3, int arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$JWiWbN0BUKrnUUULW9pjsU0EcFs(this, arg3, arg4));
    }

    public void doneHolesInMedia(long arg10, int arg12, int arg13) {
        Object[] v7;
        String v6_1;
        Locale v5_1;
        SQLitePreparedStatement v12_1;
        Object[] v6;
        String v5;
        Locale v13;
        SQLiteDatabase v12;
        int v0 = 4;
        int v1 = 3;
        int v2 = 2;
        int v3 = 0;
        if(arg13 == -1) {
            if(arg12 == 0) {
                v12 = this.database;
                v13 = Locale.US;
                v5 = "DELETE FROM media_holes_v2 WHERE uid = %d";
                v6 = new Object[]{Long.valueOf(arg10)};
            }
            else {
                v12 = this.database;
                v13 = Locale.US;
                v5 = "DELETE FROM media_holes_v2 WHERE uid = %d AND start = 0";
                v6 = new Object[]{Long.valueOf(arg10)};
            }

            v12.a(String.format(v13, v5, v6)).c().e();
            v12_1 = this.database.a("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
            while(v3 < 5) {
                v12_1.d();
                v12_1.a(1, arg10);
                v12_1.a(v2, v3);
                v12_1.a(v1, 1);
                v12_1.a(v0, 1);
                v12_1.b();
                ++v3;
            }
        }
        else {
            if(arg12 == 0) {
                v12 = this.database;
                v5_1 = Locale.US;
                v6_1 = "DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d";
                v7 = new Object[v2];
                v7[0] = Long.valueOf(arg10);
                v7[1] = Integer.valueOf(arg13);
            }
            else {
                v12 = this.database;
                v5_1 = Locale.US;
                v6_1 = "DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d AND start = 0";
                v7 = new Object[v2];
                v7[0] = Long.valueOf(arg10);
                v7[1] = Integer.valueOf(arg13);
            }

            v12.a(String.format(v5_1, v6_1, v7)).c().e();
            v12_1 = this.database.a("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
            v12_1.d();
            v12_1.a(1, arg10);
            v12_1.a(v2, arg13);
            v12_1.a(v1, 1);
            v12_1.a(v0, 1);
            v12_1.b();
        }

        v12_1.e();
    }

    private void doneHolesInTable(String arg7, long arg8, int arg10) {
        Object[] v4;
        String v3_1;
        Locale v2;
        SQLiteDatabase v10;
        if(arg10 == 0) {
            v10 = this.database;
            v2 = Locale.US;
            v3_1 = "DELETE FROM " + arg7 + " WHERE uid = %d";
            v4 = new Object[]{Long.valueOf(arg8)};
        }
        else {
            v10 = this.database;
            v2 = Locale.US;
            v3_1 = "DELETE FROM " + arg7 + " WHERE uid = %d AND start = 0";
            v4 = new Object[]{Long.valueOf(arg8)};
        }

        v10.a(String.format(v2, v3_1, v4)).c().e();
        v10 = this.database;
        StringBuilder v0 = new StringBuilder();
        v0.append("REPLACE INTO ");
        v0.append(arg7);
        v0.append(" VALUES(?, ?, ?)");
        SQLitePreparedStatement v7 = v10.a(v0.toString());
        v7.d();
        v7.a(1, arg8);
        v7.a(2, 1);
        v7.a(3, 1);
        v7.b();
        v7.e();
    }

    public void emptyMessagesMedia(ArrayList arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$x5iHolISOw_GOUIZsiT19WMFpwM(this, arg3));
    }

    private void ensureOpened() {
        try {
            this.openSync.await();
            return;
        }
        catch(Throwable ) {
            return;
        }
    }

    private void fixNotificationSettings() {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$H7ZjmdIrsBPnbbqD1cn5Lti86iY(this));
    }

    private void fixUnsupportedMedia(Message arg5) {
        if(arg5 == null) {
            return;
        }

        byte v1 = 85;
        if((arg5.media instanceof TL_messageMediaUnsupported_old)) {
            if(arg5.media.bytes.length == 0) {
                arg5.media.bytes = new byte[1];
                arg5.media.bytes[0] = v1;
            }
        }
        else if((arg5.media instanceof TL_messageMediaUnsupported)) {
            arg5.media = new TL_messageMediaUnsupported_old();
            arg5.media.bytes = new byte[1];
            arg5.media.bytes[0] = v1;
            arg5.flags |= 512;
        }
    }

    private String formatUserSearchName(User arg3) {
        StringBuilder v0 = new StringBuilder("");
        if(arg3.first_name != null && arg3.first_name.length() > 0) {
            v0.append(arg3.first_name);
        }

        if(arg3.last_name != null && arg3.last_name.length() > 0) {
            if(v0.length() > 0) {
                v0.append(" ");
            }

            v0.append(arg3.last_name);
        }

        v0.append(";;;");
        if(arg3.username != null && arg3.username.length() > 0) {
            v0.append(arg3.username);
        }

        return v0.toString().toLowerCase();
    }

    public void getBlockedUsers() {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$UIVEkSGGCiOkuYVg-Xphyltw8FA(this));
    }

    public void getBotCache(String arg4, RequestDelegate arg5) {
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$EcBgQVLQAQLXJaBkpBIsI66qpMs(this, ConnectionsManager.getInstance(this.currentAccount).getCurrentTime(), arg4, arg5));
            }
        }
    }

    public void getCachedPhoneBook(boolean arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$poHNT95Nh8sxKNeRqqdJra1ek3g(this, arg3));
    }

    public int getChannelPtsSync(int arg6) {
        CountDownLatch v0 = new CountDownLatch(1);
        Integer[] v1 = new Integer[]{Integer.valueOf(0)};
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$oPIIibM1gl8YdV9TILSwdDpVsS0(this, arg6, v1, v0));
        try {
            v0.await();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }

        return v1[0].intValue();
    }

    public Chat getChat(int arg5) {
        Object v0_1;
        Chat v0 = null;
        try {
            ArrayList v1 = new ArrayList();
            this.getChatsInternal("" + arg5, v1);
            if(v1.isEmpty()) {
                goto label_18;
            }

            v0_1 = v1.get(0);
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

    label_18:
        return ((Chat)v0_1);
    }

    public Chat getChatSync(int arg5) {
        CountDownLatch v0 = new CountDownLatch(1);
        Chat[] v1 = new Chat[1];
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$580dnN4e5LL-Iyx4pJ74fg-7XfY(this, v1, arg5, v0));
        try {
            v0.await();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

        return v1[0];
    }

    public void getChatsInternal(String arg6, ArrayList arg7) {
        if(arg6 != null && arg6.length() != 0 && arg7 != null) {
            SQLiteCursor v6 = this.database.b(String.format(Locale.US, "SELECT data FROM chats WHERE uid IN(%s)", arg6), new Object[0]);
            while(v6.a()) {
                try {
                    NativeByteBuffer v0_1 = v6.g(0);
                    if(v0_1 == null) {
                        continue;
                    }

                    Chat v1 = Chat.TLdeserialize(((AbstractSerializedData)v0_1), v0_1.readInt32(false), false);
                    v0_1.reuse();
                    if(v1 == null) {
                        continue;
                    }

                    arg7.add(v1);
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }

            v6.b();
        }
    }

    public void getContacts() {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$maFfwvZ-oVByUpXp-3mSnxIlhTs(this));
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public long getDatabaseSize() {
        long v1 = 0;
        if(this.cacheFile != null) {
            v1 += this.cacheFile.length();
        }

        if(this.shmCacheFile != null) {
            v1 += this.shmCacheFile.length();
        }

        return v1;
    }

    public void getDialogPhotos(int arg10, int arg11, long arg12, int arg14) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$CjGmzA-wP9sEOX3iVkVvyQnFJkg(this, arg12, arg10, arg11, arg14));
    }

    public int getDialogReadMax(boolean arg13, long arg14) {
        CountDownLatch v7 = new CountDownLatch(1);
        Integer[] v8 = new Integer[]{Integer.valueOf(0)};
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$4hASFpoq9MHo0jTj74FVcVv0gX8(this, arg13, arg14, v8, v7));
        try {
            v7.await();
        }
        catch(Exception v13) {
            FileLog.e(((Throwable)v13));
        }

        return v8[0].intValue();
    }

    public void getDialogs(int arg3, int arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$rFrec1Tz5RVJGiT3b5dLhz13OhQ(this, arg3, arg4));
    }

    public void getDownloadQueue(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$kde7V9Zmst601Zzc9VKLorl6q1I(this, arg3));
    }

    public EncryptedChat getEncryptedChat(int arg5) {
        ArrayList v0 = null;
        try {
            ArrayList v1 = new ArrayList();
            this.getEncryptedChatsInternal("" + arg5, v1, v0);
            if(v1.isEmpty()) {
                goto label_18;
            }

            Object v0_1 = v1.get(0);
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

    label_18:
        return ((EncryptedChat)v0);
    }

    public void getEncryptedChat(int arg3, CountDownLatch arg4, ArrayList arg5) {
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$bn95bz1kHHAzJ29ngfD5rsIZCys(this, arg3, arg5, arg4));
            }
        }
    }

    public void getEncryptedChatsInternal(String arg9, ArrayList arg10, ArrayList arg11) {
        if(arg9 != null && arg9.length() != 0 && arg10 != null) {
            SQLiteCursor v9 = this.database.b(String.format(Locale.US, "SELECT data, user, g, authkey, ttl, layer, seq_in, seq_out, use_count, exchange_id, key_date, fprint, fauthkey, khash, in_seq_no, admin_id, mtproto_seq FROM enc_chats WHERE uid IN(%s)", arg9), new Object[0]);
            while(v9.a()) {
                try {
                    NativeByteBuffer v0_1 = v9.g(0);
                    if(v0_1 == null) {
                        continue;
                    }

                    EncryptedChat v1 = EncryptedChat.TLdeserialize(((AbstractSerializedData)v0_1), v0_1.readInt32(false), false);
                    v0_1.reuse();
                    if(v1 == null) {
                        continue;
                    }

                    v1.user_id = v9.b(1);
                    if(arg11 != null && !arg11.contains(Integer.valueOf(v1.user_id))) {
                        arg11.add(Integer.valueOf(v1.user_id));
                    }

                    v1.a_or_b = v9.f(2);
                    v1.auth_key = v9.f(3);
                    v1.ttl = v9.b(4);
                    v1.layer = v9.b(5);
                    v1.seq_in = v9.b(6);
                    v1.seq_out = v9.b(7);
                    int v0_2 = v9.b(8);
                    v1.key_use_count_in = ((short)(v0_2 >> 16));
                    v1.key_use_count_out = ((short)v0_2);
                    v1.exchange_id = v9.d(9);
                    v1.key_create_date = v9.b(10);
                    v1.future_key_fingerprint = v9.d(11);
                    v1.future_auth_key = v9.f(12);
                    v1.key_hash = v9.f(13);
                    v1.in_seq_no = v9.b(14);
                    v0_2 = v9.b(15);
                    if(v0_2 != 0) {
                        v1.admin_id = v0_2;
                    }

                    v1.mtproto_seq = v9.b(16);
                    arg10.add(v1);
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }

            v9.b();
        }
    }

    public static MessagesStorage getInstance(int arg3) {
        MessagesStorage v0 = MessagesStorage.Instance[arg3];
        if(v0 == null) {
            Class v1 = MessagesStorage.class;
            __monitor_enter(v1);
            try {
                v0 = MessagesStorage.Instance[arg3];
                if(v0 == null) {
                    MessagesStorage[] v0_1 = MessagesStorage.Instance;
                    MessagesStorage v2 = new MessagesStorage(arg3);
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

    public int getLastDateValue() {
        this.ensureOpened();
        return this.lastDateValue;
    }

    public int getLastPtsValue() {
        this.ensureOpened();
        return this.lastPtsValue;
    }

    public int getLastQtsValue() {
        this.ensureOpened();
        return this.lastQtsValue;
    }

    public int getLastSecretVersion() {
        this.ensureOpened();
        return this.lastSecretVersion;
    }

    public int getLastSeqValue() {
        this.ensureOpened();
        return this.lastSeqValue;
    }

    private int getMessageMediaType(Message arg5) {
        if((arg5 instanceof TL_message_secret)) {
            if((!(arg5.media instanceof TL_messageMediaPhoto) && !MessageObject.isGifMessage(arg5) || (arg5.ttl <= 0 || arg5.ttl > 60)) && (!MessageObject.isVoiceMessage(arg5) && !MessageObject.isVideoMessage(arg5))) {
                if(MessageObject.isRoundVideoMessage(arg5)) {
                }
                else {
                    if(!(arg5.media instanceof TL_messageMediaPhoto) && !MessageObject.isVideoMessage(arg5)) {
                        return -1;
                    }

                    return 0;
                }
            }

            return 1;
        }
        else {
            if(((arg5 instanceof TL_message)) && (((arg5.media instanceof TL_messageMediaPhoto)) || ((arg5.media instanceof TL_messageMediaDocument))) && arg5.media.ttl_seconds != 0) {
                return 1;
            }

            if(!(arg5.media instanceof TL_messageMediaPhoto)) {
                if(MessageObject.isVideoMessage(arg5)) {
                }
                else {
                    return -1;
                }
            }

            return 0;
        }

        return -1;
    }

    public void getMessages(long arg16, int arg18, int arg19, int arg20, int arg21, int arg22, int arg23, boolean arg24, int arg25) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$Lec9mntLZdTi44fiMjdY09ouZVI(this, arg18, arg19, arg24, arg16, arg23, arg21, arg20, arg22, arg25));
    }

    public void getNewTask(ArrayList arg2, int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$NjHNF40J5UIR40aKMtOvKQDo4Qg(this, arg2));
    }

    public int getSecretG() {
        this.ensureOpened();
        return this.secretG;
    }

    public byte[] getSecretPBytes() {
        this.ensureOpened();
        return this.secretPBytes;
    }

    public TLObject getSentFile(String arg12, int arg13) {
        Object v0_1;
        TLObject v0 = null;
        if(arg12 != null && !arg12.toLowerCase().endsWith("attheme")) {
            CountDownLatch v1 = new CountDownLatch(1);
            ArrayList v8 = new ArrayList();
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$VVH7ZDr2peRN4FRQoCQ87M8djsU(this, arg12, arg13, v8, v1));
            try {
                v1.await();
            }
            catch(Exception v12) {
                FileLog.e(((Throwable)v12));
            }

            if(!v8.isEmpty()) {
                v0_1 = v8.get(0);
            }
        }

        return ((TLObject)v0_1);
    }

    public DispatchQueue getStorageQueue() {
        return this.storageQueue;
    }

    public int getTotalMessageCount(long arg7) {
        int[] v1 = new int[]{0};
        try {
            SQLiteCursor v7_1 = this.database.b(String.format(Locale.US, "SELECT COUNT(*)  FROM messages WHERE uid = %d ", Long.valueOf(arg7)), new Object[0]);
            if(v7_1.a()) {
                v1[0] = v7_1.b(0);
            }

            v7_1.b();
        }
        catch(a v7) {
            v7.printStackTrace();
        }

        Log.d("LEE", "AliMessageCount:" + v1[0]);
        return v1[0];
    }

    public void getUnreadMention(long arg3, IntCallback arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$9IdFW72D1R13fo8J0GAaOH-Bj7Y(this, arg3, arg5));
    }

    public void getUnsentMessages(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$V5E6Y8xCEvOR1AAVFOP4dCW3AUA(this, arg3));
    }

    public User getUser(int arg5) {
        User v0 = null;
        try {
            ArrayList v1 = new ArrayList();
            this.getUsersInternal("" + arg5, v1);
            if(v1.isEmpty()) {
                return v0;
            }

            Object v0_1 = v1.get(0);
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

        return v0;
    }

    public ArrayList getUserStateWithStartAndEndTime(long arg24, long arg26) {
        TL_dialog v3;
        Message v2;
        int v19;
        int v12;
        int v9_3;
        SQLiteCursor v0_2;
        int v10_1;
        long v17;
        long v21;
        int v11_1;
        String v10;
        Locale v9_1;
        SQLiteDatabase v0_1;
        MessagesStorage v1 = this;
        ArrayList v6 = new ArrayList();
        long v9 = 3600;
        long v7 = (arg26 - arg24) / v9;
        long v11 = 0;
        if(arg26 - v7 * v9 > v11) {
            ++v7;
        }

        int v4 = 0;
        int v5 = 0;
        while((((long)v5)) < v7) {
            if(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId() == 0) {
                return new ArrayList();
            }

            long v13 = (((long)(v5 * 3600))) + arg24;
            long v15 = v13 + v9;
            try {
                v0_1 = v1.database;
                v9_1 = Locale.US;
                v10 = "SELECT data , mid, date, uid  FROM messages WHERE date BETWEEN %d AND %d ";
                v11_1 = 2;
            }
            catch(a v0) {
                v21 = v7;
                v17 = ((long)v11_1);
                goto label_155;
            }

            try {
                Object[] v11_2 = new Object[v11_1];
                v11_2[v4] = Long.valueOf(v13);
                v11_2[1] = Long.valueOf(v15);
                String v9_2 = String.format(v9_1, v10, v11_2);
                v10_1 = 0;
                v0_2 = v0_1.b(v9_2, new Object[0]);
                v9_3 = 0;
                v11_1 = 0;
                v12 = 0;
                v19 = 0;
                goto label_45;
            }
            catch(a v0) {
                v21 = v7;
                v17 = 0;
            }

        label_155:
            v9_3 = 0;
            v11_1 = 0;
            v12 = 0;
            v19 = 0;
            goto label_159;
            try {
                while(true) {
                label_45:
                    if(!v0_2.a()) {
                        goto label_138;
                    }

                    NativeByteBuffer v4_1 = v0_2.g(v10_1);
                    if(v4_1 != null) {
                        v2 = Message.TLdeserialize(((AbstractSerializedData)v4_1), v4_1.readInt32(((boolean)v10_1)), ((boolean)v10_1));
                        v4_1.reuse();
                        if(!v2.out) {
                            if(v2.post) {
                                goto label_57;
                            }

                            break;
                        }

                        goto label_57;
                    }
                    else {
                        goto label_131;
                    }
                }
            }
            catch(a v0) {
                goto label_145;
            }

            ++v11_1;
        label_57:
            v4 = v11_1;
            try {
                if(v2.out) {
                    goto label_60;
                }

                goto label_68;
            }
            catch(a v0) {
                goto label_127;
            }

            try {
            label_60:
                if(v2.post) {
                    goto label_68;
                }

                goto label_62;
            }
            catch(a v0) {
                v11_1 = v4;
            }

        label_145:
            v21 = v7;
            v17 = 0;
            goto label_159;
        label_62:
            ++v12;
            try {
            label_68:
                v2.id = v0_2.b(1);
                v2.dialog_id = v0_2.d(3);
                v3 = new TL_dialog();
                v3.id = v2.dialog_id;
                v10_1 = ((int)(v2.dialog_id >> 32));
                v21 = v7;
            }
            catch(a v0) {
            label_127:
                v21 = v7;
                goto label_128;
            }

            try {
                if((((int)v3.id)) != 0 && v10_1 != 1 && ((v3 instanceof TL_dialog))) {
                    v17 = 0;
                    if(v3.id >= v17) {
                        goto label_110;
                    }

                    goto label_92;
                }

                goto label_122;
            }
            catch(a v0) {
            }

        label_128:
            v17 = 0;
            goto label_129;
            try {
            label_92:
                Chat v3_1 = v1.getChat(-(((int)v3.id)));
                if(v3_1 != null) {
                    if(v3_1.megagroup) {
                        if(v2.out) {
                            goto label_118;
                        }
                    }
                    else if(ChatObject.isChannel(v3_1)) {
                        goto label_122;
                    }
                    else if(!v2.out) {
                    }
                    else {
                        goto label_118;
                    }

                    goto label_116;
                label_110:
                    if(v1.getUser(((int)v3.id)) != null) {
                        goto label_122;
                    }

                    if(v2.out) {
                        goto label_118;
                    }
                }
                else {
                    goto label_122;
                }
            }
            catch(a v0) {
                goto label_129;
            }

        label_116:
            ++v19;
            goto label_122;
        label_118:
            ++v9_3;
        label_122:
            v11_1 = v4;
            goto label_134;
        label_131:
            v21 = v7;
        label_134:
            v7 = v21;
            v10_1 = 0;
            goto label_45;
        label_138:
            v21 = v7;
            v17 = 0;
            try {
                v0_2.b();
                goto label_160;
            }
            catch(a v0) {
                goto label_159;
            }

        label_129:
            v11_1 = v4;
        label_159:
            v0.printStackTrace();
        label_160:
            UserState v2_1 = new UserState();
            v2_1.setMs(v12);
            v2_1.setMr(v11_1);
            v2_1.setGp(v9_3);
            v2_1.setGr(v19);
            v2_1.setS(v13 * 1000);
            v2_1.setE(1000 * v15);
            v2_1.setVc(b.h());
            v6.add(v2_1);
            ++v5;
            v11 = v17;
            v7 = v21;
            v4 = 0;
            v9 = 3600;
        }

        return v6;
    }

    public User getUserSync(int arg5) {
        CountDownLatch v0 = new CountDownLatch(1);
        User[] v1 = new User[1];
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$plcV57VZpB9ChVjU0VxBIQwC7_E(this, v1, arg5, v0));
        try {
            v0.await();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

        return v1[0];
    }

    public ArrayList getUsers(ArrayList arg3) {
        ArrayList v0 = new ArrayList();
        try {
            this.getUsersInternal(TextUtils.join(",", ((Iterable)arg3)), v0);
        }
        catch(Exception v3) {
            v0.clear();
            FileLog.e(((Throwable)v3));
        }

        return v0;
    }

    public void getUsersInternal(String arg7, ArrayList arg8) {
        if(arg7 != null && arg7.length() != 0 && arg8 != null) {
            SQLiteCursor v7 = this.database.b(String.format(Locale.US, "SELECT data, status FROM users WHERE uid IN(%s)", arg7), new Object[0]);
            while(v7.a()) {
                try {
                    NativeByteBuffer v0_1 = v7.g(0);
                    if(v0_1 == null) {
                        continue;
                    }

                    User v1 = User.TLdeserialize(((AbstractSerializedData)v0_1), v0_1.readInt32(false), false);
                    v0_1.reuse();
                    if(v1 == null) {
                        continue;
                    }

                    if(v1.status != null) {
                        v1.status.expires = v7.b(1);
                    }

                    arg8.add(v1);
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }

            v7.b();
        }
    }

    public void getWallpapers() {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$_IlJmtWGUPQpu48oRUFHsslSvaA(this));
    }

    public boolean hasAuthMessage(int arg5) {
        CountDownLatch v0 = new CountDownLatch(1);
        boolean[] v1 = new boolean[1];
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$ush3JJi2Yw3D4PDyCWbMK0GKFcs(this, arg5, v1, v0));
        try {
            v0.await();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

        return v1[0];
    }

    public boolean isDialogHasMessages(long arg11) {
        CountDownLatch v6 = new CountDownLatch(1);
        boolean[] v7 = new boolean[1];
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$u5h2c2crT1z6WbcyxtFDsH3jW3I(this, arg11, v7, v6));
        try {
            v6.await();
        }
        catch(Exception v11) {
            FileLog.e(((Throwable)v11));
        }

        return v7[0];
    }

    private static boolean isEmpty(LongSparseArray arg0) {
        boolean v0 = arg0 == null || arg0.size() == 0 ? true : false;
        return v0;
    }

    private static boolean isEmpty(SparseArray arg0) {
        boolean v0 = arg0 == null || arg0.size() == 0 ? true : false;
        return v0;
    }

    private static boolean isEmpty(SparseIntArray arg0) {
        boolean v0 = arg0 == null || arg0.size() == 0 ? true : false;
        return v0;
    }

    private static boolean isEmpty(List arg0) {
        boolean v0 = arg0 == null || (arg0.isEmpty()) ? true : false;
        return v0;
    }

    private static boolean isEmpty(SparseLongArray arg0) {
        boolean v0 = arg0 == null || arg0.size() == 0 ? true : false;
        return v0;
    }

    public boolean isMigratedChat(int arg5) {
        CountDownLatch v0 = new CountDownLatch(1);
        boolean[] v1 = new boolean[1];
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$5VgIA0YuCIhLQCNKMu8vvKdTRVc(this, arg5, v1, v0));
        try {
            v0.await();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }

        return v1[0];
    }

    private boolean isValidKeyboardToSave(Message arg2) {
        boolean v2;
        if(arg2.reply_markup == null || ((arg2.reply_markup instanceof TL_replyInlineMarkup))) {
        label_12:
            v2 = false;
        }
        else {
            if((arg2.reply_markup.selective) && !arg2.mentioned) {
                goto label_12;
            }

            v2 = true;
        }

        return v2;
    }

    public static void lambda$addRecentLocalFile$24(MessagesStorage arg4, Document arg5, String arg6, String arg7) {
        int v0 = 2;
        if(arg5 == null) {
            goto label_19;
        }

        try {
            SQLitePreparedStatement v7 = arg4.database.a("UPDATE web_recent_v3 SET document = ? WHERE image_url = ?");
            v7.d();
            NativeByteBuffer v2 = new NativeByteBuffer(arg5.getObjectSize());
            arg5.serializeToStream(((AbstractSerializedData)v2));
            v7.a(1, v2);
            v7.a(v0, arg6);
            v7.b();
            v7.e();
            v2.reuse();
            return;
        label_19:
            SQLitePreparedStatement v5_1 = arg4.database.a("UPDATE web_recent_v3 SET local_url = ? WHERE image_url = ?");
            v5_1.d();
            v5_1.a(1, arg7);
            v5_1.a(v0, arg6);
            v5_1.b();
            v5_1.e();
            return;
        label_18:
        }
        catch(Exception v5) {
            goto label_18;
        }

        FileLog.e(((Throwable)v5));
    }

    public static void lambda$applyPhoneBookUpdates$72(MessagesStorage arg6, String arg7, String arg8) {
        try {
            if(arg7.length() != 0) {
                arg6.database.a(String.format(Locale.US, "UPDATE user_phones_v7 SET deleted = 0 WHERE sphone IN(%s)", arg7)).c().e();
            }

            if(arg8.length() == 0) {
                return;
            }

            arg6.database.a(String.format(Locale.US, "UPDATE user_phones_v7 SET deleted = 1 WHERE sphone IN(%s)", arg8)).c().e();
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
        }
    }

    public static void lambda$checkMessageByRandomId$77(MessagesStorage arg6, long arg7, boolean[] arg9, CountDownLatch arg10) {
        SQLiteCursor v7;
        SQLiteCursor v0 = null;
        try {
            v7 = arg6.database.b(String.format(Locale.US, "SELECT random_id FROM randoms WHERE random_id = %d", Long.valueOf(arg7)), new Object[0]);
            goto label_12;
        }
        catch(Throwable v8) {
        }
        catch(Exception v8_1) {
            goto label_27;
            try {
            label_12:
                if(v7.a()) {
                    arg9[0] = true;
                }

                goto label_15;
            }
            catch(Throwable v8) {
                v0 = v7;
            }
            catch(Exception v8_1) {
                v0 = v7;
                try {
                label_27:
                    FileLog.e(((Throwable)v8_1));
                    if(v0 == null) {
                        goto label_30;
                    }
                }
                catch(Throwable v8) {
                    goto label_32;
                }

                v0.b();
                goto label_30;
            }
        }

    label_32:
        if(v0 != null) {
            v0.b();
        }

        throw v8;
    label_15:
        if(v7 != null) {
            v7.b();
        }

    label_30:
        arg10.countDown();
    }

    public static void lambda$checkMessageId$78(MessagesStorage arg5, long arg6, int arg8, boolean[] arg9, CountDownLatch arg10) {
        SQLiteCursor v6;
        SQLiteCursor v0 = null;
        try {
            v6 = arg5.database.b(String.format(Locale.US, "SELECT mid FROM messages WHERE uid = %d AND mid = %d", Long.valueOf(arg6), Integer.valueOf(arg8)), new Object[0]);
            goto label_15;
        }
        catch(Throwable v7) {
        }
        catch(Exception v7_1) {
            goto label_30;
            try {
            label_15:
                if(v6.a()) {
                    arg9[0] = true;
                }

                goto label_18;
            }
            catch(Throwable v7) {
                v0 = v6;
            }
            catch(Exception v7_1) {
                v0 = v6;
                try {
                label_30:
                    FileLog.e(((Throwable)v7_1));
                    if(v0 == null) {
                        goto label_33;
                    }
                }
                catch(Throwable v7) {
                    goto label_35;
                }

                v0.b();
                goto label_33;
            }
        }

    label_35:
        if(v0 != null) {
            v0.b();
        }

        throw v7;
    label_18:
        if(v6 != null) {
            v6.b();
        }

    label_33:
        arg10.countDown();
    }

    public static void lambda$cleanup$3(MessagesStorage arg1, boolean arg2) {
        arg1.cleanupInternal();
        arg1.openDatabase(false);
        if(arg2) {
            Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesStorage$UEupzbErlOb7ycnNPm1lBZ9MmX8(arg1));
        }
    }

    public static void lambda$clearDownloadQueue$96(MessagesStorage arg5, int arg6) {
        if(arg6 == 0) {
            try {
                SQLitePreparedStatement v6_1 = arg5.database.a("DELETE FROM download_queue WHERE 1").c();
                goto label_5;
            label_9:
                v6_1 = arg5.database.a(String.format(Locale.US, "DELETE FROM download_queue WHERE type = %d", Integer.valueOf(arg6))).c();
            label_5:
                v6_1.e();
                return;
            label_8:
                goto label_21;
            }
            catch(Exception v6) {
                goto label_8;
            }
        }
        else {
            goto label_9;
        }

        goto label_5;
    label_21:
        FileLog.e(((Throwable)v6));
    }

    public static void lambda$clearSentMedia$83(MessagesStorage arg2) {
        try {
            arg2.database.a("DELETE FROM sent_files_v2 WHERE 1").c().e();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$clearUserPhoto$40(MessagesStorage arg3, int arg4, long arg5) {
        try {
            SQLiteDatabase v0 = arg3.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM user_photos WHERE uid = ");
            v1.append(arg4);
            v1.append(" AND id = ");
            v1.append(arg5);
            v0.a(v1.toString()).c().e();
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }

    public static void lambda$clearUserPhotos$39(MessagesStorage arg3, int arg4) {
        try {
            SQLiteDatabase v0 = arg3.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM user_photos WHERE uid = ");
            v1.append(arg4);
            v0.a(v1.toString()).c().e();
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }

    public static void lambda$clearWebRecent$25(MessagesStorage arg3, int arg4) {
        try {
            SQLiteDatabase v0 = arg3.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM web_recent_v3 WHERE type = ");
            v1.append(arg4);
            v0.a(v1.toString()).c().e();
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }

    public static void lambda$createPendingTask$6(MessagesStorage arg2, long arg3, NativeByteBuffer arg5) {
        try {
            SQLitePreparedStatement v0 = arg2.database.a("REPLACE INTO pending_tasks VALUES(?, ?)");
            v0.a(1, arg3);
            v0.a(2, arg5);
            v0.b();
            v0.e();
        }
        catch(Throwable v3) {
        }
        catch(Exception v3_1) {
            try {
                FileLog.e(((Throwable)v3_1));
            }
            catch(Throwable v3) {
                arg5.reuse();
                throw v3;
            }
        }

        arg5.reuse();
    }

    public static void lambda$createTaskForMid$51(MessagesStorage arg7, int arg8, int arg9, int arg10, int arg11, int arg12, boolean arg13) {
        if(arg8 > arg9) {
        }
        else {
            arg8 = arg9;
        }

        arg8 += arg10;
        try {
            SparseArray v9 = new SparseArray();
            ArrayList v10 = new ArrayList();
            long v0 = ((long)arg11);
            if(arg12 != 0) {
                v0 |= (((long)arg12)) << 32;
            }

            v10.add(Long.valueOf(v0));
            v9.put(arg8, v10);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$IYz0FbYGlPCz1eYN6tyV5MNKU0w(arg7, arg13, v10));
            SQLitePreparedStatement v10_1 = arg7.database.a("REPLACE INTO enc_tasks_v2 VALUES(?, ?)");
            for(arg12 = 0; arg12 < v9.size(); ++arg12) {
                int v13 = v9.keyAt(arg12);
                Object v3 = v9.get(v13);
                int v4;
                for(v4 = 0; v4 < ((ArrayList)v3).size(); ++v4) {
                    v10_1.d();
                    v10_1.a(1, ((ArrayList)v3).get(v4).longValue());
                    v10_1.a(2, v13);
                    v10_1.b();
                }
            }

            v10_1.e();
            arg7.database.a(String.format(Locale.US, "UPDATE messages SET ttl = 0 WHERE mid = %d", Long.valueOf(v0))).c().e();
            MessagesController.getInstance(arg7.currentAccount).didAddedNewTask(arg8, v9);
        }
        catch(Exception v8) {
            FileLog.e(((Throwable)v8));
        }
    }

    public static void lambda$createTaskForSecretChat$53(MessagesStorage arg16, ArrayList arg17, int arg18, int arg19, int arg20, int arg21) {
        Object v10_2;
        SQLiteCursor v9_1;
        MessagesStorage v1 = arg16;
        ArrayList v0 = arg17;
        int v2 = 2147483647;
        try {
            SparseArray v3 = new SparseArray();
            ArrayList v4 = new ArrayList();
            StringBuilder v5 = new StringBuilder();
            int v6 = 2;
            if(v0 == null) {
                SQLiteDatabase v9 = v1.database;
                Locale v10 = Locale.US;
                Object[] v12 = new Object[3];
                v12[0] = Long.valueOf((((long)arg18)) << 32);
                v12[1] = Integer.valueOf(arg19);
                v12[v6] = Integer.valueOf(arg20);
                v9_1 = v9.b(String.format(v10, "SELECT mid, ttl FROM messages WHERE uid = %d AND out = %d AND read_state != 0 AND ttl > 0 AND date <= %d AND send_state = 0 AND media != 1", v12), new Object[0]);
            }
            else {
                v9_1 = v1.database.b(String.format(Locale.US, "SELECT m.mid, m.ttl FROM messages as m INNER JOIN randoms as r ON m.mid = r.mid WHERE r.random_id IN (%s)", TextUtils.join(",", ((Iterable)v0))), new Object[0]);
            }

            while(v9_1.a()) {
                int v10_1 = v9_1.b(1);
                long v11 = ((long)v9_1.b(0));
                if(v0 != null) {
                    v4.add(Long.valueOf(v11));
                }

                if(v10_1 <= 0) {
                    continue;
                }

                int v13 = arg20;
                int v14 = arg21;
                int v15 = v13 > v14 ? v13 : v14;
                v15 += v10_1;
                v2 = Math.min(v2, v15);
                v10_2 = v3.get(v15);
                if(v10_2 == null) {
                    ArrayList v10_3 = new ArrayList();
                    v3.put(v15, v10_3);
                }

                if(v5.length() != 0) {
                    v5.append(",");
                }

                v5.append(v11);
                ((ArrayList)v10_2).add(Long.valueOf(v11));
            }

            v9_1.b();
            if(v0 != null) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$72wTv-LNhymdYFtfN_3iFK6abDA(v1, v4));
            }

            if(v3.size() == 0) {
                return;
            }

            v1.database.d();
            SQLitePreparedStatement v0_2 = v1.database.a("REPLACE INTO enc_tasks_v2 VALUES(?, ?)");
            int v4_1;
            for(v4_1 = 0; v4_1 < v3.size(); ++v4_1) {
                int v9_2 = v3.keyAt(v4_1);
                v10_2 = v3.get(v9_2);
                int v11_1;
                for(v11_1 = 0; v11_1 < ((ArrayList)v10_2).size(); ++v11_1) {
                    v0_2.d();
                    v0_2.a(1, ((ArrayList)v10_2).get(v11_1).longValue());
                    v0_2.a(v6, v9_2);
                    v0_2.b();
                }
            }

            v0_2.e();
            v1.database.e();
            v1.database.a(String.format(Locale.US, "UPDATE messages SET ttl = 0 WHERE mid IN(%s)", v5.toString())).c().e();
            MessagesController.getInstance(v1.currentAccount).didAddedNewTask(v2, v3);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public static void lambda$deleteBlockedUser$30(MessagesStorage arg3, int arg4) {
        try {
            SQLiteDatabase v0 = arg3.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM blocked_users WHERE uid = ");
            v1.append(arg4);
            v0.a(v1.toString()).c().e();
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }

    public static void lambda$deleteContacts$71(MessagesStorage arg3, ArrayList arg4) {
        try {
            String v4_1 = TextUtils.join(",", ((Iterable)arg4));
            SQLiteDatabase v0 = arg3.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM contacts WHERE uid IN(");
            v1.append(v4_1);
            v1.append(")");
            v0.a(v1.toString()).c().e();
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }

    public static void lambda$deleteDialog$36(MessagesStorage arg12, int arg13, long arg14) {
        SQLitePreparedStatement v13_3;
        int v8_3;
        SQLiteCursor v0_3;
        SQLiteDatabase v0_2;
        long v6_2;
        long v3_3;
        SQLiteCursor v13_2;
        StringBuilder v0_1;
        SQLiteDatabase v13_1;
        ArrayList v5_2;
        Iterator v7_2;
        ArrayList v6_1;
        SQLiteCursor v5_1;
        int v3_2;
        int v0 = 3;
        int v1 = -1;
        if(arg13 == v0) {
            try {
                SQLiteDatabase v3 = arg12.database;
                StringBuilder v4 = new StringBuilder();
                v4.append("SELECT last_mid FROM dialogs WHERE did = ");
                v4.append(arg14);
                SQLiteCursor v3_1 = v3.b(v4.toString(), new Object[0]);
                int v4_1 = v3_1.a() ? v3_1.b(0) : -1;
                v3_1.b();
                if(v4_1 != 0) {
                    return;
                }

            label_23:
                v3_2 = ((int)arg14);
                v4_1 = 2;
                if(v3_2 != 0 && arg13 != v4_1) {
                    goto label_96;
                }

                SQLiteDatabase v5 = arg12.database;
                StringBuilder v6 = new StringBuilder();
                v6.append("SELECT data FROM messages WHERE uid = ");
                v6.append(arg14);
                v5_1 = v5.b(v6.toString(), new Object[0]);
                v6_1 = new ArrayList();
                goto label_38;
            }
            catch(Exception v13) {
                goto label_22;
            }
        }

        goto label_23;
        try {
            while(true) {
            label_38:
                if(!v5_1.a()) {
                    goto label_92;
                }

                NativeByteBuffer v7_1 = v5_1.g(0);
                if(v7_1 == null) {
                    continue;
                }

                Message v8 = Message.TLdeserialize(((AbstractSerializedData)v7_1), v7_1.readInt32(false), false);
                v8.readAttachPath(((AbstractSerializedData)v7_1), UserConfig.getInstance(arg12.currentAccount).clientUserId);
                v7_1.reuse();
                if(v8 == null) {
                    continue;
                }

                if(v8.media == null) {
                    continue;
                }

                if((v8.media instanceof TL_messageMediaPhoto)) {
                    v7_2 = v8.media.photo.sizes.iterator();
                    break;
                }

                if(!(v8.media instanceof TL_messageMediaDocument)) {
                    continue;
                }

                File v7_3 = FileLoader.getPathToAttach(v8.media.document);
                if(v7_3 != null && v7_3.toString().length() > 0) {
                    v6_1.add(v7_3);
                }

                v7_3 = FileLoader.getPathToAttach(v8.media.document.thumb);
                if(v7_3 == null) {
                    continue;
                }

                if(v7_3.toString().length() <= 0) {
                    continue;
                }

                v6_1.add(v7_3);
            }

            while(true) {
                if(!v7_2.hasNext()) {
                    goto label_38;
                }

                File v8_1 = FileLoader.getPathToAttach(v7_2.next());
                if(v8_1 == null) {
                    continue;
                }

                if(v8_1.toString().length() <= 0) {
                    continue;
                }

                v6_1.add(v8_1);
            }
        }
        catch(Exception v7) {
            try {
                FileLog.e(((Throwable)v7));
            label_92:
                v5_1.b();
                FileLoader.getInstance(arg12.currentAccount).deleteFiles(v6_1, arg13);
            label_96:
                v5_2 = null;
                if(arg13 == 0) {
                    goto label_232;
                }
                else if(arg13 == v0) {
                    goto label_232;
                }
                else if(arg13 == v4_1) {
                    v13_1 = arg12.database;
                    v0_1 = new StringBuilder();
                    v0_1.append("SELECT last_mid_i, last_mid FROM dialogs WHERE did = ");
                    v0_1.append(arg14);
                    v13_2 = v13_1.b(v0_1.toString(), new Object[0]);
                    if(v13_2.a()) {
                        v3_3 = v13_2.d(0);
                        v6_2 = v13_2.d(1);
                        v0_2 = arg12.database;
                        StringBuilder v8_2 = new StringBuilder();
                        v8_2.append("SELECT data FROM messages WHERE uid = ");
                        v8_2.append(arg14);
                        v8_2.append(" AND mid IN (");
                        v8_2.append(v3_3);
                        v8_2.append(",");
                        v8_2.append(v6_2);
                        v8_2.append(")");
                        v0_3 = v0_2.b(v8_2.toString(), new Object[0]);
                        v8_3 = -1;
                        goto label_133;
                    }
                    else {
                        goto label_230;
                    }
                }

                goto label_308;
            }
            catch(Exception v13) {
                goto label_22;
            }
        }

        try {
            while(true) {
            label_133:
                if(v0_3.a()) {
                    NativeByteBuffer v9 = v0_3.g(0);
                    if(v9 == null) {
                        continue;
                    }

                    Message v10 = Message.TLdeserialize(((AbstractSerializedData)v9), v9.readInt32(false), false);
                    v10.readAttachPath(((AbstractSerializedData)v9), UserConfig.getInstance(arg12.currentAccount).clientUserId);
                    v9.reuse();
                    if(v10 == null) {
                        continue;
                    }

                    v8_3 = v10.id;
                    continue;
                }
                else {
                    goto label_150;
                }
            }
        }
        catch(Exception v2) {
            try {
                FileLog.e(((Throwable)v2));
            label_150:
                v0_3.b();
                v0_2 = arg12.database;
                StringBuilder v2_1 = new StringBuilder();
                v2_1.append("DELETE FROM messages WHERE uid = ");
                v2_1.append(arg14);
                v2_1.append(" AND mid != ");
                v2_1.append(v3_3);
                v2_1.append(" AND mid != ");
                v2_1.append(v6_2);
                v0_2.a(v2_1.toString()).c().e();
                v0_2 = arg12.database;
                v2_1 = new StringBuilder();
                v2_1.append("DELETE FROM messages_holes WHERE uid = ");
                v2_1.append(arg14);
                v0_2.a(v2_1.toString()).c().e();
                v0_2 = arg12.database;
                v2_1 = new StringBuilder();
                v2_1.append("DELETE FROM bot_keyboard WHERE uid = ");
                v2_1.append(arg14);
                v0_2.a(v2_1.toString()).c().e();
                v0_2 = arg12.database;
                v2_1 = new StringBuilder();
                v2_1.append("DELETE FROM media_counts_v2 WHERE uid = ");
                v2_1.append(arg14);
                v0_2.a(v2_1.toString()).c().e();
                v0_2 = arg12.database;
                v2_1 = new StringBuilder();
                v2_1.append("DELETE FROM media_v2 WHERE uid = ");
                v2_1.append(arg14);
                v0_2.a(v2_1.toString()).c().e();
                v0_2 = arg12.database;
                v2_1 = new StringBuilder();
                v2_1.append("DELETE FROM media_holes_v2 WHERE uid = ");
                v2_1.append(arg14);
                v0_2.a(v2_1.toString()).c().e();
                DataQuery.getInstance(arg12.currentAccount).clearBotKeyboard(arg14, v5_2);
                SQLitePreparedStatement v0_4 = arg12.database.a("REPLACE INTO messages_holes VALUES(?, ?, ?)");
                SQLitePreparedStatement v2_2 = arg12.database.a("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
                if(v8_3 != v1) {
                    MessagesStorage.createFirstHoles(arg14, v0_4, v2_2, v8_3);
                }

                v0_4.e();
                v2_2.e();
            label_230:
                v13_2.b();
                return;
            label_232:
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM dialogs WHERE did = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM chat_settings_v2 WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM chat_pinned WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM channel_users_v2 WHERE did = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM search_recent WHERE did = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                arg13 = ((int)(arg14 >> 32));
                if(v3_2 == 0) {
                    v0_2 = arg12.database;
                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("DELETE FROM enc_chats WHERE uid = ");
                    v1_1.append(arg13);
                    v13_3 = v0_2.a(v1_1.toString()).c();
                }
                else if(arg13 == 1) {
                    v13_1 = arg12.database;
                    v0_1 = new StringBuilder();
                    v0_1.append("DELETE FROM chats WHERE uid = ");
                    v0_1.append(v3_2);
                    v13_3 = v13_1.a(v0_1.toString()).c();
                }
                else {
                    goto label_308;
                }

                v13_3.e();
            label_308:
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("UPDATE dialogs SET unread_count = 0 WHERE did = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM messages WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM bot_keyboard WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM media_counts_v2 WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM media_v2 WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM messages_holes WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                v13_1 = arg12.database;
                v0_1 = new StringBuilder();
                v0_1.append("DELETE FROM media_holes_v2 WHERE uid = ");
                v0_1.append(arg14);
                v13_1.a(v0_1.toString()).c().e();
                DataQuery.getInstance(arg12.currentAccount).clearBotKeyboard(arg14, v5_2);
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$6DjTa3Y8ttX-Dp7Qv5PkUZt09LA(arg12));
                return;
            label_22:
            }
            catch(Exception v13) {
                goto label_22;
            }
        }

        FileLog.e(((Throwable)v13));
    }

    public static void lambda$deleteUserChannelHistory$34(MessagesStorage arg7, int arg8, int arg9) {
        Iterator v4_2;
        ArrayList v3_1;
        SQLiteCursor v0_1;
        ArrayList v2;
        long v0 = ((long)(-arg8));
        try {
            v2 = new ArrayList();
            SQLiteDatabase v3 = arg7.database;
            StringBuilder v4 = new StringBuilder();
            v4.append("SELECT data FROM messages WHERE uid = ");
            v4.append(v0);
            v0_1 = v3.b(v4.toString(), new Object[0]);
            v3_1 = new ArrayList();
        }
        catch(Exception v8) {
            goto label_93;
        }

        try {
            while(true) {
            label_16:
                if(!v0_1.a()) {
                    goto label_76;
                }

                NativeByteBuffer v4_1 = v0_1.g(0);
                if(v4_1 == null) {
                    continue;
                }

                Message v5 = Message.TLdeserialize(((AbstractSerializedData)v4_1), v4_1.readInt32(false), false);
                v5.readAttachPath(((AbstractSerializedData)v4_1), UserConfig.getInstance(arg7.currentAccount).clientUserId);
                v4_1.reuse();
                if(v5 == null) {
                    continue;
                }

                if(v5.from_id != arg9) {
                    continue;
                }

                if(v5.id == 1) {
                    continue;
                }

                v2.add(Integer.valueOf(v5.id));
                if((v5.media instanceof TL_messageMediaPhoto)) {
                    v4_2 = v5.media.photo.sizes.iterator();
                    break;
                }

                if(!(v5.media instanceof TL_messageMediaDocument)) {
                    continue;
                }

                File v4_3 = FileLoader.getPathToAttach(v5.media.document);
                if(v4_3 != null && v4_3.toString().length() > 0) {
                    v3_1.add(v4_3);
                }

                v4_3 = FileLoader.getPathToAttach(v5.media.document.thumb);
                if(v4_3 == null) {
                    continue;
                }

                if(v4_3.toString().length() <= 0) {
                    continue;
                }

                v3_1.add(v4_3);
            }

            while(true) {
                if(!v4_2.hasNext()) {
                    goto label_16;
                }

                File v5_1 = FileLoader.getPathToAttach(v4_2.next());
                if(v5_1 == null) {
                    continue;
                }

                if(v5_1.toString().length() <= 0) {
                    continue;
                }

                v3_1.add(v5_1);
            }
        }
        catch(Exception v9) {
            try {
                FileLog.e(((Throwable)v9));
            label_76:
                v0_1.b();
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$vFBjn3K50Wy4r1vCXOzndIVMUjk(arg7, v2, arg8));
                arg7.markMessagesAsDeletedInternal(v2, arg8);
                arg7.updateDialogsWithDeletedMessagesInternal(v2, null, arg8);
                FileLoader.getInstance(arg7.currentAccount).deleteFiles(v3_1, 0);
                if(v2.isEmpty()) {
                    return;
                }

                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$1blR2IqcEgbLTKLEB6ObXqlX7jM(arg7, v2, arg8));
            }
            catch(Exception v8) {
            label_93:
                FileLog.e(((Throwable)v8));
            }
        }
    }

    public static void lambda$emptyMessagesMedia$45(MessagesStorage arg11, ArrayList arg12) {
        Message v6;
        int v4;
        int v3;
        try {
            ArrayList v0 = new ArrayList();
            ArrayList v1 = new ArrayList();
            SQLiteCursor v12_1 = arg11.database.b(String.format(Locale.US, "SELECT data, mid, date, uid FROM messages WHERE mid IN (%s)", TextUtils.join(",", ((Iterable)arg12))), new Object[0]);
            do {
            label_16:
                v3 = 3;
                v4 = 2;
                if(!v12_1.a()) {
                    goto label_90;
                }

                NativeByteBuffer v2 = v12_1.g(0);
                if(v2 == null) {
                    continue;
                }

                v6 = Message.TLdeserialize(((AbstractSerializedData)v2), v2.readInt32(false), false);
                v6.readAttachPath(((AbstractSerializedData)v2), UserConfig.getInstance(arg11.currentAccount).clientUserId);
                v2.reuse();
                if(v6.media == null) {
                    continue;
                }

                if(v6.media.document != null) {
                    File v2_1 = FileLoader.getPathToAttach(v6.media.document, true);
                    if(v2_1 != null && v2_1.toString().length() > 0) {
                        v0.add(v2_1);
                    }

                    v2_1 = FileLoader.getPathToAttach(v6.media.document.thumb, true);
                    if(v2_1 != null && v2_1.toString().length() > 0) {
                        v0.add(v2_1);
                    }

                    v6.media.document = new TL_documentEmpty();
                }
                else {
                    if(v6.media.photo == null) {
                        continue;
                    }

                    break;
                }

                goto label_77;
            }
            while(true);

            Iterator v2_2 = v6.media.photo.sizes.iterator();
            goto label_63;
        label_90:
            v12_1.b();
            if(!v1.isEmpty()) {
                SQLitePreparedStatement v12_2 = arg11.database.a("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
                int v2_3;
                for(v2_3 = 0; v2_3 < v1.size(); ++v2_3) {
                    Object v6_1 = v1.get(v2_3);
                    NativeByteBuffer v8 = new NativeByteBuffer(((Message)v6_1).getObjectSize());
                    ((Message)v6_1).serializeToStream(((AbstractSerializedData)v8));
                    v12_2.d();
                    v12_2.a(1, ((long)((Message)v6_1).id));
                    v12_2.a(v4, ((Message)v6_1).dialog_id);
                    v12_2.a(v3, MessageObject.getUnreadFlags(((Message)v6_1)));
                    v12_2.a(4, ((Message)v6_1).send_state);
                    v12_2.a(5, ((Message)v6_1).date);
                    v12_2.a(6, v8);
                    v12_2.a(7, MessageObject.isOut(((Message)v6_1)));
                    v12_2.a(8, ((Message)v6_1).ttl);
                    int v10 = 9;
                    int v9 = (((Message)v6_1).flags & 1024) != 0 ? ((Message)v6_1).views : arg11.getMessageMediaType(((Message)v6_1));
                    v12_2.a(v10, v9);
                    v12_2.a(10, 0);
                    v12_2.a(11, ((Message)v6_1).mentioned);
                    v12_2.b();
                    v8.reuse();
                }

                v12_2.e();
                AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$QiaXVDHphjnG_zFGK-XZVxGQ_pc(arg11, v1));
            }

            FileLoader.getInstance(arg11.currentAccount).deleteFiles(v0, 0);
            return;
        label_63:
            while(v2_2.hasNext()) {
                File v8_1 = FileLoader.getPathToAttach(v2_2.next(), true);
                if(v8_1 == null) {
                    continue;
                }

                if(v8_1.toString().length() <= 0) {
                    continue;
                }

                v0.add(v8_1);
            }

            v6.media.photo = new TL_photoEmpty();
        label_77:
            v6.media.flags &= -2;
            v6.id = v12_1.b(1);
            v6.date = v12_1.b(v4);
            v6.dialog_id = v12_1.d(v3);
            v1.add(v6);
            goto label_16;
        }
        catch(Exception v12) {
            FileLog.e(((Throwable)v12));
        }
    }

    public static void lambda$fixNotificationSettings$5(MessagesStorage arg9) {
        try {
            LongSparseArray v0_1 = new LongSparseArray();
            Map v1 = MessagesController.getNotificationsSettings(arg9.currentAccount).getAll();
            Iterator v2 = v1.entrySet().iterator();
            while(true) {
                int v4 = 2;
                if(!v2.hasNext()) {
                    goto label_51;
                }

                Object v3 = v2.next();
                Object v5 = ((Map$Entry)v3).getKey();
                if(!((String)v5).startsWith("notify2_")) {
                    continue;
                }

                v3 = ((Map$Entry)v3).getValue();
                if(((Integer)v3).intValue() != v4) {
                    if(((Integer)v3).intValue() == 3) {
                    }
                    else {
                        ((Integer)v3).intValue();
                        continue;
                    }
                }

                String v5_1 = ((String)v5).replace("notify2_", "");
                long v6 = 1;
                if(((Integer)v3).intValue() == v4) {
                }
                else {
                    StringBuilder v3_1 = new StringBuilder();
                    v3_1.append("notifyuntil_");
                    v3_1.append(v5_1);
                    v3 = v1.get(v3_1.toString());
                    if(v3 != null) {
                        v6 |= (((long)((Integer)v3).intValue())) << 32;
                    }
                }

                try {
                    v0_1.put(Long.parseLong(v5_1), Long.valueOf(v6));
                    continue;
                }
                catch(Exception v3_2) {
                    try {
                        v3_2.printStackTrace();
                        continue;
                        try {
                        label_51:
                            arg9.database.d();
                            SQLitePreparedStatement v1_1 = arg9.database.a("REPLACE INTO dialog_settings VALUES(?, ?)");
                            int v2_1;
                            for(v2_1 = 0; v2_1 < v0_1.size(); ++v2_1) {
                                v1_1.d();
                                v1_1.a(1, v0_1.keyAt(v2_1));
                                v1_1.a(v4, v0_1.valueAt(v2_1).longValue());
                                v1_1.b();
                            }

                            v1_1.e();
                            arg9.database.e();
                        }
                        catch(Exception v0_2) {
                            try {
                                FileLog.e(((Throwable)v0_2));
                            }
                            catch(Throwable v0) {
                            label_77:
                                FileLog.e(v0);
                            }
                        }

                        return;
                    }
                    catch(Throwable v0) {
                        goto label_77;
                    }
                }
            }
        }
        catch(Throwable v0) {
            goto label_77;
        }
    }

    public static void lambda$getBlockedUsers$29(MessagesStorage arg7) {
        try {
            SparseIntArray v0_1 = new SparseIntArray();
            ArrayList v1 = new ArrayList();
            SQLiteCursor v2 = arg7.database.b("SELECT * FROM blocked_users WHERE 1", new Object[0]);
            StringBuilder v3 = new StringBuilder();
            while(v2.a()) {
                int v5 = v2.b(0);
                v0_1.put(v5, 1);
                if(v3.length() != 0) {
                    v3.append(",");
                }

                v3.append(v5);
            }

            v2.b();
            if(v3.length() != 0) {
                arg7.getUsersInternal(v3.toString(), v1);
            }

            MessagesController.getInstance(arg7.currentAccount).processLoadedBlockedUsers(v0_1, v1, true);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$getBotCache$61(MessagesStorage arg4, int arg5, String arg6, RequestDelegate arg7) {
        NativeByteBuffer v6_1;
        TLObject v1_1;
        SQLiteCursor v5_1;
        TL_error v0 = null;
        try {
            SQLiteDatabase v1 = arg4.database;
            StringBuilder v2 = new StringBuilder();
            v2.append("DELETE FROM botcache WHERE date < ");
            v2.append(arg5);
            v1.a(v2.toString()).c().e();
            v5_1 = arg4.database.b("SELECT data FROM botcache WHERE id = ?", new Object[]{arg6});
            if(!v5_1.a()) {
                goto label_36;
            }
        }
        catch(Exception v5) {
            v1_1 = ((TLObject)v0);
            goto label_46;
        }

        try {
            v6_1 = v5_1.g(0);
            if(v6_1 != null) {
                int v1_2 = v6_1.readInt32(false);
                if(v1_2 == TL_messages_botCallbackAnswer.constructor) {
                    TL_messages_botCallbackAnswer v1_3 = TL_messages_botCallbackAnswer.TLdeserialize(((AbstractSerializedData)v6_1), v1_2, false);
                }
                else {
                    messages_BotResults v1_4 = messages_BotResults.TLdeserialize(((AbstractSerializedData)v6_1), v1_2, false);
                }

                goto label_28;
            }

            goto label_36;
        }
        catch(Exception v6) {
            v1_1 = ((TLObject)v0);
            goto label_34;
        }

        v1_1 = ((TLObject)v0);
        goto label_50;
        try {
        label_28:
            v6_1.reuse();
            goto label_37;
        }
        catch(Throwable v5_2) {
        }
        catch(Exception v6) {
            try {
            label_34:
                FileLog.e(((Throwable)v6));
                goto label_37;
            label_36:
                v1_1 = ((TLObject)v0);
            label_37:
                v5_1.b();
                goto label_47;
            }
            catch(Throwable v5_2) {
            }
            catch(Exception v5) {
                try {
                label_46:
                    FileLog.e(((Throwable)v5));
                    goto label_47;
                }
                catch(Throwable v5_2) {
                }
            }
        }

    label_50:
        arg7.run(v1_1, v0);
        throw v5_2;
    label_47:
        arg7.run(v1_1, v0);
    }

    public static void lambda$getCachedPhoneBook$74(MessagesStorage arg26, boolean arg27) {
        SQLiteCursor v0_7;
        Object[] v13_5;
        String v9_2;
        SQLiteDatabase v0_6;
        int v13_3;
        int v9_1;
        String v0_3;
        Contact v13_2;
        Object v13_1;
        SQLiteCursor v7;
        SparseArray v14;
        int v0_2;
        SQLiteCursor v13;
        MessagesStorage v1 = arg26;
        int v2 = 6;
        int v3 = 3;
        int v4 = 5;
        int v5 = 2;
        int v6 = 4;
        int v10 = 8;
        int v11 = 5000;
        try {
            v13 = v1.database.b("SELECT name FROM sqlite_master WHERE type=\'table\' AND name=\'user_contacts_v6\'", new Object[0]);
        }
        catch(Throwable v0) {
            goto label_100;
        }
        catch(Throwable v0) {
            goto label_103;
        }

        try {
            boolean v0_1 = v13.a();
            v13.b();
            if(!v0_1) {
                goto label_91;
            }
        }
        catch(Throwable v0) {
            goto label_97;
        }
        catch(Throwable v0) {
            goto label_94;
        }

        try {
            v13 = v1.database.b("SELECT COUNT(uid) FROM user_contacts_v6 WHERE 1", new Object[0]);
        }
        catch(Throwable v0) {
            goto label_100;
        }
        catch(Throwable v0) {
            goto label_103;
        }

        try {
            v0_2 = v13.a() ? Math.min(v11, v13.b(0)) : 16;
            v13.b();
            v14 = new SparseArray(v0_2);
            v7 = v1.database.b("SELECT us.uid, us.fname, us.sname, up.phone, up.sphone, up.deleted, us.imported FROM user_contacts_v6 as us LEFT JOIN user_phones_v6 as up ON us.uid = up.uid WHERE 1", new Object[0]);
            goto label_34;
        }
        catch(Throwable v0) {
            goto label_97;
        }
        catch(Throwable v0) {
            goto label_94;
        }

    label_91:
        SQLiteCursor v17 = null;
        goto label_108;
    label_94:
        v7 = v13;
        goto label_265;
    label_97:
        SQLiteCursor v9 = v13;
        goto label_104;
        try {
            do {
            label_34:
                if(!v7.a()) {
                    break;
                }

                v0_2 = v7.b(0);
                v13_1 = v14.get(v0_2);
                if(v13_1 == null) {
                    v13_2 = new Contact();
                    v13_2.first_name = v7.e(1);
                    v13_2.last_name = v7.e(v5);
                    v13_2.imported = v7.b(v2);
                    if(v13_2.first_name == null) {
                        v13_2.first_name = "";
                    }

                    if(v13_2.last_name == null) {
                        v13_2.last_name = "";
                    }

                    v13_2.contact_id = v0_2;
                    v14.put(v0_2, v13_2);
                }

                v0_3 = v7.e(v3);
                if(v0_3 == null) {
                    goto label_34;
                }

                ((Contact)v13_1).phones.add(v0_3);
                String v15 = v7.e(v6);
                if(v15 == null) {
                    goto label_34;
                }

                if(v15.length() == v10 && v0_3.length() != v10) {
                    v15 = org.telegram.a.b.b(v0_3);
                }

                ((Contact)v13_1).shortPhones.add(v15);
                ((Contact)v13_1).phoneDeleted.add(Integer.valueOf(v7.b(v4)));
                ((Contact)v13_1).phoneTypes.add("");
            }
            while(v14.size() != v11);

            v7.b();
        }
        catch(Throwable v0) {
            goto label_265;
        }
        catch(Throwable v0) {
            v9 = v7;
            goto label_104;
        }

        try {
            ContactsController.getInstance(v1.currentAccount).migratePhoneBookToV7(v14);
            return;
        }
        catch(Throwable v0) {
        label_100:
            v7 = null;
            goto label_265;
        }
        catch(Throwable v0) {
        label_103:
            v9 = null;
        }

        try {
        label_104:
            FileLog.e(v0);
            if(v9 != null) {
                goto label_106;
            }

            goto label_107;
        }
        catch(Throwable v0) {
            goto label_264;
        }

    label_106:
        v9.b();
    label_107:
        v17 = v9;
        goto label_108;
    label_264:
        v7 = v9;
    label_265:
        if(v7 != null) {
            v7.b();
        }

        throw v0;
        try {
        label_108:
            v7 = v1.database.b("SELECT COUNT(key) FROM user_contacts_v7 WHERE 1", new Object[0]);
        }
        catch(Throwable v0) {
            goto label_154;
        }
        catch(Throwable v0) {
            goto label_157;
        }

        try {
            if(v7.a()) {
                v9_1 = v7.b(0);
            }
            else {
                goto label_141;
            }
        }
        catch(Throwable v0) {
            goto label_151;
        }

        try {
            v13_3 = Math.min(v11, v9_1);
            if(v9_1 > v11) {
            }
            else {
                goto label_120;
            }
        }
        catch(Throwable v0) {
            goto label_139;
        }

        int v14_1 = v9_1 - 5000;
        goto label_121;
    label_120:
        v14_1 = 0;
        try {
        label_121:
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d(v1.currentAccount + " current cached contacts count = " + v9_1);
            }
        }
        catch(Throwable v0) {
            goto label_135;
        }
        catch(Throwable v0) {
            goto label_149;
        }

        int v16 = v13_3;
        goto label_144;
    label_135:
        v17 = v7;
        v16 = v13_3;
        goto label_160;
    label_139:
        v17 = v7;
        goto label_158;
    label_141:
        v9_1 = 0;
        v14_1 = 0;
        v16 = 16;
    label_144:
        if(v7 != null) {
            v7.b();
        }

        v17 = v7;
        goto label_163;
    label_149:
        goto label_260;
    label_151:
        v17 = v7;
    label_157:
        v9_1 = 0;
    label_158:
        v14_1 = 0;
        v16 = 16;
        try {
        label_160:
            FileLog.e(v0);
            if(v17 == null) {
                goto label_163;
            }
        }
        catch(Throwable v0) {
            goto label_154;
        }

        v17.b();
    label_163:
        HashMap v7_1 = new HashMap(v16);
        if(v14_1 != 0) {
            try {
                v0_6 = v1.database;
                v9_2 = "SELECT us.key, us.uid, us.fname, us.sname, up.phone, up.sphone, up.deleted, us.imported FROM user_contacts_v7 as us LEFT JOIN user_phones_v7 as up ON us.key = up.key WHERE 1 LIMIT 0," + v9_1;
                v13_5 = new Object[0];
                goto label_175;
            label_182:
                v0_6 = v1.database;
                v9_2 = "SELECT us.key, us.uid, us.fname, us.sname, up.phone, up.sphone, up.deleted, us.imported FROM user_contacts_v7 as us LEFT JOIN user_phones_v7 as up ON us.key = up.key WHERE 1";
                v13_5 = new Object[0];
            }
            catch(Throwable v0) {
                goto label_178;
            }
            catch(Exception v0_5) {
                goto label_181;
            }
        }
        else {
            goto label_182;
        label_154:
            v7 = v17;
            goto label_260;
        }

        try {
        label_175:
            v0_7 = v0_6.b(v9_2, v13_5);
        }
        catch(Throwable v0) {
            goto label_178;
        }
        catch(Exception v0_5) {
            goto label_181;
        }

        v9 = v0_7;
        goto label_187;
    label_260:
        if(v7 != null) {
            v7.b();
        }

        throw v0;
        try {
            do {
            label_187:
                if(!v9.a()) {
                    break;
                }

                v0_3 = v9.e(0);
                v13_1 = v7_1.get(v0_3);
                if(v13_1 == null) {
                    v13_2 = new Contact();
                    v13_2.contact_id = v9.b(1);
                    v13_2.first_name = v9.e(v5);
                    v13_2.last_name = v9.e(v3);
                    v13_2.imported = v9.b(7);
                    if(v13_2.first_name == null) {
                        v13_2.first_name = "";
                    }

                    if(v13_2.last_name == null) {
                        v13_2.last_name = "";
                    }

                    v7_1.put(v0_3, v13_2);
                }

                v0_3 = v9.e(v6);
                if(v0_3 == null) {
                    goto label_187;
                }

                ((Contact)v13_1).phones.add(v0_3);
                String v14_2 = v9.e(v4);
                if(v14_2 == null) {
                    goto label_187;
                }

                if(v14_2.length() == v10 && v0_3.length() != v10) {
                    v14_2 = org.telegram.a.b.b(v0_3);
                }

                ((Contact)v13_1).shortPhones.add(v14_2);
                ((Contact)v13_1).phoneDeleted.add(Integer.valueOf(v9.b(v2)));
                ((Contact)v13_1).phoneTypes.add("");
            }
            while(v7_1.size() != v11);

            v9.b();
            goto label_246;
        }
        catch(Throwable v0) {
            goto label_257;
        }
        catch(Exception v0_5) {
            v17 = v9;
            goto label_242;
        }

    label_178:
        v9 = v17;
        goto label_257;
    label_181:
        try {
        label_242:
            v7_1.clear();
            FileLog.e(((Throwable)v0_5));
            if(v17 == null) {
                goto label_246;
            }
        }
        catch(Throwable v0) {
            goto label_178;
        }

        v17.b();
    label_246:
        ContactsController.getInstance(v1.currentAccount).performSyncPhoneBook(v7_1, true, true, false, false, (((int)arg27)) ^ 1, false);
        return;
    label_257:
        if(v9 != null) {
            v9.b();
        }

        throw v0;
    }

    public static void lambda$getChannelPtsSync$124(MessagesStorage arg4, int arg5, Integer[] arg6, CountDownLatch arg7) {
        SQLiteCursor v5;
        SQLiteCursor v0 = null;
        try {
            SQLiteDatabase v1 = arg4.database;
            StringBuilder v2 = new StringBuilder();
            v2.append("SELECT pts FROM dialogs WHERE did = ");
            v2.append(-arg5);
            v5 = v1.b(v2.toString(), new Object[0]);
            goto label_12;
        }
        catch(Throwable v6) {
        }
        catch(Exception v6_1) {
            goto label_29;
            try {
            label_12:
                if(v5.a()) {
                    arg6[0] = Integer.valueOf(v5.b(0));
                }

                goto label_17;
            }
            catch(Throwable v6) {
                v0 = v5;
            }
            catch(Exception v6_1) {
                v0 = v5;
                try {
                label_29:
                    FileLog.e(((Throwable)v6_1));
                    if(v0 == null) {
                        goto label_32;
                    }
                }
                catch(Throwable v6) {
                    goto label_38;
                }

                v0.b();
                goto label_32;
            }
        }

    label_38:
        if(v0 != null) {
            v0.b();
        }

        throw v6;
    label_17:
        if(v5 != null) {
            v5.b();
        }

    label_32:
        if(arg7 != null) {
            try {
                arg7.countDown();
            }
            catch(Exception v5_1) {
                FileLog.e(((Throwable)v5_1));
            }
        }
    }

    public static void lambda$getChatSync$126(MessagesStorage arg1, Chat[] arg2, int arg3, CountDownLatch arg4) {
        arg2[0] = arg1.getChat(arg3);
        arg4.countDown();
    }

    public static void lambda$getContacts$75(MessagesStorage arg8) {
        ArrayList v0 = new ArrayList();
        ArrayList v1 = new ArrayList();
        try {
            SQLiteCursor v3_1 = arg8.database.b("SELECT * FROM contacts WHERE 1", new Object[0]);
            StringBuilder v4 = new StringBuilder();
            while(v3_1.a()) {
                int v6 = v3_1.b(0);
                TL_contact v7 = new TL_contact();
                v7.user_id = v6;
                boolean v6_1 = v3_1.b(1) == 1 ? true : false;
                v7.mutual = v6_1;
                if(v4.length() != 0) {
                    v4.append(",");
                }

                v0.add(v7);
                v4.append(v7.user_id);
            }

            v3_1.b();
            if(v4.length() == 0) {
                goto label_42;
            }

            arg8.getUsersInternal(v4.toString(), v1);
        }
        catch(Exception v3) {
            v0.clear();
            v1.clear();
            FileLog.e(((Throwable)v3));
        }

    label_42:
        ContactsController.getInstance(arg8.currentAccount).processLoadedContacts(v0, v1, 1);
    }

    public static void lambda$getDialogPhotos$38(MessagesStorage arg11, long arg12, int arg14, int arg15, int arg16) {
        // Method was not decompiled
    }

    public static void lambda$getDialogReadMax$123(MessagesStorage arg4, boolean arg5, long arg6, Integer[] arg8, CountDownLatch arg9) {
        SQLiteCursor v1 = null;
        if(arg5) {
            try {
                SQLiteDatabase v5_2 = arg4.database;
                String v6 = "SELECT outbox_max FROM dialogs WHERE did = " + arg6;
                Object[] v7 = new Object[0];
                goto label_11;
            label_18:
                v5_2 = arg4.database;
                v6 = "SELECT inbox_max FROM dialogs WHERE did = " + arg6;
                v7 = new Object[0];
            label_11:
                v1 = v5_2.b(v6, v7);
                if(v1.a()) {
                    arg8[0] = Integer.valueOf(v1.b(0));
                }

                goto label_32;
            }
            catch(Exception v5) {
                goto label_17;
            }
            catch(Throwable v5_1) {
                goto label_39;
            }
        }
        else {
            goto label_18;
        }

        goto label_11;
    label_32:
        if(v1 == null) {
            goto label_37;
        }

        goto label_36;
    label_39:
        if(v1 != null) {
            v1.b();
        }

        throw v5_1;
    label_17:
        try {
            FileLog.e(((Throwable)v5));
            if(v1 == null) {
                goto label_37;
            }
        }
        catch(Throwable v5_1) {
            goto label_39;
        }

    label_36:
        v1.b();
    label_37:
        arg9.countDown();
    }

    public static void lambda$getDialogs$118(MessagesStorage arg17, int arg18, int arg19) {
        Integer v0_5;
        Message v10_2;
        NativeByteBuffer v0_4;
        int v0_2;
        TL_messages_dialogs v16;
        TL_dialog v13;
        SQLiteCursor v7_1;
        int v15;
        int v14;
        int v9;
        LongSparseArray v6;
        ArrayList v5;
        ArrayList v4;
        ArrayList v3;
        ArrayList v2;
        MessagesStorage v1 = arg17;
        TL_messages_dialogs v11 = new TL_messages_dialogs();
        ArrayList v12 = new ArrayList();
        try {
            v2 = new ArrayList();
            v2.add(Integer.valueOf(UserConfig.getInstance(v1.currentAccount).getClientUserId()));
            v3 = new ArrayList();
            v4 = new ArrayList();
            v5 = new ArrayList();
            v6 = new LongSparseArray();
            SQLiteDatabase v0_1 = v1.database;
            Locale v7 = Locale.US;
            v9 = 2;
            Object[] v10 = new Object[v9];
            v14 = 0;
            v10[0] = Integer.valueOf(arg18);
            v15 = 1;
            v10[1] = Integer.valueOf(arg19);
            v7_1 = v0_1.b(String.format(v7, "SELECT d.did, d.last_mid, d.unread_count, d.date, m.data, m.read_state, m.mid, m.send_state, s.flags, m.date, d.pts, d.inbox_max, d.outbox_max, m.replydata, d.pinned, d.unread_count_i, d.flags FROM dialogs as d LEFT JOIN messages as m ON d.last_mid = m.mid LEFT JOIN dialog_settings as s ON d.did = s.did ORDER BY d.pinned DESC, d.date DESC LIMIT %d,%d", v10), new Object[0]);
            while(true) {
            label_34:
                if(!v7_1.a()) {
                    goto label_237;
                }

                v13 = new TL_dialog();
                v16 = v11;
                break;
            }
        }
        catch(Exception v0) {
            goto label_323;
        }

        try {
            v13.id = v7_1.d(v14);
            v13.top_message = v7_1.b(v15);
            v13.unread_count = v7_1.b(v9);
            v13.last_message_date = v7_1.b(3);
            v13.pts = v7_1.b(10);
            v0_2 = v13.pts == 0 || (((int)v13.id)) > 0 ? 0 : 1;
            v13.flags = v0_2;
            v13.read_inbox_max_id = v7_1.b(11);
            v13.read_outbox_max_id = v7_1.b(12);
            v13.pinnedNum = v7_1.b(14);
            boolean v0_3 = v13.pinnedNum != 0 ? true : false;
            v13.pinned = v0_3;
            v13.unread_mentions_count = v7_1.b(15);
            v0_3 = (v7_1.b(16) & v15) != 0 ? true : false;
            v13.unread_mark = v0_3;
            long v10_1 = v7_1.d(8);
            v13.notify_settings = new TL_peerNotifySettings();
            v9 = 32;
            if(((((int)v10_1)) & v15) != 0) {
                v13.notify_settings.mute_until = ((int)(v10_1 >> v9));
                if(v13.notify_settings.mute_until == 0) {
                    v13.notify_settings.mute_until = 2147483647;
                }
            }
        }
        catch(Exception v0) {
            v11 = v16;
            goto label_323;
        }

        v11 = v16;
        try {
            ((messages_Dialogs)v11).dialogs.add(v13);
            v0_4 = v7_1.g(4);
            if(v0_4 == null) {
                goto label_197;
            }

            v10_2 = Message.TLdeserialize(((AbstractSerializedData)v0_4), v0_4.readInt32(((boolean)v14)), ((boolean)v14));
            v10_2.readAttachPath(((AbstractSerializedData)v0_4), UserConfig.getInstance(v1.currentAccount).clientUserId);
            v0_4.reuse();
            if(v10_2 == null) {
                goto label_197;
            }

            MessageObject.setUnreadFlags(v10_2, v7_1.b(5));
            v10_2.id = v7_1.b(6);
            v0_2 = v7_1.b(9);
            if(v0_2 != 0) {
                v13.last_message_date = v0_2;
            }

            v10_2.send_state = v7_1.b(7);
            v10_2.dialog_id = v13.id;
            ((messages_Dialogs)v11).messages.add(v10_2);
            MessagesStorage.addUsersAndChatsFromMessage(v10_2, v2, v3);
        }
        catch(Exception v0) {
            goto label_323;
        }

        try {
            if(v10_2.reply_to_msg_id == 0) {
                goto label_197;
            }

            if(!(v10_2.action instanceof TL_messageActionPinMessage) && !(v10_2.action instanceof TL_messageActionPaymentSent) && !(v10_2.action instanceof TL_messageActionGameScore)) {
                goto label_197;
            }

            v0_2 = 13;
            if(!v7_1.a(v0_2)) {
                v0_4 = v7_1.g(v0_2);
                if(v0_4 != null) {
                    v10_2.replyMessage = Message.TLdeserialize(((AbstractSerializedData)v0_4), v0_4.readInt32(((boolean)v14)), ((boolean)v14));
                    v10_2.replyMessage.readAttachPath(((AbstractSerializedData)v0_4), UserConfig.getInstance(v1.currentAccount).clientUserId);
                    v0_4.reuse();
                    if(v10_2.replyMessage != null) {
                        if(MessageObject.isMegagroup(v10_2)) {
                            v10_2.replyMessage.flags |= -2147483648;
                        }

                        MessagesStorage.addUsersAndChatsFromMessage(v10_2.replyMessage, v2, v3);
                    }
                }
            }

            if(v10_2.replyMessage != null) {
                goto label_197;
            }

            long v8 = ((long)v10_2.reply_to_msg_id);
            if(v10_2.to_id.channel_id != 0) {
                v8 |= (((long)v10_2.to_id.channel_id)) << 32;
            }

            if(!v5.contains(Long.valueOf(v8))) {
                v5.add(Long.valueOf(v8));
            }

            v6.put(v13.id, v10_2);
            goto label_197;
        }
        catch(Exception v0) {
            try {
                FileLog.e(((Throwable)v0));
            label_197:
                v0_2 = ((int)v13.id);
                int v8_1 = ((int)(v13.id >> 32));
                if(v0_2 != 0) {
                    if(v8_1 == 1) {
                        if(!v3.contains(Integer.valueOf(v0_2))) {
                            v0_5 = Integer.valueOf(v0_2);
                        }
                        else {
                            goto label_230;
                        }
                    }
                    else if(v0_2 > 0) {
                        if(!v2.contains(Integer.valueOf(v0_2))) {
                            v2.add(Integer.valueOf(v0_2));
                        }
                        else {
                        }

                        goto label_230;
                    }
                    else {
                        v0_2 = -v0_2;
                        if(!v3.contains(Integer.valueOf(v0_2))) {
                            v0_5 = Integer.valueOf(v0_2);
                        }
                        else {
                            goto label_230;
                        }
                    }

                    v3.add(v0_5);
                }
                else {
                    if(v4.contains(Integer.valueOf(v8_1))) {
                        goto label_230;
                    }

                    v4.add(Integer.valueOf(v8_1));
                }

            label_230:
                v9 = 2;
                v14 = 0;
                v15 = 1;
                goto label_34;
            label_237:
                v7_1.b();
                if(!v5.isEmpty()) {
                    SQLiteCursor v0_6 = v1.database.b(String.format(Locale.US, "SELECT data, mid, date, uid FROM messages WHERE mid IN(%s)", TextUtils.join(",", ((Iterable)v5))), new Object[0]);
                    while(v0_6.a()) {
                        NativeByteBuffer v5_1 = v0_6.g(0);
                        if(v5_1 != null) {
                            Message v7_2 = Message.TLdeserialize(((AbstractSerializedData)v5_1), v5_1.readInt32(false), false);
                            v7_2.readAttachPath(((AbstractSerializedData)v5_1), UserConfig.getInstance(v1.currentAccount).clientUserId);
                            v5_1.reuse();
                            v7_2.id = v0_6.b(1);
                            v7_2.date = v0_6.b(2);
                            v7_2.dialog_id = v0_6.d(3);
                            MessagesStorage.addUsersAndChatsFromMessage(v7_2, v2, v3);
                            Object v13_1 = v6.get(v7_2.dialog_id);
                            if(v13_1 != null) {
                                ((Message)v13_1).replyMessage = v7_2;
                                v7_2.dialog_id = ((Message)v13_1).dialog_id;
                                if(MessageObject.isMegagroup(((Message)v13_1))) {
                                    ((Message)v13_1).replyMessage.flags |= -2147483648;
                                    continue;
                                }
                            }
                        }
                        else {
                        }
                    }

                    v0_6.b();
                }

                if(!v4.isEmpty()) {
                    v1.getEncryptedChatsInternal(TextUtils.join(",", ((Iterable)v4)), v12, v2);
                }

                if(!v3.isEmpty()) {
                    v1.getChatsInternal(TextUtils.join(",", ((Iterable)v3)), ((messages_Dialogs)v11).chats);
                }

                if(!v2.isEmpty()) {
                    v1.getUsersInternal(TextUtils.join(",", ((Iterable)v2)), ((messages_Dialogs)v11).users);
                }

                MessagesController.getInstance(v1.currentAccount).processLoadedDialogs(v11, v12, arg18, arg19, 1, false, false, true);
                return;
            }
            catch(Exception v0) {
            }
        }

    label_323:
        ((messages_Dialogs)v11).dialogs.clear();
        ((messages_Dialogs)v11).users.clear();
        ((messages_Dialogs)v11).chats.clear();
        v12.clear();
        FileLog.e(((Throwable)v0));
        MessagesController.getInstance(v1.currentAccount).processLoadedDialogs(v11, v12, 0, 100, 1, true, false, true);
    }

    public static void lambda$getDownloadQueue$98(MessagesStorage arg8, int arg9) {
        Document v3_1;
        try {
            ArrayList v0 = new ArrayList();
            SQLiteCursor v1 = arg8.database.b(String.format(Locale.US, "SELECT uid, type, data FROM download_queue WHERE type = %d ORDER BY date DESC LIMIT 3", Integer.valueOf(arg9)), new Object[0]);
            while(v1.a()) {
                DownloadObject v2 = new DownloadObject();
                v2.type = v1.b(1);
                v2.id = v1.d(0);
                NativeByteBuffer v3 = v1.g(2);
                if(v3 != null) {
                    MessageMedia v5 = MessageMedia.TLdeserialize(((AbstractSerializedData)v3), v3.readInt32(false), false);
                    v3.reuse();
                    if(v5.document != null) {
                        v3_1 = v5.document;
                        goto label_30;
                    }
                    else if(v5.photo != null) {
                        PhotoSize v3_2 = FileLoader.getClosestPhotoSizeWithSize(v5.photo.sizes, AndroidUtilities.getPhotoSize());
                    label_30:
                        v2.object = ((TLObject)v3_1);
                    }

                    boolean v3_3 = v5.ttl_seconds != 0 ? true : false;
                    v2.secret = v3_3;
                }

                v0.add(v2);
            }

            v1.b();
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$_nO2PYsDpDcTJSVHc515VkqSt48(arg8, arg9, v0));
        }
        catch(Exception v9) {
            FileLog.e(((Throwable)v9));
        }
    }

    public static void lambda$getEncryptedChat$92(MessagesStorage arg4, int arg5, ArrayList arg6, CountDownLatch arg7) {
        try {
            ArrayList v0 = new ArrayList();
            ArrayList v1 = new ArrayList();
            arg4.getEncryptedChatsInternal("" + arg5, v1, v0);
            if(v1.isEmpty()) {
                goto label_32;
            }

            if(v0.isEmpty()) {
                goto label_32;
            }

            ArrayList v5_2 = new ArrayList();
            arg4.getUsersInternal(TextUtils.join(",", ((Iterable)v0)), v5_2);
            if(v5_2.isEmpty()) {
                goto label_32;
            }

            arg6.add(v1.get(0));
            arg6.add(v5_2.get(0));
        }
        catch(Throwable v5) {
        }
        catch(Exception v5_1) {
            try {
                FileLog.e(((Throwable)v5_1));
            }
            catch(Throwable v5) {
                arg7.countDown();
                throw v5;
            }
        }

    label_32:
        arg7.countDown();
    }

    public static void lambda$getMessages$82(MessagesStorage arg54, int arg55, int arg56, boolean arg57, long arg58, int arg60, int arg61, int arg62, int arg63, int arg64) {
        // Method was not decompiled
    }

    public static void lambda$getNewTask$46(MessagesStorage arg9, ArrayList arg10) {
        if(arg10 != null) {
            try {
                arg9.database.a(String.format(Locale.US, "DELETE FROM enc_tasks_v2 WHERE mid IN(%s)", TextUtils.join(",", ((Iterable)arg10)))).c().e();
            label_17:
                SQLiteCursor v2 = arg9.database.b("SELECT mid, date FROM enc_tasks_v2 WHERE date = (SELECT min(date) FROM enc_tasks_v2)", new Object[0]);
                int v3 = -1;
                ArrayList v4 = null;
                int v10_1 = -1;
                int v5;
                for(v5 = 0; v2.a(); v5 = v7) {
                    long v5_1 = v2.d(0);
                    if(v10_1 == v3) {
                        v10_1 = ((int)(v5_1 >> 32));
                        if(v10_1 < 0) {
                            v10_1 = 0;
                        }
                    }

                    int v7 = v2.b(1);
                    if(v4 == null) {
                        v4 = new ArrayList();
                    }

                    v4.add(Integer.valueOf(((int)v5_1)));
                }

                v2.b();
                MessagesController.getInstance(arg9.currentAccount).processLoadedDeleteTask(v5, v4, v10_1);
                return;
            label_16:
                goto label_49;
            }
            catch(Exception v10) {
                goto label_16;
            }
        }

        goto label_17;
    label_49:
        FileLog.e(((Throwable)v10));
    }

    public static void lambda$getSentFile$84(MessagesStorage arg5, String arg6, int arg7, ArrayList arg8, CountDownLatch arg9) {
        Document v7_1;
        try {
            arg6 = Utilities.MD5(arg6);
            if(arg6 == null) {
                goto label_32;
            }

            SQLiteCursor v6_2 = arg5.database.b(String.format(Locale.US, "SELECT data FROM sent_files_v2 WHERE uid = \'%s\' AND type = %d", arg6, Integer.valueOf(arg7)), new Object[0]);
            if(v6_2.a()) {
                NativeByteBuffer v7 = v6_2.g(0);
                if(v7 != null) {
                    MessageMedia v0 = MessageMedia.TLdeserialize(((AbstractSerializedData)v7), v7.readInt32(false), false);
                    v7.reuse();
                    if((v0 instanceof TL_messageMediaDocument)) {
                        v7_1 = ((TL_messageMediaDocument)v0).document;
                    }
                    else if((v0 instanceof TL_messageMediaPhoto)) {
                        Photo v7_2 = ((TL_messageMediaPhoto)v0).photo;
                    }
                    else {
                        goto label_31;
                    }

                    arg8.add(v7_1);
                }
            }

        label_31:
            v6_2.b();
        }
        catch(Throwable v6) {
        }
        catch(Exception v6_1) {
            try {
                FileLog.e(((Throwable)v6_1));
            }
            catch(Throwable v6) {
                arg9.countDown();
                throw v6;
            }
        }

    label_32:
        arg9.countDown();
    }

    public static void lambda$getUnreadMention$80(MessagesStorage arg4, long arg5, IntCallback arg7) {
        try {
            SQLiteDatabase v0 = arg4.database;
            Locale v1 = Locale.US;
            Object[] v3 = new Object[1];
            Long v5_1 = Long.valueOf(arg5);
            int v6 = 0;
            v3[0] = v5_1;
            SQLiteCursor v5_2 = v0.b(String.format(v1, "SELECT MIN(mid) FROM messages WHERE uid = %d AND mention = 1 AND read_state IN(0, 1)", v3), new Object[0]);
            if(v5_2.a()) {
                v6 = v5_2.b(0);
            }

            v5_2.b();
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$SG6r-0rzl2FBjI15u-VKOrX-oD0(arg7, v6));
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }
    }

    public static void lambda$getUnsentMessages$76(MessagesStorage arg18, int arg19) {
        int v12_1;
        int v11_1;
        MessagesStorage v1 = arg18;
        try {
            SparseArray v0_1 = new SparseArray();
            ArrayList v2 = new ArrayList();
            ArrayList v3 = new ArrayList();
            ArrayList v4 = new ArrayList();
            ArrayList v5 = new ArrayList();
            ArrayList v6 = new ArrayList();
            ArrayList v7 = new ArrayList();
            ArrayList v8 = new ArrayList();
            ArrayList v9 = new ArrayList();
            SQLiteDatabase v10 = v1.database;
            StringBuilder v11 = new StringBuilder();
            v11.append("SELECT m.read_state, m.data, m.send_state, m.mid, m.date, r.random_id, m.uid, s.seq_in, s.seq_out, m.ttl FROM messages as m LEFT JOIN randoms as r ON r.mid = m.mid LEFT JOIN messages_seq as s ON m.mid = s.mid WHERE (m.mid < 0 AND m.send_state = 1) OR (m.mid > 0 AND m.send_state = 3) ORDER BY m.mid DESC LIMIT ");
            v11.append(arg19);
            boolean v12 = false;
            SQLiteCursor v10_1 = v10.b(v11.toString(), new Object[0]);
            while(v10_1.a()) {
                NativeByteBuffer v13 = v10_1.g(1);
                if(v13 != null) {
                    Message v14 = Message.TLdeserialize(((AbstractSerializedData)v13), v13.readInt32(v12), v12);
                    v14.send_state = v10_1.b(2);
                    v14.readAttachPath(((AbstractSerializedData)v13), UserConfig.getInstance(v1.currentAccount).clientUserId);
                    v13.reuse();
                    if(v0_1.indexOfKey(v14.id) < 0) {
                        MessageObject.setUnreadFlags(v14, v10_1.b(((int)v12)));
                        v14.id = v10_1.b(3);
                        v14.date = v10_1.b(4);
                        int v15 = 5;
                        if(!v10_1.a(v15)) {
                            v14.random_id = v10_1.d(v15);
                        }

                        v14.dialog_id = v10_1.d(6);
                        v14.seq_in = v10_1.b(7);
                        v14.seq_out = v10_1.b(8);
                        v14.ttl = v10_1.b(9);
                        v2.add(v14);
                        v0_1.put(v14.id, v14);
                        int v17 = ((int)v14.dialog_id);
                        v11_1 = ((int)(v14.dialog_id >> 32));
                        if(v17 == 0) {
                            v12_1 = v17;
                            if(v9.contains(Integer.valueOf(v11_1))) {
                                goto label_115;
                            }

                            v9.add(Integer.valueOf(v11_1));
                        }
                        else if(v11_1 == 1) {
                            if(!v8.contains(Integer.valueOf(v17))) {
                                v8.add(Integer.valueOf(v17));
                            }

                            v12_1 = v17;
                        }
                        else {
                            if(v17 < 0) {
                                v12_1 = v17;
                                v11_1 = -v12_1;
                                if(v7.contains(Integer.valueOf(v11_1))) {
                                    goto label_115;
                                }

                                v7.add(Integer.valueOf(v11_1));
                                goto label_115;
                            }

                            v12_1 = v17;
                            if(v6.contains(Integer.valueOf(v12_1))) {
                                goto label_115;
                            }

                            v6.add(Integer.valueOf(v12_1));
                        }

                    label_115:
                        MessagesStorage.addUsersAndChatsFromMessage(v14, v6, v7);
                        if(v14.send_state != 3) {
                            if((v14.to_id.channel_id != 0 || (MessageObject.isUnread(v14)) || v12_1 == 0) && v14.id <= 0) {
                                goto label_130;
                            }

                            v14.send_state = 0;
                        }
                        else {
                        label_130:
                        }

                        if(v12_1 != 0) {
                            goto label_138;
                        }

                        if(v10_1.a(v15)) {
                            goto label_138;
                        }

                        v14.random_id = v10_1.d(v15);
                    }
                    else {
                        goto label_137;
                    }
                }
                else {
                label_137:
                }

            label_138:
                v12 = false;
            }

            v11_1 = 0;
            v10_1.b();
            if(!v9.isEmpty()) {
                v1.getEncryptedChatsInternal(TextUtils.join(",", ((Iterable)v9)), v5, v6);
            }

            if(!v6.isEmpty()) {
                v1.getUsersInternal(TextUtils.join(",", ((Iterable)v6)), v3);
            }

            if(!v7.isEmpty() || !v8.isEmpty()) {
                StringBuilder v0_2 = new StringBuilder();
                int v6_1;
                for(v6_1 = 0; v6_1 < v7.size(); ++v6_1) {
                    Object v9_1 = v7.get(v6_1);
                    if(v0_2.length() != 0) {
                        v0_2.append(",");
                    }

                    v0_2.append(v9_1);
                }

                while(v11_1 < v8.size()) {
                    Object v6_2 = v8.get(v11_1);
                    if(v0_2.length() != 0) {
                        v0_2.append(",");
                    }

                    v0_2.append(-((Integer)v6_2).intValue());
                    ++v11_1;
                }

                v1.getChatsInternal(v0_2.toString(), v4);
            }

            SendMessagesHelper.getInstance(v1.currentAccount).processUnsentMessages(v2, v3, v4, v5);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$getUserSync$125(MessagesStorage arg1, User[] arg2, int arg3, CountDownLatch arg4) {
        arg2[0] = arg1.getUser(arg3);
        arg4.countDown();
    }

    public static void lambda$getWallpapers$28(MessagesStorage arg5) {
        try {
            SQLiteCursor v0_1 = arg5.database.b("SELECT data FROM wallpapers WHERE 1", new Object[0]);
            ArrayList v1 = new ArrayList();
            while(v0_1.a()) {
                NativeByteBuffer v3 = v0_1.g(0);
                if(v3 == null) {
                    continue;
                }

                WallPaper v4 = WallPaper.TLdeserialize(((AbstractSerializedData)v3), v3.readInt32(false), false);
                v3.reuse();
                v1.add(v4);
            }

            v0_1.b();
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$EqUhjLVECv0E7irtSV3fkGR0khI(v1));
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$hasAuthMessage$91(MessagesStorage arg5, int arg6, boolean[] arg7, CountDownLatch arg8) {
        try {
            SQLiteCursor v6_2 = arg5.database.b(String.format(Locale.US, "SELECT mid FROM messages WHERE uid = 777000 AND date = %d AND mid < 0 LIMIT 1", Integer.valueOf(arg6)), new Object[0]);
            arg7[0] = v6_2.a();
            v6_2.b();
        }
        catch(Throwable v6) {
        }
        catch(Exception v6_1) {
            try {
                FileLog.e(((Throwable)v6_1));
            }
            catch(Throwable v6) {
                arg8.countDown();
                throw v6;
            }
        }

        arg8.countDown();
    }

    public static void lambda$isDialogHasMessages$90(MessagesStorage arg4, long arg5, boolean[] arg7, CountDownLatch arg8) {
        try {
            SQLiteCursor v5_2 = arg4.database.b(String.format(Locale.US, "SELECT mid FROM messages WHERE uid = %d LIMIT 1", Long.valueOf(arg5)), new Object[0]);
            arg7[0] = v5_2.a();
            v5_2.b();
        }
        catch(Throwable v5) {
        }
        catch(Exception v5_1) {
            try {
                FileLog.e(((Throwable)v5_1));
            }
            catch(Throwable v5) {
                arg8.countDown();
                throw v5;
            }
        }

        arg8.countDown();
    }

    public static void lambda$isMigratedChat$67(MessagesStorage arg3, int arg4, boolean[] arg5, CountDownLatch arg6) {
        try {
            SQLiteDatabase v0 = arg3.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("SELECT info FROM chat_settings_v2 WHERE uid = ");
            v1.append(arg4);
            SQLiteCursor v4_2 = v0.b(v1.toString(), new Object[0]);
            ChatFull v0_1 = null;
            new ArrayList();
            if(v4_2.a()) {
                NativeByteBuffer v2 = v4_2.g(0);
                if(v2 != null) {
                    v0_1 = ChatFull.TLdeserialize(((AbstractSerializedData)v2), v2.readInt32(false), false);
                    v2.reuse();
                }
            }

            v4_2.b();
            boolean v4_3 = !(v0_1 instanceof TL_channelFull) || v0_1.migrated_from_chat_id == 0 ? false : true;
            arg5[0] = v4_3;
            if(arg6 != null) {
                arg6.countDown();
            }
        }
        catch(Throwable v4) {
        }
        catch(Exception v4_1) {
            try {
                FileLog.e(((Throwable)v4_1));
                if(arg6 == null) {
                    return;
                }

                goto label_38;
            }
            catch(Throwable v4) {
                if(arg6 != null) {
                    arg6.countDown();
                }

                throw v4;
            }
        }

        if(arg6 != null) {
        label_38:
            arg6.countDown();
        }
    }

    public static void lambda$loadChannelAdmins$57(MessagesStorage arg4, int arg5) {
        try {
            SQLiteDatabase v0 = arg4.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("SELECT uid FROM channel_admins WHERE did = ");
            v1.append(arg5);
            SQLiteCursor v0_1 = v0.b(v1.toString(), new Object[0]);
            ArrayList v1_1 = new ArrayList();
            while(v0_1.a()) {
                v1_1.add(Integer.valueOf(v0_1.b(0)));
            }

            v0_1.b();
            MessagesController.getInstance(arg4.currentAccount).processLoadedChannelAdmins(v1_1, arg5, true);
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }
    }

    public static void lambda$loadChatInfo$68(MessagesStorage arg11, int arg12, CountDownLatch arg13, boolean arg14, boolean arg15) {
        ChannelParticipant v9;
        User v8_1;
        String v0_4;
        Object v5;
        StringBuilder v0_3;
        Exception v0_1;
        ChatFull v2_2;
        Throwable v8;
        ChatFull v6;
        NativeByteBuffer v2_1;
        SQLiteCursor v1_3;
        SQLiteDatabase v1_2;
        ArrayList v3 = new ArrayList();
        ChatFull v0 = null;
        MessageObject v7 = null;
        try {
            v1_2 = arg11.database;
            StringBuilder v2 = new StringBuilder();
            v2.append("SELECT info, pinned FROM chat_settings_v2 WHERE uid = ");
            v2.append(arg12);
            v1_3 = v1_2.b(v2.toString(), new Object[0]);
            if(v1_3.a()) {
                v2_1 = v1_3.g(0);
                if(v2_1 != null) {
                    v6 = ChatFull.TLdeserialize(((AbstractSerializedData)v2_1), v2_1.readInt32(false), false);
                    goto label_21;
                }
                else {
                    goto label_33;
                }
            }
            else {
                goto label_33;
            }

            goto label_34;
        }
        catch(Throwable v1) {
            v2_2 = v0;
            v8 = v1;
            goto label_184;
        }
        catch(Exception v1_1) {
            v2_2 = v0;
            v0_1 = v1_1;
            goto label_171;
        }

        try {
        label_21:
            v2_1.reuse();
            v6.pinned_msg_id = v1_3.b(1);
            v2_2 = v6;
            goto label_34;
        }
        catch(Throwable v0_2) {
            v8 = v0_2;
            v2_2 = v6;
            goto label_184;
        }
        catch(Exception v0_1) {
            v2_2 = v6;
            goto label_171;
        }

    label_33:
        v2_2 = v0;
        try {
        label_34:
            v1_3.b();
            if((v2_2 instanceof TL_chatFull)) {
                v0_3 = new StringBuilder();
                int v1_4;
                for(v1_4 = 0; v1_4 < v2_2.participants.participants.size(); ++v1_4) {
                    v5 = v2_2.participants.participants.get(v1_4);
                    if(v0_3.length() != 0) {
                        v0_3.append(",");
                    }

                    v0_3.append(((ChatParticipant)v5).user_id);
                }

                if(v0_3.length() == 0) {
                    goto label_142;
                }

                v0_4 = v0_3.toString();
                goto label_58;
            }

            if(!(v2_2 instanceof TL_channelFull)) {
                goto label_142;
            }

            v1_2 = arg11.database;
            StringBuilder v6_1 = new StringBuilder();
            v6_1.append("SELECT us.data, us.status, cu.data, cu.date FROM channel_users_v2 as cu LEFT JOIN users as us ON us.uid = cu.uid WHERE cu.did = ");
            v6_1.append(-arg12);
            v6_1.append(" ORDER BY cu.date DESC");
            v1_3 = v1_2.b(v6_1.toString(), new Object[0]);
            v2_2.participants = new TL_chatParticipants();
        label_77:
            while(!v1_3.a()) {
                goto label_121;
            }
        }
        catch(Exception v0_1) {
            goto label_163;
        }
        catch(Throwable v0_2) {
            goto label_183;
        }

        try {
            NativeByteBuffer v6_3 = v1_3.g(0);
            if(v6_3 != null) {
                v8_1 = User.TLdeserialize(((AbstractSerializedData)v6_3), v6_3.readInt32(false), false);
                v6_3.reuse();
            }
            else {
                v8_1 = ((User)v0);
            }

            v6_3 = v1_3.g(2);
            if(v6_3 != null) {
                v9 = ChannelParticipant.TLdeserialize(((AbstractSerializedData)v6_3), v6_3.readInt32(false), false);
                v6_3.reuse();
            }
            else {
                v9 = ((ChannelParticipant)v0);
            }

            if(v8_1 == null) {
                goto label_77;
            }

            if(v9 == null) {
                goto label_77;
            }

            if(v8_1.status != null) {
                v8_1.status.expires = v1_3.b(1);
            }

            v3.add(v8_1);
            v9.date = v1_3.b(3);
            TL_chatChannelParticipant v6_4 = new TL_chatChannelParticipant();
            v6_4.user_id = v9.user_id;
            v6_4.date = v9.date;
            v6_4.inviter_id = v9.inviter_id;
            v6_4.channelParticipant = v9;
            v2_2.participants.participants.add(v6_4);
            goto label_77;
        }
        catch(Throwable v0_2) {
        }
        catch(Exception v6_2) {
            try {
                FileLog.e(((Throwable)v6_2));
                goto label_77;
            label_121:
                v1_3.b();
                v0_3 = new StringBuilder();
                for(v1_4 = 0; v1_4 < v2_2.bot_info.size(); ++v1_4) {
                    v5 = v2_2.bot_info.get(v1_4);
                    if(v0_3.length() != 0) {
                        v0_3.append(",");
                    }

                    v0_3.append(((BotInfo)v5).user_id);
                }

                if(v0_3.length() != 0) {
                    v0_4 = v0_3.toString();
                label_58:
                    arg11.getUsersInternal(v0_4, v3);
                }

            label_142:
                if(arg13 != null) {
                    arg13.countDown();
                }

                if(((v2_2 instanceof TL_channelFull)) && v2_2.pinned_msg_id != 0) {
                    v7 = DataQuery.getInstance(arg11.currentAccount).loadPinnedMessage(arg12, v2_2.pinned_msg_id, false);
                }

                goto label_153;
            }
            catch(Throwable v0_2) {
            label_183:
                v8 = v0_2;
            }
            catch(Exception v0_1) {
            label_163:
                try {
                label_171:
                    FileLog.e(((Throwable)v0_1));
                }
                catch(Throwable v0_2) {
                    goto label_183;
                }

                MessagesController.getInstance(arg11.currentAccount).processChatInfo(arg12, v2_2, v3, true, arg14, arg15, v7);
                if(arg13 == null) {
                    return;
                }

                goto label_180;
            }
        }

    label_184:
        MessagesController.getInstance(arg11.currentAccount).processChatInfo(arg12, v2_2, v3, true, arg14, arg15, null);
        if(arg13 != null) {
            arg13.countDown();
        }

        throw v8;
    label_153:
        MessagesController.getInstance(arg11.currentAccount).processChatInfo(arg12, v2_2, v3, true, arg14, arg15, v7);
        if(arg13 != null) {
        label_180:
            arg13.countDown();
        }
    }

    public static void lambda$loadPendingTasks$15(MessagesStorage arg14) {
        -$$Lambda$MessagesStorage$OTCbnyNXoirwhvbsu8TFVflzodM v3_2;
        -$$Lambda$MessagesStorage$EuuPlYsIg_jeReoMzIbm6stO0ag v11_1;
        TL_messages_deleteMessages v8_1;
        NativeByteBuffer v1;
        SQLiteCursor v0_1;
        try {
            v0_1 = arg14.database.b("SELECT id, data FROM pending_tasks WHERE 1", new Object[0]);
            while(true) {
            label_5:
                if(!v0_1.a()) {
                    goto label_139;
                }

                long v9 = v0_1.d(0);
                v1 = v0_1.g(1);
                if(v1 == null) {
                    continue;
                }

                int v3 = v1.readInt32(false);
                switch(v3) {
                    case 0: {
                        goto label_130;
                    }
                    case 1: {
                        goto label_119;
                    }
                    case 3: {
                        goto label_67;
                    }
                    case 4: {
                        goto label_57;
                    }
                    case 6: {
                        goto label_43;
                    }
                    case 7: {
                        goto label_25;
                    }
                    case 9: {
                        goto label_14;
                    }
                    case 2: 
                    case 5: 
                    case 8: 
                    case 10: {
                        goto label_78;
                    }
                }

                goto label_137;
            label_130:
                Chat v3_1 = Chat.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false);
                if(v3_1 == null) {
                    goto label_137;
                }

                Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesStorage$X8wmcVmkWlOOmT7hyCwq34C_3HA(arg14, v3_1, v9));
                goto label_137;
            label_67:
                SendMessagesHelper.getInstance(arg14.currentAccount).sendGame(InputPeer.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false), InputMedia.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false), v1.readInt64(false), v9);
                goto label_137;
            label_119:
                Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesStorage$0pEDcAF09KSwzKWD9sU7FMqhzD0(arg14, v1.readInt32(false), v1.readInt32(false), v9));
                goto label_137;
            label_57:
                -$$Lambda$MessagesStorage$AuH1gRs2iXmoSCC0c3xvFDAEwcM v11 = new -$$Lambda$MessagesStorage$AuH1gRs2iXmoSCC0c3xvFDAEwcM(arg14, v1.readInt64(false), v1.readBool(false), InputPeer.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false), v9);
                goto label_23;
            label_25:
                int v5 = v1.readInt32(false);
                v3 = v1.readInt32(false);
                TL_messages_deleteMessages v4 = TL_messages_deleteMessages.TLdeserialize(((AbstractSerializedData)v1), v3, false);
                if(v4 == null) {
                    TL_channels_deleteMessages v8 = TL_channels_deleteMessages.TLdeserialize(((AbstractSerializedData)v1), v3, false);
                }
                else {
                    v8_1 = v4;
                }

                if((((TL_channels_deleteMessages)v8_1)) == null) {
                    arg14.removePendingTask(v9);
                    goto label_137;
                }

                v11_1 = new -$$Lambda$MessagesStorage$EuuPlYsIg_jeReoMzIbm6stO0ag(arg14, v5, v9, ((TLObject)v8_1));
                goto label_23;
            label_43:
                Utilities.stageQueue.postRunnable(new -$$Lambda$MessagesStorage$veltQ-QzWYSSmgAGGDUTY-jvHoM(arg14, v1.readInt32(false), v1.readInt32(false), v9, InputChannel.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false)));
                goto label_137;
            label_78:
                TL_dialog v5_1 = new TL_dialog();
                v5_1.id = v1.readInt64(false);
                v5_1.top_message = v1.readInt32(false);
                v5_1.read_inbox_max_id = v1.readInt32(false);
                v5_1.read_outbox_max_id = v1.readInt32(false);
                v5_1.unread_count = v1.readInt32(false);
                v5_1.last_message_date = v1.readInt32(false);
                v5_1.pts = v1.readInt32(false);
                v5_1.flags = v1.readInt32(false);
                if(v3 >= 5) {
                    v5_1.pinned = v1.readBool(false);
                    v5_1.pinnedNum = v1.readInt32(false);
                }

                if(v3 >= 8) {
                    v5_1.unread_mentions_count = v1.readInt32(false);
                }

                if(v3 >= 10) {
                    v5_1.unread_mark = v1.readBool(false);
                }

                -$$Lambda$MessagesStorage$fAveEai337A6-EXAwvhcRUwb9DM v11_2 = new -$$Lambda$MessagesStorage$fAveEai337A6-EXAwvhcRUwb9DM(arg14, v5_1, InputPeer.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false), v9);
                goto label_23;
            label_14:
                v3_2 = new -$$Lambda$MessagesStorage$OTCbnyNXoirwhvbsu8TFVflzodM(arg14, v1.readInt64(false), InputPeer.TLdeserialize(((AbstractSerializedData)v1), v1.readInt32(false), false), v9);
                break;
            }
        }
        catch(Exception v0) {
            goto label_142;
        }

        -$$Lambda$MessagesStorage$OTCbnyNXoirwhvbsu8TFVflzodM v11_3 = v3_2;
        try {
        label_23:
            AndroidUtilities.runOnUIThread(((Runnable)v11_1));
        label_137:
            v1.reuse();
            goto label_5;
        label_139:
            v0_1.b();
        }
        catch(Exception v0) {
        label_142:
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$loadUnreadMessages$20(MessagesStorage arg21) {
        // Method was not decompiled
    }

    public static void lambda$loadWebRecent$23(MessagesStorage arg8, int arg9) {
        try {
            SQLiteDatabase v0 = arg8.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("SELECT id, image_url, thumb_url, local_url, width, height, size, date, document FROM web_recent_v3 WHERE type = ");
            v1.append(arg9);
            v1.append(" ORDER BY date DESC");
            SQLiteCursor v0_1 = v0.b(v1.toString(), new Object[0]);
            ArrayList v1_1 = new ArrayList();
            while(v0_1.a()) {
                SearchImage v3 = new SearchImage();
                v3.id = v0_1.e(0);
                v3.imageUrl = v0_1.e(1);
                v3.thumbUrl = v0_1.e(2);
                v3.localUrl = v0_1.e(3);
                v3.width = v0_1.b(4);
                v3.height = v0_1.b(5);
                v3.size = v0_1.b(6);
                v3.date = v0_1.b(7);
                int v4 = 8;
                if(!v0_1.a(v4)) {
                    NativeByteBuffer v4_1 = v0_1.g(v4);
                    if(v4_1 != null) {
                        int v5 = v4_1.readInt32(false);
                        v3.document = Document.TLdeserialize(((AbstractSerializedData)v4_1), v5, false);
                        if(v3.document == null) {
                            v3.photo = Photo.TLdeserialize(((AbstractSerializedData)v4_1), v5, false);
                            if(v3.photo != null) {
                                PhotoSize v5_1 = FileLoader.getClosestPhotoSizeWithSize(v3.photo.sizes, AndroidUtilities.getPhotoSize());
                                PhotoSize v6 = FileLoader.getClosestPhotoSizeWithSize(v3.photo.sizes, 80);
                                v3.photoSize = v5_1;
                                v3.thumbPhotoSize = v6;
                            }
                        }

                        v4_1.reuse();
                    }
                }

                v3.type = arg9;
                v1_1.add(v3);
            }

            v0_1.b();
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$EBMWqVpB6bep1dlCl3aS9_p9Fxg(arg8, arg9, v1_1));
        }
        catch(Throwable v9) {
            FileLog.e(v9);
        }
    }

    public static void lambda$markMentionMessageAsRead$47(MessagesStorage arg6, int arg7, int arg8, long arg9) {
        long v0 = ((long)arg7);
        if(arg8 != 0) {
            v0 |= (((long)arg8)) << 32;
        }

        try {
            arg6.database.a(String.format(Locale.US, "UPDATE messages SET read_state = read_state | 2 WHERE mid = %d", Long.valueOf(v0))).c().e();
            SQLiteDatabase v7_1 = arg6.database;
            StringBuilder v8 = new StringBuilder();
            v8.append("SELECT unread_count_i FROM dialogs WHERE did = ");
            v8.append(arg9);
            SQLiteCursor v7_2 = v7_1.b(v8.toString(), new Object[0]);
            arg8 = v7_2.a() ? Math.max(0, v7_2.b(0) - 1) : 0;
            v7_2.b();
            arg6.database.a(String.format(Locale.US, "UPDATE dialogs SET unread_count_i = %d WHERE did = %d", Integer.valueOf(arg8), Long.valueOf(arg9))).c().e();
            LongSparseArray v7_3 = new LongSparseArray(1);
            v7_3.put(arg9, Integer.valueOf(arg8));
            MessagesController.getInstance(arg6.currentAccount).processDialogsUpdateRead(null, v7_3);
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
        }
    }

    public static void lambda$markMessageAsMention$48(MessagesStorage arg5, long arg6) {
        try {
            arg5.database.a(String.format(Locale.US, "UPDATE messages SET mention = 1, read_state = read_state & ~2 WHERE mid = %d", Long.valueOf(arg6))).c().e();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$markMessageAsSendError$106(MessagesStorage arg4, Message arg5) {
        try {
            long v0 = ((long)arg5.id);
            if(arg5.to_id.channel_id != 0) {
                v0 |= (((long)arg5.to_id.channel_id)) << 32;
            }

            SQLiteDatabase v5_1 = arg4.database;
            StringBuilder v2 = new StringBuilder();
            v2.append("UPDATE messages SET send_state = 2 WHERE mid = ");
            v2.append(v0);
            v5_1.a(v2.toString()).c().e();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }
    }

    public static void lambda$markMessagesAsDeleted$115(MessagesStorage arg0, ArrayList arg1, int arg2) {
        arg0.markMessagesAsDeletedInternal(arg1, arg2);
    }

    public static void lambda$markMessagesAsDeleted$116(MessagesStorage arg0, int arg1, int arg2) {
        arg0.markMessagesAsDeletedInternal(arg1, arg2);
    }

    public static void lambda$markMessagesAsDeletedByRandoms$113(MessagesStorage arg5, ArrayList arg6) {
        try {
            SQLiteCursor v6_1 = arg5.database.b(String.format(Locale.US, "SELECT mid FROM randoms WHERE random_id IN(%s)", TextUtils.join(",", ((Iterable)arg6))), new Object[0]);
            ArrayList v0 = new ArrayList();
            while(v6_1.a()) {
                v0.add(Integer.valueOf(v6_1.b(0)));
            }

            v6_1.b();
            if(v0.isEmpty()) {
                return;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$9SZSA2rVtmQbeXdEzwSuYgnLdXQ(arg5, v0));
            arg5.updateDialogsWithReadMessagesInternal(v0, null, null, null);
            arg5.markMessagesAsDeletedInternal(v0, 0);
            arg5.updateDialogsWithDeletedMessagesInternal(v0, null, 0);
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$markMessagesAsRead$111(MessagesStorage arg0, SparseLongArray arg1, SparseLongArray arg2, SparseIntArray arg3) {
        arg0.markMessagesAsReadInternal(arg1, arg2, arg3);
    }

    public static void lambda$markMessagesContentAsRead$110(MessagesStorage arg6, ArrayList arg7, int arg8) {
        try {
            String v7_1 = TextUtils.join(",", ((Iterable)arg7));
            arg6.database.a(String.format(Locale.US, "UPDATE messages SET read_state = read_state | 2 WHERE mid IN (%s)", v7_1)).c().e();
            if(arg8 == 0) {
                return;
            }

            SQLiteCursor v7_2 = arg6.database.b(String.format(Locale.US, "SELECT mid, ttl FROM messages WHERE mid IN (%s) AND ttl > 0", v7_1), new Object[0]);
            ArrayList v8 = null;
            while(v7_2.a()) {
                if(v8 == null) {
                    v8 = new ArrayList();
                }

                v8.add(Integer.valueOf(v7_2.b(0)));
            }

            if(v8 != null) {
                arg6.emptyMessagesMedia(v8);
            }

            v7_2.b();
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
        }
    }

    public static void lambda$new$0(MessagesStorage arg1) {
        arg1.openDatabase(true);
    }

    public static void lambda$null$10(MessagesStorage arg1, TL_dialog arg2, InputPeer arg3, long arg4) {
        MessagesController.getInstance(arg1.currentAccount).checkLastDialogMessage(arg2, arg3, arg4);
    }

    public static void lambda$null$101(MessagesStorage arg3, long arg4) {
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.removeAllMessagesFromDialog, new Object[]{Long.valueOf(arg4), Boolean.valueOf(true)});
    }

    public static void lambda$null$11(MessagesStorage arg8, long arg9, boolean arg11, InputPeer arg12, long arg13) {
        MessagesController.getInstance(arg8.currentAccount).pinDialog(arg9, arg11, arg12, arg13);
    }

    public static void lambda$null$112(MessagesStorage arg4, ArrayList arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.messagesDeleted, new Object[]{arg5, Integer.valueOf(0)});
    }

    public static void lambda$null$12(MessagesStorage arg7, int arg8, int arg9, long arg10, InputChannel arg12) {
        MessagesController.getInstance(arg7.currentAccount).getChannelDifference(arg8, arg9, arg10, arg12);
    }

    public static void lambda$null$13(MessagesStorage arg10, int arg11, long arg12, TLObject arg14) {
        MessagesController.getInstance(arg10.currentAccount).deleteMessages(null, null, null, arg11, true, arg12, arg14);
    }

    public static void lambda$null$14(MessagesStorage arg7, long arg8, InputPeer arg10, long arg11) {
        MessagesController.getInstance(arg7.currentAccount).markDialogAsUnread(arg8, arg10, arg11);
    }

    public static void lambda$null$19(MessagesStorage arg7, LongSparseArray arg8, ArrayList arg9, ArrayList arg10, ArrayList arg11, ArrayList arg12) {
        NotificationsController.getInstance(arg7.currentAccount).processLoadedUnreadMessages(arg8, arg9, arg10, arg11, arg12);
    }

    public static void lambda$null$2(MessagesStorage arg1) {
        MessagesController.getInstance(arg1.currentAccount).getDifference();
    }

    public static void lambda$null$22(MessagesStorage arg4, int arg5, ArrayList arg6) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.recentImagesDidLoaded, new Object[]{Integer.valueOf(arg5), arg6});
    }

    static void lambda$null$27(ArrayList arg4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.wallpapersDidLoaded, new Object[]{arg4});
    }

    public static void lambda$null$32(MessagesStorage arg1, ArrayList arg2, int arg3) {
        MessagesController.getInstance(arg1.currentAccount).markChannelDialogMessageAsDeleted(arg2, arg3);
    }

    public static void lambda$null$33(MessagesStorage arg4, ArrayList arg5, int arg6) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.messagesDeleted, new Object[]{arg5, Integer.valueOf(arg6)});
    }

    public static void lambda$null$35(MessagesStorage arg3) {
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch, new Object[0]);
    }

    public static void lambda$null$37(MessagesStorage arg9, photos_Photos arg10, int arg11, int arg12, long arg13, int arg15) {
        MessagesController.getInstance(arg9.currentAccount).processLoadedUserPhotos(arg10, arg11, arg12, arg13, true, arg15);
    }

    static int lambda$null$41(LongSparseArray arg2, Long arg3, Long arg4) {
        Object v3 = arg2.get(arg3.longValue());
        Object v2 = arg2.get(arg4.longValue());
        if(((Integer)v3).intValue() < ((Integer)v2).intValue()) {
            return 1;
        }

        if(((Integer)v3).intValue() > ((Integer)v2).intValue()) {
            return -1;
        }

        return 0;
    }

    public static void lambda$null$44(MessagesStorage arg6, ArrayList arg7) {
        int v1;
        for(v1 = 0; v1 < arg7.size(); ++v1) {
            NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.updateMessageMedia, new Object[]{arg7.get(v1)});
        }
    }

    public static void lambda$null$50(MessagesStorage arg3, boolean arg4, ArrayList arg5) {
        if(!arg4) {
            arg3.markMessagesContentAsRead(arg5, 0);
        }

        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.messagesReadContent, new Object[]{arg5});
    }

    public static void lambda$null$52(MessagesStorage arg4, ArrayList arg5) {
        arg4.markMessagesContentAsRead(arg5, 0);
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.messagesReadContent, new Object[]{arg5});
    }

    public static void lambda$null$55(MessagesStorage arg5, ChatFull arg6) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{arg6, Integer.valueOf(0), Boolean.valueOf(false), null});
    }

    public static void lambda$null$63(MessagesStorage arg5, ChatFull arg6) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{arg6, Integer.valueOf(0), Boolean.valueOf(false), null});
    }

    public static void lambda$null$65(MessagesStorage arg5, ChatFull arg6) {
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoaded, new Object[]{arg6, Integer.valueOf(0), Boolean.valueOf(false), null});
    }

    static void lambda$null$79(IntCallback arg0, int arg1) {
        arg0.run(arg1);
    }

    public static void lambda$null$8(MessagesStorage arg1, Chat arg2, long arg3) {
        MessagesController.getInstance(arg1.currentAccount).loadUnknownChannel(arg2, arg3);
    }

    static int lambda$null$81(Message arg4, Message arg5) {
        int v2 = -1;
        if(arg4.id <= 0 || arg5.id <= 0) {
            if(arg4.id < 0 && arg5.id < 0) {
                if(arg4.id < arg5.id) {
                    return v2;
                }
                else if(arg4.id > arg5.id) {
                    return 1;
                }
                else {
                    return 0;
                }
            }

            if(arg4.date > arg5.date) {
                return v2;
            }

            if(arg4.date >= arg5.date) {
                return 0;
            }

            return 1;
        }
        else if(arg4.id > arg5.id) {
            return v2;
        }
        else if(arg4.id < arg5.id) {
            return 1;
        }

        return 0;
    }

    public static void lambda$null$9(MessagesStorage arg7, int arg8, int arg9, long arg10) {
        MessagesController.getInstance(arg7.currentAccount).getChannelDifference(arg8, arg9, arg10, null);
    }

    public static void lambda$null$97(MessagesStorage arg1, int arg2, ArrayList arg3) {
        DownloadController.getInstance(arg1.currentAccount).processDownloadObjects(arg2, arg3);
    }

    public static void lambda$null$99(MessagesStorage arg4, ArrayList arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.didReceivedWebpages, new Object[]{arg5});
    }

    public static void lambda$overwriteChannel$102(MessagesStorage arg10, int arg11, int arg12, TL_updates_channelDifferenceTooLong arg13) {
        int v6;
        int v3_1;
        long v0 = ((long)(-arg11));
        try {
            SQLiteDatabase v2 = arg10.database;
            StringBuilder v3 = new StringBuilder();
            v3.append("SELECT pts, pinned FROM dialogs WHERE did = ");
            v3.append(v0);
            SQLiteCursor v2_1 = v2.b(v3.toString(), new Object[0]);
            if(v2_1.a()) {
                v3_1 = v2_1.b(1);
            label_20:
                v6 = 0;
            }
            else if(arg12 != 0) {
                v3_1 = 0;
                v6 = 1;
            }
            else {
                v3_1 = 0;
                goto label_20;
            }

            v2_1.b();
            v2 = arg10.database;
            StringBuilder v7 = new StringBuilder();
            v7.append("DELETE FROM messages WHERE uid = ");
            v7.append(v0);
            v2.a(v7.toString()).c().e();
            v2 = arg10.database;
            v7 = new StringBuilder();
            v7.append("DELETE FROM bot_keyboard WHERE uid = ");
            v7.append(v0);
            v2.a(v7.toString()).c().e();
            v2 = arg10.database;
            v7 = new StringBuilder();
            v7.append("DELETE FROM media_counts_v2 WHERE uid = ");
            v7.append(v0);
            v2.a(v7.toString()).c().e();
            v2 = arg10.database;
            v7 = new StringBuilder();
            v7.append("DELETE FROM media_v2 WHERE uid = ");
            v7.append(v0);
            v2.a(v7.toString()).c().e();
            v2 = arg10.database;
            v7 = new StringBuilder();
            v7.append("DELETE FROM messages_holes WHERE uid = ");
            v7.append(v0);
            v2.a(v7.toString()).c().e();
            v2 = arg10.database;
            v7 = new StringBuilder();
            v7.append("DELETE FROM media_holes_v2 WHERE uid = ");
            v7.append(v0);
            v2.a(v7.toString()).c().e();
            ArrayList v7_1 = null;
            DataQuery.getInstance(arg10.currentAccount).clearBotKeyboard(v0, v7_1);
            TL_messages_dialogs v2_2 = new TL_messages_dialogs();
            v2_2.chats.addAll(arg13.chats);
            v2_2.users.addAll(arg13.users);
            v2_2.messages.addAll(arg13.messages);
            TL_dialog v8 = new TL_dialog();
            v8.id = v0;
            v8.flags = 1;
            v8.peer = new TL_peerChannel();
            v8.peer.channel_id = arg11;
            v8.top_message = arg13.top_message;
            v8.read_inbox_max_id = arg13.read_inbox_max_id;
            v8.read_outbox_max_id = arg13.read_outbox_max_id;
            v8.unread_count = arg13.unread_count;
            v8.unread_mentions_count = arg13.unread_mentions_count;
            v8.notify_settings = ((PeerNotifySettings)v7_1);
            boolean v9 = v3_1 != 0 ? true : false;
            v8.pinned = v9;
            v8.pinnedNum = v3_1;
            v8.pts = arg13.pts;
            v2_2.dialogs.add(v8);
            arg10.putDialogsInternal(((messages_Dialogs)v2_2), 0);
            arg10.updateDialogsWithDeletedMessages(new ArrayList(), v7_1, false, arg11);
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$KIpJJp_BAvwQEG8H55QK7Q2J-40(arg10, v0));
            if(v6 == 0) {
                return;
            }

            if(arg12 == 1) {
                MessagesController.getInstance(arg10.currentAccount).checkChannelInviter(arg11);
                return;
            }

            MessagesController.getInstance(arg10.currentAccount).generateJoinMessage(arg11, false);
        }
        catch(Exception v11) {
            FileLog.e(((Throwable)v11));
        }
    }

    public static void lambda$processPendingRead$69(MessagesStorage arg14, long arg15, long arg17, boolean arg19, long arg20) {
        int v10_1;
        SQLitePreparedStatement v0_4;
        long v10;
        int v4_1;
        MessagesStorage v1 = arg14;
        long v2 = arg15;
        try {
            SQLiteDatabase v0_1 = v1.database;
            StringBuilder v4 = new StringBuilder();
            v4.append("SELECT unread_count, inbox_max, last_mid FROM dialogs WHERE did = ");
            v4.append(v2);
            int v5 = 0;
            SQLiteCursor v0_2 = v0_1.b(v4.toString(), new Object[0]);
            long v6 = 0;
            int v8 = 2;
            if(v0_2.a()) {
                v4_1 = v0_2.b(0);
                v6 = ((long)v0_2.b(1));
                v10 = v0_2.d(v8);
            }
            else {
                v10 = v6;
                v4_1 = 0;
            }

            v0_2.b();
            v1.database.d();
            int v0_3 = ((int)v2);
            if(v0_3 != 0) {
                v6 = Math.max(v6, ((long)(((int)arg17))));
                if(arg19) {
                    v6 |= (((long)(-v0_3))) << 32;
                }

                v0_4 = v1.database.a("UPDATE messages SET read_state = read_state | 1 WHERE uid = ? AND mid <= ? AND read_state IN(0,2) AND out = 0");
                v0_4.d();
                v0_4.a(1, v2);
                v0_4.a(v8, v6);
                v0_4.b();
                v0_4.e();
                if(v6 >= v10) {
                    goto label_86;
                }

                v0_2 = v1.database.b("SELECT changes()", new Object[0]);
                v10_1 = v0_2.a() ? v0_2.b(0) : 0;
                v0_2.b();
                goto label_59;
            }
            else {
                v6 = ((long)(((int)arg20)));
                v0_4 = v1.database.a("UPDATE messages SET read_state = read_state | 1 WHERE uid = ? AND mid >= ? AND read_state IN(0,2) AND out = 0");
                v0_4.d();
                v0_4.a(1, v2);
                v0_4.a(v8, v6);
                v0_4.b();
                v0_4.e();
                if(v6 <= v10) {
                    goto label_86;
                }

                v0_2 = v1.database.b("SELECT changes()", new Object[0]);
                v10_1 = v0_2.a() ? v0_2.b(0) : 0;
                v0_2.b();
            label_59:
                v5 = Math.max(0, v4_1 - v10_1);
            }

        label_86:
            v0_4 = v1.database.a("UPDATE dialogs SET unread_count = ?, inbox_max = ? WHERE did = ?");
            v0_4.d();
            v0_4.a(1, v5);
            v0_4.a(v8, ((int)v6));
            v0_4.a(3, v2);
            v0_4.b();
            v0_4.e();
            v1.database.e();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$putBlockedUsers$31(MessagesStorage arg4, boolean arg5, SparseIntArray arg6) {
        if(arg5) {
            try {
                arg4.database.a("DELETE FROM blocked_users WHERE 1").c().e();
            label_9:
                arg4.database.d();
                SQLitePreparedStatement v5_1 = arg4.database.a("REPLACE INTO blocked_users VALUES(?)");
                int v0 = 0;
                int v1 = arg6.size();
                while(v0 < v1) {
                    v5_1.d();
                    v5_1.a(1, arg6.keyAt(v0));
                    v5_1.b();
                    ++v0;
                }

                v5_1.e();
                arg4.database.e();
                return;
            label_8:
                goto label_28;
            }
            catch(Exception v5) {
                goto label_8;
            }
        }

        goto label_9;
    label_28:
        FileLog.e(((Throwable)v5));
    }

    public static void lambda$putCachedPhoneBook$73(MessagesStorage arg10, HashMap arg11, boolean arg12) {
        Object v2;
        int v3;
        try {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d(arg10.currentAccount + " save contacts to db " + arg11.size());
            }

            arg10.database.a("DELETE FROM user_contacts_v7 WHERE 1").c().e();
            arg10.database.a("DELETE FROM user_phones_v7 WHERE 1").c().e();
            arg10.database.d();
            SQLitePreparedStatement v0_1 = arg10.database.a("REPLACE INTO user_contacts_v7 VALUES(?, ?, ?, ?, ?)");
            SQLitePreparedStatement v1 = arg10.database.a("REPLACE INTO user_phones_v7 VALUES(?, ?, ?, ?)");
            Iterator v11_1 = arg11.entrySet().iterator();
            while(true) {
            label_32:
                v3 = 0;
                if(!v11_1.hasNext()) {
                    goto label_80;
                }

                v2 = v11_1.next().getValue();
                if(((Contact)v2).phones.isEmpty()) {
                    continue;
                }

                if(!((Contact)v2).shortPhones.isEmpty()) {
                    break;
                }
            }

            v0_1.d();
            v0_1.a(1, ((Contact)v2).key);
            int v6 = 2;
            v0_1.a(v6, ((Contact)v2).contact_id);
            int v7 = 3;
            v0_1.a(v7, ((Contact)v2).first_name);
            int v8 = 4;
            v0_1.a(v8, ((Contact)v2).last_name);
            v0_1.a(5, ((Contact)v2).imported);
            v0_1.b();
            goto label_61;
        label_80:
            v0_1.e();
            v1.e();
            arg10.database.e();
            if(!arg12) {
                return;
            }

            arg10.database.a("DROP TABLE IF EXISTS user_contacts_v6;").c().e();
            arg10.database.a("DROP TABLE IF EXISTS user_phones_v6;").c().e();
            arg10.getCachedPhoneBook(false);
            return;
            while(true) {
            label_61:
                if(v3 >= ((Contact)v2).phones.size()) {
                    goto label_32;
                }

                v1.d();
                v1.a(1, ((Contact)v2).key);
                v1.a(v6, ((Contact)v2).phones.get(v3));
                v1.a(v7, ((Contact)v2).shortPhones.get(v3));
                v1.a(v8, ((Contact)v2).phoneDeleted.get(v3).intValue());
                v1.b();
                ++v3;
            }
        }
        catch(Exception v11) {
            FileLog.e(((Throwable)v11));
        }
    }

    public static void lambda$putChannelAdmins$58(MessagesStorage arg5, int arg6, ArrayList arg7) {
        try {
            SQLiteDatabase v0 = arg5.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM channel_admins WHERE did = ");
            v1.append(arg6);
            v0.a(v1.toString()).c().e();
            arg5.database.d();
            SQLitePreparedStatement v0_1 = arg5.database.a("REPLACE INTO channel_admins VALUES(?, ?)");
            System.currentTimeMillis();
            int v1_1;
            for(v1_1 = 0; v1_1 < arg7.size(); ++v1_1) {
                v0_1.d();
                v0_1.a(1, arg6);
                v0_1.a(2, arg7.get(v1_1).intValue());
                v0_1.b();
            }

            v0_1.e();
            arg5.database.e();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$putChannelViews$103(MessagesStorage arg12, SparseArray arg13, boolean arg14) {
        try {
            arg12.database.d();
            SQLitePreparedStatement v0 = arg12.database.a("UPDATE messages SET media = max((SELECT media FROM messages WHERE mid = ?), ?) WHERE mid = ?");
            int v2;
            for(v2 = 0; v2 < arg13.size(); ++v2) {
                int v3 = arg13.keyAt(v2);
                Object v4 = arg13.get(v3);
                int v5;
                for(v5 = 0; v5 < ((SparseIntArray)v4).size(); ++v5) {
                    int v6 = ((SparseIntArray)v4).get(((SparseIntArray)v4).keyAt(v5));
                    long v7 = ((long)((SparseIntArray)v4).keyAt(v5));
                    if(arg14) {
                        v7 |= (((long)(-v3))) << 32;
                    }

                    v0.d();
                    v0.a(1, v7);
                    v0.a(2, v6);
                    v0.a(3, v7);
                    v0.b();
                }
            }

            v0.e();
            arg12.database.e();
        }
        catch(Exception v13) {
            FileLog.e(((Throwable)v13));
        }
    }

    public static void lambda$putContacts$70(MessagesStorage arg4, boolean arg5, ArrayList arg6) {
        if(arg5) {
            try {
                arg4.database.a("DELETE FROM contacts WHERE 1").c().e();
            label_9:
                arg4.database.d();
                SQLitePreparedStatement v5_1 = arg4.database.a("REPLACE INTO contacts VALUES(?, ?)");
                int v0;
                for(v0 = 0; v0 < arg6.size(); ++v0) {
                    Object v1 = arg6.get(v0);
                    v5_1.d();
                    v5_1.a(1, ((TL_contact)v1).user_id);
                    v5_1.a(2, ((TL_contact)v1).mutual);
                    v5_1.b();
                }

                v5_1.e();
                arg4.database.e();
                return;
            label_8:
                goto label_32;
            }
            catch(Exception v5) {
                goto label_8;
            }
        }

        goto label_9;
    label_32:
        FileLog.e(((Throwable)v5));
    }

    public static void lambda$putDialogPhotos$43(MessagesStorage arg6, int arg7, photos_Photos arg8) {
        try {
            SQLiteDatabase v0 = arg6.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM user_photos WHERE uid = ");
            v1.append(arg7);
            v0.a(v1.toString()).c().e();
            SQLitePreparedStatement v0_1 = arg6.database.a("REPLACE INTO user_photos VALUES(?, ?, ?)");
            Iterator v8 = arg8.photos.iterator();
            while(v8.hasNext()) {
                Object v1_1 = v8.next();
                if((v1_1 instanceof TL_photoEmpty)) {
                    continue;
                }

                v0_1.d();
                NativeByteBuffer v2 = new NativeByteBuffer(((Photo)v1_1).getObjectSize());
                ((Photo)v1_1).serializeToStream(((AbstractSerializedData)v2));
                v0_1.a(1, arg7);
                v0_1.a(2, ((Photo)v1_1).id);
                v0_1.a(3, v2);
                v0_1.b();
                v2.reuse();
            }

            v0_1.e();
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
        }
    }

    public static void lambda$putDialogs$122(MessagesStorage arg0, messages_Dialogs arg1, int arg2) {
        arg0.putDialogsInternal(arg1, arg2);
        try {
            arg0.loadUnreadMessages();
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }
    }

    public static void lambda$putEncryptedChat$93(MessagesStorage arg16, EncryptedChat arg17, User arg18, TL_dialog arg19) {
        MessagesStorage v1 = arg16;
        EncryptedChat v0 = arg17;
        User v2 = arg18;
        TL_dialog v3 = arg19;
        try {
            int v5 = 16;
            if((v0.key_hash == null || v0.key_hash.length < v5) && v0.auth_key != null) {
                v0.key_hash = AndroidUtilities.calcAuthKeyHash(v0.auth_key);
            }

            SQLitePreparedStatement v4 = v1.database.a("REPLACE INTO enc_chats VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            NativeByteBuffer v6 = new NativeByteBuffer(arg17.getObjectSize());
            int v8 = v0.a_or_b != null ? v0.a_or_b.length : 1;
            NativeByteBuffer v7 = new NativeByteBuffer(v8);
            int v10 = v0.auth_key != null ? v0.auth_key.length : 1;
            NativeByteBuffer v8_1 = new NativeByteBuffer(v10);
            int v11 = v0.future_auth_key != null ? v0.future_auth_key.length : 1;
            NativeByteBuffer v10_1 = new NativeByteBuffer(v11);
            int v12 = v0.key_hash != null ? v0.key_hash.length : 1;
            NativeByteBuffer v11_1 = new NativeByteBuffer(v12);
            v0.serializeToStream(((AbstractSerializedData)v6));
            v4.a(1, v0.id);
            v4.a(2, v2.id);
            v4.a(3, v1.formatUserSearchName(v2));
            int v2_1 = 4;
            v4.a(v2_1, v6);
            if(v0.a_or_b != null) {
                v7.writeBytes(v0.a_or_b);
            }

            if(v0.auth_key != null) {
                v8_1.writeBytes(v0.auth_key);
            }

            if(v0.future_auth_key != null) {
                v10_1.writeBytes(v0.future_auth_key);
            }

            if(v0.key_hash != null) {
                v11_1.writeBytes(v0.key_hash);
            }

            v4.a(5, v7);
            v4.a(6, v8_1);
            v4.a(7, v0.ttl);
            v4.a(8, v0.layer);
            v4.a(9, v0.seq_in);
            v4.a(10, v0.seq_out);
            v4.a(11, v0.key_use_count_out | v0.key_use_count_in << v5);
            v4.a(12, v0.exchange_id);
            v4.a(13, v0.key_create_date);
            v4.a(14, v0.future_key_fingerprint);
            v4.a(15, v10_1);
            v4.a(v5, v11_1);
            v4.a(17, v0.in_seq_no);
            v4.a(18, v0.admin_id);
            v4.a(19, v0.mtproto_seq);
            v4.b();
            v4.e();
            v6.reuse();
            v7.reuse();
            v8_1.reuse();
            v10_1.reuse();
            v11_1.reuse();
            if(v3 == null) {
                return;
            }

            SQLitePreparedStatement v0_2 = v1.database.a("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            v0_2.a(1, v3.id);
            v0_2.a(2, v3.last_message_date);
            v0_2.a(3, v3.unread_count);
            v0_2.a(v2_1, v3.top_message);
            v0_2.a(5, v3.read_inbox_max_id);
            v0_2.a(6, v3.read_outbox_max_id);
            v0_2.a(7, 0);
            v0_2.a(8, v3.unread_mentions_count);
            v0_2.a(9, v3.pts);
            v0_2.a(10, 0);
            v0_2.a(11, v3.pinnedNum);
            v0_2.a(12, v3.flags);
            v0_2.b();
            v0_2.e();
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public static void lambda$putMessages$105(MessagesStorage arg0, ArrayList arg1, boolean arg2, boolean arg3, int arg4, boolean arg5) {
        arg0.putMessagesInternal(arg1, arg2, arg3, arg4, arg5);
    }

    public static void lambda$putMessages$117(MessagesStorage arg25, messages_Messages arg26, int arg27, long arg28, int arg30, boolean arg31) {
        Object v1_3;
        SQLitePreparedStatement v0_4;
        int v12_3;
        int v3_1;
        messages_Messages v6_3;
        int v0_3;
        SQLitePreparedStatement v8_1;
        SQLiteCursor v0_2;
        SQLitePreparedStatement v24;
        StringBuilder v13_2;
        int v23;
        SQLitePreparedStatement v22;
        int v13_1;
        Object v21;
        int v20;
        Object v19;
        int v5;
        int v1;
        int v15;
        MessagesStorage v7 = arg25;
        messages_Messages v0 = arg26;
        int v8 = arg27;
        long v9 = arg28;
        int v11 = arg30;
        try {
            if(v0.messages.isEmpty()) {
                if(v8 == 0) {
                    v7.doneHolesInTable("messages_holes", v9, v11);
                    v7.doneHolesInMedia(v9, v11, -1);
                }

                return;
            }

            v7.database.d();
            int v14 = 3;
            if(v8 == 0) {
                v15 = v0.messages.get(v0.messages.size() - 1).id;
                arg25.closeHolesInTable("messages_holes", arg28, v15, arg30);
                goto label_36;
            }
            else if(v8 == 1) {
                int v6 = v0.messages.get(0).id;
                arg25.closeHolesInTable("messages_holes", arg28, arg30, v6);
                arg25.closeHolesInMedia(arg28, arg30, v6, -1);
            }
            else {
                if(v8 == v14) {
                    v1 = 4;
                }
                else if(v8 != 2) {
                    v1 = 4;
                    if(v8 == v1) {
                    }
                    else {
                        goto label_93;
                    }
                }
                else {
                    v1 = 4;
                }

                v11 = v11 != 0 || v8 == v1 ? v0.messages.get(0).id : 2147483647;
                v5 = v0.messages.get(v0.messages.size() - 1).id;
                v15 = v5;
                arg25.closeHolesInTable("messages_holes", arg28, v5, v11);
            label_36:
                arg25.closeHolesInMedia(arg28, v15, arg30, -1);
            }

        label_93:
            v1 = v0.messages.size();
            SQLitePreparedStatement v2 = v7.database.a("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
            SQLitePreparedStatement v3 = v7.database.a("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
            int v4 = 0;
            v5 = 0;
            SQLitePreparedStatement v6_1 = null;
            Object v11_1 = null;
            int v16 = 2147483647;
            while(v5 < v1) {
                Object v14_1 = v0.messages.get(v5);
                int v17 = v1;
                SQLitePreparedStatement v18 = v2;
                long v1_1 = ((long)((Message)v14_1).id);
                if(v4 == 0) {
                    v4 = ((Message)v14_1).to_id.channel_id;
                }

                if(((Message)v14_1).to_id.channel_id != 0) {
                    v19 = v14_1;
                    v1_1 |= (((long)v4)) << 32;
                }
                else {
                    v19 = v14_1;
                }

                if(v8 == -2) {
                    v20 = v4;
                    v21 = v11_1;
                    SQLiteCursor v4_1 = v7.database.b(String.format(Locale.US, "SELECT mid, data, ttl, mention, read_state, send_state FROM messages WHERE mid = %d", Long.valueOf(v1_1)), new Object[0]);
                    boolean v11_2 = v4_1.a();
                    if(v11_2) {
                        NativeByteBuffer v13 = v4_1.g(1);
                        if(v13 != null) {
                            Message v12 = Message.TLdeserialize(((AbstractSerializedData)v13), v13.readInt32(false), false);
                            v12.readAttachPath(((AbstractSerializedData)v13), UserConfig.getInstance(v7.currentAccount).clientUserId);
                            v13.reuse();
                            v13_1 = v4_1.b(5);
                            if(v12 == null) {
                                goto label_165;
                            }
                            else if(v13_1 != 3) {
                                v14_1 = v19;
                                ((Message)v14_1).attachPath = v12.attachPath;
                                ((Message)v14_1).ttl = v4_1.b(2);
                            }
                            else {
                                goto label_165;
                            }
                        }
                        else {
                        label_165:
                            v14_1 = v19;
                        }

                        boolean v12_1 = v4_1.b(3) != 0 ? true : false;
                        v13_1 = 4;
                        v8 = v4_1.b(v13_1);
                        if(v12_1 != ((Message)v14_1).mentioned) {
                            v22 = v6_1;
                            v13_1 = v16;
                            if(v13_1 == 2147483647) {
                                SQLiteDatabase v6_2 = v7.database;
                                v23 = v13_1;
                                v13_2 = new StringBuilder();
                                v24 = v3;
                                v13_2.append("SELECT unread_count_i FROM dialogs WHERE did = ");
                                v13_2.append(v9);
                                v0_2 = v6_2.b(v13_2.toString(), new Object[0]);
                                v16 = v0_2.a() ? v0_2.b(0) : v23;
                                v0_2.b();
                            }
                            else {
                                v24 = v3;
                                v16 = v13_1;
                            }

                            if(v12_1) {
                                if(v8 > 1) {
                                    goto label_223;
                                }

                                --v16;
                                goto label_223;
                            }

                            if(!((Message)v14_1).media_unread) {
                                goto label_223;
                            }

                            ++v16;
                            goto label_223;
                        }

                        v24 = v3;
                        v22 = v6_1;
                        v23 = v16;
                        goto label_222;
                    }
                    else {
                        v24 = v3;
                        v22 = v6_1;
                        v23 = v16;
                        v14_1 = v19;
                    label_222:
                        v16 = v23;
                    }

                label_223:
                    v4_1.b();
                    if(!v11_2) {
                        v3 = v18;
                        v8_1 = v24;
                        v0_3 = arg27;
                        v6_3 = arg26;
                        goto label_399;
                    }

                    v23 = v16;
                    goto label_239;
                }
                else {
                    v24 = v3;
                    v20 = v4;
                    v22 = v6_1;
                    v21 = v11_1;
                    v23 = v16;
                    v14_1 = v19;
                label_239:
                    v8 = 6;
                    if(v5 != 0 || !arg31) {
                        v6_3 = arg26;
                    }
                    else {
                        SQLiteDatabase v12_2 = v7.database;
                        v13_2 = new StringBuilder();
                        v13_2.append("SELECT pinned, unread_count_i, flags FROM dialogs WHERE did = ");
                        v13_2.append(v9);
                        v0_2 = v12_2.b(v13_2.toString(), new Object[0]);
                        if(v0_2.a()) {
                            v3_1 = v0_2.b(0);
                            v13_1 = v0_2.b(1);
                            v12_3 = v0_2.b(2);
                        }
                        else {
                            v3_1 = 0;
                            v12_3 = 0;
                            v13_1 = 0;
                        }

                        v0_2.b();
                        v0_4 = v7.database.a("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        v0_4.a(1, v9);
                        v0_4.a(2, ((Message)v14_1).date);
                        v0_4.a(3, 0);
                        v0_4.a(4, v1_1);
                        v0_4.a(5, ((Message)v14_1).id);
                        v0_4.a(v8, 0);
                        v0_4.a(7, v1_1);
                        v0_4.a(8, v13_1);
                        v6_3 = arg26;
                        v0_4.a(9, v6_3.pts);
                        v0_4.a(10, ((Message)v14_1).date);
                        v0_4.a(11, v3_1);
                        v0_4.a(12, v12_3);
                        v0_4.b();
                        v0_4.e();
                    }

                    v7.fixUnsupportedMedia(((Message)v14_1));
                    v18.d();
                    NativeByteBuffer v0_5 = new NativeByteBuffer(((Message)v14_1).getObjectSize());
                    ((Message)v14_1).serializeToStream(((AbstractSerializedData)v0_5));
                    v3 = v18;
                    v3.a(1, v1_1);
                    v3.a(2, v9);
                    v3.a(3, MessageObject.getUnreadFlags(((Message)v14_1)));
                    v3.a(4, ((Message)v14_1).send_state);
                    v3.a(5, ((Message)v14_1).date);
                    v3.a(v8, v0_5);
                    v3.a(7, MessageObject.isOut(((Message)v14_1)));
                    v3.a(8, ((Message)v14_1).ttl);
                    if((((Message)v14_1).flags & 1024) != 0) {
                        v4 = ((Message)v14_1).views;
                        v8 = 9;
                    }
                    else {
                        v8 = 9;
                        v4 = v7.getMessageMediaType(((Message)v14_1));
                    }

                    v3.a(v8, v4);
                    v3.a(10, 0);
                    v3.a(11, ((Message)v14_1).mentioned);
                    v3.b();
                    if(DataQuery.canAddMessageToMedia(((Message)v14_1))) {
                        v24.d();
                        v8_1 = v24;
                        v8_1.a(1, v1_1);
                        v8_1.a(2, v9);
                        v8_1.a(3, ((Message)v14_1).date);
                        v8_1.a(4, DataQuery.getMediaType(((Message)v14_1)));
                        v8_1.a(5, v0_5);
                        v8_1.b();
                    }
                    else {
                        v8_1 = v24;
                    }

                    v0_5.reuse();
                    if((((Message)v14_1).media instanceof TL_messageMediaWebPage)) {
                        v0_4 = v22 == null ? v7.database.a("REPLACE INTO webpage_pending VALUES(?, ?)") : v22;
                        v0_4.d();
                        v0_4.a(1, ((Message)v14_1).media.webpage.id);
                        v0_4.a(2, v1_1);
                        v0_4.b();
                        v22 = v0_4;
                    }

                    v0_3 = arg27;
                    if(v0_3 != 0 || !v7.isValidKeyboardToSave(((Message)v14_1))) {
                        v1_3 = v21;
                    label_397:
                        v21 = v1_3;
                    }
                    else {
                        if(v21 != null) {
                            Message v1_2 = ((Message)v21);
                            if(v1_2.id < ((Message)v14_1).id) {
                                goto label_394;
                            }

                            goto label_397;
                        }

                    label_394:
                        v21 = v14_1;
                    }

                    v16 = v23;
                }

            label_399:
                ++v5;
                v2 = v3;
                v3 = v8_1;
                v1 = v17;
                v4 = v20;
                v11_1 = v21;
                v8 = v0_3;
                v0 = v6_3;
                v6_1 = v22;
            }

            v22 = v6_1;
            v1_3 = v11_1;
            v23 = v16;
            v6_3 = v0;
            v2.e();
            v3.e();
            if(v22 != null) {
                v22.e();
            }

            if(v1_3 != null) {
                DataQuery.getInstance(v7.currentAccount).putBotKeyboard(v9, ((Message)v1_3));
            }

            v7.putUsersInternal(v6_3.users);
            v7.putChatsInternal(v6_3.chats);
            v0_3 = v23;
            if(v0_3 != 2147483647) {
                v7.database.a(String.format(Locale.US, "UPDATE dialogs SET unread_count_i = %d WHERE did = %d", Integer.valueOf(v0_3), Long.valueOf(arg28))).c().e();
                LongSparseArray v1_4 = new LongSparseArray(1);
                v1_4.put(v9, Integer.valueOf(v0_3));
                MessagesController.getInstance(v7.currentAccount).processDialogsUpdateRead(null, v1_4);
            }

            v7.database.e();
            if(!arg31) {
                return;
            }

            v7.updateDialogsWithDeletedMessages(new ArrayList(), null, false, v4);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public static void lambda$putMessagesInternal$104(MessagesStorage arg1, int arg2) {
        DownloadController.getInstance(arg1.currentAccount).newDownloadObjectsAvailable(arg2);
    }

    public static void lambda$putSentFile$85(MessagesStorage arg4, String arg5, TLObject arg6, int arg7) {
        SQLitePreparedStatement v6_1;
        int v6;
        TL_messageMediaPhoto v1;
        SQLitePreparedStatement v0 = null;
        try {
            arg5 = Utilities.MD5(arg5);
            if(arg5 != null) {
                if((arg6 instanceof Photo)) {
                    v1 = new TL_messageMediaPhoto();
                    ((MessageMedia)v1).photo = ((Photo)arg6);
                    v6 = ((MessageMedia)v1).flags;
                    goto label_10;
                }
                else if((arg6 instanceof Document)) {
                    TL_messageMediaDocument v1_1 = new TL_messageMediaDocument();
                    ((MessageMedia)v1_1).document = ((Document)arg6);
                    v6 = ((MessageMedia)v1_1).flags;
                label_10:
                    ((MessageMedia)v1).flags = v6 | 1;
                }
                else {
                    v1 = ((TL_messageMediaPhoto)v0);
                }

                if(v1 == null) {
                    return;
                }

                v6_1 = arg4.database.a("REPLACE INTO sent_files_v2 VALUES(?, ?, ?)");
                goto label_26;
            }
            else {
                goto label_45;
            }
        }
        catch(Throwable v5) {
        }
        catch(Exception v5_1) {
            goto label_52;
            try {
            label_26:
                v6_1.d();
                NativeByteBuffer v0_1 = new NativeByteBuffer(((MessageMedia)v1).getObjectSize());
                ((MessageMedia)v1).serializeToStream(((AbstractSerializedData)v0_1));
                v6_1.a(1, arg5);
                v6_1.a(2, arg7);
                v6_1.a(3, v0_1);
                v6_1.b();
                v0_1.reuse();
                goto label_46;
            }
            catch(Throwable v5) {
                v0 = v6_1;
            }
            catch(Exception v5_1) {
                v0 = v6_1;
                try {
                label_52:
                    FileLog.e(((Throwable)v5_1));
                    if(v0 == null) {
                        return;
                    }
                }
                catch(Throwable v5) {
                    goto label_56;
                }

                v0.e();
                return;
            }
        }

    label_56:
        if(v0 != null) {
            v0.e();
        }

        throw v5;
    label_45:
        v6_1 = v0;
    label_46:
        if(v6_1 != null) {
            v6_1.e();
        }
    }

    public static void lambda$putUsersAndChats$94(MessagesStorage arg0, ArrayList arg1, ArrayList arg2, boolean arg3) {
        arg0.putUsersAndChatsInternal(arg1, arg2, arg3);
    }

    public static void lambda$putWallpapers$21(MessagesStorage arg5, ArrayList arg6) {
        int v0 = 0;
        try {
            arg5.database.a("DELETE FROM wallpapers WHERE 1").c().e();
            arg5.database.d();
            SQLitePreparedStatement v1 = arg5.database.a("REPLACE INTO wallpapers VALUES(?, ?)");
            Iterator v6_1 = arg6.iterator();
            while(v6_1.hasNext()) {
                Object v2 = v6_1.next();
                v1.d();
                NativeByteBuffer v3 = new NativeByteBuffer(((WallPaper)v2).getObjectSize());
                ((WallPaper)v2).serializeToStream(((AbstractSerializedData)v3));
                v1.a(1, v0);
                v1.a(2, v3);
                v1.b();
                ++v0;
                v3.reuse();
            }

            v1.e();
            arg5.database.e();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$putWebPages$100(MessagesStorage arg10, LongSparseArray arg11) {
        try {
            ArrayList v0 = new ArrayList();
            int v1 = 0;
            int v2;
            for(v2 = 0; v2 < arg11.size(); ++v2) {
                SQLiteDatabase v3 = arg10.database;
                StringBuilder v5 = new StringBuilder();
                v5.append("SELECT mid FROM webpage_pending WHERE id = ");
                v5.append(arg11.keyAt(v2));
                SQLiteCursor v3_1 = v3.b(v5.toString(), new Object[0]);
                ArrayList v5_1 = new ArrayList();
                while(v3_1.a()) {
                    v5_1.add(Long.valueOf(v3_1.d(0)));
                }

                v3_1.b();
                if(v5_1.isEmpty()) {
                }
                else {
                    v3_1 = arg10.database.b(String.format(Locale.US, "SELECT mid, data FROM messages WHERE mid IN (%s)", TextUtils.join(",", ((Iterable)v5_1))), new Object[0]);
                    while(v3_1.a()) {
                        int v5_2 = v3_1.b(0);
                        NativeByteBuffer v6 = v3_1.g(1);
                        if(v6 == null) {
                            continue;
                        }

                        Message v7 = Message.TLdeserialize(((AbstractSerializedData)v6), v6.readInt32(false), false);
                        v7.readAttachPath(((AbstractSerializedData)v6), UserConfig.getInstance(arg10.currentAccount).clientUserId);
                        v6.reuse();
                        if(!(v7.media instanceof TL_messageMediaWebPage)) {
                            continue;
                        }

                        v7.id = v5_2;
                        v7.media.webpage = arg11.valueAt(v2);
                        v0.add(v7);
                    }

                    v3_1.b();
                }
            }

            if(v0.isEmpty()) {
                return;
            }

            arg10.database.d();
            SQLitePreparedStatement v11_1 = arg10.database.a("UPDATE messages SET data = ? WHERE mid = ?");
            SQLitePreparedStatement v2_1 = arg10.database.a("UPDATE media_v2 SET data = ? WHERE mid = ?");
            while(v1 < v0.size()) {
                Object v3_2 = v0.get(v1);
                NativeByteBuffer v5_3 = new NativeByteBuffer(((Message)v3_2).getObjectSize());
                ((Message)v3_2).serializeToStream(((AbstractSerializedData)v5_3));
                long v6_1 = ((long)((Message)v3_2).id);
                if(((Message)v3_2).to_id.channel_id != 0) {
                    v6_1 |= (((long)((Message)v3_2).to_id.channel_id)) << 32;
                }

                v11_1.d();
                v11_1.a(1, v5_3);
                v11_1.a(2, v6_1);
                v11_1.b();
                v2_1.d();
                v2_1.a(1, v5_3);
                v2_1.a(2, v6_1);
                v2_1.b();
                v5_3.reuse();
                ++v1;
            }

            v11_1.e();
            v2_1.e();
            arg10.database.e();
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$MI5VM5i1WtvrtzhgcxKMLuevC0g(arg10, v0));
        }
        catch(Exception v11) {
            FileLog.e(((Throwable)v11));
        }
    }

    public static void lambda$putWebRecent$26(MessagesStorage arg6, ArrayList arg7) {
        int v3;
        try {
            arg6.database.d();
            SQLitePreparedStatement v0 = arg6.database.a("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int v1 = 0;
            while(true) {
                v3 = 200;
                if(v1 < arg7.size()) {
                    if(v1 == v3) {
                    }
                    else {
                        Object v2 = arg7.get(v1);
                        v0.d();
                        v0.a(1, ((SearchImage)v2).id);
                        v0.a(2, ((SearchImage)v2).type);
                        v3 = 3;
                        String v4 = ((SearchImage)v2).imageUrl != null ? ((SearchImage)v2).imageUrl : "";
                        v0.a(v3, v4);
                        v3 = 4;
                        v4 = ((SearchImage)v2).thumbUrl != null ? ((SearchImage)v2).thumbUrl : "";
                        v0.a(v3, v4);
                        v3 = 5;
                        v4 = ((SearchImage)v2).localUrl != null ? ((SearchImage)v2).localUrl : "";
                        v0.a(v3, v4);
                        v0.a(6, ((SearchImage)v2).width);
                        v0.a(7, ((SearchImage)v2).height);
                        v0.a(8, ((SearchImage)v2).size);
                        v0.a(9, ((SearchImage)v2).date);
                        NativeByteBuffer v3_1 = null;
                        int v5 = 10;
                        if(((SearchImage)v2).photo != null) {
                            v3_1 = new NativeByteBuffer(((SearchImage)v2).photo.getObjectSize());
                            ((SearchImage)v2).photo.serializeToStream(((AbstractSerializedData)v3_1));
                            goto label_62;
                        }
                        else if(((SearchImage)v2).document != null) {
                            v3_1 = new NativeByteBuffer(((SearchImage)v2).document.getObjectSize());
                            ((SearchImage)v2).document.serializeToStream(((AbstractSerializedData)v3_1));
                        label_62:
                            v0.a(v5, v3_1);
                        }
                        else {
                            v0.a(v5);
                        }

                        v0.b();
                        if(v3_1 != null) {
                            v3_1.reuse();
                        }

                        ++v1;
                        continue;
                    }
                }

                break;
            }

            v0.e();
            arg6.database.e();
            if(arg7.size() < v3) {
                return;
            }

            arg6.database.d();
            while(v3 < arg7.size()) {
                SQLiteDatabase v0_1 = arg6.database;
                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("DELETE FROM web_recent_v3 WHERE id = \'");
                v1_1.append(arg7.get(v3).id);
                v1_1.append("\'");
                v0_1.a(v1_1.toString()).c().e();
                ++v3;
            }

            arg6.database.e();
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
        }
    }

    public static void lambda$removeFromDownloadQueue$95(MessagesStorage arg7, boolean arg8, int arg9, long arg10) {
        int v0 = 2;
        if(arg8) {
            try {
                SQLiteCursor v8_1 = arg7.database.b(String.format(Locale.US, "SELECT min(date) FROM download_queue WHERE type = %d", Integer.valueOf(arg9)), new Object[0]);
                int v4 = -1;
                int v3 = v8_1.a() ? v8_1.b(0) : -1;
                v8_1.b();
                if(v3 == v4) {
                    return;
                }

                SQLiteDatabase v8_2 = arg7.database;
                Locale v4_1 = Locale.US;
                Object[] v6 = new Object[3];
                v6[0] = Integer.valueOf(v3 - 1);
                v6[1] = Long.valueOf(arg10);
                v6[v0] = Integer.valueOf(arg9);
                SQLitePreparedStatement v8_3 = v8_2.a(String.format(v4_1, "UPDATE download_queue SET date = %d WHERE uid = %d AND type = %d", v6)).c();
                goto label_36;
            label_40:
                v8_2 = arg7.database;
                Locale v3_1 = Locale.US;
                v8_3 = v8_2.a(String.format(v3_1, "DELETE FROM download_queue WHERE uid = %d AND type = %d", Long.valueOf(arg10), Integer.valueOf(arg9))).c();
            label_36:
                v8_3.e();
                return;
            label_39:
                goto label_52;
            }
            catch(Exception v8) {
                goto label_39;
            }
        }
        else {
            goto label_40;
        }

        goto label_36;
    label_52:
        FileLog.e(((Throwable)v8));
    }

    public static void lambda$removePendingTask$7(MessagesStorage arg3, long arg4) {
        try {
            SQLiteDatabase v0 = arg3.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("DELETE FROM pending_tasks WHERE id = ");
            v1.append(arg4);
            v0.a(v1.toString()).c().e();
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }

    public static void lambda$resetDialogs$42(MessagesStorage arg16, messages_Dialogs arg17, int arg18, int arg19, int arg20, int arg21, int arg22, Message arg23, int arg24, LongSparseArray arg25, LongSparseArray arg26) {
        int v2;
        long v3_4;
        UserConfig v2_1;
        Object v3_3;
        Object v12_3;
        int v14;
        MessagesStorage v1 = arg16;
        messages_Dialogs v0 = arg17;
        Message v11 = arg23;
        try {
            ArrayList v3 = new ArrayList();
            int v4 = v0.dialogs.size() - arg18;
            LongSparseArray v5 = new LongSparseArray();
            ArrayList v6 = new ArrayList();
            ArrayList v7 = new ArrayList();
            int v8;
            for(v8 = arg18; v8 < v0.dialogs.size(); ++v8) {
                v7.add(Long.valueOf(v0.dialogs.get(v8).id));
            }

            SQLiteCursor v8_1 = v1.database.b("SELECT did, pinned FROM dialogs WHERE 1", new Object[0]);
            int v9 = 0;
            while(v8_1.a()) {
                long v12 = v8_1.d(0);
                v14 = v8_1.b(1);
                int v15 = ((int)v12);
                if(v15 == 0) {
                    continue;
                }

                v3.add(Integer.valueOf(v15));
                if(v14 <= 0) {
                    continue;
                }

                v9 = Math.max(v14, v9);
                v5.put(v12, Integer.valueOf(v14));
                v6.add(Long.valueOf(v12));
            }

            Collections.sort(((List)v6), new -$$Lambda$MessagesStorage$lhgV8k1XYLPVcl4zmcSTA2rNGF0(v5));
            while(v6.size() < v4) {
                v6.add(0, Long.valueOf(0));
            }

            v8_1.b();
            String v3_1 = "(" + TextUtils.join(",", ((Iterable)v3)) + ")";
            v1.database.d();
            SQLiteDatabase v8_3 = v1.database;
            StringBuilder v12_1 = new StringBuilder();
            v12_1.append("DELETE FROM dialogs WHERE did IN ");
            v12_1.append(v3_1);
            v8_3.a(v12_1.toString()).c().e();
            v8_3 = v1.database;
            v12_1 = new StringBuilder();
            v12_1.append("DELETE FROM messages WHERE uid IN ");
            v12_1.append(v3_1);
            v8_3.a(v12_1.toString()).c().e();
            v8_3 = v1.database;
            v12_1 = new StringBuilder();
            v12_1.append("DELETE FROM bot_keyboard WHERE uid IN ");
            v12_1.append(v3_1);
            v8_3.a(v12_1.toString()).c().e();
            v8_3 = v1.database;
            v12_1 = new StringBuilder();
            v12_1.append("DELETE FROM media_counts_v2 WHERE uid IN ");
            v12_1.append(v3_1);
            v8_3.a(v12_1.toString()).c().e();
            v8_3 = v1.database;
            v12_1 = new StringBuilder();
            v12_1.append("DELETE FROM media_v2 WHERE uid IN ");
            v12_1.append(v3_1);
            v8_3.a(v12_1.toString()).c().e();
            v8_3 = v1.database;
            v12_1 = new StringBuilder();
            v12_1.append("DELETE FROM messages_holes WHERE uid IN ");
            v12_1.append(v3_1);
            v8_3.a(v12_1.toString()).c().e();
            v8_3 = v1.database;
            v12_1 = new StringBuilder();
            v12_1.append("DELETE FROM media_holes_v2 WHERE uid IN ");
            v12_1.append(v3_1);
            v8_3.a(v12_1.toString()).c().e();
            v1.database.e();
            int v3_2;
            for(v3_2 = 0; v3_2 < v4; ++v3_2) {
                Object v8_4 = v0.dialogs.get(arg18 + v3_2);
                int v12_2 = v6.indexOf(Long.valueOf(((TL_dialog)v8_4).id));
                int v13 = v7.indexOf(Long.valueOf(((TL_dialog)v8_4).id));
                v14 = -1;
                if(v12_2 != v14 && v13 != v14) {
                    if(v12_2 == v13) {
                        v12_3 = v5.get(((TL_dialog)v8_4).id);
                        if(v12_3 != null) {
                            v12_2 = ((Integer)v12_3).intValue();
                        }
                        else {
                            goto label_168;
                        }
                    }
                    else {
                        v12_3 = v5.get(v6.get(v13).longValue());
                        if(v12_3 != null) {
                            v12_2 = ((Integer)v12_3).intValue();
                        }
                        else {
                            goto label_168;
                        }
                    }

                    ((TL_dialog)v8_4).pinnedNum = v12_2;
                }

            label_168:
                if(((TL_dialog)v8_4).pinnedNum == 0) {
                    ((TL_dialog)v8_4).pinnedNum = v4 - v3_2 + v9;
                }
            }

            v1.putDialogsInternal(v0, 0);
            v1.saveDiffParamsInternal(arg19, arg20, arg21, arg22);
            if(v11 == null || v11.id == UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetId) {
                UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetId = 2147483647;
                goto label_298;
                while(true) {
                label_278:
                    if(v2 < v0.users.size()) {
                        v3_3 = v0.users.get(v2);
                        if(((User)v3_3).id == UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetUserId) {
                            v2_1 = UserConfig.getInstance(v1.currentAccount);
                            v3_4 = ((User)v3_3).access_hash;
                        }
                        else {
                            ++v2;
                            continue;
                        }
                    }
                    else {
                        goto label_298;
                    }

                    goto label_228;
                }

                while(true) {
                label_247:
                    if(v2 < v0.chats.size()) {
                        v3_3 = v0.chats.get(v2);
                        if(((Chat)v3_3).id == UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChatId) {
                            v2_1 = UserConfig.getInstance(v1.currentAccount);
                            v3_4 = ((Chat)v3_3).access_hash;
                        }
                        else {
                            ++v2;
                            continue;
                        }
                    }
                    else {
                        goto label_298;
                    }

                    goto label_228;
                }

                while(true) {
                label_215:
                    if(v2 < v0.chats.size()) {
                        v3_3 = v0.chats.get(v2);
                        if(((Chat)v3_3).id == UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChannelId) {
                            v2_1 = UserConfig.getInstance(v1.currentAccount);
                            v3_4 = ((Chat)v3_3).access_hash;
                        }
                        else {
                            ++v2;
                            continue;
                        }
                    }
                    else {
                        goto label_298;
                    }

                    break;
                }

            label_228:
                v2_1.dialogsLoadOffsetAccess = v3_4;
            }
            else {
                UserConfig.getInstance(v1.currentAccount).totalDialogsLoadCount = v0.dialogs.size();
                UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetId = v11.id;
                UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetDate = v11.date;
                if(v11.to_id.channel_id != 0) {
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChannelId = v11.to_id.channel_id;
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChatId = 0;
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetUserId = 0;
                    v2 = 0;
                    goto label_215;
                }
                else if(v11.to_id.chat_id != 0) {
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChatId = v11.to_id.chat_id;
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChannelId = 0;
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetUserId = 0;
                    v2 = 0;
                    goto label_247;
                }
                else if(v11.to_id.user_id != 0) {
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetUserId = v11.to_id.user_id;
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChatId = 0;
                    UserConfig.getInstance(v1.currentAccount).dialogsLoadOffsetChannelId = 0;
                    v2 = 0;
                    goto label_278;
                }
                else {
                }
            }

        label_298:
            UserConfig.getInstance(v1.currentAccount).saveConfig(false);
            MessagesController.getInstance(v1.currentAccount).completeDialogsReset(arg17, arg24, arg19, arg20, arg21, arg22, arg25, arg26, arg23);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public static void lambda$resetMentionsCount$49(MessagesStorage arg7, int arg8, long arg9) {
        if(arg8 == 0) {
            try {
                arg7.database.a(String.format(Locale.US, "UPDATE messages SET read_state = read_state | 2 WHERE uid = %d AND mention = 1 AND read_state IN(0, 1)", Long.valueOf(arg9))).c().e();
            label_16:
                arg7.database.a(String.format(Locale.US, "UPDATE dialogs SET unread_count_i = %d WHERE did = %d", Integer.valueOf(arg8), Long.valueOf(arg9))).c().e();
                LongSparseArray v0 = new LongSparseArray(1);
                v0.put(arg9, Integer.valueOf(arg8));
                MessagesController.getInstance(arg7.currentAccount).processDialogsUpdateRead(null, v0);
                return;
            label_15:
                goto label_38;
            }
            catch(Exception v8) {
                goto label_15;
            }
        }

        goto label_16;
    label_38:
        FileLog.e(((Throwable)v8));
    }

    public static void lambda$saveBotCache$60(MessagesStorage arg4, TLObject arg5, String arg6) {
        int v1;
        try {
            int v0 = ConnectionsManager.getInstance(arg4.currentAccount).getCurrentTime();
            if((arg5 instanceof TL_messages_botCallbackAnswer)) {
                v1 = arg5.cache_time;
                goto label_7;
            }
            else if((arg5 instanceof TL_messages_botResults)) {
                v1 = arg5.cache_time;
            label_7:
                v0 += v1;
            }

            SQLitePreparedStatement v1_1 = arg4.database.a("REPLACE INTO botcache VALUES(?, ?, ?)");
            NativeByteBuffer v2 = new NativeByteBuffer(arg5.getObjectSize());
            arg5.serializeToStream(((AbstractSerializedData)v2));
            v1_1.a(1, arg6);
            v1_1.a(2, v0);
            v1_1.a(3, v2);
            v1_1.b();
            v1_1.e();
            v2.reuse();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }
    }

    public static void lambda$saveChannelPts$16(MessagesStorage arg2, int arg3, int arg4) {
        try {
            SQLitePreparedStatement v0 = arg2.database.a("UPDATE dialogs SET pts = ? WHERE did = ?");
            v0.a(1, arg3);
            v0.a(2, -arg4);
            v0.b();
            v0.e();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void lambda$saveDiffParams$17(MessagesStorage arg0, int arg1, int arg2, int arg3, int arg4) {
        arg0.saveDiffParamsInternal(arg1, arg2, arg3, arg4);
    }

    public static void lambda$saveSecretParams$4(MessagesStorage arg2, int arg3, int arg4, byte[] arg5) {
        try {
            SQLitePreparedStatement v0 = arg2.database.a("UPDATE params SET lsv = ?, sg = ?, pbytes = ? WHERE id = 1");
            int v1 = 1;
            v0.a(1, arg3);
            v0.a(2, arg4);
            if(arg5 != null) {
                v1 = arg5.length;
            }

            NativeByteBuffer v3_1 = new NativeByteBuffer(v1);
            if(arg5 != null) {
                v3_1.writeBytes(arg5);
            }

            v0.a(3, v3_1);
            v0.b();
            v0.e();
            v3_1.reuse();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void lambda$setDialogFlags$18(MessagesStorage arg5, long arg6, long arg8) {
        try {
            arg5.database.a(String.format(Locale.US, "REPLACE INTO dialog_settings VALUES(%d, %d)", Long.valueOf(arg6), Long.valueOf(arg8))).c().e();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$setDialogPinned$121(MessagesStorage arg2, int arg3, long arg4) {
        try {
            SQLitePreparedStatement v0 = arg2.database.a("UPDATE dialogs SET pinned = ? WHERE did = ?");
            v0.a(1, arg3);
            v0.a(2, arg4);
            v0.b();
            v0.e();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void lambda$setDialogUnread$120(MessagesStorage arg6, long arg7, boolean arg9) {
        SQLiteCursor v2_2;
        SQLiteCursor v0 = null;
        int v1 = 0;
        try {
            SQLiteDatabase v2_1 = arg6.database;
            StringBuilder v3 = new StringBuilder();
            v3.append("SELECT flags FROM dialogs WHERE did = ");
            v3.append(arg7);
            v2_2 = v2_1.b(v3.toString(), new Object[0]);
            goto label_11;
        }
        catch(Throwable v7) {
        }
        catch(Exception v2) {
            goto label_29;
            try {
            label_11:
                if(v2_2.a()) {
                    v1 = v2_2.b(0);
                }

                goto label_15;
            }
            catch(Throwable v7) {
                v0 = v2_2;
            }
            catch(Exception v0_1) {
                SQLiteCursor v5 = v2_2;
                v2 = v0_1;
                v0 = v5;
                try {
                label_29:
                    FileLog.e(((Throwable)v2));
                    if(v0 == null) {
                        goto label_32;
                    }
                }
                catch(Throwable v7) {
                    goto label_46;
                }

                try {
                    v0.b();
                    goto label_32;
                }
                catch(Exception v7_1) {
                    goto label_50;
                }
            }
        }

        try {
        label_46:
            if(v0 != null) {
                v0.b();
            }

            throw v7;
        }
        catch(Exception v7_1) {
            goto label_50;
        }

    label_15:
        if(v2_2 == null) {
            goto label_32;
        }

        try {
            v2_2.b();
        label_32:
            int v9 = arg9 ? v1 | 1 : v1 & -2;
            SQLitePreparedStatement v1_1 = arg6.database.a("UPDATE dialogs SET flags = ? WHERE did = ?");
            v1_1.a(1, v9);
            v1_1.a(2, arg7);
            v1_1.b();
            v1_1.e();
            return;
        label_50:
        }
        catch(Exception v7_1) {
            goto label_50;
        }

        FileLog.e(((Throwable)v7_1));
    }

    public static void lambda$setMessageSeq$107(MessagesStorage arg2, int arg3, int arg4, int arg5) {
        try {
            SQLitePreparedStatement v0 = arg2.database.a("REPLACE INTO messages_seq VALUES(?, ?, ?)");
            v0.d();
            v0.a(1, arg3);
            v0.a(2, arg4);
            v0.a(3, arg5);
            v0.b();
            v0.e();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void lambda$unpinAllDialogsExceptNew$119(MessagesStorage arg7, ArrayList arg8) {
        try {
            ArrayList v0 = new ArrayList();
            SQLiteCursor v8_1 = arg7.database.b(String.format(Locale.US, "SELECT did FROM dialogs WHERE pinned != 0 AND did NOT IN (%s)", TextUtils.join(",", ((Iterable)arg8))), new Object[0]);
            while(v8_1.a()) {
                if((((int)v8_1.d(0))) == 0) {
                    continue;
                }

                v0.add(Long.valueOf(v8_1.d(0)));
            }

            v8_1.b();
            if(v0.isEmpty()) {
                return;
            }

            SQLitePreparedStatement v8_2 = arg7.database.a("UPDATE dialogs SET pinned = ? WHERE did = ?");
            int v1;
            for(v1 = 0; v1 < v0.size(); ++v1) {
                long v2 = v0.get(v1).longValue();
                v8_2.d();
                v8_2.a(1, 0);
                v8_2.a(2, v2);
                v8_2.b();
            }

            v8_2.e();
        }
        catch(Exception v8) {
            FileLog.e(((Throwable)v8));
        }
    }

    public static void lambda$updateChannelPinnedMessage$64(MessagesStorage arg5, int arg6, int arg7) {
        try {
            SQLiteDatabase v0 = arg5.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("SELECT info, pinned FROM chat_settings_v2 WHERE uid = ");
            v1.append(arg6);
            SQLiteCursor v0_1 = v0.b(v1.toString(), new Object[0]);
            ChatFull v1_1 = null;
            new ArrayList();
            if(v0_1.a()) {
                NativeByteBuffer v3 = v0_1.g(0);
                if(v3 != null) {
                    v1_1 = ChatFull.TLdeserialize(((AbstractSerializedData)v3), v3.readInt32(false), false);
                    v3.reuse();
                    v1_1.pinned_msg_id = v0_1.b(1);
                }
            }

            v0_1.b();
            if(!(v1_1 instanceof TL_channelFull)) {
                return;
            }

            v1_1.pinned_msg_id = arg7;
            v1_1.flags |= 32;
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$RSCfHUqNo1GyDNuwKXrOvxwWC2A(arg5, v1_1));
            SQLitePreparedStatement v7 = arg5.database.a("REPLACE INTO chat_settings_v2 VALUES(?, ?, ?)");
            NativeByteBuffer v0_2 = new NativeByteBuffer(v1_1.getObjectSize());
            v1_1.serializeToStream(((AbstractSerializedData)v0_2));
            v7.a(1, arg6);
            v7.a(2, v0_2);
            v7.a(3, v1_1.pinned_msg_id);
            v7.b();
            v7.e();
            v0_2.reuse();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$updateChannelUsers$59(MessagesStorage arg9, int arg10, ArrayList arg11) {
        long v0 = ((long)(-arg10));
        try {
            SQLiteDatabase v2 = arg9.database;
            StringBuilder v3 = new StringBuilder();
            v3.append("DELETE FROM channel_users_v2 WHERE did = ");
            v3.append(v0);
            v2.a(v3.toString()).c().e();
            arg9.database.d();
            SQLitePreparedStatement v2_1 = arg9.database.a("REPLACE INTO channel_users_v2 VALUES(?, ?, ?, ?)");
            int v5 = ((int)(System.currentTimeMillis() / 1000));
            int v3_1;
            for(v3_1 = 0; v3_1 < arg11.size(); ++v3_1) {
                Object v6 = arg11.get(v3_1);
                v2_1.d();
                v2_1.a(1, v0);
                v2_1.a(2, ((ChannelParticipant)v6).user_id);
                v2_1.a(3, v5);
                NativeByteBuffer v7 = new NativeByteBuffer(((ChannelParticipant)v6).getObjectSize());
                ((ChannelParticipant)v6).serializeToStream(((AbstractSerializedData)v7));
                v2_1.a(4, v7);
                v7.reuse();
                v2_1.b();
                --v5;
            }

            v2_1.e();
            arg9.database.e();
            arg9.loadChatInfo(arg10, null, false, true);
        }
        catch(Exception v10) {
            FileLog.e(((Throwable)v10));
        }
    }

    public static void lambda$updateChatInfo$62(MessagesStorage arg18, boolean arg19, ChatFull arg20) {
        MessagesStorage v1 = arg18;
        ChatFull v0 = arg20;
        if(arg19) {
            try {
                SQLiteDatabase v3 = v1.database;
                StringBuilder v4 = new StringBuilder();
                v4.append("SELECT uid FROM chat_settings_v2 WHERE uid = ");
                v4.append(v0.id);
                SQLiteCursor v3_1 = v3.b(v4.toString(), new Object[0]);
                boolean v4_1 = v3_1.a();
                v3_1.b();
                if(!v4_1) {
                    return;
                }

            label_20:
                SQLitePreparedStatement v3_2 = v1.database.a("REPLACE INTO chat_settings_v2 VALUES(?, ?, ?)");
                NativeByteBuffer v4_2 = new NativeByteBuffer(arg20.getObjectSize());
                v0.serializeToStream(((AbstractSerializedData)v4_2));
                v3_2.a(1, v0.id);
                int v5 = 2;
                v3_2.a(v5, v4_2);
                int v8 = 3;
                v3_2.a(v8, v0.pinned_msg_id);
                v3_2.b();
                v3_2.e();
                v4_2.reuse();
                if(!(v0 instanceof TL_channelFull)) {
                    return;
                }

                v3 = v1.database;
                v4 = new StringBuilder();
                v4.append("SELECT date, pts, last_mid, inbox_max, outbox_max, pinned, unread_count_i, flags FROM dialogs WHERE did = ");
                v4.append(-v0.id);
                v3_1 = v3.b(v4.toString(), new Object[0]);
                if((v3_1.a()) && v3_1.b(v8) < v0.read_inbox_max_id) {
                    int v4_3 = v3_1.b(0);
                    int v7 = v3_1.b(1);
                    long v9 = v3_1.d(v5);
                    int v12 = v3_1.b(4);
                    int v14 = v3_1.b(5);
                    int v2 = v3_1.b(6);
                    int v13 = v3_1.b(7);
                    SQLitePreparedStatement v11 = v1.database.a("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    v11.a(1, ((long)(-v0.id)));
                    v11.a(v5, v4_3);
                    v11.a(3, v0.unread_count);
                    v11.a(4, v9);
                    v11.a(5, v0.read_inbox_max_id);
                    v11.a(6, Math.max(v12, v0.read_outbox_max_id));
                    v11.a(7, 0);
                    v11.a(8, v2);
                    v11.a(9, v7);
                    v11.a(10, 0);
                    v11.a(11, v14);
                    v11.a(12, v13);
                    v11.b();
                    v11.e();
                }

                v3_1.b();
                return;
            label_19:
                goto label_107;
            }
            catch(Exception v0_1) {
                goto label_19;
            }
        }

        goto label_20;
    label_107:
        FileLog.e(((Throwable)v0_1));
    }

    public static void lambda$updateChatInfo$66(MessagesStorage arg5, int arg6, int arg7, int arg8, int arg9, int arg10) {
        TL_chatParticipant v8_1;
        try {
            SQLiteDatabase v0 = arg5.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("SELECT info, pinned FROM chat_settings_v2 WHERE uid = ");
            v1.append(arg6);
            int v2 = 0;
            SQLiteCursor v0_1 = v0.b(v1.toString(), new Object[0]);
            ChatFull v1_1 = null;
            new ArrayList();
            if(v0_1.a()) {
                NativeByteBuffer v3 = v0_1.g(0);
                if(v3 != null) {
                    v1_1 = ChatFull.TLdeserialize(((AbstractSerializedData)v3), v3.readInt32(false), false);
                    v3.reuse();
                    v1_1.pinned_msg_id = v0_1.b(1);
                }
            }

            v0_1.b();
            if(!(v1_1 instanceof TL_chatFull)) {
                return;
            }

            int v0_2 = 2;
            if(arg7 == 1) {
                while(v2 < v1_1.participants.participants.size()) {
                    if(v1_1.participants.participants.get(v2).user_id == arg8) {
                        v1_1.participants.participants.remove(v2);
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    break;
                }
            }
            else if(arg7 == 0) {
                Iterator v7 = v1_1.participants.participants.iterator();
                do {
                    if(v7.hasNext()) {
                        if(v7.next().user_id != arg8) {
                            continue;
                        }

                        return;
                    }
                    else {
                        goto label_53;
                    }
                }
                while(true);

                return;
            label_53:
                TL_chatParticipant v7_1 = new TL_chatParticipant();
                v7_1.user_id = arg8;
                v7_1.inviter_id = arg9;
                v7_1.date = ConnectionsManager.getInstance(arg5.currentAccount).getCurrentTime();
                v1_1.participants.participants.add(v7_1);
            }
            else {
                if(arg7 != v0_2) {
                    goto label_99;
                }

                while(v2 < v1_1.participants.participants.size()) {
                    Object v7_2 = v1_1.participants.participants.get(v2);
                    if(((ChatParticipant)v7_2).user_id == arg8) {
                        if(arg9 == 1) {
                            TL_chatParticipantAdmin v8 = new TL_chatParticipantAdmin();
                            ((ChatParticipant)v8).user_id = ((ChatParticipant)v7_2).user_id;
                            ((ChatParticipant)v8).date = ((ChatParticipant)v7_2).date;
                            arg7 = ((ChatParticipant)v7_2).inviter_id;
                        }
                        else {
                            v8_1 = new TL_chatParticipant();
                            ((ChatParticipant)v8_1).user_id = ((ChatParticipant)v7_2).user_id;
                            ((ChatParticipant)v8_1).date = ((ChatParticipant)v7_2).date;
                            arg7 = ((ChatParticipant)v7_2).inviter_id;
                        }

                        ((ChatParticipant)v8_1).inviter_id = arg7;
                        v1_1.participants.participants.set(v2, v8_1);
                        break;
                    }

                    ++v2;
                }
            }

        label_99:
            v1_1.participants.version = arg10;
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$AB2bBnEAj-fyyqEVm2jJWPzNsJM(arg5, v1_1));
            SQLitePreparedStatement v7_3 = arg5.database.a("REPLACE INTO chat_settings_v2 VALUES(?, ?, ?)");
            NativeByteBuffer v8_2 = new NativeByteBuffer(v1_1.getObjectSize());
            v1_1.serializeToStream(((AbstractSerializedData)v8_2));
            v7_3.a(1, arg6);
            v7_3.a(v0_2, v8_2);
            v7_3.a(3, v1_1.pinned_msg_id);
            v7_3.b();
            v7_3.e();
            v8_2.reuse();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$updateChatParticipants$56(MessagesStorage arg5, ChatParticipants arg6) {
        try {
            SQLiteDatabase v0 = arg5.database;
            StringBuilder v1 = new StringBuilder();
            v1.append("SELECT info, pinned FROM chat_settings_v2 WHERE uid = ");
            v1.append(arg6.chat_id);
            SQLiteCursor v0_1 = v0.b(v1.toString(), new Object[0]);
            ChatFull v1_1 = null;
            new ArrayList();
            if(v0_1.a()) {
                NativeByteBuffer v3 = v0_1.g(0);
                if(v3 != null) {
                    v1_1 = ChatFull.TLdeserialize(((AbstractSerializedData)v3), v3.readInt32(false), false);
                    v3.reuse();
                    v1_1.pinned_msg_id = v0_1.b(1);
                }
            }

            v0_1.b();
            if(!(v1_1 instanceof TL_chatFull)) {
                return;
            }

            v1_1.participants = arg6;
            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$GWNVLU8GpNoRjKRQcP8kjjGDTsQ(arg5, v1_1));
            SQLitePreparedStatement v6_1 = arg5.database.a("REPLACE INTO chat_settings_v2 VALUES(?, ?, ?)");
            NativeByteBuffer v0_2 = new NativeByteBuffer(v1_1.getObjectSize());
            v1_1.serializeToStream(((AbstractSerializedData)v0_2));
            v6_1.a(1, v1_1.id);
            v6_1.a(2, v0_2);
            v6_1.a(3, v1_1.pinned_msg_id);
            v6_1.b();
            v6_1.e();
            v0_2.reuse();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$updateDbToLastVersion$1(MessagesStorage arg10, int arg11) {
        SQLiteCursor v11_1;
        int v0 = 4;
        if(arg11 < v0) {
            try {
                arg10.database.a("CREATE TABLE IF NOT EXISTS user_photos(uid INTEGER, id INTEGER, data BLOB, PRIMARY KEY (uid, id))").c().e();
                arg10.database.a("DROP INDEX IF EXISTS read_state_out_idx_messages;").c().e();
                arg10.database.a("DROP INDEX IF EXISTS ttl_idx_messages;").c().e();
                arg10.database.a("DROP INDEX IF EXISTS date_idx_messages;").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS mid_out_idx_messages ON messages(mid, out);").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS task_idx_messages ON messages(uid, out, read_state, ttl, date, send_state);").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS uid_date_mid_idx_messages ON messages(uid, date, mid);").c().e();
                arg10.database.a("CREATE TABLE IF NOT EXISTS user_contacts_v6(uid INTEGER PRIMARY KEY, fname TEXT, sname TEXT)").c().e();
                arg10.database.a("CREATE TABLE IF NOT EXISTS user_phones_v6(uid INTEGER, phone TEXT, sphone TEXT, deleted INTEGER, PRIMARY KEY (uid, phone))").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS sphone_deleted_idx_user_phones ON user_phones_v6(sphone, deleted);").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS mid_idx_randoms ON randoms(mid);").c().e();
                arg10.database.a("CREATE TABLE IF NOT EXISTS sent_files_v2(uid TEXT, type INTEGER, data BLOB, PRIMARY KEY (uid, type))").c().e();
                arg10.database.a("CREATE TABLE IF NOT EXISTS blocked_users(uid INTEGER PRIMARY KEY)").c().e();
                arg10.database.a("CREATE TABLE IF NOT EXISTS download_queue(uid INTEGER, type INTEGER, date INTEGER, data BLOB, PRIMARY KEY (uid, type));").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS type_date_idx_download_queue ON download_queue(type, date);").c().e();
                arg10.database.a("CREATE TABLE IF NOT EXISTS dialog_settings(did INTEGER PRIMARY KEY, flags INTEGER);").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS send_state_idx_messages ON messages(mid, send_state, date) WHERE mid < 0 AND send_state = 1;").c().e();
                arg10.database.a("CREATE INDEX IF NOT EXISTS unread_count_idx_dialogs ON dialogs(unread_count);").c().e();
                arg10.database.a("UPDATE messages SET send_state = 2 WHERE mid < 0 AND send_state = 1").c().e();
                arg10.fixNotificationSettings();
                arg10.database.a("PRAGMA user_version = 4").c().e();
                arg11 = 4;
            label_107:
                int v1 = 6;
                if(arg11 == v0) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS enc_tasks_v2(mid INTEGER PRIMARY KEY, date INTEGER)").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS date_idx_enc_tasks_v2 ON enc_tasks_v2(date);").c().e();
                    arg10.database.d();
                    v11_1 = arg10.database.b("SELECT date, data FROM enc_tasks WHERE 1", new Object[0]);
                    SQLitePreparedStatement v4 = arg10.database.a("REPLACE INTO enc_tasks_v2 VALUES(?, ?)");
                    if(v11_1.a()) {
                        int v5 = v11_1.b(0);
                        NativeByteBuffer v6 = v11_1.g(1);
                        if(v6 != null) {
                            int v7 = v6.limit();
                            int v8;
                            for(v8 = 0; v8 < v7 / 4; ++v8) {
                                v4.d();
                                v4.a(1, v6.readInt32(false));
                                v4.a(2, v5);
                                v4.b();
                            }

                            v6.reuse();
                        }
                    }

                    v4.e();
                    v11_1.b();
                    arg10.database.e();
                    arg10.database.a("DROP INDEX IF EXISTS date_idx_enc_tasks;").c().e();
                    arg10.database.a("DROP TABLE IF EXISTS enc_tasks;").c().e();
                    arg10.database.a("ALTER TABLE messages ADD COLUMN media INTEGER default 0").c().e();
                    arg10.database.a("PRAGMA user_version = 6").c().e();
                    arg11 = 6;
                }

                v0 = 7;
                if(arg11 == v1) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS messages_seq(mid INTEGER PRIMARY KEY, seq_in INTEGER, seq_out INTEGER);").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS seq_idx_messages_seq ON messages_seq(seq_in, seq_out);").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN layer INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN seq_in INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN seq_out INTEGER default 0").c().e();
                    arg10.database.a("PRAGMA user_version = 7").c().e();
                    arg11 = 7;
                }

                v1 = 10;
                if(arg11 == v0 || arg11 == 8 || arg11 == 9) {
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN use_count INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN exchange_id INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN key_date INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN fprint INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN fauthkey BLOB default NULL").c().e();
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN khash BLOB default NULL").c().e();
                    arg10.database.a("PRAGMA user_version = 10").c().e();
                    arg11 = 10;
                }

                v0 = 11;
                if(arg11 == v1) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS web_recent_v3(id TEXT, type INTEGER, image_url TEXT, thumb_url TEXT, local_url TEXT, width INTEGER, height INTEGER, size INTEGER, date INTEGER, PRIMARY KEY (id, type));").c().e();
                    arg10.database.a("PRAGMA user_version = 11").c().e();
                    arg11 = 11;
                }

                v1 = 13;
                if(arg11 == v0 || arg11 == 12) {
                    arg10.database.a("DROP INDEX IF EXISTS uid_mid_idx_media;").c().e();
                    arg10.database.a("DROP INDEX IF EXISTS mid_idx_media;").c().e();
                    arg10.database.a("DROP INDEX IF EXISTS uid_date_mid_idx_media;").c().e();
                    arg10.database.a("DROP TABLE IF EXISTS media;").c().e();
                    arg10.database.a("DROP TABLE IF EXISTS media_counts;").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS media_v2(mid INTEGER PRIMARY KEY, uid INTEGER, date INTEGER, type INTEGER, data BLOB)").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS media_counts_v2(uid INTEGER, type INTEGER, count INTEGER, PRIMARY KEY(uid, type))").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS uid_mid_type_date_idx_media ON media_v2(uid, mid, type, date);").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS keyvalue(id TEXT PRIMARY KEY, value TEXT)").c().e();
                    arg10.database.a("PRAGMA user_version = 13").c().e();
                    arg11 = 13;
                }

                v0 = 14;
                if(arg11 == v1) {
                    arg10.database.a("ALTER TABLE messages ADD COLUMN replydata BLOB default NULL").c().e();
                    arg10.database.a("PRAGMA user_version = 14").c().e();
                    arg11 = 14;
                }

                v1 = 15;
                if(arg11 == v0) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS hashtag_recent_v2(id TEXT PRIMARY KEY, date INTEGER);").c().e();
                    arg10.database.a("PRAGMA user_version = 15").c().e();
                    arg11 = 15;
                }

                v0 = 16;
                if(arg11 == v1) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS webpage_pending(id INTEGER, mid INTEGER, PRIMARY KEY (id, mid));").c().e();
                    arg10.database.a("PRAGMA user_version = 16").c().e();
                    arg11 = 16;
                }

                v1 = 17;
                if(arg11 == v0) {
                    arg10.database.a("ALTER TABLE dialogs ADD COLUMN inbox_max INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE dialogs ADD COLUMN outbox_max INTEGER default 0").c().e();
                    arg10.database.a("PRAGMA user_version = 17").c().e();
                    arg11 = 17;
                }

                v0 = 18;
                if(arg11 == v1) {
                    arg10.database.a("CREATE TABLE bot_info(uid INTEGER PRIMARY KEY, info BLOB)").c().e();
                    arg10.database.a("PRAGMA user_version = 18").c().e();
                    arg11 = 18;
                }

                v1 = 19;
                if(arg11 == v0) {
                    arg10.database.a("DROP TABLE IF EXISTS stickers;").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS stickers_v2(id INTEGER PRIMARY KEY, data BLOB, date INTEGER, hash TEXT);").c().e();
                    arg10.database.a("PRAGMA user_version = 19").c().e();
                    arg11 = 19;
                }

                v0 = 20;
                if(arg11 == v1) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS bot_keyboard(uid INTEGER PRIMARY KEY, mid INTEGER, info BLOB)").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS bot_keyboard_idx_mid ON bot_keyboard(mid);").c().e();
                    arg10.database.a("PRAGMA user_version = 20").c().e();
                    arg11 = 20;
                }

                if(arg11 == v0) {
                    arg10.database.a("CREATE TABLE search_recent(did INTEGER PRIMARY KEY, date INTEGER);").c().e();
                    arg10.database.a("PRAGMA user_version = 21").c().e();
                    arg11 = 21;
                }

                if(arg11 == 21) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS chat_settings_v2(uid INTEGER PRIMARY KEY, info BLOB)").c().e();
                    v11_1 = arg10.database.b("SELECT uid, participants FROM chat_settings WHERE uid < 0", new Object[0]);
                    SQLitePreparedStatement v0_1 = arg10.database.a("REPLACE INTO chat_settings_v2 VALUES(?, ?)");
                    while(v11_1.a()) {
                        v1 = v11_1.b(0);
                        NativeByteBuffer v4_1 = v11_1.g(1);
                        if(v4_1 == null) {
                            continue;
                        }

                        ChatParticipants v5_1 = ChatParticipants.TLdeserialize(((AbstractSerializedData)v4_1), v4_1.readInt32(false), false);
                        v4_1.reuse();
                        if(v5_1 == null) {
                            continue;
                        }

                        TL_chatFull v4_2 = new TL_chatFull();
                        v4_2.id = v1;
                        v4_2.chat_photo = new TL_photoEmpty();
                        v4_2.notify_settings = new TL_peerNotifySettingsEmpty_layer77();
                        v4_2.exported_invite = new TL_chatInviteEmpty();
                        v4_2.participants = v5_1;
                        NativeByteBuffer v5_2 = new NativeByteBuffer(v4_2.getObjectSize());
                        v4_2.serializeToStream(((AbstractSerializedData)v5_2));
                        v0_1.d();
                        v0_1.a(1, v1);
                        v0_1.a(2, v5_2);
                        v0_1.b();
                        v5_2.reuse();
                    }

                    v0_1.e();
                    v11_1.b();
                    arg10.database.a("DROP TABLE IF EXISTS chat_settings;").c().e();
                    arg10.database.a("ALTER TABLE dialogs ADD COLUMN last_mid_i INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE dialogs ADD COLUMN unread_count_i INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE dialogs ADD COLUMN pts INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE dialogs ADD COLUMN date_i INTEGER default 0").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS last_mid_i_idx_dialogs ON dialogs(last_mid_i);").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS unread_count_i_idx_dialogs ON dialogs(unread_count_i);").c().e();
                    arg10.database.a("ALTER TABLE messages ADD COLUMN imp INTEGER default 0").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS messages_holes(uid INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, start));").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS uid_end_messages_holes ON messages_holes(uid, end);").c().e();
                    arg10.database.a("PRAGMA user_version = 22").c().e();
                    arg11 = 22;
                }

                if(arg11 == 22) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS media_holes_v2(uid INTEGER, type INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, type, start));").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS uid_end_media_holes_v2 ON media_holes_v2(uid, type, end);").c().e();
                    arg10.database.a("PRAGMA user_version = 23").c().e();
                    arg11 = 23;
                }

                if(arg11 == 23 || arg11 == 24) {
                    arg10.database.a("DELETE FROM media_holes_v2 WHERE uid != 0 AND type >= 0 AND start IN (0, 1)").c().e();
                    arg10.database.a("PRAGMA user_version = 25").c().e();
                    arg11 = 25;
                }

                if(arg11 == 25 || arg11 == 26) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS channel_users_v2(did INTEGER, uid INTEGER, date INTEGER, data BLOB, PRIMARY KEY(did, uid))").c().e();
                    arg10.database.a("PRAGMA user_version = 27").c().e();
                    arg11 = 27;
                }

                if(arg11 == 27) {
                    arg10.database.a("ALTER TABLE web_recent_v3 ADD COLUMN document BLOB default NULL").c().e();
                    arg10.database.a("PRAGMA user_version = 28").c().e();
                    arg11 = 28;
                }

                if(arg11 == 28 || arg11 == 29) {
                    arg10.database.a("DELETE FROM sent_files_v2 WHERE 1").c().e();
                    arg10.database.a("DELETE FROM download_queue WHERE 1").c().e();
                    arg10.database.a("PRAGMA user_version = 30").c().e();
                    arg11 = 30;
                }

                if(arg11 == 30) {
                    arg10.database.a("ALTER TABLE chat_settings_v2 ADD COLUMN pinned INTEGER default 0").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS chat_settings_pinned_idx ON chat_settings_v2(uid, pinned) WHERE pinned != 0;").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS chat_pinned(uid INTEGER PRIMARY KEY, pinned INTEGER, data BLOB)").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS chat_pinned_mid_idx ON chat_pinned(uid, pinned) WHERE pinned != 0;").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS users_data(uid INTEGER PRIMARY KEY, about TEXT)").c().e();
                    arg10.database.a("PRAGMA user_version = 31").c().e();
                    arg11 = 31;
                }

                if(arg11 == 31) {
                    arg10.database.a("DROP TABLE IF EXISTS bot_recent;").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS chat_hints(did INTEGER, type INTEGER, rating REAL, date INTEGER, PRIMARY KEY(did, type))").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS chat_hints_rating_idx ON chat_hints(rating);").c().e();
                    arg10.database.a("PRAGMA user_version = 32").c().e();
                    arg11 = 32;
                }

                if(arg11 == 32) {
                    arg10.database.a("DROP INDEX IF EXISTS uid_mid_idx_imp_messages;").c().e();
                    arg10.database.a("DROP INDEX IF EXISTS uid_date_mid_imp_idx_messages;").c().e();
                    arg10.database.a("PRAGMA user_version = 33").c().e();
                    arg11 = 33;
                }

                if(arg11 == 33) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS pending_tasks(id INTEGER PRIMARY KEY, data BLOB);").c().e();
                    arg10.database.a("PRAGMA user_version = 34").c().e();
                    arg11 = 34;
                }

                if(arg11 == 34) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS stickers_featured(id INTEGER PRIMARY KEY, data BLOB, unread BLOB, date INTEGER, hash TEXT);").c().e();
                    arg10.database.a("PRAGMA user_version = 35").c().e();
                    arg11 = 35;
                }

                if(arg11 == 35) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS requested_holes(uid INTEGER, seq_out_start INTEGER, seq_out_end INTEGER, PRIMARY KEY (uid, seq_out_start, seq_out_end));").c().e();
                    arg10.database.a("PRAGMA user_version = 36").c().e();
                    arg11 = 36;
                }

                if(arg11 == 36) {
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN in_seq_no INTEGER default 0").c().e();
                    arg10.database.a("PRAGMA user_version = 37").c().e();
                    arg11 = 37;
                }

                if(arg11 == 37) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS botcache(id TEXT PRIMARY KEY, date INTEGER, data BLOB)").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS botcache_date_idx ON botcache(date);").c().e();
                    arg10.database.a("PRAGMA user_version = 38").c().e();
                    arg11 = 38;
                }

                if(arg11 == 38) {
                    arg10.database.a("ALTER TABLE dialogs ADD COLUMN pinned INTEGER default 0").c().e();
                    arg10.database.a("PRAGMA user_version = 39").c().e();
                    arg11 = 39;
                }

                if(arg11 == 39) {
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN admin_id INTEGER default 0").c().e();
                    arg10.database.a("PRAGMA user_version = 40").c().e();
                    arg11 = 40;
                }

                if(arg11 == 40) {
                    arg10.fixNotificationSettings();
                    arg10.database.a("PRAGMA user_version = 41").c().e();
                    arg11 = 41;
                }

                if(arg11 == 41) {
                    arg10.database.a("ALTER TABLE messages ADD COLUMN mention INTEGER default 0").c().e();
                    arg10.database.a("ALTER TABLE user_contacts_v6 ADD COLUMN imported INTEGER default 0").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS uid_mention_idx_messages ON messages(uid, mention, read_state);").c().e();
                    arg10.database.a("PRAGMA user_version = 42").c().e();
                    arg11 = 42;
                }

                if(arg11 == 42) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS sharing_locations(uid INTEGER PRIMARY KEY, mid INTEGER, date INTEGER, period INTEGER, message BLOB);").c().e();
                    arg10.database.a("PRAGMA user_version = 43").c().e();
                    arg11 = 43;
                }

                if(arg11 == 43) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS channel_admins(did INTEGER, uid INTEGER, PRIMARY KEY(did, uid))").c().e();
                    arg10.database.a("PRAGMA user_version = 44").c().e();
                    arg11 = 44;
                }

                if(arg11 == 44) {
                    arg10.database.a("CREATE TABLE IF NOT EXISTS user_contacts_v7(key TEXT PRIMARY KEY, uid INTEGER, fname TEXT, sname TEXT, imported INTEGER)").c().e();
                    arg10.database.a("CREATE TABLE IF NOT EXISTS user_phones_v7(key TEXT, phone TEXT, sphone TEXT, deleted INTEGER, PRIMARY KEY (key, phone))").c().e();
                    arg10.database.a("CREATE INDEX IF NOT EXISTS sphone_deleted_idx_user_phones ON user_phones_v7(sphone, deleted);").c().e();
                    arg10.database.a("PRAGMA user_version = 45").c().e();
                    arg11 = 45;
                }

                if(arg11 == 45) {
                    arg10.database.a("ALTER TABLE enc_chats ADD COLUMN mtproto_seq INTEGER default 0").c().e();
                    arg10.database.a("PRAGMA user_version = 46").c().e();
                    arg11 = 46;
                }

                if(arg11 == 46) {
                    arg10.database.a("DELETE FROM botcache WHERE 1").c().e();
                    arg10.database.a("PRAGMA user_version = 47").c().e();
                    arg11 = 47;
                }

                if(arg11 != 47) {
                    return;
                }

                arg10.database.a("ALTER TABLE dialogs ADD COLUMN flags INTEGER default 0").c().e();
                arg10.database.a("PRAGMA user_version = 48").c().e();
                return;
            label_106:
                goto label_910;
            }
            catch(Exception v11) {
                goto label_106;
            }
        }

        goto label_107;
    label_910:
        FileLog.e(((Throwable)v11));
    }

    public static void lambda$updateDialogsWithDeletedMessages$114(MessagesStorage arg0, ArrayList arg1, ArrayList arg2, int arg3) {
        arg0.updateDialogsWithDeletedMessagesInternal(arg1, arg2, arg3);
    }

    public static void lambda$updateDialogsWithReadMessages$54(MessagesStorage arg1, SparseLongArray arg2, SparseLongArray arg3, ArrayList arg4) {
        arg1.updateDialogsWithReadMessagesInternal(null, arg2, arg3, arg4);
    }

    public static void lambda$updateEncryptedChat$89(MessagesStorage arg10, EncryptedChat arg11) {
        SQLitePreparedStatement v1;
        int v2;
        SQLitePreparedStatement v0 = null;
        try {
            v2 = 16;
            if((arg11.key_hash == null || arg11.key_hash.length < v2) && arg11.auth_key != null) {
                arg11.key_hash = AndroidUtilities.calcAuthKeyHash(arg11.auth_key);
            }

            v1 = arg10.database.a("UPDATE enc_chats SET data = ?, g = ?, authkey = ?, ttl = ?, layer = ?, seq_in = ?, seq_out = ?, use_count = ?, exchange_id = ?, key_date = ?, fprint = ?, fauthkey = ?, khash = ?, in_seq_no = ?, admin_id = ?, mtproto_seq = ? WHERE uid = ?");
            goto label_15;
        }
        catch(Throwable v11) {
        }
        catch(Exception v11_1) {
            goto label_133;
            try {
            label_15:
                NativeByteBuffer v0_1 = new NativeByteBuffer(arg11.getObjectSize());
                int v4 = arg11.a_or_b != null ? arg11.a_or_b.length : 1;
                NativeByteBuffer v3 = new NativeByteBuffer(v4);
                int v6 = arg11.auth_key != null ? arg11.auth_key.length : 1;
                NativeByteBuffer v4_1 = new NativeByteBuffer(v6);
                int v7 = arg11.future_auth_key != null ? arg11.future_auth_key.length : 1;
                NativeByteBuffer v6_1 = new NativeByteBuffer(v7);
                int v8 = arg11.key_hash != null ? arg11.key_hash.length : 1;
                NativeByteBuffer v7_1 = new NativeByteBuffer(v8);
                arg11.serializeToStream(((AbstractSerializedData)v0_1));
                v1.a(1, v0_1);
                if(arg11.a_or_b != null) {
                    v3.writeBytes(arg11.a_or_b);
                }

                if(arg11.auth_key != null) {
                    v4_1.writeBytes(arg11.auth_key);
                }

                if(arg11.future_auth_key != null) {
                    v6_1.writeBytes(arg11.future_auth_key);
                }

                if(arg11.key_hash != null) {
                    v7_1.writeBytes(arg11.key_hash);
                }

                v1.a(2, v3);
                v1.a(3, v4_1);
                v1.a(4, arg11.ttl);
                v1.a(5, arg11.layer);
                v1.a(6, arg11.seq_in);
                v1.a(7, arg11.seq_out);
                v1.a(8, arg11.key_use_count_in << v2 | arg11.key_use_count_out);
                v1.a(9, arg11.exchange_id);
                v1.a(10, arg11.key_create_date);
                v1.a(11, arg11.future_key_fingerprint);
                v1.a(12, v6_1);
                v1.a(13, v7_1);
                v1.a(14, arg11.in_seq_no);
                v1.a(15, arg11.admin_id);
                v1.a(v2, arg11.mtproto_seq);
                v1.a(17, arg11.id);
                v1.b();
                v0_1.reuse();
                v3.reuse();
                v4_1.reuse();
                v6_1.reuse();
                v7_1.reuse();
                if(v1 == null) {
                    return;
                }

                goto label_122;
            }
            catch(Throwable v11) {
            }
            catch(Exception v11_1) {
                v0 = v1;
                try {
                label_133:
                    FileLog.e(((Throwable)v11_1));
                    if(v0 == null) {
                        return;
                    }
                }
                catch(Throwable v11) {
                    v1 = v0;
                    goto label_137;
                }

                v0.e();
                return;
            }
        }

    label_137:
        if(v1 != null) {
            v1.e();
        }

        throw v11;
    label_122:
        v1.e();
    }

    public static void lambda$updateEncryptedChatLayer$88(MessagesStorage arg3, EncryptedChat arg4) {
        SQLitePreparedStatement v0;
        SQLitePreparedStatement v1;
        try {
            v1 = arg3.database.a("UPDATE enc_chats SET layer = ? WHERE uid = ?");
            v0 = null;
            goto label_5;
        }
        catch(Throwable v4) {
        }
        catch(Exception v4_1) {
            goto label_23;
            try {
            label_5:
                v1.a(1, arg4.layer);
                v1.a(2, arg4.id);
                v1.b();
                if(v1 == null) {
                    return;
                }

                goto label_12;
            }
            catch(Throwable v4) {
            }
            catch(Exception v4_1) {
                v0 = v1;
                try {
                label_23:
                    FileLog.e(((Throwable)v4_1));
                    if(v0 == null) {
                        return;
                    }
                }
                catch(Throwable v4) {
                    v1 = v0;
                    goto label_27;
                }

                v0.e();
                return;
            }
        }

    label_27:
        if(v1 != null) {
            v1.e();
        }

        throw v4;
    label_12:
        v1.e();
    }

    public static void lambda$updateEncryptedChatSeq$86(MessagesStorage arg8, EncryptedChat arg9, boolean arg10) {
        SQLitePreparedStatement v1;
        SQLitePreparedStatement v0 = null;
        try {
            v1 = arg8.database.a("UPDATE enc_chats SET seq_in = ?, seq_out = ?, use_count = ?, in_seq_no = ?, mtproto_seq = ? WHERE uid = ?");
            goto label_4;
        }
        catch(Throwable v9) {
        }
        catch(Exception v9_1) {
            goto label_57;
            try {
            label_4:
                v1.a(1, arg9.seq_in);
                int v3 = 2;
                v1.a(v3, arg9.seq_out);
                v1.a(3, arg9.key_use_count_in << 16 | arg9.key_use_count_out);
                v1.a(4, arg9.in_seq_no);
                v1.a(5, arg9.mtproto_seq);
                v1.a(6, arg9.id);
                v1.b();
                if(arg10) {
                    long v4 = (((long)arg9.id)) << 32;
                    SQLiteDatabase v10 = arg8.database;
                    Locale v0_1 = Locale.US;
                    v10.a(String.format(v0_1, "DELETE FROM messages WHERE mid IN (SELECT m.mid FROM messages as m LEFT JOIN messages_seq as s ON m.mid = s.mid WHERE m.uid = %d AND m.date = 0 AND m.mid < 0 AND s.seq_out <= %d)", Long.valueOf(v4), Integer.valueOf(arg9.in_seq_no))).c().e();
                }

                goto label_45;
            }
            catch(Throwable v9) {
            }
            catch(Exception v9_1) {
                v0 = v1;
                try {
                label_57:
                    FileLog.e(((Throwable)v9_1));
                    if(v0 == null) {
                        return;
                    }
                }
                catch(Throwable v9) {
                    v1 = v0;
                    goto label_61;
                }

                v0.e();
                return;
            }
        }

    label_61:
        if(v1 != null) {
            v1.e();
        }

        throw v9;
    label_45:
        if(v1 != null) {
            v1.e();
        }
    }

    public static void lambda$updateEncryptedChatTTL$87(MessagesStorage arg3, EncryptedChat arg4) {
        SQLitePreparedStatement v0;
        SQLitePreparedStatement v1;
        try {
            v1 = arg3.database.a("UPDATE enc_chats SET ttl = ? WHERE uid = ?");
            v0 = null;
            goto label_5;
        }
        catch(Throwable v4) {
        }
        catch(Exception v4_1) {
            goto label_23;
            try {
            label_5:
                v1.a(1, arg4.ttl);
                v1.a(2, arg4.id);
                v1.b();
                if(v1 == null) {
                    return;
                }

                goto label_12;
            }
            catch(Throwable v4) {
            }
            catch(Exception v4_1) {
                v0 = v1;
                try {
                label_23:
                    FileLog.e(((Throwable)v4_1));
                    if(v0 == null) {
                        return;
                    }
                }
                catch(Throwable v4) {
                    v1 = v0;
                    goto label_27;
                }

                v0.e();
                return;
            }
        }

    label_27:
        if(v1 != null) {
            v1.e();
        }

        throw v4;
    label_12:
        v1.e();
    }

    public static void lambda$updateMessageStateAndId$108(MessagesStorage arg0, long arg1, Integer arg3, int arg4, int arg5, int arg6) {
        arg0.updateMessageStateAndIdInternal(arg1, arg3, arg4, arg5, arg6);
    }

    public static void lambda$updateUsers$109(MessagesStorage arg0, ArrayList arg1, boolean arg2, boolean arg3) {
        arg0.updateUsersInternal(arg1, arg2, arg3);
    }

    public void loadChannelAdmins(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$N5_Cnhe76ktbto7sbw_355RaoA0(this, arg3));
    }

    public void loadChatInfo(int arg9, CountDownLatch arg10, boolean arg11, boolean arg12) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$1XWLMSQzhs4YHJEChUZUadtSFeI(this, arg9, arg10, arg11, arg12));
    }

    private void loadPendingTasks() {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$vCb208TR5dh2gDF7AKgoPH8_6tY(this));
    }

    public void loadUnreadMessages() {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$Kcwowo8BerRvhLwdlrXhixJYcQQ(this));
    }

    public void loadWebRecent(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$rSmlh4Rq9JIJGBItUL9SAQSwJfM(this, arg3));
    }

    public void markMentionMessageAsRead(int arg9, int arg10, long arg11) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$Wnzci7K-YOWBbO8qf_uLmKwVpyg(this, arg9, arg10, arg11));
    }

    public void markMessageAsMention(long arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$u_ixI9JzOW6FPsm9cSdd1ws__vw(this, arg3));
    }

    public void markMessageAsSendError(Message arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$gv96BZNjlUiU0dyzUZaDuCT0pXY(this, arg3));
    }

    public ArrayList markMessagesAsDeleted(int arg2, int arg3, boolean arg4) {
        if(arg4) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$JtLXcQyi-0df6XIBrKgBHBO9soo(this, arg2, arg3));
            return null;
        }

        return this.markMessagesAsDeletedInternal(arg2, arg3);
    }

    public ArrayList markMessagesAsDeleted(ArrayList arg3, boolean arg4, int arg5) {
        ArrayList v1 = null;
        if(arg3.isEmpty()) {
            return v1;
        }

        if(arg4) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$3JoU5tI4DQfgMF-XdDHitQLjIWE(this, arg3, arg5));
            return v1;
        }

        return this.markMessagesAsDeletedInternal(arg3, arg5);
    }

    public void markMessagesAsDeletedByRandoms(ArrayList arg3) {
        if(arg3.isEmpty()) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$EMz4h6W689YMF7ar_JEY9QuCOfA(this, arg3));
    }

    private ArrayList markMessagesAsDeletedInternal(ArrayList arg20, int arg21) {
        Integer v10_4;
        int v4_1;
        SparseArray v3_6;
        SQLiteCursor v0_4;
        int v14_1;
        int v10_3;
        Object v7_2;
        long v7_1;
        String v17;
        long v14;
        int v12;
        int v11;
        SQLiteCursor v10_1;
        ArrayList v9_1;
        String v8_1;
        String v0_2;
        int v7;
        LongSparseArray v6;
        ArrayList v5;
        ArrayList v4;
        MessagesStorage v1 = this;
        ArrayList v2 = arg20;
        int v0 = arg21;
        try {
            v4 = new ArrayList(((Collection)v2));
            v5 = new ArrayList();
            v6 = new LongSparseArray();
            v7 = 0;
            if(v0 != 0) {
                StringBuilder v8 = new StringBuilder(arg20.size());
                int v9;
                for(v9 = 0; v9 < arg20.size(); ++v9) {
                    long v10 = (((long)v2.get(v9).intValue())) | (((long)v0)) << 32;
                    if(v8.length() > 0) {
                        v8.append(',');
                    }

                    v8.append(v10);
                }

                v0_2 = v8.toString();
            }
            else {
                v0_2 = TextUtils.join(",", ((Iterable)v2));
            }

            v8_1 = v0_2;
            v9_1 = new ArrayList();
            v0 = UserConfig.getInstance(v1.currentAccount).getClientUserId();
            v10_1 = v1.database.b(String.format(Locale.US, "SELECT uid, data, read_state, out, mention, mid FROM messages WHERE mid IN(%s)", v8_1), new Object[0]);
        }
        catch(Exception v0_1) {
            goto label_357;
        }

        while(true) {
            v11 = 3;
            v12 = 2;
            try {
                if(!v10_1.a()) {
                    break;
                }

                v14 = v10_1.d(v7);
                v4.remove(Integer.valueOf(v10_1.b(5)));
                v17 = v8_1;
                v7_1 = ((long)v0);
                if(v14 != v7_1) {
                    goto label_65;
                }

                goto label_62;
            }
            catch(Exception v0_1) {
                v17 = v8_1;
                goto label_151;
            }

            try {
            label_65:
                int v3 = v10_1.b(v12);
                if(v10_1.b(v11) == 0) {
                    v7_2 = v6.get(v14);
                    if(v7_2 == null) {
                        Integer[] v7_3 = new Integer[v12];
                        v7_3[0] = Integer.valueOf(0);
                        v7_3[1] = Integer.valueOf(0);
                        v6.put(v14, v7_3);
                    }

                    if(v3 < v12) {
                        v7_2[1] = Integer.valueOf(v7_2[1].intValue() + 1);
                    }

                    if(v3 != 0 && v3 != v12) {
                        goto label_93;
                    }

                    v7_2[0] = Integer.valueOf(v7_2[0].intValue() + 1);
                }

            label_93:
                if((((int)v14)) != 0) {
                    goto label_62;
                }

                NativeByteBuffer v3_1 = v10_1.g(1);
                if(v3_1 == null) {
                    goto label_62;
                }

                Message v8_2 = Message.TLdeserialize(((AbstractSerializedData)v3_1), v3_1.readInt32(false), false);
                v8_2.readAttachPath(((AbstractSerializedData)v3_1), UserConfig.getInstance(v1.currentAccount).clientUserId);
                v3_1.reuse();
                if(v8_2 == null) {
                    goto label_62;
                }

                if((v8_2.media instanceof TL_messageMediaPhoto)) {
                    Iterator v3_2 = v8_2.media.photo.sizes.iterator();
                    while(true) {
                        if(!v3_2.hasNext()) {
                            goto label_62;
                        }

                        File v7_4 = FileLoader.getPathToAttach(v3_2.next());
                        if(v7_4 == null) {
                            continue;
                        }

                        if(v7_4.toString().length() <= 0) {
                            continue;
                        }

                        v9_1.add(v7_4);
                    }
                }

                if(!(v8_2.media instanceof TL_messageMediaDocument)) {
                    goto label_62;
                }

                File v3_3 = FileLoader.getPathToAttach(v8_2.media.document);
                if(v3_3 != null && v3_3.toString().length() > 0) {
                    v9_1.add(v3_3);
                }

                v3_3 = FileLoader.getPathToAttach(v8_2.media.document.thumb);
                if(v3_3 == null) {
                    goto label_62;
                }

                if(v3_3.toString().length() <= 0) {
                    goto label_62;
                }

                v9_1.add(v3_3);
                goto label_62;
            }
            catch(Exception v0_1) {
            }

            try {
            label_151:
                FileLog.e(((Throwable)v0_1));
                goto label_152;
            }
            catch(Exception v0_1) {
                goto label_357;
            }

        label_62:
            v8_1 = v17;
            v7 = 0;
        }

        v17 = v8_1;
        try {
        label_152:
            v10_1.b();
            FileLoader.getInstance(v1.currentAccount).deleteFiles(v9_1, 0);
            for(v0 = 0; v0 < v6.size(); ++v0) {
                v7_1 = v6.keyAt(v0);
                Object v3_4 = v6.valueAt(v0);
                SQLiteDatabase v9_2 = v1.database;
                StringBuilder v10_2 = new StringBuilder();
                v10_2.append("SELECT unread_count, unread_count_i FROM dialogs WHERE did = ");
                v10_2.append(v7_1);
                SQLiteCursor v9_3 = v9_2.b(v10_2.toString(), new Object[0]);
                if(v9_3.a()) {
                    v10_3 = v9_3.b(0);
                    v14_1 = v9_3.b(1);
                }
                else {
                    v10_3 = 0;
                    v14_1 = 0;
                }

                v9_3.b();
                v5.add(Long.valueOf(v7_1));
                SQLitePreparedStatement v9_4 = v1.database.a("UPDATE dialogs SET unread_count = ?, unread_count_i = ? WHERE did = ?");
                v9_4.d();
                v9_4.a(1, Math.max(0, v10_3 - v3_4[0].intValue()));
                v9_4.a(v12, Math.max(0, v14_1 - v3_4[1].intValue()));
                v9_4.a(v11, v7_1);
                v9_4.b();
                v9_4.e();
            }

            v1.database.a(String.format(Locale.US, "DELETE FROM messages WHERE mid IN(%s)", v17)).c().e();
            v1.database.a(String.format(Locale.US, "DELETE FROM bot_keyboard WHERE mid IN(%s)", v17)).c().e();
            v1.database.a(String.format(Locale.US, "DELETE FROM messages_seq WHERE mid IN(%s)", v17)).c().e();
            if(v4.isEmpty()) {
                SQLiteDatabase v0_3 = v1.database;
                Locale v3_5 = Locale.US;
                Object[] v6_1 = new Object[1];
                v7 = 0;
                v6_1[0] = v17;
                v0_4 = v0_3.b(String.format(v3_5, "SELECT uid, type FROM media_v2 WHERE mid IN(%s)", v6_1), new Object[0]);
                v3_6 = null;
                goto label_244;
            }
            else {
                SQLitePreparedStatement v0_5 = v1.database.a("DELETE FROM media_counts_v2 WHERE 1").c();
                goto label_333;
            label_244:
                while(v0_4.a()) {
                    long v8_3 = v0_4.d(v7);
                    v4_1 = v0_4.b(1);
                    if(v3_6 == null) {
                        v3_6 = new SparseArray();
                    }

                    Object v6_2 = v3_6.get(v4_1);
                    if(v6_2 == null) {
                        v6 = new LongSparseArray();
                        v10_4 = Integer.valueOf(0);
                        v3_6.put(v4_1, v6);
                    }
                    else {
                        Object v10_5 = ((LongSparseArray)v6_2).get(v8_3);
                    }

                    if(v10_4 == null) {
                        v10_4 = Integer.valueOf(0);
                    }

                    ((LongSparseArray)v6_2).put(v8_3, Integer.valueOf(v10_4.intValue() + 1));
                    v7 = 0;
                }

                v0_4.b();
                if(v3_6 == null) {
                    goto label_340;
                }

                v0_5 = v1.database.a("REPLACE INTO media_counts_v2 VALUES(?, ?, ?)");
                v4_1 = 0;
                while(v4_1 < v3_6.size()) {
                    int v6_3 = v3_6.keyAt(v4_1);
                    v7_2 = v3_6.valueAt(v4_1);
                    int v8_4 = 0;
                    while(v8_4 < ((LongSparseArray)v7_2).size()) {
                        long v9_5 = ((LongSparseArray)v7_2).keyAt(v8_4);
                        SQLiteDatabase v14_2 = v1.database;
                        Locale v15 = Locale.US;
                        SQLiteCursor v11_1 = v14_2.b(String.format(v15, "SELECT count FROM media_counts_v2 WHERE uid = %d AND type = %d LIMIT 1", Long.valueOf(v9_5), Integer.valueOf(v6_3)), new Object[0]);
                        v14_1 = -1;
                        int v13_1 = v11_1.a() ? v11_1.b(0) : -1;
                        v11_1.b();
                        if(v13_1 != v14_1) {
                            v0_5.d();
                            v12 = Math.max(0, v13_1 - ((LongSparseArray)v7_2).valueAt(v8_4).intValue());
                            v0_5.a(1, v9_5);
                            v0_5.a(2, v6_3);
                            v0_5.a(3, v12);
                            v0_5.b();
                        }
                        else {
                        }

                        ++v8_4;
                        v12 = 2;
                    }

                    ++v4_1;
                    v12 = 2;
                }

            label_333:
                v0_5.e();
            }

        label_340:
            v1.database.a(String.format(Locale.US, "DELETE FROM media_v2 WHERE mid IN(%s)", v17)).c().e();
            DataQuery.getInstance(v1.currentAccount).clearBotKeyboard(0, v2);
            return v5;
        }
        catch(Exception v0_1) {
        label_357:
            FileLog.e(((Throwable)v0_1));
            return null;
        }
    }

    private ArrayList markMessagesAsDeletedInternal(int arg17, int arg18) {
        int v12_3;
        Integer[] v12_2;
        SQLiteCursor v8_1;
        int v14;
        int v13;
        int v11;
        ArrayList v6;
        long v4;
        LongSparseArray v3;
        ArrayList v2;
        MessagesStorage v1 = this;
        int v0 = arg17;
        try {
            v2 = new ArrayList();
            v3 = new LongSparseArray();
            v4 = (((long)arg18)) | (((long)v0)) << 32;
            v6 = new ArrayList();
            int v7 = UserConfig.getInstance(v1.currentAccount).getClientUserId();
            SQLiteDatabase v8 = v1.database;
            Locale v9 = Locale.US;
            v11 = 2;
            Object[] v12 = new Object[v11];
            v13 = -v0;
            v14 = 0;
            v12[0] = Integer.valueOf(v13);
            v12[1] = Long.valueOf(v4);
            v8_1 = v8.b(String.format(v9, "SELECT uid, data, read_state, out, mention FROM messages WHERE uid = %d AND mid <= %d", v12), new Object[0]);
        }
        catch(Exception v0_1) {
            goto label_222;
        }

        try {
            while(true) {
                if(!v8_1.a()) {
                    goto label_127;
                }

                long v9_1 = v8_1.d(v14);
                if(v9_1 != (((long)v7))) {
                    v0 = v8_1.b(v11);
                    if(v8_1.b(3) == 0) {
                        Object v12_1 = v3.get(v9_1);
                        if(v12_1 == null) {
                            v12_2 = new Integer[v11];
                            v12_2[0] = Integer.valueOf(0);
                            v12_2[1] = Integer.valueOf(0);
                            v3.put(v9_1, v12_2);
                        }

                        if(v0 < v11) {
                            v12_2[1] = Integer.valueOf(v12_2[1].intValue() + 1);
                        }

                        if(v0 != 0 && v0 != v11) {
                            goto label_72;
                        }

                        v12_2[0] = Integer.valueOf(v12_2[0].intValue() + 1);
                    }

                label_72:
                    if((((int)v9_1)) != 0) {
                        goto label_37;
                    }

                    NativeByteBuffer v0_2 = v8_1.g(1);
                    if(v0_2 == null) {
                        goto label_37;
                    }

                    Message v10 = Message.TLdeserialize(((AbstractSerializedData)v0_2), v0_2.readInt32(false), false);
                    v10.readAttachPath(((AbstractSerializedData)v0_2), UserConfig.getInstance(v1.currentAccount).clientUserId);
                    v0_2.reuse();
                    if(v10 == null) {
                        goto label_37;
                    }

                    if((v10.media instanceof TL_messageMediaPhoto)) {
                        Iterator v0_3 = v10.media.photo.sizes.iterator();
                        while(true) {
                            if(!v0_3.hasNext()) {
                                goto label_37;
                            }

                            File v9_2 = FileLoader.getPathToAttach(v0_3.next());
                            if(v9_2 == null) {
                                continue;
                            }

                            if(v9_2.toString().length() <= 0) {
                                continue;
                            }

                            v6.add(v9_2);
                        }
                    }

                    if(!(v10.media instanceof TL_messageMediaDocument)) {
                        goto label_37;
                    }

                    File v0_4 = FileLoader.getPathToAttach(v10.media.document);
                    if(v0_4 != null && v0_4.toString().length() > 0) {
                        v6.add(v0_4);
                    }

                    v0_4 = FileLoader.getPathToAttach(v10.media.document.thumb);
                    if(v0_4 == null) {
                        goto label_37;
                    }

                    if(v0_4.toString().length() <= 0) {
                        goto label_37;
                    }

                    v6.add(v0_4);
                }

            label_37:
                v14 = 0;
            }
        }
        catch(Exception v0_1) {
            try {
                FileLog.e(((Throwable)v0_1));
            label_127:
                v8_1.b();
                FileLoader.getInstance(v1.currentAccount).deleteFiles(v6, 0);
                for(v0 = 0; v0 < v3.size(); ++v0) {
                    long v6_1 = v3.keyAt(v0);
                    Object v8_2 = v3.valueAt(v0);
                    SQLiteDatabase v9_3 = v1.database;
                    StringBuilder v10_1 = new StringBuilder();
                    v10_1.append("SELECT unread_count, unread_count_i FROM dialogs WHERE did = ");
                    v10_1.append(v6_1);
                    SQLiteCursor v9_4 = v9_3.b(v10_1.toString(), new Object[0]);
                    if(v9_4.a()) {
                        v14 = v9_4.b(0);
                        v12_3 = v9_4.b(1);
                    }
                    else {
                        v12_3 = 0;
                        v14 = 0;
                    }

                    v9_4.b();
                    v2.add(Long.valueOf(v6_1));
                    SQLitePreparedStatement v9_5 = v1.database.a("UPDATE dialogs SET unread_count = ?, unread_count_i = ? WHERE did = ?");
                    v9_5.d();
                    v9_5.a(1, Math.max(0, v14 - v8_2[0].intValue()));
                    v9_5.a(v11, Math.max(0, v12_3 - v8_2[1].intValue()));
                    v9_5.a(3, v6_1);
                    v9_5.b();
                    v9_5.e();
                }

                SQLiteDatabase v0_5 = v1.database;
                Locale v3_1 = Locale.US;
                v0_5.a(String.format(v3_1, "DELETE FROM messages WHERE uid = %d AND mid <= %d", Integer.valueOf(v13), Long.valueOf(v4))).c().e();
                v0_5 = v1.database;
                v3_1 = Locale.US;
                v0_5.a(String.format(v3_1, "DELETE FROM media_v2 WHERE uid = %d AND mid <= %d", Integer.valueOf(v13), Long.valueOf(v4))).c().e();
                v1.database.a(String.format(Locale.US, "DELETE FROM media_counts_v2 WHERE uid = %d", Integer.valueOf(v13))).c().e();
                return v2;
            }
            catch(Exception v0_1) {
            label_222:
                FileLog.e(((Throwable)v0_1));
                return null;
            }
        }
    }

    public void markMessagesAsRead(SparseLongArray arg2, SparseLongArray arg3, SparseIntArray arg4, boolean arg5) {
        if(arg5) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$pTWff6CQiHY9ZoKO1NBJWB7cxfM(this, arg2, arg3, arg4));
        }
        else {
            this.markMessagesAsReadInternal(arg2, arg3, arg4);
        }
    }

    private void markMessagesAsReadInternal(SparseLongArray arg12, SparseLongArray arg13, SparseIntArray arg14) {
        try {
            int v1 = 2;
            int v2 = 0;
            if(!MessagesStorage.isEmpty(arg12)) {
                int v0;
                for(v0 = 0; v0 < arg12.size(); ++v0) {
                    int v4 = arg12.keyAt(v0);
                    long v5 = arg12.get(v4);
                    SQLiteDatabase v7 = this.database;
                    Locale v8 = Locale.US;
                    v7.a(String.format(v8, "UPDATE messages SET read_state = read_state | 1 WHERE uid = %d AND mid > 0 AND mid <= %d AND read_state IN(0,2) AND out = 0", Integer.valueOf(v4), Long.valueOf(v5))).c().e();
                }
            }

            if(!MessagesStorage.isEmpty(arg13)) {
                int v12_1;
                for(v12_1 = 0; v12_1 < arg13.size(); ++v12_1) {
                    v0 = arg13.keyAt(v12_1);
                    long v4_1 = arg13.get(v0);
                    SQLiteDatabase v6 = this.database;
                    Locale v7_1 = Locale.US;
                    v6.a(String.format(v7_1, "UPDATE messages SET read_state = read_state | 1 WHERE uid = %d AND mid > 0 AND mid <= %d AND read_state IN(0,2) AND out = 1", Integer.valueOf(v0), Long.valueOf(v4_1))).c().e();
                }
            }

            if(arg14 == null) {
                return;
            }

            if(MessagesStorage.isEmpty(arg14)) {
                return;
            }

            while(v2 < arg14.size()) {
                long v12_2 = (((long)arg14.keyAt(v2))) << 32;
                v0 = arg14.valueAt(v2);
                SQLitePreparedStatement v4_2 = this.database.a("UPDATE messages SET read_state = read_state | 1 WHERE uid = ? AND date <= ? AND read_state IN(0,2) AND out = 1");
                v4_2.d();
                v4_2.a(1, v12_2);
                v4_2.a(v1, v0);
                v4_2.b();
                v4_2.e();
                ++v2;
            }
        }
        catch(Exception v12) {
            FileLog.e(((Throwable)v12));
        }
    }

    public void markMessagesContentAsRead(ArrayList arg3, int arg4) {
        if(MessagesStorage.isEmpty(((List)arg3))) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$onVJfnJbR5yDEilU9D9deKefz4M(this, arg3, arg4));
    }

    public void openDatabase(boolean arg8) {
        File v0 = ApplicationLoader.getFilesDirFixed();
        if(this.currentAccount != 0) {
            StringBuilder v2 = new StringBuilder();
            v2.append("account");
            v2.append(this.currentAccount);
            v2.append("/");
            File v1 = new File(v0, v2.toString());
            v1.mkdirs();
            v0 = v1;
        }

        this.cacheFile = new File(v0, "cache4.db");
        this.walCacheFile = new File(v0, "cache4.db-wal");
        this.shmCacheFile = new File(v0, "cache4.db-shm");
        int v0_1 = this.cacheFile.exists() ^ 1;
        try {
            this.database = new SQLiteDatabase(this.cacheFile.getPath());
            this.database.a("PRAGMA secure_delete = ON").c().e();
            this.database.a("PRAGMA temp_store = 1").c().e();
            this.database.a("PRAGMA journal_mode = WAL").c().e();
            if(v0_1 != 0) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("create new database");
                }

                this.database.a("CREATE TABLE messages_holes(uid INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, start));").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS uid_end_messages_holes ON messages_holes(uid, end);").c().e();
                this.database.a("CREATE TABLE media_holes_v2(uid INTEGER, type INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, type, start));").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS uid_end_media_holes_v2 ON media_holes_v2(uid, type, end);").c().e();
                this.database.a("CREATE TABLE messages(mid INTEGER PRIMARY KEY, uid INTEGER, read_state INTEGER, send_state INTEGER, date INTEGER, data BLOB, out INTEGER, ttl INTEGER, media INTEGER, replydata BLOB, imp INTEGER, mention INTEGER)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS uid_mid_idx_messages ON messages(uid, mid);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS uid_date_mid_idx_messages ON messages(uid, date, mid);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS mid_out_idx_messages ON messages(mid, out);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS task_idx_messages ON messages(uid, out, read_state, ttl, date, send_state);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS send_state_idx_messages ON messages(mid, send_state, date) WHERE mid < 0 AND send_state = 1;").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS uid_mention_idx_messages ON messages(uid, mention, read_state);").c().e();
                this.database.a("CREATE TABLE download_queue(uid INTEGER, type INTEGER, date INTEGER, data BLOB, PRIMARY KEY (uid, type));").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS type_date_idx_download_queue ON download_queue(type, date);").c().e();
                this.database.a("CREATE TABLE user_contacts_v7(key TEXT PRIMARY KEY, uid INTEGER, fname TEXT, sname TEXT, imported INTEGER)").c().e();
                this.database.a("CREATE TABLE user_phones_v7(key TEXT, phone TEXT, sphone TEXT, deleted INTEGER, PRIMARY KEY (key, phone))").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS sphone_deleted_idx_user_phones ON user_phones_v7(sphone, deleted);").c().e();
                this.database.a("CREATE TABLE dialogs(did INTEGER PRIMARY KEY, date INTEGER, unread_count INTEGER, last_mid INTEGER, inbox_max INTEGER, outbox_max INTEGER, last_mid_i INTEGER, unread_count_i INTEGER, pts INTEGER, date_i INTEGER, pinned INTEGER, flags INTEGER)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS date_idx_dialogs ON dialogs(date);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS last_mid_idx_dialogs ON dialogs(last_mid);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS unread_count_idx_dialogs ON dialogs(unread_count);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS last_mid_i_idx_dialogs ON dialogs(last_mid_i);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS unread_count_i_idx_dialogs ON dialogs(unread_count_i);").c().e();
                this.database.a("CREATE TABLE randoms(random_id INTEGER, mid INTEGER, PRIMARY KEY (random_id, mid))").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS mid_idx_randoms ON randoms(mid);").c().e();
                this.database.a("CREATE TABLE enc_tasks_v2(mid INTEGER PRIMARY KEY, date INTEGER)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS date_idx_enc_tasks_v2 ON enc_tasks_v2(date);").c().e();
                this.database.a("CREATE TABLE messages_seq(mid INTEGER PRIMARY KEY, seq_in INTEGER, seq_out INTEGER);").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS seq_idx_messages_seq ON messages_seq(seq_in, seq_out);").c().e();
                this.database.a("CREATE TABLE params(id INTEGER PRIMARY KEY, seq INTEGER, pts INTEGER, date INTEGER, qts INTEGER, lsv INTEGER, sg INTEGER, pbytes BLOB)").c().e();
                this.database.a("INSERT INTO params VALUES(1, 0, 0, 0, 0, 0, 0, NULL)").c().e();
                this.database.a("CREATE TABLE media_v2(mid INTEGER PRIMARY KEY, uid INTEGER, date INTEGER, type INTEGER, data BLOB)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS uid_mid_type_date_idx_media ON media_v2(uid, mid, type, date);").c().e();
                this.database.a("CREATE TABLE bot_keyboard(uid INTEGER PRIMARY KEY, mid INTEGER, info BLOB)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS bot_keyboard_idx_mid ON bot_keyboard(mid);").c().e();
                this.database.a("CREATE TABLE chat_settings_v2(uid INTEGER PRIMARY KEY, info BLOB, pinned INTEGER)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS chat_settings_pinned_idx ON chat_settings_v2(uid, pinned) WHERE pinned != 0;").c().e();
                this.database.a("CREATE TABLE chat_pinned(uid INTEGER PRIMARY KEY, pinned INTEGER, data BLOB)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS chat_pinned_mid_idx ON chat_pinned(uid, pinned) WHERE pinned != 0;").c().e();
                this.database.a("CREATE TABLE chat_hints(did INTEGER, type INTEGER, rating REAL, date INTEGER, PRIMARY KEY(did, type))").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS chat_hints_rating_idx ON chat_hints(rating);").c().e();
                this.database.a("CREATE TABLE botcache(id TEXT PRIMARY KEY, date INTEGER, data BLOB)").c().e();
                this.database.a("CREATE INDEX IF NOT EXISTS botcache_date_idx ON botcache(date);").c().e();
                this.database.a("CREATE TABLE users_data(uid INTEGER PRIMARY KEY, about TEXT)").c().e();
                this.database.a("CREATE TABLE users(uid INTEGER PRIMARY KEY, name TEXT, status INTEGER, data BLOB)").c().e();
                this.database.a("CREATE TABLE chats(uid INTEGER PRIMARY KEY, name TEXT, data BLOB)").c().e();
                this.database.a("CREATE TABLE enc_chats(uid INTEGER PRIMARY KEY, user INTEGER, name TEXT, data BLOB, g BLOB, authkey BLOB, ttl INTEGER, layer INTEGER, seq_in INTEGER, seq_out INTEGER, use_count INTEGER, exchange_id INTEGER, key_date INTEGER, fprint INTEGER, fauthkey BLOB, khash BLOB, in_seq_no INTEGER, admin_id INTEGER, mtproto_seq INTEGER)").c().e();
                this.database.a("CREATE TABLE channel_users_v2(did INTEGER, uid INTEGER, date INTEGER, data BLOB, PRIMARY KEY(did, uid))").c().e();
                this.database.a("CREATE TABLE channel_admins(did INTEGER, uid INTEGER, PRIMARY KEY(did, uid))").c().e();
                this.database.a("CREATE TABLE contacts(uid INTEGER PRIMARY KEY, mutual INTEGER)").c().e();
                this.database.a("CREATE TABLE wallpapers(uid INTEGER PRIMARY KEY, data BLOB)").c().e();
                this.database.a("CREATE TABLE user_photos(uid INTEGER, id INTEGER, data BLOB, PRIMARY KEY (uid, id))").c().e();
                this.database.a("CREATE TABLE blocked_users(uid INTEGER PRIMARY KEY)").c().e();
                this.database.a("CREATE TABLE dialog_settings(did INTEGER PRIMARY KEY, flags INTEGER);").c().e();
                this.database.a("CREATE TABLE web_recent_v3(id TEXT, type INTEGER, image_url TEXT, thumb_url TEXT, local_url TEXT, width INTEGER, height INTEGER, size INTEGER, date INTEGER, document BLOB, PRIMARY KEY (id, type));").c().e();
                this.database.a("CREATE TABLE stickers_v2(id INTEGER PRIMARY KEY, data BLOB, date INTEGER, hash TEXT);").c().e();
                this.database.a("CREATE TABLE stickers_featured(id INTEGER PRIMARY KEY, data BLOB, unread BLOB, date INTEGER, hash TEXT);").c().e();
                this.database.a("CREATE TABLE hashtag_recent_v2(id TEXT PRIMARY KEY, date INTEGER);").c().e();
                this.database.a("CREATE TABLE webpage_pending(id INTEGER, mid INTEGER, PRIMARY KEY (id, mid));").c().e();
                this.database.a("CREATE TABLE sent_files_v2(uid TEXT, type INTEGER, data BLOB, PRIMARY KEY (uid, type))").c().e();
                this.database.a("CREATE TABLE search_recent(did INTEGER PRIMARY KEY, date INTEGER);").c().e();
                this.database.a("CREATE TABLE media_counts_v2(uid INTEGER, type INTEGER, count INTEGER, PRIMARY KEY(uid, type))").c().e();
                this.database.a("CREATE TABLE keyvalue(id TEXT PRIMARY KEY, value TEXT)").c().e();
                this.database.a("CREATE TABLE bot_info(uid INTEGER PRIMARY KEY, info BLOB)").c().e();
                this.database.a("CREATE TABLE pending_tasks(id INTEGER PRIMARY KEY, data BLOB);").c().e();
                this.database.a("CREATE TABLE requested_holes(uid INTEGER, seq_out_start INTEGER, seq_out_end INTEGER, PRIMARY KEY (uid, seq_out_start, seq_out_end));").c().e();
                this.database.a("CREATE TABLE sharing_locations(uid INTEGER PRIMARY KEY, mid INTEGER, date INTEGER, period INTEGER, message BLOB);").c().e();
                this.database.a("PRAGMA user_version = 48").c().e();
                goto label_504;
            }

            v0_1 = this.database.a("PRAGMA user_version", new Object[0]).intValue();
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("current db version = " + v0_1);
            }
        }
        catch(Exception v0_2) {
            goto label_471;
        }

        if(v0_1 == 0) {
            goto label_466;
        }

        try {
            SQLiteCursor v3_1 = this.database.b("SELECT seq, pts, date, qts, lsv, sg, pbytes FROM params WHERE id = 1", new Object[0]);
            if(v3_1.a()) {
                this.lastSeqValue = v3_1.b(0);
                this.lastPtsValue = v3_1.b(1);
                this.lastDateValue = v3_1.b(2);
                this.lastQtsValue = v3_1.b(3);
                this.lastSecretVersion = v3_1.b(4);
                this.secretG = v3_1.b(5);
                int v4 = 6;
                byte[] v6 = null;
                if(!v3_1.a(v4)) {
                    this.secretPBytes = v3_1.f(v4);
                    if(this.secretPBytes == null) {
                        goto label_445;
                    }
                    else if(this.secretPBytes.length == 1) {
                    }
                    else {
                        goto label_445;
                    }
                }

                this.secretPBytes = v6;
            }

        label_445:
            v3_1.b();
            goto label_462;
        }
        catch(Exception v1_1) {
            try {
                FileLog.e(((Throwable)v1_1));
            }
            catch(Exception v0_2) {
                goto label_471;
            }

            try {
                this.database.a("CREATE TABLE IF NOT EXISTS params(id INTEGER PRIMARY KEY, seq INTEGER, pts INTEGER, date INTEGER, qts INTEGER, lsv INTEGER, sg INTEGER, pbytes BLOB)").c().e();
                this.database.a("INSERT INTO params VALUES(1, 0, 0, 0, 0, 0, 0, NULL)").c().e();
                goto label_462;
            }
            catch(Exception v1_1) {
                try {
                    FileLog.e(((Throwable)v1_1));
                label_462:
                    if(v0_1 >= 48) {
                        goto label_504;
                    }

                    this.updateDbToLastVersion(v0_1);
                    goto label_504;
                label_466:
                    throw new Exception("malformed");
                }
                catch(Exception v0_2) {
                label_471:
                    FileLog.e(((Throwable)v0_2));
                    if(!arg8) {
                        goto label_504;
                    }

                    if(!v0_2.getMessage().contains("malformed")) {
                        goto label_504;
                    }

                    this.cleanupInternal();
                    UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetId = 0;
                    UserConfig.getInstance(this.currentAccount).totalDialogsLoadCount = 0;
                    UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetDate = 0;
                    UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetUserId = 0;
                    UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetChatId = 0;
                    UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetChannelId = 0;
                    UserConfig.getInstance(this.currentAccount).dialogsLoadOffsetAccess = 0;
                    UserConfig.getInstance(this.currentAccount).saveConfig(false);
                    this.openDatabase(false);
                }
            }
        }

    label_504:
        this.loadUnreadMessages();
        this.loadPendingTasks();
        try {
            this.openSync.countDown();
            return;
        }
        catch(Throwable ) {
            return;
        }
    }

    public void overwriteChannel(int arg3, TL_updates_channelDifferenceTooLong arg4, int arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$AVrdlSkNOxs49SB-egZcOtCsb8M(this, arg3, arg5, arg4));
    }

    public void processPendingRead(long arg13, long arg15, long arg17, int arg19, boolean arg20) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$0QtgEdLlxq8RHXoAPbtr5g-o2SA(this, arg13, arg15, arg20, arg17));
    }

    public void putBlockedUsers(SparseIntArray arg3, boolean arg4) {
        if(arg3 != null) {
            if(arg3.size() == 0) {
            }
            else {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$CLYSzoLH5ZYxvD-30805gFmJk0c(this, arg4, arg3));
            }
        }
    }

    public void putCachedPhoneBook(HashMap arg2, boolean arg3, boolean arg4) {
        if(arg2 != null && (!arg2.isEmpty() || (arg3) || (arg4))) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$wmljbs8FEolkTWvp5yYbTxomxl8(this, arg2, arg3));
        }
    }

    public void putChannelAdmins(int arg3, ArrayList arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$2Y7OF-MaG5vdu1wdudKPBpgWexA(this, arg3, arg4));
    }

    public void putChannelViews(SparseArray arg3, boolean arg4) {
        if(MessagesStorage.isEmpty(arg3)) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$c3Fi-zRSwutpwhqN7B95bo5f4HI(this, arg3, arg4));
    }

    private void putChatsInternal(ArrayList arg11) {
        Chat v3_1;
        Chat v7;
        if(arg11 != null && !arg11.isEmpty()) {
            SQLitePreparedStatement v0 = this.database.a("REPLACE INTO chats VALUES(?, ?, ?)");
            int v2;
            for(v2 = 0; v2 < arg11.size(); ++v2) {
                Object v3 = arg11.get(v2);
                if(((Chat)v3).min) {
                    SQLiteCursor v4 = this.database.b(String.format(Locale.US, "SELECT data FROM chats WHERE uid = %d", Integer.valueOf(((Chat)v3).id)), new Object[0]);
                    if(v4.a()) {
                        try {
                            NativeByteBuffer v6_1 = v4.g(0);
                            if(v6_1 == null) {
                                goto label_62;
                            }

                            v7 = Chat.TLdeserialize(((AbstractSerializedData)v6_1), v6_1.readInt32(false), false);
                            v6_1.reuse();
                            if(v7 == null) {
                                goto label_62;
                            }

                            v7.title = ((Chat)v3).title;
                            v7.photo = ((Chat)v3).photo;
                            v7.broadcast = ((Chat)v3).broadcast;
                            v7.verified = ((Chat)v3).verified;
                            v7.megagroup = ((Chat)v3).megagroup;
                            v7.democracy = ((Chat)v3).democracy;
                            if(((Chat)v3).username != null) {
                                v7.username = ((Chat)v3).username;
                                v7.flags |= 64;
                            }
                            else {
                                v7.username = null;
                                v7.flags &= -65;
                            }
                        }
                        catch(Exception v6) {
                            FileLog.e(((Throwable)v6));
                            goto label_62;
                        }

                        v3_1 = v7;
                    }

                label_62:
                    v4.b();
                }

                v0.d();
                NativeByteBuffer v4_1 = new NativeByteBuffer(v3_1.getObjectSize());
                v3_1.serializeToStream(((AbstractSerializedData)v4_1));
                v0.a(1, v3_1.id);
                int v6_2 = 2;
                String v3_2 = v3_1.title != null ? v3_1.title.toLowerCase() : "";
                v0.a(v6_2, v3_2);
                v0.a(3, v4_1);
                v0.b();
                v4_1.reuse();
            }

            v0.e();
        }
    }

    public void putContacts(ArrayList arg3, boolean arg4) {
        if((arg3.isEmpty()) && !arg4) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$ZqYAZfhXzKliAp4oMeYGPdUI07E(this, arg4, new ArrayList(((Collection)arg3))));
    }

    public void putDialogPhotos(int arg3, photos_Photos arg4) {
        if(arg4 != null) {
            if(arg4.photos.isEmpty()) {
            }
            else {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$SOag2s8e-ohyNNQp418oPEBvh-M(this, arg3, arg4));
            }
        }
    }

    public void putDialogs(messages_Dialogs arg3, int arg4) {
        if(arg3.dialogs.isEmpty()) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$UwHOnb6mP6DHZGMQhWUnN2Z1QaA(this, arg3, arg4));
    }

    private void putDialogsInternal(messages_Dialogs arg24, int arg25) {
        int v21;
        SQLitePreparedStatement v19;
        LongSparseArray v20;
        SQLiteCursor v4_1;
        int v13;
        MessagesStorage v1 = this;
        messages_Dialogs v0 = arg24;
        int v2 = arg25;
        try {
            v1.database.d();
            LongSparseArray v3 = new LongSparseArray(v0.messages.size());
            int v5;
            for(v5 = 0; v5 < v0.messages.size(); ++v5) {
                Object v6 = v0.messages.get(v5);
                v3.put(MessageObject.getDialogId(((Message)v6)), v6);
            }

            if(!v0.dialogs.isEmpty()) {
                SQLitePreparedStatement v5_1 = v1.database.a("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
                SQLitePreparedStatement v6_1 = v1.database.a("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                SQLitePreparedStatement v7 = v1.database.a("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
                SQLitePreparedStatement v8 = v1.database.a("REPLACE INTO dialog_settings VALUES(?, ?)");
                SQLitePreparedStatement v9 = v1.database.a("REPLACE INTO messages_holes VALUES(?, ?, ?)");
                SQLitePreparedStatement v10 = v1.database.a("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
                int v11 = 0;
                while(v11 < v0.dialogs.size()) {
                    Object v12 = v0.dialogs.get(v11);
                    SQLitePreparedStatement v16 = v5_1;
                    if(((TL_dialog)v12).id == 0) {
                        if(((TL_dialog)v12).peer.user_id != 0) {
                            ((TL_dialog)v12).id = ((long)((TL_dialog)v12).peer.user_id);
                        }
                        else {
                            v13 = ((TL_dialog)v12).peer.chat_id != 0 ? ((TL_dialog)v12).peer.chat_id : ((TL_dialog)v12).peer.channel_id;
                            ((TL_dialog)v12).id = ((long)(-v13));
                        }
                    }

                    if(v2 == 1) {
                        SQLiteDatabase v15 = v1.database;
                        StringBuilder v4 = new StringBuilder();
                        v4.append("SELECT did FROM dialogs WHERE did = ");
                        v4.append(((TL_dialog)v12).id);
                        v4_1 = v15.b(v4.toString(), new Object[0]);
                        boolean v13_1 = v4_1.a();
                        v4_1.b();
                        if(v13_1) {
                            v20 = v3;
                            v5_1 = v6_1;
                            v6_1 = v16;
                        }
                        else {
                            goto label_111;
                        }
                    }
                    else if(((TL_dialog)v12).pinned) {
                        if(v2 == 2) {
                            SQLiteDatabase v4_2 = v1.database;
                            StringBuilder v13_2 = new StringBuilder();
                            v13_2.append("SELECT pinned FROM dialogs WHERE did = ");
                            v13_2.append(((TL_dialog)v12).id);
                            v4_1 = v4_2.b(v13_2.toString(), new Object[0]);
                            if(v4_1.a()) {
                                ((TL_dialog)v12).pinnedNum = v4_1.b(0);
                            }

                            v4_1.b();
                        }
                        else {
                        }

                        goto label_111;
                    }
                    else {
                    label_111:
                        Object v4_3 = v3.get(((TL_dialog)v12).id);
                        int v17 = 32;
                        if(v4_3 != null) {
                            v13 = Math.max(((Message)v4_3).date, 0);
                            if(v1.isValidKeyboardToSave(((Message)v4_3))) {
                                v19 = v6_1;
                                DataQuery.getInstance(v1.currentAccount).putBotKeyboard(((TL_dialog)v12).id, ((Message)v4_3));
                            }
                            else {
                                v19 = v6_1;
                            }

                            v1.fixUnsupportedMedia(((Message)v4_3));
                            NativeByteBuffer v5_2 = new NativeByteBuffer(((Message)v4_3).getObjectSize());
                            ((Message)v4_3).serializeToStream(((AbstractSerializedData)v5_2));
                            v20 = v3;
                            long v2_1 = ((long)((Message)v4_3).id);
                            if(((Message)v4_3).to_id.channel_id != 0) {
                                v21 = v13;
                                v2_1 |= (((long)((Message)v4_3).to_id.channel_id)) << v17;
                            }
                            else {
                                v21 = v13;
                            }

                            v16.d();
                            v6_1 = v16;
                            v6_1.a(1, v2_1);
                            v6_1.a(2, ((TL_dialog)v12).id);
                            v6_1.a(3, MessageObject.getUnreadFlags(((Message)v4_3)));
                            v6_1.a(4, ((Message)v4_3).send_state);
                            v6_1.a(5, ((Message)v4_3).date);
                            v6_1.a(6, v5_2);
                            v6_1.a(7, MessageObject.isOut(((Message)v4_3)));
                            v6_1.a(8, 0);
                            v13 = (((Message)v4_3).flags & 1024) != 0 ? ((Message)v4_3).views : 0;
                            v6_1.a(9, v13);
                            v6_1.a(10, 0);
                            v6_1.a(11, ((Message)v4_3).mentioned);
                            v6_1.b();
                            if(DataQuery.canAddMessageToMedia(((Message)v4_3))) {
                                v7.d();
                                v7.a(1, v2_1);
                                v7.a(2, ((TL_dialog)v12).id);
                                v7.a(3, ((Message)v4_3).date);
                                v7.a(4, DataQuery.getMediaType(((Message)v4_3)));
                                v7.a(5, v5_2);
                                v7.b();
                            }

                            v5_2.reuse();
                            MessagesStorage.createFirstHoles(((TL_dialog)v12).id, v9, v10, ((Message)v4_3).id);
                            v2 = v21;
                        }
                        else {
                            v20 = v3;
                            v19 = v6_1;
                            v6_1 = v16;
                            v2 = 0;
                        }

                        long v3_1 = ((long)((TL_dialog)v12).top_message);
                        if(((TL_dialog)v12).peer.channel_id != 0) {
                            v3_1 |= (((long)((TL_dialog)v12).peer.channel_id)) << v17;
                        }

                        v19.d();
                        v5_1 = v19;
                        v5_1.a(1, ((TL_dialog)v12).id);
                        v5_1.a(2, v2);
                        v5_1.a(3, ((TL_dialog)v12).unread_count);
                        v5_1.a(4, v3_1);
                        v5_1.a(5, ((TL_dialog)v12).read_inbox_max_id);
                        v5_1.a(6, ((TL_dialog)v12).read_outbox_max_id);
                        v5_1.a(7, 0);
                        v5_1.a(8, ((TL_dialog)v12).unread_mentions_count);
                        v5_1.a(9, ((TL_dialog)v12).pts);
                        v5_1.a(10, 0);
                        v5_1.a(11, ((TL_dialog)v12).pinnedNum);
                        v2 = ((TL_dialog)v12).unread_mark ? 1 : 0;
                        v5_1.a(12, v2);
                        v5_1.b();
                        if(((TL_dialog)v12).notify_settings == null) {
                            goto label_278;
                        }

                        v8.d();
                        int v4_4 = 1;
                        v8.a(1, ((TL_dialog)v12).id);
                        if(((TL_dialog)v12).notify_settings.mute_until != 0) {
                            v2 = 2;
                        }
                        else {
                            v2 = 2;
                            v4_4 = 0;
                        }

                        v8.a(v2, v4_4);
                        v8.b();
                    }

                label_278:
                    ++v11;
                    v3 = v20;
                    v2 = arg25;
                    SQLitePreparedStatement v22 = v6_1;
                    v6_1 = v5_1;
                    v5_1 = v22;
                }

                v5_1.e();
                v6_1.e();
                v7.e();
                v8.e();
                v9.e();
                v10.e();
            }

            v1.putUsersInternal(v0.users);
            v1.putChatsInternal(v0.chats);
            v1.database.e();
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public void putEncryptedChat(EncryptedChat arg3, User arg4, TL_dialog arg5) {
        if(arg3 == null) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$D_LCRc9ideRz6Rnd9h5kUH3RXoA(this, arg3, arg4, arg5));
    }

    public void putMessages(ArrayList arg8, boolean arg9, boolean arg10, boolean arg11, int arg12) {
        this.putMessages(arg8, arg9, arg10, arg11, arg12, false);
    }

    public void putMessages(ArrayList arg9, boolean arg10, boolean arg11, boolean arg12, int arg13, boolean arg14) {
        if(arg9.size() == 0) {
            return;
        }

        if(arg11) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$ki8OvP9IeDCrFOepEmtfxaF0Qcs(this, arg9, arg10, arg12, arg13, arg14));
        }
        else {
            this.putMessagesInternal(arg9, arg10, arg12, arg13, arg14);
        }
    }

    public void putMessages(messages_Messages arg12, long arg13, int arg15, int arg16, boolean arg17) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$b8oQU-o7VUOqfIQLniQnaqyw3vM(this, arg12, arg15, arg13, arg16, arg17));
    }

    private void putMessagesInternal(ArrayList arg45, boolean arg46, boolean arg47, int arg48, boolean arg49) {
        Exception v2_9;
        long v10_4;
        int v41;
        Integer v19_2;
        int v40;
        int v1_1;
        int v11_1;
        int v13_5;
        int v23_1;
        int v15_2;
        int v12_2;
        SQLiteCursor v10_3;
        Object v4_5;
        LongSparseArray v42;
        LongSparseArray v37;
        LongSparseArray v43;
        int v38;
        int v39;
        long v6_5;
        SQLitePreparedStatement v13_4;
        TL_messageMediaDocument v13_2;
        SQLitePreparedStatement v2_8;
        SQLitePreparedStatement v36;
        SparseArray v35;
        int v6_4;
        int v14_3;
        LongSparseArray v34;
        int v3_5;
        SQLiteCursor v3_4;
        int v5_3;
        Object v14_2;
        Integer v14_1;
        Object v6_2;
        SparseArray v2_7;
        int v4_1;
        LongSparseArray v33;
        LongSparseArray v32;
        StringBuilder v2_6;
        SQLiteCursor v2_4;
        int v30;
        LongSparseArray v31;
        SQLitePreparedStatement v29;
        StringBuilder v3_1;
        SQLiteDatabase v2_3;
        Object v2_2;
        int v2_1;
        LongSparseArray v27;
        long v8_3;
        LongSparseArray v26;
        LongSparseArray v25;
        Object v5_2;
        int v24;
        int v7;
        StringBuilder v12;
        LongSparseArray v11;
        LongSparseArray v10_1;
        LongSparseArray v8_2;
        int v9;
        MessagesStorage v1 = this;
        ArrayList v2 = arg45;
        long v5 = 0;
        if(arg49) {
            try {
                Object v8 = v2.get(0);
                if(((Message)v8).dialog_id == v5) {
                    if(((Message)v8).to_id.user_id != 0) {
                        ((Message)v8).dialog_id = ((long)((Message)v8).to_id.user_id);
                    }
                    else {
                        v9 = ((Message)v8).to_id.chat_id != 0 ? ((Message)v8).to_id.chat_id : ((Message)v8).to_id.channel_id;
                        ((Message)v8).dialog_id = ((long)(-v9));
                    }
                }

                SQLiteDatabase v9_1 = v1.database;
                StringBuilder v10 = new StringBuilder();
                v10.append("SELECT last_mid FROM dialogs WHERE did = ");
                v10.append(((Message)v8).dialog_id);
                SQLiteCursor v8_1 = v9_1.b(v10.toString(), new Object[0]);
                v9 = v8_1.a() ? v8_1.b(0) : -1;
                v8_1.b();
                if(v9 != 0) {
                    return;
                }

            label_49:
                if(arg46) {
                    v1.database.d();
                }

                v8_2 = new LongSparseArray();
                LongSparseArray v9_2 = new LongSparseArray();
                v10_1 = new LongSparseArray();
                v11 = new LongSparseArray();
                v12 = new StringBuilder();
                LongSparseArray v13 = new LongSparseArray();
                LongSparseArray v14 = new LongSparseArray();
                LongSparseArray v15 = new LongSparseArray();
                SQLitePreparedStatement v4 = v1.database.a("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
                SQLitePreparedStatement v5_1 = v1.database.a("REPLACE INTO randoms VALUES(?, ?)");
                SQLitePreparedStatement v6 = v1.database.a("REPLACE INTO download_queue VALUES(?, ?, ?, ?)");
                SQLitePreparedStatement v22 = v1.database.a("REPLACE INTO webpage_pending VALUES(?, ?)");
                SQLitePreparedStatement v23 = v5_1;
                SQLitePreparedStatement v21 = v6;
                LongSparseArray v3 = null;
                LongSparseArray v6_1 = null;
                v7 = 0;
                StringBuilder v20 = null;
                while(true) {
                    v24 = 32;
                    if(v7 >= arg45.size()) {
                        break;
                    }

                    v5_2 = v2.get(v7);
                    v25 = v8_2;
                    v26 = v9_2;
                    v8_3 = ((long)((Message)v5_2).id);
                    v27 = v3;
                    if(((Message)v5_2).dialog_id == 0) {
                        if(((Message)v5_2).to_id.user_id != 0) {
                            ((Message)v5_2).dialog_id = ((long)((Message)v5_2).to_id.user_id);
                        }
                        else {
                            v2_1 = ((Message)v5_2).to_id.chat_id != 0 ? ((Message)v5_2).to_id.chat_id : ((Message)v5_2).to_id.channel_id;
                            ((Message)v5_2).dialog_id = ((long)(-v2_1));
                        }
                    }

                    if(((Message)v5_2).to_id.channel_id != 0) {
                        v8_3 |= (((long)((Message)v5_2).to_id.channel_id)) << v24;
                    }

                    if((((Message)v5_2).mentioned) && (((Message)v5_2).media_unread)) {
                        v15.put(v8_3, Long.valueOf(((Message)v5_2).dialog_id));
                    }

                    if(((((Message)v5_2).action instanceof TL_messageActionHistoryClear)) || (MessageObject.isOut(((Message)v5_2)))) {
                    label_188:
                        v29 = v4;
                        v31 = v6_1;
                        v30 = v7;
                    }
                    else {
                        if(((Message)v5_2).id <= 0 && !MessageObject.isUnread(((Message)v5_2))) {
                            goto label_188;
                        }

                        v2_2 = v13.get(((Message)v5_2).dialog_id);
                        if(v2_2 == null) {
                            v2_3 = v1.database;
                            v3_1 = new StringBuilder();
                            v29 = v4;
                            v3_1.append("SELECT inbox_max FROM dialogs WHERE did = ");
                            v31 = v6_1;
                            v30 = v7;
                            v3_1.append(((Message)v5_2).dialog_id);
                            v2_4 = v2_3.b(v3_1.toString(), new Object[0]);
                            Integer v3_2 = v2_4.a() ? Integer.valueOf(v2_4.b(0)) : Integer.valueOf(0);
                            v2_4.b();
                            v13.put(((Message)v5_2).dialog_id, v3_2);
                            Integer v2_5 = v3_2;
                        }
                        else {
                            v29 = v4;
                            v31 = v6_1;
                            v30 = v7;
                        }

                        if(((Message)v5_2).id >= 0 && ((Integer)v2_2).intValue() >= ((Message)v5_2).id) {
                            goto label_191;
                        }

                        if(v12.length() > 0) {
                            v12.append(",");
                        }

                        v12.append(v8_3);
                        v14.put(v8_3, Long.valueOf(((Message)v5_2).dialog_id));
                    }

                label_191:
                    if(DataQuery.canAddMessageToMedia(((Message)v5_2))) {
                        if(v20 == null) {
                            v20 = new StringBuilder();
                            v6_1 = new LongSparseArray();
                            v3 = new LongSparseArray();
                            v2_6 = v20;
                        }
                        else {
                            v2_6 = v20;
                            v3 = v27;
                            v6_1 = v31;
                        }

                        if(v2_6.length() > 0) {
                            v2_6.append(",");
                        }

                        v2_6.append(v8_3);
                        v32 = v13;
                        v33 = v14;
                        v6_1.put(v8_3, Long.valueOf(((Message)v5_2).dialog_id));
                        v3.put(v8_3, Integer.valueOf(DataQuery.getMediaType(((Message)v5_2))));
                        v20 = v2_6;
                    }
                    else {
                        v32 = v13;
                        v33 = v14;
                        v3 = v27;
                        v6_1 = v31;
                    }

                    if(v1.isValidKeyboardToSave(((Message)v5_2))) {
                        v2_2 = v11.get(((Message)v5_2).dialog_id);
                        if(v2_2 != null && ((Message)v2_2).id >= ((Message)v5_2).id) {
                            goto label_234;
                        }

                        v11.put(((Message)v5_2).dialog_id, v5_2);
                    }

                label_234:
                    v7 = v30 + 1;
                    v8_2 = v25;
                    v9_2 = v26;
                    v4 = v29;
                    v13 = v32;
                    v14 = v33;
                    v2 = arg45;
                }

                v27 = v3;
                v29 = v4;
                v31 = v6_1;
                v25 = v8_2;
                v26 = v9_2;
                v33 = v14;
                for(v2_1 = 0; v2_1 < v11.size(); ++v2_1) {
                    DataQuery.getInstance(v1.currentAccount).putBotKeyboard(v11.keyAt(v2_1), v11.valueAt(v2_1));
                }

                if(v20 != null) {
                    v2_3 = v1.database;
                    v3_1 = new StringBuilder();
                    v3_1.append("SELECT mid FROM media_v2 WHERE mid IN(");
                    v3_1.append(v20.toString());
                    v3_1.append(")");
                    v4_1 = 0;
                    v2_4 = v2_3.b(v3_1.toString(), new Object[0]);
                    goto label_273;
                }
                else {
                    v2_7 = null;
                    goto label_316;
                label_273:
                    while(v2_4.a()) {
                        v31.remove(v2_4.d(v4_1));
                        v31 = v31;
                        v4_1 = 0;
                    }

                    v3 = v31;
                    v2_4.b();
                    v2_7 = new SparseArray();
                    v4_1 = 0;
                    while(v4_1 < v3.size()) {
                        v5 = v3.keyAt(v4_1);
                        v8_3 = v3.valueAt(v4_1).longValue();
                        v11 = v27;
                        v5_2 = v11.get(v5);
                        v6_2 = v2_7.get(((Integer)v5_2).intValue());
                        if(v6_2 == null) {
                            v6_1 = new LongSparseArray();
                            v14_1 = Integer.valueOf(0);
                            v2_7.put(((Integer)v5_2).intValue(), v6_1);
                        }
                        else {
                            v14_2 = ((LongSparseArray)v6_2).get(v8_3);
                        }

                        if(v14_1 == null) {
                            v14_1 = Integer.valueOf(0);
                        }

                        v6_1.put(v8_3, Integer.valueOf(v14_1.intValue() + 1));
                        ++v4_1;
                        v27 = v11;
                    }
                }

            label_316:
                if(v12.length() > 0) {
                    SQLiteDatabase v3_3 = v1.database;
                    StringBuilder v4_2 = new StringBuilder();
                    v4_2.append("SELECT mid FROM messages WHERE mid IN(");
                    v4_2.append(v12.toString());
                    v4_2.append(")");
                    v5_3 = 0;
                    v3_4 = v3_3.b(v4_2.toString(), new Object[0]);
                    goto label_331;
                }
                else {
                    v8_2 = v26;
                    goto label_376;
                label_331:
                    while(v3_4.a()) {
                        v8_3 = v3_4.d(v5_3);
                        v33.remove(v8_3);
                        v15.remove(v8_3);
                        v33 = v33;
                        v5_3 = 0;
                    }

                    LongSparseArray v4_3 = v33;
                    v3_4.b();
                    v3_5 = 0;
                    while(v3_5 < v4_3.size()) {
                        v5 = v4_3.valueAt(v3_5).longValue();
                        v8_2 = v26;
                        Object v9_3 = v8_2.get(v5);
                        if(v9_3 == null) {
                            Integer v9_4 = Integer.valueOf(0);
                        }

                        v8_2.put(v5, Integer.valueOf(((Integer)v9_3).intValue() + 1));
                        ++v3_5;
                        v26 = v8_2;
                    }

                    v8_2 = v26;
                    for(v3_5 = 0; v3_5 < v15.size(); ++v3_5) {
                        long v4_4 = v15.valueAt(v3_5).longValue();
                        v6_2 = v10_1.get(v4_4);
                        if(v6_2 == null) {
                            Integer v6_3 = Integer.valueOf(0);
                        }

                        v10_1.put(v4_4, Integer.valueOf(((Integer)v6_2).intValue() + 1));
                    }
                }

            label_376:
                v3_5 = 0;
                v4 = null;
                v5_3 = 0;
                while(v3_5 < arg45.size()) {
                    Object v12_1 = arg45.get(v3_5);
                    v1.fixUnsupportedMedia(((Message)v12_1));
                    v29.d();
                    v34 = v10_1;
                    long v9_5 = ((long)((Message)v12_1).id);
                    if(((Message)v12_1).local_id != 0) {
                        v9_5 = ((long)((Message)v12_1).local_id);
                    }

                    if(((Message)v12_1).to_id.channel_id != 0) {
                        v9_5 |= (((long)((Message)v12_1).to_id.channel_id)) << v24;
                    }

                    NativeByteBuffer v13_1 = new NativeByteBuffer(((Message)v12_1).getObjectSize());
                    ((Message)v12_1).serializeToStream(((AbstractSerializedData)v13_1));
                    v14_3 = ((Message)v12_1).action == null || !(((Message)v12_1).action instanceof TL_messageEncryptedAction) || ((((Message)v12_1).action.encryptedAction instanceof TL_decryptedMessageActionSetMessageTTL)) || ((((Message)v12_1).action.encryptedAction instanceof TL_decryptedMessageActionScreenshotMessages)) ? 1 : 0;
                    if(v14_3 != 0) {
                        v11 = v25;
                        v14_2 = v11.get(((Message)v12_1).dialog_id);
                        if(v14_2 != null && ((Message)v12_1).date <= ((Message)v14_2).date && (((Message)v12_1).id <= 0 || ((Message)v14_2).id <= 0 || ((Message)v12_1).id <= ((Message)v14_2).id)) {
                            if(((Message)v12_1).id >= 0) {
                                goto label_447;
                            }

                            if(((Message)v14_2).id >= 0) {
                                goto label_447;
                            }

                            if(((Message)v12_1).id >= ((Message)v14_2).id) {
                                goto label_447;
                            }
                        }

                        v11.put(((Message)v12_1).dialog_id, v12_1);
                    }
                    else {
                        v11 = v25;
                    }

                label_447:
                    SQLitePreparedStatement v7_1 = v29;
                    v7_1.a(1, v9_5);
                    v7_1.a(2, ((Message)v12_1).dialog_id);
                    v7_1.a(3, MessageObject.getUnreadFlags(((Message)v12_1)));
                    v7_1.a(4, ((Message)v12_1).send_state);
                    v7_1.a(5, ((Message)v12_1).date);
                    v7_1.a(6, v13_1);
                    v7_1.a(7, MessageObject.isOut(((Message)v12_1)));
                    v7_1.a(8, ((Message)v12_1).ttl);
                    if((((Message)v12_1).flags & 1024) != 0) {
                        v6_4 = ((Message)v12_1).views;
                        v14_3 = 9;
                    }
                    else {
                        v14_3 = 9;
                        v6_4 = v1.getMessageMediaType(((Message)v12_1));
                    }

                    v7_1.a(v14_3, v6_4);
                    v7_1.a(10, 0);
                    v7_1.a(11, ((Message)v12_1).mentioned);
                    v7_1.b();
                    if(((Message)v12_1).random_id != 0) {
                        v23.d();
                        v35 = v2_7;
                        v6 = v23;
                        v6.a(1, ((Message)v12_1).random_id);
                        v6.a(2, v9_5);
                        v6.b();
                    }
                    else {
                        v35 = v2_7;
                        v6 = v23;
                    }

                    if(DataQuery.canAddMessageToMedia(((Message)v12_1))) {
                        if(v4 == null) {
                            v4 = v1.database.a("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
                        }

                        v4.d();
                        v4.a(1, v9_5);
                        v4.a(2, ((Message)v12_1).dialog_id);
                        v4.a(3, ((Message)v12_1).date);
                        v4.a(4, DataQuery.getMediaType(((Message)v12_1)));
                        v4.a(5, v13_1);
                        v4.b();
                    }

                    if((((Message)v12_1).media instanceof TL_messageMediaWebPage)) {
                        v22.d();
                        v36 = v4;
                        v2_8 = v22;
                        v2_8.a(1, ((Message)v12_1).media.webpage.id);
                        v2_8.a(2, v9_5);
                        v2_8.b();
                    }
                    else {
                        v36 = v4;
                        v2_8 = v22;
                    }

                    v13_1.reuse();
                    if(arg48 != 0) {
                        if(((Message)v12_1).to_id.channel_id != 0 && !((Message)v12_1).post) {
                            goto label_692;
                        }

                        if(((Message)v12_1).date < ConnectionsManager.getInstance(v1.currentAccount).getCurrentTime() - 3600) {
                            goto label_692;
                        }

                        if(!DownloadController.getInstance(v1.currentAccount).canDownloadMedia(((Message)v12_1))) {
                            goto label_692;
                        }

                        if(!(((Message)v12_1).media instanceof TL_messageMediaPhoto) && !(((Message)v12_1).media instanceof TL_messageMediaDocument)) {
                            goto label_692;
                        }

                        if(MessageObject.isVoiceMessage(((Message)v12_1))) {
                            v9_5 = ((Message)v12_1).media.document.id;
                            v13_2 = new TL_messageMediaDocument();
                            ((MessageMedia)v13_2).document = ((Message)v12_1).media.document;
                            ((MessageMedia)v13_1).flags |= 1;
                            v14_3 = 2;
                        }
                        else if(MessageObject.isRoundVideoMessage(((Message)v12_1))) {
                            v9_5 = ((Message)v12_1).media.document.id;
                            TL_messageMediaDocument v14_4 = new TL_messageMediaDocument();
                            ((MessageMedia)v14_4).document = ((Message)v12_1).media.document;
                            ((MessageMedia)v14_3).flags |= 1;
                            v13_2 = v14_4;
                            v14_3 = 64;
                        }
                        else {
                            if((((Message)v12_1).media instanceof TL_messageMediaPhoto)) {
                                if(FileLoader.getClosestPhotoSizeWithSize(((Message)v12_1).media.photo.sizes, AndroidUtilities.getPhotoSize()) != null) {
                                    v9_5 = ((Message)v12_1).media.photo.id;
                                    TL_messageMediaPhoto v13_3 = new TL_messageMediaPhoto();
                                    ((MessageMedia)v13_3).photo = ((Message)v12_1).media.photo;
                                    ((MessageMedia)v13_1).flags |= 1;
                                    v14_3 = 1;
                                    goto label_662;
                                }
                            }
                            else if(MessageObject.isVideoMessage(((Message)v12_1))) {
                                v9_5 = ((Message)v12_1).media.document.id;
                                v13_2 = new TL_messageMediaDocument();
                                ((MessageMedia)v13_2).document = ((Message)v12_1).media.document;
                                ((MessageMedia)v13_1).flags |= 1;
                                v14_3 = 4;
                                goto label_662;
                            }
                            else if(((((Message)v12_1).media instanceof TL_messageMediaDocument)) && !MessageObject.isMusicMessage(((Message)v12_1)) && !MessageObject.isGifDocument(((Message)v12_1).media.document)) {
                                v9_5 = ((Message)v12_1).media.document.id;
                                v13_2 = new TL_messageMediaDocument();
                                ((MessageMedia)v13_2).document = ((Message)v12_1).media.document;
                                ((MessageMedia)v13_1).flags |= 1;
                                v14_3 = 8;
                                goto label_662;
                            }

                            v9_5 = 0;
                            v13_2 = null;
                            v14_3 = 0;
                        }

                    label_662:
                        if(v13_2 == null) {
                            goto label_692;
                        }

                        if(((Message)v12_1).media.ttl_seconds != 0) {
                            ((MessageMedia)v13_2).ttl_seconds = ((Message)v12_1).media.ttl_seconds;
                            ((MessageMedia)v13_2).flags |= 4;
                        }

                        v5_3 |= v14_3;
                        v21.d();
                        NativeByteBuffer v15_1 = new NativeByteBuffer(((MessageMedia)v13_2).getObjectSize());
                        ((MessageMedia)v13_2).serializeToStream(((AbstractSerializedData)v15_1));
                        v13_4 = v21;
                        v13_4.a(1, v9_5);
                        v13_4.a(2, v14_3);
                        v13_4.a(3, ((Message)v12_1).date);
                        v13_4.a(4, v15_1);
                        v13_4.b();
                        v15_1.reuse();
                    }
                    else {
                    label_692:
                        v13_4 = v21;
                    }

                    ++v3_5;
                    v22 = v2_8;
                    v23 = v6;
                    v29 = v7_1;
                    v25 = v11;
                    v21 = v13_4;
                    v10_1 = v34;
                    v2_7 = v35;
                    v4 = v36;
                }

                v35 = v2_7;
                v34 = v10_1;
                v13_4 = v21;
                v2_8 = v22;
                v6 = v23;
                v11 = v25;
                v29.e();
                if(v4 != null) {
                    v4.e();
                }

                v6.e();
                v13_4.e();
                v2_8.e();
                v2_8 = v1.database.a("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                v3_5 = 0;
                while(true) {
                label_721:
                    if(v3_5 >= v11.size()) {
                        goto label_902;
                    }

                    goto label_723;
                }
            }
            catch(Exception v0) {
                goto label_47;
            }
        }

        goto label_49;
        try {
        label_723:
            v6_5 = v11.keyAt(v3_5);
            if(v6_5 == 0) {
                v39 = v3_5;
                v38 = v5_3;
                v43 = v8_2;
                v37 = v11;
                v42 = v34;
            }
            else {
                v4_5 = v11.valueAt(v3_5);
                if(v4_5 != null) {
                    goto label_742;
                }
                else {
                    goto label_745;
                }
            }

            goto label_890;
        }
        catch(Exception v0) {
            goto label_899;
        }

        try {
        label_742:
            v9 = ((Message)v4_5).to_id.channel_id;
            goto label_746;
        }
        catch(Exception v0) {
            goto label_47;
        }

    label_745:
        v9 = 0;
        try {
        label_746:
            SQLiteDatabase v10_2 = v1.database;
            v12 = new StringBuilder();
            v12.append("SELECT date, unread_count, pts, last_mid, inbox_max, outbox_max, pinned, unread_count_i, flags FROM dialogs WHERE did = ");
            v12.append(v6_5);
            v10_3 = v10_2.b(v12.toString(), new Object[0]);
            v12_2 = v9 != 0 ? 1 : 0;
            if(v10_3.a()) {
            }
            else {
                goto label_790;
            }
        }
        catch(Exception v0) {
            goto label_899;
        }

        try {
            v12_2 = v10_3.b(0);
            v14_3 = v10_3.b(1);
            v15_2 = v10_3.b(2);
            int v19 = v10_3.b(3);
            int v20_1 = v10_3.b(4);
            int v21_1 = v10_3.b(5);
            int v22_1 = v10_3.b(6);
            v23_1 = v10_3.b(7);
            v39 = v3_5;
            v38 = v5_3;
            v37 = v11;
            v13_5 = v12_2;
            v5_3 = v15_2;
            v12_2 = v19;
            v15_2 = v20_1;
            v11_1 = v21_1;
            v1_1 = v22_1;
            v3_5 = v10_3.b(8);
            goto label_806;
        label_790:
            if(v9 != 0) {
                MessagesController.getInstance(v1.currentAccount).checkChannelInviter(v9);
            }
        }
        catch(Exception v0) {
            goto label_47;
        }

        v39 = v3_5;
        v38 = v5_3;
        v37 = v11;
        v5_3 = v12_2;
        v1_1 = 0;
        v3_5 = 0;
        v11_1 = 0;
        v12_2 = 0;
        v13_5 = 0;
        v14_3 = 0;
        v15_2 = 0;
        v23_1 = 0;
        try {
        label_806:
            v10_3.b();
            v10_1 = v34;
            Object v19_1 = v10_1.get(v6_5);
            Object v20_2 = v8_2.get(v6_5);
            if(v20_2 == null) {
                Integer v20_3 = Integer.valueOf(0);
                v40 = v3_5;
            }
            else {
                v40 = v3_5;
                v8_2.put(v6_5, Integer.valueOf(((Integer)v20_2).intValue() + v14_3));
            }

            if(v19_1 == null) {
                v19_2 = Integer.valueOf(0);
            }
            else {
                v10_1.put(v6_5, Integer.valueOf(((Integer)v19_1).intValue() + v23_1));
            }

            if(v4_5 != null) {
                v42 = v10_1;
                v41 = v11_1;
                v10_4 = ((long)((Message)v4_5).id);
            }
            else {
                v42 = v10_1;
                v41 = v11_1;
                v10_4 = ((long)v12_2);
            }

            if(v4_5 != null && ((Message)v4_5).local_id != 0) {
                v10_4 = ((long)((Message)v4_5).local_id);
            }

            if(v9 != 0) {
                v43 = v8_2;
                v10_4 |= (((long)v9)) << v24;
            }
            else {
                v43 = v8_2;
            }

            v2_8.d();
            v2_8.a(1, v6_5);
            if(v4_5 != null) {
                if((arg47) && v13_5 != 0) {
                    goto label_859;
                }

                v2_8.a(2, ((Message)v4_5).date);
            }
            else {
            label_859:
                v2_8.a(2, v13_5);
            }

            v2_8.a(3, v14_3 + ((Integer)v20_2).intValue());
            v2_8.a(4, v10_4);
            v2_8.a(5, v15_2);
            v2_8.a(6, v41);
            v2_8.a(7, 0);
            v2_8.a(8, v23_1 + v19_2.intValue());
            v2_8.a(9, v5_3);
            v2_8.a(10, 0);
            v2_8.a(11, v1_1);
            v2_8.a(12, v40);
            v2_8.b();
        }
        catch(Exception v0) {
        label_899:
            v2_9 = v0;
            goto label_988;
        }

    label_890:
        v3_5 = v39 + 1;
        v11 = v37;
        v5_3 = v38;
        v34 = v42;
        v8_2 = v43;
        v1 = this;
        goto label_721;
    label_902:
        v38 = v5_3;
        v43 = v8_2;
        v42 = v34;
        try {
            v2_8.e();
            if(v35 == null) {
                goto label_970;
            }
        }
        catch(Exception v0) {
            goto label_47;
        }

        v1 = this;
        try {
            v2_8 = v1.database.a("REPLACE INTO media_counts_v2 VALUES(?, ?, ?)");
            v3_5 = 0;
            goto label_912;
        label_970:
            v1 = this;
            goto label_971;
        label_912:
            while(v3_5 < v35.size()) {
                SparseArray v4_6 = v35;
                v5_3 = v4_6.keyAt(v3_5);
                v6_2 = v4_6.valueAt(v3_5);
                for(v7 = 0; v7 < ((LongSparseArray)v6_2).size(); ++v7) {
                    v8_3 = ((LongSparseArray)v6_2).keyAt(v7);
                    v10_3 = v1.database.b(String.format(Locale.US, "SELECT count FROM media_counts_v2 WHERE uid = %d AND type = %d LIMIT 1", Long.valueOf(v8_3), Integer.valueOf(v5_3)), new Object[0]);
                    v11_1 = v10_3.a() ? v10_3.b(0) : -1;
                    v10_3.b();
                    if(v11_1 != -1) {
                        v2_8.d();
                        v11_1 += ((LongSparseArray)v6_2).valueAt(v7).intValue();
                        v2_8.a(1, v8_3);
                        v2_8.a(2, v5_3);
                        v2_8.a(3, v11_1);
                        v2_8.b();
                    }
                    else {
                    }
                }

                ++v3_5;
                v35 = v4_6;
            }

            v2_8.e();
        label_971:
            if(arg46) {
                v1.database.e();
            }

            MessagesController.getInstance(v1.currentAccount).processDialogsUpdateRead(v43, v42);
            if(v38 == 0) {
                return;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$MessagesStorage$zP56CpDF-vXgtmw04fHGkBy1DsQ(v1, v38));
            return;
        }
        catch(Exception v0) {
            goto label_47;
        }

    label_988:
        FileLog.e(((Throwable)v2_9));
        return;
    label_47:
        v2_9 = v0;
        goto label_988;
    }

    public void putSentFile(String arg3, TLObject arg4, int arg5) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$ACmHdCXd3zrRyMkzN6-V7vzYzkc(this, arg3, arg4, arg5));
            }
        }
    }

    public void putUsersAndChats(ArrayList arg2, ArrayList arg3, boolean arg4, boolean arg5) {
        if(arg2 != null && (arg2.isEmpty()) && arg3 != null && (arg3.isEmpty())) {
            return;
        }

        if(arg5) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$gDX_aZdSiIbibtSUzmbYiRYxT2A(this, arg2, arg3, arg4));
        }
        else {
            this.putUsersAndChatsInternal(arg2, arg3, arg4);
        }
    }

    private void putUsersAndChatsInternal(ArrayList arg2, ArrayList arg3, boolean arg4) {
        if(arg4) {
            try {
                this.database.d();
            label_6:
                this.putUsersInternal(arg2);
                this.putChatsInternal(arg3);
                if(!arg4) {
                    return;
                }

                this.database.e();
                return;
            label_5:
                goto label_12;
            }
            catch(Exception v2) {
                goto label_5;
            }
        }

        goto label_6;
    label_12:
        FileLog.e(((Throwable)v2));
    }

    private void putUsersInternal(ArrayList arg11) {
        int v7_1;
        UserStatus v5;
        User v7;
        if(arg11 != null && !arg11.isEmpty()) {
            SQLitePreparedStatement v0 = this.database.a("REPLACE INTO users VALUES(?, ?, ?, ?)");
            int v2;
            for(v2 = 0; v2 < arg11.size(); ++v2) {
                Object v3 = arg11.get(v2);
                if(((User)v3).min) {
                    SQLiteCursor v4 = this.database.b(String.format(Locale.US, "SELECT data FROM users WHERE uid = %d", Integer.valueOf(((User)v3).id)), new Object[0]);
                    if(v4.a()) {
                        try {
                            NativeByteBuffer v6_1 = v4.g(0);
                            if(v6_1 == null) {
                                goto label_62;
                            }

                            v7 = User.TLdeserialize(((AbstractSerializedData)v6_1), v6_1.readInt32(false), false);
                            v6_1.reuse();
                            if(v7 == null) {
                                goto label_62;
                            }

                            String v8 = null;
                            if(((User)v3).username != null) {
                                v7.username = ((User)v3).username;
                                v7.flags |= 8;
                            }
                            else {
                                v7.username = v8;
                                v7.flags &= -9;
                            }

                            if(((User)v3).photo != null) {
                                v7.photo = ((User)v3).photo;
                                v7.flags |= 32;
                            }
                            else {
                                v7.photo = ((UserProfilePhoto)v8);
                                v7.flags &= -33;
                            }
                        }
                        catch(Exception v6) {
                            FileLog.e(((Throwable)v6));
                            goto label_62;
                        }

                        User v3_1 = v7;
                    }

                label_62:
                    v4.b();
                }

                v0.d();
                NativeByteBuffer v4_1 = new NativeByteBuffer(((User)v3).getObjectSize());
                ((User)v3).serializeToStream(((AbstractSerializedData)v4_1));
                v0.a(1, ((User)v3).id);
                v0.a(2, this.formatUserSearchName(((User)v3)));
                int v6_2 = 3;
                if(((User)v3).status != null) {
                    if((((User)v3).status instanceof TL_userStatusRecently)) {
                        v5 = ((User)v3).status;
                        v7_1 = -100;
                        goto label_81;
                    }
                    else if((((User)v3).status instanceof TL_userStatusLastWeek)) {
                        v5 = ((User)v3).status;
                        v7_1 = -101;
                        goto label_81;
                    }
                    else if((((User)v3).status instanceof TL_userStatusLastMonth)) {
                        v5 = ((User)v3).status;
                        v7_1 = -102;
                    label_81:
                        v5.expires = v7_1;
                    }

                    v0.a(v6_2, ((User)v3).status.expires);
                }
                else {
                    v0.a(v6_2, 0);
                }

                v0.a(4, v4_1);
                v0.b();
                v4_1.reuse();
            }

            v0.e();
        }
    }

    public void putWallpapers(ArrayList arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$UWo36bxSYsbQlkQh_QOLkIPbPq4(this, arg3));
    }

    public void putWebPages(LongSparseArray arg3) {
        if(MessagesStorage.isEmpty(arg3)) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$bTazTjSuaUXfVsmsSabQZU6GvcA(this, arg3));
    }

    public void putWebRecent(ArrayList arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$oL5twKadpe9UtUnyeA6t0aXhJOI(this, arg3));
    }

    public void removeFromDownloadQueue(long arg9, int arg11, boolean arg12) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$FKwQvrkSkB5mdWhvrErop72UEK4(this, arg12, arg11, arg9));
    }

    public void removePendingTask(long arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$bLsQ9Zez-Ef0tQJtoMR54iM-bHA(this, arg3));
    }

    public void resetDialogs(messages_Dialogs arg16, int arg17, int arg18, int arg19, int arg20, int arg21, LongSparseArray arg22, LongSparseArray arg23, Message arg24, int arg25) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$SwVNl8FTr_qF_rghO9rjK7Rj1-Y(this, arg16, arg25, arg18, arg19, arg20, arg21, arg24, arg17, arg22, arg23));
    }

    public void resetMentionsCount(long arg3, int arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$pZWP-2-UXH03EUq-qNLMkkbQVVo(this, arg5, arg3));
    }

    public void saveBotCache(String arg3, TLObject arg4) {
        if(arg4 != null) {
            if(TextUtils.isEmpty(((CharSequence)arg3))) {
            }
            else {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$AV8JUpwRZ9sYJ8stAHUdk__a2Sc(this, arg4, arg3));
            }
        }
    }

    public void saveChannelPts(int arg3, int arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$pq7E9dtV7G8xJBVMvY0bokiYzf0(this, arg4, arg3));
    }

    public void saveDiffParams(int arg9, int arg10, int arg11, int arg12) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$T8e-tNkuxU6-LHxiGwlWr9RkqZI(this, arg9, arg10, arg11, arg12));
    }

    private void saveDiffParamsInternal(int arg3, int arg4, int arg5, int arg6) {
        try {
            if(this.lastSavedSeq == arg3 && this.lastSavedPts == arg4 && this.lastSavedDate == arg5 && this.lastQtsValue == arg6) {
                return;
            }

            SQLitePreparedStatement v0 = this.database.a("UPDATE params SET seq = ?, pts = ?, date = ?, qts = ? WHERE id = 1");
            v0.a(1, arg3);
            v0.a(2, arg4);
            v0.a(3, arg5);
            v0.a(4, arg6);
            v0.b();
            v0.e();
            this.lastSavedSeq = arg3;
            this.lastSavedPts = arg4;
            this.lastSavedDate = arg5;
            this.lastSavedQts = arg6;
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public void saveSecretParams(int arg3, int arg4, byte[] arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$NtpBYQeXRJpUfal8CNuiE9yNR3Y(this, arg3, arg4, arg5));
    }

    public void setDialogFlags(long arg9, long arg11) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$unh-_m-EHguN6Dj9RlKMss24smc(this, arg9, arg11));
    }

    public void setDialogPinned(long arg3, int arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$7ks0cC3Ml308HXyjulB1Cd3JA4k(this, arg5, arg3));
    }

    public void setDialogUnread(long arg3, boolean arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$aV_vmaGmocUzJMowFb97qB2ZcfM(this, arg3, arg5));
    }

    public void setLastDateValue(int arg1) {
        this.ensureOpened();
        this.lastDateValue = arg1;
    }

    public void setLastPtsValue(int arg1) {
        this.ensureOpened();
        this.lastPtsValue = arg1;
    }

    public void setLastQtsValue(int arg1) {
        this.ensureOpened();
        this.lastQtsValue = arg1;
    }

    public void setLastSecretVersion(int arg1) {
        this.ensureOpened();
        this.lastSecretVersion = arg1;
    }

    public void setLastSeqValue(int arg1) {
        this.ensureOpened();
        this.lastSeqValue = arg1;
    }

    public void setMessageSeq(int arg3, int arg4, int arg5) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$qF2-SihYcDCS5OcLjXqGUbM8fQQ(this, arg3, arg4, arg5));
    }

    public void setSecretG(int arg1) {
        this.ensureOpened();
        this.secretG = arg1;
    }

    public void setSecretPBytes(byte[] arg1) {
        this.ensureOpened();
        this.secretPBytes = arg1;
    }

    public void unpinAllDialogsExceptNew(ArrayList arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$d20O925KVct7cy8xTlpSbuQfBus(this, arg3));
    }

    public void updateChannelPinnedMessage(int arg3, int arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$paK5g9zc_4Y1Wl49U3Rk1WruYP4(this, arg3, arg4));
    }

    public void updateChannelUsers(int arg3, ArrayList arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$SFnkTsVwBPLYg1daXXARc_582Ks(this, arg3, arg4));
    }

    public void updateChatInfo(int arg10, int arg11, int arg12, int arg13, int arg14) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$KR6JXRA8k3MRR1qOaOPiPcPGrig(this, arg10, arg12, arg11, arg13, arg14));
    }

    public void updateChatInfo(ChatFull arg3, boolean arg4) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$cSPrS1LAvOg7iikL5ZXe46Du5GU(this, arg4, arg3));
    }

    public void updateChatParticipants(ChatParticipants arg3) {
        if(arg3 == null) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$mfb9Lk3A8pingtAxnDjjiIRIJys(this, arg3));
    }

    private void updateDbToLastVersion(int arg3) {
        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$UniaOWI4GiF_wLDJANnyTaeI1I0(this, arg3));
    }

    public void updateDialogsWithDeletedMessages(ArrayList arg2, ArrayList arg3, boolean arg4, int arg5) {
        if((arg2.isEmpty()) && arg5 == 0) {
            return;
        }

        if(arg4) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$diOgM3ivUd_j99NEcIOcv2QZWik(this, arg2, arg3, arg5));
        }
        else {
            this.updateDialogsWithDeletedMessagesInternal(arg2, arg3, arg5);
        }
    }

    private void updateDialogsWithDeletedMessagesInternal(ArrayList arg17, ArrayList arg18, int arg19) {
        Integer v5_1;
        String v9;
        SQLiteDatabase v4;
        MessagesStorage v1 = this;
        ArrayList v0 = arg18;
        int v2 = arg19;
        if(Thread.currentThread().getId() == v1.storageQueue.getId()) {
            try {
                ArrayList v3 = new ArrayList();
                int v5 = 3;
                int v6 = 2;
                if(!arg17.isEmpty()) {
                    if(v2 != 0) {
                        v3.add(Long.valueOf(((long)(-v2))));
                        v4 = v1.database;
                        v9 = "UPDATE dialogs SET last_mid = (SELECT mid FROM messages WHERE uid = ? AND date = (SELECT MAX(date) FROM messages WHERE uid = ?)) WHERE did = ?";
                    }
                    else {
                        SQLiteCursor v4_1 = v1.database.b(String.format(Locale.US, "SELECT did FROM dialogs WHERE last_mid IN(%s)", TextUtils.join(",", arg17)), new Object[0]);
                        while(v4_1.a()) {
                            v3.add(Long.valueOf(v4_1.d(0)));
                        }

                        v4_1.b();
                        v4 = v1.database;
                        v9 = "UPDATE dialogs SET last_mid = (SELECT mid FROM messages WHERE uid = ? AND date = (SELECT MAX(date) FROM messages WHERE uid = ? AND date != 0)) WHERE did = ?";
                    }

                    SQLitePreparedStatement v4_2 = v4.a(v9);
                    v1.database.d();
                    int v9_1;
                    for(v9_1 = 0; v9_1 < v3.size(); ++v9_1) {
                        long v10 = v3.get(v9_1).longValue();
                        v4_2.d();
                        v4_2.a(1, v10);
                        v4_2.a(v6, v10);
                        v4_2.a(v5, v10);
                        v4_2.b();
                    }

                    v4_2.e();
                    v1.database.e();
                }
                else {
                    v3.add(Long.valueOf(((long)(-v2))));
                }

                if(v0 != null) {
                    int v4_3;
                    for(v4_3 = 0; v4_3 < arg18.size(); ++v4_3) {
                        Object v9_2 = v0.get(v4_3);
                        if(!v3.contains(v9_2)) {
                            v3.add(v9_2);
                        }
                    }
                }

                String v0_2 = TextUtils.join(",", ((Iterable)v3));
                TL_messages_dialogs v3_1 = new TL_messages_dialogs();
                ArrayList v4_4 = new ArrayList();
                ArrayList v9_3 = new ArrayList();
                ArrayList v10_1 = new ArrayList();
                ArrayList v11 = new ArrayList();
                SQLiteCursor v0_3 = v1.database.b(String.format(Locale.US, "SELECT d.did, d.last_mid, d.unread_count, d.date, m.data, m.read_state, m.mid, m.send_state, m.date, d.pts, d.inbox_max, d.outbox_max, d.pinned, d.unread_count_i, d.flags FROM dialogs as d LEFT JOIN messages as m ON d.last_mid = m.mid WHERE d.did IN(%s)", v0_2), new Object[0]);
                while(v0_3.a()) {
                    TL_dialog v12 = new TL_dialog();
                    v12.id = v0_3.d(0);
                    v12.top_message = v0_3.b(1);
                    v12.read_inbox_max_id = v0_3.b(10);
                    v12.read_outbox_max_id = v0_3.b(11);
                    v12.unread_count = v0_3.b(v6);
                    v12.unread_mentions_count = v0_3.b(13);
                    v12.last_message_date = v0_3.b(v5);
                    v12.pts = v0_3.b(9);
                    int v13 = v2 == 0 ? 0 : 1;
                    v12.flags = v13;
                    v12.pinnedNum = v0_3.b(12);
                    boolean v13_1 = v12.pinnedNum != 0 ? true : false;
                    v12.pinned = v13_1;
                    v13_1 = (v0_3.b(14) & 1) != 0 ? true : false;
                    v12.unread_mark = v13_1;
                    ((messages_Dialogs)v3_1).dialogs.add(v12);
                    NativeByteBuffer v13_2 = v0_3.g(4);
                    if(v13_2 != null) {
                        Message v14 = Message.TLdeserialize(((AbstractSerializedData)v13_2), v13_2.readInt32(false), false);
                        v14.readAttachPath(((AbstractSerializedData)v13_2), UserConfig.getInstance(v1.currentAccount).clientUserId);
                        v13_2.reuse();
                        MessageObject.setUnreadFlags(v14, v0_3.b(5));
                        v14.id = v0_3.b(6);
                        v14.send_state = v0_3.b(7);
                        v13 = v0_3.b(8);
                        if(v13 != 0) {
                            v12.last_message_date = v13;
                        }

                        v14.dialog_id = v12.id;
                        ((messages_Dialogs)v3_1).messages.add(v14);
                        MessagesStorage.addUsersAndChatsFromMessage(v14, v9_3, v10_1);
                    }

                    v5 = ((int)v12.id);
                    v6 = ((int)(v12.id >> 32));
                    if(v5 != 0) {
                        if(v6 == 1) {
                            if(!v10_1.contains(Integer.valueOf(v5))) {
                                v5_1 = Integer.valueOf(v5);
                            }
                            else {
                                goto label_206;
                            }
                        }
                        else if(v5 > 0) {
                            if(!v9_3.contains(Integer.valueOf(v5))) {
                                v9_3.add(Integer.valueOf(v5));
                            }
                            else {
                            }

                            goto label_206;
                        }
                        else {
                            v5 = -v5;
                            if(!v10_1.contains(Integer.valueOf(v5))) {
                                v5_1 = Integer.valueOf(v5);
                            }
                            else {
                                goto label_206;
                            }
                        }

                        v10_1.add(v5_1);
                    }
                    else {
                        if(v11.contains(Integer.valueOf(v6))) {
                            goto label_206;
                        }

                        v11.add(Integer.valueOf(v6));
                    }

                label_206:
                    v5 = 3;
                    v6 = 2;
                }

                v0_3.b();
                if(!v11.isEmpty()) {
                    v1.getEncryptedChatsInternal(TextUtils.join(",", ((Iterable)v11)), v4_4, v9_3);
                }

                if(!v10_1.isEmpty()) {
                    v1.getChatsInternal(TextUtils.join(",", ((Iterable)v10_1)), ((messages_Dialogs)v3_1).chats);
                }

                if(!v9_3.isEmpty()) {
                    v1.getUsersInternal(TextUtils.join(",", ((Iterable)v9_3)), ((messages_Dialogs)v3_1).users);
                }

                if((((messages_Dialogs)v3_1).dialogs.isEmpty()) && (v4_4.isEmpty())) {
                    return;
                }

                MessagesController.getInstance(v1.currentAccount).processDialogsUpdate(((messages_Dialogs)v3_1), v4_4);
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }

            return;
        }

        throw new RuntimeException("wrong db thread");
    }

    public void updateDialogsWithReadMessages(SparseLongArray arg2, SparseLongArray arg3, ArrayList arg4, boolean arg5) {
        if((MessagesStorage.isEmpty(arg2)) && (MessagesStorage.isEmpty(((List)arg4)))) {
            return;
        }

        if(arg5) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$6ge5n8OwG6nnwnfOZsNtBU_Nc68(this, arg2, arg3, arg4));
        }
        else {
            this.updateDialogsWithReadMessagesInternal(null, arg2, arg3, arg4);
        }
    }

    private void updateDialogsWithReadMessagesInternal(ArrayList arg20, SparseLongArray arg21, SparseLongArray arg22, ArrayList arg23) {
        SQLitePreparedStatement v0_5;
        long v7_4;
        SQLiteCursor v9;
        int v7_2;
        MessagesStorage v1 = this;
        SparseLongArray v0 = arg21;
        SparseLongArray v2 = arg22;
        ArrayList v3 = arg23;
        try {
            LongSparseArray v4 = new LongSparseArray();
            LongSparseArray v5 = new LongSparseArray();
            ArrayList v6 = new ArrayList();
            int v8 = 2;
            int v10 = 0;
            if(!MessagesStorage.isEmpty(((List)arg20))) {
                SQLiteCursor v0_2 = v1.database.b(String.format(Locale.US, "SELECT uid, read_state, out FROM messages WHERE mid IN(%s)", TextUtils.join(",", arg20)), new Object[0]);
                while(v0_2.a()) {
                    if(v0_2.b(v8) != 0) {
                    }
                    else if(v0_2.b(1) != 0) {
                    }
                    else {
                        long v2_1 = v0_2.d(0);
                        Object v7 = v4.get(v2_1);
                        Integer v7_1 = v7 == null ? Integer.valueOf(1) : Integer.valueOf(((Integer)v7).intValue() + 1);
                        v4.put(v2_1, v7_1);
                    }
                }

                v0_2.b();
            }
            else {
                if(!MessagesStorage.isEmpty(arg21)) {
                    v7_2 = 0;
                    while(v7_2 < arg21.size()) {
                        int v12 = v0.keyAt(v7_2);
                        long v13 = v0.get(v12);
                        SQLiteDatabase v15 = v1.database;
                        Locale v11 = Locale.US;
                        v9 = v15.b(String.format(v11, "SELECT COUNT(mid) FROM messages WHERE uid = %d AND mid > %d AND read_state IN(0,2) AND out = 0", Integer.valueOf(v12), Long.valueOf(v13)), new Object[0]);
                        if(v9.a()) {
                            v4.put(((long)v12), Integer.valueOf(v9.b(0)));
                        }

                        v9.b();
                        SQLitePreparedStatement v8_1 = v1.database.a("UPDATE dialogs SET inbox_max = max((SELECT inbox_max FROM dialogs WHERE did = ?), ?) WHERE did = ?");
                        v8_1.d();
                        long v9_1 = ((long)v12);
                        v8_1.a(1, v9_1);
                        v8_1.a(2, ((int)v13));
                        v8_1.a(3, v9_1);
                        v8_1.b();
                        v8_1.e();
                        ++v7_2;
                        v8 = 2;
                    }
                }

                if(!MessagesStorage.isEmpty(((List)arg23))) {
                    ArrayList v0_3 = new ArrayList(((Collection)v3));
                    String v3_1 = TextUtils.join(",", ((Iterable)v3));
                    SQLiteDatabase v7_3 = v1.database;
                    Locale v8_2 = Locale.US;
                    Object[] v11_1 = new Object[1];
                    v10 = 0;
                    v11_1[0] = v3_1;
                    SQLiteCursor v3_2 = v7_3.b(String.format(v8_2, "SELECT uid, read_state, out, mention, mid FROM messages WHERE mid IN(%s)", v11_1), new Object[0]);
                    while(v3_2.a()) {
                        v7_4 = v3_2.d(v10);
                        v0_3.remove(Long.valueOf(v3_2.d(4)));
                        int v11_2 = 2;
                        if(v3_2.b(1) < v11_2 && v3_2.b(v11_2) == 0 && v3_2.b(3) == 1) {
                            Object v9_2 = v5.get(v7_4);
                            if(v9_2 == null) {
                                SQLiteDatabase v9_3 = v1.database;
                                StringBuilder v10_2 = new StringBuilder();
                                v10_2.append("SELECT unread_count_i FROM dialogs WHERE did = ");
                                v10_2.append(v7_4);
                                v9 = v9_3.b(v10_2.toString(), new Object[0]);
                                v10 = v9.a() ? v9.b(0) : 0;
                                v9.b();
                                v5.put(v7_4, Integer.valueOf(Math.max(0, v10 - 1)));
                            }
                            else {
                                v10 = 0;
                                v5.put(v7_4, Integer.valueOf(Math.max(0, ((Integer)v9_2).intValue() - 1)));
                                continue;
                            }
                        }

                        v10 = 0;
                    }

                    v3_2.b();
                    int v3_3;
                    for(v3_3 = 0; v3_3 < v0_3.size(); ++v3_3) {
                        v7_2 = ((int)(v0_3.get(v3_3).longValue() >> 32));
                        if(v7_2 > 0 && !v6.contains(Integer.valueOf(v7_2))) {
                            v6.add(Integer.valueOf(v7_2));
                        }
                    }
                }
                else {
                    v10 = 0;
                }

                if(MessagesStorage.isEmpty(arg22)) {
                    goto label_199;
                }

                int v0_4;
                for(v0_4 = 0; v0_4 < arg22.size(); ++v0_4) {
                    v3_3 = v2.keyAt(v0_4);
                    v7_4 = v2.get(v3_3);
                    SQLitePreparedStatement v9_4 = v1.database.a("UPDATE dialogs SET outbox_max = max((SELECT outbox_max FROM dialogs WHERE did = ?), ?) WHERE did = ?");
                    v9_4.d();
                    long v11_3 = ((long)v3_3);
                    v9_4.a(1, v11_3);
                    v9_4.a(2, ((int)v7_4));
                    v9_4.a(3, v11_3);
                    v9_4.b();
                    v9_4.e();
                }
            }

        label_199:
            if(v4.size() > 0 || v5.size() > 0) {
                v1.database.d();
                if(v4.size() > 0) {
                    v0_5 = v1.database.a("UPDATE dialogs SET unread_count = ? WHERE did = ?");
                    int v2_2;
                    for(v2_2 = 0; v2_2 < v4.size(); ++v2_2) {
                        v0_5.d();
                        v0_5.a(1, v4.valueAt(v2_2).intValue());
                        v0_5.a(2, v4.keyAt(v2_2));
                        v0_5.b();
                    }

                    v0_5.e();
                }

                if(v5.size() > 0) {
                    v0_5 = v1.database.a("UPDATE dialogs SET unread_count_i = ? WHERE did = ?");
                    while(v10 < v5.size()) {
                        v0_5.d();
                        v0_5.a(1, v5.valueAt(v10).intValue());
                        v0_5.a(2, v5.keyAt(v10));
                        v0_5.b();
                        ++v10;
                    }

                    v0_5.e();
                }

                v1.database.e();
            }

            MessagesController.getInstance(v1.currentAccount).processDialogsUpdateRead(v4, v5);
            if(v6.isEmpty()) {
                return;
            }

            MessagesController.getInstance(v1.currentAccount).reloadMentionsCountForChannels(v6);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public void updateEncryptedChat(EncryptedChat arg3) {
        if(arg3 == null) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$o-ygUh_QcJUM5VZGQOVkA1RHoQ4(this, arg3));
    }

    public void updateEncryptedChatLayer(EncryptedChat arg3) {
        if(arg3 == null) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$Uxf5CTpTh-viaedRWhfEzJuOaAw(this, arg3));
    }

    public void updateEncryptedChatSeq(EncryptedChat arg3, boolean arg4) {
        if(arg3 == null) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$VvZPOHcjZ0gd5mYw_rt_GNIBcJA(this, arg3, arg4));
    }

    public void updateEncryptedChatTTL(EncryptedChat arg3) {
        if(arg3 == null) {
            return;
        }

        this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$ySKleQJmoLs-m6BSZKCjdRjm3vM(this, arg3));
    }

    public long[] updateMessageStateAndId(long arg12, Integer arg14, int arg15, int arg16, boolean arg17, int arg18) {
        if(arg17) {
            this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$cOtsp-VHyGh2JREW1-725OejKtk(this, arg12, arg14, arg15, arg16, arg18));
            return null;
        }

        return this.updateMessageStateAndIdInternal(arg12, arg14, arg15, arg16, arg18);
    }

    private long[] updateMessageStateAndIdInternal(long arg21, Integer arg23, int arg24, int arg25, int arg26) {
        SQLitePreparedStatement v4_1;
        SQLitePreparedStatement v2_1;
        long[] v0_2;
        SQLitePreparedStatement v9_1;
        long v15;
        SQLiteCursor v3_1;
        long v13;
        SQLiteCursor v6_1;
        Integer v9;
        SQLiteCursor v10;
        MessagesStorage v1 = this;
        int v2 = arg25;
        int v3 = arg26;
        long v4 = ((long)arg24);
        long[] v6 = null;
        if(arg23 != null) {
            goto label_45;
        }

        try {
            v10 = v1.database.b(String.format(Locale.US, "SELECT mid FROM randoms WHERE random_id = %d LIMIT 1", Long.valueOf(arg21)), new Object[0]);
        }
        catch(Throwable v0) {
            goto label_42;
        }
        catch(Exception v0_1) {
            v10 = ((SQLiteCursor)v6);
            goto label_34;
        }

        try {
            if(v10.a()) {
                v9 = Integer.valueOf(v10.b(0));
            }
            else {
                goto label_24;
            }

            goto label_25;
        }
        catch(Throwable v0) {
        label_41:
            v6_1 = v10;
        }
        catch(Exception v0_1) {
            try {
            label_34:
                FileLog.e(((Throwable)v0_1));
                if(v10 != null) {
                    goto label_36;
                }

                goto label_37;
            }
            catch(Throwable v0) {
                goto label_41;
            }

        label_36:
            v10.b();
        label_37:
            v9 = arg23;
            goto label_38;
        }

    label_42:
        if(v6_1 != null) {
            v6_1.b();
        }

        throw v0;
    label_24:
        v9 = arg23;
    label_25:
        if(v10 != null) {
            v10.b();
        }

    label_38:
        if(v9 != null) {
        }
        else {
            return v6;
        label_45:
            v9 = arg23;
            v10 = ((SQLiteCursor)v6);
        }

        long v11 = ((long)v9.intValue());
        if(v3 != 0) {
            v13 = (((long)v3)) << 32;
            v11 |= v13;
            v13 |= v4;
        }
        else {
            v13 = v4;
        }

        try {
            v3_1 = v1.database.b(String.format(Locale.US, "SELECT uid FROM messages WHERE mid = %d LIMIT 1", Long.valueOf(v11)), new Object[0]);
            goto label_66;
        }
        catch(Throwable v0) {
        }
        catch(Exception v0_1) {
            goto label_83;
            try {
            label_66:
                if(v3_1.a()) {
                    v15 = v3_1.d(0);
                }
                else {
                    goto label_70;
                }

                goto label_71;
            }
            catch(Throwable v0) {
                v10 = v3_1;
            }
            catch(Exception v0_1) {
                v10 = v3_1;
                try {
                label_83:
                    FileLog.e(((Throwable)v0_1));
                    if(v10 == null) {
                        goto label_86;
                    }
                }
                catch(Throwable v0) {
                    goto label_233;
                }

                v10.b();
            label_86:
                v15 = 0;
                goto label_87;
            }
        }

    label_233:
        if(v10 != null) {
            v10.b();
        }

        throw v0;
    label_70:
        v15 = 0;
    label_71:
        if(v3_1 != null) {
            v3_1.b();
        }

    label_87:
        if(v15 == 0) {
            return null;
        }

        SQLitePreparedStatement v3_2 = null;
        int v6_2 = 2;
        if(Long.compare(v11, v13) == 0 && v2 != 0) {
            try {
                v9_1 = v1.database.a("UPDATE messages SET send_state = 0, date = ? WHERE mid = ?");
            }
            catch(Throwable v0) {
                v9_1 = v3_2;
                goto label_119;
            }
            catch(Exception v0_1) {
                v9_1 = v3_2;
                goto label_111;
            }

            try {
                v9_1.a(1, v2);
                v9_1.a(v6_2, v13);
                v9_1.b();
                if(v9_1 == null) {
                    goto label_114;
                }

                goto label_113;
            }
            catch(Throwable v0) {
            }
            catch(Exception v0_1) {
                try {
                label_111:
                    FileLog.e(((Throwable)v0_1));
                    if(v9_1 == null) {
                        goto label_114;
                    }

                    goto label_113;
                }
                catch(Throwable v0) {
                }
            }

        label_119:
            if(v9_1 != null) {
                v9_1.e();
            }

            throw v0;
        label_113:
            v9_1.e();
        label_114:
            v0_2 = new long[v6_2];
            v0_2[0] = v15;
            v0_2[1] = v4;
            return v0_2;
        }

        try {
            v2_1 = v1.database.a("UPDATE messages SET mid = ?, send_state = 0 WHERE mid = ?");
        }
        catch(Throwable v0) {
            v2_1 = v3_2;
            goto label_230;
        }
        catch(Exception ) {
            v2_1 = v3_2;
            goto label_134;
        }

        try {
            v2_1.a(1, v13);
            v2_1.a(v6_2, v11);
            v2_1.b();
            if(v2_1 == null) {
                goto label_162;
            }

            goto label_160;
        }
        catch(Throwable v0) {
        }
        catch(Exception ) {
            try {
            label_134:
                v1.database.a(String.format(Locale.US, "DELETE FROM messages WHERE mid = %d", Long.valueOf(v11))).c().e();
                v1.database.a(String.format(Locale.US, "DELETE FROM messages_seq WHERE mid = %d", Long.valueOf(v11))).c().e();
                goto label_159;
            }
            catch(Throwable v0) {
            label_156:
            }
            catch(Exception v0_1) {
                try {
                    FileLog.e(((Throwable)v0_1));
                }
                catch(Throwable v0) {
                    goto label_156;
                }

            label_159:
                if(v2_1 == null) {
                    goto label_162;
                }

                goto label_160;
            }
        }

    label_230:
        if(v2_1 != null) {
            v2_1.e();
        }

        throw v0;
    label_160:
        v2_1.e();
        v2_1 = v3_2;
        try {
        label_162:
            v4_1 = v1.database.a("UPDATE media_v2 SET mid = ? WHERE mid = ?");
        }
        catch(Throwable v0) {
            goto label_179;
        }
        catch(Exception ) {
            goto label_180;
        }

        try {
            v4_1.a(1, v13);
            v4_1.a(v6_2, v11);
            v4_1.b();
            if(v4_1 == null) {
                goto label_171;
            }

            goto label_169;
        }
        catch(Throwable v0) {
            v2_1 = v4_1;
        }
        catch(Exception ) {
            v2_1 = v4_1;
            try {
            label_180:
                v1.database.a(String.format(Locale.US, "DELETE FROM media_v2 WHERE mid = %d", Long.valueOf(v11))).c().e();
            }
            catch(Throwable v0) {
            }
            catch(Exception v0_1) {
                try {
                    FileLog.e(((Throwable)v0_1));
                }
                catch(Throwable v0) {
                label_179:
                    goto label_227;
                }
            }

            if(v2_1 != null) {
                v2_1.e();
                goto label_197;
            }

            v3_2 = v2_1;
            goto label_197;
        }

    label_227:
        if(v2_1 != null) {
            v2_1.e();
        }

        throw v0;
    label_169:
        v4_1.e();
        goto label_197;
    label_171:
        v3_2 = v4_1;
        try {
        label_197:
            v2_1 = v1.database.a("UPDATE dialogs SET last_mid = ? WHERE last_mid = ?");
            goto label_200;
        }
        catch(Throwable v0) {
        }
        catch(Exception v0_1) {
            goto label_215;
            try {
            label_200:
                v2_1.a(1, v13);
                v2_1.a(v6_2, v11);
                v2_1.b();
                if(v2_1 == null) {
                    goto label_218;
                }

                goto label_204;
            }
            catch(Throwable v0) {
                v3_2 = v2_1;
            }
            catch(Exception v0_1) {
                v3_2 = v2_1;
                try {
                label_215:
                    FileLog.e(((Throwable)v0_1));
                    if(v3_2 == null) {
                        goto label_218;
                    }
                }
                catch(Throwable v0) {
                    goto label_224;
                }

                v3_2.e();
                goto label_218;
            }
        }

    label_224:
        if(v3_2 != null) {
            v3_2.e();
        }

        throw v0;
    label_204:
        v2_1.e();
    label_218:
        v0_2 = new long[v6_2];
        v0_2[0] = v15;
        v0_2[1] = ((long)v9.intValue());
        return v0_2;
    }

    public void updateUsers(ArrayList arg2, boolean arg3, boolean arg4, boolean arg5) {
        if(arg2 != null) {
            if(arg2.isEmpty()) {
            }
            else if(arg5) {
                this.storageQueue.postRunnable(new -$$Lambda$MessagesStorage$DvsqzC8EDZTkSKlTek69qcpHRiA(this, arg2, arg3, arg4));
            }
            else {
                this.updateUsersInternal(arg2, arg3, arg4);
            }
        }
    }

    private void updateUsersInternal(ArrayList arg6, boolean arg7, boolean arg8) {
        Object v1;
        if(Thread.currentThread().getId() == this.storageQueue.getId()) {
            if(arg7) {
                if(arg8) {
                    try {
                        this.database.d();
                    label_9:
                        SQLitePreparedStatement v7 = this.database.a("UPDATE users SET status = ? WHERE uid = ?");
                        Iterator v6_1 = arg6.iterator();
                        while(v6_1.hasNext()) {
                            Object v0 = v6_1.next();
                            v7.d();
                            if(((User)v0).status != null) {
                                v7.a(1, ((User)v0).status.expires);
                            }
                            else {
                                v7.a(1, 0);
                            }

                            v7.a(2, ((User)v0).id);
                            v7.b();
                        }

                        v7.e();
                        if(!arg8) {
                            return;
                        }

                        SQLiteDatabase v6_2 = this.database;
                        goto label_34;
                    label_36:
                        StringBuilder v7_1 = new StringBuilder();
                        SparseArray v0_1 = new SparseArray();
                        v6_1 = arg6.iterator();
                        while(v6_1.hasNext()) {
                            v1 = v6_1.next();
                            if(v7_1.length() != 0) {
                                v7_1.append(",");
                            }

                            v7_1.append(((User)v1).id);
                            v0_1.put(((User)v1).id, v1);
                        }

                        arg6 = new ArrayList();
                        this.getUsersInternal(v7_1.toString(), arg6);
                        Iterator v7_2 = arg6.iterator();
                        while(v7_2.hasNext()) {
                            v1 = v7_2.next();
                            Object v2 = v0_1.get(((User)v1).id);
                            if(v2 == null) {
                                continue;
                            }

                            if(((User)v2).first_name != null && ((User)v2).last_name != null) {
                                if(!UserObject.isContact(((User)v1))) {
                                    ((User)v1).first_name = ((User)v2).first_name;
                                    ((User)v1).last_name = ((User)v2).last_name;
                                }

                                ((User)v1).username = ((User)v2).username;
                                continue;
                            }

                            if(((User)v2).photo != null) {
                                ((User)v1).photo = ((User)v2).photo;
                                continue;
                            }

                            if(((User)v2).phone == null) {
                                continue;
                            }

                            ((User)v1).phone = ((User)v2).phone;
                        }

                        if(arg6.isEmpty()) {
                            return;
                        }

                        if(arg8) {
                            this.database.d();
                        }

                        this.putUsersInternal(arg6);
                        if(!arg8) {
                            return;
                        }

                        v6_2 = this.database;
                    label_34:
                        v6_2.e();
                    }
                    catch(Exception v6) {
                        FileLog.e(((Throwable)v6));
                    }

                    return;
                }

                goto label_9;
            }
            else {
                goto label_36;
            }

            return;
        }

        throw new RuntimeException("wrong db thread");
    }
}

