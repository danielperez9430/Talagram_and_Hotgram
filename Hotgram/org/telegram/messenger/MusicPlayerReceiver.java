package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class MusicPlayerReceiver extends BroadcastReceiver {
    public MusicPlayerReceiver() {
        super();
    }

    public void onReceive(Context arg2, Intent arg3) {
        if(!arg3.getAction().equals("android.intent.action.MEDIA_BUTTON")) {
            if(arg3.getAction().equals("org.telegram.android.musicplayer.play")) {
            label_29:
                MediaController.getInstance().playMessage(MediaController.getInstance().getPlayingMessageObject());
                return;
            }

            if(!arg3.getAction().equals("org.telegram.android.musicplayer.pause") && !arg3.getAction().equals("android.media.AUDIO_BECOMING_NOISY")) {
                if(arg3.getAction().equals("org.telegram.android.musicplayer.next")) {
                label_47:
                    MediaController.getInstance().playNextMessage();
                    return;
                }

                if(arg3.getAction().equals("org.telegram.android.musicplayer.close")) {
                    MediaController.getInstance().cleanupPlayer(true, true);
                    return;
                }

                if(!arg3.getAction().equals("org.telegram.android.musicplayer.previous")) {
                    return;
                }

            label_62:
                MediaController.getInstance().playPreviousMessage();
                return;
            }

        label_65:
            MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        }
        else if(arg3.getExtras() == null) {
            return;
        }
        else {
            Object v2 = arg3.getExtras().get("android.intent.extra.KEY_EVENT");
            if(v2 == null) {
                return;
            }
            else if(((KeyEvent)v2).getAction() != 0) {
                return;
            }
            else {
                int v2_1 = ((KeyEvent)v2).getKeyCode();
                if(v2_1 != 79) {
                    switch(v2_1) {
                        case 85: {
                            goto label_21;
                        }
                        case 86: {
                            return;
                        }
                        case 87: {
                            goto label_47;
                        }
                        case 88: {
                            goto label_62;
                        }
                    }

                    switch(v2_1) {
                        case 126: {
                            goto label_29;
                        }
                        case 127: {
                            goto label_65;
                        }
                    }

                    return;
                }

            label_21:
                if(!MediaController.getInstance().isMessagePaused()) {
                    goto label_65;
                }

                goto label_29;
            }
        }
    }
}

