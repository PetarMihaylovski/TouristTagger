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

public class AdminAttractionDisplayAdapter extends ArrayAdapter<TouristAttraction> {
    private ArrayList<TouristAttraction> attractions;
    private LayoutInflater inflater;

    /**
     * Constructor
     *
     * @param context the context of the current state
     * @param objects the arrayList on which we are applying the adapter.
     */
    public AdminAttractionDisplayAdapter(Context context, ArrayList<TouristAttraction> objects) {
        super(context, R.layout.custom_admin_attractions_display, objects);
        this.attractions = objects;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Setting the way the data will be shown in the listView.
     *
     * @param position    the position of the clicked element
     * @param convertView the view itself.
     * @param parent      the parent
     * @return returns the modified view. The way the data will be displayed.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //Securing that the view is set
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_admin_attractions_display, parent, false);
        }

        TextView tvAttractionName = convertView.findViewById(R.id.tvAttractionName);
        TextView tvAttractionType = convertView.findViewById(R.id.tvAttractionType);
        TextView tvSpecialAttribute = convertView.findViewById(R.id.tvSpecialAttribute);

        //Get the current tourist attraction.
        final TouristAttraction currTouristAttraction = attractions.get(position);

        //Set the text for the listView.
        String text = "Name: " + currTouristAttraction.getName();
        tvAttractionName.setText(text);

        text = "Type: " + currTouristAttraction.getType();
        tvAttractionType.setText(text);

        if (currTouristAttraction.getType().equals("Bar")) {
            text = "Special Drink: " + currTouristAttraction.getSpecialAttribute();
        }
        else if (currTouristAttraction.getType().equals("Restaurant")) {
            text = "Special Meal: " + currTouristAttraction.getSpecialAttribute();
        }
        else if (currTouristAttraction.getType().equals("Theater")) {
            text = "Today's play: " + currTouristAttraction.getSpecialAttribute();
        }
        else if (currTouristAttraction.getType().equals("Museum")) {
            text = "Special Exhibition: " + currTouristAttraction.getSpecialAttribute();
        }
        tvSpecialAttribute.setText(text);

        return convertView;
    }
}
