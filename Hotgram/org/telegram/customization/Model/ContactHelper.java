package org.telegram.customization.Model;

import com.google.a.a.c;

public class ContactHelper {
    @c(a="a") long accessHash;
    int id;
    @c(a="m") String mobile;
    @c(a="n") String name;
    @c(a="u") String username;

    public ContactHelper() {
        super();
    }

    public long getAccessHash() {
        return this.accessHash;
    }

    public int getId() {
        return this.id;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setAccessHash(long arg1) {
        this.accessHash = arg1;
    }

    public void setId(int arg1) {
        this.id = arg1;
    }

    public void setMobile(String arg1) {
        this.mobile = arg1;
    }

    public void setName(String arg1) {
        this.name = arg1;
    }

    public void setUsername(String arg1) {
        this.username = arg1;
    }
}

