package nl.saxion.touristattractiontagger;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class City {
    private String name;
    private String country;
    private ArrayList<TouristAttraction> attractions;
    private static String pictureID;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(TouristAttraction attraction){
        this.attractions.add(attraction);
    }

    //return copy of the real arrayList.
    public ArrayList<TouristAttraction> getAttractions(){
        ArrayList<TouristAttraction> attractions = this.attractions;
        return attractions;
    }

    public static String getPictureID() {
        return pictureID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + country;
    }
}
