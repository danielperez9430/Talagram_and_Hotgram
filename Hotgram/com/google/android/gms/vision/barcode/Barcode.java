package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="BarcodeCreator") @Reserved(value={1}) public class Barcode extends AbstractSafeParcelable {
    @Class(creator="AddressCreator") @Reserved(value={1}) public class Address extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR = null;
        public static final int HOME = 2;
        public static final int UNKNOWN = 0;
        public static final int WORK = 1;
        @Field(id=3) public String[] addressLines;
        @Field(id=2) public int type;

        static {
            Address.CREATOR = new zza();
        }

        @Constructor public Address(@Param(id=2) int arg1, @Param(id=3) String[] arg2) {
            super();
            this.type = arg1;
            this.addressLines = arg2;
        }

        public Address() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeInt(arg4, 2, this.type);
            SafeParcelWriter.writeStringArray(arg4, 3, this.addressLines, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="CalendarDateTimeCreator") @Reserved(value={1}) public class CalendarDateTime extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=4) public int day;
        @Field(id=5) public int hours;
        @Field(id=8) public boolean isUtc;
        @Field(id=6) public int minutes;
        @Field(id=3) public int month;
        @Field(id=9) public String rawValue;
        @Field(id=7) public int seconds;
        @Field(id=2) public int year;

        static {
            CalendarDateTime.CREATOR = new zzd();
        }

        @Constructor public CalendarDateTime(@Param(id=2) int arg1, @Param(id=3) int arg2, @Param(id=4) int arg3, @Param(id=5) int arg4, @Param(id=6) int arg5, @Param(id=7) int arg6, @Param(id=8) boolean arg7, @Param(id=9) String arg8) {
            super();
            this.year = arg1;
            this.month = arg2;
            this.day = arg3;
            this.hours = arg4;
            this.minutes = arg5;
            this.seconds = arg6;
            this.isUtc = arg7;
            this.rawValue = arg8;
        }

        public CalendarDateTime() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeInt(arg4, 2, this.year);
            SafeParcelWriter.writeInt(arg4, 3, this.month);
            SafeParcelWriter.writeInt(arg4, 4, this.day);
            SafeParcelWriter.writeInt(arg4, 5, this.hours);
            SafeParcelWriter.writeInt(arg4, 6, this.minutes);
            SafeParcelWriter.writeInt(arg4, 7, this.seconds);
            SafeParcelWriter.writeBoolean(arg4, 8, this.isUtc);
            SafeParcelWriter.writeString(arg4, 9, this.rawValue, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="CalendarEventCreator") @Reserved(value={1}) public class CalendarEvent extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=3) public String description;
        @Field(id=8) public CalendarDateTime end;
        @Field(id=4) public String location;
        @Field(id=5) public String organizer;
        @Field(id=7) public CalendarDateTime start;
        @Field(id=6) public String status;
        @Field(id=2) public String summary;

        static {
            CalendarEvent.CREATOR = new zze();
        }

        @Constructor public CalendarEvent(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) String arg4, @Param(id=6) String arg5, @Param(id=7) CalendarDateTime arg6, @Param(id=8) CalendarDateTime arg7) {
            super();
            this.summary = arg1;
            this.description = arg2;
            this.location = arg3;
            this.organizer = arg4;
            this.status = arg5;
            this.start = arg6;
            this.end = arg7;
        }

        public CalendarEvent() {
            super();
        }

        public void writeToParcel(Parcel arg5, int arg6) {
            int v0 = SafeParcelWriter.beginObjectHeader(arg5);
            SafeParcelWriter.writeString(arg5, 2, this.summary, false);
            SafeParcelWriter.writeString(arg5, 3, this.description, false);
            SafeParcelWriter.writeString(arg5, 4, this.location, false);
            SafeParcelWriter.writeString(arg5, 5, this.organizer, false);
            SafeParcelWriter.writeString(arg5, 6, this.status, false);
            SafeParcelWriter.writeParcelable(arg5, 7, this.start, arg6, false);
            SafeParcelWriter.writeParcelable(arg5, 8, this.end, arg6, false);
            SafeParcelWriter.finishObjectHeader(arg5, v0);
        }
    }

    @Class(creator="ContactInfoCreator") @Reserved(value={1}) public class ContactInfo extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=8) public Address[] addresses;
        @Field(id=6) public Email[] emails;
        @Field(id=2) public PersonName name;
        @Field(id=3) public String organization;
        @Field(id=5) public Phone[] phones;
        @Field(id=4) public String title;
        @Field(id=7) public String[] urls;

        static {
            ContactInfo.CREATOR = new zzf();
        }

        @Constructor public ContactInfo(@Param(id=2) PersonName arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) Phone[] arg4, @Param(id=6) Email[] arg5, @Param(id=7) String[] arg6, @Param(id=8) Address[] arg7) {
            super();
            this.name = arg1;
            this.organization = arg2;
            this.title = arg3;
            this.phones = arg4;
            this.emails = arg5;
            this.urls = arg6;
            this.addresses = arg7;
        }

        public ContactInfo() {
            super();
        }

        public void writeToParcel(Parcel arg5, int arg6) {
            int v0 = SafeParcelWriter.beginObjectHeader(arg5);
            SafeParcelWriter.writeParcelable(arg5, 2, this.name, arg6, false);
            SafeParcelWriter.writeString(arg5, 3, this.organization, false);
            SafeParcelWriter.writeString(arg5, 4, this.title, false);
            SafeParcelWriter.writeTypedArray(arg5, 5, this.phones, arg6, false);
            SafeParcelWriter.writeTypedArray(arg5, 6, this.emails, arg6, false);
            SafeParcelWriter.writeStringArray(arg5, 7, this.urls, false);
            SafeParcelWriter.writeTypedArray(arg5, 8, this.addresses, arg6, false);
            SafeParcelWriter.finishObjectHeader(arg5, v0);
        }
    }

    @Class(creator="DriverLicenseCreator") @Reserved(value={1}) public class DriverLicense extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=8) public String addressCity;
        @Field(id=9) public String addressState;
        @Field(id=7) public String addressStreet;
        @Field(id=10) public String addressZip;
        @Field(id=14) public String birthDate;
        @Field(id=2) public String documentType;
        @Field(id=13) public String expiryDate;
        @Field(id=3) public String firstName;
        @Field(id=6) public String gender;
        @Field(id=12) public String issueDate;
        @Field(id=15) public String issuingCountry;
        @Field(id=5) public String lastName;
        @Field(id=11) public String licenseNumber;
        @Field(id=4) public String middleName;

        static {
            DriverLicense.CREATOR = new zzg();
        }

        @Constructor public DriverLicense(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) String arg4, @Param(id=6) String arg5, @Param(id=7) String arg6, @Param(id=8) String arg7, @Param(id=9) String arg8, @Param(id=10) String arg9, @Param(id=11) String arg10, @Param(id=12) String arg11, @Param(id=13) String arg12, @Param(id=14) String arg13, @Param(id=15) String arg14) {
            super();
            this.documentType = arg1;
            this.firstName = arg2;
            this.middleName = arg3;
            this.lastName = arg4;
            this.gender = arg5;
            this.addressStreet = arg6;
            this.addressCity = arg7;
            this.addressState = arg8;
            this.addressZip = arg9;
            this.licenseNumber = arg10;
            this.issueDate = arg11;
            this.expiryDate = arg12;
            this.birthDate = arg13;
            this.issuingCountry = arg14;
        }

        public DriverLicense() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeString(arg4, 2, this.documentType, false);
            SafeParcelWriter.writeString(arg4, 3, this.firstName, false);
            SafeParcelWriter.writeString(arg4, 4, this.middleName, false);
            SafeParcelWriter.writeString(arg4, 5, this.lastName, false);
            SafeParcelWriter.writeString(arg4, 6, this.gender, false);
            SafeParcelWriter.writeString(arg4, 7, this.addressStreet, false);
            SafeParcelWriter.writeString(arg4, 8, this.addressCity, false);
            SafeParcelWriter.writeString(arg4, 9, this.addressState, false);
            SafeParcelWriter.writeString(arg4, 10, this.addressZip, false);
            SafeParcelWriter.writeString(arg4, 11, this.licenseNumber, false);
            SafeParcelWriter.writeString(arg4, 12, this.issueDate, false);
            SafeParcelWriter.writeString(arg4, 13, this.expiryDate, false);
            SafeParcelWriter.writeString(arg4, 14, this.birthDate, false);
            SafeParcelWriter.writeString(arg4, 15, this.issuingCountry, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="EmailCreator") @Reserved(value={1}) public class Email extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR = null;
        public static final int HOME = 2;
        public static final int UNKNOWN = 0;
        public static final int WORK = 1;
        @Field(id=3) public String address;
        @Field(id=5) public String body;
        @Field(id=4) public String subject;
        @Field(id=2) public int type;

        static {
            Email.CREATOR = new zzh();
        }

        @Constructor public Email(@Param(id=2) int arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) String arg4) {
            super();
            this.type = arg1;
            this.address = arg2;
            this.subject = arg3;
            this.body = arg4;
        }

        public Email() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeInt(arg4, 2, this.type);
            SafeParcelWriter.writeString(arg4, 3, this.address, false);
            SafeParcelWriter.writeString(arg4, 4, this.subject, false);
            SafeParcelWriter.writeString(arg4, 5, this.body, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="GeoPointCreator") @Reserved(value={1}) public class GeoPoint extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=2) public double lat;
        @Field(id=3) public double lng;

        static {
            GeoPoint.CREATOR = new zzi();
        }

        @Constructor public GeoPoint(@Param(id=2) double arg1, @Param(id=3) double arg3) {
            super();
            this.lat = arg1;
            this.lng = arg3;
        }

        public GeoPoint() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeDouble(arg4, 2, this.lat);
            SafeParcelWriter.writeDouble(arg4, 3, this.lng);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="PersonNameCreator") @Reserved(value={1}) public class PersonName extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=5) public String first;
        @Field(id=2) public String formattedName;
        @Field(id=7) public String last;
        @Field(id=6) public String middle;
        @Field(id=4) public String prefix;
        @Field(id=3) public String pronunciation;
        @Field(id=8) public String suffix;

        static {
            PersonName.CREATOR = new zzj();
        }

        @Constructor public PersonName(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) String arg4, @Param(id=6) String arg5, @Param(id=7) String arg6, @Param(id=8) String arg7) {
            super();
            this.formattedName = arg1;
            this.pronunciation = arg2;
            this.prefix = arg3;
            this.first = arg4;
            this.middle = arg5;
            this.last = arg6;
            this.suffix = arg7;
        }

        public PersonName() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeString(arg4, 2, this.formattedName, false);
            SafeParcelWriter.writeString(arg4, 3, this.pronunciation, false);
            SafeParcelWriter.writeString(arg4, 4, this.prefix, false);
            SafeParcelWriter.writeString(arg4, 5, this.first, false);
            SafeParcelWriter.writeString(arg4, 6, this.middle, false);
            SafeParcelWriter.writeString(arg4, 7, this.last, false);
            SafeParcelWriter.writeString(arg4, 8, this.suffix, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="PhoneCreator") @Reserved(value={1}) public class Phone extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR = null;
        public static final int FAX = 3;
        public static final int HOME = 2;
        public static final int MOBILE = 4;
        public static final int UNKNOWN = 0;
        public static final int WORK = 1;
        @Field(id=3) public String number;
        @Field(id=2) public int type;

        static {
            Phone.CREATOR = new zzk();
        }

        @Constructor public Phone(@Param(id=2) int arg1, @Param(id=3) String arg2) {
            super();
            this.type = arg1;
            this.number = arg2;
        }

        public Phone() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeInt(arg4, 2, this.type);
            SafeParcelWriter.writeString(arg4, 3, this.number, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="SmsCreator") @Reserved(value={1}) public class Sms extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=2) public String message;
        @Field(id=3) public String phoneNumber;

        static {
            Sms.CREATOR = new zzl();
        }

        @Constructor public Sms(@Param(id=2) String arg1, @Param(id=3) String arg2) {
            super();
            this.message = arg1;
            this.phoneNumber = arg2;
        }

        public Sms() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeString(arg4, 2, this.message, false);
            SafeParcelWriter.writeString(arg4, 3, this.phoneNumber, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="UrlBookmarkCreator") @Reserved(value={1}) public class UrlBookmark extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR;
        @Field(id=2) public String title;
        @Field(id=3) public String url;

        static {
            UrlBookmark.CREATOR = new zzm();
        }

        @Constructor public UrlBookmark(@Param(id=2) String arg1, @Param(id=3) String arg2) {
            super();
            this.title = arg1;
            this.url = arg2;
        }

        public UrlBookmark() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeString(arg4, 2, this.title, false);
            SafeParcelWriter.writeString(arg4, 3, this.url, false);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    @Class(creator="WiFiCreator") @Reserved(value={1}) public class WiFi extends AbstractSafeParcelable {
        public static final Parcelable$Creator CREATOR = null;
        public static final int OPEN = 1;
        public static final int WEP = 3;
        public static final int WPA = 2;
        @Field(id=4) public int encryptionType;
        @Field(id=3) public String password;
        @Field(id=2) public String ssid;

        static {
            WiFi.CREATOR = new zzn();
        }

        @Constructor public WiFi(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) int arg3) {
            super();
            this.ssid = arg1;
            this.password = arg2;
            this.encryptionType = arg3;
        }

        public WiFi() {
            super();
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            arg5 = SafeParcelWriter.beginObjectHeader(arg4);
            SafeParcelWriter.writeString(arg4, 2, this.ssid, false);
            SafeParcelWriter.writeString(arg4, 3, this.password, false);
            SafeParcelWriter.writeInt(arg4, 4, this.encryptionType);
            SafeParcelWriter.finishObjectHeader(arg4, arg5);
        }
    }

    public static final int ALL_FORMATS = 0;
    public static final int AZTEC = 4096;
    public static final int CALENDAR_EVENT = 11;
    public static final int CODABAR = 8;
    public static final int CODE_128 = 1;
    public static final int CODE_39 = 2;
    public static final int CODE_93 = 4;
    public static final int CONTACT_INFO = 1;
    public static final Parcelable$Creator CREATOR = null;
    public static final int DATA_MATRIX = 16;
    public static final int DRIVER_LICENSE = 12;
    public static final int EAN_13 = 32;
    public static final int EAN_8 = 64;
    public static final int EMAIL = 2;
    public static final int GEO = 10;
    public static final int ISBN = 3;
    public static final int ITF = 128;
    public static final int PDF417 = 2048;
    public static final int PHONE = 4;
    public static final int PRODUCT = 5;
    public static final int QR_CODE = 256;
    public static final int SMS = 6;
    public static final int TEXT = 7;
    public static final int UPC_A = 512;
    public static final int UPC_E = 1024;
    public static final int URL = 8;
    public static final int WIFI = 9;
    @Field(id=13) public CalendarEvent calendarEvent;
    @Field(id=14) public ContactInfo contactInfo;
    @Field(id=6) public Point[] cornerPoints;
    @Field(id=4) public String displayValue;
    @Field(id=15) public DriverLicense driverLicense;
    @Field(id=7) public Email email;
    @Field(id=2) public int format;
    @Field(id=12) public GeoPoint geoPoint;
    @Field(id=8) public Phone phone;
    @Field(id=3) public String rawValue;
    @Field(id=9) public Sms sms;
    @Field(id=11) public UrlBookmark url;
    @Field(id=5) public int valueFormat;
    @Field(id=10) public WiFi wifi;

    static {
        Barcode.CREATOR = new zzb();
    }

    @Constructor public Barcode(@Param(id=2) int arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) int arg4, @Param(id=6) Point[] arg5, @Param(id=7) Email arg6, @Param(id=8) Phone arg7, @Param(id=9) Sms arg8, @Param(id=10) WiFi arg9, @Param(id=11) UrlBookmark arg10, @Param(id=12) GeoPoint arg11, @Param(id=13) CalendarEvent arg12, @Param(id=14) ContactInfo arg13, @Param(id=15) DriverLicense arg14) {
        super();
        this.format = arg1;
        this.rawValue = arg2;
        this.displayValue = arg3;
        this.valueFormat = arg4;
        this.cornerPoints = arg5;
        this.email = arg6;
        this.phone = arg7;
        this.sms = arg8;
        this.wifi = arg9;
        this.url = arg10;
        this.geoPoint = arg11;
        this.calendarEvent = arg12;
        this.contactInfo = arg13;
        this.driverLicense = arg14;
    }

    public Barcode() {
        super();
    }

    public Rect getBoundingBox() {
        int v0 = -2147483648;
        int v1 = 2147483647;
        int v2 = 0;
        int v3 = 2147483647;
        int v4 = -2147483648;
        while(v2 < this.cornerPoints.length) {
            Point v5 = this.cornerPoints[v2];
            v1 = Math.min(v1, v5.x);
            v0 = Math.max(v0, v5.x);
            v3 = Math.min(v3, v5.y);
            v4 = Math.max(v4, v5.y);
            ++v2;
        }

        return new Rect(v1, v3, v0, v4);
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 2, this.format);
        SafeParcelWriter.writeString(arg5, 3, this.rawValue, false);
        SafeParcelWriter.writeString(arg5, 4, this.displayValue, false);
        SafeParcelWriter.writeInt(arg5, 5, this.valueFormat);
        SafeParcelWriter.writeTypedArray(arg5, 6, this.cornerPoints, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 7, this.email, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 8, this.phone, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 9, this.sms, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 10, this.wifi, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 11, this.url, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 12, this.geoPoint, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 13, this.calendarEvent, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 14, this.contactInfo, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 15, this.driverLicense, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

