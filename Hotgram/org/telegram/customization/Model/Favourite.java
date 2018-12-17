package org.telegram.customization.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$TL_message;

public class Favourite {
    class PairDialogMessage {
        private long dialogId;
        private long messageId;

        public PairDialogMessage(long arg1, long arg3) {
            super();
            this.setDialogId(arg1);
            this.setMessageId(arg3);
        }

        public boolean equals(Object arg6) {
            boolean v6 = !(arg6 instanceof PairDialogMessage) || ((PairDialogMessage)arg6).getDialogId() != this.getDialogId() || ((PairDialogMessage)arg6).getMessageId() != this.getMessageId() ? false : true;
            return v6;
        }

        public long getDialogId() {
            return this.dialogId;
        }

        public long getMessageId() {
            return this.messageId;
        }

        public int hashCode() {
            StringBuilder v0 = new StringBuilder();
            v0.append("");
            v0.append(this.dialogId);
            v0.append(",");
            v0.append(this.messageId);
            return v0.toString().hashCode();
        }

        public void setDialogId(long arg1) {
            this.dialogId = arg1;
        }

        public void setMessageId(long arg1) {
            this.messageId = arg1;
        }
    }

    static boolean canCache;
    long chat_id;
    long cloud_id;
    static Map favoriteCache;
    static ArrayList favouritesIds;
    long id;
    private long msg_id;

    static {
        Favourite.favoriteCache = new HashMap();
        Favourite.canCache = false;
    }

    public Favourite(long arg3, long arg5, long arg7) {
        super();
        this.cloud_id = 0;
        this.id = arg3;
        this.chat_id = arg5;
        this.msg_id = arg7;
    }

    public Favourite(long arg3) {
        super();
        this.cloud_id = 0;
        this.chat_id = arg3;
    }

    public Favourite(long arg3, long arg5, long arg7, long arg9) {
        super();
        this.cloud_id = 0;
        this.id = arg3;
        this.chat_id = arg5;
        this.msg_id = arg7;
        this.cloud_id = arg9;
    }

    public Favourite() {
        super();
        this.cloud_id = 0;
    }

    public Favourite(long arg3, long arg5) {
        super();
        this.cloud_id = 0;
        this.chat_id = arg3;
        this.msg_id = arg5;
    }

    public static void addFavourite(Long arg3) {
        ApplicationLoader.databaseHandler.a(new Favourite(arg3.longValue()));
        Favourite.purgeFavouritesCache();
    }

    public static void addFavouriteMessage(Long arg2, long arg3, MessageObject arg5) {
        if(arg5 == null) {
            ApplicationLoader.databaseHandler.b(new Favourite(arg2.longValue(), arg3));
        }
        else {
            ArrayList v0 = new ArrayList();
            v0.add(arg5);
            NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(new NotificationCenterDelegate(arg2, arg3) {
                boolean isOK;

                public void didReceivedNotification(int arg10, int arg11, Object[] arg12) {
                    if(arg10 == NotificationCenter.messageReceivedByServer) {
                        arg10 = 2;
                        try {
                            Object v10_1 = arg12[arg10];
                            if(this.isOK) {
                                return;
                            }

                            this.isOK = true;
                            ApplicationLoader.databaseHandler.b(new Favourite(0, this.val$dialogId.longValue(), this.val$msgId, ((long)((TL_message)v10_1).id)));
                        }
                        catch(Exception v10) {
                            v10.printStackTrace();
                        }
                    }
                }
            }, NotificationCenter.messageReceivedByServer);
            SendMessagesHelper.getInstance(UserConfig.selectedAccount).sendMessage(v0, ((long)UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()));
        }
    }

    public static void canCacheFavoriteFiles(boolean arg0) {
        Favourite.canCache = arg0;
    }

    public static void clearCache() {
        Favourite.favoriteCache.clear();
        Favourite.canCacheFavoriteFiles(false);
    }

    public static void deleteFavourite(Long arg1) {
        ApplicationLoader.databaseHandler.a(arg1);
        Favourite.purgeFavouritesCache();
    }

    public static void deleteFavouriteMessage(Long arg7, Long arg8) {
        Favourite v0 = ApplicationLoader.databaseHandler.a(arg7, arg8.longValue());
        if(v0 == null || v0.cloud_id > 0) {
            ArrayList v2 = new ArrayList();
            v2.add(Integer.valueOf(((int)v0.cloud_id)));
            MessagesController.getInstance(UserConfig.selectedAccount).deleteMessages(v2, null, null, 0, true);
        }
        else {
            ApplicationLoader.databaseHandler.a(arg7, arg8);
        }
    }

    public static void deleteFavouritesInternal(ArrayList arg1) {
        ApplicationLoader.databaseHandler.a(arg1);
    }

    public long getChatID() {
        return this.chat_id;
    }

    public long getCloudId() {
        return this.cloud_id;
    }

    public static ArrayList getFavorites() {
        ArrayList v1_1;
        ArrayList v0 = new ArrayList();
        try {
            v1_1 = ApplicationLoader.databaseHandler.a();
        }
        catch(Exception v1) {
            goto label_13;
        }

        try {
            Collections.reverse(((List)v1_1));
            return v1_1;
        }
        catch(Exception v0_1) {
            ArrayList v3 = v1_1;
            v1 = v0_1;
            v0 = v3;
        }

    label_13:
        FileLog.e("tmessages", ((Throwable)v1));
        return v0;
    }

    public static ArrayList getFavouriteIds() {
        if(Favourite.favouritesIds == null) {
            List v0 = ApplicationLoader.databaseHandler.c();
            Favourite.favouritesIds = new ArrayList();
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                Favourite.favouritesIds.add(Long.valueOf(v0_1.next().getChatID()));
            }
        }

        return Favourite.favouritesIds;
    }

    public long getID() {
        return this.id;
    }

    public long getMsg_id() {
        return this.msg_id;
    }

    public static boolean hasFavoriteMessage() {
        try {
            return ApplicationLoader.databaseHandler.b();
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static boolean isFavourite(Long arg4) {
        try {
            if(ApplicationLoader.databaseHandler.a(arg4.longValue()) != null) {
                return 1;
            }
        }
        catch(Exception v4) {
            FileLog.e("tmessages", ((Throwable)v4));
            return 0;
        }

        return 0;
    }

    public static boolean isFavouriteMessage(Long arg0, long arg1) {
        return 0;
    }

    public static boolean isFavouriteMessageCache(MessageObject arg5) {
        if(!Favourite.canCache) {
            return 0;
        }

        PairDialogMessage v0 = new PairDialogMessage(arg5.messageOwner.dialog_id, ((long)arg5.messageOwner.id));
        if(Favourite.favoriteCache.containsKey(v0)) {
            return Favourite.favoriteCache.get(v0).booleanValue();
        }

        boolean v5 = Favourite.isFavouriteMessage(Long.valueOf(v0.getDialogId()), v0.getMessageId());
        Favourite.favoriteCache.put(v0, Boolean.valueOf(v5));
        return v5;
    }

    public static void purgeFavouritesCache() {
        Favourite.favouritesIds = null;
    }

    public void setChatID(long arg1) {
        this.chat_id = arg1;
    }

    public void setCloudId(long arg1) {
        this.cloud_id = arg1;
    }

    public void setID(long arg1) {
        this.id = arg1;
    }

    public void setMsg_id(long arg1) {
        this.msg_id = arg1;
    }
}

