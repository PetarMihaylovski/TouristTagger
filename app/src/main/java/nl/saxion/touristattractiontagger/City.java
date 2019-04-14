package nl.saxion.touristattractiontagger;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.Exceptions.SameTouristAttractionException;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class City implements Comparable<City> {
    private String name;
    private String country;
    private ArrayList<TouristAttraction> attractions;
    private String pictureID;

    /**
     * Constructor.
     *
     * @param name      The name of the city.
     * @param country   The name of the country.
     * @param pictureID The ID of the picture in the assets folder.
     */
    public City(String name, String country, String pictureID) {
        this.name = name;
        this.country = country;
        this.pictureID = pictureID;
        this.attractions = new ArrayList<>();
    }

    /**
     * Constructor without the picture variable.
     *
     * @param name    The name of the city.
     * @param country The name of the country
     */
    public City(String name, String country) {
        this.name = name;
        this.country = country;
        this.attractions = new ArrayList<>();
        this.pictureID = "picture.jpg";
    }

    /**
     * Adds a tourist attraction to the city's
     * already existing tourist attractions.
     *
     * @param attraction The tourist attraction to be added.
     * @throws SameTouristAttractionException if a city with the same name and special attribute exist.
     */
    public void addAttraction(TouristAttraction attraction) throws SameTouristAttractionException {
        for (TouristAttraction ta : this.attractions) {
            if (ta.getName().equals(attraction.getName()) && ta.getSpecialAttribute().equals(attraction.getSpecialAttribute())) {
                throw new SameTouristAttractionException();
            }
        }
        this.attractions.add(attraction);
    }

    /**
     * Removing a tourist attraction.
     *
     * @param name The name of the tourist attraction.
     * @return True if successfully removed, false otherwise.
     */
    public boolean removeAttraction(String name) {
        for (TouristAttraction ta : this.attractions) {
            if (ta.getName().toLowerCase().equals(name.toLowerCase())) {
                this.attractions.remove(ta);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a tourist attraction by name.
     *
     * @param name The name of the tourist attraction.
     * @return the tourist attraction if found, null otherwise.
     */
    public TouristAttraction findAttrByName(String name) {
        for (TouristAttraction ta : this.attractions) {
            if (ta.getName().toLowerCase().equals(name.toLowerCase())) {
                return ta;
            }
        }
        return null;
    }

    /**
     * Gives all the city's tourist attractions.
     *
     * @return A copy of the original arrayList.
     */
    public ArrayList<TouristAttraction> getAttractions() {
        ArrayList<TouristAttraction> attractions = this.attractions;
        return attractions;
    }

    /**
     * Getters and setters.
     *
     * @return The required value.
     */
    public String getPictureID() {
        return pictureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name + ", " + country;
    }

    /**
     * Sorts the cities alphabetically
     * by county's name.
     *
     * @param city The city we compare with.
     * @return An integer indicating which city is alphabetically greater.
     */
    @Override
    public int compareTo(City city) {
        return this.country.compareTo(city.getCountry());
    }
}
