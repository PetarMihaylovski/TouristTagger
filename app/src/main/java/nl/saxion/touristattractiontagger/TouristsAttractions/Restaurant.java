package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Restaurant extends TouristAttraction {
    private String specialDish;
    //Default constructor, in order not to throw an error.
    public Restaurant(String name, String location, String specialDish) {
        super(name, location);
        this.specialDish = specialDish;
    }

    public String getSpecialDish() {
        return specialDish;
    }

    @Override
    public String getSpecialAttribute() {
        return specialDish;
    }

    @Override
    public String getType() {
        return "Restaurant";
    }
}
