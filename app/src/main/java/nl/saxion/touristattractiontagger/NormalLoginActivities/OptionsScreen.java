package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.Users.BasicUser;
import nl.saxion.touristattractiontagger.Views.ProgressBar;

public class OptionsScreen extends AppCompatActivity {
    private Button btnShowFriends;
    private Button btnEditPlaces;
    private BasicUser user;
    private String cityAsString;
    //Keys for data transfer.
    public static final String CITY_STR_KEY = "cityStringKey";
    public static final String USERNAME_KEY = "usernameKey";

    /**
     * Getting information from the previous activity.
     * Assigning the views.
     * @param savedInstanceState ??
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_sreen);

        //Get the information from the previous screen.
        Intent prevScreen = getIntent();
        String userAsString = prevScreen.getStringExtra(AttractionSelectScreen.NAME_KEY);
        this.user = (BasicUser) DataProvider.getUserByName(userAsString);
        cityAsString = prevScreen.getStringExtra(AttractionSelectScreen.CITY_KEY);
        City chosenCity = DataProvider.getCityByName(cityAsString);

        //Assign the views.
        this.btnShowFriends = findViewById(R.id.btnShowFriends);
        this.btnEditPlaces = findViewById(R.id.btnEditPlaces);
        TextView tvCityNameDisplay = findViewById(R.id.tvCityName);
        TextView tvNameAndLocation = findViewById(R.id.tvUsernameAndLocation);
        ProgressBar progressBar = findViewById(R.id.pb3);
        progressBar.setValue(3);

        //Setting the values.
        tvCityNameDisplay.setText(String.format("%s", chosenCity));
        tvNameAndLocation.setText(String.format(getString(R.string.locationDisplay), this.user.getName(), chosenCity));

        //Loading the images from the assets folder.
        loadImage(chosenCity);

        //On click listeners.
        showFriendsOnClickListener();
        editSelectedPlacesOnClickListener();
    }

    /**
     * Loads the image assigned to the city.
     * @param city the chosen city.
     */
    private void loadImage(City city) {
        //Assigning the view.
        ImageView picture = findViewById(R.id.ivPicture);
        //Opening a stream.
        InputStream inputStream = null;
        try {
            //Getting the name of picture, that we want to display.
            String imageFile = city.getPictureID();
            //Getting the picture from th assets folder.
            inputStream = getApplicationContext().getAssets().open(imageFile);
            //Displaying the picture.
            Drawable d = Drawable.createFromStream(inputStream, null);
            picture.setImageDrawable(d);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            //Closing the streams that are opened.
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Changes to the next activity when called.
     * Sends the user, who is choosing where he/she has been.
     * Sends the city, chosen by the user.
     */
    private void showFriendsOnClickListener() {
        this.btnShowFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(OptionsScreen.this, DisplayFriendsLocationScreen.class);
                switchScreen.putExtra(USERNAME_KEY, user.toString());
                switchScreen.putExtra(CITY_STR_KEY, cityAsString);
                startActivity(switchScreen);
            }
        });
    }

    /**
     * Going back to the parent activity,
     * so the user edits the places he has been.
     */
    private void editSelectedPlacesOnClickListener() {
        this.btnEditPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prevScreen = new Intent();
                setResult(RESULT_OK, prevScreen);
                finish();
            }
        });
    }
}
