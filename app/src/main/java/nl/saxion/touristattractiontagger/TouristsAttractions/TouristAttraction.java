package nl.saxion.touristattractiontagger.TouristsAttractions;

public abstract class TouristAttraction {
    private String name;
    private String location;
    private boolean hasBeenThere;


    public TouristAttraction(String name, String location) {
        this.name = name;
        this.location = location;
        this.hasBeenThere = false;
    }

    public void setHasBeenThere(boolean hasBeenThere) {
        this.hasBeenThere = hasBeenThere;
    }

    public boolean isHasBeenThere() {
        return hasBeenThere;
    }

    public abstract String getType();

    public abstract String getSpecialAttribute();

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    //TODO: make a real toString method.
    @Override
    public String toString() {
        //debug purposes only.
        return this.name;
    }
}
