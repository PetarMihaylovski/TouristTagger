package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class OptionsScreen extends AppCompatActivity {
    private String username;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_sreen);

        Intent prevScreen = getIntent();
        this.username = prevScreen.getStringExtra(AttractionSelectScreen.NAME_KEY);
        String cityAsString = prevScreen.getStringExtra(AttractionSelectScreen.CITY_KEY);
        this.city = DataProvider.getCityByName(cityAsString);

    }
}
