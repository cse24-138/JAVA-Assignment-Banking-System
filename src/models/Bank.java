package model;

import java.util.*;

public class Bank {
    private final String name;
    private final String branch;
    private final Map<Long, Customer> customers = new HashMap<>();
    private long nextId = 1000L; // simple in-memory id counter

    public Bank(String name, String branch) {
        this.name = name;
        this.branch = branch;
    }

    // basic operations
    public Customer registerCustomer(String firstName, String lastName, String address, int age) {
        long id = nextId++;
        Customer c = new Customer(id, firstName, lastName, address, age);
        customers.put(id, c);
        return c;
    }

    public Optional<Customer> findCustomer(long id) {
        return Optional.ofNullable(customers.get(id));
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public int countCustomers() {
        return customers.size();
    }

    // getters
    public String getName() { return name; }
    public String getBranch() { return branch; }
}
