package pl.pwr.wybory;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

import okhttp3.ResponseBody;
import pl.pwr.wybory.Interfaces.Access;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Model.Candidate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CandidateActivity extends AppCompatActivity {

    Candidate candidate;
    Button editButton;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        int candidateId = getIntent().getExtras().getInt(Const.CANDIDATE_ID_BUNDLE);

        downloadCandidate(candidateId);

        findViewById(R.id.vote_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote();
            }
        });
        editButton = (Button) findViewById(R.id.edit_program_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditingDialog();
            }
        });
        if (!Access.isCandidate()) editButton.setVisibility(View.GONE);
    }

    private void downloadCandidate(int candidateId) {

        progressDialog = ProgressDialog.show(CandidateActivity.this, "",
                "Loading. Please wait...", false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();
        ApiServices services = retrofit.create(ApiServices.class);
        services.getCandidate(candidateId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("response>>>>>>>>", response.raw().toString());
                if(response.body() != null){
                    try {
                        if (progressDialog!=null) progressDialog.dismiss();
                        candidate = new Candidate(new JSONObject(response.body().string()));
                        fillCandidateInformation();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("response>>>>>>>>", "null");

            }
        });
    }

    private void openEditingDialog() {
        final EditText txtUrl = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Edycja")
                .setView(txtUrl)
                .setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Const.BASE_URL)
                                .build();

                        ApiServices services = retrofit.create(ApiServices.class);

                        services.editProgram(candidate.getElector_id(), String.valueOf(txtUrl.getText())).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    private void fillCandidateInformation() {
        ((TextView)findViewById(R.id.name_textView)).setText(candidate.getFirst_name()+" "+candidate.getLast_name());
        ((TextView)findViewById(R.id.position_textView)).setText(candidate.getPosition().getNameOfPosition());
        ((TextView)findViewById(R.id.date_textView)).setText(candidate.getDate());
        ((TextView)findViewById(R.id.program_textView)).setText(candidate.getProgram());
        ((TextView)findViewById(R.id.faculty_textView)).setText(candidate.getPosition().getFaculty());
    }

    private void vote() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();
        ApiServices services = retrofit.create(ApiServices.class);
                services.vote(candidate.getCandidate_id(), Access.user.getElector_id()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.body()!=null) {
                            Toast.makeText(CandidateActivity.this, "Zagłosowano", Toast.LENGTH_SHORT).show();
                            CandidateActivity.this.finish();
                        }else{
                            Toast.makeText(CandidateActivity.this, "Błąd podczas głosowania", Toast.LENGTH_SHORT).show();
                            CandidateActivity.this.finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CandidateActivity.this, "Błąd podczas głosowania", Toast.LENGTH_SHORT).show();
                        CandidateActivity.this.finish();
                    }
                });
    }

}
