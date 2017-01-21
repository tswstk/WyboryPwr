package pl.pwr.wybory;

import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.pwr.wybory.Model.Candidate;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setUpOnClicks();
    }

    private void setUpOnClicks() {

        findViewById(R.id.add_elections_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CandidateActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.questionary_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.show_elections_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CandidateActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.show_profile_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CandidateActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.add_elections_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CandidateActivity.class);
                startActivity(intent);
            }
        });
    }
}
