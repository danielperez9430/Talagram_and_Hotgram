package org.telegram.tgnet;

import android.util.SparseArray;
import org.telegram.messenger.FileLog;

public class TLClassStore {
    private SparseArray classStore;
    static TLClassStore store;

    static {
    }

    public TLClassStore() {
        super();
        this.classStore = new SparseArray();
        this.classStore.put(TL_error.constructor, TL_error.class);
        this.classStore.put(TL_decryptedMessageService.constructor, TL_decryptedMessageService.class);
        this.classStore.put(TL_decryptedMessage.constructor, TL_decryptedMessage.class);
        this.classStore.put(TL_config.constructor, TL_config.class);
        this.classStore.put(TL_decryptedMessageLayer.constructor, TL_decryptedMessageLayer.class);
        this.classStore.put(TL_decryptedMessage_layer17.constructor, TL_decryptedMessage.class);
        this.classStore.put(TL_decryptedMessage_layer45.constructor, TL_decryptedMessage_layer45.class);
        this.classStore.put(TL_decryptedMessageService_layer8.constructor, TL_decryptedMessageService_layer8.class);
        this.classStore.put(TL_decryptedMessage_layer8.constructor, TL_decryptedMessage_layer8.class);
        this.classStore.put(TL_message_secret.constructor, TL_message_secret.class);
        this.classStore.put(TL_message_secret_layer72.constructor, TL_message_secret_layer72.class);
        this.classStore.put(TL_message_secret_old.constructor, TL_message_secret_old.class);
        this.classStore.put(TL_messageEncryptedAction.constructor, TL_messageEncryptedAction.class);
        this.classStore.put(TL_null.constructor, TL_null.class);
        this.classStore.put(TL_updateShortChatMessage.constructor, TL_updateShortChatMessage.class);
        this.classStore.put(TL_updates.constructor, TL_updates.class);
        this.classStore.put(TL_updateShortMessage.constructor, TL_updateShortMessage.class);
        this.classStore.put(TL_updateShort.constructor, TL_updateShort.class);
        this.classStore.put(TL_updatesCombined.constructor, TL_updatesCombined.class);
        this.classStore.put(TL_updateShortSentMessage.constructor, TL_updateShortSentMessage.class);
        this.classStore.put(TL_updatesTooLong.constructor, TL_updatesTooLong.class);
    }

    public static TLClassStore Instance() {
        if(TLClassStore.store == null) {
            TLClassStore.store = new TLClassStore();
        }

        return TLClassStore.store;
    }

    public TLObject TLdeserialize(NativeByteBuffer arg2, int arg3, boolean arg4) {
        Object v3 = this.classStore.get(arg3);
        TLObject v0 = null;
        if(v3 != null) {
            try {
                v3 = ((Class)v3).newInstance();
                ((TLObject)v3).readParams(((AbstractSerializedData)arg2), arg4);
                return ((TLObject)v3);
            }
            catch(Throwable v2) {
                FileLog.e(v2);
            }
        }

        return v0;
    }
}

