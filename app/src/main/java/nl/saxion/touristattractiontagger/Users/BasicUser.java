package nl.saxion.touristattractiontagger.Users;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class BasicUser extends User{
    private ArrayList<TouristAttraction> visitedVenues;
    private City city;

    public BasicUser(String name, City city){
        super(name);
        this.city = city;
        this.visitedVenues = new ArrayList<>();
    }

    public void addVisitedVenue(TouristAttraction attraction){
        this.visitedVenues.add(attraction);
    }

    public ArrayList<TouristAttraction> getVisitedVenues() {
        ArrayList<TouristAttraction> visitedVenuesCopy = new ArrayList<>(visitedVenues);
        return visitedVenuesCopy;
    }

    public void removeVisitedVenue(TouristAttraction attraction){
        this.visitedVenues.remove(attraction);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
