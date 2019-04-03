package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.LoginScreen;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;
import nl.saxion.touristattractiontagger.Users.BasicUser;

public class DisplayFriendsLocationScreen extends AppCompatActivity {
    private BasicUser user;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friends_location_screen);

        DataProvider.dataTransfer(DataProvider.TEMP_VISITED_PLACES, DataProvider.PERMANENT_VISITED_PLACES);
        ScrollView test = findViewById(R.id.svScroll);
        TextView tvDisplay = test.findViewById(R.id.tvDisplay);

        //getting the data from the previous screen.
        Intent prevScreen = getIntent();
        String userAsString = prevScreen.getStringExtra(OptionsScreen.USERNAME_KEY);
        this.user = (BasicUser) DataProvider.getUserByName(userAsString);
        String cityString = prevScreen.getStringExtra(OptionsScreen.CITY_STR_KEY);
        city = DataProvider.getCityByName(cityString);

        displayData(tvDisplay);
        goToLoginScreenOnClickListener();
    }

    private void displayData(TextView tvDisplay) {
        //TODO: Implement a HashMap with key: User and values: ArrayList<TA>, here loop
        //TODO: through all the users and print their data.

        tvDisplay.setText(String.format("%s has been in %s\n", user, city));
//        TODO: make the data look nice!
        for (TouristAttraction ta : DataProvider.PERMANENT_VISITED_PLACES) {
            tvDisplay.setText(String.format("%s %s\n\t", tvDisplay.getText().toString(), ta));
        }
    }

    private void goToLoginScreenOnClickListener() {
        Button btnGotoLoginScreen = findViewById(R.id.btnGoToLoginScreen);
        btnGotoLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreens = new Intent(DisplayFriendsLocationScreen.this, LoginScreen.class);
                startActivity(switchScreens);
            }
        });
    }
}
