package org.telegram.customization.c;

import java.util.Observable;

public class c extends Observable {
    public c() {
        super();
    }

    public void a() {
        this.setChanged();
        this.notifyObservers("");
    }
}

