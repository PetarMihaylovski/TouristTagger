package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Museum extends TouristAttraction {
    private String exhibition;

    /**
     * Constructor.
     *
     * @param name       The name of the museum.
     * @param location   The street name and number of the museum.
     * @param exhibition The special exhibition in the museum.
     */
    public Museum(String name, String location, String exhibition) {
        super(name, location, 'D');
        this.exhibition = exhibition;
    }

    /**
     * Getters.
     *
     * @return The required value.
     */
    @Override
    public String getType() {
        return "Museum";
    }

    @Override
    public String getSpecialAttribute() {
        return this.exhibition;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
