package pl.pwr.wybory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CandidateActivity extends AppCompatActivity {

    Candidate candidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        this.candidate = getIntent().getExtras().getParcelable(Const.CANDIDATE_BUNDLE);
    }
}
