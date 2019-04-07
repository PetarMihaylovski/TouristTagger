package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import nl.saxion.touristattractiontagger.Adapters.CityDisplayAdapter;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class CityEdit extends AppCompatActivity {
    private Button btnAddCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_edit);

        btnAddCity = findViewById(R.id.btnAddCity);
        //creating the list view and adding an adapter.
        ListView lvCities = findViewById(R.id.lvCities);
        CityDisplayAdapter adapter = new CityDisplayAdapter(this, DataProvider.CITIES);
        lvCities.setAdapter(adapter);
        addCityOnClickListener();
    }

    private void addCityOnClickListener(){
        this.btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(CityEdit.this, AddCity.class);
                startActivity(switchScreen);
            }
        });
    }
}


