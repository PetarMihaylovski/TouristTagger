package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Museum extends TouristAttraction {
  private String exhibition;

    //Default constructor, in order not to throw an error.
    public Museum(String name, String location, String exhibition) {
        super(name, location);
        this.exhibition = exhibition;
    }

    @Override
    public String getType() {
        return "Museum";
    }

    @Override
    public String getSpecialAttribute() {
        return this.exhibition;
    }
}
