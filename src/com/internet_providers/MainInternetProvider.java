package com.internet_providers;

public class MainInternetProvider {

    public static void main(String[] args) {

        InternetProvider a1 = new InternetProvider("A1");

        TariffPackage package1 = new TariffPackage("Postpaid", 20, 2500.00, false, 1000, 2);

        PostpaidCustomer postCustomer1 = new PostpaidCustomer("postpaid", "John", "Doe", "John's address", "C999", a1, package1);

        a1.addCustomer(postCustomer1);

        System.out.println(postCustomer1.getName() + " adding facebook:");

        TariffAddOn facebook = new TariffAddOn(300.00, AddOns.FACEBOOK);
        postCustomer1.addTariffAddOns(facebook);

        System.out.println(postCustomer1.getName() + " adding instagram:");

        TariffAddOn instagram = new TariffAddOn(200.00, AddOns.INSTAGRAM);
        postCustomer1.addTariffAddOns(instagram);

        postCustomer1.surf("facebook.com", 5);
        postCustomer1.surf("facebook.com", 15);
        postCustomer1.surf("facebook.com", 10);
        postCustomer1.surf("instagram.com", 50);
        postCustomer1.surf("google.com", 70);
        postCustomer1.surf("google.com", 800);

        PrepaidCustomer pc = new PrepaidCustomer ("prepaid", "Jane", "Doe", "Jane's address", "A087", a1);
        a1.addCustomer(pc);

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

        TariffPackage package2 = new TariffPackage("Postpaid", 15, 3500.00, true, 1500, 1.5);

        PostpaidCustomer postCustomer2 = new PostpaidCustomer("postpaid", "Fido", "Dog", "Fido's house", "F158", a1, package2);

        a1.addCustomer(postCustomer2);

        System.out.println(postCustomer2.getName() + " adding facebook:");
        postCustomer2.addTariffAddOns(facebook);


        postCustomer2.surf("facebook.com", 20);
        postCustomer2.surf("facebook.com", 30);
        postCustomer2.surf("facebook.com", 51);
        postCustomer2.surf("instagram.com", 150);
        postCustomer2.surf("google.com", 100);
        postCustomer2.surf("google.com", 750);
        postCustomer2.surf("youtube.com", 2000);

        System.out.println();
        System.out.println(pc.makeListing());

        System.out.println(postCustomer1.makeListing());
        System.out.println(postCustomer2.makeListing());
        a1.generateInvoices();
    }

}
