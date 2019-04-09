package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import nl.saxion.touristattractiontagger.Adapters.CityDisplayAdapter;
import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class EditCity extends AppCompatActivity {
    private Button btnAddCity;
    private Button btnRemoveCity;
    public static final int ADD_REQUEST_CODE = 123;
    private CityDisplayAdapter adapter;
    private ListView lvCities;
    public static final String CITY_TRANSFER_KEY = "transKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_edit);

        this.btnAddCity = findViewById(R.id.btnAddCity);
        this.btnRemoveCity = findViewById(R.id.btnRemoveCity);
        //creating the list view and adding an adapter.
        lvCities = findViewById(R.id.lvCities);
        this.adapter = new CityDisplayAdapter(this, DataProvider.CITIES);
        lvCities.setAdapter(adapter);

        addCityOnClickListener();
        removeCityOnClick();
        citiesItemClickListener();
    }

    private void citiesItemClickListener() {
        this.lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //getting the clicked city
                City city = DataProvider.CITIES.get(position);
                //switching the activity and transferring the city which was clicked.
                Intent switchScreen = new Intent(EditCity.this, TouristAttractionDetails.class);
                switchScreen.putExtra(CITY_TRANSFER_KEY, city.getName());
                startActivity(switchScreen);
            }
        });
    }

    private void removeCityOnClick() {
        this.btnRemoveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditCity.this);
                alertDialog.setTitle("Remove city");
                alertDialog.setMessage("Enter the name of the city");

                final EditText input = new EditText(EditCity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(params);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityName = input.getText().toString();

                        if (!cityName.equals("")){
                            if (DataProvider.removeCityByName(cityName)){
                                adapter.remove(DataProvider.getCityByName(cityName));
                                adapter.notifyDataSetChanged();
                                Toast.makeText(EditCity.this, "City removed successfully.", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(EditCity.this, "This city does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(EditCity.this, "Invalid city name!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void addCityOnClickListener() {
        this.btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(EditCity.this, AddCity.class);
                startActivityForResult(switchScreen, ADD_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }
}


