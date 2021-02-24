package com.internet_providers;

public class MainInternetProvider {

    public static void main(String[] args) {

        InternetProvider telenor = new InternetProvider("Telenor");

        TariffPackage package1 = new TariffPackage("Postpaid", 10, 2200.00, false, 1000, 1.5);

        PostpaidCustomer postCustomer1 = new PostpaidCustomer("postpaid", "Bole", "Boskic", "Bolova adresa", "A123", telenor, package1);

        telenor.addCustomer(postCustomer1);

        System.out.println(postCustomer1.getName() + " adding facebook:");

        TariffAddOn facebook = new TariffAddOn(200.00, AddOns.FACEBOOK);
        postCustomer1.addTariffAddOns(facebook);

        System.out.println(postCustomer1.getName() + " adding instagram:");

        TariffAddOn instagram = new TariffAddOn(200.00, AddOns.INSTAGRAM);
        postCustomer1.addTariffAddOns(instagram);

        postCustomer1.surf("facebook.com", 2);
        postCustomer1.surf("facebook.com", 10);
        postCustomer1.surf("facebook.com", 30);
        postCustomer1.surf("instagram.com", 30);
        postCustomer1.surf("blabla.com", 30);
        postCustomer1.surf("blabla.com", 1000);

        PrepaidCustomer pc = new PrepaidCustomer ("prepaid", "Kosta", "Djokic", "Adresa", "B123", telenor);
        telenor.addCustomer(pc);

        pc.addCredit(1000);

        pc.surf("facebook.com", 10);
        pc.surf("facebook.com", 20);
        System.out.println(pc.getName() + " adding instagram:");
        pc.addTariffAddOns(instagram);
        pc.surf("instagram", 50);
        pc.surf("blabla.com", 30);

        System.out.println(postCustomer1.getName() + " adding fixed telephony:");
        TariffAddOn fixedTel = new TariffAddOn(500.00, AddOns.FIXED_TELEPHONY);
        postCustomer1.addTariffAddOns(fixedTel);
        System.out.println(pc.getName() + " adding fixed telephony:");
        pc.addTariffAddOns(fixedTel);

        TariffPackage package2 = new TariffPackage("Postpaid", 10, 3300.00, true, 1500, 1);

        PostpaidCustomer postCustomer2 = new PostpaidCustomer("postpaid", "Klara", "Djokic", "Ova adresA", "A234", telenor, package2);

        telenor.addCustomer(postCustomer2);

        System.out.println(postCustomer2.getName() + " adding facebook:");
        postCustomer2.addTariffAddOns(facebook);


        postCustomer2.surf("facebook.com", 30);
        postCustomer2.surf("facebook.com", 10);
        postCustomer2.surf("facebook.com", 50);
        postCustomer2.surf("instagram.com", 100);
        postCustomer2.surf("google.com", 50);
        postCustomer2.surf("google.com", 900);
        postCustomer2.surf("youtube.com", 1500);

        System.out.println();
        System.out.println(pc.makeListing());

        System.out.println(postCustomer1.makeListing());
        System.out.println(postCustomer2.makeListing());
        telenor.generateInvoices();
    }

}
