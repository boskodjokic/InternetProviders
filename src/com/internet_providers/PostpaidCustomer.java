package com.internet_providers;

public class PostpaidCustomer extends Customer {

    private double exceeding;
    private TariffPackage tariffPackage;

    public PostpaidCustomer(String customerType, String name, String surname, String address, String contractNum,
                            InternetProvider internetProvider, TariffPackage tariffPackage) {
        super(customerType, name, surname, address, contractNum, internetProvider);
        this.tariffPackage = tariffPackage;
    }

    public TariffPackage getTariffPackage() {
        return tariffPackage;
    }

    @Override
    public boolean surf(String url, int mb) {
        ListingEntry entry = new ListingEntry(url, mb);

        if (this.getTariffPackage().isUnlimited()) {
            this.addListing(entry);
            this.getInternetProvider().inputTraffic(this, url, mb);
        }else {
            if(haveAddOn(url)) {
                this.addListing(entry);
                this.getInternetProvider().inputTraffic(this, url, mb);
            }else {
                this.addListing(entry);
                this.getInternetProvider().inputTraffic(this, url, mb);
                if (this.getTariffPackage().getMegabytes() - mb >= 0) {
                    this.getTariffPackage().decreaseMegabytes(mb);
                }
                else
                {
                    exceeding += (mb - this.getTariffPackage().getMegabytes())
                            * this.getTariffPackage().getPricePerMb();
                    this.getTariffPackage().setMegabytes(0);
                }
            }
        }
        return true;
    }

    @Override
    public void addTariffAddOns(TariffAddOn addOn) {
        if (getTariffPackage().isUnlimited()) {
            if (addOn.getType().equals(AddOns.FIXED_TELEPHONY)==false &&
                    (addOn.getType().equals(AddOns.IPTV))==false) {
                System.out.println(
                        "Add-on already included in package." +
                                "Can use only fixed telephony or IPTV add-ons.");
            } else {
                super.setTariffAddOns(addOn);
                exceeding += addOn.getAddOnsprice();
                System.out.println("Successfully added " + addOn.getType().name() + ".");
            }
        } else {
            super.setTariffAddOns(addOn);
            exceeding += addOn.getAddOnsprice();
            System.out.println("Successfully added " + addOn.getType().name() + ".");
        }

    }

    public double totalForPayment() {
        return this.getTariffPackage().getPackagePrice() + exceeding;
    }

    public String generateInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("***Invoice*** ");
        sb.append("\n");
        sb.append("Package price: ").append(this.getTariffPackage().getPackagePrice());
        sb.append("\n");
        sb.append("Tariff add-ons: ");
        sb.append("\n");
        for (TariffAddOn addOn : super.getTariffAddOns()) {
            sb.append("* ").append(addOn.getType()).append(" has price: ").append(addOn.getAddOnsprice());
            sb.append("\n");
        }
        sb.append("Exceeding: ").append(Double.toString(exceeding));
        sb.append("\n");
        sb.append("Total for payment: ").append(Double.toString(this.totalForPayment()));
        sb.append("\n");

        return this.toString() + "\n" + sb.toString();
    }

    public double getExceedingTariff() {
        return exceeding;
    }

    public String toString() {
        return "Postpaid customer:" + "\n" + "Tariff package: " + this.tariffPackage.getName()+ super.toString();
    }
}
