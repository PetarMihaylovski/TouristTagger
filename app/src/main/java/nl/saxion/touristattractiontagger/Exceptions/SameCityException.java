package nl.saxion.touristattractiontagger.Exceptions;

public class SameCityException extends Exception {
    public SameCityException() {
        super();
    }

    @Override
    public String toString() {
        return "Cannot add the same city twice!";
    }
}
