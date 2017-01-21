package pl.pwr.wybory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class ElectionsActivity extends AppCompatActivity implements OnElectionsInteractionListener {

    RecyclerView mRecyclerView;
    ElectionsAdapter mAdapter;

    ArrayList<Election> mValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections);

        mValues = downloadElections(this);

        mAdapter = new ElectionsAdapter(mValues, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Election> downloadElections(ElectionsActivity electionsActivity) {
        return new ArrayList<>();
    }

    @Override
    public void onElectionsInteractionListener(Election election) {
        Intent intent = new Intent(this, CandidatesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.ELECTION_BUNDLE, election);
        startActivity(intent);
    }
}
