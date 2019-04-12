package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Theater extends TouristAttraction {
    private String play;

    /**
     * Constructor.
     *
     * @param name     The name of the theater.
     * @param location The street name and number of the theater.
     * @param play     The special play in the theater..
     */
    public Theater(String name, String location, String play) {
        super(name, location, 'C');
        this.play = play;
    }

    /**
     * Getters.
     * @return The required value.
     */
    @Override
    public String getType() {
        return "Theater";
    }

    @Override
    public String getSpecialAttribute() {
        return this.play;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
