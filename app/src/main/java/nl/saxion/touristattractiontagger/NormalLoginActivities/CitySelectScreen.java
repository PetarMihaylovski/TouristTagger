package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.Adapters.CityDisplayAdapter;
import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.Views.ProgressBar;

public class CitySelectScreen extends AppCompatActivity {
    private static ArrayList<City> cities;
    private EditText nameInput;
    private String selectedCity;
    private ListView listView;
    //Keys for data transfer.
    public static final String NAME_KEY = "name transfer";
    public static final String CITY_KEY = "city transfer";

    /**
     * Assigning the views.
     * Displaying the information.
     * @param savedInstanceState ??
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_select_screen);

        //Assigning the views.
        this.nameInput = findViewById(R.id.etNameInput);
        this.listView = findViewById(R.id.lvCitiesDisplay);

        //Setting the text.
        this.nameInput.setText("");

        //Getting the arrayList of cities.
        cities = DataProvider.CITIES;

        //Setting up a progress bar.
        ProgressBar progressBar = findViewById(R.id.pb1);
        progressBar.setValue(1);

        //Instantiating an adapter for the listView.
        CityDisplayAdapter adapter = new CityDisplayAdapter(this, cities);
        this.listView.setAdapter(adapter);

        //On click listener.
        citiesOnItemClickListener();
    }

    /**
     * On item click listener for the items in the list.
     */
    private void citiesOnItemClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Check to see if the user has entered his name.
                if (nameInput.getText().toString().equals("")){
                    Toast.makeText(CitySelectScreen.this, getString(R.string.enterNamePrompt), Toast.LENGTH_SHORT).show();
                }
                else{
                    //Getting the name from the input.
                    String userName = nameInput.getText().toString();
                    selectedCity = cities.get(position).getName();
                    //Creating a new user instance.
                    DataProvider.addUser(userName, cities.get(position));

                    //Switching activities.
                    //Sending the user, who is choosing the locations.
                    //Sending the selected city.
                    Intent switchScreen = new Intent(CitySelectScreen.this, AttractionSelectScreen.class);
                    switchScreen.putExtra(NAME_KEY, userName);
                    switchScreen.putExtra(CITY_KEY, selectedCity);
                    startActivity(switchScreen);
                }
            }
        });
    }
}
