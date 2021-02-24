package com.internet_providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Customer implements Listing {

    private String customerType;
    private String name;
    private String surname;
    private String address;
    private String contractNum;
    private InternetProvider internetProvider;
    private List<TariffAddOn> tariffAddOns;
    private List<ListingEntry> listing;

    public Customer(String customerType, String name, String surname, String address, String contractNum, InternetProvider internetProvider) {
        this.customerType = customerType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.contractNum = contractNum;
        this.internetProvider = internetProvider;
        this.listing = new ArrayList<ListingEntry>();
        this.tariffAddOns = new ArrayList<TariffAddOn>();
    }

    public abstract boolean surf(String url, int mb);

    public abstract void addTariffAddOns(TariffAddOn addOn);

    public void addListing(ListingEntry entry) {
        this.listing.add(entry);
    }

    public String makeListing() {
        List<ListingEntry> sortedList = new ArrayList<>(listing);
        Collections.sort(sortedList);
        ListingEntry entry = listing.get(0);
        int oldMb = 0;
        int mbTotalPerUrl = entry.getMegabytes();

        Map<String, Integer> urlMb = new HashMap<String, Integer>();
        urlMb.put(entry.getUrl(), oldMb);

        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i).getUrl().contains(entry.getUrl())) {
                mbTotalPerUrl += sortedList.get(i).getMegabytes();
                if (urlMb.containsKey(entry.getUrl())) {
                    urlMb.replace(entry.getUrl(), oldMb, mbTotalPerUrl);
                    oldMb = mbTotalPerUrl;
                }
            } else {
                urlMb.put(sortedList.get(i).getUrl(), sortedList.get(i).getMegabytes());
                entry = sortedList.get(i);
                oldMb = sortedList.get(i).getMegabytes();
                mbTotalPerUrl = sortedList.get(i).getMegabytes();
            }
        }

        Iterator<Entry<String, Integer>> i = urlMb.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        int total = 0;
        sb.append("Listing for user: ").append(this.name +" " + this.surname);
        sb.append("\n");
        while (i.hasNext()) {
            Map.Entry<String, Integer> pair = (Entry<String, Integer>) i.next();
            total += pair.getValue();
            sb.append(pair.getKey()).append(" : ").append(Integer.toString(pair.getValue()));
            sb.append("\n");
        }
        sb.append("===================================");
        sb.append("\n");
        sb.append("MB total: ").append(Integer.toString(total));
        sb.append("\n");
        sb.append("************************************");
        sb.append("\n");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getContractNum() {
        return contractNum;
    }

    public InternetProvider getInternetProvider() {
        return internetProvider;
    }

    public List<TariffAddOn> getTariffAddOns() {
        return tariffAddOns;
    }

    public List<ListingEntry> getListings() {
        return listing;
    }

    public boolean haveAddOn(String url) {
        for (TariffAddOn addOns : this.getTariffAddOns()) {
            if (url.toUpperCase().contains(addOns.getType().name())) {
                return true;
            }
        }
        return false;
    }

    public void setTariffAddOns(TariffAddOn addOn) {
        this.tariffAddOns.add(addOn);
    }

    public String getCustomerType() {
        return this.customerType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Name: ").append(name);
        sb.append("\n");
        sb.append("Surname: ").append(surname);
        sb.append("\n");
        sb.append("Address: ").append(address);
        sb.append("\n");
        sb.append("Contract number: ").append(contractNum.toString());
        sb.append("\n");
        sb.append("Internet provider: ").append(this.getInternetProvider().getName());
        sb.append("\n");

        return sb.toString();
    }

}