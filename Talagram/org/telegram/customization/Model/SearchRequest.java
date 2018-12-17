package org.telegram.customization.Model;

public class SearchRequest {
    int direction;
    long lastRow;
    long mediaType;
    int pageSize;
    boolean phrasesearch;
    String searchTerm;
    long sortOrder;
    int tagId;

    public SearchRequest() {
        super();
    }

    public int getDirection() {
        return this.direction;
    }

    public long getLastRow() {
        return this.lastRow;
    }

    public long getMediaType() {
        return this.mediaType;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public long getSortOrder() {
        return this.sortOrder;
    }

    public int getTagId() {
        return this.tagId;
    }

    public boolean isPhrasesearch() {
        return this.phrasesearch;
    }

    public void setDirection(int arg1) {
        this.direction = arg1;
    }

    public void setLastRow(long arg1) {
        this.lastRow = arg1;
    }

    public void setMediaType(long arg1) {
        this.mediaType = arg1;
    }

    public void setPageSize(int arg1) {
        this.pageSize = arg1;
    }

    public void setPhrasesearch(boolean arg1) {
        this.phrasesearch = arg1;
    }

    public void setSearchTerm(String arg1) {
        this.searchTerm = arg1;
    }

    public void setSortOrder(long arg1) {
        this.sortOrder = arg1;
    }

    public void setTagId(int arg1) {
        this.tagId = arg1;
    }
}

