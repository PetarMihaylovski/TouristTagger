package nl.saxion.touristattractiontagger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nl.saxion.touristattractiontagger.NormalLoginActivities.CitySelectScreen;

public class LoginScreen extends AppCompatActivity {

    private Button normalUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        normalUserLogin = findViewById(R.id.noramlLoginButton);

        /**
         * The normal way to login as a basic user.
         */
        normalUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, CitySelectScreen.class);
                startActivity(intent);
            }
        });

    }
}
