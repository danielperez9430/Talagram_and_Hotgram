package org.telegram.customization.Model;

public class FilterHelper {
    long categories;
    int mediaTypes;
    int sortOption;

    public FilterHelper() {
        super();
        this.categories = 0;
        this.mediaTypes = 0;
    }

    public long getCategories() {
        return this.categories;
    }

    public int getMediaTypes() {
        return this.mediaTypes;
    }

    public int getSortOption() {
        return this.sortOption;
    }

    public void setCategories(long arg1) {
        this.categories = arg1;
    }

    public void setMediaTypes(int arg1) {
        this.mediaTypes = arg1;
    }

    public void setSortOption(int arg1) {
        this.sortOption = arg1;
    }
}

