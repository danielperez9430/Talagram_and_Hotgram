package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.LongSparseArray;
import android.util.SparseArray;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLClassStore;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$DecryptedMessage;
import org.telegram.tgnet.TLRPC$DecryptedMessageAction;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$EncryptedFile;
import org.telegram.tgnet.TLRPC$EncryptedMessage;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionAbortKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionAcceptKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionCommitKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionDeleteMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionFlushHistory;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionNoop;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionNotifyLayer;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionReadMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionRequestKey;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionScreenshotMessages;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageActionSetMessageTTL;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageLayer;
import org.telegram.tgnet.TLRPC$TL_decryptedMessageService;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_documentEncrypted;
import org.telegram.tgnet.TLRPC$TL_encryptedChat;
import org.telegram.tgnet.TLRPC$TL_encryptedChatDiscarded;
import org.telegram.tgnet.TLRPC$TL_encryptedChatRequested;
import org.telegram.tgnet.TLRPC$TL_encryptedChatWaiting;
import org.telegram.tgnet.TLRPC$TL_encryptedFile;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_fileEncryptedLocation;
import org.telegram.tgnet.TLRPC$TL_inputEncryptedChat;
import org.telegram.tgnet.TLRPC$TL_messageEncryptedAction;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageService;
import org.telegram.tgnet.TLRPC$TL_messages_acceptEncryption;
import org.telegram.tgnet.TLRPC$TL_messages_dhConfig;
import org.telegram.tgnet.TLRPC$TL_messages_discardEncryption;
import org.telegram.tgnet.TLRPC$TL_messages_getDhConfig;
import org.telegram.tgnet.TLRPC$TL_messages_requestEncryption;
import org.telegram.tgnet.TLRPC$TL_messages_sendEncrypted;
import org.telegram.tgnet.TLRPC$TL_messages_sendEncryptedFile;
import org.telegram.tgnet.TLRPC$TL_messages_sendEncryptedMultiMedia;
import org.telegram.tgnet.TLRPC$TL_messages_sendEncryptedService;
import org.telegram.tgnet.TLRPC$TL_peerUser;
import org.telegram.tgnet.TLRPC$TL_updateEncryption;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$messages_DhConfig;
import org.telegram.tgnet.TLRPC$messages_SentEncryptedMessage;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.AlertDialog;

public class SecretChatHelper {
    public class TL_decryptedMessageHolder extends TLObject {
        public static int constructor = 1431655929;
        public int date;
        public int decryptedWithVersion;
        public EncryptedFile file;
        public TL_decryptedMessageLayer layer;
        public boolean new_key_used;

        static {
        }

        public TL_decryptedMessageHolder() {
            super();
        }

        public void readParams(AbstractSerializedData arg2, boolean arg3) {
            arg2.readInt64(arg3);
            this.date = arg2.readInt32(arg3);
            this.layer = TL_decryptedMessageLayer.TLdeserialize(arg2, arg2.readInt32(arg3), arg3);
            if(arg2.readBool(arg3)) {
                this.file = EncryptedFile.TLdeserialize(arg2, arg2.readInt32(arg3), arg3);
            }

            this.new_key_used = arg2.readBool(arg3);
        }

        public void serializeToStream(AbstractSerializedData arg3) {
            arg3.writeInt32(TL_decryptedMessageHolder.constructor);
            arg3.writeInt64(0);
            arg3.writeInt32(this.date);
            this.layer.serializeToStream(arg3);
            boolean v0 = this.file != null ? true : false;
            arg3.writeBool(v0);
            if(this.file != null) {
                this.file.serializeToStream(arg3);
            }

            arg3.writeBool(this.new_key_used);
        }
    }

    public static final int CURRENT_SECRET_CHAT_LAYER = 73;
    private static volatile SecretChatHelper[] Instance;
    private SparseArray acceptingChats;
    private int currentAccount;
    public ArrayList delayedEncryptedChatUpdates;
    private ArrayList pendingEncMessagesToDelete;
    private SparseArray secretHolesQueue;
    private ArrayList sendingNotifyLayer;
    private boolean startingSecretChat;

    static {
        SecretChatHelper.Instance = new SecretChatHelper[3];
    }

    public SecretChatHelper(int arg2) {
        super();
        this.sendingNotifyLayer = new ArrayList();
        this.secretHolesQueue = new SparseArray();
        this.acceptingChats = new SparseArray();
        this.delayedEncryptedChatUpdates = new ArrayList();
        this.pendingEncMessagesToDelete = new ArrayList();
        this.startingSecretChat = false;
        this.currentAccount = arg2;
    }

    public void acceptSecretChat(EncryptedChat arg4) {
        if(this.acceptingChats.get(arg4.id) != null) {
            return;
        }

        this.acceptingChats.put(arg4.id, arg4);
        TL_messages_getDhConfig v0 = new TL_messages_getDhConfig();
        v0.random_length = 256;
        v0.version = MessagesStorage.getInstance(this.currentAccount).getLastSecretVersion();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$SecretChatHelper$13Zq8UfkfLhsSEm3Hl6dWS1zGEc(this, arg4));
    }

    private void applyPeerLayer(EncryptedChat arg7, int arg8) {
        int v0 = AndroidUtilities.getPeerLayerVersion(arg7.layer);
        if(arg8 <= v0) {
            return;
        }

        int v2 = 16;
        if(arg7.key_hash.length == v2 && v0 >= 46) {
            try {
                byte[] v1_1 = Utilities.computeSHA256(arg7.auth_key, 0, arg7.auth_key.length);
                byte[] v3 = new byte[36];
                System.arraycopy(arg7.key_hash, 0, v3, 0, v2);
                System.arraycopy(v1_1, 0, v3, v2, 20);
                arg7.key_hash = v3;
                MessagesStorage.getInstance(this.currentAccount).updateEncryptedChat(arg7);
            }
            catch(Throwable v1) {
                FileLog.e(v1);
            }
        }

        arg7.layer = AndroidUtilities.setPeerLayerVersion(arg7.layer, arg8);
        MessagesStorage.getInstance(this.currentAccount).updateEncryptedChatLayer(arg7);
        if(v0 < 73) {
            this.sendNotifyLayerMessage(arg7, null);
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$n0uu_vomtENRYh_Kxa0sgG1vkoo(this, arg7));
    }

    public void checkSecretHoles(EncryptedChat arg12, ArrayList arg13) {
        Object v0 = this.secretHolesQueue.get(arg12.id);
        if(v0 == null) {
            return;
        }

        Collections.sort(((List)v0), -$$Lambda$SecretChatHelper$XChl_gDRHQHDfwtxghrPUY1XhL4.INSTANCE);
        int v3;
        for(v3 = 0; ((ArrayList)v0).size() > 0; v3 = 1) {
            Object v4 = ((ArrayList)v0).get(0);
            int v7 = 2;
            if(((TL_decryptedMessageHolder)v4).layer.out_seq_no != arg12.seq_in && arg12.seq_in != ((TL_decryptedMessageHolder)v4).layer.out_seq_no - v7) {
                break;
            }

            this.applyPeerLayer(arg12, ((TL_decryptedMessageHolder)v4).layer.layer);
            arg12.seq_in = ((TL_decryptedMessageHolder)v4).layer.out_seq_no;
            arg12.in_seq_no = ((TL_decryptedMessageHolder)v4).layer.in_seq_no;
            ((ArrayList)v0).remove(0);
            if(((TL_decryptedMessageHolder)v4).decryptedWithVersion == v7) {
                arg12.mtproto_seq = Math.min(arg12.mtproto_seq, arg12.seq_in);
            }

            Message v3_1 = this.processDecryptedObject(arg12, ((TL_decryptedMessageHolder)v4).file, ((TL_decryptedMessageHolder)v4).date, ((TL_decryptedMessageHolder)v4).layer.message, ((TL_decryptedMessageHolder)v4).new_key_used);
            if(v3_1 != null) {
                arg13.add(v3_1);
            }
        }

        if(((ArrayList)v0).isEmpty()) {
            this.secretHolesQueue.remove(arg12.id);
        }

        if(v3 != 0) {
            MessagesStorage.getInstance(this.currentAccount).updateEncryptedChatSeq(arg12, true);
        }
    }

    public void cleanup() {
        this.sendingNotifyLayer.clear();
        this.acceptingChats.clear();
        this.secretHolesQueue.clear();
        this.delayedEncryptedChatUpdates.clear();
        this.pendingEncMessagesToDelete.clear();
        this.startingSecretChat = false;
    }

    private Message createDeleteMessage(int arg5, int arg6, int arg7, long arg8, EncryptedChat arg10) {
        Peer v5;
        TL_messageService v0 = new TL_messageService();
        v0.action = new TL_messageEncryptedAction();
        v0.action.encryptedAction = new TL_decryptedMessageActionDeleteMessages();
        v0.action.encryptedAction.random_ids.add(Long.valueOf(arg8));
        v0.id = arg5;
        v0.local_id = arg5;
        v0.from_id = UserConfig.getInstance(this.currentAccount).getClientUserId();
        v0.unread = true;
        v0.out = true;
        v0.flags = 256;
        v0.dialog_id = (((long)arg10.id)) << 32;
        v0.to_id = new TL_peerUser();
        v0.send_state = 1;
        v0.seq_in = arg7;
        v0.seq_out = arg6;
        if(arg10.participant_id == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
            v5 = v0.to_id;
            arg6 = arg10.admin_id;
        }
        else {
            v5 = v0.to_id;
            arg6 = arg10.participant_id;
        }

        v5.user_id = arg6;
        v0.date = 0;
        v0.random_id = arg8;
        return ((Message)v0);
    }

    private TL_messageService createServiceSecretMessage(EncryptedChat arg9, DecryptedMessageAction arg10) {
        int v9;
        Peer v1_1;
        TL_messageService v0 = new TL_messageService();
        v0.action = new TL_messageEncryptedAction();
        v0.action.encryptedAction = arg10;
        int v1 = UserConfig.getInstance(this.currentAccount).getNewMessageId();
        v0.id = v1;
        v0.local_id = v1;
        v0.from_id = UserConfig.getInstance(this.currentAccount).getClientUserId();
        v0.unread = true;
        v0.out = true;
        v0.flags = 256;
        v0.dialog_id = (((long)arg9.id)) << 32;
        v0.to_id = new TL_peerUser();
        v0.send_state = 1;
        if(arg9.participant_id == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
            v1_1 = v0.to_id;
            v9 = arg9.admin_id;
        }
        else {
            v1_1 = v0.to_id;
            v9 = arg9.participant_id;
        }

        v1_1.user_id = v9;
        v0.date = ((arg10 instanceof TL_decryptedMessageActionScreenshotMessages)) || ((arg10 instanceof TL_decryptedMessageActionSetMessageTTL)) ? ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() : 0;
        v0.random_id = SendMessagesHelper.getInstance(this.currentAccount).getNextRandomId();
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
        ArrayList v3 = new ArrayList();
        v3.add(v0);
        MessagesStorage.getInstance(this.currentAccount).putMessages(v3, false, true, true, 0);
        return v0;
    }

    public void declineSecretChat(int arg3) {
        TL_messages_discardEncryption v0 = new TL_messages_discardEncryption();
        v0.chat_id = arg3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), -$$Lambda$SecretChatHelper$mfQ9CBiHkuJ2sHYijldk5vxoP4M.INSTANCE);
    }

    protected ArrayList decryptMessage(EncryptedMessage arg20) {
        DecryptedMessage v1_2;
        ArrayList v2_2;
        boolean v15;
        byte[] v14;
        SecretChatHelper v8 = this;
        EncryptedMessage v0 = arg20;
        EncryptedChat v10 = MessagesController.getInstance(v8.currentAccount).getEncryptedChatDB(v0.chat_id, true);
        ArrayList v11 = null;
        if(v10 != null && !(v10 instanceof TL_encryptedChatDiscarded)) {
            try {
                NativeByteBuffer v12 = new NativeByteBuffer(v0.bytes.length);
                v12.writeBytes(v0.bytes);
                v12.position(0);
                long v1 = v12.readInt64(false);
                if(v10.key_fingerprint == v1) {
                    v14 = v10.auth_key;
                    goto label_25;
                }
                else {
                    if(v10.future_key_fingerprint != 0 && v10.future_key_fingerprint == v1) {
                        v14 = v10.future_auth_key;
                        v15 = true;
                        goto label_38;
                    }

                    v14 = ((byte[])v11);
                label_25:
                    v15 = false;
                }

            label_38:
                int v7 = 2;
                int v6 = AndroidUtilities.getPeerLayerVersion(v10.layer) >= 73 ? 2 : 1;
                if(v14 != null) {
                    byte[] v16 = v12.readData(16, false);
                    boolean v17 = v10.admin_id == UserConfig.getInstance(v8.currentAccount).getClientUserId() ? true : false;
                    boolean v18 = v6 != v7 || v10.mtproto_seq == 0 ? true : false;
                    int v9 = v6;
                    int v13 = 2;
                    if(!this.decryptWithMtProtoVersion(v12, v14, v16, v6, v17, v18)) {
                        if(v9 == v13) {
                            if(v18) {
                                if(!this.decryptWithMtProtoVersion(v12, v14, v16, 1, v17, false)) {
                                }
                                else {
                                    v9 = 1;
                                    goto label_100;
                                }
                            }

                            return v11;
                        }
                        else {
                            if(!this.decryptWithMtProtoVersion(v12, v14, v16, 2, v17, v18)) {
                                return v11;
                            }

                            v9 = 2;
                        }
                    }

                label_100:
                    TLObject v1_1 = TLClassStore.Instance().TLdeserialize(v12, v12.readInt32(false), false);
                    v12.reuse();
                    if(!v15 && AndroidUtilities.getPeerLayerVersion(v10.layer) >= 20) {
                        v10.key_use_count_in = ((short)(v10.key_use_count_in + 1));
                    }

                    if((v1_1 instanceof TL_decryptedMessageLayer)) {
                        if(v10.seq_in == 0 && v10.seq_out == 0) {
                            if(v10.admin_id == UserConfig.getInstance(v8.currentAccount).getClientUserId()) {
                                v10.seq_out = 1;
                                v10.seq_in = -2;
                            }
                            else {
                                v10.seq_in = -1;
                            }
                        }

                        if(((TL_decryptedMessageLayer)v1_1).random_bytes.length < 15) {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.e("got random bytes less than needed");
                            }

                            return v11;
                        }

                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("current chat in_seq = " + v10.seq_in + " out_seq = " + v10.seq_out);
                            FileLog.d("got message with in_seq = " + ((TL_decryptedMessageLayer)v1_1).in_seq_no + " out_seq = " + ((TL_decryptedMessageLayer)v1_1).out_seq_no);
                        }

                        if(((TL_decryptedMessageLayer)v1_1).out_seq_no <= v10.seq_in) {
                            return v11;
                        }

                        if(v9 == 1 && v10.mtproto_seq != 0 && ((TL_decryptedMessageLayer)v1_1).out_seq_no >= v10.mtproto_seq) {
                            return v11;
                        }

                        if(v10.seq_in != ((TL_decryptedMessageLayer)v1_1).out_seq_no - v13) {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.e("got hole");
                            }

                            Object v2_1 = v8.secretHolesQueue.get(v10.id);
                            if(v2_1 == null) {
                                v2_2 = new ArrayList();
                                v8.secretHolesQueue.put(v10.id, v2_2);
                            }

                            if(v2_2.size() >= 4) {
                                v8.secretHolesQueue.remove(v10.id);
                                TL_encryptedChatDiscarded v0_2 = new TL_encryptedChatDiscarded();
                                v0_2.id = v10.id;
                                v0_2.user_id = v10.user_id;
                                v0_2.auth_key = v10.auth_key;
                                v0_2.key_create_date = v10.key_create_date;
                                v0_2.key_use_count_in = v10.key_use_count_in;
                                v0_2.key_use_count_out = v10.key_use_count_out;
                                v0_2.seq_in = v10.seq_in;
                                v0_2.seq_out = v10.seq_out;
                                AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$y_-QKcAzTtKUDu-WddTe8KbVDxY(v8, v0_2));
                                v8.declineSecretChat(v10.id);
                                return v11;
                            }

                            TL_decryptedMessageHolder v3 = new TL_decryptedMessageHolder();
                            v3.layer = ((TL_decryptedMessageLayer)v1_1);
                            v3.file = v0.file;
                            v3.date = v0.date;
                            v3.new_key_used = v15;
                            v3.decryptedWithVersion = v9;
                            v2_2.add(v3);
                            return v11;
                        }

                        if(v9 == v13) {
                            v10.mtproto_seq = Math.min(v10.mtproto_seq, v10.seq_in);
                        }

                        v8.applyPeerLayer(v10, ((TL_decryptedMessageLayer)v1_1).layer);
                        v10.seq_in = ((TL_decryptedMessageLayer)v1_1).out_seq_no;
                        v10.in_seq_no = ((TL_decryptedMessageLayer)v1_1).in_seq_no;
                        MessagesStorage.getInstance(v8.currentAccount).updateEncryptedChatSeq(v10, true);
                        v1_2 = ((TL_decryptedMessageLayer)v1_1).message;
                    }
                    else {
                        if((v1_1 instanceof TL_decryptedMessageService)) {
                            if(!(v1_1.action instanceof TL_decryptedMessageActionNotifyLayer)) {
                            }
                            else {
                                goto label_262;
                            }
                        }

                        return v11;
                    }

                label_262:
                    ArrayList v7_1 = new ArrayList();
                    Message v0_3 = this.processDecryptedObject(v10, v0.file, v0.date, ((TLObject)v1_2), v15);
                    if(v0_3 != null) {
                        v7_1.add(v0_3);
                    }

                    v8.checkSecretHoles(v10, v7_1);
                    return v7_1;
                }

                v12.reuse();
                if(!BuildVars.LOGS_ENABLED) {
                    return v11;
                }

                FileLog.e(String.format("fingerprint mismatch %x", Long.valueOf(v1)));
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }
        }

        return v11;
    }

    private boolean decryptWithMtProtoVersion(NativeByteBuffer arg28, byte[] arg29, byte[] arg30, int arg31, boolean arg32, boolean arg33) {
        int v15;
        boolean v7;
        byte[] v6;
        NativeByteBuffer v0 = arg28;
        byte[] v1 = arg30;
        int v2 = arg31;
        if(v2 == 1) {
            v6 = arg29;
            v7 = false;
        }
        else {
            v6 = arg29;
            v7 = arg32;
        }

        MessageKeyData v12 = MessageKeyData.generateMessageKeyData(v6, v1, v7, v2);
        Utilities.aesIgeEncryption(v0.buffer, v12.aesKey, v12.aesIv, false, false, 24, arg28.limit() - 24);
        int v13 = v0.readInt32(false);
        int v14 = 15;
        if(v2 == 2) {
            int v7_1 = v7 ? 8 : 0;
            v15 = 24;
            if(Utilities.arraysEquals(v1, 0, Utilities.computeSHA256(arg29, v7_1 + 88, 32, v0.buffer, 24, v0.buffer.limit()), 8)) {
                goto label_91;
            }

            if(arg33) {
                Utilities.aesIgeEncryption(v0.buffer, v12.aesKey, v12.aesIv, true, false, 24, arg28.limit() - 24);
                v0.position(v15);
            }

            return 0;
        }
        else {
            v15 = 24;
            int v4 = v13 + 28;
            if(v4 < v0.buffer.limit() - v14 || v4 > v0.buffer.limit()) {
                v4 = v0.buffer.limit();
            }

            byte[] v4_1 = Utilities.computeSHA1(v0.buffer, v15, v4);
            if(Utilities.arraysEquals(v1, 0, v4_1, v4_1.length - 16)) {
                goto label_91;
            }

            if(arg33) {
                Utilities.aesIgeEncryption(v0.buffer, v12.aesKey, v12.aesIv, true, false, 24, arg28.limit() - 24);
                v0.position(v15);
            }

            return 0;
        }

    label_91:
        if(v13 > 0) {
            if(v13 > arg28.limit() - 28) {
            }
            else {
                int v0_1 = arg28.limit() - 28 - v13;
                if(v2 == 2 && (v0_1 < 12 || v0_1 > 1024) || v2 == 1 && v0_1 > v14) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public static SecretChatHelper getInstance(int arg3) {
        SecretChatHelper v0 = SecretChatHelper.Instance[arg3];
        if(v0 == null) {
            Class v1 = SecretChatHelper.class;
            __monitor_enter(v1);
            try {
                v0 = SecretChatHelper.Instance[arg3];
                if(v0 == null) {
                    SecretChatHelper[] v0_1 = SecretChatHelper.Instance;
                    SecretChatHelper v2 = new SecretChatHelper(arg3);
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

    public static boolean isSecretInvisibleMessage(Message arg1) {
        boolean v1 = !(arg1.action instanceof TL_messageEncryptedAction) || ((arg1.action.encryptedAction instanceof TL_decryptedMessageActionScreenshotMessages)) || ((arg1.action.encryptedAction instanceof TL_decryptedMessageActionSetMessageTTL)) ? false : true;
        return v1;
    }

    public static boolean isSecretVisibleMessage(Message arg1) {
        boolean v1;
        if((arg1.action instanceof TL_messageEncryptedAction)) {
            if(!(arg1.action.encryptedAction instanceof TL_decryptedMessageActionScreenshotMessages) && !(arg1.action.encryptedAction instanceof TL_decryptedMessageActionSetMessageTTL)) {
                goto label_13;
            }

            v1 = true;
        }
        else {
        label_13:
            v1 = false;
        }

        return v1;
    }

    public static void lambda$acceptSecretChat$22(SecretChatHelper arg7, EncryptedChat arg8, TLObject arg9, TL_error arg10) {
        int v3;
        if(arg10 == null) {
            TLObject v10 = arg9;
            if(!(arg9 instanceof TL_messages_dhConfig)) {
            label_38:
                int v9 = 256;
                byte[] v0 = new byte[v9];
                int v2;
                for(v2 = 0; v2 < v9; ++v2) {
                    v0[v2] = ((byte)((((byte)(((int)(Utilities.random.nextDouble() * 256))))) ^ ((messages_DhConfig)v10).random[v2]));
                }

                arg8.a_or_b = v0;
                arg8.seq_in = -1;
                arg8.seq_out = 0;
                BigInteger v10_1 = new BigInteger(1, MessagesStorage.getInstance(arg7.currentAccount).getSecretPBytes());
                BigInteger v2_1 = BigInteger.valueOf(((long)MessagesStorage.getInstance(arg7.currentAccount).getSecretG())).modPow(new BigInteger(1, v0), v10_1);
                BigInteger v4 = new BigInteger(1, arg8.g_a);
                if(!Utilities.isGoodGaAndGb(v4, v10_1)) {
                }
                else {
                    byte[] v2_2 = v2_1.toByteArray();
                    if(v2_2.length > v9) {
                        byte[] v5 = new byte[v9];
                        System.arraycopy(v2_2, 1, v5, 0, v9);
                        v2_2 = v5;
                    }

                    byte[] v10_2 = v4.modPow(new BigInteger(1, v0), v10_1).toByteArray();
                    if(v10_2.length > v9) {
                        v0 = new byte[v9];
                        System.arraycopy(v10_2, v10_2.length - v9, v0, 0, v9);
                    }
                    else if(v10_2.length < v9) {
                        v0 = new byte[v9];
                        System.arraycopy(v10_2, 0, v0, 256 - v10_2.length, v10_2.length);
                        v3 = 0;
                        goto label_105;
                    }
                    else {
                        v0 = v10_2;
                        goto label_112;
                    label_105:
                        while(v3 < 256 - v10_2.length) {
                            v0[v3] = 0;
                            ++v3;
                        }
                    }

                label_112:
                    byte[] v9_1 = Utilities.computeSHA1(v0);
                    byte[] v3_1 = new byte[8];
                    System.arraycopy(v9_1, v9_1.length - 8, v3_1, 0, 8);
                    arg8.auth_key = v0;
                    arg8.key_create_date = ConnectionsManager.getInstance(arg7.currentAccount).getCurrentTime();
                    TL_messages_acceptEncryption v9_2 = new TL_messages_acceptEncryption();
                    v9_2.g_b = v2_2;
                    v9_2.peer = new TL_inputEncryptedChat();
                    v9_2.peer.chat_id = arg8.id;
                    v9_2.peer.access_hash = arg8.access_hash;
                    v9_2.key_fingerprint = Utilities.bytesToLong(v3_1);
                    ConnectionsManager.getInstance(arg7.currentAccount).sendRequest(((TLObject)v9_2), new -$$Lambda$SecretChatHelper$48_HP_7TQqG7f2oWszU7R3aCenM(arg7, arg8));
                    return;
                }
            }
            else if(Utilities.isGoodPrime(((messages_DhConfig)v10).p, ((messages_DhConfig)v10).g)) {
                MessagesStorage.getInstance(arg7.currentAccount).setSecretPBytes(((messages_DhConfig)v10).p);
                MessagesStorage.getInstance(arg7.currentAccount).setSecretG(((messages_DhConfig)v10).g);
                MessagesStorage.getInstance(arg7.currentAccount).setLastSecretVersion(((messages_DhConfig)v10).version);
                MessagesStorage.getInstance(arg7.currentAccount).saveSecretParams(MessagesStorage.getInstance(arg7.currentAccount).getLastSecretVersion(), MessagesStorage.getInstance(arg7.currentAccount).getSecretG(), MessagesStorage.getInstance(arg7.currentAccount).getSecretPBytes());
                goto label_38;
            }

            arg7.acceptingChats.remove(arg8.id);
            arg7.declineSecretChat(arg8.id);
            return;
        }
        else {
            arg7.acceptingChats.remove(arg8.id);
        }
    }

    public static void lambda$applyPeerLayer$8(SecretChatHelper arg4, EncryptedChat arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{arg5});
    }

    static int lambda$checkSecretHoles$15(TL_decryptedMessageHolder arg2, TL_decryptedMessageHolder arg3) {
        if(arg2.layer.out_seq_no > arg3.layer.out_seq_no) {
            return 1;
        }

        if(arg2.layer.out_seq_no < arg3.layer.out_seq_no) {
            return -1;
        }

        return 0;
    }

    static void lambda$declineSecretChat$19(TLObject arg0, TL_error arg1) {
    }

    public static void lambda$decryptMessage$16(SecretChatHelper arg4, TL_encryptedChatDiscarded arg5) {
        MessagesController.getInstance(arg4.currentAccount).putEncryptedChat(((EncryptedChat)arg5), false);
        MessagesStorage.getInstance(arg4.currentAccount).updateEncryptedChat(((EncryptedChat)arg5));
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{arg5});
    }

    public static void lambda$null$10(SecretChatHelper arg1, long arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$zouLqc6zy27lFYX8g6pcFXGkhsk(arg1, arg2));
    }

    static int lambda$null$12(Message arg0, Message arg1) {
        return AndroidUtilities.compare(arg0.seq_out, arg1.seq_out);
    }

    public static void lambda$null$13(SecretChatHelper arg5, ArrayList arg6) {
        int v1;
        for(v1 = 0; v1 < arg6.size(); ++v1) {
            MessageObject v3 = new MessageObject(arg5.currentAccount, arg6.get(v1), false);
            v3.resendAsIs = true;
            SendMessagesHelper.getInstance(arg5.currentAccount).retrySendMessage(v3, true);
        }
    }

    public static void lambda$null$20(SecretChatHelper arg4, EncryptedChat arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{arg5});
        arg4.sendNotifyLayerMessage(arg5, null);
    }

    public static void lambda$null$21(SecretChatHelper arg2, EncryptedChat arg3, TLObject arg4, TL_error arg5) {
        arg2.acceptingChats.remove(arg3.id);
        if(arg5 == null) {
            ((EncryptedChat)arg4).auth_key = arg3.auth_key;
            ((EncryptedChat)arg4).user_id = arg3.user_id;
            ((EncryptedChat)arg4).seq_in = arg3.seq_in;
            ((EncryptedChat)arg4).seq_out = arg3.seq_out;
            ((EncryptedChat)arg4).key_create_date = arg3.key_create_date;
            ((EncryptedChat)arg4).key_use_count_in = arg3.key_use_count_in;
            ((EncryptedChat)arg4).key_use_count_out = arg3.key_use_count_out;
            MessagesStorage.getInstance(arg2.currentAccount).updateEncryptedChat(((EncryptedChat)arg4));
            MessagesController.getInstance(arg2.currentAccount).putEncryptedChat(((EncryptedChat)arg4), false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$_jYoIAhmqiUWDV4t-Jnx1LLNrA8(arg2, ((EncryptedChat)arg4)));
        }
    }

    static void lambda$null$23(Context arg0, AlertDialog arg1) {
        try {
            if(((Activity)arg0).isFinishing()) {
                return;
            }

            arg1.dismiss();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$null$24(SecretChatHelper arg4) {
        if(!arg4.delayedEncryptedChatUpdates.isEmpty()) {
            MessagesController.getInstance(arg4.currentAccount).processUpdateArray(arg4.delayedEncryptedChatUpdates, null, null, false);
            arg4.delayedEncryptedChatUpdates.clear();
        }
    }

    public static void lambda$null$25(SecretChatHelper arg3, Context arg4, AlertDialog arg5, TLObject arg6, byte[] arg7, User arg8) {
        arg3.startingSecretChat = false;
        if(!((Activity)arg4).isFinishing()) {
            try {
                arg5.dismiss();
            }
            catch(Exception v4) {
                FileLog.e(((Throwable)v4));
            }
        }

        ((EncryptedChat)arg6).user_id = ((EncryptedChat)arg6).participant_id;
        ((EncryptedChat)arg6).seq_in = -2;
        ((EncryptedChat)arg6).seq_out = 1;
        ((EncryptedChat)arg6).a_or_b = arg7;
        MessagesController.getInstance(arg3.currentAccount).putEncryptedChat(((EncryptedChat)arg6), false);
        TL_dialog v5 = new TL_dialog();
        v5.id = (((long)((EncryptedChat)arg6).id)) << 32;
        v5.unread_count = 0;
        v5.top_message = 0;
        v5.last_message_date = ConnectionsManager.getInstance(arg3.currentAccount).getCurrentTime();
        MessagesController.getInstance(arg3.currentAccount).dialogs_dict.put(v5.id, v5);
        MessagesController.getInstance(arg3.currentAccount).dialogs.add(v5);
        MessagesController.getInstance(arg3.currentAccount).sortDialogs(null);
        MessagesStorage.getInstance(arg3.currentAccount).putEncryptedChat(((EncryptedChat)arg6), arg8, v5);
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.encryptedChatCreated, new Object[]{arg6});
        Utilities.stageQueue.postRunnable(new -$$Lambda$SecretChatHelper$PyQ6La6w_4bJYWv3ojsQqv921CM(arg3));
    }

    public static void lambda$null$26(SecretChatHelper arg1, Context arg2, AlertDialog arg3) {
        if(!arg2.isFinishing()) {
            arg1.startingSecretChat = false;
            try {
                arg3.dismiss();
            }
            catch(Exception v3) {
                FileLog.e(((Throwable)v3));
            }

            Builder v3_1 = new Builder(arg2);
            v3_1.setTitle(LocaleController.getString("AppName", 2131624086));
            v3_1.setMessage(LocaleController.getString("CreateEncryptedChatError", 2131624501));
            v3_1.setPositiveButton(LocaleController.getString("OK", 2131625420), null);
            v3_1.show().setCanceledOnTouchOutside(true);
        }
    }

    public static void lambda$null$27(SecretChatHelper arg7, Context arg8, AlertDialog arg9, byte[] arg10, User arg11, TLObject arg12, TL_error arg13) {
        if(arg13 == null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$0tV_MJuVJAhZ10ST8ytcL1B6vB4(arg7, arg8, arg9, arg12, arg10, arg11));
        }
        else {
            arg7.delayedEncryptedChatUpdates.clear();
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$pNz_sskaVL96CTdZukhE4ke04i8(arg7, arg8, arg9));
        }
    }

    public static void lambda$null$28(SecretChatHelper arg1, Context arg2, AlertDialog arg3) {
        arg1.startingSecretChat = false;
        if(!((Activity)arg2).isFinishing()) {
            try {
                arg3.dismiss();
            }
            catch(Exception v2) {
                FileLog.e(((Throwable)v2));
            }
        }
    }

    public static void lambda$null$3(SecretChatHelper arg6, Message arg7, String arg8) {
        arg7.send_state = 0;
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(arg7.id), Integer.valueOf(arg7.id), arg7, Long.valueOf(arg7.dialog_id), Long.valueOf(0)});
        SendMessagesHelper.getInstance(arg6.currentAccount).processSentMessage(arg7.id);
        if((MessageObject.isVideoMessage(arg7)) || (MessageObject.isNewGifMessage(arg7)) || (MessageObject.isRoundVideoMessage(arg7))) {
            SendMessagesHelper.getInstance(arg6.currentAccount).stopVideoService(arg8);
        }

        SendMessagesHelper.getInstance(arg6.currentAccount).removeFromSendingMessages(arg7.id);
    }

    public static void lambda$null$4(SecretChatHelper arg9, Message arg10, messages_SentEncryptedMessage arg11, String arg12) {
        if(SecretChatHelper.isSecretInvisibleMessage(arg10)) {
            arg11.date = 0;
        }

        MessagesStorage.getInstance(arg9.currentAccount).updateMessageStateAndId(arg10.random_id, Integer.valueOf(arg10.id), arg10.id, arg11.date, false, 0);
        AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$yx-4JXXM5arlPU5pjSxBeaB7rlo(arg9, arg10, arg12));
    }

    public static void lambda$null$5(SecretChatHelper arg5, Message arg6) {
        arg6.send_state = 2;
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(arg6.id)});
        SendMessagesHelper.getInstance(arg5.currentAccount).processSentMessage(arg6.id);
        if((MessageObject.isVideoMessage(arg6)) || (MessageObject.isNewGifMessage(arg6)) || (MessageObject.isRoundVideoMessage(arg6))) {
            SendMessagesHelper.getInstance(arg5.currentAccount).stopVideoService(arg6.attachPath);
        }

        SendMessagesHelper.getInstance(arg5.currentAccount).removeFromSendingMessages(arg6.id);
    }

    public static void lambda$null$6(SecretChatHelper arg5, DecryptedMessage arg6, EncryptedChat arg7, Message arg8, MessageObject arg9, String arg10, TLObject arg11, TL_error arg12) {
        if(arg12 == null && ((arg6.action instanceof TL_decryptedMessageActionNotifyLayer))) {
            EncryptedChat v0 = MessagesController.getInstance(arg5.currentAccount).getEncryptedChat(Integer.valueOf(arg7.id));
            if(v0 == null) {
                v0 = arg7;
            }

            if(v0.key_hash == null) {
                v0.key_hash = AndroidUtilities.calcAuthKeyHash(v0.auth_key);
            }

            if(AndroidUtilities.getPeerLayerVersion(v0.layer) >= 46) {
                int v2 = 16;
                if(v0.key_hash.length != v2) {
                    goto label_42;
                }

                try {
                    byte[] v1 = Utilities.computeSHA256(arg7.auth_key, 0, arg7.auth_key.length);
                    byte[] v3 = new byte[36];
                    System.arraycopy(arg7.key_hash, 0, v3, 0, v2);
                    System.arraycopy(v1, 0, v3, v2, 20);
                    v0.key_hash = v3;
                    MessagesStorage.getInstance(arg5.currentAccount).updateEncryptedChat(v0);
                }
                catch(Throwable v7) {
                    FileLog.e(v7);
                }
            }

        label_42:
            arg5.sendingNotifyLayer.remove(Integer.valueOf(v0.id));
            v0.layer = AndroidUtilities.setMyLayerVersion(v0.layer, 73);
            MessagesStorage.getInstance(arg5.currentAccount).updateEncryptedChatLayer(v0);
        }

        if(arg8 != null) {
            if(arg12 == null) {
                String v7_1 = arg8.attachPath;
                if(SecretChatHelper.isSecretVisibleMessage(arg8)) {
                    arg8.date = ((messages_SentEncryptedMessage)arg11).date;
                }

                if(arg9 != null && ((((messages_SentEncryptedMessage)arg11).file instanceof TL_encryptedFile))) {
                    arg5.updateMediaPaths(arg9, ((messages_SentEncryptedMessage)arg11).file, arg6, arg10);
                }

                MessagesStorage.getInstance(arg5.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SecretChatHelper$sd1MIyL1Cjxs8HzbKRfnl2G-KnY(arg5, arg8, ((messages_SentEncryptedMessage)arg11), v7_1));
            }
            else {
                MessagesStorage.getInstance(arg5.currentAccount).markMessageAsSendError(arg8);
                AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$KXmAZjrV-o2jJvsCy8wRyFby6VI(arg5, arg8));
            }
        }
    }

    public static void lambda$null$9(SecretChatHelper arg8, long arg9) {
        NotificationsController.getInstance(arg8.currentAccount).processReadMessages(null, arg9, 0, 2147483647, false);
        LongSparseArray v0 = new LongSparseArray(1);
        v0.put(arg9, Integer.valueOf(0));
        NotificationsController.getInstance(arg8.currentAccount).processDialogsUpdateRead(v0);
    }

    public static void lambda$performSendEncryptedRequest$7(SecretChatHelper arg27, EncryptedChat arg28, DecryptedMessage arg29, Message arg30, InputEncryptedFile arg31, MessageObject arg32, String arg33) {
        TL_messages_sendEncryptedService v9_1;
        TL_messages_sendEncrypted v1_2;
        long v6_3;
        TL_inputEncryptedChat v2_3;
        byte[] v2_2;
        int v6;
        SecretChatHelper v8 = arg27;
        EncryptedChat v0 = arg28;
        DecryptedMessage v3 = arg29;
        Message v5 = arg30;
        InputEncryptedFile v1 = arg31;
        try {
            TL_decryptedMessageLayer v2 = new TL_decryptedMessageLayer();
            v2.layer = Math.min(Math.max(46, AndroidUtilities.getMyLayerVersion(v0.layer)), Math.max(46, AndroidUtilities.getPeerLayerVersion(v0.layer)));
            v2.message = v3;
            v2.random_bytes = new byte[15];
            Utilities.random.nextBytes(v2.random_bytes);
            boolean v7 = true;
            int v9 = 2;
            int v4 = AndroidUtilities.getPeerLayerVersion(v0.layer) >= 73 ? 2 : 1;
            if(v0.seq_in == 0 && v0.seq_out == 0) {
                if(v0.admin_id == UserConfig.getInstance(v8.currentAccount).getClientUserId()) {
                    v0.seq_out = 1;
                    v0.seq_in = -2;
                }
                else {
                    v0.seq_in = -1;
                }
            }

            if(v5.seq_in != 0 || v5.seq_out != 0) {
                v2.in_seq_no = v5.seq_in;
                v2.out_seq_no = v5.seq_out;
            }
            else {
                v6 = v0.seq_in > 0 ? v0.seq_in : v0.seq_in + v9;
                v2.in_seq_no = v6;
                v2.out_seq_no = v0.seq_out;
                v0.seq_out += v9;
                if(AndroidUtilities.getPeerLayerVersion(v0.layer) >= 20) {
                    if(v0.key_create_date == 0) {
                        v0.key_create_date = ConnectionsManager.getInstance(v8.currentAccount).getCurrentTime();
                    }

                    v0.key_use_count_out = ((short)(v0.key_use_count_out + 1));
                    if(v0.key_use_count_out < 100 && v0.key_create_date >= ConnectionsManager.getInstance(v8.currentAccount).getCurrentTime() - 604800) {
                        goto label_94;
                    }

                    long v13 = 0;
                    if(v0.exchange_id != v13) {
                        goto label_94;
                    }

                    if(v0.future_key_fingerprint != v13) {
                        goto label_94;
                    }

                    arg27.requestNewSecretChatKey(arg28);
                }

            label_94:
                MessagesStorage.getInstance(v8.currentAccount).updateEncryptedChatSeq(v0, false);
                if(v5 == null) {
                    goto label_113;
                }

                v5.seq_in = v2.in_seq_no;
                v5.seq_out = v2.out_seq_no;
                MessagesStorage.getInstance(v8.currentAccount).setMessageSeq(v5.id, v5.seq_in, v5.seq_out);
            }

        label_113:
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d(v3 + " send message with in_seq = " + v2.in_seq_no + " out_seq = " + v2.out_seq_no);
            }

            v6 = ((TLObject)v2).getObjectSize();
            NativeByteBuffer v11 = new NativeByteBuffer(v6 + 4);
            v11.writeInt32(v6);
            ((TLObject)v2).serializeToStream(((AbstractSerializedData)v11));
            int v2_1 = v11.length();
            int v12 = 16;
            v6 = v2_1 % 16 != 0 ? 16 - v2_1 % 16 : 0;
            if(v4 == v9) {
                v6 += (Utilities.random.nextInt(3) + v9) * 16;
            }

            NativeByteBuffer v13_1 = new NativeByteBuffer(v2_1 + v6);
            v11.position(0);
            v13_1.writeBytes(v11);
            if(v6 != 0) {
                v2_2 = new byte[v6];
                Utilities.random.nextBytes(v2_2);
                v13_1.writeBytes(v2_2);
            }

            v2_2 = new byte[v12];
            if(v4 != v9 || v0.admin_id == UserConfig.getInstance(v8.currentAccount).getClientUserId()) {
                v7 = false;
            }
            else {
            }

            if(v4 == v9) {
                byte[] v14 = v0.auth_key;
                v9 = v7 ? 8 : 0;
                System.arraycopy(Utilities.computeSHA256(v14, v9 + 88, 32, v13_1.buffer, 0, v13_1.buffer.limit()), 8, v2_2, 0, v12);
            }
            else {
                byte[] v6_2 = Utilities.computeSHA1(v11.buffer);
                System.arraycopy(v6_2, v6_2.length - v12, v2_2, 0, v12);
            }

            v11.reuse();
            MessageKeyData v4_1 = MessageKeyData.generateMessageKeyData(v0.auth_key, v2_2, v7, v4);
            Utilities.aesIgeEncryption(v13_1.buffer, v4_1.aesKey, v4_1.aesIv, true, false, 0, v13_1.limit());
            NativeByteBuffer v4_2 = new NativeByteBuffer(v2_2.length + 8 + v13_1.length());
            v13_1.position(0);
            v4_2.writeInt64(v0.key_fingerprint);
            v4_2.writeBytes(v2_2);
            v4_2.writeBytes(v13_1);
            v13_1.reuse();
            v4_2.position(0);
            if(v1 == null) {
                if((v3 instanceof TL_decryptedMessageService)) {
                    TL_messages_sendEncryptedService v1_1 = new TL_messages_sendEncryptedService();
                    v1_1.data = v4_2;
                    v1_1.random_id = v3.random_id;
                    v1_1.peer = new TL_inputEncryptedChat();
                    v1_1.peer.chat_id = v0.id;
                    v2_3 = v1_1.peer;
                    v6_3 = v0.access_hash;
                }
                else {
                    v1_2 = new TL_messages_sendEncrypted();
                    v1_2.data = v4_2;
                    v1_2.random_id = v3.random_id;
                    v1_2.peer = new TL_inputEncryptedChat();
                    v1_2.peer.chat_id = v0.id;
                    v2_3 = v1_2.peer;
                    v6_3 = v0.access_hash;
                }

                v2_3.access_hash = v6_3;
                v9_1 = ((TL_messages_sendEncryptedService)v1_2);
            }
            else {
                TL_messages_sendEncryptedFile v2_4 = new TL_messages_sendEncryptedFile();
                v2_4.data = v4_2;
                v2_4.random_id = v3.random_id;
                v2_4.peer = new TL_inputEncryptedChat();
                v2_4.peer.chat_id = v0.id;
                v2_4.peer.access_hash = v0.access_hash;
                v2_4.file = v1;
                TL_messages_sendEncryptedFile v9_2 = v2_4;
            }

            ConnectionsManager.getInstance(v8.currentAccount).sendRequest(((TLObject)v9_1), new -$$Lambda$SecretChatHelper$NeIJyTvVk2g1G3EFM6ENqFtjkw0(arg27, arg29, arg28, arg30, arg32, arg33), 64);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public static void lambda$processAcceptedSecretChat$17(SecretChatHelper arg4, EncryptedChat arg5) {
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{arg5});
        arg4.sendNotifyLayerMessage(arg5, null);
    }

    public static void lambda$processAcceptedSecretChat$18(SecretChatHelper arg4, TL_encryptedChatDiscarded arg5) {
        MessagesController.getInstance(arg4.currentAccount).putEncryptedChat(((EncryptedChat)arg5), false);
        NotificationCenter.getInstance(arg4.currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{arg5});
    }

    public static void lambda$processDecryptedObject$11(SecretChatHelper arg5, long arg6) {
        Object v0 = MessagesController.getInstance(arg5.currentAccount).dialogs_dict.get(arg6);
        if(v0 != null) {
            ((TL_dialog)v0).unread_count = 0;
            MessagesController.getInstance(arg5.currentAccount).dialogMessage.remove(((TL_dialog)v0).id);
        }

        MessagesStorage.getInstance(arg5.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SecretChatHelper$kHVODiP7b77JSefUc0MrQJDvu_o(arg5, arg6));
        MessagesStorage.getInstance(arg5.currentAccount).deleteDialog(arg6, 1);
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.removeAllMessagesFromDialog, new Object[]{Long.valueOf(arg6), Boolean.valueOf(false)});
    }

    public static void lambda$processPendingEncMessages$0(SecretChatHelper arg4, ArrayList arg5) {
        int v0;
        for(v0 = 0; v0 < arg5.size(); ++v0) {
            Object v1 = MessagesController.getInstance(arg4.currentAccount).dialogMessagesByRandomIds.get(arg5.get(v0).longValue());
            if(v1 != null) {
                ((MessageObject)v1).deleted = true;
            }
        }
    }

    public static void lambda$processUpdateEncryption$1(SecretChatHelper arg3, TL_dialog arg4) {
        MessagesController.getInstance(arg3.currentAccount).dialogs_dict.put(arg4.id, arg4);
        MessagesController.getInstance(arg3.currentAccount).dialogs.add(arg4);
        MessagesController.getInstance(arg3.currentAccount).sortDialogs(null);
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    public static void lambda$processUpdateEncryption$2(SecretChatHelper arg3, EncryptedChat arg4, EncryptedChat arg5) {
        if(arg4 != null) {
            MessagesController.getInstance(arg3.currentAccount).putEncryptedChat(arg5, false);
        }

        MessagesStorage.getInstance(arg3.currentAccount).updateEncryptedChat(arg5);
        NotificationCenter.getInstance(arg3.currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{arg5});
    }

    public static void lambda$resendMessages$14(SecretChatHelper arg19, int arg20, EncryptedChat arg21, int arg22) {
        long v14_2;
        Message v1_4;
        SparseArray v11_1;
        ArrayList v12_2;
        SecretChatHelper v8 = arg19;
        EncryptedChat v9 = arg21;
        try {
            int v0_1 = v9.admin_id != UserConfig.getInstance(v8.currentAccount).getClientUserId() || arg20 % 2 != 0 ? arg20 : arg20 + 1;
            SQLiteDatabase v1 = MessagesStorage.getInstance(v8.currentAccount).getDatabase();
            Locale v2 = Locale.US;
            Object[] v4 = new Object[5];
            int v11 = 0;
            v4[0] = Integer.valueOf(v9.id);
            int v12 = 1;
            v4[1] = Integer.valueOf(v0_1);
            int v13 = 2;
            v4[v13] = Integer.valueOf(v0_1);
            int v14 = 3;
            v4[v14] = Integer.valueOf(arg22);
            v4[4] = Integer.valueOf(arg22);
            SQLiteCursor v1_1 = v1.b(String.format(v2, "SELECT uid FROM requested_holes WHERE uid = %d AND ((seq_out_start >= %d AND %d <= seq_out_end) OR (seq_out_start >= %d AND %d <= seq_out_end))", v4), new Object[0]);
            boolean v2_1 = v1_1.a();
            v1_1.b();
            if(v2_1) {
                return;
            }

            long v5 = (((long)v9.id)) << 32;
            SparseArray v7 = new SparseArray();
            ArrayList v4_1 = new ArrayList();
            int v3 = arg22;
            int v1_2;
            for(v1_2 = v0_1; v1_2 < v3; v1_2 += 2) {
                v7.put(v1_2, null);
            }

            v1 = MessagesStorage.getInstance(v8.currentAccount).getDatabase();
            v2 = Locale.US;
            Object[] v10 = new Object[v14];
            v10[0] = Long.valueOf(v5);
            v10[1] = Integer.valueOf(v0_1);
            v10[v13] = Integer.valueOf(arg22);
            SQLiteCursor v10_1 = v1.b(String.format(v2, "SELECT m.data, r.random_id, s.seq_in, s.seq_out, m.ttl, s.mid FROM messages_seq as s LEFT JOIN randoms as r ON r.mid = s.mid LEFT JOIN messages as m ON m.mid = s.mid WHERE m.uid = %d AND m.out = 1 AND s.seq_out >= %d AND s.seq_out <= %d ORDER BY seq_out ASC", v10), new Object[0]);
            while(v10_1.a()) {
                long v1_3 = v10_1.d(v12);
                if(v1_3 == 0) {
                    v1_3 = Utilities.random.nextLong();
                }

                int v15 = v10_1.b(v13);
                v13 = v10_1.b(v14);
                int v16 = v10_1.b(5);
                NativeByteBuffer v12_1 = v10_1.g(v11);
                if(v12_1 != null) {
                    Message v14_1 = Message.TLdeserialize(((AbstractSerializedData)v12_1), v12_1.readInt32(((boolean)v11)), ((boolean)v11));
                    v14_1.readAttachPath(((AbstractSerializedData)v12_1), UserConfig.getInstance(v8.currentAccount).clientUserId);
                    v12_1.reuse();
                    v14_1.random_id = v1_3;
                    v14_1.dialog_id = v5;
                    v14_1.seq_in = v15;
                    v14_1.seq_out = v13;
                    v14_1.ttl = v10_1.b(4);
                    v12_2 = v4_1;
                    v11_1 = v7;
                    v1_4 = v14_1;
                    v14_2 = v5;
                }
                else {
                    v12_2 = v4_1;
                    int v4_2 = v15;
                    v14_2 = v5;
                    v11_1 = v7;
                    v1_4 = arg19.createDeleteMessage(v16, v13, v4_2, v1_3, arg21);
                }

                v12_2.add(v1_4);
                v11_1.remove(v13);
                v7 = v11_1;
                v4_1 = v12_2;
                v5 = v14_2;
                v11 = 0;
                v12 = 1;
                v13 = 2;
                v14 = 3;
            }

            v12_2 = v4_1;
            v11_1 = v7;
            v10_1.b();
            if(v11_1.size() != 0) {
                int v10_2;
                for(v10_2 = 0; v10_2 < v11_1.size(); ++v10_2) {
                    v12_2.add(arg19.createDeleteMessage(UserConfig.getInstance(v8.currentAccount).getNewMessageId(), v11_1.keyAt(v10_2), 0, Utilities.random.nextLong(), arg21));
                }

                UserConfig.getInstance(v8.currentAccount).saveConfig(false);
            }

            Collections.sort(((List)v12_2), -$$Lambda$SecretChatHelper$O20rGLXtuSs3IvLeQHrDUrNritc.INSTANCE);
            ArrayList v1_5 = new ArrayList();
            v1_5.add(v9);
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$VQkryblefkb-35jNwuFnQ2KSKkw(v8, v12_2));
            SendMessagesHelper.getInstance(v8.currentAccount).processUnsentMessages(v12_2, new ArrayList(), new ArrayList(), v1_5);
            MessagesStorage.getInstance(v8.currentAccount).getDatabase().a(String.format(Locale.US, "REPLACE INTO requested_holes VALUES(%d, %d, %d)", Integer.valueOf(v9.id), Integer.valueOf(v0_1), Integer.valueOf(arg22))).c().e();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$startSecretChat$29(SecretChatHelper arg7, Context arg8, AlertDialog arg9, User arg10, TLObject arg11, TL_error arg12) {
        if(arg12 == null) {
            TLObject v12 = arg11;
            if((arg11 instanceof TL_messages_dhConfig)) {
                if(!Utilities.isGoodPrime(((messages_DhConfig)v12).p, ((messages_DhConfig)v12).g)) {
                    AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$V-xN6On4v0MEOO6-YoYKk7r9DOk(arg8, arg9));
                    return;
                }
                else {
                    MessagesStorage.getInstance(arg7.currentAccount).setSecretPBytes(((messages_DhConfig)v12).p);
                    MessagesStorage.getInstance(arg7.currentAccount).setSecretG(((messages_DhConfig)v12).g);
                    MessagesStorage.getInstance(arg7.currentAccount).setLastSecretVersion(((messages_DhConfig)v12).version);
                    MessagesStorage.getInstance(arg7.currentAccount).saveSecretParams(MessagesStorage.getInstance(arg7.currentAccount).getLastSecretVersion(), MessagesStorage.getInstance(arg7.currentAccount).getSecretG(), MessagesStorage.getInstance(arg7.currentAccount).getSecretPBytes());
                }
            }

            int v11 = 256;
            byte[] v4 = new byte[v11];
            int v1;
            for(v1 = 0; v1 < v11; ++v1) {
                v4[v1] = ((byte)((((byte)(((int)(Utilities.random.nextDouble() * 256))))) ^ ((messages_DhConfig)v12).random[v1]));
            }

            byte[] v12_1 = BigInteger.valueOf(((long)MessagesStorage.getInstance(arg7.currentAccount).getSecretG())).modPow(new BigInteger(1, v4), new BigInteger(1, MessagesStorage.getInstance(arg7.currentAccount).getSecretPBytes())).toByteArray();
            if(v12_1.length > v11) {
                byte[] v1_1 = new byte[v11];
                System.arraycopy(v12_1, 1, v1_1, 0, v11);
                v12_1 = v1_1;
            }

            TL_messages_requestEncryption v11_1 = new TL_messages_requestEncryption();
            v11_1.g_a = v12_1;
            v11_1.user_id = MessagesController.getInstance(arg7.currentAccount).getInputUser(arg10);
            v11_1.random_id = Utilities.random.nextInt();
            ConnectionsManager.getInstance(arg7.currentAccount).sendRequest(((TLObject)v11_1), new -$$Lambda$SecretChatHelper$BEU71I5KzZcukvSdHQ6G9fRsUbQ(arg7, arg8, arg9, v4, arg10), 2);
        }
        else {
            arg7.delayedEncryptedChatUpdates.clear();
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$TZa6lzq7a-IHyrpxBJVmRAmKQE8(arg7, arg8, arg9));
        }
    }

    public static void lambda$startSecretChat$30(SecretChatHelper arg1, int arg2, DialogInterface arg3, int arg4) {
        ConnectionsManager.getInstance(arg1.currentAccount).cancelRequest(arg2, true);
        try {
            arg3.dismiss();
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    protected void performSendEncryptedRequest(DecryptedMessage arg12, Message arg13, EncryptedChat arg14, InputEncryptedFile arg15, String arg16, MessageObject arg17) {
        EncryptedChat v2 = arg14;
        if(arg12 != null && v2.auth_key != null && !(v2 instanceof TL_encryptedChatRequested)) {
            if((v2 instanceof TL_encryptedChatWaiting)) {
            }
            else {
                SendMessagesHelper.getInstance(this.currentAccount).putToSendingMessages(arg13);
                Utilities.stageQueue.postRunnable(new -$$Lambda$SecretChatHelper$go4ClJO8kMeuzFvRQpPn8-EmO40(this, arg14, arg12, arg13, arg15, arg17, arg16));
                return;
            }
        }
    }

    protected void performSendEncryptedRequest(TL_messages_sendEncryptedMultiMedia arg10, DelayedMessage arg11) {
        int v0;
        for(v0 = 0; v0 < arg10.files.size(); ++v0) {
            this.performSendEncryptedRequest(arg10.messages.get(v0), arg11.messages.get(v0), arg11.encryptedChat, arg10.files.get(v0), arg11.originalPaths.get(v0), arg11.messageObjects.get(v0));
        }
    }

    public void processAcceptedSecretChat(EncryptedChat arg9) {
        byte[] v1_1;
        BigInteger v0 = new BigInteger(1, MessagesStorage.getInstance(this.currentAccount).getSecretPBytes());
        BigInteger v1 = new BigInteger(1, arg9.g_a_or_b);
        if(!Utilities.isGoodGaAndGb(v1, v0)) {
            this.declineSecretChat(arg9.id);
            return;
        }

        byte[] v0_1 = v1.modPow(new BigInteger(1, arg9.a_or_b), v0).toByteArray();
        int v4 = 256;
        if(v0_1.length > v4) {
            v1_1 = new byte[v4];
            System.arraycopy(v0_1, v0_1.length - v4, v1_1, 0, v4);
        }
        else if(v0_1.length < v4) {
            v1_1 = new byte[v4];
            System.arraycopy(v0_1, 0, v1_1, 256 - v0_1.length, v0_1.length);
            int v5;
            for(v5 = 0; v5 < 256 - v0_1.length; ++v5) {
                v1_1[v5] = 0;
            }
        }
        else {
            v1_1 = v0_1;
        }

        v0_1 = Utilities.computeSHA1(v1_1);
        byte[] v5_1 = new byte[8];
        System.arraycopy(v0_1, v0_1.length - 8, v5_1, 0, 8);
        if(arg9.key_fingerprint == Utilities.bytesToLong(v5_1)) {
            arg9.auth_key = v1_1;
            arg9.key_create_date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            arg9.seq_in = -2;
            arg9.seq_out = 1;
            MessagesStorage.getInstance(this.currentAccount).updateEncryptedChat(arg9);
            MessagesController.getInstance(this.currentAccount).putEncryptedChat(arg9, false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$tDKre2aQQBiVO0S8VAHIlXCNFCM(this, arg9));
        }
        else {
            TL_encryptedChatDiscarded v0_2 = new TL_encryptedChatDiscarded();
            v0_2.id = arg9.id;
            v0_2.user_id = arg9.user_id;
            v0_2.auth_key = arg9.auth_key;
            v0_2.key_create_date = arg9.key_create_date;
            v0_2.key_use_count_in = arg9.key_use_count_in;
            v0_2.key_use_count_out = arg9.key_use_count_out;
            v0_2.seq_in = arg9.seq_in;
            v0_2.seq_out = arg9.seq_out;
            v0_2.admin_id = arg9.admin_id;
            v0_2.mtproto_seq = arg9.mtproto_seq;
            MessagesStorage.getInstance(this.currentAccount).updateEncryptedChat(((EncryptedChat)v0_2));
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$D9qtWTcc8U_wHAaEgu6hZuZwoaE(this, v0_2));
            this.declineSecretChat(arg9.id);
        }
    }

    public Message processDecryptedObject(EncryptedChat arg17, EncryptedFile arg18, int arg19, TLObject arg20, boolean arg21) {
        // Method was not decompiled
    }

    protected void processPendingEncMessages() {
        if(!this.pendingEncMessagesToDelete.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$_doi6epvDK7bEAjlIQHt5tAd_wU(this, new ArrayList(this.pendingEncMessagesToDelete)));
            MessagesStorage.getInstance(this.currentAccount).markMessagesAsDeletedByRandoms(new ArrayList(this.pendingEncMessagesToDelete));
            this.pendingEncMessagesToDelete.clear();
        }
    }

    protected void processUpdateEncryption(TL_updateEncryption arg8, ConcurrentHashMap arg9) {
        EncryptedChat v0 = arg8.chat;
        long v1 = (((long)v0.id)) << 32;
        EncryptedChat v3 = MessagesController.getInstance(this.currentAccount).getEncryptedChatDB(v0.id, false);
        if(!(v0 instanceof TL_encryptedChatRequested) || v3 != null) {
            if((v0 instanceof TL_encryptedChat)) {
                if(v3 != null && ((v3 instanceof TL_encryptedChatWaiting)) && (v3.auth_key == null || v3.auth_key.length == 1)) {
                    v0.a_or_b = v3.a_or_b;
                    v0.user_id = v3.user_id;
                    this.processAcceptedSecretChat(v0);
                    return;
                }

                if(v3 != null) {
                    return;
                }

                if(!this.startingSecretChat) {
                    return;
                }

                this.delayedEncryptedChatUpdates.add(arg8);
                return;
            }

            if(v3 != null) {
                v0.user_id = v3.user_id;
                v0.auth_key = v3.auth_key;
                v0.key_create_date = v3.key_create_date;
                v0.key_use_count_in = v3.key_use_count_in;
                v0.key_use_count_out = v3.key_use_count_out;
                v0.ttl = v3.ttl;
                v0.seq_in = v3.seq_in;
                v0.seq_out = v3.seq_out;
                v0.admin_id = v3.admin_id;
                v0.mtproto_seq = v3.mtproto_seq;
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$G9V6FvkI-PnA0UumuoB_kxH2lOM(this, v3, v0));
        }
        else {
            int v3_1 = v0.participant_id;
            if(v3_1 == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                v3_1 = v0.admin_id;
            }

            User v4 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(v3_1));
            if(v4 == null) {
                Object v4_1 = arg9.get(Integer.valueOf(v3_1));
            }

            v0.user_id = v3_1;
            TL_dialog v9 = new TL_dialog();
            v9.id = v1;
            v9.unread_count = 0;
            v9.top_message = 0;
            v9.last_message_date = arg8.date;
            MessagesController.getInstance(this.currentAccount).putEncryptedChat(v0, false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$SecretChatHelper$vOnfmIg19WlGNOBT5lCtBdPmXEA(this, v9));
            MessagesStorage.getInstance(this.currentAccount).putEncryptedChat(v0, v4, v9);
            this.acceptSecretChat(v0);
        }
    }

    public void requestNewSecretChatKey(EncryptedChat arg8) {
        if(AndroidUtilities.getPeerLayerVersion(arg8.layer) < 20) {
            return;
        }

        int v0 = 256;
        byte[] v1 = new byte[v0];
        Utilities.random.nextBytes(v1);
        byte[] v2 = BigInteger.valueOf(((long)MessagesStorage.getInstance(this.currentAccount).getSecretG())).modPow(new BigInteger(1, v1), new BigInteger(1, MessagesStorage.getInstance(this.currentAccount).getSecretPBytes())).toByteArray();
        if(v2.length > v0) {
            byte[] v3 = new byte[v0];
            System.arraycopy(v2, 1, v3, 0, v0);
            v2 = v3;
        }

        arg8.exchange_id = SendMessagesHelper.getInstance(this.currentAccount).getNextRandomId();
        arg8.a_or_b = v1;
        arg8.g_a = v2;
        MessagesStorage.getInstance(this.currentAccount).updateEncryptedChat(arg8);
        this.sendRequestKeyMessage(arg8, null);
    }

    private void resendMessages(int arg3, int arg4, EncryptedChat arg5) {
        if(arg5 != null) {
            if(arg4 - arg3 < 0) {
            }
            else {
                MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SecretChatHelper$UeLgl-NG4gDOs_Y4sJhukTdyyjM(this, arg3, arg5, arg4));
            }
        }
    }

    public void sendAbortKeyMessage(EncryptedChat arg9, Message arg10, long arg11) {
        TL_messageService v10;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg10 != null) {
            v2.action = arg10.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionAbortKey();
            v2.action.exchange_id = arg11;
            v10 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v10);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendAcceptKeyMessage(EncryptedChat arg9, Message arg10) {
        TL_messageService v10;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg10 != null) {
            v2.action = arg10.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionAcceptKey();
            v2.action.exchange_id = arg9.exchange_id;
            v2.action.key_fingerprint = arg9.future_key_fingerprint;
            v2.action.g_b = arg9.g_a_or_b;
            v10 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v10);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendClearHistoryMessage(EncryptedChat arg9, Message arg10) {
        TL_messageService v10;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg10 != null) {
            v2.action = arg10.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionFlushHistory();
            v10 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v10);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendCommitKeyMessage(EncryptedChat arg9, Message arg10) {
        TL_messageService v10;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg10 != null) {
            v2.action = arg10.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionCommitKey();
            v2.action.exchange_id = arg9.exchange_id;
            v2.action.key_fingerprint = arg9.future_key_fingerprint;
            v10 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v10);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendMessagesDeleteMessage(EncryptedChat arg9, ArrayList arg10, Message arg11) {
        TL_messageService v11;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg11 != null) {
            v2.action = arg11.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionDeleteMessages();
            v2.action.random_ids = arg10;
            v11 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v11);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendMessagesReadMessage(EncryptedChat arg9, ArrayList arg10, Message arg11) {
        TL_messageService v11;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg11 != null) {
            v2.action = arg11.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionReadMessages();
            v2.action.random_ids = arg10;
            v11 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v11);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendNoopMessage(EncryptedChat arg9, Message arg10) {
        TL_messageService v10;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg10 != null) {
            v2.action = arg10.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionNoop();
            v10 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v10);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendNotifyLayerMessage(EncryptedChat arg10, Message arg11) {
        TL_messageService v11;
        if(!(arg10 instanceof TL_encryptedChat)) {
            return;
        }

        if(this.sendingNotifyLayer.contains(Integer.valueOf(arg10.id))) {
            return;
        }

        this.sendingNotifyLayer.add(Integer.valueOf(arg10.id));
        TL_decryptedMessageService v3 = new TL_decryptedMessageService();
        if(arg11 != null) {
            v3.action = arg11.action.encryptedAction;
        }
        else {
            v3.action = new TL_decryptedMessageActionNotifyLayer();
            v3.action.layer = 73;
            v11 = this.createServiceSecretMessage(arg10, v3.action);
        }

        Message v4 = ((Message)v11);
        v3.random_id = v4.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v3), v4, arg10, null, null, null);
    }

    public void sendRequestKeyMessage(EncryptedChat arg9, Message arg10) {
        TL_messageService v10;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg10 != null) {
            v2.action = arg10.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionRequestKey();
            v2.action.exchange_id = arg9.exchange_id;
            v2.action.g_a = arg9.g_a;
            v10 = this.createServiceSecretMessage(arg9, v2.action);
        }

        Message v3 = ((Message)v10);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendScreenshotMessage(EncryptedChat arg9, ArrayList arg10, Message arg11) {
        TL_messageService v11;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg11 != null) {
            v2.action = arg11.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionScreenshotMessages();
            v2.action.random_ids = arg10;
            v11 = this.createServiceSecretMessage(arg9, v2.action);
            MessageObject v10 = new MessageObject(this.currentAccount, ((Message)v11), false);
            v10.messageOwner.send_state = 1;
            ArrayList v0 = new ArrayList();
            v0.add(v10);
            MessagesController.getInstance(this.currentAccount).updateInterfaceWithMessages(((Message)v11).dialog_id, v0);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }

        Message v3 = ((Message)v11);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void sendTTLMessage(EncryptedChat arg9, Message arg10) {
        TL_messageService v10;
        if(!(arg9 instanceof TL_encryptedChat)) {
            return;
        }

        TL_decryptedMessageService v2 = new TL_decryptedMessageService();
        if(arg10 != null) {
            v2.action = arg10.action.encryptedAction;
        }
        else {
            v2.action = new TL_decryptedMessageActionSetMessageTTL();
            v2.action.ttl_seconds = arg9.ttl;
            v10 = this.createServiceSecretMessage(arg9, v2.action);
            MessageObject v0 = new MessageObject(this.currentAccount, ((Message)v10), false);
            v0.messageOwner.send_state = 1;
            ArrayList v1 = new ArrayList();
            v1.add(v0);
            MessagesController.getInstance(this.currentAccount).updateInterfaceWithMessages(((Message)v10).dialog_id, v1);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        }

        Message v3 = ((Message)v10);
        v2.random_id = v3.random_id;
        this.performSendEncryptedRequest(((DecryptedMessage)v2), v3, arg9, null, null, null);
    }

    public void startSecretChat(Context arg5, User arg6) {
        if(arg6 != null) {
            if(arg5 == null) {
            }
            else {
                this.startingSecretChat = true;
                AlertDialog v1 = new AlertDialog(arg5, 1);
                v1.setMessage(LocaleController.getString("Loading", 2131625103));
                v1.setCanceledOnTouchOutside(false);
                v1.setCancelable(false);
                TL_messages_getDhConfig v0 = new TL_messages_getDhConfig();
                v0.random_length = 256;
                v0.version = MessagesStorage.getInstance(this.currentAccount).getLastSecretVersion();
                v1.setButton(-2, LocaleController.getString("Cancel", 2131624257), new -$$Lambda$SecretChatHelper$RjgoKgglnJyCh8-o6WnS5F9uOGs(this, ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new -$$Lambda$SecretChatHelper$CyJGexOzIKpecJNn32s1dWnqMhg(this, arg5, v1, arg6), 2)));
                try {
                    v1.show();
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }
    }

    private void updateMediaPaths(MessageObject arg10, EncryptedFile arg11, DecryptedMessage arg12, String arg13) {
        Message v13 = arg10.messageOwner;
        if(arg11 != null) {
            int v1 = 4;
            if(((v13.media instanceof TL_messageMediaPhoto)) && v13.media.photo != null) {
                Object v10 = v13.media.photo.sizes.get(v13.media.photo.sizes.size() - 1);
                String v0_1 = ((PhotoSize)v10).location.volume_id + "_" + ((PhotoSize)v10).location.local_id;
                ((PhotoSize)v10).location = new TL_fileEncryptedLocation();
                ((PhotoSize)v10).location.key = arg12.media.key;
                ((PhotoSize)v10).location.iv = arg12.media.iv;
                ((PhotoSize)v10).location.dc_id = arg11.dc_id;
                ((PhotoSize)v10).location.volume_id = arg11.id;
                ((PhotoSize)v10).location.secret = arg11.access_hash;
                ((PhotoSize)v10).location.local_id = arg11.key_fingerprint;
                String v11_1 = ((PhotoSize)v10).location.volume_id + "_" + ((PhotoSize)v10).location.local_id;
                File v1_1 = FileLoader.getDirectory(v1);
                StringBuilder v3 = new StringBuilder();
                v3.append(v0_1);
                v3.append(".jpg");
                new File(v1_1, v3.toString()).renameTo(FileLoader.getPathToAttach(((TLObject)v10)));
                ImageLoader.getInstance().replaceImageInCache(v0_1, v11_1, ((PhotoSize)v10).location, true);
                ArrayList v4 = new ArrayList();
                v4.add(v13);
                MessagesStorage.getInstance(this.currentAccount).putMessages(v4, false, true, false, 0);
                return;
            }

            if(!(v13.media instanceof TL_messageMediaDocument)) {
                return;
            }

            if(v13.media.document == null) {
                return;
            }

            Document v0_2 = v13.media.document;
            v13.media.document = new TL_documentEncrypted();
            v13.media.document.id = arg11.id;
            v13.media.document.access_hash = arg11.access_hash;
            v13.media.document.date = v0_2.date;
            v13.media.document.attributes = v0_2.attributes;
            v13.media.document.mime_type = v0_2.mime_type;
            v13.media.document.size = arg11.size;
            v13.media.document.key = arg12.media.key;
            v13.media.document.iv = arg12.media.iv;
            v13.media.document.thumb = v0_2.thumb;
            v13.media.document.dc_id = arg11.dc_id;
            if(v13.attachPath != null && (v13.attachPath.startsWith(FileLoader.getDirectory(v1).getAbsolutePath())) && (new File(v13.attachPath).renameTo(FileLoader.getPathToAttach(v13.media.document)))) {
                arg10.mediaExists = arg10.attachPathExists;
                arg10.attachPathExists = false;
                v13.attachPath = "";
            }

            ArrayList v1_2 = new ArrayList();
            v1_2.add(v13);
            MessagesStorage.getInstance(this.currentAccount).putMessages(v1_2, false, true, false, 0);
        }
    }
}

