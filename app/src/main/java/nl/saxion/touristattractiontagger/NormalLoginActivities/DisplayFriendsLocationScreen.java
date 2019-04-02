package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import nl.saxion.touristattractiontagger.Adapters.VisitedLocationsAdapter;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;

public class DisplayFriendsLocationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friends_location_screen);

        VisitedLocationsAdapter adapter = new VisitedLocationsAdapter(this, DataProvider.TEMP_VISITED_PLACES);
        ListView lvFriendsLocationDisplay = findViewById(R.id.lvFriendsLocationDisplay);
        lvFriendsLocationDisplay.setAdapter(adapter);
    }
}
