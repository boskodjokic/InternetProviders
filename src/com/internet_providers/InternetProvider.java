package com.internet_providers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InternetProvider {

    private String name;
    private List<Customer> customers;

    public InternetProvider(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
    }

    public void generateInvoices() {
        List<Customer> postpaidC = returnCustomers("postpaid");
        for (Customer c : postpaidC) {
            PostpaidCustomer pc = (PostpaidCustomer) c;
            System.out.println(pc.generateInvoice());
        }
    }

    public void inputTraffic(Customer c, String url, int mb) {
        try {
            FileWriter fw = new FileWriter("traffic_saved.txt", true);
            fw.write(c.getContractNum() + ":" + url + ":" + mb);
            fw.write("\r\n");
            fw.close();

        } catch (IOException e) {
            System.out.println("Error occured.");
        }
    }

    public void printPrepaidCustomers() {
        List<Customer> prepaidCustomers = returnCustomers("prepaid");
        for (Customer c : prepaidCustomers) {
            System.out.println(c);
            System.out.println("\n");
        }
    }

    public void printPostpaidCustomers() {
        List<Customer> postpaidCustomers = returnCustomers("postpaid");
        for (Customer c : postpaidCustomers) {
            System.out.println(c);
            System.out.println("\n");
        }
    }

    public List<Customer> returnCustomers(String tariffName) {
        List<Customer> customersToReturn = new ArrayList<>();

        for (int i = 0; i < this.customers.size(); i++) {
            if(customers.get(i).getCustomerType().equalsIgnoreCase(tariffName)) {
                customersToReturn.add(customers.get(i));
            }
        }
        return customersToReturn;
    }

    public void addCustomer(Customer c) {
        for (int i = 0; i < this.customers.size(); i++) {
            if (this.customers.get(i).equals(c)) {
                System.out.println("Customer already exists.");
                break;
            }
        }
        this.customers.add(c);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Provider's name: ").append(this.getName());
        sb.append("\n");
        sb.append("List of customers: ");
        sb.append("\n");

        for (int i = 0; i < customers.size(); i++) {
            sb.append(Integer.toString(i));
            sb.append("\n");
            sb.append(customers.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}