package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.Bar;
import nl.saxion.touristattractiontagger.TouristsAttractions.Museum;
import nl.saxion.touristattractiontagger.TouristsAttractions.Restaurant;
import nl.saxion.touristattractiontagger.TouristsAttractions.Theater;

public class MakeNewTouristAttraction extends AppCompatActivity {
    //TODO: rename all city instances to chosenCity!
    private City chosenCity;
    private EditText etType;
    private EditText etName;
    private EditText etLocation;
    private EditText etSpecialAttribute;
    private Button btnAddAttraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_tourist_attraction);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra(TouristAttractionDetails.CITY_NAME_KEY);
        this.chosenCity = DataProvider.getCityByName(cityName);

        this.etType = findViewById(R.id.etType);
        this.etName = findViewById(R.id.etName);
        this.etLocation = findViewById(R.id.etLocation);
        this.etSpecialAttribute = findViewById(R.id.etSpecialAttribute);
        this.btnAddAttraction = findViewById(R.id.btnCreateAttraction);

        typeChangeListener();
        createAttractionOnClickListener();
    }

    private void createAttractionOnClickListener() {
        this.btnAddAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = etType.getText().toString().toLowerCase();
                String name = etName.getText().toString();
                String location = etLocation.getText().toString();
                String specialAttribute = etSpecialAttribute.getText().toString();

                if (!(type.equals("") || name.equals("") || location.equals("") || specialAttribute.equals(""))) {
                    switch (type) {
                        case "bar":
                            chosenCity.addAttraction(new Bar(name, location, specialAttribute));
                            break;
                        case "restaurant":
                            chosenCity.addAttraction(new Restaurant(name, location, specialAttribute));
                            break;
                        case "museum":
                            chosenCity.addAttraction(new Museum(name, location, specialAttribute));
                            break;
                        case "theater":
                            chosenCity.addAttraction(new Theater(name, location, specialAttribute));
                            break;
                    }
                    Toast.makeText(MakeNewTouristAttraction.this, "Attraction added successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void typeChangeListener() {
        this.etType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String type = s.toString().toLowerCase();

                switch (type) {
                    case "bar":
                        etSpecialAttribute.setHint("Special cocktail: ");
                        break;
                    case "restaurant":
                        etSpecialAttribute.setHint("Special dish: ");
                        break;
                    case "museum":
                        etSpecialAttribute.setHint("Exhibition: ");
                        break;
                    case "theater":
                        etSpecialAttribute.setHint("Play: ");
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //empty intent, since it is only a message carrier.
        Intent messageCarrier = new Intent();
        messageCarrier.putExtra(TouristAttractionDetails.CITY_NAME_KEY, this.etName.getText().toString());
        setResult(RESULT_OK, messageCarrier);
        super.onBackPressed();
    }
}
