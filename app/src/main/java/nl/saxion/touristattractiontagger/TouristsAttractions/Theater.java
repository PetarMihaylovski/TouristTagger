package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Theater extends TouristAttraction {
    private String play;

    public Theater(String name, String location, String play) {
        super(name, location, 'C');
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

    @Override
    public String toString() {
        return super.toString();
    }
}
