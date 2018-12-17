package org.telegram.messenger;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.content.ContentProviderOperation$Builder;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract$CommonDataKinds$Phone;
import android.provider.ContactsContract$Contacts;
import android.provider.ContactsContract$Data;
import android.provider.ContactsContract$RawContacts;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.customization.Model.ContactHelper;
import org.telegram.customization.i.c;
import org.telegram.customization.j.a.a;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputUser;
import org.telegram.tgnet.TLRPC$TL_account_getAccountTTL;
import org.telegram.tgnet.TLRPC$TL_account_getPrivacy;
import org.telegram.tgnet.TLRPC$TL_account_privacyRules;
import org.telegram.tgnet.TLRPC$TL_boolTrue;
import org.telegram.tgnet.TLRPC$TL_contact;
import org.telegram.tgnet.TLRPC$TL_contactStatus;
import org.telegram.tgnet.TLRPC$TL_contacts_contactsNotModified;
import org.telegram.tgnet.TLRPC$TL_contacts_deleteContacts;
import org.telegram.tgnet.TLRPC$TL_contacts_getContacts;
import org.telegram.tgnet.TLRPC$TL_contacts_getStatuses;
import org.telegram.tgnet.TLRPC$TL_contacts_importContacts;
import org.telegram.tgnet.TLRPC$TL_contacts_importedContacts;
import org.telegram.tgnet.TLRPC$TL_contacts_resetSaved;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_help_getInviteText;
import org.telegram.tgnet.TLRPC$TL_help_inviteText;
import org.telegram.tgnet.TLRPC$TL_inputPhoneContact;
import org.telegram.tgnet.TLRPC$TL_inputPrivacyKeyChatInvite;
import org.telegram.tgnet.TLRPC$TL_inputPrivacyKeyPhoneCall;
import org.telegram.tgnet.TLRPC$TL_inputPrivacyKeyStatusTimestamp;
import org.telegram.tgnet.TLRPC$TL_user;
import org.telegram.tgnet.TLRPC$TL_userStatusLastMonth;
import org.telegram.tgnet.TLRPC$TL_userStatusLastWeek;
import org.telegram.tgnet.TLRPC$TL_userStatusRecently;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$UserStatus;
import org.telegram.tgnet.TLRPC$Vector;
import org.telegram.tgnet.TLRPC$contacts_Contacts;
import utils.a.b;

public class ContactsController {
    public class Contact {
        public int contact_id;
        public String first_name;
        public int imported;
        public boolean isGoodProvider;
        public String key;
        public String last_name;
        public boolean namesFilled;
        public ArrayList phoneDeleted;
        public ArrayList phoneTypes;
        public ArrayList phones;
        public String provider;
        public ArrayList shortPhones;
        public User user;

        public Contact() {
            super();
            this.phones = new ArrayList(4);
            this.phoneTypes = new ArrayList(4);
            this.shortPhones = new ArrayList(4);
            this.phoneDeleted = new ArrayList(4);
        }

        public static String getLetter(String arg3, String arg4) {
            if(!TextUtils.isEmpty(((CharSequence)arg3))) {
                return arg3.substring(0, 1);
            }

            if(!TextUtils.isEmpty(((CharSequence)arg4))) {
                return arg4.substring(0, 1);
            }

            return "#";
        }

        public String getLetter() {
            return Contact.getLetter(this.first_name, this.last_name);
        }
    }

    class MyContentObserver extends ContentObserver {
        class org.telegram.messenger.ContactsController$MyContentObserver$1 implements Runnable {
            org.telegram.messenger.ContactsController$MyContentObserver$1(MyContentObserver arg1) {
                this.this$1 = arg1;
                super();
            }

            public void run() {
                int v0;
                for(v0 = 0; v0 < 3; ++v0) {
                    if(UserConfig.getInstance(v0).isClientActivated()) {
                        ConnectionsManager.getInstance(v0).resumeNetworkMaybe();
                        ContactsController.getInstance(v0).checkContacts();
                    }
                }
            }
        }

        private Runnable checkRunnable;

        public MyContentObserver(ContactsController arg1) {
            ContactsController.this = arg1;
            super(null);
            this.checkRunnable = new org.telegram.messenger.ContactsController$MyContentObserver$1(this);
        }

        public boolean deliverSelfNotifications() {
            return 0;
        }

        public void onChange(boolean arg4) {
            super.onChange(arg4);
            Object v4 = ContactsController.this.observerLock;
            __monitor_enter(v4);
            try {
                if(ContactsController.this.ignoreChanges) {
                    __monitor_exit(v4);
                    return;
                }

                __monitor_exit(v4);
            }
            catch(Throwable v0) {
                try {
                label_19:
                    __monitor_exit(v4);
                }
                catch(Throwable v0) {
                    goto label_19;
                }

                throw v0;
            }

            Utilities.globalQueue.cancelRunnable(this.checkRunnable);
            Utilities.globalQueue.postRunnable(this.checkRunnable, 500);
        }
    }

    private static volatile ContactsController[] Instance;
    private ArrayList callPrivacyRules;
    private int completedRequestsCount;
    public ArrayList contacts;
    public ArrayList contacts1;
    public HashMap contactsBook;
    private boolean contactsBookLoaded;
    public HashMap contactsBookSPhones;
    public HashMap contactsByPhone;
    public HashMap contactsByShortPhone;
    public ConcurrentHashMap contactsDict;
    public SparseArray contactsDict1;
    public boolean contactsLoaded;
    private boolean contactsSyncInProgress;
    private int currentAccount;
    private ArrayList delayedContactsUpdate;
    private int deleteAccountTTL;
    private ArrayList groupPrivacyRules;
    private boolean ignoreChanges;
    private String inviteLink;
    private String lastContactsVersions;
    private final Object loadContactsSync;
    private int loadingCallsInfo;
    private boolean loadingContacts;
    private int loadingDeleteInfo;
    private int loadingGroupInfo;
    private int loadingLastSeenInfo;
    private boolean migratingContacts;
    private final Object observerLock;
    public ArrayList phoneBookContacts;
    public ArrayList phoneBookSectionsArray;
    public HashMap phoneBookSectionsDict;
    private ArrayList privacyRules;
    private String[] projectionNames;
    private String[] projectionPhones;
    private HashMap sectionsToReplace;
    public ArrayList sortedUsersMutualSectionsArray;
    public ArrayList sortedUsersMutualSectionsArray1;
    public ArrayList sortedUsersSectionsArray;
    public ArrayList sortedUsersSectionsArray1;
    private Account systemAccount;
    private boolean updatingInviteLink;
    public HashMap usersMutualSectionsDict;
    public HashMap usersMutualSectionsDict1;
    public HashMap usersSectionsDict;
    public HashMap usersSectionsDict1;

    static {
        ContactsController.Instance = new ContactsController[3];
    }

    public ContactsController(int arg9) {
        super();
        this.loadContactsSync = new Object();
        this.observerLock = new Object();
        this.lastContactsVersions = "";
        this.delayedContactsUpdate = new ArrayList();
        this.sectionsToReplace = new HashMap();
        this.projectionPhones = new String[]{"lookup", "data1", "data2", "data3", "display_name", "account_type"};
        this.projectionNames = new String[]{"lookup", "data2", "data3", "data5"};
        this.contactsBook = new HashMap();
        this.contactsBookSPhones = new HashMap();
        this.phoneBookContacts = new ArrayList();
        this.phoneBookSectionsDict = new HashMap();
        this.phoneBookSectionsArray = new ArrayList();
        this.contacts = new ArrayList();
        this.contactsDict = new ConcurrentHashMap(20, 1f, 2);
        this.usersSectionsDict = new HashMap();
        this.sortedUsersSectionsArray = new ArrayList();
        this.usersMutualSectionsDict = new HashMap();
        this.sortedUsersMutualSectionsArray = new ArrayList();
        this.contactsByPhone = new HashMap();
        this.contactsByShortPhone = new HashMap();
        this.contacts1 = new ArrayList();
        this.contactsDict1 = new SparseArray();
        this.usersSectionsDict1 = new HashMap();
        this.sortedUsersSectionsArray1 = new ArrayList();
        this.usersMutualSectionsDict1 = new HashMap();
        this.sortedUsersMutualSectionsArray1 = new ArrayList();
        this.currentAccount = arg9;
        if(MessagesController.getMainSettings(this.currentAccount).getBoolean("needGetStatuses", false)) {
            this.reloadContactsStatuses();
        }

        this.sectionsToReplace.put("À", "A");
        this.sectionsToReplace.put("Á", "A");
        this.sectionsToReplace.put("Ä", "A");
        this.sectionsToReplace.put("Ù", "U");
        this.sectionsToReplace.put("Ú", "U");
        this.sectionsToReplace.put("Ü", "U");
        this.sectionsToReplace.put("Ì", "I");
        this.sectionsToReplace.put("Í", "I");
        this.sectionsToReplace.put("Ï", "I");
        this.sectionsToReplace.put("È", "E");
        this.sectionsToReplace.put("É", "E");
        this.sectionsToReplace.put("Ê", "E");
        this.sectionsToReplace.put("Ë", "E");
        this.sectionsToReplace.put("Ò", "O");
        this.sectionsToReplace.put("Ó", "O");
        this.sectionsToReplace.put("Ö", "O");
        this.sectionsToReplace.put("Ç", "C");
        this.sectionsToReplace.put("Ñ", "N");
        this.sectionsToReplace.put("Ÿ", "Y");
        this.sectionsToReplace.put("Ý", "Y");
        this.sectionsToReplace.put("Ţ", "Y");
        if(arg9 == 0) {
            Utilities.globalQueue.postRunnable(new Runnable() {
                public void run() {
                    try {
                        if(ContactsController.access$200(ContactsController.this)) {
                            ApplicationLoader.applicationContext.getContentResolver().registerContentObserver(ContactsContract$Contacts.CONTENT_URI, true, new MyContentObserver(ContactsController.this));
                        }

                        return;
                    }
                    catch(Throwable ) {
                        return;
                    }
                }
            });
        }
    }

    static Object access$000(ContactsController arg0) {
        return arg0.observerLock;
    }

    static boolean access$100(ContactsController arg0) {
        return arg0.ignoreChanges;
    }

    static boolean access$1002(ContactsController arg0, boolean arg1) {
        arg0.loadingContacts = arg1;
        return arg1;
    }

    static boolean access$1100(ContactsController arg0) {
        return arg0.contactsBookLoaded;
    }

    static boolean access$1102(ContactsController arg0, boolean arg1) {
        arg0.contactsBookLoaded = arg1;
        return arg1;
    }

    static String access$1202(ContactsController arg0, String arg1) {
        arg0.lastContactsVersions = arg1;
        return arg1;
    }

    static Account access$1300(ContactsController arg0) {
        return arg0.systemAccount;
    }

    static Account access$1302(ContactsController arg0, Account arg1) {
        arg0.systemAccount = arg1;
        return arg1;
    }

    static ArrayList access$1400(ContactsController arg0) {
        return arg0.delayedContactsUpdate;
    }

    static Object access$1500(ContactsController arg0) {
        return arg0.loadContactsSync;
    }

    static HashMap access$1600(ContactsController arg0) {
        return arg0.readContactsFromPhoneBook();
    }

    static void access$1700(ContactsController arg0, ArrayList arg1, ConcurrentHashMap arg2, ArrayList arg3, ArrayList arg4) {
        arg0.applyContactsUpdates(arg1, arg2, arg3, arg4);
    }

    static void access$1800(ContactsController arg0, HashMap arg1, ArrayList arg2, HashMap arg3) {
        arg0.mergePhonebookAndTelegramContacts(arg1, arg2, arg3);
    }

    static int access$1900(ContactsController arg0, ArrayList arg1) {
        return arg0.getContactsHash(arg1);
    }

    static boolean access$200(ContactsController arg0) {
        return arg0.hasContactsPermission();
    }

    static HashMap access$2000(ContactsController arg0) {
        return arg0.sectionsToReplace;
    }

    static void access$2100(ContactsController arg0) {
        arg0.performWriteContactsToPhoneBook();
    }

    static void access$2200(ContactsController arg0) {
        arg0.saveContactsLoadTime();
    }

    static void access$2300(ContactsController arg0) {
        arg0.reloadContactsStatusesMaybe();
    }

    static void access$2400(ContactsController arg0, ArrayList arg1) {
        arg0.performWriteContactsToPhoneBookInternal(arg1);
    }

    static void access$2500(ContactsController arg0, int arg1) {
        arg0.deleteContactFromPhoneBook(arg1);
    }

    static void access$2600(ContactsController arg0, boolean arg1) {
        arg0.buildContactsSectionsArrays(arg1);
    }

    static int access$2702(ContactsController arg0, int arg1) {
        arg0.deleteAccountTTL = arg1;
        return arg1;
    }

    static int access$2802(ContactsController arg0, int arg1) {
        arg0.loadingDeleteInfo = arg1;
        return arg1;
    }

    static ArrayList access$2902(ContactsController arg0, ArrayList arg1) {
        arg0.privacyRules = arg1;
        return arg1;
    }

    static boolean access$300(ContactsController arg0) {
        return arg0.migratingContacts;
    }

    static int access$3002(ContactsController arg0, int arg1) {
        arg0.loadingLastSeenInfo = arg1;
        return arg1;
    }

    static boolean access$302(ContactsController arg0, boolean arg1) {
        arg0.migratingContacts = arg1;
        return arg1;
    }

    static ArrayList access$3102(ContactsController arg0, ArrayList arg1) {
        arg0.callPrivacyRules = arg1;
        return arg1;
    }

    static int access$3202(ContactsController arg0, int arg1) {
        arg0.loadingCallsInfo = arg1;
        return arg1;
    }

    static ArrayList access$3302(ContactsController arg0, ArrayList arg1) {
        arg0.groupPrivacyRules = arg1;
        return arg1;
    }

    static int access$3402(ContactsController arg0, int arg1) {
        arg0.loadingGroupInfo = arg1;
        return arg1;
    }

    static int access$400(ContactsController arg0) {
        return arg0.completedRequestsCount;
    }

    static int access$402(ContactsController arg0, int arg1) {
        arg0.completedRequestsCount = arg1;
        return arg1;
    }

    static int access$408(ContactsController arg2) {
        int v0 = arg2.completedRequestsCount;
        arg2.completedRequestsCount = v0 + 1;
        return v0;
    }

    static boolean access$502(ContactsController arg0, boolean arg1) {
        arg0.updatingInviteLink = arg1;
        return arg1;
    }

    static int access$600(ContactsController arg0) {
        return arg0.currentAccount;
    }

    static String access$702(ContactsController arg0, String arg1) {
        arg0.inviteLink = arg1;
        return arg1;
    }

    static boolean access$800(ContactsController arg0) {
        return arg0.checkContactsInternal();
    }

    static boolean access$900(ContactsController arg0) {
        return arg0.contactsSyncInProgress;
    }

    static boolean access$902(ContactsController arg0, boolean arg1) {
        arg0.contactsSyncInProgress = arg1;
        return arg1;
    }

    public void addContact(User arg6) {
        if(arg6 != null) {
            if(TextUtils.isEmpty(arg6.phone)) {
            }
            else {
                TL_contacts_importContacts v0 = new TL_contacts_importContacts();
                ArrayList v1 = new ArrayList();
                TL_inputPhoneContact v2 = new TL_inputPhoneContact();
                v2.phone = arg6.phone;
                if(!v2.phone.startsWith("+")) {
                    v2.phone = "+" + v2.phone;
                }

                v2.first_name = arg6.first_name;
                v2.last_name = arg6.last_name;
                v2.client_id = 0;
                v1.add(v2);
                v0.contacts = v1;
                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate(arg6) {
                    public void run(TLObject arg8, TL_error arg9) {
                        Object v1;
                        if(arg9 != null) {
                            return;
                        }

                        MessagesStorage.getInstance(ContactsController.this.currentAccount).putUsersAndChats(((TL_contacts_importedContacts)arg8).users, null, true, true);
                        ArrayList v9 = new ArrayList();
                        Iterator v0 = ((TL_contacts_importedContacts)arg8).users.iterator();
                        while(v0.hasNext()) {
                            v1 = v0.next();
                            ContactHelper v3 = new ContactHelper();
                            v3.setId(((User)v1).id);
                            String v4 = "";
                            String v5 = "";
                            if(((User)v1).first_name != null && !TextUtils.isEmpty(((User)v1).first_name)) {
                                v4 = this.val$user.first_name;
                            }

                            if(((User)v1).last_name != null && !TextUtils.isEmpty(((User)v1).last_name)) {
                                v5 = ((User)v1).last_name;
                            }

                            v3.setName(v4 + " " + v5);
                            v3.setMobile(((User)v1).phone);
                            v3.setUsername(((User)v1).username);
                            v3.setAccessHash(((User)v1).access_hash);
                            v9.add(v3);
                        }

                        c.c(new a(true).api, "scontact", v9);
                        int v0_1;
                        for(v0_1 = 0; v0_1 < ((TL_contacts_importedContacts)arg8).users.size(); ++v0_1) {
                            v1 = ((TL_contacts_importedContacts)arg8).users.get(v0_1);
                            Utilities.phoneBookQueue.postRunnable(new Runnable(((User)v1)) {
                                public void run() {
                                    this.this$1.this$0.addContactToPhoneBook(this.val$u, true);
                                }
                            });
                            TL_contact v2 = new TL_contact();
                            v2.user_id = ((User)v1).id;
                            ArrayList v3_1 = new ArrayList();
                            v3_1.add(v2);
                            MessagesStorage.getInstance(ContactsController.this.currentAccount).putContacts(v3_1, false);
                            if(!TextUtils.isEmpty(((User)v1).phone)) {
                                ContactsController.formatName(((User)v1).first_name, ((User)v1).last_name);
                                MessagesStorage.getInstance(ContactsController.this.currentAccount).applyPhoneBookUpdates(((User)v1).phone, "");
                                Object v2_1 = ContactsController.this.contactsBookSPhones.get(((User)v1).phone);
                                if(v2_1 != null) {
                                    int v1_1 = ((Contact)v2_1).shortPhones.indexOf(((User)v1).phone);
                                    if(v1_1 != -1) {
                                        ((Contact)v2_1).phoneDeleted.set(v1_1, Integer.valueOf(0));
                                    }
                                }
                            }
                        }

                        AndroidUtilities.runOnUIThread(new Runnable(((TL_contacts_importedContacts)arg8)) {
                            public void run() {
                                Iterator v0 = this.val$res.users.iterator();
                                while(v0.hasNext()) {
                                    Object v1 = v0.next();
                                    MessagesController.getInstance(this.this$1.this$0.currentAccount).putUser(((User)v1), false);
                                    if(this.this$1.this$0.contactsDict.get(Integer.valueOf(((User)v1).id)) != null) {
                                        continue;
                                    }

                                    TL_contact v2 = new TL_contact();
                                    v2.user_id = ((User)v1).id;
                                    this.this$1.this$0.contacts.add(v2);
                                    this.this$1.this$0.contactsDict.put(Integer.valueOf(v2.user_id), v2);
                                }

                                this.this$1.this$0.buildContactsSectionsArrays(true);
                                NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
                            }
                        });
                    }
                }, 6);
            }
        }
    }

    public long addContactToPhoneBook(User arg9, boolean arg10) {
        long v1 = -1;
        if(this.systemAccount != null && arg9 != null) {
            if(TextUtils.isEmpty(arg9.phone)) {
            }
            else if(!this.hasContactsPermission()) {
                return v1;
            }
            else {
                Object v0 = this.observerLock;
                __monitor_enter(v0);
                try {
                    this.ignoreChanges = true;
                    __monitor_exit(v0);
                }
                catch(Throwable v9) {
                    try {
                    label_136:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v9) {
                        goto label_136;
                    }

                    throw v9;
                }

                ContentResolver v0_1 = ApplicationLoader.applicationContext.getContentResolver();
                if(arg10) {
                    try {
                        Uri v10_1 = ContactsContract$RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", this.systemAccount.name).appendQueryParameter("account_type", this.systemAccount.type).build();
                        v0_1.delete(v10_1, "sync2 = " + arg9.id, null);
                    }
                    catch(Exception v10) {
                        FileLog.e(((Throwable)v10));
                    }

                    goto label_45;
                }
                else {
                label_45:
                    ArrayList v10_2 = new ArrayList();
                    ContentProviderOperation$Builder v3_1 = ContentProviderOperation.newInsert(ContactsContract$RawContacts.CONTENT_URI);
                    v3_1.withValue("account_name", this.systemAccount.name);
                    v3_1.withValue("account_type", this.systemAccount.type);
                    v3_1.withValue("sync1", arg9.phone);
                    v3_1.withValue("sync2", Integer.valueOf(arg9.id));
                    v10_2.add(v3_1.build());
                    v3_1 = ContentProviderOperation.newInsert(ContactsContract$Data.CONTENT_URI);
                    v3_1.withValueBackReference("raw_contact_id", 0);
                    v3_1.withValue("mimetype", "vnd.android.cursor.item/name");
                    v3_1.withValue("data2", arg9.first_name);
                    v3_1.withValue("data3", arg9.last_name);
                    v10_2.add(v3_1.build());
                    v3_1 = ContentProviderOperation.newInsert(ContactsContract$Data.CONTENT_URI);
                    v3_1.withValueBackReference("raw_contact_id", 0);
                    v3_1.withValue("mimetype", "vnd.android.cursor.item/vnd.org.telegram.messenger.android.profile");
                    v3_1.withValue("data1", Integer.valueOf(arg9.id));
                    v3_1.withValue("data2", "Telegram Profile");
                    v3_1.withValue("data3", "+" + arg9.phone);
                    v3_1.withValue("data4", Integer.valueOf(arg9.id));
                    v10_2.add(v3_1.build());
                    try {
                        ContentProviderResult[] v9_2 = v0_1.applyBatch("com.android.contacts", v10_2);
                        if(v9_2 == null) {
                        }
                        else if(v9_2.length <= 0) {
                        }
                        else if(v9_2[0].uri != null) {
                            v1 = Long.parseLong(v9_2[0].uri.getLastPathSegment());
                        }
                        else {
                        }
                    }
                    catch(Exception v9_1) {
                        FileLog.e(((Throwable)v9_1));
                    }

                    Object v9_3 = this.observerLock;
                    __monitor_enter(v9_3);
                    try {
                        this.ignoreChanges = false;
                        __monitor_exit(v9_3);
                        return v1;
                    label_133:
                        __monitor_exit(v9_3);
                    }
                    catch(Throwable v10_3) {
                        goto label_133;
                    }

                    throw v10_3;
                }
            }
        }

        return v1;
    }

    private void applyContactsUpdates(ArrayList arg11, ConcurrentHashMap arg12, ArrayList arg13, ArrayList arg14) {
        User v4_1;
        User v6_1;
        Object v4;
        Object v6;
        Object v2;
        int v0 = 0;
        if(arg13 == null || arg14 == null) {
            arg13 = new ArrayList();
            arg14 = new ArrayList();
            int v1;
            for(v1 = 0; v1 < arg11.size(); ++v1) {
                v2 = arg11.get(v1);
                if(((Integer)v2).intValue() > 0) {
                    TL_contact v3 = new TL_contact();
                    v3.user_id = ((Integer)v2).intValue();
                    arg13.add(v3);
                }
                else if(((Integer)v2).intValue() < 0) {
                    arg14.add(Integer.valueOf(-((Integer)v2).intValue()));
                }
            }
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("process update - contacts add = " + arg13.size() + " delete = " + arg14.size());
        }

        v11 = new StringBuilder();
        StringBuilder v1_1 = new StringBuilder();
        int v2_1 = 0;
        int v3_1 = 0;
        while(true) {
            int v5 = -1;
            v6 = null;
            if(v2_1 < arg13.size()) {
                v4 = arg13.get(v2_1);
                if(arg12 != null) {
                    v6 = arg12.get(Integer.valueOf(((TL_contact)v4).user_id));
                }

                if(v6 == null) {
                    v6_1 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((TL_contact)v4).user_id));
                }
                else {
                    MessagesController.getInstance(this.currentAccount).putUser(((User)v6), true);
                }

                if(v6_1 == null || (TextUtils.isEmpty(v6_1.phone))) {
                    v3_1 = 1;
                }
                else {
                    v4 = this.contactsBookSPhones.get(v6_1.phone);
                    if(v4 != null) {
                        int v7 = ((Contact)v4).shortPhones.indexOf(v6_1.phone);
                        if(v7 != v5) {
                            ((Contact)v4).phoneDeleted.set(v7, Integer.valueOf(0));
                        }
                    }

                    if(v11.length() != 0) {
                        v11.append(",");
                    }

                    v11.append(v6_1.phone);
                }

                ++v2_1;
                continue;
            }

            break;
        }

        while(v0 < arg14.size()) {
            v2 = arg14.get(v0);
            Utilities.phoneBookQueue.postRunnable(new Runnable(((Integer)v2)) {
                public void run() {
                    ContactsController.this.deleteContactFromPhoneBook(this.val$uid.intValue());
                }
            });
            v4 = arg12 != null ? arg12.get(v2) : v6;
            if(v4 == null) {
                v4_1 = MessagesController.getInstance(this.currentAccount).getUser(((Integer)v2));
            }
            else {
                MessagesController.getInstance(this.currentAccount).putUser(((User)v4), true);
            }

            if(v4_1 == null) {
                v3_1 = 1;
            }
            else if(!TextUtils.isEmpty(v4_1.phone)) {
                v2 = this.contactsBookSPhones.get(v4_1.phone);
                if(v2 != null) {
                    int v8 = ((Contact)v2).shortPhones.indexOf(v4_1.phone);
                    if(v8 != v5) {
                        ((Contact)v2).phoneDeleted.set(v8, Integer.valueOf(1));
                    }
                }

                if(v1_1.length() != 0) {
                    v1_1.append(",");
                }

                v1_1.append(v4_1.phone);
            }

            ++v0;
        }

        if(v11.length() != 0 || v1_1.length() != 0) {
            MessagesStorage.getInstance(this.currentAccount).applyPhoneBookUpdates(v11.toString(), v1_1.toString());
        }

        if(v3_1 != 0) {
            Utilities.stageQueue.postRunnable(new Runnable() {
                public void run() {
                    ContactsController.this.loadContacts(false, 0);
                }
            });
        }
        else {
            AndroidUtilities.runOnUIThread(new Runnable(arg13, arg14) {
                public void run() {
                    Object v2;
                    int v1;
                    for(v1 = 0; v1 < this.val$newContacts.size(); ++v1) {
                        v2 = this.val$newContacts.get(v1);
                        if(ContactsController.this.contactsDict.get(Integer.valueOf(((TL_contact)v2).user_id)) == null) {
                            ContactsController.this.contacts.add(v2);
                            ContactsController.this.contactsDict.put(Integer.valueOf(((TL_contact)v2).user_id), v2);
                        }
                    }

                    for(v1 = 0; v1 < this.val$contactsToDelete.size(); ++v1) {
                        v2 = this.val$contactsToDelete.get(v1);
                        Object v3 = ContactsController.this.contactsDict.get(v2);
                        if(v3 != null) {
                            ContactsController.this.contacts.remove(v3);
                            ContactsController.this.contactsDict.remove(v2);
                        }
                    }

                    if(!this.val$newContacts.isEmpty()) {
                        ContactsController.this.updateUnregisteredContacts();
                        ContactsController.this.performWriteContactsToPhoneBook();
                    }

                    ContactsController.this.performSyncPhoneBook(ContactsController.this.getContactsCopy(ContactsController.this.contactsBook), false, false, false, false, true, false);
                    ContactsController.this.buildContactsSectionsArrays(this.val$newContacts.isEmpty() ^ 1);
                    NotificationCenter.getInstance(ContactsController.this.currentAccount).postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
                }
            });
        }
    }

    private void buildContactsSectionsArrays(boolean arg8) {
        Object v4_2;
        if(arg8) {
            Collections.sort(this.contacts, new Comparator() {
                public int compare(Object arg1, Object arg2) {
                    return this.compare(((TL_contact)arg1), ((TL_contact)arg2));
                }

                public int compare(TL_contact arg2, TL_contact arg3) {
                    return UserObject.getFirstName(MessagesController.getInstance(ContactsController.this.currentAccount).getUser(Integer.valueOf(arg2.user_id))).compareTo(UserObject.getFirstName(MessagesController.getInstance(ContactsController.this.currentAccount).getUser(Integer.valueOf(arg3.user_id))));
                }
            });
        }

        HashMap v8 = new HashMap();
        ArrayList v0 = new ArrayList();
        int v2;
        for(v2 = 0; v2 < this.contacts.size(); ++v2) {
            Object v3 = this.contacts.get(v2);
            User v4 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((TL_contact)v3).user_id));
            if(v4 == null) {
            }
            else {
                String v4_1 = UserObject.getFirstName(v4);
                if(v4_1.length() > 1) {
                    v4_1 = v4_1.substring(0, 1);
                }

                v4_1 = v4_1.length() == 0 ? "#" : v4_1.toUpperCase();
                Object v5 = this.sectionsToReplace.get(v4_1);
                if(v5 != null) {
                    v4_2 = v5;
                }

                v5 = v8.get(v4_2);
                if(v5 == null) {
                    ArrayList v5_1 = new ArrayList();
                    v8.put(v4_2, v5_1);
                    v0.add(v4_2);
                }

                ((ArrayList)v5).add(v3);
            }
        }

        Collections.sort(((List)v0), new Comparator() {
            public int compare(Object arg1, Object arg2) {
                return this.compare(((String)arg1), ((String)arg2));
            }

            public int compare(String arg4, String arg5) {
                int v1 = arg4.charAt(0);
                int v0 = arg5.charAt(0);
                int v2 = 35;
                if(v1 == v2) {
                    return 1;
                }

                if(v0 == v2) {
                    return -1;
                }

                return arg4.compareTo(arg5);
            }
        });
        this.usersSectionsDict = v8;
        this.sortedUsersSectionsArray = v0;
    }

    public void checkAppAccount() {
        int v6;
        int v4;
        Account[] v2;
        AccountManager v0 = AccountManager.get(ApplicationLoader.applicationContext);
        Account v1 = null;
        try {
            v2 = v0.getAccountsByType("org.telegram.messenger");
            this.systemAccount = v1;
            v4 = 0;
            while(true) {
            label_8:
                if(v4 >= v2.length) {
                    goto label_40;
                }

                Account v5 = v2[v4];
                v6 = 0;
                while(true) {
                label_12:
                    if(v6 < 3) {
                        User v7 = UserConfig.getInstance(v6).getCurrentUser();
                        if(v7 != null) {
                            String v8 = v5.name;
                            StringBuilder v9 = new StringBuilder();
                            v9.append("");
                            v9.append(v7.id);
                            if(v8.equals(v9.toString())) {
                                if(v6 == this.currentAccount) {
                                    this.systemAccount = v5;
                                }

                                break;
                            }
                        }

                        goto label_32;
                    }
                    else {
                        goto label_34;
                    }
                }
            }
        }
        catch(Throwable ) {
            goto label_40;
        }

        int v5_1 = 1;
        goto label_35;
    label_32:
        ++v6;
        goto label_12;
    label_34:
        v5_1 = 0;
    label_35:
        if(v5_1 == 0) {
            try {
                v0.removeAccount(v2[v4], ((AccountManagerCallback)v1), ((Handler)v1));
                goto label_38;
            }
            catch(Throwable ) {
            label_40:
                if(UserConfig.getInstance(this.currentAccount).isClientActivated()) {
                    this.readContacts();
                    if(this.systemAccount == null) {
                        try {
                            StringBuilder v3 = new StringBuilder();
                            v3.append("");
                            v3.append(UserConfig.getInstance(this.currentAccount).getClientUserId());
                            this.systemAccount = new Account(v3.toString(), "org.telegram.messenger");
                            v0.addAccountExplicitly(this.systemAccount, "", ((Bundle)v1));
                            return;
                        }
                        catch(Exception ) {
                            return;
                        }
                    }
                }

                return;
            }
            catch(Exception ) {
            label_38:
                ++v4;
                goto label_8;
            }
        }

        goto label_38;
    }

    public void checkContacts() {
        Utilities.globalQueue.postRunnable(new Runnable() {
            public void run() {
                if(ContactsController.this.checkContactsInternal()) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("detected contacts change");
                    }

                    ContactsController.this.performSyncPhoneBook(ContactsController.this.getContactsCopy(ContactsController.this.contactsBook), true, false, true, false, true, false);
                }
            }
        });
    }

    private boolean checkContactsInternal() {
        Throwable v1_2;
        Cursor v2_3;
        Cursor v1_1;
        ContentResolver v2;
        boolean v0 = false;
        try {
            if(!this.hasContactsPermission()) {
                return 0;
            }

            v2 = ApplicationLoader.applicationContext.getContentResolver();
            v1_1 = null;
        }
        catch(Exception v1) {
            goto label_58;
        }

        try {
            v2_3 = v2.query(ContactsContract$RawContacts.CONTENT_URI, new String[]{"version"}, null, null, null);
            if(v2_3 == null) {
                goto label_38;
            }
        }
        catch(Throwable v2_1) {
            Throwable v9_1 = v2_1;
            v2_3 = v1_1;
            v1_2 = v9_1;
            goto label_54;
        }
        catch(Exception v2_2) {
            Exception v9 = v2_2;
            v2_3 = v1_1;
            v1 = v9;
            goto label_50;
        }

        try {
            StringBuilder v1_3 = new StringBuilder();
            while(v2_3.moveToNext()) {
                v1_3.append(v2_3.getString(v2_3.getColumnIndex("version")));
            }

            String v1_4 = v1_3.toString();
            if(this.lastContactsVersions.length() != 0 && !this.lastContactsVersions.equals(v1_4)) {
                v0 = true;
            }

            this.lastContactsVersions = v1_4;
            goto label_38;
        }
        catch(Throwable v1_2) {
        }
        catch(Exception v1) {
            try {
            label_50:
                FileLog.e(((Throwable)v1));
                if(v2_3 == null) {
                    return v0;
                }
            }
            catch(Throwable v1_2) {
                goto label_54;
            }

            goto label_39;
        }

    label_54:
        if(v2_3 != null) {
            try {
                v2_3.close();
            label_56:
                throw v1_2;
            }
            catch(Exception v1) {
                goto label_58;
            }
        }

        goto label_56;
    label_38:
        if(v2_3 != null) {
            try {
            label_39:
                v2_3.close();
            }
            catch(Exception v1) {
            label_58:
                FileLog.e(((Throwable)v1));
            }
        }

        return v0;
    }

    public void checkInviteText() {
        SharedPreferences v0 = MessagesController.getMainSettings(this.currentAccount);
        this.inviteLink = v0.getString("invitelink", null);
        int v0_1 = v0.getInt("invitelinktime", 0);
        if(!this.updatingInviteLink && (this.inviteLink == null || Math.abs(System.currentTimeMillis() / 1000 - (((long)v0_1))) >= 86400)) {
            this.updatingInviteLink = true;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_help_getInviteText(), new RequestDelegate() {
                public void run(TLObject arg1, TL_error arg2) {
                    if(arg1 != null && ((TL_help_inviteText)arg1).message.length() != 0) {
                        AndroidUtilities.runOnUIThread(new Runnable(((TL_help_inviteText)arg1)) {
                            public void run() {
                                this.this$1.this$0.updatingInviteLink = false;
                                SharedPreferences$Editor v0 = MessagesController.getMainSettings(this.this$1.this$0.currentAccount).edit();
                                v0.putString("invitelink", ContactsController.access$702(this.this$1.this$0, this.val$res.message));
                                v0.putInt("invitelinktime", ((int)(System.currentTimeMillis() / 1000)));
                                v0.commit();
                            }
                        });
                    }
                }
            }, 2);
        }
    }

    public void cleanup() {
        this.contactsBook.clear();
        this.contactsBookSPhones.clear();
        this.phoneBookContacts.clear();
        this.contacts.clear();
        this.contactsDict.clear();
        this.usersSectionsDict.clear();
        this.usersMutualSectionsDict.clear();
        this.sortedUsersSectionsArray.clear();
        this.sortedUsersMutualSectionsArray.clear();
        this.delayedContactsUpdate.clear();
        this.contactsByPhone.clear();
        this.contactsByShortPhone.clear();
        this.phoneBookSectionsDict.clear();
        this.phoneBookSectionsArray.clear();
        this.loadingContacts = false;
        this.contactsSyncInProgress = false;
        this.contactsLoaded = false;
        this.contactsBookLoaded = false;
        this.lastContactsVersions = "";
        this.loadingDeleteInfo = 0;
        this.deleteAccountTTL = 0;
        this.loadingLastSeenInfo = 0;
        this.loadingGroupInfo = 0;
        this.loadingCallsInfo = 0;
        Utilities.globalQueue.postRunnable(new Runnable() {
            public void run() {
                ContactsController.this.migratingContacts = false;
                ContactsController.this.completedRequestsCount = 0;
            }
        });
        this.privacyRules = null;
    }

    public void deleteAllContacts(Runnable arg7) {
        this.resetImportedContacts();
        TL_contacts_deleteContacts v0 = new TL_contacts_deleteContacts();
        int v1 = this.contacts.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.id.add(MessagesController.getInstance(this.currentAccount).getInputUser(this.contacts.get(v2).user_id));
        }

        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate(arg7) {
            public void run(TLObject arg1, TL_error arg2) {
                if((arg1 instanceof TL_boolTrue)) {
                    ContactsController.this.contactsBookSPhones.clear();
                    ContactsController.this.contactsBook.clear();
                    ContactsController.this.completedRequestsCount = 0;
                    ContactsController.this.migratingContacts = false;
                    ContactsController.this.contactsSyncInProgress = false;
                    ContactsController.this.contactsLoaded = false;
                    ContactsController.this.loadingContacts = false;
                    ContactsController.this.contactsBookLoaded = false;
                    ContactsController.this.lastContactsVersions = "";
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            int v6;
                            int v4;
                            AccountManager v0 = AccountManager.get(ApplicationLoader.applicationContext);
                            Account v1 = null;
                            try {
                                Account[] v3 = v0.getAccountsByType("org.telegram.messenger");
                                this.this$1.this$0.systemAccount = v1;
                                v4 = 0;
                                while(true) {
                                label_10:
                                    if(v4 >= v3.length) {
                                        goto label_35;
                                    }

                                    Account v5 = v3[v4];
                                    v6 = 0;
                                    while(true) {
                                    label_14:
                                        if(v6 < 3) {
                                            User v7 = UserConfig.getInstance(v6).getCurrentUser();
                                            if(v7 != null) {
                                                String v8 = v5.name;
                                                StringBuilder v9 = new StringBuilder();
                                                v9.append("");
                                                v9.append(v7.id);
                                                if(v8.equals(v9.toString())) {
                                                    v0.removeAccount(v5, ((AccountManagerCallback)v1), ((Handler)v1));
                                                    goto label_33;
                                                }
                                            }

                                            break;
                                        }

                                        goto label_33;
                                    }
                                }
                            }
                            catch(Throwable ) {
                                goto label_35;
                            }

                            ++v6;
                            goto label_14;
                        label_33:
                            ++v4;
                            goto label_10;
                            try {
                            label_35:
                                ContactsController v3_1 = this.this$1.this$0;
                                StringBuilder v5_1 = new StringBuilder();
                                v5_1.append("");
                                v5_1.append(UserConfig.getInstance(this.this$1.this$0.currentAccount).getClientUserId());
                                v3_1.systemAccount = new Account(v5_1.toString(), "org.telegram.messenger");
                                v0.addAccountExplicitly(this.this$1.this$0.systemAccount, "", ((Bundle)v1));
                                goto label_57;
                            }
                            catch(Exception ) {
                            label_57:
                                MessagesStorage.getInstance(this.this$1.this$0.currentAccount).putCachedPhoneBook(new HashMap(), false, true);
                                MessagesStorage.getInstance(this.this$1.this$0.currentAccount).putContacts(new ArrayList(), true);
                                this.this$1.this$0.phoneBookContacts.clear();
                                this.this$1.this$0.contacts.clear();
                                this.this$1.this$0.contactsDict.clear();
                                this.this$1.this$0.usersSectionsDict.clear();
                                this.this$1.this$0.usersMutualSectionsDict.clear();
                                this.this$1.this$0.sortedUsersSectionsArray.clear();
                                this.this$1.this$0.phoneBookSectionsDict.clear();
                                this.this$1.this$0.phoneBookSectionsArray.clear();
                                this.this$1.this$0.delayedContactsUpdate.clear();
                                this.this$1.this$0.sortedUsersMutualSectionsArray.clear();
                                this.this$1.this$0.contactsByPhone.clear();
                                this.this$1.this$0.contactsByShortPhone.clear();
                                NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
                                this.this$1.this$0.loadContacts(false, 0);
                                this.this$1.val$runnable.run();
                                return;
                            }
                        }
                    });
                }
            }
        });
    }

    public void deleteContact(ArrayList arg6) {
        if(arg6 != null) {
            if(arg6.isEmpty()) {
            }
            else {
                TL_contacts_deleteContacts v0 = new TL_contacts_deleteContacts();
                ArrayList v1 = new ArrayList();
                Iterator v2 = arg6.iterator();
                while(v2.hasNext()) {
                    Object v3 = v2.next();
                    InputUser v4 = MessagesController.getInstance(this.currentAccount).getInputUser(((User)v3));
                    if(v4 == null) {
                    }
                    else {
                        v1.add(Integer.valueOf(((User)v3).id));
                        v0.id.add(v4);
                    }
                }

                ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate(v1, arg6) {
                    public void run(TLObject arg4, TL_error arg5) {
                        if(arg5 != null) {
                            return;
                        }

                        MessagesStorage.getInstance(ContactsController.this.currentAccount).deleteContacts(this.val$uids);
                        Utilities.phoneBookQueue.postRunnable(new Runnable() {
                            public void run() {
                                Iterator v0 = this.this$1.val$users.iterator();
                                while(v0.hasNext()) {
                                    this.this$1.this$0.deleteContactFromPhoneBook(v0.next().id);
                                }
                            }
                        });
                        int v4;
                        for(v4 = 0; v4 < this.val$users.size(); ++v4) {
                            Object v5 = this.val$users.get(v4);
                            if(TextUtils.isEmpty(((User)v5).phone)) {
                            }
                            else {
                                UserObject.getUserName(((User)v5));
                                MessagesStorage.getInstance(ContactsController.this.currentAccount).applyPhoneBookUpdates(((User)v5).phone, "");
                                Object v0 = ContactsController.this.contactsBookSPhones.get(((User)v5).phone);
                                if(v0 != null) {
                                    int v5_1 = ((Contact)v0).shortPhones.indexOf(((User)v5).phone);
                                    if(v5_1 != -1) {
                                        ((Contact)v0).phoneDeleted.set(v5_1, Integer.valueOf(1));
                                    }
                                }
                            }
                        }

                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                Iterator v0 = this.this$1.val$users.iterator();
                                int v2;
                                for(v2 = 0; v0.hasNext(); v2 = 1) {
                                    Object v3 = v0.next();
                                    Object v5 = this.this$1.this$0.contactsDict.get(Integer.valueOf(((User)v3).id));
                                    if(v5 == null) {
                                        continue;
                                    }

                                    this.this$1.this$0.contacts.remove(v5);
                                    this.this$1.this$0.contactsDict.remove(Integer.valueOf(((User)v3).id));
                                }

                                if(v2 != 0) {
                                    this.this$1.this$0.buildContactsSectionsArrays(false);
                                }

                                NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(1)});
                                NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
                            }
                        });
                    }
                });
            }
        }
    }

    private void deleteContactFromPhoneBook(int arg5) {
        if(!this.hasContactsPermission()) {
            return;
        }

        Object v0 = this.observerLock;
        __monitor_enter(v0);
        try {
            this.ignoreChanges = true;
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            try {
            label_45:
                __monitor_exit(v0);
            }
            catch(Throwable v5) {
                goto label_45;
            }

            throw v5;
        }

        try {
            ContentResolver v0_1 = ApplicationLoader.applicationContext.getContentResolver();
            Uri v1 = ContactsContract$RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", this.systemAccount.name).appendQueryParameter("account_type", this.systemAccount.type).build();
            v0_1.delete(v1, "sync2 = " + arg5, null);
        }
        catch(Exception v5_1) {
            FileLog.e(((Throwable)v5_1));
        }

        Object v5_2 = this.observerLock;
        __monitor_enter(v5_2);
        try {
            this.ignoreChanges = false;
            __monitor_exit(v5_2);
            return;
        label_42:
            __monitor_exit(v5_2);
        }
        catch(Throwable v0_2) {
            goto label_42;
        }

        throw v0_2;
    }

    public void deleteUnknownAppAccounts() {
        int v6;
        int v4;
        Account[] v2;
        AccountManager v1;
        Account v0 = null;
        try {
            this.systemAccount = v0;
            v1 = AccountManager.get(ApplicationLoader.applicationContext);
            v2 = v1.getAccountsByType("org.telegram.messenger");
            v4 = 0;
            while(true) {
            label_8:
                if(v4 >= v2.length) {
                    return;
                }

                Account v5 = v2[v4];
                v6 = 0;
                while(true) {
                label_12:
                    if(v6 < 3) {
                        User v7 = UserConfig.getInstance(v6).getCurrentUser();
                        if(v7 != null) {
                            String v8 = v5.name;
                            StringBuilder v9 = new StringBuilder();
                            v9.append("");
                            v9.append(v7.id);
                            if(v8.equals(v9.toString())) {
                                break;
                            }
                        }

                        goto label_29;
                    }
                    else {
                        goto label_31;
                    }
                }
            }
        }
        catch(Exception v0_1) {
            goto label_38;
        }

        int v5_1 = 1;
        goto label_32;
    label_29:
        ++v6;
        goto label_12;
    label_31:
        v5_1 = 0;
    label_32:
        if(v5_1 == 0) {
            try {
                v1.removeAccount(v2[v4], ((AccountManagerCallback)v0), ((Handler)v0));
                goto label_35;
            }
            catch(Exception ) {
            label_35:
                ++v4;
                goto label_8;
            }
        }

        goto label_35;
    label_38:
        v0_1.printStackTrace();
    }

    public void forceImportContacts() {
        Utilities.globalQueue.postRunnable(new Runnable() {
            public void run() {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("force import contacts");
                }

                ContactsController.this.performSyncPhoneBook(new HashMap(), true, true, true, true, false, false);
            }
        });
    }

    public static String formatName(String arg3, String arg4) {
        if(arg3 != null) {
            arg3 = arg3.trim();
        }

        if(arg4 != null) {
            arg4 = arg4.trim();
        }

        int v1 = 0;
        int v2 = arg3 != null ? arg3.length() : 0;
        if(arg4 != null) {
            v1 = arg4.length();
        }

        StringBuilder v0 = new StringBuilder(v2 + v1 + 1);
        if(LocaleController.nameDisplayOrder == 1) {
            if(arg3 == null || arg3.length() <= 0) {
                if(arg4 == null) {
                }
                else if(arg4.length() > 0) {
                    goto label_31;
                }

                goto label_47;
            }
            else {
                v0.append(arg3);
                if(arg4 == null) {
                    goto label_47;
                }
                else if(arg4.length() > 0) {
                    v0.append(" ");
                }
                else {
                    goto label_47;
                }
            }

        label_31:
            v0.append(arg4);
        }
        else {
            if(arg4 == null || arg4.length() <= 0) {
                if(arg3 == null) {
                }
                else if(arg3.length() > 0) {
                    goto label_46;
                }

                goto label_47;
            }
            else {
                v0.append(arg4);
                if(arg3 == null) {
                    goto label_47;
                }
                else if(arg3.length() > 0) {
                    v0.append(" ");
                }
                else {
                    goto label_47;
                }
            }

        label_46:
            v0.append(arg3);
        }

    label_47:
        return v0.toString();
    }

    public HashMap getContactsCopy(HashMap arg6) {
        HashMap v0 = new HashMap();
        Iterator v6 = arg6.entrySet().iterator();
        while(v6.hasNext()) {
            Object v1 = v6.next();
            Contact v2 = new Contact();
            v1 = ((Map$Entry)v1).getValue();
            v2.phoneDeleted.addAll(((Contact)v1).phoneDeleted);
            v2.phones.addAll(((Contact)v1).phones);
            v2.phoneTypes.addAll(((Contact)v1).phoneTypes);
            v2.shortPhones.addAll(((Contact)v1).shortPhones);
            v2.first_name = ((Contact)v1).first_name;
            v2.last_name = ((Contact)v1).last_name;
            v2.contact_id = ((Contact)v1).contact_id;
            v2.key = ((Contact)v1).key;
            v0.put(v2.key, v2);
        }

        return v0;
    }

    private int getContactsHash(ArrayList arg11) {
        int v5_1;
        ArrayList v0 = new ArrayList(((Collection)arg11));
        Collections.sort(((List)v0), new Comparator() {
            public int compare(Object arg1, Object arg2) {
                return this.compare(((TL_contact)arg1), ((TL_contact)arg2));
            }

            public int compare(TL_contact arg3, TL_contact arg4) {
                if(arg3.user_id > arg4.user_id) {
                    return 1;
                }

                if(arg3.user_id < arg4.user_id) {
                    return -1;
                }

                return 0;
            }
        });
        int v11 = v0.size();
        int v1 = -1;
        long v3 = 0;
        int v2;
        for(v2 = -1; v2 < v11; ++v2) {
            long v5 = 20261;
            long v7 = 2147483648L;
            if(v2 == v1) {
                v3 = v3 * v5 + v7;
                v5_1 = UserConfig.getInstance(this.currentAccount).contactsSavedCount;
            }
            else {
                v3 = v3 * v5 + v7;
                v5_1 = v0.get(v2).user_id;
            }

            v3 = (v3 + (((long)v5_1))) % v7;
        }

        return ((int)v3);
    }

    public int getDeleteAccountTTL() {
        return this.deleteAccountTTL;
    }

    public static ContactsController getInstance(int arg3) {
        ContactsController v0 = ContactsController.Instance[arg3];
        if(v0 == null) {
            Class v1 = ContactsController.class;
            __monitor_enter(v1);
            try {
                v0 = ContactsController.Instance[arg3];
                if(v0 == null) {
                    ContactsController[] v0_1 = ContactsController.Instance;
                    ContactsController v2 = new ContactsController(arg3);
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

    public String getInviteText(int arg7) {
        Object[] v3;
        String v7;
        String v0 = this.inviteLink == null ? "https://telegram.org/dl" : this.inviteLink;
        int v1 = 2131625001;
        if(arg7 <= 1) {
            v7 = "InviteText2";
            v3 = new Object[]{v0};
            goto label_12;
        }

        try {
            return String.format(LocaleController.getPluralString("InviteTextNum", arg7), Integer.valueOf(arg7), v0);
        }
        catch(Exception ) {
            v7 = "InviteText2";
            v3 = new Object[]{v0};
        }

    label_12:
        return LocaleController.formatString(v7, v1, v3);
    }

    public boolean getLoadingCallsInfo() {
        boolean v0 = this.loadingCallsInfo != 2 ? true : false;
        return v0;
    }

    public boolean getLoadingDeleteInfo() {
        boolean v0 = this.loadingDeleteInfo != 2 ? true : false;
        return v0;
    }

    public boolean getLoadingGroupInfo() {
        boolean v0 = this.loadingGroupInfo != 2 ? true : false;
        return v0;
    }

    public boolean getLoadingLastSeenInfo() {
        boolean v0 = this.loadingLastSeenInfo != 2 ? true : false;
        return v0;
    }

    public ArrayList getPrivacyRules(int arg2) {
        if(arg2 == 2) {
            return this.callPrivacyRules;
        }

        if(arg2 == 1) {
            return this.groupPrivacyRules;
        }

        return this.privacyRules;
    }

    private boolean hasContactsPermission() {
        Cursor v3;
        boolean v1 = false;
        if(Build$VERSION.SDK_INT >= 23) {
            if(ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_CONTACTS") == 0) {
                v1 = true;
            }

            return v1;
        }

        Cursor v0 = null;
        try {
            v3 = ApplicationLoader.applicationContext.getContentResolver().query(ContactsContract$CommonDataKinds$Phone.CONTENT_URI, this.projectionPhones, null, null, null);
            if(v3 == null) {
                goto label_35;
            }

            goto label_21;
        }
        catch(Throwable v1_1) {
        }
        catch(Throwable v1_1) {
            goto label_44;
            try {
            label_21:
                if(v3.getCount() != 0) {
                    goto label_24;
                }

                goto label_23;
            }
            catch(Throwable v0_1) {
                v1_1 = v0_1;
                v0 = v3;
            }
            catch(Throwable v0_1) {
                v1_1 = v0_1;
                v0 = v3;
                try {
                label_44:
                    FileLog.e(v1_1);
                    if(v0 == null) {
                        return 1;
                    }
                }
                catch(Throwable v1_1) {
                    goto label_51;
                }

                try {
                    v0.close();
                    return 1;
                }
                catch(Exception v0_2) {
                    goto label_49;
                }
            }
        }

    label_51:
        if(v0 != null) {
            try {
                v0.close();
            }
            catch(Exception v0_2) {
                FileLog.e(((Throwable)v0_2));
            }

            goto label_56;
        }
        else {
        label_56:
            throw v1_1;
        label_23:
            goto label_35;
        label_24:
            if(v3 != null) {
                try {
                    v3.close();
                }
                catch(Exception v0_2) {
                label_49:
                    FileLog.e(((Throwable)v0_2));
                }

                return 1;
            }
            else {
                return 1;
            }
        }

    label_35:
        if(v3 != null) {
            try {
                v3.close();
            }
            catch(Exception v0_2) {
                FileLog.e(((Throwable)v0_2));
            }
        }

        return 0;
    }

    public boolean isLoadingContacts() {
        Object v0 = this.loadContactsSync;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.loadingContacts;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    private boolean isNotValidNameString(String arg8) {
        boolean v1 = true;
        if(TextUtils.isEmpty(((CharSequence)arg8))) {
            return 1;
        }

        int v0 = arg8.length();
        int v3 = 0;
        int v4 = 0;
        while(v3 < v0) {
            int v5 = arg8.charAt(v3);
            if(v5 >= 48 && v5 <= 57) {
                ++v4;
            }

            ++v3;
        }

        if(v4 > 3) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public void loadContacts(boolean arg3, int arg4) {
        Object v0 = this.loadContactsSync;
        __monitor_enter(v0);
        try {
            this.loadingContacts = true;
            __monitor_exit(v0);
            if(!arg3) {
                goto label_14;
            }
        }
        catch(Throwable v3) {
            try {
            label_28:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_28;
            }

            throw v3;
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("load contacts from cache");
        }

        MessagesStorage.getInstance(this.currentAccount).getContacts();
        return;
    label_14:
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("load contacts from server");
        }

        TL_contacts_getContacts v3_1 = new TL_contacts_getContacts();
        v3_1.hash = arg4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v3_1), new RequestDelegate(arg4) {
            public void run(TLObject arg6, TL_error arg7) {
                if(arg7 == null) {
                    if(this.val$hash != 0 && ((arg6 instanceof TL_contacts_contactsNotModified))) {
                        ContactsController.this.contactsLoaded = true;
                        if(!ContactsController.this.delayedContactsUpdate.isEmpty() && (ContactsController.this.contactsBookLoaded)) {
                            ContactsController.this.applyContactsUpdates(ContactsController.this.delayedContactsUpdate, null, null, null);
                            ContactsController.this.delayedContactsUpdate.clear();
                        }

                        UserConfig.getInstance(ContactsController.this.currentAccount).lastContactsSyncTime = ((int)(System.currentTimeMillis() / 1000));
                        UserConfig.getInstance(ContactsController.this.currentAccount).saveConfig(false);
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                Object v0 = this.this$1.this$0.loadContactsSync;
                                __monitor_enter(v0);
                                try {
                                    this.this$1.this$0.loadingContacts = false;
                                    __monitor_exit(v0);
                                }
                                catch(Throwable v1) {
                                    try {
                                    label_18:
                                        __monitor_exit(v0);
                                    }
                                    catch(Throwable v1) {
                                        goto label_18;
                                    }

                                    throw v1;
                                }

                                NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
                            }
                        });
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("load contacts don\'t change");
                        }

                        return;
                    }

                    UserConfig.getInstance(ContactsController.this.currentAccount).contactsSavedCount = ((contacts_Contacts)arg6).saved_count;
                    UserConfig.getInstance(ContactsController.this.currentAccount).saveConfig(false);
                    ContactsController.this.processLoadedContacts(((contacts_Contacts)arg6).contacts, ((contacts_Contacts)arg6).users, 0);
                }
            }
        });
    }

    public void loadPrivacySettings() {
        TL_account_getPrivacy v0;
        if(this.loadingDeleteInfo == 0) {
            this.loadingDeleteInfo = 1;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account_getAccountTTL(), new RequestDelegate() {
                public void run(TLObject arg2, TL_error arg3) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg3, arg2) {
                        public void run() {
                            if(this.val$error == null) {
                                this.this$1.this$0.deleteAccountTTL = this.val$response.days;
                                this.this$1.this$0.loadingDeleteInfo = 2;
                            }
                            else {
                                this.this$1.this$0.loadingDeleteInfo = 0;
                            }

                            NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
                        }
                    });
                }
            });
        }

        if(this.loadingLastSeenInfo == 0) {
            this.loadingLastSeenInfo = 1;
            v0 = new TL_account_getPrivacy();
            v0.key = new TL_inputPrivacyKeyStatusTimestamp();
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate() {
                public void run(TLObject arg2, TL_error arg3) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg3, arg2) {
                        public void run() {
                            if(this.val$error == null) {
                                TLObject v0 = this.val$response;
                                MessagesController.getInstance(this.this$1.this$0.currentAccount).putUsers(((TL_account_privacyRules)v0).users, false);
                                this.this$1.this$0.privacyRules = ((TL_account_privacyRules)v0).rules;
                                this.this$1.this$0.loadingLastSeenInfo = 2;
                            }
                            else {
                                this.this$1.this$0.loadingLastSeenInfo = 0;
                            }

                            NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
                        }
                    });
                }
            });
        }

        if(this.loadingCallsInfo == 0) {
            this.loadingCallsInfo = 1;
            v0 = new TL_account_getPrivacy();
            v0.key = new TL_inputPrivacyKeyPhoneCall();
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate() {
                public void run(TLObject arg2, TL_error arg3) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg3, arg2) {
                        public void run() {
                            if(this.val$error == null) {
                                TLObject v0 = this.val$response;
                                MessagesController.getInstance(this.this$1.this$0.currentAccount).putUsers(((TL_account_privacyRules)v0).users, false);
                                this.this$1.this$0.callPrivacyRules = ((TL_account_privacyRules)v0).rules;
                                this.this$1.this$0.loadingCallsInfo = 2;
                            }
                            else {
                                this.this$1.this$0.loadingCallsInfo = 0;
                            }

                            NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
                        }
                    });
                }
            });
        }

        if(this.loadingGroupInfo == 0) {
            this.loadingGroupInfo = 1;
            v0 = new TL_account_getPrivacy();
            v0.key = new TL_inputPrivacyKeyChatInvite();
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v0), new RequestDelegate() {
                public void run(TLObject arg2, TL_error arg3) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg3, arg2) {
                        public void run() {
                            if(this.val$error == null) {
                                TLObject v0 = this.val$response;
                                MessagesController.getInstance(this.this$1.this$0.currentAccount).putUsers(((TL_account_privacyRules)v0).users, false);
                                this.this$1.this$0.groupPrivacyRules = ((TL_account_privacyRules)v0).rules;
                                this.this$1.this$0.loadingGroupInfo = 2;
                            }
                            else {
                                this.this$1.this$0.loadingGroupInfo = 0;
                            }

                            NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
                        }
                    });
                }
            });
        }

        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
    }

    protected void markAsContacted(String arg3) {
        if(arg3 == null) {
            return;
        }

        Utilities.phoneBookQueue.postRunnable(new Runnable(arg3) {
            public void run() {
                Uri v0 = Uri.parse(this.val$contactId);
                ContentValues v1 = new ContentValues();
                v1.put("last_time_contacted", Long.valueOf(System.currentTimeMillis()));
                ApplicationLoader.applicationContext.getContentResolver().update(v0, v1, null, null);
            }
        });
    }

    private void mergePhonebookAndTelegramContacts(HashMap arg9, ArrayList arg10, HashMap arg11) {
        Utilities.globalQueue.postRunnable(new Runnable(new ArrayList(this.contacts), arg11, arg9, arg10) {
            public void run() {
                ArrayList v5_1;
                int v0 = this.val$contactsCopy.size();
                int v2;
                for(v2 = 0; v2 < v0; ++v2) {
                    User v3 = MessagesController.getInstance(ContactsController.this.currentAccount).getUser(Integer.valueOf(this.val$contactsCopy.get(v2).user_id));
                    if(v3 != null) {
                        if(TextUtils.isEmpty(v3.phone)) {
                        }
                        else {
                            Object v4 = this.val$phoneBookByShortPhonesFinal.get(v3.phone.substring(Math.max(0, v3.phone.length() - 7)));
                            if(v4 == null) {
                                String v4_1 = Contact.getLetter(v3.first_name, v3.last_name);
                                Object v5 = this.val$phoneBookSectionsDictFinal.get(v4_1);
                                if(v5 == null) {
                                    v5_1 = new ArrayList();
                                    this.val$phoneBookSectionsDictFinal.put(v4_1, v5_1);
                                    this.val$phoneBookSectionsArrayFinal.add(v4_1);
                                }

                                v5_1.add(v3);
                            }
                            else if(((Contact)v4).user == null) {
                                ((Contact)v4).user = v3;
                            }
                        }
                    }
                }

                Iterator v0_1 = this.val$phoneBookSectionsDictFinal.values().iterator();
                while(v0_1.hasNext()) {
                    Collections.sort(v0_1.next(), new Comparator() {
                        public int compare(Object arg2, Object arg3) {
                            String v3_1;
                            String v2_1;
                            String v0;
                            if((arg2 instanceof User)) {
                                v0 = ((User)arg2).first_name;
                                goto label_3;
                            }
                            else if(!(arg2 instanceof Contact)) {
                                v2_1 = "";
                            }
                            else if(((Contact)arg2).user != null) {
                                v0 = ((Contact)arg2).user.first_name;
                                User v2 = ((Contact)arg2).user;
                            label_3:
                                v2_1 = v2.last_name;
                                goto label_4;
                            }
                            else {
                                v0 = ((Contact)arg2).first_name;
                                v2_1 = ((Contact)arg2).last_name;
                            label_4:
                                v2_1 = ContactsController.formatName(v0, v2_1);
                            }

                            if((arg3 instanceof User)) {
                                v0 = ((User)arg3).first_name;
                                goto label_21;
                            }
                            else if(!(arg3 instanceof Contact)) {
                                v3_1 = "";
                            }
                            else if(((Contact)arg3).user != null) {
                                v0 = ((Contact)arg3).user.first_name;
                                User v3 = ((Contact)arg3).user;
                            label_21:
                                v3_1 = v3.last_name;
                                goto label_22;
                            }
                            else {
                                v0 = ((Contact)arg3).first_name;
                                v3_1 = ((Contact)arg3).last_name;
                            label_22:
                                v3_1 = ContactsController.formatName(v0, v3_1);
                            }

                            return v2_1.compareTo(v3_1);
                        }
                    });
                }

                Collections.sort(this.val$phoneBookSectionsArrayFinal, new Comparator() {
                    public int compare(Object arg1, Object arg2) {
                        return this.compare(((String)arg1), ((String)arg2));
                    }

                    public int compare(String arg4, String arg5) {
                        int v1 = arg4.charAt(0);
                        int v0 = arg5.charAt(0);
                        int v2 = 35;
                        if(v1 == v2) {
                            return 1;
                        }

                        if(v0 == v2) {
                            return -1;
                        }

                        return arg4.compareTo(arg5);
                    }
                });
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        this.this$1.this$0.phoneBookSectionsArray = this.this$1.val$phoneBookSectionsArrayFinal;
                        this.this$1.this$0.phoneBookSectionsDict = this.this$1.val$phoneBookSectionsDictFinal;
                    }
                });
            }
        });
    }

    protected void migratePhoneBookToV7(SparseArray arg3) {
        Utilities.globalQueue.postRunnable(new Runnable(arg3) {
            public void run() {
                Object v4;
                if(ContactsController.this.migratingContacts) {
                    return;
                }

                ContactsController.this.migratingContacts = true;
                HashMap v0 = new HashMap();
                HashMap v2 = ContactsController.this.readContactsFromPhoneBook();
                HashMap v3 = new HashMap();
                Iterator v2_1 = v2.entrySet().iterator();
            label_15:
                int v5 = 0;
                if(v2_1.hasNext()) {
                    v4 = v2_1.next().getValue();
                    while(true) {
                        if(v5 >= ((Contact)v4).shortPhones.size()) {
                            goto label_15;
                        }

                        v3.put(((Contact)v4).shortPhones.get(v5), ((Contact)v4).key);
                        ++v5;
                    }
                }

                int v2_2;
                for(v2_2 = 0; v2_2 < this.val$contactHashMap.size(); ++v2_2) {
                    v4 = this.val$contactHashMap.valueAt(v2_2);
                    int v6 = 0;
                    while(v6 < ((Contact)v4).shortPhones.size()) {
                        Object v7 = v3.get(((Contact)v4).shortPhones.get(v6));
                        if(v7 != null) {
                            ((Contact)v4).key = ((String)v7);
                            v0.put(v7, v4);
                        }
                        else {
                            ++v6;
                            continue;
                        }

                        break;
                    }
                }

                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("migrated contacts " + v0.size() + " of " + this.val$contactHashMap.size());
                }

                MessagesStorage.getInstance(ContactsController.this.currentAccount).putCachedPhoneBook(v0, true, false);
            }
        });
    }

    protected void performSyncPhoneBook(HashMap arg13, boolean arg14, boolean arg15, boolean arg16, boolean arg17, boolean arg18, boolean arg19) {
        if(!arg15 && !this.contactsBookLoaded) {
            return;
        }

        Utilities.globalQueue.postRunnable(new Runnable(arg13, arg16, arg14, arg15, arg17, arg18, arg19) {
            public void run() {
                // Method was not decompiled
            }
        });
    }

    private void performWriteContactsToPhoneBook() {
        Utilities.phoneBookQueue.postRunnable(new Runnable(new ArrayList(this.contacts)) {
            public void run() {
                ContactsController.this.performWriteContactsToPhoneBookInternal(this.val$contactsArray);
            }
        });
    }

    private void performWriteContactsToPhoneBookInternal(ArrayList arg11) {
        int v1_1;
        Cursor v1;
        Cursor v0 = null;
        try {
            if(!this.hasContactsPermission()) {
                return;
            }

            v1 = ApplicationLoader.applicationContext.getContentResolver().query(ContactsContract$RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("account_name", this.systemAccount.name).appendQueryParameter("account_type", this.systemAccount.type).build(), new String[]{"_id", "sync2"}, null, null, null);
        }
        catch(Exception v11) {
            goto label_66;
        }
        catch(Throwable v11_1) {
            goto label_64;
        }

        try {
            SparseLongArray v2 = new SparseLongArray();
            if(v1 != null) {
                while(v1.moveToNext()) {
                    v2.put(v1.getInt(1), v1.getLong(0));
                }

                v1.close();
                v1_1 = 0;
            }
            else {
                goto label_54;
            }
        }
        catch(Throwable v11_1) {
            goto label_58;
        }
        catch(Exception v11) {
            goto label_61;
        }

        try {
            while(true) {
            label_40:
                if(v1_1 < arg11.size()) {
                    Object v3 = arg11.get(v1_1);
                    if(v2.indexOfKey(((TL_contact)v3).user_id) < 0) {
                        this.addContactToPhoneBook(MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((TL_contact)v3).user_id)), false);
                    }

                    break;
                }

                goto label_55;
            }
        }
        catch(Exception v11) {
            goto label_66;
        }
        catch(Throwable v11_1) {
            goto label_64;
        }

        ++v1_1;
        goto label_40;
    label_61:
        v0 = v1;
        try {
        label_66:
            FileLog.e(((Throwable)v11));
            if(v0 == null) {
                return;
            }

            goto label_68;
        }
        catch(Throwable v11_1) {
        label_64:
            goto label_70;
        }

    label_58:
        v0 = v1;
    label_70:
        if(v0 != null) {
            v0.close();
        }

        throw v11_1;
    label_54:
        v0 = v1;
    label_55:
        if(v0 != null) {
        label_68:
            v0.close();
        }
    }

    public void processContactsUpdates(ArrayList arg8, ConcurrentHashMap arg9) {
        int v3_1;
        ArrayList v0 = new ArrayList();
        ArrayList v1 = new ArrayList();
        Iterator v2 = arg8.iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            int v5 = -1;
            if(((Integer)v3).intValue() > 0) {
                TL_contact v4 = new TL_contact();
                v4.user_id = ((Integer)v3).intValue();
                v0.add(v4);
                if(this.delayedContactsUpdate.isEmpty()) {
                    continue;
                }

                v3_1 = this.delayedContactsUpdate.indexOf(Integer.valueOf(-((Integer)v3).intValue()));
                if(v3_1 == v5) {
                    continue;
                }
            }
            else {
                if(((Integer)v3).intValue() >= 0) {
                    continue;
                }

                v1.add(Integer.valueOf(-((Integer)v3).intValue()));
                if(this.delayedContactsUpdate.isEmpty()) {
                    continue;
                }

                v3_1 = this.delayedContactsUpdate.indexOf(Integer.valueOf(-((Integer)v3).intValue()));
                if(v3_1 == v5) {
                    continue;
                }
            }

            this.delayedContactsUpdate.remove(v3_1);
        }

        if(!v1.isEmpty()) {
            MessagesStorage.getInstance(this.currentAccount).deleteContacts(v1);
        }

        if(!v0.isEmpty()) {
            MessagesStorage.getInstance(this.currentAccount).putContacts(v0, false);
        }

        if(!this.contactsLoaded || !this.contactsBookLoaded) {
            this.delayedContactsUpdate.addAll(((Collection)arg8));
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("delay update - contacts add = " + v0.size() + " delete = " + v1.size());
            }
        }
        else {
            this.applyContactsUpdates(arg8, arg9, v0, v1);
        }
    }

    public void processLoadedContacts(ArrayList arg2, ArrayList arg3, int arg4) {
        AndroidUtilities.runOnUIThread(new Runnable(arg3, arg4, arg2) {
            public void run() {
                MessagesController v0 = MessagesController.getInstance(ContactsController.this.currentAccount);
                ArrayList v1 = this.val$usersArr;
                int v3 = 0;
                boolean v2 = this.val$from == 1 ? true : false;
                v0.putUsers(v1, v2);
                SparseArray v0_1 = new SparseArray();
                boolean v1_1 = this.val$contactsArr.isEmpty();
                if(!ContactsController.this.contacts.isEmpty()) {
                    int v2_1;
                    for(v2_1 = 0; v2_1 < this.val$contactsArr.size(); ++v2_1) {
                        if(ContactsController.this.contactsDict.get(Integer.valueOf(this.val$contactsArr.get(v2_1).user_id)) != null) {
                            this.val$contactsArr.remove(v2_1);
                            --v2_1;
                        }
                    }

                    this.val$contactsArr.addAll(ContactsController.this.contacts);
                }

                while(v3 < this.val$contactsArr.size()) {
                    User v2_2 = MessagesController.getInstance(ContactsController.this.currentAccount).getUser(Integer.valueOf(this.val$contactsArr.get(v3).user_id));
                    if(v2_2 != null) {
                        v0_1.put(v2_2.id, v2_2);
                    }

                    ++v3;
                }

                Utilities.stageQueue.postRunnable(new Runnable(v0_1, v1_1) {
                    public void run() {
                        ArrayList v8_2;
                        Object v7_2;
                        Object v8_1;
                        String v7_1;
                        ArrayList v19;
                        SparseArray v18;
                        ArrayList v13_1;
                        HashMap v0_2;
                        HashMap v1_3;
                        org.telegram.messenger.ContactsController$14$1 v12 = this;
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("done loading contacts");
                        }

                        long v1 = 1000;
                        if(v12.this$1.val$from == 1 && ((v12.this$1.val$contactsArr.isEmpty()) || Math.abs(System.currentTimeMillis() / v1 - (((long)UserConfig.getInstance(v12.this$1.this$0.currentAccount).lastContactsSyncTime))) >= 86400)) {
                            v12.this$1.this$0.loadContacts(false, v12.this$1.this$0.getContactsHash(v12.this$1.val$contactsArr));
                            if(!v12.this$1.val$contactsArr.isEmpty()) {
                                goto label_40;
                            }

                            return;
                        }

                    label_40:
                        if(v12.this$1.val$from == 0) {
                            UserConfig.getInstance(v12.this$1.this$0.currentAccount).lastContactsSyncTime = ((int)(System.currentTimeMillis() / v1));
                            UserConfig.getInstance(v12.this$1.this$0.currentAccount).saveConfig(false);
                        }

                        int v0;
                        for(v0 = 0; v0 < v12.this$1.val$contactsArr.size(); ++v0) {
                            Object v1_1 = v12.this$1.val$contactsArr.get(v0);
                            if(v12.val$usersDict.get(((TL_contact)v1_1).user_id) == null && ((TL_contact)v1_1).user_id != UserConfig.getInstance(v12.this$1.this$0.currentAccount).getClientUserId()) {
                                v12.this$1.this$0.loadContacts(false, 0);
                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d("contacts are broken, load from server");
                                }

                                return;
                            }
                        }

                        int v1_2 = 2;
                        ArrayList v14 = null;
                        if(v12.this$1.val$from != 1) {
                            MessagesStorage.getInstance(v12.this$1.this$0.currentAccount).putUsersAndChats(v12.this$1.val$usersArr, v14, true, true);
                            MessagesStorage v0_1 = MessagesStorage.getInstance(v12.this$1.this$0.currentAccount);
                            ArrayList v2 = v12.this$1.val$contactsArr;
                            boolean v4 = v12.this$1.val$from != v1_2 ? true : false;
                            v0_1.putContacts(v2, v4);
                        }

                        Collections.sort(v12.this$1.val$contactsArr, new Comparator() {
                            public int compare(Object arg1, Object arg2) {
                                return this.compare(((TL_contact)arg1), ((TL_contact)arg2));
                            }

                            public int compare(TL_contact arg2, TL_contact arg3) {
                                return UserObject.getFirstName(this.this$2.val$usersDict.get(arg2.user_id)).compareTo(UserObject.getFirstName(this.this$2.val$usersDict.get(arg3.user_id)));
                            }
                        });
                        ConcurrentHashMap v2_1 = new ConcurrentHashMap(20, 1f, v1_2);
                        HashMap v4_1 = new HashMap();
                        HashMap v5 = new HashMap();
                        ArrayList v6 = new ArrayList();
                        ArrayList v7 = new ArrayList();
                        SparseArray v8 = new SparseArray();
                        HashMap v9 = new HashMap();
                        HashMap v10 = new HashMap();
                        ArrayList v11 = new ArrayList();
                        ArrayList v15 = new ArrayList();
                        if(!v12.this$1.this$0.contactsBookLoaded) {
                            v1_3 = new HashMap();
                            v0_2 = new HashMap();
                        }
                        else {
                            v0_2 = ((HashMap)v14);
                            v1_3 = v0_2;
                        }

                        int v14_1 = 0;
                        while(v14_1 < v12.this$1.val$contactsArr.size()) {
                            Object v3 = v12.this$1.val$contactsArr.get(v14_1);
                            int v16 = v14_1;
                            Object v13 = v12.val$usersDict.get(((TL_contact)v3).user_id);
                            if(v13 == null) {
                                v13_1 = v7;
                                v18 = v8;
                                v19 = v15;
                            }
                            else {
                                ArrayList v17 = v7;
                                if(((User)v13).id != UserConfig.getInstance(v12.this$1.this$0.currentAccount).getClientUserId()) {
                                    if(((User)v13).status != null && ((User)v13).status.expires > ConnectionsManager.getInstance(v12.this$1.this$0.currentAccount).getCurrentTime()) {
                                        goto label_199;
                                    }

                                    if(MessagesController.getInstance(v12.this$1.this$0.currentAccount).onlinePrivacy.containsKey(Integer.valueOf(((User)v13).id))) {
                                        goto label_199;
                                    }

                                    v18 = v8;
                                }
                                else {
                                label_199:
                                    v8.put(((TL_contact)v3).user_id, v3);
                                    v7_1 = UserObject.getFirstName(((User)v13));
                                    v18 = v8;
                                    if(v7_1.length() > 1) {
                                        v7_1 = v7_1.substring(0, 1);
                                    }

                                    v7_1 = v7_1.length() == 0 ? "#" : v7_1.toUpperCase();
                                    v8_1 = v12.this$1.this$0.sectionsToReplace.get(v7_1);
                                    if(v8_1 != null) {
                                        v7_2 = v8_1;
                                    }

                                    v8_1 = v9.get(v7_1);
                                    if(v8_1 == null) {
                                        v8_2 = new ArrayList();
                                        v9.put(v7_1, v8_2);
                                        v11.add(v7_1);
                                    }

                                    v8_2.add(v3);
                                    if(!((User)v13).mutual_contact) {
                                        goto label_235;
                                    }

                                    v8_1 = v10.get(v7_1);
                                    if(v8_1 == null) {
                                        v8_2 = new ArrayList();
                                        v10.put(v7_1, v8_2);
                                        v15.add(v7_1);
                                    }

                                    v8_2.add(v3);
                                }

                            label_235:
                                v19 = v15;
                                if(!b.f(ApplicationLoader.applicationContext, ((long)((TL_contact)v3).user_id))) {
                                    v2_1.put(Integer.valueOf(((TL_contact)v3).user_id), v3);
                                    if(v1_3 == null || (TextUtils.isEmpty(((User)v13).phone))) {
                                        v14_1 = 0;
                                    }
                                    else {
                                        v1_3.put(((User)v13).phone, v3);
                                        v14_1 = 0;
                                        v0_2.put(((User)v13).phone.substring(Math.max(0, ((User)v13).phone.length() - 7)), v3);
                                    }

                                    v7_1 = UserObject.getFirstName(((User)v13));
                                    if(v7_1.length() > 1) {
                                        v7_1 = v7_1.substring(v14_1, 1);
                                    }

                                    v7_1 = v7_1.length() == 0 ? "#" : v7_1.toUpperCase();
                                    v8_1 = v12.this$1.this$0.sectionsToReplace.get(v7_1);
                                    if(v8_1 != null) {
                                        v7_2 = v8_1;
                                    }

                                    v8_1 = v4_1.get(v7_1);
                                    if(v8_1 == null) {
                                        v8_2 = new ArrayList();
                                        v4_1.put(v7_1, v8_2);
                                        v6.add(v7_1);
                                    }

                                    v8_2.add(v3);
                                    if(((User)v13).mutual_contact) {
                                        v8_1 = v5.get(v7_1);
                                        if(v8_1 == null) {
                                            v8_2 = new ArrayList();
                                            v5.put(v7_1, v8_2);
                                            v13_1 = v17;
                                            v13_1.add(v7_1);
                                        }
                                        else {
                                            v13_1 = v17;
                                        }

                                        v8_2.add(v3);
                                        goto label_309;
                                    }

                                    v13_1 = v17;
                                    goto label_309;
                                }

                                v13_1 = v17;
                                Log.d("alireza", "alireza user id is  hidden111 " + ((TL_contact)v3).user_id);
                            }

                        label_309:
                            v14_1 = v16 + 1;
                            v7 = v13_1;
                            v8 = v18;
                            v15 = v19;
                        }

                        v19 = v15;
                        Collections.sort(((List)v6), new Comparator() {
                            public int compare(Object arg1, Object arg2) {
                                return this.compare(((String)arg1), ((String)arg2));
                            }

                            public int compare(String arg4, String arg5) {
                                int v1 = arg4.charAt(0);
                                int v0 = arg5.charAt(0);
                                int v2 = 35;
                                if(v1 == v2) {
                                    return 1;
                                }

                                if(v0 == v2) {
                                    return -1;
                                }

                                return arg4.compareTo(arg5);
                            }
                        });
                        Collections.sort(v7, new Comparator() {
                            public int compare(Object arg1, Object arg2) {
                                return this.compare(((String)arg1), ((String)arg2));
                            }

                            public int compare(String arg4, String arg5) {
                                int v1 = arg4.charAt(0);
                                int v0 = arg5.charAt(0);
                                int v2 = 35;
                                if(v1 == v2) {
                                    return 1;
                                }

                                if(v0 == v2) {
                                    return -1;
                                }

                                return arg4.compareTo(arg5);
                            }
                        });
                        HashMap v15_1 = v0_2;
                        HashMap v13_2 = v1_3;
                        AndroidUtilities.runOnUIThread(new Runnable(v2_1, v4_1, v5, v6, v7, v8, v9, v10, v11, v19) {
                            public void run() {
                                this.this$2.this$1.this$0.contacts = this.this$2.this$1.val$contactsArr;
                                this.this$2.this$1.this$0.contactsDict = this.val$contactsDictionary;
                                this.this$2.this$1.this$0.usersSectionsDict = this.val$sectionsDict;
                                this.this$2.this$1.this$0.usersMutualSectionsDict = this.val$sectionsDictMutual;
                                this.this$2.this$1.this$0.sortedUsersSectionsArray = this.val$sortedSectionsArray;
                                this.this$2.this$1.this$0.sortedUsersMutualSectionsArray = this.val$sortedSectionsArrayMutual;
                                this.this$2.this$1.this$0.contactsDict1 = this.val$contactsDictionary1;
                                this.this$2.this$1.this$0.usersSectionsDict1 = this.val$sectionsDict1;
                                this.this$2.this$1.this$0.usersMutualSectionsDict1 = this.val$sectionsDictMutual1;
                                this.this$2.this$1.this$0.sortedUsersSectionsArray1 = this.val$sortedSectionsArray1;
                                this.this$2.this$1.this$0.sortedUsersMutualSectionsArray1 = this.val$sortedSectionsArrayMutual1;
                                if(this.this$2.this$1.val$from != 2) {
                                    Object v0 = this.this$2.this$1.this$0.loadContactsSync;
                                    __monitor_enter(v0);
                                    try {
                                        this.this$2.this$1.this$0.loadingContacts = false;
                                        __monitor_exit(v0);
                                        goto label_77;
                                    label_75:
                                        __monitor_exit(v0);
                                    }
                                    catch(Throwable v1) {
                                        goto label_75;
                                    }

                                    throw v1;
                                }

                            label_77:
                                this.this$2.this$1.this$0.performWriteContactsToPhoneBook();
                                this.this$2.this$1.this$0.updateUnregisteredContacts();
                                NotificationCenter.getInstance(this.this$2.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.contactsDidLoaded, new Object[0]);
                                if(this.this$2.this$1.val$from == 1 || (this.this$2.val$isEmpty)) {
                                    this.this$2.this$1.this$0.reloadContactsStatusesMaybe();
                                }
                                else {
                                    this.this$2.this$1.this$0.saveContactsLoadTime();
                                }
                            }
                        });
                        if(!v12.this$1.this$0.delayedContactsUpdate.isEmpty() && (v12.this$1.this$0.contactsLoaded) && (v12.this$1.this$0.contactsBookLoaded)) {
                            v12.this$1.this$0.applyContactsUpdates(v12.this$1.this$0.delayedContactsUpdate, null, null, null);
                            v12.this$1.this$0.delayedContactsUpdate.clear();
                        }

                        if(v13_2 != null) {
                            AndroidUtilities.runOnUIThread(new Runnable(v13_2, v15_1) {
                                public void run() {
                                    Utilities.globalQueue.postRunnable(new Runnable() {
                                        public void run() {
                                            this.this$3.this$2.this$1.this$0.contactsByPhone = this.this$3.val$contactsByPhonesDictFinal;
                                            this.this$3.this$2.this$1.this$0.contactsByShortPhone = this.this$3.val$contactsByPhonesShortDictFinal;
                                        }
                                    });
                                    if(this.this$2.this$1.this$0.contactsSyncInProgress) {
                                        return;
                                    }

                                    this.this$2.this$1.this$0.contactsSyncInProgress = true;
                                    MessagesStorage.getInstance(this.this$2.this$1.this$0.currentAccount).getCachedPhoneBook(false);
                                }
                            });
                        }
                        else {
                            v12.this$1.this$0.contactsLoaded = true;
                        }
                    }
                });
            }
        });
    }

    public void readContacts() {
        Object v0 = this.loadContactsSync;
        __monitor_enter(v0);
        try {
            if(this.loadingContacts) {
                __monitor_exit(v0);
                return;
            }

            this.loadingContacts = true;
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_15:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_15;
            }

            throw v1;
        }

        Utilities.stageQueue.postRunnable(new Runnable() {
            public void run() {
                if(ContactsController.this.contacts.isEmpty()) {
                    if(ContactsController.this.contactsLoaded) {
                    }
                    else {
                        ContactsController.this.loadContacts(true, 0);
                        return;
                    }
                }

                Object v0 = ContactsController.this.loadContactsSync;
                __monitor_enter(v0);
                try {
                    ContactsController.this.loadingContacts = false;
                    __monitor_exit(v0);
                    return;
                label_21:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_21;
                }

                throw v1;
            }
        });
    }

    private HashMap readContactsFromPhoneBook() {
        Cursor v17;
        String v3_2;
        ArrayList v0_4;
        String v0_3;
        int v0_2;
        ContentResolver v19;
        StringBuilder v18;
        Contact v12_2;
        String v7;
        HashMap v6;
        int v5;
        HashMap v2;
        Throwable v2_1;
        int v15;
        int v14;
        Cursor v4;
        ArrayList v11;
        HashMap v10;
        ContentResolver v3;
        StringBuilder v0_1;
        ContactsController v1 = this;
        if(!UserConfig.getInstance(v1.currentAccount).syncContacts) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("contacts sync disabled");
            }

            return new HashMap();
        }

        if(!this.hasContactsPermission()) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("app has no contacts permissions");
            }

            return new HashMap();
        }

        try {
            v0_1 = new StringBuilder();
            v3 = ApplicationLoader.applicationContext.getContentResolver();
            v10 = new HashMap();
            v11 = new ArrayList();
            v4 = v3.query(ContactsContract$CommonDataKinds$Phone.CONTENT_URI, v1.projectionPhones, null, null, null);
            v14 = 0;
            v15 = 1;
            if(v4 == null) {
                goto label_220;
            }
        }
        catch(Throwable v0) {
            v2_1 = v0;
            v4 = null;
            goto label_375;
        }
        catch(Throwable v0) {
            v2 = null;
            v4 = null;
            goto label_363;
        }

        try {
            v5 = v4.getCount();
            if(v5 > 0) {
                v6 = new HashMap(v5);
                v5 = 1;
            }
            else {
                goto label_209;
            }
        }
        catch(Throwable v0) {
            v2 = null;
            goto label_363;
        }

        try {
            while(v4.moveToNext()) {
                v7 = v4.getString(v15);
                String v8 = v4.getString(5);
                if(v8 == null) {
                    v8 = "";
                }

                boolean v9 = v8.indexOf(".sim") != 0 ? true : false;
                if(TextUtils.isEmpty(((CharSequence)v7))) {
                }
                else {
                    v7 = org.telegram.a.b.a(v7, ((boolean)v15));
                    if(TextUtils.isEmpty(((CharSequence)v7))) {
                    }
                    else {
                        String v2_2 = v7.startsWith("+") ? v7.substring(v15) : v7;
                        String v15_1 = v4.getString(v14);
                        v0_1.setLength(v14);
                        DatabaseUtils.appendEscapedSQLString(v0_1, v15_1);
                        String v12 = v0_1.toString();
                        Object v13 = v10.get(v2_2);
                        if(v13 != null) {
                            if(((Contact)v13).isGoodProvider) {
                                goto label_91;
                            }

                            if(v8.equals(((Contact)v13).provider)) {
                                goto label_91;
                            }

                            v0_1.setLength(v14);
                            DatabaseUtils.appendEscapedSQLString(v0_1, ((Contact)v13).key);
                            v11.remove(v0_1.toString());
                            v11.add(v12);
                            ((Contact)v13).key = v15_1;
                            ((Contact)v13).isGoodProvider = v9;
                            ((Contact)v13).provider = v8;
                            goto label_91;
                        }

                        if(!v11.contains(v12)) {
                            v11.add(v12);
                        }

                        int v13_1 = v4.getInt(2);
                        Object v12_1 = v6.get(v15_1);
                        if(v12_1 == null) {
                            v12_2 = new Contact();
                            String v14_1 = v4.getString(4);
                            v14_1 = v14_1 == null ? "" : v14_1.trim();
                            if(v1.isNotValidNameString(v14_1)) {
                                v12_2.first_name = v14_1;
                                v12_2.last_name = "";
                                v18 = v0_1;
                                v19 = v3;
                            }
                            else {
                                v18 = v0_1;
                                v0_2 = v14_1.lastIndexOf(32);
                                v19 = v3;
                                if(v0_2 != -1) {
                                    v12_2.first_name = v14_1.substring(0, v0_2).trim();
                                    v0_3 = v14_1.substring(v0_2 + 1, v14_1.length()).trim();
                                }
                                else {
                                    v12_2.first_name = v14_1;
                                    v0_3 = "";
                                }

                                v12_2.last_name = v0_3;
                            }

                            v12_2.provider = v8;
                            v12_2.isGoodProvider = v9;
                            v12_2.key = v15_1;
                            v12_2.contact_id = v5;
                            v6.put(v15_1, v12_2);
                            ++v5;
                        }
                        else {
                            v18 = v0_1;
                            v19 = v3;
                        }

                        v12_2.shortPhones.add(v2_2);
                        v12_2.phones.add(v7);
                        v12_2.phoneDeleted.add(Integer.valueOf(0));
                        v0_2 = 2131625748;
                        if(v13_1 == 0) {
                            v7 = v4.getString(3);
                            ArrayList v3_1 = v12_2.phoneTypes;
                            if(v7 != null) {
                            }
                            else {
                                v7 = LocaleController.getString("PhoneMobile", v0_2);
                            }

                            v3_1.add(v7);
                        }
                        else {
                            if(v13_1 == 1) {
                                v0_4 = v12_2.phoneTypes;
                                v3_2 = LocaleController.getString("PhoneHome", 2131625746);
                            }
                            else if(v13_1 == 2) {
                                v12_2.phoneTypes.add(LocaleController.getString("PhoneMobile", v0_2));
                                goto label_198;
                            }
                            else if(v13_1 == 3) {
                                v0_4 = v12_2.phoneTypes;
                                v3_2 = LocaleController.getString("PhoneWork", 2131625755);
                            }
                            else if(v13_1 == 12) {
                                v0_4 = v12_2.phoneTypes;
                                v3_2 = LocaleController.getString("PhoneMain", 2131625747);
                            }
                            else {
                                v0_4 = v12_2.phoneTypes;
                                v3_2 = LocaleController.getString("PhoneOther", 2131625753);
                            }

                            v0_4.add(v3_2);
                        }

                    label_198:
                        v10.put(v2_2, v12_2);
                        v0_1 = v18;
                        v3 = v19;
                        v14 = 0;
                    }
                }

            label_91:
                v15 = 1;
            }
        }
        catch(Throwable v0) {
            v2 = v6;
            goto label_363;
        }

        v19 = v3;
        v2 = v6;
        goto label_211;
    label_209:
        v19 = v3;
        v2 = null;
        try {
        label_211:
            v4.close();
            goto label_215;
        }
        catch(Throwable v0) {
        }
        catch(Throwable v0) {
        }
        catch(Exception ) {
        label_215:
            Cursor v3_3 = null;
            goto label_223;
        label_220:
            v19 = v3;
            v3_3 = v4;
            v2 = null;
            try {
            label_223:
                v0_3 = TextUtils.join(",", ((Iterable)v11));
                Uri v5_1 = ContactsContract$Data.CONTENT_URI;
                String[] v6_1 = v1.projectionNames;
                StringBuilder v4_1 = new StringBuilder();
                v4_1.append("lookup IN (");
                v4_1.append(v0_3);
                v4_1.append(") AND ");
                v4_1.append("mimetype");
                v4_1.append(" = \'");
                v4_1.append("vnd.android.cursor.item/name");
                v4_1.append("\'");
                v4 = v19.query(v5_1, v6_1, v4_1.toString(), null, null);
                if(v4 == null) {
                    goto label_341;
                }
            }
            catch(Throwable v0) {
                v2_1 = v0;
                v4 = v3_3;
                goto label_375;
            }
            catch(Throwable v0) {
                v4 = v3_3;
                goto label_363;
            }

            try {
                while(v4.moveToNext()) {
                    v3_2 = v4.getString(0);
                    String v6_2 = v4.getString(1);
                    v7 = v4.getString(2);
                    String v9_1 = v4.getString(3);
                    Object v3_4 = v2.get(v3_2);
                    if(v3_4 == null) {
                        continue;
                    }

                    if(((Contact)v3_4).namesFilled) {
                        continue;
                    }

                    if(((Contact)v3_4).isGoodProvider) {
                        if(v6_2 == null) {
                            v6_2 = "";
                        }

                        ((Contact)v3_4).first_name = v6_2;
                        ((Contact)v3_4).last_name = v7 != null ? v7 : "";
                        if(TextUtils.isEmpty(((CharSequence)v9_1))) {
                            goto label_333;
                        }

                        if(!TextUtils.isEmpty(((Contact)v3_4).first_name)) {
                            ((Contact)v3_4).first_name = ((Contact)v3_4).first_name + " " + v9_1;
                            goto label_333;
                        }

                        ((Contact)v3_4).first_name = v9_1;
                    }
                    else {
                        if((v1.isNotValidNameString(v6_2)) || !((Contact)v3_4).first_name.contains(((CharSequence)v6_2)) && !v6_2.contains(((Contact)v3_4).first_name)) {
                            if(v1.isNotValidNameString(v7)) {
                                goto label_333;
                            }
                            else if(!((Contact)v3_4).last_name.contains(((CharSequence)v7))) {
                                if(v6_2.contains(((Contact)v3_4).last_name)) {
                                    goto label_307;
                                }

                                goto label_333;
                            }
                        }

                    label_307:
                        if(v6_2 == null) {
                            v6_2 = "";
                        }

                        ((Contact)v3_4).first_name = v6_2;
                        if(!TextUtils.isEmpty(((CharSequence)v9_1))) {
                            ((Contact)v3_4).first_name = !TextUtils.isEmpty(((Contact)v3_4).first_name) ? ((Contact)v3_4).first_name + " " + v9_1 : v9_1;
                        }

                        if(v7 != null) {
                            ((Contact)v3_4).last_name = v7;
                            goto label_333;
                        }

                        ((Contact)v3_4).last_name = "";
                    }

                label_333:
                    ((Contact)v3_4).namesFilled = true;
                }
            }
            catch(Throwable v0) {
                goto label_374;
            }
            catch(Throwable v0) {
                goto label_214;
            }

            try {
                v4.close();
                goto label_339;
            }
            catch(Throwable v0) {
            label_374:
                v2_1 = v0;
            }
            catch(Throwable v0) {
            label_214:
                try {
                label_363:
                    FileLog.e(v0);
                    if(v2 != null) {
                        v2.clear();
                    }
                }
                catch(Throwable v0) {
                    goto label_374;
                }

                if(v4 == null) {
                    goto label_368;
                }

                try {
                    v4.close();
                    goto label_368;
                }
                catch(Exception v0_5) {
                    goto label_347;
                }
            }
            catch(Exception ) {
            label_339:
                v17 = null;
                goto label_342;
            }
        }

    label_375:
        if(v4 != null) {
            try {
                v4.close();
            }
            catch(Exception v0_5) {
                FileLog.e(v0_5);
            }
        }

        throw v2_1;
    label_341:
        v17 = v4;
    label_342:
        if(v17 != null) {
            try {
                v17.close();
            }
            catch(Exception v0_5) {
            label_347:
                FileLog.e(v0_5);
            }
        }

    label_368:
        if(v2 != null) {
        }
        else {
            v2 = new HashMap();
        }

        return v2;
    }

    public void reloadContactsStatuses() {
        this.saveContactsLoadTime();
        MessagesController.getInstance(this.currentAccount).clearFullUsers();
        SharedPreferences$Editor v0 = MessagesController.getMainSettings(this.currentAccount).edit();
        v0.putBoolean("needGetStatuses", true).commit();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_contacts_getStatuses(), new RequestDelegate(v0) {
            public void run(TLObject arg1, TL_error arg2) {
                if(arg2 == null) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                        public void run() {
                            int v6;
                            UserStatus v5;
                            this.this$1.val$editor.remove("needGetStatuses").commit();
                            TLObject v0 = this.val$response;
                            if(!((Vector)v0).objects.isEmpty()) {
                                ArrayList v1 = new ArrayList();
                                Iterator v0_1 = ((Vector)v0).objects.iterator();
                                while(v0_1.hasNext()) {
                                    Object v3 = v0_1.next();
                                    TL_user v4 = new TL_user();
                                    if(v3 == null) {
                                    }
                                    else {
                                        if((((TL_contactStatus)v3).status instanceof TL_userStatusRecently)) {
                                            v5 = ((TL_contactStatus)v3).status;
                                            v6 = -100;
                                            goto label_26;
                                        }
                                        else if((((TL_contactStatus)v3).status instanceof TL_userStatusLastWeek)) {
                                            v5 = ((TL_contactStatus)v3).status;
                                            v6 = -101;
                                            goto label_26;
                                        }
                                        else if((((TL_contactStatus)v3).status instanceof TL_userStatusLastMonth)) {
                                            v5 = ((TL_contactStatus)v3).status;
                                            v6 = -102;
                                        label_26:
                                            v5.expires = v6;
                                        }

                                        User v5_1 = MessagesController.getInstance(this.this$1.this$0.currentAccount).getUser(Integer.valueOf(((TL_contactStatus)v3).user_id));
                                        if(v5_1 != null) {
                                            v5_1.status = ((TL_contactStatus)v3).status;
                                        }

                                        ((User)v4).status = ((TL_contactStatus)v3).status;
                                        v1.add(v4);
                                    }
                                }

                                MessagesStorage.getInstance(this.this$1.this$0.currentAccount).updateUsers(v1, true, true, true);
                            }

                            NotificationCenter.getInstance(this.this$1.this$0.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(4)});
                        }
                    });
                }
            }
        });
    }

    private void reloadContactsStatusesMaybe() {
        try {
            if(MessagesController.getMainSettings(this.currentAccount).getLong("lastReloadStatusTime", 0) >= System.currentTimeMillis() - 86400000) {
                return;
            }

            this.reloadContactsStatuses();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public void resetImportedContacts() {
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_contacts_resetSaved(), new RequestDelegate() {
            public void run(TLObject arg1, TL_error arg2) {
            }
        });
    }

    private void saveContactsLoadTime() {
        try {
            MessagesController.getMainSettings(this.currentAccount).edit().putLong("lastReloadStatusTime", System.currentTimeMillis()).commit();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public void setDeleteAccountTTL(int arg1) {
        this.deleteAccountTTL = arg1;
    }

    public void setPrivacyRules(ArrayList arg2, int arg3) {
        if(arg3 == 2) {
            this.callPrivacyRules = arg2;
        }
        else if(arg3 == 1) {
            this.groupPrivacyRules = arg2;
        }
        else {
            this.privacyRules = arg2;
        }

        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
        this.reloadContactsStatuses();
    }

    public void syncPhoneBookByAlert(HashMap arg9, boolean arg10, boolean arg11, boolean arg12) {
        Utilities.globalQueue.postRunnable(new Runnable(arg9, arg10, arg11, arg12) {
            public void run() {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("sync contacts by alert");
                }

                ContactsController.this.performSyncPhoneBook(this.val$contacts, true, this.val$first, this.val$schedule, false, false, this.val$cancel);
            }
        });
    }

    public void updateUnregisteredContacts() {
        int v7;
        Object v4;
        HashMap v0 = new HashMap();
        int v1 = this.contacts.size();
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v4 = this.contacts.get(v3);
            User v5 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((TL_contact)v4).user_id));
            if(v5 != null) {
                if(TextUtils.isEmpty(v5.phone)) {
                }
                else {
                    v0.put(v5.phone, v4);
                }
            }
        }

        ArrayList v1_1 = new ArrayList();
        Iterator v3_1 = this.contactsBook.entrySet().iterator();
        while(v3_1.hasNext()) {
            v4 = v3_1.next().getValue();
            int v5_1 = 0;
            while(true) {
                v7 = 1;
                if(v5_1 >= ((Contact)v4).phones.size()) {
                    break;
                }
                else if(!v0.containsKey(((Contact)v4).shortPhones.get(v5_1))) {
                    if(((Contact)v4).phoneDeleted.get(v5_1).intValue() == 1) {
                    }
                    else {
                        ++v5_1;
                        continue;
                    }
                }

                goto label_49;
            }

            v7 = 0;
        label_49:
            if(v7 != 0) {
                continue;
            }

            v1_1.add(v4);
        }

        Collections.sort(((List)v1_1), new Comparator() {
            public int compare(Object arg1, Object arg2) {
                return this.compare(((Contact)arg1), ((Contact)arg2));
            }

            public int compare(Contact arg3, Contact arg4) {
                String v0 = arg3.first_name;
                if(v0.length() == 0) {
                    v0 = arg3.last_name;
                }

                String v3 = arg4.first_name;
                if(v3.length() == 0) {
                    v3 = arg4.last_name;
                }

                return v0.compareTo(v3);
            }
        });
        this.phoneBookContacts = v1_1;
    }
}

