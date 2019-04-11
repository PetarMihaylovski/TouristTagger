package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private City city;
    private Button btnAddAttraction;
    private Button btnRemoveAttraction;
    private Button btnGotoLoginScreen;
    private AdminAttractionDisplayAdapter adapter;
    public static final int NEW_CITY = 1457;
    public static final String CITY_NAME_KEY = "hiddenKey";
    private ArrayList<TouristAttraction> touristAttractions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_details);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra(EditCity.CITY_TRANSFER_KEY);
        this.city = DataProvider.getCityByName(cityName);
        touristAttractions = city.getAttractions();

        TextView tvChosenCity = findViewById(R.id.tvChosenCity);
        tvChosenCity.setText(city.toString());

        adapter = new AdminAttractionDisplayAdapter(this, touristAttractions);
        ListView lvAttractionsDisplay = findViewById(R.id.lvTouristAttrAdminDisplay);
        lvAttractionsDisplay.setAdapter(adapter);

        this.btnAddAttraction = findViewById(R.id.btnAddTouristAttr);
        this.btnRemoveAttraction = findViewById(R.id.btnRemoveTouristAttr);
        this.btnGotoLoginScreen = findViewById(R.id.btnBackToLoginScreen);

        addNewAttractionOnClickListener();
        removeAttractionOnClickButton();
        gotoLoginScreenOnClickListener();
    }

    private void removeAttractionOnClickButton() {
        this.btnRemoveAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TouristAttractionDetails.this);
                alertDialog.setTitle(R.string.notifRMTourAttr);
                alertDialog.setMessage(R.string.enterTourAttrName);

                final EditText input = new EditText(TouristAttractionDetails.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(params);
                alertDialog.setView(input);

                alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.setPositiveButton(getString(R.string.rmvPrompt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String attractionName = input.getText().toString();
                        if (!attractionName.equals("")) {
                            if (city.removeAttraction(attractionName)) {
                                TouristAttraction attraction = city.findAttrByName(attractionName);
                                adapter.remove(attraction);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(TouristAttractionDetails.this, getString(R.string.notifTourAttrAdded), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(TouristAttractionDetails.this, getString(R.string.notifTourAttrNotFound), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(TouristAttractionDetails.this, getString(R.string.notifWrongTourAttrName), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void addNewAttractionOnClickListener(){
        this.btnAddAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreens = new Intent(TouristAttractionDetails.this, MakeNewTouristAttraction.class);
                switchScreens.putExtra(CITY_NAME_KEY, city.getName());
                startActivityForResult(switchScreens, NEW_CITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (NEW_CITY == requestCode && RESULT_OK == resultCode){
            adapter.notifyDataSetChanged();
            Collections.sort(this.touristAttractions);
        }
    }

    private void gotoLoginScreenOnClickListener(){
        this.btnGotoLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchIntent = new Intent(TouristAttractionDetails.this, LoginScreen.class);
                startActivity(switchIntent);
            }
        });
    }
}
