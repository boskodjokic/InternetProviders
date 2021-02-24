package com.internet_providers;

public class ListingEntry implements Comparable<ListingEntry>{

    private String url;
    private int megabytes;

    public ListingEntry(String url, int megabytes) {
        this.url = url;
        this.megabytes = megabytes;
    }

    public String getUrl() {
        return url;
    }

    public int getMegabytes() {
        return megabytes;
    }

    public void addMegabytes(int megabytes) {
        this.megabytes += megabytes;
    }

    public int compareTo(ListingEntry listingEntry) {
        return this.url.compareToIgnoreCase(listingEntry.getUrl());
    }

    public String toString(){
        return "URL: " + this.url + ", megabytes: " + this.megabytes;
    }
}
