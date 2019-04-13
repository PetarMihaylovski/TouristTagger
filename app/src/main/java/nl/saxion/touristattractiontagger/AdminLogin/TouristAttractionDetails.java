package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import nl.saxion.touristattractiontagger.Adapters.AdminAttractionDisplayAdapter;
import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.LoginScreen;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class TouristAttractionDetails extends AppCompatActivity {

    private City chosenCity;
    private Button btnAddAttraction;
    private Button btnRemoveAttraction;
    private Button btnGotoLoginScreen;
    private ArrayList<TouristAttraction> touristAttractions;
    private AdminAttractionDisplayAdapter adapter;
    //Keys for data transfer
    public static final int NEW_CITY = 1457;
    public static final String CITY_NAME_KEY = "hiddenKey";

    /**
     * Gets the chosen city from the previous activity. (CityDisplayAdmin activity)
     * Assigning the views.
     * Displaying the tourist attractions of the chosen city.
     *
     * @param savedInstanceState ???
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_details);

        //Getting the information from the previous activity.
        Intent intent = getIntent();
        String cityName = intent.getStringExtra(CityDisplayAdmin.CITY_TRANSFER_KEY);
        this.chosenCity = DataProvider.getCityByName(cityName);
        touristAttractions = chosenCity.getAttractions();

        //Assigning the views.
        TextView tvChosenCity = findViewById(R.id.tvChosenCity);
        this.btnAddAttraction = findViewById(R.id.btnAddTouristAttr);
        this.btnRemoveAttraction = findViewById(R.id.btnRemoveTouristAttr);
        this.btnGotoLoginScreen = findViewById(R.id.btnBackToLoginScreen);

        //Setting the display for the chosen city.
        tvChosenCity.setText(chosenCity.toString());

        //Setting the adapter for the data display.
        adapter = new AdminAttractionDisplayAdapter(this, touristAttractions);
        ListView lvAttractionsDisplay = findViewById(R.id.lvTouristAttrAdminDisplay);
        lvAttractionsDisplay.setAdapter(adapter);

        //On click listeners.
        addNewAttractionOnClickListener();
        removeAttractionOnClickButton();
        gotoLoginScreenOnClickListener();
    }

    /**
     * On click listener for the button that removes
     * a tourist attraction.
     * When clicked a dialog box appears, asking for
     * the name of the tourist attraction that has to
     * be deleted.
     */
    private void removeAttractionOnClickButton() {
        this.btnRemoveAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating a dialog box.
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TouristAttractionDetails.this);
                //Setting the title and the text.
                alertDialog.setTitle(R.string.notifRMTourAttr);
                alertDialog.setMessage(R.string.enterTourAttrName);

                //Creating the input field.
                final EditText input = new EditText(TouristAttractionDetails.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(params);
                alertDialog.setView(input);

                //Creating the cancel button, which only closes the dialog box.
                alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                //Creating the remove button, which deletes the tourist attraction.
                alertDialog.setPositiveButton(getString(R.string.rmvPrompt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Getting the text from the input field.
                        String attractionName = input.getText().toString();
                        //Checking if the input is correct.
                        if (!attractionName.equals("")) {
                            //Removing the tourist attraction.
                            if (chosenCity.removeAttraction(attractionName)) {
                                TouristAttraction attraction = chosenCity.findAttrByName(attractionName);
                                adapter.remove(attraction);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(TouristAttractionDetails.this, getString(R.string.notifTourAttrAdded), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //Notifies the user for trying to delete an unexisting city.
                                Toast.makeText(TouristAttractionDetails.this, getString(R.string.notifTourAttrNotFound), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            //Notifying the user for wrong input name.
                            Toast.makeText(TouristAttractionDetails.this, getString(R.string.notifWrongTourAttrName), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();
            }
        });
    }

    /**
     * Starting a new activity,
     * for creating a new tourist attraction.
     */
    private void addNewAttractionOnClickListener() {
        this.btnAddAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreens = new Intent(TouristAttractionDetails.this, MakeNewTouristAttraction.class);
                switchScreens.putExtra(CITY_NAME_KEY, chosenCity.getName());
                startActivityForResult(switchScreens, NEW_CITY);
            }
        });
    }

    /**
     * Gets the information from the child activity.
     *
     * @param requestCode code securing that we are receiving the information from the correct activity.
     * @param resultCode  code showing that the data is transferred.
     * @param data        the information from the other activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (NEW_CITY == requestCode && RESULT_OK == resultCode) {
            adapter.notifyDataSetChanged();
            Collections.sort(this.touristAttractions);
        }
    }

    /**
     * Returns to the starting screen of the application.
     */
    private void gotoLoginScreenOnClickListener() {
        this.btnGotoLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchIntent = new Intent(TouristAttractionDetails.this, LoginScreen.class);
                startActivity(switchIntent);
            }
        });
    }
}
