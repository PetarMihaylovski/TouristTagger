package nl.saxion.touristattractiontagger.Exceptions;

public class SameTouristAttractionException extends Exception {

    public SameTouristAttractionException() {
        super();
    }

    @Override
    public String toString() {
        return "Cannot add the same tourist attraction twice!";
    }
}
