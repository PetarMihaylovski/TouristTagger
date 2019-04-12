package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.LoginScreen;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;
import nl.saxion.touristattractiontagger.Users.BasicUser;
import nl.saxion.touristattractiontagger.Views.ProgressBar;

public class DisplayFriendsLocationScreen extends AppCompatActivity {
    private BasicUser user;

    /**
     * Assigning the views.
     * Displaying dummy data.
     * Displaying data created by the users.
     *
     * @param savedInstanceState ??
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friends_location_screen);

        //Data display.
        TextView tvDisplay = findViewById(R.id.tvDisplay);
        //Makes the textView scrollable.
        tvDisplay.setMovementMethod(new ScrollingMovementMethod());
        tvDisplay.setText("");

        ProgressBar progressBar = findViewById(R.id.pb4);
        progressBar.setValue(4);

        //Getting the data from the previous screen.
        Intent prevScreen = getIntent();
        String userAsString = prevScreen.getStringExtra(OptionsScreen.USERNAME_KEY);
        this.user = (BasicUser) DataProvider.getUserByName(userAsString);

        //Displaying the dummy data.
        displayDummyData(tvDisplay);
        //Displaying user created data.
        displayData(tvDisplay);
        //On click listener.
        goToLoginScreenOnClickListener();
    }

    /**
     * Displaying the hard-coded data by traversing
     * every key in the hashMap and taking the visited locations
     * behind every dummy user..
     *
     * @param tvDisplay The display where the data is shown.
     */
    private void displayDummyData(TextView tvDisplay) {
        //Traversing the hashMap
        for (Map.Entry mapElement : DataProvider.DUMMY_DATA.entrySet()) {
            //Extracting the data behind the key.
            BasicUser key = (BasicUser) mapElement.getKey();
            tvDisplay.setText(String.format(tvDisplay.getText() + getString(R.string.locationShower) + "\n", key, key.getCity()));
            for (TouristAttraction ta : key.getVisitedVenues()) {
                tvDisplay.setText(String.format("%s%s", tvDisplay.getText().toString(), ta));
            }
            tvDisplay.setText(String.format("%s\n", tvDisplay.getText()));
        }
    }

    /**
     * Displaying the data created by the user.
     *
     * @param tvDisplay The display where the data is shown.
     */
    private void displayData(TextView tvDisplay) {
        String data = String.format(getString(R.string.locationShower), this.user, this.user.getCity());

        //Displaying all the previously generated data.
        for (String str : DataProvider.DATA_DISPLAY) {
            tvDisplay.setText(String.format("%s%s\n", tvDisplay.getText(), str));
        }
        //Displaying where the current user has been tagged from.
        tvDisplay.setText(String.format("%s" + getString(R.string.locationShower) + "\n", tvDisplay.getText(), this.user, this.user.getCity()));

        //Traversing through all the current user's visited locations.
        for (TouristAttraction ta : this.user.getVisitedVenues()) {
            tvDisplay.setText(String.format("%s%s", tvDisplay.getText(), ta));
            data += String.format("%s", ta);
        }
        tvDisplay.setText(String.format("%s\n", tvDisplay.getText()));
        data += "\n";

        //Adding the current information to the storage,
        //to be displayed for the next user.
        DataProvider.addData(data);
    }

    /**
     * On click listener to return
     * to the login screen.
     */
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
