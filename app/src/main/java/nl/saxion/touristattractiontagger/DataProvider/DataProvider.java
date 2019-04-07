package nl.saxion.touristattractiontagger.DataProvider;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.Exceptions.SameCityException;
import nl.saxion.touristattractiontagger.TouristsAttractions.Bar;
import nl.saxion.touristattractiontagger.TouristsAttractions.Museum;
import nl.saxion.touristattractiontagger.TouristsAttractions.Restaurant;
import nl.saxion.touristattractiontagger.TouristsAttractions.Theater;
import nl.saxion.touristattractiontagger.Users.Administrator;
import nl.saxion.touristattractiontagger.Users.BasicUser;
import nl.saxion.touristattractiontagger.Users.User;

public class DataProvider {
    public static ArrayList<BasicUser> USERS;
    public static ArrayList<City> CITIES;
    public static ArrayList<String> DATA_DISPLAY;
    public static Administrator ADMIN;

    static {
        USERS = new ArrayList<>();
        CITIES = new ArrayList<>();
        DATA_DISPLAY = new ArrayList<>();

        City amsterdam = new City("Amsterdam", "The Netherlands", "amsterdam_picture.jpg");
        City deventer = new City("Deventer", "The Netherlands", "deventer_picture.jpg");
        City sofia = new City("Sofia", "Bulgaria", "sofia_picture.jpg");
        City plovdiv = new City("Plovdiv", "Bulgaria", "plovdiv_picture.jpeg");

        try {
            //mass-added since they are the starting cities and cannot have duplicates.
            addCity(amsterdam);
            addCity(deventer);
            addCity(sofia);
            addCity(plovdiv);
        }
        catch (SameCityException sce) {
            sce.printStackTrace();
        }

        //TODO: add tourist attractions in the city here.
        amsterdam.addAttraction(new Bar("Door 74", "Reguliersdwarsstraat 74", "Bloody Marry"));
        amsterdam.addAttraction(new Bar("Brouwerij 't IJ", "Funenkade 7", "Cuba Libre"));
        amsterdam.addAttraction(new Bar("Hiding In Plain Sight", "Rapenburg 18", "Pina Colada"));
        amsterdam.addAttraction(new Restaurant("Restaurant Zaza's", "Daniël Stalpertstraat 103", "Fillet of COD"));
        amsterdam.addAttraction(new Restaurant("Ciel Bleu Restaurant", "Ferdinand Bolstraat 333", "Smoked Eel"));
        amsterdam.addAttraction(new Restaurant("Restaurant La Rive", "Professor Tulpplein 1", "Carabineros Prawn"));
        amsterdam.addAttraction(new Theater("Internationaal Theater", "Leidseplein 26", "All hands on deck"));
        amsterdam.addAttraction(new Theater("Royal Theater Carré", "Amstel 115 /125", "A little night music"));
        amsterdam.addAttraction(new Theater("DeLaMar", "Marnixstraat 402", "Call it love"));
        amsterdam.addAttraction(new Museum("Rijksmuseum", "Museumstraat 1", "Johannes Vermeer"));
        amsterdam.addAttraction(new Museum("Anne Frank House", "Prinsengracht 263-267", "The Secret Annex"));
        amsterdam.addAttraction(new Museum("Van Gogh Museum", "Museumplein 6", "Van Gogh Inspires"));

        ADMIN = new Administrator("admin", "admin");
    }

    /**
     * Adds a new city to the already existing list.
     * Does not allow same city additions.
     * @param city the city as an object
     * @throws SameCityException when the user tries to add the same city twice.
     */
    public static void addCity(City city) throws SameCityException {
       for (City c : CITIES) {
            if (c.getName().equals(city.getName()) && c.getCountry().equals(city.getCountry())){
                throw new SameCityException();
            }
        }

        CITIES.add(city);
    }

    public static City getCityByName(String name) {
        for (City city : CITIES) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    //TODO: make necessary checks
    public static void addData(String data){
        DATA_DISPLAY.add(data);
    }

    //TODO: make the necessary checks
    public static void addUser(String name, City city){
        USERS.add(new BasicUser(name, city));
    }

    public static User getUserByName(String name){
        for (User user : USERS){
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }
}
