package vn.iotstar.baitaptuan7_vidukhac.Volley;

public class User {
    private int id;
    private String email;
    private String name;

    // Constructor
    public User(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}