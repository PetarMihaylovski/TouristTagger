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
    private Button btnCreate;
    private String cityName;
    //Key for data transfer.
    public static final String CITY_NAME_KEY = "the city key";

    /**
     * The method called, whenever the activity is started.
     * Assigning the views from the .xml file and setting the values.
     *
     * @param savedInstanceState ??
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        //Assigning the views.
        this.etCityName = findViewById(R.id.etCityToBeCreated);
        this.etCountryName = findViewById(R.id.etCountryName);
        this.btnCreate = findViewById(R.id.btnAddCity);

        //Setting the values.
        this.etCityName.setText("");
        this.etCountryName.setText("");
        this.cityName = this.etCityName.getText().toString();

        //On click listener.
        createCityOnClickListener();
    }

    /**
     * On click listener for the city create button.
     * When called, it checks if the information entered
     * is valid and creates a new city instance.
     */
    private void createCityOnClickListener() {
        this.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting the text from the input fields.
                cityName = etCityName.getText().toString();
                String countryName = etCountryName.getText().toString();

                //Checks to see if the information entered is correct.
                if (cityName.equals("") || countryName.equals("")) {
                    Toast.makeText(MakeNewCity.this, getString(R.string.notifCannotAddCity), Toast.LENGTH_SHORT).show();
                }
                else {
                    //Throws exception if the the user is trying to create already existing city.
                    try {
                        //Successfully created the new city.
                        DataProvider.addCity(new City(cityName, countryName));
                        Toast.makeText(MakeNewCity.this, getString(R.string.notifCityAdded), Toast.LENGTH_LONG).show();
                    }
                    catch (SameCityException sce) {
                        //Duplicate city
                        Toast.makeText(MakeNewCity.this, "" + sce, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * Transfers the information back to
     * the parent activity.
     */
    @Override
    public void onBackPressed() {
        //Empty intent, since it is only a message carrier.
        Intent messageCarrier = new Intent();
        messageCarrier.putExtra(CITY_NAME_KEY, this.cityName);
        setResult(RESULT_OK, messageCarrier);
        super.onBackPressed();
    }
}
