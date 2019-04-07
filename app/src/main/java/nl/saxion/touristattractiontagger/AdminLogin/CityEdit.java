package nl.saxion.touristattractiontagger.AdminLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import nl.saxion.touristattractiontagger.Adapters.CityDisplayAdapter;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class CityEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_edit);

        TextView tvNotification = findViewById(R.id.tvNotification);
        ListView lvCities = findViewById(R.id.lvCities);
        Button addCity = findViewById(R.id.btnAddCity);
        CityDisplayAdapter adapter = new CityDisplayAdapter(this, DataProvider.CITIES);
        lvCities.setAdapter(adapter);
    }
}
