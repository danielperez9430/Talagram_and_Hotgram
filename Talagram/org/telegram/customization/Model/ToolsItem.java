package org.telegram.customization.Model;

public class ToolsItem {
    public static final int TOOL_CLEAR_CACHE = 5;
    public static final int TOOL_DOWNLOAD_MANGER = 4;
    public static final int TOOL_ID_CALL = 102;
    public static final int TOOL_ID_CONTACTS = 101;
    public static final int TOOL_ID_CREATE = 100;
    public static final int TOOL_ID_FINDER = 1;
    public static final int TOOL_ID_HEADER = 109;
    public static final int TOOL_ID_HOTGRAM_SETTING = 105;
    public static final int TOOL_ID_QUESTION = 107;
    public static final int TOOL_ID_SAVED_MESSAGE = 103;
    public static final int TOOL_ID_TELEGRAM_SETTING = 104;
    public static final int TOOL_ID_TOOLS = 106;
    public static final int TOOL_ID_VERSION = 108;
    public static final int TOOL_ONLINE_USERS = 3;
    public static final int TOOL_THEMES = 6;
    public static final int TOOL_TURN_OFF = 7;
    public static final int TOOL_USER_CHANGE_LOG = 2;
    boolean hasDivider;
    int icon;
    int id;
    String name;

    public ToolsItem(int arg1, String arg2, int arg3, boolean arg4) {
        super();
        this.id = arg1;
        this.name = arg2;
        this.icon = arg3;
        this.hasDivider = arg4;
    }

    public int getIcon() {
        return this.icon;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isHasDivider() {
        return this.hasDivider;
    }

    public void setHasDivider(boolean arg1) {
        this.hasDivider = arg1;
    }

    public void setIcon(int arg1) {
        this.icon = arg1;
    }

    public void setId(int arg1) {
        this.id = arg1;
    }

    public void setName(String arg1) {
        this.name = arg1;
    }
}

