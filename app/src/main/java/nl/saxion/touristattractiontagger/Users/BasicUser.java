package nl.saxion.touristattractiontagger.Users;

import nl.saxion.touristattractiontagger.City;

public class BasicUser extends User{
    private City visitedCity;

    public BasicUser(String name,City city){
        super(name);
        this.visitedCity = city;
    }

    public City getVisitedCity() {
        return visitedCity;
    }

    public void setVisitedCity(City visitedCity) {
        this.visitedCity = visitedCity;
    }
}
