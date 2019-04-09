package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import nl.saxion.touristattractiontagger.Adapters.AdminAttractionDisplayAdapter;
import nl.saxion.touristattractiontagger.Adapters.AttractionDisplayAdapter;
import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.NormalLoginActivities.AttractionSelectScreen;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class TouristAttractionDetails extends AppCompatActivity {

    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_details);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra(EditCity.CITY_TRANSFER_KEY);
        this.city = DataProvider.getCityByName(cityName);
        ArrayList<TouristAttraction> touristAttractions = city.getAttractions();

        TextView tvChosenCity = findViewById(R.id.tvChosenCity);
        tvChosenCity.setText(city.toString());

        AdminAttractionDisplayAdapter adapter = new AdminAttractionDisplayAdapter(this, this.city.getAttractions());
        ListView lvAttractionsDisplay = findViewById(R.id.lvTouristAttrAdminDisplay);
        lvAttractionsDisplay.setAdapter(adapter);
    }
}
