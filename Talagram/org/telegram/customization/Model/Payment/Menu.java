package org.telegram.customization.Model.Payment;

import com.google.a.a.c;

public class Menu {
    @c(a="ManuId") int id;
    int parentMenuId;
    @c(a="Status") int status;
    @c(a="Title") String title;

    public Menu() {
        super();
    }

    public int getId() {
        return this.id;
    }

    public int getParentMenuId() {
        return this.parentMenuId;
    }

    public int getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setId(int arg1) {
        this.id = arg1;
    }

    public void setParentMenuId(int arg1) {
        this.parentMenuId = arg1;
    }

    public void setStatus(int arg1) {
        this.status = arg1;
    }

    public void setTitle(String arg1) {
        this.title = arg1;
    }
}

