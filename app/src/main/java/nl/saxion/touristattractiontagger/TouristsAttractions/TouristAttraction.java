package nl.saxion.touristattractiontagger.TouristsAttractions;

public abstract class TouristAttraction implements Comparable<TouristAttraction> {
    private String name;
    private String location;
    private boolean checked; //a variable needed for the checkbox.
    private char order;

    public TouristAttraction(String name, String location, char order) {
        this.name = name;
        this.location = location;
        this.checked = false;
        this.order = order;
    }

    private char getOrder() {
        return this.order;
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

    public int compareTo(TouristAttraction ta) {
        if (this.getOrder() < ta.getOrder()) {
            return -1;
        }
        else if (this.getOrder()>ta.getOrder()){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return getName() + " (" + getType() + ")\n";
    }
}
