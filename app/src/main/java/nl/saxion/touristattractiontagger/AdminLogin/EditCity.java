package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import nl.saxion.touristattractiontagger.Adapters.CityDisplayAdapter;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class EditCity extends AppCompatActivity {
    private Button btnAddCity;
    private Button btnRemoveCity;
    public static final int REQUEST_CODE = 123;
    private CityDisplayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_edit);

        this.btnAddCity = findViewById(R.id.btnAddCity);
        this.btnRemoveCity = findViewById(R.id.btnRemoveCity);
        //creating the list view and adding an adapter.
        ListView lvCities = findViewById(R.id.lvCities);
        this.adapter = new CityDisplayAdapter(this, DataProvider.CITIES);
        lvCities.setAdapter(adapter);

        addCityOnClickListener();
    }

    private void removeCityOnClick (){
        this.btnRemoveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addCityOnClickListener() {
        this.btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(EditCity.this, AddCity.class);
                startActivityForResult(switchScreen, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            adapter.notifyDataSetChanged();
        }
    }
}

