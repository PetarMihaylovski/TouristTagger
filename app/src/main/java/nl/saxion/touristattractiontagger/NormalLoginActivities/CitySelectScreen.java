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

public class CitySelectScreen extends AppCompatActivity {
    private static ArrayList<City> cities;
    private EditText nameInput;
    public static final String NAME_KEY = "name transfer";
    public static final String CITY_KEY = "city transfer";
    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_select_screen);

        cities = DataProvider.CITIES;
        this.nameInput = findViewById(R.id.etNameInput);
        this.nameInput.setText("");
        final String userName = nameInput.getText().toString();

        //Instantiating the list view with adapter.
        CityDisplayAdapter adapter = new CityDisplayAdapter(this, cities);
        ListView listView = findViewById(R.id.lvCitiesDisplay);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Check to see if the user has entered his name.
                if (nameInput.getText().toString().equals("")){
                    Toast.makeText(CitySelectScreen.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent switchScreen = new Intent(CitySelectScreen.this, AttractionSelectScreen.class);
                    selectedCity = cities.get(position).getName();
                    //creating a new user instance.
                    DataProvider.addUser(userName, "", cities.get(position));
                    switchScreen.putExtra(NAME_KEY, userName);
                    switchScreen.putExtra(CITY_KEY, selectedCity);
                    startActivity(switchScreen);
                }
            }
        });
    }
}
