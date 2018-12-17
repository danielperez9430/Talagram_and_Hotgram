package org.telegram.customization.Model;

import android.text.TextUtils;
import com.google.a.f;
import org.telegram.customization.g.c;
import org.telegram.messenger.UserConfig;

public class RegisterModel {
    private String fullName;
    boolean isFromInfo;
    private String phone;
    String pushToken;
    private long userId;
    private String username;
    private int versionCode;

    public RegisterModel() {
        super();
    }

    public String getFullName() {
        return this.fullName;
    }

    public static RegisterModel getInstance() {
        try {
            UserConfig.getInstance(UserConfig.selectedAccount).loadConfig();
            RegisterModel v0_1 = new RegisterModel();
            if(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser() == null) {
                return v0_1;
            }

            String v1 = TextUtils.isEmpty(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().first_name) ? "" : UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().first_name;
            StringBuilder v2 = new StringBuilder();
            v2.append(v1);
            v1 = TextUtils.isEmpty(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().last_name) ? "" : " " + UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().last_name;
            v2.append(v1);
            v0_1.setFullName(v2.toString());
            v0_1.setPhone(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().phone);
            v0_1.setUserId(((long)UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().id));
            v0_1.setUsername(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().username);
            v0_1.setVersionCode(206);
            return v0_1;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            return new RegisterModel();
        }
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPushToken() {
        return this.pushToken;
    }

    public static String getRegisterModelData() {
        RegisterModel v0 = RegisterModel.getInstance();
        v0.setFromInfo(true);
        return new String(c.h(new f().a(v0)));
    }

    public static String getRegisterModelDataNew() {
        RegisterModel v0 = RegisterModel.getInstance();
        v0.setFromInfo(true);
        return new String(c.h(new f().a(v0)));
    }

    public long getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public boolean isFromInfo() {
        return this.isFromInfo;
    }

    public void setFromInfo(boolean arg1) {
        this.isFromInfo = arg1;
    }

    public void setFullName(String arg1) {
        this.fullName = arg1;
    }

    public void setPhone(String arg1) {
        this.phone = arg1;
    }

    public void setPushToken(String arg1) {
        this.pushToken = arg1;
    }

    public void setUserId(long arg1) {
        this.userId = arg1;
    }

    public void setUsername(String arg1) {
        this.username = arg1;
    }

    public void setVersionCode(int arg1) {
        this.versionCode = arg1;
    }
}

