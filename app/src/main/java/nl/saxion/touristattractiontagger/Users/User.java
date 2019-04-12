package nl.saxion.touristattractiontagger.Users;

public abstract class User {
    private String name;

    /**
     * Constructor.
     * @param name The name of the user.
     */
    public User(String name){
        this.name = name;
    }

    /**
     * Getters.
     * @return The required value.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
