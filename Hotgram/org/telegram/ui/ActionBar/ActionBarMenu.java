package org.telegram.ui.ActionBar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;

public class ActionBarMenu extends LinearLayout {
    protected boolean isActionMode;
    protected ActionBar parentActionBar;

    public ActionBarMenu(Context arg1, ActionBar arg2) {
        super(arg1);
        this.setOrientation(0);
        this.parentActionBar = arg2;
    }

    public ActionBarMenu(Context arg1) {
        super(arg1);
    }

    public ActionBarMenuItem addItem(int arg2, int arg3) {
        int v0 = this.isActionMode ? this.parentActionBar.itemsActionModeBackgroundColor : this.parentActionBar.itemsBackgroundColor;
        return this.addItem(arg2, arg3, v0);
    }

    public ActionBarMenuItem addItem(int arg8, int arg9, int arg10) {
        return this.addItem(arg8, arg9, arg10, null, AndroidUtilities.dp(48f));
    }

    public ActionBarMenuItem addItem(int arg4, int arg5, int arg6, Drawable arg7, int arg8) {
        Context v1 = this.getContext();
        int v2 = this.isActionMode ? this.parentActionBar.itemsActionModeColor : this.parentActionBar.itemsColor;
        ActionBarMenuItem v0 = new ActionBarMenuItem(v1, this, arg6, v2);
        v0.setTag(Integer.valueOf(arg4));
        if(arg7 != null) {
            v0.iconView.setImageDrawable(arg7);
        }
        else if(arg5 != 0) {
            v0.iconView.setImageResource(arg5);
        }

        this.addView(((View)v0), new LinearLayout$LayoutParams(arg8, -1));
        v0.setOnClickListener(new -$$Lambda$ActionBarMenu$ppo9UED664gE-YCecAHKNZM7u90(this));
        return v0;
    }

    public ActionBarMenuItem addItem(int arg8, Drawable arg9) {
        int v0 = this.isActionMode ? this.parentActionBar.itemsActionModeBackgroundColor : this.parentActionBar.itemsBackgroundColor;
        int v4 = v0;
        return this.addItem(arg8, 0, v4, arg9, AndroidUtilities.dp(48f));
    }

    public ActionBarMenuItem addItemWithWidth(int arg8, int arg9, int arg10) {
        int v0 = this.isActionMode ? this.parentActionBar.itemsActionModeBackgroundColor : this.parentActionBar.itemsBackgroundColor;
        int v4 = v0;
        return this.addItem(arg8, arg9, v4, null, arg10);
    }

    public void clearItems() {
        this.removeAllViews();
    }

    public void closeSearchField(boolean arg6) {
        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getChildAt(v2);
            if(((v3 instanceof ActionBarMenuItem)) && (((ActionBarMenuItem)v3).isSearchField())) {
                this.parentActionBar.onSearchFieldVisibilityChanged(false);
                ((ActionBarMenuItem)v3).toggleSearch(arg6);
                return;
            }
        }
    }

    public ActionBarMenuItem getItem(int arg2) {
        View v2 = this.findViewWithTag(Integer.valueOf(arg2));
        if((v2 instanceof ActionBarMenuItem)) {
            return ((ActionBarMenuItem)v2);
        }

        return null;
    }

    public void hideAllPopupMenus() {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((v2 instanceof ActionBarMenuItem)) {
                ((ActionBarMenuItem)v2).closeSubMenu();
            }
        }
    }

    public static void lambda$addItem$0(ActionBarMenu arg2, View arg3) {
        View v0 = arg3;
        if(((ActionBarMenuItem)v0).hasSubMenu()) {
            if(arg2.parentActionBar.actionBarMenuOnItemClick.canOpenMenu()) {
                ((ActionBarMenuItem)v0).toggleSubMenu();
            }
        }
        else if(((ActionBarMenuItem)v0).isSearchField()) {
            arg2.parentActionBar.onSearchFieldVisibilityChanged(((ActionBarMenuItem)v0).toggleSearch(true));
        }
        else {
            arg2.onItemClick(arg3.getTag().intValue());
        }
    }

    public void onItemClick(int arg2) {
        if(this.parentActionBar.actionBarMenuOnItemClick != null) {
            this.parentActionBar.actionBarMenuOnItemClick.onItemClick(arg2);
        }
    }

    public void onMenuButtonPressed() {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((v2 instanceof ActionBarMenuItem)) {
                if(((ActionBarMenuItem)v2).getVisibility() != 0) {
                }
                else if(((ActionBarMenuItem)v2).hasSubMenu()) {
                    ((ActionBarMenuItem)v2).toggleSubMenu();
                    return;
                }
                else if(((ActionBarMenuItem)v2).overrideMenuClick) {
                    this.onItemClick(((ActionBarMenuItem)v2).getTag().intValue());
                    return;
                }
            }
        }
    }

    public void openSearchField(boolean arg5, String arg6) {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if(((v2 instanceof ActionBarMenuItem)) && (((ActionBarMenuItem)v2).isSearchField())) {
                if(arg5) {
                    this.parentActionBar.onSearchFieldVisibilityChanged(((ActionBarMenuItem)v2).toggleSearch(true));
                }

                ((ActionBarMenuItem)v2).getSearchField().setText(((CharSequence)arg6));
                ((ActionBarMenuItem)v2).getSearchField().setSelection(arg6.length());
                return;
            }
        }
    }

    protected void redrawPopup(int arg5) {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((v2 instanceof ActionBarMenuItem)) {
                ((ActionBarMenuItem)v2).redrawPopup(arg5);
            }
        }
    }

    public void setEnabled(boolean arg4) {
        super.setEnabled(arg4);
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.getChildAt(v1).setEnabled(arg4);
        }
    }

    protected void setPopupItemsColor(int arg5) {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((v2 instanceof ActionBarMenuItem)) {
                ((ActionBarMenuItem)v2).setPopupItemsColor(arg5);
            }
        }
    }

    public void setSearchTextColor(int arg5, boolean arg6) {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if(((v2 instanceof ActionBarMenuItem)) && (((ActionBarMenuItem)v2).isSearchField())) {
                if(arg6) {
                    ((ActionBarMenuItem)v2).getSearchField().setHintTextColor(arg5);
                }
                else {
                    ((ActionBarMenuItem)v2).getSearchField().setTextColor(arg5);
                }

                return;
            }
        }
    }

    protected void updateItemsBackgroundColor() {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((v2 instanceof ActionBarMenuItem)) {
                int v3 = this.isActionMode ? this.parentActionBar.itemsActionModeBackgroundColor : this.parentActionBar.itemsBackgroundColor;
                v2.setBackgroundDrawable(Theme.createSelectorDrawable(v3));
            }
        }
    }

    protected void updateItemsColor() {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((v2 instanceof ActionBarMenuItem)) {
                int v3 = this.isActionMode ? this.parentActionBar.itemsActionModeColor : this.parentActionBar.itemsColor;
                ((ActionBarMenuItem)v2).setIconColor(v3);
            }
        }
    }
}

