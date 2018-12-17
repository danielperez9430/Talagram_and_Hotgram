package org.linphone.core;

import java.util.Vector;

public interface LinphoneAccountCreator {
    public interface LinphoneAccountCreatorListener {
        void onAccountCreatorAccountActivated(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorAccountCreated(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorAccountLinkedWithPhoneNumber(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorIsAccountActivated(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorIsAccountLinked(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorIsAccountUsed(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorIsPhoneNumberUsed(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorPasswordUpdated(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorPhoneAccountRecovered(LinphoneAccountCreator arg1, Status arg2);

        void onAccountCreatorPhoneNumberLinkActivated(LinphoneAccountCreator arg1, Status arg2);
    }

    public class Status {
        public static final Status AccountActivated;
        public static final Status AccountAlreadyActivated;
        public static final Status AccountCreated;
        public static final Status AccountExist;
        public static final Status AccountExistWithAlias;
        public static final Status AccountLinked;
        public static final Status AccountNotActivated;
        public static final Status AccountNotCreated;
        public static final Status AccountNotExist;
        public static final Status AccountNotLinked;
        public static final Status CountryCodeInvalid;
        public static final Status DisplayNameInvalid;
        public static final Status DomainInvalid;
        public static final Status EmailInvalid;
        public static final Status ErrorServer;
        public static final Status Failed;
        public static final Status Ok;
        public static final Status PasswordTooLong;
        public static final Status PasswordTooShort;
        public static final Status PhoneNumberInvalid;
        public static final Status PhoneNumberNotUsed;
        public static final Status PhoneNumberTooLong;
        public static final Status PhoneNumberTooShort;
        public static final Status PhoneNumberUsedAccount;
        public static final Status PhoneNumberUsedAlias;
        public static final Status RouteInvalid;
        public static final Status TransportNotSupported;
        public static final Status UsernameInvalid;
        public static final Status UsernameInvalidSize;
        public static final Status UsernameTooLong;
        public static final Status UsernameTooShort;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            Status.values = new Vector();
            Status.Ok = new Status(0, "Ok");
            Status.Failed = new Status(1, "Failed");
            Status.AccountCreated = new Status(2, "AccountCreated");
            Status.AccountNotCreated = new Status(3, "AccountNotCreated");
            Status.AccountExist = new Status(4, "AccountExist");
            Status.AccountExistWithAlias = new Status(5, "AccountExistWithAlias");
            Status.AccountNotExist = new Status(6, "AccountNotExist");
            Status.AccountActivated = new Status(7, "AccountActivated");
            Status.AccountAlreadyActivated = new Status(8, "AccountAlreadyActivated");
            Status.AccountNotActivated = new Status(9, "AccountNotActivated");
            Status.AccountLinked = new Status(10, "AccountLinked");
            Status.AccountNotLinked = new Status(11, "AccountNotLinked");
            Status.EmailInvalid = new Status(12, "EmailInvalid");
            Status.UsernameInvalid = new Status(13, "UsernameInvalid");
            Status.UsernameTooShort = new Status(14, "UsernameTooShort");
            Status.UsernameTooLong = new Status(15, "UsernameTooLong");
            Status.UsernameInvalidSize = new Status(16, "UsernameInvalidSize");
            Status.PhoneNumberInvalid = new Status(17, "PhoneNumberInvalid");
            Status.PhoneNumberTooShort = new Status(18, "PhoneNumberTooShort");
            Status.PhoneNumberTooLong = new Status(19, "PhoneNumberTooLong");
            Status.PhoneNumberUsedAccount = new Status(20, "PhoneNumberUsed");
            Status.PhoneNumberUsedAlias = new Status(21, "PhoneNumberUsed");
            Status.PhoneNumberNotUsed = new Status(22, "PhoneNumberNotUsed");
            Status.PasswordTooShort = new Status(23, "PasswordTooShort");
            Status.PasswordTooLong = new Status(24, "PasswordTooLong");
            Status.DomainInvalid = new Status(25, "DomainInvalid");
            Status.RouteInvalid = new Status(26, "RouteInvalid");
            Status.DisplayNameInvalid = new Status(27, "DisplayNameInvalid");
            Status.TransportNotSupported = new Status(28, "TransportNotSupported");
            Status.CountryCodeInvalid = new Status(29, "CountryCodeInvalid");
            Status.ErrorServer = new Status(30, "ErrorServer");
        }

        private Status(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            Status.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static Status fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < Status.values.size(); ++v0) {
                Object v1 = Status.values.elementAt(v0);
                if(((Status)v1).mValue == arg3) {
                    return ((Status)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Status not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public int toInt() {
            return this.mValue;
        }

        public String toString() {
            return this.mStringValue;
        }

        public final int value() {
            return this.mValue;
        }
    }

    Status activateAccount();

    Status activatePhoneNumberLink();

    LinphoneProxyConfig configure();

    Status createAccount();

    String getDisplayName();

    String getDomain();

    String getEmail();

    String getHa1();

    String getPassword();

    String getPhoneNumber();

    String getPrefix(String arg1);

    String getRoute();

    TransportType getTransport();

    String getUsername();

    Status isAccountActivated();

    Status isAccountLinked();

    Status isAccountUsed();

    Status isPhoneNumberUsed();

    Status linkPhoneNumberWithAccount();

    Status recoverPhoneAccount();

    Status setActivationCode(String arg1);

    Status setDisplayName(String arg1);

    Status setDomain(String arg1);

    Status setEmail(String arg1);

    Status setHa1(String arg1);

    Status setLanguage(String arg1);

    void setListener(LinphoneAccountCreatorListener arg1);

    Status setPassword(String arg1);

    Status setPhoneNumber(String arg1, String arg2);

    Status setRoute(String arg1);

    Status setTransport(TransportType arg1);

    Status setUsername(String arg1);

    Status updatePassword(String arg1);
}

