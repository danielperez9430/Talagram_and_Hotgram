package org.telegram.ui.Adapters;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$ChannelParticipantsFilter;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$TL_channelParticipantsBanned;
import org.telegram.tgnet.TLRPC$TL_channelParticipantsKicked;
import org.telegram.tgnet.TLRPC$TL_channelParticipantsSearch;
import org.telegram.tgnet.TLRPC$TL_channels_channelParticipants;
import org.telegram.tgnet.TLRPC$TL_channels_getParticipants;
import org.telegram.tgnet.TLRPC$TL_contacts_found;
import org.telegram.tgnet.TLRPC$TL_contacts_search;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$User;

public class SearchAdapterHelper {
    public final class DialogSearchResult {
        public int date;
        public CharSequence name;
        public TLObject object;

        protected DialogSearchResult() {
            super();
        }
    }

    public class HashtagObject {
        int date;
        String hashtag;

        public HashtagObject() {
            super();
        }
    }

    public interface SearchAdapterHelperDelegate {
        void onDataSetChanged();

        void onSetHashtags(ArrayList arg1, HashMap arg2);
    }

    private boolean allResultsAreGlobal;
    private int channelLastReqId;
    private int channelLastReqId2;
    private int channelReqId;
    private int channelReqId2;
    private int currentAccount;
    private SearchAdapterHelperDelegate delegate;
    private ArrayList globalSearch;
    private SparseArray globalSearchMap;
    private ArrayList groupSearch;
    private ArrayList groupSearch2;
    private ArrayList hashtags;
    private HashMap hashtagsByText;
    private boolean hashtagsLoadedFromDb;
    private String lastFoundChannel;
    private String lastFoundChannel2;
    private String lastFoundUsername;
    private int lastReqId;
    private ArrayList localSearchResults;
    private ArrayList localServerSearch;
    private int reqId;

    public SearchAdapterHelper(boolean arg3) {
        super();
        this.reqId = 0;
        this.lastFoundUsername = null;
        this.globalSearch = new ArrayList();
        this.localServerSearch = new ArrayList();
        this.globalSearchMap = new SparseArray();
        this.groupSearch = new ArrayList();
        this.groupSearch2 = new ArrayList();
        this.currentAccount = UserConfig.selectedAccount;
        this.channelReqId = 0;
        this.channelReqId2 = 0;
        this.hashtagsLoadedFromDb = false;
        this.allResultsAreGlobal = arg3;
    }

    public void addHashtagsFromMessage(CharSequence arg9) {
        HashtagObject v3_2;
        if(arg9 == null) {
            return;
        }

        Matcher v0 = Pattern.compile("(^|\\s)#[\\w@.]+").matcher(arg9);
        int v2;
        for(v2 = 0; v0.find(); v2 = 1) {
            v2 = v0.start();
            int v3 = v0.end();
            if(arg9.charAt(v2) != 64 && arg9.charAt(v2) != 35) {
                ++v2;
            }

            String v2_1 = arg9.subSequence(v2, v3).toString();
            if(this.hashtagsByText == null) {
                this.hashtagsByText = new HashMap();
                this.hashtags = new ArrayList();
            }

            Object v3_1 = this.hashtagsByText.get(v2_1);
            if(v3_1 == null) {
                v3_2 = new HashtagObject();
                v3_2.hashtag = v2_1;
                this.hashtagsByText.put(v3_2.hashtag, v3_2);
            }
            else {
                this.hashtags.remove(v3_1);
            }

            v3_2.date = ((int)(System.currentTimeMillis() / 1000));
            this.hashtags.add(0, v3_2);
        }

        if(v2 != 0) {
            this.putRecentHashtags(this.hashtags);
        }
    }

    public void clearRecentHashtags() {
        this.hashtags = new ArrayList();
        this.hashtagsByText = new HashMap();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SearchAdapterHelper$29sA63FziC1N4azDW00SIXwXXL4(this));
    }

    public ArrayList getGlobalSearch() {
        return this.globalSearch;
    }

    public ArrayList getGroupSearch() {
        return this.groupSearch;
    }

    public ArrayList getGroupSearch2() {
        return this.groupSearch2;
    }

    public ArrayList getHashtags() {
        return this.hashtags;
    }

    public String getLastFoundChannel() {
        return this.lastFoundChannel;
    }

    public String getLastFoundChannel2() {
        return this.lastFoundChannel2;
    }

    public String getLastFoundUsername() {
        return this.lastFoundUsername;
    }

    public ArrayList getLocalServerSearch() {
        return this.localServerSearch;
    }

    public static void lambda$clearRecentHashtags$10(SearchAdapterHelper arg2) {
        try {
            MessagesStorage.getInstance(arg2.currentAccount).getDatabase().a("DELETE FROM hashtag_recent_v2 WHERE 1").c().e();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$loadRecentHashtags$8(SearchAdapterHelper arg6) {
        try {
            SQLiteCursor v0_1 = MessagesStorage.getInstance(arg6.currentAccount).getDatabase().b("SELECT id, date FROM hashtag_recent_v2 WHERE 1", new Object[0]);
            ArrayList v1 = new ArrayList();
            HashMap v3 = new HashMap();
            while(v0_1.a()) {
                HashtagObject v4 = new HashtagObject();
                v4.hashtag = v0_1.e(0);
                v4.date = v0_1.b(1);
                v1.add(v4);
                v3.put(v4.hashtag, v4);
            }

            v0_1.b();
            Collections.sort(((List)v1), -$$Lambda$SearchAdapterHelper$JKrfR9DDn4AEKDwcLOw3vttTIsc.INSTANCE);
            AndroidUtilities.runOnUIThread(new -$$Lambda$SearchAdapterHelper$k6Ve2iDb_3jNIGlwadb8xDkZfMs(arg6, v1, v3));
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$null$0(SearchAdapterHelper arg2, int arg3, TL_error arg4, TLObject arg5, String arg6) {
        if(arg3 == arg2.channelLastReqId && arg4 == null) {
            arg2.lastFoundChannel = arg6.toLowerCase();
            MessagesController.getInstance(arg2.currentAccount).putUsers(((TL_channels_channelParticipants)arg5).users, false);
            arg2.groupSearch = ((TL_channels_channelParticipants)arg5).participants;
            arg2.delegate.onDataSetChanged();
        }

        arg2.channelReqId = 0;
    }

    public static void lambda$null$2(SearchAdapterHelper arg2, int arg3, TL_error arg4, TLObject arg5, String arg6) {
        if(arg3 == arg2.channelLastReqId2 && arg4 == null) {
            arg2.lastFoundChannel2 = arg6.toLowerCase();
            MessagesController.getInstance(arg2.currentAccount).putUsers(((TL_channels_channelParticipants)arg5).users, false);
            arg2.groupSearch2 = ((TL_channels_channelParticipants)arg5).participants;
            arg2.delegate.onDataSetChanged();
        }

        arg2.channelReqId2 = 0;
    }

    public static void lambda$null$4(SearchAdapterHelper arg8, int arg9, TL_error arg10, TLObject arg11, boolean arg12, boolean arg13, boolean arg14, String arg15) {
        int v13_1;
        Object v14;
        ArrayList v2_1;
        int v5_1;
        Object v6;
        Object v3;
        Object v2;
        if(arg9 == arg8.lastReqId && arg10 == null) {
            arg8.globalSearch.clear();
            arg8.globalSearchMap.clear();
            arg8.localServerSearch.clear();
            MessagesController.getInstance(arg8.currentAccount).putChats(((TL_contacts_found)arg11).chats, false);
            MessagesController.getInstance(arg8.currentAccount).putUsers(((TL_contacts_found)arg11).users, false);
            MessagesStorage.getInstance(arg8.currentAccount).putUsersAndChats(((TL_contacts_found)arg11).users, ((TL_contacts_found)arg11).chats, true, true);
            SparseArray v9 = new SparseArray();
            SparseArray v10 = new SparseArray();
            int v0;
            for(v0 = 0; v0 < ((TL_contacts_found)arg11).chats.size(); ++v0) {
                v2 = ((TL_contacts_found)arg11).chats.get(v0);
                v9.put(((Chat)v2).id, v2);
            }

            for(v0 = 0; v0 < ((TL_contacts_found)arg11).users.size(); ++v0) {
                v2 = ((TL_contacts_found)arg11).users.get(v0);
                v10.put(((User)v2).id, v2);
            }

            for(v0 = 0; true; ++v0) {
                v3 = null;
                if(v0 >= 2) {
                    break;
                }

                if(v0 != 0) {
                    v2_1 = ((TL_contacts_found)arg11).results;
                label_59:
                    int v4;
                    for(v4 = 0; v4 < v2_1.size(); ++v4) {
                        Object v5 = v2_1.get(v4);
                        if(((Peer)v5).user_id != 0) {
                            v6 = v10.get(((Peer)v5).user_id);
                            v5 = v3;
                        }
                        else {
                            if(((Peer)v5).chat_id != 0) {
                                v5_1 = ((Peer)v5).chat_id;
                            }
                            else if(((Peer)v5).channel_id != 0) {
                                v5_1 = ((Peer)v5).channel_id;
                            }
                            else {
                                goto label_80;
                            }

                            v5 = v9.get(v5_1);
                            v6 = v3;
                            goto label_82;
                        label_80:
                            v5 = v3;
                            v6 = v5;
                        }

                    label_82:
                        if(v5 != null) {
                            if(!arg12) {
                            }
                            else {
                                arg8.globalSearch.add(v5);
                                arg8.globalSearchMap.put(-((Chat)v5).id, v5);
                            }
                        }
                        else if(v6 != null) {
                            if(!arg13 && (((User)v6).bot)) {
                                goto label_105;
                            }

                            if(!arg14 && (((User)v6).self)) {
                                goto label_105;
                            }

                            arg8.globalSearch.add(v6);
                            arg8.globalSearchMap.put(((User)v6).id, v6);
                        }

                    label_105:
                    }
                }
                else if(!arg8.allResultsAreGlobal) {
                }
                else {
                    v2_1 = ((TL_contacts_found)arg11).my_results;
                    goto label_59;
                }
            }

            if(!arg8.allResultsAreGlobal) {
                int v12;
                for(v12 = 0; v12 < ((TL_contacts_found)arg11).my_results.size(); ++v12) {
                    Object v13 = ((TL_contacts_found)arg11).my_results.get(v12);
                    if(((Peer)v13).user_id != 0) {
                        v14 = v10.get(((Peer)v13).user_id);
                        v13 = v3;
                    }
                    else {
                        if(((Peer)v13).chat_id != 0) {
                            v13_1 = ((Peer)v13).chat_id;
                        }
                        else if(((Peer)v13).channel_id != 0) {
                            v13_1 = ((Peer)v13).channel_id;
                        }
                        else {
                            goto label_134;
                        }

                        v13 = v9.get(v13_1);
                        v14 = v3;
                        goto label_136;
                    label_134:
                        v13 = v3;
                        v14 = v13;
                    }

                label_136:
                    if(v13 != null) {
                        arg8.localServerSearch.add(v13);
                        arg8.globalSearchMap.put(-((Chat)v13).id, v13);
                    }
                    else if(v14 != null) {
                        arg8.localServerSearch.add(v14);
                        arg8.globalSearchMap.put(((User)v14).id, v14);
                    }
                }
            }

            arg8.lastFoundUsername = arg15.toLowerCase();
            if(arg8.localSearchResults != null) {
                arg8.mergeResults(arg8.localSearchResults);
            }

            arg8.delegate.onDataSetChanged();
        }

        arg8.reqId = 0;
    }

    static int lambda$null$6(HashtagObject arg2, HashtagObject arg3) {
        if(arg2.date < arg3.date) {
            return 1;
        }

        if(arg2.date > arg3.date) {
            return -1;
        }

        return 0;
    }

    public static void lambda$null$7(SearchAdapterHelper arg0, ArrayList arg1, HashMap arg2) {
        arg0.setHashtags(arg1, arg2);
    }

    public static void lambda$putRecentHashtags$9(SearchAdapterHelper arg5, ArrayList arg6) {
        int v3;
        try {
            MessagesStorage.getInstance(arg5.currentAccount).getDatabase().d();
            SQLitePreparedStatement v0 = MessagesStorage.getInstance(arg5.currentAccount).getDatabase().a("REPLACE INTO hashtag_recent_v2 VALUES(?, ?)");
            int v1 = 0;
            while(true) {
                v3 = 100;
                if(v1 < arg6.size()) {
                    if(v1 == v3) {
                    }
                    else {
                        Object v2 = arg6.get(v1);
                        v0.d();
                        v0.a(1, ((HashtagObject)v2).hashtag);
                        v0.a(2, ((HashtagObject)v2).date);
                        v0.b();
                        ++v1;
                        continue;
                    }
                }

                break;
            }

            v0.e();
            MessagesStorage.getInstance(arg5.currentAccount).getDatabase().e();
            if(arg6.size() < v3) {
                return;
            }

            MessagesStorage.getInstance(arg5.currentAccount).getDatabase().d();
            while(v3 < arg6.size()) {
                SQLiteDatabase v0_1 = MessagesStorage.getInstance(arg5.currentAccount).getDatabase();
                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("DELETE FROM hashtag_recent_v2 WHERE id = \'");
                v1_1.append(arg6.get(v3).hashtag);
                v1_1.append("\'");
                v0_1.a(v1_1.toString()).c().e();
                ++v3;
            }

            MessagesStorage.getInstance(arg5.currentAccount).getDatabase().e();
        }
        catch(Exception v6) {
            FileLog.e(((Throwable)v6));
        }
    }

    public static void lambda$queryServerSearch$1(SearchAdapterHelper arg7, int arg8, String arg9, TLObject arg10, TL_error arg11) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SearchAdapterHelper$zzD8O5q37u-u2IW8yJlQc3bxUjs(arg7, arg8, arg11, arg10, arg9));
    }

    public static void lambda$queryServerSearch$3(SearchAdapterHelper arg7, int arg8, String arg9, TLObject arg10, TL_error arg11) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SearchAdapterHelper$LHyTTYDhEI1mZV1aJYNL0mepqBw(arg7, arg8, arg11, arg10, arg9));
    }

    public static void lambda$queryServerSearch$5(SearchAdapterHelper arg10, int arg11, boolean arg12, boolean arg13, boolean arg14, String arg15, TLObject arg16, TL_error arg17) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$SearchAdapterHelper$APrjGpdiwvpkcFbJ12Zl0RPIrBo(arg10, arg11, arg17, arg16, arg12, arg13, arg14, arg15));
    }

    public boolean loadRecentHashtags() {
        if(this.hashtagsLoadedFromDb) {
            return 1;
        }

        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SearchAdapterHelper$3i_Ak5qswbqE9hp3UEQCvG8tV_E(this));
        return 0;
    }

    public void mergeResults(ArrayList arg5) {
        int v2_1;
        SparseArray v3;
        this.localSearchResults = arg5;
        if(this.globalSearchMap.size() != 0) {
            if(arg5 == null) {
            }
            else {
                int v0 = arg5.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    Object v2 = arg5.get(v1);
                    if((v2 instanceof User)) {
                        v2 = this.globalSearchMap.get(((User)v2).id);
                        if(v2 != null) {
                            this.globalSearch.remove(v2);
                            this.localServerSearch.remove(v2);
                            v3 = this.globalSearchMap;
                            v2_1 = ((User)v2).id;
                            goto label_37;
                        }
                    }
                    else if((v2 instanceof Chat)) {
                        v2 = this.globalSearchMap.get(-((Chat)v2).id);
                        if(v2 != null) {
                            this.globalSearch.remove(v2);
                            this.localServerSearch.remove(v2);
                            v3 = this.globalSearchMap;
                            v2_1 = -((Chat)v2).id;
                        label_37:
                            v3.remove(v2_1);
                        }
                    }
                }
            }
        }
    }

    private void putRecentHashtags(ArrayList arg3) {
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new -$$Lambda$SearchAdapterHelper$l4PoatmjtGKKwueWzuP7-vtvGTU(this, arg3));
    }

    public void queryServerSearch(String arg13, boolean arg14, boolean arg15, boolean arg16, boolean arg17, int arg18, boolean arg19) {
        TL_channelParticipantsSearch v9_1;
        SearchAdapterHelper v7 = this;
        String v6 = arg13;
        int v0 = arg18;
        if(v7.reqId != 0) {
            ConnectionsManager.getInstance(v7.currentAccount).cancelRequest(v7.reqId, true);
            v7.reqId = 0;
        }

        if(v7.channelReqId != 0) {
            ConnectionsManager.getInstance(v7.currentAccount).cancelRequest(v7.channelReqId, true);
            v7.channelReqId = 0;
        }

        if(v7.channelReqId2 != 0) {
            ConnectionsManager.getInstance(v7.currentAccount).cancelRequest(v7.channelReqId2, true);
            v7.channelReqId2 = 0;
        }

        if(v6 == null) {
            v7.groupSearch.clear();
            v7.groupSearch2.clear();
            v7.globalSearch.clear();
            v7.globalSearchMap.clear();
            v7.localServerSearch.clear();
            v7.lastReqId = 0;
            v7.channelLastReqId = 0;
            v7.channelLastReqId2 = 0;
            v7.delegate.onDataSetChanged();
            return;
        }

        int v8 = 2;
        int v5 = 50;
        if(arg13.length() <= 0 || v0 == 0) {
            v7.groupSearch.clear();
            v7.groupSearch2.clear();
            v7.channelLastReqId = 0;
            v7.delegate.onDataSetChanged();
        }
        else {
            TL_channels_getParticipants v2 = new TL_channels_getParticipants();
            if(arg19) {
                TL_channelParticipantsBanned v9 = new TL_channelParticipantsBanned();
            }
            else {
                v9_1 = new TL_channelParticipantsSearch();
            }

            v2.filter = ((ChannelParticipantsFilter)v9_1);
            v2.filter.q = v6;
            v2.limit = v5;
            v2.offset = 0;
            v2.channel = MessagesController.getInstance(v7.currentAccount).getInputChannel(v0);
            int v9_2 = v7.channelLastReqId + 1;
            v7.channelLastReqId = v9_2;
            v7.channelReqId = ConnectionsManager.getInstance(v7.currentAccount).sendRequest(((TLObject)v2), new -$$Lambda$SearchAdapterHelper$ss23u_GFUQJUVeu6gtt8hTPVc_o(this, v9_2, arg13), v8);
            if(!arg19) {
                goto label_106;
            }

            TL_channels_getParticipants v1 = new TL_channels_getParticipants();
            v1.filter = new TL_channelParticipantsKicked();
            v1.filter.q = v6;
            v1.limit = v5;
            v1.offset = 0;
            v1.channel = MessagesController.getInstance(v7.currentAccount).getInputChannel(v0);
            v0 = v7.channelLastReqId2 + 1;
            v7.channelLastReqId2 = v0;
            v7.channelReqId2 = ConnectionsManager.getInstance(v7.currentAccount).sendRequest(((TLObject)v1), new -$$Lambda$SearchAdapterHelper$NXlW2Cqah90oc3F_k1krE2PSCX0(this, v0, arg13), v8);
        }

    label_106:
        if(arg14) {
            if(arg13.length() > 0) {
                TL_contacts_search v9_3 = new TL_contacts_search();
                v9_3.q = v6;
                v9_3.limit = v5;
                int v2_1 = v7.lastReqId + 1;
                v7.lastReqId = v2_1;
                v7.reqId = ConnectionsManager.getInstance(v7.currentAccount).sendRequest(((TLObject)v9_3), new -$$Lambda$SearchAdapterHelper$Sf63MqTQefkFbuMgkigesOO9Q9I(this, v2_1, arg15, arg16, arg17, arg13), v8);
            }
            else {
                v7.globalSearch.clear();
                v7.globalSearchMap.clear();
                v7.localServerSearch.clear();
                v7.lastReqId = 0;
                v7.delegate.onDataSetChanged();
            }
        }
    }

    public void setDelegate(SearchAdapterHelperDelegate arg1) {
        this.delegate = arg1;
    }

    public void setHashtags(ArrayList arg2, HashMap arg3) {
        this.hashtags = arg2;
        this.hashtagsByText = arg3;
        this.hashtagsLoadedFromDb = true;
        this.delegate.onSetHashtags(arg2, arg3);
    }

    public void unloadRecentHashtags() {
        this.hashtagsLoadedFromDb = false;
    }
}

