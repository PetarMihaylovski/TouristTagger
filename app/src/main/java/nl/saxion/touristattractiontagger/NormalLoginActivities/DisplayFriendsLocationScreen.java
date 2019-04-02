package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import nl.saxion.touristattractiontagger.Adapters.VisitedLocationsAdapter;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class DisplayFriendsLocationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friends_location_screen);

        DataProvider.dataTransfer(DataProvider.TEMP_VISITED_PLACES, DataProvider.PERMANENT_VISITED_PLACES);
    }
}
