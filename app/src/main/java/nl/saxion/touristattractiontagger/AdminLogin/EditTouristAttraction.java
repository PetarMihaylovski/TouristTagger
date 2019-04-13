package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class EditTouristAttraction extends AppCompatActivity {

    private EditText etName;
    private EditText etSpecialAttribute;
    private EditText etLocation;
    private TouristAttraction ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tourist_attraction);

        //Assigning the views.
        this.etName = findViewById(R.id.etNameTA);
        this.etSpecialAttribute = findViewById(R.id.etSpecAttrTA);
        this.etLocation = findViewById(R.id.etLocationTA);

        //Taking the data from the parent activity.
        Intent intent = getIntent();
        String cityName = intent.getStringExtra(TouristAttractionDetails.CITY_NAME_KEY);
        City chosenCity = DataProvider.getCityByName(cityName);
        String taName = intent.getStringExtra(TouristAttractionDetails.TA_NAME);
        this.ta = chosenCity.findAttrByName(taName);

        //Setting the text.
        this.etName.setText(taName);
        this.etSpecialAttribute.setText(intent.getStringExtra(TouristAttractionDetails.TA_SPEC_ATTRIBUTE));
        this.etLocation.setText(intent.getStringExtra(TouristAttractionDetails.TA_LOCATION));
    }

    @Override
    public void onBackPressed() {
        //Getting the values from the input fields.
        String name = this.etName.getText().toString();
        String location = this.etLocation.getText().toString();
        String specialAttribute = this.etSpecialAttribute.getText().toString();

        //Checking if the user has entered invalid information.
        if (name.equals("") || location.equals("") || specialAttribute.equals("")) {
            Toast.makeText(this, getString(R.string.invalidInfo), Toast.LENGTH_SHORT).show();
        }
        else {
            //Changing the text.
            this.ta.setName(name);
            this.ta.setLocation(location);
            this.ta.setSpecialAttribute(specialAttribute);
            super.onBackPressed();
        }
    }
}
