package pl.pwr.wybory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = String.valueOf(((EditText)findViewById(R.id.login_editText)).getText());
                String password = String.valueOf(((EditText)findViewById(R.id.login_editText)).getText());

                if(verify(login, password)){
                    goToElectionsActivity();
                }
            }
        });
    }

    private void goToElectionsActivity() {
        Intent intent = new Intent(this, ElectionsActivity.class);
        startActivity(intent);
    }

    boolean verify(String login, String password){
        return true;
    }
}
