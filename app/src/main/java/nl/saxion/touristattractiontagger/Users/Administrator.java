package nl.saxion.touristattractiontagger.Users;

public class Administrator extends User {
    private String password;

    public Administrator(String name,String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
