package org.telegram.customization.Model;

import java.util.ArrayList;

public class Update {
    ArrayList changeList;
    boolean downloadBoth;
    String downloadLink;
    boolean forceUpdate;
    boolean fromMarket;
    int lastVersion;

    public Update() {
        super();
    }

    public ArrayList getChangeList() {
        return this.changeList;
    }

    public String getDownloadLink() {
        return this.downloadLink;
    }

    public static Update getHardcodedUpdate() {
        Update v0 = new Update();
        ArrayList v1 = new ArrayList();
        v1.add("adsfgsdg");
        v1.add("adsfgsdg");
        v1.add("adsfgsdg");
        v1.add("adsfgsdg");
        v0.setChangeList(v1);
        v0.setDownloadLink("http://app.hotgram.ir/hotgramv194.apk");
        v0.setFromMarket(false);
        v0.setDownloadBoth(true);
        v0.setLastVersion(2);
        return v0;
    }

    public int getLastVersion() {
        return this.lastVersion;
    }

    public boolean isDownloadBoth() {
        return this.downloadBoth;
    }

    public boolean isForceUpdate() {
        return this.forceUpdate;
    }

    public boolean isFromMarket() {
        return this.fromMarket;
    }

    public void setChangeList(ArrayList arg1) {
        this.changeList = arg1;
    }

    public void setDownloadBoth(boolean arg1) {
        this.downloadBoth = arg1;
    }

    public void setDownloadLink(String arg1) {
        this.downloadLink = arg1;
    }

    public void setForceUpdate(boolean arg1) {
        this.forceUpdate = arg1;
    }

    public void setFromMarket(boolean arg1) {
        this.fromMarket = arg1;
    }

    public void setLastVersion(int arg1) {
        this.lastVersion = arg1;
    }
}

