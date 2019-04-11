package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Restaurant extends TouristAttraction {
    private String specialDish;

    public Restaurant(String name, String location, String specialDish) {
        super(name, location, 'B');
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

    @Override
    public String toString() {
        return super.toString();
    }
}

