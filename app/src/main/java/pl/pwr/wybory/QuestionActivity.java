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
import org.json.JSONObject;

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

    //ArrayList<Integer> questionsesId;
    int [] questionsesId;
    int iter = 0;

    ArrayList<String> answerses;
    String [] answersesTab;
    JSONArray jsonArray = new JSONArray();
    JSONArray jsonQuestionId = new JSONArray();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionses = new ArrayList<Questions>();

        //answerses = new ArrayList<Answers>();
        answerses = new ArrayList<String>();

        //questionsesId = new ArrayList<Integer>();

        this.questionnaireId = getIntent().getExtras().getInt(Const.QUESTIONNAIRE_BUNDLE);

        answer = (EditText) findViewById(R.id.answer_eidtText);
        question = (TextView) findViewById(R.id.question_textView);


        downloadQuestions();

        mAdapter = new QuestionAdapter(questionses);
        findViewById(R.id.nextQuestion_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    answer();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void answer() throws JSONException {
        if(!answer.getText().toString().equals(""))
        {
            answerses.add(new String(String.valueOf(answer.getText())));
            //answersesTab[iter] = (String.valueOf(answer.getText()));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Answer", answerses.get(answerses.size()-1));
            jsonArray.put(jsonObject);
            //answerses.add(new String(String.valueOf(answer.getText())));
            nextQuestion();
        }
        else
        {
            Toast.makeText(QuestionActivity.this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
        }
    }



    public void nextQuestion() throws JSONException {
        if(questionses.size() > 0)
        {
            questionsesId[iter++] = questionses.get(0).getQuestionId();
            jsonQuestionId.put(questionses.get(0).getQuestionId());
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
                            //questionsesId.add(questionses.get(i).getQuestionId());
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
                questionsesId = new int [questionses.size()];
                answersesTab = new String [questionses.size()];
                questionsesId[iter++] = questionses.get(0).getQuestionId();
                jsonQuestionId.put(questionses.get(0).getQuestionId());
                question.setText((CharSequence) questionses.remove(0).getQuestionBody());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void sendAnswers() throws JSONException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();

        ApiServices services = retrofit.create(ApiServices.class);
        //int i = 0;
        //for(String ans : answerses)
        for(int i = 0; i < answerses.size(); i++)
        {
            services.sendAnswers(answerses.get(i), Access.user.getElector_id(), questionsesId[i]).enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.body()!=null) {
                        Toast.makeText(QuestionActivity.this, "Wysłano odpowiedzi", Toast.LENGTH_SHORT).show();
                        Toast.makeText(QuestionActivity.this, "Dziękujemy za wypełnienie ankiety.", Toast.LENGTH_SHORT).show();
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
}
