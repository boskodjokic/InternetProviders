package com.internet_providers;

public class TariffPackage {

    private String name;
    private int speed;
    private double packagePrice;
    private boolean unlimitedTraffic;
    private int megabytes;
    private double pricePerMb;

    public TariffPackage(String name, int speed, double packagePrice, boolean unlimitedTraffic, int megabytes, double pricePerMb) {
        this.name = name;
        this.speed = speed;
        this.packagePrice = packagePrice;
        this.unlimitedTraffic = unlimitedTraffic;
        this.megabytes = megabytes;
        this.pricePerMb = pricePerMb;
    }

    public String getName() {
        return this.name;
    }
    public int getSpeed() {
        return speed;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public int getMegabytes() {
        return megabytes;
    }

    public double getPricePerMb() {
        return pricePerMb;
    }

    public boolean isUnlimited() {
        return unlimitedTraffic;
    }

    public void setMegabytes(int megabytes) {
        this.megabytes = megabytes;
    }
    public void decreaseMegabytes(int mb) {
        this.megabytes -= mb;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tariff package: ").append(name);
        sb.append("\n");
        sb.append("Speed: ").append(Integer.toString(speed));
        sb.append("\n");
        sb.append("Package price: ").append(Double.toString(packagePrice));
        sb.append("\n");
        sb.append("Unlimited traffic: ").append(unlimitedTraffic);
        sb.append("\n");
        sb.append("Megabytes: ").append(Integer.toString(megabytes));
        sb.append("\n");
        sb.append("Price per MB: ").append(Double.toString(pricePerMb));
        sb.append("\n");

        return sb.toString();
    }

}
