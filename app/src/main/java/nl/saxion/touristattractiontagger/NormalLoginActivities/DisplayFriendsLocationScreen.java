package nl.saxion.touristattractiontagger.NormalLoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import nl.saxion.touristattractiontagger.City;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.LoginScreen;
import nl.saxion.touristattractiontagger.R;
import nl.saxion.touristattractiontagger.TouristsAttractions.TouristAttraction;

public class DisplayFriendsLocationScreen extends AppCompatActivity {
    private String userName;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friends_location_screen);

        DataProvider.dataTransfer(DataProvider.TEMP_VISITED_PLACES, DataProvider.PERMANENT_VISITED_PLACES);
        ScrollView test = findViewById(R.id.svScroll);
        TextView tvDisplay = test.findViewById(R.id.tvDisplay);

        //getting the data from the previous screen.
        Intent prevScreen = getIntent();
        userName = prevScreen.getStringExtra(OptionsScreen.USERNAME_KEY);
        String cityString = prevScreen.getStringExtra(OptionsScreen.CITY_STR_KEY);
        city = DataProvider.getCityByName(cityString);

        displayData(tvDisplay);
        goToLoginScreenOnClickListener();
    }

    private void displayData(TextView tvDisplay) {
/*               tvDisplay.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nulla nulla, ultricies id velit at, gravida tincidunt diam. Nulla commodo lorem vel tortor hendrerit, sed cursus leo tincidunt. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur convallis eget augue non imperdiet. Nullam venenatis neque a erat sodales tincidunt. Nulla facilisi. Proin tempus tortor at eros fringilla, nec aliquam arcu fringilla. Nulla aliquam nisi vitae justo ornare, id dignissim diam ultricies. Proin malesuada leo sed facilisis finibus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam vitae rhoncus enim. Maecenas vitae tincidunt diam, vitae euismod turpis. Morbi nec justo enim.\n" +
                "\n" +
                "Duis vehicula ligula et ex auctor facilisis. Cras tempus, lorem id lobortis interdum, purus nunc tincidunt nibh, vitae volutpat mi elit in ex. Nunc elementum, nulla eu suscipit vestibulum, ipsum dolor faucibus elit, ut tincidunt magna felis id nulla. Quisque ut dolor justo. Morbi tempus facilisis turpis, eget pellentesque nunc porttitor non. Duis et nisl id massa viverra sollicitudin ut sit amet sem. Quisque nisl ante, luctus et enim a, malesuada varius magna. Ut lobortis dolor ut ipsum sollicitudin, eget luctus ex aliquam. In facilisis eleifend rutrum. Suspendisse eu augue tempus, scelerisque neque vitae, sagittis ex. Suspendisse vulputate lobortis eros, id suscipit metus gravida ut. Sed feugiat non ligula ut condimentum.\n" +
                "\n" +
                "Ut cursus luctus elementum. Pellentesque viverra quam ante, ac placerat dolor dignissim at. Integer in eros ipsum. Vivamus ullamcorper ullamcorper tellus a mattis. Praesent id dolor a eros tristique blandit nec a mi. Phasellus varius eu ipsum vitae elementum. Morbi eu dolor neque.\n" +
                "\n" +
                "Curabitur in dui lorem. Nulla tempus, odio eu malesuada interdum, lacus purus elementum lectus, a facilisis arcu odio a sapien. In risus ipsum, pulvinar ut efficitur vitae, ullamcorper eu elit. Integer at ipsum eget sapien imperdiet porttitor. Pellentesque et nunc ipsum. Sed efficitur pharetra rhoncus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum feugiat luctus est et elementum. Morbi et placerat velit.\n" +
                "\n" +
                "Morbi vestibulum dui ac pulvinar facilisis. Curabitur sit amet tincidunt massa. Nullam pulvinar ac mauris non interdum. Curabitur feugiat molestie nunc, sit amet dapibus nulla dapibus vitae. Fusce luctus neque sit amet purus hendrerit lobortis vitae sed erat. Nunc molestie egestas arcu, et tristique justo bibendum tristique. Mauris urna lacus, tempus eu mauris non, euismod commodo lacus. Pellentesque sit amet tellus porta, tincidunt lectus eget, tempor ligula. Cras ut vulputate libero. Quisque imperdiet, ipsum vitae fringilla pharetra, orci arcu euismod felis, nec efficitur neque felis faucibus sapien. Vestibulum vulputate ultricies nibh non luctus.");
  */
        tvDisplay.setText(String.format("%s has been in %s\n", userName, city));
//        TODO: make the data look nice!
        for (TouristAttraction ta : DataProvider.PERMANENT_VISITED_PLACES) {
            tvDisplay.setText(String.format("%s %s\n\t", tvDisplay.getText().toString(), ta));
        }
    }

    private void goToLoginScreenOnClickListener() {
        Button btnGotoLoginScreen = findViewById(R.id.btnGoToLoginScreen);
        btnGotoLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreens = new Intent(DisplayFriendsLocationScreen.this, LoginScreen.class);
                userName = "";
                startActivity(switchScreens);
            }
        });
    }
}
