package com.internet_providers;

public class TariffAddOn {

    private double addOnsprice;
    private AddOns type;

    public TariffAddOn(double addOnsprice, AddOns type) {
        this.addOnsprice = addOnsprice;
        this.type = type;
    }

    public double getAddOnsprice() {
        return addOnsprice;
    }

    public AddOns getType() {
        return type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(AddOns type : AddOns.values()) {
            sb.append(type.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
