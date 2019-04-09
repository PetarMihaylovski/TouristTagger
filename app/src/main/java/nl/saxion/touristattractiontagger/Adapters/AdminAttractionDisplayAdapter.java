package nl.saxion.touristattractiontagger.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class AdminAttractionDisplayAdapter extends ArrayAdapter<TouristAttraction> {


    public AdminAttractionDisplayAdapter(Context context, List<TouristAttraction> objects) {
        super(context, R.layout.custom_admin_attractions_display, objects);
    }
}
