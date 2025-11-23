package dao;

import model.Customer;
import java.io.*;
import java.util.HashMap;

public class DataStore {

    private static final String FILE = "bank_data.dat";

    // =========== MEMORY: STORED CUSTOMERS ===========
    private static HashMap<String, Customer> customers = loadAll();

    // =========== SAVE ONE CUSTOMER ===========
    public static void saveCustomer(Customer c) {
        customers.put(c.getUsername(), c);
        saveAll();
    }

    // =========== GET CUSTOMER BY USERNAME ===========
    public static Customer getCustomer(String username) {
        return customers.get(username);
    }

    // =========== SAVE EVERYTHING TO FILE ===========
    private static void saveAll() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========== LOAD EVERYTHING ===========
    private static HashMap<String, Customer> loadAll() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (HashMap<String, Customer>) in.readObject();
        } catch (Exception e) {
            return new HashMap<>(); // no data yet
        }
    }
}
