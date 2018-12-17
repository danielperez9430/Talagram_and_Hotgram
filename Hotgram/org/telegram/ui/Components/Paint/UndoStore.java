package org.telegram.ui.Components.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.telegram.messenger.AndroidUtilities;

public class UndoStore {
    public interface UndoStoreDelegate {
        void historyChanged();
    }

    private UndoStoreDelegate delegate;
    private List operations;
    private Map uuidToOperationMap;

    public UndoStore() {
        super();
        this.uuidToOperationMap = new HashMap();
        this.operations = new ArrayList();
    }

    static UndoStoreDelegate access$000(UndoStore arg0) {
        return arg0.delegate;
    }

    public boolean canUndo() {
        return this.operations.isEmpty() ^ 1;
    }

    private void notifyOfHistoryChanges() {
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                if(UndoStore.this.delegate != null) {
                    UndoStore.this.delegate.historyChanged();
                }
            }
        });
    }

    public void registerUndo(UUID arg2, Runnable arg3) {
        this.uuidToOperationMap.put(arg2, arg3);
        this.operations.add(arg2);
        this.notifyOfHistoryChanges();
    }

    public void reset() {
        this.operations.clear();
        this.uuidToOperationMap.clear();
        this.notifyOfHistoryChanges();
    }

    public void setDelegate(UndoStoreDelegate arg1) {
        this.delegate = arg1;
    }

    public void undo() {
        if(this.operations.size() == 0) {
            return;
        }

        int v0 = this.operations.size() - 1;
        Object v1 = this.operations.get(v0);
        Object v2 = this.uuidToOperationMap.get(v1);
        this.uuidToOperationMap.remove(v1);
        this.operations.remove(v0);
        ((Runnable)v2).run();
        this.notifyOfHistoryChanges();
    }

    public void unregisterUndo(UUID arg2) {
        this.uuidToOperationMap.remove(arg2);
        this.operations.remove(arg2);
        this.notifyOfHistoryChanges();
    }
}

