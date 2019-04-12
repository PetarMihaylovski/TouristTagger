package nl.saxion.touristattractiontagger.Exceptions;

public class SameCityException extends Exception {
    /**
     * The exception occurs only when the user is trying to
     * add an already existing city.
     *
     * Constructor.
     */
    public SameCityException() {
        super();
    }

    @Override
    public String toString() {
        return "Cannot add the same city twice!";
    }
}
