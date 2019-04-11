package nl.saxion.touristattractiontagger.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.R;

public class CityDisplayAdapter extends ArrayAdapter<City> {

    private static ArrayList<City> cities;
    private LayoutInflater inflater;

    public CityDisplayAdapter(Context context, ArrayList<City> objects) {
        super(context, R.layout.custom_city_display, objects);

        cities = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.custom_city_display, parent, false);
        }

        TextView tvCity = convertView.findViewById(R.id.tvCity);

        tvCity.setText(cities.get(position).toString());

        return convertView;
    }
}
