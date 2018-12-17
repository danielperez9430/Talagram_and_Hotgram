package org.telegram.customization.Model.Payment;

import com.google.a.a.c;
import java.util.ArrayList;

public class User {
    public static final int UserAvailable = 101;
    public static final int UserNotExists = 81;
    public static final int UserNotVerified = 80;
    String address;
    int bankId;
    String cardNo;
    ArrayList channels;
    @c(a="contact") long contactNumber;
    String description;
    String firstName;
    String lastName;
    @c(a="menu") ArrayList menus;
    String message;
    Long mobileNumber;
    String nationalCode;
    String password;
    String shaba;
    int status;
    String username;

    public User() {
        super();
    }

    public String getAddress() {
        return this.address;
    }

    public int getBankId() {
        return this.bankId;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public ArrayList getChannels() {
        return this.channels;
    }

    public long getContactNumber() {
        return this.contactNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public ArrayList getMenus() {
        return this.menus;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getMobileNumber() {
        return this.mobileNumber;
    }

    public String getNationalCode() {
        return this.nationalCode;
    }

    public String getPassword() {
        return this.password;
    }

    public String getShaba() {
        return this.shaba;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUsername() {
        return this.username;
    }

    public void setAddress(String arg1) {
        this.address = arg1;
    }

    public void setBankId(int arg1) {
        this.bankId = arg1;
    }

    public void setCardNo(String arg1) {
        this.cardNo = arg1;
    }

    public void setChannels(ArrayList arg1) {
        this.channels = arg1;
    }

    public void setContactNumber(long arg1) {
        this.contactNumber = arg1;
    }

    public void setDescription(String arg1) {
        this.description = arg1;
    }

    public void setFirstName(String arg1) {
        this.firstName = arg1;
    }

    public void setLastName(String arg1) {
        this.lastName = arg1;
    }

    public void setMenus(ArrayList arg1) {
        this.menus = arg1;
    }

    public void setMessage(String arg1) {
        this.message = arg1;
    }

    public void setMobileNumber(Long arg1) {
        this.mobileNumber = arg1;
    }

    public void setNationalCode(String arg1) {
        this.nationalCode = arg1;
    }

    public void setPassword(String arg1) {
        this.password = arg1;
    }

    public void setShaba(String arg1) {
        this.shaba = arg1;
    }

    public void setStatus(int arg1) {
        this.status = arg1;
    }

    public void setUsername(String arg1) {
        this.username = arg1;
    }
}

