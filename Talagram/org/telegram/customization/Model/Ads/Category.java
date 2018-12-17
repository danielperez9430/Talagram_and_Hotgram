package org.telegram.customization.Model.Ads;

public class Category {
    public static final int STATUS_SELECTED = 1;
    public static final int STATUS_UNSELECTED = 2;
    int channelId;
    String desc;
    Long id;
    String image;
    boolean isSelected;
    int participant;
    int status;
    String title;
    String userName;

    public Category() {
        super();
    }

    public int getChannelId() {
        return this.channelId;
    }

    public String getDesc() {
        return this.desc;
    }

    public Long getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    public int getParticipant() {
        return this.participant;
    }

    public int getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUserName() {
        return this.userName;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setChannelId(int arg1) {
        this.channelId = arg1;
    }

    public void setDesc(String arg1) {
        this.desc = arg1;
    }

    public void setId(Long arg1) {
        this.id = arg1;
    }

    public void setImage(String arg1) {
        this.image = arg1;
    }

    public void setParticipant(int arg1) {
        this.participant = arg1;
    }

    public void setSelected(boolean arg1) {
        this.isSelected = arg1;
    }

    public void setStatus(int arg1) {
        this.status = arg1;
    }

    public void setTitle(String arg1) {
        this.title = arg1;
    }

    public void setUserName(String arg1) {
        this.userName = arg1;
    }
}

