package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.Adapters.AttractionDisplayAdapter;
import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;
import nl.saxion.touristattractiontagger.Users.BasicUser;
import nl.saxion.touristattractiontagger.Views.ProgressBar;

public class AttractionSelectScreen extends AppCompatActivity {
    private ListView listView;
    private City chosenCity; //the chosenCity
    private BasicUser user; //the user
    private ArrayList<TouristAttraction> allAttractions;
    public static final String NAME_KEY = "randomKeyGen";
    public static final String CITY_KEY = "cityKeyGenerated";
    public static final int CONFIRMATION_CODE = 126;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_select_screen);

        TextView tvNameAndLocation = findViewById(R.id.tvNameAndLocation);
        ProgressBar progressBar = findViewById(R.id.pb2);
        progressBar.setValue(2);

        //Get the username and the chosen chosenCity from the previous screen.
        Intent intent = getIntent();
        String usernameAsString = intent.getStringExtra(CitySelectScreen.NAME_KEY);
        this.user = (BasicUser) DataProvider.getUserByName(usernameAsString);
        String cityName = intent.getStringExtra(CitySelectScreen.CITY_KEY);
        this.chosenCity = DataProvider.getCityByName(cityName);

        try {
            //Link the chosenCity with its arrayList of allAttractions.
            this.allAttractions = chosenCity.getAttractions();
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        //Display the username and the chosenCity.
        tvNameAndLocation.setText(String.format(getString(R.string.locationDisplay), this.user.getName(), chosenCity.getName()));

        AttractionDisplayAdapter adapter = new AttractionDisplayAdapter(this, this.allAttractions, this.user);
        this.listView = findViewById(R.id.lvAttractionsDisplay);
        this.listView.setAdapter(adapter);

        locationDisplayOnClickListener();
        addAttractionsOnClickListener();
    }

    private void locationDisplayOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(AttractionSelectScreen.this, allAttractions.get(position).getLocation() + ", " + chosenCity, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addAttractionsOnClickListener(){
        Button addPlacesButton = findViewById(R.id.addPlacesButton);
        addPlacesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(AttractionSelectScreen.this, OptionsScreen.class);
                switchScreen.putExtra(NAME_KEY, user.toString());
                switchScreen.putExtra(CITY_KEY, chosenCity.getName());
                startActivityForResult(switchScreen, CONFIRMATION_CODE);
            }
        });
    }
}

