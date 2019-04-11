package nl.saxion.touristattractiontagger.TouristsAttractions;

public class Museum extends TouristAttraction {
  private String exhibition;

    public Museum(String name, String location, String exhibition) {
        super(name, location, 'D');
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

    @Override
    public String toString() {
        return super.toString();
    }
}
