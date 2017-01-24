package pl.pwr.wybory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import pl.pwr.wybory.Adapters.ElectionsAdapter;
import pl.pwr.wybory.Dialogs.AddElectionsDialog;
import pl.pwr.wybory.Interfaces.Access;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Interfaces.OnElectionsInteractionListener;
import pl.pwr.wybory.Model.Election;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ElectionsActivity extends AppCompatActivity implements OnElectionsInteractionListener {

    RecyclerView mRecyclerView;
    ElectionsAdapter mAdapter;

    ArrayList<Election> mValues;
    ProgressDialog prograssDialog;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections);

        mValues = new ArrayList<>();
        downloadElections();

        mAdapter = new ElectionsAdapter(mValues, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        fab = (FloatingActionButton) findViewById(R.id.add_election_fab);
        setOnFabAction();
        if(!Access.isCoordinator()) fab.setVisibility(View.GONE);
    }

    private void setOnFabAction() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AddElectionsDialog dialog = new AddElectionsDialog();
//                dialog.show(getSupportFragmentManager(), "add_elections_dialog");

                Intent intent = new Intent(ElectionsActivity.this, QuestionnaireActivity.class);
                startActivity(intent);
            }
        });
    }

    private void downloadElections() {

        prograssDialog = ProgressDialog.show(ElectionsActivity.this, "",
                "Loading. Please wait...", false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();

        ApiServices service = retrofit.create(ApiServices.class);

        service.getAllElections().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response != null && response.body() != null) {
                    try {
                        JSONArray array = new JSONArray(response.body().string());
                        for (int i = 0; i < array.length(); i++) {
                            mValues.add(new Election(array.getJSONObject(i)));
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    if (prograssDialog != null) {
                        prograssDialog.dismiss();
                    }
                    mAdapter.notifyDataSetChanged();

                } else {
                    System.out.println("null");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onElectionsInteractionListener(Election election) {
        Intent intent = new Intent(this, CandidatesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.ELECTION_BUNDLE, election);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
