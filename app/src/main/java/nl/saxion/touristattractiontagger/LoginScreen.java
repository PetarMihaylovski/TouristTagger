package nl.saxion.touristattractiontagger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nl.saxion.touristattractiontagger.AdminLogin.EditCity;
import nl.saxion.touristattractiontagger.DataProvider.DataProvider;
import nl.saxion.touristattractiontagger.NormalLoginActivities.CitySelectScreen;
import nl.saxion.touristattractiontagger.Users.Administrator;

public class LoginScreen extends AppCompatActivity {

    private Button btnNormalUserLogin;
    private EditText etAdminUsername;
    private EditText etAdminPassword;
    private Button btnAdminLogin;
    private Button btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        this.btnNormalUserLogin = findViewById(R.id.btnNoramlLoginButton);
        this.etAdminUsername = findViewById(R.id.etAdminName);
        this.etAdminPassword = findViewById(R.id.etAdminPassword);
        this.btnAdminLogin = findViewById(R.id.btnLogin);
        this.btnHelp= findViewById(R.id.btnHelp);

        normalLoginOnClickListener();
        adminLoginListener();

        //TODO: remove later, debug purposes.
        etAdminPassword.setText("admin");
        etAdminUsername.setText("admin");

        //TODO: make the HELP button!
    }

    private void adminLoginListener(){
        this.btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Administrator admin = DataProvider.ADMIN;
                String usernameInput = etAdminUsername.getText().toString();
                String passwordInput = etAdminPassword.getText().toString();

                if (usernameInput.equals(admin.getName()) && passwordInput.equals(admin.getPassword())){
                    Intent switchScreen = new Intent(LoginScreen.this, EditCity.class);
                    startActivity(switchScreen);
                }
                else {
                    Toast.makeText(LoginScreen.this, "Wrong username or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * The normal way to login as a basic user.
     */
    private void normalLoginOnClickListener() {
        btnNormalUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, CitySelectScreen.class);
                startActivity(intent);
            }
        });
    }
}
