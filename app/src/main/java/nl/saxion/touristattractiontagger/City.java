package nl.saxion.touristattractiontagger;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class City implements Comparable<City>{
    private String name;
    private String country;
    private ArrayList<TouristAttraction> attractions;
    private String pictureID;

    public City(String name, String country, String pictureID) {
        this.name = name;
        this.country = country;
        this.pictureID = pictureID;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(TouristAttraction attraction){
        this.attractions.add(attraction);
    }

    public boolean removeAttraction(String name){
        for (TouristAttraction ta : this.attractions) {
            if (ta.getName().toLowerCase().equals(name.toLowerCase())){
                this.attractions.remove(ta);
                return true;
            }
        }
        return false;
    }

    public TouristAttraction findAttrByName(String name){
        for (TouristAttraction ta : this.attractions) {
            if (ta.getName().toLowerCase().equals(name.toLowerCase())){
                return ta;
            }
        }
        return null;
    }

    //return copy of the real arrayList.
    public ArrayList<TouristAttraction> getAttractions(){
        ArrayList<TouristAttraction> attractions = this.attractions;
        return attractions;
    }

    public String getPictureID() {
        return pictureID;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name + ", " + country;
    }

    @Override
    public int compareTo(City city) {
        return this.country.compareTo(city.getCountry());
    }
}
