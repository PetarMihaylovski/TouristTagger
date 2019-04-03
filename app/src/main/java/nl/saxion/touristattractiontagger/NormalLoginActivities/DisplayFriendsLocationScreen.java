package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class DisplayFriendsLocationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friends_location_screen);
        
        DataProvider.dataTransfer(DataProvider.TEMP_VISITED_PLACES, DataProvider.PERMANENT_VISITED_PLACES);
        ScrollView svTest = findViewById(R.id.scScroll);
        TextView tvDisplay = svTest.findViewById(R.id.tvDataDisplay);

        Intent prevScreen = getIntent();
        String userName = prevScreen.getStringExtra(OptionsScreen.USERNAME_KEY);
        String cityString = prevScreen.getStringExtra(OptionsScreen.CITY_STR_KEY);
        City city = DataProvider.getCityByName(cityString);

        tvDisplay.setText(String.format("%s has been in %s\n", userName, city));
        //TODO: make the data look nice!
        for (TouristAttraction ta : DataProvider.PERMANENT_VISITED_PLACES) {
            tvDisplay.setText(String.format("%s %s\n\t", tvDisplay.getText().toString(), ta));
        }
        
    }
}
