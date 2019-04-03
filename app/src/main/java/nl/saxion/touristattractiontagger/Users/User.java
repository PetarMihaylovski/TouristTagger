package nl.saxion.touristattractiontagger.Users;

import androidx.annotation.NonNull;

public abstract class User {
    private String name;

    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
