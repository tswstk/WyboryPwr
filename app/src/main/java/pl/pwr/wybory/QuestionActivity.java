package pl.pwr.wybory;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import pl.pwr.wybory.Adapters.QuestionAdapter;
import pl.pwr.wybory.Interfaces.Access;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Model.Answers;
import pl.pwr.wybory.Model.Questions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionActivity extends AppCompatActivity {

    EditText answer;
    TextView question;
    ProgressDialog progressDialog;

    int questionnaireId;
    QuestionAdapter mAdapter;

    ArrayList<Questions> questionses;
    ArrayList<Integer> questionsesId;
    ArrayList<Answers> answerses;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionses = new ArrayList<Questions>();
        answerses = new ArrayList<Answers>();
        questionsesId = new ArrayList<Integer>();

        this.questionnaireId = getIntent().getExtras().getInt(Const.QUESTIONNAIRE_BUNDLE);

        answer = (EditText) findViewById(R.id.answer_eidtText);
        question = (TextView) findViewById(R.id.question_textView);


        downloadQuestions();

        mAdapter = new QuestionAdapter(questionses);
        findViewById(R.id.nextQuestion_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer();
            }
        });
    }

    private void answer() {
        if(!answer.getText().toString().equals(""))
        {
            answerses.add(new Answers(String.valueOf(answer.getText()), question.getId()));
            nextQuestion();
        }
    }

    public void nextQuestion()
    {
        if(questionses.size() > 0)
        {
            question.setText((CharSequence) questionses.remove(0).getQuestionBody());
            answer.setText("");
        }
        else
        {
            sendAnswers();
            finish();
        }
    }

    private void downloadQuestions() {

        progressDialog = ProgressDialog.show(QuestionActivity.this, "",
                "Loading. Please wait...", false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();

        ApiServices service = retrofit.create(ApiServices.class);

        service.getQuestion(questionnaireId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response!=null && response.body() != null){
                    try {
                        JSONArray array = new JSONArray(response.body().string());
                        for (int i = 0; i < array.length(); i++) {
                            questionses.add(new Questions(array.getJSONObject(i)));
                            questionsesId.add(questionses.get(i).getQuestionId());
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    if (progressDialog != null){
                        progressDialog.dismiss();
                    }
                    mAdapter.notifyDataSetChanged();

                }else{
                    System.out.println("null");
                }
                question.setText((CharSequence) questionses.remove(0).getQuestionBody());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void sendAnswers()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();

        ApiServices services = retrofit.create(ApiServices.class);
        services.sendAnswers(answerses, Access.user.getElector_id(), questionsesId).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null) {
                    Toast.makeText(QuestionActivity.this, "Wysłano odpowiedzi", Toast.LENGTH_SHORT).show();
                    QuestionActivity.this.finish();
                }else{
                    Toast.makeText(QuestionActivity.this, "Błąd podczas wysyłania", Toast.LENGTH_SHORT).show();
                    QuestionActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(QuestionActivity.this, "Nie można uzyskać połączenia", Toast.LENGTH_SHORT).show();
                QuestionActivity.this.finish();
            }
        });
    }
}
