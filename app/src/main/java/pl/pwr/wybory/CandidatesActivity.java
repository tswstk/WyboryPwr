package pl.pwr.wybory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class CandidatesActivity extends AppCompatActivity implements OnCandidateInteractionListener {


    RecyclerView mRecyclerView;
    CandidatesAdapter mAdapter;

    ArrayList<Candidate> mValues;
    Election election;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);

        this.election = getIntent().getExtras().getParcelable(Const.ELECTION_BUNDLE);

        mValues = downloadCandidates(this);

        mAdapter = new CandidatesAdapter(mValues, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Candidate> downloadCandidates(CandidatesActivity candidatesActivity) {
        return new ArrayList<>();
    }

    @Override
    public void onCandidateInteractionListener(Candidate candidate) {
        Intent intent = new Intent(this, CandidateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.CANDIDATE_BUNDLE, candidate);
        startActivity(intent);
    }
}
