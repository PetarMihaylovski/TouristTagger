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
    private City chosenCity;
    private EditText etType;
    private EditText etName;
    private EditText etLocation;
    private EditText etSpecialAttribute;
    private Button btnAddAttraction;

    /**
     * Getting the information from the previous activity.
     * Assigning the views from the .xml files and setting the values.
     *
     * @param savedInstanceState ??
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_tourist_attraction);

        //Getting the information from the previous activity.
        Intent intent = getIntent();
        String cityName = intent.getStringExtra(TouristAttractionDetails.CITY_NAME_KEY);
        this.chosenCity = DataProvider.getCityByName(cityName);

        //Assigning the views.
        this.etType = findViewById(R.id.etType);
        this.etName = findViewById(R.id.etName);
        this.etLocation = findViewById(R.id.etLocation);
        this.etSpecialAttribute = findViewById(R.id.etSpecialAttribute);
        this.btnAddAttraction = findViewById(R.id.btnCreateAttraction);

        //Setting the values.
        this.etType.setText("");
        this.etName.setText("");
        this.etLocation.setText("");
        this.etSpecialAttribute.setText("");

        //On click listeners.
        typeChangeListener();
        createAttractionOnClickListener();
    }

    /**
     * On click listener for the button
     * that creates the tourist attraction.
     * Checks the information entered by the user,
     * and creates the instance of tourist attraction.
     *
     */
    private void createAttractionOnClickListener() {
        this.btnAddAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting the values from the input fields.
                String type = etType.getText().toString().toLowerCase();
                String name = etName.getText().toString();
                String location = etLocation.getText().toString();
                String specialAttribute = etSpecialAttribute.getText().toString();

                //Checks if the information entered is correct.
                if (!(type.equals("") || name.equals("") || location.equals("") || specialAttribute.equals(""))) {
                    //Determining the type of attraction, the user is creating.
                    if ("bar".equals(type)) {
                        chosenCity.addAttraction(new Bar(name, location, specialAttribute));
                        Toast.makeText(MakeNewTouristAttraction.this, getString(R.string.notifAttrAdded), Toast.LENGTH_SHORT).show();
                    }
                    else if ("restaurant".equals(type)) {
                        chosenCity.addAttraction(new Restaurant(name, location, specialAttribute));
                        Toast.makeText(MakeNewTouristAttraction.this, getString(R.string.notifAttrAdded), Toast.LENGTH_SHORT).show();
                    }
                    else if ("museum".equals(type)) {
                        chosenCity.addAttraction(new Museum(name, location, specialAttribute));
                        Toast.makeText(MakeNewTouristAttraction.this, getString(R.string.notifAttrAdded), Toast.LENGTH_SHORT).show();
                    }
                    else if ("theater".equals(type)) {
                        chosenCity.addAttraction(new Theater(name, location, specialAttribute));
                        Toast.makeText(MakeNewTouristAttraction.this, getString(R.string.notifAttrAdded), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //The users has entered invalid type of tourist attraction.
                        Toast.makeText(MakeNewTouristAttraction.this, getString(R.string.wrongTypeAttr), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    //When the user enters wrong information, he/she is getting notified of it.
                    Toast.makeText(MakeNewTouristAttraction.this, getString(R.string.notValidAttraction), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * On text changed listener for
     * the type of attraction input field.
     * Determines the input and displays the
     * special attribute for the tourist attraction.
     *
     */
    private void typeChangeListener() {
        this.etType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Auto-generated method stub.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Auto-generated method stub.
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Getting the inputted text
                String type = s.toString().toLowerCase();
                //Checks if the format is correct.
                if (!type.equals("")){
                    //Determines what to display.
                    switch (type) {
                        case "bar":
                            etSpecialAttribute.setHint(getString(R.string.specialCocktail));
                            break;
                        case "restaurant":
                            etSpecialAttribute.setHint(getString(R.string.specialDish));
                            break;
                        case "museum":
                            etSpecialAttribute.setHint(getString(R.string.exhibition));
                            break;
                        case "theater":
                            etSpecialAttribute.setHint(getString(R.string.play));
                            break;
                    }
                }
                else {
                    //Notification for wrong type of input.
                    Toast.makeText(MakeNewTouristAttraction.this, getString(R.string.notifWronAttr), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Delivers the information created
     * to the parent activity.
     */
    @Override
    public void onBackPressed() {
        //Empty intent, since it is only a message carrier.
        Intent messageCarrier = new Intent();
        messageCarrier.putExtra(TouristAttractionDetails.CITY_NAME_KEY, this.etName.getText().toString());
        setResult(RESULT_OK, messageCarrier);
        super.onBackPressed();
    }
}
