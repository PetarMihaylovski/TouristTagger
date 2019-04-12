package nl.saxion.touristattractiontagger.TouristsAttractions;

public abstract class TouristAttraction implements Comparable<TouristAttraction> {
    private String name;
    private String location;
    private boolean checked; //A variable needed for the checkbox.
    private char order; //A value needed for the sorting.

    /**
     * Constructor.
     *
     * @param name     The name of the tourist attraction.
     * @param location The street name and number of the tourist attraction.
     * @param order    The order the arrayList is sorted.
     */
    public TouristAttraction(String name, String location, char order) {
        this.name = name;
        this.location = location;
        this.checked = false;
        this.order = order;
    }

    /**
     * Getters and setters.
     *
     * @return The required data.
     */
    private char getOrder() {
        return this.order;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public abstract String getType();

    public abstract String getSpecialAttribute();

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    /**
     * The custom way the tourist attractions are sorted.
     * When a new tourist attraction is created it goes on the bottom of the list.
     * When sorted, when a new bar is created, it goes on the bottom of the existing
     * bars, not on the bottom of the whole arrayList.
     *
     * @param ta The tourist attraction we are comparing with.
     * @return -1 if the ta is bigger than the current tourist attraction,
     * 1 if the current tourist attraction is bigger than ta.
     * 0 if they are equal.
     */
    public int compareTo(TouristAttraction ta) {
        if (this.getOrder() < ta.getOrder()) {
            return -1;
        }
        else if (this.getOrder() > ta.getOrder()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return getName() + " (" + getType() + ")\n";
    }
}
