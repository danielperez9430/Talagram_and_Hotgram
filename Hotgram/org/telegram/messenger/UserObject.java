package org.telegram.messenger;

import android.text.TextUtils;
import org.telegram.a.b;
import org.telegram.tgnet.TLRPC$TL_userContact_old2;
import org.telegram.tgnet.TLRPC$TL_userDeleted_old2;
import org.telegram.tgnet.TLRPC$TL_userEmpty;
import org.telegram.tgnet.TLRPC$TL_userSelf_old3;
import org.telegram.tgnet.TLRPC$User;

public class UserObject {
    public UserObject() {
        super();
    }

    public static String getFirstName(User arg1) {
        return UserObject.getFirstName(arg1, true);
    }

    public static String getFirstName(User arg2, boolean arg3) {
        if(arg2 != null) {
            if(UserObject.isDeleted(arg2)) {
            }
            else {
                String v0 = arg2.first_name;
                if(TextUtils.isEmpty(((CharSequence)v0))) {
                    v0 = arg2.last_name;
                }
                else if(!arg3 && v0.length() <= 2) {
                    return ContactsController.formatName(arg2.first_name, arg2.last_name);
                }

                if(!TextUtils.isEmpty(((CharSequence)v0))) {
                }
                else {
                    v0 = LocaleController.getString("HiddenName", 2131624939);
                }

                return v0;
            }
        }

        return "DELETED";
    }

    public static String getUserName(User arg3) {
        if(arg3 != null) {
            if(UserObject.isDeleted(arg3)) {
            }
            else {
                String v0 = ContactsController.formatName(arg3.first_name, arg3.last_name);
                if(v0.length() == 0 && arg3.phone != null) {
                    if(arg3.phone.length() == 0) {
                    }
                    else {
                        b v0_1 = b.a();
                        StringBuilder v1 = new StringBuilder();
                        v1.append("+");
                        v1.append(arg3.phone);
                        v0 = v0_1.e(v1.toString());
                    }
                }

                return v0;
            }
        }

        return LocaleController.getString("HiddenName", 2131624939);
    }

    public static boolean isContact(User arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!(arg1 instanceof TL_userContact_old2) && !arg1.contact && !arg1.mutual_contact) {
                goto label_9;
            }

            v1 = true;
        }
        else {
        label_9:
            v1 = false;
        }

        return v1;
    }

    public static boolean isDeleted(User arg1) {
        boolean v1 = arg1 == null || ((arg1 instanceof TL_userDeleted_old2)) || ((arg1 instanceof TL_userEmpty)) || (arg1.deleted) ? true : false;
        return v1;
    }

    public static boolean isUserSelf(User arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!(arg1 instanceof TL_userSelf_old3) && !arg1.self) {
                goto label_7;
            }

            v1 = true;
        }
        else {
        label_7:
            v1 = false;
        }

        return v1;
    }
}

