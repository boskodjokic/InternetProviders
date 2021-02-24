package com.internet_providers;

public class PrepaidCustomer extends Customer {

    private double credit;
    private final double PRICE_PER_MB = 2;

    public PrepaidCustomer(String customerType, String name, String surname, String address, String contractNum,
                           InternetProvider internetProvider) {
        super(customerType, name, surname, address, contractNum, internetProvider);
    }

    @Override
    public boolean surf(String url, int mb) {
        ListingEntry entry = new ListingEntry(url, mb);

        if (haveAddOn(url)) {
            this.addListing(entry);
            this.getInternetProvider().inputTraffic(this, url, mb);
        } else {
            if (credit - PRICE_PER_MB * mb >= 0) {
                credit -= PRICE_PER_MB * mb;
                this.addListing(entry);
                this.getInternetProvider().inputTraffic(this, url, mb);
                return true;
            } else {
                System.out.println("Unfortunately, you are out of credit. " + "Can't continue surfing.");
                return false;
            }
        }
        return false;
    }

    @Override
    public void addTariffAddOns(TariffAddOn addOn) {
        if (addOn.getType().equals(AddOns.FIXED_TELEPHONY) == false
                && addOn.getType().equals(AddOns.IPTV) == false) {

            if (credit > addOn.getAddOnsprice()) {
                credit -= addOn.getAddOnsprice();
                this.setTariffAddOns(addOn);
                System.out.println("Successfully added " + addOn.getType().name() + ".");
            } else {
                System.out.println("Unforunately, you are out of credit." + "Can not add this add-on.");
            }
        } else {
            System.out.println("Prepaid customer cannot add fixed telephony " + "or IPTV addOns");
        }
    }

    public void addCredit(double credit) {
        this.credit += credit;
    }

    public double getCredit() {
        return this.credit;
    }

    public String toString() {
        return "Prepaid customer:" + "\n" + super.toString();
    }
}
