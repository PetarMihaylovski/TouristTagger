package nl.saxion.touristattractiontagger.Users;

public class Administrator extends User {
    private String password; //The password for the administrator login.

    /**
     * Constructor.
     * @param name The admin username.
     * @param password The admin password.
     */
    public Administrator(String name,String password) {
        super(name);
        this.password = password;
    }

    /**
     * Getter
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
}
