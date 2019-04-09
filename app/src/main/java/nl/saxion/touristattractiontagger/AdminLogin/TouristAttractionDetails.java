package nl.saxion.touristattractiontagger.AdminLogin;

import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.concurrent.TimeUnit;

import nl.saxion.touristattractiontagger.Adapters.AdminAttractionDisplayAdapter;
import nl.saxion.touristattractiontagger.Adapters.AttractionDisplayAdapter;
import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.NormalLoginActivities.AttractionSelectScreen;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class TouristAttractionDetails extends AppCompatActivity {

    private City city;
    private Button btnAddAttraction;
    private Button btnRemoveAttraction;
    private AdminAttractionDisplayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_details);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra(EditCity.CITY_TRANSFER_KEY);
        this.city = DataProvider.getCityByName(cityName);
        ArrayList<TouristAttraction> touristAttractions = city.getAttractions();

        TextView tvChosenCity = findViewById(R.id.tvChosenCity);
        tvChosenCity.setText(city.toString());

        adapter = new AdminAttractionDisplayAdapter(this, touristAttractions);
        ListView lvAttractionsDisplay = findViewById(R.id.lvTouristAttrAdminDisplay);
        lvAttractionsDisplay.setAdapter(adapter);

        this.btnRemoveAttraction = findViewById(R.id.btnRemoveTouristAttr);

        removeAttractionOnClickButton();
    }

    private void removeAttractionOnClickButton() {
        this.btnRemoveAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TouristAttractionDetails.this);
                alertDialog.setTitle("Remove a tourist attraction");
                alertDialog.setMessage("Enter the name of the attraction");
                Log.d("removeATR", "In the method.");

                final EditText input = new EditText(TouristAttractionDetails.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(params);
                alertDialog.setView(input);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String attractionName = input.getText().toString();
                        if (city.removeAttraction(attractionName)) {
                            TouristAttraction attraction = city.findAttrByName(attractionName);
                            adapter.remove(attraction);
                            Toast.makeText(TouristAttractionDetails.this, "Tourist attraction removed successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(TouristAttractionDetails.this, "Tourist attraction does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                alertDialog.show();
                adapter.notifyDataSetChanged();
            }
        });
    }


}
