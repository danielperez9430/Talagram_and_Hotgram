package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_channel;
import org.telegram.tgnet.TLRPC$TL_channelForbidden;
import org.telegram.tgnet.TLRPC$TL_chatEmpty;
import org.telegram.tgnet.TLRPC$TL_chatForbidden;

public class ChatObject {
    public static final int CHAT_TYPE_BROADCAST = 1;
    public static final int CHAT_TYPE_CHANNEL = 2;
    public static final int CHAT_TYPE_CHAT = 0;
    public static final int CHAT_TYPE_MEGAGROUP = 4;
    public static final int CHAT_TYPE_USER = 3;

    public ChatObject() {
        super();
    }

    public static boolean canAddAdmins(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.add_admins) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean canAddUsers(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.invite_users) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean canAddViaLink(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.invite_link) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean canBlockUsers(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.ban_users) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean canChangeChatInfo(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.change_info) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean canEditInfo(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.change_info) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean canPost(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.post_messages) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean canSendEmbed(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(arg1 != null) {
                if(arg1.banned_rights == null) {
                    goto label_13;
                }
                else if(!arg1.banned_rights.send_media && !arg1.banned_rights.embed_links) {
                    goto label_13;
                }
            }

            v1 = false;
        }
        else {
        label_13:
            v1 = true;
        }

        return v1;
    }

    public static boolean canSendMessages(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(arg1 != null) {
                if(arg1.banned_rights == null) {
                    goto label_10;
                }
                else if(!arg1.banned_rights.send_messages) {
                    goto label_10;
                }
            }

            v1 = false;
        }
        else {
        label_10:
            v1 = true;
        }

        return v1;
    }

    public static boolean canSendStickers(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(arg1 != null) {
                if(arg1.banned_rights == null) {
                    goto label_13;
                }
                else if(!arg1.banned_rights.send_media && !arg1.banned_rights.send_stickers) {
                    goto label_13;
                }
            }

            v1 = false;
        }
        else {
        label_13:
            v1 = true;
        }

        return v1;
    }

    public static boolean canWriteToChat(Chat arg1) {
        boolean v1;
        if(!ChatObject.isChannel(arg1) || (arg1.creator)) {
        label_14:
            v1 = true;
        }
        else {
            if(arg1.admin_rights != null && (arg1.admin_rights.post_messages)) {
                goto label_14;
            }

            if(!arg1.broadcast) {
                goto label_14;
            }

            v1 = false;
        }

        return v1;
    }

    public static Chat getChatByDialog(long arg0, int arg2) {
        int v0 = ((int)arg0);
        if(v0 < 0) {
            return MessagesController.getInstance(arg2).getChat(Integer.valueOf(-v0));
        }

        return null;
    }

    public static boolean hasAdminRights(Chat arg1) {
        boolean v1;
        if(arg1 != null) {
            if(!arg1.creator) {
                if(arg1.admin_rights == null) {
                }
                else if(arg1.admin_rights.flags != 0) {
                    goto label_8;
                }

                goto label_10;
            }

        label_8:
            v1 = true;
        }
        else {
        label_10:
            v1 = false;
        }

        return v1;
    }

    public static boolean isCanWriteToChannel(int arg0, int arg1) {
        boolean v0_1;
        Chat v0 = MessagesController.getInstance(arg1).getChat(Integer.valueOf(arg0));
        if(v0 != null) {
            if(!v0.creator && (v0.admin_rights == null || !v0.admin_rights.post_messages) && !v0.megagroup) {
                goto label_15;
            }

            v0_1 = true;
        }
        else {
        label_15:
            v0_1 = false;
        }

        return v0_1;
    }

    public static boolean isChannel(Chat arg1) {
        boolean v1 = ((arg1 instanceof TL_channel)) || ((arg1 instanceof TL_channelForbidden)) ? true : false;
        return v1;
    }

    public static boolean isChannel(int arg0, int arg1) {
        Chat v0 = MessagesController.getInstance(arg1).getChat(Integer.valueOf(arg0));
        boolean v0_1 = ((v0 instanceof TL_channel)) || ((v0 instanceof TL_channelForbidden)) ? true : false;
        return v0_1;
    }

    public static boolean isKickedFromChat(Chat arg1) {
        boolean v1;
        if(arg1 == null || ((arg1 instanceof TL_chatEmpty)) || ((arg1 instanceof TL_chatForbidden)) || ((arg1 instanceof TL_channelForbidden)) || (arg1.kicked) || (arg1.deactivated)) {
        label_19:
            v1 = true;
        }
        else {
            if(arg1.banned_rights != null && (arg1.banned_rights.view_messages)) {
                goto label_19;
            }

            v1 = false;
        }

        return v1;
    }

    public static boolean isLeftFromChat(Chat arg1) {
        boolean v1 = arg1 == null || ((arg1 instanceof TL_chatEmpty)) || ((arg1 instanceof TL_chatForbidden)) || ((arg1 instanceof TL_channelForbidden)) || (arg1.left) || (arg1.deactivated) ? true : false;
        return v1;
    }

    public static boolean isMegagroup(Chat arg1) {
        boolean v1;
        if(!(arg1 instanceof TL_channel) && !(arg1 instanceof TL_channelForbidden)) {
            goto label_8;
        }
        else if(arg1.megagroup) {
            v1 = true;
        }
        else {
        label_8:
            v1 = false;
        }

        return v1;
    }

    public static boolean isNotInChat(Chat arg1) {
        boolean v1 = arg1 == null || ((arg1 instanceof TL_chatEmpty)) || ((arg1 instanceof TL_chatForbidden)) || ((arg1 instanceof TL_channelForbidden)) || (arg1.left) || (arg1.kicked) || (arg1.deactivated) ? true : false;
        return v1;
    }
}

