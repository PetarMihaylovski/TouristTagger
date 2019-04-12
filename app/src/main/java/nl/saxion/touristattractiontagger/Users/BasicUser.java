package nl.saxion.touristattractiontagger.Users;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class BasicUser extends User {
    private ArrayList<TouristAttraction> visitedVenues; //The visited tourist attractions.
    private City city; //The visited city.

    /**
     * Constructor.
     *
     * @param name The name of the user.
     * @param city The visited city.
     */
    public BasicUser(String name, City city) {
        super(name);
        this.city = city;
        this.visitedVenues = new ArrayList<>();
    }

    /**
     * Adding a new tourist attraction to the already visited.
     *
     * @param attraction The attractions the user wants to add.
     */
    public void addVisitedVenue(TouristAttraction attraction) {
        this.visitedVenues.add(attraction);
    }

    /**
     * Getter for the visited tourist attractions.
     *
     * @return A copy of the visited tourist attractions.
     */
    public ArrayList<TouristAttraction> getVisitedVenues() {
        ArrayList<TouristAttraction> visitedVenuesCopy = new ArrayList<>(visitedVenues);
        return visitedVenuesCopy;
    }

    /**
     * Removes a tourist attraction from the visited attractions.
     *
     * @param attraction The attraction to be removed.
     */
    public void removeVisitedVenue(TouristAttraction attraction) {
        this.visitedVenues.remove(attraction);
    }

    /**
     * Getter and setter.
     *
     * @return the required value.
     */
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
