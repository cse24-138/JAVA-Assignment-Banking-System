package model;

public class Customer {
    private long id;
    private String firstName, lastName, address, email, passwordHash;

    public Customer(long id, String firstName, String lastName, String address, String email, String passwordHash) {
        this.id = id; this.firstName = firstName; this.lastName = lastName;
        this.address = address; this.email = email; this.passwordHash = passwordHash;
    }
    public long getId(){ return id; }  public void setId(long id){ this.id = id; }
    public String getFirstName(){ return firstName; } public void setFirstName(String v){ firstName=v; }
    public String getLastName(){ return lastName; } public void setLastName(String v){ lastName=v; }
    public String getAddress(){ return address; } public void setAddress(String v){ address=v; }
    public String getEmail(){ return email; } public void setEmail(String v){ email=v; }
    public String getPasswordHash(){ return passwordHash; } public void setPasswordHash(String v){ passwordHash=v; }
    @Override public String toString(){ return id+" - "+firstName+" "+lastName; }
}
