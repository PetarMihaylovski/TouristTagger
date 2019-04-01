package nl.saxion.touristattractiontagger.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.CompoundControls.AttractionDisplayCompound;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class AttractionDisplayAdapter extends ArrayAdapter<TouristAttraction> {
    private CheckBox cbAdd;
    private ArrayList<TouristAttraction> attractions;

    public AttractionDisplayAdapter(Context context, ArrayList<TouristAttraction> objects) {
        super(context, R.layout.activity_attraction_select_screen, objects);
        this.attractions = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AttractionDisplayCompound adCompound = new AttractionDisplayCompound(getContext());

        TextView tvName = adCompound.getTvName();
        TextView tvType = adCompound.getTvType();
        TextView tvSpecialAttribute = adCompound.getTvSpecialAttribute();
        this.cbAdd = adCompound.getCbAddVisitedPlaces();

        final TouristAttraction currTouristAttraction = attractions.get(position);

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

        cbAddOnClick(currTouristAttraction);

        if (currTouristAttraction.isHaveBeenThere()){
            this.cbAdd.setChecked(true);
        }
        else {
            this.cbAdd.setChecked(false);
        }

        return adCompound;
    }

    private void cbAddOnClick(final TouristAttraction currTouristAttraction) {

        //TODO: when ticked and scrolled down, the add thick unthicks. Fix me!
        cbAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    currTouristAttraction.setHaveBeenThere(true);

                    if (!DataProvider.VISITED_PLACES.contains(currTouristAttraction)){
                        DataProvider.addVisitedTouristAttraction(currTouristAttraction);
                    }
                    Log.d("testHELP", "" + DataProvider.VISITED_PLACES);
                }
                else {
                    currTouristAttraction.setHaveBeenThere(false);
                    DataProvider.removeTouristAttraction(currTouristAttraction);
                    Log.d("testHELP", "" + DataProvider.VISITED_PLACES);
                }
            }
        });
    }
}
