package nl.saxion.touristattractiontagger.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.Exceptions.SameCityException;
import nl.saxion.touristattractiontagger.TouristsAttractions.Bar;
import nl.saxion.touristattractiontagger.TouristsAttractions.Museum;
import nl.saxion.touristattractiontagger.TouristsAttractions.Restaurant;
import nl.saxion.touristattractiontagger.TouristsAttractions.Theater;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;
import nl.saxion.touristattractiontagger.Users.Administrator;
import nl.saxion.touristattractiontagger.Users.BasicUser;
import nl.saxion.touristattractiontagger.Users.User;

public class DataProvider {
    private static ArrayList<BasicUser> USERS;
    public static ArrayList<City> CITIES;
    public static ArrayList<String> DATA_DISPLAY;
    public static Administrator ADMIN;
    public static HashMap<User, ArrayList<TouristAttraction>> DUMMY_DATA;

    static {
        USERS = new ArrayList<>();
        CITIES = new ArrayList<>();
        DATA_DISPLAY = new ArrayList<>();
        ADMIN = new Administrator("admin", "admin");
        DUMMY_DATA = new HashMap<>();

        City amsterdam = new City("Amsterdam", "Netherlands", "amsterdam_picture.jpg");
        City deventer = new City("Deventer", "Netherlands", "deventer_picture.jpg");
        City sofia = new City("Sofia", "Bulgaria", "sofia_picture.jpg");
        City plovdiv = new City("Plovdiv", "Bulgaria", "plovdiv_picture.jpeg");

        BasicUser firstDummy = new BasicUser("Petar", sofia);
        BasicUser secondDummy = new BasicUser("Simona", amsterdam);

        try {
            //mass-added since they are the starting cities and cannot have duplicates.
            addCity(sofia);
            addCity(plovdiv);
            addCity(amsterdam);
            addCity(deventer);
        }
        catch (SameCityException sce) {
            sce.printStackTrace();
        }

        amsterdam.addAttraction(new Bar("Door 74", "Reguliersdwarsstraat 74", "Bloody Marry"));
        amsterdam.addAttraction(new Bar("Brouwerij 't IJ", "Funenkade 7", "Cuba Libre"));
        amsterdam.addAttraction(new Bar("Hiding In Plain Sight", "Rapenburg 18", "Pina Colada"));
        amsterdam.addAttraction(new Restaurant("Restaurant Zaza's", "Daniël Stalpertstraat 103", "Fillet of COD"));
        amsterdam.addAttraction(new Restaurant("Ciel Bleu Restaurant", "Ferdinand Bolstraat 333", "Smoked Eel"));
        Restaurant restaurant = new Restaurant("Restaurant La Rive", "Professor Tulpplein 1", "Carabineros Prawn");
        amsterdam.addAttraction(restaurant);
        amsterdam.addAttraction(new Theater("Internationaal Theater", "Leidseplein 26", "All hands on deck"));
        amsterdam.addAttraction(new Theater("Royal Theater Carré", "Amstel 115 /125", "A little night music"));
        amsterdam.addAttraction(new Theater("DeLaMar", "Marnixstraat 402", "Call it love"));
        Museum rijksmuseum = new Museum("Rijksmuseum", "Museumstraat 1", "Johannes Vermeer");
        amsterdam.addAttraction(rijksmuseum);
        amsterdam.addAttraction(new Museum("Anne Frank House", "Prinsengracht 263-267", "The Secret Annex"));
        Museum vanGogh = new Museum("Van Gogh Museum", "Museumplein 6", "Van Gogh Inspires");
        amsterdam.addAttraction(vanGogh);

        deventer.addAttraction(new Bar("The Irish Elk", "Brink 29", "El Presidente"));
        deventer.addAttraction(new Bar("Jasmin's", "Brink 79", "Long Island Iced Tea"));
        deventer.addAttraction(new Bar("Walhalla", "Achter de Muren-Zandpoort 20", "Mind Eraser"));
        deventer.addAttraction(new Restaurant("'t Zusje", "Boreelplein 55", "Marinated spare-ribs"));
        deventer.addAttraction(new Restaurant("La Cubanita", "Brink 14", "Tapas"));
        deventer.addAttraction(new Restaurant("Pura Vida", "Grote Overstraat 36/40", "Carne"));
        deventer.addAttraction(new Theater("Shouwburg", "Leeuwenbrug 2", "Huub Stapel, Johana Ter Steege e.a"));
        deventer.addAttraction(new Museum("Toy Museumm", "Brink 47", "Peter Pan's Special"));
        deventer.addAttraction(new Museum("De Waag", "Brink 56", "Deventer, Stad van de IJssel"));
        deventer.addAttraction(new Museum("Geert Groote Huis", "Lamme van Dieseplein 4", "The life of Geert Groote"));

        sofia.addAttraction(new Bar("Barabar", "Triaditsa 4", "Dry Gin Martini"));
        Bar friday = new Bar("Friday", "General Yosif V. Gourko 21", "Whiskey Sour");
        sofia.addAttraction(friday);
        sofia.addAttraction(new Bar("Hambar", "6-ti septemvri 22", "Jack Rose"));
        sofia.addAttraction(new Restaurant("Chef's", "Lyubata", "Veal bon fillet steak"));
        Restaurant boom = new Restaurant("Boom Burgers", "Tsar Osvoboditel 12", "V Surf-N-Turf Burger");
        sofia.addAttraction(boom);
        sofia.addAttraction(new Restaurant("Happy Sushi Bar & Grill", "Georgi S. Rakovski 145", "Wabi-Sabi Combo"));
        Theater national = new Theater("\"Ivan Vazov\" National Theater", "Dyakon Ignatiy 5", "Ghosts");
        sofia.addAttraction(national);
        sofia.addAttraction(new Theater("Salza I Smyah", "Georgi S. Rakovski 127", "Frankenstein"));
        sofia.addAttraction(new Theater("Bulgarian Army Theatre", "Georgi S. Rakovski 98", "Tartuffe"));
        Museum museum = new Museum("National Historical Museum", "Vitoshko lale 16", "Earth's Tresures");
        sofia.addAttraction(museum);
        sofia.addAttraction(new Museum("Earth and Man", "Cherni vrah 4", "Stone's Poetry"));
        sofia.addAttraction(new Museum("National Art Gallery", "Knyaz Aleksandar I 1", "Van Gogh"));

        plovdiv.addAttraction(new Bar("Rock Bar Download", "Lady Strangford 5", "Screwdriver"));
        plovdiv.addAttraction(new Bar("Cat and Mouse Craft Beer Bar", "Hristo Dyukmedzhiev 14", "Presbyterian"));
        plovdiv.addAttraction(new Bar("Club Fargo", "Gladston 1", "Gin Gimlet"));
        plovdiv.addAttraction(new Restaurant("Pavaj", "Zlatarska 7", "French Fries"));
        plovdiv.addAttraction(new Restaurant("Hemingway", "General Gourko 10", "Smoked Duck Magret"));
        plovdiv.addAttraction(new Restaurant("Maramao", "Stefan Verkovich 8", "Beef Steak"));
        plovdiv.addAttraction(new Theater("Ancient Theater of Philippopolis", "Tsar Ivailo 4", "Cindarella"));
        plovdiv.addAttraction(new Theater("Drama Theatre", "Knyaz Alexander I 38", "Odyssey"));
        plovdiv.addAttraction(new Museum("Regional Historical Museum", "Mitropolit Paisiy 2", "Bulgarian National Revival"));
        plovdiv.addAttraction(new Museum("Regional Ethnographic Museum", "Doctor Stoyan Chomakov 2", "Clothing and textile"));
        plovdiv.addAttraction(new Museum("Aviation Museum", "????", " Different types of airplanes"));

        firstDummy.addVisitedVenue(friday);
        firstDummy.addVisitedVenue(boom);
        firstDummy.addVisitedVenue(national);
        firstDummy.addVisitedVenue(museum);
        DUMMY_DATA.put(firstDummy, firstDummy.getVisitedVenues());

        secondDummy.addVisitedVenue(rijksmuseum);
        secondDummy.addVisitedVenue(restaurant);
        secondDummy.addVisitedVenue(vanGogh);
        DUMMY_DATA.put(secondDummy, secondDummy.getVisitedVenues());
    }

    /**
     * Adds a new city to the already existing list.
     * Does not allow same city additions.
     *
     * @param city the city as an object
     * @throws SameCityException when the user tries to add the same city twice.
     */
    public static void addCity(City city) throws SameCityException {
        for (City c : CITIES) {
            if (c.getName().equals(city.getName()) && c.getCountry().equals(city.getCountry())) {
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

    public static void addData(String data) {
        DATA_DISPLAY.add(data);
    }

    public static void addUser(String name, City city) {
        USERS.add(new BasicUser(name, city));
    }

    public static User getUserByName(String name) {
        for (User user : USERS) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Finds a city by name and removes it
     *
     * @param name the name of the city, the user wants to delete
     * @return if the removal is successful or not.
     */
    public static boolean removeCityByName(String name) {
        for (City city : CITIES) {
            if (city.getName().toLowerCase().equals(name.toLowerCase())) {
                CITIES.remove(city);
                return true;
            }
        }
        return false;
    }
}
