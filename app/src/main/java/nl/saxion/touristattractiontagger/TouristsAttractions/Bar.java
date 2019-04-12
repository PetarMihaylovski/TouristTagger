package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Bar extends TouristAttraction {
    private String specialCocktail; //The special attribute.

    /**
     * Constructor.
     *
     * @param name            The name of the bar.
     * @param location        The street name and number of the bar.
     * @param specialCocktail The special drink in the bar.
     */
    public Bar(String name, String location, String specialCocktail) {
        super(name, location, 'A');
        this.specialCocktail = specialCocktail;
    }

    /**
     * Getters.
     *
     * @return the required value.
     */
    @Override
    public String getSpecialAttribute() {
        return specialCocktail;
    }

    @Override
    public String getType() {
        return "Bar";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
