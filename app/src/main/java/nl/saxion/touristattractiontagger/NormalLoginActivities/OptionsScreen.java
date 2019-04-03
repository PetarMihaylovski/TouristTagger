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

public class OptionsScreen extends AppCompatActivity {
    private Button btnShowFriends;
    private Button btnEditPlaces;
    private String userName;
    public static final String USERNAME_KEY = "gettoBoye";
    public static final String CITY_STR_KEY = "it is me MARIO!";
    private String cityAsString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_sreen);

        //Get the information from the previous screen.
        Intent prevScreen = getIntent();
        userName = prevScreen.getStringExtra(AttractionSelectScreen.NAME_KEY);
        cityAsString = prevScreen.getStringExtra(AttractionSelectScreen.CITY_KEY);
        City city = DataProvider.getCityByName(cityAsString);

        this.btnShowFriends = findViewById(R.id.btnShowFriends);
        this.btnEditPlaces = findViewById(R.id.btnEditPlaces);
        TextView tvCityNameDisplay = findViewById(R.id.tvCityName);
        TextView tvNameAndLocation = findViewById(R.id.tvUsernameAndLocation);

        tvCityNameDisplay.setText(String.format("%s", city));
        tvNameAndLocation.setText(String.format("%s is in %s", userName, city));
        loadImage(city);
        showFriendsOnClickListener();
        editSelectedPlacesOnClickListener();
    }

    private void loadImage(City city) {
        //loads the image, based on the city chosen.
        ImageView picture = findViewById(R.id.ivPicture);
        InputStream inputStream = null;
        try {
            String imageFile = city.getPictureID();
            inputStream = getApplicationContext().getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            picture.setImageDrawable(d);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
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

    private void showFriendsOnClickListener() {
        this.btnShowFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(OptionsScreen.this, DisplayFriendsLocationScreen.class);
                switchScreen.putExtra(USERNAME_KEY, userName);
                switchScreen.putExtra(CITY_STR_KEY, cityAsString);
                startActivity(switchScreen);
            }
        });
    }

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
