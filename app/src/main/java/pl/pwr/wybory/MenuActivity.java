package pl.pwr.wybory;

import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.pwr.wybory.Interfaces.Access;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Model.Candidate;
import pl.pwr.wybory.Model.Questionnaire;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setUpOnClicks();
    }

    private void setUpOnClicks() {

        Button questionaryButton = (Button) findViewById(R.id.questionary_button);
        Button electionsButton = (Button) findViewById(R.id.show_elections_button);
        Button profileButton = (Button) findViewById(R.id.show_profile_button);

        questionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, QuestionnaireActivity.class);
                startActivity(intent);
            }
        });
        electionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ElectionsActivity.class);
                startActivity(intent);
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CandidateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Const.CANDIDATE_ID_BUNDLE, Access.user.getElector_id());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        if (!Access.isCandidate()){
            profileButton.setVisibility(View.GONE);
        }
    }
}
