package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.Exceptions.SameCityException;
import nl.saxion.touristattractiontagger.R;

public class MakeNewCity extends AppCompatActivity {

    private EditText etCityName;
    private EditText etCountryName;
    private EditText etPictureName;
    private Button btnAdd;
    private String cityName;
    public static final String CITY_NAME_KEY = "the city key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        this.etCityName = findViewById(R.id.etCityToBeDeleted);
        this.etCountryName = findViewById(R.id.etCountryName);
        this.etPictureName= findViewById(R.id.etPictureName);
        this.btnAdd = findViewById(R.id.btnAddCity);

        this.etCityName.setText("");
        this.etCountryName.setText("");
        this.etPictureName.setText("");
        this.cityName = this.etCityName.getText().toString();

        addCityOnClickListener();
    }

    private void addCityOnClickListener(){
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = etCityName.getText().toString();
                String countryName = etCountryName.getText().toString();
                String pictureID = etPictureName.getText().toString();

                if (cityName.equals("") || countryName.equals("")){
                    Toast.makeText(MakeNewCity.this, "Invalid information! City cannot be added.", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        DataProvider.addCity(new City(cityName, countryName,pictureID));
                        Toast.makeText(MakeNewCity.this, "City added successfully. Press the back button to exit.", Toast.LENGTH_LONG).show();
                    }
                    catch (SameCityException sce) {
                        Toast.makeText(MakeNewCity.this, "" + sce, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //empty intent, since it is only a message carrier.
        Intent messageCarrier = new Intent();
        messageCarrier.putExtra(CITY_NAME_KEY, this.cityName);
        setResult(RESULT_OK, messageCarrier);
        super.onBackPressed();
    }
}
