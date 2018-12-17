package org.telegram.customization.Model;

import java.util.ArrayList;

public class MultiOutPut {
    ArrayList chnls;
    long ttl;

    public MultiOutPut() {
        super();
    }

    public ArrayList getChnls() {
        return this.chnls;
    }

    public long getTtl() {
        return this.ttl;
    }

    public void setChnls(ArrayList arg1) {
        this.chnls = arg1;
    }

    public void setTtl(long arg1) {
        this.ttl = arg1;
    }
}

