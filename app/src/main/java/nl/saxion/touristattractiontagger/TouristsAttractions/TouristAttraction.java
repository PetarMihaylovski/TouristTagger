package nl.saxion.touristattractiontagger.TouristsAttractions;

public abstract class TouristAttraction {
    private String name;
    private String location;
    private boolean isOnList;

    public boolean isHaveBeenThere() {
        return haveBeenThere;
    }

    public void setHaveBeenThere(boolean haveBeenThere) {
        this.haveBeenThere = haveBeenThere;
    }

    private boolean haveBeenThere = false;

    public TouristAttraction(String name, String location) {
        this.name = name;
        this.location = location;
        this.isOnList = false;
    }



    public abstract String getType();

    public abstract String getSpecialAttribute();

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public boolean isOnList() {
        return isOnList;
    }

    public void setIsOnList(boolean isOnList){
        this.isOnList = isOnList;
    }

    //TODO: make a real toString method.
    @Override
    public String toString() {
        //debug purposes only.
        return this.name;
    }
}
