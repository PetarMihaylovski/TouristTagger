package nl.saxion.touristattractiontagger.TouristsAttractions;

public abstract class TouristAttraction {
    private String name;
    private String location;
    private boolean checked; //a variable needed for the checkbox.

    public TouristAttraction(String name, String location) {
        this.name = name;
        this.location = location;
        this.checked = false;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
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
        return getName() + " (" + getType() + ")";
    }
}
