package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Bar extends TouristAttraction {
    private String specialCocktail;

    //Default constructor, in order not to throw an error.
    public Bar(String name, String location, String specialCocktail) {
        super(name, location);
        this.specialCocktail = specialCocktail;
    }

    @Override
    public String getSpecialAttribute() {
        return specialCocktail;
    }

    @Override
    public String getType() {
        return "Bar";
    }
}
