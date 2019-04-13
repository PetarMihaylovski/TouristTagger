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

import java.util.Collections;

import nl.saxion.touristattractiontagger.Adapters.CityDisplayAdapter;
import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class CityDisplayAdmin extends AppCompatActivity {
    private CityDisplayAdapter adapter;
    private ListView lvCities;
    //Keys for data transfer
    public static final int ADD_REQUEST_CODE = 123;
    public static final String CITY_TRANSFER_KEY = "transferKey";
    public static final String CITY_EDIT_NAME_KEY = "cityEditName";
    public static final String CITY_EDIT_COUNTRY_KEY = "cityEditCountry";
    public static final int EDIT_CITY = 332;


    /**
     * The method called, whenever the activity is started.
     * Displaying the data.
     * @param savedInstanceState ??
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_edit);

        //Creating and setting the adapter for the listView.
        this.adapter = new CityDisplayAdapter(this, DataProvider.CITIES);
        lvCities = findViewById(R.id.lvCities);
        lvCities.setAdapter(adapter);

        //On click listeners.
        addCityOnClickListener();
        removeCityOnClick();
        citiesItemClickListener();
        editCityOnClickListener();
    }

    /**
     * On item click listener for the items in the listView.
     * Whichever city is clicked, the screen changes to
     * this city's tourist attractions.
     */
    private void citiesItemClickListener() {
        this.lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Getting the clicked city
                City chosenCity = DataProvider.CITIES.get(position);
                //Switching the activity and transferring the city which was clicked.
                Intent switchScreen = new Intent(CityDisplayAdmin.this, TouristAttractionDetails.class);
                switchScreen.putExtra(CITY_TRANSFER_KEY, chosenCity.getName());
                startActivity(switchScreen);
            }
        });
    }

    /**
     * On click listener for the remove button.
     * When clicked a dialog box appears and the admin
     * enters the name of the city he/she wants to delete.
     */
    private void removeCityOnClick() {
        Button btnRemoveCity = findViewById(R.id.btnRemoveCity);
        btnRemoveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the dialog box.
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CityDisplayAdmin.this);
                alertDialog.setTitle(R.string.cityRemove);
                alertDialog.setMessage(R.string.cityNamePrompt);

                //Setting a edit text view, so the admin can enter the name
                //of the city he/she wants to delete.
                final EditText input = new EditText(CityDisplayAdmin.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(params);
                alertDialog.setView(input);

                //Setting a button, which when clicked removes the city.
                alertDialog.setPositiveButton(R.string.rmvPrompt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityName = input.getText().toString();
                        //Check if the user actually writes a name of the city.
                        if (!cityName.equals("")) {
                            //Removing the city.
                            if (DataProvider.removeCityByName(cityName)) {
                                adapter.remove(DataProvider.getCityByName(cityName));
                                adapter.notifyDataSetChanged();
                                Toast.makeText(CityDisplayAdmin.this, getString(R.string.notifRemoveSuccess), Toast.LENGTH_LONG).show();
                            }
                            else {
                                //Notifying that the city he wrote does not exist.
                                Toast.makeText(CityDisplayAdmin.this, getString(R.string.notifUnexistingCity), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            //Notifying that the user has not provided an input.
                            Toast.makeText(CityDisplayAdmin.this, getString(R.string.notifInvalidCity), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                //Setting a button, which when clicked returns to the previous action,
                //without removing anything.
                alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }

    /**
     * On click listener on the button for creating a new city.
     * When clicked, a new activity opens up.
     */
    private void addCityOnClickListener() {
        Button btnAddCity = findViewById(R.id.btnAddCity);
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(CityDisplayAdmin.this, MakeNewCity.class);
                startActivityForResult(switchScreen, ADD_REQUEST_CODE);
            }
        });
    }

    private void editCityOnClickListener(){
        Button btnEditCity = findViewById(R.id.btnEditCity);
        btnEditCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CityDisplayAdmin.this);
                alertDialog.setTitle("Edit a city");
                alertDialog.setMessage("Enter the name of the city you want to edit.");

                //Setting a edit text view, so the admin can enter the name
                //of the city he/she wants to edit.
                final EditText input = new EditText(CityDisplayAdmin.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(params);
                alertDialog.setView(input);

                //Setting a button, which when clicked opens a new activity to edit the city.
                alertDialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityName = input.getText().toString().toLowerCase();
                        //Check if the user actually writes a name of the city.
                        if (!cityName.equals("")) {
                            //Switching intents to EditCity activity, where the user can edit the name and the country of the city.
                           if (cityName.equals(DataProvider.getCityByName(cityName).getName().toLowerCase())){
                               City cityToBeEdited = DataProvider.getCityByName(cityName);
                               Intent switchScreens = new Intent(CityDisplayAdmin.this, EditCity.class);
                               switchScreens.putExtra(CITY_EDIT_NAME_KEY, cityToBeEdited.getName());
                               switchScreens.putExtra(CITY_EDIT_COUNTRY_KEY, cityToBeEdited.getCountry());
                               startActivityForResult(switchScreens, EDIT_CITY );
                           }
                           else {
                               Toast.makeText(CityDisplayAdmin.this, getString(R.string.notifUnexistingCity), Toast.LENGTH_SHORT).show();
                           }
                        }
                        else {
                            //Notifying that the user has not provided an input.
                            Toast.makeText(CityDisplayAdmin.this, getString(R.string.notifInvalidCity), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                //Setting a button, which when clicked returns to the previous action,
                //without doing nothing.
                alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }

    /**
     * Called when the user is getting returned from
     * a previous activity to this.
     * Getting the information the user provided in the other activity.
     *
     * @param requestCode code securing that we are receiving the information from the correct activity.
     * @param resultCode  code showing that the data is transfered.
     * @param data        the information from the other activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
            Collections.sort(DataProvider.CITIES);
        }
    }
}


