package nl.saxion.touristattractiontagger.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.CompoundControls.AttractionDisplayCompound;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;
import nl.saxion.touristattractiontagger.Users.BasicUser;

public class AttractionDisplayAdapter extends ArrayAdapter<TouristAttraction> {
    private CheckBox cbAdd;
    private ArrayList<TouristAttraction> attractions;
    private BasicUser user;

    /**
     * Constructor
     *
     * @param context the context of the current state
     * @param objects the arrayList on which we are applying the adapter.
     * @param user    the person who is selecting his visited tourist attractions.
     */
    public AttractionDisplayAdapter(Context context, ArrayList<TouristAttraction> objects, BasicUser user) {
        super(context, R.layout.activity_attraction_select_screen, objects);
        this.attractions = objects;
        this.user = user;
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
        //The compound from where I am extracting the views.
        AttractionDisplayCompound adCompound = new AttractionDisplayCompound(getContext());

        //Assign the views.
        TextView tvName = adCompound.getTvName();
        TextView tvType = adCompound.getTvType();
        TextView tvSpecialAttribute = adCompound.getTvSpecialAttribute();
        this.cbAdd = adCompound.getCbAddVisitedPlaces();

        //Get the clicked tourist attraction.
        final TouristAttraction currTouristAttraction = attractions.get(position);

        //Set the text for the listView.
        String text = "Name: " + currTouristAttraction.getName();
        tvName.setText(text);

        text = "Type: " + currTouristAttraction.getType();
        tvType.setText(text);

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

        //OnClickListener for the checkbox.
        cbAddOnClick(currTouristAttraction);

        if (currTouristAttraction.isChecked()) {
            this.cbAdd.setChecked(true);
        }
        else {
            this.cbAdd.setChecked(false);
        }

        return adCompound;
    }

    /**
     * On click listener for the checkboxes.
     * When checked, the chosen tourist attraction will be added
     * to the user's visited locations.
     * When unchecked, the tourist attraction gets removed
     * from the user's visited locations.
     *
     * @param currTouristAttraction the tourist attraction the user is adding/removing.
     */
    private void cbAddOnClick(final TouristAttraction currTouristAttraction) {
        cbAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Sets the checkbox to checked.
                    currTouristAttraction.setChecked(true);
                    //Checks if the current tourist attraction is already in the list.
                    if (!user.getVisitedVenues().contains(currTouristAttraction)) {
                        //Adds it, if it is not.
                        user.addVisitedVenue(currTouristAttraction);
                    }
                }
                else {
                    //Sets the checkbox to unchecked.
                    currTouristAttraction.setChecked(false);
                    //Removes the current tourist attraction.
                    user.removeVisitedVenue(currTouristAttraction);
                }
            }
        });
    }
}
