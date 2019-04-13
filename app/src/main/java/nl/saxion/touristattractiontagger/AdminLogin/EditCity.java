package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class EditCity extends AppCompatActivity {
    private EditText etCityName;
    private EditText etCountryName;
    private City chosenCity;

    /**
     * Sets the input fields with the current
     * name of the city and the current country name.
     *
     * @param savedInstanceState ???
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_city);

        //Getting the information from the parent activity.
        Intent intent = getIntent();
        etCityName = findViewById(R.id.etNameCity);
        etCountryName = findViewById(R.id.etCountryName);

        String cityName = intent.getStringExtra(CityDisplayAdmin.CITY_EDIT_NAME_KEY);
        this.chosenCity = DataProvider.getCityByName(cityName);
        this.etCityName.setText(cityName);
        etCountryName.setText(intent.getStringExtra(CityDisplayAdmin.CITY_EDIT_COUNTRY_KEY));
    }

    /**
     * Updating the information, by taking the values from
     * the input fields and setting them to the chosen city.
     */
    @Override
    public void onBackPressed() {
        //Getting the input fields.
        String name = this.etCityName.getText().toString();
        String country = this.etCountryName.getText().toString();

        //Changing the name of the city.
        if (name.equals("") || country.equals("")) {
            Toast.makeText(this, getString(R.string.invalidInfo), Toast.LENGTH_SHORT).show();
        }
        else {
            this.chosenCity.setName(name);
            this.chosenCity.setCountry(country);
            super.onBackPressed();
        }
    }
}
