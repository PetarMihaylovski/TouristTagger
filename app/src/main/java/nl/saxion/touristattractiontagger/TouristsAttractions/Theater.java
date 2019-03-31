package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Theater extends TouristAttraction {
    private String play;
    //Default constructor, in order not to throw an error.
    public Theater(String name, String location, String play) {
        super(name, location);
        this.play = play;
    }

    @Override
    public String getType() {
        return "Theater";
    }

    @Override
    public String getSpecialAttribute() {
        return this.play;
    }
}
