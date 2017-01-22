package pl.pwr.wybory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import pl.pwr.wybory.Adapters.QuestionnaireAdapter;
import pl.pwr.wybory.Dialogs.AddElectionsDialog;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Interfaces.OnQuestionnaireInteracionListener;
import pl.pwr.wybory.Model.Questionnaire;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class QuestionnaireActivity extends AppCompatActivity implements OnQuestionnaireInteracionListener {
    RecyclerView mRecyclerView;
    QuestionnaireAdapter questionnnaireAdapter;

    ArrayList<Questionnaire> mValues;
    ProgressDialog prograssDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaires);

        mValues = new ArrayList<>();
        downloadQuestionnaires();

        questionnnaireAdapter = new QuestionnaireAdapter(mValues, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(questionnnaireAdapter);

        setOnFabAction();
    }

    private void setOnFabAction() {
        findViewById(R.id.add_questionnaire_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddElectionsDialog dialog = new AddElectionsDialog();
                dialog.show(getSupportFragmentManager(), "add_questioonnaire_fab");
            }
        });
    }

    private void downloadQuestionnaires() {

        prograssDialog = ProgressDialog.show(QuestionnaireActivity.this, "",
                "Loading. Please wait...", false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();

        ApiServices service = retrofit.create(ApiServices.class);

        service.getAllQuestionnaires().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response != null && response.body() != null) {
                    try {
                        JSONArray array = new JSONArray(response.body().string());
                        for (int i = 0; i < array.length(); i++) {
                            mValues.add(new Questionnaire(array.getJSONObject(i)));
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    if (prograssDialog != null) {
                        prograssDialog.dismiss();
                    }
                    questionnnaireAdapter.notifyDataSetChanged();

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
    public void onQuestionnaireInteracionListener(Questionnaire questionnaire) {
        Intent intent = new Intent(this, CandidatesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.QUESTIONNAIRE_BUNDLE, questionnaire);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
