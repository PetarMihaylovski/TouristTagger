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

    /**
     * Assigning the views.
     * @param savedInstanceState ??
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        this.btnNormalUserLogin = findViewById(R.id.btnNoramlLoginButton);
        this.etAdminUsername = findViewById(R.id.etAdminName);
        this.etAdminPassword = findViewById(R.id.etAdminPassword);
        this.btnAdminLogin = findViewById(R.id.btnLogin);
        this.btnHelp= findViewById(R.id.btnHelp);

        //On click listeners.
        normalLoginOnClickListener();
        adminLoginListener();
        helpButtonOnClickListener();
    }

    /**
     * On click listener for the login button.
     * Compares the user input from the two input fields
     * with the username and the password of the admin.
     */
    private void adminLoginListener(){
        this.btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Administrator admin = DataProvider.ADMIN;
                //Getting the values from the input fields.
                String usernameInput = etAdminUsername.getText().toString();
                String passwordInput = etAdminPassword.getText().toString();

                //Making the necessary checks.
                if (usernameInput.equals(admin.getName()) && passwordInput.equals(admin.getPassword())){
                    Intent switchScreen = new Intent(LoginScreen.this, EditCity.class);
                    startActivity(switchScreen);
                }
                else {
                    Toast.makeText(LoginScreen.this, getString(R.string.wrongUsernameorPasswordPrompt), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * On click listener for the
     * normal way to login as a normal user.
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

    /**
     * On click listener for the help button,
     * showing the admin credentials.
     */
    private void helpButtonOnClickListener(){
        this.btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreen.this, getString(R.string.adminUserAndPass), Toast.LENGTH_LONG).show();
            }
        });
    }

}
