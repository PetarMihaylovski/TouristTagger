package nl.saxion.touristattractiontagger.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class VisitedLocationsAdapter extends ArrayAdapter<TouristAttraction> {
    private ArrayList<TouristAttraction> finalVisited;   //TODO: rename with a better name.
    private LayoutInflater inflater;
    public VisitedLocationsAdapter(Context context, ArrayList<TouristAttraction> objects) {
        super(context, R.layout.custom_friends_display , objects);
        finalVisited = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.custom_city_display, parent, false);
        }

        TextView tvTest = convertView.findViewById(R.id.textView);
        tvTest.setText();
    }
}
