package pl.pwr.wybory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import pl.pwr.wybory.Adapters.CandidatesAdapter;
import pl.pwr.wybory.Dialogs.AddElectionsDialog;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Interfaces.OnCandidateInteractionListener;
import pl.pwr.wybory.Model.Candidate;
import pl.pwr.wybory.Model.Election;
import pl.pwr.wybory.Model.Election;
import pl.pwr.wybory.Model.Election;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CandidatesActivity extends AppCompatActivity implements OnCandidateInteractionListener {


    RecyclerView mRecyclerView;
    CandidatesAdapter mAdapter;

    ArrayList<Candidate> mValues;
    Election election;

    ProgressDialog prograssDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);

        this.election = getIntent().getExtras().getParcelable(Const.ELECTION_BUNDLE);

        mValues = new ArrayList<>();
        downloadCandidates(election.getElctionId());

        mAdapter = new CandidatesAdapter(mValues, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        setOnFabAction();
    }

    private void setOnFabAction() {
        findViewById(R.id.add_candidate_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddElectionsDialog dialog = new AddElectionsDialog();
                dialog.show(getSupportFragmentManager(), "add_candidate_dialog");
            }
        });
    }

    private void downloadCandidates(int id) {

        prograssDialog = ProgressDialog.show(CandidatesActivity.this, "",
                "Loading. Please wait...", false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();

        ApiServices service = retrofit.create(ApiServices.class);

        service.getCandidates().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response!=null && response.body() != null){
                    try {
                        JSONArray array = new JSONArray(response.body().string());
                        for (int i = 0; i < array.length(); i++) {
                            mValues.add(new Candidate(array.getJSONObject(i)));
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    if (prograssDialog != null){
                        prograssDialog.dismiss();
                    }
                    mAdapter.notifyDataSetChanged();

                }else{
                    System.out.println("null");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCandidateInteractionListener(Candidate candidate) {
        Intent intent = new Intent(this, CandidateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.CANDIDATE_BUNDLE, candidate);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
