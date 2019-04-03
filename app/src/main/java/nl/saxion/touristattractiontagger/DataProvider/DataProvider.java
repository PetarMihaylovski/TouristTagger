package nl.saxion.touristattractiontagger.DataProvider;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.TouristsAttractions.Bar;
import nl.saxion.touristattractiontagger.TouristsAttractions.Museum;
import nl.saxion.touristattractiontagger.TouristsAttractions.Restaurant;
import nl.saxion.touristattractiontagger.TouristsAttractions.Theater;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;
import nl.saxion.touristattractiontagger.Users.Administrator;
import nl.saxion.touristattractiontagger.Users.BasicUser;
import nl.saxion.touristattractiontagger.Users.User;

public class DataProvider {
    public static ArrayList<User> USERS;
    public static ArrayList<City> CITIES;
    public static ArrayList<TouristAttraction> TEMP_VISITED_PLACES; //TODO: rename with a better name
    public static ArrayList<TouristAttraction> PERMANENT_VISITED_PLACES; //TODO: rename with a better name

    static {
        USERS = new ArrayList<>();
        CITIES = new ArrayList<>();
        TEMP_VISITED_PLACES = new ArrayList<>();
        PERMANENT_VISITED_PLACES = new ArrayList<>();

        City amsterdam = new City("Amsterdam", "The Netherlands", "amsterdam_picture.jpg");
        City deventer = new City("Deventer", "The Netherlands", "deventer_picture.jpg");
        City sofia = new City("Sofia", "Bulgaria", "sofia_picture.jpg");
        City plovdiv = new City("Plovdiv", "Bulgaria", "plovdiv_picture.jpeg");

        addCity(amsterdam);
        addCity(deventer);
        addCity(sofia);
        addCity(plovdiv);

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
    }

    //TODO: When needed make it public.
    //TODO: Check if the city already exist in the list.
    private static void addCity(City city) {
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

    public static void addUser(String name, String password, City city){
        if (password.equals("")){
            USERS.add(new BasicUser(name, city));
        }
        else {
            USERS.add(new Administrator(name,password));
        }

    }

    public static User getUserByName(String name){
        for (User user : USERS){
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    //TODO: check if the TA is already in the list.
    public static void addVisitedTouristAttraction(TouristAttraction visited) {
        TEMP_VISITED_PLACES.add(visited);
    }

    public static void removeTouristAttraction(TouristAttraction touristAttraction) {
        TEMP_VISITED_PLACES.remove(touristAttraction);
    }

    /**
     * Transfer data between two arrayLists
     *
     * @param from the arrayList to be deleted
     * @param to   the arrayList to be overwritten
     */
    public static void dataTransfer(ArrayList<TouristAttraction> from, ArrayList<TouristAttraction> to) {
        to.addAll(from);
        from.clear();
    }
}
