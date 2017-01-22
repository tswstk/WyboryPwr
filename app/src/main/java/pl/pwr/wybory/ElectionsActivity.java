package pl.pwr.wybory;

import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections);

        mValues = downloadElections(this);

        mAdapter = new ElectionsAdapter(mValues, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        setOnFabAction();
    }

    private void setOnFabAction() {
        findViewById(R.id.add_election_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddElectionsDialog dialog = new AddElectionsDialog();
                dialog.show(getSupportFragmentManager(), "add_elections_dialog");
            }
        });
    }

    private ArrayList<Election> downloadElections(ElectionsActivity electionsActivity) {

        final ArrayList<Election> elections = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://wyborypwr.azurewebsites.net/api/")
                .build();

        ApiServices service = retrofit.create(ApiServices.class);

       // RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"IdPracownika\":1}");


        service.getAllElections().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    JSONArray array = new JSONArray(response.body().string());
//                    for (int i = 0; i < array.length(); i++) {
//                        elections.add(new Election(array.getJSONObject(i)));
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                if (response!=null && response.body() != null){
                    try {
                        System.out.println(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("null");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
