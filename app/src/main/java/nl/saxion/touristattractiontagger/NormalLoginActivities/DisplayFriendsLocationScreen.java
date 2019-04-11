package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Map;

import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.LoginScreen;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;
import nl.saxion.touristattractiontagger.Users.BasicUser;

public class DisplayFriendsLocationScreen extends AppCompatActivity {
    private BasicUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friends_location_screen);

        TextView tvDisplay = findViewById(R.id.tvDisplay);
        tvDisplay.setText("");
        tvDisplay.setMovementMethod(new ScrollingMovementMethod());

        //getting the data from the previous screen.
        Intent prevScreen = getIntent();
        String userAsString = prevScreen.getStringExtra(OptionsScreen.USERNAME_KEY);
        this.user = (BasicUser) DataProvider.getUserByName(userAsString);

        displayDummyData(tvDisplay);
        displayData(tvDisplay);
        goToLoginScreenOnClickListener();
    }

    private void displayDummyData(TextView tvDisplay) {
        for (Map.Entry mapElement : DataProvider.DUMMY_DATA.entrySet()) {
            BasicUser key = (BasicUser) mapElement.getKey();
            tvDisplay.setText(String.format(tvDisplay.getText() + " %s was in %s\n", key, key.getCity()));
            for (TouristAttraction ta : key.getVisitedVenues()) {
                tvDisplay.setText(String.format("%s%s", tvDisplay.getText().toString(), ta));
            }
            tvDisplay.setText(String.format("%s\n", tvDisplay.getText()));
        }
    }

    private void displayData(TextView tvDisplay) {
        String data = String.format("%s was in %s", this.user, this.user.getCity());

        for (String str : DataProvider.DATA_DISPLAY) {
            tvDisplay.setText(String.format("%s%s", tvDisplay.getText(), str));
        }

        tvDisplay.setText(String.format("%s %s was in %s\n", tvDisplay.getText(), this.user, this.user.getCity()));

        for (TouristAttraction ta : this.user.getVisitedVenues()) {
            tvDisplay.setText(String.format("%s%s", tvDisplay.getText(), ta));
            data += String.format("%s", ta);
        }
        tvDisplay.setText(String.format("%s\n", tvDisplay.getText()));
        data += "\n";
        DataProvider.addData(data);
    }

    //TODO: find way to reset the checkboxes
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
