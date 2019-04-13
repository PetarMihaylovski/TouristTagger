package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Restaurant extends TouristAttraction {
    private String specialDish;

    /**
     * Constructor.
     *
     * @param name        The name of the restaurnat.
     * @param location    The street name and number of the restaurant.
     * @param specialDish The special dish in the restaurant.
     */
    public Restaurant(String name, String location, String specialDish) {
        super(name, location, 'B');
        this.specialDish = specialDish;
    }

    /**
     * Getters.
     *
     * @return The required value.
     */
    @Override
    public String getSpecialAttribute() {
        return specialDish;
    }

    @Override
    public void setSpecialAttribute(String specialAttribute) {
        this.specialDish = specialAttribute;
    }

    @Override
    public String getType() {
        return "Restaurant";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

