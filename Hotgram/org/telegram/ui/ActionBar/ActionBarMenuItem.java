package org.telegram.ui.ActionBar;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.content.a;
import android.text.Editable;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.reflect.Method;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.Components.CloseProgressDrawable2;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;

public class ActionBarMenuItem extends FrameLayout {
    public interface ActionBarMenuItemDelegate {
        void onItemClick(int arg1);
    }

    public class ActionBarMenuItemSearchListener {
        public ActionBarMenuItemSearchListener() {
            super();
        }

        public boolean canCollapseSearch() {
            return 1;
        }

        public void onCaptionCleared() {
        }

        public void onSearchCollapse() {
        }

        public void onSearchExpand() {
        }

        public void onSearchPressed(EditText arg1) {
        }

        public void onTextChanged(EditText arg1) {
        }
    }

    private int additionalOffset;
    private boolean allowCloseAnimation;
    private boolean animationEnabled;
    private ImageView clearButton;
    private ActionBarMenuItemDelegate delegate;
    protected ImageView iconView;
    private boolean ignoreOnTextChange;
    private boolean isSearchField;
    private boolean layoutInScreen;
    private static Method layoutInScreenMethod;
    private ActionBarMenuItemSearchListener listener;
    private int[] location;
    private boolean longClickEnabled;
    protected boolean overrideMenuClick;
    private ActionBarMenu parentMenu;
    private ActionBarPopupWindowLayout popupLayout;
    private ActionBarPopupWindow popupWindow;
    private boolean processedPopupClick;
    private CloseProgressDrawable2 progressDrawable;
    private Rect rect;
    private FrameLayout searchContainer;
    private EditTextBoldCursor searchField;
    private TextView searchFieldCaption;
    private View selectedMenuView;
    private Runnable showMenuRunnable;
    private int subMenuOpenSide;

    public ActionBarMenuItem(Context arg2, ActionBarMenu arg3, int arg4, int arg5) {
        super(arg2);
        this.allowCloseAnimation = true;
        this.animationEnabled = true;
        this.longClickEnabled = true;
        if(arg4 != 0) {
            this.setBackgroundDrawable(Theme.createSelectorDrawable(arg4));
        }

        this.parentMenu = arg3;
        this.iconView = new ImageView(arg2);
        this.iconView.setScaleType(ImageView$ScaleType.CENTER);
        this.addView(this.iconView, LayoutHelper.createFrame(-1, -1f));
        if(arg5 != 0) {
            this.iconView.setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY));
        }
    }

    static ImageView access$000(ActionBarMenuItem arg0) {
        return arg0.clearButton;
    }

    static TextView access$100(ActionBarMenuItem arg0) {
        return arg0.searchFieldCaption;
    }

    static EditTextBoldCursor access$200(ActionBarMenuItem arg0) {
        return arg0.searchField;
    }

    static boolean access$300(ActionBarMenuItem arg0) {
        return arg0.ignoreOnTextChange;
    }

    static boolean access$302(ActionBarMenuItem arg0, boolean arg1) {
        arg0.ignoreOnTextChange = arg1;
        return arg1;
    }

    static ActionBarMenuItemSearchListener access$400(ActionBarMenuItem arg0) {
        return arg0.listener;
    }

    public TextView addSubItem(int arg6, CharSequence arg7) {
        this.createPopupLayout();
        TextView v0 = new TextView(this.getContext());
        v0.setTextColor(Theme.getColor("actionBarDefaultSubmenuItem"));
        v0.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        int v2 = !LocaleController.isRTL ? 16 : 21;
        v0.setGravity(v2);
        v0.setPadding(AndroidUtilities.dp(16f), 0, AndroidUtilities.dp(16f), 0);
        v0.setTextSize(1, 16f);
        v0.setMinWidth(AndroidUtilities.dp(196f));
        v0.setTag(Integer.valueOf(arg6));
        v0.setText(arg7);
        this.popupLayout.addView(((View)v0));
        ViewGroup$LayoutParams v6 = v0.getLayoutParams();
        if(LocaleController.isRTL) {
            ((LinearLayout$LayoutParams)v6).gravity = 5;
        }

        ((LinearLayout$LayoutParams)v6).width = -1;
        ((LinearLayout$LayoutParams)v6).height = AndroidUtilities.dp(48f);
        v0.setLayoutParams(v6);
        v0.setOnClickListener(new -$$Lambda$ActionBarMenuItem$2OXLLOK5tdU8k_iUBLX7uda6XhM(this));
        return v0;
    }

    public TextView addSubItem(int arg6, CharSequence arg7, int arg8) {
        this.createPopupLayout();
        TextView v0 = new TextView(this.getContext());
        v0.setTextColor(Theme.getColor("actionBarDefaultSubmenuItem"));
        v0.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        int v2 = !LocaleController.isRTL ? 16 : 21;
        v0.setGravity(v2);
        v0.setPadding(AndroidUtilities.dp(16f), 0, AndroidUtilities.dp(16f), 0);
        v0.setTextSize(1, 16f);
        v0.setMinWidth(AndroidUtilities.dp(196f));
        v0.setTag(Integer.valueOf(arg6));
        v0.setText(arg7);
        arg6 = 5;
        v0.setCompoundDrawablePadding(arg6);
        Drawable v7 = a.a(this.getContext(), arg8);
        v7.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chats_menuItemIcon"), PorterDuff$Mode.MULTIPLY));
        v0.setCompoundDrawablesWithIntrinsicBounds(null, null, v7, null);
        this.popupLayout.addView(((View)v0));
        ViewGroup$LayoutParams v7_1 = v0.getLayoutParams();
        if(LocaleController.isRTL) {
            ((LinearLayout$LayoutParams)v7_1).gravity = arg6;
        }

        ((LinearLayout$LayoutParams)v7_1).width = -1;
        ((LinearLayout$LayoutParams)v7_1).height = AndroidUtilities.dp(48f);
        v0.setLayoutParams(v7_1);
        v0.setOnClickListener(new -$$Lambda$ActionBarMenuItem$37smKkAzmohk63TzSJQopQof02I(this));
        return v0;
    }

    public void addSubItem(int arg2, View arg3, int arg4, int arg5) {
        this.createPopupLayout();
        arg3.setLayoutParams(new LinearLayout$LayoutParams(arg4, arg5));
        this.popupLayout.addView(arg3);
        arg3.setTag(Integer.valueOf(arg2));
        arg3.setOnClickListener(new -$$Lambda$ActionBarMenuItem$9Z-bd6EyKyDSvICVkiunmSXpJnY(this));
        arg3.setBackgroundDrawable(Theme.getSelectorDrawable(false));
    }

    public void addSubItem(View arg3, int arg4, int arg5) {
        this.createPopupLayout();
        this.popupLayout.addView(arg3, new LinearLayout$LayoutParams(arg4, arg5));
    }

    public void clearSearchText() {
        if(this.searchField == null) {
            return;
        }

        this.searchField.setText("");
    }

    public void closeSubMenu() {
        if(this.popupWindow != null && (this.popupWindow.isShowing())) {
            this.popupWindow.dismiss();
        }
    }

    private void createPopupLayout() {
        if(this.popupLayout != null) {
            return;
        }

        this.rect = new Rect();
        this.location = new int[2];
        this.popupLayout = new ActionBarPopupWindowLayout(this.getContext());
        this.popupLayout.setOnTouchListener(new -$$Lambda$ActionBarMenuItem$NEgOkBBuNcW0duabIaP5FYMgH5w(this));
        this.popupLayout.setDispatchKeyEventListener(new -$$Lambda$ActionBarMenuItem$yNUlqFN00bPmohrcR1AdkblquoQ(this));
    }

    public ImageView getImageView() {
        return this.iconView;
    }

    public TextView getItemById(int arg2) {
        if(this.getpopupLayout() != null) {
            return this.getpopupLayout().findViewWithTag(Integer.valueOf(arg2));
        }

        return null;
    }

    public EditTextBoldCursor getSearchField() {
        return this.searchField;
    }

    public View getpopupLayout() {
        if(this.popupLayout != null) {
            return this.popupLayout;
        }

        return null;
    }

    public boolean hasSubMenu() {
        boolean v0 = this.popupLayout != null ? true : false;
        return v0;
    }

    public void hideSubItem(int arg3) {
        View v3 = this.popupLayout.findViewWithTag(Integer.valueOf(arg3));
        if(v3 != null) {
            int v1 = 8;
            if(v3.getVisibility() != v1) {
                v3.setVisibility(v1);
            }
        }
    }

    public boolean isSearchField() {
        return this.isSearchField;
    }

    public boolean isSubItemVisible(int arg2) {
        View v2 = this.popupLayout.findViewWithTag(Integer.valueOf(arg2));
        boolean v2_1 = v2 == null || v2.getVisibility() != 0 ? false : true;
        return v2_1;
    }

    public static void lambda$addSubItem$3(ActionBarMenuItem arg2, View arg3) {
        if(arg2.popupWindow != null && (arg2.popupWindow.isShowing())) {
            if(arg2.processedPopupClick) {
                return;
            }
            else {
                arg2.processedPopupClick = true;
                arg2.popupWindow.dismiss(arg2.allowCloseAnimation);
            }
        }

        if(arg2.parentMenu != null) {
            arg2.parentMenu.onItemClick(arg3.getTag().intValue());
        }
        else if(arg2.delegate != null) {
            arg2.delegate.onItemClick(arg3.getTag().intValue());
        }
    }

    public static void lambda$addSubItem$4(ActionBarMenuItem arg2, View arg3) {
        if(arg2.popupWindow != null && (arg2.popupWindow.isShowing())) {
            if(arg2.processedPopupClick) {
                return;
            }
            else {
                arg2.processedPopupClick = true;
                arg2.popupWindow.dismiss(arg2.allowCloseAnimation);
            }
        }

        if(arg2.parentMenu != null) {
            arg2.parentMenu.onItemClick(arg3.getTag().intValue());
        }
        else if(arg2.delegate != null) {
            arg2.delegate.onItemClick(arg3.getTag().intValue());
        }
    }

    public static void lambda$addSubItem$5(ActionBarMenuItem arg2, View arg3) {
        if(arg2.popupWindow != null && (arg2.popupWindow.isShowing())) {
            if(arg2.processedPopupClick) {
                return;
            }
            else {
                arg2.processedPopupClick = true;
                arg2.popupWindow.dismiss(arg2.allowCloseAnimation);
            }
        }

        if(arg2.parentMenu != null) {
            arg2.parentMenu.onItemClick(arg3.getTag().intValue());
        }
        else if(arg2.delegate != null) {
            arg2.delegate.onItemClick(arg3.getTag().intValue());
        }
    }

    public static boolean lambda$createPopupLayout$1(ActionBarMenuItem arg1, View arg2, MotionEvent arg3) {
        if(arg3.getActionMasked() == 0 && arg1.popupWindow != null && (arg1.popupWindow.isShowing())) {
            arg2.getHitRect(arg1.rect);
            if(!arg1.rect.contains(((int)arg3.getX()), ((int)arg3.getY()))) {
                arg1.popupWindow.dismiss();
            }
        }

        return 0;
    }

    public static void lambda$createPopupLayout$2(ActionBarMenuItem arg2, KeyEvent arg3) {
        if(arg3.getKeyCode() == 4 && arg3.getRepeatCount() == 0 && arg2.popupWindow != null && (arg2.popupWindow.isShowing())) {
            arg2.popupWindow.dismiss();
        }
    }

    public static void lambda$onTouchEvent$0(ActionBarMenuItem arg2) {
        if(arg2.getParent() != null) {
            arg2.getParent().requestDisallowInterceptTouchEvent(true);
        }

        arg2.toggleSubMenu();
    }

    public static boolean lambda$setIsSearchField$7(ActionBarMenuItem arg0, TextView arg1, int arg2, KeyEvent arg3) {
        if(arg3 != null) {
            if(arg3.getAction() != 1 || arg3.getKeyCode() != 84) {
                if(arg3.getAction() != 0) {
                }
                else if(arg3.getKeyCode() == 66) {
                    goto label_12;
                }

                return 0;
            }

        label_12:
            AndroidUtilities.hideKeyboard(arg0.searchField);
            if(arg0.listener == null) {
                return 0;
            }

            arg0.listener.onSearchPressed(arg0.searchField);
        }

        return 0;
    }

    public static void lambda$setIsSearchField$8(ActionBarMenuItem arg1, View arg2) {
        if(arg1.searchField.length() != 0) {
            arg1.searchField.setText("");
        }
        else if(arg1.searchFieldCaption != null && arg1.searchFieldCaption.getVisibility() == 0) {
            arg1.searchFieldCaption.setVisibility(8);
            if(arg1.listener != null) {
                arg1.listener.onCaptionCleared();
            }
        }

        arg1.searchField.requestFocus();
        AndroidUtilities.showKeyboard(arg1.searchField);
    }

    public static boolean lambda$toggleSubMenu$6(ActionBarMenuItem arg0, View arg1, int arg2, KeyEvent arg3) {
        if(arg2 == 82 && arg3.getRepeatCount() == 0 && arg3.getAction() == 1 && arg0.popupWindow != null && (arg0.popupWindow.isShowing())) {
            arg0.popupWindow.dismiss();
            return 1;
        }

        return 0;
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        if(this.popupWindow != null && (this.popupWindow.isShowing())) {
            this.updateOrShowPopup(false, true);
        }
    }

    public boolean onTouchEvent(MotionEvent arg10) {
        if(arg10.getActionMasked() != 0) {
            View v2 = null;
            if(arg10.getActionMasked() == 2) {
                if(this.hasSubMenu()) {
                    if(this.popupWindow != null) {
                        if(this.popupWindow == null) {
                        }
                        else if(!this.popupWindow.isShowing()) {
                            goto label_35;
                        }

                        goto label_45;
                    }

                label_35:
                    if(arg10.getY() <= (((float)this.getHeight()))) {
                        goto label_152;
                    }

                    if(this.getParent() != null) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                    }

                    this.toggleSubMenu();
                    return 1;
                }

            label_45:
                if(this.popupWindow == null) {
                    goto label_152;
                }

                if(!this.popupWindow.isShowing()) {
                    goto label_152;
                }

                this.getLocationOnScreen(this.location);
                float v0 = arg10.getX() + (((float)this.location[0]));
                float v1 = arg10.getY() + (((float)this.location[1]));
                this.popupLayout.getLocationOnScreen(this.location);
                v0 -= ((float)this.location[0]);
                v1 -= ((float)this.location[1]);
                this.selectedMenuView = v2;
                int v2_1;
                for(v2_1 = 0; true; ++v2_1) {
                    if(v2_1 >= this.popupLayout.getItemsCount()) {
                        goto label_152;
                    }

                    View v5 = this.popupLayout.getItemAt(v2_1);
                    v5.getHitRect(this.rect);
                    if(v5.getTag().intValue() < 100) {
                        int v7 = 21;
                        if(!this.rect.contains(((int)v0), ((int)v1))) {
                            v5.setPressed(false);
                            v5.setSelected(false);
                            if(Build$VERSION.SDK_INT == v7) {
                                v5.getBackground().setVisible(false, false);
                            }
                        }
                        else {
                            v5.setPressed(true);
                            v5.setSelected(true);
                            if(Build$VERSION.SDK_INT >= v7) {
                                if(Build$VERSION.SDK_INT == v7) {
                                    v5.getBackground().setVisible(true, false);
                                }

                                v5.drawableHotspotChanged(v0, v1 - (((float)v5.getTop())));
                            }

                            this.selectedMenuView = v5;
                        }
                    }
                }
            }

            if(this.popupWindow != null && (this.popupWindow.isShowing()) && arg10.getActionMasked() == 1) {
                if(this.selectedMenuView != null) {
                    this.selectedMenuView.setSelected(false);
                    if(this.parentMenu != null) {
                        this.parentMenu.onItemClick(this.selectedMenuView.getTag().intValue());
                    }
                    else if(this.delegate != null) {
                        this.delegate.onItemClick(this.selectedMenuView.getTag().intValue());
                    }

                    this.popupWindow.dismiss(this.allowCloseAnimation);
                }
                else {
                    this.popupWindow.dismiss();
                }

                goto label_152;
            }

            if(this.selectedMenuView == null) {
                goto label_152;
            }

            this.selectedMenuView.setSelected(false);
            this.selectedMenuView = v2;
        }
        else if((this.longClickEnabled) && (this.hasSubMenu())) {
            if(this.popupWindow != null) {
                if(this.popupWindow == null) {
                }
                else if(!this.popupWindow.isShowing()) {
                    goto label_13;
                }

                goto label_152;
            }

        label_13:
            this.showMenuRunnable = new -$$Lambda$ActionBarMenuItem$Y4Ro71_Kozj7zLr98hi2EHrr4-A(this);
            AndroidUtilities.runOnUIThread(this.showMenuRunnable, 200);
        }

    label_152:
        return super.onTouchEvent(arg10);
    }

    public void openSearch(boolean arg2) {
        if(this.searchContainer != null && this.searchContainer.getVisibility() != 0) {
            if(this.parentMenu == null) {
            }
            else {
                this.parentMenu.parentActionBar.onSearchFieldVisibilityChanged(this.toggleSearch(arg2));
            }
        }
    }

    public void redrawPopup(int arg4) {
        if(this.popupLayout != null) {
            this.popupLayout.backgroundDrawable.setColorFilter(new PorterDuffColorFilter(arg4, PorterDuff$Mode.MULTIPLY));
            this.popupLayout.invalidate();
        }
    }

    public ActionBarMenuItem setActionBarMenuItemSearchListener(ActionBarMenuItemSearchListener arg1) {
        this.listener = arg1;
        return this;
    }

    public void setAdditionalOffset(int arg1) {
        this.additionalOffset = arg1;
    }

    public ActionBarMenuItem setAllowCloseAnimation(boolean arg1) {
        this.allowCloseAnimation = arg1;
        return this;
    }

    public void setDelegate(ActionBarMenuItemDelegate arg1) {
        this.delegate = arg1;
    }

    public void setIcon(int arg2) {
        this.iconView.setImageResource(arg2);
    }

    public void setIcon(Drawable arg2) {
        this.iconView.setImageDrawable(arg2);
    }

    public void setIconColor(int arg4) {
        this.iconView.setColorFilter(new PorterDuffColorFilter(arg4, PorterDuff$Mode.MULTIPLY));
        if(this.clearButton != null) {
            this.clearButton.setColorFilter(new PorterDuffColorFilter(arg4, PorterDuff$Mode.MULTIPLY));
        }
    }

    public void setIgnoreOnTextChange() {
        this.ignoreOnTextChange = true;
    }

    public ActionBarMenuItem setIsSearchField(boolean arg11) {
        float v8;
        float v7;
        int v5;
        int v3;
        EditTextBoldCursor v2_1;
        FrameLayout v0_1;
        float v9;
        float v6;
        float v4;
        if(this.parentMenu == null) {
            return this;
        }

        if((arg11) && this.searchContainer == null) {
            this.searchContainer = new FrameLayout(this.getContext()) {
                protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
                    super.onLayout(arg1, arg2, arg3, arg4, arg5);
                    arg2 = 0;
                    if(LocaleController.isRTL) {
                    }
                    else if(ActionBarMenuItem.this.searchFieldCaption.getVisibility() == 0) {
                        arg2 = AndroidUtilities.dp(4f) + ActionBarMenuItem.this.searchFieldCaption.getMeasuredWidth();
                    }

                    ActionBarMenuItem.this.searchField.layout(arg2, ActionBarMenuItem.this.searchField.getTop(), ActionBarMenuItem.this.searchField.getMeasuredWidth() + arg2, ActionBarMenuItem.this.searchField.getBottom());
                }

                protected void onMeasure(int arg8, int arg9) {
                    int v4;
                    this.measureChildWithMargins(ActionBarMenuItem.this.clearButton, arg8, 0, arg9, 0);
                    if(ActionBarMenuItem.this.searchFieldCaption.getVisibility() == 0) {
                        this.measureChildWithMargins(ActionBarMenuItem.this.searchFieldCaption, arg8, View$MeasureSpec.getSize(arg8) / 2, arg9, 0);
                        v4 = ActionBarMenuItem.this.searchFieldCaption.getMeasuredWidth() + AndroidUtilities.dp(4f);
                    }
                    else {
                        v4 = 0;
                    }

                    this.measureChildWithMargins(ActionBarMenuItem.this.searchField, arg8, v4, arg9, 0);
                    View$MeasureSpec.getSize(arg8);
                    View$MeasureSpec.getSize(arg9);
                    this.setMeasuredDimension(View$MeasureSpec.getSize(arg8), View$MeasureSpec.getSize(arg9));
                }
            };
            this.parentMenu.addView(this.searchContainer, 0, LayoutHelper.createLinear(0, -1, 1f, 6, 0, 0, 0));
            this.searchContainer.setVisibility(8);
            this.searchFieldCaption = new TextView(this.getContext());
            float v2 = 18f;
            this.searchFieldCaption.setTextSize(1, v2);
            this.searchFieldCaption.setTextColor(Theme.getColor("actionBarDefaultSearch"));
            this.searchFieldCaption.setSingleLine(true);
            this.searchFieldCaption.setEllipsize(TextUtils$TruncateAt.END);
            this.searchFieldCaption.setVisibility(8);
            TextView v0 = this.searchFieldCaption;
            int v1 = LocaleController.isRTL ? 5 : 3;
            v0.setGravity(v1);
            this.searchField = new EditTextBoldCursor(this.getContext()) {
                public boolean dispatchKeyEvent(KeyEvent arg1) {
                    return super.dispatchKeyEvent(arg1);
                }

                public boolean onKeyDown(int arg2, KeyEvent arg3) {
                    if(arg2 == 67 && ActionBarMenuItem.this.searchField.length() == 0 && ActionBarMenuItem.this.searchFieldCaption.getVisibility() == 0 && ActionBarMenuItem.this.searchFieldCaption.length() > 0) {
                        ActionBarMenuItem.this.clearButton.callOnClick();
                        return 1;
                    }

                    return super.onKeyDown(arg2, arg3);
                }
            };
            this.searchField.setCursorWidth(1.5f);
            v1 = -1;
            this.searchField.setCursorColor(v1);
            this.searchField.setTextSize(1, v2);
            this.searchField.setHintTextColor(Theme.getColor("actionBarDefaultSearchPlaceholder"));
            this.searchField.setTextColor(Theme.getColor("actionBarDefaultSearch"));
            this.searchField.setSingleLine(true);
            this.searchField.setBackgroundResource(0);
            this.searchField.setPadding(0, 0, 0, 0);
            this.searchField.setInputType(this.searchField.getInputType() | 524288);
            if(Build$VERSION.SDK_INT < 23) {
                this.searchField.setCustomSelectionActionModeCallback(new ActionMode$Callback() {
                    public boolean onActionItemClicked(ActionMode arg1, MenuItem arg2) {
                        return 0;
                    }

                    public boolean onCreateActionMode(ActionMode arg1, Menu arg2) {
                        return 0;
                    }

                    public void onDestroyActionMode(ActionMode arg1) {
                    }

                    public boolean onPrepareActionMode(ActionMode arg1, Menu arg2) {
                        return 0;
                    }
                });
            }

            this.searchField.setOnEditorActionListener(new -$$Lambda$ActionBarMenuItem$DSACM5xoXBBb-9TAnJG5eS-F3HQ(this));
            this.searchField.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable arg1) {
                }

                public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
                }

                public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
                    if(ActionBarMenuItem.this.ignoreOnTextChange) {
                        ActionBarMenuItem.this.ignoreOnTextChange = false;
                        return;
                    }

                    if(ActionBarMenuItem.this.listener != null) {
                        ActionBarMenuItem.this.listener.onTextChanged(ActionBarMenuItem.this.searchField);
                    }
                }
            });
            this.searchField.setImeOptions(33554435);
            this.searchField.setTextIsSelectable(false);
            if(!LocaleController.isRTL) {
                v4 = 36f;
                v6 = 0f;
                v9 = 0f;
                this.searchContainer.addView(this.searchFieldCaption, LayoutHelper.createFrame(-2, v4, 19, 0f, 5.5f, 0f, 0f));
                v0_1 = this.searchContainer;
                v2_1 = this.searchField;
                v3 = -1;
                v5 = 16;
                v7 = 0f;
                v8 = 48f;
            }
            else {
                v4 = 36f;
                v6 = 0f;
                v8 = 48f;
                v9 = 0f;
                this.searchContainer.addView(this.searchField, LayoutHelper.createFrame(-1, v4, 16, 0f, 0f, v8, 0f));
                v0_1 = this.searchContainer;
                TextView v2_2 = this.searchFieldCaption;
                v3 = -2;
                v5 = 21;
                v7 = 5.5f;
            }

            v0_1.addView(((View)v2_1), LayoutHelper.createFrame(v3, v4, v5, v6, v7, v8, v9));
            this.clearButton = new ImageView(this.getContext());
            ImageView v0_2 = this.clearButton;
            CloseProgressDrawable2 v2_3 = new CloseProgressDrawable2();
            this.progressDrawable = v2_3;
            v0_2.setImageDrawable(((Drawable)v2_3));
            this.clearButton.setColorFilter(new PorterDuffColorFilter(this.parentMenu.parentActionBar.itemsColor, PorterDuff$Mode.MULTIPLY));
            this.clearButton.setScaleType(ImageView$ScaleType.CENTER);
            this.clearButton.setOnClickListener(new -$$Lambda$ActionBarMenuItem$_MHVU-Pdp5nAX3_6TiPCB165nO8(this));
            this.searchContainer.addView(this.clearButton, LayoutHelper.createFrame(48, v1, 21));
        }

        this.isSearchField = arg11;
        return this;
    }

    public void setLayoutInScreen(boolean arg1) {
        this.layoutInScreen = arg1;
    }

    public void setLongClickEnabled(boolean arg1) {
        this.longClickEnabled = arg1;
    }

    public ActionBarMenuItem setOverrideMenuClick(boolean arg1) {
        this.overrideMenuClick = arg1;
        return this;
    }

    public void setPopupAnimationEnabled(boolean arg2) {
        if(this.popupWindow != null) {
            this.popupWindow.setAnimationEnabled(arg2);
        }

        this.animationEnabled = arg2;
    }

    public void setPopupItemsColor(int arg5) {
        if(this.popupLayout == null) {
            return;
        }

        int v0 = this.popupLayout.linearLayout.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.popupLayout.linearLayout.getChildAt(v1);
            if((v2 instanceof TextView)) {
                ((TextView)v2).setTextColor(arg5);
            }
        }
    }

    public void setSearchFieldCaption(CharSequence arg3) {
        if(TextUtils.isEmpty(arg3)) {
            this.searchFieldCaption.setVisibility(8);
        }
        else {
            this.searchFieldCaption.setVisibility(0);
            this.searchFieldCaption.setText(arg3);
        }
    }

    public void setShowSearchProgress(boolean arg2) {
        if(this.progressDrawable == null) {
            return;
        }

        if(arg2) {
            this.progressDrawable.startAnimation();
        }
        else {
            this.progressDrawable.stopAnimation();
        }
    }

    public void setSubMenuOpenSide(int arg1) {
        this.subMenuOpenSide = arg1;
    }

    public void showSubItem(int arg2) {
        View v2 = this.popupLayout.findViewWithTag(Integer.valueOf(arg2));
        if(v2 != null && v2.getVisibility() != 0) {
            v2.setVisibility(0);
        }
    }

    public boolean toggleSearch(boolean arg4) {
        if(this.searchContainer == null) {
            return 0;
        }

        int v2 = 8;
        if(this.searchContainer.getVisibility() == 0) {
            if(this.listener == null || this.listener != null && (this.listener.canCollapseSearch())) {
                this.searchContainer.setVisibility(v2);
                this.searchField.clearFocus();
                this.setVisibility(0);
                if(arg4) {
                    AndroidUtilities.hideKeyboard(this.searchField);
                }

                if(this.listener == null) {
                    return 0;
                }

                this.listener.onSearchCollapse();
            }

            return 0;
        }

        this.searchContainer.setVisibility(0);
        this.setVisibility(v2);
        this.searchField.setText("");
        this.searchField.requestFocus();
        if(arg4) {
            AndroidUtilities.showKeyboard(this.searchField);
        }

        if(this.listener != null) {
            this.listener.onSearchExpand();
        }

        return 1;
    }

    public void toggleSubMenu() {
        if(this.popupLayout == null) {
            return;
        }

        if(this.showMenuRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.showMenuRunnable);
            this.showMenuRunnable = null;
        }

        if(this.popupWindow != null && (this.popupWindow.isShowing())) {
            this.popupWindow.dismiss();
            return;
        }

        if(this.popupWindow == null) {
            this.popupWindow = new ActionBarPopupWindow(this.popupLayout, -2, -2);
            if(!this.animationEnabled || Build$VERSION.SDK_INT < 19) {
                this.popupWindow.setAnimationStyle(2131689702);
            }
            else {
                this.popupWindow.setAnimationStyle(0);
            }

            if(!this.animationEnabled) {
                this.popupWindow.setAnimationEnabled(this.animationEnabled);
            }

            this.popupWindow.setOutsideTouchable(true);
            this.popupWindow.setClippingEnabled(true);
            if(this.layoutInScreen) {
                try {
                    if(ActionBarMenuItem.layoutInScreenMethod == null) {
                        ActionBarMenuItem.layoutInScreenMethod = PopupWindow.class.getDeclaredMethod("setLayoutInScreenEnabled", Boolean.TYPE);
                        ActionBarMenuItem.layoutInScreenMethod.setAccessible(true);
                    }

                    ActionBarMenuItem.layoutInScreenMethod.invoke(this.popupWindow, Boolean.valueOf(true));
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }

            this.popupWindow.setInputMethodMode(2);
            this.popupWindow.setSoftInputMode(0);
            this.popupLayout.measure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000f), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000f), -2147483648));
            this.popupWindow.getContentView().setFocusableInTouchMode(true);
            this.popupWindow.getContentView().setOnKeyListener(new -$$Lambda$ActionBarMenuItem$9YLDoQyZnlPz968V4zO2iegYPb0(this));
        }

        this.processedPopupClick = false;
        this.popupWindow.setFocusable(true);
        if(this.popupLayout.getMeasuredWidth() == 0) {
            this.updateOrShowPopup(true, true);
        }
        else {
            this.updateOrShowPopup(true, false);
        }

        this.popupWindow.startAnimation();
    }

    private void updateOrShowPopup(boolean arg8, boolean arg9) {
        int v6;
        int v5;
        ActionBarPopupWindow v8;
        int v3;
        ActionBarPopupWindow v1_2;
        ActionBar v2;
        int v1;
        int v0;
        if(this.parentMenu != null) {
            v0 = -this.parentMenu.parentActionBar.getMeasuredHeight();
            v1 = this.parentMenu.getTop();
        }
        else {
            float v0_1 = this.getScaleY();
            v0 = -(((int)((((float)this.getMeasuredHeight())) * v0_1 - this.getTranslationY() / v0_1)));
            v1 = this.additionalOffset;
        }

        int v4 = v0 + v1;
        if(arg8) {
            this.popupLayout.scrollToTop();
        }

        float v1_1 = 8f;
        if(this.parentMenu != null) {
            v2 = this.parentMenu.parentActionBar;
            if(this.subMenuOpenSide == 0) {
                if(arg8) {
                    this.popupWindow.showAsDropDown(((View)v2), this.getLeft() + this.parentMenu.getLeft() + this.getMeasuredWidth() - this.popupLayout.getMeasuredWidth() + (((int)this.getTranslationX())), v4);
                }

                if(!arg9) {
                    return;
                }

                v1_2 = this.popupWindow;
                v3 = this.getLeft() + this.parentMenu.getLeft() + this.getMeasuredWidth() - this.popupLayout.getMeasuredWidth() + (((int)this.getTranslationX()));
                goto label_61;
            }
            else {
                if(arg8) {
                    this.popupWindow.showAsDropDown(((View)v2), this.getLeft() - AndroidUtilities.dp(v1_1) + (((int)this.getTranslationX())), v4);
                }

                if(!arg9) {
                    return;
                }

                v8 = this.popupWindow;
                v3 = this.getLeft() - AndroidUtilities.dp(v1_1) + (((int)this.getTranslationX()));
                v5 = -1;
                v6 = -1;
                v1_2 = v8;
                goto label_122;
            }
        }
        else {
            if(this.subMenuOpenSide == 0) {
                if(this.getParent() == null) {
                    return;
                }

                ViewParent v2_1 = this.getParent();
                if(arg8) {
                    this.popupWindow.showAsDropDown(((View)v2_1), this.getLeft() + this.getMeasuredWidth() - this.popupLayout.getMeasuredWidth(), v4);
                }

                if(!arg9) {
                    return;
                }

                v1_2 = this.popupWindow;
                v3 = this.getLeft() + this.getMeasuredWidth() - this.popupLayout.getMeasuredWidth();
            label_61:
                v5 = -1;
                v6 = -1;
                goto label_122;
            }

            if(arg8) {
                this.popupWindow.showAsDropDown(((View)this), -AndroidUtilities.dp(v1_1), v4);
            }

            if(!arg9) {
                return;
            }

            v8 = this.popupWindow;
            v3 = -AndroidUtilities.dp(v1_1);
            v5 = -1;
            v6 = -1;
            v1_2 = v8;
            ActionBarMenuItem v2_2 = this;
        label_122:
            v1_2.update(((View)v2), v3, v4, v5, v6);
        }
    }
}

