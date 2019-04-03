package nl.saxion.touristattractiontagger.Users;

import nl.saxion.touristattractiontagger.City;

public class BasicUser {
    private City visitedCity;

    public BasicUser(City city){
        super();
        this.visitedCity = city;
    }

    public City getVisitedCity() {
        return visitedCity;
    }

    public void setVisitedCity(City visitedCity) {
        this.visitedCity = visitedCity;
    }
}
