package pl.pwr.wybory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Model.Candidate;

public class CandidateActivity extends AppCompatActivity {

    Candidate candidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        this.candidate = getIntent().getExtras().getParcelable(Const.CANDIDATE_BUNDLE);

        fillCandidateInformation();

        findViewById(R.id.vote_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote();
            }
        });
    }

    private void fillCandidateInformation() {
        ((TextView)findViewById(R.id.name_textView)).setText(candidate.getFirst_name()+" "+candidate.getLast_name());
        ((TextView)findViewById(R.id.position_textView)).setText(candidate.getPosition().getNameOfPosition());
        ((TextView)findViewById(R.id.date_textView)).setText(candidate.getDate());
        ((TextView)findViewById(R.id.program_textView)).setText(candidate.getProgram());
        ((TextView)findViewById(R.id.faculty_textView)).setText(candidate.getPosition().getFaculty());
    }

    private void vote() {
    }

}
