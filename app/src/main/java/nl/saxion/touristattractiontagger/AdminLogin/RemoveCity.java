package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class RemoveCity extends AppCompatActivity {
    public static final String CITY_NAME_KEY = "nameKey";
    private EditText etCityName;
    private Button btnRemoveCity;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_city);

        this.etCityName = findViewById(R.id.etCityToBeDeleted);
        this.btnRemoveCity = findViewById(R.id.btnRemoveCity);
        this.etCityName.setText("");
        removeCityOnClickListener();

    }

    private void removeCityOnClickListener(){
        this.btnRemoveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = etCityName.getText().toString();

                if (!cityName.equals("")){
                    if (DataProvider.removeCityByName(cityName)){
                        Toast.makeText(RemoveCity.this, "City removed successfully. Press the back button to exit.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(RemoveCity.this, "This city does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RemoveCity.this, "Invalid city name!", Toast.LENGTH_SHORT).show();

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
